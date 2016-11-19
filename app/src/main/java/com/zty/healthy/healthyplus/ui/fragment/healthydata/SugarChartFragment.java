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
 * Created by zty on 2016/10/20.
 */

public class SugarChartFragment extends ChartViewFragment {

    private LineChartView chartSugarBefore;
    private LineChartView chartSugarAfter;

    private int pageNo = 1;

    private int yTop = 15;//Y轴 最大值

    private List<HealthLineModel> lines = new ArrayList<>();

    @Override
    public int getContentVew() {
        return R.layout.chart_sugar;
    }

    @Override
    public void initView(View view) {
        chartSugarBefore = (LineChartView) view.findViewById(R.id.chartSugarBefore);
        chartSugarAfter = (LineChartView) view.findViewById(R.id.chartSugarAfter);

    }

    @Override
    public void initData() {

        HealthyDataService.getData(context, Urls.getMonitorGiTrend, 1, pageNo, this);

        HealthyDataService.getData(context, Urls.getMonitorFGiTrend, 2, pageNo, this);
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

                        lines.add(healthLineModel);

                        switch (requestCode) {
                            case 1:
                                setLines(chartSugarBefore, lines, false, false, "", "", yTop, values.size());
                                break;
                            case 2:
                                setLines(chartSugarAfter, lines, false, false, "", "", yTop, values.size());
                                break;
                        }
                    }
                }

            }

        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }

    }
}
