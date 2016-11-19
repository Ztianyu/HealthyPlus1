package com.zty.healthy.healthyplus.model;

/**
 * 登录成功后返回数据
 * Created by zty on 2016/10/13.
 */

public class LoginModel {

    private String userId;
    private String number;
    private String tokenId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
