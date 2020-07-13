package com.test.domain;

import java.io.Serializable;

/**
 * 保存世界各国疫情信息的实体类
 * @author balance
 */
public class WorldInfo implements Serializable {

    private String country;
    private int confirm;
    private int dead;
    private int cure;

    public WorldInfo() {
    }

    public WorldInfo(String country, int confirm, int dead, int cure) {
        this.country = country;
        this.confirm = confirm;
        this.dead = dead;
        this.cure = cure;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return "WorldInfo{" +
                "country='" + country + '\'' +
                ", confirm=" + confirm +
                ", dead=" + dead +
                ", cure=" + cure +
                '}';
    }
}
