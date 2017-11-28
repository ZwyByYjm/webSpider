package com.webSpider.dao;

import com.webSpider.pojo.User2Fan;

public interface User2FanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User2Fan record);

    int insertSelective(User2Fan record);

    User2Fan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User2Fan record);

    int updateByPrimaryKey(User2Fan record);
}