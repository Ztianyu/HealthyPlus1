package com.zty.healthy.healthyplus.service;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.inter.RequestCallback;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;

/**
 * 预警服务层
 * Created by zty on 2016/8/26.
 */
public class WarningService {

    /**
     * 获取预警信息
     */
    public static void getWarning(Context context, RequestCallback callback) {

        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String familyNumber = SharedPrefUtils.getString(context, SharedPrefUtils.FAMILY_NUM);
        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(familyNumber) && !TextUtils.isEmpty(takenId)) {
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            params.put("number", familyNumber);
            params.put("tokenId", takenId);
            RequestManager.get(-1, Urls.getArchivesWaring, params, callback);
        }
    }

}
