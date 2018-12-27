package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_user_music")
public class User2Music {
    private Integer id;

    private String userid;

    private String musicid;

    private String ratio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getMusicid() {
        return musicid;
    }

    public void setMusicid(String musicid) {
        this.musicid = musicid == null ? null : musicid.trim();
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio == null ? null : ratio.trim();
    }

    @Override
    public String toString() {
        return "User2Music{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", musicid='" + musicid + '\'' +
                ", ratio='" + ratio + '\'' +
                '}';
    }
}