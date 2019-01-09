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
 * @author root
 */
@Entity
@Table(name = "minuteavg_temp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MinuteavgTemp.findAll", query = "SELECT m FROM MinuteavgTemp m")
    , @NamedQuery(name = "MinuteavgTemp.findById", query = "SELECT m FROM MinuteavgTemp m WHERE m.id = :id")
    , @NamedQuery(name = "MinuteavgTemp.findByCompanyId", query = "SELECT m FROM MinuteavgTemp m WHERE m.companyId = :companyId")
    , @NamedQuery(name = "MinuteavgTemp.findByRegionalId", query = "SELECT m FROM MinuteavgTemp m WHERE m.regionalId = :regionalId")
    , @NamedQuery(name = "MinuteavgTemp.findByUnitsId", query = "SELECT m FROM MinuteavgTemp m WHERE m.unitsId = :unitsId")
    , @NamedQuery(name = "MinuteavgTemp.findByDeviceId", query = "SELECT m FROM MinuteavgTemp m WHERE m.deviceId = :deviceId")
    , @NamedQuery(name = "MinuteavgTemp.findByPm10", query = "SELECT m FROM MinuteavgTemp m WHERE m.pm10 = :pm10")
    , @NamedQuery(name = "MinuteavgTemp.findByPm25", query = "SELECT m FROM MinuteavgTemp m WHERE m.pm25 = :pm25")
    , @NamedQuery(name = "MinuteavgTemp.findByNoise", query = "SELECT m FROM MinuteavgTemp m WHERE m.noise = :noise")
    , @NamedQuery(name = "MinuteavgTemp.findByTsp", query = "SELECT m FROM MinuteavgTemp m WHERE m.tsp = :tsp")
    , @NamedQuery(name = "MinuteavgTemp.findByWindspeed", query = "SELECT m FROM MinuteavgTemp m WHERE m.windspeed = :windspeed")
    , @NamedQuery(name = "MinuteavgTemp.findByWinddirection", query = "SELECT m FROM MinuteavgTemp m WHERE m.winddirection = :winddirection")
    , @NamedQuery(name = "MinuteavgTemp.findByTemperature", query = "SELECT m FROM MinuteavgTemp m WHERE m.temperature = :temperature")
    , @NamedQuery(name = "MinuteavgTemp.findByHumidity", query = "SELECT m FROM MinuteavgTemp m WHERE m.humidity = :humidity")
    , @NamedQuery(name = "MinuteavgTemp.findByAirpressure", query = "SELECT m FROM MinuteavgTemp m WHERE m.airpressure = :airpressure")
    , @NamedQuery(name = "MinuteavgTemp.findByBoardHunmidity", query = "SELECT m FROM MinuteavgTemp m WHERE m.boardHunmidity = :boardHunmidity")
    , @NamedQuery(name = "MinuteavgTemp.findByBoardTemperature", query = "SELECT m FROM MinuteavgTemp m WHERE m.boardTemperature = :boardTemperature")
    , @NamedQuery(name = "MinuteavgTemp.findByRainfall", query = "SELECT m FROM MinuteavgTemp m WHERE m.rainfall = :rainfall")
    , @NamedQuery(name = "MinuteavgTemp.findByVoltage", query = "SELECT m FROM MinuteavgTemp m WHERE m.voltage = :voltage")
    , @NamedQuery(name = "MinuteavgTemp.findByCreateTime", query = "SELECT m FROM MinuteavgTemp m WHERE m.createTime = :createTime")
    , @NamedQuery(name = "MinuteavgTemp.findByAvgTime", query = "SELECT m FROM MinuteavgTemp m WHERE m.avgTime = :avgTime")
    , @NamedQuery(name = "MinuteavgTemp.findByDataType", query = "SELECT m FROM MinuteavgTemp m WHERE m.dataType = :dataType")
    , @NamedQuery(name = "MinuteavgTemp.findByMark", query = "SELECT m FROM MinuteavgTemp m WHERE m.mark = :mark")})
