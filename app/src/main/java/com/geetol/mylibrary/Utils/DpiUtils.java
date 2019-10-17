package com.geetol.mylibrary.Utils;


public class DpiUtils {

    public static int dip2px(int dp) {
        final float scale = GtSdk.contexts.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
