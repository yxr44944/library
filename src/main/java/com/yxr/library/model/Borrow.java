package com.yxr.library.model;



import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Borrow {
    private String broid;

    private String bid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT8")
    private Date borrowingTime;

    private String userid;

    public String getBroid() {
        return broid;
    }

    public void setBroid(String broid) {
        this.broid = broid == null ? null : broid.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public Date getBorrowingTime() {
        return borrowingTime;
    }

    public void setBorrowingTime(Date borrowingTime) {
        this.borrowingTime = borrowingTime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }
}