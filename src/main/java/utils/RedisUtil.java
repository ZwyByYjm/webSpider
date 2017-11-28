package utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisUtil
{
	private static JedisPool pool;

	private static Integer index;

	private static Jedis jedis;
	

	private static final String QUEUE_PREFIX = "queue_";

	private static final String SET_PREFIX = "set_";

	private static final String ITEM_PREFIX = "item_";

	//初始化
	public static JedisPool init()
	{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxWait(1000000);
		config.setMaxActive(1000);
		config.setMaxIdle(100);
		pool = new JedisPool(config, ConfigUtil.getProperty("redis","redis.ip"),6379,9999999);
		jedis = pool.getResource();
		index = Integer.parseInt(ConfigUtil.getProperty("redis","redis.index"));
		jedis.select(index);
		return pool;
	}
	// List：队列右边插入
	public static void push(String queueName,String url) 
	{
		try 
		{
			jedis.rpush(queueName , url);
		}
		finally 
		{
			pool.returnResource(jedis);
		}
	}
	//Set:向Set推入元素
	public static void sadd(String setName,String value) 
	{
		try 
		{
			jedis.sadd(setName, value);
		}
		finally 
		{
			pool.returnResource(jedis);
		}
	}
	public static String sget(String setName)
	{
		try 
		{
			return jedis.srandmember(setName);
		}
		finally 
		{
			pool.returnResource(jedis);
		}
	}
	
	// Set：移除元素
	public static void sremove(String setName,String url)
	{
		try 
		{
			jedis.srem(setName, url);
		}
		finally 
		{
			pool.returnResource(jedis);
		}
	}
	// List：判断是否存在元素
	public static boolean isExistInList(String listKey,String url)
	{
		try
		{
			Long listLen = jedis.llen(listKey);
			System.out.println(listKey + "长度为" + listLen);
			for(int i=0;i<listLen;i++)
			{
				if(url.equals(jedis.lindex(listKey, i)))
				{
					System.out.println("Find the valule in " + i);
					return true;
				}
			}
		}
		finally
		{
			pool.returnResource(jedis);
		}
		return false;
	}

	public static void main(String[] args)
	{
		init();
		boolean isExist = isExistInList("music163","www.music16.com");
		System.out.println(isExist);
	}

}
