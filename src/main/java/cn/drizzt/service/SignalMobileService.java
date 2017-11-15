package cn.drizzt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.drizzt.mapper.SignalMobileMapper;
import cn.drizzt.model.SignalMobile;
import cn.drizzt.model.SignalMobileExample;
import cn.drizzt.model.SignalMobileExample.Criteria;

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

    
}
