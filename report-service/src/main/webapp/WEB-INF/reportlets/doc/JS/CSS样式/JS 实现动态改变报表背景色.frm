<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="地区"/>
<O>
<![CDATA[华东]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select * from 销量 where 地区='${地区}']]></Query>
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
<Background name="ColorBackground"/>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    $(".content-container").css("background-color", "LavenderBlush");
}, 100);]]></Content>
</JavaScript>
</Listener>
<WidgetName name="5"/>
<LabelName name="背景色切换按钮："/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[淡紫红]]></Text>
</InnerWidget>
<BoundsAttr x="910" y="25" width="46" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    $(".content-container").css("background-color", "BlanchedAlmond");
}, 100);]]></Content>
</JavaScript>
</Listener>
<WidgetName name="4"/>
<LabelName name="背景色切换按钮："/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[白杏色]]></Text>
</InnerWidget>
<BoundsAttr x="855" y="25" width="46" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    $(".content-container").css("background-color", "PaleGreen");
}, 100);]]></Content>
</JavaScript>
</Listener>
<WidgetName name="3"/>
<LabelName name="背景色切换按钮："/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[弱绿色]]></Text>
</InnerWidget>
<BoundsAttr x="801" y="25" width="46" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    $(".content-container").css("background-color", "Azure");
}, 100);]]></Content>
</JavaScript>
</Listener>
<WidgetName name="2"/>
<LabelName name="背景色切换按钮："/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[蔚蓝色]]></Text>
</InnerWidget>
<BoundsAttr x="748" y="25" width="46" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="label0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[背景色切换:]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="609" y="25" width="84" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    $(".content-container").css("background-color", "White");
}, 100);]]></Content>
</JavaScript>
</Listener>
<WidgetName name="1"/>
<LabelName name="背景色切换按钮："/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[默认]]></Text>
</InnerWidget>
<BoundsAttr x="693" y="25" width="46" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.parameter.FormSubmitButton">
<WidgetName name="Search"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[查询]]></Text>
<Hotkeys>
<![CDATA[enter]]></Hotkeys>
</InnerWidget>
<BoundsAttr x="195" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ComboBox">
<WidgetName name="地区"/>
<LabelName name="地区:"/>
<WidgetAttr description="">
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
<BoundsAttr x="81" y="25" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Label地区"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[地区:]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="1" y="25" width="80" height="21"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="地区"/>
<Widget widgetName="Search"/>
<Widget widgetName="1"/>
<Widget widgetName="2"/>
<Widget widgetName="3"/>
<Widget widgetName="4"/>
<Widget widgetName="5"/>
</MobileWidgetList>
<Display display="true"/>
<DelayDisplayContent delay="true"/>
<UseParamsTemplate use="true"/>
<Position position="0"/>
<Design_Width design_width="960"/>
<NameTagModified>
<TagModified tag="地区" modified="true"/>
<TagModified tag="button0" modified="true"/>
</NameTagModified>
<WidgetNameTagMap>
<NameTag name="地区" tag="地区:"/>
<NameTag name="button0" tag="背景色切换按钮："/>
</WidgetNameTagMap>
<ParamAttr class="com.fr.report.mobile.DefaultMobileParamStyle"/>
</North>
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
<![CDATA[723900,723900,1844702,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[3022600,3098800,2743200,3556000,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="4" rs="2" s="0">
<O>
<![CDATA[地区销售概况]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" cs="2" s="1">
<O t="BiasTextPainter">
<IsBackSlash value="false"/>
<![CDATA[产品|销售员|地区]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="1"/>
</C>
<C c="3" r="2" s="2">
<O>
<![CDATA[销售总量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
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
<C c="0" r="4" cs="2" s="2">
<O>
<![CDATA[总计：]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SUM(C4)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SUM(D4)]]></Attributes>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="120"/>
<Background name="NullBackground"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
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
<![CDATA[m<j1aPLm6d!0O^Ge68OQ67l2Q'kiM_&4gUXOL^6(#IIM4)8[@9,XJP1X+6'?!?ipZ<.pnQ#m
j1"$<.V36jQ]AqR6lEL+Fuet0A)k;H7b!JGYdeqp\QgdraNXqHgS54[FV\8hKn<g%k!#(<kS_
<!">Clm+_gGkgT\7g5pLoX8X=;1V4.t*5O]A)r-Ek@eT%JFq7?sT*3%KKLS*Y`>i^9[s0\I`B
Xkatq%n,)%Hj!_hco/!/p_d/#"e]AjgCM50>!!kPbg?5!JLt6II/.[T+=iO]AFnFbS`!>*)CI>
S6X3c#nl6<8!po^['T/s2'.8>RjK1bsMKfgDP6%Ooe7$K'29"r(0n^YLn66u4qbci*%jr"Y]A
YB>V!,TSkVjlcCrfG?SOB!Jto"Wne[=Dc/I>l!49HO3Y0Rd$K4c1WnYe[J-%e;=f=`%bGP>+
+.=;RCTg0gGNHa/Csj5FQYS3O<uEq>8u[e>C7laKa"<07NXpI"ho#e&bLiHAK&]Ak2kqFDo()
?U@.[25J).Bg87FRGXhj;leDlm\9kKskZ+aX-bk!d\X4=Wl49*R@D41Oc8d"'/k_&I6Y?o-r
n/eF!;+K]A,<[""?&oIjL>VB.'^r1]Ar;C]AF4_-rbV@Z\*5^0&W9)-hpc]AR@8ZL`V[d(*WpE3[
b'1B6Yl-X0m/Qt/`+QPYYqN8:[`OOLK+OL`b]AcRd"KPDB`en,39o6A`@!953tUFkX[ndKh(H
GY'S"1_psr2W4*fnYH'rr4*/bTiIX`I<-&HLrhW^kToqle"P[kk.,XW\o82>o4P+'RqGqEh0
,[0K@m;PpYh-Jg_WWW4\m=J)d&M4QPjl</>!<2?r:Q+.973/9#fAPnV0h-B0'r8X$f3i`u3c
Xot1UB*1Ck71l9*E<EV=7PF,C*Uqsm:SWkeo^*>(%:VFtuV\nf5bi((!9P.lVgjahU0&JG.^
2J8il$EAGj>+jV:I(o0+8cqSQLfe8<"gq>252jd/=lY_J$I+5iJ@3;[V<r5WB.M#X[et5M@A
Fan4Oh&#on$gXadf+-)`R5P4FINm\m\)j"0VIZ-Z<2YN0U@%RHXZN"6hJIU)lhA:#Qnr;7ft
B:aVP%H]A&:B_!4pO/?ja%0*Ok$Usa%\PB;r@[I@=;JOm77nSe<).0bO,-r"S-K84cO_LB=Ff
aEq43X;%nV/!WgH.p[Qf%LAlX09%67-Q^Ts-NI"/pKAgEEQFp8&iYPeT2-<tp9m+hH;HO950
>:nC?p4*UtI'8"WN"uYRM[bgnSb]AEA2M.gP?]AM[('$9f'XSu%$CG96?:o/'ueCgB-_gFCQ+7
HS/I-#::Toc6oc3AnV/26qUUr92jQ0Ybh)(8J:eTr,<gF><X60*;X?>+BBJ5@*KaB2rN;C7A
l8K-83t!1n2B*LHH=Qu[T_4cqD(1C(@f^2<&;>PL+W44[d62"Hs[_2Mg-_<^MonS.;(r@b%D
MuW+%c+:V*O$:t)15@jb]A'!sO[MdYF,,77;Dtqp?^ZV,V/*"!s,2!5$X8M.\!C#GA>(Gp&1Z
O+;OMGp&QQRma#,\NYOL:/i2Y@kF+1A2!+uFR.4</Igqf75ohe#N*0D2i0`Pd.<Bo.$@4Ci)
=[HZg&?)#A5_&h20l*nm<,D<Ll04SG&=_EMIX.IVi6[SuA%Pp2$)VZSjD`/B;oZC4TE>+g,r
>r=Ec=$,'7171*lb[?t$9g-ag9,5K_B)/=2D0&6*+B/``M/sMabtrca"njq0T8&>quXKB<s=
slCVt2HX2d,T"FE\`3U0;eNU@o[>(D(sfm%`nad9gX%Q.;9a;+HLC=#F-JXsPH&AE2%bW]A^B
!8(m$fa0.53@e/`lP(cElVU_EUc"#)4&X39-[0Rl7ER2hldc,RW6j-?GE]A_#_kWcGh]A?EEpJ
FG\)*,/0]A%NDBLTN?CZ_HB?h7`@FRS=W>j!k(7G^(sG&ij%^a%L7A)d+N%mb\,%68t0'8#BM
abOKb.(7e:5m^@6G>NiHeh0c:$IdK>6F`T2QEa)H$$nR-SDGh*c6toU?\Hco]A,(qDWfafS$]A
=O<^N4\V$)O'5`C1(3Hn.aR-;H=pB*-.Mh#(FBT=gq^Cf\1HJQu2Nd;o^*"+K!Tt]AuPnkC]A1
kg4.%B>k/IePDFR)b^2$?r0,=\spU?*jO#EfQ]AGlZqKn[!X\$HXZWH7_ob,&o80KdEr0Y&Kq
+uqkbgj!R[=(2SDb,_Apm]A@V&_`<#UnKT:ZD>h?KD"::N*P6*0H+'I)'$h+'hIGD,p[G"bBN
HflZ)`a"n3-qK)r5Y[S$pFo@1pHa]A*F<%qBuXa.D0,*(#2.V'emm1Dp=f]AWFm,6)P)ne!/NF
pVa!M;J/@mWe*Y)?V.>9ocVSVLiVF\d8\]Aj\#g;s`+609#mZqFe]Ag&B4[t`UlgC<G6m2e]Au(
p+j:^P>u+*&&V2;9C&dgWC>7!J99-I:p_tk@,2?h"@Rl%P066"@d(0I#2ORd'/AB/0Fs.R)[
BX5jR';')?!nh;5"*)t"L.=rl@^Gkart^o+W,qc,="'s@,00r]A6a&g"Q9mV+)F\$WAWm>;e6
.P2&7KJ-(=\DF5-9slkEAG/tC1^lp=RbqbDqj!qQ8GD;1(^!/\T9XcIQuhSR19j3Wec+/3=1
Ob(."r^o+JQ`k%'IZffO<R<I\m%^0R%?6hpWVLDDrH-+0CLG9IUd<ZugBKi9=X_Ub;VA12B@
3iV._e^.KF-$L[fBdoHKILQ7Wu\)fbCO%_0/&5iV[GJ)\;qo!XD@FkgB*n3F!LNY!/C4Y%H0
Es\fGU!A-eu+uOH/,J*11SUY3.)&D?VD#L]A[YUm@l:J8f;&pO$1=b'#EY+SDCcJP$%VM5rJ>
ijhC(uTZq&fU"0)1a+f?$=2>@)@.h'bYED<VJeHac;Mt(;6N]Al9N1l`0/Vm)X<8e6f3*$PSF
$$04)1:2qZS6jhq%#d.[j1D)7'b6!NlbdG(1:)b3Hc+=`k@C34>>!(H;Rc]A]AjKKWr]AeG(Gge
(TE(o0fX^IM0oMNl;e.E*Aqr@UW^=>fpgIF/F\5PD<Or3WY-J)VD%j1R5;?%VkAPCR&tYpo&
m#dQ;cZ`Kt$n:C4M<1-jrR<8"0dDX2W/`XkRr1jSQpd;/,6tu/"&_D_X1_Zn0:K\O0hMb&2r
rjW090mIn]AQo(l9i'1MTEr%"-h\UH-^U-&s83kB8C*ZEC5qnWkAnG.ZN84'fM\>;K*I;GO#m
8FMfd6_>05HUG@d/[edtc]A@RK9#6-Q-Hl[!T_QaLD8'(eM`J%1H>*3*UH!W*CM";l-=F*FYB
M"X-Tdb\j/?Qf)s<N2?-H!o)'(%?Fc)j`(,q3=tSDFLD7,C1`-d7m"n/)<B8J$FnS]A\Z)[U]A
_fadHfWCNbPb/cDlT=Y2?2ul.GO)</_>hRE+KrffN``dual!VZe?skRiOIc-X85>dj$8]ANmr
Imo!uIlFR8ISo43qA"^'CL\9PHM8M!mY%_]A+$Z\<JVo4!?C74R6)ZZnJRBP<L/.mRJ5"qrO8
?%#&3b<3%-TpH->%6>oVgt\u.NTSrddC%K#7Kg,#Rb2KjO+CQj.N%43eHAu6dt;4cIRc@Mg^
$S@js0d58,sPrlE=,*oR3uCu^S%I=_go1?aoKS7u;_<Y05-<&ODjkQ.19)Ud20<a+krg-Qcj
M5V5>MHuT)A<CCj5e,lMqKBbeA8JON:=@f@k)Od`%aE+JgcMoS$4>/1o`uTOZej*62^ipX[#
n3g44UnB5\rL-*mC"Dc*cAS!@!(T;lDLT+uS$]Aq;^V11qe1kY\mhLX07cUWc6><jIEleFoHL
>6LS=e3Rb/Q_L9YcQ5OnuF5";k`jAnAc/h/->V\U/4e^]AUp9<ESM1;slB;h=SUgq5]A?9G.bG
^BfU;leruZ`,i"V39k`ju,*_BG"SEa&;?hI=SQ6EkVB*:?Ea7'?]A%gRoY:!1o<s:iW1J1^ii
'Ka0;AYMY*^sf?8oa3T+(\G%.^2V`RYS4QNomOlhDGOo;rJoB$mM$7\U1o]A%s*6r%h:CV!cH
LRQTMAO'0)_c@O&Mj*KY/ZU8\BB=jq!s8qC*J*&P$K/'Rc7cb^7E1j^fD4rlaij*1U`3ciUI
^\]A;6dNU87D'dU4>5SY)l:J/Q(%BDqC>(Ri;)Pl37PPVYBgDIFfG!5=/dV#AVUGm7l_Q3Cb:
dBE4NU"o"PZ3DSNK_ML)OU`^/2?VZ`BMLMhOMF^Ue2Dqpre`)uteg76FZp[5>#[/au6K*!_#
D]A]A-i)]AO"^B_tFA2[6aik'F'hV;4.pnT%3rsZ*ee_8j+;=sLIZ/#FFor:?]A@AG%'4X:JMEds
i<U6bDIoLYa*[,^IqH+9j1&M=j/1BQUlmAHS'dHA"VN,$Pt]A?Pb52Zs9f\lI46+[L[>@jD+q
Ogo85=E0ZDrS[0=Y2]AR%r#m0lIV7:)Bp5JY^r5b+WkCk6kD&Tm%o2CI;_n8MRrif/iYhj)['
UN7?itK"C%G5$X/%,99!3=[mBsMG;kY:-]A3sUI^)GRH+_^"%\sVmlG(d[,e\$7RB)/bh25gs
8hl'+nrG]AZ\6,&F;LA=n]ANjaSPg'K-+X?\6X=d*KuEUN-#iqK:."gE@7nqXno68h57>F%esp
KoP0hOo%D>5pE=75e\9/M/@,Oe2'[U#]A^**CGSh5/s<e5;MRUotLo/K:=#PcU7gO'j-RM'?7
)BC3)8!Fu>W1e:);c37Y+"eK$CYQo=YaLVnk?;M/`I"]A9E+:%<(Dgj!WBM&8O!QsO^ua`./U
L9PF*Vq/SM5Nrf4'9!4j-@l=Nb1i3hHh#\(`RGTXjL,S6)h3m4<MJNQLlt.OFSDM1@.7\'9[
i[Z.V[qN!64?D+nq;F]ASZQX%KnP5g>N4LDTJ!_29=dPOC'_Rjb>)>>+n,5'273&G>iB^%1@,
P%Z@D]A6pF34116tVWErVb7gNkkm*R+e;i4*7A:>fQ-PP/O*^iN"jYbkVceQFJi8BdQU'\-4*
D@JdM9aI+'an/cd?:iYWf<.^rLs[ogU=Ec.dC!rFmH0*A9:$h:)uYqVtHfJVb&8`3B9L-.m[
)&YX<)'Nbn\=H'"o17uUo:g)\.gD28uAq/e]A>p>n]AVj;\-0E<M>^R</cTO9rUF3-MEXr8Gic
;H2p,DCoG`=l<)#Zs'#+nOnKf2068?7@>*T(Y!Iu.VU"J1V1Dq_@;`f<340XMF</m;o)'pPp
@ImZNl1+43<+Fq2l^K$@(6Kk#LK#_(7-;rpV3%!CUWnL/*W\NQk+!aWGFeSJQu`2.ioHCRbb
#,QVmp!D5)e]AB&)44R_Ap,a`OqA*Oo.A,o--iQHO#+U=S=kontk^M)o6i<I>IJ)Y<I!H.p,!
M&SZjLOhd%0\@0JaBlP-'SO)HEEV2;,X?3%n#^I+j%bY.8n!r4u\l^1mg7JV\^H-,=8DFO=g
#Ol=eZ/nKH3*Nf`GZlSS)^Kda?Ij'IXYO@66Y86i_5kW91n03OE+m/m6]AOI4$&@7Q=tiB(M%
+KTE7m&!JaD9R!/.":NA&ijN"-QWtiK6oa8m-9RdrcD2$=_]Ae;^NPK?C]A\4CMOWk;+A/W?5H
lA4Duj#kQHd[AV7TKK]A,Tk[/BR3$LhBX0dEiegC^A[2R=:T^H\?q#M,k;And2V$jbQV0;sO?
"<81`X)"hF/lS3^*XWYT_rlFEn3R8V/f;Cr8Y$mVYGThOI_Zu[u$*o1/]Af<tEOG#oUk2TERp
,HWh3aO46NenZrUr!pWM?RA\:f'#m*5X34#A&Ob[KERqc"0"B-Z3sS8i*)jA]Asu)WoCO`%e8
m73rEZu>I.jI?\B3V`f#Ul:nlH#b#LtR=d]A:'LS0c:(<!nVjH1k-+l3R!=`;G3-_+BsUb4!m
k0ZP#h?fflfOPEZZ-sO"cOAXA]A&l?ailNe+L#.cY8cIWL_.pIY"K*9m2W%tW^5#T96khB/\f
c8XQabO,fpOhU)*i]A!&5F06UlO\!UG;=PQPA&>IM'@2@>OlH/Z]A(;pVa$OY*eKo4g[71:3$A
M]A#P@>9SmE*41+<3+ssYKF?aTb[[`le(c.LD+e#QL/G#t`"&4%.)nX::5ui!8pF+"Z*+X<r4
f:,Zkc/i!LKB4\[HFc(ea..FP"rXI0[XFm=1j:S3&<@fBEm5=Ue<+`]AcXrCEKMM'=QP"4m4U
oY%crNCNN([R';qqPXj22Q9`A:(XT1XT]Ag0.`PmMg"KIHnXAt.!RQ,27F:.2$#*T(CV!;@Yr
;oF2]AF"t9GbdRimKL1.)_mH/"9V?$3Z"r;XM/s;'*jR_Z7>:LnoO<%'k?c`(`m$AQ7)=BX/F
5E;njT&E"S!B_XnU3E,-#B*-g?Y,O+J*6_'SqYKT?^%EN3YD1QZa1U6rqd;j?3[M@.VNd8\L
>E3G'q+S8%B8#YpVZX1/=[VQ,eYE-H?WF=\pZVT:_,oDDg+6&cQc.VVt.;Ido#dN5+;hu8*@
kid">S\kT]AT!`K]AoY28J;l)34&dsMh5=2uCEG@Rbq3)MCa8H8fkVW/lcnir,f]AhG^1;qn2-6
ceJuHVR]AYT_G8)l%_UU9RTP^X8Y]AmqU#N1\,8'Ec0o^t$_b=Ni<P[uVD@$[)Erngc1QR;L38
-2&`_j_oi<Y><C[^^FG<Q9+/+k)-!0`7@6u5EM?V%RhBG]A.F3C6KcQ\&DKqpN&b^4-Fk's``
NIA+6:k/1D-+&YOc4/`MJ/4d;9E7=)r[q-UogoL#44.-J0s^/6Gs0[_sQS7srpQD&>2Koa.A
M>3K]A,-7<sQ.A823CX#pP2MMY_(bEC1PWQHe*.L"Ga<B:RZ]AP>t=6d_;$QZC7dK;brO((Q&"
_2\sr/Lht!l((4*nO8[Dp1c`IdR8,7G%48:1,PnP6RqO;XCH:6sm1P2D5AMGCi)lRb\0+Q%c
"SIQ]A,:)aAS[hf1fq?e/gRG#o6!8h'=1<_</u`D5b*.1XXM-Ju?,nNOFg:BKm:;KT/Aq*O0/
%d'-)]A2J4KIWGSbJnXk/"cl0^9sb8q&q0"T`pP/]AYS>"di(6RXEG#[SWd!F1Mb=4!e?"&+l,
`7+@YR[)iJb4HCWrDp+$.5^CE\A,iMWO]AUiFUmlpAKeXL]A:[O6e'J`%uId,jjg6?q6'2'ol/
^7+%UYP8:70bo?,"8H1;[2D`u#*8OrSDC?V#Thekhm_KJF6H(Tp2q<=/s-^@qAu?(BD,a9G$
8"BOlRL$a5X>n/0A#+s4,MF1VV:\NVp:.eHD)<0'tqT<`@hq.M)Vb7mne!1bm-c>F31#FQq-
^^]AT$KS+k/'J^tGgF%C;WfK?mkS6`a3daYfCW/SDt1OpA;f`Pki"]A_D2<8'Y9\[mNgq;5W?e
3)APTNO/"l(hoU-\=ss7XZ2k9Zd5>a#+e!@0<)^FLTsps[*38715N6(NSsCB:k.KP6Pn4\1R
P6NYb@:HV"G3m/XX79$q`mrmfOWPL\d(`4UVVfi4Jl>@Z-._Kh%7Y?bRN;\AIFkE_hHD+Xt'
5hXtuscOoC"LP]A&kPK'*p""]An@GcW^:_"K(]An2YrVR'rncJ]A#FK7e`!E$ZJG9#V?cs]ArO,">
B)*ieE2=r^'Qcu*+KZsO9X3aq2OnC.)2mp.\1_+D*`'s%LWr[&O3)!BF"Ni3(dDjr'N;k[:Q
(0c`*oOjC3`3/sP0rYVoYG\*a-nQ;m[NU%&:GIWL##EP,4nEL/^9l\3gqK"hsPhC4Dck#fSA
1CrQ9CGR!KlaBY"cNrlH#PSc714egE.^C-LiF0"M3:^+jRBFHVk-t7a]A&$B+Z*6"N^]ALX2^d
(F/aneD\#=!3$a,6ugS7?VOD'.[Ch^rBWeH[d:C]App[LPW>#h99aI?qWP]A6>2&c_gR<jEq]AA
eXWUu[ZV4YlS`N`V^g6:R*7_3eaNFI5o%<26B>#:j7bL'?3Y!j'T;9QKJh^k/SE&ujhC[$0k
&HjoX#B`^Jp3#OcpuRRQ,TC)p16:Q,;ta^9VD^"+OT(Q=?"de%%=`)Z;8#77Xr:d^:>5A6/"
,Y"u:F)j4\`!dtnCA9`sYr<CK<H%R86P7!+#>_&c\`AQ)P97&d$BJSEnHarr#Sc*4j"]AdsCq
="LI8oVJh1;9V?qD3k0Sl(+IVP"6u`5^^*jGUead+7/@O:oe#NhZX:1`E!ELASLHJR^q+W;r
d4E5"tDT_%]A9L?CZJpJUhTBUd;H&_rW.p$Q"F^g(.K[C>Zl6baoD#"!gntL=GMsjJ"-"GKcE
'I*0:N'g@0b]AY2!afYsHc:0^rS?``O`C7WS35D:5W4*ZD2SfYm;8V`,%>18aBS/\MhTG%=f(
,J98L*`^QMf!,;2WV]A^f$8M@9"9<7Cc%3[,s`P0c&F`=T3U99hJS2=]A,2gZ2o`RHV+AUFSgn
?Y;t\TkC15pIQuU4eh@C[:b8f/Hie>a9Ib!b@3=<1qlH&*,3>:L^e/e-Rn8[2f`T`u7TLq[=
g)lCQXm1u!!k<!G%l]A0-^bY2N13ct['d'Ql^<O*cmqD/Hftn$a(q6p%?qV\qf2NY'XJAoS*X
(VqlGXA(:$(N.W<GQI=*:%?2k]A*O2iu@89AKsnYYN=V8%ecFeT%T0X&No,lhSW.]A2O_s0k@S
<ru[[F.8)%u3NVRfb/bPC1jg<sR`gY.\XiiIWW^ruL)B#6n2FFD;3Y^L<R[h8cO(q#iQqD9M
Kf_)gp9C0HNBZpddt.n*htE_Coc?oaRb437\2'B!ggUG"YZN<?%N.OraEu+g4AgOXcKjdIcf
dWLX)LPPBd3Z_3-@`&[IhGUfkgSZf_f.cs[3'$[lNZ6d>7M=[BTdUnfHOr'OlFN)CPB[bmb@
+/Ib:1smNH#3t60C4)WuSp]A[<4S6.X]A[_g:<6=5mfCQ[rG8HMmURr'7)_3k^StJb*9%Eb-na
!-`B>e3rU?VYk,hT53L>C@[<Wl0=*^kAqFjWEAmO]Al1e8&,^Q'J)RZM^0,ToIj-p<pfX#k2P
$9%Ct:4gB'734H1Qb!%e;2gZeK=:0Bq,eiNDS)O!G#Cgu_V!B#!'aNN"F9^Nj'G@]AkRD5+0[
%M0<qU&5OXQ4:X6=,,eehrfa//bjK3$:d*'fVi<EsU"CRLBuUqDrA&rB8Rq._q>gjahg[cLQ
I'[-`SKS=QH?eQLa%RfE8Q_WbHQ[Q(`P+e9Jp+a"3R*UMct4s_\8$XH;k5<m06H5t=Ac*`D6
6a!#!gEmo]A8"Qn_g03lP_c6;/2\q75FJG+a:*?$i4s"@_8A7*#:K(-UqB((/A7$^.EQt@"-:
Xf8CK$JZiBh6RQ=8tDW7odY"YiJ06n*Cb*^K"O1d13X/F^g\cGI;Kf_^f`]A\nu'!O_8M2W2U
Rf+Zmpm67Pkj04iDTIj'hh?LsAK*bt._i+?pG\G^YIf%c[8"s%"-@WPNG'8iZ%V3cmp[hqmr
EWZrh3C*Lj2CMfO8EMpqU'<d]AOFL,YEF_U\T;SnTY!)dR"=O]A%/1pfMorPdinJ@9cRQ6;LpH
`-F@=%XST73>97r9iIm"LLZE?Dp=eHk-O>n4F#!Niq*..aA\kqK_Wab?a#9#*nLp;3Ugg]AdI
ar%9E1^H:`n:X>F"O2K(r8Cq608usdC$):KhNQko>PX2Q5rin-oQeTcL2&GP@_a2H:;Afd&V
>-KcgKt,Y(?)RZ;).sTBjLGe[iAdS1U1PP!_p@n',@_HQh`,mltcNh-SinROctJn^>8"ampU
bGAu6>NnE<b)*$kZ8$]AY(D+Hl52#PMsOMm5Fa(J+WY[dWmO_"u16Y@rp*/ZRY=Wu%'ff,4Y+
'aa_id<nn.IqM#&An[23%:AsaNE]ATl`1F7Qm[`]AQU/c,SX&<c"N9J0OdcSAS)pHj!d<rmAQ&
$Ka0o]Ak?;E,_,J,For(c=BNk>?9/`lZN1`*AE5M3Z;H>j>(rrebC_QK99,?g,HSG/qX#5Jr/
$Ro65N_C>I$-1mXng(.4;?jiH738QES_uP230$-\pH5KM$l_JDPS[Em/9Ih:=,beW`e3e+(1
GI'`J.nZADg7B1HFV/TsG;"Oh^D3eZjQSO3_b$:O?0K^SLbXU[DH$i"@\PJhhAuguA&]As.MK
mBUQ\SR"gcD`J/rcn07JEHIaFu9jGCCnm_gu77(6`Ta0s%eqFG0?qF0+bb<,7[Pi7mj,/E;S
J??Kh!0b?;SsHT_MOc0;,$)bW@@,c,@,f/^t&1._.6Ya@d(d:(-Sp0h283a\4OH#'cCf$/fF
?HJ1*ai`RY^M1^Cc$Vn/ljKjQZi1<)4)E67DZbOnep:TI3Womn/;]A?`.#KRPu`M(o*lCs.dl
j>LlENtW(MdUHR]Al=8XG6YCG]A/FC'qr^[hdpU7(pD?JU:Mt5Bfen/2pV_&hXh77V+RnlC:D$
c3>V;7C2JHPZuq-JGf_I9UdHCJ;1?65h$L9T%38Z*B"%K&LdIcn033,F`Vg>Q3o>Q8I_6f+K
hGp;fCIhlP*!;i0KJWt%,Z`7s"10=FB44Isef=TH4(6fa`WE\4sj66i6Mrpt'.5*.g$9/;nm
*r$$"^Pqo382!9O"2"q3a.IY7nl,#8<5J@[t2e/0X<H5?K0N1</H.+gn3#WPmZ\:`je?"g/a
4f2Fj+3`leQMPAf.&RRs@\clBom;AoYQ="2ZeQ-eFm[tgO(<n7/03\4?9e^JBXNoG;3Jdgbn
G'd@.q^*7AF?(<m(dh3B9Y<BX,-Rm%")JWQ%TPqaHP9#Qa%#X`m:pTI9]AE=/.>=_/H"jN'Y&
KYJ$p_GaR1QC9TXfDXaq=&Mb#^ARQP=^ja-nU4j$=i)HE]AJib+BE.Z]AQ^/"FT=H[KWf#TJNg
O52MbO=@4`2kPc<fpU".64sj3oRQD'Y)Q@]AP;k!+?$s5:<An&JX_<1F"*?fR2'F20rf>;MeQ
`T7+o`BcP1SqMTl$Z:fD+BOJj3Q,dmgKV;aUJXICs$:#UgVs*L$4idrD7"LUg*1p!mH^smS3
g#g6SjnZc.toG<96P;i2'_Fs!V]Aaof:SfnoC-=8m;Ibd/g5=aHDc5;=YSH:gh]A8fCP^Bpe(`
`'?J4FMYCe)TY(qghKTY:<hQ(LI+iG=sW0`9g)$#aW]AD)0<*9pIT;V^8D6PH6j8JBP)F7>0c
$-Jjqb:c01E^[,`6^(%O(dM6&Skg>ajHqOI4m[;8tFZMeO%=ja'0KAD?UZ*3`1GY.&0!!68'
\>sSI=7*U`VJ#\)#=1cb_Ua+m<no1P,,&?Q3!TDOC!G2+Qk?:/I8?kA5;$M'*h=-`S<BFnF9
A?7YVQpXYS!6;nBkr8Um!d2DLWuB7\D(GKJQ*o4TE(#\TLP#nR10NXrB`*:K3\q"-V2>Mgkn
H-C\![[S'0@h;hdAYL!\@fShe#6&_(X6YQg;gk/sNq&#tjPd*Y+476"JU6]A<!d%`=0@..ZiG
r!15<(H,]A#Fcb6WgoBCELA"^B;bN?rK.j6$1^o]A;+FMPYE3,8qHN\ue@\+U[Kbq3p^/Y#BPk
a^@@XDg_/=8AhCpn&5.7k<N3)[c]AjqeD%rhbc6`6sLY]AM4[ZL%I*:#>3X7ABq\\$+^,A0;[n
Apo4q[G3^`M;IS"F"VUeW\gs"@`m:?iW$sAn]ATp030=(/CH7ERGTb@iSHlqWNVW`UsLm,jl'
aE51fYZij;A>]A:7mnh4==*ET7i?p]A+ho<HcZmoJlHtd_H]A4(/ZB]Ac8L3c/QIMccE<"5#=\!$
ep+^NfQ@Z\,YfhiLZ)!uY2FboQ=UAV:T9q:D58oCW+=/Bk%8.Ycmh7%2k,&L\2W9\OnPI^ha
D'Sam[uuW1HFF*e[GA1XYQ_ZhSP2C_Co6ITNqL`4O$M/n.Hj<(_#cQ\YOL]Ag&9\B%Aq?GsPn
9&!=40`s?q:6A;u0lr9?<-gR'4Narj\;k:D2h7IMql@c-0Id6L2(s(uuk^<ri8nlFXV1nOA;
2AOG4_(Y$U8$<elC/)98*I7^?P8Z`P4<KAooSbQTP0<GZ7RT]A6hnd$[elh<1S)?`k8!)ib&_
rHH2]AgJ'#.Qpi[L+F>'\guW0:FjHq>Z80uKR/'Z7)P`_H[NKN6EL4jJDO.Gda.NC[kk]A^WHV
iST%E73YAU&IqHbqPr@(D<3@N')k'34nLZSIKHYr$a[;RWMcq;e;1f17.F(@O:V9Jgkl%m7U
\$L$urbC(jfhT/Wal!-eWfFNO\i@oUn'ZG:[HMAVh=eg;*`p+]Af$WlcIuQdp034=Eps3(-cb
WU`*<>P82SIQ'%H4.+l#2pJhH%V%muOF)Rn]Afh_`6O[oBb_rhc=f<R\5ko420qG1&ohPs0kD
IESUO80kZs\DfWslZ.,BKr?SMDCB$#Xbg5qJZdc`)R_Lm;V:k<*dG'Ek\<`QXcr5q^#T;e,`
2oo`2.hnVl"]A<'[JB9MRC8mQmFXPUENNAb_O`sog\*S!rd@%WO]A8#&!4NKeRBJU`kh1,"EfF
O;oQFrk1miVH!!tp6r!P5Cm'G.-L&iao/UQ\)C;lJUD"Z3nN;jm(<@-XQg,EO/^MVi3"e=:M
jO(1tc@5[q;Cc`<dkc'XUdEZ`IZ5$.Zh#a5SeQ'JG_qF@rGD2R6lDtH@G0fSE?7oSqt9l4`e
F\4Y8Zo/ap.2FAX"*Sc8TgY>%$=DOL0PD:1#ga!&`B46ss3_>$0Aci+T2HLT_oEo1(c1AmtL
b81?(h#I=8a8m"N2:W0gD7e+1MMmpNOMc'LA.?PL7e[Z2lgVFtC@a^]At_Z'Y7APE*bc)nIA5
*'\tX]AS4hNrM;X>tP$T6A=LJo4C`b&0JD@--j&'kNr`3DpirXJQZ<Q4d`_%B(Xl`U2IAJ<k\
jIWZR[(GkAc29$aX)b;q^XpS4WZ4>_BT","OpMZI;Tf5J`I2YF)WF]A_Sr@LaS%2,DZ(96g(a
i/He*JbkD9Qc<aj9C;!X1aY0-,+mnIi"W/5prh+W)W.>:@H5qRY;)CC3?e6+fJEI2CNml-q-
VGPDk0<O4h?<,Ss7EnpK>g")jKi"BgD$P_Ei6Cm2!Grl)NB-@kp2WI*c-Q&YW55Dbi2">Lug
1koB/<6de@RSIhbrXN6U-+9%7q[mSRn`/9decSl!,)ZOIub?>J]Aq1^:R+0%/ZRkmMRee-FOl
?cA%N&_5!$/gY@:U'fHo[5TU)1m"ueLP0Ob)2=p1nC$\'Ck>gV>)aE6t7P4-^E`i.,<'$O+_
<KjRARD.1SCgWP7I5F.[Z$$g(/!l3g4n,UDR]AYJ$Xb>02WK3"]AabI60f;&F=krH5C_'c`aoO
I@A'G\Ya6eAiEK>SYf?">[mbKJie&2rBFtTrVAa7`8/g4ph,DogAW%>rVrTdN_S#\W]AhG.9M
BjeQ0cp-We7;g,tE/]A5'S<GEc7/cSCL;fXQr01;C!0bWBIuU>"F9lg_h//e1*;%]A>1MbPiLu
$V]A`YuU3[,JY4eG/s4oC/p[M:d+'i&5rHZEqq$rAueM%?nDA6d.iS@GiGn*EhYJ[JlCFc?N'
k^e[FR>]A17f(2WCJOHHDm%R5fdeY`meB$63W,(*D/_'(CNZI1U@Xa,a#e\8^n$c*0?jd\/-\
R+.[*W@+l1DE4D;t'<XZ>e_rDYWUC(*I"nGISU!Efih3+js8*V=n(\PUAbstT[%Z1/O3j%9&
5GQW7ib"[p(/cudBYJZIYt2/nJff+,%BSf%\n,^[b"QlYdfe):f6..oFll3nC&&63lnnk<HU
kcFk>J;A%]AbK3a0<cFb&ZBE)ku"Ck3;XafBDQEr!+-Qb)<pAo'a$`^CSMOoD_aX5@tqi:btD
n*ECLK'1>O-o_)kB.RuW'l;<.6iG)*[6%V9q?NV=hcKqPJ[>NGOS2MUY:>BT8Ug]AbCG<Q"]AD
&)%?c':Rad%ZLpJ*4LphpBcc*rV'Cgbfm\(BVT:-[M'5]ArmQ+BVsiLFHa9i:2_gfNNXh#S5u
8AnQgLfQKr>O'</4D/if<!q8dU-Y'tmfI7$?QG1bB]Anp*T9"(pVtq4"#Vqjp6$FX\IbG4#g<
)-#!5??+.-E235"/hTju1o`#gU5"<B#K*%0QSjB79DXM65Tn-Bo/-lC.>R[FBiU:2.0S_+q'
MF^?L[]AcL&^aT3<-1<?t@N[2tI#BIg%N_-@g5BeeW!mH-[r.Gk2@M!7))Hfb^`aCdV7rFL<J
bk(.5m+^J!j,SRN=qSZs'[u=MN-p5_QoN8Ba8%T=)>IT5mf;6n745MX3P9o$>3i?O1?PV]Ab<
W5E_J7MlQ0jSbkZo_kGd2%iLElXDhd]AkpG-qrYt+&XGL9KtjoDrn_0FGj&)l`MLR2b-E14=/
=g,ME"L:O:cb[Jge+lO>kfr#%JRKf[Y&1?a=Y\%"P+33!K7WRR?<X9)iAd6gY:rj?gtM`j(n
V4:"t6VrHmD"j:V!6$:6>?I4?pH&5EU[p"c'[p7XCI%>8!`1oB";o9Bka94WC(uga"=V<hPs
\p=p5n97D+,Eb@K#Tt3]A<.fXVZt1+'[$aWA@u=["[uF)MaFi@5*as'&!t1rpPRf<L*p>qQp4
TTdVT-.@1H@bb%+Fonc3?Ba)&HOadB#QPdlrS)>Yda\FfB<W#LM*r-IlAu1P_.k9UH)K$u3*
ag>kl#!X,WLW[m(>SlZRa4^*SO#H\-6E"F@<hD_;0)40OS%#TDN$8)q<>nBFf(EU]A8K^)ktO
!iW\FZMZ#l_'Z+:uTUh6;j3j[XOKF5rG*GRDe"U"@,L49<h^B^DC+o/hVDk.N3+G6GRZDgG1
np=)kO#,k45gsbb-7B'P`eurXjXWgMqgR?eF2S=D\EunQ3eRVbhWZS&R&WYIZ4pD:$\ACURH
ih7e1jC:g!VQc=&/k1JnMDWr&_^WJKeAS<2hYi<M;]A5s)ED(8LLFU)!f03CI`mQd&5`kpeHf
rk'DN6+cJc,']A1E_KZb*@NPWRIa)H&3!gP=B-D_OCb]AZZP,;m<=Cd,UAWGG;s*>DJX^0-9DZ
k=:)@bP!6%sq5)j%GZ+U>@+8Jcb)A<'r9j24AZ%QllT@?1K'XWg1oU9oijYOl+m*qGd%J(+"
C.`Z_[1rXqSiB(bq*^6k0:^S&\\=QAqG,('seW!9#2?s3*JO`#P]AUCO$Lem2$G!Xrsad;5?,
G>O4;3l4njI\tEIo$s6A*&%igUd"(dc,enb)T*iq7)F.d\&l)uim%sO))tECo]At(8o2c6B\U
$0<oWL!6$t)Tafl6AdPGVGY(fZW_a\%]AYC-\)^!/IK9aLE&E8t%*%4/A:J-D8R2RbmjY0qTY
Er&mo5]A"P`Z:tTfu:fBJJ(B3CiGLh(O3Mfd#fStZ33iuA3lJoP#@qYTr#>b7^~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="270"/>
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
<![CDATA[723900,723900,1844702,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[3022600,3098800,2743200,3556000,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="4" rs="2" s="0">
<O>
<![CDATA[地区销售概况]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" cs="2" s="1">
<O t="BiasTextPainter">
<IsBackSlash value="false"/>
<![CDATA[产品|销售员|地区]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="1"/>
</C>
<C c="3" r="2" s="2">
<O>
<![CDATA[销售总量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
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
<C c="0" r="4" cs="2" s="2">
<O>
<![CDATA[总计：]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SUM(C4)]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=SUM(D4)]]></Attributes>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="120"/>
<Background name="NullBackground"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
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
<![CDATA[m<j1aPLm6d!0O^Ge68OQ67l2Q'kiM_&4gUXOL^6(#IIM4)8[@9,XJP1X+6'?!?ipZ<.pnQ#m
j1"$<.V36jQ]AqR6lEL+Fuet0A)k;H7b!JGYdeqp\QgdraNXqHgS54[FV\8hKn<g%k!#(<kS_
<!">Clm+_gGkgT\7g5pLoX8X=;1V4.t*5O]A)r-Ek@eT%JFq7?sT*3%KKLS*Y`>i^9[s0\I`B
Xkatq%n,)%Hj!_hco/!/p_d/#"e]AjgCM50>!!kPbg?5!JLt6II/.[T+=iO]AFnFbS`!>*)CI>
S6X3c#nl6<8!po^['T/s2'.8>RjK1bsMKfgDP6%Ooe7$K'29"r(0n^YLn66u4qbci*%jr"Y]A
YB>V!,TSkVjlcCrfG?SOB!Jto"Wne[=Dc/I>l!49HO3Y0Rd$K4c1WnYe[J-%e;=f=`%bGP>+
+.=;RCTg0gGNHa/Csj5FQYS3O<uEq>8u[e>C7laKa"<07NXpI"ho#e&bLiHAK&]Ak2kqFDo()
?U@.[25J).Bg87FRGXhj;leDlm\9kKskZ+aX-bk!d\X4=Wl49*R@D41Oc8d"'/k_&I6Y?o-r
n/eF!;+K]A,<[""?&oIjL>VB.'^r1]Ar;C]AF4_-rbV@Z\*5^0&W9)-hpc]AR@8ZL`V[d(*WpE3[
b'1B6Yl-X0m/Qt/`+QPYYqN8:[`OOLK+OL`b]AcRd"KPDB`en,39o6A`@!953tUFkX[ndKh(H
GY'S"1_psr2W4*fnYH'rr4*/bTiIX`I<-&HLrhW^kToqle"P[kk.,XW\o82>o4P+'RqGqEh0
,[0K@m;PpYh-Jg_WWW4\m=J)d&M4QPjl</>!<2?r:Q+.973/9#fAPnV0h-B0'r8X$f3i`u3c
Xot1UB*1Ck71l9*E<EV=7PF,C*Uqsm:SWkeo^*>(%:VFtuV\nf5bi((!9P.lVgjahU0&JG.^
2J8il$EAGj>+jV:I(o0+8cqSQLfe8<"gq>252jd/=lY_J$I+5iJ@3;[V<r5WB.M#X[et5M@A
Fan4Oh&#on$gXadf+-)`R5P4FINm\m\)j"0VIZ-Z<2YN0U@%RHXZN"6hJIU)lhA:#Qnr;7ft
B:aVP%H]A&:B_!4pO/?ja%0*Ok$Usa%\PB;r@[I@=;JOm77nSe<).0bO,-r"S-K84cO_LB=Ff
aEq43X;%nV/!WgH.p[Qf%LAlX09%67-Q^Ts-NI"/pKAgEEQFp8&iYPeT2-<tp9m+hH;HO950
>:nC?p4*UtI'8"WN"uYRM[bgnSb]AEA2M.gP?]AM[('$9f'XSu%$CG96?:o/'ueCgB-_gFCQ+7
HS/I-#::Toc6oc3AnV/26qUUr92jQ0Ybh)(8J:eTr,<gF><X60*;X?>+BBJ5@*KaB2rN;C7A
l8K-83t!1n2B*LHH=Qu[T_4cqD(1C(@f^2<&;>PL+W44[d62"Hs[_2Mg-_<^MonS.;(r@b%D
MuW+%c+:V*O$:t)15@jb]A'!sO[MdYF,,77;Dtqp?^ZV,V/*"!s,2!5$X8M.\!C#GA>(Gp&1Z
O+;OMGp&QQRma#,\NYOL:/i2Y@kF+1A2!+uFR.4</Igqf75ohe#N*0D2i0`Pd.<Bo.$@4Ci)
=[HZg&?)#A5_&h20l*nm<,D<Ll04SG&=_EMIX.IVi6[SuA%Pp2$)VZSjD`/B;oZC4TE>+g,r
>r=Ec=$,'7171*lb[?t$9g-ag9,5K_B)/=2D0&6*+B/``M/sMabtrca"njq0T8&>quXKB<s=
slCVt2HX2d,T"FE\`3U0;eNU@o[>(D(sfm%`nad9gX%Q.;9a;+HLC=#F-JXsPH&AE2%bW]A^B
!8(m$fa0.53@e/`lP(cElVU_EUc"#)4&X39-[0Rl7ER2hldc,RW6j-?GE]A_#_kWcGh]A?EEpJ
FG\)*,/0]A%NDBLTN?CZ_HB?h7`@FRS=W>j!k(7G^(sG&ij%^a%L7A)d+N%mb\,%68t0'8#BM
abOKb.(7e:5m^@6G>NiHeh0c:$IdK>6F`T2QEa)H$$nR-SDGh*c6toU?\Hco]A,(qDWfafS$]A
=O<^N4\V$)O'5`C1(3Hn.aR-;H=pB*-.Mh#(FBT=gq^Cf\1HJQu2Nd;o^*"+K!Tt]AuPnkC]A1
kg4.%B>k/IePDFR)b^2$?r0,=\spU?*jO#EfQ]AGlZqKn[!X\$HXZWH7_ob,&o80KdEr0Y&Kq
+uqkbgj!R[=(2SDb,_Apm]A@V&_`<#UnKT:ZD>h?KD"::N*P6*0H+'I)'$h+'hIGD,p[G"bBN
HflZ)`a"n3-qK)r5Y[S$pFo@1pHa]A*F<%qBuXa.D0,*(#2.V'emm1Dp=f]AWFm,6)P)ne!/NF
pVa!M;J/@mWe*Y)?V.>9ocVSVLiVF\d8\]Aj\#g;s`+609#mZqFe]Ag&B4[t`UlgC<G6m2e]Au(
p+j:^P>u+*&&V2;9C&dgWC>7!J99-I:p_tk@,2?h"@Rl%P066"@d(0I#2ORd'/AB/0Fs.R)[
BX5jR';')?!nh;5"*)t"L.=rl@^Gkart^o+W,qc,="'s@,00r]A6a&g"Q9mV+)F\$WAWm>;e6
.P2&7KJ-(=\DF5-9slkEAG/tC1^lp=RbqbDqj!qQ8GD;1(^!/\T9XcIQuhSR19j3Wec+/3=1
Ob(."r^o+JQ`k%'IZffO<R<I\m%^0R%?6hpWVLDDrH-+0CLG9IUd<ZugBKi9=X_Ub;VA12B@
3iV._e^.KF-$L[fBdoHKILQ7Wu\)fbCO%_0/&5iV[GJ)\;qo!XD@FkgB*n3F!LNY!/C4Y%H0
Es\fGU!A-eu+uOH/,J*11SUY3.)&D?VD#L]A[YUm@l:J8f;&pO$1=b'#EY+SDCcJP$%VM5rJ>
ijhC(uTZq&fU"0)1a+f?$=2>@)@.h'bYED<VJeHac;Mt(;6N]Al9N1l`0/Vm)X<8e6f3*$PSF
$$04)1:2qZS6jhq%#d.[j1D)7'b6!NlbdG(1:)b3Hc+=`k@C34>>!(H;Rc]A]AjKKWr]AeG(Gge
(TE(o0fX^IM0oMNl;e.E*Aqr@UW^=>fpgIF/F\5PD<Or3WY-J)VD%j1R5;?%VkAPCR&tYpo&
m#dQ;cZ`Kt$n:C4M<1-jrR<8"0dDX2W/`XkRr1jSQpd;/,6tu/"&_D_X1_Zn0:K\O0hMb&2r
rjW090mIn]AQo(l9i'1MTEr%"-h\UH-^U-&s83kB8C*ZEC5qnWkAnG.ZN84'fM\>;K*I;GO#m
8FMfd6_>05HUG@d/[edtc]A@RK9#6-Q-Hl[!T_QaLD8'(eM`J%1H>*3*UH!W*CM";l-=F*FYB
M"X-Tdb\j/?Qf)s<N2?-H!o)'(%?Fc)j`(,q3=tSDFLD7,C1`-d7m"n/)<B8J$FnS]A\Z)[U]A
_fadHfWCNbPb/cDlT=Y2?2ul.GO)</_>hRE+KrffN``dual!VZe?skRiOIc-X85>dj$8]ANmr
Imo!uIlFR8ISo43qA"^'CL\9PHM8M!mY%_]A+$Z\<JVo4!?C74R6)ZZnJRBP<L/.mRJ5"qrO8
?%#&3b<3%-TpH->%6>oVgt\u.NTSrddC%K#7Kg,#Rb2KjO+CQj.N%43eHAu6dt;4cIRc@Mg^
$S@js0d58,sPrlE=,*oR3uCu^S%I=_go1?aoKS7u;_<Y05-<&ODjkQ.19)Ud20<a+krg-Qcj
M5V5>MHuT)A<CCj5e,lMqKBbeA8JON:=@f@k)Od`%aE+JgcMoS$4>/1o`uTOZej*62^ipX[#
n3g44UnB5\rL-*mC"Dc*cAS!@!(T;lDLT+uS$]Aq;^V11qe1kY\mhLX07cUWc6><jIEleFoHL
>6LS=e3Rb/Q_L9YcQ5OnuF5";k`jAnAc/h/->V\U/4e^]AUp9<ESM1;slB;h=SUgq5]A?9G.bG
^BfU;leruZ`,i"V39k`ju,*_BG"SEa&;?hI=SQ6EkVB*:?Ea7'?]A%gRoY:!1o<s:iW1J1^ii
'Ka0;AYMY*^sf?8oa3T+(\G%.^2V`RYS4QNomOlhDGOo;rJoB$mM$7\U1o]A%s*6r%h:CV!cH
LRQTMAO'0)_c@O&Mj*KY/ZU8\BB=jq!s8qC*J*&P$K/'Rc7cb^7E1j^fD4rlaij*1U`3ciUI
^\]A;6dNU87D'dU4>5SY)l:J/Q(%BDqC>(Ri;)Pl37PPVYBgDIFfG!5=/dV#AVUGm7l_Q3Cb:
dBE4NU"o"PZ3DSNK_ML)OU`^/2?VZ`BMLMhOMF^Ue2Dqpre`)uteg76FZp[5>#[/au6K*!_#
D]A]A-i)]AO"^B_tFA2[6aik'F'hV;4.pnT%3rsZ*ee_8j+;=sLIZ/#FFor:?]A@AG%'4X:JMEds
i<U6bDIoLYa*[,^IqH+9j1&M=j/1BQUlmAHS'dHA"VN,$Pt]A?Pb52Zs9f\lI46+[L[>@jD+q
Ogo85=E0ZDrS[0=Y2]AR%r#m0lIV7:)Bp5JY^r5b+WkCk6kD&Tm%o2CI;_n8MRrif/iYhj)['
UN7?itK"C%G5$X/%,99!3=[mBsMG;kY:-]A3sUI^)GRH+_^"%\sVmlG(d[,e\$7RB)/bh25gs
8hl'+nrG]AZ\6,&F;LA=n]ANjaSPg'K-+X?\6X=d*KuEUN-#iqK:."gE@7nqXno68h57>F%esp
KoP0hOo%D>5pE=75e\9/M/@,Oe2'[U#]A^**CGSh5/s<e5;MRUotLo/K:=#PcU7gO'j-RM'?7
)BC3)8!Fu>W1e:);c37Y+"eK$CYQo=YaLVnk?;M/`I"]A9E+:%<(Dgj!WBM&8O!QsO^ua`./U
L9PF*Vq/SM5Nrf4'9!4j-@l=Nb1i3hHh#\(`RGTXjL,S6)h3m4<MJNQLlt.OFSDM1@.7\'9[
i[Z.V[qN!64?D+nq;F]ASZQX%KnP5g>N4LDTJ!_29=dPOC'_Rjb>)>>+n,5'273&G>iB^%1@,
P%Z@D]A6pF34116tVWErVb7gNkkm*R+e;i4*7A:>fQ-PP/O*^iN"jYbkVceQFJi8BdQU'\-4*
D@JdM9aI+'an/cd?:iYWf<.^rLs[ogU=Ec.dC!rFmH0*A9:$h:)uYqVtHfJVb&8`3B9L-.m[
)&YX<)'Nbn\=H'"o17uUo:g)\.gD28uAq/e]A>p>n]AVj;\-0E<M>^R</cTO9rUF3-MEXr8Gic
;H2p,DCoG`=l<)#Zs'#+nOnKf2068?7@>*T(Y!Iu.VU"J1V1Dq_@;`f<340XMF</m;o)'pPp
@ImZNl1+43<+Fq2l^K$@(6Kk#LK#_(7-;rpV3%!CUWnL/*W\NQk+!aWGFeSJQu`2.ioHCRbb
#,QVmp!D5)e]AB&)44R_Ap,a`OqA*Oo.A,o--iQHO#+U=S=kontk^M)o6i<I>IJ)Y<I!H.p,!
M&SZjLOhd%0\@0JaBlP-'SO)HEEV2;,X?3%n#^I+j%bY.8n!r4u\l^1mg7JV\^H-,=8DFO=g
#Ol=eZ/nKH3*Nf`GZlSS)^Kda?Ij'IXYO@66Y86i_5kW91n03OE+m/m6]AOI4$&@7Q=tiB(M%
+KTE7m&!JaD9R!/.":NA&ijN"-QWtiK6oa8m-9RdrcD2$=_]Ae;^NPK?C]A\4CMOWk;+A/W?5H
lA4Duj#kQHd[AV7TKK]A,Tk[/BR3$LhBX0dEiegC^A[2R=:T^H\?q#M,k;And2V$jbQV0;sO?
"<81`X)"hF/lS3^*XWYT_rlFEn3R8V/f;Cr8Y$mVYGThOI_Zu[u$*o1/]Af<tEOG#oUk2TERp
,HWh3aO46NenZrUr!pWM?RA\:f'#m*5X34#A&Ob[KERqc"0"B-Z3sS8i*)jA]Asu)WoCO`%e8
m73rEZu>I.jI?\B3V`f#Ul:nlH#b#LtR=d]A:'LS0c:(<!nVjH1k-+l3R!=`;G3-_+BsUb4!m
k0ZP#h?fflfOPEZZ-sO"cOAXA]A&l?ailNe+L#.cY8cIWL_.pIY"K*9m2W%tW^5#T96khB/\f
c8XQabO,fpOhU)*i]A!&5F06UlO\!UG;=PQPA&>IM'@2@>OlH/Z]A(;pVa$OY*eKo4g[71:3$A
M]A#P@>9SmE*41+<3+ssYKF?aTb[[`le(c.LD+e#QL/G#t`"&4%.)nX::5ui!8pF+"Z*+X<r4
f:,Zkc/i!LKB4\[HFc(ea..FP"rXI0[XFm=1j:S3&<@fBEm5=Ue<+`]AcXrCEKMM'=QP"4m4U
oY%crNCNN([R';qqPXj22Q9`A:(XT1XT]Ag0.`PmMg"KIHnXAt.!RQ,27F:.2$#*T(CV!;@Yr
;oF2]AF"t9GbdRimKL1.)_mH/"9V?$3Z"r;XM/s;'*jR_Z7>:LnoO<%'k?c`(`m$AQ7)=BX/F
5E;njT&E"S!B_XnU3E,-#B*-g?Y,O+J*6_'SqYKT?^%EN3YD1QZa1U6rqd;j?3[M@.VNd8\L
>E3G'q+S8%B8#YpVZX1/=[VQ,eYE-H?WF=\pZVT:_,oDDg+6&cQc.VVt.;Ido#dN5+;hu8*@
kid">S\kT]AT!`K]AoY28J;l)34&dsMh5=2uCEG@Rbq3)MCa8H8fkVW/lcnir,f]AhG^1;qn2-6
ceJuHVR]AYT_G8)l%_UU9RTP^X8Y]AmqU#N1\,8'Ec0o^t$_b=Ni<P[uVD@$[)Erngc1QR;L38
-2&`_j_oi<Y><C[^^FG<Q9+/+k)-!0`7@6u5EM?V%RhBG]A.F3C6KcQ\&DKqpN&b^4-Fk's``
NIA+6:k/1D-+&YOc4/`MJ/4d;9E7=)r[q-UogoL#44.-J0s^/6Gs0[_sQS7srpQD&>2Koa.A
M>3K]A,-7<sQ.A823CX#pP2MMY_(bEC1PWQHe*.L"Ga<B:RZ]AP>t=6d_;$QZC7dK;brO((Q&"
_2\sr/Lht!l((4*nO8[Dp1c`IdR8,7G%48:1,PnP6RqO;XCH:6sm1P2D5AMGCi)lRb\0+Q%c
"SIQ]A,:)aAS[hf1fq?e/gRG#o6!8h'=1<_</u`D5b*.1XXM-Ju?,nNOFg:BKm:;KT/Aq*O0/
%d'-)]A2J4KIWGSbJnXk/"cl0^9sb8q&q0"T`pP/]AYS>"di(6RXEG#[SWd!F1Mb=4!e?"&+l,
`7+@YR[)iJb4HCWrDp+$.5^CE\A,iMWO]AUiFUmlpAKeXL]A:[O6e'J`%uId,jjg6?q6'2'ol/
^7+%UYP8:70bo?,"8H1;[2D`u#*8OrSDC?V#Thekhm_KJF6H(Tp2q<=/s-^@qAu?(BD,a9G$
8"BOlRL$a5X>n/0A#+s4,MF1VV:\NVp:.eHD)<0'tqT<`@hq.M)Vb7mne!1bm-c>F31#FQq-
^^]AT$KS+k/'J^tGgF%C;WfK?mkS6`a3daYfCW/SDt1OpA;f`Pki"]A_D2<8'Y9\[mNgq;5W?e
3)APTNO/"l(hoU-\=ss7XZ2k9Zd5>a#+e!@0<)^FLTsps[*38715N6(NSsCB:k.KP6Pn4\1R
P6NYb@:HV"G3m/XX79$q`mrmfOWPL\d(`4UVVfi4Jl>@Z-._Kh%7Y?bRN;\AIFkE_hHD+Xt'
5hXtuscOoC"LP]A&kPK'*p""]An@GcW^:_"K(]An2YrVR'rncJ]A#FK7e`!E$ZJG9#V?cs]ArO,">
B)*ieE2=r^'Qcu*+KZsO9X3aq2OnC.)2mp.\1_+D*`'s%LWr[&O3)!BF"Ni3(dDjr'N;k[:Q
(0c`*oOjC3`3/sP0rYVoYG\*a-nQ;m[NU%&:GIWL##EP,4nEL/^9l\3gqK"hsPhC4Dck#fSA
1CrQ9CGR!KlaBY"cNrlH#PSc714egE.^C-LiF0"M3:^+jRBFHVk-t7a]A&$B+Z*6"N^]ALX2^d
(F/aneD\#=!3$a,6ugS7?VOD'.[Ch^rBWeH[d:C]App[LPW>#h99aI?qWP]A6>2&c_gR<jEq]AA
eXWUu[ZV4YlS`N`V^g6:R*7_3eaNFI5o%<26B>#:j7bL'?3Y!j'T;9QKJh^k/SE&ujhC[$0k
&HjoX#B`^Jp3#OcpuRRQ,TC)p16:Q,;ta^9VD^"+OT(Q=?"de%%=`)Z;8#77Xr:d^:>5A6/"
,Y"u:F)j4\`!dtnCA9`sYr<CK<H%R86P7!+#>_&c\`AQ)P97&d$BJSEnHarr#Sc*4j"]AdsCq
="LI8oVJh1;9V?qD3k0Sl(+IVP"6u`5^^*jGUead+7/@O:oe#NhZX:1`E!ELASLHJR^q+W;r
d4E5"tDT_%]A9L?CZJpJUhTBUd;H&_rW.p$Q"F^g(.K[C>Zl6baoD#"!gntL=GMsjJ"-"GKcE
'I*0:N'g@0b]AY2!afYsHc:0^rS?``O`C7WS35D:5W4*ZD2SfYm;8V`,%>18aBS/\MhTG%=f(
,J98L*`^QMf!,;2WV]A^f$8M@9"9<7Cc%3[,s`P0c&F`=T3U99hJS2=]A,2gZ2o`RHV+AUFSgn
?Y;t\TkC15pIQuU4eh@C[:b8f/Hie>a9Ib!b@3=<1qlH&*,3>:L^e/e-Rn8[2f`T`u7TLq[=
g)lCQXm1u!!k<!G%l]A0-^bY2N13ct['d'Ql^<O*cmqD/Hftn$a(q6p%?qV\qf2NY'XJAoS*X
(VqlGXA(:$(N.W<GQI=*:%?2k]A*O2iu@89AKsnYYN=V8%ecFeT%T0X&No,lhSW.]A2O_s0k@S
<ru[[F.8)%u3NVRfb/bPC1jg<sR`gY.\XiiIWW^ruL)B#6n2FFD;3Y^L<R[h8cO(q#iQqD9M
Kf_)gp9C0HNBZpddt.n*htE_Coc?oaRb437\2'B!ggUG"YZN<?%N.OraEu+g4AgOXcKjdIcf
dWLX)LPPBd3Z_3-@`&[IhGUfkgSZf_f.cs[3'$[lNZ6d>7M=[BTdUnfHOr'OlFN)CPB[bmb@
+/Ib:1smNH#3t60C4)WuSp]A[<4S6.X]A[_g:<6=5mfCQ[rG8HMmURr'7)_3k^StJb*9%Eb-na
!-`B>e3rU?VYk,hT53L>C@[<Wl0=*^kAqFjWEAmO]Al1e8&,^Q'J)RZM^0,ToIj-p<pfX#k2P
$9%Ct:4gB'734H1Qb!%e;2gZeK=:0Bq,eiNDS)O!G#Cgu_V!B#!'aNN"F9^Nj'G@]AkRD5+0[
%M0<qU&5OXQ4:X6=,,eehrfa//bjK3$:d*'fVi<EsU"CRLBuUqDrA&rB8Rq._q>gjahg[cLQ
I'[-`SKS=QH?eQLa%RfE8Q_WbHQ[Q(`P+e9Jp+a"3R*UMct4s_\8$XH;k5<m06H5t=Ac*`D6
6a!#!gEmo]A8"Qn_g03lP_c6;/2\q75FJG+a:*?$i4s"@_8A7*#:K(-UqB((/A7$^.EQt@"-:
Xf8CK$JZiBh6RQ=8tDW7odY"YiJ06n*Cb*^K"O1d13X/F^g\cGI;Kf_^f`]A\nu'!O_8M2W2U
Rf+Zmpm67Pkj04iDTIj'hh?LsAK*bt._i+?pG\G^YIf%c[8"s%"-@WPNG'8iZ%V3cmp[hqmr
EWZrh3C*Lj2CMfO8EMpqU'<d]AOFL,YEF_U\T;SnTY!)dR"=O]A%/1pfMorPdinJ@9cRQ6;LpH
`-F@=%XST73>97r9iIm"LLZE?Dp=eHk-O>n4F#!Niq*..aA\kqK_Wab?a#9#*nLp;3Ugg]AdI
ar%9E1^H:`n:X>F"O2K(r8Cq608usdC$):KhNQko>PX2Q5rin-oQeTcL2&GP@_a2H:;Afd&V
>-KcgKt,Y(?)RZ;).sTBjLGe[iAdS1U1PP!_p@n',@_HQh`,mltcNh-SinROctJn^>8"ampU
bGAu6>NnE<b)*$kZ8$]AY(D+Hl52#PMsOMm5Fa(J+WY[dWmO_"u16Y@rp*/ZRY=Wu%'ff,4Y+
'aa_id<nn.IqM#&An[23%:AsaNE]ATl`1F7Qm[`]AQU/c,SX&<c"N9J0OdcSAS)pHj!d<rmAQ&
$Ka0o]Ak?;E,_,J,For(c=BNk>?9/`lZN1`*AE5M3Z;H>j>(rrebC_QK99,?g,HSG/qX#5Jr/
$Ro65N_C>I$-1mXng(.4;?jiH738QES_uP230$-\pH5KM$l_JDPS[Em/9Ih:=,beW`e3e+(1
GI'`J.nZADg7B1HFV/TsG;"Oh^D3eZjQSO3_b$:O?0K^SLbXU[DH$i"@\PJhhAuguA&]As.MK
mBUQ\SR"gcD`J/rcn07JEHIaFu9jGCCnm_gu77(6`Ta0s%eqFG0?qF0+bb<,7[Pi7mj,/E;S
J??Kh!0b?;SsHT_MOc0;,$)bW@@,c,@,f/^t&1._.6Ya@d(d:(-Sp0h283a\4OH#'cCf$/fF
?HJ1*ai`RY^M1^Cc$Vn/ljKjQZi1<)4)E67DZbOnep:TI3Womn/;]A?`.#KRPu`M(o*lCs.dl
j>LlENtW(MdUHR]Al=8XG6YCG]A/FC'qr^[hdpU7(pD?JU:Mt5Bfen/2pV_&hXh77V+RnlC:D$
c3>V;7C2JHPZuq-JGf_I9UdHCJ;1?65h$L9T%38Z*B"%K&LdIcn033,F`Vg>Q3o>Q8I_6f+K
hGp;fCIhlP*!;i0KJWt%,Z`7s"10=FB44Isef=TH4(6fa`WE\4sj66i6Mrpt'.5*.g$9/;nm
*r$$"^Pqo382!9O"2"q3a.IY7nl,#8<5J@[t2e/0X<H5?K0N1</H.+gn3#WPmZ\:`je?"g/a
4f2Fj+3`leQMPAf.&RRs@\clBom;AoYQ="2ZeQ-eFm[tgO(<n7/03\4?9e^JBXNoG;3Jdgbn
G'd@.q^*7AF?(<m(dh3B9Y<BX,-Rm%")JWQ%TPqaHP9#Qa%#X`m:pTI9]AE=/.>=_/H"jN'Y&
KYJ$p_GaR1QC9TXfDXaq=&Mb#^ARQP=^ja-nU4j$=i)HE]AJib+BE.Z]AQ^/"FT=H[KWf#TJNg
O52MbO=@4`2kPc<fpU".64sj3oRQD'Y)Q@]AP;k!+?$s5:<An&JX_<1F"*?fR2'F20rf>;MeQ
`T7+o`BcP1SqMTl$Z:fD+BOJj3Q,dmgKV;aUJXICs$:#UgVs*L$4idrD7"LUg*1p!mH^smS3
g#g6SjnZc.toG<96P;i2'_Fs!V]Aaof:SfnoC-=8m;Ibd/g5=aHDc5;=YSH:gh]A8fCP^Bpe(`
`'?J4FMYCe)TY(qghKTY:<hQ(LI+iG=sW0`9g)$#aW]AD)0<*9pIT;V^8D6PH6j8JBP)F7>0c
$-Jjqb:c01E^[,`6^(%O(dM6&Skg>ajHqOI4m[;8tFZMeO%=ja'0KAD?UZ*3`1GY.&0!!68'
\>sSI=7*U`VJ#\)#=1cb_Ua+m<no1P,,&?Q3!TDOC!G2+Qk?:/I8?kA5;$M'*h=-`S<BFnF9
A?7YVQpXYS!6;nBkr8Um!d2DLWuB7\D(GKJQ*o4TE(#\TLP#nR10NXrB`*:K3\q"-V2>Mgkn
H-C\![[S'0@h;hdAYL!\@fShe#6&_(X6YQg;gk/sNq&#tjPd*Y+476"JU6]A<!d%`=0@..ZiG
r!15<(H,]A#Fcb6WgoBCELA"^B;bN?rK.j6$1^o]A;+FMPYE3,8qHN\ue@\+U[Kbq3p^/Y#BPk
a^@@XDg_/=8AhCpn&5.7k<N3)[c]AjqeD%rhbc6`6sLY]AM4[ZL%I*:#>3X7ABq\\$+^,A0;[n
Apo4q[G3^`M;IS"F"VUeW\gs"@`m:?iW$sAn]ATp030=(/CH7ERGTb@iSHlqWNVW`UsLm,jl'
aE51fYZij;A>]A:7mnh4==*ET7i?p]A+ho<HcZmoJlHtd_H]A4(/ZB]Ac8L3c/QIMccE<"5#=\!$
ep+^NfQ@Z\,YfhiLZ)!uY2FboQ=UAV:T9q:D58oCW+=/Bk%8.Ycmh7%2k,&L\2W9\OnPI^ha
D'Sam[uuW1HFF*e[GA1XYQ_ZhSP2C_Co6ITNqL`4O$M/n.Hj<(_#cQ\YOL]Ag&9\B%Aq?GsPn
9&!=40`s?q:6A;u0lr9?<-gR'4Narj\;k:D2h7IMql@c-0Id6L2(s(uuk^<ri8nlFXV1nOA;
2AOG4_(Y$U8$<elC/)98*I7^?P8Z`P4<KAooSbQTP0<GZ7RT]A6hnd$[elh<1S)?`k8!)ib&_
rHH2]AgJ'#.Qpi[L+F>'\guW0:FjHq>Z80uKR/'Z7)P`_H[NKN6EL4jJDO.Gda.NC[kk]A^WHV
iST%E73YAU&IqHbqPr@(D<3@N')k'34nLZSIKHYr$a[;RWMcq;e;1f17.F(@O:V9Jgkl%m7U
\$L$urbC(jfhT/Wal!-eWfFNO\i@oUn'ZG:[HMAVh=eg;*`p+]Af$WlcIuQdp034=Eps3(-cb
WU`*<>P82SIQ'%H4.+l#2pJhH%V%muOF)Rn]Afh_`6O[oBb_rhc=f<R\5ko420qG1&ohPs0kD
IESUO80kZs\DfWslZ.,BKr?SMDCB$#Xbg5qJZdc`)R_Lm;V:k<*dG'Ek\<`QXcr5q^#T;e,`
2oo`2.hnVl"]A<'[JB9MRC8mQmFXPUENNAb_O`sog\*S!rd@%WO]A8#&!4NKeRBJU`kh1,"EfF
O;oQFrk1miVH!!tp6r!P5Cm'G.-L&iao/UQ\)C;lJUD"Z3nN;jm(<@-XQg,EO/^MVi3"e=:M
jO(1tc@5[q;Cc`<dkc'XUdEZ`IZ5$.Zh#a5SeQ'JG_qF@rGD2R6lDtH@G0fSE?7oSqt9l4`e
F\4Y8Zo/ap.2FAX"*Sc8TgY>%$=DOL0PD:1#ga!&`B46ss3_>$0Aci+T2HLT_oEo1(c1AmtL
b81?(h#I=8a8m"N2:W0gD7e+1MMmpNOMc'LA.?PL7e[Z2lgVFtC@a^]At_Z'Y7APE*bc)nIA5
*'\tX]AS4hNrM;X>tP$T6A=LJo4C`b&0JD@--j&'kNr`3DpirXJQZ<Q4d`_%B(Xl`U2IAJ<k\
jIWZR[(GkAc29$aX)b;q^XpS4WZ4>_BT","OpMZI;Tf5J`I2YF)WF]A_Sr@LaS%2,DZ(96g(a
i/He*JbkD9Qc<aj9C;!X1aY0-,+mnIi"W/5prh+W)W.>:@H5qRY;)CC3?e6+fJEI2CNml-q-
VGPDk0<O4h?<,Ss7EnpK>g")jKi"BgD$P_Ei6Cm2!Grl)NB-@kp2WI*c-Q&YW55Dbi2">Lug
1koB/<6de@RSIhbrXN6U-+9%7q[mSRn`/9decSl!,)ZOIub?>J]Aq1^:R+0%/ZRkmMRee-FOl
?cA%N&_5!$/gY@:U'fHo[5TU)1m"ueLP0Ob)2=p1nC$\'Ck>gV>)aE6t7P4-^E`i.,<'$O+_
<KjRARD.1SCgWP7I5F.[Z$$g(/!l3g4n,UDR]AYJ$Xb>02WK3"]AabI60f;&F=krH5C_'c`aoO
I@A'G\Ya6eAiEK>SYf?">[mbKJie&2rBFtTrVAa7`8/g4ph,DogAW%>rVrTdN_S#\W]AhG.9M
BjeQ0cp-We7;g,tE/]A5'S<GEc7/cSCL;fXQr01;C!0bWBIuU>"F9lg_h//e1*;%]A>1MbPiLu
$V]A`YuU3[,JY4eG/s4oC/p[M:d+'i&5rHZEqq$rAueM%?nDA6d.iS@GiGn*EhYJ[JlCFc?N'
k^e[FR>]A17f(2WCJOHHDm%R5fdeY`meB$63W,(*D/_'(CNZI1U@Xa,a#e\8^n$c*0?jd\/-\
R+.[*W@+l1DE4D;t'<XZ>e_rDYWUC(*I"nGISU!Efih3+js8*V=n(\PUAbstT[%Z1/O3j%9&
5GQW7ib"[p(/cudBYJZIYt2/nJff+,%BSf%\n,^[b"QlYdfe):f6..oFll3nC&&63lnnk<HU
kcFk>J;A%]AbK3a0<cFb&ZBE)ku"Ck3;XafBDQEr!+-Qb)<pAo'a$`^CSMOoD_aX5@tqi:btD
n*ECLK'1>O-o_)kB.RuW'l;<.6iG)*[6%V9q?NV=hcKqPJ[>NGOS2MUY:>BT8Ug]AbCG<Q"]AD
&)%?c':Rad%ZLpJ*4LphpBcc*rV'Cgbfm\(BVT:-[M'5]ArmQ+BVsiLFHa9i:2_gfNNXh#S5u
8AnQgLfQKr>O'</4D/if<!q8dU-Y'tmfI7$?QG1bB]Anp*T9"(pVtq4"#Vqjp6$FX\IbG4#g<
)-#!5??+.-E235"/hTju1o`#gU5"<B#K*%0QSjB79DXM65Tn-Bo/-lC.>R[FBiU:2.0S_+q'
MF^?L[]AcL&^aT3<-1<?t@N[2tI#BIg%N_-@g5BeeW!mH-[r.Gk2@M!7))Hfb^`aCdV7rFL<J
bk(.5m+^J!j,SRN=qSZs'[u=MN-p5_QoN8Ba8%T=)>IT5mf;6n745MX3P9o$>3i?O1?PV]Ab<
W5E_J7MlQ0jSbkZo_kGd2%iLElXDhd]AkpG-qrYt+&XGL9KtjoDrn_0FGj&)l`MLR2b-E14=/
=g,ME"L:O:cb[Jge+lO>kfr#%JRKf[Y&1?a=Y\%"P+33!K7WRR?<X9)iAd6gY:rj?gtM`j(n
V4:"t6VrHmD"j:V!6$:6>?I4?pH&5EU[p"c'[p7XCI%>8!`1oB";o9Bka94WC(uga"=V<hPs
\p=p5n97D+,Eb@K#Tt3]A<.fXVZt1+'[$aWA@u=["[uF)MaFi@5*as'&!t1rpPRf<L*p>qQp4
TTdVT-.@1H@bb%+Fonc3?Ba)&HOadB#QPdlrS)>Yda\FfB<W#LM*r-IlAu1P_.k9UH)K$u3*
ag>kl#!X,WLW[m(>SlZRa4^*SO#H\-6E"F@<hD_;0)40OS%#TDN$8)q<>nBFf(EU]A8K^)ktO
!iW\FZMZ#l_'Z+:uTUh6;j3j[XOKF5rG*GRDe"U"@,L49<h^B^DC+o/hVDk.N3+G6GRZDgG1
np=)kO#,k45gsbb-7B'P`eurXjXWgMqgR?eF2S=D\EunQ3eRVbhWZS&R&WYIZ4pD:$\ACURH
ih7e1jC:g!VQc=&/k1JnMDWr&_^WJKeAS<2hYi<M;]A5s)ED(8LLFU)!f03CI`mQd&5`kpeHf
rk'DN6+cJc,']A1E_KZb*@NPWRIa)H&3!gP=B-D_OCb]AZZP,;m<=Cd,UAWGG;s*>DJX^0-9DZ
k=:)@bP!6%sq5)j%GZ+U>@+8Jcb)A<'r9j24AZ%QllT@?1K'XWg1oU9oijYOl+m*qGd%J(+"
C.`Z_[1rXqSiB(bq*^6k0:^S&\\=QAqG,('seW!9#2?s3*JO`#P]AUCO$Lem2$G!Xrsad;5?,
G>O4;3l4njI\tEIo$s6A*&%igUd"(dc,enb)T*iq7)F.d\&l)uim%sO))tECo]At(8o2c6B\U
$0<oWL!6$t)Tafl6AdPGVGY(fZW_a\%]AYC-\)^!/IK9aLE&E8t%*%4/A:J-D8R2RbmjY0qTY
Er&mo5]A"P`Z:tTfu:fBJJ(B3CiGLh(O3Mfd#fStZ33iuA3lJoP#@qYTr#>b7^~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="300"/>
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
<![CDATA[产品类型销量统计]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="0.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="产品类型" valueName="销量" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="无"/>
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
</InnerWidget>
<BoundsAttr x="0" y="0" width="480" height="270"/>
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
<![CDATA[产品类型销量统计]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="0.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="产品类型" valueName="销量" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="无"/>
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
<BoundsAttr x="0" y="300" width="480" height="300"/>
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
<![CDATA[销售员产品销量统计]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
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
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-3881788" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
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
<OneValueCDDefinition seriesName="产品" valueName="销量" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="销售员"/>
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
</InnerWidget>
<BoundsAttr x="480" y="0" width="480" height="270"/>
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
<![CDATA[销售员产品销量统计]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
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
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
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
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-3881788" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="88" foreground="-10066330"/>
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
<OneValueCDDefinition seriesName="产品" valueName="销量" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="销售员"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="480" y="300" width="480" height="300"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="chart0"/>
<Widget widgetName="chart1"/>
</MobileWidgetList>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="800" height="500"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="body"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="800" height="500"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="1"/>
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
<TemplateIdAttMark TemplateId="901642c8-0ef6-45f7-ac2d-53528440ca1b"/>
</TemplateIdAttMark>
</Form>
