package com.hry.dao;

import com.hry.bean.PublishBean;
import com.hry.domain.Book;
import com.hry.domain.Bookdetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface BookDao {

    //保存个人订单信息
    @Insert("INSERT INTO bookdetail(bid,uid,bdseat,bdprice,bdstate) VALUES(#{bid},#{uid},#{bdseat},#{bdprice},#{bdstate})")
    public void saveDetail(Bookdetail bookdetail);

    //保存当前订单
    @Insert("INSERT INTO book VALUES(#{bid},#{did},#{rid},#{cid},#{begintime},#{endtime},#{type},#{totalprice},#{bpassengerstate},#{bdriverstate},#{bstate})")
    public void save(Book book);

    //查询所有用户实时拼车  乘客加入时
    @Select("SELECT * FROM book WHERE type=1 AND bpassengerstate=0 AND bstate=0")   //0表示可用
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "did", property = "driver", one = @One(select = "com.hry.dao.DriverDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "rid", property = "route", one = @One(select = "com.hry.dao.RouteDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "car.cid", property = "car.cid"),
            @Result(column = "bid", property = "bookdetail", one = @One(select = "com.hry.dao.BookDao.findBookdetailByBid", fetchType = FetchType.EAGER)),
            @Result(column = "begintime", property = "begintime"),
            @Result(column = "endtime", property = "endtime"),
            @Result(column = "type", property = "type"),
            @Result(column = "totalprice", property = "totalprice"),
            @Result(column = "bpassengerstate", property = "bpassengerstate"),
            @Result(column = "bdriverstate", property = "bdriverstate")
    })
    public List<Book> findAllPassengerPublish();

    //查询所有用户预定拼车 乘客加入时
    @Select("SELECT * FROM book WHERE type=2 AND bpassengerstate=0 AND bstate=0")   //0表示可用
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "did", property = "driver", one = @One(select = "com.hry.dao.DriverDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "rid", property = "route", one = @One(select = "com.hry.dao.RouteDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "car.cid", property = "car.cid"),
            @Result(column = "bid", property = "bookdetail", one = @One(select = "com.hry.dao.BookDao.findBookdetailByBid", fetchType = FetchType.EAGER)),
            @Result(column = "begintime", property = "begintime"),
            @Result(column = "endtime", property = "endtime"),
            @Result(column = "type", property = "type"),
            @Result(column = "totalprice", property = "totalprice"),
            @Result(column = "bpassengerstate", property = "bpassengerstate"),
            @Result(column = "bdriverstate", property = "bdriverstate")
    })
    public List<Book> findAllPasssengerBook();

    //根据bid查询详细订单中的一个信息
    //此查询仅用于订单未生成之前，否则会报错
    //@Select("Select * FROM bookdetail WHERE bid=#{bid}")//查询所有用户实时拼车  司机
    @Select("SELECT * FROM bookdetail WHERE bid=#{bid}")   //0表示可用
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "bdid", property = "bdid"),
            @Result(column = "bid", property = "bid"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "uid", property = "user", one = @One(select = "com.hry.dao.UserDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "bdseat", property = "bdseat"),
            @Result(column = "bdprice", property = "bdprice"),
            @Result(column = "bdstate", property = "bdstate")
    })
    public Bookdetail findBookdetailByBid(String bid);


    @Select("SELECT * FROM bookdetail WHERE uid=#{uid} AND bdstate=#{bdstate}")
    public Bookdetail findBookdetailByUidAndBdstate(@Param("uid") Integer uid, @Param("bdstate") Integer bdstate);

    //查询所有
    @Select("SELECT * FROM book WHERE bid=#{bid}")
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "did", property = "did"),
            @Result(column = "rid", property = "rid"),
            @Result(column = "did", property = "driver", one = @One(select = "com.hry.dao.DriverDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "rid", property = "route", one = @One(select = "com.hry.dao.RouteDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "cid", property = "car", one = @One(select = "com.hry.dao.CarDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "cid", property = "cid"),
            @Result(column = "bid", property = "bookdetail1", many = @Many(select = "com.hry.dao.BookDao.findBookdetailByBid", fetchType = FetchType.LAZY)),
            @Result(column = "begintime", property = "begintime"),
            @Result(column = "endtime", property = "endtime"),
            @Result(column = "type", property = "type"),
            @Result(column = "bstate", property = "bstate"),
            @Result(column = "totalprice", property = "totalprice"),
            @Result(column = "bpassengerstate", property = "bpassengerstate"),
            @Result(column = "bdriverstate", property = "bdriverstate")
    })
    public Book findBookById(String bid);


    //查询所有用户实时拼车  司机
    @Select("SELECT * FROM book WHERE type=1 AND bdriverstate=0 AND bstate=0")   //0表示可用
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "did", property = "driver", one = @One(select = "com.hry.dao.DriverDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "rid", property = "route", one = @One(select = "com.hry.dao.RouteDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "car.cid", property = "car.cid"),
            @Result(column = "did", property = "did"),
            @Result(column = "rid", property = "rid"),
            @Result(column = "bid", property = "bookdetail1", many = @Many(select = "com.hry.dao.BookDao.findBookdetailByBid", fetchType = FetchType.LAZY)),
            @Result(column = "begintime", property = "begintime"),
            @Result(column = "endtime", property = "endtime"),
            @Result(column = "type", property = "type"),
            @Result(column = "totalprice", property = "totalprice"),
            @Result(column = "bpassengerstate", property = "bpassengerstate"),
            @Result(column = "bdriverstate", property = "bdriverstate"),
            @Result(column = "bstate", property = "bstate")
    })
    public List<Book> findAllDriverPublish();

    //查询所有司机预定拼车
    @Select("SELECT * FROM book WHERE type=2 AND bdriverstate=0 AND bstate=0")   //0表示可用
    @Results({
            @Result(id = true, column = "bid", property = "bid"),
            @Result(column = "did", property = "driver", one = @One(select = "com.hry.dao.DriverDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "rid", property = "route", one = @One(select = "com.hry.dao.RouteDao.findById", fetchType = FetchType.EAGER)),
            @Result(column = "car.cid", property = "car.cid"),
            @Result(column = "bid", property = "bookdetail1", many = @Many(select = "com.hry.dao.BookDao.findBookdetailByBid", fetchType = FetchType.LAZY)),
            @Result(column = "begintime", property = "begintime"),
            @Result(column = "endtime", property = "endtime"),
            @Result(column = "type", property = "type"),
            @Result(column = "totalprice", property = "totalprice"),
            @Result(column = "bpassengerstate", property = "bpassengerstate"),
            @Result(column = "bdriverstate", property = "bdriverstate"),
            @Result(column = "bdriverstate", property = "bdriverstate")
    })
    public List<Book> findAllDriverBook();

    @Select("SELECT * FROM book WHERE did=#{did} AND bstate=#{bstate}")
    public Book findBookByDidAndBstate(@Param("did") Integer did, @Param("bstate") Integer bstate);

    //删除退出详细订单
    @Delete("DELETE FROM bookdetail WHERE bid=#{bid} AND uid=#{uid}")
    public void deleteBookdetail(@Param("bid") String bid, @Param("uid") String uid);

    //删除订单
    @Delete("DELETE FROM book WHERE bid=#{bid}")
    public void deleteBook(String bid);

    //更新详细订单状态
    @Update("UPDATE bookdetail SET bdstate=#{bdstate} WHERE bid=#{bid} AND uid=#{uid}")
    public void updateBookdetailStateByBidAndUid(@Param("bid") String bid, @Param("uid") String uid, @Param("bdstate") Integer bdstate);

    //订单已完成
    @Update("UPDATE book SET bstate = 2")
    public void updateBookState2Finish(String bid);

    //查询乘客所有订单
    @Select("SELECT * FROM bookdetail WHERE uid=#{uid} AND bdstate=2")
    @Results({
            @Result(id = true, column = "bdid", property = "bdid"),
            @Result(column = "bid", property = "bid"),
            @Result(column = "bid", property = "book", one = @One(select = "com.hry.dao.BookDao.findBookById", fetchType = FetchType.EAGER)),
            @Result(column = "uid", property = "uid"),
            @Result(column = "bdseat", property = "bdseat"),
            @Result(column = "bdstate", property = "bdstate")
    })
    public List<Bookdetail> findAllBookdetail(Integer uid);

    @Update("UPDATE book SET bpassengerstate=#{bpassengerstate} WHERE bid=#{bid}")
    public void updateBookpassengerState(@Param("bpassengerstate") Integer bpassengerstate,@Param("bid") String bid);

    @Update("UPDATE book SET bstate=#{bstate} WHERE bid=#{bid}")
    public void updateBstate(Integer bstate, String bid);
}