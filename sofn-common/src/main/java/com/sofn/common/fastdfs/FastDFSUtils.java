/**
 * Copyright (c) 1998-2019 SOFN Corporation
 * All rights reserved.
 */
package com.sofn.common.fastdfs;

import com.sofn.common.config.ApplicationContextRegister;
import org.apache.commons.io.FilenameUtils;
import org.apache.tika.Tika;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.csource.fastdfs.UploadStream;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * fastdfs工具类
 *
 * @author lijiang
 * @create 2019-04-01 16:32
 **/
public final class FastDFSUtils {
    /**
     * 元数据名常量：文件名
     */
    public static final String METADATA_NAME_FILENAME = "fileName";
    /**
     * 元数据名常量：文件扩展名
     */
    public static final String METADATA_NAME_FILEEXTNAME = "fileExtName";
    /**
     * 元数据名常量：文件字节数
     */
    public static final String METADATA_NAME_FILELENGTH = "fileLength";
    /**
     * 元数据名常量：文件内容类型
     */
    public static final String METADATA_NAME_CONTENTTYPE = "contentType";
    /**
     * 禁止上传文件名后缀
     */
    public static final String FORBID_FILENAME_SUFFIX = FastDFSUtils.getFastdfsProperties().getForbidFilenameSuffix();
    /**
     * 禁止上传文件media_type
     */
    public static final String FORBID_MEDIA_TYPE = FastDFSUtils.getFastdfsProperties().getForbidMediaType();
    /**
     * 上传文件大小上限（单位：字节）
     */
    public static final long UPLOAD_FILE_SIZE_UPPER = FastDFSUtils.getFastdfsProperties().getUploadFileSizeUpper();
    /**
     * 禁止上传文件的错误提示消息
     */
    public static final String FORBID_FILE_UPLOAD_MESSAGE = "该类型的文件禁止上传";
    /**
     * 上传文件大小超出上限错误提示消息
     */
    public static final String EXCEED_SIZE_UPPER_MESSAGE = "文件大小超出上限";

    private FastDFSUtils() {
    }

    /**
     * 获取fastdfs文件服务器客户端配置项
     *
     * @return 客户端配置项
     */
    public static FastDFSProperties getFastdfsProperties() {
        return (FastDFSProperties) ApplicationContextRegister.getApplicationContext().getBean("fastdfsProperties");
    }

    /**
     * 获取TrackerServer连接池
     *
     * @return TrackerServer连接池
     */
    public static TrackerServerPool getTrackerServerPool() {
        return (TrackerServerPool) ApplicationContextRegister.getApplicationContext().getBean("trackerServerPool");
    }

