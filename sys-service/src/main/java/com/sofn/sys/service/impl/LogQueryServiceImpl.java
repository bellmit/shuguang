package com.sofn.sys.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.IdUtil;
import com.sofn.common.utils.JsonUtils;
import com.sofn.common.utils.PageUtils;
import com.sofn.common.utils.RedisHelper;
import com.sofn.sys.mapper.SysBaseDataMapper;
import com.sofn.sys.mapper.SysLogMapper;
import com.sofn.sys.model.SysBaseData;
import com.sofn.sys.model.SysLog;
import com.sofn.sys.service.LogQueryService;
import com.sofn.sys.vo.*;
import com.xxl.job.core.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author heyongjie
 * @date 2020/9/16 17:28
 */
@Service
@Slf4j
public class LogQueryServiceImpl implements LogQueryService {

    @Autowired
    private RestClient restClient;

    @Autowired
    private SysLogMapper sysLogMapper;

    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private SysBaseDataMapper sysBaseDataDao;

    private static final int THREAD_COUNT = 10;
    private static ExecutorService pool = Executors.newFixedThreadPool(1000);

    @Value("${spring.profiles.active}")
    private String env;

    private static final ObjectMapper MAPPER = new ObjectMapper();


    @Override
    public String getLogInfo(String date, String keyword, String appId, String startDate, String endDate, Integer pageNo, Integer pageSize) {
        // 这里使用低级客户端主要是为了熟悉API
        // /sys_dev_operate-2020.09.16/doc/_search
        String indexName = String.format("%s_%s_operate-%s", appId, "dev", "*");
        String url = "/" + indexName + "/_doc/_search";


        // 设置分页信息
        Map<String, Object> data = Maps.newHashMap();
        data.put("from", pageNo);
        data.put("size", pageSize);

        // 设置展示列
        Map<String, Object> showColumn = Maps.newHashMap();
        showColumn.put("include", Lists.newArrayList("message", "@timestamp","logdt"));
        data.put("_source", showColumn);

        // 设置查询条件
        if (StringUtils.isNotBlank(keyword) || StringUtils.isNotBlank(startDate)) {
            Map<String, Object> queryMap = Maps.newHashMap();
            if (StringUtils.isNotBlank(keyword)) {
                Map<String, Object> conditionMap = Maps.newHashMap();
                conditionMap.put("message", keyword);
                queryMap.put("match", conditionMap);
            }

            if (startDate != null) {
                Map<String, Object> rangeColumnMap = Maps.newHashMap();
                Map<String, Object> rangeColumnMap2 = Maps.newHashMap();
                rangeColumnMap2.put("gte", startDate);
                if (endDate != null) {
                    rangeColumnMap2.put("lte", endDate);
                }
                // 格式化日期
                rangeColumnMap2.put("format", "yyyy-MM-dd HH:mm:ss");
                rangeColumnMap.put("logdt", rangeColumnMap2);
                queryMap.put("range", rangeColumnMap);
            }
            data.put("query", queryMap);
        }
        // 设置排序
        Map<String, Object> orderInfo = Maps.newHashMap();
        Map<String, Object> orderCondition = Maps.newHashMap();
        orderCondition.put("order", "desc");
        orderInfo.put("logdt", orderCondition);
        data.put("sort", Lists.newArrayList(orderInfo));
        try {
            return requestESAndGetResponse(url, data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("Es查询失败，请检查");
        }
    }

    @Override
    public List<Map<String,Object>> getSysLogInfo() {
        // 0->CPU 1-> memory 2-> filesystem
        try {
            List<String> allIp = getAllHost();
            if(!CollectionUtils.isEmpty(allIp)){
                List<Map<String,Object>> returnInfo = Lists.newArrayList();
                allIp.forEach(item->{
                    Map<String,Object> returnMap = Maps.newHashMap();
                    returnMap.put("cpu", getUseSituation(item,"cpu"));
                    returnMap.put("memory", getUseSituation(item,"memory"));
                    returnMap.put("filesystem", getUseSituation(item,"filesystem"));
                    returnInfo.add(returnMap);
                });
                return returnInfo;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 查询系统内存CP磁盘状况
     * @return SysStatusVo
     * @param pageNo
     * @param pageSize
     */
    @Override
    public PageUtils<SysStatusVo> getSysInfo(Integer pageNo, Integer pageSize) {
        PageHelper.offsetPage(pageNo,pageSize);
        String key = "sys_pageNo_"+pageNo+"_pageSize_"+pageSize;
        if(!redisHelper.hasKey(key)){
            final int THREAD_COUNT = 10;
            ExecutorService pool = Executors.newFixedThreadPool(10);
            CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
            List<SysBaseData> sysBaseData = getSysBaseData();
            List<SysStatusVo> sysStatusVos = new ArrayList<>();
            sysBaseData.forEach(e->{
                pool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SysStatusVo sysStatus = getSysStatus(e.getBaseDataname(),e.getBaseDatacode());
                            sysStatusVos.add(sysStatus);
                            countDownLatch.countDown();//当前线程调用此方法，则计数减一
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            });
            try {
                countDownLatch.await();//阻塞当前线程，直到计数器的值为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pool.shutdown();
            String json = JsonUtils.obj2json(sysStatusVos);
            redisHelper.set(key,json,300);
            PageInfo<SysStatusVo> pageInfo = new PageInfo<>(sysStatusVos);
            return PageUtils.getPageUtils(pageInfo);
        }else {
            String o = redisHelper.get(key).toString();
            List<SysStatusVo> sysStatusVos = JsonUtils.json2List(o, SysStatusVo.class);
            PageInfo<SysStatusVo> pageInfo = new PageInfo<>(sysStatusVos);
            return PageUtils.getPageUtils(pageInfo);
        }


    }
    /**
     * 查询系统log
     * @param pageNo   开始页数
     * @param pageSize  结束页数
     * @param keyword
     * @param startDate
     * @param endDate
     * @return SysLog
     */
    @Override
    public PageUtils<SysLog> getSysLogPage(Integer pageNo, Integer pageSize, String keyword, String startDate, String endDate) {
        PageHelper.offsetPage(pageNo,pageSize);
        Map<String, Object> params = Maps.newHashMap();
        params.put("keyword",keyword);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        List<SysLog> sysLogs = sysLogMapper.getSysLogPage(params);
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogs);
        return PageUtils.getPageUtils(pageInfo);
    }

    public List<SysBaseData> getSysBaseData(){

        Map<String, Object> params = Maps.newHashMap();
        params.put("baseDataTypeCode","system_info");
        List<SysBaseData> baseDataCodeByParams = sysBaseDataDao.getBaseDataCodeByParams(params);
        return baseDataCodeByParams;
    }

    public static SysStatusVo getSysStatus(String baseDataName, String baseDataCode) {
        //从服务器获取内存、CPU、磁盘的使用状况
        String root = exec(baseDataCode, "root", baseDataName, 22, "free\n df -h\n top -b -n 1\n");
        List<String> idsByStr = IdUtil.getIdsByStr(root, "\r\n");
        //磁盘使用信息
        String fileSystem = idsByStr.stream().filter(e -> e.startsWith("/dev/mapper/centos-root")).collect(Collectors.toList()).toString();
        //内存使用信息
        String free = idsByStr.stream().filter(e -> e.startsWith("Mem")).collect(Collectors.toList()).toString();
        //cpu使用信息
        String cpu = idsByStr.stream().filter(e -> e.startsWith("%Cpu(s)")).collect(Collectors.toList()).toString();
        String system = fileSystem.substring(1, fileSystem.length() - 1);
        String fr = free.substring(free.indexOf(":")+1, free.length() - 1);
        String cp = cpu.substring(cpu.indexOf(":")+1, cpu.length() - 1);
        List<String> systems = IdUtil.getIdsByStr(system, " ");
        List<String> frees = IdUtil.getIdsByStr(fr, " ");
        List<String> cpus = IdUtil.getIdsByStr(cp, " ");
        //CPU对象
        CpuVo cpuVo = new CpuVo();
        //磁盘对象
        FileSystemVo fileSystemVo = new FileSystemVo();
        //内存对象
        MemVo memVo = new MemVo();
        if(!CollectionUtils.isEmpty(systems)){
            fileSystemVo.setSystemName(systems.get(0));
            fileSystemVo.setSize(systems.get(1));
            fileSystemVo.setUsed(systems.get(2));
            fileSystemVo.setFree(systems.get(3));
            fileSystemVo.setSizeUsedPoint(systems.get(4));
        }
        if(!CollectionUtils.isEmpty(cpus)){
            cpuVo.setName("CPU");
            cpuVo.setUs(cpus.get(0));
            cpuVo.setSy(cpus.get(1));
            cpuVo.setNi(cpus.get(2));
            cpuVo.setId(cpus.get(3));
            cpuVo.setWa(cpus.get(4));
            cpuVo.setHi(cpus.get(5));
            cpuVo.setSi(cpus.get(6));
            cpuVo.setSt(cpus.get(7));
        }
        if(!CollectionUtils.isEmpty(frees)){
            memVo.setSystemName("内存");
            memVo.setSize(frees.get(0));
            memVo.setUsed(frees.get(1));
            memVo.setFree(frees.get(2));
        }
        List<FileSystemVo> fileSystemVos = new ArrayList<>();
        fileSystemVos.add(fileSystemVo);
        List<CpuVo> cpuVos = new ArrayList<>();
        cpuVos.add(cpuVo);
        List<MemVo> memVos = new ArrayList<>();
        memVos.add(memVo);
        List<String> list = new ArrayList<>();
        list.add("free");
        list.add("df -h");
        list.add("top -b -n 1");
        SysStatusVo sysStatusVo = new SysStatusVo();
        sysStatusVo.setCommands(list);
        sysStatusVo.setFileSystemVos(fileSystemVos);
        sysStatusVo.setCpuVos(cpuVos);
        sysStatusVo.setMemVos(memVos);
        sysStatusVo.setSysIp(baseDataCode);
        return sysStatusVo;
    }


    /**
     * 获取所有的主机
     *
     * @return
     */
    private List<String> getAllHost() throws Exception {
        String date = DateUtil.format(new Date(), "YYYY.MM.dd");

        String url = String.format("/metricbeat-*-%s/doc/_search", date);
        Map<String, Object> queryData = Maps.newHashMap();
        queryData.put("size", 0);
        Map<String, Object> aggsParam = Maps.newHashMap();
        Map<String, Object> groupParam = Maps.newHashMap();
        Map<String, Object> termsParam = Maps.newHashMap();
        termsParam.put("field", "beat.name");
        termsParam.put("size", 100);
        groupParam.put("terms", termsParam);
        aggsParam.put("group_by_ip_peer", groupParam);
        queryData.put("aggs", aggsParam);
        String responseContent = requestESAndGetResponse(url, queryData);
        // 正则匹配   将数据拿出来
        // 匹配的模式
        Pattern pattern = Pattern.compile("\"key\":\".*?\"");
        Matcher matcher = pattern.matcher(responseContent);
        List<String> result = Lists.newArrayList();
        while (matcher.find()) {
            String jsonResult = "{" + matcher.group(0) + "}";
            Map<String, String> resultMap = JsonUtils.json2obj(jsonResult, Map.class);
            result.add(resultMap.get("key"));
        }
        return result;
    }

    /**
     * POST请求获取ES信息并
     *
     * @param url       请求的路径
     * @param queryData 请求参数
     * @return ES响应
     * @throws IOException 异常
     */
    private String requestESAndGetResponse(String url, Map<String, Object> queryData) throws IOException {
        Request request = new Request("POST", url);
        String requestJson = MAPPER.writeValueAsString(queryData);
        log.info("请求URL：{}", url);
        log.info("请求体：{}", requestJson);
        request.setJsonEntity(requestJson);
        Response response = restClient.performRequest(request);
        log.info("响应状态：{}", response.getStatusLine());
        String responseContent = EntityUtils.toString(response.getEntity());
        log.info("响应内容：{}", responseContent);
        return responseContent;
    }

    /**
     * 获取各类型的使用情况
     * @param ip   宿主机IP
     * @param sysType    CPU 或者内存 或者磁盘
     * @return   ES响应内容
     */
    private String getUseSituation(String ip, String sysType){
        if(StringUtils.isBlank(sysType) && StringUtils.isBlank(ip)){
            return "";
        }
        Map<String,Object> queryData = Maps.newHashMap();
        queryData.put("size", 1);

        // 根据CPU 或者 内存或者磁盘查询信息
        Map<String,Object> queryParam = Maps.newHashMap();
        Map<String,Object> matchParam = Maps.newHashMap();
        matchParam.put("metricset.name", sysType);
        queryParam.put("match", matchParam);
        queryData.put("query", queryParam);

        // 排序
        List<Map<String,Object>> lists = Lists.newArrayList();
        Map<String,Object> params = Maps.newHashMap();
        Map<String,Object> orderCondition = Maps.newHashMap();
        orderCondition.put("order", "desc");
        params.put("@timestamp", orderCondition);
        lists.add(params);
        queryData.put("sort", lists);

        try{
            // metricbeat-192.168.21.55-2020.09.22
            String date = DateUtil.format(new Date() , "YYYY.MM.dd");
            String url = String.format("/metricbeat-%s-%s/doc/_search", ip, date);
            return requestESAndGetResponse(url,queryData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    public static String exec(String host, String user, String psw, int port, String command) {
        String result = "";
        Session session = null;
        ChannelExec openChannel = null;
        try {
            // String osName = System.getProperty("os.name");
            // System.out.println("os name:"+osName);
            JSch jsch = new JSch();
            session = jsch.getSession(user, host, port);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(psw);
            session.connect();
            openChannel = (ChannelExec) session.openChannel("exec");
            openChannel.setCommand(command);
            int exitStatus = openChannel.getExitStatus();
            // System.out.println(exitStatus);
            openChannel.connect();
            InputStream in = openChannel.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String buf = null;
            while ((buf = reader.readLine()) != null) {
                result += new String(buf.getBytes("gbk"), "UTF-8") + "  \r\n";
            }
        } catch (JSchException | IOException e) {
            result += e.getMessage();
        } finally {
            if (openChannel != null && !openChannel.isClosed()) {
                openChannel.disconnect();
            }
            if (session != null && session.isConnected()) {
                session.disconnect();
            }
        }
        return result;
    }


}
