package com.sofn.model.dgap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author tianjie
 * 解析进入数据的xml字符串
 * 字符串格式必须满足
 * <records>
 <resource>
 11111
 </resource>
 <record>
 <field1></field1>
 <field2></field2>
 </record>
 <record>
 <field1></field1>
 <field2></field2>
 </record>
 </records>
 *
 * @date 2016/11/22
 */
public class InDataXmlHelper {

    public static List<Map<String,String>> getFv(String xml){
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        SAXReader reader = new SAXReader();
        // 定义一个文档
        Document document = null;
        //将字符串转换为document
        try {
            document = reader.read(new ByteArrayInputStream(xml
                    .getBytes("UTF-8")));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Element root = document.getRootElement();
        List<Element> recordList = root.elements("record");
        for(Element e : recordList){
          Map<String,String> map = new HashMap<String,String>();
            for(Element fe:(List<Element>)e.elements()){
                map.put(fe.getName(),fe.getStringValue());
          }
          list.add(map);
        }
        return list;

    }

    public static void main(String[] args) {
        String xml ="<records>\n" +
                "<resource>\n" +
                "11111\n" +
                "</resource>\n" +
                "<record>\n" +
                "<field1>11</field1>\n" +
                "<field2>22</field2>\n" +
                "</record>\n" +
                "<record>\n" +
                "<field1>111</field1>\n" +
                "<field2>智商</field2>\n" +
                "</record>\n" +
                "</records>";
        System.out.println(getFv(xml));
    }
}
