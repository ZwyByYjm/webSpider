package com.webSpider.dao;

import com.webSpider.pojo.Style;

public interface StyleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Style record);

    int insertSelective(Style record);

    Style selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Style record);

    int updateByPrimaryKey(Style record);
}