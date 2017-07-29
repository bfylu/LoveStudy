package com.example.bfy.lovestudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bfy.lovestudy.R;
import com.example.bfy.lovestudy.entity.MuKeStatus;

import java.util.List;

/**
 * Created by bfy on 17-5-27.
 */

public class MuKeStatusAdapter extends BaseAdapter {
    private Context context;
    private List<MuKeStatus> mList ;
    private LayoutInflater mInflater;


    public MuKeStatusAdapter(Context context){
        this.context = context;
        mInflater=LayoutInflater.from(context);


    }

    public void setData(List<MuKeStatus> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder ;

        if (convertView ==null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.mu_ke_status_item,null);
            holder.pic = (ImageView) convertView.findViewById(R.id.mu_ke_status_pic);
            holder.name = (TextView) convertView.findViewById(R.id.mu_ke_status_name);
            holder.numbers = (TextView) convertView.findViewById(R.id.mu_ke_status_numbers);
            holder.duration = (TextView) convertView.findViewById(R.id.mu_ke_status_duration);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(mList.get(position).getPic()).into(holder.pic);

        holder.name.setText(mList.get(position).getName());
        holder.numbers.setText(mList.get(position).getNumbers());
        holder.duration.setText(mList.get(position).getDuration());

        return convertView;
    }

    class ViewHolder{
        private ImageView pic;
        private TextView name,numbers,duration;

    }

}
