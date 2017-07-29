package com.example.bfy.lovestudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bfy.lovestudy.R;
import com.example.bfy.lovestudy.interf.ReqCallBack;
import com.example.bfy.lovestudy.net_tool.RequestManager;
import com.example.bfy.lovestudy.tool.T;

import java.util.HashMap;

public class RegisteredActivity extends AppCompatActivity implements View.OnClickListener {
    private RequestManager requestManager;
    private String url="AndroidInsertUserServlet";
    private EditText nicName;
    private EditText SongEnd;
    private EditText SongName;
    private EditText SongPass;
    private Button loginBin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        requestManager = RequestManager.getInstance(this);
        myFindView();

    }

    private void myFindView() {


        nicName = (EditText) findViewById(R.id.nic_name);
        SongEnd = (EditText) findViewById(R.id.Song_end);
        SongName = (EditText) findViewById(R.id.Song_name);
        SongPass = (EditText) findViewById(R.id.Song_pass);
        loginBin = (Button) findViewById(R.id.login_bin);
        loginBin.setOnClickListener(this);

    }

    private ReqCallBack<String> reqCallBack = new ReqCallBack<String>() {
        @Override
        public void onReqSuccess(String result) {
            Log.e("TAG","注册result:----->"+result);
            if (result.equals("true")){
                T.show("注册成功");
                startActivity(new Intent(RegisteredActivity.this, LoginActivity.class));
            }
        }
        @Override
        public void onReqFailed(String errorMsg) {
            T.show("注册失败");
        }
    };

    private void myPostAsyn(){
        HashMap<String,String> map = new HashMap<>();
        map.put("userName",SongName.getText().toString());//电话
        map.put("compel",nicName.getText().toString());//名字
        map.put("type","0");
        map.put("password",SongPass.getText().toString());//密码
        map.put("number",SongEnd.getText().toString());
        map.put("grade","2");
        map.put("faculty","2");
        requestManager.requestAsyn(url,RequestManager.TYPE_POST_FORM,map,reqCallBack);
    }

    @Override
    public void onClick(View v) {
        myPostAsyn();
    }
}
