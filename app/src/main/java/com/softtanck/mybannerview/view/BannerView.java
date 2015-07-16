package com.softtanck.mybannerview.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/15/2015
 */
public class BannerView extends ViewPager {
    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

    }
}
