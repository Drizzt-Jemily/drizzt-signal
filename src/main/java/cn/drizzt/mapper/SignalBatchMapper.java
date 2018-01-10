package cn.drizzt.mapper;

import cn.drizzt.entity.SignalBatch;
import cn.drizzt.entity.SignalBatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SignalBatchMapper {
    int countByExample(SignalBatchExample example);

    int deleteByExample(SignalBatchExample example);

    int deleteByPrimaryKey(String id);

    int insert(SignalBatch record);

    int insertSelective(SignalBatch record);

    List<SignalBatch> selectByExample(SignalBatchExample example);

    SignalBatch selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SignalBatch record, @Param("example") SignalBatchExample example);

    int updateByExample(@Param("record") SignalBatch record, @Param("example") SignalBatchExample example);

    int updateByPrimaryKeySelective(SignalBatch record);

    int updateByPrimaryKey(SignalBatch record);

    List<SignalBatch> selectPage(SignalBatchExample example);
}