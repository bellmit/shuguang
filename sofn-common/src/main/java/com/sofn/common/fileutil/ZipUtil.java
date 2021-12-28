package com.sofn.common.fileutil;

import com.sofn.common.exception.SofnException;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩文件工具类
 * @author heyongjie
 * @date 2019/6/6 10:27
 */
public class ZipUtil {

    /**
     * @param args 主方法
     */
    public static void main(String[] args) throws Exception {
        //第一个参数是需要压缩的源路径；第二个参数是压缩文件的目的路径，这边需要将压缩的文件名字加上去
        compress("C:\\Users\\heyongjie\\Desktop\\tempFilePath", "C:\\Users\\heyongjie\\Desktop\\tempFilePath.zip");
    }

    /**
     * 压缩文件
     *
     * @param srcFilePath  压缩源路径
     * @param destFilePath 压缩目的路径   这里需要具体的文件详细路径
     */
    public static void compress(String srcFilePath, String destFilePath) throws Exception {
        //
        File src = new File(srcFilePath);

        if (!src.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }
        File zipFile = new File(destFilePath);
        if(!zipFile.exists()){
            createNewFile(zipFile);
        }

        FileOutputStream fos = new FileOutputStream(zipFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        String baseDir = "";
        compressbyType(src, zos, baseDir);
        zos.close();

    }
    
    public static File createNewFile(File file)throws IOException {
        if (!file.exists()) {
            mkdir(file.getParentFile());
            boolean newFile = file.createNewFile();
            if(!newFile) {
                throw new SofnException("创建文件失败");
            }
        }
        return file;
    }
    
    private static void mkdir(File dir) {
        if (!dir.getParentFile().exists()) {
            mkdir(dir.getParentFile());
        }
        dir.mkdir();
    }

    /**
     * 按照原路径的类型就行压缩。文件路径直接把文件压缩，
     *
     * @param src  需要压缩哪个文件
     * @param zos  压缩流
     * @param baseDir   压缩到哪里
     */
    private static void compressbyType(File src, ZipOutputStream zos, String baseDir) throws Exception {

        if (!src.exists()){
            return;
        }

        System.out.println("压缩路径" + baseDir + src.getName());
        //判断文件是否是文件，如果是文件调用compressFile方法,如果是路径，则调用compressDir方法；
        if (src.isFile()) {
            //src是文件，调用此方法
            compressFile(src, zos, baseDir);
        } else if (src.isDirectory()) {
            //src是文件夹，调用此方法
            compressDir(src, zos, baseDir);

        }

    }

    /**
     * 压缩文件
     */
    private static void compressFile(File file, ZipOutputStream zos, String baseDir) throws Exception {
        if (!file.exists()){
            return;
        }
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        ZipEntry entry = new ZipEntry(baseDir + file.getName());
        zos.putNextEntry(entry);
        int count;
        byte[] buf = new byte[1024];
        while ((count = bis.read(buf)) != -1) {
            zos.write(buf, 0, count);
        }
        bis.close();
    }

    /**
     * 压缩文件夹
     */
    private static void compressDir(File dir, ZipOutputStream zos, String baseDir) throws Exception {
        if (!dir.exists()){
            return;
        }
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            try {
                zos.putNextEntry(new ZipEntry(baseDir + dir.getName() + File.separator));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(files != null){
            for (File file : files) {
                compressbyType(file, zos, baseDir + dir.getName() + File.separator);
            }
        }
    }

}
