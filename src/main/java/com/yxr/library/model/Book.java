package com.yxr.library.model;

import java.math.BigDecimal;

public class Book {
    private String bid;

    private String bname;

    private String introduce;

    private String author;

    private BigDecimal count;

    private String broid;

    private String photo;

    private BigDecimal clickCount;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname == null ? null : bname.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getBroid() {
        return broid;
    }

    public void setBroid(String broid) {
        this.broid = broid == null ? null : broid.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public BigDecimal getClickCount() {
        return clickCount;
    }

    public void setClickCount(BigDecimal clickCount) {
        this.clickCount = clickCount;
    }
}