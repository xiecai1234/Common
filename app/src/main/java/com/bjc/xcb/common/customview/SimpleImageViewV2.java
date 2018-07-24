package com.bjc.xcb.common.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bjc.xcb.common.R;
import com.bjc.xcb.common.util.ImageUtils;

/**
 * 简单的ImageView,用于显示图片
 */
public class SimpleImageViewV2 extends View {
    private static final String TAG = "SimpleImageViewV2";
    // 画笔
    private Paint mBitmapPaint;
    // 图片drawable
    private Drawable mDrawable;
    // 要绘制的图片
    Bitmap mBitmap;
    // view的宽度
    private int mWidth;
    // view的高度
    private int mHeight;

    public SimpleImageViewV2(Context context) {
        this(context, null);
    }

    public SimpleImageViewV2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setColor(Color.RED);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
                mDrawable = array.getDrawable(R.styleable.SimpleImageView_src);
                measureDrawable();
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }

        }
    }

    private void measureDrawable() {
        if (mDrawable == null) {
            throw new RuntimeException("drawable不能为空呐!");
        }

        mWidth = mDrawable.getIntrinsicWidth();
        mHeight = mDrawable.getIntrinsicHeight();

        Log.e(TAG, "picture width = " + mWidth + ", height = " + mHeight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取宽度的模式与大小
         int widthMode = MeasureSpec.getMode(widthMeasureSpec);
         int width = MeasureSpec.getSize(widthMeasureSpec);
         // 高度的模式与大小
         int heightMode = MeasureSpec.getMode(heightMeasureSpec);
         int heiht = MeasureSpec.getSize(heightMeasureSpec);
         // 设置View的宽高
         setMeasuredDimension(measureWidth(widthMode, width),
         measureHeight(heightMode, heiht));

        // 设置View的宽高
        //resolveSize这个方法的主要作用就是根据你提供的大小和模式，返回你想要的大小值，这个里面根据传入模式的不同来做相应的处理
//        setMeasuredDimension(resolveSize(mWidth, widthMeasureSpec),
//                resolveSize(mHeight, heightMeasureSpec));
    }

    private int measureWidth(int mode, int width) {
        //width是系统计算得到的，如match_parent时得到1080
        Log.e(TAG, "### measureWidth , width = " + width);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                //重要：一般不会用到
                Log.e(TAG, "### MeasureSpec.AT_MOST");
                break;

            case MeasureSpec.AT_MOST:
                //重要：wrap_content走本分支
                //Math.min(size, specSize)
                Log.e(TAG, "### MeasureSpec.AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                //重要：match_parent和具体数值（如100dp）走本分支
                Log.e(TAG, "### MeasureSpec.EXACTLY");
                mWidth = width;
                break;
        }
        return mWidth;
    }

    private int measureHeight(int mode, int height) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                break;

            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                Log.e(TAG, "### MeasureSpec.EXACTLY , height = " + height);
                mHeight = height;
                break;
        }
        return mHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap == null) {
            mBitmap = Bitmap.createScaledBitmap(ImageUtils.drawableToBitamp(mDrawable),
                    getMeasuredWidth(), getMeasuredHeight(), true);
        }
        // 绘制图片
        canvas.drawBitmap(mBitmap,
                getLeft(), getTop(), mBitmapPaint);
        canvas.save();
        canvas.rotate(90);
        mBitmapPaint.setColor(Color.YELLOW);
        mBitmapPaint.setTextSize(30);
        canvas.drawText("AngelaBaby", getLeft() + 50, getTop() - 50, mBitmapPaint);
        canvas.restore();
    }

}
