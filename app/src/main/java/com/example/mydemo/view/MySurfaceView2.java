package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * author : 宋佳
 * time   : 2018/06/04
 * desc   : SurfaceView
 * version: 1.0.0
 */

public class MySurfaceView2 extends SurfaceView implements SurfaceHolder.Callback, Runnable {


    private SurfaceHolder mHolder;

    //用于绘制的Canvas
    private Canvas mCanvas;
    //子线程标志位  用来控制子线程的
    private boolean mIsDrawing;
    private Path mPath;
    private Paint mPaint;


    public MySurfaceView2(Context context) {
        super(context);
        init(context);
    }

    public MySurfaceView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySurfaceView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        //初始化 SurfaceHolder 并注册 SurfaceHolder的回调方法
        mHolder = getHolder();
        mHolder.addCallback(this);
        //设置焦点
        setFocusable(true);
        //
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // SurfaceView的创建
        mIsDrawing = true;
        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // SurfaceView的改变
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // SurfaceView的销毁
        mIsDrawing = false;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        while (mIsDrawing) {
            draw();
        }

        //防止频繁的刷新
        long end = System.currentTimeMillis();
        // 50 - 100
        if (end - start < 100) {
            try {
                Thread.sleep(100 - (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

}
