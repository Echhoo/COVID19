package com.test.mapper.impl;

import com.test.domain.ChinaInfo;
import com.test.domain.ProvinceInfo;
import com.test.domain.WorldInfo;
import com.test.mapper.GetInfoMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 获取用户信息的dao层实现类
 * @author balance
 */
@Component
public class GetInfoMapperImpl implements GetInfoMapper {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public List<ChinaInfo> getChinaInfo() {
        Date curDate = new Date();
        DateFormat df = new SimpleDateFormat("yy-MM-dd");
        String curDateString = df.format(curDate);
        Query query = new Query(Criteria.where("date").is(curDateString));
        return mongoTemplate.find(query, ChinaInfo.class, "CHINA");
    }

    @Override
    public List<WorldInfo> getWorldInfo() {
        return mongoTemplate.findAll(WorldInfo.class, "OTHERS");
    }

    @Override
    public List<ProvinceInfo> getProvinceInfo(String province) {
        Query query = new Query(Criteria.where("parent").is(province));
        return mongoTemplate.find(query, ProvinceInfo.class, "CHINA_DETAIL");
    }
}
