package com.zty.healthy.healthyplus.model;

/**
 * Created by zty on 2016/10/20.
 */

public class WeightModel {

//            “weight”：体重，
//            “bmi”：'BMI'，
//            “water”：水分，
//            “muscle”：肌肉，
//            “bone”：骨头，
//            “fat”：脂肪，
//            “cal”：卡路里,
//            “weightLevel”：”体重风险级别(0:正常，1:偏胖，2:偏瘦, 3:肥胖)”，
//            “gatherDate”：”采集日期”,
//            “gatherTime”：”采集时间”

    private String weight;
    private String weightLevel;
    private String gatherDate;
    private String gatherTime;

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightLevel() {
        return weightLevel;
    }

    public void setWeightLevel(String weightLevel) {
        this.weightLevel = weightLevel;
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
