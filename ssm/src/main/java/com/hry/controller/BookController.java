package com.hry.controller;

import com.github.pagehelper.PageInfo;
import com.hry.algorithm.Cost;
import com.hry.domain.*;
import com.hry.service.BookService;
import com.hry.service.DriverService;
import com.hry.service.RouteService;
import com.hry.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BookController {

    @Resource
    private BookService bookService;
    @Resource
    private RouteService routeService;
    @Resource
    private DriverService driverService;



    /*乘客部分*/


    /**
     * 接收拼车订单参数 添加到数据库
     * @param book
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("/passengerPublishBook")
    public String passengerPublishBook(Book book , HttpSession session , String id){
        User user = (User) session.getAttribute("user");
        if (id.equals("1")){
            book.setType(1);  //实时
        }else {
            book.setType(2);  //预定
            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(book.getBegintime());*/
            book.setBegintime(book.getBegintime());
        }

        //查询路线并给book 设置route
        String rdeparture = book.getRoute().getRdeparture();
        String rdestination = book.getRoute().getRdestination();
        Route route = routeService.findByLocation(rdeparture, rdestination);
        book.setRoute(route);
        book.setRid(route.getRid());
        book.setBstate(0);//可查询订单
        // 给司机乘客设为可乘坐   0表示可加入
        book.setBpassengerstate(0);
        book.setBdriverstate(0);

        //book.setTotalprice(Cost.totalPrice(route.getRdistance())); 计算价格

        //生成唯一的订单id
        String uuid = UUID.randomUUID().toString().replace("-", "");
        book.setBid(uuid);
        //增加订单
        bookService.save(book);
        System.out.println(book);
        //book表插入后进行设置
        book.getBookdetail().setBid(book.getBid());  //设置bid
        book.getBookdetail().setUid(user.getUid());  //设置uid
        book.getBookdetail().setUser(user);

        book.getBookdetail().setBdstate(0); //0表示加入拼车 但未确认出发
        System.out.println(book);
        //增加发布订单详细
        bookService.saveDetail(book.getBookdetail());
        session.setAttribute("book",book);
        return "redirect:passengerCarpooling";
    }

    /**
     * 我的拼车--乘客
     * @return
     */
    @RequestMapping("passengerCarpooling")
    public String passengerCarpooling(HttpSession session){
        User user = (User) session.getAttribute("user");
        Book book = (Book) session.getAttribute("book");

        if (book == null){
            Bookdetail bookdetail = bookService.findBookdetailByUidAndBdstate(user.getUid(), 0);
            if (bookdetail!=null){
                book = bookService.findBookById(bookdetail.getBid());
                session.setAttribute("book",book);
            }
            session.setAttribute("bookdetail",bookdetail);
        }else{}
        System.out.println("book:"+book);
        return "passengerCarpooling";
    }



    /**
     * 分页查询实时拼车列表
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/passengerPublishList")
    public String passengerPublishList(HttpSession session , @RequestParam(defaultValue = "1")Integer page , @RequestParam(defaultValue = "1")Integer rows){

        //加载实时拼车信息
        List<Book> publishList = bookService.findAllPassengerPublish(page, rows);
        PageInfo<Book> allPublishPage = new PageInfo<>(publishList);
        for (Book book : publishList) {
            System.out.println(book);
        }
        session.setAttribute("passengerPublishList",publishList);
        session.setAttribute("allPassengerPublishPage",allPublishPage);

        //回首页
        return "redirect:passengerIndex";
    }

    /**
     * 分页查询预定拼车列表
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/passengerBookList")
    public String passengerBookList(HttpSession session , @RequestParam(defaultValue = "1")Integer page , @RequestParam(defaultValue = "1")Integer rows){
        System.out.println(page);
        System.out.println(rows);

        //加载预定拼车信息
        List<Book> bookList = bookService.findAllPasssengerBook(page, rows);
        PageInfo<Book> allBookPage = new PageInfo<>(bookList);

        session.setAttribute("passengerPublishList",bookList);
        session.setAttribute("allPassengerPublishPage",allBookPage);
        return "redirect:passengerBookIndex";
    }

    /**
     * 跳转到发布界面
     * @return
     */
    @RequestMapping("/passengerPublishUI")
    public String passengerPublishUI(){
        return "passenger_publishType";
    }

    /**
     * 发布类型页面根据用户选择的类型进入实时拼车或者预定拼车
     * @param id
     * @return
     */
    @RequestMapping("/passengerPublishTypeUI")
    public String passengerPublishTypeUI(String id,HttpSession session){
        List<String> depa = routeService.findAllRdeparture();
        session.setAttribute("depa",depa);

        if (id.equals("1")){  //如果传来的id=1 ，进入实时拼车
            return "passenger_publish";
        }else {     //id=2，进入拼车预定

            return "passenger_book";
        }
    }



    /**
     * 乘客加入乘客的拼车
     * @param bookdetail
     * @return
     */
    @RequestMapping("/passengerAddPublish")
    public String passengerAddPublish(Bookdetail bookdetail,HttpSession session){
        Book book = (Book) session.getAttribute("book");
        bookdetail.setBdstate(0);  //0表示已加入拼车但没有确认出发
        bookService.saveDetail(bookdetail);


        /*此处可能会出现问题*/
        if (book == null){
            bookdetail = bookService.findBookdetailByUidAndBdstate(bookdetail.getUid(), 0);
            if (bookdetail!=null){
                book = bookService.findBookById(bookdetail.getBid());
                book.setBpassengerstate(1);//乘客已满     //如果司机的写完了 应该加上判断 之前有没有用户加入 如果没有不能设置为1 这步跳过
                session.setAttribute("book",book);
            }
        }else{}
        session.setAttribute("bookdetail",bookdetail);
        System.out.println("book:"+book);
        return "passengerCarpooling";
    }

    /**
     * 用户退出拼车
     */
    @RequestMapping("/passengerQuitPublish")
    public String passengerQuitPublish(HttpSession session,String bid,String uid){
        bookService.deleteBookdetail(bid,uid);
        Bookdetail bookdetail = bookService.findBookdetailByBid(bid);
        Book book = bookService.findBookById(bid);
        bookService.updateBookpassengerState(0,bid);//有一个人 退出更新订单的 乘客state为0  可用状态
        session.setAttribute("book",book);//更新session
        if (book.getDid()==null&&bookdetail==null){  //司机和其他用户都不在 ，删该订单
            bookService.deleteBook(bid); //删除该订单
            session.removeAttribute("book"); //移除session
        }
        //重定向到首页
        return "redirect:passengerIndex";
    }

    /**
     * 用户确认出发
     */
    @RequestMapping("/passengerConfirm")
    public String passengerConfirm(HttpSession session,String bid,String uid){
        bookService.updateBookdetailStateByBidAndUid(bid,uid,1);  //1为已出发
        //bookService.updateBstate(1,bid);
        Book book = bookService.findBookById(bid);
        session.setAttribute("book",book);//更新session
        //重定向到首页
        return "passengerGo";
    }
    /**
     * 用户确认订单完成
     */
    @RequestMapping("/passengerComment")
    public String passengerComment(HttpSession session,String bid,String uid,Driver driver){
        bookService.updateBookdetailStateByBidAndUid(bid,uid,2);  //2为已完成
        Driver driver1 = driverService.findById(driver.getDid());
        Double devaluate1 = driver1.getDevaluate();
        Double devaluate = driver.getDevaluate();
        if (devaluate1 == null){
            devaluate1 = 0.0;
        }else {
            devaluate = (devaluate1+devaluate)/2.0;
        }


        //重定向到首页
        return "redirect:passengerIndex";
    }



    /**
     * 转到预定拼车首页
     * @return
     */
    @RequestMapping("/passengerBookIndex")
    public String passengerAddPublish(){
        return "passenger_book_index";
    }


