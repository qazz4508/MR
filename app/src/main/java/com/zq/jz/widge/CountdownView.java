package com.zq.jz.widge;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.zq.jz.R;
import com.zq.jz.util.LogUtil;
import com.zq.jz.util.SizeUtil;

/**
 * 倒计时圆圈控件
 */
public class CountdownView extends View {

    private Paint mBorderPaint;

    private int mBorderColor;
    private float mBorderWidth;
    private RectF mBorderRect;
    private float mSweepAngle = 30;
    private long mDuration;
    private ObjectAnimator mAnimator;
    private CountDownListener mListener;
    private float mTextSize;
    private String mText;
    private Paint mTextPaint;
    private int mTextColor;
    private Rect mRect;

    public CountdownView(Context context) {
        this(context, null);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CountdownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CountdownView);
        if (array != null) {
            mBorderColor = array.getColor(R.styleable.CountdownView_border_color, getResources().getColor(R.color.primary));
            mTextColor = array.getColor(R.styleable.CountdownView_count_down_text_color, Color.BLACK);
            mBorderWidth = array.getDimension(R.styleable.CountdownView_border_width, SizeUtil.dip2px(3));
            int integer = array.getInteger(R.styleable.CountdownView_count_down_duration, 2000);
            mDuration = (long) integer;
            mTextSize = array.getDimension(R.styleable.CountdownView_count_down_text_size, SizeUtil.sp2px(12));
            mText = array.getString(R.styleable.CountdownView_count_down_text);
            array.recycle();
        }

        init();
    }

    private void init() {
        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStrokeWidth(mBorderWidth);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);

        mRect = new Rect();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : SizeUtil.dip2px(36),
                heightMode == MeasureSpec.EXACTLY ? heightSize : SizeUtil.dip2px(36));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBorderRect = new RectF(mBorderWidth / 2, mBorderWidth / 2, w - mBorderWidth / 2, h - mBorderWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(mBorderRect, 0, mSweepAngle, false, mBorderPaint);


        mTextPaint.getTextBounds(mText, 0, mText.length(), mRect);
        int x = getMeasuredWidth() / 2 - (mRect.right - mRect.left) / 2;
        int y = (int) (getMeasuredHeight() / 2 + SizeUtil.getBaseline(mTextPaint));
        canvas.drawText(mText, x, y, mTextPaint);
    }

    public void startCountDown() {
        mAnimator = ObjectAnimator.ofFloat(this, "sweepAngle", 0, 360);
        mAnimator.setDuration(mDuration);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (mListener != null) {
                    mListener.onEnd();
                }
            }
        });
        mAnimator.start();
    }

    public void setSweepAngle(float sweepAngle) {
        this.mSweepAngle = sweepAngle;
        invalidate();
    }


    public void setListener(CountDownListener listener) {
        mListener = listener;
    }

    public interface CountDownListener {
        void onEnd();
    }

    public void cancelAnim() {
        if (mAnimator != null) {
            mAnimator.cancel();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAnim();
    }
}
