package com.example.bfy.lovestudy.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.bfy.lovestudy.R;
import com.example.bfy.lovestudy.adapter.ChapterAdapter;
import com.example.bfy.lovestudy.entity.Chapter;
import com.example.bfy.lovestudy.entity.Media;
import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import okhttp3.Call;
import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import static com.example.bfy.lovestudy.tool.App.jcVideoPlayerStandard;

public class ChapterActivity extends AppCompatActivity {

    private ChapterAdapter adapter;
    private List<Chapter> chapterList=new ArrayList<>();
    private List<Media> mediaList = new ArrayList<>();

    private ExpandableStickyListHeadersListView expandableStickyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColor(R.color.colorBar).init();
        setContentView(R.layout.activity_chapter);
        Intent intent = getIntent();
        String cid = intent.getStringExtra("cid");
        //videoView=(VideoView) findViewById(R.id.chapter_video_view);
        expandableStickyList = (ExpandableStickyListHeadersListView) findViewById(R.id.expandableStickyListHeadersListView);
        myPostAsyn(cid);
        jcVideoPlayerStandard = (JCVideoPlayerStandard) findViewById(R.id.videoplayer);

        //jcVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");

    }

    private void StickyList() {
        //初始化
        StickyListHeadersListView stickyList = (StickyListHeadersListView) findViewById(R.id.stickyListHeadersListView);
        adapter = new ChapterAdapter(this,mediaList,chapterList);
        stickyList.setAdapter(adapter);
        StickyListHeadersAdapter stickyListHeadersAdapter = new ChapterAdapter(ChapterActivity.this);
        expandableStickyList.setAdapter(stickyListHeadersAdapter);
        expandableStickyList.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {
                if (expandableStickyList.isHeaderCollapsed(headerId)) {
                    expandableStickyList.expand(headerId);
                } else {
                    expandableStickyList.collapse(headerId);
                }
            }
        });
        //ps：expandableStickyList的setOnItemClickListener不能用   github也有人问
        final ChapterAdapter myAdapter = (ChapterAdapter) stickyListHeadersAdapter;

        myAdapter.setOnMyItemClickListener(new ChapterAdapter.OnMyItemClickListener() {
            @Override
            public void onMyItemClick(int position, Object object) {
                Log.e("TAG","position--->"+object);
                Intent intent = new Intent();
                intent.setClass(ChapterActivity.this,ChapterActivity.class);
                startActivity(intent);
                //myAdapter.addItem();
            }

            @Override
            public void onMyItemLongClick(int position, Object object) {
                Toast.makeText(ChapterActivity.this, "Click long  on item" + position, Toast.LENGTH_SHORT).show();
                //myAdapter.deleteItem(position);
            }
        });

    }

private void myPostAsyn(String cid){
    Log.e("TAG","cid------->"+cid);
    OkHttpUtils
            .post()
            .url("http://www.imooc.com/api3/getcpinfo_ver2")
            .addHeader("Connection", "keep-alive")
            .addHeader("platform", "2")
            .addHeader("phoneModel", Build.MODEL)
            .addHeader("systemVersion", Build.VERSION.RELEASE)
            .addHeader("appVersion", "3.2.0")

            .addParams("cid",cid)
            .addParams("uid","0")
            .addParams("token", "8a23e151eacfe1e9556e71f8eb6b3c9b")
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
                        JSONArray jsonArray = new JSONObject(response).getJSONArray("data");
                        for (int i=0;i<jsonArray.length();i++) {

                            chapterList.add(gson.fromJson(jsonArray.getJSONObject(i).getJSONObject("chapter").toString(),Chapter.class));
                            for (int j=0;j<jsonArray.getJSONObject(i).getJSONArray("media").length();j++){
                                mediaList.add(gson.fromJson(jsonArray.getJSONObject(i).getJSONArray("media").getJSONObject(j).toString(),Media.class));
                            }
                        }

                            for (int mNum=0;mNum<mediaList.size();mNum++){
                                    Log.e("TAG","mediaList---->"+mediaList.get(mNum).getChapter_seq()+"-"+mediaList.get(mNum).getMedia_seq()+" "+mediaList.get(mNum).getName());
                            }
                        //本地的视频  需要在手机SD卡根目录添加一个 fl1234.mp4 视频
                        String videoUrl1 = Environment.getExternalStorageDirectory().getPath()+"/fl1234.mp4" ;
                        //网络视频
                        String videoUrl2 = mediaList.get(0).getMedia_url();
                        jcVideoPlayerStandard.setUp(videoUrl2
                                , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,mediaList.get(0).getName());
                        StickyList();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.e("TAG","result  :  "+response);
                }
            });
}

}
