package cn.drizzt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.drizzt.entity.SignalBatch;
import cn.drizzt.mapper.SignalBatchMapper;

@Service
public class SignalBatchService {

	@Autowired
	private SignalBatchMapper signalBatchMapper;

	public void add(SignalBatch signalBatch) {
		signalBatchMapper.insertSelective(signalBatch);
	}

}
