package com.example.bfy.lovestudy.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bfy.lovestudy.MainActivity;
import com.example.bfy.lovestudy.R;
import com.example.bfy.lovestudy.entity.User;
import com.example.bfy.lovestudy.interf.ReqCallBack;
import com.example.bfy.lovestudy.net_tool.RequestManager;
import com.example.bfy.lovestudy.tool.App;
import com.example.bfy.lovestudy.tool.T;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class LoginActivity extends AppCompatActivity {
    private EditText nameEdit,passEdit;
    private CheckBox autologin;
    private Button loginBtn;
    private TextView zhuceBtn;
    private String url="AndroidUserEnterServlet";
    //声明一个SharedPreferences用于保存数据
    private SharedPreferences spSettings = null;
    private static final String PREFS_NAME="NamePwd";
    private int type = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
       setContentView(R.layout.activity_login);

        nameEdit = (EditText) findViewById(R.id.Song_name);
        passEdit = (EditText) findViewById(R.id.Song_pass);
        loginBtn = (Button) findViewById(R.id.login_bin);
        autologin = (CheckBox) findViewById(R.id.cb_autologin);
        zhuceBtn = (TextView) findViewById(R.id.Song_zhuce);
        loginBtn.setOnClickListener(listener);
        zhuceBtn.setOnClickListener(listener);

        //绑定控件事件
        //setListener();
        //获取数据
        //getData();
    }

    private View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.login_bin:
                    if(TextUtils.isEmpty(nameEdit.getText())){
                        //T.show("用户名不能为空");
                        return;
                    }
                    else  if(TextUtils.isEmpty(passEdit.getText())){
                        //T.show("密码不能为空");
                        return;
                    }
                    else {
                        myPostAsyn();
                        //提交数据给服务器[=
                        Log.e("aa","aa");
                    }
                    break;
                case R.id.Song_zhuce:
                    Intent intent = new Intent(LoginActivity.this,RegisteredActivity.class);
                    startActivity(intent);
                    break;
            }

        }
    };

    private void setListener(){
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断用户名和密码和选项
                if ("A20".equals(nameEdit.getText().toString())
                        &&"123321".equals(passEdit.getText().toString())){
                    //判断复框是否选中
                    if (autologin.isChecked()){
                        spSettings = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                        //得到Editor对象
                        SharedPreferences.Editor edit = spSettings.edit();
                        //记录保存标记
                        edit.putBoolean("isKeep",true);
                        //记住用户名
                        edit.putString("username",nameEdit.getText().toString());
                        //记住密码
                        edit.putString("password",passEdit.getText().toString());
                        edit.commit();//提交
                    }else {
                        spSettings=getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                        //得到Editor对象
                        SharedPreferences.Editor edit = spSettings.edit();
                        //记录保存标记
                        edit.putBoolean("isKeep",false);
                        //记住用户名
                        edit.putString("username","");
                        //记住密码
                        edit.putString("password","");
                        edit.commit();//提交
                    }
                }else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData(){
        spSettings = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        //判断之前是否存过用户名密码和选项
        if (spSettings.getBoolean("isKeep",false)){
            //如果没存，则显示在相应文本框内
            nameEdit.setText(spSettings.getString("username",""));
            passEdit.setText(spSettings.getString("password",""));
        }else {
            //否则显示空
            nameEdit.setText("");
            passEdit.setText("");
        }

    }

    private void myPostAsyn(){
        HashMap<String,String> map = new HashMap<>();
        map.put("userName",nameEdit.getText().toString());
        map.put("password",passEdit.getText().toString());
        map.put("type","0");
        App.requestManager.requestAsyn(url, RequestManager.TYPE_POST_FORM,map,reqCallBack);

    }

    //
    private ReqCallBack<String> reqCallBack = new ReqCallBack<String>() {
        @Override
        public void onReqSuccess(String result) {
            Gson gson = new Gson();
            try {

                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.getInt("uid")>0) {
                    User user = new User();
                    user.setUid(jsonObject.getInt("uid"));
                    user.setUserName(jsonObject.getString("userName"));
                    user.setCompel(jsonObject.getString("compel"));
                    user.setNumber(jsonObject.getString("number"));
                    user.setPassword(jsonObject.getString("password"));
                    user.setType(jsonObject.getInt("type"));
                    App.user = user;
                    handler.sendEmptyMessage(0x123);
                }else {
                    handler.sendEmptyMessage(0x124);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onReqFailed(String errorMsg) {
            handler.sendEmptyMessage(0x000);
        }
    };

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x123){
                T.show("登录成功");
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }else if (msg.what == 0x000){
                T.show("失败");
            }else if ((msg.what ==0x124)){
                T.show("失败");
            }
        }
    };


}
