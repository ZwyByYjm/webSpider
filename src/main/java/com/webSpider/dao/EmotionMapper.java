package com.webSpider.dao;

import com.webSpider.pojo.Emotion;

public interface EmotionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Emotion record);

    int insertSelective(Emotion record);

    Emotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Emotion record);

    int updateByPrimaryKey(Emotion record);
}