package com.hry.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {

    private Integer uid;
    private String  uphone;
    private String  upassword;
    private String  uname;
    private String  ugender;
    private String  uidcard;
    private Date    ubirthday;
    private String  uregistday;
    private Integer utimes;
    private Double  uevaluate;
    private String  ulocation;
    private Integer ustate;
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }



    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uphone=" + uphone +
                ", upassword='" + upassword + '\'' +
                ", uname='" + uname + '\'' +
                ", ugender='" + ugender + '\'' +
                ", uidcard='" + uidcard + '\'' +
                ", ubirthday=" + ubirthday +
                ", uregistday=" + uregistday +
                ", utimes=" + utimes +
                ", uevaluate=" + uevaluate +
                ", ulocation='" + ulocation + '\'' +
                ", ustate=" + ustate +
                '}';
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUphone() {
        return uphone;
    }

    public void setUphone(String uphone) {
        this.uphone = uphone;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUgender() {
        return ugender;
    }

    public void setUgender(String ugender) {
        this.ugender = ugender;
    }

    public String getUidcard() {
        return uidcard;
    }

    public void setUidcard(String uidcard) {
        this.uidcard = uidcard;
    }

    public Date getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date ubirthday;
        try {
            ubirthday = sdf.parse(s);
            this.ubirthday = ubirthday;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getUregistday() {
        return uregistday;
    }

    public void setUregistday(String uregistday) {
        this.uregistday = uregistday;
    }

    public User(String uphone, String upassword, String uname, String ugender, String uidcard, Date ubirthday, String ulocation) {
        this.uphone = uphone;
        this.upassword = upassword;
        this.uname = uname;
        this.ugender = ugender;
        this.uidcard = uidcard;
        this.ubirthday = ubirthday;
        this.ulocation = ulocation;
    }

    public Integer getUtimes() {
        return utimes;
    }

    public void setUtimes(Integer utimes) {
        this.utimes = utimes;
    }

    public Double getUevaluate() {
        return uevaluate;
    }

    public void setUevaluate(Double uevaluate) {
        this.uevaluate = uevaluate;
    }

    public String getUlocation() {
        return ulocation;
    }

    public void setUlocation(String ulocation) {
        this.ulocation = ulocation;
    }

    public Integer getUstate() {
        return ustate;
    }

    public void setUstate(Integer ustate) {
        this.ustate = ustate;
    }

    public User() {
    }
}
