package com.geetol.mylibrary.Entity;



import com.google.gson.Gson;
import com.gtdev5.geetolsdk.mylibrary.beans.Ads;
import com.gtdev5.geetolsdk.mylibrary.beans.Gds;
import com.gtdev5.geetolsdk.mylibrary.beans.Swt;
import com.gtdev5.geetolsdk.mylibrary.beans.UpdateBean;
import com.gtdev5.geetolsdk.mylibrary.beans.Vip;
import com.gtdev5.geetolsdk.mylibrary.util.CPResourceUtils;
import com.gtdev5.geetolsdk.mylibrary.util.GsonUtils;
import com.gtdev5.geetolsdk.mylibrary.util.SpUtils;
import com.gtdev5.geetolsdk.mylibrary.util.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/********************
 *  Created by ：  Zeoy 
 *         Date：  2018\4\12 0012.
 * Introduction：   应用的基本类保存
 *********************/

public class AppDataModel {
    private static final String APPDATA = "appconfig";
    private static AppDataModel instance;
    private UpdateBean data;
    private Gson gson;
    private static final String HAD_REG = "had_reg";
    private static final String START_TIME = "start_time";
    private static final String END_TIME = "end_time";

    private static final String TALENS_COLECTION = "talens_colection";
    private static final String TALENS_RELATION = "talens_relation";
    private static final String TALENS_SEARCH_HISTORY = "talens_search_history";
    private static final String SPLIT = "&#&";

    private static final String TALENS_USERDATA = "talens_userdata";
    private static final String TALENS_PLAYLOG = "talens_playlog";
    private int historycount = 10;
    private boolean IsNoVipStillTip;

/*    public UserDataBean getUserDataBean() {
        return userDataBean;
    }*/
    public boolean isNoVipStillTip() {
        return IsNoVipStillTip;
    }

    public UpdateBean getData() {
        return data;
    }

    public void setData(UpdateBean data) {
        this.data = data;
    }

    public void setNoVipStillTip(boolean noVipStillTip) {
        IsNoVipStillTip = noVipStillTip;
        SpUtils.getInstance().putBoolean("no_vip_still_tipe", noVipStillTip);
    }

    private AppDataModel() {
        gson = new Gson();
        data = gson.fromJson(SpUtils.getInstance().getString(APPDATA), UpdateBean.class);
    }

    public static AppDataModel getInstance() {
        if (instance == null) {
            synchronized (AppDataModel.class) {
                if (instance == null) {
                    instance = new AppDataModel();
                }
            }
        }
        return instance;
    }




    /**
     * 判断是否已经注册
     * @return
     */
    public boolean isHadReg(){
        return SpUtils.getInstance().getBoolean(HAD_REG,false);
    }

    /**
     * 记录已经注册了
     */
    public void setHadReg(){
        SpUtils.getInstance().putBoolean(HAD_REG,true);
    }


    /**
     * 保存数据到本地
     *
     * @param updateBean
     */
    public void saveUpdate(UpdateBean updateBean) {
        this.data = updateBean;
        SpUtils.getInstance().putString(APPDATA, gson.toJson(updateBean));
    }

    /**
     * 获取广告列表
     * @return
     */
    public List<Ads> getAds(){
        if (data.getAds() == null){
            data.setAds(new ArrayList<>());
        }
        return data.getAds();
    }

    /**
     * 获取启动图广告
     * @return
     */
    public Ads getMainAds(){
        for (Ads ads:getAds()){
            if (ads.getPos().equals("185")){
                return ads;
            }
        }
        return null;
    }
    /**
     * 獲取首頁banner
     *
     * @return
     */
    public List<String> getMainBanner() {
        List<String> image = new ArrayList<>();
        for (Ads ads:getAds()){
            if (ads.getPos().equals("688")){
                image.add(ads.getImg());
            }
        }
        return image;
    }
    /**
     * 获取轮播图点击事件
     */
    public List<String> getMainBannerLike(){
        List<String> link = new ArrayList<>();
        for (Ads ads:getAds()){
            if (ads.getPos().equals("620")){
                link.add(ads.getLink());
            }
        }
        return link;
    }

    /**
     * 获取商品详细
     *
     * @return
     */
    public List<Gds> getGds() {
        if (data.getGds() == null){
            data.setGds(new ArrayList<>());
        }
        return data.getGds();
    }

    /**
     * 获取帮助连接
     *
     * @return
     */
    public String getHelpUrl() {
        return data.getHpurl();
    }

    /**
     * 获取vip
     *
     * @return
     */
    public Vip getVip() {
        return data.getVip();
    }

