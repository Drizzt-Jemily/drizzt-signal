package cn.drizzt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.service.SignalAuthService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class ApplicationTests {

	@Autowired
	private SignalAuthService signalAuthService;

	@Test
	public void testPageHelper() {
//		System.out.println(authResource.getChManagerPool().size());
		SignalAuth waitAuth = signalAuthService.getWaitAuth();
		while(waitAuth!=null) {
			System.out.println(waitAuth.getStartTime());
			waitAuth = signalAuthService.getWaitAuth();
		}
	}

}
