package com.webSpider.spider.process;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webSpider.pojo.Album;
import com.webSpider.pojo.ComposerInfo;
import com.webSpider.pojo.MusicInfo;
import com.webSpider.pojo.UserInfo;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpHost;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import utils.EncryptTools;
import utils.FileUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
public class MusicInfoProcess implements PageProcessor
{

    public static final String COOKIE =
            "__s_=1; _ntes_nnid=eae5803bb40e2409e51e275e6edc32ab,1505205076692; _ntes_nuid=eae5803bb40e2409e51e275e6edc32ab; usertrack=c+xxC1nJqmQrEPdeDD4OAg==; _ga=GA1.2.577059788.1506388583; JSESSIONID-WYYY=0wK8%5C6FjZBp2BFAf%2BejvH8ok8Cv7ICwHlioNZ%2BCVTxeVemV6QOTRKsNa7i%5CC04I%2FJAj7S%5Cdjllur0uTo%2Bj87GdfYPaKqyl2ygbOYTOs57JaAWFR9DeE7A2hGVV4KDiNY8HAFtpE%2Bh8lUjwKcfxyIny%2FJKw759t7UqAUN36Xe20l1gy8d%3A1509005917212; _iuqxldmzr_=32; __utma=94650624.577059788.1506388583.1509004120.1509004120.1; __utmb=94650624.2.10.1509004120; __utmc=94650624; __utmz=94650624.1509004120.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";

    // 匹配歌单列表URL
    public static final String UMUSICLIST_URL = "http://music\\.163\\.com/discover/playlist\\?cat=\\w+";
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
    public Site getSite()
    {
        return site;
    }


