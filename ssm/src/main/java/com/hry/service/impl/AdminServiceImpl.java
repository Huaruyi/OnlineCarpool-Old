package com.hry.service.impl;


import com.hry.dao.AdminDao;
import com.hry.domain.Admin;
import com.hry.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    @Override
    public Admin check(String aname, String apassword, Integer arole) {
        Admin admin = adminDao.check(aname, apassword, arole);
        if (admin != null){
            return admin;
        }else {
            throw new RuntimeException("用户名或密码错误");
        }
    }
}
