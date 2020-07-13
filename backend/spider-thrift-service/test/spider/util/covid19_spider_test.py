from spider.util.covid19_spider import Spider as spider

if __name__ == "__main__":
    cs = spider()
    soup = cs.makeSoup()
    print(soup.find('span', attrs={"class": "area"}, text="北京").parent.parent)

