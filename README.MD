### 开发规范 
详细参考《阿里巴巴Java开发规范》
> #### 命名
* 常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
* 杜绝完全不规范的缩写，避免望文不知义
* Service/DAO 层方法命名规约
  * 获取单个对象的方法用 `get` 做前缀。
  * 获取多个对象的方法用 `list` 做前缀。
  * 获取统计值的方法用 `count` 做前缀。
  * 插入的方法用 `save/insert` 做前缀。
  * 删除的方法用 `remove/delete` 做前缀。
  * 修改的方法用 `update` 做前缀。

> #### 接口
* 接口定义需遵循REST风格
  * GET方法用来获取资源
  * POST方法可用来创建一个资源
  * PUT方法可用来更新资源
  * DELETE方法用于删除资源
  
> #### 权限
* 权限标识定义，基本规则为“服务简写：模块名：接口权限名”，使用冒号作为分隔符,例如系统管理服务里的新增删除用户
    ```
    # 新增用户
    sys:user:add
    
    # 删除用户
    sys:user:delete
    ```
  
> #### 鉴权
* 访问/user/login 接口传递username、password获取token
   ```
   POST http://localhost:7999/user/login
   
   参数：
   {
   	"username":"admin",
   	"password":"admin",
   	"captchaId":"",
   	"captcha":"1234"
   }
   ```
* 其他接口访问需在http请求header中携带token
    ```
    GET http://localhost:7999/test/sayHello
    
    header:
    
    Authorization: 登录接口返回的token值
    ``` 
    
> #### Swagger配置
* 若服务依赖了sofn-common公用库，只需在配置文件application-*.yml中加入
    如下配置即可
    ```
    # swagger配置
    swagger:
      # 扫描包
      base-package: com.sofn.sys.web
      # 名称
      name: 系统管理
      # 接口前缀
      prefix: /sys
    ```
> #### Excel导入导出工具
* 如果服务依赖了sofn-common公用库，只需要在类上添加相关注解就可以使用Excel工具了
* Ecxcel测试方法请看com.sofn.common.excel.test.ExcelUtilTest 类
* Excel实体类请参考com.sofn.common.excel.test.testimport.ImportUserBean

> #### FastFileUtil 文件上传下载工具
* 项目中所有文件使用FastDFS进行存储，使用FastFileUtil文件上传下载工具需要依赖sofn-common公用库
* 需在fastdfs.properties配置文件中添加相关配置
*  FastFileUtil提供如下方法
```java
    public class FastFileUtil{
   
        /**
        *  上传文件并返回文件信息
        */
        CommonSysFile updateFile(MultipartFile multipartFile);
        
         /**
         * 批量上传文件  当有文件上传失败时，会删除已经上传的文件
         * @param files 文件集合  
         * @return 上传文件后获得的文件信息
         */
       public static List<CommonSysFile> batchUploadFile(List<MultipartFile> files);
       
       /**
        * 获取文件绝对路径
        * @param id  要获取的文件ID   
        * @return http://127.0.0.1:8080/filePath
        */ 
       public static String getFileAbsolutePath(String id) ;
       
       /**
        * 下载文件
        * @param id 要下载的文件ID
        * @return  fileName -> string 文件名称
        *          fileInfo -> byte[] 文件内容
        */
       public static Map<String,Object> downloadFile(String id);
       
       /**
        * 批量下载文件，最终获得一个压缩包
        * @param strIds  待下载文件集合 
        * @return  压缩包的路径 
        */
        public static String batchDownloadFileByZip(String strIds)throws Exception;
        
        /**
         * 删除文件
         * @param id  待删除文件ID
         */
        public static void deleteFile(String id);
       
    }
```

* -------------
