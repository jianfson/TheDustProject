/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author root
 */
@Entity
@Table(name = "national")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "National.findAll", query = "SELECT n FROM National n")
    , @NamedQuery(name = "National.findById", query = "SELECT n FROM National n WHERE n.id = :id")
    , @NamedQuery(name = "National.findByName", query = "SELECT n FROM National n WHERE n.name = :name")
    , @NamedQuery(name = "National.findByLng", query = "SELECT n FROM National n WHERE n.lng = :lng")
    , @NamedQuery(name = "National.findByLat", query = "SELECT n FROM National n WHERE n.lat = :lat")
    , @NamedQuery(name = "National.findByMn", query = "SELECT n FROM National n WHERE n.mn = :mn")
    , @NamedQuery(name = "National.findByCode", query = "SELECT n FROM National n WHERE n.code = :code")
    , @NamedQuery(name = "National.findByCreateTime", query = "SELECT n FROM National n WHERE n.createTime = :createTime")
    , @NamedQuery(name = "National.findByPm10", query = "SELECT n FROM National n WHERE n.pm10 = :pm10")
    , @NamedQuery(name = "National.findBySo2", query = "SELECT n FROM National n WHERE n.so2 = :so2")
    , @NamedQuery(name = "National.findByNo2", query = "SELECT n FROM National n WHERE n.no2 = :no2")
    , @NamedQuery(name = "National.findByCo1", query = "SELECT n FROM National n WHERE n.co1 = :co1")
    , @NamedQuery(name = "National.findByO3", query = "SELECT n FROM National n WHERE n.o3 = :o3")
    , @NamedQuery(name = "National.findByAqi", query = "SELECT n FROM National n WHERE n.aqi = :aqi")})
public class National implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "lng")
    private double lng;
    @Basic(optional = false)
    @Column(name = "lat")
    private double lat;
    @Basic(optional = false)
    @Column(name = "mn")
    private String mn;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "pm10")
    private BigDecimal pm10;
    @Column(name = "so2")
    private BigDecimal so2;
    @Column(name = "no2")
    private BigDecimal no2;
    @Column(name = "co1")
    private BigDecimal co1;
    @Column(name = "o3")
    private BigDecimal o3;
    @Column(name = "aqi")
    private BigDecimal aqi;

    public National() {
    }

    public National(Integer id) {
        this.id = id;
    }

    public National(Integer id, double lng, double lat, String mn, String code, Date createTime, BigDecimal pm10) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.mn = mn;
        this.code = code;
        this.createTime = createTime;
        this.pm10 = pm10;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPm10() {
        return pm10;
    }

    public void setPm10(BigDecimal pm10) {
        this.pm10 = pm10;
    }

    public BigDecimal getSo2() {
        return so2;
    }

    public void setSo2(BigDecimal so2) {
        this.so2 = so2;
    }

    public BigDecimal getNo2() {
        return no2;
    }

    public void setNo2(BigDecimal no2) {
        this.no2 = no2;
    }

    public BigDecimal getCo1() {
        return co1;
    }

    public void setCo1(BigDecimal co1) {
        this.co1 = co1;
    }

    public BigDecimal getO3() {
        return o3;
    }

    public void setO3(BigDecimal o3) {
        this.o3 = o3;
    }

    public BigDecimal getAqi() {
        return aqi;
    }

    public void setAqi(BigDecimal aqi) {
        this.aqi = aqi;
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
        if (!(object instanceof National)) {
            return false;
        }
        National other = (National) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.National[ id=" + id + " ]";
    }
    
}
