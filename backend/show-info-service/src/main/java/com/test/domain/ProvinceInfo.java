package com.test.domain;

import java.io.Serializable;

/**
 * 各省内的疫情信息实体类
 * @author balance
 */
public class ProvinceInfo implements Serializable {

    private String parent;
    private String name;
    private int confirm;
    private int dead;
    private int cure;

    @Override
    public String toString() {
        return "ProvinceInfo{" +
                "parent='" + parent + '\'' +
                ", name='" + name + '\'' +
                ", confirm=" + confirm +
                ", dead=" + dead +
                ", cure=" + cure +
                '}';
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ProvinceInfo(String parent, String name, int confirm, int dead, int cure) {
        this.parent = parent;
        this.name = name;
        this.confirm = confirm;
        this.dead = dead;
        this.cure = cure;
    }

    public ProvinceInfo() {
    }
}
