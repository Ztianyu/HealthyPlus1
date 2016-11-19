package com.zty.healthy.healthyplus.ui.activity.home;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.AccountAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.ui.fragment.location.LocationDeviceFragment;
import com.zty.healthy.healthyplus.ui.fragment.location.LocationFragment;
import com.zty.healthy.healthyplus.ui.fragment.location.TrajectoryFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 电子定位
 * Created by zty on 2016/8/22.
 */
public class LocationActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private String strTitle = "电子定位";

    private RadioGroup radioGroup;

    private LocationFragment locationFragment;
    private TrajectoryFragment trajectoryFragment;
    private LocationDeviceFragment locationDeviceFragment;

    private DrawerLayout mDrawerLayout;

    private ListView listViewAccount;
    private AccountAdapter adapter;

    private List<OldManModel> mData = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_location;
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

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupLocation);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dlLocation);

        listViewAccount = (ListView) findViewById(R.id.listViewAccountLocation);

        listViewAccount.setOnItemClickListener(this);

        if (locationFragment == null) {
            locationFragment = new LocationFragment();
        }
        changeFragment(locationFragment);

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
                case R.id.radioButtonLocation1:
                    if (locationFragment == null) {
                        locationFragment = new LocationFragment();
                    }
                    changeFragment(locationFragment);
                    break;
                case R.id.radioButtonLocation2:
                    if (trajectoryFragment == null) {
                        trajectoryFragment = new TrajectoryFragment();
                    }
                    changeFragment(trajectoryFragment);
                    break;
                case R.id.radioButtonLocation3:
                    if (locationDeviceFragment == null) {
                        locationDeviceFragment = new LocationDeviceFragment();
                    }
                    changeFragment(locationDeviceFragment);
                    break;
            }
        }
    };

    private void changeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLocation, fragment).commit();
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
        adapter.notifyDataSetChanged();
        title.setText(mData.get(position).getName() + strTitle);
        mDrawerLayout.closeDrawers();

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
