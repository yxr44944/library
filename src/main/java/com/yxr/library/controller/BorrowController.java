package com.yxr.library.controller;

import com.yxr.library.service.BorrowService;
import com.yxr.library.vo.BorrowVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @描述
 * @作者 yxr
 * @日期 4/24/2020 5:33 PM
 */
@Controller
@RequestMapping("/bor")
public class BorrowController {

    @Autowired
    BorrowService borrowService;
    @RequestMapping("/borrowList")
    @ResponseBody
    public List<BorrowVo> borringList(String bid){
        return  borrowService.queryBorrowBook(bid);
    }
}
