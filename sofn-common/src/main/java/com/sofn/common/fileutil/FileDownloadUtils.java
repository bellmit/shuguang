package com.sofn.common.fileutil;

import com.sofn.common.exception.SofnException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件下载工具
 *
 * @author heyongjie
 * @date 2019/8/1 10:53
 */
@Slf4j
public class FileDownloadUtils {

    /**
     * 根据文件名称和文件内容下载文件
     *
     * @param fileName  文件名称
     * @param fileBytes 文件内容
     * @param response  HttpServletResponse
     */
    public static void downloadFile(String fileName, byte[] fileBytes, HttpServletResponse response) {
        InputStream is = new ByteArrayInputStream(fileBytes);
        downloadFile(is, response, fileName);
    }


    /**
     * 下载文件
     *
     * @param filePath 文件路径
     * @param response HttpServletResponse
     */
    public static void downloadFile(String filePath, HttpServletResponse response) {
        BufferedInputStream br;
        File file = new File(filePath);
        if (file.exists()) {
            try {
                br = new BufferedInputStream(new FileInputStream(file));
                downloadFile(br, response, file.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new SofnException("文件不存在");
            }
        }
    }

    /**
     * 创建文件
     *
     * @param filePath 创建后的文件实际地址
     * @param is       InputStream
     */
    public static void createFile(String filePath, InputStream is) {
        File file = new File(filePath);
        FileOutputStream fos = null;
        if (file.exists()) {
            log.info("文件{}已经存在，开始删除", filePath);
            file.delete();
        }
        try {
            file.createNewFile();
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            int read;
            while ((read = is.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("写文件失败");
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 下载文件并总是删除文件
     *
     * @param filePath 文件路径
     * @param response HttpServletResponse
     */
    public static void downloadAndDeleteFile(String filePath, HttpServletResponse response) {
        if (!StringUtils.isBlank(filePath)) {
            File file = new File(filePath);
            if (file.exists()) {
                try {
                    downloadFile(filePath, response);
                } finally {
                    try {
                        Thread.sleep(3000);
                        file.delete();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                log.info("downloadAndDeleteFile文件{}不存在", filePath);
            }
        } else {
            log.info("downloadAndDeleteFile请选择要下载的文件");
        }
    }

    /**
     * 下载文件
     *
     * @param is       InputStream
     * @param response HttpServletResponse
     * @param fileName 文件名称
     */
    public static void downloadFile(InputStream is, HttpServletResponse response, String fileName) {
        OutputStream outputStream = null;
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentLength(is.available());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename="+fileName+";filename*=utf-8''"
                    + URLEncoder.encode(fileName, "utf-8"));
            byte[] bytes = new byte[1024];
            int read;
            outputStream = response.getOutputStream();
            while ((read = (is.read(bytes))) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("文件下载失败");
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
