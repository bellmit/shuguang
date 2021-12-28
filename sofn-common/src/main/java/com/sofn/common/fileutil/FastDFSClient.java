package com.sofn.common.fileutil;

import com.sofn.common.exception.SofnException;
import com.sofn.common.fastdfs.FastDFSProperties;
import com.sofn.common.fastdfs.FastDFSUtils;
import com.sofn.common.fastdfs.TrackerServerPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

import static com.sofn.common.fastdfs.FastDFSUtils.*;


/**
 * FastDFS 客户端
 *
 * @author heyongjie
 * @date 2019/6/5 14:03
 */
@Slf4j
public class FastDFSClient {
    /**
     * FastDFSClient 配置
     */
    public static String tempFilePath = FastDFSUtils.getFastdfsProperties().getTempFilePath();

    /**
     * 上传文件方法
     *
     * @param fileName    文件全路径
     * @param fileExtName 文件扩展名，不包含（.）
     * @param metas       文件扩展信息
     * @return 上传成功后返回的地址
     * @throws Exception Exception
     */
    public static String uploadFile(String fileName, String fileExtName, NameValuePair[] metas) {
        String result = null;

        if (org.springframework.util.StringUtils.isEmpty(fileExtName)) {
            fileExtName = FilenameUtils.getExtension(fileName);
        }

        // 检测文件名后缀是否为禁止上传类型
        if (FORBID_FILENAME_SUFFIX.contains(fileExtName)) {
            throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
        }

        Tika tika = new Tika();
        File file = new File(fileName);
        String mediaType = "";
        try {
            mediaType = tika.detect(file);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        // 检测文件媒体类型是否为禁止上传类型，这一步是为了防止上面被禁止上传的文件后缀名修改为可上传类型的情况
        if (FORBID_MEDIA_TYPE.contains(mediaType)) {
            throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
        }

        // 检测文件大小是否超出上限值
        if (file.length() > UPLOAD_FILE_SIZE_UPPER) {
            throw new IllegalArgumentException(EXCEED_SIZE_UPPER_MESSAGE);
        }

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            result = client.upload_file1(fileName, fileExtName, metas);
        } catch (Exception e) {
            log.error("上传文件发生异常！", e);
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
        }
        return result;
    }

    /**
     * 上传文件(该上传方法有缺陷，上传大体积的文件会有堆内存溢出风险，不推荐使用)
     *
     * @param multipartFile MultipartFile
     * @return null为失败
     */
    @Deprecated
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
        metaList[0] = new NameValuePair(FastDFSUtils.METADATA_NAME_FILENAME, multipartFile.getOriginalFilename());
        metaList[1] = new NameValuePair(FastDFSUtils.METADATA_NAME_FILEEXTNAME, ext);
        metaList[2] = new NameValuePair(FastDFSUtils.METADATA_NAME_FILELENGTH, String.valueOf(multipartFile.getSize()));

        try {
            return uploadFile(filesBytes, ext, metaList);
        } catch (Exception e) {
            throw new SofnException("上传文件异常: " + e.getMessage(), e);
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
        metaList[0] = new NameValuePair(FastDFSUtils.METADATA_NAME_FILENAME, multipartFile.getOriginalFilename());
        metaList[1] = new NameValuePair(FastDFSUtils.METADATA_NAME_FILEEXTNAME, ext);
        metaList[2] = new NameValuePair(FastDFSUtils.METADATA_NAME_FILELENGTH, String.valueOf(multipartFile.getSize()));

        try {
            return uploadFile(multipartFile.getInputStream(), ext, multipartFile.getSize(), metaList, multipartFile.getBytes());
        } catch (Exception e) {
            throw new SofnException("上传文件异常：" + e.getMessage(), e);
        }
    }

    /**
     * 上传文件,传fileName
     *
     * @param fileName 文件的磁盘路径名称 如：D:/image/aaa.jpg
     * @return null为失败
     */
    public static String uploadFile(String fileName) {
        return uploadFile(fileName, null, null);
    }

    /**
     * @param fileName 文件的磁盘路径名称 如：D:/image/aaa.jpg
     * @param extName  文件的扩展名 如 txt jpg等
     * @return null为失败
     */
    public static String uploadFile(String fileName, String extName) {
        return uploadFile(fileName, extName, null);
    }

    /**
     * 上传文件方法
     *
     * @param fileContent 文件的内容，字节数组
     * @param fileExtName 文件扩展名
     * @param metas       文件扩展信息
     * @return
     * @throws Exception
     */
    public static String uploadFile(byte[] fileContent, String fileExtName, NameValuePair[] metas) {
        String result = null;
        if (!org.springframework.util.StringUtils.isEmpty(fileExtName)) {
            // 检测文件名后缀是否为禁止上传类型
            if (FORBID_FILENAME_SUFFIX.contains(fileExtName)) {
                throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
            }
        }

        Tika tika = new Tika();
        String mediaType = tika.detect(fileContent);
        // 检测文件媒体类型是否为禁止上传类型，这一步是为了防止上面被禁止上传的文件后缀名修改为可上传类型的情况
        if (FORBID_MEDIA_TYPE.contains(mediaType)) {
            throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
        }

        // 检测文件大小是否超出上限值
        if (fileContent.length > UPLOAD_FILE_SIZE_UPPER) {
            throw new IllegalArgumentException(EXCEED_SIZE_UPPER_MESSAGE);
        }

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            result = client.upload_file1(fileContent, fileExtName, metas);
        } catch (Exception e) {
            log.error("上传文件发生异常！", e);
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
        }

