package com.webSpider.dao;

import com.webSpider.pojo.LastFMLoveTracks;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LastFMLoveTracksMapper extends Mapper<LastFMLoveTracks> {
    int deleteByTrackName(String user);

    List<LastFMLoveTracks> selectByTrackName(String user);

    List<LastFMLoveTracks> selectByUserAndTrack(String user,String trackName);

    int updateByUserSelective(LastFMLoveTracks record);
}