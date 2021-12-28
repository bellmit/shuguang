<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="实时拥堵里程" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[拥堵情况,,.,,类型,,.,,拥堵里程]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String,java.lang.String">
<![CDATA[Ha7/GbP'e*h+O@mfZO#3n)UC;]Ah]AU@E#Na[6'ccK0^EHIP%W7152EdFl.gHD$6q/3:$,]A*PT
+R)h_Y)I_B'uq`^VX://h2U!!~
]]></RowData>
</TableData>
<TableData name="实时拥堵指数变化" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[日期,,.,,拥堵指数]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String">
<![CDATA[1GmN;[,a'n1ZL5IdPWe1ZODC9;faYA#UF'lAf:c>-=E,GL]AnAI"j*58!!~
]]></RowData>
</TableData>
</TableDataMap>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="true" isAdaptivePropertyAutoMatch="true" appearRefresh="false" promptWhenLeaveWithoutSubmit="false"/>
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
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-13948117"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-13948117"/>
<appFormBodyPadding class="com.fr.base.iofile.attr.FormBodyPaddingAttrMark">
<appFormBodyPadding interval="10">
<Margin top="10" left="10" bottom="10" right="10"/>
</appFormBodyPadding>
</appFormBodyPadding>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report2"/>
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
<WidgetName name="report2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-16711423" borderRadius="8" type="1" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[=\"   实时拥堵指数\"]]></O>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<Position pos="2"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16711423"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16711423"/>
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
<![CDATA[8496300,1152000,1152000,304800,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[720000,13220700,720000,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O t="CC">
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
<Plot class="com.fr.plugin.chart.gauge.VanChartGaugePlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr enable="false" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-4079159"/>
</Attr>
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
<Attr enable="true"/>
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
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="function(){ return this.category;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
<gaugeValueLabel class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-4079159"/>
</Attr>
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
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</gaugeValueLabel>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
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
<OColor colvalue="-10043736"/>
<OColor colvalue="-3688336"/>
<OColor colvalue="-2015425"/>
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
<VanChartGaugePlotAttr gaugeStyle="pointer_semi"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="true" hingeColor="-2960686" hingeBackgroundColor="-4079159" needleColor="-36291" paneBackgroundColor="-15132391" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=100" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=60" maxValue="=80" color="-11486721"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=60" color="-8598785"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=20" maxValue="=40" color="-5776129"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=20" color="-2888193"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</GaugeDetailStyle>
<gaugeAxis>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="80" foreground="-8482910"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=2"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<VanChartGaugeAxisAttr mainTickColor="-4539718" secTickColor="-1907998"/>
</gaugeAxis>
<VanChartRadius radiusType="fixed" radius="145"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="指数"]]></Attributes>
</O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=1.74]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="当前实时拥堵指数"]]></Attributes>
</O>
</Category>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</NormalReportDataDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
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
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0">
<O>
<![CDATA[ ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O>
<![CDATA[较上周同期下降3.3%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" cs="3" s="2">
<O>
<![CDATA[近30天最堵的一天：2017-12-25 周一 高峰拥堵指数1.64]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="3">
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border>
<Top color="-6710887"/>
<Bottom style="4" color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-4079159"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-16711423"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[V0ZcV'5a3s$7H0(LkmH""=XA6#grka2'?0s5a5`R8I`@i!eU%_8eaU19(/]A$/JJLcC602R-4
rSJhr(j<DL$Guh.&ZuE6JaRIC8r%kKQ]A=,Al!&kH:DOTV+9JY'+05K&RJtK&X-;bo,H]AR)k?
7'GM1'lM$PQ]AJ9b\&a>WO`bW2OR,0f/fm6J9lI;d<;^$)6"DG*Yl'r)QQl&t($7)qCSq%]AfY
<:0ki&sfVb>%T`r:`:r6lh2aEe3=fjl3r."l866rHiqsE+?ejPqq>1g>?q0'Ttm9.P#OD-+X
h*)rBG\hWF0Z/+/A\aJe)JS[$2.N)+Bd)*C$.+TPZg%8fudTnZiP$!3-%i!I(`oq*o9EpMO_
oE9:gEP-_.I0^mMT$a2^i>l?h7j:AA_p<hnAJ9%g/Mc%9,F1u1p_PTW9NjVS@,,TC_!:TrM;
GjW?l.k@^10R9NsStbS2XF[..\!(>5H&X-42PK++[$H\Jmu69f2JPTA1B5NQkUehQ4Z[^&ld
SHt,RDe*rl)7K>pXFU:\fWBrN^$'q4ML@QJ&j6M,7c`,f,kJC%mI!kDePpW6F9_%fjMO*6."
n:_&"me(bPp#QF$Mq@?$ApXp^lD72$;^kr!aqWLrbInqSC6qFN:ZorAHW/U9ft0on9?&PM19
49XpY5D[u;g&%G4ks*3XKS*\<0R`pN0hGgAea/nFbjj4GVo]A=`-OJdI#ia_UL=kcQnZ79"k^
]A^i*Zl:=?nPl4^PT"[C\TqS1R`poY-^Q)2%EWkNBRMj\[M(9j4P.m,6Ug_]AM%8DGq,mG&89,
Nij!f-+Dc0?nkR?I!n@ZmX[.K$7l.^$a??_c8PF9/:]A>X,NACUmpMek3p(Xjr<Z>PO*SmRul
1R*Z0@_e>mASK,DXQ2D!D^8q&?nkWW+AK<sVYmT">OFo1'^W1!I;><9OEfDsjXGIm:6U^D>m
PGp<SI@8XfU?o:S&,FEeWjV:.M'N-a%XLG[D>U[r!5;?AdsEE3iBXBUDg&peZ(Z_/)S'BM^:
%.I!<f+Hrq?N,f9S5!>?C/esKhUh^?o_PHb?cd]AS*@I-UGM<i$`)AW7Rf"d9aTD9p!se7Y]A.
!dB*bofh.NZ^GOueh]A+gLFOo_S5*sPGYG844a__^QtG%t7u62(c'W]At>O%2hRVjffND^S6o$
>BJ%jrpbN,X)$4ke9G1!0/q+BID5#I305kI8udSP)^rL5`htS<s?/27W$)k#a`ARaGh4\<Ic
,2FT6P9kkfH#?pRS]A!'=("t./\<mO2j_c1_j("11`G"<K4^DNB8e<&CF1Zf$@e!b7NNijR@4
O8XtI&#j_2-/+(^kYu3h;s!g0`E]Aa#n)TU6Qt-h?O"PhWmaHc7O^JCRa+PD6dXT'YfhMI55=
r;Y/F0K`!6opo_6bl2E\+]A>V]AM.gj6>?Dc1!a6-h"["$t=6O&)'\on4B!V_#sNSF%e=NNQu0
#+Kq-^+i?*_H1=t,9B!'2d*ic7ARSq@egHBe8;kq(AY(]AU]AnW;9GWcrFcG9(/A=b`XF^ma:Y
%MZJ94Vpe6eAZM3%"brgoj)0LNa1koC'l%M:[$[UqPORmaJJG=h4NCH6ETeE8eL.9jQJLc'>
d)mk*UocliVjJ4?Z9"g)E<i-488%lB&P:"CDY#^1DDVq%jC17o<Sn6X+MVVTGX\AO;WrmAEq
3Fo2ncDFs^EbB`oqc;9DU2$39i$$HdTh:QGBV+=>QtpGj&]A;'M"_MJm5eDb`+=t.9Q'8g2'j
T/T&Ll^0E;;2MNis8QE1kck,_W\2TRGXB3"kuEI3F^mbDi19%`G46(h-&Nk2Lo\:)22Fhs%i
qm:R;$5:$6+<,6*83$9cC+dC]AbVf<]A>:8+t0QIH\h`+6AhsrVT<Y=rfhDLMN\Xj@^j=!s+8?
Q#IYj'kJB2SFkG.Qg.m&a=UIO2U.O-S)I9.64I.@uFS'26or(8[0M_<\WGR*_"_B-m)p2HFf
*d?0R!XOmfschrd`Yfi,h@[S<h[[&,7BWTH53n+8@T,s4cGnaRGe'/-.g#33BQ#nuQJ)uN:=
<MT;1';sM;-`eA>:@AOr-i3m%(uPAe6iC$UZdJ9VA_8ZI*_Y:N)DF%fLIp'ARY!QP,l!dBs$
C_6:,jY+=:(Tq;QL3@-a'?Un`e.2`E;.4:2F86!!NW4ja!7C&p3?YuO61TL6$)%WalphbH'<
bPC.Fr-,*-_n$t.FFb1C5IsM.7_6:rh;m)W4c#Nq)5/6!5k<a2pjhDBm=+R*F#^(3o7?5M7"
+;H#@]AuF\oG*XaDS/p5n0GD,Q%iI<$E:fh,Pj#HJ8V"_uq)LRF+\FVJ^k!R-pJdoT3qi<M]Aq
3%7KU[T37^CX!R!Vi]A92>qjVu$cg"g4abflJ/upD;M+[ar?SfZ\n+XI6.0kcq/-Mgh7il/)O
Y]A5_8&l[a$L?O\1,_f/qeeS6(I@a'Ud@]Af/gqaQS6(er$j(qSiWpi*2]A?nYE$<7W29`QX/q.
PY@hn]AF(+ZrsS#Lm?'ud#b?&k1pg.mi=2Y#RT(\7LP&tO7\2mgsGe.oZ"9^T[Uk"lqV.dXgU
8bIn;72'#b3j'+;$o>I^1fUE\mAjo79<o@77nDJ,h>5Z!*-(B\VW>i'lX$B3bDkWT;Sj&]AP/
i2q04<.(fhCR2>O&c]A8=sp\jq?\pf*`EmXX^U,bu$98;Aij!\MfbMogNsJEE@TD\Z"^I-JM"
RmIo8_HM9!fs)G=.-.;2R7E3b!dos&=2TeFumaXjR,I*pmkp(Y:1)D#O4:6S5f,GWrO8eF&e
EGSq7f:bFSsqe5il/c$c(GQYf]A/$:)7UA:=C$1Kc7+Y[!2IDWWm7Gn`Ua'SQ]A$ke2%"!J9A=
lfOt+0t*EZ4UO?"T0<I6+=QMNa$$&FAJUSFS]A;N9YrMu)V+bbK,P!,T@HW3CdMQ:37UFF.>#
3>@Fd6D?pBB?K0A>jPH.;OHCUPqA5?4Zs,Nq`Q^M_ua9oh07!#<^GBN(")UHHNA]A=Vhd+8>.
rTSVIIG`RHBf]A`1Y[6=ugWk>X',hlpZI.[0[Z6*OG5=D+Q?*(a!EeGbRi$1t;(Se;#CX1,Ei
ehJNlX0rYkk"8o_6`/m*S,S!Mr:W?&&"FCh3og&L[3hnO4ql^qAnkFDSD;&]A<=!g4l.%e&8(
pm`(-W^kE>3R_I;=Z:?N2?Xs+9V>eM%+\a4A1('?<?iQa:/(YK+,.[qNnAniK%cJ9XI-sb8`
_i!*k`$<\n,K_&T>H-<3k0Q[@>gW`C#&!oPPCTPHANMp%!E%"Y8L?!d"!WZ3]AEIi4]A"07.<!
&sgaIG13tV+u@4'6"o76KUnIkp2uD;%!;R*rnCn9fk,C\8Gu\JZ&so2rWD`>XkR3(gDb6N)I
-uaac'di2e>\s2#5';l_]A&MYI>GgU`M%r5@58C[oE7nDqtQuEUaE0hTVn`l06LKg3O>D/:#j
E4%$;;7>!Is5Y^7?#C:_9.g[9H.N,2[psPY\b:S#:k,XN#Z*q$G1G8[?WDbN4]A\7\=h1hn(m
`6ROJOtBH4c&g6Np5[>^.%E%n#C7(eQie_S3dt*M7$o`kNqhFmhU:Or]A!"`n_)^Z]A_$S^5f;
<9!(Kp`/*sPNF1ZaZd-elc%_56>YffA1GE@AGhP11B&QaNJnEaK(9@$N*Q/1WDLoOE#H2jI9
/hAUj;1!^E"-ogWUt5*_jl*X/b/CmLB&LLoC7<)ILWUesomq6fT1)'FF9`[m>t=r37[/+351
O;;@Zi$tEK1E<_I*'1?t2_T(WbE0OY]A,e8nsW+O/t<P>jll)F4`G;0rfo>!_6/(.J>2Da?Re
[#^2do?PP9X+.G9J's5C>ej#Ra08,>Tr$HWZ="q9LZYuM'B?l+"p!o7/)9n!mGOIVa:P:0u]A
)?sTIDEAD0u[i'o$jP*EXEc0["FIq\io*>I4Zd?W(e"i-rVXnDD4/)WQAr,8s?Op96\jT`>K
jFSXNMPWuT,XMi)\fC,L=%TFIBIbW<]AL(B.sMW)qj!(P?Oi5<t![8Re]AocKU+hApV]AjI%VP6
g^3[,VQ+`QZ.)gY2kS-+6Q>,h.da\s>*-`XmFDd_89M.Z(Tm"R_M'V)nre[>]AtX1$*$+ZDIZ
2uemuU:iCgT5.^ukN@Sp)H,]A"l]A+H^g.l]Als4?4(S7gIH77q,uYF3!o2q5idsh)ED3UpK1+9
bYF@O2hgY:<KB(OX%e4.Ze*>BFC6^7rI=f::$iblbq;Zpn>77C%/OK\L<`'q@gM(jWhZ,7/%
Qn.NOlZ2H2cX=9i@A3J#.EV)V4ZZ6qMiH2h=5@qA_HqHRSjWM?.qjrqs7^CKWrJi+^-F&5fC
0$i,J]A_Cc$q&F]AA><A24+`]AnZ(H6Eo9Fh4:rdJOjjcc&er[CW<Y;c["m'0I.tZ-#Cbt@'m=R
/T/(I'ShbuoF64bUlc9c2;b*%11UIcC[-8/Hla8[[DT-Pd9fi=JKt#hIC_VUdSA;H2J<Ro*;
-LLYnW`*Z.3b?=l.S7+eY)]A]AORC7k^%^HJ53a&*pDoiCEB8hs,>>,j//XI!fY$AOl9R.JU>+
6\VF&+2se`TGuq-=Q;:rlhAih,JK058oYoPG6Y\t)KmT@p%unk>i,gZ&cUUYDCi^)qqs>m;K
!_*W4O!4]A*QM?C_A1<&9*oh]AkLn1,_5"jYQCJkE'TauOHGT"'KT(Hi*/LK+d5(`K5RKUMqk)
%_^hf[!9r,Cfh8CNX9p.GkTU:`1aYtqS6'ItmI:?]A_fT.7hea<U,eq?meNm\^3%3I0B,*SR>
$h6`ifs4$'n1nS6#6!!5H(CB>o!mEQ(<=:G5VJ%lIVA)fp0^Xnlp\d5UQL)8]AZt3TUo8_`pt
+#;K*NCXrTb,.I%L)d2rhjZErKRlHkeqJQj*9S%%H;b-6qW)I^\#DNp#7N784]AQjX[`N7q8Y
k[rI[kNf>+%/^O:*l781@h$DF%.JXMcZS]AEco6LZ/+dZ'#_L>QP"Jg1V7n^VeiIo4,fNu1eD
3qd(IVBNJ^%&EUa@Dh,R%KXo0/H7,]A88F&mYgJn$LHDF!^WXZ334#4Z2uFai"h<9'o$r^mHG
cH=8u/%?AM*pfh2#9U"0.PCIE(UT5F4/h,9tjk!Eg'g-@`,hs$Z7_tIb0SLeh3Q5UfCAQA+Y
[AB;!O-85n@I,4=U`VZgZW=1]AKraoqg?Z>FXZ%>3WbG1.lhUu]A0s3YPSK"iDXSmh/1eAu%%V
W&_hWs!CRb$$a1;SXC0a6I:ZFXZS%Ts=`20e)QCd+in8i,[XY[@\(R!EJ6$r^S:XP!&P3U1k
_/*CIY"EcdL\_O!d_oLncrI`*=n`D<"gOZGJGL%^fL?<QH-'\uu;T,5r`;OX%Ns_N(1H<cO\
I/bYA^bE.'*8'BQ'%E2"Z93mXo,*I]AOG_R%2pf=*U_[0k9eMJU$ArH7J&[6]AI7Q!^P_VY>31
[)?Z`Il.hC.Unm8&n?\h3"2#Q2<9Ie6VE5%KBh]A>icnDO2CN1g18BHq+me`HCR=eu1-/eg*^
=/o[r*WNVs^3_F;6!-'qVWFeTL+TY22/(N`#6,T.e#f8i3Uj]AT+H.i/-)[hcj$s)h(:M.\Ro
JiN[`)04BHdT0,\-+3HO"Ukc,?6TW6$P7]AE*FU;g!^V4No9rFI6BH-P<iH)on/]AEU2F&XT[$
s&@mK4?S8HDh_57p7Hmj:?W3no&S(&edMpL;ZWA!PVaB^bYJB4laW<\)r)h*fZeKc"W\E*Yb
?L;h?tU#ZRgD2pX0D$C>8]A%eE%>1<b>R3)Yu5iVM\BdMAOE;bSMIL)6D;RCjc+L,[Q>)s3m"
O9NPpVF9*g`1BQOo"fR?)X<%\9j=Q^X+Zn2/Y`?m#fJ'2Z(.V\+P3Jb)B:2<Yg$EX%=A**;c
F&L;.Kaeq/><OVijl@ep@d:s2TV5RBNZ3D7HOX(K/B)?mgDIb.DR;E:#UL>2MfqQsE6aE7$h
m&eLg(2e=WTnq2+6dkE&STR_%E1E5-ikZND4<7F-uQd-m5%l?d<[p)dWOp/8iNHEP7@>^WJl
0c"h$dQ"NOT:^%U8_YeAqBPGE;1/n4jF7bRW2t!<p>N70`I>E+G-8'^bc9'57j:!-^3gpM'B
t8X%b*.11k7cI*JJmi@_DM?H.f_+*La1%E5<;E']AHFnS8k4-[=ML4S\+&KqMTdg1/B)8fi+S
`5Sq070%3W6M`.KHr\#["l^j=+dol=jU^HYYWdNr0s/=^m9[,(!<c#c8HppnX+iJ0I%Y(/HQ
Qmc,>JkOKE[BY<dDb1fi1$dMJa5]AfqDt*8*9#THc]AK8%?!g31>_"Foq_&$,&e%7:<<kNkKJ9
aDM*1Cfoo'BZ_os),rV9uQ*<EZ,P`Z>+?(Tp'\-afgF9UOgHX4).q`nE2t6-5Vk0f*f,'fE$
'YhNdl@K6]A?<PGDPNZ"+*h+<:trPLclZS*:oX\L+:'a5<2gWGK1_U;bP=j:IHq^Q>F^Wp_R\
i>?(iC%bbCGd"Ro2sbjJCmq905/ir9p`*Tnt9;=,>8(b]AX)-nP#tRqe4sM&9>.[reC*GInNn
FNiq\#))@[Lc4`,XDbHjYA]A/lit5>uF6j4H3I^<0/1TOcWI\0FoO4/p\TkPS.oBApO%<`pLC
I;[->WNogF-0M9=B!p`?5c[C.mlMi-+WCW5^&XEiLB4jt&6QI(esu]A*b\:7SEGc%n<Dju0hK
9u(MS5Ji:0Z?\4IV^7gYX?SXKK:Q^K.0Cnr(aS79n;^qQ?p.GQr>6Q?/^55$G+BQ(=gY44a+
S;l?F+%&[V,/.IYQ#afOjF5UFTh&2KEfAZ=T8`^a'ls[Lm<]A@?-D;d/9^@8^/8`tgC!W;8=1
nRlICo;%8b;ps>X/dPJ=*`LO@Vr*g@B5X1\"M%#1dWJU4rOF99q8Y1rX;>I"m2:S]A^#hsS5H
jG`i%7"P,F0%mi+hMP<Qg5VDBp\cZ02E(esO8M^P3WNQMO8hcV^>G@&#@4O"]A=j#j1t8>Ip.
Ll<=MMe%48UaAY>XQGW9UiL3Wl:_0/9p_BpU=q[S"`i-JRFRNKCQ9G$$HNIj7+F;`A\;9Der
t0WF-j(e0rTP=eJnt`%lMRsHZ_':O4j3XE3%@o<MZ"TKu\tI\n>/LISW1B6+X'Y_E:?Q&<%E
J@Vogk'(4=`aGb90:5Jl!g3Dm:npWYBUY$hRI&m;qcGJG^=st.qISJCZDgpdWrG_j*s!;CDb
H%uBCH$OmBef7rf/sn*$k*TW^OKq'hYL5ZiO.g\\CQLq9<kjk^OKK034-@BBul;NlfRVFS<C
gYeMqr^G_E(9!_P>7*nMuA5((d4GonFL%kUl5U`f.+V#i7*lHoH8bpDgEjCQs2lc*4!L3T3`
%`3P4[7NPVrBFuKR*/>WTEssLMQ(A4"lR2p8Z\od%6_JBbB*4*d0lFR[FZ5'NCGY?0Fpq'jF
_+j4'F,dR9>OiDgZ9d$.?JjZnuJ/D5kqEA=isYKVjV"T7O+&!TW=Yb#&*$hHntg+M8$0["PY
:@D*qNp"]AQ.mQ)j?SJui';`jk.3@<*X3O1F<'q"PNK*,$&UM)H:pD]A:tET]A,hCF$Z\EH5JHJ
,f=[2$+5f(Yk`7eP:W5DSXYCPj[ob<O)CSor,(dp4dM`"ff<P@VpMkeU:nD.;"HiqrPq'Gj5
OK<#^1BNCrFfjjd\p>tUHi_=]A=PI7_]ACNLg:)m,PA%'jp':cgj/`hDUd%p76UIDaA-$']Ai`k
;458^e=N.1;?r3UAch\"Wps<p4!0tO'Fpl>7a";&=Rm=rCR\u3AH;N!;VPLaq89m"+='aZI5
^uMioPSIHJqtUV+8X]AK*/Ouo)f,T)-Dgudk"l8=)NK\;.aR-=_gh&la1E*(i$J!9,-3Pr\_+
L1bX@RXaYTJBbIV%#W_F_dV^2N)are+rbsnTEa`F?/"RZ^+O$jN/pNj.qm`.:,r>X2OBos7`
)bOC4\q1n.4uQCPn28S(jmHt[B]A'FMP:&"dKd#CV/#EW0o-cd:/Ke]AT8hp#gB=";Wa46H%'p
RV7SI89al9&H)=qclTSNAE%D,.m;?@/K5,F7A5EV#p@Fqr0<$KGPSM0>MF:tGB]A9I?k8GBjt
*dSI.SPk@"9T\Zi#]A;Edob>a@F[\LKlWVnW?L3\NYSg58=SOZ4gGPPjR4tVR.Nk;P_k`4n@o
B1+QCp[^.ef,R[JPe*gAT2a+@.uGO)hNa3U8ROf,lRQ1U.8!QD/E^_EKj86Z_Jf8Zsa%/8i&
S?BS&Ud3!UQF`b.?*cBt+d`5>aMW9me_$ckrZY1@q<b^$6i/?@-!Y.7tlrl!CXclE]A_g?0\a
3QE5ZdE<3h?J`hAf7h&34kLLna1Z2a3`S%>LXJYYZH`.A,>t1F_almBD6DCm%Yetm^p*mFSf
`Yg-JWV'`Xa0%NKIF51mLZ;0hh`X0ic!`8,P4iSZGFa^8%j"1JRMI-+Z#EF5\5Z[0M,MeQ:R
45'6bNh;qdNcfi,ksshDX_a4s)T[X1)NilN>:#KW"n$)3Fb3phnHA7h+[-k,0g$GsZR7Be^h
_;:D!*MX3_X:RQcM^]A#_I/nnqN=C&W,U'f\U#r(!#7;A6aBtq_7acRQ8"1iCV>#;H#ksPHXP
XASfAjNYEka@8Z6ojmR.`f\UGN8XMsn,[K[C7UAn)Q8QV:\&BL<>pUJsOpT-gf?/0D]Anr^"U
VecDF4l!F@s^o]A@<V;.<NFNILiEk_:OVk1B;fK;!LS.Qit:b54>(WT7:rVQn$,nn<%B.QelF
(3Bc3"PYal/hX@-hme>S6ccA5b`mQS#/<'cc7PU'8hL3l>T[c:J]Aq/f:#q0()b%3Z0gSu]Aaq
c>n<*5"taqh&jq$N?WdL%CgGE,k/1gUs=or\d3NILhCl0Lo7^t0nakHB#<K/dpEpRVa(SLq,
>Ar/?Hl"po\&6Dnt=UZ!4pqmF%/aZRdnuZZM'E`H-D-d`+CDrfC9ipk,8^+a#YFDeL/hB2^4
u%H5aY*RE>'2I-gO*0RdF++I?EkIK"adDm1dj.`3#nB'4[_&Q-LDPQ0#$n:ql"0nYB-'>\sf
B5AjbBV8rQ?c'qg3mB1SR;9bd4+6`Q\+5faY0hn\=M<<XSGq/6$J\K.B!]A0)8MR35J6L=12i
[Iq+#tbLni4@P1sH"bZ&[eC3Egsf*5Kk<--K"/757dNX9Na^n^`X5oU`;N)95jQ$NB-IFI<)
kTP,;T=emPSSe=L+<7Ng6]A/!_);un]ABo5rC+X]Ac>3R=R8U4%\dX@qE+2jLt'lf?W'_OG,?(F
Z^7@mo&j?'pG"O3k45Ea[A0kYY`r0O^*am"[gr!fZ[TJL2I(3OL;;+&u0^dX)h$Pjfua5hl:
VeSM)YfY4X;+Hl1L+M?U4NPR>`e]An9ed7=70"t2V4B9Bo(cT4kuT9>7`S[%;Km,Z*(]A)Apm3
oNN<$2NPMoAi5Pm>RrAcPf&ZmgL8tYs9hi\lX7*f9%,-3iCjR.9FQ)q56MJZcca(PiLoCB<L
#=W*WoK9iS#D<0'WDXH$PDcN`O*hWpH3Scs]A=DespY@M[>e#Y`/ufeMs/IXf2f0\WqFBX#]A>
Gbh[H98-Fq-PP)9DHa0ZJ(I-7d0$9B1=)dgg7=0Uq*?a@gH[4dV0lWO[;Q5Fl:KR1DHsMSak
EE$\?rsuZBEGso#IQ$]As6.WPGt%r.UOkE_?p7An`ctG1.48S(O;5201"EB1,_\,7Q:J\>_'*
N<d`o,(85EVRf.2CRk9hHJ4fV#cQG&_oIN-CEGQ!#!^R()JKeI-pb^9g`B_b`o>]Ajk1?)9h+
8R<uhX1]A'+M(%n6d'),@ctF'S_QkOrm)T((c=!uPErFC>LJ$G%6gE/BT'!E>NSXd&b9-j\2#
FY@"7#hP!YSE(Gq3ic_#GE'FbW)V*cFM1_rF\ci`/mDb%jrIh37'ccp$4rVmuk*6$dQHCUtE
W(Qh.5WY>:?[7fH5bs_EZDPOAaU0tsp@tOuB6d=O?NKfAF0N6eYZ%QET-uYT)VF>a:T2f-g+
3bF;A$VDE&&efI2s5.Uje8DOo0&<UJ+-?'4h<qbASmo6gliEs16[%Bm:sf?,q:Te3Nna?CqE
S/H2Pk[f:T[HT0Ye\cH:!?`j+X^\MN<,2_sQNY6#+:UpWoZ2LVD7Q15EWYp4Rg<"mX3!kId$
R5Q3RZQe?+-pgAnkp95/7n5Q$l.#_Y2^O&'sDBh0aA"q"2/<,pIC6s$'4bi8<M*slZdtK<]A+
K,.S&=F"THY$UVc29H7la^]AS9c_a.,<ah"&u%=iClj-').*C_qFf.UJ(TRPan,k5D;sMFkU7
?UCJ,[#WNbB",?qnkN?L&T,_-HI;J<!jK%q+$/i-bko)rJKmsCm.7&79!B,[a))WWgarT:`p
G7Wg."04>7]A<4mC:lQ@*5enAoZ)]A4[l&g/`FW:ROr(R"]A*TNa4>ZcBE)O)",*n1Z[LAZgUm"
:j7gZ@.7k_L8Rn(=.m"aE@nnZi7=S.O:5:T*kGJ&g\LmNN8?.en;$3p^F6JG-"]AO4HSluc-`
T+=rRB_\!>FYRcL,5#NNfj%cgh21/dh!fJ/=+/,S`&W6?AQ@mNr1'bprI`0*t[hrEq\*8^p0
GYlnPb;b.(t0iR^IT>/lkFnX$^%ApCqE<,Q;AdY\&q;PIi7KAX0]A6o)q8Z@5=!d["Et"WZph
qX#$o$]AeA=Sl06[re((IGbtEn0TCjDF=G8><Z\.e>]A]A[;.ulNT#qCi)93R/ALHsc=M1YbT/n
*q3PimmPFehaLo3/cSJ%m;dmnL(f73]A'^8fg==(f5C4mQ(m>HJ'nS`PUm>_5?l`j$m"`Qn^-
gnY9AN$%!2\hl<*Hompr,)W6\9+!:ge)E6[HT5aG9W)$Ebcn\OP$R5E*n[71UPf0:$a8I#/d
j1?BW\l$LOOh.iPsLRc%nBoJHnpM:bCRli?4Chf6as!C,@XdbBTDddLGKlS*^7M18R]AfE;e!
OiKRj>Q:=u$AT?40T]Aqo3),_+_7>u_P2<Q'0YrP[odFe,=j@"h'<npE`bA8,EV9HKa,;l3;^
P)@F'0/9Hmi.fU+/`/Bu[5WP\p7.?*lP`4D,hod9d74gIYQjI%;+tmgGhN7C0sC:/?IUNJ(K
R//F*]A61I0GMnBqiA2`Xmnt'@pPTGo+I"hHoP""1YsQs&b2,/'1h%lo+LJ1?LjSS1"aO!Y:]A
qV)@7C9$9XG=NBYU:0k18iOZ2?j7fbheB360@08G6S;lY'>FoGf\D^X(_I;iAm$^H.r7nYY7
1!YDo4STB'To[hY&g^5T-p(pfe=TDX&u*?W/$a.<"'21&GF2Ye&?COg1i!n.bll`?'i0=fT@
;'*n8(:gZ)ac0Rk<^MATFD=.8;WrTsVCh3N84aOX6oGl)539<._d;U+r)Vh7o`#<PM(h;!B3
`&VFs&Z;qm_c8Z^?1HF]AMT$N13k>=J/k7)$^,gDrCW/F).dAhk+XFC7g;=:t%BI\A.indQ=p
b+unE+NRAj%qX=XIom/X/j$Nk;cP9"=d"^F&<kGoUXukGs>JoJ6VD&1W`I3.>K4!u,#\3E$X
qR0;9?gUJ8'V08u<$=H2I]A`sc@jG_fsX3>l'QT`3]AI\\j1AROG"j]ACU7=^gd!VeYR?hU\f%0
&5*U9!*MLVi-oc(_gGdc=92s0s;6[;r&DAFnb:_i(MYdm1U<g-ms&lHUc3odp7\HkQ(9-E91
iJ>"l]A'oJjEs;1lu$i_D/O&65.SMs[Ak*I<Q'NqN8fgJ^[%r^1ChCA4=JpK+:SpU,?danpPr
G]AdYGVcQb&e/-'s3mbC7+LK:2;;+ZO#lK^@4MI'ae#Z=pjG%b26[2n34*M"'KhA`dd+nI"m:
Ru&':\'Dls<;a8cFbM`[Km%.I(F:3j%:i<<Eh[qgUC,JD0<f*,ek`B1miFRl%Vk10]AhP@neh
M%G%BVm+_QGQ.uhoh0nHf"fq=VBMdGVj/DGopU=3=+r,"j@UWS(jSkQK/T*Xs3I(]A:5.J\P:
-/@T55e]AYHaE,olm.sk>'OoX1[DVq#9eQZ`sf=a--+\W9_u<Q_S`ZN@HmP.M'k)DD$ZM!q0o
i]A9k'4)piTq--"1-GUGM\'p[_eC(+<"[N^>ZAntG5i`Z%FVC.5cGb$Rb"05X[fqa(5oX8.;F
=R0l:"Noc;\5cW7dt:BND+kaP+(TpBQLH2Elq7<38<\KEiH;^p"^F&>UFGHr*RZ.fF$[9L5m
Pkur<Y@TcYS,&VKc'Vj:Dj5aliIH%WY/u)4)[qY-q<2?+8TWs#M[:*%'XJJ@GrW6'^1@J^[+
8]AX(Z.>UG8Hq%i2rhlNhJ_'.W3qSD]AnnSc]A#o3]AcO$rrEbZ5!O/OBbZPXak[]A!LS^N!:@/6\
tdN8lM+\Z;"2iTRUC@r4^.QY_Fo!;f-0U5V;LJq$7+t@66<,fE=g_G4fIbCc/F=7.6V<nA<<
i67E>ebS?\GQDQFiIYEB9Djh8>bMT`lmk]A<pt?2ch1cXLArCd!O#!Yq@fHD/9Md7SEZjrjLU
MR8^_*+.<7JP[*OOBTkE.*JsU0RPU(Y4"*^jDC7>6jAMXF5)i)`!Vc1aE19"_*uuhX\?2"d=
QmdS)XU0VTB=Y.rY4j?#>$Q#u:=p*ku>E[D3qrBG&i]AV6/N:R/-X-e'PRk4/M%Hk+lq'%k]A$
APuo8BC[jTm`;LYMKqsNmq(@ZW1RR$SY#[o8>>j/@dDA>o[Sf1Ei+_G/`oWu;*/\GhB3'^iU
3DY*lE\6Hq*uESS7O;jZp=f*Qge`TZ-tah6u`PS&*$Un6/WDVpF__^k@L.rUW.r\*riYZ:/C
TA078:Gg3G_!hmY5)Xl[/<WN/`n?U7mia5p*a[[]A#"3U5\JWS.Y576lrF0P@cBUB?7i`DU&<
A'H00*_\eX.\VF,P[.Rt*/8BqK0Ej^3P+a'H`r<(]A"-6_EP51Af5pV5Aa7G1p+4VK(!ef=U.
fN:TfX"HYGs&I>>b`k&le[22tpCH*q'[-oers)HM#\Hqlt1;JnEm"Nb#3ojS5ru`>@R`[-I_
X&;MBlK=fa@L3o<Qpg19pfs+i$*pVn$]Aek\qJt:dTefa?OZ+TG%>d4A'<KYXOf;5C(ADGsY`
)me!=-`9/o,kDRT45]AX<J[nOA;i]A=.#1r2?+'KY@[gQWJ4>#9f#OuL1s?;:s5qLO,M,-(Pe1
6r19qNo.&X>mGbLBjs$Bd:Mjr9[9L.3DS\6.l(t6Y;M:f%O]A&Htbd02EE-!'#9h"rAoQ1hrD
hT:--g,j-5q6Or/S.Oa`@Sd%l^N\u;EU]AjWNK7H=0AVPXH@:ue3AXYD_S.drZp;0;eYX7ZX^
QY@bZ,`:ah#`.;.EOe)fnn9j(u^$Calu%boUE[aQdMah&!Qh^&_(7f/qL%mb7KK$=TqQAlcc
R^GaAZK@VoM@<nBUoEht$Fp"#OH2=-cMH#NtnTpC^BOm5tIr?H^,?*dq[hl=35-/t.BLsO&%
VJc.I'k4_VCSO/r;eL^bdrU<j-?1hq]ATWUYPgT3POXU2[l9A[nF:@eC5#o/@ski7Tbhr'a"d
35%'>Xf\)+nNLX(@b+(0K"Gg#0EQ%D0#Y-Nqm\V*.M=hMA_d0*skQh$t,ke(:/7eN#=WmS:A
N#'uUIe?`S9Bl<m?%.j<X0hD&^4iB1BF"dVPW@C=R8(,\O5NL=0kn9%\'hJs[[ib[ptq1Bn%
TL4IJK#:Y_suGodF]AS+7eofh5C,^p(j?\nW<eIK@PHqGn+5FAZt"%rBVYDI5dVOQDH32;T.#
^GOm&Ls5YEVh!;fWTI6HAdsDr4/Q/-i\;Q4:[!s-`+Ca#ZW.Hk):R8t[83d/KP(rYDM\\*;c
kc`e`P_`><)AB50n`E#kBb\9;V&*(8r>,pr)3;b`6lU^;Gc0HXG1[93;"=;iU\aHgVY0-KRl
nKF8Y?]AHdP(mT=gEcVr.`$65]Ah(TE":tb;!e=EBBE$:^+CaF@%DVh,=Z\!!Vf)_KI`kNg^2>
EC961fU+lErb#B(E+KrZ1EUZ;DBe[e8uAXUDk)KBG.nX$e'(&`4+10Wp]AU9HQN:1pqN!t<:P
=+ep&*uL`ADUj1;<Va[fbF`O%?e)l$nci6^`J,]A3\a!"2kMPj<WnsO!OfGhIet8@KMkC4.Z<
tTtfpW<+mOj5_#-#Z+>aA0grTu=S.G$Rd)Hb'o92"eB,s0-Q"LiU;6W?=NZU@G>lr'[6R)&b
]AK_Jnt/&7R\C"l3AgR#%Q@[o0!&*)3n,OnZoY.Dr7JU\a.N,+h/35\!2?Yj1&TLmB3eV8^9]A
`r]A>EV]A^?56S+@BQ[g_&#f[3<_6;B__dR`p<]AlNs&9@**"caGKn!JTa;;[6O,>^T&_-#%i5Y
?]A:d*@C,4[<.Jf6^0/MH:O[_`(Sfr'kPk@)q,8G&Ikis+.!%/A.HX$6f1J9d:X/g,qa%5DJB
TD@/i<U[RL.+Z]A!b]A#-m$4--kU`OHnG=sUl!N0cuS_bebL$(8`aLC0jVEa\<CqJVX6UImF[P
H0g4lW5?\$Js-hLcdoW:`fh^GKI1#aj""pu&ns@r0mCIL;_RV@s0L0bYe%GM@N2k`>s!Y.Th
7lj[\%!+J;X5V&s6g7AVKu-G8K-;;N@.>,Iq;SJZG<gd7D\q[J;93"mfU3DBHVG8ErX]A8:'%
(q:&_IpJ(c-\MWWfYH>A$-\q2X^hhb1FaHK.C49kKRMZ)(gF4iHGPlJ+V'*4_dcU*9LkNiAu
:/CpTl4:8jQ`3(Rq0r~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="370" height="164"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_report2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   实时拥堵指数"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_report2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   实时拥堵指数"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</title>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
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
<![CDATA[1440000,5867400,1152000,1152000,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[720000,12954000,720000,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[实时拥堵指数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O t="CC">
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
<Plot class="com.fr.plugin.chart.gauge.VanChartGaugePlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr enable="false" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
</Attr>
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
<Attr enable="true"/>
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
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
<gaugeValueLabel class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="黑体" style="0" size="80" foreground="-13421773"/>
</Attr>
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
<Attr enable="false"/>
</AttrTooltipChangedValueFormat>
</changedValue>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</gaugeValueLabel>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
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
<OColor colvalue="-10043736"/>
<OColor colvalue="-3688336"/>
<OColor colvalue="-2015425"/>
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
<VanChartGaugePlotAttr gaugeStyle="pointer_semi"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="true" hingeColor="-10130579" hingeBackgroundColor="-2297095" needleColor="-1740454" paneBackgroundColor="-197380" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=100" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=60" maxValue="=80" color="-11486721"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=60" color="-8598785"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=20" maxValue="=40" color="-5776129"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=20" color="-2888193"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</GaugeDetailStyle>
<gaugeAxis>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="64" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=2"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<VanChartGaugeAxisAttr mainTickColor="-4539718" secTickColor="-1907998"/>
</gaugeAxis>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="指数"]]></Attributes>
</O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=1.74]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="当前实时拥堵指数"]]></Attributes>
</O>
</Category>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</NormalReportDataDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
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
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1">
<O>
<![CDATA[ ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="2">
<O>
<![CDATA[较上周同期下降3.3%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="3">
<O>
<![CDATA[近30天最堵的一天：2017-12-25 周一 高峰拥堵指数1.64]]></O>
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="黑体" style="0" size="80" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-1118482"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="黑体" style="0" size="80" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="663" width="370" height="200"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart2"/>
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
<WidgetName name="chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-16711423" borderRadius="8" type="1" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[=\"  实时拥堵指数变化（近七天）\"]]></O>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<Position pos="2"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16711423"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16711423"/>
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
<![CDATA[实时拥堵指数变化(近七天)]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="112" foreground="-1"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
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
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
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
<OColor colvalue="-35328"/>
<OColor colvalue="-11184811"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-5197648"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-5197648"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-11841939" lineColor="-11841939"/>
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
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="40.0" categoryIntervalPercent="35.0" fixedWidth="true" columnWidth="13" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[实时拥堵指数变化]]></Name>
</TableData>
<CategoryName value="日期"/>
<ChartSummaryColumn name="拥堵指数" function="com.fr.data.util.function.NoneFunction" customName="拥堵指数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
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
<BoundsAttr x="0" y="36" width="370" height="197"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="  实时拥堵指数变化（近七天）"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="  实时拥堵指数变化（近七天）"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
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
<![CDATA[实时拥堵指数变化(近七天)]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="112" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
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
<OColor colvalue="-3018827"/>
<OColor colvalue="-11184811"/>
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
<newLineColor mainGridColor="-3881788" lineColor="-8355712"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-8355712"/>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-3881788" lineColor="-8355712"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="黑体" style="0" size="80" foreground="-8355712"/>
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
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="false" columnWidth="0" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[实时拥堵指数变化]]></Name>
</TableData>
<CategoryName value="日期"/>
<ChartSummaryColumn name="拥堵指数" function="com.fr.data.util.function.NoneFunction" customName="拥堵指数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
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
<BoundsAttr x="0" y="863" width="370" height="233"/>
</Widget>
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
<border style="1" color="-16711423" borderRadius="8" type="1" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[=\"   实时拥堵里程\"]]></O>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<Position pos="2"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16711423"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16711423"/>
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
<![CDATA[实时拥堵里程]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="112" foreground="-1"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
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
<FRFont name="微软雅黑" style="0" size="96" foreground="-4079159"/>
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
<OColor colvalue="-35328"/>
<OColor colvalue="-5682"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-3355444"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-8355712"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-4079159"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-3355444"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-8355712"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="96" foreground="-3355444"/>
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
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="40.0" categoryIntervalPercent="35.0" fixedWidth="true" columnWidth="13" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="类型" valueName="拥堵里程" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[实时拥堵里程]]></Name>
</TableData>
<CategoryName value="拥堵情况"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
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
<BoundsAttr x="0" y="36" width="370" height="185"/>
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
<![CDATA[="   实时拥堵里程"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   实时拥堵里程"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
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
<![CDATA[实时拥堵里程]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="112" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr position="1" visible="true"/>
<FRFont name="微软雅黑" style="0" size="80" foreground="-10066330"/>
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
<OColor colvalue="-1087417"/>
<OColor colvalue="-3026479"/>
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
<newLineColor lineColor="-8355712"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-8355712"/>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-8355712"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-10066330"/>
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
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="false" columnWidth="0" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="类型" valueName="拥堵里程" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[实时拥堵里程]]></Name>
</TableData>
<CategoryName value="拥堵情况"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
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
<BoundsAttr x="0" y="221" width="370" height="221"/>
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
<border style="1" color="-16711423" borderRadius="8" type="1" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[=\"   实时道路拥堵指数\"]]></O>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<Position pos="2"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16711423"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16711423"/>
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
<![CDATA[266700,1562100,152400,1943100,1943100,1943100,190500,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[720000,7200900,533400,2247900,2286000,381000,1866900,1409700,685800,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" cs="3" s="1">
<O>
<![CDATA[高速/快速路]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="1" cs="3" s="1">
<O>
<![CDATA[主干路]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" cs="7" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="3">
<O>
<![CDATA[拥堵指数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" cs="3" s="4">
<O t="BigDecimal">
<![CDATA[1.14]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="3" cs="3" s="4">
<O t="BigDecimal">
<![CDATA[1.91]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="5">
<O>
<![CDATA[平均速度]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" cs="3" s="6">
<O>
<![CDATA[71.56km/h]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" cs="3" s="6">
<O>
<![CDATA[27.55km/h]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="7">
<O>
<![CDATA[拥堵指数较上周同期]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="8">
<O>
<![CDATA[+]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="9">
<O>
<![CDATA[5.6%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="11">
<O>
<![CDATA[-]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="12">
<O>
<![CDATA[3.5%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="5" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="5">
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[/\2<Pe,T=F5K8dP=<S=JhKg^=5U7XIp!KYnD`T<#Alt;X.&T)LTisHX?CR0SG]AtdiW0p"'do
K3eVbJ%,QoPT"iV2X7s1@*s^$N2ZoC]Ac!kB/qjqdV>Z3IFYMNU[nm"JY;R5Bead6kp/;CT54
eT>r8ci"1]AMKSTH]AUN+9+[h]AWp*,F^iRiWu'2qgl9cX[U9dm!ceHrr"1%'2JT9\]A(h,G<BFm
'[q<rqfne6UTJ\,%LP:do,AmOHO.GHg^-nKLa(TRT;Y"F*tiE@me\[Okl$"f2ogU6^R),%U:
J$E5=/`-gi'ZjX(-8&%1MKbFU-^'mj)BmTfRfr[F$(35YL,m.a%Gl"/-[qj-?^M`i\.]AT]AKj
><fpl8)<=4Ik.[UUgPS,I]A82@HU]A6jM2cpE&^2`hqhNu$[C%Z[-Ns)p9g0B\Qp;b8epd'2^3
qF+cL2,dle69^cCC\\W*nl]APtgATPKHQf$-)[+q^P@nrR4Y-Sj%g]A#2"`LMR)*Ff?95qr<at
aa_&R^P[mGeRF@;kXcZ*H?KtXL.Jibe\)2CDijo)gT?Fs^X2eH"'8K\&J$N.A[6,6n]AlAbcZ
_'J+PNJHoes>\er9Ar;a7DfKG_IU:[jh(Mr*f`U^KkSF>++IH,E'8^]At>@KFt9qYrCZ302c9
E-l"'1ZWJ_gTD*'f3eW,Y??o1;!QbI$TB[-H[/As2'Ilc11mJ]A6/lT)F:2`"MMQZBibW)^DR
U>7K4RIDXi_3VjaV7L)Lq1?B&@n+2B=!YNQ#W\LUmHJ;d<P3Q)(<nklO_d6*/U'7@FB)b@U+
dD?8_AYs>ak6l%B!Jb>?TPK>]A?X-7Em.=V-cc#pNS*pK]AL!K<o45,JKe6r\8uB(/"u"0p[Up
8@X!Z-6nphdT[RG)RddFt[$0>:G[>Gd-c6PR>K;*3D-Mk$,A+raF/$CPbh;%>-@Us%V`$\62
Cid'Qs8^llqTLmd_=`:Wp-ZGMHe6WE4r\NqNI2ZXC*?ihJK;lF%H:7R*r^%mMK2Hg=DZhX!^
M!HsD&d&n/o8;n?4?[9>[jrj/e>lKrHJ(]A24jXV5c4B.o@$]A'[Z_7JN4UMo:VKh\/ZUj;fCj
DTVLtZMD\F[`>\0qrfi^5+7urkB=.<5#1nBXg=cTl?0s*@4D)__nWVY,N#%6MH9_HJlD28PD
RZs^OIG*i_Y)b;jrku)WU"d79;R[J[[a8*rY=_$+))3oEP8S07,8<Quaklg"XaXbA.c_B7sg
"=8HgYL:p'KkR`37c]A!9qq_7F5EUcR]AqjaY5n'fIfaeKl03`G/bplNt0^Dl(=Ah2Y_%6LYPM
Am'rCrcAOrn]AT2WM&qd*Sc7PQ.j4f1JqfI8p9B^>kYU&r^sS3A`://bAc&$P2Vh_7+[Z1AJ#
"NNY/%hbdlDb8LmgcnB<)"W@aXC4'p[pC*Kiamc7#jF`N\lV^rEpB5c@DgV#J.-coi,GB^\o
%O*Mg'@#+bAgMGgIi?Y9oC"1+("#%?=a;/S=+l_XX1[q@jn0BAH8]Ad&qrCnR:8Pk/RqDpbrj
LjJ.'Z4=(SroHKf*'UpT19_TD"Y!.sg@qJc,LjVp)jSn]ABV$dV81cG3*iCPFN<7\7HI\`Rgf
%o>W9s-r6%2>0!fo`LN&<"pLT$$:".6kE#P2T*'E2*M&%bP.MGN,BD[3e4lU4!WL$V3R"Ns@
e@,":"eMND%R//M/@CIN9/*++.r1Y-a.Q6fB.N]A7/nU`m0PkKOCo(O3*+J+I31X2:OEZ&XBZ
+">7&$Z**unX$5hVUh9H\3o_>NC:$Aff]Ap!Ic6G+5A?$9C=8o.SY7<-cmja*X7;gn#YMGKO$
f!i@WWk7pgn_l->j)ZHP/$Fk;Ub&:;rnPEN_:)t)i\Br<65iq$QQ@joR:hAkVshq!??r!Pq"
>J<dh>j+M4)>)R::8TUNEppH@2p;0R(!DaueqMj!g$t7-=B2Doo%%]ATu8?ZB@5V@SUngDGTX
3C!#n`U'0IR;c8\L2sHIHVm,u"k>.(3S^L5t;f0-@Y*ofe:qjV9!5rrJ\FDA%Bne7jcBFgMS
.t\c>+^Xi^.%p5\aB>$7:L)1phD2NIs&7=gJ>:<Z/Q\jA\rr=QQeg;drL/T,Uhbon#s>8SYF
s:FY`KJq>BO1gnfc4X6\mg=4^=5gs.s#[:OANHLe]ADZVk]A3,uKG?lUl*SXN3_SDCfdqqZ\e6
l`bF_]AI\@p?>]ABs3\ep*2)_]Au,53\To<sn_+'c!1p*B^,]AK1.BFEkh+0_`,<pu\P1m;\[jHB
sYtpib&L4()=F2kT=r[#ub@RUQ4D?CFtYbEbW(1K[-k^9/If-8P=Th43Bp7$O?#Y`q+.BX9b
).s@e1qdYZ+C=o-AWO7-Z/ctD-HL+t.o6-gSgu8ie2rP808#lY+a@>-j2.R^:hSuuim'u/qJ
oe%0lC_%JESQ;ui=3O,%\$OKdZ&/?Y?ioZ?\M#;'c<.H`6'&^MN7#S3&PtjOK._@8V"P-psG
?n<%a^.6<@X=p*+9PCdZDhG=)3t8BZiceu'^,D(`,Y;,Kf3Ydg8^G4qXB^`V5POK$u>>0dN*
j?8;9Z@QJ4Q,kK/k")2:7dq=F<^[-'#%OWL;5a>6Z>]AVV?D\(:+T>dp_GFIj1MS"\)\.PE%J
Jag!6"kAgnIl%AbEVo&*HDe>2aY]APIaHX[kMVUDmuc$g'2:BMcBfMHsS<f=I3[Nl89ESD=+q
^YWYj;1m5%OX`<V?F0TM#8&V7"]APjXHX;t2e+b"0P^t>P>nN$tZ0=Y@Xe.&k7[8RjbUU5t53
uOHI>Z/E5Ddch;gMtIhKn46FQ9DYd*Cb<*dRZCX15g)`0^QkOWisJ1DB^N2j'+$AKaS,0Xel
G1Oc;?^66]Aj%qM\Cm(<S'q7QNhW.q+,M"E:hZW06&8/Wm-q&n^+Z\./)h43FYXA2X#'g]A-#>
gu<?JEODPRiD^k.`j.n/@\<4ENo99O'<8u&_PIG>LW6.l3Q1cQ"rS;fJ^C1T\+6eA8>'1Tr7
jFj52gY`-iPisU,@Km-KEDscU=pa6$7l(Ps4lBBT[4OGP6[i)Q+(?>K>&pG)ALBM"&V:>[HI
b6+f;qS!!ecfu!Q0`>?9G!+J#0RfWUi0[T[UFON=h6*N[m#Rc61>kc>N*p47)Gm@2Nft#6l]A
SO%j$dL2rD"rKcIRp1]A(I`7lOV,dE1llgRj&n"7G0\uS<=3:N\3^)0TXZ-(\@PL0f1lX=Y^p
h'F*/8:]AB,gBSp.^(@i6g4eCtN:am%t=CSAUX*/!;#JnWb9(l9u'D8^/B%%!Pb,I&Si'l`$@
]AFA=mlh+6q2*"S=WQ91T]A$5Pp]A8oEepBJL1c+%6SK:)KXa:0`Nr7']A)Ji[Ap6\!$<iBlPpk.
O*.'l0bPs!\7Grs5ksRIg:fqsl4;51a;TO&`X*GnTYGrgd>00.L"-2Y^2nL$H*Ni;_d%:2$.
I1Md>#3"6U%6GU1I>)00Q\he^nc@8LB=Mpi^_JLdWPl/+gCZp?[m'cI\;R([$rcp/ems[9M:
<=mmMEGUSC1e?n(_!a-5MRdWdr0AiXP'.f.&0TH"b35_"G<fUBM;:+pA<c&KrrCn'oG@&2fM
^@ln,gTCn%!Z;aEU-AXJgafcAa7^/MU2W#2%*Bu"9oqJ'+)Ir&uRfdBIEo4U;sLQi/Y_)5@o
"a:N"+_jr1l(&saD>n2-4C%:;6?RX]AJ'cShUuBS$pb^p`gIetPo'[^R/@:E`9.k+j<o*J=j$
#Td``t'r&E]A<a4GZNE,fU]A6]A#8s$l3nhb5Bj"310&/_#V`-23ETWdc"]AJbZE7":m'uH6i:nm
o(Nn,0p'JH#?=]A`J"=enZ]A3Z3,*VNLAH)n7<nVIP]A5CFUAA/tMb:tgm?igj;V,JSD_]AX#qjF
abCgVV?+^SU>lQ03J5\?IpeE%&CJ;i#R8Fg5YL:(ir:RT]A*f=C7X4bD>Pqde&I.6=1(PH7Dc
!X(R"$I4#e5k7<Phaa$L-R507G%S*>Pl!G\<BDi+1&Ce]A#I_!ESLhr4=ro]AqNc-MDimF\h7t
-TMo/-rm_EP'^-tBT.\t,^Q]AQ$#V?Jjm\pE&$Sj-l7Zb1K)rsCKGd>)\WZ%$<LUqW3-?@,0n
PdCfJ[T"G8N+Zp<ARm/1>,c1KEsTFBfA+#?n<Ofr>df/.hC-'jc!5_+e>2XYu?*=pV8d.M%+
b23-sdKKU"kZ)`a"(MJJa?0p9/mmZZ^or=/_c<DI8<*=btK5j2`_`m%X*r09d<iB:M9$-5rZ
Ua+tO%SI;Iq0X?ND(l_^O_&K!_S%?eOPa5Y>P%h6Labk,ir'q9`mS=\TGGnA@N%+U.T&9OS,
ZGd"@d4Zn5*dqF;%?EP.Vs*^k[XZ3H*=*q#Rt,[DtF8W&#>U>qJqd^:cp<M:,_CI28C=bhSs
m`!SA.d3?S4O/GJppB>k,lJNLOWt,?VQF/aJU;)='aDrkh#t`^;p^P2+Rh4RV\7d$%Sn\"AB
J4%.1Psh)u'k.Es$;f=_l]A*_C0EMFfRm[3Q4UQnZ$i-OLqb4@`JQD+FG"s$AF;KI'ToFU.Sb
FH.CFa0,4!sh%eF+#]A_+.@^DZ0!f`d7X+P,8Zgj;uo^SDE'e?mqV_,L:^<C2\apaSqHN-GB%
2T?o[a4&YI-[C(Im6urE'1iAe\G=33CI)=N'UgoFFrf3EF<0h![E'KeT6$k]A%??S.2SEWONW
el-F@U80qH8E%Ai7/`ek+efJMa@djde;,lK3>Gn9s)Ettj5g2HKHC8KeCPKjS!rTqCJNr+;`
iBgdBA!ZOV.`N8'iia59&mH1?M9fT+5GQ8kKROPSX,n$sbp_ZJkN9\`2rR?,SK=3g&iSaDNG
@Jg>JRqs7uUAd@Dn^-8fC?`0R9a"rt@?l8`Ye-dV1DM78,;&%dr;<;Q/sha-Wa&N1)W8G%lR
-`hW*&%*"%"74D4g$7>0\))b5')BVFTY.5s`kP=LtGMjWuJV4AV9tI5VR'9'D-`[JjYcN&(7
pfGX3O1lP)+H@3`11El<[n,C4*BN]A,9ChDr!MFBe=j=p'UU-&iN^u5o6C1KS#6\GprD[l0F]A
9_9sDA/%F&0.p47FldET<h'>"Ps+(Mg)&+G/55(aTA+;'_)%Q@fm&/j.u0J5U"TD'E^KMaZ!
rZX3E6#leC_[h'I$Ir,R2Z3t,Q@VJf=r7ZC8ehMqVWLfu'THGFX)b@-Uh[L\lFduU7^huH*:
mTf";=p-B/VjQlTrjKU't99-6l6tZNSRNRPT`2H*`,hJ)^FP%O<*_nKK.4n6ck2'BDG4luI_
'4liHo0o"7Ag1esslD$%D02U3;T%Wt*H0KciO+gB9e3Fa)A;e/[(c8g1#p9mLcPhC]Aho3G4p
-VbS@CYl&Xf_*DnZ@rDO-P86(*h\&id:-AZNI&UnqfGUk'.`j(_4k-T[dArH^q8-'2ECj'BI
)Bp)UeFP24iP6"+p`n'&M/[l-Ck.Y:"B3O9q>B+L"'>2$X,_<,61]A<:;TBY3'h(poPIf44rL
PKUBo,VjnqA>ukTP+3#haXlZ"J?U./3MUg#G<,,@dMQ8g7Eu3rAG#p6]A=.Q4[hul.GZ,8@$$
sjJZL&8<H,paWX_smPGSWIVTnU1Ph`N[\^<I8W*El.&lu]AS!,8pa&HMdU46#"q<rO5,0.b'.
':'_9ee[0]AImW-T=Gid&;]AQ+CY2l+ZgDl5!s5k+P#NmZ3hk/\T/fA`Ht66.#2`;u-()jh1qG
>,:;aP/="VtDIlmQe+I.S)'#c-*=>;eX3E<)7"3UrE\WLhUJC0/f+5/N+J97ujj)O7bh\8iV
rB5Y4culVrofH@Tr&Q/XnG-l=l`R1+6*4CbD,'5q?RG/n*m4\Z2`_:U4/)bMb=N<9i9mF\@4
r71/=pO"KXBB(tMSL$MmJ(Jm]A!'W<p^PifWIutrcn;j*0dFT"gfC;3QA!Yap)0mO4bM\TW!o
:0[p@Hi"\^nVFL))?M_sY3B,'=3FpU.r:VB/LrhnQ%4e`.W'U\Ke-WQ5:6.e[&1LVl[Ik3=V
2hW]A93%,t)=3,l?<NbMnZeQ)WI2eM0M?"T)VZL[kkY)+:co<LQ1O&u!oUD8FPPMF1NqfsU-0
=<Wgn;NU:n\TT.,(F:IiR$X7-3AY@4K/-B;g)c6diBSa/%b7?okP8YNI1PZi"q6?!!B'H?A"
lT_[5V-BqsWgOSs.)UmL[\@A/JeS0JVqW\86F[:Gc]A.1,j]ArV,hKI@f"(odlSpD>7CgL`>X\
7X<NSh^Ym_anLr\oVgpY=6N[`?CN5d@XG1"Gr*3-?HU>F$lF$!c="^dh>+<[B.<sS$1d%F"+
$2YoT0B1G>=BXU.'iE<F=.ur*!A:l;`Gm1g8Ml9\s5>#*Zob<@ZGUgEViQf:6<)'$io'@`7!
9gJ`@0196`h=[$J57W^m>Tn;Ha?NK.l`aY_R#1jW%@W?@!qPnS$E*T%?#p^<?.;<:1J6ISX>
HgVP$WL7TL#]A[BB%;8I^e0J;_-j(n%Q;/(s&)\.=_?d/4'\uX$6i\L;U,4UMQ1#QnW<)%p2/
Rnp%XiVeS1\0EW[pR3inj&Ri3clE<!\W^_L9Hl574MhX7Rt-3=\/_Q*[1!HK`'m<uQGI7:r]A
i#-lKB^srbQ`-k0iaP#V'q<HUo`::Wk>Y5.qHCUsPV*r8:fU1.FOu.,hkuKKi+.4p;ZU*&,J
nOq>t?&e.cig+@u`CA-KX3r+MZ<'fV-4;B\EWH[a34+j$/Y=F*)\7?tD@/CMRD,c&M&KXQEB
Fl:qSQY9e$.19UGu:eTXj=[>(Yn4UE)15h'Eq\<(X.KtQq=;K-;']AQLeI.^7r>c!3DOA85.]A
J$Ne[;G&@7J'pWcCcak1RYfiAr#'<k5(pp8h=OXbb<brq-sURQ+@70*,H\L$DIQf0XamU'q!
Wr."#9-L`Cl_7aL-W1B[Td)/Z[HeUjKgFXBi.b[tbDF'`o,kHDPnKj>fO0JC9BHN9G"]Ah+'9
b(FHT9OTGK^*dVfl:]A6fSjJXmW1\#1A:GV+d:O+([^=f^>q?!2#)#Bq0F_r<F]AhQs!kNUe'E
B:d$'%(-Jf$f'`iQTji&>^h`s!o5eX<KS8?,MN,1,uVcK(1l+Sb;R@-UY?j24G1`#,s'7o1C
HT=a*`;oX+a-s6BSd):H;l$[%pMct$"aqg*"FFW9\=ujfq$EI3;p5?<!hj4LH$@/VOJ-`TiV
i^*_fRGc^_u]A7R'7bh#g6]AXqa[fs6bja'o7U<`o#k8sL^aE_JQ[e9CMjS.TF@M9FnpI.fIDD
(O`f`([Q]Ak@9\KOu[0LFjWR4b-g-^ShV;=?MFHIUP[Mo.Q-?;R?=.o"k[1bN<DORj+Hh`Xu%
EkcO1*-AJ9R.J99/OoA\R<k[8M?6Zn,7iN)f1^N;b3@:?rVZE_*1\Km$eOjqf7fbhk-%uc-b
f7T@,lu7I6\_V]AH[/]AT@iI,hdhJsQZTFogWPB(=8"'C*ML*>5Y>j:WBHB$r&e6=bN3[Qmf+u
7/_o4$F(_cgnolVVPP]A5!%W7KQpe($bA>QXkZfY)cmFWar5Nc^nIQ6:`&dMmD)2lr4lhu18.
/SbXpRtD[#S82?[6).$rht3HkI%E#L-5#RL#IhKdD5e*Us]AOR`CE;ckXr\9*K*F:lQCV,*ZJ
P?)G7V"aJ=EifOkfq*u6`-TO^)DKNh'ecAB\OVMhj$<N*M#EjlIG#'hUH[nagO4J^r07;8u5
pb@%C'7^6)/#Zt4Z>r[t1P/9u$:+*S;)jU%eaP$a]AB0hHj7'Se0i<Pt:BTUI>liAo)Ir+JCP
.^ijPU8m,1\lF@1H%X%78&077I8+3Ud(t5/^?^6a(Kh[Iko]AGgYBo4doJ]A1U_"NCuOr$&FmJ
;D"A._M19aNMq7$?GoK#eEgEkB@b2!gA2;2O*kn6"O=F5S?u:40F@on4$BLAHIlG?]A[f(=#&
VY5Z,e%r$3Jb\Vr\mCO,JV<WRqW\hAIKPl#5ahmTT&aT^(#krrkiQID(fQQ#-')>%akIsfnB
:mi<CNUC.'S6+qI:WY#bNPk&EWqBpG^Qp@'=lG,4,M832h/!&@Da)uSD)g(T4knEsJmYTTq.
U/CjDmC$+RP_YTC;T8?`BD%0$5[Q]A#l)d02]AGp"b$g]AI_U+Zu@PVJTZJRiL2D3lJ./.:fqOg
lHMkaO#;0;+#5\T6P!*jc\$bm?F!j1WR'8%!>:TQ]Af"2V%-OPu#G?245La'nO<TT)#pUr1OF
JLGlZMimnW+iC&G?8K4h\MFcf,M?:*%e+#]A5PXNc7H12a&<mNR*HosH&MHB`JAZAkIjJYWVY
@j1*jK+>n#YsmRp17J48i9EMlsXf<VL4`)iY!9%:Zp9d;W<t;LKaJrq2b3n?J$G3gifMBhiM
u4_l;n_ao#C"bO4?FC]A#*;"YI/m"5D3O.$'RGT`u(Wkc>`pjufk@Jk62pSGojZ6pfj#(hg)Y
S']A'bj6!?@M!K_sAOanDP`e>.k`:1Rl1=q,=6?+X]A"#*#_Md:.L\a7i.IMkSD9)!DGQA1U0B
e?\C.?*[.ZY,6)oAFL-841)@ssf]ASKmn&!MRHX%D.<D<hR<%3\L]A!jJ0;O_8IjG#)6lR6)?;
&-?.a6Vt'gZ0X5DtSSK4Ds+31X)qNnch5J>49?Z0I#7SVkjif^ZY(r2dGn@ih->C\[WG>(a_
;eb0?P"j5,Z5PUVY)qP.H:=aUE:W+o9&L"iJ;8J*kEO'(KtlhdE_$#_oRt2NO[,JGN(dC$.#
LalC*`.Dp$4<nYbP>/#^MM^ED1lM7T@R#WtQ.$]AcVU\Qn1ao_oC^+RT)/Ifl&EgkR@S[I9+N
\>\Z`=0<Ta.RP%/j`6#nCEF+_3TKWeQ;4UqA:iZ_q@g$Sp*HW9LoG:*+V:1;HRT["D*;LU#D
9=K[YY)K]AWV!Q8adT_Vd&E&)e/)D0Ejb<Y#Y!@i5H+"\&4$t\;Q,4\3*g$QQ\nXdEPpi7G03
@DQ"QinG*Y-:+Uc:=Kb3FIh<>[Ht;<V@cdk^YGDFL!)Lm!/%RVCD\>8Ip".Z9Cn#9\Ai^pA4
j`slWO0^#GMVb+:p)U\A.Nb@C);(qf8pL+8=QV72pd0_hp_7q&cI#>/+?NVHiBW0Gp)B[6(D
c3TKP+/`!r66IP1EO^]A>u4Rs=65m7'e60;FsdfBQ8dDrRnm]ACa[dk:r&Ch\0E^ge2ZA28>8b
C)FnJQ-gC(VhOI?+'+&EQ63qhnsbcj!PUHf@Q8qF8%8#i?%k='9J(&#D).s2bu(cjm"htnCc
0P`XGK$qM!GC'Cd9Cuq@l]A;7q_3(p(\jX`8'@::"hp@l5`nTJf=i`Tu;G5PpLRQ`,3#Q']A/.
04Y,pZ558!',nF2X5hW4Gh9;E99+4)O`cB<'Kf,pkS`-WT*XN@kb.CI<q(1MZqQK%_ALW.dV
3Oq/W=t,m<OVW4J!ZTXfD$'ueR[hOJRT)ZiD1,!-5)hW^5j,MR^CCYN81b/]A,f:Bd-,gPYL8
LSFXoa7=@6j-%=5u"```u+4g/"t_jCa#5Wb34,k@3o`]AS"10;aO9cCI3u6%$`q!]A2#aqQ]A'\
mP]A<FW%N0E-\mLYFkQ^JE=,`i,nKd<\E:Lc7@BurauI\a;e!0+R<_q0k2KYT7O(`YO]Ab&\E^
\^dkt0O^,[2_R3_fWO>t\ho;,mVlHZc7<H'URL^0-!d:\>VF.Hl56Je1DC-%Dn\OTQ*4?=).
r'qLh(!_1@TE;6Lr"04'Yf``N'4m"kA?po1R(jpof]AH0oQ]AOpg1f3<0$OpO3m61S2d]AeQgRh
o;5SjbM5f:-u3(IVUq$i2ocE(^\oLBU[cl5Unas@3\XiQA<I2'tN=bI>@)b[8G,rRoFpmN/-
I;F\DqVO%S_^kBC\$4'f]Af-B?SNhbH?Gm;Ttp,_b#>\2VN;AN$hP]AkJ9=Ej'f6Q(7Z+s#-3S
JfOe9F4V]A\l).:EX)NA&Zl]Aai^J*SC9$`UE`eAe69]A5G@rH]A7q<gI-X>Qo)0eo,1_iJC("_s
XZB211f/\kTAJ^3VrZf]A%R"1iO%Ge@.ER7Y@4G<7C4u3DfH8h2.\pM#;=tQ5u2'ZWR>S;`lA
k2Kj'N`Ac6/-Z,]A%")7g-^Y03^+(Zb;fLX0b3\6_&!H#1QLXW]ALWOHgUH"7srqfbace6<=\&
d-d*ll@fG""u?QYuV2+H@TCa&^7$B#RSS7Bu\VnAXp(B2Nc)q/`X"p$'mjrqbC?dB!iJm#ee
]Ar]As9Ok'!Gi#'_+D%$ho]A#N80&TSWK2P[#Kgc\7hZ29LPl)^s=k-(UhNupgb(7&fXR8g_:2C
-(H6l[65Vn)H_A5nL,nHN<QIlS_,1^?nJD=^^#L4j\^l8qPTcuWd)h5GXYjJkW).,N%CIJ(Q
n;;,5X7W'X5*+MqAn-O,YjZSk%K'H./6BoF:I!qtt;T((4M=niqg8<YN<FjqTAN^[>d&jH37
jJgC1FW[i7_WYf2"088/<lQjY&aX?_XKoJ23Q':H#^-X>N2Ub'p#/38:Q-837dV(bmFM.1XT
sr[t)I\fTgA#3ZSVM?kD3b>%)uoK/UTWn>QdfP\BM4f)&(k@cAqa,:"VTY[MI.=dFe&W>mGV
ra[#lbHlngdlp%E4o9E(4_96QCi)2!+9ebtmn4rP@,ArV""HX?/BAR8;h_XC.?l:3Wf9J&a6
??E[22SD&70j*oO(ThCaLE:E%?@nG^o_cd5rIkW3g;7_eT4]AkF=T%ofaXC0<Y&KN<N(X^H\k
9"oA>\gVCmGSbmY,-"^24cC:W8`*UpSW^L("M34@\Xd9@mRH4epRdnRXa+n+S.pBC>FO"@Ak
J3ohY,:m*^'eh+I&XmK\KCol@Zd`gHgTkE,%1MAKo:<]AJtr&tOZZYZn_a/d05E`kb!LeB)=Y
APlDE5]ACH?56I(N;/+_2Z49:nUY'8*Repah.BKLP?/B1;JH"AZesk3j&0TqJ"mJ0]AAF5gX\O
)o`^Vo=]A>H_I2P2[rloW=Jdfc?ne7=[CWQ`K[[Frb<_3RMqaW"F'q+B'EOD24Grh)Hc2nuFY
o._!S)Vs'_EJQO?0J2LKU&"'jStiGd7uC1J"GjNIV51uZlB$qX^Jl'6Z,"V>jla?Gch%8/M4
q\TR[Pc;4KCuBAH&*4F4W,pgH'sqUHo7`H]Ad<Dm_4h%ONF0-LPC7<lkEUi!t@W"0,%l8pZD;
ISD9FPfL[:_3fPOn=O=&t8>f\j4'`6.q5EL_Rr#0LGbI0/o,3`%&Z7+*aMC;8O4rd6c1uS[[
]A56?i?"b,Ru=BnH95IaO8>DUI>E78El$;M`TjdA#Cf`i1in$^`JG]A&YPTN-R82Jo'3D11:GQ
uY1uT%B?Fe;GqU5&,K#_5BS-!:McDZj<$n)i=Vk(<-rdc$W9,q2WjMOra%6H-1rU)P@MAE4u
$U>Ib6D,=icKo`u7-3!3HB\RN+N-JT2Wm--d8i>mrV#3OVQ>/q8(C<Dq7i.e]A\lO/No0#HP(
*[khDaO+,Kk*7p>#I-,gs\f,EB"U)buYekaU+Z)7eaI4J!S4d5=#5g()!=[K0sG!>$Z9NtR"
VRB1VB553/$c+/Tb2XU_hBZR[A\$<iUkSop.AO0El@T1a706<oI&bF/)Ah=Ujc(*JaP1[R"W
RfM+H'II+e(rSApG`A?Ei$S<!X's;)uLe&fcj[E\ib$cMd7p-Qbq!:6)WssiRYjI`t72XMn3
#HP-fO0J?<QJfmHSI3D@N:2/AN`,sgpWR--:'DCk]A##u7Udcp`E?3MKSUE@Q52>?tK_qCn$n
&'L]ASZ+*"?hZO)Y\X#[Gapc;o_T#k%?*WUG1E`1c_:s&17BgK>J:',NL0's28SWH8hgU>jo?
Q8)2Q7^Tkr34?lB`K/W95H^<A;/WFAUgQ7LW(<^$";7WD+"1m(SlAHb!K;<96HW%cr:olEq.
@/&g^Hf9QAl)q+hc972Os\e%[F9Q3DO,l+QLZla7!;AEO)I'X@[AjQD@0Otc7o>\X3RSFM$+
ln<bW,lKedbZ>MN!]A)C@kh$7-(%(c_iX,7Z@4E%\O7t;m,a!''%ugdZM42!g<j="D?Gbqhs=
&u"%Jsq$229K+kSs*pP*Q50tIp1G\)E&2WN?*I;fi*qu[dS3!]A>2J%c0?i7W3:H,P;oIJuFa
db?6Y7D"P<raWu:GRU_@k]Ab4QTW`"ZBM3O/BN]AF)c/IJ(,1raX(7'i:q+p6+G5hEs1(SH1KI
QK?'Ar2c%CU2=]Ap'ah!=_8thWVIpS[+TTO"F`nZmDK<'7u.G;%8N%2)IOr2`q&+!r1-0Z*<7
8n:A2HZgo^i%g0BVgm^br)+P/tk!s(37!8-na1UtaY"3P\`q8XG]AV]AfZEA3:65RtZMWOg4aI
DQPD+D2`Q4G)53It#kF``LUYlMb,4F4di\VSm8bb:albPqG0aU14)V=**L9N7Ik`RfUgr9Fp
1i^:CNKQObl4`btt.@;#A(V!5A(gDeh)iS?GX5+P3=kHop^KB683-%/)<UdHm82DS=/QEYX-
9sn/TA><j1]AlH]An<GdSRW:D3]AZ031,aCl/'Mmf^1B@cERs#UeN&ptV@V[9EppUk*KC7'4tk<
1`'Z&pWPmfa1g(U_jH>0ifu(I-iYg#02W+"q!dDq#+r6TYX7^B!ZZpjGs4P`SGXk9b0)hqol
CT3&'%,jFpude(dl7@DFp6<5f]A.=)fj3`;Z+Xjf:^g--9Z"^h;pP%-akIe+AT-BKeCD>_h^h
bXY(2:34bh7D]Ab@f19PoWe2H[J#>Nk_j;c@+EY*Vf$R#UX1]AX/^jjH_r49*lMU:\rq`!4[%f
@G0CH;.0TcSe^B3]AQBW-+g5=s<c*Ig`qXV)a"'5]Aa>rO%3%)ZNPog@IW)i.(ilo1@kcKsicN
2Z:kH[)Eqj:@EBA50IJ#if-!4@u8e6n9$k$4!=LZj_q[:p0NUEqPp=EP-,>-!Y<[r6T+9)if
<sCOSX00VVgmcT5>?*?o_Be?)VMcPm5aGJS1CP%Er!WjG0U#;1h]A/Ft\]A;0)0PR&)0AMa"M=
cbr`W@?1hQ@,l8RB%XNn686)o,T^t?%BGmLRlPO6]A[(56hrK[_I1NB'FoTi`<@G6B=lsYY.I
0\7$S_eR-_V1!BbTc-2@aP8^e"$8-%Dt%<oW_JCO%kuQ>@Nh_n4@5s.p1@ukmo"0Y7h<[Hm6
W)2chJu_9KQ$d/k8Dcepn(FVo)hFWQbX_CX#`H9)#fAht1[Flq,[Wr(tq!.^g<#1SP2N'rfM
4AYKI"-'!1(%+dLj*.VQp'#rr@4IUo6)dF`,JtK!ma7NKm<Y.[?@s9Q%^"N<@d?W+?mAZ3H1
P6Qo^[=Xc/B,L\Ko$27s',HIk&.pImG^@Wp$\b[:u;m,;bcA\X=CAbI41lQoK*P+kQ3MFc9M
=1D<`:D6.20s%=nHnLBaqSf^G^7&nKGKj0d[2Uf%8FCn"O\qOAK#Bkt!>'P4&^U!@!\uj*8Z
@H<m1(>D%s-uNeH0(/G1iP0)U"=f%Nq.I[@2m7u.^*c38Z/eA2B6OkGkM_h_g%5HGW;[HTc.
f74CKHXqmY;i::msb$W$:_Dn#1Z3ALQb]A^h!81SqC\5W:+C*H%KX83/A#XlFP1+/jb@67Ia2
8n3d\*'f*?>0Qg-Q@kSP+C*n`CYi<*Hl@<#+03P:^Na-co+GU>m@XPNQL+JX6$G+F-YP>`8!
N;<aV[b+EK2h2>dK.k4T<F8h?OgRT2OIdX?:`M<@g0^ZsQf4jBH!n&/%&rWec_VJu1&=nc!]A
R@TB!YX(C.gX-28iUhTE*4Rp;]Ak=>bbA,Z(LN?b`K*Ql8nn>u"g;)ro7hXA1L\X99'#ZU.7*
b>+uV9UOiB5eq3L"XQW^=hRM9^;\`hXA(UE.<0+_1R,LbK%PY*DW:+FVMS)2H?IqmUR)_UkR
7p=MS?>-dfOUH)UmLlK*g[ChU2)hdfig!(m]At4[7@_>nJb0MZYsp=TL\HP<cOThChb75N##@
%#-&*/ueO*pXJZs=6LcF!gg_%IGa\=;eT\cmG*tC_IOj`Q//+N"m9]AhQ;+KtoV/Q2s(SFP'Q
7a7AA[,J+T2+uc\Qc`0[h5Gn>d8Yr0c!bZ>jTN2?r3W4l$4=TJ"LB#Wq#`\Y'b/8"GZ]A.KC7
!_PkeeGNItaB/=?Ls*PpR!Q_'tkG6&&EDG-[o@l7_H!cJJT$k[,bR[JZ1s?F>fDBBi3&<o*!
IKaZ1Ja\FYV8%flZCTA\@h.eWW\+4+%hq?*6sAjm-(X2R3[oiEda=tf^k'Wkgaj3r0tDo\^&
9P[KK(0D<HF+@O7MnFL^Q4RJ0"Xo-iMkN+/Jg`cG,bl/$e4.Gbgd)bX)0]A[?4]A1j-<&p&XE,
hhT-m?c"(,kl"MlpZWj`,AQ(RWXjt+H1E?l3Xg,fk&p^k"js,/*OsiblD9,=%@SPrMd4.)LP
#X0ddjO>Hrfn/qp9eGf7f=2Nd=ZObfe4ii5EiEbZ8?KEZZ.;l_)bo'G]AH$Ga-rT$&c^jlN<e
u1,npt.q-o5iFe".'S+s=H/Jd6kNs2rO;D8U>ZR/1<jK0#Z>g!-NJ/Bi1+KXX-T?R\[W(d*R
J-.cD;bL4"uVe7f`u=M3s^W=njd1`((nBKFePsL(RZQ.kF5#BGCOq9b76pFEsI+:Y/qChMBU
lce]AT[30c'^G;U&EN=2dY$=2`K#G;B,7!aT'XXar[me>*8D05j<4g_;'7)_XNQHVT_njJDOZ
F(@nOjB3*?ML,-2V*$/r5ebun*l`NI"-qY\P2sVbl]AD,db)[FBG_mF[.=ICddo;[Z@&#)5#o
]A-6k=odX?M(AsSJlYA:!1B1VNNUX#4nfc%q3.G"27GDRg*16L2&;%H2fks*&/UoH="j6rU2j
<O(Mc0F$N^6Z0/9?<pU68<n>49=]ADD?8S@,`U8cLtOfQ_&Cl0<?)4pO-,<Wi/>cWnSSjsf"l
t?rnZZ[qP"o=9>U>GM><X@sb6oFEB/-tOZ=0?s8[=pD@`,F[eShuPAWLJlrc_^$]A->T1u):)
-MTWGQGbCWGHP)kSbXBD/W\&VJ#Ep`d6:&*jkUW%gu2*3]Asg)WV)^e!XbU]Abc6i,F5c(AE=+
DOO'qb+h@t)=%G3QA]AM#fO5V;h>B<8jQg&UkYeoK2OcFEi1kNYEl9uK\A's#A?bld.-e''\F
fFHb@&=0PuqOj+<A]A%]A16B9a8rHj*'(b1,OGpWkJb=#`pl-!)N!:/r#%D#Wp.3G?32X(P<QG
S\*f/>7=-Muc\6=]AZPpL:CAuQ[0(I9&0>,U!J"(oXS`#hQHg)JiksU;Gk@)6*'R'3u8`V@%N
MV[qJT*'!^?gE=^@bVa<H3pJ$$/AT-A.8k:/'TMkZC5[_7_!I5prbch0*65Jj&n#o>$$YNk-
.iE[E-Vc]AqsKbTEG/1ZU$E;@>&(2iIEY,]A%MB]A:[3,7:B&i]A^d"9HlN';c">9>`<+bV"#]A:a
YJ+.?5%n;,h-KqC$"!Lh$k:]Ah46AW)P)G9hhVl14NV@+Eiauq5W)rP%DVphbqHBL+@J;Z+Sl
rPYl0r<o[DQS;B+<t4`7!E[=I@?@Q=Ug:82SD\PO<"rVJlCM<M7of8n'V=$l8fgEl<t55Yju
J/oXP;aErdcH"=Z_H;;m[>?Nab&knSrDa&C`*/"6,4mJ<&)&qQ0T0LU5c)WU]Ae.<"oY:o$8F
VUL2LRcb/Wi3?:lgd.Boj^]AJkKV1pr47<%)R-Ltq4RISq-P[F2:,dRaN6Vqom^ds%_#u&R@F
/m7[8EOrtiQ#^78QeI;\X)#_USLWC\u;N$AOm#N9jh2QK-=O$eUSHWJ-pAR]Aiql?VBhf=8U@
UMrneW,$k,1NF,YJO<(7NDO53D^]A4H>$oJm5LC73_<b"CF%UYp=<+o#OuZu:ai^5WifDCg8?
?Vl5HdWbZ,%B#P,q8]AP/=O`hW-V:GVH.tSR,]Ad"#]AFe+c]AT3'^qEY1GjZ(5eeo2Qi"K^AS6<
O*STciT%s'g;ij5;j(4IYA=imc?-n?aK)iYfL0_7SS@lSGY($/qKc+VsdAr?0GOG"dPd0oQQ
SWDP5]AMX]A"dY=\S\MVr"(]AOAqa8!?8%+:7?uc#s,M,DWDp6IUmM<3RrM!*C_o^@fIH'%_8PT
L#PeFHs%,Fp?Z$DI?lJ&\^-cbUUSAieC#h$&o8K,LY1OiAJ]A7^P)O2>\X&Dc8%lfN[FJ6MNj
a#C>g`7riJS(\b_8W6?0&)`[d5"G0gIO-**jkFLd_sf-73i**V)boqfk,[/o`LnYD`?bA]A<b
6.J:SIj%7.9Eq;[':@LIZs->RA69;+sdqX+FTj\kGG1HZj<',j>7NPS/HqS)r`'aePX^V3q<
61Z%J+dtA1EF/R;G.ZCc9e9I4;Z((8-J<j(6*5&*JI/9V=/nHeE<;[e6eZ?^2&O+EX`==qHc
M(::=Qlo/VJEATlQ1X.*-uda2OWaO<4=%%"jHnsiK/!PUAnPMjE[FRs#`FC;?X7<o,e5+_gJ
Wf8H';]AReXmdSSQ:+cjcp,d%fp-fB&P320Y"uN%4WDMHT3_*smPPYJ&oLU_L=*&9FEG%##=S
YHI4p?(9c3raG-o!P^bj+*N.r7S<Q-~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border>
<Top style="17" color="-16711423"/>
<Bottom style="14" color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Top style="17" color="-16711423"/>
<Bottom style="14" color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-6710887"/>
<Bottom style="14" color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-6710887"/>
<Bottom style="14" color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-6710887"/>
<Bottom color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-35328"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="Verdana" style="0" size="112" foreground="-35328"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="SimSun" style="0" size="112"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-6710887"/>
<Bottom color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-16723457"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-16723457"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="112"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-6710887"/>
<Bottom color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.25"/>
<IM>
<![CDATA[`HZendsM,0'@DfpUlob^DQ!hP&]A9e/1?tqR`KFC<OYGq+HU?:D.a21q3-(@_`#'N"`2p@_R*
@oDL;gB/`qM:SqOM\5k?QWLY2At7?[ocPnUA5[YLiC=I(ipjpZM6Y(Pc!@2QW"'P$6u.B`#K
-e9=_<W?jUKA:ab7oK)96Up_:cZF)k7SiOsY9\E&/X<4(+D_P4`>BtEob6,Y`#;WSDbl!GY8
TT^KD:0c,5+"j^fk.h!dt<IG@0?$S"V&V_Nli945G@tfeSCD0KH*2ml?;OlJ>"#m-T;dkj8.
KVIrt6t(kj#B>(fIc#P5qJ$3qRoeb^P[4Q=",Vg%pS3;)J=G;JYg^"8_Q'!&VVf5).3!t&VO
!K2SMaPiRCgD'+'$IfT"phcRVm2EK$5&iIPee)t-fO4>0XF+>FI"%Pu3/R7^O7@D(Qkt^^ng
O;F'D0(l_g?28gloE'R@C'%N-#]A`mL]AThB`ZL=^ZenOlO74t&4L"<[BV:F%l=+2qh]A/E&XkM
1CT,MigL0^c340V\9TeW<P#bgh&(nH-@Du/sAdRlMp[n-gId0cg*g?L1V:54nMORVeP;]A)M"
m;J!ptglX^Q"W.`psjLAQI&oGl4iUDhf\Om\)CPNCR3lhljJLBr9a`A6e%p\U:@O\:"?A\=m
f4I%GP,&>;g?&>Wo&o[QphHYoiE:QKgFrF6-^N:s9_[g0.]Ab!9J4L/[@):sX4!gO.TLBl$[!
-dDW]AFQ:"bZW0#UJ:&<.:\mpSVLB2%%rS?DOn<ienbGbt#lUIq;Y%=<>HjQOba#!,,Ms\_lP
Q(]A=at4t((,EMHZd'513EPVA9:kbcGMN8)AgM$5$kh1jkC_uQ`3emme*D2DBkJf$u4nXK"cT
ENt]A,#muMmAkVIG9c(E9E'=jSiC4-cJ]AulLJpk!jiQG"o;"\MeN&m;A7JXH+$D%+3!Qk3H(S
GSm@2N0-=kiN0+=\Yp,h+5gFV-l<\o)f"tijP7ZR@''b)EY/eli757UmA\@/u'C]ARWUQ<&,:
?W`eUM_,koBeCn&tSGL`gU<-:ASQ,#m<[kKrcnC?3%K.;Cm4hDkAf5Sj.T:e^(Si*ph'or$?
aht[@,$9\7r%nLY!K*/e+`Bm%k66__/"%?MKU.a)R=Oa$e5LB'>Zg^S)4^S+IT&"2D0*U.O\
J7Vj,"L4q8i.O5=%pZP>oL6;_'>Mn3aC5Rk:j5mDDE%.VW3kj9pT;$Or)O$XUJ?MPe%gfE$t
[`n::Y0d6p'aX$V$:WAX+0TUIi*X33seQ9B@QG61eR&gl,8Jn\/Rmgu#'?7=NpBm5F?Im:4G
J_5)Ys(<\r=H"27SiEjWR02BrIo%&jCscEI"$QAI7%\2'"L#qdL\M-<:WWTg-/0?pPqPAKYH
BQTdoJO>[4tC3k9m_+/YR&<a`uq>-o4DDVleo']AbGI;l!R&ZM'BVD[8OOE:GS"-_lA`ND!K[
g6>:Y),pCqQp"d*Oj=J5LtIYS)VrtA.Po"X(CYOX'4.nGVo15lRp'\l@N7RLBT@qTB4fWQXC
)V9kk$8ooX![ZRLW=E?e9iEld7$9E>SEp>>.H93BN\]A5(K[BcctnM!`N!?e%,X)?qWp<l=<.
_V`f(",EQE()kb%\9PBkf.;^&96b3q#.N1N*oruk!3Llb3a>("%?/=\5f86r-).0)2ba!$Ch
PEj7+Gh_q8FJRh<5l.CH25,``"HOA*6nT^S`1M;H5,Uuf>^ugN#Es?8$$1TkLHLdHS3cQBHj
f(2e;'GL5#9JH,j[<S)Nrl,IO=;dJB2:1Kj:0\UNLZ'l."g7paGc$I^\QB^DioN6\uMV;9^W
hG;u<`24Ug27Y0ZZdRHf;GX[/?r.!jV_jf)%=_D<Injo'[J[;X[*,RjM[#s-A*Lr\m/.QGWp
f(t'`Q8pj.)Yp_\nh!@2@m`j&TPoAT0r$U4prKLbT^hl^I"YNCLTq@u5k7e%(i'>31,]A=##t
I/ro47ZZJ>Gb_m@V#M3P/:%NkFF2?"G:`HLW7%]A/u`$'IN5(b;eOJB/$=ZOM"<guPc'-..R4
$<j:`JgF*TD^JPcDHhK#V32/(8A_,7AuT:9\2-^/4pdYG\?N&&.6F9%L/\;>1s$f$.thi^/-
prRakolcZ3;i=-j$B55;-R;)%IZ_7W3$rR*?jD<A=R(1ok"PT6PS;>#e8&/a7iJThJ"^p3en
HXd@BFa3o//48E`rFW>+n"gsH*C9ubr#'u,<GVJ:EuTc]A9GPkhAbdL^ck!mYK&N77\'ZUE)Z
sDHpLP\1B1OT"$1("Ygm0.aR_,fU[Bn#&E(Fg(1O'Pk7ApTl[fq\C0=#isK&Par>esU]A%Q(T
\&B\dK*SmZL,5MbWMe`lVU#;N`4KRd(5<nIZ<X+.]A;]A5!.R1C,KT`WZuHVSaj.83^b@7nO=2
f/2S<F?s'nNOLhfU?bG2PW.03*ati'ZFa.8?@LA[^I<[^98.KR&.q??4%;s"]ALf*2ASmZ,$f
2*AWC+J*as2cHg\C>p?l=t+OL`Di?TW*"93*X*JQ%T"J64a3e:Scd'5mlN'"m\l%6=.`&V;!
BI\npWmN5n]A.g0"p2M[0WZ_#gQX=-f#Hs$Vcjk$A7AF%$e9`'^Y71d.@_V"bW(\]AP?+'o`DI
:Zn9sUfkT!1Nf$4\p;:hPtKgd$s\C1Kp^c92Z0^q"p>W!-mHBZh1rj4p!l'+LiZparGY4ERZ
m:)X^9n"$iKmtHC=?sj3O8K:Cc'TnMGD*'r9#"7[W3B)cJOu\D1pEq2E1UX2Z<JFVXdepL`6
C\-Bh$4Yh*7q_0@>o=tje3'@Z(n3/RcO9p0i3lC:ABWf;0EYLL#R[+6t'1dgG+e[*?O+4ret
GtbO;5MXk'J6+eKD=.qnpjr0;Jl@lW)naI!tjXC5\3nG\eJ0Fhg9kk6<7Do)^a_cDsCAoXgQ
$db,_C<.fZ(D(5l%i]A,8_J3?p!V&KmnJb]Ae>jr(+;f%^f^+`bf'Bd%M1#75N@(H:-s/\<]A-V
a7OL,0A@.lJ^G>u&hKl<]Ar7Q/?#I9$g(>KU)k^If@^hi^?p\A)k(._J"V#^S+)VEB(KMUOC3
!<hQ?_:)pBbM<=!iCO9]A47sk2Ph!B?YHV)P`S17&Lf>",FaI_<r3rVM7.dDe_KD,)JM@RfR_
[:Ka9+Gp(!mhi4+f3Kqi2Z'GLTi'DO.Po'c8ZI.P8Cq[MaM2)@4I8S<Km'+;'d7q=`+Zq:K1
\B9eA[Q,d:164AS).&09hn*1u]AJBhu?,$%6$I(n^9+bBT7WOU&)poMk1$diuPJH16+8Y-<ng
Y2i?bf2BEK=4C.pa*6Ou1\u;_Gm\fG1HkPFl+EG3J;MC(U@jd6atd%>D\TG'RL4AkWf?Dlbg
+=R;+u5W4_fFln5IaKE=EdOlggn\"t!t,ZSN+3#@)LB/bUVM`\PjO$I>)^qI"*T[@1<7AU\*
X#qV>lQuO9XhK+>mcd]A%AqZjo[G6e,rQ4#VQ&ca\*R!a!Pmo%MVHFuUmP_kAah(kuig>$Gqc
Y`DG>TrGf-mSK(C@]ASJ,0VO"/U3L"IZP:^+ptdn)o>[g;PFUR%S'Z6_PPT87Lk'4"eD&]AH1$
-%7f(CD.b8-1X=3u0&6eHkpW1Z1ecf6V30nsl34D(_^Y;X/,70ZuA'8PV"bR7DoP=[q%p]A9K
EI!3WmR6YY[L7C7!'LfnLaj6FdJ=6V'FZl5\kd'>eKZ9XU+DF,W-P#@)Ctf@9j%".U:t=Pj!
t6']AWl*BE9M6\8XS(u$&8"FRXHZC0V*1jlDE<kQg=T\#7@Rs5cl`1(mAf5-@<A]ACQ43Z=)43
L,h%"U>"N1CpI,N4ikT#(n\VD'C)cpian5*4/`p0c;&Jcn:T]A`kA(>_'Q?KAS*papB_*^r^L
E0'!^9N/RB+jg8i&Ulo(:!1W?rpBCXN\!AF21MYY]A?JhMXQ2"DDB+-D)D1^f035UJ!%=V*\.
S-Ze!!VKo&u^V4IgM\lB]AKCkB$\S\Ha71[G),k(!7FJi6HZ;/FrG2DJp@ap]Ak7O/sJCAYucG
i`13N\JMY:S\5r9,Cb4DgnRe66.KX4ikmtENfE,<1B]AA]AN-]AQk0pBdQUj11Tk(j=FR=<[-cq
8<7ONO"iRd4O?i6TNA$18_&\NteBWYMHELdGusS/&8>,gWsgdg@$R87W8;q37,Y`q"N>j*.d
k:_>IV5kOV&O=6eiYZB&?rXYQ7AF[2G(5'-3m6MJ)#b?eg6p/;EKOrMHnMMqXL6[%P'/h?W`
8ObHhMf+,BQ![Z<M+[#hKU`m^t4)SG2]A7IG98n,2_F,%B9eXc>aWT_as.(B,DZ'p"'6c2e$B
T38LLulQ)&PoT':;p2dg`@B[.2&ZFMdnJ]AK:1*N(SG6!Z!<B=KUc6`j5<PnN6ITbch-8<;<B
'D$5C<T[8=)"tK_[!)WDp@n(QY#q_AOIRUO6!kLkN=.&^*e3^tH-4.l!cE*_:P^%+QkkXt-3
EY;KS[m5%6dl<lhG,-]A`\Du$BjhYYD>IpY^F-a;'in`W4qm*W=S99qK@#J%D1Rn[IDBR^.oG
+^R%AML0fHg"40)\nb!M>]A>D-?5.)%d(gI/?[V(sJhQ*=39CL9n/uNDAIDTMJ"C]AB03W>i?P
q8&o;@U-Z/I^r'\!u/`n4_)85-jS8WCE[DH`R<4>H[IK5F&FqJ=!h>[.PBn$^:/2OQ6l-bs(
S%.SA6,SGac(/ol8+50L>a7&t2N16Wf_;2_,aNjON)im>e8eQ/Os1Il8%E:Dq+-KZN'#S--<
/em0O"p$!6I`]A;fen%RrS:%$`OAnU&q.<U,X4O[ogdo#,Mr'%C=!ms/260MrH&_E!ERSe]Af%
dSF\YQ8*^F@@`46AWT7Ne4H]A/t2kLC?<A1E/m%0?<3]AEh>e`1lqdToCH["k6mSo?Q7[53#$W
j)0er,p\=Ji^OPg:r4f]Am+'$:E]AL#t[`)^tb1hgb>>ApnQS[50S"_ttJa]A8Mg4VVeplqD?IW
R+LR8tO8,,foZqgsGWGcV\Pom$3=P2mY3UrSE!'!VWjP\2t>0%S./aF;/sU2[8(nfnn7iPtY
%RLP?PQMTnnepENI04b1R9gaqZ$e"OD4EA-WLG5.K.)mcqIYo!sP$(n:Gkd`,"^73"R\JtFR
/h]A2"KBl3egN''T5YGfOXrSJa]A>sEL)>);1MA<O0Q?H;52\)>ceJO)]AhQ(n>=:;uhkqRE3aC
'2c,[RG:4^o2l!fF>QRM)\4irmV#Xh7pd'Q)crA8[]A@ZeH4:;ThDZ1a.e+`P)lXpi/?XiQi!
aG;HdMOOm+]APK4#(<8&Cp\<7e/OR<,6fp+gCZQ$*jXr<Ab^,+N52]AA\6CGBiI'&nb,h5JGS*
@V<,?C$S0#[mjqb-YKW:5h^8PiBXBpN'&)qCZXKR!X8^"j76`\%,K-9X;"0LD74GZ!oAu\"$
bhg*<EMF)C*kJT59Dq7uTn&lFO^^FHZ(dN\o]A;1V$"n"rBqM#iM$P+a.]AW^Q8Qa8!g6loc;s
k-OH9hklRP^1m,anqnp\^qcql`_nq"'/h4%k2eH,R=/^8G+mk`<p9%F7@<kk<L+KZ/sa,q+Q
j64If!*PpD.1-Sp0HgcaNDWRdf!k0fKS\,U9`&a)gqT>sCDlT0$VAXSF\4`4eBhNPH']Ao.BD
R9Sm;TO=`u`B^,?DPl*o?5cZ!NbU+%\6.?+^HZJKWc_aE1_^]Ae-Q`&JeSnDXkSk+X,oJ^VG'
q`J?:6/+=aOJ<kH6ArG*qeE>33i5WJ;W##DY;)PTU.DbU*n^01$7S,DDq#M(bkJtm%`-ig=R
`tC2m@%0&aSUljj53RqU1?XDBJQqjhq-A[;-2l^Btc9?324(++UqNF*o4%A]A&9+,QSc!^G/U
kCnmq<:rg1lW4ap;&I,K%?4_R;k$nh.+ONG)%`+_*G"EolW>i3r^k\hHRk$5]A@=ZAH;4#F,T
WD]A,KE\:RbZA.HEr%5U4;IJ.'EI46qp)nJ*KqUWX(Z;b=rggelf/;R,3Lt-E\;A:j`Xo=QI>
f\@T/D^#1-'91nM.ReHf7Q7#!]AcV6bm%);Rg8q*K<YG)ds=+'b1#*&ju@/>T1;e+^>YQhUD8
(+GXY0shoGat@o_bB"=#L<:$7.lV68&ABUf(6#n&SCVge$g7&AGtj#g8CTb+L2h+.#6(U@\%
Sk!.ReT\=fKEh`s>^Kr*?]AEdpB@(:jJ)YZGY%pf+9Z\n:5Hh6+7`mG(^tF*H2uk88L?[h3P9
N&!,uK'q([1^Ergn5[:9^hF);n<j2E6?`bgSD]AZ+p!:YR76&bX)@oL,jc_^Tplq!_40@&ogk
*@#`q9Rc!m(#Z\C0T)>s4Asd71k;nY73.]A]Ab,5[J@3-n:m-75*;h?k60e]AqR"FZ2:Sj_&0n:
@nH-j#pg[iub?JMcKoVYcrZk8-D9]Ant<eE%&B2Ksf29Ip22ePsidB4jsqKe"1mNCn4KGHUNh
&i.>7BihYMITB&\eF5tgYGC<U/_Q<3+!J\CEIDcAQ\=l;PjK2KuJb*#,lTIi^Q$9K[q#ig"R
[W-?TGsP.Ln`h;]A>rL_;-S1<&2J#1Lm!bQq2#]A?nh:pUQ7YfEi0]AUN5,P!=r;ga%n@@lh8m,
1B3Tc*>#2=4=j0T39HEt9mJ.kUV3J_O2"=JBF0,d1hh<G$#,R/FbetUL!`lV?\@.2pior&/b
s=[WLoGY0?-G)20kB.lUb3%a9qRG?(YT3a!9g.R]A[Fc\0EdKZ>3c/a+LM(%T4?noC^5ff?!!
['W*@E&WquC`3,_MS9e*)dEr1e;gUh%QT`sZV^[5@I^%UMdHebNT7Biq*CDV7/Rffi+h$#?W
;+)*<#W(%6o>\`$$o\.JOn\A\\*'i.9q[`XEha@P-5Q+<Gj5[.RDU`P:,3oQ!/)6ZNjVs#h^
IL9>.53`GF"*G/[Y5/:<uKQCRq=g=E6!N>c>QmN+n$2]ABdC+%,B(]AhcA'oT4E02mU'`&b!He
QlaVO-&"P]A[)OFma`T)PdN>YEHu!njfe)*#6P0a_?hj$3^DkPSm/R7.YKZ_[833Wn$+Vuf!S
_-&Y#H._9G]A4_g,7rST_NotMgtV#Wr;rkjI9N^QC:t!r<Sd=:-Dgcr/nncg^'-7['_5T7^B(
cRUcZ7]A'i_W`?VO>?9'=J_>1(Ih3F-M\=EQSAKZ0/D$iDlQ'&_:eHdj#SA>K?7@1Cd_/BDhb
-0UuLN&;8U^49&I?gMj?-!=Sns.>S$gp#Thln?\3<csH[RK2@J+RKUN"4eEPIsZdD,cpN?oa
b/Hmne_#S;A3a@@,Z8`?:Er>#U!ViM6^UOO6Mqp^^27arC)]AA#A=EQGU2ltC1(EZoYU(=DjZ
[B*>ZSmM/hE#Zf>$Jo^p\h)m#O^&SODlXLho?V6&$c_6=8/S?VhdFsgi1b7:kB%X?^#6$(i'
&hI.fYO"D\kI,",YZLbm@kKol&]AE@^\@G;WJb8IglTh,6e)<mPgIEUc:8qQ'puW$2;K\1U^_
)X&n0K:AaB1-I'lR!MS_V24OXJN47lD:=ehV7;i84h1)-(oX@Am+I+W^XGL0LI!m^Yp-Wu3F
c7#R4(,)L]A8iqcV`38F*nZ&'A0bONj7Dr%OLMqU1Q]A(V^Fg1^$HmRj149-3X-(q=*Zo"aG>g
@7=]ACd0=`<n3"b3O-r$jFUa//5Nr-^t_":@g3b%G'njKrjQ`pQop6i_@6s*0!/o@I@1M'WDX
!5YeSm:b10c-2KND8naAL4ktfhnfkVMr)+jE<-V5n$-0Ca(8tRMfC:[plDcWN?nK6O:tlOos
_S`NWYmG8,lT6qK=$DO;Tsh/Wt-s84eEo9[%Z?aA_?1FV=!Go[Rc&0jRUnn.4r!bX4[Z,OXl
r`UiqiEloj>aIcBSho2sP@jS2aqMZbbruX&AqtFDYIjD2p4kNZ7<?tYP,n%*a2jA:DNN:0T?
_tV?-#MmuMsk%\PpBe#GTX_q?^YicN4%'uhtl^WO^ruc<r)'e;>i?k$bSZ*2[6Vo)?<ti:f$
BXfcgD@mWL%di6r`4*h]A199\_SfTfoYhIof'+-+-X.^MmLbqR:JDK?o%"QFn+S_ckc%jS\*g
:$1=G6]A\1jL)oTc+ME.)8K(B//Z]A/B>'Ou)eqYg!H*28<^EA/^M-e5>6!&INE(I^SOKLfCri
^Z'J=6.XSZ!%U,mPAHq1iRpdeem%J]AILL$b7qt+n7^mrMK6HkMMU</r@JF(I/(-GbDVS>Hbe
,)1$,JOI=M!"fdN3Y(D,KYu'^VjN*B-d+:\b"TdgP>ZK55,W#ZW=H]APk.,aQ5b?KIMlW:V$5
o9$?jpsQaq\QkGV_)5"*`.[ZDf>F>;na/1_%'eEa\HY/`'6u^JGc0L2nrjqPH#;&Zd+I<j/@
AuO0VIF6D]A@['TPY\qL2H>=u(*rC?VNpMlQ/!Qcp3Xn=LheDuR5^:X&!)EjU+"dm^L$g<g&1
U]A[kLiYL2MkgX[F't;i.-1UV%W]AMP#Jh`jU;\o;5GJMf4]Ajd<R(?gb]A+;a?mPD]ARhM>r#WVJ
Abk^iUqAg5]A$#6r'L8Z.He+/K=c=oUB]A)L.2u9ZSQN.S6-UZciHD4>>Y#JWqmK]A94,Y:*Q,J
1A"8"I[+LuMP":+5=+j2/r(j+BYG.:.gqRL;=IbBPY*H(Fqt\L2Ne,?\,7.2Pn%unF,?ksg2
A#(6P0ql=^aZeNX1sltPFB>S8-S/[6G[8]Am:Y`"^/fYZ%/fP97<"kA?Pom?Eeo20/+r7gc]AX
$!^(iAsmZdmZ9"ViT9cb*VQ![3]A..nYHhT/"os!Qbf1E5B"9De0_=a'*YKsssqT9Gh(D<ZW#
fDZ!~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="370" height="185"/>
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
<![CDATA[="   实时道路拥堵指数"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   实时道路拥堵指数"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</title>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-4144960" borderRadius="0" type="0" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
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
<![CDATA[1440000,1152000,1152000,1152000,1152000,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[720000,4686300,1440000,1152000,288000,1440000,1152000,288000,720000,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" cs="6" s="0">
<O>
<![CDATA[实时道路拥堵指数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="0">
<O>
<![CDATA[ ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="0">
<O>
<![CDATA[ ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" cs="3" s="1">
<O>
<![CDATA[高速/快速路]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="1" cs="3" s="1">
<O>
<![CDATA[主干路]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="2">
<O>
<![CDATA[拥堵指数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" cs="3" s="2">
<O t="BigDecimal">
<![CDATA[1.14]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2" cs="3" s="2">
<O t="BigDecimal">
<![CDATA[1.91]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="2">
<O>
<![CDATA[平均速度]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" cs="3" s="2">
<O>
<![CDATA[71.56km/h]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="3" cs="3" s="2">
<O>
<![CDATA[27.55km/h]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[拥堵指数较上周同期]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="4">
<O>
<![CDATA[5.6%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" s="4">
<O>
<![CDATA[3.5%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="4" color="-1513240"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-1513240"/>
<Bottom style="4" color="-1513240"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-1513240"/>
</Border>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-1513240"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[!N#m#reXHH7h#eD$31&+%7s)Y;?-[s&-)\1'F4mC!!%[]A]AlsGV!GtCr5u`*!h35HG!^RNTZ-
Z&.<!n$T@ma`_<nbr'a[R'.^)(%R-n'KKU[\qD$*?$6"UUJ?g5<#/7WcpqAiJQZJOMVX00._
=s3&WS1DrU)N5JcVT`UnKm8r]Aks!6e?Z3$3FCWX1OlE6-m!7tN'Jt6B7,?=mL6-m>d(=NR0B
+d0e6sh*0Se*="]AQ+F7_UMDu)e46K;bs=p,,YaEet.6n>,_rF!8lH4-+Xpn&I&%Ef\1^/CC`
tW`ifGOV'R^-a.?LVG,mWON.V==S"AEoDJ7W_0AKk4.1!hmB'_d(dK75d+Ckp,DoR2ZJ<J.u
/ZkM;KNc`g-KN6[4p=cd:SSJHLn`[[d"K\!nB_6.\hMC"8S3V;:fR"*VXHB?3bnkbhZR"_/^
L8cK7>)L+ZmgNnC9Cpir"To&8eL;BE:oc`8+3@0NSC91sDFKM.*K:iDt,tkDYi-$2^r9qY`3
HOHOf0BPclY=j@9Q!!!!j78?7R6=>B~
]]></IM>
</FineImage>
</Background>
<Border>
<Top style="4" color="-1513240"/>
<Bottom color="-1513240"/>
<Left color="-1513240"/>
<Right color="-1513240"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[!J1?#reXHH7h#eD$31&+%7s)Y;?-[s&c_n3'F4mC!!%P9D+Xd.!D,jN5u`*!A,-*(56Ebs<=
C$a:rBtu\sDM*$UM<%?@FKZ"uY6fHnsYNh1#t?)Z#hK(YpaWhYshI56s4)/%@KP&=![(]A2H[
Qs/9&3rb3i0+bPY*7!!q/K(NG.#cMuco:t=:olp!_lYdRh"/GctgP`B;+:L+!9(Tq5V7JbaB
haWq0/VH7S:f?7&?`cJ-Je<unEtt%i,nRhnUBG`VM@V?5o>ud^&Z+np3itFnaEkJ.[7dh7eL
tH9O"*S]A^'lF#r1@e+W,ja+L.4[`U@R1k?AK4f_'FqdbC@[rWd`_D@hU5&o.JnNMK[W^L&\.
o52JHV?]AOt$t;0NbF_7EoTrl;jT,STnW#OCs#YeAiBcGM\sHRTA>Q&iRlPdhO,DdP5;%C`Ij
7aUli[Vi(AIAelL,Jk'2h?I+C[(U$j$FQ-OHGTkG;D`!!!!j78?7R6=>B~
]]></IM>
</FineImage>
</Background>
<Border>
<Top style="4" color="-1513240"/>
<Bottom color="-1513240"/>
<Left color="-1513240"/>
<Right color="-1513240"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.25"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="221"/>
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
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-16711423" borderRadius="8" type="1" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[=\"   昨日早晚高峰\"]]></O>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<Position pos="2"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16711423"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16711423"/>
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
<![CDATA[342900,807840,807840,342900,288000,288000,495300,815040,815040,495300,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[720000,1607040,419100,4381500,838200,3429000,720000,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" rs="2" s="1">
<O>
<![CDATA[早高峰小时]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="0" rs="2" s="3">
<O>
<![CDATA[拥堵指数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="0" s="0">
<O>
<![CDATA[ ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" rs="2" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" rs="2" s="6">
<O>
<![CDATA[07:35-08:35]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2" rs="2" s="7">
<O t="BigDecimal">
<![CDATA[1.52]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="3" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="6" rs="2" s="1">
<O>
<![CDATA[晚高峰小时]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="6" rs="2" s="15">
<O>
<![CDATA[拥堵指数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" rs="2" s="16">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="7" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="7" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="8" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="8" rs="2" s="6">
<O>
<![CDATA[18:00-19:00]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="8" s="17">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="8" rs="2" s="7">
<O t="BigDecimal">
<![CDATA[2.12]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="8" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="9">
<O>
<![CDATA[  ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="9" s="17">
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="6">
<FRFont name="微软雅黑" style="0" size="72" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border>
<Top color="-6710887"/>
<Bottom color="-6710887"/>
<Left style="14" color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!OiA+p5)U@7h#eD$31&+%7s)Y;?-[sGQ7^DGR+KR!!##i2UVR*$%>H65u`*!m9OFb'h&[?O*
F.,mlUNq(_L73Se7_P:=%[#%^R?+#bGGRW/9FE<5:$DN@68G@YbpWd23>fHJ\(B"d(2]ALu<i
7`(3iqjY.e_D,nmpg"kn\[kmuok3+ItljN1*X8]A-2qO"FcL'A"n$O[=M$O[=M$O[=MMd/SJf
O2PK=/*ns^s@o-gtpOA=_IjRZbInMU6BQZ0/:*=n^P-QBE/mV]ApjRM4D+`'DAF,jDL$Z'J[t
A,[;H`u!/B8CCgbgdXdb"KaG!WUJ]Age-[;H`u&E9Fbe@JdrJ6Xf6]ABEDI\li\H)"LIVMf,HD
bsDNJOR!T*GRh-!()d0*qKIXEk>LVXT`JQB[0d`K$+4Xd)??i%h\7=1;ek,D%8gh';L%OTOL
ms#3JU9=mej(=n9c^V1(PM2?`2F)GBSL!/VMd$\1V@UgL(iB\7o6E4r*6Si8t(!3=k(]A$69`
]AaSS7hl:\"6$ls+]AYV,?l-M<J3Phe(0_8!U(r,(qEQt\\r6+Z4:7Es+I9er_EFhg9s@[;.Vc
a1T2[^T*s3#f'ZA##FGI"4do&cmjTka?01Qe=d%3mDHn=)`A:S0ka-7#7-lN.+"&j"NYZG+M
9R&d*r[)K&qCMOpTodfe`Z)oY7Vl""NlHN`<8*!i<]A6"IkqpDI^OlNo95rQuWKU[P%uSC57#
J;#k>jJSr!XTuWnhciJ&^D$!eNi(Ab2IPr7"<-fA%1R`[DE>Q77X.WTaR)QqnU+1dSKV/UNt
i%kp8cqlE]A1L`2LdB)o.fpd_&,ROi1\Mj7F;Qf7je6g0OgI+%.X`:pc89-'&alj$G<\KlN(,
O>cV?ePQs:4F0;hh4'C##8e"1rkqF6,EC?SR`RuJ[)"eJ4=u<KA#d,3';6[_pWQ2RQh;IW`E
`\&H40GL-:-D!,3uf0J,dd))R#_A'2>a.JLJa>VR*h*L9VeTGcH9C!f9JfX4'sKV^T!OtWUN
N4C7<b^$KLWH(Im]A0S]Ar>(=bUVl4\V^iU-.oN+DY]A,BGhieGIT?D&S9Irh)aN<@Acjm&&m4_
EEms)JLaiJFN<'IGkhda=@5H*T=`!.Y21!R1)Pp2QNcZ2F"k=1l2(9[430)ZZ*\'q0^^c<UN
Ra$47InQ;!6EX"lRhdPTA@s-K&%:C#1TGGoF\*/%ICP%6[-Za6lOD<T\f:!.Hb4$B,hhZ=i+
=$u4'C"*sGO]Ak:*.YkNhg]A/qloW<^!r(_L>*K>fUOd9!!/">$KXUGl-RiU.`9*2XLU?tgcC:
gsMg>;LWPWh/]AU\.mP!ZR22j@9;#c!'Pfm\RG+Kq*Aaj;Zh<aQJ*J8Kl+rN]A3ZKX6JCW4;NF
]AA4AX3R3J?R#6uIhW)0iKd\joV=9F;nO-Fk;b.7MPCj'Ro*jd(B;Kf_L8p4Q(`lo&kd(bmf3
8'Nmt0Z'EOHnttu9[0tbaIAK$?]A=/J^eIc!D&gDC>sV5u;'Qln:L$gh8"PC#R]A/;+$8CsrjJ
3_P0H3O,\ItX=q,E_O"q&\i;Z<c%!WM2d;R<=3NSn\InF%uGH'n&Hh/i*=<Tl6^c8Xg[)`=i
U4d6Ji_'6DmNq$<BXrNd2;^[SUXs9!OkHeL:$sOad%m9(3Qp#UE.]A.*F,&]AqWKsQZ"$Q2#Gk
<+:n'6Vhbgq\.Z0NkU8N2'`gi2u(9S!A]AD*5cGaqJ=Kk.\;47m&/nRF2rBPma1o25e'tUcH>
(**n*K"IeH;%%#i5CM8,QDg?lG"ZlqTsAn?t=^r[C^EV!M[ONM)sCBn/In+L#!=@Z/s=lF1"
0ZWSm%F;d>Sq/W/"L!Ht1[[/[P>seNSYS?NU.h&4?4Qb_*fjrX>2k2j1*RcdRLG:$L<2Cucb
0fPhZ09ePhGCWkaK+.iU\YlG9c*STi%jh5e8t4QmXVaeDH"l6T]A;SZ0T#@4@4(Is&WX,-SfL
$JS>8/8N1GC.Fak,L!4d39tEoI;+JmA;+T;+9tEn>9ZB[+dFQs*S9.\]ALH:1=-Bc9-5uM6&c
^PV+ZTQILYEG)f69KEQ2:j)R"(+YqIbb2$[mdtkM]A6bW?bar,Trq;[l=uGqG-`f-Bb3/BV!s
Rj.k6`o>qi)f5m`1n6(&$BlOrqTD;HBkd]A0=2AQKW^2PM?#d+b`qJPl%F^.E*W3h)See04Od
ag;@J3Rh<2&i(:u?%ssV_A5Yt-nR:RGi=H(hJ!_[dhaIlBO$?#)k=IK,ts$:h7hCoAHk#h`D
;9IU8m32;KJ-aaK$bh%hN@m0UdW_X3@:_FQ7:c6O([OE6DlkF-Ki.6!)7*@=B':9GL]AT)a[i
sP"b"lV<=3,r@is30@3^g:9,Ip.0Zf=*tF[]A2QBfO1)bbN(W[D"Z6k2c.dWBl['EV@_5mUlp
i`_H<L@iA%&F@Un?L?ToboIq<0<#>Z*]A85W6CRNg#Up-),pH%N0($$c[aCfh7K\5MgiRh_TN
6C[1D/7L[F+bpVl2EVA+K!j<ue>>AJ[/N!B<f31HU)=qRY?cJAbr0CEgA,E%?<0X#S8<B/Zp
\As!fNKfA'OC`Xq]ApZ`F479a+4&(;@rA[DbBPW&hlU#+LWM..3X)MMcRuYlZ:(kjC\,;?uo4
XP?kZ;e5'4o5%aE`d>VE.<^'Vi%<>]AE5oSpV8fCa_hg?#Z3U7N30;[s9OSf[,9!\lm6i$1=_
#4JNAS]Adn,/W3N$dV00+ElN(8^fSB^Ub!HeWCtNI_Lb_9k2C_A-Q_6W?L,<S/BQ[*-WXj=N!
f8",Ucmr8L?U^*ek_;UXkVFZa.uIe+=cg@$Fd\%oLj0T$QCd*XML$1<0+93\gYo39^7fYi2.
UbFEWV.7L?)6qHK`1c=["B<(L`Zf1kS*"dimeVB`l9,bf(Mb)>qs0.,(Z53iB"4=f#,\;S-=
01?7%eHrd:#r]A"Cqj^*0+O?=*kIk=0L?/Ts#1kB6-<4g:/2SJ[%\?+kRtV%p?f/]AbRT_WX%0
.4CLVgiJW-,Gn!+iI&U*`CP)a`PTPkcOU15(b#`:KpMZd8\`q#J_&;sS%h!Qn(,p*;.1^oOu
]A0%q==Xdb"KaHAFUHE`TQ@8nU+94O)*ppCafG^1kVmq5kb>$+m/=BJ[-=BJ[-'Fk'CiR3EqZ
3j[`z8OZBBY!QNJ~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Left style="14" color="-6710887"/>
</Border>
</Style>
<Style horizontal_alignment="2" vertical_alignment="1" imageLayout="1" paddingLeft="6">
<FRFont name="Verdana" style="0" size="96" foreground="-35328"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" vertical_alignment="1" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-35328"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="64"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="4" color="-6710887"/>
<Left style="14" color="-6710887"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="4" color="-6710887"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="14" color="-6710887"/>
<Bottom color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="14" color="-6710887"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="64"/>
<Background name="NullBackground"/>
<Border>
<Top style="14" color="-6710887"/>
<Bottom color="-6710887"/>
<Left color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="14" color="-6710887"/>
<Left style="14" color="-6710887"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-4473925"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!N-0)pk_gB7h#eD$31&+%7s)Y;?-[sGQ7^DGR+KR!!##i2UVR*#B!+$5u`*!m@@sI*(:R^fD
E:t)p]A%D:dJ%hLgn;T=OlH5-8NRkQsblmL^-d6T'Ju-"b'<5LkmH)";*:t\n?62.Z#L1qB:a
-M_Q9h6kVADlfKVQWuf<Lbcrt[ebuM[h[^d?UpJP=^&M&S<HT<F4Uf4^%)Xb-__;M:0FYiQ%
)Xb-__;M:Zp^1t"B?-d<DdX1Y'cbnT@BWjo)#Ker7S(qY?.;X/+WROEJ:Dr.<e)-pWO>OS3L
8(cIL&PY<WEj%Y!iqM0?ib]AGC>ACW4iuF`*38SuG5<l8iuOct-3C)\O_=`8\tCp\!V63:<8.
T_c8+"FV)&XJr-uP?V"+nr^3P_ub]A]AWP72RQ^\&uqn0V@[A?qe+.Ia2E,`%t!`eWS*=Mb:Q*
3<l\1`J5Ni=kjDQ^=cg>f<Hl`N$Bp#jUVm8'HR9<L5q'"+rZ@Jsj-1?8@Y\Pp>:^B440/_T+
ids`SUpr6*^A8VgI89pPITB(T#iqasJUM7IU"]AVOX"tm=`-ZOo^H]AL&P>>a/r>$tE\Dq_D5T
Eatb6P<E;8E.[PpfF'$07<J&p]ACC-d'mHYqt$TPc$CN%8YMbp<HB4=e5pJ6&'`h/c5Ic:\(=
n=p[&ip:K@Q(\I#i(gWYH]A<cS=/%(ckjh'>C4+'OXj"gpXA7-not^l<lSduB:Ob1.cBYe]Ar,
UJfIm2(>[D=M.?@O":44(YG-AiJ//HOuNOVZIuat^;*O.`8leE2*Pir5oL(paMI$a<22ZI,-
Eie?BVga(A<Aqq&Urp0AmY6_?Y+G;S.Jc??G19l#C<5a-g(Ph_7ZBrFb"fe8]A(5j^*Cns*ck
8JW').l<#hK,_%LoEPY7V!d1T4"09[K`:Y'P'<1$:'`L(o*/RBt=#e\aB*r>&hAK\b`@>%dV
2dPli#<@Y]Ag'OeE)FoTN]AT$^l+Ju]AENtCeo;Ucq%$9fjYmXg$mnh&q$)$p^'>M&g"FicZHR=
9+oIPj731U7WmaUd))Em6Lb1/<MlVa7tGu&FR4F&QI\A#`h;s,:R-r2rt/$LE,p)RLQqTEC"
@kYW.?;^liTG"6>76.BjK`_GR5.BZq7SMtln`[fKW+KgeB/^%c^Zb=?e-'P]Ak9oe![PFKk(S
AUA5lS+*mD4rQ0!8QpmSIJ?>ke6(^hpp<F7eHm9CZM/IDr%U7Hq\hqk\%M$f5i^;scI4/_Dq
#jjHc<0,ucOMDqJ0HOjlUX"3@Ws.p<W`gKWgJ>=cp(Uh=GEIVb2SQc/Ck#ZQbdlMGn,e9lH-
#Th7:-5,JY;kP(3S-PgIhM[.8mC-$,.XQHmqD3%_.pZ#r+YUi6XX]AN7;WUHo2g`V)d00/7u(
95+'g<:_sJ7$o09%dBlA]AX^tZs7D/KHHq9K,O`KF*"(+%Y%1Tpa(3OP`!?da)pkndJ$7P84E
PG>X;AR#35jYaO+*a@YV+Nn:[g:C=,UCF9MjM?R[c!EUQK)WUg&]A[Q8#:B/gaCILRWhoI3"m
ecjq)VQsTjj"S+)O.PaE-G%F_[Lm`d%E\De(`]A\'\XeOi*LG]A(f2W0Q%k&/R#W<e[;*!XO8c
+^D3I<buLJSq*Pgtl`g?O6i[9!OUDrGjN)TEjDe\uU*#q.oQ-!sXM?$@i`6`B3OB\qP,&mDU
q*3.ea7)#\[2uss(_4oFQXj/q0PLgJ"N6F+?=A%$Y7KB3bTP[1H!+=$D>"Q.D7F49=4Ua:Fc
#--W&um*5/8Urs"0`(umr@Sba5k3A]AA`l:X;$CQr9_^&.]A"5JZ5O<*pI8%[Ahp%,eC<VDO)C
oh"R*/#Vl)>Iq<>8*e:koGVF>cIU&*\@GpoTGOcDFN#PP[p@ca\`hgG)8H1WlmK=:CLRE9!k
gD7Z6en^6TeN^7,CP-\7F62,0tGJ"kaY:0+!rgkr`]A_mZW1#4LBi!@19lYN#cS2>Xk;QCJA\
B`OU^7aIDhD[*Ur2+LO*s^d/"(WZ,aS#DK+a0a;_%c>5J6,cg(MhkN/@OW<.1PWZK7R$YunC
CqGL.Al`/VCT?$WATJOW30JR:Gdngo;H^P$VD0-^luq)'-=3aIFNP''jkMI)L^UT0VVM!X<?
%H-1q(Eq$;,'a\b%R2#-=h[b?%bo8Ke?D^=b.+"RuC5fo;7<PNT!F\]A&+H%eU.3K(u_$7G8b
@:se[*[o-o8N.CqAh1^\8^t:D$oiZ(2o'Bm1;.pL9Wl:d(t^@3c<2@-B,Mub-,$[=>g,6;8+
fb#Shk3f^$uD(;StN;PeuEa0j!50b0JDa3CtOr/.kb+>=ROE`cso-lEAju"#+6(5;lj':`0j
LAPQ&b"a1(IHr7*EL"8D0X@s-7R,h&o8(tQFJ]A\l[g`1DFr4Ya`.Q%mf5@\[^r4^8P4*b8/j
sMrKH@\!"/1L65V0=0B^+.#S%)Xb-__;M:0FYiQ%)Xb-__;M:0XgL)5Q=r>10pZkz8OZBBY!
QNJ~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" vertical_alignment="1" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-35328"/>
<Background name="NullBackground"/>
<Border>
<Top color="-6710887"/>
<Bottom color="-6710887"/>
<Left style="14" color="-6710887"/>
<Right color="-6710887"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.25"/>
<IM>
<![CDATA[c$F_!dsq">7CnWL-$QnI<(Ie89r)#aV9!54Ed:0nYhVIB:FNd71<(k&PMC1a,BT-fd$EIhEZ
'V^Qt8)N9dH@cn+6>RoBkisk&i]AIFe7cAT'$Z5_SSiWrU0>/<icif0fD/%@`:s\MA<2Qf*_"
2C#@P4CrM:FN[BMWVls]AkUGt^%oms(uH40uFRbU;Vi&^Rh(&tL8O6j-8A`Yl=RrldIpMO3gZ
,<;Om6f>#NTp#ShXTo!^R_AA3,:;d^Q!P\MdjTQ/31<_7@ru9)k14EI4A[S))11iV("2POJt
mB>bU^831tP5Cgmh8lK/FTGrkg\[o'f7Q?/)[AYuBR5=N#ZB6,LFG?S*P&g&XeJ'k]Ar(1I#U
U5J*A!L'C^<'5=,=igp8m?f=,U0Z=]AhhrFX4`?@5R-iLca%dU?Xq(g0ZubZJ#4c+g4T0Nq!b
k<f7SGH'G7c7FoTZZ_;NTaE"@t:mXSC#!\`u/9MSkWP:\Ac`,OM$V);=&9TTMME"+BiS1%CN
:[p&O]A4YWD8ioZ?UrMHFAIIGe'9*sW%8g\:HYbUe!YsqHrTp>DbOJ_m=alWR:Vp*4B)=\!Z"
lZ9AerWnlYb%W=-;AQ`Vi?EnaetZbp#*ITmOAfH`A<MunL?*so#.eNKjoc?mt`(d>_,XhD2G
rmSYcr_oED=A&\+Xk-VY9o,OP49PTTu]A+NA/j(g0.E&o-\ADLQ,OPh'Gb_DE6h%nZ@-i7"lg
Rrm`?Qc!ok5qU70L!RLs3HgB1L<RcX*1i7(&%#ed3EKpM"s4<`#;umei_a?:&)a/N.n9%n+6
FJTqq,^TfrG`L@$LD!k:$"*\J3ot_@95hgiR-4')Bksb^Os'-PA<&o,QBFHdg)IrM`)`Lt%S
\?-9eEc.(=W:H3T/_@;^;&d)shjGIC\f+Q9X&`3s.\+'Q+ZL,VufVFeJq?YI0#a#(/ct\!)\
j!Dt*uQ4*8a"n_"jrCO6?]AIW66:d-HB]A]AtA>#MN,#mQ.2fs5Ma7Xg#WD;+&"3[ZF-qp\B-M>
.&koS@*!rH?of]AC%&(0/MAn[7j1B6@&gWXLfQ+jNDJr6R:.g'Z[=5!J^/8GjYmXOOSVE;[\b
QAs"]A155.ZpC)9CnCJb>Nu5g;<[%gl?3UK)N[n$P7)@K,/0Bk@]AYBBT4!,Cl5b(;g?O8Rdf"
6)"$RY8P&)\01ATR['Ss"p@=PDJTKh=T6eY3EK_>gbb(LV1goS8Uje^"Hi;RtI)m6n4]An'\R
ooj7W><1?5K./-ElVdXe'QI<]Ao\I`7QE(#8J\3fq+Ug,'P$ZX<jS*\]AaQOuGm_/cBh8F%;MM
^J>%K0q1@"N&=8A\`Sp3h-mf[-E95l?<7fl0fgcHTA>b@6O4HCi_BL]A>=I>Uq8WbU!;Z'J'U
]A`Tgq;jk[pCrPqeDc]Aa&#%KGhZ0qmP7*pUXFVN+tmE)/_Hp+-!jS6Gta>4MYsMb4js@5Y(?c
_9L?;FVM%4o-O9>m#dcIb!D=3$?i,g'&sjaGIHAo,W\3&BZa;09\Si:YnRbNm2ZBsl/$o[<=
-]AZed^0Y*?RhBnK5`_><bE]AJk?o]A-$aP]A@8=//'FSm>rl,?m!m3RQ!sbj8hh&-l2F91,&8mh
7EUDg**@=p-<7K:.%+M",P;&4Cd]ARe!)F7f)K`K,WXThuM\q_tC)Eh)#:CO_p"IB+T1I7e,F
Q%j?_82Q/C/:L3f[qFs)Ei?);;ujdh>7(]AEPX^i-:4eP#HJ#N"UEU`DNH&)$#*89V`84]A$WP
P*[5m^!^N^cn;^M"I=J_tMi7NKZ.U2J.&>)6UG%V(G4DuuH.L0s.UQ+@@=k6>)P>h1Ap(;n!
`MZ,oG>(Vs_'nV1$YaJJg%;iQWn/duUJn$ZQ)VD:o$5CHR\2P=:s[_Z,*(>WW2]AI%J)ra9HI
oN'l0U*q*JgEZIEeImY<foRn<73=QqW\=;[0O81n1qi:Y24W1NNd&"JU&U]A]ArH?oUS!%k^N0
i[<nh[+J#/h0Xt2i2A:&Qi]AUi)f^u^qTl&CHM#Dut8PS=tnjD)K#ZU:@^!iTdc`sjj6gF/0F
/VsA_CBkoi@I.IB7b$#`?`mOCd*XT\uJ.aSqRH02%_D:P0gk/1uB5paoPX3Drh!Dd;QZG`rd
`Cp#KkHkF,K>)/5>A7h:#KBlSH>enjp9Xdsi^1ESu(Qh4dG[gZ+TL$1PVb_nFp:?1cL%r/M<
P>@_q)%^]A8-"%oq[MUk0amqE]AAtZ):=Xm;2EU.Crn'";P^*2#JU?@W%b&2-jd&pn!!9Vjs<6
nQ?M![:8"*hN9aq-!AFrKYhI>pHr`6%Q'Xn&Qn8;kT1U""&&QWCE/3Fo!3[CL?NWP^%kY10h
fKb?kj$^5pQo+QZGSQBKLY7a/#]A(K*q;K$M`Q?/CXSu?TkQ#u$tT/s*C6i59/&KTi`?E+_nS
V"`Ud9:L(/hF42<h7o'K$\#i,p0&r=%U1oD@,k!mjOS.PT>l!jNf!.\;]A?;.YFf=e;2^dg\=
]A?/Q5IVM4o3liS;N:cn"d62_H-e5"Ch:6c@j8,3n8^?rS0Xl7TdXW!@>dPSt=-c_^56:0?(P
(q.B75]A!4#Q%lgcq4mo5qV&iQWTdJeP5ODH-qVNb5@6E+c]AX]AY_UNAq6>teW4i2*MHaT.[o6
m)Ns+gXd1i*#k?SD47dYX7P*u<]A-G+Ht<mu/0?[Ri8G:&d*l%WPa\63(D]AN*C=Nm/Si\N']Aj
I8RB'(=ILAfQ`H9]Aj$U]A$SVf$(eiHPjO?AMIYErp^FIK!$f1>+:")bg5hMjUm+mB4i@FEI&Q
\(.-G,Q)ibn_":A9*i9kJ\I4T>M00]AsN`=l;aC$L3Jgu=6>[m:'@SB2;q$kRi1BVcC.nGm#g
Y&JJS8H7T7U2),(43Jk[,gbc'@RT8jsm%]A=6io<%V;4lYtYY65W<[rV%VO:pM4c+TjreZ^/&
iP%FmbdrorY+H`MC?.)'j.sF4.$"&5rLJgZ'"dma1^.,?NQ6?j)T)\Xb&c\bH-<4*Xi_nX0:
!YbQ+?`Hi_rn@JBQl1_d^@>Au!?inQ8DZ%8*,_eW5pVm0%G,n@6^,b!a+$B&1U3,B0R:gb%/
RdDkUXDsA?(fN.^qc<&-6r4pI8K!m$k"r(`OF[2L<bj!r(15*(SOUgjGr*F:%YW,.+6p.nAc
I^f*UVcn4qg&1<G;2]Ah^FoQ:iNpXiEQZ<spSF2/4Rtkh4D&g292[^<>Gg4`f;%\cSS#.CU(:
E0OqC'_HJ_%c:'HLJ!!Rt^k?K)OSn:&+dZ^,GV(fRt/"%E%Q$Dj6^q/'eMn,,n7QP*3N>!Cr
NHZBQJ<"pa(0L8n[(`%oeU>F==g7A'"**[Z<R-"SQBq&Ia<B9kh4M_N-5A#`lc-VRP7$mXJT
_3lWoS#DmpoQ3@?\1t&uTW`SUfal2DfQm&>uR/L&qrQi?;<5.7//Z'Z/ou,A]AG]A$E!$(S_W,
"=,7@UUNAGQLEElo"]AqHN#/3iaag6/mg6$J5P#0gfA5("GkIr.*@apOB)^[Bo%u?N:e'5V<M
VM0Kq#b8a!`;)94PQWA8rTkXK/5ju$$3@LiB-(7[GOYALJeJibW1ljj,IEFBQE<2Cq)UhA;@
iRaeY00`KtPtBeVb+\%Jop\f7^A>et/+TR9Im1V[h?jiqf)*eHdJ73WSsnqkEJ+'\H4[DUT>
_d/;-Nr_pp+aNt7WA>"=0rMo_K$g?4Xp^$<l(\X9&8n9AX1[t3R72u_KPc*l4G).OZeV,<Qn
cM8NM=D=nIu%*VmHq0$P7X";e4]A.N0b'/X,'QdY+ZPP6E:V;HtRVee(?OY*FXqK[J"%eVH'Q
2]A:p6+Z4^I@A]A>IK"%3P#T^l*7G(CE8m=o//@u51W<5pE]AXpO?Q\!qYfnYrdQr48(DQZ?.l4
lfh&Z5M2snR<s!T<001X&4(;l<DUG6+Hh\J0N'aVsLbUa]A$7^s#WPNA"8n#K<te8kH$cf1hN
FQO,KO(WGoUO5G$T<cFSP9Y>upFml#TOM$b<5'M+1gcnt-9l^@3]Al:"%]A`i_X6QCib#/[R9;
o.fP*NKNhH_7u+8$p17pAorEHO2TQS@FSIK&1^PE0dR\[Y8`f:6p2J2h+,GY9;)EZ`pGNICF
W<nE,&1ibQC@s=e6;=kjVa.qm2WQ4BR??p$iOW#Eb:<$WVNEUPH!p[lKUUe^o0&i31s0k]ASP
H_MD5N?LlRjnKR0\h<]An/Grpk+1n,aJPRLe1=9#(0`8e]Ac#L5;=CWoMJQPY+,aK0<KBc:7Io
Z@u-8!`D^0052$IP\%mgMIm059?2)T/W(R`B9R%dLN8UM=.%qOLa<l"mLP<$;\r"BMon:`+,
X&8b,!(2R_>H>i"t8pUV(7`9qb'OF`?3Mu="NJ?'AB+'Gl-F]ANZ<O1A^bC;9dEc@8U%pia"C
-Gukp0*/0,C"G,p/p`;Ffl(Vb9RC`/3CUJ)=eu^jLo`F-#*j,4ZA*J)m[l6p[//[>9m!>KX*
%%mcYAuB=,tQMRn0h^T]A3<c"0mAW@abp1gI%<O`k4DHmq':6,;u\!bPL#iG,]AU)7T5CNAl<l
i6sT8\0/1Y5"K&APpFpTW/d>E/IX_c/r5'!OOC"/&\]Ah)kU49L9!*Fa&*m(dFlYWjhgJs]AP#
9FN=]AjCj_'_3-p=Z>8<n4[(,?!2P'VMO&JB8>U23fNJ$,V,/!JIa.jeau6oGjmbq)Eo(o?2m
A,QZ[_8jIL>%]Ac?kO9%e!C4FPU@V,H;.[A?6+`uAZ:*&lWX(:&eSk<K+3RQ+0oGS>SD6"M))
1`o$nUCf/e<lS]ATim9Y`@U_F01>i4.a7Pb0XV%NcYS$R#9!<-_/WCeQXnG\*[1BotClDfO>>
ArUn`ma?L?_Dnq-e2Y@QU]ATKV1N:F:<)@g)&0'(u.L^g"257>En,8/n@]A^_t--+=N;^k$Ak,
,G2$"FEOFf[JgQ@%l%(G`frOu62J>N0F9VP7=Qiij]A4?Z>o;ttJKbE&0e>M"j/82D7qPrX3W
*Wm\2se/lIP!V.L"4SI3]AZ*=`#2cbTibcNXo$6_mO!8:U<dl=321)V5:[L;/$ctAcoh-E?)h
<ZLU(P46P"LFmbW-7]A9=VI[7Y%1KCI=A'>VQQ-B/7;0AmsRU$;qmbd4C:8)`=nWER-.43)J4
2bc`BRW#kQ!B*DG?6?<IEG)6K]A3l&]AF<\LT'O?a8De>H(n.BfaKeMo+(2="adOO"]A0,VaS@%
S[%I<rQ-orcT270OBoms`YId&q('3g(Q0'WYlF1A*?cP.bnnHZfQj7<[isCYW+'Sqea$[oQ^
a(t=r\$Ee4Nc@G?O+YYD;>.fM^@%tXpl^D]Ad=8pLI8qbgNO)it`o<LHAL>:$Gh)[(%q/Z9QM
)n#]AU`n&]AH>+,-3V]A!R\U1h/<bXfWIZiFJ0%]AFn9HT/p_,JM0+?35U?p!B`gX`GeMAS+^KUQ
]A!8oa7eLo^Tm=iD;LoQ3=_KFB3SQoOE77AuBV:7`pW-*OS%]ADD\-LR[b?c:^0%4h3[dYWtU5
0LR!DE^J`^J:Kc%`(]AQ3c(2M'?N'G1.B`PC<Ih`q8"Eh4-Y$G^nFX&PLD.p)B3%"$&KOQZS:
S#iMlO?GC(;`,PRM9pXe?!:g!f2u4#/=.DcPQ<i"-s]AP=obN]A+GJ1.MUoo&tq6]A[N[H]ABR%2
o[e2T09kBb\N-eC%q/-Y^Y+Y.PT%rE2W)&R@>Ydn17F`ZI9iDE:EL\E!ZAT`Zno5qFm#U`B;
nSV[fBjct29ZkTi/asp[@'2<V;CT)/JkfR/m'"OO#02;,!P3-es*=*$Q5;fm4#9mCHM7s^H_
AT2g4aMZH-8m<FK?_"8BV`1]AJS8&XRoYB4B#rZ<RQlDH(K[mE&:-XdM:'=%2Q6NfBepM/an>
O.+.8r%k)ns.F=*g)kl`L1P+<kG@MBpdPRadStSdrk:im:PejrZt,4"'BjdiC0nhJ47h^o(_
-Y?!5K-4U?Z+"R/4bXH^%8qI*p$ZB,disrOB;*plsa\Pm^Q\"j)-Y8QJ2ch4L<V<.uN3Ncdl
dNGQs72SQaZk3,)'9X+9Rg_f9'KP]ANSF9r:BpdILLeoPrG2)_V.lO`W.BbD3[]A7c-RAD"k9Q
+47D_XH=X[f.8<U_?,84[/E.AaO.<0Zf'5iA`3N*^bmj8m>)>@>8p@LFBbmip_qgFdMmr2f4
#X04\@AP`/9Nkbut:b^j7;Li=PEAaZWeaG_&f31OKoNW]A/QPj8KH]AF8D6-%#P;7bpo-f=[`N
/IsXEeA<R?.&8-gj*#(cj)h3FVM+$uEEV&N&h-=I#j6=]Ai0q3d^%sT36;-?L2VD^e*W:e#L^
@VM:fX30&6h*;+"l/2rYMYt/NR($8FS/mI*+L_VMJ`Ve=>TO9%]A7-q+g\9+[?JW5=:t3R:W,
FT5L8]A/e"AR%e%DQ(L`Q,0kHD/k=)j/M5_X1)PZBsSg-*[o9:38<J+IsF'DtWg"Ue2+u(nCM
Tumc>u;hp2g.0'&2%2obo0_"eQ[9FVd$kd2dh!,/(l;VMH`:cqNg[+1Fu'0Ht=KT4BN-`qJ<
%\hp^M3R+J)rb+:Uc!EV<Z\6g<D"m9siL9Jq5gp+(:pJZo3h6dbVAm.MO4o+'UeYhjWe\&p[
\,&\2i^k\ej',:u<<,-_gj?99949."X?o6Y(?%<RBQQ<R952j2h*Z/[p46rleqYp<]AF9tbN&
VQTT@,t.1TT0MLOL2G]APkV03fSGj/#X3M3]AO*H&nPlE]A!J498d4`14LoM2A]A4)#<pZ5[;g]Ad
%F/Bu<P^dlRf9mN1F*1ER4mUkD?1[G4gN)p_[F5#=,Mtg50&Jj*I,a_KS(QKYgoJp>gsM0c@
Bqh2*h/"<F78JFXE.,O)QaFh*XSWOZl\KY>GbSE%(P&t79gu+"G2OZCo2>/0Zh/IlpTE\9^5
nbV`ZNTaFL2>ZUK2LQc`MW@I4S+F;(SI%[1PHD(^dWS=Nch+EtEaiqri3a7Ws9EbsQeW3hO2
(TR=8rAG%0[D/]A"YakFSQ;\piZ?AIJR%U^%f'"9'p;j1]A3YcMBcU+I";?p'bhdDMPfaU&gQ"
Hq!&6;t>;d)<?j_7]AZ;@6Z-2Fff4`@=5RafO5'6X]A3$,9PdepC#jk-ITHlGSNt6#ViP`WCbP
BZA/uSpZ,YTo4NB;dRtH!VphmoS8@(52Fm8s>7t2S&Vg3d`#cGVqDUh%Ba')"/`Qq\W[[>s#
gJ?S*?<XQnn*`+_U)H.m&7@7!FbXjIRs%'@=Dc-Zn=Gbi1`'?n+Qpq!BFUUTBoG3@D-opE4N
6c]Ae-$/@Hs'74W_&-1=0'Vg('?#W4[%iLlJ)gbFnaVBk4C*Nioj;=3_%r>'LA*iHBQhJ@;Cp
:l5Fd^B[eT=''K5U#0(4\W]A\'G:P?O(opma+*'DZn/@gUnb%30AW4mIi''O`dH^+IKfn3!jZ
K[-pM5%3XWVo)=RB-3c>7!B^G!2dl)n]Acq45a>$452<j!'c8!_*StIY5+h#S9(PZNuN.Lm67
=I":VDBtUM0+sC#R)1RpI&jeUkIXEQF(o_!B[e6&qf2hL]Ab!IVR[p+IXo0hj3.9OY)STZ.Zj
\Oo;?GbR\[8.b1V,gl_GHG+@iOd++Q8-?ud79<D`"s`_V]A7NSm?#>lh>]A=_?''"f$2oH')ja
5R0kt,92C(FF^MlVVO7L./aCA&Wmc4:@HlMlNUV%#plMfGAg@c4_mU#U]A9YL6V`HB7'MJrYW
hmqO`J(*;f,7ajWF^qH8(+&"r&CUfbSS3gQY6m)4,NhU/dhPq:R&fl:BDp>k,K#*+nOLt`X8
#UL8LqN)G-bWTY>=o%PB[\4=)NhUMYb2*0s3*%`YH2u^8:f#p(.B,C]AAFY@Wh4>i"l#QdpO@
eaGTVNiTT9sXP=m<?J)Y:.d?a7MKGWbL/WHH>8Aj%Ll`F=Q4R/;LOJ7%BLL;'*<(YOFY&X)G
0F[)gd8&PU(u1ec"41;*\]A?MKCWcupK?"kjjF%IM*8UV@QSZF^]A'3M8d)t38S:QpcO\A%eHR
7c`>/JZ=8^\LAJ!oCo]Al:6fKGdEQ<5ijefLgER.(3E-"`!nPDbNCs4adfndp?XXT"AQ$Do@N
jMHu@IB3?/jC`c47dt^(SY%K=KE(q)6>EUApL!far(Jq;?=4sbZp;=D>ek#JT@bR>TdmP`fa
fN!6Y_u`iP/Wi%S@8h6O6ocXontj.VT_%,%Z8-bV+bA58/r(U2TZe0n4aaTlE$g'oi&+_C(i
2MV`M5dhB)>bBVmS`\'cMEm&L!296Fl#+%$PPVpa[8E8,-$O77#9brKr:9R\l[TrPCp@u6k9
>TdKiC-aECEqC;gEB"['_AQh&O$S4]Amjd.s$)m8jf&d9?r&J$j+99q!/p-!<D+o]A6$<?1GnF
Qraq3[#7>*Pb-)@"*c2WW@^Z`<t#g*[5Q?.pq;,n=fY9B*9\:cM:PA$!/OWsGR/UHOG^dY-i
!,a:D?;@+hA/9$q_%2Y+4^\6-oDI"N.a<u/;3=XEA['A9pH;dUBf,bQ1]Asei`RoP\)Bq`\/m
J_\JYdiL#O.T\PgK:E9A$BN)rtuXrfb/Bk&IXa6L)P_'NY=u[uZ'>Eu>>hmGCE!s7i;tR"e1
/rt!P535S[L(Q@oaJm^EhrqmVcZY4XDf]A%7Jq%Hcf5kQQXMJopHpMTnk="m/>ju!MO,^03s-
-qDMr]A>DMaX;Km2*P$,[PI%IMhS.VE7f0+aH?MV^\"&%`.>YE7BDItn`lLgjJL8=?]Ai82eRt
VO5ug.t:nG,hesZc`F<5.En$YUqF>+l=-,F`eO2JqgW3TI/YC?]AY%=(X>)VG4Z==+\d]Am5m4
nCuH<EReV?<ao6)UP1<Nca)T,!P6Rr"Xbo8"[dK^X]A`+3Xq3h6<dd!5o%`XM/7-'/Qrc,6IK
c9Y!B0XXBWe6)s,<pDGs1r53.Nm@cmZ-rKutrTkT&A!K&;0Oj+WagJP0E7d6qD]A>GadaE\nX
iIsM=Z;O[X2$UrB'EgGs]As'9KIm$W.(V%68G/CJ.id-8^74Ccg)e#@;6W?dkP',4H'9Bk[ij
aN:%e%'M8pH\@IlV`S,J'dHk&PIiN!$;!/f\=!0`sqm7pbtj1J^bIu^^Ds[J,^=DJ+T,+/9?
H5nB@en$Ri""ems=)r0/__4PNijdE?8c03+&oPuLlNRgrW(=opD9KDfXi$/rEV4P@;Xo?Ye4
8/^Xn57f"\%EbqK.Kcr*:"k?m.S!8YGWD^Sc!]A`m_C^X0IJEV+>re>SJXMRbA`H(l,!#`u:g
PDaJI$P/,=q>n`%Zb%Ts76aQ8!E4.i4*@7Bt++O)9^*O4]A?:#6K?f:WR,J]A73u)at-2Dr#5i
Q%fE7nRd\XmAKbCVf[eM89=+g.]At*2BN?lLV!s8I.Xo<N[-;S(IP69]A]A-qh]A<GWu"of)/@q3
T,#VT+!:^TSFN4^KsYtnq>T8c*ZSJGVpkd"h&jt@FIb%:l4:,U-\J)*R]AW4^I"bD%!@5$Ul-
^tQJo69f/,J(h_CN^&a!6,TE!&DdsuA0`0r"IQL>'E,,`-]A`"6'\Wi)/e@2Pk_oa`]A#H!j#N
rL`*+j^(qXDU9g'6ne.72K>[\e:!R0BLr]AYj4<mIOS2K*gAOEZ!\Yt$^IT4;n!mWbheA/$_2
!UY;br)pE";n=_bT@I-e%^)b:64ep0Vn@ju'-WCJoRlfDZCi`2`*F#&5J`QW8nop'd9@mP^=
`.HqFV"n0XqR"\tB_7>C%#6~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="370" height="185"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   昨日早晚高峰"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="370" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="   昨日早晚高峰"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<border style="1" color="-16711423"/>
</title>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="1"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
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
<![CDATA[1440000,1008000,1008000,288000,288000,1008000,1008000,288000,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[720000,1752600,3848100,2781300,720000,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="5" s="0">
<O>
<![CDATA[昨日早晚高峰]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" rs="2" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="3">
<O>
<![CDATA[早高峰小时]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="4">
<O>
<![CDATA[拥堵指数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="1">
<O>
<![CDATA[ ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="3">
<O>
<![CDATA[07:35-08:35]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" s="4">
<O t="BigDecimal">
<![CDATA[1.52]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" rs="2" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="3">
<O>
<![CDATA[晚高峰小时]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="4">
<O>
<![CDATA[拥堵指数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="3">
<O>
<![CDATA[18:00-19:00]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="6" s="4">
<O t="BigDecimal">
<![CDATA[2.12]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7">
<O>
<![CDATA[  ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
</CellElementList>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[!Drp$rJ=?G7h#eD$31&+%7s)Y;?-[s'EA+5'F4mC!!&"8*=VuK!Z4Ks5u`*!Xd..(<)FKfYG
d^7'ngEld=h\/C*OG6p^[ccnd^LrSRK7In'FO$08rl'd\[]Am>IGqXENMlX1Dk\9/ZbI-C!d0
m$R3,EmkDT-GWu<I05'^`^\<#'kIg!5;,#G<S0A`,onUHD4uZY85s-3,+V'$g0*GC04p3ifD
I=ZN==!]AF5+fSh5O\4>RKJ++X158^dY<4)R(!2BHB-o;SPEN.5Veb?:TQ?Z+[-Q$`&*p#$/V
5/!DB(F"4i7k.cON`6G[41`q(O-Q]A1C[aP=g4.!.N`M=^q'_97jcaF7ts,*5<`VKLHC':>4\
V7pTKfnobMRmN:8lak1tB@EY"<q;`3Y78B_Z31r$`Fd'C#`i-222BJ\iqAVeo_ns2^'FN]A+H
o\4iE&G5/rn?sGp`?9IN+4l3W'4a]AX_9b(Z7^+iD8He$fD;9Z8JE<cGD#3JQ81s;V_RX<Nk=
u&()=VHZhYr1RUp+J-/P@Gq>6Fi33q)7lPDZ8Znk2hA-rf7W6_k,8.]Aj]AlVbOJ88&II;:7bE
3NJ;(OP*Gpk/?qK*;7!R?oq)305r=r"IKWIn^lB!jN9?K:?h<j=OW(Z<&eR=.+D2JU=p$nG<
=aVfoj%7*C'E!n%qD?C[t<Nrp:'hHn[>n14bZ*3\3:NLC(J^l?nS9Ko\9\9WEE5RBIKni,Jr
qZkDceJVqO&cD\0!!#SZ:.26O@"J~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style imageLayout="1" paddingLeft="6">
<FRFont name="黑体" style="0" size="64" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="黑体" style="0" size="64" foreground="-13421773"/>
<Background name="NullBackground"/>
<Border>
<Left style="4" color="-1118482"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="64"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-1118482"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="64"/>
<Background name="NullBackground"/>
<Border>
<Top style="4" color="-1118482"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="黑体" style="0" size="72" foreground="-13421773"/>
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[!KR;$rJ=?G7h#eD$31&+%7s)Y;?-[s'EA+5'F4mC!!&"8*=VuK!`hl\5u`*!I?_onN):G3lW
OF&79YOO\6DZq9-[=BZ"\!t+IU@,mpg5S2MR%q#ndj`Og\[A)H+D/1rL[urW*CVL:,"`pW/l
eS&m\JigDUc]AfbcF]A^If;H/=&ZKgK3XV,Gt[M*s6)g%f%kVUOcSq0`0"KKkC6iko:^3Tq[.B
!YFgL9-'qG'd5g<2tJXO?LVQ(H>nL,01<RQO3H!>V:Nd]AI<e$UKfG'L>[Kf>!9h(;`HnAfB_
a>KPCVc.ud$9X4:r+IM[;c6s0Z]A:lOU5`#G?fe1[)Fbp3.XJ>4WJ>h$*`4@#iLc62lEi1+cs
LVp/-b[dgG;Q8.USU*E&dn_r]AnK$Y[a^d;N?R#>\j37N@HMa9)Wq=S<NlU1L+FITq(0[i+qX
]AYX:\e17T"E$'fBID.3!kFb&>PRKG)O*tA.Qa.D.L"`(Udt=*s6;LKJK453G%;R+=h+0K-!J
3p!9S#0Teom7JDp"&)5146$cgaYJjtUQ0S@8N:9QJfc9:$6@KSMZU?uH"b.ZnbfjtnJ!="_-
S/";b0o0?"-!`c/=;GeU[*5,:LaLpg+n6G,e>(<mU&oYUZdL/;V>EEr,Du+32FaRr=3NKS?M
9d4cDUV;4A^pLEXC.SoHAr/N)nM0DU$BS5<N..8<"bb!=8KZ>_FJ^Yf41G$>9bH`<(E15>P=
,ic.(]A;i-ab,IPZbE0G-J)BUsQ-b'7gP92B#<=C+J"C^IlNlGnAGZ'B&+$!*'.Ek\!QIM-d=
0BC5sG;KC=SkDBT?;*z8OZBBY!QNJ~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.25"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="442" width="370" height="221"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="chart0"/>
<Widget widgetName="report1"/>
<Widget widgetName="report2"/>
<Widget widgetName="chart2"/>
</MobileWidgetList>
<WidgetZoomAttr compState="1"/>
<AppRelayout appRelayout="true"/>
<Size width="370" height="1096"/>
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
<TemplateIdAttMark TemplateId="8832b657-ddfc-4cfc-b69d-aeeb5b05e933"/>
</TemplateIdAttMark>
</Form>
