package com.webSpider.spider.pipeline;

import com.webSpider.dao.AlbumMapper;
import com.webSpider.pojo.Album;
import com.webSpider.spider.schedule.QueueNameConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import utils.RedisUtil;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Component
@Transactional
public class AlbumPipeline implements Pipeline {
    @Autowired
    private AlbumMapper albumMapper;


    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println(resultItems.getRequest().getUrl());
        if (!resultItems.getRequest().getUrl().contains("http://music.163.com/discover/playlist/")) {

            try {
                Album album = resultItems.get("album");
                albumMapper.updateByAlbumIdSelective(album);

            } catch (Exception e) {
                System.out.println("更新专辑信息数据异常:" + e.getCause());
                RedisUtil.push(QueueNameConstant.QUEUE_ALBUM_ERROR, resultItems.getRequest().getUrl());
                //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
            }

        }
    }
}
