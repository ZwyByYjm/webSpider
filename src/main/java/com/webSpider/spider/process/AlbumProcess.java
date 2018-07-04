package com.webSpider.spider.process;


import com.webSpider.pojo.Album;
import org.apache.http.HttpHost;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import utils.FileUtils;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */

@Component
public class AlbumProcess implements PageProcessor {


    public static AlbumProcess musicListInfoProcess;  // 关键2

    @PostConstruct
    public void init() {
        musicListInfoProcess = this;
    }

    public static final String COOKIE =
            "__s_=1; _ntes_nnid=eae5803bb40e2409e51e275e6edc32ab,1505205076692; _ntes_nuid=eae5803bb40e2409e51e275e6edc32ab; usertrack=c+xxC1nJqmQrEPdeDD4OAg==; _ga=GA1.2.577059788.1506388583; JSESSIONID-WYYY=0wK8%5C6FjZBp2BFAf%2BejvH8ok8Cv7ICwHlioNZ%2BCVTxeVemV6QOTRKsNa7i%5CC04I%2FJAj7S%5Cdjllur0uTo%2Bj87GdfYPaKqyl2ygbOYTOs57JaAWFR9DeE7A2hGVV4KDiNY8HAFtpE%2Bh8lUjwKcfxyIny%2FJKw759t7UqAUN36Xe20l1gy8d%3A1509005917212; _iuqxldmzr_=32; __utma=94650624.577059788.1506388583.1509004120.1509004120.1; __utmb=94650624.2.10.1509004120; __utmc=94650624; __utmz=94650624.1509004120.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";


    //	public static final String COOKIE = "bid=\"cLFu0ljxKRE\"; gr_user_id=71a8fed9-c730-4e6b-815f-813f99b450f4; ll=\"108288\"; ap=1; viewed=\"3283973_5257905\"; __utmt=1; dbcl2=\"142128977:lKgaClsIDDI\"; ck=\"uNFe\"; gr_session_id_22c937bbd8ebd703f2d8e9445f7dfd03=c78404b1-2e3a-4bb6-afd9-45e6cfdc0d41; __utmt_douban=1; __utma=30149280.1220747617.1453995829.1455611943.1456219113.6; __utmb=30149280.4.10.1456219113; __utmc=30149280; __utmz=30149280.1456219113.6.5.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=30149280.14212; push_noty_num=0; push_doumail_num=1";
    public static final String[] AGENTS = new String[]{
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2471.2 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.12 Safari/535.11",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.4033.400 QQBrowser/9.6.12624.400"};
    // TODO:设置代理IP
    public static final HttpHost[] IPS = new HttpHost[]{
            //new HttpHost("61.135.217.7", 80),//bj ....
            //new HttpHost("123.126.32.102", 8080),//北京	高匿	HTTP...
            new HttpHost("171.13.3.113", 38839),//hnzj....
            new HttpHost("122.114.27.241", 808),//hnzj
            new HttpHost("222.89.107.84", 808),//hnzjzj....
            new HttpHost("123.161.239.5", 35770),//hnzj
            new HttpHost("127.0.0.1", 8080),
            //new HttpHost("61.135.217.3", 80),//bj...
            new HttpHost("122.114.31.177", 808),//hnzj!!!!
    };


    private Site site = Site.me().setRetryTimes(3).setCharset("UTF-8")
            .setUserAgent(AGENTS[new Random().nextInt(6)])
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive")
            .addHeader("Referer", "http://music.163.com/")
            .setSleepTime(1500)
            .setTimeOut(30000)
            .setRetryTimes(5)
            .setCycleRetryTimes(5)
            //.setHttpProxy(IPS[new Random().nextInt(6)])
            //.addCookie("Cookie", COOKIES[new Random().nextInt(3)] + System.currentTimeMillis());
            .addCookie("Cookie", COOKIE);


    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        try {
            site.setUserAgent(AGENTS[new Random().nextInt(5)])
                    .addCookie("Cookie", COOKIE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        processForMusicInfo(page);
    }

    /**
     * @Author 印佳明
     * @Date 2018/7/4 9:57
     * Ablum信息
     * composerid createdtime
     */
    public void processForMusicInfo(Page page) {
        System.out.println("******开始爬取专辑信息 " + page.getUrl().toString() + " *******" + new Date());
        try {
            Album album = new Album();

            album.setAlbumid(page.getUrl().toString().split("=")[1]);
            System.out.println("getAlbumid    " + album.getAlbumid());
            album.setComposerid(page.getHtml().xpath("//div[@class='topblk']/p/span/a/@href").toString().split("=")[1]);
            System.out.println("getComposerid    " + album.getComposerid());
            String dataTimeStr = page.getHtml().xpath("//div[@class='topblk']/p").all().get(1);
            System.out.println("dataTimeStr    " + dataTimeStr);
            Pattern pattern = Pattern.compile("[0-9]{4}[-][0-9]{1,2}[-][0-9]{1,2}");
            Matcher matcher = pattern.matcher(dataTimeStr);
            if (matcher.find()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                album.setCreatedtime(sdf.parse(matcher.group(0)));
            }

            page.putField("album", album);

            System.out.println("******结束爬取专辑信息 " + page.getUrl().toString() + " *******" + new Date());
        } catch (Exception e) {
            System.out.println("歌曲详细信息XPath解析出现异常 " + page.getUrl() + new Date());
            e.printStackTrace();
            FileUtils.writeStrToFile(page.getUrl() + "\r\n", "E:\\ideawork\\webSpider\\src\\main\\resources\\music_error.txt");
        }
    }


}
