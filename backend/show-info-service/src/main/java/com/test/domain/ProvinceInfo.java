package com.test.domain;

import java.io.Serializable;

/**
 * 各省内的疫情信息实体类
 * @author balance
 */
public class ProvinceInfo implements Serializable {

    private String parent;
    private String city;
    private int confirm;
    private int dead;
    private int cure;

    public ProvinceInfo(String parent, String city, int confirm, int dead, int cure) {
        this.parent = parent;
        this.city = city;
        this.confirm = confirm;
        this.dead = dead;
        this.cure = cure;
    }

    public ProvinceInfo() {
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public int getDead() {
        return dead;
    }

    public void setDead(int dead) {
        this.dead = dead;
    }

    public int getCure() {
        return cure;
    }

    public void setCure(int cure) {
        this.cure = cure;
    }

    @Override
    public String toString() {
        return "ProvinceInfo{" +
                "parent='" + parent + '\'' +
                ", city='" + city + '\'' +
                ", confirm=" + confirm +
                ", dead=" + dead +
                ", cure=" + cure +
                '}';
    }
}
