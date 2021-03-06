/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author jasonley
 */
@Entity
@Table(name = "dayavg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dayavg.findAll", query = "SELECT d FROM Dayavg d")
    , @NamedQuery(name = "Dayavg.findById", query = "SELECT d FROM Dayavg d WHERE d.dayavgPK.id = :id")
    , @NamedQuery(name = "Dayavg.findByCompanyId", query = "SELECT d FROM Dayavg d WHERE d.companyId = :companyId")
    , @NamedQuery(name = "Dayavg.findByRegionalId", query = "SELECT d FROM Dayavg d WHERE d.regionalId = :regionalId")
    , @NamedQuery(name = "Dayavg.findByUnitsId", query = "SELECT d FROM Dayavg d WHERE d.unitsId = :unitsId")
    , @NamedQuery(name = "Dayavg.findByDeviceId", query = "SELECT d FROM Dayavg d WHERE d.deviceId = :deviceId")
    , @NamedQuery(name = "Dayavg.findByPm10", query = "SELECT d FROM Dayavg d WHERE d.pm10 = :pm10")
    , @NamedQuery(name = "Dayavg.findByPm25", query = "SELECT d FROM Dayavg d WHERE d.pm25 = :pm25")
    , @NamedQuery(name = "Dayavg.findByNoise", query = "SELECT d FROM Dayavg d WHERE d.noise = :noise")
    , @NamedQuery(name = "Dayavg.findByTsp", query = "SELECT d FROM Dayavg d WHERE d.tsp = :tsp")
    , @NamedQuery(name = "Dayavg.findByWindspeed", query = "SELECT d FROM Dayavg d WHERE d.windspeed = :windspeed")
    , @NamedQuery(name = "Dayavg.findByWinddirection", query = "SELECT d FROM Dayavg d WHERE d.winddirection = :winddirection")
    , @NamedQuery(name = "Dayavg.findByTemperature", query = "SELECT d FROM Dayavg d WHERE d.temperature = :temperature")
    , @NamedQuery(name = "Dayavg.findByHumidity", query = "SELECT d FROM Dayavg d WHERE d.humidity = :humidity")
    , @NamedQuery(name = "Dayavg.findByAirpressure", query = "SELECT d FROM Dayavg d WHERE d.airpressure = :airpressure")
    , @NamedQuery(name = "Dayavg.findByBoardHunmidity", query = "SELECT d FROM Dayavg d WHERE d.boardHunmidity = :boardHunmidity")
    , @NamedQuery(name = "Dayavg.findByBoardTemperature", query = "SELECT d FROM Dayavg d WHERE d.boardTemperature = :boardTemperature")
    , @NamedQuery(name = "Dayavg.findByRainfall", query = "SELECT d FROM Dayavg d WHERE d.rainfall = :rainfall")
    , @NamedQuery(name = "Dayavg.findByVoltage", query = "SELECT d FROM Dayavg d WHERE d.voltage = :voltage")
    , @NamedQuery(name = "Dayavg.findByRate", query = "SELECT d FROM Dayavg d WHERE d.rate = :rate")
    , @NamedQuery(name = "Dayavg.findByValid", query = "SELECT d FROM Dayavg d WHERE d.valid = :valid")
    , @NamedQuery(name = "Dayavg.findByDataType", query = "SELECT d FROM Dayavg d WHERE d.dataType = :dataType")
    , @NamedQuery(name = "Dayavg.findByCreateTime", query = "SELECT d FROM Dayavg d WHERE d.dayavgPK.createTime = :createTime")
    , @NamedQuery(name = "Dayavg.findByAvgTime", query = "SELECT d FROM Dayavg d WHERE d.avgTime = :avgTime")})

