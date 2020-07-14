# 数据库设计

## OTHERS
> 其他国家的数据

|属性名|取值|说明|
|:-|:-|:-|
|`id`|int|pk auto_increment|
|`name`|varchar(30)|pk 国家名|
|`confirm`|int|确诊数|
|`dead`|int|死亡人数|
|`cure`|int|治愈人数|
|`date`|varchar(10)|数据对应日期|
|`time`|varchar(8)|数据对应时间|
|`latest`|bool|表示是否为最新数据|

## CHINA
> 中国各省的具体数据

|属性名|取值|说明|
|:-|:-|:-|
|`id`|int|pk auto_increment|
|`name`|varchar(30)|pk 省名|
|`confirm`|int|确诊数|
|`dead`|int|死亡人数|
|`cure`|int|治愈人数|
|`date`|varchar(10)|数据对应日期|
|`time`|varchar(8)|数据对应时间|
|`latest`|bool|表示是否为最新数据|

## CHINA_DETAIL
> 中国每个省内的数据

|属性名|取值|说明|
|:-|:-|:-|
|`parent`|varchar(30)|fk(CHINA.name)|
|`name`|varchar(30)|pk 地区|
|`confirm`|int|确诊数|
|`dead`|int|死亡人数|
|`cure`|int|治愈人数|
|`date`|varchar(10)|数据对应日期|
|`time`|varchar(8)|数据对应时间|
|`latest`|bool|表示是否为最新数据|