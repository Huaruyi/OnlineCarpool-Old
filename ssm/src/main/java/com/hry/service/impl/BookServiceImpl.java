package com.hry.service.impl;

import com.github.pagehelper.PageHelper;
import com.hry.dao.BookDao;
import com.hry.domain.Book;
import com.hry.domain.Bookdetail;
import com.hry.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Resource
    private BookDao bookDao;

    //保存个人订单信息
    @Override
    public void saveDetail(Bookdetail bookdetail){
        bookDao.saveDetail(bookdetail);
    }

    //保存当前订单
    @Override
    public void save(Book book) {
        bookDao.save(book);
    }



    //查询所有订单
    @Override
    public List<Book> findAllPassengerPublish(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return bookDao.findAllPassengerPublish();
    }



    @Override
    public List<Book> findAllPasssengerBook(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return bookDao.findAllPasssengerBook();

    }

    @Override
    public Bookdetail findBookdetailByUidAndBdstate(Integer uid, Integer bdstate) {
        return bookDao.findBookdetailByUidAndBdstate(uid,bdstate);
    }

    @Override
    public Book findBookById(String bid) {
        return bookDao.findBookById(bid);
    }

    @Override
    public Book findBookByDidAndBstate(Integer did, Integer bstate) {
        return bookDao.findBookByDidAndBstate(did, bstate);
    }

    @Override
    public void deleteBookdetail(String bid, String uid) {
         bookDao.deleteBookdetail(bid,uid);
    }

    @Override
    public Bookdetail findBookdetailByBid(String bid){
        return bookDao.findBookdetailByBid(bid);
    }

    //取消订单
    @Override
    public void deleteBook(String bid) {
        bookDao.deleteBook(bid);
    }

    //更新详细订单状态
    @Override
    public void updateBookdetailStateByBidAndUid(String bid, String uid, Integer bdstate) {
        bookDao.updateBookdetailStateByBidAndUid(bid,uid,bdstate);
    }

    //设置订单为完成状态
    @Override
    public void updateBookState2Finish(String bid) {
        bookDao.updateBookState2Finish(bid);
    }


    //查询乘客的所有订单
    @Override
    public List<Bookdetail> findAllBookdetail(Integer uid, Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return bookDao.findAllBookdetail(uid);
    }

    @Override
    public void updateBookpassengerState(Integer bpassengerstate,String bid) {
        bookDao.updateBookpassengerState(bpassengerstate,bid);
    }

    @Override
    public void updateBstate(Integer bstate, String bid) {
        bookDao.updateBstate(bstate,bid);
    }

    @Override
    public List<Book> findAllDriverPublish(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        return bookDao.findAllDriverPublish();
    }
}
