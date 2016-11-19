package com.zty.healthy.healthyplus.ui.fragment.healthydata;

import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.model.ChartDataModel;
import com.zty.healthy.healthyplus.model.HealthLineModel;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.service.HealthyDataService;
import com.zty.healthy.healthyplus.service.Urls;
import com.zty.healthy.healthyplus.utils.JsonUtils;
import com.zty.healthy.healthyplus.utils.ResultUtil;
import com.zty.healthy.healthyplus.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by zty on 2016/10/21.
 */

public class PressureChartFragment extends ChartViewFragment {

    private LineChartView chartHighPressure;
    private LineChartView chartLowPressure;
    private LineChartView chartHeartBeat;

    private int pageNo = 1;

    private int pressureYTop = 300;//血压Y轴 最大值
    private int heartYTop = 300;//心跳最大值

    private List<HealthLineModel> highPressureLines = new ArrayList<>();
    private List<HealthLineModel> lowPressureLines = new ArrayList<>();
    private List<HealthLineModel> heartPressureLines = new ArrayList<>();

    @Override
    public int getContentVew() {
        return R.layout.chart_pressure;
    }

    @Override
    public void initView(View view) {
        chartHighPressure = (LineChartView) view.findViewById(R.id.chartHighPressure);
        chartLowPressure = (LineChartView) view.findViewById(R.id.chartLowPressure);
        chartHeartBeat = (LineChartView) view.findViewById(R.id.chartHeartBeat);
    }

    @Override
    public void initData() {
        HealthyDataService.getData(context, Urls.getMonitorBpTrend, -1, pageNo, this);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

        ResultBean resultBean = ResultUtil.getResult(response, false);

        if (resultBean.isSuccess()) {

            ChartDataModel model = JsonUtils.changeJsonToBean(resultBean.getResult(), ChartDataModel.class);

            if (model.getSeries() != null && model.getSeries().size() > 0) {

                for (int m = 0; m < model.getSeries().size(); m++) {

                    if (model.getSeries().get(m).getData() != null && model.getSeries().get(m).getData().size() > 0) {

                        List<String> values = new ArrayList<>();

                        for (int i = 0; i < model.getSeries().get(m).getData().size(); i++) {
                            values.add(model.getSeries().get(m).getData().get(i).getValue());
                        }

                        HealthLineModel healthLineModel = new HealthLineModel();

                        healthLineModel.setValues(values);

                        if (model.getSeries().get(m).getName().equals("舒张压")) {
                            highPressureLines.add(healthLineModel);
                        } else if (model.getSeries().get(m).getName().equals("收缩压")) {
                            lowPressureLines.add(healthLineModel);
                        } else if (model.getSeries().get(m).getName().equals("心率")) {
                            heartPressureLines.add(healthLineModel);
                        }

                        setLines(chartHighPressure, highPressureLines, false, false, "", "", pressureYTop, values.size());
                        setLines(chartLowPressure, lowPressureLines, false, false, "", "", pressureYTop, values.size());
                        setLines(chartHeartBeat, heartPressureLines, false, false, "", "", heartYTop, values.size());
                    }
                }

            }

        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }

    }
}
