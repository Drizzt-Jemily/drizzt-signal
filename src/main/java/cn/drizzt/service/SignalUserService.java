package cn.drizzt.service;

import java.util.List;

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
	
	public void increaseNumber(String id) {
		signalUserMapper.increaseNumber(id);
	}
	
	public List<SignalUser> getAll(){
		return signalUserMapper.selectAll();
	}
	
	public void add(SignalUser signalUser){
		signalUserMapper.insertSelective(signalUser);
	}
	
	public void edit(SignalUser signalUser){
		signalUserMapper.updateByPrimaryKeySelective(signalUser);
	}

}
