package com.webSpider.spider.schedule;

//redis 中 爬虫待抓取队列的常量
public interface QueueNameConstant {
	  static final String QUEUE_MUSICLIST_INFO="queue_musiclist_info_";
	  static final String QUEUE_MUSIC_INFO="queue_music_info_";
	  static final String QUEUE_USER_INFO = "queue_user_info_";
	  
	  static final String QUEUE_MUSICLIST_ERROR = "queue_musiclist_error";
	  static final String QUEUE_MUSICLIST2MUSIC_ERROR = "queue_musiclist2music_error";
	  static final String QUEUE_MUSIC_ERROR = "queue_music_error";
	  static final String QUEUE_COMPOSER_ERROR = "queue_composer_error";
	static final String QUEUE_ALBUM_ERROR = "queue_album_error";
	static final String QUEUE_USER_ERROR = "queue_user_error";
}
