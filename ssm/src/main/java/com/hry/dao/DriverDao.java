package com.hry.dao;

import com.hry.domain.Car;
import com.hry.domain.Driver;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverDao {

    //查询所有账户信息
    @Select("SELECT * FROM driver")
    public List<Driver> findAll();

    //登录
    @Select("SELECT * FROM driver WHERE dphone=#{dphone} AND dpassword=#{dpassword}")
    public Driver check(@Param("dphone")String dphone, @Param("dpassword")String dpassword);

    //注册
    @Insert("INSERT INTO driver(dphone,dpassword,dname,dgender,ddriverlicense,dbirthday,dregistday,dtimes,dlocation,dstate) VALUES(#{dphone},#{dpassword},#{dname},#{dgender},#{ddriverlicense},#{dbirthday},#{dregistday},#{dtimes},#{dlocation},#{dstate})")
    public void regist(Driver driver);


    //保存
    @Insert("INSERT INTO driver(dphone,dname,dgender) VALUES(#{dphone},#{dname},#{dgender})")
    public void save(Driver driver);

    //根据id查询
    @Select("SELECT * FROM driver WHERE did = #{did}")
    public Driver findById(Integer did);

    //根据phone查id
    @Select("SELECT did FROM driver WHERE dphone = #{dphone}")
    public String findIdByPhone4RigistCar(String dphone);

    //更新
    @Update("UPDATE driver SET dphone=#{dphone},dname=#{dname},dgender=#{dgender} WHERE did=#{did}")
    public void update(Driver driver);

    //删除
    @Delete({
            "<script>"
                    +"DELETE FROM driver WHERE did IN "
                    +"<foreach item='did' index='index' collection='array' open='(' separator=',' close=')'>"
                    +       "#{did}"
                    +"</foreach>"
                    +"</script>"
    })
    public void delete(Integer[] did);

    //批量验证
    @Update({
            "<script>"
                    +"UPDATE driver SET dstate=2 WHERE did IN "
                    +"<foreach item='did' index='index' collection='array' open='(' separator=',' close=')'>"
                    +       "#{did}"
                    +"</foreach>"
                    +"</script>"
    })
    public void verify(Integer[] did);

    //批量驳回
    @Update({
            "<script>"
                    +"UPDATE driver SET dstate=-1 WHERE did IN "
                    +"<foreach item='did' index='index' collection='array' open='(' separator=',' close=')'>"
                    +       "#{did}"
                    +"</foreach>"
                    +"</script>"
    })
    public void reject(Integer[] did);

    //查询状态码为1（请求认证）的用户
    @Select("SELECT * FROM driver WHERE dstate=1")
    public List<Driver> findPending();

    //检查手机号是否已被注册
    @Select("SELECT COUNT(*) FROM driver WHERE dphone=#{dphone}")
    public boolean checkDphone(String dphone);

    //更新手机号
    @Update("UPDATE driver SET dphone=#{dphone} WHERE did=#{did}")
    public void updateDphone(@Param("did") Integer did, @Param("dphone") String dphone);

    //更新密码
    @Update("UPDATE driver SET dpassword=#{dpassword} WHERE did=#{did}")
    public void updateDpassword(@Param("did") Integer did, @Param("newdpassword") String newdpassword);

    //保存 用于司机注册车辆
    @Insert("INSERT INTO car(did,cbrand,ctype,ccolor,cno,ccarlicense,cstate) VALUES(#{did},#{cbrand},#{ctype},#{ccolor},#{cno},#{ccarlicense},#{cstate})")
    public void saveCar(Car car);

    //批量验证
    @Update({
            "<script>"
                    +"UPDATE car SET cstate=2 WHERE cid IN "
                    +"<foreach item='cid' index='index' collection='array' open='(' separator=',' close=')'>"
                    +       "#{cid}"
                    +"</foreach>"
                    +"</script>"
    })
    public void verifyCar(Integer[] cid);

    //批量驳回
    @Update({
            "<script>"
                    +"UPDATE car SET cstate=-1 WHERE cid IN "
                    +"<foreach item='cid' index='index' collection='array' open='(' separator=',' close=')'>"
                    +       "#{cid}"
                    +"</foreach>"
                    +"</script>"
    })
    public void rejectCar(Integer[] cid);


    //查询状态码为1（请求认证）的用户
    @Select("SELECT * FROM car WHERE cstate=1")
    public List<Car> findCarPending();

    //更新司机评论
    @Update("UPDATE driver SET devaluate=#{devaluate} WHERE did=#{did}")
    public void updateEvaluate(Integer did, Double devaluate);

    //查询绑定的车辆
    @Select("SELECT * FROM car WHERE did=#{did}")
    public Car findCarById(Integer did);
}
