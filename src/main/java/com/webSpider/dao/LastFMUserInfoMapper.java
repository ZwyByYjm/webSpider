package com.webSpider.dao;

import com.webSpider.pojo.LastFMUserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface LastFMUserInfoMapper extends Mapper<LastFMUserInfo> {
    int deleteByName(String name);

    int updateByNameSelective(LastFMUserInfo record);

    int selectUserSum();

    List<LastFMUserInfo> selectByName(String name);

    List<LastFMUserInfo> selectUserByStatus(Integer status);

}