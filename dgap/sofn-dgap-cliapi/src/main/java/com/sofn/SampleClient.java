package com.sofn;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sofn.model.dgap.DataImportResult;
import com.sofn.wsClient.ex.DataxExportClient;
import com.sofn.wsClient.ex.DataxImportClient;

import org.apache.commons.io.FileUtils;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.io.File;
import java.util.List;

/**
 * Created by helong on 18-5-8.
 */
public class SampleClient {

    @Parameter(
            names = {"--token", "-t"},
            description = "token"
    )
    String token = null;

    @Parameter(
            names = {"--url"},
            description = "url"
    )
    String url = null;

    @Parameter(
            names = {"--verbose", "-V"},
            description = "verbose"
    )
    boolean verbose = false;

    @Parameter(
            names = {"--cmd", "-c"},
            description = "cmds like: gen-sample, data-import, data-export, datalog-export"
    )
    String cmd = null;

    @Parameter(
            names = {"--classname", "-C"},
            description = "classname"
    )
    String classname = "com.sofn.wsClient.samplebeans.GreenCertification";

    @Parameter(
            names = {"--op", "-o"},
            description = "operations like: add, update, delete"
    )
    String op=null;

    @Parameter(
            names = {"--start", "-s"},
            description = "start index"
    )
    int start=0;

    @Parameter(
            names = {"--end", "-e"},
            description = "end index"
    )
    int end=0;

    @Parameter(
            names = {"--condition"},
            description = "condition for exporting data"
    )
    String condition=null;

    @Parameter(
            names = {"--position"},
            description = "position to fetch data from"
    )
    String pos=null;

    @Parameter(
            names = {"--file", "-f"},
            description = "file to operate"
    )
    String file="./sample.json";

    @Parameter(
            names = {"--help", "-h"},
            help = true
    )
    private boolean help;

    public static void main(String[] args) throws Exception {

        SampleClient cli = new SampleClient();
        JCommander jc = JCommander.newBuilder().addObject(cli)
                .build();

        jc.parse(args);
        if (cli.help) {
            jc.usage();
            return;
        }

        ObjectMapper mapper = new ObjectMapper();
        Class clazz = Class.forName(cli.classname);
        PodamFactory factory = new PodamFactoryImpl();
        Object data = factory.manufacturePojo(clazz);
        //Object data = clazz.newInstance();

        if(cli.cmd == null){
            System.out.print("--cmd must be specified!");
            System.exit(-1);
        }
        switch (cli.cmd) {
            case "gen-sample": {
                File file = null;
                if (cli.file == null) {
                    file = File.createTempFile("dgap-", "-data.json");
                } else {
                    file = new File(cli.file);
                }

                String jsonString = mapper.writeValueAsString(data);
                FileUtils.write(file, jsonString, "utf8");
                System.out.println("sample data was written to file:" + file.getPath());
                break;
            }
            case "data-import":
                if(cli.token == null || cli.url == null){
                    System.out.print("--token and --url must be specified!");
                    System.exit(-1);
                }
                DataxImportClient client = new DataxImportClient(cli.url);

                if(cli.file == null){
                    System.out.print("--file must be specified!");
                    System.exit(-1);
                }

                data = mapper.readValue(new File(cli.file), clazz);

                if(cli.op == null){
                    System.out.print("--op must be specified!");
                    System.exit(-1);
                }
                DataImportResult result = null;
                switch (cli.op){
                    case "add":
                        result = client.addData(cli.token, data);
                        break;
                    case "update":
                        result = client.updateData(cli.token, data);
                        break;
                    case "delete":
                        result = client.deleteData(cli.token, data);
                        break;
                }
                if (!result.isSuccess()) {
                    System.out.println(result.getErrorCode());
                }else{
                    System.out.println("success!");
                }

                break;

            case "data-export": {
                if (cli.token == null || cli.url == null) {
                    System.out.print("--token and --url must be specified!");
                    System.exit(-1);
                }
                DataxExportClient exclient = new DataxExportClient(cli.url);

                List<Object> list = exclient.getData(cli.token, cli.start, cli.end, cli.condition, clazz);
                if (list != null && !list.isEmpty()) {
                    for (Object gc : list) {
                        String jsonString = mapper.writeValueAsString(gc);
                        System.out.println(jsonString);
                    }
                } else {
                    System.out.println("empty result!");
                }

                break;
            }

            case "datalog-export": {
                if (cli.token == null || cli.url == null) {
                    System.out.print("--token and --url must be specified!");
                    System.exit(-1);
                }
                DataxExportClient exclient = new DataxExportClient(cli.url);

                List<Object> list = exclient.getDataChangeLog(cli.token,cli.pos, clazz);
                if (list != null && !list.isEmpty()) {
                    for (Object gc : list) {
                        String jsonString = mapper.writeValueAsString(gc);
                        System.out.println(jsonString);
                    }
                } else {
                    System.out.println("empty result!");
                }

                break;
            }

        }
        System.exit(0);
    }

}
