package com.sofn.ducss.enums;

public class Constants {
    /**
     *  APPID
     */
    public static final String APPID = "ducss";

    /**
     *  新疆建设兵团区划id
     */
    public static final String XINJIANG_CONSTRUCTION_CORPS = "660000";

    /**
     *  新疆自治区区划id
     */
    public static final String XINJIANG= "650000";

    /**
     * 部级，area_id
     */
    public static final String ZHONGGUO_AREA_ID= "100000";

    /**
     * 部级，area_name
     */
    public static final String ZHONGGUO_AREA_NAME= "中国";

    /**
     * 部级，area_name
     */
    public static final String QUANGUO_AREA_NAME= "全国";

    /**
     * 激活时候增加状态识别码，'Y'-报告,'N'-普通文件
     */
    public static final String REPORT_FILE = "Y";

    /**
     * 审核相关的状态
     */
    public interface ExamineState {
        /**
         * 保存未上报
         */
        public static final Byte SAVE = 0;
        /**
         * 已上报
         */
        public static final Byte REPORTED = 1;
        /**
         * 已读
         */
        public static final Byte READ = 2;
        /**
         * 已退回
         */
        public static final Byte RETURNED = 3;
        /**
         * 已撤回
         */
        public static final Byte WITHDRAWN = 4;
        /**
         * 已通过
         */
        public static final Byte PASSED = 5;
        /**
         * 已下发
         */
        public static final Byte ISSUE = 6;
        /**
         * 已公布
         */
        public static final Byte PUBLISH = 7;
    }

    /**
     * 其他
     */
    public interface Other {
        /**
         * 超级用户区域
         */
        Integer SUPER_USER_AREA=9999999;

        /**
         * 初始密码
         */
        String BASIC_PWD = "Abcd1234";
    }

    /**
     * 数据字典类型
     */
    public interface DictionaryType {

        /**
         * 秸秆类型
         */
        public static String STRAW_TYPE = "straw_type";

        /**
         * 区划类型
         */
        public static String AREA_TYPE = "area_type";

        /**
         * 公用类型
         */
        public static String COMMON_TYPE = "common_type";
    }

    public interface Application {
        /**
         * 肥料化
         */
        public static String fertilising = "0";
        /**
         * 饲料化
         */
        public static String forage = "1";
        /**
         * 燃料化
         */
        public static String fuel = "2";
        /**
         * 基料化
         */
        public static String base = "3";
        /**
         * 原料化
         */
        public static String material = "4";
        /**
         * 其他
         */
        public static String other = "5";
    }

    /**
     * 获取序号类型
     */
    public interface SequenceType {

        /**
         * 农户
         */
        public static String FRMER_TYPE = "farmer";

        /**
         * 主体
         */
        public static String MAIN_TYPE = "main";

    }

    /**
     * 字典类型common_type，具体数值
     */
    public interface CommonDictType{
        /**
         * 默认最小预计填报数
         */
        String MIN_EXPECTNUM = "min_expectnum";
        /**
         * 黑龙江农垦和新疆兵团，特殊备注
         */
        String HS_SPECIAL = "hs_special";
        /**
         * des加密key
         */
        String DES_ENCRYPT_KEY = "des_encrypt_key";
        /**
         * 加密标志，0未加密，1已加密
         */
        String ENCRYPTED_FLAG = "encrypted_flag";
        /**
         * 连续登录失败后，ip+coockie锁定时间(秒)
         */
        String LOGIN_ERROR_LOCK_SECONDS = "login_error_lock_seconds";

    }

    /**
     * ehcache名称
     */
    public interface EhCacheNames{
        /**
         * 字典信息缓存
         */
        String DICT_CACHE="dict_cache";
        /**
         * 登录错误IP缓存
         */
        String LOGIN_IP_CACHE="login_IP_cache";
    }

    /**
     * 登录错误记录，配置信息
     */
    public interface LoginErrConfig{
        /**
         *最大错误次数
         */
        int MAX_ERR_COUNT = 5;
        /**
         * 锁定时间(分)
         */
        int LOCK_MINUTES = 1;
        /**
         * 计时周期(分)
         */
        int ERR_PERIOD_MINUTES = 1;
    }


    // 直辖市ID
    private static Integer[] zxsArea = {110000, 120000, 310000, 500000};
    // 直辖市市ID
    private static Integer[] zxsChildArea = {110100, 120100, 310100, 500100};

    public static Integer getZxsAreaId(Integer areaId) {
        for (int i = 0; i < zxsArea.length; i++) {
            if (zxsArea[i].equals(areaId)) {
                areaId = zxsChildArea[i];
                break;
            }
        }
        return areaId;
    }
}
