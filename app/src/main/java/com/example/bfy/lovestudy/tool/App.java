package com.example.bfy.lovestudy.tool;

import android.app.Application;
import android.os.Build;

import com.example.bfy.lovestudy.adapter.MuKeStatusAdapter;
import com.example.bfy.lovestudy.entity.MuKeStatus;
import com.example.bfy.lovestudy.entity.User;
import com.example.bfy.lovestudy.net_tool.RequestManager;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;

/**
 * Created by bfy on 17-5-27.
 */

public class App extends Application {
    public static JCVideoPlayerStandard jcVideoPlayerStandard;
    public static User user;
    public static RequestManager requestManager;
    public static MuKeStatusAdapter muKeStatusAdapter = null;
    public static List<MuKeStatus> listStatus = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        new T(this);//实例化
        requestManager=RequestManager.getInstance(this);
    }

    private void myPostAsyn(){
        OkHttpUtils
                .post()
                .url("http://www.imooc.com/api3/newcourseskill")
                .addHeader("User-Agent", "mukewang/5.1.3 (Android 7.0; Xiaomi MI 4S Build/NRD90M),Network WIFI")
                .addHeader("APP-INFO", "mukewang/5.1.3 (Android 7.0; Xiaomi MI 4S Build/NRD90M),Network WIFI")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "3.2.0")

                .addParams("uid","0")
                .addParams("token", "8a23e151eacfe1e9556e71f8eb6b3c9b")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });
    }

}
