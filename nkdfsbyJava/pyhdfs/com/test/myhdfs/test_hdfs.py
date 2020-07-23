#daoru
from hdfs import Client

client = Client("http://master:9870")
# client.makedirs("/abc/xyz")
x = client.list("/")
y = client.list("/", status=True)
print(y[1][0])
print(y[1][1]["accessTime"])


client.upload("/abc", "HDFSDao.py")
client.download("/abc/HDFSDao.py", "d:/ttt.py")

print("end___")