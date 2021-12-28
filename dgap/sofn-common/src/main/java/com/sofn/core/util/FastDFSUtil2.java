package com.sofn.core.util;

import com.sofn.core.util.fastDFS.ConnectionPool;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/** 外网
 * Created by wuye on 16/11/1.
 */
public class FastDFSUtil2 {

    private static final String FASTDFS_CONIFG = "fdfs_client.conf";

    /** 连接池 */
    private static ConnectionPool connectionPool = null;
    /** 连接池默认最小连接数 */
    private static long minPoolSize = 1;
    /** 连接池默认最大连接数 */
    private static long maxPoolSize = 10;
    /** 当前创建的连接数 */
    private static volatile long nowPoolSize = 0;
    /** 默认等待时间（单位：秒） */
    private static long waitTimes = 200;

    /**
     * 初始化线程池
     *
     * @Description:
     *
     */
    public static void init() {
        String logId = UUID.randomUUID().toString();
//        log.info("[初始化线程池(Init)][" + logId + "][默认参数：minPoolSize="
//                + minPoolSize + ",maxPoolSize=" + maxPoolSize + ",waitTimes="
//                + waitTimes + "]");
        connectionPool = new ConnectionPool(minPoolSize, maxPoolSize, waitTimes);
    }

//    /**
//     * 文件上传(byte[])
//     * @param
//     * @return
//     */
//    public static String getUploadFileByInputStream(InputStream inputStream) throws IOException, MyException {
//        //加载配置文件
//        String path = new File(FastDFSUtil.class.getResource("/").getFile()).getCanonicalPath();
//        String configFileName = path + File.separator+FASTDFS_CONIFG;
//        ClientGlobal.init(configFileName);
//        byte[] bt = IOUtils.toByteArray(inputStream);
//        String files = upliadFileByByte(inputStream,"sofn.png",bt.length);
////        List<String> files2 = Arrays.asList(files);
////        StringBuffer str = new StringBuffer();
////        for (int i = 0;i<files2.size();i++){
////            str.append(files2.get(i));
////            str.append("/");
////        }
////        str.deleteCharAt(str.length()-1);
//        String address = "192.168.21.249/";
//        //address+=str.toString();
//        return address;
//    }
//
//
    /**
     * 文件上传（File）
     * @param file
     * @return
     * @throws Exception
     */
    public static String getUpliadFileAddress(File file)throws Exception{
        //加载配置文件的方式
//        String path = new File(FastDFSUtil.class.getResource("/").getFile()).getCanonicalPath();
//        String configFileName = path + File.separator+FASTDFS_CONIFG;
//        ClientGlobal.init(configFileName);
        String[] files = uploadFile(file,file.getName(),file.length());
        List<String> files2 = Arrays.asList(files);
        StringBuffer str = new StringBuffer();
        for (int i = 0;i<files2.size();i++){
            str.append(files2.get(i));
            str.append("/");
        }
        str.deleteCharAt(str.length()-1);
        String address = "218.89.222.117/dfs/";
        address+=str.toString();
        return address;
    }
//
    /**
     * 文件上传（MultipartFile）
     * @param file
     * @return
     * @throws Exception
     */
    public static String getUpliadFileAddress2(MultipartFile file)throws Exception{
        //加载配置文件的方式
//        String path = new File(FastDFSUtil.class.getResource("/").getFile()).getCanonicalPath();
//        String configFileName = path + File.separator+FASTDFS_CONIFG;
//        ClientGlobal.init(configFileName);
        String[] files = uploadFile2(file);
        List<String> files2 = Arrays.asList(files);
        StringBuffer str = new StringBuffer();
        for (int i = 0;i<files2.size();i++){
            str.append(files2.get(i));
            str.append("/");
        }
        str.deleteCharAt(str.length()-1);
        String address = "218.89.222.117/dfs/";
        address+=str.toString();
        return address;
    }

    /**
     * 文件下载
     * @param fileUrl
     * @return
     */
    public static ResponseEntity<byte[]> dowloadFile(String fileUrl,String fileName) throws Exception{
        //加载配置文件
//        String path = new File(FastDFSUtil.class.getResource("/").getFile()).getCanonicalPath();
//        String configFileName = path + File.separator+FASTDFS_CONIFG;
//        ClientGlobal.init(configFileName);
        ResponseEntity<byte[]> result = dowload(fileUrl,fileName);
        return result;
    }

    /**
     * 文件下载
     * @param fileUrl
     * @return
     */
    public static InputStream dowloadFileToInputStream(String fileUrl) throws Exception{
        //加载配置文件
//        String path = new File(FastDFSUtil.class.getResource("/").getFile()).getCanonicalPath();
//        String configFileName = path + File.separator+FASTDFS_CONIFG;
//        ClientGlobal.init(configFileName);
        return dowload2(fileUrl);
    }

