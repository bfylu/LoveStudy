package com.example.bfy.lovestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bfy.lovestudy.R;


/**
 * Created by JOHN on 2017/5/31.
 */

public class GridviewAdapter extends BaseAdapter{
    private Context context;
    private String[] t;
    public GridviewAdapter(Context context){
        this.context=context;
        t=context.getResources().getStringArray(R.array.home_title);
    }
    @Override
    public int getCount() {
        return t.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder;
        if (v==null){
            holder=new ViewHolder();
            v= LayoutInflater.from(context).inflate(R.layout.home_griview_item,null);
            holder.text= (TextView) v.findViewById(R.id.gridview_item_title);
            v.setTag(holder);
        }
        else {
            holder= (ViewHolder) v.getTag();
        }
        holder.text.setText(t[position]);
        return v;
    }
    class ViewHolder{
        TextView text;
    }
}
