package com.sofn.sys.util;

import com.fr.cert.token.JwtBuilder;
import com.fr.cert.token.Jwts;
import com.fr.cert.token.SignatureAlgorithm;
import com.google.common.collect.Maps;
import com.sofn.common.exception.SofnException;
import com.sofn.common.utils.RedisHelper;
import com.sofn.common.utils.SpringContextHolder;
import com.sofn.sys.config.FineReportConfig;
import com.sofn.sys.enums.SysManageEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

/**
 * 帆软报表工具类
 *
 * @author heyongjie
 * @date 2020/5/26 14:36
 */
@Slf4j
public class FineReportUtil {

    private static RedisHelper redisHelper = SpringContextHolder.getBean(RedisHelper.class);

    private static FineReportConfig fineReportConfig = SpringContextHolder.getBean(FineReportConfig.class);


    /**
     * 帆软报表TOKEN超时时间  单位毫秒
     */
    private static final long VALID_TIME = 30 * 60 * 1000;

    /**
     * 帆软报表缓存KEY
     */
    private static final String FINE_REPORT_CACHE_KEY = "FINE_REPORT_CACHE_KEY";

    /**
     * MAP KEY 后缀
     */
    private static final String MAP_KEY_SUF = "_TIME_";

    /**
     * 普通报表
     */
    private static final String REPORT_TYPE_ORDINARA = ".cpt";

    /**
     * 决策报表
     */
    private static final String REPORT_TYPE_POLICY_DECISION = ".frm";

    /**
     * 请求成功响应码
     */
    private static final int RESULT_SUCCESS_CODE = 200;

    /**
     * 访问决策系统包含哪个字符串就报错
     */
    private static final String RESULT_ERR_MESSAGE = "出错页面";

    /**
     * 获取报表路径
     *
     * @param reportName 报表名称
     * @param type       报表类型
     * @return 报表真实路径
     */
    public static String getReportUrl(String reportName, String type) {
        if (StringUtils.isBlank(reportName) || StringUtils.isBlank(type)) {
            throw new SofnException("报表名称和类型必传");
        }
        String reportUrl = fineReportConfig.getReportUrl();
//        String reportUrl = InitWorkFlowUrl.getWorkFlowUrl("reportUrl");
        if (StringUtils.isBlank(reportUrl)) {
            throw new SofnException("请配置帆软报表访问路径");
        }
        String suf = SysManageEnum.SYS_REPORT_TYPE_ORDINARA.getCode().equals(type) ? REPORT_TYPE_ORDINARA : REPORT_TYPE_POLICY_DECISION;
        // 根据类型添加报表名称后缀
        reportName = reportName + suf;
        String token = getToken(reportName);
        return String.format(reportUrl + "%s&fine_digital_signature=" + token, reportName);
    }


