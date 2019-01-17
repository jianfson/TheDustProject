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
public class FactorData {
    private String region;
    private Double effect;
    private Integer worksite;
    
    public FactorData() {
    }

    public FactorData(String region, Double effect, Integer worksite) {
        this.region = region;
        this.effect = effect;
        this.worksite = worksite;
    }
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    public Double getEffect() {
        return effect;
    }

    public void setEffect(Double effect) {
        this.effect = effect;
    }
    
    public Integer getWorksite() {
        return worksite;
    }

    public void setWorksite(Integer worksite) {
        this.worksite = worksite;
    }
}
