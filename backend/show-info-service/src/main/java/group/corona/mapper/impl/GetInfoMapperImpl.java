package group.corona.mapper.impl;

import group.corona.domain.ChinaInfo;
import group.corona.domain.ProvinceInfo;
import group.corona.domain.WorldInfo;
import group.corona.mapper.GetInfoMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
        Query query = new Query(Criteria.where("latest").is(1));
        return mongoTemplate.find(query, ChinaInfo.class, "CHINA");
    }

    @Override
    public List<WorldInfo> getWorldInfo() {
        Query query = new Query(Criteria.where("latest").is(1));
        return mongoTemplate.find(query, WorldInfo.class, "OTHERS");
    }

    @Override
    public List<ProvinceInfo> getProvinceInfo(String province) {
        Query query = new Query(Criteria.where("parent").is(province).and("latest").is(1));
        return mongoTemplate.find(query, ProvinceInfo.class, "CHINA_DETAIL");
    }
}
