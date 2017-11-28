package com.webSpider.spider.process;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webSpider.pojo.*;
import org.apache.commons.codec.binary.Hex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import utils.EncryptTools;
import utils.FileUtils;
import utils.UnicodeUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
public class MusicListInfoProcess implements PageProcessor
{
    //private UserInfoMapper userInfoMapper;
    //
    //public UserInfoProcess(UserInfoMapper userInfoMapper)
    //{
    //    this.userInfoMapper = userInfoMapper;
    //}

    //@Autowired
    //private UserInfoMapper userInfoMapper;

    //public static UserInfoProcess userInfoProcess;
    //
    //@PostConstruct
    //public void init() {
    //    userInfoProcess = this;
    //}
    //@Resource(name = "userInfoService")
    //private UserInfoService userInfoService;

    public static final String COOKIE = "__s_=1; _ntes_nnid=eae5803bb40e2409e51e275e6edc32ab,1505205076692; _ntes_nuid=eae5803bb40e2409e51e275e6edc32ab; usertrack=c+xxC1nJqmQrEPdeDD4OAg==; _ga=GA1.2.577059788.1506388583; JSESSIONID-WYYY=0wK8%5C6FjZBp2BFAf%2BejvH8ok8Cv7ICwHlioNZ%2BCVTxeVemV6QOTRKsNa7i%5CC04I%2FJAj7S%5Cdjllur0uTo%2Bj87GdfYPaKqyl2ygbOYTOs57JaAWFR9DeE7A2hGVV4KDiNY8HAFtpE%2Bh8lUjwKcfxyIny%2FJKw759t7UqAUN36Xe20l1gy8d%3A1509005917212; _iuqxldmzr_=32; __utma=94650624.577059788.1506388583.1509004120.1509004120.1; __utmb=94650624.2.10.1509004120; __utmc=94650624; __utmz=94650624.1509004120.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)";

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
    private Site site = Site.me().setRetryTimes(3).setCharset("UTF-8")
            .setUserAgent(AGENTS[new Random().nextInt(6)])
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive")
            .addHeader("Referer","http://music.163.com/")
            .setSleepTime(0)
            .setRetryTimes(3)
            .setCycleRetryTimes(100)
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
            site.setUserAgent(AGENTS[new Random().nextInt(5)]);
            Thread.sleep(new Random().nextInt(10) * 1000);

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // 当前页面是图书列表页面
        if (page.getUrl().regex("http://music\\.163\\.com/discover/playlist/\\S+").match())
        {
            processForMusicListList(page);
        }
        // 当前页面是图书详细信息页面
        else if (page.getUrl()
                .regex("http://music\\.163\\.com/playlist\\S+")
                .match())
        //else if(page.getUrl().toString().indexOf("http://music.163.com/playlist/?")>0)
        {
            processForMusicListInfo(page);
        } else if (page.getUrl()
                .regex("http://music\\.163\\.com/song\\S+")
                .match())
        //else if(page.getUrl().toString().indexOf("http://music.163.com/song?id=")>0)
        {
            processForMusicInfo(page);
        }
    }

    /**
     * @Date 2017/11/18 17:50
     * 歌单列表  抽取歌单详细信息URL 获取下一页歌单列表URL
     */
    public void processForMusicListList(Page page)
    {
        // 下一页的Url page.getHtml().css("div.u-page").links().regex(".*/discover/playlist/\\?order=hot&cat =\\w+)").all()
        String nextPageUrlXpath = "//a[@class='zbtn znxt']/@href";
        // 如果当前页有数据 ，不会返回空，否则返回空
        String hasContentXpath = "//div[@class='g-wrap p-pl f-pr']/ul/li";
        // 获取歌单内容地址
        String musicListUrlXpath = "//a[@class='msk']/@href";
        // 获取标签
        //String tag = page.getUrl().toString().split("/")[4];
        List<String> musicListUrls = page.getHtml().xpath(musicListUrlXpath).all();
        String nextPageUrl = page.getHtml().xpath(nextPageUrlXpath).toString().trim();
        if (page.getHtml().xpath(hasContentXpath).all().size() > 0)
        {
            // 把歌单详细内容URL加入爬取队列
            for (String musicListUrl : musicListUrls)
            {
                page.addTargetRequest(new Request(musicListUrl).setPriority(1));
                //  .putExtra("tag", tag));
                System.out.println("歌单列表-入列:" + musicListUrl);
            }
            // 把下一页歌单列表加入队列
            page.addTargetRequest(new Request(nextPageUrl).setPriority(0));
        }
    }

    /**
     * @Date 2017/11/18 17:50
     * 歌单详细信息
     * id     musiclistid    musiclistname    createrid   tag    introduction    followedcount
     * musiccount    playcount    commentcount    forwardcount    listurl
     */
    public void processForMusicListInfo(Page page)
    {
        System.out.println("******开始爬取歌单详细信息+" + page.getUrl().toString() + "+*******");
        try
        {
            MusicListInfo musicListInfo = new MusicListInfo();
            musicListInfo.setMusiclistid(page.getUrl().toString().split("=")[1].trim());
            musicListInfo.setMusiclistname(page.getHtml().xpath("//div[@class='tit']/h2/text()").toString().trim());
            musicListInfo.setCreaterid(page.getHtml().xpath("//span[@class='name']/a/@href").toString().split("=")[1].trim());
            List<String> tags = page.getHtml().xpath("//div[@class='tags f-cb']/a/i/text()").all();//..
            String tag = "";
            for (int i = 0; i < tags.size(); i++)
            {
                if (i != (tags.size() - 1))
                    tag += tags.get(i).trim() + "$";
                else
                    tag += tags.get(i).trim();
            }
            musicListInfo.setTag(tag);
            musicListInfo.setIntroduction(page.getHtml().xpath("//p[@id='album-desc-more']/text()").toString().trim()
                    .replace("<b>", "")
                    .replace("</b>", "")
                    .replace("\"", "")
                    .replace("<br><br>", " ")
                    .replace("<br>", " "));
            musicListInfo.setFollowedcount(Integer.parseInt(page.getHtml()
                    .xpath("//div[@id='content-operation']/a[@class='u-btni u-btni-fav ']/@data-count").toString().trim()));
            musicListInfo.setMusiccount(Integer.parseInt(page.getHtml().xpath("//span[@id='playlist-track-count']/text()").toString().trim()));
            musicListInfo.setPlaycount(Integer.parseInt(page.getHtml().xpath("//strong[@id='play-count']/text()").toString().trim()));
            musicListInfo.setCommentcount(Integer.parseInt(page.getHtml().xpath("//span[@id='cnt_comment_count']/text()").toString().trim()));
            musicListInfo.setListurl(page.getUrl().toString());
            musicListInfo.setForwardcount(Integer.parseInt(page.getHtml()
                    .xpath("//div[@id='content-operation']/a[@class='u-btni u-btni-share ']/@data-count").toString().trim()));

            List<String> musicidUrlList = page.getHtml().css("div.n-songtb").links().regex(".*/song\\?id=.*").all();

            List<MusicList2Music> musicList2MusicsList = new ArrayList<>();
            for (String mucUrl : musicidUrlList)
            {
                MusicList2Music tempMusicLsit2Music = new MusicList2Music();
                tempMusicLsit2Music.setMusiclistid(musicListInfo.getMusiclistid());
                tempMusicLsit2Music.setMusicid(mucUrl.split("=")[1].trim());
                musicList2MusicsList.add(tempMusicLsit2Music);

                page.addTargetRequest(new Request(mucUrl).setPriority(2)
                        .putExtra("tag", tag));
            }

            page.putField("musicList2MusicsList", musicList2MusicsList);
            page.putField("musicListInfo", musicListInfo);

        } catch (Exception e)
        {
            System.out.println("歌单详细信息XPath解析出现异常" + page.getUrl());
            FileUtils.writeStrToFile(page.getUrl() + "\r\n", "E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
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

            musicInfo.setComposerid(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString().split("=")[1].trim());
            composerInfo.setComposerid(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString().split("=")[1].trim());
            composerInfo.setName(page.getHtml().xpath("//div[@class='cnt']/p/span/@title").toString());
            composerInfo.setComposerurl(page.getHtml().xpath("//div[@class='cnt']/p/span/a/@href").toString());

            musicInfo.setAlbumid(page.getHtml().xpath("//div[@class='cnt']/p/a/@href").toString().split("=")[1]);
            album.setAlbumid(page.getHtml().xpath("//div[@class='cnt']/p/a/@href").toString().split("=")[1]);
            album.setAlbumname(page.getHtml().xpath("//div[@class='cnt']/p/a/text()").toString());
            album.setAblumurl(page.getHtml().xpath("//div[@class='cnt']/p/a/@href").toString());


            musicInfo.setTag(UnicodeUtil.unicodeToString(page.getRequest().getExtra("tag").toString()));
            //System.out.println(page.getHtml().xpath("//span[@id='cnt_comment_count']").toString());
            int offset = 0;
            JSONObject jsonComment = commentAPI(musicInfo.getMusicid(), offset);
            int[] num = {1,2,3};
            if(jsonComment.getString("msg") == null)
            {
                System.out.println(page.getUrl().toString() + " 获取评论：offset = " + offset + " 请稍等！！！");
                musicInfo.setCommentcount(Integer.parseInt(jsonComment.getString("total")));

                while (offset < 1001 && offset < musicInfo.getCommentcount())
                {
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
                    jsonComment = commentAPI(musicInfo.getMusicid(), offset);
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
            FileUtils.writeStrToFile(page.getUrl() + "\r\n", "E:\\ideawork\\webSpider\\src\\main\\resources\\music_error.txt");
        }
    }


    /**
     * @Author 印佳明
     * @Date 2017/11/27 17:33
     * 评论进行加密过  所以要特殊处理
     */
    public JSONObject commentAPI(String musicid, int offset) throws Exception
    {

        if (offset%1000==0)
            Thread.sleep(new Random().nextInt(30) * 50);
        //私钥，随机16位字符串（自己可改）
        //String secKey = "cd859f54539b24b7";
        String secKey = UUID.randomUUID().toString()
                    .replace("-", "").substring(0,16);
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
        Document document = Jsoup.connect("http://music.163.com/weapi/v1/resource/comments/R_SO_4_" + musicid + "?csrf_token=4eef1bc804a5b1f3b3c85366e404f583").cookie("Cookie", COOKIE).userAgent(AGENTS[new Random().nextInt(6)])
                .header("Referer", "http://music.163.com/song?id="+ musicid).data("params", params).data("encSecKey", encSecKey)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                .header("Cache-Control", "max-age=0")
                .header("Connection", "keep-alive")
                .ignoreContentType(true).post();

        //System.out.println("评论：" + document.text());
        return JSON.parseObject(document.text());
    }

}
