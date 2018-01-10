package cn.drizzt.mapper;

import cn.drizzt.entity.SignalAuth;
import cn.drizzt.entity.SignalAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SignalAuthMapper {
    int countByExample(SignalAuthExample example);

    int deleteByExample(SignalAuthExample example);

    int deleteByPrimaryKey(String id);

    int insert(SignalAuth record);

    int insertSelective(SignalAuth record);

    List<SignalAuth> selectByExample(SignalAuthExample example);

    SignalAuth selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SignalAuth record, @Param("example") SignalAuthExample example);

    int updateByExample(@Param("record") SignalAuth record, @Param("example") SignalAuthExample example);

    int updateByPrimaryKeySelective(SignalAuth record);

    int updateByPrimaryKey(SignalAuth record);

    List<SignalAuth> selectPage(SignalAuthExample example);
}