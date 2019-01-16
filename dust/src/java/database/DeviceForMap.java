/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import method.ReturnPack_of_PollutionLevel;
import method.Pollution_level;
//import method.Pollution_level.Pollution_level_Caculate;
/**
 *
 * @author root
 */
public class DeviceForMap {
    private String deviceId;
    private String deviceName;
    private String deviceAddress;
    private Double lng;
    private Double lat;
    private Double pm10;
    private int IAQI;
    private String PollutionRank;
    private String Color;
    
    //private Pollution_level Pollution;
    
    public DeviceForMap() {
    }

    public DeviceForMap(String deviceId
                    , String deviceName
                    , String deviceAddress
                    , Double lng
                    , Double lat
                    , Double pm10) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
        this.lng = lng;
        this.lat = lat;
        this.pm10 = pm10;
        Pollution_level Pollution = new Pollution_level();
        ReturnPack_of_PollutionLevel RP = Pollution.Pollution_level_Caculate(pm10.intValue());
        this.IAQI = RP.IAQI_pm10;
        this.PollutionRank = RP.PollutionRank;
        this.Color = RP.Color;
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

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
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
    
    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }
    
    public int getIAQI() {
        return IAQI;
    }

    public void setIAQI(int IAQI) {
        this.IAQI = IAQI;
    }
    
    public String getPollutionRank() {
        return PollutionRank;
    }

    public void setPollutionRank(String PollutionRank) {
        this.PollutionRank = PollutionRank;
    }
    
    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }
    
}
