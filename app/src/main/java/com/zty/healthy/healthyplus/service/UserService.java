package com.zty.healthy.healthyplus.service;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.inter.RequestCallback;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;

/**
 * 账户服务层
 * Created by zty on 2016/8/31.
 */
public class UserService extends BaseService {

    /**
     * 用户登录
     */
    public static void login(String phone, String passWord, RequestCallback callback) {
        RequestParams params = new RequestParams();
        params.put("loginName", phone);
        params.put("password", passWord);
        params.put("appMark", appMark);

        RequestManager.post(-1, Urls.Login, params, callback);
    }

    /**
     * 获取老人列表
     */
    public static void getOldMen(Context context, RequestCallback callback) {

        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String familyNumber = SharedPrefUtils.getString(context, SharedPrefUtils.FAMILY_NUM);
        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(familyNumber) && !TextUtils.isEmpty(takenId)) {
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            params.put("number", familyNumber);
            params.put("tokenId", takenId);
            RequestManager.get(-1, Urls.getOldMen, params, callback);
        }
    }

    /**
     * 设置新密码
     */
    public static void setPassWord(String phone, String passWord, RequestCallback callback) {
        RequestParams params = new RequestParams();
        params.put("loginName", phone);
        params.put("password", passWord);
        params.put("appMark", appMark);
        RequestManager.post(-1, Urls.setPassWord, params, callback);

    }

    /**
     * 用户注册
     */
    public static void register(String phone, String passWord, RequestCallback callback) {
        RequestParams params = new RequestParams();
        params.put("mobile", phone);
        params.put("password", passWord);
        RequestManager.post(-1, Urls.Register, params, callback);

    }

    /**
     * 修改密码
     */
    public static void resetNewPw(Context context, String oldPw, String passWord, RequestCallback callback) {

        String phone = SharedPrefUtils.getString(context, SharedPrefUtils.USER_PHONE);

        if (!TextUtils.isEmpty(phone)) {
            RequestParams params = new RequestParams();
            params.put("loginName", phone);
            params.put("oldPassword", oldPw);
            params.put("newPassword", passWord);
            params.put("appMark", appMark);
            RequestManager.post(-1, Urls.resetPassWord, params, callback);
        }


    }
}
