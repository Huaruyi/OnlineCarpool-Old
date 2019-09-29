package com.hry.bean;

public class StatBean {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public StatBean() {
    }

    public StatBean(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "StatBean{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