        return result;
    }

    /**
     * 文件流上传文件
     *
     * @param inStream     文件流
     * @param fileExtName  文件扩展名
     * @param fileLength   文件长度
     * @param metas        文件扩展信息
     * @param contentBytes 包含文件内容的字节数组，用于检测文件媒体类型
     * @return 文件访问地址（不含IP和端口）
     */
    public static String uploadFile(InputStream inStream, String fileExtName, long fileLength, NameValuePair[] metas, byte[] contentBytes) {
        String result = null;
        if (!org.springframework.util.StringUtils.isEmpty(fileExtName)) {
            // 检测文件名后缀是否为禁止上传类型
            if (FORBID_FILENAME_SUFFIX.contains(fileExtName)) {
                throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
            }
        }

        if (contentBytes != null) {
            Tika tika = new Tika();
            String mediaType = tika.detect(contentBytes);

            // 检测文件媒体类型是否为禁止上传类型，这一步是为了防止上面被禁止上传的文件后缀名修改为可上传类型的情况
            if (FORBID_MEDIA_TYPE.contains(mediaType)) {
                throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
            }
        }

        // 检测文件大小是否超出上限值
        if (fileLength > UPLOAD_FILE_SIZE_UPPER) {
            throw new IllegalArgumentException(EXCEED_SIZE_UPPER_MESSAGE);
        }

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            result = client.upload_file1(null, fileLength, new UploadStream(inStream, fileLength), fileExtName, metas);
        } catch (Exception e) {
            log.error("上传文件发生异常！", e);
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
            try {
                inStream.close();
            } catch (IOException e) {
                log.error("关闭文件输入流发生异常！", e);
                e.printStackTrace();
            }
        }

        return result;
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
     * 文件下载到磁盘 (下载大体积文件有堆内存溢出风险，不推荐使用)
     *
     * @param path   图片路径
     * @param output 输出流 中包含要输出到磁盘的路径
     * @return -1失败,0成功
     */
    @Deprecated
    public static int download_file(String path, BufferedOutputStream output) {
        int result = -1;

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            byte[] b = client.download_file1(path);
            try {
                if (b != null) {
                    output.write(b);
                    result = 0;
                }
            } catch (Exception e) {
                log.error("下载文件发生异常！", e);
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
            log.error("下载文件发生异常！", e);
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
        }
        return result;
    }

    /**
     * 获取文件数组 (下载大体积文件有堆内存溢出风险，不推荐使用)
     *
     * @param path 文件的路径 如group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return
     */
    @Deprecated
    public static byte[] download_bytes(String path) {
        byte[] b = null;

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            b = client.download_file1(path);
        } catch (Exception e) {
            log.error("下载文件发生异常！", e);
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
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

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            result = client.delete_file(group, storagePath);
        } catch (Exception e) {
            log.error("删除文件发生异常！", e);
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
        }
        return result;
    }

    /**
     * @param storagePath 文件的全部路径 如：group1/M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return -1失败,0成功
     */
    public static Integer delete_file(String storagePath) {
        int result = -1;

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            result = client.delete_file1(storagePath);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
        }
        return result;
    }

    /**
     * 批量删除文件
     *
     * @param storagePaths 需要删除的文件路径
     */
    public static void batchDeleteFile(List<String> storagePaths) {
        if (CollectionUtils.isEmpty(storagePaths)) {
            return;
        }
        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 storageClient = new StorageClient1(trackerServer, null);
            storagePaths.forEach((storagePath) -> {
                try {
                    storageClient.delete_file1(storagePath);
                } catch (Exception e) {
                    log.error("文件删除失败：storagePath=【{}】", storagePath);
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            log.error("批量删除文件时获取trackerServer发生异常", e);
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
        }
    }

    /**
     * 获取远程服务器文件资源信息
     *
     * @param groupName      文件组名 如：group1
     * @param remoteFileName M00/00/00/wKgRsVjtwpSAXGwkAAAweEAzRjw471.jpg
     * @return
     */
    public FileInfo getFile(String groupName, String remoteFileName) {
        FileInfo fileInfo = null;
        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = FastDFSUtils.getTrackerServerPool();
        TrackerServer trackerServer = null;

        try {
            trackerServer = trackerServerPool.borrowObject();
            StorageClient1 client = new StorageClient1(trackerServer, null);
            fileInfo = client.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            log.error("获取文件资源信息时发生异常", e);
            e.printStackTrace();
        } finally {
            if (trackerServer != null) {
                trackerServerPool.returnObject(trackerServer);
            }
        }

        return fileInfo;
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
        FastDFSProperties fastdfsProperties = FastDFSUtils.getFastdfsProperties();
        return fastdfsProperties.getDownloadPath();
    }

    /**
     * 文件流上传callback
     */
    private static class UploadFileSender implements UploadCallback {

        private final InputStream inStream;

        public UploadFileSender(InputStream inStream) {
            this.inStream = inStream;
        }

        @Override
        public int send(OutputStream out) throws IOException {
            byte[] bs = new byte[262144];
            int i = 0;
            try {
                while ((i = inStream.read(bs)) != -1) {

                    out.write(bs, 0, i);

                }
                out.flush();
            } catch (Exception e) {
                log.error("上传文件发生异常！", e);
                e.printStackTrace();
                return -1;
            }

            return 0;
        }
    }
}
