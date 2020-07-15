package group.corona.service;

import group.corona.domain.ChinaInfo;
import group.corona.domain.ProvinceInfo;
import group.corona.domain.WorldInfo;

import java.util.List;

/**
 * 获取疫情信息的service层接口
 * @author balance
 */
public interface GetInfoService {

    /**
     * 获取中国各省的疫情信息
     * @return
     */
    List<ChinaInfo> getChinaInfo();

    /**
     * 获取世界各国的疫情信息
     * @return
     */
    List<WorldInfo> getWorldInfo();

    /**
     * 获取中国制定省内各地区的疫情信息
     * @param province
     * @return
     */
    List<ProvinceInfo> getProvinceInfo(String province);

}
