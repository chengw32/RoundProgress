package com.test.custom.roundprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Author 陈国武
 * Time 2017/5/16.
 * Des:
 */

public class RoundProgress extends View {
    public RoundProgress(Context context) {
        super(context);
    }

    private int textColor ,progressColor,progressBgColor,progressMax;
    private float progressWidth ,textSize;

    private Paint mPaint ;
    private Paint linePaint ;
    private float angle = 0;
    private float currentProgress = 0;


    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        textColor = typedArray.getColor(R.styleable.RoundProgress_ProgressTextColor, Color.GRAY);
        progressColor = typedArray.getColor(R.styleable.RoundProgress_ProgressColor, Color.GREEN);
        progressBgColor = typedArray.getColor(R.styleable.RoundProgress_ProgressBackgroundColor, Color.BLUE);
        progressMax = typedArray.getInteger(R.styleable.RoundProgress_ProgressMaxValue,100);
        textSize = typedArray.getDimension(R.styleable.RoundProgress_ProgressTextSize, 50);
        progressWidth = typedArray.getDimension(R.styleable.RoundProgress_ProgressWidth, 20);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerPoint = getWidth() / 2 ;
        float radius = centerPoint - progressWidth / 2 ;
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(progressWidth);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(centerPoint,centerPoint,radius,mPaint);

        //中间的进度提示文字
        mPaint.setColor(Color.GRAY);
        mPaint.setTextAlign(Paint.Align.CENTER);//设置文本相对 画字体时 的起始坐标 的位置 center 以起始坐标为中心
        mPaint.setStrokeWidth(0);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT);//设置字体呀样式
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        linePaint = new Paint();
//        canvas.drawLine(0,centerPoint,getWidth(),centerPoint,linePaint);
//        canvas.drawLine(centerPoint,0,centerPoint,getWidth(),linePaint);

        Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
        float y = (float) centerPoint -  (fm.bottom+fm.top)/2;
        canvas.drawText((int)(currentProgress * 100 )+ "%",centerPoint, y,mPaint);

        //画圆弧
//        RectF rf = new RectF(10, 10,50,50 );
        RectF rf = new RectF(centerPoint - radius, centerPoint - radius,centerPoint + radius,centerPoint + radius );//外切圆的矩形区域
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeWidth(progressWidth);
        canvas.drawArc(rf,-180,angle,false,mPaint);//开始角度是从右下开始 顺时针递增

    }

    public void setProgress(int progress){
        currentProgress = (float) progress / (float) progressMax ;
        angle =  (currentProgress * 360);
        Log.e("angle","-----------"+angle);
        Log.e("currentProgress","-----------"+currentProgress);
        postInvalidate();
    }

    public void setProgressBgColor (int color){
        this.progressBgColor = color;
    }

    public void setProgressColor(int color){
        this.progressColor = color;
    }

    public int getProgressBgColor(){
        return progressBgColor;
    }

}
