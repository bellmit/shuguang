package com.sofn.core.util;


import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;

import java.io.File;

/**
 * Created by wuye on 2016/11/11.
 */
public class OpenOfficeUtil {

    /**
     * 转为PDF
     *
     * @param
     */
    public static boolean doc2pdf(File docFile,File pdfFile) throws Exception {
        try {
            if(!docFile.exists()){
                return false;
            }

            if(!pdfFile.exists()){
                pdfFile.getParentFile().mkdirs();
            }
            OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1",8100);
           // SocketOpenOfficeConnection connection1 = new SocketOpenOfficeConnection("172.16.7.38",8100);
            connection.connect();
            DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
            converter.convert(docFile,pdfFile);
            connection.disconnect();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
