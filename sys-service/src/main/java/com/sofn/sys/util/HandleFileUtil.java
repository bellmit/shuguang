package com.sofn.sys.util;

import com.sofn.common.exception.SofnException;
import com.sofn.common.fileutil.FastDFSClient;
import com.sofn.common.fileutil.ZipUtil;
import com.sofn.common.utils.DateUtils;
import com.sofn.common.utils.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;

/**
 * 处理文件的工具
 * @author heyongjie
 * @date 2019/11/12 14:45
 */
@Slf4j
public class HandleFileUtil {

    /**
     * 根据文件名获取添加UUID后的文件名 如文件名为test.xml 返回test-UUID.xml
     * @param fileName String  test.xml
     * @return test-UUID.xml
     */
    public static String getFileName(String fileName){
        String toPrefix = fileName.substring(0,fileName.lastIndexOf("."));
        // 获取文件后缀
        String toSuffix = fileName.substring(fileName.lastIndexOf("."));

        fileName = toPrefix+ "-" + IdUtil.getUUId() + toSuffix;
        return fileName;
    }


    /**
     * 压缩文件
     * @param effectivePaths  文件名称和文件路径
     * @return  压缩文件的路径
     */
    public static String zipFileByPaths(Map<String, String> effectivePaths){
        String fileName = "批量下载文件" +DateUtils.format(new Date(),"yyyyMMddHHmmss");
        return zipFileByPaths(effectivePaths,fileName);
    }

    /**
     * 压缩文件
     * @param effectivePaths  文件名称和文件路径
     * @return  压缩文件的路径
     */
    public static String zipFileByPaths(Map<String, String> effectivePaths,String fileName){
        if(StringUtils.isBlank(fileName)){
            fileName = "批量下载文件" +DateUtils.format(new Date(),"yyyyMMddHHmmss");
        }
        // 1. 创建临时文件目录
        String tempFilePath = FastDFSClient.tempFilePath + File.separator + fileName ;
        File file = new File(tempFilePath);
        // 如果文件存在就等待一秒， 再次判断是否存在！ 直到文件不存在就创建文件
        boolean flag;
        do{
            if (file.exists()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
            }else{
                file.mkdirs();
                flag = false;
            }
        }while(flag);
        // 2. 下载文件
        effectivePaths.forEach((k,v) ->{
            try {
                execuDownloadFile(k,v,tempFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 3. 压缩文件
        String zipFilePath = tempFilePath + ".zip";
        try {
            ZipUtil.compress(tempFilePath,zipFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("压缩文件失败");
        }
        // 4. 删除之前下载的文件
        deleteDirOrFile(tempFilePath);
        // 5. 返回这个压缩文件路径以供下载
        return zipFilePath;
    }

    /**
     * 删除文件夹或者文件  注意：会删除当前目录和当前目录下的所有文件和文件夹
     * @param path
     */
    public static void deleteDirOrFile(String path){
        File file = new File(path);
        if(file.exists()){
            // 如果是文件直接删除
            if(file.isFile()){
                file.delete();
            }else{
                // 如果是文件夹获取下面所有的文件和文件夾
                File [] listFile =  file.listFiles();
                log.info("{}当前目录下的文件数目:{}",path,listFile.length);
                if(listFile != null && listFile.length > 0){
                    // 1.这里删除当前文件下的所有文件
                    for (File file1 : listFile) {
                        if(file1.isFile()){
                            file1.delete();
                        }else{
                            deleteDirOrFile(file1.getAbsolutePath());
                        }
                    }
                    // 2.删除当前文件
                    file.delete();
                }else if(listFile.length == 0){
                    // 如果文件夹下没有内容直接删除
                    log.info("删除文件夹");
                    file.delete();
                }
            }
        }
    }

    /**
     * 下载文件到指定目录
     *
     * @param fileName     文件名称
     * @param filePath     文件在文件服务器上存储的路径
     * @param tempFilePath 文件下载到本地之后的路径    这个目录必须存在，如果不存在就不执行下载
     */
    public static void execuDownloadFile(String fileName, String filePath, String tempFilePath) throws Exception {
        File tempFile = new File(tempFilePath);
        if (tempFile.exists()) {
            // 创建文件  如:C:\Users\heyongjie\Desktop\UUID\aaa-UUID.txt
            File file = new File(tempFilePath + File.separator + fileName );
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            int code = FastDFSClient.download_file(filePath,bos);
            if(code == -1){
                log.info("【批量下载文件】文件{}下载失败，可能是文件服务器上不存在，或者其他，请检查",fileName);
            }
        }else{
            log.info("临时文件夹{}不存在",tempFilePath);
            throw new SofnException("文件下载失败");
        }
    }



}
