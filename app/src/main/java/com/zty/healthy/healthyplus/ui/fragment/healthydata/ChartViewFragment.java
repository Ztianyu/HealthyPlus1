package com.zty.healthy.healthyplus.ui.fragment.healthydata;


import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.model.HealthLineModel;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by zty on 2016/8/22.
 */
public abstract class ChartViewFragment extends BaseFragment {

    private int numberOfLines = 1;
    private int maxNumberOfLines = 3;

    float[][] randomNumbersTab;

    private boolean hasAxes = true;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = false;
    private boolean hasLabels = true;
    private boolean isCubic = false;
    private boolean hasLabelForSelected = false;

    private List<AxisValue> axisValues = new ArrayList<>();// x轴刻度值

    private List<String> xAxis = new ArrayList<>();


    private int[] colors = {R.color.legendLineColor1, R.color.legendLineColor2, R.color.legendLineColor3};

    private boolean isHasAxeX;
    private boolean isHasAxeY;

    private String xName;
    private String yName;


    public void setLines(LineChartView lineChart, List<HealthLineModel> lines, boolean isHasAxeX,
                         boolean isHasAxeY, String xName, String yName, int yTop, int numberOfPoints) {
        this.isHasAxeX = isHasAxeX;
        this.isHasAxeY = isHasAxeY;
        this.xName = xName;
        this.yName = yName;

        generateValues(lines, numberOfPoints);

        generateData(lineChart, numberOfPoints);

        lineChart.setViewportCalculationEnabled(false);

        resetViewport(lineChart, yTop, numberOfPoints);

    }

    private void resetViewport(LineChartView lineChart, int yTop, int numberOfPoints) {
        // Reset viewport height range to (0,yTop)
        final Viewport v = new Viewport(lineChart.getMaximumViewport());
        v.bottom = 0;
        v.top = yTop;
        v.left = 0;
        v.right = numberOfPoints - 1;
        lineChart.setMaximumViewport(v);
        lineChart.setCurrentViewport(v);
    }

    private void generateValues(List<HealthLineModel> lines, int numberOfPoints) {

        if (lines.size() <= 0)
            return;

        axisValues = new ArrayList<>();

        maxNumberOfLines = lines.size();

        randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

        for (int i = 0; i < maxNumberOfLines; i++) {

            for (int j = 0; j < numberOfPoints; j++) {

                randomNumbersTab[i][j] = Float.parseFloat(lines.get(i)
                        .getValues().get(j));

                axisValues.add(new AxisValue(j).setLabel(xAxis.get(j)));

            }
        }
    }

    private void generateData(LineChartView lineChart, int numberOfPoints) {

        if (numberOfLines <= 0)
            return;

        List<Line> lines = new ArrayList<>();

        for (int i = 0; i < numberOfLines; i++) {

            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < numberOfPoints; j++) {

                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(getResources().getColor(colors[i]));
            line.setShape(shape);
            line.setStrokeWidth(2);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
            line.setPointColor(getResources().getColor(colors[i]));
            line.setPointRadius(5);

            lines.add(line);

        }

        LineChartData data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);

            axisY.setTextSize(15);
            axisY.setTextColor(getResources().getColor(R.color.colorAccent));

            if (isHasAxeX) {
                axisX.setName(xName);
            }
            if (isHasAxeY) {
                axisY.setName(yName);
            }

            axisX.setValues(axisValues);
            axisX.setTextSize(12);
            axisX.setTextColor(getResources().getColor(R.color.colorAccent));

            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);

        lineChart.setLineChartData(data);

    }

}
