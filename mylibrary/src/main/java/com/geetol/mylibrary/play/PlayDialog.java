package com.geetol.mylibrary.play;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;

import com.alipay.sdk.app.PayTask;
import com.geetol.mylibrary.Entity.AppDataModel;
import com.geetol.mylibrary.R;
import com.geetol.mylibrary.Utils.TalenHttpUtils;
import com.geetol.mylibrary.databinding.WxAliDialogBinding;
import com.gtdev5.geetolsdk.mylibrary.beans.ApliyBean;
import com.gtdev5.geetolsdk.mylibrary.beans.PayResult;
import com.gtdev5.geetolsdk.mylibrary.beans.UpdateBean;
import com.gtdev5.geetolsdk.mylibrary.callback.BaseCallback;
import com.gtdev5.geetolsdk.mylibrary.http.HttpUtils;
import com.gtdev5.geetolsdk.mylibrary.util.ToastUtils;
import com.gtdev5.geetolsdk.mylibrary.util.Utils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;
import java.util.Objects;

import okhttp3.Request;
import okhttp3.Response;

public class PlayDialog {

    public interface PlayOkInterface {
        void ok();
    }

    private PlayOkInterface playOk;

    public void show(Activity context, int vipId, PlayOkInterface playOk) {
        this.playOk = playOk;
        if (AppDataModel.getInstance() != null &&
                AppDataModel.getInstance().getGds() != null &&
                AppDataModel.getInstance().getGds().size() > 0) {

            if (AppDataModel.getInstance().getGds().get(0).getPayway().equals("[1]")) {//微信
                wxPlay(context);
            } else if (AppDataModel.getInstance().getGds().get(0).getPayway().equals("[2]")) {//支付宝
                pay(AppDataModel.getInstance().getGds().get(vipId).getGid(), context);
            } else if (AppDataModel.getInstance().getGds().get(0).getPayway().contains("[1]")
                    && AppDataModel.getInstance().getGds().get(0).getPayway().contains("[2]")) {
                Dialog dialog = new Dialog(context, R.style.dialog_custom);
                WxAliDialogBinding view = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.wx_ali_dialog, null, false);
                dialog.setContentView(view.getRoot());
                Objects.requireNonNull(dialog.getWindow()).setGravity(Gravity.BOTTOM);

                WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setAttributes(layoutParams);
                view.ali.setOnClickListener(v -> pay(AppDataModel.getInstance().getGds().get(vipId).getGid(), context));
                view.wx.setOnClickListener(v -> wxPlay(context));
                dialog.show();

            }
        } else {
            ToastUtils.showLongToast("goods信息为空");
        }
    }

    private void wxPlay(Activity context) {

        final IWXAPI msgApi = WXAPIFactory.createWXAPI(context, "wxd930ea5d5a258f4f");
        PayReq request = new PayReq();
        request.appId = "wxd930ea5d5a258f4f";
        request.partnerId = "1900000109";
        request.prepayId = "1101000000140415649af9fc314aa427";
        request.packageValue = "Sign=WXPay";
        request.nonceStr = "1101000000140429eb40476f8896f4c9";
        request.timeStamp = "1398746574";
        request.sign = "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
        msgApi.sendReq(request);
//        HttpUtils.getInstance().PostOdOrder(1, model.getGds().get(0).getGid(), 0, 1, new BaseCallback<OdResultBean>() {
//            @Override
//            public void onRequestBefore() {
//
//            }
//
//            @Override
//            public void onFailure(Request request, Exception e) {
//
//            }
//
//            @Override
//            public void onSuccess(Response response, OdResultBean o) {
//                if (o != null && !o.isIssucc()) {
//                    ToastUtils.showShortToast(o.getMsg());
//                    return;
//                }
//                IWXAPI api = WXAPIFactory.createWXAPI(context, o.getAppid(), false);
//                api.registerApp(o.getAppid());//填写自己的APPID，注册本身APP
//                PayReq req = new PayReq();//PayReq就是订单信息对象//给req对象赋值
//                req.appId = o.getAppid();//APPID
//                req.partnerId = o.getPartnerId();//商户号
//                req.prepayId = o.getPrepayid();  //预订单id
//                req.nonceStr = o.getNonce_str();//随机数
//                req.timeStamp = o.getTimestramp();//时间戳
//                req.packageValue = o.getPackage_str();//固定值Sign=WXPay
//                req.sign = o.getSign();//签名
//                //Log.e("zeoy","服务器签名字符串："+o.getSign());
//                api.sendReq(req);//将订单信息对象发送给微信服务器，即发送支付请求
//
//            }
//
//            @Override
//            public void onError(Response response, int errorCode, Exception e) {
//
//            }
//        });


    }

    private static final int SDK_PAY_FLAG = 1;

    /**
     * 支付宝购买
     */
    private void pay(int Gid, Activity context) {


        if (Utils.isNetworkAvailable(context)) {
            TalenHttpUtils.PostOrder(1, Gid, 0, 2, new BaseCallback<ApliyBean>() {
                @Override
                public void onRequestBefore() {
//                    OnRefreshStart();
                }

                @Override
                public void onFailure(Request request, Exception e) {
                    ToastUtils.showLongToast("链接超时");
//                    OnRefreshFinish();
                }

                @Override
                public void onSuccess(Response response, ApliyBean apliyBean) {
//                    OnRefreshFinish();
                    if (response.isSuccessful()) {
                        if (apliyBean != null) {
                            if (!apliyBean.isIssucc()) {
                                ToastUtils.showLongToast(apliyBean.getMsg());
                                return;
                            }
                            Runnable runnable = () -> {
                                PayTask alipay = new PayTask(context);
                                Map<String, String> map = alipay.payV2(apliyBean.getPackage_str(), true);
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                payResult = new PayResult(map);
                                mHandler.sendMessage(msg);
                            };

                            Thread payThread = new Thread(runnable);
                            payThread.start();
                        }
                    }
                }

                @Override
                public void onError(Response response, int errorCode, Exception e) {
//                    OnRefreshFinish();
                }
            });
        } else {
            ToastUtils.showLongToast("网络连接失败，请先开启网络");
        }
    }

    private PayResult payResult;

    private Handler mHandler = new Handler(message -> {
        if (message.what == SDK_PAY_FLAG) {
            switch (payResult.getResultStatus()) {
                case "9000":
                    upData();
                    break;
                case "8000":
                    ToastUtils.showLongToast("正在处理中");
                    break;
                case "4000":
                    ToastUtils.showLongToast("订单支付失败");
                    break;
                case "5000":
                    ToastUtils.showLongToast("重复请求");
                    break;
                case "6001":
                    ToastUtils.showLongToast("已取消支付");
                    break;
                case "6002":
                    ToastUtils.showLongToast("网络连接出错");
                    break;
                case "6004":
                    ToastUtils.showLongToast("正在处理中");
                    break;
                default:
                    ToastUtils.showLongToast("支付失败");
                    break;
            }
        }
        return false;
    });

    private void upData() {
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
                    AppDataModel.getInstance().saveUpdate(o);
                    ToastUtils.showLongToast("数据更新成功");
                    if (playOk != null) {
                        playOk.ok();
                    }
                }
            }

            @Override
            public void onError(Response response, int errorCode, Exception e) {

            }
        });

    }

}
