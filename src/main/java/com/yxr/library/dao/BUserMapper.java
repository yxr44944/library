package com.yxr.library.dao;

import com.yxr.library.model.BUser;
import com.yxr.library.model.BUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BUserMapper {
    int countByExample(BUserExample example);

    int deleteByExample(BUserExample example);

    int deleteByPrimaryKey(String userid);

    int insert(BUser record);

    int insertSelective(BUser record);

    List<BUser> selectByExample(BUserExample example);

    BUser selectByPrimaryKey(String userid);

    int updateByExampleSelective(@Param("record") BUser record, @Param("example") BUserExample example);

    int updateByExample(@Param("record") BUser record, @Param("example") BUserExample example);

    int updateByPrimaryKeySelective(BUser record);

    int updateByPrimaryKey(BUser record);
}