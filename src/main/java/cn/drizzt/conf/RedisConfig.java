package cn.drizzt.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import cn.drizzt.listener.AuthMessageDelegateListener;

@Configuration
public class RedisConfig {

	@Bean
	RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

	// @Bean
	// JdkSerializationRedisSerializer jdkSerializer() {
	// return new JdkSerializationRedisSerializer();
	// }

	@Bean
	MessageListenerAdapter authMessageListener() {
		AuthMessageDelegateListener authMessageDelegateListener = new AuthMessageDelegateListener();
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(authMessageDelegateListener,
				"handleMessage");
		// messageListenerAdapter.setSerializer(jdkSerializer());
		return messageListenerAdapter;
	}

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(authMessageListener(), new PatternTopic("auth"));
		return container;
	}

}
