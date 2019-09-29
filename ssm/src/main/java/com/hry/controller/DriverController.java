package com.hry.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hry.domain.Book;
import com.hry.domain.Car;
import com.hry.domain.Driver;
import com.hry.service.BookService;
import com.hry.service.DriverService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DriverController {

    //@Autowired
    @Resource
    private DriverService driverService;
    @Resource
    private BookService bookService;

    //设计Map集合存储需要给页面的对象数据
    private Map<String,Object> result = new HashMap<>();

    /**
     * 司机注册
     * 分两步，第一步完成个人认证
     * @param driver
     * @return
     */
    @RequestMapping("/driverRegister")
    public String driverRegister(Driver driver,HttpSession session){
        //注册+个人认证
        driverService.regist(driver);
        //转到
        String did = driverService.findIdByPhone4RigistCar(driver.getDphone());
        session.setAttribute("did",did);
        return "driver_rigistCar";
    }

    /**
     * 司机注册
     * 第二部，绑定车辆
     * @param car
     * @param session
     * @return
     */
    @RequestMapping("/driverRigistCar")
    public String driverRigistCar(Car car,HttpSession session){
        System.out.println(car);
        car.setCstate(1); //等待审核
        driverService.saveCar(car);
        return "driver_succ";
    }

    /**
     * 返回司机首页
     * @return
     */
    @RequestMapping("/driverIndex")
    public String driverIndex(){
        return "driver_index";
    }

    /**
     * 司机登录
     * @param dphone
     * @param dpassword
     * @param session
     * @return
     */
    @RequestMapping("/driverLogin")
    public String findDriverByDphone(String dphone, String dpassword, HttpSession session){
        try {
            Driver driver = driverService.check(dphone, dpassword);
            Book book = bookService.findBookByDidAndBstate(driver.getDid(),0);
            session.setAttribute("book",book);
            session.setAttribute("driver",driver);
            return "redirect:driverPublishList";
        } catch (Exception e) {
            session.setAttribute("message",e.getMessage());
            return "driver_login";
        }
    }

    //进入审核图片和下载界面
    @RequestMapping("/driverApprove")
    public String driverApprove(){
        return "driverImg";
    }

    /**
     * 图片下载 ---驾驶证下载
     * @param ddriverlicense
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/driverDownImg")
    public String driverDownImg(String ddriverlicense, HttpServletResponse response) throws Exception {
        //设置好路径
        String path = "D:\\uploads"+ddriverlicense;
        //将文件读取到输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //设置文件的转码
        ddriverlicense = URLEncoder.encode(ddriverlicense,"UTF-8");
        //解决中文显示乱码
        response.addHeader("Content-Disposition","attachment;filename=" + ddriverlicense);
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
        return "driverImg";
    }

   /* @RequestMapping("/carDownImg")
    public String carDownImg(String ccarlicense, HttpServletResponse response) throws Exception {
        //设置好路径
        String path = "D:\\uploads"+ccarlicense;
        //将文件读取到输入流
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(path)));
        //设置文件的转码
        ccarlicense = URLEncoder.encode(ccarlicense,"UTF-8");
        //解决中文显示乱码
        response.addHeader("Content-Disposition","attachment;filename=" + ccarlicense);
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
        return "driverImg";
    }*/

    /**
     * 检查手机是否与数据库中存储的匹配
     * @param dphone
     * @return
     */
    @RequestMapping("/checkDphone")
    @ResponseBody
    public Map<String,Object> checkDphone(String dphone){
        boolean b = driverService.checkDphone(dphone);
        System.out.println(b);
        result.put("success",b);
        return result;
    }

    /**
     * 检查用户输入的当前密码是否与数据库中的密码匹配
     * @param dpassword
     * @param session
     * @return
     */
    @RequestMapping("/checkDpassword")
    @ResponseBody
    public Map<String,Object> checkDpassword(String dpassword , HttpSession session){
        Driver driver = (Driver) session.getAttribute("driver");
        Boolean b = true;
        if (driver.getDpassword().equals(dpassword)){
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
    @RequestMapping("/driverSpace")
    public String passengerSpace(){
        return "driver_space";
    }

    /**
     * 重新绑定手机
     * @param driver
     * @param session
     * @return
     */
    @RequestMapping("/modifyDphone")
    public String modifyUphone(Driver driver,HttpSession session){
        Integer did = driver.getDid();
        //由于密码已经使用Validate验证过了，不在数据库重新查询了
        String dphone = driver.getDphone();
        driverService.updateDphone(did,dphone);
        SetUserSession(did,session);
        //user = userService.findById(uid);
        //session.setAttribute("user",user);
        return "redirect:passengerSpace";
    }

    /**
     * 修改密码
     * @param driver
     * @param newdpassword
     * @param session
     * @return
     */
    @RequestMapping("modifyDpassword")
    public String modifyUpassword(Driver driver,String newdpassword,HttpSession session){
        Integer did = driver.getDid();
        driverService.updateDpassword(did,newdpassword);
        return "relogin";
    }

    //重置
    private void SetUserSession(Integer did,HttpSession session){
        Driver driver = driverService.findById(did);
        session.setAttribute("driver",driver);
    }


    /*————————————————————————————后台管理部分——————————————————————————————————————*/



    /**
     * 分页查询
     * 前端：datagrid分页功能需要添加pagination属性
     * 后端：接收page和rows参数，根据参数分页查询，获取结果，返回给页面
     * total:总记录数
     * @param page 当前页码
     * @param rows 每页显示记录数/数据
     * @return
     */
    @RequestMapping("/listDriverByPage")
    @ResponseBody //json
    public Map<String,Object> listByPage(Integer page,Integer rows){
        //设置分页参数
        PageHelper.startPage(page,rows);
        //查询所有数据
        List<Driver> list = driverService.findAll();
        //使用PageInfo封装查询结果
        PageInfo<Driver> pageInfo = new PageInfo<Driver>(list);
        //从PageInfo对象取出查询结果
        //总记录数
        long total = pageInfo.getTotal();
        //当前页数数据
        List<Driver> curList = pageInfo.getList();
        result.put("total",total);
        result.put("rows",curList);
        return result;
    }

    /**
     * 查询所有待审核列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/driverPendingList")
    @ResponseBody //json
    public Map<String,Object> driverPendingList(Integer page,Integer rows){
        //设置分页参数
        PageHelper.startPage(page,rows);
        //查询所有数据
        List<Driver> list = driverService.findPending();
        //使用PageInfo封装查询结果
        PageInfo<Driver> pageInfo = new PageInfo<Driver>(list);
        //从PageInfo对象取出查询结果
        //总记录数
        long total = pageInfo.getTotal();
        //当前页数数据
        List<Driver> curList = pageInfo.getList();
        result.put("total",total);
        result.put("rows",curList);

        return result;
    }

    /**
     * 保存修改或者新增
     * @param driver
     * @return
     */
    @RequestMapping("/saveDriver")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> save(Driver driver){
        try {
            driverService.save(driver);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 查询所有数据，给页面返回一个json格式数据
     * easyUI的datagrid组件需要展示数据，要提供json数据：[{id:1,name:xx},{id:2,name:xx}]
     * @return
     */
    @RequestMapping("/listDriver")
    @ResponseBody       //用于转换对象为json
    public List<Driver> list(){
        //查询数据
        return driverService.findAll();
    }

    /**
     * 根据id查找，进行更新操作
     * @param did
     * @return
     */
    @RequestMapping("/findDriverById")
    @ResponseBody       //用于转换对象为json
    public Driver findById(Integer did){
        return driverService.findById(did);
    }

    /**
     * 批量删除
     * @param did
     * @return
     */
    @RequestMapping("/deleteDriver")
    @ResponseBody
    public Map<String,Object> delete(Integer[] did){
        try {
            driverService.delete(did);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 验证
     * @param did
     */
    @RequestMapping("/verifyDriver")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> verify(Integer[] did){
        try {
            driverService.verify(did);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 驳回
     * @param did
     */
    @RequestMapping("/rejectDriver")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> reject(Integer[] did){
        try {
            driverService.reject(did);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 查询所有待审用户
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/carPendingList")
    @ResponseBody //json
    public Map<String,Object> carPendingList(Integer page,Integer rows){
        //设置分页参数
        PageHelper.startPage(page,rows);
        //查询所有数据
        List<Car> list = driverService.findCarPending();
        //使用PageInfo封装查询结果
        PageInfo<Car> pageInfo = new PageInfo<>(list);
        //从PageInfo对象取出查询结果
        //总记录数
        long total = pageInfo.getTotal();
        //当前页数数据
        List<Car> curList = pageInfo.getList();
        result.put("total",total);
        result.put("rows",curList);

        return result;
    }

    /**
     * 验证
     * @param did
     */
    @RequestMapping("/verifyCar")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> verifyCar(Integer[] cid){
        try {
            driverService.verifyCar(cid);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 驳回
     * @param cid
     */
    @RequestMapping("/rejectCar")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> rejectCar(Integer[] cid){
        try {
            driverService.rejectCar(cid);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }
}
