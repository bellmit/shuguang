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
<![CDATA[SELECT * FROM get]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="season"/>
<O>
<![CDATA[Q1]]></O>
</Parameter>
<Parameter>
<Attributes name="type"/>
<O>
<![CDATA[纯收入]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select type,ROUND((金额/纯收入)*100,2) as money1 from
(SELECT type,sum as 金额 FROM get
where type='${type}' and season='${season}'
AND country='China' and year1='2004' and cate='现实') a,
(select sum as 纯收入 from get where type='纯收入' and year1='2004'
AND cate='现实' and season='${season}' and country='China') b]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds3" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O>
<![CDATA[纯收入]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM get
where type='${type}'
AND country='China']]></Query>
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
<![CDATA[1638300,1409700,1181100,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[5410200,3200400,3238500,2743200,1028700,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="5" s="0">
<O>
<![CDATA[标准收益综述]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O>
<![CDATA[ABC公司（单位 千$）]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="2">
<O>
<![CDATA[Q2/04]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="2">
<O>
<![CDATA[Q2/05]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" cs="2" s="3">
<O>
<![CDATA[变化率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="type"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<JSImport>
<![CDATA[null]]></JSImport>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$type = A3]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Scope val="1"/>
<Background name="ColorBackground" color="-4144960"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="sum"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[2]]></CNUMBER>
<CNAME>
<![CDATA[type]]></CNAME>
<Compare op="0">
<ColumnRow column="0" row="2"/>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[1]]></CNUMBER>
<CNAME>
<![CDATA[year1]]></CNAME>
<Compare op="0">
<O>
<![CDATA[2004]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[season]]></CNAME>
<Compare op="0">
<O>
<![CDATA[Q2]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[5]]></CNUMBER>
<CNAME>
<![CDATA[cate]]></CNAME>
<Compare op="0">
<O>
<![CDATA[现实]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[3]]></CNUMBER>
<CNAME>
<![CDATA[country]]></CNAME>
<Compare op="0">
<O>
<![CDATA[China]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="sum"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[2]]></CNUMBER>
<CNAME>
<![CDATA[type]]></CNAME>
<Compare op="0">
<ColumnRow column="0" row="2"/>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[1]]></CNUMBER>
<CNAME>
<![CDATA[year1]]></CNAME>
<Compare op="0">
<O>
<![CDATA[2005]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[5]]></CNUMBER>
<CNAME>
<![CDATA[cate]]></CNAME>
<Compare op="0">
<O>
<![CDATA[现实]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[season]]></CNAME>
<Compare op="0">
<O>
<![CDATA[Q2]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[3]]></CNUMBER>
<CNAME>
<![CDATA[country]]></CNAME>
<Compare op="0">
<O>
<![CDATA[China]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0" leftParentDefault="false" left="A3"/>
</C>
<C c="3" r="2" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=(C3 - B3) / B3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand leftParentDefault="false" left="A3"/>
</C>
<C c="4" r="2" s="6">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart0" animateType="none"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象3">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1" animateType="none"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[D3 >= 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[!T4,'qMA$D7h#eD$31&+%7s)Y;?-[s('"=7('k*E!!'sECc_Us"fFp[5u`*!Fd7/M;g/qrms
>A0<8#IG(;tp:(")#S$P#S*i';+\#E"BML0K`h"LXmFJceirWi2k(77e1J8/Y;qXiCGW'1D@
EUE-)LC2Nb>pOWi`:]AL4N]A\MDXTA84G?UBfqQft-GP\OMTl/CMGFQ[d!e`on.$h@"2E5^r%T
@$fAQ:83,%m;#t4W=3n5W_8;p2ol^)p\S5Y@q!@'Kt5:(%mu:n9PqBW_B.mRWDH*3D9"Q(b!
s6O5jTIlCodRniRJJm=M9gM28o'$h9Qr,I6r?";VD2\B5k,[te$8*sBZf1`d27FoNt05e1UZ
X8SH%Nm3)iZ40jkYX$bI#NW]A-V_M"D*X:TmX1h]A:mXZf7Xfto9K0;.VPm=LJp,lWGnufK]Al>
YV>f$b%ma4=\':(gu8@ojp>kHX81K)A2Q@Xf\?D<2=8-\Z'=.Ae-SNcc.mgc/5t@TRQaONKH
D"E=Uq[oMpA?g^#B&O-C49:5MgLX9cW9C<()#cWt0[rDS[f=E-(dRYGnc@d0u1*4kc,>:>l*
`ep1V"b1;^8+/f:Xl>T_J[k+s&D(NTQ!6dj'm8"Lu;FS@<rO%]A@[DN9o%,nCLcRIVCiIQ?Yh
rr<lAKK$',T-Ej4EV"`o\A@uXscl93`W&X-NT.rO-=@hpNps"0#bffWM[3B3WRN6W$oeG[m?
Bq#+pnf9Yj@+5K+LVp&q_Q]Adt.#,Y7/7WoFYclqc]A%`[BE7B)Wc32K,6FSod*&9MGg;>u/b@
1"'iHgV/e1nL/f-(Ya2cZIt+OkLuo<=(8_"1BHCf3Y(OBRuq`$ND='Ze45%]A5'cP6Ab<oUZ8
$8&lVaesq<Q2A+.G=\TMp'#T>4'4F?G]A:#W3:=>u:9:DtL\q0thi[>kB=:V&B_C,4dgVo-/?
YJ1YJA(m9:hGLohW4mf"qG*##&t=^qO5=O9QQ0Lf"6d'9cdJp+.LXZ!k3mh-R>c,'#b]ASr\5
r_RR?tRaWOVaT1/3^#7Qn/KIE5</L?L;!uJo@Ao_idZ%A1p<(dSc(p2;S-$ZX]AQH1`B^oqF,
QBNA,CPV[OV%R#UV]AXa0#k'd'jqpm5htR5BU+EZNqV84#HANIqO`g!1&si*ZM?L:^h\'!>=m
R/K&?GJ:0mCLaKO,K1dBAsU]AUYV]AZA$QUS%o99IjKLJ!^WXF5@PDoN%eU^O*jB`P;WDNQ]AKE
:orD3p^p@Vqeh+Y$K@)G4hf&TOnFe80GEn`EoIOMC`<<ek/2:Y('B[IS"B#,)$JWdO_s?^@)
sK:KZBg,"OqGO5hpBg*?k=,TT/"9<*$JC^&NZNVSMN_>p1n!B1[9Db!u`Y&#["J_h]A,O^&Ob
So2q^BaYSApB_i@B0D]A\.$puT^X`/UPF%`bl%'#O?kZTYt1N564]AjHu1%*CQ@Z79&*Cm$;t`
ZZ<6:VN;"?+^lm6T"p.p`,Y"'fEEc5(1Z=F)6hpHAoVR@j`H>)Hk^.EYP(]A/&>+MT1VQX@8^
#pbUjdKYeB]Ae066kq>WH,)3IFo5s+si>V#K8fZ@$s]Am8eU%j==1@1(7/h]A$qdaZhu61a2K-D
0[%,WpA[dT^r)JFn/u35/=))1nmc*k&@>YY`^GoX3[r`;Uh'HXI>!1#Q'$PO(%c:Zbc='P(2
8$5"0K3E&qKqtI2CWUNA$&d+%j'>!X]AEmocI^"us-=."hI%=p4#LVVQ:%uS[Pd4/=hprN$F:
(V)><F.5C@O,_/$Xj>\/PWa"A>ZQYd3i&S`i^DN^m-RdN'4dTIQ6d?F9*XJ=:N23_@YhTd]Aa
KZlU4=Z%t;kMr8`<\6Z`D-Matkkj!i`*(X2f.gh'c?4;=$"4O\=[l>A-Zp(%"#L9'')8bmn,
NFg!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[D3 < 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[!FH&(q2%pC7h#eD$31&+%7s)Y;?-[s('"=7('k*E!!'sECc_Us"suo05u`*!Fd!,.P=H>-(C
5eL_ZQ/s0jJH)<!_c>Js%<%ZrI@NH3$@K$Unp_.b'K9JO)`0:.L)%U*?F7JZ),Q:TU:bS18c
6&."@#0F8mGI/e(Te_6F?1V_U]Ap?hkAmG!$l!7B1I<t\5M/OB1WU:l&Ki(b0+XYPCVl+FCk]A
kOm;`'d2kngK?07FV]AFI/Mt&*Qa69;?]AF7dLHi]AR0(gI5)F@f"@Q+W:ZR4TA5K:k.u9q&SnP
.(#qDP3i3aQN/M)rhU&Aau,\k*ON5gebLsjc"T!SOXY[Fh6pP(6sX.&e;P1OBmSh]AY,g@VpX
gT)Mj.[<r"QOTbbrkj`)ia]AT<qf:&l.05mdH%8`dM=X&I$hC"F\dk:F:(VbHKJY0UP\0:/Q(
ItHXEsbu-n:i$mIPWJ]A2P#<JNjP8m\[en3$;-"AoBGleEpXeDpso]A",pV8R*7Bp>:^INCP^f
e5W!8"7<_5@(r_3dojVLW;kg:k+KD^PRtU]A)1lS-Yq)0)B&dr<h'$=*+lQX,\UuR2J^a)D7s
-%,49-!f'plK_<ULbVSU@9gS]AK_`q/h]A=]A`O1t!>?;heG?'7'CcF_!O<^,05+#n;JS"gGKFd
RqDD7(C?lEeD8Rd3@hI(/=9lpqWJ[T7QR\S!\"g-0&[jk,f&>H>/,4-cL0,?QMT\e3!RX#kq
g]AboL8uOO"B/FS6(_")h*B92j"HM!P.0t-O3lD=^D?R.+*V%^uX0jbLLb6I'a4&4u/^]Aeb'/
Xs$!!1YYAm4Hh[fka9!`OFAUR90U!+q*#NTo"r%Y=[2e4jK4J\)*B\rK.!@aZ0h'adkmESXG
bbL#Ul!iF@>4JKlO_PLKsnO^AP*m@#u&(:pf?UHN]A#';]A=4"^n-HUBpY3oc3,?nkM!3rG^k'
H=g?&;WJlMs3PX*5n>@PPe&^5s,A8Yr,jBd!3pc9erB>18<+?&:)dqE"WDZgr>7Dn)j)7q,Y
MVVAW#R)gs?iiB\p7eP#-Shm<a8/:p>)()g=FBVMjD@H"&j&[KUeY'R[PC=MGWU/$N.jO.GI
0eJ*;0pnq0)"=4hoH_fK1)ia(U(d=k8_:,a)qqNUPU7.R9r96K)4R@hAVrWmY*dDO>mItO19
bXjrE1ptm%fg[k%o,LA#%<&KYC/>a?o#SA7P[/:<lA7/:Jm5es>M.SVL7Xd1ER<5\t"_8i@X
#WT0_6%k_oNL^CEdLLYOFm0:P=/_H/D(lhWa"8&Cb%#Xna?%r.&6bSanTIMOEVRfBlI;fbOb
`!^AHgXaNWhCT=cOR`.5Q='E3LRSQh;g$+U>d@Q1W\p!d6;q+3^"g_^S*s^UC9u`^]A<s\F&g
UJ[#_LYqoBB5[^Ut;_ur*/p*^4U5Z)>k!mqbt%95kc]AXhupKmY?dg"SgTjG8h)erU%=J<<G/
769Z<Z0'/<8=Z6t1"li[Z&TAUfR``7mY9$-;6S)YQa\?s&UDY.,6.22Ffh7,\3VdCg<($kZo
)DI:6=*:KfgslN*?2M<G5U*d5>tLUf8gb)2FgR/uUZe_ikC3S>G58n--q!a%m8pIIRrHe5R>
,F/@!HK/tKKd-c0DK81N:.Hks:5aAlUDscqG+taeiDU.J9R0*0m+*;NCd-NS)&#*)ii'"KL4
?M<Wc.O9j/h&)0=0<.g^&qk5I""eZM_em%2Gd/@$>%j+:]Aq"i3QDZq_["PTZ5(Mbe"@-.6P1
4>(!d89oG*TD?]AK!Kc7cK`<cXe&3_:4srqK"]Ak7o2qT1Qu%'02etoBs"]A(_<IT)mnRFToP"`
C=sg'IQ\LIKd#Ya\e+fj,(\j6+^Z4G9+)OYeXLc$QS6ZBB6=9$ReuE#`#8Gp@NrqjRJf$QEQ
W(cWGQ+I#C;!t:b>r]Ap$+N8'3F5mqDDrHO2^Lj0P#16/\AYX;i1`mCr*3@jEQ(-s+h?>&kV4
Od^!LE&KNsopgQ-GZ&\-q^1Nm6>D91ra%^pX%\]AA->Mc/IKh$Q:X,_qY[6HW8PR,ada^0J3c
bt;8A':64"s4emz8OZBBY!QNJ~
]]></IM>
</FineImage>
</Background>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="152" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Top style="1" color="-398635"/>
<Left style="1" color="-398635"/>
<Right style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Left style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Right style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[$#,##0;($#,##0)]]></Format>
<FRFont name="SimSun" style="0" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0%]]></Format>
<FRFont name="SimSun" style="0" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-16776961" underline="1"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Right style="1" color="-398635"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94d7P?6I^VKJu[8u4,L[)>Y31KB5d$Aq0u`[\5mD09ADKN'BgAJ9YdJ0_63Q_K#p!4%tcb6
QP&7#2DkQ;Q$H[O<Pd2*sVrJ:Jmg0aUFc2kV^c\*#`K[bj+&e:0UDhY5n(4MTd[L\%/*nU,c
,W[/#g/9lRZqVuu3(;++k@)3p1YPec03E?%bFhn+qriV;HqKm%=]AVdbF]A.s8Bc^-QPg809_n
1VY%]A-D*mNpXFno?[.LcIhe0[9EIcL7ihbg*OW1aihEp1pZo3Rq(+Bf&&F$Q0Gh+Y:X<Z8?P
$d'HrIF/f..@h3.n4f%"d='5+uC@EP8E@PF?!Qk81(RGZfoKT]A;`H$n:r)[Z7D=Vh_:;.[<`
<I:CIVhX06mK>G)#G@1M*QK/eD5[t.gJ6r^hs`i:gRoM0jVR30d7rJKYgV#F6rlku2t:l&jD
4@@!=u`>_\K3pk7bV#\0$,&!In+"II.)&2lQW\@I%HBHHJYph(jqX4=fqhk_a!CcWj?@-tc=
A#4bG+INVK=&(:s=kf+td6[7C6#2).lG2EPWWlB3S7?"s&*-[8,.0:mM#FV1l\Ztj#JAKia8
6O+4jJ)C1BQ?L[[ij0,;2DXOc;(^BC@]A=!-JSoG,O+I&jsHV)g&%^#KTXiBD3O\QDLDRkA26
<l9gMTPoS6F8O79W&dU7M1hNMk9VqjWf:=rIL_.i_0<`ISQ#WI9[6=0(q5Wrh#?.ZB8KQ')!
<NqeM4^</trq:(kZjeT.q37E%E<nK%!J\^I`ag1414B5^Gu2l3L;YafpSMf,HDBW#k!J:6##
apTin-'D11fpIR3fd4=2bC]A84oUY#on0<R!c'4;4s[Fr>390%B(\hq=2KUWVin;P:6stZVKU
/!"S#3&VVV19)7@NFPgEuUhDORa22OkMcs,-&#)RN=GH@<ebqQN&#-5#ZI9:!hie8Y8O>gMD
^]Ao3BPKn/`'8q)IhE%9<^CtLc<n5F<Xn<.@i0F2f/8%NniiA[[sP9Lp)h78Ua"rnBb#%%FmJ
FgoY.gA;$RE)S^?0E_m/ku;Pdb:j0T3sBd-A8!^'QBK1p`m9,K,4=-0:0HE%KM6V*36-%Ra_
%)2h[9,;7<!a>lR=GWV3rUHobSoeL#s4a?pcfa%jd;bO,;.[0LDjgFj`Kt,D;EBK?:1BY31B
h''^>g7T;#Aec^^JkL4cHo*%-&76;8S)8f9.+"QP$joq0Vdq!(-u,;r15\Df?k2!K4cJk;t2
Y6"^8XT:3N./+>n.=!_DN?&%]Aa/!.0<NnRS???!mfDirh&TK9+-7*8-'?P1qTZ192JiPjuI\
i>lpN!+d2nDkERf$/C[&#iKK"-a.DHNf/j`,\ZJ\RNMl*cFZ`C;*]AW$$+lU%UN@rgF=KsLYt
jNUst*^T7Sonde1gl!J2(/T^Lo2nJa3T2;OmSb)W$]A&>LO7k^V$F..1=g8m`Dn<8+HUYRim]A
j`<:nC1T;rp'E0g&P)-AdooEtiSiV1M=CmeA[`lWrUd3akq]AFAjfBnYRAlMX,jAV[ou$-Ia9
;1b'(uN<r$;Q6d`=-8dN]A7>fKjr^M(Tbs]AfGMHZYdGP$!EamI5%/CNf"`<JQ8lBp-Xn(MDAY
":Z_IPEdQb^&QXI(EHBooMhGZ]A]Apj$R>sbP+7+8WZBk'lqc"M=71f57+'p/<l]A4`:k0i?cL4
Y_nQg7@<bBMURgaqpAr=Dr;,LN)IEq*%4!QS9UqVk^q9\al9B;mp)HK-)`[<J^rMZ!Wd#^VH
*tp/eiF4pWp?e%8coL!MM$=_Z,#H'#!&S8s]AR7^!UV,+F@;jIYk@p<rVU%a`:=6R@J:f%G,9
RN86(3>?rY_WZ\PB@/TMA:RR]A!.H*+`:)bL-+@UHDgfYK9I,L0NS<A;hT$V^\mumDN[^Ad@o
R\iBQ"U/0JPgI#ifbsa0>qA%9n?c:YA`m?LfFWN;*mRKJTk'l>p"PZaCPR#`nA,`t#7"@6K>
0Na_=2e2T47<@Cu$X71(\$W8gDTT8gsA#"]A6,?X,7PetKKm:$!h7eb.lr9st#Whm0$4pr=k>
V"YI5J0hM#uThrN-]A&^*8VEW]A:Z<3aBt4Wn<aB+A0j*>q@NKf9j7q:]AEqHq]A;.%q,WF>@MBQ
I5r^G3)2?&KVJ8+j]AfD$k*2WNqPi[0BB691oDUpFdM%FlteUt-8sm87,CA!`Jm6Sf8d+$-"H
1*</%5;Y^1p7,BhccbOgfMHX_-[.:.f@sp\c?KM[>WBj9:r4if%m>a%52SZ_#JQ2U7":fmO+
qH'r7\#75Y>I8"s1j[=Tp.uG7ips]AR,Kd39l:e`MqVVqQ)fa+,!^SPd2;6W&T2c-TLVJ4B4K
H!BCS'%H5qcn4YLXmLr!K>"X6oMW0KF.`(#olT9j]A-N#c!m`_"(;-b'4;iD#YNXg=2U5,P0C
43g\.3S,kA=\R0b;k%Zm/8lR3q<tAYJIUXScdE.^8#U,+;BUN:J]A)DSP?3ce7u/Hk".3lGkr
,7=A!nT\-a#YN0TuRHU1(p1iKF,\6:HpKfKO,c9IG_UoRk$H5j_B5oLh7A;!c3DK?=h]A)3WT
;frmK+k!^\N6<cQ[ho@r!9Y`;+qa>%Li\,CD82.rC1[\ok?ST?k\e5:jOu*hP,CZ;$464L_T
3RZ5A:chAF9Pn*\GL@_Nat[fB333bjDo9!%Ol*\\^'K[VUjP!A`2AOD+p!.m(Bk+%U5>@r&\
iA<78]A_0NK@&N:rg*)J=<bZsC/Y3b)ZJ27%<#chX/e8l%X8Eh8;LZkAh??^qZeAc=gd-ff57
ri,m@h:Dp,=Hdb8;B/dUWl4tqgp`ZQkln`]AnYUZ+'QRtDJ12]A`!cI@\7$1E>;:(aB'a9PL2@
S7<!ic/N`-Z%@r!FH$*sZ0#:L?.`9hj0?E^tcW%+>9Thp5+R>stBR<Z]A4LD5X;,b7au1IL%l
C_%H-@\+F^XAt%e+/TX,jPVqgJ."OG'r0LM(INM2-.cV`RFPXdBL>K+##NH;2EMa[#:/+en5
1mJADcoS!J1TXQMc$C;iDgp-Z*20p%[BS8j>p&P0^(0_hj#V`R.[+qO^+6HZa>83MB-Z^L@n
h1E;=-Zd7Ui<jJRWG!</kW/lcSG"B3X&RNV#ZtC9Tl-8k#e=%Or4KO/]AbIbVN8sX**C%e]ANH
nSrpa?)<0q".3&LbAmh.f"%O+Q8o3KM/:ha$8u&ET4cKZ2QCq2`gZ]A;$6\t5]A*O9,(qNXqZ9
4qD9EY0;"h<==Nq=^%5&6e")$cETn0u1#2*QAnI\9;FTnUnR[+N'&OAW)qP25!9HtG=:15SO
f4tl6Zm-etSHrBc%]Aq>QCal`bH>]Al#F2rT,bk;HT?#?0tS`-FZqTMEel?HF.n'5l:o[)GB6R
J<u.9&^3pnGZ_>!C_F'6'j;m[%qXM@Gi4;9UE&%TG6iY!Hf/GSI8<eX\-&ZSA:3LK]AH!po'l
2O&C6cRdB)3ah0/<8^f<R3C.o#n4db+pDbSmg5a'*Hr6/Tgoe(<2sG4.+Aoi"?4-GOW2L[an
kf$U(=<shigRpKf^+-saj(ZfiuL=X3b,TR1"X-aO6oflOkPprjO'XnL<%6PZK(LE,$>+.#_9
^Zfne?0Q$*X"akB9*D*l71D(;F-.h9@g>r#9C0r`!M-/fm`]A4Xcr@N-egPZelNK<=u>S!S/o
IfN>oD>-G`F<D\)4s.%(i;]A!bAO:D*r\f=#S_@gmM;M2Q1+%%mf6M:%Ri'k/RXVWD(VoE:Sp
o:Q#%(A$^X<3D>+s/k,@G2oB8#oK/Q.`."ol>hbR4f*?"cTZ$9<iogGP59Y'3nLg,UB]AJ:MQ
.L`9H/,3khu*6P&_8hl_^bMILoASY"0@X)Gs9h>h1(aG5tVF^IK$NHE%e'^MS7BFuLO+0mIP
Ia+WGMYj_n#rDSrPGG&qJ)alZs4PYK(P$7PY,YCWGt7i,Q6c2D626bX/LX1hUthb@ZJ9\3)o
>jKTc8`Q0acU/_(NlgbW%#99E1ke\jH#+._o3I^jD&fL$hOHV@Gbai6d,j47ui9I>=[7c'FN
+N6,nl!21]AW^!4&cKR@MknnM\jf=MFEE:Zs"JgeeX8cj0e/C?I:TbqMZFQ+1iECA<%;1W?kA
Q,a8"YE8>b:eq[%(sI\/'M'N/P/4<8YU4YphqD%=B6#,IX(+-CZp>Lt'l>kTScAfH(3@KAQa
?hru;e^G=*V?E_^sSsd]A0$m@`8ju*EC#3G/0)n#`;ilD(IQX:<h!o\!e1e'U&p1F2d(tf4.`
-VqX`'ARJT'l^1[bI'1'L'2SeU:Qpa%(1/42/1Pgc"c563.YhA.FjX0tKtb4G[mi2Df#,0K<
VDe[EsAYIbi^4?#RC^M;<Vo\lM<kdC,uT9O$;>+.CYS>@[P%;W+jl+b&")2V_73th]AF%IGX0
6B]A;j0%i`BO]A;qU7Vi@RlY`EoGkh.W3iRWpZU-OEb*M#0B6o>+oJ.6#?^M]A37H"I<fHD*DAX
PBbFU5i_29L92([$:?7gJngX$DXbY&jq^^ID,u[Wkd8gkQC_G))[EFsT4=.NUEd'FBD0r'fK
J_u.e84/ZRn)2*4K+R6e[rq*"5/f?nDlf9l9:3`]A/+hu^H[L]AkHT%"#_7bg$8WHQMg8,M9.Q
3TfaiWIqU_*A$\=BT"7(a&2bP1HT*VPaPJil8KFcH&S&)h'HC7JY>lId$L>,1e9P>np"Xmhn
3@?H,_)p:YCN`DV^a&LCX0UN2Dh.4AUkX'6A]Aj:bZ&Ds0A&>@`3QH(pe`?]Af7oh(5Dih@b%0
YB@$lMaf/Bi"U`0"!uY^7eG]Aa1*-6A^)fQUib\pKYdB6*IQ@$W*2jid*dp2,%]AA%1U;sBq2k
5u)Zj`q56ebnAODaq]A>3"j\&&jMloFcBKn+odm9P^7()=]AH_ig#ujn+)qe<&kki;/N3AVZG[
;YqXP+Hi_pIN2QT]AlkM3QN#/6'*@Y9L4t',W\i")G*n`mAFK'p[$SZBNXoRfa7I'HZSIC7<V
LJ@eZ?#(2^&F(5CC_,Oa>^_RFe[LuZ#&!L9QFO-[+Rh3E>UTO[TpE`gqMGH=HMiT.P0NS4FI
AkrN,0>5_-EB,7?RRFMRrcFiAD-1LGO\p.3222IJQAMRf#d!T?aI$P*KH?Sg"hPgV6&E?]A?(
:c)s/N4NGP\a8ul-AXG;ZF<QaK]Aoh3`a@;O:m\%cHWqDY:/c$;Z_<tXE\B5qVi0@h"7`AK-o
88E4+0T0Ap,L4-?j)),Ym;Y-Z0pk"F.FX0^T)=Vd&TA[2)hf5!g\J0)2/N>qNO94C<:BF:2?
Hd/Z!@MfQ1KcR*K#iY/Y'!N)bZT5#os>guL1(5l+T:)FO*>jG9tc^`!>"3HKT@N$NZg#X5W(
AH,l;VQ>EV=4.BcYO>Zm\X"d/`.N]A8C*LX24#+YLl=W`'230&gIh-o5]Aa6Jgt'EUDnkQ8lmW
HW$>W.9_/+$&HMbYeRIQ2\Wqi>1=_]A9*203DL[BZT3?LD!/7`d%K`NCsd$@n]A95W4cd8Zsf0
MdHmocDQ7n^b"=IZ'^OEFeJ='\$>WXO/=1SR;br=l=AFZE1\(+f/m@ZO/=fNJpHV<`fFST5O
6$tBcb8)0S&!K?;NeBHp*:7^H:4uLWe)7J?9cV>Il8f5se^CC,Ze3Z'B%<&W2d]AcFmn'p^_<
EQ^-TAO0#,?[GMVWf1b^Wk;'r-#!93[fi,6fDV#E)B;Wg?!35_l'%K6gSsbg+"WSYuC]AmI)#
WRbHG6%:IE)>b0[m<uirDEV!TR'L:>l$n2rBE#R<hbt^M*U'j,HU1.aBG8gkAHg[U99LP=Nm
XTXI+KpR;om+BUq5%W,4;T#!@H!T.e`urYDj%QW-*-%%p4?ngVWa-`@X,;!u]AS-^OZO(4X>I
9$C`6MP3?=TQJ*bf5+Y@_2F41Ku"7MUdN[)CWKu!jJBIP3;M+%oO<F(3K?-&s*H1M9cS=CDI
]A5U.:IH$\i2d?OMniY_OtCG[F>Taijn!6U,?]A(<$:*q:LQ5aDn5!&`^:hLr)ORi44<\E@k(4
X2@8aFZb76+6kjhE3P^2BJ;qWWP!q[!Wt$l9OEcd*YR>Xd?6tHfmagn<$3kq3o'gDBR<kO^!
XWqhT]A,Mc@'A3\$nFPa$$N5AI<U;I9^:.#a#Ge(92gnTZ]A*Vm3TeA&_&3D[6F*:/`&*.*phP
,HKqM&=pW?f+J`-N9W(<J,C6VBC8`X5SVn^#.2:+T'5>*m(_9e"6r4UD]AOhU$LhnF3]An&.P;
ZKSFeYd$GqhgN\;2QsTVVC1tp?)X6^5WZ*.p7MT&3]A41V+EZDd&d\JdOI9sfE=Z6?i&%l%_1
:,)[u@4n[>*Fo9fe##-T(gJ+[K5*q(c(d2Wp_s%&P%(FRadJ\mBBBA.bluZaiea_?t@SeQ*9
4PESq=\"R^T%5tqb\#^o#`56N+VFJg]AZ,63[f$bB,9:)h`gJD$END;f&m*I2;b0^<;CO/hL]A
F1f4XYCR&$cqtYdiXsTcO!7b]A!CHVDbGA%Q6//2PO5#M*9,1)eH*ma5aGJ]Ad:>TIA9!LfNuh
VTmZXW<eVn\cc\M`io):/\A!2Y._\eL.(lbcDAR]A]AtEI/t`HY2G`H?&JPQ\f7OKiJ8l16k<F
gmEl?'bLa.hrTV^$Tkt+a./[d;u5YDc-*9Vi*JBM@WX]A/*F^b#4BjRbg@f(AGs".M0aRsIj0
qjB/M0J!@\i6;:TYefZs8f7MiG-8V8("u;,Q;#Q,.qu:#,ft8fF%f#FuiZW2:n$+9be&N31Y
mfloBh@"LI5IN<[M'1?jC/+fOndaM4IG*WD)TKBpd'q.\?YV=D8Vd%=Iq=rC+9`(:f:92Xa9
!l11QOB8>JEj,Ql!9=3"gE.)!eH8cm87oi_m$iT&H!R9&OAu2iUl,UHPX.JIIg[Ej"F.l334
K%:)HcQ`BX\ip.rHtSYHG;HOAIN9SQ(<67R4?F`VqtK):s=%FE!h]Af:Dhc*oqF+<,&d`cB.0
<n,<r!qd-G>?M7t]A57(#LXR5d7)VI;3R/6f,0Z#]ASY-aM[Q6M7jp"'B[i5"^7l`lQh:DNJ^G
GVb0aA&;LFNE9Sg4u".M1rJNqg4bA[Q+<D(^<5N"5<H/EaMj=aDgn_VJRm%0e\A3I`fnkj\3
m6/*hl3AXEqB9I#"ZCW4))@;q)^qR`L%J2"059XNp]A:A6D:K\T>4qL&_pR0e1cX/ZCKqQQ?X
e<EHRKjYQKg4$gl@)QbfY.Mlgh-B/HarH.c92.W5#g7<_('"UcscEn?$2?J4#Mo?d3Bclq2[
,L3==59*hUpg4h\Q5C;o4@[0dAoQO-Q8:gQB$\"c.sAB2gK1[/^"=<lKTYsZf/!4nk0QBGRC
)X"7,<?[&MWX#_bIeV0TiLe6VbCC#3E=5lBcVFeLHH]AX^D`jA2)?UVslct<5IqAanG0toQ*l
-qjT0oQbS0%KQcK*V#q?#U4XrVjQ/jXo*l@/g~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="480" height="540"/>
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
<C c="0" r="0" cs="5" s="0">
<O>
<![CDATA[标准收益综述]]></O>
<PrivilegeControl/>
<CellGUIAttr/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O>
<![CDATA[ABC公司（单位 千$）]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="2">
<O>
<![CDATA[Q2/04]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="2">
<O>
<![CDATA[Q2/05]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" cs="2" s="3">
<O>
<![CDATA[变化率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="type"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<JSImport>
<![CDATA[null]]></JSImport>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$type = A3]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Scope val="1"/>
<Background name="ColorBackground" color="-4144960"/>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="sum"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[2]]></CNUMBER>
<CNAME>
<![CDATA[type]]></CNAME>
<Compare op="0">
<ColumnRow column="0" row="2"/>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[1]]></CNUMBER>
<CNAME>
<![CDATA[year1]]></CNAME>
<Compare op="0">
<O>
<![CDATA[2004]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[season]]></CNAME>
<Compare op="0">
<O>
<![CDATA[Q2]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[5]]></CNUMBER>
<CNAME>
<![CDATA[cate]]></CNAME>
<Compare op="0">
<O>
<![CDATA[现实]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[3]]></CNUMBER>
<CNAME>
<![CDATA[country]]></CNAME>
<Compare op="0">
<O>
<![CDATA[China]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="sum"/>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[2]]></CNUMBER>
<CNAME>
<![CDATA[type]]></CNAME>
<Compare op="0">
<ColumnRow column="0" row="2"/>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[1]]></CNUMBER>
<CNAME>
<![CDATA[year1]]></CNAME>
<Compare op="0">
<O>
<![CDATA[2005]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[5]]></CNUMBER>
<CNAME>
<![CDATA[cate]]></CNAME>
<Compare op="0">
<O>
<![CDATA[现实]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[4]]></CNUMBER>
<CNAME>
<![CDATA[season]]></CNAME>
<Compare op="0">
<O>
<![CDATA[Q2]]></O>
</Compare>
</Condition>
</JoinCondition>
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[3]]></CNUMBER>
<CNAME>
<![CDATA[country]]></CNAME>
<Compare op="0">
<O>
<![CDATA[China]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand dir="0" leftParentDefault="false" left="A3"/>
</C>
<C c="3" r="2" s="4">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=(C3 - B3) / B3]]></Attributes>
</O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[var pa=parent.FR.SessionMgr.getContentPane();
pa.getWidgetByName("iframeEditor1").setValue("ReportServer?reportlet=demo/analytics/get_1.cpt&__showtoolbar__=false&type="+FR.cjkEncode(type)+"");]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<Expand leftParentDefault="false" left="A3"/>
</C>
<C c="4" r="2" s="5">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart0" animateType="none"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象3">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="type"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=A3]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1" animateType="none"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<CellGUIAttr/>
<CellPageAttr/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[D3 >= 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[!T4,'qMA$D7h#eD$31&+%7s)Y;?-[s('"=7('k*E!!'sECc_Us"fFp[5u`*!Fd7/M;g/qrms
>A0<8#IG(;tp:(")#S$P#S*i';+\#E"BML0K`h"LXmFJceirWi2k(77e1J8/Y;qXiCGW'1D@
EUE-)LC2Nb>pOWi`:]AL4N]A\MDXTA84G?UBfqQft-GP\OMTl/CMGFQ[d!e`on.$h@"2E5^r%T
@$fAQ:83,%m;#t4W=3n5W_8;p2ol^)p\S5Y@q!@'Kt5:(%mu:n9PqBW_B.mRWDH*3D9"Q(b!
s6O5jTIlCodRniRJJm=M9gM28o'$h9Qr,I6r?";VD2\B5k,[te$8*sBZf1`d27FoNt05e1UZ
X8SH%Nm3)iZ40jkYX$bI#NW]A-V_M"D*X:TmX1h]A:mXZf7Xfto9K0;.VPm=LJp,lWGnufK]Al>
YV>f$b%ma4=\':(gu8@ojp>kHX81K)A2Q@Xf\?D<2=8-\Z'=.Ae-SNcc.mgc/5t@TRQaONKH
D"E=Uq[oMpA?g^#B&O-C49:5MgLX9cW9C<()#cWt0[rDS[f=E-(dRYGnc@d0u1*4kc,>:>l*
`ep1V"b1;^8+/f:Xl>T_J[k+s&D(NTQ!6dj'm8"Lu;FS@<rO%]A@[DN9o%,nCLcRIVCiIQ?Yh
rr<lAKK$',T-Ej4EV"`o\A@uXscl93`W&X-NT.rO-=@hpNps"0#bffWM[3B3WRN6W$oeG[m?
Bq#+pnf9Yj@+5K+LVp&q_Q]Adt.#,Y7/7WoFYclqc]A%`[BE7B)Wc32K,6FSod*&9MGg;>u/b@
1"'iHgV/e1nL/f-(Ya2cZIt+OkLuo<=(8_"1BHCf3Y(OBRuq`$ND='Ze45%]A5'cP6Ab<oUZ8
$8&lVaesq<Q2A+.G=\TMp'#T>4'4F?G]A:#W3:=>u:9:DtL\q0thi[>kB=:V&B_C,4dgVo-/?
YJ1YJA(m9:hGLohW4mf"qG*##&t=^qO5=O9QQ0Lf"6d'9cdJp+.LXZ!k3mh-R>c,'#b]ASr\5
r_RR?tRaWOVaT1/3^#7Qn/KIE5</L?L;!uJo@Ao_idZ%A1p<(dSc(p2;S-$ZX]AQH1`B^oqF,
QBNA,CPV[OV%R#UV]AXa0#k'd'jqpm5htR5BU+EZNqV84#HANIqO`g!1&si*ZM?L:^h\'!>=m
R/K&?GJ:0mCLaKO,K1dBAsU]AUYV]AZA$QUS%o99IjKLJ!^WXF5@PDoN%eU^O*jB`P;WDNQ]AKE
:orD3p^p@Vqeh+Y$K@)G4hf&TOnFe80GEn`EoIOMC`<<ek/2:Y('B[IS"B#,)$JWdO_s?^@)
sK:KZBg,"OqGO5hpBg*?k=,TT/"9<*$JC^&NZNVSMN_>p1n!B1[9Db!u`Y&#["J_h]A,O^&Ob
So2q^BaYSApB_i@B0D]A\.$puT^X`/UPF%`bl%'#O?kZTYt1N564]AjHu1%*CQ@Z79&*Cm$;t`
ZZ<6:VN;"?+^lm6T"p.p`,Y"'fEEc5(1Z=F)6hpHAoVR@j`H>)Hk^.EYP(]A/&>+MT1VQX@8^
#pbUjdKYeB]Ae066kq>WH,)3IFo5s+si>V#K8fZ@$s]Am8eU%j==1@1(7/h]A$qdaZhu61a2K-D
0[%,WpA[dT^r)JFn/u35/=))1nmc*k&@>YY`^GoX3[r`;Uh'HXI>!1#Q'$PO(%c:Zbc='P(2
8$5"0K3E&qKqtI2CWUNA$&d+%j'>!X]AEmocI^"us-=."hI%=p4#LVVQ:%uS[Pd4/=hprN$F:
(V)><F.5C@O,_/$Xj>\/PWa"A>ZQYd3i&S`i^DN^m-RdN'4dTIQ6d?F9*XJ=:N23_@YhTd]Aa
KZlU4=Z%t;kMr8`<\6Z`D-Matkkj!i`*(X2f.gh'c?4;=$"4O\=[l>A-Zp(%"#L9'')8bmn,
NFg!(fUS7'8jaJc~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
</Highlight>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[D3 < 0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[!FH&(q2%pC7h#eD$31&+%7s)Y;?-[s('"=7('k*E!!'sECc_Us"suo05u`*!Fd!,.P=H>-(C
5eL_ZQ/s0jJH)<!_c>Js%<%ZrI@NH3$@K$Unp_.b'K9JO)`0:.L)%U*?F7JZ),Q:TU:bS18c
6&."@#0F8mGI/e(Te_6F?1V_U]Ap?hkAmG!$l!7B1I<t\5M/OB1WU:l&Ki(b0+XYPCVl+FCk]A
kOm;`'d2kngK?07FV]AFI/Mt&*Qa69;?]AF7dLHi]AR0(gI5)F@f"@Q+W:ZR4TA5K:k.u9q&SnP
.(#qDP3i3aQN/M)rhU&Aau,\k*ON5gebLsjc"T!SOXY[Fh6pP(6sX.&e;P1OBmSh]AY,g@VpX
gT)Mj.[<r"QOTbbrkj`)ia]AT<qf:&l.05mdH%8`dM=X&I$hC"F\dk:F:(VbHKJY0UP\0:/Q(
ItHXEsbu-n:i$mIPWJ]A2P#<JNjP8m\[en3$;-"AoBGleEpXeDpso]A",pV8R*7Bp>:^INCP^f
e5W!8"7<_5@(r_3dojVLW;kg:k+KD^PRtU]A)1lS-Yq)0)B&dr<h'$=*+lQX,\UuR2J^a)D7s
-%,49-!f'plK_<ULbVSU@9gS]AK_`q/h]A=]A`O1t!>?;heG?'7'CcF_!O<^,05+#n;JS"gGKFd
RqDD7(C?lEeD8Rd3@hI(/=9lpqWJ[T7QR\S!\"g-0&[jk,f&>H>/,4-cL0,?QMT\e3!RX#kq
g]AboL8uOO"B/FS6(_")h*B92j"HM!P.0t-O3lD=^D?R.+*V%^uX0jbLLb6I'a4&4u/^]Aeb'/
Xs$!!1YYAm4Hh[fka9!`OFAUR90U!+q*#NTo"r%Y=[2e4jK4J\)*B\rK.!@aZ0h'adkmESXG
bbL#Ul!iF@>4JKlO_PLKsnO^AP*m@#u&(:pf?UHN]A#';]A=4"^n-HUBpY3oc3,?nkM!3rG^k'
H=g?&;WJlMs3PX*5n>@PPe&^5s,A8Yr,jBd!3pc9erB>18<+?&:)dqE"WDZgr>7Dn)j)7q,Y
MVVAW#R)gs?iiB\p7eP#-Shm<a8/:p>)()g=FBVMjD@H"&j&[KUeY'R[PC=MGWU/$N.jO.GI
0eJ*;0pnq0)"=4hoH_fK1)ia(U(d=k8_:,a)qqNUPU7.R9r96K)4R@hAVrWmY*dDO>mItO19
bXjrE1ptm%fg[k%o,LA#%<&KYC/>a?o#SA7P[/:<lA7/:Jm5es>M.SVL7Xd1ER<5\t"_8i@X
#WT0_6%k_oNL^CEdLLYOFm0:P=/_H/D(lhWa"8&Cb%#Xna?%r.&6bSanTIMOEVRfBlI;fbOb
`!^AHgXaNWhCT=cOR`.5Q='E3LRSQh;g$+U>d@Q1W\p!d6;q+3^"g_^S*s^UC9u`^]A<s\F&g
UJ[#_LYqoBB5[^Ut;_ur*/p*^4U5Z)>k!mqbt%95kc]AXhupKmY?dg"SgTjG8h)erU%=J<<G/
769Z<Z0'/<8=Z6t1"li[Z&TAUfR``7mY9$-;6S)YQa\?s&UDY.,6.22Ffh7,\3VdCg<($kZo
)DI:6=*:KfgslN*?2M<G5U*d5>tLUf8gb)2FgR/uUZe_ikC3S>G58n--q!a%m8pIIRrHe5R>
,F/@!HK/tKKd-c0DK81N:.Hks:5aAlUDscqG+taeiDU.J9R0*0m+*;NCd-NS)&#*)ii'"KL4
?M<Wc.O9j/h&)0=0<.g^&qk5I""eZM_em%2Gd/@$>%j+:]Aq"i3QDZq_["PTZ5(Mbe"@-.6P1
4>(!d89oG*TD?]AK!Kc7cK`<cXe&3_:4srqK"]Ak7o2qT1Qu%'02etoBs"]A(_<IT)mnRFToP"`
C=sg'IQ\LIKd#Ya\e+fj,(\j6+^Z4G9+)OYeXLc$QS6ZBB6=9$ReuE#`#8Gp@NrqjRJf$QEQ
W(cWGQ+I#C;!t:b>r]Ap$+N8'3F5mqDDrHO2^Lj0P#16/\AYX;i1`mCr*3@jEQ(-s+h?>&kV4
Od^!LE&KNsopgQ-GZ&\-q^1Nm6>D91ra%^pX%\]AA->Mc/IKh$Q:X,_qY[6HW8PR,ada^0J3c
bt;8A':64"s4emz8OZBBY!QNJ~
]]></IM>
</FineImage>
</Background>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="152" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Top style="1" color="-398635"/>
<Left style="1" color="-398635"/>
<Right style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Left style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Right style="1" color="-398635"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[$#,##0;($#,##0)]]></Format>
<FRFont name="SimSun" style="0" size="72" foreground="-4492536"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-16776961" underline="1"/>
<Background name="ColorBackground" color="-398635"/>
<Border>
<Right style="1" color="-398635"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94d?;ch\MV&`c=_:epV,5Itt3$SHYC/ks7)llAAeQN,d=KI<mb9fWmR"`pG:b*kA,N+0L/Z
mUH;MYl.,h/R,']AkW1UbRse"G/sr&@.F8DQo%R_tULpVuALC&,4q%caiLiT6GS;pZ0,EFlCf
HcC*t[ju.&crlpF?DXM0s\#9(oq[<=4In`>FS(j)pCYjGKe"1#C=&;M2iis`aT/M(=?pPS%?
bC]Ao"Wi#_O8V@%[&oe$n&D`tAj*Q)iEr]A?XA.B&'>KcOqtn^C,iY;:c;ZD\Fo-,n,o]A1tl=N
Tb$@Uh-f$SPb?D(37TEHXrTuJ>D*)@iGeiIQsE?.X'=]Ah-W:,dGP\om@9nFTKBoO@[?OhU>&
&?0tp`MnGokK>3E(#JbeEpq9]A[^:M1gYLr''=4&fg74Mb7J'UmGq0&)W[SaaMFS8%4"o-"\N
Fa!pi=Yu,]A1NT:#[igQC'8p)BE)1<<-9%jg`a.*c,$n]Atu3lClG)g`[AI>/N=GiLX<bV1UeJ
[ZEC1@O).AD96Fb^P3_sf)j1L?[87L@g<XRjRItifG:9HT+1$$PC^6@ic<Q3.k@\N`hRL.c`
pea0\g(*F@P#DP=O^unmEq&kKXUWg\>9[i!&kPS0HC%RQR]AGRT9o)[l`\e\\Y4JL10)kk&@J
m<+]A:glo[Q=7XNXYGb3(d$/KrC7Xg@iOL"J25Z@:YsD%!W-%ZC#2bkqOgB1U/bD1Hg^41S?J
c-OhBFK6\W(%,Z=irko`['<NCcAcKK3gO!9PjPP9#%a'GL#k]A4RoA84Clsi\Q#<J(lr<p8A9
@rGf#Kl'2APOJ.I5S7hLDB-SJG@F*[2OuF;\.:n/X4iO`pp4lR/0[iVtu`)`33uUt:L:k`Le
4?5.bJ0CX6>dYd:K^e,J$6eMoT^A(X.p/'hkei(t."H>od%H4s'GU4OHf$_uaZMaY&Jn4fN)
"dZdl".Jnh;F.B$Ym&,DX%kZ`H5"DC2R)lmn5&QP/`m_LB??b\Rrn[`7+-T`d&/6/X/Y2r<-
[]ASYCU1?@@dWk0gM`ZcdRSnujul'[#bUoTp:C:=*'1l6tBOr2]A>1p/'a;olYH-Oj,]AdUXNj8
B_4-_`8=;-,LpKP:_MAqAk`<.A:"Z@5R&(Y(;0BHk@O/5s1A6p/@b=aCTc+B4=gF%I\#'HVe
i^YjJd^`@op3d6-Vnp6+pjVpF#18M0VC8@grJ&b.*hh;Q<Q;9g!gH[$u7,2"jlkg<m7`nR5b
NcHiLMSLM&Bh=q0q=cVn'KL?f1f0I?N"C9X7cbK)+%.>)(GsPl`\FfXH;tcR7]A]AsnD/ffS!K
j`sr<LN<%4qiD8<"Jp)jB/=Ae"@fpnF5]A`>OX(7ormi<"KstTS+8-FK9-Mc/RYT#=/=NH>U6
l+ion]ArgIj#4F2G%*7XH@[_17^7e]A)\Z3I%3pa_-`[^ARBFkrrEYep?kcj0sB4*fu'e4bnjY
l]AkI>Rr[jQUU<MKE3S`]Aq3MPJP&Q,K9g3Sh:>EW5T=?a]A#S`7oREOO>/@/Ypma1AtKD&qa9;
[Y_[),1Y<mZLU(pn`caEN"Yhp!M%[YDg4f;YI8N_2(&$/U,W;-4S<bs/r:J!M[>2oo1;ZsAX
FSj!j9_0;8$,acbN;3n`X8uJ'&fo/rnd\^Yo_%.,O$B]AImS\Rl&9P&>(dPZ-+?lJrK[\BP0<
?&ugVYMR?W72E"q3WZ`T(IX*nA6%K8S&Gl%2F8[*g2>B=W#.>Ud"!*PW;+t!Z3Ng9cf#9r5D
3`]A;@reTuC#iDo2!dBY4/ZU(EJRTZ3:G*(NHg)-uA%`"mX^3B'\%[T(*rK6;@Y^j"I.Ab)rJ
Xrt]AYJrZJq.#,a)bba(sQ+c^LjVrIL]A?@gK)WNnaYBP(kL>N*mZe.?hJ3T]Ag2sQTdeoU2_/i
']A?J]AeGE]AHREjbmsMa\-QiAR31jj2?s(2g_?c`(YaZXCY:<u)(hT;7adbF'Xqo7T%TEloo@E
hfNBo3<]AU:#"43#cK3Xm7F1N%rN_-;$qJEk&5R70k&2ZHUT46TV50`Nkh<GNVgg<?Vqsmu[i
iX):BVQaJH(Ig0MYH4Z,0A(^p(*)S,?-,A]AX.T`f9jD70[4]AMOfMCq`o8N2.T66$WM53.6>e
1^<gSj*o69dH5q(9t:.U_8kk![.!U*\R7^EN.AhKqr1P]A(SQlfG&OeC]ABd!g%c1g0Q^_p&O'
Hhm^prBen9p&\=Ma:(IPM`B4\r(t<MIkPQhJGG@\f\"g'FKHc_fC.R#E[HC*l4?"@0C&Hdf"
@&#(RV44'XO#3's<TlZIPI+jJ$]Ah4m/9c-c@INq4=c$.p-@,c8@3L9Qt2_3:[n2J]Agdbrkpg
!?WrtZRKdcN67logVH-#39&IeF=q$k`iD&r0;jFJpC53a<oSZpNL+84E;12IS-#(>5bPKh)3
=2\o(QVH)0iahlm!s'*^&LcYhU$_[CAZ?Hob,6EFa@=5"\$O9:J\"WfD._ofdg3Kj7WclFKP
QsHcKrRHE&5)+VAauLbbi+$o:A>6Qs(IZXk0J9^P#:WA3Fbog^R&(1#_?qjFn)rFX+L.QBt(
Qd\Eh#:B4^=KZBs6=OZ?=Q4e/]ALa:UFS+>S&I:sfHUg*_P8fL6BA=EO5sjX+P,W7(e`"oCh,
VO^&0:HKa/l%o?$qV_`JlQDd[7&0;iXbhMhS^<iCZ0Tb0:L%M="Nf:&Dod:,cSY@%OT]A3mAL
l-9@##Y[!<r*I8=7W]AKn*PC,;E3f7[*22@C?A\&A#[n__'3>mXM:.qu$]A$AR$f"X'-V28pE?
n9R`0\'[.+0s0kgr'#_F/oi/8dJ.(-B([gJ/rJsBqMGpe0VD8N8iVTI@D4R?5#\7FoU0W%3s
^!*CtiOF40eCeu/"\H`k)b-`!DGA*E?o>ouEqQTO)<S`TEsJ7]A\bmEMC3Y?IBVG(]A=[J&<`&
[6D:0=3=M4PmiS1"jMqNUDSkER4A.rXk%C3Ash*Y;d>i@)!MmFA7o6ZCSZqe6>+[c_ceUco4
1,sP_moWKB1]Ar`FW3%hckM7UI#1tn%Gg(m%5>i1tG2/d^3RC#+`0_?;inEO=2-XeEe[1Z7Ye
,ICJL\(<7CGOlk_3:^p>\\8#6!CIG^:a_C78f*KK=%i=k@i0_@jV\rYCAq%,2;X;Df>8X;Hl
LS4D!,-<(/m[rW,cDlnZdE4UOiB.GPO8<j`SO'oB-A??OWhI=d+'nXr6^d4S!<2U292`Ol<.
UDE)@fB,Eg?`cj$D:SE%uR\3d=oqPtP:ZTGNsOJRk-'Et4&8:-S^$Nr5P"2s/Q@NlY/Gl(=K
ITr(d=-[cOq>d]A_bgO1$DoXKfY\mEJ=k$#I@ZIn$<-i@P0Ae2Fm%H_TFMmq@G#qE!,-*LhER
i?GhODjc=<8O[=4K6(46\b)0.s_\_itG.dG1_-e;I7/k&#-(-,r>P'utXr5.dJ^JlO4n-O(U
JfQELZ,+5McjK`hL)j\gC8)^^B=6JHHK,f3QTFcUN,_+[46*0:MSKGkHC0h^($W5Sn#&TT(B
26ms`e)3:-kaOpK!Sa5+<>$n8jC2=>s&h69G:Yn'O7Q@%@8T7/FLLgYsaY*WLfYY]A;:!a5*X
/(2Mr.ula+<uNb3H<ZLij4Cl2`/CQL0ECp]AG=\'<7\bV*L^k'g=UrM!3CeDY;0@>R1Md<JS&
\,;mU]AS*uD(*cXkItC<#:\u#2IkS5UA1nBW;a#/WSj]A=9iJ_'o7g>4@%LG*qITV,TV`3>U\h
E.`AA\VHLs&^IjA'9@r-Y]AQW#>+Ap39-h9sP[neNqnKE2Qlh@uFj1YcC1GSnq06_8to&]Ags%
anW_1V7"2THg8,I)o5kK6g@tu4^#b=K]A3O!aI$&b^Uc2i%PKEc*.a.h.fR.R\+1[7#'H:)lF
qj&=qr>s/cWk32.jTPtfKj^+/pGAe#Pni&fbEtcnYfZ(V.2%b&uBJ/k8LDTh>&RfE^O9MAiU
T"O0)qM#]AVd>9(_j]A9/%A$,ZSJs*M[Thd*mC0?oRO:BqN''G/G.KN-<tgk+/]Ad0+aT^qNdH#
3dGK#m.`]A[N``DUj=k/<.$etB480aA4,[:CPCp4KD'S6@!lm;cUX*\IrN./bC`latU/1[2ni
f<02Z<9Kd'O,m8lj"Ss,9m\.Lp(9Y;bLZIg">0DuLRmhf&,"0A_sopChFTbd8B6+lBq^kZ9\
tgHQ1?A2"U,(4EIl?S_5(7=^4`C=.-V6jHo@10#NR'0-]A0>%Mj$q_!edkKcD8"XdisrQiM%T
lO2fK<$+979Bt#"!RKg77a>o^kLO:qrJc0(F,B"U7t))?J"+O'%B$:-ZF+HSr((,#`+&Nj`V
dm^$4Z8?k'uCn:2T!@S`4.:(T\@L@&ZjN>)JJnmuX==+dmjel[aYB>u241beS&]AN!Ms!1a,@
Hfqm1Nl5GSd".&EOc6f4bidqm)$lUtlTN-a[Pns%@oa`+[p%Yq&8c)2T!daJN^#kR]Apkgb:d
jc"on<WOFdsT_[!u9WKu^73(4Kh"D!R4^%HASN?WP)*$j?;M<q$"eLE$eD74l3<0JTGUnF/q
@)+gdb,p3nMWtS<o8Pir-k/2$8J^+"!ln:6"mLe[h%-_\5F6\P@f]AAM_H`$cK%t4dgG#ln-$
k39-5*(Sq1k+4_IsC@eTaMr%h!DT$!IA#F&-,Tm_<oY/2G'?f$R1u.]AEF=i.5#%lN&`/(o$n
Gi-:NPjd'l`oT0/i)2i4hu;WAZ7MM!BCD]A[5R8"e4d7hqaG&U/1E5+njN]And/ZVZFf$@dt34
F8:b/PTUAK4"E:?MMOG)M<t\-d_b6g#,CESjr;Worc%!mOLFqZNFIDS;G`UFjV@d/87<l`A.
(YUa)5oOJD)2*4#R72_*"Ea:*U[<UOoacD*0:-!&/j*+fY:KVZQufXS^+T#.V*_nk%kP%*#3
61DmG:KB<9E)Q[,;Tbb=tOD"Vuo:UFM@&iKWVO1Xcc@>/C]ALZQ0\aeK7fUi6C@-Qt?7he\6^
@YV[o%DX_#$KYVg*8CjYdbr-/mT#o+W@_(_6XUQ`c\f`jBHbQ1.#FPeT!G."`T(b+Q,V1%"Q
3&oUh7,j=Fri7LR:dA@Baj(&k.pPsA5!$G-%Xkshj]A)Al#Vm.\aah[/QmZi/732dqd!MWrOi
%,(ao>LpT(#k1.Vcda[=8EaG0<L+C/>5[<P+kJfJ+3*L=]AF<-l.LN@e0i^_/H*Eq]AUf]A";XK
`&j'ApIK2sn*)=tqXX/O8gc1\27lif[q-i2V>[JBGuYknfqsC5rgT)F5kc&quR+Y8':+&Zk&
rg^_U<?#^0`9#>KNUW"#sY1E48r.)hVg4)3Eh*:'T)o<NZ*Z=>GMp"a]A$).NriXG<980or1'
%H>eYR-\s?U.>oF1CM:kGpR6J<$1h_n<4ZQ3A2kAuZQUa_q:I*:?G^06BJ;r@e'S)4K&A`j6
.<n^.U9;)q]ANR"Wa]AqPQqHU]Ae=2i,[mO0]A]AR@31[-@PI-&oX##p`hgla5s+NUg$Dn/"P:eXl
JYQ]As;g&'%ZiRRY^'0H@bWRD9[Gc0C_WQ]A&np:@XL(CsWo<Hkn!TQDqN/rDIcs7R.V+*[)^&
o-P<l9b(-or#TJ]AE+T!dRU!EI!GD':5:"3oc#^JK*m<8F/.p4NIMF-UJ_2Fd#X3!=mDB=E)8
"\?9%1P:<,sq)_a\77^a'YBR\tK]AZDS:#'UKkauf325_U03<fp5"NU,PV^?E`M?PlkQjjm?T
hC]A7nJL1u)I,Ug0Ih`):fHDrrOuT6,42Rr1gOKPP$#g.QObI3.bbKO"uXj/+(4.YHVeEM<A9
6(0K]Aj+-J=9mm3ndFY;\.9b4K$YW3IV4!(<[L943oU@=[s]AOfZY)#Fd(^qp7HkG'5g77B=4@
@b0+%aH[Fgd_\S%772Db,c*)b0NFO)i;.=6T+g5]A`*r+S=F[5@]AYP))IE'8u']ApqsbP=7q-P
?h?S<515(;V'80D(&%pSObd^<YU,1."l#$+L=bi>*Q>FuGr0Up924V<L.>B66C@?R]A7W"''a
ac=+">!uW8]A!coBNUC(87AqoEI8p`5LdZ_I0/,uOR\/Y^Dd1*2)+GJC,QPBG7Ng=&q(i9s-@
d]An@h3St4M[#cQs#5,kYM;8MQ,Xa/)3Y9eL:G2H#7Z!*4%f2oI_QV`qj$Uq>m0?L62B,,A=u
Yr*Dc9Lqb$n9X9`fus448TpLXSHg]A%3Q~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="480" height="540"/>
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
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.CustomPlot">
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
<DefaultAttr class="com.fr.chart.chartglyph.CustomAttr">
<CustomAttr>
<ConditionAttr name=""/>
<ctattr renderer="1"/>
</CustomAttr>
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
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="season"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1" animateType="none"/>
<linkType type="0"/>
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
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Attr>
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
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="0"/>
<newLineColor mainGridColor="-4144960" lineColor="-5197648"/>
<AxisPosition value="4"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=0"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
</ValueAxis>
</secondAxis>
<CateAttr isStacked="false"/>
<CustomTypeConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.CustomAttr">
<CustomAttr>
<ConditionAttr name="">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</DefaultAttr>
<ConditionAttrList>
<List index="0">
<CustomAttr>
<ConditionAttr name="条件属性01">
<AttrList>
<Attr class="com.fr.chart.base.AttrBarSeries">
<AttrBarSeries>
<Attr seriesOverlapPercent="-0.25" categoryIntervalPercent="1.0" axisPosition="LEFT"/>
</AttrBarSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[1]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
</List>
<List index="1">
<CustomAttr>
<ConditionAttr name="条件属性11">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="NullMarker" axisPosition="RIGHT"/>
</AttrLineSeries>
</Attr>
</AttrList>
<Condition class="com.fr.data.condition.ListCondition">
<JoinCondition join="0">
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[系列序号]]></CNAME>
<Compare op="0">
<O t="I">
<![CDATA[2]]></O>
</Compare>
</Condition>
</JoinCondition>
</Condition>
</ConditionAttr>
<ctattr renderer="2"/>
</CustomAttr>
</List>
</ConditionAttrList>
</ConditionCollection>
</CustomTypeConditionCollection>
</CategoryPlot>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="cate" valueName="sum" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds3]]></Name>
</TableData>
<CategoryName value="season"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="480" y="0" width="480" height="270"/>
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
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.PiePlot">
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
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}${PERCENT}"/>
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
<ConditionAttr name="">
<AttrList>
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
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="country" valueName="sum" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="480" y="0" width="480" height="270"/>
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
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.MeterPlot">
<MeterPlot>
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
<MeterStyle>
<Attr meterAngle="180" maxArrowAngle="180" units="元" order="0" designType="0" tickLabelsVisible="true" dialShape="180" isShowTitle="true" meterType="0" startValue="=0" endValue="=180" tickSize="=20.0"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft Yahei" style="0" size="80"/>
</Attr>
</TextAttr>
<valueTextAttr>
<TextAttr>
<Attr alignText="0">
<FRFont name="Century Gothic" style="1" size="144" foreground="-11683767"/>
</Attr>
</TextAttr>
</valueTextAttr>
<unitTextAttr>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft Yahei" style="0" size="96"/>
</Attr>
</TextAttr>
</unitTextAttr>
<IntervalList>
<MeterInterval label="分段区域" backgroudColor="-1620162" beginValue="=0" endValue="=60"/>
<MeterInterval label="分段区域" backgroudColor="-208375" beginValue="=60" endValue="=120"/>
<MeterInterval label="分段区域" backgroudColor="-11683767" beginValue="=120" endValue="=180"/>
</IntervalList>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
</MeterStyle>
</MeterPlot>
</Plot>
<ChartDefinition>
<MeterTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<MeterTable201109 meterType="1" name="type" value="money1"/>
</MeterTableDefinition>
</ChartDefinition>
</Chart>
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
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="88"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.Bar2DPlot">
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
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="72"/>
</Attr>
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
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
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
<Attr alignText="0">
<FRFont name="Century Gothic" style="0" size="72"/>
</Attr>
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
</Plot>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="480" y="270" width="480" height="270"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="chart0"/>
<Widget widgetName="chart1"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="683fcadc-68b6-41ea-94b7-3e318f55d952"/>
</TemplateIdAttMark>
</Form>
