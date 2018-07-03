package com.webSpider.dao;

import com.webSpider.pojo.LastFMTracks;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LastFMTracksMapper extends Mapper<LastFMTracks> {
    int deleteByName(String name);

    List<LastFMTracks>  selectByName(String name);

    int updateByNameSelective(LastFMTracks record);
}