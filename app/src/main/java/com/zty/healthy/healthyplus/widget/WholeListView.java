package com.zty.healthy.healthyplus.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 全部显示的listView
 */
public class WholeListView extends ListView {

    public WholeListView(Context context) {
        super(context);
    }

    public WholeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WholeListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
