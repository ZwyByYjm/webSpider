package com.webSpider.dao;

import com.webSpider.pojo.User2Music;

public interface User2MusicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User2Music record);

    int insertSelective(User2Music record);

    User2Music selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User2Music record);

    int updateByPrimaryKey(User2Music record);
}