package com.example.bfy.lovestudy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.bfy.lovestudy.R;

public class SheZhiActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout dengglu,fasongtongzhi,qingchuhuangcun,lixianxiazai,guangyuwmn;
    private ImageView fanhuijian,nictu,she_la,she_xia_la,she_zidong_la;
    private int i=1;
    private int[] images = {R.mipmap.she_la2,R.mipmap.she_la1};
    private int index = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_zhi);
        find();
        btn();

    }

    private void btn() {

        qingchuhuangcun.setOnClickListener(this);
        lixianxiazai.setOnClickListener(this);
        guangyuwmn.setOnClickListener(this);
        fanhuijian.setOnClickListener(this);
        she_la.setOnClickListener(this);
        she_xia_la.setOnClickListener(this);
        she_zidong_la.setOnClickListener(this);
    }

    private void find() {

        qingchuhuangcun = (LinearLayout) findViewById(R.id.qingchuhuangcun);
        lixianxiazai = (LinearLayout) findViewById(R.id.lixianxiazai);
        guangyuwmn = (LinearLayout) findViewById(R.id.guangyuwmn);

        fanhuijian = (ImageView) findViewById(R.id.fanhuijian);

        she_la = (ImageView) findViewById(R.id.she_la);
        she_xia_la = (ImageView) findViewById(R.id.she_xia_la);
        she_zidong_la = (ImageView) findViewById(R.id.she_zidong_la);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.qingchuhuangcun:
                break;
            case R.id.lixianxiazai:
                break;
            case R.id.guangyuwmn:
                break;
            case R.id.fanhuijian:
                finish();
                break;
            case R.id.she_la:
                she_la.setImageResource(images[++index%images.length]);
                break;
            case R.id.she_xia_la:
                she_xia_la.setImageResource(images[++index%images.length]);
                break;
            case R.id.she_zidong_la:
                she_zidong_la.setImageResource(images[++index%images.length]);
                break;
        }
    }

}
