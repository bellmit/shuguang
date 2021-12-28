## Excel导入导出工具使用方法

### 1. 实体Bean定义

```java
@Data
@ExcelSheetInfo(title = "用户Title",sheetName = "用户Sheet")
public class ImportUserBean {
    @ExcelField(title = "用户名")
    @ExcelImportCheck(isNull =  false,max = 20,min = 1,errMsg = "用户名在1到20之间")
    private String username;

    @ExcelField(title = "密码",type = ExcelField.Type.IMPORT)
    @ExcelImportCheck(isNull =  false)
    private String password;

    @ExcelField(title = "性别",dictType = "sex" )
    private String sex;

    @ExcelField(title = "年龄",dataFormat = "0")
    @ExcelImportCheck(max = 150,min = 0,errMsg = "年龄最大150岁")
    private Integer age;

    @ExcelField(title = "出生日期",dataFormat = "yyyy-MM")
    @ExcelImportCheck(min = 1700,max = 9999,errMsg = "日期不合法,合法日期为1700-9999年")
    private Date birthDay;

    @ExcelField(title = "余额",dataFormat = "0.00")
    @ExcelImportCheck(max = 999999999,errMsg = "余额最大值999999999")
    private Double balance;

    @ExcelField(title = "总价",dataFormat = "0.000")
    @ExcelImportCheck(max = 999999999,errMsg = "总价最大值999999999")
    private BigDecimal totalPrice;

    @ExcelFields(clazz = ImportUserAreaBan.class)
    private ImportUserAreaBan importUserAreaBan;
}
```

如上实体Bean定义

### 2. 注解解释

#### @Data

使用lomback插件生成get set等方法

#### @ExcelSheetInfo

title ： Excel标题

sheetName: sheet名称

生成后效果如下：

![](picture\ExcelSheetInfo效果.png)

#### @ExcelField 

title: 导出列标题，如上图的用户名、密码、性别等

sort :字段排序 ，默认按照字段声明顺序读取Excel,可自定义

type:导入导出类型   Type.ALL 该字段导入导出都要使用（默认）   Type.EXPORT  该字段仅在导出时使用  Type.IMPORT 该字   段仅在导入时使用

align :导出时值得位置 LEFT、CENTER（默认）、RIGHT、AUTO

width :指定导出列宽（以字符宽度的1/256为单位，假如你想显示5个字符的话，就可以设置5*256）

column:  导入时指定列索引，如果这个属性设置了，那么就固定读取这一列的数据

dictType: 字典类型，在导出的时候会自动生成下拉框让选择，如Bean声明中sex字段，效果如下：

![](picture\dictType效果.png)

dataFormat:数据类型，这个字段填写后，会自动对用户输入的数据做格式化，如Bean定义中的出生日期、余额、总价：效果如下：再导入数据的时候获取的是格式化后的值

![](picture\dataFormat效果.png)

![](picture\dataFormat效果2.png)

#### @ExcelImportCheck

isNull: 这个属性Excel中不会有提示，但在导入的时候会校验，false 为不可为空

max: 字符串时为最大长度，数值时为最大值   为日期时满足格式：年月日，如：20190225;年月,如201902；年2019,其余都不满足

min :  字符串时为最小长度，数值时为最小值   为日期时满足格式：年月日，如：20190225;年月,如201902；年2019,其余都不满足

errMsg: 错误提示消息

下面根据最上面的Bean定义展示几个效果图：

![](picture\CheckImport效果图1.png)

![](picture\CheckImport效果图2.png)

![](picture\CheckImport效果图3.png)

![](picture\CheckImport效果图4.png)

![](picture\CheckImport效果图5.png)

#### @ExcelFields

标识包含属性是对象

class : 当前对象的类信息

### 3. 说明

目前工具还不支持日期格式为纯年份的数据，因为Excel如果设置为日期格式，输入如2015会自动变为1905/7/7，所以如果要纯年份，可能需要使用字符串类型判断了

### 4. 提供的方法

#### 导出模板  ExcelExport.class

```java
/**
     * 创建模板  创建模板的时候可以导出Type.All 和Type.IMPORT
     * @param filePath  文件生成路径
     * @param cls    Excel注解类
     * @param <E>  泛型
     */
public static <E> void createTemplate(String filePath, Class cls)
```

#### 读取Excel      ExcelImport.class

```java
   /**
     * 导入文件获取数据
     * @param filePath    文件路径  C:\Users\heyongjie\Desktop\testReadExcel.xlsx
     * @param headerNum   使用了类信息来导出的模板，程序会自动判断，所以传入-1就好
     * @param cls         Excel注解标志类
     * @param <T>         泛型
     * @return            读取Excel后产生的数据
     */
    public static <T> List<T>  getDataByExcel(String filePath, int headerNum, Class<T> cls) throws ExcelException；
   /**
     * 根据上传的文件获取数据
     * @param multipartFile  上传的文件
     * @param cls  Excel注解类
     * @param <T>  泛型
     * @return  根据上传的文件获取的数据
     * @throws ExcelException  读取文件异常
     */
    public static <T> List<T> getDataByExcel(MultipartFile multipartFile, Class<T> cls) throws ExcelException；
     /**
     * 根据上传的文件获取数据
     * @param multipartFile  上传的文件
     * @param headerNum    从哪行开始读取
     * @param cls     Excel注解类
     * @param sheetIndexOrName   读取第几个sheet 默认第0个
     * @param <T>  泛型
     * @return  根据上传的文件获取的数据
     * @throws ExcelException  读取文件异常
     */
    public static <T> List<T>  getDataByExcel(MultipartFile multipartFile, int headerNum, Class<T> cls,Object sheetIndexOrName)；
     /**
     * 指定Sheet获取数据
     * @param filePath    文件路径  C:\Users\heyongjie\Desktop\testReadExcel.xlsx
     * @param headerNum   如果使用的是导出模板然后在导入请传入-1
     * @param cls         Excel注解标志类
     * @param sheet       指定的sheet
     * @param <T>         泛型
     * @return            读取Excel后产生的数据
     */
    public static <T> List<T>  getDataByExcelAndSheet(String filePath, int headerNum, Class<T> cls, ExcelField.Type type,Object sheet)


```

#### 生成Excel

```java
  /**
     * 创建Excel
     *
     * @param filePath 创建后的文件路径
     * @param cls      添加了Excel注解信息的类
     * @param list     数据，如果数据为空就会生成模板信息
     * @param <E>      E
     */
    public static <E> void createExcel(String filePath, Class cls, List<E> list) 
        
        
 /**
     * 创建Excel
     *
     * @param filePath 创建后的文件路径
     * @param cls      添加了Excel注解信息的类
     * @param list     数据，如果数据为空就会生成模板信息
     * @param type     ExcelField 导入还是导出 
     * @param <E>      E
     */
    private static <E> void createExcel(String filePath, Class cls, List<E> list, ExcelField.Type type);

```





