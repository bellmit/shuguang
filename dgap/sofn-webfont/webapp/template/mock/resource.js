export const resource = {
    "menu": {
        "data": [{
            "id": 1,
            "text": "",
            "children": [{
                "id": 12,
                "text": "资源目录管理",
                "state": "closed",
                "children": [{
                    "id": 121,
                    "text": "资源目录",
                    "menuUrl": "/#/index/content/dgap/resource/resourceDir"
                }, {
                    "id": 122,
                    "text": "资源管理",
                    "menuUrl": "/#/index/content/dgap/resource/resourceMa",
                    "attributes": {
                        "p1": "Custom Attribute1",
                        "p2": "Custom Attribute2"
                    }
                }, {
                    "id": 181,
                    "text": "资源发布",
                    "menuUrl": "/webapp/dgap/resource_operation/structure"
                }, {
                    "id": 123,
                    "text": "资源权限设置",
                    "menuUrl": "/#/index/content/dgap/resource/resourceRole"
                }, {
                    "id": 124,
                    "text": "资源申请",
                    "checked": true,
                    "menuUrl": "/#/index/content/dgap/resource/dgapResourceApplication"
                }, {
                    "id": 125,
                    "text": "资源审批",
                    "menuUrl": "/#/index/content/dgap/application/resourceApplication"
                }]
            }, {
                "id": 13,
                "text": "服务监控",
                "state": "closed",
                "children": [{
                    "id": 131,
                    "text": "预警规则管理",
                    "menuUrl": "/#/index/content/dgap/alertConfig/alertConfig"
                }, {
                    "id": 132,
                    "text": "预警信息管理",
                    "menuUrl": "/#/index/content/dgap/alertConfig/alertConfigLog"
                }]
            }, {
                "id": 14,
                "text": "数据统计",
                "state": "closed",
                "children": [{
                    "id": 141,
                    "text": "服务调度统计",
                    "menuUrl": "/#/index/content/dgap/wsStat/dailyStat"
                }, {
                    "id": 142,
                    "text": "服务出错统计",
                    "menuUrl": "/#/index/content/dgap/wsStat/errorStat"
                }]
            }]
        }],
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1479977068500
    },
    "list": {
        "data": {
            "pageNum": 0,
            "pageSize": 0,
            "size": 0,
            "orderBy": null,
            "startRow": 0,
            "endRow": 0,
            "total": 7,
            "pages": 0,
            "list": [{
                "DESCRIPTION": "asdfasdf",
                "ID": "aa68766ca4584cad90a283aa0774f0c2589f14206d57442397951ef8d5f82172",
                "UPDATE_TIME": 1479361749000,
                "RN": 5,
                "NAME": "adfadfadf"
            }, {
                "DESCRIPTION": "asdfasdf",
                "ID": "9e95005e68b849cab916e1fad859f9f6196f35926c3b41858b824afd4a47a6be",
                "UPDATE_TIME": 1479361744000,
                "RN": 4,
                "NAME": "adfadsf"
            }, {
                "DESCRIPTION": "sdfgsdfgsdfg",
                "ID": "384bddd0bce0414190c75dfa3fdff611eb22bb4234364e2296873b716dbea2de",
                "UPDATE_TIME": 1479361371000,
                "RN": 3,
                "NAME": "stst"
            }, {
                "DESCRIPTION": "sdfgsdfgsdfg",
                "ID": "35fe711e5031491f8e4ec9199bdb973cb065f7be735745d39f4fb847c2e4e3e0",
                "UPDATE_TIME": 1479361360000,
                "RN": 2,
                "NAME": "sdfgsdfg"
            }, {
                "DESCRIPTION": "rturtu",
                "ID": "9bfaff9ac5e04d1992e457b94d2d13208048735fbefc48b498984f15deee4871",
                "UPDATE_TIME": 1479361353000,
                "RN": 1,
                "NAME": "ythrt"
            }, {
                "DESCRIPTION": "1212",
                "ID": "4f279d7cff11447a8d3dc1a6dde198e9f287e9161bac4850aeccb57597f78834",
                "UPDATE_TIME": 1479263313000,
                "RN": 7,
                "NAME": "去去去去"
            }, {
                "DESCRIPTION": "ww",
                "ID": "1d99557c173541c68e3f62a620e1c34c907e6460014c4d88afae4cafc5d644f3",
                "UPDATE_TIME": 1479262241000,
                "RN": 6,
                "NAME": "qq"
            }],
            "firstPage": 0,
            "prePage": 0,
            "nextPage": 0,
            "lastPage": 0,
            "isFirstPage": false,
            "isLastPage": false,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 0,
            "navigatepageNums": null
        },
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1479977068500
    },
    "fields": {
        "data": [{
            "id": "1",
            "remark": null,
            "createBy": "2",
            "createTime": null,
            "updateBy": "1",
            "updateTime": 1479950261000,
            "delFlag": "N",
            "chineseName": "姓名",
            "englishName": "name",
            "type": "varchar",
            "len": "64",
            "dataImportTableId": "1",
            "createDate": 1472001445000,
            "reservedField1": null,
            "reservedField2": null,
            "reservedField3": null,
            "reservedField4": null,
            "reservedField5": null,
            "reservedField6": null,
            "reservedField7": null,
            "reservedField8": null,
            "reservedField9": null,
            "reservedField10": null,
            "reservedField11": null,
            "reservedField12": null,
            "reservedField13": null,
            "reservedField14": null,
            "reservedField15": null,
            "reservedField16": null,
            "reservedField17": null,
            "reservedField18": null,
            "reservedField19": null,
            "reservedField20": null
        }, {
            "id": "2",
            "remark": null,
            "createBy": "2",
            "createTime": null,
            "updateBy": "2",
            "updateTime": 1479951177000,
            "delFlag": "N",
            "chineseName": "年龄",
            "englishName": "age",
            "type": "integer",
            "len": "20",
            "dataImportTableId": "1",
            "createDate": 1479951174000,
            "reservedField1": null,
            "reservedField2": null,
            "reservedField3": null,
            "reservedField4": null,
            "reservedField5": null,
            "reservedField6": null,
            "reservedField7": null,
            "reservedField8": null,
            "reservedField9": null,
            "reservedField10": null,
            "reservedField11": null,
            "reservedField12": null,
            "reservedField13": null,
            "reservedField14": null,
            "reservedField15": null,
            "reservedField16": null,
            "reservedField17": null,
            "reservedField18": null,
            "reservedField19": null,
            "reservedField20": null
        }],
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1480661680061
    },
    "example": {
        "data": {
            "fileds": [{
                "title": "姓名",
                "dataIndex": "NAME"
            }, {
                "title": "年龄",
                "dataIndex": "AGE"
            }],
            "table": [{
                "NAME": "1",
                "AGE": 1
            }, {
                "NAME": "2",
                "AGE": 2
            }, {
                "NAME": "3",
                "AGE": 4
            }]
        },
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1480922579061
    },
    "tables": {
        "data": [{
            "id": "0",
            "text": "仓库表",
            "children": [{
                "id": "1",
                "text": "T_USER-用户表",
                "children": []
            }]
        }],
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1480930986691
    },
    "success": {
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1480922579061
    },
    "error": {
        "httpCode": 500,
        "msg": "请求失败",
        "timestamp": 1480922579061
    },
    "fieldType": {
        "data": [{
            "minLength": 1,
            "name": "文本",
            "description": "",
            "type": "varchar",
            "hasLength": true,
            "maxLength": 2000
        }, {
            "minLength": 1,
            "name": "数值",
            "description": "",
            "type": "bigint",
            "hasLength": true,
            "maxLength": 255
        }, {
            "minLength": 1,
            "name": "小数",
            "description": "包含整数部分和小数部分,形如5.2",
            "type": "number",
            "hasLength": true,
            "maxLength": 38
        }, {
            "minLength": null,
            "name": "日期",
            "description": "包含年月日的信息，形如2016-12-05",
            "type": "date",
            "hasLength": false,
            "maxLength": null
        }, {
            "minLength": null,
            "name": "时间",
            "description": "包含时分秒的信息，形如2016-12-05 13:10:10 ",
            "type": "time",
            "hasLength": false,
            "maxLength": null
        }, {
            "minLength": null,
            "name": "时间戳",
            "description": "1481011781227",
            "type": "timestamp",
            "hasLength": false,
            "maxLength": null
        }],
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1480922579061
    },
    "page": {
        "data": {
            "fields": [{
                "title": "年龄",
                "dataIndex": "AGE"
            }, {
                "title": "姓名",
                "dataIndex": "NAME"
            }],
            "table": [{
                "AGE": 1,
                "NAME": "1"
            }, {
                "AGE": 2,
                "NAME": "2"
            }, {
                "AGE": 4,
                "NAME": "3"
            }]
        },
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1481190400617
    },
    "resourceDir": {
        "data": [{
            "name": "xxx1",
            "description": "vvvvvvvv",
            "id": "b2847fea38fa4228a49ec2852f82942afe4eb880c37a4b81a4cdeb6984b62aa1"
        }, {
            "name": "44",
            "description": "5545",
            "id": "85ceb97effd946199f449910b32d2f325322504988104952afda668a3149e88c"
        }, {
            "name": "导航页",
            "description": "常用链接",
            "id": "964fe30e137941e4967734960c24849e2ceb7d6a47f34f209f91775376064a9f"
        }, {
            "name": "输入法",
            "description": "输入的方式",
            "id": "3537d33805af44b8be7f14c271de95a9f87c1080323c446e8fd6a4531238b9c9"
        }, {
            "name": "操作日志",
            "description": "系统操作记录",
            "id": "065cbbfc00fd4b50b7a4de920ae7bdec8d7308d7e8ca4493a3ca418c5c86c1f8"
        }, {
            "name": "阿凡达是的",
            "description": "阿斯蒂芬啊",
            "id": "fc8e733adbf1416892c77cd108a6eee2635c0721b2fe4c2eb287aa8fab7b9ef1"
        }, {
            "name": "JSF",
            "description": "SDF",
            "id": "08dec47832214ef59b3fa40656a1081805b665d4cda94c029c97c736aceb8cd7"
        }, {
            "name": "AAA",
            "description": "AAA",
            "id": "613cecd4e16348549059f9bab374435f24cdf187a0dc4edeafb06e78def61abd"
        }, {
            "name": "VVV",
            "description": "VVV",
            "id": "d9f9fbd0c0054ae2bdede5029e0146f692bde602211748988a7d9ca1bc6b8687"
        }],
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1481247548832
    },
    "dataList": {
        "data": {
            "pageNum": 1,
            "pageSize": 0,
            "size": 0,
            "orderBy": null,
            "startRow": 0,
            "endRow": 0,
            "total": 22,
            "pages": 0,
            "list": [{
                "ID": "1",
                "USERNAME": "张珊",
                "PASSWORD": "123456",
                "JOB": "java",
                "STATUS": "Y",
                "RN": 1
            }, {
                "ID": "2",
                "USERNAME": "李四",
                "PASSWORD": "123456",
                "JOB": "java",
                "STATUS": "N",
                "RN": 2
            }, {
                "ID": "3",
                "USERNAME": "王五",
                "PASSWORD": "456789",
                "JOB": "php",
                "STATUS": "N",
                "RN": 3
            }, {
                "ID": "4",
                "USERNAME": "李柳",
                "PASSWORD": "32323",
                "JOB": "Java",
                "STATUS": "N",
                "RN": 4
            }, {
                "ID": "5",
                "USERNAME": "但是",
                "PASSWORD": "223333",
                "JOB": "ui",
                "STATUS": "Y",
                "RN": 5
            }, {
                "ID": "6",
                "USERNAME": "主管",
                "PASSWORD": "33344",
                "JOB": "Java",
                "STATUS": "Y",
                "RN": 6
            }, {
                "ID": "7",
                "USERNAME": "李武",
                "PASSWORD": "677",
                "JOB": "java",
                "STATUS": "Y",
                "RN": 7
            }, {
                "ID": "8",
                "USERNAME": "追溯",
                "PASSWORD": "44444",
                "JOB": "java",
                "STATUS": "Y",
                "RN": 8
            }, {
                "ID": "9",
                "USERNAME": "王二",
                "PASSWORD": "44334",
                "JOB": "java",
                "STATUS": "Y",
                "RN": 9
            }, {
                "ID": "10",
                "USERNAME": "赵伟",
                "PASSWORD": "4555",
                "JOB": "psp",
                "STATUS": "Y",
                "RN": 10
            }],
            "firstPage": 0,
            "prePage": 0,
            "nextPage": 0,
            "lastPage": 0,
            "isFirstPage": false,
            "isLastPage": false,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 0,
            "navigatepageNums": null
        },
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1481284002515
    },
    "fieldList": {
        "data": [{
            "id": "444c83319bd04ff89899b7dc11c45d4b7e384ddfb5d84028ba973ad3ec26b8d0",
            "remark": null,
            "createBy": "admin",
            "createTime": null,
            "updateBy": "admin",
            "updateTime": 1481248701000,
            "delFlag": "N",
            "chineseName": "职业",
            "englishName": "JOB",
            "type": "varchar",
            "len": "64",
            "dataImportTableId": "2",
            "createDate": 1481248701000,
            "reservedField1": null,
            "reservedField2": null,
            "reservedField3": null,
            "reservedField4": null,
            "reservedField5": null,
            "reservedField6": null,
            "reservedField7": null,
            "reservedField8": null,
            "reservedField9": null,
            "reservedField10": null,
            "reservedField11": null,
            "reservedField12": null,
            "reservedField13": null,
            "reservedField14": null,
            "reservedField15": null,
            "reservedField16": null,
            "reservedField17": null,
            "reservedField18": null,
            "reservedField19": null,
            "reservedField20": null,
            "typeName": "文本"
        }, {
            "id": "7727bca922c54f6eaca5d18815f6be14a9bd576c337b46febffff524caecf05d",
            "remark": null,
            "createBy": "admin",
            "createTime": null,
            "updateBy": "admin",
            "updateTime": 1481248651000,
            "delFlag": "N",
            "chineseName": "用户名",
            "englishName": "USERNAME",
            "type": "varchar",
            "len": "64",
            "dataImportTableId": "2",
            "createDate": 1481248651000,
            "reservedField1": null,
            "reservedField2": null,
            "reservedField3": null,
            "reservedField4": null,
            "reservedField5": null,
            "reservedField6": null,
            "reservedField7": null,
            "reservedField8": null,
            "reservedField9": null,
            "reservedField10": null,
            "reservedField11": null,
            "reservedField12": null,
            "reservedField13": null,
            "reservedField14": null,
            "reservedField15": null,
            "reservedField16": null,
            "reservedField17": null,
            "reservedField18": null,
            "reservedField19": null,
            "reservedField20": null,
            "typeName": "文本"
        }, {
            "id": "a9f884be9e704c05aee937cbde9d1c1d93ccd8086a8c4c8195acb3089717490a",
            "remark": null,
            "createBy": "admin",
            "createTime": null,
            "updateBy": "admin",
            "updateTime": 1481248680000,
            "delFlag": "N",
            "chineseName": "密码",
            "englishName": "PASSWORD",
            "type": "varchar",
            "len": "64",
            "dataImportTableId": "2",
            "createDate": 1481248680000,
            "reservedField1": null,
            "reservedField2": null,
            "reservedField3": null,
            "reservedField4": null,
            "reservedField5": null,
            "reservedField6": null,
            "reservedField7": null,
            "reservedField8": null,
            "reservedField9": null,
            "reservedField10": null,
            "reservedField11": null,
            "reservedField12": null,
            "reservedField13": null,
            "reservedField14": null,
            "reservedField15": null,
            "reservedField16": null,
            "reservedField17": null,
            "reservedField18": null,
            "reservedField19": null,
            "reservedField20": null,
            "typeName": "文本"
        }],
        "httpCode": 200,
        "msg": "请求成功",
        "timestamp": 1481265015396
    }
}