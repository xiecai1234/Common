package com.bjc.xcb.common.animation;

import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.bjc.xcb.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ObjectAnimatorActivity extends AppCompatActivity {
    @BindView(R.id.imageview)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_objectanimator);
        ButterKnife.bind(this);
    }

    public void translate(View view) {
//        TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 0);
//        translateAnimation.setDuration(2000);
//        translateAnimation.setFillAfter(true);
//        imageView.startAnimation(translateAnimation);

        ObjectAnimator.ofFloat(imageView, "translationX", 0, 200)//动画类型及参数值
                .setDuration(1000)//动画时长
                .start();//开始动画
    }

    public void rotate(View view){
//        ObjectAnimator.ofFloat(imageView,"rotationX",0,360).setDuration(1000).start();//以X轴为轴旋转一圈
        ObjectAnimator.ofFloat(imageView,"rotationY",0,360).setDuration(1000).start();//以Y轴为轴旋转一圈
    }

    public void scale(View view){
        ObjectAnimator.ofFloat(imageView,"scaleX",1,2.0f).setDuration(1000).start();
        ObjectAnimator.ofFloat(imageView,"scaleY",1,2.0f).setDuration(1000).start();
    }

    public void alpha(View view){
        ObjectAnimator.ofFloat(imageView,"alpha",1,0.5f).setDuration(1000).start();
    }

    public void fromXML(View view){
        //加载属性动画需要用到AnimatorInflater类
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater
                .loadAnimator(this, R.animator.object);
        //用于动画计算的需要，如果开始和结束的值不是基本类型的时候，这个方法是需要的。
        objectAnimator.setEvaluator(new ArgbEvaluator());
        //设置动画的设置目标
        objectAnimator.setTarget(imageView);
        objectAnimator.start();
    }

    @OnClick(R.id.imageview)
    public void onViewClicked() {
        Toast.makeText(this, "点我", Toast.LENGTH_SHORT).show();
    }
}
