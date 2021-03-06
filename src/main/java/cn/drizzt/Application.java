package cn.drizzt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.drizzt.thread.AuthResource;

@Controller
@EnableWebMvc
@SpringBootApplication
@MapperScan(basePackages = "cn.drizzt.mapper")
public class Application extends WebMvcConfigurerAdapter implements CommandLineRunner {

	@Autowired
	private AuthResource authResource;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		authResource.init();
	}

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView result = new ModelAndView("index");
		return result;
	}

	@RequestMapping("/batch")
	public ModelAndView batch() {
		ModelAndView result = new ModelAndView("batch");
		return result;
	}
}
