package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class MyViewGroupB extends ViewGroup {


    public MyViewGroupB(Context context) {
        super(context);
    }

    public MyViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("sjb", "dispatchTouchEvent事件----B" + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        Log.i("sjb", "onInterceptTouchEvent事件----B" + ev.getAction());
        return super.onInterceptTouchEvent(ev);

    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("sjb", "onTouchEvent----B" + ev.getAction());
        return super.onTouchEvent(ev);
    }
}
