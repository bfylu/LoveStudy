package com.example.bfy.lovestudy.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bfy.lovestudy.R;
import com.example.bfy.lovestudy.activity.ChapterActivity;
import com.example.bfy.lovestudy.adapter.MuKeStatusAdapter;
import com.example.bfy.lovestudy.entity.MuKeStatus;
import com.example.bfy.lovestudy.tool.App;
import com.example.bfy.lovestudy.view.Mylistview;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.example.bfy.lovestudy.tool.App.listStatus;
import static com.example.bfy.lovestudy.tool.App.muKeStatusAdapter;


public class HomeFragment extends Fragment implements XBanner.XBannerAdapter {
    //private MuKeStatusAdapter muKeStatusAdapter;
    private XBanner Xbanner;
    private List<String> imgesUrl = new ArrayList<>();
    //private List<MuKeStatus> listStatus = new ArrayList<>();
    private Mylistview listView;
    public int page=1;//设置页数
    private int item_id=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_home_fragment, null);
        imgesUrl.clear();
        Xbanner= (XBanner) v.findViewById(R.id.banner);
        listView = (Mylistview) v.findViewById(R.id.mu_ke_status_listview);
        listView.setOnItemClickListener(itemClickListener);

        listView.setOnRefreshlistener(new Mylistview.Myinterface() {
            @Override
            public void onFreshing() {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            sleep(2000);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    page+=1;
                                    myPostTsy(page);
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
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    page-=1;
                                    myPostTsy(page);

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
        myPostTsy(page);




        setadapter();
        //添加广告数据

        getImgDate();
        Xbanner.setData(imgesUrl,null);//第二个参数为提示文字资源集合
        Xbanner.setmAdapter(this);
        // 设置XBanner的页面切换特效
        Xbanner.setPageTransformer(Transformer.Default);
        Xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(getActivity(), "点击了第" + (position + 1) + "张图片", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
    //item单击
    private ListView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("TAG","listStatus---->"+listStatus.get(position).getId());
            Intent intent = new Intent();
            intent.setClass(getActivity(),ChapterActivity.class);
            intent.putExtra("cid",listStatus.get(position-1).getId());
            startActivity(intent);
        }
    };

    /**
     * 获取图片数据源
     */
    private void getImgDate(){
        imgesUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497957624&di=05dc237f6feb3dd1f04b021fc4d04cf2&imgtype=jpg&er=1&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2Fc5131475jw1enzk2qrckbj20fk078q4q.jpg");
        imgesUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497362871990&di=8ee33077dd5f9346ccb2b54ea2c4b6df&imgtype=0&src=http%3A%2F%2Fimg.mukewang.com%2F53eafa9d0001ba7006000338-590-330.jpg");
        imgesUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497362871990&di=835d91412720ad32ec6b92a19bb30240&imgtype=0&src=http%3A%2F%2Fww1.sinaimg.cn%2Flarge%2Fc5131475jw1exel6gq5n5j20fk0780tq.jpg");
        imgesUrl.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1497362871989&di=b160f22e40e6240972385daf4b86935d&imgtype=0&src=http%3A%2F%2Fimg.mukewang.com%2F5668dc790001aa2b06000338-590-330.jpg");
    }
    /**
     * Xbanner框架
     * @param banner
     * @param model
     * @param view
     * @param position
     */
    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        Glide.with(this).load(imgesUrl.get(position)).into((ImageView) view);
    }
    @Override
    public void onResume() {
        super.onResume();
        Xbanner.startAutoPlay();
        App.listStatus.clear();

    }

    @Override
    public void onStop() {
        super.onStop();
        Xbanner.stopAutoPlay();
    }

    //设置适配器
    private void setadapter() {
        //适配器
        muKeStatusAdapter = new MuKeStatusAdapter(getActivity());
        muKeStatusAdapter.setData(listStatus);
        listView.setAdapter(muKeStatusAdapter);

    }

    private void myPostTsy(int t){
        OkHttpUtils
                .post()
                .url("http://www.imooc.com/api3/courselist_ver2")
                .addHeader("Connection", "keep-alive")
                .addHeader("platform", "2")
                .addHeader("phoneModel", Build.MODEL)
                .addHeader("systemVersion", Build.VERSION.RELEASE)
                .addHeader("appVersion", "3.2.0")

                .addParams("cat_type","223")
                .addParams("uid","0")
                .addParams("timestamp","1495809555771")
                .addParams("token", "7f75e24cb1f7e5c358f03a7b40a60976")
                .addParams("page",t+"")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG","Exception-----》"+e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            //Log.e("TAG","jsonObject" + jsonObject);
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            for (int i=0;i<jsonArray.length();i++){

                                listStatus.add(gson.fromJson(jsonArray.get(i).toString(),MuKeStatus.class));
                                //Log.e("lst:",listStatus.get(i).getName());
                            }
                            App.muKeStatusAdapter.setData(listStatus);
                            muKeStatusAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.e("TAG","result  :  "+response);
                    }
                });
    }




}
