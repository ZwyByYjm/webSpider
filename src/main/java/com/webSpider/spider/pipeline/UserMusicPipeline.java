package com.webSpider.spider.pipeline;

import com.webSpider.dao.*;
import com.webSpider.pojo.Album;
import com.webSpider.pojo.ComposerInfo;
import com.webSpider.pojo.MusicInfo;
import com.webSpider.pojo.User2Music;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 印佳明
 * @create 2017-10-26 19:59
 */
@Component
@Transactional
public class UserMusicPipeline implements Pipeline
{
    @Autowired
    private MusicInfoMapper musicInfoMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private ComposerInfoMapper composerInfoMapper;
    @Autowired
    private User2MusicMapper user2MusicMapper;

    @Override
    public void process(ResultItems resultItems, Task task)
    {
        String userno = resultItems.get("userno");
        if(StringUtils.isNotEmpty(userno)){
            Map<String,Object> map = new HashMap<>();
            map.put("userid",userno);
            map.put("level",-1);
            userInfoMapper.updateByUserIdAndLevel(map);
        }

        List<User2Music> user2MusicList = resultItems.get("user2MusicList");
        if (user2MusicList != null && user2MusicList.size() != 0)
        {
            for (User2Music anUser2MusicList : user2MusicList) {
                if (user2MusicMapper.selectByUseridAndMusicid(anUser2MusicList.getUserid(),anUser2MusicList.getMusicid()).size() <= 0) {
                    user2MusicMapper.insert(anUser2MusicList);
                }
            }
        }
        List<MusicInfo> musicInfoList = resultItems.get("musicInfoList");
        if (musicInfoList != null && musicInfoList.size() != 0)
        {
            for (MusicInfo musicInfo : musicInfoList) {
                if (musicInfoMapper.selectByMusicId(musicInfo.getMusicid()).size() <= 0) {
                    musicInfoMapper.insert(musicInfo);
                }
            }
        }
        List<ComposerInfo> composerInfoList = resultItems.get("composerInfoList");
        if (composerInfoList != null && composerInfoList.size() != 0)
        {
            for (ComposerInfo composerInfo : composerInfoList) {
                if (composerInfoMapper.selectByComposerId(composerInfo.getComposerid()).size() <= 0) {
                    composerInfoMapper.insert(composerInfo);
                }
            }
        }
        List<Album> albumList = resultItems.get("albumList");
        if (albumList != null && albumList.size() != 0)
        {
            for (Album album : albumList) {
                if (albumMapper.selectByAlbumId(album.getAlbumid()).size() <= 0) {
                    albumMapper.insert(album);
                }
            }
        }
    }
}
