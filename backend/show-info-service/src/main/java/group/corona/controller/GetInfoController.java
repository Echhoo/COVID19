package group.corona.controller;

import group.corona.domain.ChinaInfo;
import group.corona.domain.ProvinceInfo;
import group.corona.domain.WorldInfo;
import group.corona.service.GetInfoService;
import group.corona.thrift.ServerProvider;
import group.corona.thrift.spider.SpiderService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 返回世界疫情信息
     * @return
     */
    @RequestMapping(value = "/world", method = RequestMethod.GET)
    @ResponseBody
    public List<WorldInfo> getWorldInfo(){
        System.out.println("开始获取世界信息...");
        SpiderService.Iface spiderService = serverProvider.getSpiderService();
        try {
            System.out.println(spiderService.updateOthersData());
        } catch (TException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("调用python爬虫成功...");
        return getInfoService.getWorldInfo();
    }

    /**
     * 获取中国整体疫情信息
     * @return
     */
    @RequestMapping(value = "/china", method = RequestMethod.GET)
    @ResponseBody
    public List<ChinaInfo> getChinaInfo(){
        System.out.println("开始获取中国信息...");
        SpiderService.Iface spiderService = serverProvider.getSpiderService();
        try {
            System.out.println(spiderService.updateChinaData());
        } catch (TException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("调用python爬虫成功...");
        return getInfoService.getChinaInfo();
    }

    /**
     * 获取指定省的疫情信息
     * @param province: 省份信息
     * @return
     */
    @RequestMapping(value = "/province", method = RequestMethod.POST)
    @ResponseBody
    public List<ProvinceInfo> getProvinceInfo(@RequestParam("province") String province){
        System.out.println("开始获取省份信息");
        SpiderService.Iface spiderService = serverProvider.getSpiderService();
        try {
            System.out.println(spiderService.updateChinaProvienceData(province));
        } catch (TException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("调用python爬虫成功...");
        return getInfoService.getProvinceInfo(province);
    }

}
