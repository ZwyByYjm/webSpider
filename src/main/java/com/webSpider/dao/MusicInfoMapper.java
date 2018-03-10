package com.webSpider.dao;

import com.webSpider.pojo.MusicInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MusicInfoMapper extends Mapper<MusicInfo>
{
    //int deleteByPrimaryKey(Integer id);
    //
    //int insert(MusicInfo record);
    //
    //int insertSelective(MusicInfo record);
    //
    //MusicInfo selectByPrimaryKey(Integer id);
    //
    //int updateByPrimaryKeySelective(MusicInfo record);
    //
    //int updateByPrimaryKey(MusicInfo record);

    List<MusicInfo> selectByMusicId(String musicid);

    int updateByTag(String tag);
}