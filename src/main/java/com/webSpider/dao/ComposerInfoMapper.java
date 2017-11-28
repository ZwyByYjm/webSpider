package com.webSpider.dao;

import com.webSpider.pojo.ComposerInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
@Repository
public interface ComposerInfoMapper extends Mapper<ComposerInfo>
{
    //int deleteByPrimaryKey(Integer id);
    //
    //int insert(ComposerInfo record);
    //
    //int insertSelective(ComposerInfo record);
    //
    //ComposerInfo selectByPrimaryKey(Integer id);
    //
    //int updateByPrimaryKeySelective(ComposerInfo record);
    //
    //int updateByPrimaryKey(ComposerInfo record);


    List<ComposerInfo> selectByComposerId(String composerid);
}