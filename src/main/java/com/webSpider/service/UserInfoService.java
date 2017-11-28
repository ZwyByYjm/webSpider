package com.webSpider.service;


import com.webSpider.pojo.UserInfo;

import java.util.List;

/**
 * @author 印佳明
 * @create 2017-10-26 22:48
 */
public interface UserInfoService
{
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectByUserno(String userno);
}
