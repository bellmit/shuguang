<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT
     产品.产品id,类别名称,产品名称,round(sum(营业额1),2) as 营业额,round(sum(成本1),2) as 成本,round(sum(利润1),2) as 利润
FROM
     S产品 产品 ,S类别 类别,
(SELECT 产品ID,
     round(sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣)),2) as 营业额1,
     round(sum(订单明细.进价*订单明细.数量),2) as 成本1,
     round(sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣))-sum(订单明细.进价*订单明细.数量),2) as 利润1
FROM
     S订单 订单 INNER JOIN S订单明细 订单明细 ON 订单.订单ID = 订单明细.订单ID
where strftime('%Y',订单.订购日期) in ('2011','2012')
group by 产品ID) a
where 产品.类别ID = 类别.类别ID and a.产品id=产品.产品id
group by 类别名称,产品.产品id,产品名称]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false"/>
</FormMobileAttr>
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
<Margin top="8" left="10" bottom="1" right="10"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-985610"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-985610"/>
<LCAttr vgap="0" hgap="0" compInterval="8"/>
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
<border style="0" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   各产品大类营业额\"]]></O>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="SimSun" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="SimSun" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="true"/>
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-12279553"/>
<OColor colvalue="-7551489"/>
<OColor colvalue="-13320285"/>
<OColor colvalue="-18398"/>
<OColor colvalue="-10456860"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="0.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="类别名称" valueName="营业额" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="5" isDiscardOtherCate="false" isDiscardOtherSeries="true" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="0" y="38" width="480" height="200"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   各产品大类营业额"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="480" height="38"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   总营业额利润(万元)"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2368549"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-2368549" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   总营业额利润(万元)\"]]></O>
<FRFont name="微软雅黑" style="0" size="80"/>
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
<Chart name="默认" chartClass="com.fr.chart.chartattr.Chart">
<Chart class="com.fr.chart.chartattr.Chart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.Bar2DPlot">
<CategoryPlot>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="true" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="true"/>
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Legend>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="0"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="false"/>
</AttrFillStyle>
</newPlotFillStyle>
<RectanglePlotAttr interactiveAxisTooltip="false"/>
<xAxis>
<CategoryAxis class="com.fr.chart.chartattr.CategoryAxis">
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</CategoryAxis>
</xAxis>
<yAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</yAxis>
<secondAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</secondAxis>
<CateAttr isStacked="false"/>
<BarAttr isHorizontal="true" overlap="-0.25" interval="1.0"/>
<Bar2DAttr isSimulation3D="false"/>
</CategoryPlot>
</Plot>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="32" width="480" height="238"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart1"/>
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
<WidgetName name="chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   各产品大类利润\"]]></O>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title4VanChart>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="SimSun" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="true"/>
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-12279553"/>
<OColor colvalue="-7551489"/>
<OColor colvalue="-13320285"/>
<OColor colvalue="-18398"/>
<OColor colvalue="-10456860"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-6187579"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-12423245"/>
<OColor colvalue="-10043521"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-13031292"/>
<OColor colvalue="-16732559"/>
<OColor colvalue="-7099690"/>
<OColor colvalue="-11991199"/>
<OColor colvalue="-331445"/>
<OColor colvalue="-6991099"/>
<OColor colvalue="-16686527"/>
<OColor colvalue="-9205567"/>
<OColor colvalue="-7397856"/>
<OColor colvalue="-406154"/>
<OColor colvalue="-2712831"/>
<OColor colvalue="-4737097"/>
<OColor colvalue="-11460720"/>
<OColor colvalue="-6696775"/>
<OColor colvalue="-3685632"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="0.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="类别名称" valueName="利润" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="5" isDiscardOtherCate="false" isDiscardOtherSeries="true" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipCategoryFormat">
<AttrToolTipCategoryFormat>
<Attr enable="false"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipSeriesFormat">
<AttrTooltipSeriesFormat>
<Attr enable="false"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipChangedPercentFormat>
</changedPercent>
<changedValue class="com.fr.plugin.chart.base.format.AttrTooltipChangedValueFormat">
<AttrTooltipChangedValueFormat>
<Attr enable="true"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-15395563"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</refreshMoreLabel>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="480" y="38" width="480" height="200"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   各产品大类利润"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="480" height="38"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   各产品营业额利润"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2368549"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
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
<Chart name="默认" chartClass="com.fr.chart.chartattr.Chart">
<Chart class="com.fr.chart.chartattr.Chart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<ChartAttr isJSDraw="true" isStyleGlobal="false"/>
<Title>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.CustomPlot">
<CategoryPlot>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="true" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.CustomAttr">
<CustomAttr>
<ConditionAttr name=""/>
<ctattr renderer="1"/>
</CustomAttr>
</DefaultAttr>
</ConditionCollection>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="true"/>
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Legend>
<DataSheet>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr isVisible="false"/>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="0"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="false"/>
</AttrFillStyle>
</newPlotFillStyle>
<RectanglePlotAttr interactiveAxisTooltip="false"/>
<xAxis>
<CategoryAxis class="com.fr.chart.chartattr.CategoryAxis">
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</CategoryAxis>
</xAxis>
<yAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</yAxis>
<secondAxis>
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</secondAxis>
<CateAttr isStacked="false"/>
<CustomTypeConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.CustomAttr">
<CustomAttr>
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</DefaultAttr>
<ConditionAttrList>
<List index="0">
<CustomAttr>
<ConditionAttr name="条件属性01">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[1]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</List>
<List index="1">
<CustomAttr>
<ConditionAttr name="条件属性11">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="NullMarker" axisPosition="RIGHT"/>
</AttrLineSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[2]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
<ctattr renderer="2"/>
</CustomAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
</CustomTypeConditionCollection>
</CategoryPlot>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value="类别名称"/>
<ChartSummaryColumn name="收入" function="com.fr.data.util.function.NoneFunction" customName="收入"/>
<ChartSummaryColumn name="利润" function="com.fr.data.util.function.NoneFunction" customName="利润"/>
<ChartSummaryColumn name="利润率" function="com.fr.data.util.function.NoneFunction" customName="利润率"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="480" y="32" width="480" height="238"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0"/>
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
<InnerWidget class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   产品销售明细\"]]></O>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
<FormElementCase>
<ReportPageAttr>
<HR F="0" T="0"/>
<FR/>
<HC/>
<FC/>
<UPFCR COLUMN="false" ROW="true"/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[990600,990600,952500,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[3238500,4572000,4686300,5067300,5867400,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[产品大类]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[产品子类]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[营业额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[成本]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[利润]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" rs="2" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="类别名称"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<SelectCount count="5"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[row()%2=0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-1641987"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" leftParentDefault="false" left="A2"/>
</C>
<C c="2" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="营业额"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[row()%2=0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-1641987"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="成本"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[row()%2=0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-1641987"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="利润"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[row()%2=0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-1641987"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="2" s="3">
<O>
<![CDATA[汇总]]></O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="A2"/>
</C>
<C c="2" r="2" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(C2)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand leftParentDefault="false" left="A2"/>
</C>
<C c="3" r="2" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(D2)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand leftParentDefault="false" left="A2"/>
</C>
<C c="4" r="2" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(E2)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand leftParentDefault="false" left="A2"/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="16">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-8078337"/>
<Border>
<Top style="1" color="-1"/>
<Bottom style="1" color="-1"/>
<Left style="1" color="-1"/>
<Right style="1" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="16">
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
<Background name="ColorBackground" color="-1641987"/>
<Border>
<Top style="1" color="-1"/>
<Bottom style="1" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="16">
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="16">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-8078337"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<f:@;ch\]Ae[A4G/(<4(2b:]A,e7f:^&J;S8e4u9P:p<@n5tG/k;FNj,&."B)3gR$J;PiIba3
$r1(T9eU+XBo>Um0%7!@-YP";1b]A+XA6*j?M9l#A6TZo)E@:chY>e=>&[qluug.rOgJJmYrT
;L5.tmm&F\7/R%fEqsV5qiM&b:OqoWQI/Gp!_`qj\$Y`eO#a>5d]A3BGi<D85p?c4HeO]AeEm=
J>4F>3RgA%>Fap$F)SZl8RHBdBA%Hm-'^QVUs5QJ$qJGn"]A??<bHT"S;dIZ>P$COgmC0I5XT
IRc:48Z(R;L[J%mo&j_seYBD-%>[f#+k;n'Y<hBA4dD!-h.rVjp=;U80=p\/bUTHC<20DTYi
r1QJNlk5)mnbR"Pgq,a5*P5'U8oK]AU.aiE_:'Ms3DtrR,=,fBZe8;467@3_tA,Ch_=KBt!ET
ECWG6He@_9iL?Xmt;;(Ycs/hoH&'/Ao;9BlrP5^eF3^a\XBs[HQUB<Yq!$X>d!'Md2]AdHS0H
ZUl/%/01uiGMtHMJ0iIi='[T8iNHdBa&?m+\d:_p+X.q^ebn9)s_=9gf<%G2,U(G$j6Ja$Mi
fja>rFZkZUD6jF.[oc@e^K=oRj#,Y?ZAB=$Zsb^80mF0%Z^90=6#aJ:oXQ4b3bWQ?fY'7rSj
t1OlF!@0/PO#<U`YGh_^;#->3bNNO^5Br^O-cfEke*m0>6=/f-T'5&dp=<j3$*5u9fS'n]A9*
]Ar[++HfU$\7U"6/R'<0XY1_P?\fUZYk2!5JGDBcKN/Wh18F<#:gPVsIn'u\8ah"HSNCdif/u
Fro8=(61UALO^#U3"0@&^`[Ah[XVHWA(Gke6>_M$uARbL>[hJDF4cIYYZR*r@iLAT\870:0V
!N/tqr,?mduhHs<D.,h,gSuh#$qR(?^$<Y\02H^$ka@[$pJA-O6#AICjd4'8@HX?'(EBeT9d
e&<jmd0#_c@G[1!NDF!YnZ[e5.;B'MtC1+q5uhAPc5RO6\f^I&0]A9Y>ggOUk(cutcODlql#'
CSBsXUDN)^#,^G4l3<>,\FfWtjQ:PjHVCVu!8h`g<"q3BWS5nh^o^;PM-OVDXaZ+5pNP+Rr+
[af<RVsDcJ-6Ch%l$dm=^[)%3gk[[8_P&6YN'HRU^*<R@$EV.hLVN?f/tY,bW92l>n`'^Ihu
5o8f*;mN/(<fjhJ9NZn2R6JEX2[3nS^_m`.Sr-EAbDc0]A/9]Af)<iTIu_7bo5*(r]Al+[h#k8?
Q^m(DPNN@%[T)L_Y`7W?IhDNQPJVWs+)mBtNQ1*LgM/`W4:+cFQAtSLHhee"S;lbfi8$<ak%
Y+*AG"W<og[F7hW-(;B7,f-9`n.0ELsKAHSdW>^cR@dN%nN>&:qL2n`-RP(`;UhPID?qN)SA
Vq=1dnU0qWRI22/s:^sCrd241ocJSZ*DEZ>u,p3n6<#<ImtGW%_2e;\UpcIck/<5V-Id;HRH
3'3TNhF%uSdBnWK>O*5Jdrm_n2m$^j9k8$l3b>W_\IrUL24eOQdhaoL(M?G;,92G0WlUpr5t
a0hpRumrr[`]Ao8$jL:2%0PJL4=)adrLps<m<143oFtu'n`j$hLMnjK"hiamY![;>j<&Y15:Q
h&#cL6\dOSf(r>;B%jBPdd965:d`LN.<?TGm\ZTtiKr,5S(+fiVnG8IW4eVu"GUc/hh(K/_8
4MR/&BDno%%+VG*g*Y)`YDRBlG\%+YraT.'Mp%GA6#",2X;;/#3==b>\KM^eclCBS%<Y#cm5
hY2K7MV;;?`nDV9^j#&cnYB'o9jY.Wbi=g]AR#>ZVJn=*O^=%ZV[HWK@T5h`t?QB"TA]AM]A/&R
A\h)K"SPLeOmo/e>`2Q&bM`SlNNmD]AX^`4R*CDrE6So3BV?:^s(elb?X26&S:XRP47=:uNbL
eSI/R`26>?!P";NY%P9KXgP==]Ad5*3-/OngZSka^/\[pgWKs+]AWr68<m)s3IF(RNfObX'>DT
:om`R/)C^SaX5%JrK5Qe>>#-^[k6F6OI4uW'T:tV7gY7a<$$f(NVc#dL!+e#0eAS8dUk$e%s
6sFr>jad3ehaC&FuU?"3mn2GC5YinjNQ=)Z/k2qk-<Oi1L7TcMF5FMI!6pYfCMPC4OF]AF7?[
!3F5cX-I"s+0dbUB_^qo!5<Gas@=G<i;XR&)YccN7]Aia[G4/hR^>PBe5k/n$RNj1okpe(p8*
#^oj6DmI?6_[(K34Q2$?X&*+af$.UbKmOI@aHn:P8<*&bSC2#P?-)u3Rq/'V<5`A>+tP$`>q
Eglb6hl[j[2]A`iau@]A2[`+)H-pp3GOj9D6P@WQLa;o<K2&IR(*9cnMpT`;UJ'oT-5N<s]A.l1
@d3;/BaL/4-=4o>a<9<H,.cVXSD!f]ANeRFqdkt7^)iLri;_pPV*)7+3]A.6jZ;\T#=Dk)$%pj
DgZ:BtT.P^QI$PX,D\3+9p;ZZ#la-;/pfDJNY#]A=NmH2(p^U2Uhb@]AS/(>X%rcl6#aBMua/H
N+b85/?OCR0]A1Wb<4\ZPp4Bl9HR[?g;fBgtl(i:%<4nD05.9JS]Aip'C-/p:lg:j91G&hmih4
kWRobCNA\oG30OYF@oE"qk$;s?\OCBG1tH2`e*Q0DPEKHALXN3n<SpD@UtE]A-dcWmqS9S^=N
WalC9UgqT6*b%_Z,QA6T&P_a6`#s!t+j-Dp6rZqH0&'[_7?N,bYO$9dRUbIq=T8O^oa/(]Ac>
.F0g2Lg^LtY<-__UMbr.r7jW\W9p"T!Au'&K^i&DaV3,2R6J5X5JgN67>S'Wj7F]AMa2(B:D?
lLO4qBT!l5B6jC0s$6/1tm84K9NnJq7,3b"$[R$&OS$%eb%Oh)@&V^k9OOVo+$?a,]AeHB*(Q
hCktr=H%%d(%1$f,&J]A5Qq:d$QaUo]APrCT[J*)O`6M^.1%5((!Ik@emG&]Ak"1SHB(;@>UW!P
oS^Rs80_fHn#7_1JZpu76L@54gdJAK1T]Ac.!Oc9NRo]A2M7_E%>oB_LI4d)5,_f068q#f/pA5
K?!O9/`0ls=VYp3lQ+0[a9cJVUKL(q@XKI^=N\!Dfp+BDTnjK@urb!mWA%^'S:b^/#EDl++o
P$+K<%YAF=mQ3+-%7o%gg)qC8^'a,PcR`;65g2)1f/(u_3II35>Tok"d*c09<C>Y!T0Ln(Ns
(_6152>-:8HIRD5D8XXom(80AZj"*WQa&o<'3\>pVIkecUIscdnnCQR4]AkMe14]AX0!OBTD#l
`<Eg8c)""q^Cd.c-M?9;2H,h7\[rTE\2W4t$)m_c)2VP5K%@p6.TXJtr-rD]Ah;q(l.(2KkB,
/.ko!kM+/Q[X1GI>DW@<+ZuASZ?h3D+=inIk(^H8A+\1#Z!h)ZL'$B#Q"_Zs`m2i>6sf7e>;
=7U0Wj6)M<Kj@?\jT$DtIi9,!FT)T>8@MA$gs`Xpf\4(HMT1`WJD$9PZ5(T^4"'b/VQo**p.
Kh"HbuQdAmP#U5#X2B2SEn\^NC0&aI2nA\R?K+*+._O7(tMa8T4_edP;`!Pu%IfXj8ira./)
M<3^$N/A2/VdOlO%4rI2eFW$#JYYk$(_jebc^dq5i$6Nq/V(4b)QLeQQ9'u9JGEl%Zb(aU62
;[SAnV]ASCC//MNI?3S/WC"l]Aa(^J7'Y"[*M8*DD./Fo;TiqE$eUi=LHA$.mMUc2(&)Ep9ImR
A-G*R]AeoOi^g%qf)o:k2!&u=iM6UfK-k:*%?f*.#/<%!>6(Q'Y4`=9_q,\H\ql+I3kg\&2o6
;GCAI9Uo5X+O&k)"Zm:&Ii=V[)+HYG2$Q7;iO-YC4Nq8>#o5*OJBi->1k0Lt:=F94%Ekk&oX
V@9?1ti(j-iXt43XjfW-X"(Sf"N/b8l"[3THWCa63?i$9D5j#;'5_p.UJ&'qC#E^o$]A&WYqI
i:,8!]Am[uo/3;baK[/]A5OmoMq%P<&RH:VLZCX8>V?*;#?e70.UMKiklbDLYT@sV"pDR8\P%C
6$-2O44it4orjk3Y1ERlA`=%^qdEo<YEjgkJE[5V#H=ngh=UYFYIb6k/1DLZaE.!.t+jMo=Z
M<UJC)n^iIc[Q3(0.AFT#?r(pMYlo_+TGD"^a"&M]A?uL_BPHcinJH1B0Xju:a2AjV9P[8]A'i
>Za);6m\;5sdARa?+0%Lg29/P!/#R`ORWqe#"Tk8']AcO.Z"fS`E[U?gWksZ=m<u4t+3)XmD;
)3gms[b"@OIQ7NH)Fl+'2ou1$'-gHb10%;Y5I1h7=C[rK6+`BCVBXN_l,]A'M!W&N-F-IirbA
'XY`g1I^^2O_)*BW,K:/#oB8_s"E:Nd5lN.1DT?ic]A&o%ULM)!?!9,6"n/>KV1:iQ#/6Xpl7
L;SN!#BkRFjsT>^6GABYUaTOh;tCQo>4;))(JHP_Olds``3ok7E,i]A3*1j&]A/re&at)+Z@"!
CMa!u'bB[LH3"h.g&2)*8b]AYg^SJ^,6@f^=1R*ID=/M$V`Wqj%9#]A%kR2F3qmEFg>lH\IZFe
1otLAf-Qm(')W^>npVnfKQeN_I<o(3K>&ndh%`0X'Yr#&:28<Qjtn#o`13dufg%)dMn>l5q.
TEc<UjD-I&)dnRk?GIWa,8+s(TZ!PQ?JPL\GQ53$<RDuj!(0Z;]A"")'RgL;/b]AXm^R[1@2a8
e``'[2iuK\ZZ>a/LMpG7Rjud.Zo)G[T5#I"9]AP7F`=$\_?N"q[P)?jNZ-l&81rj;?UAd\^Ql
<ZDs$ij'E^EphV^hWSSiKBO?7cS_?s\*;qf6rAA*EpM$TNFj!#N1:q7HcBP%BaAG36NU);,k
S^2n64%"/T.XWUr$dhX/rNoO3qee6)<gV)g?u"hFR:`V70El7^!1N20lX.9lFB)US9EcF:&c
ER&`kiBA@jf@n%#I?nWAH>0=,_dgH/Z3%9'5DYj+sI;K[,ViP\_sWbK.mK`c%t\jb.e]AO$i#
N0_TCNG=I_HP7cZdOg$-R$7ILidcXpZd*h`3FA?Eb^F'POhXd4/ethGdK&D'g%JMoY3+Ud"I
!<(^MKH>:Z[[FAM1J5Y#8qC9Kd;d#`>j'((.'.4X]Af<k,%sb@CnQH_V%f,QpA.a8Sm=X\Q`,
.D:7aY5h_T&KpgG?M4&'9f>P+HROLIT9Fj_[AWf=]A!K/_\YMF;,N!FP]AlU3/si?Ti%f`89$:
93LjnL0375J2?lp-D'P41sSOaC5ZM`0mOa"QK.-M`f]ACXoNVrI3mY?e[<7inJ#Ij4VhQudh6
A5QBc$J[:PYV^(H4EuL`\87aJbj6Xgp!ZJ7G4CTi/&%>^DC@K!elCe&?7*cQr9c7U*?[8fMW
U_Kr'IJAIgU\n\t=EX+Q6O_^3+eG*n]AnWgSP.L+!0'!#W;hI_FpnOoOm0',EC-r7&-@<4!d-
;$.*Bj`21CqiGTg2^j$=LW,R]A2mdDLjdmXCL[jcnAedX!\?oFC.!lII)FtbQp04Z,U2B<+0!
%^!2@m8Ec%G3%<%FF]AX04MQWY5[Q6BFoh$8Q<bj"JqqF9AT[&_cUFo]Am&>]A_>CGF]AMj@cAV#
jfI>LEBnu$Zk(3uQn%l/.G+[f5"(>nMt<<!,YNmE%U]A(nTShOcW!a:1f$8-4K^Xfad8c`Pj.
JNhfWle<@l5.#C`Au74-/LkEpGT2I?CUqBWK[D,3PD.,&YG"A@mD_AXIJVmVl.r#h*,-bu<g
-j]A-Sl#+tI;YMPodo2L*=8I\8OCO#"nLcEL7X+k:\a^M.*\cH`UVJVnM@:A?02o3,TZ96M[4
E`bt3_n`X&2*ra1KMn5ZPBt#[,iHP"31Xm/S+^!V0hWQLD\,NL:[jT)*a(hqu*[QosRJdc)K
41>C#iNI\2?SSVaaYLH0)i\$.>lLsCNGV"X"p4Mq=*82o@=MBFIMgq,2iDVd)"gSpBX7!rkb
30>q4:YfO2M&ueIgbg>:Ln\g.KI.VdC2Vf^0U[c8G*D=Yq#-f,o447dZ'T(K.r%d2VLX<sim
O.#[\M&P,^.pugfRAoV4P>BF6itqp+GRKASY7\_$?O^l#V+[#CusQ&pD2fYBlW;B<uNN;UJJ
mf3(HD2UJPRkd^P"\qNX4c$$_W:oro3N/sI0"^n;Q9t0DQ4pn6]AoSV.5o]AJi,oHJ>V\]A6`P9
a`C_\AT<lXV4tKE(/?r1A]A_MR3$1_<n&jiO<>4hp<APf8^o"g>=fLFik`:q<olQImT,Q%=)g
__VLT72jX=qG;ZsW^`nK-E0#&.s>pe\*H2'\DAb3===qWp"=W2$h-bRW$Z#!@.L51QG2g(1h
Mr!6d3HciD&qIEur76sNJT!\0<lTi.Q_"O/3BCY_(RO05B>t'4%]A!"8KOt$af&dc,WIaT7eg
mi-.[58c]AKL*[Cf)Ac[2mLi14n*.=:"W39*1A5gICCYV)lV0Go'+Ma$3?cKEUtqI469GQ`Iu
=9^^*/8"+Vlh!YhV:5qQ`j*br]A]Ad'jMTL>b_FTnH5iOsUN,F-#/!C_4nLJQ:%\SJj*CK*Nc1
_p/+ZO@nQaMQAB/J;0iL\#`,8Q7$o'PcjO-RO/U+T3f.VBu1c5d>gUn>=nV@#/`O*4rqlM3`
crbK*X&RLVHD@/.ffhggQqcH/-OG#0*rW:.W[IQ"V8G;TmS-_22cj74FhmNmrS5pV^+YF]Aaa
U5pM^\n7k0K<RLr-Kpn<AHOP"q%mc\8S^YEjBto0Q<DrkE1(7`!oh.r6Vh0_c=,c?UjQ]AJ#&
iu>(V7G4Rf\oI*m,o%X>:Qpeif8I0H(L<bp>M['iq]A5iWcp!-+.O#C6.8?PpAG7eYe:e$tS.
f*B&j.akC<3Tbf;k*U3D9'`:Pgp_+=M>X%iaN5=2b1GUD!C.*N\0$`1nNg,RUqM1eOZq\o'h
;WsgEg:!e'E,9tba=-I6O-4:ZUEY8O0Lnuf#t#E'F(QmVU`U&*[;j05I0VoT5^k^p.Ih259r
bgR1r7t*oS2SX_@(>T*t$2>jSWW4T9kp/ei(MUC&E">`Q(l+h`%LaXWf6UtduTg=K;C-B+.f
;G(/i\DbN0.<3orJ8Ip)gYaIf_:jU^<:/W`qUktU=pNdD"iFtXF+9g"I-^5]A,jjU[ppmnV1U
G60pU2?uCHKQpjma;oo9H:^GK"5nE_,mHYuR$G\l[h2Sm]Ahl^rHAUongbB"c-$3F&/Km2NQ[
Yn$9P5T>1(?+1@<t,_dc5I`unVNW'R(.aOomY9F0cd^m,Y@TX7(TUaSkrmI$h,f1e,.8JuPQ
6r=/EME1V<?G6ijRrZ^U^R+JH+\4oTLPELfUs8LZ%/s*R[22q-rNk1^h!J''1Su">(;&SD$R
%,R;o7Hm]Ag)q'n)b56GlGU-s'i1f3WIdF\^\Sr[:k(\d`4=6"rY5K-(j/jM=b[;CamsqW\r4
Au:8/:OuU3"tNO=+mb9ribhib\\0#35+uK7YBr1aRf`.XYEC4lE?UO4E9k?0l,Mgl@jL6@lE
oRX?n,p^qq&9MHs,%$]A8#r&3f_$oX"Zbdq]A']A=OrWRkD*Kn=>9]A1T1%J7GYudhbJ@!kD1n=W
::[3aPPM*R&-#(AbG@:9WKU95dFRa_tRDmRb-5OMBRp!\?Xu12B*KMV4(iJo,c19m,otCouL
dCo=3:!8mor"Ht_U#LWZAXB#)Rh%8fV6eqU-ZCNbj@FMm'fu[[KAVVd@[nAOd9Bamg/?\qW`
.@*=t6'6Y<6n]A)O3Y:$<G1:&*LkQM;"WA9a4L8LMcY/,4Oh5),%u_V>0'hN$1Q3d@iB"B\/>
M\L-L3g?S6V<*DiE\!p34P(rZ3<EHI9rua2eNmpKrM4BJ]A1WqKM\XKGUeN+4W38i6"lR?f=T
O<^/aoT?J8/>\iDMk7F\[^:=Y16P_oAhkq'WB#-DY<QN,VDgIJ$O)4+*APLZ_g&5@#uJ`"l'
u'A)qa42dQ\A2S-'&$HhWG7p&``Mo/S[QCd4QQ6YXgDt6"TAZJEFoiDuXS%,BR"7@spKnUGO
-d!Kr]A)gMos`t'N%p]A!eT?pu[neqRhb/DNqk3BB6!a?Yo?6GRLC8\H)EU_Jmjs>g,p]A@ah\_
K"`bem#5q*ra,^Ct%;PD8D"Bu$aQsX8AV0mEG.c^<*WH-<H+V1YSg2QsV/fVei]Afi%I0A63@
h93^9D2XTW#.29QmS6/_=esu]AbR+72T!h^<"A>2]AI25!^=H#2QF>59)"3\Nsa;0_YVAhRAW[
q@ulq)&soHnKbs8>V-BeZjg)Jn(n$="-;%/q;`a]A0_ENjc*`+<i`fF.F!\i\g6jZlc1uGZ'Z
@Yd]A=G_-k;@\pem=cG,u^,V4!A"I&o=c;Qk3ee6`m8POh/m==#E_nWlQN6=5`)5l>AXaY7.0
;<l<?NK]AcO\SC3pK1M8DoY'&PI9'hX'JQGZ,d5"+ERBOnA.d7;!ROc0<*rjn5dJskC\1j:<k
X-(q]A(mK7s4ZH.GuTYn(3[k$#0_>Z8Aj@@j`CXST2BZ@/dm6,g'\di5RL2K`&W@-`3aVql,7
gm=4^V"lEM^snm+N:+Fr6tDe-jJLljbF:rhbuk/fqoqnfa+rT%cl:q4BJ3f?[mCrHY`5%_%d
@;bpOQk@nUO9oEck:/8&X)RA!ttBhK^JsP#hO&\-Ct9.Z\G0%Q9U?m9,&cKo;1!75EF;VDXK
=Ru'RTR"4?\&N92D2>-S1&CWdo1[M\8>8Ab,lb#*-1Mh*qR+5"*aFc5\eIs8[)n%d-gY*I_\
`YZs]A'#eZjg_Tmi4ZVR99S$/BeFUVOUBT>9c@*=p"K/;euo:kj,8R<n8k]Ac$'l^nZJn-<>Hk
F,j7TFfQq<usX*p/2/a(cOA$uY1p?h<haII'r9b)cVPQ5lHI:1XbpljB0s0TWP?bf9XZ$[No
FhXHH*Rj2/dB"UI-l=^@92]AW1EoQqt'3'"4#rZI;m>$N.#Ubfq2J^]ANZd'cbY_B$tCrt(bP)
2JC+,?,YOFUs9ki#s)6bD2]ADGio2mE?Oj-^=rJL7!FZ4p^!6NQ]Ak9FPXfpCPWDBO?ISdi;*;
mLJ!"d&mI>smA-[aZSQR0Dc?d?e`%`j'&'4SRX?kY0khA;[tmt1qN-7W\N:c%4sCh.WXlW\P
h*HbH`F88ZA"-:b*t@bQIKnd)ZZ]A,*\g9sW`K_,S1o0+C[ee@X-$"5J4Bl`KW9H+No^!S.V^
]A<5ei7SogOCsJ'hb4?apfS4-:#-HBU%CpL`sL9HUA\]AJA";CI+Fp.10M3;ES?U<iF1qW]AaB9
0Zac[P`,bLYQ_PP.Rm6a-ASCE&Pj7P:S]Ah>#TDO-Jc_C@]A"L[&T]AlHuKF]Asges-_3A/G$KlM
Sd"-RW,,LReFIm0%LC8=:(:OSH.G[gb=I9W@ip&hFBS^m:Bk4m+X/R5Gg1DP@UAX8)b-8ndD
>`EKrKb:ks.ELQZFM4?`*=^PD]A0fGrW\i#U'cFU2g1@ZW:qA!f*qZ0c9Pqmhrg@?\&J#9i\l
=n^1DM1%rLg_hl)0O'0^)U+#S994H3<'QO!f_H]A*QZ8O@Bdo_aQdJ&E?4iVJ#`&MYS?M3Eg;
1Jr']AZBBsb8n[&[Q@;1Ko(VCfb(j-4f>\.?X3Mu"@D@?6C9a8pIO_^7b-$dF*0[+Yss)%IN@
'eS%b#)g$S*W64BI5DqTAU`ia>\J#rSKi/J.U"TXT?cCmWYf8'H]Af2=%h-R(^$\>S$BZ3or(
-3CcufomNf:Xs\,/.,d=&3cop,Xfk<KG$'_a]AX8eKZ;2+`:8<cX,[#!dCsD09-(+/)5l?d?j
*(EAL!@t^_.q7@fTr-<,W4IufNX$%`A^A:B$6#gi\H%s(k2WDU4)h"Y2D(b!eBck@ZGNH:kP
r$,^Zp3To1`0bLn!#ud.F&Pk.oO*3<K@P%X@J$/8n!X_[n5JjMO:O8!/Sndq(s9;OoO?75Vl
ac@P,3OO?)[;]AK@kQ8Qpk>jEUa2&%)2iU^srhZZOf[6ac?MF+oZ++2S/&.dIDsD8&lpN"\UK
GLcTBh`-sKYtCP(321u?2':D]A>FW%^>c89Y6`_n=lW\q/Tb*6Z'+8ligk=%[B,NgUZZKo4Tu
CUZ?CmB9jEG9gX_.E-pU?QTPG)NYjc'(%Ltr'lI'`AB;n&g.-3eL`7f#*R;N<d27nH>[DJJM
HQg._?1S7eB;B[5PGlSdl4P)rNol\>+Xbdt/d:9g@Lg6]A9;dt3*?i(b?5Or9.ghibHVL2/21
FD3r^dX_^P2^R>;IRl#?nE0-WYGU5%Os"&Wj0#bA%qQi*MHi*U!ZWoW@/c?@Wf$F`kC=W5Fd
cg[HMo5b2qa,<&)7-*'$;F.HP_Zj;55r*Ja"Z`1fSq"!^`%)c]A#>EI$qD%8@Brj[]A0rD0kSO
cS4g,mdT>f8^L6l,9.#4jT?%D?tCdk2;6ctQ3&fE?0aLtNRCDtZK\^B)T^3P4!JqD8";,/YQ
-9'eh0lJ6buQHX?*RT;JEGE;,,'C;mThZ+"Gk6jZitZQ*#u;?6)+\WtT\4^ki9=*rTO%_^0(
uPL3q>BR9L]Ag!j'Z*Qj_l?'gE9-Js6PK6$)VXSc<XN>RiHRAL9O.-[1G?h^7pA^*SW!oLqmq
BQ\\X9[07,2.(qN+q"u_%0Yi8<mJ_SN?4")t_Lui071r;JK(A&+_"tqW%i>!*n,K(S)9:is0
eQ_QA1_UK1N"6,_(qb);kkP\;-Q_L3%%k$I]A%48"Y'nFfMnJ'+usNBV<r8p4.l(Ya*3rV-PG
m[I"V1f)PWre:cJaPr*Rg/m/Tq=-Y,2HD=Okr4tV"YP@I;Q5&!5#K^crUdoLa@RJeYMgP4$C
4O&*C"]A<a.N5r_KbXf^QH@a.+4R]AJXb9+1&g`"`,/K0R1H$oUW26/TDJ.R>558TDn`/3l)@e
^>+]A<-,<n*[8]AC7eF^I6K;B8Brpg+3Lc-_UD[n?Yf;lj'05i\as)`H?A\KDFT@t&d?#@$KN]A
Br8hC[oO6%c<oIS2gn,`)mOe.^m$p[!>g#M._ZLO&kqf`nJ2b%*lQ[TKKQi6@!4H5TK:fcC\
.!kJI2-n/E0mDoB78AN4"kX]ATNFQf2Q2&u]A%@T>"^BgeadJh!<6H<<Q'5Pu`9Z2X5MH.mr`X
D6%[Sm?1A;IdY9<)ng3_\--U6%o:[q_k0#J3IM[@HOmNieG^TK"6f\liARI5[l6P"fSd8BVu
B+.apDgUF]AZoUU)t]AYggAf-_T/pZNuXnQ'+KBZOLXVoAVWDa`X",nOohe!*.F]AsEVX(o7)h#
XiOkXfbrQ$NSY9[*P2bgO6gA3#`O+LKX9,'_"Wt2*Go>J6Mtf4PD-Quum/3K9FHoY'MZ8O=g
.SNldRS0:^S.Qd*+D\>-.3=^S\kG>Vr=-91Lno\YtGmL,,t<Nq&O)818jn7``TQ"n?l$U.sP
HbYqT`YKabbceF[G/kjDJZTJO\.N*:JAAd#m-94qc1Dj^'%iAVi?%o-,/bK`>Bo&iA*Uk)HS
m_T)rE<hB7nf"(5ll73r/&I1kH_']Ak^Mlg1b\=*:_k0]A\s,T`=5KPs4aP/qsD+,V"n=Pq!SV
HX*dr=EWfUL0^9uA=mI-VJV^Ie:CM(!V!Z7n1?510/j*Di1\&7f-eJ&Gc+T5mEa=1rreIcTn
c5H8RL/@Q[@]AtNE<\;e@Cr'(Hd:.%.R_4:ikhu;J",<oMdf_ihED3"J>U/#>\cHmh>VsFoVM
X_nT689;)3[$*7"o_]AgPT2G4q4dJ8In<W=MR7k/#i$EJ"TF&ITp-Ur4B![CDYGLMfX*h;`?&
jmp/lt!s,Y9Qh?\O4bcENn"`Y=e(!'f;:B)g#*Y[ur"cKif;BZ,MaIHhb5R)!88Z6V:`cKL3
B4Nl#n3<j:@E8U]AB"Dg@Q^mK$X+JQ[qi@L,Fda>^R0EfV%:%2Vm6>[J3^EA1">6Sq!h%'E6X
k5Z/3aeTi/A!i7qIM^)0cleFm_lT;WkGK35m1dW0L-,EZN0b'l?.q[.-1TRbYHOn`R8H!DX:
VCgSP#&)rR?@ALXnSIY.hJ5Gtj-N?<JA6k*mIRZYCQGDN_7a8)^q8f<Gh0u>rSWtOgO"MtXc
*`q[:="6G2Kn'@e<ZH)IcQ[PY;lE/a-fUVgA_Q1'V:K98?$-Yp!cTcS5rQmbQS7ZkQFIs%E:
\<)/Kcm4+kcr45K/]A\es`Ok]ANeg'u651NmU;$A/I<XaJ$%r#>adS3VBG^I@M:4X;aDa?gGmh
Hcp384cHM)cS$:o3%r9^BHp[*m>NH[8*(\rSN.Pp(e0fRZJp6S#PEXuh!+f)>$d#CqR*!jR,
G;+c?a/&?g:X\T(*21(W^!KmT$Bo%l'Rr@n_mcOc"g%q!XcJ;*VpUA5uJBIQ6iT+k8$%]Agu.
aGaLOYJaj>,R8qN<Z"5EW8DK23%r!*rLh:14k_$!_L!"_&SmS;3[1j-2of>rp)*I,A7$.,`o
FckHqi;X6s)mS2@@TgR[_6<m?3]Ab4o_3hdH16:eaHWtf:6TE_GB\PJ>R2JA:7VNX4.Y4VEIO
!8eDM\_Y"i!3dheQIA%0#KCER%<N?]A5\MQ+5b=3^KSWQI>+_hHC_Eh$SiKm3r88-%huk+Dq9
Tal,>3p?_G(J?DQ#>Ojh$Mp-rp1#"DN6csT5X-S1kg9XMP`>"-O)TusmZ`s)U(A4lYMbOE/O
m2VaR>YBet^O/B>n0t$tUfDEf(J4>5Xm;HZ'N*ASdQ/$e)qG1tum,?[sY!Q0\!#*jn!;OXt*
)VEYJ#/D9gmif#=GpRaj*:0go@geFe7W*epTm#4'L7,g)&b[lh$Pa*2YSqb$]A!]AL-@2kY'1W
PJ\#VM,E0\0c^<Ml5%P:1[b#4GfkWHN,EcP@TY1>CrC2<sg?-27)et<FLdCn`7`aef;a"q=M
\'TDWMWj(G.kO("=Bd7^'b5Q3Rcg0ZgLo4'OJ@qI78qLD)MoT[jpAF8q7^nAkoU'pntRsrJ,
i35Ps)([c,1oFMY!V0S?BUIrD=lD9FF2aWqUlPE(#sU$h-%53mngo3R:NK-;M>mL:SQ1dl1s
bt,Q4^Z(i8N2&Gf)kPG-$3`$upKSI:Y0BqHQ&68PR8]AdY!a2`e>V-cn)(8'%ZMoRb['[=[Q(
Ka5f7)!rMbr9(DFLMCJ`#91lI^3&&d#8>?Xn^L>40]ALZ&u:2E!55LD92i\KjW/_=HaWZn!3-
j;,d%lH@+?*Vt<(A]A$'(UuEai37h,S6C="k$(2fa61dC.Jn!>s!I>2kqG[G?Fclj]AdMXXGo+
dQYKj5tf:iOo+SL94iJ?m1?[f$Gb%W,re@mt"jW<GlH*er5ZZ,a2,?qY8%fJ79V8i%NjkQ'd
U3sm7_lY<H5.J:H[GjljK(H6pmRbs]AE.2#Ic^(MrNjN_?),2rcc8G0<,qdq3r3'd?Dk+6*TF
S)8Ym2q!c/65bm)E84q)S>4f):'qjJa@Eh)`Z%ppQdWK:nXs[c+6LZYr#H)#mcE]A!'A.8iZQ
u*c+!p]ARJ]A3(Iri?]AiQc?mW^p/mYTN<'j.4lnP%-:ZbDpsXS)$,cSa]AH(#7L9Df'r$PRm>\)
S&j>89IV&3a7IICpFm@DUiJh>shL-fD5._+nVT:ejuhbJqY7KN'4Vr:s@BuZGH4p.\!ie-27
3=HeAV`A/[<gX^/PX">UL^96,ug<=>qm?)B+PYot1)RN&P?lOo\6SDuCQZ2`&Vi2h35Y/N#Y
O.,W'/!+ZJq(%u*47[;Yq7gSVh*o#7p[rHj*rXP3ps8hA_r%SCkG+gP#6~
]]></IM>
<ReportFitAttr fitStateInPC="2" fitFont="false"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="38" width="960" height="232"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   产品销售明细"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="38"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="title"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   产品销售明细"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="80"/>
<border style="1" color="-2368549"/>
</title>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-2368549" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   产品销售明细\"]]></O>
<FRFont name="微软雅黑" style="0" size="80"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-2752555"/>
<Alpha alpha="0.5"/>
</Border>
<Background name="ColorBackground" color="-2752555"/>
<FormElementCase>
<ReportPageAttr>
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[723900,723900,952500,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2590800,2743200,2743200,2743200,2743200,2057400,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" rs="2" s="0">
<O>
<![CDATA[年份]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="1" r="0" rs="2" s="0">
<O>
<![CDATA[地区]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="2" r="0" cs="4" s="1">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="类别名称"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<SelectCount count="4"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="1"/>
</C>
<C c="2" r="1" s="2">
<O>
<![CDATA[营业额]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="3">
<O>
<![CDATA[利润]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="5" r="1" s="4">
<O>
<![CDATA[排名]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="2" s="5">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="订单_订购日期"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="6">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="订单_货主城市"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="营业额"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[订单明细_产品ID]]></CNAME>
<Compare op="0">
<SimpleDSColumn dsName="ds2" columnName="产品id"/>
</Compare>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="3" r="2" s="8">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="成本"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[订单明细_产品ID]]></CNAME>
<Compare op="0">
<SimpleDSColumn dsName="ds2" columnName="产品id"/>
</Compare>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ColWidthHighlightAction"/>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="2" s="9">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=C3 - D3]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="5" r="2" s="10">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=count(E3[!0;!0]A{A3 = $A3 && E3 > $E3 && C1 = $C1}) + 1]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="88"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-4144960"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-4144960"/>
<Left style="2" color="-1"/>
<Right style="2" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-4144960"/>
<Bottom style="1" color="-4144960"/>
<Left style="2" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-4144960"/>
<Bottom style="1" color="-4144960"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-4144960"/>
<Bottom style="1" color="-4144960"/>
<Right style="2" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="8">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[¤#,##0.00]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
<Left style="2" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[¤#,##0.00]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="8">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[¤#,##0.00]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-236032"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="3" color="-2237477"/>
<Right style="2" color="-1"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=@.;chQ$aZSK`/n8NT"h>nCoJWhEU-O66.NK1@d-=]A7je`b2'tFJk63o<rO`n)WUogU281
@PR"_'d9(_Z26`AEiY5R@HX7"QS:,S4u>2tEMi=Sk3.?cW!-dm*EmLRb^F?@/l`hY1;kLC`9
5^j@_@\p2&C"lAPNYA[aO!/=a_@,]Ap1*oJF7\#a3o=:du0p\J\*F.cDUlcMh_?2?*nTh9l\7
e$PLJ)^1HDu4T=Kt0imqG7*LLdp"riT]AQuqRb\2^KO10^o,@0B]A%o5N^!2b\YNpSG15S_MfG
aa7r4hYc*Z%>TK=2i2&*aEE>mr]A"prg7`GqI[H^/@0_K/VVD/lb,#!6Ypr$*?VN=?+W<:7'2
;%>-i`]A4Q9m`*/De[ISY9qqGDLq^SE/1(5?Dd17sJS(r)+`_r"==jUWN),EDItuM-@=QqfH?
1SV-@0*QYA:ice7s)q+/,-XgS&Z$GqXY+_U="+RH,,#7Vg*QC"d"d44'11EmW%=[^J.#`a4=
f(q363Nr[1>>B:FT.R<S=;%gu08^DM;I?@.NN6Hp.2:%698?.<O:S`XMl2dA1((_h4)Pt)!+
)8[7nNg_!2EeA]Ap-)>[f(4JN3P-%\Y-jQP@,U6646gcV.pZRCBTnmMh$`HaMFlME/uKKQ426
?aeN;:`S.OrK(H]AckF#J/%*7E%d9WYMf>@:6D<O&Ef=;RAm`FL/DofLXUo^k2s_+`0>`ieSk
,71+\Wge0OJc[db8t\0Y&;K(?%eq1B6%3hO%SfXTd[PsbRZfq$=Xt`5[hG]Ao%]A@\r-6co^`A
j#tiHGk7)H6(G.Vmf(@M?/1#F'^;W+mAmT%uXHC>pmk+_;O6OaMfR9Lh*#+[C*)ZP=/.;PYB
(&p4>&<<i>[PB,!gF7MPqVU>h%bt77EgN_Un2PuZUGVKoJ/FQO-56\EX7!:bRB'QQ#'$u?mq
>Iqk;I`GL4]AG?D6)_d\%D;D*lhJKj"^PQ)FdUh&r_j6:ic:-&o^\$J6\gN\^(t\i2=?F`_X(
]AWYS^J1TB6M$LYG76%0L8>[O[:dr5$M"YqR,CR\,M?HsSj]AE)19@>.tT6AJhm6-,JQm<[6g"
i_5tL(X6b(=u<sCH*6l!Nj>nAK)RPk[]Aau7%)?Z1SL[Z6.6DDm^uIR[S(PQ[+H3OiR&!5pOj
*?R@'H1Z9Cq!?hsD-<RT.%5d-a4kdoA76N&@aUBJ/n0M5GY:\T16OSN=*G)7KujHg7-+T_B^
/mON/fG3Y!g1qfnif<^=Nr0_MG3Q4i1[34ma8_*(DSiTkIJdjQ2VB]AlTe6a(:BX;p\@JX=WZ
9?c=eB]Aj.E(r!\)^tf'&gY_=hT-p;hgS"$!0/daO("d3L5S!H5+sg*@*.<al;=@kJdjQt96c
2Z0S-[iQm5^6&t)5g*4(KC3\c5sD`qU+g@7X"eAn-q8Ns'&4.O=rWq[L_W%td.Q@,O(jh'L>
jnZm&E6[m>ApF3c$!C/rhKnQ9Y,ONC%CirIEN9IjQf<(X&c@@DMd27%*>Z8MR68hn0ru:g7X
[%s;5eVsg;LO`N[V38fo+LCpFRYA!Nak:#M*tk6PU1spm`e0NF1="g.uQE@KsF\@p%MgQ]AXF
RW-E:!#>T4,NPDj8.L?@jUcKE7PSIc^Ph;1FC)`%P=)hL9*Id;46b%P2Ms>R48(hh@)%%6u<
V]A&4]Agt`Xom4A+__f8c!d!ImmkaC1'7+92JAIuVXuR'd7]AE-ED>]AL(_G!ufjm,A?#;B1j6Y7
i0k+"Z0YA2o:ImW3F:Va@=i!QMI=]A`:U9N0a##%YS=(!S^cG.r\lF=D3UdJUH16V'EJNY=(0
o@f2ADrJ$:g8mOr/C-#!NRpd"G_oV&ai1MSN%W<FXQ@ms(9%0_\cN/HHE^EhDK"-)$(GKQ6_
RZ8StsV,d1(uZHE]A;p<Sb)`b]A?S)HS_Yj8GY1o_-C/2lWs;S#YJKe$nX\`=+,)=mRiA3,3%9
:0%WeJ`(79#$?`O,46Z8iR?D#c#i%FbB(k0.kH&&$;K>F[jJZqH+6e[C]A'bsAGIHt2k)Dj9Z
RJ0-S(r2IN4&Uf1MXteff7=eGU"*&AQ,kj8@)+'cSs<+D=R"sdiRUW>I\84A4JabRD/&)7k^
6&P_N/QO3m,3K(iXRHiJ&q,Ob3J\\4$N(&baJ(ib$F\LqGKU)21jpTNtHE+T6P_T8nrS;Zsn
3-XeB!(*[&5&'UiLO-7hi(XB*7bE^Lhs5,K-r\7,;PJ(X&6QV>CjegToG0C9F.6+//Y1NO<=
:GqIQqlO9#V2%FEOd_MF-Pi2EkB(/JZ9URui+n1]ANtfSV%`q:<##P.FF<9enb*1=!aN!-&FF
`PP8!&n7#FU.0r'9<dnITFiTD,E3,4sf+$!lV2qbr;UPYEI2I%i<a.^m0t*X@cfIZg057C_$
t@?amuBtT6A]A&e/XZBr9.f4,>LAJWU<T#Ads`r+9IS<s`nl9Gac`@RF,;f8gT3iaYKTA,*4A
d^`h:nUd:k+S)@]Ai71QLcZPW<dMjG-u^rbX2heh^,kOe!C=3DB'2RdJaLDbeg\aB6Qk>Q4Hd
4WI%5E;q@Vl(S1iOjhC+="eJJ]Ah'K6Ro6&q0+(FoJ1.1!AIJf`@e=JA\+9Cd4AAa/^EdcI*m
+Kd.VMP\s*BX6!OaWE.dcB)f85lu'\CH*3@Xl!6smAu(aF!DFmPi)60D_$A.,Xt"D+,\-oHO
JTuU^]AppZhq`Yb7M3u8rY'#Ls,0HGuD-'`h2R(^Ah1F8YI32Z)m]AjEk!7e7qYiff=X+3X@<M
IbM!9cpKaE%n$]A$X:;2B52#k(dO\<T9T^$`([G%:NL3:+$(#>$4o=@jOo9aKJ3WhB'!pFf.e
6f?nRD("V?9<f%T.)<Pb1dT9Ac.3qJ_$SGJsc1Q$>#0(2SQ$p*N%&o;5MRg[,En!tY'L%($K
@J(->hr)=sk/mq9YM4CnV?ZW.m+:`\`Y@7Wf=d99O_,,)/!aD)(FtSa_o.`qcH?=\E7t!?5?
:Mpe]Aq`0R4%=&\`(/@bWa*/)V1usN%+/7RsE#%;O'_M#K-DLIs%]Al^/\Esaa!lYW"]AbCLM@H
%Nd4pX+29PH=Xl==mL6i5N[9;)_k\'k1>ueYHOqU2[)2X5\ZNGQ\uk9^Um16KTsQkJ1NInPk
7:tG]Als,h=;W$I`MP/s:G@rlI)_NjV^ncgHmYTN>s:mn5b?]A%Q./@ZoCeS>Y7M#-Nb2)<"up
EVZfLOiQ;Hk[>m('WJ`ZkdT@;8u'Mg93@l:HW/3b2EjS"j]A=NEKe;qHT<%*gMHk.ujJ*-!It
Du2f3@j&`LdJim8;tfoFGAC_K]AirO>")e9mL[2K&1\TQToordO/NeDr]AnE!6?Q?:.eN#G2^l
"Uf4Y%++XZC8IG*bHR($1tR^gaU@)u)6LmS4Z?)t#Q:n)S`9m:AG&Kp9*A1;m'W/!UR=B2?%
A*u)&266E^`NII8'K%WW=7\$uG,?;QTcQU5\V1^"a[R@%,>e#MS&Y>1?CmDI1C@XV#?>CG!k
sa.N3uUgc1#J`mm"L\1*u^Iq#i=Iq:g7UYDG'.Q?jM<<%+DhR5-&_"C/ma)I@u.UQ,<n,CQL
g$V^q`LcI9Nj*PpJ8iZ$GtB@@d-H*GeLb?*#YBkm-F-'c/NlRAh)*+]AP%7,8bAhHHS$<h;kl
/ntV`p6kHO.#Jc)3mSo<0n]A2Ar1/9aHLFh&1VjBI_!.--ofkROW;o8bX>k6U&P"U(EOS$I25
-gjYXr0U8DRkqC+W7>eS;Nr5'YE/`op8)WH-NgCF0*?0r'b#X1<s#GTkbiJ'o)/qkEc&0N!D
IB60b&5XOu.)P5[@r=n,[_bcFU1kQLq2VlU&KP]Areq64fp``s/<XO^BmcX2k+eCE980s[B$E
:Q.o!EsQH,aKt`p?k=b=78Wn`EpK[!9jcIhu)On4\Wd%?YQ[-7dD^5)QJ<*ScQsb;(\#fAd8
ndA_LmdL_96uo[iO\)eh#[_$)c$bjB`h-i36>3k2:-E;NNiOg+Rs'\T5':sP:T1au)LHQgWE
36B.SM$>r.)<eiJOkRdtaLrF)(pk#u;,PQ,jHJOb,,.WE^EmTiA2T`(DKO%.@g`gCQtnieB9
m[TY2Mq=8RR]A$\qV#7]A/(_33=&sS4=C"%,4"6l$fXU"nDo8O)ZpB?2"Coang.Ej9I)<-QGB?
WK3=1"LI#M4au&]A\[FqG$_OUR[2j-=7#%)*GSq=.@Q:*mD<?QMUTOHkFl05SNZUpTo2Lkq\,
M)YqQM[=ZKsFnO9Ys\"`O7[g8_oW11E1Lbe%6YRWupW*J158Vc<?<*[2Y"S*Y$@k)^6/m>sl
n@9f>u]A2jV<XnXI(:189S6)7-^g*d2O$,dWL,V6dmPh_"^".EO.N.#*b7_I;V@ALJ,,gJ7nY
U'9NBA3L6:/kG/G$?f+UGlrHW3o/Ih1L@oUCs]A/n1[<V,HoS>`:Z\,)@HE(>KR<OHQ>fk\lV
#tmPCY?\h>b@JhH#Llo&19"m:=r$$/[D^ZW]A.JA+B-Y.h8+CC8BWuiA;E2T(Vp2$c.:C6(Qm
A07s]Au+'Wd+<rY+k7h*FiQ)SOnV&;amf-^o)<4Gna(B0f^5lVD0[dAe[-`V,<-%lV1e'SUYO
\0FB=^l=;:iCA$a7@Ka^DZ=lL14I/`KV9=WuXr8o/hU#J%L%8mt5FNeI@oM4oR##mB\+T7Al
!LTt[P;:aIFUQ/BAUl24Q*b=g9Bc5tsjZXFUjjr'#uKA12kQ*efEO0r;.jj(d`+8:O\0MBjV
Ol,dnD8dOd\Z'sC+30e:2%!M\Z7In/>l.JJ_<Ip0A$=R)d?%Rmk9-b>?]AO7_ff6m_]A'$:C7o
6Uql&)f"AOZbq*aLN_HYgf#hhcWG^XjE`LT`!Q0!r(3eB1FdcGk$1Xh(,3C@:(,:_Vk@Oq-D
K$s[jb7eZ1hB(CXb`#8?BlmKH-&kgI,ccb)A'U`d*\tA=_j@iC#5NbY;<:k%OJY*oTLnOU>i
]AY!XU(JMu=MCe4CiRbG<N+*ogMZ#Sm6k)ola$+]A/k;62A^<*ER]A`U9/XMZg-/+`tS<<4?Cbb
-7:$SRCAaM/8PM*p@C)b^]A\4KCI&@AnMjebJ-$-`b!+;JJ4b>gPVD3=lh(8@c)"O#Cd::gpH
4tcuZ/mUEk,!T//C'&ZTrPlGT*p3PD_?jX:2q.?H5Dp;9)g$>NA!sojI`-[eB$eP6#eeZ3C*
:%-E<.CIECcQEf6'qhQbK$NhiFHT/;b@bnO)\3De.-jk61<bYTA$QeeaaUS/9i$4B[pBZda?
8+Sj_DOqB+B"]AVdl_&-c1hoTc[(UqSj^dLK,ojP/3%8IQuHcmR0,Y[hSVF3SgOYf,8_q#"e'
?]A@<hWn3do#M*.0l(IF3"elQLTOmMW/I3F!(&$i%]ABI5^230NYEbtJW^)1C8NspC5b,Xl!^S
6^X*g!pmL77'kFP^Rdp%R@YDADORK*8f-S*S_]As&UQXro;$-5s*8@S<8[!f*OTGll1F`g2`,
QQ-aB(=S08*eH5>#:g6VZ^6,ZFZMUSdf[R@?!K6OD._SY4u+$,@rl]ACj^uo=/TiE&QlYM"@%
CGP<7>(11ms$7DKe&YkS`-9\&aIZ9(4"&mu:=G1t>i)LJcE_gu9Q9=1GBr.d^+)"%dI'5u7M
RnQ^N22DF?Pch9?+B7DN7mgI+JS27TtrkY#oXV^P=pO*?kL3d$mq$cp>R5=T[C"%o3gp[^d$
XFKLk[$r;M3cF?e,nF$2LLoP[r!juoNke8PgK:@e>/c(DY&>&K2qD3VLTlFP.tZ6Zb@2>\&]A
?>7[`?+f)$)!ge5Qtj7La_\[PB.'CQ7:G29?LYo/s6TFLRARcqc6WM<+\:-gHh>IDc8dj@dW
6/("sStG*_e0;%FqS(IlM:l?Uq%r2VEZ&Tm;`8*f&QpcDI<YSY%+fF$3OP']AD[hpaId-A%f?
3NiedlAb==iTI+GqWlT<m`_rE7-1ngDMubM:)JkGB#KbT(sDgFq2V869!qb(erA+Wt2[gLl$
1l>L`7O6ln=bB@CkgN.6rDA9<"O=)1R101^h+[,a*,L=nq.Oq`7qRak+?cg._#`uiqZhh0j1
:X=F\=;DJ^NS_UCu#Ej=C8M_SNZE!km#$KKD&0rZ&:8&6H!erN3f?#_uf,D0G0X->C6l3!K4
59?_Nm0`(fOJ;8(c'_5"1"G&+q1K[eN$+YX9oTRGsV<6f#!MN1!6V0gcF?')$+ac'GLb`=I-
o2tC(kF*d>rJT`7N/ip=1D&=$+\`dX$e.U-nk51\7e.d,;KTSgg+AR5^X4JHq7(GB'?GVJa@
6?8d0?^sLTcbTaYK=2L\,\,8<=e0Ar(G$7j'%]A.0ASn(2/Ksae@=l>l3HgJ_rs%&P<bdDRGF
j,C7m+hp.qgX(k6dOUtXtjbEXAZH('<R[Vo/*m_n>j%*:nj6(cpo&Df8f\X[ZB\/s<haMA%5
LtI.C]A6#cW??Q8j=V@`%dF2c4r]As(rqE\a5nfMV^3ZE>lZE7?`un<iIuip,oC7]Al1(P.ibs!
!\`=.D^X?n^<+1&^;+>,2k)o;CW19Vpa^K9\ofBoX0J[7Z.#M2;F'Y\b[nIi*23ona)&cge6
q)27I6,n>-Q;^WMd(+M;#6V+kp/n<6f,GSK*_m-A,^HV1OIMK.R?[e-NIlQa<!%.rmf,+;'E
_W4\S_M9^PaL4KT,hR=20+h9LOFO%+fu?qS+(U"2T+#d+WO&jWp9j#VEG\c'U5sdh\c1mQ=h
+m5B-C]ATMTX1Xh@o`a\joF;J/:`;XYe?"I3u7`51J=gQjOj=#n-?mTk*g07al@dp9X9a4:U#
OQ>Xl/tM?fU2<3nJ?eNOk8n29hgLR*nY:]A%QIhY-OonA+"3Os#u=J!5>$@0TG'hI/q%:bOns
+TT!>IFre,=U;QKGG6F.:Og@_BSZs0!_MA=K`9ff!K1(ZPPjCUAG/T*P+p-\:-SF`NFHb(T)
rRVCqT5]A,G7m#Gbm[RRskKue[0>*2.L9Af^+Y/rQOCH^=j"#6W,$?B88Zh\UZ6.?J)?=$e%4
m(ImO]AQ'1=-JY4jid!KnecD#7uK\]A5.[);+(6dFN$kniP,;E\9,+tIdglFi"t/q.)lhX<eIm
kN/9se-,[9)5R;J$kXi\Z@8XCR[c&P]A\`AH$6c(jUJj;cdg+E/4=$R:k+np,:815:cV2R/_A
?/M)#L!%4$PX0=H,E_=?+@9=jZQp.GDjBMDVU*QU%b6EK;om`E`96fIA-;<Z4D8Hlatf7K(1
CAK%=3b8'2mbBEB&3g7?F#OQs+.V[\EiDn46Z*E!pX`7Mc1YQ#PbcEcBrGe(@s",5T32]A4`E
i6%+X^MRWU4DD,$8nfH<]AcKmH?Q\rK%7%L"[Ml7+fA%Ue7%-X4V4`o,T-/?EjWL<TH6_4-2V
J/^n",#O="\BhG'*oCQC@]AO.'dEu:ApC9rR\L_o(Hl9^5>hMY,W[m,O8a1Y"N!1)5Ls0@Lg;
7Nd&r8^B0'^1)h&fDQ4#kSr)QH8o@AJM_4M!(mo!UIi/FJQ:dc;^jd1%6+fK'.0*Q)H[s81i
+gn.g-prR[S5*Idr7hjUWZBlB_uPR\#kn"FdfX,h"L5&n*A$LpBOt)E6AE":F`t`X6d37(Td
iJ&EC)>Vpm>ArKg"TO00map4XkE;\J&dj):C-#=f"&2]A8CHh&'\BQUN%ADPQ-T`a&iaZpkVg
'=+Uj+4:lYmRkp!!c:5G4ES^A6im:m=UuuIHV9`6aDHY"_<T]A61+;j(F[2\O1,r^`rV%p0,2
fT'oQ/J%E)D0q,Nq]A-1mFZZ34_U+eIs&2-+F<dXe=HTG6*YU9]ADM`-cQ)&I:?i>`-2CYA9Q,
4"lR_<IjD\`\7WeYrsJ~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="270" width="960" height="270"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="http://localhost:8075/webroot/decision/view/form?viewlet=demo%252Fanalytics%252Fsales%252F%25E5%25A4%259A%25E7%25BB%25B4%25E5%25BA%25A6%25E8%2590%25A5%25E6%2594%25B6%25E5%2588%2586%25E6%259E%2590.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[总体概况]]></Text>
<initial>
<Background name="ColorBackground" color="-3219465"/>
</initial>
<over>
<Background name="ColorBackground" color="-12279553"/>
</over>
<click>
<Background name="ColorBackground" color="-12279553"/>
</click>
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="126" y="0" width="140" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="http://localhost:8075/webroot/decision/view/form?viewlet=demo%252Fanalytics%252Fsales%252F%25E6%258C%2589%25E5%2588%2586%25E5%2585%25AC%25E5%258F%25B8%25E7%25BB%259F%25E8%25AE%25A1%25E8%2590%25A5%25E6%2594%25B6.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[按分公司统计]]></Text>
<initial>
<Background name="ColorBackground" color="-3219465"/>
</initial>
<over>
<Background name="ColorBackground" color="-12279553"/>
</over>
<click>
<Background name="ImageBackground" layout="0">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n.'5/B,F0,eoU#*Wf5GSh?$H'm&5<0]AZeA?oVP%Z\U9#K8JJ4`O
#TJp[OX.0@kTR?6E`VnY,/S1V:@\DT6g"4_;Y3]A9@Zh!_m7qFS0]AXkEO<r!!~
]]></IM>
</FineImage>
</Background>
</click>
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="692" y="0" width="141" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="http://localhost:8075/webroot/decision/view/form?viewlet=demo%252Fanalytics%252Fsales%252F%25E6%258C%2589%25E6%2597%25B6%25E9%2597%25B4%25E7%25BB%259F%25E8%25AE%25A1%25E8%2590%25A5%25E6%2594%25B6.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[按时间统计]]></Text>
<initial>
<Background name="ColorBackground" color="-3219465"/>
</initial>
<over>
<Background name="ColorBackground" color="-12279553"/>
</over>
<click>
<Background name="ColorBackground" color="-12279553"/>
</click>
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="266" y="0" width="148" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="http://localhost:8075/webroot/decision/view/form?viewlet=demo%252Fanalytics%252Fsales%252F%25E6%258C%2589%25E5%259C%25B0%25E5%258C%25BA%25E7%25BB%259F%25E8%25AE%25A1%25E8%2590%25A5%25E6%2594%25B6.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[按地区统计]]></Text>
<initial>
<Background name="ColorBackground" color="-3219465"/>
</initial>
<over>
<Background name="ColorBackground" color="-12279553"/>
</over>
<click>
<Background name="ImageBackground" layout="0">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n.'5/B,F0,eoU#*Wf5GSh?$H'm&5<0]AZeA?oVP%Z\U9#K8JJ4`O
#TJp[OX.0@kTR?6E`VnY,/S1V:@\DT6g"4_;Y3]A9@Zh!_m7qFS0]AXkEO<r!!~
]]></IM>
</FineImage>
</Background>
</click>
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="555" y="0" width="137" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.location="http://localhost:8075/webroot/decision/view/form?viewlet=demo%252Fanalytics%252Fsales%252F%25E6%258C%2589%25E4%25BA%25A7%25E5%2593%2581%25E7%25BB%259F%25E8%25AE%25A1%25E8%2590%25A5%25E6%2594%25B6.frm";]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button4"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[按产品统计]]></Text>
<initial>
<Background name="ColorBackground" color="-12279553"/>
</initial>
<over>
<Background name="ColorBackground" color="-12279553"/>
</over>
<click>
<Background name="ColorBackground" color="-12279553"/>
</click>
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="414" y="0" width="141" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="label0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue/>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="126" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="label1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue/>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="833" y="0" width="127" height="32"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="label0"/>
<Widget widgetName="button0"/>
<Widget widgetName="button2"/>
<Widget widgetName="button4"/>
<Widget widgetName="button3"/>
<Widget widgetName="button1"/>
<Widget widgetName="label1"/>
<Widget widgetName="chart0"/>
<Widget widgetName="chart1"/>
<Widget widgetName="report0"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="7c1ce39d-5103-4f36-9ff7-c2adaea0f52f"/>
</TemplateIdAttMark>
</Form>
