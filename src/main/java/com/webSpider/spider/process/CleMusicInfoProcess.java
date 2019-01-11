package com.webSpider.spider.process;


import com.webSpider.pojo.CleMusicInfoTemp1;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import utils.FileUtils;

import java.util.Random;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
public class CleMusicInfoProcess implements PageProcessor {

    public static final String COOKIE = "_ntes_nuid=3f699717063528cda9b23f6ac8a8d305; _ga=GA1.2.1350381713.1513686416; __gads=ID=c8221b335307e0de:T=1514885571:S=ALNI_MatArhY-Cc9E8Yy-yqcaYCj9xNCdw; vjuids=6e1acf2df.160b6376d52.0.9f88a3a88c3ff; mail_psc_fingerprint=d6e1c49113b22130cd01255775fa3dea; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; nts_mail_user=jmncut@163.com:-1:1; _iuqxldmzr_=32; __utmz=94650624.1530666604.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); P_INFO=jmncut@163.com|1533461848|0|hzhr|00&99|bej&1533461822&hzhr#bej&null#10#0#0|156778&1|hzhr|jmncut@163.com; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2Fapp%2Fpersonal%2Finterview%22%2C%22updatedTime%22%3A%201533469827560%2C%22sessionStartTime%22%3A%201533469827555%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%202%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%22fb7144bc-c66a-48a1-9448-4ccb69ba9b1e%22%2C%22persistedTime%22%3A%201533376685421%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201533469827560%7D%2C%22sessionUuid%22%3A%20%2205981d6a-b73b-498f-b56c-1f76d96c4354%22%7D; __f_=1542348544305; UM_distinctid=167ab0c683b305-0f42cf6457120d-b79193d-1fa400-167ab0c683c9bf; vjlast=1514885574.1544967136.12; vinfo_n_f_l_n3=b556854f4af88be7.1.13.1514885573981.1544787052701.1544967174133; _ntes_nnid=3f699717063528cda9b23f6ac8a8d305,1545722470603; WM_TID=hLxcyJ8Y4vJFAVQEVRZpL%2Fl1XvMMYgig; __remember_me=true; WM_NI=aI%2Bs8cQGsK3TGn9BxtlRp51fD8X64B3lR4Dyhz3Lf5OFocmqkGtk5rhxFPXicQ1W1kFtLG4HNvNx9fraO9nSYzObQ5ktKTZUdHWHbx%2FgsZVLDRf5Q1XsoC%2BhMGAMFQqFR2s%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee93b56ab8aea3bae648af968ea2d84e829a9faab73b83acfaccd148b19b999ab32af0fea7c3b92aa397e594bb699399bdacb26085b2f7a3fb80b08ec0d6b46e88e898d7b74af1b49ed5c773aca6fdade53f8d9aa184f07ba999bc8cb87aaca88ab1db63a18fffb8b46186ac84a8b7448eaa8299bc6eb2bf81daed599ba68183d43fa9ac85b5d95fa89899a9e174edb9bdbbd548888eaad6d43b9cada1a4aa648f998faec55bb39a9ed3f237e2a3; __utmc=94650624; __utma=94650624.1350381713.1513686416.1547177952.1547185095.15; MUSIC_U=9c24379b9ac2d7c2efca1ea90a87f2b3b043d173204a83e2b90e1b53951b133732fb6615e0249f62a8c1d037e4e928df31b299d667364ed3; __csrf=5958547ce8edcb6beab321838509677f; __utmb=94650624.8.10.1547185095; JSESSIONID-WYYY=uhJmAgvCizZ%2B2d06I4CknVeUeOhoDwNd7a%2Bn9M6wMSG8rITply1I61ECaXurnvqslG8%2FbsGCHDcdlhKbopF%2FBnZ3pAQ9Qh7DvkPTqxqscnyxyx7Cl%5CEFiIW3umJV0gO2NQPdJUd%5CGAR%2BeZ9k%2F3598Q1%5Cni8s7QNUToij5G10AE%5C8mnbz%3A1547188635028";

    public static final String[] AGENTS = new String[]{
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2471.2 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.12 Safari/535.11",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.4033.400 QQBrowser/9.6.12624.400"};
    private Site site = Site.me().setRetryTimes(3).setCharset("UTF-8")
            .setUserAgent(AGENTS[new Random().nextInt(6)])
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate, br")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive")
            .addHeader("Referer", "http://music.163.com/")
            .addHeader("Host", "music.163.com")
            .addHeader("Upgrade-Insecure-Requests", "1")
            .setSleepTime(0)
            .setRetryTimes(3)
            .setCycleRetryTimes(100)
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
            site.setUserAgent(AGENTS[new Random().nextInt(5)]);
            Thread.sleep(new Random().nextInt(30) * 100);

        } catch (Exception e) {
            e.printStackTrace();
        }
        processForMusicInfo(page);

    }

    /**
     * @Author 印佳明
     * @Date 2017/11/21 14:14
     * 歌曲详细信息
     * id     musicid    name    createdtime    composerid    commentcount
     * musicurl    tag    emotionid    styleid    languageid  albumid
     */
    public void processForMusicInfo(Page page) {
        System.out.println("******开始爬取歌曲详细信息+" + page.getUrl().toString() + "+*******");
        try {
            CleMusicInfoTemp1 cleMusicInfoTemp1 = new CleMusicInfoTemp1();
            cleMusicInfoTemp1.setMusicid(page.getUrl().toString().split("=")[1].trim());
            System.out.println(page.getHtml().xpath("//div[@class='tit']/text()"));
            System.out.println(page.getHtml().xpath("//div[@class='cnt']/p/span/@title"));
            cleMusicInfoTemp1.setName(page.getHtml().xpath("//div[@class='tit']/em/text()").toString().trim());
            System.out.println(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href"));
            cleMusicInfoTemp1.setComposerid(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString().split("=")[1].trim());


            page.putField("cleMusicInfoTemp1", cleMusicInfoTemp1);

        } catch (Exception e) {
            System.out.println("歌曲详细信息XPath解析出现异常" + page.getUrl());
            e.printStackTrace();
            FileUtils.writeStrToFile(page.getUrl() + "\r\n", "G:\\ideawork\\webSpider\\src\\main\\resources\\cle_music_error1.txt");
        }
    }


}
