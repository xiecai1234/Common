package com.bjc.xcb.common.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bjc.xcb.common.R;

/**
 * 圆形ImageView,用于显示用户头像
 */
public class MeaturedCircleImageView extends View {

    private static final String STATE_INSTANCE = "state_instance";
    private static final String RADIUS = "radius";

    /**
     * 绘图的Paint
     */
    private Paint mBitmapPaint;
    /**
     * 圆角的半径
     */
    private int mRadius;
    /**
     * 3x3 矩阵，主要用于缩小放大
     */
    private Matrix mMatrix;
    /**
     * 渲染图像，使用图像为绘制图形着色
     */
    private BitmapShader mBitmapShader;

    private Drawable mDrawable;
    /**
     * view的宽度
     */
    private int mWidth;

    public MeaturedCircleImageView(Context context) {
        this(context, null);
    }

    public MeaturedCircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.CircleImageView);
                mDrawable = array.getDrawable(R.styleable.CircleImageView_circle_src);
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
        mWidth = Math.min(mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
        mRadius = mWidth / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mWidth);
    }

    public Drawable getDrawable() {
        return mDrawable;
    }

    /**
     * 初始化BitmapShader
     */
    private void setUpShader() {
        Drawable drawable = getDrawable();
        Bitmap bmp = drawableToBitamp(drawable);
        if (drawable == null || bmp == null) {
            return;
        }

        // 将bmp作为着色器，就是在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(bmp, TileMode.CLAMP, TileMode.CLAMP);
        float scale = 1.0f;
        // 拿到bitmap宽或高的小值
        int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
        scale = mWidth * 1.0f / bSize;

        // shader的变换矩阵，我们这里主要用于放大或者缩小
        mMatrix.setScale(scale, scale);
        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mBitmapPaint.setShader(mBitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }

        Log.e(VIEW_LOG_TAG, "### draw bitmap ");
        setUpShader();
        canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
    }

    /**
     * drawable转bitmap
     * 
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(RADIUS, mRadius);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state)
                    .getParcelable(STATE_INSTANCE));
            this.mRadius = bundle.getInt(RADIUS);
        } else {
            super.onRestoreInstanceState(state);
        }
    }
}
