package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "cle_musicinfo_temp1")
public class CleMusicInfoTemp1 {
    private Integer id;

    private String musicid;

    private String name;

    private String composerid;

    private String tag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMusicid() {
        return musicid;
    }

    public void setMusicid(String musicid) {
        this.musicid = musicid == null ? null : musicid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getComposerid() {
        return composerid;
    }

    public void setComposerid(String composerid) {
        this.composerid = composerid == null ? null : composerid.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }
}