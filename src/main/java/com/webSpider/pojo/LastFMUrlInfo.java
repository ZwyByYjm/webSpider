package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "lastfm_urlinfo")
public class LastFMUrlInfo {
    private Integer id;

    private String url;

    private String perpage;

    private String total;

    private String totalpages;

    private String page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPerpage() {
        return perpage;
    }

    public void setPerpage(String perpage) {
        this.perpage = perpage == null ? null : perpage.trim();
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total == null ? null : total.trim();
    }

    public String getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(String totalpages) {
        this.totalpages = totalpages == null ? null : totalpages.trim();
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page == null ? null : page.trim();
    }

    @Override
    public String toString() {
        return "LastFMUrlInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", perpage='" + perpage + '\'' +
                ", total='" + total + '\'' +
                ", totalpages='" + totalpages + '\'' +
                ", page='" + page + '\'' +
                '}';
    }
}