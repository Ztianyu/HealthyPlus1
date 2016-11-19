package com.zty.healthy.healthyplus.utils;


import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.zty.healthy.healthyplus.model.ResultBean;


/**
 * 网络数据处理类
 */
public class ResultUtil {


    /**
     * @param json   客户端返回的json数据
     * @param isShow 是否用toast显示msg
     * @return
     */
    public static synchronized ResultBean getResult(String json, boolean isShow) {
        Log.i("ResultUtil", json);
        ResultBean resultBean = new ResultBean();
        if (json == null) {
            return resultBean;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("head")) {
                JSONObject headObject = new JSONObject(jsonObject.getString("head"));
                if (headObject.has("ret")) {
                    resultBean.setCode(headObject.getInt("ret"));
                }
                if (headObject.has("ret") && headObject.getInt("ret") == 0) {
                    resultBean.setSuccess(true);
                    if (headObject.has("msg") && isShow) {
//                        ToastUtil.show(headObject.getString("msg"));
                    }

                }
                if (headObject.has("msg")) {
                    resultBean.setMsg(headObject.getString("msg"));
                }
                if (headObject.has("totalPage")) {
                    resultBean.setTotalPage(headObject.getInt("totalPage"));
                }
            }
            if (jsonObject.has("data")) {
                resultBean.setResult(jsonObject.getString("data"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return resultBean;
    }

}
