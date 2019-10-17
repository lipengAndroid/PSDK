package com.geetol.mylibrary.Utils;

import com.geetol.mylibrary.Entity.Constants;
import com.gtdev5.geetolsdk.mylibrary.callback.BaseCallback;
import com.gtdev5.geetolsdk.mylibrary.contants.API;
import com.gtdev5.geetolsdk.mylibrary.http.HttpUtils;
import com.gtdev5.geetolsdk.mylibrary.util.MapUtils;
import com.gtdev5.geetolsdk.mylibrary.util.SpUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Info     ： Create by Zeoy
 * Introduce：故事软件新增接口
 * Date     ： 2018/12/14
 */
public class TalenHttpUtils {
    private static final String COMMON_URL = "http://duanjia.dunjiakj.com/app/";

    private static final String GET_STORY = "story.get_story";             //获取故事
    private static final String GET_VAR_CODE = "story.getvarcode";         //获取验证码
    private static final String REG = "story.reg";                         //注册
    private static final String LOGIN = "story.login";                     //登录
    private static final String GET_RELATION = "story.get_relation";       //获取分类
    private static final String SET_HEADIMG = "story.set_headimg";         //设置用户头像
    private static final String SET_PLAYLOG = "story.set_playlog";         //添加播放记录
    private static final String GET_USERINFO = "story.get_userinfo";       //获取用户的vip及基本信息，原uipdata接口的vip信息失效
    private static final String GET_USERLOG = "story.get_userlog";         //获取播放记录（天数）
    private static final String GET_PLAYLOG = "story.get_playlog";         //获取播放记录故事列表
    private static final String SET_COLECTION = "story.set_shoucang";      //设置或者取消收藏
    private static final String GET_COLECTION = "story.get_shoucang";      //获取收藏的故事列表
    private static final String GET_STORY_RAND = "story.get_story_rand";   //随便听听接口，获取随机故事
    private static final String GET_COLECTION_ALLID  = "story.get_shoucang_allid";    //获取收藏的所有id
    private static final String GET_STORY_NAME_RAND = "story.get_story_name_rand";    //获取故事名字 随机

    private static final String GET_DOCUMENT_LIST = "get_helpword";    //获取故事名字 随机

    //添加播放记录、获取用户的vip及基本信息、获取播放记录、获取播放记录故事列表、设置或者取消收藏、获取收藏的故事列表、随便听听接口，获取随机故事、获取收藏的所有id

    /**
     * 获取助眠文档列表
     *
     * @param page     当前的页面
     * @param limit    每页的限制
     * @param title    分类名称  --助眠文档
     * @param callback 回调
     */
    public static void GetDocumentList(int page, int limit, String title, BaseCallback callback) {
        Map<String, String> parm = MapUtils.getCurrencyMap();
        parm.put("page", String.valueOf(page));
        parm.put("limit", String.valueOf(limit));
        parm.put("gate", title);
        HttpUtils.getInstance().post(COMMON_URL + GET_DOCUMENT_LIST, parm, callback);
    }

    /**
     * 随机获取故事
     * @param length
     * @param callback
     */
    public static void PostGetStoryNameRand(int length, BaseCallback callback){
        Map<String, String> parm = MapUtils.getCurrencyMap();
        parm.put("length", String.valueOf(length));
        HttpUtils.getInstance().post(COMMON_URL + GET_STORY_NAME_RAND, parm, callback);
    }

    /**
     * 获取已收藏的故事id列表
     * @param callback
     */
    public static void PostGetColectionAllid (BaseCallback callback){
        HttpUtils.getInstance().post(COMMON_URL+GET_COLECTION_ALLID,getLoginMap(),callback);
    }

    /**
     * 随机获取故事接口
     *
     * @param length   获取故事
     * @param callback
     */
    public static void PostGetStoryRand(int length, BaseCallback callback) {
        Map<String, String> parm = MapUtils.getCurrencyMap();
        parm.put("length", String.valueOf(length));
        HttpUtils.getInstance().post(COMMON_URL + GET_STORY_RAND, parm, callback);
    }

