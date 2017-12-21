package cn.drizzt.mapper;

import org.apache.ibatis.annotations.Update;

import cn.drizzt.entity.SignalUser;
import cn.drizzt.util.MyMapper;

public interface SignalUserMapper extends MyMapper<SignalUser> {

	@Update("update signal_user set number=number-1 where id=#{id}")
	public void reduceNumber(String id);
	
	@Update("update signal_user set number=number+1 where id=#{id}")
	public void increaseNumber(String id);
	
}
