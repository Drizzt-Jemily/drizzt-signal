package cn.drizzt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import cn.drizzt.entity.SignalMobile;
import cn.drizzt.service.SignalMobileService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class ApplicationTests {

	@Autowired
    private SignalMobileService signalMobileService;
	
	@Test
    public void testPageHelper() {
		SignalMobile signalMobile = new SignalMobile();
		signalMobile.setPage(1);
		signalMobile.setRows(5);
		List<SignalMobile> all = signalMobileService.getAll(signalMobile);
		System.out.println(all.get(0).getMobileNumber());
    }

}
