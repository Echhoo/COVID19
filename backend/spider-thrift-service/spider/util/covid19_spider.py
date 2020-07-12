# beautifulsoup
from bs4 import BeautifulSoup
# requests
import requests
# 链接 mongodb
import pymongo
# 正则表达式
import re

class ConnectException(Exception):
    """
    连接到网站时的异常
    """
    def __init__(self, msg="连接异常！"):
        super(ConnectException, self).__init__(msg)

class NoDataException(Exception):
    """
    获取数据失败的异常
    """
    def __init__(self, msg="当日无数据！"):
        super(NoDataException, self).__init__(msg)


class Spider:

    BASE_URL = "http://m.sinovision.net/newpneumonia.php"
    client = pymongo.MongoClient(host='101.200.189.12', port=27017)
    db = client['COVID19Spider']
    CHINA_TABLE = "CHINA"
    CHINA_DETAIL_TABLE = "CHINA_DETAIL"
    OTHER_TABLE = "OTHERS"
    soup = None

    def __init__(self):
        # ('DEFAULT', 'MONGODB-X509', 'PLAIN', 'MONGODB-CR', 'SCRAM-SHA-256', 'SCRAM-SHA-1', 'GSSAPI')
        # self.client.COVID19Spider.authenticate(name="admin", password="admin")
        pass

    def makeSoup(self):
        """
        链接url，做一锅汤
        :return: BeautifulSoup
        """
        req = requests.get(self.BASE_URL, headers={"User-Agent": "Mozilla5.0"})
        if req.status_code != 200:
            # 请求失败时报错
            raise ConnectException
        self.soup = BeautifulSoup(req.text, 'lxml')
        return self.soup

    def getOtherCountriesData(self, countries, date, time):
        """
        将所有其他国家的书存入数据库
        :param countries: 所有其他国家的Tag类型list
        :return:存有所有数据的dir list
        """
        return [{
            "country": country.contents[1].text,
            "confirm": int(country.contents[3].text),
            "dead": int(country.contents[5].text),
            "cure": int(country.contents[7].text),
            "date": date,
            "time": time
        } for country in countries]

    def getOtherCountries(self):
        """
        封装的获取其他数据
        :return:
        """
        # 首先验证有没有今天的数据
        title = self.soup.find('span', class_="today-title", text="全球疫情")
        if title is None:
            raise NoDataException

        # 确定数据更新的日期
        date_element = title.find_next_sibling('span', attrs={"class": "today-time"}) \
                            .find_next_sibling('span', attrs={"class": "today-time"})
        regex = re.compile(r'\d{4}-\d{2}-\d{2}')
        date = str(regex.search(date_element.text).group())
        regex = re.compile(r'\d{2}:\d{2}:\d{2}')
        time = str(regex.search(date_element.text).group())

        # 判断数据库中是否有当日数据
        database = self.db[self.OTHER_TABLE]
        exists = database.find_one({"date": date})
        if exists is None:
            # 当日数据不存在, 则需要插入
            # 获取所有其他国家数据的Tag list
            countries = title.find_all_next('div', attrs={"class": "prod"})
            data = self.getOtherCountriesData(countries, date, time)
            try:
                database.insert_many(data)
            except Exception as e:
                print("Something Wrong!", e)
                return False
            return True
        else:
            # 当日数据存在, 则需要具体判断
            exists = database.find_one({"time": time})
            if exists is None:
                # 当日数据存在, 但更新时间不同，则需要更新
                countries = title.find_all_next('div', attrs={"class": "prod"})
                data = self.getOtherCountriesData(countries, date, time)
                try:
                    database.delete_many({"date": date})
                    database.insert_many(data)
                except Exception as e:
                    print("Something Wrong!", e)
                    return False
                return True
            else:
                # 当日数据存在且数据更新时间相同
                return True