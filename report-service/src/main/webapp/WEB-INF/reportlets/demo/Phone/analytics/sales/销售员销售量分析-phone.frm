<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds3" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[day]]></O>
</Parameter>
<Parameter>
<Attributes name="p_city"/>
<O>
<![CDATA[北京]]></O>
</Parameter>
<Parameter>
<Attributes name="p_store"/>
<O>
<![CDATA[门店一]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT *,price*sales as salesamount FROM `sale`

where city='${p_city}' and store='${p_store}'
${if(datetype="day", " and time ='2017-01-01'","")}
${if(datetype="week", " and time >= '2017-01-01'and time<='2017-01-05'","")}
${if(datetype="month", " and time >= '2017-01-01'and time<='2017-01-30'","")}
${if(datetype="quarter", " and time >= '2017-01-01'and time<='2017-03-30'","")}
${if(datetype="year", " and time >= '2017-01-01'and time<='2017-12-30'","")}]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[day]]></O>
</Parameter>
<Parameter>
<Attributes name="p_city"/>
<O>
<![CDATA[北京]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT city,store,sum(price*sales) as sum_salesamount FROM `sale`

where city='${p_city}'
${if(datetype="day", " and time ='2017-01-01'","")}
${if(datetype="week", " and time >= '2017-01-01'and time<='2017-01-05'","")}
${if(datetype="month", " and time >= '2017-01-01'and time<='2017-01-30'","")}
${if(datetype="quarter", " and time >= '2017-01-01'and time<='2017-03-30'","")}
${if(datetype="year", " and time >= '2017-01-01'and time<='2017-12-30'","")}

group by city,store
order by sum_salesamount]]></Query>
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
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-15198170"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-15198170"/>
<appFormBodyPadding class="com.fr.base.iofile.attr.FormBodyPaddingAttrMark">
<appFormBodyPadding interval="10">
<Margin top="10" left="10" bottom="10" right="10"/>
</appFormBodyPadding>
</appFormBodyPadding>
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
<border style="0" color="-13882294" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr shadow="true"/>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="3"/>
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
<Attr trendLineName="" trendLineType="exponential" prePeriod="0" afterPeriod="0"/>
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
<Attr position="3" visible="true"/>
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
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
<OColor colvalue="-15296026"/>
<OColor colvalue="-1286587"/>
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
<VanChartRectanglePlotAttr vanChartPlotType="custom" isDefaultIntervalBackground="true"/>
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
<newLineColor lineColor="-11841939"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="96" foreground="-4079159"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-4144960"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="96" foreground="-4079159"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
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
<newLineColor lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="96" foreground="-4079159"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴2" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<ConditionAttrList>
<List index="0">
<ConditionAttr name="堆积和坐标轴1">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrSeriesStackAndAxis">
<AttrSeriesStackAndAxis>
<Attr xAxisIndex="0" yAxisIndex="1" stacked="true" percentStacked="false" stackID="堆积和坐标轴1"/>
</AttrSeriesStackAndAxis>
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
<JoinCondition join="1">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[3]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="30.0" categoryIntervalPercent="30.0" fixedWidth="true" columnWidth="14" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[销量]]></O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=report1~e2]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[销售额]]></O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=report1~f2]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=report1~d2]]></Attributes>
</O>
</Category>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</NormalReportDataDefinition>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="262"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.custom.VanChartCustomPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName="新特性"/>
<isCustomFillStyle isCustomFillStyle="false"/>
<ColorList>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-3881788" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴2" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<VanChartCustomPlotAttr customStyle="column_line"/>
<CustomPlotList>
<VanChartPlot class="com.fr.plugin.chart.column.VanChartColumnPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
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
<Attr shadow="true"/>
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
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
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
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName="新特性"/>
<isCustomFillStyle isCustomFillStyle="false"/>
<ColorList>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="custom" isDefaultIntervalBackground="true"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-3881788" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴2" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<ConditionAttrList>
<List index="0">
<ConditionAttr name="堆积和坐标轴1">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrSeriesStackAndAxis">
<AttrSeriesStackAndAxis>
<Attr xAxisIndex="0" yAxisIndex="0" stacked="false" percentStacked="false" stackID="stackID"/>
</AttrSeriesStackAndAxis>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition"/>
</ConditionAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="false" columnWidth="0" filledWithImage="false" isBar="false"/>
</VanChartPlot>
<VanChartPlot class="com.fr.plugin.chart.line.VanChartLinePlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
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
<Attr shadow="true"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="2" lineStyle="0" nullValueBreak="true"/>
</VanAttrLine>
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
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-10066330"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</DataSheet>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName="新特性"/>
<isCustomFillStyle isCustomFillStyle="false"/>
<ColorList>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartRectanglePlotAttr vanChartPlotType="custom" isDefaultIntervalBackground="true"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-3881788" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴2" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<ConditionAttrList>
<List index="0">
<ConditionAttr name="堆积和坐标轴1">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrSeriesStackAndAxis">
<AttrSeriesStackAndAxis>
<Attr xAxisIndex="0" yAxisIndex="1" stacked="false" percentStacked="false" stackID="stackID"/>
</AttrSeriesStackAndAxis>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition"/>
</ConditionAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
</stackAndAxisCondition>
</VanChartPlot>
</CustomPlotList>
</Plot>
</Chart>
<tools hidden="true" sort="false" export="true" fullScreen="true"/>
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
</body>
</InnerWidget>
<BoundsAttr x="0" y="462" width="370" height="262"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report1"/>
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
<WidgetName name="report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="0" color="-15198170" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[一级部门销售分析]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="2"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
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
<![CDATA[1428750,266700,964800,190500,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[838200,612000,838200,3086100,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="3" s="0">
<O>
<![CDATA[排名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[销售员]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="0" s="0">
<O>
<![CDATA[销售额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" rs="3" s="1">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="salesman"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<SortFormula>
<![CDATA[排名]]></SortFormula>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" order="2">
<SortFormula>
<![CDATA[=F2]]></SortFormula>
</Expand>
</C>
<C c="4" r="1" rs="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="sales"/>
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
<C c="5" r="1" rs="3" s="3">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="salesamount"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex order="2"/>
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
<C c="1" r="2" s="4">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SORT(F2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[B3 = "1"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!=8r'qMA$D7h#eD$31&+%7s)Y;?-[s5QCca5R7Po!!'$5ES^Zq"OK_.5u`*!m97b-<)F?RiM
O$__@/&%\-\i_LeMjJW_L+ZfbaSfUI!;-82::(TP.2\E7*)>,Z-?j!i7*^;9SJ[aUcnNr0l+
3c+M0/n(u[/GON*6nDa`YL7*>Mr6FceHd7'6^64XH*9%Nba?us^2E[Red=*q5<`">:7(bBlU
@p.p>#9a+lfE9:'=D>kXfqg"NZC\;@V\i)jYl1Qgk++.Ni38j$fL([./D(8BT-;VRP;2oVr.
X3XGB;>QsB\>Vn`@EVElDlN1)Si"_rNnm&tRLT`'/@TA!_Hp*U0^fFFgJCM<]AY'.rrCU\/.>
D9\tlHZanf>8nHtB7,Kln/qQEf5SY<ec*38Bnt0>osE6?R'md.8JelP52]A(<J2$@d)?h&HE2
N"RXcL3J:>-6J$/_G3J!fpW#nWgO:>J'%X.MH*'DDP_&1asb@G@Cj"l!HaH"NG@[l=7gVpe%
,T8C9H,5QV<B]Apqn^cA-uVmD-(p^Y't5PQn7nT3m36;5<M0`R>+aTLR_nHZenhE"9p,Q7Uu0
+8D?]A1qq#SuDO235[V`(-[+rf\k[@Jk#Y/_QOtT\(!F'HX+f(GB!%lpn[is^>MojDu^^9%FS
s?OoUA_3;_b<mJL+LfrRm\I7`oSmPXcGS!(X?$TPE!5;2T^+k0O>O.k9O`a83r6O^r@!2oaW
@+>1gaL+CZ2s%99Cj3s#gJ@g@<G,ekVF.L5(puS"!45+ki[XhPZDEA6HOR/B5[adsj7_ZT9)
nJdopq-lq4&m'!k>N_n+_i=H=pHNo0j\ns-XARRZQXR2Zat3U<KS0`@,ss%:$\?6p"K@<RM`
EBQ[G@X:4Rogfu]A-#>R%S3!AFX>[g!.Rf(4tnf9M$!%'@b\kJ<kPVaDnUee5X"OYjhVeL(lp
PM<<E0F.5,!`Km.T?+!M"R>b)SY8(W#6=94oo'^,,s6`J<\t!%jtclc+YOY?M.*a>6&UVOjZ
rf0"AaXWQnG5$6.9Ri`ET0!PhQ]AP`G&8clfjF/Uh"WI)qHj\Sjt<Qs:%=F9H7j>[X>D7"F0i
iY8[I!4[o"dh/6OE.Qg`*ATid3S!r"r4Pf*^f<e#NW<R@$Z&sc&[X+i\;D-P&R\@QJUP/D+o
o-1R*uXT6ZCFWZZcNP<tQD6!atbGY6Y-2$j%FD.<+4'+AWr863*l@aKlG863*m:&C466$3?N
[_E8>r`>4!i@E92"r,K7!!cU:4]A)qJ<L_Nbfjp_pAQ5J)EV[W[B9nrY5Qn(Lii/XiTg(8d]A;
q)PtcF,^A!4K>T>nQiB7Q:Rm*/mG/ju`knXoL1Q'\o0!NNie_3#]At\jZOS0Dase$%S+&4:`P
LY8`fu5!2Y^hO[A:RO)kT"3$*9(QEgnVMGWHfW-T_BS.7nfLaHHpg5i/jL;!H/K3Ob/jRYd<
E,8Ya*UtX8/uK&WaleLWWtZ_OQ_[k=[7*YN:P9):F9")"aVc(mH4@T9?B]AFj'^*al6pOJ.$Y
01CYkXrg)r'eN%j1qb"'Xc_0292_A[-Ri$i-g()[eCf<-oW^G-Rhoc-/E]AhF.o%./WAXAKc'
gjB:7#i@iB)htG-tp5AtO!!#SZ:.26O@"J~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[]]></O>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[B3 = "2"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!E'0)pk_gB7h#eD$31&+%7s)Y;?-[s5QCca5R7Po!!'$5ES^Zq#8p*$5u`*!m93_1<(.XY(O
a3r_@'.o(8<IL(lT&A'I!f[@k8Nc]AL=#R9]ASVO/PlklLmJej1^]ACZ9LqDN`X?5a?qUkZV(I;
4d*g?Z\$XY.]A.TFe]AA<J3[q?N&badM7baHp;KJ3/MVEO,B^[KpFq<,tnC"$tY(-$t>$]A]Am4$
X7Pl-n.,<W<(hOKtH#lF>HD\qDhnYC-^,A_W#qV%4\#f;?J+In%443Jjd>p>6"5_->6=]A#M@
!1q'I)3!>S5d!HuAlHkQu4egVOLhDf0)s+%?(J0nh0;U_;7YW(I8D$@:[M,,".'=C2@d5E+P
;QqA&`,13+q\*DG*H&BBqKK*<Tq=5Am)4*iXn>I.l)LZ/^=H9AGPhpM__nq>pK7:ae_?]A%5O
G]APh0WjTIjr@kX(YsYKQN=rMl5u3%cm1hJ9q)+&Me$T4mtO!Z'mgFU9am2O;#/69MfYW,';O
IpSN3M4hR>KUK=Q((P1A?JQf(d[+h2YQ$.sl3du?.2uBjgUAdMJ?+YsI4sW2d%T30T:>g?o%
KF='\2FRg$T0=1:e]AT8A3d;@\D_>:Hua.KVX]AR44aVu[^J7b5OMuj0Z-^#dpEE*CTJedjR/d
$M:IMX7fX'[gMI3=RAi;D0FRFQOd5fE90')ZqQuN*Bcf*3[q_l6j*FAYS)'pJ8R8)ZJ;<O96
`u_Z3>_lmN34VmR/BbPRPI:*3_VG^,&gsE%ZM:J+7((@n(G5o,TU'6mj5fV@^=o38lg9dN:.
Df6Aar..M5hU+?@4(r$T5nka1/Z<;!..@7&Cu2BfI5n&-'F9/MIu>^!(k@5]A$P[BVkZaL`ZC
@.TIFhe0A8@eqVa-%e8cI)tS?Fm2]AZ<7$i?MC>PnU'-%.*X&\[@kKJe$kSS5\#@!Q2WtRq*O
*drK?APn$G3VN8`Gr0#`J$/#R5PNLS?"?`#"*74]A(ku[)69R%q!0ELM-;ef/r[aZ-QY?cM(a
Bi/p5nRlJbUSi@T$0+1@82'4(NurN,PWo9^GrSHYO.J9-8N"86_Kd=Nj*'^%TjmRjB"MEN4B
Q[(6!kI.[=+i[mhDj+YD,-U^Mc`d,U=A3Z_s"71KoK2i9PF;[bYNEQsH':?';HmjhHJg,LKE
aXFP]A>qsbo/*SOY_[H\FbR1]Au$L5mG$.VJg6=356eK6WQp!j:?:JWngA_J-+/).PT,%cWn.D
E*\F8KB1W/>=/ZcELLZLL8ZE`,0Ycu$E4pR'E0N*#`./Z6'H!.Y_k4o%SNMVUii.1kYcuqYm
5?h:Mc5FG0em&TQQl']A*XSq3rP..o/;PeM<I1_efKJNL%XfiGYg(+:K3Sc69o7ckr(8$GFjK
C8!*:3H9ak^q;Z-3"nD^]Al-:`@HO]Aq]AGi6%h>_uP7%<jshdn_!A;leuQ=?R)Rr>!Eh5nea&8
+O6g^=q[A#`n-#9,P'3"0/q&ZXut\j"sg\m5oaqh3krJjRk2;J!#_CbJ1H\6M#mNEU9b)$M;
sXY%m_Bd-Ib+Hf'+II"X58tKX__UC[?mBqmQ6\a!31]A%=CsN2.G^0M#mNO9@F(Qies>5IP4+
SLe^'Ha'`lJ;/'LT/hTT5G(6<,p)!B]A:ee7khm()eoE&)8]A1`W8bC;h_rAYI(AR^Q"YW]A.=f
rfQfjJ5mlXbd)MUef.okdu-i'Ak<u+#r8-fjS)I3$tK)U45MRDOSk&s2NOZ,\N:UOdCmn?Mh
Sqd9J#!K8/XE#,FuOf)^863b0.04)N6%re&%D7t;fprV>n;TSQ6pP?R'"nNrtnD*/OQHj2=l
1BDCOaJ6h;!LO((p]A5M2ND/AIH0F;('+:FM$s'FZ"?.B7N/,0kK]ASAY7YO3%S-R\ub$bW:a]A
Rt(]AZYu'>hW9fgKH_(Ro&nWWA=NZ@P-SF8$.SC)_do&j70ijEg%DZQoY>bKa5-5[q7nZ"F1N
)rmP7VFHqq`JUosb`MX04_4le\RB<u3ho_R9e<X0aE1C@mJ@#Wh=UagI,b-MPVcnK5$Wh&_#
B$jM&aqJ`&W)8h5js0b6oSf<W.A,-^hHZ=ia!n4@Ng`EeC1#W$*I#G2dWSI!A:FM7(g]Ar`un
NZJhKCZ<E@@Cp4OY,EdGK1!"MZ"<4pD-\Ki5gB94*Qp&`r$8S01FZ6XH9ZbCXBg2lj0hIXQp
/>PbZ!O'53=#[7*T)VSK62Z;s!p=Ls+0NGfPIrNkn2"hB<P090%*kS1NdS,4m0aFp#e?5^!b
s:.[pDjloC0\!.[&\"oT-.sDLW0U;ZTAYFr&De1Gq9)rKWL9k<Q3)nu^(@jQn#B"K-8ULKSl
7A:,EVoJPapH\0qu\aU[tVr72Wc<Pi!!!!!j78?7R6=>B~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[]]></O>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[B3 = "3"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!MB[)pk_gB7h#eD$31&+%7s)Y;?-[s5QCca5R7Po!!'$5ES^Zq#A6Ur5u`*!m944?<)jWe5P
boN+9(bfcpI@8-:VKu\q#Bo\-:ZD>==Gr7f;43bGGCd/cc\E7_fIM'GZU`ikj7JD1T,\Trfn
>BA-7LFug?$[%0sQkNeUgGOKr94n$Xb)+41^PB;*ESF:k"^3e/;mgU4Z"]Aq?>:mAFH:m>=:=
^#7jQ6?D;(?Ma]A$gD]A=i;BiqL(p;+If>:2'?uab[ikn\/\gfo?3S_]APP'@t3@El7g'@E4*P!
Me!<IA"Op?6I]ANc,qJB\`8d'@WWq#:A'?O]A#P"W4l]A$M.JC:"h6f0!:?Z']A:_rLWIHB!%inM
1NQ:G0,'U8*PRiW"kV5FcXp%pY=kgpC`RZMDl92(9'U8o+#NnS^Ys[P#1m=C:U;]A-Ilc",LV
YoeT_8g`'PZ>o"+<>n(9tTpi)oc)Y;?BDQTlJk&*:$@fe(]AR?L0-*A:Q?.KPqAFaNL1c2rua
'GW^\hXJ/hc"lBsE!T/]Aa=AiJ.bF]A893Q*]A?5M)KqI:"ALaj!4JCP`td+f]AgIW:ChLn2(&us
5=l$Rf\D"QpXW+Qh"bpOK'9Q\;nl%q*$gfa9eU'@\WgBLiuI&]A2`MXXYCY#+%_"8i/lstG@1
>k,V+Yp95g]AKn\*5lH;5.Pqj,F$;htH]AnEH`'Er[#sjj2`2(;QmhSp5`'77/XRS*5-lA"OC!
hVgi]AR[)`Dq*kcU/9:h>ho_Q$HTB#>*"BBFa^8^*Wd)Vb`-"EU`6q(^:A-0"<r(3aYoIe*dM
&]A?POI-u[.E_0-_E*26;7@'@rA^Zb'Ws5OXQ"JX*usjDu#Me:%4H>8HI5AC#8Rg`7=pK^6Ci
&iQ^hGNG.]A*mUtt_)BMu&b;d9X:=Sj`hp'B+>5fFAYV<Q0I:A->E:l)6p,R.9g=rMtc%J_;b
DFe',O,`+/'&$ZPJ$K?kA^ZWc#L&.`J-A(Oa[Z,YnEak(IQ.Mq,6T.QR/f0D52&[S7-D8nW@
2#k94EUFi2u)R1F]A4S6U7o$WG'9h]AG7WQ+Va?EPAT)W@SN=W;u*D56P)l\4$^e/596kX1TJY
<*ES87+KUFY8#b=qFpVS6WMgQJp7Goj`,^[acC%41H8>^T7+qh,2Vj`S"rETS#bJo4BXdc;Z
Z<]Adh,uM0mh@0b9&DOWL>CXX)2Scpd-#<**"O^7t<Y>eWCC(7-^):1sjE=a.E-#*S\;$4r,,
<e9Oc;PVIe*1Z_#VlaWIm?*`%R>R9hs)Adura+C(C;-jBM6?.Qf.4(+Zl*iq,kNRuh>eC?H;
K3<D1-<UQEnE)X,MMaf4!0$WF9,Eg&`@lP"n(VK-\$Al=U%\"infa1s)9Ss"DCV:*-fT_A<2
+G4l<#33q]AuB,OSs',jdVY#-KEo]AEk/O_<d`SY5kQIrQ`tr[t7]A=i2YeD(BG_;Mk*eP-NIn,
m:("?%g,lJ&MF2R'c2<@F+s=.U35&8muQ[c0<Ko`e(sm*<u"Rc!iXGJDL):9<d(+C+7s@;'t
RUp7dkB\bNoC!?3CN^/ui:"X8iREH\,A'8jE*Q4!3C\,2blior&$?.N8H):`\nYoi]A=Jddc7
qU;VriWtcF@=(P0XWb:u-\/BTd4s!s/XS*jR>fB.16;@NORuXN3jPt7+lT"[FI!fUg5'j@J!
QM1`0^A)i><i/*@Ad5C7n`rZRkQhAUAAQt-cA,\31KcaZZ_HnM)3mc$>6fja6KdCoUL;FXa5
X4cXF>!!TG<%qnVHuE[$JnFX"IY(!1&!Vo!V!<lsBRUC-bi^J04[`RR5g]AHo2BT=_eC.M_l1
i5ObeYfo3'RDRLf.>nLMi2E@m*<C*u*)m_RWI-Uo%Rh$]A$-O;YAG_t(g>:u\X92;Zg4/2R^e
G!BHX$J-&Z-MCWoqGp"hudUP+q+RdB9IS38e=VH<SGF7+eBH71WaGRo"b>X-;#E]A2ZSG_4r^
<@n@)Jl)N4GD"AtK\,\IMIQZK\RsYsdare!R_?j#<`5;i@VoK)G(X3UQq^+R5KOTQf%Q3#QK
p>Emqu00f+rpKuH<FF`d+Sls&VOA<js_u\ltc03!Cj:6<d!OF\)nG"V#<'e2KsRg*9"L0E=p
3pFf6L'f@BY&p6/"j!MeZW[kSalhBi<-@59$*lNVQ/CgIQ*&Bb29:!./?2A(2#,%L";\=/nL
a"s&EI#%GX,nDW7Z]A_sqKR8fd5m=lJgEu7BTO3Pn(OS8F&9+#*e`:QkN$J;r4Fn>nFY,h&pd
d/KnW,!T,F7u6A&KFTPN6R)>g$^n@Ke^l7]Aej:T;_>Fq"oO;LK,"h^Ys[P#1j@T\L_58@coR
n4.$H%U)gtA9*1>2]AJ5//V(<>m@nNoqFTbhX:V9Vg_eYjNd1l>GhSW&G^a-*qH^Lm=_^q3r7
,pJhl`+4QjU#I?OF2]A0$FVO&%NcQq6qdHH(%F#Hl0)sY&(>p`5D)1;`rH)>!(fUS7'8jaJc
~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" left="F2" order="1">
<SortFormula>
<![CDATA[=switch($$$,"1","2","3")]]></SortFormula>
</Expand>
</C>
<C c="2" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3">
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-4079159"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="80" foreground="-4079159"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0;-#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="80" foreground="-4079159"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buf6Re9N.&<'$i/8r'@1CoK-8+i^l0>8siD$PT:'W)VejQt*%Hh,L^d9S_c0%CL%PBTP5h.X
=^"CLUb?8JjP-?nPrlk;Sg0-U$.2mHZ5$NUQW9rI0e$kOX.P^-DYU^Y3GPZr(FIAldfm=h2A
mCl"^ORJ@iGiP9q*"9iNrZ^JRW^[g-=#;:3VnFk-Cm*FpnjH>EEeIT/[UbPZ>6N:<!"TBtR\
+A@WAK('$`BQOX2-Wp"SqJM?R[<B&^?JVLD3<%YaNpn&9p(@C#U=M1MPMZs0(Q[8fhbZV[PO
-nc!$S3jqf43(#Rhm7rAc9-AAP-b.3(j4'#\9J>\ZhY+F6^Y[i!h>kgr"Dl42?)EF#%Y/qK?
-O@9DqoIsW;NG$*2@XQ(3A,^QmddepeE#-'W]AA.t>c@hX*HZkoF!Jhe?-,H8ifGNC8%Lj$g_
cl8M+P'`9QI,R=673XioDt\73OtiB4go]A0C[GIFj)sQM9Lu%q^@e3<pk;7=-E>$s)MTQX"a.
>EjnSdlEh7W7-YTZ3cKg<pM_g>*Y5qKSBl>W&BaghBYTb*lHG*%qgNV'`+]A2N)h\g(*a^6*o
f\$T>R<Z?<7e:+SnUh>bK*@;(F!'/;f@(*'#M_V/@mUWj-'8aq.`tqnU\+s2@\4HP&o(sgL/
'&@.q__^r!B_3E=`Rc)lh!F&CK'$b()_6"+HK\IE?%j!C7@XHKt8$DK`GYeRE;j%&q_k]Aq[F
\$gW]A]AO$6T!T1^BZVK@%ce(a$FE.BQ`i\BBa*KGNcAX-hrUfEI\.Q03]AbQhApIEQuObSm=<C
U*[Vn^p(i=&;IMHOIBZI0gU'dlfMW08RV_Gc<3BrDbrp\6*Or$I\q73l5iLP*-.OLj0$'qr*
c4>,>+1&Z$Z$%+<@KB,I&OG2>5rHH:6)=3gTlM^K2-O_(Q^P8V5=)0Zf?#/Uq*+*#gTU-B"m
1?;,;:&G@oWY1H1eY\T.3k-n9?7,R[sDY>p0^t:Z&IIfU#a^)WQUlJDiGsn'(FbWc%1jIq'-
qa>>nO_h6!sIDN.7TdO;`-o@6QR!n?cmRbfZ49pauO'/gcu%65CU56]A#`RX!!0*j5;1?M^/f
dUi,1$5Si*aU1Ba]A%F/^6t^^7Z%/24]ANiBR9sMX:)HCG"@T`[Kd]AR9;Sa422C2RK8]AcB>(/b
"lmMZ[UDKoF;tYe/3m[IP[1W6F+eg=KqTO6PfZK9Y@PVokscU@,NB:_skAkXo1u5\Y`00^`9
)6+QrNha3)O;tc>XcZh),CNa/]A,;e%7pP!<b?.R:Q]A%3jpEu!G2W<%Sc'>F00;b_lQ4=k,4S
*qs'klcu.$qlR[ortFZ?D^2GL$$Eb1f^[b;KN`d/_cs2g/5rN=#(gr=dcHj27<mRq:K1=__<
"W3c#IbVc)ICOD4eLH!r"CXK64;4ghXI:=Ih54/a<RPhbiQ9T#]AK6rc(H,!ru.*#Vu;m$('l
/)1k#2.Og]A;Dt<H&(qu2=90uHKMf"fJ]ASj-Y\Q-T0l7QSEcpo@0T<6OM,QpFIp?;HoTEFFVr
^9W>oc7*I'9:"XB/]AqUai:*&TG?s]A^Abmr6i,h-:mE[=0[%9jL!'[+0"I3>.7>WeKkep)#&B
(S!sM`UZr@;9AQ9S%'RAAB2,$:.fPr`j!^Y-Glf.1nhXp)cYol>Y6i[@@>f3EV2al\b@XRIE
PQ6FrG-PB":pdb^-hite5Oa>4s6ePK9f#PD.DA.Pq%+SH+[i_Uf\ZuUW-A4<"6B;%3p8EOU`
IYKd55]A_1aJ?/ICZg^Z,p\RU7dTM!CJPq2bGL\l3"WWG4.J.V1r2$kt0t.S@OTN8ic*h)t"q
Pt1;BkBP]A_;DA5mT]AL&_76mb,3,[J[.k3E5GH*^\(@aJWjWpuS$[n^JUijo[!6g!J>j^oa(4
:3tqo'_e>c&1AQMD1'-0o@QE!.iP<ri^B&qbj9``0l/%tr7J/$CPG(_Jdm?fnHWr*P8$h<'7
0ZUk;Q2ZW/S5I&:\pn]A5*g2GQZQcu(Vo-=@m2'^b6^_I+EA4Qt1>hhC?U11ILZNDNG^!(ka<
2D'+=&_`9(r7#1qj)lf@WZ9goVjNXHffPS>2sPhap+s#1Ql`53@%m5(\eI4'()eKH6\qEJRF
`a\Ifmo2#D,AP*XkV8V&`(fPL+DE.t^T!1R@,("kYB;HB`jVd<+G@YHbn!b@#.jWF$BPfL2T
p>AR2\$hK"OjKTl(?V0,Y,RCiK0:?@H<(TF;9>3RCUDn&2k1`,aCGsLSI2)p6_Zo0=4)lP7B
DutAS%bW!]ALLa";?0K(j;^[f!3kWnsbr<b8;+TLX\`a^5[-qaL2>SpLG8>?oW-h"hUY@(Yq[
(6C2#,c<::s:h]AMS"R3`3PT8YZ/S#soP1Se6:$j+M+L+WJ1%aAj&`K'T@AU_\'aV.-Lo,drf
s%9_`P=t`ObA?I6eod,Yb1Cu&uEEI\[1FML"'CbIB*0B84n"d,'/YDFUD@L:a/M,XOW7V6UR
FXac&E03ogI=!6'qWJ:kF1WdDCf;9sm\8sd1ml@f[3_:$OIbs3A2:31/QLCVZjWt3Fc7R"2P
r9<='=g4lYr>>u^T!'0r':A,G7>(/Jd>9H#-Z7Mb#,q,Qo\F&E&fSu`SUE;Y]A%)t3^Dkuf)h
m(s6&"11.Zlg5nF$6AADWj\Ft+.>4R0D*''EXqh:)9-+,Zu+B*pjg*GX2Z666rd\N@u%q=jK
[nn0JI)FPJ+f?R+#!?/srZ>1bq2:u\jF47.@(Ta6uE'1]A,78jhS5*<)CL<C=S:UIT(bu/"r2
\7tF-k+/tlnT5JKk[M1)P($.=@Z&igP9r9b0cAENi5@R(Z4EZ#VI'Whd8IM9%.Njj:6`rUYB
1[D]A]A#dB_S-*$(u]AN*ru5F7',/)!1r'1T/G>GZ^(itd(*/o`]AEipbdV2g'QW[-brN[E02$!;
_qn7%Yi425>jWV)rPpYn'L/Yb?>\KNJs4ftGc$e&n/NL^,<j#YOkOh[K.Fe3Y:t99.*^_7iL
l3qfik#FN[NoVMug-Z^`6!:.OD=L4Db]ArKomm-d9(Ju-^["&kj>k9L3(]A(;-"boW0sar')hc
u5O%]AX9>B',q)Ulplj-eiZ,Me2mhESd7dTqbIQ@je?BEjqhIecFbKSjHn70?k!jmRQSP9UVY
37Eg@BYNqPs[tilb9LI?g%ps=?HgH)ITnbl`Zi7_"kG[5>0ASDl,c..)"Z%;-s3I@A"I*(L.
Q$R'Id%2[$=c'34+^U_h^`M$l71*s_l\o.:q-d&#kT6@K5tq%A/8Ig'h")g7U,h#%Qm)UKZA
VfUY/%9JnBc+\B3_XM#7bLU>q&kfGIcCLCV:[D.ST=E6mKf7'i3.]AqEg^#nEA7s1#>kV%9qK
$Fm!mmn]Ap>XQ"BEL:'dn2%*;uMSj+T)4*&n:lCaN-j2/Tg9bkjs'LJFsW@%&k5hW]AjJTTo8r
1a31nVVpU8$&AD#kg#+-LTc1=-+;5Y)i3TsJY<O"h/)c_O)\3\pZFC6skCd[L-Muo<]A4o?b;
I/P"';[c+o#ACB[@`8?aV=u;_9=G`bVNdfTsZWCd_'FWYs\I")Xohr.*Yl0J#P>h.[W`0i?r
beq@h;!\:*j<W1h2.O30Xj[oj%RqJGK;O^G7S%N.-rcY<;*NX!dM`c-C8kV;>MC@tLmh%e-=
]A53,gBKtd"BC&0-_Zt[i.0=aS8a48@3lq(1F8pI>9ob7BnfqH?@I9%;40:qOnWNkoa7kQ@,X
'gURIP0(q*^TE8Kf"Dj.,:tM5'8rj%^Rln8Pt34k!M*^Il>[p_S&<lZ"56PQ9a<_bepg,;ME
;fFUC54f8_@e-`1Y1@W<>JCdVu$<Hsc<8fG3>.PeH#)s2/=Es)1UDqePED@Z&jkT2UL`@0Q]A
j3"D3r2@X_'d'C-u6Z380(IgNs=T9@.aWJFg&4:JhY!i:#@#!6H;1*q)'L7-4jFC'<EFil2(
J=XNg!niLO<UQm7rPeX&*C,^NN0nMi$<hCFblUX\&g=Zah>3Y7ZZjE6p#.N%$aeqIZVQtXN3
*oCX)0atctcMbo<%=f(O6ft,WM!U#a>8gnYG%lof#)skDY5ERrBKk@b#sU>9,MM%=;sSGE%U
&3bb3M*j9M44^H\6?C:V:P\p+O@&q3rj(*`\Q_+S]AQsol1+_p5dhQj3s(R;>M?>T+'bl\PsK
$aaW#j$Dq8Ti=VjiQ%WuO?,oa5dok;(eW^hKO"mIs_"j$El2+Hf+bV=lA8oA-&X^Mu6k*Jd^
8p7Wjp7Q]A2XFjlK@[I12"eFn?'S/AD5cS#J?pQt@7H@_Uj""+<\KSj!JMP?>0&GJDb#&D:0)
h>=&=<"T\2-bot'#LA-@8Kg%\(8K)&GrD#1!krK[Q@gMh;I^J5eUL\Th#E^!<T]A(TZm0IdYR
!ZQhNrL&Qca[#0d"8LS1GuD6ar2>&)5pt]Ab*=;/^i^%TJ@N>IR?N'p]AZg:'Z$C"I59@__&qH
R<;8&Iu@RE'FlB#jR]A1i_qF>C)^IfXNjG*a7j*eQ+e<7$[(o+TkJ4s+dn^1j@2bV6kLAR.OQ
Ph"eUjKp%5?nYQau<6riH>+&ep#DNAg%L+S"ouXNYI?!!0H",hjRD2UiBnIO."IKr55l#djj
^VGqP<[Y=GaDGH?WE&`ctiQP$&$O8gR+"BliHpZB*Wtj90aG/7'/0OJ(5iHE7'I.NB(]AhJr^
[&g:D]A,r>*p:R.oU+F4EV;3;.XR#=up77marPX@_b,M.,C^CCN.h)PO-$cp[G@*m&s/bu]A'k
,D\hET=(u_KCjh?1-@B2#4H(?)M^u'Z%qG]AV#!'OF-m>!f:]AVe(R4%8$0tIQ4-2mA_PXM2PS
ah-;C^/Ej?]A[`+KTQ'[RPrST`/isH'=\i#S;UU>;kLpS&-5s_0h:jlHTA3FdRWhG";!1s%JJ
:JG;O>Sr3+j=.747n)<%(cX-q>Hj'6H8*"0Te639pq*Y(I^QAI5q7q0e$>bRbX+*,(\!1:fN
,Wr-9LpbKb]ATECN\$+m5O/e&0("(pr5gVq9dXCO9hb]A2eHXqV.OVKVnM.W44,+qKRqV%,KN@
?MpQ-K.'S@)J>gAgN57fRT/CV?Km2mR!!Q\B;61Y/W6Q#Sn?ul2FH-ugpW&F$2j(JF74J7Dm
W!&J&2ri!l`^S)g;,Ok$WG1'XTkes%QkY-63`JKW1jLE"#Nr/;?^X)Ppt=Ct+=O$m7)/Hur%
EU`2SKMS:=JJ=U`VsW;'Q8p7\K8X*9DTpR,`cZf%8:YHk*O]Al5dl4"2;B45R&3AOg]AI^itT5
]A0TCr_NrG#>UHVBec$r7A`30QnJA:PSq6/"H%R9S-~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="267"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[一级部门销售分析]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="2"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
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
<![CDATA[1008000,1428750,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[排名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[销售员]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[销售额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$datetype]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SORT(B2)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand leftParentDefault="false" left="B2"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="salesman"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="p_city"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/saleshome-2new.frm]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="sales"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="salesamount"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-16776961" underline="1"/>
<Background name="NullBackground"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0;-#,##0]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-16777216"/>
<Bottom style="1" color="-16777216"/>
<Left style="1" color="-16777216"/>
<Right style="1" color="-16777216"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="195" width="370" height="267"/>
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
<border style="0" color="-13882294" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
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
<![CDATA[2019300,876300,1638300,1028700,1638300,1638300,288000,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[266700,3744000,2102400,576000,576000,3744000,1181100,576000,1981200,3886200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$p_store]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" cs="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="0" cs="4" s="3">
<O>
<![CDATA[截止时间：]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="0" s="4">
<O>
<![CDATA[2017 - 06]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="5">
<O>
<![CDATA[日]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[day]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "day"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-15296026"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="2" cs="3" s="5">
<O>
<![CDATA[周]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[week]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "week"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-15296026"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="2" s="5">
<O>
<![CDATA[月]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[month]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "month"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-15296026"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="2" cs="3" s="5">
<O>
<![CDATA[季]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[quarter]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "quarter"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-15296026"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="9" r="2" s="5">
<O>
<![CDATA[年]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[year]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "year"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-15296026"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" cs="2" s="6">
<O>
<![CDATA[销售额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" cs="3" s="6">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="4" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="4" cs="2" s="6">
<O>
<![CDATA[排名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" cs="2" s="8">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(report1~F2)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" cs="3" s="8">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(report1~E2)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="5" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="5" cs="2" s="11">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(report1~F2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=max(ds1.select(sum_salesamount))]]></Attributes>
</O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[1]]></O>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=indexofarray(sortarray(ds1.select(sum_salesamount)), len(sortarray(ds1.select(sum_salesamount))) - 1)]]></Attributes>
</O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[2]]></O>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=indexofarray(sortarray(ds1.select(sum_salesamount)), len(sortarray(ds1.select(sum_salesamount))) - 2)]]></Attributes>
</O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[3]]></O>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=indexofarray(sortarray(ds1.select(sum_salesamount)), len(sortarray(ds1.select(sum_salesamount))) - 3)]]></Attributes>
</O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[4]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="6">
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="144" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Top color="-11841939"/>
<Bottom style="1" color="-13421773"/>
<Left color="-11841939"/>
<Right color="-11841939"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-13421773"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-13421773"/>
</Border>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="128" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-13421773"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="128" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-13421773"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="128" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-15296026"/>
<Bottom style="1" color="-15296026"/>
<Left style="1" color="-15296026"/>
<Right style="1" color="-15296026"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="136" foreground="-5000269"/>
<Background name="ColorBackground" color="-14342347"/>
<Border>
<Top style="1" color="-14342347"/>
<Bottom style="1" color="-14342347"/>
<Left style="1" color="-14342347"/>
<Right style="1" color="-14342347"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="128"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="Verdana" style="0" size="168" foreground="-15296026"/>
<Background name="ColorBackground" color="-14342347"/>
<Border>
<Top style="1" color="-14342347"/>
<Bottom style="1" color="-14342347"/>
<Left style="1" color="-14342347"/>
<Right style="1" color="-14342347"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="160"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="Verdana" style="0" size="168" foreground="-15296026"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="168" foreground="-1286587"/>
<Background name="ColorBackground" color="-14342347"/>
<Border>
<Top style="1" color="-14342347"/>
<Bottom style="1" color="-14342347"/>
<Left style="1" color="-14342347"/>
<Right style="1" color="-14342347"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buf<Te7m[hp-(?oj%q0bQ:!'tjA_'FX"r=n%Vi5_b'6(f10FfkSm?tZQQ0HOZ"o[9MO:3]AWF
i/%Ak8p.K+u'd0ki>]A)"[_H`jWLe[I<:(pO22-\aTFl?2&JIp\0$jf6&HjiU3EgV]AdE4,GVb
]A\'p[!1.tG!17oH$Q9d;bK9R5kRC.<eS9B3IjUZRCS2M,Ch@&Xi8lUOnqg9+V0+4Xc8sE)I6
?aW"a(K_kh_!6$Z\?%1'r(GFpepR2ZW@ihh=&+]APblH]ABkeYK&TQ)Aio9ainMYi$rL89W3AI
*hKG/KgHgIpjYFcm$m1>/$,`u-jl]A%oM_r3V`eQ3)Oa2O3]A*Jm?tLh*JQfHR:T%)Yj+$[.Ws
*(f*h5ODGN-Bs#c:D-A['AKu0N=W2*F@R4d;_CA.\=i-^N_m&$;:lpd=NB\TB>^t(STbg?bg
^>2]AOH4kH6kKRO47IOD[Itm)4SodY87K)OqDCkgOW?s:H=*"@qAs<PJZ6PbUTmD9<-poST^f
%2HGFm]A=k%!*(^"jpRK-sosN=j5XEXRdH9<`R1TNR%-r@sHl4VBq)-Lgm+',F'Q)A'@0T@AZ
fac]A)CXWZ.HhAFVHlj5NJM=iC;!0@U<TBsHg7f6f`DshQ_bi&lJ^Ans1AhbeJ+]AS"2lb2B.$
mX?PpW:dMrXA(f2+enjKc*534)G]Am0T:4^P%ns&#G8oajsRhg_b!1bdQ*DWn]A5oL+?\&'M6d
]A^R>%'<fE9@2Ru#ronZN2=7de/ZVijs%sUZ''<>/jcupfm&$@DPik(m0j_=aV;8'@NJMO9fs
?Gtr/bf_ZVeDMWu?tr]Ah0)pdR+HD95&NMr+?IU>tHnZWJICQF?<s;EG5J3`rNmh-/i6HSsP"
6E-Z$4&nStcX"qIf\b?X+nQF?)5ZMcD!76JM)O3=3_FOXP_ZqD8jpf]As0GEu@/3%ZjLJqR$;
+7lnqr"Jj;D!cm4JWF=7`q)pQs0M0BCo#'R@L+h6m?*4HT7PJ^6[nlg\@p:3sl>TaC\naoB\
6cNG*[Ich<JbF*,aL[tkkkdKUr]AEnY:E-pjHFi24i#_KZ+cVQR:0GXdWEZTC/C+W'OJ$KBCU
Xe!=mUoINb>=AO^TB=0=T-F'Bhc6W]A']Ao('_^O*SjR>0(b(^H#]AN:bu`d)?",FN66EH2sP,a
SNVI^eMc%:D;DoQl]A!bmj:s>4Fu6pJr,c4D=YLQ?!_Zq!htE-o%,`Z13_*S3=C997Y)Q<6.r
W',DA$Lrl`sJ.#^;/1)PjN'.g8$kAOBqkB!46+[4/r1r.j;W1N4/o%%\%[u6?g>E#Ss#9qfR
Z!!'krK$TOLK^e/3!71+YA34>Ko/bc>B-I9]A(Z:EU8T?s)#s#Ec$Oj_rqJe/Qtf"rh/NOO73
CVNqSP^2HO;bJ,C2lmsiiZIbOFPVK2(0gc@hu-,F'H3^JK>)rZ@'=0JMK\TW0<BQ;UL@]AsTM
oV\)O-tp]Am:sN.YXI]AO+eOn1IS!`NIJ4lE<!<B"39+)%"\;biKbk6$b0*rgWoU)(g)qaLOS_
m")%i%M'T'$NN`(`WRrh;h'&oaTi)CF@--3[g'h`3ct<XL_""pop$)Js2;2tk'gU@G0'lCo9
&2\tP$Bbs?@LR*'S\`?KuU=ePUQctj=ET3c>G.H\YRo24SXKS@P+DruuoYftOVr!qf&.eJHV
T"NPo?X>fj1:Fr*+EQX`bQQ=i^2_u&3j(5_2)/?8P/Kg`-q5Q(k7Y-K+2O9,amQIBiFFBRHu
fj8I!7pqGlo1mt8f.]A(D=o%t8;nFUpc#8A8B&n2@6hC@Xrq6`i?"Ht@LqBH65Fd9slMohmf<
_6SG=Cs:s6ShLuQeOPbGf@2-(e;Icph$eG_.OK*J^DZ,/AJd;+0O6:Nk8#7OGEF'i$H]AAXg4
@gEp)"/FZ[a[W+@&nK40;`GdEA`#lB*!mF=b'PB(-u0`.9FS*dBa8LWu683PD3Joi$(N-?4b
q36n(NC;(+TK#]A2fo6dubf-mVYk7@M&Mp,O4lee_-3'jsbiC]AnPbMI'X<"%+jXJ"+(E10qs*
Gg3oMaO9sdFSOg=&GTlBeb,4>>gnbZ1%p4<,IQlla8oi\kg<=kDq@bVZtD56-[<;L:0'1#`N
noWAM6OZb@af%R,k<LA[M@?WjsFD=O<p/KQO2<<^@dgf<"P9r//IML9HpXb2>PAP9Bt7sqd6
YucH;+*c#Z2.@V6ZqOod]Ans7O<02/do>gbG[j1%"@V*nDOdCFl8<"1qQ^"8\a[ZZ[:Q16Q;c
D^7ETu@5U"-Hr0d/E&cK)]A\$+Tl&qHJ;tD!`t3WEdI68FW*=C7'@Qh9dB[KP"<9.QBZ1Z8n2
IGp7<U\>0'pUNill8-`j9f10-HP"6>&C.P(j,G,>QLrP,sn_Hdoda6`"?bTu4[rsm@JU&0Ke
($0f)VH^T"p__p@mb(5)3ie[R-^^'fgi<Zoc*O/72sRb1r=OO;*PU(4H!rPk=b9d7,.FB5TM
OFn.2/a8aPd>0,SphQ=b>*$c,2*!5>Lmb5?cKf=U\``@G+adl`!g*?2OiFAZ,*5BBL'o[dU0
?H10eC>$r3_S(Yps8'&M'5"!>`ZN%.!G9UTkF,Oe)!.eWh+Dl`,HV&gQ4F8kUa*0m)\46kY0
j:b?j-6#gV32Yp/Y>qUYFN\[i-Ir-rgPUfs@pFJ92Sf^m>VliR"<M#m/o,cUQOqeOFV6]AglN
$EnHLo2fhPj`,[IB;/TZf54qsof(F]AT;[C,i;(h,K,S>?ij"L,QqJY/ta5bA5@%;GLDc<1cE
FG,5=\@0PpK=o@XR[8R_N.*E+`GjgaDY]AfY*E8\dJt/]ACgCr33SSKiIgXqEn8Ci$Q(n*8.t3
k59)jo@aW6+*M,sbq!(f$(YD5`)"+0d[4C:(2^0F7QRT+`KM0_/L9rA+Q=kthh@FZSB%^h2A
JefcH8`=;t"V_1f]Ad>j@>abek,Dtl]AA3iuMM.^-P%.ji&,2j$%8SnU8h>.F'rac*NCl++7j<
X7@;51H8'hjZib;s$L@(0g6Vd1rbq2OoVhK$^I"d$HKZHXgdj'pu#q36n7*9-te+kcZO>ed/
s//aCd+IeE=2,#r7.S:pZE*Dg#[L<stGj`ecI5d4:K+`O_OEE$$8#0DLIJIh+&U1'1h`0<ug
[B?&GCtQ;0X":>R/pGV1IA[g_,d(m'3A$b'6EM_NSQ6F#hID+NN1q9ARg;YfXh;-COGJuEG\
NqFgMlR(2='-NTOsL[)oi595F,G@4JuoeL?OgTfgY6Smt:/G-`h[I-2jh<Ng[Ib\npR#J-3/
W.M;7B<76.n@TU'&oUL]A.TsKUA+Umgb>PRQDEGFB`nt3p4W+F`:<;,s%UV^0!-rcM]A[<49>p
09452a&E6!3,J8]AQhTSb'r;JlU]A8Q\6/-W&c?M5EnI0*o5(8I=87BnEV(7.@.LfOLgTqUB8h
TeQ?^3_1g5W?sVuf't?>=f&/5PTL7VQU$_#fmVq6Q?WIZ*TdY\r'b8CXMoko^bO[U"GBin<W
!2#dD*7D(oHa^E@MI[!5EVWD2C4n&\Q.X.CcJ!Y;rZi.3g=8ac2(b8YE<!Wcu[T^<6VN;+*!
%DR.`^h\92AZ:+H#1[f',F/[r.2dt<dt\>+2T?s8b$dCE3e907,-]A7rR&'\RSKRRL;@`[5cp
\ZBEhH]APSkn)\Uomk^7P.rqm^&%8:Q><g%gCa4U>(6]Ak^9!aM-OKFk%-E]A%Nc9./Nq7Iqo/)
K-AB6A`MPSL?^1B'Q\f!HT:$*XWE^<Bq!drN>dJ`FuM0=T8kaZh\t#p]A>K0aOJ?7]A*qb7gA"
%%=ni[rf*p(kY2I1MbmnBLd'\lJ&[6S0"'_0q[^Qsbd[\O\]A`dt]A$^PPY/uiF$Zo^amT$+J'
:sf3ct<@OA\1HY1Bc=%@/%3"^kp/8T2l0<68srgW!UA]AcBmk_Ql#F"dNNFOr2(J?cM2\aK;T
f+'Ir#KJ&5N.qmc0c9UqWEhUR.h^!To*@N>>k?e81`h9A`pLKGDIR.jhWK"7h4V@O*m]A8JuZ
4;1#KlR=CQljK^Gf1ClZX@0-]AZpi9.&!b!AbA!E!F.YJtB+uus^$EFOPtKE!OF;H@/de408G
;.fq+4'!B5=iJ:k(h!pO1b-\BlJol4JNM]A&L,S0$<F,4Vo!974%>r</-PW;`$/oQ=+KMQWnC
L'[9^L?k@%G4T1pU^Qtn!)(g1V"ZkE;V:3f#lrR7HCE8Ok;X&,8[/B[h?BEl>n'u*R%aOQNE
:&_n$cOd[#O]AU6HQ<NtmR"g99_";ccb9iTY>j,\QCXh<F-?9\?[\_Zp,#-:?Vjen<Y'cjr9I
U<beIgp-JF8[2J5R:$+!_t1k-uYD?1>[()nE4OX=oeL':2li8gE1CHa,6l/k(Q$0`&$#I7hT
q..L`*iC>Vj'h0"QtCl<I[9/b,*fA=e+uU'*[D/s=p]A1sY*2AjmCjWY2KSuj5c*emi[7_2K!
^^jVs!g6GX9\nNM"8fAY=R7QQi%3K6g(bGt+`_*B^_5c_O_u=CBlKVTV%$6V8;js*OY1`c5F
@"tdDa8.C!UeuV&`+l#j*n2(m(ZJT*r9k*ms/PK?IK=r""l7.Mr&@<Q(fWdOVl(LH#pkAa2^
MlM(W3JQrcN=%qc(763cjb4I9"@BiTDku+@`G3%ePHeF>9sH@X^2lf=6o"fKQN#fpM3*I@>D
ZjD<[-5cf_t0BF8K:1>dYMa"S`=8c"E.g>sOc,945XS8dcmbsQTNPOi^o.GJI,2OlsfVc5-t
M_lNLoZd-Ee;K.")WuSMR"^;998Jc*kE?a7G&:$+PU*bNN32^XT[D+:@qbr"8H@6b?H<(0m^
K!Y$ge^h,0N8d0j<iGZ`7[8NeKlS=UgVG<Pq%E:LGr-m_aeFs*Z^i:MC1-3^$j9/H?gW#p:H
k:/f?^9Cl,0XCC>/1E2#TnYTU,7;r0STj:=h_#6$XL\(1oktJe*M'"-(SLM68TSV7HRgTs'^
OMGU-/bMLLZ;4GP(fOCaGr-i8;oA?:qV#e+oB^Dk&rL0qtZut/mUoXS<!JOL+8A$?CF/O(?s
O9T4G(S^de^gq<Y=Wqi+-c-(P![g`1''k77n-gcY$[O8b6NLKl;2[]Ai)47l#u?MfPD>=dKnG
[<qp:L":(?[<NRBFK&W[^fV.J00'3(f2aFt.F[iVdB\ZZl89Sk\XTt/),;=:8FM!Y59=Mkls
@nX<h2gl&HQ,D:+11*'br<m>KX^CqU%gjP::N%kn+Ye&Ao<!Xq+S\^NXZUP'@B_TQ.\0`**.
Q5(K#1>5j<<RY`ERTHh`6qEG?ZiOlk@SV:25_Kb;X7YE1KM-UOCoh(=Pai(mYjpR2qn:c5#I
g^TU`Cpo9WBMA'3L"2'"9%pQ]A/u;FqWb]Am!<~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="195"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
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
<![CDATA[1008000,1152000,1296000,1371600,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[142875,2880000,2880000,2880000,2880000,2880000,171450,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[集团公司]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[截止时间]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" cs="2" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=2017 - 06 - 01]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$datetype]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O>
<![CDATA[日]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[day]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "day"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BorderHighlightAction">
<Border topLine="0" topColor="-16777216" bottomLine="2" bottomColor="-12598302" leftLine="0" leftColor="-16777216" rightLine="0" rightColor="-16777216"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="1" s="1">
<O>
<![CDATA[周]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[week]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "week"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BorderHighlightAction">
<Border topLine="0" topColor="-16777216" bottomLine="2" bottomColor="-12598302" leftLine="0" leftColor="-16777216" rightLine="0" rightColor="-16777216"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="1" s="1">
<O>
<![CDATA[月]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[month]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "month"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BorderHighlightAction">
<Border topLine="0" topColor="-16777216" bottomLine="2" bottomColor="-12598302" leftLine="0" leftColor="-16777216" rightLine="0" rightColor="-16777216"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="1" s="1">
<O>
<![CDATA[季]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[quarter]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "quarter"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BorderHighlightAction">
<Border topLine="0" topColor="-16777216" bottomLine="2" bottomColor="-12598302" leftLine="0" leftColor="-16777216" rightLine="0" rightColor="-16777216"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="1" s="1">
<O>
<![CDATA[年]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="datetype"/>
<O>
<![CDATA[year]]></O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$datetype = "year"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BorderHighlightAction">
<Border topLine="0" topColor="-16777216" bottomLine="2" bottomColor="-12598302" leftLine="0" leftColor="-16777216" rightLine="0" rightColor="-16777216"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="2">
<O>
<![CDATA[销售额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" cs="4" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(report1~D2)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="2">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" cs="4" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(report1~C2)]]></Attributes>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-13882037"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="195"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="report1"/>
<Widget widgetName="chart0"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="370" height="724"/>
<ResolutionScalingAttr percent="0.9"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="e28f918c-eae4-485a-8183-931b7c5e1f0d"/>
</TemplateIdAttMark>
</Form>