/*-----------------------------司机部分-----------------------------*/
    /**
     * 发布拼车
     * 接收拼车订单参数 添加到数据库
     * @param book
     * @return
     */
    @RequestMapping("/driverPublishBook")
    public String driverPublishBook(Book book , HttpSession session , String id){
        Driver driver = (Driver) session.getAttribute("driver");


        Car car = driverService.findCarById(driver.getDid());
        book.setCid(car.getCid());
        book.setCar(car);

        if (id.equals("1")){
            book.setType(1); //实时拼车
        }else {
            book.setType(2); //预定拼车
           /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(new Date());*/
            book.setBegintime(book.getBegintime());
        }

        String rdeparture = book.getRoute().getRdeparture();
        String rdestination = book.getRoute().getRdestination();
        Route route = routeService.findByLocation(rdeparture, rdestination);
        book.setRid(route.getRid());
        book.setRoute(route);

        book.setBstate(0);//可查询订单
        book.setBpassengerstate(0);//乘客可加入
        book.setBdriverstate(1); //1表示司机不可加入
        //book.setTotalprice(Cost.totalPrice(route.getRdistance()));

        //生成唯一的订单id
        String uuid = UUID.randomUUID().toString().replace("-", "");
        book.setBid(uuid);
        book.setDid(driver.getDid());
        book.setDriver(driver);
        //增加订单
        bookService.save(book);
        session.setAttribute("book",book);
        //book表插入后进行设置
        //book.getBookdetail().setBid(book.getBid());
        //book.getBookdetail().setDid(user.getDid());
        //增加发布订单详细
        //bookService.saveDetail(book.getBookdetail());
        return "redirect:driverCarpooling";
    }

    /**
     * 我的拼车--司机
     * @return
     */
    @RequestMapping("driverCarpooling")
    public String driverCarpooling(HttpSession session){
        Driver driver = (Driver) session.getAttribute("driver");
        Book book = (Book) session.getAttribute("book");

        /*此处可能会出现问题*/
        if (book == null){
            book = bookService.findBookByDidAndBstate(driver.getDid(),0);
            session.setAttribute("book",book);

        }else{}
        System.out.println("book:"+book);
        return "driverCarpooling";
    }


    /**
     * 分页查询实时拼车列表
     * 加载实时拼车信息，然后重定向回首页
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/driverPublishList")
    public String driverPublishList(HttpSession session , @RequestParam(defaultValue = "1")Integer page , @RequestParam(defaultValue = "1")Integer rows){
        //加载实时拼车信息
        List<Book> publishList = bookService.findAllDriverPublish(page, rows);
        PageInfo<Book> allPublishPage = new PageInfo<>(publishList);
        for (Book book : publishList) {
            System.out.println(book);
        }
        session.setAttribute("driverPublishList",publishList);
        session.setAttribute("allDriverPublishPage",allPublishPage);

        //回首页
        return "redirect:driverIndex";
    }

    /**
     * 跳转到发布界面
     * @return
     */
    @RequestMapping("/driverPublishUI")
    public String driverPublishUI(){
        return "driver_publishType";
    }

    /**
     * 发布类型页面根据用户选择的类型进入实时拼车或者预定拼车
     * @param id
     * @return
     */
    @RequestMapping("/driverPublishTypeUI")
    public String driverPublishTypeUI(String id,HttpSession session){
        List<String> depa = routeService.findAllRdeparture();
        session.setAttribute("depa",depa);

        if (id.equals("1")){  //如果传来的id=1 ，进入实时拼车
            return "driver_publish";
        }else {     //id=2，进入拼车预定

            return "driver_book";
        }
    }

    /**
     * 分页查询预定拼车列表
     * @param session
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/driverBookList")
    public String driverBookList(HttpSession session , @RequestParam(defaultValue = "1")Integer page , @RequestParam(defaultValue = "1")Integer rows){

        //加载预定拼车信息
        List<Book> bookList = bookService.findAllPasssengerBook(page, rows);
        PageInfo<Book> allBookPage = new PageInfo<>(bookList);

        session.setAttribute("driverPublishList",bookList);
        session.setAttribute("allDriverPublishPage",allBookPage);
        return "redirect:driverBookIndex";
    }

    /**
     * 加入实时拼车
     * @param book
     * @param session
     * @return
     */
    @RequestMapping("driverAddPublish")
    public String driverAddPublish(Book book,HttpSession session){
        Integer did = book.getDid();
        //bookService.saveBookDidAndCidByBid();
        /*此处可能会出现问题*/
        if (book == null){
            book = bookService.findBookById(book.getBid());
            session.setAttribute("book",book);
        }else{}


        return "redirect:driverCarpooling";
    }

    /**
     * 转到预定拼车首页
     * @return
     */
    @RequestMapping("/driverBookIndex")
    public String driverBookIndex(){
        return "driver_book_index";
    }
}
