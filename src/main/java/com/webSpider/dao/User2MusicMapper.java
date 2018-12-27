package com.webSpider.dao;

import com.webSpider.pojo.User2Music;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface User2MusicMapper extends Mapper<User2Music> {
    List<User2Music> selectByUseridAndMusicid(@Param("userid")String userid,@Param("musicid")String musicid);
}