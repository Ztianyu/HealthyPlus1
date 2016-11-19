package com.zty.healthy.healthyplus.ui.activity.service;

import android.view.View;
import android.widget.RadioGroup;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.ui.fragment.server.DoctorPersonFragment;
import com.zty.healthy.healthyplus.ui.fragment.server.DoctorTeamFragment;

/**
 * Created by zty on 2016/10/10.
 */

public class DoctorActivity extends BaseActivity implements View.OnClickListener {

    private RadioGroup radioGroupDoctor;

    private DoctorPersonFragment doctorPersonFragment;
    private DoctorTeamFragment doctorTeamFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_doctor;
    }

    @Override
    protected void initView() {
        title.setText("医生服务");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setText("");

        radioGroupDoctor = (RadioGroup) findViewById(R.id.radioGroupDoctor);
        radioGroupDoctor.setOnCheckedChangeListener(checkedChangeListener);

        if (doctorPersonFragment == null) {
            doctorPersonFragment = new DoctorPersonFragment();
        }
        changeFragment(doctorPersonFragment);

    }

    @Override
    protected void initData() {

    }

    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioBtnDoctor1:
                    if (doctorPersonFragment == null) {
                        doctorPersonFragment = new DoctorPersonFragment();
                    }
                    changeFragment(doctorPersonFragment);
                    break;
                case R.id.radioBtnDoctor2:
                    if (doctorTeamFragment == null) {
                        doctorTeamFragment = new DoctorTeamFragment();
                    }
                    changeFragment(doctorTeamFragment);
                    break;
            }
        }
    };

    private void changeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentDoctor, fragment).commit();
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
            case R.id.titleLeft:
                finish();
                break;
        }
    }
}
