package com.test.domain;

import java.io.Serializable;

/**
 * 保存中国各省的总体疫情信息
 * @author balance
 */
public class ChinaInfo implements Serializable {

    private String province;
    private int confirm;
    private int dead;
    private int cure;

    public ChinaInfo(String province, int confirm, int dead, int cure) {
        this.province = province;
        this.confirm = confirm;
        this.dead = dead;
        this.cure = cure;
    }

    public ChinaInfo() {
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
        return "ChinaInfo{" +
                "province='" + province + '\'' +
                ", confirm=" + confirm +
                ", dead=" + dead +
                ", cure=" + cure +
                '}';
    }
}