    /**
     * 删除文件
     */
    public static String deleteFastDFS(String fileUrl)throws Exception{
        //加载配置文件
//        String path = new File(FastDFSUtil.class.getResource("/").getFile()).getCanonicalPath();
//        String configFileName = path + File.separatorChar+FASTDFS_CONIFG;
//        ClientGlobal.init(configFileName);
        int i = deleteFile(fileUrl);
        if(i == 0){
            return "删除成功";
        }else{
            return "删除失败";
        }
    }
//
//    /**
//     * 上传文件(byte[])
//     * @param
//     * @param uploadFileName
//     * @param fileLength
//     * @return
//     * @throws IOException
//     */
//    public static String upliadFileByByte(InputStream inputStream,String uploadFileName,long fileLength)throws IOException{
//        byte[] fileBuff = getFileBuffer(inputStream,fileLength);
//        String files = null;
//        String fileExtName = "";
//        if(uploadFileName.contains(".")){
//            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
//        }else{
//            System.out.print("Fail to upload file,because the format of filename is illegal");
//            return null;
//        }
//        //建立链接
//        TrackerClient trackerClient = new TrackerClient();
//        TrackerServer trackerServer = trackerClient.getConnection();
//        StorageServer storageServer=  null;
//        StorageClient1 client = new StorageClient1(trackerServer, storageServer);
//
//        //设置元信息
//        NameValuePair[] metaList = new NameValuePair[3];
//        metaList[0] = new NameValuePair("fileName",uploadFileName);
//        metaList[1] = new NameValuePair("fileExtName",fileExtName);
//        metaList[2] = new NameValuePair("fileLength",String.valueOf(fileLength));
//
//        //上传文件
//        try {
//            files = client.upload_file1(fileBuff,fileExtName,metaList);
//        } catch (MyException e) {
//            e.printStackTrace();
//        }
//        trackerServer.close();;
//
//        return files;
//    }
//
//
    /**
     * 上传文件(File)
     */
    public static String[] uploadFile(File file,String uploadFileName,long fileLength) throws IOException, Exception {
        byte[] fileBuff = getFileBuffer(new FileInputStream(file),fileLength);
        String[] files = null;
        String fileExtName = "";
        if(uploadFileName.contains(".")){
            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
        }else{
            System.out.print("Fail to upload file,because the format of filename is illegal");
            return null;
        }
        init();
        //建立链接
        String logId = UUID.randomUUID().toString();
        //TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer=  null;
        StorageClient client = new StorageClient(trackerServer,storageServer);

        //设置元信息
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName",uploadFileName);
        metaList[1] = new NameValuePair("fileExtName",fileExtName);
        metaList[2] = new NameValuePair("fileLength",String.valueOf(fileLength));

        //上传文件
        try {
            files = client.upload_file(fileBuff,fileExtName,metaList);
        } catch (MyException e) {
            e.printStackTrace();
        }
        connectionPool.checkin(trackerServer,logId);

        return files;
    }
//
    /**
     * 文件上传（MultipartFile）
     * @param file
     * @return
     */
    public static String[] uploadFile2(MultipartFile file) throws IOException, MyException, Exception {
        String tempFileName = file.getOriginalFilename();
        byte[] fileBuff = file.getBytes();
        String[] fileId = null;
        String fileExtName = tempFileName.substring(tempFileName.lastIndexOf("."));

        //建立连接
        init();
//        TrackerClient tracker = new TrackerClient();
//        TrackerServer trackerServer = tracker.getConnection();
        String logId = UUID.randomUUID().toString();
        //TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        //设置元信息
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", tempFileName);
        metaList[1] = new NameValuePair("fileExtName", fileExtName);
        metaList[2] = new NameValuePair("fileLength", String.valueOf(file.getSize()));

        //上传文件
        fileId = client.upload_file(fileBuff, fileExtName, metaList);
        connectionPool.checkin(trackerServer,logId);
       // trackerServer.close();
        return fileId;
    }

