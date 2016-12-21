package com.example.administrator.loadingpager.ui;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.loadingpager.R;


/**
 * Created by Administrator on 2016/12/21.
 */

public class LoadingPager implements Loading {
    private ViewGroup parent;
    private View loadingLayout;
    private Animation loadingAnimation = null;
    private Animation inLoadingAnimation = null;
    private Animation outLoadingAnimation = null;

    public LoadingPager(ViewGroup parent, View loadingLayout, Animation loadingAnimation, Animation inLoadingAnimation, Animation outLoadingAnimation) {
        this.parent = parent;
        this.loadingLayout = loadingLayout;
        this.loadingAnimation = loadingAnimation;
        this.inLoadingAnimation = inLoadingAnimation;
        this.outLoadingAnimation = outLoadingAnimation;
    }

    @Override
    public void show() {
//        parent.removeAllViews();
        parent.addView(loadingLayout);
        //start Animations
        if (inLoadingAnimation != null) {
            loadingLayout.startAnimation(inLoadingAnimation);

        }
        if (loadingAnimation != null) {
            View child = ((ViewGroup) (loadingLayout)).getChildAt(0);
            child.startAnimation(loadingAnimation);
        }
    }

    @Override
    public void dismiss() {
        if (outLoadingAnimation == null) {
            removeLoadingPager();
            return;
        }
        loadingLayout.startAnimation(outLoadingAnimation);
        outLoadingAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                removeLoadingPager();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void removeLoadingPager() {
        View child = ((ViewGroup) (loadingLayout)).getChildAt(0);
        child.clearAnimation();
        loadingLayout.clearAnimation();
        parent.removeView(loadingLayout);
    }

    public static class Builder implements BuilderLoadingPager<Builder, LoadingPager> {
        private ViewGroup parent;
        public Context mContext;
        private ViewGroup loadingLayout;
        private View loadingLayoutChild;
        private int layoutId;
        private Animation loadingAnimation = null;
        private Animation inLoadingAnimation = null;
        private Animation outLoadingAnimation = null;

        public Builder(ViewGroup parent) {
            this.parent = parent;
            this.mContext = parent.getContext();
            this.layoutId = R.layout.loading_pager;
            //init default loadingPager container
            loadingLayout = initLoadingPagerContainer();
            //init default loadingPager container's Child
            loadingLayoutChild = initLoadingLayoutChild();
            //init default Animation
            loadingAnimation = initLoadingAnimation();
            inLoadingAnimation = initInLoadingAnimation();
            outLoadingAnimation = initOutLoadingAnimation();
        }

        protected View initLoadingLayoutChild() {
            TextView textView = new TextView(parent.getContext());
            textView.setText("loading");
            return textView;
        }

        protected FrameLayout initLoadingPagerContainer() {
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            FrameLayout container = new FrameLayout(this.parent.getContext());
            container.setBackgroundColor(Color.parseColor("#fff000"));
            container.setLayoutParams(params);
            return container;
        }

        protected Animation initOutLoadingAnimation() {
            return null;
        }

        protected Animation initInLoadingAnimation() {
            return null;
        }

        protected Animation initLoadingAnimation() {
            return null;
        }

        @Override
        public Builder setInLoadingAnimation(Animation animation) {
            inLoadingAnimation = animation;
            return this;
        }

        @Override
        public Builder setOutLoadingAnimation(Animation animation) {
            outLoadingAnimation = animation;
            return this;
        }

        @Override
        public Builder setLoadinAnimation(Animation animation) {
            loadingAnimation = animation;
            return this;
        }


        @Override
        public Builder setLoadingLayout(ViewGroup view) {
            loadingLayout = view;
            return this;
        }

        @Override
        public Builder setLoadingLayoutChild(View view) {
            this.loadingLayoutChild = view;
            return this;
        }

        public Builder setLoadingLayout(int resId) {
            View view = LayoutInflater.from(parent.getContext()).inflate(resId, parent);
            if (!(view instanceof ViewGroup)) {
                throw new IllegalStateException("LoadingLayout must be ViewGroup");
            }
            loadingLayout = (ViewGroup) view;
            return this;
        }

        @Override
        public LoadingPager create() {
            ViewGroup.LayoutParams parentParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            loadingLayout.setLayoutParams(parentParams);
            loadingLayout.addView(loadingLayoutChild);
            // make loading layout child center
            letLoadingLayoutChildCenter();
            LoadingPager loadingPager = new LoadingPager(parent, loadingLayout, loadingAnimation, inLoadingAnimation, outLoadingAnimation);
            return loadingPager;
        }

        private void letLoadingLayoutChildCenter() {
            /*loadingLayout.measure(0,0);
            loadingLayoutChild.measure(0,0);
            int loadingLayoutWidth = loadingLayout.getMeasuredWidth();
            int loadingLayoutHeight = loadingLayout.getMeasuredHeight();
            int loadingLayoutChildWidth = loadingLayoutChild.getMeasuredWidth();
            int loadingLayoutChildHeight = loadingLayoutChild.getMeasuredHeight();
            int left = (loadingLayoutWidth - loadingLayoutChildWidth) / 2;
            int top = (loadingLayoutHeight - loadingLayoutChildHeight) / 2;*/
            FrameLayout.LayoutParams params =
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            loadingLayoutChild.setLayoutParams(params);
        }
    }
}
