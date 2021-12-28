# 方式一
> 直接在要打包项目的pom.xml文件路径下执行以下命令,然后再到Jenkins上构建

```shell script
mvn clean package -U -DskipTests docker:build
```

# 方式二
> 为了方便，可以将方式一种的命令配置成一个run configuration

具体步骤如下：
Edit Configurations --> 点击左上角的加号 --> 选择Maven --> working directory项选择项目目录 -->

command line 填写方式一中的命令（不需要加mvn) --> 确定 

