package com.webSpider.spider;


import com.webSpider.spider.pipeline.UserInfoPipeline;
import com.webSpider.spider.process.UserInfoProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Controller
@RequestMapping(value = "spider")
public class UserInfoSpider implements Crawler
{
    @Autowired
    private UserInfoPipeline userInfoPipeline;


    @Override
    public void crawl()
    {
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

        Spider.create(new UserInfoProcess())
                .addUrl("http://music.163.com/user/home?id=563562614")
                //.addUrl("https://www.douban.com/people/4400922/")
                .addPipeline(userInfoPipeline)
                .setDownloader(new HttpClientDownloader())
                .thread(1).run();
    }

    @RequestMapping(value = "music163")
    @ResponseBody
    public String music163()
    {
        Spider.create(new UserInfoProcess())
                .addUrl("http://music.163.com/user/home?id=119543810")
                //.addUrl("https://www.douban.com/people/4400922/")
                .addPipeline(userInfoPipeline)
                //.setDownloader(new HttpClientDownloader())
                .thread(1).start();
        return "爬虫开始";
    }
}
