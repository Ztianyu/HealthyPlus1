package com.zty.healthy.healthyplus.ui.activity.mall;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.BaseActivity;

/**
 * 购物车
 * Created by zty on 2016/10/12.
 */

public class ShoppingCartActivity extends BaseActivity implements View.OnClickListener {

    private ListView listViewShoppingCart;
    private TextView textCartPrise, textToPay;

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping_cart;
    }

    @Override
    protected void initView() {

        title.setText("购物车");
        left.setText("");
        left.setBackgroundResource(R.mipmap.ic_back);
        left.setOnClickListener(this);
        right.setText("");
        right.setBackgroundResource(R.mipmap.ic_login);
        right.setOnClickListener(this);

        listViewShoppingCart = (ListView) findViewById(R.id.listViewShoppingCart);
        textCartPrise = (TextView) findViewById(R.id.textCartPrise);
        textToPay = (TextView) findViewById(R.id.textToPay);
    }

    @Override
    protected void initData() {

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
                break;
        }
    }
}
