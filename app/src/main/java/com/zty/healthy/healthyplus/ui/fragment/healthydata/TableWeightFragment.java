package com.zty.healthy.healthyplus.ui.fragment.healthydata;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.WeightDataAdapter;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.model.WeightModel;
import com.zty.healthy.healthyplus.recycler.FooterRefreshAdapter;
import com.zty.healthy.healthyplus.service.HealthyDataService;
import com.zty.healthy.healthyplus.service.RequestManager;
import com.zty.healthy.healthyplus.service.Urls;
import com.zty.healthy.healthyplus.utils.DataUtils;
import com.zty.healthy.healthyplus.utils.DialogUtils;
import com.zty.healthy.healthyplus.utils.HealthyDataTitleUtils;
import com.zty.healthy.healthyplus.utils.ResultUtil;
import com.zty.healthy.healthyplus.utils.SharedPrefUtils;
import com.zty.healthy.healthyplus.utils.ToastUtils;
import com.zty.healthy.healthyplus.widget.MyDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2016/10/20.
 */

public class TableWeightFragment extends BaseFragment implements View.OnClickListener, DialogUtils.SaveWeight {

    private Button btnManual;
    private RecyclerView recyclerTableData;

    private FrameLayout layoutDataTitle;

    private int mTempPageCount = 2;
    private int mLastVisibleItemPosition;

    private int PAGE_COUNT = 1;

    private boolean isLoadMore;

    private WeightDataAdapter weightDataAdapter;

    @Override
    public int getContentVew() {
        return R.layout.view_table_data;
    }

    @Override
    public void initView(View view) {

        btnManual = (Button) view.findViewById(R.id.btnManual);
        btnManual.setOnClickListener(this);

        recyclerTableData = (RecyclerView) view.findViewById(R.id.recyclerTableData);

        layoutDataTitle = (FrameLayout) view.findViewById(R.id.layoutDataTitle);

    }

    @Override
    public void initData() {

        HealthyDataTitleUtils.setWeightTitle(context, layoutDataTitle, true);

        HealthyDataService.getData(context, Urls.getMonitorWeightHistory, 2, PAGE_COUNT, this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerTableData.setLayoutManager(layoutManager);

        recyclerTableData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    //防止重复请求
                    if (PAGE_COUNT == mTempPageCount) {
                        return;
                    }

                    if (mLastVisibleItemPosition > 0 && mLastVisibleItemPosition + 1 == weightDataAdapter.getItemCount()) {
                        //已到达底部，开始加载更多
                        isLoadMore = true;
                        weightDataAdapter.updateRefreshState(FooterRefreshAdapter.STATE_START);
                        PAGE_COUNT = mTempPageCount;
                        HealthyDataService.getData(context, Urls.getMonitorWeightHistory, 2, PAGE_COUNT, TableWeightFragment.this);
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });

        weightDataAdapter = new WeightDataAdapter(context, new ArrayList<WeightModel>());

        recyclerTableData.setAdapter(weightDataAdapter);

    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

        ResultBean resultBean = ResultUtil.getResult(response, false);

        if (resultBean.isSuccess()) {

            switch (requestCode) {
                case 1:
                    ToastUtils.show(context, "保存成功");
                    break;
                case 2:
                    if (PAGE_COUNT >= 2) {
                        mTempPageCount++;
                    }

                    List<WeightModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<WeightModel>>() {
                    }.getType());

                    weightDataAdapter.notifyBottomRefresh(models);

                    recyclerTableData.addItemDecoration(new MyDecoration(context, MyDecoration.VERTICAL_LIST));

                    break;
            }

        } else {
            ToastUtils.show(context, "数据提交失败！");
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnManual) {
            DialogUtils.weightDialog(context, this);
        }
    }

    @Override
    public void saveListener(String weight) {
        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);
        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String familyUserId = SharedPrefUtils.getString(context, SharedPrefUtils.CURRENT_OLD_MAN);
        String height = SharedPrefUtils.getString(context, SharedPrefUtils.CURRENT_OLD_MAN_HEIGHT);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(familyUserId)) {
            RequestParams params = new RequestParams();
            params.put("takenId", takenId);
            params.put("userId", userId);
            params.put("familyUserId", familyUserId);
            params.put("weight", weight);
            params.put("bmi", DataUtils.getBmi(Double.parseDouble(weight), Double.parseDouble(height)));
            params.put("water", "0");
            params.put("muscle", "0");
            params.put("bone", "0");
            params.put("fat", "0");
            params.put("cal", "0");
            params.put("height", height);

            RequestManager.post(1, Urls.submitMonitorWeight, params, this);
        } else if (TextUtils.isEmpty(userId)) {
            ToastUtils.show(context, "请登录后重试");
        } else {
            ToastUtils.show(context, "请选择家庭成员");
        }
    }

}
