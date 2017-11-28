package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_composerinfo")
public class ComposerInfo {
    private Integer id;

    private String composerid;

    private String name;

    private String introduction;

    private String hotmusicname;

    private String composerurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComposerid() {
        return composerid;
    }

    public void setComposerid(String composerid) {
        this.composerid = composerid == null ? null : composerid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public String getHotmusicname() {
        return hotmusicname;
    }

    public void setHotmusicname(String hotmusicname) {
        this.hotmusicname = hotmusicname == null ? null : hotmusicname.trim();
    }

    public String getComposerurl() {
        return composerurl;
    }

    public void setComposerurl(String composerurl) {
        this.composerurl = composerurl == null ? null : composerurl.trim();
    }
}