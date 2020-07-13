package com.test.controller;

import com.test.domain.ChinaInfo;
import com.test.domain.WorldInfo;
import com.test.service.GetInfoService;
import com.test.thrift.ServerProvider;
import group.corona.thrift.spider.SpiderService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 获取疫情信息的Controller层实现类
 * @author balance
 */
@Controller
@RequestMapping("/data")
public class GetInfoController {

    @Resource
    private GetInfoService getInfoService;
    @Resource
    private ServerProvider serverProvider;

    @RequestMapping(value = "/world", method = RequestMethod.GET)
    @ResponseBody
    public List<WorldInfo> getWorldInfo(){
        SpiderService.Iface spiderService = serverProvider.getSpiderService();
        try {
            spiderService.updateOthersData();
        } catch (TException e) {
            e.printStackTrace();
            return null;
        }
        List<WorldInfo> worldInfo = getInfoService.getWorldInfo();
        return worldInfo;
    }

}
