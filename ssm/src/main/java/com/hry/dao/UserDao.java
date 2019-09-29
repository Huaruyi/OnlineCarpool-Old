package com.hry.dao;

import com.hry.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    /*
    模糊查询  如果使用#{username}  在传入值时需要带有%  比如 %华如意%
    或者使用：
    @Select(" SELECT * FROM user WHERE username like '%${value}%'")
     */

    //查询所有账户信息
    @Select("SELECT * FROM user")
    public List<User> findAll();

    //登录
    @Select("SELECT * FROM user WHERE uphone=#{uphone} AND upassword=#{upassword}")
    public User check(@Param("uphone")String uphone, @Param("upassword")String upassword);

    //注册
    @Insert("INSERT INTO user(uphone,upassword,uname,ugender,uidcard,ubirthday,uregistday,utimes,uevaluate,ulocation,ustate) VALUES(#{uphone},#{upassword},#{uname},#{ugender},#{uidcard},#{ubirthday},#{uregistday},#{utimes},#{uevaluate},#{ulocation},#{ustate})")
    public void regist(User user);

    //保存
    @Insert("INSERT INTO user(uphone,uname,ugender) VALUES(#{uphone},#{uname},#{ugender})")
    public void save(User user);

    //根据id查询
    @Select("SELECT * FROM user WHERE uid = #{uid}")
    public User findById(Integer uid);

    //更新
    @Update("UPDATE user SET uphone=#{uphone},uname=#{uname},ugender=#{ugender} WHERE uid=#{uid}")
    public void update(User user);

    //删除
    @Delete({
            "<script>"
                +"DELETE FROM user WHERE uid IN "
                +"<foreach item='uid' index='index' collection='array' open='(' separator=',' close=')'>"
                +       "#{uid}"
                +"</foreach>"
            +"</script>"
    })
    public void delete(Integer[] uid);

    //批量验证
    @Update({
            "<script>"
                +"UPDATE user SET ustate=2 WHERE uid IN "
                +"<foreach item='uid' index='index' collection='array' open='(' separator=',' close=')'>"
                +       "#{uid}"
                +"</foreach>"
             +"</script>"
    })
    public void verify(Integer[] uid);

    //批量驳回
    @Update({
            "<script>"
                +"UPDATE user SET ustate=-1 WHERE uid IN "
                    +"<foreach item='uid' index='index' collection='array' open='(' separator=',' close=')'>"
                    +       "#{uid}"
                    +"</foreach>"
            +"</script>"
    })
    public void reject(Integer[] uid);

    //查询状态码为1（请求认证）的用户
    @Select("SELECT * FROM user WHERE ustate=1")
    public List<User> findPending();

    //检查手机号是否已被注册
    @Select("SELECT COUNT(*) FROM user WHERE uphone=#{uphone}")
    public boolean checkUphone(String uphone);

    //更新手机号
    @Update("UPDATE user SET uphone=#{uphone} WHERE uid=#{uid}")
    public void updateUphone(@Param("uid") Integer uid, @Param("uphone") String uphone);

    //更新密码
    @Update("UPDATE user SET upassword=#{upassword} WHERE uid=#{uid}")
    public void updateUpassword(@Param("uid") Integer uid, @Param("upassword") String upassword);





    //统计注册
    @Select("SELECT ustate FROM user")
    public List<Integer> selectPassengerRegisted();
}
