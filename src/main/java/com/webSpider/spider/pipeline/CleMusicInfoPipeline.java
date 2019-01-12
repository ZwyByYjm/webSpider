package com.webSpider.spider.pipeline;

import com.webSpider.dao.CleMusicInfoTemp1Mapper;
import com.webSpider.pojo.CleMusicInfoTemp1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Component
@Transactional
public class CleMusicInfoPipeline implements Pipeline {
    @Autowired
    private CleMusicInfoTemp1Mapper cleMusicInfoTemp1Mapper;

    @Override
    public void process(ResultItems resultItems, Task task) {
        CleMusicInfoTemp1 cleMusicInfoTemp1 = resultItems.get("cleMusicInfoTemp1");
        if (cleMusicInfoTemp1 != null) {
            CleMusicInfoTemp1 cleMusicInfoTemp11 = cleMusicInfoTemp1Mapper.selectByMusicId(cleMusicInfoTemp1.getMusicid()).get(0);
            cleMusicInfoTemp11.setComposerid(cleMusicInfoTemp1.getComposerid());
            cleMusicInfoTemp11.setName(cleMusicInfoTemp1.getName());
            cleMusicInfoTemp1Mapper.updateByMusicId(cleMusicInfoTemp11);
        }
    }
}
