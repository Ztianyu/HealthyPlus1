package com.zty.healthy.healthyplus.model;

/**
 * Created by zty on 2016/8/27.
 */
public class WarningModel {

//            “id”: “B624064BA065E01CB73F835017FE96FA”,
//            “warningInfo”: “预警说明”,
//            “warningSource” : “预警来源”，
//            “createDate”:”创建时间”
//            “remarks”:”预警内容”

    private String id;
    private String warningInfo;
    private String warningSource;
    private String createDate;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarningInfo() {
        return warningInfo;
    }

    public void setWarningInfo(String warningInfo) {
        this.warningInfo = warningInfo;
    }

    public String getWarningSource() {
        return warningSource;
    }

    public void setWarningSource(String warningSource) {
        this.warningSource = warningSource;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
