package com.sofn.ducss.web;

import com.sofn.ducss.config.FormCallbackConfig;
import com.sofn.ducss.sysapi.SysFileApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.xiaoleilu.hutool.util.ClassUtil.getClassLoader;

@Api(value = "畅写office组件回调保存接口", tags = "畅写office组件回调保存接口")
@RestController
@RequestMapping(value = "/callback")
public class CallbackController {

    private static final String templateFolder = getClassLoader().getResource("static/").getPath();
    @Autowired
    private FormCallbackConfig formCallbackConfig;
    @Autowired(required = false)
    private SysFileApi sysFileApi;

    /*@ApiOperation(value = "畅写office组件回调保存接口")
    @PostMapping("/callbackSave")
    public Map callbackSave(@RequestBody ChangXieVO vo) throws IOException {
        Map map = new HashMap<>();
        map.put("error", 0);
        String[] strArra = new String[2];
        String token = "";
        if(vo.getStatus() == FormManagerEnum.ZBBC.getIndex() || vo.getStatus() == FormManagerEnum.BJBC.getIndex()){
            if (!"".equals(vo.getUserdata())){
                Map mapOne = JSON.parseObject(vo.getUserdata(), Map.class);
                if (!"".equals(mapOne.get("saveUserID"))){
                    Map mapTwo = JSON.parseObject(mapOne.get("saveUserID").toString(), Map.class);
                    if (!"".equals(mapTwo.get("id"))){
                        strArra = mapTwo.get("id").toString().split(",");
                        if (strArra.length != 2){
                            throw new SofnException("当前用户状态异常");
                        } else {
                            token = strArra[1];
                        }
                    }
                }
            }
            URL url = new URL(vo.getUrl());
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection)
                    url.openConnection();
            *//**
     * 畅写编辑器回调传的url
     * http://192.168.21.157/cache/files/33_2961/output.docx/output.docx?
     * md5=OxaGXBOt76a8xtO5c7WeyA&expires=1605576486&disposition=attachment&ooname=output.docx
     **//*
     *//*String filePathUrl = connection.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);
            String fileName = fileFullName.substring(0,fileFullName.indexOf("?"));*//*
            //通过文件id获取文件名称
            InputStream stream = connection.getInputStream();
            //linux 下保存地址
            //String newFileDir = formCallbackConfig.getDir() + File.separator + fileName;
            Result<SysFileManageVo> result = sysFileApi.getOne(vo.getKey());
            String fileName = "output.docx";
            if (result.getData() != null) {
                fileName = result.getData().getFileName();
            } else {
                throw new SofnException("文件请求异常");
            }
            //String newFileDir = templateFolder + fileName;
            //linux下地址
            String newFileDir = formCallbackConfig.getDir() + File.separator  + fileName;
            File savedFile = new File(newFileDir);
            try (FileOutputStream out = new FileOutputStream(savedFile)) {
                int read;
                final byte[] bytes = new byte[1024];
                while ((read = stream.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
            }
            connection.disconnect();

            File file = new File(newFileDir);
            DiskFileItem fileItem = (DiskFileItem) new DiskFileItemFactory().createItem("file",
                    MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
            try (InputStream input = new FileInputStream(file); OutputStream os = fileItem.getOutputStream()) {
                IOUtils.copy(input, os);
            }
            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            //调用支撑平台接口上传图片
            SysFileVo data = sysFileApi.uploadFile(multipartFile,token).getData();
            //激活
            if(null!=data){
                SysFileManageForm fileManageForm = new SysFileManageForm(data.getFileName(),"ducss", null, null, data.getId());
                sysFileApi.activationFile(fileManageForm,token);
            }

            if(StringUtils.isNotBlank(vo.getKey())){
                //删除文件服务器中的旧文件
                sysFileApi.delFile(vo.getKey());
            }

            file.delete();

            return map;
        }
        //直接返回json.toString(); 得到结果为 "{error=0}"
        //JSON.toJSONString(json);得到结果为 "{\"error\":0}"
        //{"error":0}

        return map;
    }*/
}
