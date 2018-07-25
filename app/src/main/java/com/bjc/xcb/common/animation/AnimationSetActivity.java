package com.bjc.xcb.common.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.Toast;

import com.bjc.xcb.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationSetActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_set);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.imageView)
    public void onViewClicked() {
//        ObjectAnimator.ofFloat(imageView, "rotation", 0F, 360F).setDuration(1000).start();
//        ObjectAnimator.ofFloat(imageView, "scaleX", 1F, 2F).setDuration(1000).start();
//        ObjectAnimator.ofFloat(imageView, "translationX", 0F, 200F).setDuration(1000).start();

//        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("rotation", 0F, 360F);
//        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofFloat("scaleX", 1F, 2F);
//        PropertyValuesHolder propertyValuesHolder3 = PropertyValuesHolder.ofFloat("translationX", 0F, 200F);
//        ObjectAnimator.ofPropertyValuesHolder(imageView, propertyValuesHolder1,
//                propertyValuesHolder2, propertyValuesHolder3).setDuration(1000).start();

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 180f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(imageView, "scaleY", 1f, 1.5f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 200f);
        objectAnimator3.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(AnimationSetActivity.this, "平移动画结束", Toast.LENGTH_SHORT).show();
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.playTogether(objectAnimator1, objectAnimator2, objectAnimator3);
//        animatorSet.playSequentially(objectAnimator1, objectAnimator2, objectAnimator3);
        animatorSet.play(objectAnimator3).with(objectAnimator2).before(objectAnimator1);
        animatorSet.setDuration(2000);
        animatorSet.start();
    }
}