    /**
     * 文件上传
     *
     * <p>
     * 方法内部调用uploadFile(InputStream inputStream, long fileSize, String fileExtName, NameValuePair[] metaArray)实现文件上传， 无JVM堆内存溢出问题
     * </p>
     *
     * @param file A representation of an uploaded file received in a multipart request (在多部分请求中接收的上传文件的表示形式)
     * @return 已上传文件信息
     * @throws Exception 上传文件发生的异常
     * @see FastDFSUtils#uploadFile(InputStream, long, String, NameValuePair[], byte[]);
     */
    public static UploadedFileInfo uploadFile(MultipartFile file) throws Exception {
        final String fileName = file.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)) {
            throw new IllegalArgumentException("上传文件名不能为空");
        }

        final String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 设置文件元数据
        final NameValuePair[] fileMetaArray = new NameValuePair[4];
        fileMetaArray[0] = new NameValuePair(METADATA_NAME_FILENAME, fileName);
        fileMetaArray[1] = new NameValuePair(METADATA_NAME_FILEEXTNAME, fileExtName);
        fileMetaArray[2] = new NameValuePair(METADATA_NAME_FILELENGTH, String.valueOf(file.getSize()));
        fileMetaArray[3] = new NameValuePair(METADATA_NAME_CONTENTTYPE, file.getContentType());

        return uploadFile(file.getInputStream(), file.getSize(), fileExtName, fileMetaArray, file.getBytes());
    }

    /**
     * 文件上传
     * <p>
     * 温馨提示： 上传大体积的文件推荐使用该方法，无JVM堆内存溢出问题
     * </p>
     *
     * @param inputStream  文件输入流
     * @param fileSize     文件字节数
     * @param fileExtName  文件扩展名
     * @param metaArray    文件元数据，例如：文件名，文件字节数，等等
     * @param contentBytes 包含文件内容的字节数组，用于检测文件媒体类型
     * @return 已上传文件信息
     * @throws Exception 上传文件发生的异常
     */
    public static UploadedFileInfo uploadFile(InputStream inputStream, long fileSize, String fileExtName, NameValuePair[] metaArray, byte[] contentBytes) throws Exception {
        final String downloadPath = getFastdfsProperties().getDownloadPath();
        String groupNameAndFilename = null;

        if (!StringUtils.isEmpty(fileExtName)) {
            // 检测文件名后缀是否为禁止上传类型
            if (FORBID_FILENAME_SUFFIX.contains(fileExtName)) {
                throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
            }
        }

        Tika tika = new Tika();
        String mediaType;
        try {
            if (contentBytes != null) {
                mediaType = tika.detect(contentBytes);
            } else {
                mediaType = tika.detect(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        // 检测文件媒体类型是否为禁止上传类型，这一步是为了防止上面被禁止上传的文件后缀名修改为可上传类型的情况
        if (FORBID_MEDIA_TYPE.contains(mediaType)) {
            throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
        }

        // 检测文件大小是否超出上限值
        if (fileSize > UPLOAD_FILE_SIZE_UPPER) {
            throw new IllegalArgumentException(EXCEED_SIZE_UPPER_MESSAGE);
        }

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = getTrackerServerPool();
        final TrackerServer trackerServer = trackerServerPool.borrowObject();
        final StorageClient1 client = new StorageClient1(trackerServer, null);

        //上传文件 (2018-3-29  解决大文件上传可能会内存溢出的问题)
        try {
            groupNameAndFilename = client.upload_file1(null, fileSize, new UploadStream(inputStream, fileSize), fileExtName, metaArray);
        } catch (IOException | MyException e) {
            throw e;
        } finally {
            trackerServerPool.returnObject(trackerServer);
            inputStream.close();
        }

        if (groupNameAndFilename != null) {
            UploadedFileInfo fileInfo = new UploadedFileInfo();
            fileInfo.setFileExtName(fileExtName);
            fileInfo.setFileLength(fileSize);
            fileInfo.setFileUrl(String.format("%s%s", downloadPath, groupNameAndFilename));
            for (NameValuePair meta : metaArray) {
                if (METADATA_NAME_FILENAME.equals(meta.getName())) {
                    fileInfo.setFileName(meta.getValue());
                    continue;
                }
                if (METADATA_NAME_CONTENTTYPE.equals(meta.getName())) {
                    fileInfo.setContentType(meta.getValue());
                }
            }

            return fileInfo;
        } else {
            return null;
        }
    }

    /**
     * 文件上传
     *
     * @param fileName    不包含完整路径的文件名,例如: aaa.txt， 文件名不能为空串或null，且必须包含扩展名
     * @param contentType 文件内容的类别，该参数在不确定或无法获取的情况下可以为空
     * @param fileBytes   包含文件内容的字节数组
     * @return 已上传文件信息
     * @throws Exception
     * @see FastDFSUtils#uploadFile(java.lang.String, java.lang.String, org.csource.common.NameValuePair[], byte[])
     */
    public static UploadedFileInfo uploadFile(String fileName, String contentType, byte[] fileBytes) throws Exception {
        final ArrayList<NameValuePair> metaList = new ArrayList<>();
        final String fileExtName = FilenameUtils.getExtension(fileName);

        if (StringUtils.isEmpty(fileExtName)) {
            throw new IllegalArgumentException("上传文件名为空或者文件扩展名不存在");
        }

        metaList.add(new NameValuePair(METADATA_NAME_FILENAME, fileName));
        metaList.add(new NameValuePair(METADATA_NAME_FILEEXTNAME, fileExtName));
        metaList.add(new NameValuePair(METADATA_NAME_FILELENGTH, String.valueOf(fileBytes.length)));
        if (!StringUtils.isEmpty(contentType)) {
            metaList.add(new NameValuePair(METADATA_NAME_CONTENTTYPE, contentType));
        }

        return uploadFile(fileName, fileExtName, metaList.toArray(new NameValuePair[0]), fileBytes);
    }

    /**
     * 文件上传
     *
     * @param fileName  不包含完整路径的文件名,例如: aaa.txt， 文件名不能为空串或null，且必须包含扩展名
     * @param fileBytes 包含文件内容的字节数组
     * @return 已上传文件信息
     * @throws Exception
     * @see FastDFSUtils#uploadFile(java.lang.String, java.lang.String, byte[])
     */
    public static UploadedFileInfo uploadFile(String fileName, byte[] fileBytes) throws Exception {
        return uploadFile(fileName, null, fileBytes);
    }

    /**
     * 文件上传
     *
     * @param fileName    不包含完整路径的文件名,例如: aaa.txt
     * @param fileExtName 文件扩展名，例如： doc,jpg,png,gif 等等
     * @param metaArray   文件元数据，例如：文件名，文件字节数，等等
     * @param fileBytes   包含文件内容的字节数组
     * @return 已上传文件信息
     * @throws Exception
     */
    public static UploadedFileInfo uploadFile(String fileName, String fileExtName, NameValuePair[] metaArray, byte[] fileBytes) throws Exception {
        final String downloadPath = getFastdfsProperties().getDownloadPath();
        String groupNameAndFilename = null;
        if (!StringUtils.isEmpty(fileExtName)) {
            // 检测文件名后缀是否为禁止上传类型
            if (FORBID_FILENAME_SUFFIX.contains(fileExtName)) {
                throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
            }
        }

        Tika tika = new Tika();
        String mediaType = tika.detect(fileBytes);
        // 检测文件媒体类型是否为禁止上传类型，这一步是为了防止上面被禁止上传的文件后缀名修改为可上传类型的情况
        if (FORBID_MEDIA_TYPE.contains(mediaType)) {
            throw new IllegalArgumentException(FORBID_FILE_UPLOAD_MESSAGE);
        }

        // 检测文件大小是否超出上限值
        if (fileBytes.length > UPLOAD_FILE_SIZE_UPPER) {
            throw new IllegalArgumentException(EXCEED_SIZE_UPPER_MESSAGE);
        }

        // 从连接池获取TrackerServer实例并创建fastdfs文件客户端
        TrackerServerPool trackerServerPool = getTrackerServerPool();
        final TrackerServer trackerServer = trackerServerPool.borrowObject();
        final StorageClient1 client = new StorageClient1(trackerServer, null);

        try {
            groupNameAndFilename = client.upload_file1(fileBytes, fileExtName, metaArray);
        } catch (IOException | MyException e) {
            throw e;
        } finally {
            trackerServerPool.returnObject(trackerServer);
        }

        if (groupNameAndFilename != null) {
            UploadedFileInfo fileInfo = new UploadedFileInfo();
            fileInfo.setFileName(fileName);
            fileInfo.setFileExtName(fileExtName);
            long size = fileBytes.length;
            fileInfo.setFileLength(size);
            fileInfo.setFileUrl(String.format("%s%s", downloadPath, groupNameAndFilename));

            return fileInfo;
        } else {
            return null;
        }
    }

    /**
     * 解析文件下载链接
     *
     * @param fileUrl 文件下载链接
     * @return fastdfs文件信息
     */
    private static FastdfsFileInfo parseFileUrl(final String fileUrl) {
        FastdfsFileInfo fileInfo = new FastdfsFileInfo();
        String substr = fileUrl.substring(fileUrl.indexOf("group"));
        fileInfo.setGroupName(substr.split("/")[0]);
        fileInfo.setRemoteFileName(substr.substring(substr.indexOf("/") + 1));

        return fileInfo;
    }
}
