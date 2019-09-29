package com.hry.service.impl;

import com.hry.dao.DriverDao;
import com.hry.domain.Car;
import com.hry.domain.Driver;
import com.hry.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service("driverService")
public class DriverServiceImpl implements DriverService {
    //@Autowired
    @Resource
    private DriverDao driverDao;

    @Override
    public List<Driver> findAll() {
        System.out.println("业务层：查询所有账户");
        return driverDao.findAll();
    }

    @Override
    public Driver check(String dphone, String dpassword) {

        Driver driver = driverDao.check(dphone, dpassword);
        if (driver != null){
            //判断是否注册
            return driver;
        }else{
            throw new RuntimeException("用户名或密码错误");
        }
    }

    //注册
    @Override
    public void regist(Driver driver) {
        driver.setDstate(1);  //初始化用户状态设为1，已注册但未通过身份认证审核
        driver.setDtimes(0);  //初始化用户拼车次数为0
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        driver.setDregistday(format);

        //使用fileupload组建完成文件上传
        //上传的位置
        String path = "D:\\uploads\\driver";
        File file = new File(path);
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }
        //说明上传文件项
        //获取到上传文件的名称
        MultipartFile multipartFile = driver.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        if (!fileName.equals("")){
            //把文件的名称设置唯一值，uuid
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid+"_"+fileName;
            //完成文件上传
            try {
                multipartFile.transferTo(new File(path,fileName));
            } catch (IOException e) {
                throw new RuntimeException("上传驾驶证失败");
            }
            String sqlpath = path+"\\"+fileName;
            sqlpath = sqlpath.substring(10);  //如果做图片下载、回显 由于已经在idea配好了虚拟路径D:\\uploads 因此上传到本地后需要截取掉前10个字符串 只保存相对路径

            driver.setDdriverlicense(sqlpath);
        }


        driverDao.regist(driver);
    }

    @Override
    public void save(Driver driver) {
        //判断是添加还是修改
        if (driver.getDid()!=null){
            //修改
            driverDao.update(driver);
        }else{
            //添加
            driverDao.save(driver);
        }
    }

    @Override
    public Driver findById(Integer did) {
        return driverDao.findById(did);
    }

    //根据phone查id
    @Override
    public String findIdByPhone4RigistCar(String dphone){
        return driverDao.findIdByPhone4RigistCar(dphone);
    }

    @Override
    public void delete(Integer[] did) {
        driverDao.delete(did);
    }

    @Override
    public void verify(Integer[] did) {
        driverDao.verify(did);
    }

    @Override
    public void reject(Integer[] did) {
        driverDao.reject(did);
    }

    @Override
    public List<Driver> findPending() {
        return driverDao.findPending();
    }

    @Override
    public boolean checkDphone(String dphone) {
        return driverDao.checkDphone(dphone);
    }

    @Override
    public void updateDphone(Integer did, String dphone) {
        driverDao.updateDphone(did,dphone);
    }

    @Override
    public void updateDpassword(Integer did, String newdpassword) {
        driverDao.updateDpassword(did,newdpassword);
    }

    @Override
    public void saveCar(Car car) {
        //使用fileupload组建完成文件上传
        //上传的位置
        String path = "D:\\uploads\\car";
        File file = new File(path);
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }
        //说明上传文件项
        //获取到上传文件的名称
        MultipartFile multipartFile = car.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        if (!fileName.equals("")){
            //把文件的名称设置唯一值，uuid
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid+"_"+fileName;
            //完成文件上传
            try {
                multipartFile.transferTo(new File(path,fileName));
            } catch (IOException e) {
                throw new RuntimeException("上传行驶证失败");
            }
            String sqlpath = path+"\\"+fileName;
            sqlpath = sqlpath.substring(10);  //如果做图片下载、回显 由于已经在idea配好了虚拟路径D:\\uploads 因此上传到本地后需要截取掉前10个字符串 只保存相对路径

            car.setCcarlicense(sqlpath);
        }
        driverDao.saveCar(car);
    }

    @Override
    public void verifyCar(Integer[] cid) {
        driverDao.verifyCar(cid);
    }

    @Override
    public void rejectCar(Integer[] cid) {
        driverDao.rejectCar(cid);
    }

    @Override
    public List<Car> findCarPending() {
        return driverDao.findCarPending();
    }

    @Override
    public void updateEvaluate(Integer did, Double devaluate) {
        driverDao.updateEvaluate(did,devaluate);
    }

    @Override
    public Car findCarById(Integer did) {
        return driverDao.findCarById(did);
    }

}
