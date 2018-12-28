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

    public static final String COOKIE = "_ntes_nuid=3f699717063528cda9b23f6ac8a8d305; _ga=GA1.2.1350381713.1513686416; __gads=ID=c8221b335307e0de:T=1514885571:S=ALNI_MatArhY-Cc9E8Yy-yqcaYCj9xNCdw; vjuids=6e1acf2df.160b6376d52.0.9f88a3a88c3ff; mail_psc_fingerprint=d6e1c49113b22130cd01255775fa3dea; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; nts_mail_user=jmncut@163.com:-1:1; _iuqxldmzr_=32; __utmz=94650624.1530666604.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); P_INFO=jmncut@163.com|1533461848|0|hzhr|00&99|bej&1533461822&hzhr#bej&null#10#0#0|156778&1|hzhr|jmncut@163.com; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2Fapp%2Fpersonal%2Finterview%22%2C%22updatedTime%22%3A%201533469827560%2C%22sessionStartTime%22%3A%201533469827555%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%202%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%22fb7144bc-c66a-48a1-9448-4ccb69ba9b1e%22%2C%22persistedTime%22%3A%201533376685421%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201533469827560%7D%2C%22sessionUuid%22%3A%20%2205981d6a-b73b-498f-b56c-1f76d96c4354%22%7D; __f_=1542348544305; Province=010; City=010; UM_distinctid=167ab0c683b305-0f42cf6457120d-b79193d-1fa400-167ab0c683c9bf; vjlast=1514885574.1544967136.12; vinfo_n_f_l_n3=b556854f4af88be7.1.13.1514885573981.1544787052701.1544967174133; _ntes_nnid=3f699717063528cda9b23f6ac8a8d305,1545722470603; WM_TID=hLxcyJ8Y4vJFAVQEVRZpL%2Fl1XvMMYgig; __utma=94650624.1350381713.1513686416.1545831224.1545961870.6; __utmc=94650624; WM_NI=%2B%2BeoydvT3ItGKWCg9ZFZqeEIVkXUew%2BxcIl5T%2BBky4VHQiWljrPfZ4TiB8sezS5CCT8TUU5O%2BFX%2FmKNF0JCXz9smJlSnfPHuQncg0s80XDeTlpH3XaM%2F%2F6jq41jZUEKwb3o%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee8de845aca6bcaecc3d83ac8ab7c45b928f8f84bc45f5b08788d1688cb599dacc2af0fea7c3b92ae9f5ab90fc70b68cfdaaf74d89898d88bc528686bf86c84396b598b6e87de9a8adaaef3f98f58e8cfc4096989f8ad668aaaea087d94098b78ed9f26fba8dfba4c260fc8c9bb6b845e99a98d0f0418e9a00abc254bae7bd8ec96891b2aab1cb7f93b2ad88b379fcf098a5e860b0b088d4db6b8ff1e1d7e97bf8efa6bbd84f89a9aed4ee37e2a3; JSESSIONID-WYYY=29Vg8X2c7f%2FYbk58gq%2F7vVP1qBHNsZaP%2BoPT%2FlijA%5CgG3IijCoGZYZYS9eMgHB1Zo1p34%2BjcMruT%2FQgIr3VWm16iNI5yGlsEHMeG6WJ6xd5%2FiPrEQ%2Bz5rqgoGC6yijlf5zA5F6uyQZccnsMmY5qbOlT99VCy66NtkJwD%2BhI1YWCdH%2Fui%3A1545965410077; __utmb=94650624.6.10.1545961870 ";

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
            for (int i = 0; !Objects.isNull(allData) && i < allData.size(); i++) {
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

        String cooke = "_ntes_nuid=3f699717063528cda9b23f6ac8a8d305; _ga=GA1.2.1350381713.1513686416; __gads=ID=c8221b335307e0de:T=1514885571:S=ALNI_MatArhY-Cc9E8Yy-yqcaYCj9xNCdw; vjuids=6e1acf2df.160b6376d52.0.9f88a3a88c3ff; mail_psc_fingerprint=d6e1c49113b22130cd01255775fa3dea; NTES_CMT_USER_INFO=118404385%7C%E6%9C%89%E6%80%81%E5%BA%A6%E7%BD%91%E5%8F%8B073Hkx%7C%7Cfalse%7Cam1uY3V0QDE2My5jb20%3D; nts_mail_user=jmncut@163.com:-1:1; _iuqxldmzr_=32; __utmz=94650624.1530666604.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); P_INFO=jmncut@163.com|1533461848|0|hzhr|00&99|bej&1533461822&hzhr#bej&null#10#0#0|156778&1|hzhr|jmncut@163.com; mp_MA-9ADA-91BF1A6C9E06_hubble=%7B%22sessionReferrer%22%3A%20%22https%3A%2F%2Fcampus.163.com%2Fapp%2Fpersonal%2Finterview%22%2C%22updatedTime%22%3A%201533469827560%2C%22sessionStartTime%22%3A%201533469827555%2C%22sendNumClass%22%3A%20%7B%22allNum%22%3A%202%2C%22errSendNum%22%3A%200%7D%2C%22deviceUdid%22%3A%20%22fb7144bc-c66a-48a1-9448-4ccb69ba9b1e%22%2C%22persistedTime%22%3A%201533376685421%2C%22LASTEVENT%22%3A%20%7B%22eventId%22%3A%20%22da_screen%22%2C%22time%22%3A%201533469827560%7D%2C%22sessionUuid%22%3A%20%2205981d6a-b73b-498f-b56c-1f76d96c4354%22%7D; __f_=1542348544305; Province=010; City=010; UM_distinctid=167ab0c683b305-0f42cf6457120d-b79193d-1fa400-167ab0c683c9bf; vjlast=1514885574.1544967136.12; vinfo_n_f_l_n3=b556854f4af88be7.1.13.1514885573981.1544787052701.1544967174133; _ntes_nnid=3f699717063528cda9b23f6ac8a8d305,1545722470603; WM_TID=hLxcyJ8Y4vJFAVQEVRZpL%2Fl1XvMMYgig; __utma=94650624.1350381713.1513686416.1545831224.1545961870.6; __utmc=94650624; WM_NI=%2B%2BeoydvT3ItGKWCg9ZFZqeEIVkXUew%2BxcIl5T%2BBky4VHQiWljrPfZ4TiB8sezS5CCT8TUU5O%2BFX%2FmKNF0JCXz9smJlSnfPHuQncg0s80XDeTlpH3XaM%2F%2F6jq41jZUEKwb3o%3D; WM_NIKE=9ca17ae2e6ffcda170e2e6ee8de845aca6bcaecc3d83ac8ab7c45b928f8f84bc45f5b08788d1688cb599dacc2af0fea7c3b92ae9f5ab90fc70b68cfdaaf74d89898d88bc528686bf86c84396b598b6e87de9a8adaaef3f98f58e8cfc4096989f8ad668aaaea087d94098b78ed9f26fba8dfba4c260fc8c9bb6b845e99a98d0f0418e9a00abc254bae7bd8ec96891b2aab1cb7f93b2ad88b379fcf098a5e860b0b088d4db6b8ff1e1d7e97bf8efa6bbd84f89a9aed4ee37e2a3; JSESSIONID-WYYY=29Vg8X2c7f%2FYbk58gq%2F7vVP1qBHNsZaP%2BoPT%2FlijA%5CgG3IijCoGZYZYS9eMgHB1Zo1p34%2BjcMruT%2FQgIr3VWm16iNI5yGlsEHMeG6WJ6xd5%2FiPrEQ%2Bz5rqgoGC6yijlf5zA5F6uyQZccnsMmY5qbOlT99VCy66NtkJwD%2BhI1YWCdH%2Fui%3A1545965410077; __utmb=94650624.6.10.1545961870";

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
