package com.hry.service;



import com.hry.domain.Car;
import com.hry.domain.Driver;

import java.util.List;

public interface DriverService {

    //查询所有账户信息
    public List<Driver> findAll();

    //根据用户名查询
    public Driver check(String dphone, String dpassword);
    /*//验证是否被注册过
    public boolean checkUsername(String name);*/

    //注册
    public void regist(Driver driver);

    //保存
    public void save(Driver driver);

    //根据id查询
    public Driver findById(Integer did);

    //根据phone查id
    public String findIdByPhone4RigistCar(String dphone);

    //批量删除
    public void delete(Integer[] did);

    //批量验证
    public void verify(Integer[] did);

    //批量驳回
    public void reject(Integer[] did);

    //查询所有待审用户
    public List<Driver> findPending();

    //检查手机号是否已被注册
    public boolean checkDphone(String dphone);

    //更新手机号
    public void updateDphone(Integer did, String dphone);

    //更新密码
    public void updateDpassword(Integer did, String newdpassword);



    //绑定汽车
    public void saveCar(Car car);

    //批量验证
    public void verifyCar(Integer[] cid);

    //批量驳回
    public void rejectCar(Integer[] cid);

    //查询所有待审用户
    public List<Car> findCarPending();
    //更新评价
    public void updateEvaluate(Integer did, Double devaluate);
    //查找绑定的车
    public Car findCarById(Integer did);
}
