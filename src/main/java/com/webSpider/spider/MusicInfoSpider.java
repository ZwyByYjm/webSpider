package com.webSpider.spider;


import com.webSpider.spider.downloader.HttpClientDownloader;
import com.webSpider.spider.pipeline.MusicInfoPipeline;
import com.webSpider.spider.process.MusicInfoProcess;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Controller
@RequestMapping(value = "musicInfoSpider")
public class MusicInfoSpider
{
    @Autowired
    private MusicInfoPipeline musicInfoPipeline;


    @RequestMapping(value = "musicInfo")
    @ResponseBody
    public String music163()
    {
        File f = new File("G:\\ideawork\\webSpider\\src\\main\\resources\\music_error.txt");
        List<String> musicUrls = null;
        try
        {
            musicUrls = FileUtils.readLines(f);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("数量:" + musicUrls.size());
        String[] urls = new String[musicUrls.size()];
        for (int i = 0; i < musicUrls.size(); i++)
        {
            urls[i] = musicUrls.get(i);
        }
        Spider.create(new MusicInfoProcess())
                .addUrl(urls)
                //.addUrl("http://www.douban.com/people/4400922/")
                .addPipeline(musicInfoPipeline)
                .setDownloader(new HttpClientDownloader())
                //.scheduler(new RedisScheduler(pool, Integer.parseInt(ConfigUtil.getProperty("redis", "redis.index")), QueueNameConstant.QUEUE_MUSICLIST_INFO))
                .thread(1).start();
        return "spider begining！！！";
    }
}
