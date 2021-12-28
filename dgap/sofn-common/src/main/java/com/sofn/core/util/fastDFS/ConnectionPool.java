package com.sofn.core.util.fastDFS;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;


/** fastDFS连接池
 * Created by wuye on 2017/1/5.
 */
public final class ConnectionPool {
    protected Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    public static final String FASTDFS_CONIFG = "system.properties";
    /** 空闲的连接池 */
    private LinkedBlockingQueue<TrackerServer> idleConnectionPool = null;
    /** 连接池默认最小连接数 */
    private long minPoolSize = 1;
    /** 连接池默认最大连接数 */
    private long maxPoolSize = 10;
    /** 当前创建的连接数 */
    private volatile long nowPoolSize = 0;
    /** 默认等待时间（单位：秒） */
    private long waitTimes = 200;
    /** fastdfs客户端创建连接默认1次 */
    private static final int COUNT = 1;

    /**
     * 创建fastDFS连接池，可以指定最大连接数、最小连接数、等待时间
     *
     * @param minPoolSize 连接池最小连接数
     * @param maxPoolSize 连接池最大连接数
     * @param waitTimes 等待时间（单位：秒）
     */
    public ConnectionPool(long minPoolSize, long maxPoolSize, long waitTimes) {
        String logId = UUID.randomUUID().toString();
        logger.info("[线程池构造方法(ConnectionPool)][" + logId
                + "][默认参数：minPoolSize=" + minPoolSize + ",maxPoolSize="
                + maxPoolSize + ",waitTimes=" + waitTimes + "]");
        this.minPoolSize = minPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.waitTimes = waitTimes;
        /** 初始化连接池 */
        poolInit(logId);
        /** 注册心跳 */
        HeartBeat beat = new HeartBeat(this);
        beat.beat();
    }

    /**
     * 创建fastDFS连接池，使用默认设置（连接池默认最小连接数: 1 ; 连接池默认最大连接数: 10 ; 默认等待时间（单位：秒）: 200）
     */
    public ConnectionPool() {
        String logId = UUID.randomUUID().toString();
        logger.info("[线程池构造方法(ConnectionPool)][" + logId
                + "][默认参数：minPoolSize=" + minPoolSize + ",maxPoolSize="
                + maxPoolSize + ",waitTimes=" + waitTimes + "]");
        /** 初始化连接池 */
        poolInit(logId);
        /** 注册心跳 */
        HeartBeat beat = new HeartBeat(this);
        beat.beat();
    }

    /**
     *
     * @Description: 连接池初始化 (在加载当前ConnectionPool时执行) 1).加载配置文件 2).空闲连接池初始化；
     *               3).创建最小连接数的连接，并放入到空闲连接池；
     *
     */
    private void poolInit(String logId) {
        try {
            /** 加载配置文件 */
            initClientGlobal();
            /** 初始化空闲连接池 */
            idleConnectionPool = new LinkedBlockingQueue<>();
            /** 往线程池中添加默认大小的线程 */
            for (int i = 0; i < minPoolSize; i++) {
                createTrackerServer(logId, COUNT);
            }
        } catch (Exception e) {
            logger.error("[FASTDFS初始化(init)--异常][" + logId + "][异常：{}]", e);
        }
    }

    /**
     *
     * @Description: 创建TrackerServer,并放入空闲连接池
     *
     */
    public void createTrackerServer(String logId, int flag) {

        logger.info("[创建TrackerServer(createTrackerServer)][" + logId + "]");
        TrackerServer trackerServer = null;

        try {

            TrackerClient trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            while (trackerServer == null && flag < 5) {
                logger.info("[创建TrackerServer(createTrackerServer)][" + logId
                        + "][第" + flag + "次重建]");
                flag++;
                initClientGlobal();
                trackerServer = trackerClient.getConnection();
            }
            org.csource.fastdfs.ProtoCommon.activeTest(trackerServer
                    .getSocket());
            idleConnectionPool.add(trackerServer);
            /** 同一时间只允许一个线程对nowPoolSize操作 **/
            synchronized (this) {
                nowPoolSize++;
            }

        } catch (Exception e) {

            logger.error("[创建TrackerServer(createTrackerServer)][" + logId
                    + "][异常：{}]", e);

        } finally {

            if (trackerServer != null) {
                try {
                    trackerServer.close();
                } catch (Exception e) {
                    logger.error("[创建TrackerServer(createTrackerServer)--关闭trackerServer异常]["
                            + logId + "][异常：{}]", e);
                }
            }

        }
    }

