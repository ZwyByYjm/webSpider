package com.webSpider.spider.process;


import com.webSpider.pojo.UserInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Random;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
public class UserInfoProcess implements PageProcessor
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

    // 匹配专辑URL
    public static final String USER_URL = "http://music\\.163\\.com/user/home\\?id=\\d+";
    //	public static final String COOKIE = "bid=\"cLFu0ljxKRE\"; gr_user_id=71a8fed9-c730-4e6b-815f-813f99b450f4; ll=\"108288\"; ap=1; viewed=\"3283973_5257905\"; __utmt=1; dbcl2=\"142128977:lKgaClsIDDI\"; ck=\"uNFe\"; gr_session_id_22c937bbd8ebd703f2d8e9445f7dfd03=c78404b1-2e3a-4bb6-afd9-45e6cfdc0d41; __utmt_douban=1; __utma=30149280.1220747617.1453995829.1455611943.1456219113.6; __utmb=30149280.4.10.1456219113; __utmc=30149280; __utmz=30149280.1456219113.6.5.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=30149280.14212; push_noty_num=0; push_doumail_num=1";
    public static final String[] AGENTS = new String[]{
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2471.2 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.12 Safari/535.11",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36"};
    // TODO:设置代理IP
    private Site site = Site.me().setRetryTimes(3).setCharset("UTF-8")
            .setUserAgent(AGENTS[new Random().nextInt(5)])
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Encoding", "gzip, deflate")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("Connection", "keep-alive")
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
            Thread.sleep(new Random().nextInt(30) * 100);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            if (page.getUrl().regex(USER_URL).match())
            {
                String userno = page.getUrl().toString().split("=")[1].trim();
                System.out.println(userno);
                String name = page.getHtml().xpath("//span[@class='tit f-ff2 s-fc0 f-thide']/text()").get().trim();
                page.putField("name",name);
                System.out.println(name);
                String level = page.getHtml().xpath("//span[@class='lev u-lev u-icn2 u-icn2-lev']/text()").get().trim();
                System.out.println(level);
                String eventcount = page.getHtml().xpath("//strong[@id='event_count']/text()").get().trim();
                System.out.println(eventcount);
                String followcount = page.getHtml().xpath("//strong[@id='follow_count']/text()").get();
                System.out.println(followcount);
                String fancount = page.getHtml().xpath("//strong[@id='fan_count']/text()").get();
                System.out.println(fancount);
                String location = page.getHtml().xpath("//div[@class='inf s-fc3']/span/text()").get().split("\\：")[1];
                System.out.println(location);
                if (page.getResultItems().get("name") == null) {
                    //skip this page
                    page.setSkip(true);
                }

                UserInfo user = new UserInfo();
                user.setUserid(userno);
                user.setName(name);
                user.setLevel(Integer.parseInt(level));
                user.setFollowscount(Integer.parseInt(followcount));
                user.setFanscount(Integer.parseInt(fancount));
                //userInfoProcess.userInfoService.selectByPrimaryKey(1);
                //userInfoProcess.userInfoMapper.insert(user);
                //userInfoService.selectByPrimaryKey(1);
                //userInfoMapper.insert(user);

                page.putField("user",user);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("用户信息Xpath解析失败");
            //FileUtils.writeStrToFile(page.getUrl().toString() + "\r\n","E:\\IdeaProjects\\doubanWebSpider\\src\\main\\resources\\user_error.txt");
        }
    }
}