    /**
     * 获取收藏列表
     *
     * @param page     当前的页面
     * @param limit    每页的个数
     * @param callback
     */
    public static void PostGetColection(int page, int limit, BaseCallback callback) {
        Map<String, String> parm = getLoginMap();
        parm.put("page", String.valueOf(page));
        parm.put("limit", String.valueOf(limit));
        HttpUtils.getInstance().post(COMMON_URL + GET_COLECTION, parm, callback);
    }


    /**
     * 故事支付接口
     * @param type
     * @param pid
     * @param amount
     * @param pway
     * @param callback
     */
    public static void PostOrder(int type, int pid, float amount, int pway, BaseCallback callback){
        Map<String, String> map = new HashMap<>();
        map.putAll(getLoginMap());
        map.put("type", String.valueOf(type));
        map.put("pid", String.valueOf(pid));
        map.put("amount", String.valueOf(amount));
        map.put("pway", String.valueOf(pway));
        HttpUtils.getInstance().post(COMMON_URL+ API.ORDER_OD,map,callback);
    }


    /**
     * 设置收藏或者取消收藏
     *
     * @param stroy_id
     * @param callback
     */
    public static void PostSetColection(int stroy_id, BaseCallback callback) {
        Map<String, String> parm = getLoginMap();
        parm.put("stroy_id", String.valueOf(stroy_id));
        HttpUtils.getInstance().post(COMMON_URL + SET_COLECTION, parm, callback);
    }

    /**
     * 获取故事播放记录
     *
     * @param page     当前的页面
     * @param limit    每页的个数
     * @param callback
     */
    public static void PostGetPlayLog(int page, int limit, BaseCallback callback) {
        Map<String, String> parm = getLoginMap();
        parm.put("page", String.valueOf(page));
        parm.put("limit", String.valueOf(limit));
        HttpUtils.getInstance().post(COMMON_URL + GET_PLAYLOG, parm, callback);
    }

    /**
     * 获取故事
     *
     * @param page     当前的页面
     * @param limit    每页的限制
     * @param rel      专题或者分类的id
     * @param title    包含的标题
     * @param odr     排序12345,11,12,13,14,15
     * @param callback 回调
     */
    public static void PostGetStory(int page, int limit, int rel, String title, int odr, int apptype, BaseCallback callback) {
        Map<String, String> parm = MapUtils.getCurrencyMap();
        parm.put("page", String.valueOf(page));
        parm.put("limit", String.valueOf(limit));
        parm.put("rel", String.valueOf(rel));
        parm.put("title", title);
        parm.put("odr", String.valueOf(odr));
        parm.put("apptype", String.valueOf(apptype));
        HttpUtils.getInstance().post(COMMON_URL + GET_STORY, parm, callback);
    }

  /*  *//**
     * @param page
     * @param limit
     * @param rel
     * @param title
     * @param ord      为1 即为时间倒序
     * @param vip      为1就只查询价格大于0的故事
     * @param callback
     *//*
    public static void PostGetStory(int page, int limit, int rel, String title, int ord, int vip, BaseCallback callback) {
        Map<String, String> parm = MapUtils.getCurrencyMap();
        parm.put("page", String.valueOf(page));
        parm.put("limit", String.valueOf(limit));
        parm.put("rel", String.valueOf(rel));
        parm.put("ord", String.valueOf(ord));
        parm.put("vip", String.valueOf(vip));
        parm.put("title", title);
        HttpUtils.getInstance().post(COMMON_URL + GET_STORY, parm, callback);
    }*/


    /**
     * 获取验证码
     *
     * @param phone                手机号
     * @param callback             回调
     */
    public static void PostGetVarCode(String phone, BaseCallback callback) {
        Map<String, String> map = MapUtils.getCurrencyMap();
        map.put("tel", phone);
        HttpUtils.getInstance().post(COMMON_URL + GET_VAR_CODE, map, callback);
    }

