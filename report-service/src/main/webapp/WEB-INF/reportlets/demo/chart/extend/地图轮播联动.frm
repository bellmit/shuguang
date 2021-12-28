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
<![CDATA[SELECT * FROM GisMap]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="区域名"/>
<O>
<![CDATA[南京市]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM GisMap WHERE 区域名='${区域名}']]></Query>
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
<InnerWidget class="com.fr.form.ui.container.WAbsoluteBodyLayout">
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
<Chart name="默认" chartClass="com.fr.plugin.chart.meter.MeterMainTypeChart">
<SubChart class="com.fr.plugin.chart.fluid.FluidChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.fluid.FluidDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr value="值" goal="目标值" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="center" title="=$区域名+&quot;销售进度&quot;"/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="false"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="false"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#00%]]></Format>
</ETooltipItem>
</item>
</itemList>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
</EAutoLink>
<AttrBackground>
<Background name="ColorBackground" color="-15329992"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<FluidGraphic>
<attr animation="true">
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="1" areaNumber="3" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=1" maxValue="=0.67" color="-16737566"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0.67" maxValue="=0.33" color="-16712452"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0.33" maxValue="=0" color="-467834"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</attr>
</FluidGraphic>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
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
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.meter.MeterMainTypeChart">
<SubChart class="com.fr.plugin.chart.fluid.FluidChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.fluid.FluidDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr value="值" goal="目标值" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="center" title="=$区域名+&quot;销售进度&quot;"/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="false"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="false"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#00%]]></Format>
</ETooltipItem>
</item>
</itemList>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
</EAutoLink>
<AttrBackground>
<Background name="ColorBackground" color="-15329992"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<FluidGraphic>
<attr animation="true">
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="1" areaNumber="3" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=1" maxValue="=0.67" color="-16737566"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0.67" maxValue="=0.33" color="-16712452"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0.33" maxValue="=0" color="-467834"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</attr>
</FluidGraphic>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="455" y="270" width="262" height="242"/>
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
<Chart name="默认" chartClass="com.fr.plugin.chart.kpi.KPIMainTypeChart">
<SubChart class="com.fr.plugin.chart.kpi.KPIElectronChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.kpi.KPIDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr names="区域名" title="销量" values="值" values_function="com.fr.data.util.function.NoneFunction" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title=""/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList/>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
</EAutoLink>
<AttrBackground>
<Background name="ColorBackground" color="-15329992"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<KPIGraphic>
<kpiGraphicAttr themeColor="-16712452"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="3" mainColor="-14374913"/>
</MapHotAreaColor>
</KPIGraphic>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
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
<Chart name="默认" chartClass="com.fr.plugin.chart.kpi.KPIMainTypeChart">
<SubChart class="com.fr.plugin.chart.kpi.KPIElectronChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.kpi.KPIDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr names="区域名" title="销量" values="值" values_function="com.fr.data.util.function.NoneFunction" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title=""/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList/>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
</EAutoLink>
<AttrBackground>
<Background name="ColorBackground" color="-15329992"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<KPIGraphic>
<kpiGraphicAttr themeColor="-16712452"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="3" mainColor="-14374913"/>
</MapHotAreaColor>
</KPIGraphic>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="455" y="28" width="262" height="241"/>
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
<Chart name="默认" chartClass="com.fr.plugin.chart.map.MapMainTypeChart">
<SubChart class="com.fr.plugin.chart.globe.slideshowmap.SlideShowMapChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.globe.slideshowmap.SlideShowMapDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr longitude="经度" latitude="纬度" areaName="区域名" description="说明" customName="false" customField_0="系列名" customField_1="值" customField_1_function="com.fr.data.util.function.NoneFunction"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title=""/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList/>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
<NameJavaScriptGroup>
<NameJavaScript name="联动指标卡">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="区域名"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREANAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1" animateType="increment"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="联动水球图">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="区域名"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREANAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart2" animateType="increment"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
</EAutoLink>
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
<SlideShowMapGraphics>
<attr shape="pillar" graphicsSize="5.0" opacity="100.0"/>
</SlideShowMapGraphics>
<SlideShowMapType>
<SlideShowMapTypeAttr style="black" initZoom="11" playZoom="11"/>
<ViewCenter auto="true" longitude="0.0" latitude="0.0"/>
</SlideShowMapType>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
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
<Chart name="默认" chartClass="com.fr.plugin.chart.map.MapMainTypeChart">
<SubChart class="com.fr.plugin.chart.globe.slideshowmap.SlideShowMapChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.globe.slideshowmap.SlideShowMapDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr longitude="经度" latitude="纬度" areaName="区域名" description="说明" customName="false" customField_0="系列名" customField_1="值" customField_1_function="com.fr.data.util.function.NoneFunction"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title=""/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList/>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
<NameJavaScriptGroup>
<NameJavaScript name="联动指标卡">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="区域名"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREANAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1" animateType="increment"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="联动水球图">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="区域名"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREANAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart2" animateType="increment"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
</EAutoLink>
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
<SlideShowMapGraphics>
<attr shape="pillar" graphicsSize="5.0" opacity="100.0"/>
</SlideShowMapGraphics>
<SlideShowMapType>
<SlideShowMapTypeAttr style="black" initZoom="11" playZoom="11"/>
<ViewCenter auto="true" longitude="0.0" latitude="0.0"/>
</SlideShowMapType>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="1" y="28" width="454" height="484"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="chart0"/>
<Widget widgetName="chart1"/>
<Widget widgetName="chart2"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="650" height="460"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="650" height="460"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="1"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" horizontalGap="200" verticalGap="100" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="f8ad276b-e173-488b-bb5b-008367db895f"/>
</TemplateIdAttMark>
</Form>
