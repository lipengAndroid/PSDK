package com.geetol.mylibrary.Dialog;

import android.app.Activity;
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

public class GTXXDialog extends Dialog implements View.OnClickListener {


    private DialogInterFaceForAgreement listener;

    public GTXXDialog(DialogInterFaceForAgreement listener) {
        super(UserPrivacyUtils.activity, R.style.dialog_custom);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gtxx_dialog_agreement);
        setCancelable(false);//点击外部不可dismiss
        setCanceledOnTouchOutside(false);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.tv_agree).setOnClickListener(this);

        TextViewParser textViewParser = new TextViewParser();
        textViewParser.setTxt("欢迎使用本软件，我们非常重视保护您的隐私和个人信息，在您—使用本软件之前，请认真阅读", 14, "#333333");

        textViewParser.setTxt("《用户协议》", 14, Color.parseColor("#5cb412"), v -> WebActivity.start(GtSdk.contexts, "file:///android_asset/gtxx_user.html", "用户协议"));
        textViewParser.setTxt("及", 14, "#333333");
        textViewParser.setTxt("《隐私政策》", 14, Color.parseColor("#5cb412"), v -> WebActivity.start(GtSdk.contexts, "file:///android_asset/gtxx_ys.html", "隐私政策"));
        textViewParser.setTxt("您同意并接受全部条款后方可开始使用本软件", 14, "#333333");

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
