package com.hry.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {

    private String bid;
    private Integer rid;
    private Integer did;
    private Integer cid;
    private String begintime;
    private String endtime;
    private Integer type;
    private Double totalprice;
    private Integer bpassengerstate;
    private Integer bdriverstate;
    private Integer bstate;
    private Route route;
    private Driver driver;
    private Car car;
    private Bookdetail bookdetail;
    private List<Bookdetail1> bookdetail1;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getBpassengerstate() {
        return bpassengerstate;
    }

    public void setBpassengerstate(Integer bpassengerstate) {
        this.bpassengerstate = bpassengerstate;
    }

    public Integer getBdriverstate() {
        return bdriverstate;
    }

    public void setBdriverstate(Integer bdriverstate) {
        this.bdriverstate = bdriverstate;
    }

    public Integer getBstate() {
        return bstate;
    }

    public void setBstate(Integer bstate) {
        this.bstate = bstate;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Bookdetail getBookdetail() {
        return bookdetail;
    }

    public void setBookdetail(Bookdetail bookdetail) {
        this.bookdetail = bookdetail;
    }

    public List<Bookdetail1> getBookdetail1() {
        return bookdetail1;
    }

    public void setBookdetail1(List<Bookdetail1> bookdetail1) {
        this.bookdetail1 = bookdetail1;
    }

    public Book(String bid, Integer rid, Integer did, Integer cid, String begintime, String endtime, Integer type, Double totalprice, Integer bpassengerstate, Integer bdriverstate, Integer bstate, Route route, Driver driver, Car car, Bookdetail bookdetail, List<Bookdetail1> bookdetail1) {
        this.bid = bid;
        this.rid = rid;
        this.did = did;
        this.cid = cid;
        this.begintime = begintime;
        this.endtime = endtime;
        this.type = type;
        this.totalprice = totalprice;
        this.bpassengerstate = bpassengerstate;
        this.bdriverstate = bdriverstate;
        this.bstate = bstate;
        this.route = route;
        this.driver = driver;
        this.car = car;
        this.bookdetail = bookdetail;
        this.bookdetail1 = bookdetail1;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "bid='" + bid + '\'' +
                ", rid=" + rid +
                ", did=" + did +
                ", cid=" + cid +
                ", begintime='" + begintime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", type=" + type +
                ", totalprice=" + totalprice +
                ", bpassengerstate=" + bpassengerstate +
                ", bdriverstate=" + bdriverstate +
                ", bstate=" + bstate +
                ", route=" + route +
                ", driver=" + driver +
                ", car=" + car +
                ", bookdetail=" + bookdetail +
                ", bookdetail1=" + bookdetail1 +
                '}';
    }
}
