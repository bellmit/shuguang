package com.sofn.service.apigen;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.sofn.core.util.SecurityUtil;
import org.apache.commons.io.FileUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.ObjectFactory;
import org.mybatis.generator.internal.util.StringUtility;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by helong on 18-5-7.
 */
public class ApiGenerator {

    private static final byte[] key = { 9, -1, 0, 5, 39, 8, 6, 19 };

    public static byte[] genApiJar(String connectionURL, String username, String password,
                                   String targetPackage, String targetProject,
                                   String schemaName, String[] tableNames, boolean clean) throws IOException {

        //ObjectFactory.addExternalClassLoader(Thread.currentThread().getContextClassLoader());
        String cliapiFile = ApiGenerator.class.getClassLoader().getResource("client-codes/cliapi.zip").getFile();
        JarArch.unzipTo(cliapiFile,targetProject);
        password = SecurityUtil.decryptDes(password,key);
        String targetDir = new File(targetProject,"sofn-dgap-cliapi/src/main/java").getAbsolutePath();

        byte[] buf = new byte[0];
        try {
            buf = genMybatisConfig(connectionURL, username, password,
                    targetPackage, targetDir,
                    schemaName, tableNames);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.print("configure:"+new String(buf));
        Reader r = new StringReader(new String(buf));

        try {
            genApiJavaSources(r, targetDir);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String tempFileName = "/tmp/dgap-" + System.currentTimeMillis() + ".zip";
        JarArch.createZip(targetProject, tempFileName);
        byte[] fc = FileUtils.readFileToByteArray(new File(tempFileName));
        if(clean) {
            new File(tempFileName).delete();
            FileUtils.deleteDirectory(new File(targetProject));
        }
        return fc;
    }

    static byte[] genMybatisConfig(String connectionURL, String username, String password,
                                   String targetPackage, String targetProject,
                                   String schemaName, String[] tableNames) throws IOException {
        Writer writer = new OutputStreamWriter(System.out);
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("mybatis-gen-config.xml.mustache");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        mustache.execute(new PrintWriter(baos), new Setting(connectionURL, username, password,
                targetPackage, targetProject,
                schemaName, tableNames)).flush();
        return baos.toByteArray();
    }

    static void genApiJavaSources(Reader configure, String outputDir) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        boolean verbose = true;
        Set<String> contextsToRun = new HashSet<String>();

        Set<String> fullyqualifiedTables = new HashSet<String>();

        ConfigurationParser cp = new ConfigurationParser(null, warnings);
        Configuration config = cp.parseConfiguration(configure);

        ShellCallback callback = new StandardShellCallback(outputDir, overwrite);

        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null, contextsToRun, fullyqualifiedTables);

        for (String error : warnings) {
            System.out.println(error);
        }
    }

    static ProgressCallback getProgressCallback() {
        return new ProgressCallback() {

            @Override
            public void introspectionStarted(int i) {
                System.out.println("introspectionStarted:" + i);
            }

            @Override
            public void generationStarted(int i) {
                System.out.println("generationStarted:" + i);
            }

            @Override
            public void saveStarted(int i) {
                System.out.println("saveStarted:" + i);
            }

            @Override
            public void startTask(String s) {
                System.out.println("startTask:" + s);
            }

            @Override
            public void done() {
                System.out.println("done");
            }

            @Override
            public void checkCancel() throws InterruptedException {
                System.out.println("checkCancel");
            }
        };
    }

    static class Table {
        public Table(String schema, String tableName) {
            this.schema = schema;
            this.tableName = tableName;
        }

        String schema;
        String tableName;
    }

    static class Setting {

        public Setting(String connectionURL, String username, String password, String targetPackage, String targetProject,
                       String schemaName, String[] tableNames) {
            this.connectionURL = connectionURL;
            this.userId = username;
            this.password = password;
            this.targetPackage = targetPackage;
            this.targetProject = targetProject;
            tables = new ArrayList<Table>();
            for (String tableName : tableNames) {
                Table t = new Table(schemaName, tableName);
                tables.add(t);
            }
        }

        String connectionURL;
        String userId;
        String password;

        String targetPackage;
        String targetProject;

        List<Table> tables;

        List<Table> tables() {
            return tables;
        }
    }


}
