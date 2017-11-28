package com.webSpider.dao;

import com.webSpider.pojo.User2Follow;

public interface User2FollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User2Follow record);

    int insertSelective(User2Follow record);

    User2Follow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User2Follow record);

    int updateByPrimaryKey(User2Follow record);
}