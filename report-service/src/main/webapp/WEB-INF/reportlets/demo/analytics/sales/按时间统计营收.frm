<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O>
<![CDATA[月份]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select ${type} as 类型,sum(营业额1) as 营业额,sum(成本1) as 成本 from
(SELECT
     date(订单.订购日期) AS 订购日期,
     strftime('%Y',订单.订购日期) AS 年份,
    strftime('%m',订单.订购日期) AS 月份, 
     sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣)) as 营业额1,
     sum(订单明细.进价*订单明细.数量) as 成本1
FROM
     S订单 订单 INNER JOIN S订单明细 订单明细 ON 订单.订单ID = 订单明细.订单ID
where strftime('%Y',订单.订购日期) in ('2011','2012')
group by strftime('%Y',订单.订购日期),date(订单.订购日期),strftime('%m',订单.订购日期)) a
group by ${type}
]]></Query>
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
<![CDATA[=\"   营业额&利润趋势变化图\"]]></O>
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
<Plot class="com.fr.plugin.chart.column.VanChartColumnPlot">
<VanChartPlotVersion version="20170715"/>
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
<Attr enable="true"/>
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
<Attr lineStyle="1" isRoundBorder="false" roundRadius="3"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="linear" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
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
<Attr position="1" visible="true"/>
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="false"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-12279553"/>
<OColor colvalue="-13320285"/>
<OColor colvalue="-4363512"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
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
<VanChartRectanglePlotAttr vanChartPlotType="normal" isDefaultIntervalBackground="true"/>
<XAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartAxis">
<Title>
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
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-854793"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</XAxisList>
<YAxisList>
<VanChartAxis class="com.fr.plugin.chart.attr.axis.VanChartValueAxis">
<Title>
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
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-854793" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=40000" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="30.0" fixedWidth="true" columnWidth="20" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="类型"/>
<ChartSummaryColumn name="营业额" function="com.fr.data.util.function.NoneFunction" customName="营业额"/>
<ChartSummaryColumn name="成本" function="com.fr.data.util.function.NoneFunction" customName="成本"/>
</MoreNameCDDefinition>
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
<BoundsAttr x="0" y="38" width="960" height="208"/>
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
<![CDATA[="   营业额&利润趋势变化图"]]></Attributes>
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
<BoundsAttr x="0" y="58" width="960" height="246"/>
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
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   销售效果时间明细\"]]></O>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="0.5"/>
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
<![CDATA[1028700,1028700,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2171700,5562600,5181600,5295900,5829300,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[序号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[日期]]></O>
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
<C c="0" r="1" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=&B2]]></Attributes>
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
<Scope val="1"/>
<Background name="ColorBackground" color="-1641987"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" leftParentDefault="false" left="B2"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="类型"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0"/>
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
<Expand/>
</C>
<C c="4" r="1" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=C2 - D2]]></Attributes>
</O>
<PrivilegeControl/>
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
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1"/>
<Bottom style="1" color="-1"/>
<Left style="1" color="-1"/>
<Right style="1" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="16">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00]]></Format>
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1"/>
<Bottom style="1" color="-1"/>
<Left style="1" color="-1"/>
<Right style="1" color="-1"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<a+\;qJTE7m4o$.V\3KCB\0_D,(V*8elq'BkppfX^DBkb1%1Q<?r0n?kTbDNru;STV[6488
9/A;'M`f.7?fP,*4BG5Th=EJ5$:pKFfp+ADc<q4Y=;?nc!i1D1DD;cP+mdlW_:(^#/^8C[J=
W"9m(qk/Cu'S=F2mI.-H`S=HGV:/7!r5P6tU?NQ7CLH7GDe@bA>2a?!8)=/&L7!Bn0r3GVJ\
V$g@hcX=?E,%7=a3Lnqf(Ng5LV:->HkXs"eG^mu5H]A?^anF>YRC';1A)3TKH'>t_k[.JCT.>
PleZN35C*A&W[GfrNhd%FtHfZ$Lp=1qaYb@gU&B#b>jG;-6Gs9Bb9<(Ep>e?,X-u(E\2=b7G
gJW9a3+t@EI'p&(E3_J2;'XZ`nbX!D\[VDr5&2PVjKD</1+dYagBuLXE@u1BMI.NpRo&;Lr/
Ej\*c'V=qWM%RZUlO0V<k#^))Fq+\oKoSYQha7rSeZ:a(!Ci\BM@h9:Y3$AhSU2c:doU>qjN
W=)St5>L_DNXS$,s>51pKdb]AB&5Nqg(DU-ljTsI<P6soD`RSb$>*,/2r+WW(mQ\g`ULn>17c
1)Zuk#rRHaLA*>E9.Wk1G[QB=r6Ru@1=gVpY0#hT/QC:G[:L]ACJmQ$U;1)8h*i*$bRi$LWl@
*m!UY\G\$q5kYW%_59f+;7]A:^o_HZPC$g_,YfiCPI5hJPLh6+8.p'3SXdlrA+K?;--Pg'b)i
W:>9irtq=?dIN/Aqd`*Xa?eB5,1&3<#D'H.`5>DnX\F3lhIA9:Sp*3@RPZlfZCgi.50$4d=)
<!E2p>jJY?uM40\ti,:EZ:mA$H_:lh#.Ya_Zi)r*u7V@SMl-FoKV5lFJZ8<k$E`T@,F)2MYf
7!"sI%!&53o:bbd`TAJ-aoP0V$3m8t37Z4l$Z_J>-d[ntHQi-t70BdR>&cRs.9&a#JBNYTQ0
G+a_-Me2/!@7eXS&\hf1t@T!4C\)pQW%+Q<tCme50\'PM"3;L:\AR^_fWC@96i?LfWs6iQb?
]AipVL@)#;CP,'qEIQh/[UQ?ONdKV/03T9r9o&0ldUr/2$Bd4VBD]AW9CB04'pd]A_m-90+"/U1
/!^p&A/h#6mk`=*Z^.p&f"qH(%tFr,6lC'bcR\U`:PT=`PgL("9m.Dpa1o[b^4%H;-!tY`<:
;p$j;9o'n34V3Z#m\qCV4041;$HEb$rB#)Yn"C@#7mo=P>R-:g'?OO]A5um0"*&MJ_2@VP=Hn
><s@[?GTLA!S:0<.&)\Df(prA5"5c.a]A)C/jQ=V`5#F]A+uT6gP[LuXWBT=X$jc5jg>DGGa@S
X8U#jRe_be02,O7ION@O_BdNN\HS:d:(b]AkZmg^\^lMM86G-q88^PrU=S27s/s!5FEl+".\*
9Ip2Q.(*%R%`I]A<20KV)Jh^VF$e\*S7WB)`:]AOcZs7\8-9nYYjRn]A'ne.B-W%eH>iGH<r)R3
Id4LtoCnl(0m?-OT_d$8A,.L;I:q.X?/>AU0@oC^*Q[.YP/n&#g&HnFURO?uY/kA.Jalg_Cq
0Tg?>B3MV97J'ka9.d?!iZ.4`TgdV&I1Si>s]A'C:5U._s/5)I$C]AiYLSt*`u#6bZj_:/.:CZ
VIutg/BKd_^@kb?gP,stgA4qUldb1ugE@PGMD/69Ao2s6ZT<oaKLAg78)D7LsCqk9WPB[Ik=
f)IR:SH86'=Q(-9+%EBhkmFH$JH#1/VeKd!A;jY<kGE1JN93aB$IGa=arrtIA#Wl>"LH$&PN
Cf(_W%/rgI2XM<C%c7V,$?2rPH+7@jbBX<lPWXV+@bEh]A1c;_.Isi:l1nFQLdk<HMu9B!sF0
?1"ZMA!H&-XsA./pro]AXi3aDb,5B\T\AB,-3Zfe%^tiIt1)&TH#A;*D_f$KJ>a&p<9,i$+TV
-dTY)aRFlrU/GlC'1'gWsbY-11G"dTi:0UJ:,fWJ0TLC7!TVG)A'S?e1M=X8D@uI4n&df#+d
$,Ai`N6F,qf.CSIsSNaq3n,OA=d"^VSIcuCR7-"AP\OUq%#nMD40e0+WEi&TQ-MY5q(:9)ed
/;6n)#dc\jg,b@0=K:n-"fOS;luI^42gGjidcg5m.@C*;]A5noX?e(3BIYgN1d<qj6W)=(FPa
RmSj:3(]A@PgP9Cb:*!--t+E%7S;*9NkQa"LV-$k/jA[/gI,f!:diJ\d1?3SDt5NVK7q=Ff]AO
6o+em?Op>`dkM(,Pb*'1/X5!5G*lV,cu`Ddj.ugCQR="K;.A=m*"E@JKZS5-HH([)P^&ie/@
iVCJak]An<9p&:r.fND5aig)4j9mMX3X2o['O=TBm\(%7j/r,0N*c;9BRb+2o;6RdV?4Q5\nZ
@pVp9a?G]AG$DkBJX)sc/XLqR!YU/7(Q?dEhc)t`N'*pW3d7H2^;1r$b/f]AaUJTr"((IDm#V1
=I(q_'N#GR+"+..*$pAUk9pF'#%5@=5oZq68hI<[_k/2Wu]Ad!m`ilX8`5-!GD4DX%FF?Vbgo
G65t_lu+psO#^oJ:]AYZ;W8rObD)rhS'5cZRXRKQ435GQSiEmTH>*kOfHg__GWPB@_3B"40)X
Io?<t$OMd\^X,E9o\MdUdrX=GM9W,LVm3'+8piGB@W*iC)EB.bXbB8)jO$Bge<t#U=2]A-mVG
S9nVf7VZi1o-ej12N>f.&%\r=,1OKnKj^aK[MX,?)X>UKOpY-`YNHjFU]A_@bhd4A):U$Pg7+
aPe8#I5iRT%`B7_cei5<*.&bW3iFZ5*Vt7Fp#&[rgm#od[dU+h)VefQm*#gs+E#H#J'4tcX!
O`"CNLI#.a8okkmQa/0mW!:^*DN(nVW@[mYo_P5H?Jj23,9PYEND^X(iT638YH[aQ,<$fiqT
ubN$%DFV!Hn6WTh'3LVYJ:NK,Q*HZ]AT+d?@20q1@tBTHF^]AEB'iW&6UUcO=Kt.lq;?^nP77G
IeAC6?/jB%EECQg+oKu7*ekQLT17PqT/+1&E%N1a;ckt9C=^JZ/YdWp_ctTY#"\GW)Q0r8]AV
XSE&$`MU,>GlMFU(IQlW%CDSs@--c,/FeGlY<Jl3hK*:?[i(N-VNgQDSCj'Qu;(A/\F7bd=3
YPNj*F[:,`k)6bohL%mp>8V*ZH`MUUVr&F8&cU8,W%6j_R1@I>c&F\5ZD'PDCC-:?RI"C,[\
V@u#CkO)1epWPp!Oo*VZgGgDEbZaRni9]AKkWjj>iJhEA!AU+d#i</bBGhEK=2bEqe'>KBH45
H%eE)W\O;.G@W-_b%.;E\Za-iiV&SgZ;>('J$.Mo-SWct:^Mhl!b]A#:n0,O0(O+US:^e_(GH
e)o=9AVnLL'NJ(lK%6E=X-hP^S3on!GgS^.+^q6UYY5M/IPrW%esWQh/,'$!EZ,[e8E(B<^C
Jl]AcZ6S?Kr(ok]ALpXa*D:M]A3p5C>%SM!3;l;mu9X7W)L_[*"$QTOdi/PI`l<0q>/*";"=_XT
XNlmc#[d(0;C/r3u0/>3'\@cb`]A\9tg9+U!R9</X+EY:<RbG#/<lBlk-FU^PoElPn4n5@]A9>
-)"HG%X7h3Lrn(,s0;PFQ_QjAmn.-._V(F(adaVM-W@gJ#9urD+1&p.okVe(TH_.g!D78>0a
bcd4uu\#&L!DHopqTh5&BMmk.8.f<,\QV:W9]A-u#b)B9=m=)>i1iBE"'2URO&lL\nRT_?%XO
[E!V1pbOknB]AUJW.W6Ns"]AGe%5$]A-I"-;J]A]AgI%U[N1h6-]Ae_N`BZ'39<aqWS1ZT&K"L_/Xp
nKV^p1-;/oUV'f*-N:i+7qG^K\$F&@l:6DOaG$5a9jL-jjcG)"/HG_)OG,g,$(uDEbGJ&^%$
AL81c7Dhtkj3CMhUh7#/?U5LS6mPk+&(<7L-4IdR4r]ADR+cX+0^e3S6^<q=LoT6,>&dueQ0R
D+(LMNJt5?Q?gZa*Vc-^YFP6$bU-pm,90C(hrFS+Ko!mLC^BEi'rm!:k_@7H\1W=2:=,.V:j
@U8pHO["d[_<m3%7"#*Dr>Q!B%(As0qbE+S[HEhTotWX:Y?Wo3T^C;(f*3>BH>jGOQM,hqQ&
6.@R$W9P<:af@f<ZdkW&c\CiDC5'2^Y1.PbN1"8tq,)^%Jq-QBOE!XiCp=#.1_amnp206$!U
ZND%6M2*?0Tmrh0K=`*9u0V*U*?DO[=nG<<S@r/2SgcO$LN\WQ=dm26%sdKJ2UiAD=2%r@1B
fH7i]AQE7"4"As9;%R/KM;_@"kEBr<84B_VCo(_KN*6X)Z:Y]Ai%W%>&CbRIrsMDlQpO((O]APE
D\uR</'U,?0eUCrWHYJP"s?-;V$77)$TcB5=H;9>*0e@$7F6Im?CH0?_U;sr#7<VrluKTFp-
LAk.^;+,K>,Ab8YW>[7!@;CK$cqcML2[PohC6>Q[Q0T`GjR`<9V\N:kk,TW\%Cp0j`P`26P+
0,p56$$>CLE$Bu1;"R2I`0>q"l%P_KOA46_;i4F_Y\p\S[B9tg]Abq0p-DoTCaO*ZciH^iU/:
LG*KCScKD5ra!Mq2u!W]A^0Q_$2$G3@iO6_g\TdQ84YR=N\sH'ZU^2s'82Cq0N*Ah?b'e.AKh
"k@?d\!=>W0R`kmHe#Yk7)44>:Esb!?U%F<B>@Z+lRuJdfT(Sk`A&LFQO_b%5KUq#jh[k>*l
P!j,,2^$j]A^Qg/(S<07Wk/KHc>r\iIX6n_DHJ0n&#QJ4Z:@mE7V;CqH?n(R>Nd9cOj;A:=<(
O,*&4QpD.OU1.ZY+30_&;2,ra)6s#*hH8+g,!2X/jgpJeri.kI`i6oY]A2hq6ZQVDTI1h[dKM
R\d5I*q"gk^/\)+I-KR/V1a%IP/Y\`ioVcR/7TWRF)>ok4%g->iJOOCr@/$U&IYdYePWP=`?
k_l/bcPfTZ=cY(=ijQCNr+^5-S5jnR/ZI(`<Y;bW6uaNKTAckQ9UaAGo?kQ6%YI31/h64sG9
1aD=YW)nak!_oBTDXdEt,E\AO:3h#GpL+IO4FSJ$\qMS'Z6Wr,a]AXJA;-9UZu39Ib>D9,@\2
s4a:B$SV^AmMcW!UIrR&RmOlr[p.N?UCFbpfE7S2$G@=@f%f%LcNq7(cr9`<#AO@T0LEXpNF
f/TkX"`W+&^NIbLI_!'J\g\9u4]A%CH_rY7CN0ZAcc+5Ko%+mg(MS_53*ZlZ\N0f>EljQaOYG
,a`+TZ-C1s$8-[B%li#^^aW<hp77C?Z##<s+6TuYm#ctgLqP326t9HsUd2n^#j075bQ84^YV
fV5q)$)W&bK*XZ!R06C;L)'We!hnh&8Hf[o-bFJWk/O]AQ\Y>PSu@Mm&dDE_TbG1\%%6se:a8
0Y#?d`Y&Eob!u:O]AXWr0"BQODf5(21P[rfH!@n2NM5in!PWB87*h1PRuClFJLQFG)8;'DN;c
?P8Db3oimgg%F5j)pHchc0dHHH2tUFNie26>rO9r1[/jB5>4D]AN-YhF\AZ\s5b0PXt7c9nmt
lIppJ3ZM!K"eQEF>&0PnmF]Are3gNuTU?J0r.=f-01.*2<gaJaE<R12MG<mi-Jrmkk!peE:kM
/uM1PWG[8N.(/I$\Hc35-@ER.q@+=9o6V=LY8@pq+7o7(0u)tI^I/c`fL^b72Y!=eB$8Ce8.
RolMej>(8l.]Ag0_;WfpnkJtJjLWjA8:]At^s5:7lkFaH)8$Eu`\-g7raGrFOub7eGK`*eNnn+
XM!/_HV9jGR-Hk*VZ,ZpkS'#PrVr`L9L>1+k6Bh3YEU<A%mUN>'?'hI39S._n[>4LVgiAshQ
%q:G,VEmWdtuh#KDk\d;?"n-4+(NtlSXGuY(Z*2]AZK]Ad/#&HD5jDO(!g(bq)_).9g?9PB6jA
G<Sg[X;G8!cqoh"?B6qti.s73GL5A>)uin:+/j%,s%`1\9a@n`@e&UM7;)snb0ruHHPN#nS?
;14t5nP`FR-M,NT%$8kl#Dbi>?@c6(_+mjlR+a+1d;_&k8@ej;pjamT\#NMe:Z+DH&=CUjlR
&H;(i&.jkVqeOYE\Z_Wh25Heg=PY'pI`I=nbJ54uW.3_!pKO@8XNErqB"\&/%As%_e]A9I1;[
>8A'^V^s,u66_^%TZJ68<""K:>KcY5BBn`=G\+>3bJDQCL3IU:D!]AMC448oG8hf$;CrWEhIh
EVXJpB$j"6c%Vk65di)aN=!-LA%Wc^:[!(jM&GOQMNO7e-sPYn&j84k,\t0DK-T`AsFqjIqh
Aen:lCJh:M"o.ID`R3HJYQ51`r&RLl[MgL35al.CdgLaXb^jM#MJaapJG&[Ebaf^1V2=3lmX
4deNq#7ZfdVk&s)*iQ:SY1UrpOhkWgeHndAI]A=t2#"&jHpX=E!D)7!kmn8?[A(G\/h&QKY1q
5B[$'G)&.-/rC1jZuGN=>>RNJo'p=9Ytg]A?4o+]A/ihiZUuN]AiOgp=De!a5$1K]AkKFAL75=V%
%%]AQL2-2hH?`K$C8dghqlln!Sb>jAjOgr.FB0!^pJjHU,d4s:=0744I]A&Z_(s1a33^W3EagV
qHGbT?7heBg>S!e@LRX[i7LFY9[ni,Y?fp=T0%UmsW#t1'/M3F$Z[:)X9s16Y$Uc)[;H<%*)
(H^J=iDIjlkL%t02L*-csoSobkffY]AKHGoTZt`%Sr--,Njadb2f9nk&cTrpdZtVaU'`(cUVV
_r8<G02(bh$hnc0(LC)p#@9@Y?Ea(_or,J/WV[UA`?V"^3cW.!6L`Yronlm?aU6C;b(iSdOM
&0tej!o/A'`Yc5NC*o*g6QUYJI5<gZ\-Xa\GSuDUpARke2m1)nk6NNV!UN^WT_BQ19;GU]A*q
2Bl"CM.H^S5%NU@iDWfk9+M[E,:@M\Kns+JTa'04lb>15Q3;^G;B>".&\Sh.HePb#5f3D=I"
ZL6.lhYPt+.Hq]A9]ApOrN;gh0:!;6+/J+MD9K9:B\1seLGQ6`sHrE).I%ZF7?8*'PiE-N?<sU
qhmYYQbn*sCr"KUCSXuiR*h`n^!XG=9UC8FWe4*TV$hotT#&aOrFA,dKVT:W:DYLL.]Ao<rS9
hE,brH0!_=OmEVon=U,Y[2_!moHGT_q%F71d<@D[F`&!hF5NI+fH-)9Z7b_Gho8&MB+A8\8P
BuW50nedg)%d.3s:oLB)V`7VLWEU?W.&);A@dHd'+:T?6!NZ$O:3+)Rt3pn]AoUfkre1FKI`K
qi[:mQ6Z8P`b,;dgVe/X_(OlD4M-Z)pdOcn)d@DYVM00>iq/gB>+,:(XorV23/Db\uhgNkb&
H)V@r^W\<<"hjOZ,9,ki@qVJ)65!1HWfiO]AoMWSjs<u)S42Ee+oK8rE)aJm*?:/!iE47Lgp!
<_6\u&X_%XDS)nW1jhW'F$$L]AC*0XZ78ZL7lpm9OOgruJrF)nJ_]Ah9a81"[S<#2RMi]A%3,kj
h(^Vq:<nq,6d75>;>F%d4;nMYS=&r]AJUj5)1CD"I(oR^-SS3oM9FbU-HVGJNiLY)4.k!3%/G
JPX%)'9Q/[Y9$8W$RsHk@$cMq7,u-AJA%$f[`X\W9@-8Zf<Bq>]AHf8"V\/aKZAT<3?/;#b0)
Y*jm+<PJb!1&8JD3$NEZNll(3*d1pj67RD!+=S%N1&'?%44kPh.@i3+An"HQe*33KsRRK.l9
nnNRXqGSpe9&A;ReD(J\"fL*[LDj\+Ce'bFkY]A`9_UaaU/4Q\,2n+6MUo'i'T-U2*QF4Z":=
bR8):GTbX71@5*$ImXa%Wa<-8bsmL"FC7Pm1)+*?H@[dj*N8,)<i`>I8fS(2\?B0D!trm=Gn
Nh*=p@+n#$Mcu`>&^1hB)(/)kDRI04K!U%9^8]A1AGPg2U=VW-FGVUd0<pR\S2)I3DJ!,7E-L
#0+,Cno"qp,RnW;KRDD"CDaG]A#$o!uYeKl?D=aK&"FZF^eX]A@?cK^j=(gJ+P/!0m4m")jefI
P3H3LiQWEf4>4U@sgFGpIg9nbqAJ%s;Adt]AR3cR^/(s72-;c+2>Dh,.=14[q5!Uc6Zd(Fp<U
qu#'jlRh&!Q3HWRA!0S?WB9!D<*-XalFgWr%indOB;StZuaS:ZYcdAU;]Ai_`CF7SJr2Q6Y09
R0.GBFnqum0`(D;V9<aT5$.o0.#IpUZbVM-f?gX.j*9k,DE<.%8<2Gt9Lb$&<,=nMcP>47&t
QDO23#LFS<((!ZVm)8\s;Fg?)6.;=q&H6iPXL0jofMK(uiZjQkd0;#f$.1n*7$gRNf]AYC;+`
$7o%>9LrE);0oh]A<gPm<,,qBOiW>iuTbMe,)j%2PuM3[JgmE.B#SFF6$#*%Ms_'6q`37Ol*5
>K'#ghUDgmsNDr7"l9J[Z:F"WRNDMf8ca>@\0Qtl:/@K:*]A-rQ(0Znh_]A%?U]Ai[3PhiFM]AX/
I-_7VU%_4<\2h5Y5LCBr>13@^:V"uO&8PkEt(YPFJN)EX<%@Q_;,:.4lsa%]A(%(J+$_Y&hVs
s_crY2tO8OLT;.h:.9/u-3LN3&nK_'q?Cqmop+5e>[%`tj1!2.!=+ruRr0^^$Rii\>Zl*[70
*fs\U`c/a^kM-0VbV.!:f5p9>?U3]A8/RupWNZbC#NK`HWShTNi)9;>!^[3Dh>U74.aj76agU
A$q'4H&9I(_8+S<VLh2no.K5i8SeV>Uth+-sAdjC.AhKlHVg5*i\>LR9e"`Q(@a@pCLC:<!X
m[3H&>pQWdo#OU]AH);gjSdMg(,=F8[]An2Nr9L^85JR7c1`PUO`37MUt9^"G7\24tTD0[(Q1c
r#Jtq>p%]A(R4l&Sp--UBpc@:R"e)-*@8GRq+m78T<'(n]AEV*dh5LEN(pRbP^SpW":M%?B1&.
Oqpr=cj$>oZW*R;$?^QPsgogg\acmsk]Ad>*%S.E0b%I&/i:SQBcqA@5-jQ9Mg\LLkc3%IkmC
Rqbo&3*%Yr%K@>n2TKt$VEDDUpR'aZlahF/9&`DgTj:[O9V]Au`D)BdNfV<&J"h@ka[&qQ7<s
PDo!A1H')DuN>FA#g!0rW?/4A[6"TJi.*n9Hj-"_ulX,VB894?<Fi4FlbZ"XI/kW]A<b,-Q:-
n$%2?no99T^k7.gP2:Aa=MoLP[<(acMgSn6DrX$DEd#i\CYE$7"-%Im8i6ok:V+_5X_.+tIB
GrKP?:XUBj=.$L+."cc\`<_hIa.msdX3-[=kM(c;Rf0A0Z*9a3p7H7d.CZbL#S1o?:%]Ah,N"
oPd`/TWJ=C<)`;J*AgIkYf,G54gJm_;&fE%^QR!,]A@+o8F3CMH+735[cr[<gl0TRZVOipHch
9OuG^mli1B.itgcQCF@:X66X[.UGSS(h4B30$ZgaM^gh3\I1nH.NI1V*:'#L%S+F\[;`ndW:
Y:/&-`@BU!\2u,dS8\U01K_Vc\K_W?9<6PoNd-0[3\RVGbEJ0mZs4kDp<u;.Wu.]Aaob4[%_b
o72)!E`#G+XLEbr6o-@j]A><cgBlENgV/A-@4g]AaCVB%CuDZU5Ifl3;PQ*+2p.k!fhVb:+Ee7
ud)?K/Kmf+1A-HkD[`3@u8^&LNY3+;V<@D7%Xk&NbbB&s/6>]Ap5,:(<t87NB\X5//iHfr7`.
VB:M0%8SN"q,9jIIG>tAC'_0A]AOQS[D`i+56R*tr?)QFf;Z$iVd+\E$`@H>d_?ib9<I4Vu(A
n14#g]A-&s5onq#5)LT]A1bVY'9LB]An:`NXT@&EWd]A4.A#d<d]A;Kct+TF^Qn6j]Aj:EaBE.mJ~
]]></IM>
<ReportFitAttr fitStateInPC="2" fitFont="false"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="38" width="960" height="198"/>
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
<![CDATA[="   销售效果时间明细"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<border style="1" color="-723724"/>
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
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="304" width="960" height="236"/>
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
<Background name="ColorBackground" color="-12279553"/>
</click>
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="691" y="0" width="140" height="32"/>
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
<Background name="ColorBackground" color="-12279553"/>
</click>
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="554" y="0" width="137" height="32"/>
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
<BoundsAttr x="414" y="0" width="140" height="32"/>
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
<BoundsAttr x="831" y="0" width="129" height="32"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.RadioGroup">
<WidgetName name="type"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Dictionary class="com.fr.data.impl.CustomDictionary">
<CustomDictAttr>
<Dict key="年份" value="按年"/>
<Dict key="月份" value="按月"/>
<Dict key="订购日期" value="按日期"/>
</CustomDictAttr>
</Dictionary>
<widgetValue>
<O>
<![CDATA[月份]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="0" y="32" width="960" height="26"/>
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
<Widget widgetName="type"/>
<Widget widgetName="chart0"/>
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
<TemplateIdAttMark TemplateId="ee1bcff8-45d5-483c-9a23-d0d07b94f45d"/>
</TemplateIdAttMark>
</Form>