    @Override
    public void process(Page page)
    {
        try
        {
            site.setUserAgent(AGENTS[new Random().nextInt(5)])
                    //.setHttpProxy(IPS[new Random().nextInt(6)])
                    //.addCookie("Cookie", COOKIES[new Random().nextInt(3)] + System.currentTimeMillis());
                    .addCookie("Cookie", COOKIE);
            //Thread.sleep(new Random().nextInt(5) * 1000);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

         if (page.getUrl()
                .regex("http://music\\.163\\.com/song\\S+")
                .match())
        {
            processForMusicInfo(page);
        }
    }

    /**
     * @Author 印佳明
     * @Date 2017/11/21 14:14
     * 歌曲详细信息
     * id     musicid    name    createdtime    composerid    commentcount
     * musicurl    tag    emotionid    styleid    languageid  albumid
     */
    public void processForMusicInfo(Page page)
    {
        System.out.println("******开始爬取歌曲详细信息+" + page.getUrl().toString() + "+*******");
        try
        {
            MusicInfo musicInfo = new MusicInfo();
            ComposerInfo composerInfo = new ComposerInfo();
            Album album = new Album();
            List<UserInfo> userInfoList = new ArrayList<>();
            musicInfo.setMusicid(page.getUrl().toString().split("=")[1].trim());
            musicInfo.setName(page.getHtml().xpath("//div[@class='tit']/em/text()").toString().trim());
            //musicInfo.setCreatedtime(null);//获取专辑只有设置

            List<String> artlistURLAndAblumURL = page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").all();

            if (page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString() != null)
            {
                musicInfo.setComposerid(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString().split("=")[1].trim());
                composerInfo.setComposerid(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString().split("=")[1].trim());
                composerInfo.setName(page.getHtml().xpath("//div[@class='cnt']/p/span/@title").toString());
                composerInfo.setComposerurl(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString());
            }
            if(page.getHtml().xpath("//div[@class='cnt']/p/a/@href").toString() != null)
            {
                musicInfo.setAlbumid(page.getHtml().xpath("//div[@class='cnt']/p/a/@href").toString().split("=")[1]);
                album.setAlbumid(page.getHtml().xpath("//div[@class='cnt']/p/a/@href").toString().split("=")[1]);
                album.setAlbumname(page.getHtml().xpath("//div[@class='cnt']/p/a/text()").toString());
                album.setAblumurl(page.getHtml().xpath("//div[@class='cnt']/p/a/@href").toString());
            }

            //musicInfo.setTag(UnicodeUtil.unicodeToString(page.getRequest().getExtra("tag").toString()));
            musicInfo.setTag("-1");
            //System.out.println(page.getHtml().xpath("//span[@id='cnt_comment_count']").toString());
            int offset = 0;
            JSONObject jsonComment = commentAPI(musicInfo.getMusicid(), offset);
            int[] num = {1, 2, 3};
            if (jsonComment.getString("msg") == null)
            {
                System.out.println(page.getUrl().toString() + " *****************获取评论中**************");
                musicInfo.setCommentcount(Integer.parseInt(jsonComment.getString("total")));

                while (offset < 1001 && offset < musicInfo.getCommentcount())
                {
                    System.out.println(page.getUrl().toString() + " 获取评论：offset = " + offset + " 请稍等！！！");
                    JSONArray comments = jsonComment.getJSONArray("comments");
                    for (int i = 0; i < comments.size(); i++)
                    {
                        JSONObject commentsJSONObject = comments.getJSONObject(i);
                        JSONObject jsonUser = commentsJSONObject.getJSONObject("user");
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUserid(jsonUser.getString("userId"));
                        userInfo.setUserurl("http://music.163.com/user/home?id=" + userInfo.getUserid());
                        userInfo.setName(jsonUser.getString("nickname"));
                        userInfoList.add(userInfo);
                    }
                    offset += num[new Random().nextInt(3)] * 10;
                    Thread.sleep(new Random().nextInt(8) * 100);
                    jsonComment = commentAPI(musicInfo.getMusicid(), offset);
                    if (jsonComment == null)
                        break;
                }
            }

            //musicInfo.setEmotionid(null);
            //musicInfo.setLanguageid(null);
            musicInfo.setMusicurl(page.getUrl().toString());
            //musicInfo.setStyleid(null);

            page.putField("musicInfo", musicInfo);
            page.putField("composerInfo", composerInfo);
            page.putField("album", album);
            page.putField("userInfoList", userInfoList);

        } catch (Exception e)
        {
            System.out.println("歌曲详细信息XPath解析出现异常" + page.getUrl());
            e.printStackTrace();
            FileUtils.writeStrToFile(page.getUrl() + "\r\n", "G:\\ideawork\\webSpider\\src\\main\\resources\\music_error1.txt");
        }
    }


    /**
     * @Author 印佳明
     * @Date 2017/11/27 17:33
     * 评论进行加密过  所以要特殊处理
     */
    public JSONObject commentAPI(String musicid, int offset) throws Exception
    {

       String cooke =  "_ntes_nuid=53f8777a908adc1fade5f4d408b71734; vjuids=-cc3f4d4f4.15755ef852d.0.a64594b096de3; __gads=ID=96e8a37b864614e1:T=1477567255:S=ALNI_MZDp-klfpudEJCh0C3_ErZXMm4Brw; __s_=1; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; __utma=187553192.135554208.1475672367.1504746836.1505093225.13; __utmz=187553192.1505093225.13.10.utmcsr=open.163.com|utmccn=(referral)|utmcmd=referral|utmcct=/ted/; __oc_uuid=13902340-f9bc-11e6-a69c-b7ce7eb90a7c; _ntes_nnid=53f8777a908adc1fade5f4d408b71734,1506485249602; vjlast=1474615543.1508207044.11; vinfo_n_f_l_n3=dd7c1e1c7c903f37.1.20.1474615543099.1506490530904.1508207185197; usertrack=c+xxC1n22F8nk0YmAxDjAg==; _ga=GA1.2.135554208.1475672367; P_INFO=jmncut@163.com|1505093238|2|open|00&99|bej&1504142674&open#bej&null#10#0#0|156778&1|open&study|jmncut@163.com; JSESSIONID-WYYY=dIkS2jVEkXhSWroYjpJEO4Klbf6dIsex0w1jCmWX6fJZvyySw7MOp%2FhIQksmVm%2Bc2j%2BDCfobEyFnZBYrROciT4mDQUagIb4wWTxxxPxP855YBFRjmD6NSceJ9MIazQ4vZ7kvzVV9z5TwuCMlNQ5IyHNAxbhqEm1nJjEkGu8tC%5CYDnJwH%3A1511919295977; _iuqxldmzr_=32; __utma=94650624.135554208.1475672367.1511872233.1511918327.13; __utmb=94650624.2.10.1511918327; __utmc=94650624; __utmz=94650624.1493814912.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __remember_me=true; MUSIC_U=e253743d84ed11a4aeeade41d1a92277dbda2dccfc340ef8afc61c610e10e57a657b34efc0a47cb8f6f5b8899e2fc54b8bafcdfe5ad2b092; __csrf=9b528e36a413da944ca00ea70beef1be";

        if (offset % 1000 == 0)
            Thread.sleep(new Random().nextInt(30) * 50);
        //私钥，随机16位字符串（自己可改）
        //String secKey = "cd859f54539b24b7";
        String secKey = UUID.randomUUID().toString()
                .replace("-", "").substring(0, 16);
        String text = "{'username': '', 'rememberLogin': 'true', 'password': '', 'offset': '" + offset + "'}";
        String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
        String nonce = "0CoJUm6Qyw8W8jud";
        String pubKey = "010001";
        //2次AES加密，得到params
        String params = EncryptTools.encrypt(EncryptTools.encrypt(text, nonce), secKey);
        StringBuffer stringBuffer = new StringBuffer(secKey);
        //逆置私钥
        secKey = stringBuffer.reverse().toString();
        String hex = Hex.encodeHexString(secKey.getBytes());
        BigInteger bigInteger1 = new BigInteger(hex, 16);
        BigInteger bigInteger2 = new BigInteger(pubKey, 16);
        BigInteger bigInteger3 = new BigInteger(modulus, 16);
        //RSA加密计算
        BigInteger bigInteger4 = bigInteger1.pow(bigInteger2.intValue()).remainder(bigInteger3);
        String encSecKey = Hex.encodeHexString(bigInteger4.toByteArray());
        //字符填充
        encSecKey = EncryptTools.zfill(encSecKey, 256);
        //评论获取
        Document document = Jsoup.connect("http://music.163.com/weapi/v1/resource/comments/R_SO_4_" + musicid + "?csrf_token=4eef1bc804a5b1f3b3c85366e404f583")
                .cookie("Cookie", cooke).userAgent(AGENTS[new Random().nextInt(6)])
                .header("Referer", "http://music.163.com/song?id=" + musicid).data("params", params)
                .data("encSecKey", encSecKey)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                .header("Cache-Control", "max-age=0")
                .header("Connection", "keep-alive")
                .ignoreContentType(true).post();

        //System.out.println("评论：" + document.text());
        if (document.text().equalsIgnoreCase("") || document.text() == null)
            return null;
        else
            return JSON.parseObject(document.text());
    }

}
