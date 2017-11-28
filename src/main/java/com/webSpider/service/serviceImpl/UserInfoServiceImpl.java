package com.webSpider.service.serviceImpl;


import com.webSpider.dao.UserInfoMapper;
import com.webSpider.pojo.UserInfo;
import com.webSpider.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author 印佳明
 * @create 2017-10-26 22:48
 */
//@Service(value = "userInfoService")
public class UserInfoServiceImpl implements UserInfoService
{
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo selectByPrimaryKey(Integer id)
    {
        return this.userInfoMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(UserInfo record)
    {
        return 0;
    }

    public int updateByPrimaryKey(UserInfo record)
    {
        return 0;
    }

    public int deleteByPrimaryKey(Integer id)
    {
        return 0;
    }

    public int insert(UserInfo record)
    {
        return this.userInfoMapper.insert(record);
    }

    public int insertSelective(UserInfo record)
    {
        return 0;
    }

    public List<UserInfo> selectByUserno(String userno)
    {
        return null;
    }
}
