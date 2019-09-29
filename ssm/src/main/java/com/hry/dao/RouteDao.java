package com.hry.dao;

import com.hry.domain.Route;
import org.apache.ibatis.annotations.*;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

public interface RouteDao {

    //查询所有
    @Select("SELECT * FROM route WHERE rdeparture=#{rdeparture} AND rdestination=#{rdestination}")
    public Route findByLocation(@Param("rdeparture")String rdeparture, @Param("rdestination")String rdestination);

    //根据路线查询指定rid
    @Select("SELECT rid FROM route WHERE rdeparture=#{rdeparture} AND rdestination=#{rdestination}")
    public Integer findIdByLocation(@Param("rdeparture")String rdeparture, @Param("rdestination")String rdestination);

    //查询指定距离
    @Select("SELECT rdistance FROM route WHERE rdeparture=#{rdeparture} AND rdestination=#{rdestination}")
    public List<Double> findOneRdistance(@Param("rdeparture") String depa,@Param("rdestination") String dest);

    //查询指定信号数
    @Select("SELECT rsignal FROM route WHERE rdeparture=#{rdeparture} AND rdestination=#{rdestination}")
    public String findOneRsignal();

    //根据id拆查询
    @Select("SELECT * FROM route WHERE rid = #{rid}")
    public Route findById(Integer rid);

    //查询所有出发地
    @Select("SELECT DISTINCT rdeparture FROM route WHERE rstate=1")
    public List<String> findAllRdeparture();

    //查询所有目的地
    @Select("SELECT DISTINCT rdestination FROM route WHERE rstate=1 AND rdeparture=#{rdeparture}")
    public List<String> findRdestinationByRdeparture(String rdeparture);

    //查询所有路线信息
    @Select("SELECT * FROM route")
    public List<Route> findAll();

    //更新
    @Update("UPDATE route SET rdeparture=#{rdeparture},rdestination=#{rdestination},rdistance=#{rdistance},rsignal=#{rsignal} WHERE rid=#{rid}")
    public void update(Route route);

    //新增保存
    @Insert("INSERT INTO user(rdeparture,rdestination,rdistance，rsignal,rstate) VALUES(#{rdeparture},#{rdestination},#{rdistance},#{rsignal},1)")
    public void save(Route route);

    //删除(下架)
    @Update({
            "<script>"
                    +"Update route SET rstate = 0 WHERE rid IN "
                    +"<foreach item='rid' index='index' collection='array' open='(' separator=',' close=')'>"
                    +       "#{rid}"
                    +"</foreach>"
            +"</script>"
    })
    public void delete(Integer[] rid);

    //恢复
    @Update({
            "<script>"
                    +"Update route SET rstate = 1 WHERE rid IN "
                    +"<foreach item='rid' index='index' collection='array' open='(' separator=',' close=')'>"
                    +       "#{rid}"
                    +"</foreach>"
                    +"</script>"
    })
    public void renew(Integer[] rid);
}