    /**
     *
     * @Description: 获取空闲连接 1).在空闲池（idleConnectionPool)中弹出一个连接；
     *               2).把该连接放入忙碌池（busyConnectionPool）中; 3).返回 connection
     *               4).如果没有idle connection, 等待 wait_time秒, and check again
     *
     * @throws Exception
     *
     */
    public TrackerServer checkout(String logId) throws Exception {

        logger.info("[获取空闲连接(checkout)][" + logId + "]");
        TrackerServer trackerServer = idleConnectionPool.poll();

        if (trackerServer == null) {
            if (nowPoolSize < maxPoolSize) {
                createTrackerServer(logId, COUNT);
                try {
                    trackerServer = idleConnectionPool.poll(waitTimes,
                            TimeUnit.SECONDS);
                } catch (Exception e) {
                    logger.error("[获取空闲连接(checkout)-error][" + logId
                            + "][error:获取连接超时:{}]",e);
//                    throw E.WAIT_IDLECONNECTION_TIMEOUT.ERROR();
                }
            }
            if (trackerServer == null) {
//                LOGGER.error("[获取空闲连接(checkout)-error][" + logId
//                        + "][error:获取连接超时（" + waitTimes + "s）]");
//                throw ERRORS.WAIT_IDLECONNECTION_TIMEOUT.ERROR();
            }

        }
        logger.info("[获取空闲连接(checkout)][" + logId + "][获取空闲连接成功]");
        return trackerServer;

    }

    /**
     *
     * @Description: 释放繁忙连接 1.如果空闲池的连接小于最小连接值，就把当前连接放入idleConnectionPool；
     *               2.如果空闲池的连接等于或大于最小连接值，就把当前释放连接丢弃；
     *
     * @param
     *
     */

    public void checkin(TrackerServer trackerServer, String logId) throws IOException {

        logger.info("[释放当前连接(checkin)][" + logId + "][prams:" + trackerServer
                + "] ");
        if (trackerServer != null) {
            if (idleConnectionPool.size() < minPoolSize) {
                trackerServer.close();
                idleConnectionPool.add(trackerServer);
            } else {
                synchronized (this) {
                    if (nowPoolSize != 0) {
                        nowPoolSize--;
                    }
                }
            }
        }

    }

    /**
     *
     * @Description: 删除不可用的连接，并把当前连接数减一（调用过程中trackerServer报异常，调用一般在finally中）
     * @param trackerServer
     *
     */
    public void drop(TrackerServer trackerServer, String logId) {
        logger.info("[删除不可用连接方法(drop)][" + logId + "][parms:" + trackerServer
                + "] ");
        if (trackerServer != null) {
            synchronized (this) {
                if (nowPoolSize != 0) {
                    nowPoolSize--;
                }
            }
            try {
                trackerServer.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initClientGlobal() throws Exception {
        //加载配置文件
        Properties props = PropertiesLoaderUtils.loadAllProperties(FASTDFS_CONIFG);
        ClientGlobal.initByProperties(props);
    }

    public LinkedBlockingQueue<TrackerServer> getIdleConnectionPool() {
        return idleConnectionPool;
    }

    public long getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(long minPoolSize) {
        if (minPoolSize != 0) {
            this.minPoolSize = minPoolSize;
        }
    }

    public long getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(long maxPoolSize) {
        if (maxPoolSize != 0) {
            this.maxPoolSize = maxPoolSize;
        }
    }

    public long getWaitTimes() {
        return waitTimes;
    }

    public void setWaitTimes(int waitTimes) {
        if (waitTimes != 0) {
            this.waitTimes = waitTimes;
        }
    }
}
