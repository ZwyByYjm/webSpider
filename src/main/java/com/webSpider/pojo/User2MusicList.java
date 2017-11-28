package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_user_musiclist")
public class User2MusicList {
    private Integer id;

    private String userid;

    private String musiclistid;

    private Integer listencount;

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

    public String getMusiclistid() {
        return musiclistid;
    }

    public void setMusiclistid(String musiclistid) {
        this.musiclistid = musiclistid == null ? null : musiclistid.trim();
    }

    public Integer getListencount() {
        return listencount;
    }

    public void setListencount(Integer listencount) {
        this.listencount = listencount;
    }
}