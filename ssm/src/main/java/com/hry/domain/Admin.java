package com.hry.domain;

import java.io.Serializable;

public class Admin implements Serializable {

    private Integer aid;
    private String aname;
    private String apassword;
    private Integer arole;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public Integer getArole() {
        return arole;
    }

    public void setArole(Integer arole) {
        this.arole = arole;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", apassword='" + apassword + '\'' +
                ", arole=" + arole +
                '}';
    }
}
