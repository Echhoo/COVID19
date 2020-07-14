package com.test.service;

import com.test.domain.ChinaInfo;
import com.test.domain.ProvinceInfo;
import com.test.domain.WorldInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class GetInfoServiceTest {

    @Resource
    private GetInfoService getInfoService;

    @Test
    public void getChinaInfoTest(){
        List<ChinaInfo> chinaInfo = getInfoService.getChinaInfo();
        for (ChinaInfo info : chinaInfo) {
            System.out.println(info);
        }
    }

    @Test
    public void getWorldInfoTest(){
        List<WorldInfo> worldInfo = getInfoService.getWorldInfo();
        for (WorldInfo info : worldInfo) {
            System.out.println(info);
        }
    }

    @Test
    public void getProvinceInfoTest(){
        String province = "北京";
        List<ProvinceInfo> provinceInfo = getInfoService.getProvinceInfo(province);
        for (ProvinceInfo info : provinceInfo) {
            System.out.println(info);
        }

    }

}
