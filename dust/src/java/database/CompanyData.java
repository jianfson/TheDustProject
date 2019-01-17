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
public class CompanyData {
    private String name;
    private String address;
    private String start;
    private String stop;
    
    public CompanyData() {
    }

    public CompanyData(String name, String address, String start, String stop) {
        this.name = name;
        this.address = address;
        this.start = start;
        this.stop = stop;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
    
    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }
    
}
