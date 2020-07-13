package com.test.service.impl;

import com.test.domain.ChinaInfo;
import com.test.domain.ProvinceInfo;
import com.test.domain.WorldInfo;
import com.test.mapper.GetInfoMapper;
import com.test.service.GetInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取疫情信息的service层实现类
 * @author balance
 */
@Service
public class GetInfoServiceImpl implements GetInfoService {

    @Resource
    private GetInfoMapper getInfoMapper;

    @Override
    public List<ChinaInfo> getChinaInfo() {
        return getInfoMapper.getChinaInfo();
    }

    @Override
    public List<WorldInfo> getWorldInfo() {
        return getInfoMapper.getWorldInfo();
    }

    @Override
    public List<ProvinceInfo> getProvinceInfo(String province) {
        return getInfoMapper.getProvinceInfo(province);
    }
}
