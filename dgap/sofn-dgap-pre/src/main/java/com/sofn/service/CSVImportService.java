package com.sofn.service;

import com.sofn.core.support.dubbo.spring.annotation.DubboReference;
import com.sofn.model.dgap.Constants;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.model.dgap.FieldData;
import com.sofn.model.dgap.RowData;
import com.sofn.model.generator.TDgapResource;
import com.sofn.provider.dgap.DataImportProvider;
import com.sofn.provider.dgap.TDgapResourceProvider;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.util.*;

/**
 * Created by helong on 17-7-4.
 */
@Service
public class CSVImportService {
    @Autowired
    @NotNull
    private DataImportService dataImportService;

    private final static String TOKEN = "csv-import";

    @Value("${dataImport.csv.maxrows}")
    long MAXLENGTH;

    @Value("${datafile.import.basedir}")
    @NotNull
    String basedir = "/tmp";

    protected static Logger logger = LoggerFactory.getLogger(CSVImportService.class);

    @DubboReference
    @NotNull
    private TDgapResourceProvider resourceProvider;

    private String getFileZoneDir(@NotNull String subDirName) {
        return basedir+File.separator+subDirName;
    }

    public boolean newFilesArrived(@NotNull String filePath){
        String resourceId = getResourceIdByFilePath(filePath);
        if(resourceId == null)
            return false;

        String toDir = getFileZoneDir(resourceId);
        List<FileOpStatus> cpResults = noRecCopyFilesTo(resourceId, filePath, toDir, true);
        List<FileOpStatus> impResults = importResourceFiles(resourceId, toDir);
        return true;
    }

    private String getResourceIdByFilePath(@NotNull String filePath) {
        List<TDgapResource> resources = resourceProvider.getResourceByFilePath(filePath);
        if(resources.size() == 1){
            return resources.get(0).getId();
        }else {
            return null;
        }
    }

