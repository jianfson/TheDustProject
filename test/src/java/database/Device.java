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
@Table(name = "device")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d")
    , @NamedQuery(name = "Device.findById", query = "SELECT d FROM Device d WHERE d.id = :id")
    , @NamedQuery(name = "Device.findByDeviceId", query = "SELECT d FROM Device d WHERE d.deviceId = :deviceId")
    , @NamedQuery(name = "Device.findByDeviceName", query = "SELECT d FROM Device d WHERE d.deviceName = :deviceName")
    , @NamedQuery(name = "Device.findByIsClosed", query = "SELECT d FROM Device d WHERE d.isClosed = :isClosed")
    , @NamedQuery(name = "Device.findByStatus", query = "SELECT d FROM Device d WHERE d.status = :status")
    , @NamedQuery(name = "Device.findByType", query = "SELECT d FROM Device d WHERE d.type = :type")
    , @NamedQuery(name = "Device.findByParameters", query = "SELECT d FROM Device d WHERE d.parameters = :parameters")
    , @NamedQuery(name = "Device.findByShowName", query = "SELECT d FROM Device d WHERE d.showName = :showName")
    , @NamedQuery(name = "Device.findByDeviceAddress", query = "SELECT d FROM Device d WHERE d.deviceAddress = :deviceAddress")
    , @NamedQuery(name = "Device.findByIsCar", query = "SELECT d FROM Device d WHERE d.isCar = :isCar")
    , @NamedQuery(name = "Device.findByCarNo", query = "SELECT d FROM Device d WHERE d.carNo = :carNo")
    , @NamedQuery(name = "Device.findByLng", query = "SELECT d FROM Device d WHERE d.lng = :lng")
    , @NamedQuery(name = "Device.findByLat", query = "SELECT d FROM Device d WHERE d.lat = :lat")
    , @NamedQuery(name = "Device.findByCompanyId", query = "SELECT d FROM Device d WHERE d.companyId = :companyId")
    , @NamedQuery(name = "Device.findByRegionalId", query = "SELECT d FROM Device d WHERE d.regionalId = :regionalId")
    , @NamedQuery(name = "Device.findByGkdId", query = "SELECT d FROM Device d WHERE d.gkdId = :gkdId")
    , @NamedQuery(name = "Device.findByCreateId", query = "SELECT d FROM Device d WHERE d.createId = :createId")
    , @NamedQuery(name = "Device.findByUpdateId", query = "SELECT d FROM Device d WHERE d.updateId = :updateId")
    , @NamedQuery(name = "Device.findBySn", query = "SELECT d FROM Device d WHERE d.sn = :sn")
    , @NamedQuery(name = "Device.findByCreateTime", query = "SELECT d FROM Device d WHERE d.createTime = :createTime")
    , @NamedQuery(name = "Device.findByUpdateTime", query = "SELECT d FROM Device d WHERE d.updateTime = :updateTime")
    , @NamedQuery(name = "Device.findByRemarks", query = "SELECT d FROM Device d WHERE d.remarks = :remarks")
    , @NamedQuery(name = "Device.findByPic", query = "SELECT d FROM Device d WHERE d.pic = :pic")
    , @NamedQuery(name = "Device.findByDeviceNo", query = "SELECT d FROM Device d WHERE d.deviceNo = :deviceNo")
    , @NamedQuery(name = "Device.findByFactory", query = "SELECT d FROM Device d WHERE d.factory = :factory")
    , @NamedQuery(name = "Device.findByBatch", query = "SELECT d FROM Device d WHERE d.batch = :batch")
    , @NamedQuery(name = "Device.findByBuy", query = "SELECT d FROM Device d WHERE d.buy = :buy")
    , @NamedQuery(name = "Device.findByVideoIp", query = "SELECT d FROM Device d WHERE d.videoIp = :videoIp")
    , @NamedQuery(name = "Device.findByVideoPort", query = "SELECT d FROM Device d WHERE d.videoPort = :videoPort")
    , @NamedQuery(name = "Device.findByVideoName", query = "SELECT d FROM Device d WHERE d.videoName = :videoName")
    , @NamedQuery(name = "Device.findByVideoPassword", query = "SELECT d FROM Device d WHERE d.videoPassword = :videoPassword")
    , @NamedQuery(name = "Device.findByVideoVersion", query = "SELECT d FROM Device d WHERE d.videoVersion = :videoVersion")
    , @NamedQuery(name = "Device.findByCard", query = "SELECT d FROM Device d WHERE d.card = :card")
    , @NamedQuery(name = "Device.findByDataType", query = "SELECT d FROM Device d WHERE d.dataType = :dataType")
    , @NamedQuery(name = "Device.findByIsCalibration", query = "SELECT d FROM Device d WHERE d.isCalibration = :isCalibration")
    , @NamedQuery(name = "Device.findByLaststartTime", query = "SELECT d FROM Device d WHERE d.laststartTime = :laststartTime")
    , @NamedQuery(name = "Device.findByLastdownTime", query = "SELECT d FROM Device d WHERE d.lastdownTime = :lastdownTime")
    , @NamedQuery(name = "Device.findByExpectType", query = "SELECT d FROM Device d WHERE d.expectType = :expectType")
    , @NamedQuery(name = "Device.findByIsAllowed", query = "SELECT d FROM Device d WHERE d.isAllowed = :isAllowed")
    , @NamedQuery(name = "Device.findByLocation", query = "SELECT d FROM Device d WHERE d.location = :location")})
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
    @Column(name = "is_closed")
    private Integer isClosed;
    @Column(name = "status")
    private Integer status;
    @Column(name = "type")
    private Integer type;
    @Column(name = "parameters")
    private String parameters;
    @Column(name = "show_name")
    private String showName;
    @Column(name = "device_address")
    private String deviceAddress;
    @Column(name = "is_car")
    private Integer isCar;
    @Column(name = "car_no")
    private String carNo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "lng")
    private Double lng;
    @Column(name = "lat")
    private Double lat;
    @Column(name = "company_id")
    private Integer companyId;
    @Column(name = "regional_id")
    private Integer regionalId;
    @Column(name = "gkd_id")
    private String gkdId;
    @Column(name = "create_id")
    private Integer createId;
    @Column(name = "update_id")
    private Integer updateId;
    @Column(name = "sn")
    private String sn;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "pic")
    private String pic;
    @Column(name = "device_no")
    private String deviceNo;
    @Column(name = "factory")
    private Integer factory;
    @Column(name = "batch")
    private Integer batch;
    @Column(name = "buy")
    private Integer buy;
    @Column(name = "video_ip")
    private String videoIp;
    @Column(name = "video_port")
    private Integer videoPort;
    @Column(name = "video_name")
    private String videoName;
    @Column(name = "video_password")
    private String videoPassword;
    @Column(name = "video_version")
    private String videoVersion;
    @Column(name = "card")
    private String card;
    @Column(name = "dataType")
    private Integer dataType;
    @Column(name = "is_calibration")
    private Integer isCalibration;
    @Column(name = "laststart_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date laststartTime;
    @Column(name = "lastdown_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastdownTime;
    @Column(name = "expect_type")
    private String expectType;
    @Column(name = "is_allowed")
    private Integer isAllowed;
    @Basic(optional = false)
    @Column(name = "location")
    private String location;

    public Device() {
    }

    public Device(Integer id) {
        this.id = id;
    }

    public Device(Integer id, String location) {
        this.id = id;
        this.location = location;
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

    public Integer getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Integer isClosed) {
        this.isClosed = isClosed;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public Integer getIsCar() {
        return isCar;
    }

    public void setIsCar(Integer isCar) {
        this.isCar = isCar;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(Integer regionalId) {
        this.regionalId = regionalId;
    }

    public String getGkdId() {
        return gkdId;
    }

    public void setGkdId(String gkdId) {
        this.gkdId = gkdId;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getFactory() {
        return factory;
    }

    public void setFactory(Integer factory) {
        this.factory = factory;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Integer getBuy() {
        return buy;
    }

    public void setBuy(Integer buy) {
        this.buy = buy;
    }

    public String getVideoIp() {
        return videoIp;
    }

    public void setVideoIp(String videoIp) {
        this.videoIp = videoIp;
    }

    public Integer getVideoPort() {
        return videoPort;
    }

    public void setVideoPort(Integer videoPort) {
        this.videoPort = videoPort;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPassword() {
        return videoPassword;
    }

    public void setVideoPassword(String videoPassword) {
        this.videoPassword = videoPassword;
    }

    public String getVideoVersion() {
        return videoVersion;
    }

    public void setVideoVersion(String videoVersion) {
        this.videoVersion = videoVersion;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getIsCalibration() {
        return isCalibration;
    }

    public void setIsCalibration(Integer isCalibration) {
        this.isCalibration = isCalibration;
    }

    public Date getLaststartTime() {
        return laststartTime;
    }

    public void setLaststartTime(Date laststartTime) {
        this.laststartTime = laststartTime;
    }

    public Date getLastdownTime() {
        return lastdownTime;
    }

    public void setLastdownTime(Date lastdownTime) {
        this.lastdownTime = lastdownTime;
    }

    public String getExpectType() {
        return expectType;
    }

    public void setExpectType(String expectType) {
        this.expectType = expectType;
    }

    public Integer getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(Integer isAllowed) {
        this.isAllowed = isAllowed;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
