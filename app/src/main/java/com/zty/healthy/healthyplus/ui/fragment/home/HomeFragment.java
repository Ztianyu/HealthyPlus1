package com.zty.healthy.healthyplus.ui.fragment.home;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.AccountIconAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.manager.OldManAccountManger;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.service.UserService;
import com.zty.healthy.healthyplus.ui.activity.home.AdviseActivity;
import com.zty.healthy.healthyplus.ui.activity.home.AssessmentActivity;
import com.zty.healthy.healthyplus.ui.activity.home.FallWarningActivity;
import com.zty.healthy.healthyplus.ui.activity.home.HealthyDataActivity;
import com.zty.healthy.healthyplus.ui.activity.home.LocationActivity;
import com.zty.healthy.healthyplus.ui.activity.home.WarningActivity;
import com.zty.healthy.healthyplus.ui.activity.user.LoginActivity;
import com.zty.healthy.healthyplus.utils.ResultUtil;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;
import com.zty.healthy.healthyplus.utils.ToastUtils;
import com.zty.healthy.healthyplus.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2016/8/16.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private MyViewPager viewPager;
    private TextView btnFun1, btnFun2, btnFun3, btnFun4, btnFun5, btnFun6;
    private ImageView btnLast, btnNext;
    private TextView textAccountName;

    private String strTitle = "健康1+1";

    private List<OldManModel> listModels = new ArrayList<>();

    private int currentPage = 0;

    @Override
    public int getContentVew() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

        ((BaseActivity) context).left.setVisibility(View.INVISIBLE);
        ((BaseActivity) context).right.setVisibility(View.VISIBLE);
        ((BaseActivity) context).title.setText(strTitle);
        ((BaseActivity) context).right.setBackgroundResource(R.mipmap.ic_login);
        ((BaseActivity) context).right.setOnClickListener(this);
        ((BaseActivity) context).right.setText("");

        viewPager = (MyViewPager) view.findViewById(R.id.viewPagerAccount);
        viewPager.addOnPageChangeListener(this);
        btnLast = (ImageView) view.findViewById(R.id.btnLast);
        btnNext = (ImageView) view.findViewById(R.id.btnNext);
        btnLast.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        textAccountName = (TextView) view.findViewById(R.id.textAccountName);

        btnFun1 = (TextView) view.findViewById(R.id.btnHomeFun1);
        btnFun2 = (TextView) view.findViewById(R.id.btnHomeFun2);
        btnFun3 = (TextView) view.findViewById(R.id.btnHomeFun3);
        btnFun4 = (TextView) view.findViewById(R.id.btnHomeFun4);
        btnFun5 = (TextView) view.findViewById(R.id.btnHomeFun5);
        btnFun6 = (TextView) view.findViewById(R.id.btnHomeFun6);
        btnFun1.setOnClickListener(this);
        btnFun2.setOnClickListener(this);
        btnFun3.setOnClickListener(this);
        btnFun4.setOnClickListener(this);
        btnFun5.setOnClickListener(this);
        btnFun6.setOnClickListener(this);
    }

    @Override
    public void initData() {
        UserService.getOldMen(context, this);

        if (listModels.size() <= 0) {
            setOldMan(listModels);
        }
    }

    private void setOldMan(List<OldManModel> models) {

        listModels.clear();

        if (models != null && models.size() > 0) {
            String currentOldMan = SharedPrefUtils.getString(context, SharedPrefUtils.CURRENT_OLD_MAN);

            if (TextUtils.isEmpty(currentOldMan)) {
                SharedPrefUtils.setString(context, SharedPrefUtils.CURRENT_OLD_MAN, models.get(0).getId());
                SharedPrefUtils.setString(context, SharedPrefUtils.CURRENT_OLD_MAN_HEIGHT, models.get(0).getHeight());
            }

            for (int i = 0; i < models.size(); i++) {

                listModels.add(models.get(i));

                OldManAccountManger.getInstance().setCurrentAccount(models.get(i));

                if (!TextUtils.isEmpty(currentOldMan) && models.get(i).getId().equals(currentOldMan))
                    currentPage = i;
            }
        }

        models.add(new OldManModel());

        viewPager.setAdapter(new AccountIconAdapter(context, models));
        viewPager.setCurrentItem(currentPage);
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {
        ResultBean resultBean = ResultUtil.getResult(response, false);

        if (resultBean.isSuccess()) {
            List<OldManModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<OldManModel>>() {
            }.getType());

            setOldMan(models);
        } else {
            ToastUtils.show(context, resultBean.getMsg());
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.titleRight:
                startActivity(new Intent(context, LoginActivity.class));
                break;
            case R.id.btnHomeFun1:
                startActivity(new Intent(context, AssessmentActivity.class));
                break;
            case R.id.btnHomeFun2:
                startActivity(new Intent(context, HealthyDataActivity.class));
                break;
            case R.id.btnHomeFun3:
                startActivity(new Intent(context, AdviseActivity.class));
                break;
            case R.id.btnHomeFun4:
                startActivity(new Intent(context, WarningActivity.class));
                break;
            case R.id.btnHomeFun5:
                startActivity(new Intent(context, LocationActivity.class));
                break;
            case R.id.btnHomeFun6:
                startActivity(new Intent(context, FallWarningActivity.class));
                break;
            case R.id.btnLast:
                if (currentPage > 0) {
                    currentPage--;
                    viewPager.setCurrentItem(currentPage);
                    SharedPrefUtils.updateString(context, SharedPrefUtils.CURRENT_OLD_MAN, listModels.get(currentPage).getAppellation());
                    SharedPrefUtils.updateString(context, SharedPrefUtils.CURRENT_OLD_MAN_HEIGHT, listModels.get(currentPage).getHeight());
                }
                break;
            case R.id.btnNext:
                if (currentPage <= listModels.size()) {
                    currentPage++;
                    viewPager.setCurrentItem(currentPage);
                    SharedPrefUtils.updateString(context, SharedPrefUtils.CURRENT_OLD_MAN, listModels.get(currentPage).getAppellation());
                    SharedPrefUtils.updateString(context, SharedPrefUtils.CURRENT_OLD_MAN_HEIGHT, listModels.get(currentPage).getHeight());
                }
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (!TextUtils.isEmpty(listModels.get(position).getName())) {
            textAccountName.setText(listModels.get(position).getName());
        } else {
            textAccountName.setText("");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
