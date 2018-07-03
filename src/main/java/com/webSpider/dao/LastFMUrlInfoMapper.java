package com.webSpider.dao;

import com.webSpider.pojo.LastFMUrlInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LastFMUrlInfoMapper extends Mapper<LastFMUrlInfo> {
    int deleteByUrl(String url);

//    int insert(LastFMUrlInfo record);
//
//    int insertSelective(LastFMUrlInfo record);

    List<LastFMUrlInfo> selectByUrl(String url);

    int updateByUrlSelective(LastFMUrlInfo record);
//
//    int updateByPrimaryKey(LastFMUrlInfo record);
}