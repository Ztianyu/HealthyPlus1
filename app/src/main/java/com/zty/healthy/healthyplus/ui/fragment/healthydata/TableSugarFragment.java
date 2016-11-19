package com.zty.healthy.healthyplus.ui.fragment.healthydata;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.SugarAdapter;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.model.ResultBean;
import com.zty.healthy.healthyplus.model.SugarDataModel;
import com.zty.healthy.healthyplus.recycler.FooterRefreshAdapter;
import com.zty.healthy.healthyplus.service.HealthyDataService;
import com.zty.healthy.healthyplus.service.RequestManager;
import com.zty.healthy.healthyplus.service.Urls;
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

public class TableSugarFragment extends BaseFragment implements View.OnClickListener, DialogUtils.SaveSugar {
    private Button btnManual;
    private RecyclerView recyclerTableData;

    private FrameLayout layoutDataTitle;

    private int mTempPageCount = 2;
    private int mLastVisibleItemPosition;

    private int PAGE_COUNT = 1;

    private boolean isLoadMore;

    private SugarAdapter sugarAdapter;

    private RadioGroup radioGroupSugar;

    private boolean isChange;//是否是选择了餐前、餐后（是的话刷新数据、否的话添加数据）

    private String getUrl;

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
        radioGroupSugar = (RadioGroup) view.findViewById(R.id.radioGroupSugar);
        radioGroupSugar.setVisibility(View.VISIBLE);
        radioGroupSugar.setOnCheckedChangeListener(checkedChangeListener);

    }

    @Override
    public void initData() {

        HealthyDataTitleUtils.setWeightTitle(context, layoutDataTitle, false);

        getUrl = Urls.getMonitorGiHistory;

        HealthyDataService.getData(context, getUrl, 2, PAGE_COUNT, this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerTableData.setLayoutManager(layoutManager);

        recyclerTableData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                    isChange = false;

                    //防止重复请求
                    if (PAGE_COUNT == mTempPageCount) {
                        return;
                    }

                    if (mLastVisibleItemPosition > 0 && mLastVisibleItemPosition + 1 == sugarAdapter.getItemCount()) {
                        //已到达底部，开始加载更多
                        isLoadMore = true;
                        sugarAdapter.updateRefreshState(FooterRefreshAdapter.STATE_START);
                        PAGE_COUNT = mTempPageCount;
                        HealthyDataService.getData(context, Urls.getMonitorWeightHistory, 2, PAGE_COUNT, TableSugarFragment.this);
                    }
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            }
        });

        sugarAdapter = new SugarAdapter(context, new ArrayList<SugarDataModel>());

        recyclerTableData.setAdapter(sugarAdapter);

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

                    List<SugarDataModel> models = new Gson().fromJson(resultBean.getResult(), new TypeToken<List<SugarDataModel>>() {
                    }.getType());

                    if (isChange) {
                        sugarAdapter.notifyBottomRefresh(models);
                    } else {
                        sugarAdapter.notifyTopRefresh(models);
                    }

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
            DialogUtils.sugarDialog(context, this);
        }
    }

    private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId) {
                case R.id.radioSugarBefore:
                    getUrl = Urls.getMonitorGiHistory;
                    break;
                case R.id.radioSugarAfter:
                    getUrl = Urls.getMonitorFgiHistory;
                    break;
            }
            isChange = true;
            PAGE_COUNT = 1;
            HealthyDataService.getData(context, getUrl, 2, PAGE_COUNT, TableSugarFragment.this);
        }
    };

    @Override
    public void saveListener(boolean isBefore, String sugar) {

        String takenId = SharedPrefUtils.getString(context, SharedPrefUtils.TAKEN_ID);
        String userId = SharedPrefUtils.getString(context, SharedPrefUtils.USER_ID);
        String familyUserId = SharedPrefUtils.getString(context, SharedPrefUtils.CURRENT_OLD_MAN);

        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(familyUserId)) {
            RequestParams params = new RequestParams();
            params.put("takenId", takenId);
            params.put("userId", userId);
            params.put("familyUserId", familyUserId);
            params.put("sgi", "0");
            if (isBefore) {
                params.put("gi", sugar);
                params.put("fgi", "0");
            } else {
                params.put("gi", "0");
                params.put("fgi", sugar);
            }
            RequestManager.post(1, Urls.sumbitMonitorGi, params, this);
        } else if (TextUtils.isEmpty(userId)) {
            ToastUtils.show(context, "请登录后重试");
        } else {
            ToastUtils.show(context, "请选择家庭成员");
        }
    }
}
