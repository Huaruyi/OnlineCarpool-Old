package com.hry.service;

import com.hry.domain.Route;

import java.util.List;

public interface RouteService {

    //根据路线查询
    public Route findByLocation(String rdeparture, String rdestination);

    //根据id查询
    public Route findById(Integer rid);

    //查询所有出发地
    public List<String> findAllRdeparture();

    //查询公里数
    public List<Double> findOneRdistance(String depa,String dest);

    //根据出发地查询目的地
    public List<String> findRdestinationByRdeparture(String rdeparture);

    public List<Route> findAll();

    public void save(Route route);

    public void delete(Integer[] rid);

    public void renew(Integer[] rid);
}

