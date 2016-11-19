package com.zty.healthy.healthyplus.ui.activity.mall;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.ViewPagerAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.ui.fragment.mall.GoodsFragment;
import com.zty.healthy.healthyplus.ui.fragment.mall.OrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 商城订单
 * Created by zty on 2016/10/12.
 */

public class OrderActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tabMall;
    private ViewPager viewPagerMall;

    private List<String> titles = new ArrayList<>();

    private ViewPagerAdapter adapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_mall;
    }

    @Override
    protected void initView() {
        title.setText("我的订单");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setText("");
        right.setBackgroundResource(R.mipmap.ic_shopping_cart);
        right.setOnClickListener(this);

        tabMall = (TabLayout) findViewById(R.id.tabMall);
        viewPagerMall = (ViewPager) findViewById(R.id.viewPagerMall);

        tabMall.setTabMode(TabLayout.MODE_FIXED);
        tabMall.setTabTextColors(Color.WHITE, Color.rgb(255, 224, 91));

    }

    @Override
    protected void initData() {

        titles.clear();
        titles.add("全部");
        titles.add("代付款");
        titles.add("已完成");
        titles.add("已取消");

        List<OrderFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new OrderFragment(i + ""));
        }
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
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
                finish();
                break;
            case R.id.titleRight:
                startActivity(new Intent(this, ShoppingCartActivity.class));
                break;
        }

    }
}
