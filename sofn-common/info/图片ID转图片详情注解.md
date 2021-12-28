#### 涉及到两个注解，@ImageTran 和 @DataImage
这两个注解的作用分别是，@ImageTran用在需要把返回结果翻译成图片详情的方法
上，@DataImage用在需要翻译的字段上。

#### @ImageTran用法：
在需要把返回结果翻译成图片详情的方法上加上该注解

示例：
```java
    @ImageTran
    @PostMapping("/getTestInfo")
    public Result getTestInfo() {
        return new Result;
    }
```

#### @DataImage注解用法：
在图片ID的属性上加上@DataImage注解，其中注解中参数isList = true表明是一个
List<String>类型的图片ID集合

示例：
```java
@Data
public static class TestInfo {
    private String name;
    private String nickname;
    private int age;
    @DataImage
    private String headImage;
    private List<TestInfo> testInfo;
    @DataImage(isList = true)
    private List<String> strList;
}
```

#### 翻译后数据：
翻译后不会覆盖原有属性，会新增一个属性，名称为“原属性名_info”,例如下边示例中headImage属性翻译后
会多一个headImage_info

示例：
```
{
	"code": 200,
	"data": {
		"testInfo": [
			{
				"headImage": "11111111111111111",
				"name": "heyongjie",
				"nickname": "hyj",
				"age": 24
			},
			{
				"headImage": "222222222222222",
				"name": "zhouqingchun",
				"nickname": "zcq",
				"age": 25
			}
		],
		"strList": [
			"33333333333333",
			"444444444444444"
		],
		"strList_info": [
			{
                "downloadTimes": 0,
                "fileName": "测试图片文件",
                "filePath": "http://10.0.50.101:80/group1/M00/00/05/CgAyZV3fWVeAJIArAAPLYskd42U908.jpg",
                "fileSize": 248674,
                "fileType": ".jpg",
                "id": "33333333333333",
                "operateTime": 1574918487000,
                "operator": "03",
                "operatorName": "瞿治杰",
                "remark": "string",
                "systemId": "sys",
                "systemName": "系统支撑平台"
            },
			{
                "downloadTimes": 0,
                "fileName": "测试图片文件",
                "filePath": "http://10.0.50.101:80/group1/M00/00/05/CgAyZV3fWVeAJIArAAPLYskd42U908.jpg",
                "fileSize": 248674,
                "fileType": ".jpg",
                "id": "444444444444444",
                "operateTime": 1574918487000,
                "operator": "03",
                "operatorName": "瞿治杰",
                "remark": "string",
                "systemId": "sys",
                "systemName": "系统支撑平台"
            }
		],
		"name": "quzhijie",
		"nickname": "qzj",
		"headImage": "a81365398ca74d718d5b1b25bcc08971",
		"headImage_info": {
			"downloadTimes": 0,
			"fileName": "测试图片文件",
			"filePath": "http://10.0.50.101:80/group1/M00/00/05/CgAyZV3fWVeAJIArAAPLYskd42U908.jpg",
			"fileSize": 248674,
			"fileType": ".jpg",
			"id": "a81365398ca74d718d5b1b25bcc08971",
			"operateTime": 1574918487000,
			"operator": "03",
			"operatorName": "瞿治杰",
			"remark": "string",
			"systemId": "sys",
			"systemName": "系统支撑平台"
		},
		"age": 20
	},
	"msg": ""
}
```
