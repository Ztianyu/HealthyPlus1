package com.zty.healthy.healthyplus.ui.activity.home;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.AccountAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.ui.fragment.home.AssessDataFragment;
import com.zty.healthy.healthyplus.ui.fragment.home.AssessmentFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康评估
 * Created by zty on 2016/8/22.
 */
public class AssessmentActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private String strTitle = "健康评估";

    private RadioGroup radioGroup;
    private RadioButton radioButtonData1, radioButtonData2;

    private AssessmentFragment assessmentFragment;
    private AssessDataFragment assessDataFragment;

    private DrawerLayout mDrawerLayout;

    private ListView listViewAccount;
    private AccountAdapter adapter;

    private List<OldManModel> mData = new ArrayList<>();

    public String strCurrentName = "";

    @Override
    protected int getContentView() {
        return R.layout.activity_healthy_data;
    }

    @Override
    protected void initView() {

        title.setText(strTitle);
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setText("");
        right.setBackgroundResource(R.mipmap.ic_login);
        right.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupData);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);

        radioButtonData1 = (RadioButton) findViewById(R.id.radioButtonData1);
        radioButtonData1.setText("在线评估");

        radioButtonData2 = (RadioButton) findViewById(R.id.radioButtonData2);
        radioButtonData2.setText("评估报告");

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dlHealthyData);

        listViewAccount = (ListView) findViewById(R.id.listViewAccount);

        listViewAccount.setOnItemClickListener(this);

        if (assessmentFragment == null) {
            assessmentFragment = new AssessmentFragment();
        }
        changeFragment(assessmentFragment);

    }

    @Override
    protected void initData() {
        mData.clear();
        OldManModel model = new OldManModel();
        model.setName("爸爸");
        mData.add(model);

        model = new OldManModel();
        model.setName("妈妈");
        mData.add(model);

        model = new OldManModel();
        model.setName("爷爷");
        mData.add(model);

        model = new OldManModel();
        model.setName("奶奶");
        mData.add(model);

        strCurrentName = mData.get(0).getName();

        if (TextUtils.isEmpty(strCurrentName)) {
            strCurrentName = mData.get(0).getName();
        }

        adapter = new AccountAdapter(this, mData);
        listViewAccount.setAdapter(adapter);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.radioButtonData1:
                    if (assessmentFragment == null) {
                        assessmentFragment = new AssessmentFragment();
                    }
                    changeFragment(assessmentFragment);
                    break;
                case R.id.radioButtonData2:
                    if (assessDataFragment == null) {
                        assessDataFragment = new AssessDataFragment();
                    }
                    changeFragment(assessDataFragment);
                    break;
            }
        }
    };

    private void changeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHealthyData, fragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleLeft:
                finish();
                break;
            case R.id.titleRight:
                mDrawerLayout.openDrawer(GravityCompat.END);
                break;
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        strCurrentName = mData.get(position).getName();

        adapter.notifyDataSetChanged();
        mDrawerLayout.closeDrawers();

        if (assessmentFragment != null) {
            assessmentFragment.initData();
        }

        if (assessDataFragment != null) {
            assessDataFragment.initData();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            mDrawerLayout.closeDrawers();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
