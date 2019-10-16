package com.geetol.mylibrary.Dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.geetol.mylibrary.InterFace.DialogInterFaceForAgreement;
import com.geetol.mylibrary.R;
import com.geetol.mylibrary.Utils.GtSdk;
import com.geetol.mylibrary.Utils.MResource;
import com.geetol.mylibrary.Utils.TextViewParser;
import com.geetol.mylibrary.Utils.UserPrivacyUtils;
import com.geetol.mylibrary.View.WebActivity;


/**
 * Created by ZL on 2019/9/25
 * <p>
 * 用户协议对话框
 */

public class DunJiaDialog extends Dialog implements View.OnClickListener {


    private DialogInterFaceForAgreement listener;

    public DunJiaDialog(DialogInterFaceForAgreement listener) {
        super(UserPrivacyUtils.activity, R.style.dialog_custom);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dun_jia_dialog_agreement);
        setCancelable(false);//点击外部不可dismiss
        setCanceledOnTouchOutside(false);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_agree).setOnClickListener(this);

        TextViewParser textViewParser = new TextViewParser();
        textViewParser.setTxt("尊敬的用户欢迎使用" + GtSdk.contexts.getResources().getString(R.string.app_name)
                + "\n在您使用前请仔细阅读", 14, "#333333");

        textViewParser.setTxt("《注册协议》", 14, Color.parseColor("#018FFD"), v -> WebActivity.start(GtSdk.contexts, "file:///android_asset/dj_user.html", "注册协议"));
        textViewParser.setTxt("和", 14, "#333333");
        textViewParser.setTxt("《隐私政策》", 14, Color.parseColor("#018FFD"), v -> WebActivity.start(GtSdk.contexts, "file:///android_asset/dj_ys.html", "隐私政策"));
        textViewParser.setTxt("我们将严格遵守您同意的各项条款使用您的信息，以便为您提供更好的服务。", 14, "#333333");


        textViewParser.parse(findViewById(R.id.data));

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == MResource.getIdByName("id", "tv_cancel")) {
            dismiss();
        }
        if (v.getId() == MResource.getIdByName("id", "tv_agree")) {
            listener.ok();
            dismiss();
        }
    }
}