    static @NotNull List<FileOpStatus> noRecCopyFilesTo(@NotNull String resourceId,
                                               @NotNull String srcPath, @NotNull String destDir, boolean deleteSource){
        List<FileOpStatus> result = new ArrayList<FileOpStatus>();
        Queue<String> toProcessList = new LinkedList<String>();
        FileOpStatus opStatus = null;
        File dDir = null;
        if (destDir != null){
            dDir = new File(destDir);
            if(!dDir.exists()){
                dDir.mkdirs();
            }
        }
        File s = new File(srcPath);
        if(s.exists() && s.isFile()){
            srcPath = s.getParent();
        }
        toProcessList.add(s.getPath());
        do {
            String toProcessPath = toProcessList.remove();
            File f = new File(toProcessPath);
            File d = new File(toProcessPath.replace(srcPath, destDir));
            try {
                if (f.exists() && f.isDirectory()) {
                    if (!d.exists()) {
                        Files.copy(f.toPath(), d.toPath());
                        opStatus = getPrintOpStatus(FileOpStatus.OP.COPY, true, resourceId, f.getPath(), d.getPath(), null);
                        result.add(opStatus);
                    } else {
                        logger.warn(Constants.Features.Dgappre_csv.logTag()+":directory named "+d.toPath()+" already exists!");
                    }

                    File[] files = f.listFiles();
                    for (File file : files) {
                        toProcessList.add(file.getPath());
                    }
                } else if(f.exists()){
                    FileTime mTime = Files.getLastModifiedTime(f.toPath());
                    Files.copy(f.toPath(), d.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    opStatus = getPrintOpStatus(FileOpStatus.OP.COPY, true, resourceId, f.getPath(), d.getPath(), null);
                    result.add(opStatus);

                    if(deleteSource) {
                        try {
                            Files.delete(f.toPath());
                        } catch (IOException e) {
                            opStatus = getPrintOpStatus(FileOpStatus.OP.DELETE_SOURCE_FILE, false, resourceId, f.getPath(), d.getPath(), e.getClass().getSimpleName());
                            result.add(opStatus);
                            e.printStackTrace();
                        }
                    }
                } else {
                    opStatus = getPrintOpStatus(FileOpStatus.OP.COPY, true, resourceId, f.getPath(), d.getPath(), "source path not exists");
                    result.add(opStatus);
                }
            } catch (IOException e) {
                opStatus = getPrintOpStatus(FileOpStatus.OP.COPY, false, resourceId, f.getPath(), d.getPath(), e.getClass().getSimpleName());
                result.add(opStatus);
                e.printStackTrace();
            }
        }while(!toProcessList.isEmpty());
        return result;
    }

    static @NotNull FileOpStatus getPrintOpStatus(FileOpStatus.OP op, boolean success,
                                                  @NotNull String resourceId,
                                                  String srcPath, String destPath, String errorMsg){
        FileOpStatus opStatus = new FileOpStatus(op, success, resourceId, srcPath, destPath, errorMsg);
        logger.debug(Constants.Features.Dgappre_csv.logTag()+":"+opStatus.toString());
        return opStatus;
    }

    @NotNull List<FileOpStatus> importResourceFiles(@NotNull String resourceId, @NotNull String dirPath){
        List<FileOpStatus> statusList = new ArrayList<FileOpStatus>();
        FileOpStatus opStatus = null;
        //check files needed to process
        File[] files = new File(dirPath).listFiles();
        if(files == null) {
            opStatus = getPrintOpStatus(FileOpStatus.OP.IMPORT,false,resourceId, null,dirPath,null);
            statusList.add(opStatus);
            return statusList;
        }
        for(File file:files){
            try {
                if(file.isFile()) {
                    opStatus = importCsvFile(resourceId, file.getPath());
                }
                if(opStatus.success){
                    try {
                        Files.delete(file.toPath());
                    }catch(Exception e){
                        opStatus = getPrintOpStatus(FileOpStatus.OP.DELETE_IMPORT_FILE,false, resourceId, null,file.getPath(),null);
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                opStatus = getPrintOpStatus(FileOpStatus.OP.IMPORT,false,resourceId, null,file.getPath(),null);
                e.printStackTrace();
            }
            statusList.add(opStatus);
        }
        return statusList;
    }

    @NotNull FileOpStatus importCsvFile(@NotNull String resourceId,@NotNull String csvFilePath) throws IOException {

        //fetch 1st line
        List<RowData> data = readCsvFile(csvFilePath);

        DataImportResult result = dataImportService.insertBatch(TOKEN,resourceId, data);

        return getPrintOpStatus(FileOpStatus.OP.IMPORT,result.isSuccess(),resourceId, null,csvFilePath,null);
    }

    @NotNull List<RowData> readCsvFile(@NotNull String csvFilePath) throws IOException {
        Reader in = new FileReader(csvFilePath);
        List<RowData> rows = new ArrayList<RowData>();
        try {
            CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
            Map<String, Integer> headers = parser.getHeaderMap();

            for (CSVRecord record : parser) {
                if (parser.getRecordNumber() > (MAXLENGTH - 1)) {
                    throw new RuntimeException("csv file too large, exceeds limit of record count of:" + MAXLENGTH);
                }
                RowData row = new RowData();
                List<FieldData> fields = new ArrayList<FieldData>();
                for (String header : headers.keySet()) {
                    if ("ID".equals(header)) {
                        row.setId(record.get(header));
                    } else {
                        FieldData field = new FieldData();
                        field.setColumnName(header);
                        field.setValue(record.get(header));
                        fields.add(field);
                    }
                }
                row.setFieldsData(fields);
                rows.add(row);
            }
        }finally {
            if(in !=null) {
                in.close();
            }
        }
        return rows;
    }

    public static class FileOpStatus {
        enum OP {
            COPY,DELETE_SOURCE_FILE,IMPORT,DELETE_IMPORT_FILE
        }
        OP op;
        String resourceId;
        String srcPath;
        String destPath;

        @Override
        public String toString() {
            return "FileOpStatus{" +
                    "op=" + op +
                    ", resourceId='" + resourceId + '\'' +
                    ", srcPath='" + srcPath + '\'' +
                    ", destPath='" + destPath + '\'' +
                    ", success=" + success +
                    ", errorMsg='" + errorMsg + '\'' +
                    '}';
        }

        boolean success;

        public FileOpStatus(OP op,boolean success,String resourceId, String srcPath, String destPath,  String errorMsg) {
            this.op = op;
            this.resourceId = resourceId;
            this.srcPath = srcPath;
            this.destPath = destPath;
            this.success = success;
            this.errorMsg = errorMsg;
        }

        String errorMsg;
    }


}
