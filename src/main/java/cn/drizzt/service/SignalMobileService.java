package cn.drizzt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.drizzt.entity.SignalMobile;
import cn.drizzt.entity.SignalMobileExample;
import cn.drizzt.entity.SignalMobileExample.Criteria;
import cn.drizzt.mapper.SignalMobileMapper;

@Service
public class SignalMobileService {

	@Autowired
	private SignalMobileMapper signalMobileMapper;

	public SignalMobile getById(String mobileNumber) {
		return signalMobileMapper.selectByPrimaryKey(mobileNumber);
	}

	public List<SignalMobile> getByCode(String areaCode) {
		SignalMobileExample example = new SignalMobileExample();
		Criteria criteria = example.createCriteria();
		criteria.andAreaCodeEqualTo(areaCode);
		return signalMobileMapper.selectByExample(example);
	}

	public void add(SignalMobile signalMobile) {
		signalMobileMapper.insertSelective(signalMobile);
	}
	
	public String convertCalling(String calling, String areaCode) {
		String tmp = calling.substring(0, 7);
		try {
			SignalMobileExample example = new SignalMobileExample();
			Criteria criteria = example.createCriteria();
			criteria.andMobileNumberEqualTo(tmp);
			List<SignalMobile> signalMobiles = signalMobileMapper.selectByExample(example);
			SignalMobile signalMobile = null;
			if (signalMobiles.size() > 0) {
				signalMobile = signalMobiles.get(0);
			}
			if (signalMobile != null && null != signalMobile.getAreaCode()) {
				if (!signalMobile.getAreaCode().equals(areaCode)) {
					return "0" + calling;
				} else {
					return calling;
				}

			} else {
				return "0" + calling;
			}
		} catch (Exception e) {
			return "0" + calling;
		}
	}

}
