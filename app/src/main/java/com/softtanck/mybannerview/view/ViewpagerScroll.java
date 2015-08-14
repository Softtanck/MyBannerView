package com.softtanck.mybannerview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 8/14/2015
 */
public class ViewpagerScroll extends Scroller {


    /**
     * 滚动速度
     */
    private int mScrollSpeed;

    public ViewpagerScroll(Context context, ViewPager viewPager, int speed) {
        super(context);
        this.mScrollSpeed = speed;
        initScroller(viewPager);
    }

    public ViewpagerScroll(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @SuppressLint("NewApi")
    public ViewpagerScroll(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        if (200 < duration)
            super.startScroll(startX, startY, dx, dy, duration);
        else
            super.startScroll(startX, startY, dx, dy, mScrollSpeed);

    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mScrollSpeed);
    }

    /**
     * 初始化ViewPager
     *
     * @param viewPager
     */
    private void initScroller(ViewPager viewPager) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(viewPager, this);
        } catch (Exception e) {
        }
    }
}
