package cn.drizzt.mapper;

import cn.drizzt.entity.SignalMobile;
import cn.drizzt.entity.SignalMobileExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SignalMobileMapper {
    int countByExample(SignalMobileExample example);

    int deleteByExample(SignalMobileExample example);

    int deleteByPrimaryKey(String mobileNumber);

    int insert(SignalMobile record);

    int insertSelective(SignalMobile record);

    List<SignalMobile> selectByExample(SignalMobileExample example);

    SignalMobile selectByPrimaryKey(String mobileNumber);

    int updateByExampleSelective(@Param("record") SignalMobile record, @Param("example") SignalMobileExample example);

    int updateByExample(@Param("record") SignalMobile record, @Param("example") SignalMobileExample example);

    int updateByPrimaryKeySelective(SignalMobile record);

    int updateByPrimaryKey(SignalMobile record);

    List<SignalMobile> selectPage(SignalMobileExample example);
}