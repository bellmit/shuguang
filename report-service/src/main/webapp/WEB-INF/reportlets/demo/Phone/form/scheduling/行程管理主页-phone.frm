<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="fr_username"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="day"/>
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
<![CDATA[SELECT * FROM 客户拜访行程录入表
where 1=1
and 员工姓名 = '${fr_username}'
${if(len(day)=0, " and 日期 = '" + format(today(),"yyyy-MM-dd") + "'", " and 日期 ='" + day + "'")}
limit 1]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="fr_username"/>
<O>
<![CDATA[人事员A]]></O>
</Parameter>
<Parameter>
<Attributes name="day"/>
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
<![CDATA[SELECT * FROM 客户拜访行程录入表
where 1=1
and 员工姓名 = '${fr_username}'
${if(len(day)=0, " and strftime('%Y',日期) = '"  + year(today()) + "'"," and strftime('%Y',日期) = '"  + year(day) + "'")}
${if(len(day)=0, " and cast(strftime('%m',日期) as int) = "  + month(today())," and cast(strftime('%m',日期) as int) = "  + month(day))}]]></Query>
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
<FRFont name="Agency FB" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-328966"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-328966"/>
<appFormBodyPadding class="com.fr.base.iofile.attr.FormBodyPaddingAttrMark">
<appFormBodyPadding interval="0">
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
<border style="1" color="-657931" borderRadius="4" type="0" borderStyle="0"/>
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
<![CDATA[1524000,0,1003412,1728000,1728000,1728000,1728000,1728000,1728000,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[1872000,1872000,1872000,1872000,1872000,1872000,1872000,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="2" s="0">
<O>
<![CDATA[ <上个月]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=MONTHDELTA(C1,-1)]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="2" r="0" cs="3" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=if(len($day) = 0, today(), $day)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="0" cs="2" s="2">
<O>
<![CDATA[下个月> ]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="动态参数1">
<JavaScript class="com.fr.js.ParameterJavaScript">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=MONTHDELTA(C1, 1)]]></Attributes>
</O>
</Parameter>
</Parameters>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="1" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=weekday(date(year(C1), month(C1), 1))]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=daysofmonth(C1)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=daysofmonth(MONTHDELTA(C1, -1))]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=month(C1)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=year(C1)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="4">
<O>
<![CDATA[日]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="4">
<O>
<![CDATA[一]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="4">
<O>
<![CDATA[二]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" s="4">
<O>
<![CDATA[三]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="4">
<O>
<![CDATA[四]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2" s="4">
<O>
<![CDATA[五]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2" s="4">
<O>
<![CDATA[六]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(A2 = 0, 1, C2 - A2 + 1)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="2">
<O>
<![CDATA[20]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="3" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(A2 <= 1, 1 - A2 + 1, C2 - A2 + 2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="2">
<O>
<![CDATA[20]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="3" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(A2 <= 2, 2 - A2 + 1, C2 - A2 + 3)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="2">
<O>
<![CDATA[20]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="3" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(A2 <= 3, 3 - A2 + 1, C2 - A2 + 4)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="2">
<O>
<![CDATA[20]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="3" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(A2 <= 4, 4 - A2 + 1, C2 - A2 + 5)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="2">
<O>
<![CDATA[20]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="3" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(A2 <= 5, 5 - A2 + 1, C2 - A2 + 6)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="2">
<O>
<![CDATA[20]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="3" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(A2 <= 6, 6 - A2 + 1, C2 - A2 + 7)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,1),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[A2 = 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="2">
<O>
<![CDATA[20]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="4" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G4 + 1]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="4" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G4 + 2]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="4" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G4 + 3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="4" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G4 + 4]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="4" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G4 + 5]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="4" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G4 + 6]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="4" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G4 + 7]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="5" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G5 + 1]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="5" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G5 + 2]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" upParentDefault="false"/>
</C>
<C c="2" r="5" s="6">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G5 + 3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="5" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G5 + 4]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="5" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G5 + 5]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="5" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G5 + 6]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="5" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G5 + 7]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="6" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G6 + 1]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="6" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G6 + 2]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" upParentDefault="false"/>
</C>
<C c="2" r="6" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G6 + 3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="6" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G6 + 4]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="6" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G6 + 5]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="6" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G6 + 6]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="6" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G6 + 7]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="7" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G7 + 1]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="7" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=G7 + 2]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2, D2, $$$), "yyyy-MM-dd")]]></Attributes>
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
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" upParentDefault="false"/>
</C>
<C c="2" r="7" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 3 <= B2, G7 + 3, G7 + 3 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 3 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 3 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 3 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 3),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 3),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 3 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-11561760"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="7" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 4 <= B2, G7 + 4, G7 + 4 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 4 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 4 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 4 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 4),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 4),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 4 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-11561760"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="7" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 5 <= B2, G7 + 5, G7 + 5 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 5 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 5 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 5 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 5),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 5),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 5 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="7" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 6 <= B2, G7 + 6, G7 + 6 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 6 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 6 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 6 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 6),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 6),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 6 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="7" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 7 <= B2, G7 + 7, G7 + 7 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 7 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 7 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 7 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 7),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 7),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 7 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="8" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 8 <= B2, G7 + 8, G7 + 8 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 8 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 8 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 8 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 8),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 8),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 8 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.RowHeightHighlightAction"/>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="8" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 9 <= B2, G7 + 9, G7 + 9 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 9 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 9 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 9 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 9),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 9),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 9 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="8" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 10 <= B2, G7 + 10, G7 + 10 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 10 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 10),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 10),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 10 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 10 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="8" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 11 <= B2, G7 + 11, G7 + 11 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 11 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 11 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 11 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 11),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 11),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 11 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="8" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 12 <= B2, G7 + 12, G7 + 12 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 12 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 12 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 12 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 12),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 12),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 12 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="8" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 13 <= B2, G7 + 13, G7 + 13 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 13 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 13 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 13 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 13),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 13),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 13 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="8" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(G7 + 14 <= B2, G7 + 14, G7 + 14 - B2)]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 14 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.FRFontHighlightAction">
<FRFont name="SimSun" style="0" size="96" foreground="-1"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-14701083"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性6]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 14 <= B2]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[len(value("ds2", 2, 1, format(DATE(E2, D2, $$$), "yyyy-MM-dd"))) > 0]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-12678698"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性3]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 14 <= B2]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 14),"yyyy-MM-dd")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report1" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="fix"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
</O>
</Parameter>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=format(date(E2,D2,G7 + 14),"yyyy-MM-dd")]]></Attributes>
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
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性4]]></Name>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[fix = $$$]]></Formula>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[G7 + 14 <= B2]]></Formula>
</Condition>
</JoinCondition>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-12678698"/>
</HighlightAction>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-1"/>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性5]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="4">
<O>
<![CDATA[25]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ForegroundHighlightAction">
<Foreground color="-2892049"/>
</HighlightAction>
</Highlight>
</HighlightList>
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
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="5">
<Format class="com.fr.base.SimpleDateFormatThreadSafe">
<![CDATA[yyyy-MM-dd]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-14701083"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.SimpleDateFormatThreadSafe">
<![CDATA[yyyy-MM-dd ]]></Format>
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-14701083"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1" paddingRight="5">
<Format class="com.fr.base.SimpleDateFormatThreadSafe">
<![CDATA[yyyy-MM-dd]]></Format>
<FRFont name="微软雅黑" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-14701083"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Microsoft YaHei UI" style="0" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-12416045"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-6710887"/>
<Background name="ColorBackground" color="-328966"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-10972189"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-592138"/>
<Bottom style="1" color="-592138"/>
<Left style="1" color="-592138"/>
<Right style="1" color="-592138"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1" paddingLeft="5" paddingRight="5" spacingBefore="2" spacingAfter="2" lineSpacing="2">
<FRFont name="Verdana" style="0" size="96" foreground="-10972189"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-592138"/>
<Bottom style="1" color="-592138"/>
<Left style="1" color="-592138"/>
<Right style="1" color="-592138"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<a1Be*m<\V$Pa(a#&uT,c;9pN$Os8kX6^8eJ)Sn)07,TMY^9tB';LV/BT-uZq@#d'U*SJCW
sk6:>>1qQ70NFqmer+n+UhZO8HY<kb%7R?J"LhSY!%JF^;UoBtdM49O;Pl]AQk.%GATPuDr(O
Tn:1CG@Gm>NnU$u$k3Z9bHb7BCCg8oMp>hGq-Fn8C4nqs[[j\@A92!n!Em[.p:?\JGPsSBB`
V]AB_&TsFJ*PK#<PdYLYl0`'5pFJ5u]A)J\?(!OI74@ekp)l'Z;"h12Z!cg/rBYfH&`b`ZbS"=
?fE<Wn!Y"d`mCO5pSgks:k4'E8u5.'g-L0H/\q'gX%QJ;^ZJ[l^FT,Hgp*WOO0G<TI`_eH1[
&-Ai"d6FHe;4bjf*aF3=Ika&^pe]A<4_P0TM*YSpm&kgKf^N=f=(o^aPcq0OR%D.$YHl%Kl&C
u9=LiME(YnkJdP-a;/(k]AmR#UH"+Z#F`G&AhJA-QLt)k)r,-Jhh*j-7Zk,FOD;',jD[YYYc5
m%NBp.KI@O5QObA1Hc\?<Tj$4J?;g%IF2["I%2\7"mppIfG?gqn%H\A05?0m09`Lc89cfM_r
r$?/)k2m]AqFiG%@$52NopPPt3n#c+e,?AT<2P^2<cO&V0m7EZ&s<'b"EamI]A:fS[LIQ2bG*]A
7TaBO"Mj7JmO"#[[X\gd'7^5KA$QXZk$-JD#lF"B-n$CO#4Oi4J%#*]Acc7r.SPm_1(V?c/nG
4bn.0:Y9+BZ]A4t+%H]A]Ap">>$_PYd>E9\!.%]AQa5GW;DUq`hLH%iW+6EQ$lfLDj9qs,I?]Ak5#
J76M9'X6PsP-(,o_VACA4XeU?.u08<H(GXG\R[^Ma:qdegQHc8`ZgMRg(Q*MaSOk`3]APW8")
V:=:=ikNfbr;HE%6\M-bA0AoJXUPY8SO9>2UPsJspHHad_al9h5\)]AW8pC,V:`/]Abh_aZ7C`
[Xs>]A"$jkGEG"TQ!Y3tWqnC*VUemmJf.eHCH&;EEH9?9`?t?,'e1#!;lmncHWH(HglZ8t4EP
ADH,BJ4\<Z:7ed>Z9,\AcYDZt.PVbpC(k`u?i=!<YI`#J&K$pp[Vs(B_G;e!F6ocHp%:JrXL
ic=f=J]AjZDo%0r/+_.)]Aq`[jCCZ<n6+0(Rn*e^g_T.a),4&flQYt1V3P:%8n(4WiN^Z1JlZ$
D9A,qj!Wa=O.t*tD/X^7'3AWc#B=eS<'4d$G!M2FY>5he0<V2Q"Ur;--gQQK@#BB/V40a"@3
Kpt.Fdr:@56r6Cn69V8Tegd*!"N#U$f9H/u[XD(AUMd"AGP!T5tLU>h5P.)-hK("pc0JqA!A
$7L,k=1Ihq2K!2o)#rQ2+0Q>XcnZ"VSeI=rRNUtM[TJ\5gH=Pe.6q/QR*AG<o8"*#.(3;o1H
2B`muS_2#eSZiEiE:<r[7r>i(FR61Xs0RcQeX.%D4rXn!POWA8p7bi1^[b9ff;AM/_()=ut@
CN*BkAM96bF_^%tlt&%&rN>6Bq#_3UW8\6oD\&8&Z[[3;T\<o$%V*K?ha2!XV/>:RQ`YRR;6
C9/Y"7E\724&2I7a'Fpl_sBL@"E6]A=ored=Gkr/RbH-lb@22Xda:gB@Apm$U&Hr%\=4.;Z*u
4Vm!nG[ARs,0>-o=UF<5k&EssU98OBrcfnpNBi_E+iO<Ri)Gn&4)2u"8p()Lp/!g0Pa^f>cY
@d2^A\a!nZ'5c)jU.D1q0qZ_p\>=(qMC_J^>9;R,]A-]AU<;[,JjU2hOe<E*R'oq?qa=h+d47=
hWT2(R55;l@j9G*&%OGp#__JoB/38RQ,]AJer%,XXNK[ND]AfbPXNhn8&!8/LuJM!aF!I<Lg?S
r*s0uFfNA7e`)B*b:odI=*D71hA+^4\n-_`TRB[IQ.rGY/0@;9c5ek7D5_3.^[)QkDX9JX/k
IQ,aY)o1*4eHl?%sf#RKl<Vg1s6E*]A>,/<$G6kisB@Z;TtPf-C;$I?YiQEcLdL8Titsd>lJd
d07EbVY@!a<)\b=3]AJ=!*rs4URFd\Hpq@r4k4qZG:I/H3Pg#NZll9=^uLpBV^lYVLr>hbWqk
-tVL3qB/]AG,_nu4<lQa&en42C-Nhd4#4%BJGh=LLfuKCb\-E%ea5AP!9'irF8Q9Z+U#EL.[C
L,X)$.3)V3D$4JII%*ieS1s'gU>2I=7K.!aR46EQ3-kpI)mk3UO'qo:e,GD:=.2uFNL>gYH/
*XB^@)>]A5&H>Qd*c/X$0k4>Ec5t[*pVR$$`HlFR&')6QtT;s@[0pQdtQ==TYKaf[\5+@*QpE
d6Y<fc+DHD8Q^G<8o+mu(>O(EJ"<fXuohl2bgc<-r.?<nGjh=$<4!TG,Y"ct*TDH:=NF\hQC
s`+/:AWW#7mj-:b:1<bbY;h=_>R!KpV9*XuM'^o(5nX13fYiitbq/SUZ$#6[VNZ8p^7=07F^
.atpX4sq>GX<.0:mU#F1(5<W]A/!A"4QsCOP'>>q`:WL(V7`e2Bc9?-'g*M!:\uo5(o7^J381
F6^+cDr&K>kI517U&9ZEXjkk'_)^)bVqk8q>;VV0[JZ/Q$'g1J4Em<#OnWT:eHOO:i/]AtSXZ
kTpc:j.lSG9h5.'?-:EmY0)<X(XS%R9a7]AiL.rm<FUS,.;;tLbNA%+,14LO&-rnhEI:A\>KT
[C>Z2&J>_HS3(Q>QOt?V\lV!'[;II-5*B)qtm9Ymu)3mVG\`)g$7c2&fNn8EtuO;Zf*<q/#(
;JS.Gu/q@gg'UK`r?\"(q;Eg0,p<pXW$1qF'J"0Z6OjW-(_aKSc3ZAT[iA@)'2>etm@d<r*E
gZ%`)=1[tl1B?F9(5)j;lTN1ES\nuq0pr-P>^ahG0mofOJmG*dMR$pJ#XGb@>OSsc'2Sh.+F
d2fCE4>C.LNnq)U;^o!p`CA(j)/j-WnQ7TJf>)NXLOTBc,V_\*88HckQQa_a/6,>2?U3>o*K
3n:f?&PGLW8Z+b$':"=5IauQqGjbL&<]AO+T\9_?2'CtA9dpTTY6jiU'>'XHbX<*Y.2jHV0X'
(0CmB<,3S`Iq;3F=a.P05`2XZ50FL^keHHeQL]AiT+1!_.@<58/1s:5Yg&/Z'/5;`S#)Gk1C9
$lY/?fkH'nob(dSLhb=:@pW-lC[r^SbiNk\>667K2F0,i\6IA@GYY@-k*flOCda0FO,S/q-q
($3H_m&rm+QT-#!Qej$dL+iF5De=`LL+O5XHkIss):f*[9\1b*\;]A1P;r-Hj^.te!i>mMIAg
'!!K@/(_a%[I/Q'hEB[eU14df@t8X$qqW*\g3D;r@\WiehDoW=Zi8/GC#"W-"8BnkT!bY:+c
i#NW"U'`_>4M,HImdeBjCR@`)3Qdc`SF12"%^bk7k_B7^k,7-[(,O,Ud(,k=5g+V(pO[`79B
8Z"*!:=SnHXcP=Q,Y$[%iPm)!A/@>"rnTbn9K?E3_F%D>.j_>PQtZRB0u#R?&8d.!:r%f?O`
PFtW>K;hsKW<VJ,5j)Dtl&Jr>W9J0,bItf0WG^+ulF=Dm,AW\;$di5:^AetWh+POJuN+AI/r
,CB\Y3=hX>bl_oR50pq4.#d.&ThLL&+Qu&hRr[>V4bO<*Smn:g)TPiIm;eIK!GI7&c@OpRET
U4;E`mC^APNKm!2rM%QBE01Z)lb5Xo,_A6qYe46Pq=L$^L,)AI5s<6]A4SC^;S<g@6SI@Ce'`
W?=u)g%-.ucrlL7k$c"(gY&rXh0(5YaS<"P#4YGUVMGF#CP\,Id3:W<=A0b)\S(Z7gJk1V=0
qR,?LmU#$YO,Eqg>BU$$6n!R\eooH$n9KS6OJA7F#DsK*OG35%mS6G:&r@T5GnqQ$-MtHPL,
+/YD"1/92kYW7E;=i+,02g%NoN:&l1"2%>O"ST==pr`bq>2Cgn9h.i)a3IpB=AUG!%-PCZs%
P[mB.<SaPH+mFa7is*NR>j=HU_nB,,`@c[-S02JT3mWKj,K_KP2ZuE+;N!=NC)=a5=4fg+7m
Men@>&DTZKc)g%/Z53NJur2+qetG6pprFsMp>S(]AXdQSMTshX;=q%+Tp1ZC&'eR+TfeNHM$n
Tsj3_c-/doj!WX)C35!bcdYQd;*D>4gQ`rn\tM7JN_j`*0mA/kqiXuVmm$TdqX%s':"iDWJ)
DI4m?Ph,=n\;3p]A3SA'\=/U2r*$(4h`ANa.E/cO6^Vdp0PI>;o)Q7lQ69\mg?u>9>CoR$V(*
mk)V4.CXOXRp3p/-U06DUkDLQdla(uK6ci@+Jd-^Bq[\_IBWesP^4#0U_THDGD;1XuGE.t]An
`P(U4dss)*/Vh<6V&Hl8R"e_druTBXBaDdI>jpp<p]AMaoa=r`%BOtEk51VKIic`a`=lG&$9G
6njA9toB\^U'FdF@Ah@$FLWUOd_bOY@eL(H]AH<p`%lr<q$/H1uGT/KLG54@/U!4eq(Q#qUDd
Z3#Sdiu"CrMqe.h4WL((WmrXs_`M6!5KFu'$>"QiM-,eKi7l;[)]AOjpmb7<V(F0\Y8bb<-B>
^=\U1^3?OSnVLgjMUb#L.)hTIK['.g*"ha"Jf3fNKC8?$-GprN?5#Zbc@8%\QoP[EFBYPl-D
N(A-)CP.*B?CVC%%Vs;mI(oX>VVa<oq?95O-*g`J9f;H<bJOiNB/3?VaE8p.M.#*V,o^[;7k
9S`Un7U7S(jH_tCl@tJS^Vd4I.,TC;0]AU.r=Q:d*_#u!=e?ju%-"_Y=,tN=(mgP=`$=34*PZ
\:BR/]A%J:KGs8n<oB]ADe[[+*I$g47V\#b!<uK"PdYc45\-mcEOFcE_K(d*=-E@0/*6M@la_D
5LbdERbIo;:J_?jP!:41,A!`YZQ=nTTrbDC5<)a^jqJBnDce&&"me\pM;j3c8/,,O"drPh.h
]AR3?,:FnH,"`!1N"piCUqGBTtk'pk6f(Skk99lVt<WjTNcgp!KYXpo:#T4Sq>!f;>G!K[8_^
.:rmZsW9M1C?(`/&VIDFs%"Hu8`R?@,0&UAUncg1OaG.4Yqg&`uHJ!T@&Lu1TlIE&RRTHn\T
'a`R%&KKh2,aSElnR5Fs&Q>C5>L?!c#0'4:EJFPNV>3?o'?&k-%4/ge?:k;$\=hs"+e"M"aG
Z6jm+N4A.2fl`#7g'a0UOAmqJ`;RRugH@\#nB"/]A.EQTm0QF4'4^SB$!';+NPjfG&VDHD%Jj
5QkF>n086BOpC_RK:d1j=U#(9PqYUA2&@F`JduNIbGX4sa#>X:.Lprdj'Ilq0#!+='/+]Ab&s
SF5bZn#3'Ei*Imt>K-\nATmH;a\H+_&)i_+TC?:!8:g!L,8hHC%9G@.8Ot.!Rd(KU1+?6<Bh
(@HGpG:9Z#G4M)4m)"a&QN<g%r1b)CL&tCBP]A!)-GmF6Tn(K1WBfOH?B8G*qEeJC$=Kh0!<7
uOh\;!qbugK',$9^g;\6L@^T*HYSCVmJNEpSu_g23Z&W9/+%+;\PM=@u_fDamd3:'*:Vl+nu
\Lk':Iha_8DgJ2sO[W$2S3#Lpe'kH+h<Fe.$qLdL2XLPICs`?r>t_`!^E5p6H5NH#/cc]A#V4
'X86)\?MZql9m<5_hLf4XL#MdWXM2YiaTE-d&\XTe7Zag!j$kn\mh7e"M-N)?X*3T6nZUSP#
@_^k(D`aRh`aWam2-M3f:bKkMo.fl[P,Q?(7Ti9XU9O\8(M<lG1@cMS]Ai->KZj95i66!/ebY
bg!r_r<^T;-61Z8CT0Qi^j9h/Q:Z[mt2>u`Q)YIq)n@3Via2SQm)NJh7Xc6,8Iph\(^/>);Q
*cu#9[W81M'Bctk;PO?n!Pri`SAMY<S@r4n./kGX:i5nL-$W@*Z?&gL`?Z7^phD\9)+Ki&Wg
r;,Y8Q[KOS\C0\[nSeisK4l"FJ:fu%?hgSQqT1g1;5rZ8NA,m(3VWc`%J<)m@!@b)4+,Dbke
MFlI_=@J<r^uoroL66:sMnDL73tmL7&j%caH12AUJKVHt'N;'([j2pYo7Agt`L\2_G^8ND0C
flS)QA[X=3i:I90heq6C)g^@Fn\L$CW^3;N,lmJoX-\1CX<QD2Za!2c7(XbM19:mj4"CdK:n
P#,(t&B5N26(!m=SCir[THH'fl7r#-!rP\?N-<ID6e<k/GkrBNq#VdG]Af*_6f,+%;Y"gt`?a
V]AMd)Bp3cOS-<$keS'Gl?V:$$^'ruK7ZHb*>X5Mn'G,=iTpW^Pa:BUjBf[-5Nur"Uo4\@H:o
mpFckO/Z#R]A?CBY"NA7d#_(M\9R2VGE<)elH9:;!cY)Imor0;kG?!D>!\imb,U,D;["=]AI\S
5IsMG;.G@irBD(9o5MA!.ZC8?<E%FfUcF![-=7#%OTOFOH7IHO2jeilr?Ho\@[:D=%fP]AY"^
$%+d]A1AV#dU;2a;B6C@dc_8G:]AO8c&R4&A-_&;('2h`OaQM$K_e*iO-gHL8?E2\]AH:=e@t7(
I%Ub?V?@DlRrT^aA,aGTD)FH'd?/_X;B.og\T3f]AHD'.R118VP@:(h+Q'^!nM3^8&8?krnkm
A1]A[X]Ar+dEpn]AM,]A*._7SQC@n=!T6;!;nHOWh<1n;bkp?Z$>RC:MX"9&s#,ABQWr5h2"(<NO
/D'='d^50jjBX2.E)>)Y=uGB.u9r8F*BUTF0P^+A\+P!9T)!J#lNMRU3%7N0-e\)B7$'#D?1
Ku2>G]AU3#(8JAo^qVm?]AgNpjHVCk4]A"#CD+#`9W!4=lWP\3M(%Mmg0mN^2JR#nE79k`\O%rs
0)N0CF_C=k#W_[O]A84T4?G>?9JT(5`H6h&_ZuD.j>j`AJX7DBh#0*11L_\Ht%b+[%%tX*7o!
uO"+$]A>W.j@_3YJ1%O<S5=.3r6$Rg%H+0.r>^Y]A`%4<Lb*gF.`15Tr=CYlkEAhlS]ATgMZPC-
Xiu504$G!LDSDcT`QXCc2%pK&8(2O:c9$hf+p"[Gu2R9qsIs0R"`5pgCcdaKcEYm('1NkKY[
d2q4/?boYas;8HGaM`#HCQ$63.OU1#^.htc$\NXG6h>0'rQ;RP`I^t6WsSk?AKc5&tW=s^.\
B$qG(Yun<#4grDXCQ5L+P"LgJ\jM0B@5'*=lDT@1UR91CJIQg=ZrV0HL#o1Jin^[N.3bK80]A
!6aA!`foJ)@\&m0%MSnjMW$6DQ"He1)k26?kik>dZZ&Jc,j&s+.qOIhE+Mnum,WUBV#=H0ju
AMO)7nBLDfBRc\_2.JtpVYq^m*%sOQ"2HciqMXc4AoD4%&p3Nj?MXSj:?fO83fq3E7o5;k&e
Fo1Fe0o\dN3$aOBplN"J"s)?frZ^&"l1ma(]A(NgS-Q'n,7iT)"Q:iC]AQk#""@u3/3I9rXO)G
Haj5!6Hh"o6YfmNu!(X1L6@RP]AMF^\9;9,OF#lQ#kC/UQq%aA=k+;,+%_#'(tCET_0BWJVP2
4i$?'84Ke]ApW+rXpbAP+'Bu>bI5`SI?0!><Q.0]AYr9Gp'rporgBX(Z6s6gqo<4jC$/Vnt"E5
J'9d!'Jc3h5I,)=dW6WqKSRTI`tFdo=BteiWjS[l&DG0V1Po8T2.:#8%(&VZOh%KWoauO>12
8WAI_$Z(`Vj4PA49dbk/Fc(`;s6[t'Ifs]A3Kh]AMk*XN)HqnD<dYp/f7WNng*f71=E^KBuEsJ
8W[,33g+^i;uOn66:2V2bkj1-)Z8Kd_p(AD5=ToHdI,O8%29pGRqo@l(id<RoI.V[DdRGk9Z
1rc[i7e2n-0kW$L[1'_:QaiQ:[TopnahdhH,\A,"B&I=B9$iY&9%TR$N-d_:Zj>Jpg_UN'o1
F:jk8VksY^QSJhq6`d[<'4fd&=qBaJ(gbY_6UJ-)maCnY3/&4t'69D5@N8fC8/Oe9nM9's7D
C'X_*(a;ahnBaZBtPM=7sUs)6A4Bb/2=`K?Ue>[7"/3,)faPdNZ239D%&F'2W6Q_LlGj=?(B
o!&XWh,I?capPmOWMdqkXCDS)WV+>0Y>G^K0L1t>-g;B`,g"WSV8o@?ZB3nesJNE9U=cW/8T
$5Emi;-Yn4QN%'G1u=f1%:jA*bXS(P8D1G<NQ\KAGbjic3a$mWIg]AM&.r9#&.B]AS_k99Fgen
ENdWd9_kiLNTdg>"536'@u+6u&0U@.e*D$Wd,);HX6(7#XLNdqL=->bZ.]AsN\C##k$*AsK($
.dZ.?.L&QgUl8V,"Pu9b-aI.>iu.&kKLBONMmo:J<%cog5YXM.I-E/!`*RWA3k:@jaJ@!Oh8
>^(P@XbQ1.p@Z[WI!L"e6pG"7j$:N&6)P=([$9qlI/P\CtCTMcegH$EmgF8(jX%\c:g.gG7_
r,5>e&^3<G$_2s[!c-;RYKX7S^hpq2/k'G]AC^C<X=L9QR:*+\8U!2k^+K@^B$Il=9UZlRc%'
YMFiW&3-@N)f4ckmfP(JjJpY,$\)@F`Y_q%$ARBeg2#WT.Ku^a<>=<:TGS^Q:VN1@X6o2\sE
pr5lGiZRDG<ng?(\^;8h&>o;^SlFrDB$Y#H_WI'r]Ag*#n9<qGr8K3(?SGg?MnY.7?eel%2NF
K>X4A\_VbQf.KGnIkIJ'&`:.HWOae91dZe/h<b$QU.>R)":C"_M(t>3.*oY*h"on\HH7-KXi
=QR-p[*meds5AYoG#!$us=^Hd)]A@H:cDm+Rc+a0jk.%;I.;^2d"$""Bt5qP4"Qm[-@)`m^JM
DF[69)ij/.0EXIC[7`9a4MQHU_X0G20hZkos0nCeGjP*d;esOW1Ft'>(X1OtN9;b=`,bu08C
!iZJYk$A>,&r2';fquQR`<_QO#mTlG+JMZKQ+TP*O+B0HABh@7f9j.+_qJFg8h(\T?GlY@";
`D(F?5RMX(l\#*[e\E#IfoIEk37Eu_FB7(?[Q:04>+ACG*V=b%rL`@Z46<I-$s_ISl8S*h"V
asD@l<qT`77Kr\8mg`-@Qi.jWJ`%M]ABjZSU+._?NWQmA`fcL93nrMSO*iT+6[j9\k8K/RK(]A
$/`[2WZF<40kk6HSHiSp9K=*ndYM-fmYmHIj<'o5BuFaa`I+%<uJDZ6.(q3kk>6>lW#O#"W$
=R8AY`gG$_uoo6F%2*T6Ya?/W*MM,/1W+>CO05.q/j88J1CSnsS%I``_/*J2RDB"lCMr1Fm9
u9p<8E)'9%/'`^YV[VjcV&G5Q':7?aZk+1%=_*KQfq=@r,1;m]AZUXfKmb5K-[B)<0sAbWptZ
A_@i!!9HD=rSpXLBb:qV!9oQTXgdEL32k(AMc4<L?-FCaqRjJFh:JVpKdWfQ29HO\^?EaH)A
DW.(P;7"%ga90?J\B/ILW+\ag188+C/<Z&9K?*Mm(AV+j+RqQjf&u33:qu)ObC;jFEe"#igF
qp?VJ5Df(3ehn-2_niM)0il&q#@arh:o?erJ(rmHT\XR,D2Y=>Eu%39-[i'LA4r%nWNg16f[
l1`j2c3uUAmX>kgSasjD1o\EN&P?^)mWne*j.ZTCHDsGnO`'b<HY-c@tGCD]An0lSeEEqm%r4
nK`-mq_Bk?$cp]A+&RplY(2?aqAAJ*fI>[ZQoYkXTSZ>`n*pM)!l)_DaG#:+99H.g1%!.(J7m
jq/0X=',hpcuY&L<ZnQU36JU=Sod.eeN&JJaGRa!^ihgC0M6f*5PH#L?<FqRH5I`OE%V<(1T
CRTOqF@C\g(_5E`Isbpn7\/B$B0IV$pXF$LVY$<?Y&9*sSs:hfgr!.\JjJf_C;$FMpOh:g`,
N5Za0:e#ruic3aY'G*5cQ&H:-N8)l]AkI4L&XE>n*Jbu#<jBd4VNYD/"NSGC)d-#T,7f@XD[_
sT(X6ObtO]A$5Z0\3d@j$#rU=%b:JH8@htDHfZ0_+2e/$ETS83r*l5<LWaPD#)b06m/HLf!L(
5H&`_!L^4fe?XBT#L94R:_%6FpS$eaEo?>i?'k9jm;4^Y1-d$>6Rue=-Ti6kE&\jVb6?,-p7
8OJ;jO6X#F//d5"GjBL>>?edF8%jo[:X%2/6'JWK!Y8+2BED.X?QL;$Ch>UBOqE_i,+./-tM
5O">7.L',JIklWD@VT#8U7>;7i<RMSG%6_j9JZ$YrE)=a$rEGuRjUhsZNVjFQ^7W`-_XtDj&
aC]ACG[n[DmulPg_'SE4EAeb&O*[C%Ogtb4t%rm?L@9uig>`hdBOW-2cckFX+nLF!*HoaJ5nJ
XD+,nW1?O=pR`>"3e$Rj#H0iYj[ren]A8Dat:/E#n"GXC_J9o,9%UL(QD_4cSjS4mmD<R>@8l
r3:hI=7;X5UsKUa:>[0qZT7X$ACNNU/.#jicHU/aj4-:1Z>cp!>K78i@Y]ArA2>!\(>O+=PK#
/q%82lo)bDi?BG198gd?6^P[SDt8gQiT]A"QA5i<"/6[&X'o%akQNLlpY!O9lD>LAkm&nt*d.
L;HSi-AtgY>-^p,S9l;I+8lQNl"ekE]AE=7s\Ys20!6OTk[Dn(J"l)H]ANL2jrFu%Y--"j@.)k
3#E5I(:_-':2aL]Agld4+Xl*.+FaH(HTR"T.mnAkOB,KbN"A.@Jg'78XlcY/!6:9^AS'*/XUr
]AiBdLZeO-&RZX`d8d5<`V$H^t'oVC.;I"`4!Hb_j-rKmuL*%hm=NEO8iL+08;m-mn`DHbB`A
)>HX,M4,UcTA=hc7"*U65DA*@rO1.5SEi(]APRdah]Ao#8Q1G0b>_V?D32Y,PrDC)R)BIU"^hR
*HUrs@Tmrn>jX[BTR]AG%;d#;Ec'j"X#lho?msNO1dN,NiNL9I:GG)aets?Ep&%chYrZf3t(/
GY!Tq%l_r?Gokib]A[R[Bn*\Ja(+EO9Su%&:Od246X?/AJ`%J#XMrUAM$(W]A0$UP,tb4p[na:
\Rf3dWt9;W#j9ml=K:h@qb@neaQa$ZV4b/Q0GmRTWR?LAk!t902meE%APdGu994Ktdn'1WN*
Qn5kQ!E<+0sbSY*ZaG\msm,@H1l0<&6Mj\^8eYZ8ZEa)KcffcdkgHG)3VYL%^5iVuQjG[[ET
QqO;OiB'clmTOG5d&M<7keT>qWc,p*ep`[8\hMsQKnZ0YMD!W\0ZFTg>i.+=:'k1/*GZAXY9
;,S_H0*4eGsQs&W;=TN,oS;f-2sm.\dE+SuA0A0M)(e^Tc==4s4Hcp=B)nFWE=AQk^^mBjdW
g2^"EfgIjj)u?<='#gpjQbs&MXAENJd;".^a)jN&@q[p`H'FDX8^#A3>F+L>e"?B_B\9L+Nr
GJFO:K%h&lBHKI>5/8air.C]AM!I/]A"U-$J^Thg?L*F>9)D'3ko]A14UfX[nD7$^f8@tfa\,;3
A<M)-iVIKl4J%%[k9[8iMiq9YJ<LgMl&7PC+S$E9Q&WaD?ljhQYlmQa#*!f8<cli2Bpfcm22
(VjpMi-$_1b-%nAJjru'B?jFklfU2&++Rp-)mEpmL]Ae%8niLB70L=u\H\M9FBo0G6*j@"Ysi
o)2;Z8j;YKbKWbJE3(WeK7Pd?sqOr4]AP'YW,.(>N@aP#s[k@;?OVCC(Db=$G&)=sa`5\V,?u
7^Gc6b4<*e4-R"6oF$N]A]AQ:H9`9MU)!9TUOIQiF?GcP`h/V1`^IiVc0;`i`i_S"U.aDh$/A%
12HiHmu^1-KQDP2?L&oUSG9+_o&-(&("&EqD[A&Y\4h5H3NI+QE6]An'gX6(V-i1LA]AE-D1d3
$C=XD(;h[QtYtdi"h]AT3AedJTK$5<LI)c#;.>a*,Dq0r2P)Bs'#?u**p>hk49UN+e-Jp>d7j
iS"NoR%&U%\.sj1kC5MfGA55&>m`1h"KjTR_U,nYpYZ[4k39/-QRlUO<n#9>hh.K:>@B7A$S
lcF]A*eL>oK`>TTU2pgt6<HP#ts/msgmJH(79+)/Kfq3=c+\a-GJ4M[C$nNqi&9`)qg*Z']A]A=
<Z2AJpD#%(.8X$b?:n(g0OT^nAhBAMGH0[00aV%=QWjgbIdU@I48!_'(4GqHOp^pW40&Zi`!
>X;UdJ\SlQ1M7G4CiE8u.F.ZM$)=Y:.-^g1XA3K`;0p=T7WZ;Nl>AcKB/&)`'O;C:j![5LAh
4'A$$CNc#TH[d$2SqY`tRc:L:q5]A=B9NfF@RTWd@u1(c4>bgS"fj(FD&A?gqIf:3&&gZoS3+
!(4AdZskLK;Ag^<tCAW[IlKQk"-Q5rc\+F!Y`rc<brk8JoGVZBC/t$klTcldXkP_a"6*NRhe
pB`R"Y"GuJ2rQqRi5.B2'BAEj*LL9S(?L&oIdY361`LXgQX/-+H-Hf=k0Mg!b',@G:u%+4^d
@$#s_@31Q<-"&cb-)RPq^QY6@>#Be340/(iEN_WuSGY^g^6(K:Ud5qkgL);7%)3$3ErJmR+`
[timL5+XT2e!QphoP9:"A=0o<t(oQ_8[kN"YIQHLckf)ONiQmTmoH^E(!<"^pU-VPbK*M-L5
6/JV;H_W+lpA,)X*(Xlp\]Aq^c>`PK/4e*Oh9m;0G=pMl.H;\jpM=6CNs@B4a@63t!nG>B8q>
mjt3)q=)]AJ7L(eF)!HABAlnZ0V;3HCjFEKY]A%mFM4hb8'm9[H>QulH08UGsL=>AV0V<<.q?t
&ZELp:`g!8e<[i/iS)i@3a0Vr+md-bFRQI%97FGd6YIE!@%UTV`;C/J>>drc_DC-o;:"OI`M
1"l/%VE(D&k(k2-(F"iqEB-5#4V8I*jFk`E<AbT)[;LeNKOA#j-^AWX-,;r5/<:5dli:>)W3
Q#j*T>83M.D-'E2+eae2;#1jdS:^!R_N"0GURZQG=$Z1]A$"5g.C:=nb'r;Q]A3r#\Qtm'OX4q
(Gj`/o*(Z1oIJWaB+Cb<jP)!hlDF=0PU&*dH;IFR_'7"OaY##CB!XY:rCIeX0CS\rh4[YCVq
@SpN^-VWpV5/W4rJX"bPpjmbCN*=ECr8DsMi[(`M""L5GtG-\FdU7fn!B:f-$BGiS3WPH'm*
A/e[4*klcf$eIBQJk*-c(6#86iE,5cbV/,nO>m#7L2jNs'>I'NA%6*OOuf@YeSoLdB9Al_g5
r#Q'2=abICJL]A?!m*H6)g#jF>MlCOG&c%N!J6b:0`4Tfi14:JBgBh>S1/#RqHfiKg(KE.]Aia
Y%CVMj>=5!^EY?3!1C@AE=UI^pk9NjK4?=[e5bNtfR_U3</*LEn7jb(^u8Fp@bS^;pNVGm&G
ma%Yb5>GY#($6_T:kj^`&WXac[ah2t#1i]A-E)i&/c=>--W(UXd$@()E7<NNcF2*D3P&Q\o2Y
8mOoY8fSo)0Od*V%s(.;G^?bSuB5Nkn\gn]A#DL_453RIW(Cn!BR]AOK?E@)J5E9oq!=DX!r<B
>%P)]Aq`Ab[4PqO1hb?QhLXqK<<9!P.NC$h_6AkL,FjqgT`:2BjLdTDGb6/r4UEXOs;ZjQ&`]A
'@.TlDb$I`Td&C8IV6]A-j,P0BKSBY-2Fl#Aa:>#]A5W8k4bssNu(&Pj4/=J!HpT,a#3&]AP6U6
-mXa",;M$4m7*CfC+3L/(RD/I-"/h1O,-n$f=8KZ*B=22*V6,jc]AdOKLNi]A-(Pl64&caRWe=
J.0T$n%XYSM2AWp4"[+%?1R,qmlRa[AjshQ_Z[P/>4-?FG\S2TsJN#JX5f3XOIKYi-'bO9q7
F(u+e2q5]A39/Qk@bRfna0jR;D&3XH"[JnZ<Chm&>L"kQ]A+j/%.eF\U\U4)M7?mibRnq<3HB&
jr$7tt8\&EF%)193bNh'[4k1AH:3Ws4WpV'c=KjAIs8e=+Dc6)Pd?uiXQej5(!TFK9UTP+]A6
6EZ^T9W=%B6!;-f#g[YS':).=rRm^sg=!S?At(d'\83GN^"Z7#K,ea;na0]A9(nSGL&2DM6[b
dr"SDgru?1^q]ArjPkZ^WdA)^+qpo`;em('1ZQ,;npj\&=Qd6o(6eipK53l;Z\=E0hUpKTM:$
%!F[D;fX@b$KpBmZ)oC)k#n?6p]Ap2!;ZRAr8m:9]A`Of.K$'0cdr^3dd]An-kMQN-/%o?.]Ar5B
#[ap_%4iP$@e6>1fs:G9IsTXHPs]A44_<\'CJKE<Eku[ZKVo5;H*0@\Ct"7,GJmpuhF97%1ZK
i1?VknALB?Yc%`R\rcLidD,OnS6n3pIB@A#j_LPG`i?3s,rfH\sE%#8t[>GQ`&)UZAE#"MH1
<(`Oq4*\^$g>mb,"Zd:-*rb[D;cC0_^2&!.oP[tSj4]Al-Q^.F#El&8W.<]AXis2/h@LTh1IN'
"0">HLV8i67kNnE<UW4s5aG+,1=)p=kgaACQRiFHZ_tbQU!Jjfu#f3S;65raI#oA<&<&U8:B
Bn3*@DHaNQDb6)(]AZi[AG06\M[q,8,As!!;LT:':R:q$N*aC5,5OIIal2Z^!nYeJFAFUm.Hg
p]A"NlbP<TMgqT/j#I1Dj56&fq"prR'elk-3j[#Pd,3O[Z'.Qf/Y-2>VWq,cLA(ZGFXhZEHt3
(s*RLm4G<!#3_7Cp*(pQU3S(#Ai(p@@R':Sa]Aga;\2iJ/!"$3d5Qb!fhT,CRlXW+12QoH.\I
qI1+PHaEZH8#$T`r#>A`GX?XMAMOu1V)cp%WB:-*H5p2lY<pq2-Xs%m0A["Gn-+ZAR\T!.<B
7>p.NeB=DJ1D:QufqUQG5'Q27iKrY+,PSH@FnHKkHF:0NpRc\q#=g'&p-H.r$]APMA;?TpXqm
K?4WE@Qimk:C#"1T+Qa5)F.e"JnAA(_f0s;k3RB^-5Sr2pld;?"XEPlH;#q_>k6-SVXm5.Pf
TZ2EMnsQ*l#VCXfPqd@FpQ<c?R55JC6%OUTK7Z'+/hrCC1[>sk%^V#`8DCmYmVCijM2E=bD-
&3"P<Z9VX?Wt=1W),N/.+j)Iu'-q"N=$T9nY#[jQ2_jb`X<e:6-?F-DJ$L;QN7[WC)NJ_1hB
r.B'g7L3F$d7bfNbE`%2j?J^/8In(b[4B0!I*QhSZC^]ASIq'QLi-1k"-C^kueY3"^K/?XH]AH
uCNnYBmfjM<W2C9d#+3F^sj^MWm/G1YWP5;?t4>"`ANn`Bh1*$!4nfq\B'c`Gl6mFY19C8pQ
L0h]Amn&V*$]A:MRH))Aq%IFrp-k\!d'ckM&`9r=8~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="636" height="384"/>
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
<![CDATA[1143000,1143000,723900,723900,1790700,1790700,1790700,1790700,1790700,1790700,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[800100,2514600,2476500,2552700,2476500,2476500,2552700,2476500,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" cs="2" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=now()]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=weekday(now())]]></Attributes>
</O>
<PrivilegeControl/>
<Present class="com.fr.base.present.DictPresent">
<Dictionary class="com.fr.data.impl.CustomDictionary">
<CustomDictAttr>
<Dict key="0" value="星期一"/>
<Dict key="1" value="星期二"/>
<Dict key="2" value="星期三"/>
<Dict key="3" value="星期四"/>
<Dict key="4" value="星期五"/>
<Dict key="5" value="星期六"/>
<Dict key="6" value="星期日"/>
</CustomDictAttr>
</Dictionary>
</Present>
<Expand/>
</C>
<C c="4" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="0" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="0" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="0" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" cs="2" s="4">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=now()]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=weekday(date(year(today()), month(today()), 1))]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.RowHeightHighlightAction"/>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="2" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=daysofmonth(today())]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="2" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="2" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="6">
<O>
<![CDATA[日]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="6">
<O>
<![CDATA[一]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="6">
<O>
<![CDATA[二]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3" s="6">
<O>
<![CDATA[三]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="3" s="6">
<O>
<![CDATA[四]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="3" s="6">
<O>
<![CDATA[五]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="3" s="6">
<O>
<![CDATA[六]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 = 0, 1, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=if(B3 = 0,1,"")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="4" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 1, 1 - B3 + 1, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 1, 1 - B3 + 1, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="4" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 2, 2 - B3 + 1, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 2, 2 - B3 + 1, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="4" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 3, 3 - B3 + 1, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 3, 3 - B3 + 1, ""]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="4" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 4, 4 - B3 + 1, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 4, 4 - B3 + 1, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="4" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 5, 5 - B3 + 1, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 5, 5 - B3 + 1, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="7" r="4" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 6, 6 - B3 + 1, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(B3 <= 6, 6 - B3 + 1, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="5" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5 + 1]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5 + 1]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="5" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5 + 2]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5+2]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="5" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5 + 3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5+3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="5" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5 + 4]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5+4]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="5" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5 + 5]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5+5]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="5" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5 + 6]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5+6]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="7" r="5" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5 + 7]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H5+7]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="6" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="6" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6 + 1]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6+1]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="6" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6 + 2]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6+2]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" upParentDefault="false"/>
</C>
<C c="3" r="6" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6 + 3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6+3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="6" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6 + 4]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6+4]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="6" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6 + 5]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6+5]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="6" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6 + 6]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6+6]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="7" r="6" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6 + 7]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H6+7]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="7" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7 + 1]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7+1]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="7" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7 + 2]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7+2]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" upParentDefault="false"/>
</C>
<C c="3" r="7" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7 + 3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7+3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="7" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7 + 4]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7 + 4]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="7" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7 + 5]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7+5]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="7" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7 + 6]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7+6]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="7" r="7" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7 + 7]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H7+7]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="8" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="8" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H8 + 1]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H8+1]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="8" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H8 + 2]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=H8+2]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand leftParentDefault="false" upParentDefault="false"/>
</C>
<C c="3" r="8" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 3 <= C3, H8 + 3, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 3 <= C3, H8 + 3, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="4" r="8" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 4 <= C3, H8 + 4, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 4 <= C3, H8 + 4, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="8" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 5 <= C3, H8 + 5, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 5 <= C3, H8 + 5, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="6" r="8" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 6 <= C3, H8 + 6, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 6 <= C3, H8 + 6, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="7" r="8" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 7 <= C3, H8 + 7, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 7 <= C3, H8 + 7, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="0" r="9" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="9" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 8 <= C3, H8 + 8, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 8 <= C3, H8 + 8, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="2" r="9" s="7">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 9 <= C3, H8 + 9, "")]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=IF(H8 + 9 <= C3, H8 + 9, "")]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/7-3客户拜访行程安排录入.d.cpt&op=write]]></ReportletName>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[day(today()) = $$$]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ColorBackground" color="-16463659"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="9" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="9" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="9" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="9" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="7" r="9" s="8">
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
<Background name="ColorBackground" color="-14605511"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="Verdana" style="0" size="72" foreground="-16721179"/>
<Background name="ColorBackground" color="-14605511"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Verdana" style="0" size="72" foreground="-16721179"/>
<Background name="ColorBackground" color="-14605511"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="Verdana" style="0" size="72"/>
<Background name="ColorBackground" color="-14605511"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="Verdana" style="0" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-14605511"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="72"/>
<Background name="ColorBackground" color="-14605511"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="1" size="88" foreground="-1"/>
<Background name="ColorBackground" color="-13947574"/>
<Border>
<Top style="1" color="-14605511"/>
<Bottom style="1" color="-14605511"/>
<Left style="1" color="-14605511"/>
<Right style="1" color="-14605511"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="88" foreground="-16776961" underline="1"/>
<Background name="ColorBackground" color="-13947574"/>
<Border>
<Top style="1" color="-14605511"/>
<Bottom style="1" color="-14605511"/>
<Left style="1" color="-14605511"/>
<Right style="1" color="-14605511"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Verdana" style="0" size="88"/>
<Background name="ColorBackground" color="-13947574"/>
<Border>
<Top style="1" color="-14605511"/>
<Bottom style="1" color="-14605511"/>
<Left style="1" color="-14605511"/>
<Right style="1" color="-14605511"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="636" height="384"/>
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
<border style="1" color="-657931" borderRadius="0" type="0" borderStyle="0"/>
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
<![CDATA[1524000,1447800,1447800,1447800,1447800,1447800,1447800,0,1104900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2771775,9677400,1359461,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[行程安排]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=if(len($day) = 0, today(), $day)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="2">
<O>
<![CDATA[＋]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="行程安排录入">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters>
<Parameter>
<Attributes name="day"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=if(len($day) = 0, format(today(), "yyyy-MM-dd"), $day)]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_self]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/demo/Phone/form/scheduling/客户拜访行程安排录入-phone.cpt&op=write]]></ReportletName>
<Attr>
<DialogAttr class="com.fr.js.ReportletHyperlinkDialogAttr">
<O>
<![CDATA[]]></O>
<Location center="true"/>
</DialogAttr>
</Attr>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="1" s="3">
<O>
<![CDATA[拜访时间]]></O>
<PrivilegeControl/>
<Expand leftParentDefault="false" left="B2"/>
</C>
<C c="1" r="1" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="拜访时间"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="6">
<O>
<![CDATA[拜访对象]]></O>
<PrivilegeControl/>
<Expand leftParentDefault="false" left="B2"/>
</C>
<C c="1" r="2" cs="2" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="拜访对象"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="3" s="6">
<O>
<![CDATA[主要事宜]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" cs="2" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="主要事宜"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="4" s="8">
<O>
<![CDATA[联系人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" cs="2" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="联系人"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="5" s="6">
<O>
<![CDATA[联系电话]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" cs="2" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="联系电话"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<CellGUIAttr showAsDefault="true"/>
<CellPageAttr/>
<Expand dir="0"/>
</C>
<C c="0" r="6" s="6">
<O>
<![CDATA[地址]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="6" cs="2" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地址"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="7" s="5">
<O>
<![CDATA[ ]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="7" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="8" cs="2" s="9">
<O>
<![CDATA[注：点击+号新增行程安排]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="8" s="5">
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
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<Background name="ColorBackground" color="-14701083"/>
<Border/>
</Style>
<Style imageLayout="1" paddingLeft="5">
<Format class="com.fr.base.SimpleDateFormatThreadSafe">
<![CDATA[yyyy-MM-dd]]></Format>
<FRFont name="微软雅黑" style="0" size="104" foreground="-1"/>
<Background name="ColorBackground" color="-14701083"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="112" foreground="-1"/>
<Background name="ColorBackground" color="-14701083"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="10">
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
<Background name="ColorBackground" color="-328708"/>
<Border>
<Top style="1" color="-986635"/>
<Bottom style="1" color="-986635"/>
<Left style="1" color="-986635"/>
<Right style="1" color="-986635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="5">
<FRFont name="微软雅黑" style="0" size="88" foreground="-11561760"/>
<Background name="ColorBackground" color="-328708"/>
<Border>
<Top style="1" color="-986635"/>
<Bottom style="1" color="-986635"/>
<Left color="-986635"/>
<Right color="-986635"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-394759"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="10">
<FRFont name="Microsoft YaHei" style="0" size="80" foreground="-10066330"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-986635"/>
<Bottom style="1" color="-986635"/>
<Left style="1" color="-986635"/>
<Right style="1" color="-986635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="5">
<FRFont name="Microsoft YaHei" style="0" size="80" foreground="-11561760"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-986635"/>
<Bottom style="1" color="-986635"/>
<Left style="1" color="-986635"/>
<Right style="1" color="-986635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1" paddingLeft="10">
<FRFont name="Microsoft YaHei" style="0" size="80" foreground="-10066330"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-986635"/>
<Bottom style="1" color="-986635"/>
<Left style="1" color="-986635"/>
<Right style="1" color="-986635"/>
</Border>
</Style>
<Style imageLayout="1" paddingLeft="5">
<FRFont name="微软雅黑" style="0" size="88" foreground="-6710887"/>
<Background name="ColorBackground" color="-394759"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[ja284P^gH_8uO;+[]Aq/J,g47a`f`m7;Giac0/EpV2Ifff.O#JE(fT*c+q8+MF^c-`1,9OuQ8
+SGm*;QD>0h>_o3BW9r6BS:Y00'(]A^O2;?='i0lZ:tXSMQ!J\2NqqAmo-,dQORH`58f$bkr:
;?t3Nb:Y$1g@F9FqX0$Zm-YX">&1O:<OYNgC&[o$sqOi.^iY8!ajgEN2Wp\['B,J?m`r8[o=
LU5m<uH<GaEbdtIto@041Y7@jO#dSO)tXolZ*4A!/-FFD3rUS"2;L>8+o,1+sLsqI,Xt$6)!
L]AA;=Bo0fDWKB^b)\dE1=3rpic,QhG&(_F9+gL8k?0VT`uF?^]ADG)0N0+4`bTNSm+!hGPg<<
SsiTj;pjWdGTbYJqa3q![pN3[Pmff6Esc'`Vh2K-_*Jj\g14ZIgo\$FO?qo`g/.GF_$r,hOK
cE(S"1q!K\<2=RXaMLaH!O7Gu-f_WdW:GeS=ACee(Vb!7/:eN)Lgc+;f.`&9B-aG9;\OM=t_
ZZUf0/UH,2a(YiODcs3oTP\VNlE$a=0TPJGGMYg4A_Qj`@*>9)3VJ"J;IA!pX%NF&;q%GA%Z
Dt(8,*R1";<6OMhV27Xe-'a9^H4H*,X9.-CLs7mm'bJkN0Z^:<PVc$'haNTH:RW1`H=<77p8
I$iZ*42a+@4_gCd>_$%d\H4gT:3l]AVKR,=77Bf1C+YV!,i9;>F[[Vf7$$AEdSFN#%[C6#\t6
L)hLRY8ag'3DKeceE1_@,]A\/47dHltL^.sP/^PY<$1+I@21i/E>"l(/K=:G!`$YpS$T6Or34
>psT/UP7Q`*ZR>VrX4`OG+>)5\p-h0nM^YOU^A'$,(_IJFMj8G1-"onqm4mAe3Am+\nVj=3s
/fMj%2UK3[]ADBHK43fd9^kgk\X.spV0DpJ24PJ!YsU)_okm9A7F&-`i[>:?rFC"Zo_UijeR?
:X8&dq!Xro)A&tkUrr?m._I2_J5[4!"mb9QUV$X;H=,@7]A^r[7kGj1=&aV<+?T50WUSh+ceY
dEV+XJJdqC\RO61CD2rmB'Q<ZrQl=L9cCJs%8(=6\UijD\X!>;IMqlr!DWc1D`AIu[s^1S.\
mUa)m1lG!89(&0)4Qk7gEu)q33SgY+K[O*d<U:<K`ep!J_cS)7h1%$O+BF)%B]A8VFAfeY@]Ag
U6*(7uT<N$^!oPNkd!(6*_mmOKN5QKf>c7F0R1Rm-$*h'7:q]A32#Y+d!#P-e0e[$VX5D:GJg
Bhbn*:=D13>r1P/".40\6PUk)`Wd`'ZI)i>MDTZeab*f2KYO]A;prJ3#e9nNoen]A>7$G7FM1]A
bkATol&fgZ/AoI<ijl!C93Ld(b*qcbo%^3%4VQHd'\PEDi(#A7`H_NT*eL)&kYFB*X/Y$d4a
DgCQJ"d9NR6[L0=Sn$N:+2j$#&G6b<"c5*kn((1]A0`4Jd4[-o6,gSuPf^lHGMRR1<H_o-`5X
,"2c1h*p!M>=!VldYTW7>:W5QMB%=9mCZ14^DUXMI>9s^(0iMj0`:EXla9O)q1W)H2+6FpVn
49nB=$oHrgh6@fABemkC_Su_Il*!Sj4*`\M:j*\WbmE&a,W^;9m?(NK[5SnoWeV=Ct`1?k=l
k_'a#j2=P]A2HAi%s=KAB2d*d4^Ji'4eco_m5bru@;'3EX67=\:SVe6%3hpIu_OuSCoNIl%f8
CoWfUOVo9_OK(JltibbF.tdCGocfP]A33cN;D0PQ5V-?($pK,"*1>7E]AU0T3Ta(B+#'Iu\]App
;a-9,t.L&c.\U[i8_kWeYQ,CB]A?@'NQhGE:HABibG8/TGEdU(s`XZ$4Y-qW/ugmTP42o6T<-
&TF'VrcI,\J\t7q%B?ds=@SMTR+OenFAW3B<:df_K1mN1-P%gUUEjEeAY6`uI8hE4r:Dk+?@
tH#OfkN,#B3#2#:^.\+I:l"IE"9-;'(,?n.KPD0=9AP\_/I8alq%"_G6L[a*=p09DbDQ^gT&
t1f5\d=1O8c?]ATb05iLt'bjX6U1]A_1F4_c&UM8+%LOe+MAPe,4p#LRV_`(UBC<SF$@fNQp&e
N[m=We$XB(,)7A<N_V8LNm`UPT+!e+8F&X&QGARW6#=h5,McjV0!NI4TKg"C@<#@`nrpK]AE4
"]A0(79+)d8,3*`Ws6.DQBHJcVk^Y#OE21<?7PEg7leB5&[Hpr.:U;>VnPkMnY#6H,.Ndg`HF
,(C'tiGm:2MOIF#N?=t9=Q6;`<!-4pnfK27UMdiFHE%sR0orPk"ak]Afi7t5e`ZS-LpTqea6>
8`7#p_psZqe25IiuOpQprN0a^ed(F(u@'_9MsAH/WbTg!CA!:L!Yf194_24AR20K=@mtJsK)
5&2ed6c?2NJH+Uhg8!iceP.Rs/W.@oqmeQB-3_A>C"7\WqGYR(%%.=PZWOL^4akBSeoN3m6!
=9o`a(V@09/:@ecja:@D&cqBUjHa$No"#TbHSoi?1'U+n?D+=AFf`i]A4LERJWAHB+9ltg]A$%
;_HK%HqV'V4:Y`[=VCgIPZ'6*B:#=63T>`PgG4:P8FN*Tj89qYO%KG^GC<P`!C[iiU14?QXM
L%Z!)KL;Cg2^Gu8Zkl=R!\nW7o_.6'IG4RQcN6Q*lLm&,DU^9h2p'f"&C4Dt"$&`Bb()Mm9-
5/7b!%m.\,Gkke0d>ub<7A9hN$aBA0`fRaF^*37@.]Aek[G$SJX!%?bWB\6SB;&I78\GCh3.%
sds;Fd`P,r#",T:=-1eGjTS<U)P]A17C%Gl[dF,b1*0eOMQq/7YR'Wk?M)[mE?U\amE6kDM4j
\ACiqM]A%_>md$K%:2?mACO`@1AV,/4?Ttn&)G!2h**k3/4i!pl/HCA]AaIB)YFLtq;_tK1B@f
A"naX5(iiV1d%4rZeG)TU%rX7JDg8IrI)P=<6:Sj:<IVkY(M\>E%SZMm0<`?MU@?b3T^ob/4
_M+&8q<HWI%$\V\C#MQW$+.K\a'P1J>T@0/]AqB[$Bcq![9/GnTgT?AP%+/9[R<)#+mU%D51:
8JXW+$LXjaP([Fl%P^E<P`7p%ZSP,S:Nu.lMmRVJo7)G\:uJ+TXDu[!6JE(s?_6[T7U%a8]A@
sa8l+(ZNB=_bg^N^hopIg]A<;oZ\Z!Z@mPa9U60TUj+P]APB>FDL[eUpY3!lJpte"s/cb*Q<Uc
k5?F9d)1cGHJf"pm*N]AG-K_$ihaX#_$5Y$#`$WbY\_Cn%YJ?c*(\^Te,-jSHt8-f4Vi'Irg$
L(?>c>ls+HQj<JFA"%GYL1&7s$,m6c$P!qJnlA5emf#W/^p]A[U3BD<a5plD6Y25)?hT.ATmS
%F^\-he['*_?9^Pd*Z+H-8rojo[JS\hE$b8a[4&2j:2\>(e<IZlqo9>g'ia$N50j48@=KrrS
Z+nEcjf=g/\&o5PktM]AI/!i29ln5Hnp/*qMDXeO<61dm$It.kYO$I\"=&]A41"%(S]AIc?lQYe
icoY<aHGj.Wn,>rJ@\pXIZTf1$qf/I);oJhZCZ"6%R[H/`Fn*Fknu+fk`:9thpTD?BS^d+<f
80;f_6><Q`_]AFX.4&D[+s:WtIu9a15Hlj>;A6=eOEWhG9e6&9>.:gcBpL!>!6s<[SZeO<V3K
F:bD98gD$f(Em$c(!23VQGfrOSo'!g,.GIVV]A@Nu4^q-eP$B>n9!1&m(4*.7j#q'N5e!m^?V
jLom_p1oR#U43%s!uiQaSAIrNV[]A#BSi)NE*GRke9q;dI964Ufj5Pf(E3K5)6Dq,D[Y2I*R?
OYSmp]AeI(l,4*2m_h_XXO%eor9Rqq`D2`s*-DfRI<pJ*1-F(n:lO1I1lEl[7"8H>u<n)>t^R
8+?q%I&"(L"_oc!crNqVc@:YEZ3*F.A3B92LR*j;>DB6*#ad=F<Q7pMEUj0U=?\CiO:G+l3)
og9lZS/=3)__=@KWKf<%2_9M"Uc3bm85AiUQ0B.Na<L1"7\cfX"q95iY%QsK4qcF?_V5LK,:
C9)'=UNBYBWCg7T2`B>*<MT>k!%Qji[pq94@f<[\k`*gWWZ>2Y;*,f=)2N*r3N3XM6@Ir*!(
W)+p,%"nks*u>\]A:<B.F#LC*m>3oO_Z]AsceYB0FH(1Q+NW2Q5:;Ycd!p@Zo8@74R&-HEP#D7
?Pe3ARGZ$N!1>1k#>GV`\^d3l"E-?>uKC?7`PRJZhblQ9[3i7#L_A6=I&&%tK6O3IpbMH7+'
CjfO>,UZ@<G;f#8S7?=M;6Xb,Sc$+]A&#:/nKRa':fMXY2&<O%'b/V?2\-9pV;3@(WE0)(ql*
+i*\+1Mt0h[C$32DfNfPtF,\q5FrZYLQ'S=DDC<d.CoUo^hY6GG^3Fhcl7]A>E.uZj[B\rBLd
qj(-g`(elj*;H'1]A]A.@<"g&O`Aa2^(6s7(XXm$nb]Ar'>p.>\jb9S&a.HX`.Xb46Q,9nUW4m8
bKUP&)XsT:ULk,-ZO44"a[p)Or`P/a+0'0*)D-B1#2>Ss$7^GWl<BVQ-;Q,["B9cX46<GN<'
G$!?sY<P7fpaG;Gmj^jqs%1(Kp6ai_MGI6sC%BnZgSG]AO>`L/u+m#"ri654<T`J3=-+kMe>*
o[\pP0\=.!\SB#/X!T)H@#4]A8_0@7HE4o-<:_@2'h4_*sMNj1839gEfPaqRX)%/j-fd$\F\H
]AD8$/c;lnp+c$$N!!<*Y+>-=)5WjU_BM'4'&I6F>e*s*\\`g(Kr]AB\q88O_7HelVk-Sql4YF
?ki9Y2>F9\Au6,aR,]AHL*%BSMjRaMHn,hg_k]ANI9bt.?&Yp:m+'$\HAq$8?C.t&5/p"VZn1?
CH<#7n,^i?de;/S:\.!4,6=BuH>'*;XRCUCmO<te=CRT:2LYO1P-1_JGpkaD$\.?;aI)S>;Y
X(B&FkdM]A#@:5"N)b$ce>?t@\MR#*J.]Ab5diDo]A<',Ff2U:Nl,qgk"K-'6h-o-J6kgN7"PJU
Yg2m.hnOdGm8OXJ&rJ!cXICC4[K#NV[j\%(]A+=b*oP+r##:C74e[o#_&-p#K=8>4V%Q_&`/k
gBU^9'%P"3!luk3ZlJSUCBlg'nKpY4=WKHKDRf$4r=j6#-L_t#oYQ6_+mSpe5X*p:ASp3gj:
nTg&eApEd>60!t?L4<X8t[%J#9'15b@qrjiLfVd$j6R>GGU.R0-#%-#T;g"h&,O,hBR4Xf?6
1P&"IiQY4AbtmQW':?a=hiH@S\`'6dlR`&sf?d>lB%9tW8A?@Zk?*k:%7_(s<PFlnkG).8'4
ikJ5m#,8$nUir'M6rQcXpQF3KHATP:LTqYGFu_pJAfbBUa+uMb4H9L55rX2*\T9PC,CD%>?G
2VQ"Ie\,5>+n:iPBVm*;Z;":e\WAm%NHM$-Emf;M3,0ou%["Sglg%]A_XJql?0&D#'2?Nq%;G
s5&%2hm,mhSNEe3c,e$_#*GJiU59A_r9V1$QNaUiLSuC;?ZSmAfin@moMPP4Z&9#a8"`qMCI
&'Eo%O;cV]An5QQtRfi+&6neUdHK?Ecrr=Uk>^W1EMFY*moha).j0roL('KbM>KP/k5>G)Ciq
FP>!7=^rH[ESY/3`7dm`o1T@#B6am]Ad2%8qB":m;HFWu.H37&:lW@C*1*$=/@a;7_Z5hsJ'_
sBC22,0ODu$!+rMqSkCg:7rd"Ce>:9>!<k4"EA!?i#P#@@5PI^XsBHad-MG6FQ+mi,+<+B_l
NGeZOhG$A*qiGbQ,Bn^'MdaCS1]AC2+F**/d=mpXj4n=<dj>U@3ks/?T?`i%d@\s'/OcEd%_S
r?IddpsMQkMkZe5fSR>MOOHZZeE9(+G4Dn>R3hTfWPT8CkYguI,f09=9o2JLU]A'rbOUo`-K[
tbU@7ef7#g5G9MVp8P1l)%B/d/HP"E3bm-E;)0m[ZtOc?G>aN*%?VeFqBN'/#aDCP8rSUI+A
E\`Zp(ufWX5:C%-YhT?/n3&SAWeO_%+g3Dpn%M7/)bVB#5VL"t:ffHD>!()nI3%i"HfsbKF)
VF]ACojFREl%uY40)L_Ni:HdSVQB#H!bbLFG0>Sdr:"X:-Y["-*6&Ge]A]AED6_N2g.0)VXL6h&
AK^UI>\[,`ZR!p_3>MMr$2u)GnPXEL5+i4@6!fa,]Al&eE;/T=!NHuj\il5?EkIgH6=lU-,D:
0&t'Er93"U)rfCC>;B;?f%s6?TdR1"p544Up=ktMn-(p?hJM,:)7*D?uf@f@uG"cnuDA8Dq;
l9@Z^FD[^UbR0nTd[Cs^7a#_h"irKBOu7+tlrF\;+%eUc4E(jT9BitU?@!WkqGf:i[J8/q*5
VJYH.+sl>=3X+BGN"Bpc`HX`&;@VN[OsFEb7bb>ZQ+FdsL[8I@='Bu0klT\\(YB4!k(5]AM$h
,;?2.(Jpl-\iRM^O/#:57@Bd($O"]A!bK9_tGK@Fm>K]A4Vra8MN$AcB0@mA4=pYO]AR?2oHH1&
o)Ph_mDJU6P$5FrS]Ae[>XZGl/#\XidbA=NeBQn35,6??m*dkU0`3moOu_fkg:HFgN\QJ`Xhb
;T4)K'7)&F=*,Scb*+15nL8tg0sN^7[TS%CsE=bQ,]A8jrct9@qUYB^p7;\?:F:6#MiP`smi/
`#`%0Tk$<G".A)o`9nif&HR$]A.qieWAHW/*P.?=nYMgWM[aNTC6W9S,[1j(Vq!_mp)*Q^EBh
>@m,I_`UaMf*b[1\lE`GXK%>B^I;!fIY<Rk'!$\tniY"sojhI+0`:`/NdMsgrX`6<H#!f5m!
)/U*SCA:N/R5g2ZI&("`R((G5NUim?Q56G9ZVbrVS&WOK6*d&$>*l//(8e`RZr6!!K?^X7($
H^@Xmp%>c:%6,r(Kl(7=WTTNBD*qR[/P)+q>0%n/78-8Y+i$4tiIO7h(:t*(-lM&tHc8!L43
$Q[!&,004N7@>jJ,6I;dPT'e;b`srFoWSsm37$@3ug?7:^,X>[qRFIY8%T1CfR!b;ZWLJ9e0
QZkcQNPJDX5"@t8<MJZ#'gWUrPhT@?K/PPRd%Qb(Mf>V<ST]A@e1kmG7r$2TV^4?r7Q-#ioIQ
j]A<"5"eEF;;5hWQ2K@rsbmQ`,*J<6=<CM4W*U"teW1S*d9Bkd9CY<Db;-)1:V$St,RT_]A5Ph
:ZkO)/-,e"F(:2;iZL^q]AsZY>DFSGn[#;GV:j2*="7`Ui+A//L]A,^gq-0+l1jC0n=e;U(ch<
a9Ceer-DZPE@Z(i?,ou",B9h#d'KGq!N&$/RjA:DnoBIcbYJ8qAZ?Y&dMJk+<na<bX>.5>8M
Z*sf;(n$#Fje`N9>&WMjiu,,B"JL3Yjp[S[p2%4pF-u(@G43Om"rid(EU/t[f*l2pR.g[.6s
8%Q"Rn0^uf;:f=+(G6J\52olB?"d@njdh$b`:c)QB6YT;Ur":o!Gp@_0OGhWI&FYGm>+(Q5Y
hAVG;i`TiOrSJK&BELno/5P(?d;dI<>Z8RVXU3C-U$=1i\0gZ7r,3M5Ri%I)bn77Jj8HF2]A/
7,]A*aqtGD)ld$5TFQ$rhcJZ>52hT8M+?DV+Q/*&PY;M*qe3Je1I5>"^5Aj%nq'NnsM-%s1IP
UG>1Z=aEi+qWbqZt_6>i/=T#O\QOItTAuI:&OMc)fCuGX1CAg(R:]Aau/V=,'G<]AC043QNTe`
=5@lk*6X>9[M=:pSQp(.7@=hVp\Mp7F9nS;5&N^b_=)c6T,p-QtUtiI3St#BeNVWHVqtM;<X
/&5IX1L$9^m9!1n:%a_Ld)WLZ<D)cLe%/)kEFptb!`CcBR[pVRu-`4398g_!8L>m#t;9P/(O
4e5t_Z/[1k/m)PI,j-%G9;TS]AW]Ap#cr\NsNoe8eh>AYcfLYXEYU,$foIdQeH@6n7abb]AJKC!
=S"eGF@L,hV]A>Q-(SV+:G@9IOF8gF+HeC+J#cY!n!?i64q4_+l[i:h<1<.]AK3tD!"s360+uU
t!.udsEd;WiBIiB=fVL]A$bQH'HP-==X,CX;GBp0%G$A")+]A@36XF$WJgeW]AN+IoK`hl?'<k7
!uqu@EW+2akZdAAJIB\q_RMig(:2M:`i'aP@E!2!!(]AZYWd!]A%htV/"B$KV]A,e6?4!O0?[_e
"K=oL$cERVLX#;1!e2Eb@?'E,qS5D2BZMN#ki,RS:cGg"fR_1["Y4(d&\S?N]A`HhGHF(At:2
jIia#8j)W`Z'D)Cia\<JT]A46t6kZI/&3ZLU-pp[uf-=KcTJ(YcT</=o[JPs-N&Kkk\reVUPe
7oV]AE6?&k.gHOb)[Q.2&k*LRSfs$$\pAoLmZnkE\T;F'+tZ3B/LY'_R/<CF_XG7'+G&]A`+Ut
85nAZm.Zk[ehSk?amJj'^:afnA"Oue]AWs:[t$eb[\Wh7&u'7dpc:0ApqERe,OG(_VMC4R^'U
U(,l\1W$S1X+1h_>%t09;06[T'Y)j:fpP)jW-=SK>f.3V?]A=gT!_blG<SX24%JY_1=1A=d_4
6hfUFXXI[pVAAK+I-YpKteHM\&7c:`<O$q9s/3Zi@u>JoD(.gX]A,$H:7RQmgC`*<h'EnmM$D
+N6_HEko@_bDG\n50^IIo+.?^hkr[5=g,ZO2EnRe&nofg6J02okU]At?:Ohg<>7EJWHV)?d#n
H)mLgmn*K*a1#idR_Lh>*pLFqu>a4;l3__%jN"p6hE"94M6PlX7GT39j4U]AN&D.NK%3AQ\CC
TITKH3/K$k*pV^^&Qtk,fZE`nk`f5HKo_]A.c2"V5P^Sf%gA)!lkQ.6XtXCC)S"[4SAm%%kJ`
S"KX0pY:#HjfGKfs*1@QX_PEX'F$BSI9\?L(+RS`L/u.o-:2<Tl$sXZ`kj9s(EAJ.p1\,"0X
IOhZ"^j1"AU!R0AO>=C%SGU@2*%M&@kYUT0rCg7oPgdf]A1Wj1;YbhXS,F.n$;6lQ*Qh"-[]A0
9^iZq;g"D*=?[nej;=_nIV'W?"nsGP[o<3jc'='O&nuTA7`(J"Y[?G3@:mV?qNo")GeYE2Ta
K$t4oeBi]A=3p+#l!bDIfGpM\)=U@h79/91ZjjU?O1AUG9HsYlp/J'%F#G:MEfE.4If)77'ZI
jN!):DSe!WRo1f@>/="1RQ^:1Q",5ZcpAQ[.!VY745pJ^$`*B%HJo2uHLOnDu=muHpG.J[=B
&+'e[DFf>)_RFu#8WU@R/rHrKB33&qCi7pf^4Sn"E--Rd;qiU\>$p<\(C`4k?1T@n$/fW4d'
@IrUacDK.Qn'KDZ-R[*.9(1Q3[_02r_/K96D1>&\Kp"q/Eug?4Z=dI*n>r0NkQJF-K-io'/M
$ua'eK+eJ&@P"boSS@5_=NDi2h&E^TTI\iLR8iPi7hQMq]A\k=ch_D9lC,SJfYT>'[lm60DO)
LOq3ej&*06l;%n6!=D<&.VL1u?SFp=1b8H>6L.IKdplH,cRtR$Dt">2E/?fAQ#g1?SarLo0Q
e/?ruC;QLL-gQ9hmoq/gBl31&.V8l7Y.V'SR5DR&:-#k4#KH&,`AR_m?dmbF1)DO?YF;gm)M
=Fqu;)^G'<gi"]ApU!F4!Jl*AEqqfej>^bZ,bdI(gWpCJI^*#0:Nd5+@Md#%(5$H_3SSm#$\Q
_-Tf6eYrdseR8\X2'XVk8\k`U6?itR&h'ejCJ]AJ#qqM:)"$IoP-4N3^Pa/dg,RccY7[7NWo=
cj;'(2-'nIh"'AI,k44gLhO?m_h?)!As5.:U6k1%RaOE$!_m(bPl$JDdCITCW(c!37V=bSNF
Pj0-Pp&$"Ku,+c:4./:S\GODk<.-M)UCu493/M[8Z^U'gEI-2,B>Oj.L1]A.U]AeDl%VuMNZX8
#.f:C.jYCaV=WERJ5.tR3Tm&rTHVRjPb'mX^&)cgc@g_LHK?DU):Gt""k(r((ZFQf(g%uPD!
r,lPR+I<W&rpoZXt9-5f'+e!o+eLh:9`;t,n=ui$ss@8quhK^3s=7d0cXQMDL-[X%235OV!b
S[Z-]A;.UXl8YTUp=/;CXA%'C^hHHS.'6JqW*"6))"fLsbckaU.c<J1FYe?8,c7=q4E*a#'R0
"f<q2:"g9J8o\f0g>g8QEM-[(U.s84I[3i,%fjH%@'u:ro(XqsLT;V=O"`;B;M!\eFUKFa^$
ob/o1B6j=k9I.+J4i490c4@*rj0(_Th7E/_'G%)p\:($to:Hj4kK3e=Aq:EEL:=7XlBj<'eb
YQeVhZj]A<aX7(l]A@"KMXh8@q]Aer_*\uB]ASG!'_dc%nABI9\OA45pLt3mG3[cn@*/8rU4f1.q
-VAas25IU?TllkUWogLou7ih`pgYuk=3m&Z$+975P?^]Ajlgd=e$p??RiK0QL095se$q9bf5G
3nB#A9t(<SMl$f>%I(Dp%)]ARf03dZ:iO.1F#9YGCjcb:$j+A_pos**h<W$cgXAlq!FC4Aj'$
75%\N45ranCW(?'8rN*rq"hI&Eo^?'Q!7)-/Z`?Fd2P[dJMQH[d@e$I%IUq;VK*5\2YtNA!$
MPF0$NpQcb3<HQu&_K@]ABedFUIh!=nLbHEsasT1ALI*(?:Z@pmV5R%c8Ffmr[a78T$WX[Nh9
E:HT.@%U=C%d5TcKnoYYAiLW@,>?P7:1_!\@M'RUKn!I+Mlq'el`SaUDV_X6*OT?QjY>@P%#
tma.MqMCAWm04K]An%cE7MRlS:SR-/UU[&@l,eM+p2mpIOl#Ak\pPeA%Rs4sdQ(MX)qkX7l(E
4/5eN=9gXW5[5fVY(h*Lc^,W>g=S@Z#C:Wi,?aZe+c&D9-+&"Hl?h-eZmq(jfiO$'u$_\DcW
:OBk$;hL5iepFl-Dt-a4b)(%uVeZ9LeDP!P:[W#1I\[UU?/_qXm8U'L"'OOk\`P(.2/aF>,5
L3EG)A^?AA>fd/ILTM^U(:'M$+C:Y_@eP-2*o6_":@b58Y=?dgpl5bYPhR)c>!k@;Pgl&.Mp
J)S3cM;(*70[.iFKch*^h2p)ei`#BY"]A[XrRo(AZVjFt#?KtLC''m)hXG'H9khk)!neT6*^0
f"T-KYbU&!bh,:FJVG/fjcQA^tM0fU`dK.lN%<R4$8YuVP?3c/A30Cs%l-eI$C)bYH*J:lCk
*?$kP17.h[Bj2UiD0U;$UEH#Oq%2_cc(]A%33K*-!#JrT(5c`0MJK!T\ns?rYSGVFd3UG&Q\N
UDM#S*^0oO4ZG6T+rDT:?/r?JqS)$82*B9`]A\u5W&OtWOeh=2qfV)X?]A1"KKfFH*+$47(LE0
e>]AgOhIM#[RIg'MdhT88sD_*u$3^WQnC:+[;\14-P.\%QkZFD-jdKjSY7AqE79Mh/U!/((@^
tC\[tV]A84D.]AMB2s$akA5E@D3mO%,18.V6GU6>NllRNLMULZ.oLRb'(4OeM)BT6Ig,:g*()/
(m+J38N@B(RH(-Wr)FgISq$bI>-W;*gtBiEb:Y[Yu$:V'B%5.o2J(PG2N3-KF";LQZ'*-S`8
fh\d)m16u/OdHcQ(A(XnF<"rfYB;Qc`k%hN?'";HZVLq4L$<.1p/6k[]A;0.2]AjRprKr4k'b&
rM.3]AF?A^[IA'kTjM@7)?&S]A-'tuT1>F&,PREhs#-7]A-/G@m1Z;(`7oeU'*=2LomH$n>5E0h
.:AIl[n:Pu`e]A-4l)'H244P*:1%QMb)FD2Z[6h]ACBV-K*YG1YG0t8Hs`-b@/JcZ/*1-`_"Pg
5<ok*=L^."HcR)>]AO\/1Fpml#%Q`6RS0#OBF,[hFcNl$6A/\QUQi;B(#r8MQ;7k`T3^7i#!2
*F@q$YMVB3#7[fLC]AAJ#O$#&^//$`eu\1TKDZ<uW*/Mgg.T,[h;r=qh1LO\2Z&jU#X*J@IRO
$)M6rL_%0aQA,gfI=mc>Zd$\Hs=p\M*I8PU5[jrKZpMSjJOYItuYj?`>9T9;_Eoak#&XNJQl
!Oc4A0raQJkROSRJfIMK$%s07)^Tk;ZC^/HV<[$IQTfj__9taGHqFiSc]AYYDd-:G-L8Qu8Aa
lp/o;ql;2F2;)XZmA)kH@;LpE0-L-DEtD'7J2a"kA4]A>?Y5&W$,(T]A>rWc_qc'WOZNY?"4>*
IX0Q;tg2`Y^$mCSl328/mq/R<121gb[AA'<%d%HZom:DekJt]A:$DK=?M)jj4=>bdb6"lOQ\$
F>mm+U>0]A"<5<[*kW]A>.(Y;6NH\9=0`67I(-5N_p->9!%5s4WH#$R<:S_/r8Jd0P#fCVA/$l
V"(JLOSc'Ms-+Q=ekm1s*0c.u!A[?ZmY#*UlB.f=8t8tB'$qrn88]AV.0V-UgUUN^tGmF'A+<
@+j*R;A+U:PL=I.N:AHa!sVq2Z1H*C\.f"*JU(bK+Od>%W6aH4d\b_k2O?@(6hW#KM^4XVo;
B2f00qso40k$s8&/#?l]AQ_\>7$$@H3U]AW^IQKXjb:<(4!#lin01;kCS;QkNMK=fNNZ,nG.eq
2JXWFqM9ddm[t*msQ-N^HW&6"entDM2oHKj#;6(k,S?EulI&Q5R+tNS^-tM[A%91nWrie\AT
JN(I::iJ'du:qdchuk;HK8f;(8)o=)>JH1<uSN,8jYdO'7p9ZD.EA>N!Ft_>,6DC?p*EC=6h
PI+08isZi)7nHp[kmJJgIeVJ[39qPiZmb>kN!NStA$^#kB@l$(FHAWQmmF*Sr:i_;\%p4EFM
+r#PSPB\9&FMc%a$a[[(CMh%8Aj/B-/_n<u]Aj`[YE[@V3r,\d)*&n.V8hc-XHA'#DlHAs!>)
tJ'0bkYC>TNNDllp\?qUttY*#8B8$3IUtngn-h)i^gX$0`C4h2YX_#X2S!K./!Si+t;EoG?g
#jYqfTREba,e]A/HHi$_$**^$^#\60%8@Zm('T;sM;8]A8mFR2jl@%%FQ@O%`A)`-G/1"a5fhQ
T+>4V=Q:P$Y2+;R,!/FL!^IJq6$+=AiB#.e%_]AC.E?)';HB)%!8$o`Sss<'2FkYj#197(:_1
no`[>t4nF<_6XH@$m"`gqO2^!RXH$jGM.h9@A:)Ogt('59t3kSXHm<c.7V%rSu:@k?2bJNFm
L@)V0f"!=#8::29\dK'AEk_XDRQ_]Aid;b\5+90*G:5LKP58oaibT5H\Wi<\6OXNP(T=CT3`f
pt)'95tkD`:WGYO;Ohc5dYrCDr4p]AfYpN6>7b:I/7Vq@=("pM>W?F:D#!s:slLI_"<iM\`&J
o:Q6KO/#+[9M;b$mm^DiW%:\snXMNaV3T\F#,'(hlZ5;S"5$mHt.GCoR:O.re/ho_MWf&GD/
l&fBOl="/)SI@0j&o9>7_*i49+RbL6(`$Nm=k3c*@uL7)%hT=8P0;FR.?kdM<ad%]Actr]ACUq
4Gp.m>*qEZN3iF:mD-@sue(>%*L:Y+5k7N!Q\%[T;-P-e`0kH+i[k1OU_3]A-^l[\hh1>M2Pr
Vj/[gYm%1/oOU['eapo3#q/H?Q<W\mX\6]AqK#<TS^\\5!QWIVd[6*5"X"9sp9.j5Uk4)SeZc
a#eq!.:CR:5B?Ng^@)kekhs$?SaE9p]A"u)j'`o\SK+D=?*GF9bdab15hb,ZmH,lqSt&hJi=[
;0BjLpgT5ql6r!"M2Qa^Ton.2/+]A,9_n8bN60J0nnh4B0oBOi8XqKhI:c:Z+SH?87bK=`sq-
TdKV3_6j2iF#u%#W.0qNG0:'on3O6>oZrlUZsa+E<YYj\*ms?c@W&(,O`1+JT5r:M(:2Y=*R
Pn`t&&)i=H&jgp^pA2p1t2.#`h`l:Ye."5P4Gp^d#S+15cV#V1nX%2rYkXt0G"$SRD@@9rsF
Re,A_M,:U0FH`p1LA+9?;^[lg^Cs-\-6:1M,bD!1l3$#m-LbX",:dBu=#8=(#=c(O8B\R3gt
WLGdDCtP]A\s:1-2cjbVWU]A4-q>S`*G%CW^MZ`<3FHdT=889L_K\[]AGd6GeT+?.ShJ*;89`Ya
rb@@Fkp=L0sqPMo[+@U?T3RRB^&B\AiC2SsTE)$+1.q>).k+8\Vp@9J1p;n#-<mo;C9_DGB@
h=J@dD^L"=uA+nPsYPs`1D2]A#;Fdp!S'n)l!9s_-jS:93.96UM_$:r<AFnC5WmWXWLbmls*a
qYh"@'/Ve&3-@?I^s;`?ph_O6r8(NB1hmr"ai(uB8&EWj&(+4?\Qd"dXFb\<:YehA_f;fE$I
'27a:)A4H]A#ZT,9Y3akf\ao<T(LH(MPe8s"94unkami8BQNK`k^]A0#j?b;[=A'LTf>EetO-O
QGORE8_sdZ*9UL5mi22,X%!#-2+.qEropP>9'RVc^VK7S@O)_-iV[9k!Tohp']AeOb_(;UCcR
bn,\12!*,7khr;slO/IaAi#\G4K%70[qIG!O=DCi/7O;Bg.qtH/NG3+gRUJQ*.+L#ME?&-`)
3RR6"5nGm>9[g<Jgo,$.sEeNGf`@8(EAbipBNYPIXCB"!peSGbQlB=k]A%:T*k-*%l&2;CBAP
A/WZ!i@H+78iS$(7caL)ZRgb_Oi*XUbQJ[PO>R.esjdd)h;.sb*J-Q-@uK7\uKiP*G.>8TRH
J&H.t2ug5mAd**aID#6NkfAAU+..J'HG"Gb'AtaWhCt'I*)!J'*b>u),p)7KSdXiN8&'N)`a
jsB1G9JW0*^dR^4)#cih&!S:INac"e>5,MNUfLrFjYB93t`7]AA$b&k=8A]Am"*l/*loEX?83G
W)^72ITi%c%[PU2klh]AmjNX.D>Jh"#%Dd1jSbBjc]APqDoc"<,r8WFaoWGFgVF&EocJ<+W<(i
P0&dhi7VE=REf&O7,FS2lj#PKVSNYr/C?+>dghC)Qmh,TA1^hcP]A#]AG$d1-9CC8>2>CaD`m_
sMj[W03s6fU$s0C>X@.Z"^p=)6XiGelAm7,YlQEI6jJi@[H$=CNYlooXUn&rRdW_ns;jGVo&
4Sh-`3A\G=9H!l/cS(S%,N2-sYc5GV5$KmiIp!kb7^[e9DY\FJ$an<.Za$2DM8'8G%:a5C[J
g\CoQNB\?1[q&PRhM:j$1c2/:sGj8[Gsa\-B@uX0Wr?&b).Zpo7-1CgFY+iimg2?b:d;[/YP
!oBqd7pF'DT#O<0S]A"f$MIp`,Q9.q\#^#;'QORS\0q\lBnL3k6Gj@sRFTITp7o"hs^f`FX.I
rTKGnK76'RKNrWWlK#TAZ#'rp&gi?rijHQs0aWnR!M%):hp%*LU*SOn*^4.QVF.k_42?<jaY
4Jilaq*b10ihJ4%;d2t?7H'_7/4Z>5HPIZK#Pa72._D+B'2MUKdk^D_S6:-VJG<G_X.NP7QT
fm<T)0HE53oIgU?TA3u%RMaNblFlAok>,*>$i0rn:tnNsd,2Ob:k:G"';FD"_4G>[mq)3JeV
#]AFTZ=b)pGh@h^s`6ZW?9SVr^O'gp@A'oX^>i%dRMa$/B#:B@636j:Qiu,^[7R0+8*,Is$*;
1RY4#tL%=s;UL44i_8ntiE.=S`VJ%@2r^13JH.XhfqHHtu)`:/H1@#CV.XuZj=0D6&QML<f^
ZCA.`X<PZIjg-p84MtXlQ=j9Ir]AfP,F>a"Eg^2eq_)j!C974i->.nudVH'3laAGIfUE[DN;W
PiLbp$!1l[M[2?)JXAdfNHJEm.Le`1\"9=]APXMN,:a9Sacs67KZh4E@dhhhTN8K[D_h$.7Kt
^OX\kSR2=Oo)1qL;.\=>$258!rml*P/)rT%W':g-qhGkmIE.IJn6[P!G7Q$b"QK?E9-!tqIL
6QJiMU#ZV`MJ\eZCGPXerr5DbC:7E-mFK'B.'P3e5$8q"?qJrZ@:\+"FF<jS*sclXJoqYK2=
5Y5`OUNiV"rb4^&ED=`ORMR*9\q@f$p.&2.0^U7_g/"\G^&iBL37)F!AAG>NXm2u=h]ACD]A.V
;`H/f?3?5p.SfdqDG[LF(=mR+MRpbe0B'%]AqORa)N@KY/-Pm%Dk\l#Rbt(nh>Fa"=]A]AhN@hi
@`;s%q,@K,L42q6,:AeX_W~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="636" height="361"/>
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
<![CDATA[723900,990600,990600,952500,1028700,990600,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[1104900,2819400,4229100,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O>
<![CDATA[拜访对象]]></O>
<PrivilegeControl/>
<Expand leftParentDefault="false" left="C2"/>
</C>
<C c="2" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="拜访对象"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[日期]]></CNAME>
<Compare op="0">
<Parameter>
<Attributes name="day"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Compare>
</Condition>
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
<![CDATA[len($$$) = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[无]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="1">
<O>
<![CDATA[联系人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="联系人"/>
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
<![CDATA[len($$$) = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[无]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" leftParentDefault="false" left="C2"/>
</C>
<C c="1" r="3" s="1">
<O>
<![CDATA[主要事宜]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="主要事宜"/>
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
<![CDATA[len($$$) = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[无]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" leftParentDefault="false" left="C2"/>
</C>
<C c="1" r="4" s="1">
<O>
<![CDATA[拜访时间]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="拜访时间"/>
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
<![CDATA[len($$$) = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[无]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" leftParentDefault="false" left="C2"/>
</C>
<C c="1" r="5" s="2">
<O>
<![CDATA[地址]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地址"/>
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
<![CDATA[len($$$) = 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ValueHighlightAction">
<O>
<![CDATA[无]]></O>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0" leftParentDefault="false" left="C2"/>
</C>
<C c="6" r="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="8" r="13">
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
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-1"/>
<Background name="ColorBackground" color="-13421671"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-1"/>
<Background name="ColorBackground" color="-13421671"/>
<Border>
<Bottom style="1" color="-1"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="384" width="636" height="361"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="report1"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="636" height="745"/>
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
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="3031f921-eb32-4504-ac31-b229b322cd9f"/>
</TemplateIdAttMark>
</Form>
