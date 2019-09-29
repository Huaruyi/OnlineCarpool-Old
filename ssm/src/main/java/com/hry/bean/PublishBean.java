package com.hry.bean;

import com.hry.domain.*;

public class PublishBean {
    private Book book;
    private Route route;
    private Driver driver;
    private Car car;
    private Bookdetail bookdetail;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public PublishBean() {
    }

    public PublishBean(Book book, Route route, Driver driver, Car car, Bookdetail bookdetail) {
        this.book = book;
        this.route = route;
        this.driver = driver;
        this.car = car;
        this.bookdetail = bookdetail;
    }
}