    /**
     * @param tel      电话
     * @param name     用户名
     * @param pwd      密码
     * @param code     验证码
     * @param ckey     验证码令牌
     * @param callback 回调
     */
    public static void PostReg(String tel, String name, String pwd, String code, String ckey, BaseCallback callback) {
        Map<String, String> map = MapUtils.getCurrencyMap();
        map.put("tel", tel);
        map.put("name", name);
        map.put("pwd", pwd);
        map.put("code", code);
        map.put("ckey", ckey);
        HttpUtils.getInstance().post(COMMON_URL + REG, map, callback);
    }

    /**
     * 登录接口
     * @param user     用户名
     * @param pwd      密码
     * @param callback 回调
     */
    public static void PostLogin(String user, String pwd, BaseCallback callback) {
        Map<String, String> map = MapUtils.getCurrencyMap();
        map.put("user", user);
        map.put("pwd", pwd);
        HttpUtils.getInstance().post(COMMON_URL + LOGIN, map, callback);
    }


    /**
     * 获取专题或者分类
     * @param type     获取类型：0：全部  1：专题  2：分类  3:榜单   4：其他（今日推荐）
     * @param callback 回调
     */
    public static void PostGetRelation(int type, BaseCallback callback) {
        Map<String, String> map = MapUtils.getCurrencyMap();
        map.put("type", String.valueOf(type));
        HttpUtils.getInstance().post(COMMON_URL + GET_RELATION, map, callback);
    }


    /**
     * 保存登录成功的信息（部分接口需要填写登录信息才能使用）
     * @param loginDataBean
     */
   /* public static void savaLoginInfo(LoginDataBean loginDataBean) {
        if (loginDataBean != null) {
            SpUtils.getInstance().putInt(Constants.USER_ID, loginDataBean.getUser_id());
            SpUtils.getInstance().putString(Constants.UKEY, loginDataBean.getUkey());
            SpUtils.getInstance().putBoolean(Constants.ISLOGIN, true);
        }

    }*/


    /**
     * 设置用户头像
     * @param imgbase64 图片base64
     * @param callback  回调
     */
    public static void PostSetHeadIMG(String imgbase64, BaseCallback callback) {
        Map<String, String> map = getLoginMap();
        map.put("img", String.valueOf(imgbase64));
        HttpUtils.getInstance().post(COMMON_URL + SET_HEADIMG, map, callback);
    }

    /**
     * 获取播放记录
     * @param callback
     */
    public static void PostGetUserLog(BaseCallback callback) {
        HttpUtils.getInstance().post(COMMON_URL + GET_USERLOG, getLoginMap(), callback);
    }

    /**
     * 获取用户信息
     * @param callback
     */
    public static void PostGetUserInfo(BaseCallback callback) {
        HttpUtils.getInstance().post(COMMON_URL + GET_USERINFO, getLoginMap(), callback);
    }

    /**
     * 添加故事播放记录
     * @param storyid
     * @param callback
     */
    public static void PostSetPlayLog(int storyid, BaseCallback callback) {
        Map<String, String> map = getLoginMap();
        map.put("stroy_id", String.valueOf(storyid));
        HttpUtils.getInstance().post(COMMON_URL + SET_PLAYLOG, map, callback);
    }


    /**
     * 获取需要登录信息的基本参数map
     * @return
     */
    public static Map<String, String> getLoginMap() {
        Map<String, String> map = MapUtils.getCurrencyMap();
        int user_id = SpUtils.getInstance().getInt(Constants.USER_ID,0);
        map.put(Constants.USER_ID, String.valueOf(user_id));
        map.put(Constants.UKEY, String.valueOf(SpUtils.getInstance().getString(Constants.UKEY, "")));
        return map;
    }



}
