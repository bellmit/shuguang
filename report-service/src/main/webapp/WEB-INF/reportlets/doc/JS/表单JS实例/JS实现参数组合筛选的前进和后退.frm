<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="DIQU"/>
<O>
<![CDATA[华东]]></O>
</Parameter>
<Parameter>
<Attributes name="SALES"/>
<O>
<![CDATA[孙林]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 销量
WHERE 地区 = '${DIQU}'
AND 销售员 = '${SALES}']]></Query>
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
<MobileBookMark useBookMark="false" bookMarkName="form" frozen="false"/>
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
<ShowBookmarks showBookmarks="true"/>
<NorthAttr/>
<North class="com.fr.form.ui.container.WParameterLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[window.str = [["华东","孙林"]A,["华北","王伟"]A]A
window.index = 0;]]></Content>
</JavaScript>
</Listener>
<WidgetName name="para"/>
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
<Background name="ColorBackground"/>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var para1 = str[index]A[0]A;
var para2 = str[index]A[1]A;
this.options.form.getWidgetByName("DIQU").setValue(para1);
this.options.form.getWidgetByName("SALES").setValue(para2);
index = index + 1;
_g().parameterCommit();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button1"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="button1" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[forward]]></Text>
</InnerWidget>
<BoundsAttr x="749" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var para1 = str[index-2]A[0]A;
var para2 = str[index-2]A[1]A;
this.options.form.getWidgetByName("DIQU").setValue(para1);
this.options.form.getWidgetByName("SALES").setValue(para2);
index = index - 1;
_g().parameterCommit();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="button0" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[back]]></Text>
</InnerWidget>
<BoundsAttr x="639" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[//赋予初值
this.options.form.getWidgetByName("DIQU").setValue("华北");
this.options.form.getWidgetByName("SALES").setValue("王伟");
//参数提交
_g().parameterCommit();]]></Content>
</JavaScript>
</Listener>
<WidgetName name="clear"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="button0" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[reset]]></Text>
</InnerWidget>
<BoundsAttr x="532" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.parameter.FormSubmitButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[index = index + 1;
var para1 = this.options.form.getWidgetByName("DIQU").getValue();
var para2 = this.options.form.getWidgetByName("SALES").getValue();
str[index-1]A = [para1,para2]A;]]></Content>
</JavaScript>
</Listener>
<WidgetName name="Search"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="Search" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[查询]]></Text>
<Hotkeys>
<![CDATA[enter]]></Hotkeys>
</InnerWidget>
<BoundsAttr x="424" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ComboBox">
<WidgetName name="SALES"/>
<LabelName name="SALES:"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="SALES" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Dictionary class="com.fr.data.impl.DatabaseDictionary">
<FormulaDictAttr kiName="销售员" viName="销售员"/>
<DBDictAttr tableName="销量" schemaName="" ki="-1" vi="-1" kiName="销售员" viName="销售员"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
</Dictionary>
<widgetValue>
<O>
<![CDATA[孙林]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="307" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="LabelSALES"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="LabelSALES" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[SALES:]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="227" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ComboBox">
<WidgetName name="DIQU"/>
<LabelName name="DIQU:"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="DIQU" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Dictionary class="com.fr.data.impl.DatabaseDictionary">
<FormulaDictAttr kiName="地区" viName="地区"/>
<DBDictAttr tableName="销量" schemaName="" ki="-1" vi="-1" kiName="地区" viName="地区"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
</Dictionary>
<widgetValue>
<O>
<![CDATA[华东]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="130" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="LabelDIQU"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[DIQU:]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="50" y="25" width="80" height="21"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="DIQU"/>
<Widget widgetName="SALES"/>
<Widget widgetName="Search"/>
<Widget widgetName="clear"/>
<Widget widgetName="button0"/>
<Widget widgetName="button1"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<Display display="true"/>
<DelayDisplayContent delay="true"/>
<UseParamsTemplate use="true"/>
<Position position="0"/>
<Design_Width design_width="960"/>
<NameTagModified>
<TagModified tag="DIQU" modified="true"/>
<TagModified tag="SALES" modified="true"/>
</NameTagModified>
<WidgetNameTagMap>
<NameTag name="DIQU" tag="DIQU:"/>
<NameTag name="SALES" tag="SALES:"/>
</WidgetNameTagMap>
<ParamAttr class="com.fr.report.mobile.DefaultMobileParamStyle"/>
</North>
<Center class="com.fr.form.ui.container.WFitLayout">
<WidgetName name="body"/>
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
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WAbsoluteBodyLayout">
<WidgetName name="body"/>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[地区]]></O>
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
<![CDATA[产品类型]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[产品]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品类型"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
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
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=j@P?5=j:p`iK)6I:P8:*G`=BXoh5`/-LBktBj0N76ZCK+9?&Yhf$9/a0uC)(bZ>#9f=8s
-P5@7LS+/<po,KLNckP-?:78MD=BQfWs:kDF<TrTJ@i5O]AcPEkZVOpN`D*H/E[`nZRCg<\.U
i*!V%tf_aWS+2M?Cl<uBIYa:YXXr)d2945!=m+_Eo4!2LTN!i577T%$,-ANMBIbV>]A97Oj?n
""e5q1gn,PI@=!^VB-pTa*5n[iiD2k@;Y`b*Ul/S(Bk!e%nfj>'k&`XoEQha*I*4rJ)1JHCf
!Lpli_BNG$g442qiU8"6^B2+"7MUYAPdP0)D9MM&4;824F<@OB08,[#<mmjpYrZ:\u\/k$ed
]AjJU.6lIi[J?!`&@nb_Q(+C9cbIHfoSibB'3o9;]AZ4sFcb"K&.Zm*YYl%_8f'$G?@kg\.\T$
m)3.C+t^Qq]As46S%O:+('=L:+7`I)MllRBChkt__2<u0T'+&Q>bT^)/qP:cb+O.)mOn.Wb18
<W=R@D_H!U:g-KTh^Ep0.MVfH=S&AANINjpLLK9LI,GjC`.;j%>h@+$O-E;U%Zlk9NEYU%0]A
E5e&/kLd>ju!AK#>q&XrVbJ-``Q6TjgH2/qO]A1f^GFY+_[c1n[<t3WLJctF>uc.`'nXDjV4u
Ubh<F19b5Qt/)EB5MBqnm91m<:5[Pj#+T;U\,jEF'o[\h(]A'_'P_HSe#)'$]AT+;qt><+Xr86
=l*A[5E-[HKC^BhnG1+p8HrZ8T0FT)X04NpUWAp0Xt]AQZi@*]A2G"$;ELiJ&:IMn%qWBd_Op^
aUC,ei,;^''qpab/;s%>[aL`s@<iR3WPW='P[?>,H#k]A@n^i^3<uV0LXb4DgC<P)(l8$@k5p
iq;l7e/JUgQK`n1t?q3l5.mCb/%*57['eUEJHQ$43H>r6]As)TBZ6[pVGA$sLmR&#Vl+$amGG
=@eQ%*+Bph@+.l]AN3-U+mr$"Xcf.pCb^48[J&1Q(3JrK146sIWdt@X9,gs3goffrZY1DhKD?
TE*:e>io1./7fG,+ldXt'Dq/)6rh_83LG<C?Kg4C<5S6<\+HkYe)YYcrI'aq-RSUD2frE9KN
A/]A_0C\)L'U>]AX+/+#kAae0%$:W)0(i[6ZqJL8Q7<E7`I!HttPE5_MIZpV(T8:q!-"`C=m,J
Ndsg)E+S=s5P:-,l6u`^T=O50?r]AbF:(nGO1cLg^nBnH+:*!@PYE,&3JFsp[5BGG8Mu$Xac:
FbMZ6K'97,rnH@uZWtY!lWF6244!kjUrX8PN:P8sMR@ZB*U#"7#n\^uu)V<oX\h&O6on^fG)
R$"Y]Ans`#XfpR,rc80EYj/=Wj"U`"&'m?Y?*js+EgMdFZsO4>kGrj.eepbfG8Q?T?5ES9@DC
Ec-49Y,F&)BJcZa>hkIKrmVe/)sY7`2VapK$@.k8$sor\sh(-tq4gelsBQ]A[=_3*D$7<"j\B
;m!oCes@<,+C4lQ5u'm-?+FKQ7g6YF?,mI3L5TfSW^-j.]A/eL'`>b_,$`dZEZn.>ODP84I)-
%XRJkGr:2_bW`/%qbDd0,=LWt7nk=T=,n<NN3tN"\r\,DnEFR3aPUFRdsY&Vtc8S;j[DpnrN
nl/%ujNC2I;,2e.hfZpe?f?ZQ9]ARS+^GSbcXUSPskkRc"l)]AMO@j@M9W)5<U[m*]AOe;L`Tc.
rI,DjD65$W$oY:WYt_`0I"^Z/LdPj<)N!.0mPe-pHtnYP9ius5)=s^-tLALilSkiD9>P]ANYV
"'@&Nq`M&'ePh*T/J/$bHdqW4W/_o_XOe3:g&NHK@EA:JtM$isA?[TO1i1=K3(l^FlmKJuT5
,qtD)Is?i4b6#4T#*h[rhFauMcqhf/UmGeZS+bkMl`]A>M.m#gh*B@qb`hkR[24c*QC#@fD,u
B84Y(*523X^om_?B&911U%eba$p2)A'&;+#tf8<>=3^@RK,5O![#ZFhr>"bLM@L.sZ+^EmFq
?VruSBJ"JXK,A1H,L^f-t-YKU3oqBngc!j\Xhf?ZfQBjpAV-KHRnY<A0>5J;?*3C2K/4i:,#
2&tF2]AGU5PhDp=l'TLAB?M;#:?/O\qY[<(l0]Aph$a3m\4:]A]AUMnK^iL>YB86THG=&sLLElLF
#fhO^7iOTG/&)he<FIDACtEN81cK<Eb<!CqXH$G@_bcK:6nc*pBV8/#8GIHQdq)atNpP5d4k
W=>T!+d4q_nk_;+qUCHH8U0Gu&,E>i]A$tAJio)<M-p^B81Ufs+!D&%N'"XVK?f%uWki!)_bg
T.NYm6\2l:t6`DNA!E[=e'Ee<If]AL)#]Ak1`9NTEp[520-4#@Bq%YZAT1Bj#LkW?0e-11$t\(
3m-B$-E=)HlP&;Q:Li4CN0u.SoUg911SicW[6e%,WoTmP<:'G5gADH\Lq6FNXArkUZ`Po3M_
fu9ACEHoUM4P<9qBV[0C@-kpdhS@U-_BsD]AZS?`T_p6Q>N;0.nC24t%9nH8oZU(j\i'*4!qB
eViFS58J#Bk5Di!/EERWcY5jcESLqU5m06>X?BN9u<WEuQ(5#1UFB$sGkQe#?hO*D,1X(F;I
Hr/L$=Zc<l@`-s@$WBNJ4>(Z;r'tF%fq9\[=L,3O+e,rQQ6FDZedGBETtZajQ-L+W(=7%t[q
ff/UA?Ws1,e8If)1_Df(L+^.i.H#As&TVU._&>*>oe$^8-,rX,Rd&TP7SYL=S7lW/TG+.clF
#.lVr1Nu*$q%0$DiLFCepOo\i1QXZmM':%0(d!)=H@L<u>#\m$SP]A"V:]A"oXQP:4X#A=IUY?
,X>ujN20WQ<[@AgO`>`PN,j>4_REINB%L[-YMN5Vs-qG`*@E@TdBumPLR^D$!4O"61O"!'dc
&UI337bV=(NG9&C1]A`X,!\L>Iq?&*Y36Ztt]AK?)-FhguWAEUS[DG%`TqM7JiW]APY#aNjO2Gf
83B"#2q$Y\BBlB!0Va-"$XfE6A?J;%^F'%7$ALAXh@?r3OrH8!_0RKWhsV7XG$C8WN>.jB1\
gOC@gOAg-&W@A\#]A?%=>]A%T:2%`0-#b0t(at1-CA&F4$l`X_]A5(J;n<QWabQY%N,05tnN"TD
G-JkQD_*g1!V*,_jmEogGObp["LEPpPR^d\n9P(XQ>9[k2([%_u##_:5L!n6VoFU$B]AALc!j
^G4;;\4*UBp@5fcI$h+\hlGiUJY07H3O`-Z!hd9??78QP[-[Q%[,oX%&05i>%EXo-M'dqG_V
UT:tB(j)L1m&iu>=rmbU,sd;+a,3&sH9@.PdOM6a9+K>O*8L9E^Ym(oR4C0^Z>:2IH-@JVO/
XWD!E80a=FUnsJB;X^Gu=bbS!A)ZOT&l^&8$1"tDRpF'qh.O5KB=_e>bD':r90^=`9Q:k]AL8
ctSL#U7:VSo2A+`^Wk6k[j;g&XU&l'`;T)?*sZhF`RE'>Z'GkXG^>$l%^!2\MeuaO$_;4d)1
lPn_2cUKB7lTkJJg;9E@:@8p]A(:GVk?h/uXj,a%mH_V?XQZf@^\aqS0C'Pj*fa-J,qZS6S+U
Zu/aS%rBd%+R_tl[a9Xc).ncaQ?s=)B!CRhe7d[ma'sDHfo^I1iXq/)>ipaR$9br9BTSLiK^
Gm,EN/!,um9ch%Q2UR403G\(_D;[R9>+,12(K$o(O`CbfNf'Q%;bUsalY8]A-sN"7)\Hf?H&]A
b>]Aq]A-Bb?@F*OGQJp9$[U[uDiaUFmH7DS8I1B!Gnf"'I.H)e*Om,2o<VW]A?6?RO%&jGE/+4<
MF(@\9OXY5\Fj&lle^:p/KMr9p%a&>Imch_OM*.PpLd9U$rW1`eX:q;]AUL(o<T#ouQj3KZr/
VE0R>c4Ph?c45.a(h*RS9;3]A2IW%qWkMk[duV">#[e9<:Y7N%uEE&SA7ThAi?85fY#`I4>\P
?F[V$8OmadsfH8*-.p;,af/nd3rid2j#kPKFg8%I;h8>\/E3;WHlgo10fOmKOWnN?/ZU&O=<
/@ObcJ0/3_A>-tu:R*@:XIb[e8pG):C=NP^\.*ECiA-MB)]A(chTVAd>,$-d7KW@1iiq]ATuq?
YF$SLo/t_I`(5L.@duVD3t.f+RA[[@PoK;@/7"YoYsGZqCqqiR9Y"WO5.VRDKU6"4)3sVLXb
;5U^<1uG0.Joq952G(X/1*+;bEWP]ApeA(aS-J`)mL*:KPocW1oPn;Ldhc-RX6u$Bh4GnnF%%
Q[hJA6GhY92WL2t`I[do0re!c3f6+_KEBp>Y3JYss@IiP1)oY!Q2fd=KD%sM'9fb?U.TT-^1
h>O#-<m[hM=?FQZr,cYlpHuJo^s6=FBgAOE]Ak<Re)_pj^PS,Bfq'^(%H>DLSR>FmZ+>)<4L+
O*0kP4GRqrgmjcTrumG<%EM9unsT*_u,q*&5+[`.Q;aW@n5QYqX`7]AGeVcP/jhl-Iaf[Ch7,
)!6l@9007X^P5*=1=A6T`e7m-h"pZ".NpRG:;+6j/P$u,N4.="<52$(6.:r+Kc67-o#Bc"cJ
VBQBi4>U1ebHQnl'EC<>sUh8_-GVla8n+d,eO4&-@J_oqn^\9Y#&`s'd2tiA>ZP)-M7([D&=
'qtjAogSGh;IqdnKesAC-nKKZfdF&NF,>G"Q8k_S5-_oG2R)-r]A)>X;4rCjd/U+Ta''e0#0R
nu1dSW6jJ?%XTDa@?=$Lb---q>Biu*gHjh[eWP`fW,UNj*J:1HeR%Q.LaF]ACO'Ng=mb]APqr$
_miONa='FqUE]ACr&Ofl_GGTmiU*X@)6q\h&/lYTiE=pp&LAZoA%Y(P]Ao,3Sh."%2^67djb^M
?9e<:6HW[Bb>gp9]AkI4cC]A+FPSYtj>it:CMrLeAYlb3B:B&]A<8h6)11*P\YO+,drf8(_p33=
UP7Wa#':AquB*_!IXR$?uCUA(H>nJ]Aup*D]AP==C4'UW%D_qGULI>[@&g9RHj:^R;NiKO@@5Q
tQcJJWMK#Q&n%I<W7&B8L&BJ9PQPRqlpYsI//QW>i".$R,hT$;l43tlr_>#OB'3/=<.gX*q/
?N:N":)+S$XX/>_8pW:_D?+j<*d*@,_6(XlDm?*o:=$HeLSNVf2D]AcgDgE!YO0$T$=Xad#p?
;,JKCN:2T;jr6j*?OJs5XqRK#<sdpTg[(U1DeOU86>/C8XLF^Y#`(t65(XdAC$O/Ib?"8f+*
Z7GI.r6!#SE10<(Mgq[N*R#e[SH1ZSrF]AK]A;:kY</(01*LQ6'V\D^\bQ9F+jCLum.=J5_jSc
pNm4RHO-6KY\U\\bcVL.Rum4O2p81oInrOB)?#WT"aXXP.Xa`K9\'U\H_0"Z\80f!`YNS%5A
fK&Ru3n'5CA>_:7>T[):[!B390Gi)!,7N_ncg&@8qH(-s7"V>>pkf@fQE=[m&k*83"+!_\i[
BU2pMh._N([?3\'Q(DLG_'>Y^2AeY`5)Och_3jFQjlm@mI`[[%]Aj!6F%=q5d#@/=-SJ#;$%#
';Kq<lb#CZ]AkA]AMS&'('(kGZDZZgQdFE;%/o"'BGE7(PJ'+N_jO1O=V_[+0\T^Gla?Plk"'I
__B2fBrMO0[EOZUJT^#)(*G:m"AGoieKgf;SLZcLY-;lIaKlEbR.,UoFjR$k>$1N0=TZi^&.
<G[QP5[8FR'=Ol'!HV)YQg$\1O2VqLsG1D!bc"qKA,IXJ/V6gBPk/4U3gh<?.5/=K@2gVpE0
^Ja$<0P\#Fh2DJkcg\cj2*8F3M>9l32Uj(kQh?\'l,I5np[6]AZW:NPHL_"A^>_u'0\.)kATD
Q?2TB%Gd:=$W@:T]ADTRDOeC;e&c%C<D/!0M?DFg9f>GN]A.s>QIA'ZU_NK+ZMEGcX4+X@48e#
FMfe3HS`kJ"s"Bt."P%j==$)&WX'FAQ&_A)a":VRa(!a@P:7^6G*gP5ol@qUkJM=V%oRL4;\
6#\A.0='1.2lM:CXYmZD.!-T2@:`8'!,l)uN[9&R'`G,hG&qUjb8<)FNag4MPRVc-->Zl,*O
Fmg*D"GN%+n_n508KEHQm:e0#6u6)b?Me]A,P)p#*XBZB"GIM*!UT"8#3p5(>OnW^nEf:.If/
inTKW!c)=uWLfkU)_NHM#hMd,[k"$:k)`<%@SC@X+8G$i&`u"#<?kS<KX'Z_KC&JbC"9.IK4
_69[98bq=133iI`69M@DY6nhKYWQYXVNktX=o\Rr?-UHRttrGO+j?B!"q8,d>(WQr4@\!Bq`
Tb,2E&)A$nCjE"8_=I't738.QpAKgOho.5W;S>/3Zg75=o</^e]Aac[Uk6ce9r]AM6C1d<&<5S
$``00W]A9fT]A;3JOJ>8Hc0-UlVmD-EV?tpTW"Dg#s")cK$NJ='p;02;g473aP_ErJC\_!ihrp
?_lOrcLjfIH)j_SV9H#jb=4^HWaWKTg<`Jr_ooE65?q[o('E5!;S(Y^sd$,!#cNJoPY2G@_&
FQOR,g]AU@%!!8IRJ:Mnf=4Z2\>rO)n0cTcrCS?SbP!QCMpnY%VUpHWb?I_97.k&Gs!H![uJa
BWW9Ti#C)aFX'X3Xus?-=7#HI"^SDm`#5Pa9LTH46LAU?BF<n`gr1).s1tQWOV"(MDh%C&%X
u9<T=7V#iM.=FJNRHc@n&+cW(XEP/Gm9Z?ZKQ)TVP^&X4cF2Z@.aWL,k\$#-$Kgt`CSM2()T
7FF1S_P^g>5,]AfZ]A9AUN`,W1Dn@jT4r.O.W_3O4\c`..Jr'sT2h/BF_1r]AMA_U7'`Z/R9D>?
]AI\AmagEO3S$'_QELZ<\S*`D"^eQ?\>U<*!8l)(0VDqBr^=\HnQOF5Q;rH^[8bZacgc>Nf4;
>>JINo5+lb@+g;+0Buk%UDgT*o8d@91;NFT3:lLcOhlHZTP]A<nJ>"]A!3a0QQh[b_2ugth78]A
:0oC2q5FZ4(+"l"L-eG;BMIYYo9"-Jh3S$?)U>dhr^Lo)EBRuMULGGiX@1"dY*b#Ynr,=5Hr
qJoRblShubZ>e[HR"<t^8n_WFH<s!D<%1Gr+he!LM+XLZ]A_?Jbjf="gWoQ^r[A"5[]A'#?:Cs
`54>#F.JoHO,=OQ`qZ/.DG=-gQEaJN>B14H`n2MB4OtARlu,X_#GPB-/7IsL`tHlIfc&cO9.
J8^(62CG$<LFT)l`s0mfgNE4,;0FLSe_pVUD!tOHYt;(,[s!:Bae>_b1X)gdp%8g7M$&]AfoM
<EA\YrF(CUd$K3[Xl]Ab_$-hI&K`lN4M$`2u,O+/*u;@K@UQr=fSPq)c$ir%>SGCIU)O)Llq+
lBjT7XPA;\-E'S'E;dpaS34CG2`R;r%?eXRAgk`33R(a4QI3nKai4=7?,$`[s"d4":0`7-<r
rH&^GqccDTD8/-`drTb7->6c3/1lh*QnR[:,Tr&l<SlWhHLf6q"P(TMoJdW*Zg/LLgg]AQYtk
HtJnuFlO"e6H%*=TK5RSGf.(`#:L>8C_XJL9<GV0!:R#S4UAD,i-V??=Pud=2LpnmCb&E;U1
<fnPl:Mm#CIhP80jDGSom^+F`R(,[*A7Q5h#iIo%UieOn&T99p3oH%CRD54`^qoHHV7ZVY4p
35,2FI*;oUYOEK)d/k'"16$mIl'OY#F3%9N$5'e^H%NHTe=As"MM+>!["3m`qA?C&Yg+m"[P
$oF@21:>=o2-INo!e@A^\8)m4`LE#!!~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[地区]]></O>
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
<![CDATA[产品类型]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[产品]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品类型"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
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
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94d;P?7Gf;<h8`Zo[2A3X]AM2!0L/UPU,s8X*#7e3n?UJBMm/H#T@5H^*MtlD-\oL/Z\BOUF
;oT!ldL@U8A\m=;6u2&k!?D637I(,K]ACAl[j/F#9W:0"+S*REprtP4nh,.H-a%Y2h)??46,]A
3Dkg.-)='K`j<#f"PY)Vs*WDP;OAF*:NgETkJ#@F]AI/HFmnQlND5+3NifAB_rEP?k]ATC5ies
((Rrm(7a<45uCbrTWrm^:aE6Q9*7a++HT68H6tVT68!;5ApJPB%tl45hjVoSD!!!-\m5O5Pr
CLIIS%pMlYcW`T@:D8`&`2!U5bI*4Rmm66Wl)Qg4G".N]A>==d%.-."m=G=1RGP0M7gp%srVo
B&\bTSsMk[WHGPFV\iLaR+L/mh-p3eb/-TV]AOe17L.gUo67;)s`b?YVC:]AMsbt?=C>@Eu^Ha
c3XjU[skCs39!"N@*:(d0JtC$XBf4[\ha[hL/0?hJ5#GGArMbAs)kk'9\8`iddb4f6+/+#a!
#]A`2euG)(]Adc>gj$g,h\m86`:-$E\<o,W\!$qeUfQ,<PSQ!NXf2%sj3P:6sg5Vq6/JB7Kkr!
0h&aB^:'*1o;a</I'XDA)MYtH'Vk7-dV6TKcZONbn=&O",qH5;h*k8KrO(XY,c2Q(3d2/VYL
jOf^^o</p<F;;9;#2L:EA>)k:ilBY&f4pTbg$0>q)mO#5BU&Tn;<"#;-=A?bhB3%.`7B;VcX
AX_9<[!t9m%PPEXp5U"e4Jf+KLc1*.7!>_*QU,jdm-2=[qij_dM'iBmrSRa\4l"J,\54BS3"
;3IM"^Re$s=$\/023gr!$eVEP`kOI`-ecia,Q5=7BtWCN*c//6P>fa=4q;^EQ=G[&Jf4mdS3
s?CG`*^[jNL`5:g(`m)GW]A1Muu8Q4)]A2op!\;W;7u3;Xgn,XDt@5/JX51VKh^&1bGFJ%):s5
&rih;5NqgaTWJK+YWq:YN\MCZobr(Kdf$<fL@_\C^Mob/@\M^mPT5B,kNo`)_BQ&Cs+S?WaB
/hLqqqJPYFPE<:kunr38t?+"pY;b%bY@+fK'qYI(bc\?XLpJ";1PBO#b0JT3%R9td$=[5E%h
>eok:n\YJa`%B'FX'l<`-1S%PL9df#3l9Kan[U*0c4tq$>_b<:S0P;*/`,o&N]Ab:Q?"09?bs
")`CG*g^7P&\D<AiV#7-rW1:f&6C9Vc[@]ATT_LJ9#qDqG6qNZBA^i6VUHEp85gRT>?DCU+W?
51c8cdh)Wk2J,*-PKNS8\HNl;.?UH?p-G4Y1(UD^%eQKmumfhj59hb[+mQ`t7?f3'ed&a5u#
?cUZ\^o-9^t9SJ]AKXY7HZ&o=:jqS[YR1-D_@:C9:N?Yl`T3S0a9J"$8rjC?m',D>.!@V5Kq?
;`?RcYl(40bJHgV.IO?]A<BDM@;[I?.2S*#T_sqIj]Ace5IU-7f6,(1#BY&UaYQ:Ki3heS_>To
DrL@6*]AFVIR,MhJO)E7$4IicggXVuX#F3aY1qs0X5?q[;YEM0[0Rn>:NT8of53p09^\2c:r3
>elI?Jq-\kLXQ@/-L3!Ah<Jgsk+g^3`b^JWrr:QYrVqiC,pF/3i$h8A*kC?HfM&b]A76PMF9Q
Sdbjde'.pk&1IdSUd1AdHZ_)RH\kF(B%udJPi]Aao4hNe5lqZQV7@Dg;Of6_dE*[k&YI1s>a1
0@ip?.d'`i*?<(<k\S"Xt6"lMp+mda^+?XlQ<gCK6sH?3C2/N/gGA&(SC4L&T!tMmhVE0k!a
JF7)9!!Mt1+s$=&KedS>,!=#05X;V099aDf!h$Uo0J<14f&O_I>K.]A7#;/uCO^IDtgq&-Y]A$
`M%0OOVggk2LVV8DTLkJ;5*Ju>R8MB\T6n(f1JuW!aX)!9+:cHQr*0MQr317Z*FWOkEO<-aU
)d'n)k`LQu`H[/)nj;DCM5`#8pB?G31s![F[b4f#1g[IdA2@^U.YsIJ$4q'DA5[(mUpdYkn]A
AcZZH:!FF+Kda?^I\9_?kddS@o:=u8(@.<l5lFuD"bq-&R>j;oTKS/e/m7,\l?1(r?Ab0/0$
6HZnh0_R6X5A]A?a&\g`7+6,fB?R^S0(F9^WIjo[jrp=2mRo#@$u`5$g;5^=*sp;r06f#)2R.
UTfD/os5DUsQcr&DRh,\VnkL3[jJs3$9RUEY*ZA@30h1=2[qENe+cuDq$a\?1:?cc9+g0qX[
[gL$Bb1^8;Z!5?kE>u[$.-OX4@G$W143C]A(l"@,b[@Yq+'gVa.Z;(Jk2OkBtbfChG2P14/!]A
/`?Mfn(C/buAk@%NoMeT@=*4hTt44!AZV>QXm%S[0kq0S;r"`#Rikg!DiNB@HFJG&%$CRqM-
<T/DBT&93]Ag3f2-4Xd,2*#\3)R0I^_8B1m;>qEfg1@6Z,)RC%Sq;&lK`6+0L@?>rSmD;rpJH
-WO@KpBAGkH:%+nbNtXjq]A=Vqk?2VDBrLc']Ab]A(\%GfbIU=@\ZqP3+d3[H1G?K<SCGM($7W6
JQ!l`<-]A(Cp6IK*5)$:cJu7s]Af82!G).EAKtDUU71.&:)pgAso^^Hk8[!Il-,Tf"pKk^T?HT
e/g0L*nui>-=I0e4F"oK2LKn%X-qltqA^ihQ>*'nadm4hcGD^dQCEcW3k'qiiKU)AW)e/+TV
H8bY=:JuAi-hJj)#GY%7tg0NujBO/@P8o6hs32A'`9fT.*[u<M>GQO2TMMA70i[k*l.I#isi
@3o?WXS1V49G@K\odTSLr4<chhpl%DR#BoBjX;9p?q.80UhrhBVeN+@e<-1*aOi$aI[-\daC
9D6HRY'`*VZ]A&4eDfi>Z516B%9iMe%XBotB8KNcX/3di1Sqtu(EHM'BbgZL^5h=H+K:-4c!Z
:l#rTdY'n/f#BGf5(co9bLT_$X>S+37%^A*GR\n^q-5FT8HX0?.iqtspK"0.sPc(ai,U@=mt
-YGkj^d`+!>Y)/rDUk00n\poD)(ESR(^S)qkZ.jn3aCRebN2b0<mY$K2b8iX?]A`M5j[Pi`#M
.ip>AY%18eERb8b.Vp_i!Rfj!%G$\#ZWiJUH+`[u1+c27cfYr)=_dlBAP-&]A6W+XpVL?W%oB
3hME34&BYCjpjiKDgL`mg`5Q6Mpa4e#FG11t6$h;$\nmP71Q$i-dOZSHSTO"*XA!PaM.e!+(
D)N_:trbfc1"O+79muHg@bp9fB:6#Y0m&Y%=s7JhgbFJ^aZg=57^iC<0q5XhAc<n]Af?J;<8<
f!h%u)p<P+gU`Vt<RO>9nP5-RgaCE:&7>h`kMbYFf@A`k!(r/:)U]A@cVs?tu]AQ+fXRA/8KHp
:9;7Z,;mqh3j3@^q(gcrDlPLrb*+ZS"pS>0<tjg*\U?o]AH]AFCbYWfk]AaYFjkrr@g7DCG+O=j
sRKI>M/O3WGsXg6(9L=C)S\n/V^n*(N3B=)U56>Dq.t1"sXr(";dmS\!!fl<QGlWjC!8L,%9
dBY-tG`5'q567BoZ#:W#`%cM>8P'3<]A",pi,cS<mf[r1tR\^ddVCCp['D_\A[5dO=:)Iq_37
:n^=VjhKCEn2,<9WR"q[1!A\KM7bZ#N@t\"ju>jR_nd-eI,'Ri;lHlc/2*ncGG7+ZG4bF)Ia
]AQ,d>d\+0).ZkNS-b7n:@(%LU)mQD:E<0#i\+Xc#b&e.unF[_'XMatqhj@\a<S@WYQ/F?OM*
2E>k/L,r-VU<8sq.UZh`q#FH.Xg<eOjWqnU[gr/=W',1+[>L3Lj]A\i24$(0QA+&[H@KP'4>c
&4a"ub\TVB:"q"?>d-,+n"r4sC3j4T%cSQ">%pQ]AZDN4iq\_H=+Dc59e\QK>A!-mOE*]Abd$A
"eMX,C8pPm=[2&4d!aC(ROKoBpA$*#)-;h31XPI:OaCHbF3+?s0?qFo:7a/dgoKeBE7%E$Q`
t]A1;rUqs&9cmWKgT!$r-."`/<5X(AHc$@mE*!K8R'&CqQE'N_RX,#\Em^;C'a+,p#?$Q6]A5a
P;T%k3]AXr!t^\g8\^aMa/0KS7Wuk-",joM3+MBI:1p4Zj9>#N'1Pgr.mpWEh]A7\jQTX;kpIf
7r0,=B?lUPg'L@4Lo&3n'dgUWDBKK3c>l^FGOU3KnT\?smk8IS`XVT.>qX;=kI+g@RT-8"Xl
U$D*A/$DMhNWR&"[G/:.2:K=%e<+XB%uY<';U5C0_^ncGU0T]Ah/'iQpe=ic?.+;(1V9S_1Hu
R7@(X(MF;H11sYBC9@E07REMp.qNW>*BjR9s_\,mJq\(Sj[<`E<KO`Q^"I%fpKcF&\>gNp,Q
%kMAr(lQ24*Q,&YZ^Q@H'C]A&/OQLP\T[6IX;5W%.=Nu#)m?]AfZqt=S("V;3YkB]A005N)0o`k
_)e;oN3H#;k%'0N&cI?6Ig8P(<"2;.bRW@Kk[:nl-@(0"Wt\aF1B;]AB7)6A<G<OS0.^OYD@=
7m99>;mOAWR9qfi%:./[?+iBkeq5r'-V]A'riT'liiSa&f99#0Q32eV2e7S8'qb$V7_c%%:0%
^WSX/*BR8Mf8"2Wr@T;Q]A$4+H^%FE@_`tiY8+:^'M:^Qq/acMJfXp'q=Dfh[`23pLZM[$>OL
nK^Z:S)dRWXa#C))ArVsDp@NXmih@`\UEX'K0b(<?DjVcBp)90e\YJc&+!4L/2?36"[qem<-
;CPXUT6+."il9S$m0+jJ+d:3D_res%c*0Am>H,&VPi8lm<^b6hYXUW_.B_u=hJcX`H&?kN^Q
]AdqiNT\ZP>bZMh#m%@\1q>OceKV+/olVmoeR)*_=+D;uL-d<FJnQh>DY$+4WNAp<mS,gGWff
W)CG;;Bhm5[G87A\+:(j<nV:2Hd.`@foki8B(3ORN%nC'Jua96$_$?:p@S.7k[(0uoXqH,(8
C-rM`tJYN>a"bhD><ga5Zn4BUA&?pYlZXW<4]A.p<E@IcG3A$7+tFHa5.PF0"rb38u>T4&@f;
Gq[\=_YUa`E,5j9sL"\F*4cq;.7sj\reL3WpnJ=0'1LA!<ld8;tF3=h)%PoW5#_eB[mYk_1#
ee[kQi7hh:Kg%W*ucj'[JRd@+-oP:bO$5"8d?4oM<=I^GEnp))n4JhhZqG5lW,J&Cdq?UT@i
>2a\L+RXV_G$nmI2VV.*j-X63&KlHXU8<B!#u[>'YJAV`C>gK)F>)ql$D#fGI/ZoJ97M$^>M
kpIQWPSn%2JqC%qo=pBrl3ZNXr_e#RfsTYEqV>Nq5dpO$]A269?)jP2c(JS0:31C6ASW*\Toa
IEF5HqY%4qrVl`Y!o(%m=&EG&9qEot[Z!Uq9LXU(9b-VXrr?B4i,p'0h$MItmrhO5cE:G/ut
X,:duPSU,)'3'cDqWJb-*OXn&7l4=6OKC"V:R'T@FjXFEI]AQc^4h!K:M:7uUfEAtp)R2fd$F
l91pST(W'5Q*'%4UPE!WXdG/'S]A:TUcFb-RZj+#p5tS*I^r#b[eelDDg8$R.?S8JYp]A`19jX
a\$9>M&g%Bs^GmLM"1V6j,%LjF^)YhL,PI.6t9prpLp=P.`Keilgdeaco'#>b0`RhlPL&B6'
';1R*4k+qQ;DY?i2<FrG^l!%j;XWat#@-5/]A-#I&&HI[@/V6h`e[T)T>bX?E^+,B+^365bpi
@0R8R]A+6N]Af%G:/N!5`0bMHI%>`\QF+(h(]A&rcCcX\Q?]A\WM*J<4)7X53pQO^;08P.Y6i1E=
#AL-''7IuF4YI@GW<3+FoXpZ"dKT<_((a#"BCYK18.#"29;?&i6%`1!u'dV;I@507X4?EFXg
+0puCmp0%:!>Yb2\$FiMN1Kr`bq7eO33oiU$d7np)FNMkfimI5nD44?T%?Ud/abT&C#BYS[e
L!e^&5G8h!iUe"6)&gs>Rc%XXaDr!F!qmN5"%NBTU%+'n?[j(TOe;cc^R7Rq-caF-8a1lKW2
8f^*)*7!N&P6(fKVe\F2NDiIfkKR68#f:'`BF'FffBQ]As7JJMd9un[4%\`#J@=ls[3H$/DJY
>/H8S+[*2nYqc,5N>,H**QXDVPpE^D_?7g6#XFomc74\)b)*O<;[B:;^*tc>&t+oJb,<hb_n
KNepVY2]Al,T/H(85,1eMShFQ:[PEEa"Grr+dnec'g-rX0Y`Ub/CKuPQNU]AM)3jDVdF%t_9>W
6I4\iEd!)_>pprjm"?nP:>I%jXihtJuEN:7;Q:P+,GIcn^:B/Q\#HQi3`]A`-mPOImG/cLX=b
k/:B=XJAWfd+j`,BN!,_G+ZbT($dI#Ilrq]ATJ2TKu.M;hM#Dc8r9:O/e*4/j$F2/kO1gYh-5
i;>]A]APP\\c4(GHhIYIa%Fg#VNg2e,WEHnN`TuiD0H88Pq4BGXN$l2^[&IYFYIBBKB(s5W>\M
3MD/c]A.kc;&5:G*$FR'!hoTAV^>7EG)aQhKg:OOV@hrLUBe?aO4e9DlX=t3)D]AEHG5fQ's-5
`COn^9ORo`2Dkr72Vl:AGPC'Go'35;'!g.-YcrJLOCpCQi2eYTSS?3h`hUr#goJM`>HX/,se
+C?sAW!AY$@ipHL#]A.hX*f85Z.cLAe4iA-<YT%E'ZJWL@^,tnoP`*M\LkG:FjdZfVke/23k.
A0OWHCdNPcs$m`/'(GlT;h5Z__g,L'afr<,&bdM73t&b20M!b<B=q,tu7a_p.^lL#!/Y`YJq
YAG]A]A>9G7bS"8mQD1Ri)0Ln$mJ/kHpk[*M?-?X[+YrYcuo\g;prsRII\`Z5'CQ]A_;-iF6g)\
GBQ@"/M;4sSG,$$UTUg7k+e4c$3C&Q-]AV]A9BN$+<.%SQckZ$R:,"C(6<PFX4;k*TlQTU$@R6
"rSi@]A&4V!!4"W]A32<dp]ABGc,`Kd]Ae/9EJ%sQtLQD#%D;"+f)He[MU8TdGlSQ;C%aES9T$W#
XT+F@<7r2<?34FA4mJg.,SN<1i\6ZeRC&re&-_&d;mK4?hYem#0-jG5h+JR<gO"H7&L(K?84
%:0A]AHp"A!QSAWhDOU@E1Y[:dltjnUYH4M%pc>iu63*BUX;DkBV'+T(il^l8O')2*c,_"ZSB
P,E(#!N#$<\+l>:%Z53:N/dMjW8?`e*=?<A#M770N.`UY!6g49]AYfr5@Z5$57nV-G=7/&Mhd
.RnF;K`Jb9qb<F\65/)nNY/Wf[W\Ac&`0j7)sP/0`.!S<rFg%!r9jpjJU%]A'm(j%)fh%]AGA-
R%DSV2ij?b0C.;&s:MPoKqMtUKK`H>X%>VI#<^%06OR`2oV:qedI(RtC18V/g-q$_>-k&ME,
*h;<0K+e1>X_R3!JQ[8K8n@LHYrGY?u.6#8l>(]A&G"Rr+O&!o?j$@WW1A;<![KP29[*HT6q0
MS(r@NC%t#\G39XDB!-i&S_O]AZ797RDrjA0^RMrXDZAE?(c='s=*FYc'0-op&ee5$V*I]Au[(
H.=X8389R\>Dm_I?SW+(:aG!]A<HQsgG"Ll8"7&e*4[`\.CZ#'uHX"7OEFID-l"!"`M5E<6:3
ROZ8M1GU.TA#uoMi%o2Z,uD9'UK@$(S(i^m),!^JIB,*]AB"Q5Yo^k3<_\cE*:i!9c)YN330*
k%L<Z\bI)?$RqhsFAkKK49,KUGUPp]A-p-8SX$=0`+X.?mP"k#oAP(sWnJftbXPcfJqBSuR31
j/\?D$-Q.Cd#rC^+,`mo7Oof+r!^*Uj[T=(-$mfJD_Xb:D8<PBWP?Ve.pX+W\1jJ\U:^C227
(ia?MBDQI5nXcF.;.+[=C7V#WJQ*$G-@(U$,1Mao@!HP.4O?N69^__$0--1W<2hE3:;<DEi]A
JV-Vul=nlt9)"`_q`2G1@ml;_$f#9F3s:9B<(`S5V#_o<*nrMHj(=H.?3qMWCMACYr/<'HUH
[LD?227Nfmc+?rT!8[F#SFO.8(1"qab&JMiaKLY=5sd&":8(!\+d*gD9E8TN%S';(ucS-pn>
TZ2<67!e%Enn=j=K%gN+_gALpY5gE9=e0XlD-MjZX'=SH673GD`l]A,[-W$b<e)Jp\P(T9<nJ
-^FK;g-pRNcpKkC#X:Ym?ZVZln_d%?i?CB+#G=5Yda,;Q/s.W\sI:C3WYinqo`6/4V61pki<
16D[_5M(4kO<.-opkk!.o,:Knf\!\'0--g3t[1n06oro4WZHi\s!XSXj4PJ[@S[.""q~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="29" y="19" width="496" height="239"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
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
<TemplateIdAttMark TemplateId="d8886a18-8a6b-4262-ab8d-d9579dd6ffb3"/>
</TemplateIdAttMark>
</Form>
