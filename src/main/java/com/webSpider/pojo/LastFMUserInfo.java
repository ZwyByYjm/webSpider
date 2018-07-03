package com.webSpider.pojo;

import javax.persistence.Table;
import java.util.Objects;

@Table(name = "lastfm_userinfo")
public class LastFMUserInfo {
    private Integer id;

    private String name;

    private String realname;

    private String age;

    private String bootstrap;

    private String country;

    private String gender;

    private String playcount;

    private String playlists;

    private String registered;

    private String subscriber;

    private String type;

    private String url;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public String getBootstrap() {
        return bootstrap;
    }

    public void setBootstrap(String bootstrap) {
        this.bootstrap = bootstrap == null ? null : bootstrap.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String playlists) {
        this.playlists = playlists == null ? null : playlists.trim();
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber == null ? null : subscriber.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastFMUserInfo that = (LastFMUserInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(realname, that.realname) &&
                Objects.equals(age, that.age) &&
                Objects.equals(bootstrap, that.bootstrap) &&
                Objects.equals(country, that.country) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(playcount, that.playcount) &&
                Objects.equals(playlists, that.playlists) &&
                Objects.equals(registered, that.registered) &&
                Objects.equals(subscriber, that.subscriber) &&
                Objects.equals(type, that.type) &&
                Objects.equals(url, that.url) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, realname, age, bootstrap, country, gender, playcount, playlists, registered, subscriber, type, url, status);
    }

    @Override
    public String toString() {
        return "LastFMUserInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", realname='" + realname + '\'' +
                ", age='" + age + '\'' +
                ", bootstrap='" + bootstrap + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", playcount=" + playcount +
                ", playlists='" + playlists + '\'' +
                ", registered=" + registered +
                ", subscriber='" + subscriber + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }
}