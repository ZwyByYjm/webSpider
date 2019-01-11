package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "cle_composerinfo")
public class CleComposerInfo {
    private Integer id;

    private String composerid;

    private String name;

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
}