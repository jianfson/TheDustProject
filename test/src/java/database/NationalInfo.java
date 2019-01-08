/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jiangxin
 */
@Entity
@Table(name = "national_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NationalInfo.findAll", query = "SELECT n FROM NationalInfo n")
    , @NamedQuery(name = "NationalInfo.findById", query = "SELECT n FROM NationalInfo n WHERE n.id = :id")
    , @NamedQuery(name = "NationalInfo.findByName", query = "SELECT n FROM NationalInfo n WHERE n.name = :name")
    , @NamedQuery(name = "NationalInfo.findByLng", query = "SELECT n FROM NationalInfo n WHERE n.lng = :lng")
    , @NamedQuery(name = "NationalInfo.findByLat", query = "SELECT n FROM NationalInfo n WHERE n.lat = :lat")
    , @NamedQuery(name = "NationalInfo.findByMn", query = "SELECT n FROM NationalInfo n WHERE n.mn = :mn")
    , @NamedQuery(name = "NationalInfo.findByCode", query = "SELECT n FROM NationalInfo n WHERE n.code = :code")
    , @NamedQuery(name = "NationalInfo.findBySrcCode", query = "SELECT n FROM NationalInfo n WHERE n.srcCode = :srcCode")})
public class NationalInfo implements Serializable {

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
    @Column(name = "src_code")
    private String srcCode;

    public NationalInfo() {
    }

    public NationalInfo(Integer id) {
        this.id = id;
    }

    public NationalInfo(Integer id, double lng, double lat, String mn, String code) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.mn = mn;
        this.code = code;
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

    public String getSrcCode() {
        return srcCode;
    }

    public void setSrcCode(String srcCode) {
        this.srcCode = srcCode;
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
        if (!(object instanceof NationalInfo)) {
            return false;
        }
        NationalInfo other = (NationalInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.NationalInfo[ id=" + id + " ]";
    }
    
}
