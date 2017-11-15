package cn.drizzt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import cn.drizzt.model.SignalMobile;
import cn.drizzt.service.SignalMobileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class ApplicationTests {

	@Autowired
    private SignalMobileService signalMobileService;
	
	@Test
    public void test() {
		SignalMobile signalMobile = signalMobileService.getById("1300000");
		System.out.println(signalMobile.getAreaCode());
		List<SignalMobile> signalMobiles = signalMobileService.getByCode("0531");
		System.out.println(signalMobiles.get(0).getMobileArea());
    }

}
