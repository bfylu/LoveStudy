package com.example.bfy.lovestudy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.bfy.lovestudy.R;
import com.example.bfy.lovestudy.activity.MuKeStatusActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseFragment extends Fragment {
    private GridView gview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter sim_adapter;
    // 图片封装为一个数组
    private int[] icon = { R.drawable.d1, R.drawable.d2,
            R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8,
            R.drawable.d9, R.drawable.r1, R.drawable.r2,
            R.drawable.r3, R.drawable.r4, R.drawable.r5
    };
    private String[] iconName = { "HTML/CSS", "JavaScript", "jQuery", "Html5", "Node.js",
            "AngularJS", "WebApp","前端工具","CSS3","JAVA","C++","Android","MySQL","SQL Server"};
    private String[] iconNum ={"7","44","219","221","222","1123","1260","1261","1262","220","1331","223","952","1366"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.activity_course_activict, null);
        gview = (GridView) v.findViewById(R.id.gview);
        //新建List
        data_list = new ArrayList<Map<String,Object>>();
        //获取数据
        getData();
        //新建适配器
        String [] from ={"image","text"};
        int [] to = {R.id.image,R.id.text};
        sim_adapter = new SimpleAdapter(getActivity(), data_list, R.layout.grid_item, from, to);
        //配置适配器
        gview.setAdapter(sim_adapter);
        return v;
    }
    public List<Map<String, Object>> getData(){
        //cion和iconName的长度是相同的，这里任选其一都可以
        for(int i=0;i<icon.length;i++){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", icon[i]);
            map.put("text", iconName[i]);
            data_list.add(map);
            gview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),MuKeStatusActivity.class);
                    intent.putExtra("cid",iconNum[(int) id]);
                    Log.e("TAG",id+"");
                    startActivity(intent);
                }
            });
        }

        return data_list;
    }
}