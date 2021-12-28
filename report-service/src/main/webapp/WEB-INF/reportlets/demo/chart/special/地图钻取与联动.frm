<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds3" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="province"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 地图1 where 1=1 ${if(len(province) = 0||province=='中国(省级)'," order by 销售额 desc","and pid='"+province+"'")} ]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds4" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 地图1]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
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
<ShowBookmarks showBookmarks="false"/>
<Center class="com.fr.form.ui.container.WFitLayout">
<WidgetName name="body"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="5" left="8" bottom="1" right="8"/>
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
<LCAttr vgap="0" hgap="0" compInterval="5"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="report0" frozen="false"/>
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
<![CDATA[0,1409700,1028700,936000,228600,723900,723900,1790700,723900,723900,914400,1066800,723900,190500,1408320,1104900,892800,838200,838200,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,0,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="5" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="  " + if(len($province) = 0 || $province = "中国(省级)", "全国数据", $province + "数据")]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="2" s="1">
<O>
<![CDATA[地名]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="1" r="2" s="2">
<O>
<![CDATA[销售额]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="2" r="2" s="2">
<O>
<![CDATA[利润额]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="3" r="2" s="2">
<O>
<![CDATA[运营费用]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="4" r="2" s="3">
<O>
<![CDATA[税费]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="3" s="4">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="省份"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[省份]]></CNAME>
<Compare op="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=if(len($province) = 0 || $province = "中国(省级)", nofilter, $province)]]></Attributes>
</O>
</Compare>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len($province) = 0 || $province = "中国(省级)"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[全国]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="3" s="5">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="销售额"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[省份]]></CNAME>
<Compare op="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=if(len($province) = 0 || $province = "中国(省级)", nofilter, $province)]]></Attributes>
</O>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="2" r="3" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B4 - D4 - E4]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand leftParentDefault="false" left="B4"/>
</C>
<C c="3" r="3" s="5">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="运营费用"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand leftParentDefault="false" left="B4"/>
</C>
<C c="4" r="3" s="6">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="税费"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand leftParentDefault="false" left="B4"/>
</C>
<C c="0" r="4" cs="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" cs="5" rs="8" s="8">
<O t="CC">
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="true" changeType="carousel" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0">
<FRFont name="SimSun" style="0" size="72"/>
</Attr>
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
<Attr lineStyle="1" isRoundBorder="false" roundRadius="5"/>
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
<Attr position="4" visible="true"/>
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" layout="aligned" customSize="true" maxHeight="100.0" isHighlight="true"/>
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
<OColor colvalue="-12276512"/>
<OColor colvalue="-12203043"/>
<OColor colvalue="-10760542"/>
<OColor colvalue="-1059000"/>
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
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-10066330"/>
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
<newLineColor mainGridColor="-1118482" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="64" foreground="-10066330"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="25" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B3:E3]]></Attributes>
</O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B4:E4]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O>
<![CDATA[]]></O>
</Category>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</NormalReportDataDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="92ffdae4-7ae1-4882-9709-05e20603a382"/>
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
<Chart name="图表2" chartClass="com.fr.plugin.chart.vanchart.VanChart">
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
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" layout="aligned" customSize="true" maxHeight="100.0" isHighlight="true"/>
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
<OColor colvalue="-12276512"/>
<OColor colvalue="-12203043"/>
<OColor colvalue="-10760542"/>
<OColor colvalue="-1059000"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="0.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=b3:e3]]></Attributes>
</O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=b4:e4]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O>
<![CDATA[]]></O>
</Category>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
</NormalReportDataDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="d7e33ebe-ca88-412a-add8-c92598834e1e"/>
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
</O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="14" cs="5" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="  " + if(len($province) = 0 || $province = "中国(省级)", "全国下属地区数据", $province + "下属地区数据")]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="15" s="1">
<O>
<![CDATA[地名]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="1" r="15" s="2">
<O>
<![CDATA[销售额]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="2" r="15" s="2">
<O>
<![CDATA[利润额]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="3" r="15" s="2">
<O>
<![CDATA[运营费用]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="4" r="15" s="3">
<O>
<![CDATA[税费]]></O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="16" s="9">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="省份"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[pid]]></CNAME>
<Compare op="0">
<Parameter>
<Attributes name="province"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Compare>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<SelectCount count="5"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(province) = 0 || $province = "中国(省级)"]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[F19 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.RowHeightHighlightAction"/>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(A17) = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[(row() - 16) % 2 != 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Scope val="1"/>
<Background name="ColorBackground" color="-854279"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" multiNumber="5"/>
</C>
<C c="1" r="16" s="10">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="销售额"/>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" left="A17"/>
</C>
<C c="2" r="16" s="10">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B17 - D17 - E17]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" left="B17"/>
</C>
<C c="3" r="16" s="10">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="运营费用"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" left="B17"/>
</C>
<C c="4" r="16" s="11">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="税费"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" left="B17"/>
</C>
<C c="5" r="16" s="12">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=len(INDEXOFARRAY(A19, 1))]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="17" s="9">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="pid"/>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(pid) != 0]]></Formula>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<SortFormula>
<![CDATA[B18]]></SortFormula>
<SelectCount count="6"/>
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
<![CDATA[len(province) != 0 && $province != "中国(省级)"]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.RowHeightHighlightAction"/>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[(row() - 17) % 2 != 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Scope val="1"/>
<Background name="ColorBackground" color="-854279"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" order="2">
<SortFormula>
<![CDATA[B16]]></SortFormula>
</Expand>
</C>
<C c="1" r="17" s="10">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="销售额"/>
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
<C c="2" r="17" s="10">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B18 - D18 - E18]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="17" s="10">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="运营费用"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="17" s="11">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="税费"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="17" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="18" s="9">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="省份"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[省份]]></CNAME>
<Compare op="0">
<Parameter>
<Attributes name="province"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(pid) != 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<SelectCount count="5"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(province) = 0 || $province = "中国(省级)"]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="1">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[F17 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.RowHeightHighlightAction"/>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len($$$) = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[(row() - 16) % 2 != 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Scope val="1"/>
<Background name="ColorBackground" color="-854279"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" multiNumber="5"/>
</C>
<C c="1" r="18" s="10">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="销售额"/>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="18" s="10">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B19 - D19 - E19]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="18" s="10">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="运营费用"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="18" s="11">
<O t="DSColumn">
<Attributes dsName="ds4" columnName="税费"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="0">
<O>
<![CDATA[0]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[--]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="18" s="14">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=len(INDEXOFARRAY(A17, 1))]]></Attributes>
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
<Style imageLayout="1">
<FRFont name="Microsoft YaHei" style="0" size="96" foreground="-16749643"/>
<Background name="ColorBackground" color="-2953990"/>
<Border>
<Top style="1" color="-1446671"/>
<Bottom style="1" color="-1446671"/>
<Left style="1" color="-1446671"/>
<Right style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-14145496"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
<Bottom style="1" color="-1446671"/>
<Left style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-14145496"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
<Bottom style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-14145496"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
<Bottom style="1" color="-1446671"/>
<Right style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="64" foreground="-11579569"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
<Left style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="64" foreground="-11579569"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="64" foreground="-11579569"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
<Right style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72"/>
<Background name="NullBackground"/>
<Border>
<Left style="1" color="-1446671"/>
<Right style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-1446671"/>
<Left style="1" color="-1446671"/>
<Right style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="64" foreground="-11579569"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
<Bottom style="1" color="-1446671"/>
<Left style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0]]></Format>
<FRFont name="微软雅黑" style="0" size="64" foreground="-11579569"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
<Bottom style="1" color="-1446671"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0]]></Format>
<FRFont name="微软雅黑" style="0" size="64" foreground="-11579569"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-1446671"/>
<Bottom style="1" color="-1446671"/>
<Right style="1" color="-1446671"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-3342388"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-13159"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-103"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[jLb_"e(OFIlu=4=C)@NcV'mgF+K,tt64>7j8s(G@!L49`;*lYX+QB>M%8[TK6olK!kUd,C$I
T6S7mNj`&4-[p\A("EcMsF7PrR*6ms"6p4Pqs?N69EuCVT1@mok'Ns']AYY+M]AMD]A4o_@J[8u
aR;LX\_JIr5q%#.in2L"W9['A.C&2I<_(>^p9imo2mNu"A0<Z"UD(CHJ;sI-WEi>eb]A6/%ih
hR&&*oXC'?-Z<phBgI`_*k-3?BC]Ac&0Z&*Db?@-6"b*(G79oRI1`rMCP0-KccQ"\`2<%$<V%
^ur=:UJCb&/ZD]Aa&[5@_IA._R\Rq=+#uctEXn+57ir>"laR@GQ?Xe[K-QU.m%)=s*GD[?IH(
E*fXA3p\3&qdWQVPJJE@bCF,PrplXXc6p&!9FaSHnGi+ooeHd@7!3^W4h`pb!+Yp]A,o9X\aA
W<@keFO=3U[<#Ia[0.FQf.@`>!%;#PtcJY+iZ?r939-g6Sl]A!?:$/eX(C7fJD6U;8\>gWCJ#
dnc!=Fs(_GJT",NA^K#M[YdE(lf!kb)L\8(R7I<[+InfC(D9j:nnRI"i^E6T-,Tk(35RH_Qa
(H4DY('ZuT5>*p_'7j?/Z9ti%@n66!,l^lDB</#mGn\-M=Z\-KOq<XmCC#j1.+7K_EQkJ.KZ
nd^!Ys7Shr@;q&G#Ma]AaMZc"*?H"e&+'0+uTU;U3>DZO.\FR3`Jo-7<4(Dn`(9gB*QlZJ.%I
Z>Fee1u*1/a3r)9#POY.Y@OfD$:3EN$CGa8?]A`1"/C(meidBm(lG!5$4.Wu^L[/0_SiqM!+M
Vqtm1fSi061F>Z4kE7=Ge?qNb/Qn`QCa0gl0]AQ,A0YCb<-Vmdt[<c%!Bc0c2F8!-$1p"Hqdg
-k3!Xu<o`Ce9k-CLg/LF';#]Af/',`Z[b`tl:YNC2^@$seEdU>-(aXFJY>@9S_JX(B1bUGa"c
/$7_DfU$j@-,\sL#q<Efp@/0qjdJ:YprV"jBSQ<iE/so;i:p6oO5;N:ZJ$1B7e?F4b&"Le@E
(rIeE%@&R`Y@Y)ppW3%utSA0SqUs,c,nXEC:;Z\<&^.3cP9AP^4*#4egL9dYVOL+l\gX*"!X
g!'3=I7A^fn*MkcF>PiO^AUaS"U1.DoG\k0@hO=JS=oGL^f'(KE[648S)#pU^BJ4#@$`Mc4%
sB[s-c,+Q@B/oqs'S-PCX*r<G%08bYgkqC+PnXY3#4E^lo#s.g%T_p?(,G*ACICMs%8pcFsP
W7Y:q0""YPHJN&>!NiiU\hT$p6k!.J/_Dk1G)2LP#5=)-*b/Mll!!Abifk,N@WVpPL<DPVqM
\WJU4$Pbbl+#&q.F&`[Rtcff)Q$od^-?r_pRKnc)-Nr/\N8'@!L=g=[LKQ'PD*tAfqO$sQ4F
*6Lh!V,L0H@(^\N360a=,%^0joUmYN]A'HPAK(52e=l-[p`iMRknNOQ_PGIL;H`P1/iiZ_UZ$
B:d(&(rrE:(7uJU`pVr5.=`Nr3`67Yhj&\4(Jba+V!qqd6-k%*G%$s$ILZS_L09#.jm`;s]Am
/JiHp?*i7QfkX`P`fuWd=>/X2'4h$E*9tLY0PSZB/mn1A8mA2R,")g@S/3=Q"Cn?X=P]Ap47k
[N+1^@<\?J6Yc3J]AB+1IuTou"t\XM^e0<RPm"+=Dh#9>6n?-I4%qJX9"Wf;`t^1,P+)[hE`4
/XINpJ\(PSO'(8*1g_hqW'CY7hCk/KG'\',acPms1IcI7ftW,h@Uan<*m`<V@rI+S$J0R7)W
3U+Zq3@]AoZ[UHmiUecM'n_'"(lmMT8@m+/WfCri[$L[HH]A`p/t1qCpe;>c$"E&<7,M]ArF%pH
"e([@&h)-'WW+utW,q,uJ'UG8"VGFlYV2U-JL@Q-7jB3lEacJT`uC!17N0dP<F[68T1Ca'YN
e2R``emsI(-#pB=unpDoJgDdrlC9?Z4O%62GVRs!\WG8)S59i\fk$Q:[n?4i9tEOrj&gmcPk
qb&o<2j"^1-`%T-b$1c4_;LSis<Ye]A&*!qfpOUBN^+ruL.5\Z,X./`)m$W_Kt7]A'bq,@s@NX
JskYq8NbW]A1;!3f]AB!N\81Y2OKfXWZak(lmqFYJ!lbM37eVAmGA(9X7kra+&e*Uco(MLaR)t
B#PA@WM^J?'boHdd2PA3IuKJn<alZ@bZ0/U3$7\.Vpoj&6ih'u!."j]Aok;fBFEq']AUn%V^A'
Q(V?#nGPHB>,T45F<\(!F\r-[@\)&$mU$f=Z:(Dm*[90`JI_>C#+>@\;NmlnIhHXj$=V.6C3
\Zad;$a(I9IYd#Xep[S)e*V"K;Zc'L-:<I;21!J6)>dip*8n6J&BiQW?\q!m=1C#=!KXa"^h
Yi!/iAKSh)CfsFS,kQ16OHIeu=aEJ4VN.S^tHB33g1gu?M^.S&5#=Y4G/.eiVPDDl%Qc\a$G
c0kMBsK1'a!%[bSU[(2W,V6$:7'#Z0(B<JV^M`ShJ6gB8PZ.G1`knr/Bt9S=\a82N"e;$ETY
4c33>gX2s+%T<'3#QJ?"^t=:u%&Pg8M:^6JH'5[A"0L$#PO&duV%J)%gH]A7i\:D\[Uc/83k^
+Loie&4Q%_&`#<N2ml2rg"GtE#=DKVEoPiq/G7/fU0?CsTp7'%`[M1Ec"0c6JQ\9Q[b;MY"D
^$l8"B*_bj/j<R(E69pN/fQ.N0Webj0o2#aubXTYbZSln.^a\u)'gOY:U,`hghqPi*oV@CBI
%*^NcF<;-3QTkK>AAhC.(:eBncV_eCqU]Ak-+Hc7-)%8\FCIZL)JD-k*[_&X0Ci;+Qh;*OGMh
`La3SN-phFZX`CE1r!3NZ\MrN<E%!%+2M9,_JkFoRUMf%o3G:Kf$NqB0$986ib:%Af0m\f/L
\&h-T6afKqe^^ea6HgQ'=M97[lGA^^iJiVi^'=Gb=-OY<NM8m3Lu&PDcJ'dJtm*1t"4R;of8
!;"'6JmnTkNL&uC.3K7a&\Rlm<quDGM(V#t2`mZS(/[KYSI73BAduBCSB_jfMNC&6j<&(T^U
XnfppI/H4;A`WHSW*M:b=2,A.S<*,/lCGTi47c`esdlG>PdN.1kCX731?ag.\"7r("T@!!on
`N+>%q.r/d/I^6(Xcedo-EIVuGP+@.dGpt;H:an)^:tpH+a[9\`bmQuADjI&j:Y.ZN'Eq<ra
:dqm%Qg=T$Wo3)OOq/TelXTcrj=\XA/"?>U-rYgr:puW$=Ws?ed!(<C7UnF/a-MYPDu8dDgH
TQ(&ic+@oQjp-\IP+_K/(P1]Ao?T\u!QZP9D#Z17Z"/np?LOfDEF&BC9)M`o)#1;/Pk$)fCF8
C@1>F!dFDohrVanHT['J/i;?C0X]AOW5iNn/jr'<V\S\R)EgTP\gDl9aNIso"Klqqt7^;LNpO
cm0#<$\![u-0Ym2M7moT+!VPW%shP89P;?`T.bTtW92Gc<W6GR;NC_.r!8H7#B7+-nims42!
Kl8=#2<lm`ibaKd\"?eBHO[Q<5*CRl--fu;0q>uOq1S*mVjYn6U`uUmHlXm?bAR"/*:Jd&Dg
m!4G2]AB:Bec[NY3>&X6TAc)<cW\m.Ao-_PHHHuH'_RKdHc(LArTUdSaHW!eor%mm)H]Afi=E%
t1Yqdu=fB2p5WJOhjj)U0sfPI/R0']A_9?XtQ?))KLd&?istk&a*7q"YAtp6[#a<+8FiYq,f4
.fh<>U.UEsEd.6l@nVibosm<pecqIJ?(505#fRMi7Y;FuMO#cEG34^R#kpAR8u).mjqpTS=a
mkE9]AVf-p[ASADhLo<6aE8`Rq.ZqFYJ(`2NcOp<>JNXc7+rF<5)[pRk,g<Bju_)i8uNoQ_nf
2CE<KdLei@[NEe<3h>.ZIB.#'tlcL!2I]A##@HVRl<<WIKd$gKg_RQBp26D/EI$d2sJk]A0^%'
+]AJ+$FDrf+r3Jr7*@[G3@)(l1i=<%o_C2>+Hu#GZfR&*WA5>sim@3.T<XpB>McBAAZrp"Wa7
8[oN6g!:7?)fhQSr%%<&bLhj>#oKT(UG_M!M\Y%;(WP;d.=-&$J1"n^%)%/esRf$2]A#U2&QP
X.?J`J[_4IbQ:4X3KHi=NZM'2)uC1H21\.qqajcjKmIue^INPBh.n;Z+'bekVe73c7Qd>W!(
)U,.V(.mij(bH&:&4%DCYWsEH[A:<@ZUPY$b9:8C@V%:j_nMFZY>l\Nj^t]AP\"G4_Bp.F^)X
On698a=SVA`BK_u(Q:0>Y//j,GQl&S%IN_f)7@NSeA)-phVoNVd,Tpe=K1d;-G"Gf=]A6+6;C
YO8iIE=!'9ar>PS39^uZ(uQ[O"t]A<G0k(SaD"s).UV:<"/Y;`Cb1-qGJ:G7S=9"KN:aRPH#o
i\cUP4>Xl+`oaR^5L`<1NlAm5\f'Vg_(Sqm+'*G?DC3aA1SnogFY[F"4;RdK77*FE`CHb6e8
%l&NInr!^d,pb@/I,Ul`Io^`\]A)\^POKbPF;(gD>*Y^Vq,e&fUdh)k,m",fch>0YV7">%+#h
fk4<j#VoB!AUIIE1HL8B0fc;:YU3?@;YK$/5h7-HkmOOKhI(s-O6Kf*jT\KtY[>-a%!o]AVID
OTN.\a51g&3)d>]A'SB16S7H:qWR8c-(:tVY:8@U99h^&G(EcNDNW,=V6<'O34&iC$hN1\Y@i
fOAD"T^9N1T&2]A[-uJrHdHufRh5NX"*nAmAR\!42C6_JgU=i;?7`e2>qtW'+YKk7'2m2YBt`
/)H.?^6dC:XK5!!b^q8SWj0<^Tg@"i/NO+rSU+;Wu6]A,"3;RKnVJmEBUF,.>?7mhh+a;;XiK
\9RkH7-n/e9_DN/7kSHW4RuW95m2r2YK]AP,18QnTA>P*>C3?X)P0,pL0Qki"\Z3Dk3r`]Ac-L
nPVC?cHYK(9\ddJIp_'2VaOpupRP]A"Ct?!N6P4&(Ln6D:unK-RO6Y4=MGTqVcS1?;eAI]Ac@W
rn+9'XAHoEDqtO%sI0_K2^poFu20Q_YdV:,<?2r]AL4MQrKZC!9.ecf-Y(QO^mCS+)@9Y\/.l
k/acf%!oPN's,1Rdm>Sm@!Nk#sctZe2o\_#L6j@CK&"]A1@P2blC4):XE^uXHaln*hr+p7r;/
Co.F(189=7>T63KUek8,>p;cX\*-Lo+ED^>A\e-bM_!:/g5+c2QZLYr"E"7'W<F]A8<')ZH/L
[=8%"F&/q+T7;iP*HQo4]AI9S*+HB7s02J'EHTBFIH0%ZkBW:ruJ'omUjUjp0j_'\fpZ0f%mX
r7icU;'@5lIctF]Aej3W":8OBk;3%`&.(Rrn'+o3Z8e\.IZX9&o)p"o!+ftJ%KWZUu5\'6W"V
ZrfcA7[81$J%TE[+b'u3u+q%G%*594_Gk]AG0Q;aioVPrV/8sZ%!Qt8ZGm,AZ30Cgnk8E[RD<
`cDp&\2ItCu9.%%TLmiIbm!+ajl;YcamR.Paa[`[>QZ@2(++3?/=quM2WQ$'R#O:lc_C0_l&
h7Y]Al+D%-7"g\CLrik`@\,B9S?opRoZ^3M%B%Iap#)8"(W_"h/ar</E@p48)CH-lme'd]A)__
GLGe"N6H=lTbPeK1KW=0LFp;T"\D0i@[^%'\>=N4fc8D9'bZ3Z3GpIBHe>@08>cVlZ?p?2MW
Eu51+B*,,D#[EHR3BqTYgT"@;#LkgEeKK$A$$iX<mS96?)VU]A<c?EVN1>)(E>BH6<LIP16dQ
+(1'g>gLEhj7#Cg/f(?S?/6fO;rSIM0Sksu\p_9p1K^QMSj0ef`c=@6Cd<h'T&u+A4?5S^Xb
'3$++5CtsrXju_SUE*YG&A.E9kZ<MDJj?PR9STk@6Oc,-3"KKQm]A;]A='!MUU&r@WT2%GKhAq
&(j[?btl'QeF@Tc[:gPEh,=CK%%`1`rt&DtUT42at+F:!@B#Yi$TjX;VB/rk`Y)=tg'NmK)u
>"B$F:8rZq$9+-='`BV]AONIN9@h)`HEoE]A-"m=HT#,%CRP?VVGPEH*7=CoZtVWuuiaj5kD5\
f+*HYb74D72OtY*2.N,3HbN!<f77Y6$GdXi]AM"l<T%ABJ%J%4(5/#Z6epjR"d$/i,mE2P=+D
*5,au9em:h=$UZeRf/)Pb=2Oa@eed]A&3IF#3b$*@:@$T@u;G&tBZidru*lV'e0g0Wt9>pZm7
,70?1I<U3JLUiOO03%bMu)Lq(L>Zo=J)7OW3Y$gWsr+*;)\9U/S/]Aj!pSH$+"b!:GT@fKajX
El+ib%d.KH6!PG)]A50pFHum`/aPo2.`Q"M^*[a'7ptR&e\?D]A>(^+>tIR^_%"FSK#UP)gSY2
d^*.-'I,D_En4coVj$`rmXkN%hCdagNJ<C8iW+9I`9ut-8Zuh5Z-eW#@3ZFnh5e[#YUU\ABB
Zo&p]ABe11r=:de[[G!4K^Bl,2(Mdp/605@Q;?ZQS[7`1F6$[<!U15#[b,0=W\pRAKqoJN?-Y
sS,`'ho9r7hhEiG#O'VOnhI+HUIZeMorm>`O=10hqO92[+YBjXD9%G,l(]AoTR42%.4@<MR_a
XH[-M.db?Mg/0M+#c7@)]Ab/7NU`HE:_+@sQ`gRkL(%,KbTmXcY<ddsD`7&>kf1r[\SjSZ\T4
,.:0fg2#tM()&`jNMG#Yrqi0)^`c2%YB+3%kH6%a=MU780cd/7_*,n@Z*i)ZnXj@dUQA1:=g
V:ilAe!bh#>u"G4D&k_cfJT@67e1"fHRhRdfLe[U$^Yl-;gO/^X/CX0kUc_SNH'B7BY?AVq5
u[Dh@=DkMj5@l_JiDgi;H5YK:2q,d7d#]AaV43*4Z?p#5+W.:LdVuDFYDgRiS<#I4fr3%Y`?^
"#bg*@`]A[JgkFsiEpZQU#PhunW_.=DAc:'$+>H*c?^bXZ1>0PF"6X7_%R^Y+R47VmQ1;-qY5
5rN'$*9s5X`J'-iHFF;RKc]A5"ME<\M=MHl4A>DDQ\(547haU+:IF!(ULuW;@e;W/Y)Tp4Alk
_%9fF"20?h6KS4jgb%)WNf='46nTg+BiH/]ASaPMsJQ2+#]A^n)TWZRb*qs@3dtD"iS^SN9Rj5
A!sn3>$0j8)2Yc5FOt!_C,l9jm$qO+$Z*V'SuXBsP\^tWK5C:RYS36^3S)kQ(?K[:;q8=Bn2
PIQ)WO[g8kR!c:S;S![*?I>V*dm6mEEbNcjLPm<72MR,MuW3-e<EVC/"E5i.NT?^6]ALf?-^@
_;X,RFE+*q!l_eg442O2M"F(VcCDS'Nre(r.(M\GkdU0^kqUjm_X-9dI"R2O[P\$IEL5YR"s
$8_?gBW\I#SV=iC&mL/8CVdqqI`7PVJE1&W]Ak*N8T!0WHmUZ@kD-2mYnZ^m[t_CJpo4&'9ah
LLU&XaQn<M4cDh(!Z*0qo`9@c9;]A=Q6fPooI&/inI[.`^#EU3UOb]As+8GFEf*OP]A"TH]AmYlc
W`nQac`KK<PrU(Hj.#g+i%J9;]A\M-:ZMaPBn#;=[K/5HbV<)QUhYs+@q1184+,ORl?dJX65M
Q[,((7Roo_hTBZDO/!'u!/D$hR"26)BjJ'n(FQ0M]A\inTu]AW0K1=sArbh1i+Ek)3)irD2*j=
U6D\uM7THY"7!gM@o-GVN(Q+NVhNCXSD8iN/aje^e$Dmq,biM(I>$8tQ!I^Q&jKt=7acCkEe
VU]ARbO=gZoe0&:HT+E>Y%TWi]A1T'gJNe(M7,(kr_pS_>QZ'.+C%VS%cX*Jpjll&R^PNTR2k"
0]AB(HY%+@p/0q!+GcR1)7lqIf\Om4e=7^***c!"H#Y="1Xt*#@U5<U"3_T5C658[e:5]ANZ=)
YZAQ@=qL[]A9bPjCI^>e+Ut+Qu,FupAYfDGIj)Yn,%IM(DOaQf<P?eZSq3hcD[geO%B0-ilA&
-O#IBg>r]Aeiq`.LFZrM2drDr.o(VX'Q@f8gBCZ[Go=B41h46gFW\>XR_(1GsaOjeQ(d2eH:N
F!I_Df5DHA>':4&m:l-5[gDX4C$)6M:If2^`]A`hWu*K0&^#uM]A[?.dJ1H<Ms?=4k9pO+c&n:
]A77O4lsT:ZS,)<%CEMhU_N'@@e5$6M#Wc3Wq-d[Br7UK]AE1[J=t#.fmFC/!XZb:U0<(QkPV(
hYGn5fcqb)))qq9qh<>7Cb0cjJ%o4#!HT([X:<[]AaXbL+u.4s/JpZKOhPF^9?-*]ARD5cp>E+
i9*Wc;>TY)Dt.\2S`$YVC$)0th*_^i+W5Vlfq]ADGWfcWmKg,0h=[+5AQ^F/=MNP.sG#c8Gl1
[YHl_T,B?+=9\omL)A0r:?2>rehXJk_VJVJC+JNPh6Y4_.`!iE$iD1\%;=BdMq`$gr5VAhHD
+cV7`2r1FZef$&0*ke#cWHU_RJr[(dl&o(AmUW%r:fU4YEhfD-ks*!m:a-%;%-0pC3h+N8AV
<6I_4Mm15G!bG+'+bQOOMrWT1t8plWW]Al]ABQ;2CO)gm]AeaQ)niFG)SY!RnfO/W7%6Kid2*os
+XGf=N"&@RnGXLk^EOY8Ar0,$gD$CI&'LTq!J]A:$?TXAmfIBX8?KM+b0ZmE%muPdI/[nFFAd
1JhPm_u[PBE0ceQ%.)(DdE521l#Z.'WdWpD]A[TV^o+Y[RgiUg"]Ar9fU&sS!ik?=(tEJEmQa^
"]A-JmWNVEQUui5C(_c,7k=RjPC>XHZh&E,%r*V6"4S.302u2ZQHWU)\jF4&snNkQ&C`j1D*B
Vb&>.UkIc\o?#?!^1"l+&1FfQ.4gp?"h5PGEXj&lU_J$VfU+(s@0`GNQ>e!`)Yu9(ec\7BnQ
QrtV*>-8Hb.,@ZHL[1CYFGhr^'@i.<&20(/X2uqU<;`*;s2=!-mn+"Wa;o5>uOadVujVS5[`
s\ao!HteamR'CVB.34K^FJGBB%e$1NR0ms<e)1RmEY/\3qPZ51ud8SRJ6aa;$7'E&//(]A1sA
aNPq'766qnP*21<]AU3^qTb-D8@X2OO&oAH]A<3+3a%Wl8ee;PG:A3mi:.'\nPITY+P+(Vk0JQ
R+<QZ;E9JgEM.Gm54:\lU:=3n6(a/_X<7b%--F5fY$eeifDW<qqBO)>E[5b.^I;e`%kqK#Yu
:po4/uqp,VTR%ak1!*'/=YR/3="+S@rPZ9Y^eR+QJC,!?f5$13eBm7.1l>\0oNd`%N('QFVC
6p*DbVDc!=Aa`QAQ\R$>pB7d98[^u+h<'5feAD)Sa7?TI>tkPo>b@aMZhOHh#]A6W+/5W+aNH
aK`uehOo4)tC4R-kN=1bnmKJ'B2QOESh4nc*L]A-[m&LKFgb;pdIGp41lmfKXc7<g((.mlaG2
iIs?^*_CnGg`dcQp3";k@9!Up?,K87dqh=t.)kYs_$J6Qpb*BQi\ChPrV]ANG6VE%_`_$OeEW
320fj>L:=[':1=NB@%0,*rPO4dbQCVPo[lV`j9Yh*mp&WH$6%%a:N+&t(f"@?'tZ)``+'!i\
1b0B<Eo%s0a8&I_:)Qb:>Q"f0UVi1L]Aee^K0;(QQ5/LB'!"+W]AgaMfe29Jg&bPGuCc(WG;8(
n(A/@u78,r&+>[A-]AS<=b$A[Ior2[qJ>ZREjijbgM@G>@^XW[UT/JFH,St,KEH$91cAYDm*]A
4#CB#1<'8/(;@A3`>X,j/o[?!/*\Ad`c/:U=<:O>4Hl1ul.OfB+0!29j/L7Ks>42U'<l;bYQ
Aec`#T63'oK&;4<!]A6_#8u?O-Z8=#?fMs+)DkT$ip]AO>.A>]A=060-0/kbMD#^!O-50aU^n%]A
KK17s#\G5`_G+B:RfOZO3QH8=ZU">l_3TD-MmD'J16Y47sa,fU_!SeoQ'L@Do4AU0,^lFMGX
Yk!&h=W1c#.;4-6[+4j5lHqJ*5o;@06&[6J/B&MfjNPhJ\%f*,6'G"30d9u=*j%EaOQ*7NAI
/9RO$F@Ej<thQ#ZXm(do3_+4+%WTeLW5:X91l(mT;BnjKfq^lm=cfamWb)EeCUGlQbTgBBSY
4boh=KnZ65<>BimU59`4(M#UcgVV]AOa1^Ft2mEo)4OWoQt%ir?N<9chZg)de6-s"S\Aq:N]Ar
+T<0=3(6/UYL?o@Ko1k.J'ZB;fj=NQJGnUj(1T[/G3S:Xgtha+n_q%RD38`D;DpXg;YJc4E6
&s'3.Gq=;dB6+4q-W!r?P&+s4nIC(^rnaf_g#N=3cjUgSb/dZkZ?mj;t)e;<HnC!%g^WP8c)
m@L!1B`Tqt[B"W/N`ku3VnsT<Q2<cBP]AahXp&bj(T_#J8nqN,u[ru8`VBsp.nJW591Te5be<
t\.:W@(C,K@WPI$d[4(:kI's-*)@`N?^cadNRM15)e#r6u;hgH-QK^\M&K^=nfke_de@WWl:
6fQ4k(&1\a<FeAg7YRMn2aU1IR:5b_(/Do_b?%.&e3NYRauIGNDE`U)n.+_11oQk+\/b>QXV
_"W602RP!Hn;\`>X=!G[p,#J<KJRmIXcgqd-3;$)PIp:RbIRc>1tq017(YgGHD/&%2ulcp6!
nM,5Li!jLG<h0E=PFpQ>g4-<0nB5p%nO?jn[63#gI&EhlsSm4!rp_/^XI2>5D0HN,JFNoUH1
#<pBNEN3q5!SgY--8j4#.Wj,E\nL`qH;X=kpQ[\`jVnGNE>[%&*PimT"*]ART-#3HYVs,0Wgq
CY273")n'H-0)2q8m]AmT4YR!D.3mj\X<$h>RRUOZ@"$$=p39<$V2askk=M@^rirW6@BZmLD`
h7%t^dSpm(NiPt.r,6Up@\ld\M931gZ>e74O'6RV1P_F-"_8&<t>=l?8SJ[ZD#?[7N]AO;je+
mfqa#hol*V^!XUj1`o1G1,QpSkniFOg?LKT?tZtU9U`@Fh3tr+BAYemfu[D`9N&DC&t"[eQg
*i>-F0DnALkF4O`fo,[H5H9s,%.j",Ms"k$_OO,6@ip-gc0:*o,0!Wf1,+VlQ]A>#2CYBn0JV
X)J'C:ES6_c>2Us!hbnTBmIljGq^qW$L[[gML.(HYQP<,S*.F(-/4-@?*u\GlMf+\)dCtXjQ
:m"5Y7CV,go_WO;f.c@8D'k/4''$fm,<jh@u)dt-7,;&T'@2*`)4M#Um8b0F/u)->9E:]A5eh
g]APe^jhe`*_JoNM:c0op3^`,St<FBmd!0*O$_F[q"EkZ2p`r*O)G:k_]A^E4Wo)=;BYNhY^kk
6\<V+E5Z^6'7'll[VR`qEb&M%$Y"mbK=p:G<(t6Q@`gTbQe8WKi6aRnp@H8B\\:2qY2`((2q
!=p-T7]AA;hj[[d^mVrCOqakqRojt?dS+D.1fL8'.<OUCbr84+GJ.$2:.U1VDhSD0dj#nV>-t
Me2!tU&.'X:YM.F;9m.M#0KcrU&Xi"(3lQ"t.45kk1,L*)6IH?-Tt"=]Ac_/*4pVe3&$q`.:m
,iQso)'sRFRIleZK]AHpL:7dK<_9Q>ilMYbJT:nkQ3RpWImuf)0ojmua)IXt5V<+'%(3,5-2n
LY!3o5s@^^e?KuLnN>Q+\*oeIU8$MZ>96/j#iqZ:R^mL&#1\ae9e<XP&4G3&(8J::F<2pqhM
W"f(Gs#3sumg3-5CSEtZdQenE%:(T>lTL'9hS59IlB0!A)<qL;Cn<1W92;,un0t$WUJ5SWNA
(9c"hR1>E2KFm1&K6YQg'4!=9"#?eAsBMrN(F%3a)r")QnR.e!oS"L"Rp-K`$S>A_n?^!TOl
s!BCDA..]A/h\%(H@(WeZ3j*M"G5jP/>i1i<71TCm'""!mi`'O`H[4P'k(PjrM+f*3WmijGuo
[gdt9ur9+*[<(bi#:Hg;s*h>YhH53&;O=8G5q2:51I4Q#S5oqj*hWrr->P'&$jkD$tk)"b['
J>]A-thQnT/KM<W'75XFNE1F78lH<SOTa@47\m\HU5]A!asDB8mM+F4Ep]ABK7=%/h\7\aOD-?Z
>]A#V$U7K3'<!!/rQCp)MB!Q;a:XZmV5M7PVV$GE7%it+&"VI__Qb4<Nh79t:9fq/"rQHqE+[
B*E`nRf$!gm!6U;^fE72n0VVkl4b?I*6g+B0n8m98i9iDh&0B$cq>oZ^<q;lkA?Ok^"=:aF>
NS2]A#k&GNEc(e.d[Y1fTL%gUZ_a%JV'<FpU4QUh,*pNDcIl>uKM76sT`55DdsPN;_^YrG687
)Je(N(M1**NoB_Faq=$:PCJT(=RQHKi?JLe>(GI2/p4gKK==]A5;6qo>RakYrCk5K>8Ia,\bg
[<h<G79SJD;?<Z_e\UiCV-mC\5&9[/\*CLMg5!d>823gfM*f8,B05$cS(`SHk\POi%(eLZXI
m?mpg1tg=q@QH#W5-fB%5.CJBS%YRU]A2?NqhX'To<@CmiI1!_>N4_DYf.8Gt+-6I?&Uu[4J2
IYHco=/@,g@QQ4,ZjnK1QR^=F1E`dd5,8Lg3N/-RWg:lbM.c$PDpAk3lI\5f29SG%O%/8R*=
;E28S_<@&eE%u6Vm.1_5DK1bM!hcGU5E-"o6emYIt7+;^p9%.^som:3CYR+HDPmj*_Zt(*L+
U'SQpJ*%sXtB@TYp+t7ADGAXFqdG__`6q_qtYVtTt'K(7DoWbUB6kPS!Ws-2b,41'Xb&Ff/$
eq/4s4)GTM&/jNEMV+Io0=YO`S%^cV%R-/.M_nNlp$;+)S,T55!@kkND(mNQP345l"HM.C/&
Po_B##.'j>\4((T(O3!kFD<3J-qkrrQW^26%C1eTP>[d%Xp3+b95.7N1q^D#]A,[<I2L_`=LQ
M6-[J:Q=isV0$P`-tCVVrr5pVb(f&`1+&+5#c9[,IiD\>`1`(8eq2`=?(OSmJ!m#hJK/Hm2j
*nug+Geij+R,gYB8b$+Z">rC_9^W_55)A>($g1o/cQ!q3:*1boU,UW@iBDL:qcc>+W@'qXX^
kNB1lWui&r:id)96#UbAsP:s95Kl?"Qjhb"a`1%3"=uq/^IFuZWY^ol_,QO(Yr9Ud6_SK&.o
6!5D:_OT;Fa,/aPRjO='7`c!8t[<V3uVTWfIY8j3T2!9F#>E-#Q/[nK7oQV#3E&p]A@"(6$1*
b1?B3+'/I!5k+1L_bj4R\^,aE71c`H:2;J\qEu(pl\_oYB.\NQpj@_<7iD%PDkZQkE*A"OqY
0I&AJM$]A#&B?,n'm"o?T:OUr]A<lfI[(U5.o)Q7Ziusm9]AuPN-is\,f>e[GVMN_?;@[ECbq3C
JV`:XKd%A)Zoj;$(\-mL'(FJ";cVr40Y;&VaXpn<pS#a\SO'A^?+[<J30=YtX4h_JiY3(\Z:
q?2Zi$$RdNm>VLCb30DG)0`_KZQZdC.jOgn*'7="%t=K1j3!k^SNuF'X*A^</6"/R4fs1]A"K
tSrO>mHSdJpRn$EX>k^[2F2op%j@.jYhInr+OE5mkR%eIL5/@gl%1L<b_mmBu!1A)K/1+RDr
gsoc/re?sD`b!kRZ6Lugb.Qq*D[\nY[&Fd@:$O#,?/4^MP+\6-l-G._+<o[]A]A=WOP27KUBgd
Z`?`s`hs`7:<g3PJJL>Ntt$cF<l;g3(nY7u"LR_fkVjjcNgCJ91Jt!R&1bT8E^A_G.,@O8DG
>.%FLU*2=ip94;-OTdP>_jIi9UE8M(U13,Q)*n0;kXibiJ$jZQZW]A%XFo)h%iUJlC_f-q8L6
4,%U<<.7D!:^'*qkj=8EQ^+-eM+Uh!B7W'#K63p6&]A<qI#B6CI2_3RH)sB`dchiU7lL<A9\e
-ocF5FQ+op)r6jAjS=P32RCYAq3LO9r@ZZ\3e)sE)`%Q4/$IZ4+s(SFml\&+\KhB!n@kj[DJ
/72ppP9f9Co/W:B6V)$/3[n2=b$n,0iWe!:At]A%O@l=g7S?",?gEJX@o,lu4R/-3><.P.N*d
..1qG"N4\tcE;eFFi3fAgL^FGihmDLjk\Cl5b>%^,pB<8[e1pG*t10V`3k:t+d]A'nCg'clih
7K"MaE)/m89*Vum[3*@k*8SYqkO,.We4p>IF:H#6(W2Y4-cpWYu^`hTg+Mc5L``efh-]AjK@<
S"OFbe_@'^6VG6XZcSJPFiBU7;H_eg#K5(+,f01.WZ=64RcuB&D"sOSRK#j*E2G:a.O<Vq;q
s!F4u7c%i=C>-3;>W@ucE"b).sJZV?Ft-[J/'T&Uh8TQ#]AL;p+X_f>&WdUfk>f6prP0UPe#H
JT`VBRL0k2SeiSJc,"q0gBIV@Ts+KSTl2Iqk,927+6:kNn&qSMR=kJ8YC2qN7-:KNj%U\Tc!
s^Mb&t.,s4T"JbjUNi<L`]AtQ#"@\K<*I:IhE!4X92dC]AK\miC^:2WjjLVNn_\"FL/#+<Q>6B
g`KA=/]A=^#QMi2XPBlZ!L<,AeWq?,Z-cBHGY:e1E*e^/p172hlZAO55)6,q12(%hH556^4h\
7li9[,b'X1N["I48=N.PsF9cqFYoA%?6f)m%KE1_)>2kh"r,B+B]AE-YuD$+3)A"1TdXR9TE3
,I+.0Nu4UN3Vm?_8L^IC>@h$F]A!S`[fuZA-tIG'7%MoPO3_k=Z-1@2>r1.^N9qMQPWf9Nfu.
CrR61Xd,n5!srWDUH0DWV*:,F/T]A8X;W,MW#IQ`^(s@%EHW:16!lF`m?a]Ak8E)[=cC:GYJcF
*PLGl\X/%MH#+$J'PcSq.FJ+ufW&)AfgGb6*MdTom_W3t?g%mSKr(%C9Yq]A4:=Ai=r#;Au8H
92gLTSen`^+:78O)GldF7"&P8rc!9c2?7&$gH@c`T*rmO#Ps]A->,UTisA$"=ab%Dm7k4mq_3
@?PA/6^Lr'r54&j!75Mm)!]At>Yei<5[JfR=Vo%Cg5O@eC8lhsD\lK\Y#ldFr#:r;H%Wr81M3
F)!26n"h`mDKL:c[*5>F]Ad&LuJ/bI+)N-^!_T<E?:QkIg!<i`a8o:9;#$I%Q0Ol"f(LA^+)e
#A7\h="WW:!_S#7X)ar;hs^[=B3:lFQaqSLA<$;,$Qo(9AX)3_?I?+c2.UM=*=sfVZ-HSO1\
=lDftIdF5#7"'MpQ6f[/9sn^#5P`AmarKMiQHZP6?X`&uC+@a0K7?A>@R)8U\T+(-bKVUdLK
;HA@C:X[*mAs0fe"S2tD>>=WDNM'4$8KFnI!ks]ATm&%cY@q^+r*4E.uQIPMHZ9``59>KLcC+
GN\X!MWasJ*np@F"W!NC.*2"Jt#XL>tWnIkk^/3enafRl1qfk4!*P&P[s"BHTO:`3%0.q";.
UWl<:LoCB#hgTq!1@@LWR7CCuq+e6`&<X;sS33A94:`+19IH2W^iQ>0!#l`;t2TV!I<URM:l
*-pZgJSQd<E)/Pt$DSR+'VpsB=QgamSN[N/+tmI^Ec)`Dbcqp47'UZ0)qmPKl.i=2Ip6koP1
Dreoa"i8]AGMZ.;a"od*FU/-FYGBLOCZa%CW2t:UH'tIXcidqr\G$`qf_f$0R_J'(!gg]AP=8K
4[%(6iS.9XY5jTKCW@o)`SV$aFniaCI-[-.sh=$E'EMI-=pJ_+Y4W(m1;/pTF/4Oi]A8G0$QN
/I^o9^NLH=+g-+=Di2CH/^B##:Osm8rZBXSgXrtMI`=%msnC@0P3Y=/-P:A6HuR#"$":e9c;
0U30KrPDO@/HB()0giBS'[=^]AH25o$ji^Gi&*%l\?URtBI[R#R(5e=(tOcPP?L?(Dr`3>FWC
J01b:',\D4=U.7US\MV10#OK^.s>gclX0B23Qf2tTa4bMAJqfik2pK+&a:UnImigh+ChPVXj
1P/P+5iqdGD2kCE;k`LM:TSJC+i'f3M"`kH[>X#DB40m6Be>OS%abS@>Z&r![eMn71m+5L$0
QE;&^[1,\PuYXB)TD"u>mePVFIlE0B0hPXC(N8DmZruFk<'>XN9kf*F>Q(e3b)Y<ngF'Wdnj
94,C6EWrT?d"7q>&oPMl1$=mZi3r1flkFq)q^&lDH4nji81%HRgC0e^Lp9=XGY95:HT'$9Q3
**EU*c^dsn.kaF+q>-P.F*JpoRa^C3BsQ.*F)o=8bd[-93DEPJIRDc^g('9XJT+O,8s"N81H
qJ+_!m=kOU^PqPYIq-LEY!IH'n*;'7gfG<Oqq&C0nb#MRhg:N_8:#Nm!TQ)@TJ.ORh]AqFf-A
pa%%'J*DHdLHk/YR45b(Hd8i[Tom5(UpCB(sE6%FfAj_LV!c>AICD:<jf:X-ZlZmUmlm?RKn
)Zg_$?ih;nW&lXZF!NbH"eF1RZDC;+?'u15=91[`[qM0\)QiN9kCG)T^'M#E8^[<cVCdit!1
,slJQ[!CUddI52h)V\A'LQmjARCkoI!LiQ$#^VJ\k%/HN=Unt.RViE4go=m+a7V_hm1R2*Un
[h5<tFgkG?DQEW-^Ts!;c"-NApcs-]AGW$[nIj1D2Tjr8\.YXWBuIiVe8F!>3!F"k_54VP!q:
rJ6<LK>1k>8P^I!ore3-NVm/Qa.8lpAG)JDRBCS5>P^8ff-/Eg:T3P\`H'N<TPK_f,ZVXO]AY
S/-%<1d;@%fLlHCF`Z%n-SOi[r3s^O>-K*5H2JL["oC2"Q?$')[>o7niR,NF-]Afda+$YdC7q
O!Ppd]A^_Lmggqu!`SLY:Uml=;spF)=sXU^:;2i)DlGGb(VI0lX;;u\&4?sfB&%maN;;KYNf2
>>A<+5"B91%fJ+i-?O66+Th1n)To)3t2s1H6-ijJ@pnoK,.gS4Wm`KYHZV<QN8$$9(MAr+_t
/O$e!X`dah*.0rtVQL%E93;HDuFALt2Dl9sMB@;salKYY-c&VN6g/VBTZ;XuqObX6GTPeI25
adstO+3[0YB:-k5%.5^ZV8jk#2iBB2F=q#sQ.)W=#nPL-(mD)ok-;%F"7j/DXsIgC0:%^3IU
!"iE@BGE;kD(*7k7Y4BqKYg`%>8P!c)'T&-mo%?IDr-js9\1E28AQ_7,:-/(^nY1OkO<X;U=
`<WID4if=I4[9N]AX/5p0YYJV[=O37rh#]AA8:?<TT^'^ii@YZ=[OqQsU9pB&F!_ab;D3=i%>f
d@eL`hZCcpTun=oPuQTN@[K6<ig^]A*n%4./uB:4kG,BZbQkFsLt5S.$q<.1IRI@Agot7[Iu&
qk:UL=ds5CE0l.43H1a`uhX-ORA#(I?SLIp_&qVd[).e/)Da(e7^C\?rX>/>i`lIT%Z*d[co
<Yj^N'Um2XO?9H+BIN^<kf>KF9.s1MC_8L=&bsD%%^^Di"^,e%+capFeZ"u6D"4:2S*8u>DF
qZu.g.=`H+oM>2'4tD\o:rc6su'>^#D&#0J]A>f;a1/533GPR4qncTNeW"J83K<'C%k`'Kgd@
1Sjfi)Np@CEE[A#oSC[H0_=rg&j/*F]A"S=1EHD)mj/@%-9E0!S!NJ$tGQq:9j9QY>d,Gj\ci
=,2er-/f``*&n4-CnH4P`h;N,F?+5618'9%;P^6q80b.R1mt(QSTo<ipg:d=>j$YT;ZYQQ#R
=;NLu%=S;iqW;J0)%]A/;YSp^bWMe7S&rDn^+%1N\&$4u<Hu^&)C%K:^1t7nO@eF3>'V]A4[4[
es1cPVF5Tl@F@/Y_HZU?W:XL6/+XV:F7HEXdGG%]A8BqCU1Zj9;oCAq>m(8\bUJj0:%DTP\'4
V9PE6nmB5PI;%I'r7?\HPa&a"GPn<)G`mlrPPVa@Z?$NFK!G.Y2;cG$JJ#i"(gJ*ToA2h_9$
gTJqq.JC5j0PuWk,%u3,W>n"s%g//kF\#c<hiPD`NXM/6S5eaAkX)HJ#]ARtEH%L"nOZup^3Q
"Ij,h-9ojOjhr%Fn8SuT#jADQp^eERD@?fpLbu:9ZFA1jFN1n-As3?9o-15#)a$$kinKu[Qg
=Bd^3PMkc.l!fNC@t6eeqj4)MHfKgc7tNK2%#/p:8fSm*_q:l_,i*]AG'rpOYGK?#::1&)>\o
li$Y2de5(O&e4cAXK6fdC8QL&Tft'Hk_7]A5IE)EMj'k_:YBb8u95Sj?5`:emk3R9/]A5=H=[0
0s=eb[:g>;<Ym40;FlkZ_\,&COd$lNmsA^saKE0Va72D-)H&4Pm06o]AT;"VS-pDZ;0ti(I]AN
^)3H^1<fqr-pR[lrorWa$Ns<fFB6&cL`m+6FSgqT\,m@qoX\WZ$GN4CrFZf,`bp[7WS%a[n&
cFjoB5)'1p2^p&a=[:+6[2R?pu<8\UW9q3&r$"QL<_W$94#d5C,,(/d5eqTD,]A15B.ipu'eA
^7]ASRks\f[I:)/AS6AT7(3C'\\.Pq/h+<12Of`(PgF&-iXJl/,R#*s1M]A@L)?1s4r%:FB#eG
UAV'r\J<ipb$quTY58Al(6;j:Rb"F>,E[$Pk^VR"V:L^^LNW8!SGS)$nW$j@`$IsPaHA4"qo
?Ts-uO$0D=<Mn_h_W:V[\*:YFR/&Df[88.6#pe0&Y>SZkC=0/+s%n@;llT;n:1C6d^]A3=/(V
&<A+)%B`"M'%G.uWdCEq5^Ko/c\\A2ZM,\nnK'E6QSQR1/6-_p4=1fukj/%DQs)i7'Q-pWqN
O&>@P7Q4+aE@9D?E(H`)cmQ#4ru2\/sRhKh<<"o&E]AkZNdq$R>s&ONg@-&ai]Am7TJ$=9hfL%
s=B=>Uo4M&-"aFDGp[]AnqGcJF2fWfE?\4Qj7DlYr;^LRdYYp8AEt:pBIKJ,`d(3A-ADr/E<=
l6]AOaq=mc[N('GE^rk\g1C9iLl`=W`Ypcj^==^fp*]Alo'p,:1jYG!-\]AH$HMnIEoWHOn]AC!n
I@$dkR'02j7<S`ht3&Y.jom,Nl#l>rcrYBBkOkK:Y+k)Pu7M&NM8E)7R2]A%/%dS__K^n-(>I
TNPD;iGe^B`O#8:^b8hO?[?$S5BRmiRUBBip+YYOpKZUK0gq1L%AL1:!R,OkBj7\o5MmQ31q
2p"&9+ocpDiKq^Js('>K)0)@W"lU(N(^(o">oR@1;eTSed;bFd>FbZa,JuEPuB6V2U%##=XQ
<!irRA:Slcj</?j(MoRSu8IP"Q(c$_X%b5mmXlZ7m3Z28H&n_6?\CkJbC05DVXhH@.<N)&5Z
4PBM!p7)pD0b</*'4I6n6[V+EIF`>]ABe@U#ru.4^@%i9.)U^Ku=C;YA:pFGi0+j6;/6+CTP\
(ND&/ArkcC/_#7B'M4Y89O)=&VeRO(Z[7__)Rd\*OK^%X,9"]Ak-<[0Cnu32P)4f/]A3S!5>Ns
,KR@(%U"LY^Zu<>D)^.["ms:J$ZDa'.5JQkB"S,f!;B9QmOHV]AQht?QEP?id=.a=1ZAJUl_B
\\j&qrColWFqfm`hV[?@]AE3Ag.Z.]AI-RZLduF<@W]AHsg]ANSJ2K3o"#4rQ55JEbkAb_nqfRD7
uk-DuVD[>icdDJ8E[<m,emUQ#(>%g6>d\&+,&2N)n\Aq&PCD[eYm.m$4dae0#J/i6lOr-7+"
Y"\$B[]AWMh8Q02$V6apSB;8>3&nqB_m$*_(qUH>c.YU"bd#KaQ.W]AR/?Po2V;<6Vo?s[S?C'
)58f%FQsnQQ**'g"^D"rl.'E8\ZTb:m0t4_bM[dq2sD'Q4"Y\jJlZE6C.'F4^2iFH>rJ)I(G
n-a`JKI7C*2,[!&P*"\34A:Ke'^2/WK+40UF$gu.T%lK+0\,oMj]AHL:D7R,8PRh=<F1/0)_l
+*o/GZ`5&3>V/Dq,Fo!dAk6qc')p[DjLkof".BA"F)9Fb5EHbD99/aPtUF53=fjO!X"Cm[#J
liHcOZW$u1tO]A&^YjPE,j-m`fulI:M=u[RsTP(3<\M<]A;N@mFWFIQbah?S\YZ-Yqk@AZ44MD
)5jPNHPb$T:LM0DN>p"B@CWb_+)2W^s5jYI88%C_=l.C9]A(bMG:B:?Q8K=ECHWLj'fq#Vu`.
u=loq>YV^,AqR/4l%)"+4G#>YiJm)tID^"eGGKqIVgKhOk=d/`\&uXSWtY>>7IGesc4aI1G"
2Elb*^V)5E%G8`kd5g@c1\!)70M&dcdY"N/D(6';%acMC;R(htVW(315c[NfNG6Ab))7H.4*
8Z#7,F@CV:`8HTAhjRWQDksoU<jHoc,SrhVl>83Tgu%?W="(*,uZDlK]A9V$^8=qfSnh;"?hI
,A@bJqVh*]Ab;8G_B<<EG9Q6Y2Zk*2[.%UCFasRTQN73Bk*c91\CW/'C%K!h-L@s,k=*pMYGW
V2Oc\970r$.HeaMlYQXQ>N;-s4R`W.;%BsCN5`QB2]AE?tC=+0a%L)hp[ZP8KUkHKTE'C+7<S
kCN"]A.n:#ja\7H-O!-2OAX13+'@,pkRg'1rrpb\,s4"2qs]Am7dg<AJ\qd>7E&<qUDbM7EGc&
"e5M.b?I#scrs_a7F2$MoZ#LR,?#\8UN]A+P?c7HZ$8BDSqVREV1b'-Q=)uH7HkXXp^5bTpCr
bPUj)d1KgCN@K\kOktJ@b-E9Zf>4\acr=N_`e`FOpRu3s8.S3Yl:N!\M_gF>,hWSW;(5FQ&U
p?9*"QXZJ9KlD:?A#0-O-mabeUCLS8(rbptpb"L6t&[_[egjQC+>(_CP&TX,o37=CbII3ksn
!EAW.BpMMu^3-A]AFCiTc8G=^$@'$2g>o.if+Uj^G.Fe[8X*MJuK.Dn$?>!jDjmp4#?mD]AZTt
^6Pkq^_5'W[ASW831qL%.'_:-\HcQ"tVKY0SnIC2A4T+7CZShUDF(h@/_eJb$au4qmg[/?^;
VhG@C[Z+=:i4=2U2e;ID)@>kU8#p).`rQR./?AkJXL\!XbeobNtjDItd*iX#V&UfbhX!o&3-
*%Ld]A9MD]A3aQdHpD$Cu%?)1"'`3q9ZMF5590WLLXq<&[Ep0+UL#N:q"Lj^'5Abrj3IbX%[3L
D73\m:+)L$[?7EoNt:m@C$9O0[FD]A,T:?!<g+]AU8K$YR<ObRmDF/HqD$^;3bHFlWKhgj<l)H
U2s,-2dMjJ(cAPE[TM<Tla[5i<I(5oPNc<j$ZG_:aSk?miB?:cA#V4$U1LrbZV$fln8'Fu\.
WEu-unD"$@Q()Mog%D"5fK'lfSsD)H!BOS:u>6bIXlo:TD+W4FYNa3BQm7IZWQ=b-XM`Y5Tj
9@\JP`&XReHbU>Im(ke3@0_cm*hB2&j--B/G)FTkXccGW[2`7cNo^Pg70(p!rHuj10mFCh3g
UX^7_#<g".*Kb6\p2W>(\6'9TUDR#TDIB>Ks=AT@DCWA=Bd_3\n)6bhAWU11d=,)HVTulp$N
oM2_d">7t*Bl=q=^Eh5-CE?p(Q<`]A()aE@.Ms:$kp(>/-QYMkEf0+#Mef3IZVh(<A\=/>Rbl
!jKj1Ea'if)/P1]ATB,f83OunQ(q]AHX5"Y*,h_H@ITr%+@&ef2e&UD`@,rdsWlBFGjSEXjCMV
FZ+d$WVK#[(CpESdaD<e=0&j$V3A-GliAdQJdoFY(i]A_EH2Nc:CDklEX+jNtmlY\k=!e;TCD
Z$P/b/!.3Eg]A-WM7+J?]At+LatFpgam[>i!???.PQ8a>Z4YAi<OgKF`XWVF!,a1'c0@i(5e_1
dAgXZ##8^6G.:#%Zk60!o(/=m]A+2D$lX"#7&/#8q2>PQLkAHh7nIe_g9(S1B]A_]A%gE8XbO<U
PBM2\OR[UH\?jm5g-!XGG9qDrOuRHHB9.CQ''M[M88Ck7@Z^:h,9.9VcoYs1'>[.$3Jjn0<.
[>\/lr*HG)iNN%A;_Y##N*Nts+WnN5@aGR]AWi(W-!&iBFKM5)GHYt;/0\=(_NY7SQG^Q`rbq
T>_R92&N'YNV8qoc:.@M@&1Om<WY[futZWuf!E?Ka7%0W.b)EP+&2]Af0eZXsD3Y%E"16fH?k
Ws"Np2.E5$H,F3]Afqaq;`0?%n&r23(>Ddn1QPUm*-I\$CNmIV/ojU/W!m[1!4:/TnPiNq;70
jU5P6_W1\M;RTk'kG]ArNjf#Ldmf\,PK"s7-lPt@XRl<(C/4AQA5oZWju\WMH)=Bs-CP%.(rZ
HpX-,Xes*@WW>t#@DBPgMGCH5gaYXmCmgdr_+H?*HmS+,!)*T`IU%1X+aqB5W.5Ft/Cife1h
4WZ\$?%D9l[i3C9Mg!Wap1mj0'$FHc&ou*`Mu@(V-/_^S3p^X*OEQo2c'?G6f)e>$O"2,J-l
NEY3X5!uKehDT'<5Rt[MYNp/1+DaGd$M_'G&tb(*e'^;J)/XrX5B>H_j8?+*88Z[-nFqs6RW
pJ8G3H%l=-5qou+8]AGTZL#1:$n*ELTXAKLUC3^2m%nm$U-8?lopcJ]A\^1Za5F*a2j$>N;H$(
aorgaMlfjO84R?%ol)U/>OY.feUSW@('3]A;1:1S?AGmoXUf:hA1JrYACV)r&$<[jorjOe@Z(
sIVhUDa-Jdg&r>q6g6+XYY'qdrAj;/t[fl'ft;EAGpnFZn$RFE]A4iZH=,AMlVc4/.+mV3c71
G'+3i\A!lg"udRsNCfY(K"'la=A9K7IgfD-j14jFT)MEcN3\e8m_uJ,I&O(KYGF/)h4L76*M
Jg=lHc`!-tVb0m><ncAAZP\i@ZZN0XTTe>[%$-gX,lO'q6H9i;,(:=.)0t`""f^@E522UZeE
):'@:2SIbV5#^&EXU]An+_k0&b[]AWT4`.N?P6U)L\PNR^..#&\Pekq8%3TSq.]Ag?q^a9*nQS8
:@)^fm>E-"j+9gQ,Kj@[2W<!.SL;C^8+s(\E_p3%_79h?r)c+3m65bdSp41p*IDEWodF;FWX
[#(B3`8\P,KNLRjQo-YKqPq"Nr/#`m>iRHk02Qq:H.W$L-Nq_MGe+!rmn8)bg2>5(-AiG^B0
Djs%cC@hU^B_2F*19mZ.;aj9$[ii'?8uKmXBBF,MCW=A/O&i]AoZedWGAhJ\oDA<&8$!Y",,k
Pfjs(LlF_j\6dra3\[@V&JN5Z*iu[WFt>AQ&j[VH)#A^nk%q,pE[?!j[O`6>EGQI8R5f&^(r
c"^HliLlal*,Gp8.,YA=R;@)b;A.t,f4o#7uB;QE"f/:<Ln%@7Pr]AMXTF$JjCbNJleaT;7`<
CYcqQFN*K:Rt3Hifo?AK_U1n[hE#!pA`)W5'qWp"?:PQ9^N+58?L[6p*f^2^T[9d/Ie\=]A"4
F+oZ?riY<H!_N=Yr0ofmcWdKAO.`4Hbt=mph3;+@>KC=1Jbl&XDBi:869Y6qFi]A1%MA>P=Ih
!S!9ZGa.4G:iVMsckc.Nh/N5_-i'O-q\NHE4E35bn!rlJ5[)^sL;IN_bg2+\E6QTReV9skRc
Lg8p1?Y?okUJrG(a^KNW8W<iH.eUdlDA=R^>n95/B<`b5FU]A8anoL(1d+&PpF*T0&%EqG`0<
@'M&PM7*5k>,u&XXXg%1Ae:61MriF,bKu>C'k#AK\0DXkSreZeT`m:rt_!NYW^$C`'"T)G#m
mh]AG>c6IPb,HB:#nihR]A.Q5m7r2TUfE_.XS&s=ASNQRDL(E\R1I,8;pu1-r:LF(t_AHi[j>1
XD#U+Nd\8_CZglP1["*O,gUJ7f3kA9fo=m*BaHo$Vm@F<mmZiU)Ek/i]AD%&HpHPrm/O^Z<H(
c*hlRCk$rlOS%Pg-Z@gSA"96-+n+'#a=6>_p=Wg1KP)p2?INrI6fPP=jmYDbHW&PK,!5e*q%
UD+!X\fV[Ugi_*;K"oOSiju.\(2^9AI:.73f(aT)pt[kb9r]A-?;KDhZ-R&(TaV&gdP8b_U!:
5dsoCmP,gjn,>2poT+$"^8Um'+#m<Auq44QciAH(p7.k95FgCSahjc,OhfA@=8<'M9d?#=CI
L^@9Tk>AO\]A8opH"d:8K0TlbnWq=iVb\!C'\IU&Da-J;]AfD6tAF/g"6D2HgHeda6=dNX8\-6
-ef&P\#d+:KggkQ,_qXmcadlZfn2L@X3.c3/0%fJj0ag`uSY)BOFhQXr_'(8Xll,k3Sl(=?Y
okJI_45cc8K/U)&(D5**-ac(J1fi^h.=h]A_CP(/=dfPZo7-Vp46LsqE:8dC!6ntd5A]AMu"29
_)iP/:l<Nd_fP7T0XD!EueOX\fJB,?B,bQup2.TaVcmAl%7?,^f63>Zr/)\4b9kAa4DYIHD)
+Nr7fc8=<+QBP_ehoK`_D2Chji>ReZ1I*2Z,V.M]ARE%K<U.pBD#e?h13$"8V*)#D!`Mn]AN\L
]Ah-aj3t&l,/6oFVdB<O*@/l#!Jj_jd=++o@GpN"`GJ(*84sml6\=QgRch.?F;$VSRcg,ihOr
M1JSRK,f5'J8/q;IF?fCOU^RAF(YZb:-&RD5,BB\d="q#d#nra7s^VAP50@*KeX/,S)`47E^
>H*^CgL9r>=?YK>gd/CJH)5sJY&UZ1*!E>M4I>DZlZa5QJJFHB;oo=DS:J0aYmqPA&+?'oke
K5`<c+6-;C't/s0gL"=4kWQZ>-JuC[k[DpVioEn>2X^mg-W#I%LEdU:spBlfZO8L2N^aqqA^
<2achZ\C:=AJ(9H#9iaTsY?*Chn+Er:ER(18)#A8mQ-/'.YeX>kf`]A$4mDQHNq(Jm$OgK@Gs
6>.u0S:HTb]A$lpO3TSr>RSH%V._tt=dIU2pequq`]AClEDDr#l<;cU?V6SoM>LX8e);cN*>\2
c7B-TXDNofR*bKm_@hYd=COER)XYdT>:?GjX3>[Dl<Ni//eGJgL'8$7YQ_2pXJd#2W6`Cj\G
\)Z)QQkmB^hISijjDK:=6sC@8'Ds<EjbOdln,kG4;>&\o_Zp^SBO%TBB2,T?rY2sderJ!CB9
\O4e"`\N,UFbaD'.W`N"/OB$3j[%<4Y1HNt\E('68@-.s!2R*kA;"hW*L0ClHj9<J?b*MK\q
]A@?S<V#VSgB0%-H]ACP>$6.+aCEihrFa.`H_)R-uj<CPQ?JZWc5(hY3S\Ls9E-9TiDNSlA,Oo
hNA9U$j/k=+>rhl_K^iM=>b4K!T7n_idq!9qUqNFC&.!f6P#V+41`Ohb'a8lgJ?kkUpn$*oT
)!\-r[@/$tgu^)6K:5m?oqf127"WT9_nW%NhWF!:D]A$":f*a+E]Aa#Mi/,M5u)%2j[lon^EJL
B!T+)><IAgauiT5C[n_jP-%*nBg@ea9;^[3nMfhcU1B#n@hn^b1lP&XAes"$c%=A6.Dmra?^
F*uV_6'eKX/)nEJe=b7Y%C78kgKCLKC:s<bnEadDVL?G($06CN84]A#2lp[)9*-@)EiR&"3ba
.WP3_s:Chk6V;$G[EaWKkFgJSe.s+1j>o#HLcMNdZl10N``K0u+qiL:WXM,08c/;>S%#cWi?
-=5XJe,U>Db*Y2;j1\[r)B_o*l*4kJ]Aa&NkWoH$XRDgB.._?.CXi8=lI-WY&<+WTT=XN*[=\
@==Ee]Ag>&2h>GgFba85teo`b6VHD5XG]A?5Ef"_df1UZB4gg[%aJo:=iu\AS=aq]A,ce_'3&?s
$3'ZJS\#WeEQbrD)h"^02kmYY6n76hD5olCBUg2XC(_K*!.0`X.BXZ_,>c!9\keH!U(0>W\]A
r6N21B%qb>puG?-S-YqD-IcbRBHL(:1oEL"]A@/Q>ZT/ROU`).fishJJ3l.enhO/EjCsP"(>l
L+3J4E^-6[Hp/WN9m"/&(b.p<VPsb?$jY1]AE.WX=mC??BE#W$;_%q-?SB?^kZrY(d+mF93pX
gYS-3A9dL;<iS,isBfjG67tL;()A2Z4Pg6jICN@c0OgHeCXM\bAsl0+Ek]AO$jW>;U&&:)jfY
jSm$);B!%8B((@OKf'MrBMR\-k14]A71C'[m)EF70pb0,3$uNnd9H>'*Lc.=p2a'od[_'@Ic,
26s<rZ9')V<gMIAF4CZFCQ%1IQ^(p$.DOOLB_`[>Qr)WrJ3PB\keaouKjI*K-UDn'G@_^rP%
A,0>A28b7Pb"eWN9]AhUu:UT$L%uL!"^chC=Q\.fIK(KV#7'U!qbS%rHlOar`ou*=-Lao6dDj
sl@HLJnb1LmAu;Th0![.ti9P$@og=2V)4^j\!nm$DdItORKQXkeLE$$Lr7`sS[%?%PFtYnSZ
9?$2cW8Y@&-ZY)*s]Akqa-.?).,PH</5/o81YOACX.CctbUqD(`[&#!b'O/ae8\:lm&-!P!)W
8K@N=a`EuM#^_C=LML#<TI/:BhGElaf8:'3ms`(!`LZ$.Je-7<C5\2EU1D5ELr!P[$iTFX$\
7)o-ENSJ1P'*1tdLcCX=[V"c@jgJSfDTVedO#Ul#]Ag3K-;IO]A+d*]A+KU`,^QiTP%tUJVUJaA
J:3-Q6nWV59;1U!o"ups=l>cGBsUZUT2oV?@P]Ab98<uk-Ve9FuaQRLtD%KZ.O4j^W[0oCiW2
^m[%St$?*aoqDM&;\Lr[$8N'+t>L-NPhV#6V"2t$Vp+RF!3#RRVAQEgdm\emVk`?5M>3+Sr_
<B>CD&(fX=Dje_=KBI=Bom"#ETc$RM(Md+-G.#)of)MH@+i'Rh3ju!`#&`R-M]Aup;_pVnd5`
d(PnSW'U(ES0=AaI=LjJMMMSWO@V7AgZ::V1(_-1jD/of;LJib)Z`^]A%2B`pZH[U!W3XeXiI
s0r[V4!<$d?7grV/??c\Bb5'Dm0uLleYA`Fa#YA3C!#=foV1fDKjYW,''%jWUVWC4l?(D.lV
@'%k4T%?>R$pj1G(S4b=5_c;(\iQ\#+#<\LN"*QBgdcID(.e"$BWXie-[WPOi_p^/SX4cEj)
cO&nhh#]A#IKpF'TCUR0Q%jX63"(5O72iTcU2#TO2LCp!ZHAsUZY/\k=OARQU`l9(>VMqjLob
@"uP*P"aT9Tf0fjKl22"6HT`8K%t=6<ai7F@>iVD#,Ms(i&kofRem/h9:6bA7neo[Q?j3>%n
6)/6`5IS;3AC^tb?@d9a$b"\>90[KCX9HV+("l1YhMQo-^lXEp%,jO7`@XRF_MR`h]A(YA)ej
c)HMurQXTZ[Pel)mU.IZgO2]AMf(d&N.cqKOi4iH?]A]ARMWr:ZGqGP!'sVc1L,gpc:AFq>L7=H
4aua2ZY&Si#Wl.:q&Weh5_3X(\5l@CTBsA;!>/3M)T=MMTa;Y#jA#8MXHBSj`Ct)ms[9Gq*(
qgl5c0m<?t$mFgm2$:@p"HuTt9N6/:-$-OPgABi[OipX$5!MlY8,BnjJ=/>C-:M;ae)g%@VO
'S_+i0/t2L%=8*YV\cY+L&(S/a6$=F)i3EN/3;hjhGDB;u-LqGYlX6KR&iV0S)VT3:,biC1)
:)?lmTlQGIoN=?cien("]A52A?8:U."EudHb3.F*$;e%%R-npZshik:Pp5i-jd5[&[[OWa]Ak+
$/>NE2%.<i!J8dQ>X>/"[/[Pr_nKIY-S@ao/]AII%WMC="?-'O`27/DRLpmbHB2m.]AaNr/-dT
L4h/%.Ho7T/JX8p.Jd;90^1W!Efga_EM;=$X)LN9I@.51%T.\+TraX7JteL+[Ujq&F01+Z1Q
^5G;G*M@]AMPru>etA]AhF_4<s.di4,lO=<:n&b%(g\`Q.CUL\IfrToTTWbhf]AEmHb6]AP$KW-=
Vt.]AT")td!CuQ7DtN[o<]A>B@:q`B1;`p(-OJ=di;BE'LTZD"ndAfcGa^Z5_XA`^WPP+K^EDA
I`J?=p:aAXgIbS5F_`g'Gb#fG,/d[?U"Ia,2q]A45]AWRG&:.HMAB)m#sS*)]A]AAm13.:r<tM%k
<]As1ge7RqoU*LPu]AeQn8fWFFK<iOpHo<BH>la[]A8c6=\89bR)lOQ\NZNIlV2\kG^a^Or+T:=
<ZUH&k`u]A0p*E&si9G,3'\[&MJic)OH0W:p12+TmVFD$uUJ7Z6;jt-%X^b&sM>TIW+PTYieB
WcWSMmJ%2d:=55EH43$O:&*^E>9q&?n4MONR6'eX:9=L1nJ]A@/&ZD9g;BGOQb[Q9tH@L\f4S
@u-o!K<9inj=8u%8.aX5T/&3+8W5>cHT6mEGdd1cbai[;aNrP$Bm:Jj;q_5K/<&kBOou+Y-+
ctH$2"oA\=VW9i:T>c=<)gSEI*-4]AeaY=P[Y/5NI(^r(MRJo;dk5&%Ilq%*]A]AJN/)pf-`Ye0
3ZXn1?"`T&T.RdE-"qj1=FTISfkskYnK-l!,Q4UHGDY@3E^F2uK<MIiS4FV>PT*;P=p;2!1%
`9W3tR/#P_sJG39B&P4isFdCsEF"61tlF#7q*7dkkWJmPC7YGaSV--L*Z@NCltH2f8[_[(b$
V`*fAGlI8/umplb9TN&j<F]A(<2BlV)5jK<&=&4DeQhP1i!QB3Pi0BGdk6=UAg@@\YVV\02%+
t9;Uaol#';#%86;L'(eN%E_TeikpJU4+KjUQn]AB\7ch(3Tg7UFAO)Z4SEB!Y_cMA6c8.QARc
S$50">!nq":>RHHQFF%%._="4\u2$jnGD<kni/CB_l\R%#Hg)uh"!!gUb"fWl0/JLl(P^i.0
:*i6]AGJ6,F"J9hO@9$SWc,R@9OYNrmc'n^['S$AWgKT[tP+d7Gft)$9>Kr5ZG0?lDDo(:#L]A
_>'$\IW8M2HQ.St0V&KP4P=@SihRi*8h-`Mp89Jnj2]AbX62t`$K5pC+@"+qZC6P="qm=0'p?
CJj/sm'/O^l>H)gKW`Bn6Nm]AD??k3,3H]Ah>;-ed2G/PK2m6V/W$EWf]ASl6SO!G^3b\^/V;gj
ECc7p7.6XNZMNp,0X^D_cdCnUig)TbKX9DW>8-J=V<J2&'(.]ARgae'<e)\(Q(Z[--*6r(0t=
WRGdRDRd%++GTt(_cmX2m`e\W*X*MO1DIpoCRj[Xb'=*Fq@Nnh]AQ<11]AMF54h<%2dJ,*[^q0
2E:3sV;F"sgg('@&tQHLhV/13.dZ(P%bp!5>Gpe1k#$P2hPYL)908oc'I:E.^cH6X']A*7;8K
tCj:q3=:0N[q$Qp=LNN/EdSn$LSY:/C<:+onR%CmIWS6'$OSO&P/Z$,./5"O>Y[:MoG!c$+Q
g(JPYBbdnP;i&a(*Q.QH18\A:6*kM^81U!Hj%Gkmdar8W&-j+Lb5:S675,&o;2tEPMBf).]AX
jWNn`&q<90fNh0MiTI*)M(HN:g4rsB+$@\L18?d<@cBuZ?HT6D7"K+i9(:I#Al'6W?aaRHk\
Z2Moa4.0^Ut"ElJ/-M]AB:K2("]AjIn<6mQ1HFX8e(\bAFJQ-e%T$E9d>_q1$q/G1<o_fH$9^2
NJUqg[\KC\1lIsfoAYPuP)"&Ng64m%n$[QM9gU=;ZQ@GaeI4GAog?J'B(]AT8"g\E@ZIp[S8m
&`$le*DFpT;Z=S8Bak'Du5-l613NO&a,r3L3b%F9nI[E3M6'e'*8>G]A5(Ib/5BRe?7=Nl]ATp
YIPj$!Yu%<h'Rg&.!q,0?8p7p;c(i1'2(i$t7B;8HqY6)k%!9D=<fVtkA&!]Ac;5:+tGtJe`V
H9a,]A:0p=DK"mQdlodjFo1:=9Kp/kC4P(O#HXeOoJ&RJ;!\.FgWRoWQ&iWA`CVkU8B46r=lO
&dL;qmC6;=-FS!UsqTZgSTL9jucab6A9Vml[Nk+tVW:c2'-g;/W@[)hKqe(\G<4NUXMoBFGb
Oe;$XoPaR/cXH?6NZo]A:P\Epqr#eFSaRIht-Z$/FkLCIi<^$>C@onh4S%*i2lQ"BO-K9]Ah?8
dWh3_<:=U;X3)FK#ST!n$l3;^UhN)CJCeW:(VD\jE+$.pe82^+#:?(AEk\BUQN/I`4!R*K37
3e0gb!M_,NH?-WQM=XhUH4@_<%LhE/4m2N4ta48%`69o<<[SeZ.%cj0]AmKV-@D8BQo"bFVYo
Vtrk,&c"mC?B6'mrQc/dtbO)fr^ksPlM2H%i3:[;!]AFN*HXceZ^B/<dbthHe4X>9TP`"KTY_
96]A>CtO#."0Y,1<TfD]A5SK,5lkV`A=?rDhKDdV(RhJOX?o8=ThQg:-"SZ$]Artr+fK#<MQ`.U
e(`?K@u$8K>%&qu<o55!c9Y2ki>02AR*1dJ0#A,P_\a"J2u746k_%FnqWO1`[%mG:3WCK7s.
C.5j\j#f3Z[]AgLXkNl9nUSQT[H0Ef"U0RZ*LlfL4+t<XTd>_!i4rk441fEI;XL0GWJ6(Nu`$
IMaDoa53mC+W+\YYfIDbJAIl$72Yoi7pq'Z!d[r?EZA,K>"'U1+2bgckAD\gsoAGkX,Gh3KI
$;j"G?[NOH?LB]A!Q]At0H"X$G8JS/<lWY]AQ9m+1%_Oh"?Wl=D1,nW;hId%N`ZY[JI9J92XNp`
S94e-Ct_j`D+$B+Mia91L@RmQMkLW+OF%Ii4B2B]A,^,`;,6]A6$[ID)<'S+0@J/863Ed;b85I
4.>R5UN7ok=rqkSAn3cCN,Oat4`8`aDgjerfJrR-M5"8p/sE)@!mpQ'/B?hHl&ORc?8b*=Ca
('66J4BFgnU#SSgB)d@'Rgk=_PFqJbmi%i"FFoQ94EC4B9c.0FK@']A/0!>7jBM'RFk\8$F=H
^r$jP[9"Gul&#%E42m"GP0=<b5O9tnbOt9sKm^D4!h58Y!1l?%[SOHM]AKuBBiX%!Ucb:Gn_"
-RePWkK.d:6/ZcCslJGI3H<u?aAnSBMoC82oot4]A+BY"9q=_qS\?8qVX_hYQR5gB66XQk[pO
YB$[6Nf>8!!&RD#97oOlda61SVdZ[@2Uq[D+ckflaIE+6.)J<JaYZc3\nje9pMXuirsK%h`9
6oAUt-a^*oP3?MTDs">m`gOnkNerqFhd@EfZuqe/VG.4-j;"Ccm$Wmd?p)+/84H\VOM?9/!M
#b3`$s,hX.Mo,1l7>YJHUft.iW9.Zm=@R<g_O.gIW5eiShi$Sl;"]AS$#7FmO4A15=aD:=DM^
/IL:YNXZ/bI5,fO3Cmd,Wpa5=)%knbWiHX=Z_fi346FAR:_Dl@-cK3G'Ps)_p%a>YfWEieo\
r%.[T-p7L#i-WK>M1!NjU<1jMKG%t7jj>oZJ3P7NThj\mjI>a!(_coNU+<$Dqi(kZhSQb5-O
.]A#6"kQ=[G\45e/j?85r?[=gH.'[N/^T:`084Sg:%#I)2r,C5!r(EU&(K!g6cJ*Qa)t*I*_F
e;tWNN&.qkZX(dofj\W6"8ka=C?QRthaZKi3ohAu5]A$lS4Nm6egk0ZY1h""DBbaS/Ft)$+l-
-K8(.Mr'GED&.M9H]Aof;m4+<36n-9[.fe'K8Bo/Fe.5Q:?6&/pduqP`GY=Jg1^U:[rsDC9U%
f6c*FCYOQcE,#$$e5.]A8nOn@HN;C^q7PQ=>r]AU+]Ap45f/AaRCMDUin;mD9p5Pk&iYQ!&iBh(
:Y92]A8%*Njb_IRm[mFc,f)f=]A,eQ.D150`1pdl8Uq'[kaqu8!khN<p5G*j>0locuEd4hlhRc
g)qf'ehilr7)kGd+aWEiQ[X^Eu"8B(<lWp\q,>N"j`?/lF&p2lkMrsO;KCZEiD1Y&,soj-2O
g/NqY4;T&KFIH`XRX;!N/r!#@>LP0H<nbF_Fio,-K,W6Uo,5*6P#\dNEU1$J_`584a%N,62R
[iuLdlH8>V]A90NP=q$U68@49mnqA7^rt?]A/jj7T1&/`9(XXGf"c>(E>+[a^pK0SWL?#JRi^Z
#0s.YKOg*38oUO(1`2>rNm;..H8]A-NiI\K#L3&O1n/TMg<&&Z2SA7TPb!sRsH&ruqf<4\q3c
$).'&=^hjf`;+c\3U<E]AD^:D)V-AUm,5B7esEqf%pb?D1.9+=r:9/!I9Dd!DAgW3e.)!g@/L
IRD#S>o07$KB33<k2J=XlPX;@@pP4WmLLZVbr_X6Ba^[6\]A&Wq<rN8mRs<d<9AXp/SNcTgM-
n^N[_p_22?)n"%h??"s]A6[,/*$Lag3(VM,'74M)H!dFE_^5)Se,ti*g1kP)ni(ALL@e1496_
s_E<h=D>bl50A2Lj%m5XZj_Z4Ei=5Q'WPB.s;>KI[2g<O8(cB)guQL0[%gO^S9"@Y_4JE!]A,
C1A.E[&@NA"CYjDaGYq6[R_T$0mDgjIq/&(]ARp6r#6Lm+t\%$4"!.E`D>a*RIfTcrX>&,YLJ
`i"P$8<>&C6N@+^]A!/2r^,R&a1e,6?J]A*IgXFffQM=:Fko\dXqg)m2$.EVD4-M>Z_ITuB;ld
/SSO3(h\X7#m%%+;E0&5:CT=YPAe3VISgt/eqTm^QlPOX$]A?O&'a6`]A*#G$EJCC^"jeOXQcI
NT^$UVm5_nXgW4inEc]AQc6IRRFtt6^'`Z9A[f?7dc[YW_\0G'#'&?2GWP@<eiN4,Y9arTlhe
8%@&f)EZ&p&A4S-/>Xd?GN_r^?)QV`L!2]A;XW_FLHE#lIWd?7E<'';e`f;4V@"jI7*-VkY<A
'P3UgL/hsd[_l<=a_EWG>p[q7I2LW\_ph#otlB:S"Pr._iW]Ar@\TQ&YpepoGc7U5Lf3S_E([
>Q?J\ql>Z?@;."F0ANaj\a%G]A`G\LPu7OoKI_O;k5R>M-:0EQf1;9=AJQ.!fQJp%\*&7rrRC
V`qJO=kD7@s-:5OtpXbPeONIgKskChM9q`J[XQa,bn&rCN:5lP`Jhq'lH?_\>n0P4.QYFu%r
ac-Ci`0#BqI+s+FHl.4DTlK=X!L.DA,9GRckWUrMDUE1(UJD^Od8n]A?`_pG/RV8d`T0pk<H+
:I@,]A./l8RV:b@eY@5rm,eEY5EW,^F:_BK<M+)(0r9ejl>3=05!Q8!YSoYp9iLCSd*nB:u4"
nGKO5^m\&*eR+K,JDB&+>qJ2m'a]AEETp)&]A26[;c/M+`ijfkq!2bu\,`7X8n]A,f,J*pX70bm
u:KDHRB_o:;[n"H88_Xr`fB7e>0darqF[m^\>7%.GU"MWG,X*`d=5\G@P(sq\.(@Kcsr'@;n
k,1@/@I@B>r^"T7K.bS<UqiD`;^Trm(:phB6=<bZkTA`8il_pp+s5YR+p*`j:\li0ME^MhO/
*AK04g,O?_;"1Y3WGDbL?=("%o5*CX8/dQ%(5+]AWGTR&PCb,J&FIP"AHnJJq/R8h&r-cu9.;
e#EM+GYR6FnnlXF1PGpQ&STIk=`>+aZ=p4CMmhq]A4pJ7nhk%98g>YrI;Dk*E?;Zh,bVuMnNp
FHgcqAD]A&Ismg.WRpYc_>rrW~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="598" y="0" width="364" height="541"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList/>
<ReportAttrSet>
<ReportSettings headerHeight="0" footerHeight="0">
<PaperSetting/>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="598" y="0" width="364" height="541"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart1"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart1" frozen="false"/>
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
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\"  \"+\"地区数据(多层钻取联动)\"]]></O>
<FRFont name="Microsoft YaHei" style="0" size="96" foreground="-16749643"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-2953990"/>
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
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
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
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.drillmap.VanChartDrillMapPlot">
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
<Attr class="com.fr.plugin.chart.base.AttrBorderWithAlpha">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
<AlphaAttr alpha="1.0"/>
</Attr>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapTooltip">
<AttrMapTooltip>
<areaTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
</areaTooltip>
<pointTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
</pointTooltip>
<lineTooltip class="com.fr.plugin.chart.base.AttrTooltip">
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipStartAndEndNameFormat">
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
</lineTooltip>
</AttrMapTooltip>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapLabel">
<AttrMapLabel>
<areaLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" align="9" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="72" foreground="-1"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
</AttrLabel>
</areaLabel>
<pointLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" align="9" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
</AttrLabel>
</pointLabel>
</AttrMapLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="true"/>
<FRFont name="Microsoft YaHei UI" style="0" size="72" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" layout="aligned" customSize="true" maxHeight="100.0" isHighlight="false"/>
<Attr4VanChartScatter>
<Type rangeLegendType="1"/>
<GradualLegend>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="1.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-7874817" dist="0.0"/>
<ColorDist color="-13075251" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<LegendLabelFormat>
<IsCommon commonValueFormat="true"/>
</LegendLabelFormat>
</GradualLegend>
</Attr4VanChartScatter>
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
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="province"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report0" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
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
<VanChartMapPlotAttr mapType="area" geourl="assets/map/geographic/world/中国(省级).json" zoomlevel="4" mapmarkertype="0" nullvaluecolor="-3355444"/>
<lineMapDataProcessor>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
</lineMapDataProcessor>
<GisLayer>
<Attr gislayer="predefined_layer" layerName="清新"/>
</GisLayer>
<ViewCenter auto="false" longitude="103.826447" latitude="36.059561"/>
<pointConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</pointConditionCollection>
<lineConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</lineConditionCollection>
<matchResult/>
<layerMapTypeList>
<single type="area"/>
<single type="area"/>
</layerMapTypeList>
<layerLevelList>
<single level="0"/>
<single level="6"/>
</layerLevelList>
<drillUpHyperLink>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="province"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report0" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
</drillUpHyperLink>
<DrillMapTools>
<drillAttr enable="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="96" foreground="-5066062"/>
</Attr>
</TextAttr>
<backgroundinfo>
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
<Attr alpha="0.8"/>
</AttrAlpha>
</GI>
</backgroundinfo>
<selectbackgroundinfo>
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
</selectbackgroundinfo>
</DrillMapTools>
<matchResultList/>
</Plot>
<ChartDefinition>
<DillMapDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<Attr fromBottomData="false"/>
<bottomDataDefinition class="com.fr.plugin.chart.map.data.VanMapMoreNameCDDefinition">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value="pid"/>
<ChartSummaryColumn name="销售额" function="com.fr.data.util.function.NoneFunction" customName="销售额"/>
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
<matchResult/>
</bottomDataDefinition>
<eachLayerDataDefinitionList>
<SingleLayerDataDefinition class="com.fr.plugin.chart.map.data.VanMapMoreNameCDDefinition">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds4]]></Name>
</TableData>
<CategoryName value="pid"/>
<ChartSummaryColumn name="销售额" function="com.fr.data.util.function.NoneFunction" customName="销售额"/>
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
<matchResult/>
</SingleLayerDataDefinition>
<SingleLayerDataDefinition class="com.fr.plugin.chart.map.data.VanMapMoreNameCDDefinition">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds4]]></Name>
</TableData>
<CategoryName value="省份"/>
<ChartSummaryColumn name="销售额" function="com.fr.data.util.function.NoneFunction" customName="销售额"/>
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
<matchResult/>
</SingleLayerDataDefinition>
</eachLayerDataDefinitionList>
</DillMapDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="7de3237d-d760-48bf-a58c-8816ef610a9a"/>
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
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="36" width="598" height="505"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart1"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="  "+"地区数据(多层钻取联动)"]]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="Microsoft YaHei" style="0" size="96" foreground="-16749643"/>
<Background name="ColorBackground" color="-2953990"/>
<border style="1" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="598" height="36"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_chart1"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[地区数据]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="96"/>
<Background name="ColorBackground" color="-6697729"/>
<border style="1" color="-723724"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
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
<![CDATA[地区数据]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.MapPlot">
<MapPlot>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="-1" seriesDragEnable="true" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${SERIES}${BR}${CATEGORY}${BR}${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</PercentFormat>
</AttrContents>
</newHotTooltipStyle>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
<ConditionAttrList>
<List index="0">
<ConditionAttr name="条件属性1">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-6697984"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[区域名]]></CNAME>
<Compare op="0">
<O>
<![CDATA[广东省]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[区域名]]></CNAME>
<Compare op="0">
<O>
<![CDATA[广州市]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
</List>
</ConditionAttrList>
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
<NameJavaScriptGroup>
<NameJavaScript name="当前">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="province"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report0" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
<newPlotFillStyle>
<AttrFillStyle>
<AFStyle colorStyle="0"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="false"/>
</AttrFillStyle>
</newPlotFillStyle>
<newattr201212 mapName="中国" isSvgMap="true" mapType="Map_Normal" isHeatMap="true" heatIndex="0"/>
<MapHotAreaColor>
<MC_Attr minValue="100.0" maxValue="600.0" useType="0" areaNumber="5" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=500.0" maxValue="=600.0" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=400.0" maxValue="=500.0" color="-11486721"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=300.0" maxValue="=400.0" color="-8598785"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=200.0" maxValue="=300.0" color="-5776129"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=100.0" maxValue="=200.0" color="-2888193"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
<BubblePlot>
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
<FRFont name="SimSun" style="0" size="72"/>
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
<ValueAxis class="com.fr.chart.chartattr.ValueAxis">
<ValueAxisAttr201108 alignZeroValue="false"/>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
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
<Attr alignText="0"/>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</yAxis>
<BubblePlotAttr bubbleSize="50.0" bubbleType="1" isNegative="true"/>
</BubblePlot>
<Plot>
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
<FRFont name="SimSun" style="0" size="72"/>
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
<PieAttr subType="1" smallPercent="0.05"/>
</Plot>
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
<FRFont name="SimSun" style="0" size="72"/>
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
<Attr alignText="0"/>
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
<Attr alignText="0"/>
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
<Attr alignText="0"/>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</secondAxis>
<CateAttr isStacked="false"/>
<BarAttr isHorizontal="false" overlap="-0.25" interval="1.0"/>
<Bar2DAttr isSimulation3D="false"/>
</CategoryPlot>
</MapPlot>
</Plot>
<ChartDefinition>
<MapMoreLayerTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<MoreMapSingle>
<MapSingleLayerTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<AreaName areaName="省份"/>
<DefinitionList>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[销售额]]></O>
</SeriesName>
<SeriesValue>
<O>
<![CDATA[销售额]]></O>
</SeriesValue>
</SeriesDefinition>
</DefinitionList>
</MapSingleLayerTableDefinition>
</MoreMapSingle>
</MapMoreLayerTableDefinition>
</ChartDefinition>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="598" height="541"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="chart1"/>
<Widget widgetName="report0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="962" height="541"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="5"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="c4efeba1-7f2b-4b5c-a856-c534ebafdeb0"/>
</TemplateIdAttMark>
</Form>
