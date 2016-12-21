package com.example.administrator.loadingpager.ui;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface BuilderLoadingPager<Builder, LoadingPager> {
    Builder setInLoadingAnimation(Animation animation);

    Builder setOutLoadingAnimation(Animation animation);

    Builder setLoadinAnimation(Animation animation);

    Builder setLoadingLayout(ViewGroup view);

    Builder setLoadingLayoutChild(View view);

    LoadingPager create();
}
