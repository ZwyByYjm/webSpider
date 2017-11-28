package com.webSpider.dao;

import com.webSpider.pojo.Album;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface AlbumMapper extends Mapper<Album>{
    //int deleteByPrimaryKey(Integer id);
    //
    //int insert(Album record);
    //
    //int insertSelective(Album record);
    //
    //Album selectByPrimaryKey(Integer id);
    //
    //int updateByPrimaryKeySelective(Album record);
    //
    //int updateByPrimaryKey(Album record);


    List<Album> selectByAlbumId(String albumid);
}