package com.yxr.library.dao;

import com.yxr.library.model.Book;
import com.yxr.library.model.BookExample;
import java.util.List;

import com.yxr.library.vo.BookVo;
import org.apache.ibatis.annotations.Param;

public interface BookMapper {
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(String bid);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(String bid);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);


    List<BookVo> queryBookList(BookVo bookVo);

    BookVo queryBookListById(String bid);

    int updateFlag(String bid);
}