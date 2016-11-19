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

public class WeightChartFragment extends ChartViewFragment {

    private LineChartView lineChartView;

    private int pageNo = 1;

    private int yTop = 15;//Y轴 最大值

    private List<HealthLineModel> lines = new ArrayList<>();

    @Override
    public int getContentVew() {
        return R.layout.view_chart;
    }

    @Override
    public void initView(View view) {
        lineChartView = (LineChartView) view.findViewById(R.id.lineChartView);
    }

    @Override
    public void initData() {
        HealthyDataService.getData(context, Urls.getMonitorWeightTrend, -1, pageNo, this);

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

                        setLines(lineChartView, lines, false, false, "", "", yTop, values.size());
                    }
                }

            }
        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }
    }
}
