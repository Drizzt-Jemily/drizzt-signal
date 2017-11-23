package cn.drizzt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.drizzt.entity.SignalMobile;
import cn.drizzt.entity.SignalMobileExample;
import cn.drizzt.entity.SignalMobileExample.Criteria;
import cn.drizzt.mapper.SignalMobileMapper;

@Service
public class SignalMobileService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignalMobileService.class);

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
	
	public List<SignalMobile> getAll(SignalMobile signalMobile) {
        if (signalMobile.getPage() != null && signalMobile.getRows() != null) {
        	PageHelper.orderBy("mobile_number desc");
            PageHelper.startPage(signalMobile.getPage(), signalMobile.getRows());
        }
        return signalMobileMapper.selectAll();
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
					LOGGER.debug("数据库匹配，手机号加0");
					return "0" + calling;
				} else {
					LOGGER.debug("数据库匹配，手机号不加0");
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
