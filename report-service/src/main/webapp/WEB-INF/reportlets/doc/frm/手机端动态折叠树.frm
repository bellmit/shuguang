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
<![CDATA[SELECT * FROM 销量]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="销售员"/>
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
<![CDATA[SELECT 销售员,销售总额 from 销售总额 where 销售员='${销售员}']]></Query>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[3352800,2743200,2743200,2743200,0,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
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
<![CDATA[LEN(E1)=0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前决策报表对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="销售员"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
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
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[LEN(E1)>0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前决策报表对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="销售员"/>
<O>
<![CDATA[]]></O>
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
</HighlightList>
<Expand dir="0"/>
</C>
<C c="4" r="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="销售总额"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[销售员]]></CNAME>
<Compare op="0">
<SimpleDSColumn dsName="ds1" columnName="销售员"/>
</Compare>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[LEN(E1)=0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.RowHeightHighlightAction"/>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品类型"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="A1"/>
</C>
<C c="2" r="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94:%;eI!qUkU@cbdM!n"O^TBcrh(fnUa2m=Bs6NR^R<QG>Y,XSPU&r7N3KW$!YgEe.Ne"hu
u-G=;[ro;5$ejP?;(Jek/9.N=\WQ6$&,\<":06\a;t)\&*EYM:LR?atMn)kBrWjH(X8J^)&t
^o8WCh.?RO1!Wk]AT03e!j5X:tp+Fl6F4H4Sp11&WI&2="Jng04Ofj-Ycq:=+7"1pJ"*'R>Ij
,e0%gcbJ::n#-jfZT7Z(H^jiS%!D,nr*kPQQJ+a_V[JcEO#%6pM44%#7Xc3%#u+(e2-0b:D*
;,Pi-U/8!t5c"fJc6\$a*,(@MQQlD6-!H6.FEc?-qt)#M,C<_a]Agg+Hm\Hf,"mK3$#L*=Y+'
s-LACa,E`uq$PF90+fGr(m@ufO_brB?D8VXc$WoY$tR(EH]A@$J9:amD/#oZZ6$!IR*aWlr9Y
Jn-k3^gE%-Qa=grnd?#e#6+jD?s"8P);5GfPT14Y&a6esRKMWuSTO+,+6(EUINOA`l]AK<7%%
fB^A+$/`CCBn`Ie8q!(9P<-it)'Z3jil*_ssL34/:Y=q*-r.MM?`>U/>Ehn+@g.FK^P//E0#
kg74KGF$9jr_j_Ush$337*pm)N#H%D'!28EZ.h=ce]A`fM0F]Ah2`,n&X1/$'Q1EK)Pfsb$n$Z
Ue&Pqil!M.)b(qk-c#]Aa_Ok#SfLb@3(#U,<!ZD)5tX8\=?D.[]A11]ADbNEg#)Ii',:oVERrn!
#J>-45N$CiGD&QcmYp!UESING/(ZIJ:MC3sFGU@=X7*X(R)O5IFs<MCK3:i8q"Ug=4C5dqia
'3o)QQ[5D'Pn`Xe(ErMT%rYZ=_m?itAUX.R8egIqeaPYGl;qV"n&IKNWW0Kbt@RbcFLTlL/D
KVVQqFDB'akle9\jd3alfECpnW&c_BPa2I6j[)0-;Vu)r)Sk*.u;<gP346[b:O.Bc5'f7RKn
8iG,EoqI;7:qZ*?Fm50oD;$8]Ab5N!or'3(kGXIX6t_Gs#seqd:e@_M6ID/?`*T?Ml<`nioJ)
2npLe64#tm6V4usE7WVB/Q]A:F@V.l/es"nuXoE4ub(clJP/[X9tBdj?QG)4nX.dEL5`W&d1@
naZ)f/RsgUp+eJ\#'RjJfp6.98KWs=8ba9=rb8rOnF1#WpaBuV2YA[93g6(Ro%_-#CVG_0CD
d;Yn*rPfDtR"9@nm-.`dsqnan!1oLNMSu5*Ff%(%Wa?r)La6&OY3`OFZ&d*^C+`H2f>*41M>
L?c"kW;)nunI<;j*T]Age^lW[o$XC1LX&s`?Vq.*IIrOUknc<.'V8Bis?ZE3q[\]AKJ<Rr*Fl=
\55e#/0`go]A%U:a;%a^.QP82oJ3>EMq9e-$"Klo>/M;%o-qosgXK8=oVc]A@8Vn^kW%N=-(TB
A81G<kZ-Ji"<qp@JYOk(Y:>rd(O,39J.D>ue:/s0`kZB$gc:R!@AZ0oRIGWBLkFj)a>IKUAG
'\-Xlik%`YQfAWj&'(?rjM@:ub4MC'fXs=83GI0?030BSTL_'lP*shnR8GLF.68I,e6j,i+)
`CE;*;PNdSg?EWhOC(hK'6%f?DLJ3G"m4nqCH"e!450Xt=J9/5cmcrc&NAYGSh08Wh*Mc/tJ
;.j1RdK:Bs:(&''Q.50$oGfi:?lfCnaX;jV6V:QlEJkf*T;r_.cRnCiUU*/6MOsqeQJg`%."
-,B`kCo$P/Fb0AB:]An\IHh#5_<s_&(R60V/RYR1=+$p7F.@@]A'ZPD*!j!ONE)C'<1k6ND_l*
Qc?ieM/'jaS]A!<UDI[dfC\i&@C<-2KZ<XQ>/Z?4YFgDq\(^-icB"D=DA.+,/]A+m[[TM!Mh##
O3L9H9;q@79mj>bM:RDKGX#)L2ppO^qr?cGW"9%iLBh(N)kPgCo65h0V/_Qc0@N2FO;Vg=[,
Z*r(TEG*VVR;"_?DR([d-*IaXV",PuP_T<p6,YjCcP3JB`9CSXMD:fmDV-Y6ji@At_)_q8LB
K9.4cc\`"Jsb[lW=n]A;:&b&?g9@X]A,darmch\U>cPk?QI#AbeW:2j^oY2M.77ok[D<kp&&DV
'u`3-9&-43s&sO'<Q8,"HbOhX#DsklG-@`'Q!Y\RF*be($G^&3=<M1h]AltobuE?o;t`aWJ,W
d&`;'YPRt.fX,sfY^U`3Z$i%k`Bc-8Z\CF;AS4"72"3pBY0S-rBrSgit'4]AuKB@h\kJ1ql'@
0d?^GGtE6Xhc[4\]A+/AOoB":F5^D)7Xu.*Yk?#=_dlVXMe_CHkHNR[hh!WiAE_/b=`">cBcW
RN`(;1bg*=f,X^-Z!3WmO,7"M=eoF4mWTj#@IMHc;Rrr/)^;9nck!EHa1I3:.[GigC6`c"*>
,2Z]A'5Ffc/]A-hmE*lejGW4RNmaDZN2!@$ri1n"#^_P+\OlAWh<0%#YL3#Q$P43"!Ul"Ga/VR
LX%]A\EQ6m^Db?0)fQ6U)n'lW?8EA,,=i*m(B8LYr)^;k]ANW:q/ReO`Yh<#=oN^5UeAr/QYhD
jum,<7OR)l<]APSArL1\>9+W0Li(f<H]Ac[UfD(k-c;-\0On_KB6g6\X?tM0it&JJmp);#J?`@
0bM+t7F^mM)>38/?S=aL?NnSPI6Km&8+hj$#<$RA3k,JEG"aK>[>`E]A(^\*]AjL,1<b9%T'm2
\Sbo*J(M>F*5DYbjV['_@VHPG<ah%a1qHn!W8>T;(@K6:UB[BA:<p,*EM+Xk`%Am(-uT5)Ll
@^H8,af_)X0Eh?1%iB3qX*e>VOLPn1U5U^A]AJZ9O(hQbDqM_[4\-f8kA`:*H:L'P.6Gu-9&+
oRg1Hot^Ils:sa;;;HjR%`:nbC<`g<Sj%9"&-qF9C]Ao+MR@R`/9[OiL3bff;e`*"+ob-dPPe
WPE4+.OT&md?0L?01IEmWcVm6"BUW3[.Et%Js=GCE4'H2Oa@Z%fVHroBYLDC98bQksK<>!d0
\8K#IP>8j$CR0?b9r-bmZ59l:UE[\ZkbBPs?J$WUGP4FK3MLVM>$$+f,5Ns+)[<4=@kK!($3
)$pS8fsO*q"[@7^$!ng_L%n1f_bLFZLX^?Kc3tBdFl>^#8E_T(Sa+:iUl`A?-J^DEc/hs3U^
oYIH\i&#s-aKt=)^M6kEEgH\h;-2\XT_JTUhEj*#]A)Fr>*^jSGm-5JVZ0Okc4k^-iT2/Z'8V
N_UdPT2`a1'uotRk@-2\+JIUA>!)[&A4Jt2A#BMrU*`*4/Gn+T(Pc4rK^+h@KN$"oaNZfe(l
MAFIfDZJ'+mH?IoYkSrRT[@92q6ePYm"?db!b#E![@E=p)s?kt6gQ<2*iD79":\lHl(8-b&=
kL_dG9&7h9="QD5b^aLRp]An:p;#STd36huL2WGr[UeucPXNe.^:P4El_s5>1Ouk*R.s!4F.L
32aW%@npd(affa_g%I+KRIk3bU@2J-OlT3Z05hOkNq1Gl[B@r,6L)3W8fcJ,Kl~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
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
<![CDATA[3352800,2743200,2743200,2743200,0,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
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
<![CDATA[LEN(E1)=0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前决策报表对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="销售员"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$$$]]></Attributes>
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
<![CDATA[条件属性2]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[LEN(E1)>0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.HyperlinkHighlightAction">
<NameJavaScriptGroup>
<NameJavaScript name="当前决策报表对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="销售员"/>
<O>
<![CDATA[]]></O>
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
</HighlightList>
<Expand dir="0"/>
</C>
<C c="4" r="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="销售总额"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[销售员]]></CNAME>
<Compare op="0">
<SimpleDSColumn dsName="ds1" columnName="销售员"/>
</Compare>
</Condition>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[$$$]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[LEN(E1)=0]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.RowHeightHighlightAction"/>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="1" r="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品类型"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="A1"/>
</C>
<C c="2" r="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94:%;eI!qUkU@cbdM!n"O^TBcrh(fnUa2m=Bs6NR^R<QG>Y,XSPU&r7N3KW$!YgEe.Ne"hu
u-G=;[ro;5$ejP?;(Jek/9.N=\WQ6$&,\<":06\a;t)\&*EYM:LR?atMn)kBrWjH(X8J^)&t
^o8WCh.?RO1!Wk]AT03e!j5X:tp+Fl6F4H4Sp11&WI&2="Jng04Ofj-Ycq:=+7"1pJ"*'R>Ij
,e0%gcbJ::n#-jfZT7Z(H^jiS%!D,nr*kPQQJ+a_V[JcEO#%6pM44%#7Xc3%#u+(e2-0b:D*
;,Pi-U/8!t5c"fJc6\$a*,(@MQQlD6-!H6.FEc?-qt)#M,C<_a]Agg+Hm\Hf,"mK3$#L*=Y+'
s-LACa,E`uq$PF90+fGr(m@ufO_brB?D8VXc$WoY$tR(EH]A@$J9:amD/#oZZ6$!IR*aWlr9Y
Jn-k3^gE%-Qa=grnd?#e#6+jD?s"8P);5GfPT14Y&a6esRKMWuSTO+,+6(EUINOA`l]AK<7%%
fB^A+$/`CCBn`Ie8q!(9P<-it)'Z3jil*_ssL34/:Y=q*-r.MM?`>U/>Ehn+@g.FK^P//E0#
kg74KGF$9jr_j_Ush$337*pm)N#H%D'!28EZ.h=ce]A`fM0F]Ah2`,n&X1/$'Q1EK)Pfsb$n$Z
Ue&Pqil!M.)b(qk-c#]Aa_Ok#SfLb@3(#U,<!ZD)5tX8\=?D.[]A11]ADbNEg#)Ii',:oVERrn!
#J>-45N$CiGD&QcmYp!UESING/(ZIJ:MC3sFGU@=X7*X(R)O5IFs<MCK3:i8q"Ug=4C5dqia
'3o)QQ[5D'Pn`Xe(ErMT%rYZ=_m?itAUX.R8egIqeaPYGl;qV"n&IKNWW0Kbt@RbcFLTlL/D
KVVQqFDB'akle9\jd3alfECpnW&c_BPa2I6j[)0-;Vu)r)Sk*.u;<gP346[b:O.Bc5'f7RKn
8iG,EoqI;7:qZ*?Fm50oD;$8]Ab5N!or'3(kGXIX6t_Gs#seqd:e@_M6ID/?`*T?Ml<`nioJ)
2npLe64#tm6V4usE7WVB/Q]A:F@V.l/es"nuXoE4ub(clJP/[X9tBdj?QG)4nX.dEL5`W&d1@
naZ)f/RsgUp+eJ\#'RjJfp6.98KWs=8ba9=rb8rOnF1#WpaBuV2YA[93g6(Ro%_-#CVG_0CD
d;Yn*rPfDtR"9@nm-.`dsqnan!1oLNMSu5*Ff%(%Wa?r)La6&OY3`OFZ&d*^C+`H2f>*41M>
L?c"kW;)nunI<;j*T]Age^lW[o$XC1LX&s`?Vq.*IIrOUknc<.'V8Bis?ZE3q[\]AKJ<Rr*Fl=
\55e#/0`go]A%U:a;%a^.QP82oJ3>EMq9e-$"Klo>/M;%o-qosgXK8=oVc]A@8Vn^kW%N=-(TB
A81G<kZ-Ji"<qp@JYOk(Y:>rd(O,39J.D>ue:/s0`kZB$gc:R!@AZ0oRIGWBLkFj)a>IKUAG
'\-Xlik%`YQfAWj&'(?rjM@:ub4MC'fXs=83GI0?030BSTL_'lP*shnR8GLF.68I,e6j,i+)
`CE;*;PNdSg?EWhOC(hK'6%f?DLJ3G"m4nqCH"e!450Xt=J9/5cmcrc&NAYGSh08Wh*Mc/tJ
;.j1RdK:Bs:(&''Q.50$oGfi:?lfCnaX;jV6V:QlEJkf*T;r_.cRnCiUU*/6MOsqeQJg`%."
-,B`kCo$P/Fb0AB:]An\IHh#5_<s_&(R60V/RYR1=+$p7F.@@]A'ZPD*!j!ONE)C'<1k6ND_l*
Qc?ieM/'jaS]A!<UDI[dfC\i&@C<-2KZ<XQ>/Z?4YFgDq\(^-icB"D=DA.+,/]A+m[[TM!Mh##
O3L9H9;q@79mj>bM:RDKGX#)L2ppO^qr?cGW"9%iLBh(N)kPgCo65h0V/_Qc0@N2FO;Vg=[,
Z*r(TEG*VVR;"_?DR([d-*IaXV",PuP_T<p6,YjCcP3JB`9CSXMD:fmDV-Y6ji@At_)_q8LB
K9.4cc\`"Jsb[lW=n]A;:&b&?g9@X]A,darmch\U>cPk?QI#AbeW:2j^oY2M.77ok[D<kp&&DV
'u`3-9&-43s&sO'<Q8,"HbOhX#DsklG-@`'Q!Y\RF*be($G^&3=<M1h]AltobuE?o;t`aWJ,W
d&`;'YPRt.fX,sfY^U`3Z$i%k`Bc-8Z\CF;AS4"72"3pBY0S-rBrSgit'4]AuKB@h\kJ1ql'@
0d?^GGtE6Xhc[4\]A+/AOoB":F5^D)7Xu.*Yk?#=_dlVXMe_CHkHNR[hh!WiAE_/b=`">cBcW
RN`(;1bg*=f,X^-Z!3WmO,7"M=eoF4mWTj#@IMHc;Rrr/)^;9nck!EHa1I3:.[GigC6`c"*>
,2Z]A'5Ffc/]A-hmE*lejGW4RNmaDZN2!@$ri1n"#^_P+\OlAWh<0%#YL3#Q$P43"!Ul"Ga/VR
LX%]A\EQ6m^Db?0)fQ6U)n'lW?8EA,,=i*m(B8LYr)^;k]ANW:q/ReO`Yh<#=oN^5UeAr/QYhD
jum,<7OR)l<]APSArL1\>9+W0Li(f<H]Ac[UfD(k-c;-\0On_KB6g6\X?tM0it&JJmp);#J?`@
0bM+t7F^mM)>38/?S=aL?NnSPI6Km&8+hj$#<$RA3k,JEG"aK>[>`E]A(^\*]AjL,1<b9%T'm2
\Sbo*J(M>F*5DYbjV['_@VHPG<ah%a1qHn!W8>T;(@K6:UB[BA:<p,*EM+Xk`%Am(-uT5)Ll
@^H8,af_)X0Eh?1%iB3qX*e>VOLPn1U5U^A]AJZ9O(hQbDqM_[4\-f8kA`:*H:L'P.6Gu-9&+
oRg1Hot^Ils:sa;;;HjR%`:nbC<`g<Sj%9"&-qF9C]Ao+MR@R`/9[OiL3bff;e`*"+ob-dPPe
WPE4+.OT&md?0L?01IEmWcVm6"BUW3[.Et%Js=GCE4'H2Oa@Z%fVHroBYLDC98bQksK<>!d0
\8K#IP>8j$CR0?b9r-bmZ59l:UE[\ZkbBPs?J$WUGP4FK3MLVM>$$+f,5Ns+)[<4=@kK!($3
)$pS8fsO*q"[@7^$!ng_L%n1f_bLFZLX^?Kc3tBdFl>^#8E_T(Sa+:iUl`A?-J^DEc/hs3U^
oYIH\i&#s-aKt=)^M6kEEgH\h;-2\XT_JTUhEj*#]A)Fr>*^jSGm-5JVZ0Okc4k^-iT2/Z'8V
N_UdPT2`a1'uotRk@-2\+JIUA>!)[&A4Jt2A#BMrU*`*4/Gn+T(Pc4rK^+h@KN$"oaNZfe(l
MAFIfDZJ'+mH?IoYkSrRT[@92q6ePYm"?db!b#E![@E=p)s?kt6gQ<2*iD79":\lHl(8-b&=
kL_dG9&7h9="QD5b^aLRp]An:p;#STd36huL2WGr[UeucPXNe.^:P4El_s5>1Ouk*R.s!4F.L
32aW%@npd(affa_g%I+KRIk3bU@2J-OlT3Z05hOkNq1Gl[B@r,6L)3W8fcJ,Kl~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="1.0"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="5"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="4149db62-c655-454f-a1eb-9add1ecbb260"/>
</TemplateIdAttMark>
</Form>
