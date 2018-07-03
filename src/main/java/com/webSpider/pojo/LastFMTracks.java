package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "lastfm_track")
public class LastFMTracks {
    private Integer id;

    private String name;

    private String albumText;

    private String albumMbid;

    private String artistText;

    private String artistMbid;

    private String mbid;

    private String streamable;

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

    public String getAlbumText() {
        return albumText;
    }

    public void setAlbumText(String albumText) {
        this.albumText = albumText == null ? null : albumText.trim();
    }

    public String getAlbumMbid() {
        return albumMbid;
    }

    public void setAlbumMbid(String albumMbid) {
        this.albumMbid = albumMbid == null ? null : albumMbid.trim();
    }

    public String getArtistText() {
        return artistText;
    }

    public void setArtistText(String artistText) {
        this.artistText = artistText == null ? null : artistText.trim();
    }

    public String getArtistMbid() {
        return artistMbid;
    }

    public void setArtistMbid(String artistMbid) {
        this.artistMbid = artistMbid == null ? null : artistMbid.trim();
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid == null ? null : mbid.trim();
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable == null ? null : streamable.trim();
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
    public String toString() {
        return "LastFMTracks{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", albumText='" + albumText + '\'' +
                ", albumMbid='" + albumMbid + '\'' +
                ", artistText='" + artistText + '\'' +
                ", artistMbid='" + artistMbid + '\'' +
                ", mbid='" + mbid + '\'' +
                ", streamable='" + streamable + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }
}