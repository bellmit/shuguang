<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="9.0.0">
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<WidgetName name="form"/>
<WidgetAttr description="">
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
<Center class="com.fr.form.ui.container.WFitLayout">
<WidgetName name="body"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
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
<Chart name="默认" chartClass="com.fr.plugin.flowchart.customflowchart.FlowChart">
<Chart class="com.fr.plugin.flowchart.customflowchart.FlowChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Plot class="com.fr.chart.chartattr.ChartsPlot">
<Plot plotID="FlowChart"/>
</Plot>
<ChartDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</ChartDefinition>
</Chart>
<FlowChart dataset="{&quot;title&quot;:&quot;&quot;,&quot;nodes&quot;:[{&quot;name&quot;:&quot;开始&quot;,&quot;type&quot;:&quot;start&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:2},{&quot;name&quot;:&quot;操作&quot;,&quot;type&quot;:&quot;operation&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:0},{&quot;name&quot;:&quot;条件&quot;,&quot;type&quot;:&quot;condition&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:4},{&quot;name&quot;:&quot;输入输出&quot;,&quot;type&quot;:&quot;inputoutput&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:1},{&quot;name&quot;:&quot;子进程&quot;,&quot;type&quot;:&quot;subroutine&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:4},{&quot;name&quot;:&quot;结束&quot;,&quot;type&quot;:&quot;end&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:3}],&quot;lines&quot;:[{&quot;nodeModels&quot;:[{&quot;nodeId&quot;:0,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:1,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:2,&quot;outDirectionType&quot;:&quot;bottom&quot;}]},{&quot;nodeModels&quot;:[{&quot;nodeId&quot;:2,&quot;conditionType&quot;:&quot;yes&quot;,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:3,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:5,&quot;outDirectionType&quot;:&quot;bottom&quot;}]},{&quot;nodeModels&quot;:[{&quot;nodeId&quot;:2,&quot;conditionType&quot;:&quot;no&quot;,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:4,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:1,&quot;outDirectionType&quot;:&quot;bottom&quot;}]}]}" styleattr="{&quot;lineStyle&quot;:{&quot;lineColor&quot;:&quot;#000000&quot;,&quot;lineWidth&quot;:2,&quot;lineLength&quot;:30,&quot;arrowType&quot;:1,&quot;scale&quot;:1.0},&quot;nodeStyles&quot;:[{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:1,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#ffffff&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#e6e6fa&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#b0c4de&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#add8e6&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#98fb98&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#ffffff&quot;,&quot;fillColor&quot;:&quot;#228b22&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#f5f5dc&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#f0e68c&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#ffdab9&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#ffffff&quot;,&quot;fillColor&quot;:&quot;#d2691e&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;}],&quot;titleStyle&quot;:{&quot;fontWeight&quot;:1,&quot;fontSize&quot;:18,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;bgColor&quot;:&quot;#ffffff&quot;}}"/>
<ChartDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</ChartDefinition>
</Chart>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
<WidgetAttr description="">
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
<Chart name="默认" chartClass="com.fr.plugin.flowchart.customflowchart.FlowChart">
<Chart class="com.fr.plugin.flowchart.customflowchart.FlowChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Plot class="com.fr.chart.chartattr.ChartsPlot">
<Plot plotID="FlowChart"/>
</Plot>
<ChartDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</ChartDefinition>
</Chart>
<FlowChart dataset="{&quot;title&quot;:&quot;&quot;,&quot;nodes&quot;:[{&quot;name&quot;:&quot;开始&quot;,&quot;type&quot;:&quot;start&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:2},{&quot;name&quot;:&quot;操作&quot;,&quot;type&quot;:&quot;operation&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:0},{&quot;name&quot;:&quot;条件&quot;,&quot;type&quot;:&quot;condition&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:4},{&quot;name&quot;:&quot;输入输出&quot;,&quot;type&quot;:&quot;inputoutput&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:1},{&quot;name&quot;:&quot;子进程&quot;,&quot;type&quot;:&quot;subroutine&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:4},{&quot;name&quot;:&quot;结束&quot;,&quot;type&quot;:&quot;end&quot;,&quot;link&quot;:&quot;&quot;,&quot;style&quot;:3}],&quot;lines&quot;:[{&quot;nodeModels&quot;:[{&quot;nodeId&quot;:0,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:1,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:2,&quot;outDirectionType&quot;:&quot;bottom&quot;}]},{&quot;nodeModels&quot;:[{&quot;nodeId&quot;:2,&quot;conditionType&quot;:&quot;yes&quot;,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:3,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:5,&quot;outDirectionType&quot;:&quot;bottom&quot;}]},{&quot;nodeModels&quot;:[{&quot;nodeId&quot;:2,&quot;conditionType&quot;:&quot;no&quot;,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:4,&quot;outDirectionType&quot;:&quot;bottom&quot;},{&quot;nodeId&quot;:1,&quot;outDirectionType&quot;:&quot;bottom&quot;}]}]}" styleattr="{&quot;lineStyle&quot;:{&quot;lineColor&quot;:&quot;#000000&quot;,&quot;lineWidth&quot;:2,&quot;lineLength&quot;:30,&quot;arrowType&quot;:1,&quot;scale&quot;:1.0},&quot;nodeStyles&quot;:[{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:1,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#ffffff&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#e6e6fa&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#b0c4de&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#add8e6&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#98fb98&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#ffffff&quot;,&quot;fillColor&quot;:&quot;#228b22&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#f5f5dc&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#f0e68c&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fillColor&quot;:&quot;#ffdab9&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;},{&quot;textMargin&quot;:10,&quot;fontWeight&quot;:0,&quot;fontSize&quot;:14,&quot;fontColor&quot;:&quot;#ffffff&quot;,&quot;fillColor&quot;:&quot;#d2691e&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;yesText&quot;:&quot;yes&quot;,&quot;noText&quot;:&quot;no&quot;}],&quot;titleStyle&quot;:{&quot;fontWeight&quot;:1,&quot;fontSize&quot;:18,&quot;fontColor&quot;:&quot;#000000&quot;,&quot;fontFamily&quot;:&quot;微软雅黑&quot;,&quot;bgColor&quot;:&quot;#ffffff&quot;}}"/>
<ChartDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</ChartDefinition>
</Chart>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="chart0"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="0.9"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="JAA"/>
<PreviewType PreviewType="0"/>
<TemplateID TemplateID="d65dc2cc-6a87-4826-9fc2-47ee1743e31a"/>
<TemplateIdAttMark class="com.fr.base.iofileattr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="53957fba-d407-4e20-aa14-9632b97bf9d2"/>
</TemplateIdAttMark>
</Form>
