package com.zty.healthy.healthyplus.model;

import com.zty.healthy.healthyplus.db.sqlite.annotation.Id;
import com.zty.healthy.healthyplus.db.sqlite.annotation.Table;

/**
 * Created by zty on 2016/8/22.
 */

@Table(name = "OldMan")
public class OldManModel {

//            “id”:“ID”,
//            “number”:”家庭编号”,
//            “name”：“姓名”
//            “appellation”：“称谓”
//            “sex”: “性别”,
//            “age”:”年龄”
//            “height”:”身高”,
//            “weight”:”体重”,
//            “birthDate”:”出生日期”,
//            “createDate”:”创建时间”,
//            “updateDate”:”更新时间”


    @Id(column = "index")
    private int index;
    private String id;
    private String icon;
    private String name;
    private String appellation;
    private String sex;
    private String age;
    private String height;
    private String weight;
    private String birthDate;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppellation() {
        return appellation;
    }

    public void setAppellation(String appellation) {
        this.appellation = appellation;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
