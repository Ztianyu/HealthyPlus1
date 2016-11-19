package com.zty.healthy.healthyplus.service;

/**
 * 请求地址
 * Created by zty on 2016/9/1.
 */
public class Urls {

    public static String HOST = "http://community.nug-hospital.com:8082/kanglehome";

    //注册
    public static String Register = HOST + "/ws/user/register";
    //找回密码
    public static String setPassWord = HOST + "/ws/user/setNewPwd";
    //设置密码
    public static String resetPassWord = HOST + "/ws/user/updatePwd";
    //登录
    public static String Login = HOST + "/ws/user/sysLogin";
    //获取老人列表
    public static String getOldMen = HOST + "/ws/famliyUser/userList";
    //提交老人档案
    public static String submitFamilyUser = HOST + "/ws/famliyUser/submitFamilyUser";
    //获取老人设备信息
    public static String getDevUser = HOST + "/ws/devUser/getDevUser";
    //绑定老人设备
    public static String submitDev = HOST + "/ws/devUser/submitDev";
    //获取健康方案
    public static String getHealthScheme = HOST + "/ws/healthScheme/list";
    //获取疾病史
    public static String getFamilyDiseasesOption = HOST + "/ws/dict/getFamilyDiseasesOption";
    //提交血压数据
    public static String sumbitMonitorBp = HOST + "/ws/dict/getFamilyDiseasesOption";
    //获取血压数据
    public static String getMonitorBpHistory = HOST + "/ws/dict/getFamilyDiseasesOption";
    //提交体重数据
    public static String submitMonitorWeight = HOST + "/ws/monitorWeight/submitMonitorWeight";
    //获取体重数据
    public static String getMonitorWeightHistory = HOST + "/ws/monitorWeight/getMonitorWeightHistory";
    //获取餐前血糖数据
    public static String getMonitorGiHistory = HOST + "/ws/monitorGi/getMonitorGiHistory";
    //获取餐后血糖数据
    public static String getMonitorFgiHistory = HOST + "/ws/monitorGi/getMonitorFgiHistory";
    //提交血糖数据
    public static String sumbitMonitorGi = HOST + "/ws/monitorGi/sumbitMonitorGiy";
    //获取餐前血糖趋势图
    public static String getMonitorGiTrend = HOST + "/ws/monitorGi/getMonitorGiTrend";
    //获取餐后血糖趋势图
    public static String getMonitorFGiTrend = HOST + "/ws/monitorGi/getMonitorFGiTrend";
    //获取血压趋势图
    public static String getMonitorBpTrend = HOST + "/ws/monitorBp/getMonitorBpTrend";
    //获取体重趋势图
    public static String getMonitorWeightTrend = HOST + "/ws/monitorWeight/getMonitorWeightTrend";
    //获取健康预警
    public static String getArchivesWaring = HOST + "/ws/monitorWeight/getMonitorWeightTrend";
    //获取评估分类
    public static String getAssessClass = HOST + "/ws/assess/getAssessClass";
    //获取评估分类下的评估项目
    public static String getAssessQuest = HOST + "/ws/assess/getAssessQuest";
}
