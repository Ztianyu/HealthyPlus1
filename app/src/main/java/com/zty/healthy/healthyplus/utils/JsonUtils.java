package com.zty.healthy.healthyplus.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static String createJsonString(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }

    public static <T> T changeJsonToBean(String json, Class<T> cls) {
        Gson gson = new Gson();
        T t = gson.fromJson(json, cls);
        return t;
    }

    public static <T> List<T> changeJsonToList(String json, Class<T> cls) {
        Gson gson = new Gson();
        List<T> list = gson.fromJson(json, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    public static <T> List<Map<String, T>> changeJsonToListMaps(
            String json) {
        List<Map<String, T>> list = null;
        Gson gson = new Gson();
        list = gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {
        }.getType());
        return list;
    }

    public static <T> Map<String, T> changeJsonToMaps(String json) {
        Map<String, T> map = null;
        Gson gson = new Gson();
        map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
        }.getType());
        return map;
    }

}
