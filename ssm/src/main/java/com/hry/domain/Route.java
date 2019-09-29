package com.hry.domain;

import java.io.Serializable;

public class Route implements Serializable {

    private Integer rid;
    private String rdeparture;
    private String rdestination;
    private Double rdistance;
    private Integer rsignal;
    private Integer rstate;



    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRdeparture() {
        return rdeparture;
    }

    public void setRdeparture(String rdeparture) {
        this.rdeparture = rdeparture;
    }

    public String getRdestination() {
        return rdestination;
    }

    public void setRdestination(String rdestination) {
        this.rdestination = rdestination;
    }

    public Double getRdistance() {
        return rdistance;
    }

    public void setRdistance(Double rdistance) {
        this.rdistance = rdistance;
    }

    public Integer getRsignal() {
        return rsignal;
    }

    public void setRsignal(Integer rsignal) {
        this.rsignal = rsignal;
    }

    public Integer getRstate() {
        return rstate;
    }

    public void setRstate(Integer rstate) {
        this.rstate = rstate;
    }

    public Route(Integer rid, String rdeparture, String rdestination, Double rdistance, Integer rsignal, Integer rstate) {
        this.rid = rid;
        this.rdeparture = rdeparture;
        this.rdestination = rdestination;
        this.rdistance = rdistance;
        this.rsignal = rsignal;
        this.rstate = rstate;
    }

    public Route() {
    }

    @Override
    public String toString() {
        return "Route{" +
                "rid=" + rid +
                ", rdeparture='" + rdeparture + '\'' +
                ", rdestination='" + rdestination + '\'' +
                ", rdistance=" + rdistance +
                ", rsignal=" + rsignal +
                ", rstate=" + rstate +
                '}';
    }
}
