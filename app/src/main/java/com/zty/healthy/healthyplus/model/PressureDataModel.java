package com.zty.healthy.healthyplus.model;

/**
 * Created by zty on 2016/10/20.
 */

public class PressureDataModel {

//            “hbp”: 舒张压，
//            “lbp”：收缩压，
//            “heart”：心率，
//            “gatherDateStr”：”采集日期”
//            “gatherTime”：”采集时间”，
//            “hbpLevel”: “舒张压风险级别(0:正常，1:偏高，2:偏低)”，
//            “lbpLevel”：”收缩压风险级别(0:正常，1:偏高，2:偏低)”，
//            “heartLevel”：”心率风险级别(0:正常，1:偏高，2:偏低)”

    private String hbp;
    private String lbp;
    private String heart;
    private String gatherDateStr;
    private String gatherTime;
    private int hbpLevel;
    private int lbpLevel;
    private int heartLevel;

    public String getHbp() {
        return hbp;
    }

    public void setHbp(String hbp) {
        this.hbp = hbp;
    }

    public String getLbp() {
        return lbp;
    }

    public void setLbp(String lbp) {
        this.lbp = lbp;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getGatherDateStr() {
        return gatherDateStr;
    }

    public void setGatherDateStr(String gatherDateStr) {
        this.gatherDateStr = gatherDateStr;
    }

    public String getGatherTime() {
        return gatherTime;
    }

    public void setGatherTime(String gatherTime) {
        this.gatherTime = gatherTime;
    }

    public int getHbpLevel() {
        return hbpLevel;
    }

    public void setHbpLevel(int hbpLevel) {
        this.hbpLevel = hbpLevel;
    }

    public int getLbpLevel() {
        return lbpLevel;
    }

    public void setLbpLevel(int lbpLevel) {
        this.lbpLevel = lbpLevel;
    }

    public int getHeartLevel() {
        return heartLevel;
    }

    public void setHeartLevel(int heartLevel) {
        this.heartLevel = heartLevel;
    }
}
