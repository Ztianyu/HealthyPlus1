package com.zty.healthy.healthyplus.ui.fragment.server;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.PersonDoctorAdapter;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.model.DoctorModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人医生列表
 * Created by zty on 2016/10/11.
 */

public class DoctorPersonFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {

    private TextView textIllness;
    private Spinner spinnerDoctor1, spinnerDoctor2, spinnerDoctor3;
    private ListView listDoctor;

    private List<DoctorModel> mData = new ArrayList<>();

    private PersonDoctorAdapter adapter;

    @Override
    public int getContentVew() {
        return R.layout.fragment_list_doctor;
    }

    @Override
    public void initView(View view) {
        textIllness = (TextView) view.findViewById(R.id.textIllness);
        spinnerDoctor1 = (Spinner) view.findViewById(R.id.spinnerDoctor1);
        spinnerDoctor2 = (Spinner) view.findViewById(R.id.spinnerDoctor2);
        spinnerDoctor3 = (Spinner) view.findViewById(R.id.spinnerDoctor3);
        listDoctor = (ListView) view.findViewById(R.id.listDoctor);
        spinnerDoctor1.setOnItemSelectedListener(this);
        spinnerDoctor2.setOnItemSelectedListener(this);
        spinnerDoctor3.setOnItemSelectedListener(this);
    }

    @Override
    public void initData() {
        mData.clear();
        for (int i = 0; i < 10; i++) {
            mData.add(new DoctorModel());
        }

        adapter = new PersonDoctorAdapter(context, mData);
        listDoctor.setAdapter(adapter);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerDoctor1:
                break;
            case R.id.spinnerDoctor2:
                break;
            case R.id.spinnerDoctor3:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
