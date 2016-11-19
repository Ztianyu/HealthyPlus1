package com.zty.healthy.healthyplus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by zty on 2016/10/10.
 */

public class WholeGridView extends GridView {
    public WholeGridView(Context context) {
        super(context);
    }

    public WholeGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WholeGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
