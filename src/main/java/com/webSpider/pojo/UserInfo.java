package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_userinfo")
public class UserInfo
{
    private Integer id;

    private String userid;

    private String name;

    private String sex;

    private Integer level;

    private Integer listensongscount;

    private Integer followscount;

    private Integer fanscount;

    private String userurl;


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserid()
    {
        return userid;
    }

    public void setUserid(String userid)
    {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name == null ? null : name.trim();
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel(Integer level)
    {
        this.level = level;
    }

    public Integer getListensongscount()
    {
        return listensongscount;
    }

    public void setListensongscount(Integer listensongscount)
    {
        this.listensongscount = listensongscount;
    }

    public Integer getFollowscount()
    {
        return followscount;
    }

    public void setFollowscount(Integer followscount)
    {
        this.followscount = followscount;
    }

    public Integer getFanscount()
    {
        return fanscount;
    }

    public void setFanscount(Integer fanscount)
    {
        this.fanscount = fanscount;
    }

    public String getUserurl()
    {
        return userurl;
    }

    public void setUserurl(String userurl)
    {
        this.userurl = userurl == null ? null : userurl.trim();
    }

    @Override
    public String toString()
    {
        return "UserInfo{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", level=" + level +
                ", listensongscount=" + listensongscount +
                ", followscount=" + followscount +
                ", fanscount=" + fanscount +
                ", userurl='" + userurl + '\'' +
                '}';
    }

}