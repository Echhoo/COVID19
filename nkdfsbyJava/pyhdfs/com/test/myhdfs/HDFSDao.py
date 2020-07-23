from hdfs import Client

class HDFSDao(object):
    def __init__(self,url):
        self.url=url

    def get_client(self):
        client =Client(self.url)
        return client


