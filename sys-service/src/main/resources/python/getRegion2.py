import requests
# pip3 install bs4
# 这个是parentIds不包含自己的代码
from bs4 import BeautifulSoup

r = requests.get('http://www.mca.gov.cn///article/sj/xzqh/2020/2020/2020072805001.html')
soup = BeautifulSoup(r.text, 'html.parser', from_encoding='utf-8')

'''
# 这是多行注释
regionCode   区划代码
regionName   区划名称
parentId     区划的父ID
parentIds    区划的树结构 如/id/aaa
parentNames  区划的名称结构   如/行政区划/北京市

'''


def getRegionInfo(region_code, region_name, parentId, parent_ids, parent_names):
    region_info = {"regionCode": region_code, "regionName": region_name, "parentId": parentId, "parentIds": parent_ids,
                   "parentNames": parent_names, "id": region_code}
    return region_info


'''
获取虚拟的行政区划
provinceCode   省的区划
regionName     省的名称
name           虚拟区划名称
code           虚拟区划的代码     0100  市辖区   0200  县
parentNames  区划的名称结构   如/行政区划/北京市

'''


def getVmRegionInfo(province_code, province_name, name, code):
    regionCodePrefix = province_code[0:2]
    realRegionCode = regionCodePrefix + code
    parentIds = SPLIT_STR + ROOT_NODE_CODE + SPLIT_STR + province_code
    parentNames = SPLIT_STR + ROOT_NODE_NAME + SPLIT_STR + province_name
    regionInfo = {
        "regionCode": realRegionCode, "regionName": name, "parentId": province_code, "parentIds": parentIds,
        "parentNames": parentNames, "id": realRegionCode
    }
    return regionInfo


'''
    将数据写入Sql文件
'''


def contentInputFilt(data_list):
    f = open('C:/Users/heyongjie/Desktop/insertRegion.sql', 'w', encoding='utf8')
    f.write('truncate table sys_region_new;')
    tempSql = '''
        insert into sys_region_new(
                    ID                  ,
                    PARENT_ID           ,
                    REGION_NAME         ,
                    REGION_CODE         ,
                    CREATE_USER_ID      ,
                    CREATE_TIME         ,
                    DEL_FLAG           ,
                    PARENT_IDS,
                    PARENT_NAMES
                )VALUES (
                    '%s',
                    '%s',
                    '%s',
                    '%s',
                    '%s',
                    SYSDATE,
                    'N',
                    '%s',
                    '%s'
                );
    '''
    f.write(tempSql % (ROOT_NODE_CODE, '0', ROOT_NODE_NAME, ROOT_NODE_CODE, '05', '/', '/'))
    for data in data_list:
        s = tempSql % (data["id"], data["parentId"], data["regionName"], data["id"], '05', data["parentIds"], data["parentNames"])
        f.write(s)
    f.close()

# 根节点的名称
ROOT_NODE_NAME = '行政区划'
# 根节点的ID
ROOT_NODE_CODE = '100000'
# ID之间的分隔符
SPLIT_STR = '/'

# #\32 020年2月份县以上行政区划代码_8204 > table > tbody > tr:nth-child(4) > td:nth-child(2)
# #\32 020年2月份县以上行政区划代码_8204 > table > tbody > tr:nth-child(4) > td:nth-child(2)
# trs = soup.select('#\32 020年2月份县以上行政区划代码_8204 > table > tbody > tr:nth-child(4) > td:nth-child(2)')
trs = soup.find_all('tr')
# 所有区县数据
data_list = []
# 省的名称
provinceName = ''
# 省的代码
provinceCode = ''
# 市的代码
cityCode = ''
# 市的名称
cityName = ''
# 是否已经创建虚拟区划
flag = False

#
for tr in trs:
    tds = tr.find_all('td')
    # 区划代码
    # print(tds)
    try:
        spanstr = tds[2].find_all('span')
        if len(spanstr) != 0:
            # 市和县
            spaceStr = spanstr[0].contents[0]
            spaceNum = 1
            for s in spaceStr:
                if s == ' ':
                    spaceNum += 1
            # 如果一个空格表示市  二个空格表示县
            # <td class="xl738204"><span style="mso-spacerun:yes">   </span>图木舒克市</td>
            regionCode = tds[1].contents[0]
            regionName = tds[2].contents[1]
            if regionCode == '行政区划代码':
                continue
            if spaceNum == 1:
                # 市
                cityName = regionName
                cityCode = regionCode
                parentIds = SPLIT_STR + ROOT_NODE_CODE + SPLIT_STR + provinceCode
                parent_names = SPLIT_STR + ROOT_NODE_NAME + SPLIT_STR + provinceName
                regionInfo = getRegionInfo(cityCode, cityName, provinceCode, parentIds, parent_names)
                data_list.append(regionInfo)
            elif spaceNum == 2:
                # 县
                # 判断市的code是否为空 如果为空就直接生成二个虚拟区划的名称
                # 0100 市辖区  0200：县
                if cityCode == '' and flag == False:
                    region1 = getVmRegionInfo(provinceCode, provinceName, "市辖区", "0100")
                    region2 = getVmRegionInfo(provinceCode, provinceName, "县", "0200")
                    flag = True
                    data_list.append(region1)
                    data_list.append(region2)
                if cityCode == '':
                    parentIdPrefix = provinceCode[0:2]
                    if '县' in regionName:
                        # 应该放置在虚拟区划县中
                        parentIds = SPLIT_STR + ROOT_NODE_CODE + SPLIT_STR + provinceCode + SPLIT_STR + parentIdPrefix + "0200"
                        parent_names = SPLIT_STR + ROOT_NODE_NAME + SPLIT_STR + provinceName + SPLIT_STR + '县'
                        regionInfo = getRegionInfo(regionCode, regionName, parentIdPrefix + '0200', parentIds,
                                                   parent_names)
                        data_list.append(regionInfo)
                    else:
                        # 应该放置在虚拟区划市中
                        parentIds = SPLIT_STR + ROOT_NODE_CODE + SPLIT_STR + provinceCode + SPLIT_STR + parentIdPrefix + "0100"
                        parent_names = SPLIT_STR + ROOT_NODE_NAME + SPLIT_STR + provinceName + SPLIT_STR + '市辖区'
                        regionInfo = getRegionInfo(regionCode, regionName, parentIdPrefix + '0100', parentIds,
                                                   parent_names)
                        data_list.append(regionInfo)
                else:
                    parentIds = SPLIT_STR + ROOT_NODE_CODE + SPLIT_STR + provinceCode + SPLIT_STR + cityCode
                    parent_names = SPLIT_STR + ROOT_NODE_NAME + SPLIT_STR + provinceName + SPLIT_STR + cityName

                    regionInfo = getRegionInfo(regionCode, regionName, cityCode, parentIds,
                                               parent_names)
                    data_list.append(regionInfo)
            else:
                print(12343)
        else:
            # 省
            provinceCode = tds[1].contents[0]
            provinceName = tds[2].contents[0]
            parentIds = SPLIT_STR + ROOT_NODE_CODE
            parent_names = SPLIT_STR + ROOT_NODE_NAME
            regionInfo = getRegionInfo(provinceCode, provinceName, ROOT_NODE_CODE, parentIds, parent_names)
            data_list.append(regionInfo)
            # 重置信息
            cityName = ''
            cityCode = ''
            flag = False
    except:
        print(tds)

contentInputFilt(data_list)


