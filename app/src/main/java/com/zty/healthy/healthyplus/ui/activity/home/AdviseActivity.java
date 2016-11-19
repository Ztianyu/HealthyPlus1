package com.zty.healthy.healthyplus.ui.activity.home;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.AccountAdapter;
import com.zty.healthy.healthyplus.adapter.AdviceAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.model.AdviceModel;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.service.HealthyService;
import com.zty.healthy.healthyplus.utils.LayoutManager;
import com.zty.healthy.healthyplus.utils.ResultUtil;
import com.zty.healthy.healthyplus.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康方案
 * Created by zty on 2016/8/22.
 */
public class AdviseActivity extends BaseActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {

    private static int CODE_GET_ADVICE = 1;

    private DrawerLayout mDrawerLayout;

    private ListView listViewAccount;
    private AccountAdapter accountAdapter;

    private Spinner spinner;
    private TextView count;
    private RecyclerView listView;

    private ArrayAdapter<String> spinnerAdapter;
    private AdviceAdapter adapter;

    private List<String> doctors = new ArrayList<>();
    private List<AdviceModel> adviceModels = new ArrayList<>();

    private List<OldManModel> mData = new ArrayList<>();

    private String strTitle = "的健康方案";

    private String familyUserId;
    private String doctorId;


    @Override
    protected int getContentView() {
        return R.layout.activity_advice;
    }

    @Override
    protected void initView() {

        title.setText("爸爸" + strTitle);
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setText("");
        right.setBackgroundResource(R.mipmap.ic_login);
        right.setOnClickListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.dlHealthyAdvice);

        listViewAccount = (ListView) findViewById(R.id.listViewAdvice);

        listViewAccount.setOnItemClickListener(this);

        spinner = (Spinner) findViewById(R.id.spinnerAdvice);
        spinner.setOnItemSelectedListener(this);
        count = (TextView) findViewById(R.id.textAdviceCount);

        listView = (RecyclerView) findViewById(R.id.listViewAdviceContent);

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

        accountAdapter = new AccountAdapter(this, mData);
        listViewAccount.setAdapter(accountAdapter);

        doctors.clear();
        doctors.add("徐子川-主治医师（冠心病）");
        doctors.add("王东强-主治医师（心脑科）");
        doctors.add("张潇-医师（糖尿病）");
        doctors.add("王伟-主治医师（心血管疾病）");
        doctors.add("张德文-主治医师（外科）");

        spinnerAdapter = new ArrayAdapter(this, R.layout.item_spinner, R.id.textSpinner, doctors);
        spinner.setAdapter(spinnerAdapter);

        adviceModels.clear();

        HealthyService.getHealthScheme(this, CODE_GET_ADVICE, familyUserId, doctorId, this);

        for (int i = 0; i < 10; i++) {
            adviceModels.add(new AdviceModel());
        }
        adapter = new AdviceAdapter(this, adviceModels);

        LayoutManager.setVertical(this, listView);
        listView.setAdapter(adapter);

        count.setText("24");

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response, false);

        if (resultBean.isSuccess()) {

            List<AdviceModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<AdviceModel>>() {
            }.getType());

            adviceModels.clear();
            count.setText("");

            if (models != null && models.size() > 0) {
                adviceModels.addAll(models);

                adapter = new AdviceAdapter(this, adviceModels);

                SchemeDetailsActivity.familyMemberName = mData.get(0).getName();

                LayoutManager.setVertical(this, listView);
                listView.setAdapter(adapter);

                count.setText("" + adviceModels.size());
            }
        } else {
            ToastUtils.show(this, resultBean.getMsg());
        }

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

            SchemeDetailsActivity.familyMemberName = mData.get(position).getName();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
