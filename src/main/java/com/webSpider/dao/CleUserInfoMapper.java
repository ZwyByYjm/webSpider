package com.webSpider.dao;

import com.webSpider.pojo.CleUserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface CleUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CleUserInfo record);

    int insertSelective(CleUserInfo record);

    CleUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CleUserInfo record);

    int updateByPrimaryKey(CleUserInfo record);
}