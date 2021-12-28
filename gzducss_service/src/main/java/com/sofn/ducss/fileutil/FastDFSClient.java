package com.sofn.ducss.fileutil;

import com.sofn.ducss.exception.SofnException;
import com.sofn.ducss.util.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

/**
 * FastDFS 客户端
 *
 * @author heyongjie
 * @date 2019/6/5 14:03
 */
@Slf4j
public class FastDFSClient {
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient1 storageClient;


    /**
     * FastDFSClient 配置
     */
    public static String trackerIp = "";
    public static String trackerPort = "";
    private static String charset = "";
    private static String connectTimeoutInSeconds = "";
    private static String networkTimeoutInSeconds = "";
    public static String httpTrackerHttpPort = "";
    public static String httpPrefix = "";
    public static String tempFilePath = "";
    public static String extranetIp = "";

    static {

        String env;
        try{
            env = SpringContextHolder.getActiveProfile();
        }catch (Exception e){
            e.printStackTrace();
            env = "dev";
        }


        String fastConfigFile = String.format("config/fastdfs-%s.properties", env);
        // 加载配置文件
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fastConfigFile);
        Properties properties = new Properties();
        try {
            properties.load(is);
            trackerIp = properties.getProperty("trackerIp").trim();
            trackerPort = properties.getProperty("trackerPort").trim();
            charset = properties.getProperty("charset").trim();
            connectTimeoutInSeconds = properties.getProperty("connectTimeoutInSeconds").trim();
            networkTimeoutInSeconds = properties.getProperty("networkTimeoutInSeconds").trim();
            httpTrackerHttpPort = properties.getProperty("httpTrackerHttpPort").trim();
            httpPrefix = properties.getProperty("httpPrefix").trim();
            tempFilePath = properties.getProperty("tempFilePath").trim();
            extranetIp = properties.getProperty("extranetIp").trim();
            if (StringUtils.isBlank(extranetIp)) {
                // log.warn("未配置外网访问地址，正在使用文件服务器的IP");
                extranetIp = trackerIp;
            }

            // 初始化配置
            Properties fastSfsProperties = new Properties();
            if (StringUtils.isEmpty(trackerIp) || StringUtils.isEmpty(trackerPort)) {
                throw new SofnException("请检查FastDFS配置：tracker_ip和tracker_port为必须项");
            }
            String trackerServers = trackerIp + ":" + trackerPort;
            fastSfsProperties.setProperty("fastdfs.tracker_servers", trackerServers);
            fastSfsProperties.setProperty("fastdfs.connect_timeout_in_seconds", connectTimeoutInSeconds);
            fastSfsProperties.setProperty("fastdfs.network_timeout_in_seconds", networkTimeoutInSeconds);
            fastSfsProperties.setProperty("fastdfs.charset", charset);
            fastSfsProperties.setProperty("fastdfs.http_tracker_http_port", httpTrackerHttpPort);
            try {
                ClientGlobal.initByProperties(fastSfsProperties);
                trackerClient = new TrackerClient();
                try {
                    trackerServer = trackerClient.getConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new SofnException("连接FastDFS服务器异常");
                }
                storageServer = null;
                storageClient = new StorageClient1(trackerServer, storageServer);
            } catch (Exception e) {
                e.printStackTrace();
                throw new SofnException("初始化FastDFS配置异常");
            }
        } catch (IOException e) {
            // log.info("fastdfs.properties配置文件不存在");
            e.printStackTrace();
        }

    }

    /**
     * 因为每次调用都会new一个socket连接，调用完成后会关闭，所以不能复用，这里直接每次都重新创建连接
     *
     * @return StorageClient1
     */
    private static StorageClient1 getStorageClient() {
        TrackerClient trackerClient = new TrackerClient();
        try {
            TrackerServer trackerServer = trackerClient.getConnection();
            return new StorageClient1(trackerServer, null);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SofnException("连接FastDFS服务器异常");
        }
    }

    /**
     * 上传文件方法
     *
     * @param fileName 文件全路径
     * @param extName  文件扩展名，不包含（.）
     * @param metas    文件扩展信息
     * @return 上传成功后返回的地址
     * @throws Exception Exception
     */
    public static String uploadFile(String fileName, String extName, NameValuePair[] metas) {
        String result = null;
        try {
            result = getStorageClient().upload_file1(fileName, extName, metas);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 上传文件
     *
     * @param multipartFile MultipartFile
     * @return null为失败
     */
    public static String uploadFile(MultipartFile multipartFile) {
        byte[] filesBytes = null;
        try {
            filesBytes = multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SofnException("获取文件内容异常");
        }
        // 获取后缀
        String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", multipartFile.getOriginalFilename());
        metaList[1] = new NameValuePair("fileExt", ext);
        metaList[2] = new NameValuePair("fileSize", String.valueOf(multipartFile.getSize()));

        try {
            return uploadFile(filesBytes, ext, metaList);
        } catch (Exception e) {
            throw new SofnException("上传文件异常");
        }
    }

    /**
     * 文件流上传文件
     *
     * @param multipartFile MultipartFile
     * @return null为失败
     */
    public static String uploadFileForStream(MultipartFile multipartFile) {
      // 获取后缀
      String ext = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
      NameValuePair[] metaList = new NameValuePair[3];
      metaList[0] = new NameValuePair("fileName", multipartFile.getOriginalFilename());
      metaList[1] = new NameValuePair("fileExt", ext);
      metaList[2] = new NameValuePair("fileSize", String.valueOf(multipartFile.getSize()));

      try {
        return uploadFile(multipartFile.getInputStream(), ext, multipartFile.getSize(),metaList);
      } catch (Exception e) {
        throw new SofnException("上传文件异常");
      }
    }

    /**
     * 上传文件,传fileName
     *
     * @param fileName 文件的磁盘路径名称 如：D:/image/aaa.jpg
     * @return null为失败
     */
    public String uploadFile(String fileName) {
        return uploadFile(fileName, null, null);
    }

    /**
     * @param fileName 文件的磁盘路径名称 如：D:/image/aaa.jpg
     * @param extName  文件的扩展名 如 txt jpg等
     * @return null为失败
     */
    public String uploadFile(String fileName, String extName) {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     *
     * @param fileContent 文件的内容，字节数组
     * @param extName     文件扩展名
     * @param metas       文件扩展信息
     * @return
     * @throws Exception
     */
    public static String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) {
        String result = null;
        try {
            result = getStorageClient().upload_file1(fileContent, extName, metas);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 文件流上传文件
     * @param inStream 文件流
     * @param extName 文件扩展名
     * @param fileLength 文件长度
     * @param metas 文件扩展信息
     * @return
     */
      public static String uploadFile(InputStream inStream, String extName, long fileLength,NameValuePair[] metas) {
        String[] result = null;
        try {
          result = getStorageClient().upload_file(null, fileLength, new UploadFileSender(inStream), extName, metas);
        } catch (IOException | MyException e) {
          e.printStackTrace();
        }

        return result!=null&&result.length>0?StringUtils.join(result,"/"):null;
      }

    /**
     * 上传文件
     *
     * @param fileContent 文件的字节数组
     * @return null为失败
     * @throws Exception
     */
    public String uploadFile(byte[] fileContent) throws Exception {
        return uploadFile(fileContent, null, null);
    }

    /**
     * 上传文件
     *
     * @param fileContent 文件的字节数组
     * @param extName     文件的扩展名 如 txt  jpg png 等
     * @return null为失败
     */
    public String uploadFile(byte[] fileContent, String extName) {
        return uploadFile(fileContent, extName, null);
    }

    /**
     * 文件下载到磁盘
     *
     * @param path   图片路径
     * @param output 输出流 中包含要输出到磁盘的路径
     * @return -1失败,0成功
     */
    public static int download_file(String path, BufferedOutputStream output) {
        int result = -1;
        try {
            byte[] b = getStorageClient().download_file1(path);
            try {
                if (b != null) {
                    output.write(b);
                    result = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } //用户可能取消了下载
            finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取文件数组
     *
     * @param path 文件的路径 如group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return
     */
    public static byte[] download_bytes(String path) {
        byte[] b = null;
        try {
            b = getStorageClient().download_file1(path);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 删除文件
     *
     * @param group       组名 如：group1
     * @param storagePath 不带组名的路径名称 如：M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return -1失败,0成功
     */
    public Integer delete_file(String group, String storagePath) {
        int result = -1;
        try {
            result = getStorageClient().delete_file(group, storagePath);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param storagePath 文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return -1失败,0成功
     * @throws IOException
     * @throws Exception
     */
    public static Integer delete_file(String storagePath) {
        int result = -1;
        try {
            result = getStorageClient().delete_file1(storagePath);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 批量删除文件
     *
     * @param storagePaths  需要删除的文件路径
     */
    public static void batchDeleteFile(List<String> storagePaths) {
        if (CollectionUtils.isEmpty(storagePaths)) {
            return;
        }
        StorageClient1 storageClient = getStorageClient();
        storagePaths.forEach((storagePath) -> {
            try {
                storageClient.delete_file1(storagePath);
            } catch (Exception e) {
                log.error("文件删除失败：storagePath=【{}】",storagePath);
                e.printStackTrace();
            }
        });
    }

    /**
     * 获取远程服务器文件资源信息
     *
     * @param groupName      文件组名 如：group1
     * @param remoteFileName M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return
     */
    public FileInfo getFile(String groupName, String remoteFileName) {
        try {
            return getStorageClient().get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件真实路径，可供直接访问   获取前缀
     *
     * @param path FastDFS文件路径 /path/...
     * @return http://127.0.0.1:8881/path/...
     */
    public static String getRealPath(String path) {
        return getPrefix() + path;
    }

    /**
     * 获取前缀
     *
     * @return http://127.0.0.1:8881/
     */
    public static String getPrefix() {
        return httpPrefix + extranetIp + ":" + httpTrackerHttpPort + "/";
    }

    /**
     * 文件流上传callback
     */
    private static class UploadFileSender implements UploadCallback {

        private InputStream inStream;

        public UploadFileSender(InputStream inStream) {
          this.inStream = inStream;
        }

        @Override
        public int send(OutputStream out) throws IOException {
          byte[] bs = new byte[1024];
          int i=0;
          try{
            while ((i = inStream.read(bs)) != -1) {

              out.write(bs, 0, i);

            }
            out.flush();
          }catch (Exception e){
            e.printStackTrace();
          }

          return 0;
        }
      }
}
