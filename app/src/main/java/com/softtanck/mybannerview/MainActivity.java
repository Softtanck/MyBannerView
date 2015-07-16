package com.softtanck.mybannerview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.Toast;

import com.softtanck.mybannerview.adapter.MyPagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private List<ImageView> list;
    private MyPagerAdapter adapter;

    private ImageView imageView;
    private int currentIndex;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(msg.what,true);
            handler.sendEmptyMessageDelayed(++msg.what, 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.vp);

        imageView = (ImageView) findViewById(R.id.iv);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(MainActivity.this);
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

//        try {
//            Field field = ViewPager.class.getDeclaredField("mScroller");
//            field.setAccessible(true);
//            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(),
//                    new AccelerateInterpolator());
//            field.set(viewPager, scroller);
//            scroller.setmDuration(1);
//        } catch (Exception e) {
//            Log.d("Tanck", "", e);
//        }
        
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
            if (currentIndex == i) // 设置选中项为红色
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
