package cn.drizzt.conf;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import cn.drizzt.util.Const;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AuthConfig {

	@Bean(name = "dialExecutor")
	public ThreadPoolExecutor dialExecutor() {
		return new ThreadPoolExecutor(Const.CARD_NUMBER, Const.CARD_NUMBER, Const.POOL_KEEPALIVE_SECOND,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), Executors.defaultThreadFactory());
	}

}
