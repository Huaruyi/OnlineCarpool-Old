package com.hry.service;

import com.hry.domain.Book;
import com.hry.domain.Bookdetail;

import java.util.List;

public interface BookService {

    //保存个人订单信息
    public void saveDetail(Bookdetail bookdetail);

    //保存当前订单
    public void save(Book book);

    //查询所有订单
    public List<Book> findAllPassengerPublish(Integer page, Integer rows);

    public List<Book> findAllPasssengerBook(Integer page, Integer rows);

    public Bookdetail findBookdetailByUidAndBdstate(Integer uid, Integer bdstate);

    public Book findBookById(String bid);

    public Book findBookByDidAndBstate(Integer did, Integer bstate);

    public void deleteBookdetail(String bid, String uid);

    public Bookdetail findBookdetailByBid(String bid);

    public void deleteBook(String bid);

    public void updateBookdetailStateByBidAndUid(String bid, String uid, Integer bdstate);

    public void updateBookState2Finish(String bid);

    public List<Bookdetail> findAllBookdetail(Integer uid, Integer page, Integer rows);

    public void updateBookpassengerState(Integer bpassengerstate,String bid);

    public void updateBstate(Integer bstate, String bid);

    public List<Book> findAllDriverPublish(Integer page, Integer rows);
}