    /**
     * 检查报表是否能够正确访问，如果不能访问则抛出异常
     *
     * @param reportName 报表名称
     * @param type       报表类型
     */
    public static void checkReportIsExists(String reportName, String type) {
        try {
            String url = getReportUrl(reportName, type);
            String encode = URLEncoder.encode(reportName, "UTF-8");
            url = url.replaceAll(reportName, encode);
            URLConnection urlConnection = new URL(url).openConnection();
            HttpURLConnection connection = (HttpURLConnection) urlConnection;
            connection.setRequestMethod("GET");
            //连接
            connection.connect();
            //得到响应码
            int responseCode = connection.getResponseCode();
            if (responseCode != RESULT_SUCCESS_CODE) {
                throw new SofnException("不能正确的访问帆软的决策系统！请检查！");
            } else {
                String s = readResponseContent(connection.getInputStream());
                if (s.contains(RESULT_ERR_MESSAGE) || s.contains("Oops")) {
                    throw new SofnException("该报表不能正确访问，请检查报表名称和类别是否正确！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof SofnException) {
                throw new SofnException(e.getMessage());
            }
            throw new SofnException("请确保帆软的决策系统能够访问，并且报表已经开启了数字认证方式！");
        }
    }

    /**
     * 读取响应字节流并将之转为字符串
     *
     * @param in 要读取的字节流
     * @return 返回的内容
     * @throws IOException 内容读取异常
     */
    private static String readResponseContent(InputStream in) throws IOException {
        StringBuilder content = new StringBuilder();
        try (Reader reader = new InputStreamReader(in)) {
            char[] buffer = new char[1024];
            int head;
            while ((head = reader.read(buffer)) > 0) {
                content.append(new String(buffer, 0, head));
            }
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("读取响应失败");
        } finally {
            if (null != in) {
                in.close();
            }
        }
    }

    /**
     * 将TOKEN缓存起来  避免每次都重新生成
     *
     * @param token      帆软报表生成的TOKEN
     * @param reportName 设计的报表名称
     */
    private static void cacheToken(String reportName, String token) {
        if (!StringUtils.isBlank(reportName) && !StringUtils.isBlank(token)) {
            if (redisHelper != null) {
                Map<Object, Object> result = redisHelper.hmget(FINE_REPORT_CACHE_KEY);
                if (CollectionUtils.isEmpty(result)) {
                    result = Maps.newHashMap();
                }
                Date currentTime = new Date();
                //  这里减去5秒是避免Fine先失效了
                long validTime = new Date(currentTime.getTime() + VALID_TIME - 5).getTime();
                // Map的值为  失效的时间+_+token
                String realToken = validTime + MAP_KEY_SUF + token;
                result.put(reportName, realToken);
                Map<String, Object> cacheMap = Maps.newHashMap();
                result.forEach((k, v) -> cacheMap.put(k.toString(), v));
                redisHelper.hmset(FINE_REPORT_CACHE_KEY, cacheMap);
            }
        }
    }

    /**
     * 获取报表缓存
     *
     * @param reportName 报表名称，需要加上后缀
     */
    private static String getToken(String reportName) {
        if (!StringUtils.isBlank(reportName)) {
            if (redisHelper != null) {
                Object hget = redisHelper.hget(FINE_REPORT_CACHE_KEY, reportName);
                // 缓存中没有值就重新生成
                if (hget == null || StringUtils.isBlank(hget.toString())) {
                    String token = createJwt(reportName);
                    if (!StringUtils.isBlank(token)) {
                        cacheToken(reportName, token);
                    }
                    return token;
                } else {
                    // 如果失效了就重新生成
                    String tokenAndTime = hget.toString();
                    long time = Long.parseLong(tokenAndTime.split(MAP_KEY_SUF)[0]);
                    long currTime = System.currentTimeMillis();
                    // 如果第一次缓存时间加上超时时间 在减去当前时间的毫秒数 则代表该数据已经过期
                    if (time - currTime < 0) {
                        String token = createJwt(reportName);
                        if (!StringUtils.isBlank(token)) {
                            cacheToken(reportName, token);
                        }
                        return token;
                    } else {
                        return tokenAndTime.split(MAP_KEY_SUF)[1];
                    }
                }
            }
        }
        throw new SofnException("请传入报表名称");
    }


    /**
     * 创建帆软认证TOKEN
     *
     * @param subject 报表文件名称 如XXX.rpt
     * @return 生成的TOKEN
     */
    private static String createJwt(String subject) {
        return createJwt("", "", subject, VALID_TIME, fineReportConfig.getPassword());
    }

    /**
     * 创建帆软认证TOKEN
     *
     * @param issuer    不知道用途 传入null
     * @param id        不知道用途 传入null
     * @param subject   报表文件名称 如XXX.rpt
     * @param validTime 经过多少时间后过期
     * @param key       在决策系统中配置的数字签名秘钥
     * @return 生成的TOKEN
     */
    public static String createJwt(String issuer, String id, String subject, long validTime, String key) {
        log.info("开始生成报表：{}的Token",subject);
        if (issuer == null) {
            issuer = "";
        }
        if (id == null) {
            id = "";
        }

        //用于生成数字签名，即参数fine_digital_signature的值
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date currentTime = new Date();
        Date expirationTime = new Date(currentTime.getTime() + validTime);
        JwtBuilder builder = Jwts.builder()
                .setIssuer(issuer)
                .setSubject(subject)
                .setIssuedAt(currentTime)
                .setExpiration(expirationTime)
                .setId(id)
                .signWith(signatureAlgorithm, key);
        String token = builder.compact();
        log.info("报表：{}的Token为：{}",subject,token);
        return token;
    }

}
