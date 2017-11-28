package com.webSpider.dao;

import com.webSpider.pojo.Album2Music;

public interface Album2MusicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Album2Music record);

    int insertSelective(Album2Music record);

    Album2Music selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Album2Music record);

    int updateByPrimaryKey(Album2Music record);
}