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
     货主地区,
     round(sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣)),2) as 营业额,
     round(sum(订单明细.进价*订单明细.数量),2) as 成本,
     round((sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣))- sum(订单明细.进价*订单明细.数量)),2) as 利润,
     round((sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣))- sum(订单明细.进价*订单明细.数量))/sum(订单明细.单价*订单明细.数量*(1-订单明细.折扣)),2) as 利润率
FROM
     S订单 订单,S订单明细 订单明细 ,S产品 产品,S类别 类别
where strftime('%Y',订单.订购日期) in ('2011','2012') and 订单.订单ID = 订单明细.订单ID  and 产品.产品ID=订单明细.产品ID and 产品.类别ID=类别.类别ID
group by 货主地区]]></Query>
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
<![CDATA[=\"   各地区分公司营业额&利润图\"]]></O>
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
<Attr position="1" visible="true"/>
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
<OColor colvalue="-18398"/>
<OColor colvalue="-16750485"/>
<OColor colvalue="-3658447"/>
<OColor colvalue="-10331231"/>
<OColor colvalue="-7763575"/>
<OColor colvalue="-6514688"/>
<OColor colvalue="-16744620"/>
<OColor colvalue="-155607"/>
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
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
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
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
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
<Attr xAxisIndex="0" yAxisIndex="0" stacked="false" percentStacked="false" stackID="堆积和坐标轴1"/>
</AttrSeriesStackAndAxis>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition"/>
</ConditionAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="30.0" fixedWidth="true" columnWidth="20" filledWithImage="false" isBar="false"/>
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
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="2" lineStyle="0" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="RoundFilledMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
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
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
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
<Attr xAxisIndex="0" yAxisIndex="1" stacked="false" percentStacked="false" stackID="堆积和坐标轴1"/>
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
<ChartDefinition>
<CustomDefinition>
<DefinitionMapList>
<DefinitionMap key="line">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="货主地区"/>
<ChartSummaryColumn name="利润率" function="com.fr.data.util.function.NoneFunction" customName="利润率"/>
</MoreNameCDDefinition>
</DefinitionMap>
<DefinitionMap key="column">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="货主地区"/>
<ChartSummaryColumn name="成本" function="com.fr.data.util.function.NoneFunction" customName="成本"/>
<ChartSummaryColumn name="利润" function="com.fr.data.util.function.NoneFunction" customName="利润"/>
</MoreNameCDDefinition>
</DefinitionMap>
</DefinitionMapList>
</CustomDefinition>
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
<![CDATA[="   各地区分公司营业额&利润图"]]></Attributes>
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
<BoundsAttr x="0" y="29" width="960" height="213"/>
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
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"   地区销售效果明细\"]]></O>
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
<HR/>
<FR/>
<HC/>
<FC/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[1028700,990600,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4114800,5295900,4724400,4838700,4800600,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[分公司]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[营业额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[成本]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[利润]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[利润率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="货主地区"/>
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
<Scope val="1"/>
<Background name="ColorBackground" color="-1641987"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="1">
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
<C c="2" r="1" s="1">
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
<C c="3" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="利润"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=D2 / B2]]></Attributes>
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
<![CDATA[#0.00%]]></Format>
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
<![CDATA[m<jCge)gEbA]A<o4coIX^6!2\u>02ha5fU"h0sqK>*N_SX9d<q%A\qgW#+K0q2IMdt-Gh[#O?
<jX-]ATpi70jP3Rtri>`[2WYk1I]A+Dt`K7(AWBLp?D8F4Qi4qkF6tjL\KuGYNrpkp\hh_f6*e
-?e`;;q/8WLlf[H?r7+kThVmYl&C/Vj_3Eh>ir,?^aTLdVIVE0&qXVYXMsIc\XjsrfoUk"!l
jQn.,MP#^qV(V+V2m`bCHq]Am1MJ-G?9?Q%s3N8,:7U)5B^JG9It"%?n`&;W\b2#2&T(bdZZ9
4DB6@LJleLlJs+<68Y$QNf9(DX'L"5#8@nf9,798]A>i8)ekd!e_<`+AM@V"[YlGdXQJG'KHr
TV$l$9ErD<Ji=!F=QOll7_IK.N!k7(7jKfr4C9BoF`[E/JLXrBDu:4H-`CPJ0b[-hL>7!<I>
tiK'b5`(7?iNT'Y`5s5k`&krG'kT!bc4[HITaB%J2N_*W(%>@*btd\Ga:]Acn_gZds5u@\DY/
lU#ajIK<[ce@kh!ZI\_Z@pskO6"n-'lM,a?84mskeZnM=5\KJuH3(inD>?g%?hCT_W[)JK"]A
BYl6$UZEP-t&@cmaC,Y7<u&E*hc`<7@PU0ZJH7Bh>df#)r`,qM@.aNV'V_td5$o/I05cFerk
/c)pYY`L<SXh>l9Mq^AsVN,]A9S956ocG/+fN$O>F#I&hkUn"^Ohc=K8UG2:*5X"9(^mE?/`'
/]ABV4aL4m*C#Ei$g//pbK*u#J2V))&mA8u$h?f:J/_]A3+A`%#Nmr9KQ6tVc%c5&8[XB#+l1s
f?qn-gKAj1/"MLP[ZRJ?FjmIU8YoD]A/*!hW:9;A1["?h\34.7%gRJY&IbU#t_onVT1Pu7(Q1
r<CG=Gn<A5.]AQ$NTKc^]A92k+)=H?'F6f8\V3LO'[bB5rKN,l\gY`ToZAJ<O'5jVZ-QbmdcbI
]Am^!JU"]A2PDp3;G<N!VUHjS!:#DNJM`1UD?bN>=*%U%O:*49c$30"7=F68Ub5@H<W%s!4p3F
>/!`q'j:Je3BT#V?h:dK<=Im>D*$=kS_3bQK@S"nTh[G_$b4VY1Sm$kFK))V(gI0Mo?Y]AAQ1
Vaqq.W2`go#S>dVl0Rac?7505SiKn4k8nHGHdDWN+Br)hfl#?j]Ab,5\bqefslPg!VRB[\!cN
-^DB1?6b4uk^409r)OkTA1;4_htEs#3J`[B4@'9\d,hQ[m`9J2eM&!=r'(74eoS4u$+3&C4@
Y-1,UigiXed-`2/CH"_p3iIrHDbCFZ.E3]AQ6bDrki\/oI(ja-!G/;EN"hBh`K:6HM^pU>_2O
Tu^4>J4sp,^Ai]AV5juW9OBV;\=>aol4UD>DHef!$4kg3Crt`KmpaIprc_5k3FPiYikik]A+'>
s,i,A?2gt;5]A#<"Wi-]A*q=-\j^orpBL4^2kb*QZf=3qa-gf<jF[']Al4mqg>5WF?b3`$J!]A*p
=sJdf@Pp%%D4pNCO.d/XPrAT+^]A1^l$V/s$O[+W1:!0VaL<!iCH^n,L2oH,>n(/J1`^V)C3Z
(i!NpG+%ni15-&W^pf^cE&;kN+4MX&),/QaQ0H"N]A`u?oQ%KTsC18@oo9D2@-)$3o:JPN$ZE
STlq5E:m`UpVpq?t;2o2UL>m"t;K`9q`QlQEKpi,=X+sK#7oL!n)`[(>O(%$mf>J`Nlj69CQ
"aP4JU&[,`]A$M'qn`T=BW3lAS7t/2#Z<B;1k'hhg+ntdf^CeBPi9V_-^l%Mq*ALki:[H30Qh
/n<r/EH!"iB]A9E<gFS8L2A3@UJ]Aolhm')dSQ4i.,"^3-dZQ6emaa8Pk=?0Pk.@(7p><GJ&6S
8UZb,fDXQ#!qiPR!PpOg>2O'.Q[OHEM%5[X..-Or:cd]A1_M[0J]A4W6I;,6`t4#InVqPEfF2b
[/4*LG#'eS(:lDh,U@F1>G"8meA+A>R4aRrmbrk=Va+;9,!u?b6^:rZ).,&A8Y?FB:?EKEjZ
e"R0E"bkdS-.R.m4VRt9#:AHj[5#ECjBSjZELI'\>Djq-bKgIu7hbe`BCKWHOKq3;o)?,$VI
J?pdgS+]A.kY]A$bMXS!V-W#_#K5B,`MO1FG67p0!>&V!Fa!Vg-kRU:`IfSTQ80"(`F3%a2D4J
AL`''\]ABjO-R6cR*W_oj&Q<0MGdm*/2YjZsFjBEC(]A8/+aKXUggn\;n`jqtq!Y`i9o"Q(L\X
W#WP;:?i\NHJ7f^]As.kZYhRuNV<h.Z/r)=a_GCn::5e+&Vki>N%QV"kr!<E^<jeAUTYH$=5u
o7r+7P(Pf:=^<Nm1k1$UY9Y/%?Pt:dtVYCH<#`bKH<W:\f_`Kulu*`b6htA%d7OGLUo0Q#HL
aHWCeS0);6[=VLjF?*Z1:"l29%_+-i8$hmm=X@cA5EjD2k>h(ak#hW[UjZ6p[%=DRBYinO#?
Qn>fAKKP&OBi>F',[fbEqqD7X#1E^5D-)2QO6tnp@;E.5J5GCa('($KC`!(MF#XClG.1+E8l
O<`LV>rG5'"4c;INh4'4?f5$@2J-rg.^VZl-odE[m^#eoXOrM*;fW7d5FD>"u,WZW53;@Z;k
Gr!3H^#$=8b34>%lI-`n.'XrXH!L_t^p*/!5nFScgbn)eF>,gH\^2VAO&9ojh%#moI./u,i9
]A3?YFJ\tddOk7=rWQC-tgNXfc0U']AII1mYokjXo;U-<?P%f95^)`;\?fL'1AJh;KRmGDp/SR
CPh1n=&j\42Pfg5t63W#]Ag&t`893Fjt#OT*i$GXAm\Tu>B[3a%Cqs/m*Kr:-%Jnr9HEDSYZ$
bbJ$GR)7!C@6&rKY_t5^Qe$C?\th/I7jB$*u<[5@eg3m^@/<?oV3DoQ^T6mi5\^_5H9bfGUH
L8:Q,KYWbcR0oj*d_<H*V.\!E_(+l9oq0)4I\I<?aIBWRI';]AR*QOJ@QF<"#:0Tr5Za4:U>N
S/NWLX6e!HBX5IA58>&B=#ZGd\a=,VI0b^r;(2R&n3s-$;+=^$5&lX&q!i-&JVOCh)l?=i@;
#+h*hdX"V`[pd<nTg2($^%n.'(AKkA&>^?aA?,>IZ>mii07Y_5gi)YNJ0e/]Ac+PIn7@;Bq!%
0]ASK5ie;b%01BQX9mEoSuT787sknX>cQqT%mL^P24R0%DR0YN403'Z-fBoW*5Y3?T,eVt@lL
O4^7(Fg#k"5kj7Q'ZM+U<lk++0cPQ>l!(n[_S'XXcCA?+SMNmQ_Y!S-DU$sqT2@Xk7irP+%2
o9U!Jm$!Z]AB]AI;Gp2TV2I95[:1-g^lbJ]A%`80K3RfUJiec%NZ!&"=CZf9(VTZ,YrV6?jLP$)
B-4]A::#EQ'rt\@f31+4)+&H,Cn.c4AP&@%A27\//#+'5XLIU2)j]AYXUU`C8i>9[9)Qr)UVK;
mSh6J:4pM5C;5"C:eQC1q<=beYah(J$TD-*N6>pL_b+\m\SV`/u1_H&-EBR/469'cV_)WS<j
]Aa!Kui$=2H.TZKKf_6W?on+8q6$r0mIhO=@92KlmkfQ_fG'n@)19-Ve5rN_@-gY)c>*Ndp-.
U-IANPB1AfAtG5+4A*qEY#c*B#GmK<srq#d:)4MV=Q!30(7DYp"rr6h?a@*0+7d9DQ4S6jrB
;qeW@HSd&naCob_gopYq`K;5bK!FSr)^es_K7FIFe0=h;h>HIb"<di?u'Sr[R);,Sk?:=FPa
U5or"ER@s'8Y`.8h-s'gHQ%ZDLPCUa/9ppi(C<:YC>4FQ^X]A2lRZH)pBt&\h2g%mPFrX^I?=
sETF^q/1c6;#HIk]Ace^1(qm^bpCDpoQ#8]AMd9(C%WrG83N1-]A#&KN-i>Ul,6us/U9>2RAV9K
6jRPf8_q*u.e7,taVOh3([mF5toetEOSmqMn)6Woj&%a>%`Ln_3F"9oKNn54HZR!2Se6)6f^
W[A(m=+#@nZt@"M$6se+Ag*@L*BAbM9@)uq>7R,<VRZh\gU?\p54k@KlkYYIW8fTld@r4I_%
s$O-:#pr3rLQr,*knaaQf's59Iub\#tjcSrt:"*hII"*'9>s+($<TDlM-_`co7pBb`@E0JK!
`,u_9pj"pP->C?WB/?RT6h1rCP\:!.G@ZLHaNemn\a$k8BHcqlZ[:7k4Ba[:j>r6=6BfPTWF
i5C"<L^2$M"Sn>$O&1fm2@;!p?us@U+(X_3E!^ohaUhG*3l:#cmDKnp0I@l!=H4G'/P?)n3<
seB8gjaoU/qcLl)2cS$U4qk0*adRlh%=9@%GB;PR/aYe)>#Af*$%XJ<p$kB+[i51rG\5$i?i
QIp^Z96c]AIna_m_dTH3H5#a17>oau;^pZ")Bja?WBB@Fe>^=R$6!U:)Ym>dpPQZ=g3@I^L>r
&QT-a[R*W,uqXgl`.0Z)eq8'j_Z8gWjN]A\qss>-RckL(u1p@[soJI?lg!9Dqg_>!EQ$iRXWX
@Gq?DcdZDF-OMGuDF:t$SUn>@);-LLAm*$sML@k3[.GJ3;lrR1+n\U9M&JpP4;d4#S96pRNL
E4[T%\T+93RY%17qu-4c(/Ce:k_(g1o%E'R*eLlZ,[[ShElEMUA5VB`aJ2KZ?dJgLn?<M-(&
OY[Sqhc_8>&('R"bP`NnMg:=aAQ'8hq*qok$X5nSX6_P^O&sH[<GKsK--N02<WDa80SRt:KW
f]Anc[P1.o2P,5kK^EmiMtUY%[g\*Ohl_)]A-XK[dq0sih4^4^4I=23?20pq.GcF9Tf0So6b$j
[/neoGGCj+8SLW%2^s,NSu%\<&6MOBrJT42[lNE1CuPn_Fr8rOj^J7.aZM*gVE:V0anS3&j4
?QK[1UiYK%[2!7U%)"H9.NH@;rOYK:-:HcE#Q0=?>`so4M_4J6d+!2i0eQ)\N2cdCNaN-GEr
$nXWQJn^0obehn2*;E8/=QWY/_<B]A(?TkB49G,,'g1e7A&IWih+t_7!]AK"VMTE@S;G>IEiq2
17\p=mC-s.P:[<;[G.>Aee2oa^p:@>EOWaK_"<'FQ\Yd%AoG98@gnHL$q?*'.;6Gd3<M"U,b
/.&uK!4JVI76Q^36AeTln=XfNbcYdQ8$1YE(UQfY<%BT1in'@)YdNNP\c/MP1n\99SabVbOM
N8iAV:!?K+]AjMY@Pgmnck<)oEf#jn9mepI8O4UTRe+.8?CK"MY0V9m&X<Z:q9/K4>146T#)t
^XV`JKUcT_.1tQ&mN*N`KBjgp^YHI?4,>W8Y`TteHd]Ag9Fs_KL@f1q)JJS6Y]AL_Bjr7I`A)t
<DmT!`r)JP$8+Kr/Jr"+j^>O$IfH:eBqRW<?rOjWS/6J^/GS_NYc('e0F>EF/S"NnsF)oIMD
Jh)L)NDtC`H$@#\i?`0')'WAR+UC-cWDKAud\mA_@^+0O0hoO`9,TPd%N+(iDLHR-]AXO2?Nl
>WsM8Zr#u0"b@arXiT`#P[L?&bK6"/n[=,Ak&PNF^>h=3TWc(SXW0GBL[(HP+*Z`'63.qB4e
NEohVB&F(XGKO^@4!:4,[kS8._"!qc[!F23fV4>&sr`sH!gEsuqJFjEA/%(Y5&N4MIL>f+!2
iK131/6\e@^CL"tfi19F\/?;.?g^JsddgVq[T[]AQX/FE;\8HDnb-VsC:MYrQ17c%`h8XQMDr
h/,PF[$/]A[S?9EZX$\CLa_iOYo>X2;'4]AiZ3Y?8"ibWh.4@Xeo0.6\'ag<=;4ULr6e\eg`W5
IT>QnC6MNW*@dsXh#h8"-=/*$rDjsRi.>Or+5)\F=@Bg$J)8l.4_=:$.DY49<$#E[F[RWKST
gil3,_%:<aG_cnQht,+dD5S3pFh_4`Zu"E5c'6e;6q*<$,l3fE%uD8bW7D*Pd4Jb.2/?31\@
c',u0QF27`'167s8/8e\*mla!/69=Pl\WAL^3Q/Ttk[@=AQ2k7A=a5-#<F.sl+Q_WR0F0TB:
cZ#fU?"?2Vh%[uK`/2Hl%Ams1gHfUA"kJ>N_jn`eqrO@CY<,aL<$UIFYdCtZO^4$=CG1">+K
l<P;gaDnk1@*J;78kG0?\5JiFLbV.TB#;M-`s(::i@tr!f^td5e",6*#0IUG&$19n*?R;8Ts
BbfS]A]A70%W]A*9T=%C@UpG*H,5jQ,*1-`+eT^p/l_%oIM=\F1^gQQT%bY/#XZ*O_>3mYWO,@l
Bk7bSC\Fc#cX`eBO3Hm4p<ar:<NsL1SQ9;[!o68YR^6Ec;I/=q`De;`mXAUn*)uI[kj#.6Hj
VuKLJ+`/%IhJ]Ae#`3GuCb@"Wq;dJWcUI]A&u7p=PI[eg3>BBRVO0eO7UFHQL/J[MYV@Z/&'Zl
4bVmNJ!8+c^EP^2KV_d@CR7EDO\P[^7HF`<B-ft0W?178W]A-,#\1'e$O.7B0OAZj+>Q`aBFA
DS>Oa`q?NONhY05Vb=@KtS3?@!f4Sm^aa4_=6+104u(RB2("Tq/_&p.\9TeCN.6ER`nBpfZ!
\bt%pl39eq%Sl(lY;Vt:[HB[-S`"e@:Pe-&J@p1m.ZG1,s-D;aN6qdJ-(M,Z0+"Y8VGhN3fJ
bJol(AE`A_k309AgS:no&7liDq2*S!igt'@blk:J_NSJ)AJ%'Qn2;E9BK"A(`?;NB1`aT,DZ
h^L6'-;?YfX9`WkU6B4L*U%'=7<gHr`/b^B\[C#kr'GV%XXK=3BnVpk1MScLp&5\%nbA^DEB
_+BQQd8ULjRHa),N$g##3hDErXGE/7RAk2oI_*Gqm>"Ktb@Cl]AOQ2'T;^>]A[muXj;d5lN0kJ
lO''c_L<eMliqLc7r2WCRm&:>r+\WQ&"brecRYTkVe$7S<T>B[Ae\Tm!X'WW]Anll3X(5`#2B
_qnGBU44fUH+@c4$]AYiR17s41[Z4=1JpKm9k.ZD0Fahn`tdi"oa4I>ULQ^,;YFnq]A;>?a5ZJ
>nZQI[T,VNlf_Wl,Fd<R)V#mb8Apup6n,U[C5OcX?CKD4A7A`r0&gi0Q"E1EHHjXDn5$(F\1
N+Bc^Qii!,%s'Oi7h5;g[=%YZq/UG`7l_t)#D#QIujGu7ik2j9))YK+N::Vi6`,rC8EPiQ96
U0s&@[*rN(`Pb\!I#$s+3Lss95,r:DG_!>8\o.BOH'u2.3<HU8AJe[<5M`)tc6>n\-4<Vf@I
psSC1t5B+:XO072H$r>b/),E[J&aWY<>>m1si'Vc?a9PX_q"N`;:U`Jc[-PHghir90\VB>I#
g<>_ToB,!A!\>fsO<:SH3jEC&?$"UTJo[Q-bTRJ0JZ')@k2RI/P\JY+`qO@fVmDmc:To#;1a
oQ[>%#PL3<ADL1V=-+jDs(H4%5L.8&3cE`GpQS[>*u^p8&uqaCO5,(s$2*gIa=IJ4b60LlLZ
AiOB[S:2*@_^`oQlsK]A(&(+`@_DrJB/8B&H(f`KGV>H'Zcos4R\6o`@Nk<2YbpEE1,bPB-<d
VhW521S+.3Ee)p#p3+j@9!qfo)_#TslqV^EoRH.>Y84g"FKq!jl1?J%ZGZG1lfWI;E)@J7)O
C0oc$"ZQ?b+/qdQ#DKD9/Wu9i2k)Y5H;ccaiq._;3D^]AR4:3`WLZ[?u$Qg_Im2?.o,0:<)r2
.]A*`pHN=,DPB^[a'd*L#R]A9j,QUm/LS9Jp-tL\ok&$<H6]A9DYClgK"UbQbV3=s)L.GZ=E2tf
t@aeR4T@5Ws#&CJ[dNW^:a)EqkYKICenk6lGkK/?XR62cFMQSH+3;,Xm(XS415]ABK;UG^p$N
+@=8uG+Gu3Z4R\c&_&(jOrq`9E.Vp1r[#*VDJMrja5D-?O!qh/nBPs?GP7%5j``dd/modp;C
Zrupuc`qf6VCGp\H3"i:dIhBcbo^sE2I#(Lc<BR07Hr6J:LKiaHlg:,$`S5PqkfK/68*/+^H
*4D]A9)dnqd,'F50X<uDGEE:IW3o!_Q'96L5l%VrO1;rr[A%Rku;O5HZDUU=Dm6shA(6aS,gD
*=j-igr<G)S)%9r5bn,FY,%ae/6V7Z:,^k&Iei"ZB(YX)+nBF/#rbSCGE07&sf]Ar)(0M;hQ"
@b\rf01SVZ:b/^o12]AP*\uEqrNu$1$>m2$/,ifBI^(A3_0iTqWSGW[@a)TU<rVd)[<mJ6Jl1
hf^W?O$2CUlWgS"FI<EFKAoKhIAOZjA4+WHXhOJMW!ZQO*)O9L3&Y(%R9Q,kjIkh?h7X!O#+
.s_*Kj;$`%0:aF!LqQX)+B@rVbUtj4#=FQf95B4HJT1Ha+ju;-icDrSZbEsuPoYE+Id"N8]Ag
[E`dL5$[I%o#B'GWb?jCZ=`NA:-2eOEFMUY[d3o*h08comKQ*uHUM_X^g-*fW4dM>d;BHMj?
^*6;'NSGl"hGrP]AZ*.Qo!5=2'O-ZBgV[WcffSl^B"WXjFXf?O,.qXL_[MninQlG(1@5kc2.5
>M(;P91mnD[E'q-[okr#kE;=oBW:UTD&r*kRoJDW[EnjHd/OU(n;BUOhTFIp'BPVk8LpamaL
OF8DMltDkt)5He<[2h^U+I-onU0a)C@%+NT8;9:#C`l`Y^X0M3U!AiXLJhrhBH[sLtu0)0#g
WBm%0ht<<XTC85l[e9M@fITjQ<UaMmd*rbW8La[)F@a1,PfpCqOcm1EKdV-I?1WeMmH1/i0`
%nA1V;FbC]A=)>k?)3[+XVZBe%>9gesLqKceYdU`H()Zl0<o8-.W(uWahna!Y=o(+EO?aI5?E
jLB<f"<qk6XT0)Ntjo'&,]AbfUck`LJ$qtBmD`HSJh[seUe\T>p9T1a_5dhHQQ6@,;G!(Y\8+
B>-1-R()a7#ML/j\-;KS5UW%X5&^oJ`%e2[m5QGeR#WYe6r?Zs-$UK6I]A?5Mo4-G]A'Q+:$AW
5UW#%TAHX$&lmSP\r,qVrQdaE\GGYEu-0Z3gTT*8/a_\PXL43<>d7\<4%7iD=."R]AW'37/27
U=5648)fdK59pdR]ATk^Jm/Nt$aB2h/Hb+IYp+2<4Nlj^mpghlP8i)S:<+W-.g"tKccF/q"Z9
SsB5W2mufe+_!pcgBNpob($kIlaH2q^"i6(^OFWj6dpQm$$E1cp8eTi,Q>[>k2$YWNQ?`S:Y
I"cj7o=Z%X1^lV`kYK?nj;r\mO1E3R>]AM3mRW,i10*f3:bF[.%nR)k-!\8q%f2m`&'`V>ka7
=]AD++,.jIU9pXA5X9'6WI9(.d!VmGQc^q@o_G0J\%(MLK1^j5GFA7Z_",Fd&4gGncZ/Ql^q*
mREBeY)/j%*6K9f=AeCK/B?oW-_qJo:S$)]Auo=N[HYEXgh1[83*'Z[p<r'c!J/5*O?,>W)MB
GO@<7>]AM<UCPEV'mqkWj/^Q&24hiq;G/i]A$E;bcdnVc]Ab!#kXH&0dFDrZPuX^BPnWO3%k3V,
2qAX@5Y>aqI=]AKSMhS[A"-?U94Nr-'l>I$^a$4MDf\N!mSr^/i4]AHWBO<VVleOp!Q$qFfh\0
XQobYs<'\,nbMFcWq__aZ7HCL=Dn]As*eq]ArL'A!*f-Hb#!i.HR0GV@i$/bk0?K^N`u!?e-Oh
$q@7q8OcU-=9W$<SpSS5Rjtj&$_u/=BtB:T8ST5$HKaG[tj]A.^&@uX)\dM@S]AaA@#@`j(7N<
WS*J%uRq(@VOnMQ*p^YcVT=6HPt)MJkJX7n,(^-bqr,8*g!*5WVDqeL?8^E\M9Gur6Jgg=+_
'9.oj\H.Pko-WE[Ij(LBK8/B@MeqEj^*t%i#OVE-E>ai=EV[7g;B6(a=53o58S>^,5D@:2$r
#JS^_p8OZOUHs<![G//l*%8BlVDt%Bt%q\'OmUhiTT*QWF7DG+a!M$A\9s^`_L^,Fkuu!?f(
oSB;$g=F8"l!lN.8Vl$MF8gK0;FW#sa9dG2U8DCk?8B=dg^]AiWpWMq?27F_hgj1a8SgI*qR<
0uj>I0PLK77#$]A!-uN4SK9Pg]AH9\e8^"-4c,;0riFnk9-P34D?n3"[bD1DbYjPj5h7jh?;Z5
7W5Y:'fG!Q"lasP9jT1/j]A-%l;q'4h3RqQ\14B]Am:IX4!0Bk(edVJH.]Aa=Wt"1AUn2KO4,U9
=Ta554Z4u3`PkdWYq:TUq,a-]AoBX'Ims=o[3<LDD`):;tQ6gn7(3I<^,c3U*P/Rri2Ci6b/$
HTJ.,?-QN4<36>Pf)iXZDmOV,_.+,Qq%3ElEN-=5n^:jZ*/*^N2[1GsWYPZleC(1rLe"n6N5
&ps2$4J;r>HYI%e7<g-,b]A7!3+%3%\mM#uEkeE=%`<'iaIMgmBDa3XYGe]AliUDY`mqp\ta~
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
<![CDATA[="   地区销售效果明细"]]></Attributes>
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
<BoundsAttr x="0" y="242" width="960" height="238"/>
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
<BoundsAttr x="126" y="0" width="140" height="29"/>
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
<BoundsAttr x="691" y="0" width="140" height="29"/>
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
<BoundsAttr x="266" y="0" width="148" height="29"/>
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
<BoundsAttr x="554" y="0" width="137" height="29"/>
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
<BoundsAttr x="414" y="0" width="140" height="29"/>
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
<BoundsAttr x="0" y="0" width="126" height="29"/>
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
<BoundsAttr x="831" y="0" width="129" height="29"/>
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
<Widget widgetName="chart1"/>
<Widget widgetName="report0"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="480"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="c07ce15c-8f8e-4e46-b19d-423c8f7d9ed9"/>
</TemplateIdAttMark>
</Form>
