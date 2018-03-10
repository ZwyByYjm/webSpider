package com.webSpider.spider.pipeline;

import com.webSpider.dao.*;
import com.webSpider.pojo.Album;
import com.webSpider.pojo.ComposerInfo;
import com.webSpider.pojo.MusicInfo;
import com.webSpider.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import utils.FileUtils;

import java.util.List;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Component
@Transactional
public class MusicInfoPipeline implements Pipeline
{
    @Autowired
    private MusicListInfoMapper musicListInfoMapper;
    @Autowired
    private MusicList2MusicMapper musicList2MusicMapper;
    @Autowired
    private MusicInfoMapper musicInfoMapper;
    @Autowired
    private ComposerInfoMapper composerInfoMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public void process(ResultItems resultItems, Task task)
    {
        System.out.println(resultItems.getRequest().getUrl());

        try
        {
            MusicInfo musicInfo = resultItems.get("musicInfo");
            if (musicInfo != null && musicInfoMapper.selectByMusicId(musicInfo.getMusicid()).size() <= 0)
            {
                musicInfoMapper.insert(musicInfo);
                System.out.println(musicInfo);
            }
        } catch (Exception e)
        {
            System.out.println("插入歌曲信息数据异常:" + e.getCause());
            //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
            FileUtils.writeStrToFile(resultItems.getRequest().getUrl() + "\r\n", "E:\\ideawork\\webSpider\\src\\main\\resources\\music_error1.txt");
        }

        try
        {
            ComposerInfo composerInfo = resultItems.get("composerInfo");
            if (composerInfo != null && composerInfoMapper.selectByComposerId(composerInfo.getComposerid()).size() <= 0)
            {
                composerInfoMapper.insert(composerInfo);
                System.out.println(composerInfo);
            }
        } catch (Exception e)
        {
            System.out.println("插入歌手信息数据异常:" + e.getCause());
            //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
            FileUtils.writeStrToFile(resultItems.getRequest().getUrl() + "\r\n", "E:\\ideawork\\webSpider\\src\\main\\resources\\music_error1.txt");
        }
        try
        {
            Album album = resultItems.get("album");
            if (album != null && albumMapper.selectByAlbumId(album.getAlbumid()).size() <= 0)
            {
                albumMapper.insert(album);
                System.out.println(album);
            }
        } catch (Exception e)
        {
            System.out.println("插入专辑信息数据异常:" + e.getCause());
            //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
            FileUtils.writeStrToFile(resultItems.getRequest().getUrl() + "\r\n", "E:\\ideawork\\webSpider\\src\\main\\resources\\music_error1.txt");
        }


        List<UserInfo> userInfoList = resultItems.get("userInfoList");
        if (userInfoList != null && userInfoList.size() != 0)
        {
            for (UserInfo userInfo : userInfoList)
            {
                try
                {
                    if (userInfoMapper.selectByUserid(userInfo.getUserid()).size() <= 0)
                    {
                        userInfoMapper.insert(userInfo);
                        System.out.println(userInfo);
                    }
                } catch (Exception e)
                {
                    System.out.println("插入用户信息数据异常:" + e.getCause());
                    //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
                    FileUtils.writeStrToFile(resultItems.getRequest().getUrl() + "\r\n", "E:\\ideawork\\webSpider\\src\\main\\resources\\music_error1.txt");
                }
            }

        }
    }
}
