package com.webSpider.pojo;

import javax.persistence.Table;

@Table(name = "tb_album_music")
public class Album2Music {
    private Integer id;

    private String albumid;

    private String musicid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlbumid() {
        return albumid;
    }

    public void setAlbumid(String albumid) {
        this.albumid = albumid == null ? null : albumid.trim();
    }

    public String getMusicid() {
        return musicid;
    }

    public void setMusicid(String musicid) {
        this.musicid = musicid == null ? null : musicid.trim();
    }

    @Override
    public String toString()
    {
        return "Album2Music{" +
                "id=" + id +
                ", albumid='" + albumid + '\'' +
                ", musicid='" + musicid + '\'' +
                '}';
    }
}