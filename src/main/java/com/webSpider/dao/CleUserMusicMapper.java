package com.webSpider.dao;

import com.webSpider.pojo.CleUserMusic;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CleUserMusicMapper  extends Mapper<CleUserMusic> {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(CleUserMusic record);
//
//    int insertSelective(CleUserMusic record);
//
//    CleUserMusic selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(CleUserMusic record);
//
//    int updateByPrimaryKey(CleUserMusic record);

    List<CleUserMusic> selectByMusicId(String musicid);

}