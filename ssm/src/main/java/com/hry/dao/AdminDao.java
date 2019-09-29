package com.hry.dao;

import com.hry.domain.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {

    @Select("SELECT * FROM admin WHERE aname=#{aname} AND apassword=#{apassword} AND arole=#{arole}")
    public Admin check(@Param("aname") String aname, @Param("apassword") String apassword, @Param("arole") Integer arole);
}
