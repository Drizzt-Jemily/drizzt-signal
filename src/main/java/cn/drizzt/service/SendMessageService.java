package cn.drizzt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public void sendMessage(String channel, String message) {
		redisTemplate.convertAndSend(channel, message);
	}
}