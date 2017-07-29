package com.example.bfy.lovestudy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.bfy.lovestudy.R;


public class course_tiao extends AppCompatActivity {
private TextView text_biao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_tiao);
        text_biao = (TextView) findViewById(R.id.text_biao);
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        text_biao.setText(text);
    }
}
