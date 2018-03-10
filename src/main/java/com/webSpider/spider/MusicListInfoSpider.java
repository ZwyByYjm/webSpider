package com.webSpider.spider;


import com.webSpider.dao.MusicInfoMapper;
import com.webSpider.dao.MusicListInfoMapper;
import com.webSpider.pojo.MusicListInfo;
import com.webSpider.spider.pipeline.MusicListInfoPipeline;
import com.webSpider.spider.process.MusicListInfoProcess;
import com.webSpider.spider.schedule.QueueNameConstant;
import com.webSpider.spider.schedule.RedisScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.Spider;
import utils.ConfigUtil;
import utils.RedisUtil;

import java.util.List;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Controller
@RequestMapping(value = "musicListInfoSpider")
public class MusicListInfoSpider implements Crawler
{
    @Autowired
    private MusicListInfoPipeline musicListInfoPipeline;

    @Autowired
    private MusicListInfoMapper musicListInfoMapper;

    @Autowired
    private MusicInfoMapper musicInfoMapper;

    @RequestMapping(value = "selectAllTag")
    @ResponseBody
    public void selectAllTag()
    {
        List<MusicListInfo> list = musicListInfoMapper.selectAllTag();

        for (MusicListInfo ls : list)
        {
            System.out.println(musicInfoMapper.updateByTag(ls.getTag()));
        }
    }

    @Override
    public void crawl()
    {
        RedisUtil.init();
        //File f = new File("E:\\ideawork\\music163WebSpider\\src\\main\\resources\\downloaded\\user_403.txt");
        //// 排除已经下载过的地址
        //List<String> userUrls = null;
        //try
        //{
        //    userUrls = FileUtils.readLines(f);
        //} catch (IOException e)
        //{
        //    e.printStackTrace();
        //}
        //
        //System.out.println("数量:" + userUrls.size());
        //String[] urls = new String[userUrls.size()];
        //for (int i = 0; i < userUrls.size(); i++)
        //{
        //    urls[i] = userUrls.get(i);
        //}

        Spider.create(new MusicListInfoProcess())
                .addUrl("http://music.163.com/discover/playlist/?cat=华语")
                //.addUrl("https://www.douban.com/people/4400922/")
                .addPipeline(musicListInfoPipeline)
                .scheduler(new RedisScheduler(pool, Integer.parseInt(ConfigUtil.getProperty("redis", "redis.index")), QueueNameConstant.QUEUE_MUSICLIST_INFO))
                .thread(1).start();
    }

    //public static void main(String[] args)
    //{
    //    applicationContext.getBean(MusicListInfoSpider.class).crawl();
    //}

    @RequestMapping(value = "musicList")
    @ResponseBody
    public String music163()
    {
        JedisPool pool = RedisUtil.init();
        //RedisUtil.init();
        Spider.create(new MusicListInfoProcess())
                .addUrl("http://music.163.com/discover/playlist/?cat=" + ConfigUtil.getProperty("redis","musiclist.name") +"")
                //.addUrl("http://www.douban.com/people/4400922/")
                .addPipeline(musicListInfoPipeline)
                //.setDownloader(new HttpClientDownloader())
                .scheduler(new RedisScheduler(pool, Integer.parseInt(ConfigUtil.getProperty("redis", "redis.index")), QueueNameConstant.QUEUE_MUSICLIST_INFO))
                .thread(1).start();
        return "spider begining!!!!!!!!!!!!!!!!!!";
    }
}
