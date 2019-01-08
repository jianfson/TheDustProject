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
 * @author jiangxin
 */
@Entity
@Table(name = "company")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
    , @NamedQuery(name = "Company.findById", query = "SELECT c FROM Company c WHERE c.id = :id")
    , @NamedQuery(name = "Company.findByCompanyName", query = "SELECT c FROM Company c WHERE c.companyName = :companyName")
    , @NamedQuery(name = "Company.findByUserId", query = "SELECT c FROM Company c WHERE c.userId = :userId")
    , @NamedQuery(name = "Company.findByCompanyAddress", query = "SELECT c FROM Company c WHERE c.companyAddress = :companyAddress")
    , @NamedQuery(name = "Company.findByUnitsId", query = "SELECT c FROM Company c WHERE c.unitsId = :unitsId")
    , @NamedQuery(name = "Company.findByRegionalId", query = "SELECT c FROM Company c WHERE c.regionalId = :regionalId")
    , @NamedQuery(name = "Company.findByCreateTime", query = "SELECT c FROM Company c WHERE c.createTime = :createTime")
    , @NamedQuery(name = "Company.findByUpdateTime", query = "SELECT c FROM Company c WHERE c.updateTime = :updateTime")
    , @NamedQuery(name = "Company.findByRemarks", query = "SELECT c FROM Company c WHERE c.remarks = :remarks")
    , @NamedQuery(name = "Company.findByGq", query = "SELECT c FROM Company c WHERE c.gq = :gq")
    , @NamedQuery(name = "Company.findByKgrq", query = "SELECT c FROM Company c WHERE c.kgrq = :kgrq")
    , @NamedQuery(name = "Company.findByJgrq", query = "SELECT c FROM Company c WHERE c.jgrq = :jgrq")
    , @NamedQuery(name = "Company.findByJldw", query = "SELECT c FROM Company c WHERE c.jldw = :jldw")
    , @NamedQuery(name = "Company.findByJllxr", query = "SELECT c FROM Company c WHERE c.jllxr = :jllxr")
    , @NamedQuery(name = "Company.findByJldh", query = "SELECT c FROM Company c WHERE c.jldh = :jldh")
    , @NamedQuery(name = "Company.findByJsdw", query = "SELECT c FROM Company c WHERE c.jsdw = :jsdw")
    , @NamedQuery(name = "Company.findByJslxr", query = "SELECT c FROM Company c WHERE c.jslxr = :jslxr")
    , @NamedQuery(name = "Company.findByJsdh", query = "SELECT c FROM Company c WHERE c.jsdh = :jsdh")
    , @NamedQuery(name = "Company.findBySchedule", query = "SELECT c FROM Company c WHERE c.schedule = :schedule")
    , @NamedQuery(name = "Company.findByJgType", query = "SELECT c FROM Company c WHERE c.jgType = :jgType")
    , @NamedQuery(name = "Company.findByType", query = "SELECT c FROM Company c WHERE c.type = :type")
    , @NamedQuery(name = "Company.findByAjbah", query = "SELECT c FROM Company c WHERE c.ajbah = :ajbah")
    , @NamedQuery(name = "Company.findByAjbadw", query = "SELECT c FROM Company c WHERE c.ajbadw = :ajbadw")
    , @NamedQuery(name = "Company.findByGuid", query = "SELECT c FROM Company c WHERE c.guid = :guid")
    , @NamedQuery(name = "Company.findByJsCode", query = "SELECT c FROM Company c WHERE c.jsCode = :jsCode")
    , @NamedQuery(name = "Company.findByJlCode", query = "SELECT c FROM Company c WHERE c.jlCode = :jlCode")
    , @NamedQuery(name = "Company.findByJlGuid", query = "SELECT c FROM Company c WHERE c.jlGuid = :jlGuid")
    , @NamedQuery(name = "Company.findByJsGuid", query = "SELECT c FROM Company c WHERE c.jsGuid = :jsGuid")
    , @NamedQuery(name = "Company.findByEditTimes", query = "SELECT c FROM Company c WHERE c.editTimes = :editTimes")
    , @NamedQuery(name = "Company.findBySglxr", query = "SELECT c FROM Company c WHERE c.sglxr = :sglxr")
    , @NamedQuery(name = "Company.findBySgdh", query = "SELECT c FROM Company c WHERE c.sgdh = :sgdh")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "company_address")
    private String companyAddress;
    @Column(name = "units_id")
    private Integer unitsId;
    @Column(name = "regional_id")
    private Integer regionalId;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "gq")
    private String gq;
    @Column(name = "kgrq")
    @Temporal(TemporalType.TIMESTAMP)
    private Date kgrq;
    @Column(name = "jgrq")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jgrq;
    @Column(name = "jldw")
    private String jldw;
    @Column(name = "jllxr")
    private String jllxr;
    @Column(name = "jldh")
    private String jldh;
    @Column(name = "jsdw")
    private String jsdw;
    @Column(name = "jslxr")
    private String jslxr;
    @Column(name = "jsdh")
    private String jsdh;
    @Column(name = "schedule")
    private String schedule;
    @Column(name = "jgType")
    private Short jgType;
    @Column(name = "type")
    private Short type;
    @Column(name = "ajbah")
    private String ajbah;
    @Column(name = "ajbadw")
    private String ajbadw;
    @Column(name = "guid")
    private String guid;
    @Column(name = "jsCode")
    private String jsCode;
    @Column(name = "jlCode")
    private String jlCode;
    @Column(name = "jlGuid")
    private String jlGuid;
    @Column(name = "jsGuid")
    private String jsGuid;
    @Column(name = "edit_times")
    private Integer editTimes;
    @Column(name = "sglxr")
    private String sglxr;
    @Column(name = "sgdh")
    private String sgdh;

    public Company() {
    }

    public Company(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Integer getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(Integer unitsId) {
        this.unitsId = unitsId;
    }

    public Integer getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(Integer regionalId) {
        this.regionalId = regionalId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getGq() {
        return gq;
    }

    public void setGq(String gq) {
        this.gq = gq;
    }

    public Date getKgrq() {
        return kgrq;
    }

    public void setKgrq(Date kgrq) {
        this.kgrq = kgrq;
    }

    public Date getJgrq() {
        return jgrq;
    }

    public void setJgrq(Date jgrq) {
        this.jgrq = jgrq;
    }

    public String getJldw() {
        return jldw;
    }

    public void setJldw(String jldw) {
        this.jldw = jldw;
    }

    public String getJllxr() {
        return jllxr;
    }

    public void setJllxr(String jllxr) {
        this.jllxr = jllxr;
    }

    public String getJldh() {
        return jldh;
    }

    public void setJldh(String jldh) {
        this.jldh = jldh;
    }

    public String getJsdw() {
        return jsdw;
    }

    public void setJsdw(String jsdw) {
        this.jsdw = jsdw;
    }

    public String getJslxr() {
        return jslxr;
    }

    public void setJslxr(String jslxr) {
        this.jslxr = jslxr;
    }

    public String getJsdh() {
        return jsdh;
    }

    public void setJsdh(String jsdh) {
        this.jsdh = jsdh;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Short getJgType() {
        return jgType;
    }

    public void setJgType(Short jgType) {
        this.jgType = jgType;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getAjbah() {
        return ajbah;
    }

    public void setAjbah(String ajbah) {
        this.ajbah = ajbah;
    }

    public String getAjbadw() {
        return ajbadw;
    }

    public void setAjbadw(String ajbadw) {
        this.ajbadw = ajbadw;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getJsCode() {
        return jsCode;
    }

    public void setJsCode(String jsCode) {
        this.jsCode = jsCode;
    }

    public String getJlCode() {
        return jlCode;
    }

    public void setJlCode(String jlCode) {
        this.jlCode = jlCode;
    }

    public String getJlGuid() {
        return jlGuid;
    }

    public void setJlGuid(String jlGuid) {
        this.jlGuid = jlGuid;
    }

    public String getJsGuid() {
        return jsGuid;
    }

    public void setJsGuid(String jsGuid) {
        this.jsGuid = jsGuid;
    }

    public Integer getEditTimes() {
        return editTimes;
    }

    public void setEditTimes(Integer editTimes) {
        this.editTimes = editTimes;
    }

    public String getSglxr() {
        return sglxr;
    }

    public void setSglxr(String sglxr) {
        this.sglxr = sglxr;
    }

    public String getSgdh() {
        return sgdh;
    }

    public void setSgdh(String sgdh) {
        this.sgdh = sgdh;
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
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Company[ id=" + id + " ]";
    }
    
}
