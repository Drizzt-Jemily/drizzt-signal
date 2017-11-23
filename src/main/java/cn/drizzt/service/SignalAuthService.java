package cn.drizzt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.entity.SignalAuthExample;
import cn.drizzt.entity.SignalAuthExample.Criteria;
import cn.drizzt.mapper.SignalAuthMapper;
import cn.drizzt.util.Const;

@Service
public class SignalAuthService {

	@Autowired
	private SignalAuthMapper signalAuthMapper;

	public void add(SignalAuth signalAuth) {
		signalAuthMapper.insertSelective(signalAuth);
	}

	public SignalAuth getWaitAuth() {
		SignalAuthExample example = new SignalAuthExample();
		Criteria criteria = example.createCriteria();
		criteria.andCallResultEqualTo(Const.CALL_RESULT_0);
		example.setOrderByClause("start_time asc");
		List<SignalAuth> signalAuthList = signalAuthMapper.selectByExample(example);
		if (signalAuthList.size() > 0) {
			return signalAuthList.get(0);
		} else {
			return null;
		}
	}
	
	public void update(SignalAuth signalAuth){
		signalAuthMapper.updateByPrimaryKeySelective(signalAuth);
	}

}
