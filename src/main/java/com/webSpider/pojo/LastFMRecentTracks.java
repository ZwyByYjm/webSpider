package com.webSpider.pojo;

import javax.persistence.Table;
import java.util.Date;

@Table(name = "lastfm_recenttracks")
public class LastFMRecentTracks {
    private Integer id;

    private String user;

    private String trackName;

    private Date dateText;

    private String dateUts;

    private Integer preference;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName == null ? null : trackName.trim();
    }

    public Date getDateText() {
        return dateText;
    }

    public void setDateText(Date dateText) {
        this.dateText = dateText;
    }

    public String getDateUts() {
        return dateUts;
    }

    public void setDateUts(String dateUts) {
        this.dateUts = dateUts == null ? null : dateUts.trim();
    }

    public Integer getPreference() {
        return preference;
    }

    public void setPreference(Integer preference) {
        this.preference = preference;
    }

    @Override
    public String toString() {
        return "LastFMRecentTracks{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", trackName='" + trackName + '\'' +
                ", dateText=" + dateText +
                ", dateUts='" + dateUts + '\'' +
                ", preference=" + preference +
                '}';
    }
}