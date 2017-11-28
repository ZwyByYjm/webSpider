package com.webSpider.dao;

import com.webSpider.pojo.MusicListInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MusicListInfoMapper  extends Mapper<MusicListInfo>
{
    //int deleteByPrimaryKey(Integer id);
    //
    //int insert(MusicListInfo record);
    //
    //int insertSelective(MusicListInfo record);
    //
    //MusicListInfo selectByPrimaryKey(Integer id);
    //
    //int updateByPrimaryKeySelective(MusicListInfo record);
    //
    //int updateByPrimaryKey(MusicListInfo record);

    List<MusicListInfo> selectByMusicListId(String musiclistid);
}