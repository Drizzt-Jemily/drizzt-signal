package cn.drizzt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.drizzt.entity.SignalUser;
import cn.drizzt.mapper.SignalUserMapper;

@Service
public class SignalUserService {

	@Autowired
	private SignalUserMapper signalUserMapper;

	public SignalUser getById(String id) {
		return signalUserMapper.selectByPrimaryKey(id);
	}

	public void reduceNumber(String id) {
		signalUserMapper.reduceNumber(id);
	}

}
