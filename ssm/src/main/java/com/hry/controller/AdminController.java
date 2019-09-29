package com.hry.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hry.domain.Admin;
import com.hry.domain.User;
import com.hry.service.AdminService;
import com.hry.service.BookService;
import com.hry.service.RouteService;
import com.hry.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class AdminController {
    @Resource
    private AdminService adminService;
    @Resource
    private UserService userService;
    @Resource
    private RouteService routeService;
    @Resource
    private BookService bookService;

    @RequestMapping("adminForward")
    public String adminForward(){
        return "/admin/admin_login.jsp";
    }

    @RequestMapping("adminIndex")
    public String adminIndex(){
        return "forward:admin/admin_index.jsp";
    }

    @RequestMapping("adminVerifyIndex")
    public String adminVerifyIndex(){
        return "admin_index1";
    }

    @RequestMapping("adminRouteIndex")
    public String adminRouteIndex(){
        return "admin_index2";
    }
    @RequestMapping("adminEvalutionIndex")
    public String adminEvalutionIndex(){
        return "admin_index3";
    }


    @RequestMapping("/adminLogin")
    public String login(Admin admin, HttpSession session){
        Integer arole = admin.getArole();
        String aname = admin.getAname();
        String apassword = admin.getApassword();
        if (arole == 0){
            if (aname.equals("root")&&apassword.equals("root")){
                session.setAttribute("root",admin);
                return "redirect:adminIndex";
            }else {
                session.setAttribute("message","用户名或密码错");
                return "/admin/admin_login.jsp";
            }
        }else {

            try {
                Admin admin1 = adminService.check(aname, apassword, arole);
                if (arole == 1){
                    session.setAttribute("admin1",admin1);
                    return "redirect:adminVerifyIndex";
                }else if (arole == 2){
                    session.setAttribute("admin2",admin1);
                    return "redirect:adminRouteIndex";
                }else if (arole == 3){
                    session.setAttribute("admin3",admin1);
                    return "redirect:adminEvalutionIndex";
                }
            } catch (Exception e) {
                session.setAttribute("message",e.getMessage());
                return "/admin/admin_login.jsp";
            }
        }
        return "/admin/admin_login.jsp";
    }

    //设计Map集合存储需要给页面的对象数据
    private Map<String,Object> result = new HashMap<>();

    /**
     * 分页查询  所有乘客
     * 前端：datagrid分页功能需要添加pagination属性
     * 后端：接收page和rows参数，根据参数分页查询，获取结果，返回给页面
     * total:总记录数
     * @param page 当前页码
     * @param rows 每页显示记录数/数据
     * @return
     */
    @RequestMapping("/listPassengerByPage")
    @ResponseBody //json
    public Map<String,Object> listPassengerByPage(Integer page,Integer rows){
        //设置分页参数
        PageHelper.startPage(page,rows);
        //查询所有数据
        List<User> list = userService.findAll();
        //使用PageInfo封装查询结果
        PageInfo<User> pageInfo = new PageInfo<>(list);
        //从PageInfo对象取出查询结果
        //总记录数
        long total = pageInfo.getTotal();
        //当前页数数据
        List<User> curList = pageInfo.getList();
        result.put("total",total);
        result.put("rows",curList);
        return result;
    }

    /**
     * 分页查询  所有待审核乘客
     * 前端：datagrid分页功能需要添加pagination属性
     * 后端：接收page和rows参数，根据参数分页查询，获取结果，返回给页面
     * total:总记录数
     * @param page 当前页码
     * @param rows 每页显示记录数/数据
     * @return
     */
    @RequestMapping("/passengerPendingList")
    @ResponseBody //json
    public Map<String,Object> passengerPendingList(Integer page,Integer rows){
        //设置分页参数
        PageHelper.startPage(page,rows);
        //查询所有数据
        List<User> list = userService.findPending();
        //使用PageInfo封装查询结果
        PageInfo<User> pageInfo = new PageInfo<>(list);
        //从PageInfo对象取出查询结果
        //总记录数
        long total = pageInfo.getTotal();
        //当前页数数据
        List<User> curList = pageInfo.getList();
        result.put("total",total);
        result.put("rows",curList);

        return result;
    }

    /**
     * 保存:修改与添加
     * @param user
     * @return
     */
    @RequestMapping("/savePassenger")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> savePassenger(User user){
        try {
            userService.save(user);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 测试easyUI用  现在用不到了
     * 查询所有数据，给页面返回一个json格式数据
     * easyUI的datagrid组件需要展示数据，要提供json数据：[{id:1,name:xx},{id:2,name:xx}]
     * @return
     */
    @RequestMapping("/listPassenger")
    @ResponseBody       //用于转换对象为json
    public List<User> listPassenger(){
        //查询数据
        return userService.findAll();
    }

    /**
     * 根据id查找，进行更新操作
     * @param uid
     * @return
     */
    @RequestMapping("/findPassengerById")
    @ResponseBody       //用于转换对象为json
    public User findPassengerById(Integer uid){
        return userService.findById(uid);
    }

    /**
     * 批量删除
     * @param uid
     * @return
     */
    @RequestMapping("/deletePassenger")
    @ResponseBody
    public Map<String,Object> deletePassenger(Integer[] uid){
        try {
            userService.delete(uid);
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
     * @param uid
     */
    @RequestMapping("/verifyPassenger")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> verifyPassenger(Integer[] uid){
        try {
            userService.verify(uid);
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
     * @param uid
     */
    @RequestMapping("/rejectPassenger")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> rejectPassenger(Integer[] uid){
        try {
            userService.reject(uid);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }
}
