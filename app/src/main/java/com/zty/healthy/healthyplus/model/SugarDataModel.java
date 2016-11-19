package com.zty.healthy.healthyplus.model;

/**
 * Created by zty on 2016/10/20.
 */

public class SugarDataModel {

//            “gi”： 餐前血糖，
//            “giLevel”：”餐前血糖风险级别(0:正常，1:偏高，2:偏低)”，
//            “gatherDate”：”采集日期”,
//            “gatherTime”：”采集时间”

//            “fgi”：餐后血糖，
//            “fgiLevel”：”餐后血糖风险级别(0:正常，1:偏高，2:偏低)”，
//            “gatherDate”：”采集日期”,
//            “gatherTime”：”采集时间”

    private String gi;
    private String fgi;
    private String giLevel;
    private String fgiLevel;
    private String gatherDate;
    private String gatherTime;

    public String getGi() {
        return gi;
    }

    public void setGi(String gi) {
        this.gi = gi;
    }

    public String getFgi() {
        return fgi;
    }

    public void setFgi(String fgi) {
        this.fgi = fgi;
    }

    public String getGiLevel() {
        return giLevel;
    }

    public void setGiLevel(String giLevel) {
        this.giLevel = giLevel;
    }

    public String getFgiLevel() {
        return fgiLevel;
    }

    public void setFgiLevel(String fgiLevel) {
        this.fgiLevel = fgiLevel;
    }

    public String getGatherDate() {
        return gatherDate;
    }

    public void setGatherDate(String gatherDate) {
        this.gatherDate = gatherDate;
    }

    public String getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(String gatherTime) {
        this.gatherTime = gatherTime;
    }
}
