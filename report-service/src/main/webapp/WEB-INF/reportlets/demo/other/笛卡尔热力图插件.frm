<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="File1" class="com.fr.data.impl.ExcelTableData">
<ExcelTableDataAttr needColumnName="true" isFromEnv="true" filePath="reportlets/笛卡尔热力图数据集.xls"/>
<Parameters/>
</TableData>
</TableDataMap>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
</FormMobileAttr>
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<WidgetName name="form"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<ShowBookmarks showBookmarks="true"/>
<Center class="com.fr.form.ui.container.WFitLayout">
<WidgetName name="body"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WAbsoluteBodyLayout">
<WidgetName name="body"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart0" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="me.hugo.fanruan.plugin.eheatmap.PluginChart">
<attr refreshEnabled="false" refreshTime="10.0" theme="{&quot;xAxis&quot;:{&quot;height&quot;:50,&quot;textStyle&quot;:{&quot;fontSize&quot;:12,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;fontWeight&quot;:&quot;&quot;,&quot;fontStyle&quot;:&quot;&quot;},&quot;lineColor&quot;:&quot;#00FFFF&quot;,&quot;lineType&quot;:1},&quot;yAxis&quot;:{&quot;width&quot;:50,&quot;textStyle&quot;:{&quot;fontSize&quot;:12,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;fontWeight&quot;:&quot;&quot;,&quot;fontStyle&quot;:&quot;&quot;},&quot;lineColor&quot;:&quot;#33CCCC&quot;,&quot;lineType&quot;:1},&quot;vistualMap&quot;:{&quot;min&quot;:&quot;0&quot;,&quot;max&quot;:&quot;15&quot;,&quot;minColor&quot;:&quot;#FFCC99&quot;,&quot;maxColor&quot;:&quot;#FF0000&quot;,&quot;sectionCount&quot;:2,&quot;width&quot;:50,&quot;textStyle&quot;:{&quot;fontSize&quot;:13,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;fontWeight&quot;:&quot;bolder&quot;,&quot;fontStyle&quot;:&quot;&quot;}},&quot;axisCustomLabel&quot;:&quot;function(label,axis){\n    if(0==axis){\n        return label+&apos;M&apos;;\n    }else if(1 == axis){\n        return label+&apos;D&apos;;\n    }\n    return label;\n}&quot;,&quot;bg&quot;:{},&quot;tips&quot;:{&quot;parts&quot;:[&quot;今天的温度是&quot;,&quot;摄氏度&quot;],&quot;textStyle&quot;:{&quot;fontSize&quot;:12,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;宋体&quot;,&quot;fontWeight&quot;:&quot;&quot;,&quot;fontStyle&quot;:&quot;&quot;},&quot;lineType&quot;:1,&quot;lineColor&quot;:&quot;&quot;,&quot;fillColor&quot;:&quot;#FFFFFF&quot;,&quot;fillOpacity&quot;:97},&quot;tools&quot;:{&quot;fullDisplay&quot;:true,&quot;collapse&quot;:false},&quot;effect&quot;:{&quot;hyperLinkFunc&quot;:&quot;function(xAxis, yAxis, val) { return `www.so.com/s?q=${xAxis}+${yAxis}+${val}`;}&quot;,&quot;refreshTime&quot;:0},&quot;padding&quot;:{&quot;left&quot;:5,&quot;right&quot;:10,&quot;top&quot;:55,&quot;bottom&quot;:5}}"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<AbstractDataConfig class="me.hugo.fanruan.plugin.eheatmap.PluginChartDataConfig" pluginID="me.hugo.fanruan.plugin.eheatmap">
<attr xAxis="X" yAxis="Y" value="值" customName="false"/>
</AbstractDataConfig>
</DataSet>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="me.hugo.fanruan.plugin.eheatmap.PluginChart">
<attr refreshEnabled="false" refreshTime="10.0" theme="{&quot;xAxis&quot;:{&quot;height&quot;:50,&quot;textStyle&quot;:{&quot;fontSize&quot;:12,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;fontWeight&quot;:&quot;&quot;,&quot;fontStyle&quot;:&quot;&quot;},&quot;lineColor&quot;:&quot;#00FFFF&quot;,&quot;lineType&quot;:1},&quot;yAxis&quot;:{&quot;width&quot;:50,&quot;textStyle&quot;:{&quot;fontSize&quot;:12,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;fontWeight&quot;:&quot;&quot;,&quot;fontStyle&quot;:&quot;&quot;},&quot;lineColor&quot;:&quot;#33CCCC&quot;,&quot;lineType&quot;:1},&quot;vistualMap&quot;:{&quot;min&quot;:&quot;0&quot;,&quot;max&quot;:&quot;15&quot;,&quot;minColor&quot;:&quot;#FFCC99&quot;,&quot;maxColor&quot;:&quot;#FF0000&quot;,&quot;sectionCount&quot;:2,&quot;width&quot;:50,&quot;textStyle&quot;:{&quot;fontSize&quot;:13,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;fontWeight&quot;:&quot;bolder&quot;,&quot;fontStyle&quot;:&quot;&quot;}},&quot;axisCustomLabel&quot;:&quot;function(label,axis){\n    if(0==axis){\n        return label+&apos;M&apos;;\n    }else if(1 == axis){\n        return label+&apos;D&apos;;\n    }\n    return label;\n}&quot;,&quot;bg&quot;:{},&quot;tips&quot;:{&quot;parts&quot;:[&quot;今天的温度是&quot;,&quot;摄氏度&quot;],&quot;textStyle&quot;:{&quot;fontSize&quot;:12,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;宋体&quot;,&quot;fontWeight&quot;:&quot;&quot;,&quot;fontStyle&quot;:&quot;&quot;},&quot;lineType&quot;:1,&quot;lineColor&quot;:&quot;&quot;,&quot;fillColor&quot;:&quot;#FFFFFF&quot;,&quot;fillOpacity&quot;:97},&quot;tools&quot;:{&quot;fullDisplay&quot;:true,&quot;collapse&quot;:false},&quot;effect&quot;:{&quot;hyperLinkFunc&quot;:&quot;function(xAxis, yAxis, val) { return `www.so.com/s?q=${xAxis}+${yAxis}+${val}`;}&quot;,&quot;refreshTime&quot;:0},&quot;padding&quot;:{&quot;left&quot;:5,&quot;right&quot;:10,&quot;top&quot;:55,&quot;bottom&quot;:5}}"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<AbstractDataConfig class="me.hugo.fanruan.plugin.eheatmap.PluginChartDataConfig" pluginID="me.hugo.fanruan.plugin.eheatmap">
<attr xAxis="X" yAxis="Y" value="值" customName="false"/>
</AbstractDataConfig>
</DataSet>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="1152" height="648"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="chart0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="1"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" horizontalGap="200" verticalGap="100" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="c2f8a6de-8890-4da4-b2dc-7d9c2f775e8e"/>
</TemplateIdAttMark>
</Form>
