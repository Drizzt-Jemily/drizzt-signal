package cn.drizzt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.drizzt.entity.SignalUser;
import cn.drizzt.entity.SignalUserExample;
import cn.drizzt.mapper.SignalUserMapper;
import cn.drizzt.mapper.SignalUserMapperEx;

@Service
public class SignalUserService {

	@Autowired
	private SignalUserMapper signalUserMapper;
	
	@Autowired
	private SignalUserMapperEx signalUserMapperEx;

	public SignalUser getById(String id) {
		return signalUserMapper.selectByPrimaryKey(id);
	}

	public void reduceNumber(String id) {
		signalUserMapperEx.reduceNumber(id);
	}
	
	public void increaseNumber(String id) {
		signalUserMapperEx.increaseNumber(id);
	}
	
	public List<SignalUser> getAll(){
		SignalUserExample example = new SignalUserExample();
		return signalUserMapper.selectByExample(example);
	}
	
	public void add(SignalUser signalUser){
		signalUserMapper.insertSelective(signalUser);
	}
	
	public void edit(SignalUser signalUser){
		signalUserMapper.updateByPrimaryKeySelective(signalUser);
	}

}
