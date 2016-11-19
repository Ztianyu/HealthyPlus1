package com.zty.healthy.healthyplus.ui.fragment.healthydata;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.ViewPagerAdapter;
import com.zty.healthy.healthyplus.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 健康监测数据（趋势图）
 * Created by zty on 2016/8/22.
 */
public class ChartFragment extends BaseFragment {
    private TabLayout tab;
    private ViewPager viewPager;

    private List<String> titles = new ArrayList<>();

    private ViewPagerAdapter adapter;

    @Override
    public int getContentVew() {
        return R.layout.fragment_healthy_data;
    }

    @Override
    public void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.tabHealthyData);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerHealthyData);

        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabTextColors(Color.WHITE, Color.rgb(255, 224, 91));
    }

    @Override
    public void initData() {
        titles.clear();
        titles.add("血压");
        titles.add("血糖");
        titles.add("心电");
        titles.add("体重");

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new PressureChartFragment());
        fragments.add(new SugarChartFragment());
        fragments.add(new PressureChartFragment());
        fragments.add(new WeightChartFragment());

        adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(titles);
        viewPager.setAdapter(adapter);

        tab.setupWithViewPager(viewPager);


    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }
}