    /**
     * 文件下载
     * @param fileUrl
     * @return
     */
    public static ResponseEntity<byte[]> dowload(String fileUrl, String fileName) throws IOException, MyException, Exception {
        HttpHeaders headers = new HttpHeaders();

        //建立连接
        init();
//        TrackerClient tracker = new TrackerClient();
//        TrackerServer trackerServer = tracker.getConnection();
        String logId = UUID.randomUUID().toString();
        //TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer,storageServer);
        //获取文件组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String group = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/")+1);
        String specFileName = fileName + substr.substring(substr.indexOf("."));
        byte[] b = client.download_file(group,remoteFileName);
        connectionPool.checkin(trackerServer,logId);
        headers.setContentDispositionFormData("attachment",  new String(specFileName.getBytes("UTF-8"),"iso-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        //InputStream inputStream = new ByteArrayInputStream(b);
        return new ResponseEntity<byte[]>(b, headers, HttpStatus.CREATED);
    }

    /**
     * 文件下载2
     * @param fileUrl
     * @return
     */
    public static InputStream dowload2(String fileUrl) throws Exception{
        //建立连接
        init();
//        TrackerClient tracker = new TrackerClient();
//        TrackerServer trackerServer = tracker.getConnection();
        String logId = UUID.randomUUID().toString();
        //TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer,storageServer);
        //获取文件组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String group = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/")+1);
        byte[] b = client.download_file(group,remoteFileName);
        InputStream inputStream = new ByteArrayInputStream(b);
        connectionPool.checkin(trackerServer,logId);
        return inputStream;
    }

    /**
     * 文件删除
     * @param fileUrl
     * @return
     * @throws Exception
     */
    public static int deleteFile(String fileUrl)throws Exception{
        //建立连接
        init();
//        TrackerClient trackerClient = new TrackerClient();
//        TrackerServer trackerServer = trackerClient.getConnection();
        String logId = UUID.randomUUID().toString();
        //TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient client = new StorageClient(trackerServer,storageServer);
        //获取文件名组别和文件名
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        String group = substr.split("/")[0];
        String remoteFileName = substr.substring(substr.indexOf("/")+1);
        int result = client.delete_file(group,remoteFileName);
        connectionPool.checkin(trackerServer,logId);
        return result;
    }

//    /**
//     * 获取文件服务器上文件所在组别
//     * @param fileUrl
//     * @return
//     */
//    public String getGroupName(String filePath){
//        String substr = filePath.substring(filePath.indexOf("group"));
//        String group = substr.split("/")[0];
//        String remoteFileName = substr.substring(substr.indexOf("/")+1);
//        String specFileName = substr.substring(substr.indexOf("."));
//        return groupName;
//    }
//
//    /**
//     * 获取文件路径
//     * @param fileUrl
//     * @return
//     */
//    public String getFileUlr(String fileUrl){
//        String url = null;
//
//        return url;
//    }


    private static byte[] getFileBuffer(InputStream inStream,long fileLength) throws IOException{
        byte[] buffer = new byte[256*1024];
        byte[] fileBuffer = new byte[(int)fileLength];

        int count = 0;
        int lenght = 0;

        while((lenght = inStream.read(buffer))!= -1){
            for(int i =0;i<lenght;++i){
                fileBuffer[count+i] = buffer[i];
            }
            count += lenght;
        }
        return fileBuffer;
    }


    /**
     //     * 文件上传(InputStream)
     //     * @param
     //     * @return
     //     */
//    public static String getUploadFileByInputStream(InputStream inputStream,String uploadFileName,long fileLength) throws IOException, MyException {
//        //加载配置文件
//        String path = new File(FastDFSUtil.class.getResource("/").getFile()).getCanonicalPath();
//        String configFileName = path + File.separator+FASTDFS_CONIFG;
//        ClientGlobal.init(configFileName);
//        String[] files = uploadFileByStream(inputStream,uploadFileName,fileLength);
//        List<String> files2 = Arrays.asList(files);
//        StringBuffer str = new StringBuffer();
//        for (int i = 0;i<files2.size();i++){
//            str.append(files2.get(i));
//            str.append("/");
//        }
//        str.deleteCharAt(str.length()-1);
//        String address = "192.168.21.249/";
//        //address+=str.toString();
//        return address;
//    }


    public static String uploadFileByStream(InputStream inStream, String uploadFileName, long fileLength) throws IOException, MyException, Exception {
//        //加载配置文件
//        String path = new File(FastDFSUtil.class.getResource("/").getFile()).getCanonicalPath();
//        String configFileName = path + File.separator+FASTDFS_CONIFG;
//        ClientGlobal.init(configFileName);
        String[] results = null;
        String fileExtName = "";
        if (uploadFileName.contains(".")) {
            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        } else {
            return null;
        }
        //建立连接
        init();
//        TrackerClient tracker = new TrackerClient();
//        TrackerServer trackerServer = tracker.getConnection();
        String logId = UUID.randomUUID().toString();
        //TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = connectionPool.checkout(logId);
        StorageServer storageServer = null;
        StorageClient1 client = new StorageClient1(trackerServer, storageServer);

        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", uploadFileName);
        metaList[1] = new NameValuePair("fileExtName", fileExtName);
        metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));

        try {
            // results[0]: groupName, results[1]: remoteFilename.
            results = client.upload_file(null, fileLength, new UploadFileSender(inStream), fileExtName, metaList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        connectionPool.checkin(trackerServer,logId);
        trackerServer.close();
        List<String> files2 = Arrays.asList(results);
        StringBuffer str = new StringBuffer();
        for (int i = 0;i<files2.size();i++){
            str.append(files2.get(i));
            str.append("/");
        }
        str.deleteCharAt(str.length()-1);
        String address = "10.0.51.100/dfs/";
        address+=str.toString();
        connectionPool.checkin(trackerServer,logId);
        return address;
    }

    private static class UploadFileSender implements UploadCallback {

        private InputStream inStream;

        public UploadFileSender(InputStream inStream) {
            this.inStream = inStream;
        }

        public int send(OutputStream out) throws IOException {
            int readBytes;
            while((readBytes = inStream.read()) > 0) {
                out.write(readBytes);
            }
            return 0;
        }
    }
}

