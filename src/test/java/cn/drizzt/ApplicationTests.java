package cn.drizzt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import cn.drizzt.entity.SignalUser;
import cn.drizzt.service.SignalUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class ApplicationTests {

	@Autowired
	private SignalUserService signalUserService;

	@Test
	public void testPageHelper() {
		List<SignalUser> all = signalUserService.getAll();
		System.out.println(all.get(0));
	}

}