public class MinuteavgTemp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "company_id")
    private int companyId;
    @Basic(optional = false)
    @Column(name = "regional_id")
    private int regionalId;
    @Basic(optional = false)
    @Column(name = "units_id")
    private int unitsId;
    @Basic(optional = false)
    @Column(name = "device_id")
    private String deviceId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pm10")
    private Double pm10;
    @Column(name = "pm25")
    private Double pm25;
    @Column(name = "noise")
    private Double noise;
    @Column(name = "tsp")
    private Double tsp;
    @Column(name = "windspeed")
    private Double windspeed;
    @Column(name = "winddirection")
    private Double winddirection;
    @Column(name = "temperature")
    private Double temperature;
    @Column(name = "humidity")
    private Double humidity;
    @Column(name = "airpressure")
    private Double airpressure;
    @Column(name = "board_hunmidity")
    private Double boardHunmidity;
    @Column(name = "board_temperature")
    private Double boardTemperature;
    @Column(name = "rainfall")
    private Double rainfall;
    @Column(name = "voltage")
    private Double voltage;
    @Basic(optional = false)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Basic(optional = false)
    @Column(name = "avg_time")
    @Temporal(TemporalType.DATE)
    private Date avgTime;
    @Column(name = "dataType")
    private Boolean dataType;
    @Column(name = "mark")
    private Boolean mark;

    public MinuteavgTemp() {
    }

    public MinuteavgTemp(Long id) {
        this.id = id;
    }

    public MinuteavgTemp(Long id, int companyId, int regionalId, int unitsId, String deviceId, Date createTime, Date avgTime) {
        this.id = id;
        this.companyId = companyId;
        this.regionalId = regionalId;
        this.unitsId = unitsId;
        this.deviceId = deviceId;
        this.createTime = createTime;
        this.avgTime = avgTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getRegionalId() {
        return regionalId;
    }

    public void setRegionalId(int regionalId) {
        this.regionalId = regionalId;
    }

    public int getUnitsId() {
        return unitsId;
    }

    public void setUnitsId(int unitsId) {
        this.unitsId = unitsId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Double getPm25() {
        return pm25;
    }

    public void setPm25(Double pm25) {
        this.pm25 = pm25;
    }

    public Double getNoise() {
        return noise;
    }

    public void setNoise(Double noise) {
        this.noise = noise;
    }

    public Double getTsp() {
        return tsp;
    }

    public void setTsp(Double tsp) {
        this.tsp = tsp;
    }

    public Double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(Double windspeed) {
        this.windspeed = windspeed;
    }

    public Double getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(Double winddirection) {
        this.winddirection = winddirection;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getAirpressure() {
        return airpressure;
    }

    public void setAirpressure(Double airpressure) {
        this.airpressure = airpressure;
    }

    public Double getBoardHunmidity() {
        return boardHunmidity;
    }

    public void setBoardHunmidity(Double boardHunmidity) {
        this.boardHunmidity = boardHunmidity;
    }

    public Double getBoardTemperature() {
        return boardTemperature;
    }

    public void setBoardTemperature(Double boardTemperature) {
        this.boardTemperature = boardTemperature;
    }

    public Double getRainfall() {
        return rainfall;
    }

    public void setRainfall(Double rainfall) {
        this.rainfall = rainfall;
    }

    public Double getVoltage() {
        return voltage;
    }

    public void setVoltage(Double voltage) {
        this.voltage = voltage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Date avgTime) {
        this.avgTime = avgTime;
    }

    public Boolean getDataType() {
        return dataType;
    }

    public void setDataType(Boolean dataType) {
        this.dataType = dataType;
    }

    public Boolean getMark() {
        return mark;
    }

    public void setMark(Boolean mark) {
        this.mark = mark;
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
        if (!(object instanceof MinuteavgTemp)) {
            return false;
        }
        MinuteavgTemp other = (MinuteavgTemp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.MinuteavgTemp[ id=" + id + " ]";
    }
    
}
