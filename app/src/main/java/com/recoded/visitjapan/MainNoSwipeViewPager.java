package com.recoded.visitjapan;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;

/**
 * Created by wisam on Oct 16 17.
 */

public class MainNoSwipeViewPager extends ViewPager {
    private boolean swipeEnabled;

    public MainNoSwipeViewPager(Context context) {
        super(context);
        setMyScroller();
        swipeEnabled = false;
    }

    public MainNoSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MainNoSwipeViewPager);
        try {
            swipeEnabled = a.getBoolean(R.styleable.MainNoSwipeViewPager_swipeable, false);
        } finally {
            a.recycle();
        }
        setMyScroller();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (swipeEnabled) {
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (swipeEnabled) {
            return super.onTouchEvent(ev);
        }
        return false;
    }

    public void setSwipingEnabled(boolean enabled) {
        this.swipeEnabled = enabled;
    }

    //down one is added for smooth scrolling

    private void setMyScroller() {
        try {
            Class<?> viewpager = ViewPager.class;
            Field scroller = viewpager.getDeclaredField("mScroller");
            scroller.setAccessible(true);
            scroller.set(this, new MyScroller(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyScroller extends Scroller {
        public MyScroller(Context context) {
            super(context, new DecelerateInterpolator());
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, 350 /*1 secs*/);
        }
    }
}
