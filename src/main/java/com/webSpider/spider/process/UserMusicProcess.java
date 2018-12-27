package com.webSpider.spider.process;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webSpider.pojo.Album;
import com.webSpider.pojo.ComposerInfo;
import com.webSpider.pojo.MusicInfo;
import com.webSpider.pojo.User2Music;
import org.apache.commons.codec.binary.Hex;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import utils.EncryptTools;
import utils.FileUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * @author 印佳明
 * @create 2018年12月26日21:54:27
 */
public class UserMusicProcess implements PageProcessor {

    public static final String COOKIE = "_ntes_nuid=3f699717063528cda9b23f6ac8a8d305; _ga=GA1.2.1350381713.1513686416; __gads=ID=c8221b335307e0de:T=1514885571:S=ALNI_MatArhY-Cc9E8Yy-yqcaYCj9xNCdw; vjuids=6e1acf2df.160b6376d52.0.9f88a3a88c3ff; mail_psc_fingerprint=d6e1c49113b22130cd01255775fa3dea; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; nts_mail_user=jmncut@163.com:-1:1; _iuqxldmzr_=32; __utmz=94650624.1530666604.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); P_INFO=jmncut@163.com|1533461848|0|hzhr|00&99|bej&1533461822&hzhr#bej&null#10#0#0|156778&1|hzhr|jmncut@163.com; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2Fapp%2Fpersonal%2Finterview%22%2C%22updatedTime%22%3A%201533469827560%2C%22sessionStartTime%22%3A%201533469827555%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%202%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%22fb7144bc-c66a-48a1-9448-4ccb69ba9b1e%22%2C%22persistedTime%22%3A%201533376685421%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201533469827560%7D%2C%22sessionUuid%22%3A%20%2205981d6a-b73b-498f-b56c-1f76d96c4354%22%7D; __f_=1542348544305; Province=010; City=010; UM_distinctid=167ab0c683b305-0f42cf6457120d-b79193d-1fa400-167ab0c683c9bf; _antanalysis_s_id=1544762978057; s_n_f_l_n3=b556854f4af88be71544942149180; vjlast=1514885574.1544967136.12; ne_analysis_trace_id=1544967136433; vinfo_n_f_l_n3=b556854f4af88be7.1.13.1514885573981.1544787052701.1544967174133; _ntes_nnid=3f699717063528cda9b23f6ac8a8d305,1545722470603; __utmc=94650624; WM_TID=hLxcyJ8Y4vJFAVQEVRZpL%2Fl1XvMMYgig; WM_NI=n7OJMX9Tbh8EjH7h6FqsB3ivAP5fUVzUArAOf83nVpKD0IpM6IrxGO08hqkoE8CtZBhOf1JQNj9RyJbeUdJLbbfrbki2QXxs%2BtbTy309s4j6v3bTyuPr%2BUoQvtZElUYcNEI%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee98d145afb6a690b444bab88aa6d45f968e9abab86ff6b3888ebb7fbb8aacbbd12af0fea7c3b92aaee981abd054fb8cae89bb4faa98f989b54eb3ad8ed4ec7e8390fb87d85eba8b8f93c445a6e8fd8fd57c899c00aaf347f697be99c645f4e8a892ea6587e88d95d94aac91aad2c95b8eb6f7b3fc42a2bb9b85c948a2beaa93e161abee9fb9fb79e9acaeb5fc39f4e7bdd5e93eb6edaeb5fc7f94aee5b6ee5bb6ac96a6ef5f9bbd82a6cc37e2a3; JSESSIONID-WYYY=s16PnZ7QbelZseF%2Fum4eqzXNtPbAlU7lZvONklsjjWU9m37oXgSIgSYmR1175RG8ZTT3r%2FfQwAY5GPiZRam6%2BBonSc73Hm6mzKWPWy2FJgh%2FG5j9WDvcE%2B6MObqKNX5YkM%2BASez0HsU4KwWu8OvgjMw3FUANuotxHU3EtblX6E%2FBU5Ya%3A1545828644066; __utma=94650624.1350381713.1513686416.1545814658.1545828002.4; __utmb=94650624.2.10.1545828002";

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
            .addHeader("Host", "music.163.com")
            .addHeader("Referer", "https://music.163.com/")
            .setSleepTime(0)
            .setRetryTimes(3)
            .setCycleRetryTimes(100)
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
        try {
            String userno = page.getUrl().toString().split("=")[1].trim();
            System.out.println("userno: " + userno +" DATA: "+ new Date());

            List<User2Music> user2MusicList = new ArrayList<>();
            List<MusicInfo> musicInfoList = new ArrayList<>();
            List<ComposerInfo> composerInfoList = new ArrayList<>();
            List<Album> albumList = new ArrayList<>();
            JSONObject jsonRecord = recordAPI(userno);
            JSONArray allData = jsonRecord.getJSONArray("allData");
            for (int i = 0; i < allData.size(); i++) {
                JSONObject recordJSONObject = allData.getJSONObject(i);

                JSONObject songJSONObject = recordJSONObject.getJSONObject("song");
                String songId = songJSONObject.getString("id");

                MusicInfo musicInfo = new MusicInfo();
                JSONObject songSong = songJSONObject.getJSONObject("song");
                musicInfo.setMusicid(songId);
                musicInfo.setAlbumid(songJSONObject.getJSONObject("al").getString("id"));
                musicInfo.setComposerid(songJSONObject.getJSONArray("ar").getJSONObject(0).getString("id"));
                musicInfo.setTag(songSong.getJSONObject("album").getString("genre"));
                musicInfoList.add(musicInfo);

                ComposerInfo composerInfo = new ComposerInfo();
                composerInfo.setComposerid(musicInfo.getComposerid());
                composerInfo.setName(songSong.getJSONObject("artist").getString("name"));
                composerInfoList.add(composerInfo);

                Album album = new Album();
                album.setAlbumid(musicInfo.getAlbumid());
                album.setComposerid(musicInfo.getComposerid());
                album.setAlbumname(songSong.getJSONObject("album").getString("name"));
                albumList.add(album);


                User2Music user2Music = new User2Music();
                user2Music.setUserid(userno);
                user2Music.setMusicid(songId);
                user2Music.setRatio(recordJSONObject.getString("score"));
                user2MusicList.add(user2Music);
                System.out.println("user2Music: " + user2Music.toString());
            }


            page.putField("user2MusicList", user2MusicList);
            page.putField("musicInfoList", musicInfoList);
            page.putField("composerInfoList", composerInfoList);
            page.putField("albumList", albumList);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("用户_音乐信息Xpath解析失败");
            FileUtils.writeStrToFile(page.getUrl() + "\r\n", "G:\\ideawork\\webSpider\\src\\main\\resources\\User_music_error.txt");
        }
    }


    /**
     * @Author 印佳明
     * @Date 2017/11/27 17:33
     * 最近听歌歌单进行加密过  所以要特殊处理
     */
    public JSONObject recordAPI(String userid) throws Exception {

        String cooke = "_ntes_nuid=53f8777a908adc1fade5f4d408b71734; vjuids=-cc3f4d4f4.15755ef852d.0.a64594b096de3; __gads=ID=96e8a37b864614e1:T=1477567255:S=ALNI_MZDp-klfpudEJCh0C3_ErZXMm4Brw; __s_=1; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; __utma=187553192.135554208.1475672367.1504746836.1505093225.13; __utmz=187553192.1505093225.13.10.utmcsr=open.163.com|utmccn=(referral)|utmcmd=referral|utmcct=/ted/; __oc_uuid=13902340-f9bc-11e6-a69c-b7ce7eb90a7c; _ntes_nnid=53f8777a908adc1fade5f4d408b71734,1506485249602; vjlast=1474615543.1508207044.11; vinfo_n_f_l_n3=dd7c1e1c7c903f37.1.20.1474615543099.1506490530904.1508207185197; usertrack=c+xxC1n22F8nk0YmAxDjAg==; _ga=GA1.2.135554208.1475672367; P_INFO=jmncut@163.com|1505093238|2|open|00&99|bej&1504142674&open#bej&null#10#0#0|156778&1|open&study|jmncut@163.com; JSESSIONID-WYYY=dIkS2jVEkXhSWroYjpJEO4Klbf6dIsex0w1jCmWX6fJZvyySw7MOp%2FhIQksmVm%2Bc2j%2BDCfobEyFnZBYrROciT4mDQUagIb4wWTxxxPxP855YBFRjmD6NSceJ9MIazQ4vZ7kvzVV9z5TwuCMlNQ5IyHNAxbhqEm1nJjEkGu8tC%5CYDnJwH%3A1511919295977; _iuqxldmzr_=32; __utma=94650624.135554208.1475672367.1511872233.1511918327.13; __utmb=94650624.2.10.1511918327; __utmc=94650624; __utmz=94650624.1493814912.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __remember_me=true; MUSIC_U=e253743d84ed11a4aeeade41d1a92277dbda2dccfc340ef8afc61c610e10e57a657b34efc0a47cb8f6f5b8899e2fc54b8bafcdfe5ad2b092; __csrf=9b528e36a413da944ca00ea70beef1be";

        //私钥，随机16位字符串（自己可改）
        //String secKey = "cd859f54539b24b7";
        String secKey = UUID.randomUUID().toString()
                .replace("-", "").substring(0, 16);
        String text = "{'uid': '" + userid + "', 'total':'true', 'type':'0',limit:'100', 'offset': '0', 'csrf_token':''}";
        String modulus = "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7";
        String nonce = "0CoJUm6Qyw8W8jud";
        String pubKey = "010001";
        //2次AES加密，得到params
        String params = EncryptTools.encrypt(EncryptTools.encrypt(text, nonce), secKey);
        StringBuilder stringBuffer = new StringBuilder(secKey);
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
        //获取
        Document document = Jsoup.connect("https://music.163.com/weapi/v1/play/record?csrf_token=")
                .cookie("Cookie", cooke).userAgent(AGENTS[new Random().nextInt(6)])
                .header("Referer", "https://music.163.com/user/songs/rank?id=" + userid)
                .data("params", params)
                .data("encSecKey", encSecKey)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6")
                .header("Cache-Control", "max-age=0")
                .header("Connection", "keep-alive")
                .ignoreContentType(true).post();

        if (document.text().equalsIgnoreCase("") || document.text() == null)
            return null;
        else
            return JSON.parseObject(document.text());
    }

}
