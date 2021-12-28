<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="内置数据集1" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[月份,,.,,销售额,,.,,利润额,,.,,利润率]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String,java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String,java.lang.String,java.lang.String">
<![CDATA[/WR(J`'=S1@o:G\3l"#\NBPo1mV!1V88UHrrLK5VH5sktW>Kk0iGYaU(q2]A^>%2C*OGb1NN,
3rSP6C84HBBLr+2t*+4`ZM:M]ApU;$=nNGaPeSK4+oD+g/70?quH~
]]></RowData>
</TableData>
<TableData name="内置数据集2" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[月份,,.,,产量]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String">
<![CDATA[*K/#K!&+\rhT7TKTEk?I<lE=MCr'+.+u]AU41Z;Ej#`33h2Ogn>A5/g&pE7^.\"+$~
]]></RowData>
</TableData>
<TableData name="内置数据集3" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[销售额,,.,,月份,,.,,成本,,.,,流动资产,,.,,净利润,,.,,销量,,.,,生产库存]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String">
<![CDATA[1Ggqmj>>pS2)*rF"(H<o2?@8s!s8N~
]]></RowData>
</TableData>
</TableDataMap>
<ReportFitAttr fitStateInPC="2" fitFont="false"/>
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
<Background name="ColorBackground" color="-15591374"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-15591374"/>
<appFormBodyPadding class="com.fr.base.iofile.attr.FormBodyPaddingAttrMark">
<appFormBodyPadding interval="6">
<Margin top="8" left="10" bottom="8" right="10"/>
</appFormBodyPadding>
</appFormBodyPadding>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart0_c"/>
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
<WidgetName name="chart0_c"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-1" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-15591374"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-15591374"/>
<LayoutAttr selectedIndex="2"/>
<ChangeAttr enable="true" changeType="carousel" timeInterval="3" buttonColor="-15591374" carouselColor="-15591374" showArrow="true">
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-15591374"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-15591374"/>
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
<Attr alpha="0.99"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[销售额]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="20.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.radar.VanChartRadarPlot">
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
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<newColor borderColor="-1"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="1" lineStyle="0" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="1.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrAreaSeriesFillColorBackground">
<AttrAreaSeriesFillColorBackground>
<Attr alpha="0.15000000596046448" color="-9864992"/>
</AttrAreaSeriesFillColorBackground>
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
<OColor colvalue="-9864992"/>
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
<Attr rotation="-90" alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-13155990"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="80" foreground="-6315084"/>
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
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-12827024" lineColor="-13155990"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=2000"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartRadarPlotAttr radarType="polygon"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[内置数据集1]]></Name>
</TableData>
<CategoryName value="月份"/>
<ChartSummaryColumn name="销售额" function="com.fr.data.util.function.NoneFunction" customName="销售额"/>
</MoreNameCDDefinition>
</ChartDefinition>
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
<Chart name="图表2" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-15591374"/>
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
<![CDATA[生产量]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.radar.VanChartRadarPlot">
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="1" lineStyle="0" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="1.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrAreaSeriesFillColorBackground">
<AttrAreaSeriesFillColorBackground>
<Attr alpha="0.15000000596046448" color="-9864992"/>
</AttrAreaSeriesFillColorBackground>
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
<OColor colvalue="-9864992"/>
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
<Attr rotation="-90" alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-12827024"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="80" foreground="-6315084"/>
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
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-12827024" lineColor="-13155990"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=600"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartRadarPlotAttr radarType="polygon"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[内置数据集2]]></Name>
</TableData>
<CategoryName value="月份"/>
<ChartSummaryColumn name="产量" function="com.fr.data.util.function.NoneFunction" customName="产量"/>
</MoreNameCDDefinition>
</ChartDefinition>
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
<Chart name="图表3" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-15591374"/>
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
<![CDATA[利润]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.radar.VanChartRadarPlot">
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
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0%]]></Format>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.5"/>
</AttrAlpha>
</GI>
</AttrTooltip>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="1" lineStyle="0" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="1.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrAreaSeriesFillColorBackground">
<AttrAreaSeriesFillColorBackground>
<Attr alpha="0.15000000596046448" color="-1"/>
</AttrAreaSeriesFillColorBackground>
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
<OColor colvalue="-9864992"/>
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
<Attr rotation="-90" alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-13155990"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="80" foreground="-6315084"/>
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
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor mainGridColor="-12827024" lineColor="-13155990"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange minValue="=0" maxValue="=0.8"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartRadarPlotAttr radarType="polygon"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[内置数据集1]]></Name>
</TableData>
<CategoryName value="月份"/>
<ChartSummaryColumn name="利润率" function="com.fr.data.util.function.NoneFunction" customName="利润率"/>
</MoreNameCDDefinition>
</ChartDefinition>
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
<Attr enable="true" duration="3" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<HtmlLabel customText="function(){ return this.changedValue;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-15591374"/>
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
<BoundsAttr x="0" y="0" width="342" height="236"/>
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
<LayoutAttr selectedIndex="1"/>
<ChangeAttr enable="true" changeType="carousel" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<![CDATA[销售额走势]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.line.VanChartLinePlot">
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded1]]></Name>
</TableData>
<CategoryName value="月份"/>
<ChartSummaryColumn name="销售额" function="com.fr.data.util.function.NoneFunction" customName="销售额"/>
</MoreNameCDDefinition>
</ChartDefinition>
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
<![CDATA[生产量趋势]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.line.VanChartLinePlot">
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded2]]></Name>
</TableData>
<CategoryName value="月份"/>
<ChartSummaryColumn name="产量" function="com.fr.data.util.function.NoneFunction" customName="产量"/>
</MoreNameCDDefinition>
</ChartDefinition>
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
<Chart name="图表3" chartClass="com.fr.plugin.chart.vanchart.VanChart">
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
<![CDATA[利润趋势]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-13421773"/>
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
<Attr position="3" visible="true"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
</Attr>
</TextAttr>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0%]]></Format>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
</Attr>
</TextAttr>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0%]]></Format>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-10066330"/>
</Attr>
</TextAttr>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0%]]></Format>
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
<ChartDefinition>
<CustomDefinition>
<DefinitionMapList>
<DefinitionMap key="column">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded1]]></Name>
</TableData>
<CategoryName value="月份"/>
<ChartSummaryColumn name="利润额" function="com.fr.data.util.function.NoneFunction" customName="利润额"/>
</MoreNameCDDefinition>
</DefinitionMap>
<DefinitionMap key="line">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded1]]></Name>
</TableData>
<CategoryName value="月份"/>
<ChartSummaryColumn name="利润率" function="com.fr.data.util.function.NoneFunction" customName="利润率"/>
</MoreNameCDDefinition>
</DefinitionMap>
</DefinitionMapList>
</CustomDefinition>
</ChartDefinition>
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
<BoundsAttr x="0" y="346" width="342" height="236"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0_c_c"/>
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
<WidgetName name="report0_c_c"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-14340777" borderRadius="6" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-14340777"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-14340777"/>
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
<![CDATA[304800,2019300,0,1524000,1568160,1104900,342900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[419100,1872000,495300,2592000,2286000,495300,2095500,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" cs="3" s="0">
<O>
<![CDATA[ 净利润]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" cs="3" s="1">
<O t="I">
<![CDATA[632]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="月份"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="净利润"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" cs="6" s="2">
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
<![CDATA[主营业务收入]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="2"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-15591374"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<OColor colvalue="-9864992"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=1000"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
</XAxisList>
<YAxisList>
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
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="6" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[流动资产]]></O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=D3]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B3]]></Attributes>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[ 本月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" cs="2" s="4">
<O t="I">
<![CDATA[684]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="5">
<O>
<![CDATA[本年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" cs="2" s="4">
<O t="I">
<![CDATA[3210]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="3">
<O>
<![CDATA[ 环比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="7">
<O>
<![CDATA[ 14%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="5">
<O>
<![CDATA[同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="9">
<O>
<![CDATA[ 0.4%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6">
<PrivilegeControl/>
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
<C c="3" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6">
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="192" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="240" foreground="-15680447"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n)H]Abf.C&,[50K*sY]A7Sh?$H'm#NO%P[lT:N^M0d&VOMX/dc4Is
Us?p1/m1(;BhK)`-U4@R'F5@>2?ac\>qFeg.6i)T;9d#Q+2Y?)Y+h0PNSr:isVIl*F+.U+qF
dUjYG-:6>&2]Am8)X_GnqDqgtnJVi+]Ajr[=r]A>f*p9CsgB/%La6t3]AMKrrbsDs(K!0ZEIm5EK
.V`FN2NZAQ[&.RNahL"olF:!PgBO5~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-38294"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!Rq*"s+sQI7h#eD$31&+%7s)Y;?-[s%KHJ/$4$h9!!%]A*DoVhX!1QPJ5u`*!A$CA<Djg"1H2
gIQ5WoMcn)k)24duD,=A9pG+?^,U@/jc2U[=iPrs7@9ruXc`"#@*-1p;\=D=`&jd"1B[D<sN
#>f,O>9-?)/)SrjGT!)r(E8D*69gCFp**,c>-SHmfZu&)`?;2R3s8RR<TFLd[Ctf1-,H)2jl
dL-bFq<cN#@emG-quC's(F\D#ih'Q!2;C.?ZD/)eGoRL!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-12721762"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[c!"g`PLm7?HAXT]A@V[(h"LLo07OTAjAC)GK'i5GQ&dAr!BFFo>A;48,;FHI96"SIT:b*)R&d
V#08ebg[LM2c'aWQ5[;o6b<>/]A:Hdqa<&o<e=YXM*6BCO"ICfA7']AfP8/X6ieJT+X(]AO@'>Q
C`+M+D&hBbY98Y#=]A+:6#88N#qhn-oe0PN5/OQGF5<6d"d4tuTL6'cUd-hV81h'_,WaBG)VK
0E.i^uM-O4*JYVjB;1p\oLAtF6iD2r@@\a-CQ=*^rL2#"72U`eJ>E)g9Tg;[@4"V[?^GJ.V6
(#>\aSWLA`@l7=7R/O6Buo_k]A!#ER4QB[Q32s5pO5rR$$0DhEid^H,>jd"Fi_g#)YVr\QoYF
]A01q0+uGT+17kYhRf_%K+qU/*p8WF4TW5?g>S?d!601,o.RG%u6-;tMp:Wd$7U,1-g*/<$:n
PSTL"&4jhkU!o9)VG&kWs^lkjs(?/-^mIohSJ8%c^7WiG&g,,7^AZC[Y@pL1NmQ,h:_$'@$6
MVeto*^f,<0hh9:uqsb\>_%PaDopq;/b'[9!^ki/"Od-usAC/gT]A)+)K%AZE<23/%22=r.BT
,KP]AkW[RRJ1>23.q;r%e3!bGhl#U%TCr7nhGi1sC@Z4e[>g9:5[\X'>UoNAjrSFRVoHpt^;I
k\(QBF%^;P8;@OjmVqCnM1?!%]Ag?*';'ZCkj6XI_qiLECt4.5O'?N=9tYH%JoC\f.KhI)=!:
F-t9F:%C2ClI3ranTlm%]ARL6iR+0WtMe3,]AFk^%b:""s3-C@/N[aXP4f3d]ADj7c->W>.U:X1
[F2p&R]Apf=EP=467_X(Ia5EM+coF;Fc@e4G/G-QtUSkJ%l\!P>a%Mm;.E'ZZYtWs-$rHp*U`
?E.6!mf!1"O-r2$,>JC)s1+DG;Kc`&'s71Lm)S`<GJ?"Xo-OO]A>ARTg4DS"C!E#(V-:bUd^#
t;/siQAUHpe1+l=,IRR.k>#$VSF7B=(b+)3X6d^,LR8to<YKT!H@uqYl\0=e*"kPOdL4SIn!
$!omUJ?)seWO>Jo,=)0aHo!7=c--\EsrFO,9-6?*`!]A-s=%#<cREVKL`cT07=@'q\^R.CX03
<?B/<=8>pCBo;T^/m7cToi_Y?3R'(CoQSTAG7f9BX5(^RM/d`T)]AB!%K["\<i8#afa4oD:IA
1"eTdlB+.h_=S;Ne->Y-7X$g1JXh,l6[i&!Z%Hd2p"_o?"4^3"O9GbRGh'`2qa$Yarlgl!g#
DAm`),q"\]AYgS9M[FO65l17f)BX/,qA+k#`N/5Ri<l='-XkYX:(KPdC7Mh8)5i'TpN-rGd+o
CG/bW#f1,3#$eQd<nOjJeJ%OE,OG\-#I$X^F:mC4HBR\Ld0]A*W_CIF"),GJhu2OODIXr4&i`
$@\^YFk1j?8n?l)!kgQ>ZF9P)\5i*XIq;Xtig>CK3JB>N?PKJ@Wpe$`0Lnog53mo/k=N#J0B
nBGiY3YJ3ZC'Q"#W\qLU1UTBiJ/;iQj-D\g@.cRqMpV+I0W,`:9@:BrjYJX=?=?Se<Cccu'r
W/`O04j+3X&Rj9P%W<=O6j_&DPdW]A&PJDdh.02X-_Z7%3\&%Rq`NL(Mgt=(2'C\0QhTQ<jX)
8Q@)(YU+T;<clH3Xg1X]AP?\pd>ZbrV#N!Q,p+Z4S9)lAM3i,&p$`WOmNCV\BNG[($.%S-58e
fGr+m$RfI0XUg%J?$:SM*GHba\,"C-#EULh/U-15/S+"/ul4s:Pu&l%[@@)e3p+I#V;_t9F`
3Y&$nNWE$Eop:^JWS.OR-FlNk-WCp&5E@X;iVXJe8]AMe!AFImWODQPnOX@RH"r7-n!#/gjm@
@!pjK0L"[)c+D4^1I6`f4P--S)1[V9C4/t2c,4tmNUfor&L0AWHc`=_Q6JMB>KZ9%c*285W+
@"5;hh+%_S#;uCRNV8mf-=mr(!S?2<Hp!1/uI+h=?\G1Y5kSFIK,gpH%5c">rd1(^)$7ETJ`
`jnC-?JeIC3W]ACat,X7*4m-qNTJdotGO+nkmNI<]ABO0u%d.%9V"Q-po&BUrHL=.upi6NX,k3
o;or,Ujm*bJ_U?p:/2qQ?5QUS8Xe4:(=W`VQ)E%,@6SD8cdmRrpg\jL=q[)68)F8Ne6-1Oo>
u#@UOQA<GGa]AJh()72l8j"M;XmN]AF:t$q`G3P>SQ]Aa\h.nQMCD9q[*fkLM<IW,87/aig/e5;
[+fsjb&%u&4OK%$Deij"%XlSl[0%W$(To@/RmY)8(4D\38"L4M9"ddllKg6."B?aN^X6GQ6n
dmT76!b[;%g1J%Lus:(9+gl)foqj"V)1>^(sp,TUC->+I:6AY,-W6[2Q25Oh;sYOaC;a2^uU
KZ3E^.0q"^X/$R2'Z(#LT#VYg7+9AK]AA?kj"V?d2_-,M7<,$JJ6M\tGO0O;fY(@<2>@9I]AmZ
Vm#kG9r&S]A(qauaA3((BS4dp62J_=hhUs^!^4eM^B<W]A)A/5(g'D^AQVVEnD(+*%'\[3O51e
`jA]AjcY#>5e7/RO6n?=!3PM3,t7kg%jGY1K7K[J1EenpB%f4+Pf79Q/7:ekkPF#%-FR:n)58
cF;uc%$<UQk:P;2YK0mG#:1-MNnhHe%)Xa`"tU0F_QeR6.:0).A;'$2W$dR3JT;&YMSEj8XP
]A)6Y*><WDc.@p8_N&?ZX>)4fHq(T2('gMAbbU\rZd8TRKX3Od7)!\%'[NbjftA)*&+\O1_#[
o<q[^a:TT]A[M$`-4fWRG1d4KqZ$AlDBp8YrRf$dc.R4*b&pNGoHpLDOLZBa1C\jZna69#Y$C
+X5Yo'h#s"unMO)Y1Hh@KUs^BiqCO3PbH%n8qTC@8?#=C8-CXq+OK4OD0jH)7ci(QV7$mD(=
C)f/`(a+hQ#*7^<=4lKj40@XG8LCP0GA$$j(VPkZ#/^+j96VE/buo2*DKS:,>(P,*n1&?hBK
``AAK2rg"9q4fm5Fp)el['Reo\pW'MUB'4hMQi?#]A#M?i<609IGW6m:M;C[hD'ltKNX%#MGU
O(@YjeHJ-*oqtoW&C?,nYW28ahP[![6blPB9,Tnt[EL1AD2)a&F8mi/qpNjBSImgPl'L0's,
(;G%M'AgWs!"D?5OCJE1$kh4sg!e-2?$Cn:[&Qf2p"]An-%Zurih\b2!&"0BUhBo;(f)[A"JM
)$CMUD+k/0@TAY!p-r5<^'7pSoETB:"kiOP2ahkVc@lp@nRO\PtB&)*3&H^^2+0gVj(&ZejO
t&/c]A4^KFm3sfcIgpNe]A'69`33Uk%hDZ:Xk@d9Y5A&9\I!1Ef^s5(@&W$euhc]AJ\=.5rT5e?
.;DrM&9)N"%t7.&DkH#$MK#SuN]A68[<>P6/4.IH]AiEn5$*#(#?Y03>L^AeP#XhK8f+3S*:Pk
8QU['s75:T6-u!?G=$g5Rr-l9iA[C\Ght,98Gl\J2*:NVHMiCR$(lp0-l!hgV%Dp1maCIjCS
e5KY"']A4,L]A6.J`_i!BP'62,3"Ck]A/Om/d^(DipuufE2Ucgd1(`Fk?nA!FsL&i7Ohh3g_c5G
Yikg6@[V7.pk,T<Ph,mB5UdBiIW]A.?RXgJ1o0^2p%icgHTmY:,q**J>YTGX^VmnrBY9q=ofU
/RZq<(Vn*>8@eER;choF-tWCARSM46GW-RAG'i``U*Jhnj&G!N4(WOg(r?W1o[6W]ACWfO35<
*'7Trp$t-FNc")5!4Qe;7bi$IbFA;DBm_g.Q$Fs`l<]A:$.W`Gt&fK"a@#LH%*@\Df1!-0:]AH
(A:UWS8s]AO&T#C9ds=25FPY?gf5VC9#\*]A0DaL_Nhm<c;tP$lAdn86-V)62'W^&\]AWPMhZ.j
]Ar8`fGi>^?`$\!!J;ubJ6@-XFR.JH*n`W!uB<nhY-^s96l@:3a'F\K14)4)gC6ZIn^d'gha6
W.P+itP&h4XoguPouA3LSF'aMM:Y40*Z]AK,$"%'%9h0g!7&\_j;a`%/2hOfF;2U62#-N)n5D
&kMVld2MaLAdPIA+Geeo"g,)VrqX6W0b>taS.!bIUK[m*Uhc5i6)><"p^DPfbK,*^P#9KpPU
@I-$dli;dupVS/[('l)S09n^LHAkeRf05,??G4i7+%:*XHLJ*oHJ<Ok61J49ot1qA9=fKf&(
-ZaU*^\m,%I)9djRZd__NAOT19`B4a5dKbN>b+o\,N=6*2/d'npt+4>UMY5BUh#?)/Z37AS9
a%'q:u2fj&fT&Lgd+O:IX5*q,ZAX[7)'b)T:S<<:D,9;[?*+O>Nm7UK9Sp4<BrC,mQCikfCk
!3Kf[L$+@k#QB1@A):2+uVH@=#/UFAk_s1;1+AbY-LDRB$lp`/dLbcJ.T/()U,7,mbFKXoop
C'dC9ISK$f><OCFZPog2u6GYbRTYC?>fOU7f#pn2aqJUJbH2#@d/B,?Pca+l#1OkdIKOMM*s
fWi%Jc#H?^6,c02mc.PYMf2"#cbSe!Yl%uP;#93cXUYHQQU>Eg2;5:nRUZ!_I;\r%2ItH*D_
^IdQ!A\i*\nS?C2['F1h!ckJr/*rm,dtQSB-4Wm!F,ee*\#cT7j*.$3k5V7p7THPoApjZN8k
f_==i[\`VA:OTfo$rfB!(0]A?R^B"V&*jW]A@a)Qf9>TWL[VmFWcDWc^KW$]A?SO4DbJ@qbRd^D
1Aa?"P4$R^a*[M(Z3g33r.D(_HfpX&%A2uA*R<(Kas+&EnTgnI;=^/S2J0Wj>jnVndF8X(U/
=s'r^$9p3_3J_?G1tmX]ABQ@TT7ZB=.\8L_nKG#X4VH]Al9F811,75N')Oad!8Q-C5$Yf9]Alp$
/W'j%[s"JnOu+4;A!2SBCJB-?B1jW$EZ!7kZ=7BbUB+8V4H?/VNP0S'08FGZdP,TRf\2bX]A,
F`^GpKL[;q-CI-*f'<6Jg?ilJ?h>^(brn,L#_2U21'baeF<1[p-UbD\=P$(i9`k%A73CCDI^
;@_>V"/;=.c7X)4.oLUekits<p'LiTShO_a(i*oAq/'7sYo_!k!G2)&X8o&q0khYMY^Q/pC4
Ad1]Ann=$QaB<TB`+%!.gdM+)-<hEN4IV"d'b"?q).&[!E`]A!?="LgLmQ3MC@j>73gO!AJBih
qn>BM0.R*e)0==gOLCKK5.K#;mmG,d+-.<uue/]API?.JkHj$Srm."emu8$?EUsHLEF^TR95m
N-G:7!J?[]A<f\8I^9<4Bg$!!.`FiTd`iXiIe7Zn?dCRU=K]A'H^[Gf0boZSTEkIRe-E;DW@c*
RA8G!JpB8b$j*oHrC5^DX4uPm*9VpI-$6/#t^tk!b16+'uK6V(`g_5/k6&p)-f@Zi+3!CL\g
?=5)q#9MIPM-0>H&3Rp(bF^^^Yp.WWEj/=iTPBYWcc?hY4)fIED1hG8s`gm:ObOfm1[fa75d
9%G!6.&I!LdK#CSeluj?(85a8pmq>d6dM0\+7K=B7h/noB=jmiD^."l$jQ-k[N/tUVm("Z^%
%o`9R]AOU\p2XhL7MGK[LE,Wd'Ms/re9h=8D`UG>]ARNj#:@`ZY(7@)&:02_/fieV_I<3H,$:5
ff=@*EEf>0)QrW@gUZ[J>mA@?TW#;4/\-VhlJ>V&&!)rr<OO7qW`hq#1CR]A=ms>_]AU:ruH0d
Ck%Og?j\mFZid*gidF>Q0&&W,'Ai'Me;J":0[fkF%Me_t<b<H0+)I2FStudkfUYfBAJB937N
@\84qkLGajKhAQq>GNAD&@k_Ben90"3&SPQ?]AfhO)JN[`65I4HQIqTjU[+K++ZT.(n`_98I)
kZ>):eXQ`&KVb7mkrOmft36sk:rjIeRNu*L;BbJooG;rLQRQU3C-)MCglMG2b(:AP%HuE)`h
jG"-aJMQDKdV/B'PL"7\"CP:;("470qs`QJ!bUAE/(c\QCgkOgEQHn,ks_K&c_\=g4:EDE>$
*3F:D'$n/ShlYL?p]AM`l-Un<4p;M,+.D.##-mWo351\,CA?XJ7o>5"E9TX6o^#k.GXpEd0Zs
V)$-tCo:?d'YUq2*4KB)+Qi+!VOjdlJ:DZL;"H3U&<j4:HI?CiaEK+RKtHDZdY^;]AF8_d'Kc
W-s8DAQM:_(1HC$(#Glf,CkgHs#_7AP_Ja:^0-*&0JAE,/DKEOsLXmMCANHeGm@K,p'CcL1Z
Ur""f4VM+K%-lIHFNfNKQ_m/iIo*EkXN7+9r\![HW9u'b9tfZ\uZ`B^\ikn.!#(N*)N7Wqum
_.83fY5HghKQo:9nZ^?QumT6X%Ib:5RJcB9)s.eDYk:KpIcm,O"Q_/!&=qG$2j=GB$:^>aZ`
#IDYC>2t&&c&r%l%8+B.BBe_Tdm&;ad\hQH`5eF_iR/=lgXS"YEWpbEYY';s_@uoFR&[%4qE
q"32;:YHk/G+3O;>K'8)-2BI^N[hL/'6Rc1m>4g,HcAJRU:?Ug?r5%`k5g5O1qB&0+n&JV^p
Ah/2WZC5Q9%[PT?"cf[,7!t9j/8%R@rKqQ9L)?h(8J?/.5OH=+7=$$(i&<+KcK5t-jO$0^N2
JfQXPPsjtN$h?T1_OZJhDs^/Mnu?qqR"n),co2Zl]A.0A&\F@Qn]AokaQej?752&o>Q2coN0Ro
*[&*A*`2u%\?rd`6f=?r/s_&7jUCG/&b<&rp`j6`mm[B65pc74<*AlIfqNP"sqfNO=^bNRl0
QYS4j*j]ADoTCj^mE@S;J;j4AcnX!.s!0FM&ZQH_2+W]A7$]A%_L5=0!.^<;WWPkLFlFUM;!YC?
^W!G\JhKRf;R'lFh@cp?-a,c+?jsGbt8O84e/*iZK![Qf)^6?8@'/CFGe!.Uuu3[k544!:YD
_*VN!fPhprT9\k\7=tVp446Nk421A,pi'.0>)1G_%/ZV-_<240$-GI`i1EB15rBjr*o:M]AaT
t>duCoj?&J]AS%<4;k]Aj\2lD^EHAd&,';LB_14UPT_363>]AG<-(O4+Ja:g9(:njZ.11t5HIAi
WlbbPC7jF9X6R;<Y?UIFqTY6sDc9U0pi_=5-V?kbW)`1Pq^\]ApAC@elpt;:iTcik]Ag'NurbI
pN8+#o2/K\n,R8%=%f$J<'$UJVH8n':fP1*#@nc2r&3:<q;lF*Y[6,ij-N)p'Clq'90bqCKD
Jm(974uM?t6_h&\M*4;ffbPGM.:9Y'G%:*I<*e'n;\B1GGAuK"(-d9@NlU\qC0'/^>ATpVc[
t!WGuhL6bQ3J2khC7VtA9\Q6[+('krGr_SnMbQkMDRpa;$=KT'P<ii4OMq[6X"ofs;`;,tF\
^PL5'@!b_HtRc3"5to@;lj&lnlt,/@!)-eIqT:IH:R+XMA6%KT8&X^^JO8E$u-5B--:La-]AM
=mh?i]Adqan?\TEpl+QVHNLM';_A.<_Rh+2V^`ImeCuXu85UM"?[YF,\2)+LuT)]Aj7pTh,7&E
J@Z8IqtKdf3?dgm[Of7CR)8=_r<Uf]A<.!SgiGr(ZR6$Z,.f8\tkrM@/K:hR0-!E\"]AeSi)cB
\;*c:jfHoo$n&+.m%(gYhi;;0%6f1*aOrWmLp?2n&QZ`c$q6"ok^]A9raAkY4q\,=c?To6B+9
+BD$:.RfA=D%lkR"5f83JO`6[#0C&B0j)/=s!]A=\43<GVTqe0R`2/I3TcK`hm9UDfuV&%]A$(
+QjgpqK8Hhq3b,@d=<25q&q5W(#Ctc%Z`f9dCC<=m`]A_5M;JA^Kg*a,%81Ur[@a?Yu/E"HFP
F]A1P:GW%:R25jI/5&2>$]A^S#Hi9IPj]AZ+T5C0USDkSn,H\/h]Ad&QqRrRCn''-%Li;^ET@ahW
L9`ad$]AJE9)dI,.El@;['`Cm@B@,XB8&lomQ0$IYj4SF>jUY!(M>1?C`qPk.9..MT1>Y^d%_
-5`dk`61eIWIfXT&P#S`.Ga+J>njiV7%ZB(/Z3Q_]Ail4pokKVFJ(uLOF:/183E[<%KNEI1-+
%D$h'S&ZGq<hsBb&DsP"4d><rq8si%s1rgYU(NOm'5lI$e>_@s,).l>;CT$q$bnB#s\XD")C
s9.+rW`~
]]></IM>
<ReportFitAttr fitStateInPC="2" fitFont="false"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="171" y="0" width="171" height="116"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<CellElementList>
<C c="0" r="0">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[]AiYY=;eHl+6Uts:e]Ah9u:a#.;[%/tp9!g]A+M:?=8!iEK1G%rm0[<8ST6kCUe`,2%`'t@7V^]A
tL/<Le&n<@gF]AAF4%TZ4]AL*#SAhLRMuC8O;hX]AH0cX@,726(mYij;OSdp"s#o7"htN[_3V_h
Wrq*[OhkJM3doWZ)f$\51P/6V1BiDj*jf?[D1tk##X.]A61HV8ZHiX;/k3BV0U0dW"T\#?ksP
#D:IOn2u3bun!EG^S32X-Kd9aG):SgRUgfbk6J!dbI`>=0jLE8:QuK2'd=t]A.HX?ZQ`m\W[.
GR5Zcfn3NP,Q/aB9X/pFjBDK4DT"V2'Z(/%EN6[.Y?_-bJNfo5.kBdYo4nunb&&E+R"o()pK
,+pXL43n4!g*u;In[`b)Y,D=4\d"%W).qO'b"#hD\&b-=0HEd?e1!W<dqnrnQ+%HEqtXP!I>
O[Xa*g3V*jIpDbO$b-aEnpsFeemP5?%BlF[^?e;Y_+Gc9a99/TQRM&qi*rgtBZ3\iD]A&8#Y&
*0/\QPU=V]A$pLU=a(&qrS_re=mC$miMo4gq.l&\41Rp2&:87";FX)V:R]A+1ItD-!]A$_qMs+H
t64WQ4@I2[=m+,bArAf['\N]AYg7BN4,UX,Qemoc#1S$2+#q?;6['4\+Q8bGQ1K9CTd<F'=,W
Vod*)2qOi3HdX;)0+NT2!&?O5;D/S<#jo38`Ub<q===5UOJ=k&_7/5a4UL.<al4VcnN>bkR;
d3YCQH0VY.:.-i56Ml'UL,]A]Amn^OmYdT$Hdq=*?5'E.JaB(t51KjE*1S5HQEV!%1'3ZLKT+V
$.=LBFR6>jfGPbG=qeCb.i+Ce`=[`7*F)An(#P.b7B'GO,1@:j#RrH5s=O7f!^GfceP^?C'^
mn5T$]A')O,h,h)3/Ctq]A*W6[t9jIL[OL(Q7*1]A"u*TO_VAHAFW&Z$/B/NuA0qhip*<5!_djb
4+R!--O&Ga-*,VF_QtqE\/>+_1c?VOU/#gWA$FtJaDkj152QDh/7Lh=$21lpAWlj8=+j6?K.
[#,82qHWac5NDYqdCF`2Y`g9-A:lk<"IC9B6<e\-*-d&)bOa^ts:SC-A5Xuf7K1`iQMIcCCd
@22]Ad2V6C1YU:Za.gu-N1%<5o>0Cke+o5//>$+O4(D#D/`/NN5DZerZG6Mb<9_bc/+G5o:ee
mQ93H>Jad*'&Id^J/_<`s".q4b*%#[?tU82a&\_BA7Cj*nM`Q?>VTbq`V%9<@Q[C+&rS\6gr
i[>H*g%.6hg<o:5J7H&([9ua_:OGfifLNAsR)QHC)YnU(eWVuusY^[MjhpgB!$DWp"dK`;Gk
WDI\+3qE7)HGXtl90]ABhc%2[0Q--kKfF]A<,?pj%it)2Y0/>V4;@N-Ii#4"K%4.3^P9Z$TEXJ
;?V8hgPeugAg/pr67".Z^@M^]AgAIJp_Yp>D*9$kdb9Bc%tHD:3`;s#=E=!X[-aR'ce*T:_,P
II'pUa>gqn/G,<CHB6MKM#X,C02]AXA'o2=<L1>;4\&U3uO&J7MId1EEcKLG*/"'h@,e1!FqB
fp,UA7pXk`L*0:OL00YT>/NO;?m0KL'6.aDMF0V+]A&W@W.kJg>J=XUnK7tfmHsaSW"&*]A=_-
*qNHYr(aO_1Z;NlS!U7cQ*kIa*<>'0U%unK1C?X4X,u%\i?70DG-7c$K9p(=_R86(0IG(6[q
*-^a"['0?G#rFCTVS&8o$PT87pk1`;?I:D>%QVsD/mb;bgph8;A!UR?UG)@>lT8i<kY7fs+:
7IkeaL4]AXV*t-8oSgZA%>[/*/IU/Wr0Ik#8:%gf"@q3=7qpEjtiEr2A<cb22sHg'Q1i`=DZ_
k690"B2$_&fC)hNAIguOMp2_>cGflG)uK@AXInr[T&(nYoGi=lLab)\3(]ABf`drR74G0k"52
&#nN_G1V2l'oRM=N7(l7V?Wp=XYmnQDBH6L>Bbf\/YS&L;(s^g!;`77VW:%)268h7D>U6@Q)
D[;HoAYpkZSCID#5mWV)RU(r,7!9A3e]AFK8dVYgqqF#.XuZl;SoI*ROq'!S[@N/U%E0I(^G2
S,),;TbYSQqP[kF%@]ASH\O-@c]At;qK/l<Z;qYDH-)r_k5eOK!rr8?`_*h'1E!*\-nEmKkXT&
pm%Xf0AImVdD#_MqkK:$`9`$TR+!+UX<-D@=O4fHdh7f`ns+RBYO'*Rqg%VWKZjrPV,)MV'H
0gJI%aRTO@Hd/J!0H#PJ,OC_Tj28fMp72=+j!F^'7_;`$9-[+U&Z\7+50BZrc\4EsSm!MN:b
O(0>HP8rm&U-"KamI>]A:+SqOXAI)Y*_.cP!N!IXiL$qR)oL5G=G.]AQf:q8m/d`6:^5X#e<GR
_qpEDM6mK0;7a^FFp\SDn2\2+>FZ)cH\JES0JV4=tkhqC>2G"QLfG7#fqA!5=)#Kir(VE4fS
YfA^c[lSn'"Xm#/5b]AV!>,X@o]ABq]AhZ*X1p^dE\+deJJFs'\Si"'7hfV/_">L*!JP2hD%R[\
-j9XK=j12Pdn=`pj/HH!*SE*fH$_^EWS[RPpq`s6%:Hnp4d7Blge&?E&'8@%?j]AMm`eMDc<K
L'_RQqaLGF-<37O?*eV(F/HF9?MgEb<k99CqQ+M8fnBaF2glU:1e'M4SsKh\`AH1LLpSLZ.I
C5uC?SCH/,nXTk,8PrpR;B/3u^>b^:&jYS7VslV.@8JQF#<Oq[?Sqe<t*;e*Ztl%`!hd]A1+W
.Z!l4""6OkZ4iH"1Fb1IJ-hP_!^P%=gbJ,@$o4P?kCGeerM5nL24X9`qKlV8h!+.9E'?Qrc0
'fkil:<ORb'$f,V9i&&/Fr=XY!]Adjab>IU[,(84KIq:Y\*dcZXW`Ff'J`\O2#al+m_%,ko%"
=[CK;LR.RLlq/^#sUZDb6;!0bamnsqXeopIZa$Y?1%T#"<^fr/?p+tU_c8*S+'K-Ta?m0`Rg
\7)p>lr0g0JWfTJDR"f>9d@p(1FUte&cqgRAT+r^WaGXM(78maYT3,M&Z`.t?EKtq9(<Ja+S
0(urPS2i%.ra3NeV&(hplVTHN)PmrN.pGZYIGtkjg**^AAE~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="171" y="115" width="171" height="116"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0_c_c_c"/>
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
<WidgetName name="report0_c_c_c"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-14340777" borderRadius="8" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-14340777"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-14340777"/>
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
<![CDATA[304800,2171700,0,1524000,1638300,1104900,342900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[419100,1872000,495300,2592000,2286000,495300,2095500,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" cs="3" s="0">
<O>
<![CDATA[ 流动资产]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" cs="3" s="1">
<O t="I">
<![CDATA[124]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="月份"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="流动资产"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" cs="6" s="2">
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
<![CDATA[主营业务收入]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="2"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-15591374"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<OColor colvalue="-9864992"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=1000"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
</XAxisList>
<YAxisList>
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
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="6" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[流动资产]]></O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=D3]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B3]]></Attributes>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[ 本月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" cs="2" s="4">
<O t="I">
<![CDATA[705]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="5">
<O>
<![CDATA[本年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" cs="2" s="6">
<O t="I">
<![CDATA[8470]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="3">
<O>
<![CDATA[ 环比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="8">
<O>
<![CDATA[ 14%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="5">
<O>
<![CDATA[同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="10">
<O>
<![CDATA[ 0.4%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6">
<PrivilegeControl/>
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
<C c="3" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6">
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="192" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="240" foreground="-15680447"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n)H]Abf.C&,[50K*sY]A7Sh?$H'm#NO%P[lT:N^M0d&VOMX/dc4Is
Us?p1/m1(;BhK)`-U4@R'F5@>2?ac\>qFeg.6i)T;9d#Q+2Y?)Y+h0PNSr:isVIl*F+.U+qF
dUjYG-:6>&2]Am8)X_GnqDqgtnJVi+]Ajr[=r]A>f*p9CsgB/%La6t3]AMKrrbsDs(K!0ZEIm5EK
.V`FN2NZAQ[&.RNahL"olF:!PgBO5~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-38294"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style vertical_alignment="3" imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!Rq*"s+sQI7h#eD$31&+%7s)Y;?-[s%KHJ/$4$h9!!%]A*DoVhX!1QPJ5u`*!A$CA<Djg"1H2
gIQ5WoMcn)k)24duD,=A9pG+?^,U@/jc2U[=iPrs7@9ruXc`"#@*-1p;\=D=`&jd"1B[D<sN
#>f,O>9-?)/)SrjGT!)r(E8D*69gCFp**,c>-SHmfZu&)`?;2R3s8RR<TFLd[Ctf1-,H)2jl
dL-bFq<cN#@emG-quC's(F\D#ih'Q!2;C.?ZD/)eGoRL!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-12721762"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buo<SP\^/gl6JHR&CF,'b*$>A%['/XN!r%=H-2GLOVpf'7`V*RZ8Rso=^oAf9o':Z_+_tb9G
/RCp9B\9kY7TA4eMK@T&4_hja!Hp]A'fHB?/KG.Y6d:-B#MTtK+e@hnJE?,3!05!:]AZ,*EjN*
WhOnsd=+NX6Fc6e%c<X'\8Ti:#G4r,9e5""YKbV[O^B=rRT6323j5Lc[DU6FoWY,L9Y4,5uE
B2"86i6<,<[l4J4_D(#)6.cQ;@W<.*&tGC(6<E+;?<D<UMbOmrN1oR[f)0ue-K2e_$\_X5+O
o$4>XHg8*rp$[VP^PP9(]A3[nSDhW%%)p_mK-O1Kqf3""&r"7Yr%E&!%JUgX,WO:cdV-<+B[f
0uCHYWPnu,\q"t=(*[ikF.dETP<UKs:S[X.Q..Cb<iKO>AYfKNFj^-/#KfhjoRULgIH&\YY"
JK#>kML.J,LLdf88,kdZE5!K,#-7l]A]A3Ai_R8F<&uU`-]AOFe^e$h_`rIhKYn6&(B:>__[r$a
3/\')q2t^,opT9Zk&Q(P\ad4/JR7E.kH2nK)4=OZ@UsDbLVD,N4p\q-%DQUL-6+Bj!6Y&:s2
W>`?DI"2V:"kUI:h9&i[JJ)>CGpC"dAf'ZK"886-O[c%FO$:^UC,+Oe1"8O,KksnV,)B_7`K
A#-nF5\9IrE06BkOrpa"oL8kYf**U"rN=+GKpR\<_#FFi;'dX&*GXO:\@U)I"J[2:+2Fl4b#
lcb!s:l0WsF25&qBKLlPn2;84l%pEHd7#;KF6!7$]ABM?ZC/T=6p/$MM32X3n%tF$=m^HOUFZ
[J?a=b))p4df^a7XuY^c8P:g;W;uK9t"N4H_,nX7I8,W5fZO.H9F^)o8(RRe4>O2=J47n%h'
5W>Bu/n9mm)]ACBUf']Ac_.p3Q_MB4FOj#9nZ6MVdtH*)G4M)S8X2s0S1ppV[/F!ON73gF'D['
#)D(;RuHWVQZ+L)NLGBoR^)q6=Sj+A(^W"CajkC\?K&rn.r,\*_=@^f!?E:He[%C20PQQ2^N
(hY^0r\RqTE5NcQC/MP8^^p%SSTW4NNLh!jC^Z#B5`/d14UMO*1,i73-<j9#_H:\%cO)#502
5eH"-F08:uQ/`XP3.!]A+`lDN`ST9sc^9Rlsoo+-=@/#l6-gg-8)G":tKR,5a:r+IFTa/2/BL
K]A10R[O(VWEjJ)<S4>pB-<:EaiZ<Gqb&j3ehDd\9*1VdH,FO4<NZ-YG(1;]Au39^'12GOq*C1
/XMX6D-Q"p9h36MDF<YAg7-8nI60"u7bl+).MG[q*hFWs[!XB)YHbGk+-6Y;6dn><,Rs!`Fh
Ib4^>?q@a(CeLi\So3G'(HB51)5.iBU0SRs3T+CrWY6Qf+Q^hLQJ#dA[!]AK2QldbTK"PQ6E1
*M*1h3]ABN$GiE>U`"*YprfnmX)FM,H*S`/oYU[nG4[R3R7">p_2Z[^nLA\=p36B5oWnQTP]A8
I%ZdU=c2!NpE\QUGJ_>r1@i+A@cGeGo95X73f3udkl,*U1nh.X:oe24qaBO4]AZ$@u_Kl*&(V
@O?R>0lajJ=q1:VSKZ?.=mJZ,*&PSO.AnPmJ70(Z<_DnD7Rd'$lJ!WG_%fgtKj-FBR7o2pF9
RcqUI[n#9iVN&I42/4djW"ci]AnZ\1e4Ts?muD.JLnZE1?"4ll7ZVr$\f/c\90iB^/0JD-/0a
gMM\M*h_>DMY<JZ`rW=:RWR!5(0@?P`&:n['u2r!b:-'LOdOUMBps6,!WWP%db[.9lL<Cr5g
OE-Rai)!0'c=-Q9oT&B@0iH+f0;hbH7::sLQKX5Egn"MN(*oA8S3gdZMNmD4Y%dUILOM2eg0
NaUh3[gJ.4o]A.\gi5*^6WALZS+pa;W%er(h50KURGG@dDm41AqFeuA5?b<":H"FTk\@rh56.
1lGDa;#3/K[k+/!N,b8NY4c]A;TkU$Ose<J3*iQUCf\+@0%u^aA'XqKPeYV@!A4b\(T_Yr0aj
oJaaA16d7VP6Y$iNj/N!@-F8BchlHo#gLQ)+D)D:r2eXM!c33lA21HJMQf,TsB49P(Tn(CEA
$;1&ae5?RaiV1H#pu)pJOmDJ*#T9,-,"e+Wh!5E-S\+e$="K3ibMD5@L90[#+:E2MLDgF>%l
ErNl1MVK6m@ZiTO1-LJl"&c!AZSrg&#YB"UsM-qdhJRLkRM43&QUIr,WUEoG2IM&2c-19g0E
$DO#RG/]A58Ci[*P\X/Q'nX]A\9U&A&Gc9[I7(:!d$:5>Pom14u0KWr(p.rWkl!GK2HO9XOqLu
"`)ZqCRES.3^IH?5Vag*OnV"12t[ACG\!/,SkFQJE`+F"dKll:5tb4dN$&Y>E'Y>C$Q9l8ol
A4aaH7$V8\H;dW>rnSSEYeAbF>5*ZJ1:*(lbI.?Gl"b4n+>/^M!U5?2::RoXi6%!%(L12"ng
l0>;XI)0(Je:aF)9tum/Or`*!`ed?$WBL&%P@QL<L`6]AlF(t4;/0ImG=Dg5Su5p#fq5b4(s+
`OA9s7'9Ljl&G_GA&NP@l\^="J);dO9^:[UfPQ&(_jLFWT4.P^f;oj\/SDP<<_CX?jFZ3n6*
f7n_<[mB`Q/1l;B(LX^-'X!N9]AuY/\/P?W2+O(()rl#L:7dTO#FJg+0qmc1KS*C(A4M+#7K\
C3VC9f"]A[SgO-bYl&Q*Z?arF?t`)N3N1//can6[>0j7<!h']Aa[N@6-SGVd&HcYmX5kj2GBP&
?*=\HZl^Ubd]Ag><`QGn,l1uZ+D>U`o0(RC:a2f64q+DLmq`C@u0dkqp_?Kn'Qj=Hr=+1:Y;@
U3!)BY1So\1>9`it-4f$\t/"Suc<hN-+NfWq[@l(T0\Pi]AoKJ_,&EAMWHAI<0F=Wd!/oF$as
m._X\JEeRr5:ZU_TAF(0@L=/hNJ[b:dn!AS@4@Yi0KpbLT9l-[=a(J&<i`)/S!5h5,rKgT'\
!mVFnoZ-atD?5I.T*_o$Ln%^QT7]AVW7b<]A0?.1);SV8@RLI$]A@o,G=W'KWN'G9gK!j[t;I\^
q/TJ8@8!AguN:OK\[`E(<RTL0Wtj1kE_0'&?"@-taK5<[/,P10`2L0Wl*do2\M?[EU9k>@3:
[J\JNd"<g%2*CKCJZga?.mDhF2U0a4V8EUu+[W&Tg]A#@DW@6<=njf9r7L'8pgdg;]A7.aVX_B
UG6r64=C:Z=mW9A&4PS.,Ytkn^Xop,B8r,Z/WrS_[Xsc,#J>pf\'Rka)M2K/J?i8JJsrXb#^
+..\hSlgX%Laic`9H1o&j6JN**&1Pt2tb<ci^Z^P1p;X/sO%7@=ne@OLNE.^P(/Kq&o:5KXt
/a:6$n7m-u/eEeIQpj4:+T"m!XJiu$!MJ3&lo^q&)h&T,%g:TfR`0W#;-r$o+c=%fA"&Ynei
C7RE#rsO^C!Zu!a9SDi\`UEU##[u>9M&mS3A-+Sta;:c^h-aA8NWnq07;^<oJ9)[UcQ:#*h:
DInp?bhb5$6ce+(2=OOQfF[R%:>0DTX<BZh[<&#K2Ohmo".rCFH]A4,4g@\oiUW[UG0E(cfo]A
B)2S\/ug5B>uL_$[n:o$o[OHMK1t`+a`a1>G<k>h.Bp>e;G-&!*k^fUReT&:lc0gf.':kDEZ
BeZ`gh59iI?$J/Gf^JA.*H7R"]At>/V+b)NG&AjPA%&Z?W"J&_i$\jZYa(ksOF9MMmdPf]Ap/r
WN)tucqk1Q.G(YLCXQ:O$+$DJ9jAd<?Qb4CXPW7sPVsr"JR)S+KY;i+;dSr%oOW1(S_Pa`%4
6c?S:3M7OWhV@Es,D,#fmr'_=rAs&/I@?koW&+eqCTBK4pTUj[pV,*\Q=.JHFjU!Jue[WSUc
PeSViA2#QtuQ(q9%*"J7u!fi%KNAi9;;DW*JS9cNfkfIQ7ScLN:XJ&LkKBLDOO66MuVNSJGq
:ggq+O?(A."7PCY:,%CKiXHHOjk2R"0+0B%kQ:9O,8u'k'!O=fOM@fIMHEN7]A=12/>t)a_Y8
cF;X;9oP7sSM*mPl77#[XtFM7B5"DaBI2H19&5!<p-C\Xn9;d=Da7seNH+YW2P2]AL:=9OX7m
gF>Y+Ms.(0@PtElJCp8[_mL'@=RQjXA1LtW`"n1%6X)5=QGAMt30,:R)D#,M((4M:1EW,j53
pc9ioW3`khP[lQEt1,X9-K2cJ^KIAEJVCkKsaZq3,uo\bE,s)O]A:k0Vf)0*d?Obm$62sSBT`
@(#=MiLl<hB/upYa7D&K/XoGV?\'gKj%K(]A'oZr4FQ\s!4Mk@QCQi=+$=(,CI[.M+Wn>m'/A
4_%BYHV('QbF!DnpKB/H9g\HeSB`8hK2m24IF`KaD$h:-j;'oq46E:*7fgoPt8cc%(5J5#sl
2A"[QMhH3r=t?t.V\FX">M*uf>q(-?Qj/k7BnA.uCGIkRB**-R1$`P"-C-U0/_(@J6]A_4Zj.
'#a?>BNu%Bot-!?^(6#-N%:jj9Ag=^[F.a)8tIb10->7P&#b<0/Z,+)RTK*Yc:uOH89A5/VD
28PQNZn0i9qupB?(6IMha?*0W9(2Al;LLF_Z0p*#G.R/EFi&!Cni)dHr*Qi)(/)e'0k_gdNY
:L2iTe*GL7RZ_4FSGnjcVNe+p&OSORn5=_7.$.CG4ar'F`Tqnte1)Z^4p`t-%\)/Q7_7$fHg
'J$1LW_:TB%l2ZOo&5iL81LgMFECJk0BFXC>6@Rc_W#RA&Gh4!AgP-iaA#T:PMr;WhE\9P:'
>+3TmF*_01O6%[5,u;7PXT"jki%EuE`Q<;2q&s'VS#`K?K9CuI4]AoU8S8?F6e:&1%#?gLH2C
;>4B]A1=:30c':"VHgW>JHa^M\J6bp=Lp?\R\+!K>J642+_V!Ztj@e%'T/`ZX@d-rTWb=9W*+
P]A(q*hs4,fos8kn(GD1OWu7mf'l2bl.-?qUnEkN=tq.*ZkVFPO`F`?q.%k6CQqe-EGL<+]As%
np7C?m@'.Q.Hon51DB+9[oI\Dc5,+rpAmRKl@[BsI2JnXcO6*9(XmDef$sk)<9EYtXnm/CF-
tZCn]A)-NaA?2[Sf$_up:Cm4ZdXGsGZ4!pSXjYQY1,h?2?[s2-c;;l3QIH[^T4I>^T]A4WIf"Y
N@/<\ZjbI5:YOMKpiHgo`;F$iqG]AF*7M?7`'%hT[$mb;q"a0aeAV[NY,sY08CJ7>$:5q_,Bb
Y_paIbHV2ITAh%Fd0'bR$B?mAK61VI^,#e]A@Vi$)M2#0^@[*J]AE6)j:$%t:6;#9fj?qOhR`&
2P9OQH]AqI'phfGD[l(i+*jQE`X(Ge:,&eC'H,tUB3jCatl2FF$=G_=aQidb]A8!WZR9Ioe+s&
^Gd=G1?"YX>NRF4cjb3KsLNIY,QNr:<6/af7*8"B`=6fC\#[VfSV6,rWc&I=i<,]A>FkQ2oNV
_i.uOC*A&%=e,Z_En*=n*jjm/Y@gqO_(t]AEuaW03ATN*ZiN1sR=/S/Pt-a)`V`Q62?rG^E\a
T*fibC.[`Kp\899#i<+!)BYp]AfHJ]AE!VH(3'6N=W7q<`Jfh36UPU!0]Ap9qOt_KUYbB)m2)rM
>l)Hu]AT%Tuj%b;gbu&@AK?)ba]ABGKNY/u&?>Y\Q=F$$uQDdqaV?XDJ^$GWJe^>kFW6uCCo)q
F]A\'F=LQZ=q[9lAa1AcbAToB1[>/7D^jsS.@r@=r>sN:->]AMm3>k/>P'C?q8hGGm]AX0Fk1Z<
/RpW3pQZdce[a4SkNDRWS7sUiLDX_Kf]A.$@k81rH/@<F!3-u)M&ep0rQk41pmN>L@^$DR\^V
ROIU%cn[$)NCLGm>/sG]AfkejV;s#h7E[<bSI]AjRZ;2Fs-me!qldB9DcbQn&D^Y6Pj?FM+X"k
shV1MmeGBFhMm<N,MbS?7&o$C(Nd\=(OakhY/lP-0BeZV!M=fEQP/4@4elp6\t=eTg<X7%!l
F#:9-as?W*:-B"=RaYlu`DkNebTg>Y;GW]A5-O8@ie8oBX&`,OC$Gg6b'IHc.*3H5N>4-I#OD
Ss%oN9e^MbYFdAOJm$5%M4j%IVG_F0]ARQ\d'DA2.SoEiAQ,1l?L%BG,]A=0TC/h`U+s]AO)jdr
om#s$TD9a5/%N#M=`Ugu0XqDM6C;?Mq`dm$sgf.Gaq/3ddCY!Y!I>hjI1Q@gbpu:YC?jHG/N
qe9#1ALUPDl1A3[`J7flMQ1+&>FON6Nra=G4=0afeEs,-"EVIP4qI5j`kl:PC6B7-eGarKkV
#6k_D,Eb'\8Fln1D2?;%M:gY4+("R8Bl1L@TR10.NX%:t;s>Q3`Hd.#]AZZ7HC(hU_4$OLj)h
'`5clVP,]AZa@p70`TU_teVs>?=N4N!)SSt+>a9[`Q_gqPa$:!*$e.<6$ib[WK!C$Jet(rd-[
=U'7=0MCnliNl-`18+HglFu.G\h(mI92Khqf1U__,29IiM)Zj;+Zc:H#.LaSJb+AcH:YVB3;
qL+-FZ[QeWuLB9P05.V^LH,:!oZl8u(9^du_\d#!n]AR$N3>YubjQ!=9+gQac))Tqrj9b:TpP
D7/]A$?-Y$ZLc7IITVL)R@8'8SAfq"ZT7LN#A3b;k!K@=`T?WNnRAi1D?'"'(CS!+6[rGO1*l
^[qSHe4%qe!1rcZ_O#b:/6ImE,:3t??+AGl;AOa$FK!VX/Hs7mPi;7s@W8!6liZ%!.[75M*H
rfV6/hdIPf)).oC9=qGu>:AtW811Ve-XH;jA(CN@<Npu!nio733Cf>,'kNNKF)8/WZnG%\[e
4i9IoB5%aUb0EUd!g[=Gs?!KGj6+a=;I<.&4LY8CI/207pImb+^g*A6EI)=X@iK,4:F8f,?f
;_PtIG8&arLSaOL71gMJu>tZ3frUFP`KhEQs=>OgR:9(E@q#=jfAj^mRGE2SD+hC.7fWj/ZZ
e8cFrPc%lD,S[:^-6"eZ>I,HDf_+?2HB_6kuCrA"XW)e?PM+Y%K4$2'o0Fd\L.7%EO/_A--C
X0-:A,/n\)-P3VcZo77ET%#C@Ni-hZlMU#pG5]AKp76@Xio^::'b>Vd/0ZiE?Xu<ZGS.q1B*r
]A%\^;I>KM,\0Bslee7hEKZ5)Qc!-Udh`"d6mJdoC\;0-`?)hhpJ_l_Djk"gMD9]A7-"k<83CD
k@O:LbQ`lepc/o,ln[).%*'esXK>#.n)nA"C(6eR@]At'JRB#rWZ@O)^:3AO;dMM>HKF\K!6k
Qimr:@eQAXTZ1c:H-"l9['nf&pWqI",]AR#W]AWW$"$T)g)(YK>j6HJdoQSb7JnXIV0-V<Y8U]A
)osoOsN=blZ7mJI-\d0=%;VoB*MRmeu8(N2B@<`OI8Y8i85i+aeC'>E?1Y??$Q%&a-#7-T0s
^lW3i,dP$!@Y_Q=>*Xe#Tf_6[mso7()Gi(\Bm7OP5m`aT@j$PB!gs0\IY[s)l!m>\=1b_&-`
gPt`q$!U88TWElc<rUdpB`W>Hjm?<4mNa9%ZQJHo'7O;cerf9\KYIErjsgtBm@)GgJfP2#`#
K+eHbo8ll"3/P<I+s1n+^%=CSVA#!Hd0lY-`\[F<3M\o'I6Bm;FJ)r_P("oI'=:\3&_Fp^t/
C@;Sm$V3R'2rmb$R3hap$bK=Lt<T9BUC1u@P/ZXGR4S.L3&%h(@NG$]AGKeiSX<jQfjUEu2hp
.5+"OL5)0;^B=.DT9k2*N-fS3F?ja9gsIBAX@=34nt(QAHNcA-Jhkf\QW``T,[6&2TSibb&(
=?D=^\r^A`#Elb^N.4)^p=>q7.-b46KClhgM)>cp1RS&!TO)=1GpW3!HmHq*bC1G2pP8Xend
N-,l\*dlA_%tl\[q6f:lY)f&m"Hl=#He'a6ZP"0H=$M1jgZYeWpWK#h$3b&(P^WknOAu[[+"
:Xr?"3bVc<sM"kif2C,M"#6(S5EFOWc93RroA^p>3Mui.mhJB!>P*^/a\hiu@C6]A"l>92"4Z
\?UWSZ<kG3t7FpljD9"L4*mUB\EIKA"Re*/8/s#[$XYuei5!icr(,d)6$^#^'<[g,#_7o?#/
HguiLC((=&h=J$:RZT)XM:o,A$7NP==;&P&Pb_$rTFjEgHiek+aE>7/ViQa>8KT,5@[&:K7&
LRc2Rh~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="171" height="116"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="115" width="171" height="116"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0_c_c_c_c_c"/>
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
<WidgetName name="report0_c_c_c_c_c"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-14340777" borderRadius="8" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-14340777"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-14340777"/>
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
<![CDATA[304800,1943100,0,1524000,1529280,1104900,342900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[419100,1872000,495300,2592000,2286000,495300,2095500,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" cs="3" s="0">
<O>
<![CDATA[ 生产库存]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" cs="3" s="1">
<O t="I">
<![CDATA[867]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="月份"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="生产库存"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" cs="6" s="2">
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
<![CDATA[主营业务收入]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="2"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-15591374"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<OColor colvalue="-9864992"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=1000"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
</XAxisList>
<YAxisList>
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
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="6" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[流动资产]]></O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=D3]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B3]]></Attributes>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[ 本月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" cs="2" s="4">
<O t="I">
<![CDATA[684]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="5">
<O>
<![CDATA[本年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" cs="2" s="6">
<O t="I">
<![CDATA[9345]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="3">
<O>
<![CDATA[ 环比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="8">
<O>
<![CDATA[ 14%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="5">
<O>
<![CDATA[同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="10">
<O>
<![CDATA[ 0.4%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6">
<PrivilegeControl/>
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
<C c="3" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6">
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="192" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="240" foreground="-15680447"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n)H]Abf.C&,[50K*sY]A7Sh?$H'm#NO%P[lT:N^M0d&VOMX/dc4Is
Us?p1/m1(;BhK)`-U4@R'F5@>2?ac\>qFeg.6i)T;9d#Q+2Y?)Y+h0PNSr:isVIl*F+.U+qF
dUjYG-:6>&2]Am8)X_GnqDqgtnJVi+]Ajr[=r]A>f*p9CsgB/%La6t3]AMKrrbsDs(K!0ZEIm5EK
.V`FN2NZAQ[&.RNahL"olF:!PgBO5~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-38294"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!Rq*"s+sQI7h#eD$31&+%7s)Y;?-[s%KHJ/$4$h9!!%]A*DoVhX!1QPJ5u`*!A$CA<Djg"1H2
gIQ5WoMcn)k)24duD,=A9pG+?^,U@/jc2U[=iPrs7@9ruXc`"#@*-1p;\=D=`&jd"1B[D<sN
#>f,O>9-?)/)SrjGT!)r(E8D*69gCFp**,c>-SHmfZu&)`?;2R3s8RR<TFLd[Ctf1-,H)2jl
dL-bFq<cN#@emG-quC's(F\D#ih'Q!2;C.?ZD/)eGoRL!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-12721762"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buf6RP\^.<ogUE)G$36[1ngk1,tjC8.>8UC6PRiX1!"N+QEep_,bc\=@lR7=(lZTGBg`m]A`f
`46fXJ-Y)rAMYhqmtj4eL'hGL$+Fc[FX$F5>IH]Arphu\KXe>eDo8eAHN>Fj3g/8`#&\g6c*5
HK\*Q:SpVE(i=:K38a@JG53ncV\;SJ??tN_"<5F6bK-X7#=KK':FU=:Z=;E?Pb@T(IC.k/CA
*o>4mi+6)Qe[`?@CRWC>/[=,=i'`s4T+1\Gh+755D;),<sa(D7(+>YTlJ?5Um%0gC\Q=lHq@
>-<UhQhFCrap9[!Z>*qp[)S8ESd0NM@\1eDKPl?N7klu:iQ:W<ji/cOhB5%2DaZ0juNU&F79
VM+5^YdN;&AA9=kn5=m4C?LdN$d&o^JAse_i1am29fRR98G8Dd&H6iJ$i"CB:q[Y"L)aD*)T
K1ank_ZH.NSrFn6_0!6neh'L/Mr#Dk5TU0/"u.HsN=[itF;Re'Ksf`!f\eC?R('?!0-sIk'D
nD1@^'JB:q%`oHc&n'bI*4Pa[PcHTQ$;NY&jJ#2\K?VU1QV%b"<UuIb(?ITpppTg[eVA+e<N
eU#pa\P*I^8TXAoi(.!`dL13T!<Jl76;_h*=!$>":c=dM)k'*er,E`e.H?2+Lig$WP^i"'eF
l]A%[Cg/'<t!j(lh7Yl2+WH;>ZMj#7+4hP8da\Aq9&u2c`2$H;E8n0IDZ!<Vr!g1^V3I`gc*N
dH981&=TfKF=Z`0g\4,m%jDHX7f+!&S23@6Rs<+RQb_MRJ`d+/=K6,(VhF0Fa<.<n<Bd6]AVZ
L..2;!Og$@;gb(,0D"ij-Um(ALTq3o"4"&c(]AT;*2sji$`r?-PERGmmK._9'`<g461WcGA%*
FpE*Wlma?:6)?*\-f%D"#`r?$-6=Tg]A&if=Y.Z"QtAULFfoU\s^%(\e@aWC@1h3Q4?I?0"#0
f!hQH6G3G691S%=bt!Q4/e_>DJcF"nhomdc_;+so><\>_3!`7Sfh+EW'0=;@4lZkT)0L<D;_
;@s0>5d1UM,br6WLuS*P+[PHa=fOH[iLad/Z,""dStVVoIhg89LORr)<_FtW)RnoL\n:pU_B
PBG38*Gsk\T?4J0aZ#Xf!1E2H5ZPmjRL0Mo5rHF$n]AGRQ)V2OnYf+6`k&B_JNuW0GY9akB\"
^pQcl443$5nXIWG),[]A<'HhA86VT8BUUW`&3[@WmTM;n75T[MW(qC*61$Z:rY?5V"LcSs&]A_
1e2Q58J2<$Y[t/+Q'$2ge=J2"qKp8?L8W,NegFJ)XG'DRVUALSY.riR"D1R8^npIC*A$#\2]A
RqG:>BSuQBck,j/Irld!FV9N1P;G<ens0'X\l]AedF$?pI>&7'i@0KU@u-rSf`;h4%)t7"iHS
j,X@i\.m!p/"S/^<n*)L;1*WB)",6DB7i85ts"'@^B`sNDng^%g$khT!'c^jq'7=DH:c$c+u
MC_,?.M8+n6HO9<TsC91>W@cV6Ie-G/_R@W-+*LQIGeNrRdT`OJ9;uKQ#[,:=`/U+3mrNLi)
Aj=RDg>,.Bc;T<1Wu%cdjnNFWf/L".Y1V0Eu)ubiKoDAnB^i-,KnBm==8!WJm#I7Ui!]Ab>oX
6k4]A!GIS)D]A_.7%;C7V8%*M`X/X?(rXr`QBG0,r*X1HV6@8!pR8["X`mHUcd#_89<Ji`!otO
,/fAiQR3i-A*\`$(K:Od6ej96q+Nts1o)BF.X*rW)%QBMVisp[aYNP2Si8h\u##3!ECiU-kT
(\.)kgH+=O)Kp.g,3'+/o':S2A5jO[fKkdr[lg#gi0=kB(K7pmYRAb7:@3sfCW563s4U_9hS
f/37br+#o.YepC'&H3?4piHPM(_(rSR!)b(iD&FCmJ+N]A92;^4Y%[92XA6\,Y!]ADkAO$WRh%
UC_Jj!<GZ>!_nOg*Hm'R!SGRk^S%;nT;JNpM'T\]A%X`Bf*mgbj4S&7pB9"RQ,J>+g13LIB0<
$qc)n#;:mN6DR.3_^>7;I=#?7<$9lt.lScuN)G\Xo67Hls?uc4,46eM(ZmC`poYm8#T%*RX3
3q&Ohg:HQ_c'1ofA%pWFgQ[k5%^@d1f+T?(A(jir.<6BNf`\[LM#1PVH9pU*MklOXJb7*6Lq
5\X<B6#b\r&'no2$^>?YgaN]AA"Dm8YUg;/a%:oq^-3K9jhcDc>l'2dI\4_l;R$q64]A]Ama2%M
[MQtdYDK@Nomp_U`U?^04j%(SXiuMQQ]A9[7%55m;d8SA3+KX*.q2,qih*5"`!deP"$Xs+O?o
LsHDP6tcc0!BJ6mt-iQM*p,?ncHtU<=s1iaJ`@OUR?jo"j>39VLeECk'n8k0khlC(;8.>qMg
J/KQP$og#c8d\Z$i^21QeF_rCOi:4>%Yc-07dARKk-ZFOG0JR+5]AUt5YgM5n,WLSJp"llBQZ
KjLr7J..U`9[*I^5g[-BaYpjdrC_d4La1(jp=+Xr=q1$\gD4!4E[rSbUXAN?]AmUI8k^cVkSr
qCikA/pq#'4DHb:k4RGTo+fFYQSA10t7nc[AT`,0ldf86t*%]AT4)$pIb#>q&_(,0LPgrk@6q
L4PC93FG>t`W]A1&##^BQL1iI5fpL`kr`!lRF$Q,".-riCd8;hWd5O!.qLgo20!:@hm)!anCn
8t\N?3rL?sZ7.F$6Ub0+j2LY/&?FeMNFU)%"H(qWkcZZV)JXf/EYMN@@\2Q",TTVO<TG)=;Y
>6.$.VAJVKUc4a%D+#+,ET6l9<`MM>ma-%7q[`p*NW#+9F3TEkb!qi3d+Oau)_jLko.52Z!0
2,ihKT+SX^DX3?%ob!L_)[hg\/&buEP`.$Qcp=hFgp9';/WR$kNDW/6;3P_%COt[k/C$+0gO
!,N/(T>]AIdrH<c?)-?SK,3X2@sc9He:$IS$*AF1Hqk>2RF+j($Vcc<Z1R67pe*J<)=_5aH5T
Gc=hacN[mo/Bb%6dDafGJmaaVE)Duca7fWs9u2#Hk\TRJZ/-ArdPa_Z*2kA.VIC+-^T$:2I^
H>?,k6$?hQS@P$2=%Z`#r_lCmeC;3@_D4S'I$p3lZU0p(g_lX3%N=/5B3E3q$$qTa*s)Ydj?
*E"h3!h<\*3o;p@"1e3s<B8pl"_PZ$iK]AHfG0Jo<LPB2SE`TG)JTn3Hp@-S2NdRVB..-P\P[
gu+GS3a1ZfM2pA.#=i1bY)@[`;j=@1kZlIiO,%Q;c#oTL_'gQ9V&9egH:m'ZZUJAS`Zghm.$
,^W_Q0n0'%c%jUP<JGSbWPCoS8?h@#M(M*r23#>X)!kZ\uu,1S*lQVT'#'^(uI0Q:kjd3D80
Okqd'.8[LV3de6e9?#j?_d#&'CtRka)LU(Pq`m_:1P?3cU>dp(3>mgLSE4tdg%/%[3_d^Y$Q
=OGQ"cT+/4=um')%>04;qN?//[CNL<lZkgB]A9Y[UB]Ac+mb^iTC;$[-QEDdLB/"GF<!P.lC^&
r&6au0XFQ=1mQI]AQVt1j87pf6_,;Mdab_p7$c<(sDH'-'RTTn<ho=<G87+ocS6n2D6Xh0Jm0
H9YP>`[N,bjA8dC>DEHb/]A@n-!LJZ?#l"h$nXt;Mu/mAG6(o_6;!_<H*;+S;WkcXP4(dg+!Z
k>'bHOOc7Gg>.]ANlGGJdAX?5+*S,ZD]AiEcD;Q"i[p0Y-Ba39Qfa%CJCXR5gG&n=rL<P;RVEm
p?56\4US>UAr.a#[g[6dTU[D:@VS73":jq[>0u>9cATd%1B.fC6rM$WX%OpR?-*:8-R.s2UF
rLM)`K`!o"I3KdILJi7JD(K%ZhT,I12^Km=)\6P;hkLh2e]ABYZIFZ51:[Qj#Vot$]A=<KNcct
3_bIE(]Aa%^Ta-!LFs,TmRMLMtmO$jKAPa29;_/oQUL5]As<bJt6.BP!t-3_=3Pj#i+SSGp4;I
AEI&&Ctur%FeqoHUhu<0fbVl+#WrF\M_%+lK`M)>/4!Pi2V*)&-Sn8quVk)^L;PD&G':f`Z/
Y45lW%]ACVIKZJ8qN!SdA2Yf]AY[?e7Fanpf;Qjfp)($IL/3-r6\#EnDls1h=%sZ93nh=R(=iW
OgY8]A'(N?2_cjk:]A5YVgRi%&DcD)'Y4%WnP]A-j?Go,==<KH,*]A#\ESZHES[p=+`QYapj,<B9
HQ2mlSL2fhi-'A!e@_!Y!P4P]A,[jU'qe5KMMSVV6aIFH8kc5#,VXuj*sY!R<l0>O^)8nMS.-
#SCr?VP)+5'S"i_N"`Gf$jr_PqPkSEN(O_:R*^[_Tm`DUhjCTYc,05s&ZO!jhf@P>6?h+=r3
XU;U'Sqgma;#ZNq^IB0bVW<05LFOCo,Tf20_;0\6R[(<o4Gn1ZWV"eSF`3ia2<fl$:K6X;>$
bI)N%kJ7dJR;(h*O+)/tF_K-Bim6=8h2>j.TcBD4qV_G/d%fs>!sOVeaYHo`aHHRQq/S>mr,
N7W0\$7sJTT@sV6NMjgUnr.=`0=8.kGF=h_acNjh_Rk)cn=?#`i@g%e1%VOaS2>@?27,lLUM
uErECOOY?QZ$T1dI'f1k-iZU#fg,[*$T/d:u2,YXcS+C.k<o9%*WM^X9Q.9iu^gWFL(Rlc7U
W-^?ig"6sN/)t*/!9a3dp\:8?KjG?[?HVeJi'JG!:ZMnrl5s&KNa\Z+(6?TtF"@GIa@7gT^I
p2``q^J4.rfVP?4M&X44<\RCP-6IF@B*aj'O'8d!1eMf/@RS(@Vu(Z+UuA8SVEjBLX[<2g-D
EIFk^^_:KnCN@IG@H+ggS;b0SLsgejUA;&_Kg/SAZ-O5;:?+M*t$Y;<!\4r<e&'mOe8g:V:\
6ks(`NZ7tCVk'b,BsTf;c!EF:09p&S]AQ!GL7Z0pI=jqT=J@G<d8d\Fi-p=l5eSG#(qafC_La
_>\T=DnbMYiqT\)uKAorZ\5f6]AAJH#8JP&Qr$W@C1ZCX6?GmRnO*fjjL$OaYaNfb2+h6@X@A
Q<nX6TW9l?"dAndXYCO^cj#jNZ(`h>)1#@!Op_/38"UWH4$.g"3,-KKH)BrWbn1e<=D.E=*m
$&u..mV-JY<n=WHkTkoEK#sbQGXrmHLS;/Ssk^LaGk`K+^W,K35TdRo\@6<^[Dm1.m%Kq"Qi
=hM<C=S+3i[Nr;,!g),ZWPa1i%\YZ/M("f@?!U9C1Fn\iY95Qr1KMI,L9i5:V@_89kJP8%cp
0q.d&^B'lqHq(HHi&XFr310fSg;YD^NV[nm-P1R'"KjJF"NN9,NDVFtA'ZYtLDsuo?5lXi6V
fomUuh*>"K9@HR-OI(k"n^;&"A@$^2ptl6TNTm'`EP7Dq'Tt#d\rt`DLgaCWj9BqJgZ??COQ
t1rkFSPeKXI+l.1g]AAcQ*JR/2I6J`r&_3n]A-1`^Pd4GmY7VDeGtHpXTTQ%\:)K[KS^WE;IUJ
A2]A+gY$4JPV^6Gf$m<R2eYMhjf-W$\>ZAomCCaPKgR*0_i?\<.kE!GJWWS1p*se)nK5/%A,A
/o_-W&a>KOA2(e:nfQ(L+(>s5a]A,l^,NkuEM:8,Wu8#^\1VrMj3VcNMYbAaq6!)C<7,/Ih9[
)\.l[9MMbnLiLq@8foel\nWg'hT:N#-1&p]A$88_-3iP:TfVZ#k/,.VFn_q#VC'C_+eYYZpl$
d+]A4gOe,6\/d'SX\m"]AbA^P>k)_r&b.+uId]A"`RY63<ON,-Cp"Z6W;e93/^Ys0oOB'ogGpUW
&K/Tt0l=iEX3YKbdc,[8cO0(u\oB2M<KmMg*HL_@qB#68=nqc%m4mNt"R2&J_!_irrl31n41
P+uW9`UBqW'-4'kNf!Cg@'(?KmJP$>YbgQ^tM(rG.m]AuQX!R1)+'qAeIs9YTF#m;b:eXW#%t
c1nfip=AmAAdOK/_MYV)HKfBSX,Tr1t;98,K`V7fme.)\ZajTHOTX"QuE[<'$f&]Ag/(;X4$*
`9`!@r6B-BNubNaF/ZZ-Pg#"@'&f52nAVrXp>CGKKLnpE/fmRr'NnTsdB&MXo)Qa0Akj@F_8
Rl28+l8m4-^OLg(W17>+C_[k\=;TWcp#?<+KuD-I-8b@j&gQaH2Iqc]AX6MaJDJN7X4M"Dj)%
:W5<DifA@o+]AG(VGnY[*M$'1k5TM:47-bO00916I0bR_:AM[$qDN<,'TJK7^$rK6R;q6->hZ
5W&\Q^o%X`;T>RW5Y`srI0jt-L4,7*-DMH?N)Ss+b9a=,GXk[5PE<(Z1>7eX8WWb8jA=k4Zj
VtX<\e9`/YtSrj+sCqm54:om<%el.(,QH1N2BcG)l]Ai[/A)=;6`*q\Gt.es%8X?/BPM"uM_Y
'FgCUMc<h7DKG":IL,an#QEL@XmYd-p)Wr^EH6pth)EN7mFi@^7-miaiU%.3]AFqQ,/mpHQ`>
BhlVBe,HV_#MkV[t>lGc&H4(OXjXmC3j'>S[H4.K0!"EE!BU+f-GKof2TuL<Dm9gAh*cn]A5=
iI!3QD_M)6l^or5+Yqg"*YNEgBW<g<Td-`H<qHA2K-Fef^Xk_2$MU3G7SoIi)X6t'#nmBRec
@<^QBhCDEV9#O/0#7T;qp;?[@6PEj72NU[0&^i.5C!9e39\ZG4drQlr\FC\,70!-_hMB:Y!F
.[32Ir!kaMY4KOpZ%2Ip/gX@QGnc,QD\7]A"RSmN1`S+g(+mjf<nM[=Cog8s,t#MiPA*77:NA
9kruDKWhWfrlM3DQ&CIc8/JFDgV*$L=jV=<HZ#)u2<grVKs0\?^]A&%d/>[>eqtoV\rF"o;@1
pud@gCC/Sf:]A`X\&tW(8]AV87r%n<?GHr[NSaQ(X%)^T]A9D$E#[>#+GdWS(ZW4Ue'>1nAe!?5
b[[kK\TWZ1J1&'F/#"pTBFb`<<Fab*KiIp(`Hc9S<C>W@@6M-h7T'GC_pgoXK!gJeD"42OBY
'A3d.k>Vm&?,h8<VIpmD-M[UoshV+AgMAFAT-]A99q$pt7D>Bp!W9I(eR6^;p/OhF!6tGI$F<
1cQrWqD"Xj5e<fC+M;^hO)'^8UtNDZg\A,k;R]A4C60.9t]A/F#3)FLLZWgh`b(keLFjkgTA;0
,Z]A*m#SD`Y_,WF-0S%o<jSZW6ZBedU?1[;2I77M7eKJ,)/mD<7#lSoo<^_`&'8X$2=M$n45J
p>soq..C6059rABuD_L-'-pc0lL;:3_WIY[7&G-oo62FRUAqYZ29*@81fiF'du6rE[_'FG$f
?V0Ndqn.&]A8SXg%Pmb[U)O\*TDgV*bKaT1gscs%Zg#7+dN"`789k<;[[g/>Rg+X%df\:,42.
iHur@g.$p;*4f4Ni]Agkd^aScp6#f*'c3efqNFX(77:(aa^l[M.=c0qOt(itYOnS_MB_[F3/8
H%8*OX5^p/70H*Y1"a+7[lFPc+/khATQea3(&'7G-MWUqUH94Hn!TJ9TcA57hdIF9aRXn82e
*2-G?_Q">_ZVl"PPn_[R2t</2(L[TU\@Hl"fCa4Pl<bi"A9RKrWgg0Vq&'fgrNjtcX%S5u%u
5DDL%8;[P"q>@1m/^h+KW;,nLLptqEdE@+o>M+i(d.qp(\RR52Vlc"27EuP7lnL5ClhVRLhs
9^T>S<^,q6![hSQ`Zo&1:Q"^U)/TSYDe,6MZ5.+YN,A^MZ8T.)GlNsTplMDFFUb^B`^!hjhG
E*>'Hj@2Q=/U4+T-TS:q*-A1c!N@V0>Ek(24N]A6'K0Mnr!daO-+.VGoi)<N,lZ&*lra>K2=f
F>GZf>FU46p8kNQ_e?,>O+o81(\Yo=/sAMr<>I0oO[Zq`U!r=0/+G<+BXJWNC#3X9-2rq`h&
78T9ejjJO;Koo`#/V)$*NPS7?o&f<=W=fVh0AcK?]AZNdWQ1jsL1:V4g(9lp3g0r#J0g#:D'#
9BR?LqHt#VeIbRL!F%oQ<qNaZaNfm=,*~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="171" y="0" width="171" height="115"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="171" y="231" width="171" height="115"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0_c_c_c_c"/>
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
<WidgetName name="report0_c_c_c_c"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-14340777" borderRadius="8" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-14340777"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-14340777"/>
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
<![CDATA[304800,2057400,0,1524000,1638300,1104900,342900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[419100,1872000,495300,2592000,2286000,495300,2095500,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" cs="3" s="0">
<O>
<![CDATA[ 销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" cs="3" s="1">
<O t="I">
<![CDATA[746]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="月份"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="销量"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" cs="6" s="2">
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
<![CDATA[主营业务收入]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="2"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-15591374"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<OColor colvalue="-9864992"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=1000"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
</XAxisList>
<YAxisList>
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
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="6" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[流动资产]]></O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=D3]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B3]]></Attributes>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[ 本月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" cs="2" s="4">
<O t="I">
<![CDATA[684]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="5">
<O>
<![CDATA[本年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" cs="2" s="6">
<O t="I">
<![CDATA[7680]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="3">
<O>
<![CDATA[ 环比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="8">
<O>
<![CDATA[ 14%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="5">
<O>
<![CDATA[同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="10">
<O>
<![CDATA[ 0.4%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6">
<PrivilegeControl/>
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
<C c="3" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6">
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="192" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="240" foreground="-15680447"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n)H]Abf.C&,[50K*sY]A7Sh?$H'm#NO%P[lT:N^M0d&VOMX/dc4Is
Us?p1/m1(;BhK)`-U4@R'F5@>2?ac\>qFeg.6i)T;9d#Q+2Y?)Y+h0PNSr:isVIl*F+.U+qF
dUjYG-:6>&2]Am8)X_GnqDqgtnJVi+]Ajr[=r]A>f*p9CsgB/%La6t3]AMKrrbsDs(K!0ZEIm5EK
.V`FN2NZAQ[&.RNahL"olF:!PgBO5~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-38294"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!Rq*"s+sQI7h#eD$31&+%7s)Y;?-[s%KHJ/$4$h9!!%]A*DoVhX!1QPJ5u`*!A$CA<Djg"1H2
gIQ5WoMcn)k)24duD,=A9pG+?^,U@/jc2U[=iPrs7@9ruXc`"#@*-1p;\=D=`&jd"1B[D<sN
#>f,O>9-?)/)SrjGT!)r(E8D*69gCFp**,c>-SHmfZu&)`?;2R3s8RR<TFLd[Ctf1-,H)2jl
dL-bFq<cN#@emG-quC's(F\D#ih'Q!2;C.?ZD/)eGoRL!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-12721762"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buo<SeEck$;OS/#/1!s^<?\.p)VZr4&^hlCg(gJ%P!'8>)-p=fX^kf,,[)$"@p^S=,%9V4Z4
1<,/7UABE#HmgUMlIUms^VkrV_2Kqt6<=I%KL]AcZu.(hWI#I7%LP=h&s(!#QfQn3-Or88I/j
l-3UVX"=a[L64H]A&KKs]A\*]AmhU3]AM7Abqmtr_lf;+45KU7[0"M)5*IC:EaQ*8I0[@AlgdhSc
7aX'O+'CO^"V_bk&oI[i941/&JmoTaJ0[ePCGU>F)C)si=E-KS5)8prIZij_^_2I0\``p<R1
c`bN0I\'rK=%LJpB@KMH]Aq;oe(u_o)<k'.U!2BqG7MdTRm#3RalET*qc:'B*s.3EZSKq_X0?
p<-0;^7C\I@2nteL@PMOmOeULE:#FT-tsA<mN=_-+KF;cL5Fk5JX<+@U>q@V]A&MJ+_E-]AK"'
`IZ%F@r%+Tlibq>W00h@@d:Y18ps@1mHE0chAW/N_9>qd:?_WVXp!Fn-VIa1'pm:*"Q8))3j
/3>^8UM*<_<*EWciN-k8t%CsB%%9Soa?0\dfj,%jK(rS%J+cPFlp8[3'nl559e+]AO]A+(E!T3
T!(bXnEsh]AWq#Eg6"/FJ#C!/e^F`ia?K^G(VN`$K1tN6G;XPI]AsO@CR!9\tKA4)#>T2b1Oj6
CCoP6kM!QqDti-<n=0:C>H4/C[J"iP>5@bTdME,%gAA#]Am(U`UHrMJqGdCs%:G9)D-0<H+j1
\"FS\)i1<_(r%jb`F^T@q([4.0?j;'mf!sX:E]Afr@Ielm72cIAq,#Uj46g/4=RNB$0.R[6QV
uC+[I!h#J8Q-'m6Bsh-PB;DdVp>u14Sk7ePYMN$CJ,7hhd[7&!*ZHBtKB`C`lo;oPubJf;1W
3+<-iT6sL-$[L+75f6j2Eg)TL!Zc6Rkg"'Cn@6<(+L.4HO,;&uu)"DHIf'39f6pp;?o>,_Mj
N$smXO"1N&([^)``W64Wt)j9N6mJ]A!?MN*>\,TFahJR[+GeQ@>_gL_Gm#ON@"'*n1u`hb_D9
A.8f_?PM]ACR1j;?rX20)E9l9S7uZrBG1pAT-E:^`\$`%rmRGCreS:!%+^cQ7Vq"2FW.2(4CB
!b[Tgc3'9cO/n*RG3pScUGVT1gks8YP)U+oMK48m!DpFaXJ^/roLq+:b>=?HnP&>>(46^d:W
cPD5t=EFjEI*W?)(3U-!E2:=p\oW'Z0X&-c8\'g"JK1C"#GL`7QkdqZN!TmZPNITcjY$F3`k
_=X/8r.@ht]AVQuDh$l&"aEXj'\!faJk,\#!1If(n^=C`FYFSQ]AF+:4V[=#Ba:/DBcuk8RcFE
9@9_UZgn6G45-=&oa'"lpi!Z<PL.@V1n$[;mtYj)nh=gaor9%f$^Odq8eT#K`u0pUm2<AN8F
1$U4#jrP]Ad,uSslMc]A)Dn!:,5;:m)fS(O$<-R$_@EZ\R"oFo&gBndAItZCNK\l'f7Z)-uJMJ
21*nl4,8TB\M%GeC_%0(m1O9E6;ML_2$uGL:QLhI*KgkfHM<[u=&H.4%oLUXIC]An4ZXql,iW
#fpq<%hbUeWmV5cOc\'jNGe3mk?D-Q1b`!ce)s$7Sc)L^W/E+g8'MOC+g&[m@G.+9eqH@"=!
%&:n_Lr+'JqY0GmcESkMbm:;/E$F:4>\4Kr:o<d*a``)gtQF$5_pX.8f9X!r-k(EW/+jY7+,
'cE?EC`ZnBh3L4'D?V7@E*4W'FeSdp!gY9'm<*6^:"<c<_eLMfDV$6^*t_bE2Hi'jMD<g-R0
dNn.eAXo@f1r2pq(pN<X1E2tX=,ZoXEfUY+=g^9cMlHrCqL'FO(\2FB:8"BZ[3bREUG:MS^'
mL`FA$QlXE.;tJ-&*K:iZK.RnpL+;54m1[6N"V"6lLXIhcLk#3m*qM"ageJ!qm%a$B_AcI:m
74*"Gt+B+\YGT9/@.L@FMlhaLc42p,Cu]A-]ABuPUlKB>^$YLpD4<fA&_J%:LG(,*.!PkoN=N8
OF82@R,&i+J2Gk1OR[I5_r/X2<b876-h._A"jLotF"iJWnlDZPA1i7,.EPCLWV0O>26?oN]Ae
&gKaVN`f-B8i.u9\,5&L6K/lA?%up%3YPu$_S?Ur%.d,RBoWpGp.f+g&9YS8rci\;HY'P]AL_
IGLBn$k[oN0qhGeZ<2J4A-fd9?`9'>)?dUakAH(eO/ccXj(1HrC!pt/_pX&_X79FDf<R]AuNM
MSO[]A.%=ml5&(hdU,qAKg1t1[^.@X:(;?_@FP*KK2?Wh&T_A=YVsrW<+bk==:</3Jf'J1'4V
B<&iX.XE_iXhQ!YWjXHP1&]AGdCs?3RgPH5)c?%S7^Fla;]A)L\86RY]Apu"O0sG@r_F9:4ScW=
oS\tSA167,PcaFGgf#N$`+@VcbLnm/.o&8pu:/un`LXaGJ)8hK.P<8C."PQNji"cgOhT@AUP
,/\`a:moaH$WmKjXCc<TAkC;4JU7X/SsVZ;1=hp5Mi@3Y_O"Z>;fE8GVQN$7JonLnt7m$6(d
nIZH&a9H%B);iV<5pDJ('P:qAaF'<8RM9a,ebQM`QkK'4+D4;^N3!GE!@95#s'-4Th+lNCK&
=amFdB713+inEn=B/TE?^aZqZ*\3(JWo5r:6#$GUf*Z$h,'N_im?i4pCJ<loODDP=2pqSUJs
?$Pqc%$u_t[91]A3UV:MO$.7o&KC&0XJE@T384NWsggWE.RWPlV:?[Z$mmK&K8&0GaO4p_mp-
s%\-;aO\44?Vn\,DIl#9ZrWK+@&M-G%q;@@)DQ%TE0(6YVs&S)bmbM0`N`MIaNT^No1BNqHF
d.Xor\ZWb(2<ZkMrb?>VR!'oBB5Esm&JiFet+idXg<Pu_<togqMtBU%XaiD"ZlSWnbD-rim>
*1BR&#Znm&LAe(Gf^f%k0+Yn)QTOE_/g^r/-&`J1(*OuKMP\duH&c(f"XhB+?rJsA>;5Wpl<
fhI'TUUFf60:c-F9=G6X*4iYDI.b,dSP#a8hIq`U?!FQ]AXfJ5$D3"33hcE3GoWJ)M-W1hJ%l
n=A`bWAW7Po)a.A4?6bZY6SQ,c3pT,Y5;O:NF/l@.f9RU6`n-ljL`q.3slQE_*l9i2W=S%#*
$nM8OSB\u3hUN#m:ol_.!4J=nDDR:J66S="h"AET>[%O(`o7rC+MpjUBKUl(UclcE`lmfT2o
!"aQT#\[AjG@c^C#.r&%FF5Eb2bR6ifl!"e;"We$DXM&89DqOgL2YC[\=p=";=+<:+3\<WTD
Um1K6hZAcHVT$@1hNm8C6R#eW[rFjqmXf-E;fA=k^7E.*U?;i/Y<HJW=LqRW:WU&n'E&i6@<
cES!1fE8P:6=W&@VK9?/nsPegk*sNi-m0UJ`O8hXn0bAjE5hoid^/2'6oPd$HdoP=eQj/k02
CEulVHX$5gWAfEZ;d'-8B%p+H`JiW'i*`aMK]A?]A8hng[;:/u=1DQi(Ta!YB[9<`[DO%@31/]A
WMcX,tg`CC.C/d/5Gl9jBd(P:Md-8GB-Q4k<7;B8$8C1`"8hsR7T"_1#GMq'<K3C7%7U-T--
25q"6hoH3?8*e$WoF%DfP&@,m%33TVmD'>&G!NibrZ2l6f/3I4[6NLUl9%tg3R%u<d=@@!B\
B=[i.&:PjkQYfn5D`%/^,Yn7^2/RFuX*3(Yh+&BS"tSjU$d#^j2fR-7C\".sDH]ACOb#Vr(6j
PSdikAp\2)bg2nOfZqrais&q8H=paS]ApV!#iR=o='.3P"8C%<H)*e-bJW/h!DPutFF[AknaJ
CPl\\+t,o2g4>g@M(<%s6l(i>,-df7:\ghh:l,9I-K!q?41L`DG</JQ'J!0pYF@^c@AJjcKf
BCa\EeS@js'\o)+E[)`HR>)D+tj.Vd@K.'\B&1HhrO(5>%fim?9Vk.*I;JPnGBZ12r6"bj`g
hob<JPi@OSh1ZPqL-GFp@_D?,""2Q0A?$f;k@.4QVs7N0>I0.@E7dcV<O9:R=dYJQpFrU;pN
<;@l_&LWOaTJN0)Yj90(6=)$0D!/72gZ&&Ce0hMUsji,(aVaubeSbi5[eF.k`?;I()T:0M17
EE!J8IJ:5CC^D$u.CCW9B3Qg.0k0=P%Fq73]A_q!5)O_R)+:<`+7,Kos9:PVpMl^`,e?lYE:7
S!m@_6C5%eR<t+gfU[AJ6Y:I&F7\Pnkk22J$%!C*-(Z7A]A*^7Q'>5.EkD5.?0"?9sX;NYdQi
iR_0_`Z&1d%-8er$,,a75N^c_=KQja#3:!mQ&,AFGPK@I(W`>g;aQ3c;,5O*nZ4hu1dd!I1D
:?ce.$Luhi#U=j/X:`h4CbHHY(G^*WD^U$i7LMSl^%(nX9eX^X:7@1;J$Rb5c4+iHCR[338N
qGcFl"Zi+/r$VQ8CqGRMon4W=$)>!H>R.3h(Wl/?uoYX'2Mbsmc?PFnh@bEt3??W\A1Tf$1T
aKAUdM3Mu=$'@OVCc#%?,1cr^rb2+`%Y[-*]Aa)JJ"r_-%C.W4"khW>*1M=8@S$Oe4"m%s+f/
EAfe+u"t)TOFkp;;*iY5Ma'5m.XG4t(*h.Br3EkS`XNFhZFZLHUa9jO0pBjnMNP.=[AtWj1i
r;^iP'*Lr]Ana#XlI]A(^`52LZU,jV?pV+HaZKl6O6!EU5&7*%\>Hc^QmFiTjS3_HCA.GFb=pJ
2pn>-Ppqc$ShNoSC<.s$@/jgE0SaG9@[\EKcRHNd-G_DPR6h/WL(;l?9SBYcTTHDB2X1Z_V5
26YLf+NkJn"F))QB*^KNNcTut4#D6OLr:3tl1B2),e8_jEI[3(1j/6/:d27M2uZ"aJk-69'3
8:(tL9fAg4EMjh+1.scggGP8`rs<nQrShMm8"W"AR:ji-IlsB]Ab^!3e<7'E1J]Au5(Q'1cRID
1CR#r13[G%_3F0#hu:a':/GK5p[U;Pnd&HCPH/ae7,!0+VB4]Au<Zu^^)q;W$kNoL#CpdbIhr
Sjf7cK\2Z%pA[:dCYe*Vj1]AL:;<n27=pRLEZ=Y(q:l<;9X!HD(/X(KY`=;iLP#FAAO!=;'4?
N^_"Wh_<j)Q1dcI-39Aps_\,h`b\36tO[=9__?c2o,PfXG"%^R"OpqrToIh9l(S93T.\8FK`
if-gqaf&&^5UU"f3*Td&VoMe"QuQk>c[.UhL>a1ui<hTQ52mE8l"l_mgh/-Pf!n+YR>(=ZWR
T[+&/ra:0jI94_8O*a)<HWkp-9ra'11jcTg[@'DFJVmZ?VMW(La(TRj1u#]AmYG+U8Z]AcBVo_
IW12n%Cp6LusadR\MqhEKdn@O9ckk;lpYfC[-U.kqdl@@K/k:"@TI(F*)`dBM[qHW_k*)dbg
PIb%;d_;k1O'uAf,/F4f4a)UUn>.$&V'Q.J7CD?_+EnQkHDBEUYZILid+k"GY!d:e3Ct_g)e
FIp\Y\/5_*neu0G\G%>]A@Do[k3m[2Aunc-EP1BV$ZEqh]AO(B?n;SI]A.$AG4+]A,6V)jEu.m)[
)u=3JP+25lV[dZ62Z@0=!i&t9C?-U"Ij@>QDO(PR)kL7ZSRdWRK9(FkM$`XAs[L?Rs<UkX:7
qrOIr:(mDhAbnR,"G"YU)n^khnVOAkg#"0J$g`%TfP[!7(&,,YUmLKl7hPdjY5^miag:bWQa
d-K]A=3$b2G;S[dNAG@l3H0Lk0tK1XWmJIB:iP`4am[i&_AB/GR%B\,OX._Qp#OonAaQ-lhUm
eXY"P[ikOJ$KtS#Y(K@SD(*8K@3dUNnqO`)El+E.a@&5I&U>bE"T$l5MFo@9GRuRDY7:-ZKL
^&I]A4V6LGVcXFnFI7a]AriF9I\4sQ/TqngJ%\>MdD\(8Po%P^>HXcSO\.ho-##kGGfLtTYMsK
lLScXZ(GGZ*A257:Y[pXm^gP+t2T7$>f[imY2#5PskBl?D9A'@U^f%9:9CL>^jftIhs?XQb*
G<b6ki[CAdD2FR3GNSUEUqJ8Jb*;eHI<juag4Id71p0Uh_Spkth"eNIrd#2m_Z6!jk]A@g^o/
_$PlOd+q`'^6oA3jtnNH2rrAM<S-IkEJ-C.2+jFaJj:8_!7aH`l\ar<5'pI?D>5aFBI,b<j/
fJ3IFH*ssA<^Hp5,CZ3i`M[3L@k`@!bX^AuQ"h^LThHUI_B(+)kk6b062f@(Oc<^?!$!.2(<
d`DTCg"@i)""]ARH0/FSch.sa'p_bs:REf;c$[U&s7)A]A5k.A=rC(&i<0Ksj'"n@GH=OX.I]A&
_YW#Ib=gfjoX(78-$9a4QREA1C+,7(h"nX^k$=:dJ&q?<k)L:C973,MqS5a8q_gpdj<53ffk
Lt8Pf#:ku?#UD]AHJV#642?gB`^(AC!jX@l:'A!J02_OUE:2nMpO`1,ef-!8\[hJWs_F^EoM^
O-*Mpll8<MdIqKb".<NitHd4`KpY6#(?+?0PO#ck$XiAUqL?koAh-X:iQFMW"=r"E<e(Zb0T
)W,L.1a*k'TUGT\[lhi_g]A-_%jhYtqhBmWs0]AN+;[c)8MR0;^+`CP.@kQ$N<LXq9`RUqi.G)
t]Ai($K"2?;j%PE7J_3\LsJNPF?P?PPtES@deV/[_$pK9)><B^l+HsG3i?#KDZ0I"EsK=->3P
J4DgM!3#[ssX-Q;H%+WU:nH)\J$Mj\T,;9shQ74fmT</]A[omu-BE63h%^A>b5kAmD_Tk(fB0
+tge`l<i3=%qBOon8bUT:^;;$UHfHr[)J%fHS2m]An"'TCf9T09J%n,=<1'<;ln7V=VgEhE;J
&CBH29;@j:>p;inV:KlM)M5GXlSI`Ulc%*,K3cGkq&=+0uL^[n[:1OJB6*]AXb";:[oqmH?_e
35C=i+@'G^9-!)?+//BXqkk"-om3627=>WZ>e%;%lqTWaOG%q5ufo*;Gj'h,/2_R[UE=dM-1
B."N`3ZcQpQ5DN+FFTqB4sEOE[BUq0T%FoT-J=Wlb.noU7ihB;po'1-iYck)80?4C_h2>`Eu
fi%oj5LBZ%e=i[ZN>khoKX`VP'^b$V/o/G1+us/**km&3@;FT#OJZS)o[^?GmUm<b%@=nl/4
N3^I<s/Pdj]AA,GYc&nJLOekSQ\('0HfQ[SX,6@Z02mVI9DaP9k#5\s0Ao'mG8*9sGFQXCg#:
=BRg7[rM^.!Yd8"HU7M^(gQ+i^#+1:fnDs.J227Wlr&`0\n-?RUZ'[!rCX.jogTCFlfU>qGN
-?7`=!EO>u(,+K6F%ng4s:+^\-RQp`9BaLq3[:H=oM:2Fd<=NbJ-IWq,3Bapr\YU,P[Pgq<9
uX>;e>rj3EZ$$3`?_#Z86WYpD!^XC;^MX*jjrM<`.Jk_r@KOGTm0YG;24[_rs-F..m1*CpKq
%$]ATA^dBY-:<?;#tZ8n*Vm)K?akQg<ijmcP1EIANO8b5lI8#9@f*&Q(V?+d%ind:FqPLI\dH
"E.ULr_X*SOq-s#iOM.Yhb<M=#6_kp!DBT6e]A<'f/mUSn8F1kk'n-<R5E4IL]A@=9@`QfHg]A,
]AT>%h/=/(n_$K.ABl^e^O\<>gXkpk?uf*]A7Q2Y0_%AThtaWngHtceSL%`thL*[U<3[Urlmmn
!:3$-2n&,_.6Ye)$7(n)TklLt%Cue019%/Z$gqoUc48@^MSm3#E5APt&5lbS1U^\gWl!)M9.
QA2ab;VQq"aXD=$5>)=N[br*B[]A2k!+71k(H^E`OcL^3YT_r^0k8BOGt=8@rri~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="171" height="115"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<CellElementList>
<C c="0" r="0">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[]AiYY=;eHl+6Uts:e]Ah9u:a#.;[%/tp9!g]A+M:?=8!iEK1G%rm0[<8ST6kCUe`,2%`'t@7V^]A
tL/<Le&n<@gF]AAF4%TZ4]AL*#SAhLRMuC8O;hX]AH0cX@,726(mYij;OSdp"s#o7"htN[_3V_h
Wrq*[OhkJM3doWZ)f$\51P/6V1BiDj*jf?[D1tk##X.]A61HV8ZHiX;/k3BV0U0dW"T\#?ksP
#D:IOn2u3bun!EG^S32X-Kd9aG):SgRUgfbk6J!dbI`>=0jLE8:QuK2'd=t]A.HX?ZQ`m\W[.
GR5Zcfn3NP,Q/aB9X/pFjBDK4DT"V2'Z(/%EN6[.Y?_-bJNfo5.kBdYo4nunb&&E+R"o()pK
,+pXL43n4!g*u;In[`b)Y,D=4\d"%W).qO'b"#hD\&b-=0HEd?e1!W<dqnrnQ+%HEqtXP!I>
O[Xa*g3V*jIpDbO$b-aEnpsFeemP5?%BlF[^?e;Y_+Gc9a99/TQRM&qi*rgtBZ3\iD]A&8#Y&
*0/\QPU=V]A$pLU=a(&qrS_re=mC$miMo4gq.l&\41Rp2&:87";FX)V:R]A+1ItD-!]A$_qMs+H
t64WQ4@I2[=m+,bArAf['\N]AYg7BN4,UX,Qemoc#1S$2+#q?;6['4\+Q8bGQ1K9CTd<F'=,W
Vod*)2qOi3HdX;)0+NT2!&?O5;D/S<#jo38`Ub<q===5UOJ=k&_7/5a4UL.<al4VcnN>bkR;
d3YCQH0VY.:.-i56Ml'UL,]A]Amn^OmYdT$Hdq=*?5'E.JaB(t51KjE*1S5HQEV!%1'3ZLKT+V
$.=LBFR6>jfGPbG=qeCb.i+Ce`=[`7*F)An(#P.b7B'GO,1@:j#RrH5s=O7f!^GfceP^?C'^
mn5T$]A')O,h,h)3/Ctq]A*W6[t9jIL[OL(Q7*1]A"u*TO_VAHAFW&Z$/B/NuA0qhip*<5!_djb
4+R!--O&Ga-*,VF_QtqE\/>+_1c?VOU/#gWA$FtJaDkj152QDh/7Lh=$21lpAWlj8=+j6?K.
[#,82qHWac5NDYqdCF`2Y`g9-A:lk<"IC9B6<e\-*-d&)bOa^ts:SC-A5Xuf7K1`iQMIcCCd
@22]Ad2V6C1YU:Za.gu-N1%<5o>0Cke+o5//>$+O4(D#D/`/NN5DZerZG6Mb<9_bc/+G5o:ee
mQ93H>Jad*'&Id^J/_<`s".q4b*%#[?tU82a&\_BA7Cj*nM`Q?>VTbq`V%9<@Q[C+&rS\6gr
i[>H*g%.6hg<o:5J7H&([9ua_:OGfifLNAsR)QHC)YnU(eWVuusY^[MjhpgB!$DWp"dK`;Gk
WDI\+3qE7)HGXtl90]ABhc%2[0Q--kKfF]A<,?pj%it)2Y0/>V4;@N-Ii#4"K%4.3^P9Z$TEXJ
;?V8hgPeugAg/pr67".Z^@M^]AgAIJp_Yp>D*9$kdb9Bc%tHD:3`;s#=E=!X[-aR'ce*T:_,P
II'pUa>gqn/G,<CHB6MKM#X,C02]AXA'o2=<L1>;4\&U3uO&J7MId1EEcKLG*/"'h@,e1!FqB
fp,UA7pXk`L*0:OL00YT>/NO;?m0KL'6.aDMF0V+]A&W@W.kJg>J=XUnK7tfmHsaSW"&*]A=_-
*qNHYr(aO_1Z;NlS!U7cQ*kIa*<>'0U%unK1C?X4X,u%\i?70DG-7c$K9p(=_R86(0IG(6[q
*-^a"['0?G#rFCTVS&8o$PT87pk1`;?I:D>%QVsD/mb;bgph8;A!UR?UG)@>lT8i<kY7fs+:
7IkeaL4]AXV*t-8oSgZA%>[/*/IU/Wr0Ik#8:%gf"@q3=7qpEjtiEr2A<cb22sHg'Q1i`=DZ_
k690"B2$_&fC)hNAIguOMp2_>cGflG)uK@AXInr[T&(nYoGi=lLab)\3(]ABf`drR74G0k"52
&#nN_G1V2l'oRM=N7(l7V?Wp=XYmnQDBH6L>Bbf\/YS&L;(s^g!;`77VW:%)268h7D>U6@Q)
D[;HoAYpkZSCID#5mWV)RU(r,7!9A3e]AFK8dVYgqqF#.XuZl;SoI*ROq'!S[@N/U%E0I(^G2
S,),;TbYSQqP[kF%@]ASH\O-@c]At;qK/l<Z;qYDH-)r_k5eOK!rr8?`_*h'1E!*\-nEmKkXT&
pm%Xf0AImVdD#_MqkK:$`9`$TR+!+UX<-D@=O4fHdh7f`ns+RBYO'*Rqg%VWKZjrPV,)MV'H
0gJI%aRTO@Hd/J!0H#PJ,OC_Tj28fMp72=+j!F^'7_;`$9-[+U&Z\7+50BZrc\4EsSm!MN:b
O(0>HP8rm&U-"KamI>]A:+SqOXAI)Y*_.cP!N!IXiL$qR)oL5G=G.]AQf:q8m/d`6:^5X#e<GR
_qpEDM6mK0;7a^FFp\SDn2\2+>FZ)cH\JES0JV4=tkhqC>2G"QLfG7#fqA!5=)#Kir(VE4fS
YfA^c[lSn'"Xm#/5b]AV!>,X@o]ABq]AhZ*X1p^dE\+deJJFs'\Si"'7hfV/_">L*!JP2hD%R[\
-j9XK=j12Pdn=`pj/HH!*SE*fH$_^EWS[RPpq`s6%:Hnp4d7Blge&?E&'8@%?j]AMm`eMDc<K
L'_RQqaLGF-<37O?*eV(F/HF9?MgEb<k99CqQ+M8fnBaF2glU:1e'M4SsKh\`AH1LLpSLZ.I
C5uC?SCH/,nXTk,8PrpR;B/3u^>b^:&jYS7VslV.@8JQF#<Oq[?Sqe<t*;e*Ztl%`!hd]A1+W
.Z!l4""6OkZ4iH"1Fb1IJ-hP_!^P%=gbJ,@$o4P?kCGeerM5nL24X9`qKlV8h!+.9E'?Qrc0
'fkil:<ORb'$f,V9i&&/Fr=XY!]Adjab>IU[,(84KIq:Y\*dcZXW`Ff'J`\O2#al+m_%,ko%"
=[CK;LR.RLlq/^#sUZDb6;!0bamnsqXeopIZa$Y?1%T#"<^fr/?p+tU_c8*S+'K-Ta?m0`Rg
\7)p>lr0g0JWfTJDR"f>9d@p(1FUte&cqgRAT+r^WaGXM(78maYT3,M&Z`.t?EKtq9(<Ja+S
0(urPS2i%.ra3NeV&(hplVTHN)PmrN.pGZYIGtkjg**^AAE~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="231" width="171" height="115"/>
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
<border style="1" color="-14340777" borderRadius="8" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-14340777"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-14340777"/>
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
<![CDATA[304800,1828800,0,1524000,1524000,1143000,342900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[419100,1872000,495300,2592000,2286000,495300,2057400,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" cs="3" s="0">
<O>
<![CDATA[ 全部成本]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" cs="3" s="1">
<O t="I">
<![CDATA[467]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="月份"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="成本"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" cs="6" s="2">
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
<![CDATA[主营业务收入]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="2"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-15591374"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<OColor colvalue="-9864992"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=900"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
</XAxisList>
<YAxisList>
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
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="6" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[成本]]></O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=D3]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B3]]></Attributes>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[ 本月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" cs="2" s="4">
<O t="I">
<![CDATA[417]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="5">
<O>
<![CDATA[本年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" cs="2" s="6">
<O t="I">
<![CDATA[5630]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="3">
<O>
<![CDATA[ 环比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="8">
<O>
<![CDATA[ 20%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="5">
<O>
<![CDATA[同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="10">
<O>
<![CDATA[ 0.6%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6">
<PrivilegeControl/>
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
<C c="3" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6">
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="192" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="240" foreground="-15680447"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n)H]Abf.C&,[50K*sY]A7Sh?$H'm#NO%P[lT:N^M0d&VOMX/dc4Is
Us?p1/m1(;BhK)`-U4@R'F5@>2?ac\>qFeg.6i)T;9d#Q+2Y?)Y+h0PNSr:isVIl*F+.U+qF
dUjYG-:6>&2]Am8)X_GnqDqgtnJVi+]Ajr[=r]A>f*p9CsgB/%La6t3]AMKrrbsDs(K!0ZEIm5EK
.V`FN2NZAQ[&.RNahL"olF:!PgBO5~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-38294"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!Rq*"s+sQI7h#eD$31&+%7s)Y;?-[s%KHJ/$4$h9!!%]A*DoVhX!1QPJ5u`*!A$CA<Djg"1H2
gIQ5WoMcn)k)24duD,=A9pG+?^,U@/jc2U[=iPrs7@9ruXc`"#@*-1p;\=D=`&jd"1B[D<sN
#>f,O>9-?)/)SrjGT!)r(E8D*69gCFp**,c>-SHmfZu&)`?;2R3s8RR<TFLd[Ctf1-,H)2jl
dL-bFq<cN#@emG-quC's(F\D#ih'Q!2;C.?ZD/)eGoRL!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-12721762"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buf0pP?HIs1?(tmCnWd.<tp`?]Ad#g?<&t-g,r-&F#p#fX&HeeYkTHN0^n?K-TnXaC!WiHY+U
legFFaua8;lA`"2aEMmPhJa^Y`l,H?79?V`j3*pYIXS5JI`Q#6o`EqfFK]A2$SVTaal^hftV)
%7f:RL<e3NViOW[09RUlD8M9Ilg\HTu7B\nB&%ujm/0P6Wr8uMN4Q`f!5D#XCd(6c@B8*t6i
.MoD-HC4Nf7%E[4tnj,Bn[kq>]A4aIN9Q_BLjAG'0<aPAFK#.KZ,:O?">$IN=a%1"5NT/O/BX
RCCX8C6DMqL&;4i1$d5tO]A`p?,KEP=m9"G;cJ'fod=I+!\>Z[2;//7ABhWL_Xdp<DX*1-%id
WTiOZk,HWR["*B"FEU+1&!qob+:7fSgu,U"H5>#s>Uk_]AhiPX1C-<`:QkXG>4o36udcU7eEn
W(eFnMmlH`9B-kO?-35:c&T-*"M5BP."E9i=uhE;,q/4.Dlp/M0>Xa6fa]AeAC59lW9iM7qp/
<or^M47mErB%7goUe@(S4P_?iQ8In7eKXId'*l=uBPP:E#'O(1`">_NJllFDfa)9!r*mGXl&
@`$1>R:-c<(JmX#$7/LDha=AW=F+,YMap_A4VR1j.gY:]A(e!_!='s7iSGpVS4EJoR,WbU\%m
D%^Q@)tJ$-j,4]A.>^Pg/IQ*j$kB(FJ"6Vc"W'K[[e.WK&IX4UuCT0fIM$A)Z&L-AXg1cP@8%
HW##n3]AI2A!0cJ&6._K,FGNL>O;2LP2IFt<Q4\(K,.Z[W;ok#1Ks5d#9@*t%\E59Z08AnD$"
&,Zc^c?cVU3Ubh.4*P6=cF2?a<7^./?OY$*Ck_/lVOtU@Y+8hK9r]ASr^:qF,\!h^)nPk,]AR<
uAUf/3B.9WubRe',7UNiQp'kpG<0l'_HmW_aoiju(l2)gT7;Atd:M>k635;cX&D>lAHhO0-K
+e'$#N'$,DkPcdO2Y.OpUpsf)q;gm0gT1.:n)(7ZIT-Xn['O`dr2$)I"0m$oEbm7*I>Ui1.:
"9nG0j/hY.]A,r\Z*1bPad6ihY@`hq9i^m%tK$_N5+73DmuL.1+gtC3]ACHBs1$2V<bNe1)g^N
IW:OqIo2:Z8\p+nR%(FH'f^CcJ[C(p?W,1'A`[RF(02IAJ+H>V0DKrc]ADoNXGaf=Y@C/j3LW
3u#R\l7kmT9Ymc^F6%2HRpB@<)sb*MMG>EE-S)).3^,G;,rm-.05o`MV!'(j9d'+kilq>[VX
.(nFT>LfPN?4)2SOoIbuV59&ePkb#TC?U!536jg@[5OD5Y3VV8'EZ`:*()Y.A*Lssp!-@g$V
=OBER60JN[^s*$%k^Xa!Y^$e1L3PUlO,?1)*ArFDKN?6KL4kSY_>]A]AEpVO#N@liZ20f\$"kY
BihX1V1/ZR9H'N7JV84a#`,2'4GgiZ_]A]Ai9IKb-`a3f#sS"0@l#ai,:[3ZRcY6`pR`SLF;_=
N<SJ?:'O73@%9a*U7cSgX4Y:@Jc]A'q.:Nom-=IqJR/d*=r*R#LI=&+=1kk.)dDi"pcnMh4]AY
cg>N:FmLJOE5!QWDrZSLGec:M=N!(\!r]A1A+s+=JK5YY";I_ZNRET*juI`Y6>:o/Fhi21mZP
K.`*u$4',PHNi2]AK1@cIp/"X["V%C.GgPA_a!*X_Z>CZPf0DSFH)rkB)jO:$N(YlhbR8c@>7
NA4r\s2M%Po+aDNl3!W:3e?MWkp53kF6`#\t#3$'X2t]ABAB4mI*-WBJ36_<g\0D.*Q1UHFiJ
RfE5FthbUpOb_o854d)0jkf=PV6VHO0\Sf^*=l/6tl]AHPaqOX.=Y\-kum$;H8J/a_BH#gC-V
'@EMD2Hf&Ml_67-/$jI.Lr&h__UWT3Q,a)=@P0Ha&[DRP_knb*;4>Y*AKV9b*T]A!PV_eXr9U
[=EFt-"r;*;1s6*hkM5!_u^P@W!q0K)50jZg7p$7FWR&D\O**1":)2On,\@ei&MJOogTQ_$9
An$VnaQUtZEHdUb,lKgq^1(f.T(_"_@Ren..qf>+6nuM[sZfVHH_PBW<51NqnXFT?HKZHdTj
F\_`4NqROJ)GdfG^kN[`s_[HR23N"FYJTJ5CE"&GMSgsSg"WD]AAs(EqhQQR>mr$s>H@/8Y:I
>*,+k021&R3OJQhih+WK\>?NBFWgQX0Z7+MZoHi!["4rGF8g&oD&AmNd"+7@PL_AlCFG"8:b
)>pY,]AI2f+(KeNN5X0rf=`.Pm63A7]A*icTe:KG]A+\5r&Enb;m'*UC0M<oJ1-R?"GE"OEfj)U
AX/EaJ`)fO*S->!-2Cg3?n8f?FQ'k%N1*Gr.Y?B!u0[N8$g`W\e^jJ&tgs-VcKgXN1(mM[kE
[Hj>PA>maksD3k$A'^)T&4Hb5oR`P1iqQtQnLO5A94pp.jA61^&B6Ni]A<NCZLRf9Vj`g!RUK
Lg01KoEh1`RB/0+DDAh4P?%eU$jd]A%WsjTWfrW\-WB!\SE8Hp.BOeZqjW'R^D8N&?<YnAd_T
)F2N]Aj['f.'se\bPKg2jqlEtlG([.Y*"O9mrl"%_uOPt?2';QU8`8`nGH=I;ZFH/sP5-o&LM
?!+Zm]A#`#gV61OIWJri'KBf:]An4l_R;/e?@BOjngG46ob($i7IJN1Fb9:9P/[KlF)5Y%CSIj
lsp-?H?D,K7QtGbG-Q`buOS=+$H$5"V\SR<L,(1=8RW?P*n2kHbk1ji?TKcQFI\lb<P,?Hn%
)69<i*ERs)TM\.k;2MJ1@Y*G=bLF(,-c;(9X,P[F7kjc.B&\dFdLDGIVG_:\q?]ARi[V3qRg=
nsZ!<U\$>cn%lon_YmO@QXcTfat=#T:RY=al9t&(?j&U*C6B\:A6hIenB(C?8+ZIF1!T>Y-*
n^0fgbF=jekLW2[;H&.UZ=EtH:hp'H8FdkJETYjtZ6Ya%6FHuG-l^rLGZgH+emYRc[kW'^$7
kHn\KQE)&hBs_'E%2Y!50WI1u.RXAR[Pl,BD/SCOET.q!_Dg?(6?b^T733JAj:1]A`;U&qq@K
E_BAC7p@nCF0eSBV__=/t*7EN.CTEN93(#2-Te9OEr:F1SeQXon=Y9sHZ4Dh:IOlZEM4/>(>
Un&/c*Es\A@XCdid9B(>'q:f,fBp\8MKR4#K>"Ye;=*U_:@LKA`FI,0E\C+V$Fe;;<=hYd=5
?;88(4it]A&E*i`m.1/8C0)a2rZ'ci)#AOI<AL`oBqR^+>PhOk-DfE7DcceV%_J)ZY+Ho69a4
D]A)7?GTLqI/Mi[$QG+-U_FEAsh1r?XlXo?HXq?bYYcCXSMRBFL7H]AJuN4+te5+;Ib^DFf*(g
%D'jqi<0%<"j77Q#EX1"k0)+2<7Pb9jFG:Y^+UJcFJ40SRb!PBH&B6P8J:cu"DV^(_g4g(_\
Lh>"I/GOl?47)XXdc\+:8Xn:@?*T7m]A\@'maHR(&Cm)as!BKLZct$iJX"+[ICiLYf`)*7I9-
#+q?;\8+`@SA!u.CVhuDn'tI_qJK0p$G*rGYTr\aVK88?<f.YN?&0N7uoI/@I9't0/AF8RsL
ii0Z?_5UNlR>op]ALVGM?5-:U=^<5':u?d0B?]A3&-T(sl\G;oL]A`gFi_oasoa%r1u:N..)/,"
`=lREFZV"u8*%%`XektDSgX+IsZh_IKic8@oN[obHpHg1c_L^fK[7=ljQ/m;<4*IMq+,sI;X
VhnH(CZ?hEIV%k^:L!"aN<QMG`aiZna.E?qhI/uem2B5fX`OeXZl@=I>%(h_/F55N&aZ8<n;
%+[bj7)c\;0Og\$<"1c<O1d2.+*#!_[KcE[E>V_I46%hi'fAJ*cGg]ApoJTf1t;tRf.\827\A
u_ZM9a<WeSGD)iofI9LVjZtkX2_:jZGaLq7+$ERkT-RqRIOfrEE_!*ElW-keA?a@NL#u\Eo"
+.6ZE_gZdBb,A7^OX'j=j>.q1cI'U),-EjRuC22M;41fRZ\r$,GZj_iN(HZbr8pdI`K5:OsT
Iq9=LSlpPeP!UCcYJ?gJjiLEr2S=>0M>l$o<-NR2$A?_'cbn4m]A]AZ[[dqD?j+sSL2(bD,t5j
\9P%CeJc)hV$7"q<3bJ_N[Q>C@_)2WF171"93=DoZeFbp'LOSlWc^6i2i[cHb:]A&F9&[Q+"N
mnT4P2ien.+)A%uAEl"+13%LNj8)3E?ItHLs19-ncF3JQ;n#*qK$N_rS@)Eq>X6qGc'I:#W3
'f?cPcl7eT5n:f0e79R4nW:sCIqBqT(,<o7CZ%c<Mr/_#OGV&*!_6u:A@"Vc!JP21KX7CiQR
0CZ:$I9f*l4;eH?1_Fh$K]A'sR=;puW)0/%&[2IQ+,:0_k!,7;&f='Z-KrF0$0PehT/2GW#RE
u!;R*a%D7QVej-Tk]A6e_`,)=1C"<t$uR.m0M^V)a?6UYPG;=O$E<4dDrA%Ti$r*3)Y<I?d,;
?*S=T%dI=J$D1Y>48Q\Hgq_;NDrtP/G6$sZ[pP?J-Kp4SCAW>M?:tp2p<KK6D"cZV"2d[aj`
3"K:P7ZWqUqii]A)NFI"rK^iqSn8egV'+_.)$Vc($jhb13T="WVcaO@)BM7l<K1ph,=cCTA@+
3K1AsaS%:+@dmNdLc7*KolcBNo'H?MmD\l-u;$K.lU*PPB;WC!&XJTbg)a);Uhkka.NTB\#j
cQS!ldO20KLurjMrr_\qk*9ebTKAe>VDr_/ra+XKNltd4`@]A8#2QEb4(0f[PTpkq^Kt;<W3O
'E3R\l3Td7Ccf:u4ABXck%SWp9Qk^3=#qC:0'J(4^2D'mYl?2R+7^[0+nRTl'g#FTjp;o1K_
S3cZT$T%6-IJh$>ni#qY:mTBGXF$u>#.YtM6<>O!Y?9n-V`[)V\/)3o4$@(9P1G*4XN:/dp6
K8mCKDZ>7dq9ImZQTbY`kl.$rS5A"Rkq`V,dM]A@=]AG9j#0>t*!Z75"k$#d!KH=TMoXF5;j_>
f0eH[!I)`LNk,*33S^aaWZHP;sj!7Ol,-FD)-&X7:R7UVN:VGpn@=V'J+.U^M="9^(Q[0`dZ
++0Q1b$XU^.>n_d9M$YdlbX]A1"J</p"?6R[6qV7^*uVUR2/A;lo&P<P^@2on0rj%aPdVqo@\
+p@g3$FGI&eGkm[^9IU]Ada!`%O2%QkJR8po0!n^Gk'3&EA8]A`,'dSK`%R;]AniB2+R2R!%:I!
?V-jcXKarJXf/dH?T_0M*=o+-Za_,(=Ai*IhT5EG;_aVin`<C7nXfPeTj-E;9cH#]AeXS+_A^
D-QnUU,cVfh\=bll>;N#]A9Z+"b1aUaCZ*_/%F:HrVSS_c9Qk,A\"-.^=34!.)@8m1de*orNc
L]A#M=(p_K8_*I1X7EoIpHh@+8K@ENP$XF]ARnBW&-P8Df;b#;%YU+15uu?T@CY\m7!iW3IQ1M
31OUUm(6$Z-")<G+0!8/Ws2KDWo7r;5B@G3"U'r'WATGV+KCrSYg<1S1Ed;d97%EHkGhC2KS
Rr`/=)o7GW6<p+lOdK/)&q_dnEVPEcR;[@MN%*St[ilHTQV/5["W-V3s2<]A(F,U#N)u@tVC?
mJ'86kQ+#$"4qqGp\V`B8*Mi%$&ZrO2Qa;ZLNs5,n]A!c[9"TK!<1H+'h_)Xr)(u"XV8Fd!Nd
F!DP.bDtB*^-Bl,rsEnJsi!XpCJg96"CGWkK]ABT0F%+JVE>&\m)1GqcFHom?Rr?;T-Wia\5U
XkF'0cI=8/mf2$<q(/E\_J0L<&7UY^f-]A^,Ec1'6l3aCB[*\WY1dnD[rjB$Ec(1Vm;4::mhH
$OMC*LZ@s-b*Q^+Iqk)8uO2fVDL9hWBIL6UH!MWm@0::Vs:f5=;^(gT\3T.r6#d*WKX;[4Bn
8;`;CA:o5;QDZA:bRl/cmhlk"(kmaC:7fue)c%M_&LV^aaA]A?(\7(R6*Ic9?:IAT<LdpjB6%
>J,50<]AOPB5Mo#V)7BSA[/!3-h`48OSOE>2-sWh208*(On0Ct%W+:i4$6$!D)hn$7T%<7$Ah
K(JG%K+em.b'0SR?YK[oh.gMp"tH1e,eK&%NVp?;A12qFoIgJ;Q%q2K]A0TEBV\"l04o-DRPb
hr1"4`Z%YP;@<(O8pk97A"Pkn'Bd6WKbCeg6=Z)S?7L(CO2'Fu+16WW3E61MBj1kf<f]A/FCn
Llc&)kJ89?.-*08?S=QG1$pZe/i_C.uY1SQ^@!AdZcqgOHrpXJPo7eAZ?;!b$J%Gc_k<(STJ
>8eRQd.QKNA>aOaeI&t4U43/%hL?@#USK'D<aej?B<F28rj=T;:n+n4OXa`e`?mc/Mmd5=Hg
-QB@!KAi1`nP3PCm_oEEN^qNO^-!J8h5(UkkViE!hj/GW,U]A!o-dD6<P)t(#i$Z+m%r,rjeg
'pib#[D;84S`4__87o0rY..L\l&(XC)@7K'9B_U;S/A'SsnMO)1Hd7P]ABbg=>*0ljTJPA[=P
Wg]A!G]APlGrDFKVEEPr@dLV"m12G[0o:q''X\lm+;glOQ"Zl704X8<g6+8$KBa$43F+i_\lod
rJgthr_G/10GE3[^j*Z\7aoO7dS*7Tb8Gr3ROkRG"Y/A:+OY_NU3b[>4EZQd)l`>rrL5U@O'
7k':gu6FR_4/,'FoGU$n]A7ie0Wes(A\>`u(5(%CX`UHs#&#58s[gT.R!eLBd@Egc*i7W.P%E
T`6)Ka.\,jh7l.1&l9nCNadF'mVE0(;'V_I]A`0I6[KW:g,%`Z@'UAo!XP<_5@O$i<I+6VmPc
XQ.ptaqMIpU#hh!bkV2e=M;fJ$2&at(4WpB=ccV5l.CUaZ;Aj>!'Aa@OX6%:NG?;Lm;IbhWN
HC#b.&A<aCqVC"eRn7I7"jc%3#-]A(H$cQY;Yn-!-An1@j)H*S-^=&F2U)Nh5TYbBJ3-?'[6>
27[cqS\s.kZ?Y@IB93ODS)q6#YI,VP2gSc8A)8Pg9"2<pC"dA"om/S[mVr(]A8kNn;"B(B6$M
39(#0/+"G$ImTe.Bji!YiN[Nkg:PHPZZ]AN(,D0=nU@d(s@-e2PG$h;e&^h^X>r3&Zi*\q9a1
J__*@AIU&T=F'S-bSC&mj`om"dSh&kKQ[,0U")O1:r_;iLkRGVl=l>eK/YXgP*j)V0gM^C@,
"]AgTr0a"5?>9jm`sK";52a@_g%]AUXn*aa&3.tW\9:5>f=5FaD1bPFT#Z6'Mnc=p6g(0&V1Al
a]A3]A:h[+d7<0\@+'7SYT5Nd,0a3m[06do%0)8),_':sA3aheP^0.mUM.fY<F*9\"KlUPb,4&
;U5iik2c5B8_1Fj6Y8?;7n-"!?7Dof^tmi&W2Ons-a"`7"s'bmD)1l!.AN_l[QH_C".86c/H
*2r[iS%&\ssBF8'hdFi9-_8XA+RlOQtYba$:)^$Vor/$7(KY]Ad:0GGi!i)atho=$7&]ATMZ!-
&UND3o;:8G<;4u:`H_nj-%u*C?f-+i(u_S:Ru:4Upkh6pIg'(8m3L*J+%[9qAkiuh2?cJeUR
`n,\$:o.-^J:9C&b%<^#%f:nWt1E+f@WofLjdq:3]AEr_!=UgqEK.hqag@=--`?pg$OIfPdGd
XF8G*XDTIfN,IHnTcKRI\Q9bfSX(3i-Mi7<p?Bi?7D_\MmdIHZb<>1b8aQ!(KVlDca'XLZA*
?8UG#O&p,Fm#\F?G=&YaghK_^g1jq6f'.)dd<rhKa>VbSK&@u@.GtS>Qk+noBT8rYI87uj3;
o>Kp,(FdBD-haQt-o1c]AJ@g=IMOR-qu6nCj&6rp3<f*I10<b!DaLKX,N5dkdZmH*K(r/3P]Ae
)sI8qPtpH%o?Nl]A_)=nT3UtkD!-rLL<[3H!hWElXdV?5fDj(2]A1N4.4@dfT_61%a&3%0'Y@f
dg'<RRTQmps#[6Fg=r=T7YcXs#`#Ja@rW009TEhJC4UqQA?+b@Si>"p/`@@nm/?2.?0K8BS0
eT$<FV`YJVY^QX>-&i9B[%!M[r<'VD/_Ih6cA7tDn/RGl%irhCbZeI$7Z+U(gNbsC%'.X.=9
d,e&ZQti,r$r6O/((!@kbbR(M/%*o`fg>0\ViGV&o;$=+b+&_7g0%nFO03Dc%998omZ~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="171" y="0" width="171" height="115"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<![CDATA[1440000,2160000,1008000,1008000,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[1872000,2592000,2592000,2016000,288000,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="4" s="0">
<O>
<![CDATA[主营业务收入]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="3" s="2">
<O t="I">
<![CDATA[2470]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<O>
<![CDATA[41.83%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="4">
<O>
<![CDATA[本月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="5">
<O t="I">
<![CDATA[204]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="4">
<O>
<![CDATA[环比上月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" s="5">
<O>
<![CDATA[-1.4%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="4">
<O>
<![CDATA[本年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="5">
<O>
<![CDATA[-1.4%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="4">
<O>
<![CDATA[同比去年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="5">
<O>
<![CDATA[-7%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3" s="1">
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
<FRFont name="微软雅黑" style="1" size="112" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="240" foreground="-15680447"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Microsoft YaHei UI" style="0" size="72" foreground="-6710887"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-6710887"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="Microsoft YaHei UI" style="0" size="96" foreground="-15680447"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buf6nP1dUZXIQT'6#4V8XDk.D.b!1>`.Bh(;KFe;gpc&R\'!.'E_4_2O[LdB_9@<.C0E/)$k
5p.8Pqbf8J+[:\N!NObgZK-[sRT8`:NK=qjqlCg\H4]AT6">W.?e6\ek)qV&.mIoKn)r,#crR
Hrh"XB_gB:El9-dC+:EaPoh@e/Qq2&k^sMHcY"0,IaldB_()PbcX3.^m3O0UCe_Tm^HHT9)3
,q]AREGe^&cIV07S^N,94i3AcHGjjX?9Y8\mhWTCR+<IkrI/U49g57j2KRj+R=XP/XBo9aAoX
MaYpW@r;GV<5f%$(Z]AC=+/'QIP8CY\t@C6A(mThfXkX^Gi%0t3>X;aJ?(5[pHakRGfPYY$+q
6tgFN@_Ka)%qnM[%Q3-bI"X/=-H=%4EV3+K/;hW)3k@X@%j^?';EZA=_L(i(5X`h<AQP6_H:
6gpX%lGCXJT\mZ!O.:cZl6V."[bY'a`.38NV!^%u7G*i@UOl0GuaBeXl&@Hf$l<IWqVHD?3B
1.fg`"+T_HI0HRTagZuQ%lgFnf0F^2nclU.85`-,g2Cd,'GGCMI?`WW<JkCgIl.UgoMI3_;T
Z9IKR_IDq>,X\;)FN4_BDse).0I=;\p8"#eDg6!f$tlP[K1+1SAIslQ*l:C(pi<d;a-HXD3+
dJ;4b_MWh8u8/mp2!%nL$YRU^h,(UaEFe#%U_Eo93p(kAE;f%^9Ed$Jk(h?hr@HpI(D^mj$"
W't,^=Kn&$"daS#i@qq.l4N]AI>hiVsrIee0lo8r=7ueau%Zu&D]AI.UQ<H+]AS>28W4&[[`9/.
R)=-h1T=S09F6d<-rb"eBiE;RRWg*+gZ*Z<@@OQ.mf!:_6$&XJ3)fjsP7>n[%g8fnF^9."6W
JN=rX,+%qHL_TjMWf`QZ4,aqW4O)QG(9L36MS@-COcBTq3jHJVM*LX!O'S?HlPZut]A/h^1'6
jT,_f#.iJPq4p).'HeJGgA/cr-!:=7&!%2G[r+7<h`Cn@59M%H^eTXJsH;t[-$'3T"aMBJP\
"HoJePY$<EH^;_u9]A'-EK;rCM`EW1MJWIjTA!cZ3Kaprb_H%$g+l$RQ;n[p_d3[HtR80XGg$
@lT+eg`pR#9MV`>cN(SEed!(a5bI_/)P?5<jo5sReMXc!nX2Ql&)5Q(CiWU#G0V^D.MbmX5R
-FqeC8pgA]AeFf<(Y6Rc%\o,1u9L9-WJe1Qpm^1jFa]A[9dsNfq\>]AjRIniDT?URg%Lm*]AqBV3
+=1";Rjh=h#p:r%4Iopd#2E$@G$(W(68B,Keoh_/)T_E(\WCl[RBr=f.K<ut^Y'2)?FkFN<_
h%APf&UX*^e62@Y&^_q,iX"70m#3=CB3(Q91Ae_9(_%YiZ/0A10Jphds#(hfK$u9Z:F`7Flg
Wch#K""\&O9#6l;O+Zdf7`+H%Jbk_#D@QWp"oh".PP<_FAA:Ndg;:)`IYr4'85")Bu9)J?r1
X5R?BniNX[>C3^5_;e`]ALB^BQpP:RVAk2^=XMo(gD'PKTY)C9FL:O8"pFbD.$WW97L:4<j`g
DIgJ0!I6SL*s=HmhEi6IV`H]AHum=8ltB<P(u$?-(d'??GDDY6W4n&M3!AWo%YYa%/"\Q^L3u
F)S#T,p-lR&TN]Alm*5s5A[\XKW!>CX&j69hO54Zs%mS]A79;98)YFpQ<+ZdiuqV\K]AZeTJ`b<
*3E[c85b=E'M`m,bhpQ(50>fPLT2<rB]AmOHFP?Y@0h0kXH'dagHG)Pc+!8n*;>33*H^g_7/#
Q5V\55$@@jC!&a9?0/rX[0D9HspOH1\>@B]A.0XrF1e3$:@E]Adt#ERJ5U5Bu;?c#c!NCG^9EM
YTAdZkhk$A,lqhXNu%/F"EeE/f/<>PI*+%oMOCuBS([$g$F;Vfc$[6.Gc;-T7@S.UW&)io3E
&WI]Aq*i=iP"[HSWmV@U0t[mUle:WBk0'1<c4o0:5a&eZ-LTQ]A-#:%"YZ=0:[-)MobVrk@]Agc
']Apm.LUR/KB_"gMqd+sgf=K]A8)gsF<)(`[_]Are8lbT7M]A93-#f"V0#(h_F<l"6u"Q'`Vn=dF
!8Io4lKI2,(S2[2rCjr!EpkDYbb5"+td92h`EMh:8$rY2a[p!i;0O4UlQ:9HeGkN)D(fC5t:
6Di67FScSr-R,<p/rmI7GZ)KW7Z:b'.18tu#!m5Z9$HMCJ,R*K]A4O+ArF']A:08>>;%^DM`JG
.(-T1.0PK[o6p3^NA"3'&:Gc$5<4[?Lb"KCY'`7%5UKh>$deu(Vt$C6O8<*p(fc6_;:rjrO'
M*l>%=n:")KNq]A2o'`Fj*)N>M:@.VoSUHQebA_X.u3IXkDDoK+gr:.q!Oa[tF-O:cCiD^nf,
jbTP0S>QkB.aT]AX.gsLHsKmgn,O"jlM@9cc.Dm,.-BtDlk?R7RCFeUAt"]AD>!OgoS]A,)C=Hf
C$LZgmD%>_W1,oYOr1FbkkF=UiZ6cs'Khk20[JE/*Bu:frenY'K.E[R!Df#2FJu&/JBr]AFmC
BU7`_)`BRS4ZYrrAAo;Zn68Fs00-_gLtmJbCZndV"jQo9f(1=AtH_2cX`$LXhKG'tTuqdpF\
GJ`TfUfie:\'&?eQmH!lQQH-G%X;3mA<JfIE\=Dfn%qGucF,3u0]A9nF)SK,`b2m[9e^@KRC,
=O(mhKW6V[e"$/q_n0r)DR/N4""=GgrE(dJLl1,M@$UXo>NZXd*^N[,iIY2Q2p5)(tm%[A#@
^/3oNR`4T<R;<+=OZTSs-'KrDu[%7a<%qX7!V_1/OH\d_j%lO*@Nt6.3_Vr1F&M9s&Jf]A(oq
_0^&1uY6./FI=uF4CQt`.Q)Pnm)]A`HOAIF+_;hO=>.&7an@8P*nYM8_WrR)ooY&$XZA%Na\8
P(_H!6%2DT4TCAZe.lm+0ia!U^E)TAh43SU(FkCHT4+\T2@W$kFFFuSj1<3D=UYcG\S-LZf=
@'4gn`6?7^)\aK;^g_bdY<"3=No#D"9FlDDqK2q\=@0Q1FX0S6:U/_Hkj/+4+KN[.iWD%_YW
I;`l<M:7C&r@hPfsg2_$`]ArpC;mM_i`<F2guP+Rp7-.QduHaK]A=;B)p[\oMs7#8>mYYOK4>D
M]A;jcANI/i&#P?EbG@sPi*Y34lFbrZ?CJ3m\6B`fUc=Q)o7T'PjlGS=:HZB;Vhbpk1O!_T=O
/cBXcNJ/;d@`T]Ai/n$aAqNP[=Ru^_@h,5-Vj0mO7TPe0Oa;YlLX(F+V_?:902m,mc:9cHZJ>
JK%H9[l8DYe;A/O]AE_)U[KZ/l;O#&%.@7AnCF<`F=\3W$-^C4%(4$:>ci?J0S'bjgh[rCh_X
C.McZ";tXoHq.o"64cI;0+9UP'#\\"TK/Pq1uh00keaTV[8V,%mD`]Asa1Z!#/i"=n-lG.\Nu
1*e<>p"iN29tBT+>ooXmdIW0SWb:cs:FM6#a?jSN#phK@L(prPRFC+7hO,3^.#sc8PhThJ%_
DY,,';7-fen:bMsf3]AGO&DR@\Z;dXODo@;^P%@Fb8m*DEZL8pPh-s&SpU8UVRg+3E>PcAs&Y
Yg&7-BjkKV_?b)=H00;*Oidm(?qN:*tk$6>%Nu+P?:qG1$MZ=8RQ.)^VD1XXY5<\>h91E7Vh
926-'sj'=s0\93t^U4(t&TbT_IW7%%M1S_5=\VDM?Y,Ck$K>&>d:F#DX[FsWsNqUO,%;),D<
RVoas?Jdn/QJnM%kA3j'WbV1."[L3=\R\8%e?rul:>ueZ:1<NGkj','jUoL+M\6?oN\R/DN"
Ee*j=sb-M1+<*.):"8,aGX`\k.t'>rTkF4g`)g<*(qnO&anYSo8Y#k>]AXp,arI*hR?Mu1AU3
m;8`W$F\hNRb/Nf=4o.d"IDrcD)X^<;H*C1Wn<1Z[-)Rfg_.fg7KtcHZ`5us##2-f_G3iR'L
T*.\%V^;D?%7LiOg2_:XKK44r;)=V8'c)Ekq`j"QhbN'ouGHFqb.TohE#e]AW%Husq?D_q5?2
:2iA2Y1_*'c.1QBkgXWn%+43ARE%kC\]Af`fTAS6BBX1toh)JNEVmG.c-!'G;$"Y-D<4.o6IA
b0=d>;'qWAe13FHi1fEg5!VeI.KL.?iAq!#UX.U@Uof_P*Lj_Enr6OcfNuP4=gf#me*tR@n$
o!SH"5a<+JU[eV^MKmU?!f.?MRq1@%1kjYAuAp1t($o)Ks)ugF!XG/Re/2^8O7=AQQCNkqgW
ngB6nk)51nS=M>*D+BHUS=_o#+K&C6QOO2*TeX42P4[c=<gEg_%g[?E:<LU2=/JH)-Vmt'R`
9O$RSq)BTP'.mU!3_7L_2jd'<ZAJ=b#qpnIJi9rgTN?.jELC=&5rh('#Q^;":uQn/8;_>(L0
5aVI;P'i@I48'N\&o<'&@R/b$H&q-Soj2BV$M;ma"J-hNAfg65gOrlS(:gf;=.8KZ1d[t%u.
BT?/3M\9c5k8K,#k[_pX&7mMhUboi%%.JUg0<^1dJ/pNN*i[Y5Cb.bp*?6J+7.;osUNG@Vel
-3nL,FG-h/LCr_:om![dWc;rO4k\Vh1l_R%iW)]A5UZ/]A<&fs13Y2N$LlhL@@.;_aIYSH\9LH
0K6i;NF$t.j\Q?Q^:d0r3:1+>UTlV^M_W"mt3O<>B+$jOR"de)2T7J?:I5eU(;_.PSGAm9,2
O3'@Akk6p'9u^Wn.#kcm.0bf[#0lElUHL/a22C5Jr^rc,5M*1Gf6t(9,.[J*tZ=Z4skQKo[,
`5Wja,qldi<MW2L5XlJ8Acl)9975*#tk$:r'9S]A>s/_M12@XhH:h6Y`LH"#gtT*4%b,p,>9"
PWQl+@`uS8=#,!`mFFs:TLO&1Q<W4H,t'.X)_9drW1CU#^aePY4VRVoX=N<'l<!5=<gocm=t
GXrc9=UHG3X06nkb>+72T"]A)YihLF2+/W-Mr8BS1RC6WW[^Q^r:)KLp+apg9<YpbZ&'+Le5l
Y\l1XlB_l3?,bV*4^+>^UTBb=]A,_g98qHn#Z#j70B7BoO\U\A+Wm5>;_B%,%FZgd%'n8l3;Y
E!EjG,<.?FuZJB<Vc3G(,PS^="<:.85-$JpWb4/>2"a&d0c1Gk>?<f#:]Acf6=U>'iT=G&"$Y
kU\'hKZNgd!c#o#3UQ>RY0jp8N\2tF[*atfp+3%^`_1f`""gMMl*9l@)R&S&V-KflA3s5.n9
h@FN6A-RMUD0!jaXcK8`r;K'Z"k45N'G&5.aTn2-6T"KCeJ;Rd9p_C>l9T3[^JbNV5?pYaQ-
c&&2Z70<Q<c8t0<OEo#_+aW!us;.LY)lloEgs'eEIM9O->=a:pKM]AnQCp?I;us09#:2YTGjj
C_r%s&QG5)8m]A6La$L.BIPbnj3<SJ`t$*$&K*L@i"!\2N'0Zf<EM4$o4LF.bbWKi4<h"`2n/
bPWhrPAJ7h8\[SNaDIGO:dg+$A#Y_D2+Hf>t._`8I>02fZi2(p\k]Ak(A35%nS.-/Td6-W3_b
+@N'4F#3kA]AWFO2G5meESDEM6j^ah>/h`I0e+KU%B$;*'ZU^qQWZi]Ao@a_PD2gdH>/[DfdZo
S"sH?<jb^T\.Y8%"eiY]A(Sj!f,OEhoWU.X=_;)7S2+?NL80*[^0?)YA(l0H._jY'+LViV(#$
P2DqOH,mq=-qR>NXu&FkXiLY2/K@d&\V]AMUoi#ZLVU'Bm0R+=u)(a(Vm->EX<*o/f<O)(TBo
IJQUFHeUJ>]A$IOl2Z*VJcDYO<D7*/\D&[AT;VbN-\nA;QES`P**(`Pr$[Wc5s#%Q<Mk3eC_R
2_7-&._`,gI,i(`^'bE;#Y?GR3KHTH3b1`,"*?!::Y_EgFr(#Q=UX$5oL>GZfZ':4cj0*+8b
\W[ged92W#p-ZDD%p\8jM*)X8Zp#T0(6rF^#fATf:fP;R>m.4#pebYgpfGpH6>q]A[MCCq3dL
Lr\h=gh/E'.$'\@[SHZN_#4)d)uc-jSFTmjc;u^mhSX/==4Xun'7NI!)Srht$PSl$(>_O&27
gIi$@R.K[#eus]A=5KX>jDeBhf=.?$8Y&I3VCHOc:0YcfsB>F<mj5?1^225KE+>`43DY.R/AW
^9]AdeB2Q39hdS<2!_PZ`sN:;oj360uCT.i,S[r>M$[>j!$o*B[[pVn4"3kk!KOZ]AfgrcfT,n
3sS%UmFqEK\<>rp=+g-4&'+^#ottH7:m4>rZ%dBMc+C(onIXqQp#g^:t1BEcLM[WF]AI<.gTc
gk6mDXU9rI[W"LCLU2@BnA=P0p]AOu34,O^u)[QKa;3U?RNT?5uMWb1t92$3E+kT^NSa\>D0+
#oo>"pLR;<PTL?@]AcI+KMUns*:at$,d,3)"Lu:9u&M&-GB>QMr8VlW9>H[>0.C2NZX2_m!GR
ZpX)13"C5(>ukf6XR;4:sbA4Rl#?Yd*Jo04hW&2lae%K5XNY&i2ps@m7r\)<3/h<VWne$]AhP
W&26g*9diXec;27o^%+gL0lXnT?kJ4Ze#Z[3f=UqK#Q]A23>kr=.s+`Yo1mOC&'h>rBMc^i(h
Tk7TH*YQBZ^7!p`?Yb+M2Ya7g$!^7M/@/"0ATt[82&F_!)IP.]AUUXb^5]AGA15_''IUDA,Lim
HrM/,=[[#r"QQ?,9:5j[sp%_PGX`;Am!;!*.3`KBJ6)Q%41<X+%Z)qWO<@P-[#?dk/9UeoFL
0=/.a2O(+W?U":@T_S-/J_(-UDMd[gTC2J8iQ\bD^4&"^^Q!CEjDW55mdS3k+5f`Q$ULTJ[i
^G!6KlEKFPbT8`dbV"5BS`1T!74$KD>0J4V+DAG>%-)IG`<q=!2\b9acUo//o3eq/#WG+Yi+
qk^'tcMYI7=7DQN6r^`u?qJpRhB6!%:;&O&J:MC/a0:MmR5_M7=/T.3ZKJn4'e4>#H+N0Jc<
Qu_S1r!4>W=8[94atITHs&/K-t@URRJdrE3#D8jAjlXZb.FmTYII`]A)0jK]Ar^..;n+ENYeCI
eS@Q>aS#HS&IVA/j)i6M?DJ%M+>g4[s4jX\o383(,uUJH^c2Tu%`#R:tl/(I_Er`eQ?=+Qg^
0p+Zub@U7'(/G;15(dR"NJR$0I^\jbZM9Rn$8:k>@!R3$DX*M7LuBjC%j[$c=g&#7'0c&*pJ
gtiYkLj_M&X<rj3mPucOUmG':'M9>-B0cS2iE,5hQlTP^@fB)=-W<L#\N_7lZQSU4N?m!=gm
bCB't2HIQEck6Q>qA(uM4*^0(lQ'E\UlXCPR7J?<t6rn%5[Q'j3o:0q!TIQo&j>Vpo0_sXE@
`4V2Jb2)"n'6;S3^m-\Ut4B?oe8G!R/_QpRWG@PGOr^*@_j:P>=2`Fr:&=j;p':a,E.dt%e+
It1GZqTR^0T(q#-O[ncWZ5s6oB4FKMBXq8lj=eM?16fGopTFYG63"hpaD`83QS9#qjGOCmXi
'FKLa/Srtu/#F?X(n)EB/bb>\>.)rIq<=N>:*+h2a(jR+Lt>%4^$Qehi'2@OK+mBoPq8JEkO
I]Al1%O48nf:7$r_#=8S,*^H:_Z%o'3E:^VMeXqDb;GC5L_Xl\83<AK!C(R=9"u9]AN-f'+80!
#Qp$jn>qi8("A&r+Y*/&,\KsA.e@sksqnA^s7#3Dl"#3kkrF-%ZGN0Y"T72I$q&pU#[sd-b7
%\27^P:n;ZZLDNrZ^k4"b(?/R4o,D050jKX0jlc;:KOSX21F3.(/DQDG?!iF%pUJd.BugggW
#uQ2ljG:CDTZPRSIo0Tu@V_"@EW&oo1EiA6XWou_@[CBAg4T@^82?H1.6jIKb.IbI_:[Ylek
)dbOTS`.?Gc,cC,b"BMFBbj+T;#$G;anR`'DAcMfbF"kHFN<so'X(&SVFc069<(CUp."SCr`
i3lD'T@h/5t;qNa@aSoY9GQ#fUN;^[,T297O_cG&mO<?PDq^9<(5ge8)=:(!0ZcX`a$`fC)3
eNfODDEGZQ^69A4Cp[NZDOs=ESUY"8#jp:S7^_VVTFga#&]A=l)cGX&.##oIaEgu\P;~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="171" y="0" width="171" height="115"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0_c"/>
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
<WidgetName name="report0_c"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-14340777" borderRadius="8" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-14340777"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-14340777"/>
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
<![CDATA[304800,2019300,0,1524000,1638300,1181100,342900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[419100,1866900,495300,2743200,2286000,495300,2095500,2781300,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" cs="3" s="0">
<O>
<![CDATA[ 主营业务收入]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" cs="3" s="1">
<O t="I">
<![CDATA[247]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="月份"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2">
<O t="DSColumn">
<Attributes dsName="内置数据集3" columnName="成本"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" cs="6" s="2">
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
<![CDATA[主营业务收入]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="2"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-15591374"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
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
<OColor colvalue="-9864992"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=900"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
</XAxisList>
<YAxisList>
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
<Attr rotation="-90" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="6" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<NormalReportDataDefinition>
<Series>
<SeriesDefinition>
<SeriesName>
<O>
<![CDATA[成本]]></O>
</SeriesName>
<SeriesValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=D3]]></Attributes>
</O>
</SeriesValue>
</SeriesDefinition>
</Series>
<Category>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B3]]></Attributes>
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
<CellGUIAttr adjustmode="0"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[ 本月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" cs="2" s="4">
<O>
<![CDATA[ 847]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="3">
<O>
<![CDATA[本年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" cs="2" s="5">
<O>
<![CDATA[  2047]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="3">
<O>
<![CDATA[ 环比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="7">
<O>
<![CDATA[ 12%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="3">
<O>
<![CDATA[同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="9">
<O>
<![CDATA[ 0.4%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6">
<PrivilegeControl/>
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
<C c="3" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6">
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="192" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="微软雅黑" style="0" size="240" foreground="-15680447"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-6315084"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#,##0]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style vertical_alignment="1" imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n)H]Abf.C&,[50K*sY]A7Sh?$H'm#NO%P[lT:N^M0d&VOMX/dc4Is
Us?p1/m1(;BhK)`-U4@R'F5@>2?ac\>qFeg.6i)T;9d#Q+2Y?)Y+h0PNSr:isVIl*F+.U+qF
dUjYG-:6>&2]Am8)X_GnqDqgtnJVi+]Ajr[=r]A>f*p9CsgB/%La6t3]AMKrrbsDs(K!0ZEIm5EK
.V`FN2NZAQ[&.RNahL"olF:!PgBO5~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-38294"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style vertical_alignment="1" imageLayout="1">
<FRFont name="SimSun" style="0" size="104"/>
<Background name="ImageBackground" layout="4">
<FineImage fm="png">
<IM>
<![CDATA[!Rq*"s+sQI7h#eD$31&+%7s)Y;?-[s%KHJ/$4$h9!!%]A*DoVhX!1QPJ5u`*!A$CA<Djg"1H2
gIQ5WoMcn)k)24duD,=A9pG+?^,U@/jc2U[=iPrs7@9ruXc`"#@*-1p;\=D=`&jd"1B[D<sN
#>f,O>9-?)/)SrjGT!)r(E8D*69gCFp**,c>-SHmfZu&)`?;2R3s8RR<TFLd[Ctf1-,H)2jl
dL-bFq<cN#@emG-quC's(F\D#ih'Q!2;C.?ZD/)eGoRL!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="104" foreground="-12721762"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[buf6r;qJIT[8IG8%UfR@<Qbfn4Ca#$+J?pqL*-ai+U3cG;h?U@'H%oVAMeoc6PpJ%A<9W>Ll
a"d1.Xffa:-'66@c_$Y6aO]AD/JaT^V9;@p>0Fnn$U`U^2NBPIGEOM&0qSAj5Ue05B>,:O7*H
N5/&\ZCuP\3_@IV6oC;2W0gGHHI7W'k3hk!U+gcNlH2blO"*$Fn;7"m:lHLj:A;_G?I@k[Iq
D,S,_GBEca/S3lB?oj@LkbT"BOC(OFqN!Sn8mr5@I9`Ch(")&)2am^Nm3ktBp-&F'1gW.22R
cN^bOnFhB]AAR?(l'nlHIMi8%"koE6c</I;u,cNR:PmC6fT\d+iZt,Z[ed6"WGg*OgIHW5>j'
q$94U_!(`9\W%CKCmKre1]A='kbl'>ohUUjt$`Qt_+Jok!bEWY?nBeLL8TdmiRZT#I<`Ct/Z1
QV?MQAWk6OJ0#[V6+ir\a(K^V&\XAY5(8YAq[CMS"t*h!'UgG-csA5[8hPbW67jnA^k1]AAFl
SMgCNb4i42`-dp.Kc=t?kG+mp`(fu"Bjn,ZK-L.kG#%p012]Aj?6P)#7(?>ai85F'`E?W^)-d
i*S"k..``ZAY(%I#T%!m3k778SRbffO>g>N:!Y;$5?3Kg*MO!boFn;/2;N:XcGH0Zjj'A#'&
mda4&o@!'=*FiTS#H-P2W;_$"pjq98>P=<@WjijksV,;m\mod`M;BhA[i`O+JT'l%G_ng,h[
UZ$V88GQ3Q)d#U4Or!#e76RBmQ;Tb19X8cO*>O6G3np0cluNZjR;Fo7ID)X'nQA@VF7NH&:0
jTf%S0D?7Uk:]A!t]A&`pg`KbZcU)!$*-'E)'s,:P1\]AtcV_A+YgSSDl=h;#kq'*&U\#QlA/"]A
^T*;-k5JE`i=<)nM-%:eY=GcSiE6>?0S2A8ad-rXbff'g7Jqo'95CDY=Z\.]ASRjIBN-,CPV5
?rq2AN^[!+,:pCLC?e+"L!3P(1E+#hW?[2.Bb02WgOU?nfR]AoX:A%<,QBbnrF3IPEGR0b+_+
nG8,o+IA1b\d2_]A_!gYDQkp^IZ3HO,(l&rLUSP5F18m4Gd9M8%+MBBR5@LX!]ALUKn/ip:Taq
jljp=OFn/3o=AMSd"g6=B.bt+i-"5<i?E,_T,hMM:SBb8'8T5TpEAuT";Qc:c/^278F6XM.L
<<?.gSejaETc/ER;5g3#jn9H+*==a&=9.lUQ$H^7QrT5_rQ>=o4eN1Bt:!fsD$s#eU8Cl<SC
)+T=?JipKj7D0b7M,PInrOlCNmgZJeVZ/,Y$V%G<Io%_:ur-[o;aMcs2=a2X_aPB!g0cnm+K
$5&>"_(939"UGeW"bN"4'BWtCI?lD]A%Gh./5NPp8+lQu\J!Lrb]A?iERcEQa6_p72/oEndLcO
^;*,)PsKQ)-G:-j.K%lC\b4JY[K"quj7NSSJkArLaL<Y'V%*5=nEBEE[9*DKPR<WLHpH(rW0
'@#'U%\"FrOH5O]AamCa5U_^G;e9=,TaqQ7sgg\pQlYP(,@F9:;oWmR9c,]ALWM^!'nB1NQA8#
m1C/!3BHdthh:QnH0j.n@VZKS0p2:rs^K>GN=3#,G,t%.2Bum>85.nW-ZnZEg.G=*(\Z7MdP
7*$&gqbTkKorEU/`50,\8j*T>#FVh8d9:-8W)OKSq&l#RR[oFWtH]A9h%oE>NDC#YIfd?F6!H
EOY\9F[J1de,'s=aAm:T''CAmK?>E"Lh6&gFD9iB`.f4UnmK':Cn_W8U#FL6q9Jnkqk@'=lo
oPU/F2:$4g&Z[S<;HN0mEe:<.aElD."?Y)P0G6"gWe0\.:t*clJ#=sQA#<brKJY#pR$%-KZE
Upn:LH*eNfMJR[\H"@hTH.-5SdjneZg`[YcZ[K$c^ItUG5H%<>"GgH57@Eq'R[h@74T"rOYo
('l)m(*5**Xu#cQPJAVh`]A$q<aY.O(Ml%22uT$(M2S[6d,/&^KE_eNOo?R17gb*S\`I[^^7N
830O`D4+WU=CRS\H;q!mJ<&3RNhaT8CLU3fSFc<H'^Nj_:["sJk"gOg_Zt*_9'ZPW#:X'(r:
f'(0.b\44"a5IRDEEEk'*6OQ-"+]AoD>i-U]A*SXrc>7fU4t$t2H=;<9NUul3NKH/>Uf&a`/o)
Mt)V!1"+!5LY)XupO(t?s<%+=krHDYZ]AG2e#Q_YqWUqaH5=:=]AA[UCjdtVPU(qF1d?O@(aHe
rhp$@okOV-Ge$L64Yr6qBo\l8h\-u?_aR@6jT9FI9g3P3YTt<8".#R_klC^@"8e.sq2TArR5
1>N<cHqjO9K4Yh*4"WP82Y7:S+ST+#4Fk?0mXt'/DeBY*uqN$sQlPc'Z3COCu5aqrORaKfU,
am&&J`0c1ioqqT;!VL=!F2>^3?<YW`S'^uh6=aAML4A.kX[3i08ilR!!?-Zn0=f^;"a$=UA!
Em4O`h8'Q+FBHp#KliBiHCrIEC\QZ2:Hg="EFl@0BX*WKp1\3UgjTsq+C('J:tDMZjY/Y%o3
DRDD33b%hNqX=,35u]AT*$t<g7oVg$ZgkJcRm[R\Fl3Djq<ODKc4>?=r]AsWJJ+MEcTQ%2_aCM
6.^O4*lmEIm-!IaM)p'=ne[DW\#'Ajk/[i[Yk7K?(^BFK*25_?:-D0EK5GjYpc'6EH!pY$2'
RmQeH+Wl;!p*^UQneMH[*'#^<Rq/hJd84&YI+k/kEgBVq&-_cAr%f(ta+-'q39]A'L_Z\alp7
$C.*<>qqpS6&!")GnB`)PdHFYuW3iFX0P3S828"XPLbJP?b=$Yl*Z#.pWeI??MSi+TVP]A=W4
2pJ">gKmT2AW7B(`L\-M`2IHTq&,0&Wt'GOkWhWNiP$t32-"N0s#_]A708SuMh?#TE<AOTia4
X$6(ah/`=YhDNO#bM#Q`+q]A2A;]AQO!Yu&@:!/%5,W9GID-&KsXVKpN1UXe!IXW%*0c5(V5@a
(g-W^e?OYI%=GFj67$*i8I'Jf)ZFl.6/"55nF0&SMD/CV1;<7e]A2J[iKT&>P=L.K+T0LQI(7
qV'#nhV2gdH@0H%fa0!bPN=PZhG.^70$D#T27O`LXrsU0FfFK:1dHhu7ToP$.iQUE$pNi)ik
^(\j9W0Vh]ARX.FKC;!K)LVfm*C4uApP&RdhXm5coT>BFr^E!X?h5bb3Ch)h+BWKE[g1qGO/4
WJ.UJ+lJQ^du;KBfKb/0<Y)8T`NRr?%\'qA;IUJ[Y,.%c.)PSiF9$+3S/u.IbBj`I'M;\X@/
OSrCr,)S6gh+qf'4k6T2H'=[`Zj8[>oe"lR<r``$*;Qf']AkQ=3qsRM'&=2%4qXi/9'b+prt:
n1n['A?_NoC>4ok(EPF6FGa"nXYK2!nmp+s'`iO=gAI6a>OG$h;:FTqrLKtc3!JZ^heVN=*6
tqI-4([Bi3>_F"WRAW=;Vl"m$3.A;JGUKPPu`-0DZk6hP%n<S.tO/FNGO?m5)'KBMeZV,52R
abM(t-=J$h-;;tIUl7?e`Z+Qs5`N@.HZcRKXoBbr9'"2=bb2l7=g9sU^:g;rQd='_+hW'DQo
PsZf)uFTNK.;%8k#<$_,Nk4rX<@(W==R2ak^`ZO<k&H,pb8hQKL6]A!=Z/2:r\3&,?)EX+%(D
`'Z!#a?lftq,2[OB(%NAeL0*T(7),;3B4jqIR?3NS=-Zk;1d1A!J^L0QHfX2Tt>s`$s)i06B
1Th_8'<Wor[9_4MQ6A!S9beiQ7r3hJ<hR,92%-trT(@B=n=`4I;GQd<otsCRkSMSq@_-n#*n
']AL%o4Sd<98sXgJc/\/4GR\=4Ig%^CD\OX/0r]AFX9uCJe3g7K)m$%O8$sFZS&)B8u^M!(@(!
-i&5Q"_`$>E9`g%."iEE0mA'>JE440'!h6?KoS,cCS>RqTZU`;R$6.ufoA0ZkGuI*ENJole!
A;6..54X8NH,`!lU'c/<Vi%Ib2p.F)#$=Li!4/&,J>0%qh;</meRh34Zm]APXVQkTLF.Y>bHT
.bM:Ka$]AfDF]A`j(.Hh:&YS/KMaL8]AY>J%Ibu/K20'chGKocfjt7=\>s8O['Fk'P!PQO+Ij:^
6D6UZXD^\4K=s8:Pp9PU=3iLI.5T=44@tJri5dcoXEFpX4qUm/r?CU+rK6L]Ah;9:N#2$i[</
ML\_BVl]A7M`9`l,P]A@*G8M3rFl49W]Ah+(6B!>iXK6o4n$+s8B3eerf%IM\&<k780(ct56csD
1(t#QqFkoD<lT1lma%]AVrY8ECU(W2*d0^=:2Q:V%S8GdN#mVLpE3^)uD<*i_#m7Z\Z<N;L:s
#hM(#;EnSNtsA^WB2Ze8_GK*KqcYlV!3='.K33nj(B0<h*'o0V6IL7eAm>p>M:sMn=+6(i#2
Z)en<GR:MrABgD8O27iMY[]A?K)rD61d*2SbN(SXB!a;Xfr9f;f;\gQ>=:A7Yp2/A>L]ApJ;IT
b8_l5iZ&MZ`,df&V_C:7=i]AmuGBp).o[#bhdOJeDckkNP>=>M._UA[f0'TVfRg.Up9ODh$F%
plP8s':"*^=.aW9m%mO"M:)UY!cXG;E+NOf_;N?7KhhPR`%WQ75pP:jTrnEr"DV(6e;D0)Al
1F=)kAN(^=MC7=3-Uu[A6G2R6FWMPL@H+tIPHZgRE;+ZVSF<OcQ7Enc>RWc_jC%j??Z1b$F"
?5Gj>!t\QC0`kK[YUg,Y3-=\d2/3?qqLO4^h=UObWRX)bK6VO9UG02q:P:dBaI%eH,WjnVi!
Me0e?nZX,O4[dXbJeigZ5QH;<E:lBMr;'YQ?:*FqpY//`A0c?#63(u:*c9!\31:EeQ-*=b*I
IcqIoF:Vc&^0]A)RhT>t#GfbN[B').uabrE`bM@A?Aho^-r<@D)79C@<oc@^F_/\@l4EE>I-l
&acVc=r^SaWCcf%YP5IS:KG$pR5@q&EH\2Jqmr`E&qK4aiF='sja3dr!7ZdLk<g?!W4*P.!Q
_]A^=5QfVV9P0m@62@u38/N@#s*D:4,$5St+<BrnMK^2ttKb7H&..g?d<1cb7,SIq!BrDYQ(H
9+NX3.A#0a@[TLS//BTii(^\=60Vn`"aVtMP9md?fG0tg*oBtNkQ\b"POV(b9n!P41p6pDB7
up'fh+u4*F'[c.:h%6<$U:LsZHg9sVsKKh(]Ab^mHf-\Yq'!B6`SWjjR>(L\_26NOsQc5=PW1
B_c(f.P!1d1)Z;Dm>.nSF%nH]Af[pc;mpV@R]A4+N^H$>R:-f"6nic[b`%q4c6VkKHDZ^ZAN-;
]Ai]AZH"B>94t=&A[QK9%"Z6MFMU8Tb\O`PcK7kk6#h,2Njak]A?)M;)d6*H,a)$u]AoURZbT*]AJ
J>mL!i\Z>e\OP$U:aO)'#jAQ8lhA<M8H28R&jFip=TVrYD_gpe>T+N39eEXJr9Asom(popUr
RjZ>Wj\f>Lj30NJLWDC%]A^1'[dhDc*E>78qsLq#2juh<.?bRQ8S::pJ?VkX&A_h`$isPK<;/
1.%sHCmgSjM*hC3!STHcBF<Si?&l/162;STSJ9?`3"k63O`AYJsgNbDL9h87%rX^%Z)2@[7(
qj`bUqoFbVJDpRKIKfD.\?mCoir&T4qrcd`3E/ND0bYG1;r)UPU/H]AJD\G(Tm-L$2BSF8M&e
GRbZK'5hBZ'C%W<H#pg0'cR+U'%*J[;'=a0#q*!OoqrG]Agqln.#Wt\]AoKVm;Ea4O4*K*g^/u
AB*G@l)#)Fc,ERW[]AJ&`h3%m!&F^Jg#rR01rj*+QpePd.$-a#CTWt=T[:=)#*RXO(lIWoM5c
Tlnc42_Z&P8=YqqqF46DPmEYJ`W`L[,f+UqY8Ds'1"eKeLJ#4/#L,NIJRn0YHUT+"&%J#:bs
MuO0Z^s&`XOfcA2@;7\M0*-4]A$g]AAGm`g0@i.=kGXb,%k(:;)A_uh(hqV!q"Ge(Dh5bNM672
-u!k!i%?[.P+)!K5GLo*,LPOL-`NWII7m,^3BBd\V@MSXR+X<&kh2q',fBc'NL>i8SjH\+7H
8DF0$u$0(RVK;O!%]A5=>Db!'F4P#\2WE/f):+T"m$)>1X[_!Z=mWr8$%[:)(g!d(ch03AVNJ
U0-`Nr>!kTRX!k:A6BfK6&>;GJV*EQTh,MN,Y=0kgNm(OPNd5fg*H"l6Gm[n:/"+j_EE>,+?
Wo0A@Y%'@9dk%$RD9@!('da/7EVK(QFf9ge^2XmL_M4unmZ$jl\Mng^1dfZU@ZCo1&4Mb`'C
/*9o62m3lLLCpQ7SQMl,5pLau+Bq.\?hT4sC=9Ga&@\5JZQ\38bV0Sf^8q3\W3e@,&"Mrkbr
^sf^lD9cLX]ATrXS#[SKE7+k3jHL):u&20j8plBm7>+<0:&+=^[p"3n#i?o3NAAL#11^l"lEn
b[lZT\0F]A"/Bm9&_Xfr<n5_+tPYlCm8>P,mt*K^CVNtHRdf6N#r@cQhud,?t.1Ac]A`i&T`IJ
q<YVKHU5eM3,VDA>^A;C_5jnYcE_d`.7,TVGPg'jPD!ob?]Ac6i\dQWCP!L6BknX16rHGZ+]A%
A@,u2JSZ_Ltt/mci>:)#P%]AfQd_287WN1UX24@.,3JI-qep<^#-IaSGBl%tj>bTN2]A&NYk-k
%FdJj1E+DGtF(J=\fJDS>+D+>,s'r!l&&6Vi><UhT_:cCCN1.*^)p1"RuKKPt\II\k`Ppq.J
UVR6a3#J[eQf46a8Li'`\I+HNnW,'H7YOslm74.4f*LrQGc@on5Y!e#m6CaLACrm'7Z#sd.7
F/)B`YqSYC%A*Rf087Y.r6gk),T05Gg>`ge`47.qkFcgABAbmUN8hoZM#rIqIX*"UGbsI\A5
"p%;'`c7FVs8_K6.<rlG0>i#cbT9/qPJk=\(IQ[ugM4B")gAMcsB,ueh;`K'k(\"h7;":I0X
tT_45h4q^D]A#'Y?eqEh1Mkq<&[sf,P(2db]A1[[/Gc^;Id>g_h=XfB[X!?iNBPb(qfHSZih$Q
P;lJ/5$Ximk5L(_ZUreR#e]A-'ID#BYCuCL(2sl<sb,#_.bjCGtHG29:-25jWk>93^2_@/VhN
2]AgVeT6qt+g_YZf:MB\ffktO0(enLWCYnb;U;4IWUECoe&9D/#`2_uJ>!OQRX.=$W+Zif0ip
5Nc,/^\L]AIc6&@qbI(^e+>.rZ.gS;Q5i3E`aJW,XpO2oJjAcok)1&S>U?<49!R$K7bFQb(0"
X^tj+?elE'W`,jk4f@tF:]AS?kml!?;4FVgC1nuDi--8,LQ<2_4__-_tA"kts)mAn0&s.h#UV
\NqeC]A)(lh*"G(Rp&0;X*16J[UKu/Vqb=]AH1f8o:n273cY?"fnTUg^f5eA6&oc"^7!M:#%bV
sLr;QlT\mFf(o<j%!ZiZq]A2&7FN//IST-`_q]A1f%E]AhjU)6)4[!,IJ[Em\d-eZo0;JZ+5lYh
`o&d2Kgmu5H9^SkSU2ga;u*R4Ab*!NT=TkDP<o8sXR6QMSTXt_n*IOJ07W3<,9ht\BNs^Js,
E$r+%tSPn,Dh"kfaD.hi%=o&(fJ_);u(cc.AXM,(6Rp;*'7=6DN.2_>bT>Mb"VE=K^)<4btd
,-QO,UI.mU"o*#jN<6LK@I^bc[:<3tZPd;:*I'JY=`b'!0B[n>.;!lgn98QbJ5Q*B)C6^.F5
p:saNqd:H#,UF`,N%l[OGPZh\<g[8pXV88ARa#HB$'`7Yu#E(5YBNRXGCm--iE7`cBp7Zla5
!A/!arqda7N%SH&HJ";Fr5gA7F[blFH+dRpW5/sf^8PQJ,L_L3pk"8[@2'YU/0,h2UgXDsZA
^ks?U<WT!#b)*nJX;<a*#&V2d\9.c-8amW6E[tKmL\XXXeqa*o%)VjOfRr09'[#pKWg='tXr
633T)(--^hlJ`fUmYq]Ae(QlItIYc<n;g>SNF3^en)UWTqJsl;oq?&>S*jJ_Zp.RGQTL'g]AN@
VlY:l#!:_]A"=0kBeY8';E?0p\JYK0?T3[njto;akac-u1koJ^oCa@nj+2d6C4.6X3pqu'o9"
s\g!$PM`f`0H.N'(mV(*'e@6-G9VJGV]AQc4s09]A+0n(4"S)9WIfK~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="171" height="115"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
<Background name="ColorBackground" color="-1"/>
</ReportSettings>
</ReportAttrSet>
</FormElementCase>
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="171" height="115"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0_c"/>
<Widget widgetName="report0_c_c"/>
<Widget widgetName="report0_c_c_c_c"/>
<Widget widgetName="report0_c_c_c_c_c"/>
<Widget widgetName="report0"/>
<Widget widgetName="body"/>
<Widget widgetName="report0_c_c_c"/>
<Widget widgetName="chart0_c"/>
</MobileWidgetList>
<WidgetZoomAttr compState="1"/>
<AppRelayout appRelayout="false"/>
<Size width="342" height="582"/>
<ResolutionScalingAttr percent="0.9"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="5"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<MobileOnlyTemplateAttrMark class="com.fr.base.iofile.attr.MobileOnlyTemplateAttrMark"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="1443389b-a741-45c7-85fd-30c62cac6ddd"/>
</TemplateIdAttMark>
</Form>
