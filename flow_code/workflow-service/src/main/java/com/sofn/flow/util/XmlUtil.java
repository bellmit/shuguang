package com.sofn.flow.util;

import com.sofn.flow.exception.SofnException;
import com.sofn.flow.service.impl.ProcessDefinitionServiceImpl;
import com.sofn.flow.vo.ProcessDefinitionAttrVo;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * 解析XML工具类
 *
 * @author heyongjie
 * @date 2020/5/18 16:08
 */
public class XmlUtil {

    /**
     * 获取流程定义设置的属性
     *
     * @param defId 流程定义ID
     * @return List<ProcessDefinitionAttrVo>
     */
    public static List<ProcessDefinitionAttrVo> getDefinitionAttr(String defId) {
        byte[] processDefinitionXml = DbUtil.getProcessDefinitionXml(defId);
        List<ProcessDefinitionAttrVo> processDefinitionAttrVos = new ArrayList<>();
        try {

            SAXReader reader = new SAXReader();
            InputStream inputStream = new ByteArrayInputStream(processDefinitionXml);
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            Element dataFields = rootElement.element("DataFields");
            if (dataFields != null) {
                List<Element> elements = dataFields.elements();
                if (!CollectionUtils.isEmpty(elements)) {
                    for (Element element : elements) {
                        ProcessDefinitionAttrVo processDefinitionAttrVo = new ProcessDefinitionAttrVo();
                        // ID 和名称
                        String fileId = element.attributeValue("Id");
                        String fileName = element.attributeValue("Name");
                        processDefinitionAttrVo.setAttrId(fileId);
                        processDefinitionAttrVo.setAttrName(fileName);
                        // 数据类型
                        Element dataType = element.element("DataType");
                        if (dataType != null) {
                            // 数据的基本类型
                            Element basicType = dataType.element("BasicType");
                            if (basicType != null) {
                                String fileType = basicType.attributeValue("Type");
                                processDefinitionAttrVo.setAttrType(fileType);
                            }
                        }
                        // 字段描述
                        Element descriptionElement = element.element("Description");
                        if (descriptionElement != null) {
                            processDefinitionAttrVo.setDescription(descriptionElement.getTextTrim());
                        }
                        processDefinitionAttrVos.add(processDefinitionAttrVo);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processDefinitionAttrVos;


    }

    /**
     * 读取inforflow.xml 获取数据连接
     *
     * @return Map<String, String>   数据连接对象
     */
    static Map<String, String> getDbInfo() {
        InputStream inputStream = null;
        try {
            Resource resource = new ClassPathResource("inforflow.xml");
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputStream != null) {
            SAXReader reader = new SAXReader();
            Document document;
            try {
                document = reader.read(inputStream);
            } catch (DocumentException e) {
                e.printStackTrace();
                return null;
            }
            Element root = document.getRootElement();
            List<Element> childElements = root.elements();

            Element element = null;

            for (Element childElement : childElements) {
                Iterator eleIt = childElement.elementIterator();
                while (eleIt.hasNext()) {
                    Element e = (Element) eleIt.next();
                    if ("dataBaseProperties".equals(e.getName())) {
                        element = e;
                        break;
                    }
                }
                if (element != null) {
                    break;
                }
            }
            if (element != null) {
                List<Element> elements = element.elements();
                Map<String, String> dbInfo = new HashMap<>(4);
                for (Element e : elements) {
                    dbInfo.put(e.attributeValue("key"), e.getTextTrim());
                }
                return dbInfo;
            }
        }
        return null;

    }

    /**
     * 根据流程定义和流程的activityId获取往后的条件
     *
     * @param defId      流程定义ID
     * @param activityId 流程节点ID  如是开始节点还是申请请假节点的ID
     */
    public static List<String> getCondition(String defId, String activityId) {
        byte[] processDefinitionXml = DbUtil.getProcessDefinitionXml(defId);
        List<String> conditions = new ArrayList<>();
        try {
            SAXReader reader = new SAXReader();
            InputStream inputStream = new ByteArrayInputStream(processDefinitionXml);
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            Element dataFields = rootElement.element("WorkflowProcesses");
            if (dataFields != null) {
                Element workflowProcessElement = dataFields.element("WorkflowProcess");
                if (workflowProcessElement != null) {
                    Element transitionsElement = workflowProcessElement.element("Transitions");
                    if (transitionsElement != null) {
                        List<Element> transitionsElements = transitionsElement.elements();
                        if (!CollectionUtils.isEmpty(transitionsElements)) {
                            for (Element element : transitionsElements) {
                                if (element != null && activityId.equals(element.attribute("From").getValue())) {
                                    Element condition = element.element("Condition");
                                    if (condition != null) {
                                        conditions.add(condition.getTextTrim());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SofnException("获取报表的条件失败！");
        }
        return conditions;
    }

    /**
     * 根据从设计图中获取的条件获取某个参数的期望值
     * 如条件为 $R{leaveDate} &lt;= { 10} or ( $R{isapproved} = { 否 } and $R{leaveDate} &gt; { 10 } )
     * 参数为leaveDate
     * 那么返回值为10
     *
     * @param conditions 在流程定义中跳转到下一级节点期望的条件
     * @param params     希望获取哪个参数的期望值
     * @return 参数对应的值
     */
    public static Set<String> getCondition(List<String> conditions, String params) {
        Set<String> expectValue = new HashSet<>();
        if (!CollectionUtils.isEmpty(conditions)) {
            for (String condition : conditions) {
                // $R{applicantRole} = {flow_test_one} AND  $R{applicantRole} = {flow_test_one2}
                if (!StringUtils.isBlank(condition)) {
                    //   $R{    出现的位置
                    // 如上字符串，经过截取后值为  conditionSplit=["","applicantRole} = {flow_test_one} AND  ","applicantRole} = {flow_test_one}"]
                    String[] conditionSplit = condition.split("\\$R\\{");
                    for (String handlerStr : conditionSplit) {
                        if (!StringUtils.isBlank(handlerStr)) {
                            // 根据=号拆分
                            if (handlerStr.contains("{")) {
                                String[] split = handlerStr.split("\\{");
                                // 如param = applicantRole}
                                String param = split[0];
                                if (param.contains(params)) {
                                    String value = split[1];
                                    // 最终截取{}里面的内容
                                    String realValue = value.substring(0, value.indexOf("}"));
                                    if (!StringUtils.isBlank(realValue)) {
                                        expectValue.add(realValue.trim());
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }
        return expectValue;
    }


    public static void main(String[] args) {
        List<String> conditions = new ArrayList<>();
        conditions.add("$R{applicantRole} = {flow_test_one} AND  $R{applicantRole} = {flow_test_one}");
        conditions.add("$R{leaveDate} &lt;= { 10} or ( $R{isapproved} = { 否 } and $R{leaveDate} &gt; { 10 } )");
        getCondition(conditions, "isapproved");
        getCondition(conditions, "leaveDate");
        getCondition(conditions, "applicantRole");
    }

}
