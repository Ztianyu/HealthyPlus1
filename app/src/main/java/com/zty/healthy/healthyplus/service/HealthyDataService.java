package com.zty.healthy.healthyplus.service;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.inter.RequestCallback;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;

/**
 * 健康数据服务层
 * Created by zty on 2016/9/21.
 */

public class HealthyDataService {

    /**
     * 获取健康数据
     */
    public static void getData(Context context, String url, int code, int pageNo, RequestCallback callback) {

        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);
        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String familyUserId = SharedPrefUtils.getString(context, SharedPrefUtils.CURRENT_OLD_MAN);

        if (!TextUtils.isEmpty(familyUserId)) {
            RequestParams params = new RequestParams();
            params.put("takenId", takenId);
            params.put("userId", userId);
            params.put("familyUserId", familyUserId);
            params.put("statType", 4 + "");
            params.put("pageNo", pageNo + "");

            RequestManager.get(code, url, params, callback);
        }
    }
}
