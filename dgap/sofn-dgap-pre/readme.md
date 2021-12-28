1.所有的webservice的接口必须继承hearService。heartService提供心跳方法的接口。
2.所有的webservice实现方法必须继承BaseWsService.heartService提供心跳方法的实现。
3.查询接口的xml格式：
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.sofn.com/">
   <soapenv:Header>
   <auth:authentication xmlns:auth="http://gd.chinamobile.com//authentication">
<auth_password>k5Xf9B20M9</auth_password>
</auth:authentication>
   </soapenv:Header>
   <soapenv:Body>
      <ws:getMessage>
         <!--Optional:-->
         <arg0>
         <![CDATA[
         <SoapXml>
         <resource>1</resource>
         <returnFields>1,2</returnFields>
         <condition>1</condition>
         <record_num>1</record_num>
         </SoapXml>
         ]]>
         </arg0>
      </ws:getMessage>
   </soapenv:Body>
</soapenv:Envelope>