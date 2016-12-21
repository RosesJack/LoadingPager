package com.example.administrator.loadingpager.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import com.example.administrator.loadingpager.R;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MyLoadingPager extends LoadingPager.Builder {
    public MyLoadingPager(ViewGroup parent) {
        super(parent);
    }

    @Override
    protected View initLoadingLayoutChild() {
        return LayoutInflater.from(mContext).inflate(R.layout.loading_child, null);
    }

    @Override
    protected Animation initOutLoadingAnimation() {
        Animation animation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(2000);
        return animation;
    }

    @Override
    protected Animation initInLoadingAnimation() {
        Animation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        animation.setDuration(2000);
        return animation;
    }

    @Override
    protected Animation initLoadingAnimation() {
        Animation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, -1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);
        animation.setInterpolator(new AccelerateInterpolator());
        animation.setDuration(1000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        return animation;
    }
}
