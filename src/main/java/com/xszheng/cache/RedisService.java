package com.xszheng.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.xszheng.domain.SkProductStock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisService {
	
	private final Logger log = LoggerFactory.getLogger(RedisService.class);
	
	private JedisPool jedisPool;

	public RedisService(String ip, int port) {
		super();
		jedisPool = new JedisPool(ip, port);
	}
	
	private RuntimeSchema<SkProductStock> schema = RuntimeSchema.createFrom(SkProductStock.class);
	
	public SkProductStock getProduct(long id){
		// redis操作逻辑
		try {
			Jedis jedis = jedisPool.getResource();
			try{
				String key = "seckill:"+id;
				// redis 并没有实现内部序列化操作
				// 采用自定义序列化
				// protostuff:pojo
				byte[] bytes = jedis.get(key.getBytes());
				if(bytes != null){
					// 空对象
					SkProductStock product = schema.newMessage();
					ProtostuffIOUtil.mergeFrom(bytes, product, schema);
					// skProductStock 被反序列化
					return product;
				}
			} finally{
				jedis.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String putProduct(SkProductStock product){
		try {
			Jedis jedis = jedisPool.getResource();
			try{
				String key = "seckill:"+product.getId();
				byte[] bytes = ProtostuffIOUtil.toByteArray(product, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
				// 有效期60s
				String result = jedis.setex(key.getBytes(), 60, bytes);
				return result;
			} finally{
				jedis.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
