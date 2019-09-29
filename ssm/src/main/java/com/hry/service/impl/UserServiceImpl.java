package com.hry.service.impl;

import com.hry.dao.UserDao;
import com.hry.domain.User;
import com.hry.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    //@Autowired
    @Resource
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        System.out.println("业务层：查询所有账户");
        return userDao.findAll();
    }



    @Override
    public User check(String uphone, String upassword) {

        User user = userDao.check(uphone, upassword);
        if (user != null){
            //判断是否注册
            return user;
        }else{
            throw new RuntimeException("用户名或密码错误");
        }

    }

    //注册
    @Override
    public void regist(User user) {
        user.setUstate(1);  //初始化用户状态设为1，已注册但未通过身份认证审核
        user.setUtimes(0);  //初始化用户拼车次数为0
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        user.setUregistday(format);

        //使用fileupload组建完成文件上传
        //上传的位置
        String path = "D:\\uploads\\passenger";
        File file = new File(path);
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }
        //说明上传文件项
        //获取到上传文件的名称
        MultipartFile multipartFile = user.getMultipartFile();
        String fileName = multipartFile.getOriginalFilename();
        if (!fileName.equals("")){
            //把文件的名称设置唯一值，uuid
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid+"_"+fileName;
            //完成文件上传
            try {
                multipartFile.transferTo(new File(path,fileName));
            } catch (IOException e) {
                throw new RuntimeException("上传身份证失败");
            }
            String sqlpath = path+"\\"+fileName;
            sqlpath = sqlpath.substring(10);  //如果做图片下载、回显 由于已经在idea配好了虚拟路径D:\\uploads 因此上传到本地后需要截取掉前10个字符串 只保存相对路径

            user.setUidcard(sqlpath);
        }
        userDao.regist(user);
    }



//保存
    @Override
    public void save(User user) {
        //判断是添加还是修改
        if (user.getUid()!=null){
            //修改
            userDao.update(user);
        }else{
            //添加
            userDao.save(user);
        }

    }
//查询id
    @Override
    public User findById(Integer uid) {
        return userDao.findById(uid);
    }
//删除
    @Override
    public void delete(Integer[] uid) {
        userDao.delete(uid);
    }
//通过
    @Override
    public void verify(Integer[] uid) {
        userDao.verify(uid);
    }
//驳回
    @Override
    public void reject(Integer[] uid) {
        userDao.reject(uid);
    }
//查询待审核
    @Override
    public List<User> findPending() {
         return userDao.findPending();
    }

    @Override
    public boolean checkUphone(String uphone) {
        return userDao.checkUphone(uphone);
    }

    @Override
    public void updateUphone(Integer uid, String uphone) {
        userDao.updateUphone(uid,uphone);
    }

    @Override
    public void updateUpassword(Integer uid, String upassword) {
        userDao.updateUpassword(uid,upassword);
    }






}
