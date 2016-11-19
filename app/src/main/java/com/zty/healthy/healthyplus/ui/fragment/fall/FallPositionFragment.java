package com.zty.healthy.healthyplus.ui.fragment.fall;

import android.view.View;

import com.baidu.mapapi.map.MapView;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseFragment;

/**
 * 跌倒位置
 * Created by zty on 2016/9/1.
 */
public class FallPositionFragment extends BaseFragment {

    private MapView mMapView;

    @Override
    public int getContentVew() {
        return R.layout.view_map;
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
