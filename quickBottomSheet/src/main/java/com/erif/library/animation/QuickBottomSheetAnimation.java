package com.erif.library.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

import androidx.annotation.NonNull;

import com.erif.library.R;

public class QuickBottomSheetAnimation {

    public static void bounce(View view) {
        float bottom_sheet_margin_top = view.getContext().getResources().getDimension(R.dimen.bottom_sheet_margin_top) * -1;
        float bottom_sheet_trans = view.getContext().getResources().getDimension(R.dimen.bottom_sheet_trans);
        view.post(() -> {
            ValueAnimator anim3 = ValueAnimator.ofFloat(bottom_sheet_trans, 0f);
            anim3.setDuration(200);
            anim3.addUpdateListener(valueAnimator -> {
                float value = (float) valueAnimator.getAnimatedValue();
                view.setTranslationY(value);
            });

            ValueAnimator anim2 = ValueAnimator.ofFloat(bottom_sheet_margin_top, bottom_sheet_trans);
            anim2.setDuration(200);
            anim2.addUpdateListener(valueAnimator -> {
                float value = (float) valueAnimator.getAnimatedValue();
                view.setTranslationY(value);
            });
            anim2.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animator) {}
                @Override
                public void onAnimationEnd(@NonNull Animator animator) {
                    anim3.start();
                }
                @Override
                public void onAnimationCancel(@NonNull Animator animator) {}
                @Override
                public void onAnimationRepeat(@NonNull Animator animator) {}
            });

            ValueAnimator anim = ValueAnimator.ofFloat(0f, bottom_sheet_margin_top);
            anim.setDuration(150);
            anim.addUpdateListener(valueAnimator -> {
                float value = (float) valueAnimator.getAnimatedValue();
                view.setTranslationY(value);
            });
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(@NonNull Animator animator) {}
                @Override
                public void onAnimationEnd(@NonNull Animator animator) {
                    anim2.start();
                }
                @Override
                public void onAnimationCancel(@NonNull Animator animator) {}
                @Override
                public void onAnimationRepeat(@NonNull Animator animator) {}
            });
            anim.start();
        });
    }

    public static void zoomOut(View view) {
        float start = 0.8f;
        view.setScaleX(start);
        view.setScaleY(start);
        view.post(() -> {
            ValueAnimator anim = ValueAnimator.ofFloat(start, 1f);
            anim.setDuration(300);
            anim.addUpdateListener(valueAnimator -> {
                float value = (float) valueAnimator.getAnimatedValue();
                view.setScaleX(value);
                view.setScaleY(value);
            });
            anim.start();
        });
    }

    public static void zoomIn(View view) {
        float start = 1.3f;
        view.setScaleX(start);
        view.setScaleY(start);
        view.post(() -> {
            ValueAnimator anim = ValueAnimator.ofFloat(start, 1f);
            anim.setDuration(300);
            anim.addUpdateListener(valueAnimator -> {
                float value = (float) valueAnimator.getAnimatedValue();
                view.setScaleX(value);
                view.setScaleY(value);
            });
            anim.start();
        });
    }

    public static void slideLeft(View view) {
        view.post(() -> {
            int width = view.getWidth();
            view.setTranslationX(width);
            ValueAnimator anim = ValueAnimator.ofFloat(width, 0f);
            anim.setDuration(300);
            anim.addUpdateListener(valueAnimator -> {
                float value = (float) valueAnimator.getAnimatedValue();
                view.setTranslationX(value);
            });
            anim.start();
        });
    }

    public static void slideRight(View view) {
        view.post(() -> {
            int width = view.getWidth() * -1;
            view.setTranslationX(width);
            ValueAnimator anim = ValueAnimator.ofFloat(width, 0f);
            anim.setDuration(300);
            anim.addUpdateListener(valueAnimator -> {
                float value = (float) valueAnimator.getAnimatedValue();
                view.setTranslationX(value);
            });
            anim.start();
        });
    }

}
