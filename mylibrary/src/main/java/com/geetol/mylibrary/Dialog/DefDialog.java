package com.geetol.mylibrary.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import com.geetol.mylibrary.InterFace.DialogInterFaceForAgreement;
import com.geetol.mylibrary.R;
import com.geetol.mylibrary.Utils.GtSdk;
import com.geetol.mylibrary.Utils.TextViewParser;
import com.geetol.mylibrary.Utils.UserPrivacyUtils;
import com.geetol.mylibrary.View.WebActivity;

import static com.geetol.mylibrary.Utils.GtSdk.user_address_v;
import static com.geetol.mylibrary.Utils.GtSdk.ys_address_v;


public class DefDialog extends Dialog {

    /**
     * 默认弹窗
     */
    private DialogInterFaceForAgreement listener;

    public DefDialog(DialogInterFaceForAgreement listener) {
        super(UserPrivacyUtils.activity, R.style.dialog_custom);
        this.listener = listener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jry_dialog_agreement);
        setCancelable(false);//点击外部不可dismiss
        setCanceledOnTouchOutside(false);
        findViewById(R.id.tv_cancel).setOnClickListener(view -> {
            UserPrivacyUtils.activity.finish();
            dismiss();
        });
        findViewById(R.id.tv_agree).setOnClickListener(view -> {
            listener.ok();
            dismiss();
        });

        TextViewParser textViewParser = new TextViewParser();
        textViewParser.setTxt("尊敬的用户欢迎使用" + GtSdk.contexts.getResources().getString(R.string.app_name)
                + "\n在您使用前请仔细阅读", 14, "#333333");

        textViewParser.setTxt("《注册协议》", 14, Color.parseColor("#018FFD"), v -> WebActivity.start(GtSdk.contexts, user_address_v, "注册协议"));
        textViewParser.setTxt("和", 14, "#333333");
        textViewParser.setTxt("《隐私政策》", 14, Color.parseColor("#018FFD"), v -> WebActivity.start(GtSdk.contexts, ys_address_v, "隐私政策"));
        textViewParser.setTxt("我们将严格遵守您同意的各项条款使用您的信息，以便为您提供更好的服务。", 14, "#333333");


        textViewParser.parse(findViewById(R.id.data));

    }


}
