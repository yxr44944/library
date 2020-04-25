package com.yxr.library.vo;

import com.yxr.library.model.Borrow;
import lombok.Data;


/**
 * @描述
 * @作者 yxr
 * @日期 4/24/2020 5:37 PM
 */
@Data
public class BorrowVo extends Borrow {
    //书籍名称
    private String bname;
    //用户名称
    private String uname;
}
