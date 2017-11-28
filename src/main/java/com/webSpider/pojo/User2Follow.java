package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_user_follow")
public class User2Follow {
    private Integer id;

    private String userid;

    private String followuserid;

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

    public String getFollowuserid() {
        return followuserid;
    }

    public void setFollowuserid(String followuserid) {
        this.followuserid = followuserid == null ? null : followuserid.trim();
    }
}