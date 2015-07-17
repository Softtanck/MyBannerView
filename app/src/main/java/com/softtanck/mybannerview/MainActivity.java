package com.softtanck.mybannerview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.softtanck.mybannerview.fragment.OneFragment;
import com.softtanck.mybannerview.fragment.ThreeFragment;
import com.softtanck.mybannerview.fragment.TwoFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView one;

    private TextView two;

    private TextView three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        changeFragment(new OneFragment());
    }


    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment, null).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                changeFragment(new OneFragment());
                break;
            case R.id.two:
                changeFragment(new TwoFragment());
                break;
            case R.id.three:
                changeFragment(new ThreeFragment());
                break;
        }
    }
}
