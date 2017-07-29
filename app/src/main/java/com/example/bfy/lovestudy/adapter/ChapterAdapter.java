package com.example.bfy.lovestudy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bfy.lovestudy.R;
import com.example.bfy.lovestudy.activity.ChapterActivity;
import com.example.bfy.lovestudy.entity.Chapter;
import com.example.bfy.lovestudy.entity.Media;
import com.example.bfy.lovestudy.tool.App;

import java.util.ArrayList;
import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by qi on 2017/6/14.
 */

public class ChapterAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private List<Media> mlist=new ArrayList<>();
    private List<Chapter> listChap= new ArrayList<>();
    private LayoutInflater inflater;
    private OnMyItemClickListener listener;
    private Context context;
    public ChapterAdapter(Context context, List<Media> mlist, List<Chapter> listChap) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.mlist=mlist;
        this.listChap = listChap;
        this.mlist = new ArrayList(mlist);//解决java.lang.UnsupportedOperationException

    }

    public ChapterAdapter(ChapterActivity mainActivity) {
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_body, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.id_tv_item);


            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    
                    //listener.onMyItemLongClick(position, mlist.get(position));
                    return false;
                }
            });

            convertView.setTag(holder);


        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*
        if (mlist.get(position).getChapter_seq() ==position+1) {
            holder.text.setText(mlist.get(position).getChapter_seq() + "-" + mlist.get(position).getMedia_seq() + " " + mlist.get(position).getName().toString());
        }*/
        holder.text.setText(mlist.get(position).getChapter_seq() + "-" + mlist.get(position).getMedia_seq() + " " + mlist.get(position).getName().toString());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG",mlist.get(position).getChapter_seq() + "-" + mlist.get(position).getMedia_seq() + " " +mlist.get(position).getName());
                //context.startActivity(new Intent(context,RegisteredActivity.class));
                //App.videoView.setVideoURI(Uri.parse(mlist.get(position).getMedia_down_url()));
                //网络视频
                String videoUrl2 = mlist.get(position).getMedia_url();
                App.jcVideoPlayerStandard.setUp(videoUrl2
                        , JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,mlist.get(position).getName());
                //listener.onMyItemClick(position,mlist.get(position).getName());
            }
        });
        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.item_head, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.id_tv_head_item);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = mlist.get(position).getChapter_seq()+"";
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
       // Log.e("tAG","listChap"+listChap.get(position).getName().charAt(0));
        String st = mlist.get(position).getChapter_seq()+"";
        return st.charAt(0);
      /*  if (position<3){
            return 0;
        }else if (position<5){
            return 1;
        }else if (position<8){
            return 2;
        }else {
            return 3;
        }*/


    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }

    /**
     * 内部接口回调方法
     */
    public interface OnMyItemClickListener {
        void onMyItemClick(int position, Object object);
        void onMyItemLongClick(int position, Object object);
    }
    /**
     * 设置监听方法
     *
     * @param listener
     */
    public void setOnMyItemClickListener(OnMyItemClickListener listener) {
        this.listener = listener;
    }

    public void addItem(){
        mlist.add(null);
        notifyDataSetChanged();
    }
    public void deleteItem(int position){
        mlist.remove(position);
        notifyDataSetChanged();
    }

}