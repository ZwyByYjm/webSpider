package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_musiclistinfo")
public class MusicListInfo {
    private Integer id;

    private String musiclistid;

    private String musiclistname;

    private String createrid;

    private String tag;

    private String introduction;

    private Integer followedcount;

    private Integer musiccount;

    private Integer playcount;

    private Integer commentcount;

    private Integer forwardcount;

    private String listurl;

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

    public String getMusiclistname() {
        return musiclistname;
    }

    public void setMusiclistname(String musiclistname) {
        this.musiclistname = musiclistname == null ? null : musiclistname.trim();
    }

    public String getCreaterid() {
        return createrid;
    }

    public void setCreaterid(String createrid) {
        this.createrid = createrid == null ? null : createrid.trim();
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
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

    public String getListurl() {
        return listurl;
    }

    public void setListurl(String listurl) {
        this.listurl = listurl == null ? null : listurl.trim();
    }

    @Override
    public String toString()
    {
        return "MusicListInfo{" +
                "id=" + id +
                ", musiclistid='" + musiclistid + '\'' +
                ", musiclistname='" + musiclistname + '\'' +
                ", createrid='" + createrid + '\'' +
                ", tag='" + tag + '\'' +
                ", introduction='" + introduction + '\'' +
                ", followedcount=" + followedcount +
                ", musiccount=" + musiccount +
                ", playcount=" + playcount +
                ", commentcount=" + commentcount +
                ", forwardcount=" + forwardcount +
                ", listurl='" + listurl + '\'' +
                '}';
    }
}