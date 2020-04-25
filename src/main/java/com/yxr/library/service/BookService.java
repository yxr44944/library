package com.yxr.library.service;

import com.yxr.library.dao.BookMapper;
import com.yxr.library.dao.BorrowMapper;
import com.yxr.library.model.BUser;

import com.yxr.library.model.Book;
import com.yxr.library.model.Borrow;
import com.yxr.library.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @描述
 * @作者 yxr
 * @日期 4/22/2020 8:17 PM
 */
@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowMapper borrowMapper;

    public List<BookVo> queryBookList(BookVo bookVo) {
        return bookMapper.queryBookList(bookVo);
    }


    //借图书
    public boolean lendBook(BUser userInfo, String bid) {
        //判断用户是否为空
        if (userInfo != null) {
            //创建日志对象
            Borrow borrow = new Borrow();
            //添加图书的id
            borrow.setBid(bid);
            //添加日志的id（uuid）
            borrow.setBroid(UUID.randomUUID().toString());
            //创建借书的时间
            borrow.setBorrowingTime(new Date());
            //借书用户的id
            borrow.setUserid(userInfo.getUserid());
            //判断添加日志成功
            int insert = borrowMapper.insert(borrow);
            //更新数据
            int updateFlag = bookMapper.updateFlag(bid);
            //进行返回如果成功则修改状态如果不成功事务回滚
            if (insert > 0 && updateFlag > 0) {
                return true;
            }
            return false;

        } else {
            return false;
        }
    }
    //归还图书
    public boolean backBook(String bid) {
        Book book = new Book();
        book.setBroid("0");
        book.setBid(bid);
        int i = bookMapper.updateByPrimaryKeySelective(book);
        if (i > 0) {
            return true;
        }
        return false;
    }

    //添加
    public int addBook(Book book) {
        //uuid
        book.setBid(UUID.randomUUID().toString());
        //借阅量
       book.setCount(BigDecimal.valueOf(0));
       //借阅状态
       book.setBroid("0");
       return bookMapper.insert(book);
    }

    public BookVo queryBookListById(String bid) {
        return bookMapper.queryBookListById(bid);
    }
}
