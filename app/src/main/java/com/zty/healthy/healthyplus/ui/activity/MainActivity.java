package com.zty.healthy.healthyplus.ui.activity;


import android.widget.RadioGroup;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.ui.fragment.forum.ForumFragment;
import com.zty.healthy.healthyplus.ui.fragment.home.HomeFragment;
import com.zty.healthy.healthyplus.ui.fragment.mall.MallFragment;
import com.zty.healthy.healthyplus.ui.fragment.personal.PersonalFragment;
import com.zty.healthy.healthyplus.ui.fragment.server.ServerFragment;

/**
 * Created by zty on 2016/8/16.
 */
public class MainActivity extends BaseActivity {

    private RadioGroup radioGroup;

    private HomeFragment homeFragment;
    private ServerFragment serverFragment;
    private MallFragment mallFragment;
    private ForumFragment forumFragment;
    private PersonalFragment personalFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
    }

    @Override
    protected void initData() {

        homeFragment = new HomeFragment();
        changeFragment(homeFragment);

    }

    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioButton1:
                    homeFragment = new HomeFragment();
                    changeFragment(homeFragment);
                    break;
                case R.id.radioButton2:
                    serverFragment = new ServerFragment();
                    changeFragment(serverFragment);
                    break;
                case R.id.radioButton3:
                    mallFragment = new MallFragment();
                    changeFragment(mallFragment);
                    break;
                case R.id.radioButton4:
                    forumFragment = new ForumFragment();
                    changeFragment(forumFragment);
                    break;
                case R.id.radioButton5:
                    personalFragment = new PersonalFragment();
                    changeFragment(personalFragment);
                    break;
            }
        }
    };

    private void changeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHome, fragment).commit();
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
