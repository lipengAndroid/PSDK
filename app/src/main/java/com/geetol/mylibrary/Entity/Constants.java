package com.geetol.mylibrary.Entity;

import java.io.File;

/**
 * Info     ： Create by Zeoy
 * Introduce：
 * Date     ： 2018/12/20
 */
public class Constants {
    public static final String USER_ID = "user_id";
    public static final String UKEY = "user_key";
    public static final String ISLOGIN = "islogin";

    public static final String TALENS_MODEL = "talens_model";      //故事的模式
    public static final String TALENS_NEXT_NODEL = "talens_next_nodel";//故事的重複模式
    public static final String TALENS_PAUSE_TIME = "talens_pause_time";//故事结束的时间

    public static final int TALENS_PASUE = 1991;                       //故事停止播放

    public static final int TALENS_SHOWVIP = 1992;                       //弹出vip界面

    public static final int HIDE_FLOATWINDOW = 1234;                      //隐藏悬浮窗
    public static final int SHOW_FLOATWINDOW = 23453;                     //显示悬浮窗


    public static final int TALENS_ALL = 9;                        //全部音乐
    public static final int MUSIC_CITY = 8;                     //  都市858
    public static final int MUSIC_WAI = 0;                       // 野外  857
    public static final int MUSIC_ANIMAL = 1;                    // 动物  699
    public static final int MUSIC_WHITE = 2;                       //白噪音 703
    public static final int MUSIC_SCHOOL = 3;                      //学校  701
    public static final int MUSIC_THINK = 4;                      //冥想 698


    public static final int DREAM_AUDIO_SWITCH = 0;                  //梦话录制开关  默认关闭

//    public static final long AUDIO_START_TIME = 0;                  //录制开始时间
//    public static final long AUDIO_END_TIME = 0;                  //录制结束时间

    //登录结果码
    //登录失效
    public static final String LOGIN_FAILED = "0x1002";

    //暂停播放
    public static final int MUSIC_PASUE = 0x1134;
    //播放
    public static final int MUSIC_PLAY = 0x1135;

    //分类获取完成
    public static final int GET_RELATION_OK = 0x453;



    //intent传递的data
    public static final String DATA = "data";
    public static String[] PICTURE_PERMISSION = {android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE};

//    public static final String DATA_PATH = File.separator+"talens"+File.separator;
    public static final String DATA_PATH = File.separator+"sleep"+ File.separator;

}
