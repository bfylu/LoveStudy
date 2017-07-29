package com.example.bfy.lovestudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bfy.lovestudy.MainActivity;
import com.example.bfy.lovestudy.R;


public class Welcome extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private LinearLayout tz2;
    private int i=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv1 = (TextView) findViewById(R.id.miashu);
        tz2 = (LinearLayout) findViewById(R.id.tz2);
        tz2.setOnClickListener(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        T();
    }
    private void T(){
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    while (i>1){
                        Thread.sleep(1000);
                        i--;
                        handler.sendEmptyMessage(0x123);
                    }
                    Intent intent = new Intent(Welcome.this, MainActivity.class);
                    startActivity(intent);
                    finish();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0x123){
                tv1.setText(i+"秒");
            }
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent3 = new Intent(Welcome.this, MainActivity.class);
        startActivity(intent3);
        finish();
    }
}
