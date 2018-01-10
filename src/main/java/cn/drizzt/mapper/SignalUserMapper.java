package cn.drizzt.mapper;

import cn.drizzt.entity.SignalUser;
import cn.drizzt.entity.SignalUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SignalUserMapper {
    int countByExample(SignalUserExample example);

    int deleteByExample(SignalUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SignalUser record);

    int insertSelective(SignalUser record);

    List<SignalUser> selectByExample(SignalUserExample example);

    SignalUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SignalUser record, @Param("example") SignalUserExample example);

    int updateByExample(@Param("record") SignalUser record, @Param("example") SignalUserExample example);

    int updateByPrimaryKeySelective(SignalUser record);

    int updateByPrimaryKey(SignalUser record);

    List<SignalUser> selectPage(SignalUserExample example);
}