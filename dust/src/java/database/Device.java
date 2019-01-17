/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "device")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
    , @NamedQuery(name = "Device.findById", query = "SELECT d FROM Device d WHERE d.id = :id")
    , @NamedQuery(name = "Device.findByDeviceId", query = "SELECT d FROM Device d WHERE d.deviceId = :deviceId")
    , @NamedQuery(name = "Device.findByDeviceName", query = "SELECT d FROM Device d WHERE d.deviceName = :deviceName")
    , @NamedQuery(name = "Device.findByDeviceAddress", query = "SELECT d FROM Device d WHERE d.deviceAddress = :deviceAddress")
    , @NamedQuery(name = "Device.findByLng", query = "SELECT d FROM Device d WHERE d.lng = :lng")
    , @NamedQuery(name = "Device.findByLat", query = "SELECT d FROM Device d WHERE d.lat = :lat")
    , @NamedQuery(name = "Device.findByRegionalId", query = "SELECT d FROM Device d WHERE d.regionalId = :regionalId")
    , @NamedQuery(name = "Device.findAllcity", query = "SELECT d FROM Device d WHERE d.regionalId > 510101\n" +
                                                                               "AND d.regionalId <= 510109\n")})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Device.findByRegionalIdForMap"
            , query = "SELECT d.device_id as deviceId\n" +
                    ", ANY_VALUE(d.device_name) as deviceName\n" +
                    ", ANY_VALUE(d.device_address) as deviceAddress\n" +
                    ", ANY_VALUE(d.lng) as lng, ANY_VALUE(d.lat) as lat\n" +
                    ",AVG(da.pm10) as pm10\n" +
                    "FROM device d LEFT JOIN dayavg da\n" +
                    "on d.device_id = da.device_id\n" +
                    "where\n" +
                    "d.regional_id=?regionalId\n" +
                    "AND da.avg_time >= ?from\n" +
                    "AND da.avg_time < ?to\n" +
                    "GROUP BY d.device_id;"
            , resultSetMapping = "forMap")
    , @NamedNativeQuery(name = "Device.findAllcityForMap"
            , query = "SELECT d.device_id as deviceId\n" +
                    ", ANY_VALUE(d.device_name) as deviceName\n" +
                    ", ANY_VALUE(d.device_address) as deviceAddress\n" +
                    ", ANY_VALUE(d.lng) as lng, ANY_VALUE(d.lat) as lat\n" +
                    ",AVG(da.pm10) as pm10\n" +
                    "FROM device d LEFT JOIN dayavg da\n" +
                    "on d.device_id = da.device_id\n" +
                    "where (d.regional_id > 510101 AND d.regional_id <= 510109)\n" +
                    "AND (da.avg_time >= ?from AND da.avg_time < ?to)\n" +
                    "GROUP BY d.device_id;"
            , resultSetMapping = "forMap")
    , @NamedNativeQuery(name = "Device.findByRegionalIdForTable"
            , query = "SELECT d.device_id, d.device_name FROM device d where d.regional_id=?regionalId"
            , resultSetMapping = "forTable")
    , @NamedNativeQuery(name = "Device.findDeviceForRelation"
            , query = "select CASE d.regional_id\n" +
                        "  WHEN 510104 THEN '锦江区'\n" +
                        "  WHEN 510105 THEN '青羊区' \n" +
                        "  WHEN 510106 THEN '金牛区'\n" +
                        "  WHEN 510107 THEN '武侯区'\n" +
                        "  WHEN 510108 THEN '成华区'\n" +
                        "  WHEN 510109 THEN '高新区'\n" +
                        "  ELSE '市辖区' END as regionalId,\n" +
                        "count(*) as relation\n" +
                        "from device d\n" +
                        "where\n" +
                        "d.regional_id > 510101\n" +
                        "AND d.regional_id <= 510109\n" +
                        "group by regionalId having relation>1"
            , resultSetMapping = "forRelation")
})
@SqlResultSetMappings(
{
    @SqlResultSetMapping(
            name="forMap",
            entities = {},  
            columns = {  
                @ColumnResult(name = "deviceId"),  
                @ColumnResult(name = "deviceName"),
                @ColumnResult(name = "deviceAddress"),
                @ColumnResult(name = "lng"),
                @ColumnResult(name = "lat"),
                @ColumnResult(name = "pm10")
            }  
    )
    , @SqlResultSetMapping(
            name="forTable",
            entities = {},  
            columns = {  
                @ColumnResult(name = "device_id"),  
                @ColumnResult(name = "device_name") 
            }  
    )
    , @SqlResultSetMapping(
            name="forRelation",
            entities = {},  
            columns = {  
                @ColumnResult(name = "regionalId"),  
                @ColumnResult(name = "relation") 
            }  
    )
})

public class Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "device_id")
    private String deviceId;
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "device_address")
    private String deviceAddress;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "lng")
    private Double lng;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "regional_id")
    private Integer regionalId;

    public Device() {
    }

    public Device(Integer id) {
        this.id = id;
    }
    
    public Device(String deviceId, String deviceName) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(Integer regionalId) {
        this.regionalId = regionalId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Device[ id=" + id + " ]";
    }
    
}
