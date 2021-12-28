<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
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
<NorthAttr/>
<North class="com.fr.form.ui.container.WParameterLayout">
<WidgetName name="para"/>
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
<Background name="ColorBackground" color="-39169"/>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.TextEditor">
<WidgetName name="textEditor0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<TextAttr/>
<widgetValue>
<O>
<![CDATA[ 日期：]]></O>
</widgetValue>
<MobileScanCodeAttr scanCode="true"/>
</InnerWidget>
<BoundsAttr x="32" y="23" width="51" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.DateEditor">
<WidgetName name="dateEditor0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<DateAttr/>
<widgetValue>
<O t="Date">
<![CDATA[1478793600000]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="99" y="23" width="109" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.parameter.FormSubmitButton">
<WidgetName name="formSubmit0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[查询]]></Text>
<Hotkeys>
<![CDATA[enter]]></Hotkeys>
</InnerWidget>
<BoundsAttr x="256" y="23" width="80" height="21"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="textEditor0"/>
<Widget widgetName="dateEditor0"/>
<Widget widgetName="formSubmit0"/>
</MobileWidgetList>
<Display display="true"/>
<DelayDisplayContent delay="false"/>
<UseParamsTemplate use="false"/>
<Position position="1"/>
<Design_Width design_width="960"/>
<NameTagModified/>
<WidgetNameTagMap/>
<ParamAttr class="com.fr.report.mobile.DefaultMobileParamStyle"/>
</North>
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
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<appFormBodyPadding class="com.fr.base.iofile.attr.FormBodyPaddingAttrMark">
<appFormBodyPadding interval="4">
<Margin top="10" left="10" bottom="10" right="10"/>
</appFormBodyPadding>
</appFormBodyPadding>
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
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-1" borderRadius="4" type="0" borderStyle="0"/>
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
<![CDATA[1728000,576000,1485900,1295400,228600,723900,1215360,1215360,288000,1215360,1215360,288000,1215360,1215360,288000,1215360,1215360,288000,1215360,1215360,288000,1215360,1215360,288000,1215360,1215360,288000,1215360,1215360,288000,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[914400,216000,2743200,288000,2743200,288000,2743200,288000,2743200,288000,2743200,0,433136,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="12" s="0">
<O>
<![CDATA[每日整体销售分析]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
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
<C c="3" r="1" s="1">
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
<C c="6" r="1" s="1">
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
<C c="9" r="1" s="1">
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
<C c="0" r="2" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="3">
<O t="I">
<![CDATA[2773]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="3">
<O t="I">
<![CDATA[1823]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2" s="3">
<O>
<![CDATA[48%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="2" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="2" s="3">
<O t="I">
<![CDATA[2635]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="2" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="2" s="3">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="2" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="4">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3" s="4">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="3" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="3" s="4">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="3" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="3" s="4">
<O>
<![CDATA[客流量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="3" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="3" s="4">
<O>
<![CDATA[排名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="4" s="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="5" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6" rs="5" s="8">
<O>
<![CDATA[日]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="6" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="9">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="6" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6" s="9">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="6" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="6" s="9">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="6" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="6" s="9">
<O>
<![CDATA[规模同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="6" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="6" s="9">
<O>
<![CDATA[同店同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="6" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="7" s="12">
<O t="I">
<![CDATA[732]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="7" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="7" s="12">
<O t="I">
<![CDATA[273]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="7" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="7" s="12">
<O>
<![CDATA[30%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="7" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="7" s="12">
<O>
<![CDATA[35%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="7" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="7" s="12">
<O>
<![CDATA[9%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="7" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="8" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="8" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="8" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="8" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="8" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="8" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="8" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="8" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="8" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="8" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="8" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="9" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="9" s="9">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="9" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="9" s="9">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="9" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="9" s="9">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="9" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="9" s="9">
<O>
<![CDATA[规模同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="9" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="9" s="9">
<O>
<![CDATA[同店同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="9" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="10" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="10" s="12">
<O t="I">
<![CDATA[263]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="10" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="10" s="12">
<O t="I">
<![CDATA[326]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="10" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="10" s="12">
<O>
<![CDATA[80%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="10" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="10" s="12">
<O>
<![CDATA[45%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="10" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="10" s="12">
<O>
<![CDATA[9%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="10" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="11" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="11" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="11" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="11" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="12" rs="5" s="8">
<O>
<![CDATA[周]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="12" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="12" s="9">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="12" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="12" s="9">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="12" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="12" s="9">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="12" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="12" s="15">
<O>
<![CDATA[规模同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="12" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="12" s="9">
<O>
<![CDATA[同店同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="12" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="13" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="13" s="12">
<O t="I">
<![CDATA[367]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="13" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="13" s="12">
<O t="I">
<![CDATA[32]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="13" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="13" s="12">
<O>
<![CDATA[78.3%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="13" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="13" s="16">
<O>
<![CDATA[-40.3%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="13" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="13" s="12">
<O>
<![CDATA[9%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="13" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="14" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="14" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="14" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="14" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="14" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="14" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="14" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="14" s="17">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="14" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="14" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="14" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="15" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="15" s="9">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="15" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="15" s="9">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="15" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="15" s="18">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="15" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="15" s="19">
<O>
<![CDATA[规模同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="15" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="15" s="9">
<O>
<![CDATA[同店同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="15" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="16" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="16" s="12">
<O t="I">
<![CDATA[23]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="16" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="16" s="12">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="16" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="16" s="18">
<O>
<![CDATA[28.3%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="16" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="16" s="19">
<O>
<![CDATA[70.3%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="16" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="16" s="12">
<O>
<![CDATA[3.9%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="16" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="17" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="17" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="17" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="17" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="17" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="17" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="17" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="17" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="17" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="17" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="17" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="17" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="18" rs="5" s="8">
<O>
<![CDATA[月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="18" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="18" s="9">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="18" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="18" s="9">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="18" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="18" s="18">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="18" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="18" s="9">
<O>
<![CDATA[规模同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="18" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="18" s="18">
<O>
<![CDATA[同店同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="18" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="19" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="19" s="12">
<O t="I">
<![CDATA[233]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="19" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="19" s="12">
<O t="I">
<![CDATA[323]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="19" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="19" s="18">
<O>
<![CDATA[45.9%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="19" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="19" s="12">
<O>
<![CDATA[31.2%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="19" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="19" s="18">
<O>
<![CDATA[-11.1%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="19" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="20" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="20" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="20" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="20" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="20" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="20" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="20" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="20" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="20" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="20" s="17">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="20" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="21" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="21" s="9">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="21" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="21" s="9">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="21" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="21" s="9">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="21" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="21" s="9">
<O>
<![CDATA[规模同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="21" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="21" s="20">
<O>
<![CDATA[同店同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="21" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="22" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="22" s="12">
<O t="I">
<![CDATA[23]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="22" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="22" s="12">
<O t="I">
<![CDATA[233]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="22" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="22" s="12">
<O>
<![CDATA[75.9%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="22" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="22" s="12">
<O>
<![CDATA[31.2%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="22" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="22" s="21">
<O>
<![CDATA[-21.1%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="22" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="23" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="23" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="23" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="23" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="23" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="23" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="23" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="23" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="23" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="23" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="23" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="23" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="24" rs="5" s="8">
<O>
<![CDATA[年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="24" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="24" s="9">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="24" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="24" s="19">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="24" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="24" s="9">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="24" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="24" s="22">
<O>
<![CDATA[规模同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="24" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="24" s="9">
<O>
<![CDATA[同店同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="24" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="25" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="25" s="23">
<O t="I">
<![CDATA[273]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="25" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="25" s="19">
<O t="I">
<![CDATA[333]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="25" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="25" s="12">
<O>
<![CDATA[86.2%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="25" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="25" s="22">
<O>
<![CDATA[45.7%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="25" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="25" s="12">
<O>
<![CDATA[1.3%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="25" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="26" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="26" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="26" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="26" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="26" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="26" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="26" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="26" s="17">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="26" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="26" s="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="26" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="27" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="27" s="9">
<O>
<![CDATA[销售目标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="27" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="27" s="9">
<O>
<![CDATA[销售实绩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="27" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="27" s="22">
<O>
<![CDATA[达成率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="27" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="27" s="19">
<O>
<![CDATA[规模同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="27" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="27" s="9">
<O>
<![CDATA[同店同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="27" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="28" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="28" s="12">
<O t="I">
<![CDATA[2387]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="28" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="28" s="12">
<O t="I">
<![CDATA[33]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="28" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="28" s="22">
<O>
<![CDATA[86.2%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="28" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="28" s="19">
<O>
<![CDATA[45.7%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="28" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="28" s="12">
<O>
<![CDATA[3.1%]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="28" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="10" r="29" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="11" r="29" s="1">
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
<FRFont name="微软雅黑" style="0" size="128" foreground="-1"/>
<Background name="ColorBackground" color="-13712703"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="1" size="144" foreground="-13712703"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96" foreground="-10066330"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top color="-2565928"/>
<Bottom style="2" color="-1644050"/>
<Left color="-2565928"/>
<Right color="-2565928"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="112" foreground="-1"/>
<Background name="ColorBackground" color="-13712703"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96" foreground="-10066330"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-1644050"/>
<Bottom color="-2894891"/>
<Left style="1" color="-1644050"/>
<Right style="1" color="-1644050"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-10066330"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96" foreground="-10066330"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top color="-2894891"/>
<Bottom style="1" color="-1644050"/>
<Left style="1" color="-1644050"/>
<Right style="1" color="-1644050"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="96" foreground="-10066330"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="80"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00%]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-43677"/>
<Border>
<Top style="1" color="-1644050"/>
<Bottom color="-4210753"/>
<Left style="1" color="-1644050"/>
<Right style="1" color="-1644050"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00%]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-43677"/>
<Border>
<Top color="-4210753"/>
<Bottom style="1" color="-1644050"/>
<Left style="1" color="-1644050"/>
<Right style="1" color="-1644050"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="96"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00%]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-43677"/>
<Border>
<Top style="1" color="-43677"/>
<Bottom style="1" color="-43677"/>
<Left style="1" color="-43677"/>
<Right style="1" color="-43677"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-13712703"/>
<Border>
<Top style="1" color="-13712703"/>
<Bottom style="1" color="-13712703"/>
<Left style="1" color="-13712703"/>
<Right style="1" color="-13712703"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00%]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-43677"/>
<Border>
<Top style="1" color="-1644050"/>
<Left style="1" color="-1644050"/>
<Right style="1" color="-1644050"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00%]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-43677"/>
<Border>
<Bottom style="1" color="-1644050"/>
<Left style="1" color="-1644050"/>
<Right style="1" color="-1644050"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-43677"/>
<Border>
<Top style="1" color="-43677"/>
<Bottom style="1" color="-43677"/>
<Left style="1" color="-43677"/>
<Right style="1" color="-43677"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96" foreground="-10066330"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-1"/>
<Bottom style="1" color="-1644050"/>
<Left style="1" color="-1644050"/>
<Right style="1" color="-1644050"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[be.Rj;dHjbC]ARL]A'9G`u&PtM$U^IGDRXihEKI3o'OkphK#R47i?::5S%=um%-m>7*.1$+_Ls
uu^kiE2Nchm)"S[6Z!q`STli6'$_>-j5Lme\&8c9/kBI?TDKSatF<@l1Z5h9\6,li`.:GNgp
fWFWX*<p-_tcC).[.,XVf,Lkrls1FYZmk=XG+sd6_"@t$$k3iU>!Luc;$aIf#^>r""\W@gqg
\OM#q6:<',1i+GUBS01N]A)Jo^E:@"N,'5cP2@#)1W5iY?U/B2^X$e4b+oalC=?$@GitHM$ej
icD"cR'LEA&hlu>I*D_Bflp`h[L%%b>u@VL$5#O/HscI26,+UEc0Y]A\iE?CSHWSV%OWHI1O%
'1'@#9jV%[VBFdh-EeOC&,=mXbU*ec.HWcpH)\>-euD#ba%ELc5S'<r%d\p[Xoj)o-CEdgKM
p!P^!_"m=![Dm2`O>Ci!.Mu&dAb7]AeH0d6'tDK3Ma4p4fEo:9Fi>%Z66<[b0;<f.fn+a*0BO
YO\d#\-ka+n9SImnag'\,k<c7ZBjnS-(EaC$I0'nt\EtjR>@(I[b?6/u!/KT]A2"96UOJY?C=
66kWb:(t+C6RXoiWH_j)H1aaSK'be%TDAHF74lUA!8h_Sit=H1XX_TR</ZPJ6:Z85u)06Ri[
eq25:=#XUcE0<4cM`TkeP.`]AT0pIgrCsm'+aqF@O.a$!I?%N$hs!+n`Ji)kS&EU)L7ZDk1m)
R%HP"$/8'&$<"YR9DdU9i-U%iY\lL`D!;sl0ehWZ#TL#NlZ"VrQKt$jFW??d'bR5OerJ$O_T
cB<nUiRq%%t0TdMQ\\.+_#R`^R+<!$qF.j@;j("M>sgVE*F]A=TND;(LIsbjWrJKd9mnah%pQ
Gp\nt\L\gE1QXL`'"%[S]A"E(2kOMpn6EGYRBs5!,LN'LT!$V4A4"OjM^L%WB$@Peu>IGZ=eB
OaG"]AbIn,>eSnto5A)poD\_?TW&#l\-C1":(gG/Wf)HI5A8C91tRENfY7&@D.:!;7qJEF(!5
R9-quA;:r'The`W^r*u'(iBr2M[)AJoZ7P\"aFer3@q"(E+!XEI>(TU+S8#KTGrQ"!9#$pIm
.U1au6eTq$Hi=uOju+L>D@:B.E$stS!&N_Ij('bdjh1;EkeXPkQjTfd97pC_8+$F%KOWf$T(
p<HVk\>#+ZGe\E0pt`538_%LTcFu"Jt1*Z_&:%7bOm+k`pA`,Y\$<g8Nf@H-KFu8EaK9bShQ
-P<!?='M$\$keSP[fHY)Y&-!<c[BoV>8e3ta3m6aED5]Apk.qi!NG,-B^8ZIHd[UCr!bL4pMm
JbrX'.f*in9:Y6AHA:tESIPM2rBP=@2F,GT#4NO[Z(A:hW*pjo]AKu"RYoYX\@E/T/#n[(lM&
HD-R0CZn)DV94?:(37r'YuKQM\nL@$c.irZA6X[hScp6RIXXUT^i)Z:)qTs&;Fr]AO-ij/GeA
FPH$pAl6AMPGlRf-7Z&6k.'QOWh5BCM`ne7f\fJbSU(.UH,PAFEPGhSIJtU[B<-ArZgYF=l&
E3]AY.<r77IR&;6pe3nf]AYU6OYh9pD&;Ws;:Njs\\jj'E4t@r/U/a0Qp35Q\J*kLksc?r3j)4
SV0t[MhodS@U-Q`02Q5I[P-Re3Tp7A,o_!.GGGla#c64WW$Q+4T[r>:^@[8DeQgZA/lafE(C
,hN`OCKkha8OT^P11Ta"SI[gnP##<Jkd4;L+QRcO(oddpaVA$0JA\_1G)`!%(1kQ6D@M?m-L
h";cJ+tCfF>9U*aKdr"qS'N.:"2,2OSfBsg";rUtj+Sf`-jG@`aBM_S8`OndRp'i:1@RT++5
d2BJ4TZ3]AE3l1l+%XrjH`^;L,&,eg)0;3]A!g=.OLZH]AVD<Zl7(+2^kKbGi@EP)6('V%V6]A_m
*i#`P:Gnb=V8q_*%T1d%qY094cflK;;,:\T@)@,5qDSVb`pU(37#)CcOE-j#btS\HhnOo7J&
b`H(`4Wo9PqTs^"ent,AG%5-X&F_^A#Y=*i9T^2F<HfZFnS:UF7_=[:63@+7i?-%/]Aoc=bp[
$tT9HI:"]A@hCbeZsS3t<gV;,=5V1LYOsmM,C7J,4S<a2nlD/">>82FYC7DZX[;j4:mD.1/Y"
!nM3:D$a_8i1NgFQb<ct=WL"lPe]Aq@Z3B656)17j/tL::+B[f%oEDB%AoU'LM!+(3pW-4<jg
-r$TO7S5o#bUq;5"M1nkSR'+Nds*HZh)P1klHe58D^g>j,ha/2DG_#UM'Il+VEuKCbI4MS/X
OGe_)CqYG`h2I^MIPFO:q;>MGBBIAHn?-`Tk>f)G07aQ/<tlF:Xm3ET6\l>i@B=TCLkYN_rW
ZKTI^%MNR<_5hR9+)Cs1R,D;k+[QL&`dX7(nmtQ7bAoYTXbn^^a\KiueLfb*kSJf^-1g@oGm
,1m*b]AMUl/*hra7aQP1d-DJn6$uPdAO%b:d!`]A\&`de$Wl0dU!Z=`B$2nbeOI6o`"02q_]Aq\
#'Zt*+PA_98[.Z+.5lLjeiK8dcN8V7j*?%As)QS4X^>4Zt=ZUYL(ma:fHa0e.oD?(T*;E`S:
Gd[UPV>R91[$bm!Q/r/*NK"Gt;NJH4cl<Nj,e\8'pi&P`2F&.`>U+NV=gWmn]AB$-QL+K,l_5
cJu-IS:U$Qga1pqul?KbAu70jb/9<GF;A_a]ARVGhnLGMY>N$\PeKh0@cpIeoUJ^"1N._\1@7
oTWh*63Oq:S3X=PL!hV`rh3]A5*U^hSFe4_X\RD4J+.&#BcFI]Ah%!*RlS"j<n@F;UVD)SBq-l
`k_\mF36YBd+>B7++J+r1</lG;HC.7Lck,b\>rn;OdS_M4SF_-h+^_/LAliZB;bfY[t04_>u
r%P=`N;A%]A9gJC<Z[a6Cqm,r9V^Od@6B1-+h\3_%<uItO^:c.*>gXlrIQUmfC!(8:uK3JLt<
I96/i:O<u)F/1H-W`01Spbml$U7ej0S?#4h(ltmOVeK3kIW_jn[S'E&qU.&FP%%=G*lLQp8K
cmEg]AKH`+A>su8`P;\>Q=01O*AjI,9"VnlOc>^e_hAVqV\PB*uWIn';.I21ZFign"eSVX[eL
(&+kogP=6sbL1V+!FaS"[rfeutkTp\*r(4?^q=kJe^_R\$SE(q"ni?0k,EKoBM>PA55qrYu.
>W-9^[pm6Y/(?ZPC%fX)[/+AHA]AQ.A!i=rd(8&XRj77p(Bnu%1*k;Vn[J5G6SL\5IqiR=L&e
'%a3Jc(GY8K+=n\a(4V6rCbbUCX[(;bmb,P'Dc\fY1KLA\Ah-Ol!4%3T<n&MB5YCh>66NK84
pqIlM1@UoaR%]AcJ`ZKfA@MaqLRpmt)jb!1]Ak>E*,k(_:XlaM$fk/B,Oko]A92m20glmcb:REh
'm(f-kM>T@II0=erq=lQ(q/IA$AH+nh5q"%$LtNU@5OV/'#IIatCFcPkg"(Vi.\f=rBk[23B
,\HZfL9DEZ9f)BaVGAu4>7h/Ac!dfn@`e,**o1<D0X/`KN<?D`>Q#]AH!)8*e":i=Qrfb[nu)
<rp`J7LafH&,'5N"kikf2$1\bZ?9/o/-6.5dH^j*,-Oq'X2T6GKNCC[,..c"$?=G^0keNqD3
e_c.>nml)kGOE:V)0cP56P4F3:JSUi'KlP]A9V0:I'@:^h7.\oF(D52LS/e"@,2foMST*:C>]A
DY@`+1kF>7pjA:UCP"B[_(Y/qW!G%V,KN)5Vho;V^ba"=J$"bS!j!:]Ahl7o8&ig_VaA$JW_,
e!*^:5iEj$MD%AKfeN>_KeZPX]Aqpd\?VU68:?FLV>K*ehm#]Agm0Kqo;N:1S:3^]AV$uXlTeWj
7>f17O/r>lEK>lWhkGZQ>XVA!SYaHu;&$G;KfOEJh)Du*YRR6JS<Z/Ml"_B]A[*rDIQ-.Q3*4
GOho<^-.L&mkr'mJ5Z!$e:kR)o]Agn%uQWu3/U(lL9-h(HWA@%]Al^?(1b]Act067c*rgK_$\hH
s5*`R2tXtY2/\*@[GW%(3Le_tun`!L$X,$-tF<`)`AfThD-FT[).%C[*7WB9:F)nuc8Gn'Ek
_jrF@Y]Aq!)s3M%GkkQdej<a#X<[)u#LbH/ffVt0=\h`Z%g)-u]A1:Aq*h>*-Rr#SAZmQ=FgX.
<8j7$J%QRMUYi,?K@LL.T=jJke;$oIl>l-?Wp3@$]A0&VDc!&9d'cl/+GFl<)lt\-;0lVk8u-
lfTLgfW,1VU]AW)W'VWH=sC?t7t^4=&bi:]A>3-WSlJjO+BeA6n,7l6Pt7>*!;7mO@UH]AQV0Up
"0L8nA6,R4i>H[Rm[cJWhI`%!_G``aFqO6&;[%j]A!<1r.)<C-p?@#p5Nn@&L:f;"'.DH#$Gi
^fIaY.U\UjgRpr-S/<q/1bDsW/:1>g3]A[9NNu0[^pk&I(TR7UE;<lI87a4&=&4P08=O"0#U&
@gi1]AjX@K+1c@`t^H)7,RV.m*!2&'\m<?h0".Yil$fRfC)erpq-TMg%ZiTKXg+l=BD9mU;kD
On2RstKMlmH1`oMqS&:ED0LY<3pt:9`bBVLEIh56b^NQ2<?6NBG+.OS+=_(g\fmqE/2AZX8f
*-'`)"g0),0X'5;Fim`YdTin(CiN&g$P9]AK\XNrMi[$s-G^<:u<W6nRLisTXmM:*MXS<Cm"\
$:"4))U]A^(dKc,a<lOVa=gPdkAS+3gU/828ZfHXMrLC]A*[dX.dgbV@o#Xq^!tB_o76"dMl1[
-(e%q%t-o]A6aXS+0]Am]A$9]Ab-J7Lf*fJt$Eonc!]Ae0d/QdQs,aC5r$[/UbUENdld[Uu[XbAF@
]Aemajmdh2PQL<j`BlT0eAB^f4V5n,(\>CYW'<TYV^h2eJE>b@&q/"cXJAR5rUFrQXj;HC4U,
G(S`DiDMnP=l)Y'L!P-`t!Xh\@(_lo!+e[U8]A=F+.<=HrbX%S3PpA<XH>60S(<qkZ;GCgqe2
hm\8/,X+E?BmBb82*b1+#ML7#;7"7bn-sP`(ClIX+7MR?lTl24Ii8s9.Qt]A%2*?0-d=C(6T-
dU6g^T!fHhA3"/-M=U&a'Q79)Mj""<_=AE!6VuV$\b(J=3+^`Q_kOuJ&Mhe3nj89W[V7kYKf
#G0V$nq-8bJZ$M[r"k\,4S3.9_YMi&?-["LNeW#g=%c[r>9_f[UKk\fm;Y&SkDF^`em9/A']A
)<r&LJcrnK.&A+*)^nEm'%M"SBfPO.]A@$Kf1>sS<I?Vu]AD_f7a#!5C`q*+=QBi[<+-!sggek
Go-XK2Oc`9b!TBdE-=(L,&jeeBgIX8ab*npg/u,nj[1D,p5'SMDlppGW3`%2<cY9VsOjqYP+
for%g2Hq)Pk;P]Amtm,=Z:`XV&7rFQ2+Qm_p-LG_QuL]AMhNGZ0">0nA7"!+Le=g=$4:&RC-_1
?i^F&]AHKJ?MC@E-P7:3T.HRN:QNC6Je>EIiJHCTVm%q$2M2*7C,F6Z2r#\uR_NLKGI-)7le.
B^3mZ`+Ce0b91]AfM*S\E!hLdqm"#._VjM#>[74'CQ3-TV=>i8\qqg<.PJR"^B_=QN,DkU1QI
DEF`dl8NU<@*@jA!-S/$8*N=3XXMp%bl]Al6NEU1b^#7'+0AT]Ab%gW<TkgR).e]AjHTr&Xd_"A
KL>5'+#iRk+\.S>2jKW&\+BYbS/p4*l)O9s;)nf0`7>4Z4&HF[!+mm8_*D]A$Hi0Z]AcI_._HU
C=Vdlo^l?gn2JH9mDnsm1o,HKAK0f)>q#*_E9]A8[*ab+C__hGffLJH0!Y\p-!/8OI)_o6q6c
Ak]AmY,du>.+(&MXj5J(C77^M1P222W]AUM]A*m2AH#%m-/)S6kF6`?4+M/oLA@MKcI_#T7gk.h
/+HnA9uLr4QsWh=%!kQLKgq&\/d2;8UuF6F!beF0^uiMA@E_fA<\FL:;:T[iO3cQ9!K5mbX>
4.bk6K6,h^'Agi`@M>=)mNP$lq-fY+e^uJn0(F)1f>L#[aEiY?[4IsOg:LRW#VN%XqhAQ,De
:@RDTWHZ@,5Roj2j(dH8J`19j$_gniL9/Y@YdVW&YX6=X4=%Bc#4`ApTA\8n!I.S0)Gs95;Q
SRiq(E[cb5eG\a=[G52EH]A(aqC)8'\pmqo<;<lBQpHUmBsM^-+sn&*(>6gLJ?a!Dtp3^jePl
Tf*nXK=eTYN:_),\J1lD8Mu*H(^AD::s.`(s"(h#!AdL#^E;b=/EJ63R)U[5$k,ael%^lWDf
ioQ&(_:,n41s?!aO>7$/uDWn]A%Pb3p5bbG^'LKQ]A=EN0AF,e,me$?;hX+6sAPu,YCU:1_dhS
Q>**<gHId)c_(4l)>YLO@78\s^BU!T@>c/FGbNDnTPIU"n!H<o_6Z<8N9OT/d4G1Uje1GCRP
DB[9:c&NM!^1(?ghdb92d#c$-)>^EThPV1a,\QDuc7&hDFcYQIQsU5[ETI!WY6T7q<1D=As.
CXTZ]AG@SOCiT"R;$/=Xo[0U&HoCFcCh!7#V001=Ao/=:U+TS;Vn<`*#H/t0D-:f3ZVlu00o,
mI>SX!am?#[c1).nX@so<!jK=oiPD.>G&6:X>:0_<lK#aK\L"Bf'%,=%tSn8X_F11.7Cmc(>
_-E$h7k'i_R+iGs5(*c-+6VSgY>O=%'D2eY2=doH;33e@a[==S5F+]A,R8gpa'410Q(=T@QZd
=tguXLICA7Qs@5WGtsMf_i*8BWgs%?+BBiP-YF-Qj)YjZl]APkMU/IX7?/`[*]AU[qpRZb^+G@
C`oh\?c2;N_<DUReD5M&o7bWZL'?ko=4QYUUCe<mJq@7XO5<VS/$X>qj\9E+N`TS&br<5;.e
J7!+Z*]A"9tP#PRT?gM<sfQ(>8%VK'k(0_VLWeMh>$ZQAJKcPf_#"m7c>C^6BEQdqj`3Z'!PP
N1?Q$Vct"h8*#R)m'G-7>;tQR<,.gB6')XYK*Steo[W=qcf2:[D(<`Yc6N7R;7:/Tq3pgc=M
]A4eHQgSO"%U,'$TOtVP=cdl1JeB/jSub%HGBt0k4b(6`kRt+I`ADP6*KO=Sa#T%;4lb,K$[,
)[<Y<m4G[bFu9HAQ*7W@J\N$a2]AK!TA,aEJiZ(^UYg+riE;G/MX'NFXb7@tkf\F=5ZC8AZie
A9rP^E,TSF'&d\]AQ6d-L>IXUtpDjM0G62Vms%)47A7@V-gB%4:B:)-K4R>LaJZ''73S5f^Ce
I(Z\I.75Rsa@I!,q;RAU)%XCII[<4'p6Gp_W4F\Z:I7EB]A,TPfVmPf'JW8W%p_05?(r7(siK
"I/]A$&]A6M_>t1&rS?]AVW`GdT$)u5;5%iP;HgTn2jiGA]Al<jK04`[(:FdU*+JY=6jWJ]A\q$"G
>(a4_nZ"JT6L)S6=-XL$"efC`M$8YTgL@,G@0Z)RUuh`l<2YHq'6D_a#h4N,ZdAMN^0`1"u=
lS)GVH[<a$,U.MLS[K]AK?!Y]A%'`h+nlrKB5a=t-6#-1)*C5Xh%G\-jo>&M=WB[s#coZT#r:^
#H9;9/Y.E:QBKEc5$sq\4&f9W?Nm';Iq"druH%NI!ag>gN]AQJGf/r<c$js)bfWna8#"C*N1"
$p,f<F;LP>NqL,==8H#0mK9qm[0dECafg#>Ceqt?%g2Ho8!&_lT[C*DRF+ZuZs3qg<kp@e2k
!.pO1ekLJW7%<A5@R/eSaFar*?i(e!_4L7<Hn>:-@rp'o=n""\=k4pT?]A:,ZhQbPqEL[1";]A
&]A60Wp-=oY3&g6ms@S'*:+WOs(k8Vte*%:.I(MV"!k*NX5=%'PK&Nk5FqIh%X?ImW>(<%#F[
k5ca)g8X5BOetb,P(%,1HRqC'EUr@'DC@u)UkO,,!rbcFf.7hmp!KhLdn<rTDLNh]A<>NYfMT
eZB?J>5>;f>3b<&^ZZ?#KZr>&<XVC\^FCT`/HUCgd=fe+8:_a`4uIqZ]Ac]A`l0R6NQriiRahX
`p)JFt'%Wlbr(DRsc?*Ro'M)l7ANTT/4Rt6`.k"@iH]AH$_"sB-j4c9M!&57eL>Yn]A@R;(3f\
c>hqeV/J40d5PTG@B+[S1@r/Nh:/_(kYbqDZjgHiaTZBMk1=8:4<RW(n,=$bu36AD#NiZ\*-
q*AU6dXjV)@7I&.(I:N%m#:;,\fbes5*rM5U=mb5R<Nok&VaCV_Hj=mGBht)H__(\Z%)$k.e
a3]Ap+'C:uuE0c(J?6C#7i.>JI7FI]A3[rK5S[WK*^)(BD$i^Qg<Z-LsuoZ(%X,WZ7u[c!AnQ[
(@+'+&j]AF`<_rX,;)Y`D-2Y7?%(\',>g01G3dQ<DF4:OQ#u1@bRuI6(-_EHE]A^]A1i5<=WlJT
Jf6D_m/dnUp;k&GpQ>eu#l-,daVKd1#NrVdDA"XfMY30GTJ&5[=,$b$*be!r1!jBf"'H2g;[
397lQdA*O`26HIg1m5>lS;2Rg$g_E0Tlb7c&Wbn1Y`UEOFf?ebnOIU-B2$Rm/'UU,d`LL*'\
rA%j_;V"XS3Pr"=I1;odqonHKUPdV`UK/5/S!mG?P^%P&#;eh9A8"Kg$_\C,F;Ydd??&;p1P
2nh1R:Ie59Gtq:W.sUjI)N<Wt1'l9^(RbQBgV36lbQHG60=$,0P/@LT<Isr#.#a4jYONQphH
T^:NU3BRr(rSA[Mg*Yk9XE(e?8f@Y-5LED"#a2#uR*,hnOCFdC-H`5+GNI9)6\.@u'f9>>pX
eM1E\<nG2Za8)+BdI^K8KEi3(HN^2)Kl:cJ74WI32Pd?W2bY3atK>$j12r!9lJ()jJ0`)<60
Q@o+_N[!,NE`+NqV+kq?Sbs8.hr3`R6VE):hT[41#F0d#k(C+=Y.JPk;3N;^Sm8pr3Y,.Q(_
G#jXu$Im%QnDYcoh?r7n?V?U#$44t.A)=I2;W_.NA5a_BcUV8Y@5n&<hc/Wck/TM434cA-SF
6*uO,&-DmE8$$D+`9eqjBmnI`pEr)R'csVI]A4REN,G@QOp*1b>RY,>B@Y'mBEPhh)BAS2`aQ
hQ^)(nZIqQ_sa1-MlLTCBrOF*;,s)j+Fhd@a8VC$c$10<'TZ+.#&\n(CYdXDSbjfr3Z)/gX<
.[ktFpDZ.k9iP,J"lt^iZQi2#e*b>0,1)LY$*Xc[SM5hjTELP*qL\'Rp6#W@-)HpAfraj@up
tY*qMWQOo[AKk)FYjqt.kZEan^YM*4#XGt60m;nl@:4F8I9jDHJ"_ke90!3^8s$K0B.)NKD@
&#W5,X5U-^^H@Z=N8bV\'IN.!W86(+(g*o:h-YYg@_e7G/V1a2BTV'@YR(i:!`dU3&4?;1>d
_15DB4.F&\K94kkYrg)]AXP9JC+,K+E#cj.IVU5p$Fg0HuTjfU^?HZ8bf'a^)'m:a2PeO'dj[
%L/K[?UlK]A(e]AHKVr[&MunqBd6I5\.Pc29rj,2CjR*$oGFJcF^8P^od]AOBVe]AK7faiL;9`gq
1T>fc3(g0\OHs"_WMu:nkiOX=8X))fpir'VY@mt`n8/$5m4GBlbYG0Y8\oUKHl_jW==*ON_V
*eliXaD1<lUrH:(tVu&0SQ;93o-JMZ>GT!_M02a($a/<F;`T*</5W6K"5Z3"28a=*+_^f)b/
1RDc]AY0><\AE;WJOh1YgHX3H-NY$oZaX?I".!Q\D.59s(/c-YD19iT#j4Z"?8AS)a,e+tXb[
ABr0^\=VjWN]A8EG<tqc\2T^Bhm%1WlrteB.GT*shO]A`_0o6Z;fE\L(2/N[T2Lti2Vk3V94,g
8WF=sA29B'Q.(Be^-P4i0K>`g6,J+;t"kQe%^!hb;k=2DguCP?\;YBOC]AeJ*uL^BMHLdGab?
%9Ccb0J'Xk\e75t]A0*aBfB$pN(X*h-1;t@7F-U&m+I+WA$%\0j+kH%Egl!V(R/4;WOD'Bq%^
[,LoP?Q*N[c+f/)=55eIc=@h;e@u6kb5-6Wen:3<COH2cBT9VN)qNP<@"`'QphfAKPRnr3he
W9e8IFb`YnHHU<8P/jqq7$9$?HfCI'LAbS147$_Nj6oN(@Y2S#-)k1%Oi5;)_TWrLl&^`57m
%&\3Zr-*<ROc/M/8!Kj.FK:'5CsnT(l+<i\-QPDsLQl/!2M^2)ANX-K6(U+s/mS*0VYuKmg(
O'Ohh>@ZYNG(P+#/iQK1G:dOs&Rpd?@(^EW<nF^&"ouHV7l1VTKqbA,a2Prj1BM.t(E`MelJ
oo]AGN?j?c=EE7":ol[:b.")aI8p4hLS-Rl:LZ=^;i]A]ARgS"Oa1JOX_5pnk6/<Vn@<30.rr5Q
Rr>E<sHt!T*XF%g\(?\Ak3D$)%^1OVu(sFp`@RAJl`?8h<ZlDcA1$$3tSGK=E0WBkQo:&R6g
?()ktA)5>?Q/(H,Da*ES^"_\LK$a=8<Cn.bAB#$KGZTS"\&?(lpj5,]A)mr/-#&otCS.Zjo2L
\8TXm;Bs_a5^B#6hSi9b7K'm\\<',(R0]A#Z?Dsoa%Rjg%nYht$F$f#3YtRZ9XonQaC,)0'X;
BKrB@3o+Yj^-!48UWTT`OK&GF,VIDaO\i*`?._oKn(;7phBl93MD6j@f[X+e]A=_PJa]AYUS.6
tl7$qj(2/Y4P9sq,!R-^\]ACO6H[sk*KAVo,6_'XhM[q.hB4>TSKK5!%1oNtHd/_NOlX(%bSM
p34($,@I,CE%qL]APi^-f;/_#1q32gelt05XKgQ\)oajaGmlo31ipE+W`#m[9BT`&MV3(03i9
6(:WF!%&ut(u)'+HC89Ju)o_q2KL)E:`bMk"Gs5URZke#_DJ.PDR\:Q>jp8![hU:T=R"(-[5
WPigYc4R>^/?q\<C%Lu6gA7\+hifW,D9K,!l5uO3T=P/I]Ac&`%eZ,SC>nmGiY;)4!HisPM(F
`e(F,o(Ln#+mL&`4#sR+#YeF\\U*"34:qhK/0:1XFF-<]A*jb*giT.7Fb0IcaJu#3\\i++@$G
Driu]A.?,!(P6jD;!^6'FQI`GdTn4m(`BKBD5:gV./?Hj5%4-gLB=F%!.I(,L\*X*k(HGSsmE
?$".(q06W(D/!Dl6-E@--ir::j=OD&RFk[n!]A^mPBP(bYpdYaRI_(V%<CkWls19b33H.4WmN
;]A=QXpIRrT02]AG(OW2o5?h4r>mi`qW]AfZVFe_^)^Fj-s\mndoma<'uKDBb:V-f=XakDMuBYl
8W'lsV*_>c,jp5r^@ZEAV3GXejnY[#eVp5/!S8*m(E7r?@"%nF9(h'=8401/\9u4"fbr.ZK@
u9lW1JIQ(:>Zbf6:jC%Rl6CSDMIVRsh9FS\6eEMK[.TKjPr)FHUMsJ9^?435^X[-sj)Ks%B;
'h;h\$;4RRWI]AW'IDdk"]Aq7Pjspt-i;)1)PpUZEWQ*HMj=JGWYJ[J:O(7%8O,<<-Al4,Oido
?akW6lR(;g)F?7_5"uNKHZiTJM.32ZJ=B:0536XcIo6E7b)3`'lsnYP6$MWNuNC._&%u?Yk:
m9EEC1U=d1HVK]A,"k$CiVio_kQi&G<+93Hn:-nSk?`@ur)^[SKN"mT+slTaRY7hcY>cn:9Ff
^b(m[nt\SDIu(-CKOQj+-5'scG8=A<0RD+:^QMc;$cm:hl\1/.2<2A1$k&#Fm^Nn/%bXPac:
n]A",?SQ4G+SPk']A+Y#Ad7Y'oa@0Wd_MiieQt[g\6q^E5t1R+LlfT)QmLUMOEIW'jp0@4>J$(
LIjFc^Ir;([/T0Nf0SYc&CX=.l]ARZeKLAl,@3Kl.A[&K_Z;NYP4OQrm%k*6:t5b^[WGCPU1^
Apa!XupB9[<9pQ%\,n+7]A8")>');0'Hg!a+I[2To5a>s'q<pl<M6.%;5]AG-9)RFQ;L\s7A(/
S[3i?q6-ZL'&@(HXhO\e43s2rji.?'f?hj*n7aR%[8:'f-"_tho5>b-:U!I$$BM4+(0]ARJp\
^SK*dr!`-tmgh<N(Y#7'IUb5mCr&Zhf3:AlDYm#1]A+9->;N#//d379MH7TU)NX&Y5I\j88G]A
EtQ/sceZ*^kUThlF9'`%aJt'SCQN$Kk]A-hfG2"#WKLr0nGA9-k4pNNJ1K$cVh90U44u*8H!)
\*R<)l(uU?^6"9nt`FC>iq&.kkQ(6fsX)5/o5'e7r"N"<?=&&no@u^!Z<'F$JgF!TrP(%R8M
JTmb77#<>IhDh.Eu3"IHin7lc8(EFKf$=oR81foJg!L870E`$W1(fm-oHOUFE[LT7]Agd0&2?
B?oBNUZTj<)g(<(@h='%-8er")T]AD*_-MfnEm57gZFgcQ)e&84)N%[1m:43E?&WqBX9`[>94
e'3#[-^RFcr>n^SDaElG<j9>-V@HE;1HW'&S=VoQ6>b6lMJ<##r/lucr*+QWHjJ-Cl$=k&D0
&p,k!qZNXn1?ZOJW'7Q9;F^Ap#IP?Z'EYmDE#64_7\M\815!*V7I3#.Z*TEWYpU"s9GVMT%.
@a[Qc@V+c6dO"D.V*Ql$r_NrXBVEjtBO4EKY93Wo4Pq4&G-GH3!mH&M9:_p>AM"icWra4pHp
R'AbPW2oRo`&daWrQMh7&$]Ag8f\?RN_S!eh3S9/oD@-aNip.1VZXTI8L)[>6Dc7]ASjQ_Y%=t
#;07qds3#5Z*&nSq&1R]Ar)3@.F_.4ib*2mdQLb0.uahqu)b?XO[tU-:[XqA#NUH*lZGT4q(\
p`s$^]A65ZT,FZVk-SVF&U>f6>jc.@]A#gl<9hZmjPVGXrP>I25_kmrl4SmrdZ$VI]AsrIujP6=
/t'@^-:Ca0eu;%A%b1KQm:'Zu%6=2o.6a+qZ(qnn\<@*@0X8fSku@P$0&/@#rb.DeerGRW;G
k<)Z/Yk5(X=$2"$12FQs%NGX`im"[GO63IJN<lF(@3rHF-r3PcY(6C!=QQ@#rYjl;uaafgjW
FHl38e'hg(k_/34E-C1Tgq=u=b0[:.^Z/B"NUu]A>SpZ1"k/*uSX<5hB]AW[2b=T!a\8u<LRb`
#ihE]AdQideoV0!ih\Fk2&;_PhN(cr0&Z5kAcRZ@P!h<=+bd8&Ec;A4JGU>c6,2?U(OfGEf.;
7-m[JdA(kM'QK?j\>hOI:`<aWc\r=^Sm&IKZ\qB?)LRPoV=C_"oXW%pE0^*6Wnf5IoaMduP*
DBJosOl?B:=L<rJkAS8`KBK#PPR+B%HHHM'_77"NIB2QZjsE^M2WLiI8Q)$YmC$[_=n[[n]A'
uG<T!0jbo<m/i%fi%:4utR70N6Gpe5p>B(LiEGb:A!+(g?0o#\1!aqfWHUqpZf\hgo523ueJ
)qB)+Ud"i's+c>'DnFU?Uq37UFl!(mZC;9QY1^[2t1'gh;Og9[FG"?61D(6T$Ua1Kk=;slt?
^&<-YTa?^$MY)6(o>/ZPoG^1paal:E4L%Bd,(-KLCnrXC")RsMOC9YaCG&$=JNrAB&"eFO^,
oN1IHN</#d]A'3"3+[SX`@8)c`A7BX6b<E]A<KSfW;`b)=3GBo30%`8e7(=BF:<hCJZPHKT9mG
3uu'i<a%o1pXPMF2cSXl9`[eMXEk'fo/Yr+j_3-A.k]A%+%\p/m9cA&\m2<Y&jNap*_E_4/^`
4\E:`'MMP'>hbAX$BCXLgS/XbG"6f05Qp+%\`_\^YNY:<cKTs'a[X-&?f$KR[Ps2j\2$;.^]A
?b`cE_'l@P4l#,oSKn[I9k;G2uJs3#Ii7E+s<MX]A_h*LrKGOeIAF9P2K?t$(;=VpZT>.R+C1
f=g!7bg\hK,qh3O73rhN8Cgk"Eo6i6VE_.H*Yb/*msotIQ:-V&a22n*TB>g"[`jKB.(kXUm:
$defI0Dp_l77-aZa/6nV2TZ)u'mI[q,tQq2O3SV.:PN7\6hB&]Ae%8X'50Sme/ULL.Yq,H<->
e1jWVT$:UYYN+Y`M8_rG+4c>@P.Q-9Z`,>W;Tu.OARPA2R`9H8C^0BQ\]A1oK]ANk,@0uX@@1C
`f!uGG]AtDh?PIIKQ$=h'*T$oQ@6$peDp\eKq_3tp]A>J2Ah9Qr7>[4OZsrt)oiEc#R`RHuY3\
'hYND,EBm.Z`\4[F>Ce1+1h)iZqugqbg2&l2E(jWR6_tnqg,GP-aW:6S9?rTt.6`:`mC9+FN
oY?c+=_IM3g+GVK1175L;j2!?.PG8"Fk3r[+Vb'<Z2_51N9#4(fSm^OL$,Ojb/a7r/K!=dH:
_7nf"SO=S[bouX`s$qt2-$L8^HS39\4`e'4V,7_/hCI&fCJKQe_lh42hT3S>f9*'?-Rih92!
.Xq3_U7c>IZF1SI<fhj>$s(SN@;i16?M;3'+pNT/=VM@&-n5Y*Xf;mVhu:\[g,4>j9B%IL"g
JgHAB;n'=8RDh$Z^T82[*]Ah[-ALYhdoLU0=<.m?QjZ3AaH;;;5g]A.ZPi#d;n/9oa%8_TDbe(
JbRlf[a&kc/,f=?OTi4n3S\5[MPF(jDYjSpN'trQ0s;Th#+3Qr"&pOGkoA4<@2?t^nBU@0/u
SC:0!/hUjo_8G'?<#76IW/qU+*^-6h]AeX?ck#N$5Fcgq*"BeL)YH25Zrunt7W;f,>1B',-ZR
5-01C\>ihariu?A)RmcU@3$V9N#s+p\2pg^Js)0$rtA`VU-4@D1aajKCj+Cr+lLWfOCQ8B.)
fBX.\_'k5,\S>B(G.g'_JJ4<,KjVo<^a]ACSV!TC-g!#k3)XbQrIYc.h#W@(Rr"8\o%5t?]A)I
.-4`8gDKQAYF45956n\Ic2WeRBFa9XUdni8sY,WAsO\D@'Vp]A:$T(E7l0jF]AW"K[>#b"+"m'
8`I5)1ZB?$1>]A=WO-Iqs6L_JDLV$PW&!eV-lTH,)WjZHEM?c5E03Z7$n\43(:0e#Kg#c+G[)
:@3'MlH88&&9(HT(iYs/_b+&e/m%i[n[9[Yq0]A$)Gp/aE_<H!Y_>)*ck+^G%8HWgHR>m`fE7
!Ku$HhY[o^^BD&5RbWeX[YOU^<*#+QG&_T5302,CFbE?d0plkVA_6:E040N>4\57Xl^qVA*K
SI;H2;@\Fq(%GSW[(kelfCiep(n;<oSS%BJQlD,akRLU53D4Y[p%/;'Go_QeL4ZauPSt-?ul
Jaf=8_#<bW&8lrAd2d8%GgcWWSKD%CH/%s/345LM88g_;`k,u=[OR(\88U&1Q"RT.W`K:bg*
OUXKE$<^`e]AjNl["lsS`o(,Ee1NCo4K%FTc-9dH8?1;;f@K']Ar]AIDd^CeL*8A6&<o`%O8nXj
*c;3'>"S[Q2_;&4luCE'n+>`G-^hch7sCFoAe2D$n3.Z2WD;*Y$>CM`%be[6C-%O1p`LQ-Je
s,m*U0:Z5K@%F6I>[m(S8IUs(m$XN;kf@`IedV`"+]AX</!TYc2/!6@oR=V3&05S,uI\rb:#@
5/Kf-oqu[0m(f5UuTm'm,Oo-f!XF_gu7!"6Cnj[a;XXXm<dK6QHS^MKqbla72A?+V`&g6VbL
E.$$,2;<lUX@BW48a+:%:A=rf.rZJk/n/"ZK<58!*n,fT7fg<+23p3F9@?i6gE9m!thHp:^Z
$\bKHHZTK?XMO`.$>*JBccIS+"a&lW#8n`I=dKn)k:^KlcCD'UlY+m9Rld$Phj>`MN6()*dV
Tl/2Yb*1Y-&m>Wm6=pB>fW'TZgrh1K]A,e+cE,FTF0'nO\#Y^TNI<eS+..+'Luoe:am]A"l\[0
rQZ`AmNg10?B'?SXU&_o:UIc_>ZPhF%^4S"k#A1On:bE)&IL\eaZkd.mY*JpCMqX</j'R]A:4
Z(M?]A4n'QJVuApJ7&;6h''qH@=u85^!k(V1:6:Eou`AmkRkgTh.hgW!m-U$[<9lhUmg@emA[
=Qh%bXhN[d*b&3VG%\1u^2'dY+ncbK#1FY,X?b<kA"lMOl?5%=m.ZN<d-*P`V"bMboYU!n6i
>GG`A&s=A%D'*qD)"n@bk!'Y!SW'Pq.DX.a+hEdiim%fp:Enk#'hRLT'=p;X.I$3XHEP*nX:
OrIgZ]A;8b6ron=lTuGcTF9?BcJ*AT]AF?3e5RbGO1I2W$SCM(&NZs2pS&m!'#O6X&p?\bfX>e
0AmYtneJMJ]AO+B5Vi1E<?If?8XULj5aj;5Bp`t4e_J<Liisue6-rtT6%?;XCbqa]AW\a,Hq6U
=es*/8Y=@KL):[8Ts.NB2J`K.)0/HhaA/;i';mE8`,'k179T?#fWkARe<fQfO9IRYRkfgE<$
c/2<EJWjOMCP%eM=^a`cmJAFZAi#:S^%p27E]A#e_-_CGb=l+_]AW7Zr!?Z0`MVCMdrY[C&F:i
RRO',`)h*%WFG*h60(-(b?b9<pS?7NtZI=TO8E)YonA+N9iN(,e6U[W>E.B9Ij%_=AoDG%<=
\E5_uUj7$C<)DDhbXJ>`s;WYMmr5Di=&"M$gCM7`)-`I)s"K69a=EbLQnR^t1'-Kb/Z%;mlB
3TNB:N,5KE?+LKl9V7ptnAV]Ajf7JhaiI2eqA%r;`J2;gtAA-!)LOQ0-i^$AHWVb,`$'gmU0W
fcGYM9ct`I.^b]AebR&Gm\#/$/Bl/%nK;Ra#'B`n6=rHF/^*oq$g<'!6c]A!KCV57;H>rp;&='
Orf0B$09t`["b5fbTDo4TZM1#V6D=YqE#2Q34J5V"8MP,.eYU!J9K+g_A@)\eRW68t$>XHl+
6[i2p)\fNGZSVCH*5BUeKE?G2U6(<[,]A$K97j@[-;@+*Tdk"bn_ed`@FF)9CCN=T:g"^2LkH
h%R_ZQ`>Ou9,\*NKs?(?3OqQ>q^61gsnB,Yk4;="%RG;oBtfP5gM49PJj?>&QN]AKP(4B-cbW
(9s`#ZYdVr[S/hBFerq-=hJrXCh4mC2mljg\$mBc>Ie*=Xefa`K[=Jk3--_?28krfWduZCmC
05NX?`GaSZ9$kMq:"2Bi$;i$LBiK%G`c^i6(6O*c/CB.+i&dWm<=F@X[Q^=cln`Cq_\aQj#X
l(0>F1U]A,hUfHlpML?R992(Bm>SC;%LD;dg'rmVh"<oR*%Qjh5g%X#j?=fn5Bk.h(*X76/N\
d79++jD1@E8d8C8s\(VZq9UZ'L@H1ICni@OD3nMr!`W>/nYqYekQUPGVXA@"6WU%n_rU8ZmY
XC35ahk.WPPL2b6(aq(Luipjb@F5h/:-j3]Adj`0<E.\H?/N9mj(h_&/GbEYrcOo"[/Gopj&4
QI6OJdI#B*K57Nc*9@c"GCdicX(8Ct:r/W"YP(^eff.k"1.o;l$h%?+0JCGM]Ae![PiM[0Zk#
)oV+\Z:/``Ei/_$3VOaUm=nh:T[^pKMn#=c3=pact[^l2i?79AI@Rf_-0XgmF'O?pGsH7&`+
E<?0T=l@jFg:mq)cUi'u!N`*@"@H:VZ"?s=TrTZ[m9]A3XHS`T4G/DO!u;h0QYh!2Ofc_.55B
5u_bbKbjf[j%RRYGYnlIZTbLdS\Fn9!1.+it99"I<nc4(U&[D%il"8o*:7uT'`:PY$dI@F+3
SD#C1i'if.k*%8b'r`bsf`*X$JD1;<mGRe<#qGPpYj-i(p1IpZ:TbS/'Dg!S*`%Xc.W+$!MV
i\'eNAiJ*H34f!.)>R\^:P5pA_TM8o8q>4cHk,g9oTG[pd05^U>W9>CKcW'#:.Pu0V*S"ue4
;Ye]A`t#Zq5YDM#`B6c(lZ"Kl:tKek(gX^)?3m=5*c?-bTEO+\!,]AAeq8gA;l@XR]AQu_cH:4R
qn&`sJk[ALrq(1`od,4NWrstccm<TaYdl7&'YB,?\5t/Niaq$WLks<&U9>/KD!Lc.L6Y67\M
#Z(`1LXh(0h$OoT_96_h7?'%(II0VY69qNF+\_qDV&*DT:^IIASo5b5^>o4]A(+aEmq^_YjSo
+u)9I5<cj5q>W#2nR.?J5q&mgaRm$7%?3jIBI50V^:J+)4qiC.?PJbenaY4TWu)Io+0-0j:Z
g_efhI-f(<gXs%Q`sWq_`&o=/k^'60bbVLo!KYA._#s-9\sIp\T8D*t8)\n0XZ9Ugh>lMOb\
4;SSigRi3i)KUi*&J9Mk/R:Z&QPHG/>,Cp7+6ahDrg)hY`%u8BZ<'bmFf\)Pu1_$oSXQW)Yl
kJ$;:jj!$Iq.i+7P_E/!+48Z1s-G]Ap=%Atssq+8e:7X37Nn,1t$54pT-g=5rN&\rUJ8Z:nm'
>1N!21bGk7s'3/oEAhq747iEEQpj5b#MeRQQjLr:djauVDelr99:gg5u_"/nh<WE]Ah2OEb&>
>Qa?g$Umn4LuZ^u*@5PR&R]A2ackhKMM3hf$9*kJmX]A5u]ALcVV)\H'"4TJrF&jVcXpjoGNbh7
A\76!dq$aOF=uh8!n2$2n>8;A<P\"`,o@NbK.a;-X<u;c^5:ST;CWCaX'7_H9pL%k2M3ja2!
d5fGsM6g[0(o*LikqL38[E7c[:sBNf&Q<-77k+`peV3chjJ;5JBAJFh:=?26R?Lo?-Q]A81V%
h^,dUMCR%dA*aI$/E(6OGl/r<?o@[,q@r\KoTf3Y!>i=aD6+1O:BAW:5O+u!kDBdVJ%'jfgX
AF_pDR7.:W5$jS-*n/Jm=eaUl\AR9^ZoVlm\e>!bD5>Xhs0^J^bPHa#@(;(SL0_9o/k>CDGa
u6ba2/!,o_*c?"tLWo+Ju&mA*t&-<C`p5M5DSlO2W"la]A=$9MB!/UB_l#hKJL1L31]AN`&9U?
3(u7s9*2J8*sq%MkI/kY1A)dUA]Ai8P3K"ul[>/>0bOTS,Ia+=#OJ^ma%djhFqq^u;s6>9l0i
4L+e"]A\&0,=afc$r:u/rcU1Y%gR;\=Fo'#RYV<(_hV/DdV!mI^4,/9]A<6-]A0.Z`nY20X?:Xp
[_[r-_i0>L^N6S.e^Qb+8ICCdkP/]AHOVb;S[KFVY6OO.+smUnU^A)tulS[(TN#V,MC+B.G$j
07WGmMX7kf,7^@OPtdX'^\p0=KI%F6'Ls4EbcNY93d/NiM[&GF'p>^(H`h)H%R>)9]AMO:-KV
^70%d[@)(m<>ojcjC4<RMcFPJ@!"<!Xq4rL\IHNtLa/cPB(=&#LK<hP+MkL=FB"_G4pXqc[-
M+h4Q:/[Bq.KSVFk,^oOjJNF-GCGu*.q6XN9`O+k0b]A4>s1lDFV./ZJ(tJUk:#M>3^&Q7E;p
<\bgmBrsV&7[`VV;Hh;Tf&$9L4$n$;uu@L(?i"\UPqG"e/jKF="Q=;I`D7fdOg`V`PuA)Ep=
BjG:WW*/i['^grc*W-")1XCTBkr!FI[`b0>YRYC^]AO+'GhK;LT9K?l89[2&l44m2&d.S*d<r
pXHL08ONb)G`[qM?Kf`T-!*MFM=30&r@=i9Sdhd[8'Xo>OEjY=r%edCfEW*^4'XgTZA>-2r+
(G_Y6KAi0hZVXachbk^]AbA[3*qqMZm-TXL0KrX&$1QG;S`ijhLhBIX:AgG[9"nGd+"L06h>r
dqHn/$5r9bMNUA3.p@CK4_5Z<%K'9K(1*!jYpVcYFT1lBhqq81N!d!U^;JLarP<H:#iD6-Bt
+//pfD=`14jIlCl-*TWDu<]AqXK%A%rD^!W!KP\iVK[#om8uO,dL5E0/81LcY]AiHkKp\#Df.d
)7aa\mrSq;XXbsk8N:p!f]A`.3Nk'ZBac,H4YEp*G_NZ[JT$eP>-$Ou9Q/`5I?lK'BgQWTg7,
HgS^;<iY<I/7o_XD@NPc@>$<HXJ,knAd^j0NDDlO-YQ8@5+-72mlj4ed@"WIkN"fq5_O/:%Y
Ntc0BQBlA`DGI;ZcIMFr_=Oi)TJZI#dNV,g6r#b?#'Z,>ln0f@Ka.qY10Z'%J"D[<]Alkfp0A
ot4ELnbQlB]AebuX87I6)4(*,Z^BVGp)*]A9Yj\0_WLE$/^1u1r!^rd*m5=M%.]AkHVP/#t21R+
hMe9kkLI*GH*WX*PB_;^/@&X9Cj0U>4jTCJrPdeUF\k#f=bV>#E]A\;a@F:8IPC.%O5@V%pC<
Jdp\O6TUh9r1Sqjoo>d"h_@?t:l2GQkOR.);'M[@SIB[X0kI'CX8@DVGc.T(CY%Y=]AHHBF42
W6>+53I1[4n-G<1%ECIkqV1O50HTR^m]A]A=&^:4h(r4r0IWETm`oNH@j>I!mKC-E?A^1G`"HN
=hGO-+,R=q6/A:c@Ga(i/R3YQuiSiqj7Tl=3]A51[&aC8,l4pa[`;nSP7GTbXu/HF%A3m!"89
SjD]Ab+Td("Gb69?M`ar[ehcSK+H&<9qWjk\hVgo6lUie$0HPi-$:_\iRtBsSR+6cU:"/>8??
#k4!D\]AWoR[EDjDu3[]AQ'f%TO70ShA(s:TgFK4qFae6,-C7/r<c&s[/Y:N4DsP)-c"Jh/s6N
`XAA%iCijifN;8ZOd,L*r$!=#X)C.q*;(,g^6=1l"h.M2@=c8]AX1Y:ng]AbY4>)a'a!1#ZVOR
S6@=ZL78YV*E(->5h-NIj]AEeg-8be_k[D%/3a&erSC%<WFm;HYh]AN71ZW[_<B.t<aMt`;5)M
Q[`H0<+a=4f@O:AH>Bu:o00S11Ld=jI_Z'n-Ke&G`.RSc`.lYfALp!1=Aqjq0[XOOti1-9m.
_8X]A'Asrr9(gF_:.0*,ZC9rL'NV7"123oV3(qa2d7\6I$qU&t)NIsMXqd3c(BqK.?OI#PWFZ
q[+1(r;M(10u(a34VE8T(u:I0=%N0Q'kr:;^&=+FCRu>!H=9J^?O[6Pd,F_:ae$X:o4^P<P9
sQQZb6HRQ]A+7A%e^$e"pUIp[qGQ[`VF=T6mf,'K7C+^uTCjF+R>qgL96Xi'=D?TX+OD!Thh#
&B;hL*a7&;R5RF@,`JHo<O5#?GBCq62h(%7fC!iJbLg<a@TQmfWH:K3+EH2^lfGbg!1cX3_+
k]Aigs`WVUNgcpS:>#$adpFj7fftHARIeE,YGrAcc]A;.NF'0Y/t>Dgh-a)"(+YUPp2rr>LR]AD
Yjo7f<CnNW85nMU<>R;^a+n%4<"1D`6je_80!(Aa-f67f/hds[T0qEN@!\;?oT,q94AV5#9e
^od;ZGV1Ykj$K"SY.?!qO,0]AkdS!Xf;p7GHa^+i^^o\%ppMe`Dieh=`<ld,U@!--I_JDXh5E
KDslZ512k*^`/MO4K=<l*19(a.J5\bJL_bO2D!nn/Q'!V-HrgmaCOJ<u3/gWe_&+HX^SbO5o
7"3F03>7I<Y*XE25@I<Vmp?^he7QGFli)VD\f-tfa,MIB[phq4,Io1.nF96C/th&q!OP_;/l
I[VL.$9#1BlX2B-h!X`EU\^AOBH\k)pLn*2daJY[KAm<<X<^abG)BHn@1I4Mc-blWJk:HQVK
@fk;!>p0k8Xb"+9'gK3\/O\XUMQu47ie9Y-4/H#.?'k/:M-"4-hNpk;S/@d**D@\lYi=fobC
ijDY$jHA;T4CF[7NLW-A<p"W%0B,a4kN9d8&uGm8OsI0S0nDJN(ngBdU+#+b#p?3'g9&8=Dd
FkHFL>[8uf.?k6k#k[HO<Rfsb<b=EWPDq!SsA*eD#GM@u2RL0sFqKWpQ%VI#Tr:^[maRr25p
E[!I-..FmHXNfm_+ZP`5.$b7$tb[)cFBGWn\PdQ]A-Js->K3/3$8+A`WmBS9NqqYCcG/rh@U6
I+B<2fGGSAu;?;CQ[,45u6$9HG"3b%5(`G+Tg#S(Ms$ZliH_Q#+(?7(Zd8fOU'i3q+VG"5eT
GAH_c+mOc`D@*("GTfgBe,ADk*[i:pMB6dCYO-$l6AK,;?#!S(T&;78).t(liG*N0E\M=rbl
lgobB7T1,\D5-CrA!7[_'[:R1;rgpEt\LPmfOnJoGIh36bF[3?!%X\*_Q0"h[!\:sM&LD4J0
E2M5,0Roe:DfC,dtb=%G8JQVPR5C,A@0bKU+B+qLg60WT$!M0&FG@4is[;'%'p=q*OhsohC%
`9pQCNAh@aS-<A!eTiJ(/G7,7$"G6#G10.kQ!rTUn;mA!j7FNRW<6f6.k37lNQ-.%ekojVf0
eP]AL\e?QXX6lL0'HbB^KLUD->2r+SMA:!_'C0URX74B+nm_,8VDi`<Qu<`Q>2*UZZ2RYGQr+
^/+U\EU2f^#s;"+FSDBTm:-<pKbPDC7qL%R\XOW.+d@(peQf#H0c='XB$hs!`WG@!Ud/Xer;
uBhL.uib5OP3""nd+<bMC3.n8<Xb:-BIlg@<2H4TA<Um_EoG]A'Yffa@\7'7="6oApe5=E$N'
T[j(aGcN,&(8+ejkKKT1RI;?$R$)[Wc'm0'9gomU]AK@+(O3:^f=lf&[%2jcAm:KW1di(25#s
**"B#ksJ.]At[LgrrNEb/AuN+gTuXC4)'Xs>k<e)%9"6NZr&a6!KJbWs0&ruN+sj0eft6J;Pg
>nOcCY,%3S(C5u`^u"U't76k1nA;7ag[C-[IR)4F*=O:mZ%!t6Hr:r6&i5ep2bBgG*\-oVGl
l?DDqGOE'.>;ndQP.YG@k<41\BCG#/Ec;$o<0u2img/#_8O[=*!?CpQB^aEMLK)4\XhtC*?K
_?0QhXXmqmB>uX4mMU)c73u_UgD-5tB=N8;H9KCm>k.cX*C7ZQdb"?ANRAVPrEB&XcVV,"#]A
5=@'BgX:F6qlJ$L=P6NbsN>/RkpeHTJ[)tBq32l9aq(A$m(HLg=-pR3MpFr?tVL+cC#?A4LO
j,Y?D0&InXtnd&TBg"uMn*R'jI`*-*#1?'Z]ARC9Xtspj"G<Z"f7;&D7S7,W2em+U5Bs*k>]AJ
fL,[o2H?Fk_lSK#o7_Y)L%<l(R`]AAee:bpm^5As&V1:k@ZHqagA=SZI'MS>7+*T&oq*V5V7l
7Y(:"QGF'_R]A\0@otSI`Y:U"qo]A^,Q9Vp9O)L#<_b"CRC!>?8,0@'k(3_I_Y_:mi\8-mYX*1
;pQ+>!uW^/pZS\61)&csp^$jsR;OARY6:Q+R>u3#a77/MBVms%VL`TN0e&eqgdeY]A>Oq9bp)
"Sh^rhdY.D=:C:'(1`'_`lZm%`3i3Q)2pnWdj3`aT]ApS'Y@6GkgI&8<bDWfL2c5&TQr<Q?R"
GL8LO5@&`ccH`X)+'G+!NtR/e=o#daUi@\SdkZORr\jQ`V<-*EPqfOC5(#A%1.lV+@n%-OBE
O#0V\Fue\9a+1g*?"6r]A3TZF1Dq*%2>5)<.kHbRGRJ!SDSVZ^NNK8U8j.`S$P=osD,A$@TG`
&PYC<IjYE^&?)bo<h9QiW,Ht;R3_VPm3/h4C'hnqPk,b@0-igfRj+@Tg:UJX^d]AS(RklG;MP
lho_@P>/cpQir2c`G;@+k/sUhGi@-AF\N=jPAC.sifE)!$PNkJ!PNeqO2MM;TCU.=niSG1SY
h"U#QEUl/QqSZ8qmd!HMJDT3f(IkR$J!G"X;+?V/5$;_,P4QaR]A2Xa*$/W:aDDSn)[o,_.s_
sg"H/h`E#MJ?f?UIf_3c=4u^Xmg@@N_0Hi8%=C#$C)Gi$Yqc_iiq+,533m@',iaC^>eY@L%Z
UKD"ps[p>EikEBeD8+,'\?KmTq@_[$&5dR_j2WVRtUYTc<Q0SaS6d_R=FIJjqdpXcPV*FRd,
?UV7K6.k6[bMRZUnJp?U(;R4RP,U%@r2NLD<i@tjl>Nni+<Xcj<gM.<$B<.I=>CS5Q[qo^*8
R8obb%'3qWQ20!1rG:P%?Meb99nXXaVAGXYh;^j&A^VOf1u3_;2WBRGek@Y`)&?52M/_\1^O
hp^MVJ*a1;_f@BQ(r.LdZYFW!XmOgI,1D4hsKaRBafp:2sNOXS8=PL@>[kuZlb<'e!&ngL$9
Vk-^G)_H_1,7.fTI=?`Za82UR/,TAA[gXn2IfqgoS[4RW<e3%rm!).Vr=j:Kj%;ZZ&\]AOkjg
7;2ipu)(jM3^RGkDYQH6!DAiebh:<.Z0jHEm["D`2OUoa.7.)\4D';&q3M3Hb,A#m?<\2VoM
(uML>LD4/cR.).G/Y'e]A-=82(\`2F+mT,[tBL]A>g6uCggMt*$kV[toAYk>aH)Cdjj]A/q*Z6m
>^2"Oi*4H,1QsB#:erT=IBOnG+/NBrHFs=E[oGZ-KX;8E>/=@M\5g58X\J\`5"_@)9TuCoP5
R`Zn7,LjuM\]A^;fO->fno`JoXD$fQgSDQG-=[WP=fpr3Qq9#Zk_i0Lr0lHcE]AoXe+srU01EX
Pn3+J84%O79B*5]AY*RsH9b@2[6cRmE6=Wa">!n<6%7g&Q4kg,dW2DH5D\INc^V"7&jCU6=-^
Z7@RKh[d6G?^G-?gs3^nC1`<\Mk5@6Pan1t$!F43>`r;RmRr`f@e]Abo_FPFtelPh3(%-$Yqb
l&\S-r7\?^c/&;A`;4uK2=lN!O*\$..>aLp$>I#nWVaPVFS/hKcdH*#U2CBT0lcXV6$17C+R
cF?_quc59aI7)BT.Wr2'^h^ehc"+.2R#WhZ.SBeDa.(_]AV&Ma_WZ9K]A`jPc"8k6&d,(f7#PX
1pkTBV_d0_*@i@J::Aa#[gmS9"P#SpT1VKJm-U0L\j1f:@Pj'5t]A_aa5)b:DK'8Z$cJLa$n!
>r6D@$K=bCTK4'Tr#V_"/LF\5[Z:.euauB5h*CWR=\$eM5e*sSlZXm]A8Qj<\"7aFdluB%:f8
&LJ>4Z=o%I;CpFpoeW'He2h,aFPEF@p/:?/eArfBREfrQJ[J8kXCrMad<:OWi>1%jN)WCr,,
rcrs^!G]AT[ar(OoBH8`o[QE##P(e?NI%nt_Np5S8R:/gFK<Ak2"UciQBA4eROc^)[_iS[,[b
7GnK3e!EFMrL)P6\DOmSZ_.juHVn-X>6Q,^9:"pR_aN292jt;De;4OnWgV,>3HeE*L0;ZYP.
`mQP9-pSpSOm]A.qqmfEn%rI7KWVJk0)?9OUhoK^u#jT_3*,(:unre(aN/WGuSmlJ-]A6p:sP*
qo3Cd\0l5!4u0Up(nr"8;Y76EI:dM60c#ZU*":r7C*LSg-08;jVJniZ(uZPlG]A+P?6aAX+lp
oNd`16^=.X)Z^Z@NHQ;_!q`m!Vkq($h(<.)K[r?($@C;CW\2k_;?NYbGL1p!u7+p[BurG;'-
(7,%2Q,cK.0:7c"K"ZIUjhX(fD[^f'_fX^1"F<k7;VXtlbAbktF?=j<n@fugqGqZZK_PMWn^
^]AA.g%!GeHO%<B`-*"UlkK30LjA-s"U9?Q2(4]AY&ClB@X75pf<k;(Q?o2E0^2#88:/(3#o$e
sW4%k5Mk<3k^#U-YFGQNo1]AZ)7AYF=Sm"XW#M]A-ut\!UFL,PuGMkUoCi\\:*".<jPq&52I2#
c<Zi8DgaC9ds=-KQgF_;<H(tjYSN!/Ef6<oDZ9.k/3A-Tn=lE/-nBg%,?*jEP22JNr&'q?et
cjjE_eFO^2A)>M!"oCpR2;oK;VtQOa/20MVr8KK4u^Y.rJk@Rr_]ADpe634qMV3q)@qsA?\b+
#R.MI)Li%gV%nG:4f`\o-`!L*`)g^OE]AZ]AWoY,CO)fkj8Z]ACo_K0o=@_J%Mt0$neW+Nq$BeO
3X3MC4S6I(!YEJ?rBtanGs!PT%K-PN2I%NdNeX2%5N\H"VaIW?4`&oM!i\o)3eaf0.a%Jeu:
LSL4Z9VDc)`ZK8TLs,IM[%U(oB1nEei"jNU^NC$`*aou]AJ5'rF[@.k$SSk]A,/Cm)E0o/VLt#
Y3Ii=u@?Sof,KEZ`d;1JT`nSZ6&jC"7BgZDZd(bgP8<Obi#\VG5&W=YoKWT^'bk4OYu_TOUH
1Z:UDUX]AZQR_D,$9=f<_R"=4]AQU5SRZ0rA/]A=CRYY?PgXM<?lJe?83FfeQpqo0N9.OM]ANrK,
d4n[I=N3&Cj<..cjt4BAGili5/9r\Wou4;"@=D=:+8%&jU:buV]ALHKA6Jo81SUNH-[_d99bB
JEq<e;&q+LeDc'2/:s9jmrLjS42bVima3^q<ji-SW="gA,Z4mu6gebBs71pnPXFn,ii,CqEQ
0B(2:Zl!3hqrQJ#0AL'51H&jl19"s"S&qg0eDWO![)1qpq+$RFJVfUXu!Rlc<+W\rpBc:r5+
Z%d6b$lbnSBVNYBl55!LE^I>9sd;#]A5U(2%l7:W%O"8%gE2B-3_3bH#A+8%0*Z2"DhQN2r%o
/2`ru_G=s9'VZ!uH_.L8)L&#POdXV4P1`r`4OG=56:Y$nBV%V^EV*4V9F/T/+SmK5+f%k%aN
Yi^*2I6iH*_\-7tO;_tp7e3[t*q*I9o*pf&eI&s$m:baDi0WNZ8E<oo6^Ne&Okm#F1sYKPit
![>qQ!G>f/?cC#eJEb<Gujd4bjqPUIBmhJ<pVa:V3'5%e#ZnSQ,K^,W/-Zfi6;I3uQ#M/D`3
8%uh_T\i_5>W0=7JW0p_h9l]ARBG.a:6igK$Jp;&T!YBoW$f2_K#&3opbK"Z;E7s;GFcHdi/:
GFS[Vs#C'W+f(PrB$+:90^"A>Y&DH;bO"*@YV;jMVQ>2+SWJQDUWQO=ii6Ss1U^B/+V"j3DK
sZSg(.o2^2XtpmnrR,TmEtg<(4DEi(a:[dZ3gp2WXnOK)2Q.O(d/)AcAgJk4mGrWIVIT&p1+
7O=eAJo?%nN!TD86.YTm!`-Nu;7#&9]AdsukE1/tfBtW!G[cY*L&:eqtqd`8S,k$b)!qlp6&t
@Kb2e[UXC?`ZG\N>bB@gWPuAgS5:"A7,>'V`'\k<c!H9:n&%ISHU;]A*5q=fit=bHI&I?.,BN
QoW!K[QirE4`9t5;9Q;`_Yki(82Ud,:9pB6b"1RILbN\/<cBg%m`rc'gs6mA3J%YRODLm8d^
:ArJlb_gOJs,u-#O2jqM9k!f#&8CE:LGX6'`MR,I>ufPo&G:YgBp1g)D*+SIlc0PL9FXta@l
dVpW<3rY2@3Bhr??5^Ko-Z?(-@r[V-EJ=ijFoHioY$)\!$TWo(]ApAIVZ]AmR5?;*=:O=S=ZE@
I7jH5r;rsY&1NRA+qS(mDm'qXXG(Oi(1J4-1VBn8\Nb&!VA)S-E'ltYJh.%@og`W-cJ&!dR1
]A9sJe%?=]A*!SI:d$nklF(MVhP+;ZJ/I*^g)P#pcdeJ3!IO^:.BJ;I82=G1$G:HI#AmYlWTNR
5[Ej>[Ag`^ePaJ?lU$7V%'0Y&-AsU<D$[4e<nqrO%A@3<1p+rp"G8SI:H]AChDB87\OAK'nl*
ZJFfCA3Wn1)7([i[-(OWG#+gTKaph]A@2[%6An$]A1fWk7oGGUX+XJHm.eB+BAEka1X;T2VTJP
d:(q'TJGG*?ET#)hfk0LHE]Ak6i_*5fIO31\\@%@0k51-s+V(Xc.T:.dUj63*1q*JtS?d&uY1
1M4+SqZ/-TAcR@?n7=JEK*LSnO1seGf?p+`Oql*6d>/i&3I=*>DbQXX[[[#l$K#ei9!VASl?
iDK(-X4=.71`OQSrsF6%suGj*F4\:IOggo38"pJiUBPZgjDO]A9Y+>Vh=V&Uk"?2-a/l-HN,d
/(3?NVDu#>"==%k$YcZXEFXT1j]Atk2?EUtMJR0+Q68S<o8$8M)Yb7#%'&QWY#G6nuAYjFI-O
q)-m;^%!(^:YLE4M=^7d1P;r@R#BXO6(k@?J:sbglS9SMHsn^=c_6s504(/3^T95,P6s");l
C_Q,,LtH^E7Di/([kk'jId`%;Q7J<;r++>%$B.sqNa?c(0a&dAFs[Q+A"?47L6#pt<rk?UE3
fQk),hOC#V_uHeW#<iOid!)1$W+`;bIshN[0b=lkJXi+n3H7\!-Iu?]AdAg8"Y=6LhHH<#282
:%rF`.TKTB"=aT_(Q%2t_:5(ipkqK\L_oT/+jG&2?oIVB='5lB7V2?uP9OTtni+Yi[>o4na/
"M;l05H/l?CCDq0DBOjjKd,3tH"VhL;ioTQ+>M4"3]AA2ft-s'h49]A'[XZjJ:W<Fj6%N(8!,-
XS13PrK@4@Ue#Ed3;AI+NG"QB\DRe^3Eo%=N_/q;h?ElmrUO\JAs-N:$u=g%!r/6-F(&0]A-,
-]A+!#k'a(L8ohsW2D=blQZ(,1'mR1uHps(l2d&`l"":(.NFHC3I/B62on.76r_#?!h16V5US
T`@GJ4PXII%XKd1O^U2&+^3jGe":#<-ZuUq)mL^_epN3-'tppSfH-:,'F&dR)?=RiqDSjM'3
j/HTRZHmq^g^o_G'Th!S4_4K\jYggc'dQ+`?&&pYhtD7C%Wc>U&[e"3P0k`so#A5:G$[4tJW
sH%8RW^3Q\c)1)g[3G,Vd?'$^=S4A]A$)<Bj+.Tu<6m`dh)Kdg,4pIA%%oop,jo6/#uVfXRIL
9R5Ka;sDMC5D]Ac9d7kA@$>h\7>ibOpchlX<DVYiLZctM<pUrH((qS:dh+#Ka\KOGs,3/;q'"
q02Ih6CZB"oN*5GgN]A3Q@.b4P8lRP-$%TnB.d-*:7LK%lq:I&QQ=H+-?E:I]AS5d0TPh;;1o0
8BieE!#=-sb?@qr<[61c%uL3n>j$J@2L/\A[$[OAAG`*BNq2$>#-Yj)Q/,R=+,J,VO\&G4Sd
"G^E%C7o"-&G;%@j28;>f9X\BqS_Gq7H.QfP`B+PDT4AkJ/(ZS9I->(CSO2"stBOA+ee6hH9
&9`fr%Cn^;A0N)o7?:R$`nX40LF<8N"M&jD'^p<YPCf4.#ZpN/ba.j<p$'DK\0MnAgP"17X+
!S-JfE1%2qf^FGa1U;*7L',qD[YU&"rDsTnj7,:2"^.^"MXn9#'q?h2t*_XJe.k;+-)Jl-rV
Yoi1]AgLL;4nO[HJ_S%G%,i-AO2#W<"JYCG'a\/e;&SrQ?rGij[$^4HL=cI4.`W0kDg3Mc&n2
^6i"jZ_Z*nVo_SLMG1^*0L'LS;Q0,Obp1o(4X>OE^oe]AKpfJG?.Sg<?@2nE`UF4t1%j,\K>d
2o5.l0+bpbi5\#NQDts"0<k"6sfQN^us8g%P(1>`6@:mX&l^`@p(e!\gn?hFD$jRjB=L.\ru
s#^p+cTH8LNL9";8L-4ZNq#.7/mO6.+c5>'=)W9UL"M0Q_S=CTV*[s(;(]AbPpCUM\BMd*k,c
]A*AuVM;2BL?1@/QJuQl!?YA).a+H#@RU93r)7):NTcqD"Sbg2LHgFa[@$'NYMh\g6b$l2ZX2
k^2l./ijuCR1s4&.8s+R#IA`X>.=`FIHWDCWK.K>S>AY1#gBmluV_N\</7MUb/_/>nbBT6VJ
dY'7Z/l&iZFR_Y#Ai_?8&`B-2!:UGc<2OU,'p$C^F9*QUl,@&49E,Ikmgn)?r/j_2,Q<1rQ@
F#J?e"X/&9$dQni%J74:nX,Z&%A,.Y+DJ2ka[[cdF"?`!a3BJ\T,%]ACLNeEf!=#CX<?*TBab
.B:q<lN%V[holS&4R);+N:Blh$;`[kj>Kgo^%+.^k-LWel,$oNIjj,;+fJl28\qqOcdNf\7C
QVT;`N>!Ge,ridPQ%7K>_@(m>PT_R@#HkeronOd<UWGGCWe,X'A#roCP#a)=CS!nKaUu(hn-
hc?nH[1'#*+L3u0C.JEc.6,+`F3UqG1Tb=)*b-I"q['f"kCQ\`E\@Z>K%jROjQIA\7X<ZU(f
8&u%rc.KD]A%)>(BbgUnALZ`qnTf0\>6dido`^3oAVs$b.%MNHH^XF^hX3Ek*?^Gnp/s^2.Ie
6t~
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
<CellElementList>
<C c="0" r="0">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="600" height="540"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="false"/>
<Size width="600" height="540"/>
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
<TemplateIdAttMark TemplateId="0e1b2f45-c2ee-4dea-8fcc-d815cc7b058b"/>
</TemplateIdAttMark>
</Form>
