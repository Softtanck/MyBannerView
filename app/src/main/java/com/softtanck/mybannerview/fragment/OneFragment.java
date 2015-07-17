package com.softtanck.mybannerview.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.softtanck.mybannerview.FixedSpeedScroller;
import com.softtanck.mybannerview.R;
import com.softtanck.mybannerview.adapter.MyPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 7/17/2015
 */
public class OneFragment extends Fragment implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private List<ImageView> list;
    private MyPagerAdapter adapter;

    private ImageView imageView;
    private int currentIndex;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(msg.what);
            handler.sendEmptyMessageDelayed(++msg.what, 3000);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.vp);

        imageView = (ImageView) view.findViewById(R.id.iv);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(view.getContext());
            imageView.setImageResource(R.mipmap.ic_launcher);
            list.add(imageView);
        }
        adapter = new MyPagerAdapter(list);
        viewPager.setAdapter(adapter);
        int currentItem = Integer.MAX_VALUE / 2;
        currentItem = currentItem - ((Integer.MAX_VALUE / 2) % list.size());
        viewPager.setCurrentItem(currentItem);
        viewPager.setOnPageChangeListener(this);
        drawPoint();
        handler.sendEmptyMessageDelayed(++currentItem, 3000);

        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(),
                    new AccelerateInterpolator());
            field.set(viewPager, scroller);
            scroller.setmDuration(500);
        } catch (Exception e) {
            Log.d("Tanck", "", e);
        }

    }


    private void drawPoint() {
        int radius = 10; // 半径
        int spacing = 50; // 点之间间隔
        Bitmap points = Bitmap.createBitmap(radius * 2 + spacing * (list.size() - 1), radius * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(points);
        Paint paint = new Paint();
        paint.setAntiAlias(true); // 设置画笔为无锯齿
        paint.setStyle(Paint.Style.FILL); // 实心
        for (int i = 0; i < list.size(); i++) {
            paint.setColor(Color.GRAY);
            if (currentIndex == i) // 设置选中项为白色
                paint.setColor(Color.WHITE);
            canvas.drawCircle(radius + spacing * i, radius, radius, paint);
        }
        imageView.setImageBitmap(points);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentIndex = position % list.size();
        drawPoint();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
