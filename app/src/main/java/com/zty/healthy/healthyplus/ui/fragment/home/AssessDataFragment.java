package com.zty.healthy.healthyplus.ui.fragment.home;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.ui.activity.home.AssessmentActivity;
import com.zty.healthy.healthyplus.widget.WholeListView;

/**
 * 评估报告
 * Created by zty on 2016/8/24.
 */
public class AssessDataFragment extends BaseFragment implements View.OnClickListener {
    private TextView textReportTitle;

    private WholeListView listReport, listAdvise;
    private TextView textServiceNote;
    private Button btnRecommend, btnChoice;

    @Override
    public int getContentVew() {
        return R.layout.fragment_assessment_report;
    }

    @Override
    public void initView(View view) {
        textReportTitle = (TextView) view.findViewById(R.id.textReportTitle);
        listReport = (WholeListView) view.findViewById(R.id.listReport);
        listAdvise = (WholeListView) view.findViewById(R.id.listAdvise);
        textServiceNote = (TextView) view.findViewById(R.id.textServiceNote);
        btnRecommend = (Button) view.findViewById(R.id.btnRecommend);
        btnRecommend.setOnClickListener(this);
        btnChoice = (Button) view.findViewById(R.id.btnChoice);
        btnChoice.setOnClickListener(this);

    }

    @Override
    public void initData() {
        ((BaseActivity) context).title.setText("评估报告");
        textReportTitle.setText(((AssessmentActivity) context).strCurrentName);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRecommend:
                break;
            case R.id.btnChoice:
                break;
        }

    }
}
