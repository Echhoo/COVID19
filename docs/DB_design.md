# 数据库设计

## OTHERS
> 其他国家的数据

|属性名|取值|说明|
|:-|:-|:-|
|`id`|int|pk auto_increment|
|`country`|varchar(30)|pk 国家名|
|`confirm`|int|确诊数|
|`dead`|int|死亡人数|
|`cure`|int|治愈人数|
|`date`|varchar(8)|数据对应日期|

## CHINA
> 中国各省的具体数据

|属性名|取值|说明|
|:-|:-|:-|
|`id`|int|pk auto_increment|
|`city`|varchar(30)|pk 省名|
|`confirm`|int|确诊数|
|`dead`|int|死亡人数|
|`cure`|int|治愈人数|
|`date`|varchar(8)|数据对应日期|

## CHINA_DETAIL
> 中国每个省内的数据

|属性名|取值|说明|
|:-|:-|:-|
|`p_id`|int|fk(CHINA.id)|
|`area`|varchar(30)|pk 地区|
|`confirm`|int|确诊数|
|`dead`|int|死亡人数|
|`cure`|int|治愈人数|
|`date`|varchar(8)|数据对应日期|