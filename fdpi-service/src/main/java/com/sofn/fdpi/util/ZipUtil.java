package com.sofn.fdpi.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩和解压
 *
 * @author L
 */
public class ZipUtil {
    /**
     * 压缩多个文件到zop
     *
     * @param filePath         需要压缩的文件
     * @param zipPath          需要压缩到的zip路径
     * @param keepDirStructure 是否保持原目录结构
     * @return
     */
    public static int compress(List<String> filePath, String zipPath, Boolean keepDirStructure) {
        byte[] buf = new byte[1024];
        File zipFile = new File(zipPath);
        int zipCount = 0;
        try {
            if (!zipFile.exists()) {
                zipFile.createNewFile();
            }
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < filePath.size(); i++) {
                String relativePath = filePath.get(i);
                if (StringUtils.isEmpty(relativePath)) {
                    continue;
                }
                File sourceFile = new File(relativePath);
                if (sourceFile == null || !sourceFile.exists()) {
                    continue;
                }
                FileInputStream fis = new FileInputStream(sourceFile);
                if (keepDirStructure != null && keepDirStructure) {
                    zos.putNextEntry(new ZipEntry(relativePath));
                } else {
                    zos.putNextEntry(new ZipEntry(sourceFile.getName()));
                }
                int len;
                while ((len = fis.read(buf)) > 0) {
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                fis.close();
                zipCount++;
            }
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zipCount;
    }
    public static void createZip(ZipOutputStream zos, String fileName, byte[] b) {
        try {
            // //create zip
            zos.putNextEntry(new ZipEntry(fileName));
            // set the declear
            zos.write(b, 0, b.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
