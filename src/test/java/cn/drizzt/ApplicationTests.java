package cn.drizzt;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.entity.SignalMobile;
import cn.drizzt.service.SignalAuthService;
import cn.drizzt.service.SignalMobileService;
import cn.drizzt.util.Const;
import cn.drizzt.util.Numbers;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(Application.class)
public class ApplicationTests {

	@Autowired
    private SignalMobileService signalMobileService;
	
	@Autowired
    private SignalAuthService signalAuthService;
	
	@Test
    public void test() {
		SignalMobile signalMobile = signalMobileService.getById("1300000");
		System.out.println(signalMobile.getAreaCode());
		List<SignalMobile> signalMobiles = signalMobileService.getByCode("0531");
		System.out.println(signalMobiles.get(0).getMobileArea());
		SignalAuth signalAuth = new SignalAuth();
		signalAuth.setId(Numbers.uuid());
		signalAuth.setCalling("18611967787");
		signalAuth.setStartTime(System.currentTimeMillis());
		signalAuth.setCallResult(Const.CALL_RESULT_0);
		signalAuthService.add(signalAuth);
		SignalAuth waitAuth = signalAuthService.getWaitAuth();
		System.out.println(waitAuth.getId());
    }

}
