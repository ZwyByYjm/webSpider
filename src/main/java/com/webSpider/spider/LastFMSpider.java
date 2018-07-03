package com.webSpider.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webSpider.dao.*;
import com.webSpider.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import utils.JSON2ObjUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Last.fm数据爬取
 *
 * @author 印佳明
 * @create 2018-06-05 14:35
 */
@Controller
@RequestMapping(value = "lastFm")
public class LastFMSpider {

    @Autowired
    private LastFMUrlInfoMapper lastFMUrlInfoMapper;
    @Autowired
    private LastFMUserInfoMapper lastFMUserInfoMapper;
    @Autowired
    private LastFMTracksMapper lastFMTracksMapper;
    @Autowired
    private LastFMLoveTracksMapper lastFMLoveTracksMapper;
    @Autowired
    private LastFMRecentTracksMapper lastFMRecentTracksMapper;


    @Value("${LastFM.Url}")
    private String lastFmUrl;


    @RequestMapping(value = "lastFmUserInfo")
    @ResponseBody
    public String lastFmUserInfo() {
        List<LastFMUserInfo> lastFMUserInfoList = lastFMUserInfoMapper.selectUserByStatus(0);
        System.out.println("lastFMUserInfoList " + lastFMUserInfoList.size());
        for (LastFMUserInfo lastFMUserInfo : lastFMUserInfoList) {
            doGetUserInfo("user.getfriends", lastFMUserInfo.getName(), "c17e237aa0b58a995217ec8a479d3132", "1");
            lastFMUserInfo.setStatus(1);
            lastFMUserInfoMapper.updateByNameSelective(lastFMUserInfo);
        }


//        URL url = new URL("http://ws.audioscrobbler.com/2.0/?method=user.getfriends&user=rj&api_key=c17e237aa0b58a995217ec8a479d3132&format=json");

        return "lastFmUserInfo end！！！";
    }


    @RequestMapping(value = "lastFmRecentTracks")
    @ResponseBody
    public String lastFmRecentTracks() {
        List<LastFMUserInfo> lastFMUserInfoList = lastFMUserInfoMapper.selectUserByStatus(2);
        System.out.println("lastFMUserInfoList " + lastFMUserInfoList.size());
        for (LastFMUserInfo lastFMUserInfo : lastFMUserInfoList) {
            doGetRecentTrack("user.getrecenttracks", lastFMUserInfo.getName(), "c17e237aa0b58a995217ec8a479d3132", "1");
            lastFMUserInfo.setStatus(3);
            lastFMUserInfoMapper.updateByNameSelective(lastFMUserInfo);
        }

//        http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=woca&api_key=c17e237aa0b58a995217ec8a479d3132&from=1514736000&format=json&page=598
//        doGetRecentTrack("user.getrecenttracks", "woca", "c17e237aa0b58a995217ec8a479d3132", "1277");
//        lastFMUserInfo.setStatus(3);
//        lastFMUserInfoMapper.updateByNameSelective(lastFMUserInfo);



        return "lastFmRecentTracks end！！！";
    }


    @RequestMapping(value = "lastFmRecentTracksStack")
    @ResponseBody
    public String lastFmRecentTracksStack() {
//        List<LastFMUserInfo> lastFMUserInfoList = lastFMUserInfoMapper.selectUserByStatus(2);
//        System.out.println("lastFMUserInfoList " + lastFMUserInfoList.size());
//        for (LastFMUserInfo lastFMUserInfo : lastFMUserInfoList) {
//            doGetRecentTrack("user.getrecenttracks", lastFMUserInfo.getName(), "c17e237aa0b58a995217ec8a479d3132", "1");
//            lastFMUserInfo.setStatus(3);
//            lastFMUserInfoMapper.updateByNameSelective(lastFMUserInfo);
//        }

//        http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=woca&api_key=c17e237aa0b58a995217ec8a479d3132&from=1514736000&format=json&page=598
//        http://ws.audioscrobbler.com/2.0/?method=user.getrecenttracks&user=marslaw1&api_key=c17e237aa0b58a995217ec8a479d3132&from=1514736000&format=json&page=596
//        prizefyter
        doGetRecentTrack("user.getrecenttracks", "prizefyter", "c17e237aa0b58a995217ec8a479d3132", "687");
//        lastFMUserInfo.setStatus(3);
//        lastFMUserInfoMapper.updateByNameSelective(lastFMUserInfo);



        return "lastFmRecentTracks end！！！";
    }



