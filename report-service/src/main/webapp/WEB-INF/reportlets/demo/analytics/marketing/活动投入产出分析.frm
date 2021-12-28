<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="File1" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[公司,,.,,日期,,.,,流量]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.Integer,java.lang.Integer]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.Integer,java.lang.Integer">
<![CDATA[<Kt*m"YgES@o86YnZP#FM-iY?":YnScjU>D0$oXGd8bG2[Nsb:hYYdhn*u+!Um1Fh?R1IbOV
,p48j%\DE=_?Q/SL5:cm7!]A/fB'U"8$T+#qIG,)B"LSQ)k$F1)Pj0<NJ<]Ain9_l8KgO`1kt(
j`)D7m`6s`F8`BBknnQL17]Au)\_%j>Q+I-#M^Mf`/cgU;L7Z`_V+AHX;W32LGH"^$\P=a+lj
G>U5pV[smP?'qO+WPTb3;N8mE-j_"QDoM-g),D/&NA%Cq=4flmLf?-7l<QVS+BUBVfIhIm[d
<uNs9=.7m-)Y%JmZVQ20h-qs2cqNW;@ARK<C)99;A-%e@Xg5s<Ud4(kSQ&toE*8R,]AXWpU2?
4<VGN53mi<-bIbjHE96;Q&TjuJWi>jQ*7*A\GoV8FoA$Ek)`gk*c5FQ4fo0.5WL.BLCq,3$i
^5~
]]></RowData>
</TableData>
<TableData name="Embedded1" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[名称,,.,,金额]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String">
<![CDATA[He,Fu`Kmo-G&DQX54Ra/\X\s$#!fusI+P*j4g!&AVESj&$C*>#!m<^\gA%te+%=2j$mYoT~
]]></RowData>
</TableData>
<TableData name="Embedded2" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[类别,,.,,占比]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String">
<![CDATA[Ha7G_e&dSFFu4.V)K@okE-Cnn)T+Qs3]AW0o!!~
]]></RowData>
</TableData>
<TableData name="Embedded3" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[列,,.,,值]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String">
<![CDATA[1ZYnsi%j:K1&q:~
]]></RowData>
</TableData>
<TableData name="Embedded4" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[列,,.,,值]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String">
<![CDATA[1ZC5KiA0IN0`qC~
]]></RowData>
</TableData>
<TableData name="Embedded5" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[列,,.,,值]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String">
<![CDATA[1ZUAOjYGgP1'IX~
]]></RowData>
</TableData>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT ${int(RAND()*1000)} as result1,${int(RAND()*500)} as result2,${int(RAND()*800)} as result3,${int(RAND()*4000)} as result4]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<ReportFitAttr fitStateInPC="2" fitFont="false"/>
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
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="Al Bayan" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-15197385"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-15197385"/>
<LCAttr vgap="0" hgap="0" compInterval="5"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report5"/>
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
<WidgetName name="report5"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-14669005" borderRadius="0" type="0" borderStyle="0"/>
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
<![CDATA[1728000,6019800,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[15163800,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[活动投入产出分析]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="true" showMutiSeries="false" isCustom="false"/>
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
<HtmlLabel customText="function(){ return this.category+this.seriesName+this.value/10000+&quot;万&quot;;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-16744320"/>
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
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Al Bayan" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="false"/>
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
<HtmlLabel customText="function(){ return this.value/10000+&quot;万&quot;;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
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
<OColor colvalue="-907154"/>
<OColor colvalue="-15872"/>
<OColor colvalue="-8202753"/>
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
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-14075297"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-5197648"/>
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
<newLineColor mainGridColor="-14141347" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-9273712"/>
</Attr>
</TextAttr>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[¤#0]]></Format>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="false" isRotation="false"/>
<HtmlLabel customText="function(){ return &quot;$&quot;+this/10000+&quot;万&quot;; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="40.0" fixedWidth="true" columnWidth="20" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="名称" valueName="金额" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded1]]></Name>
</TableData>
<CategoryName value=""/>
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
<FRFont name="微软雅黑" style="1" size="128" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<j+_;q\bU-5[,HbUCGm3]A[NR#`+"Qe5;:$TV<W.Jg*X`oFaK"5t"+?H4>Ze.A;KfTI<?DUd
G-E%\Z7?KI#Fb:/cc)GON**SGR`p*uf]AFBCE7Z>G#QACG4Rd=sl>_G-M&J^iAW]A5@4D@%4D4
,ph\dm[O`Y6gB#)RqUEh]A0"OI`]AA9RuDL,$gr]A!h;mEBLc^HpiK7(X5:C?LC@=P8<I[?!Y`9
F<YjWm.lb/T]AjlRiRuo<Glq:72E;LoAlb=L3&2ZMt5/,2Q?NnP.b84-`+fNV[Ja?Z.>\a09X
"5mX8b[%i\``0Vtt;YlC+^9(L#4Xbsf^dlh\gn+P*$_'r6&92UT[af0Ho*J3KD/a8g12r8s/
QISLj$UY>u*N@rha5t)jFHZV*5CZA`&XGAqi%Im]AgpZV+Tm(g,UZDEaLnRjV@6>[Js5`=+S;
iD,IpC8"193@k.3B=C&iSolB`6s($X@'+r2K!6NPs0>"YZMEgULn]A"7"*C]An%!@K3,++l$:5
\r`O/8E_c6l/=I3tHDqJGqbT?r/bqceH"rZbb)E1A>_B%Z_C8FH8Rc]AHcZ4--og2I+-U_P1]A
Mo<Qg,b==I_VW'4*R$tp"8En7)m;Y"h?IuSsVa1_Z*"YORq;m;^heuMRj9PWjTUf5\qUD&Gk
UUENR2Cs$[#Ns43/VUlCj]Alq2NbQnkV9QSLgj7a&KRes&#]AM39qP145cr?6<tl+sYKhN(Iuf
]A`.*sroX;R/<;[c68=ht`Vk[ff<H?o7Egsa[A?Ydl.q?9_]A?<!43YWqUS9F"':sZ!%[?c6[m
q7!Kd.uB0:W+d\sa3JI(Fd]ASDIOkSO;l$8S@W*SD?,;\6i)nJ_"USb@2[/',j\I#ED[c*2Ui
I;4a*?Z<e84cA>)o'`):K32k@@YP&[Va+;*Pc;:-8E0T@_`bT;t0rLC.VI+F-OB5ZZ8KD%cH
XR<DQU$*^0a&7S93osI?i4GpBVK)rmH3>VThYWYS'PfZnI&?Sf`AR;H))<9,J>nL60%Og^VH
S8mR*C&ngc_KZCOd>K7@EZ9THn;2N`-14W=AD8<D?1lO,)udJs8UWP@3fDZ>#EPZNL.7j8_!
!5a*;IB:.:>HWlLiN*Q#1OrXa(<ZEh2ICJ(S&R#F_0.,"j?)E?!'%ZEEpGMo&iTL!K)m0r4p
5[&1i?W,1[$&7TYo"Qh>VWqVb8+YC_?J2ADIYXNG,mA>L.dM(HD%kYW_A'YuK`Zg<?gFJOOr
DqE?qh'W*pJg6r\j/G?,$QP',;F?dqV0;.2'J=%UK`\.6:bKh/eET<T3j<&SK@:*OJ#QVB,#
B&DpONPKdfRu8nH%#rMidYEqFhSRS[j96+TR;d+]AQeQ<o9gg.3O-e0-#FhYRgtVDT<[Y0hKr
A\]ANIbX`PKBA^!WPW01pS.FN*I4W)h.AOZ^%J(*ie5&jjgmm!<2dGRh<&BorRT]Am+G^LusQp
nh']A0XIH2uWrD*Oc,a%JaFdd,h*lrR;5t=n^:#%/4l*G+,XY@9@f3.>;m;i+m-*-mnaW,_I(
N+j$X=XF\%JLoDQGr(*VW`s2LogkEback7HO+ifQ+)j!8sBD)!]AK,l'BYCgg)#c`Z_p&oIfc
E#kki[s5V.j1HS!9]A6_RTgViao_j=Z2X<OM-D0nkh#P6H0?0bo>ZI7/sQD;CeosdfRp$n-n4
Z^t^2*.J7%KPZLek3$P$^-If[_e>A%j!hm+_cnGN3aO+YcV+V]AM9;cq=(*22i#i+8bD0-<^a
i(4QC8a,nmaBEq8cg26dg<e7N5Soh7'Z-8-u(mZ*VF(ob6)52!FPpR1QnfWbgrG.#'6Q#ClJ
H(U-cA=CEC7^G6oJo,1LqMMsH(4+h:b:+MRUN(?d=1sVK6<F!^Qh'8L'.B\#`N$?1Pl=]AY9.
R'5Tb&9fX+tLs\I0dY<&G4!ls=HP-9XI0EDlJn`Bc`e^e.0<TrG"u*E*(YWK1*"0pGcXDDR!
AMo-[m@l6e4EYYh"9N=oWBcRF<]AG>]ALpn#[-YJ54Sasag)['Z<(cFj\>TDG_[Nl<[)"?Fumi
mgUOL+7k$etL[580Sk10pV>9H%GFiTk!s?iG%M>@d7'(_I0ltCh,rPb3iUblEk.YKp(jq9oR
eD^8O0Ep$,WeXabYhZ(6#mmG5BuZ/U%Ol;6JNbKl\"P-2ctib1Z:7L&nCP3:Gt,u"BtBBF5q
eZZ$kIN2V.c.hPZ8P"[#9A8@!kN(!]A*b@)$@`3<*JeoW.nY*,`dVI6gYhfnZr/5h<>F"PfRu
cMqi/hpJ3.m\&Jt&WRH2.RH%u`3tK8YT(c=)9=q/2`Y!%9)3"7JI%&K8SQ[\[E>2DUd^`UIt
ZgN26n!q(XA"pUI)r]A,K45aAGD+V$D=!-lAm<]AZj>jn!C4m=-^JEEOh?I!iU)M"tPc)I+1in
kA2%Jt*)*g6p3>m:V`3fDCtp3Ql"1_i\p_0pbB$8khn!qW4&N8mq.F:,ul2XlSgQ1P%]A$>o9
O"nbU:QF!dCJ"-TkqUr_K&mOg/TVj+5/VfGIVn"*s%DJKX)Tf/h4]AVSmh^W1`ODd'J@.b!D;
b;0P6YLh=T?&$1mf]A.WD-[OJd%*qYu`K17X2++!EP=.2'[cE*6-##hKHfWD`k`C[T^^K7BU)
,buQj=YV(A+lN_@ghM/T4u%M+^.P+n&%Ni[=k^F5&Tnho"MPYAJ=(/`8_h,]A,B[4$_SdE7eY
a=mAbV\Bt>P&rZ>OEn]Ab;;r2.n:63:b]A:ocpW7c2('?**^D8l$Bo=__]AXr+sL(GX<#ED7e$&
1A6BoGVSH(8S[(\U?S2%gh2lp0)H/]AOe:YFP-?Nl_dn^D)iVZ7g^pi23E?a[fqWkBhNo,3J#
RJZjLB@D+RHLY3`F'13,g#^c:ZI;J1Y/MppJ.=Y!.g=l$cGGV@"DmGST-MH.k:@U[nE[$t)*
!&'jJ?+[sbbro5+:Vbr.FAI>`Xrtft$0c?5H3^g^,GV?s.7"X'M(>o3\l1,_5i=ge)'YVI'i
fd+H-4s]A["qKLA'6QQE^q_'irllBU;NI+]A6:$%@34YnhC?XVF(^lt'aH'\-Xm?(Ak-"^?@%=
*V(Z/1i,a7J?G/CT]A<@`@P\h;?C3]AQCPQW=SLSuG9>=l"u\sN"oe[uiUAd*$Z/B1Dj`F]A/`1
Y`n]AcUc*aTAUi$WB`=c21L1PSc3;EN=1O)Rp;nnW:3c.-4r<Rn_?g.,-27-kX/4nIOd$Gm)X
@#FU$O,#B^I&UuF0OFQD*5(Zn;[S^hR8UCkdG\Aua#7UZ3RlC(Me^[W5.Ep8`]AR<%_$Pf=-;
`M2V'!dN.!^--i:5j8LRe?Z"RX-a%V[2^Yns))C/bFq:Y"u+S3r1]AGQG%pH[f3Pm07:),)]An
!Y-b7RBLn9Q)t[/b>PZ=O?*PdjEnX<KKkADuPG+sp9R8>9pIEAe7=.FYkl"ebBDSU6;Z*8Wt
s%<0#/0ihS<#F4K:gY]A"O7a,il?3s["#^p'-F<l00bq6KN!SWpg4iKQK`sN6SlRiooi<Zq#l
I939W3"n(ifA`@IHBFQ=k[N6/;`7/OBV3mQO9]AXe)Ut9do+k#ik&KC[T]A<2UmQ@]A*4U4N-_!
b=]Ai0\10/\NO3[eSm7Ps5%8isn'4V\\ZOEoQk]A"41oc'Bq5/[WnEWiE(()DY!,e@YA-+#6m`
Fh>r/ZF?_9A%Kb\qMo=d[r)rg*'W`eOm'3o$GUb3dRIRnfIkc?11.HBWY"`]Ag?"r7[=@2l0.
'LF6deuNiYdaLDuJT_U#JNE0@>s=mt0),:PL+lg:c0b4l)T<Y%+G4%h?'\#UhsX"m,6M=mDC
c`CD:2J0Gj=q1DJV/p_\PG"C<[kY%,s-YQi;eK#F"pN1P;[u!(@?;2Z![kgMlMb&N[6M3gXX
WEm`i?T#u6@4)4A(%^Le31d+iB3:&RBR"_X_KAdp$`!%rFe1gb41dk1Kj=SD+\]Af'tC*$,qW
s@mJ?)gI;LN([q;cb_/f[t?>Oe=eQsb:V`cP3'b]AIkn0(]ALXY0'njuh_+27d>Y3DtK')ftN2
NB<*cOm4*u%bUY!.^&"L)!'6R/A,lbE,Ao"[iFeKogG)FRo"E'Z:P6uBOgtc4iNCbC=#i4`>
V:=>sm\l66Tq@LK?jd/`Eu0S_'hNKHeqVG.HTp5!lL5kGUe1PSYWY"j8p,O1[G8oo!%%n11c
\o0eDRVk-R%7ctW%U+((t1Z4?(M\DB"Od_t7PX;rJcfsacWoiKEjF$-dUD!q1ab*oV-9Lg!\
(OdMZZj73i<3+4:8i(u6V%q8'4(r__\='D5%@[<hP+>W_Y59S:(l5AY'\dpPErh'[md>X@Ws
H2c5C2u$Oa:(1^**ff;@8%[us\RH5P@*H!b8fRZj./^m6O`e@0]AYiY-0l1hbF>n#34"f(B?]A
%(&d_l[DokEAh\+%87BSb;3NDB]AX@*IbI6%bKWbNRFa+m!J1fFSB=ZlC#n>=@mD^d:-NT6Sq
N_1:J@iLEUmY'kLhA=XU)NY&\B)%^)qB-icQPZbb^'(PkP<u,t>oFNK)hcE95E_<Q<NlZYr(
*L`)1D4!_b.%lJ\>g&/9f/OGS>-FV*VO,RV,lXjCAFNeH5CSMlWE00<sMn4laPN<J%M%e.cl
U=J-ro0s.q'K\53."A)oC1T4HOEBS^Be]A<G\"*[f$frk06hCo6u6ILs#:`61Rilrkd`?WF2B
.K]Aa"GZZ6=B_$`u>'Nh1^RF>?+#iHV+2ZuJ-LWoHfuC+EjlFQFU9Lb-%YFoc<?,3;jf.b4'l
!YIV9=5,(bL8M+*bPY<F`Fa"0VR>b!F&4'N#c7pfdS,/cd@mh2<8*l2cb#C8m%KF+`C\YP':
,j52bllphr@h4o$R;P=QOV&A]Aa%udN_[l(.j=un1uTt[R-GRc=HHq$lE</jkd<W9HalqCi",
).r/I@,GX7qS_YLD<LXPF!C4jFqNp-iPCV+Op^,0W4dt2:PKemB9AqeC(eIVDf4'Zbd_rDFQ
$:sb0Y'"cr>?h'K%/&0Q'Km>(=)bWA"DK/0E<&=Cq()#JDGG^*."d'AsQYr_o19?2H8GD+_G
]A.E0(3l0GiVMi:,l<_8^Ot!,jC'49(m2QuX[`j_h<A^<u&%'OfPaN3>\$@otAG.Hntk*!\Vo
UVm!,mLfc0"nH2haWu!NlJ,#ee2cj&q"$S<N+r0:K\W7g9Cu-.AI#R5(:pmW!>r`6+hpPdhS
A^4F>=*'T1@@O:lgYm3"pGa@O,N:a(^"5X2I9nd!9jFmnm:K]Ag(%nKT;uUe+8XLI[O>5#pu]A
%5tm>"*.<p33A<KPQ45$2=RDkRO)AKEG-IDg89K51m^<_"h]A_[?3ketXMtdiTJ<SCjbJtF&1
s-`O.XU)<WFB!KJETFJI8E__o<#"0BfPl..<bCcl!0Cfbmctnf+DDX\FJn-13<+*<VVP[gu+
$cj@,elnb,5LWSLP7HtgV81hcnMNtMY'RqKK]AnOsYrdp@M2R$n2RLCpHm8"%8,6JK;^iU`>+
4-FcsF(n,REA0h5+)k\fkDm.V55I1&`%e(s"CC:P-`qsk.eXG]A9Xt7_nf2mVn#Q"U<,:nc;d
=_YQ>dEaXR(%OE?SD,[P^"QO,R35?qO]AO,,3VM4aS>4O2'#/BY+qc@ohNh=a,aoOLC,h;*c2
>Sf!`N8QF]At9Jj%)ZgN&gm'sYDK^j+Q8QsLRFYH!L'_?9CJ@VlpoiOK49L(kYA[%WXb17>$-
@<NdT3ZghZac4C5k9!skHs\)r$m@Q7N0#-;"Q(\9n?)%JssA+1Xmn9jK<@$OF91PQ@W(=pDF
,$.T/;.E0sNfI!ES"r41R`?LBi91hQ:+IV@bT;\Gh.^KNn]AZ(UBL?gR!o:d89ccKXReququg
mh\=<Hd&AJPPh-V\l`CZHX50"U=@.TXkhtX?7nrdEP9;8X>#Ih0r8;bDb<=Z`I4JaiTThB<T
Z2EL'Tufdes+Cou[9.#-J&lCdRbSdHru24'gfR`CDEjl/FUSJp+m%/EP7Zr.p;PLHDBdhSbd
H9N$*gKR([*XFJT:^ZYiq3?ee<'Y2O9b/qaWM[+hG9?dgI0Ec.cTg)JH?3$AQH)aY-3^(h$L
n8!!T$]A-9QSQCKQFQ+K[:?c+Nrugp*/0%m1Rgu-gddhB=e#]AQI;`\*OkJE5Ps1D7T5SR_Cu"
=;3l3GA4VL2j?EuT\&[O@g40WdE?,.E5p]Aps94iru[\22I5Ta*aD#!I/:n\43Th.=LQ>5EbY
?c**n,U9PX0EU"jbft.Cd4G10U^(Y[OS4K!3<EL3X6QN;a[sgj1pc8N<`CY[Im\KQC\j,1SQ
]AOq12:D.&ekB#)Aicfc\`RD=qo)h&U%k.4*HSV1+Ml_iJn<^V]ATgaj`g%:GPEBjT6ZrLZiWW
BW6K/_d-%&Y.P/S5(4XJQ'5#B&HgV6::c,Bf*<6[M@M0t;8e.ND#kY$X7XEZ)6@pZT'0XMPC
ZShZ]Au<]A:`%F.%dS_W>elfc/X?.6IY>rRe.@&d;?TQn!e@t;$9fb'7mp>(H]Ap]A:$cd$D7k0e
Jc%$')8YMYZ*@#CZMeq?Y`o6@m[Xm%A1OUX!1LP8IgDD%GM[:4ijHa6q^+n(J3G.rqeU!uTh
:-W;W:[<>qh<=e=5<B98d*(T^>7?L(eV>YQ9E"hfBpRtAN!!*=2L/eBQ?9%kQl&,t=Jh7R""
b!K_5cY1G`AjX"AR\t.,[%MZ#jr'F(aZt-@c$efF;r0nh>f#ATYDOqrh#$ii?Saf%YCsXP_U
u&LYrD(VnG*T[PFoe_D9urV0F$]A7sMsUdsD'G79f7Bm2p/iU`<Mjjg1/&P(U;:A[2BPJ>>NW
;9G2?S\SFSub&=O"r8,WYufq>NtU1_r9+>T5>laV\.g-0QB1o@bg%X.`:@sXUg9-:gsgRgo(
tPMJ2s?>QV*6i5@!,EP-bQBRd2hT7S('j@&eS-*CR_ol&#5&,bX3)@S!soeWn-n7pdKnZDf>
kGPJZm"frH"4bCcKOYA,.(Pba`UFU4@HPQPM'AI)qX_iS>3bt3e@*O@6UJ<@3E<hU9C3RZfA
XP.0<AYsE-cqH6L&Vba2BJLNE.s.jS2p,aaGSkZM/sGdR1rjoenqq?$Di0:D(Vd"p/G<FB8[
2h&C\$_<,75$n/_Ajf$HEboVR-WDE'<\QFD#5GD@rfAeSD@9<..G]A0IW7hB4,>[+gX</fnM@
R+#L3cnEb7$dP^0R<2W[%5md8KT99GnKm`LJ^\D_q=4?r;!eTUn`(@^U,;@Ut+B^T$oc-ci*
HG8-Vp$2-\cA'$TGdBC@3qP:$*J#Qr9!UR)."L!HG.m#-HS^"nh3rq4f69Oo5L//B`H\%V$D
p>\\Qia2OIk%Ok]A$gbA79Q-"&a9dV6b&5=l8:EEEd9j2;qDtbR6J%)ifqd"u-[_CN_GfuOYH
nH!nu6h^Z$eZV@E64ppY*[0%?ZF@ho33U4CkqH]A!nLfQ<O^&Qk4e',c))>V)m+lk>A6M=3@W
"G^<eM8<1r!EB_^_*A9-jdsluKjLjij[c=XK9`[l/b)"f=FEIB,&b:6c&@M>^\r[R!9'[G('
`gd,DpY=hNd&u*'NX)4n7(:"_V2![qf+U#C7j_61&?9C/LA$E-MKWAI/%?jon=[BjeU.!Y$1
X'+Qr:TD8d!;&*4K>oFV@Dmb:7SbCWB9#BT`PpcKN?(T:>(q%1Fsc:1rKi\K7a+k0!,>bU>i
?2<+QbOmIHiCiDDm?g"%f8[na@8`%`B8[des#O>.Lh]AQ1VX31*3ACJ+r(4j$LC@;qXc1*Xm>
#"#GV*i2h[Y8N@si*k]AiW31@qN_;jg4>_As<fe>nS1H#P5Vt)Fku1Xc,b!3EL.Z5Q&I5<XrX
O@S3B$@PNZ(pm@@RKF'>,9g8Z$0,:$:#,N@m8@>Aq(uURJ*.S^7Fl^XsGuCfK02rk8aA+DB/
Hl9SGKVYQBrr]A/lnW0=&0>ZeSka(O$q[eKl`%g:ZZ,<ideH9$l,f>P2g^pps*ikXh/-Y.+#P
;6LsJ!&Z;NGM2$"h\3:aU)OBO2$-!jl]Ad=*t0q/)[6Y,RV>1NRh[BlFE>bW8M`7q-mXrT0BW
gK*$c+CZY'%j5=MIf\JN$^X,s,Z&(ocmKM00Kg1B;V5I=GeL8L;!Lbl3C.Lu!gOD+T#l#'Oa
D-gK.l$>LokuMVI3oo;Teqo/3up=f5kgHqq;3*2_<.G?BWObK_*0'oi^2*nA4A":u<@'3t#U
0*kVuG&[1XT_(P]Ag,sDH4f84\<g9j7+_KdU@=7\)jjB8n(f:F>@,k,UZkkq)kS+QtBUb%m@_
nLKl@leIfS4&8@_FA>#Hoq"@hp$>lDd4Moc7JB2G3%$@MMjaIm!eRajkkcJ6!cdVjHcK;WL"
&'-SY3t0Uj))7gi5`]Ah\6.&:eD+UWn7#!!RNEI1%/`rZQ?J+:Bg+X-q_QmO<]AN3Z]A4^n/!,4
2IP43[S]AcY`nl5[RE5pN/^dW.hmA5(lmUEi[KC0V7"2B7Y7qRdY]AN@KYcLGN;#@q&Y,]AXnUa
9BU^NaF)Gi4VD#j>KD-\(6&hm3X@DYQ9F5PO@]AE,G&N=PWRrONk($=Lb*?69MdO"F,=l'VoF
6rI(jHX/l(qcG-OIlT)V_Sph/<,?=?m(!]AJ7`20>)Pace'l.u3G3,)lfEepnQSCZ.Jc>uN.;
?p<+=;WRqL%D@6EW4MX<N8hl)?[Ss5*&UaDUT\J[94IOmc-GG?D7n/b42?!W9RgjMcXf@CQ<
9S*Y<XKZZp[aZSu[XWK^^6XugLj$++:9RHW'k.SnQOSPGMQf/#5u@hp`>A$D3M4"4^sgL^qU
9E&#0YcEBnpD;h=H;a:1&-d>g&"e/cO-Frt,oG+Z$4GZO57W-ilR/m1@LZLTLe;P&-XY!g=2
F[<TnhBI+r4@#?pt-q[U[8fFtMEn%n_%Q^&V05mf&T,`DTSJjIP8;JVSRe`/nTDh]ANk_G$`c
iFSf/L)V9qGi!]A9X,CJAO5nXhE.q!Aq2;ss_U[?Zn1o650H?LKN*1?DK:#G9BamHg[*akQ?a
c<I%X&,h0ZR$u:US]A.;j1lELWF0pM7jh+ufqk!#-oTn_f^pc5BN2EJm7j2n%,bVHF^R76>NJ
/`'Nbh8$6:88p9_TOqh0]A;6QUhF\78hF^,h*]A2p&1AP4$Do[A#pMch_Uk:rJ672A&c+(jI0I
kg1%`RrnL)CV%6m['V[YdgF1J#/R7pNPC_@S^DkE\Sf4"`M4S\$@0[miJIZ9pS%;t==uX0a_
/W)hhAMh*/'nF9Ge<0pXaJ/c&YnZLZlK.g[`m]A++Z?,J[-@S;<-),X]AE:kgM0BXA.D5.kD+_
tQ=0PEG3XK^P<e_$j4cVmada#u$%k'Z2]A]A@=-m2/=*-r\KPtUpom/;llS9ZFHeq%6I@#qJ[2
H08Lj\q!M>qeC;)7(#!"f?t-G=#Ush7OS!ft!?X@>mb$"nSl,:q-C=n\j>J!,?GNRWjjJXQl
pQ[2T>V2X2/Ues8,1\,=N@!]AkdC,HA.Pd43G4[Y06JWiE'OU`;n+oYMT_b!j54.G6Vq_&%o'
CHQnbB7`LdF;('I3f&O[n7_T9$Iu1>7U3_-=rgeI/jc):]A5hf:Q+#"`1;sONW4;2XU0\iJje
Z&9fn7&mMJ_<8MW7d,7I/c55hB^I?$Z4E;A'0)\gb;iR3-&*'DT#aN8i-YeC9qfqYKGF7@d%
C8h7UL+Vl]AnrW4F+O=%P0[EmKeK9(p6mo7=U9>5u9'6[>6TA]A@#g6L[1]AXlJ-[e:fC54)_`/
Cf(=C&c$j=H0"K2Ih)a;l(0i))64LFFI:slD(^mX!8'%.-hrMU:Fh77uqQ?<]AUWn7%WY\)kM
9?HO\4;GgPtH6o0SW;(i6&j_YgZTg52/Z</!=6DKJt#go/`1.ogf%9%)c8&]Am3]A[V"]Ak7]A[>
dkW]ADeS1lUq/,e%MZ%B'[U.h"-bgFiA`DfJ>kt>85S)J_!:_/^:n2D#;+gO7GfoF%bicTn*I
fT.bZic1cUlRJ$Eh/0hESsPo"nY;k[_[;2BgYN'-n*RNq03W-HF*s;rRSVp81X-\l421bHuW
GDK<ZjZi*j_mPspJ0qH(uEm\?![L)LW\fKg>H<2t2!^UPT"=Gc_S,S9?9NFY<YD#MW#'lGQ/
Hhg(N`;)=\g[4/[7`F6W_)_B7Ag]AC-KQD*M[9!'<V*`Z#cLMQ@^m;paV7-9T&!Bn)*.]A&@P0
3eg@,ORkD;9d$YbEf[?9F/R+3&+N&DA-c46K0]AhSC6if86+d1<<8FRO/@Y]A&(r9H@Q\c<UJ;
1dcQS%^>8Bd94c*Qp=C<8HHlqC]A'G%[=!VAZDG*I59oHC[dPAY1/Ui^*9uN%U,4SZ6#(l2]A_
na6@VPgg\YQeG97EMg>[*JrEr5ne<^CiqKU>g>CbjYZiH9e&J4q5qEFE)qX_9*rf(G>5_oB]A
Np-#\LB]AIdPB>'ms:kb!s%+p7)da@X1TgUQVC6,Xu=t5s9n6)a/4!%PScV#2uSX^tAqQ!u5p
@dc6>"K8m8aeXB*Zi\h>B7<#psD-/B(<P<fXYj5oI&'4DEs"rbPtbfKX'>8,O>CLYE%E^Wia
uk`PKZajRnmmA3Dh0H0Y6:FW>^]AaQl!b,/q>Qcb0eq-1,X^:2]AiXH"IQ0LRuDD`^&t,56(N+
Q`hKSRuVW7E?V6;=BSg24#o<$\(sE3a%b5p#O7Pup6`pJbs)o9&`6%m9!;Z4o2tk[c,ddTDs
]Ae&`SaeE9$W_7BX>hR(k^%0Q@%Sq%/@%>YN/"YF*;S-3gs/QfJ,9JNqdV0[\[kt<mQXc5?&<
l7@AkDi<X7(JsqeMhDBPFQ,LnN<9MS^If:-22!pCC;g^*$q_^O9a2]AW'_\490`7$9`kc3/&Y
+$-E[f)3tKj$[BEOK1o/E`>DfGp"Sm.+=VNBHR[idkII-fg2J_D_!lV(74\T:)%-TD;\)69E
\_RcQE3:<PO9jYj+&kmLG-&!O#jjE0"-Y&V8C1TN6tG!7Q<[sb-g'(J?h__B+$D1h%qhl,iU
eEG$r<RZ1S>m`u!_NDX9cAJFTV]AY?j[(35S?"Z;h+50LD7DR^Q3X.Mqbp]AiDCn_DTa/IeKYl
ge9Ni9_._*Z!-m2m2RS6J0/E0e:8pBNuRiYu\<(9!,;F0fBrA3p8B9&*u&:-Z65A?#/]AUGqJ
#6stu1FM*l&'gbK?9W6i%&MnNQ4^rA^i<QER&UCXB>N6XFTdFan@JI5ilEs?M+\U0o^\Ac:6
!?\#Bi']Am,$=jHQd_-9MieQ*"'1?5@)^5:@OnZU^#d@"e974QFi[U?)?5>27Jt/H/Nm`K#'d
ddX8l1hRT2YC$hOaQCa\k3+EP'<V%L.I'cEt=?fbu+5P3D?B*W4hO2i-\oW-qU-E`M'l!LF>
0X<Zu56B'flp'VW5%%c$ECd8[1*51fkD2t>M5%c"s0nrfdZmr_B4j@Q&q<JSQZ@Lma_hJrj"
cu-QVi\-;JBEmp"1EOJc$B!h3Iu2*;$UCM,s[VQ'H"JU\9iHpf:#a,'A,DNERPko9P%-rX$[
Y+Y5_DJYb8'NnD2nb]A[@,h$j8-m*7)kHi&@GCg(HIg5iQ-p[W6jW`Q7<-"DFB-/HusOfAJ#2
gAbsGHZqm%s)I;/+(f2g;#XH>G=qR*A0XG;Cu\n<tUn/BqQ@%(;i#E6Fr2<^Gt6_H(_+"S.(
T#qF[F=8e4&*bpGPp-4[Be\TRl9@I'n32X#@FM<@.I>Hcd(!u@g6j#L8sEd[??NUuZsYG@K:
oa@cDBIiM5Rqn9ae7)kQ%U'`&LVfSa,5crHGEn8N8X!(jEH]Al^:F'Lpe0bS6^IZ7MUkVOMU6
Xf.e;p-j#Z<e[/+qAJEVWRHVPs1\2Ejc2s7"u,]A,KL8d^.k5g6\O_@IYU=e5GQ/0IK"MY@_9
>?UZdEa.1n*49jlr.*.2\ZM\\6]A2VTr<pN<<_?FW5i)H(.<e#:aDGerF8#rKEZ^a(iNOtZtB
,HnoM9^OXX33/rRd=!5=-H`uP]A=-Q=S6k3:2,+mRES<L\"b;]AG.ledhH=n,N`V_d\T$dM),B
#H:F<!uE'5.!D5-G4&;YL@Ip\fb?6?rg`f8rF1croT!'TICJX^_7D_EJ$"M-jJ6B5DTV3fB7
AiW!^G8QD`MZe9*h'#L[Q24#s)]A?EU6^kC-7R5@#?]Ab>W55Q0+h^ikW5s]A4YQSO8.m$6-7S_
a<'oj)*Bc>+X73G8b+rG_<I7DZ'1+]A4EofDT6^]AtJSH#C`D-nB8W=_5T7j:>G8\no![")%6t
M;>-'cmrCO!8Vq=kS1G#E\R#k!8C\UCO&+mr;c<Wp9';EJ=;(85N"jYm&mS>e8fs4?H[A7&)
0s"\6h8TKHBgA/!P=h,'52-TFKn_DWu(PYGr]Ar7$/(G^M6CC\B-V>Nf[]AZMjk5+4$/;MiAhZ
r3!Qr.AeA:s[-iZ04mlO08bEWioaZ!rI%L5ND?&2\FIOU=]A/XODEkZa:WJduY01Qn\;UJik4
H&lG?PC>#qF_$n>'$1OAa([E=G-]AL^<NW]AWT%?o;OG*Cl<M&LWNle@`lEL?8U!bZ</4iC9Z%
U]AgGmhl0PQ0Ta^CFK7LN`S^r_jDG3[Yd?EVO<H=p(,*ia_j`+;Q7\GGJmQE7'A3\eX$%"Ah/
0X9u\tSMFR?G'!-dFS1)\/5hDaO$TVWPUi%Y%\I3CO54mJnWdV)m^BL2Oot&7<VG=uk%aS*)
'#'%jm^Kt%TU":$&<B<pY;V$&');C-T8U(0dIDn9OH<R.io5ZBNZ*(?`EBB;-^reH)MH1,$`
f2.9)=VVqi1ZY[@[GJpq=Rc*2<f*]A^a;56WeD'$'J^Zu@6Z,muWG@8n53bn`qT;cHbqr`6<,
bTj7*D9A8rdEAtk9o+i`B6)F$),@KG[Y;Sf_&NZa8CRg/G7T>0!Thl>^?f_''ToamO(u(YX.
Q1<49G$p)t[Af;p>7_g*hgobT%*%mV:2*DnW54St;#d2e>uS+/_?81eo%8AqOH3pPqEW:AH?
\'-jEP`n9CD2]ANa!KQfTI4[EFiqLO+21$&RCCe![QEHM!d>3FD`Y?U+AA/SV57Q1-4Y&9MO7
^DJ44c\r:$5L<]AW10<Sfcu?*'K;Lgm^9qR9O.u!]AYMFk3BB4TKfjjK/U#Uoc]AY6lLgAXsgPo
)R+89;[/ndot#3U:A+%X\%+eh[bA5++CQQ':P\*P8Mh,qJQ(3]AohZDr+ueVDbWpp'^PhdB@P
>Pan#6iP$/8+Hq`D@Tk7'+'@%l1+<*/e_P#N:-gm:F@ClYmKG\jt'D![&Nc[)gJqFCpWloX4
7:0CeRs`[.Y.Z&E]Aj&E"K"tqi(,9&"4FU)Om8>M,n$7&+$+VjX7:QZ(KI*l1Bj3T\:tMU`N(
.89!9ATN/[`O,k3lgX+<O[Bl(%2:"p7IX_89TPD,>26Zo&@l6sqgj95Hfo_Wiq;ZiRrn=J#"
c6cI17]Aq:i+]AAqW1k\GH@c';P@VLm/eA!Qfc4fhm^pr]A6OW+^DORc#>orS#Q0b2&*R)b&kQN
]AO:r2t2RLs&IGZVUbQ`T=ucX/kJ!"K#fC0i!#Qr3M+H`id6MTaFbD/A?&*p\kHB/e=1cfpU1
@(JP-`OY\=@R%4[)#UW-.L#pMWm)PE`N:ljJmP]AF-VFh@MD3>hH;r,)r7Q[_AS6@eHS\V.DY
;h:Pnfb#Z)6U<!%GLbm=nYA9/!d]Ad[485I@'_2U6N?[V)C!-D-^7@OV3(Q\u3S>fl?3fh-^P
%0AX"jQU1U:&)VOXL1QW)%%eV3&1M#8."XqA9Ks2)5cD9J&$u!OjKkEG\iUcPl+XIUKN=_=1
slS!qIR>MJ9JF9j>5S-YnkRQk%`jq6_32.5)Yf8X/j8>Gp=STr%^1;>+r9sY)COtdWH*ImR(
[7[60u]AY\D,.Rc&73k+bqNVYd:;:NbaQ(OiR]A&MZp&<oUOY<@N`?TcXG*B.Mej'L/1L"i4)D
;]A2O(g1@ds]A!9s,Ntk9)QdGA3.8/=d)0RQuK1*ePXlWTNF@g6fbI`F5p9>S/Cj9j?9hHuPdW
/,R:Cp2o;]AY6b;j0;<p4\#jQZ212X&\uYc#@e_)$[c4"mL0F"i)s)6hRTN3(?n=TOFJa?F97
U",L*5)B?Ij'Nf'7Qdi\$1OjQa-^emD@X\Kgr+'QFUJ#9,6"6+:QO]A:9q9`E1:Gra61&[OB$
a:f@BdL!HNqB?#Ssq@m/r.K_pisJ,6d25DTs@&_5Xn/jH"(0iO*ec98^uUI9r5'FmHPk#S\3
<tNui$,e&^7P8?:l9Q?,E"bc6CO5d?AS+_*;IVV6KhF@1pZkinMr26S\4[3k/T175npW(F9R
j6!0l*K9B>WMn:4^eP#.5LgefqFN3p2#5r]ALL]A=/>ntfQc+;Fs6bHLr9<2nDB-dYGk13S>#"
co@M%/:cblbVm/cdo.VP`os(Eskp7s.UmfPTW\6jY8;6Bu/N(;i&BkZUG"g"O&:I2O,e^gS\
Z,Bc:4C2`5J'I8qK+&06UAp[BR0\Fp1+$I@K=C^\&BH[pA"ni6'`?+0noEoHjk9coWb6(J?U
m:A)R^TU$Tob@>/2tRpg06WVLO01d14X-L4sI!Aj,r-Z5Te>)(noAFX/\CQ'B9?&<Y0;l!S?
YJ*j1,Q<p>&-JYGX=hsfZ+-5()+*L(RSO>1B0^4$-spG)Br0_V-3^">A0qhZO]AGJ9/`PiYWh
5-B&+jqCDbB?akMpmfOXWkKm9$Bt.3%aJ\BC<p8QotNsjcD+sYU:rfk?ZMEhA)bpX2=mca?^
KUBKg>C+1Gd)'>P%\Y)'U1FW"&dj7ujnTb/sisR&?F]ALVdW[/rIoA]APn.,)H'XNk2SV,MsZ)
`]Au-Cd<\>Lp>$\K5`+jU4n+1>`B-^qH,FsrU?MX:1k>.&97k>e-GTK<9g#U+%\^o(_W`a0f3
_9f<JY[3\9uJGgVh[Vb1j[>)S[;%2A_BlYI,"<WQREK9n*HNiH=lWa=A,Z\BSafg5#4!5DMM
EroHZVV<fC@qgW[j7GAC/<6tKDP]Ae_ln6*QeNoi2&7qr)&a"Z60,e=A1W9_RE)kFX\QE]AQ$>
r1N9nCnT(qp5psYql.?WeYuC;[)mEI9o/IU"R+NV1XA'@3V>]A'0#B%t?+pnljjp(F,(,/s^.
#)O?q6"[WAnIH:#b[5ajGr/$R5hpNk8^X@9;oqiXpjJ("mN"]Ak0kDcW3^2QXR8s"Bmatma>d
<k"gh=dNB!lYmJ52n!RojDI\_]A3ZG(Y=WK<*J6U]AihdoXnjV>_GE]A:^_Ls7Lf6Q>>8("-#YW
Yi>RgG#i9GP^9:nBL)O_^E0s?+#*\>q&G.e$QMbRZ$m]Ap&$%6c.,8OQD8/BMjjQ4o-4p^"$M
ja8^*&l:Y+e)O"WsQH2hC_*e,=#<T("cD1!LeRcW+F$s-O*F.GpPcB5XofDS['JfctFQWG8M
+>o?A7(:u2YYa)RY]Ak5TSal(kgp0OD*/;42JZgnds-!u:7e:nF`4dc3LV/g]ARqMsLCcMXi2`
<QP5/H\(e`kb%7'Zm7RVX(Df+81B2U:rd6Kl.5-;#56>MTnSm,HGRg6ArT-#L%:>Ce"NT^U8
]AaaP6T.DD0gG54=Ld'&hO2cpYeR`LQS1"+Z\/s8'8Z0cA6h_aF2'&$n3feYjq6sP"Q$fAQm3
_9o'as/(mIAIu%SOLI^*^IY[s6qG[@]A08N`6C)("BX*ef@"R4W0d^&Rugg<4$ViORsSP?HZa
OrjP^h;Sn;^oP3pEY41h;WW'Fl13Iq*5nS(s9@?N51elcGC's!QJMDKU4_NdT,h(uU+!%Y7a
L@F*7?jY)q@]AqgSk;bUa?.%6cKRRRRYs*bF$!*X.'40JLdk@_4L;bh<$XC[e*LrT#m0r]Ani4
-'@Kh,$.RVJ&-*#[XZ%$u"?H),LhkYE&eF89[%\%\q$/Z=f1[1cUs<pZ+#5p-OLJO"Cd$GVH
Y(Uq2)EMi;[Oo.r06Me#]A;n^0=LPO`aW%Y"1H.<]Aj,CeT/2c1MH969Hp.+pDS1m09(.r=aWq
2H=P3q^Z%/VjAcUYPLo[c[XXIJ0a'\"K^%_0.QZXe&1@1,Iad"LZIJ@u:@N)@HFApJUC6A>T
I65!m$D:f]AE)mqU@`#d^W)'>g93J-cfLL>1XUduEuo,EYWclPCkM?G;0_V"=hu3ERj:V:V'6
VL@/\fA>Z;$qZK&En<@?V*,qXV!<^p8<7$j-_9NVT[sX<T53@(3\q[n13sHTY_(Sm8fLDNGf
PV<WK%jc?fL;%>7A;m12-I;E28cki<2)Q,CN]A#NG`4TSO!*t*F5^06@F["+Q%pt=dErs]A<A/
#hD=<(pYf2XA$FB'fWgiq*RX>.>rcB#+%D)Oi/t)qA0&3mjJQAI:jHm7XDc-b0$9*S"@tu25
(iOO14S"K=87@27_67_:%^G3%i=uOpMLXl8J:UAAq?H$\DWR+1RdDkW`Q:6"6??pQ'F7FDXB
-?>jO!H4c<d2nl^"p>u9m#*i_DT7;HlfUFAn\f7j(Qfa_;o6X>SRI_e)!?\67Z[a(TR)s$O1
V"TDf\iX6^A^mV'G$[>e]AUfN)[Ag?IO#tqecNY/D*R<jCs+>jHqQg,aklrN1CLta48rYi3q#
1FXpaa2[JSUj(*GgPcLKh57pBQHOR<g)I;9/9X$W?p&9V=799*LZ$=Q^+R,J@UE3kO_ZE>4g
-R2DIeBC)a.Cc?<9nSI5mE=-sgXn,<?E5Ykna0Wc9o/Rs`&k":;0=KKMM"T=Qh7Sd5bEs8\f
?*`N-?J0[EuVm"fCa"%O5\utW-oA&nf.)CYD\9rWSADlV55f.KOM&:&/@`]A4r.+X=n^4uS=]A
/*aRCDO&HC!n<UVE=\Qk;G%8;\=/MYtBHG_U[N=Etja-&oG>#Z0CKAW]A&T_CT4[LH-oq2CAo
h_li4HN'.mNlAHtC1Qh?;(Wa:oQ*Yf%sTt(r+:kVga+)In2<P(DZ39eRslfp)MnjN"fO5!)I
<1ik=fi/ke5P#g[9Da$:+q]Ad^MlE_bVk+C_u.(J;;@-n?i.%IB^4;L_Wo[CK+pY()HB*>eM*
5b6?Ag$g/V'QPCZb8HSr3dpj"'$:I$M$S8&Qo5b5BM>gQ,:ARX^p]AQjS,rmNKJ?c*=?a`u._
HC_C?3L<VVTp[=U^GobTFNb@l62X:'#To_`c3Z(c?a`IjrCq^K,DHXP?QIm2<MPYW:dW`DIg
=]AIen/Lc*B*UjX@8tp:%AG*$rHK6>gCS?N4HAJP1H8!\XZTjp*[cAnRNc*F-;XV%c\0]AFiiu
T\b<M<M&lBa\`afO.#j:DWeS:87'CPQ=WGM!.]A,3CO`#:`,_eqIc$%m+4iM28u>t1N,b*#"#
ZrGBFu@+!=d@KcF,BdG5LdnN/]A]A-<,o"kc?"=WBF7mX?R.G@c<47/BNh/4$NMII+:=L(_`PV
_H2E"=eCYV1NP#dLa6tD,Xo>:BE+CY=^$1%8Gu6?e&;sV6?K8]AKpOY0p>r23gYFn\0OfP@bl
2T`W2K"Li1WR9`2,r-FOkU.0N,bL6":u>ZC>3A)]Ah>d6J=%gk>jdM+$/O6W10%2j,Mq0B$mU
-@^uSf+&5a/KJ3I5@B!CD7bLOu2+4FQAX5U_L0-=>']A&b_HgLr@Q;3qk)DQ\,Z:\"-C^E$99
BPUG8'0k3%5<-p0jJY/KfTYah`3f2h@I4\'k0`%.NfB:B5p"+L'B`o>fQK#4rg1d8g(@[T)J
H8OYnd6652Q;[U$Ma!OEUU=9<Re8LcUad8>f7*pRN?BgTL'>$(Z#7P(_65kmoAj:@GIa3#>/
k0`-ik/g9HKX*dMH::="l!((6C8eJ@F"t>HQ:>`8<LgIbL6>.pW?NQ`gl0&=H(803rkL[^5a
(*$]Ali!;2#n+#BU@@:!m/CZ%T2"%:T`uiEUn9ouT3@KoCh]ABV=MFr;e<EW*eEURk(ps'$?aN
8L,`TJqIKuq[\@DL:H%^.h)'INQ"&KBmI=E/IS7=S&Amf8l^V6h"!goTeR3`)!\j):UfP0ni
os.g`!tIN$jpT:Idf@PIrJ.b2>3`t&H_]A@T".=aJ8>!,lnHh!G&Y&hI`t`eDP=Lui;%8=^V3
Rut!3QHP@12u]A!;6!!fOdd?Rl2(7!,7_u#-liuMG5!!D*phD*..=i^2'YE'u5A=IUT:d^^,#
@5GSb\Rni;Q?BOe!@''4d4\pUp3NqYV@CC%U9(#n'iFLVF[:fl?82SYD++Z2.07laP*oh/P0
#Q_GZciBue$7kh'\5`T_1.ZWs0dIN_HnF%@n4=OVCA:>!Uuc.L&1kcGh*\BXM[D@_7'`Ff;=
;r%]A0aBYp_CIE5D)dj*t8Apj^F9S3X-jcj+4T():JM$/,LF<1Yt?:jSKSh>TeG'H!N.fO]AN7
[em.ArV4.V<mfM,_7Iq<=FFTQOS,Fr2Y(Q5\p\'XqH(%h#bYX9?q*\B".Xi@!ZAX;%k)jmNr
KO;_UPD$=Y.-ToU>T0;J)NQ_V.!E0#r)[*2f[V,b^QT..tuSE?Fu=[b3=qUC_191GCYlI,NH
U'NP.%OQ$4C'^,Xn5gf_j!(TpKa\8YES/Um\0ilA%3&?p]A^BtWU31'j3PI-0qAe@C[;s^)Jo
HH(!bP0^QcSTDSH>RJGb3T<L(.^5+F#-iE]Asp`%p&\r.s$8R5\"3GR:,I\M(R[Mrcl?(HA>R
uBglfSC)hT``87mq%ZE2/5^L[gV0K]A6(1&`OV`XmP6K;:%QLCQcNDM`]AYn?e$4VQmoH:]AsnC
9QIiMI@F[^0oS3m?fHUBRplJ]AXoJT)C&5eX6mSZ7HFT0,X4sn`Y!kK(qIPLsec?7h^s1f\Hh
/0q<W=Bcs!28<;:DT&OLG^mkd`k3q9")B$"^K9j3qCnmp28=[kl84L-<='_qSai0bpS%Vjrf
NTL6#oj1VlUcSohNW6#o(QN"(LVOK?6WerI'%2]A@Ws2,8XlkmNrR/K7-^]A.Nj4cZo^4$aCZB
hLcj<g\Z!LrUu3GaF93"-HdfX#3(Z#7.i#%l97hbgQeA)DII"!uf`ocfu/(hJ\4mr%q"*VTQ
30XaeBIIg,cZE1C;O#8R<;J[0`d$j:s7m1f%@:'81EC>ZP4LiWcb*56jgEa=U=gefFT>=&)>
,6!cB,Q^eXe`f((b[Z`VAhe#rHYL:j`YY"aPOP#!s)L6OH>985S/I8g03]A,Md=`!J3BDRek0
drDr-.G9f$>4(NUQce~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="685" y="0" width="275" height="207"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report5"/>
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
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="685" y="108" width="275" height="207"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report3"/>
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
<WidgetName name="report3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-14669005" borderRadius="0" type="0" borderStyle="0"/>
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
<![CDATA[1728000,1676400,1600200,2095500,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[6112365,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[销量分析]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" rs="3">
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
<FRFont name="Al Bayan" style="0" size="128" foreground="-13421773"/>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
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
<FRFont name="Al Bayan" style="0" size="72"/>
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
<FRFont name="Al Bayan" style="0" size="72" foreground="-6113837"/>
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
<OColor colvalue="-1441703"/>
<OColor colvalue="-149760"/>
<OColor colvalue="-10502420"/>
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
<PieAttr4VanChart roseType="sameArc" startAngle="0.0" endAngle="360.0" innerRadius="80.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="类别" valueName="占比" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded2]]></Name>
</TableData>
<CategoryName value="无"/>
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
<FRFont name="微软雅黑" style="1" size="128" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<j+_P@rMIN@7mDcoP=L('eCd!LY*5:ddaV11Vo.TErVF)5n8QKa7r4!^RE[+c+3o6tHZ1@I
G?qKG=e"=X<eHgS[hqRde$e5OW:C?hr_/T0)dqY3VVDHd;GYCF%]ArN.K>&YtY2u#T95\rt,7
^LB,-$Y`/^S4#E=F0*#651"8F&)NZd7oTbIh"kFXVF81joA&Aeo=T#MLbiBOeVt)(1-uj'Jm
,J"r=ZN7qB+5;-Q?/nA'?>uk=F9G=O-6t!*jkR?ClHcTs&<l%lbrH.rTbC2:[C%Vc9ML/GMN
CFk@3:K*Sq%Nn/mo7TdVHg]ANa#-CTL+F#mBrZ2KT(F.81S#\&$AAQ`Q;,qCle^`(3Bk3Mo6m
I_j3`80i\0WEe8b78%pok7?\2Q:/^7+>Wn`]AV)EZ`TX,-/Ea1/Y&HqOf=sMVqVaIhiAKjdX)
$eOiOdF.RT8qu.9ak,5adUd'tW>mk9g9HR=[)\=d:!BmudMC8IZg4jP1RMi4Ik\QhEq=53W'
/4t'p5\\:Q.!=pC!bg"C(ocM.&?P2T:/,]AVAEZ7d5o]A*'E0**Q+4UXMW;b95f5,l!2HWT[29
=P2Vf5BDFcKX3S-pku78(%b!"'r"9O<=6kU>kuIgT<%0*K@K]A'qHmci`bt(/f/aKKLqFWY8:
EYalVYcVJJbLI/e7`P&e\9Ysg8'ElBUp3fg3[a!*kt?-#K]AGF@_S*XX.T74n%$7<s1tJ/=m,
q1c#Ki&^VkrCNMaZ)"aCnobd;kY*p`\\SWN^WY=kX,sHW*jh:&6#;l+eIsXEP'-H=[FBNrb1
KtJ\R;"=+pgMJOs*ZX'!$9/9sBbTTK,C`pAL%3rSInNhDM#u<sQBs)SH."`)RE"bbP]A_l,Ef
`;M$.1UNEKk.EgURo?[h1r!PC7hUb=L)pb/@DIolFpEJqFFcBnjZ`U1CIBptKh7h=<K,**2F
R="WQg9j@$0TaDM9QSkj9fBV^$u$pmYes-5"]AA?d`0e4g`(P^6/D0"W8f2%oHdEcBM\$P.!7
dESn#N:h0u?6IP"UW>0=$0256#UYZKX!dC%er1NSMdBapa*X]ASCIdXX!RV6NnQ2'.?;EU9R[
XgL5mrG_cbp5Ra1]As='!Y+CsN]AP;J(MrfT$(>,[::"eU3j^p#0Y0Rt8lU.Gt\A#MN#Y^EQN:
#6pq]AP4%]ATcr<3EC9G5&N!GCMAi)&bWckqrjbqB_(Fb9r@ma2<UU8E/75[KKNoBr%>%Rg+;Z
Y8,Ou$V0e[V?d?U0[92F=pQLSR`5jp`=8_puFjGahs&7S5(X'HtF&_UV1^^nYCGg80q>N#X*
"rL7VYVeF$_&KVb%qb)Ief=O-g026jRj1f"#6K/eqIJ-rT;!pIm7?@c=k5"hgb=0-f5dMK!p
'c9>A'=TuIpnRA7QS`bcF+0;&9pNIQm:L$\WeL47]A.`iFjip0Sd8`P!?a/VnB1@BuhNa>4,u
`:q%8iMtZ$T=jd)C4A_'%Y+Qt=]AoZN^X575D;L25":eCP4MJMKMn9:kqtTRu!/B75Ig3f^oE
.-'neXp*ee;spm@<0Rij5i`;[X<+eUP7,2jXJEm=/*CO8Pu'@E;WM]ASWh@P9<p+7?hJh4!F2
I&!>t-r;3efr&k+2X1#HO#7YC"^5L>jQB.Hhn@"mAnHO;cH9lsmMe*"ES=,Nb533sa=U#&]A<
D[qnR-fWSHqnd(kt$,WD3a#R:Z-jfgM@,107(Y(`Y(q$d6nf%$\Ph*(F%19Bc8g402&M0[M&
_RAiW!YcHa^,lCNbMEOh4Ab8/-NK7c%j-C0n==BSci/M#;k/t'I1'RZ2);/r_Hhe/R\N^k";
lBYN,LS?RPF40>2m,Ck]A3aSVjp(^'@o0CWe@&JoKO/)I@VoH7g+gD.(?CBc]A:/f'>j.jFD?G
%\;g,-UpJ]A>AcEeC@%GrJ1o\8p5G:AnrObbmmF@<cJB`">QmjGqBF)uQ\S`Pp9(U;JbY.)<u
9Lp9A7/U)8:;,rSa!""FnWoofn3=K5W51m16rU)(X"6\gT;Gt0Ki:<d>[S,oT^d3iUP#Hn:i
tWe;\c0:bZE>5)X<_bh6BrN"\)]A-1#%_@./;)Xi/t%"55<QMJW9,m=joGclG=QJlJ9*>ml9o
UoCs+!L@C[p.8MYR87iHbR<_grr.tu[D;Eok9L^<;>3H@FGEC8]AH3mAC8i0fblpf<_oUW*em
)Bm..H$]A>l3D>Jdn7qi:4+HOG,YK.kIjcSRY%XXtD7hK5W6P&Me43DB>]A&aEEloJEA7j?dBe
(n9iDH;3o4We<FQ.r_"qEh\U:kU\^J.dqSf]AJVoYAIO`u11Y;l.A_X/H:F2"';iM.o_:\rNM
c/XDPjqQmXFCEb]AND75q0GKcqHd>pmF=Sk#`KuE&i:eDIX1R^gC;T,1hZkYeFB&LhrF>XR\=
#7G87K/mj>m'cCPlfQ_0UE!Xf(3;]ANb_.OEVHC:F+DH@(U-+:`SJ9n>I4r&HK',$hgtEL;75
%hd"V#joMn;ibV$c'#g9*P-VqIU\i(bU8:rO-5:l:Y5&L=0#@hJFf<XmBeQcb<\[+oEJK0a;
%]A1hZ3kXrgD3]A%$%6W0<0sP[DFim4N=?sSAWdr9YQW0+m]A,9DCMMYCJRVC/R6F+l-OY&.:lC
MK\&$VAZf]AKhnbKJ'mqc#[?c!J/6XmbZED<`j[Xb+uh;3D$n@P;s)e_qn_NIF?+hVC]AY162;
ljkDgWPSZ)p4@eY3:U*)A>#CniNf%!o]A-NY+4A1hCggATZ,)3;/;,s.3Y&rpOp=\Z9;A2fOT
%`#V-5<4_=8BXlrU0Ak7g'ee`CmS]A9q,%Eh$CIhUh>a!a&j%Pq4=m5bHOc_qC2%4.Y"Z^M"O
lWMCjT';CspFn1<.c"2V&e4hkgLSF&/96F=2Cer/mSRV-,uSdY)lA/-%bTB<U^r2i`/?*i,2
i,Q`PTMN]AojPb`JrjsWbIZCb11n1$i"$lk]A)9>d>mm)fSeZ3/;UM`AhY,CYK)1*h_lC"NReO
l<XLHBj/lK9^fSsBUAZN6DRXc=EE0C[S!a>6<\qOjmlP]AY:7`&gN\K1%*Fok9Yf-[DT4a0ri
\*gY5n13?;G@PR?LdMoT605p;pe@1o1k>6t_M&o:T+/pNq4q2D<G"5@j8qK/H&(Y+N)#%@Jl
Ic6*i"*B/7Tc,Rnm!'seH?5;I;t<=HgEFTKN0=ce`)jbh91oo5s%#leFf$709n2?SjhQ=jj^
ITCf(u^J)&?$MXPL"&G"^_8a(N"jr^N.i2pF6d9-]AC!B6t7_Fpsh]A*7Q3%)ODrjkJ]AJ/*S@!
\KaT;-I6jd4d$)rpO:?iJG_VfJYBm(Nlu&!"ri8`0Nt:bhOsjU`96qWDOt@&l4h[tfa7$GUh
Ls[5FPk)NUX9,NjW+p>h$opNA^]AS)B&2rRm3119onjb$gbuWTTnnWTPtb\ZiXJ67*"Edf;pG
9;H3jkF,.N'5j64-1"Q?N'RlDIibB6sLI)edP:2jGiOXf3d]A8YQmTVp%^MjQ-M&31G6I$il-
F4KE1/LH%p/mgGXPf=6&CeUYm45&1<80-Z:pM*MKLL44kW:8flK!@T-13Z7kfk`e-:103j25
B&^N12STIAhkPXGM]A)lt:A_OP"m(%oI,[KN@jQ&D]A/=@`0cMa5Le\Th<q7Q0P6?><(s,Wqq$
^c",;.4Z!Y/5;nAE-<XHn';:E.G/*4qQ2GBj6N:aHXuIdj(!X+lWYT)`4EOki;r[7]A;H@&'a
`I\c0,&8iUhjqLp3]A#Y1U#cjuZFa^h7YgQK2s2gJ(/]A?"nX%LYqru:kQ3t0<WWj5=FskJKB`
aY]A/L)PdR8"p-_9!C$]A1GWA22m^eX%Ib#A;(";,9B.i1m)O.9ZpcY_-"/D_^j9H=cVBfD+Vk
<nrnGOO7:Y/=nLiJJI^k1(mY-[[8Z,(fAsMn7LslZR2q1V*Kj*PlP2$[\*Y3bZ6fA;po?ZWP
4\:-!M?l3%!n*&1W7SD:!r!/X.]A?KCgh67/SK!FK2@TjUEF?dbK=$t\cQE1SSuAp6$3\mL"O
]A%qGU,:0(REOi6.O;ef),4'%+r3Cj84CVI3dr@4jg+M:C:#RL$B=Fqe;DP3[o?,HFM0=\_N7
jUd+SN.0Ol!lK"JA*<)2srmbq3f-i4]A9q-Zq1RP=j@n!D.6l*-4,1s)9?.f`-!Z#b9O;)L//
f;n>t>OATZ[L,4![gB[VC8a)\"I"Qf$++=#%3YBq8-a5E@iV4ds`.BLV"f#@Io%r'#%2[rF<
A:`_2\Q[0Bsf)ZnBJHYKBi)*dN-hje-/8s_J)='jQ'h^kiQ27p3km=8)gbN0H5$?Sm6)*fhq
9iqKXug9?q3VIo69@f+TNIdJfpo;I"'T0jeQPM#!0$Rf1;eZYkT)mH&#EB&=[,Pr[i*J_-]AN
6UGi]A&V1_$LR"\mr?\N6n<ft`ENG00CiBL71@"t-M.1rGCmT`Z*F96EeO7oa,,(1(q$Yg,AB
+D;oe9#5f!9Z&<#]A?W+4Gr&.RiT6"B^7P'i`/Fnq6SU0'aaFU(kY*AA6VR^,3'BGgpVXA&<K
H9!ThTXYu/6M'Ubj/o$qZiZ69)eU$tS+"ptUmiSJFb7glO5,5ieYAIp(UA`moS:X&7Sp-_40
Orp8O,dAs&=H[J,2qtRk9U1IHpdPfe_1uh`,a#/r/O&b<Bk]AKhPJEe;>$=!K+"dT8Ol(e#*s
uM86;Ij`/4P!-oMifdB9j?@dkE41;`*3P`EK6f%YL]Ag>BasY<IRY2R]AQO+/LiH=JCKUjRlFo
i,ABhSQ&bf'SH7]ASd<de`g+ar#$D*l`(<4$-qpa*5KKY.f6.D/pFfJ&$c5nKhCk-j'^pV2PZ
lM+-U#=)GOCVG`i`JMqtRLs;WbDTD&o1ZD!P^p4;d2P)PTkI_>icZaKS^bdFPIJ3?t(AZPo6
PgN1)b#@cHjoq`=.lWZs+`O0.@.2qN/Z'SDT9o\)9M=A(XX@&(>D]A'kZ201t5KrS1J[\]AMH7
_I4@cMg^jqr'MpBmAhcYl@bp`8`caXA<o=\(KMu'()`aU_N*;Bn&`LA?BScW>KuCp%TQnj5T
]Ar.\-X:?EAe'!qJi/mP+FIVpe-N9r??!ZCE(<qsB,j0,#L[>ms/gr'BjDp?7JofVr0fYS^J:
o-!GaQ-,&7QF%NRTZUN`1fga?VoYD?KJXOC`-P6UU3EGUHG9GaJZ*3u<X]APDVJf.sm,;^;Rj
ZWiA`f+>?KTWcnr,,;CqDoX0fn3+UC[#!ln$61JGduPC4Rk*h"lEt8`)7)P$">j+"4gV4!G3
F&/NPG%gB5p3QlZoknFB8cHoT:%&m5j9XcCuR8(2QS[aM"[@'+Q20F_<a@7@^T5OO&QQl%m,
$cc)mP=5=i6.dn$5f9aa6sb1Up-bUc?';IWK>U\6V<Z"U`U;64Ml^o8(8ee:g.sGg[MH/>aS
-$8rhV(RNr5.Hi+Y>b>FuoB6VZ\&m=\<k)Q7I4&3I^rZV]A(@?:\LFoJ'+ZC]A]AO8o;5a$3__A
9=o_f-G<Y9QVB$#*M[1%n_*(UV.;4%,==gc8ZuOtF%BbIN^I)6=:&SQ7)7qE3]AEsu?FfdjO]A
d7%:><OL\np)KS<i5_[`Z)Y-gclJ"_94roS#M?]ANam!H;h\N%:DPC%ZmKmGg.=S-a<FmX+_.
BnAQ?q]A`G+G,)aFKKUHfKmK#iSgL@1JgF;O464MZRC]AbiX.;&;1n\[W#<L&TAQI^:TGjJQRB
/a\8MSoAp"!'=(.r3D`g=sA[DJhXBj^p2*I.$ND?<We^hct@"k1pC@NfK.1".pR?/M&!M[-]A
E"5aIEm5]Ae)OD*_,I1]AYkd[8ODOoodFig5NL#2Q]AcN^'K@R.dpNFdDnsB5Ut)\FSBXeMmSO5
<qYlh]A/`pLOY(@*)4iun,&2?>YUZ,W:.sChD]AL3Q,7:7g]Am"+%J%oT=^#-jhZW0.5]ACW)]Af5
7`adC%3^@lum6e`O`JRLH:+-j[id?C^c2`=?8F%K_'I*'n6gY\G"bNF=k0D,7ujat;W$F)]AS
7GF?q#-XBJ-%q:Fcn[D=jABTqE#(@*p%dEHNIgYR\Hp4[?cK1uX!ScZp>F`n\K.8DC_>+Yp@
Z/QX't<UeiQcFrVir(:.?8a8(\)lrVQ4o4S.LgA=TP?N)ioe6$`.QcqlJ6O<OO-_&71(ce:H
jr?dj55Jk2=@i:Nh(enr4X@Fa:jgq@@V!WH'G;m&n:g6NgE8nrBQc;kH^[L'5b@W=C!j0TOG
-#X]AJ\1)c`oLSeD3sn"l9:P?tR\&PI3'$S1+PO+CABDa^^X[us?Ii@9lu"eI,.Cf/RZPr"$!
[P-2)\0-\(AK&UeXBj)7Ia>GOq5A$rT=#T9BJ^T<h$9JS-b,Q^=%'4H^ok*m;2l//*MZR52D
\me[\9kJ3iE]AXnESMEmg.Tq5Ilp=HLO4]AMkS1%%2Bj-(S;e?"ut@kY/><KUT1r3$6+!GXjb3
&s>YU""`ba"a?;H-AW<ccs^'(tJ2FFC[3a%G45QF#b3_DL[Oj;]Ac5#,]A.[]A4n'dtp]Ac&D0s3
Gp$`m_l+5uU!>>WC%%V;NXT+sY>I9Stg\Kr\&A$\VKW0G+8SNQ40SB-jO7KQW<G%c%8If[2,
T(G4SS5/7/W>O]AE8M*0j+BDX2+Z7GYqP<cjG(.Vo=2]A;$oLE#h84`b9`bI,"=\4/e`cA_"Yk
L4'^Nd%_W\X!jfCQ<>e8[!YX=\J%n44*J<a#YdM-bqebr%[IJiKU[Rs"_<Hj5;$+hG>D/pNs
=NN1F>/2n-0U*iYC**>sZKUUf0F:$ojb]AOdr""<WdQ(I)5>nC(YTP+d!&k:O$R?2c:0XAYVi
3k,hLsV=597+3\rXS"cMhoh$\6>:'\7EM/kEZ:^ZJ-Li)!2AW."0Y-4Zth5&a4,=R1%3rJUl
d8+s@g[gN8tueC/6lq'?ED!`7'91KSn;?`AN"VRD]AYj?';M3qlPGq*s<d7K\RpKL6JU7l2U\
$C.ePgg_-FRb(2FA,,eXm;L7k0WU%\Y[Vs9bPjAUrbOg0@,g4TTqNV4X["Q+.1sc&[[>a8J%
Q/rEcs&c0BM<8+p+l=%Ehf/^o9=m;d]A`J$Id>Q\(5\9GaG3WinGT,,dj]Ai7B4<YX;i$\QLXa
^1&F&WZ=pWL7$$RXGKTW)r_o;GF\:e?l0CY(e`qU7/767S;W?dG(6^JIXp@poccc2iDJ$4=M
E!N\`p?6@r7KE`fWf3Xoic'@=]AYh'b4G!,&3?s/L(^78j.<,I+eVJ@FXr2H(DoN:1m,cNUlc
=B\d]Als8%)1EQMH_I/@BI&Qu-E2o;U?=/>(5UCD%>?LCB;,6Ts9K*)]Al+Nr/)As%hhtfWd-=
_Cbui#mnebHIZGI!25io;\ijZT,WQZfK0LY/ejBU4M5I$9P3jnN[(qho,'5p?j<;[&9pA,2I
;Cqhi&N?]Auf+Lm%4OBYhHXudt0Ui"9pC&/nq;epFqVWS5k[g!TcLb&O"<Mo$dbd4bdLJR-Q;
/UG)</[_%aJ-It!G6Cm3%2+%N\Vi]A*g^t_%DNHPT#8T+5edblK)Z#>Ul["82kbe7/MP%M4ZB
n!@$rka2c$fEfs;*\TIM^ab@6eCm\AF,Hs#UG%AQ)?VX"EhU8+X,5^,1A>R3VYQ17+]ATe'C:
Fl!."A3kkWL23o'auLKbC-#4Ia,Rm5.-I-,e;R$gYa/.2J@(j)+4d9,g_/>tW<c_0(;S(8_5
^71\%THg0d?8*fScFA=$R_9m)$%Js]A52d1V;cQi:-=!+<>C-.MX9E%1:O@KKh(^]AVJ;cL0!E
=-p)oiL(W0ZLk9jJ$BQO9Dol38on4j$GYe'mL8a?]A+*@V_5qiA5I#8H_6SUq&V06D`WcA).[
O8sWXi,Lrdl:"@*(a#2J^kA+p@@kbQe@:pu=mR^eg;!Z:l)M0<sr0==dMi,SlpIPL8>XEX>;
MMdMja]A:p-JIYC\Itoj7aAsWbR&kF/Weeaoe\6PNVs7n6Wc/^5)Jq:3+;"_O1?#Z/\h]A%4.a
W/p9,S*Yr`,_qp+pPO^)AgM8&$FOq@-RUsI'4b;<IZ#G:<>QT^SK9A3a8jpbH#1G^WU[l<@$
bmN8ZBl5N;cFPo9lFGVbYVSaA9O,59lDMQ'k(hG*pP1,@pumTS#TQ@.$S&tt)^I]AW+>J^<-M
dQpRsF?=,L5-oltD#?X`;Uo")ss!4UZph`1[AiNn.kPZ<Pns@<N-%VgFT/U*.KC3A*`3!_LC
ZTD+->4sdk6#G)S50EQMYDM?rOhZimE$o_UjW.c-?10L8+n!S0P$dJ$l$pG=URUYn1-Gj;L;
C`Za'o?";NR7`uM*pM:m$kIST8Xn>SchFW&3ERU:Gr)-jC(%8;GN]AN3<;<RO1-e*F#raaMNs
MO`hWI&#Qd]A]A\--L'\`^L):uXPoo*?B?-Zq9i!7R_VN&;ul4UD`*CX3%0RYc6VXZOl55Or4J
l\F!d*BC*T<YlUi=oo^4E>X44i]A]A'1VeSU'aC`s`5[HX>7TCk4jS,G?,&4NW3cl$@>[1#P%T
"'HSFe1M=`.o"7m\S[<N5674]AdBi3HD5dAf*]AoQMjhAC!0i0\JmXl4>H6)e&/H>!6a@SoD6;
78_\!ub3XJSA+0k?4[J,Kjl#o*lMJh[j!ABmI))ft1?1U,P'8Z\;dNeED/`=GCV[,c!F0^SF
NW/,o]A))9MVft_7(pX943t'O9X-Q+XWG0u%DE,]AL-hk17+#=9j\_-]A5MJ!.\]ALSc(dWO">qr
sqc,)!a+Pn=@(VY9"O,_ll:VPU!]A-ZApnQk6e_0(.#1ofa[T1L&a[!JHo@).n$!S<J;\gj,2
D4ZYM(4b,<KBg((>_`i>W5l]A[HBE:=IM.aUQqs?E4Z'+3jY6jYg@2!^RDKS%;,P^q>&N-K2Z
NHP2eRkB$2*+l>%q,Bpl#51r0%Fq?;9=m+H5LSV9-/VPYrZ/3]A5JjRo?.4.tI4'?6$'n&Pma
Bh"4SW):r"EPd-.a+>DJ(2_S"8Tn':dI(+Rlds7e,=%C%cCgS?g]AdX/Z""E2fJk*bS-48GOH
tOcaa\_LA\k]A/>@6*!CWgCYr3le#2g?*0T,o-n2\&AG%G^H;bP5*O`qf4=N_kLo!Lrh*[<]AV
^N]A(3nP0X[fR]AeN*nX=Sr^S/FuV&e]A,O(Ib7Z`Lhk>md05=I;N\QhtBE^>Y\(lJKT4@ae/E\
8#t$;3_QqTQeL`F%dA4aXKX*lPNr`RZ==:['2A%UUIMjU>[$No.hdM:RRX.,.kFb[a8MSET/
hn:I0<V8mknN/d\bVmEa)(D;l!RjZ@R9CpX+GnUU"EL.s+]A[$%W2ZEd?u:OZVphJgUinlcBg
2"Ib70#IWnn92dE)+RP!27a`FcoO*&XV4qpQ=7)!Dh$u"Ei5Gj1QuhHRH.1>7b0H#!mrj"Th
aa$MR6aEuJMN3*;Bb2K&e9n;0t4A/_)+49hm;)XT/j4CLn3W-aRTt)_B9*DFTB/&+[Qdo`Tf
Mf\sFbDnVtjk;-p6fWUWs%f&Xm2A#HF\o]ArJb4't(G/q&+K>B%ErmHpuu<P%>0j,seI#$6#+
!=*KgM&+5pp92QtgV>o>(8i**pK+7t,h+&+/k'c(]A9@.__81_d)b:);]A='QSDZ3,E@TggH`R
^ueUX^cj([?Q)H:<dghN+quJ'0EPIH#$j;I/a1fYZEM*FYi+fXc4:B.r7`?I\YQNt,5O-+O&
q#(;3:PWh4hHYIZDnZpe-Ol(%i3>`aGhLr(&;ff#F(#[&V)a(uX[m?Kf)0FTVc@sm)?S/lVo
euMdUdonV=3PHSi7>abi3GP_b.7\3&<^/9,WaY7G8q`g5=MT;O*as<Z[-s,Ni:L9`O@flZm1
'VV\mADL^,dMAH>E)Y%j2sb5.;Q)q]Ahe=]A!G.VIWJGEQ7$8#\_LA=>=^9TZa5eQ[m<A5,W2e
Q"EOa"M;_%Sg/o+!5]A`0HbNIJq0Yt=<Q1ZTTIZoC_0TN$s(b^)]A\)VCMDQ/OhRRI,MarQpMe
k(o0oTXpLtK9@ZS.Eo@SP$GLU6Te!?ihF>nr:_X0k&lmk%kbj<G4)iKD6bXCQ;@JhO;F1J(g
*`S6T^@i0A`Q($(EiT82Zg9CdmW.hGKY!:iOJo_3PU8OkNBJ\5<rpCfPrS"%']A8Y1+B,eDEY
6TGDRFjf'oLh$o6\ds1/@c^,PB&:^%Vfb<Q*]A:5drD'5BCX7;A*fBLFYG@H[9mu$O-5tL_+<
=sh*n0:qR%fm(0,"fm'Wgp05m[J0aFq%*S*j?'g0#G"91R-Y&[%.RmgLUKiSq/+lu+Z)S)bn
9_Y'.Bla$P&HoXA5"qlJgtt!_:iORgN#@qg.X+fJhLPgSi(:(]AefKGk"LTChI+W&iS"\CW\O
6?jhdEY63[u2M]ABNu0SoKd8R*A1fj?>cUr93,_,O"6D=%=t[rPNuqr<V?ng8ORO/7m2CM6E2
4@ul*/f%;JWGH=".paf;Sd]Ah85kg=3`!'O3RS1!=4[lig18gj&"fV:"6j-;3[Y!$:(D,i_J8
V)f&8;J[`3c#dY"n9?&ePR,bZ3>qqYe/3mZj+\['2K!g?_q:M4QhHD+N%\Dmln:_CD!C1Thu
n3&FQ8BE%L:D*Y>W[Y)_&NSk[ifWH+Vs<C)`5140L-?SYGuA4-k0aX%H,/b\)Y>VPufN?gTG
)CSEOgBY@bVEsr#Z49Q"<f*gD*Z2B']Agk+:Q-Cd50+>q.R3OUCO"=g/j`O03IXjc^Em>G*ZX
nK9")!"SSubcRF@<NLrCk7@lcg6;+$Vq,#_ItKnQqL5f<J!-hP4$>2<!^Ra54JB[5a)=<DXe
#_n_G^rN`-t=Ol*\VCG-e[O9rR1C6V$0'AQDaGXC/68PaN*%A;=!ACaSQt;2rrU3JCl6,FT,
VlJ2=IZ827>gcsZ'eAqZG*qcf=r88lhPt<a3Z'%OU:C8WLhhQ;?l06[&$2HD79E&=oXYh?/)
#7f:1BSKn<eeed_&3*CDMADqu&Q`O=FVkELti&`a'B^`>$3BaKFVD&U*?g[&:WPtdG+.3l6E
'?1g9).U%EZODN@or]AoBp68/RqnkYN46F#SZ(f[Kem$9<(^YsOgDu^4.8BjlT>3?:+"$_jOM
9;bpXDDj1b7'bPrf7JhK\5r]A,"Dg;MYE*,.mCjOf";(,4<d>`cA9c$CtJ`OqT*JX/\h/5ufK
g/`3N.1B2X>M9!+</'nA'F.h>$*6),C$ju(C@DeGn'W%'T0W!>X9UIB*R=3$P?=JCKC&B.f)
k[0YDPu^EbN>!X1@R)*,lJM2/->[q,HYAQl8I.sMt6I,T#SM-Y2r`l@7!m8djJF,PB;D;aJb
NXQgDj(0.nl+/>2CcP'e&p&)#F]Am3/JR+d8$IN![ZMMiUe\KC8HsO@B=c8=%EeAq_PePQ*Gn
d2m4/?)N[5Rso9).4Mfu\8q-a@Y+iq51k3`bHD+3if0U@8#$.TN'"]Aq0JT+ZGRcKa=YY%]A=6
W$F`m^9EA]AN4\S0M\smWe\/c;8[cG6%T$\?&/3WsK2-&`;AL-^'QAWUeFjC?%l+Yp3shbib(
keK0u[%?,]AHJa]AR'bd/Nq-WCOk)LPbraZeL(]Aq&HbosS:/JXBT8f+%po-*P;/Ie.Wc_MB&)s
%^h:koQsbc:GI"cI/i=P@MoP)G#"$Mll[b6F/Ui!4SZWH1BDDL\@GE8h3tt-n"2F`Ye0A\(K
9A_$k?(Es'8Y,9X+n/t:VNYtp-Z/77^Yg>b"<lRncVnEQSOSh#6DT1u.0Zm0#M2FTCFpjC\b
]AQDM^iI2'eV=,/@3aY0Q7DJQP0tb>lD#a<!0E+qKf#<m[+m-kWii>_kV2if]AcG0)EesnO4\`
$7"X'2%.mE34\PK0<=jV6&Dm&QZsdU*&^_-8k.`A7.W3=I/K]AKRaMb747qXpD4^BLr4PfYt$
FdE:Iol6+B#YX.GN?=g1TVOGu\h74al_N5Dhr.9)_r6Mb9b.o3`\%d7J[ZB3h<O&e&210!Z\
:`-&n4.4L(9RY0>%!;_TIVGl".>f1R[NNAWCjSchh_QRr)e.Z8'U>>oj"cp6`9:F*]A8qY4R<
sW<Ql;RX86q3&$Kf_e5"T:Ql+=mPQVJ\7'8n12#o7`P;CPV0b=#Ac^h;J[6=K@/8jo8.S4o2
Gn8:'c?$[2apXWDCD`QqW5IM6h*+qr7*N5GMR.W3f*2gH;YNoOM/$!aX#CUS1d3caWbJSi<2
McG]A,4cl-u_=dm'1Jg($00RBW0K`4pBS(]AfgUD<+hLika!1)6Yp$C\)'82`^tAZ%?SW1Zb%"
MP<kAm:P[6,!'X='qApDY5=XOr5B/(:+1j#@mE$e-E(6'D^4*?k"T`&:3N"%@j.VLH!An+um
.WRt1Cp#WdpBQ:<9/HaS&$Q1MmZ'6`W(#N!uu'2RMaO[V9Co&<_gB6,2s4s#OdDQ/li(4qAb
e\fs3nTg]A,MmF2o4f65gd^3Eg7^d:V)g6/@8komHL64TBgCX<+-%^I@_^]A/%AngBSCm41!KN
J'nj(n^.>Gl?Jg?8_YnsHTOi-FUKOZf+q&B&FFTf'$p/5i?Hj[`aKG(5UPoko^8\a11PEn'a
,A@I^%NV,UA&X`3AP09bpp^hFeB&TZ8;fUFb\7'#DeTq3dY,^<Q0P[85ToD(`A5CBYZj/hW/
n..Q-r.T#D5V7aI6ZP.+2YSW-%oS"oAS8+*h8Qnt>P:LEgiC*D+Y1^2!6KL!plCsL!h0%S.Z
>!<=@*+DQei#V)S988+,Ss_l$t;8cpahH%pS&$+k1O6d(3$oF%aOjp>8bN8;La@[[Ieaq1G2
1_`luTYP.^cOWL2U--^CI9USb5"]A64]A$59K,lO#3I%50Xbe5;#KAGq&"Q;HA8"93iWN-PM$h
(Y!SKF$$G_HHDBsanIj(S:[4]Apgoea:IT;X?Y0?t&YmSZkiF&ta1dA(m6mEe?4d%;-ONhX"2
t,Iq2Y[Ndm8E87H0>+&Jjb.Z#AAl.2$6n:dYDjqE.Q04\ll3/4rJoJc0/&E'bp/QIhW4=ks!
5jP+qCNa1qa^:;.h^pnADR\q5l#^2G;(TF]AL\-4G9HK'a-hAP?*am)0fL]A".G$FrZ\*GQcWd
$,?/B.Ipb2X2Moj'(fDC^5o7[bG"9s*iES^"Mh8ECp@rZnZ^$a0=Il9E<l9;@lQg@;c9/=mT
hL>`-\b\]A5Im+*43Rd^$`POr(_R^G)nga[7D^=[!6,rW@Z8K^211<7()W>I.r9j#3J;rBWG7
&QSb6p3A91J#fE`hqO@p<fNG\:r$`?;o-?^PFE"ljSj;ZTo9[Y0^m[CO'(,)/.!.7S+mBSA4
,>+SEt-Y[]A9:]A23'o&nbnn(3jgsCXrd0lqYIOBCF%_ZPekuj*T3`![A!>b`;L,u).(+s2Vn'
5hFB(iJrX:8p)C"&5$&X?V^R]A#rEgEA^J9n(J,8^iT1uH\?9XtD\HR`aq!OX:s+M]AE"4HI`'
KOp2lJ5=7%sbEUi=H#u\@_@PM7%n8'&8>o>mmf$^kBOnBAo%:C$7b%\q<K+CB0?IMl+4HhW4
WjgpLFR7<Q8)_LEb,I1;g4rJR9qrr;\&WcT>TM5=cjWiFU!&2L/EF/41@aod>crmUsGf)@mW
(2UAg/(%NTr*plJK0WR&elV?Tc-#6GT2#Q3!)4R#q9r7i;ss\/O)>(W8VK;(QH,%%UhXTE0b
NmS\g2#JXMr/tOn/fIf8d"[@6,^Wl\U+)Qrj$*V-B%W0!tYs"-q;Z^=MV3OU0g#HrUAfg\4d
^rdD;XFs"I4/9kN5F:G]A9?c-a\2"?>.9Xg$C.Qrq(r8C;CO>eMIjGh+.ePG%qpTHiErc./U4
`"tkr-F$b(S+[7&NJ]AI!TIHij!R'#pes`rquWpSID\R#cY"7uo)eV)D-Wj.b`0=-TDkU?*3#
F+5HUdOhrOMb9R[4,jEGJkB0U"Z\n%oelC!0?[8<MX)>@YN<q)aqOKQ1:^[FelT;+L`$J>P$
Wj(Yf1toeS]AOHE=qS1_O?^0rDES,0d-]A@FaN;j0KU;SWue"#Sjf3kH'=e%O&""/^AN;QVaB'
8IY;fnsWn0TJeI4[X4'i<TWQhoNm7<XO&TLRug6f;'Hk-0KtbKPA<@90t;?Jj5Hq^qD6>V?9
?S0V)B]AJLYp((pB$rq'T6fb89e>\K$lZFHd#O8FT<C*kR,-b+jWI^j9Vj#s6bQhj5eE0J$Iq
<MPjHnUWbp8ntri@;UUq9)_%^tX:\Bj]AZlq3c!3N<09VhoXm5&D$gZ%b'N`P,8Jf"#l:d)>m
5Bk6Ct;/Ii>shau6qP6e_'Q-o:e8ZcP-6[[?HFRfGP[L2?(Xo@lHf@Pp<!F&8BPd9Mc=5nTN
fA9\(L()gb"P3H3EgHb,C`,\@4C8op#0NjBhG\?2W,h:gi`<lp=!WHCqZEK&<l6-L__$==Y'
,!4iGVGG:frY44Y,CnL^<0a+2Si2\g8GDF^c%eJ#epjCI3XMp=0#LO8F89?"9sF4sao7c0:h
J'GcC11).3-gXt0"<G[+$^\87P7]A%?DfcJ.87/p9q?X*hf746<2l[Q1eppB)YRSe=+LC+h'8
K?erret47q$EpAi-cBS$0/gI%!L1dlDHY/J.\,$a"\`0HNZ53XRGrn(uakPbHRGfQ\\HQgaf
mq$<so-ou3i!$fh##9Wn>FmsTZ+>O6"$KS=J""S(AYnpA3$&AIfKkC6ioA\if]AN@KQXX5eU+
+m8F\MkT,iP<!)\#^%A.i$Ij;bLc\JOBfBr&)@*oIl/.c*R!&cEXX_/#d*lU!M_q(.4Ot6":
^mAHObL%OG'S";gsF%Td7)HkBOVGa1Ij^)nuI6;trJ2#+uV\P,7H.c55%oc=+d5Kq27=*Mmh
,"^cNI"7-CPr6mg&#[q"1m%5[[(Ma&nB4_gMj>CBk#7W8:.:(?2S:&rlYLlh$`A]Arp6!3lm)
HUdq^Cf$CTY,iFVug;a;[:Tf('s[(]Alsl-"es%^2U:BQma&(?qY2D"X'p<e!)f.<NV_0hWWp
f+n)BaaG\4-D<C$Ng7u.f@"9lVC>XeHL$gps.s50Eus"!Kr=M`mPpRtBCA!_AAPr9;/[7;L9
3br24H(Em-;K:P"*l4B.RD;l9C_'`q,Uh<ieP)L#RYmd8o'GAKLb=)27e_53g0aFTXI.QMQI
*?DTt^X)<s0q26XfT6bL&H2@4mqi$)-B=[+;h(lPH"8_&t0$D'hJJ'gc6d/ZMtuA7a5]APpEM
E$Fn!'hk';fc\_Pn4P(7>c\uB6I3borOP*QDNY.Wsi>KTpRDSnS0*h>\Xu*jdpA[CQKGql[J
'Qlb'/e7U8S@r"N./ap/jGQKSqJ?p`H.#\aVi8iIO+sE'qI4&\\1B2>)SZa'<F;2)S[]A%gEV
8_q(E.XWr_(KRH6*sG67e[`^eJDW>8GeM@d!]AkUZCqTIm5WRu0(SWiA"*)]A.eL90`JClm,?i
6h)_tkqfXghSC!/&7mM"fD7E2GI:I\G9[^t)r7Q?C:8[0/bi\5(VHG-jDXEfC75=d<eNkjNK
t*ql\pD(2n4bA9bm;en\<oa^djS[O'0=/j0[C;IhX.1d1!&G>5Zi:O^mJ[mMukt5[^VT%<#c
EJ1AJ=F%KL.'8(X:f96JK2=uFN&--5-I(?,$=Y\)JY3bgs0N\fRZ2V\"5Q=m(M\H9oK/&Y/h
CTuHIgr+G1u)0`o9p'S9U6+_W;C5g-u7t[bRQbi]A,@NVaKraO!dsLd93jN85="\+q5-:hl.7
:FJXZa(8p&/dZkeo_%YQ^p`*X_LUE=]A%*T7RfMtMI5PX'?X"SAuF:FC2seh%d:9?`Cp?d7Xl
T1a14%D?,6G:_2C?ojpQ.fm@Gb%oQXVA"(<eq,ZVA2eAgpSF@)/K;4p:33Xsie^+2-BY&/Q7
%nd,Qg#.`UV#\1^(a%G)60&ogXK'B6`d%IbkSODZ2j~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="685" y="0" width="275" height="224"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report3"/>
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
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="685" y="315" width="275" height="224"/>
</Widget>
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
<border style="0" color="-14669005" borderRadius="0" type="0" borderStyle="0"/>
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
<![CDATA[1714500,2503860,1939264,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[6480000,3810000,515500,6480000,4320000,571500,515500,6480000,4320000,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="9" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='  '+'现金红包投入与发放情况分析']]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" rs="2" s="1">
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
<![CDATA[80%]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="96" foreground="-10502420"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="true" x="37.0" y="37.0" limitSize="false" maxHeight="15.0"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Al Bayan" style="0" size="72"/>
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
<FRFont name="Al Bayan" style="0" size="88" foreground="-10066330"/>
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
<OColor colvalue="-10502420"/>
<OColor colvalue="-16118480"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="80.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="列" valueName="值" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded3]]></Name>
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
<C c="1" r="1" s="2">
<O>
<![CDATA[&yen361,16]]></O>
<PrivilegeControl/>
<CellGUIAttr showAsHTML="true"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="3" r="1" rs="2" s="1">
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
<![CDATA[54%]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="96" foreground="-149760"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="true" x="37.0" y="37.0" limitSize="false" maxHeight="15.0"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Al Bayan" style="0" size="72"/>
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
<FRFont name="Al Bayan" style="0" size="88" foreground="-10066330"/>
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
<OColor colvalue="-149760"/>
<OColor colvalue="-16118480"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="80.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="列" valueName="值" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded4]]></Name>
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
<C c="4" r="1" cs="2" s="2">
<O>
<![CDATA[&yen29，172]]></O>
<PrivilegeControl/>
<CellGUIAttr showAsHTML="true"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="7" r="1" rs="2" s="1">
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
<![CDATA[75%]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="96" foreground="-1441703"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="true" x="37.0" y="37.0" limitSize="false" maxHeight="15.0"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Al Bayan" style="0" size="72"/>
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
<FRFont name="Al Bayan" style="0" size="88" foreground="-10066330"/>
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
<OColor colvalue="-1441703"/>
<OColor colvalue="-16118480"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="80.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="列" valueName="值" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[Embedded5]]></Name>
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
<C c="8" r="1" s="2">
<O>
<![CDATA[&yen4，999]]></O>
<PrivilegeControl/>
<CellGUIAttr showAsHTML="true"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="1" r="2" s="3">
<O>
<![CDATA[已发放红包]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" cs="2" s="3">
<O>
<![CDATA[已发放红包]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="2" s="3">
<O>
<![CDATA[已发放红包]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
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
<FRFont name="微软雅黑" style="1" size="128" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-14736834"/>
<Border/>
</Style>
<Style horizontal_alignment="0" vertical_alignment="3" imageLayout="1">
<FRFont name="Verdana" style="0" size="112" foreground="-2297870"/>
<Background name="ColorBackground" color="-14736834"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-983553"/>
<Background name="ColorBackground" color="-14736834"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m(?sj>lFCU]A(I1<P_`'I4\-iE-R*jEUJDHK\TPb3<KQDr3ml^GU5aucHZ)bOmq.qacB-iEp\
UTSi`tYm?9Z]AJg&'L\Z.E0O[FSumD]A(j0lA?iTF@6*/O=38AWXSs<!<@WjGQT&Zj:q00!W]Am
(Pb-%3n4MD,-A\Qt5R#TWC3?i/`gY\3qD:6iNUP]AdQSLdJ0%m3<*a)(#VrW*bUqlSBmd/b?i
O3J'bX:-;La&f>j(I;arJ5JT(?i2f?SC8-akPH56;int-Ege2]AnUGl&]A1<<U*7#A.DI&(m=N
G%iEAhhl0hQZFZf;Kf/o*Ra!Aaj2Z*1Yd#duk@;]A.oSe6c"&3*CKE*6!;XIhIug&(sW'q]A0l
''Z\&EYV&nY"q>AI/?c,;An?F3k=$Jj;G:RY'4ZH?%Z$TO)A7<N%fd048.!C:cC]A%lr?NBFi
jti^,#2o]A)'bGF7G@^+pTFTl!\24VCjdMe%2VF=1=Ps$L)JEpC]AFg5NZH4RGu1jl)9/9f7(h
<dmeL'\4T_"FK.K3mYd3C`,>#opFo$tlO=Q.AK*tXjrL=t@r%R?1>c=Y`WKDlha,W>2I*Bs%
p#7gL=QQY)L5bq::rppU^Ib=A6:N2WgkPmn*I5\P<)#tVX5edO_W9Ncor)Yg,bNMP$>nF")X
NNCU`!1]Ac#t$?`U+SB\;H[e9_\[]A`;6G?LA1hoP?2neC(h0;BV7Aa+98"M_s.rF%I^4]AY$#n
\a"KjSP;E.i.,^$Ah+?\/t&%BHn_H&e\WNn$!=REHbp*mIr`!\Es>9[4e;?E[\ek+IA+!S]A$
I-)_c8Kd+g@;A8Q(e&s,8%Xgbo5l1i+\EBfQ^1?q2C(O[q.*XR*)B+r8Mq#4)h+M;OKi<C\.
_PgjfMOcF@o&d=*L4ckQ7r#gpiHJ!0_.2DRm0^1DM^SDU8^s03$jJe%;%ab&V,Z\@Rj6uWFW
0j@@RB]Am)^KS,OU$mI@Jt;pWfE%1k0KiH]AfBt_2aeZAj?rTHVkO.[K"^j7.gUIio/$hiT^%t
BDDiUH0$i*=,>2T=M9<`I3@+T4t4bg\QFJdfE:"hG:>m:pgH*.;h%**#_BeoLEc(^4Es$R)*
0/o4[qp>,!N:XPc#kfBZ6EgY?0XidPNE[`42(]Aa5W6_M:C?bQ(17p^/r`n'bs*qt&rYWEg"h
R$:IfB5d*DBAm0KKIBrdFpED?Tlaru_l_nb;aTbPuaH'tJjtc;%C?qsT0`G>`B^BJ7F-XM5:
;P3Mr&p\L[fjSpF66N7#\3:oK1k6lPNCmKRI2/j<3#P\5hA^g[]A^#3q#f9BRVApeZi`>h*gJ
c"m.-\KG3U,r2I/+(eNDW$/[$S[b4mf+S(2M/_W[NB:H4caB3(R$K_o!j^oP@EBr(Df_CJqP
P0U?*I*_,_Z:4Y>"(*XEEh!O+ePj3$^QiD`gk/dAXLQq@pX+=Fi7!8LtE,6qD-diNPOh?;LE
\Z:6lSuC9SS`f!?ZNT'F"fO97s2r9nb0U)4m!i#;MnK_I9l`?<Rc#[f:!2k&0QK_O0mU&Aij
Jr0LZ<pK.#a*T=Y8HLS_1gc`dcLUGTYliW*.NYbC=L&%^TL6s&7F?;u8YHecc<NkORkIMXeh
pet&5o`;$V0!j=-_)J>W+LrE$q->rSB&CE%!5?P/hq$Y$eUNFVD:&cCIVV6oG57l4Pk.f+W>
Md[%OH\Ee?JC\F<<f(O_Ul0nF'U9fX1Can#E'=LX:QSqBB9CoB7orf(,+pn-m=>@lmtNu"[D
jC?t#1IcJUQprBuh$RNP/YatJ1bmPe@DG#i0W2c8m;2c8r5O![&6RMQtZ456R63fK_@SYqN1
ARp6"<p/SsaLBe5R]AM.Ak%iPfQZ4R:Y=eGuBPV8nUa0]AsD+[j]A'I'dS^Mj\_URgKH/=Za>W@
?L3g%Y?sglb+sm.7>c@Kei`'RtkPNX@*idOV1EJAYJtNLms6#IX1U1fc&AX'r@T;I]A[%`OiQ
U>:0"/Qi$@qB8SSO&'sQ_n\+Io4R'R"$dK+t$__:)]AJ"D9=ao@IE=5Di(/qhdABhJ+]AiT0$?
;8<Q2^!$fW,HhUN+9Qq-'27ipI5lNcGp5i[keG,;,sjVm"ujXg5(HrKUF"Q@Y!81OShpicZ(
;?2BbP-U&tI4IhV^H5JHK/<ol`U8]A+%UYMh))5RN@]A5Q@<SIJVK!JsTUVd*Z&]AB?7N&ig/r=
Su?6b3E9Tb+/J`!?Zeca[uXeIO\d\'SZ%:d!5EV<REjeNAI2[I@;7A7DbV]A"eZ4q;_eCtO#Y
'7b;"e#S>A@lNHks*993h:*PhB=HGXXStm7;(QmfW9+?W&D'X+@/ldd.:QR-SiDU*0%X2>+(
l$/rODPDJb4%i5X3[n`qG/N@F)6cg5U6tp=e!!U/m?BDT'l!-R1EmU_]A$N>ZDm'e!E1Q5eI.
Wg?Sq00.RP(Imu&V$nF>dQGmm3.-RD%4g<7\/td8')DL6ma"`.6k+CE*=f32f`h9*L$;_4SK
^uVf._?'/8nf&U_9+q(D?4<Bj%U-@fs"p:bLa>b-`D2Uj5Q:W>Tdd0"ST"et=b_IoIko#b^Q
r(ZUk*=,uH(+=YS"gN@d\T63ag>5"F@6eA@cB@,F/9PGndBm-<S&%l#B+"W.-)HC"`;#tpcP
8f`3KgmXeA^NVLtcHbD$Tt`[1WS2.CC,4:AZ^uV/;A@%V&1[L5s!.d%c82=El4/\Su.rd@0m
1H1K'Vc3P.WMZ?i@efqE@W--ssOmg/(p1q6HfA>GW(+iZS,k>PXic?KREYe0%eD>CQ.OGEU#
tt9"\ei=6HG<e(B:]AX=Cr+V2$UjG(EXB0.5B@dS&5uQ?-C]ARV:g!KoN(icQYI>2L)=u216PK
G6qGKa4iab1!.4@+"FQ#gh1H>1/gt&!?4ck$d]A+@bmYi!JN`bVo/Ie&?SXhPA&W1'q?aaLN^
7a6BKkUF52[D.$-+aiigpVT2CRP97Y]Ario"_S8r_$B32WIN;m]A+=hEOCEWHqjeBR"M`I2BW6
^C:fdMr8R@JpQ5[[E7Vm0$qA\)83;V[K9O@rmcK3qknUS2UkD9,JBW5)d#[E?a;j]AhO?1fQn
*28"6G>"Ogn?5pBO#O)4H"G,4qB1QUoL"7>0-nEr$V;26<\&d@cQHZ5AQ^lD%"uRdS=>=Cag
uZ^3r8EXVm[!+u?fL)n4d8IH15*8/-]A.\Z0e5#>s5O!g]A*_'%pIXXFCirr27u4(U(k/JlLYr
kAg'8)Vo^`EfGn>;2q:0Vm?klHSU2e'nQSTt^]A%,"gBTr,@dW!p`A<)XKs)blOdqN`h:U^@g
2dKBO=ISCOBjlI3L$07[]A"\gF#Yk3Zk'EP;V/Vq;p5Aj44&h*!7_@QXhO-8V*[o;G1#&7'*2
nHOh%_thR<&Z3Hm\Jl_6aNQZ<2fS,H5=pOVBc=mPG]AOQrs21CQj6a(E.??)0fGad#u(JmLPX
B6LM:rpnR7#h'c!:qll;`g=hd.\4F`ab=ArEl/cN4?]ARdLIsj\1cq!Je5JO^R>4-iRWqnA+Z
&C\:h,j>S1[?E6h8iMg[&rpk3nXUm^dOS^.YcmWblosVs2(\pe=:PT+\"sBPd7BraU"#uZIo
(q?5ott>[LQ<';t]AbfudM$mb\Q1iRe_S#PBoS^O@Mrk5Mk5=GIS3?.,!`Q*qc]AkZVCGaN[ZZ
W:qmCS"WeVHMsip'u"\5M^N?E2OEUsS:@P533+^t*1?=Y%)aIic%eGBO6WZ6gac=\AYF7Oc@
:l+cd+LRs0/]APHmoOF7'\6Ugm]A//K%QYirq]A?Vg4?+dfSPb]Ak]AW&>>ue,5s-\_^kmBUE;U*e
gkV[H0cLMQ/i"5'VI$pGPp(5++?A`c"&[_rOl=qm.oJU%Be8(PH^4b+M=Jo_@&KZ/YRoKseV
%E@km5o86iW/d$Ynan]A,!=\T'_K,8?S+>47GC$M?t[Y51(n:?a-E7WGsfU=RKP((#<g3ci?'
UH"-9:c@k.!29m$\I.9M/XRd<9ha%B/kp3@lYe6cm*<5<:e/gWaIRr1@25t.R_aF_F.T.\U\
eHp?OO1n.g7<!h8hpP@s]Ac?9Apm<RT"VXq_ffVdEl6Q;Wp0e'1L<niQ@AfN&Y`g<C:B(kKDS
DOCk==R\R%iOuqr%."BpRbbQYIAH"M'+hPk@IP@&M8>n[+=$>J:Ua8FPd$WFS3hjn>`fUu1"
nIF;Dqi=?!$l'ZI9lZ'm-h@!(@keU2HX!O!\6[>j4i!t\4f/q2_Oo,[jX8;^>m<jijgHM64*
RH^D<6R.X+^Seem,l4IFWYL2T-)Y)IZRFDbghYKEqgA6phr2UH9)/6X#PYZHa+'22jj0^]At_
5nZK67O74X#JBC5p)*R)#KF:eZd`Vp6J5LidNQ8H*#;8-KFlhtF@Z4JmtIoI`li3;V:rh;)p
CF/RBAph^@hs\<EPYbbHqDCtjg[]A5qPb0OmmQSShBa%X+B@h&p6k>WpgqKg-WHVlfq(RG"_&
6hFnu&cpEt9=[I6_O\EtbJ.#e4%!'aZJ8ZP"cbPqX)Loo#F"Dm;D\S#il6ZC4RYJ#u5XL5SA
rX!OAC\OlBkrpa:h=!b(Ag8GGB)pj0iA8Jc`FU1_ms1>W9h72QM3mNT2ET;^:]A8@(X!:<&03
TW)p-a8bsP)Ok`G\_'#Q,(<c3%OE%lV-M?kM$(XLa2]A#C:#=;]AFtXcabuWbjbCYk^5&\?lLO
`]Ap7=p&4ZcH_1V]AEIB2>XT@(-C`VSl6A@5%]A<nt%7(BCN09nG?ZXQ`bVul;DA`A%HoYOq?'r
Y=lAlAG/:n=!e`BP5F$8_XHV8W&^Va'n*sqJ`XG;SD&td;]A%O##'+Z"QW(AAG%iN;Ag@Ut?>
`e(/=mF6HlB<a&3kR#4;an,`*8nDmUo(m?YrqImPL$gg._:%l,:6.>ZrXlT+ZsBI`S,MZrVg
@U;1S7mUgURnlD':T6(EKb_udS:6[=]A7XS(d\[eUH]AdL\Wlg%WLL!!>'_pYXZf5]A;N!nZ*5&
[8%KMaS*,I^[R?/87qZM33d)(r'Gu.'N6r7+Ar9:F"f,\W%YJb.Zq$M5;(.<$fG7Pkh0%5?S
5S.hu8Age-r'AZfDgCE.*!XegY!Op9UjdmB*,F'RrOUmH6;n&!@N5dMB"0pRUG6%Ljh)NJ,j
30BUbl.`OTCct0iP.7u--dkDdp:ajEVO1qD:Hp4)^QW'uKq>uWG[ek;2e)0bIZ9N3$_=5G&o
V03f8PQhjiXqLHRm%pT0K[RB9J6ll>:CAUp`oBoe;ooNB4#"VFgcN"!U)h..O0@;3J^+W!=m
`-Z3eO*:$R=LjHTBOad6Y,&:o\&Nb0i96.i=<Xl]A+n'^[H5C:;;rVj9L+/$$(_ckA[[C3@Wb
t7-'eP%HgWcHtZRGuo;5IIj2bY;`.jS@SRn2+>#Sl4*NL`Y[nT(T6,IGcY@A1G%EVo(Hss#P
$Y:smTio1agC6CVk=7&L7fC6n(/_35Lahd>/goc/K7%FZd:4p)h*Rn.7M!GB_u$]AWoS@\fb8
'#2RSE@Hh/icYGmDtueZM\,B"Q0\G1"s5,(:st%?#in9P7CP2(5WX'3iV'B%+aA;;<[JIgE8
F1&8P;ZJdO#A^F)LYH+W7Pn9nF(+mEHi==Ldo`+rW,[fq#&)S(*;Br?-E1BklLgR[_"rDl[4
Ss.IMsc;I"!Li^QB7C]A,u`rYr/+%J%epZ17QnQC=qYgWDW;T".a5fo;bUrB(Z5_+nHmO\Ydd
)U%p.3p>jlAXCdOn#$d2!G:cJuZ3F[u7JP">jgX'eK8h)sK0rI5kUm@n1X"0nUL0mQu:dn9K
>D@7)"gN-K0]A0K=)")(o;D`/heU<s-S-V7R?/DLi2RAk(lg,o#*m+L5'\5pOaZR"j8N3#ul-
Tri+E3bVH\e>'E>FP^`qr(1&TOQCE"BObo\kH'3-A_9]A0i!Zr"Rr>?UEUjIA?8,'.51<P)fN
g_JC]AudmDF?8\0ni,&8Dcs%P$PL.<^<>Tp,53-5)7ihFiEHu;MOP72?,RTO]AJ`ML-Bb;Zh&i
rfY^_QgXnMRR7!,X;/\G\2]A24j0'<N]A+7-4$p?5TW;Nb4l]A6']Ao@SHOBG&,.<a*7)C;V;'pH
n<Os?ETjQ=q3p'jqmlbaX$;8?,@#3_`*`7[cJdW?8_'NCEG6/W/h,4COU@0$LX-o#6Zj+QJ[
=<F6k=G@c(`9aVdiOQYI?".u?t'dK20&]AL3q1io7'S3n5`,.q!toiI23=QG!^JQ+D5*+*FHU
:8:kE@`=%m0I-AZ0#^W(0,tol*ReP:UgsAb#VU=V7`*3H4PdZI+7aTcD907_5efqTD.rObS-
,B%5]Aq-:1-bt<p$8b%o_)Acg(r'04MTFg7BdGaH<gbV^Gi(<(d]AXi:[:5W<.H]ALkoCc-k]AZ3
",Zne!cQc/B0p-31nrXR]Ah!G+nO%n-ab,hMNG!2Q"CoSKlmT`5P(%6WVk)ag66aPZE=be2IH
Qc?%K@mXOr\6m-E"CB]AHA!X_.*-f12X$M4gB?l-Gr>;L5itq\1oMl:]AE3S[`5mc_PJ<bFK>Z
\SK9R"_*6"l?NUU7*b3F/6r`I5K3`&!1Y48M@FYbYa\LIlp&G&<<Il,U*nq^JhiUKY!YUX$F
^e>ZR^J*CqPR".3'0X:*^Xt2VI%Rbs*4g)rH6HO.[KZMP7%U603W*L!>F;cp>A<fHK6?15+d
t2a9`%oFU3P]A^&gn;BEbF:0>BTkDIDD-DVI2c*4hA\=K/IVl?bag+2L/Egg.JO]A]A><PeAcQm
'&39+-Kup1Q1sVomS/B>)!m7YV<MarN9l_JAcFToh,X#loF1tdUUbGI-!n"'b">LA=8_C4T7
Du!$?e`>I;O:>$Pb.54ae;Mc`S^T'<SGro,'-nRo@dLk0>pC5h0Xri,a0>%p$&1*ISlKngJp
CN!MQ$-QFqaYj6eAH_\u``6p4kW3h_0e;jH!A,lp#,\G.66>]A8q&RmGZXf3t6>c^:]AO-50&Y
2QqD@cV"M[(-Y+Z*<1<7Os%Qr86'Y[Zd\[GhX!-(_8(Vb:HhPDFO#k;7gd+NqrkQ/RFN*^M.
]AF&VK4HY"Y0>AQd^CZ97lue+@Lc!g--Smp%1=ETJ^d2=,P'<l-bhqmOm&k>n1UbAfm^"s3Vl
J_;<r[-Dc`(pHqtJ:W'Ir4+8+/4&]AS(kIH_:kC^8CdhR+9MOcY7cTcQ=oXt':f0]AL4<<"Q6K
/5OK?=g\%[[qOo`qX#m5@9ImhKdH-f%T.HLE2d)A9[Z'lBZ]A7f=(!=nLI4;3.UXMFNH'bo<a
ZC?>7p^bBpt?K@dB_cZ<j*!aT>0>TaVSZ,ii/kemi"h%B^%a%NcLkAX9GAl]A#(K*0#C3GC''
)3)7+OOhH4A)u"td^gaEG!YC;FU%W1ZP^())H32d.E(>M$ZKH4Eb@3Z#>o4n,r/)X^]AeGP48
QHgT@n<r"gC2P>>b`rOe$5_Z$G+qk_?'5lX40LSq-[/;YL6_'t#k#XQ;WSf,\l[GiG\W12^4
2^T4/Wh("d]A$>k_AphD\l9l(8Z*RV[o3Amhs-\8kVSn)$%`ME'\(G(SU%l'kKO_#6iaEl/pF
S(C0$4h7W3,Ap@3^-T9NNY9Q9HX,Uj\=s+cq^I.`/]A`Pd_h9]Ab`UtH8m0Bn:KFD&aoO65_B`
\C\-JfmL3n1Ce5@3Uq,bY>gIAqYL.Mlaj^et$XqT,mnUIbu/I`X8;&.&u;mL.]AAPTcDmE(pU
m=s!=i@a$^8:i!9/Bn\%aV*^,L6BhGp;7jiL#)0"%D9:&)B(Q?=cq<t3[IWGP$SH@2EP"0/e
>l!":\#Gh=BpL+`)qhlpt'dO/Y0sb8]A@0%_L#7CIFBR25A$]AHg@urlrn0A8f&MO<dbr>mIY^
&=#(9pNRZnLXuD=Y(07r`$ElW(Hq+bbj9IiO$N[MNCRJb0mPRic.+$2T[6:A:)bQ>%+crO@V
6#'Oqq=3#Y8>k!8NHlm4WC^"m0TECa&Q^?*]Ao&*=+$Ehr,UB%nDMaqNZmYS'LFYX;hg'?UfQ
i2]A9BM84`#1s/<)@.h8c)qN]AuZWnS45Dif*28:^/DUX#X22^WqMTTj5*:017"(1K7kS.o6HG
i'\#7nG&2!5aC^a*7-g2?'f7?hoj/4H/?V!qIBas8Hl4Fr^Tj(D-S(?mISZjGLe]AnAWr<8("
mQ`XFC"&eXikLUoe-4I"^Z>6mJr.Gu[&mHE?3n=%96P.hRdQ3;`Er20Np^lij7j@kBKPV(kI
kdrEe0bS5j<h1LU9WLaOe]AaJOCg+=AojcqiEH8mS-'uUB-\1kfokL-JO9e_e#XPf(<Vg),gk
Vf\$'aIr,@]AltRo=jiBLO(\Y(sN()'XV)2>B>o#Z5Maf.^c$D)jhaLT#L^s"l)sW4YHTjoL0
':mhQ&s>u.A2]A3"SrPCq.``EM6qa%5c07n*uA=M]AFD9a&Dlps`kZO(1S9nEn3ZUtBL)hi0($
Q7s@cblpC>PM;S2/Kmlk`9<E`0!-%'b&\&Q&W5s)"@cqi%js2M&`$d/K.#,d=AQk,.erPA$M
^5Q=Lc1(S:i@lQTg03O>LdF_Z3WN[>K4"Mh@Q9Y(+!8S]A1RF3TRfeG8Rd.jRLCYec)$QSL0_
\dl]APs,?B'@1V;GUg)Ue#Ur0IWSR,@O-jncFC/\%;#E:9m]Agq87G0HO&8RBRoAZUG_Gen%5j
>T!@T'R1$f`StS`9US(YM(Gia`N=?r,Q'meqS"i&C+=k3(KrQI9\L*:!Mk8q\.kOeQ.%/&"X
:!,'J&WKbB$=6<r\IZ`&EGk$P\TL=g2l*4SE//a3JQXNFBSdOBJUHKusBT?bIaITX4J\gt?d
X0Xi:>e%3-8&!_NI35K/W=ntLX:7ZA[lC'LLc<A8PVIi<9K%^sbakg<F%<-)0c#9FbBjCB$6
R[#I3MsX%c9!>$mkM;/#360T>3ahYKKWQm5ATrl+TqLA3Ai!Wc[.u!,:c#N!X>HnD)9Ab\/$
sLd6%?a`K>b24T>>T.c<F-/&rZB8*Qj@cWE_3]A46o2?2'#MUNaNCM(nL.:\on;0Nn.$G.7)>
+cntIAnCCJoEt#VaT8jlS]AJbr:<qc`mNARf@iJZmd&[1+V!O/1`B<(ToDU_l(=k*g;kAno8W
:#RZ]A12jO,Rn4[u5j8h+V^+4:VGBO`6gpn6'U*I//Ok,_$1oQSOPC:TR?]A%V_h#s@u6Dm0da
?A1*kp6;Hndn\9>QRgh:(]A0.B>jjM:+(d,I6ki(F2D1]AY#GbCAI1FI<UTr9-MJW\D4_Al?C1
^6sq(lMhiK-]AGV_#jJ30hhVQsK`%>e"[m.]A;2`ZATShrKucOBMZ66OJPn2/@9M_fN5[0_*q3
820:@>;0Leo(L9/r'opNr40lMhIO)MD2\=eP9T"40*K%A@KH>R.b2QbYASGs#U2IOO@cT5=J
%5*SZ82;cq6`X7fu`,+!HQV!;&dU7I=Vlr16oC,I+eOGqAQsqF_,spomF5)8QT^(D2YDpUOL
t!$DOrSEcm3.'$ORuL1n*W?toT3)dcqhiji3YbACIFl581d4Y@kj&V+-g5n!(@=e;K9I]A5OK
YKH(JGf;bSME$OW?1R6[EbTHf!pEuWHm06fIF(ri]AZ-50\_/psGVYj>e(X$\k)lp%?Ye!(\]A
1Rl;Eh.Wg-bt%H/ei6o]A0L;&1BZu^r^%M*"DE[P'gj([F%^C>S%HN3196aOaauqD8)G6C1D`
'$KL4)0CYsE3o)`_j!SZu]A9'*&'3>&bYh[dbG8"O,X/GE&09Q1!.7`^[(a#b8nr7,^S@B(;A
HCA\1(k"56raBA93(Vb7FWf5]AbH\9%B35&]A-%^Si-6uS\+'p97JaE1P.tod`S5.]A-Uh*1f0R
)Z/2%DN]AX:[m\C8`cS)5r?nLZtP\^^JB6[9JH?uVKj%L4d(o/'<,A%>')@ct27,kPV6r7AJ-
NB3\=d:uA2D@]Af!;K1m@FsY2OD<\8?f7.n<g?H3pV59l%OS"JV]AQ7#B9CaW>)9,$k>@pt8.=
Ia1Q?F2\jO_JR]A`%(Nm^YRR9'Sf7A%&p'e5#i?7XJ[V$QYG$d4>k5\>(L&Ij9u&T/^mbi5?b
c4@9Rf?g=^E:ma4Rl%e,a2aPJG\5'[oM8;rn+>&;Zm)=ptE4h"c$<%e1Hjm^2.95/MNb7Z_e
(eJPlY!"`+lukL)FP:T.>2o9Ql6(6kOT6c9^GPcb?'FT5&_pkqkIC#7_+/s0f?RD40+pLFYK
B\ldKJ`.lOH#q;5\Ua%7M'bepgCJ1iHmVW;MpgTncFVhGF"2<E%%R]A9lTZfFX@<?7--p/H#t
Wt&dWcU%(5E$atPG(s+XL'nJW-#"`;B8es%b'LR6OSMEV4r*e!+:X5_RZC).RkqN=Z/=h(P)
]A3)3C;,a:u-8Mk6J`6SOAtA8,)hs9Y>r#$U[V3.t\)"^1u(b6=[0KS,9aD`W+\EHj(o/UoWQ
#Yhu!f;@I_PoUH:Sot9U.U'.np:+IHE-PjT>S):;=Y>h_)@^C'#io@W=i#XeZQeIOm%L:RjU
kK&Lk"6\FFZNIo@kV0qSEa=%Ii.QY*k+tG=`QC'<3;E74<b1&f'1&b48D\X961]A#GSD44;45
Q++r^1G;qRI.O"52Ao]AiQq%ik0?lBd"&hgn)$Q_T(padf'(J1<=e804&tk;FYa/\VlL-A4$^
8Z-J*DTf-*"D(NP7`#eggQ\h9,^6h+1iBR2o?j7:+?M5/F,oOm2G-&m\]ADjURQ`#nqp"aUNa
^-\&nWm6%!%f#5EgWEm%7)^.\I`*;O$"l%GY/D#X!>b/0<?8[q>WE[HkjO5_aj0049IhDHN@
*i>f=WXMfZKP)(#J4`gT0!L8r2d0OKb3`l@"aED.Pg4XbR[IEeBarK^tfIZsf43>jb3F*YCa
j//U"V0>)XXhRb.UW?B:rd%HkLH2:Q(@idAsp0!OI^cVZ//]A^,^$p`*fR20F[_R.5`jcX*1L
pll-!m7r#fMN';*HVfecF.joiM(\a<9`UXQE?li\N!UbVW>>R\f[V"S&Rlh@Hn^1^Vh,9&SS
fUikq(pJEJ^<Yg7nj\/M$stZoOUrO5,!4OUPlmJUkbghO;,6):DC=A]AKjQ4Y!%)^D^:T$RG+
u8En'E-*NI;HI'j\FTD\@uUgIj?UTU^c-Mn2"0pAS3VJ>p]A9SF^gE:[8p$4S1k4rA)sIZleZ
cqUIKc./Q4jeJ2U]AW92r6h7abgh.I^Vi9/]A3p&[lCIlEBBOgZD0(K#_R4H>YJ#n,ZHh?*T!#
&MLc>Q'2BLi!Q2/j!G*T!NCkGZ2:a?(0HCQ(dLL*LcD0O/ZG-/2;VkD%gMuQs2j70R&CQdHL
UGi.%KJTdsiGfQ.&(8;MOjACt9L,ID"Ggt&7J'#]A:AU@,F`kWJMt2+1Wc?fC@,\\cC$IT'4s
O4R<RMp_%(?qTq!Q>2""Gij+Dg\`%6D!<@\SL#c2F=?S4(WH;TGj[?]AQ\EOYMc&A0TlDBf76
abeoOJ:\,m1>HSh7@g`_JUoN`u2GI$RH3S7Ff#KDj)C3:lAKUlbu4aE]Ab<?X"ERYpb'aFI7+
/[P7AY8c22lV>#<@T$q#=7gC;4Aik37=%mo="(QX.14EEm?QE9'^:]A?!:7Z)ir,MOJb0Z3(&
[>V,"d#h=ZtC39g"O<LRtt9<VR*%A5V-?$NrCE<`[K<bIK%nn:O(rW6/No"3(/*pp4_!0DZ<
>3R_-+#:Q/IKKY*F'N-8TMlmJu$S=/?YaJPuuO%7&DodJ4.NWi;uC#7AA<=WMR%jijB;#3@(
'Zf^q89tU_fiH8ebWsWPq^gYWL(28RgZ(M)*=o5++ud\28eY'Yj.Mtf1$+g$1c]AJa#ilO=;^
KaEo8&;46MX#RmLEmIEQIm%9Yp9OEThk;`EmGo5LU9X1G.[HLglDC`5ht[PtO/!5\>RG02a[
=EQ(k%NUNqF3I>D\G+NHuhlF'GRcK7E<6r+l4EnN$-*aO[S0ekLGIHb3be3agI*U",Ybc=:n
V'ATq*2p/0juQm'S=Et_Nb^WK^T0;oJ)gu+Xk.EJ,676O48lJX6nNUY<9:EAETjfMhGAS>AF
U03]AFVI]AF+8R1T[0+egodVWJ'e:>3%s6,)9S@eN@'2;l?Qe;F`no8L&rNlFr1HH9tG8m]A%[#
-g^06]ApnjpeW]A]A[^<<;Zn]A@VuKtkE]AS(LiTkjH1.N9XQlE]A\2"2J9)P`d+gD=#h;1FR,X?A(
0`5]A1/D@!,lAKC_VW<]A52?#S5+(M`'JX,7WkABr[PE5rEG"fO'M7D>h"-!qR.hn+H.rllOK!
)nCcY.S;9bFHAX=(pk:ELHBXd9QZ\3onie8KR'>b'A-TC=RH6AEi'dh$@1H3h\>83-Er==IY
8(:!DW<#b?cp@RFX/pQmBOC]AT=A6UfKa/.<ffTFZJT"L=BWjT03<-[&!TFMX6[u+"b#G/%Ju
iSa3lDT-;RJNMs0>6`C:j6;KX#:p"OQodA\5UFkT*7\V8U:q]A5A\puA:Q[c4+(Ze^[7EM'6I
[.e*(D7&ae:052c0C?okO7uHKTYLo,.BkiqA:*(fm\rih6#<>E((nUYES9="E?C/H_@CAa4A
#.<L\nm)m$<hHCe,Z]Ago[V"E"oPgG4VijKp5PBod<FI9):_gj+$m@/1^-?ZPI/Ah@cUC@G7-
[`FY?jSL/<T-ag&JGbO?;9V+ciA,BA?Wdu6p=p7ge7R1KdIgk1?-]A_[K[7XCWF(=s)!n@XcN
%)XD!<'*6TJKE`:J<me]AloV$4cO:1BhnJ2BY_3dTJmhM#(e"3`LX_W`3cl<,cr1mA6qVIgR.
<@4>ZBDQ\+2:"oSVt::ii0R'QYP]A@k]AH[$e=^)P3o(B!'O?;(Sef1-L*T@FqGr5%[ZG?e,b`
Do[67(t\8\4@18Gi%[U2QgdW+Ug;jKK7]AfQAFZMfTkV9Z=jO0pl-6l^iVI+<o7P;V?&8LF5*
K'uZIRWJ:g>LooR+32F/Urp)"`P>i-&N_,3J&g1$Dhe&Tj[hIj:3u[l%A=aN]A*@/g[U@T6X\
[DFqC-?pM:fcY0_D@&jc`GYSVbV()hDDpH74WJOk!ZLBQU;/s6#\a_K(^N'#(PkgiVVe[iP@
pWo]A0b!--a'>OjPV\"tHcD$0bk]A$Y9B?8?h%N&]A(8dCj,39_\!NqI.Vni7SdHP7UiDU9-B;0
o3ZB"S7fRL&jEY3@[P&J'^Yq]Al0B<=,m]A7OP/*8Bo57H@p;+=cLtn`CY9@ZX7P`-*0SD((2p
X+=WP'#%1G7of"#8A-QPZjG3m=p^;=hd:WVYI<.M?i/PTe`F:W:SWC'!s<,7_=,7Ke!2n3X1
(Q?dP(?g6DrDm<'M0hT(cqp=^78Q5[>"N9)OeAV0Ti1oYQVfQ[Z195cl9.88L?'g_#pXo06t
P<F<AiSD)W]A--F^:L`5>Y154t)&7LQAN-:X,KS9^,0qE]AT$3T.3oBFK\?geLOR,:8FB*IXfk
[qR/'\2:WL3c:W8t>=.R";@B2=I-=%6-tt4^@-;BP0@q`>scQ`91.!JXD,dmM^jLDQld?Q1o
ABj-4eUB:mc,AB9O;M[T0=@7H]A4>bU5]A5Bq,M'3=Uk8MoMY&.t4?Jkd(YpNBKbM:cK7AXU\7
[TKX*jI)qYQ=bI?Se^#^<55-aH/S`990L'L&>b>N`D0o/*Zt=G"N2M?\Yl4k9SLkas4rMR"m
+UDNfO$<UUeBsLFpIJEHCWq[?dKG%[lKA,gd9_cQ,e&3-b`'IT^3%1G"^SPEL,e(ml<i<nLZ
JS:85d1\G%*5c/j\1%2,TW#CXZA0V$9R[N`LR*#)MUF7FnVr%GHcdWIKq[&SJ,b[902<V4mj
rcF(07Q?p6j(cL;fsXrPIf(UGi#KHaQ<;>RXrjpcT0Gp?8pq!<9.oG%+!W5*(I[,L[M9qSsc
&9VV*%jCXdtC+)Jm/irHIP(pPn(1&WYq<+ZQo@ZN*[c,A;sm.4uC6@1aZlk`<ue?bk*%&<*Y
`V>W2\&GcA5,@oG-&XmDG:.FB0,O%,\R,sUX=h[D#,Ml1:i="u]A>ZmF0FK&I#d<.1=A?V'o/
@PK6_cYZG)o-hQa2P/\qk^O1q")mZT4SGRS*2PS>W8f;]A<u!m%r2X]A0FBZ1%9hNM>oj/$guY
R_0mT5(LNYfe4Fr))1=ktflC*u#!,//`;);P[l^PORZqM,3EQ'5`[-.0/ITa;go=2c[bXN9)
1YjW?_^ator0@^T"0-cq-_?\6A^tH%t_-Ss'F&$(LX.ph,b\BH']A**cY:gS%m%acgelcDN+)
cr^^ZA!-i(AnAI/60p(d>b:K(as=&c>MHFU@_SHK7$2O/EAM(lYd?\8Ts>`NIT4VpZie)(lD
&n(G(@r3rRf:9.b`cA6]A;sj,*KP,RUG4X,'iiU=@CH7.3ra!(<ZPEUTIN&"KG0&71r/)KS'(
Uj,)g9]A"!d$L2IC""+4%AAJNqT=u)L#EC,hcj3Wpq<gbMS%"??:B.^tJVDM77B\3)<GmFH'h
]Ar:$%[2Zf-L^?E-R(hq9McLu6d+5Wk-BD_*qInC>m2PY8Nl*mEX>@?ED1Jo.$]A\M#,D%L!Ok
;esGg*]AJiCgUQ'PE'UB;Wto^mIt/`I<E3$-I#:7fgu:Y,N4^!)KR355_aEkBN6cJ+nrQ\3U%
fT'`Q[`@2p?[W8V-l/rR,cAJe+).-0;aI$5S]AVW<)l#?!1'(:uq6'%NP3X[aml)]A@fX8$ArP
Qsa+;5JX<sIlG?u]A@^-*]A-`\^oXoH,U3-qnXZa,>dpM6#/!)srBO@-l/X>%J.#M8\a@EiPal
(bnmV%NNWgj6nU+Olrn3Uap%87QF1uJhbC$`>,[UrSAGSMQRhnA@A_]AM'e6o".O\Jk\4/jBX
Rn'=cL`L$_13+;m5d0J?+2+JaSh3GRO3sgc)0A&Q%pfW3e<8AEsNEDqdXDi(^FQ)L+Fud,g5
-6I4)(iPH)qFB0-l]A``D0o;*Hh=Qo@=gu%^X4"KRT%7'lH5_k[ED$$75_NcA&%q,@s9EC7X3
h'1'5q/Q+Vq['G_NGA[P.AU[&6<D)<j<=:5:Q1\k[H<e.U.g3\tc1'_]AXreBgeqMMCHp?_P^
G8Xd+LN^SY5sQ`mSs.3hQJ\PZ^>*#Zs)9oSa@mgc/T$25H:@UGn]A`Ta=mZai-UT<j4tS:k-n
TUF0LH=dn5F-;R)$+((d?e4a(C1VrFXAA+E7r.,ljD3-IFluZ*!]A_)I<2^8DYX6!Skgq<<,k
h3A)^c(M7c]A`N]An8i-soI?fgn(ndKS`i"sEfXN<.o=';\K[-9f%cU[*0+*$%=.E>uikuGIh3
+A?_bpZ/o.pb35lCT`#,mNWiFb]A!Wb^]A4IRk?>k#I!c$-QALI<R$r_CD=iiNMdg13,9BhnUQ
&Aa[r5'"gTJsndeV7G5i$)'*%^JM:,q]A37Q=+\m2&*-b\b[$i$b;h4Ll-UO@KV)_G4u-O%o6
9uhD9oEsF1h,X^WB6bps^fn9^n@GkY;U`nYEh8.X-PTnmmon(H"+cDc8S$$ian)['A=e><??
a*YBJ>_k3_EWZW=6CaB#3AG)pEY3gN+MWeGV05^8)q6W^kF8dm]ACPkeW!H"V!E^]AijEMDgJ>
XV+bnd]A]AY+D-4=3BHO"XTBeXF6hmNQ%MQ)&r2R,s^ERbnqAY^)U]ANNoYr+0&r>QF$<.3%S+]A
F4>Qb]Ah=kX"c,s5&\6%3Nn>qVLVgB8QYgPUog<S)EW#RVr+N)o/R9h5r10dkc!*,TUB?39[2
O$0Dfp^NkM"u/B7T-qS;"1)E=im!DSgG-fkLE+qZ]A?N6U/]A`d+FA(&\?1X>oSp16Po.aPY$K
</jt;:5/%jO!"5-hgD;CHViW5h;L'1Z*M'5B1=[,U04\19jX98;Pb!7Z*.J9,c@K^]A&A_(dW
GlI%qN-PIHc@^j-FLrmPq5s[rK_5V/P'NZCj!nd1D179A=lK(ES-Wh8N5sPO<9Fl#G<'"4WI
0!UVT+GdB:!gL6>.h8S:tRUa*QL<R68N*jP\+8Xaj+JbK^6tpqS0?.Gn!=kSXFij.7Tu"4s3
XXGH_;WuhkZ%#u8^97^4W:H7NE5OV,7VD18YFXK_9%4.-^t:\cd[:"OVQff@/n_7=LQ^FgSO
/SY0&b(]AI-Z1:sGbPoID9/Wfpp"+SB@:q:RBXd9B8t$XPJ+F[JZDE=^2h:Q,17`1W5T3?:_p
qi=@q\lb]A2e%48m]A-9nM<f%m?^<p9EIDHhO(EcUD]Aebbu5CA9T>C8U5EO.jmIp+D[re"B$1=
cE!Q-b+g;A,M,f+f(d_Ok;(h8g)TYL!-S]Au"?.5Ql7mAu<8j3Uj+p/&G!7;W9sEPDHVY?;oc
i_r:[!mctH.Egd/KD&o2q%uKE6mVjcUim4rbou-eE:Vak,poROgSQtW]A]Aqt3O+iYoNkeeLh)
<6!9hp_H'H[CUFfmY2m^,O`=qGm>AZkT*<$t_tUV05QC&m-*i^K2!oI++d>X6455i1MMSL]A;
V]AeSWjt1nPk"Y5RhO0eJ8UV.TAm&S_`?!`_turU%?%6_R=II2.')g<PI1i9E9J@fMWp[N5*+
TliJ29WekhZ8&SMZ/l/5l^2uXo$7ijGe/Oca2t@a[SeKRQ&Rtf!f&i]A:QbYk_8eCP]AL`3!;6
Qa<(@^3e*@8G*8Tn,CA!=:#1<K5fj^e>%$hKW:[[;dnlC$gceEXpm0-KjV`5=JBPaoQNnS11
"/(14&o9rK;QZ0m<_#F]A;:W'f\<.&M@*i-]A`.oH/N'f\SRcg(OPAu8Qi';j;%,2LgnWXW^sE
fkA,?O1_ZT&4Qb:KDdXT]A7Cfd<;5ek2_(M0o/oZ/W62hOP2mEMN2TrW(QimFnMd=k!DFlES'
sA9>@cJ"765h6^!L2Pqf#u_*XVb/hAp_(2Ynq';I)EVEm*XdM_FS;sj8^(4hFZ7F*;XEhV',
SfW9b8N)Xo!&hM`:A]Ar&qbbtJ&nRN<c\:gj.J.lpZ@ah1Q2!O7+XA[2e^af#0Pe@k`EmG-Xu
n4A'jI).m7ll<3FB]Ah,e5Hn%^bMWk2",9JWj.ECZI.>hN>%I2SoGTb+nK7Nre0m=Hd0F7"sK
Xkkgu0kf<A,9hcIl_`T30d9b'bD$dt'khA^hFnL:62!\ST/AQSn(W`.`ddVGMgnO##W*(4lI
'[S"2NZn;ogrG8%Q8g9Z91f>fkZ^Xb7#tcnr]A9Zi6X'']Af91mRf2a%K'^Be76@\Bm"mBYAE/
.E%^41*E]A@>c48#Yf4tTHM-Q>!$4nXWJ1d)_JTZudk]A.4_^U6A7u`eO4N2k2C&[3VZPRA"!"
8CIE7?j2q$p)P(N>s?$pGNBb(#W1U[k[V!XF]A\K&(@QZO<MN<OTh#B\\'[!GB>LfV>3aHFYJ
^\V*L<5kH$r_;6A/#(7ls`[P>eJP?>TOi["]AOW)U!m)g&jb%BP4;g0r%Nor,T<sCp[#hPk1$
pe[i08(tCDWPaoTO0oBtfV.9j]ATO!@3)q&5.A"dMCHGp@4Z2M)43[]A\GbI89dBpa1GSAGh_M
X5Q'K:Pb+#7Q,$\5j@_N['#oT1bTUjuqA\rM1RYC6*+%ZIVW2#mO%5F,#8hhXj,rH'"UImp=
dToOS8!R^bio4CA$$'POFY\rfFEL?te`r>uC1q<]A,5^$R2-U(7iKB7@YAFA=NWe_MsuI^ks_
EkMH:YF/OEF]A8nN%`*K_GeJr6)&9]ANXRL?^Ns"&q<]AmO3l,A"Z<+h-2\dM:C[B&+"4$00`pE
6OZO78&'o)(9%J@4G1>./0C&Q_crT8`o8K&iQnekVAD8Lq)N<_R1Y_)f*6<k3OHEam?rs&qj
%GgTET;uQ.G4lLq%&j]AJ_aWIE.7mF8M)'6LFD&+>3QcBq9i3Ad>L)Q\<='tJ`jaV,b8Hn;PT
,70s@sjDE2+I7BO-(EtqqY_t&L;O)kAC)-Jmp:Jn=0$XU)f!G5S37)FSI`\N%$3@a$O7f;'b
eT+fX'_p$5,^=hn4R]AGMu/ihm3-mP(dWa/FLni4?s>>!rC.6B(^W<,M$Pb[+dUeHCbZ*-c#!
+8/`.D_RF_T%.Un+$nY,-S?.qrpDT!+3j9B.Q#3qn)tdY?Hk>G=I%S.]AiXAalL4JL["-WZDE
MQ)BUn@U4A*nBSTqG$m6iW7gl!4j]AFA<Qq5.UlK/"<$r`]A9SU^!VO7Z.Y`iC5c38\,TLQkgK
gEa-(;.o2%HSW&CFCrc1gRUW)0NcR\Rq!U<1(/m=a(Z$NNfbe\.r_!lE\ZIkqmrHFd-Q5@E7
D49p^KmE%`1!WF8F59FZWP[UiFp.YjA^9jo758^#A]Ar-4%?87?e6i1ApTd/o&-W5<2%*t($*
C`FFNhHl.`2frh8eV@P[-hc"!d\^T=&[TCYBOlW,P/VU#\-kWc4K^Pr*:%dN*R>4oO'<M)^6
=ntl#k-GBsS)aMB+_)A[[(agRI7^Te?%1PUog1SAa0Au#h%<]A:V9K8WOh[NT]Al4*6c%!aWc<
\]A]AIO9PV">!gPC.Xr\_Um;NYf"RkHl]A:o?q9iKqeBnC?ur/*R]Abgd;u8V>94e=3pBr/E=MN:
`']A$NnRi9FkJm*:9.bcB/WD,h8jc8bt!aHUmna[NMZ^'=Q*Dp_p/DLpck7K<#BA>Nt/Nb6V'
`I'3qDJPcX.k5(=^1^7_G8fEYI"30Krtkq1r->,#1Z/j3J+kb2KH=trkF%i%`tXPD1/5M`I4
PR=S*9jp=!#sBqLWn:RXD*_qFTTG(=Tr!Me`4@!o=gnZ4b5=6aZ("0YfQ>pa+EgAh:BcW;J4
aA8[ka>a@`kK_i#d0+CP"?Iad>X=#D.2TGMGK^,FY4>Y^JK5C5q$ng*E^uQ(]A:V=%01qq!.'
J)]A_Zd\5$>,oF:Zbj/e?Q/;_Xh"[r`3`R!G(`q.p_F`_g561gX<DMqHiXO([)Ui,,*3?'g,"
ClX+/m=SB%8h,ReFmt=$,m"D:9.rJYgX2RbZk\`8"k`PjDRMMdCKE`Yq-]AGSiQ#h:U%>0rmK
.Tr=/F5;;KXH2&&$D>.V^Rkc9"r!g([[a9Fn=n2oZ%a$.&a<96\d,1qa1!4R6D8rB!-b-=%_
k3:D,C:O^8u=N%kaG;(OG9[A;clU0A^UW_)Pq$EJH1ES&Qu&ZPt-h+g*/f]A/f80"2p6Ie)-c
^UZ*n4K9t`qk<PF8JJ0'Ybs?Q`\*H_G;=.=M:u#?g*cX?*?c$p/VD[ER*pb:kEH-BFUo?P+R
,T[0Frf-*%XWMs%;D?#MkP5:"5jNl/E?:>dO4=U1!%9oJ.<[jfInD#a@@1WYQ^LY8-nU[7)\
AlAW8La7c4"XljQ!qYg%,1]A%*0;54oDi._0)X$%qnB@htT1n=B+D]AV<P[)13gg[:0^NHa75Z
Ur3&M@r$(H[U&+C?^G4FnQF(mL[XHa3]AZ.4/QiONH(0)m9)9$=ct@6Zd/4]ATNCI>)'07+as>
;,AaQr9dH\;2+nHjW+iNt!7@;F9JV3[Bn^2ZLc`d.fnHi90YP8+V\1S$_CB@s9b%%r1@:ZHT
Ttg;jDGe:PW6@8ol\Q,:*@=`$]AG8`RURP1`lFrK-]A"jkRH.`]ASY&G,K0g;jG_06prd,!uL=K
K\6EBmg9GrQnJEW3t(nBJ%^s./IDE))A[:o`8het:)!78)I'n9Cd3Wp;dn]A`+2eYVJn8^9&f
15[p;:G)5:OGk&AH3Mm\2ba65-(&o=%1_[D\*Uub*fN;eK!K'N0Ielr7(HLu-kp'/p4&D@Y5
39<VIJ(W5+cK^=d!E:ojbarK$j0#hp<ra0>#TH^N(!Y3^]A/1+)pqWC`Vc&EUJqO>&6UlTgdB
%.r,-fc\Y#['o>>J)SYFh:8XB!jmTfG/>sS)L7<f[KY^qJgU$f"pn>B3VlHb]Aq\K@Z'i/Mfg
5&7MIYA+GLBN4'[Re*>J\qAEQl;iQ1-a;<JB_SY#]A$I,i\iQYd6BZI7a7Re"o^)s*jdH%W.c
<J"CiA6E9<"tNT4m#(/TWQ*\p6/S(&5>nC\0I6:Q\.,*_#)[R7o(:l>n^l;!UtT*iUQ4<eL6
$R*fpCGl<]A>@U/eQb@(5MkHbq"rPmj9ES<G^-:=7@%RlJcaX>&Z+'5(epFrK<ZLWJ@bbdJUb
e1YVZKSq%qXn?W$NDT#B[%?B=fSBJT6b4]A8j[Y+a#'-s%M(04$:N02Nq_JTiul7NI91ta^.a
A)ad_aiJ(:L,s/Jr4(ZBR.g=O*HXhBX<^!:]A[<fr(Dk05+1<!fXfVT'iCVbe>;(VVaMW"eJ>
K0!UF6i&s#17KONU@SEpZ/'MQRtRJO+0$25E6_apq!l"NR=MK$W_/jO^g<VpJI1]AM"30[Vh`
>VW4F5tacG&>7m/$N31XiYFYO;J>R!Krej*4.$e0eFh4E4t,%:86gL4O#3`%d2sl<KkR8i1]A
IlL4Od@6/SHgdV4Ke&!Pr_4"5hm=*W#%-rbO#"#Lin#18oDX#Nj3OO9kkn!Qb_X!n/Y#f!#$
E?9#q\GblbalsZRO>9!pot9)"._DsTE^.sZS7%?9:-2!i>)0kgQ.UcTdg%trBNq^q:j[FpoQ
#.#;RdTPj'@<?IT,+C*]Ap".Q*=[]A:f^%pN?[u>SSQoEZ7@heK^%=4=<a*kf4R=jP0Y(Ja?lG
mCn?W4iaJL$Zp.3E:)>B\2QUljMTZ1Bc*t8Wr:7;P*R\gXk#q3TOk'LnB)s]A?MHY^,5c$"O;
l.bFQdCOG-2dYAkU:LY$>"LehX]A2'Ps.H$6%-EO\?0KCsU6#*q`=F0YpSl1i;:!<*Rb3/J=P
o%q5EVoW\hP`H_D?8:&AKjBhE-r!o=TbrLg3$$);e`:`?)bG9p\:@CVqcOuGUAi[po:.RVsn
\Ip<k4>SMj,Tnt:Wep$E*,ReLhB9Aeh:WnhWB8qV,,Wh<?V.f>FQh+,k5FAm'6;rs3g4!;cG
5[!1Y=ua?5MI^L)Im++*\P%SU:>?!1/.hbZ__3J0=(R,SD)77Lu,1CTfn]A@!`+/B1%7(qiHB
>[1Nuf`5li[]A"4__0E'UTp5.tkjLSn\*+atVq?pRXhX;l.AFi!)E7WY$l%Z*04jB3#FdkbP7
F?.3ts6Zeu.f#hLj&>JVV`pdCaA9kMN$fM9p.):@k@"RX=cI/gFHJ__]A]A<CJXL)>>$7h[T+P
\j=/A9`:".RhW!F5I[]At7nVq"`et4UTZsZjChX[&`,Y5aH4%Kl\^@;o9Lh]A%6^6%U(o%jE@J
'2V`q[b8SfBDpdoCsIJju%UiWsGp)E>s?b#H3[2'(ZPfd3!gT_nBh(in+tH.^a?XoMP^cm>-
-Ycf<1Xd+*'qO\,.bp3q)Sch]A`"hC)2i0H]A^3dZ#S)l5(]A<RBhl;rq"YG4OH,<ZX5:"(sJM2
9C/!jR4kMO>g8LV0"O5iV52SZ,;9-l*U=bcd#QbMDU!P;-(H^0Vd\SO6JIjpjF$D^DMGNs*K
K(lq.5`ZS+<&8_-QRdC'i_5EJif5Hfhg'T#3H/KE2s6n"221,4%dBQ6l0&E0,"U6#[OnS$s,
295k04LM/ujTsH7j3j_ubR@YVQ:_l8'N]AulZPm:ZE;e9"DocPBtLX!;=eZ?,q`eRd#O=Tr[`
d^2$!_+)Vp"rW&e[5U8S>S+ME.m6[h<R7[n=PH*SAJcN;^6Q+*@CTLN67TN(b"MbpOl0cMq>
ifqVu:E5?hI!.X49DW^3cPa<LN&=kikFO5N'9f.6)U$ZW-s_W/?u!>:!-b<PgH2_9;[g8mCB
fhNaR+F_7T(\b+uh2t=OI1G]A?U5/MW*[o6.jZ1K%#!;Ic6$>]AgI+CM6f:fPZ[Xb'bg^8#m!#
q>j?g\:/_LY(oFAFgG-"?2<ib^^`>h+dJAQ=rX\(mVuric$u"0/?-C#9Jj/ks06Pf-$'("!1
NJV9aK.RcX#<5P@D5s[o'Q6M+gdE$pV,"hnDMCXLUF96h4=@3fl+Xh#c@L*!tOS7K5f!A#d^
UcqkrVq@gEmC.qDXKUTbWPL@bWTKE/7gFi8dooGQN0a\hBQb*ggu_"6W8JY</`>L>0Q7+R83
XK(Hn*[]A-.bePj;hDUQ@u'7ota^hOFHaQ+ST]AhPPNC>+J\5hr_g8qq9.1'OJ]Afa]AO(b+4Sk$
Vla>JI!NL7bL2!/8bJCRpG",(2cXGT"^erdg9ASIN&E7'FGF&Un(h2l*VK+dHdt@G_jf9`XY
G&a]A86I!/_)-`FmJ-%:"Od%9d+*tSMk!%7ep:J<]AiJPi`I1crU>hGNr5VR9%VfE"UXtXrG<G
ITZ)bs[5j4`,:.-B/aE[^QXCj&SVgO-]A.R]Aqefg>+)nsoY6Hk;%'DARebr]AFZ\uj>67;*!il
Hd?r\R!bC]A1m.dZH765NT0kPAfPD1-\UC2%3H_0PRV\"G.rWXg^:TFoR$?!V5@L#mc!$,Ur'
V;:3XQYDA0J7;V$mWgUq<df.0[B=qG:H:.0rA+6c"F1Zea?g!rX2#7S0qo"=UaB=fo;eMj<M
,^Gq0`<ZeseXu^P=Xo,CF#F9aI)rPU9$IXcF3E5+aS`#\eJ?_->Y;s[=rfgMmV#eN<n5Pi>#
3#]APg*_%PR";`p-?=D6O_^O<aS;Bf6m!3f@RU!9s&CNo"#n2OtB^8!1Hf\iTSK1#h/]A;S*WT
8)EgiTP0_oShX717#J[UK2F?GsC'ZYLG'.qd@Q2mg5\DMEN1dlqQSN&iqE.5nn"u]AdD(crU\
gJ4sBkQ>V>l)A_]Aq6(tY7&q?I'.+Dc^oL(F<ludb"!!uJHu>TGhMWUlhiBm1eBIR2:ua95f$
7L;8Z]ArIa/-ESbhPJ%!pDGJA*<nf%2NnW?UH,JHa]Ab`.jtEmlii%=@FY._p9;>P4@M+PC=6s
"d.65(#N6CWp;9oHqeMsU'`@_n,-mBaQn<MKf'=I.JeX>Qr`+7=gKma]A<n+(Au#+VqQ-fJeR
839@M\PdRqii!`%_Y5Rp/-ijb39T.+K,=pq(#nYihSkRrgROg"/4->E1lYlLUHdQ'sLuS?:\
ioeSRO>mfgLF1>V?H-7r5Xng9:?EJN%^IST'A"0o8Z<["f]Ae8lg)dg;-W4<0ZL7u-&L4O2]AC
gSafZF_5#6B-'3:2?A@a3U/jH,d8*)@qZMGa?8tCE%"pj,W%B"f[c.d-]A7._-`!d4jW#PQP;
/8dWR&XV>\PY=i);X;ZGF<-gUI^SQ(M5qNgYVh:1:BOK5j67:O%NMg:/]A"uf8iRk@-\Z.a;.
j1o6g%B<UJ1YOCOBqX9'arOY?MK+(4>6,75Kfn.oel"]A!6>EOI]APcA&[(l6#X4`YH&1I/uk3
r.c5TS:re7<'5JSI[-O4TS"?TSI<i0l.^Z$WW/42(#a)brR#A1t\sht0Qn[7)LeXm!gL%p_,
"8?eU/o:Akt/P:@bJ:&u>DU<a84H4H2,^P`Zr7:,"NY_q0kI1EUaR2tcS='!mkP6A`prt<M5
9X*(C%&s!?J$kn%]A_IMSFYmX<LC"X9M1EESk/L!p$:"\G"-Y!Jo@7(k`K=r;GPda$!L)HX4J
iOX08rPT<YX#pm@o4^kXl&MNoI0f%N!<8+l1KGE/1fRi&MOK&0<s0MsXjo@J(?)n;5[+d/L2
^4"]A$>;*;7T',/]A7`SW6,jpB.2JI62Y;WTW*6dHUDQd2JH["$AL0$J@0)Q#hjS\[PoLkacV\
1INr%*iH:YYd%)btKiTDpmuU$pY9>"+[nBa"qbhZ11d]An)""`g'SW6)-)OVE??MCVe>dOB1I
Pcccb*F[rrqmIu\0UgP(b.c,F@a]AXGq';Ike*C:V[Gh"WN]A\A1nP4Hi;=:\n%1"8\5Z4>a?:
n&B1]AHk*7mKu+pKR?c0ZTQ2'65#iiPoHgu!"YR'ingS@1JmI^2LOr6H`FUjgE`=(Z`iG3ajY
e9-Un?DEJ*_=&#rajU<Ke*\/T=U:9)J%W^cZP;ZUZk#2Ydb>3!BbGEgT_d\`9JdHfJI_h[?W
:3`.HQkD5+P&Sltf>8"V)H]A3p=ADNm=_m*M@+-+bI!9Y<nbY`@_7H,\l6aEa2YLHITs.HCZp
pcK5FK5q+Pu+e!?b1=U*!CQEf,)8[pob8410Q#fjV3FS[9X"ekE`*41oqgl3c;;cGs?N"m^&
81'3s#13d[ob5=+]A^O1u\dn+VUOaNVh$g=XC&_+\fq%jM1jmE9crjd:N^e5p+I5P,#'<0>9J
)AZFjb&t,58^X$qeA`3cZ7dITcYZW#e1bJi#:]A#2ijL%B%H@tm3rO:IOb75k<Jb$_,gCDcgc
D!/Z/jk9/eq!NcjC/,L^MRSr$<-qWQ)`':LN/b8q\nGgMgVPJHlUV/0LR%E@$r\J:N?!>[Y2
5WKKQ%C]ATb*no<8d/G!XXXcBD"U3XT65-URh6ZpX'<dGP+!3aYE5#RKG@G1qSD<XThE)TY>Q
<(h1OcX\nm^bJ5\?O1%!V*lREJjsPt"5ij&U?kX1t7*%+hG-]A-;3ucDd[s_LE+8>82`edbDe
+2)X*g3+:<8YK(4EnsYq1,H3@RI8Ab,>B:@G_*"SJlfag=PfoJ+47DiWc;'06T>`S1UJMdH&
fEd>3;(p;-ijlpfdSjZRQmJhr.LYMFR`@ZGk0jN8O^_\nF?<7O`Rj_*.mdm38lf(7caSB@I5
h]AZ!K`a,5:JlL:cHnE>YrGH:@%Ri_A2I:JnY<:GZ`,=_ljYa)A;-Fu$Ye9X_2A^(.@;Y)+<(
CUJ+WY0)j`YQYL%?a"b2/jp1o50ZnRlSpCP>ouV#!C-:2Od(Cll]AJVYe>W'U%5KFJIA]A0Q#:
+2fJSVD#!#@Ima2$_2^*FOqkAi_L=_S>G0YOXT(_+PeplDVoY4U4&1<O9\SotQ'9*,TfUlV\
aUVgPKRt`)IUQQq3b2RsN'B<lIXBo2VV[?P='&mSCY5hG:Bo0G_'67Ch$gp#k@90/%2$U_"Y
WK8roYD-?n[3H7L^G?*#:`[oFMMsCE1I@=(.Z6W';?KfBMk\PEM8CfC"#?[mGJo3!-(-N@g,
E#.>g-_JN9r&+*@O]A31V]Aie!t=t"&F"$4#'X8(B>LS8iX:/e:?dUis*uHU*W%Y<V]AN`iLj+^
Es(`b=0oC72.\A*'`_;?--Wkp9ju*f$ULh18><,E8I,bCPKf1&"NWf"Q`1*)X:YkM]ATM?N1P
PM6TQ$X#*:We\n\m9BDR;5#Nm!6nI$MB76Nr>UR0%lcCM*VYT4;@&LfM6-LIf!L2`l$71DJY
&RoQUq!g;uP_:@R"d/E7B)%O`s&p)Y$1.Y)"(u/!g,jS5P@,4R#hhSWqcM"BGeSif%hiX"S>
Cm&`(eZc\Wo4G>!\^VtY8W]AF5JqB=Wc7mnBD;d<EI)(F$*HP%(tX9g:h]AY."O&t;k\+b`DO-
02^eMf3?c*"g9UM<I*LdD$Q_Eo7Xb[3\!AdIdB.P?ae/*n'#f2=&?n>S7f$b,(18?r3=iJ^+
<_X*$=7l>o;oNgV/.s:9AG;TC2#Z]A9qBE#=XX=rc#s,SRq$XkU,!-#D3/qffK`Zp?BGa=30,
RgeG<kE!"b9!K@Ue=W4M:qqdM2.%kBHXX[p?rQmm<QjEli+pn]AulE+M[_-(Og6#\aY4s&sN#
Bao+"d%KoZrI(WD/o`$)^Z0H0A94iRKa2"3naM/31Z*l)8E.LY]ARp)ag-4Z1Aq&Dj&pEeCFf
t]AApdS5(tYc<t#N5%7aI\#CF=lC3:pb"j?$HIZ)c`jQ0que\`^9:11*,PDtI+PhDXa`H,jJs
k@/i?gZrr+q4-$_T/jUck%[a4Jl)I8/$WYO+?A?LINZ#R?!LKni`g(12CgeXoEJN[%ij9;,?
jGWKY4Tm#DL@aq9Yd"BI?)Z33CV!^SAC>5)B]AMQ-BZfF1[/*lf?>u[OO^4K\),R2]Arb9@_EX
0qhkr&^F\434"QF*oV"5A&[*G-+s&uaTX]AV$PZ#\-jX:;EMp]A<!>b_>n'KIN9Q!=T?E_hpoY
fn7LK?5.=JHCnRj.,Xs1:DdSHE+/J)eGC2MRJnHTSW%9PTbb$>Y`C^-9plE$45\,#omt7/f+
24hg72hb.-]Aj7nrSuj!!Jq7oD7/Vc>T^ck?;1Ji*?5%&^qFq74gcOLOoI>0=)CR(IsdUVV'7
%FX"uNYp7,[eX,7q>$\F.7FYTZp]A4I$dSr;o>5KT_Ln?a_':7&R23))MtfZcbGj=CjbiuoNr
T_h2=pS+Tb*XS>\LTE`1V]Aben)S%=?@GDtT:GnqeP)QKO^u7hH'7ER[MpX"O*f7]A%`jkkU]A(
Wb_<6E8UD?a_l]APrVY5gZ"O<tsnsN@HmH$*$CA,e.&tMt_WbLki_UXB>Y@E(DF-0au=al%'5
2Ut.;h1eJe%URp>X+PMW@gKS'18;\doPb7r?<4Ho[>2Mgi*BDQol=#kmMM't.Mbg%DY8Bbg*
_E9G&6LP<OigS!s$#;G_a3%i3gDa@6M('c"SLUfe,4_19:h=OmT/,TC(ClTf'jK[p;M,f3<F
l%np!Ar(hMe`@Ulu#/oti.4>%9><ra"NP>(LE%Z03-\qo3>TS=t(aYQ'hGreOWG$eXYQUtO0
inE<`&04beC,J9@&Rb_ja95TRfnH=C@SgJW$CPV&#P]A)8?QK2.d[F"/#hU6fC*&IkA#88/$'
UP3&SSicG1S+-&KHS5f5!eQC;YO$JsFp2SjYNO-GV9R&HI5>ErQi#2g[mWnH:%js+nKOrVH^
/g1lA-OS-)+@&G[f)+DU:5[/>WhktoNfPUB(cNZYrfq*,KW'kdu>mm$6_QXQ`&Yg;`;S7=3q
7KOFFtQ*<HLIlogr_,IaTI_`n2CKm0Ed2JH/^(`(d[HoA0[b1d3q<`@;POhi`Ml:k#;S45o?
>1j#YkfV1SCiOe[B[a9m@s%!PQhaS[2;XN0eWck/qqN/,))jpBbVF/`I#)V34k[Y/$I(UTMK
o&.B'LKZFDZmd-f[9Ycn6o8>,BKKT'PcZ!KNZrm@:=q!+k(Gp_K:&Gg%qj0'a"Ds5!]AEP:HL
VZAgB"5]A();ekTb'='nt<WDK1Ur2Li7+Vj_?a`LGQe:PuAYDs#rT1HseB2]AhPKO.3cm3>h.Z
)NECm#iP=8qZUh>4+`%"(PihlVdR+E$DbMJK)t7*:Yp%ar8#J&L/j)?e8'iDlMIJRhYbE^PE
BDO<K<&N>M=A<B91A#+;%+&`lt)gK8c^7.Md(&&nrNTC&H_h&c,0L7D@g#I3[Kg`b?#1*Vdt
]A$B_n[D#8L!H&ItF2Lk/A>^-JIGrj=d.Ua5T26^cZCH/L46!A\DG;r6dp73WRh-=\r/d1o9k
IB5h8q$<HGCBYA-\SD2?S'*(5QMUT]A!1'5R6&eZe3^CW&&3(9epu^so(dICG!k:p)++kCr+s
d*s"K=oGl.X!.-_3^1&_)au3aK#2G]ACk,E4=u3"BDD)Hh\P,gO%<D/fGt^PXlhJ._ZWZCYDZ
OSgbEt&]AZ$"D3LRW[3#Ii*ho=Mifa3.(cmEl!(9^4?YTTS3/&QXh0_IKXT0mEX4Se"@m,KaI
P1c>?hEeSK.kt8*Wg[X9g0K7i\A@s"Z9P?)VU_+_g`$bAqA0e5Rl>rJQha>M""25k8mJS#as
iXR!'hNLL>aKo:L(8fk`B%TW-`6lU7/@*B5&1D_^`q`IL!O%+Jb.iJ>laU3&Er7O"'b+S0t[
_XO:NH:p;Y;9k^UqQ'nn)*]A=*-m?8teGCqF1MO?iXoR_AHQaHs/.!N7FI"j#J&=oK#@7gc[E
0XL^MSB!3g(^tqn?8UB9*H8r*rIUFnXD>QL9n8"T~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="685" height="166"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report2"/>
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
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="373" width="685" height="166"/>
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
<border style="0" color="-14669005" borderRadius="0" type="0" borderStyle="0"/>
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
<![CDATA[1728000,12306300,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,4914900,792000,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="7" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='  '+'流量投入与发放情况分析']]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="8">
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
<Plot class="com.fr.plugin.chart.area.VanChartAreaPlot">
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="1" isCustom="false"/>
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
<Attr enable="true" duration="4" followMouse="true" showMutiSeries="true" isCustom="false"/>
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
<HtmlLabel customText="function(){ return this.category+this.seriesName+this.value+&quot;M&quot;;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-16777216"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="2" lineStyle="2" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="2.0" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrAreaSeriesFillColorBackground">
<AttrAreaSeriesFillColorBackground>
<Attr alpha="0.15"/>
</AttrAreaSeriesFillColorBackground>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
<ConditionAttrList>
<List index="0">
<ConditionAttr name="条件属性1">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="1" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Al Bayan" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="false"/>
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
<HtmlLabel customText="function(){ return this.value+&quot;M&quot;;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrEffect">
<AttrEffect>
<attr enabled="true" period="3.0"/>
</AttrEffect>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="RoundFilledMarker" radius="7.0" width="30.0" height="30.0" color="-907154"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[值]]></CNAME>
<Compare op="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=max(File1.select("流量"))]]></Attributes>
</O>
</Compare>
</Condition>
</ConditionAttr>
</List>
<List index="1">
<ConditionAttr name="条件属性2">
<AttrList>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="1" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Al Bayan" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="false"/>
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
<HtmlLabel customText="function(){ return this.value+&quot;M&quot;;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrEffect">
<AttrEffect>
<attr enabled="true" period="2.0"/>
</AttrEffect>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="RoundMarker" radius="4.5" width="30.0" height="30.0" color="-16744448"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[值]]></CNAME>
<Compare op="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=min(File1.select("流量"))]]></Attributes>
</O>
</Compare>
</Condition>
</ConditionAttr>
</List>
</ConditionAttrList>
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
<FRFont name="Al Bayan" style="0" size="72" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="100.0" y="2.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
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
<OColor colvalue="-8202753"/>
<OColor colvalue="-907154"/>
<OColor colvalue="-15872"/>
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
<newLineColor mainGridColor="-14735033" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-9142639"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="false" isRotation="false"/>
<HtmlLabel customText="function(){ return this+&quot;M&quot;; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<OneValueCDDefinition seriesName="公司" valueName="流量" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<CategoryName value="日期"/>
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
<FRFont name="微软雅黑" style="1" size="128" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m(?t5;erj#)FVD2;,fs!8.uR9V=u*$5Qh4T,!qM<+@q8a'3#YHV&-*CV21@,Q&Z2DM'R2=L)
t(r#m<d\_=d60hdtA&kISt7EP<O`+"iq+1?KGJB_O=FI#,:HUs!rVb[CJO^o"<5D$g:0%g;t
,Kcaj!?eGaJq#pb/*7`ZTHJ:YoH&N$phk\V,ZiiJmkfDWl.@qSZr`F!FoV$#@bj9?2*@o<#k
o*b+l!IUQ9h[OM?Mi<i0"cT-gUR$a`g1oZ5+mK*OMK@#]AljA!<q4L/ZX-?RQm<TS2mX'<fQn
LX1<@@B=*XpG&Z<pWelrX96kbmX]A4BKWminoDS$:('hDm=>KNF/EB]AXM4<jec">oW\iWNsO`
;Y7H%KAc5[lIis88'pk/KiCC_A\a?$.ETcocn+(WEq[;"#MG+gkMTcO$U_qeNe(EDk#;6S=j
FjtIlm-$pj9hW[.5N[:V]AXjQ]A)hSN,Sk.4flYWBEl['/?6Id1B1Z7D]A/O:M^L@VpqQ7'-Ypm
!F=?tG&an+ENW7R&L,mi[?eYOG<R8r1gLNCVYF4GFK"$d<VZIqAf(J3)ZR@4#Oqf_H!3g?il
/)AUVV_ae7Eq97"hr?=W/QG@LU:K"jt;,(ki<4?+J&\`d-X$\:K^kB5nDIFop!cl9+YBe?,Y
989oEDM65#o7cd\#8(E_2cVi<s.&OL5S+I%:f+g)E`=VAH0F0W6[*cK9#m5)6^:%V9l"\"#-
`MH4I<9JSn^i!/52pl6R1>degf+MBKV;Dp;cReDBbuiglG@RgbW]A98*>'gV;\(V<=#NCIMPp
OCRY-4I1m2Z&C`iacJ,:tp[.eppET(5LCiiUT4(RG?,je9;g"+ei[(=Y1s]A5Chn^NrOt6;NB
J[4HdQW6F6c<EI`64U@`afTeKedJ]A^NlK5?TlMmno5Xrp("'OJKA:k/;8Qn`_@HPD&A@\(bb
CD<s^u?479^ScJ>jGZPH^(qg@VX-_KhoU_c$u.iD5$6@GBq8Qcn)D9TWgY]A%e@+H&uB3Jo]An
+jmK=08Tncl5Wps6SM^<;?)r^=L;NE3]A8c;LY/nul&r#QY6N\fcRgG\TN<RUhDb9'tWjjeKN
_p"13ZH[N6Bt1cQ'/VZ[pj'B^dUH'&hKHs$XE2W'"Q7Dl[dDW:,Hj^sCjoP?%6Z+^0i7K(36
+6[]A0_:cSp=Y/?$Ur`M]AQH&JLbJ.^4p<QCBITjZ4c+J*1j;V!siT?+'e$W'TF'D]A/[H9OYsb
66Jtc;?rKWAh-lc9J_eHLDdl9?[;rn-d0;a/pocr5((864-Cs!7ftjiqo$n/Njc]A]A<cMPfKp
19G$&1cK:/Gjt'T>a,+C$F6RbHNSp'Wm3%>4DN`UArAt^#2"kUUqf7j8^Si]A,0K$EugD<l^e
k"6d#H'.7ISu<@;$Uel2#64-GY>W#tk?:*8#Z7Qs%W'_FK:T4Rh%bbm5(rVaap9Pn1K4!n*[
0;']AW&[a[^S)CjD0_$hlc%?'X^t__X@eY=5QE7C(MdZnt!=8W&V"Q:XPC7R;k^U<KaJNs4NJ
[X3)!=leA\c0\^WVlC*8C?Ap1u^P?ML.-^"a8=7Sl4IU&7E0*nC$YOk'bfSd0bB\)?`$eEEm
Xrb$B3r2o+5kNPebL>oM:U8D6j1E`Ukce>2nqm/OOQ?2s4V<ta+4k'EcmJelhJdm/Fm]AZYr&
B`+&+ZB:8BZ=6N^D%a&^o238oD^8Z+H;fSqN&>f9@jptj#n?&+b2;Bdsm2s"90%6s"Wp@]AkP
m?T-=2<_8!&.ZT/5i5OuYHo'b`"J&*Hq&KKSRcCOhaK)3oBJl7IBh>>LA9Q[fi=s*ZP8aF`1
ps^`ZQV$#/$te&GZOE;I<)%:ticXeQIlE_("0g,.:Zo`ml7D,J15^M[*PfASVnW_tAc6geoP
:/@rj2+oqa/S"</-Qt?#+O[N;B^63/eWIjL^d=fT^GX9M?ui:.VjH'c9&!Fh`D(HMkgE(D/>
o_#8\A0)Y_jX%XF?.M,iSn4'<B)3ba>5,o3;8g7T[+Ls(U96B=>'4tsNq4!KEqZ(/0e:@,)a
5fF7O<h>U3K!&gh+;Um[SS=5HE%&ioFQZ?f=$).*C#$#rq^2WJFWQ$$;r,26Lg[iL#%QUXZJ
Tm%!T'=o1^$k;*dq'gHKXVN4\pf31_)kb5Lfq:S#.u7=9S,&.8I--QZPa3e);T>SJ"KG2Lp0
3INKG-3C[fr:>**a.U$LJ)qQFO05kp#bD5ob^!7fPq`5</HD]A\If5^:BD]Ada)U50:7BO3Y;Y
AZ]AZrQq#rur$jSrf:_rh%"8AO<NmW`<)#g<e!t$m%Q8jIgr3?N&#=rtFm556HE9^oIdnib/9
t<p8/L*;ohF+nd43W:Y@W@U(A.nWO8'aCHiIU(n'l8ik+e0$nG_,N@O!ak>?mJQt5uHro2*I
_P\OWc[aD(RH\QX#F2eB'*iA57aUa,"U@^jMr<9TIZ)T+&`6/kcrq<nF+mYqA5@Ef?[=)'br
[1rGWn:#BX)!gjS]A2)*:khCb+_oGOPof&\Pi>OpT9oF/Wh6n'M(3#e,6NS-2T*k,9cA,J`VK
p&dCES<B%u%D4n$0UpJYS9]ADW%i.Gi86KrdLra[(^piZC(.e`=G/P08+"QHhI3'7I*rY@@ib
XI2;8I=N]Aod*-<65-)hY1j$=t&tKW$6r"Z*E95aTeG<IflWtl0fXh]AUc#RJ/,aG=-EpuRJ:f
f3,OAtD2RIWR=j(=r:k9,Q!<g^T\XVP0mC]A9NTW>Ha5PJ6PfDB"E=oaC[,A@kIK-@t)PV4um
3Z-(geSss5q-KJBR-P(OQ,H_ie!.U[+WYmX%,\/Dk8%$,CIN_b7eBK#enut=!0h:rW0C=&n(
<d8grRK8!NIa0H&"(l5Q2s%?lW4p/g_"*btY#_fPJPG`A'`DW[WiL=p\djq#pdBQe2uf,1)n
k1:W;8a<7AbZm$,;^I[24D4O5%IBGh5YaV]A^s;BHX(Z%<#Y#0L1c@OXUV?9dFi7I!OGH.4d$
EI4oo7\+D)D^XpUoXj2.gJk`aW9ML)dX8"jD3Jd`=Kh\Bd;;S+uI8*G^23MeVip(Kuq64Cja
`"f:4NnMNNi2g;#S,N`]AXY&V>VRiB:[lQ`;QETC";o1ehkK#=0739Z\0pL;8qs40p=/da%2/
cC%B5"NG3q<<AErb(2>(B0r80_AashcX]Ap''&7W4n#tj-YMH,3,7XH+6R?9i7u1qs4QW;J@@
,0&0n`P-bu66b9-AjSW0ZkZRun=>D<'/!qTT>3X)Vm[2[Z7ii<V8'=X"@DQgB7?p&7c8&a]Ad
$o^;A=oJMG,J<ms+4njm30Y\U^u<<Qr%nI2FQlVJTl@hAN\D681fIP40Mq\F05:W(bkO-\I1
;!2daaq@5\Lqqkq2<,aXs"X,$49-_SlZ`dlm[rd:s]Ai1i7O(j#_<jH6)$*r48g&^`BCA]AI83
_a[nI!nEEb8N^$(t(Lng?Y(@1^/H+uDDg^LD%4"XN(V00qk[.;UCj.&fXf"UJ)#]AY]AUL/2ia
),j4))(B+D6<K3\k\ha``M3aQE;FVaOLGd9R8CecImDr;sC`8g%qbX*+1p-o\A0AU2VQT8GF
H;i_8IU#3F[a!1d&<pq"Y^^EV+6mS__+R%>*KBP6#K(I!pk&M-I$/a9INI@U735">9!k2:^W
=onYm+=V$Q.biYE`WhXe*@Q?bY6+PYjgX6oQULWda\aHQmW6lu6Qe0CJlDu$f3j$>C-\t8[l
6LGDD:$b37ETS5=*(!4?>@\Y<RZH'0Ht.gOOBfN"T`R?*9UqOAf*uS(OngFRbu2q!"nEm/,)
JI`4(f3P:YP*G%0g>S8YkIO0rlVtA*1#Wqh,SH$3QoX<U@0[LD4`$=[7qVipXH"V0W"Di$j$
*#HeP,@&Mhm)Ig;g*Y2$3de/8/F.o@OWne^$A>gfi,nF,DtV$i\nVNoNMR;ATmk\*,:bummh
KcVSn7D+)Eq#?W=nAQQhbi_O9-W%=LTeNF'YeMsmQTAdJjsCP$1DKl[^`q?Y@T,ji+1HnW6!
*ss^n8e^<9gY@0qLR@,S\]A64M8(k-k[<#Z[raEm2qQH,k5?WW<(E;nRrUii?@r<"l5EMK7@t
.7gZPZbMa)VXAL['f_ka0"3fh:kAZ:\3O/s;B",-UqZ/%;u.3(b/V!G&#Q[=1*l8BF;dO5XB
7S30o[LV2fQm"L#aBh*d'4jgQZVY8mch=-)kDW(kI13edGorT17:!JEO(&uqF-STbpDWXR7?
WjC_!"@hN<"aVS`U=9$\9$h.s3AVk"Ab67U_Rk'ijRd6=McKC.]A]A6jKXI9g/9-.mLgC-(1W=
'.Ie=b*Na!IOKs)?5>POW*#L!)Yg2n"=V/1OrcT'3s*`%S,lgO;_`N8F2^;b_T!>N1<0ITM(
=&GCI*&^_/V>Kq[3*K+!dCroO_I4/Z)fNUDh]AmYWBfj`92BIgnccEBOFU\_dh4dpBqEGTK;W
8$DODPInp0f[\d=7cY%*\bbhoL9X)3"*G!FfE/'Cn6FIa)+A[^p>GCIAu[?u%pUWO#*u9sVE
#1g0/E2%<F:i%@bG.]Ag41D8BBTf2*SY/Yl!)IsLdEZm>1Z/#sQk7[T6ff/dWX^/16f;=p6#X
0O6+0CLk!^TpMg0D$sOc.X:2@]A-?'N$EA/)lV6:LIQNB$S"j1]Anq&n+4m3c*e2PSo;q,Z:pp
#]Ar/Oojar%=V$?PjZIKVaT&3_,<L#7)O$A29lfXQ%h$Q[7V%13j[LGX/AASr:e,o9FS-6>BH
M-HD(N3AmT#U>'L$H/(&ODfVBgXGsb;4/odBFWH=);4W+P\kK4ml2!mU4+Tf'RB'fR:elqUS
7Xn8DI$d*N#sY,_!Q,HH5M`II!?R55L3]A[oX1F;s9+>oa>4a3;%do3Y7&BcU[Isl!5B4f=.3
FF<*"o'[G>7]A>WqcbhbH;a#CdkaAkaj\3)`gk+0juE4soa=o!3CJ,52G84geD`-\#RJY,Cm1
\Su:9NY'[\:CoK<P*qR5?,TT'$bCM6VpGQn^8^hGVst`]ALCa9dm;bmY]Aaa#5BS-'=j7(4<9b
*8ln#YNP&?!QFXiTheH3^>.Qjf#EUL)Ko&TdNs3'?gPqhr!,EINPWb:k@P_om+*,_p/...bM
)6KMC:$:Mah]Ac.F$Ou.X1D9O6:J.ZL*a*af:H+ZU4`mBV:n_Nkde[+reNInaC)U.i;L__k=6
nTd_nJ,F=s@shCalZ,<+lj:;qr&NT\kcli\GRJ?;o8fs+bWt!P6a,7eK[qGA;76dP*I5-6UF
32T?aCDb0P^r<Il0_NjuDQ-JLUBb&RX%b<*(So+$J`%pYpN#<Q$BShJ`5&Q^W`u>**QCi!A9
U#.H5OA"jc?MEh4&sDRK:46<\#(92WfN>n/mV)L.Lm9*3AS@+hue'=o+uAB6H4?;pVg2e]AZZ
_\,p-#W[h"dc#\1[69C]Ajs3KFD.S^0.K?HbG\.S%BPO'Ecj.E1QEO`fY5J8/2d2\TTu#RZ$K
jJ&cZj@*dbO#Ja^DC<\$$NtH<Ht!fCl1?BA%Xhl(12eVmHPXZ_KmRjjCRn[FAsINK8^2b4dH
LA"IG?OsR>!X.BaS!#f:&8&me.:Kp-u=(E(P1iQOg*q)4bK,N3+S)#X2S"TcjmG[R4WIM@+f
8Sfa8!HNi/jN2hFJl[^kTNbZMGqKFTab=IqCh?hEmUZ11(9(Nh)Sp2iR?5VCt4'&ddg&3qS>
dlkb>IU@^5J6.Wc$hP4'&I8^i-a##N2"?\rGeG-J80q6O#hR#4OE-\Ap&Oh8nr2<pC5G]AQN<
D%)_cUE4UJ6XF03@5fq;;2/nP,1dqsUJ]AKo.>2LGSb0Y,r$US.G:.j(^ZLO.*Jb>kp;!3<T]A
q]AgTjO8<`Ua7m8_QG*uRlS<BH*!:^0$;Y21E[S[Y._;O_G#ia)i:t`IFA0nYU"Nf%-9.:Bb*
\BG(+8+LSgR'9RmCZ%9+B9;*HdY+g%TSoTTRWgaeoRcilT5t6OuaP?4_-cG>Yi#V/;ajKY5.
P[VHj]AA>1jL\/3<nPed2u-g0f''^'pqL\7E@Q6,MYBiZiV0L6t\4(rM;Z^#)m!mX!nEd_:nd
Z@?q`jS7/COg>W.s_!9bEq'[ftB+1CZP5jSWGO!'c6Sb*FDtQ!7!X9EOW!*``HZ*D`*mlAGY
5817mB+CfM>umkuG`Zl4W,<9&1<gga?a>lF&-BKma[]AUp>Q4[ZGZNB%?^<44&/"5^mWcEc9%
Q`2EKf.*-;Z.KpPXM#t'%'=m;)dl/-hePb@m?+FsY[RH%*Zs`H#N]A%8TFfh=mAa--Ckj;8i(
&s]Aa,7o0bJ*EGU$=ge-MCY`Op_3KV@q@1\Fnc(HE9E?s/)Iq<X=Y)RX-8pN]AVS?#Tbi1TnF;
2a'WWZ%_&UIoW]AX>*m1FNnneA[g<f)&]A8Gfl9Y`INH$pt%*7#2"8B?"@WFcA/cU0HklQF#"9
;<25LF)Kb\]A^PAbD0,&:Fg;V>qTf4dD3JFQO[c&m9Tlg0Xi\!jINF1.Y5q<>h=aa_kj9.)$P
VHLNB$.4X^T!01bc=I,cq)#M>H@.(ZTgAWV%4@\0X:cP;?@27W/Y4F$<)lZf+NG,idh)K&[,
+ZCH)]AaWQSrYp[OK7.e:c)g#q^n'1*]A:k53K4rj.`f:]A"CE:_oJ4H>Wn*TFBPrK+!;T&i6;f
9$]AF*YQ@8QcL02b`purQQQoM8R?an[04]A3kMiU+P)RQ'O?=pplA]Al#P[EdWndd;kfGOg_#5n
g(ZTNCA,M:8NF1T^==+0cU3hN5m,-&mQ,C%7<5q<J*5c:$?7jogZ-+;`m7<!&:%Hn4AFa^*[
kk-!Yo4blKA]AZ.%>E(B5tknh<ZE<=*\Du^h1a/`4`eJ4,"b/);si9%jRgZCPHq@mOItIe#?4
\ZG#arW+7]A#TmVag3qBKTbGrPeDmqR"3k9^Rr[m)o"<'4`P07B0X@!'C<>XK`1g>&TGBm0/M
F$9#Di40F=*XOBZO)K"R`Iq_Oc)p5;6C\)g>`rD$nrU5UNMj4fl%?*X451\HN,8XYm#pqWA7
I1Yr@[M0)$qLO9.9M(H.9*s,jj!7gCRAijkLNpR%THc`#/QkJ6p1SM)P:9WnR`/>Na`mCp*+
&)J&`?eUCNNpU[\D'p4q:)D\1ed/cE]AYs`ZrX1=\X-L"#:R!#flhAf'70gKDojJ06P66]A!Ng
quV84Al.;l8L]A#-po`p$f&B>]A'5e&Sji.fBFO84^+,kf>WS*Gf(:G#3^sne]Aj:2:ing@mq*7
R?IK3tOR@7%W'!Zffb->Dhe8GPb]ACC:`q60]AtkXANJT&uPoSW:q-?%9C4V"Jm]AgUeK>df;_s
;Ce7f^@GLQ"PC6/Qbg0*mueT99h?p3OF"m*<DKeAQ]A*,OeYS`'Y#(g-.(.NT[Waf#T&i!q&-
Et_%^d2D-0ndEDJMNUldX6N,C\@-J7n&>:Z\3smH*cD]AGYLcA-?\mWnrX1oHS]AMpa0Tb1PH&
F4R#_EpFXR"3bFJA3F\C"1bKS,7LBi_>a@8-$h='i6jq-c&o*4ECZ8\t^Y29,qfO0Y#<Mm"&
F9V2>[00IgtS2.7.jWFN,<'`$L_DK^=ZlBqp?ciD2<-K.cG,BrE1:]A4@b4sc\5PXKnkJ]AA\e
89gkTm)80uY+??CP>aX%Oc*G*K6kPlfZ:W83XM#*\+(Y-b^fjKgC?h[V"6OO3X$89r]AIT=C@
ab`QYEcrVoM?!Veql1iY.!(u2`QO_mOBUdt[<7?\LuTr8W>jk\TjlXEeaWgK(,D-o7W?Y/-E
;\l59cgZ2VjAQ2""la8f[oEoVZW+P]ASeI.8Y/ABS*^M3S\)^(_0hc9a6YtCa$Y''BMQY@>VI
lG@0/IK5<G\(r)Y#=TbaGRGE>^"!4oA3^0OujST%X`BuLM%-YIu06X<Mkj2)0%eTLr`nK[n3
a[9CSR'ghL>;5>"_lleXpUio!(($n<*kfc[[:,5r)iSX\*g_j@Yb<+1`:#"=!1BVYJ>A[%k`
n,;;V*a/hP!Y\'m9Mgl_O8lO;D1X_M=`F-7I2prcE%>q@7_nckZ9baR#E%ADQoi0s.!e)RWB
Ie\qDJ!bo`$K&51D^#\l<3`2$V%B"-=^OM_A::PD^e>K\9LM.Mo$Ykg3=$XVL*hA3mkh_!a-
,'!m:OqiZ4SEnmSc-1#p'4O*\bE_bsQiufRup--B$o+aS)'?15r(J+[>-"rd\L]A:pn!tW?Pq
&d-4^[aGO#BcWuIN9dD5S2/^XE(X$_F/`ug:bIk#8PCWr\bB8@#@jqn:9o=->WMt2qW_;CKY
mE-GiJh^dp=QpE*Pj+sK2NnDS)l/,:?73tVj*g=ZWT#a/PlZ7CVQ;GG`I,20kut47=:^hhk4
g:6G&B$N/jLm_k5>1MY-PbD&OAum#Djc`3g:/KOip:K\;_$h%jHVmHI)3>kFKF+OVlTSg%ut
=lgO2:)riag=tH0A\oJl"%t*8Wk8QL&&1GeA0E*up)I))+na3&kds-Mdoo!^$?V\NJPHDtn&
Qf[]APqAY&/:[$2]A"@]ANWK8<2m*W_79_]A%CfEPi/[N^7@;0dM&&GIVH^^RmO'lnY^nP9X=pqO
$DBjh4Sg?;c90!("nC9!#SrN<3$)P15T/ZqBJlK,=/@.o0=C)SQ87hibbF(t/<.j0)cAd@Li
kE5sNra=2)mklR>.RrQ_QskY40t?`^X_DU&1R!`@k0VV)WVT%+\n?4#Bi_9LmaoGQRu[sgR@
,qrnd6fAdC)T^:h2&/1ADR?&(rEc$g0J">Vt&K8[.6U`>'4KCqfH8uk1t1fbWV19V_]A;bfm8
@_V3W3RkI-:u-(Jmid9"I,Q(<"p;AZMSBDXb[k)!EIK)DhrdZqZgTV$gQ<uY>-N!VBDYRNjI
<h0=s\F=d\T;?Gj$H4@2!q:E$ST0e=4Ga!@Y,%Fdm8535"0_8TT6I6/%0l"!KZZf*-,9EiRl
/,9C$G%T)?LKJ<lDT12jMEg`).<Wl.6k(etbSm\kY^NTT<?52)LqAqB2\!q>tY0FMVOJ)88+
jB,#C@:oSAF<qjNXT3l$TlRp))hs6F5%d1.$uh;QpdO%LM"9\8=5t;(8?]Aogoh:7_HGoCY@b
8')!RAjp*]Ar'%9S6;?qVp#io88>MWi(E:ePO#M`,*`Z=jlKJdRl(Lsh\C2;h8e57kDJcg@,!
:s`eo?ADj5=S-9QImP-PcD;ARZ^;Kd.toc[=2,cD&OiN:pBm%\ak;'[*>pqEO"9_dBuF']A8m
)6Y.Ts10e]As\9REAaS/td<UP#!.b_(o1C]A'kZ_.8f\BO9p.Zb"7P>H"\H"M72.=9u[=t2p/W
,jFX^\q:JTfAUn+aF*\7B._!Wk:$a"Hl@aRna9bSZOZlo2MTbWoe;ea3[f.2G=;JqSiUpD&J
ho4\]Ae$B2:X[Nu_l0%S!<7o6"FA,U!m$80VdQ+t(;PF`f#b'LneiP8).@ggA:dqTO,%T0F:=
CB79*?"4[&W;5-(e=@Z[i"Nc7@a^X>U;-SPiN>giFU,$J5\A-*+E*M--cg"bS9o&F-Z9O(hg
W[_e(?2^G@-nOC^>N]AgdEu>GX?:FVU$X0dmPMPg9n,Qj_a"]A9k>?I2[PB80KSL'Sj_Dp4p]A7
EBq]A1RA"qZi2Kc!Q``[Qc(8J4XU(",TCcdh>0[-1AekVLR/Ar42osn5NqYPrYr<hSe.%)N.2
.P.Pl(a$Vo4ei$'cX]AH?Tn7Z#(EaNY']APN<i:M;)cS75`<)FFAM(%T<*HJDO&G6??]AH!XS/V
KT!Z;)#AQ#%Z;[)>qBoP[FgdfRC6$5VB1=P)$,BK's39KV[INf\TNEKDV1\jCEc/"c4X=S[A
M&CM>q13S9`G'>lZq='97a**RtV5ftCX[au/P<Ir1sY.4<YQq45ueggKs86s[`H,4=GeA4O\
7bqg:8#U?`b94N9#_Wi;@<N'?\6g1s`#$L&46F#5&i2CA:>Y:mqS^tBO5^f\pR]A?6<Ic(%$*
alb3]Ak-r9,HWs'D6?5$+,[o_*[Ta']ARVR^f^50W,+<$UZ!Po(@jAG,,;ou)D.E,.Rq+!a+tq
A9SiRFa*N.YjguWK]Aj&7j#7$>,FdqGJ;g=Bo%]Ap';_oQ$&<XUlI>\]ATIe-*^MWM(`6HpLu[^
8u8QT*MqX7>`"$M:SJ*6ECgf_!Z"ZCYK5c5n]ASQk0QHV$*oF(]A:)@K7\`F:-r&X\Cet4MKhF
3]AAU2L>CeYUY?>0?m`F,@_0[)c\+6(A[P=IZA=>U[,1=^CN6E'Fsh9Ehkc$535bd?jbA:2'E
$6W"od0(0;nr:`uZID_tN<.RPnBP8G9d%Kd%/6%<2G<'?Cfp5"BNdnLBq+0Xj+D-e2ER^e4E
\3pr1P[)_ZG)#G(GtMJ+/\cnTLAd@j<NAE`=7eEGqPBok(6JSH#d:%GFD^hgM9Lgj0f,[1(p
,4<TRMKLl%i`:KP+U8=F8H$5dc-\#ga./mO9D)(+n=WU-qCM]AY-#(."GQ$G3>+RWX!o^f5BY
Y5DunL;Y2R+k(K%+O3jLDkbRJs`eGI2NdH^,Z_5?+q#e5jm86T?C@0X4Hcg"'5Ip)0e^'`OY
krg&MFQndG,8Des2,"mm0<o%tg6;2ffe`sAKT]AX[#D.Vm\ql)r,<q!8&<Usfs0;hB(a]AK^M_
=H>1@@]A.Zc?ttA+\kZb]AGMS^U.CbWLRN(@+$V7hmF!Hb,IHo7od2fhRf!cs'6f!]A+`[)"->V
tU#/%W2p+-2Beqf!UbW]A`b>W[-tq,MT'^s0u0,/S=kM2U=&E'u_0,HRH=?is8=7K)p*0^fTC
5Zb!\g;urL!&SH!^$J_OLMr*Z87M=C8L'TH%?g4PYIP]AWP"#sTc:0Nr5ls?2E,T^Z<[Q9]A!M
.#P6PBiEY-.0l4duu\m^tfa'p6\[RcraX_:4'#/4&%h1X^c1d:T6oHbBE2"h<iqY1@LOq207
Y!^OsQr1[s+5YpULhVF6"[;38/591Tns=$*0ulo:'YBj$X5<L(O3h/=hf(S<5_,%)XGQhQE+
il"s,QuDu&I4q_^hef<Y_pAh+)'A:1^HMiY*BURV!G5-]ALX"oJ?3o#ok?#tSDI,(.n"=ThIQ
Xs1D)$$B+%Cl3oB$(AUcp5\eFoSiZqgsCeA0&<2&Cb!a/1]ACD;s%#;09>)jP.(e"kh,PQ\Il
/@U<9&+'aa8V-![*iFdUY!%bL?FJl%!Qrrl\q*K(\9!6&MM_hd)91[CB>MfRSq::^>UXQ#Fg
MuQ4NIL:e,\5'[9#fs56KX)L5F479RYC/_ot=9k69B`"UBbYQCATX&=,Y3&%'@]A72kiVqCDg
g+[honk^$2["giEP1PT$pHG[N13T?oJiA<qOq,O8M![DX2GCnQ[oK_;G,)p]A=gH0n3eMT\NR
]Aa5SIq2NTI^NuA73rb)>FgN/&m?\KKnnZML1*gc/\JlV'TINUJEM5)6paPr.R.aeiJjI,$`B
7&W^5TXggd02t(sjg^n4HS+rd=Te''jW3j*n5NMG':J=h:<^jBWPN^#X`g@m+B$&m9C8k+[i
a65k\,SH%!WB*Gn7kA"$V/eeH3hDM%e4d<C(*`Ui7kq9m"ShBLCa]AK%=1%K*>-@1iH$/^;WA
,$YYYi=$LU;[\C6]Am2d%tl;#dJ?!5JmCUr$heg:68s#(i%iGI`/ftXof%[)cI,@%Un;olID;
*t+YE-7JE>Vs)nj&&p>%q!6jXJ0B0.l:m<_.h+q"<N$t"HJ_4GeC2=%@,$;3=^1O3mXg@W(:
p[1RI#[6-=o$]AS;_MeP^cJ=q:!)d.<4f4m-;HLRQXY+g9X%DiM?NZ@&qRBD80/3,]Ae0bf6:X
+FKC,p/l+>I+2Gi4XC!n$N2U``_'l6*?:?uN^+;tf2#THirg)gD04i0hrta=X3dpu+RPD%)i
=c%HfmbRY,s5]A_33_i>LMnQC>k7Pf9]AEg%l2EQ>_P:X2ZF:l#ih@<)SJR,)Vt#TKpTY#EabE
)'a.Z`tNcbdm$gh-T\7h.96M(.6h7OXH0$c0m33g=TB;FR)!O7T(Qa1-l0dS[cMp)[,E1B$R
+0'68;Y[`#Eio4fm+qXuZ6EPJkoS]A4p^3FQXA6D=<:S\l7iqq]Ac!"^3DGi'M'cYj,45OP^D[
+A0\81g+]A`X(l6F1^:N`%#Gp,%cIU"[^,(AUXck_np8]Aj_!:"mh[ERY:j`hKD"PPgdt!qCG@
o6;i"b^#J_OZb;!h!O)a5CoO4/M4Nk@DOOJnI,qsAUV3jZU8>fS-.r2X'/<:/LEMg-TA.ZSm
-2.$#'k'6j.nXY&cK#R-+.(*]A_U5Jej;1hRJ892X<[3;TUI$]A;>5^s(6%:X$e!:+$>CNp(A`
R]AKK7,LttkoC@uYHBf%)c9'Rh@KE^4'ELcnMrBP6HT!h6BrXeaZ`K4^R6,g9>J@g*:d+!W^U
Rk)7JO3BC?#U(k4VXA'Yt`d!etkfO4[3nL;Zg2Z8kPo]AsKmlB3:cJHjHD-PVPQL*=GXp47C8
=B"3WWBr9A>nd3dAp*/4kS<uLN>(3BgGFNb=u&,!'k&:<V6KC<eOnk'aa((%H_B]An#/Q%!*E
&!Hm2AW?9?X<*mqMosUq^j1G4JbmWo0o7b8f'**rk.<]A/P9%5+-m9GlGM1W1m.QKS'Zf6e_l
L-'DSAA\'e?)l-bVOHj)cBmXo]Aa!GA]A_P9qG;D.!q!=f%4*9:Ac)PCJ`pq;V9m'69?aEQ&m;
_akMeB&4o+MfYZD<K3<*sjMqD66UUCEuX:Y*l1t=l<Xl?8@#7EVDtR7J5KaNe@&Y@F4#&4,Y
q,0nT;D1[_Gg;q7>j"LM0*.M<Q<?[B>V*F3I8cE"a=Ga/20_IT9'J20&c>$U;`X;1FXT*o,%
XA7XtZGglBSJA)uSJLCbAc>d@(.Se%TKH+*lG@)7QhZ6$G9tmU&eBJ6?JQ@T2#09Zl75Vtm9
S::jfP^M0AOGVjI;2%r&RPkT"ju]Aj(S"J^d>\+i,JdT]AZ=._1J7C]AXa40AfUBtXYnPM(Ss!3
>Aamf\Fjqb3RMVEtoF2ht(5KTS*rUB,J^V]A;Bm4EDE+a1)LXQWo,0AScr4<j2IYFkZ<:X)17
0JuB9;S8E^8^j:[@^)JbQ@NYH'SDdf$7EXi;Gl+)+VQ<@9h_rbf#(+q=_&Fqsn9_:5Yc/2Ui
CicM<auKp@%b!6WB5pCm'k>XNlCY@F+X2XoKGWuX5mKs`M?ogS`k9MElEHqlQGH@=CVOZ7ET
Hj;:d[FdZ'fQ[b?iM3I7pi\)uQs$Y`4e1X$X.JQ,E#4k-pn.CB")@_b_O3$$ae:fdp%TVS'A
qVbDs/*2[NBeI9.KG"lcpOShi(-j>%s8mVc[*cXraaBFBLY/hO^M%97d;t?Jof\1THS%Kt&m
9VuP-;e[1a3TBkR7627Q6pmTh*OGTsG@gZq"K::(kK<<5nqeV]Abp:8bG3!25PO_rD\O.n^ST
B=Ha=@-M8\cubd]A,>Q3ora_K6N)TG0U8>kW0<[%8gquBSV@eM6^sJk+m^9<ocn%a;6[Ali'B
M(6r\=@dD"]A"=OBn!,`")j8%([FN>)*hY?^Cdc)F14NNKQ>+h2\:6p:^N5AL=XpHpq*jnC]A=
0tA5lgkHmJk]A0RNN;$"ZhFZK`fetA3lAnGU_,`sS/YK2LfG0-PZ\2/>CqT-*3&TNfF+M>f6K
m$\<6nDO%W;c*DlmCArl)6jWW^guY7o<RSTg_5Z6Bp1me^G]A7D4,%[0\XFCMi*aFq,Hc]Au$u
h@<<1[b^c[%E;Xa,RaFX(ho`i6<+cYo*;t%=?YeA?38%]AqdO6'X]Ab*1!kjOL[mjqLV3)a&4<
^V>]A(,e7OSBp*'BI5c=o3fi/'C=q-CNknf['`\`^gi)*&Rjk(WfDgKp3S))3/-;@DsAAIB#@
VrI\:WXZHr[+?q#]A:B_hj3fj/de?B&IYD0>Ljm#@os<2aLIEhE#<$#/i%0ac-N)OE--J7!5&
%T1u18,ciNg;^E<mqhg<G?KKJ5ADTYh=,7]ACTJ\Ze&;K>HKFL:%-?I8<SRBmB;1*s#dPkl1*
XF$%+dHP1-p;3(:,3J?>r$.;6Y4>"Uj`mYt9qg33KW:Q*3D>-H?<9T,d+9I97DIXdQ;$lVY'
:/Ltijrl#Q>Z)Cr7BQ0Yrj>]A]AVL`.,=*$f1JJME,TeWSdd4bq%,4-j8M';(l\(`OH52l#L33
*UkRjOWT7'f't\ht-`R&`p4\VXS4k\d=g%Xj"gOZI;$N"a[kM;5%7tm??$^\7V!,Z#C.Mcs^
jW27j,Y%37O7'f/q.A4cn\kMh4I$=InF+_Ad[+NM0h7kThSDG%QChkqf6oEY``5N@O"NI[:1
6t]A5e<Cid#*8?\$Ce#*bhL6Ad^7%'(d/"UYQE2PkUtCa&B1K4!mA\h;1^J.CX3.6ej6Zj<W8
QAbD06=-4YGJ>J#XKHWN<$A&j=mVYg=o0%qk15Zl4dS>nD0$4^eY;HKRoKNEh2F)+E.a^1O0
ajT^TOS9Q^^I[@5>[\<`pR*&,;kdX/IS541/i3G;A%T)X9f<RGg3VmN`IWdl0mO)ZRWh<U/=
-$T@*Ll/77F_hUF5.8$M#Q+TnO+HdVaPg.E1>p:=g;*<b^[<26fUj"N+]AW2Q20+>E,s@E"5"
IliMZ2QINM@uIPV)^n'W3@1IV$'YJ:N<46JlTWj3u[APeH#U8SPD(^Du)r'VYdGX$I5U1pY,
8$j2um`.B=PnMDMgc4,V&oDC=B4.T-Q^NgA9siEgT`oiK7LXC\C)g8i;8Gn4i(k&cna,'n"n
kNH5=;(YIPtSfhiZ"J"[C!)@=:"O%Q%m'rmrDf"VUJA.<_l5LI!!Z;<Q<ANk<>EW_5L@qa;:
!VJE?87W$NLeS'LE>M2cs=gb.(fK*k_a,%rI+NJ9E!\`7L3bbDQc3R0gebXVkDp3Xh(S'b\A
Hf$<5T$Dce^-3$!nWO3*<'!G<=_+/-Fa\tf(+[.14*r(O<.XG88$,U0Z#\:eXSps-BDOsJP4
Lr.CR)k!rem?8'1lnZ06F"TM]ANN%/UChdm\$<`Q&H622Vf/igfZ-I4%9`)(miB=M;cN((Q(u
@'K.FhK-D/@:tGDon$[\R7Tp'%>FP0MPB%g,8`dDi2*TIQ;8K\qP6+>/99!JWiZq;?P8J6+<
IB5j<U2cR=Sc5kd[G_25uEDWen=Gf;ZLlZG789ko&Wn5+RHK=.#HD(h;.F]A"&/?5CQ4S_CK[
E(fXWh-l/Vtgf6c)cN8UUl:GX8PPc_7PVeF<e(W)YIA+,.0,C04S$7*_Y4k=Xf#;Ut92D5?`
&M^bnj/>:!]A[*T&l"iC;/\OY;a9Y3%HaY)J%imhdJ114$295h+gY<I9MZske[3Nd*Qb?U]A]AR
jQ!4K$dnJNi1mA^%D9&VrG13pLBT,2$EJ]Ar?&:i3M2)pU]ABGQqq*#0gZCQ",DZpGH$uWCP>N
1blGLD"SL^SdA?nk^DqFboj3!7<+t3L/U_.&_3`LYadJOY>MWpR_]ADrrjGU&RB=5Z#Qf-I`P
Q9ug>2iskgYTDD6#Gg^?:.C_+1\PmgY0k#0rq^1X.eTNI+J4GJ&;JJh,#R3ACq<rM2N>oENk
-44Gg3:jP@Jh9Rgi-S[18Z<o2U^RWtIr6a+7EZe[S_ZcjWbn9sffN)QPPl3?BRmq<10b>79U
^(KGg8:Z,85UI,]A*T!qPV]A'I^="sI-agp%Cqd]A#=lI%$L2D[(N(S78Lo@8jXM\JmZ1Rba.%p
>_pl=j`1[^:-m4/]AT5`[D$^K!G-YKYkJ6+pHD0QejW>)cHM.t5[lG+>-`W^Q^6je<:m]AqS<k
o:lF2XC":j5DY?^.+nt[eTGO+afp%`<f@?BZ1d)I`';FFI$fY"%GX$Oq(Ns\frD%YA0\'+Rm
Y5;]A2dE""5=Qe*PCRC=c-QW7gD=Cl"2`q!Mjd[2P,(dO5nPmE6o6Ja))da]Ar:2_WEre)//GD
Y6@\7ZoGIcko5.IpQb8;\75pLMKYTfRh.U?.3QF"2A^KmL`SAUE24U-89hCf&]A,@C2^%7jt1
>c(bG\tcQAh$37KNu6\QB+#em(,I=5R/j(1(*7Ymfi^G39%%hbE.H@>ud\j^2`)tfk;R4:<D
BB98ft3pA%ser3l=sCGp;^"GCWP_ns=$/Ied%L'o2d,km4-J_tF[Mu,:C075j3\sGN1g3PD_
PZ6HrJeaaLA@-QVp<cf@j(#*`(5hYmq>)*KIL[p*5/XNHA$FLd:jDc^!]ANKl$!OuYe>Los5e
82s$c@Qt6WH8qp\l8OaGf'sE>;[BSGMe\]A7\":K03_gKm[R5N^MXSs*R"NU*-5=JJOId[&QT
Rau55Z2=TnM*iJn!,064):KBh"a,<;E]AP*Xh)M?m!P6Zdnlhu_p*W0R.O*q^H:a(:mgAH!KP
$@&!]A!T&ihOD35"6bXL8S]Ar#.OV1%SUMjucgig[noaclD=,><a]A5WSAo&]Ae@ScDM?Gm#.P($
*]A;?_bod8`dXat.JmLRB/2(\Gn^`%Q=M*RpCr'uPdhH\t^$f8RAmR.qYD@,m@r3Je@AMGl%I
Blt`e3!s=B5%R6#mf]A&*Nu@T`iU]AWX*FY4E(S,=onZ!>H+J4Jo6/H17!cDL.I<LB@[j1@eZX
5k)c@'LYMUCFR&<(ZraQ6j</f_jQCK6#*ZR6Sug1[*C,@&+ZnF\*#JpDBYlK5EG5e1*!<$`R
,0b?K2P]Ao^uQ856Y[++<5R['V*M%3pphKYd?"*E%b5R?_o(?TM&?cgl&7[@XN>KoI_CMPIXi
RrE\LX%TuG5\%)8aQ_"MUZt[qd7+#<3\/Qr#!B;a*s9gUR.2WJ8AnF,:\)elt"-t_CJ.(U!]A
-[m[G&V\gj\&,1+`;?]ADTVMs$/]A-Pqfd<p<J+\[:-4BUa<W*k3Sf9WXh-(MM!fcahXV;i?f[
m2Xl-hP;*`4tW"]AAh@c12MM#(+j`k-C6g-Na\iPb,#rc!9fgG`UcuqKW(bX5ELH1cOY9+=Z5
/&]AmE?l]A,"oa,LP&iErohU_"8dC>X.^tGZu88XVMXlG)pF1]A9]A'E9*!d>r<AH2enpY2]ARXh<
eR$d+V-A45GMp"E=kC_Z*IKrI!ml\:I'a)O.[I=FG(!$19K0)mLkE0Q8e%(CdY6oYCF:PAm9
aRn'%[JmgBMuBU`g1o_=ji\mr$OphO[J#33kJ0#UN5u&oY^"T1BDhKZO'c=P:ip\B'a)Dej6
Za,[PFu0hg.bgB@?%bNrT0lK2@RIkklJ^87reYj_S$cVhm`H'.X"k!c;=IWQ7$GRDZ5\eIed
VDZiRqqe#hkti8#_8fHq-h,`\a.F5'P<CUgR11t1q3aA&*j78EAFFb+-J*EpaYGJ9C?Xt0j6
_o0bVs[r=`,KDO1fe=L4@hkGsL..cC;qcc1%F)d?C:FT@CgJk_(\'@s\@@7n6Ggb]APoi?HY;
TGm,@;I>tUadll[(=ipPR`=$>cR2TV]A:8b0Y+naK,pUu%d&QQ*1//cf]AI>mEBW'oJ"%sUZ-^
S3$d\dG!@Z<tT6+<qI\:YRkOQg=KB00<gYngGGnd<qQu:cI:)8-Y=Ub<#`]AUu@4c+oOr8VLV
RN,GE"DSq6*>9\@(VmfasC/I:sIVlUO4nHtb8)=B1V2HD.Q!\V*m@;e0k=Cse/XH#udH>DY9
\QoNreZVi^+gCSHKcmGfQig5sI9K-gdXe^[NEMch[3&@K-^lMm,,q_;GbGkYiO0pDUQ,2/[C
'@na61Z24)1b_'aS_3@gI\,S]A.H_Zb2qiMUSS@a;X'i$E;pnQgu]A:e`"fkLeG`#Z-*jllQOH
G(G/YR,PcrAo/7Y5)au^(cSR73o<Spsids6#p"U4-;l_8fP;]ANqJErE0@N'jt_AfV#B5aMS*
$)(,ot;TR.DH'8E_lXBo'fO1_cms<m/^.<D$WjGo'R!c=R((GN%lo5O0bYkIIba-,4iUh0s,
,QL+e!ek6'2!G=%2T2.D>$Dt!=h(,L=_C1)37;#P:5'mcleVeiW"cP!Ahp,i-5]AWR5[njX%7
dop&8;^H>3)#h=+YElZbhA*l&XN_6*\I=Y:iC>cb,5rSqSPJt%lL<D`5qMCVW\(Q-om9(;;2
1J6?uLGO0UkN4VEMZI=;;_,/`T)gfCt`0$=l&EH*7%=UMAYk/D.MoP2D0"a%\+_f[Y^MV'fQ
e@a`1sa3R:&)99[PK@W]A@3mO[?rpgmM9dpR7#B.*#gljD]Ab4PB;>$!LZhs5K]Ar!YX`Gn26CV
"1LJTfRO[F<pD>a@2oh&Vog,Eb3*l#qt&U9$86D8#C@Z@3oEbEub_9B"hZul[:);mFTbhdGZ
h0;ipfS\Bn,L;r%3S9<SZU&*1T=XVd=C[1aaqn1t\,@"[)QfSr9ZP?Y@ZfQ$"_X(U]A7?ar-Z
b`S;M$kHuH+H7<eOEnWga-8%DFW2=fAJLZ"nr4R0\\Sm_;kSpW_8%"*0^77t\)*IuWk5coEe
EO\gSo(oZ-=pV4]A_oG2osUN`X#j*/X/&8a9"j%NG:B;X@D`dW4G71$+[cVL3n\\nu+9HKQ;(
aI[r2Ac#1Q$h"b6XDN'P3.e=UhAuXRaB9n0X[ol0hbSQgI[e?cJ\pst8DQS=jXCGt6"J%Kus
.N9?(Q`gpm7L*"O[YR\!'FB+TcMo#I8slkJnB7h9o[6.gRV'B?0:*Kk#5dCU@)BA/PjJ22[6
I:cY$(J<<J]AiF(/a4ksKHqiO)_,l\&V0QPu@mlNMq\Gt3E7!e6aqOfJf@40&>X"U&;o^J<U3
i@4Z*7WPFX@^sP5jTM9Iqhs80$8ST$#tMK4ohtY2$Q.8&gs5Mnc8ee1ER+9a)bPeU.l]AfO'r
-,lMKcP.6<]AMG&rF0Y7oY$TJc\KL)<A#AMSA1kn;t50*oAkI>A8ko=@9SiLl#0of0X[KjU%7
S3g0O:#b47lUM7d8LPQ&o5+j,pg<0+,"uc;E^edHb'PSKYA_MduSE#G^o(#tBMi5`b/4*46[
']AaXF4RfbIQb_7RHr6U0pQ):DMN/QrT5!S:=E\>WMUI@6r+rW,K0!B^[WVWT9ZreeX.WG=?A
"hf$M*fAjLkLjm-.EUu54Rld+;Q8fN#Y402hPp'ok`Et?D60If1k;pQ1o[bEuo&jjnKZ#g]AD
L:?VO0mXorh*UqHo.'#Q^WrA*)3g%Dro[.XA\`0+J04]Al""D@C$32CM@5$p8%[I!R(EQnVha
eZ]A37_LB]A=K?A29W6hQR\Ce3'd0"kMdU(Z%ek3\bEQ;J&`>i`V5R-5.7;Sckg0]A$(rQ+#k&^
7dXA=nGob9>#r@8/K/$G)F^`Zl)&KAr46+CS"qF"YJii7R(@C0lZ1pDJP5&sp#!&#t)\Y6i&
jbR!GTk_!j:4]A7R@\%<%s5.rM+>6MMM0C+6^rX&?%X0`:E450?ga3t7Fb=P/cY\c24:o$Yi'
BY%*`O`$,`mK.iFbD\0b??30Y1+(+1$qR@8t#hq<;,QL*l\/(7uO;k3,bL`[pt0^2qZg"j.;
i4a8:+Td,i>-?Nc!KQ&1%jFX)Q(6^]A.?1ksh<,u.B<_s'nP)ndIU2Dn>4ZoI5*m_MAbh90Z[
k>?IC'T.et^/qL%YTk.jMq8'-!`;__-6NI%^o(oQc?sIjT9"2f3i!_ljf>o'sUJ>)4D<Xs&X
9hI5K)g3.J4qqYVdpM,/`:6I0Of]AaXOh,djM%=@2,05o"pYM<Dm94*3:W%QfX4t@Bo6\?,OW
ZL3mU0q:*>nNgGLDb71XY0hd*<(emEnA\]Ae7k9DD6U;afTq-eDJN&\j]AT,sVR]A'3[pr4+I/B
bO;5Y[p9"uCukP[ZfZ@S:0!QrEh4;kXdZj@qc4bVeM?"`[a=c*RrkNPO"$%OXZL8Q`4#X^'C
o_C#Y+0A5+k:39mO85n8nWh)^&&&aZ^nk@e57Fb$#]AlluG1R:gksK09#djp-Effs2>[4a&+E
22_q^sqOr:]AmZ`[um<(Ns_-cPq7[LDCB<2li?4E[Qd+C;A5`7[c&2jL0gpPnlJLrpnb`j4(P
GoQiKr\&#/CBrn'a34n!qVrUgJc\LX=W%CZ_TpKTHW>4g\9%YJ.D=&T,V3&*EbE_/k435Q_l
]A>e<&K7Y,29d0ACW[Ye+uVnIMP<-dYe_EgQ$iC-360i@KTYAP.(7bGI@q-npJId88[D)@Sf"
#_*ZEFJQY(us=#5>&R;_@LCJc4pGG-R+m(gF)6GV"Yg4hU=YjVaT@`(ijN',r#2ZR?P]AAYZG
?iqgDpO/,[.Ogch#96nOm>BLmdGq'16/,F5]A:XY?)aJd>)K;Nn*R+7sB#n:jZMDSLK>3!7/"
CiOZdaS%E@<_($a%gL]AkB[l0?Cf@.q'4KI6Z*sYhK04&]AH:%a`s3?+;G,7$(4.j8@M-Z+=_6
]ArQ7D;3M/2D9jYGhKHWCZpIJRZ=HU3keuaDh:T!*b,D,IS:,[^k7,YAe9;#MTcL5Bb'?]AZL?
7gWU6LZ\.M$2%qW?44!cofXl%3kM=S!d\"]Aj&uX<\%-*%8/J2Q@_0(d<`VRD=ipOTFG?Yq)4
<*`1&[DXuJP?m6$@2r8&,n![Gc#d_OAkMblN#P"#H_7DL<(,OeMKX0EVuX&"fDo,5noAFg\+
cuR(2]APT;Mn6b!k-G7XfV9WWBY>&t5W^#M[GDUjc9gcG;486W,qX9QEq9DCV`Z&"b)[DUS!O
A`JESYVu93(<GlII5`-7Do0A\Ag_oZ0lb&=54*B,6ZZJ)*oOWdRNTXP;T?A>s6&+j+X"6/5%
h1-0G\KfIX#KSm]A=l-UD;b]AXeE`(*MfE^uFABdJ$1C8S<<6lRc,B5;B1CU';8&).67DYa3<1
Fp!)fALW[[\M"#&Sg>Q&CkP`*mu^;&P9qUbXt;KN08$P_W$>S5WcNIA/tI9(6JCa'X\Z>>`>
?l85M<3V,kkjJhWAORK>U0QIusDbMo@8A*#-^RNN>lNOi%9^WHnj+9J6t*n-6IU\_n*WC$`0
K\ktSh&S4iC;2('ao*UP+&I'JW:=.3W+C<m\Fu)DZG!Sq?,LW8oO>WG9j<o%+X-!sEUui-_n
YEg-b-cE4$1(1$CW^EP19h%&HaMYXh_')[X-2+W^*to]A<-gAaG'16r_=7gpTcuQZGF\;W[SE
&M`"B_m#q]A/US89A>TDceF<)PA]ASX1j5!8VEPFLZR`FduHWL#8(/\$hhm2E=b_Y3:<<Jjopr
+1'J+e[(kbtuoXq4]A781Vr\+@fV*9!d;YZV)b&Ujo#O#V7#P(%kPNO#26`u%LN7'._@25e=*
tKW1m*1g>[mIB)0H8_)cCPNCV`G^T+#_:WlLElY;^,]A>F!L*'jbJ)o]A7Ib+t:mj<(:<#N_X^
Gk4up,':%4SAu=[#rc1sXNXn;2:d310p$`0p$c0FpFFOujXeP,oH-?[+:t2Xc@Hh0[_9D^*D
R&5oYW=qE+CTT<8aM;bYC$Cd%3_%#fr%-1ZCKRXc_n+!gSYT5MoZ1,S/dQ'NkFF%O:PZIuK,
@oFE%D43;[=OOK,SgEH.CCu<Wr1,ZMJE^P_a%\`nl(pqHLnGd`mjXos0ef1nW$/.$(-`<,s5
bE35%a8S#J#lJZkT=jYs6-kD@FL"MPma5@E>U3USk1>+*k$AG;O9QQ$fU5u=IStc)fA4s]A8+
/V[Z7LWkJ=9+d)BSI3[f7P9D-ej=S..Ce,P1#-\1\1G\t/[D3r*o[H<8iNgIK<_JtN6?br%e
QW<_'miK$RO>W)8kf<bl2Knf4TIh49q\<GM/YfBUf_BUOO\S.&]AFlnPGOIEe:-j\]A#"\&^L^
[@.<cJ2aJ(O[!VKp'rnrY'#B_W]AMYog;^i1cb#ZC*BA<]A@Sn^=iBcVu'1kh02e\PZ>>5IaRl
dK_DP4KS>YB_r>/N-YUCa[ocXfo,iG)bBHn%8%q*fe9/<1.sS/I2Ko:0#P/T;I!GXiWg,!qD
oX<=XCfX0GO_j4"C4+t_bh.VDC[2M$d+&JTr"DZ`5TOGTII._Z5Y/E4uDJ,0IDD2#^!P(A:0
NFjSjY:p\"&Mn*g8r`@2t.64(U[SYT8+f0Vq%S5_NU0hcJR]A93!&h:1]A/,+NOI\QKLo6]AB.C
QJ@11#oaga7`10s)`BM0eb=IfSC;,CqY_JupNuhPhp:cgraUG^>rU*qH=#4r]A&)m=QAF&$O]A
E;(I5H88gOa`PRD#lNGF`S&K6\qsm`Qjr[F+6G5s\U;*r6^(8\cl%cH/mAOZI6u9226M?nL_
FUFTj+]AIahg,&9_"o,<#CMpg6HQ,BSs-F>"m==<to_:,XhC":=n2OHKcFWl%u'lZ2O"mSisk
>X2aLX_X:AZ1hp2LZ4g_a!CI!A$J_)sE48(mtg7ll]A0%s%^86AjN[4dT1JT#:,_%j2F]AjXdU
Ykm&21Ge]A*_7lDV\t5s8H66d.nq;4uKUpa^^t1]AUdo!7AtA4K&&f)gV%kk>XC'nSM3#MIsW0
'!W]AB-T%AHeVX]A0h"C)'apo6V[R]AY\r)4%0l(6/,n2CYB%L69Z5^L&J$&lW,dGo!SkqFXGQ#
fljlNYN^-86S9D;qZkP-kb1dq5)f&aMY@dXKYgBdQWM@S*3]A[&*X,q)n*B2,erLQj=bEEtVZ
Q\3Sj^3HfHa4=n;9%M]Atf7DY7JR.^b=Q2C>F8OTV]A.#jD0lu91?3#*)@EeDQjPY4'8HQ*q"k
"j;#]AN73<<j8StcP)CM8i5*%LGW=EV_qVN\?HK"G(@&XGQZ;[k>4sR+lfN!'EBcSn?^HB3=g
;=9!ZW3+W.P^q+Y1.Nf_\\A;TJT*24J`$+MHjd71/'rVOV7d?`Bk-m*/+iWQOkdMS_9P4-!6
PQc2iH?8FZ;Nq*'.;dL,4`rQZ\(j-t.I=curQkCV=j>Zh[j(8kW-<YH8HrPjV[%.>,m'3-Rt
\[_&"/(B[Jl9(-e%hP!#dHlNM$%=<aTln>&%R\q5>UE$JbbS[aZ:D+\m<+r8#>_XLeB0h58J
#aFVlI+bfJ)`B6?bah=[r:V+@(AO("4Vm!fGj,#=/2sUjs=6RHp[JId%hpa<'Jitl]A>3ce?o
t5rr-imat/cg1M,8I>13.ZhSA$0I-baVYJOqQ91JQ'T13G/Q.^3cO/6)UV(AGrRc=K&u+-oA
NacuYu"l25t)i2Cjk<KTe5Vtqa69H?26?3`t/iY_V2Qi9h74[L-!]ArLn\jY5JHI=Ef%4$qmZ
>Nc[^AoYi9m]A#.C\ooMLTJe![Vujr1.G(I><Na3_e[k,TEbOe^>*Yc#;N7mD"d8(3C_P\sC%
mZd6MBoHdT('P-MnF&eP]AYk(JFUN]A(R!M`QHZ%Q]AGQ..S$m[fqlni4^58&W;tbVoEW>"q<H=
NT#1/CKU2c,Wdc2@lH5"2J=,Jin!.15)M/FBDBh,Q@9n`;UMj/(:*-pR:-1Emo8&7pnNP?f`
;;fh@g.&7d2:9l!E%Z2+Si-QrDei(GJ>4/b)Z6rR)^li$,fgK>i)"MmDUh:P@m(nk)8KOO(^
U6:0!NcasX4a5<)TebY$=o3C\66faR<lWDMbbhrLZ'lY\#6>eO@`r,C\L_-jbuE><]AdQ""_H
s/`)MadXcp_tl^*a1de:1ZK8dM(3tD?)'+@1Ff6C#Ll]AIY4gZ29V$Et@k"nZ%%Ld7CmV5O:(
5P+.iPtHCkY%)W3u*eDTHkQphml:$R]A%^BD_%o93]A]A]AJKHso6eO3a"2L>"!<WM6+[CYMlE5H
0Zo+BC#9V70$Y)QG!q+4e2U\h3IeoU9It(/P9IeA2kadK[Gum91!YH+rba1,^N7kmXQ]A,EA)
/KOi64iL2^OUe72-!-ngdYu'jeGmZ3$BbX#f9[24tTM$(AN[&`S_aR<sS`l;=1>n6XZ8<Q?Z
;FheD&8kndW(oA1k@Mk7FG1W'gO9t<W$GMO7`2HVEjmN1@t=7#XCMJGS,Oa/q!2kZq"dYdJ'
[L]A`a8hT,iG_;_h7'?8.U[+_\^ETG/=LuMLVk:\-LV>%<NA$kcq1-Zd_<[r)>%ZB/GceMRB^
9iWFk:#mYPbnVo$!P/Vt0)3qf!.rjp&X#+D.51[`,Q;1V3]A:V1VX$qG"lrJJPbGAr&PoShTD
ma(;8sNCfBU\n,H=7Q&]A)\hQhDLE=uSQ1^BDA1$XU'g04\Ri[PD'l1=K=a4+S,S<[,D$JcN!
t-M;*VqX\#]AL*:\QZSo^n93^QflJ538H=[A#?h=.dG&a-NEd!D:0nV`SS[n="@'72<tsi`!J
;r(h="$A[O-3hKm+Sa.*SO<ba/ZPf6T>G1s@d6!!M-jZa.cg[*^;Sm+_BLNLen.lRJc%N!]A(
G^k(UD,``Tj.4,dAHd^.0()D+U%(UDE!'1?dW<2+23>1Wb&Daar36AW;gI#0[hPgEG"(S49a
56>\b\Yd!/IK)19PG$E>u2PJXE@-T'cQ"JChA231i+'e;?/3/%g))WQI;u'a>%SC/&3qj@KP
+;\Vc+na\W?]Ac>H28=sq-CJ<l5*(C/.T?'^TDQeG/Be8lGXgE1\%7"b'5$PXnkTdeA)q!=Pp
4JWfk11MsG.W6k3:#M<;/$#_Snt(_Jp5SBY#<jRj1V#tZ]A>^RZ3##)XfKLb*d]Ah-h<A6u4Ji
A;&DWu^ThmZ2ipZFo-okSV+`YK6o6;lueZdLDWVAAScb,m$f:K9M*1]A%ZI%2M/<%rt+NrZ?E
6\&_%qf\`M9T1/E!ojEJApB!$0AFoq-tK]Ah.,,*9\k6]A<F$ct+s(Y_(YI\B0&-u+uRLdFBZT
IZg[Q-$#0N(8_Tf_"N.Tqub1W&MDh8$ZBQB$<3;+iq9oA]A]AR#83jn+5cE8\i=f03i#h_hiKj
=5MO?5F.KitL3Hs.5%*B80+]AQ@&93MslZ!a2>F:XL\d3o"Q6HPT5BWEVVI^;bhXN6%0/RSYa
V$[jWnM)dFI4$E1$qud#l6J6'+>(Q%f5R[[m=D!BPJ)8#7GM!2Bun9EbO/#C'%n5<uDCcOn^
hcdB_,nHe2"*aZh0N@DKUjV*nnE&nhtSC@<Q"9t#YpA+g^:O/@pke94>kSgT\q92Z;mVE("D
i?U_M2&*kKb)_(He[!hmja+1"ZjmGm.kS;(1!QTYj!JfpdQOUh$54cB:@&^fG3LZ4%K'B`".
GamqdA9'gWg7.f<.+h.JgW3d17=Z_N=5m2@R2CO7c),3<&,5ZdM%tE73.PerR5^`FBjiAs=2
.L2mP+4$Jh&;qB=RbG>'39;Zkr<oE>m*6d29SP;MU6V1&B;T1LCR@1ra/%Ygt$;%9*_)qn=1
l&EOXbs.g,7@i%&Ed4G7smS/?f@^&#M_SP:]AhVYNt+=<8Q7l0EEY-Q/K8jgQ("O;GZ`TlaZ;
A?,k0A&U+LUJZ:f.UjXo?rT,[*`Eu54R]Aga>L9@!d,8^Gt%&b#h`&DQ1=10m59J1I\3rk_Bt
57-*ME,XC6Oas+K.$hZ.`E>Do,>^$_mVV]A^e<<;4&mEb?Ue'kkFpa]Ab=rSGGhDFD@et\H;q+
eE[Y*<U4p[[K!;Na\P!mtmnRZUZfXIN4&c)Xe>4#`_2cn-7F,:YQL3X'YW->!iH607cP^6:G
?kj]A4I;X:0*(/n8F<;-gG?'k+1:2IDQi[h3(;;G=Z^3rL^q<Hc?#"3*#n<>;:d0>@.:t:J%Z
bR:)AtXDp4Y3\AH-Yg!UHq2UN%.+Sj*PZ<IMg8jB:oqB&j$J-[<F!s2^P(&X_CC4.?mZC]Au)
ZTCA&j<V7G>-mp]AjG^q%F![SUep'I#-Zh2R]A^%@(#BECSKCacU>0S]AsGe=s8.#_g^ERI+dNr
b.qiO#-c#9/Sd=eBjpLI]ArU<e]Aqb2Q^i2]Ajp+V"W@9Sq[.?0kF:d+`eV709/,\"gR&##OE'c
2c%(H/[benu7M)CVoD5sp-S6*o<&Z$>e"-3`V@m0(Q%C%u]AQB@5,+B3^+oD5-Ka!^I4\hM8m
LAPD"A0d@qUN4h61W/\XdD%BiFcZ+nD2^FpNL2YINAMTrXn+E%^bM6fm-ai:)%^G%%8J"h(4
]A9Qb5>W&+IpAU^<U.tLQP^NcnTcK@kZV.NY8A8=le!6>4`ul+0@T$Q#G<A=jnh1oXZOW;CJ4
.RQ==0*a^/oNO<iC5/X&@sGLG!Val(dNVR3-Nm&5?]AY->`>2>b*[:+^I3\IC\P'BrFI5o+q'
km^:=5LMB*,#.,dAlTlL;AIr!kY=%9"b"k*p5\859i!ItT4#ReNsr@l:CfUHS2I+AcWPuI^m
g!`cu'\:Br#,tYC0dhF)J06U.SUcVnF_/B5?M?osq2@)e!f)70QuDn9n;&RR"R]AC!83BZ<iY
?',iuN`10cqHD@m>TLh*-MK*bV(_M$5BmM'Okgrpr[<AAPW9C-p/a2\Oe;<4"HtqD$ARP=hi
TnO>@butn%j$(L<D;4G)Dk;:CF)'O9D=rUQSh'/%i=nrU<5W+UbKVb2r>T+C,a)>F&>m/Vo\
u%l7Ir[m]AipM8h!,i#f<I@?2"]A/bZgMt7s$q_1!$*@U?38gjWrKJQB#7F3]A$,Pl+c.9R\-F/
>"o9Gr$?Vf/[^;lk7^V$BfLX5'Fc'FQIpo>):[/Cftor7O6+B^WFCj_fI=I'Xh$)SRA+#7nM
AO+f@f<67]A3lVi3Y,)bBJ+EXk\s4d9$KUFCWhrrU@2RLf!tF"M7T?)cbh&*=k-`;7_hHXL^K
"l;W)VA":NBkM<Od'VO/l]Aje%JGLU>ZX-ffr:%j:("SJ,%g&]Aq)EMj&t?reN:f4R"qnm4nel
.+8ar0E<]AC'4,B\$Gn6X>&;0F%kanHa_5'X0MS$:YLWhf:+5<S/3[W-ToPT>\PKZ)q\,)/QO
SAhYM>"G"crb7>0k7[n&SJ()^jE!$`8#ZiKjCo@15b5Ctc[>^-%'j$E9AB92FU:4NY1SB-]AJ
NXQ1M@-Rr?61L;hVTsjni=:[:\_VlupS2WqJ@$pC_HoZZG:c!.f50uraZG<b,oU>.0+qc*%e
4k/dKV]AC.iSianJctDQ?skY]Af?pB?tYP200BIY<-PpfO.`5il$Oe:iX*KILj@r'T;*KPel1(
;Z7bH7<F*og(t@+]A^nmbd?fJYB;ePoYG]AQ!8FPEpt_`&g0IL.l]A:.c_<*<8,"Q?$f?MNQd:p
!BmjVd%ufhSX&&mP1`R[lHESMSd;J`tM%)6PX1m$H-Y:VCM;lL`CA$ZJb4(-hmU;&`1+@<*H
2aVq,/]ApR/OY]Ae0+o_HM<5g!MnL4%m^X%]A?!1]A"7Y5(NsV+YSR0s(%1*'AYA6,1Prm=X,7,T
PEX7Z*59%cnUq0Vh5#*eatdC*<.r-P(f(Y*@H*j)%=#.jCYg5YT8c:BQG]A+M#$u;F%'qKtgj
4VH?`q62W$3<D6L?bPfsgB4^%\CV3,L2aG/&\ShHXZ7<4sAIW9m'1P-S^VOP%Peo_q*0N70<
\,Nh9RJ60OQK]A#h7f_IA#LC+fBA\cL"cV;C3`DAIJ[)BD4a7BVt_XgDI>1nK-;>!NPi!d4A9
[0mc6<Q%nU_EQ..1,_r;RU+f@Y[B>2R#'H8#BH<S?b(<e[kFf(Q/e]AklbjW`ADrqqq5%eL>;
*=hM&p[O8IDTdkUN@h1urCZGH5J`CkUQps_J-ZG+5uaU*SOU*2om@a*SB./EahCPDaSF=_@3
95'$5;`6<$"/94F1-PSO/pj(&;`R!55dro5b8i%N\B4<ugSZt8Rg4,@0[GET6B=2Wdg8KX=L
(pT?!rfi9XV9t`67`]A,k7$Q'!g`HW;3AnQW4#(Rh'mAaOr88\8:nnPeIii"GRBe-J5(&F*4=
AQ\<<R"Eq8d!^k"/mPP;Jfk<A[qY[9Z+L:qLUG\<VE9q.=2r._..?YbhZ<+]AdDXaV_35m@f1
o%R2pVS\12Cp*MnX]A!p8M?9bm7aNJI=fGVH2%uopiq/cI#VRZhC5^bj=k]AD9>ouR:@dtl<lj
LACm)X-V7B[2Nf!>_MMd9n8:0kAY6@3S:h&RZmseB:<)$F36+H]A7R>E@FQ'7Us):NsQGWN!I
#2ifVSWSliRS=;M##u`%qZZ2lD[fuJJ,?cckZE]A#;!`jKkci,R`6bM>pM0;I>\CI4J)pTHeO
t8']AEbti48+hI`0lgIRi2$)7'JgpKHWG.S>7dB/,$4-I\BKlL'tPf[>F[d6"eMe%5")Gm<//
QBdT$,J=qM\F\_SD)h'o!pta,.9-CF]A"j#`*[t/Z[eg=+_Q]A,:J2VNm)Wk.k+P<C?a`+e\;d
eI5fP8`\#G1JQDc&<4IR_ul6jW+lG9Z29:%hUofGd+&C*8Za<5M:_!DY;9W/1&I%*KtgjNtg
S0n4V%6m,qGP;,8RCZH^#,:5bToj6(60WZ3-t)A(HVWoNep0ij>;?W0*.7"C1V*/N(*VO.!W
09]A$jXRj&:hpn//V^9>8r6n@MTM.8Ch@n$"cn[>`>"V7/q%ac/i8%)V=QUcYf]A%LcI)=EB6M
,AP2eRuW=!j?u_'b7%[mlVfls.athbA4$G8Y3TDuq36X($;'JD43QrEW!lN;.8O'u"Nhhckd
Z65X7n)KCY`WtpTe@A,@VNnuVjrm$iqN685U+0k!gJf"n'"0e-u'8lu+VW5_-V*j0p*V]A+!+
qFI)=h`GWa^1CCWf*U>R4o65]A%fk2_0O*2i<@m@k\KCuN4Ri_e^u<23WF++V9(e;Gq4lE9RA
^^a]AXRI"=$.1$#Z"Y@P)Dt9'7,F)]A##T=!JbS]Ab/b$6tJ^aRJ/'TrN[pu(-Yj#@]AH/4=cnT2
Y0pJDJqunP@g.Wj82aguN-0E,9IChm!!uV;A6_%83/><d1mb^\%QEWs>!itS'nC(!i9&2mVc
W;^<G&+?dCC1jN`<BD^G%:>Up`@%S12[KB?i*X%F2N]AiSAOfAcEUI4HpBCe>V:!p.Es7/J,`
oTq`R2+sZk+fG.AE03;)KVt947&9&:doerD;bLI#E@J7N'cPCg07P]Ar<Tq98KEf(OXe_3/r@
6mB5<8&4qRV[*o-Gp_gK'9qJKt2iLj_0N#/*WWRRTLTAk.8S@4-i)-ATRm!:=B,?jrnHl<R?
jD4V2(75[YklpbZOD/.A[]A@g8nphFK2OgC1lnD9-"L:?K.>/A53n8nT4WDL!$SMD=OY"uYN^
TUrQZ:iNRtN'!f4l.;g>c8e[*.LbC4TlK,E\4jHCa[o%QfJ!_Wgc[(O4JH.8[mf#&=T$'T%D
B0FiX5Gr`'H1\F,5uW4]A,c<$IgA<Yr%!f\P>-(A9jg>@,-^R5$0*2g1OUj?L&<*l-R$.!ZC^
%[%@D\4Z?,%D3/5>:QVL-N?P*:@!g-tho-jYeAF!"7&E)DX@?FEh6l"@Z89hkA9=SercH2Jq
"k!=jrqHB<Q.Mf%7>I>PXobu/TPUW!U*F^!B(Z\)Ni?I2_7EFU\77-"?UN;9'p4[4e>;TVg@
\Xb2knbmd6h6H/6D45CN)d'F5&t_`NK\-YIJUVa-LuDCQmFT?gedMc&7;M8Gh^$%F-M!)i_3
f=MlF*HauS$GR[4Tm!Iu!RF\VSTjcb4BYu)W)Zu>:Yt6de'@"!`HD10'0WOfkGA[)hZ+EQoB
)^PZOTZl=p/Vc_?`]A9fEbO0#EW3-XY[I<2-%B?JN`$p$?RMADR>$nf8K79^;"m1L=]A*^;A?D
XQeS5P2>0LMg2d2sDB8$qbcbd(,$LLZYZR(F$`!=P4#S5M/I[/f:h*5J;Uogf4Uk5gFs$_*@
2dS/.tG2GP@(U^'jcac$ilQEJep.FO'GQ2m$H9@+YJOA:R"D93!GJ#)05$0qjN>;lKf(KCK@
dDJa\Ng8"V=8Y#3H0G0F9f8,;*pZcSXOmD"#A'^[&FDP\uB!X+B2IKF@N5p7#mR5ab2G4_[q
Lc;:L8Z;>G*H)aR"pM>nMTRK@C7igZ6EMUuP]AG+tCpKHl@HEC%N![SDV&ONN1nLojS]AG6!.6
Tb#hQ-?IC:r1-Pp"A6&X2j^kT+XSER+K,LLb'/A6i:C<)LocP\+g)pBdr__-M3rhDZK0=J!U
?3t$Wjl3=El'gH*U\GP*_Pj;''(i@t?VgUY$fQHEn"=O+dKaV$&4RdU&0M9h9-K=ClV90IoQ
<WlqB_Cd.d`0aub"tXO.kBCa7Q1<2Y)Y._B9J&H&g\6=+'^Xo<?A<]A`D(PA%ZS-%fi#HV4K/
Hk\N=-q@[t%C)npODc0BE<>,6u,6NYrh*9Sa8\m)!Cq<j!kW?r*NEDV&/Qf_-5dp*kfmdE`V
eCZVE<0\9V[hD98=Q?L&?oAqU)\:q3aqCEcZUPLhTdZ9[YR@NI^RTH@=GRhP-A$%9X155GWj
dAPN31E8I^sMH/g+SSgk&Q`jH>9&hMDa?MJC&kLVGn8$=S8k(5H-ZaE3t6dkPd3$1_AEo[aQ
fO#-d26%5gsD`=FP"\"b9%Ne=JBC*O]A]AMbU,BK7)Vl1@$_CoBG+CENd<T,iXGT7N3/YH-='-
b]AiFE#E@`g@Q7dP?oSEc:tsdO_%E;l8`9.QsuoCeQRK]AV[E23PGIRJ$;0\mf#9DF[J]A?`[=R
RY2f<LYDU&u+VkE?ENf)o.\BeG&XkZ2_U5mO>cEn)n*<X]A/5"TfUG%`;&Mh\Ba>18ae@s4Y+
['a]AVA8-fDi1%C%V;8)`HgU!3MW=!`N6^X[BQdj.E/j=Z]A5qshnJ]AN3LopW?MV!g(O-6I\[h
3kSWQ1[;?`2o*`n\1\(,;<=M9pCRl=ap:H7sd3Ya-KDfgnak*_>g6/O6R6Z0sEX1?6NGj*'K
LUUMG8?VqX:NVnb$:"aD)L,=[rcJ=PQ13<3m0gQ#C)OD#6E!bEjT.fbT[[J)$b)l>9<c-B5B
oDrJ@jdpFm,l*3G@qq1Ba>M2o'0a94n9`k$L1c1!SXL(!ZQjbD2Y,&2j)JTi08kLiE["?$eu
I<efTf>50uil2pP?Q:8_*he-9M!k6;FH!)%*ncKO@N9;>)Lj720b.7Qcf%8MfG*Q71g)j%r^
#NGI-W2`=5BON9/b^kE.)m,:m_'`FBMfb%E[lR'D;b7jp_Yq-M1&JM3@rpe9@j(Fs#8[eO'8
.eh,8'#\D9HNp#Vg%r<<9`26AO%X,0lWKr/t8OVY+:_NP1bnLDETpA$a6!gd:m8&M.hl6%NO
.7\$A]A\G[[-0-bNrLtC#2bDc!6H,)$Z@#+@^,MT.)L"XOIWa-dr)MaH1p11L8OToVZ@II_=\
?)Lb-`2L$9iFTTBb9/>;Z&BL-geqW96k\T(l]A@iKVb!fRafUV"G^5Ekf:_UE980Np^#7KZ?J
9aAf*Krn;"WnVl\1;`p0DeOnJdm)9&7Q#_-UUBF<MoR5b#+l"!&dNin]AbH[6[$Em_Y10=p0H
m]AbL4m.p=\mI'o9aWQI0Oe!6S#_(fF'1W#^=faZQ`_V4H=U8@amk%#u([AnO/,*U_!madKXn
&KOdPdffK*PdQ\76:6Xmq?tGGStHX<1;Y:eil):O,sGMh=ja8.G#YW>`]AdO#('dDXkG'(pH)
b[,"d'[5GMF,^//7@/S5BZ'E":UP*4o%9En-G)KZW='uSu\O[,Nk<8pL&AM]AeXlN=t"k*=/%
\G?@[od=$g5BYgX6_s$,WXT29P\@lSMRV_:S4TYeYc1:Q*g2S+bL!H"j![l'R5a0X:Nh%4pX
(CO`p`11YaHgef^t#):&mFC7^7gkk.]AAN"Ame\<Z!_#O+&mC/n)RUM8*R;L3/Pbpo0H1)pZ'
Cp6[?B;j]A0]A.uM?r$Ho2<_leG]A_CFR5g>hA#p-P9IUI4(DTDu1Y+NicW/\s]Ak.$Hq4Yo\]Ab/
r\G->#n6"k3@LdVJsNTfa?ZeVEhaH!C3+`f]Am(_a^^hZ!NIN916UX!j5KV'dAOYj\4=R[#4-
`Jh6Of]A*W.RVM/b5l]A.4"k@1\sI4PI51n'cc'gl\BaFJ`,OO5(D&+fB3W%%*:%DKNdN^&?3`
@f"B`Yp(F,1,ju8>Jo*\]AHX$33\nL4D[=<p"a\)"&4Hp]A"CuNCue&h^>\oJT>R!pfPYdk@.3
)rjXRk?&pA/R\99JHWFVS@4mBA==IHaqn*SkB>US%-884@[8q47oaK#V9)PqiT]A2O,[0C)&B
Ig&j&h<_Tm*_(`;Z1$R-cPp.Q.Wh,$UHjAtC@J0c;0#r`b'uJobs"Li^38_>ou^*%08B<[#]A
0s4+a\=?[Rgdb93*CfRlO3AOFthEiu&UQltYuYCH[m[]A<7o*rVLJ&nS4<D13*(E(Y3nS<Q^<
_JQgU<ac5=R<El/V+/l0-$0oL75$_.BpVkNG6c.F4QDM]AiDgtua96uA^["=U3@V(nk61%9Ai
6:*DL/X,b[WVa22kiKBoDg=7E8VeImqIotAX.n%8l3/&cbdI(l4`eZE:H"ia`Rt@m3AcH#(E
&bJHnOXFSZA,kLes%/Muo[o?V3rqkRl<1rT!T5qWO`3D:eVd9S^hN':H7a52'R>Z8!*lGq5p
f1XlLiY\Y6nJ0+1(+HBL5m`.7'BrcfA\+$tkUjcX*JI$Lru%TN*_V!m-]Ag4qUA#o[K/IL_,h
FQH[.(T8MEO39F>JP+Mc1`;a3qE)Pj3[]A+8f$j5[.T>ZYKh7@Pc(3AY#(M^*`i:O0Y7>U^_W
o>L5;ueRsB/;<Jrn&X9Eo><a=38s_.jC]ATc+\*sS<]AtZ[pVX6-"As%B-cCp<I@G]AGXBLFb?n
?-g5!/45lZbJ'T)/**,(cc]A-=Bt_+kBgpb0p192nr22H`0JW5_0\.cZZTZIVBH$VGK8<bE^R
l;$E,XsR5e'O<_\R1G\#A(MG1*\PkTP#Nu/>,86akFQ'AWa+_Rull,7l36m9k/Q<I="-?Y]Ao
LT>iFT2R;C;TqiE<S@ND:U--(C4^d^qe[u8.#rrGlm.$ZUl.0t"p'Je^j&JWl*[1&Q=kbqS0
)ZYAX`J;I4&_X3:jYC",SCNCD%iUC@B\SIu6^TPe'&"OKrKW+nM-84jG]AP1$MV7B6m.cAtrs
C!1]AMso[@:IVbdihOOf>"I@J\A1-PkKlB+7E^A>W8pl!Hj;1ZPk2LS1gBnh?sM-?MqG?iiOf
u_qC5F"jQeQX%Y\+<N0$`)nPh"9MPedOr;"282@iBK&Zp'BH&f_d+-6bB0Y9:2`mq-U]Ahdgn
`ojhE$WCSX%-r_dp/O?3C<Y,?5YPWnoM:6`pL34V*R+ur6_ZY`)jqE2]A]A7V/6ZBW8<l(4k5$
L&Bb`5Au\$J!54b;K"Xb5oB/M0JFXDpHktn&BOh]A2E#S'pT@c^AS]A]AV2mCrBb5ESR2$9drT1
MB8rGM<Fqk+.i!rt%S\cOp`N8p[IeT4OF2(VN8_bWbS?Q.JH$<04Ls$Tpnh3t@mHKk)3mm9A
9a7!+]As#i,ECtpoY8Gc_NOdK&mq[!4pD.q<Q=qprqn`[jc"U$O7A6NF\a_GPMn'Uo>"0EOoZ
XNh/A;0oW91q:S^\C9RR[P/,?=iQK@+s/sT7-o6k!IuB.%Q\dM*oIh/Xq46ko8q2j>Li?QJm
B2Z]AU(d;11A%jk<VK<2O!#I@q9=*cAkWrW'U,I6dIg[5E_G4861"*m%^fnb7!5Ie3AJ23PGD
OaJku,0T/]A=/,#b%*_"$s1V,%7jW0QQOfqmbE%$kp&OA?NdTaqM>Qi;Bk9IZ,Eb"`0dT'`;'
tIO^T6-3Gk^`PL1HmZ0`qU9s$Q=ArV5!3mi[0RIph64:BKn?"r1MC*90(n&F>BJpU9O4J%oa
8C$,K:\rb*/FS:+u\aNGSC!%bpNVpLTBj(GkmtQ4;BeWk9i9WbN*u3Wsn6&$7IA:FVA[h(%h
4NSm^\7P\c[JC*;g-8f=oMR`-h;c]ApNM+7H+QBArmQ?fr5AV0TCj/<?LQGnZW8[;:IqWnp*V
LM0"gtEpa8lkIb$/t3Nj]APo#s<D8uU$uoqUK=XPdYN\t<mS8G/:*+_OYgdE0uN=PA-4Z#lfc
]AIf_U%^]AO<RVU]AZnsf-lG#WtA^%Y!G'qk;h#8Fln7'Xc0f:s`T!r`\-$:Crpm_<ut,s?#957
AW<<H;,PV;hMM68P=lN*Eg(7oV]A1*6H`r%?ph/lF\&2C9mC0;76EEGbh<e!=,Io=UeSB?N!l
.lQ-o/Da8&B\@LTP\X^XR`snD#.s#gu<uo>mL>sk>]A4A@Oq5X*?.L/R%%DU"hd8-?@cS.X?I
`5X#m-XaYNH`IVd05_%("LX$p4)l,DoW)!V#umhZ(Qbe:AB;0[lVacER*]A6hu>Qf++&lW_mb
j:OtGcle)*bo65Lq_\',VQY:BIJ8:P>/0s9nL]AZ_/9YF@+]ANVC-[Dnq'F-KRH&>.7%0>5eKa
N"p+[pKH!-]AM*hR<ZZpX",W7qs.pUV`R%k2&VD]AII(LQDU&_KB429WBZnKZ'nC5MT)o[<[Fk
LW:J$G+C*M1Sm+)&:We_r3'M<!Rcc!L;)+SO^R?&5@)\tX7aiW[j#f@7l+%DBHp0.T[Jrn!n
As*a6jEACK"D+n<9IIh,]A.U9AQrr1!TYHkB0me)\Xo_75Up?b3@^_i6TYeU,9^8MF&-&7iJ\
,SgZs.Kb$k1de9H''hFhZ_EVqs?&7mX)S/?T220]AY>Po^mTQDc/d*Yn5gd)qe".TKb)<'A`!
G'TFn<&73?9/XLm`k&M"YG=S7Bu7j=58M?En7L<FLEO8X/aa%[TMn?%lY0q^<<cKfuUrQ&?K
S#6'oTY-<JhcpVYMC^B)/=M.(q*t4J]Ar\]Ae;=/;7amQ^]A2b-HB-@A8>\'[J%!G#p%Irj?A2^
kJ,>T)^;g9J<Zs5$&]A:0>Ye@J);AQeY[^rj;T-P!aftd31H'SNlP.Im/7sVW<j%YQ"V~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="685" height="265"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report1"/>
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
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="108" width="685" height="265"/>
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
<border style="0" color="-14669005" borderRadius="0" type="0" borderStyle="0"/>
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
<![CDATA[515500,515500,864000,1440000,864000,1006453,515500,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[515500,1440000,2880000,515500,1440000,2880000,515500,1440000,3313932,515500,1440000,3289385,515500,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="12" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="12" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" rs="3" s="2">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="5" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="true" position="0" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="240" foreground="-1954"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="false"/>
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
<HtmlLabel customText="function(){ return &apos;+&apos;+this.value;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<OColor colvalue="-1"/>
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
<newLineColor lineColor=""/>
<AxisPosition value="1"/>
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
<newLineColor lineColor=""/>
<AxisPosition value="5"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="0.0" categoryIntervalPercent="0.0" fixedWidth="true" columnWidth="0" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="result1" valueName="result1" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value=""/>
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
<attr moreLabel="true" autoTooltip="false"/>
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
<CellGUIAttr showAsHTML="true"/>
<Expand/>
</C>
<C c="3" r="2" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2" rs="3" s="2">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="8" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="true" position="0" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="240" foreground="-1954"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="false"/>
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
<HtmlLabel customText="function(){ return &apos;+&apos;+this.value;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<OColor colvalue="-1"/>
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
<newLineColor lineColor=""/>
<AxisPosition value="1"/>
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
<newLineColor lineColor=""/>
<AxisPosition value="5"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="0.0" categoryIntervalPercent="0.0" fixedWidth="true" columnWidth="0" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="result2" valueName="result2" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value=""/>
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
<attr moreLabel="true" autoTooltip="false"/>
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
<CellGUIAttr showAsHTML="true"/>
<Expand/>
</C>
<C c="6" r="2" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="2" rs="3" s="2">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="11" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="true" position="0" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="240" foreground="-1954"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="false"/>
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
<HtmlLabel customText="function(){ return &apos;+&apos;+this.value;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<OColor colvalue="-1"/>
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
<newLineColor lineColor=""/>
<AxisPosition value="1"/>
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
<newLineColor lineColor=""/>
<AxisPosition value="5"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="0.0" categoryIntervalPercent="0.0" fixedWidth="true" columnWidth="0" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="result3" valueName="result3" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value=""/>
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
<attr moreLabel="true" autoTooltip="false"/>
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
<CellGUIAttr showAsHTML="true"/>
<Expand/>
</C>
<C c="9" r="2" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="2" rs="3" s="2">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="14" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="true" position="0" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="240" foreground="-1954"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="false"/>
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
<HtmlLabel customText="function(){ return &apos;+&apos;+this.value;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<OColor colvalue="-1"/>
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
<newLineColor lineColor=""/>
<AxisPosition value="1"/>
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
<newLineColor lineColor=""/>
<AxisPosition value="5"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="0.0" categoryIntervalPercent="0.0" fixedWidth="true" columnWidth="0" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="result4" valueName="result4" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value=""/>
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
<attr moreLabel="true" autoTooltip="false"/>
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
<CellGUIAttr showAsHTML="true"/>
<Expand/>
</C>
<C c="12" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="4">
<O t="Image">
<IM>
<![CDATA[!HeO&qh\-E7h#eD$31&+%7s)Y;?-[s2?3^W49u,k!!"V-8\4s&"?]A7D5u`*!c'q:<PZ\QJh8
KQF;&#@:6^]ArJoV<lb0$H!V/r7#f#7mP8]A\*8Akp-\ELLES>71V!U_2D]Ag;8g?\l9TALr9nb
USM:8+TI,KB_lNh+Cng,X<IYe4U_>fFn%J"<Jg?HGqRVto<WDA^UGU/:)30cUrG@RFM_P_`4
.^3*T9UX#7nc0h9W4laq^k;@#Es9a?2+53Q^[cRq6!OqA"F$(_a;lCi%'EZj2bU[\,Crtab3
St&'gr$h1Ypl<<;jM\mRNW:F(crYRHM;=V=Q6#&s[U=J;7L^D6@[f*Kpi\aLB`eP&f>!^eWA
*@_<@5@tY1>'A31b1:#9Wjf4NL&[&g0J(b^VP3kFKR-G5aZ*7r<#]AX>or0J#ZuIc._<88Cct
f1IVL>m-W.aZ,g4nlol(9X6jp9jU!u(1N+;d6"!i$*jPi6.?@-Sc%a&(e?qic3%0FUH]A3.0Z
fUJ=<@=B/:C-79M,.tU.B_.^i_*f^t1"P^>:Ol4U=`Z'f8_@2e3aU+8L%"9l3L/map*uNKHJ
Vq6^HtNgS$,7D]AUQU[noNUWh=L>)=p1IlC,]A3sLfEY?aNC&aQ_f-$5Jn]A!Dkc&G>Pnk8C,Dg
G8^h50*fQ^VOl4`FabX93@X&/b6;C)a'rCbrV$WmPpq$3YE[72QG^]AhsDEGSI"K#t\=_^8AE
'XZ7OT:72uHH$kQ+-u&d,'$7`orGC(N@Ium3,m=!FU7dJ68m3O3sZ$$>Qeb0]Ag:t#A!=/h+j
fNS3p*qpYW=Cg20:/k)oE!;G)]A$F["acXP8/?o%@[]A)2l/E1SgJ#l(54Rq6u<d$-)5cII]A1l
lE.ZXO6FC4XrC-+Cd=(">WU[QK7#5WCn>6`[hSSgmF7BE_G^GDMd3f`oE62-2Po%jNiCa,OI
8DAB5DDZi(Jl0g]A?02S>1#U"7ugR3\3]A]AKU1u/m7\^<Gnbn7a?s(aSm"h7*7>Isp^UXe,cu\
T>Bin@H=P8Gdd%5$4V`X&N&*9e.gkHRL#D-\WrX`lSV]AsauDBk'o'E7O7?r>rf"pUCO[?#s#
JZ,=lok$pDkb#Dr,udSV(fe0!"FSA624H*Rdi+RWV8ldXIgNS=R_<kj!coZXj>KK2_OKE[U'
m1?`b>%t[qNrro*UU3NG#g<:docOD2<A=d>_/ufP>@l[!R`aF%-t-bJ4r.@/%/?dB71cK]AV&
Tb&3#)`RN$=BW9o*[m=2jE&pS?_;]Ant>#[se:-<d?D-V'e;B;]AK&e4i!+c">+l%t'l8:QE_G
1bLlN^15?gDALN4J5Q0mT<b4%OV_m1n;p5LFtU9Aght)RtaZ02%MV\r.]A-qPH#1C!4th-$/J
O=7fQ,bK<OHq?jA8un8FdH^$L&8['-.MC*[T(rrHQ?!NaA[q\oM8!!#SZ:.26O@"J~
]]></IM>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3" s="4">
<O t="Image">
<IM>
<![CDATA[!J:N&qh\-E7h#eD$31&+%7s)Y;?-[s8cShk6O3kr!!#+<PIUYn"A26R5u`*!m@6a]A'L<FfT0
"Z]A)iFNE@La;>.>iIK<,b,k,C&]A#lG\YmE'_Ep-n7^UTQh$\#`9#L5uA.Jh(pB=/eLId#VJE
;*eINO@PHnNeSQ"4&pq3jlLi)@n2$8>]A&*I0<HTnL6hI"(8C1i)d%Lm,pY<<u.XWWg6hifgD
R#'#W.7_F'efuf!_*2i^ka!ZOet<5TEH`7')/)7Vu]A,l5dbk*5Srt)<'6p!f(+\+.$:7/./5
>#DTnPGG.$</DaST*kJn$(<7*rFFqTM;jo?g!DMediAh6_Q:oHf]A;:*LBJGr<T;7Ntkk<*jO
hhAp79%#I7i/@<)*,<O[rb_`D+"W/?-DS[CZm2b?iMndS++.uM\Mc:^jnT;,Tdr#6o4uk]AH*
PFP:cQ?#X?l\7QO`1#W!"EKK6$0+/fZ*W8i0c?[Y>#Hi"YL=rm[rR)Y1??b,$jO:_)<@SA3#
]AomdL9]AE4sE5fET&JU5?B8iFHK&b@pl8dO^5"9)[3Kpb1O%otqqdVr5XStR`u4BMV1f9j32(
h_<%(!2*I53aA6XX]A?u^GSi%<_t4`(+F`;)k]A1KEAg>t4kFGgAB<k92Gc"HjRsZTr#rp]AL>(
.;Allq<5Gu;!AfCiN9AI7&%b.MM8H,SW:p"DS'=`IG$GWe@DQ.P(j7XE@j=&\JPInWt;bQ%b
mkkC9NgpPtHdig]A[97`YjZ8mc.J/>-\\2\f>e4Rbi"HPo9hpgC\c!?S29&887[U:a!N6%,BO
?kWQ>R@NplM:s0Ra-9?3n(sR0c?c6Iaue]A\G]A3\oQIU6@l21;1LSE9luC8'8^JhbFKGb!(;=
E;^6'WOqn4e9++`aQ_kA#$$6!j)DKZ6R?T3/R+VjQ2f!Z]Ac#UGqWgYPSf["%6nVG3&bW5\53
jSo`&)9snVGQS:E-i*0o#r<Be&'\c,s/KSaC>B4MbX@eEt]A1'Acn8(loHa1K=SO7K^+=*FFk
N,G$$W,=JPGj@/&<)F:G9"kl"Fl+N^mL6eBsV"B.*sS]A([\Y0F(ieY-T7YmkD_%#fsUL6#o<
C'RWo%76c]Am&%\CV4DcpGnX>0_r5C]A-f`)K2<'Yqj8m"83bfFp6O27<l2gS2BO<(PDF'\U92
!MpWhdc'k4Iqt),LXn*<U@Dou7]AEnedbC-b2'D@Md)M,9+'j6/tXhEnQH:Y>tFk.)eJZZQ%A
q&._;1)jG^mRbLqNJ9'iX]A.r,6dR=&,>h[J@9W&l1dLN5YQM3NFr$J2>?9G#Tk!7%8#J<,s?
H']Ar(q&:4++o:dK9QC2mb0_/#J.#)/[r-C(K:lmo=pjVQ<G2i:Wq?f5APe\$FSP0+1b3EOh*
EtgEeJgqj#MHFgFN"egVnYI434`b2[`e>:hcDn9t\dS<(G0:eO*5gJkof6m[Ir:dn%4z8OZB
BY!QNJ~
]]></IM>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="3" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="3" s="4">
<O t="Image">
<IM>
<![CDATA[!UU.*pPD^A7h#eD$31&+%7s)Y;?-[s5QCca5R7Po!!'$5ES^Zq#dd/k5u`*!j]A_tO'3,mhhL
!<"$^Pc`@,8<6h(]A=i3f7cjT]A1ofF[ee6'mmd*g#UE\$\Ri25o`,n"@Zr;b:!^Xm+^=3*S_c
^<Wl8BIc&m*,e;_V,hZuRhoqRsc+`q%mskB7rj"\5'\QXo,rUVDZBq7hQ^F4a:IMOX$t2kj#
j^f4O:naT%F+0qB_"rHrm_R#.:*hM]A'o1g6i?CQI"!iFgGd,=G,RuSmTSu0J[AQ(Z)K23Vjk
PL&;&0CjB(:t;99;eQ2nGH,/V'gWUI^;JsKQI`qoJ(=N/Zmbt8A6*t5^*d,b)NZEpNI<>RdK
@.0,la#Og4T&:ZY0fKk_4h5=ES)O-6&H(^a"jcUWXf:'Aq_#NbH"$Xc]AYL\=n@)725K6b)9H
gSH>0so)k]A3K6!/0E`TS:T#J3DC`!+$@V>@O6c5muu3Ks\nmmF^)n!2:XK+%ZbCS/o,T=^B<
k$H[o>_t#3_!<C:"EKER4Z&rN,jJ%0YmkTPZU1n:MZZ-FI[fHO,dT6EVBn%LINJ:YujlaR1M
2e9V]AK[_u62aiDI1C%R@sFk"lmoUm3KLEIQZ&5=b,,31>PeYUYl70h!aNF<0"s^<2pu+7Es=
J]A?@XkpHa48P$2=3,I`->gBBXB/.@+h?2()[9J?_.5fB</$$NTK%O0o$&*,C;q;n4K#YNKH[
'Lfl>4ZPX?\Wd#E&k8'3-9_Y.CrGM7nbs+]A:0?1bH.kZ/qh,d^akbb7JX,-?d-P2rP.?*C(P
'_p'%V(a/$obFHL#S/kaPXg>cMpGa?BUq/p`G5oQ\S)0EBo&W\kTnq^,;:.89_AW[kJ#.86q
4BKH2EIRm/b8!@CPFfFmL;j4[U;kDosTj\="Fc.9LAcN+:s6g!XpCr-M#;'??0'"\"$*O?UX
lfa%1/E\MB_E/@]A#,d[5muu*4#oN4!FR_$[U(Q:kIK"`=?LcC6b^BAVVm\a^.G[PiP4kASsg
T6*ba+V4;(/J<?6YPZK-!b*.mnGd5eV,gqn8Dh&<sZ9PP%d0Wcpp*=\&?/%gD6OG(c9@HKJr
E4WnUI="b9L#a(U,dgaKD,2oWqCQ7\Y[7"ar+Oo\KD8`Zs.Z'g6?f*K.pHs9B5rj?ND5Ip:)
HOOV2?O4nf@A+*1Kn1;%hGE\@rtfk;.LW@-I;4/E$>dE;ZqL5^!.KhuQSN_nJ32*Fj6_G[0h
?JGNqHEWZF[pU_u#]AOi>]A_0rIf_YVD@Yg#$#$KmN8i,rgWIU$d;8Ipo_Q?b'1!BNgE%A8jpj
5t7s.VWAjTY*4"*J`+eQ1Q9,J>Vq6a[m%m9m,erf(NU^WgWLl=9-c!#%9^2T5S>YUaVMNBkE
"20E1up[>">,Nbc/TZC'_XWZcG5di:<u^4mDnL5BP#q0K2@K'M`ibS^U;I*TT(Vu8%JJ0MQf
/47%H#;3)J2g+G0IfL&29tW-?Z$\`aclLm"4U1s.4$EZ&gq._M/bhSl-T-FJ/=uphj'd>E3b
Vs6$*RM%k&BW-MCYW->Xe_!;?2fp!Sj_i-nBWDo)!7/ad?t<`b8BCN0^@YQ)Y'[!apanK48P
gf)6KG`qtN^:'+XBLXcmsWiPrW4UPs[e?l>:Ns,#j[tni<!"<fN[b?Nq^E^X,M%+uKWDYdk'
ne/r76!*:/O>Sae="'qnJ(a8kWGljD#g7re)dZ1&cg_]AX>KcKouG6tBMoL`C6%"oS&2mjXtM
/&3[f##bAC4]A6'J&]AY0SD3Uu7@%0dGF".0,ml!0E\7"$m[\`,O2ObLM.2^WTE?-fEQp@u`-n
3jtb@P"IMt7H]AEN'(#u**'t*lK^<cJdps1b/C^p`O'K/@0aFDT=+5oT"n0U._E8cYOX&=RSt
Taj;:T#"BRo9[KSrNaY/#d\QBMX;U(IrJG_IuA;_0,Ne`Gp$XN#pbi@<mu6E84d%A8"gR?XJ
Xnm/D5Wa(RE;I0e\$jn!kWClKDT+l<.5uL$`f!B*behStQ,-2Z^$r<)%Q+1D;4IKSthbN9^n
k*QVCW0sP%<QLgZ9+!RN"e.%qiU.8<#4)d77Fkl"p?KMD:XlUHi[i)To*4(Fte9e!!EHQ!8P
o.:i]AWFrf__NL7E)\qbRBHH3,5UMgA@8+s[6jLCVpJ#p(qsF_?k^is,Q0<TFmR!g=&t/o]Asq
A#lJfHhmaXNRO_[O=m_0ebDSMMg)uSDZqaQGRUY'8[_S44BWH<P*D)o#/F%m7WKMENn_u?!S
!Gj`p*pb=/o/H/-I39WCRc!e*1A#5!VOn+U*B`A5X(SaXi4.lE,h'AMsI(%@(\Wj@m3.9U=l
^c*C,#&X9k=Gh1PBYH9)L?P!51r<e;5F!$WSrf7*2A>T-Sd_fa+K0k%0W%SO^121mZ"+2Ql=
RH@n)\!U$2TR;SHIN=Qo^D?()Bm*.h"$$g&fInrT-+N<RQ@h1*_!8tgQeTqb+Z[,VVHH`Vbb
>,PM<//*DK<#iZkT+jK=M8J-\0=BMqb7J/k\!c@?4)3^Y>r/Z#F]A<u$.h7jNM9JsoXMJBUIb
&f(+#$mm;W4S*5k]ADu=hXi,YB:?O,*N0,IU=5G@,2\S$L.V#K=[%&M%g9N$U2f'$*DN3D)NY
5?X?6Y@;!tY=3-k-HlQOQ3o7SbdQ!=Jr/Xmuo$<F/l36K[U`=Pj,:=mp)MC8=5q^\%r1<NM;
m#;)mTn3X9u9pPV'S77;Caq8gnWqpUm&`/E+*6RY8:(6Iq<<cE*Y#;r*[.I[;q;%"0YBmqFP
hF\D5D(75gL4)#3D.ZhH8al/J3^*dJU3.+K3]A8\?"AU^K:,p@iY3:-:GrAKI="b$2rN4HO6a
\f":\Ba]A.Sk(HS/:D22V*bb\ib'hS2N'@ceEpR)o=.!!#SZ:.26O@"J~
]]></IM>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="3" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="3" s="4">
<O t="Image">
<IM>
<![CDATA[!RCp'qMA$D7h#eD$31&+%7s)Y;?-[s1&q:S5mRYp!!&4>pYGlM"dV_J5u`*!m@.mM,srAsYL
ctH-%\Oh`/h(h40oS6=_g/I*g%1dOB?EZ&OE?oIHQVrDc[L'"A:$;@t1^n=gIh]A[GhpZ+1i]A
58q[b!_t^LmIIE,Qh&5UM^=03qBC99@Wk1cqP*=#CmTW[ueN'O&SZ5mDBe$8C]AZ=[^<KurW#
+I`u5"g@m>IA_tK*N@uG2Vu<4iUS!K=1Z#[6L_?/.Wi_03rj"noKkKl`bG[3<o4FE=G+jRGn
Esm]Au+tXoVnPcN@VF("FbbjUToN)MYrDlauQ29H^ReorS_@,g'06i"+i2gXtK:Vfbf?@djuP
B`$uJ_<4VP9\k_Y2rakLVS4uMAoUa68;GK&,q]Ajr/3r(;*<bb)434pSlVXJ,\H&<!H7%FG*?
m'6@X^#J]ATK<W_'n$nR@-?/0UHV.Mi9D9Ln0O>Wf]A;0"G?]AG4I9AsidX%E\0*_dYgD*S0!h5
^"s2#0+tR8d=s+[^g0_ko/I]ASlrB4i1;cY>?S5pIp=N8KA$WlAQ#/7NZL8]A?uEXo7hf;+P?<
B7/Err6?fA3iRG(A7Uj["=lf3B<(.+U2!gJg&;rb3ml7LC/[,:rUg!&_#mD&.hg!VmV7-kec
WocHd6B0uY.o+f$fgM(\U_>Tb_&*6na4$s-il0e9/7$c1ch9HbSsrd3H6k@k/,@Od!pR1"[Y
1[*Prcj):)gDaeb\ND#Z(^qh?B'rQPY\NbXkuIpT>[WOuX6l<u-K\eAD\Wcpg't,2m?en^?k
`$%iLNcE)M<1=$>1>**\P%QD.*iTh%Koh?W;,[]A>@\DMN/7-_<U"C.=NeYUYQ*B:0I45:oRK
iqEH,35+Br+E4Q6W\YV=5a^2q#=%rqaE]Ah]A?E38r%NW9u`]AbKf.TZ?N6lWF)%i&JR2'Bs+_s
%M1(Ir1saC8d)MCPr#+\u&@nCHK7KnAjSn:+6@)K->LiI_X;F1]AF@9]A2SOaFYa#*a8mLYDZ@
cYq"YH"#8&gb!eKg*op*@aYn#cLUX+Er;Pd[/$bS=iN`A0F=&nSe1(bK2P&;d;7$YX&1W)Ja
DVE9s&GVg@3nj7#31IS2$=+c+&[s%9Cs8t_Z-Ohfceq0-"%X"ZL*P%.":=G1J8;'n`=QKMAN
tS*4frQVGcdd1KPmPoPBFdW4:d76^aGrrcbK.3ddCW_JSi)j79ajb6'_JKBbp]AD]As]AJ7`_VG
B0\D&R[p>.;abp?PB4Q?\8!$8EosAF/;E&^RmjQK&Y9fVQ72=OE#!af4+g+o5_\XW\d4g.`V
)m0lE^YusQ6[4NQ!.r.D&((6ilL8Vb0fX-^!L9_8Z3A.'PHLO;Ra/,7I$0u=U)]A.q`'LWJl=
=u\LB0CjcOIC,8_JII?bI&<484EMcOt'8DRef$<&A;(5^/:oGF[JNr5V.@%he?b@1enGne)p
(d!#VoP2@9a;-5W;ZZFb7ugXO;9A7$I;F]AhU6F+M$_Fd^g*/?*7T]ALu8Ht+Jc:c9'BFRd8/%
9=*-)^KW%CQl_]A$coFfqu4fN3SP"mUOZ(VR%GtKN_K-lXBn7?Q/6+mW"sf-`jml-+L%rpkr[
I=pKmNpJ+06T$CJIn5IqCW"`G!^5Jh5-?^bRYPkO2fF-gc$uH_a_nNW.LcA't+[nH^;VuU*[
\bC$Wp!D3AMQ=oXa;W@'Bde]AXoO8BE"MKcHFo3M"D&j$Y;L05@?TCr7N<dj"%\_#`G#\+[F^
HGL+DnS#.jSt)8J'ON&Vbf30fY:#k`Y.Hq9ql_.JVA,BQr:+'c-JD?+OiL2->E*_Dp=A_=8W
.AE^25.%F.:aMa1?R1`IA(\Z_>bc1o`H;$[X8RMJ3JgqP<V4iRz8OZBBY!QNJ~
]]></IM>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="12" r="3" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="4" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="4" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="4" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="4" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="12" r="4" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="5">
<O>
<![CDATA[参与活动人数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="5">
<O>
<![CDATA[新客人数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="5" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="5" s="5">
<O>
<![CDATA[购买数量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="5" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="5" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="5" s="5">
<O>
<![CDATA[购买金额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="12" r="5" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="12" r="6" s="0">
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
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-15262921"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-14012338"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-14012338"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-15262921"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="4">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-14012338"/>
<Border>
<Top color="-16777216"/>
<Bottom color="-16777216"/>
<Left color="-16777216"/>
<Right color="-16777216"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-263173"/>
<Background name="ColorBackground" color="-14012338"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[gq79)dp':5*,oBg4&&"+7=#UZ:q?fAO<-[`V?Q3^,D7=EflZJLJ92?a/g\V?7!e;Z+uQ_5Ud
.8f%A)b<ZT%clpY6;BpO7?OWlt'BCT]A\<(iui;'5!o8H%q`F)#sZ,d<bH3#QXWO"9;g<4aER
s2g\AhE<%,N:$^b$i:DnMjJ`9_f$$k[)6\X'r*:sP01=LF#-j\`Dqk#roXnil7q,epW/c^3E
K/RKgMouCq]ARm5jZ'CuPc13rr&?=TYBEpj?FEG\o[T?_iq>!SrgoFJK2h!%nn<)l.&(3h*]AV
``KdLs0_Noi/1k8>>;>Zq$[RQ>pA1=i=rJAog"H:5R&I+l?c_jqZpQK6nq>md/@uBi!AQ)1W
/P9Zp!KU*eGZ%eE=QMPVdL2<m22"nO+0g&UO@GrbG`@/`4:0[oqJlH@G^[Q&poJ>=j9\)_qN
3qYlXPD^DTD8$9uEL;XhRUab+[B<*qodkpM7jPIcnm)e^,ZDL<%(R^L5<=olk9#P+ZmSp%!(
!o.gn:37RBo)KHa7I[ocMa.W]AFAJaIE`2Y:><9?YD[.#o;g,"-D^)k:9rp8STnR(1G$=u9uk
JK74D;^,^Z[r-$9C1_9Z`NncAZ#*FZXfu1EQ-B&P9Cci-g:Hn&mFceBZr@o^-,*j?@!&CP(X
fCk:S<TOgDW9r,D+8/sBfiHuoOo!UOL_/_+S91LdG_m<[dE+PM%Pq7=BpKT/IuU<4SKU\a`c
L/D&!q1/+l))d.!IR9ko0VAaq$M@#('rVF[.cr:>Du\J*P'1u2QkJp?d*',G9:CWl"-j'<r8
`rnfHEmFR%PQ`RUr@g['A0L\1#"c%,`@7Z,iH+4f4P:h!RU:]A@p<'YSl-mgsUjJGP.5q5Wuu
<O#<D6KJTfX9*8fe*Nu`IF^$B?N[Khb\?XEtUUh,J<R-k#?u-)8[4n3%QfLtY"Z'D1nO&&;g
F$2\793MZVU$W:l#*V4a;7F\]AAsb/cZkHA-`10VV:V*kD62nOl4%c0Fo!e>?#M#\DFffjP?=
t_/Y6I_W`0<"_U.R!RBiaqFjt[/6Y8jdKPOVcAB53GSYrPpY,QV183k^[XMs<2A7\n%U[[;B
/W(]AO!XhVP>H(h>YDP97N"+2:J<gVZ?/$:jj5GDe2nNBIGt@tUP*1!GPo2T<o`O1sN[>C?R]A
dT_bL#`(P;@P:^*a?!>34^9!qY!KU5,e^=>Y;=c/-G[i5EcmB_XaT=O35F6*MV4N2ajaIM0K
3M(eCth4s(^(iGQRZ&F'<c3I<#8m,uA;m(t%1.J)a5L,_Y4oq%<JP$a+o7l##pDJKlg'+md<
2Ok@T$DAhqVdA"4+4`e0R,GO+BU9=+GsE>_b"0?5i-UD_UR[)CUK7W&]AfaG-\+UJi'ui'[`U
X#$JQrYfk6Q[LHj9L+C1=QWW\+jRs,J,!;.s'fqgb-Np)$<#L0;VlM:8E?JCCT"Kk[>Lficd
m+i3HLg7(*5,nn+I6@Yda#gXJ9ighf@H)'-4+_*lI%YV1/pbOubs]A6Y\(c/fK=8?*F)_549[
\1LYK0PX?S5(b%U"qQ&e>!KM?4Mhr:#5[#>NM/:rNF@7<"s`Ga%8'J>(/LCp2T=b't5%g7m+
MS9Mq>VU)SR^%/@hoLQ*Zq`m[hF(4e!(0([F#K>*lk/>#dRD,7%#Ni)O6*%tY_Vss;_b9?-D
=FAJ#*UN+D!Hakl4^t3%Eo'Yr14UWb;f3=,3l\L]A#hnt?/oPCB5b)?R<>kP,W:)WMW@^\%;l
?0<8:]A-n2K\g9F1%KSGV2r3RsFtr+$J4!:9[k.dDR4Tbi%,Ngm.EjF7$Lp]Ao:DImViGV\9Sd
!k<Da+sA08TWrJEiu^H'.hU*Dj?MJhj*`/fTdCiOm`<+F)ZbSHOjY\?,P<@%ju]An)KIuEpTF
u5DiXniC!:PWfrHElrY(gke-XY1:4&b!CXa.E@QE)t.627'k8DaO2j8YGZkq<nRq*'YLEFId
T2_n/`j9OMtDaa-\G:L,)Eh$XRd"cS=r=dR#)Y2aoID+,)R6??S/5CC:L=cId&[oYos33sN'
-nctZQP9Uc@;#MCo;=!nlb1jA-@KS5igNo,n)`QdCo(`'sNHgVZlMBBIs6-RHj?8"U=m#8lb
*;h&Nm>#j_BWl/CLaRd,ObkNb&TT+5Bk:As:=O6rGW^R#<`#2TpDir2hC4.H#cQu8#3TdeuE
EIQbQ4p(sefg$imN@Kjp@"m/"m70:a6u;5:is+Pqr\&0b5;_RfVja]AglnNjALY(5dF:4u+(]A
I3B7Mmfs\6rJ2J"mP"(\f*JqK3dq=3Ws`fc![7Qi5sNXT"93;[`r@32!nA@)CW+Lg`@Nl\84
!;M"A6Q?^KWs.&O7SRQ*j?:=fX%]ApuXrh&o?cX]AjGhi<!QJ,=*Y(N`tl$b&1h="XCAs,0gFP
l6XOqR]Afbl^&]A>Fh?!"%Qbo1*UQ4UoeD\"^rWKT,#lBH""^G@%%TPg^ALW"h1U/r!%6W"WW$
mWZ*]A++3r_]A]Ar;&Odn&^R(Jcm/_DM`'fk0<'H603i.d(0`_!s8rR"YMPo3<#8^U*F*'`IE*+
s*l+9m#\(g<Z'bT-q<S9[.@.=DqCN@*O.u*!uAgd5BrKYPi<*nP3Rp7B!'\ZPj>5c.h=siM]A
qZufAObpGC6]A4C$AMeQD?fuqX`[R[hB7i5pMF.2LfPDnM"^:_dn7+Cp^+Re@;raY.2\L:KQ?
IhO]As.VJHERkd'Ujl/`S/\l;4Xq0KBnMedfdWW[$sc*]A$d]AcddQ_8?1e?OQ`<1JabpPmPNcE
YCm67M@4dgXhlbcP0rPPXJI_mLSRcT0N<j4,1+EfQFG,J[#a&FbN#7@HKBg5G%[8J*E1T?t=
NWoH-cqPUC9R,9WJ9ZL;k-hl1T]A1HlC8VnZj%8Ah5%k(LM90Q,O7]AXW>a)Vc`L3KWmo@%Ur-
D_l0S&*c&V'=#SP&'G7cqPP$g3ZMoA5Z@g]A5(S.omlQ?WpNL)0MIhLW.8s^ZM?'>iO^h.\ln
/KD>aFeAU-j\]A_[:9Xb_2!O*8#La>$Au#n"-lahN$]A-<T;=TI5ji;6\*B,Fn(PC96u=)YhdF
np@M_FI['kVnW:9!0ul-ag:DMB%8J[n]Ar#M;'&3]A=:Fm^i8Cm$V*ODF[]A_%7,VKbDRR.La-S
$p?8lO=@qa1kt_>-rVIl'HN+h2aWM+JBAl"Xju7\bC-Y@kYjA>+lhkFCJ3e4'sk/[pGr.]AUp
cULKis6M\ihlV692_c-7?B<<AO66W,8^*L$IG7-J<q?K;nM$brSUjm$a7Z3iWeDNlM?i\OK2
RpGBRSStM5P5E9s0\]AKa=4cGJ\tMj#PO!Ed7*JnB7d9`R1Y$@.q/O!%^82"nj/SbV`RQ2@C$
>>^LEPA=+X0drJFE[:'BO^U2WB!bCt*)ic;c1(gfDFh.`O\N>J%g50AZIT0@)C!0UslD#]A_k
+X$8%eb.!&!&!eSjI9-*,G8t]An$#<S64KFdqBIqiu"hZ%j2amV)r/]AS(^TO?I06\VPeIUuS2
RJ)!3SQ>Sfo7aF%.Eu=MZ7B-+,m@Tq>IZJf-195jio.]AbT`D_S.7@"A2D>sNdlapnW6u.H(8
rZKT]Akl,V\'pF0PM`[Ag@5%W$NZlN:SZK<dfS>Is!Q2Z2lW/?Bm?CL[R)MlKZPbpfFGYWZY;
XS_,eP.J3WhAJCh+W`C`k`'pZ*Ighgs)NU`A`N=?3`"*j77?gjZ,)fu)\&K%iuRFfD)^bWmN
luMhCFrhdUFN[c!-b:<nl5"*(i!B)Dc5()'*6Bj1^K8meWkIUHZ5nF,B>XT:$*u6$7G8qMfA
J[JmnW;9A>*[*lQ.P$_taPj[@0G9h'M($%6TGXF,p/blfV91)rP_HQL(piA/uVCj@!p/r3&p
g`aF1U'a05.\27A,ABd;OnX?GlQa_K8'F>5CNDYAQ"/i#9*<Q<^0RBp5!Ljo8-94T26p.[i=
^j.EIs2DYm"]A^51n-9#dm:-W]AJV(m=t*b>h07"EhRKZ'q72]A_\+VA2L#VX/mD!=>qc`T2>#q
T9[,l)#HidLsC-0JMT5.h)6&&p<l'PU)?hE]A:"[GR;H"U!M)+W2e"-=&MnPb?&\0nIoEQ(Ok
Q2l1=#_snqo:7$/?=R[AVZ_bsW!LD4iQFIsp3DLR@=5&6"kgi7uUoT1HH_/bb%B6!L>7Ka(b
.gAW3"&.0p'B.Joj!SV[H`]A4DHd]AY-/iWirTi_eH`1)Xbagog6^?bj[R97!!!"FFkfN-9aC]A
m,D.;C9UmC?+9Ma$3NBaOZQoIDC#g%QN=[!2h?mPQs]AQh&KK6\q70CUq@_qQ#6;q33$p)9!&
XDIS7aE[WcGY^RU)eI^Ce0R<lA[qJ(VdGB)Ah3iNaSr-BJLkYo0KmN=!"W;UD6EN!/$)-6d%
ea.jA(@:_<QU=$WIB"&&DYA9:mp!ZgjR-'YlnK*j9bctH<grI7o5:[.-Lq'7AXaWP`hI8^8]A
(,T7Tu3m,:m[e@-t<Sl!ZbXAjorX29iW;E>&!!ODgj%,JW6ojon-/8j#6'G?,-4@b!N]A[H8L
qHuQEE^5*VZB(s&IhK,H/Y.dKfNJt4;6P'0*R4iAkhYncAR:A=5QLZV/#ao$K4gm<>PB#t"n
"'M?37YYXrP8]ApfA[f:oetqd>IkN[EVdbU,hAuFRWV`f`sKUO_fX5PK=UA6BM>>H"Ft@K_p<
l"[cu6[,DedY]ANo`2r"2C=F(,VA9ikAXN/sK!h=No?hA7a2?6),6"+0l?=X0G@eSl/7O`VHU
E?9Dno1q^p=.hpE_C$&"JN=Z!e)PDV/*S%7ft1kKX!1]A&BDQAsBSf'>:Pk!pB0X@G2.nN#kG
M\.k+kFA9?@:X!]Aj97k33!;bh(\BQ#3YYD2!A#L;nppitXmX^Z)2P-2U1kRJdnH3DW)_#)CI
RJ"X1)hC5d#J<A1?QFkqsXc#(p8OdKcjgs3?e/7`DhnWP)SShpk[TjB`b@''M+(jC@,TOI2:
`O%\iJWTOQ(kGL33>rA(8aRcKskS11nmab5^l.1;SkeU4OWQrV!N)IWc9GZoPKOl*pMEeHJI
/Oru%&snZT9=WW7kKIX#OY]A[cXR$bs,@L\?j0kC=bo>To3a;5A4Y,,n^F`H_\!15XU)=r;;*
7I7tE@&n]A\B<U-np@]AIFcKM?8mf)T.NFQ_*rjau%%27se=MhT]Ae\c]Ab6^0,D(ZFlYC.(7Udd
XlBB>5V0DBW]Ab7l944mebMc2OT"6ekqk(AC<359i(:/MDEq.L"fm<3Uc:F>GUq,o=`_WQrXU
9lA^$:1,Z9ZIVZ1B+U,\7*ob82I4)E\G3ii+#WN'7d.oGEp&X(HS&#I?h2<WDj+jIf#%?[hJ
Et>u.p[5ridiS9F2]AJ#ZbXHNK7&Bd2QB45NdiWEr5?[.K7"9AmQo?q(&jp"lf@\Mp9)g;Kg>
4XM&2s/.AJjKk5DYBiu\'^Q2R?lGY)usX\)%-jXq9nT@#2Ecg?onnEX[n>t@j5$(U07k$5@*
\_;ZeOfLos%`^q?T0?B3=e-Vq.lOi7.rG+E/j5"fXdaYagC2P8]AmD*OQ=1UZ9s,J;gFhK$g'
YhFls.]AFEM1BO%qrUQ6(lT_qA?bJ+UH@)LL3!g?_l6PF>bAfjM3+CNKK()SP1;rgZ-`R.$%l
/5n%QreTERm=jl.pfhTQV9Y)g_DOShi]AX&;q`h)WPZ!g=617/r/j1=X&=ZRY/DGmF=P<.VG"
^<N9cD!MbnYbSaA<oiVS;:$fkSS(d3ip%Wd!"O"im]Aog/nk.)G!1T*&u$NqdLZI'+qbTl@Kp
[Q[.3lsdm)-J,rRb&/O?3[0A*j]AFIe\Ni[6S\EsMH`3V2fDZA6dJbJ4<:agLMRc,mcM7U/Xc
"=TWF3DePNVAM[fbp>!ibgqqic5#S^PODJ02:DMA#YOt-!O&b.`AZp1O8-]At7O@M2$:hUIJ+
KWC%'$IQ+V?+-+`%i%:0M1oXVrV#_;>'-cqH8acXOm-<aA>mhO>1^nll"fI*08SV[(bngiO_
>]A!pGa9Er+NAF0F1XX$6MGD\om>@aQPiWV<QA:1CTp$NCK90N@>RD>ZmdSV[IPH:Z)O$uc<P
=<%*kf!ViHX*k29uEUlr`Y!VH8#?>WkLZfb6Op_[8l.o"t,bb#/5M!_iZ?_GQ5g1r/J,#Lr'
kiD`+T['O:E)Mq@I#Y5Hhmd,guW%&-/<9&A@\G.P1'%8*DE?f7ruD(#Mh``P5jB8s,FTmMAF
R/6>qY^rYK`0HW!Up.IX$,h;&.e;k=RBR3sN%7OP[.L"C1AZ%kId/+>JpZb/H..lX"L2hL98
c1JPlmr`^8U8=O>8F%["3JV`RaL!R\se)O-ThO_8>LlqlIes-1!*eUD2t6q3=JrK8[u^5HND
&(AYrKj8@1F_-G?9-sFPrFJQ)HI!J%@`-p=7F?14Q@nTsnIH.mYSQar1fK,tVkuUbdkGLLHY
DIT+gp^]AloKh/2?j]AcMen;Lh7iX$U0(1/ZPOHP3aeo*BUD(Q4pOOtKR^"c]A[=-C0V>DI"0@$
$(S%,99kP;YkY,00_cil@P?mT6S.:V@('.BFE&Y?Elo]ASq%63[#bSSYVCJO\=oT.:YOEVFD:
-p3<b<;DEb5%4OX5+eM`$u`!tnC2m1%kbUm=*Z\)eR8;&\)Bad8u-g.C?m!;VL$RgnqqK+J3
5Y8eq6tpo$"JYP=rCGck=$\VV@I.DDPo=./mP_)>7Su0B0Rj(idR7dJ)GHWPFX8,64&&S64*
m"*;Y6S&J*P`6,SWQh/4@nla0S13HWtm&26_7PP[pU=4M`o*]A`"C\C1t(1n+H>&Bii(#$D:'
[$g%dfm"#Sl!]AOW\E<+Wf8TTs3tjQiW[1=HjAor[+Ah%hTC)K!anedl\PF@M(qYqR_9F`*$S
IF/2!%-]ATtDSJR)!Np^WpALVpa?l/+&CLpKgn;YE\^?N)o/1]A.Z"l0^^6OLTLt'.gY&IHi^n
_><>/mX[fC:Orl@!sE>-G*Hj'rL"'6Q3UI2+[eVkI5W+a`I`J$0qFtQNkR\D[3qe+rlc#_Yb
eHVpJn^d-/*fm9g)DT!t)MudI#N[/_.6bY@D_/,WE%,c5PS2ipqONH$31R[^pSC.F?]Ar`r.-
tLE@_8JLK"-Wp_C:pSb$-K#oU\>lD[_,tc=aYJ7qOJ]A/BUg%SLH'&'MN>6Ct)lZK0q@*OCB:
#%Hf@gk*)PAcEAWW[FW)2:ZrCTcm*oHHnQi*el<N)\-<8\8=`Y&m9<CER"6lc%WppEG'd;Cu
hGI&XF$pm0R2"7E6jW8rF!b1\;-jju/M4Z`E=UoS\eH,tdN?r:n-pbnLI@a*P]AJ0j:>qQ@X/
%='J4j']A\S,/:L43C%[p"9]Aq$J0)LhK_AF:\S>j,?V9XUdk^GG_6DTC=6@Y@%G=bHgAbKXTU
WJVJ[KK.P4LqHMfGSJ'6W&0eNU=*Bn/2)/>uH<q%97f]A)Fb.U?5D-3dN?*R7p=Qd":]ANr<,i
0o!31_c,dn<\8&:L[B*5?*Bd&Ja<tZd<6>bW\gUcnV,*_j<[`IUm,'s.l1tr_]A?ko%7g>p@2
106PA%UVH<:2A1nM`#^#"_*-'[_S4Z40pE>\n;MOdj>P9m,8P[Z>WAhQla1a:s@[g).1c2+c
9<Kj;2OciOC'FP37q&1tlg*T$Wt(jlW;Gmf"H3,-'phd=koM&nl=oYWs!m\J/c7D91#fPZ^d
c9b"W13XmpR1(WEFVQDXXG?N2]Aggn0`lFGc.D",lC6as&J-BbB^EZl]Ah-q98__q>'o*\'D$T
81qna>gK#X$kYkD[1\K*mh*lYZJ7RJK.?W?,Y$?&LhtfV085m>;0P?_i-hVY*YVkF$/;U>\M
j[kM>mpbTLiRcp2GWfm\QDmJOeI'YF?PfrndqR%$,>f8S%P%Ymj;PCq+8\o9DR)ZGgDCg))[
8:CP(Ca[tdtXh=XdLf&k(V;gg]AY;Ge=>,o)CO'`A)4,7B#[HWdGmS5o-h/8-R-3.W8d\!b?`
rLQK(<+/55O1G[Dc0@=5F_J5V?K>&S)dK%nLf,J!WaJ^Ap:!;9;,pSq3?*r!dl&Qb;T.`aJm
>Ocb;M\]A%e]AW@lsd_lQED6%[>:^`iIFYJpg0O3U:2@$)fWD3eS>2l_N+3Wn'.'BBd2dUeo6X
\1)lJhs-iNA&)2dAd<WigeVa"eFt)G3gZNKYn)\(bZRHG!'hrc2SU!dZmZ!u_[)B?bbJLlJL
KATrRX@RpH>Zp@hol#9s[naM;7N3n2_oXW"G_spZ_.s[D_iZZMQ<'TG97Ud4p,DDM3S_=JY8
QDX<7$-1nZg4*m*hKmEcV?;"8d0HogMfLFpELDW',(AloPN@,h0+T'96P1nZ=qcib`5'DI#t
8?k9O@Ca)Z1!QE)SJcMW@d<Ff)nAj;#5nMWLS>:PN*Ru9sT=@dZo,"/.<c9lfig7Ab2Jd6a[
Mc0K?k]Ar.eqLk5H82Cj5V2I:S?=^M*dhrD*K@bgp?pQ)7i$[(j#\u[STrbFSWWBA\?C'NQ)#
LPUprI=G>/Oi.[6ZhoE\Ljs$BIX%2F[1Sc<S^s?(^\HNiO"./:'6PZVt=b\RG_=DM$M',Jl*
F5f<SX3ks>JO:C-<7S7d&l^2^8\&Z.>rl%raD6Cg8QgWkdhG#n7?@'2E4k:-X9:-?p#)\2;F
/X4oc)atJ[V=_LZ?sJ7orCu5G4=AFZ^Aq?]Afr!L$_"8s/.9q8Ke'Jq$uGg?D02[1Q\^=gU]Ah
Um8Q,Nl?#V'2SG4(MW`siUds1b'q5gfRq'n-2]A1#%JeH-G6,WT424XD0#Odbr\,W_]A&0OS<k
'.4Yli$.&,)M?SrgpW!G&Q;b<qSu+O_A2egCYgNuDKUM3maJhgcb*(jH:b^IaS!CsM/:j8JV
uf8Q9I?B6S.O6m$O%:fq"D=N^^h@1\MUmNSACV/Xd??h\<r)>=XuUOum>;mB-smLA(AC[2AJ
G&5<_+.3D[bG;^Z"a)g!p3OmpbSa;h>n&gkqmgH=["_E_^;D@3pK\8gF$i#6^r)e8*$Tb@-L
0c3LS,J8X=meX33dj0^M]Ae%0\*^I::NGP&j&7Lcc_uPEaiRI"\BitbQRfSdCRgM7M0A@c;^9
]A,;H!PQl*S-H[Jsu"W>^&F"JLEeqZp6-&/Zj_p=WMZ`Q5$AcGX^NZ_o)6:JW1glL<H`X4,J\
.t)Hjesb!ucMH9pOs7CT\.MNV21#_L>\%!fB$Rh1f9MqC`5MkODB>@;Bc8$]A.K7VUh%GqBPX
sZ*E*6hT7)emLa>@u\Cj*(MTSfH#EFr_D%ka"4OQUV\B7nPg4%D6;b+,$fCc_`CCs$C<3[p6
Yj<G*BdNK)]AH'es7>0'nslCJUBo9as4->3_QZh$,)5]ANn1$l6^Z3=CQ`Zt5/\Vd39G,stRl$
WU]AC23Sp8o@]AJb8Nnkkrl[4JK/%:"Pn1%J#4,PIh2j5sba#agoLaYFdpotg[.@gEdIP#o[Mk
,qYLYqEG<u(f$\qnKdF/NU.Lp*_*37Yq*Wh+'Y\1KE_:,-W>L3M'<IFeJjtr#k-^c6h'@cPQ
,dm6"W(3).Km`3o.+8R\SbX1TM!(W5=_.7O-e7L(U?!-8htH1(&RQ!POL%j8i&A"?IuaM?7/
M-X]AL%W)qZ7f7&R")NmH!=0s0ICDN%,cs1o/#$=-LS_l8j#i,;]AV(D*:U*`D@_Klm`c]A_ct4
Y^+W+k4^0lREm;XCA1*AlSmjc9':jD9dBQK<D#16/db:fb41/U,j]Af$@7cTI@]AX5$l_Y@;nR
JZ.?5)JXNY'_480btMom>]AP6OXS57?91]A79#-L+rjFlHjQDF-pr)q5%85s:>BMmp6DHaD+:W
r2'+_$jhQAr"\(`7#L&ZGkk`cNio[-6j*S[8C.=^(IF"GG@>pGi2$E#:4\a&E'&ri_eA5nDh
bJe."j>q4ZYdt'5g54h0K6/QY?_:Z*/^:3*-9WGiV#t()!g'leGtda.N9[P.nS==)N3@08NV
$D.DJ3V:ifH<F938*SrU!)3:n$?4:^'VuDI[6`9&g;X/mpt-dB2l^XADIm?GnK&;tklUqP:U
MFQa3iRcOF^P;LnX*W7$pYV48[pJ]A5YFi4K`iUra^)(lj]Ah!9p8GV<o/;+%!kd!M;CA'AO&B
><"]AYphbH2PQf%fosWfm2G7E3gEhj2YYh;G4IpP4rHu',ug<en4"hK5e`0ib2BHRBmW:^_(u
8*mc<KAH,b@%[<!%2<lonS`^&^V?`?^<mac-n#!i<N;MGq+9)AA:%1RfolJ\RCkF0VA!hH+E
C=^;`a?Ug#=HJP52\cVkqI6O'=>ue#QD[;[f2Fu$f'"&Q7BAE:.Te'cYA.*nUZKA2<u$/+O[
3\qb4:7`jmLZ5X`jhBa#Pp9ah+=JEK\kqqU8:9IND/]A<)@Jp=jT:ca/%+\D_r-==/c@t4n!!
46bY@Sk6bIkD45rX^&C[io2%3\e^a>Y28CN<ZOEcmEtDtT="eaf#Bsiq@ZbXg.lSAI6q0i3O
dpK2ipYgWF<9nk`M'6Qi@!nmZ6>47R'4`eM!84@Y[rsB6\QunTk=I/C@5*&9N'b"SLLQ2:,I
W%m<t1F1R<2R_PNGLo@tY'qG2V#*nfDJnKlUS(9Caq-DbPr:itc#7Jnk+51s;k'fu3@gD:8s
lH"p5U;\&i[:4gBT;2JY_W&n!G8"TVCH5J9.E]A.R8j>(?H7.t<\MdHj;Icq\>(pT0<aFTYVW
(J[N5F;A.0Z>QRSm[Q4@uj5l=eoG:c-+/n!*p%l^J7=<dpp0J:9i`SSM@nh=qh(^MS=Q!Fp+
[6SW=qg?k2rYA(QOlFd<dbDm%diB=9Cn%l!#ZWrio#Sn/+*UNZs7sQHpSnG`$bjU?f@.Pgb-
iMT3R$TGXq^]A;8ntl-l5s7@)/Za(LE\SWf-6GP,9`#_BiU3/]A+4a#F#A#Ls&#*;NJTAuAGkE
XHOJHP):ZQEd__1h#V;k`*JALm46A0NgG6]A<KPqg.79*0[L?)j>HXdeNs;oS\cO>*RLJ.&<t
EJV`Le7FsFNs)JKWW1;J+*O7,I_se5Df=@DKntZ9E;DlTS/N!(9k-LW@[N`eVj>rP&cu,!3"
BuFq*MVg3-bJVgZ=3Od06k%A%;@`F!+%^:9Rq[BnNQ/6TM,BFf,._%*aqW=_KMA_!HCrAp\-
\=D_PA^9%eenX6Cph`PGLX:NtSq"W;CnJb9J7p*L_kGXrBP(+Or/Jg2r=L/6*Wh7"8]A`IYZ9
iFk@>ij\>\g/A=YK@bKn=0X?X0XRb0C%GqnAj@"r.$"UABA^78)F[oJJ]A]AMo^#\cZb'MFK=9
G1T,f>,+N0gn:;j6Bs5TbZ985.0Sn#lK=Hmq7D`g#q1V,?;]AJ!r:Ha$Fk/OIYb]A)DG6Fm*J8
28RHW:GBaU-.tHpA2X<K0(:Zln:A[Lh)==#hdV*aEaBEWIo/*Nb$hU17`bihO"CL,<m[MXTQ
SWYZ(6XR:j'%)cC$9EP+,,L"sBnbC`,QMP'4-ROO]Ah<)sGB4[5#-_cVFQWJk+Km#fm"Q,%n6
_0eF_`Q#RAdDuM4/')+SjpJ3E:Do,Atk,hq`?OfN=Pm7L%l95O(c+(Ig0pM,-ZYHI;QY_jBa
lHuclIROH<gN:i)(RS/;`#hl]A3&>p53UJZ8X)^GH(Q21Odi/`,7n.#Oe,A;S0iW-_V?&gnPL
*Rj'?J'"Wtog`VmAc9:f;4<1PNLB?h:VRF):,o@!<=QZ/@?8MjR>TC8&Q$c?hHA[lED:#;mi
9K5\R;/6:.8qa<Wfr%>q20sIA2%kAh"7N@(\$H6*%NrB2?Tk7F0HIMIn9t&sf$BJP&Fb[Pe%
mL,Jjf\%pk`(Qbr[6<cKJ_1Y:N%Z&-c)5`1tF]Aj*1+SMW-Yqpce3mmtFdZMh"TkhPp!l5l&/
4==JG2q/k3t3#1js1:7S/_FNSHnj0q:TUZDD$p`"@I,;t6We+8P>oqeLSBUpG8d[/f!)_M@&
%[h"H3Yf)LX^:>3FD>R5fsO[r,/Qo2d?7C:Wr`<GCu2FE3FiGq`k#T_n^:Bs)dXT%^j35jJ=
[\[flG%+^N7jSptbl6@5.]A:o0r1#>MMfDn`[9f<!5[m3:SK]A%aSoJ1V(HUn*+QQjfjWU:HKn
^mJ#>\k&N1"W^3@l?uC3Q0?FH8AJet8\ZUX*@ARH4+Ta"'!u@^8bSAZap*WaSc2H5JR?;J[`
'RK$G1or=ftr$7i7;;rcWA[e4atm7.ibg5-Zg&N)rUpQQWKtE8VOlg5h=BMlg'.`@[\A1DT/
m?.VMpI4bie[[eu;V'XK0YG&mUrAm/erk#E=Om_O9Qr?$AVV=CLW8(aib6d[m66BSsXZj#oq
>7Ag&-]AW8:NV#nGNgT$4+:]AVXdgLP&h`LWm9&]A=T,G#?9ln:oF!#'<K3R920lWT58A8:VG>H
e'q:dVI<>"*fm:?4*b7$aV=3*<aq]A_6AElAq.j`X-U;tf-UDG-J,9=2=56Ch_coDq<34YKF:
a)8X*HANrm%MKcdnf*aI)c,^bN_%Zt%Vh:R[r,/PX$$G%_O!rT!+i(K^RFci>$K-:^T[=d<D
6sT\fJ\Pg$g@AE8edlil,9%:T)^-gA92S5&baXk]A737*uqjNb>$j+SUT0"qgNf5Me@0/GA:5
f%phf2l9j\f?a[V"c!@J1!2t+Oa"b8s/M6gdl8RRJHmX$9O?`S=_#/605GEI'YhpFGlk*hah
U9$kU4H_9^KQWT(@\3%mPH)s>olYaP"V9p6L%U?)O&)Uc/EukB(:4mb?2O-^m3+?HYnVh3dn
",QQ0)O4L*W.<K2[W@aMPp=G7>cIi_H?Q_1UmBIMRTaSDObR'Z]AGI6L%u+T$`cg-2QLM/6ji
C"KZ)_>H6?fd^^<Zi>Vio`f!Zd1tH5-nckS*bh@I^\:o"5n2C]Ams^B"!,MM/#*6RJXAo>]A\M
2>R%I"MT<I;g+eT46spp:EiZ6%m\pa_'@71T>N0V3C?E_Dlr0o)jtH]AT5am<5=M/XJ?kb8#0
pn9C?*H)opIDIkgD^=4VFii:[I<1#[,8%D:b"/dVt%7fHI0ZD9:d:>u:o@2I-2B8Q@qUUK"p
O*B<"6(pIVKBHQ`.3>p;iW/@#*qT=-QCIJ255&9\X?!-BuCKu)X3D[p?k:_fQABE#*>_B>n?
BOCAA68W[d%t<u"5kGiE8L#7l/FW$$4:JK0SI($`-0#tO*bIlnS$4.BMdcbSZd]AhlHf@d3sb
/gDNgP&7.[.$`eqO7B7S]A.f:i(/nR^Q[0B=X'/#B?'kf.^OfQ$i_U"j^'`:)KT'"6Y[>PV6A
=hT0L!@\Ak0)!qi&43XXkF+QedTSZPmtd_@aSCKI&1]A;a6*4eQ56!:B8f!oof.5(_<U?h<Kg
g$'R:GaNSGNRk'pLnH2u3;eIN5f2b*r#c4rB]A^Usr;Ns3=/[6Ub,63q2(\FKaX#@i8K(9(NA
3rWG2!V1]Ao;?S718-dq`QE7gq_5q\HFmXR\>F#,nLds<c7Atnp#H#fOf+79N9POYm8N!.Qgu
mH5m!$hGs.*^+&^)VnT!Ed>@KEfW"6n6RlV@?NiY6p+,cha+QuVKEX+_XBO3brT>\]A%A09ZC
";'\[OY4lEAb0app=e2ZL7p&5%5k$18?9u!.dKe(3DET=R]Acqk_?[7]AZp$XqI1-sCqngIMDp
%oKN41O'nSqgu)[EoH4]Ae:ZBl`:T`i%L./(=@YR;=M3]Ah5$&OH/FKePEf2(\oU#O43&PTBVm
a8*Gc3M*0o^qPeU)eUHr0]A'AHXfe'c3;Mha?<d]AXXKt`mT-cAB$AF8!ke"C<B;D2eG(2CO<j
9Ucob/_pF2$8sHdX"n)F,HAGVgPnhZ,H=*J8LFpQaO"u#P$:&:$^,^<)igkjRn`$*K#4>,<%
rlb'ZE^28NG7fG>!WO.GAbMX,MWTY;@ZIZU_Cb&7jm5:5i9<;tpB:&c$mLCYNm&14ind_;O*
9%GJ!:(=:/h,7`.n#(<OJj5/0:@9#dpV,AtpGed&c:nC\R?1"9VSbO6)-9fcJ"0f9gK2qDc!
o/;2m#li;mtI0QeSn.mG$3T9"WT((%Pg*-&P/bVTTJdh^M3X+?0E[h7"'YTpG,A.M#&*?*Lk
0VTeM7(B=ZaInj]Am3r;ZADBf\qnKgr;Ju[D=9s"-EfH/%tR?73`:2D[3^Pa0OL<ld=5D/(F$
)hM@UD^#2HksmRlj4l/5l)r;p$Y[4EN#FK2T85ia5)$D*DIZ_=frr1Fp>`]A?mbD:1A.Ad8GA
'H+4RIUHe_TH]AX,)/N#h(d..7dREL/BQ=C0J>gg_'n5CoiHjd7]Ai(tf9S0krtT15gnF:[Xj#
/D7^?nKX>u#Fr=`+qXI'7HL%6$m)K9?r'\,TsI=(/@($`8&$uG-hB&Lh&s[n4?kBIArcbQ%N
*KXRtfe.Fc:dCb"l6M\>]A3NB:aKaoXrlG$p]A(ijF/o,jp'L_VtnttWtFT\\>`Q(s"_TpP6*o
3b"\!!KM;O]A@CBun'C#4;M_inXT'4QlS!\3G4Qrij>&05j]A9(WfblV;DDB_1-I>`%VTIP+o1
WoL[\no7)d6cUrM3-8+P,::':&8Et)q=:e+8PmeI?Wb**='OYV5BlO8'lg4Z[@7KrQ(>O+%F
G2+/&2!gPD0-/+]Aq6=V"e:28_7E9g`fc"/7EklY[g]A<R&KjiIp;ZMZ4A.?*R<!dHAjtn]AK2l
H5RMVE"$jKI6"fMLRTk"P4`j!HY.<l#@\lY'TY&.c58CqM;V`@lp_lG+HgC9l<lKF=f&^pn\
IeY5cC!4F<j!icIZm*@)HS"OcgA84\FJ:3I+%,+46uT=%6g]AEql$!`iLQ_IrA%%!@oM59O"(
["B%a!C<4O?DGqD$]A:SH@.,$oD/!S06)ft_D_>%%4Vgf#rW%M/da!o?ZU)6&>.mn=b`6C!-L
AhNoLo9*c]AIo:TA'%2p'Ke!P^Mo?'D+jj3LcF%",Km'`-$b9KDL8_a-T^X#)"R=T>S.8tgi=
)W-5dsU'*1O.p@=T]A>t]AW;]A.]A'Wf9Rg7709i7<M/#:Z^.ZM/5Q;=E_tu0krN'X7L#JY2b0=#
J&]AJm[NY-T1d5NKTRp^G:?;j'GolG"0^Lt#-7ireUtBc6fNM<WjjrdHjj.@1:S7sLGc3>\Ds
QoC6_1(F-0WY.ZaS%&YLCtiRZ5VrT%+Z7$YcUg]Ac=ihH'pF8^%nSF(_fKP+9L0W4/!LA*?U'
crcND*<8VE0U4t7P6oPLOg\#n)=,419dk3h[C93<2LKkX$&:>pH[FKOoP8m!hP[j<!c$b:8-
/5jfIB7U=qL[8d3\\iA_88I9ne=9P6I6iH&qWZ#"J5ce';b+PFtBS3H$19\1\/ECf8q?r#K?
DV3jfiC,aqY5*4SlQ:&Imu7sXZm)'FeEh7Z]ACAb-1a^RW?$!rDVaYL,</-7hrHnE:)kYB#5f
qoVPP1Ft159Nl;]A&Y/pY.V=(nUKL-jDi:7`H2#5CV[[kbPga$`^L_i=W<2<^CE[UtD`CE41a
KB,36rtE-;.AX)1DKj_/ie(knET;InHe6lk.75-ATB9)`6rU0r0ZI2m$G[_7T!D&%]A_RT[bl
`WRf48=!oWeepE[1*qi!`U$KB$'>WQAh+HJN`hp.5$u+993X1%sL.nJBQ$KfRjf#'8#CEJ4k
@g@SO!jd,fm/W%!c2=aXnc>29fhRB-cuI\&rLO%H]A7dkD93)7.2u0]AZ1H(Bl4ugB2e/a)*!u
tIh&akEOd)X]AepBq^[ILLp.]At9ClZ!N_W!_>V4B^Bb`m:iuDrRCaFX9kU.E^K4fH2#Ip`rDV
Kf+/p/!?rO2Uc%L)<:l;WLPV#.uS.(o*B>!@/j1%a<#-J:%/H*_NKlbdK;g&r&-#nm3D?;GZ
6B]A+/`.G-WprJ0<pmXc?%*.moec?#V*B"$U>d<S&1NEfCjEA/G\"uUgBf3+pMe^=+P6g]A+JJ
ii`a1.;LhXbO_WshafM_U2ODCXZ)/M4An7G=hh\h8TLsD!>am`h%CkP2WnNk/p]AlV3Ke-Be+
6;!pAbcbs"*:ccCCI#emQ_:9"V..dG2R8VIaCQcqY%C:a8bsmIp^PAaAa6/<-o7lPX\TSAUN
Ps5<2ejE6rppn#=AEhD&`@N>dUn4kGF[N.ZNO3e6FCK;>-s^"cf3D@L+GW"6=\ZF?Pb9>dJr
HY+=0l5"F)\m(D%E((]AY#SB=3.W_acDBD\2Re#J-NpLU=GatA3^9h/>a6!T?&RR['*pPVk_C
EItPucI2J`SKS&&SLdXql]ALV='A"?/oLj4@eG6e[u1R7MSS[_EtHu=BLtI"=FSdkmtI^3s_(
E"jKk_f>B0@M4cn;OMb!nSLk`3ra@gLjn.XZD\1UP(A7#/<hE:TU!=utHFCHhS/Hj(LS]A[D$
*g3rk3PA=%%OQr*Lal^I`58mJoJ^!?D8\#D65aa$sZ%Y-?kh$;jDni5r'um#LEA)n7U/*onJ
Z*qKK3G*H\8.q]AD\2[!YRs%^`NHNE8m<;CUQPW:*X"e2AFB[\X\+ncV_b5o48fh)<5oY3NSD
@JsK%b;W)q2!oefZma(X_0:.:e7<j$p&TSYb/&=O-h>j/0.IJd]AP-?Ci`,'2btna]AVa37_Ot
Ps/k3Qh[KY+smWM<2tnqSk93`2K$9]Aphr"ELlVK,F.b/Z9*f5)o*$#.X11AV:9q^2,q9/e"`
rRNul.k>QtO6th0s#GZ6K*q>h^%eh@gUiKF"HtU`rhh:V-DcF./<;/<D$?nN<DLO%)jA^hs/
MM1W[,3Y039JRR.htMd+hZk1ZN&Y>P;@Tl@n!!,f1O1o03m=tmTHGR3nfq)[eYOW^m(rN5[9
ZSV$Y6Taj%5uG\,=hfp[ZY1u9uc'/!l=WJYQV.scBu!mB-;4OD,:54aJX!+@4'pOn8/Vm;<$
1G"TATqI?'Vof9[[p_IB2Vml)Ti&a(1X?1%@Lb9%$pr0fH2/i>&%3dt=[2qB"n#ALD!9H!g)
Dl9G@7qCgj6<L)SeO!"`=rmI%PAuRp\iYCar%3ntfSRL`t^Z=.:Ed<rIgcIE'cD8SSGU":Q-
:8d-#`&E#=]AKT=+%DtM(S@qH0bj#X%J+5`%`eX_'JHL\/AWDe1+h-snQ8a)Ed^TANH54-VU#
RS/qiRc<Gq]AR6>JLW;Khp-8D5nO1M(1U;\*in-I^o9$2eEOlIT0KQO@d9:MgDG5EL0"a1[bR
gmUg_46DY+l2:X7+7oZS^$i`8hE;q$>;ffd^Y:^&FjG6[srWYL4A*i+_VS^P#^-0!70(X9u6
c*UMaj>i,G)cK]A4rlod>Z&jCKh$Xb+8k1-$TJ(ZFe!l*2<%eL0%s93s,=:em)i&nK2!j+LlE
kMFk$t/WjYtAIYH&JS;sQHS'.*1npd*f.SbRC&kr3\;$ssOgdYYhH9EkPLh35+IjAR<>INH3
`W\"W=]Agn*N[EbEo22QM1\T$WH]A[G#"o@)1"QkrX`+!a40W8KlSLuVpHJHsAY%Ge)0ZB89Y!
U_[SZPgU5PWpT0<k%VA0C]A0e\H*oLj$P)+e:%Xn016'sXqhDZO9@UJMYrZD9r@Y`k=U=]AU;1
q2@:f2OXHA,k_NE+-(2L^6;pPj56=CWkS-s7fWUV^V4PT+mVSD9GE]AVG_@isQa`':=m'e@=Y
eoB!+ORG*-0!$YtN@pKgoHC6#=A"ai>hd!?L(\B.S?jA2jG(h)`mh4-OK0+\+Bn54au>d7bf
<FA@%YW!@bK*V\[;@Xr3p0Q1^^._HoJ5VnV?FJd9R2:>=H29?0U]Atl)A2.b4(lU80g^T7$5K
FPkMhtc.te0!g^H?^bS26JEPch+RZc;I!JrbXA8Fe`DDI8L9-.M/WZn3K8hf>+:t'uj;:cGp
A,,:P7e$<DL+PjBC$tZ@9iV'hHs=Q0eMZS>FM/$43:6oMFiL!=?1a5M.*sgDd_Ki%ni]A@?dh
.Zn#ks+f:a$K+l'oc7u/f"-H$>`qXSBmONQrtohrOF4lcLi?7?B!m-/$>H,5]AIn>T%mMm#sS
Zu6E8Q2<f.#\KT6D0@"X_^$UI$H0bt3knp$dUf<q+_th%#e]Af^;b<NMH'9bF`<-$)D@*30m!
\G,Yt_8tN/EA7g5f+V&bC93K8;?56O3%HRp7jXbQ"l+_L,l(R'tF"'\?*(-')U>\);A.U'Ph
1A4XrnDCc*j3;a,_fPS.7kD/S:".j&\j;i(6F,^bnO9HLCh-YM4(1XS@B*(ou=jQCSD9&Jfd
&"?B_S;f$nGJ*Cer;OL7-e(iTn(S7KspeM=n(3'n6D@cMqk\^8*QQt3k36M<96rW\2!R(E&3
EMCmojWXR"qXf>rQ]AB^)LP^>#f)_8$4$K8_L^Xm9"Ee"><un\ZeIEj_D6*=qAb8fdNZ--``B
*7Mp-mq)]AGL^.#3SP2csqpe/$'mc'HZkTKK/&>NIZ1?"@^1]AoDC03k*>V%B+k/t?IC$NGe?K
Q"3e*9!)+qschQk'n%>9TN^*irc`JW6A6r']A@2^h^k#/_b2[L9J*Z=:Bd>"4C2fS<ZI`RsZ4
^Fr*'E7G]Agps&no\;'m#t#&P8[i7G0F4'4,2o.nR&mHslinR;$RO:IJjG]Au"5(rr5Rf;F/+N
fr8l`Una0IHrjl6J'WME=]A&j@k<s+$ni5)C)I`1HAI8]A0:s;MI7PDbPng;NV<@fR'O2tH3AC
6%Cm;-@"\ED[&2=>;?8>1mHlV?\fMuZ4DZ.)L\3e0?@+=nY\MDkVeP>(#.`qWT?T4mOMs+XT
2S:^2F(TQT9IXkQq695G2(]AP)OggR"f[d_14rX_TiF'gh*E3VjL@JmfJ!2M*(R-g$;@;MV=P
Y?'_fhaHHS?l!Ra6>CHsN`KQdL^3X1sgTK_c@AP;n<f#O17!3)t!nho2K!G8il+b(65#ZEmf
4cuDfoj;Nj,K4K7Tc#<Url\WmE.FR;Sa,Y1Kc)Y>UM)e(0@C"FU2s:G_k/3\.LGkf_NO.eZ7
/kd:P4[OQ,6BrOAQ@DGY'K6m@7P;5?sELg5$8Jc<2ZL^M?s\#2(-_hWGop#SHUrc5Pb#W'sX
cTYifDm>Pr1#EVAXk3;$&q[lJ(M%FkKqdLs<1`o:KuF*?Z[R^<4G"_L-(+7!RYaZtg;9:p(9
gM"PFa7L5.E0_A-H6/*g9^e(%J#)A0<$-D>F1BJhLhG((9hf96O\l0%VkP^@@4h:j5jcudoL
"XmTF,:E(UsK@>']A''62)W*,Gt/.`UE@$8=/T(MG\$RprT$nR_0:>FeiI(`RK@12d"W?]AUS6
.W%N"8F4h:0,^qf,s)Xr9m"585q%NUBblCF4!?8ug@UA=M<#<Cd)bBAj60:[AA=_4XXCB>'i
o4=6Up.WJ@hdY\H;Ui4D$HPGRO3(4:SYl=I&U&HA$DbHNImhgff=L)U".T!Ln?X+eKWOb&eW
T.2b2Qa(t)UQD_CKgPRSQV`Uu.IQTm85:H3`_Lk2/`>lWI?''@[j<rE5b=f;!FU%dFaKCI?l
>hi_Lf@bK#0U9UIWhhNZjr7c`8`C,OphC0CK.>pJ70FRrH*$IRhGAW?pi:fI<sK;@lLN_*Qj
&#rV^Pmo';!L$Apgt%TK6fR6>Z@XU]Au8NBNG1+b0oD.R?_K%F&d=c,3HuXgBJY/B^Y.ks)'.
K5D6m2DV)7,Fjn+^KcF=ZPDGmK^sd<Ui>)CC2l/+H+08nlP&3#gS`.L;n%q.ed&RTK=^e>:A
T1=ncO@7q4O6$0fV^#2dchau35P&WbfC'Ap$MM6f!PJ5@G#S"`npFZ=mq`L1`"f/r'GY-NCM
S(O"L5&[qh8%MJ(L?cS&3CXch<!jd@#`-dVVfC6T:#M#;PO_uc%a$?Ue4c$q^m3>@93E8=:8
L+G4f4KP7WfcujmlBnpFkppm"G+[JSPooDL$Y;(QNrRrtZ.^X8m?B7g*OV)#@!;h[FhH8j;u
`Z!LFqoZj?$3G2f@7b]AaUp4<JBu`:s77ZT_C.#KtkLDJoC[Ya!k'm%08(EWQD!_V9:ib?sm%
!QI`AP2PF^FX9gu!pNkRU^GE)s!>8h9Ygftao[Ke3d#f@O4qqdO>-cf.pc`9[X-N8X9=DIea
OY1Gcg2_1[e`E=HB?^\AXU/R%Ndr3i9f>kX?0$fib3YUanhNr?fB2V%@X,(Z`\D-'JhqiA=c
S1aWA'iG\rkc<U69fjEZn\b)kX:baR5+Cq2?(\0t/mGaB`R4/;:kZ#/;Ka8f.NQ?g71r0M#T
K^NjgG+,,Le3DMr%G3^goa4_M"2fUhbcG\XD\2@p/S=t8%?!6WHE*'NBK#\AepcYI28ulWmr
UocrG$Men$J9b_fD$n`qF-\2?YqljcRS+]AiQsB_<sM$^7=s'P6$dlgUD)o;Sp23(JcoQ.BRi
W\!%$oVq7<3ilcfK\i!jA;8"S\f`JW9Ph4<j^JLN*5F3iY7&*5P#Yqa4B(gd^OG*W(QPF^H]A
I-'5qPB.:Wloe<4mQE=^rbh!hg'c:aU\M`eDGH9[=9EUX.83HRudt#.P3t_9_@mT/"uW9KCV
Sm_UBjAbB5b9POi=rNjQp3?+<K+5Q0F#*ZF*4k1%-!4f7&2Y/2<p7_FX[d&l+G1'`NlVtmR<
$XQo-4)VF\'^iF"]AD'#Pe3EX(ZAWDZP'dMH>"$f(G.UC[\-LYT6PiU3QoU1[L9PnE!M$fZ11
f+\/]A+>83@ip$oB.20H?dJ%KVX#pV_kX/300.GRW4?[CY`/I+I!"j.7+1`;iq>Hg8I'TUGdN
ofGrXSYZ6(qc@:(hb,RgGO.'&mD=Id%h)]AA?Y&tc\qPWltr7`W?/PF#<CAMRBQAFiVh?rt%<
,T[70C\^L$PK`IFIa(mEHa3%ij_KNC7`s_i#=$7$.&r-"UCnMpq7F7YC#"cB@(/LS1u]Af6$=
RE4W.T_\il@uU?&N>AV"TpkZY4-Yii7[,B&ToLZu<$7kroUE%lQ]Ad.doeJgGbtl.n^gF*Dg_
%OSR[gW`D03fJ?>p3]A-1XCP;F_Vj#'f$Zd`mm_G$rV^@b^+(g*1)XmujCiVYfs),0o-Si66E
c!R$fhO<G`sjgBYs))]At`*!QYoa@Rf=;I45>1A_QIMrni?%)Z#!j#/$5%Oh!,f_A="TebEA!
&_52iranU9EL=&:,2i/U$#sIq1)Loo0f)GdKD.pu$&>,b?ToeRZA_mM=1uc?1\$O:UMJPI'H
u+o;g(^)Q-;bg,fTfU\WRl^uf>8i]AmbnN?b>cm-^J^'C!_`6b]A>J-P.[*fg.-_g5.*E[e%;B
LLFbZB2"OhWl#,!>Ie1u.L65l/>K5#'V4W5sI0aqES6e3R@rcn>Rj]AbL/Ou(=7+1m387,Hjl
$ekU<`EA9#)nUCCM);JQmUKbslD>cH+qT1cDMGIp1*s%k1D/(]Ar(&s2-hC@(XG"h9#FT>tfm
7@h!Rg#n=S!cPi:&!=U_9>;8s]A31,[GV6calL^8@HPg>:#,QYgf6d&Gj,8jYa1id]A#YsmQGp
aDCO5(m;HX"rgBbcK')+Jk,41A+=cY>FriFG#*b?B9-83nH:YFHNA^1BBH:L&^+BGTP)pR\j
)#;$a*?c.EQ\+D>ljJeUMfT6^c;8'/n8h;G.JpY^kTF\S:t5:orb:U0.=\<qg_cj5oYg(8]Ak
"[oFi4B)4gC^re'haae?+#fkr!NLF8&rLZo-*)=-n7(SC(1S]A)urqM"`?]A(!Z7ljc,P%du3!
YV/0B`m\slK.;/4rQP?`=c@L>lKO;2>sL`s$(^t#dmJ[eeHOO0ce/HiV2jV8$W*">nJ:$Z8.
6kl*V8*Qo,uFq:$u'6d2t;,q,h5j_A991RoQt_)XiO'EWsQ%,JWt_L'CP8*SQgk2b"J\=*n!
if@741"%Z:o@$/83Jr#ajd+X?a+eV^9<5p[q"@36m()`>c5U'm0$:/3a15&M$.]A>lA)1i,<@
i2R]A9U%h.^^)1g1;4F+YGJ\!oBtZ4[MF>i%f^?gp0N)qCOL!cSaUB\dQ4ng26"lZ.R2U>=m&
@^nRXdX&&c84INC^3Cqh#?/AYXaHq9_d4atLrHujCn\i!^_-(N^5ju$^R]A(nkVQD@na:,%J#
$p4%5+P2qW0VO5;c]Ap[UQm-5'cKD!"`9t3&E-63N8%VUp*#P7=Jq+utW#\69"5>l!N&oU=p;
O`T\2!nY.eDuRW6$8G2*K2(I13`[]AL+_jDk8$0$/iJHBsED8WF5;B^3lj&pJ/?X<R^ce2>U;
Se</m2ip2Hj)&^dspdLMqp3S<]AqGLSZbrKMD+n!FM`f3WGGiUfcF]A_#9[q\d6oAWR:P=ZCDM
Y;U;=%*F3WY:nYIOqDo.u;NS>lSB?-X1H4cUoX@rV2CnJ[#(^n()k;Si)%3Y:OC_%3;3G[P%
HrSZ"(66LV@>=/j8\dp-^H`!#[i!n4?olk'3en`Q-IoE=b#(<t!:K6,mKG!Af6Tq4^Ik/g$:
,!r[7kh_pVqoR)J`&&YZq<>R7B:9F`2Inq4q>'^R0XFhVdjc%_%5Q$hS732M8:$`L*`*P@eG
9]A"J"B\SDCZ@G9T'HW?_;CV/CL)5:t>&j1,D1-,AU&3;pmo1aa$?1-1jLLj*]A(Hg".,/p8r?
0FbfJC.JteCA,>JXLr_Z'PYm0?H$<]A&C?'Y%1eXbugNN;V!W'3W+DJLNH"GHAJh_$7q`1WMF
G`,VT@F1"<ibHm="fg;OUN#@@)')t&^IfOH9i(Jg:#8aldBoC7l(mb.5YRnpN$pL]AOpfF6#\
k9@]ANKpZKh=aB4dIW))g$55Wld(TeXjaHQ9]Ad,dZnBNNMLKS*M"KpC5dCc$,4Qb[#QNj:'A4
*7s\QT9s(.<p:;]ArM2hV1*e8.9u0UTFf`dMJ-T@=Ifsb:b3:EV$kU3<RlY`!>!4"78NPF(?;
(';^?*%.q>Rd_b=pFFPT5@_4X7r8H9n+&E+I#%o*<9Q$^s'-1=00e[X_#bqS:@[;3O[MG.Q/
=/T*AEnEa?313@LeDblZ>(V,E291\sTRK$D'qcQ/*lgE_jU+)W``u.;q8QpF]AH9,YDWX=nMo
hmh/p%cL<-tLhs4*;gM\&PuTnsV;UBJK=9jmS_52Jh_"/31[fBcCpJl%::Vo[pXuO745hG:A
k5hfZ>P.T2BJr\=cIaSPEU:!\#&0oC>U35]AP'K,mRa!Q0c-6dEu@@NJio"9b88*41,Iapcf#
A4\gG6+Ib&@,7+G0og!LR^r$Ef&aFIfo>ZrNdOTUY_N+"U#4ZE`7N!&%;hQLO4i%_p?)8r6'
EN`U4<C*d_EYd$=D@j53u[+$Lr6Gr`!rbprOMJ_mA+ZW@MlTLu,t$q=Wu87@$Ofq^ha1N^pc
i3``h4aa77QScH[Q+\(/+MkaQE?Fttq5*t1Cg%0(MJ$HP_M:upflK&"YVOKL#AMOj=G=^nlE
lm'%HTrmIq\,Mj;^;(!\]AW1_.'SGE`rQ6H5?9WWLSD`HkQa)fPO,@$pn9M/]A&-N%Wi\.='+5
J4+is[ki[2C15=/fH^eAecpHn9GR*a"RlGF&?j<GH>)LA7=/pA61*fuI)gJc!XeBUM@'mq#c
^SeY`UX]AgFCkYC:ju3<Z>h@F]A.Whqr/lbTW\d)52n;1A>de98H*Qr70<9%g"5P;[1A6`u+o3
miX7W]AXs\l\lD63]A*\I:1>*mL$t([54g%po5gKGD!kMFsFVUVQ.&;n)N:t@GQT*p_/38q:%T
OG:`QT7%YF1V0J2cb`*PBqi:o+gA'i[Oi!_L98JC'o$THg12o/1ZNr>3533G[1B5o(91ihb<
#pULCfpZ^]AO%tSL2fiW_ra*;4]AsO'aFn_2Os-YKhatVT=QFeD:AE@.gNY%9#'S/MK3ca8?'6
j7X5p0n1bj3`eFP1[Q-XEM7g!HqWsCOWg!cA!$M+amr0M:gT*j^=f00&6fU%7d^l#"r#U<S9
q>5$JaDe1W1N+ONV4bqDNVeRVdr%JdHeFID%Pq5=n*I:3Y2QX_Cs/@PJ.6*,,^[g2pH<P5%C
-Bs.dN/<N/Z!%MsSPY%Y6jL(d0gN0uKiP#taP=kk02Z5BHN)jiYSW8u#Fpg$Z/V2,/&I+*Nh
Ra%!JJ,_]AgAoW.&N?WjonqaR^2'2;(V4X7M,m!Mb'YcDf.IXCH06WdBYhaLo9[F=u#VTPuic
&"252Vk)KPq@+%V7It>TO<4=jUc;Ke?**kX>aLcc:K"W"k(&jV\]APt_k^qsPY6lo_kg=tf*l
?'86kY1e%]AtKnapgC5j#&:lZ/McAd66CV5qR@XA)lD9K%0U\5^=o^X^fFIc>[8S>;bH]ATqiC
Ee`"e<l*laQ/952X'7b^5#_ZKbB\lshT]Afe5M*(ZgeV-g>hUgf@]A/Gja):C:8ut\A?X($S`\
#1DCD5;Rmh(SEQraG!VdC^L9Qu=C#KueSOS&'h;pC86FrM?tS=CI84K"+Y$W'a0bp4Gj3)ak
ZV^u/<j#8""_92B!50i`@V\0`L)a?Z6>2.b&,%mM:LW(j2GVQS)AI`j+n(?mjW5u(\$4-6Ak
c4KR8nQn9"_Tt$2d4OJ4`JnH7;5E9lM,Q$L2r8LUTCQ3/PXOO?4$!ZRB#t*]ASh$>G3H2Ko#U
QW`@WJ'[os*2m:&.LHq9U8!g'>/=:mc_p`+e]AM6Lg%TZI:#RX'(!d@`"dl?^KU'<[$Rb]AhIc
9BCY^^aR?l<j!tnI%Z_T/%&TXn!M)-3)]A#4q_<#8e.kJAMToo52OF/U%CJRVk\ZYZ6J04lne
Ke,SWq@tjE9;uJ(80aBtcn\]A:H68%;'dE>]A+Oe7pBtoluIhb&K$Wi+Dca[]AD)SVItZb6p]AOp
]Ao^!6Xg0P^9b6d@=Q>I$GQK;H(\Q0PR[b#`/mneh!NT0Y6CRB"rSe*'ee.Bc[c+^:*#(n#-.
n#SIj@>r&jep%(Ag?es54*'.H)8KM<+:3<T*/FJq?jrG4=DIFd&]An3JFL?[CWh4H^FK>JG;F
[K7IB&"V<`HR9'I1?,GORPP*Q]A*Tu)5`]A-O5ra?G9__g9r%]AG[#"Y2^a8jnj/cCP^#DLQi]AP
mPOuF4dBCEN8O?C?+9Nt\]A?<6'\<qcWT^d7\*<fVX8^rD*A/bl=o%fK;iEq`f8-E<hRFNMBB
0=`i/!6Rg$/fUE`=UcR&K:KjcZ3MSl;8/g^q>>@X9J=2fu*&O\3*XWSV*5;a'u7Dt^k@3jD`
4`,]AUf1[^"N`u@lI`!r9piL<`-*]A(1(9n,DYI4Yhi%:@!]A*PL7ur:Q;+%)^[I!.MRbg]Aa:tm
T;R%P&gG?s$b)eQn+fR;hJ1pbhZB1p>?+J<_H[d=R7*X[Wb[QDSn5J=r-Ul/[H1Jo0Sj"'o;
m3So@lRDA2@.jXu/O),Hi-l!*hohksj:"3Nf_DlI0f3U#m^-ohPWN<6Y>.G?,u2/3.9/<lsU
TmdRC]A8\P`<,KXSJc;b"6IA$iTpr\Hs)6SG7=KMLC9WZZ=%KmX-0"=QX0a>S7Ts^7(2i`%bb
=!03s/D(+]A5lIZGRMn^,q+?/Z-bSgs^s%Pmq?#=^>79daIa(d]Ak[4QF<1^1`A!a6H_GKd$D9
eElG6.cMU`@cq[9\b;5A5q`<`=_PpFX3KV2'$c>H`V9QO8edoL,KR]A`+lD==FA-(P0m?\J/@
]A)2$+5g'QD8EeGW.rEQ6`OT0SX*`8#F.$3#trk(P1csLha_C4CPRL-'RG6ZofMB`(\]A.50&U
sC]AkCZZC7'ggjtb/@hn[%?Lb_8W40jhMIs"OqEu\pZ6(0NT>S=#MBP&B`UH#[/V]A.#EN%ALe
c;Q]A?d!O@.G)_r\4nf1rZDnS1.R]AUf5&rSs%!hEd@c+<1q[Zo$XPD/!]Apa^dYla?;HGPM/IW
l62f$kou3)%Nmr4MHC6mJm9*nohU(\7Q[X*7!IH:rN+$4e_3UBs&e'c=-f;AUo?0,3g3i_fG
RY$b@@eKU@LjO94lrE@o)\Z*k4#G*caEg%t,Yo6gAU;DF<biYZc<aLWkm_POYIQ=B.\h(-4b
t<HT_De?%i=\P4Yl?:9lmO>Dl154$3b$(?$Bp@J<ACuKYRtC2%M%@;H8I99IE_t`=4int+ra
fKm:Bsa;qqTDU[cI-I`@7@Ib$]AJE09c1=6oUi"#YO+Irq<S/O;hlf=MEKJMo=ED#V(RY%Y&t
g)2:h!)UuNe0Q+`$K;[8hqP:&&O`[K5T@jscO$&V(LF]Aba'#I]Ae(/sERn!Y1,1Ul<r[3[3ng
4p,EGk_j0C2n;l3Mip5gALfBl@',>da&>Ml#/=iO1GR^bt"8K.H?r$7<ST>?eub+SC.kom5T
h2pfQ1>A5Z![GV!\gOJ3H>YlqM@W,Co0u;W%d;Y]A]Aa!:UNF36B4+63!ZHsp)I!\cih5KL7[e
\)EmQ@WG@JY)%E.Xo4(;K5:!:+57f,P0IT.rP8M9Nu_U@WLAle@MYV+\6%s4iD`k5;.u1e\n
A?*]A![/DK?L_$d0.Zo1N$]A)(8_[KjA=#d-`lr=<UT@1.3H^QIJ^[)N'UWr^@*%9J74W?lYoX
;l(-fc<)8C0a&\cE5+VO!F+h?jf]AbETe>H]AH;Y7Di32@RU,$1UA:BBqjkmdtCcUAO>76@iim
57)16OVK<)Hm,1!Tu>9(C\Kgkne,L;q&h#;n6e0KfG-bPd'B<Agh9+(?Lr;8ZAR+ZD_-\DoS
uBh=+tPt;%=%C/3`hpRf6cF(3Kp"T`q*V\,A?M!'Pj#((h7@@#;5\%%"/X)^pZ!F.:id?N"[
dr3mp*r)iOrXkdD_W)T1h;nKh_j+>UJ^_rnrSdL>tTKfR`6km/Dr6q-`U*(13[55lCb'LK-0
EJh4D0AG"%Cc[bVd\ngEN"O%Q-3lV@I&AZ"CNPgk9kO.rh8D;;e51@5Jc\c<cc)XRBGBt/m?
?$Y&W1T9/MbAS#Vqjpbj`@c654A]A.&H[HrBlQ!4mBPtlSq0f5\s0RkF"8PI<,EU5j,H7c=Xa
et(![aHqS9rk:m=0U<J(@FG!au"2*%o[#fo6u3QbddX^IL=bE4A5!bmM]AM("F17=j./pmF=>
U1Z3<RFA_N%(Gcf2:T(rmSDucRbT(+0]A8;Q"3PKte,]AtAkAlYZJ$=]A0P22l/Q+)3f.S13^6e
ZGiKFsX@hSlT`$fYi-`rF,T;+;d3#ARgWZM@ja_q7@T8h42P9"q*d"*Yl1-fW.C\mUd$B"MV
2)6W)QXaaDUOeG%9&`=AJ7p'7)K#IeacPl^4hp/F&!X_-iB]AV-e(<Ttk3US0JlV?kS!LUm=c
6_R<GND.XC,5l+'cr=+AbPSb`Y54`2U0;NSPSTI9VL^Y7kH$?Le\N7+!J%jJN%o\a!NUa$'F
"_rK)%ofQj-??;$[$%$d!^I=LetFAj]AHK8)cEb8CRbIriq0ul44AKo[tZs&%ut/k.paFnVd-
s<RILjM/Je@B8;]A9Od[AJ%p>'hO;`i$lNS]A`B[3DSLVR*<pP4R38l@[_CAQJu@=8:FcEqk`H
J%0Nq/<LZI5dWYM`,_*%F#YlDP@;2'".P6]A0NhBko<da[M=I-B`:+:SnnT;@R1F:&Q_(4F=)
q]AT_=q;@P]AEkh'cW4lMKt-U`93;K0]AgZ#IhI68`RQW)G+1D;C/6#cH-<6r.r.DF!Fb@nb+Bb
b,:p03f(%9#<2CA!%$\,A?HIW-j#.%;8,.Ts,8$*O^h/Eq0s#jo`Ahc`WsXR!@PdR-<=e9cC
A:#U_CS7^)&_tW'17id1;6H/I/n;1Y?]AX=76s2l-R:Q^rqYH6KuBqMd_9k0q.<r2UF'",Zt+
"),13dj?G)`i#"`UaN/dibQ;oUJZgAS28H3#bl%0]A+#bS^Ra6!QT8iu)/XKurW.6nJ"EVYF\
]A&n-d`uo)U[.&$2s2;p&1@F#R*2*Z'ZuM&lbFd4>Mhg/ln*mTY#,a/jc!n)3#+7SW1ZsJBWf
?Xh$F8rp67gX&VY!(>)jI&cb?(ICb"g`(7E`>JMFOI=@1BP\KdaeHPo>s3;S]ArM!PYj,C=tr
:l9t)Bk"Fe*%9;MkV@u]A#AlunW18ArBfOk*Jn$;n-\JlT<:mN2?-^udF#=57g-?c^PMrAN!n
[eJK#]A+8'Y6tG=s/1X;E/RV;]Anb1[Mk(h+Mis4l>V\&@[7?'XcD%R`($ip!T1:$XS0L"JH\q
c170mP9&1YT%O[pjW#TdMa?)G=?3]A7An=TlE?:j#S!I.WoE4@QV3K0669EJ#UJk#Q*.Zik1#
Wl'2(XG<%#'qsH0q8uI-A)=^Ki:LFU)!r0nDLQ3(G4-/#i`_;-U0*-MB/jm<4%;'>FW>_NSM
)Z.I,fK6;7rLh5;M.:P<nO/h3.1TlQ.UpG6;b`Ds;>#K!d5Agk3a3nV"EnoRbQ4r51dZbb+d
/4*2W"AD+]A84`'^&Q;Z0.Qc"/N`6A=O6$V,H!]ARLbqW&AOI$Jh-@h.d9PQ:"qVZc7!`OiM5-
VoG19CIIY[UN7>4]A;.+WS[nirqn%o[>e#4,7%VE_kY[I/Pm=5"N=AOXl*8+nS#.@*B?Wa&\A
=%gl,AhRPTL"X3h#ArDY1gQpmR1'"!MY`3N2I]ANp\I*S04R3k51`rY/k+ZDrj<?J7LiV#g:q
At7^Wi#p#B>@>!B9Z/'G%6;k^i,j)P52'Pg`rK":*/uGYA+0MM&.$!]Aj]A75em,r&[=!</9'r
*dAC?fu+p#<@N%tUP!;VBCZ7[sKc!`oLD$$]A0Ei,Z=8EUDt&MP"`h55-g)c@EY&\u0r2dIgp
G6@sIgPCA5M$>]AE0cZt5#XksedKs;FKttE;LJM7af)>>(aW5%?Sg19LH:74D/.-jToV8ql-p
U`#=5]A*'q8`7U#X\X$Pmk)25F_nV=:jt@DUN#3O62Co+`LF&s)ceg:"r#XTS7_AJc36u$J=H
J-N'(0\mm`'b:4@uA"\&<@);gU_>aT~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="108"/>
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
<![CDATA[1143000,1714500,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,8640000,990600,2743200,8640000,1028700,2743200,7920000,2514600,1333500,7920000,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" rs="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="1">
<O>
<![CDATA[参与活动人数]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<PrivilegeControl/>
</C>
<C c="3" r="0" rs="2" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[新客人数]]></O>
<PrivilegeControl/>
</C>
<C c="5" r="0" s="0">
<PrivilegeControl/>
</C>
<C c="6" r="0" rs="2" s="0">
<PrivilegeControl/>
</C>
<C c="7" r="0" s="0">
<O>
<![CDATA[购买奶粉罐数]]></O>
<PrivilegeControl/>
</C>
<C c="8" r="0" s="0">
<PrivilegeControl/>
</C>
<C c="9" r="0" rs="2" s="0">
<PrivilegeControl/>
</C>
<C c="10" r="0" s="0">
<O>
<![CDATA[购买奶粉金额]]></O>
<PrivilegeControl/>
</C>
<C c="1" r="1" s="2">
<O>
<![CDATA[Number of participating activities]]></O>
<PrivilegeControl/>
</C>
<C c="2" r="1" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="1" s="2">
<O>
<![CDATA[Number of new customers]]></O>
<PrivilegeControl/>
</C>
<C c="5" r="1" s="0">
<PrivilegeControl/>
</C>
<C c="7" r="1" s="2">
<O>
<![CDATA[Number of powder cans]]></O>
<PrivilegeControl/>
</C>
<C c="8" r="1" s="2">
<PrivilegeControl/>
</C>
<C c="10" r="1" s="2">
<O>
<![CDATA[Purchase amount of milk powder]]></O>
<PrivilegeControl/>
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
<FRFont name="SimSun" style="0" size="72" foreground="-263173"/>
<Background name="ColorBackground" color="-14669005"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-263173"/>
<Background name="ColorBackground" color="-14669005"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-9470325"/>
<Background name="ColorBackground" color="-14669005"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="108"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="report1"/>
<Widget widgetName="report5"/>
<Widget widgetName="report3"/>
<Widget widgetName="report2"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="539"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="8cf5935c-f059-4005-988c-8b75842ce14f"/>
</TemplateIdAttMark>
</Form>
