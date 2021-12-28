package com.sofn.core.oid;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ID生成类，根据传入参数返回唯一码
 * Created by Administrator on 2016/9/7.
 */
public class IdGenerator {
    private static final String DATE_FORMAT_PATTERN = "yyyyMMddHHmmss";
    public IdGenerator(){
        System.out.println("创建对象");
    }
    /**
     * 农业行业代码
     * 1、IOS
     * 2、国家成员
     * 156、中国
     * 326、农业行业
     * 8、国家平台
     */
    private static final String INDUSTRY_CODE = "1.2.156.326.8";

    /**
     * 业务类型
     */
    public enum ServiceType {
        /**
         * 追溯业务
         */
        Retrospect(1);
        private int value = 0;

        private ServiceType(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 编码属性
     */
    public enum EncodingProperties {
        /**
         * 主体身份码
         */
        MainBodyIdentityCode(0),
        /**
         * 主体用户码
         */
        MainBodyUserCode(1),
        /**
         * 产品批次码
         */
        ProductBatchCode(2),
        /**
         * 产品追溯码
         */
        ProductTraceabilityCode(3),
        /**
         * 主体临时身份码
         */
        TemporaryIdentityCode(4),
        /**
         * 产品临时码
         */
        ProductTemporaryCode(5),
        /**
         * 入市追溯凭证
         */
        IntoCityTraceability(6);
        public int value = 0;

        private EncodingProperties(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 主体类别
     */
    public enum MainBodyCategories {
        /**
         * 生产主体
         */
        MainWorkingBody(0),
        /**
         * 经营主体
         */
        MainManagementBody(1),
        /**
         * 生产经营主体
         */
        MainBodyOfWokingAndManagement(2),
        /**
         * 农资主体
         */
        AgriculturalMaterialsSubject(3);
        public int value = 0;

        private MainBodyCategories(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 组织形式
     */
    public enum OrganizationForms {
        //[0]企业   [1]个人
        /**
         * 企业
         */
        Enterprise(0),
        /**
         * 个人
         */
        Individual(1),
        /**
         * 家庭农场
         */
        Familyfarm(2),
        /**
         * 合作社
         */
        Cooperative(3)
        ;
        public int value = 0;

        private OrganizationForms(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 追溯码类型
     */
    public enum TracingCodeType {
        /**
         * 生产批次码
         */
        production(1),
        /**
         * 流通批次码
         */
        circulate(2),
        /**
         * 入市追溯凭证
         */
        enterMarket(3);
        public int value = 1;

        private TracingCodeType(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }


    /**
     * 左补位函数
     *
     * @param old    需要补位的字符串
     * @param length 补位后字符串长度
     * @param c      补位填充的字符
     * @return 补位后的字符串
     */
    private String leftFillSeats(String old, int length, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - old.length(); i++)
            sb.append(c);
        sb.append(old);
        return sb.toString();
    }

    /**
     * 获取主体身份码
     *
     * @param serviceType        业务类型(追溯业务)
     * @param mainBodyCategories 主体类别
     * @param organizationForms  组织形式
     * @param mainBodyNumber     主体号
     * @return 主体身份码
     * @Deprecated
     */
    public String getMainBodyIdentityCode(ServiceType serviceType, MainBodyCategories mainBodyCategories, OrganizationForms
            organizationForms, String mainBodyNumber) {
        //初始化并追加农业行业代码+国家平台+业务平台
        StringBuilder sb = new StringBuilder(this.INDUSTRY_CODE);
        //追加业务类型
        sb.append('.');
        sb.append(serviceType.value);
        //追加编码属性
        sb.append('.');
        sb.append(EncodingProperties.MainBodyIdentityCode.value);
        //追加主体类别
        sb.append('.');
        sb.append(mainBodyCategories.value);
        //追加组织形式
        sb.append('.');
        sb.append(organizationForms.value);
        //补位主体号
        mainBodyNumber = leftFillSeats(mainBodyNumber, 18, '0');
        //追加主体号
        sb.append('.');
        sb.append(mainBodyNumber);
        return sb.toString();
    }

    /**
     * 获取主体用户码
     *
     * @param serviceType        业务类型(追溯业务)
     * @param mainBodyCategories 主体类别
     * @param organizationForms  组织形式
     * @param mainBodyNumber     主体号
     * @param userNumber         用户号
     * @return 主体用户码
     * @Deprecated
     */
    public String getMainBodyUserCode(ServiceType serviceType, MainBodyCategories mainBodyCategories, OrganizationForms
            organizationForms, String mainBodyNumber, String userNumber) {
        //初始化并追加农业行业代码+国家平台+业务平台
        StringBuilder sb = new StringBuilder(this.INDUSTRY_CODE);
        //追加业务类型
        sb.append('.');
        sb.append(serviceType.value);
        //追加编码属性
        sb.append('.');
        sb.append(EncodingProperties.MainBodyUserCode.value);
        //追加主体类别
        sb.append('.');
        sb.append(mainBodyCategories.value);
        //追加组织形式
        sb.append('.');
        sb.append(organizationForms.value);
        //补位主体号
        mainBodyNumber = leftFillSeats(mainBodyNumber, 18, '0');
        //追加主体号
        sb.append('.');
        sb.append(mainBodyNumber);
        //追加用户号
        sb.append('.');
        sb.append(this.leftFillSeats(userNumber,3,'0'));
        return sb.toString();
    }

    /**
     * 获取产品批次码
     *
     * @param serviceType         业务类型(追溯业务)
     * @param mainBodyCategories  主体类别
     * @param organizationForms   组织形式
     * @param mainBodyNumber      主体号
     * @param projectCategoryCode 农产品分类码
     * @return 产品批次码
     * @Deprecated
     */
    public String getProductBatchCode(ServiceType serviceType, MainBodyCategories mainBodyCategories, OrganizationForms
            organizationForms, String mainBodyNumber, String projectCategoryCode) {
        //初始化并追加农业行业代码+国家平台+业务平台
        StringBuilder sb = new StringBuilder(this.INDUSTRY_CODE);
        //追加业务类型
        sb.append('.');
        sb.append(serviceType.value);
        //追加编码属性
        sb.append('.');
        sb.append(EncodingProperties.ProductBatchCode.value);
        //追加主体类别
        sb.append('.');
        sb.append(mainBodyCategories.value);
        //追加组织形式
        sb.append('.');
        sb.append(organizationForms.value);
        //补位主体号
        mainBodyNumber = leftFillSeats(mainBodyNumber, 18, '0');
        //追加主体号
        sb.append('.');
        sb.append(mainBodyNumber);
        //追加农产品分类码
        sb.append('.');
        sb.append(projectCategoryCode);
        //追加时间戳
        sb.append('.');
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }

    /**
     * 获取产品追溯码
     *
     * @param serviceType         业务类型(追溯业务)
     * @param mainBodyCategories  主体类别
     * @param organizationForms   组织形式
     * @param downstreamCode      下游主体身份码
     * @param projectCategoryCode 农产品分类码
     * @return 产品追溯码
     * @Deprecated
     */
    public String getProductTraceabilityCode(ServiceType serviceType, MainBodyCategories mainBodyCategories, OrganizationForms
            organizationForms, String downstreamCode, String projectCategoryCode) {
        //初始化并追加农业行业代码+国家平台+业务平台
        StringBuilder sb = new StringBuilder(this.INDUSTRY_CODE);
        //追加业务类型
        sb.append('.');
        sb.append(serviceType.value);
        //追加编码属性
        sb.append('.');
        sb.append(EncodingProperties.ProductTraceabilityCode.value);
        //追加主体类别
        sb.append('.');
        sb.append(mainBodyCategories.value);
        //追加组织形式
        sb.append('.');
        sb.append(organizationForms.value);
        //补位下游身份识别码
        downstreamCode = leftFillSeats(downstreamCode, 18, '0');
        //追加下游身份识别码
        sb.append('.');
        sb.append(downstreamCode);
        //追加农产品分类码
        sb.append('.');
        sb.append(projectCategoryCode);
        //追加时间戳
        sb.append('.');
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }

    /**
     * 获取入市追溯凭证
     *
     * @param serviceType         业务类型(追溯业务)
     * @param mainBodyCategories  主体类别
     * @param organizationForms   组织形式
     * @param downstreamCode      下游主体身份码
     * @param projectCategoryCode 农产品分类码
     * @return 入市追溯凭证
     * @Deprecated
     */
    public String getIntoCityTraceability(ServiceType serviceType, MainBodyCategories mainBodyCategories, OrganizationForms
            organizationForms, String downstreamCode, String projectCategoryCode) {
        //初始化并追加农业行业代码+国家平台+业务平台
        StringBuilder sb = new StringBuilder(this.INDUSTRY_CODE);
        //追加业务类型
        sb.append('.');
        sb.append(serviceType.value);
        //追加编码属性
        sb.append('.');
        sb.append(EncodingProperties.IntoCityTraceability.value);
        //追加主体类别
        sb.append('.');
        sb.append(mainBodyCategories.value);
        //追加组织形式
        sb.append('.');
        sb.append(organizationForms.value);
        //补位下游身份识别码
        downstreamCode = leftFillSeats(downstreamCode, 18, '0');
        //追加下游身份识别码
        sb.append('.');
        sb.append(downstreamCode);
        //追加农产品分类码
        sb.append('.');
        sb.append(projectCategoryCode);
        //追加时间戳
        sb.append('.');
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }

    /**
     * 获取临时主体身份码
     *
     * @param serviceType        业务类型(追溯业务)
     * @param mainBodyCategories 主体类别
     * @param organizationForms  组织形式
     * @param mainBodyNumber     主体号
     * @return 临时主体身份码
     */
    public String getTemporaryIdentityCode(ServiceType serviceType, MainBodyCategories mainBodyCategories, OrganizationForms
            organizationForms, String mainBodyNumber) {
        //初始化并追加农业行业代码+国家平台+业务平台
        StringBuilder sb = new StringBuilder(this.INDUSTRY_CODE);
        //追加业务类型
        sb.append('.');
        sb.append(serviceType.value);
        //追加编码属性
        sb.append('.');
        sb.append(EncodingProperties.TemporaryIdentityCode.value);
        //追加主体类别
        sb.append('.');
        sb.append(mainBodyCategories.value);
        //追加组织形式
        sb.append('.');
        sb.append(organizationForms.value);
        //补位主体号
        mainBodyNumber = leftFillSeats(mainBodyNumber, 18, '0');
        //追加主体号
        sb.append('.');
        sb.append(mainBodyNumber);
        return sb.toString();
    }

    /**
     * 获取产品临时码
     *
     * @param serviceType         业务类型(追溯业务)
     * @param mainBodyCategories  主体类别
     * @param organizationForms   组织形式
     * @param mainBodyNumber      主体号
     * @param projectCategoryCode 农产品分类码
     * @return 产品临时码
     */
    public String getProductTemporaryCode(ServiceType serviceType, MainBodyCategories mainBodyCategories, OrganizationForms
            organizationForms, String mainBodyNumber, String projectCategoryCode) {
        //初始化并追加农业行业代码+国家平台+业务平台
        StringBuilder sb = new StringBuilder(this.INDUSTRY_CODE);
        //追加业务类型
        sb.append('.');
        sb.append(serviceType.value);
        //追加编码属性
        sb.append('.');
        sb.append(EncodingProperties.ProductTemporaryCode.value);
        //追加主体类别
        sb.append('.');
        sb.append(mainBodyCategories.value);
        //追加组织形式
        sb.append('.');
        sb.append(organizationForms.value);
        //补位主体号
        mainBodyNumber = leftFillSeats(mainBodyNumber, 18, '0');
        //追加主体号
        sb.append('.');
        sb.append(mainBodyNumber);
        //追加农产品分类码
        sb.append('.');
        sb.append(projectCategoryCode);
        //追加时间戳 产品临时登记时间码
        sb.append('.');
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }


    /**
     * 根据编码返回编码类型
     * @param code 编码
     * @return
     */
    public static EncodingProperties getEncodingPropertie(String code){
        try {
            int v = Integer.valueOf(code.substring(16, 17));
            for(EncodingProperties s:EncodingProperties.values()){
                if(s.value()==v)
                    return s;
            }
            return null;
        }catch (Exception ex){
            return null;
        }
    }

    /**
     * 根据追溯码返回追溯码类型
     * @param code 编码
     * @return 追溯码类型
     */
    public static TracingCodeType getTracingCodeType(String code) {
        int codeType;

        try {
            // 判断是否是老编码规则的追溯码
            if (code.startsWith(INDUSTRY_CODE)) {
                codeType = Integer.valueOf(code.substring(14, 15));
            } else {
                codeType = Integer.valueOf(code.substring(0, 1));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        for (TracingCodeType s : TracingCodeType.values()) {
            if (s.value() == codeType)
                return s;
        }
        return null;
    }
}
