package com.zty.healthy.healthyplus.ui.activity.home;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.AccountAdapter;
import com.zty.healthy.healthyplus.adapter.WarningAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.model.WarningModel;
import com.zty.healthy.healthyplus.service.WarningService;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康预警
 * Created by zty on 2016/8/22.
 */
public class WarningActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private DrawerLayout mDrawerLayout;

    private ListView listViewAccount;
    private AccountAdapter accountAdapter;

    private ListView listView;

    private WarningAdapter adapter;

    private List<WarningModel> warningModels = new ArrayList<>();

    private List<OldManModel> mData = new ArrayList<>();

    private String strTitle = "预警信息";


    @Override
    protected int getContentView() {
        return R.layout.activity_warning;
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

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dlHealthyWarning);

        listViewAccount = (ListView) findViewById(R.id.listViewWarning);

        listViewAccount.setOnItemClickListener(this);

        listView = (ListView) findViewById(R.id.listViewWarningContent);

    }

    @Override
    protected void initData() {
        if (models != null && models.size() > 0) {
            accountAdapter = new AccountAdapter(this, models);
            listViewAccount.setAdapter(accountAdapter);

            title.setText(currentModel.getName() + "的" + strTitle);

            WarningService.getWarning(this, this);
        }


        warningModels.clear();
        for (int i = 0; i < 10; i++) {
            warningModels.add(new WarningModel());
        }
        adapter = new WarningAdapter(this, warningModels);
        listView.setAdapter(adapter);

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
            case R.id.titleRight:
                mDrawerLayout.openDrawer(GravityCompat.END);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.listViewAdvice) {
            title.setText(mData.get(position).getName() + strTitle);
            mDrawerLayout.closeDrawers();
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
