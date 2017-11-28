package com.webSpider.dao;

import com.webSpider.pojo.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo>
{
    List<UserInfo> selectByUserid(String userid);
}