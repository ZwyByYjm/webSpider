package com.webSpider.dao;

import com.webSpider.pojo.UserInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo>
{
    List<UserInfo> selectByUserid(String userid);

    List<Integer> selectUserByPageNum(Map map);

    Integer updateByUserIdAndLevel(Map map);
}