package com.webSpider.service.serviceImpl;

import com.webSpider.dao.LastFMUrlInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 印佳明
 * @create 2018-06-06 19:47
 */
@Service
@Transactional
public class LastFMUrlInfoServiceImpl {
    @Autowired
    private LastFMUrlInfoMapper lastFMUrlInfoMapper;

}
