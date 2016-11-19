package com.zty.healthy.healthyplus.ui.fragment.server;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.ui.activity.service.DoctorActivity;

/**
 * Created by zty on 2016/10/10.
 */

public class DiseaseFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button btnChoiceDisease;

    private int currentPage = 0;

    public DiseaseFragment(int page) {
        this.currentPage = page;
    }

    @Override
    public int getContentVew() {
        return R.layout.view_service_disease;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        btnChoiceDisease = (Button) view.findViewById(R.id.btnChoiceDisease);
        btnChoiceDisease.setOnClickListener(this);
    }

    @Override
    public void initData() {

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
            case R.id.btnChoiceDisease:
                startActivity(new Intent(context, DoctorActivity.class));
                break;
        }

    }
}
