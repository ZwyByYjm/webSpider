package com.webSpider.dao;

import com.webSpider.pojo.CleMusicInfoTemp1;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface CleMusicInfoTemp1Mapper  extends Mapper<CleMusicInfoTemp1> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(CleMusicInfoTemp1 record);
//
//    int insertSelective(CleMusicInfoTemp1 record);
//
//    CleMusicInfoTemp1 selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(CleMusicInfoTemp1 record);
//
//    int updateByPrimaryKey(CleMusicInfoTemp1 record);


    List<Integer> selectMusicIdByPageNum(Map map);

    List<CleMusicInfoTemp1> selectByMusicId(String musicid);
    Integer updateByMusicId(CleMusicInfoTemp1 cleMusicInfoTemp1);
}