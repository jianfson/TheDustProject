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
public class CorrectionData {
    private Double nation;
    private Double normal;
    private Double correct;
    private String avgTime;
    
    //private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");

    public CorrectionData() {
    }

    public CorrectionData(Double nation, Double normal, Double correct, String date) {
        this.nation = nation;
        this.normal = normal;
        this.correct = correct;
        this.avgTime = date;
    }
    
    public Double getNation() {
        return nation;
    }

    public void setNation(Double nation) {
        this.nation = nation;
    }
    
    public Double getNormal() {
        return normal;
    }

    public void setNormal(Double normal) {
        this.normal = normal;
    }
    
    public Double getCorrect() {
        return correct;
    }

    public void setCorrect(Double correct) {
        this.correct = correct;
    }

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }
    
}
