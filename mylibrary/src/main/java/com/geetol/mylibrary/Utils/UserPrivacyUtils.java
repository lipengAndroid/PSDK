package com.geetol.mylibrary.Utils;

import android.annotation.SuppressLint;
import android.app.Activity;


import androidx.annotation.LayoutRes;

import com.geetol.mylibrary.Dialog.DefDialog;
import com.geetol.mylibrary.Dialog.DunJiaDialog;
import com.geetol.mylibrary.Dialog.GTXXDialog;
import com.geetol.mylibrary.Dialog.HengHaDialog;
import com.geetol.mylibrary.Dialog.JRYDialog;
import com.geetol.mylibrary.Dialog.PubDialog;
import com.geetol.mylibrary.Dialog.QingQuanDialog;
import com.geetol.mylibrary.Dialog.ZiYiDialog;
import com.geetol.mylibrary.InterFace.DialogInterFaceForAgreement;
import com.gtdev5.geetolsdk.mylibrary.util.SpUtils;

import static com.geetol.mylibrary.Entity.KEY.ONE_START;

/**
 * 用户协议 隐私政策弹窗 工具类
 */
public class UserPrivacyUtils implements DialogInterFaceForAgreement {

    @SuppressLint("StaticFieldLeak")
    public static Activity activity;
    private RegUtils.RegUtilsOk face;


    public void show(Activity a, @LayoutRes int lays, String usr, String ys, RegUtils.RegUtilsOk f) {

        face = f;
        activity = a;
        RegUtils.init(a);
        if (!SpUtils.getInstance().getString(ONE_START).equals("")) {
            RegUtils.getMy().run(face);
        } else if (GtSdk.contexts != null) {
            new PubDialog(lays, usr, ys, this);
        }
    }

    public void show(Activity a, RegUtils.RegUtilsOk f) {
        face = f;
        activity = a;
        RegUtils.init(a);
        if (!SpUtils.getInstance().getString(ONE_START).equals("")) {
            RegUtils.getMy().run(face);
        } else if (GtSdk.contexts != null) {
            switch (GtSdk.contexts.getPackageName()) {
                case "com.dunjiakj.lgwx"://遁甲
                case "com.dunjiakj.shortvideo":
                case "com.gtdev5.caller_flashover":
                case "com.dunjiakj.fakecall":
                case "com.dunjiakj.djlock":
                case "com.dunjiakj.newqqlock":
                case "com.gtdev5.qqlock":
                case "com.dunjiakj.children_stories":
                case "com.dunjiakj.yjzfks":
                case "com.geetol.fond.dream":
                case "com.dunjiakj.makename":
                    new DunJiaDialog(this).show();
                    break;
                case "com.junruy.virtualapp"://君如意
                case "com.junruy.we_king_of_funs":
                case "com.jry.watercamera":
                case "com.softwarelock.call_flash":
                case "com.junruy.fakecall":
                case "com.getv5.softwarelock":
                case "com.junruy.wechat_creater":
                case "com.junruy.geetol_yjzf":
                case "com.junruy.cgfyj":
                case "com.junruy.makename":
                    new JRYDialog(this).show();
                    break;
                case "com.qqkj66.virtualapp"://清泉
                case "com.qqkj66.virtuallocation":
                case "com.qqkj66.shortvideo":
                case "com.qqkj66.watercamera":
                case "com.gtdev5.wx_applock":
                case "com.gtdev5.wsjtw":
                case "com.qqkj66.easysleep":
                case "com.qqkj66.makename":
                    new QingQuanDialog(this).show();
                    break;
                case " com.ziyi18.wxxh"://紫伊
                case "com.ziyi18.shortvideo":
                case "com.ziyi.watercamera":
                case "com.gtdev5.call_flash4":
                case "com.ziyi18.babytale/com.ziyi18.bbgs":
                case "com.ziyi18.geetol_yjzf":
                case "com.com.ziyi18.goodsleep":
                case "com.ziyi18.geemakename":
                    new ZiYiDialog(this).show();
                    break;
                case "com.hehax.wzxg"://哼哈
                case "com.hehax.wesourceutils":
                case "com.hehax.shortvideo":
                case "com.hehax.fakecall":
                case "com.gtdev5.indulgelock":
                case "com.hehax.creater":
                case "com.hehax.chat_create_shot":
                case "com.hehax.thgs":
                case "com.hehax.cygs/com.hehax.cygsdq":
                    new HengHaDialog(this).show();
                    break;
                case "com.geetol.sleep":// 集拓信息
                    new GTXXDialog(this).show();
                    break;
                case "com.gtdev5.app_lock"://未找到公司
                case "com.geetol.passwordmanage":
                    new DefDialog(this).show();
                    break;
                default:
                    new DefDialog(this).show();
                    break;

            }
        }

    }

    @Override
    public void ok() {
        RegUtils.getMy().run(face);
    }
}
