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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "houravg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Houravg.findAll", query = "SELECT h FROM Houravg h")
    , @NamedQuery(name = "Houravg.findById", query = "SELECT h FROM Houravg h WHERE h.houravgPK.id = :id")
    , @NamedQuery(name = "Houravg.findByCompanyId", query = "SELECT h FROM Houravg h WHERE h.companyId = :companyId")
    , @NamedQuery(name = "Houravg.findByRegionalId", query = "SELECT h FROM Houravg h WHERE h.regionalId = :regionalId")
    , @NamedQuery(name = "Houravg.findByUnitsId", query = "SELECT h FROM Houravg h WHERE h.unitsId = :unitsId")
    , @NamedQuery(name = "Houravg.findByDeviceId", query = "SELECT h FROM Houravg h WHERE h.deviceId = :deviceId")
    , @NamedQuery(name = "Houravg.findByPm10", query = "SELECT h FROM Houravg h WHERE h.pm10 = :pm10")
    , @NamedQuery(name = "Houravg.findByPm25", query = "SELECT h FROM Houravg h WHERE h.pm25 = :pm25")
    , @NamedQuery(name = "Houravg.findByNoise", query = "SELECT h FROM Houravg h WHERE h.noise = :noise")
    , @NamedQuery(name = "Houravg.findByTsp", query = "SELECT h FROM Houravg h WHERE h.tsp = :tsp")
    , @NamedQuery(name = "Houravg.findByWindspeed", query = "SELECT h FROM Houravg h WHERE h.windspeed = :windspeed")
    , @NamedQuery(name = "Houravg.findByWinddirection", query = "SELECT h FROM Houravg h WHERE h.winddirection = :winddirection")
    , @NamedQuery(name = "Houravg.findByTemperature", query = "SELECT h FROM Houravg h WHERE h.temperature = :temperature")
    , @NamedQuery(name = "Houravg.findByHumidity", query = "SELECT h FROM Houravg h WHERE h.humidity = :humidity")
    , @NamedQuery(name = "Houravg.findByAirpressure", query = "SELECT h FROM Houravg h WHERE h.airpressure = :airpressure")
    , @NamedQuery(name = "Houravg.findByBoardHunmidity", query = "SELECT h FROM Houravg h WHERE h.boardHunmidity = :boardHunmidity")
    , @NamedQuery(name = "Houravg.findByBoardTemperature", query = "SELECT h FROM Houravg h WHERE h.boardTemperature = :boardTemperature")
    , @NamedQuery(name = "Houravg.findByRainfall", query = "SELECT h FROM Houravg h WHERE h.rainfall = :rainfall")
    , @NamedQuery(name = "Houravg.findByVoltage", query = "SELECT h FROM Houravg h WHERE h.voltage = :voltage")
    , @NamedQuery(name = "Houravg.findByRate", query = "SELECT h FROM Houravg h WHERE h.rate = :rate")
    , @NamedQuery(name = "Houravg.findByValid", query = "SELECT h FROM Houravg h WHERE h.valid = :valid")
    , @NamedQuery(name = "Houravg.findByDataType", query = "SELECT h FROM Houravg h WHERE h.dataType = :dataType")
    , @NamedQuery(name = "Houravg.findByCreateTime", query = "SELECT h FROM Houravg h WHERE h.houravgPK.createTime = :createTime")
    , @NamedQuery(name = "Houravg.findByAvgTime", query = "SELECT h FROM Houravg h WHERE h.avgTime = :avgTime")})
public class Houravg implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HouravgPK houravgPK;
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
    @Column(name = "rate")
    private Double rate;
    @Column(name = "valid")
    private Double valid;
    @Column(name = "dataType")
    private Short dataType;
    @Basic(optional = false)
    @Column(name = "avg_time")
    @Temporal(TemporalType.DATE)
    private Date avgTime;

    public Houravg() {
    }

    public Houravg(HouravgPK houravgPK) {
        this.houravgPK = houravgPK;
    }

    public Houravg(HouravgPK houravgPK, int companyId, int regionalId, int unitsId, String deviceId, Date avgTime) {
        this.houravgPK = houravgPK;
        this.companyId = companyId;
        this.regionalId = regionalId;
        this.unitsId = unitsId;
        this.deviceId = deviceId;
        this.avgTime = avgTime;
    }

    public Houravg(long id, Date createTime) {
        this.houravgPK = new HouravgPK(id, createTime);
    }

    public HouravgPK getHouravgPK() {
        return houravgPK;
    }

    public void setHouravgPK(HouravgPK houravgPK) {
        this.houravgPK = houravgPK;
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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getValid() {
        return valid;
    }

    public void setValid(Double valid) {
        this.valid = valid;
    }

    public Short getDataType() {
        return dataType;
    }

    public void setDataType(Short dataType) {
        this.dataType = dataType;
    }

    public Date getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Date avgTime) {
        this.avgTime = avgTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (houravgPK != null ? houravgPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Houravg)) {
            return false;
        }
        Houravg other = (Houravg) object;
        if ((this.houravgPK == null && other.houravgPK != null) || (this.houravgPK != null && !this.houravgPK.equals(other.houravgPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Houravg[ houravgPK=" + houravgPK + " ]";
    }
    
}
