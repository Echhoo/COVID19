# 声明 python 包名
namespace py spider.api
# 声明 java 包名
namespace java group.corona.thrift.spider

service SpiderService {
    # 刷新其他国家的数据，并将数据储存到数据库中
    bool updateOthersData();

    # 刷新中国各省的数据
    bool updateChinaData();

    # 刷新国内某省的数据
    bool updateChinaProvienceData(1:string provience);
}