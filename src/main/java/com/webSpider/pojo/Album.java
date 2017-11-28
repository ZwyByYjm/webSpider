package com.webSpider.pojo;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_album")
public class Album {
    private Integer id;

    private String albumid;

    private String albumname;

    private String composerid;

    private Date createdtime;

    private String introduction;

    private Integer followedcount;

    private Integer musiccount;

    private Integer playcount;

    private Integer commentcount;

    private Integer forwardcount;

    private String ablumurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumid() {
        return albumid;
    }

    public void setAlbumid(String albumid) {
        this.albumid = albumid == null ? null : albumid.trim();
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname == null ? null : albumname.trim();
    }

    public String getComposerid() {
        return composerid;
    }

    public void setComposerid(String composerid) {
        this.composerid = composerid == null ? null : composerid.trim();
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getFollowedcount() {
        return followedcount;
    }

    public void setFollowedcount(Integer followedcount) {
        this.followedcount = followedcount;
    }

    public Integer getMusiccount() {
        return musiccount;
    }

    public void setMusiccount(Integer musiccount) {
        this.musiccount = musiccount;
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public Integer getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(Integer commentcount) {
        this.commentcount = commentcount;
    }

    public Integer getForwardcount() {
        return forwardcount;
    }

    public void setForwardcount(Integer forwardcount) {
        this.forwardcount = forwardcount;
    }

    public String getAblumurl() {
        return ablumurl;
    }

    public void setAblumurl(String ablumurl) {
        this.ablumurl = ablumurl == null ? null : ablumurl.trim();
    }

    @Override
    public String toString()
    {
        return "Album{" +
                "id=" + id +
                ", albumid='" + albumid + '\'' +
                ", albumname='" + albumname + '\'' +
                ", composerid='" + composerid + '\'' +
                ", createdtime=" + createdtime +
                ", introduction='" + introduction + '\'' +
                ", followedcount=" + followedcount +
                ", musiccount=" + musiccount +
                ", playcount=" + playcount +
                ", commentcount=" + commentcount +
                ", forwardcount=" + forwardcount +
                ", ablumurl='" + ablumurl + '\'' +
                '}';
    }
}