    /**
     * 判断vip是否过时
     *
     * @return
     */
    public boolean IsVipOutOffTime() {
        try {
            if (getVip() != null && !getVip().isIsout()) {
                Date now = new Date(System.currentTimeMillis());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                Date viptiam = sdf.parse(getVip().getTime());
                Calendar c = Calendar.getInstance();
                c.setTime(viptiam);
                c.add(Calendar.DAY_OF_MONTH, 1);
                viptiam=c.getTime();
                if (viptiam.before(now)) {
                    data.getVip().setIsout(true);
                }
                saveUpdate(data);

            }
        } catch (ParseException e) {
            if (!data.getVip().getTime().equals("永久")) {
                data.getVip().setIsout(true);
                saveUpdate(data);
            }
            e.printStackTrace();
        }
        if (data != null && data.getVip() != null){
            return data.getVip().isIsout();
        } else{
            return true;
        }

    }

    public String getContrctType() {
        if (data != null && data.getContract() != null){
            return data.getContract().getTxt();
        } else{
            return null;
        }

    }

    public String getContrct() {
        if (data != null && data.getContract() != null){
            return data.getContract().getNum();
        } else{
            return null;
        }

    }

    /**
     * 获取是否已登录状态
     *
     * @return
     */
    public static boolean isLogin() {
        //return SpUtils.getInstance().getBoolean(Constants.ISLOGIN, false);
        return true;
    }


    /**
     * 登录失效取消登录状态
     */
    public static void setUnLogin() {
        SpUtils.getInstance().putBoolean(Constants.ISLOGIN, false);
        SpUtils.getInstance().putString(Constants.UKEY, "");
    }
    /**
     * 获取所有的分类
     *
     * @return
     */
    public List<SeminarInfo> getRelation() {
        List<SeminarInfo> result;
        String temp = SpUtils.getInstance().getString(TALENS_RELATION, "");
        if (Utils.isEmpty(temp)) {
            return null;
        }
        result = GsonUtils.getFromList(temp, SeminarInfo.class);
        return result;
    }

    /**
     * 保存分类
     *
     * @param datas
     */
    public void saveRelations(List<SeminarInfo> datas) {
        SpUtils.getInstance().putString(TALENS_RELATION, GsonUtils.getStringFromBean(datas));
    }

    /**
     * 获取哄睡的故事分类
     *
     * @return
     */
    public int getHelpSleepPos() {
        return 524;
    }

    /**
     * 获取搜索的记录
     *
     * @return
     */
    public List<String> getSearchHistory() {
        List<String> result = new ArrayList<>();
        String temp = SpUtils.getInstance().getString(TALENS_SEARCH_HISTORY, "");
        if (!Utils.isEmpty(temp)) {
            String[] strings = temp.split(SPLIT);
            result.addAll(Arrays.asList(strings));
        }
        return result;
    }

    /**
     * 保存搜索历史记录
     *
     * @param string
     */
    public void saveSearchHistory(String string) {
        List<String> result = getSearchHistory();

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(string)) {
                result.remove(i);
            }
        }
        result.add(0, string);
        if (result.size() > historycount) {
            result.remove(historycount - 1);
        }
        savaHistory(result);
    }

    /**
     * 移除记录
     *
     * @param string
     */
    public void RemoveHistory(String string) {
        List<String> result = getSearchHistory();

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(string)) {
                result.remove(i);
            }
        }
        savaHistory(result);
    }


    public void RemoveHistory() {
        SpUtils.getInstance().putString(TALENS_SEARCH_HISTORY, "");
    }

    /**
     * 保存搜索历史记录列表
     *
     * @param result
     */
    private void savaHistory(List<String> result) {
        StringBuilder stringBuilder = new StringBuilder();
        if (result != null) {
            for (String s : result) {

                stringBuilder.append(s).append(SPLIT);
            }
        }
        SpUtils.getInstance().putString(TALENS_SEARCH_HISTORY, stringBuilder.toString());
    }


    /**
     * 获取分享的文字信息
     *
     * @return
     */
    public String getShareText() {
        for (Swt swt : data.getSwt()) {
            String share_swt_code = CPResourceUtils.getString("share_swt_code");
            if (swt.getCode().equals(CPResourceUtils.getString("share_swt_code"))) {
                return swt.getVal2();
            }
        }
        return "【分享链接获取失败】";
    }

    /**
     * 保存用户头像及vip信息
     *
     * @param o
     */
   /* public void saveUserData(UserDataBean o) {
        this.userDataBean = o;
        SpUtils.getInstance().putString(TALENS_USERDATA, GsonUtils.getStringFromBean(o));
    }

    public void savePlayLogBean(PlayLogDataBean o) {
        SpUtils.getInstance().putString(TALENS_PLAYLOG, GsonUtils.getStringFromBean(o));
    }

    public PlayLogDataBean getPlayLogBean() {
        return GsonUtils.getFromClass(SpUtils.getInstance().getString(TALENS_PLAYLOG), PlayLogDataBean.class);
    }*/

    //public void
}
