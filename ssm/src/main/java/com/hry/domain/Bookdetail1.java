package com.hry.domain;

import java.io.Serializable;

public class Bookdetail1 implements Serializable {
    private Integer bdid;
    private String bid;
    private Integer uid;
    private Integer bdseat;
    private Double bdprice;
    private Integer bdstate;
    private Book book;
    private User user;

    public Integer getBdid() {
        return bdid;
    }

    public void setBdid(Integer bdid) {
        this.bdid = bdid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBdseat() {
        return bdseat;
    }

    public void setBdseat(Integer bdseat) {
        this.bdseat = bdseat;
    }

    public Double getBdprice() {
        return bdprice;
    }

    public void setBdprice(Double bdprice) {
        this.bdprice = bdprice;
    }

    public Integer getBdstate() {
        return bdstate;
    }

    public void setBdstate(Integer bdstate) {
        this.bdstate = bdstate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bookdetail1(Integer bdid, String bid, Integer uid, Integer bdseat, Double bdprice, Integer bdstate, Book book, User user) {
        this.bdid = bdid;
        this.bid = bid;
        this.uid = uid;
        this.bdseat = bdseat;
        this.bdprice = bdprice;
        this.bdstate = bdstate;
        this.book = book;
        this.user = user;
    }

    public Bookdetail1() {
    }

    @Override
    public String toString() {
        return "Bookdetail{" +
                "bdid=" + bdid +
                ", bid='" + bid + '\'' +
                ", uid=" + uid +
                ", bdseat=" + bdseat +
                ", bdprice=" + bdprice +
                ", bdstate=" + bdstate +
                ", book=" + book +
                ", user=" + user +
                '}';
    }
}
