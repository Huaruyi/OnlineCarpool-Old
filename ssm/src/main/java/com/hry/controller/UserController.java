package com.hry.controller;

import com.github.pagehelper.PageInfo;
import com.hry.domain.Book;
import com.hry.domain.Bookdetail;
import com.hry.domain.User;
import com.hry.service.BookService;
import com.hry.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class UserController {
    //@Autowired
    @Resource
    private UserService userService;
    @Resource
    private BookService bookService;

    private Map<String,Object> result = new HashMap<>();

    @RequestMapping("/passengerApprove")
    public String passengerApprove(){
        return "passengerImg";
    }

    @RequestMapping("/passengerDownImg")
    public String passengerDownImg(String uidcard, HttpServletResponse response) throws Exception {
        //设置好路径
        String path = "D:\\uploads"+uidcard;
        //将文件读取到输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //设置文件的转码
        uidcard = URLEncoder.encode(uidcard,"UTF-8");
        //解决中文显示乱码
        response.addHeader("Content-Disposition","attachment;filename=" + uidcard);
        //设置相应类型
        response.setContentType("multipart/form-data");
        //将对应文件读取出来
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        int len = 0;
        while((len = bis.read()) != -1){
            out.write(len);
            out.flush();
        }
        out.close();
        return "passengerImg";
    }

    @RequestMapping("/Car")
    public String Car(){
        return "driver_rigistCar";
    }

    /**
     * 从外部跳转到index首页
     * @return
     */

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/forward")
    public String forward(HttpSession session , @RequestParam(defaultValue = "1")Integer page , @RequestParam(defaultValue = "1")Integer rows){
        //加载实时拼车信息
        List<Book> publishList = bookService.findAllPassengerPublish(page, rows);
        PageInfo<Book> allPublishPage = new PageInfo<>(publishList);
        for (Book book : publishList) {
            System.out.println(book);
        }
        session.setAttribute("passengerPublishList",publishList);
        session.setAttribute("allPassengerPublishPage",allPublishPage);
        return "redirect:index";
    }

    /**
     * 访客模式下的预定拼车
     * @return
     */
    @RequestMapping("/bookList")
    public String bookList(HttpSession session , @RequestParam(defaultValue = "1")Integer page , @RequestParam(defaultValue = "1")Integer rows){
        //加载预定拼车信息
        List<Book> bookList = bookService.findAllPasssengerBook(page, rows);
        PageInfo<Book> allBookPage = new PageInfo<>(bookList);

        session.setAttribute("passengerPublishList",bookList);
        session.setAttribute("allPassengerPublishPage",allBookPage);
        return "redirect:bookIndex";
    }

    /**
     *
     * 访客预定拼车首页
     * @return
     */
    @RequestMapping("bookIndex")
    public String bookIndex(){
        return "index_booklist";
    }


    /**
     * 返回乘客首页
     * @return
     */
    @RequestMapping("/passengerIndex")
    public String passengerIndex(){
        return "passenger_index";
    }

    /**
     * 跳转指定登录页面
     * @param id
     * @return
     */
    @RequestMapping("/loginUI")
    public String login(String id){
        if (id.equals("1")){
            return "passenger_login";
        }else {
            return "driver_login";
        }
    }

    /**
     * 乘客登录
     * 先重定向到publishList请求 来加载实时拼车信息 然后重定向到passengerIndex 首页
     * @param uphone
     * @param upassword
     * @param session
     * @param
     * @return
     */
    @RequestMapping("/passengerLogin")
    public String findUserByUPhone(String uphone, String upassword, HttpSession session){
        try {
            User user = userService.check(uphone, upassword);
            Bookdetail bookdetail = bookService.findBookdetailByUidAndBdstate(user.getUid(), 0);
            Book book = null;
            if (bookdetail != null){
                book = bookService.findBookById(bookdetail.getBid());
            }

            session.setAttribute("book",book);
            session.setAttribute("bookdetail",bookdetail);
            session.setAttribute("user",user);
            //重定向到publishList，获取实时拼车数据
            return  "redirect:passengerPublishList";
        } catch (Exception e) {
            //打印错误信息
            session.setAttribute("message",e.getMessage());
            //回到登录页面
            return "passenger_login";
        }
    }

    /**
     * 选择注册角色
     * @param id
     * @return
     */
    @RequestMapping("/registerUI")
    public String registerUI(String id){
        if (id.equals("1")){  //如果传来的id=1 ，进入乘客注册
            return "passenger_register";
        }else {     //id=2，进入司机注册
            return "driver_register";
        }
    }

    /**
     * 跳转到注册页面
     * @return
     */
    @RequestMapping("/registerTypeUI")
    public String registerType(){
        return "registerType";
    }

    /**
     * 选择登录角色
     * @return
     */
    @RequestMapping("/loginTypeUI")
    public String loginType(){
        return "loginType";
    }

    /**
     * 注册成功后 跳转到成功界面
     * @param user
     * @return
     */
    @RequestMapping("/passengerRegister")
    public String register(User user){
        userService.regist(user);
        return "passenger_succ";
    }

    /**
     * 注销登录
     * 清空session 并重定向回index主页面 （重定向会清除request和response中的数据）
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        //清空session
        request.getSession().invalidate();
        //重定向到首页
        return "redirect:index.jsp";
    }

    /**
     * 检查手机号是否已被注册
     * @param uphone
     * @return
     */
    @RequestMapping("/checkUphone")
    @ResponseBody
    public Map<String,Object> checkUphone(String uphone){
        boolean b = userService.checkUphone(uphone);
        System.out.println(b);
        result.put("success",b);
        return result;
    }


    /**
     * 检查密码是否正确
     * @param upassword
     * @param session
     * @return
     */
    @RequestMapping("/checkUpassword")
    @ResponseBody
    public Map<String,Object> checkUpassword(String upassword , HttpSession session){
        User user = (User) session.getAttribute("user");
        Boolean b = true;
        if (user.getUpassword().equals(upassword)){
            b = false;
        }
        System.out.println(b);
        result.put("success",b);
        return result;
    }

    /**
     * 个人中心
     * @return
     */
    @RequestMapping("/passengerSpace")
    public String passengerSpace(){
        return "passenger_space";
    }


    @RequestMapping("passengerOrderList")
    public String passengerOrderUI(HttpSession session , @RequestParam(defaultValue = "1")Integer page , @RequestParam(defaultValue = "1")Integer rows){
        User user = (User) session.getAttribute("user");
        List<Bookdetail> bookdetailList = bookService.findAllBookdetail(user.getUid(), page, rows);
        PageInfo<Bookdetail> bookdetailPageInfo = new PageInfo<>(bookdetailList);
        session.setAttribute("passengerBookdetailList",bookdetailList);
        session.setAttribute("passengerBookdetailPageInfo",bookdetailPageInfo);
        return "passengerOrder";
    }

    /**
     * 修改手机手机号
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/modifyUphone")
    public String modifyUphone(User user,HttpSession session){
        Integer uid = user.getUid();
        //由于密码已经使用Validate验证过了，不在数据库重新查询了
        String uphone = user.getUphone();
        userService.updateUphone(uid,uphone);
        SetUserSession(uid,session);
        //user = userService.findById(uid);
        //session.setAttribute("user",user);
        return "redirect:passengerSpace";
    }

    /**
     * 修改密码
     * @param user
     * @param newupassword
     * @param session
     * @return
     */
    @RequestMapping("modifyUpassword")
    public String modifyUpassword(User user,String newupassword,HttpSession session){
        Integer uid = user.getUid();
        userService.updateUpassword(uid,newupassword);
        return "relogin";
    }

    /**
     * 修改后重新设置session,以防止显示空数据
     * @param uid
     * @param session
     */
    private void SetUserSession(Integer uid,HttpSession session){
        User user = userService.findById(uid);
        session.setAttribute("user",user);
    }









}
