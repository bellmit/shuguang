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
<![CDATA[SELECT * FROM 大陆车辆销售]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="car"/>
<O>
<![CDATA[比亚迪]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM TOP10车型 where 车型='${car}']]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds3" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 车型销售数据]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds4" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="cata"/>
<O>
<![CDATA[SUV]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 车型销售数据 where 种类='${cata}']]></Query>
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
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[车辆销售总情况]]></O>
<FRFont name="微软雅黑" style="0" size="96"/>
<Position pos="2"/>
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
<C c="8" r="0" s="0">
<O>
<![CDATA[总量：]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="0" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量(万辆)"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$+"万辆"]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="12" rs="10">
<O t="CC">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="-1" seriesDragEnable="true" plotStyle="5" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${SERIES}${BR}${CATEGORY}${BR}${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
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
<ConditionAttrList>
<List index="0">
<CustomAttr>
<ConditionAttr name="条件属性1">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-6697729"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
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
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
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
<ConditionAttr name="系列设置1">
<AttrList>
<Attr class="com.fr.chart.base.AttrAreaSeries">
<AttrAreaSeries>
<Attr isCurve="false" markerType="NullMarker" axisPosition="RIGHT"/>
</AttrAreaSeries>
</Attr>
</AttrList>
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
</ConditionAttr>
<ctattr renderer="6"/>
</CustomAttr>
</List>
<List index="1">
<CustomAttr>
<ConditionAttr name="系列设置2">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="DiamondFilledMarker" axisPosition="LEFT"/>
</AttrLineSeries>
</Attr>
</AttrList>
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
<OneValueCDDefinition seriesName="年份" valueName="销量(万辆)" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="月份"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
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
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-13408513"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[mC[^,P^V:NhGmW+6C/ueEj^iX9?<Ed8BDWW/(q3EL6-haKLU[$2n"sUKabsb,*I)k4pI3nm+
X3&`dH++qnB9"^7+B`h9YD4IXaN[i,bLQB0D:LE?+H-F.OI-h&C+QN80A=p?Z3g^TOTGB?rc
_Sisgs*]AsGXft'iSIErt`4mK&i#2VqXeVB`c&q'8T%t?eZqV*ad_i<4PYN7R)/+ZED1#+bdl
29L=B1rJ>%\#loYWm@-*5/08570()D(n$*JeV3M^:TEd1`j4LriSr'B``^VM;QWWr+oqiFIS
g_hFhd[')i<1]Al67b\"[>8dBL5.kp#6;7j_?G&XZt18gR>5WEmqX3_5$bP*4J&K7SrsHs>J3
;JK>:(\Q%hCMV=p_cngk+Q:l9kq0DJ[;X1.nCa]AVFkp]A;)F3,)WV'GR2oVFFX'Rsrr/IA5J`
["?,Q@1s26_?QZi;MRr?!$d66qruD4GJu@944rc3d]A=Hf6G*r8,^t#4U&LTO-pS7^aZIjt7I
7N_.b,.&bSXElTSKS]A(iuV`W+mc"[5.dRG*7^@g<r.PSoe@,s_&l6J)_&-%T*S8ER5BDYek&
"B6e<&SG)##CL_#S0Z^X+9</b/r9"Cf,#)#'5c?!oV!9,@c<a!_7XIJ]A2jYj`<uDiL7"59.h
G"=;pa15lpNlVC_]Am2]Ahi,ei2icfWs3*)!Uj,B:eFnaODseoel]AYoN0%g*6PMuUNB($Z<X\=
iAh1U-,8pYJ]AeolNs0+"gGSr6i)D`-e$l;[C9GaMZ=$bCi7.`pG$+iI@e>oC%J7_tJ?sM+9q
]A)^b@%Lc:DQL4g```gYZHZ@m1R,`8JIYPiR%gLjC*("Wn!cYVF3RJB2UF8TPM.3,Q\f%8I!-
+7WmL%^4(2T4!>Gq:5WS?9hd4'&-(>(d<!%$A_t3I-_Ra=aMbiS^9_p1@HX)7_?"JQj<bMIq
o4JpqBiT`5:3Z:dpHVr!h2Xu-_/jf<=%1jKTm=NO*I7lc$N<;?f828NerDHp#D16EhJ6(OYK
=Eamd&7@8u_7H#Sp)MMJj``2nENh:n&HJK*aeY>.UpTB<r?rG@Q$JK#1G':Jf]A5R3I0L7>@=
D6,U>,*BR$E#^.9%2(!g_)g[X@Ah_$J<u^D!.]A`_%rHuD>QPfJck*Xd5;9Kt]Aj4=2Vr#TD=h
dhq%,<b.p4K/FEUP<^<p(*tC?E9@M6lda87MX\Y6+!$,M_0W9a/RTR)`b)$jmEhNhOu:.S+^
?MNnPo$>aNiGHET7(eEsYS":Ts3I'hj&p^$m;`uFr<-UHnbqP3.%Y)>IR#a8s_YUb1CB#[fa
EEL3QZ.>_!D9a"3e.(Zk:ocH3T]Al%DQ-rnmG1Zgpu_uYbTKQ@R&mkUeR&ARIuHJik2*\]AppL
qd]ATse+f6\Bfr!YcmH7t0r$D\m$ILDZM<.[f)L'O6a.d7(lC)CdQVXFQcp93G*K(d"240I)7
di=+G!.]Ah4?(BnI-'P6;LYG?#DUi.;bf%LjU'p)i>:cC',lP%k(SRr,Bg)&P`r.UmI^-]AX7)
POR6p\`&a8dfgVH!s:fdjf[5#9@&"W#BZ!XW6!Mof.OoPY,?DpXUpdO:5d2k4]ASfN`0Ib;,>
<+Lg(Ga(4#6Z:IcA$%r7+*nl!9[lF#O/gihtp:@+u%DJh@<;8FUjDE)j/eS$kS=<tU<aqpT=
KQA@jTukR1Pg%5rKi!UZ*8r+81r^`)ros7cAi3HA%09:+f=1"11aV</jr:Qn9k@sRkI8#UX$
&m)P!?+g`T)-?'0l'j.]A4-.TXKcqqn@e]AG*i*LST]A\Z0bnRmJ3)<JATC'!.M+;GbQ=+opHq5
b2o-6?k&+K``Z&ad;sd"faSZ"+V.K^r`PC8JT]A&C)S5$M`2:Q,7Q2W--3LC/Ap^<>W"+GOCF
fCSid.tHL9.M3J^9r"!#0J9"9Nn&VX5iPSAeeeXRQnMQXo(c;Nk_pYX9kM:H9.n$sV>P8W("
2Du'O`eTg=To<b+`hc,Hr%nsHb02iLhf!7"s`N"ZRZSERIngOtNETnLV+pe#<'+?1(`@UU^B
(Xn>h#r$;`@U0FdKZ63poaa%N98Ma\W?sVf&6<UYkQHGlSo8=b141Wh[]AR,!9QPUkWH\[>ku
3s[tJ[tF&*/\994VLT%^H^'SlJ91B)O."()'[V8=D&I1P]AmZelO_h_]Ad)LC$A,f6_uHXIDDj
YZotJ[RUn7;qSGV12QDS61A<o7L\-@rpYsur+u8jZW]AjOUn$;VF04a69P`gHnW%=eC1.b@DD
gj_nQALT"Z88_0"XkcNQoo3pt+UURQm\P1sMW'Y.Aid\[4(iJ.#:ZH1RTD$JtV%/L(qVaLkG
--Lpd94I9KfV#F>&QI@@j`H_"^W7#NfA'p!G2Z8u5'b#6XYG3HcdrWZq`8P3@-[k:!bUP5=6
Wk.*^*Z7:PW6U=RT2I(L_I6OQY+k^c',K.9'Gs^m[J5s-Up$8FDko\?Nj5skR<h^=^Bje+&)
[Z^pu86`g&KX1O>/k/_[*[]A3b!Jc@/^P.l"*s*+*"M+fUF0?(d^k9*+/>VD)n*DIau!:fU\M
cE?dBGr%i2%F9Uf,8sim2=">eLEq.S"DNo<G!MML$sV?2"1sfYh$MH8UW)B]A[Ub*?mfdJ+Cb
H32L]AH.5fqkVA9I.?m'bL*+C*N%29Y0KsH[cE9#Gc)cpTA$[:L2j[J'DT["TG\_]A@aGgUQ-6
I+R8FmBU8q,hF/1c6/o>)&+A^WrKS"!/4VS8U@7C8J.EaKmZa^)*mIsLa=`BSq*W,bCMla/o
_0taA]A`sO."'g"k;)<+ctss83Q>^<dtu)FPpL-@o;eO_*<cbMG;T$]ANu+%;gBG`"i::WPC12
bmcK3^CWR2SbEPbr1S_BD`Wq0BWVi-WSNpeOje_'qPmD&*O7Sn/1RDq,YA=WlBp[/=Z#oa>H
G>Jd_cTJZt8.ORq%q!`<9)W8q9/]AKmq`--R2:0Zd>>7pI8e>)M6Ig=K<>'O[+*gj`LLIseiN
'iWLPMnufaePJS-ss7r`I2`e?K^?*Tq.,Rr!&8;&lroZaLTpp]AVZQ_<0aD<>T3LMD!X)c$lJ
84!uNs"9m[dQLOKKI7r:m^dL)aO:(jAk8-NE+m1QVn`LUM)=2j44JTD<a-,N)#6UG#%8<&!]A
;.=-6pKZD"P^3ESJCpP+9W45+#-"7DDoD4n[[g7o9k[ncbd6P/>2<TkN0.!D1h1/(Ub7>B*]A
uU-RGH7d:80s4.DkWK.7TXFJkKZIf!#NP'oo2bpumLrZi6UQ8']A"Z.4_N;X.Dj_@:K\bH584
7k]A_^Q9=6NP3i9#S[.F8rn"G/TC=!LHd-G,2Ef[Sc$0645C2rpV8l9b@$Qf&(ViQ_;`A[@GV
j>%pp63TgbJg76@6")D=?ILV3guRl]A]AhX)^t%ldWY>PU+8j"CUM[I(QnS65.7E2CL!3Q89,Y
"dO\I?%TpLL2ca,pLkanJ8n+;UR*eCfU.Ltag63FK^/Dr`OR\A/0"nJklfJoO?6XBt\LlNVD
<R!tg=GE=7FkO!!*G0e6TdInG0K?3qQ6-b!H7>Qnu1rBQ>[5//q#C0Z;.nUP9.41+6-C)Yd;
Ge:<bOfP)bOP^GC/p$QK!ZIU01VfIW5X*O$lbS3P*4$&A[@Sbf!N&:@kQdEe85a.:DM5-$;B
(7u%_#'SQ0?`/R`NWiZ$5#E*(DtQ:FRB&1+%+?1Ffhqa:_6#P,B(P&>*_X7m9fuBK3o6T2p>
ETX]A53#*QK:F0DfnkL9.Gr08#s+c&d%+sV\>nKfaG!9!rU:a^m=`_>%_?'ShGcE4uV<"-;dl
4(:r(-dM"Tsb+%NRXRWD0=usi<ei6_tEqV"4BemF4DDZQ`m/T$lVGloAZ]Af!`"o\;DP[(.-M
[>7R<,5AN2O=)QOCS/SIjYo;%p^tOW[tO1]Ai!-8(0=H"@ts4ULPju\)9PFn/Mc;ehD[m!i9h
2qpg,5&1>0,H6q832X4l$aGTUPb>;mUco0&ltE9^94.RG9B\(9a%a'2!FaWd_QY:V+h=-+_-
/^4pZHcG@NnkH]A\lYOOO;3'_RCaM$;R?u#D4,bi*e2G&[/+G&"2/Ke%50>pqGn05#2d=Qu3t
gJ\SVdT[j_fM8Xi]AXe&@^?,[3ac6+kB@Ebi)q#bhD[HJlN'co=^%YAFAP;BjjU#=c(5#I\BM
86`1o:Gn,#m/u##*]AN["oifS;A/dJUe)kj=3jd'?Bk+tuGq7,M.[;-"dQB0f.L=nc"g-.H6Y
-X!ADQ^<o*r$`M`W!^\,]A<H=d<MM52L0&ZYo>,aBT\.;/RNA"DB@4\ns:'_(4X(6P8m[RC]AA
GN3;NtJf$NJ)(0GTG()pnV!6oRk-6HJP2:cPQYa=g8Gsu+VKP@&YV9\tTp?fei%Y^c>kiPT!
G)7*n[Cet4cCnEM6n.PQDRFj#7FJGMWmj[W5q%:%C:%HtU4f.['/e?B,RD\$[1,B?_oL)5Nl
*NrVn@<]Afb=._YVEB:kHprW]A\:bT#MT]AR,,Wf>q&4J17D/M>TPjA>C_]Aa)A0bd&8'#\=n"<,
oB#=83>o72C6YEimkJT-^6I9tU=\YPu)+'!SJc$'8/u=%lkT"['eS08*OM)!VTE*oV+);ei\
q]AOgD?CJOEGP*n)(D%llAHH3Hifg"BTAhQUu`gjrOX_L#7'R0l8FcCk0L-Coo(,-kYokSGLC
.od-]AHo#X4*>]A\-TpridNV*^e$f(8#HJV08=I0B$OMN[`&H)2j[<H2@!WNnL/5\"PC=Q-ct2
@$EImeYI<[QEV8AMYYM:<lsT[#n(pfN<.am#W1cu_&I[DQZ=Ek3aG`9&9YoQ[`H`1g-R<`pL
_?;3dMd<ooo(gIoo<YF&BHZaqNH87C&6$Um/:3b?5YG-"9#PLScTi\j.d93b9mFQRCd3fn]AH
7jqV]AdoFs)coXjI%]Ah9$[*k!Fp,ts2Jc9K8nA8i:R$90!b'B,P?BA:/t8rMH$PSTYX$/&]At2
SVdu`]A4f'_GkHej)7+tE#h)&Zh/W)<uh>TQ?!^n,h>hk'I]AA=\/rf+1es6^(5(Qg+8Bu'hQ>
qsp'd`U_SGog*Xf6\A9*b6*kM?BEka&P:/)C=,qcbXi!4,_,g@0(m5fTomn*1[DT'P[T\d3q
j_;c4Xl=G?'Bc3<qYb1+8S4n;'UR$/1_4>9nf^srYS!-uiA)gS\Fs5O<`EJ@Do+QFG#O^9*,
:eA*(5#Rms2H`Jm(UsfBY9)?()"pEoQr.jB"KX5(T[<8amNCki$12kM\T#HPTYFOGGoJCDr%
7aAtHg0Cb0UKYiigA8H;%gMe^l5f0<=eU%uk>L\`diWKH,m%4[7$^p[JP3:'udkiCZ%8KpW8
EhFpV]A9fn+IpiFqKa<eRYdCK1-]Au,$>59u5+dXR;X)Du?;I2pknfa&r5h?(S?Gjoe=<7g]AD&
#HEk(:%a8Jr%>A6qu^CWIdc-O>]A;utkI$qtH)f-8ZBY_@6[)T_6@+PX@NI4!j)/T7$)%'k>3
WfUNm845*bq:_']A=Pum]AL#P?#`K05kBh5lBmmf90PcW`UJl=GhW";L%>'`%WV.-U]AZZ:Y#",
OB!#-`H,?MDSFH$r<h=5R_6oQ1udXsEhC`?@H6Kk.j"VE3qL<lM]A_ZE)JnE72"T:cQ2>lI]Ah
&dc2q*A(^Xg4[E&&ZRCH=Xac!l;3l16b9pmheB_I2Ia0is+V+=MaCa>-+reL[AC*>`UX^N`B
"Zdq(7+7Ob0+/)r"W-Ur2#)s<#Yo6RqoI_^`2/Vp^$_iJ\"`W.?`9`MTbOY=`h[*Vc*t0Z=,
I(gL*mK(Dd$d9kJ]A-V!JoZHu.(8p<A@;MUh$qI\,WW=gd&7,!tb,8DbT\!Nfd^)26Sg7Mt.`
^+juJ&LWdUqHVeNU6E==!_W1K8g!uaD5V(j)a_7(7YhT3FV12?E;Z47[^Fl.M=`i)`bUY>?5
Zl!/\=hG((EO*6%a1fT0S-#HpVDhgiD`Ql/KDoq((+sb,2",_F>`9midH(^)JkZE-sm:>Tt4
<^*r(UC`h?-8UcXL55-AF6NPotc^RXOefbRV(%IO^MdWFF@n*$J,nA8TDC$o:46SX#h%WU2o
diU\Qd`6*8U+qAlSTkSjcoDJ1*aAm2uMJ"$+Iub@q'HS=?Cs![D!LBC)J+1Mu-8c`C\&6U7"
h:S3227(!BsQg#:Z)&$8Z%8m+(&Q[r&!9oADR'K&1ap>h^2X:hVX4263M`1`j7)l$Ooa;:ZR
-d64a.62Sil8o@ob1s-MM;_\=a>!KXG3I,.HQp_3h*uX"ek=N?q\&C*3u@[dThT,a:@?C'`h
St^8,g0*?V4>rJi<2^D'Kh!o_&9WGqgskg#khO.=\s*"c$tCE%d)1bVA;P/K9<nl;/9+5P.(
r@u8*e79VSRmGHYNlj!q4P`UlQYT=%7=1W@MdX=/:]ANl@"5Moo,;P>nn<rO)e(<7tDH&5>8E
ZjYAh5;IO:K"Xbk7C>2$hLsO,+DcEc_Q(I/R1D<-T780?A(IbDj2^t)LRjZ?"f;Jek?hOqs_
PSP2M3JZjp;G?,_t`"G*fj4`pf'd]A3')`U:&F+Q6j]Aq"7i]A.`SSJl2q?$RXqs0Z=jYX8.PSH
fHSf&MmUKs>,_D3XkT7=;S$D*bP/inau2BCV"FDLi8n]AboknnP#kZg"53m0o#jdhZg:C^aq7
u.e@pen=*5n+<.<ksrB"k(64LM3@'5RT$;8ed@F)%R4Pf]A<BVA'7f6TA5Pc)4k0^#XV@<Y%R
HgOpl62qI:)YKGK[jp0s+f,MQg?eJf6;M'WJ8,:-c6B36p>.8eU*/7Dh=lZK+;RApp%O:#XD
/@_fjgmVh-Df<0:rBe+k]A(q?\_aG*4$Q9NpQqX$X8PSDX7sVdB4Cd]AGoC=)Y!YKTq'*Kr`nk
=INK7\QAN'!l-G'[ZgK(U,1Y\c%=%t)>Pa;Oqpm\Z46bbgSUIsrG9I71%5Frco8HTTsb8e4@
WD&8u$Vq)=i(YqE+.'_5"Ku4RB`GOH/-=EC*;.?Y<IVGQ-B6h`.suFdYq,'MH8-bLGSWP0hH
`7)8Bl1p/bGmp.:l'MLJ=]A<d7;,iLsYn%S'.)`CH5#-"P=IUo3##^"(B(.*0Oti:V[^N)5a/
XF"XuR6n,##9/Jm.DKP:.[,N25#D/7-=GGTU<JE?PhY5<r<etjV&LfN>[u[e@OA^t,COD279
B29bH7jN-Gi_Z6H>"]AUHsB]A[,<Pu]A^=#,$3b-^Mf_mH&:#IP>1ZML53P)RSR@QuU92Y(iIrh
o?T6N#3JOF2X1.B`QRHSOCOH^p;ElU0q6@/\[Mj.uKctIOTXF4.8Tcr]AVEDL1Ff+N`ndFa1g
Rjt<_^ale!r9kW\ZS8*k.V8mn1LmZ,Nhe5a*$)LMd$9N)RcXoVK&ehB9J82tnQ[Ej)>E\3Te
[iSh-eYu,B/K#@4bAuVMPU)9`A[.RG`MQ@SKeD^%KHM]AsA5X:UiY*-:&#,M@*=Gj\-ckdnpX
[@tDjI#c0=kbNfP@DP\j(_]A\i&[;KeTO4u5EjK*jDYQjsKVZG4MA]AX7A"h]Ao$#*-GjU?]A`]AK
jLJ1fL"8#rVr9CC5Gnq<g9MR3>5Z@WK::K]A!>C6h@``F:5E:i@HjD17R:0d*l&$&MLLj#(B(
'J"pmi,bc\/JNg_;$f7nC98e8&#GIcR;^Bf_7s0h:,/">?'s&4?Jb=&(M>2B!1qb&d,MB8kD
&#P2N<VkWEWVnH<W#MQKV3`/VU-(`-YIaiMQS2riZGtijHA+]A:oLCNc<5SYdQ")1VM?hOd$_
QJt,);f2E962\nGnuJLONV\cU55ko7YK;d+PstRsKa%0fWKZ0X,"-:9"Mp125rEKM7B/(<pm
i.R5![pY]A.TeG#AuX_jIddnJ<X9e%h+,p5kD1/4C4T)K.ol+2cJ6V:Tf]Ae6,I"#8d=JJSHsb
Yocki[hn*ZA/J_"pr,8GS/`>l/HHC'03iC8:!(_Pgtha,RgBj"?^\g.9!&)IMZhJ<=VEoI`P
L@3HX9#<YGer,6feS`(ul,g6`&`@luSA[Ou/[Y9b$WIWtRH.OasqAAL'18?;#/#*H<YZGCEV
Datg>Zg$Dc#GQ877!_Rb2TO_DCV@j-a/"A;M"gSE<?n)/Ya^0.f-o]A>Fh@F=(,Q'_rBp20UU
DuW_0QM[?Bq/%0QiP[/"J4G-FJUjfA_iX'"GF:!ior/AkP<loG'/t)rZqU@Po_>PkG0,.l-R
mmQV"@/"eAcGYP(=8@hT;*%s#]A0=kJL+r,D":$)j*X0U^S[3u4;;OA>s\B`Ve\sm%LS$Ln_;
D<n+jC0f[gJZD(S6FY[LC&[-?E)[^<k-D.YPtQ44G:B#'9J[enc?6FL(le28+k`7[+`WZ(0q
XqHtsO6UmPQ2?*r]A5e3EC*(rodS+2V`n:5T1=isXoW]Aqj/)Ks^dic+1"7M&ERt/9\l/fKk4F
!ELL/PNK!SW_6EK6U'8>gi?X9M%<LkkTJNdfI-Q[Z;9,E1Y"H#QY1,H_29k$(X:!SK?Y&j]AY
6'SFXFMUT$n>H[_,>cLNdo]AQkjZM4nSBf&WueJ@qjM8NuM?n-_%BL/0)lgl))5DTk3jFY`[O
h%m(OuH(@*sTNBZG&nh#BHV<[,O#R66hseX)Op*2HDd5MdZ*m*E(V>VGc"LpMN;m2="Hq_aa
gS#mckecAOk9NZ32,00ghK[1'0E6H2*5,r0=F(`KO1`1?3K;[[;=#6=M?D1DG-q?i8<+*#kp
LChEo+#L8t/%Z=*ok<^s<lf.=O[1V4/2*a_D*m$'*PgV`t+(KqUPq'U\L8V$<7;_s_t62Xn/
<.A:IE`D>8;Y4)7)?AdM"S!?RgDJS>]A_Q4ooL8%;h1bI`mf;:jL/L76SZaZ;0Y,>_a?8bFT4
5(a7J`O<H6>Uhi&@"'"SBmK0jLYMT=_#1d*SQC:E_DrmjD`oA$X+Ks1,VH<8/%;8Qu.sj:KO
CJKQdsMI-P_KJkMuqdOWX>)bF&9EHjfI/oRW)iR\qqq-5N(H(VWYIOnOPoqmKaNE/WPY8,XX
I^tjbh*5>UTfmcYGN'=R;Bo?I,!R7mW8@S>ogk/m<c-d-d\dR,)RE$X3N5]A3G0#F-onC##>^
>crX_ej(.YRn04U+]Ars'oc]A)l+kiVrc(Z>DnS)8@<!paL(1%XXFZp%,A^Q+q5g&MrFn&n./k
)\Rek3JBIk-;&7;la?+OQ5=tHOOA2+*tkpG<hd4*GBBW@N"XM.RmG+!RB?&F&$nBoBAEaD,_
Y-Eb#-ogLda]Ad01,aMnTH!hI%<4Rm'P$oY[&'X]A7]AqLY5b%1=nJ^L)T/2LSAi&S:nGc)`Rmd
0:pL(j1Nq]AdK3aBu<^X-*S<2so^7ZTYB/5VUi2%cnTk=glS20sU11e9MU8?>M);s\g'L+\)M
(]AH]A[-ObZ8>F<iEe1I]AGcm4J?ihjoH&D7a!^:$IPdM=.P'#cMoFFCXCgb!1>!Ke8f[_sRNK7
gWnOGGk&=#G\4[QJ=H#mt2Jr#rE>jEfR$EoR-!oI0[qpFir6/nlrAS`TQ8(Ylo(I^W9X3!Q#
7JDRa=0Q`);<SD':l-do:hqOF]A_l)2FVW#Oo\e3QrL=.[?7IKKBZU=RGqPp1Ipr6GKZdXJ'S
jZt+ht![(*=UDACOKS.2OJ:a60FofP`7_XFG1^@kb%XhXrgG:/qL7U'<,1c98#T/=`>O=f^f
@Cg>lH"]Ahqus87_I7d6UT0Jk2C26PYfbi5gAZ@7$XZ#jitk1\CkNS1gd;>dD39M:LfgBDKk;
+knSnMe;>,/g9TZAIbagec^91-(*%Ui^5HlL[U;I$acbn>.&I6D)jdpgi^dlAB/\g;EcOd%9
tTpF,FHF^7A67L=K%P)ebZr%SE@S?:fXn<LNIjmgkWRJXnbD$:dfS:Z\]A+TPh0P:sM_>PoB;
$dO)3jhZkh=*\;Kp_+em2l51h2j`8\]A<OEW3Vo/SdYDM6MqSg$1bUq0^]AcgGmc_"u\'V$_jK
HUE,@HY3>mBAbJ'a\.,);g_o!`B8!gW,^k(\@aEdZ_AX-8T*Z!b`:S8R?:k'0q=9lm<`[5#:
'D2hNE/`-M&]A(>k>gR1JG+*eR@>2:%XAXaS?p/L)b]AoF0eDJ(X"`^TKpq4VQD;MG9pP7>8d.
UB8upDPS\2p'7k)YCsDbZ6_f,9=cJ'a\r,q2(o0B'LbN5*^'1WKY>S\@noE#l,hb23P`\(&Q
G:am6'K0!AM-[h.FI6rVi@5QRE2gGPc18$G-GPcSB>(koN_,JQ>^VK=,:0qQRHE)Y8\F?t9n
4;SqE7c'W&aCOY-OH@nrZt/h,13uja8JL1q:6Y.6Q"ncr&B+?(31aD\kb\_\J%L.`b*T^'Ug
MasU^19DYf@IQ?rNT.0AN"W.?\K$]A]A/*:*`QlOgf=j+7WrtZiVH[^OXY3[Sa]AQ?Sgsma$@Yn
gAsJra)nVD\[SSE:;.GT<-(5M$Ng(eL^[\foH&n8L/?F7[kYpu6d1hI*MrEgK?<=nSC7P)_j
*U3jH,X_"Y1.4BbWh]A>gDGFU,,bZ>;$JB(7+CAbL``+ercjh/e@Xre0lH0N`Rril[+<)dX>8
)Yl0HamDaiCV^@V:ME%tT4l;Q$\WYY$WIX,k)2;P*V-091L:7dHciE2^qKP,=F.eK#_fQ8&p
RlW:k-bH]AV);/5:(Q@ZQff84b'XlQd(E+W#p3[uNjA#2onCVj!I1H6eS%I7jXRLa>O^.,mcm
;iXe-hNi1A%o@O7F3[J1>e;+m(EQ-l>u=3%YTi2,eRJZO=G88?lL\hXT9WDO.C1<j_#2&D.T
'bWg\+hluO8aZNh4kP)*cP*=\B.GR7JncX>ZT<^kFb>]A(P:#W?ac]A_\M?e9>'JK:pW;ii+]AD
RL#%4(f.TaVk,IU?jA`bV5A#&rFlX[4#eLn\VH9p!0t%m`SqXi);/uESOMMq61_[MO!`S1n(
D_TuOi$j*^dJS<tm#V,:"K4c7cbjg&Z:_`9*c*!i8:*/GpTHsq1VEA"Gc8Z%%/<7GX/?LtTA
%\]AhuDnD/0o!hMUH#ro_NApG9!Q79cO+7dlXn=pLAN/#o`&]AE^"2:LmNtl^?Gg5@?Q8>=)qp
"R_dWY(l_50pQ/%[S!Db^d]A%?r()2)]Ak9=FsEVd&@:_JH,04FY`PW(MQg1hh)9rDc4A+R/na
^=Pp;F:rT3,pD5Ac%!/$'9b6`5m-MTb^Y"2hDn^LRbqi,'LL09M_.aid.)a8YO='[DU%N_#h
?pRCBAH)p[=K4j^s3\K@O,"mCQ@u[4BLnq;/%E`'%b8Qp+^]Au7_$XON"3V(MZJF)R9IGo*4>
`CHHM:*'G57hD?f@**]Aumjdk[*s0tc<YI)U0;3R-U&EH?f7/c7LnejaDTJ1+#UH%5BAksn@2
OfZ/,'P6Wo1;Z?h5N('HiLFRaO@QFG&?@"iN3'\"NPrh)E9W.j;t+s9K>K(sjMAii?Sr]A8=9
ja/Jn<i#I[%j;;8Z$tk(fK2ql9utpY?uMQ\1qk:3clO-mKP)-t0U0@*-jkK.TQlU''8e$_"Z
eFBHIIZP>u>o!74,EHt=?b=VkLXUXfEc@9(dQ;H/e!1tHQP,NZI4Z3goYp_O!4E'okgr[.47
c41Oa6^AB4VQ,-?41Oc6If<.>;FoHb=2tm`2c4o8i_#F?M+L[4[)#X!c:R*jgS/a37p%rQN-
cD_"Gp%p;6"';n)g7DuHSj$ELpK\:o_T5"XfKkqn5aBjmqVnOS8rrAh:2+XM@D3Cg>dKLj7L
0/&;3X\g,JhTQ@t3T6q3TPDnaY9gW,3R%l1jJF&51.USWgc0&<0.hV!H,r@MlrjKi4Er:!ab
0$dB7=g%aJ\N`!*#5+J`opu\=a/u';4piTq%1GEUeDFXWQTV&Fk&FG,SK]Acd1LUlusJ,bJPt
!Vrh'I=77R<aO>K6VbFq^p%r>MWKlTbn[[#$!;3&&8[DtDe+ChuWAmEL%"OnQZjLlu-8'%Mb
6PT(p'Oj!G'A0!J"s$f0a1W]AQW1%@[oP.Aq0Z\?^LmAGB;<&[=/;H_UA:o:s0#@!b%N@CCqJ
N#p'g8TpaPb\rbc,7d6+eC9n5NSUZHUNWGJ_bG->:aM3&n'5Ae4/qo4'NFQJ>r;=WQ^^6lPp
T"0NdJ@6<qL0hIMAF*A?3]A5%5D"Q]A^LJ_&WP]A5UQoBpdjU1^'9*]AT`7VWHfbr:$#EAWD\#>g
@W#X5ea1rNc$9Ejfi<UF2SW"b+efaOl+$i'aA/YN76FSU(pF&HVrZj:Ze<&u9I%*^_QeGalY
fCS(*"s4rK''!#4X)iLj,lXB/'B>Hb1P7_Y!a%M*Nn(cO/*mfLn!&Wom`19;;LW/<9GQYUH9
7\oGfG(Ua!aES!HCZTic-3D[]A?PFEondOg5f+X&1>`)DSFIl8G.+;q(tn:(@9D+++N:/dX9U
V8MY!8g]A#r4SA@ZEB]A-(bLISkk8irlKGXAZdp7@*aiYf5f?O,]A,T`Om'+CRhWj[iql-dhr]Aa
T?kl'e4mHd$$De1\\fKeiHu]AN^cQ2'?e.B%2b_<Mhq2Wr)E`<iR/#Zs8e*5!=Vu.l65\c[<M
U9Y#[(G=--]AOg0.oX^&s`&SFkWba#EG2sHM\$V30sggrQg+;.IT[ncBj(8E+/98,n%BJLf-j
5oR`5NcY!/.I@&HlJICRR[_>SG)`K59WlFDfV&glKf<N1+'mZ=^!N<<LJmN=NPd!R'Gi`i_O
t-7o6$q*secD(^MrN.\[QAXg>n&T]Anh$t5.,-,8_i0s55rCs7,r;iJP9Fmq71gX?P)Bi!9nP
?7^16Ld8`kUC26+RaSfd"L2,ggYc=`O,"2sm3M:Mq8A$h.*H>7I2&8Y>$ScFWW5An;[0iU7f
-SDGnN1f)injIJ-nnmd_k:?`Z%538d>!K/PF%Pqi8>eY%jOUO="G*UhZPn:0%T,j4F2'bn3f
^(bbd`ir'LPL[><>G3G)DpW<`qmR40C/]AR%W>?qI2<[Z*stP\M2>a'T&APWZYZd2YV;JoK'D
=4W8%<CATIWC9(41P<;4IO7n]A]AC(D;F+$q(*$GmM:C)bb8lYj\+#$;c#EZqZ.NRp/c`CUG-&
Yiu*R[=/!'-[qdW[SC1R3Snc#rcFE?1O)]A?FS(k%1=PpTc<fZ"sS:,N4opHe!C4d6eVt.L\i
UqGs/<aG$-@$kWe_Ph5"a9@Se"RYMBdT7;7#UDKV2G^28h=aY\,gm^F?'+VQgC5!0/+>XI*`
+edATW[j?!002<P:TJX_]A9Tp`Jm/+>Zi/190m!mBS20Kt8TiWi>(QEaYkTVNC;fBlZ3,Q;&&
=FVm[iAhX26.P)kcFbn9/6sX[8;gef;T_a2+qmogBs,<k!#)IU?EAjb>9F\`/@Tm]A<=%)t!N
oZ7/^9MUXQYqM5\#]A'CPPlCuTLe$fD$,P<Uo^e9'I[2E%pR`NBN(\O$d@;]A!%MR^WorN[h=f
kSZK3X.+k]A/;9>m"%mW[siOJlB26*YF:Ydj!g26X4\OOD(\q;,2<\i'WNQk5M5s^;6P&'nI*
>&d6X_\9kVuGEts"\24[+DHRq8=;:sl_4;e[i9PrhAc-:Mh[]A:i_nsqD\]AmN96H1k(D9j;l4
PgOoU(T$<&k:8*YEUDD`&di>^fblu5pOa$0h3!SqlS@,D/g#AR2AnKW\8thsQJ(l7kuZ2l7l
(4+W/8(Va[]A=_@Qp!=%JfKjbPX><>iTcj`3AAI.EK1-?=(,tOdE-bJuG0TN@TpZ=A>m!Xj'd
K[(>\V@1fnc'P\4MbgaM"L7B!R<rHRNP$%c?6[;PJ[VFuF`H>L[)'pYI>aUF&cLng(rD&kIT
_$Ef!aNd=[Rb%K_fLqrXjtSG2OpCAQNm!i]At!O;Bl;@O`Z-QRP85Q92+uQI=%_Wg[6@D:>u&
YR/GcD)%Ne2_Q1oQ3mC\TGOEjqaPR>',%sR_sk=0R-/`cu)XNln0S7os,6m,?3=SKKs6g+*S
^M>.qFD'n4KDQ:jT=1g)8$U'EGqBGb[B0.4%Z>PJO-db>4tb.8Li*DY_E2"MB,%).nLGkq$#
eMQ<Q3KlD&8oaJno$Gm]A8aL8WB'GhhL,bX@)Tp7Y&)k[U6jmk`]ALC*QX#dq)4:9P@i'&;.'m
Y[&j)DlSB-jF-$e>+kj!'ceJ3#?cB@W(TMMe0C[SHK$(_e5?jo2ChF>MC9-;@#?Er9kj9i9h
p%0qnJ@[N;Ob@4,89[_S"'1]A,!#Gg/!&rqqQiTEpDQsqT>U^@(PLSM\Nu?cQ]A>kSP:b`ThE3
ipIAQX;[L\V]Aj?Vh?`[j>WW:pN?eX>ecBAXIr7?rM'Cdpbk0Y<^R$S''3P#nS]A;m$2V=(?':
6W/Ss]A\;gAg-Dn/-[C]AnN1p2VMg#fgp=B&]A15gA%PY.E5p4o*i5L)pi?/mN&2mN7fAL60cGB
3b`2CAmO+Q4\^T@!s3fH9rKp5U3o`5Ml%;*CDNRN^NjGoun[N+DVE?ort`laB"J]AVHb.fCoL
;2j6G$hhksPB\;@i"4@R70"_VgrLoH0W%Xj!6$'<r`45:Go,MgmU+O#VZ+D3s,&cM01i8Hhp
>$js;.%c5j3:A*09^&#LhYN>ST"aDlWZP;f_(nUFss"sF((`;7<YAS,U3$Nr#gbs-dN@EaK#
rf.]AZg;;kZ5d>\kMNYBNqG%pe*+e`gFA'^]An4G4)e03s:MZL7r/dH'&8KZ[S]Ae%fT7SJ1Fi0
S]ATdmV<WNV&gmglU!VBEPZ%JYe%@XNX,Q>D%7m=KG>nM!M%?X6@AWtK6NJsO&>a:\Sr+#N^'
%K\kIMrLZ+GXZ^rEEdg,&_bg8>D^K>$iM\_!U9]AceK*@_+cS\!nR6kNX^i=&+kYHK3P4:i(M
a:e39b)aLKIlHhWuk2j[=#Hp,ki)?Z8VF'2j,5m3M]A3XY=lu_p2GOuK:m?6IqiG,kYlf%hOV
L:Tk2I@ioRfO7b+\GH=5j`ml_g@Nq>%_2r4F@!h_(Ro"O#H@-.5O0RC-#EV2mo9]AX^%fR,sP
B2#=.cMe14]Aj)nCb?j,=RteuRSddLr0APR]AeP1>O8t`il$tiq"KE9=j(5I1]APjoP@_Rmp3%;
QsFcL(=C$4;mWKd'B`29r:[s,B\sN<)l)MZE\@cqVA'8PEkSId]AHYR4Nt\>n+\%+#-_)&#/m
L+*.Tnn<cgMs#Tr-F\D[jLiZmQq&0di'LZ1]A$7j?FJBO1#PlEp74rS+MmZV<rPT8!mV!6l_n
K[OC3-gZiGW3ETb_h+X"FHdEOjAF:;Ngb!*'paOqoK-ni@ibcWf7%CPi/ht&a;=T%aI\-m1=
c\a]AQLIH-N:="X[Ef8%1pM\&EUMp;KJldq?aAQQcZ'X7-.O#H#m,jlNk>GB:l<@-3TLgq)9T
su>p`OcC9k%`bO]A5i/0D1IF)4D%V+\,*#Q8Ma\_mGQCK0SS#t;`.C2+7NU)U7%B.QJKmqOXW
%*$)A2[uFmJ7p2/d/YogA4S\LFPMbUDgVTOXgKHdP9)r>"^>=\'9Z(tQbMLs<CcQW2An\b/^
@$ST:/umkO_r%gN2hl:Ll:idkiHdHEr(jRN6r?.UCu2L7;[trGinZX5f`AK<@u9HQ+p*_2CM
6.ML79'!C["noa]AlC>*6UI91,$f:_pr5tHrB;rsH(GB%ZcJ\4<a7+KfBU1?,dVJ,UIS')q@P
Oa+EMU@(PU2CV[j3EJESuT1JNtZ(7AcTNLfe1]A"T,#a`Oj^)*,l8LQOt\d]A`*U>dOK^0ccnO
":;0th5+C<g!=[j>(TQb(\_TKPViq=,HjPWWN5jGB0hiE4n"ZS-3C""tJGkn_e;B48OOb;d$
EfL-Ki/K33rE=GYM9-L^_JEBsfXoOKmVBuQ_RUX9V1<i"N/0-BS+jX"'(7rh1PH7QXB@h!O5
mM]A2\=;]A)5(bD_m[;UV;jfZ=\_4tj&Sun6f+s#YJ"6NBo>=lbr<+u7=MV'rR!MM$dae0d)j1
sSH(M]A[n7Y"Kn&]AnS]A7r,""C[`=/R:gl^j)P[G*nM%G>H_o,P27Blo;br/k628%'>:E$fH0M
RJeekqE)QHuCE%pdV(n3I#M>#7>WlX2IE]A7bjNo&u'f*)c[ZZUP_Fu&1-<QKD_0K[3*KraLQ
;^?]AN8[E+:o0JFRe66feDt#]AXJ6O#b)f!g07l>]AhpC);/;0hQ!4q:3o[tAk/mW+$TU-#QdD9
?@-@Y,P;H(Lj>-q[,8j-oHZ(j]A7B[A)<^/S"bdZePpYh_6Tau[T;?`Lk,Xth[k`KNnd7XI>N
R6i.dqAK"0^=>MFcL;69]A'OmMP`[cC0HB5n8,-$HddbnOc,gnlDB6X#TeJo\]A?U2jtc8hYFg
O*CSZE?"`KS"ZPPU/;k+Y^Vm@f2*RJ./^Xd"(Zs.1@=;^['"69TcF^%OpahdFqtRbn]ATQ&?Q
@DCiN)+U"`;WN^E3iuN?^p[85N&TJiu%**-5=`pKD#llNFP2gZZC9fcjqqXcHYHJ2q@+AG:1
VC=DT4_(BPA>:A0-G"Zu+!X!IrWhjK6=D'&\F?*kFEn%Q-I)CGP'RmrUT'(NKhE!Y(^r*X)Q
N>\2RM1:sDbp]A2$Ls]A8f!oq;IhJ=E9D+EK:SSXl8'nkpI2O5-;TA.OnTP<lR'?:!K%PonNol
Usc/_96t5$,::k8;c"\$Z8_F/T#L^%\&d`^n-Qoh3]AZWk$MGX2[T[\,"'tVT+<6U`eCu6^pG
Y[k0-^bkWqr44\G#]AM8jma`dSB?YSE7Pc8iX\LC\FPIH>b>c,qaG*PtJY%[IMBou0mZr9b+$
g'PE;XJPU$ccOENGno=CFW>4Z1FBFGro*,@lcpqDPsV=m7H-2I)jRAWu_+Nr'Z/W!)D[LB)i
T'e7`@D\R`8/KCQQC8r0S<(4GbonP6KR_g+K&N)kq_Z?l*IO\)C*[QFJ_&bA@e_T./#2F3=n
'cn'pUq-CL:sR>ooj,7$_.YFMrF&b)5?+i<oNq^Z:HaH;?%nj+OZM\[TI&E9jLb>;kHk3(eb
,I_Yo/n7KCj@LH-3Wqm>#H64k]AAG0nPKaZOSUj5:Tns]Aj;W*]A"?ZEQ!F[`NF.>9WLM@V`#"g
P)tn(F:nu0.[AW'aJ"D2LkP@DEq>3&IH#an:Y^R4+rXZI4gMR7'l=hiqU/`MlJ+Sk?o3_6*^
`8hTQtq?Y/Eas`_Es(_bhG?q<W>*RGj")orH-M25Q'ZjpR3OG.dIV12:J)OZ7#k6OZ0$WCD`
G,RIuf!AD=qd-`0=DlGki'bE)XYSKB8,jTTEXMFl\`CLS7'/#8Kb>+9YhI_X+@,:/W$WO=Bu
YlVY5T;C4NA(^K625[fOq9-0)KiqcGpb!r0(ZDs\[!&NpqH+*ZIeLJ;mfa2k+H1`383(iX.@
RYA%ToIC4&t&2$-9XpTf8-m<f4MkQKfQP-Mbi5DA'`hS*!nXf)su?nC#n/"Y+ZV=k=[p:X<'
ke6q/MiP%F%9H_kMdPW[eQsDp>ksK_la%<#nEJNMMK;2k)Y,)ci.N1$8'SDXF,U6NR&pDS'`
29@WB'D&:Sj;O3gH>bFLL\Zf*D3::h,$%Vn`/h)g/1Ll7c]A@s&+,IrA]As\=YE&]A79?K%>4eU
8V7\a;oR[NUVf(#ml#s7DKAU?1Qq^LLIYFnOi>G8m?O)\dt@I2g$f6)S@:ai+NEW942X6K?t
,+eF2q5+A`br=qu]AVTE,4A]AXH\O:g?)-W]A#05doc/ko#53.OF#fr%JL96A]AKA2>4u-b!3NB_
r'd>GHT9.l5@qFa[3pCCBo8_#O,/.4r:K#+s1%0^Nk1D;NKT3]AhDkYjl8lnsRNJErPAH!Z$F
aMIhRUccCfXqr\b0Pd1LqJ*Ol/9]Aj2/Ce&gB9LpiXs3oil.10BjhJg149H^=U0[N9jXNOJp3
XbWh-ms,9`cp7unlEhdcM!Wc`^Yg203OX=g&o'M#M"RGe2D]Agf-JJE3]Annu>g4SF0o8-hfb=
r@CMl&f7-fQ*r>PQqiL>i?Gq>Y2e,@oLQuASP@;1QTeaE%`O69\GPB6l'p#!ckOP.>1qFaB:
+p/q>a>YD;fe`I/I>L+2o.a:)=pA:D#(O_?cY)$Z^`L%LQpj)m86RSN$>=9]A[j]A3o\(;H0(&
^[+R+3,$9oG'ic]A3kicOc>_f7W4:!8[rO*rRib9p)L2e\&i:n9j?I6LaQo,?Fi*ZpQtiF6Kc
pd_M:!CYQre#ol@^<TC$0J2t[(ne(e58[%rgFB.-X)d5Jt7T6,06p`N?o0i%h\p80@52J7l&
l30\8F9A3R;9a;ene]A.-Rg#W\Z-'QB(\:JP,TAC9$NaOOeg8FLpTjfl]Ai*M?g#k#lJaT><Ft
u[MnaD8JU?#2PqDN2VP>=?[qdHDED!%DaOna,GgqqF>fCQ\Uq)Dh(,"MQ(H>)?L;cANV9UoL
9c5<o^:',OFHu+s.=Q'1k:gaM=bQF*mF:f"lEO"W!jbLo8#]AN6l.^RJc7sEk`GcGV'BR\r0D
MY-r3$lq()Vqh,.G,Qf'T%6*>Y#;]A[<Onc'h?%r9gW97U19(k.Xb3OI!Z^UJ>R39bUadC1C/
,VSTiDe,iAN1-WG+CVa0h(.;L&=J+$sfF"!\3OhMC$@8LK%BcTea)3]A(,0')4$!g+:'Q6[p]A
KkGE._aLp]A<FH50`0('CF"JSMHUb&@Vl[-$"!U*$+&_\BW9[JH]Agh%l>f<u1-@fBNPca@)/3
oocW3@&7U)_ODQCac(,S!sgGX<c]AM/]Ab1n%<?-H_:W<c=I5LM6mZk7ZkA=maWX3)YNRJsHnQ
\!u,#h4oWOo[9O.fmfI=E/J[l-0#Cgi>i[/5`%7m_[od\>%eX;qm^QqCWap%A.7e6Yp(!JI(
QPEs7u2+q]AqLn-;*\r?Hjd8CmqO#1/]A.+T<b2=QjZ<62G8ZB$`]AM^'2$dh=de=u&q>]Ai+&O7
hI?6#-]A]AR,DWgL3q3/f9$SaON1m$7rDgOqA5YS,Ms5#<P,\]AjRQipJhte!*csX#GYj\!GXAC
UW87/7eHd#R'WUgN*U1Aj5^fZk761%]A%f*0/0rZ/$V!f/\b),(378uO:PW&0&H6s_S(s_oDV
@QMdOatp(^ai?;mStRT.!e`WR`UnM`[j8``8<qRh$WH"?rAa@Zia84kY@Ju>U]Ale@6d%E16W
4>ClH+SBTNGccaN-boc<!5n__48t`R2$ml'^b,6fd<^'>maCPj-+P]A4jXk-3%HKCZY-buJIX
bHg[sr2C59R_61Sc66IJ-U8lNH<Hh6TXE!]A_NaN'7k=8kegm`(ZNTqViF)N3pX$I.-5$h`+c
FNc-YbLlG4a`#.L)5*gARH<G!AEXE1[]A.@[s!?Vf!::]AI6iC;Y[lmqQdoM:s'jT8WjqZfi%i
F@i<hcP3&$=_@9:kAG&/Cjd3kPB`Y:%F-?m%#p=rF,KH4\MJbZ+D(bm^$Th*W"4-e0P)A2gr
H^P6G5nA3T"Y/-_MoF,^bp2%2TT(!6T`MmQ4ZJlWi[GfsU$TZ2>mS<-*p9)EbYO8JYpNXP3.
e%6cuMgP3.h4BCKs":P>p?kb"LH*uCd!AMZ`oCY3i.1*.jN%7IR.R!SiMeLh:AqraV\MOJEl
N10n<4NW]A7n!@H2UTj6")da\LeaKJB;p2qa'0_KgH@W2I*Gd7L7ER9Xb3dL0qi+l=8o$f(8_
qd.F90L;?ZSdL,j=!.3RL'c'P7(_/R+SGHrOOInE@k0n4+ag,C8!"&\40sF,O=>MGXB*6163
0=o'RJn*[=9K\mR0$Xp^A5sOnGg4grc60T8_<>4=L\n_WMtN"G+*ignCN<B570V:kIX[fg!T
P"nN(C`TTj@o@p/#RR)UG[@DXDVf>p&^8_7>Ur$_ua9OUum(/qV_!-A_/rNUIUVQ0cOSH]A=[
Adg3g)3r._Rj>s/d&eGW+a8_PB^*\*-g0CegIflnp*7&>SiX4dM0D*J?E-Fa9H"jg<#[gr6R
hsmJ8/X^qn+iBQOf=%s5uS'57Z2EWL'7VDFb3`9HQ*;7G<:LTjs55L?WiW!+M>Z+@Za96'VW
8'[uRZg9Fe_]A'1.1kdGA8$]A)p)UEdocEIuWKH7aau]A$I_T_WX%Es$?7O%jt+L#kMg'HgW2o=
%@EYMh>[n?2\EN"0tT]A_"hmO%_?W=7tG]A2I)!'Yl3^4Pq<,3(hSL%tGEu1r[3-`JIaBOQ"-V
a8?g,O`U6nJ0:1cQ`2#pNd]A5Y>"P1EEA$ikp5K78hfRd6&eN:6Z>i)[k^DJ#C2dWB+,<7'R:
Z'Wbkr[0us(MO)mW]AN`$l[uA?TH2]A3kLo*>$H/u16&<JYs7P=E\h>\:U7rjR"gb5)@GjbuF@
A"qo$_*W;(CNEF<`O`@V"ge(&Lrr#aq9EDl2"_lNWmp,;_A`aNaV]A#!P4uF@QSo-c'\FP-4B
i(==TBkNGp*4o0Z5jhDI5OD%K[+>Q(W)npT`52QL5F4Mjol]A9IoYPtF0)W2dU`Kqb4l2P7bO
r!2!$2Wm)X?VJD4]A402@a_I8s8/Oe42pQu0F-<9gS`Q"?/Bipcd@n_(dZ2)5nr-s/&qU;hc;
-tq]A?3cpnOO!L)9*DRL(6e"5j4:oP:7GW5=45l-O)mg:nQ"kNO#XBm/o;5=bc,K]A,.[">0Jp
oF'`tKN[Po8&OTgCj5pRdm*]AY3W7!6JaFt(]AA_dr?:Z9rF<btj$Jdi+J'sb8UM(`*g'3oaqc
i*)\gfOrO3&<6ce\LVfKhM.kV:,f/=#uF=*b2Re8A[WP>4%Sb0FU7E'l9CUe<fqj,';L-$6g
b%3LVnLfPqW9Y15hs0>XNfr><0>91Ajm%XQ$n>E&>=UbJ_aLe,b0Q-B0Lfmp1r,f`DnBoW(N
fDL!TX>[0R3Rk.Lg4IOi9cHI1*(keAuJ,TR7UhBDLUi[9UB&9kGuUoq7h1FF/&cqadFIg5X"
SddT&QuX-i8XdtVg"BdNW*;2RR3^R:\=#^D[N3idB)a=S#iO<RurcNn0Yh]A)WhJU0B5bn/U3
L)+N`r^P*2_;i:^#@T53CPcA$H4GN9O7ia)bLY!Pq`!?'K9/)YEC@Ae!>Hkspc"G&k41r_A$
Y;d-%8Wd:=oJa\d:)V1Ms<q-aQALdVjVEq!?W4FBSX-^AFq-_%k,GghU,&B6Sn=Fdks[DHco
:gE*mWD3(2W?-+Bko``%R6o.rO;,S3*#VsOfLIi[hY6LMi:K'DB_E!4%8HjV(2@L^/r,bL<&
GMF__rqbFTRXUag>>@Halp88;tdujiBIN;.^^U+iL9K9VfdU?gKrc?6jDN!nisZ@0;oVQHCq
&!oXeZbU;SMJ<'/VhR)_hQW<f'?bc/mQHQ*b&?7WEH<Cu!Wr;H6Sha3QpFrp\BG4?<TY#RE&
p#nMFLHHa%]Aj75&1fpNrSFOYfG3ea/)kV.dr-9F='<1W-p<*Daqu?Q#qS2sjEA8?E:k7_[Ce
jtT86V;+CL%=/<[R4[3/ITG7<Ei,qe`_u9er9hnaB,<lMMWajnnW3O01o^WL]AG$JDo@c3ZJ'
Rr\Bd_Wf49aU/XOGlJ,12oATm`F^tpA!<2:0T`\(L,)Mu)s"8XZ._0MQ1#V8&`,L]AG6f]AoKG
qQU9r:M^h\sT'2:RpJ<U.2QZWc[VL!TYBuf87b#;6kHE@"BnL`%dbT^.nRk%Foik4-0C`~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="642" height="202"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[车辆销售总情况]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="96"/>
<border style="1" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="642" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[车辆销售总情况]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="96"/>
<border style="1" color="-723724"/>
</title>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[车辆销售总情况]]></O>
<FRFont name="微软雅黑" style="0" size="96"/>
<Position pos="2"/>
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
<C c="8" r="0" s="0">
<O>
<![CDATA[总量：]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="9" r="0" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量(万辆)"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.SummaryGrouper">
<FN>
<![CDATA[com.fr.data.util.function.SumFunction]]></FN>
</RG>
<Result>
<![CDATA[$$$+"万辆"]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="12" rs="10">
<O t="CC">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="-1" seriesDragEnable="true" plotStyle="5" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${SERIES}${BR}${CATEGORY}${BR}${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.##%]]></Format>
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
<ConditionAttrList>
<List index="0">
<CustomAttr>
<ConditionAttr name="条件属性1">
<AttrList>
<Attr class="com.fr.chart.base.AttrBackground">
<AttrBackground>
<Background name="ColorBackground" color="-6697729"/>
<Attr shadow="false"/>
</AttrBackground>
</Attr>
</AttrList>
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
</ConditionAttr>
<ctattr renderer="1"/>
</CustomAttr>
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
<ConditionAttr name="系列设置1">
<AttrList>
<Attr class="com.fr.chart.base.AttrAreaSeries">
<AttrAreaSeries>
<Attr isCurve="false" markerType="NullMarker" axisPosition="RIGHT"/>
</AttrAreaSeries>
</Attr>
</AttrList>
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
</ConditionAttr>
<ctattr renderer="6"/>
</CustomAttr>
</List>
<List index="1">
<CustomAttr>
<ConditionAttr name="系列设置2">
<AttrList>
<Attr class="com.fr.chart.base.AttrLineSeries">
<AttrLineSeries>
<Attr isCurve="false" isNullValueBreak="true" lineStyle="5" markerType="DiamondFilledMarker" axisPosition="RIGHT"/>
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
<OneValueCDDefinition seriesName="年份" valueName="销量(万辆)" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="月份"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
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
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-13408513"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<iPOPM*CR@f[;V7>ctQLf>fQdY3JYC8di)&=QRL!!:S915`jM'bKWC2[-"2*X(^.U/F@/d#
m0P#pF3gc8frXABoPjHg[6pGPSV<kLk4L[Fb8Y[^.mu:%c-ja3PhlE8U`prHjE*#7_^T*B<Q
3`).GqRujejl3@e9'\uhicuG,;XZH10@+$89qs24r02<Th375fq<&p9\V8.H&M,sSnB="ot\
.&.ucB`Nbn=J0,*hA/@:k&/iY5Z7K1f?lQ+SB%AZdKs\,35>jF\&"2FbgrEYg(VL3*NGWCk8
PQ.`ptXNOOpti0$;VVqY2k.mpoDM%3DU!&1CY7OF31aIV=]A)?i$5`Pl7q7q%<>SD-DYnML`Z
L7-/$ooL]A/J?P.T`LM-]A[I43&G1o=m8i;s,1=f0+cE&+^"`.!rNh"j),\FT=&C+2CA5%Ra^e
`+F;/)@./`7O1Cf/C^cm9n"G]AH\lF]A3Xin^O@Ho'[6gmnT+pVo7%J]AT&#.B9UW$>Q?NVhGV,
?h:S[B\<D"a1@(b9_8hrN`jOJd$r-=S4uBTAk*XE$NSX"/mPKHtY\Z[tOULO_hcDT0K"0Nu'
Qp6W>[2C0-b=1:$]AS9=+YM<QBuI=/f4Z!0e9,KD?5VhZJJEE?'?h$V&leZHSXB<m_r`k"k8K
a\(n#_s&u@LTT3V\a5/u`=0o"f/89k@5,cGa$Hk$"Y^*S6TPXRKAY$<$a^e&\C*?F,sF&0`d
PhrW<`76Zf).sSsnL9.25fgrdFYt)Bd(6D!kp]AN"!AOmYAMkBL)i0l*V45`UR.uV-q15+)]AC
L:qH3T:Q.11qqr"[3n<Am+9=bkfPp5UrcGXJ-.N@G7_bJ4_aPFuH8d&7paEjnIXS[GR#B40/
X4G!Q`UGEQ8^iB2B>sMBIaOh5SfZ4m=TQlh7[jLRR7/;MLTM&N^V.qVSOkG_hT$)-<o8K%R.
"=`UQU:[5+--7qeoC3g+/e/a]A.&2OeAuhtobrg,dSi$Ha+5HK'Wq0dlLoQYq:F/4`$Q<W45W
A`>lKE0(`;8u4&&R6#J^prJQ17F$qmJ_:bt*s2J=AGF9AeRo%s!an$3V,'q6AsVHi#[&R/Di
9Ne"t+M;f;G[$i+U7(j9MRle?ON]Ako$)>8S,a=I#H*3fP_A3;AMMN&=Fr%oq$0iOeUCcJDF[
?3js/qLus+$V#"U_*n"Y]AV3EsH^L#?2m]A!#t+l&o:[fV"d\[15HAUZi*&NIQGu.Kl;ml`Ga'
"TYnC=Y^!$t_SGB(p09B5jO5-g"@*diKVNk,MN534?1Z6<[uYQ`[:[?Ygfd`1%V"Q%A\IOj5
//+Za*ob>-AVkFIf%5nUFaU]A>#q*W1XG:es2g)h&/[Xqg6$l6VDd;9!FO1RbUBJQa`Dk"bV-
1>)deBgqJak]A)U7n:$9ed%P]A&C.rMJ-VXNa#pD;\<pk;L&ti2hZKQQ<2OF\gq&F0(]A=KHt*$
=Eju-r1IC1NrG.m%Ju7mag%lkB67K-"1@uV3nKVk8>BVo!/\/tMpf[Pc[ZNC(^!h$U,8(#;g
<keS0gLlbtigof(n\f??Mk+8A-kYBS;[)0<T<]ASH6Gc(d>LlM/CZ!%F'90C7JPpUP9?@r$TP
77RDT1N[")GNd_C'E7JcYZsfu/PC"o$C0>N1*G/"`'T!A(r-:i2%ALh!7M(&E?u<paQ4-`Y7
t5_D/WL=s5oKlGgIqnF?r!nf$>NU@mN<!W/,oC+Tp1;\R1`!F<[ZDaSK*k]ApU\>l]AD"68Lh$
dT'1lhG*F0<"]AX=GsDouhteP&.,3;kX>D%+^C3?2g88l?f[kGeikT$K/TIYK6)rF_;D8cI:G
EZiSj(C26k!pXU\!qb1k2B?X:"EBF`7#k;:>m;G#&Dt<R5sS+XB]Ad@g9LbT.ETj^gG^c_`gH
fH=dcDl__:0FJ>EKVf#j7+tVUV=WFZKG[0Z4t70Yo'.*,D/gUG>:a/[ua@Yc58\\$)JJ%N+$
5J@KU8#qlZ1LX>)s3=;%YUO:C%rWGM:!u/fL]Ahu%,_co9\-_#+W0N<^9ejs&UIJg@oc0?7Ar
1>eUs%tFsP)^6;)7n$rOk+;gaW:Z!cc'M3:XS,sU_7.P#Wsk6IIYpsZN548^C:VA9E';OCr!
lmi"#V_@i/'#,o)t4Us&4l\,@sY/MN&Hb>f']AlVs0==Lt;qQ5bgu5RGOZadR8SN'1ZGLJD"B
;^1mc".Hh!+AH'tG'EtHs2oRs\"S'iP^g14lTtMd`D+7+cTf".>QR&WZj<Xl$>Af9.Ads>DB
n[:K8UW]AH?68o1?]AV8]AYj>O`F#R)[tM/2&;!OSI(VX]ARk/6?/5M__TFKmiCCF`d.s*\k%6Cj
XVgDmjH<q=0g3Y]A2PC=(W52ZW'1GeXa1is0<"@Zn(Wp5BsYH=o=R^e!;81qM"':6tsn6XGMV
B8jn9X`!hmE?SbH)5S5Ik*S/7V!.6RuH'(bG/&=k!,?`!e3&+M%nAkb-X0SP`%#mqta-&SFc
s#d@G';8B]AukWBF9cCr4J7^uZ_p?.*noY.":1#+<)ZXZAZA`tWEMCHB+:RX=Y_@$3g`:L9+<
g"A*J1;7^'#6E$SSrs=6m4NL<hZZjeh(4ZB)"KsrTYm+6IL(5RbGa1F#hkIin?.:6kMkJk\a
@A^UXKWc]A[qD*GDHVQCm+LL0E[.p,ooNL$0@Sh@_<RT)4u>+09\upjI)%6MEg77GkBPT;U<(
@"mhJThGfEDAu7,pTlpBaX(l8W\7G=!?-Zo_q(f!`AdBg>Ps'gI4pt(cPU!;RkW.]AYdFo9_L
7S@8l$HVRd>SjcJ>QVP@@*l]AD1SBCjGUY(d0WARaqFc/Du6'W7EYQ?H'Ck.@[_;?'&qiPqOU
RAPU]Ak@A1THHPm4GkTd.UhdgshG8:@tYjRSQ`?lZcT)#T+T2@<;8`bE.[";nGU,gOjO!Y=.Q
Zf(e#NBKcUpa*fO@&HD<<,,@UYF"*o*4n$b3o_VLRIUGf1A<eg[?H;&C!Z0!2<;&%<:0-gL[
qM4-rACD".>K)K6qcqr,UC/9T[TIFKUP(%5r_M^P5S*MZ(9e-1`-Ac9CgqTJrS46P([=,3Y(
DrPn*W,KJ#mH'Kgom/"'9!-[&\PWjhdj?N@[4MdI&pTZE(Tl6j(WnXYSfor(4&r)%2(;ddE-
Ftj&n.jfc@*,F:M3ok,L7;UTaf/9/ZO1#^it2`1EIeV5?3<KGDgg:OkYVjRbA#B8#B@rS8@^
'p5PkXm"Rhs4K/nEtOa-VjPojCZ-Kd/A+/\0b+aV;pZFW=dF6MpI,+C@D>?5?5TkYfMQc[]An
;*&`I]A_t<]ASdNQF;G,Y<QEN;^V1p[q,j&jNnNp!@3;V497*>\dYX"[),.CKqQhdLl:fMkTS0
a)p>HH4gmDcO\+a9[d[_IW5k2gm"hene=rTDtN8hEt<Nh^'ii=29pc'o7d+!9+BC#',oEF:I
]A6BmeK<ao1i.f:7`[FLHgr#6G+CGS+;H,F27/!c+QE]A\&VOMX*G'tcj"-T;_/[,ek=nk;`47
-V_e\V:XeU#0<I`j\Q_]Au_6QkNcUjhKq:qVjUo^huMh-.:MG3:'_<S%e@m9H^._d,;87Bh8Q
OUHc"e&.\(hPq_&pR*XY[sQrNj(IqusNJ1!E;2fKQ<d`5[J7'MNj)Smn!GY$#&mhF!fH=/$O
<8%U"nj._HkY8NR=Zb8c]AXV?NIa4J14X8oN>T4m7+ZYGXg2N.1XY,G[.qP2=U3cOlW[A9F4.
hIjf@C8O"_:P/O\Imr;PaRu]A+=sGH<uH=kBgn@Bd/='rlPMp*e0m#Q"8TtM4#[]ArLHMk.mrN
u3,'$1X+oqQ7+bKoYJl+u5\Fm*MQT-lY,Y@M@NMg1&@G96,j.j2F,;3$@TTa$5XpUB^1>;j%
k%c)rp[$rje8!g<.LMZZqH-]A:ma_`UEJLZoi[VpZ**"bSC1nXD-+OL:p#C.RMVk6;/Lmso&t
jo0!Z)/qF.Xhad_EYR&s>+>C?n5<IF]ASOjFsRaQI@al"IN@WhrR=V2R([=furkh*9A2Q[9P$
oiC]A/3M$qHp`sg766`lR]A(VZHqNs:$JESRT?A"B]Apnq[PK'pMuN'1s%hG,)t3\31h66es4,`
h)7rt]A@g<X"o'fr[,U8,iV/Mkhlg/MgWrE1g,4F]A_aV9HWCTegV-4Fsn'E,?l^?iuU:lW95+
LJCVQK2p@(b8++Y7=/sS&,qlWD3>:.O>8U`n0@/)t[jQdm3T%*!MaEC!GDaRY?sa.71WbHa<
pN=@H.O(c)T+JS.Ae]AULANCS.h?nmnkmc3c.+%fd[fXolB:)(A2J6&MFPn`>2Yp[6eGt@Rre
+rdPAK`ksE)I,Hb4-R&t,.YS=N\Yu"V99Hn)R:FHbo^>@_8bUPBC)J&b0geQQ?)-&,b?gW.U
9>liu%Nej+j@LapR3Xhu#UBi=S=S/e?,GU")AE<;p=KhHTg=SV[pa$o4GsR(UJtnMpoIrcg)
$ZT54L9(/RE6F4kWe=QF$(p0\ernQm>:kI3I<kVN?0c1+2B*f2fmOEf(imS1m#Nc%%BN+_*R
X2Ebmm[kNJtK#u\"Ar"+pD6IQ0_jLhpG!!Q7m[^F_k:6_6FK[5\Y04Cuc#saH&!CQ,qi:iaX
)4"QZmM*5A48*".VO4:-42o?!SG4E&T^aR,M?YAYj1`nO9cH9j1,4@e[\"5(7VG"TGHH+bI:
^37JN6K7p7>7<Be><Ae_ibc#G?Zc,[kY[87UrNJHrA&tKb_Yh!tFe?`leKC(A@Z^M#&$gZ*$
FRM8Tj9idED'?\1@(ekholuj?+Y((`5Zg9!'M1Ke1=asr(N%D=Oi[$L&tY[F*hT-[N;NDnSO
h(L#e0jNieMoj4Rq2-1UJM=A1H:E=lbhk4P+6EWku/fUg5%sRm4\dlqln]A#I[Qu@>VgE><1_
K%n@)MRhYSDHpDsY\'7O5FNC'?.r%!f(^XqYBOE=p1?JeZXleX&<>5Z1]AnF;5]AF*7Ro`@1t;
oSuWZmE@B>IVeZ#@Sg1n^:*R<it7c@FO[-bTZ^9j1MjP:TL37Y%'BS,*_?aODXP`D'@qfZY#
P!ZXb5^+(>^_f^PF-QKCf]A4+_LD<lba^LsSa$)aGi'ZZS2U"5^G5hBJaAY<BmKl=C-fK\UZ_
0TahRUAkC#f"Z)oMXmnkIRP+01bMI8L"@1[Cp88s/85c,d`=<VGu/VZLZi\Ci@>Jl_k<NH@>
VR3%,dCgZgL#dQ9NpQ@Y3R72?7Xr/qe`=k,>YZ$0j/\r#?f2'F?;`cNRgM-Ol2S$&40*SVbn
*)!2]A9mm+B[j8>rqe4O<BgeFh0P*NKGH;Uq1^d8m9T'g]A7XXXmK/UZ0Qb7\mM[a4nD;5a^9>
0$d4l'`37SPKY/SsnfkDIlBM^qN(Z8X*35-do/1R(6q>=Luc6B.i[N89a*%(U,T0eFoG]AX`f
1V:7V!rD2GR?JuU4bF/'LaHE6,Y9YuQTW$nUr\^=GdcHq/tS[Z(jSit&"Ym"pMb/^aimYAsD
=5uqJmp]ATZ'blt\7Bp/62&mAj^#/<OkF6!Qdq/H?Ml$)%H+g";/iaGNb"KjC&m%"kh[0]ARI_
.^`!sjuo-#WB%l"\Q'UKOjI0b=0$_M]A]AIF"leS>^Nrlm:or47@Zn.]AmQdZ3j=g8lMfF>4Y3n
.h+B]Ac><G9g[rS&:qX@7rE0n1h&SXQuk)T[XH<n&U/WErrq(9G:oan@h2;&AlX]AN5>U;O[?f
sFuHA$:GN"YK!88oU&HE=1<6^Rl5p0@\-J:q^=H86KDNTt(\XU%o*n<olWZcs29a<I=);E-B
R:0?2j$D"J;SH)\>j(8lJb5e5m^1R):EGnWYnMgsC;G(!rW"bo7^Q[Q]A:mF56[%PWJ438I['
'b2LeaAt@D**Ytt%kp0u47!n2YjJhN6I`ss^=M@!e9RtDd8%fu)aP")mrQBaLZd/$Ph'LG;N
5DH&:27jE]A(B`>S+uJQ/!he<8\?Y\+A'T:POE2:I*UZ.O.(ZB&oP'o39p`!t$7[kDQPJ(UHd
_N+u'ig*=eZ$sO)nOlOj+'F.oWYp-gAF'GBW4/:%AJ#5*1bAER(3;.LQB*GFTM=P:d<Np%A[
4(kVC:;V]A'f^nZ9&"+Qbn!dtI7_$13aCud,L;mU]A!3D'L3b3CXu2)di@$(!U[cL(jss@41!l
kf5pF:GhND3F*UD\?4D=l/UJVJ%7Ims>$<;lC?9TL='hk3ST5$M8LVM5QA?U2_lSgDfn(XuW
(!HlWUIrd3'&1fjdWQ?:HWt");!<ODg2XLN&R,d8:\3cRBXgJI`KER50:&[ecro(EVZL3npm
JY_l1/F*4p_O02%@#VQP(moVH<)4rD@F2N(^3,&DO!!'ldMS41m%BGdd?M3AdAO-20*^'t8X
:1=/Y(kO!'>(AUL003N5SXR!9]A%[Sg]AG(&Ku'$59AXSJ5RNm>+G2^l9DShA?=CXF4(afF:gN
8bi085_Q?c23(kXM">_fS)8Z@]AHXl/Zp/o[J:Y7.!p*G!:GNc%bmu^j#";#p@ceW:Yh3!Mf4
t0OS3c/HAfm69bDW:+JJ(E4T]A\@-YQPKdeZp"Rf`K5;jc1TKT:[h*]APu\KaSJE3FCGYCNg:i
M">C'3cq<t5lMfprTcQc%QBa8cQW'D76iJiT:2\8Ed[-+9W+KC4-a_iD5GXo.7RQ7-KALTAT
=FW1&@r)$Rt*g$7<aJePDbS_@QDX@/,/8XJ8qI#]A^P(3>e&J&YP#pEQ4AAT9N3-U<Ss3(Q1&
lk1Z4=))pSrZbiBcZrc2Y$Oi>XQ+&[%iP@I6ZPt8_3S)?VASH7cAu4r&,e;-I:/Ne[HM8bXU
Yi0XmIf.NWg_BZArKH3A%CTmIa"XEIUAq;&jA6ML(cE-eFXlL1j8+GD4>R`cj(&$*3GQ7U)/
&Y>_&/bUg:+C[O:-YRaZkTMB1X99ak;+d>6Woc/dL+r6]A>;G\)ljmeH=4]ASH"9O0pq/Htp^S
Ual8U`\`PT=U[\g`.6S'l?u*PLE>A"9Se7>$lkX"mmam/ok#`.8$KTMYoa\qS-7'0Uk&\O:V
(kBI`#H.cft&MI!Xe]A.CId2;FA1L""7lZq8s'']Ae2="oOKX!2!=.9]A,N<C>;K65JQ=3MW>Ii
s!blk;qt:CmlD[IVHBna)I]AiAj`%`=L9hJjBVFmu_51e?4qAi]A9"_I\H2=44a'i?T\AH"2iH
F^V#!OJcX9W)0rZlqM=2-Uf_,*?>Ir,H<+KapXm<JIDtUO)J2HK!`OD$3]A7+&s.2LR3=)IKn
PefghoL-C9IPRX;X9Po^t2nT`eJfo4ee6-d1pDr?]AH;=W_2*_):^Z,F!mj[,``D'P\k?,p4*
JsK<@jKlq2q#Gm%$?T0H<5fdM;UVa=^hui#kB<t>EejprL_G9J]A;8n8<L?!#\[Y<O(N\eM<r
0gT>udioQJAkcZP9#MC,nPhF-'c*0rQ']A.]A92Ba+!ESJ?0pM+Sn50Z^^W,CV0r:EJnV&efEK
Ji?n^D49.KghYpS[PFn:KJ3qfA@m9p_mKIH4R\GR[q>-bN%lp0hQbQ4g#d;6V,0D==p+#g!@
\?]AZZdFLdM*cr^:2R-R=R26DC9q[@bp9iWVq\r:=po%=1SH@E,8dIY#1K4L@OC`GJ4OA9(pu
C_"bpLI2cY"g\Z_&\mqjNK+_kc1f]AF[%(,42)-`g2rTB+r#Vm^sC@[;lL#O7naf&!'!;Nmhp
.WOpnO9Z0!KU"6=!G<qYIH"[Im78K#[<PT#F.q=#N/b8^&1]ARi"HQ@QU!BUW%r17%PgD'_6<
PO;l]AMt&M%ZQZ0MO:h[d3[WgqQF0Pa<jC=XMeVgAFu:9>U#*5U")#Tn0Ooh.e=0A548:P%E5
'`BC<Lcb.-!ej1uU<5PYAe8?_i)Hgrdd05"2&mJ=7FP,=(*ng5c+aC@_3Msq*8[<&Z71c;r[
`1CFR%F#Bb+"Ag$GsMTJ_T>K5mm-+3-,<:,6nI9mmYLnn*QG</\SfU?<%_h*(Um70$7)V:2O
>fQE]A-9N'Wl/Zp!Y0'mj=i]AG8^E;.5e7rbOL-/<1"@mt&I9]ARN)@<I;UuP.NSm9#-!"FRGZR
Fc5Nd#1!Z>Jo(/^9u0/Fp'/Pg6dhUC3abkO5qd[)/&7k1_B$8mAe(nq2>Fr%XN[KZ8!B5gC"
&iU<Gr(9.GR*?B3X\9iRb3k+PQ?*/'R9T]AF:FRr,MU\a+Mni67Q%\g2RRW^l\812kP0USAP2
#^Srd!lAl5qgU(;+9+_<DFLoa1<[\#f0b9c?&]Aii>%%d0Pg/\./r^7B-an/fOD*\nOH8r/YF
5/QDBtB5U\B+A"lg42/F5VWBkVi@ukJRHDq9*Th9l,*8,2d4s^)ab$jYS4CG.A1[>U<noEn9
5BPW;CJ1FiZ6DK:62K!=%i^fl,2JOiSV_N<Y*#^tThH]A-=#,tcV@>169I3%Vl#)ODu6X=Nd>
Sf[\_L-bRBH,<5YN3cK*-3BkFB9!\8k,^Z:[[RRr\.*-'4;bO6hAcj<*%=jcd!1jKo<I?[B"
aLmKjRd]AjIW`SGF<hb*^+E-0\g't;_VOV/l0t'//V_d&/>7"\,afANCCYf--Yl$X(_sXX=WU
7Ehc]A80j]Aq6Q^qUj4.-KaR0J.`e]AS^@-d:&KBK:hY/9Gr<5e?_P>^rr%O8e5^LE,W[3X3,#+
T<r>cYJrMJU__;mAeKhigS-dGB<*t071C)4'abqNc&FO0rC-^FJ`&0=3$6]AUGHdB^PI\L/Jt
s1<!eH?'NHhR-3Nf+/`CX]Aod,@A+2@n9#gHM$Wu%`V.r/-R7.qCQCX8k4isRUh]AsAJiDbL4O
FED&EaN)\>:7LUaemj=tIn2;0qHSacqdTB4_6Fcq6K$$^f7-nkN^1ND1F^^SW.WAFTi?ZDo:
S3`bQi"oRhWJhO!]A*Ce&E;;=%^#1>IKM/$&gj1TWfDYfbcS\J[T<Hl6Si',^Z2,Oeqc[gSq:
=2)P`Hd=SBAZ8eK1eWFDJ<aCRn,tWI:fANni.W>lpoVZm`lO@Ejl/"G)Tfd<fDq!Jl6shb?L
(BmdC&6YX8Y=D<$%W8$ah*lk7\/N)^MC;<KO1d&h^C`q`dDl(\h=C#MoGh!mEn4ZZ\*YJGrV
]AnIuGCr/rtn6P[k7e?,nc`]A>A&R(`$G@6&C8`O13hMP>1NQ`.s#Y=7J_(+ZMMG2q#P\h!P&>
]A.a0Z>IJJD^^A\*MMn)5mq,)SY19atV/@Y0ng%%ihIo;85oc-/4%4KKq\9>3N*c1EV/iJlP5
L_*gdH;!XuBK/@S0AbRh\i*lLgLkMMk\3.r!t@C]A502X_S$AWVP4V@/1YQHSt2qO(Fst+2m?
V(M.1.Xqk3&9f+,_*^la@kMREu,b[*c`Z($c-gZ2:.dO)&oBoJi!gNh1PFm)K'L(p'.PFeWI
Y_^fW;S@.i7V0?&5klPI+(.MZM;hTkLH;i/&+WUZo2SkLkEVA0A^DqC^Pa"XH;0q=7#(_)]AK
nL2oXuARTCgZ]A&`Hh).':D8^>Y1Vj=%i5'"(FZ`AsALkLUr!Js//Z8PoSLKCcP>f_:tc%D-/
Or'.i2QkQd-lDs-K07-Id[Yb><q#FQnH2V6I>j`9bhC:2U>dm?RjNcp"Sj_:c`cW\!\B=9]Ar
-gt`51Jd3s,u=ArUeJlDk<Q'X83r/+Vbtpn01iVop@e%T!q'm:^dRaWo[e-9N?m\.Ut>0Bem
>pS]A8\*^F`82XVY#CKCbL!6(IF6oH^=V)&6Z"2=4kQX*>\Le=e9keJ=-_ch:WpPA1ggeA#@Y
L6!3Y05lJ'&25INGj+LJ&BCEJ[Fgd`DU/qcN]Ae^dVQshXAT4Oc#ARt\l*Hh'=p/Ek`L*C@=?
T-WtN/9M72%QF,E>nReotXAE+K,&rJV0M(nc8?8)KmO*U9,"B&5V>@S:'fc'=0qmtdhn9fLM
?tIhl\`HiSr4As[kP:[<eX-O,qD<`5okILC(gkMoD:3P##@Hf"M79m`q,_U>k/U&5V)J8[Vt
XLiDQT?k)lX:TPJ7%W9gp[PN.(fZfiA0B#\QM:3]A*BTdcPda`+K:flB)n*9t`@l$<!rnYa]Aa
9F5PDF>W8!$W#M.naDFeK;(7KSDI<,YZs@n5!Z&ZT=$EVO]Au\bW_;h5;FQ7mkp7r#rC5GE8)
bZ3lCdFQN,'&uQ+WgAZntsX7BLXGY6nh^UNQ=.k^8u8=l\&=TU:mPd%cBG4XgdZ?:(Vu%hrY
&Z3MM!V=3rCuDgu#H\r9LfI=LMA*ZBM6GtPnr:^eP&ln>q7PEVt,p_9:eJ9pQe6BA1Tj-q!T
cQ^knO9\Smadck&Nq[5X\+:;:o<lmb"3(DjDd/K3gtZQPG6l<_*/bjf(J\XJjmdLB+[75M4L
PEjSZLeiF>;b!ZI9YZ_:oLA/0[.4Sd4DNJd!hic3)-@H.WU;:61aLN3'-mRLO/!M`f/$Ht!W
+L-:uUk3UP^VUr2LHq%9`#3Q?ImeT5Rm;K8oVQ[?iGH,KXj[ut,]ATS%c9Zj`s7rGC2c!kt>"
#-fEf0i*OK8s3kmek2n*#osaHD([4UhF8&U5f"NXEUe.:k?YbL#VW:0/Y9(#E-/JLsa?"4s]A
m?*9W#DiAjZo]A#6jrEF+o%orS(T4P/+I['0n5_2h(fl7<Z1eKUS_4>lb?BI7X??]A8@"7`5Bd
F_<5#E\8U-nYMR5EdNYshX:KMLVH8OZ-,_-bL-/Pg*Pg!WtXT2K_aguM*eg:nJ_u6IbjS':N
4m#lpNqs7]Ar+rO4JF)\<"l:rc>10=g:8oUhW6O\ZJ.TI,*F$Tjq?7=0tb($U:Y-PW#RS$nP]A
T'8KQ>^EV+YNORYcb?rddD?Uq$jVgRmLr+rP+mrC_!>[D/n_K%c*YEZ%L"o_>]Ab@#b[*lV$9
\>2NfG8&5k?S6$G:AG_jgRRO[+uj/6PX)RMB3aus1t7bl180TeYl*W]AL(YShG!iW0<7rYkpG
V@\#]AGLCVh?RC(K^0R-6ubTHT[^)[!T[Jm4s1UM\AtZYd`lk;-PlRe(Vs1:/YtIO2M(?rkl0
`VWY8LNE'[^22krd?Z"UN18m9&JgmlgU?/1Yq&"P:fpC*_+j]AD>M+t$kK'C8)8:3c_nDT!9X
u`n2'.)[r`ZqfYdJB31_'+-R0d`"mBpucGO+)Qc9YXnG''Hdn7TlqGnJ48cXE6[+JVY'HLpq
,)6V/]A+#.=#QRW*&QfqFlj7<HHrdGDK1;0$DpAFMR[MaF\6F@:CTs&s?9u6@E0(F;Gl2Hifl
]A0qt]AHt/sotD5Wn)l*aB!4+@6>3&2q'H\[&.uVYOJjp-(H)Jb>j:30.T3jL=)p[;+l%5.6ic
oa@;^:m@_Po;VIIfKa"c_GGJg)F1D`:.+h,<:]AKk8HUWE[%Ab"W#Tdp^1-f^(I)0]ABe4?WMV
'>'4J=Y6SEI4uX5aar0j6rgX+20rI5<@PW`4X(qTK(2@%T]A;?;0+N_^WY.#siL5Wo\MjE[&t
du3EMqH!Zme1_5.b_)#J83dl(tRZckNNAoVQOD:P)lWQ!J3L$L!>?^7[;e<uQudg*g&9e=$4
o-Jp\RWdG^Qb_Y8+=$OYoa&sN6COD1'A8nJrTYuje4grX5aW\SdgZMfp6Rr50gtgkG/LJE,[
--nYS9'2F%'%#F=3?9YPSta`f4NR$bMj51^qIW$n*q&[$R_#0]AFOk`X`CL0j+rDk'.0e'9UN
0.5K6rANq56TL(TT[QYoVTDK3Wui,NXc362G0^mn0BaCR)bgjW3ci-\6X1(a2K5g$)YlBiuF
c'3+o?">fCi\<!eMTTq:R%p@?&AM9C?ssl7WRJ?FEW&c-(F3c$<^hu#O?%OR9)(lAlS72gZ1
,sU\Mcgb?KGs^?JWn2&]AUdlmXa2u>::r"0Zp"uJN'@bjORVOm@Y*_[f*f.''s\P:9os\/dd/
H*?oO`]AmF'"FQLi90"P,m(8oX2["/<nHSXE4QQ+UEEsA\NC;iuV?5K(!J,Dp9"BS0&=279]AL
Zd`i+kRmA6rkAcYbXR=?EJg<M\UuC>.cCJhN<9MU"uTWk_A/7cC6'H<JHWg1K$,?35UfLn]Ae
A-2#,h>#Q56Em(X##338`6cGYttEJIFt4)u0ZQ:K(O"H=eAHT8cg?7@))]AY+/O.0"4P]AX<e=
Vbd$=[Ou[sJ;a`RH8sGeDF+]AHa?Yjt\l\>;AA!2W,,U%jq]AXL5,fd1UEcCSC>7`DtNkO3#rF
9lUcjR8ncXE5Sh#(Pa/IOCP93d27S'[[r63Z`Hp!RPUluC43mQ$#fmXdjJk3R_8iS![RRrK/
r$CHOWB5]APZjo?k0-l/k;5!"R!:sPg$!8I>l,TB0Ij(K?uGQIMVj:62,>'HLF<G.p\!A=ndI
ZPSrj3s"C9`Z!m\R+P,;I&OUaX*:FUtQ"Q:47([4?sH72/[h@8Pl4#o4!d=n:A!]AToX9#Eoq
4F\[\@l:sA;?7s\KbQHYpP<5k(a3>k#G7[#O=VRBO57^2+W1g,&-W=c1)PZo?/E]AAAff8S$n
?5<Ug]AYNYlSl$"32?rk1\(Vdm'``SnB`$)B?ZlAEX9)j.@;cOB4;ic%_bE7_/`(5,,Z-Gj]Ar
AD$g6nGTHo9]AqkOh=NF+Jr67/'$:&3.hA\N7Y'F4&F&iXn6=llmA*/=2/NgAr(G.3hKs791u
R`;I2qA:+1CNqspnXUc1CA'LU>.$uU#]Ar054Bo6ia#RJF(K3C.8G!cbQoU\qqj!u>h6(92_<
Ipbg.`sFYOq&9Vn+46;UBVZb\guB)Mq8ULdo!?:nqf:Y_P`:SAG+Q5)(j@n#J-+2;CpZpc6-
9F#e3KhC*Qpg2S6+LHpRQiqPZ@#/HKWCkUg$Hs%5T8Ml7W`$rJQV8.:Fd5OIW\jX((d[$/fc
C7%f%`PZ/bD&'E<YaIF'ON_Yuh8mDm)7p%g%]A#\%KcM_V5H'NA]A4JX/T(ZQ%c0UY\X./&m[K
Hdd8pImoQW!Bb6nZ#pHWQ=V,c_)4Vjp]A5<FJ44?M'6r9Id^(?0oB,-p9r7G;4sUYD*Y6>kaD
/K2p+$\l`^s7!?H1'3.8s0!kd/?l:;74<P57?+6.tp9lak7uBoam"klm@&&(kd-"tR$:MC6q
jknQ(:MD-[Ra''14)'VT3Zklc&`N^L>b!8fPtS@gr/B`fVBo)\U+6RnW4X<:OoOdBn+(q.pY
YBi$#GemoYDTa#e%MerCaOn>d19I+"aWG]A+[@(8V"/qtnCaQ_Z]Ar+Wgn,35;\nmM1i1A5$\e
^;>2p1H;;i>OH=B15Q5hko5I2E&Nb4RYe)4gAGo>lZs:.SoiRo0)a`DoVrAPbR9>q--GH>kc
0En<_+T"Z@#scJ02uA$,0HUQGiVf'TBVbCS5]Aa-3!EkqsjKN)r/"p-]A@+jb0o(Se82hs.hf+
`!<mi_Veu_n:guAI$jsEtlp(7/q&p-l)eOnP-[DB1o)3=4GG6,9,4=]At[pX+Gl3fI%jQifp4
&",r.MGB6j7JKaI/7#X<6t4eed-2\red\a976LpHq,6d"f]Ak1*&o;Hcp^\Phosr(KSOtJGN1
Yp+$OIk`["-U4>Rpn[`u]Aj$s;3IB7Dr$Rq4Mlq$'rHe8O/R\X(?D5b4&Y"YWf4E[Was.SV5+
P15u*Z^QGe?Q'64PH[^b*p0^>)MW?]A=^3c1rb\8!=i0tt8(1L=%.p(87o]Ae\ll3p*j-DDN`*
Do8)X!-8[kWN<oC?_rWtKXb:GQr/"TDM^Yja3(r(:t%P"k]A?D\h)Kn.Zbgah%2(J1IuXClPV
r,B1@'d1(T;5@Sjn0T]AL4oeX'qek0nN%K>0a-&RkF4uh>=Q(>T4D567*E;_MoQ_D(AP\sp)]A
VM?Y_gUa8M?O^aKotDV,=m-N?sU7`dHF9qdt8C[&Q<K3PTVJ*mNbq8o6-d0h_hW<,&L_qgPq
Q%Chc_k(?aL4h:"eC=k2XMA`V\K%[f2`-OkBKc)_jdbQLDs`4$KrH`'p@pK[Ak=gl?e8Jm+K
^cM+ek9m6D*KrqZoM<W96MO?;:erl=BB^WDF[p0]AKg>>0'`nU(Ou]A=6hlg+`Wc>`AOrQ_]Ac"
44_/>K(qdB!##Ra!CePkK24hPL!*96qslV>-EpWfPgAbZcKfad!m:3$UWdP#:rd0+cN/l)*9
m)SS3rE8uOH<&>@^,ZM`R]AI@pfR)[,#/t*TF9eR+Di-ot:2Qo8dqZ=On19iBCREQ2a^Y$jhn
u_5[)Ab5F&BBNd^]AbD7X\l).&(#l7.98"LJ[7W9/<k7-"-:CUr\2UmEQU3"0R>V0hNKp'906
3a#;DL/UFc2:AhrfU$?)QO0c::XOV;OA"_]A>-"AdpQ3.4EaApC#W>W'jte*IomTg0%E;>Ofl
P*uK5OpWB8%F[sT24ie@-g(2ESC`gPX.8d]A3n>9c(iP;g/IQe>OW>#DDbFG=8'_"9B()+e;'
l\-TL*_\-;+9j2&?`41dKp3r?\5XP'k_jY[*JjY*YsZS9T_mReh+"ltdN.7%U_VCK4'1KdV)
gmF_?PkK_.]A3;o4>Jf/QQ)q-W!<\[)o(dA3Wb,OiA0*A>]A5+sU.:5nHnAk$G.J)@"ccT-AY%
^5"[r2KbB\!;9(*asDEq9@UpQr:b2*F(tb8Jfnpkn=[=3Fp?9o1nphYAo3V,ou&+KK?mNeX-
Y6Ub0B,PJ0_5@HlQ.%MLH*EH%tY90Yj.`7?J4SaFtkb\\$M(7W^0<[&QoVbaJX%BMmki[GA;
%=+nLVq3g!/'>h(@]AH"IJM*TO/1Cdk%0miG="2O/_I!8k*[q,)nC=BTSQWOX.+mGp>9$V;er
U8B$8[*nk$gl&j)dl=%UANEM.0q-)[,Kc7=dGH^9L/j(Ug:*>7Zr5Nkaj!Fp?9(@6$&R!d1d
NeL!fiois0]AoiHl/(T=#M5DUW3-^GtC:,RgQ"07_6&;n`bc*2`qYu^)V=eqLQH<Nu&OX&=Jk
HG=WTmE]A&daZ[fA!:-6bNr'LD(;g?0l!F1od3IN9"*%cNN<P3,:",:2>/:FlFAT.PmZ"m/j<
9#Y>Xr[)U"iY($:GpZh]A0thMRn<#nCK*9"V]AS4bOmFNq]Ak=aL@V*nWFUShgd8h'B5g'IL*fV
$MS]AbS+^m:nPUg@*)^q4W3'\&LLjY'MYZ)<@IB125hea;;UV&fJ_U`l6C7?lQ8**r/B*eVCD
Y9r1FK=%Y\*,f'X3i.9fgC)^ii#WCGA#Uo?gZ)ZS)#_*m->6$E2W_BVs-2#<ufb7V4Lbk@sC
5]A&D!hi<pH`DNa%4C(@AtX1ENGfBoK^I8#.Ns1ne]ACo5g\VL,bgZHD&2_i]A(Ur()`G[`AcW7
-^10XXMHT&;gu)\ugW6J%'1OBdHBT^JMfOmWF^tYjeHUNCB,%[Op?^VUt^G-\3EmTVJX;pd5
(gJE>Y_2nS+V<K@D0GQdo3C>Cp]AT5I)U0:WEGn1rq]A<T5l_]ArIYEB;^NQ^P6&H?T1#X8[XWU
b>n;:H:Ugg'-j,VU96(bGA[8ncnQUq8UB_+pRUa#RI\M"3D(!s,#Pucfk*$Z)PSpH?=e,Gqe
0EMrhq(?F*AoZeR]Ae514W8.Ge-hP7S2+::5Wa`<Ka1P;2IN4DYcZTpH4$3l'(d=T;%KB[#a4
s(>@thOAgU.3:HS5P.;4?M>W5[?hA*j<r8Z6:MD.QKB8^u>./1dQ)7W*$f<Z.;f,KhMZWQ5L
h+I`//bW4qV*k[EIl*$%Lh>p,GrtL]A\4bKHDt&3d=f3rJ6,+GC="U$_Jeo.l^3-Am7[qrXJF
>]AiDXKD_-TukPbJeKZYd_t&Irr5WO/&#pS?:U19TS:;&n)lVY>,0SqeC5Y*0jH0'h4^R+WIR
Q)<V.q($Z(loit9%a%*Y(&$ACl?_<s?eaoO@pL@\OR-#jDB2g0YO?e[@/Zi7,LT8'M:Bs32K
fA;\U70r@8oo@(@7?+iOEYn$rYe9ap=a]Ae)ttNQcTrMd\,XQBDTc"+hbg.<5Eb)'2$*(:%c3
K]A08i3GQ$2dN,*3SE&iA!h]AX]A`=Ko,F7tpVfCI8"\4"lEd9))IF/d_hCpKmeMock2M3EXD#+
3aXPh/S<PdD:=o"XD'9.gsJ"jg$;rA7-dWr6hk+n7_oG_g@`+91TID>uQRkTg*7$ft`h,O-[
$<JpEc4Q1;.s(Q>oh<id(oA%EfTrF;.N-_t[K?6[AbI@JJdM);g\`.!,)kN&Vu$#b:YbqHC@
e^02R.oQk';/Xi,b\*/BGu*[nPgn@o@feHsfV3O]ASnp.!:5Lt/:VT;a`-i%N/<TQ^q4WDDO*
R_qZRJd7!\iP?;`V\05YLFjQ-P@?/Tc67rEE>sOM3;q.U\oYf4UL!;cUhNS@n1+[AFgIaJ<r
^E(J@<n1m36`LXe)eijZ8_D[.3?kJ@git$S+h]A%r/K'Jun+u3)$($/J"`<^n#H#HfjTFi^&Y
[B*g]A]Al"`9Kbr2`X1.?'?ZB^hYu\8h.(]AK9:X`Q*tmmHrf[cuEjUuK%e0\gNc`tqagCg71A'
iB/FR=3R*fi]A^YVN-"lH*tmr%Tk!9'>YpsaY;:]Atdt(Ou6"ja#/m:9hAN(BHtTn((R/ah-`1
"^*9WA93U%pWcAta^bGmQ-#&6IuL:Z+1-74K=ZL*g.E9%kVf)<HWQ-c;7filY#,?)rI/:+R5
bCs"pWjE%=)aLS`PYXX8K.V^,+?@1@:[F(8*!Yg9%BKlN(<"<BXPS,'rc<+<>e"25AU&(M]Ah
!lP`PlhCHqJZH:e:";9c8#KW?[Q=3Ph+]AJb8<B:XFgjIh3/*.5m)gM8VO*-cH@CYObkh5ADE
)$"n$<^ki1T\?t:Ii["$Xsp<)ECHh&mpn/J_2gFIK>1TI]AWPhAc:dG/<e$XrcQVWaZl@p?iF
%+mku#pIdC]AFM)<cMmW,$9YFSNW>h\jYk.;]A$`tK98^SU^,Q"iI0.!Zm/6\q(Q]A<l5tG#f";
&Q/2VmC&pO;d-T@^)RuY\Kis'MXH7W>5%fO7.XXNXnH)L*Xn[9M>[B?jtqCj62T-P_10:k:*
'!MpE(7a2#C4<^L+mn1AAE;J7kj8$P[$5R:+loFH:s_WSa<G7K-%LkPDrkR-b.Fr0F382d`h
/nlp$NjSC.c&#^77<FhEgbMu"sJ4-gN]Ab]AgU@Woh^[sfaH?oQ2='T^!5'c4qjm-<lAn-2D:K
_%:d%[u6/5ZJ25rXhP.i8b(s$8ITt9SekI1d4S=Zr=Bl!L@sQO`U*S%B_kch:=*)qA@tsfO\
qC#.cGf$ANFWT4mp?NJ0t^S2h;I]AKCS-0**!PQkMG1!4p<1<:=Pta\c@C_C4E17d80qYMW4.
U^Yb@YidDeLh@Da,hi3,Pf=$/p1Af\=Qm%q6a;TBL+37\Du2)c^-^DL^.6L";>KEGO.4aUV"
cnO*LE8Ich:&)Q)>JMYo$^(\SDU^8M'D8YJU:HQb_8,SrpAM$]AJN&=&qp:N[OR=L)`8rN=^3
b4,%T?s5Pg5X^D(q$;%,^SiZTuMV+H-9YP`TAKaQl@%oC!7/Xoch4RgYo3O8G>?DH-l5;gL\
GU@f>VVFH0cNO;#kA7#eZ<'Pj<1k8M1H2/A=(]A3o07Vu^pZad\/\?T=Wd:Y)fWg<;9SOEE+*
]ADl!n2'!=F^DLM^0-I/k3M6^olWUMXjb?*Hhf]AUcLj5N^bflTnZ/!4G+T<\j`m5NZg,(auj)
ZY]ARWj?7:U$WGQNY?`#\Gf)ufH`,88LI"[Jr9e"NO)>=p`2:0;VUXX+:J['1%4I?`*=o>[>?
fQ&%P'r4HELh+Dfm=qN%a6jO$,N`d7AXr]APSS,GqP>!9u$(sNTi#_p>bO&NZH\Ln>)Tb!3%1
GAnQ3N:nug6ds]A^?j5Z920THT>X*1,L&N9QAT,`-'`N><qY^.fnDRkU.f3M8F)"\NAmYjX8O
t6IkSa2r]AY1H7X1ng?#)iR"-8,]AlJ`P>__6fd22o,d#Fb3pkb"c;_?]A?WT<=C@56Rl@j7)s:
K#LMus3%h':=:Ttpd.t;05<=I?G&gZr!fXP?pZ9X9'(H^'qfN-.<HL/aqV,m3dAJV)2r1$]AQ
WgXi[h;GQGO?&^[dnYngQBEM39mQR%@0Tu$R+8irL$75k*.B^3.st6L\,PMWDjIXFr3k_9gl
6IN[B>9+oQaM80,L)6E\Emc85Dp_he4Pncg7'+?+44`DI)/4[Q[iIGsGUY9]AcA-$;'N3mN>6
BZB&E-:&m-M)/d.\Y*E6U[Cn^0i%.R@">u5E!B@MX#h?aM$,'qgWoA6'!-R?())&kn&D>7MC
[PKF[Wrb'o%<n=<8XWf"#ebVAW?3ToB2Q"k+.,Z4(YY`cY:/^crJ%3d#N07kKDXK^[:Zc5^8
/sO)@ln=L1=4WIMn"+i#T`/c#Y4FHJC`_#DV9r]AMfOHOdf#\*1-2emD.82]APkjB;*=8>.[A3
oAspto84U>2sdnoiSp9.B/RD"387ph:;X2.@]A]A4[Xj%T<^7mHRW&R7P,9sD&i_Nl"&Yd;E$)
gf34p(ER',e$klJ>+FZ^k__<IO-n>jn!O]A`c9Z;f%k2#Q-027)0Dii9P96YWV,o+KHs%13h>
C-P5=EHs.Stm4jI#hNiJU3GI43R!qu#L--B73FtD&fP$OmS'$&oqjk>2Xa0TJmM=&"r@.:9T
LK#]Ar']AcOehqlnHO(bLkl%<!*J7q/%9(-:#(5[RR<BfrH#&SHClaTt-]ArN3&uhI\FR3NVpI\
sG\_rnRH5YiN4Pug[4eM_lM)!F)6RjHT2r@E"E]A'G(nRkCJ>28++RA6lZr]A\*r0V"_CWCDI`
[IG,EEd?4/1>8j.DVF,Jdl#@X>ao5`aL$jbaa6XZ.0tUDb[`@(dRD:WI=r#$YP-+3^(Z21e"
s!ZD+AJsIO&9-@l;-TM`PKD:Qr<HU`MMmp@>e>ohL0nZ,FI85&]A9nX7P0c):+rjk+^*^0X=.
&!hH*n4@>&,nq[!A^)*mG3DidfU<V(EXM91ULWXflIQGEoqtq#-1-btd9n6'KPpYcJ:s.P`.
Pu\cFng+*@`uiTVP"9+bXd,O>)\,t4@;_Kg"%e-S)O;+PS2@^(MF%"NGqKhm(0\?EQ+:bM_N
^B0Cm;DflB%(!P>Ql^H_nI=\_$aA(SCA:U*T[BcYI4m89g+DI/+e[s+doT7\+28C)'Y"d'uf
I5<fZV]AnT3&u#5jl*<fRNdf.TFu\PZ+#?0QUO!$'es4&UhTs7+iZXpd^(GZd<^4[NcZa1_Y,
3&j$.s%A,HklXj;h]AJT%buNM9,GdNXJ#!+V'<>!#/2ACDRK:>"VfUT`s`:7X0qEeK$hmO(M!
qo0NN0KKHKc+5_'GM%XS]AWQ4\X*&;D?+)L59la2g^M&MOcbI$#cUHdkE/-;8t@3&Ql"mWT?a
qa[s(TBgPeC86G?Ym=_jAif*YZC3a_23p`^!9t)A@cbMGBto_FIrM&^e!7m`cZgr^csFfrjW
8Yi/$O`YJkEs_Bj-hh;/g,E+'J9ph3T-/Nrg*]A:6Po"K2/8RC3(kT%`[D4L0Mtn?[+a\W?Q5
j"Il$Ir?Ir&D?O0UUQ>LPipfm5MidGB-UahKFQ;&k%aQQqtAYNjTXpCI1#i(HD7/`6Ws283^
4KSD.l$&A:HV<KF$4#(cL')bh@pOTUu_fiDaS#bqu8,iXu+Q@*0B>&1tsWO"K/WJVi$'\+X/
rg<%+bV>[k)9N(clG3RJ*7SA@0HPP9@i::1]Ap9IU]A&eQ7jO4<S"68Kinr-UAO\U44#j"K#YI
rG8XY4_8CKrF2P)B*dc:hm;?PP^:56"ohL)FBNqruN&E6-2AqfM>AkBn.n&2P-r%#=)?UXbp
QaOPX%Bl'=R]A2O.*nrAc>;P6T&sd-fXScpd6P5+p_5?M&`Xg]A-/4MY>XDV&>]AefH);9]A5(Dh
<;-t<E%=MbT'=!<V>g`r9u4s#e'e>hS35J#J*s(V_g,EbZuV@/#O`i>pBQ[YTc1o=lsl1;]A#
h?+'ZAAcK,@565aLKQ0&">rW9mnM!Zp'>FN_7>BP-WqU3m[#093)3eX>.cH`!,/^<Ye(BR]Ai
rM-1Z`^\7*5q'F=J;gXYG7_!SJrF_s)C>aMjoA9fnqne1+8\KU7*sf@HA(3ZV0006PpB=K6J
<Gs8qROL$eOt(#;ZnDUm&*&gKV5.AkIs\U.uR8K[ZtQ6LMi<YaLhZYlKmjV;tqr.>P<&;FTm
Z_jTL8(^>)RHKZV.R56!icMYekOlcJ"T`,F_p]Ai]A;IbP!Zo@/<(QjWS=bRi_V*CAsEibngSp
\@>)ij(Bd[2tCZij"aFDTG/VE!pU$TpD'sPQ2A;_nt>&Tg3n(5X-auu\%8bmi"'0$U\u/,/B
dKD3sC)1]A\,7<+YBLao-i%D=WeM(./`]A#E<a[d48kE6ao?aC[srKnk/gAML$3RGggSuZ+89f
'U\;q\_H--lRu7+k@ppDKa7bcG5:GocI@\9]A_#;$m=@#&P_1.YHmVb!pN4MfV-S$\Fa#lFPe
S3*?)>g9as-mWuI@=#`&jF-qrG6s^6RFUk3<42fp?s/sL1&Mrq73V)P=5%TbMT+l]A\0@_#76
7q@JDKJJf=T6MDYgfr8k]Anr3Jb6kO@Rd5G.U5WVHFUr]Ag@**pSf2FCKaqDcct2QE*=p?]Ah>8
r=hN!2d;U`&VBBH'P?(Krt.'dbJ&ggKui>?0jD]As]A`0TJ*_b'N*7X`U?PF"VAD]A=,r?h^@^7
`n';V_5;1Ni265P?t?MQ`W)%g>2rB5.AJeMT4.-nd7R9&7hXgt5-5]Aj[=jqYO94N8h)A,%df
A%?r9t7"-66+aF3sDsUinmUe'F68Ou&"7Ht$=j(NND!:lDbd*lk00&@p^=agL&0Z;mJ)eYsN
J8'APi'__;U987p/O?lNrTJn(;S.("5Q1a[+mn?cW*qRVL"SW<ng8=DCm7Bo%_jnJ]Ap6+1H[
3E1PFb8jRu;!_kJ7]A.Jn&6r%C23%6Q=(!RU%ZAa.+n!bT/(Wc.^:QI;"kb5sr&aXd7R"?8m,
m(Z+pW@hQHDg"EZJN?s6Af(*IV_!nRpAX*KC[WYMcgMG3mp#t31]A=cM5BsNNE.9O8E##]A]AmT
Z"BHt2*fYFb\g_Ne<*4a\Xq%38D<SEJZtGnl9H3Ds!+g;sd4qee@sCe=#dVGb@9"8\K=:l=#
K9Vu\um9_Y:O#-3r-r]A?)H>`\0AE(Q?96pPt_Z*C3R3k!h#!+L\9t;1h^>h+n;Q)gViO7uJB
D<iE..?6Y]AE<`q:J`E6HaBeWDr'oCQ<-^MfBC;Cl<(GpB%dmohXRMqYPD8m^?g=J_s95t5WG
R5rIQj"[+(_(:`fL+:V4[OgK-rl#H%IkmI-UkJ\DpIrqSF@AFfXY*TbTE^G*&qPg>m@J+"AU
n_9Zb9m%ihAqIRkLk$?:<Bb?NiIBhG%tsDk/$c5RjI:8^1u]AMtW3PBdPT/Iiih_T@V#Ofm0:
)OT9&\)P==PY+?HE/'WOC]A%-QMr?`3QnNkLRXEU@Tm$ce>3CA"DAtaKG&joKSIF*4GCHo>&,
Dk5U^#VUNM-\8hBq2cA/i26hS:)Mg-M>`X?)g%ZARliS>e*30e)Ai2JRB8Z_W@^Nf(lu.;YQ
\sDlIe/Mbs,6SU/JAs\L-.<?lV]ALf?QAMbW@R,f2L4hB!!~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="642" height="238"/>
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
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[各级别车型销量情况]]></O>
<FRFont name="微软雅黑" style="0" size="96"/>
<Position pos="2"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="-1" seriesDragEnable="true" plotStyle="4" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${SERIES}${BR}${CATEGORY}${BR}${VALUE}"/>
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
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript2">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="cata"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[$("tr[tridx=10]A","div.content-container").children().eq(5).html(cata+"-TOP5车型销量");  ]]></Content>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象3">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="cata"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report2" animateType="none"/>
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
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds3]]></Name>
</TableData>
<CategoryName value="种类"/>
<ChartSummaryColumn name="2013年" function="com.fr.data.util.function.SumFunction" customName="2013年"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="472" height="256"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[各级别车型销量情况]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="96"/>
<border style="1" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="472" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[各级别车型销量情况]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="88"/>
<border style="1" color="-723724"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[各级别车型销量情况]]></O>
<FRFont name="微软雅黑" style="0" size="88"/>
<Position pos="2"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="-1" seriesDragEnable="true" plotStyle="0" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${SERIES}${BR}${CATEGORY}${BR}${VALUE}"/>
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
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="cata"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart2" animateType="none"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="JavaScript2">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters>
<Parameter>
<Attributes name="cata"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=CATEGORY]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[$("tr[tridx=10]A","div.content-container").children().eq(5).html(cata+"-TOP5车型销量");  ]]></Content>
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
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds3]]></Name>
</TableData>
<CategoryName value="种类"/>
<ChartSummaryColumn name="2013年" function="com.fr.data.util.function.SumFunction" customName="2013年"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="238" width="472" height="292"/>
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
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[12-13年总销量比较]]></O>
<FRFont name="微软雅黑" style="0" size="96"/>
<Position pos="2"/>
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
<FRFont name="Microsoft YaHei" style="1" size="128" foreground="-16764058"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.Donut2DPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="true" plotStyle="4" combinedSize="50.0"/>
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
<Attr position="3" visible="true"/>
<FRFont name="SimSun" style="0" size="72" foreground="-8355712"/>
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
<DonutAttr innterRadiusPercent="0.5" seriesGap="0.05" categoryGap="0.2"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="年份" valueName="销量(万辆)" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="642" y="36" width="308" height="202"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[12-13年总销量比较]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="96"/>
<border style="1" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="308" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[12-13年总销量比较]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="88"/>
<border style="1" color="-723724"/>
</title>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[12-13年总销量比较]]></O>
<FRFont name="微软雅黑" style="0" size="88"/>
<Position pos="2"/>
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
<FRFont name="Microsoft YaHei" style="1" size="128" foreground="-16764058"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Plot class="com.fr.chart.chartattr.Donut2DPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="true" plotStyle="4" combinedSize="50.0"/>
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
<Attr position="3" visible="true"/>
<FRFont name="SimSun" style="0" size="72" foreground="-8355712"/>
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
<DonutAttr innterRadiusPercent="0.5" seriesGap="0.05" categoryGap="0.2"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="年份" valueName="销量(万辆)" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="642" y="0" width="308" height="238"/>
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
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[各个级别车型]]></O>
<FRFont name="微软雅黑" style="0" size="96"/>
<Position pos="2"/>
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
<C c="0" r="0" cs="3" s="0">
<O>
<![CDATA[微小型车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" cs="3" s="0">
<O>
<![CDATA[紧凑型车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="3" rs="2" s="1">
<O t="D">
<![CDATA[85.4]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" cs="3" rs="2" s="1">
<O t="D">
<![CDATA[134.57]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" cs="3" s="0">
<O>
<![CDATA[中型车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" cs="3" s="0">
<O>
<![CDATA[中大型车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" cs="3" rs="2" s="1">
<O t="D">
<![CDATA[70.95]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" cs="3" rs="2" s="1">
<O t="D">
<![CDATA[35.5]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6" cs="3" s="0">
<O>
<![CDATA[SUV]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="6" cs="3" s="0">
<O>
<![CDATA[MPV]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7" cs="3" rs="2" s="1">
<O t="D">
<![CDATA[86.04]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="7" cs="3" rs="2" s="1">
<O t="D">
<![CDATA[113.98]]></O>
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
<FRFont name="微软雅黑" style="0" size="80"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="Century Gothic" style="1" size="104"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94pCdc]A>&gMuV[Vm`P22.(NA/Gl5&BBC/mfg`NkQE`KIU9V[rdaXN*(IWJUZSgkQX#[O$fl
0YhRMdTq(NccW98$9mR0oi#0Mn,<&h*V3BM6.5S^,o6T'(/Y>Q8jGm[*7RY4)%s^=S@oSNDF
i/Qo\rd@s(QhKfk6m\3-G@2CMA@c3ZP_8t\'c&7JqH*)E$rS$\(U61#BP:uRU$aBQ<T)*Kf0
&_eVU7^</qcWGrh@9tag)5IpGOF4!]AD12.Io3uQ7Ht\ImtS"O\8uZ4i4WX(CATK;<)01p3AU
O)+]AT#p3)i43`<c/mS#2-]ArQeEL*PosmDhQQEBu9h8"/fJ&N;$Qo1X@ckou`&VIe$LC$^`.L
)'VnpO+PL3Ipo./.qPLkRU#Hc8S<o"2CnO<^:@AM8Vm?)"S8dZ&fNmD@qJ.7YruM!8$J7#@%
O;?CO<,/VEpR5qqPMQ]AKGd(VeO+qjNJ+R1-`_Vmb5ub>KdI2BIGogg*aX0Wi`eem;n?;oP"I
q+s3s1<hhSKKRY6E5'G6XkM>fqAr<7$kP^7Sqcjgie"%=q-+g=0JqKL59kTj+b=+h`[S>K0\
?XKPdA)\hiN/K:Yd7V..-J]A>>rgh`aU6Jk-U1W_c,WOW`eQCrPWd0IqjFKl..:t,,]A6GWirt
o#+&D.X0b.:?hHOZb<-En=`d-$XD-YW''fV+`U2A+VZN[&s=AjTK6$^`s7J1idc+:3QLf_'V
di't]A'Z1[610!B]AXEn^F)Yck4;u43PPBU0E]AWPa+Di\?]ASO[424BCjK5N8SGG<g8S\o`G48A
;P9bl2@Om&,JCU!>C<Go7u"6f]A5/XHF6'Z5T6,\;KQ+*uE6#lIHNE>hJAHKUX,.I^Sh?C1Db
cdIA8Qpm`K6)m2!LB/1,/k)1p10=#nuCpPjCU58Bb&^'L!SY,D&LC/gLii9/JM.W)5\mZ1\>
/+us:kRM]A6_%'5p;(kV(g1W&\M4k`VLC/J6qSldpYnTq.=2aFhdn:HJZ,*fNS[G_'5l.LUEk
in,b?f]A.e20#Vs_H%h,\t`@T-2"=^cE.?/ul3dQ):2S&U8W\34R+*QH6n79>d_M1at<J<:$k
o?Z]A[AXu5'-$fd3/eiX[#>\pLQ%m*u:'PdIqM%#m&0J_@9u/G!&^(TjTb?>K)>af)*R,GQ56
BkDYuH4iPS??)FU]AMKZ[!!G*/>YK,eg`m^/UEFGT-5bo=L*e;Vjh\HqD<s!XJ^4\i42DP_4F
ZhA?2!9lj6[aLE)r[_o7k2,<ruM_jV@K4s7YYqj%AL_OAn\j4_h\*.%T4OOBRTqBQU<28O;m
WHc,K7@2GJNl7n^Sl(pZ_k!u5+SAfj=iG_AKC)-4S40oM<9F?8q0@.`9;]ANbn*$ZdQ>ZmH]A4
oD-O(e&hhD`jR0>3*FDPed\uaDc=fr1`B<8qtqM5VVned6lDcc2I=]ANmt2I;R6N`EBk^/t#!
a:b8:lp>Rtku("@Dl5ZG`=-o[il._MhQ\Ya*rhrAo>Ss)gH;\iFST(X?,_PFZq5m)MM<*1f[
_6N,Nu,.R<msi7mLo_:B3R*nY*S3==MjW2VaHdA4B1o;6rGm^+S8F*g;LTrbIKt#Yt>u+'sB
[iEZpin%<#5[$D[E>c0eeU?Cd\FkZ<>*DR`9\V'-Al;K9ih#i?/\tm@6^KW[IoR_)@F*0UeK
]A8^/o_cKZ@u5m$.?>nf<nZZ7QFi)jRq;jU*M'=dP,!=d2V$1!9G'2XI_[o;GC.^GjWPXmFbf
qbas.F8i80-a9Q,iaL:?[1$KI>TD(R1lNL2h)E]AHM6j2fJiY#9K!hM_A'\^JmJ_'P]AdDJgKQ
0gD!uX:_=fp[VPp^e9P*?0+5Vi]AI!;K#A4<KpZ/GaJ)doJ&fnVeIXO=^9t2ai4jo,hpL'3LM
uH:Uq&VCIPYTdKe/4S`YUjTik6s'ljnnB6ZV5r7O3(q@1rC6g'Z*g"qX9/jEh(+]AlMnCX*qZ
50Y=2H>(JkrojMBn7mW&%TUn93d.j3=Khn'5FOfkXWYJP7WN.PsQdVt$enAJ`4Pu:*Da3l@o
TlsI0d>u4]AZ!ZsCL'9Pq3DuB)Fs.9rWB[#V;65U.l[*9j>10K9@M^BBW(,[V*=VUd`Oea$u2
=hAlY8,bnpt-n8]AIt\7+8A;&g5&qQe'XD=YO.U5D6IimYkq*l<Y67XNhs`6gH.J_>15a7`Rs
aoA.Ilq<_6kp8_cd+K[/b:OCWqd["RaJf$5>`pbSV(Y\q9\Gc#Be$1@'AVN_[@6tVeS(OAel
Eih)C]Aa"p=5j2PG=nPbpgsrM!8kIC[(&uKHjh618Ru;9p9-5a]AfUK"rW0=_'%GdHmq9J&//:
F9]A&8:.\-@&B>,^lJOjFc-`?2@=Bs=u\T@0^7f`g:\U+C\&5U4_m[*!]A1<l#N!j5:pp0qVTh
83jrS+@TTFh,5oF18_P3p8>fpS[P7`nY+P/fY>.T$HaI=hr+f^UquZcoBQS_*s\t1AHd@LdQ
Rbp"D`**8EQ+.pfNl%]AA@ASQ]A34^A86*Rt=M\`]Ak-n*cAHETlDQ[&#M_E9PV\lODD57O0:SO
H:D]AH'@LEkc>JWE<IR=^9=+ghUrNtNrPj9UD0FnP%YkTN_/2!5PZFJ)'"InO>K4pkkK;s_8>
E>1s"iK]AQY?U0nUBcU;,h5Cm9nq.-/DHlXNjU@qEEKkA_70N/OiIn7?]Af6Q*o;B;"1c9E;W4
oh@?k2^@Q"g*[7M\[c.&8@`RYnr%qbE7C"8"YCQ3@q1ZaWB0+lA]A=J9djW;NXV7rJ^AT[!+e
(aPC6@\GlTN*%59@kW3G('"ZILZ[);PU=t&foYi_r8<lQh,WC=M3^H4'!pT!d,V6Yi:(a4Jm
*Uh3Fp^[%`'Zar<5%nr\s6F2<YSj!)7:I1u?0.$Z+N]AT0.eMg0Qk!g-;UmXXWn+HLHrW_5m&
!bIKp>S<G%=V4HmB?;55mNH\\/eCQ,hO;0`@u.a/E%$'=[LH/[<`T96dU0ZlX)91pI'bOJ*t
gRLn2r*:\[p,Gf6bUl<cpZ1M(Vl4DJJb_2#ROo]AmGI'S[$YmfItH^#IW+aBm%OEYJ[BT"%ee
0$E8kuAR#GG`;s(t&ArH2WWR+2VP"'T'"j5(2,SBS6I:pVF(s$9lZp^?hLOX-nT1V7f7-D5j
It=,Jj<AZqU0a$[LJbg1$,N`!oG3bp4'i$l@ZfHqE\!uC5PX+?0&\uj%=pH,K'MP_kX9NGUH
a)DG7]ARHtiH[;,thR42l:m\88QQ`@ul>OlVF-$hUGTq<U$(QqK6Z0#kJeN+)8YYSX`1g>&,g
=ONI?LA<-k$13sF>[h3L\Q5AJcd!V4Te@:#/"S@?@14!'R('9n%urQ7XDBDZIdO;l/$1IdWU
-QC%O+7!URi3BY-iBMZ/.P6#k,_r@j>H'T?r\>gKV%PG\ELVc6#8P^U#3cAkP9G02U]AnQ`JM
fduZ'Xr-@tjT+@jn"cJjYePFARl#a?ZJ%fa??@5>$XSW<L&Y4P8?g;G3B+$(T8sU%j0QKD&E
>jXMG6*M:;,9J(RZ^f\a4:AQb)\@d4>fZ:N.mo[.qjWtT"jU_nYr;Jg<,5nG'Q-D+s[)Cp6]A
a)3#/&9[E@<3N/45Mr%T6(_(qH=lEnCR2qLqdrNS=;cOHoZ:11/9XsIlF`9!:_mu;/%H"U(:
g#*tr?*a,$j`4p1%8&>7#'V"U)$Gn=mt@Jl7A`9i8')6OISB7K=6PCUDGAX\<R=la`QXMCd_
Mqa)Ph@/X7!s@M5,"p8qfBu-gkk,'RRsXatHo;^KRi;'#$b'Z2u>t%9kAcXA#uiC8p@DXa.\
rF\UKo-86nQ-)993IhINr9e5U?Cpt-fT)sE3aKk:P.ZtRiRd/%cBEQqXg37n%6gB;k*i0baK
7fV/^PP9(8958<OT+fjcfP:lI&pe'nii3ZR$&$^!=V172c9=Y^S>E:iB"!g"$<X/S9?(F052
]A\/^m6hbU]Au07uXAhX5OkiRL:;Ge5m.d6<VPFGJ,(0euR'5CGC++Uh6"'CgG#4$g"3kS!6^0
iMQo)RT/:'D".P&ZFuqEc1'[!]A#qRk3YYXl6E9:YfZEL7g5)_Z:mO(i>?2Q%;(&k['i#TC.S
7<uAPY`n7Mme6<$SVM_V5q4()38+e\DoNc*/J`AD47KN--0p.k::fZTTBkVB)NQIPQfGTBV)
IdYG+2KkcR&VqFj?P>n8tC0P0hTK$]ADX!OU&YnTS"ea!ZaDHM2]A$B1pqFHU')2Q^e`]AAnkHF
k]AK#-QNa&\L"2L&1R^BC8nh.^PJcD)QVbt,AL!.m#3-$StKc=0?U:$<<)ab_N&Z^kj[n^M\n
_qL5)u.)[Sc9RdY:,G2e."#(@rl$>DT2eAi^m-3p\e3*WDmfnUQJA8jBM2Wj,<'pHt5S=Ac+
Y.nOAE2Ij#VEr3\p_amO_P%lH#9G+MSd0R.=kT^#&GtYC3nq!T)&C15eX-nnoiV/GVN&s>_5
HFIRk:jZ`\4!Q'0)k#*<<.Rrch"cRb&'l%auetK,X-B4I6-cQ#!2T2M8`KZ%^,D+FKS_FOt/
t"F9^g?l5qVF?9##QWZRO+S+84TXO*dQH%d6/(,&JESc(poFD=uCP_muTMd=^d%0Tnb^(qj\
t;Q`Oeg2kk;/K:XS+M*>]A$jK\T_4mo?[NVqA"FoVg+5>KPA#YPNY)]AQNohJF-r+2P\=FjkMt
+Rltf\3qTA2]A9"U[(g2uZ2ojIN[T8`M#`ljm8<4S3Qfp457n1dO*Q0q.B0:44RnsYWM\QH$R
6BU9Q92s`TcBPepW_c@/ed!P)8@nAfY#Q?Z"&oZ+q!kNc9M,:SUkEQ>psli]A3g174";j'Vq(
A>tZfrc&,5&=+mt+_9a:9G^?ZotnRei;ZT^hHAg98VLEb1,05+?582HX)KQ0^J#SXUrkD:mt
3;<&)S0Qji1e:a,Se'Z1N/.nGB+Nq/FhVG"TjB-pq%\K^_7.rORVSm\rP-bnDZ)XI+S#Oh:Q
u4+Q0peA':"(/p&=huY<;B^'>86X&]AK<PI5LAUqCbY_6JY,P"a$q<\HP/d<^S@k9?6A&sB7%
\i1`Yc$2MW]AL3&8m&f!Wd9S?T\rk%S)TDZH&L?7T=nUU1\WDiN.Vi&j&54fi8-^lW&mF4N9&
Q]AIq)!p&>Ce6<T3-l_g&FBTi0f?ee;3g0#L\'.m\.8)NC@6`B[3+/?B-jk(\/cVG[O[3-tKG
#&"KErt:%/\#*?dN52Ao;!/I;TVrA3"^smTsNi@Cl\>es*)7f$7M5@=CD&[:K?Don>C",Xtb
IooPJq&S^C02-.Qbg$Y4\+]AguP7sEKIJ)anNK@q5Nk4S_H"OqF\j33(e$Sijd\bW+QTR;?;C
7^EP<Q>%[lE[\B%6NYD[B6&]Ak\l+rg28tGeCB3?r8fLh[\"J02r?G760R[;pU^@k%_7B-8J>
$XUia$b@Wr4^MpLZ>O1H]AVF2q#a5u(7MH1^n:rt>rir%!9tl'1ttp%o(q]AREs?mIq/U$t69s
+s+h&>\KJB@+Uin@63Is\&;U*8%3Vi!4d,t$YX7?k/!hM[f$7183L5*@<WaSrS;iriSB1Opc
qNUSbH]AML=;1]A"a"D(B]ADj)e>POO>,Iqk`IlNZdP:ONNVQQECEj,lO/dR12CL@E^"=!jq:9A
(ZW"C@)ksV;=U]AWiH=`^1B^Cm*5VY$'Q>X70O*MUnPJ#,drQ^Mg;M]At"<(pZ]A)tPe;9;ME^+
eLdnJ&ECLcTFg\6aOW$<,YX-h7#beg\e;"qY.lY[MQ);X@;FP0qpttBN;k:M-7G@39\.c8<8
1+rskS_#a%:?eS%n8.C^Q9.ai3:an0#A56OiEn%jg%Trd$f+S*#_<io#hZWj)D22Q[>!GMl8
(/.s1)FX<!b'\m9r^/]A1DM%&qL/g.Yb^ebXo*ZTR3WE9u\_!T$&c'm?bE\?+-YTI1<PK5mEH
;u?\R`FPh.tC?odcf9V]Ah@hcbU?o9#neNbn,EaL9lb_?;`1@XQ"i<-p2`r!(;FZRM/rW0gA"
s#aWW/qGIB/b&91Hq^D_U:Dg-WJ^EBH)OlIWCq]Ai<f*bP8qR=:U'PP[_$=Y"=L`7.$kZ!%g,
=FTQgp'0[:C)4c=B2u]A)d;(_@c"<K^1guZ$,:-?>h0[:9E8WnJ6'-=qF_Z&!]A3o$SCFu"+EJ
Z2p*e2X:\?ER9jTUOn'l+d6uUurkTFp5cZCj,c?)AC?&dT#cn"-:joe(=.jl(c.0P6W$K9UK
$rFkLl$mbWd+=%`CkuC-/Y7\dTk*Zm:2c*5B/>0:jsPhZD]Aeo=775ti<`Zh!e.;-]AY$%=qG*
GuqR=?)Ie3l/O_ic"5?..!OCuiAHWR+P1Ic_R0aq=AqFtg7$^/1XgT>(CfcU!7R1?+Wfekks
b:uGG%A48>a?#&N&R51WMGG/p;2j^PPk#?1A[Oq!;!/A&XV.m@,kb,t4i2`Yq2EC(K4;t6qk
_2N.!Pt&55h-AE_@<Ps[R*bsNtEP/&Z4ki)d"2f9FKOG-=TBr^Tj#Wchs04mn[FYpE%*r\s$
<]A:iF)T,=4\s)&9_'UAo0;M.?Q@7PkI7Q@LC)D4n(,JIKtEp[-?#&]AD*\%N#9D,t*k2`tL]AO
&&;`IDsUcoV"n<<5d"g'i2Nh#Q5#PPI6NZKrjWr&Mkf.?kR)'>-$2OJls2??!ba4"]A7A>(j&
ikh^37C,(<BO=R;8IXi7/78TRWfqTJPCKP*IbEs#uUm3-B7";:fL@#'W>?/6.&;.j980?X!S
^ehR3Ma,pGOUd1FXL2gA.bV-"jkf:K4&N=A8Kk"uC3a&J6;FJ$)e@"`!JQdYScFA#e`<@*IM
iKYT\Lo;EQ*i[QVNnpZW`?4%GiFG@99JpOSW8L<<Bb;]A%"_[C[[Rc[/%-V*8\s"e8T"o,1r/
i<Kdk`8'^%]An!tf]A4%62qA>[[C%.UVZSq4AGTBnoWA/)Gnc_kIruIrB2[JNj,1HQW1s~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="472" y="36" width="176" height="256"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[各个级别车型]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="96"/>
<border style="1" color="-723724"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="176" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[各个级别车型]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="0" size="88"/>
<border style="1" color="-723724"/>
</title>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[各个级别车型]]></O>
<FRFont name="微软雅黑" style="0" size="88"/>
<Position pos="2"/>
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
<C c="0" r="0" cs="2" s="0">
<O>
<![CDATA[微小型车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" cs="2" s="1">
<O>
<![CDATA[紧凑型车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="2" rs="2" s="2">
<O t="D">
<![CDATA[85.4]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" cs="2" rs="2" s="2">
<O t="D">
<![CDATA[134.57]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" cs="2" s="5">
<O>
<![CDATA[中型车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" cs="2" s="6">
<O>
<![CDATA[中大型车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" cs="2" rs="2" s="2">
<O t="D">
<![CDATA[70.95]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" cs="2" rs="2" s="2">
<O t="D">
<![CDATA[35.5]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="7" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="7" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="8" cs="2" s="5">
<O>
<![CDATA[SUV]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="8" cs="2" s="5">
<O>
<![CDATA[MPV]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="9" cs="2" rs="2" s="2">
<O t="D">
<![CDATA[86.04]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="9" cs="2" rs="2" s="2">
<O t="D">
<![CDATA[113.98]]></O>
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
<FRFont name="微软雅黑" style="0" size="80"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Right style="1" color="-1644826"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="Century Gothic" style="1" size="104"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Right style="1" color="-1644826"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Right style="1" color="-1644826"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-1644826"/>
<Right style="1" color="-1644826"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-1644826"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94j9PLl,0\+S/&>&SN$#EmYR$GDW+2:@(CU,Ka^<G6,c#7(lF1^Fd0_F"SP*Gqi+8oE%A!!
6ke88JIk)$('*;U&3nd1AQg70sO^&OR1oODn""UP+#rZ"%FK=j,dMn!ngO55Xm)^V'a8A+,t
,]AXIC=3HO>qZ$R;WP<!\j-I%tM;X]A3_i(I8&/)G@0]AMI<Wh"p8tXPo2Ad7Rck517N+AY7L5`
@VF$de]AU-r/'(]AcPG+I05j0E*O"E7hX0Ve^\&3@.J'3)2[!M="G7't]A5DoGkKkMV28+PpC%.
f:(gE[mZnO;RF/;s64JjF3n0HlCQcmCt;Xp21O'sX7oA.Sq?_nFMbn-eT$UX'??cTFBIn[rZ
3n8XnT2FUaEQOIe`R?7`Un5SiVBH/9QS<Sq-^$3M5o\"gB;<=SG=("Ne-ec&G4MAPeNRJ&p9
Zo=LjM$SM7no#o\i83oW7-1.Hro;\*eq1i);T:k(FAhClhM!c"lsG`SgA'qKo)V8\J'C/hp"
>Vp:K<j[qZRB[S0T425cgrBQZl=W]Ac_pF'(/m&=IX7#NGA2*:#TS4h3n3n^,a^W0jJn99,'6
[CVqdE-$jn]AR4p[<TSqT&q&`>NQ*t;K_%7[?&nlSZ`ul(,>d`SFVUHNG*!p3Dke4>V/.p\\B
+[kOfF38$HT"VcAMJ8UO*G7Zm?(.;HUqi>U$<`luJ3(@\CA4t(-d@s6;"^'Lb=CKQPGU7*)^
D<C?UKBJeT#U-k7?W,Nq'))EFl>*!`b<Y>(/i_J/kY<7QF65VKVu@migZd(=_p%4_^90rV-0
/Y`2N4cofNA0>mKc.VZFjiCQ7;(3bgGP1TTEE_8&CpJ\ImiWZW+Er9%clK*\,BpGAh'1jgC9
"V#gQr\#N2##M^4nhenk+4tr:",L'0Q1\)GkB40P!9r!m6OE)mIQVhi0c,emeYVL@ASL&<AN
1fm]ATjP3-7CRGM>9AmIe%CGdbPY5+Y_7#p'K%,Q.(O\dK=@5+M0l8S%[iY^eKt'1Xru+hjP5
)O&2a6k0k=Spof=-q-;uL/:^8o^o2_r-OGRa$KSc9G<BQ=G\]ASbB0;C::(eB[f^3Jj--ZCn@
^.=h41*=&<NBMksZ^:DDBC)4h89.dS[BYo9>`Mkh;?o,JjfOpB3XZYu)//jmithN.7dAaTR6
)QrH.B(4L,^oj>6=rFYVe6D("/$1Iaih:`g;/f^X-X9Sq\0mlI?F%#7?;"#KH[Zs,R8P,M5.
*Au>`%/Yf+=FU8f&^1UWumRXgG/(kraA*^<O%mF^KTXAN0f2^ik/m#P$T>pg.`j&A#7Re_R3
!1FW@iFVo[k),<ILkoFDi5U%jd1fmhqIMSkt48T:/B"<'Vi*4H-'mE>JV.",NlCC,?REFI)<
*K@K0HBHJAVkZgI!S`_p=^0q32=W+ntXCZ"gPiAV#LUIoSq"j%:(mm5oB0n$SI3`W3!+B.^+
jEs*rNT7a#4E_dLRY[ra]ArXYeFa"^EW`EIXg!;EKBZk/#FGJCUl"C,BCeDOaEdMPWN1cl#$=
_J-PsIR4gQ'"ZlEIk[gVd)=haHY3dO]AKJ*9SeB=a/1nh&&q+4&=fs0q8JZD1g\fQl8)n%HAr
3Ih\VsbXDuJ.MN'VJ)+60hY,6R\()$cNQ_baVACL6l:i!$3do6L=Q/e\2e>u2V=N-DVa%Vp+
I88(YTqr2V,EYMbH;nmBHLMH3S_N$a&C(kYu8PYb[u9M;p9pT1$9Y)5IGniS&4>6o557-ntl
T\6!#T63df/8`/\Ni_&,<eKf^/\Nk21C/U2<`*hMdQ>X=@5jF1WL+'+s[FKPPc_geP:1foZ^
ObB$:/;)J%4&A.6F0r?!UFV2\\s-8!r:N^d6]ACf0:kf,5ck)G=oursXl*rQq2<J3,Y8b'n1J
Z[S3!RJhe$L-*V0%I4^JSZNnc&6;d22*uig^6#,$L.t@u)&/-#tk/XL(t_QRdS+3"RCaX`D(
l=SPn=I</:/J)H-0r,>%3k;&TK;`1.dG(qRMq>2c]ACD#mD-D$fNhPZ$/qpi$,nHDJ?2HXm<.
T&@'V@nuP]ATZXe)"1#"Ar7Z_hi$F??D$W5I!B+q9(g]A]A+hAk,e1U)b^Uhh4/Df&-Q_6q9m]AT
3+F!NRZ.\>GCJ=-6)1N&1+U'>'bR9)^Jl%f9i=ddFuSS3NDXS9)X=HRr]AhmrVMc[+@H.dZ?/
2n/dldU$<Y&[<JJ#KKmu5(M*+/7fU%o0N:ps&m;FP0bK#ZRPc`g?SfAJ=/XM@jb_S&QO2^kG
ko8N;Fn?.2H=o?Vs4dgM-'B)H#kCAs<*RaEg7<kO/):,cCGj.]AiYRpLasr=C:d[`0t[@n:R6
=<Tc=6#WHD[;pF$VV]Ahnt$GpSKWm0^4gZK(GfX1Tc>#dVZQlK'="q$ic+iP%,;T%adVt#9U\
9VfOTpkt76K7:]AC.Rch5/]A3'gMUc^b7t:e\9b@8:/k=YG;R\--oj,CL`M-q3L1#TAVa#N'q=
!BFVA]A]AZ:9t3/k\.NXl9I/'q1]AAo&?^ugR9:2g?+oEXrreM'%<A#$J\%re3)8bJj1<t04Y22
(W@a6?Df+K>&W>@c4)V1:>s0[@`]AsWTQbUV%?:_AHQ6f"^8g>DJRRb<pKBP<g`Z;1n1>hBeB
K[R+)4&#rueB..%MU(K-VaJ(U6<ea3SkqJ!sE"r*C=#YeA`>Z1OBn6Z3e9n]AR+YqNg[:erM3
3hJIE2i7nR06#t,;e68`8Or1t&kKU`(d,@P<\Q:n[itN\2=sR/,B:)L*'^R<GIU>,7eZs_;O
U4keCNSgc8Nu]A[=)$1c5Dmk+O_MC#FRtVX:#EXeUtiVe@DlF7ao^iI@NScojHFuKr$t*\Jub
mL"?+OUQ<_T>GbsJ*A\JE.W@@c?qgR540&/TM;a[n=/=1YX(%_U<A/_+04n9d+h67^%6Jfo^
BY0e2ZAuZ[:j_`KGj$`Y%2'\1Suk%6C1(/u_dMAs-/c^(G09buj=u]A-pZ.O2_O"*4l,h>,NV
O<eD`\T/Pp`4OcK*/u-P+"fRs6t$=tedQHO9P52R/BQ$^kKT/jJ[aj*lCEr6qTqX[#Y.4!&.
,^'rf45_$uiM!k>]AB.Bf$B8,XnV-fpFnjo@$S?]AGn/!0J[5\St>8D;l(_L^_)%*=c_!;d[(V
]ARitp9R'^5bCKLA`Q2idr9Jd4+Xu2/J-*5PQW^AG3s6,JFfWUB$E`W`gCDBKHL5;a=klKq'j
lCfA'XQXNU7)Ho]AHoi_mIe9%0GRc16u<S'jg),,(jRpTqSY6T*n-E9kIJ1WgsFJ#+lAgr/TN
#Tf55?nCRph9W*#WLMN*ECXi$PTM!.Cd4B:&FN@#@1NQ7S6CM0%\)7#;fC<m)JbY[R,8Hm,%
?>4gYST?\r#WB5AO%+r\?h,8GkZFr`W[c_S$FDMrR4DLpD(LK>(?^S"+<*=Rm"igNJPRYaeJ
Yru6$AVQ,lJrNi`$ie-[kbqmEeL_rh4:#Rrtfbcs9L8j/;02<B1G8".J_'A2f%'R;VV\RL<(
2&,f319Ok=1uXAjMc*N\*G-g&/.labBiGJ(>Y:HLL(hk\@ZYNKUVS_ghQ>'n]A]A%/EAj#*P?4
6@&)t(Hd:<F(R$]A1,YI&WjW2"Tmr.5jMJZ`K_au-Pnr!1K=H]Ap$/1l6qs)&m;(QWd7+A1!Y&
E3#H]A*!KHb%RuU9,\U^e`+VL5KR:%;XaI!QSLk>n%]A/1E3*)m,]A<Rg%<[s#DFT@rjnp]A0;nG
f(,=XaaaYTV-]AOS+KB-.3Y;MI9^TVdraAb\B#;:KODC`LuFs&+;)M3?P.$kadQ9'$tkSZA27
lJ3G2K_f&iZ"`%&F2tIaK4"pI4EAj4rXRr$:3rr\FG;>&47<@kd(Gm.6Ze/S!E$mi,`p7qZg
!e8PI;QC*MSOk[+It1oeO2mb\KmfDoI,(?B1A+Cr;q&/q4F>TFqd3O7)`;+hcs^B=OC]A'\3h
k&;AC.4oVQK%DF",hK-8tB$Z>OSPb*cJap#o='a;`4qMZUkLh9YIhs\G)q]A`DI5k</3"7Dc5
Yl,`"Gs!'cFpGu@e5$BbOg^DGmgGt+qW]A-K4GleCfA&f"F.:!Brek=IN,&n,IMqCjhn^l)L,
Z%meZ?^Td+/VZGdKlXgp-q\S"rRY!lpSnb86!`JI>-qXoT3W0;#I4#rt=']A43PU:TnCQ<=h5
K)_RGq0AXJ*/F9KPIhXKG")[(%3]A:D8Dq`W*.P($4,$$;0igV0YV;*H!2g!P<7=!1S;m%B/p
-Xjjf/'r8+idT(osh<na@r3#=7!(Wk+E)m9@fq,qVm*a5_&:#ON/\?Yr*+!PYI@`quVn//9:
.";2855Kj6Go%]ANV48!5=9(tN'c^ns$Bnhb+_F8]A&V0P,rF$Q1M`_C;Yl)<8nea.r"V41l&d
B?iis@9\BC`K7b*pB^\W0#i52Z59&@JaPP5Z+n8En+6Y5&sd92N2Og/;Nu[QY,L5D-icjbGU
R6EKEm$7[.?Np*:j6qFu;q[!Om_/p#5V\l8D(Qfl4BfJtr@01rqRSUdMu"Y.m2&70/sV[2Y1
:`Dh%A4R<r'kmZfXdi;?t[G97?)E7=U\2Z"KT3bt7Ti5Hb9gSgc.?/&?!KLK3[h(pTl3WqDj
'Y)Cbu9q\oeib6`m!fPLYhC32>IM7S/GE;M))&KQW9R[0N;hG%"<T*f>l69MB!2/oZP2#)n*
qjW@WNtRRlB_\_lET.7>mZ?!!PRL`",@Uc=#BQ?1?Km+p[`A#uH!gYX1%;8X!uU`*Q!TQLC7
=kbkW$L%%<k03&EXOJicQG2`tZ=/e?=8i)M]A/q-\?HjbO@?"Bd&%96(E_ArOBbfJF>5B=]AKN
?jEEPWFD)o,J((']ATXJV7.Ri'6jUE\[+6#h>Q>N3u+DWSj8mm@1#p/afM5hGWjmTr#]AC`3rA
4TkH:#FTs7OZ)#QUm%t@6Tr"sI1Bd$b#]AAt[9G<n7lJDLjP3E6b1H+fu;i[AJj64S\WIDT@b
G++/Wp(-N+@&;Q#8&0EiJd$GU(qn-ZS"8f:21N-_3-stXHg5_CqWNdE-mVKBB-'ma?cNki4.
jt+q?H%7"n?:5N4"4l26O\1=UM0iP_Korl#iTpdaI4BDc]Ag_0T=D]A^ii-[Idb-+ImDWSaESk
LF,k;I/2B2-s?VcghHa\qI4eg9m7_*b@WLB2t<eW,"3B#fe;bM-61,/JQt"QRktF%ir31GAr
^CMk_-GMeN/^ukJQO?U0t,(>3Ls^lET)sfM@1B:/6Es7',T?!-neSS>Qn!lpdK5]A(,%cP[Bo
O$>^'ad%!(-WH6Vg_k\[rq!i^_-$gtMg\9SR0'edkNM;_MkJGb94b#:mN,Z+7A$J0K>#CjVf
I]AEQ_Mo@`-F(U*(_Y*Y.+h\O!q8`(RGU(U6%ob8"uJp#d%XJg4ni0=*,Y2?"R!!+]Ada8t-u.
*iMHZC/j2H.</;]ApREXj%O))<Q1V@>0fmm1t3E`#]ACI`0V/DGH"VWr^]Ae9ertd>gEo<(LfRY
e-cTlD_L2VQ,fqR*[sU%rqpe03]AT_9e<Y:(i(c.9>G.f9:=[8mj'GZp`KT<`aZF>?lrIb(-u
3DeE0iplf7CkOJcsD=<b:(VK0Su(KM>JP("i#R30qs99k8i)8$U"Ws(;'pm8D7Gga&)u6ZK)
I9Y0"pSmoeB<[MV9"Nq]AW;UcN]AD/);L-#q*pmb,7;jut'r]A7U2<)l3'M%CM,9Sr4AU6%pns!
NZ9M:6b?cSE1U9Fnpt-7$AD("Qo<*"a>hT)llejKZ$_(N_9Zn9a[IuaS'(7AG3:3k^InNAeF
QtD(U+XGIF]AW"sob3_k5/F_5d462X#(e"H,>EK/7Ve"tj#3>7&EHVr-nUaR9P3FR%?.Y-N.1
<LjakP>oI0%5BtHJM+/##gK<1&ena,k>nZ=5cDG@`M5A"F52#K`h$jp&$uaEfF%-W)DGdH8f
eYJgU>Rta4+LT/Q2ZR@2KTg.2scUVP-RuG@l_+)&J5,0UG,gO6#%f:(aK5,/u8L??/mLZ(l&
l+9/pCG/hrk$qZ9R(]A]ArjpSel%(m95Kj6ED-Wh/t</-4SIT+8)+2SptZG0sPU<2C=BD-gUo/
pM+AU.jk_[kbofm8S@+b<B@bL@I.!VLCL0hg82a/O'JM&*qObHTnfdnC1]A]AVS3LqDt#F+49?
3#j6M,:7LA.&k;Q]A\1/^tXS*`T\X_.Y,fH*'XOPUD@'`\7KPQif:JE$\Z7uk87j/uJhJAZR0
RDj8uDEU%8EnY7-G#1RWFh&.V$'"^<XLgb]AgBnT"<_iY3+)T)h8n)qNkc/qlP&fuq0<[H,['
MO6fGhUeT+UF=f([(:%W"89Q9t0AR*%]AXRO!QIIlun!GfhDr*7Os0f%6O;6kg:ZHslLP&5-e
1.u-bt1l9).@'t#_>V2?>]ArLHXD";)[?BBOO5--6g]A"R2dppC1nf%]AmKn8<MH?^Knb8Q5YO,
8!"jSp]AnVSZCb"K3I$Q]ALkG+s2<csIgc<)S$WB,Ief&/&0'i*(263?+Zqq)G-Hj\?JiWs-\q
R)*eHVlOS#(]Ad>Ks#5k$g*?;P^!jmNWd_*-Z+h[Jk3"D,#1Uq`UY<TSqRhBjDb"S.'D+7%l7
Z5-k=nLfl*l;_ZamRllfBiLH)N0b#AB=OC5d^c8'M%(+lX$MfFmpRb.H6.5]Ag3I'h]AV<hmqH
MSDd&k(Y^dVpi([R:1%W9&!iD6?5GgW\Pa%>UEH=_rf]A.bS;r]Ae\BfcONF8jg.@`r;@Q#`9H
rp4iOZTKN^qOg^n4RL8@\2etVW!*JNmU<.C15;Ma&#f%>gpo0+`?3ja95d>0#FF7ZG+.ibAV
PXkHZo[4lOZ4fSqo@6/3NWuP=to_4NVi^[_:=@Lc54+HnA'g[@=@oTMa*:3c^`GSDSSjGp]AQ
H<Q25j;d;_D:N%sU#l^eja)=#[c<oWH7d5MmG#OKe&2V;,V1<S"'DI<3!+XM?8B>[UilV%nN
1HdB<m&;1@Hfq:H1-%\l3-YRY;Ik2-*.b(2[W4[(GMjc3^ljAFF&Fti@\lPoqU=s3pdZi!VS
<UJ>Rg@\84*]A"]A)BWpeV;IXf_4MtFDcf_\Wi9F@?)HG`XVkdi7u#r*Jh"kB=tl7#*P<iJ,G?
>E&@H".,`4RZm;Jdq=.nJPt4qe\`55VdB$&)W$]A5"H?hBLdj&"P^V4+B.)<uc/ldkX3J%O(?
u!WA<042866T]A(P6PIZk&Gm"4$<F@U;BOSq=2YejqUn75V`SO/FX*J(^[o@!rc>Zg!,(:D?(
_->/,NPkm<dH[/S%=7djN[d9&Bk.`b+>qU5I9D:D?J1;EWfHZrkGs'h;t%g?3b$CFr31)EKQ
%c;@"bVh$so?2dE)3qV:)rtiQdNEp>ZBr=.@tukkZ%LF5l3)!l"ntXFRqAoAi&*UG+fn9WCa
?OdT=LD5Lcu1PPG4U\)d9)_Kr88.h!`j5!QgrAgS\RTo+W+Hs%cXa<%Tqbi&M4EP(o_dr@RL
E%:?/i7#[`<J0Bl_THP@I4bF(dcSC;Eoaec^p50Uhhk!T6SD"H7L]AX"D\+hMYQ3/(<g(Q+b@
A<EbW+nY29A3+)<'6@A0AFgQ4S=&7dl.B"Z]A11L(5Kr&.l=^Z@L98ICdIL":Ar&8epHEsn@*
bV2mI>V^8(]A0~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="472" y="238" width="176" height="292"/>
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
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=if(len($cata)=0,\"SUV\",$cata)+\"-TOP5车型销量\"]]></O>
<FRFont name="微软雅黑" style="0" size="88"/>
<Position pos="2"/>
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
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=if(len($cata) = 0, "SUV", $cata) + "-TOP5车型销量"]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="5" rs="10">
<O t="CC">
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
<Plot class="com.fr.chart.chartattr.Pie3DPlot">
<Plot>
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
<Depth value="20"/>
<Projection mode="2" elevation="25" rotation="0" focus="800"/>
</Plot>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="车型" valueName="2013年" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds4]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
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
<FRFont name="微软雅黑" style="0" size="96"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[mC[^0;qKSZgg2)gD6:37KfVN#id_RCA;3nq-\=`/@1F]A\bX,he8ZbEMVMZ^/Ne.fJP>":[Jc
cQOJ5dZ8,YA?)f`31K9OG$uh?r96hWMTHrpbEoit7EecT0[*Xl&uW[J&I8fS^MX[sYULX3X!
,Cs2WqVH0=;[__O@cTZ">oa=nqV!*-aB:l`)3,a*E&tUg8r8i>t9PTPh6@t8PMaYE9bnk6bE
l[!g5U.Z<HG:I]A(]AU7Wn+Wt<S(^bHs*EVIQ<+=Z1`]AD<<cdJJEYJ:gbDmU)+^HJ=Ma8;;^(!
,OqT<UDKT.i08r&"l@CH)gNO7@Ira1]As_o*@H8p[6>YGCK;=?Y(b<.aum+K!qYKTGsi]AO$pY
TD8.Foc6Zn_BB+EQX@Be@4pE4iQ]A$7RO%48Of%S!l99^ts7^`BjOelki*mRkJEW5f8R[tadG
k^\b2+@JkrE)S*anuT_J&If&'cg?FFWCD19b0V]AQ!UVbf[i/SpTjh+Z\q?Tc2>KjnU]Al0.W:
t]A1;eh)=9\-eGcne^-D$:f*>E8NVrbR$^`l$[^pXF[[bB]ASej#j(P=HS4W]A2l#2Q>qQsCbOd
]AZXO]ApX/d8a""\ecXIF+G<@uH\!JT3F(k*E5qmZFh^"IR`1:^,+<6W*!Bi=s,JbIKrDP?E%"
s/nRcB*Tic5:p5(pe5l8[aAtZipI5FbEShl.@T7J\R:U_i44[SN7b+BfgNH,Z0b'WB1CHZ/l
JZ]A(.qcLU=O]A,n#S,Mmo_bB3faXYGhqa7SXN[h6_$s+-0EQ+pB[$iEid/]AK3aD^N!qB*Ee6n
'$<3'I$U)i.6;mlh(FPV;Wh:'^b:'A-`#CORBLYd=4fm[,g(oijS"_%/+&Wdgfgb52Yj7?fI
r<>'hG@WjTD14_r8aAH69=='q!(AQ_3FgdpTQ`Ce+,"L2IHN8&Yc,%X1JhclDd?RirjAZ>8H
^<KG61RA:1/"+unE<;^804oe;`:S.Jji`%U!8Dameq,2Q/*Jb[^)C8b82fKptgo0UA`9I<Ar
#'gP8;]A'U`Hsh`nloV^f/nMHKL/gF_b%FN=&JXN7+AiFXg4IP"(`W`ENtX$aV*o"IJ2$0@`=
7[H[I3u[L?/&.sV,+E`LMQV-c+rndQa?hS1=f+'%dWr$1\Z/9MH/4#rdt;kFJ7r&1pCZ*'97
0am!$$%l]A?>QjF3&^=_V`p1(p.uV:na5(H06@9HAJ[/i)<*geuJ9hF#BikIfRn%=5!A>k8tN
d"8hh#&,uYqX+/\%Q4^B6DSV(-a[DGd`D'*6H9gs@EmPkTCLo+/A^H.DqH>P7oe+"&1`c$`Z
1X/nOp3I6[k&h*Ziob>;dS%BdQ'8#pJh"L)O"[SSp7%;YiH+>\ClAtO,I\O0Dg9OGRsuY]AR>
(qD\p309,kB00b``skWJ7Y@oSp;Co:!/NeQN[V0SocqJfQlWd"3YJKT$1H=$TU540"HUO4,2
"1iP`BdjSs#)3V(p'?Vi01cW7_ln@Y#E7s#8?sG?M]A6jT+6BD4/JLq4Q?qXLQ5VY^)_+kGrH
AVX$AWa3'3:/BeKm;&UWTr!Tl0048!SO,i(M+K(GF=>#FkiS5*6s@3r-O9gHM1;WRSFDl'b[
i2N8cmnWhH@*Z<+u4g&R2UjFp8G.khbD%InL#?c(DgfE#@`#k?%(cG_Y,BdPY`WK#3.oS5_)
LQM#-GZiNV+NrI)X!O7IO4n8<6HuTip]AV<fJ<Pg@F@:JZiuU/=<cHFR.h[k%V:eA`lI3)W9A
/,9`=@!b)UlDo5.'lJ!cCt46e8*<r*Qg1Y-3gXg]Ao+J^fdmol]A?.=7<gsl0.C<KQFT>1pCaL
VRR]ANal1jBRGDTdbC0icIOTXJ6)nDc);Gm4s'<b9fSMO+ZHjrtH@1`Pq1pZQ?B-'rr*\Ibb1
X'._*"JlaU:h,Oj`%6o"++V=$fiPTc)]A+@$_M@N)[^UOlbfk2S;E\^p9O(VuE8AA7dK(G0^0
'T5)-'ECTJp::VfQ3"WXu;ot^b[DJQZ?h+(bBa&Xh]A%!jC-\q<!<:(X;)<ldt$%=?k,k9qkH
'?Q"mU#B?(0('/6?C5D1Q8S=;XTIE1Z^LOGZLVT8QWRG)nk[8"ghsq"?.*3NORqUi*?^6KkD
7tdX_/I:u$;BB-pdf.lJ!b+H7$7%`<mp@50kmG%.XQdkTf'fAec3b8=I/AG\Q4Zgo-Ub(XFf
qX>h4_GFQLFiE07I/d71h2'qnHFFN1qu<6:ZpUfs:-M2/of(B$>ZnTrI;gUk-@p1.%+Z8b^\
iZF1`K/sh&P8SPHp-2Rk.Mc<,\b,InHfsg9M*+[:^>/ctIr@'+j]A0qM**m7%S:+UUjtc._(U
%+\/0aLW'm>Rl&iLIp@H.>9ehd1as*69&JpZ5D,X0#E8U5:cAuBoNnoO]AEaYhq8BJUJ,F\&U
sq!::g3BUR/m/+NlSC"MSR!05jP7cd5.)m(fW#5:t2i1D3iRImNsG=d?HgTQp,0u-lASmq%b
?#V/L[F5T1E,1P[s%&5hARrS@5PEFcWD)@+sMA[8r<]AU!Ne]A79<Q38rYsl1D3iDqKMP_YC;u
UH/tEB/rPe1ATD*RpPS.(D[f'.O4Y*[>M!Z8UIut!]AQ]A'F::h2SRgLh\n]AJ2C6+[(<a<j@+L
HVXD22pF=_0.GPmK_u(pUU%oKp.*Oj91`V.285d`]A9>bpqYR0O,gf51n6`V`2<RlP7.o*:OQ
l[OO(?5go[MUo;9(ripM[5Fh0aOe[E`Lsl(=1_-.enhC2p33iL)b6iK);*]A2!D"=?_33q/n%
DbsL1TjCW%[q(gVgQ3Y0o>Vu.EO2RZfM"q5&=*`!^S<s&'t5Vo)/rR\5^C2#R.#=f`6@q^[7
PV:.2JPieJeF<q]AJ1U#-pebq6=trJA#g;f1FXmjC'::&R.2pYYO,`Y)gRj1I`]Apo;q*B;auA
5FUS\p^X4g"6U'k%5#aHZ"Y`)SHe,t"%Ud$76'0>olPW4Kc)t*4lDIpTr`QZLm8d$r(j0hDp
cU_oG_gTAQj(E?Ss5ld!b9Yq9E.N9C)&:=g?L9<k25R1:]A/Jm_>C.9GN0BEjGT)f<jt-5D7d
gI"(YEW\M`@&9&ul^@@!D:BB99E('S1Dm='\=E`e/gc=5#Gl</JFda$$/p$>E%[RD]A7AIu\'
8`Lki_lOC?1f.bmS/Bf&g_''fYm`!R;=&hmK>i40Sb@,e2%#MK54Of*@gZISg]A4F9eYu'GC-
S`2u\D(s+*<(<3,4KO^R$?[)=O8%NX']Aq6#u^_<L!oZ"B9Oe`=G"2n-NAQ^b%@9sTdb5$?t%
@"89ndTl@XHGYK.'[<Z0UFK:f(1<Qc]AhddUP`no?gt8?B=CS(E#Z\.UHRhj@p7[f11A/i*RS
rhkbd#>tS_:[Y"UonT3^k]A8+6*`F^;T&--#:X)NeB]A0Zd.[,<)E=[*CjBOhS.G-CeGW`*,A4
'[UIWDnS_ah,1f?/'@o>sMcJWPr[o-fO&p]A#jk*F)P%Dq,k&LuI.jI\$4]AE)jETu`6jU.Bm#
cZuJ1(XYRb]A7FLc((g9=UDYq4[T.OX7.T8-9O11R\"_2np#3/Y"cCm*a^rO+_uA3h/:g^/97
7lDHit!4D]AIZ1M(,E-)K8lnLcs"_20+PV4G3c07QVLNXG.i67oR]Af?f<\H0#'Y9Xd0#WPKqq
*?:PR#n2i:OS8X*8ue?@fVpVQd9VG<[5+/N4)hp+oc`YB`h%#>J(%jA=!Nk2EHC'LhVk%H`Z
!=j:=rR4=UOGs/@`/BJSXe.2sg9`A\pMb4N/T'nClXSm4,;AErA9@e_%M2caMWa*UnKg?ho!
=<6a3p-_l!q1.8F9JaZo0e*uYDaG[dj/YQn%U1(?ON&PGL*ltA.[mppV,qq$Pb`1EKq!s>)J
KUc9jlaD42+31t_J5,j?JP(Y'$r$WHmD`LfO6I:,W\NsHBfCFM_!TCHt>t]AI87LaMb`/^V>3
tglN6h(.cQ1f'iZ;8iaYA8R&[]AYN-#9,jG_IR;&9^GAD$E>NL&JaAAq:`qAB4NG%LQr,Ur/(
CPb8B;N&W]AH?t"W4EdSb?+4ol\h@g,JQ4qliS3'JqN50hN,8b>^iC*3BrbXk*,QH-7ldZ:KX
f>eHuZp[Z0ATM[hO#mB,a]ARUk<G3nkD/S]AZ.=O[)fdNZ*Ze:Xbq3f)Ng>3_O$(m,9;"or4Jq
R6<><##Vp4B*]AB<T<K(k9_k7n"K='CAl4"JSV6h-S9KU[Y;*.FUisp4cM&5Kj;5laGgIW9Z<
o`ZSagTZNiN*/3,>6QFC51pud<t5iRSt/",7^0M^C(T6Ei08R2K.6L*]AM3_9L^%\ltOc`),5
Oqd`'L\HV96p(th1'i/$@j?V?-_PYiLmGAqM*-SrD0dW]AOsLsE<hbG=M(Z*W5p?DtP/ZVaIe
4L)<[X1On!\3nR4V,#E8og.RVAZfs7/)a!=Q6Tm`Bt0V3g-,F$b1`iVg#SRhD\MLtk>VEh)m
5p4(,FHCGGU"A`K\/uf2ble]A_H8%5Adi?c<Jq3GgAbflU5dS\6bAdOH\hiZT<mQglPPtdU_,
BFX(B#Ht`\IUN?mP*F?Fg94RHpk,lL\UldT-@@=5:r>9cYQ:lLhaim<N34O03TdU?4]AZ8X$\
4#TBj<Kh/FrhKuGiO]A6Xc7C\hgR]AL\iA7/0oHc8#4K3H3GfKi;FPWuG`\SI>>]A]AB]A`_4I.8%
%,(bB5-;]AG2:?l2/#0t5&)=ro/mJp/n*'@WSDjof%<e(=del8?s;chT.q0YQt5>O&(bU^8.-
I%d,K0dqKm>nFsE'=*?RU&&GamI\E?2rMBk-?6[I9+G03_25[$<@L)d%8JE0ogi3.EqWgbh9
._AL]A]AODPYY/6i.`88O]A\HAf<kpJMMeaZDi&faQ-AZm^1,sRq&9gQ@k>5ZIj#bRZm2ec5Zok
EYSBn0DD,8AkKSGPqNOdrrYm6ji#oH14D,]A*-@97]A255uB05oePSrLYY(2n`FBjnGq@WF*j[
9Sumlqal;aO/b@_X>KUXUETL)+q4N-9n.W"_BFK9ES$PC/u!S+pfS$?OIM5$0=("[8kN:)sN
r:WqDC$pN@4.R?TGi!K]A2iF8HH@Cf%oK)G)\]AFVHgcRUM't#3UW47(XH[G/bCsNkn4l;c2fC
`rEX&Wj9XcmX&O>R.r.#+1$Me,#oR8dD?@[J%b,ABK2l$_D3h_"&nKci<Qj12nBhJDqqZ88O
74VE%%HNA$aXQ9Z`sL84-ljY=FH+Llqu-er'hQ5$>X`C:$Ml<N#+Q`tZd4fiQ93->V<$!7BV
/,Z,Jt:J4[7Zi[:tCUsJG\9kSbL[*lT3Jq*5bKa1Pks09)_r@6+b:d,bf5k2d]Aq]ACD/Ri/rK
"VJ%a4M@F2Z[gf4=O,]Apk2jq<%"XR9+%.(Gm,@Dm+M's-EPXd9!^ij%W%*^i[bV3>*tq\@(l
)t%qgr4$D0[*T<u`W[B^)`7F;%)K`EbmX1C.14_[-=kU+P@onOOW?cB7T1tsfPEsupOb)9+V
,CKBg\[at1JX]AE.+\L[LH?p!>f:cJFB`qH-R+):2H%b+9H?1NCn9AE%#f26S$g6L:c/om+HF
WTI'"\$U%Yq^T!`K4tcc(a"Lt'/%$955^l7*KU:WF6N`'N'Wd,7#Q\A3f8:O'U;?sRf"&^*9
a<.)d!rumQGjDpA_@jI`u]Ao:apl@)&'Q=5Ne2t1HXBIK#_Bd*5.$2;84nq*Y@P?/gcIAu'Xi
rX!ubE2<F<S2?DQ=PT+?pC2,qHOZ4R&%oP%;@?3WhE,9Fc=ANji$7egSQSCE*jBUC=*<eds)
]A0o:RS3fkW)PhU/XVVEsDUq$-,9W24>X$!q7`GGVdOk=X=0J&,s+_i@&GQr@FENjI:N:3C9\
/=f2_LduBK\TW":4/J>ojL)ICc##6*Cm>OjntMRFo)a+n!nul&4#m%[oYUo/F0BZ$Tl#>b@q
ds"GJ&Ite;g)#qI_PM?+?&V<f]AT!B7qXUkc?7#/L2U:J.RV.EQ6!0QMV&#%\S_iiUM8q@3VO
F`?V9iNa\*+\2UsQ34m(EMJgmiS^UF^f#SsUpBZ@V`k\^YZPi%!NIaP2gFf92%&a(Gap*!bC
1Hh+5JN7@OU8ME</M2bn'eM>$(]AS88DDkj.YamcHGW#WTSlDKJOI9=_mas#]A]AsNao=IXtlGc
aN-j"(Qdb7qC9HiWAoNS[IX2)dS+X-\$k]AhT.Hg6-J'3UMnc1O,mKunfS2nt-r$[_dJ_m1er
Z:>*RWqLK1cR-YMd2b9Fe*h`=7FT/5le`'XK13O<V:=5?/?,,l[FOKs:Y)RPW%@7Ma(@`,@I
Rb*XEa0IWWa^JHclhrbHb-q/j]A"B'CdF/l1#^u=`q`Ch[nFEAo%ojlebpOOG7]AnMBBu)cFOT
uY,Ok_'")RfF1MTJ4-c+e[61hOL?N2Mpc0"pJ@!MHLQO85HF#4#Q`<k+U"2uN*.h4F^]A8u'c
qm%Z*1mWPO%i.!WMTl4;B]ACp(kgYgb^,hI@stoT@+,7lcu$EO<tIa/,TU?7gaQioclRNe[K=
;:h'_d*AQkO1Ia[=Ln(SFFXc[XoFg+!eR#A`gBd6o.D5SJlS80bHo2^F=!1bs2``%mG`CB&<
`cHJ=q0J#gaU?a'P@J980f[qg3%*M6S>n56$P/m65rnn7rN*Kip@lgiPm^fKTcjSmRf@[rE"
]A0p9"WL>+X&h0YX#SHmP$RP@r_b*F1.431BT*p`BM6L'f]ANW-+O.B&t&=*?hiE=Vq)>1!C$%
[0/DI.gSLpADLc;d.H!;@hU<^7'88'f)5W-u[mX`VlE@*M"'K]A:=1Yi#mAN!MU_d'51HJ!@0
%5eD@+(DK2+Ug,m,fu9*VVDQlh*lj#/ZLN9*5pB)I+Z`PeBROiJA4IeYXAhFiiO;Db(uDn9K
*.9B_!jik6aA'ePg=]A98@sa$e#tr2P$shKON(<jIdOZlsJKXXl=-.&qAKX"=2uJD4dClW7gl
[745"^uY$^W!50n2^)$PPA_8C$kB.X@NHV(bNCbZI<SXoma:C1j(R<Ac9og%QtW?cc!W(uD1
oY+5#k!"U]A8KI"+LpHeImWne8]Ar<ff5Qi_fRGd?7/,=b6Gn'kAgk\-gC!$U!7T.f>:Ec)V[k
QEfF^!%JMUB6_"Cu)L*$$fR3FaB(?Higfh-BF^u*WpNfmoI8(A6AE]A=SiWA!C+G5+X(K?skC
Q>p[>=4]AZN`@Pe(K*>?m++a<:;L4Z_5l]ACW<iKFMKk7URs9KXG.^3=!n:d[2HQ>O&JBb:P%O
"(N2a's*0^8"'%JcYX7)SAa!VWqhaIcqMJ$\k6is4pY/-4K/D?<U;Y-/2aW7L$>D45BC-em7
#d'VT+^q@'`P65ee'*cgg1#T@"=<I2)!,9XpIrH,*E8!OdAX"Sq,-adT0M5[l>2gci9EV#Qb
WKD6'/Nec%4JiZud!0m*;R1?gDp]ATisCuE0JS;aq40.TM.pj&Wj%YCqaqd\[.cMXB7<((,Y1
)*:[od_oT$AeVW#^"q;&[kMup@"Z)RL_L&egUs(,dA:R4`L"iSWF?qH!a^h^=$TjEb*Y[%??
l_-\E`\r?%'e,OS/Tk0ErWEc#2gO[3N=ZjA%=H]A=8$7on(kW'Lb9sP=UIP#=_e>ph`#dqaW5
Lo``kE_OGV'@(kmW3R*"BDMBk@8#+hSC'D:7W>j7;U"G=:-TF,u4"#N;'`GaKf#GmSB9FMTJ
8X0%pVQ+nr-m6Xh_BB#JSqPjl^=@ntRRj8$G7%)O<l#Q:`'G\<;!\O>T_5(VXo/E\Wn(!hDI
JV9@=a7mF++nW^GdSPdHe_Ra_geBq8(C+fdK[kV.f5IO[3Si0\LX/BY=FfM9D>h:L\^bG#^)
J$?jS[X<&U,>Wi.EhJ_QqHtWeXL!GDQ<Ns[qglo,s.=85s7,ARh?=^iCh]A\I_-@Fs4a8?AC"
cF-SrTta4O#uEH%qee'\LeB%I5%`$J;GoHfMP>:hWM2#ocQAc;Zs-X_\RE1ftstHM9XCeAQ3
`)[4fh$R0qogo3.]A7c>Km?!79)GTEsZfgepEX6tp[>fk5S=>iWuq%n-,:UVRE<'j_5@SWJ\G
OYW*ZnM>lf.Eol">[A0E?Ak1ahj;N*S0*S<e#\#oqChK=lmPs@&6a:[oFTQA4gL/0H`\OME\
Dg9e;[4f"^VuL6eW_nqLL!Y9Ka,KZj)"rlJ-/t=.qKpN.Wi&g0TXND"X/S)is\Vfro]AFcgTP
?;iq*Hd%LWfh$e`'2,k30e0cK):hPe8A\03e==#p]ACmT$.*`XU*NUJcY/*%ji@:%Z*Z)o+M;
?kR"6rWJSPGMoe!koYPrrkGV"OfG_4O49J<=>Z`J1TtSKsY^qjpp3?\e!?[I/3>$b-Njge9M
_N%,pQUSbSm>7)+QVVNcJL!i9^2IA(!)=Z@!$olMKMHaqWg(dKg]ApoioH6\hU:_H!>RYQnTC
h\o*CRr7oA]Am=mQ[(HoT+,j.5cHp]AsL!$G3+<pG`Q>Q?4c'bRJkug?ooT]A#.4DU:3elQkBj)
YS;ZQX2;2he]AR3noh+R:Q?YGd2hlhHou`!TK@p:8(?Nd"(m\Do\Q6b"]A\?>ges5UF5&J7i&Q
;-KpVS<rM\(Sk7,$b>DLtabFnomQgQiMa1*k<+-,;[B.c_<!#r%Cl_Z0@?8,O%QZtEbXI$o*
)i1Rn<T+JfTf=FKO6>Mnh"-JhtJUK,@Q*C`TmM=7ue<h$X/kch(EA5`QZ$bhbTJr/TAl[Sf>
R5?$J0O>D^;![q*T+JlT0,/3up+jA'S+\;MV.:efi?ON')&j%mPNb^4IQ\e^.6F)WWb`;XbY
.MVh&\rY*'SB#I/#fp8S>Vg"bkl;Y7UQG;;]A<aak?0?+=%s&f\N*1heW]AaXW.Mg-H+t_]ARLk
@Sj2d<smKnLo9GB//\G.[/u3b?!sg*IJbaTk'Bc!,O=q0!$>(T#Z6!eLV5WqGF9nTMnqGe6N
*gSO^EL=b-c(cLWra^bc4da#'J=c1I>[s#)"T<_1rSspfHiO<;)7pMa2";Q2K\JD!\Hj='9;
4(4"Vo$SDb()=T&QK0.o_(Z*bm+u[;l-W0gVd41-8A>:R3'G<fnS58/+kT?>"B9eFAIYIAd.
fG@7Xl%J[!<=)/Bl1<\;MbM&q^kW,kYs)pV5&@FkgZMZj2(;*57n8L2L#QX1pEL,I,nZ=h2l
n9"!#R>f-g3PNVc9,[S7A5S)aFL$id>!4WN4W@ba(_XrY@VQ$@UE%A&o9C?=^=05]A-\@ca9!
0l-Jb$bq4ARPBV2>O]ABGH2@UC*;/&p?2hEbQtMm-0kl-(=J\XgkE,D-<UTR[Fbak0mKZ7Jnj
+U;rfrC2'\pP%ZU=KUUN&;>&csWf8P=l6NV9`934Q'_N7-QWp84M_Mc6S5jA'Og-C?8'r(`.
7dp<O/@#BG?%mQ:l0(6Gjp%%\f-3NQ/gHpCf=G?&otVQ5r$0pjg]A'5dI_t'Zqm_J_pIV?8N@
d$f]A[c%)&qft\l0P$bF]AeVPi=r^YW\(aY?W.d@oHY,!(`J'*a/m]AD;7%tJ&<JX$Q0T4-=nWc
8/`/*dK(["!"D6BP\=Z#d.3/^ZM.Ba,s7q7Q4_>g&iA2ofQ<]A,D5=3HB@7s._^Z=On![\URD
YbSG4g<id)*[FjeG7rS/Fh6Dl\0;8U>6$l,`)kbC8'Lg#FJp[9KrB9'UldGJ8?[4+.,<[2u7
+#>C/D)#hmEg?=nImOR8QlL/at*"O:GD%[HRW12tJ:"Yt0G96hGYrQ!"'bN>,`kEpOTH!V`b
E[Dph_OXIh7ac.B0:o)esI&42)K]A-Ub.iudOm35>'N;GX4e$#QIP`Wq"+!ZQgOP[g/aH8ADu
NQI49CTPQ.^eH(nMFh6(?I\6Yfd\K(QZJ,W2^4@iRPhdNE'&M+O4-<SJ\[hR)jG0LTaIS^rt
f08)coXXA[SpUe041`<3R?*Z1%':HTO,XcqDS+YhKGr8u,Q[Tu"of9u\niF,Z\;S6P!_Zo\q
2t`]AUBbOg(JVjF;1RAkK\bR=AZ*_*Y6LL"TYa=*ag+!Ta6664b53ha6/m.).Q's_+(BK<:6\
K^+#fg\\":BHHo)l2Ecki<S'nIDbYap/XqZq.C:E<h"SpMmAPj)h@r[`muUc`#^J@'o[6(!4
<\c6aqN@33QLICTZS(8Gi]ALd/^[lb(A.s,?%XMH+H-tSE[?c='oshemp3VH0Z55cQq(T\9"G
%M,W?WrfkXJeJi7V4[D6smYfbI*>STSV5M5)q^aB5Xig>Fi]A9ao_#Ob/8<nrEWdF>8l]A=(cN
O$"XZ[p`:'Jqj'qW,-H`7k:]AARPptX]AT<@OR'=c;"^&fY=i4obCeXi0=PqeBY-`#=#sQdREZ
l$UB4E!Z,I^Zs#_JQ^7G*_7"e^I!Z*]A!P#fe5*d(Y@kX>#W/2eN"$'@eA\*(j/9XQ*^ZLP%n
K^Ph/teVA%a']A5l`0_ELZ<[J7IkI\bI6?H2nZ]AjbmQArSG\\2@Y4(QN/:JYbZ:A'$r&K3_1-
458%l^)1F]AEu4(CYm-f._KY70O$g`RkJdY0PDg*$q5F2!GpWsjn@L9!!M(s&l#rS=q"RiU]A#
iM>g2lXR:5W`^)9^dO#7mBj*oUF(gf'3(W>VP[U`509nWt_R>fhC^!k3#p4VAKd!e]ArWYH2V
LRMaa_@Wt]A\-(b21.X^*SWt**Blol?)`26AM6*h>gg:]A^@Hp6aL^WLG^0l(5;kec+E'&V:PS
"hE;M!`!Af2m._=N*/Wn$:0d4UI[Fu<S,YpubS\R%:uf_cuc@Fm"\@G(K1`PYHAec&-jnYgZ
V"b4V1.9hh<YNjdW]A=4Q@1,;kVWn+_=*F^bK39geU@T*3AVN)CuYdEAG7#m;Fc=pXNpX.9F+
n53dGCl-`>W5d[4]A-^oEMB,,aUU<+ddEPLIY]A6@%?MhC725@G`Dc'lBX76$5UgoCgEJm/2/>
eE_8B-dD<Xp[6\;#@2)FW/f+pm=D-nj2\0PP?2i+r4'!7_V-pcHY<2l)/V8sn3V?kjV_Flc0
R+$_(n#$DB<)m&OTed@07*25bAO=^TF."s<"te"t^Ym_G[b8Mk#\^HPZ;/D@2SgQMrak]ABcU
5q=etcB-T@gJ3_5GIch+/XkXNiHZSWlKg4Z$)e"-Uu[/dKq]A!3Q360/C1sL/R)-\hn%.cGV7
!ic]A8&AF,c21,bMVjufT!TRS"n/Z`QlT_H(kg^.M4DMI\F-F"u$OsmNR'Yu!8(RlL+\tV?$X
XQTOd1hR5OO:>EXJrPCd[9fC[L39V5O+CPH=\PBZI0HPgrY\RmuckZVTA2;*&pQijT&XkooJ
Jq@Un$f=9sa;;XQhnST!+S;K*+q39>>]AeEWUFM(GSWN%MLNe."I',tD!q2C+.WRdR2FljbP3
p^bo$6AEXL%0;bk9R]A#t?XpZb_;Jb%]A'<S!(0qY=s+pVlB$)H.B-Z$+5C_OkJC-G"-8h3j@C
60_XPP^;ftJ`M<4X3/fQhd;`[p/KB1ot*D=d.^6FWh.mCi7I>^^?Yd\+ikLsq![5S24JTL@7
<'C>WNl#hC8D!0l_Sh;%l"Uk/C.><]AZp"YjFIXt8kla6j1#,j%m]A0$n)Hmc)On>o10,"ianm
<p2DCXbZ+br08)9Wpi4:_$sbOVP+qJ^#t">]Ac[t@OCHIH\rOl2<1smSKq%r[["O\]A0]Ar^V.[
Q!P.]ALH^.\3M@S^WW;V)P4ZO-O.'q3heUo"F'VDc1gU$4U42$oE,n4Edf>%C=.`^s@aJuEfP
7ksd6Xc"ler/QYmqsSbmX9DjF+Pp2G`9Nc'GuDjY,Eh;N>m2MrmN@lOJ,Acc>\TC=!XKu%>@
i9u\LQ"or??81*8XO$GJtT>Or<3*0hPR']A\q'IK]A?=ef'kD9$IR2"/UB/mMSYY+D@i<Zm"td
.gq4\OMTMPK&dK,hHdR)s]Aih_`Ts`K)m^Du+K4Q^./N7OF>V*1,JR-?Vd#iTek\SI/63c-e>
h$,;mT/'6=:e$s;F9@@J69j&_?KWaLr%\C#B&,s(flBP'eK+>A\=[&q^D[AJ,jAiSKqOCEk[
oXkk3:&^n-LL-enk3,Zhr2!6Y`pX#C)JE_taaU+%b;4k?=(=h\^JcV5h\K=IjanLt,6:/Tf#
mj3DDI?S6mAn0:6X6u;Z)QOYcI,l!FAs=>q18)6M;n&^Bi$W3i$)fK_L0/>j\?DYNnjI;'`C
6WFnt2RtpB+V3/r:JR&gcmQqPn\lW\uk`qSlR(eaL<72cH;l:K:N*4$Oq)#&L3,&+6aH_^$.
9[P/Y]AL#Vm!;quG\1!PSIJ2h'(=KuO$k0\5g#R-e%$MZ;`:i]AIP]A<7s,[bbq_3'X[-MBME^a
mGp1T<N[HkkFphaF<f7Y6<rsW5uI&[[8HS)#!d,^u9\W.DuS?rSQ0WeduAHTc'6)ZYR:lEk%
gAe1_B,gL6sbDE]A$u0Y5b;hs:,^W7S0JH!AdIP<.!_lG"6AeN-!9_n?'A\l&Z0<$VsOO;WkB
c4nYsEtH#`CaP53ciWB.Xa;S6\a'&CVtD^ObOj;gcR">^A&'8D'l7\d2JZK;nq']AOq0psL1I
km,nc91C78%R-;JBc1puNbsJ/-nn070i-P!ap+$uB35OV,GsPRY>TD>ERb_3ig)7L\]AnN.'M
pdljnD,Gi04EhP@bhQRQ$%t!d'gmD_onPj-i>B]Ahb1'/V0Z%+^`d&M%D5Pc<):Zbgqnf;6hQ
Fb5d!A).a//jo\:jaj]A>J"KM#RYuharY>IGfgI6&ENeuS<P<='k"V7:I+D1bilHOJ^YulD+d
RnkAY]Aea]A:Yg%F;#_b4lQ$bKWP/Go%nW+hhRZ\Kdk7RrL2+RcKL1G[4Mebg!>KgP1V_JRuNc
Z=Xoe$`UmXb-N*;7".F`bWkTJ[=le9)R`c%qUmqnE6:*;BM_)qjba062hU/+>m^a![(Cu[!o
fZ./3:o=!C1I0a,LnQ0:e_m7VSld!F)aqO8mY);0lU:Vr?-&`).322tI8n'eHk@m')Z/G,\c
SK@@[K6eNu2662RHr@MEi+5]A]AP^P+:/#(Jr&q+JfMhEc22m[G'$MZCqDpf'ChKW3g)rC0"<'
'ILZrE*P.Z*!:lPi2G!=!R%WTn-h_",2Z:fEbthSh/nu2DICU1_PiCE>d$>La/VdZakKS9L*
[MG('X_N".`HNZ#`1#");NQnD$XR7^nP!?.M%S?MoHbVAa$q^_1*:2JrP(QUQA?KtuMc%teL
<L\]A0W/3-t/:>Kt4*S@2-S[RM)4,.]AFj45>]A/9H.)&HQ53Q[-:LSo$tA=nmo'BiQdIQkJ?$l
c_Q@Z0!S$TbO_XRiS`1j41pn_rIuOYS9@1VT1(%]A:&nR^-5Ae5moE^ATls!<,O.\Z1ld`T[T
]A&70X0,u9=XR&(Y=PkbQV`Aha$P]AQcZ\ZW69;g'$\!l,S+/XimZ#cZH]AllFk)6&'oE!0d@1b
e/DtVAVSU%Ql7_]A=#M7p#;O@(lf3^jb^*h`5M3X^9[m"`m/0KrZklAnXN=[H^(:&2)AN7Km`
UpUkn_AfW!nDa7Z>U*AUFd[a;H>0c,K`;]ArI<I<F-->!UUV`J\Fco2ks<n#iEi%Itfb(EcS,
GonSEHd8V+J@]A"q6>[.C,[.:/Ek>>,9*,o@Jf3R3jAC2=i^V7.:$Yu\@bB^ACf-,RXn'pEme
/+1F7h8OYW&91"*tL&6it,2nslFO1Lb6b(E$[E$!GP5A2"N;):9bA%t'>4"a=h@(qM<EBHWY
`^4;+UUUfQ)J,!T*AZYdbJP.r1gbAhR=J05^NmDOFq3o2t"Gcfl6%LULkHrPcS\_26)d?tA;
H+>;!h:+9I%o/qI3(ZhU:qGB6\<d9l'k9:EYB?0HG7RuYnbXRagnr]A=ZTp\e\%qjaP'>l`>m
g:__;3NErfi3n+lSu0[-f5cHQu9&NMdJo<.#`k2q8r4Tu3Tpq'e:#`[e;'L&i%+dK7*O:PFh
Icji]AK>/nsj']AoW@+]ANF#K"JJ:0(cofi0Nub\hod8FZK/%C_Is=K"nQE_f4$9&ZtWF!h;fFk
CKU:dM\PM!Ps]AaB"r"k\;n#T2CO.^ToLP"5jqY0rNdhQ^A&.l!qt_\IB#uP[pA'Afk]A>TN,h
5Z)T_,Cr3G2r`(c4R;qdkmBI5<"cC([&Ap^4jEVr<b;efKnD%kKIGP+H0!Z6k(C=ha(f&^8R
#'`&QltRa>WXb9#D5'AC49'8[r)dA6J+>G*J_ZrPe!BG=#;gKM;N"f4d_oRGCBG^\qUfh%qj
B^Gg+n,*N]A\3NeffZ_D+W;p5Lrt-&kb`RX>d4n"-rA8.QqnH^:fuRo?TODA<265fG&>pJKoZ
gbQpOfHb'4gFo9'S'RG!>Q[7=EV/Utbno$Id"tiN*Noo2Dmj1Dl<Sum,G.ZGd/`cCOS,*^K@
Rtn?-.]A-C1)),=[3U)E=h"g!g1\F6gOYB#Nd.mS]A1lNCH]AZjgkO19]As+TAi02p.Eo54/rU.J
LLV!XSXHr"`D%Am`^F3@6I*266Y#qK4HeiKGr7!<hqK$JAg"rbo<MK82#q!(`L?<2']AT4NFD
P;:8-Bif$%l&"II?fh^8K%A_Q/*;[%&()^kLmYj]A\mE425.5?+WgRbj4mdQ!V\)ar`oXt+g3
c4i2Z9QWQ/P\JLA]A]AX@3+a8CHMAr9BP=BQ0"4A+At&6smSJ)q2%75R9r$EF+)-?7de4S8FgU
?PC^OR>WZ/"7O.AkhEfI0N=_^K/>k":pUl.7o"fF)Pr0Hb+#)UR"C`bbguJL6\"'WW<)N\N!
4F?#hH[EW2=Hg3V%6cTHGfr.L>1);d?G?h0FH]A-8snS_r;i,HsUj]A&ZPJ#+'72]AK!h_>?$+.
Fc9@)T`#8E;"'eUR'k^fP@DSg0pfr_2!t6-oQ`-*$G;0<Zc&ELanIf@5lBtL@M;"Cs35Jt5P
T)(#@KoY,0bpp$C<J)-2A+N-<omg_;Xo^WQ*3ijC]AE-jY=M>tFK.u9LBUXdB^MicBYLlef;9
\n':m-%>gWT!),89F]A.QRh(!rsm_E]AgIM*"ns_)NH2`>ij6oDm)UGJ'P>lLWkI!H4R;T.!ek
3m6ePCQY9d3)i@13Xs=@;*d=VI0P<Goq9au-f]Ag82G8CofgerTe@O<gTfqef7._Nd1mhUb!K
FkHZKj^>o@P-\h/&PFYD<QCS1L\-o?']AqSPl_B^d&7)fB`X_(i@.1jm`ja_qoW,2(OP]Ah=MA
`k?RN2rFZqA;[I"OQ+c_hP/5:)5ouHeU=U6796-3L+eP<h:M:8dCKL-Rhl2F)AkSIDC*aLb'
lYJ_3%K/4V"U1jV-Q\YMMkD:EbY'U&EHaV=k5#R_m.'ng78QsM%(94W1nh:n]ALK$"65g/e<K
0'gBl;]A@f'^?l.M?.?isiT+Vn7Kr1Z(3K"HMBLG<4bA_QS%?BTQeNdF.X*Aq'1k$eG%pb)<4
mnD`lNpoPb0tI2JO0rF'-#lbi9]Ah\gWqMkPmQjVS)NL0mbsIj+lom]AEK\gN.SNAGC=6%qihN
"?OQ@/0BXVp**rXm4.HL\$qC\HmH)9>AZ$]AdW*PHaiF7ZZGM<mI",*l-q0P!0T>Y/N+3l+m8
H.]AJplL-EKV;dc"LJ]Af='?Q9gf\%0n0/&c@JdaC"pNAI]AZ,m9PHS4`3c[L&\(\Wlh'_4D2j;
"d3QF\83R$[=0Wb)N_?PXC8IN#`\AdE#dnRa?SjB:cOY)*"#Hn;_if"uS1]Ah-AS#%*A8@?';
^'e`4:S8BPfr6ac/>K#?u`CRspGlR=]Ag\Uj\u'or1K<G>,*DSLb/U=bQQ`+?nj;;n"V\QtdY
S)DfZH!O$d0(]A4ZTkqcfMOYZ]A<lSjH:iuE0Hto]AP@ITYd3KIl9m1tR)%8c09A;&U#(F)`2WJ
@_Q]A&(K%hF,s@'4I#s,Lh=ej%!VQ8OB8bXJCa#,b2E4PG\V^3/QMrG*1bic"g)\ahY\g[IVE
XAL<g*+4!]AAK3WR@%VuP<A=rm]Ad:913.VUQa1g7P>CIDtH9Q?:qCfC@ko)(GsDdnr:]A*`bo'
H(9c9/bODZKWP2'*<u3cX*_OlLa\8nVLHQ^]AhP?U<Y<T[Xj@C2>Yl9L'Yu4-<2-jkc\bKnkh
oXoqm*(;gD+K\lI@L7\mY(\sbi,99a0]AebNII_:!*\mL,kWB!3`h-'F/af/#8V9p>a"-d.QV
ZR<u9d8+I,'T/E^b:XOIapDemm9.aq"X4Pt?_nnHVN?#fNf-PMDLpP9daef)A&'MDIW"ap`P
c47'!s$\T.G'p1-;G,Y0H2qg-l&T:.MCJ;142qhuGA:+?s)tJcf,o#[T8L>AP".G=Aop+'Yh
[*"Y8uG&*lSbB+mnk]ATCsfYT]Ah$f*6Fj]A@=N#Qm&'Z$7KWC,1tX`B5'\5)uj\hO.HN\P$%Ff
HooVD5>/&l1D:Ae%s;HgoM`(,>rqjl\K5W]ANFBHh-g%R;Mu=3X1,@nS9F[rK=VX"<<a=eP=<
9uqU/k4,R,!48'ZCoNmMW@Zt8[^n(F;Z&/]A[XLScDRNe>d5KUuPD:?>tS0M`EWZ#eYuQ-E>2
qqR4\"`X@[a0,+L7at%N;$8giW7Q9&69)9%_sG[^^g:]A?$[FWV9gBM(n-Ha<Bj9ZhdaIak-,
8pe]A-Vi9?ncEAdH/:2*8eF8KFM?T%dG^80(!M!m[B,8GLAWB$Lb"`e#N-51N]A+p06Gi_6V3$
0n817[FAIg,Lqji7lk,]A6%Ys6a<\>^X;[kege%j);9.'g:%.V@^ap&)[`hiYF::U?U=f"JHM
@l1<#Ju9ZX%t<=hq^LMI5#^rh+u<Wchp<J1NqNH1$0[4:TF-fkQo(nI?b/WLd)R`i'13n<[R
Hp;"mR2l9?-&oF2UP(U*S<C2^X`:NBW"HJ9+1K0_XY0bnlVV^Ed7Re(]AGEJ&Af`Hm+E_mYMG
QapYgn[OI]A(2#5<gHQ;UWs(6.9i>K[J2<o#;._;Lb5PY=OS!G53\mh[<-_kjcu#ed9Z+(&n]A
5+D&if"*O#Ei[HU1b!]A/jII-0Q8lQlkc_`AABsOp3C/=4Js#(Z_64):932?[Gp\b[!Da5L,I
.>UeFmGR=to]Ab`pj%OFl8/@D1haB1+R[lCB+9_P;14Q'4FK$ok6k;^\lm>4_RrZqmNd(I/<2
CT;9V\NCMd'<4\UMb,oBD>]Aq!iL"*mH`RQLT\DGUs)V,k[bk;drdg9V5YTY55Ri2I@"i1d-a
n9k9bNe/hmt9KNh'iRdno0raXPM;E_k9ZDq"u8Z)dqR@9f:=p&:3lt#gP#Y*Fn5W*UkaePk9
a/:k5<Qo,u02666)-"!tk&lSCX*?dfjI@LR_#;fXJB(jP5a[.0YecGQEAu`'gYl&3_:4;5,q
d'.I&`i[eq!7:S$Gi]ASK04.Ek^/r2!=_Qa4b"+DqNS1m`c-KpQ7%.(!IjLdA0DmXMhX-0P85
C-XDi.SAqd&?5(S[Lj!?p)kcWP&r+Cu:'Tc!H''Fu/k<0-maI^[JnLH"RjdM*3.:c(Ym445c
k@]A($HlM:W\o2&=EdL%SEnnA5H1WD74,q<AgZTc,BCFV<:g\L$KJ\a$qLYKpr/]Au!e;PBALU
ZH&\_Vp)C7]AuKaSuViK3%)rbsipG&ejP(8[hPLWB6FjNsnB.&+4kKG:1HINQ1<m+rqE>nXm'
fI*k;pR@Bd^Jep.G!e]A:iV-9PhbJSeOY5bY,i75DNS]A:FUA3[!rpRI^@j[(!JCrX]AHf\rtQ1
mP*D4Iie.='2mDhA*t^:+p.]AeJ=)pA9IU!YGCof/r(D]AP%;.)R3""U'*7GA2LdU*m0qUF*1W
JmGnkW3W@-gl[t[7fsTNJCAocS%at\c'SS36F`S,,Zq@;=rP.(RZHHH9"MuDn#dBgI;d^el9
:R'hIFH9KgW*&1FFA/CbM!+tIf+TAG?nMpCu/\<6JpIaZu=YdPP=n,JAS"$%U-s=;/hP41oe
7;i)HL^K!L7,*CFMQ\UK@b_SN[FURea]A4_mf(H=nR;>X.+S-QZcZ=f?k0D,F'%B16-Hc<o+@
7t!mFE#@Kl)Fcd[7QSi#7Llua53q2fpA@e/SMdG-SI?R/8C_8uqT8nL.7pQ0VZP:j$Ti*FJa
i2D_.i"eD_+cNk:+6t0:Jq3eR&S"7@WH">_5,hL^B=APiP-\ilST><&(??20WL?o;]AI-C.X-
eNG4BL!.3&Qgq7-ATZCB4J!P:TJ;E.tho0WuQ&ZInhSo1D>>`d#UF.BXf5*tkS2)9=cCI``-
\#*k_]A=TQ2J49a%_\o-*cYdWQO$\.^jBJ[):"e.c52hK/BuhDM/nEMDLI^f3_k,/'-l4-]ACm
X"Q@H7nMD:a2pAA/:2SYOU^,W"2*X'T&BJ=<qgq3I&bq2FWC'*h,?Z'rH:!h(dNH^7T6JLfN
k!pKJpr6r@!JLb6FtOVVIR6L2.X+mpG5FEd_<"Mi$`kbFd.TE-PSC0RJjIs0=&-!Qd1,eF%i
!BuULG67d1LhmYKeub[ON3[*ONnt?2^OH;)5IZoqUJD"]A!_UMJ6kL4o@AsoJNecIel2]A3a@j
'i);r^9@FjYm8Vf6p.$ulg<&.amg6uY3p02R*LTPc2]A)o*2*n=/>n[`k<QiFh,[j=JT.sHYQ
5e]At:f.BGB]A(5&#WaD#QVfOr>tS;onKY=omD<5%#Bo\7<Yq+8SQ,;s]A)>pfWm-J$6*Q<%od9
6Nd[%:*/q$l_N(jBu,/;uWr30-(EU)]A<Z2GDDfEMJ(DcjY[Stio8q:ODr8OY8aHuJ=<Vg#C$
J*O,K"G<gUNU@r+)#JY)K?maRL4<IS#&.3rh#k4fitgpE**VEJOH(kU[PF32<>K*TYb]AJ*7[
f"P,h]AN(_T=#^f#QT.Gbpu&DlK(,dis-'JS-i0C*F]AIG$I*UKZ=&D$9eJ@"eW]AU+gN_m<V;Q
!#I4*mQ@pWcoKPAiXQ_i@1HjX+r9Re2_)fXSQ%Oh[+LT3'oT[qK9Ofr922S3Ck&VWNiP4KL-
[V,,#.i.<D0\ONf7.->/c3/!D87#8k+-Qd#+#br3KOc9=-3W;SG<gc+6&1[%-dX*Rls)JI=-
GNQ[N@q@XSsj<9JSi184`)rWLaZ!6b(#[m</Y<H3U*%V,*sf:/6s5jZG5?eBBqgn4TBK@?W2
QGtG5rtBm>n:Nm%<+Q,[o6h/QnL&g65d=);KSK@9j/!oHO,es3a$)6GG/`/7qHunn_o;;fJf
"HTk'o)E^$*(X#D$3d:0)2siUoYhR:=:3/_?I:^p4rmpKqc4Kk1c'$kOZoQ^tG2aQnXK<>GR
8SM"<s@+gY_TBP/C8d7TDqpp=3eW?<Fa0p>sLQ(/VU8g'(5;.IgaDokO/phcT&,HO;7PGpfK
,0m*e#0\k:Y,]AB;mnd5i'5`h&*B]A:N;lW&M-W<HeGT+4F[l0jg>2"uNj-OaP_Iq[s2ASD^G;
Lm\G0k_4E^f$$&bH%6[UJlX(=+)^Re$>W7bQT-HkWG!3'"1\c^Wg9H^KRlCP71@Nl'Z?]AW=/
]A&fU]A13:,+#mSNImtQ5W#t<$X?&2o;b(P4Ek0O&^o)(m3Dc0?bJ)ZTLe($O4hT%]ADHgpYCXn
?i6hW&@ZrhjF#[/pJNFEGI\C_LC.s-2n53=t0r$[a9bjT2AB9P'UDZ<f3(+ZhIS#jr@"/-?&
oU$[<]A?T^QHgG%cK**mL=(Y`RAD52lgLF/F]AVFSqeUW$!prWN1t))K!#=U2gLHuR7)epjRm+
%O3Y$MNj5s%W(pr^PYn-h*C?=!d#o7S0%kVH$VrF:li-`Hl]AV9!RrtSM$*Hqq15(aks>=T9@
SalOtmu/snK(Y)=o/QDI3s2a?EY)sV-lT7?,l/PF$?:Uln;QO-[T"<%#Fjo<Nrs"2\qPbS0s
S+gne+8kAbO,&!;UO#f2rI7)(12fr1hUni%=HBIn#splURt%klAI&[4@t=S*pAYf2dp8!RIj
4`8DnLki6CHT)om[;OhOak#N.1(K8nU@'etqMiI3L:N1&Ah<SpFJ$5mJ<;:B/cY?U8"j-tG=
R@cHp-VYM=eg&[O1Ih1![$5:)'fBE,1CleJn.>MErM9UP@j9_O-k'LqD=P<P`#PGPOnDW$tX
GTgCilb*M5=XsbBqf;I58gt[2RKd>\$AClLfi70poHFTd\fDj=nk34YZp!$^Xp-0#NHk+GNG
0krNGcVD'1UqNuXd]A"B[R`QXsZ_b(P<XdmdIc(%jd^'iQ-ZIFQI@QABYs^P\:`^@`qWb5sAr
j)7]AGlPd@j(ae-kH5?]AmIL<(NF@K%QBYu2Qo/8I!KZ=&\_`-RIppL8h0IR<@WTtu=0!h]AkaR
Zu2*#\r_T;T;&G^<ZM>_*=Dq57-$<1"(3TMKpl47^PHp%b4@,R4c`#qS9*Y+_tqrBIPhZna!
$Q@G7/9;J7;hs9-!]A9@Z>*VDkUcTg`>#.l)G(Q]A"E@a-pjo=A##IT9%dH/A#jgk?j=>4>i^N
^s7Y^u^-TZ@DU0+oB!R3HX8(/PM]AW2Un4FX[A_E!Uh._%Dg^V4'+YYnk@)gS*?6o[;YHLn$I
IHn]AuWNVs-S&0E6:ShjXL6D]A9t-KNSo)[j)R;G$_uK"IL+rZJa8^a#!Ku/:Zu'^O6a(EGb;4
c$-h/MfU&;L,BX+rE*42hl$3]Ac0V$E]A'f"\iI%@MBYP)XP>=nlk`]AYj-eEc_6FZ"+K9%:gi-
f57ZKg2Pc1-Q*m!o&MQgXmc<B9GiVL*n:T.^AOI4PoA>;t=!'W"m8f_+c6=q^Gk(Zu;'."2"
cfl/,uJ(SHaI/C/M=D,2q:`DMjVfP%f6Gad:PM'"od=lJr>*]A)<Y2,UH^ON?Qj86SXaT&k0D
&?_C>_3\/c:hOu=Ac%]AB*b,TaR[hAR.=G1=BF2U,-5m<:%sg]A#aCGG>uf=a8epeTQR))[Sc3
7&,u\I-:<8i@kB3TTpDjtG-pGCSI7np0DL!"o__$!KTE"H(qRUCiPCZ#J>qcg"=_*pqj4@0'
DTg$Bi.#&-(jZ-Dp=H.8[-qISRm3?tp"Act87Eg%`-;Fb_b^pI&TZV"58J.8Vp^$d_s8.4)\
D\1^129r<eNC29_RZIBa`CQPF$aoIfK~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="648" y="0" width="302" height="292"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="1" left="1" bottom="1" right="1"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=if(len($cata)=0,\"SUV\",$cata)+\"-TOP5车型销量\"]]></O>
<FRFont name="微软雅黑" style="0" size="88"/>
<Position pos="2"/>
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
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=if(len($cata)=0,"SUV",$cata)+"-TOP5车型销量"]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" cs="5" rs="10">
<O t="CC">
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
<Plot class="com.fr.chart.chartattr.Pie3DPlot">
<Plot>
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
<Depth value="20"/>
<Projection mode="2" elevation="25" rotation="0" focus="800"/>
</Plot>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="车型" valueName="2013年" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds4]]></Name>
</TableData>
<CategoryName value=""/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
</Chart>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="11">
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
<FRFont name="微软雅黑" style="0" size="88"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[mCWa-P@rH*hO.,]A>,k7n_$-O7,0CNd7!@!Z>Ta@T#s1OX"VbJp5Y/$;'%n`9@?7^JKM;aL&k
6F]A";4j(fMNao_@dMQ65IX,fP*hes7=Mt1Y6i!fCesTV4K(/o:5]AJhq@Gpgd,7$^Td$^K3s#
saX=\o0:H!ko#[eqT7$27g7NR[.!r]A>IJq88=8/,OHk-_5>T!?#am$.=g9l&id*/WBeRbr.C
S(+/*_9jaas9pZs5ckrrij-WC&?'=e]AIFGY'q>e_h5hp^9$h3bGdK$kDC:0"qB-GgEH02Z0C
0WJ+ij2B?hF5#(nGC'kQEk#\,t9[YP'MSdUR7X:K,d=Xl.A:[6SRUr]A."g37t]A9i3mK9JeA;
?E`h0qX/k#`r+'8$[n'a3\Lq!]A9t8=CO4I6NB8Zik[MJ;AmiB%8fi,fh3_E<!'dmdQm;RH0E
M1=e'M#K[)%_`('X2bp$YiDjhKZ?%W]A^Sh\aK&W3dBArd!1YqsFqTb'YY7YhZ?d(.1AJY'l3
UjSo)C(poaYII5<Onuj_-G0k#!G.e=p%X)42lYkGIQp^m6(n4Y:6oT-IJoV<%3d`_%\K083L
8(;5WQOhn'/>BX7WV-@H6UA=l8$!O\-9NbXV*,Z,kbh=24#%KBQqU=\G6tO%\'jIH"fD;Mt%
u[H!^$Eq2/IJkU_bTS\@=]A)ac[Ad"Z0o6&OZ8fJ-VF?-+;WW`b^)`O`_89u`Y$I)fBs+m0Y#
o^I,C,Itf2k$uZgO@Q3X(JO<'UK>7DN2ho;l),:UEt(.#T_G%E5R(?A&N8ckKe\uT<-rljI5
Y<^J#:]A+b`L:.5>`a>G=o4pII>T0[HNP")9'DgL:e2RSPqJ.CV\3S,RgnIPnW00]Aup>1ZAMf
_!0fm<TfA\a[6(j1WE4DK;`s*VIYAZ3K!Tf"8Ac>!SV#r5a%]A6=,<IoX2W,n1K7>SgG%4X#j
4_hTOcB0I(fj471jk8J4apYr0ub&R,7.qo^(uN8RqKX@]A\UpjE=71Imk@S5fO_hkZX8td[_i
Zr82+<N;@lTi>Ik)@H+rRgR:Y.WlYP!p)V-+EIMs`!<&"s7aC0)cn.OTAmOS"J)GJY='RNJ>
-1(WjL`Qc9(eKl,MBZ!e)cr;@87DS:%=O0Qq&,Fh.pCB2*J0j6Z8Z_n:*3Ceo<O_(M7aicnX
dCRW<;f+OY>=Tke4mpA++m`l8t`%SZ]Au:Q212(?1W;e6&&a>I!^B+L(s""60R>86*GFkVqr1
%VXJ")ih_.<%fKk,VI<Us^ip"([s)rDRV:0Vp3S:gHHjOX,U;)?!A(ikb4gYYs*E4`kXf*B(
(10KN?bWHs*au"0@+%]Aiml82R\>+[g/=(q-fAr"g76q,c(DT"KS:YNQjmWoMLrYooE@Y<k.s
""9pER,K<eD_D3c*TCrr-%5Z>E:A^)gTWDjr#EEUBpn+P3"l3M]AIo""VeXOl-&]A6kuOG`BTN
Fa#/fJ:3K0(D,,.c?%MPCRWQ#B\-lsf3d]A[KGULr.%+MHWUO7J]A-UFMqsDZo,blSWWEq*3Hb
02r58rVD5ONY*IDI*Rdq3;p>M,/l;Ht:[%=+\e_sFd(_Pt8,=.Qu:N)U("AAp\UE5]A535@rW
)1$09Ls)k`"?Z37994l@=iNLk>;%60F1t%-mn)V5?MrCm`qS5?pi^sWW\n&0cEKQTKM6XEqr
`g\H.mm'nZ\)oRGm-RJ&e+sdmT>=7#00N>E^+2_R<8fUq:>Jehp`?nAA$$1E*eL'<Y"FY\-<
rq2DB!JbGFohK?VO:6gp?ff?DQRWM9g2_ItP\Z4C5^hkm*?b(MS<lc[\`@]Aj$O\>ATFR6J's
!T#LO/bWm[_WebSF]AGAnAb37.@Ga+(jUNm/%`b>"`q[*H7EOTpN!:s8!`Zt1m<6@=miE#2Dl
,Fp)33'sBf"9/%-D,S"<I\+3.IJK1dDT@,+fF2i<N251Q?`f0)YYd(fnLfKFRZ:<'&O>;>Ud
GRZ&FtAJj3UHOle#i@mZ"8VDUJ-_@GK'-`e9W^h;YB2i:t+X(:rj4T2sR]A:tTWeq8.BRa#k'
XV]AjlR,P`H^DGs#ardf$`iatRThJ\*Y95Z#4BuC1g!rgocD#rG>j84i-@!bhO/of*p*:H(42
Tm"DepAMY8*iW[[2]AO3]Ac=N,`#X;T6]AW$C/RK3g!5p=ZDo@$i[*1nYiOs.W@%(Mjr8T8\AJ?
N'7]AP$0=Zul+CLP,A/#DE[t&q$&"cX6j.tAN9P?IK;Y0KYlg'GRZ20QW''LB5S1f'k!b4cCG
<qp'Db1_E58=>l7&<*N"QV!7O6,Q(uP$AX@=:Y'UB=5%rR,C.L<,\oHf9]AeT9Hs-J)Y,He^O
2^qIke&N?cg1io@j5)J;;0IqFgCh-?>"Bc1V>^n6.D)&dd^`QJWF__%bW-RGa5s9'I6286]AR
qhg)7Q`Bb=X_Cun[sueZ?E%M.\\&r"uKn/LG(/T<m7BW+H;hM%&"(b]A9fBf]AdLmSdG5Cd[EH
bK`#8C_[AJT^TD,Hb,@K)Pp>is^lqbO`IU=&KUkIR@$SpD9g;5_F#)j&>`Ie#A[\Ma8U4=92
QemS6Or``7AY@^fr9JL#`$%Y1bq^(\a:3EUf2(^,/uKS%<hpKe:J..BY4H/NC[W"3jBqFU@#
GCNMRrB`?Z%hklHt6""Ip+oAcHZM*;kiOE<F<Hs0X;*;MK3'&ZtjjVN.-LGo@T:#uCO.PRZ)
+I/#BI!1B4P*RjHLRht`s9USu!Ka'5eh^Y%;8qPl;;j(BXAr0(ahP[;L6!sqs,b7/gFt17&0
34D>$ZK\TcUUd(\%^?rD&^'MKW`&fkGtIjPY?LLDgcL!OakO0s,M-[4&k0qa3*RVVrGE`A=k
!%lXi%3[<V8HIIo*iX7bt\p[I_;:qPchi79?ZhgF=9pIdX6dIsbALhec1SfUN.M4u!ZS).$*
P2ddB+fJNik)!3@>mnFEWPBhoD+C*pc;ABkm/6sJp]AT&5+"^(=,g'l6*5aoP8KRc,.S[3Fqo
7.[bZ9RI#I$';'.Z6!Rs8U)ak,ClR"Qd7[/U,;7kc^s'[5*5o,TM76tkHe6g<@]ApP?6kPnt>
sSP3o9YOS+3aXj"&<"<Lsl$XPDq&-mi)sL\@*&I-53)K5[f^e=l=grMcT12h)W]AbVkG5kY8V
%%l4Situ)l.qiLStG7>gG@@q(NZp/kSU.t@uJ@tc9*#,8[]Aj3_.!pg*TIX@-*ZPGTa?TAA"-
Ao"#7+H.KH]A9UL0PsMi8Q5<`_Pa_>iKmP)`Qd:SS7k.P@%F8bt9nA%Zr)U;t/kj'<aUXqFa5
"A3BbQnP_(L:r$6>k?[-5DeHlEd"8Vr^"..29QJD57(M@nWLrTK2?E=QFX.KZTU-[GG^t_Na
2na^:^p*AdIio?/%-a;9^kGk78"KV@FL>r"b\1O/O9>iaZ2&jD:<E@BbI%o("a6%bV\aS'XV
YgW4n<MlKZPr&!*RG=!uj2N_4]AgpIG<4:+tpJ]APdPl?T7icg'7DRUJjaP@^-))7#%q)Zp_m#
s):-5<7j\-n2@B)51JQNA6L+HtFdF<=OENhNu:c>+XY!kJb*s>AOIP!3f-:Yjkc#KMA`"ILB
;ATG<%M$+B(t1@#t[7EK2im<:AqaF]ASfEfPHi:VZr"W`GPK[Yp"/p4(tMh[(Z<B[(CoSUTT6
gW<iP[2;FbfJM,C39Lu\b2.%Y/+K"@5FkOum+'6>-@W8m:LKSM21&rrL36J5o/f%g]A@+'fq<
8t<"6uLGErurqg#4rloiblnI-m3s9gY-+ee4,=n+6fd,2F>$G\)SNC++G*W@1>@e'KCKTiWL
Z)_B'0\T]A=Qq)<:=+%FL\:-JDpNm=*<9o(dkoAed-IjF!M<Z)^+O:Ls"hQ[<n<R)s?3(H':F
YVe2fs+=_/AikkoHi1MCrD5@+rfblDeF>OHU((uX>kL=;mI;o:)q),9i;M2h)j\1a(^Z5g8P
p3CS?;N^qqb>DbbJPW5;kT@:oSg$=R<)")Ht<QciaVl*49=Eh%GC09%nUf7-N;.R.h-`6Vdr
e0]Ar^(]A`JAAMXEO?nV:n%ZM&@=YptCGca*@D2L1N[qP*(bI2TF9QUYui'SVq+Mf3]AiO2m4Q2
_bX=r%.BE*B=anS9G"dPn]ACPgN%qV52tW9C/+h+^;p@4(k&o;TZr2E3GP0N!jJnlL#K;[+\Y
h:_Q>-1VA5R-:"q=CNNa'm$t<)7Mt:`(5S[d%9HYh[cI/T%'Lkop0=s9@S#EkZMLEB+7elq%
r3R9]AGgs"(0PJol*aoc'V:P]Aak4o:pl[Zr5BIl7p[]AjLFDdCcmm.K,,i,S&MqV'K?tsGXW*'
U0Ycs1Vaa-pu>2`^>S%H>9)/YY\$h!.i/be6!Qq@,Ck?(dY=iE]A0"E=3+C)(BI1kZM\3e^#G
9-Y@/rICqni#8h"BqFbtG_fN6%gdJ%_41>'go%Q$k*sNb)_<c@Z/m"H1A7GNrkJC/NM]Aoa/?
T0k%BTHtfkKoa>@UCC$+.uRg3kq1qVf-kW'&I'#\f5;dYT.@N[Qh:O6b3?2Ip2t[s^`XVY-@
&LNpTaB%;A#dQ3pN&(YPldoT\-FkmAooQ1G14f90cJZ_0CS4bM$=P;7%?pa%()Qt'9Y]Ah.7Q
"M%1N_\#O,0Dg@E,'jsoOsbXMZ3pL9NIUId'7K^O^4oqUO6WTYq7]A/`kV%S<rKEW4t8[j;^#
Ol@_F6^:Z-K_O=Zro?h_cg2rQ?qF*V(Lllgu<HBo1S7s@#SBkF<YqW-N(0<`b7EcpqnhdjKC
mIfX%Vc"7mK+\$"-X,7fRatg>Pr$EBlk'^*dS;e)`gcOOhSr$C7:j0H9$sEqaYBl9+8Jl9ou
T'&23V6&juENpc8iiGE7nk)!\,YDb36S+bVRV6LXHP-X.oDKq"elhk#u$oiA<QIXK54d@E\C
\=+P0l'=B&*>tTP+ML#dIh3TSn!BuG$QhA=j4u%]A;[eO,$V>amVarT`TLEKKR&tLK;Y8m-M^
u[Pd![Z'Z,@.+5`XD0Oq4:SbFAE[;HWFXiL+T\jL!]A%DD!2@5(\#;0)I?hi6tp(?f?hsl1Wr
nph+MAni9]A'E7%5O",/GRpgfE3@/>V$X1\`NqTnSE$+c,98902Wp[80Q0jUBomb)SMoP8WYY
-74GW%P9QFZ]A2I*p'DCAo.^XHGhlo`K&M+Do,$=Y7"Q%<V-Blo06:t(f9ePRk/8==/2M#<f)
2OZ_IRIMKMs,E2BanE<Qsb<GJ,ZL!_3qFZkKD#fBQ<f2gH,54NBIKqN)[>=lkUh!h=esI).c
tgb<PUih8dC^nZ6Lge&VImc=LKZ>mGJ]ApbZRY#/8+4A+5'0;Fs"06bHkM-#hFVuhArC[YbdA
fhh@Lr^clF<8.!6p`n/lg'c*/m<T/&F/;3BfCk!ln<[R*#,F'g0cL&f4*udJT/F8EDYJj$=p
J4_i\1I%:D^R<X$'P)ju9c^;n;>]A((XVmdQTF%U0DB7u*04GkpE:Y+'jYf+<IX19iJo16a%S
GbkXN%o`Y6s7ddXAUP\t!-d&l8u#m>guMRE_b?+E0E,Nc3'tR=G6^Y,r16?I]AE-;1[m_9",9
C&+S1SP1R__:\'eBoJBAjHp[Uh-]APg^HX;282`CHE:.OR0=`XXVko1?td*D_+:kf`sh`=Gf1
<FsOeoa3D]AdYLg:U/'cX?-L4S1jrFh0[e(tK9WL=W:U.;D$,%D<NH7]AT;jsh"r(?GJR@'@>n
I8><j#jB@f;U5"(13I&.E6):idRI\>uVnFC<Z)ST@":4BV4sk^W""lI9"i-2^iEa*8.&]A]AM/
Y=8(*!.864P>eQ4.a!tI[I9JD9fG6i&pD"Yrc&X"mr]AY?>.9?Jap3ZmEPe#)P5S-M,e3nS*B
aC8g9mtGkY#^_EQlt!_YIe";n=(`qS^:@Mu"hbQ8?*9gaE]A;k$b\i0YLNTKtZqqGY*!$4K5*
Z0:*df,g(4=)ISr:UbHXAIfK:PG-DU.TTo2t$[o(J1r)ceGW;7"B]AgOpC$P4.D?Rc%^09VR;
<=a:c;aT@`U8<OkP\#18\hth!-7;9Y;rT^H\`o*eMXKZ\-L%*^'_1\pZ_AnABW'ots#e=oAR
4QAVT!S^tTa!j0-?_7G%EQ)0pY=OEafIPlcJ<3BGK?'[+k^MeB,OE]A$^EAc:+tH1!2$">ODB
8qi697@6CXq,)D\h7ZJ;lgjIC2F\3^+pj\S-odgpDtOr@`TE(d?u:+Jm2Q&pabZLK*>IR9><
J<SA;ohG7ah@P4FhTtX<43'd*C8TBJd@sXQ\j-c`LW[Kf/'/0ZX27Ofqi5rbiH!Q2&'6ms;W
:u1?(A'OiHmtYCk1BR6<D:pWS'&'9\,+khAtq@[G]Ai9$W4[//Cet%GNMG^f%^j3@G=kP9D-`
TF=J#&4!K_.`g>-U/D;aFldQbLiu@Ws7rI0#K2i-p8%MX1/.-\9+^T<$"u+$\O*:Lu<O/3n>
?>+(kSZY\K@h2N"keP#_+ErT<J0]A10%!j8c$YJ=R%D$s6r<\7fU`&46C-/XhkuF!-RRslQ4^
65PMeL>&sl:]AW't(VAPQAFWTX9;Pc'F@]Abq<Z.H\O5SnDO'kr8=W9o/SUJpRfNkrO6m?@D1=
jie;q]AGhk[(I4[@a@siVej;4Gd+4k@@UIAV\(-MlVZGbtiG;'ISD^dHY[?aV1SEoX?#iimlt
,<PC0^P@NJ-r-TC7%EA@I]AS*B#lu542g!-Zr@J]Aus[n<N$@=92DI!N9(\_&J^$7^%qYs!J33
MI<roO67+K@Frt>0nsS4Vh&u$!f*'9gjU:,XC6ra^E2R'YAG8WQ^]A.teOPd9G[&qm_$fI04r
F..AheI?MCQb8#03@%:Aj?l!egf4PFf.27LDj/\@""VP#r+CI=e=JcjBR5a==tf)0HW=DNm[
CCb2Q\"06oZ)PH8r9j4i#p/$nSf:K$>+4$.UK]AQUb%L%bofYGWdN<)IHO>CZKCb)2h*e^hb_
mONMKc!.KX*FI1#-rI<!fl0SY@Is`97=^@!]AC1I(8r-c!o`G^-2H-0&Ik+p8C$PID[#ZPWaT
gOnNRj`6eE@(M_&(OV>&-"Fet4ce"mZO`g)@9hfuY%lFG<[j7%I+"(m9mOBtQbe^5'T;_+i'
J$5#1T\m3&*EiVusG:TRr"LK9!$ZoHkBrklb3f"ej"hSkGT.uF"AnG@0pV&bO\4%-T-J1!g=
T7S.`[)"a6f^9fDn)cp'*9-<6A'dXW[(]Ap=O"n:cCac(.4Dmq4TkpLBI^$Fn'j&Qg;iJAjMO
Gb1^Snol6KmWo.M?AX&2_%X)]Ap\;bXq2.)K_O!,kLD<BmgBR+$M9+=$um)V=Q_,k<!ojA\[[
1^]A_Tr+%]A"$0BfLoq2QO5]AlZOJS^n%`@ZABYhio1$59\@gosC!;&QK7!T&JE-,=M/>bo5WIi
n'2IHD>jp;k0Mk#Y(Fks(%Imb8t76LD''`Aog'5+:6/l<.X64cXOLb_g4H10<ikQgBZ]A[B&(
K`a:rGXlBr;Z.RCXHtQ>)eVZ.?;@5`P(^.I!A;:^dgd*eO%CFF%b[RrNK(*<>GBi5MLuI"N"
!ZD)f(B'7DJ^1]A\b]A22Tg:&;3Joe]AhL2P*EB8V^!3C^tMdMK+Uf4k91:oQ#*HjlO3(Hce,WA
$17WQF#LmLCU-7*]AK\d5^^\I)hGlRG'C7%/BZYNaNnSonfhR_[US=#89WW_:gY1_.VOl?sUn
f1Qe<4GtOTf/W=,@EY=:jfW,@[<(H]A<s*P%&1>o]A)E^#?O?+a[Frc28VEbMh2#+*7/;LX`'.
sI]A$BjWHVnK]A5Ag*;Ui&a?#_KlUC.8O@O?L8c)Eh^k$^Ff-ZNI!V>YV$n%%6STp*DACUk&Y\
7rMg(bPV&iDLh59sXh(O3Wh$0gY3He[V$:kS!K>9IZb[*^E*e1'<hMN[?<h"W5g%X^ops23o
(0"b-P:L\A+t6)(qY(;BJjC`4+49>NlKO^/V<QPPX[/dVufp[-5#S5XBr?61`nZ+#t"LiU43
pk2>M3H(FC/1SP5WO^+KVjU3OfG:WJ7:WTYA]AOG[K?'4@irG09rGq&s<KiSLI)#W3(!Dr;+$
dZ(chnKA<,bp'oF/okDb;nSPaikP:V3P-->=(=WurDJrIY0G@eETPIgZ1F+"]AZ?P)F<FBo\(
ZEcgInaL>c6BoC-WnIRa!B>Ipf=7+U(aZA+p#>7:c"#!7/.R;M^G_DTNrh`@p'UYSnC0js>=
*/Y@86]AFX9LDL[/@oXG*r3;tTjSq6kL2suE4AAN7_?.Q#DC[V7[L^E&udL\`=dUU&"$X'[LV
%i9k_^g=0:a<O,E?OD"Q?aM1/V@u)ao7e#jSWN[",e_5_e?EUK[lr0?C4K)B&*u)S#7eJ;iB
^oc\!?]AQQT"HEBU3BOe.`S_4!_LK,CXZ'ZK]A/haP*ndTjo41)61$/ll[bEkR-Pa(;._i.4B#
F'N7%<)E**V'2jTBF&*82O#JH^t%n3c";K8I*cceYALT;!B*^!*3XVq^o]APoH?(t*QnD*ARJ
b[4JDiTRL!2Aqm)0a-2n3Bqep^_H<7ZO*m`OS9+Bdk0b\&a)V/0o;EM60JNRQ<)fM\G7-(@f
P/dAe"nL4p,ALi%QRZ*:mkT.\V]AbVjEVa5RE*,aBjAQ*8;DgeBL':t?KjXSAeP=[uXJoB\D^
'#OB!ipX@,q7REI'^PmI86NnoP;(#,n8JWl)ZOIqRC`Rb>0(aFESM.jG[>W74Se5"t>K5,iA
iZlE59]A0"6d5Q2#"6a%.9"RgIBii>H,ESWY:1Oqe+L"d8QNKa04iD1^/aM3=9c'CZ?V!)2:i
MpDoAoe"In<pA!:Jd;tH0"okFXB-<r4^j6-U=sa1maR/,jrS)IZa5#jZ(QJf;&N1)>a<%2k0
-;<g*?ZqMO)eee:B]A#(b'FQL:*$9<ZA3T]Aq;^&Wu+1o:O!/-M_;'\/]AhgNUP`quC;m[6VH3%
RjQN@b'+_B>iMoPjHr@In";BK`aBBW-@"Ls-k&V"I2B"K\I%9LlQD+HBejQ=5A>S7R]A#l9@m
dss)"i6iIE`gQa\/6m%U1/_Aba`u8'otK8,uQ1/Qu*@Y;sE1[+tW"YI'YM.o1c4\'JI]A!X"!
2(@=/.sYKO&^4I,1;[*"mUA&$D+5@o/XpXkR8>F)j#jA%,kd=.R@OS5r_36gE^m5:&Y3_8Y_
KJRr,VgZA"qJZC'ij09Q-nQ*LSPm,\C)."^h%O3OWh-h3ZkS8;FbMH&A[NjZ>*G6GX<gV<*M
P+han<Gh:Gjrt<cJ-%ST?ubHoO,Ig<$RM*5GbQK6!G^Z_?N?kRB11$uKdnN&nTf2-T=<))+>
`@,)W64!B%4ORbg2jilY>JUkiha4AR^;2lq<@t7jcFu;n%cpq[sKbNL@VfpD'6O.]AqBW7JE;
hRam-'mVT<*da+r096ul<lBgBZDLP@03<N.FT;O2Y.@2K'Tr(Nf+346KE<^+SF$$A+9*o))%
cM+Am5bIqesnN3s@Z:a%4[7aVlQ@EN<YV"Ucj-_pI$lsuaRMi7KA]A9Asg]A4^+5Q7*L#ei74^
Vrr3uJT*ajHjRJMTh-Jn[AG6$fqfbhK(tcR+BV`fRk.nD@Muf3pC\JS1eof2Rbr0falaEJWR
"p)Oc&D4Tq0)$RT'H^nJU3DL*rK>e$R4*36u%W9?c)JD2l`.([h1(:or-Bqs=d;P@)u5pD9D
k^"%:N,&q+).@B)?E0Z/t$AT^G=[`#L[>P&DcAg1dBnB"0m^3Y7U(8HB7=>3l>YJoHVY`[Y&
W<qO,\njQetU]APEq?85$J*P(^]As"XPLt?83C^=SV$H16qV&7@N7dXm^\1BEi%6"IfrcFgCKc
*`)o\qI*;'Y-#%]A;)oE'']A<[H$&.8as=Wh,U(b&Zgmb`W%@2K(b!LqO@G*+4UH"4SpM.N4V?
DL&.[M]Aol'&-NVYF=9Pa37?@a;6:u)Q$p'FIGi(mN49KYqs(.-@U6pDQN]AA95Onu052[D=2L
+o?/kIO+3]A8%m0Nc%=c/g9h.:2=Wp+9S(LON4R,En)&?eLcbgBVoVk8"m"!u&m]A(sosH0ro]A
m&X$JnZV.nC+P*7-SR-*?:51mmJ-eXkOsU\.$qON:5EN;.jBeI1,Mg<F:I4W\-u6f[CIl9&H
BRkt//&[-1iO%$*FPup]AgrS"/"7GJ"!U$EEM1e'$i8?\3`$:u\J1j]A+#9;_M;KH==egllpR5
iuCQM7>FI!"$"!lU8*e4sK5<dg>"/a1:*oeh6A0B)8i!L-7rBn\AQ5s7e[HqB&?JiLuVA-JC
BgfG5<6atADj]AL\m!7/]AH[BD2W`r9E\^nC&?>BonLf:'IcAq\oG;^9J+nR=\L86R3k-R@Nr(
<0E'SLs8-4]AOBaRE,!h04rs\0Teqeh7G(<)Q<2D*N59cZ#&Ld0d-d*nDOe.bcKsCoC4j@_;<
@L\\Gm=!6eZ*T(o*BXr<!TsnVVC;4pHi&$27rRBVs7En"Eh(8sOjn@RAi5A=-&toOE;!J*4-
NX*IXo#M)d+ILC8mA45f2A'E<[5`aZgTC&.PEDXg?"$'!3GDFOcj?(ag0Po]AHma2T;Q>kI&L
uje:0eG#r";9Di7dR>+M14-]AAT1FE"qHGFQQTSmAge#JYV8.iIMCe#&^kk3QJ[&S+HtPiPF6
J\n;rf]AS.W=*?'RlJ7fqeO*mH"]AV.nYY<?H=UdlkkX%ok!n0id7B`OmLO/#b7hD_]AN</&QY0
SVfgT5\Rqse!JpOsLNW0BaUTi8K(3`G:F'+]AsI6kN1ao;A69fJ8,'C"/klJ0_<"W*+$9^6A\
M]AcZ4'Em-H6/CAE$!90:0d%"/QJb3+@e".$eb%r9<*JlR7'LEeq;aE+&fYBC&+!Z)?R1'4=<
CP3PE(0Q!'u=1^1>3f9Q=!l9^KPW'WMEGAI[7mZ,+Q,%C)JIYA'5G=Dl]Ag@k%e6oQKisGs1F
.=2A%Z?R`j4cDK!,N/WFJem0d5,Ym(kf?i.Bu6UrXq"p%X;?YRC?C1;d$2@/%s+Mp=B,k<r%
\`6,W6@T<!HbY9aRDAE09d&XZM*ojUO<iOqWlnoGN</1!>]AQimq#QeH-5;eQ\kI/BDjt>l-L
UVM/0_+B[(P+aI"02emL=5K@E=jqWsr]A9%L2Z5T"6H8;Ac+kr<jQA]A$X,\DK+i,MP?:HI^2o
&3ThkI@W(l/A0K13h)7>i=%)YK.SXeM3nhTm3ncu#Q#J":OCbP;0DK%D,C+drCm.2Y'0jB<Y
li\.?QJ^<]AuEPlm08NP-g8gNicpMp2m$&,/Z^h/H:1+\cd8D0!U'tT[]AeFB?Ia2+0j-;I*Ci
.6&hLCfgMa+[5-AlO?O>nB)s<_IOA4g5Td(Q^FQTr[(e-)U2R4BI0mp^:3LYG]AEV\OYHBuDk
jeG-CQ]A0*^@#lMqcbsgM]ASmPufaW]A1N3#tD<,n;7O^c&g74i<!NC^FJOsI%#Kk7RN+$osn^&
r^IUFRpnp(M`$5Hcr4m*ZHIAJp0sZ)E'SQXf#s`bUpfbEr)*<rROsk?N`O[Z[HF?Q6Jkkbkf
N2<aGnA2Rmr[_o>@Kncei>q0%)W?+<@aRr-@FD>)!P:J3'?[568H<RLl"^[OkOb(m`/lerJ1
$mXj2tp^blRk1$k#hpFDH*"/liiQpf.-&&+lWS/,f,2_rPLG,C<';dJ(B0AUUm5UW19%e"NP
><r_'Wo6t!oc<`r'qN,i-f5J2fjmU$@3;C+mN'CP]AJWe%4iK1kpN.)"&b<Ln@*rNOS/e3m"m
-Brn\*X24hMT@8I;5V\SYM=5BX7#??3@&r&XPNdTg5'OQTLiKq*M$Yk]ABN39X![1::rgYgT_
8:p>O,#ufoQ7LJmk9=(Cq348Q%\1l9QXt>gFItQ"`*04[3\?r'OKBM$[eI`/p.>P!fV6Rp.5
8P!T%r]A9lJ%bIAu/8hc9M9g6n^I7q,5VUD:mG<,(pJpR?^@UtVe2ump#j:^3?FX!(*mZJ+`D
)tf,F.8Pem(X%JM>%h3GWglh4>:39YW.G9:7=&)ifs=3fE(N'NU`2+_k2hhOB-!Q<='mbQ>l
^K)eSPuFSK#+P./@H,9Pm]A]AV/'B):u'g3Ll[d]A0B!E3#KNW?k$Bq_s83KB.!pWI)]A11]AhMLq
SG6EI2(&Zf%,U#6nu(^8US[J!-Z"jb3#(Mf`OA!ngk,0d:Q6pi]A.18$N3e6,HM`-N*k'hPoW
5\$gk"n8>2QPlU^VTkH31PJQD@ZA4h6]A21H5f#_f:1EG]A&SM8\kbYoo9UUKiS$Ck;e1jVstG
@8YrO:e:'pg")Ku(</OX^C!f>VDUATO9aJqB3_g#pjjXM.)"3BfVP[c_Bis=*VGm:f+`Dj=Y
ZtT>q%kNum4!nMD\@"N&1PNN;Yi33s%&&p6<?Gf_ArLaJ.O6A%(rE6;mAgd3SaDibPdT7/ju
EXp8?=8]AuIs`$gb>kRaY&(r2uAtqn0(YgLtud:oTop"KoD2`qVnG&89^4't\ljfV:RZMeX?4
]A29n(*Y2l!)3%L)>goH3MP!5R15*s!E'7,qdXc*Z&fd;ue"p0i0kZ*#<n:In%i@9]AS1g0U?T
g!eGue,eC_-3*_a^</g5uI?:Ts\Kc8.<i:EqtlRBFeK;QfY_Er5'K>0mrDT@9;r&6N4+U$FX
$Vt\/4_7j:mZ/I*@QW)/^Oj6_`=/Qq<aGp&C,++;Mg99e:9legm=;9\k'N)Y86>XF]A/P\GOl
=aj9!EagJ!E79(M>!o`BCE8YF!OY(b@;Cb.V9t_3mt5H.8$n9GoYN4fu_T1?lI/sZVqqZpj=
^C(+8u)q\$NPMHRA#OI.`?0:-H)Q0q7Xf$ICu7/;reim#)4QqkT=)YSdU+t[h(/ZMD2;p+df
qWmZ1h-TG/>9nP8<Oa5k,ck4HJsL;kbVPSk#LJ#D9!$$CT,cEf:IS!eaX'_OnHae^0:ZSe`W
=ZE;Nr!]A_t(T"<q$P$o;<CaHUfE-<XVtan$\fa5Ec7+VZp&+@ik?[oi*Pbcl,XB;I76m:-1g
"fLgVh[dJlel4J-O(*N/W&1<B=67kON0V:tT&>66L`cpBe]ApVcIF?S]Ani8roZ;pp*0OdcurR
9:L4J`kB7(Y?DDH[>C7Ru"gQ/9@o+FY3<saOq_Q/B$r$XtpS^+!1lID!3^ho7!,h+:A5mbft
koB*L'jQE=I$TTl?->^LBP_X[>0e%HO[\L[<8bRJgrFI/-g3MnBtB<6naB=UJdZK9j[-A)=H
7o8jUH6S$?<4(Msc=_CGjg3]A9NBLE^'7Kei]Ag0dBNL!H`.05LTgAdPS#iW]Akk-l9rT[gLM0Z
Nr3VA!lFKjnZ!^Z3Zb]A<b4PHBb.mS!Wt<j9j`AU.#j4\m';M,`RUp:XsQ.9ebQJD[,\kX/ma
/>%_mOoq%\q+!AEsmk(@BR$p"ompBk)[/<7Bm"HNG9a;/r1gtoG@5+Wp6F'D+V4Jd/VM2m1s
3_+Bl38D]A[=uGh*n(BQ^D8NdFO`iJ/"&q:Hr^s/iH)?:a<=,$-(k-F`KfP>1/@&0F-$ja$.$
MJjFF3Q#gq@&:C()F@Q]A8g)+$/2il&G9cR0i_B_JLh)<JT;)$8AP.Ia<i``kCZ7HS\'pfmX.
!IM"$2R-`tCY!J4_HcH`\mS_LnS`T2Z8$ODS+3\6W9Hmrhn.N;M$'Nh0.X&#*dcg95B(q@\k
P*P]A&IT[KqG>^_?]AIqi&_*ERNAeZW)pI%d;Z!@Crf)X$Fo/[BOReDG5MM'o*$-c&65c$Q&^f
<2X;h3_CV87p:k07K(\<Cr7K('%1<["E3^c&38R;dXHMl#PE((\&hrr*\O&-[p0&\iL%B(7n
6rNK?gAFGJj7#mVXl,Gic#CHf(t"6FR9gpCTs^/\C5#9;c5dHIYO8c_1QTDaWM0_h>4E.DP!
p?1lgYUb;kL(2ImC]Ad)lZm,$T.f<OR]A(#YArjeD&$fA]A)EYWV%\Ddm7_fK&]As*fB?sf;aO+i
lp<4k*`Z"Aa3:1l.?<*S5V@5/LmRO'5T/ErBed+NQ]AfAq\8=-fGDP!&-tmGOg6j_IQ)<[:lH
/dh`G^#fgk?o-^TG4be^`hI9mT2tb,pjc2pOtbRBun<;#qdNp_8"?JikGb1aPEWHL.o.7'MC
c:5:4r5.5A-p?J;3\Y\-^ZDV%pin^C'QR&uoE3%;Gj).0HF,!&YMV$Uar4@ZV53&K!a,M'!j
XP9SSb^nQr\8L?>PmW)O(3?\H%M6]A(U2UOWtd.PLr=uV\MV^<4o(5VgpsT/H`_q#eMlb&P@k
)a8R:fI[>\'W,d*`"QTl$+Vbn?5B<qidh:u?)dOC2q7@J?=../@0,(-Yp$g<k=:ZjS*;/>rh
fdmDmE/;0mn1a`DLnSS67!,sfEl*%&go<HtKY@KKlI0k-ki\BBnJF2o#5VcF\1!`Y=8(h6;n
[<]A$G\"0<o[_Ue3G6)L*Y1pg.YqM15:(_!o<bWa1@HK^u]Ar!ORH$%L^a]A.E3m[Ip"/KG#Bkk
5@)oBJGbHgJEZ.7d4SB_/b/[`Io-ZfCnk9$MFZtG+nAlf9mfP<$T`Q/UE2_.GV>9ZNWApDY-
Jr]AYf&Vo^CgDd9Q#O<tB^7=G,MF3M5@kC%8SVJq]ACl_X:Y'I/`Na_EZ=CNFUJ=frS1,SO!.:
mNUtdH?\Wg]A9m;AkqMT7C!KK.BYAAs:c(Z=l5qjQso12&;%%/BN)HJRZ%R5NQ18(XHVbR;Z_
<s*DM"'C,O71$c(,<les_+E4#mu2@1ac<YMXS5D=qVT#SW2^6"b9Dm_%Ta-4\0dMm!,k+OR-
F/l_PIHdMT:eI2]AqCWS`0358&&4kkJPt_Tr+KbP/>.16YE?\=]AC5LrJ\?PcbJAj*aM-"?M4n
JeEoeSFK+9N?Fb_*2_=pNEA^K&FaH`>%<H+kVt;u!?]ArjYk24.hHCGYNE,J.HAZf@PAo,-2U
W\/ZGcGds%?l,fZH=[j"ar-RR<AWibjJsJXQ6.&RTI@7AtGa>Vr`IDG-O9J2sL:2qt$SGS6J
H\o,_gVlI$lWAZ(run%uZnNY!hoCh*ED;+m\CU%]A'f<*`I,?5RWtcfjS>R]AsOsk^e*[6ZV><
qUmW>fCfW,"DK:10#0Gcc)pZF,PJ%]A1d)9t+"e&ZQkdU1R1F^8(@up,$8AM)>1$YUg3[2-^L
duI2Bu2q;Yb7n(#l$[8+`G`n;'c`gDV9H_R;as@PBIWD=on^jH]AmVn>\MH;NH35:NPht3]A$3
n53JoXEYDX3Q3Ecm[6!8EJH]Al&Z]AU`rD!=r)ru!ECe9[GnkF_5Dm/dfV\i"kgG!^Uaa%r!qi
"0S,&#op0=cmJ),@-^n5Bgb=dDol^3eEY"p:&(&V]AO?tXB8gmmlN_(5"*kh>Xnu,_pP:jR1,
iS([h>Dr5AbRs0Wq:$i\@1%NCqPEsA)S\JuMH/Q9XE<(%>=77=)>I9n*K]A)D-JU3kT[]AaF`M
<aUa4h/$I=g;#1Fr>Pq?d(8XS^E_#oY%X2WCV6*p.b<>je'7A1)@*>A_"$b1rbV"/BnCK`EJ
Ogt8uaN\!k"n&"h/nCP7QK*U:b:_neO1i[%6#u`]A6s>Xle?/H@"k<VB<\[mZM9"2!'I3LKd+
UZ*q#R-"k9a*'b5kW#LX`rNb'^20n6%jggspi>?&L>ZKlKqsLZhD0Ya=2>b5UN3hV/N7MpqH
;@[UqB".jW?O[-5B3iMJ%O*o6-;mAMWp'HEbPS4gcjmR2UHf\YWq=3SH;^UpU6l"T%o9k(+J
+o1TFhn1<InK"j.P6Loq%V=a_=Me7!Mu(IVGSCd+V53-O$tp\^;Oib1#-mji=,<B_r:%A1jb
Il`N"qk6DITsN?QEV#%uGu3)BCSp(:C6'I%3a8;IPk#s"e-B=*E%8!AY]AGi0iHZ.-XMo(nU]A
iui*8m!^&NJ!=jiiQR(c4M:!MW_=HZ0VL0_JKji[YFO:l/.!*kC%jN<03\6+<s9[Vd[@9%"G
dhrDnF_&^l]ACX"$+DjRY4!'Z>r#O3-rGuX,PKf\Tr1mPc?IVlM3]AMG]Aor]Au'/CL+X:F,))dM
IU49G&KHcD7,!+k3I^f;\`7Cn@4\a;B=-Z2!=]A"BO!k3atI#/=u;rpYaVc:7(&UWS%^W(j]A2
#4i#)13#AmatKIFn6d@g'2BXf2GO\5A%on]A(r!oB0+$S-G4nIBiQ[[0H*+-.CFUOXJ2>&b-s
3;[><.E-`j!tp8=]AqA%GN>&g;IUFg-'b)s7YCXo5%)U+5-C^.d*E2%PVU/t+.`_ZJ)iSQX4,
AD(B?P+2;_Tr[r^hL4.$rJLF.h8pc0EbP'rIb[`[469%`o8H!FVpN+%+OXqjb1[q5R2f`N-(
HkFMj'?f_")`>]A;17jY+#UnkPn'fERH4#M0RX#\$q^CQIOA)PV%A#uUD.p'>5qEn0Cn9nX1E
`,`jX.lF-l-(jIRPRrlMP,F4f"+.>M-V='gV;h!g76o^NXkt)Xj1cg&\jp%_;<Uu&(?.<B:3
up:.es5Z3I$5&;lLPLgno4Z19\5g\pPd\BT@:OWCeL2C-f,fGn'uXJ=l]A5XJtI$0/Iuo@OZP
HjWKjAH3M29q=pjOctrs*7l@S454FR')P<B('GckL:%MqIZ^@N_B1k>k*MP_P89ClBcjbtm0
qA[]AQtsu['pc]A`'!_Re(&acJ_.9*4'#7qo\s0&28Vn1/>kGsp,4%4/mh;PXfq\CiDsa\i*-!
&8NbO:DhA^"271cWk(^9L7J62P<YZ&K*`_TdpEdCiXRPWT[$tFT@e!tQ9$J'=DQFb6k'm'dM
5]AVHQWkT["MSu\O#eJI-!=aMg$832g$haX#8#OQc_bE&G,5\[,oCcjJ[Taj*,Y-^$QW*2eSp
X(,7&[ETT?Q#Dl#'N[?+V/=KIqM4>o.Ya>=P1Q6i=S6%0=[!<f1EqN]ALZY'jaG[\`JsNM1Og
P2BJK1QnB:p_GC<6JRN7T9Qj"l1lUY@Rc59WbC<)iH57MOi;XQm/Jc"eF_#^)[kEV^JGRKA<
IB(>$?1X8EtFV?p8)96).XUjcQ"^HnjKJ:RbYjkI[Xk$q2q@VV^*01gfkQn8uUbE)i6<4Vn4
g?u#:BohE(j$+;KS8]A<kTZ/)@84j[<tDs!_,W8uEiW)Pr%0qSkS0FY_88o0AE,KX_Cq9tC75
h$K>#*WFG\?<,?&sD/eNDd)86f7aV:l)NX1T>[Q'Cs=,Z&R^_jXle+rBr@78@Oc.>]A7?R:Oh
>PUPN<,LmFCGF5GTtqWup/f6=bW9$LhF/P^FoOQmUBF:K8=rh0;&pIA/t1.X]Ah1J)FiP"C,%
`2KrSWMfo(=&rR$Eq;1A2lYFK-a-UV\fYG56$-n'O8F8C8g7rn%)l-XQSa0e9Oj!Yo5L6^P0
d)`e^bXL^g)smINuaeBgt2kmhfuM>p9W94dF9ElTn0)gV6Z2&(dljk#'LqNTJePSakb+qCjc
4>7J<W\\]AdC$+l>*17c:P)cuGU^8==$/EbM74!n%m,N9q@).AiM:0!&?)*^SQBgriOH$R/QZ
n<c(3W%#p#?JX*G>7/h_6:RkKdm;6HrW?B/^.jCDq^i]AmJ,7]As$&)a@p_3%^H-'Fr6%%#L"u
s@-Mb6t9bAdZ?E%DIDH5Nifhl\:2eVTe`#d9g5^c"]AWl^5#(iFVF,0B1fME%Or5e]ABhpmQYc
kd:pT+11HVHo++`"9_IZ#!9n?.W&.%,GjgjDJ"6q60hM4r@q>;\&fo]A]APkoEhf/a[<b5"+gk
L@1dE_l+%k"`Ob>$('RqJViEtU4)&RV:*F)+Q5o+m-5G%/0KK"/4H(guS1!ne:.3^U`LejZp
>Wke2aQ,Yi8U+\<W-#Illa11rTXN%Q);3g^<hE]Atokq=[n[P=^C@g-kn<7gD%ITT6ac!:7/o
B:^dW4/1M-A,,.#O[ZCY</j[9<i)WA"6IJcYki@;e^<8jCGV[1D7K;]AGo4@A9mafLhH$uhbk
HI.54Xtb:9#Fr<d(80mYXW8%k2_Q2k0:4unJBZj.JX:Bt.iTW=@%D;?bh8*iH_5S@"2]A]A:o2
#',\5N@"XPcc#a5e&jr'-'m\ImUQ0fr0k4K89t\C[;P(XOuf:'5bP$iB2!3"KK/-g6,c:A%o
8)DSRG6u:i0JE$A7]Abi3pc=c?rUGk6N8*d7:5U2U6Z6TB9Jd]A"e.3_.>Z1UD^l))15jc8#Do
nD*"k_DM4#Gi$cJsX^gC!Mctt#8pM,C]AjJtSdR&5k!fa50X8n3>.X%gR-*-G6Ft.$u)+B;&h
tl(L[Q`bX'?B,qo?GO7(_n>@f]A^%9`:_MCUAB$d-Lq<T5FPb5J0L*c(%.s<2<,9*e4s]A]Aq)u
bNrJg`7I;pGB-+iicfHMn^j,f;!jCKGJ+O2b:I-YTE0!Qrp0?Eb.K;XdfgNCT7dCh*CW/RCa
>CZ?<s5pc=NIsV/7na^"mi(#ZhJ[L_cc)8ka!7"[]A^\O.iRTd%e7Xh$P[n":f"BF^kE[s.$Q
s!d%`R"9\Qd++.J3l(U5muTY#NO8B$OGdE2/QG"t9XHSFG$\,OlR1^0=X(O('cGn78mn)#]A*
TO"W^CpZf9gnoLh.gNgqhNPp=BC3c6-[^p:sHbTI:35Oa@6/!L'?Ot&\2YoS%9_hUkMo+MO<
r;mpZ]A8cX+>DP7X^B`1>AI\_-/>kN(0G39ED/uC>**^9\-_jsRLH%!GC"m:rf8%hg3ON4o))
X,(]A7^g#A53NEWg8M\&b>i`&.#6It7+HY2r)&@h1bbqG%9N$]AYdp!F&<g^>##29uLo.:4i2P
Y()j;kFA<NY91"ngc0`t^%l=]A^p7IFC@q>pB(jfFfb4<`F_<5;3b/2<8d\E%\"XX<-"WW/&L
Mc2GLNiC/J-#pG]A]A19nDAK`R4"[,NUL52km2qGD.m>;B5[6FkAr-N_s:UtlTF\JbCACS$t2@
"2p'B8!?29?6P*h,-/$4$.e$/OL%nmKE%=1]Ak!*C`8Qn;!hLS5(KPe7!IE-.Cp*IPm0dd&&*
ULktJ&(0ZF1tX$SC$$Wo7-Xmc+^[CjG@CYs%&(FnKT%4Gi5"NAnhN>cdlsFLVL1&rpAbu.2s
'!rqbLq&,lj=Zp&(6X*0FmlON^%nhE"M/AZ_e"kgpn\$Z0dP:\/1"$;LS5.i02_>D3d8`&s"
(G8r0Ro#(HJKqT:p-2e.-1W:tFKeP_b3=''LAQNu:_IM'T=bX1`JZ1caX5d!q?X:D2cGa3m;
g-t+2QnCGfI6.-@WBdN0Cg2gHuEX=^eSu^I8)"3t\?"Jq#S#mi'b'Yk`IhICCDh=g8G9OuNM
=J+dfDM2N=nn9^%kFXEX&mE?;q.WdWBWBB#nn!"L5^5ZUO:nYL)2^R%8hJTiu\n1k/&CM![&
Qb'qd9r,5g^XZ;.>>Y?8@/18)-B3J.H0J4!GBa*Z&c[k_C^"WC3qU2B@:49H18R9r]ASgZC@q
LCk@8@4Q?jVP`sfTbjkHr6Eqgj`bYU5UZ;6f8^$R%Rbgj!%=K]A@8M!i9R3pADab`?/+a[k_B
Y.CW;L=/S/0W?75pY_M[rdgiS?0IBG#B:*fBt#(urK#mHZMi,lnGb(-q?ZokrM?:9]AT!S!)a
EHoEF.C8pKa(R9DWQ1+Zhnf@=3J#^#k1)&:e?@G0Y@X&*h7;o-97]Ao0*e^-%b9TjCMuA-13X
+>O,7tF<R?:[uKhd3K9G<M!_it'h*j4654l"0lY2ggd;*k`86\h$dQd<-%*YUr'/a3Y58!,>
<PPYpQ9DcktIV58QPVBd^pet=1]A[Pqd8i`<FZYWriB$)VtY,-WuEUkS=Yj^O8j^Gr;SF;>Om
#N;]Af'c7lK7'&p-.e+#+M"P[gZ/Q,2in1Q<0Xf)Dj!DAtcDK0BhD(Gm@n4pR(W*UP0nl&SNT
4$i%jgL\Bfl:Q<tg4+nUH_"b@\G18B#@rQ1,&_D-s-Hf$NW;_IVEFGC?ILi##o<sGkg0e5:B
c3C8Ad;rQ74VHgHYK%$R(YU.l/:;1D6k8,.EUFrt2ej9\dCb=']A:-duXO@2m42-5Gj%cDOrp
>&jY2uJH&.q\Zm,/4+#aL[@&I2dFgF-F-Pa(jr:?LE3K;PRA;ZU"TN0R$=_aLgL-cCR/W5BD
Bj':s66:GCJ;)Ui[#=__FL-A0U>e4m<!=@,NZNZP**Q6^@$3fECV,J09Lba5->C+\\AC=pcc
Hl[-_/o]A.XlP2P'l/\=X3:kN:nFEa7f`aaidVii'G"(!)8%N0@71'b[tH$,/i#SQae2ltTrO
0`GsFOpZS@4?;RE<!Rh6p7@16"G$^u;n_52;Lm@h:_Uub+t9K.I0@Lj\,W#(58,tg)>rKBjH
H+o]A"I/ikZiOb(hr`BF",;MYFenYX'k$eDht:&WN!4P/)Gm,n9!^6kQ2-oS6'#m/G_&As-+?
\q]AI'q%MQkh_]AP*7ka=^^4l-XZBtr3eTD3iMn.FW.9@0Q`^YV&e=7G=d5ubPE+!?US,L2qML
OX8A:`4;,0mj@!N@P_B:L>]A!OS[W?.\Ds?Fgc^cP/ESl]A8e!'.G3dK]AVX6JO5)d,kp?;U;(1
2)6^G@qm`b>5`"f"h'n=qLe,d`qX]APO2U/A-d;\Pj%g]Ah[RIA]AV^An:T*;4eM+'q&sPp]AqpF
R/`ka:ETB6^>j]A=X@tgpd#BD3cm1Y.h?H7<?\R:bV+B+B[Z-k)PNlr7arsl[5EM5,:/8XSl1
lb(Fu#(2oKEbK#uUJ:Q8`Lgl92jF?qKh?<]A,os*M!`5'=Nq4N33XIE?;<Pi+E`Vch;42/rNE
fn$c^Op@ud]Ag&Ea'5sK#;"Zh[21*4aLqUIij7URuO8KY7_rPeR0G'9ASeQq32s7aIjcEBRqg
Z`KIhYDWQp@]AC![Bji9JNi?dSif`=P/$lZ^4%D=V[?oAK=3LJh[<he8#LC92-kK#lM9:KnPG
-\F"dLYX6k1($(*X<YM82Gne#-8Xop/mgAkgOJH37Yc1V+Hm'[_ofaIa'o0f;PBR<5<Y:?[G
19ehkl2;/k$:F1k*6^URM#Y#<hj3K)QP2Tkg?;c']AM<cL^RY]A+R5i7M2?OO4V[llrF8q"*9]A
@D<LVOR=.d4(CqmMt&?2E7rqnI9.M7K6Lrg9952_"^al;.F%~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="648" y="238" width="302" height="292"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="chart1"/>
<Widget widgetName="chart0"/>
<Widget widgetName="report1"/>
<Widget widgetName="report2"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="950" height="530"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="3970222b-0bb9-406e-acf6-cf6a6bb71cff"/>
</TemplateIdAttMark>
</Form>
