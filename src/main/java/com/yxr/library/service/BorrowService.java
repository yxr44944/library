package com.yxr.library.service;

import com.yxr.library.dao.BorrowMapper;
import com.yxr.library.vo.BorrowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @描述
 * @作者 yxr
 * @日期 4/24/2020 5:34 PM
 */
@Service
public class BorrowService {
    @Autowired
    BorrowMapper borrowMapper;

    public List<BorrowVo> queryBorrowBook(String bid) {
        return borrowMapper.queryBorrowBook(bid);
    }
}
