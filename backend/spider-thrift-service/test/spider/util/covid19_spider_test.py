from spider.util.covid19_spider import Spider as spider

if __name__ == "__main__":
    cs = spider()
    cs.getProvinceData("湖北")
    cs.getProvinceData("北京")

