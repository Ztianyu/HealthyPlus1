package com.zty.healthy.healthyplus.ui.fragment.healthydata;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.utils.LayoutManager;

/**
 * Created by zty on 2016/8/22.
 */
public class TableDataFragment extends BaseFragment {

    private int page = 0;

    private RecyclerView recyclerView;

    public TableDataFragment(int page) {
        this.page = page;
    }

    @Override
    public int getContentVew() {
        return R.layout.view_table_data;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerTableData);
        LayoutManager.setVertical(context, recyclerView);
    }

    @Override
    public void initData() {


    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
