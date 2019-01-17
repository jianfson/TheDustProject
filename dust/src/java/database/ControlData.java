/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author jasonley
 */
public class ControlData {
    private String season;
    private Integer red;
    private Integer orange;
    private Integer yellow;
    private Integer max;
    private Integer current;
    private Integer remain;
    private Integer stop;
    
    public ControlData() {
    }

    public ControlData(String season, 
                       Integer red,
                       Integer orange,
                       Integer yellow,
                       Integer max,
                       Integer current,
                       Integer remain,
                       Integer stop) {
        this.season = season;
        this.red = red;
        this.orange = orange;
        this.yellow = yellow;
        this.max = max;
        this.current = current;
        this.remain = remain;
        this.stop = stop;
    }
    
    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }
    
    public Integer getRed() {
        return red;
    }

    public void setRed(Integer red) {
        this.red = red;
    }
    
    public Integer getOrange() {
        return orange;
    }

    public void setOrange(Integer orange) {
        this.orange = orange;
    }
    
    public Integer getYellow() {
        return yellow;
    }

    public void setYellow(Integer yellow) {
        this.yellow = yellow;
    }
    
    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
    
    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }
    
    public Integer getRemain() {
        return remain;
    }

    public void setRemain(Integer remain) {
        this.remain = remain;
    }
    
    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }
    
}
