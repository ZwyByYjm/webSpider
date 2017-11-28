package com.webSpider.dao;

import com.webSpider.pojo.User2MusicList;

public interface User2MusicListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User2MusicList record);

    int insertSelective(User2MusicList record);

    User2MusicList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User2MusicList record);

    int updateByPrimaryKey(User2MusicList record);
}