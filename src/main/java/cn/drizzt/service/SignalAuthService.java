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
		example.setPageIndex(1);
		example.setPageSize(1);
		example.setOrderByClause("start_time asc");
		List<SignalAuth> signalAuthList = signalAuthMapper.selectByExample(example);
		if (signalAuthList.size() > 0) {
			return signalAuthList.get(0);
		} else {
			return null;
		}
	}

	public int getWaitAuthNum() {
		SignalAuthExample example = new SignalAuthExample();
		Criteria criteria = example.createCriteria();
		criteria.andCallResultEqualTo(Const.CALL_RESULT_0);
		return signalAuthMapper.countByExample(example);
	}

	public void update(SignalAuth signalAuth) {
		signalAuthMapper.updateByPrimaryKeySelective(signalAuth);
	}

	public SignalAuth getById(String id) {
		return signalAuthMapper.selectByPrimaryKey(id);
	}

	public List<SignalAuth> getByBatchId(String batchId) {
		SignalAuthExample example = new SignalAuthExample();
		Criteria criteria = example.createCriteria();
		criteria.andBatchIdEqualTo(batchId);
		example.setOrderByClause("call_result desc");
		return signalAuthMapper.selectByExample(example);
	}

	public SignalAuth getByLastCalling(String calling) {
		SignalAuthExample example = new SignalAuthExample();
		Criteria criteria = example.createCriteria();
		criteria.andCallingEqualTo(calling);
		example.setPageIndex(1);
		example.setPageSize(1);
		example.setOrderByClause("start_time desc");
		List<SignalAuth> signalAuths = signalAuthMapper.selectByExample(example);
		if (signalAuths.size() > 0) {
			SignalAuth signalAuth = signalAuths.get(0);
			long startTime = signalAuth.getStartTime();
			Integer callResult = signalAuth.getCallResult();
			if (callResult == 0 || callResult == 99) {
				return signalAuth;
			} else if (System.currentTimeMillis() - startTime < 4 * 60 * 60 * 1000) {
				if (callResult == Const.CALL_RESULT_5 || callResult == Const.CALL_RESULT_7
						|| callResult == Const.CALL_RESULT_8) {
					return signalAuth;
				} else {
					return null;
				}
			} else if (System.currentTimeMillis() - startTime < 24 * 60 * 60 * 1000) {
				if (callResult == Const.CALL_RESULT_1 || callResult == Const.CALL_RESULT_2
						|| callResult == Const.CALL_RESULT_3 || callResult == Const.CALL_RESULT_4
						|| callResult == Const.CALL_RESULT_6) {
					return signalAuth;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}
