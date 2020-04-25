package com.yxr.library.vo;

import com.yxr.library.model.Book;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @描述
 * @作者 yxr
 * @日期 4/23/2020 3:44 PM
 */
@Data
public class BookVo extends Book {
    private Date borrowNewTime;
    private String flag;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endTime;
}
