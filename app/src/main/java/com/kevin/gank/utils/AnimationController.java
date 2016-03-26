package com.kevin.gank.utils;
//
//  Created by XuGuobiao  2012-8-25
//  Copyright 2012年 ever. All rights reserved
//
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class AnimationController {
    public final int rela1 = Animation.RELATIVE_TO_SELF;
    public final int rela2 = Animation.RELATIVE_TO_PARENT;

    public final int Default = -1;
    public final int Linear = 0;
    public final int Accelerate = 1;
    public final int Decelerate = 2;
    public final int AccelerateDecelerate = 3;
    public final int Bounce = 4;
    public final int Overshoot = 5;
    public final int Anticipate = 6;
    public final int AnticipateOvershoot = 7;

    // LinearInterpolator,AccelerateInterpolator,DecelerateInterpolator,AccelerateDecelerateInterpolator,
    // BounceInterpolator,OvershootInterpolator,AnticipateInterpolator,AnticipateOvershootInterpolator

    public AnimationController() {
    }


    private class MyAnimationListener implements AnimationListener {
        private View view;
        private int visiabilityOnEnd;

        public MyAnimationListener(View view) {
            this.view = view;
            visiabilityOnEnd = View.GONE;
        }
        public MyAnimationListener(View view,int visiabilityOnEnd) {
            this.view = view;
            this.visiabilityOnEnd = visiabilityOnEnd;
        }

        @Override
        public void onAnimationStart(Animation animation) {
            // this.view.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            this.view.setVisibility(visiabilityOnEnd);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    private void setEffect(Animation animation, int interpolatorType, long durationMillis, long delayMillis) {
        switch (interpolatorType) {
            case 0:
                animation.setInterpolator(new LinearInterpolator());
                break;
            case 1:
                animation.setInterpolator(new AccelerateInterpolator());
                break;
            case 2:
                animation.setInterpolator(new DecelerateInterpolator());
                break;
            case 3:
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                break;
            case 4:
                animation.setInterpolator(new BounceInterpolator());
                break;
            case 5:
                animation.setInterpolator(new OvershootInterpolator());
                break;
            case 6:
                animation.setInterpolator(new AnticipateInterpolator());
                break;
            case 7:
                animation.setInterpolator(new AnticipateOvershootInterpolator());
                break;
            default:
                break;
        }
        animation.setDuration(durationMillis);
        animation.setStartOffset(delayMillis);
    }

    private void baseIn(View view, Animation animation, long durationMillis, long delayMillis) {
        setEffect(animation, Default, durationMillis, delayMillis);
        view.setVisibility(View.VISIBLE);
        view.startAnimation(animation);
    }

    private void baseOut(View view, Animation animation, long durationMillis, long delayMillis,int visiblityOnEnd) {
        setEffect(animation, Default, durationMillis, delayMillis);
        animation.setAnimationListener(new MyAnimationListener(view, visiblityOnEnd));
        view.startAnimation(animation);
    }

    private void baseOut(View view, Animation animation, long durationMillis, long delayMillis) {
        setEffect(animation, Default, durationMillis, delayMillis);
        animation.setAnimationListener(new MyAnimationListener(view));
        view.startAnimation(animation);
    }

    public void show(View view) {
        view.setVisibility(View.VISIBLE);
    }

    public void hide(View view) {
        view.setVisibility(View.GONE);
    }

    public void transparent(View view) {
        view.setVisibility(View.INVISIBLE);
    }

    public void fadeIn(View view, long durationMillis, long delayMillis) {
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    public void fadeOut(View view, long durationMillis, long delayMillis) {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void slideIn(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation = new TranslateAnimation(rela2, 1, rela2, 0, rela2, 0, rela2, 0);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    public void slideOut(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation = new TranslateAnimation(rela2, 0, rela2, -1, rela2, 0, rela2, 0);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void slideUp(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation = new TranslateAnimation(rela2, 0, rela2, 0, rela2, 0, rela2, 1);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void slideDown(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation = new TranslateAnimation(rela2, 0, rela2, 0, rela2, 1, rela2, 0);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void scaleIn(View view, long durationMillis, long delayMillis) {
        ScaleAnimation animation = new ScaleAnimation(0, 1, 0, 1, rela2, 0.5f, rela2, 0.5f);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    public void scaleIn2(View view, long durationMillis, long delayMillis) {
        ScaleAnimation animation = new ScaleAnimation(2, 0.9f, 2, 0.9f, rela1, 0.5f, rela1, 0.5f);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    public void scaleOut(View view, long durationMillis, long delayMillis) {
        ScaleAnimation animation = new ScaleAnimation(1, 0, 1, 0, rela2, 0.5f, rela2, 0.5f);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void rotateIn(View view, long durationMillis, long delayMillis) {
        RotateAnimation animation = new RotateAnimation(-90, 0, rela1, 0, rela1, 1);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    public void rotateOut(View view, long durationMillis, long delayMillis) {
        RotateAnimation animation = new RotateAnimation(0, 90, rela1, 0, rela1, 1);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void scaleRotateIn(View view, long durationMillis, long delayMillis) {
        ScaleAnimation animation1 = new ScaleAnimation(0, 1, 0, 1, rela1, 0.5f, rela1, 0.5f);
        RotateAnimation animation2 = new RotateAnimation(0, 360, rela1, 0.5f, rela1, 0.5f);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    public void scaleRotateOut(View view, long durationMillis, long delayMillis) {
        ScaleAnimation animation1 = new ScaleAnimation(1, 0, 1, 0, rela1, 0.5f, rela1, 0.5f);
        RotateAnimation animation2 = new RotateAnimation(0, 360, rela1, 0.5f, rela1, 0.5f);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void slideFadeIn(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation1 = new TranslateAnimation(rela2, 1, rela2, 0, rela2, 0, rela2, 0);
        AlphaAnimation animation2 = new AlphaAnimation(0, 1);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    public void slideFadeOut(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation1 = new TranslateAnimation(rela2, 0, rela2, -1, rela2, 0, rela2, 0);
        AlphaAnimation animation2 = new AlphaAnimation(1, 0);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void slideFadeIn_up(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation1 = new TranslateAnimation(rela2, 0, rela2, 0, rela2, 1, rela2, 0);
        AlphaAnimation animation2 = new AlphaAnimation(0, 1);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseIn(view, animation, durationMillis, delayMillis);
    }
    public void slideFadeIn_up(View view, long durationMillis, long delayMillis,int rela) {
        TranslateAnimation animation1 = new TranslateAnimation(rela, 0, rela, 0, rela, 1, rela, 0);
        AlphaAnimation animation2 = new AlphaAnimation(0, 1);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    public void slideFadeOut_down(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation1 = new TranslateAnimation(rela2, 0, rela2, 0, rela2, 0, rela2, 1);
        AlphaAnimation animation2 = new AlphaAnimation(1, 0);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseOut(view, animation, durationMillis, delayMillis);
    }

    public void slideFadeOut_down(View view, long durationMillis, long delayMillis,int visibilityOnEnd) {
        TranslateAnimation animation1 = new TranslateAnimation(rela2, 0, rela2, 0, rela2, 0, rela2, 1);
        AlphaAnimation animation2 = new AlphaAnimation(1, 0);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseOut(view, animation, durationMillis, delayMillis,visibilityOnEnd);
    }
    public void slideFadeOut_down(View view, long durationMillis, long delayMillis,int visibilityOnEnd,int rela) {
        TranslateAnimation animation1 = new TranslateAnimation(rela, 0, rela, 0, rela, 0, rela, 1);
        AlphaAnimation animation2 = new AlphaAnimation(1, 0);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseOut(view, animation, durationMillis, delayMillis,visibilityOnEnd);
    }


    public void slideLeft(View view, long durationMillis, long delayMillis) {
        TranslateAnimation animation1 = new TranslateAnimation(rela2, 1, rela2, 0, rela2, 0, rela2, 0);
        AlphaAnimation animation2 = new AlphaAnimation(0, 1);
        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(animation1);
        animation.addAnimation(animation2);
        baseIn(view, animation, durationMillis, delayMillis);
    }

    /**
     * 使用属性动画把空间view移动到合适的位置
     * 已弃用，考虑删除
     * @param view
     * @param suitablePosition
     */
    public static void translateOfTouch(View view, int suitablePosition)
    {
        int duration = 300;
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "TranslationX", suitablePosition - view.getLayoutParams().width/2)
                                                .setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    /**
     * 控件跟随手指
     * 已弃用，考虑删除
     * @param view
     * @param startPosition
     * @param moveX
     */
    public static void followFinger(View view, float startPosition, float moveX)
    {
//        ObjectAnimator.ofFloat(view,"translationX",startPosition,startPosition+moveX-view.getLayoutParams().width/2).setDuration(1).start();
        ObjectAnimator.ofFloat(view,"translationX",startPosition,startPosition+moveX).setDuration(1).start();
    }

    /**
     * 控件纵向移动
     * @param v
     */
    public static void translateY(View v, int startY, int endY) {
        int duration = 200;
        ObjectAnimator animator = ObjectAnimator.ofFloat(v, "translationY", startY, endY);
        animator.setDuration(duration);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