@NamedNativeQueries({
    @NamedNativeQuery(name = "Dayavg.findAllcityForMonth"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y-%m') as date FROM dayavg d\n" +
                        "WHERE d.avg_time >= ?from AND d.avg_time < ?to\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findByRegionalIdForMonth"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y-%m') as date FROM dayavg d\n" +
                        "WHERE (d.avg_time >= ?from AND d.avg_time < ?to)\n" +
                        "AND d.regional_id =?regionalId\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findAllcityForDay"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y-%m-%d') as date FROM dayavg d\n" +
                        "WHERE date_format(d.avg_time,'%Y-%m') = ?from\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findByRegionalIdForDay"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y-%m-%d') as date FROM dayavg d\n" +
                        "WHERE (date_format(d.avg_time,'%Y-%m') = ?from)\n" +
                        "AND d.regional_id = ?regionalId\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findAllcityForSeason"
            , query = "SELECT AVG(d.pm10) as pm, concat(year(d.avg_time),'年',\n" +
                        "CASE QUARTER(d.avg_time)\n" +
                        "  WHEN 1 THEN '春'\n" +
                        "  WHEN 2 THEN '夏' \n" +
                        "  WHEN 3 THEN '秋' \n" +
                        "  ELSE '冬' END)  as date FROM dayavg d\n" +
                        "WHERE CASE QUARTER(d.avg_time)\n" +
                        "  WHEN 1 THEN 'spring'\n" +
                        "  WHEN 2 THEN 'summer' \n" +
                        "  WHEN 3 THEN 'autumn' \n" +
                        "  ELSE 'winter' END = ?season\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findByRegionalIdForSeason"
            , query = "SELECT AVG(d.pm10) as pm, concat(year(d.avg_time),'年',\n" +
                        "CASE QUARTER(d.avg_time)\n" +
                        "  WHEN 1 THEN '春'\n" +
                        "  WHEN 2 THEN '夏' \n" +
                        "  WHEN 3 THEN '秋' \n" +
                        "  ELSE '冬' END)  as date FROM dayavg d\n" +
                        "WHERE CASE QUARTER(d.avg_time)\n" +
                        "  WHEN 1 THEN 'spring'\n" +
                        "  WHEN 2 THEN 'summer' \n" +
                        "  WHEN 3 THEN 'autumn' \n" +
                        "  ELSE 'winter' END = ?season\n" +
                        "AND d.regional_id = ?regionalId\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findAllcityForYear"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y') as date FROM dayavg d\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findByRegionalIdForYear"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y') as date FROM dayavg d\n" +
                        "WHERE d.regional_id = ?regionalId\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findDayavgForAllcity"
            , query = "SELECT AVG(da.pm10) as pm, da.avg_time as date\n" +
                        "FROM dayavg da\n" +
                        "where da.regional_id > 510101 AND da.regional_id <= 510109 AND\n" +
                        "da.avg_time >= ?from\n" +
                        "AND da.avg_time < ?to\n" +
                        "GROUP BY date\n" +
                        "ORDER BY date ASC"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findDayavgForRegion"
            , query = "SELECT AVG(da.pm10) as pm, da.avg_time as date\n" +
                        "FROM dayavg da\n" +
                        "where\n" +
                        "da.regional_id = ?regionalId\n" +
                        "AND da.avg_time >= ?from\n" +
                        "AND da.avg_time < ?to\n" +
                        "GROUP BY date\n" +
                        "ORDER BY date ASC"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findDayByAllcityForFactor"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y-%m-%d') as date\n" +
                        "FROM dayavg d\n" +
                        "WHERE d.regional_id > 510101 AND d.regional_id <= 510109 AND date_format(d.avg_time,'%Y-%m-%d') = ?from\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findDayByRegionalIdForFactor"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y-%m-%d') as date\n" +
                        "FROM dayavg d\n" +
                        "WHERE d.regional_id = ?regionalId\n" +
                        "AND date_format(d.avg_time,'%Y-%m-%d') = ?from\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findMonthByAllcityForFactor"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y-%m') as date\n" +
                        "FROM dayavg d\n" +
                        "WHERE d.regional_id > 510101 AND d.regional_id <= 510109 AND date_format(d.avg_time,'%Y-%m') = ?from\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findMonthByRegionalIdForFactor"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y-%m') as date\n" +
                        "FROM dayavg d\n" +
                        "WHERE d.regional_id = ?regionalId\n" +
                        "AND date_format(d.avg_time,'%Y-%m') = ?from\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findYearByAllcityForFactor"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y') as date\n" +
                        "FROM dayavg d\n" +
                        "WHERE d.regional_id > 510101 AND d.regional_id <= 510109 AND date_format(d.avg_time,'%Y') = ?from\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findYearByRegionalIdForFactor"
            , query = "SELECT AVG(d.pm10) as pm, date_format(d.avg_time,'%Y') as date\n" +
                        "FROM dayavg d\n" +
                        "WHERE d.regional_id = ?regionalId\n" +
                        "AND date_format(d.avg_time,'%Y') = ?from\n" +
                        "GROUP BY date"
            , resultSetMapping = "forMonth")
    , @NamedNativeQuery(name = "Dayavg.findWorksiteByAllcityForFactor"
            , query = "SELECT COUNT(c.id) as site\n" +
                        "FROM company c\n" +
                        "WHERE c.regional_id > 510101\n" +
                        "AND c.regional_id <= 510109\n" +
                        "AND c.jgrq >= ?from\n" +
                        "AND c.kgrq <= ?from"
            , resultSetMapping = "forWorksite")
    , @NamedNativeQuery(name = "Dayavg.findWorksiteByRegionalIdForFactor"
            , query = "SELECT COUNT(c.id) as site\n" +
                        "FROM company c\n" +
                        "WHERE c.regional_id = ?regionalId\n" +
                        "AND c.jgrq >= ?from\n" +
                        "AND c.kgrq <= ?from"
            , resultSetMapping = "forWorksite")
})
@SqlResultSetMappings(
{
    @SqlResultSetMapping(
            name="forMonth",
            entities = {},
            columns = {
                @ColumnResult(name = "pm"),  
                @ColumnResult(name = "date")
            }
    )
    , @SqlResultSetMapping(
            name="forWorksite",
            entities = {},  
            columns = {
                @ColumnResult(name = "site") 
            }  
    )
})

