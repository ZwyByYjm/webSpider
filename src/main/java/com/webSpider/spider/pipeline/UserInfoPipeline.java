package com.webSpider.spider.pipeline;

import com.webSpider.dao.UserInfoMapper;
import com.webSpider.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Component
@Transactional
public class UserInfoPipeline implements Pipeline
{
    @Autowired
    private UserInfoMapper userInfoMapper;

    //public static UserInfoPipeline userInfoPipeline;
    //
    //@PostConstruct
    //public void init() {
    //    userInfoPipeline = this;
    //}

    @Override
    public void process(ResultItems resultItems, Task task)
    {
        for (Map.Entry<String, Object> entry:resultItems.getAll().entrySet())
        {
            try
            {
                UserInfo user = (UserInfo) entry.getValue();
                if (userInfoMapper.selectByUserid(user.getUserid()).size() <= 0)
                    userInfoMapper.insert(user);
            }
            catch(Exception e)
            {
                System.out.println("插入用户数据异常:" + e.getCause() );
                //RedisUtil.push(QueueNameConstant.QUEUE_USER_ERROR,resultItems.getRequest().getUrl());
            }
        }
    }

    //public void process(UserInfo userInfo, Task task)
    //{
    //    try
    //    {
    //        if (userInfoPipeline.userInfoMapper.selectByUserno(userInfo.getUserno()).size() <= 0)
    //            userInfoPipeline.userInfoMapper.insert(userInfo);
    //    }
    //    catch(Exception e)
    //    {
    //        System.out.println("插入用户数据异常:" + e.getCause() );
    //        //RedisUtil.push(QueueNameConstant.QUEUE_USER_ERROR,resultItems.getRequest().getUrl());
    //    }
    //}
}
