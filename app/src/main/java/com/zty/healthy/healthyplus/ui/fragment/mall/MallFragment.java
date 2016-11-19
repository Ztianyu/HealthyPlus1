package com.zty.healthy.healthyplus.ui.fragment.mall;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.ViewPagerAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.ui.activity.mall.OrderActivity;
import com.zty.healthy.healthyplus.ui.activity.mall.ShoppingCartActivity;
import com.zty.healthy.healthyplus.ui.fragment.healthydata.TableDataFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2016/8/16.
 */
public class MallFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tabMall;
    private ViewPager viewPagerMall;

    private List<String> titles = new ArrayList<>();

    private ViewPagerAdapter adapter;

    @Override
    public int getContentVew() {
        return R.layout.fragment_mall;
    }

    @Override
    public void initView(View view) {
        ((BaseActivity) context).left.setVisibility(View.VISIBLE);
        ((BaseActivity) context).right.setVisibility(View.VISIBLE);
        ((BaseActivity) context).title.setText("商 城");
        ((BaseActivity) context).left.setText("我的订单");
        ((BaseActivity) context).left.setBackgroundResource(R.color.transparent);
        ((BaseActivity) context).left.setOnClickListener(this);
        ((BaseActivity) context).right.setText("");
        ((BaseActivity) context).right.setBackgroundResource(R.mipmap.ic_shopping_cart);
        ((BaseActivity) context).right.setOnClickListener(this);

        tabMall = (TabLayout) view.findViewById(R.id.tabMall);
        viewPagerMall = (ViewPager) view.findViewById(R.id.viewPagerMall);

        tabMall.setTabMode(TabLayout.MODE_FIXED);
        tabMall.setTabTextColors(Color.WHITE, Color.rgb(255, 224, 91));
    }

    @Override
    public void initData() {

        titles.clear();
        titles.add("检测类");
        titles.add("辅助类");
        titles.add("保健类");
        titles.add("营养类");
        titles.add("其他");

        List<GoodsFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new GoodsFragment(i + ""));
        }
        adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitles(titles);
        viewPagerMall.setAdapter(adapter);

        tabMall.setupWithViewPager(viewPagerMall);

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
                context.startActivity(new Intent(context, OrderActivity.class));
                break;
            case R.id.titleRight:
                context.startActivity(new Intent(context, ShoppingCartActivity.class));
                break;
        }

    }
}
