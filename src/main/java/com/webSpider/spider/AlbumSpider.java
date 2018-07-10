package com.webSpider.spider;

import com.webSpider.dao.AlbumMapper;
import com.webSpider.pojo.Album;
import com.webSpider.spider.downloader.HttpClientDownloader;
import com.webSpider.spider.pipeline.AlbumPipeline;
import com.webSpider.spider.process.AlbumProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * 爬取专辑相关信息
 *
 * @author 印佳明
 * @create 2018-07-04 9:27
 */
@Controller
@RequestMapping(value = "albumSpider")
public class AlbumSpider {
    @Autowired
    private AlbumPipeline albumPipeline;
    @Autowired
    private AlbumMapper albumMapper;


    @RequestMapping(value = "album")
    @ResponseBody
    public String album()
    {
        List<Album> albumList = albumMapper.selectAllNullComposerId();

        System.out.println("数量:" + albumList.size());
        String[] urls = new String[albumList.size()];
        for (int i = 0; i < albumList.size(); i++)
        {
            urls[i] = albumList.get(i).getAblumurl();
        }
        Spider.create(new AlbumProcess())
                .addUrl(urls)
                .addPipeline(albumPipeline)
                .setDownloader(new HttpClientDownloader())
                .thread(1).start();
        return "spider begining！！！";
    }
}
