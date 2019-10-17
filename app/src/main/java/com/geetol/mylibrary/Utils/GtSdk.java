package com.geetol.mylibrary.Utils;

import android.content.Context;

import com.gtdev5.geetolsdk.mylibrary.initialization.GeetolSDK;

public class GtSdk {

    public static Context contexts;

    public static String api = "";

    public static void init(Context context) {
        switch (context.getPackageName()) {

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
                api = "duanjia.dunjiakj.com";
                break;

            case " com.ziyi18.wxxh"://紫伊
            case "com.ziyi18.shortvideo":
            case "com.ziyi.watercamera":
            case "com.gtdev5.call_flash4":
            case "com.ziyi18.babytale/com.ziyi18.bbgs":
            case "com.ziyi18.geetol_yjzf":
            case "com.com.ziyi18.goodsleep":
            case "com.ziyi18.geemakename":

                api = "ziyi.ziyi18.com";
                break;

            case "com.hjkj66.virtualapp"://海景
            case "com.hjk66.xndw":
            case "com.hjkj66.weichathongbao":
            case "com.gtdev5.caller_flash3":
            case "com.hjkj66.fakecall":
            case "com.gtdev5.zgjt":
            case "com.hjkj66.yjzf":

                api = "haijing.hjkj66.com";
                break;
            case "com.qqkj66.virtualapp"://清泉
            case "com.qqkj66.virtuallocation":
            case "com.qqkj66.shortvideo":
            case "com.qqkj66.watercamera":
            case "com.gtdev5.wx_applock":
            case "com.gtdev5.wsjtw":
            case "com.qqkj66.easysleep":
            case "com.qqkj66.makename":
                api = "qingquan.qqkj66.com";
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


                api = "junruyi.junruy.com";
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


                api = "heha.hehax.com";
                break;

            case "com.xindhe68.virtuallocation":
            case "com.xindihe.we_king_of_funs":
            case "com.xindh68.hongbaozhushou":
            case "com.xindihe.watercamera":
            case "com.xindihe.zgjt2":
                api = "xindihe.xindihe.cn";
                break;
            case "com.geetol.sleep"://集拓信息
                api = "xinxi.gtxinxi.cn";
                break;


        }

        api = "http://" + api + "/app/";
        GeetolSDK.init(context, api);
        contexts = context;
    }
}
