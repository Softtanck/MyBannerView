package com.softtanck.mybannerview.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/15/2015
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<ImageView> mlist;

    public MyPagerAdapter(List<ImageView> list) {
        this.mlist = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % mlist.size();
        container.addView(mlist.get(position));
        return mlist.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position = position % mlist.size();
        container.removeView(mlist.get(position));
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
