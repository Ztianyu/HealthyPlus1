package com.zty.healthy.healthyplus.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.zty.healthy.healthyplus.R;
import com.zty.healthy.healthyplus.base.MyApplication;
import com.zty.healthy.healthyplus.model.OldManModel;
import com.zty.healthy.healthyplus.ui.activity.home.AddOldManActivity;
import com.zty.healthy.healthyplus.utils.ImageLoader;

import java.util.List;

/**
 * Created by zty on 2016/8/27.
 */
public class AccountIconAdapter extends PagerAdapter {

    private Context context;
    private List<OldManModel> mData;

    public AccountIconAdapter(Context context, List<OldManModel> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = View.inflate(context, R.layout.item_circle_img,
                null);
        CircularImageView image = (CircularImageView) view.findViewById(R.id.imgCircleImg);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == (mData.size() - 1)) {
                    context.startActivity(new Intent(context, AddOldManActivity.class));
                }
            }
        });

        if (position == (mData.size() - 1)) {
            ImageLoader.load(context, R.mipmap.icon_user, image);
        }

        ImageLoader.load(context, mData.get(position).getIcon(), image);

        container.addView(image);

        return image;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
