package com.bjc.xcb.common.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bjc.xcb.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends AppCompatActivity {

    @BindView(com.bjc.xcb.common.R.id.btn_alpha)
    Button btnAlpha;
    @BindView(com.bjc.xcb.common.R.id.iv_pic)
    ImageView imageView;

    private boolean isAlphaZero = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_customview_normal_img);
        setContentView(com.bjc.xcb.common.R.layout.anim_test);
        ButterKnife.bind(this);
    }

    @OnClick(com.bjc.xcb.common.R.id.btn_alpha)
    public void onViewClicked() {
//        changeAlpha2();
        translate();
//        rotate();
//        scale();
    }

    public void changeAlpha() {
        if (isAlphaZero) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);//初始化操作，参数传入0和1，即由透明度0变化到透明度为1
            imageView.startAnimation(alphaAnimation);//开始动画
            alphaAnimation.setFillAfter(true);//动画结束后保持状态
            alphaAnimation.setDuration(2000);//动画持续时间，单位为毫秒
            isAlphaZero = false;//标识位
        } else {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);//初始化操作，参数传入1和0，即由透明度1变化到透明度为0
            imageView.startAnimation(alphaAnimation);//开始动画
            isAlphaZero = true;//标识位
            alphaAnimation.setFillAfter(true);//动画结束后保持状态
            alphaAnimation.setDuration(2000);//动画持续时间
        }
    }

   public void changeAlpha2() {
        Animation alphaAnimation = AnimationUtils.loadAnimation(this, com.bjc.xcb.common.R.anim.alpha);
       imageView.startAnimation(alphaAnimation);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(AnimationActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void translate() {
//        TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 200);
//        translateAnimation.setDuration(2000);
//        imageView.setAnimation(translateAnimation);
//        imageView.startAnimation(translateAnimation);

        Animation translateAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, R.anim.in_from_down);
        imageView.setAnimation(translateAnimation);
        imageView.startAnimation(translateAnimation);
    }

    public void rotate() {
//        RotateAnimation rotateAnimation = new RotateAnimation(-180, 360, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0.5f);
//        imageView.setAnimation(rotateAnimation);
//        rotateAnimation.setDuration(2000);
//        imageView.startAnimation(rotateAnimation);

        Animation rotateAnimation= AnimationUtils.loadAnimation(AnimationActivity.this,com.bjc.xcb.common.R.anim.rotate);
        imageView.setAnimation(rotateAnimation);
        imageView.startAnimation(rotateAnimation);
    }

    public void scale() {
//        ScaleAnimation scaleAnimation=new ScaleAnimation(1f,0.5f,1f,0.3f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//        scaleAnimation.setDuration(2000);
//        imageView.setAnimation(scaleAnimation);
//        imageView.startAnimation(scaleAnimation);

        Animation scaleAnimation = AnimationUtils.loadAnimation(AnimationActivity.this, com.bjc.xcb.common.R.anim.scale);
        imageView.setAnimation(scaleAnimation);
        scaleAnimation.setFillAfter(true);
        imageView.startAnimation(scaleAnimation);
    }
}
