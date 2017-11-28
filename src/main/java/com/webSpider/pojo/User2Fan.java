package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_user_fan")
public class User2Fan {
    private Integer id;

    private String userid;

    private String fanuserid;

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

    public String getFanuserid() {
        return fanuserid;
    }

    public void setFanuserid(String fanuserid) {
        this.fanuserid = fanuserid == null ? null : fanuserid.trim();
    }
}