    @RequestMapping(value = "lastFmLoveTracks")
    @ResponseBody
    public String lastFmLoveTracks() {
        List<LastFMUserInfo> lastFMUserInfoList = lastFMUserInfoMapper.selectUserByStatus(1);
        System.out.println("lastFMUserInfoList " + lastFMUserInfoList.size());
        for (LastFMUserInfo lastFMUserInfo : lastFMUserInfoList) {
            doGetLoveTrack("user.getlovedtracks", lastFMUserInfo.getName(), "c17e237aa0b58a995217ec8a479d3132", "1");
            lastFMUserInfo.setStatus(2);
            lastFMUserInfoMapper.updateByNameSelective(lastFMUserInfo);
        }

        return "lastFmLoveTracks end！！！";
    }




    private int doGetUserInfo(String method, String user, String api_key, String page) {
        int selectUserSum = lastFMUserInfoMapper.selectUserSum();
        System.out.println("selectUserSum  " + selectUserSum);

        try {
//            String urlStr = String.format(lastFmUrl,"user.getfriends","rj","c17e237aa0b58a995217ec8a479d3132","1");

            String urlStr = String.format(lastFmUrl, method, user, api_key, page);
            System.out.println("******************urlStr begin : " + urlStr + "***********");
            URL url = new URL(urlStr);
            if (lastFMUrlInfoMapper.selectByUrl(urlStr).size() <= 0) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                StringBuilder get = new StringBuilder();
                System.out.println("httpURLConnection.getResponseCode() : " + httpURLConnection.getResponseCode());
                if (httpURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    String read;
                    while (((read = bufferedReader.readLine())) != null) {
                        get.append(read + "\r\n");
                    }
                    JSONObject jsonObject = JSON.parseObject(get.toString());
                    if (jsonObject.size() == 0)
                        return 0;

                    jsonObject = JSON.parseObject(jsonObject.get("friends").toString());

                    LastFMUrlInfo lastFMUrlInfo = new LastFMUrlInfo();
                    System.out.println("begin get urlInfo " + lastFMUrlInfo.getUrl());
                    lastFMUrlInfo.setUrl(urlStr);
                    lastFMUrlInfo.setTotal(JSON.parseObject(jsonObject.get("@attr").toString()).get("total").toString());
                    lastFMUrlInfo.setPerpage(JSON.parseObject(jsonObject.get("@attr").toString()).get("perPage").toString());
                    lastFMUrlInfo.setTotalpages(JSON.parseObject(jsonObject.get("@attr").toString()).get("totalPages").toString());
                    lastFMUrlInfo.setPage(JSON.parseObject(jsonObject.get("@attr").toString()).get("page").toString());
                    lastFMUrlInfoMapper.insert(lastFMUrlInfo);
                    System.out.println("end get urlInfo " + lastFMUrlInfo.getUrl());
                    System.out.println("---------------------------------------");

                    int pageNow = Integer.parseInt(page);
                    int totalPages = Integer.parseInt(JSON.parseObject(jsonObject.get("@attr").toString()).get("totalPages").toString());
                    if (pageNow <= totalPages) {
                        JSONArray userArr = JSON.parseArray(jsonObject.get("user").toString());
                        for (Object userJson : userArr) {
                            LastFMUserInfo lastFMUserInfo = JSON2ObjUtil.json2Object(JSONObject.parseObject(userJson.toString()), LastFMUserInfo.class);

                            System.out.println("lastFMUserInfo begin " + lastFMUserInfo.getName());
                            if (lastFMUserInfoMapper.selectByName(lastFMUserInfo.getName()).size() <= 0) {//查询 lastFmUserInfo 表中是否有同名的人
                                lastFMUserInfo.setStatus(0);
                                lastFMUserInfoMapper.insert(lastFMUserInfo);
                            } else {
                                break;
                            }
                            System.out.println("lastFMUserInfo end " + lastFMUserInfo.getName());
                        }

                        System.out.println("urlStr end : " + urlStr);
                        System.out.println("****************************************");
                        return doGetUserInfo(method, user, api_key, String.valueOf(pageNow + 1));
                    } else
                        return 0;

                } else
                    return doGetUserInfo(method, user, api_key, String.valueOf(Integer.parseInt(page) + 1));
            } else
                return doGetUserInfo(method, user, api_key, String.valueOf(Integer.parseInt(page) + 1));
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int doGetRecentTrack(String method, String user, String api_key, String page) {

        try {
            String urlStr = String.format(lastFmUrl, method, user, api_key, page);
            System.out.println("******************urlStr begin : " + urlStr + "***********");
            URL url = new URL(urlStr);
            if (lastFMUrlInfoMapper.selectByUrl(urlStr).size() <= 0) {

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                StringBuilder get = new StringBuilder();
                System.out.println("httpURLConnection.getResponseCode() : " + httpURLConnection.getResponseCode() +" time :" + new Date());
                if (httpURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    String read;
                    while (((read = bufferedReader.readLine())) != null) {
                        get.append(read + "\r\n");
                    }
                    JSONObject jsonObject = JSON.parseObject(get.toString());
                    if (jsonObject.size() == 0)
                        return 0;
                    jsonObject = JSON.parseObject(jsonObject.get("recenttracks").toString());
                    LastFMUrlInfo lastFMUrlInfo = new LastFMUrlInfo();
                    System.out.println("begin get urlInfo " + lastFMUrlInfo.getUrl());
                    lastFMUrlInfo.setUrl(urlStr);
                    lastFMUrlInfo.setTotal(JSON.parseObject(jsonObject.get("@attr").toString()).get("total").toString());
                    lastFMUrlInfo.setPerpage(JSON.parseObject(jsonObject.get("@attr").toString()).get("perPage").toString());
                    lastFMUrlInfo.setTotalpages(JSON.parseObject(jsonObject.get("@attr").toString()).get("totalPages").toString());
                    lastFMUrlInfo.setPage(JSON.parseObject(jsonObject.get("@attr").toString()).get("page").toString());
                    lastFMUrlInfoMapper.insert(lastFMUrlInfo);
                    System.out.println("end get urlInfo " + lastFMUrlInfo.getUrl());
                    System.out.println("---------------------------------------");


                    int pageNow = Integer.parseInt(page);
                    int totalPages = Integer.parseInt(JSON.parseObject(jsonObject.get("@attr").toString()).get("totalPages").toString());
                    if (pageNow <= totalPages) {
                        JSONArray trackArr = JSON.parseArray(jsonObject.getString("track"));
                        for (Object trackJson : trackArr) {

                            Calendar c = Calendar.getInstance();
                            if (JSON.parseObject(trackJson.toString()).getString("date")!=null) {
                                c.setTime(new Date(Long.parseLong(
                                        JSON.parseObject(
                                                JSON.parseObject(trackJson.toString()).getString("date"))
                                                .getString("uts"))));
                            }
                            System.out.println("lastFMTracks begin ");
                            LastFMTracks lastFMTracks = new LastFMTracks();
                            lastFMTracks.setName(JSON.parseObject(trackJson.toString()).getString("name"));
                            lastFMTracks.setAlbumText(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("album")).getString("#text"));
                            lastFMTracks.setAlbumMbid(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("album")).getString("mbid"));
                            lastFMTracks.setArtistText(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("artist")).getString("#text"));
                            lastFMTracks.setArtistMbid(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("artist")).getString("mbid"));
                            lastFMTracks.setMbid(JSON.parseObject(trackJson.toString()).getString("mbid"));
                            lastFMTracks.setStreamable(JSON.parseObject(trackJson.toString()).getString("streamable"));
                            lastFMTracks.setUrl(JSON.parseObject(trackJson.toString()).getString("url"));

                            if (lastFMUserInfoMapper.selectByName(lastFMTracks.getName()).size() <= 0) {
                                lastFMTracks.setStatus(0);
                                lastFMTracksMapper.insert(lastFMTracks);
                            } else {
                                lastFMTracksMapper.updateByNameSelective(lastFMTracks);
                            }
                            System.out.println("lastFMTracks end " + lastFMTracks.getName());

                            System.out.println("lastFMRecentTracks begin ");

                            LastFMRecentTracks lastFMRecentTracks = new LastFMRecentTracks();
                            lastFMRecentTracks.setTrackName(lastFMTracks.getName());

                            if (JSON.parseObject(trackJson.toString()).getString("date")!=null) {
                                lastFMRecentTracks.setDateText(new Date(Long.parseLong(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("date")).getString("uts")+"000")-288000000));
                            }
                            lastFMRecentTracks.setDateUts(lastFMTracks.getName());
                            lastFMRecentTracks.setUser(user);
                            lastFMRecentTracks.setPreference(lastFMLoveTracksMapper.selectByUserAndTrack(user, lastFMTracks.getName()).size() > 0 ? 1 : 0);

                            if (lastFMRecentTracksMapper.selectByUserAndTrack(user, lastFMTracks.getName()).size() <= 0) {
                                lastFMRecentTracksMapper.insert(lastFMRecentTracks);
                            } else {
                                break;
                            }
                            System.out.println("lastFMRecentTracks end " + lastFMRecentTracks.getTrackName());
                        }

                        System.out.println("urlStr end : " + urlStr);
                        System.out.println("****************************************");
                        return doGetRecentTrack(method, user, api_key, String.valueOf(pageNow + 1));
                    } else
                        return 0;

                } else
                    return doGetRecentTrack(method, user, api_key, String.valueOf(Integer.parseInt(page) + 1));
            } else
                return doGetRecentTrack(method, user, api_key, String.valueOf(Integer.parseInt(page) + 1));
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int doGetLoveTrack(String method, String user, String api_key, String page) {

        try {
            String urlStr = String.format(lastFmUrl, method, user, api_key, page);
            System.out.println("******************urlStr begin : " + urlStr + "*********** ");
            URL url = new URL(urlStr);
            if (lastFMUrlInfoMapper.selectByUrl(urlStr).size() <= 0) {
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                StringBuilder get = new StringBuilder();
                System.out.println("httpURLConnection.getResponseCode() : " + httpURLConnection.getResponseCode() + "时间 ：" + new Date());
                if (httpURLConnection.getResponseCode() == 200) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));
                    String read;
                    while (((read = bufferedReader.readLine())) != null) {
                        get.append(read + "\r\n");
                    }
                    JSONObject jsonObject = JSON.parseObject(get.toString());
                    if (jsonObject.size() == 0)
                        return 0;
                    jsonObject = JSON.parseObject(jsonObject.get("lovedtracks").toString());
                    LastFMUrlInfo lastFMUrlInfo = new LastFMUrlInfo();
                    System.out.println("begin get urlInfo " + lastFMUrlInfo.getUrl());
                    lastFMUrlInfo.setUrl(urlStr);
                    lastFMUrlInfo.setTotal(JSON.parseObject(jsonObject.get("@attr").toString()).get("total").toString());
                    lastFMUrlInfo.setPerpage(JSON.parseObject(jsonObject.get("@attr").toString()).get("perPage").toString());
                    lastFMUrlInfo.setTotalpages(JSON.parseObject(jsonObject.get("@attr").toString()).get("totalPages").toString());
                    lastFMUrlInfo.setPage(JSON.parseObject(jsonObject.get("@attr").toString()).get("page").toString());
                    lastFMUrlInfoMapper.insert(lastFMUrlInfo);
                    System.out.println("end get urlInfo " + lastFMUrlInfo.getUrl());
                    System.out.println("---------------------------------------");


                    int pageNow = Integer.parseInt(page);
                    int totalPages = Integer.parseInt(JSON.parseObject(jsonObject.get("@attr").toString()).get("totalPages").toString());
                    if (pageNow <= totalPages) {
                        JSONArray trackArr = JSON.parseArray(jsonObject.getString("track"));
                        for (Object trackJson : trackArr) {
                            System.out.println("lastFMTracks begin ");
                            LastFMTracks lastFMTracks = new LastFMTracks();
                            lastFMTracks.setName(JSON.parseObject(trackJson.toString()).getString("name"));

                            if (lastFMUserInfoMapper.selectByName(lastFMTracks.getName()).size() <= 0) {
//                            lastFMTracks.setAlbumText(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("album")).getString("#text"));
//                            lastFMTracks.setAlbumMbid(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("album")).getString("mbid"));
                                lastFMTracks.setArtistText(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("artist")).getString("#text"));
                                lastFMTracks.setArtistMbid(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("artist")).getString("mbid"));
                                lastFMTracks.setMbid(JSON.parseObject(trackJson.toString()).getString("mbid"));
                                lastFMTracks.setStreamable(JSON.parseObject(trackJson.toString()).getString("streamable"));
                                lastFMTracks.setUrl(JSON.parseObject(trackJson.toString()).getString("url"));
                                lastFMTracks.setStatus(0);
                                lastFMTracksMapper.insert(lastFMTracks);
                            } else {
                                break;
                            }
                            System.out.println("lastFMTracks end " + lastFMTracks.getName());

                            System.out.println("lastFMLoveTracks begin ");
                            LastFMLoveTracks lastFMLoveTracks = new LastFMLoveTracks();
                            lastFMLoveTracks.setTrackName(lastFMTracks.getName());
                            lastFMLoveTracks.setDateText(new Date(Long.parseLong(JSON.parseObject(JSON.parseObject(trackJson.toString()).getString("date")).getString("uts"))));
                            lastFMLoveTracks.setDateUts(lastFMTracks.getName());
                            lastFMLoveTracks.setUser(user);

                            if (lastFMLoveTracksMapper.selectByTrackName(lastFMLoveTracks.getTrackName()).size() <= 0) {
                                lastFMLoveTracksMapper.insert(lastFMLoveTracks);
                            } else {
                                break;
                            }
                            System.out.println("lastFMLoveTracks end " + lastFMLoveTracks.getTrackName());
                        }

                        System.out.println("urlStr end : " + urlStr);
                        System.out.println("****************************************");
                        return doGetLoveTrack(method, user, api_key, String.valueOf(pageNow + 1));
                    } else
                        return 0;

                } else
                    return doGetLoveTrack(method, user, api_key, String.valueOf(Integer.parseInt(page) + 1));
            } else
                return doGetLoveTrack(method, user, api_key, String.valueOf(Integer.parseInt(page) + 1));
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void main(String[] args) throws Exception {

    }
}
