package com.geetol.mylibrary.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.geetol.mylibrary.Entity.KEY;
import com.gtdev5.geetolsdk.mylibrary.beans.ResultBean;
import com.gtdev5.geetolsdk.mylibrary.beans.UpdateBean;
import com.gtdev5.geetolsdk.mylibrary.callback.BaseCallback;
import com.gtdev5.geetolsdk.mylibrary.http.HttpUtils;
import com.gtdev5.geetolsdk.mylibrary.util.PermissionUtils;
import com.gtdev5.geetolsdk.mylibrary.util.ToastUtils;
import com.gtdev5.geetolsdk.mylibrary.util.Utils;

import okhttp3.Request;
import okhttp3.Response;

public class RegUtils {
    public interface RegUtilsOk {
        void ok(UpdateBean o);
    }

    private RegUtilsOk regUtilsOk;
    private static final int RESULT_ACTION_USAGE_ACCESS_SETTINGS = 1;
    private boolean oneStart;
    private Context activity;

    private String[] permiss10 = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private String[] permiss9 = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static RegUtils regUtils;

    public static RegUtils getMy() {
        return regUtils;
    }

    public static void init(Activity a) {
        if (regUtils == null) {
            regUtils = new RegUtils(a);
        }
    }

    public RegUtils(Activity activity) {
        this.activity = activity;
    }

    public void run(RegUtilsOk regUtilsOk) {
        this.regUtilsOk = regUtilsOk;
        oneStart = TextUtils.isEmpty(PreferencesUtil.getString(KEY.ONE_START, ""));
        initDatas();
    }

    private void initDatas() {

        if (Utils.isNetworkAvailable(activity)) { //如果有网请求权限
            checkPermissions();
        } else {
            if (!oneStart) {
                regUtilsOk.ok(null);
            } else {
                ToastUtils.showShortToast("网络异常！请开启网络后重新打开");
            }
        }
    }


    /**
     * 绑定设备逻辑
     */
    private void bindDevice() {
        HttpUtils.getInstance().postImeiExit(new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, ResultBean o) {
                if (o != null) {
                    if (o.isIssucc()) {
                        // 将IMEI值替换为Mac地址
                        replaceImei();
                    } else {
                        if (oneStart) {
                            regDevice();
                        } else {
                            getUpdate();
                        }
                    }
                }
            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {
            }
        });
    }


    /**
     * 替换IMEI
     */
    private void replaceImei() {
        HttpUtils.getInstance().postImeiReplace(new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {
            }

            @Override
            public void onFailure(Request request, Exception e) {
            }

            @Override
            public void onSuccess(Response response, ResultBean o) {
                if (o != null && o.isIssucc()) {
                    Log.e("TAG", "Mac替换Imei值成功！");
                    getUpdate();
                }
            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {
            }
        });
    }


    /**
     * 注册设备
     */
    private void regDevice() {
        HttpUtils.getInstance().postRegister(new BaseCallback<ResultBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {
                Log.e("TAG", "注册失败");
            }

            @Override
            public void onSuccess(Response response, ResultBean resultBean) {
                if (resultBean != null && resultBean.isIssucc()) {
                    Log.e("TAG", "注册成功！" + resultBean.isIssucc());
                    getUpdate();
                }
            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {
                Log.e("TAG", "注册失败" + e.toString());
            }
        });
    }


    /**
     * 更新数据
     */
    private void getUpdate() {
        HttpUtils.getInstance().postUpdate(new BaseCallback<UpdateBean>() {
            @Override
            public void onRequestBefore() {

            }

            @Override
            public void onFailure(Request request, Exception e) {

            }

            @Override
            public void onSuccess(Response response, UpdateBean o) {
                if (o != null && o.getIssucc()) {

                    regUtilsOk.ok(o);
                }
            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });
    }

    private void checkPermissions() {
        if (android.os.Build.VERSION.SDK_INT <= 28) {
            PermissionUtils.checkAndRequestMorePermissions(activity, permiss9, RESULT_ACTION_USAGE_ACCESS_SETTINGS, this::bindDevice);
        } else {
            PermissionUtils.checkAndRequestMorePermissions(activity, permiss10, RESULT_ACTION_USAGE_ACCESS_SETTINGS, this::bindDevice);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        boolean allOK = true;
        if (requestCode == RESULT_ACTION_USAGE_ACCESS_SETTINGS)
            for (int a = 0; a < permissions.length; a++) {

                if (grantResults[a] != 0) {
                    allOK = false;
                    break;
                }
            }
        if (allOK) {//全部同意
            bindDevice();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("温馨提示")
                    .setMessage("授予权限能使数据绑定手机哦，点击确定继续授权,取消可能会导致功能不正常")
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        checkPermissions();
                    })
                    .setNegativeButton("取消", (dialogInterface, i) -> {
                        bindDevice();
                    }).create().show();
        }
    }
}
