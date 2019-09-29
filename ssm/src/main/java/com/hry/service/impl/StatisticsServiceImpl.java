package com.hry.service.impl;

import com.hry.dao.UserDao;
import com.hry.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private UserDao userDao;

    @Override
    public List<Integer> selectPassengerRegisted() {
        return userDao.selectPassengerRegisted();
    }

}
