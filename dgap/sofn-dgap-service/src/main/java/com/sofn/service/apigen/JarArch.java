package com.sofn.service.apigen;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;

//import org.codehaus.plexus.archiver.jar.JarArchiver;
/**
 * Created by helong on 18-5-7.
 */
public class JarArch {

    public static void createJar(String dir, String destFile) throws IOException {
/*
        JarArchiver archiver = new JarArchiver();
        archiver.addDirectory(new File(dir));
        archiver.setDestFile(new File(destFile));
        archiver.createArchive();
*/
    }

    public static void createZip(String dir, String destFile){
        try {
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            ZipFile zipFile = new ZipFile(destFile);

            File targetFile = new File(dir);

            for(File file:targetFile.listFiles()) {
                if(file.isFile()){
                    zipFile.addFile(file, parameters);
                }else if(file.isDirectory()) {
                    zipFile.addFolder(file, parameters);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean unzipTo(String destFile, String destDir){
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(destFile);
            zipFile.extractAll(destDir);
            return true;
        } catch (ZipException e) {
            System.out.println("unzip file failed:"+destFile);
            e.printStackTrace();
            return false;
        }
    }
}
