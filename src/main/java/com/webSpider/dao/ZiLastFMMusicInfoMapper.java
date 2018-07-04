package com.webSpider.dao;

import com.webSpider.pojo.UserInfo;
import com.webSpider.pojo.ZiLastFMMusicInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ZiLastFMMusicInfoMapper extends Mapper<UserInfo> {


    List<ZiLastFMMusicInfo> selectByMusicName(String musicName);


    List<ZiLastFMMusicInfo> selectByStatus(Integer status);

//    int deleteByPrimaryKey(Integer id);
//
//    int insert(ZiLastFMMusicInfo record);
//
//    int insertSelective(ZiLastFMMusicInfo record);
//
//    ZiLastFMMusicInfo selectByPrimaryKey(Integer id);
//
    int updateByMusicNameSelective(ZiLastFMMusicInfo record);
//
//    int updateByPrimaryKey(ZiLastFMMusicInfo record);


}