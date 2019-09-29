package com.hry.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hry.domain.Route;
import com.hry.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RouteController {
    @Resource
    private RouteService routeService;

    //设计Map集合存储需要给页面的对象数据
    private Map<String,Object> result = new HashMap<>();

    /**
     * 返回查询出的某出发地对应的json数据
     *
     * @param session
     * @param depa
     * @return
     */
    @RequestMapping("/changedest")
    @ResponseBody
    public List<String> changedest(HttpSession session,String depa){
        List<String> dest = routeService.findRdestinationByRdeparture(depa);
        return dest;
    }

    /**
     * 返回查询出的某出发地对应的json数据
     *
     * @param session
     * @param depa
     * @return
     */
    @RequestMapping("/selectdistance")
    @ResponseBody
    public List<Double> selectdistance(HttpSession session,String depa,String dest){
        List<Double> rdistance = routeService.findOneRdistance(depa, dest);
        return rdistance;
    }


    /**
     * 分页查询  所有路线
     * 前端：datagrid分页功能需要添加pagination属性
     * 后端：接收page和rows参数，根据参数分页查询，获取结果，返回给页面
     * total:总记录数
     * @param page 当前页码
     * @param rows 每页显示记录数/数据
     * @return
     */
    @RequestMapping("/listRouteByPage")
    @ResponseBody   //json
    public Map<String,Object> listRouteByPage(Integer page,Integer rows){
        //设置分页参数
        PageHelper.startPage(page,rows);
        //查询所有数据
        List<Route> list = routeService.findAll();
        //使用PageInfo封装查询结果
        PageInfo<Route> pageInfo = new PageInfo<>(list);
        //从PageInfo对象取出查询结果
        //总记录数
        long total = pageInfo.getTotal();
        //当前页数数据
        List<Route> curList = pageInfo.getList();
        result.put("total",total);
        result.put("rows",curList);
        return result;
    }

    /**
     * 保存:修改与添加
     * @param route
     * @return
     */
    @RequestMapping("/saveRoute")
    @ResponseBody       //用于转换对象为json
    public Map<String,Object> saveRoute(Route route){
        try {
            routeService.save(route);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 根据id查找，进行更新操作
     * @param rid
     * @return
     */
    @RequestMapping("/findRouteById")
    @ResponseBody       //用于转换对象为json
    public Route findRouteById(Integer rid){
        return routeService.findById(rid);
    }

    /**
     * 批量下架
     * @param rid
     * @return
     */
    @RequestMapping("/deleteRoute")
    @ResponseBody
    public Map<String,Object> deleteRoute(Integer[] rid){
        try {
            routeService.delete(rid);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    /**
     * 批量恢复
     * @param rid
     * @return
     */
    @RequestMapping("/renewRoute")
    @ResponseBody
    public Map<String,Object> renewRoute(Integer[] rid){
        try {
            routeService.renew(rid);
            result.put("success",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success",false);
            result.put("msg",e.getMessage());
        }
        return result;
    }
}
