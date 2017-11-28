package com.webSpider.pojo;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_musicinfo")
public class MusicInfo {
    private Integer id;

    private String musicid;

    private String name;

    private Date createdtime;

    private String composerid;

    private Integer commentcount;

    private String tag;

    private Integer emotionid;

    private Integer styleid;

    private Integer languageid;

    private String albumid;

    private String musicurl;

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

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getComposerid() {
        return composerid;
    }

    public void setComposerid(String composerid) {
        this.composerid = composerid == null ? null : composerid.trim();
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount ;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public Integer getEmotionid() {
        return emotionid;
    }

    public void setEmotionid(Integer emotionid) {
        this.emotionid = emotionid;
    }

    public Integer getStyleid() {
        return styleid;
    }

    public void setStyleid(Integer styleid) {
        this.styleid = styleid;
    }

    public Integer getLanguageid() {
        return languageid;
    }

    public void setLanguageid(Integer languageid) {
        this.languageid = languageid;
    }

    public String getAlbumid() {
        return albumid;
    }

    public void setAlbumid(String albumid) {
        this.albumid = albumid == null ? null : albumid.trim();
    }

    public String getMusicurl() {
        return musicurl;
    }

    public void setMusicurl(String musicurl) {
        this.musicurl = musicurl == null ? null : musicurl.trim();
    }

    @Override
    public String toString()
    {
        return "MusicInfo{" +
                "id=" + id +
                ", musicid='" + musicid + '\'' +
                ", name='" + name + '\'' +
                ", createdtime=" + createdtime +
                ", composerid='" + composerid + '\'' +
                ", commentcount=" + commentcount +
                ", tag='" + tag + '\'' +
                ", emotionid=" + emotionid +
                ", styleid=" + styleid +
                ", languageid=" + languageid +
                ", albumid='" + albumid + '\'' +
                ", musicurl='" + musicurl + '\'' +
                '}';
    }
}