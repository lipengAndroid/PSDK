package com.geetol.mylibrary.Utils;

import android.app.Activity;

import com.geetol.mylibrary.Dialog.DunJiaDialog;
import com.geetol.mylibrary.Dialog.GTXXDialog;
import com.geetol.mylibrary.Dialog.HengHaDialog;
import com.geetol.mylibrary.Dialog.JRYDialog;
import com.geetol.mylibrary.Dialog.QingQuanDialog;
import com.geetol.mylibrary.Dialog.ZiYiDialog;
import com.geetol.mylibrary.InterFace.DialogInterFaceForAgreement;
import com.gtdev5.geetolsdk.mylibrary.util.SpUtils;

import static com.geetol.mylibrary.Entity.KEY.ONE_START;

/**
 * 用户协议 隐私政策弹窗 工具类111111
 */
public class UserPrivacyUtils implements DialogInterFaceForAgreement {

    public static Activity activity;
    private RegUtils.RegUtilsOk face;

    public void show(Activity a, RegUtils.RegUtilsOk f) {
        face = f;
        activity = a;
        RegUtils.init(a);
        if (!SpUtils.getInstance().getString(ONE_START).equals("")) {
            RegUtils.getMy().run(face);
        } else if (GtSdk.contexts != null) {
            switch (GtSdk.contexts.getPackageName()) {
                case "com.dunjiakj.makename"://遁甲
                case "com.geetol.fond.dream":
                    new DunJiaDialog(this).show();
                    break;
                case "com.junruy.makename":// 君如意
                    new JRYDialog(this).show();
                    break;
                case "com.qqkj66.makename"://清泉
                case "com.qqkj66.easysleep":
                    new QingQuanDialog(this).show();
                    break;
                case "com.ziyi18.geemakename"://紫伊
                case "com.com.ziyi18.goodsleep":
                    new ZiYiDialog(this).show();
                    break;
                case "com.gtdev5.indulgelock"://哼哈
                    new HengHaDialog(this).show();
                    break;
                case "com.geetol.sleep":// 集拓信息
                    new GTXXDialog(this).show();
                    break;
                case "com.gtdev5.app_lock"://未找到公司
                case "com.geetol.passwordmanage":
                    break;

            }
        }

    }

    @Override
    public void ok() {
        RegUtils.getMy().run(face);
    }
}
