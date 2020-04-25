package com.yxr.library.dao;

import com.yxr.library.model.Borrow;
import com.yxr.library.model.BorrowExample;
import java.util.List;

import com.yxr.library.vo.BorrowVo;
import org.apache.ibatis.annotations.Param;

public interface BorrowMapper {
    int countByExample(BorrowExample example);

    int deleteByExample(BorrowExample example);

    int deleteByPrimaryKey(String broid);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    List<Borrow> selectByExample(BorrowExample example);

    Borrow selectByPrimaryKey(String broid);

    int updateByExampleSelective(@Param("record") Borrow record, @Param("example") BorrowExample example);

    int updateByExample(@Param("record") Borrow record, @Param("example") BorrowExample example);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

    List<BorrowVo> queryBorrowBook(String bid);
}