package com.example.bfy.lovestudy.entity;

/**
 * Created by bfy on 17-6-7.
 */

public class Media {

    /**
     * id : 15066
     * name : ContentProvider特点
     * type : 1
     * chapter_seq : 3
     * chapter_id : 4175
     * media_seq : 1
     * media_url : http://v3.mukewang.com/59360931e420e553688b459a/L.mp4
     * media_down_url : http://v3.mukewang.com/59360931e420e553688b459a/M.mp4
     * duration : 469570
     * last_time : 0
     * last_date : 0
     * share_url : http://www.imooc.com/video/15066
     * have_ques : 0
     * media_size : 22720020
     * media_down_size : 32563310
     * status : 0
     */

    private int id;
    private String name;
    private int type;
    private int chapter_seq;
    private int chapter_id;
    private int media_seq;
    private String media_url;
    private String media_down_url;
    private int duration;
    private int last_time;
    private int last_date;
    private String share_url;
    private int have_ques;
    private int media_size;
    private int media_down_size;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getChapter_seq() {
        return chapter_seq;
    }

    public void setChapter_seq(int chapter_seq) {
        this.chapter_seq = chapter_seq;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public int getMedia_seq() {
        return media_seq;
    }

    public void setMedia_seq(int media_seq) {
        this.media_seq = media_seq;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getMedia_down_url() {
        return media_down_url;
    }

    public void setMedia_down_url(String media_down_url) {
        this.media_down_url = media_down_url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLast_time() {
        return last_time;
    }

    public void setLast_time(int last_time) {
        this.last_time = last_time;
    }

    public int getLast_date() {
        return last_date;
    }

    public void setLast_date(int last_date) {
        this.last_date = last_date;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getHave_ques() {
        return have_ques;
    }

    public void setHave_ques(int have_ques) {
        this.have_ques = have_ques;
    }

    public int getMedia_size() {
        return media_size;
    }

    public void setMedia_size(int media_size) {
        this.media_size = media_size;
    }

    public int getMedia_down_size() {
        return media_down_size;
    }

    public void setMedia_down_size(int media_down_size) {
        this.media_down_size = media_down_size;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
