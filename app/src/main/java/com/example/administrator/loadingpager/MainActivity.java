package com.example.administrator.loadingpager;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.loadingpager.ui.LoadingPager;
import com.example.administrator.loadingpager.ui.MyLoadingPager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FrameLayout parent;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        parent = (FrameLayout) findViewById(R.id.activity_main);
        parent.setBackgroundColor(Color.BLUE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final LoadingPager loadingPager = new MyLoadingPager(parent).create();
                loadingPager.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingPager.dismiss();
                    }
                }, 5000);
            }
        });

    }


}
