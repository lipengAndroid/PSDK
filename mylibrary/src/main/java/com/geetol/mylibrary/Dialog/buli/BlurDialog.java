package com.geetol.mylibrary.Dialog.buli;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.geetol.mylibrary.R;

import static com.geetol.mylibrary.Utils.GtSdk.Gauss;


public class BlurDialog extends Dialog {

    private BlurView mBlurView;

    public BlurDialog(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BlurDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    protected BlurDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init(context);
    }

    private void init(Context context) {
        Activity activity = getActivityFromContext(context);
        if (activity == null || !Gauss) {
            Log.e("BlurDialog", "context is not a Activity Context......");
            return;
        }
        ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
        mBlurView = decorView.findViewById(R.id.blur_dialog_bg);
        if (mBlurView == null) {
            mBlurView = new BlurView(activity);
            mBlurView.setId(R.id.blur_dialog_bg);
            mBlurView.setAlpha(0f);
            decorView.addView(mBlurView, new ViewGroup.LayoutParams(1, 1));
        }
    }

    private Activity getActivityFromContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mBlurView != null && Gauss) {
            mBlurView.blur();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mBlurView != null && Gauss) {
            mBlurView.show();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mBlurView != null && Gauss) {
            mBlurView.hide();
        }
    }

}
