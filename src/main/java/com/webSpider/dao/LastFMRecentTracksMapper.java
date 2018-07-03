package com.webSpider.dao;

import com.webSpider.pojo.LastFMRecentTracks;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LastFMRecentTracksMapper extends Mapper<LastFMRecentTracks> {
    int deleteByTrackName(String trackName);

    List<LastFMRecentTracks> selectByTrackName(String trackName);

    List<LastFMRecentTracks> selectByUserAndTrack(String user,String trackName);

    int updateByUserSelective(LastFMRecentTracks record);
}