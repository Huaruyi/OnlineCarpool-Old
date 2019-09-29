package com.hry.service;

import com.hry.domain.User;

import java.util.List;

public interface UserService {
    //查询所有账户信息
    public List<User> findAll();

    //根据用户手机查询
    public User check(String uphone, String upassword);

    /*//验证是否被注册过
    public boolean checkUsername(String name);*/

    //注册
    public void regist(User user);

    //保存
    public void save(User user);

    //根据id查询
    public User findById(Integer uid);

    //批量删除
    public void delete(Integer[] uid);

    //批量验证
    public void verify(Integer[] uid);

    //批量验证
    public void reject(Integer[] uid);

    //查询所有待审用户
    public List<User> findPending();

    //检查手机号是否已被注册
    public boolean checkUphone(String uphone);

    //更新手机号
    public void updateUphone(Integer uid, String uphone);

    //更新密码
    public void updateUpassword(Integer uid, String upassword);












}
