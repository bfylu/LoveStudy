package com.example.bfy.lovestudy.entity;

/**
 * Created by bfy on 17-5-26.
 */

public class MuKeStatus {
    /**
     * id
     */
    private String id;
    /**
     *标题名字
     */
    private String name;
    /**
     *标题图片
     */
    private String pic;
    /**
     *描述
     */
    private String desc;
    /**
     *学习
     */
    private String is_learned;
    /**
     *客人id
     */

    private String company_id;
    /**
     *
     */
    private String numbers;
    /**
     *更新时间
     */
    private String update_time;
    /**
     *课程类型
     */
    private String coursetype;
    /**
     *持续时间
     */
    private String duration;
    /**
     *完
     */
    private String finished;
    /**
     *跟随
     */
    private String is_follow;
    /**
     *章节
     */
    private String max_chapter_seq;
    /**
     *媒体
     */
    private String max_media_seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIs_learned() {
        return is_learned;
    }

    public void setIs_learned(String is_learned) {
        this.is_learned = is_learned;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCoursetype() {
        return coursetype;
    }

    public void setCoursetype(String coursetype) {
        this.coursetype = coursetype;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getIs_follow() {
        return is_follow;
    }

    public void setIs_follow(String is_follow) {
        this.is_follow = is_follow;
    }

    public String getMax_chapter_seq() {
        return max_chapter_seq;
    }

    public void setMax_chapter_seq(String max_chapter_seq) {
        this.max_chapter_seq = max_chapter_seq;
    }

    public String getMax_media_seq() {
        return max_media_seq;
    }

    public void setMax_media_seq(String max_media_seq) {
        this.max_media_seq = max_media_seq;
    }
}
