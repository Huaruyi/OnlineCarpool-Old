package com.hry.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Driver implements Serializable{

    private Integer did;
    private String dphone;
    private String dpassword;
    private String dname;
    private String dgender;
    private String ddriverlicense;
    private Date dbirthday;
    private String dregistday;
    private Integer dtimes;
    private Double devaluate;
    private String dlocation;
    private Integer dstate;
    private MultipartFile multipartFile;

    @Override
    public String toString() {
        return "Driver{" +
                "did=" + did +
                ", dphone='" + dphone + '\'' +
                ", dpassword='" + dpassword + '\'' +
                ", dname='" + dname + '\'' +
                ", dgender='" + dgender + '\'' +
                ", ddriverlicense='" + ddriverlicense + '\'' +
                ", dbirthday=" + dbirthday +
                ", dregistday='" + dregistday + '\'' +
                ", dtimes=" + dtimes +
                ", devaluate=" + devaluate +
                ", dlocation='" + dlocation + '\'' +
                ", dstate=" + dstate +
                ", multipartFile=" + multipartFile +
                '}';
    }

    public Driver(Integer did, String dphone, String dpassword, String dname, String dgender, String ddriverlicense, Date dbirthday, String dregistday, Integer dtimes, Double devaluate, String dlocation, Integer dstate, MultipartFile multipartFile) {
        this.did = did;
        this.dphone = dphone;
        this.dpassword = dpassword;
        this.dname = dname;
        this.dgender = dgender;
        this.ddriverlicense = ddriverlicense;
        this.dbirthday = dbirthday;
        this.dregistday = dregistday;
        this.dtimes = dtimes;
        this.devaluate = devaluate;
        this.dlocation = dlocation;
        this.dstate = dstate;
        this.multipartFile = multipartFile;
    }

    public Driver() {
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDphone() {
        return dphone;
    }

    public void setDphone(String dphone) {
        this.dphone = dphone;
    }

    public String getDpassword() {
        return dpassword;
    }

    public void setDpassword(String dpassword) {
        this.dpassword = dpassword;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDgender() {
        return dgender;
    }

    public void setDgender(String dgender) {
        this.dgender = dgender;
    }

    public String getDdriverlicense() {
        return ddriverlicense;
    }

    public void setDdriverlicense(String ddriverlicense) {
        this.ddriverlicense = ddriverlicense;
    }

    public String getDregistday() {
        return dregistday;
    }

    public void setDregistday(String dregistday) {
        this.dregistday = dregistday;
    }

    public Integer getDtimes() {
        return dtimes;
    }

    public void setDtimes(Integer dtimes) {
        this.dtimes = dtimes;
    }

    public Double getDevaluate() {
        return devaluate;
    }

    public void setDevaluate(Double devaluate) {
        this.devaluate = devaluate;
    }

    public String getDlocation() {
        return dlocation;
    }

    public void setDlocation(String dlocation) {
        this.dlocation = dlocation;
    }

    public Integer getDstate() {
        return dstate;
    }

    public void setDstate(Integer dstate) {
        this.dstate = dstate;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Date getDbirthday() {
        return dbirthday;
    }

    public void setDbirthday(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dbirthday;
        try {
            dbirthday = sdf.parse(s);
            this.dbirthday = dbirthday;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}



