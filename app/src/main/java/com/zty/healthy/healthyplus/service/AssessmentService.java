package com.zty.healthy.healthyplus.service;

import android.content.Context;
import android.text.TextUtils;

import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.inter.RequestCallback;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;

/**
 * Created by zty on 2016/10/22.
 */

public class AssessmentService {

    /**
     * 获取评估分类
     */
    public static void getGroupItem(int code, Context context, RequestCallback callback) {

        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(takenId)) {
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            params.put("tokenId", takenId);
            RequestManager.get(code, Urls.getAssessClass, params, callback);
        }
    }

    /**
     * 获取评估分类下的评估项目
     */
    public static void getAssessQuest(int code, Context context, String assessId, RequestCallback callback) {

        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(takenId)) {
            RequestParams params = new RequestParams();
            params.put("userId", userId);
            params.put("tokenId", takenId);
            params.put("assessId", assessId);
            RequestManager.get(code, Urls.getAssessQuest, params, callback);
        }
    }
}
