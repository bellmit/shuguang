package com.sofn.core.util;

import java.util.HashMap;

/**
 * describe:用户通过url访问服务工具类
 *
 * @author xuquan
 * @date 2018/11/08
 */
public class VisitService {

    private String context;                     //服务上下文
    private String url;                         //服务url
    private long interval;                      //访问服务间隔时间，单位秒（ms）,默认为5s
    private long startTime;                     //访问限制生效日期
    private String userId;                      //访问用户id
    private String PC_KEY;                      //pc端口key
    private HashMap<String, Long> info;          //存储的对象信息
    private HashMap<String, HashMap<String, Long>> urlInfoMap;//url信息map

    private static final String templateMessage = "请不要频繁的访问该服务，大约%s秒后重新访问。";
    private static final String KEY_START_TIME = "START_TIME";
    private static final String KEY_INTERVAL = "INTERVAL";


    public VisitService(String context, String url, String userId) {
        this(context, url, 5000, userId);
    }

    public VisitService(String context, String url, long interval, String userId) {
        this.context = context;
        this.url = url;
        this.interval = interval;
        this.userId = userId;

        info = new HashMap<>();
        urlInfoMap = new HashMap<>();

    }


    /**
     * 获取当前用户是否可以访问该服务
     * 通过redis存储对应系统接口的访问时间及间隔
     * 1、存储格式
     * key:PC_CONTEXT_USERID
     * value:该用户context所有的url访问信息（hashmap）
     * 2、判断依据 当前访问时间 - 生效的第一次限制时间 < interval 则被限制
     *
     * @return
     */
    public boolean isLimited() {
        boolean isLimited;
        PC_KEY = "PC_" + context + "_" + userId;
        urlInfoMap = (HashMap<String, HashMap<String, Long>>) RedisUtil.get(PC_KEY);
        if (urlInfoMap != null) {
            info = urlInfoMap.get(url);
            if (info == null) {
                updateUrlInfoMap(System.currentTimeMillis());
                isLimited = false;
            } else {
                startTime = info.get(KEY_START_TIME);
                //interval = info.get(KEY_INTERVAL);

                long currentTime = System.currentTimeMillis();
                if ((currentTime - startTime) < interval) {
                    isLimited = true;
                } else {
                    updateUrlInfoMap(currentTime);
                    isLimited = false;
                }
            }
        } else {
            updateUrlInfoMap(System.currentTimeMillis());

            isLimited = false;
        }

        return isLimited;
    }

    /**
     * 访问被限制的提示信息
     *
     * @return
     */
    public String getMessage() {
        return String.format(templateMessage, String.valueOf(interval/1000));
    }

    private void updateUrlInfoMap(long startTime) {
        if (info == null)
            info = new HashMap<>();
        info.put(KEY_START_TIME, startTime);
        info.put(KEY_INTERVAL, interval);

        if (urlInfoMap == null)
            urlInfoMap = new HashMap<>();
        urlInfoMap.put(url, info);

        RedisUtil.set(PC_KEY, urlInfoMap);
    }

}
