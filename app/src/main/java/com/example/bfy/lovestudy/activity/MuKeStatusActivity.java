package com.example.bfy.lovestudy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bfy.lovestudy.R;
import com.example.bfy.lovestudy.adapter.MuKeStatusAdapter;
import com.example.bfy.lovestudy.entity.MuKeStatus;
import com.example.bfy.lovestudy.tool.App;
import com.example.bfy.lovestudy.view.Mylistview;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MuKeStatusActivity extends AppCompatActivity {
    private MuKeStatusAdapter muKeStatusAdapter;
    private Context context;
    private Mylistview listView;
    private int page=1;
    private String cid;
    private List<MuKeStatus> listStatus = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mu_ke_stu_status);
        listView = (Mylistview) findViewById(R.id.mu_ke_status_listview);
        Intent intent = getIntent();
        cid = intent.getStringExtra("cid");
        myPostAsyn(cid,page);
        listView.setOnItemClickListener(itemClickListener);
        //上拉更多,下拉刷新
        listView.setOnRefreshlistener(new Mylistview.Myinterface() {
            @Override
            public void onFreshing() {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(2000);
                            MuKeStatusActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (listStatus.size()<20) return;
                                    page+=1;
                                    myPostAsyn(cid,page);
                                    //listView.setSelection(i*20+1);
                                    App.muKeStatusAdapter.notifyDataSetChanged();//刷新适配器
                                    listView.finishView();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

            @Override
            public void loadMore() {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(2000);
                            MuKeStatusActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    page-=1;
                                    myPostAsyn(cid,page);

                                    App.muKeStatusAdapter.notifyDataSetChanged();//刷新适配器
                                    listView.finishView();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }


    //item单击
    private ListView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("TAG","listStatus---->"+listStatus.get(position).getId());
            Intent intent = new Intent();
            intent.setClass(MuKeStatusActivity.this,ChapterActivity.class);
            intent.putExtra("cid",listStatus.get(position-1).getId());
            startActivity(intent);
        }
    };

    private void myPostAsyn(String cid,int page){
        OkHttpUtils.post()
                .url("http://www.imooc.com/api3/getcpinfo_ver2")
                .url("http://www.imooc.com/api3/courselist_ver2")
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "3.2.0")

                .addParams("cat_type",cid)
                .addParams("uid","0")
                .addParams("timestamp","1495809555771")
                .addParams("token", "7f75e24cb1f7e5c358f03a7b40a60976")
                .addParams("page",page+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        listStatus.clear();
                        Gson gson = new Gson();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            //Log.e("TAG","jsonObject" + jsonObject);
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++){

                                listStatus.add(gson.fromJson(jsonArray.get(i).toString(),MuKeStatus.class));
                                //Log.e("lst:",listStatus.get(i).getName());
                            }
                            //适配器
                            muKeStatusAdapter = new MuKeStatusAdapter(MuKeStatusActivity.this);
                            muKeStatusAdapter.setData(listStatus);
                            listView.setAdapter(muKeStatusAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }


}
