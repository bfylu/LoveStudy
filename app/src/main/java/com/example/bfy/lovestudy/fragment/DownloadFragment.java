package com.example.bfy.lovestudy.fragment;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bfy.lovestudy.PopWindow;
import com.example.bfy.lovestudy.R;


public class DownloadFragment extends Fragment implements View.OnClickListener {
    private TextView tv_memory_info,gengbtn;
    private ImageView button;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_download_fragment, null);
        button = (ImageView) v.findViewById(R.id.button_btn);
        button.setOnClickListener(this);
        gengbtn = (TextView) v.findViewById(R.id.geng_btn);
        gengbtn.setOnClickListener(this);
        tv_memory_info = (TextView) v.findViewById(R.id.tv_memory_info);
        v.findViewById(R.id.button_btn).setOnClickListener(this);
        //读取Storage大小信息
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long blockSizeLong = statFs.getBlockSizeLong();
        long availbleBlocksLong = statFs.getAvailableBlocksLong();
        //界面上显示Storage信息
        tv_memory_info.setText("   剩余："+ Formatter.formatFileSize(getActivity(),blockSizeLong * availbleBlocksLong));
        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_btn){
            PopWindow popWindow = new PopWindow(getActivity());
            popWindow.showPopupWindow(v.findViewById(R.id.button_btn));
        }
    }
}

