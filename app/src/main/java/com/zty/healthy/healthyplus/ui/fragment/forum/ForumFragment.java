package com.zty.healthy.healthyplus.ui.fragment.forum;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.adapter.ViewPagerAdapter;
import com.zty.healthy.healthyplus.base.BaseActivity;
import com.zty.healthy.healthyplus.base.BaseFragment;
import com.zty.healthy.healthyplus.ui.activity.forum.MyForumActivity;
import com.zty.healthy.healthyplus.ui.activity.forum.PostForumActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zty on 2016/8/16.
 */
public class ForumFragment extends BaseFragment implements View.OnClickListener {

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
        ((BaseActivity) context).left.setVisibility(View.VISIBLE);
        ((BaseActivity) context).right.setVisibility(View.VISIBLE);
        ((BaseActivity) context).title.setText("论 坛");
        ((BaseActivity) context).left.setText("我的帖子");
        ((BaseActivity) context).left.setBackgroundResource(R.color.transparent);
        ((BaseActivity) context).left.setOnClickListener(this);
        ((BaseActivity) context).right.setText("");
        ((BaseActivity) context).right.setBackgroundResource(R.mipmap.ic_form_add);
        ((BaseActivity) context).right.setOnClickListener(this);

        tab = (TabLayout) view.findViewById(R.id.tabHealthyData);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerHealthyData);

        tab.setTabMode(TabLayout.MODE_FIXED);
        tab.setTabTextColors(Color.WHITE, Color.rgb(255, 224, 91));

    }

    @Override
    public void initData() {

        titles.clear();
        titles.add("社区交流");
        titles.add("健康知识");
        titles.add("视频讲座");

        List<BaseFragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new ForumListFragment(i));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titleLeft:
                startActivity(new Intent(context, MyForumActivity.class));
                break;
            case R.id.titleRight:
                startActivity(new Intent(context, PostForumActivity.class));
                break;
        }

    }
}
