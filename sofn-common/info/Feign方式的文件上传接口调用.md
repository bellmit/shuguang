## 服务提供方（接收文件）
#### 调用sys-service的文件上传接口：
```
@PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "上传文件，上传成功后需要调用激活文件接口才有效，否则文件会定期清理")
    @ResponseBody
    public Result<SysFileVo> uploadFile(@ApiParam(value = "文件") @RequestPart(value = "file") MultipartFile file,
                                        @RequestHeader(value = JWTToken.TOKEN,required = false) @ApiParam(required = false) String token) {
        System.out.println(token);
        SysFileVo sysFileVo = sysFileManageService.uploadFile(file,token);
        return Result.ok(sysFileVo);
    }
```
## 服务消费方（发送文件）
#### 子系统feign接口
```
@FeignClient(
        value = "sys-service",
        configuration = FeignConfiguration.class )
public interface SysFileApi {
 
    @PostMapping(value = "/fileManage/uploadFile", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        Result<SysFileVo> uploadFile(@ApiParam(value = "文件") @RequestPart(value = "file") MultipartFile file);
 
}
```
#### 子系统业务代码生成MultipartFile，并调用feign接口
```
File file = new File();
DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                        MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
    IOUtils.copy(input, os);
}
MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
SysFileVo data = sysFileApi.uploadFile(multipartFile).getData();
```