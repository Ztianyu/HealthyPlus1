package com.zty.healthy.healthyplus.ui.fragment.location;

import android.view.View;

import com.baidu.mapapi.map.MapView;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseFragment;

/**
 * 历史轨迹
 * Created by zty on 2016/9/1.
 */
public class TrajectoryFragment extends BaseFragment {
    private MapView mMapView;

    @Override
    public int getContentVew() {
        return R.layout.view_trajectory;
    }

    @Override
    public void initView(View view) {
        //获取地图控件引用
        mMapView = (MapView) view.findViewById(R.id.mMapView);
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
