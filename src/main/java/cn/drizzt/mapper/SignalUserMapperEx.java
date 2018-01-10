package cn.drizzt.mapper;

import org.apache.ibatis.annotations.Update;

public interface SignalUserMapperEx {

	@Update("update signal_user set number=number-1 where id=#{id}")
	public void reduceNumber(String id);

	@Update("update signal_user set number=number+1 where id=#{id}")
	public void increaseNumber(String id);
}