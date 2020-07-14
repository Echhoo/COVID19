package com.test.domain;

import java.io.Serializable;

/**
 * 保存中国各省的总体疫情信息
 * @author balance
 */
public class ChinaInfo implements Serializable {

    private String name;
    private int confirm;
    private int dead;
    private int cure;

    @Override
    public String toString() {
        return "ChinaInfo{" +
                "name='" + name + '\'' +
                ", confirm=" + confirm +
                ", dead=" + dead +
                ", cure=" + cure +
                '}';
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

    public ChinaInfo(String name, int confirm, int dead, int cure) {
        this.name = name;
        this.confirm = confirm;
        this.dead = dead;
        this.cure = cure;
    }

    public ChinaInfo() {
    }
}
