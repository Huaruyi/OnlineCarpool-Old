package com.hry.dao;

import com.hry.domain.Car;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDao {

    @Insert("INSERT INTO car(did,ccbrand,ctype,ccolor,cno,ccarlicense) VALUES(#{did},#{ccbrand},#{ctype},#{ccolor},#{cno},#{ccarlicense})")
    public void save();

    @Select("SELECT * FROM car WHERE cid=#{cid}")
    public Car findById(Integer cid);

    @Select("SELECT * FROM car WHERE did=#{did}")
    public Car findByDid(Integer cid);
}
