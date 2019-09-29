package com.hry.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class Car implements Serializable {

    private Integer cid;
    private Integer did;
    private String cbrand;
    private String ctype;
    private String ccolor;
    private String cno;
    private String ccarlicense;
    private Integer cstate;
    private Driver driver;
    private MultipartFile multipartFile;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getCbrand() {
        return cbrand;
    }

    public void setCbrand(String cbrand) {
        this.cbrand = cbrand;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getCcolor() {
        return ccolor;
    }

    public void setCcolor(String ccolor) {
        this.ccolor = ccolor;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCcarlicense() {
        return ccarlicense;
    }

    public void setCcarlicense(String ccarlicense) {
        this.ccarlicense = ccarlicense;
    }

    public Integer getCstate() {
        return cstate;
    }

    public void setCstate(Integer cstate) {
        this.cstate = cstate;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Car(Integer cid, Integer did, String cbrand, String ctype, String ccolor, String cno, String ccarlicense, Integer cstate, Driver driver, MultipartFile multipartFile) {
        this.cid = cid;
        this.did = did;
        this.cbrand = cbrand;
        this.ctype = ctype;
        this.ccolor = ccolor;
        this.cno = cno;
        this.ccarlicense = ccarlicense;
        this.cstate = cstate;
        this.driver = driver;
        this.multipartFile = multipartFile;
    }

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "cid=" + cid +
                ", did=" + did +
                ", cbrand='" + cbrand + '\'' +
                ", ctype='" + ctype + '\'' +
                ", ccolor='" + ccolor + '\'' +
                ", cno='" + cno + '\'' +
                ", ccarlicense='" + ccarlicense + '\'' +
                ", cstate='" + cstate + '\'' +
                ", driver=" + driver +
                ", multipartFile=" + multipartFile +
                '}';
    }
}
