package com.webSpider.spider;


import com.webSpider.dao.User2MusicMapper;
import com.webSpider.dao.UserInfoMapper;
import com.webSpider.spider.pipeline.UserMusicPipeline;
import com.webSpider.spider.process.UserMusicProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Controller
@RequestMapping(value = "User")
public class UserMusicSpider implements Crawler {
    @Autowired
    private UserMusicPipeline userMusicPipeline;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private User2MusicMapper user2MusicMapper;

    @Override
    public void crawl() {
        Spider.create(new UserMusicProcess())
                .addUrl("http://music.163.com/user/home?id=563562614")
                //.addUrl("https://www.douban.com/people/4400922/")
                .addPipeline(userMusicPipeline)
                .setDownloader(new HttpClientDownloader())
                .thread(1).run();
    }

    @RequestMapping(value = "music163")
    @ResponseBody
    public String music163() {

        for (int i = 1; ; i++) {
            Map<String,Integer> map = new HashMap<>();
            map.put("pageBegin",(i-1)*100);
            map.put("pageEnd",i*100);
            List<Integer> list = userInfoMapper.selectUserByPageNum(map);
            if (Objects.isNull(list)||list.size()==0){
                break;
            }
            String[] urls = new String[list.size()];
            for (int j = 0;j < list.size();j++){
                urls[j]="https://music.163.com/#/user/songs/rank?id="+list.get(j);
            }

            Spider.create(new UserMusicProcess())
                    .addUrl(urls)
                    //.addUrl("https://www.douban.com/people/4400922/")
                    .addPipeline(userMusicPipeline)
                    //.setDownloader(new GetData())
                    .thread(1).start();
        }


//        Spider.create(new UserMusicProcess())
//                .addUrl("https://music.163.com/#/user/songs/rank?id=96079630")
//                //.addUrl("https://www.douban.com/people/4400922/")
//                .addPipeline(userMusicPipeline)
//                //.setDownloader(new GetData())
//                .thread(1).start();
        return "爬虫开始";
    }
}
