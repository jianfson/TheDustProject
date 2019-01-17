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
public class RelationData {
    private String region;
    private Double relation;
    
    public RelationData() {
    }

    public RelationData(String region, Long count) {
        this.region = region;
        this.relation = count / 771.0;
    }
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    public Double getRelation() {
        return relation;
    }

    public void setRelation(Double relation) {
        this.relation = relation;
    }
    
}
