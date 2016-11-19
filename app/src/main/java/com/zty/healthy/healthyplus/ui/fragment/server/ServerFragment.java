package com.zty.healthy.healthyplus.ui.fragment.server;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.ViewPagerAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2016/8/16.
 */
public class ServerFragment extends BaseFragment {

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
        ((BaseActivity) context).title.setText("医生预定");
        ((BaseActivity) context).left.setVisibility(View.INVISIBLE);
        ((BaseActivity) context).right.setVisibility(View.INVISIBLE);

        tab = (TabLayout) view.findViewById(R.id.tabHealthyData);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerHealthyData);

        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabTextColors(Color.WHITE, Color.rgb(255, 224, 91));
    }

    @Override
    public void initData() {

        titles.clear();
        titles.add("病状分类");
        titles.add("身体症状");

        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new DiseaseFragment(i));
        }
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
