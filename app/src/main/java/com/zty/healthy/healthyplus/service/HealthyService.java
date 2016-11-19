package com.zty.healthy.healthyplus.service;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.inter.RequestCallback;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;

/**
 * 健康服务 服务层
 * Created by zty on 2016/10/14.
 */

public class HealthyService {

    /**
     * 获取健康方案
     */
    public static void getHealthScheme(Context context, int code, String familyUserId,
                                       String doctorId, RequestCallback callback) {

        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);
        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);

        if (!TextUtils.isEmpty(userId)) {
            RequestParams params = new RequestParams();
            params.put("takenId", takenId);
            params.put("userId", userId);
            params.put("familyUserId", familyUserId);
            params.put("doctorId", doctorId);
            RequestManager.get(code, Urls.getHealthScheme, params, callback);
        }
    }

    /**
     * 获取疾病信息
     */
    public static void getFamilyDiseasesOption(Context context, int code, RequestCallback callback) {

        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);
        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);

        if (!TextUtils.isEmpty(userId)) {
            RequestParams params = new RequestParams();
            params.put("takenId", takenId);
            params.put("userId", userId);
            RequestManager.get(code, Urls.getFamilyDiseasesOption, params, callback);
        }
    }
}