public class Dayavg implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DayavgPK dayavgPK;
    @Basic(optional = true)
    @Column(name = "company_id",nullable=true)
    private int companyId;
    @Basic(optional = true)
    @Column(name = "regional_id")
    private int regionalId;
    @Basic(optional = true)
    @Column(name = "units_id")
    private int unitsId;
    @Basic(optional = true)
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
    private Boolean dataType;
    @Basic(optional = true)
    @Column(name = "avg_time")
    @Temporal(TemporalType.DATE)
    private Date avgTime;
    
    //private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public Dayavg() {
    }

    public Dayavg(Double pm, java.sql.Date date) {
        this.pm10 = pm;
        this.avgTime = new java.util.Date(date.getTime());
    }
    
    public Dayavg(DayavgPK dayavgPK) {
        this.dayavgPK = dayavgPK;
    }

    public Dayavg(DayavgPK dayavgPK, int companyId, int regionalId, int unitsId, String deviceId, Date avgTime) {
        this.dayavgPK = dayavgPK;
        this.companyId = companyId;
        this.regionalId = regionalId;
        this.unitsId = unitsId;
        this.deviceId = deviceId;
        this.avgTime = avgTime;
    }

    public Dayavg(long id, Date createTime) {
        this.dayavgPK = new DayavgPK(id, createTime);
    }

    public DayavgPK getDayavgPK() {
        return dayavgPK;
    }

    public void setDayavgPK(DayavgPK dayavgPK) {
        this.dayavgPK = dayavgPK;
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

    public Boolean getDataType() {
        return dataType;
    }

    public void setDataType(Boolean dataType) {
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
        hash += (dayavgPK != null ? dayavgPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dayavg)) {
            return false;
        }
        Dayavg other = (Dayavg) object;
        if ((this.dayavgPK == null && other.dayavgPK != null) || (this.dayavgPK != null && !this.dayavgPK.equals(other.dayavgPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "database.Dayavg[ dayavgPK=" + dayavgPK + " ]";
    }
    
}
