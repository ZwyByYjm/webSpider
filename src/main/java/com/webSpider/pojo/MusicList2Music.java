package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_musiclist_music")
public class MusicList2Music {
    private Integer id;

    private String musiclistid;

    private String musicid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusiclistid() {
        return musiclistid;
    }

    public void setMusiclistid(String musiclistid) {
        this.musiclistid = musiclistid == null ? null : musiclistid.trim();
    }

    public String getMusicid() {
        return musicid;
    }

    public void setMusicid(String musicid) {
        this.musicid = musicid == null ? null : musicid.trim();
    }

    @Override
    public String toString()
    {
        return "MusicList2Music{" +
                "id=" + id +
                ", musiclistid='" + musiclistid + '\'' +
                ", musicid='" + musicid + '\'' +
                '}';
    }
}