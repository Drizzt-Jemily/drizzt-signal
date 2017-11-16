package cn.drizzt;

import javax.sound.midi.Receiver;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "cn.drizzt.mapper")
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("服务启动完成!");
	}

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("index");
		result.addObject("demo", "欢迎您");
		return result;
	}
}
