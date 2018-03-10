package com.webSpider.spider.pipeline;

import com.webSpider.dao.*;
import com.webSpider.pojo.*;
import com.webSpider.spider.schedule.QueueNameConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import utils.RedisUtil;

import java.util.List;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Component
@Transactional
public class MusicListInfoPipeline implements Pipeline
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
        if (!resultItems.getRequest().getUrl().contains("http://music.163.com/discover/playlist/"))
        {
            try
            {
                MusicListInfo musicListInfo = resultItems.get("musicListInfo");
                if (musicListInfo != null && musicListInfoMapper.selectByMusicListId(musicListInfo.getMusiclistid()).size() <= 0)
                {
                    musicListInfoMapper.insert(musicListInfo);
                    System.out.println(musicListInfo);
                }
            } catch (Exception e)
            {
                System.out.println("插入歌单信息数据异常:" + e.getCause());
                RedisUtil.push(QueueNameConstant.QUEUE_MUSICLIST_ERROR, resultItems.getRequest().getUrl());
                //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
            }

            List<MusicList2Music> musicList2MusicsList = resultItems.get("musicList2MusicsList");
            if (musicList2MusicsList != null)
            {
                for (MusicList2Music musicList2Music : musicList2MusicsList)
                {
                    try
                    {
                        if (musicList2MusicMapper.selectByMusicListIdMusicId(musicList2Music.getMusiclistid(), musicList2Music.getMusicid()).size() <= 0)
                        {
                            musicList2MusicMapper.insert(musicList2Music);
                            System.out.println(musicList2Music);
                        }
                    } catch (Exception e)
                    {
                        System.out.println("插入歌单_歌曲表信息数据异常:" + e.getCause());
                        RedisUtil.push(QueueNameConstant.QUEUE_MUSICLIST2MUSIC_ERROR, resultItems.getRequest().getUrl() + "musicid = " + musicList2Music.getMusicid());
                        //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
                    }
                }
            }
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
                RedisUtil.push(QueueNameConstant.QUEUE_MUSIC_ERROR, resultItems.getRequest().getUrl());
                //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
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
                RedisUtil.push(QueueNameConstant.QUEUE_COMPOSER_ERROR, resultItems.getRequest().getUrl());
                //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
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
                RedisUtil.push(QueueNameConstant.QUEUE_ALBUM_ERROR, resultItems.getRequest().getUrl());
                //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
            }


            List<UserInfo> userInfoList = resultItems.get("userInfoList");
            if (userInfoList != null && userInfoList.size()!=0)
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
                        System.out.println("插入歌单_歌曲表信息数据异常:" + e.getCause());
                        RedisUtil.push(QueueNameConstant.QUEUE_USER_ERROR, resultItems.getRequest().getUrl() + "userid = " + userInfo.getUserid());
                        //FileUtils.writeStrToFile(resultItems.getRequest().getUrl()+ "\r\n","E:\\ideawork\\webSpider\\src\\main\\resources\\musiclistinfo_error.txt");
                    }
                }
            }
        }
    }
}
