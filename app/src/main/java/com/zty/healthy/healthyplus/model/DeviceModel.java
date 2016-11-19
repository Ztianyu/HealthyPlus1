package com.zty.healthy.healthyplus.model;

/**
 * Created by zty on 2016/10/17.
 */

public class DeviceModel {

//            “id”:”主键id”
//            “devId”:”设备号”，
//            “devType”:”设备类型（1-血压仪2-血糖仪3-跌倒报警4-电子定位）”

    private String id;
    private String devId;
    private String devType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }
}
