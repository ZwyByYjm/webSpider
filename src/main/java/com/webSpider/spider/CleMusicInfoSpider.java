package com.webSpider.spider;


import com.webSpider.dao.CleMusicInfoTemp1Mapper;
import com.webSpider.spider.pipeline.CleMusicInfoPipeline;
import com.webSpider.spider.process.CleMusicInfoProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import us.codecraft.webmagic.Spider;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Controller
@RequestMapping(value = "Cle")
public class CleMusicInfoSpider {
    @Autowired
    private CleMusicInfoPipeline cleMusicInfoPipeline;
    @Autowired
    private CleMusicInfoTemp1Mapper cleMusicInfoTemp1Mapper;


    @RequestMapping(value = "musicInfo")
    @ResponseBody
    public String music163() {
//        for (int i = 1; ; i++) {
//            Map<String, Integer> map = new HashMap<>();
//            map.put("pageBegin", (i - 1) * 100);
//            map.put("pageEnd", i * 100);
//            List<Integer> list = cleMusicInfoTemp1Mapper.selectMusicIdByPageNum(map);
//            if (Objects.isNull(list) || list.size() == 0) {
//                break;
//            }
//            String[] urls = new String[list.size()];
//            for (int j = 0; j < list.size(); j++) {
//                urls[j] = "https://music.163.com/#/song?id=" + list.get(j);
//            }

            Spider.create(new CleMusicInfoProcess())
//                    .addUrl(urls)
                    .addUrl("https://music.163.com/#/song?id=454698272")
                    .addPipeline(cleMusicInfoPipeline)
                    //.setDownloader(new GetData())
                    .thread(1).start();
//        }
        return "爬虫开始";
    }
}
