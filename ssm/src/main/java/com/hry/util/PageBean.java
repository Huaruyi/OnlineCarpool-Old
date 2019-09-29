package com.hry.util;

import java.util.List;

public class PageBean<T> {

    private Integer currentPage;    //当前页码           页面传过来的
    private Integer currentCount;   //分页记录条数        后台设置
    private Integer totalPage;  //总的页码
    private Integer totalCount;     //总的记录条数
    private List<T> list;   //list集合



    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(Integer currentCount) {
        this.currentCount = currentCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageBean() {
    }

    public PageBean(Integer currentPage, Integer currentCount, Integer totalPage, Integer totalCount, List<T> list) {
        this.currentPage = currentPage;
        this.currentCount = currentCount;
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", currentCount=" + currentCount +
                ", totalPage=" + totalPage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}
