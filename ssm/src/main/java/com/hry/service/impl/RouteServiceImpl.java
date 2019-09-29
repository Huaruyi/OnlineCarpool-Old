package com.hry.service.impl;

import com.hry.dao.RouteDao;
import com.hry.domain.Route;
import com.hry.service.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("routeService")
public class RouteServiceImpl implements RouteService {

    //@Autowired
    @Resource
    private RouteDao routeDao;

    //根据路线查询指定rid
    @Override
    public Route findByLocation(String rdeparture, String rdestination) {
        return routeDao.findByLocation(rdeparture,rdestination);
    }

    @Override
    public Route findById(Integer rid) {
        return routeDao.findById(rid);
    }

    @Override
    public List<String> findAllRdeparture() {
        return routeDao.findAllRdeparture();
    }

    @Override
    public List<Double> findOneRdistance(String depa, String dest) {
        return routeDao.findOneRdistance(depa,dest);
    }

    @Override
    public List<String> findRdestinationByRdeparture(String rdeparture) {
        return routeDao.findRdestinationByRdeparture(rdeparture);
    }

    @Override
    public List<Route> findAll() {
        System.out.println("业务层：查询所有路线");
        return routeDao.findAll();
    }

    @Override
    public void save(Route route) {
        //判断是添加还是修改
        if (route.getRid()!=null){
            //修改
            routeDao.update(route);
        }else{
            //添加
            routeDao.save(route);
        }
    }

    @Override
    public void delete(Integer[] rid) {
        routeDao.delete(rid);
    }

    @Override
    public void renew(Integer[] rid) {
        routeDao.renew(rid);
    }
}
