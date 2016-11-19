package com.zty.healthy.healthyplus.service;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.inter.RequestCallback;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;

/**
 * Created by zty on 2016/10/17.
 */

public class OldManService {

    /**
     * 获取老人设备信息
     */
    public static void getOldManDevice(Context context, int code, String familyUserId, RequestCallback callback) {

        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(takenId)) {
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            params.put("tokenId", takenId);
            params.put("familyUserId", familyUserId);
            RequestManager.get(code, Urls.getDevUser, params, callback);
        }
    }

    /**
     * 绑定老人设备
     */
    public static void bindingDevice(Context context, int code, String familyUserId,
                                     String sugarDevId, String pressureDevId, String fallDevId, String localizerDevId, RequestCallback callback) {

        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(takenId)) {
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            params.put("tokenId", takenId);
            params.put("familyUserId", familyUserId);
            params.put("sugarDevId", sugarDevId);
            params.put("pressureDevId", pressureDevId);
            params.put("fallDevId", fallDevId);
            params.put("localizerDevId", localizerDevId);
            RequestManager.post(code, Urls.submitDev, params, callback);
        }
    }
}
