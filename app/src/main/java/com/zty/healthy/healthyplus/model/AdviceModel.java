package com.zty.healthy.healthyplus.model;

import java.io.Serializable;

/**
 * 健康建议
 * Created by zty on 2016/8/25.
 */
public class AdviceModel implements Serializable{
//             “id”: “B624064BA065E01CB73F835017FE96FA”,
//            “title”: “标题”,
//            “healthScheme” : “健康方案”，
//            “status”:”状态（1：未读2：已读）”
//            “createDate”:”创建时间”

    private String id;
    private String title;
    private String healthScheme;
    private int status;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHealthScheme() {
        return healthScheme;
    }

    public void setHealthScheme(String healthScheme) {
        this.healthScheme = healthScheme;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
