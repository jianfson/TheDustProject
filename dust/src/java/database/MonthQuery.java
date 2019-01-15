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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jiangxin
 */

//@Entity
@XmlRootElement

public class MonthQuery {

    private static final long serialVersionUID = 1L;
    private Double pm10;
    @Temporal(TemporalType.DATE)
    private Date avgTime;
    
    //private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public MonthQuery() {
    }

    public MonthQuery(Double pm, java.sql.Date date) {
        this.pm10 = pm;
        this.avgTime = new java.util.Date(date.getTime());
    }
    
    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Date getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(Date avgTime) {
        this.avgTime = avgTime;
    }
    
}