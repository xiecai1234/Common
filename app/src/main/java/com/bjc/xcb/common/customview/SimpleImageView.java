package com.bjc.xcb.common.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.bjc.xcb.common.R;
import com.bjc.xcb.common.util.ImageUtils;


/**
 * 简单的ImageView,用于显示图片
 */
public class SimpleImageView extends View {
    // 画笔
    private Paint mBitmapPaint;
    // 图片drawable
    private Drawable mDrawable;
    // view的宽度
    private int mWidth;
    // view的高度
    private int mHeight;

    public SimpleImageView(Context context) {
        this(context, null);
    }

    public SimpleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
                //注意下划线
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 设置View的宽高
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawable == null) {
            return;
        }
        // 绘制图片
        canvas.drawBitmap(ImageUtils.drawableToBitamp(mDrawable),
                getLeft(), getTop(), mBitmapPaint);
    }

}
