package com.zty.healthy.healthyplus.model;

import java.util.List;

/**
 * Created by zty on 2016/10/18.
 */

public class OldManDevices {

    private String own;
    private String familyUserId;
    private String pressure;
    private String sugar;
    private String warning;
    private String location;

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getPressure() {
        return pressure;
    }

    public String getFamilyUserId() {
        return familyUserId;
    }

    public void setFamilyUserId(String familyUserId) {
        this.familyUserId = familyUserId;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
