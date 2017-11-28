package com.webSpider.dao;

import com.webSpider.pojo.Language;

public interface LanguageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Language record);

    int insertSelective(Language record);

    Language selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Language record);

    int updateByPrimaryKey(Language record);
}