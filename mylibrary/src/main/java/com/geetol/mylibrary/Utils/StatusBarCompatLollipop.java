package com.geetol.mylibrary.Utils;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.ref.WeakReference;


/**
 * After Lollipop use system method.
 * Created by qiu on 8/27/16.
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class StatusBarCompatLollipop {


    /**
     * set StatusBarColor
     * <p>
     * 1. set Flags to call setStatusBarColor
     * 2. call setSystemUiVisibility to clear translucentStatusBar's Flag.
     * 3. set FitsSystemWindows to false
     */
    static void setStatusBarColor(Activity activity, int statusColor) {
        Window window = activity.getWindow();

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(statusColor);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

        ViewGroup mContentView = window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            mChildView.setFitsSystemWindows(false);
            mChildView.requestApplyInsets();
        }
    }

    /**
     * translucentStatusBar(full-screen)
     * <p>
     * 1. set Flags to full-screen
     * 2. set FitsSystemWindows to false
     *
     * @param hideStatusBarBackground hide statusBar's shadow
     */
    static void translucentStatusBar(Activity activity, boolean hideStatusBarBackground) {
        Window window = activity.getWindow();

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (hideStatusBarBackground) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        }

        ViewGroup mContentView = window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            mChildView.setFitsSystemWindows(false);
            mChildView.requestApplyInsets();
        }
    }


    /**
     * use ValueAnimator to change statusBarColor when using collapsingToolbarLayout
     */
    private static void startColorAnimation(int startColor, int endColor, long duration, final WeakReference<Window> windowWeakReference) {
        if (sAnimator != null) {
            sAnimator.cancel();
        }
        sAnimator = ValueAnimator.ofArgb(startColor, endColor)
                .setDuration(duration);
        sAnimator.addUpdateListener(valueAnimator -> {
            final Window window = windowWeakReference.get();
            if (window != null) {
                window.setStatusBarColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        sAnimator.start();
    }

    private static ValueAnimator sAnimator;
}
