package com.zty.healthy.healthyplus.model;

import java.util.List;

/**
 * 健康数据趋势数据
 * Created by zty on 2016/10/21.
 */

public class ChartDataModel {

    private XAxis xAxis;
    private List<Series> series;

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public class XAxis {

        private List<String> categories;

        public List<String> getCategories() {
            return categories;
        }

        public void setCategories(List<String> categories) {
            this.categories = categories;
        }
    }

    public class Series {

        private String name;
        private NormalRange normalRange;
        private List<Data> data;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public NormalRange getNormalRange() {
            return normalRange;
        }

        public void setNormalRange(NormalRange normalRange) {
            this.normalRange = normalRange;
        }

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }
    }

    public class NormalRange {

        private String normalBeg;
        private String normalEnd;

        public String getNormalBeg() {
            return normalBeg;
        }

        public void setNormalBeg(String normalBeg) {
            this.normalBeg = normalBeg;
        }

        public String getNormalEnd() {
            return normalEnd;
        }

        public void setNormalEnd(String normalEnd) {
            this.normalEnd = normalEnd;
        }
    }

    public class Data {
        private String value;
        private int remark;// remark:(0:正常，1:偏高，2:偏低)

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getRemark() {
            return remark;
        }

        public void setRemark(int remark) {
            this.remark = remark;
        }
    }
}
