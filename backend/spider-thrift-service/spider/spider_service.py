from spider.api.SpiderService import Iface

# 爬虫实现

def main():
    print("hello world")

class SpiderServiceHandler(Iface):
    def updateData(self):
        pass

    def updateChinaData(self):
        pass

    def updateChinaProvienceData(self, provience):
        pass

if __name__ == "__main__":
    main()