<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="File1" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[开始时间,,.,,结束时间,,.,,里程碑时间,,.,,任务,,.,,模块,,.,,进度]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String">
<![CDATA[Q'>!<__;XUf?PhT5cV)[\-]A&@FFada.Sp\7;F,M4[31$R&3lM\K6muh5TTkuSQ>;r2"/QXq2
H7g&Hf/]A$,C8%#lCgQUoTXFI8N/"X/F@k@nE]A^6I,$XDZuhsmM<M=)nImB6V>kn9<B!#nj/7
!&[/(PeKlrpN8)F76;F]An/IY%#Fm`ua%!D5hV:N-HAtiUtgPb=YH`kq4Q5S.]A6&'o@*\d$r'
+@5JMuccq[0$iC,XBtYj:H%e6d=)mYc_Pm&o#Tmefd<`1,7`j)&l+CVuj$Y=W'/;%)TP*j]A8
&1\286BUKsr#5`*`DS<q6TMf(FhPO^CJ3Lc/X3;$MIE*sT>0"*lSi6T@8k2fC`o`k5=S(`u6
&[AOF#70YMTX&Kgo"GP_NDJNsN3qkLj7EGsXY6NOorM&.q[3~
]]></RowData>
</TableData>
</TableDataMap>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false"/>
</FormMobileAttr>
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    $('.timeaxisGroup-inner-body-back line').css('stroke', 'unset');
}, 100);
setTimeout(function() {
    $(".parameter-container-collapseimg-up").bind("click", function() {
        setTimeout(function() {
    $('.timeaxisGroup-inner-body-back line').css('stroke', 'unset');
}, 100);
    });
    $(".parameter-container-collapseimg-down").bind("click", function() {
       setTimeout(function() {
    $('.timeaxisGroup-inner-body-back line').css('stroke', 'unset');
}, 100); 
    });
}, 100);]]></Content>
</JavaScript>
</Listener>
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
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
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
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<Refresh class="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr" pluginID="com.fr.plugin.reportRefresh.v10">
<Refresh state="0" interval="0.0" customClass="false"/>
</Refresh>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,6286500,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,12725400,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="9" rs="16">
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
<Background name="ColorBackground" color="-14801862"/>
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
<![CDATA[深色背景甘特图]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="160" foreground="-5723992"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="2"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.gantt.VanChartGanttPlot">
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
<Attr class="com.fr.plugin.chart.gantt.attr.AttrGanttLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.gantt.attr.AttrGanttLabelDetail">
<Attr showLine="false" autoAdjust="false" position="4" isCustom="false"/>
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
<AttrTooltipProcessesFormat>
<Attr enable="false"/>
</AttrTooltipProcessesFormat>
<AttrTooltipStartTimeFormat>
<Attr enable="false"/>
</AttrTooltipStartTimeFormat>
<AttrTooltipFinishTimeFormat>
<Attr enable="false"/>
</AttrTooltipFinishTimeFormat>
<AttrTooltipDurationFormat>
<Attr enable="true" format="day"/>
</AttrTooltipDurationFormat>
<AttrTooltipProgressFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipProgressFormat>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.gantt.attr.AttrGanttTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="true" showMutiSeries="false" isCustom="false"/>
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
<AttrTooltipProcessesFormat>
<Attr enable="true"/>
</AttrTooltipProcessesFormat>
<AttrTooltipStartTimeFormat>
<Attr enable="true"/>
</AttrTooltipStartTimeFormat>
<AttrTooltipFinishTimeFormat>
<Attr enable="true"/>
</AttrTooltipFinishTimeFormat>
<AttrTooltipDurationFormat>
<Attr enable="true" format="day"/>
</AttrTooltipDurationFormat>
<AttrTooltipProgressFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipProgressFormat>
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
<Attr isCommon="true" markerType="StarFilledMarker" radius="4.5" width="30.0" height="30.0" color="-13691"/>
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
<Attr position="1" visible="true"/>
<FRFont name="Microsoft YaHei UI" style="0" size="80" foreground="-7101768"/>
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
<OColor colvalue="-814717"/>
<OColor colvalue="-10244375"/>
<OColor colvalue="-10895670"/>
<OColor colvalue="-866690"/>
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
<VanChartGanttPlotAttr seriesnewlineenable="false" linecolor="-7697782" linewidth="1"/>
<GanttProcessAxis>
<GanttProcessAttr customproportion="false" proportion="30.0"/>
<verticalheaderstyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="100.0" backgroundcolor="-15394264"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-7101768"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</verticalheaderstyle>
<horizontalheaderstyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="100.0" backgroundcolor="-15394264"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-7101768"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</horizontalheaderstyle>
<bodystyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="20.0" backgroundcolor="-3552823"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="黑体" style="0" size="64" foreground="-4144960"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</bodystyle>
</GanttProcessAxis>
<GanttTimeAxis>
<GanttTimeAttr timezoom="true" zoomlevel="0" weekendtooltip="true"/>
<upheaderstyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="100.0" backgroundcolor="-15394264"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-7101768"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</upheaderstyle>
<downheaderstyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="100.0" backgroundcolor="-15394264"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-7101768"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</downheaderstyle>
</GanttTimeAxis>
</Plot>
<ChartDefinition>
<VanGanttDefinition>
<dataDefinition class="com.fr.plugin.chart.gantt.data.VanGanttTableDefinition">
<VanGanttTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<GanttAttr firstprocess="模块" seriesname="任务" starttime="开始时间" endtime="结束时间" marktime="里程碑时间" progress="进度" linkid=""/>
<processList/>
</VanGanttTableDefinition>
</dataDefinition>
<linkDefinition class="com.fr.plugin.chart.gantt.data.VanGanttLinkTableDefinition">
<FilterDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<Attr startid="" endid="" linktype=""/>
</FilterDefinition>
</linkDefinition>
</VanGanttDefinition>
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[mCW[';q]Ak`IM+mW'gtnuPUNJ<70`s08Sl=;?pLq/`YeqKN$=(B'_Nq:#T:UdJ<21S#`)>l;F
5J)YY]Aq%-nS%=#Q^)"83>^#&4B67mbI\hhuEPe=c;D4S?pPJTDb#3;teKsj6VF:V`d`n!.]A(
Teunua!7]A*j!'dSC$G*"?i7L!hfGD;?oa81'=$P2k&+=0XZj'1-i[V]A;Q*]AXRqnLt[ij^(Cj
b.\=g9UmaRH[FJ[mbelr0t[gnGG;7lXHS!rNP_ToA+-8mHhcPh;b*pV*-;hm,GGuZ.:4Vj,&
G"cUe`<FkmEp):rp>lur^Nj_`0j"FIq\iSk(Vq-VTl6>[kH-ukCk.W6a^1h:+fH3foYYQk??
K%g]A*^ci<8,o$Ak+ZtF>s#lDY5L=g<Im>"l07Ir/H5,d0(OBHfB8Z(k7!>OM4;%ig-j\MDbU
1JN6%@:ZnonNcm?#@:XiR);ds0ffkReLX@c6M[lGGrE@+RdtnD[e^E#44Vf*Ds&ShUCls%+&
tYd[2!)Xo@9m?SD"58g691;r?-^#+RnSHJZ_q$CTQ,*AC@BD.<1Y0]A!ug_?&.]Adp\3c_#c2Z
M(1g\/$)["K&_(S[W:89q>Fs)!uVSiVV*@9A"IFd^QR#Rh&EO@ippnq2699Y6#Ncm-(a:C:<
61WE6\_#eIl/GQeK%ha";UN5#YJ]A6s.RBm4Q<hn$&2_S,V`&e1=k;Qj]A(_noaR0TS-f,=l@k
%p%B0h]Ap=H?aP/lf9mu:4=FL`'j\/f5IZk!l(@"@=Es2p<>eC,AomESajFpV1%ch9L*CuY(E
/h*=)1,44'kBE*8<.<'+kiUq1C=\$%"pB=1Q,(^soc#HUFhZkr/D`HsX14(p@e7W=LGEC.ru
\CJBM>b6<jhGZRGSWt,K.G<4I-RO3bI*Mp1`2@%TE]A;!LIC;$MNY6[NsfsUFg`C2d>CbGF3&
t\IKAtB6ZVW&QTi@W:DR;D?D@+TQBiU_4;RsK"8UJ9Qr6?1Hf68+O#204,2A!bo_0.GtAEmD
:Ci)OUp'(-tg$&dnTYn&MMSeF?(^**3CD^>,@r%9&FLh'd>#:U24G9Dqibd4NJLU/F[5$g')
]A@J>1cJg2_rBp4JSY2AebOt')kqfrSO@sGF(),r)Me-rg6j^'X]A<\u7"2Z*sd$>\r^r=@_.B
$OheeYuIO"@0P.V>"EPRY=o:6;[Cidrh"1Cb<u'eg/:JQ<Vhe3]A^JW"3,0DFM#/5JWT_ID;^
f%;\Z?4+0C3KFY!Wr=*8L2J'VZkqA>'Dub-mjNWHPC0llfiM@h.!GO(@'>b@PBXkBN4\FZpc
nJYM?b<,G!nGiO'-K79[S2D2,/>ppLLgVhac.!"*L^jEBQWFUj`6A1rsm`N9ZlsWD7>+X7RO
KtR/e,WGGh."gH*,gNIbe09*nXsoo5QGJ-^r92#"1[VU)(p_;0Zo(W@B9'hMM-1+sVIe_tKq
:,ENI0>1XeRIoEF^[L`NAoTqb^n+J#9WHcSaRCE2f&IoG)AC3BV?c45AdW=#?J\k")n-alOd
:5ked4\iX/.I%<T:fkWporI4,`cDkN$M,/.i?PhtEl>p\@ijC<kiT/e>M[)!Wml['e.g)X&I
0>2Y`t<LFSN"Xr&\'6?p5fj<2P!5ObJ@_dtF?2HP"L'$#!0um+/1[?>]AZL6q*h6*t4:&?tFc
in`T<SgZWjc#pjcE.G_%UNT_F*70ES%$U:f@MC^LZIl_OA1?.?H_`p[g3%6XqdpO,!LtN%sR
__M!!-LFUQ?0:(=]A225+RVr7eo=pAQhHfg`h6;e"/=klu>Uj5Z1++2PLKqkJ`Q(nhegYuTSV
$"P'>6rT3:R"I,MgC/o#gXo[]A1<9Zm8VRB7Gm/BWa>MX(bc/brjD1Fm-7:F#<>S`jiA(aL&m
4lNq7iU_Qif-Tdrk8!V1#rN[unl*??YhrC\P\nN#RXDUHS1Gf5]A4H;"Q7`VG8CtG\8P9[&4U
n'((%,lf]A]AiL<S#'!`U;'R6Xlg^`&#^e77Pj>pjNJjrFMD%NfRYF@a?*J8(Zu:#j\.>2$14Q
C<'.Q&\/j4,X-sjeSZ<FS@KW']Ai0MRE%%s/8CPXZr+]A8,08q=nL^:`lEk5fCkL59?Tuf4iHa
d@))_T=5;lu&/5be(KUN7sM?I`Y1Zg*<>3=RE'KhDa]A<UulT"VXT%BXU6/n;)c[6I)HWbV_=
-M2C[:]AS,h`.,Jj^pr`tps:fOWtpQbmnUiS%92i,'9T7'=o!a\^cq$N4im:4bogY2AiflGQ+
V@8Csr+rl=k%P>@%T=LAM+ofI,'=8>qucm9T$)M=s^OZl_Wc='r7hIpG&>d(m9-EIBf,GZnB
i@UlDU&;QVA-oc*[M?,#1m_*Oh+m6]Ai!L>MI(XJbf-^(7Sr]AFW'*WB]A+j+Al1FX62hd<!c\(
:_<=*"=B,+jW-_VhQ+1_1bCh122kJ;5dfj,QBMP#Lcdd'f4D*=4)CKo9E=E#rqHZ'SOfq:ct
8X^%r$bMjHsS#9OHVc$D_uZWi&?eQcRu?shd<fScgW4cRYUA):<8,+d\-EXfQMcsL_^A3(qB
46(8g,p5[UJ+r%Aa/_*4c[Dk?%[d8c3%0ogS'r51*]A;]A$#&G[c;UUjtT##dj^cT$K_q`]A3Wj
i,\;8CG"79tL-*h>H1O0)EtMnXICY*?*&a:ReE]Ad`%Wc5"7eG;e+ZhO8aa`p5@!F<]A;M9DXc
Gbra0/`g=k,5IsE9p:sL`@M#C_V;j,M>[-X?5gA1b02C8Bf7rq3X`^8=Bu_'41!.hm3`[35J
SBhgkO'KU]Am$>'B&@?lB\Afm)j-ZhIBL."1BHFQS71p8J;aVh0S_a^4Qinh[a?.3J]Ad\V4gQ
Q(?eG?c$0ZU36/&Z6X2UVjP='/%Siom%4?6)??&N4U^f_7fH5k-iV86;[jtPp"h*%NHmp7AJ
0DD]Adq1SEii=m;[A(Jg).MC$1.h.;[kHZo_?R%B+PBYOfL^E=YVXM"2G@sSIhDd&;"ikKO.P
Nkr9:V2+VXWC?fB$LP\YXS+31i=dX0[XWPn`WH<NLa-_G(S$drl.fO*UUXnpOg9-3FMk2XQ]A
RljW-(*lL;6k`PoPfmD'\9O3c/A$IH#b58%Wq9Hj.b(;\_%D/OSgA7GujY!-LU4<_X`]A`DjC
MTD0'Qd_AXeBK!)G#i)`E*Ko`.RuB9"Ep9(l$`RR^Bbh8#'!N^Ubee#.bQMMtjng'=KP6:G/
<HUDJ]A]A?FtCef[bnR^9tZpp8IlF8USfu+G:lcgDn;L,(+PcRS2'niL*1JcsN5hk1(IbA(CVe
hUXn%'o<5F,,mf1nB`39=Yq6]AM`GWGrF)*jn5Ba]A]A/M_CW>b(bjnoj&H4_-?CB4@26e<Lp8E
Y29(O#D-#lcCJ.)\%,*9")tXWcgC#U-Q>UjeT-f@H<+&=7#N8N..=YJ$,bs5OBiABY'*Z2"V
QKTUVh5).<EIl5eu'4Nil[D`4IKtOj]Ac-/=GCcX+4A2PV2I-"L<8-$hhPO;35Bmj0REP//Z/
39o)C2R.^YDd"gn?HD!4WQtL`qC?Bk&3ZKJ]A9L`%XQli?.aX/!t3l?;YV50RjY@_EoQN/A1,
^@M%EX'O2%=?j9n*3JhCYi%a?"?4UD^IA=aGu#<s^]Ac9BpnXK))RQIa6CV`/D<c6.?bnu>I>
e5QIWffus78(KWFoBPP3n&<Jbrq\O\UOV.*1'2"T^>VEU&1P#EJoJi17G5eLm6?gejK2Bk79
#hm11M=0q]At]AXE<+_5roAhJ$[PK,.D"_6beVa?94o;)0Pj0N:WL*%`^"P-U8t\@<iof0.!lk
5,?@p8^&a$%YAB)(kkle%qWC4P?b['Vl/`._3-e;P<pCE!@/@au5^n1rZ,O(-P'a^JEB8.Is
!ea:J=1+inJ4nf\$Ajl'7%WVqAg*13X'#*,,A-]A8<`K(X0J*i-a#-ZUTcF6;=ZoqKZ&VV38+
,qZp.\h8f%L^_"l7s+;s:8nN,VE#%CD.26\3d%P3g8&a,:CoFUSs:0m+%7/kZn0!YZ`Cm*Ed
iYJ@#FBm?@3sq#ij?H;k9pF_T=j:UC'nI>hGL86hnIg<4PDE4@,^cB(kDWd`q4d)G=Hr<6FZ
ssPbblT"RtHjSm^97Tof=8k3dHRT,-F(KGH*fFo$I:43&Me,8'9lB4;U]A]Ap`oB]A3dZG/NSFZ
\pdMk>er\"&n<f>brtJiio!W<c__0;%>EHG9Pf#]AZDgEI4a_Sq"A)RU!oXgA4j$+WF;8_++r
]AmMUn;l;Fi&(WAmU=Pu@6XGL^E)QTdrTY[hoao%bUO+QIWI9<C26LFE@d>_e5JZBS26!`"#H
tY95I,:=mPB<AJp`Pqdne]A!+9X<Y^nA1(e;afg)h=*PBFO;\4G+%X.U"tQgNGH7$csAIsqM'
oP.Y;2LdI[8OQ3s]Aou=>iQe?;]A1K=Jb]AfeW>1X#K2-O[#l+WdJm4pP5d9]AkS6Ci=6IdU]Aq65
YT:>J4Ltbs-`/-:s\u4YFnK>umD-*p1/VN9u!UoHMH&.u'BR-.@LZ)?%5&GP,KNMRfFn2N8[
1frZ2VeU29/B[NG"Ank09m'G==+P7_7@WG6@4>-]A]As"\[T)3B@'@7Qj]AqS)ccYeC_QC?LAVm
11fg9_a_jIKkAL!67,4rHuH4+@#k.2t&nqqppOB:;+k=V1@E%OQuu0fb]AESZ[G$A#t*Nclmu
)2^%oPRqBM-(8<.U1+,2S"nr#3h.F:5pE6pIoI5;m%3&YM^dTq1`R-)J*F(fi>J,rP[%L"S0
.&6keVLRp[$-nE'/56FMPS0;rVij&B[pt#-/]AFYX4`k<D.QD2Dijr!g-!qSJECd6iQ1CJ8>V
7J$,/IRVHp_n3_qaRppb2BLXotk8jM9`N6]A;i+!alPr%c<+AA%d>>&Ptuah1mE6KafC9M1Ae
R!iAD8Pr]Ajl#T>^/$/QM0G%H/:<cU\T*YfelmE+@*[M*g<NBer0KC'f.U'7m*70C>!r.t3'C
i`pB(%<?U,HVP'kQ`3Nca@ZWN0;_\8W"dKNcXT@X_MCg0`9a'@kF$4lb%1E)9dl2B:Dba&2<
L=[kVNj7XR%eFnpgCm%F2Q9gEPtp(t-a`GHq%bW0;l8n7nn_#/a9F.E3+P\X4>F,c_aJ>_b,
53R]A#T3fl1QU<f9TCL(^fE%db@[*]A#Q'-('o4nL$8Sf=[DH)o_fkOXd*\]AIj'"4RpHQ#KZ+>
Q?g[DaC6?i7Qdn'4?U1K"-[&e]AFY*AY,6+aQ=Ka[c6)SH;JpdWfEh0*3G'E<A1[&%r]A8<h?_
Pc!i(e"NI&1'QLNo]A-5jg/RD5,Nro;<dOl%tZ8d*iDj>I_Gcrf%Su%m^oZ[6F7YA^As/Z2e.
,:bMaGoNk01RP-be"<siP?!<<1,d#J__5>T$oJN!ad4RYs;Fr=/Y,T2&jpqQ9JM?LqrIl^(l
Q75Z;;Ic83"a)3Q-WW\(]A44')s,g('p&@/timQ8Afs`clrT6]A2)s@kkdqfT1d#?mZGO*tBkU
?'"?kdmELDiU%J)>.JT7)1Y[EI_QK=bYM)m?fGkGKT_&>J1X&'@c,3/GgrHXIPk28o':65)Z
a/QAMg)qI2bj/(Q5'rMD7AHa9[qWb7Q^?C]A`DEUp-iinaO*)$pCm0HPI5OWjU2tTi+"$W4I`
$=m_oE,12-!/Lq0I_+LEpM.Ci&+F@=mZPQTt&=4a,;W)$M(*_B[or]AiX/<GIljHH@ds,r%A>
<=)^lnEq8C,U`C]AYX?YXm_lp;+7O1D-VkNRAD^8JPsaQG1:4e?ai&se^*S(DupmnZ.dro9*(
/(d+h_KA))K5a;&JkDE*U?'+=hNN?27dbXZ/>S25Y44lCL_J2@oc@HWB/B%GSe8D#egQ,PnB
-4F,P+=CGuB0a%n&,&5!;gWu]A.M7GrM%Q8'>`6b27ZG,Nl=r$o,G77jFT41(S[Pe1#kqXe,f
hrfROOQfd4lU0dt)4mC?jF<&Kpic:ls5&g#T.PLF3un&N!OmK5OLr8RP/olqs6)03;CL4af2
@8WguEYJr\r"71KV1t[u"B%X%8I$E`klTO4DG1:@[pk[FRi3u<AlIR/QfO5<+*`?siN1Q"0-
U$ZRgi(JW<em*FlAu9Q)TWc@dj`*/mF(1.`Dl@@6T1b4Nu"Y%6r6$!9h&VV,IKT=7^'TaU5E
peno^Nfku^#q3aol<d4/i6JhK"NS2)$qAg(YHS2u,?bht$h/T`1\(9jN^O7[j,q'r?eG1)[C
EA31$m3KeX+JQV=j4.jPEp9\$g=W=#J.Ib_$Z6c7hCIDS6A[H6?R4dH.=3VkC6a%Vs.-OZ#H
pR'b'(_X^1$?[m3mBuk1K)I0Z:+]A=!4.ePHK86N>)=+9&`6/r]ACe)l>ln498p#U(`kSH'[1B
fG<bi_NVrZRB8)UEK2^DGWe&mfmG#+>^fbu9QM8BLaZS%L#=g`c5!VF-FB<d74]AA8<Hd<aC3
UE4oe=8*9$RZuoV\RMZ*plN"(OX<o'Z8-=JFoh(csq-P*&YnKf[f5;K!Y/9!Kas+UusuTW+h
i)Zr6qL$35DFn!:$DIJkIr[aRhh<Ll$^]AJ;/59VfR1QBC>s98*d=QX]A#q.FY%2GrEF:\VIIa
FN?Np\B$uC"@:6'n=E<+fpT4WNca>/DP+Zcm++i+meK*omKa=1R=uV1?7OJ%Ec#hrGWUcu(L
C5lpVYPgU`uA5=(;+/#afL,5WSVJmY[eb=)8mWIn+-P,Ml8gg`a#;&k6]A9LFls4p>s(F2FSN
*%;W!U05mrg+b]A,4&]AL!&C89767E]AF#cseBK)@:qTFjP\@VdRg,CWAQ9TMs!/cP3lqGF\?tF
P"(Cla&8K$h_UroPt<`]A9)(r,`FTQ/Itc5b6%hBO48tkHWnF4\[!1N>&g#DdW`$JLHDGCICF
78mh-L3KKQO71eGQdhYf#43sr.Xl<ST&-=W1X^oljO$ep$QBIDc?JD9tNY7>LjcN4+e([N:f
7Y2=bd6XQ?D"ZY4Bn:e5aqTMJ\XJ[L3f,KS.`mCchjnZgHf?T)3Y5/FbKm7%MjLRf!_2To5d
<bAR#sUjePgo?C=feu&!oo5Y#S9JUq(LR"+Za>Ulu"b-/hf3M7s7q4%/br`<>k%lV2nN@pl3
ABq`TH%TLq`LY\,qT!X4B','`^>4BHT2eF0qaVE"'os+7\Il)@E5d9sO1)'RB/1brE3GN7uI
i7r-TK9TtTdm#8k>W!!++HY=r9P]A5HE[1h;sOHZM$a!C4/qrS&$inNiRC"UbYa_(WAp#Nq8J
?e`HsTmUP"5VnP5A<:V=5U%X/a4DTp_l(C:V/Wa87"odGZ5N'5rD'RZC'#c`ODa=Is+]Ai`,C
;E)+,cu9PE"#F01m?Y+@;"R,!p4`ed`Vf_A!1.E(e(5q.F!_BldVhBs09f(8E!2+/'[0=?&%
cC3lr_ppT9I$M7A#.4dHlh_\_?/MIb>'9!dRSt.cC/>q"X4GElO\1O%.`\*feM_?oU3JTL;!
QCmus(6H?50Y$3BPngPc^,2ilFZPk)tX-Q##eTX*qY.TnglGtCAcRU,Vmg_K98"F8>+RHV47
t1DqgR.NJ(>(2hp&#.PdoY8fl@QXEGH6j+rL"kb#GH&+V33g4\5OjZT6a7bq&D0YF<L:1(J,
(\Ab#,o"g`a/2f4NpA_\cV&O"$PflV.&?s?m6oJ-5S0ft;LTPru0g1l-#]ATV]A.ZWFGT.?E!Q
<?.o2RK>rLAW<4L5fEelc\c\=\ToIc$rTQmjOu'FBns\RPH[n2c$bYA$F_d1V.Y@$iro'c3,
@gf';0TCp$r)4F@^t[5Uon;I4Xb[Ug64!P[%]A`3jr=sF*Str)>EMOE)oX9_E/?PW)"tsW.eQ
^6jFL-Mq+?>Uf?.Q!B6)V-fVgh_L<r0157QL%?O9nJNn!LMui_ZRCSlYB_Uau1WGX74.>;#P
V]AG<6jF+0F,hXTQ]AS_QX%3U-$"/r/XDQ4OWNXIRBRH'U/XQtQS:GJ>X&?DE_L+7%,.nAQ\q<
DRM]A+\SPKq$J=#^]A*ZH-=QK?-tuRtSD74"q+DKdmW%]A*J_W[S:[<YlVt&VbjoH$*iVhmtEV+
EF!fj&HQ8W;spq!I=L,_LoTnnVrn?(LtD<"p%Boe2q_)D4+A6ToZen>E?la;L@g:)go<1E+I
,r86C&`Wl1JempP#Sh4luL7qNc"u@T7'5)s6eAX_f<q$qiSGpb^ceTaf179B(.jL&#7=iiL)
s6;H!sMdgCV4!`U0UZ@<B,6[cZmtkP0ST\[VAMcGk'sNZM@WFBhZ#HZEc>4HB6Q5$uOmKFhL
asrcJfB5W\e#>BE58c2e/'is-CVHtbJ>6;G&/iVcM7T=o;_jaFXVIV*,_W=g$)lgFo&`d\nS
"X/Q^0t!_pE:TISar]A+4]AeJR*1V&pWGkXDPSOHilEI4?7.1HZGMS42""4=1`@Le%18XB`3uG
+'[,hL*;=4-%eB!gP+0MiN@<pf1/Q;?Debrj5@[\J5$]Ak[H>+4naO7J>pGmQ7Q>J3a/Z%l8u
dQESs8(Fjs9[r[eLCJasrM$pSFQ/K\'hs^^$2ki?N.e$0AQ0JWSDDoY9D:/KRYWNkfOPNU,.
d:LD724n'SM,D0gffF=".ge'%P/6g'1cPT3j(PAhNg#B/t8<,>brtR@.W@p,hPHb*2O:`Q]AL
]A&d\-g6NIatN2Gb?bHQ3]AD#31*T\BIZu[-)B+J;lALl[I"Nd5cF7@H)Zk988u6PcKc\(NViM
o;$(X8*a#BlL*mFmiN)?bLiKBMW1B,HnprUt96BTA_k;*I,q>6."GEn-^c^4=:hT%b*]AsI%u
bdJ&U\%@_F>PtE5am([KJ()unjo[ET.7%4gSHIRWI7r\)G>T;b?KQZ)/,_Y>WO%Nroq?/r9M
d)oN5Y?`-e1a`E,O=:?-RNVI!mEJ7sL\Bk7pQaQEL'XR+>O(oM-9c3CbZqm!2&S2pBf>HlGY
tV*6-Ba`nV0hs0NebkRr-Ea:fU="5TD<Zq?.q*XnuAB/1=HZ$euL#(0CRSm[CO[&V3ZtPK-D
sOAGqVNPp"`QNqd)?iH,_BI0!8)s.hq"TYP2!uL4f&d_6^Vt^(_8jZ34gI)ke=('M.DC'*BU
<]A8CS:ZTS)k<]Aff"@m)poD.M&NemdAq3`pm"Eo`p43)EBsS[7$[mp>bj*/0c%CkDG0,cG^1`
/Kj@=DP5cEf3r).F+>3rHh?cE*Y6Nf@]AiV:D@"Y(X>$FJE928U7t\6D:\FVU7DQ:=WBfC0EX
!nl5[V'5dnVA]AhOtA2']AsK3bPX$f;^F$YHV+!F'YXhb8k+1ppB_E"5-t7eA_eG\%bs$$-KkF
16H`1uD+(L7?,N;>erG&nc)Y#f%'/7AB7X!a`cYggGV!H+6"nl-X%B9$&^l>!2>8%N*t6f-#
LIrRAe50BSDGksW*EF^H\hN/*I.s,_=ZK\?uZ&ajmHdd'`Vq:8*ZW-'q:A916LoY!=L\<Lke
([[F[Bb%B;BX=Kaj#Kl$G(9pHjAg'21I`s!bfff?4`pR8s0VNd^mTa87m8Dm]A$#Jh^39&"XR
c^r3m&9l6*6uC?Q*-_ip"1"N'Q-f,O"V`_0ru(6UG;Cr!LBP<a$UMP!5UQ'ZRoYiI^hV5R"O
_8Yg.,J\51CnA+'o3=3r0#cV9T+;qnX\r&g2^c87mKT*g7P3Ps"1A@7"tCesEgU[=q??7VJh
._)RGQmej2=9?4Pc/R$MQkI-:kAZM&2Is\pO3_9o[`aMiq*/%\:gK[^mmRbqU7N;)*Lld:>"
/T.q3`P]A[kR#lGh8fm&[q=]Au*C$"4]A:1$_q^I0%\C45`-HtY=89P&V<c4g&rV@>&0KW3cVGZ
dlZ#qPlCUT#8;K%p`CO*Y-mJU:JJZ9h9hU3h?Yf'=D@rU!?mW-P(Xr1HZcHQjQh-7riqWOZo
jJ>2;(q9<V;105'W:juO1[kS2]A^&;5S0\qJaDd=7<IJ?Yb<d-g\Mc]AN23BY"+g3BGo"#M<9N
uS;+<b9V3mG,5WkBg#i#/Z;KKSYLSkp'?"^UQU/`B.Phc]A;)"q>d*q[MNVlC]AMn57F#VJ,l_
m1HNgq_>?7>U^(Voe2Jr=V&doc]A6s6[oD%T^NUsf?0c_@5:R?+V'>l^_iufM0M,gjQF\mMqC
E'1kK4iCXUc2KL*]A+qG%&Y/pYh*Y7I=QON\dk?^Rc!%TOk]AGaDqOVQfW?S![$@J5Ghdi8_]A1
\T#=8tr]A>jXNj'qi4Ar]A>F/lO?-Nrsq".L7FRh+"oEd]ACLXDEdh*V>h!8s->TmhfJ9YFL]Ak[
ck\r#OT&:Op:fqkcK`8a^`\HQ^c:tBM-EU;;`T#fl8tdTJ!=loN<FK0L-"^u*S1b>%)`-!G)
U[Hl#DT'EeFHT.A,Om'U[lNJQX2R-$'T[/CakqAS'e;[@MuD<_#F[*nN$0P#:5j:4hcg.3VO
9]AK-!5@5G.R\%eBB?.oT1s87J6GISl7+'>/ndf6JaD6cHUhU5cYjp51QPB$"'k0E"2+*K>^1
AolEok`IG@'uojFfqU9m1ed'm-cHhs7^^"9<Q=3+;Dc@']AUA%5nh?0p&r^kBHZ,#AOgIL&Ze
<$So(Z.jSI4R-BW[n$l5.:)%SB>%bBM-n&JNH9q$5'"&B/VGcr6pPkf2))n.S5ikQ)\(S4km
k5FX^_-GSX1I_7!iOe]Adq5E6*eW>ludt1Ic-$*Ilhs5]AUJk+Gp%X&A_64Z9;`25.O?jnWc[O
udqc3GGcI843?7(Ph9jnlZ:Lc'fFc\;$'J')STN!=.b\\q7S#-E-1r97DCKp&q`86#V+0R(r
G0k-/q`r<8m'>SOl6H3tW,r.6b!>\BP5mD/,HDW\rX`QUOY+0&N'eD!D*CmFQK5SB_G,Yk7E
[8_S4T:/!HOrNXdIIu)KRic9<p5ZQ^f(fbpO7_")`)%B<T*uMFelHV/aC%>/km9jLao>:;c7
*'?u<<6S6,Bu7tV4i)o$KWS<H[IKSP/KD,-<Cre6Sa76<IIds0I-q8X=M')?V=j*eI7hB0/*
jA"Nh:gi$OUqBa]A/b_hJfloT4GLkm.m$O^tJPW.PW/ZYJOXj(O&d"Wp_:ogp6:5cd*WoT3Mq
*pa2um;(K_N9;JWFa1*GKD:`R]A.ggKI;['M*:(OI48RTEbSC^53r_!>hui?6"e6'PX6gdS%d
$IUPO@'"K<JJJ+?tg1_>.!CqF-h&qq*:MI$$L[=G\gi"ZMD4KCSMmD$15hP>rSb\[5!m#&0Z
RUZ62ii8SSXA[mRQE:coJA3;'ksEp4>1?Z!l'&]ALV,71-+>#i+kGc.)[B!m\3#LL+WYRT&/&
20P!V,c^lKD.W$mrT;UZ*tj>.IK^!!3AT]Ahr2GZcZ?=H!U3%/"366hT.OX*UWI0@r8t@h*_@
]AhCqhi''HN!TR5nN16ZQ0o0D1R$Z8dPNe`3*[po1YVi!RXBu<g^^aj#[_U@dV11PB4BMg?W3
Eh+TX4KsM?1V[XA\@$L/Jc+W(BWV9teEPR]AE*%@tJ_N:e^P%DqZ`8KaNjfnu'X0XeR9E%<d&
]AZR-,O#\/8e3H&st'3\as1.YKaGE;+$1s%\3j=>&%m'=F2.O?eTe`i1EoEQ0oh>/+qH6"ao,
U)>a:E6:P5\&qhJ/,.L"E9%>)@n6b]AIV7QE+L#':4Q`%bO>6)\4Pa1Zi=dn+/jh[#gmFo]AfE
OR/dn)qa*!bm;R\1I^nLMAL'_&]AhJ56bVdXO`PO7"c&j]A7R!dWRcP'Q]AFc$2]A+9AUlh;6qnW
:TB-Jqm&7i64=8<7c29?I@>j^5(`'+\mlRi"e-V%=[V#h*9GCN.^qmN)!,3*4U/Cs)*8PFs)
`[&G36t=o:OWLs-K%Ehca(;8bk@apI&W?glF%8n&d8\D9mh.EXr<\bL0)&eoJonb0rF#o\"C
(7G*s3%Gd]A",9\EtW4@c'7;e;':AdR#JZ-l*U.K-J@mU$fUoU9t8%&*cDLEUXcQtS-!Y.nZ6
;^H&1[+=M_M$t4#@U&8UZG!R)QDlA.%5#O7[/P/m@4%[2s\>df)DJ9$*;%-BCXAS'_`SF+Jc
1jZ[jA%LReoRg@)#&FBFJh-S-o8l;i3HYjC%t/B3X+7)1:ZltXJA'_jKi>Furb0LNR7<M"m.
PAeIKqZ8QMOhU\_)I]A[l6gY"s$P<P:]A.7<snhubPo0XO_?4Be/EG$a:(<V,gPO9P\C1=tTd6
(ZR<bt=74RmZ<=`r$5%4%;0p!B>)o7icDFH35cm>Dj4QcZ<(!p,uN71q2@S$2I>mYI8!oR=M
g@9*bde2+*^p%NfoT,jqno/'qM/>'c-R#arUXL4!g]Afi?mAAmA=gYUFGf3D5R*-=Dl43#/X8
$?PH8s)&K7LEh5:Qm17WLaWET&G!q-#PeeZWLlTq5:+:1$_,L9;IJla8g"kK-$Fnmlt<HGH7
[+oD8_\PC&t&00-)iTaa??c.PAfF%(!r?&IcgVE#=!ZX3K$')-?@-RH;u2f,S3aJAH1ihClR
FR4b@9upcrkm1RZa<hnl&'Y93)Ad%G@.ClA%#R1;n<S?+qr&XO6gO;i3gn*qcF5/\Cp9BEde
Tnt_*m*"MhQ9^$R#+p,$mRXT0Fa#?gui3(J0e^?!k*NnApja4pFR>/0g(ICueJELSP-Nqhu#
<#F8$<->sf.rX%r@ST5VpBe#/Jpps.%Sku@G=n'/RYgUl`W(r9IXA@"t+BsO1D@&[Pf_*\I_
Ir&\nki&0=\@M4]A2dQ^Y%\6Teb:s7Wi_Xp/m1MOEdlA9J@"g)O=Ad7Par7/$:?6MD*#V_.js
r"UFE&LfM2MlQS0RI]A&*+2i5Y!PYeDNSZ<2Q$C1oPW0^3IZ+?=Ai#RS;;k7NCSLo]A6CP2rT$
K8I3U2Gsb8-KH6`)dXW:U/%0hQ5>u:C5):8+-;B/1b0[$+o5`T8&d$+kVp<E\0drqFp9CQ+?
0D$KQL9gKh'AlIXbFQX,f%^a+Jb8/f1d3V$c6'NODEQ^=^XlGZYl9A.n`7E:Cf@96t*9a=1o
aE=NMf97%n(.iVPEq^#NbIO*V$3"As>_TR\AZ/^sF(n7=h!b&<D+Kg[_!cATH[XrEQ7'-Q^O
&)"*mNdWN401f;R^$X-:7e$KGf'qfMagrZW%:!Ze,$H7Ln7lnA.)-!]AR=efP<\G'r37UB;P0
'V<41B7-RD=&+7/C4C;MLujB00t>QGR9@^_]A3<HuL\6f)'E`*XZ9a5T8#\OH\%:lo?<O$a:K
P95O<D:M"OBZUe+>jAqB<PklU4JDsMG]AaGa6-_T<^-2@g>"^iBL'meS['"kLOI:kK*,/*F-S
b=-mK#^%c,+#&H$?;MXR.j/VPMA'lJt1#NT<QU"4IT12.AP&D2m^*6&q0B)d0&tFA0MHXZpC
HonPk2.rg2_2,Xq!<YlHp%l4U<[Dl%##-&;aO'8h3S"Ss-D(#)p-P5%bbCReWDm5aW-XZQtO
DZtNhj)u!bV)c(H",6]A4U#1]A_A."M3-]A62TO^f'-iV:\\i+Ht;l]A%l!]A)K^/Z+RXJ<J21N%g
[:Poac,c.H?:a9It6#c)X/^B`_O1@Fu9@?ooQZ-75>N@HMRJ5(;*Z+P-.)DT%5j2HGPM?NKE
>h+Yeg3?.+X5m4Y62\Vl>+*RMFT(`_C.^cQ8:IO0+?nP!$#gPJn48gRk(_24TPH-:$7O0j8b
O-s=A"h@:`oF;i2lI6PhIqhI'Gie.lL2C.!c]Ag>B?I3aCI]A@(=9h!W\5gk8F]ARE/\EiC]A.X9
QMUd>3Aa+pN5Erg4[0]A^9'jRna;0haORZ6hCDZ2tm<G1W]AT7tH-S<[mqg$NenEo&m9CejHeG
Fqa$\&Vfbs%[iVE.:CtD<4)07j::aU?(]Aj`#_&V5mUZ&ZpE9$"[dUoXS>YQnKW)ma;N$IXH"
%05L+u?r!r(05Jb1TLFi]A(H=?3]AS[DOae\Z]A]A]A':o#Hoe7A=NYp"Sa*)!TIrge+#-%4j!W>A
P7^drP,0IYq7t:-@o)%O%2=_X3F2<WSGL<qb/.EL'HG5leIR%qa8g(Ngg58$!K!HQ6$*roAg
`CJU?B)m+@jCY4/Q`?H>:7`7S4$?,;s`i:i.QfUqXn3Wl2Mhcb0pu[Xnm/EB*i<=DU'0>*!\
9S&*D$B^BDP%oag,!d(eI0'&QKL3P6Cg`156l,RFHQm(8po6+T<j<_-4)Vd?<\:+f]A%)+ZS7
,`EA95l+pHc6CIoV^/1KG"PikK48@pg&`8n;B@2dWMl'O5Sj1r`F/,,+_VVoL2c+0[/]A<EW$
gjfG.9G0ns>O`4tJFTM+_.!-^?r3q\8C]A8uWT&!(.Ia1[_eZW@]A>`C/e==_H1=g%kT<rDr8g
@Z%+I_KE&V1Mc&H/`.l+p_5bR9?1*+E1U[<*%#6`MiL3(e$_i]AcU?FI1;Fm5N?bZR[fMn<q$
0Q1Xlspc;s:!;HsiN:oV=ADX>0$PGR61EYGq>&a-,P-<Ho&NNY"79rff)m4W&c'`Epme)/ma
jV=KfO$[p"-il^CE/6&13>5OXAYdYOXHtl6Z4>e!V%f,gYUY8XaCus"7qTWti)Y.fXEGVM%>
"FIRS:dZ$b&[*AH-7;EHhf?m:Xs]A%Fu2[_8)@.jae0o#G>9LJN)qm$eGmUZ&WVC4hHfkVHn2
"VIXj'R=+/f-qqF!js$BkH5<?(s9HR+FSlb7dRDgY*bsIDTD-2(9Z+q%^'tSKG0'^a[fX"GP
SD_4]AOC(+P$;MOC$Mk6tK)Rd*DV3k`%(;3rD"br&TBi&;-:N>5!hnU.ML&p'[R25"[EX5\B@
Q?A!c@Vj[a,)6K=;B"jcn?6o!%gq.'+SI_V20/Yi\L(fp>dqheF"@@qW3N99M/(*jJKiQ%HY
CW/$:'.(p'lG-&6c*R:\H>'&<B:+0FH;.[GJ"(i*OAIpgm4"]AINYJ\d]A8fj7Kn\c4b]A[!#:<
j+sVoS_UP58%&]Ah=Q0hfR<>^QKI9sW5>U![R@49,-TnXgG3BKH2i)QTb#D<\bI+R^3,\%M\,
O<4!X?(3gLFh$V4Jo&jL\U^%dnl^0)9[K%FU/pbl5V;m\$+gq9N*gM\6p%A"GjFX-1-0BXI6
)aY:O.Wf8U!HuZI0/<>'2j!FS?ZPFclbia"rVS=9`'73"^H#m(T.h?o?f/#f;K8YpG8dL,TO
.dPc$M%1WJf[ZbP&+`7gT@Of(AJ[+"BgLgAo'I,?-;(W`kkG$=+b9hikL]AbKCYB'%EE1h!oZ
KdVGTWhPP2AIJN?TjS5>=T7DL*o+?p]A)a%rIqRMIf0a_gl]A9:G!s,Rh[s+O_BT.0f.nirMi4
;ZpGG$8[i(,I?f6KGr\ON(%I:?B7<)GX0D5KuN;63#d.LEeQqo#0k(gqrT2FnT_niV5q=s%N
77PTtYoU%mkt1DK$WIsQTO)dOqWiLkO0Q[t%1Dl-go%#"6gP"#JfZWDBS2`l^C&T&8Y0nVWc
Ot8:S-oA>$U%1)Q@]A?XKh+G+=(nQ^G"TeqDH*DW.R4-8\eH+U;388iod9F$aa<sRC*%]A_E\s
"DA@%,0,)>6G=HIjVLc]Af;V.ln@pOp)nH.?$cI%7(<*'j4+Q2g05k05:*N!,(d+q(Ln5Q&+N
5Qi=\s682Eh*0#hjE%FN$`1X.uLd_.G,&<jVP#Ng)B1.EJ`3Em334MsESeNm=7=-fDZOB;S2
fOe)@I#HSDDXc[E%@Y'i((#JIU)%$h,7!044<?'V/0;RZ,EWfLjU7*?A*tn$O7=bGF6^:dkH
T3T'f"D9lRlPhRS=Af6@e`L5?PfF+0O91RESAK%\]A/aA7t1<*A+q]A<IdIhIfi?66SpZ9h7B!
mI!/90%:uZNSs;@3QW?t;6=4bh?!_!6eU>E=hSmO-I)7F!9lY/)r(7t$(2H28Ks#N8+IkP@*
.NX?%j1&'X0k((p@Ks.&V*9$:3Y<a3XVjD^/;m1?4qYb`]Apb%(n@]A(23tELm$0/^f8`W>pG.
"J\D`gg/;l")N\?sUm%1arb*9?(*pT`NoYr[D0=u;[Y'nM-G)9:.YRGjCS\6c:/+0CW&Fkr.
HB9>MY^-7dA<L2_Z=0FYGEL96p3UoD>"7QkQ9LB+eJ?cN_jNIJ>D\XH]AUY`?MU'4cBDU1rq(
%oq0*H6QQ`/a/nj,"f/:4#jIc,7g'DL&qs9uK.i[FO4a;^HY%uA$CV&PSlY[tfFlu-dR_7rj
,E@G)3oQR%3>7q9T_O3'i:fGN*tRb-=@stkRN)^2^QF2p;7Pj$=,*>p)I?OQ5C;>)e=j&n7O
tBX0pWG6Gj@#E)*A;SDOn^>h0Y1YCsU1B6WAQlP?Ai*HZnnrN%8=#k8p&l2i>@+d.<M/H1F4
63U0jhr-r-()g'F=:T,kt?dcY*U`d4t@f$2eJV'TAVZ-/^>`-o7MmVW4`]A\s`e7fk+^-cN%Y
ec&ak.HOh5CAMRq%;?qd@'r]A?]Ag#ug9]AsREFJCqXjQ>_7Ge\^4E09Trt(TlP6Pp_C-_dsR6h
R-aS1oF5H[J6ruY.U?Ql1(Art+56^E"9%EUT\i@@>SA5fYDp#d@$(q__JFL>,[g+)`A`,/B@
[D95rmg0WL)!?>o2<^US<u+p`X9UuCHYGP:3YU%PHN3C,7^gV6qQHiAn;nN>%SMlYa+41Ei,
8oWFZ-J&f5c_jfJLHCT)/T.@W<=V06"K4k&Z6u7BHKI,04<IXn?J\L7;2$g"45h7\%hqN9CS
HSFi=iGZ@JuhK5@pa/8+u-aoB5pPOKq2mJjN2mK&6<,rse]AW]AD>&;-5).$m/R?+sql$XHqmR
,laF[!*2k7%R^CEl9=4G1(B;+9tnB%g1A^)L:5'!:9MX/AJOmr)Sij++j\IqY%u`Vj"6cNP*
"46(T!`-GS%ra0J1iVr#pOGa@k8\kK;Y[5IM9`9+2(.gi0OI!2LKT9c"=EO:Oaj0@^7?3U$!
U!%156;uF@(cBn"OnTODeX$,l[t65j@6P?iMlHqO+0@jpJUhT:UQmd$Y'KK5no\A`d4#B(K'
^C4g]A&lrIXE.SM.3Z@k2&p04&Hf"We8eee'!tKY2&plZ1!Pgb?"jqM%0t^[0%8+b*D'FKA>Q
?aA(.>[7U/o7u2WQM(Rg]AB=HBdD//qicp@(.1(q^Q#Q9KK^O.jJkasqkC`qoHEq>Oh*E)_"^
MCE`R^C)r#LZ"_9rbR-?*;21*c#=c&Mm1&<"@WT8ZOBo20on1SE\+@=;1D%/5#`e3$dEib?k
`5_Gr]AGNokrrFfK*CT7JV-ZV`2L+qVhh<cUi4_MB@&>r4pF&cE0LrZ0JQ49C?8,pM$cJ`,F?
XlO<lJM=L??-5mEYGMl.lBuC.r&^h_3EfDBKAnDX*T&Uc:7EaK'rhVe<HD]Al:hqRC=%FVnS5
`1C+FDh`,iY5q<JZ[MJS%NV;W[-,4a,o]ADZF$b&nKu:Xktg>:Ui5R-%PtrX["bZ*3fSO@711
L/?s[_*+]AJ#=QdR'Q7A6%\?ccDp$BF4)\[A@KnjDIMHU05<R_NM$03J!;H62%0jBk(%Fuo*P
]AK/&O;_[`;ubdKapMNnDpK/%P<BRYE/B3?ci+Sorg/6HY>+5?:uuC$%R;$Ya;kG`_<KjJ(pX
YWgJKlE2c,/@Mh[CWEg=b&-fG?R>;Zhn@=/\I".Z4IH(6H_Bha-Ig-J`M1ncMBrh"$"HeE.+
Bc"lYV45FCOh5`&Qme2)^O=2\$9#@9%/HPO=HmUL0PpCn5#4$KrPq04`8DEbOm=b%!6K]An,X
L;i&t*j7%&H!<?m@rS9UrM#bNq;qH)kB0)Q2,0<dGed56~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
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
<Refresh class="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr" pluginID="com.fr.plugin.reportRefresh.v10">
<Refresh state="0" interval="0.0" customClass="false"/>
</Refresh>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,6286500,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,12725400,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="9" rs="16">
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
<Background name="ColorBackground" color="-14801862"/>
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
<![CDATA[深色背景甘特图]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="160" foreground="-5723992"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="2"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.gantt.VanChartGanttPlot">
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
<Attr class="com.fr.plugin.chart.gantt.attr.AttrGanttLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.gantt.attr.AttrGanttLabelDetail">
<Attr showLine="false" autoAdjust="false" position="4" isCustom="false"/>
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
<AttrTooltipProcessesFormat>
<Attr enable="false"/>
</AttrTooltipProcessesFormat>
<AttrTooltipStartTimeFormat>
<Attr enable="false"/>
</AttrTooltipStartTimeFormat>
<AttrTooltipFinishTimeFormat>
<Attr enable="false"/>
</AttrTooltipFinishTimeFormat>
<AttrTooltipDurationFormat>
<Attr enable="true" format="day"/>
</AttrTooltipDurationFormat>
<AttrTooltipProgressFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipProgressFormat>
<HtmlLabel customText="" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
</AttrToolTipContent>
</labelDetail>
</AttrLabel>
</Attr>
<Attr class="com.fr.plugin.chart.gantt.attr.AttrGanttTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="true" showMutiSeries="false" isCustom="false"/>
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
<AttrTooltipProcessesFormat>
<Attr enable="true"/>
</AttrTooltipProcessesFormat>
<AttrTooltipStartTimeFormat>
<Attr enable="true"/>
</AttrTooltipStartTimeFormat>
<AttrTooltipFinishTimeFormat>
<Attr enable="true"/>
</AttrTooltipFinishTimeFormat>
<AttrTooltipDurationFormat>
<Attr enable="true" format="day"/>
</AttrTooltipDurationFormat>
<AttrTooltipProgressFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipProgressFormat>
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
<Attr isCommon="true" markerType="StarFilledMarker" radius="4.5" width="30.0" height="30.0" color="-13691"/>
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
<Attr position="1" visible="true"/>
<FRFont name="Microsoft YaHei UI" style="0" size="80" foreground="-7101768"/>
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
<OColor colvalue="-814717"/>
<OColor colvalue="-10244375"/>
<OColor colvalue="-10895670"/>
<OColor colvalue="-866690"/>
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
<VanChartGanttPlotAttr seriesnewlineenable="false" linecolor="-7697782" linewidth="1"/>
<GanttProcessAxis>
<GanttProcessAttr customproportion="false" proportion="30.0"/>
<verticalheaderstyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="100.0" backgroundcolor="-15394264"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-7101768"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</verticalheaderstyle>
<horizontalheaderstyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="100.0" backgroundcolor="-15394264"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-7101768"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</horizontalheaderstyle>
<bodystyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="20.0" backgroundcolor="-3552823"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="黑体" style="0" size="64" foreground="-4144960"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</bodystyle>
</GanttProcessAxis>
<GanttTimeAxis>
<GanttTimeAttr timezoom="true" zoomlevel="0" weekendtooltip="true"/>
<upheaderstyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="100.0" backgroundcolor="-15394264"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-7101768"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</upheaderstyle>
<downheaderstyle class="com.fr.plugin.chart.gantt.attr.GanttAxisStyleAttr">
<GanttAxisStyleAttr>
<attr alpha="100.0" backgroundcolor="-15394264"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei UI" style="0" size="64" foreground="-7101768"/>
</Attr>
</TextAttr>
</GanttAxisStyleAttr>
</downheaderstyle>
</GanttTimeAxis>
</Plot>
<ChartDefinition>
<VanGanttDefinition>
<dataDefinition class="com.fr.plugin.chart.gantt.data.VanGanttTableDefinition">
<VanGanttTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<GanttAttr firstprocess="模块" seriesname="任务" starttime="开始时间" endtime="结束时间" marktime="里程碑时间" progress="进度" linkid=""/>
<processList/>
</VanGanttTableDefinition>
</dataDefinition>
<linkDefinition class="com.fr.plugin.chart.gantt.data.VanGanttLinkTableDefinition">
<FilterDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<Attr startid="" endid="" linktype=""/>
</FilterDefinition>
</linkDefinition>
</VanGanttDefinition>
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[mCW[';q]Ak`IM+mW'gtnuPUNJ<70`s08Sl=;?pLq/`YeqKN$=(B'_Nq:#T:UdJ<21S#`)>l;F
5J)YY]Aq%-nS%=#Q^)"83>^#&4B67mbI\hhuEPe=c;D4S?pPJTDb#3;teKsj6VF:V`d`n!.]A(
Teunua!7]A*j!'dSC$G*"?i7L!hfGD;?oa81'=$P2k&+=0XZj'1-i[V]A;Q*]AXRqnLt[ij^(Cj
b.\=g9UmaRH[FJ[mbelr0t[gnGG;7lXHS!rNP_ToA+-8mHhcPh;b*pV*-;hm,GGuZ.:4Vj,&
G"cUe`<FkmEp):rp>lur^Nj_`0j"FIq\iSk(Vq-VTl6>[kH-ukCk.W6a^1h:+fH3foYYQk??
K%g]A*^ci<8,o$Ak+ZtF>s#lDY5L=g<Im>"l07Ir/H5,d0(OBHfB8Z(k7!>OM4;%ig-j\MDbU
1JN6%@:ZnonNcm?#@:XiR);ds0ffkReLX@c6M[lGGrE@+RdtnD[e^E#44Vf*Ds&ShUCls%+&
tYd[2!)Xo@9m?SD"58g691;r?-^#+RnSHJZ_q$CTQ,*AC@BD.<1Y0]A!ug_?&.]Adp\3c_#c2Z
M(1g\/$)["K&_(S[W:89q>Fs)!uVSiVV*@9A"IFd^QR#Rh&EO@ippnq2699Y6#Ncm-(a:C:<
61WE6\_#eIl/GQeK%ha";UN5#YJ]A6s.RBm4Q<hn$&2_S,V`&e1=k;Qj]A(_noaR0TS-f,=l@k
%p%B0h]Ap=H?aP/lf9mu:4=FL`'j\/f5IZk!l(@"@=Es2p<>eC,AomESajFpV1%ch9L*CuY(E
/h*=)1,44'kBE*8<.<'+kiUq1C=\$%"pB=1Q,(^soc#HUFhZkr/D`HsX14(p@e7W=LGEC.ru
\CJBM>b6<jhGZRGSWt,K.G<4I-RO3bI*Mp1`2@%TE]A;!LIC;$MNY6[NsfsUFg`C2d>CbGF3&
t\IKAtB6ZVW&QTi@W:DR;D?D@+TQBiU_4;RsK"8UJ9Qr6?1Hf68+O#204,2A!bo_0.GtAEmD
:Ci)OUp'(-tg$&dnTYn&MMSeF?(^**3CD^>,@r%9&FLh'd>#:U24G9Dqibd4NJLU/F[5$g')
]A@J>1cJg2_rBp4JSY2AebOt')kqfrSO@sGF(),r)Me-rg6j^'X]A<\u7"2Z*sd$>\r^r=@_.B
$OheeYuIO"@0P.V>"EPRY=o:6;[Cidrh"1Cb<u'eg/:JQ<Vhe3]A^JW"3,0DFM#/5JWT_ID;^
f%;\Z?4+0C3KFY!Wr=*8L2J'VZkqA>'Dub-mjNWHPC0llfiM@h.!GO(@'>b@PBXkBN4\FZpc
nJYM?b<,G!nGiO'-K79[S2D2,/>ppLLgVhac.!"*L^jEBQWFUj`6A1rsm`N9ZlsWD7>+X7RO
KtR/e,WGGh."gH*,gNIbe09*nXsoo5QGJ-^r92#"1[VU)(p_;0Zo(W@B9'hMM-1+sVIe_tKq
:,ENI0>1XeRIoEF^[L`NAoTqb^n+J#9WHcSaRCE2f&IoG)AC3BV?c45AdW=#?J\k")n-alOd
:5ked4\iX/.I%<T:fkWporI4,`cDkN$M,/.i?PhtEl>p\@ijC<kiT/e>M[)!Wml['e.g)X&I
0>2Y`t<LFSN"Xr&\'6?p5fj<2P!5ObJ@_dtF?2HP"L'$#!0um+/1[?>]AZL6q*h6*t4:&?tFc
in`T<SgZWjc#pjcE.G_%UNT_F*70ES%$U:f@MC^LZIl_OA1?.?H_`p[g3%6XqdpO,!LtN%sR
__M!!-LFUQ?0:(=]A225+RVr7eo=pAQhHfg`h6;e"/=klu>Uj5Z1++2PLKqkJ`Q(nhegYuTSV
$"P'>6rT3:R"I,MgC/o#gXo[]A1<9Zm8VRB7Gm/BWa>MX(bc/brjD1Fm-7:F#<>S`jiA(aL&m
4lNq7iU_Qif-Tdrk8!V1#rN[unl*??YhrC\P\nN#RXDUHS1Gf5]A4H;"Q7`VG8CtG\8P9[&4U
n'((%,lf]A]AiL<S#'!`U;'R6Xlg^`&#^e77Pj>pjNJjrFMD%NfRYF@a?*J8(Zu:#j\.>2$14Q
C<'.Q&\/j4,X-sjeSZ<FS@KW']Ai0MRE%%s/8CPXZr+]A8,08q=nL^:`lEk5fCkL59?Tuf4iHa
d@))_T=5;lu&/5be(KUN7sM?I`Y1Zg*<>3=RE'KhDa]A<UulT"VXT%BXU6/n;)c[6I)HWbV_=
-M2C[:]AS,h`.,Jj^pr`tps:fOWtpQbmnUiS%92i,'9T7'=o!a\^cq$N4im:4bogY2AiflGQ+
V@8Csr+rl=k%P>@%T=LAM+ofI,'=8>qucm9T$)M=s^OZl_Wc='r7hIpG&>d(m9-EIBf,GZnB
i@UlDU&;QVA-oc*[M?,#1m_*Oh+m6]Ai!L>MI(XJbf-^(7Sr]AFW'*WB]A+j+Al1FX62hd<!c\(
:_<=*"=B,+jW-_VhQ+1_1bCh122kJ;5dfj,QBMP#Lcdd'f4D*=4)CKo9E=E#rqHZ'SOfq:ct
8X^%r$bMjHsS#9OHVc$D_uZWi&?eQcRu?shd<fScgW4cRYUA):<8,+d\-EXfQMcsL_^A3(qB
46(8g,p5[UJ+r%Aa/_*4c[Dk?%[d8c3%0ogS'r51*]A;]A$#&G[c;UUjtT##dj^cT$K_q`]A3Wj
i,\;8CG"79tL-*h>H1O0)EtMnXICY*?*&a:ReE]Ad`%Wc5"7eG;e+ZhO8aa`p5@!F<]A;M9DXc
Gbra0/`g=k,5IsE9p:sL`@M#C_V;j,M>[-X?5gA1b02C8Bf7rq3X`^8=Bu_'41!.hm3`[35J
SBhgkO'KU]Am$>'B&@?lB\Afm)j-ZhIBL."1BHFQS71p8J;aVh0S_a^4Qinh[a?.3J]Ad\V4gQ
Q(?eG?c$0ZU36/&Z6X2UVjP='/%Siom%4?6)??&N4U^f_7fH5k-iV86;[jtPp"h*%NHmp7AJ
0DD]Adq1SEii=m;[A(Jg).MC$1.h.;[kHZo_?R%B+PBYOfL^E=YVXM"2G@sSIhDd&;"ikKO.P
Nkr9:V2+VXWC?fB$LP\YXS+31i=dX0[XWPn`WH<NLa-_G(S$drl.fO*UUXnpOg9-3FMk2XQ]A
RljW-(*lL;6k`PoPfmD'\9O3c/A$IH#b58%Wq9Hj.b(;\_%D/OSgA7GujY!-LU4<_X`]A`DjC
MTD0'Qd_AXeBK!)G#i)`E*Ko`.RuB9"Ep9(l$`RR^Bbh8#'!N^Ubee#.bQMMtjng'=KP6:G/
<HUDJ]A]A?FtCef[bnR^9tZpp8IlF8USfu+G:lcgDn;L,(+PcRS2'niL*1JcsN5hk1(IbA(CVe
hUXn%'o<5F,,mf1nB`39=Yq6]AM`GWGrF)*jn5Ba]A]A/M_CW>b(bjnoj&H4_-?CB4@26e<Lp8E
Y29(O#D-#lcCJ.)\%,*9")tXWcgC#U-Q>UjeT-f@H<+&=7#N8N..=YJ$,bs5OBiABY'*Z2"V
QKTUVh5).<EIl5eu'4Nil[D`4IKtOj]Ac-/=GCcX+4A2PV2I-"L<8-$hhPO;35Bmj0REP//Z/
39o)C2R.^YDd"gn?HD!4WQtL`qC?Bk&3ZKJ]A9L`%XQli?.aX/!t3l?;YV50RjY@_EoQN/A1,
^@M%EX'O2%=?j9n*3JhCYi%a?"?4UD^IA=aGu#<s^]Ac9BpnXK))RQIa6CV`/D<c6.?bnu>I>
e5QIWffus78(KWFoBPP3n&<Jbrq\O\UOV.*1'2"T^>VEU&1P#EJoJi17G5eLm6?gejK2Bk79
#hm11M=0q]At]AXE<+_5roAhJ$[PK,.D"_6beVa?94o;)0Pj0N:WL*%`^"P-U8t\@<iof0.!lk
5,?@p8^&a$%YAB)(kkle%qWC4P?b['Vl/`._3-e;P<pCE!@/@au5^n1rZ,O(-P'a^JEB8.Is
!ea:J=1+inJ4nf\$Ajl'7%WVqAg*13X'#*,,A-]A8<`K(X0J*i-a#-ZUTcF6;=ZoqKZ&VV38+
,qZp.\h8f%L^_"l7s+;s:8nN,VE#%CD.26\3d%P3g8&a,:CoFUSs:0m+%7/kZn0!YZ`Cm*Ed
iYJ@#FBm?@3sq#ij?H;k9pF_T=j:UC'nI>hGL86hnIg<4PDE4@,^cB(kDWd`q4d)G=Hr<6FZ
ssPbblT"RtHjSm^97Tof=8k3dHRT,-F(KGH*fFo$I:43&Me,8'9lB4;U]A]Ap`oB]A3dZG/NSFZ
\pdMk>er\"&n<f>brtJiio!W<c__0;%>EHG9Pf#]AZDgEI4a_Sq"A)RU!oXgA4j$+WF;8_++r
]AmMUn;l;Fi&(WAmU=Pu@6XGL^E)QTdrTY[hoao%bUO+QIWI9<C26LFE@d>_e5JZBS26!`"#H
tY95I,:=mPB<AJp`Pqdne]A!+9X<Y^nA1(e;afg)h=*PBFO;\4G+%X.U"tQgNGH7$csAIsqM'
oP.Y;2LdI[8OQ3s]Aou=>iQe?;]A1K=Jb]AfeW>1X#K2-O[#l+WdJm4pP5d9]AkS6Ci=6IdU]Aq65
YT:>J4Ltbs-`/-:s\u4YFnK>umD-*p1/VN9u!UoHMH&.u'BR-.@LZ)?%5&GP,KNMRfFn2N8[
1frZ2VeU29/B[NG"Ank09m'G==+P7_7@WG6@4>-]A]As"\[T)3B@'@7Qj]AqS)ccYeC_QC?LAVm
11fg9_a_jIKkAL!67,4rHuH4+@#k.2t&nqqppOB:;+k=V1@E%OQuu0fb]AESZ[G$A#t*Nclmu
)2^%oPRqBM-(8<.U1+,2S"nr#3h.F:5pE6pIoI5;m%3&YM^dTq1`R-)J*F(fi>J,rP[%L"S0
.&6keVLRp[$-nE'/56FMPS0;rVij&B[pt#-/]AFYX4`k<D.QD2Dijr!g-!qSJECd6iQ1CJ8>V
7J$,/IRVHp_n3_qaRppb2BLXotk8jM9`N6]A;i+!alPr%c<+AA%d>>&Ptuah1mE6KafC9M1Ae
R!iAD8Pr]Ajl#T>^/$/QM0G%H/:<cU\T*YfelmE+@*[M*g<NBer0KC'f.U'7m*70C>!r.t3'C
i`pB(%<?U,HVP'kQ`3Nca@ZWN0;_\8W"dKNcXT@X_MCg0`9a'@kF$4lb%1E)9dl2B:Dba&2<
L=[kVNj7XR%eFnpgCm%F2Q9gEPtp(t-a`GHq%bW0;l8n7nn_#/a9F.E3+P\X4>F,c_aJ>_b,
53R]A#T3fl1QU<f9TCL(^fE%db@[*]A#Q'-('o4nL$8Sf=[DH)o_fkOXd*\]AIj'"4RpHQ#KZ+>
Q?g[DaC6?i7Qdn'4?U1K"-[&e]AFY*AY,6+aQ=Ka[c6)SH;JpdWfEh0*3G'E<A1[&%r]A8<h?_
Pc!i(e"NI&1'QLNo]A-5jg/RD5,Nro;<dOl%tZ8d*iDj>I_Gcrf%Su%m^oZ[6F7YA^As/Z2e.
,:bMaGoNk01RP-be"<siP?!<<1,d#J__5>T$oJN!ad4RYs;Fr=/Y,T2&jpqQ9JM?LqrIl^(l
Q75Z;;Ic83"a)3Q-WW\(]A44')s,g('p&@/timQ8Afs`clrT6]A2)s@kkdqfT1d#?mZGO*tBkU
?'"?kdmELDiU%J)>.JT7)1Y[EI_QK=bYM)m?fGkGKT_&>J1X&'@c,3/GgrHXIPk28o':65)Z
a/QAMg)qI2bj/(Q5'rMD7AHa9[qWb7Q^?C]A`DEUp-iinaO*)$pCm0HPI5OWjU2tTi+"$W4I`
$=m_oE,12-!/Lq0I_+LEpM.Ci&+F@=mZPQTt&=4a,;W)$M(*_B[or]AiX/<GIljHH@ds,r%A>
<=)^lnEq8C,U`C]AYX?YXm_lp;+7O1D-VkNRAD^8JPsaQG1:4e?ai&se^*S(DupmnZ.dro9*(
/(d+h_KA))K5a;&JkDE*U?'+=hNN?27dbXZ/>S25Y44lCL_J2@oc@HWB/B%GSe8D#egQ,PnB
-4F,P+=CGuB0a%n&,&5!;gWu]A.M7GrM%Q8'>`6b27ZG,Nl=r$o,G77jFT41(S[Pe1#kqXe,f
hrfROOQfd4lU0dt)4mC?jF<&Kpic:ls5&g#T.PLF3un&N!OmK5OLr8RP/olqs6)03;CL4af2
@8WguEYJr\r"71KV1t[u"B%X%8I$E`klTO4DG1:@[pk[FRi3u<AlIR/QfO5<+*`?siN1Q"0-
U$ZRgi(JW<em*FlAu9Q)TWc@dj`*/mF(1.`Dl@@6T1b4Nu"Y%6r6$!9h&VV,IKT=7^'TaU5E
peno^Nfku^#q3aol<d4/i6JhK"NS2)$qAg(YHS2u,?bht$h/T`1\(9jN^O7[j,q'r?eG1)[C
EA31$m3KeX+JQV=j4.jPEp9\$g=W=#J.Ib_$Z6c7hCIDS6A[H6?R4dH.=3VkC6a%Vs.-OZ#H
pR'b'(_X^1$?[m3mBuk1K)I0Z:+]A=!4.ePHK86N>)=+9&`6/r]ACe)l>ln498p#U(`kSH'[1B
fG<bi_NVrZRB8)UEK2^DGWe&mfmG#+>^fbu9QM8BLaZS%L#=g`c5!VF-FB<d74]AA8<Hd<aC3
UE4oe=8*9$RZuoV\RMZ*plN"(OX<o'Z8-=JFoh(csq-P*&YnKf[f5;K!Y/9!Kas+UusuTW+h
i)Zr6qL$35DFn!:$DIJkIr[aRhh<Ll$^]AJ;/59VfR1QBC>s98*d=QX]A#q.FY%2GrEF:\VIIa
FN?Np\B$uC"@:6'n=E<+fpT4WNca>/DP+Zcm++i+meK*omKa=1R=uV1?7OJ%Ec#hrGWUcu(L
C5lpVYPgU`uA5=(;+/#afL,5WSVJmY[eb=)8mWIn+-P,Ml8gg`a#;&k6]A9LFls4p>s(F2FSN
*%;W!U05mrg+b]A,4&]AL!&C89767E]AF#cseBK)@:qTFjP\@VdRg,CWAQ9TMs!/cP3lqGF\?tF
P"(Cla&8K$h_UroPt<`]A9)(r,`FTQ/Itc5b6%hBO48tkHWnF4\[!1N>&g#DdW`$JLHDGCICF
78mh-L3KKQO71eGQdhYf#43sr.Xl<ST&-=W1X^oljO$ep$QBIDc?JD9tNY7>LjcN4+e([N:f
7Y2=bd6XQ?D"ZY4Bn:e5aqTMJ\XJ[L3f,KS.`mCchjnZgHf?T)3Y5/FbKm7%MjLRf!_2To5d
<bAR#sUjePgo?C=feu&!oo5Y#S9JUq(LR"+Za>Ulu"b-/hf3M7s7q4%/br`<>k%lV2nN@pl3
ABq`TH%TLq`LY\,qT!X4B','`^>4BHT2eF0qaVE"'os+7\Il)@E5d9sO1)'RB/1brE3GN7uI
i7r-TK9TtTdm#8k>W!!++HY=r9P]A5HE[1h;sOHZM$a!C4/qrS&$inNiRC"UbYa_(WAp#Nq8J
?e`HsTmUP"5VnP5A<:V=5U%X/a4DTp_l(C:V/Wa87"odGZ5N'5rD'RZC'#c`ODa=Is+]Ai`,C
;E)+,cu9PE"#F01m?Y+@;"R,!p4`ed`Vf_A!1.E(e(5q.F!_BldVhBs09f(8E!2+/'[0=?&%
cC3lr_ppT9I$M7A#.4dHlh_\_?/MIb>'9!dRSt.cC/>q"X4GElO\1O%.`\*feM_?oU3JTL;!
QCmus(6H?50Y$3BPngPc^,2ilFZPk)tX-Q##eTX*qY.TnglGtCAcRU,Vmg_K98"F8>+RHV47
t1DqgR.NJ(>(2hp&#.PdoY8fl@QXEGH6j+rL"kb#GH&+V33g4\5OjZT6a7bq&D0YF<L:1(J,
(\Ab#,o"g`a/2f4NpA_\cV&O"$PflV.&?s?m6oJ-5S0ft;LTPru0g1l-#]ATV]A.ZWFGT.?E!Q
<?.o2RK>rLAW<4L5fEelc\c\=\ToIc$rTQmjOu'FBns\RPH[n2c$bYA$F_d1V.Y@$iro'c3,
@gf';0TCp$r)4F@^t[5Uon;I4Xb[Ug64!P[%]A`3jr=sF*Str)>EMOE)oX9_E/?PW)"tsW.eQ
^6jFL-Mq+?>Uf?.Q!B6)V-fVgh_L<r0157QL%?O9nJNn!LMui_ZRCSlYB_Uau1WGX74.>;#P
V]AG<6jF+0F,hXTQ]AS_QX%3U-$"/r/XDQ4OWNXIRBRH'U/XQtQS:GJ>X&?DE_L+7%,.nAQ\q<
DRM]A+\SPKq$J=#^]A*ZH-=QK?-tuRtSD74"q+DKdmW%]A*J_W[S:[<YlVt&VbjoH$*iVhmtEV+
EF!fj&HQ8W;spq!I=L,_LoTnnVrn?(LtD<"p%Boe2q_)D4+A6ToZen>E?la;L@g:)go<1E+I
,r86C&`Wl1JempP#Sh4luL7qNc"u@T7'5)s6eAX_f<q$qiSGpb^ceTaf179B(.jL&#7=iiL)
s6;H!sMdgCV4!`U0UZ@<B,6[cZmtkP0ST\[VAMcGk'sNZM@WFBhZ#HZEc>4HB6Q5$uOmKFhL
asrcJfB5W\e#>BE58c2e/'is-CVHtbJ>6;G&/iVcM7T=o;_jaFXVIV*,_W=g$)lgFo&`d\nS
"X/Q^0t!_pE:TISar]A+4]AeJR*1V&pWGkXDPSOHilEI4?7.1HZGMS42""4=1`@Le%18XB`3uG
+'[,hL*;=4-%eB!gP+0MiN@<pf1/Q;?Debrj5@[\J5$]Ak[H>+4naO7J>pGmQ7Q>J3a/Z%l8u
dQESs8(Fjs9[r[eLCJasrM$pSFQ/K\'hs^^$2ki?N.e$0AQ0JWSDDoY9D:/KRYWNkfOPNU,.
d:LD724n'SM,D0gffF=".ge'%P/6g'1cPT3j(PAhNg#B/t8<,>brtR@.W@p,hPHb*2O:`Q]AL
]A&d\-g6NIatN2Gb?bHQ3]AD#31*T\BIZu[-)B+J;lALl[I"Nd5cF7@H)Zk988u6PcKc\(NViM
o;$(X8*a#BlL*mFmiN)?bLiKBMW1B,HnprUt96BTA_k;*I,q>6."GEn-^c^4=:hT%b*]AsI%u
bdJ&U\%@_F>PtE5am([KJ()unjo[ET.7%4gSHIRWI7r\)G>T;b?KQZ)/,_Y>WO%Nroq?/r9M
d)oN5Y?`-e1a`E,O=:?-RNVI!mEJ7sL\Bk7pQaQEL'XR+>O(oM-9c3CbZqm!2&S2pBf>HlGY
tV*6-Ba`nV0hs0NebkRr-Ea:fU="5TD<Zq?.q*XnuAB/1=HZ$euL#(0CRSm[CO[&V3ZtPK-D
sOAGqVNPp"`QNqd)?iH,_BI0!8)s.hq"TYP2!uL4f&d_6^Vt^(_8jZ34gI)ke=('M.DC'*BU
<]A8CS:ZTS)k<]Aff"@m)poD.M&NemdAq3`pm"Eo`p43)EBsS[7$[mp>bj*/0c%CkDG0,cG^1`
/Kj@=DP5cEf3r).F+>3rHh?cE*Y6Nf@]AiV:D@"Y(X>$FJE928U7t\6D:\FVU7DQ:=WBfC0EX
!nl5[V'5dnVA]AhOtA2']AsK3bPX$f;^F$YHV+!F'YXhb8k+1ppB_E"5-t7eA_eG\%bs$$-KkF
16H`1uD+(L7?,N;>erG&nc)Y#f%'/7AB7X!a`cYggGV!H+6"nl-X%B9$&^l>!2>8%N*t6f-#
LIrRAe50BSDGksW*EF^H\hN/*I.s,_=ZK\?uZ&ajmHdd'`Vq:8*ZW-'q:A916LoY!=L\<Lke
([[F[Bb%B;BX=Kaj#Kl$G(9pHjAg'21I`s!bfff?4`pR8s0VNd^mTa87m8Dm]A$#Jh^39&"XR
c^r3m&9l6*6uC?Q*-_ip"1"N'Q-f,O"V`_0ru(6UG;Cr!LBP<a$UMP!5UQ'ZRoYiI^hV5R"O
_8Yg.,J\51CnA+'o3=3r0#cV9T+;qnX\r&g2^c87mKT*g7P3Ps"1A@7"tCesEgU[=q??7VJh
._)RGQmej2=9?4Pc/R$MQkI-:kAZM&2Is\pO3_9o[`aMiq*/%\:gK[^mmRbqU7N;)*Lld:>"
/T.q3`P]A[kR#lGh8fm&[q=]Au*C$"4]A:1$_q^I0%\C45`-HtY=89P&V<c4g&rV@>&0KW3cVGZ
dlZ#qPlCUT#8;K%p`CO*Y-mJU:JJZ9h9hU3h?Yf'=D@rU!?mW-P(Xr1HZcHQjQh-7riqWOZo
jJ>2;(q9<V;105'W:juO1[kS2]A^&;5S0\qJaDd=7<IJ?Yb<d-g\Mc]AN23BY"+g3BGo"#M<9N
uS;+<b9V3mG,5WkBg#i#/Z;KKSYLSkp'?"^UQU/`B.Phc]A;)"q>d*q[MNVlC]AMn57F#VJ,l_
m1HNgq_>?7>U^(Voe2Jr=V&doc]A6s6[oD%T^NUsf?0c_@5:R?+V'>l^_iufM0M,gjQF\mMqC
E'1kK4iCXUc2KL*]A+qG%&Y/pYh*Y7I=QON\dk?^Rc!%TOk]AGaDqOVQfW?S![$@J5Ghdi8_]A1
\T#=8tr]A>jXNj'qi4Ar]A>F/lO?-Nrsq".L7FRh+"oEd]ACLXDEdh*V>h!8s->TmhfJ9YFL]Ak[
ck\r#OT&:Op:fqkcK`8a^`\HQ^c:tBM-EU;;`T#fl8tdTJ!=loN<FK0L-"^u*S1b>%)`-!G)
U[Hl#DT'EeFHT.A,Om'U[lNJQX2R-$'T[/CakqAS'e;[@MuD<_#F[*nN$0P#:5j:4hcg.3VO
9]AK-!5@5G.R\%eBB?.oT1s87J6GISl7+'>/ndf6JaD6cHUhU5cYjp51QPB$"'k0E"2+*K>^1
AolEok`IG@'uojFfqU9m1ed'm-cHhs7^^"9<Q=3+;Dc@']AUA%5nh?0p&r^kBHZ,#AOgIL&Ze
<$So(Z.jSI4R-BW[n$l5.:)%SB>%bBM-n&JNH9q$5'"&B/VGcr6pPkf2))n.S5ikQ)\(S4km
k5FX^_-GSX1I_7!iOe]Adq5E6*eW>ludt1Ic-$*Ilhs5]AUJk+Gp%X&A_64Z9;`25.O?jnWc[O
udqc3GGcI843?7(Ph9jnlZ:Lc'fFc\;$'J')STN!=.b\\q7S#-E-1r97DCKp&q`86#V+0R(r
G0k-/q`r<8m'>SOl6H3tW,r.6b!>\BP5mD/,HDW\rX`QUOY+0&N'eD!D*CmFQK5SB_G,Yk7E
[8_S4T:/!HOrNXdIIu)KRic9<p5ZQ^f(fbpO7_")`)%B<T*uMFelHV/aC%>/km9jLao>:;c7
*'?u<<6S6,Bu7tV4i)o$KWS<H[IKSP/KD,-<Cre6Sa76<IIds0I-q8X=M')?V=j*eI7hB0/*
jA"Nh:gi$OUqBa]A/b_hJfloT4GLkm.m$O^tJPW.PW/ZYJOXj(O&d"Wp_:ogp6:5cd*WoT3Mq
*pa2um;(K_N9;JWFa1*GKD:`R]A.ggKI;['M*:(OI48RTEbSC^53r_!>hui?6"e6'PX6gdS%d
$IUPO@'"K<JJJ+?tg1_>.!CqF-h&qq*:MI$$L[=G\gi"ZMD4KCSMmD$15hP>rSb\[5!m#&0Z
RUZ62ii8SSXA[mRQE:coJA3;'ksEp4>1?Z!l'&]ALV,71-+>#i+kGc.)[B!m\3#LL+WYRT&/&
20P!V,c^lKD.W$mrT;UZ*tj>.IK^!!3AT]Ahr2GZcZ?=H!U3%/"366hT.OX*UWI0@r8t@h*_@
]AhCqhi''HN!TR5nN16ZQ0o0D1R$Z8dPNe`3*[po1YVi!RXBu<g^^aj#[_U@dV11PB4BMg?W3
Eh+TX4KsM?1V[XA\@$L/Jc+W(BWV9teEPR]AE*%@tJ_N:e^P%DqZ`8KaNjfnu'X0XeR9E%<d&
]AZR-,O#\/8e3H&st'3\as1.YKaGE;+$1s%\3j=>&%m'=F2.O?eTe`i1EoEQ0oh>/+qH6"ao,
U)>a:E6:P5\&qhJ/,.L"E9%>)@n6b]AIV7QE+L#':4Q`%bO>6)\4Pa1Zi=dn+/jh[#gmFo]AfE
OR/dn)qa*!bm;R\1I^nLMAL'_&]AhJ56bVdXO`PO7"c&j]A7R!dWRcP'Q]AFc$2]A+9AUlh;6qnW
:TB-Jqm&7i64=8<7c29?I@>j^5(`'+\mlRi"e-V%=[V#h*9GCN.^qmN)!,3*4U/Cs)*8PFs)
`[&G36t=o:OWLs-K%Ehca(;8bk@apI&W?glF%8n&d8\D9mh.EXr<\bL0)&eoJonb0rF#o\"C
(7G*s3%Gd]A",9\EtW4@c'7;e;':AdR#JZ-l*U.K-J@mU$fUoU9t8%&*cDLEUXcQtS-!Y.nZ6
;^H&1[+=M_M$t4#@U&8UZG!R)QDlA.%5#O7[/P/m@4%[2s\>df)DJ9$*;%-BCXAS'_`SF+Jc
1jZ[jA%LReoRg@)#&FBFJh-S-o8l;i3HYjC%t/B3X+7)1:ZltXJA'_jKi>Furb0LNR7<M"m.
PAeIKqZ8QMOhU\_)I]A[l6gY"s$P<P:]A.7<snhubPo0XO_?4Be/EG$a:(<V,gPO9P\C1=tTd6
(ZR<bt=74RmZ<=`r$5%4%;0p!B>)o7icDFH35cm>Dj4QcZ<(!p,uN71q2@S$2I>mYI8!oR=M
g@9*bde2+*^p%NfoT,jqno/'qM/>'c-R#arUXL4!g]Afi?mAAmA=gYUFGf3D5R*-=Dl43#/X8
$?PH8s)&K7LEh5:Qm17WLaWET&G!q-#PeeZWLlTq5:+:1$_,L9;IJla8g"kK-$Fnmlt<HGH7
[+oD8_\PC&t&00-)iTaa??c.PAfF%(!r?&IcgVE#=!ZX3K$')-?@-RH;u2f,S3aJAH1ihClR
FR4b@9upcrkm1RZa<hnl&'Y93)Ad%G@.ClA%#R1;n<S?+qr&XO6gO;i3gn*qcF5/\Cp9BEde
Tnt_*m*"MhQ9^$R#+p,$mRXT0Fa#?gui3(J0e^?!k*NnApja4pFR>/0g(ICueJELSP-Nqhu#
<#F8$<->sf.rX%r@ST5VpBe#/Jpps.%Sku@G=n'/RYgUl`W(r9IXA@"t+BsO1D@&[Pf_*\I_
Ir&\nki&0=\@M4]A2dQ^Y%\6Teb:s7Wi_Xp/m1MOEdlA9J@"g)O=Ad7Par7/$:?6MD*#V_.js
r"UFE&LfM2MlQS0RI]A&*+2i5Y!PYeDNSZ<2Q$C1oPW0^3IZ+?=Ai#RS;;k7NCSLo]A6CP2rT$
K8I3U2Gsb8-KH6`)dXW:U/%0hQ5>u:C5):8+-;B/1b0[$+o5`T8&d$+kVp<E\0drqFp9CQ+?
0D$KQL9gKh'AlIXbFQX,f%^a+Jb8/f1d3V$c6'NODEQ^=^XlGZYl9A.n`7E:Cf@96t*9a=1o
aE=NMf97%n(.iVPEq^#NbIO*V$3"As>_TR\AZ/^sF(n7=h!b&<D+Kg[_!cATH[XrEQ7'-Q^O
&)"*mNdWN401f;R^$X-:7e$KGf'qfMagrZW%:!Ze,$H7Ln7lnA.)-!]AR=efP<\G'r37UB;P0
'V<41B7-RD=&+7/C4C;MLujB00t>QGR9@^_]A3<HuL\6f)'E`*XZ9a5T8#\OH\%:lo?<O$a:K
P95O<D:M"OBZUe+>jAqB<PklU4JDsMG]AaGa6-_T<^-2@g>"^iBL'meS['"kLOI:kK*,/*F-S
b=-mK#^%c,+#&H$?;MXR.j/VPMA'lJt1#NT<QU"4IT12.AP&D2m^*6&q0B)d0&tFA0MHXZpC
HonPk2.rg2_2,Xq!<YlHp%l4U<[Dl%##-&;aO'8h3S"Ss-D(#)p-P5%bbCReWDm5aW-XZQtO
DZtNhj)u!bV)c(H",6]A4U#1]A_A."M3-]A62TO^f'-iV:\\i+Ht;l]A%l!]A)K^/Z+RXJ<J21N%g
[:Poac,c.H?:a9It6#c)X/^B`_O1@Fu9@?ooQZ-75>N@HMRJ5(;*Z+P-.)DT%5j2HGPM?NKE
>h+Yeg3?.+X5m4Y62\Vl>+*RMFT(`_C.^cQ8:IO0+?nP!$#gPJn48gRk(_24TPH-:$7O0j8b
O-s=A"h@:`oF;i2lI6PhIqhI'Gie.lL2C.!c]Ag>B?I3aCI]A@(=9h!W\5gk8F]ARE/\EiC]A.X9
QMUd>3Aa+pN5Erg4[0]A^9'jRna;0haORZ6hCDZ2tm<G1W]AT7tH-S<[mqg$NenEo&m9CejHeG
Fqa$\&Vfbs%[iVE.:CtD<4)07j::aU?(]Aj`#_&V5mUZ&ZpE9$"[dUoXS>YQnKW)ma;N$IXH"
%05L+u?r!r(05Jb1TLFi]A(H=?3]AS[DOae\Z]A]A]A':o#Hoe7A=NYp"Sa*)!TIrge+#-%4j!W>A
P7^drP,0IYq7t:-@o)%O%2=_X3F2<WSGL<qb/.EL'HG5leIR%qa8g(Ngg58$!K!HQ6$*roAg
`CJU?B)m+@jCY4/Q`?H>:7`7S4$?,;s`i:i.QfUqXn3Wl2Mhcb0pu[Xnm/EB*i<=DU'0>*!\
9S&*D$B^BDP%oag,!d(eI0'&QKL3P6Cg`156l,RFHQm(8po6+T<j<_-4)Vd?<\:+f]A%)+ZS7
,`EA95l+pHc6CIoV^/1KG"PikK48@pg&`8n;B@2dWMl'O5Sj1r`F/,,+_VVoL2c+0[/]A<EW$
gjfG.9G0ns>O`4tJFTM+_.!-^?r3q\8C]A8uWT&!(.Ia1[_eZW@]A>`C/e==_H1=g%kT<rDr8g
@Z%+I_KE&V1Mc&H/`.l+p_5bR9?1*+E1U[<*%#6`MiL3(e$_i]AcU?FI1;Fm5N?bZR[fMn<q$
0Q1Xlspc;s:!;HsiN:oV=ADX>0$PGR61EYGq>&a-,P-<Ho&NNY"79rff)m4W&c'`Epme)/ma
jV=KfO$[p"-il^CE/6&13>5OXAYdYOXHtl6Z4>e!V%f,gYUY8XaCus"7qTWti)Y.fXEGVM%>
"FIRS:dZ$b&[*AH-7;EHhf?m:Xs]A%Fu2[_8)@.jae0o#G>9LJN)qm$eGmUZ&WVC4hHfkVHn2
"VIXj'R=+/f-qqF!js$BkH5<?(s9HR+FSlb7dRDgY*bsIDTD-2(9Z+q%^'tSKG0'^a[fX"GP
SD_4]AOC(+P$;MOC$Mk6tK)Rd*DV3k`%(;3rD"br&TBi&;-:N>5!hnU.ML&p'[R25"[EX5\B@
Q?A!c@Vj[a,)6K=;B"jcn?6o!%gq.'+SI_V20/Yi\L(fp>dqheF"@@qW3N99M/(*jJKiQ%HY
CW/$:'.(p'lG-&6c*R:\H>'&<B:+0FH;.[GJ"(i*OAIpgm4"]AINYJ\d]A8fj7Kn\c4b]A[!#:<
j+sVoS_UP58%&]Ah=Q0hfR<>^QKI9sW5>U![R@49,-TnXgG3BKH2i)QTb#D<\bI+R^3,\%M\,
O<4!X?(3gLFh$V4Jo&jL\U^%dnl^0)9[K%FU/pbl5V;m\$+gq9N*gM\6p%A"GjFX-1-0BXI6
)aY:O.Wf8U!HuZI0/<>'2j!FS?ZPFclbia"rVS=9`'73"^H#m(T.h?o?f/#f;K8YpG8dL,TO
.dPc$M%1WJf[ZbP&+`7gT@Of(AJ[+"BgLgAo'I,?-;(W`kkG$=+b9hikL]AbKCYB'%EE1h!oZ
KdVGTWhPP2AIJN?TjS5>=T7DL*o+?p]A)a%rIqRMIf0a_gl]A9:G!s,Rh[s+O_BT.0f.nirMi4
;ZpGG$8[i(,I?f6KGr\ON(%I:?B7<)GX0D5KuN;63#d.LEeQqo#0k(gqrT2FnT_niV5q=s%N
77PTtYoU%mkt1DK$WIsQTO)dOqWiLkO0Q[t%1Dl-go%#"6gP"#JfZWDBS2`l^C&T&8Y0nVWc
Ot8:S-oA>$U%1)Q@]A?XKh+G+=(nQ^G"TeqDH*DW.R4-8\eH+U;388iod9F$aa<sRC*%]A_E\s
"DA@%,0,)>6G=HIjVLc]Af;V.ln@pOp)nH.?$cI%7(<*'j4+Q2g05k05:*N!,(d+q(Ln5Q&+N
5Qi=\s682Eh*0#hjE%FN$`1X.uLd_.G,&<jVP#Ng)B1.EJ`3Em334MsESeNm=7=-fDZOB;S2
fOe)@I#HSDDXc[E%@Y'i((#JIU)%$h,7!044<?'V/0;RZ,EWfLjU7*?A*tn$O7=bGF6^:dkH
T3T'f"D9lRlPhRS=Af6@e`L5?PfF+0O91RESAK%\]A/aA7t1<*A+q]A<IdIhIfi?66SpZ9h7B!
mI!/90%:uZNSs;@3QW?t;6=4bh?!_!6eU>E=hSmO-I)7F!9lY/)r(7t$(2H28Ks#N8+IkP@*
.NX?%j1&'X0k((p@Ks.&V*9$:3Y<a3XVjD^/;m1?4qYb`]Apb%(n@]A(23tELm$0/^f8`W>pG.
"J\D`gg/;l")N\?sUm%1arb*9?(*pT`NoYr[D0=u;[Y'nM-G)9:.YRGjCS\6c:/+0CW&Fkr.
HB9>MY^-7dA<L2_Z=0FYGEL96p3UoD>"7QkQ9LB+eJ?cN_jNIJ>D\XH]AUY`?MU'4cBDU1rq(
%oq0*H6QQ`/a/nj,"f/:4#jIc,7g'DL&qs9uK.i[FO4a;^HY%uA$CV&PSlY[tfFlu-dR_7rj
,E@G)3oQR%3>7q9T_O3'i:fGN*tRb-=@stkRN)^2^QF2p;7Pj$=,*>p)I?OQ5C;>)e=j&n7O
tBX0pWG6Gj@#E)*A;SDOn^>h0Y1YCsU1B6WAQlP?Ai*HZnnrN%8=#k8p&l2i>@+d.<M/H1F4
63U0jhr-r-()g'F=:T,kt?dcY*U`d4t@f$2eJV'TAVZ-/^>`-o7MmVW4`]A\s`e7fk+^-cN%Y
ec&ak.HOh5CAMRq%;?qd@'r]A?]Ag#ug9]AsREFJCqXjQ>_7Ge\^4E09Trt(TlP6Pp_C-_dsR6h
R-aS1oF5H[J6ruY.U?Ql1(Art+56^E"9%EUT\i@@>SA5fYDp#d@$(q__JFL>,[g+)`A`,/B@
[D95rmg0WL)!?>o2<^US<u+p`X9UuCHYGP:3YU%PHN3C,7^gV6qQHiAn;nN>%SMlYa+41Ei,
8oWFZ-J&f5c_jfJLHCT)/T.@W<=V06"K4k&Z6u7BHKI,04<IXn?J\L7;2$g"45h7\%hqN9CS
HSFi=iGZ@JuhK5@pa/8+u-aoB5pPOKq2mJjN2mK&6<,rse]AW]AD>&;-5).$m/R?+sql$XHqmR
,laF[!*2k7%R^CEl9=4G1(B;+9tnB%g1A^)L:5'!:9MX/AJOmr)Sij++j\IqY%u`Vj"6cNP*
"46(T!`-GS%ra0J1iVr#pOGa@k8\kK;Y[5IM9`9+2(.gi0OI!2LKT9c"=EO:Oaj0@^7?3U$!
U!%156;uF@(cBn"OnTODeX$,l[t65j@6P?iMlHqO+0@jpJUhT:UQmd$Y'KK5no\A`d4#B(K'
^C4g]A&lrIXE.SM.3Z@k2&p04&Hf"We8eee'!tKY2&plZ1!Pgb?"jqM%0t^[0%8+b*D'FKA>Q
?aA(.>[7U/o7u2WQM(Rg]AB=HBdD//qicp@(.1(q^Q#Q9KK^O.jJkasqkC`qoHEq>Oh*E)_"^
MCE`R^C)r#LZ"_9rbR-?*;21*c#=c&Mm1&<"@WT8ZOBo20on1SE\+@=;1D%/5#`e3$dEib?k
`5_Gr]AGNokrrFfK*CT7JV-ZV`2L+qVhh<cUi4_MB@&>r4pF&cE0LrZ0JQ49C?8,pM$cJ`,F?
XlO<lJM=L??-5mEYGMl.lBuC.r&^h_3EfDBKAnDX*T&Uc:7EaK'rhVe<HD]Al:hqRC=%FVnS5
`1C+FDh`,iY5q<JZ[MJS%NV;W[-,4a,o]ADZF$b&nKu:Xktg>:Ui5R-%PtrX["bZ*3fSO@711
L/?s[_*+]AJ#=QdR'Q7A6%\?ccDp$BF4)\[A@KnjDIMHU05<R_NM$03J!;H62%0jBk(%Fuo*P
]AK/&O;_[`;ubdKapMNnDpK/%P<BRYE/B3?ci+Sorg/6HY>+5?:uuC$%R;$Ya;kG`_<KjJ(pX
YWgJKlE2c,/@Mh[CWEg=b&-fG?R>;Zhn@=/\I".Z4IH(6H_Bha-Ig-J`M1ncMBrh"$"HeE.+
Bc"lYV45FCOh5`&Qme2)^O=2\$9#@9%/HPO=HmUL0PpCn5#4$KrPq04`8DEbOm=b%!6K]An,X
L;i&t*j7%&H!<?m@rS9UrM#bNq;qH)kB0)Q2,0<dGed56~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
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
<PreviewType PreviewType="5"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="b8476b69-5616-4902-8d25-42392002f0fc"/>
</TemplateIdAttMark>
</Form>
