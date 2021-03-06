package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * author : 宋佳
 * time   : 2018/06/04
 * desc   : SurfaceView
 * version: 1.0.0
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {


    private SurfaceHolder mHolder;

    //用于绘制的Canvas
    private Canvas mCanvas;
    //子线程标志位  用来控制子线程的
    private boolean mIsDrawing;
    private int x = 0;
    private int y = 0;
    private Path mPath;
    private Paint mPaint;


    public MySurfaceView(Context context) {
        super(context);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // SurfaceView的创建
        mIsDrawing = true;
        mPath.moveTo(0, 400);
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
        while (mIsDrawing) {
            draw();
            x += 1;
            y = (int) (100 * Math.sin(x * 2 * Math.PI / 180) + 400);
            mPath.lineTo(x, y);
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            //开始绘制
            // SurfaceView背景
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath, mPaint);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                //每次绘制完 开始提交
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }

    }
}
