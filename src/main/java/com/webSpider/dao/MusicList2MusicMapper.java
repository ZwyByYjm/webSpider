package com.webSpider.dao;

import com.webSpider.pojo.MusicList2Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface MusicList2MusicMapper extends Mapper<MusicList2Music>
{
    //int deleteByPrimaryKey(Integer id);
    //
    //int insert(MusicList2Music record);
    //
    //int insertSelective(MusicList2Music record);
    //
    //MusicList2Music selectByPrimaryKey(Integer id);
    //
    //int updateByPrimaryKeySelective(MusicList2Music record);
    //
    //int updateByPrimaryKey(MusicList2Music record);

    List<MusicList2Music> selectByMusicListIdMusicId(@Param("musiclistid")String musiclistid,@Param("musicid") String musicid);
}