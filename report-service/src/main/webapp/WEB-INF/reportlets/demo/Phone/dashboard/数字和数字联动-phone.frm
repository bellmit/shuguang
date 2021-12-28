<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.EmbeddedTableData">
<Parameters/>
<DSName>
<![CDATA[]]></DSName>
<ColumnNames>
<![CDATA[区域,,.,,销售额,,.,,利润额,,.,,利润率,,.,,客流量,,.,,客单价,,.,,上月销售额,,.,,上月利润额,,.,,上月利润率,,.,,上月客流量,,.,,上月客单价,,.,,上年销售额,,.,,上年利润额,,.,,上年利润率,,.,,上年客流量,,.,,上年客单价]]></ColumnNames>
<ColumnTypes>
<![CDATA[java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String]]></ColumnTypes>
<RowData ColumnTypes="java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String,java.lang.String">
<![CDATA[NRGKI_E\OFn[ZAhCUgu]A0'Vs6+t>R0<,m&"kobF+^._c'/PNOJN]A.sXm7.el?#Q4Q\R!G:ZV
uE@&sV5[8aF/h6-d"b&MP!:9lo0e\LkG:F[/(tLfcP*6_g>[6KDsi]Ag&"@^.3f0!<t'9p5>Z
=-%X!N[&]A7keRh(/))tcM[3M]A8#0KFh4*>o"MbXu6$D\]AiK]A"/+rOO'%pS8_%'h[i?0\eMHU
K,iN%g*^aok`/S^jg$T&@:!g!!~
]]></RowData>
</TableData>
</TableDataMap>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false"/>
</FormMobileAttr>
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O>
<![CDATA[北京分部]]></O>
</Parameter>
</Parameters>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16777216"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16777216"/>
<appFormBodyPadding class="com.fr.base.iofile.attr.FormBodyPaddingAttrMark">
<appFormBodyPadding interval="10">
<Margin top="10" left="10" bottom="10" right="10"/>
</appFormBodyPadding>
</appFormBodyPadding>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
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
<border style="0" color="-13421773" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16777216"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16777216"/>
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
<![CDATA[1028700,1181100,1066800,1066800,1066800,1066800,1066800,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[432000,1866900,2743200,2743200,2514600,432000,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="5" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$dept]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="2">
<O>
<![CDATA[指标]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="2">
<O>
<![CDATA[本月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="2">
<O>
<![CDATA[环比上月]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="2">
<O>
<![CDATA[同比去年]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="3">
<O>
<![CDATA[销售额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售额"/>
<Condition class="com.fr.data.condition.CommonCondition">
<CNUMBER>
<![CDATA[0]]></CNUMBER>
<CNAME>
<![CDATA[区域]]></CNAME>
<Compare op="0">
<Parameter>
<Attributes name="dept"/>
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
<Expand dir="0"/>
</C>
<C c="3" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上月销售额"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上年销售额"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="3" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="6">
<O>
<![CDATA[利润额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="利润额"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="3" r="3" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上月利润额"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="4" r="3" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上年利润额"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[利润率]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="利润率"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="3" r="4" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上月利润率"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="4" r="4" s="4">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上年利润率"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="0" r="5" s="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="6">
<O>
<![CDATA[客流量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="客流量"/>
<Complex/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="3" r="5" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上月客流量"/>
<Complex/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="4" r="5" s="7">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上年客流量"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="1" r="6" s="8">
<O>
<![CDATA[客单价]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="8">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="客单价"/>
<Complex/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="3" r="6" s="8">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上月客单价"/>
<Complex/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="4" r="6" s="8">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="上年客单价"/>
<Complex/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" leftParentDefault="false" left="C3"/>
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
<Style horizontal_alignment="2" vertical_alignment="1" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="ColorBackground" color="-13421773"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-1"/>
<Background name="ColorBackground" color="-13421773"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="64" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="64" foreground="-4079159"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-15198183"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="64" foreground="-1"/>
<Background name="ColorBackground" color="-15198183"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="64" foreground="-4079159"/>
<Background name="ColorBackground" color="-15198183"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="64" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[c$9On;q^lY*?AjSU3&dn;*d6rAf(c,6(0#u'EUa]AZj]A>f,_;jA0G"j3.7t9<"GfSX,tL\).i
gst80EK1N0FGHj<%@:H#MQ.H#NZ5H9^!?Y*FA&g#q_8^MiT0SGV&P;Zjc&7).KbD&]A,+cPNa
#gU8-DrFbQS_nC,MXCAhZS(s1cL%?((&uUq.]AYp;2?tcIU#7,q&`dmHlm<mHrG[@<k&`Dtp^
"[]ARAW3QKgQLj&b<lg;q!j4_s7sWRYep,$KGUiGGEg#Ofo`FX?D8r\[8bRhbk?ce($$.7I&W
KC72=4]AWs_Sg:(j'#&1?+&DEto(j.i0gkrgk/c@W3WeG1f"gd(++euLEf'Z]AgLYgbgDbIjOH
QKBg4[EY8OgYn^qkeZ";HP)WH?cL3P9T0;cK,d*Ho?8/Jc.on!U_RllFaEuGd8h^PRF-<jY_
Nb,`8A\t"*7.H_LB3"j0/g#cf<^Fmj[U[X;'."drgk6hXV<GmXVfhZ\$$Qj`JK)\<G?)9"73
$4j-uV?5#,a"7GC#\O\E.o4N=h1g2ST;D0",]A*ZYfMH[.)AR+?k/O@S=bCX]AcMu:bT'ddtVA
MGsicaC$D1X!;S/Rb54N>u@`eaBl9j0WVY:g2lV,u9mT0/O%;nVE6:3-DrhNq[>cNf#K'qBj
kU$4)2h7Wc.O=$j'G:p0&GLQ:o?9h?^B\^%O/p3IoE>$6-V\/]AlE,<l?`PgEa`r%]Ac5`Q7Yb
(BO[WMmA#R&fXWlG13$.!+C^Z6h?XH)Nd%=X2@);e`M(cPOU2"ouU`l)#YK[:&DQ=9Pe+Y;&
G848,"&lZ^R2p4)DZIS&7E7(e#!9bM%,=>7nA>j"E$gQ#V,T+1Z;OS/r4gVn0o/_R@.(l@D2
h#]A)<QNT]A!W_Xkg+JNDY;*0a=01#a6aqjT*cA"qPQ=\K&k[cQ]A&,@137+'nSh&hsb#ArE2rC
j;qHcVao&5O-.E5TL:egAggQ0rkYK/%D(hW=GHAS:`S`hEVn.VA%\!&2^_gP"`k!-U2W'1MK
pJA_J!M"u=lA]A9P53/;4&'\m5YGqa;:;+/1q\I6$j1H]ADAIBcl!AR+6Pq0A5Ge^iYFuj1\s-
%(4UC/G23E1CE\m7US1pa,#j769oQiCBD#cg07^/ZTX1W3D@DIN2.t0fNI)U]A42P!q)tZZAF
i(oYtII]A0QV+(B%if.l>*c[T&4`;3P(</fnT_3*nL*2mJ`m2Gh1&PUake[j#PQB;U&hJ2W@k
tEHB@7,N]A\WBB^p_jEh:CO853+TYkTS]A]A:4oSrEBQV+7gI&=kBW0==T:HL`?/@0$GDP:MRQS
Wq8OI$n-Ek,S"e>]AY"BiXlpJA-2#m3UIGK$(p*N0;m>DV.)TLOikk9Y0E-\YHJhR\saVZE2k
'I]Aj_jf&X]A@hi>1>1OoABF56Y.XTh/seIGh*+&T.DY3$.#D-(JF8M!ph[N`q0=';3mVPdqUY
@=**a)EDN^'f2S@5l[n2)18tTD+i?@R(8'Y8kd4aaPf"cRKoeq]AM/%F?:LV':pg<?(^'j=F]A
e-JE&BQD-;oDVl6YaP\&W?'5>hH$9`SoDYXcf&n-85s;i4n6?3I\XhUW-9"ZGJK-)YgbeS/>
LqRc<TdrT/(=M2D<#nJ'g&%^m31On8M9V,M).%*KaZ1ukp\Pn<$"q=-iFAm4rJ[^q*Q$+\Q@
`Y4pP&p[IV-+a>[O):RkZ;rr`,;=QaqOhn/S8!k+90hj&d9^D3B1@^mjF0`($m]A-6G^KtbS`
B5fBXiAmc=q*2SAFCp*`R2VVUglo0C!j#8(RA2^p0X*XWZP>$+#X";b0@?h]AOrD1cL@<8e=+
'58jC7D&dR09\Y8+g^;U0H>K5A,k<&`ZX"Lkn0?hLKAWBhAc0rGS43_/Z72s7E+CN^p=o:C[
K9S?-h>[a:=L4.+`L/ZHp_#j?+IkJQ@5Vc&fNe<&cXi]A2^Mr=@lmkIlsj@,V"\uXU/lH-<ta
e^,5t?QHON?+?</\Qgi1fF.7ff&IL7\7C.)a$lj8-'pUW[4A\"S_hLQAFbLDGRXf=VP"%b2N
+pZlX6)7/>P,0h;0'Rp%$!na,)"\(Fu;,O\>R5]ARo=-WjL52:oF'"$Af#p",;2OS<8H/W$IU
TgLfE,m7F/M`$+fk#YQJ^nq*_iE]AuGtn:TlY1\o$>Ks-,hQ8(Ki34\<ke5FB>8+&"RdIY$Kc
hV3t#a2mH*ZfZhrIV8/)p"f/cGO#-BS::Ol5>#dja<K-OhUrJ?+YfQ(!o/@9(*69kl@\`Q)6
LP[dO,l.+l2rio.CD$>(rI^)SKnromV%E_SV2R_Yc.^$G2%&+>YKp@_>78a/SWXfs#dZBaW.
kSj]A,?:')m_\1[srq%rKSK^^O4'A%LE;1*%ah!B"eQc-Bb<)fP$np)U?^6I*UD\@PqF^bC;e
PPh[3?T;o3j:$+9t8A$PN"TJ&p"1B,^HYu-Q-k9?h_YN1BWrJ,^m@>J`fR"J,s^+$HmBe.m(
A(>J6$:4ta#Ak_!C7$k6DeKHTJ@BG/um]AJZO_[hE'j`lb#UkZ+`rVn$O?hIYQYBE7#TVO:ad
+jMg?-\79?dK6:Y(Zoi<LnQkPhGUq6U4+oA\,B3B_9>q)mP_;JFC:EMdqh[,Nd&t^DKjGU!;
"FX\g&bNS**iB2?K2[F$W1e:3p&Z$\)^<j_*-8Tf3`Zc$X(J.PHKE34M/_de&&*E^1?a@?9s
Tr<%5Jr9C(M5DM#nCN\`c6j2T-bbPO.keVXDo2T&WYPS#=Mh.T'NX1SQL#u>j4"'^=X&]AeC&
1K5A+kfkf*(U_1.BV4j\]A=UBjVc8^'TU7Th:iTmdhA%Q2FQpF--nU/!=TdEFCj!ACk]Aql86P
:$-Z#O\HjRe"s'Q,sbCjGrho9cll=m(2#/s.q6#9N+7_1X$qJqaAB@#Mq3_7$1`!rm%#H?:P
B4Y@TD,\@^btlRK[]Aj^c4iGVOaI0ahi8lF=#55J,^GjoT$-tpjKoEt<)oYl%m6%fBQ)l@eX'
/kXMAQ6*%BU:9kdQ5I;G%6gLTFpuNW$a'=OQg;jsqm`9G'B0mqF*`K?Rh2lHKLd6bO0`p]A'G
qF'T22]AC8u9#Nc^73:4L2WBHeOrFirM&a8bSQA)BZ_<>d2[eIT`ci/@lf(3nT?qL)B0ON2VG
L``PKLLd:NMgW@:R[A:c[a(!I$`a4iDN[g>?MAsM@L5ECefpbh>scr\:-DIqQ5.tA$g_YHae
<a:V+su3k>Wom1B[mR>DYIC-/HboWO.)e5%Z/X+kfjHr-lZVTn1^YN%^g:$mWY"e8jI-(nsn
+/h&Gr2(.O!leh7$9>@W-V9q3]A@Mi5[Z4@J<SDcH&]ARq8KYk)STAs)PHjBo?O;PZ+W&!/NhN
DV^.u>u"m.'Q3grH7*Q-\<OYthR7*6fni,j#O-bZRoK%"k_M54O0H0D.#`DT96-Z/[=C21LT
np><(g)&Tf#!A>9j0-`W3X%^(j\;a>;5BlL)8]AF&O2+b(N1WTCK".fQ_no2Xg0aG6^h+9?l$
DKPsXO,35W#2[r"2S7EjU_J-,PUlcFj'+F'>E7//r<l3FZLa7\OIm-Z9PNR(GYT=,7^IBptt
/+Z4q9f<'\Ed90\h2?jpukk)9Y5e!rA[*IA_gCHPN?#c4aQkPCX4lO4<j3^7p]A-AN>C[R>"7
XogOeK(F$ug'cEh9Hbjt62eb:*Ql_q/aL$idb7-MbW?k*Pm;6pn,P\Oh`OtH^62i#]Ab^+"[?
(MYI&NZ<GRk:m.#Y.u<.G+H$P\1"jQ2#7]AQ@50=7WA<a/5\ZlrItao[O3I%uJi(mTW.S-rk4
%+A&0qo#"N-;F+uARH!CDJYpV9kapH=^DFMm1H"Ze:%4n0@esPRI1R:`p/eXJ.J9F0k$T/.9
%-^c8dEpZVI.!F-i9VJ['NE=ZK')B!$+OlomMrNfo@D64C'>@<S7AdLHTIR>BUJuhF&3FHl3
2^R42t?c6_dXO:R1in5*Xq8+_Ya_ZkThO.uQ8,J9OG:jq7#HLJs.mBphUFUXN%kO.;#Lt$Sj
3nBSiq:GD1'F^>in+"Gg-.#/3Od[k(P,I-8DmrD;Vi$X/^<@PQI4D`?'kS+JUK,\/1W,lg;u
DH3o<#LmLjRM#YA-RQ^8s7o_[$!d?SGJ\;G0@5I\W:>F;`GS\V.=;RQ*`pmpAn)/#;h-lef6
"i*3Bk]AD$U.YW1`eR:j>k09_UEha^M.aCcM7\%d%0p16o[SVWm[X7G:OXi^cT+;jfKh^/2nj
Z.ArB#(9snc1Zt/_i^!b%XTQOjp$bAU5j';<3KH3")6ajplGhF_tadoXK.V5dV>^+s7%F-V?
.u[O-C9=iLg(%?P?md`ZEJ1I/OCW4Oso=/m(1VQY([]AA22hV,n5]A?iJNkiG*d]ACY]AL@41fp7
Nh)skiDFqobN4s%8_>r0*_)2>Mp&&Tl\pgG'hU4?N%"B"5HF>81=`l;*lHBk<Qk4;55L_;b\
5<Pgp_';O<l>8^br&,);r[e\QbY("qcqZ:t(G_GK]AS7?n!RH9%"+GW(qPH#ik>ZZPmV_-2^`
!G&*YX<i-aeL,5]AdrauPr@23bgdPG=n,F(Q[eW^)+?%7+#*u3sd/W'c5TMBqB>]AGMX`FCaL.
Z.3f'kMNX*f#WfSegd?.dEV'nf=#Lqi:OoJ:JZIZCFomJ`3)oYGDF'=)^UIUs^NGqHJ]Ae<tN
J:Q9lOh,\""jV0nS,V=V^02kJe6Pjun3*qud@Pb5UEfkK_5ZH2ZBP>B\,;=N+0Upun=YrR:'
.tgbN/Z[o5S>k04'#<a@0Gg-$T!t:oYuO4;jU`9:.WGRqr"!D!=&cn;<BmP"XfGpX+qPm4[#
7C04jg[e6M04)"f!sga7E,$6'5,>i(;=pld]Ag.=.TloL.c)L&IL=\=51@^QR$4oSWEQt[Cqb
W3=K2<<WW><f[X%G]AOh^dI/t'>;/S,UhG2HV0!?!@/f8^=WK^;t7AAIW,<MUGghC0oO<8bP_
95OVJP`kL=NA.F9b]A7CS-c$#e-Y5Y:F![\G!>tGrDPn;3;'k,$V$#a'i-;(GTe!]AdoQIU]AHR
.fgZ3J-"Hgn_pDtjbHj6S=A2rU7_o+8\UN%p5LpiP'0JfS1MpU!s;$+I"cSE.0DV:e]A,>'Pt
B6HYuKO!26`@:R&_2nlPhN@WS%iJjm$oNQo.-8RI!r*-$&s]AJn7rid<XmnW.'@YI_l)l)gor
`!?,.D!T6pah.P_6RfV$9=QoE\-5bMDnB3;$i+&=`'r2UbR[b%&afA(a<bf4lR+#*Z-;b7Wc
l4$@I;0qoZYpFWEp"3=,93;8=l\H"A7@%p8U_]A9<VX]A#"U+]A=M:=Efd-K*V=WCfmEo:#6cZk
HR$JjK%Du#aE=XWk'_!YqXdST.T`dEr0o07p*Za,\]Alk]A;lI^Eg8FcTA<dD._/j*$E%Rf[UR
Mdlts"Lo'')5qpKSHo?Ztko=$2AjKj_(IBfUf4mN(]A#(8Ot;,m%tk^BW)$.ih_FDYG8I%8KY
B5t@m61l1lj!jZ&-\TDZ<;"/#>\1#6Z^18RFjgd8$t$?S\d'>OqR@LaD.R'u_t)KE@Und$g3
1fVMMS]AkI8R3j:lR[\R=B]AmWcerHLl4k'5l[os\R"QQpu!2;8*ZY'eG+"gfYdG_6-f;roVs$
U1+6+p7]A4B)&b$bf>`rB72^lfZ^1PnpTUSS190keF7spA&<A!i,]AtA.V/cmf#?e34s\6WZ`D
Ir;EQRoJopU9RZdJW[GG,IN2]A:OT[/c;h,`kG$3kjh@o&<su@I$:I@*n7o$-gJQG886f;Vf#
d&PE,=Y`cS!VBTf!@"05#M$)VlDm%r1S?7n0\"\a(O^Za44Y[D63V]AT,9`Kq"l.b5Zej!7/X
mi8gAJ_cF5/s;s")NlfeWHtf)n5]AANH,&N.D9KZ8H9?tumb7A%g\iEnl>Z=MI26deDQj!"I4
[Z).LhCaK2O[-f>h(O\J2<..?deD,t`OjN)Rt0PfgSeSQf6\q3\5Un.tqCU@(7s]AZ0Qc((YJ
oC-UFZ/nT7jKS<&^^G)j]AKpUmA0#=("<;Ob81sJGs9>OLip7dD\UCptUC8Fbu\>#m8A(co::
&#*Ucgk%Loh@Lu1\2el"clRcq7O,T<k9^d1oji+?%`4(gd2:/7Q?$h<Z7[T[k`&WM=J#bemQ
:_GH$c_5g3fYRW?HY(EIsp(g3A@Kc0JgB8NQ$I3rBPnkOSfp*B0pVXLQ=\arNX_OlBG<ZTq"
_7Y*_([8jQ*T/>)=[<-WUO.\7k!eYcm^/dEQ"9W`rK\HUOhu#`-Wuc:7e";V$=Z.5o31b#+u
.F8ff,a&fqV^%.uo:2\>X_81<E*^bqSFmG4LdJS<i%=]AdDpK7J3%nUk!+@a->)'E8t!jn5'F
.0O5LuYO[$<C!NZ+X#-")PtbUjoq#8*qUD!7`oa+R<D'=SZ)Ekr+]Ae#'M;-sRpg]ABJVTPXWg
+4lPa/u!!5U=VIYjJ6p5.dBUP$:r^p77es#:6iAFd#&S%,o:B&]Al1)29V[ELpFmLQ5ZH8VOn
Ms5ZTIm[.d;YWF-06-&XTm?mHYoDbKgNc/*RQgl/:`gCs@"4&P@c6&AM,6dNEgIP#+j]Au^JY
\FO58VRjp5-RdGlkHs2,pj:eF4HK5dX*B:1!aHP<597c@)t+8LSEF0!It?UR,;`)!m_"e&r4
3lf]Am#-Ph0E6%rAsVIpJWD#k*-.%W6I8cE-bcS)"-Ff-C"1`^a62[/SltW^"sUJE&T8=RN8S
d!-`(hG?M=q1<g[-?;BZu`Nr-@QuFp+>@3gpXfW;kdV8@J7GEDNZ\fh(dGG7o>lCZEUaa>&m
+eMk7rYk`G":<N/?2VqUrRsq)Ynhfr3#g.W)28dJh<ThQYMD?6_C)c4j"\R.^M)hPp)<7k4g
))fPf@%",]A?sJsqTY_5/F/\4"ef(^cb:-Kh<@*0FJ@gY?`5Ea&&Xn1_H,U?>R^NYbT%Q*+t,
Ji'=`R=-ncN73FCA"=(?S0^o_+ZNC:eO<[OSn,B"#I?[Ykd7BZ?HiCnqa"nMlbKI$]A3n5RP]A
H-Ye<$l"@3]AirKB!bp@*Q]AY%XkD"eYpHZce1_JQUaJX"TipaRps*0Np]A'g\;IF5DTD:TOEW]A
*-O;q/bmtd+#2OWB<+#T(ag,$.$9cjHW1G[ekTmLG.d3=lrR>:6Nbq.@&sj%7q?V&3*Bp!G)
P&\R%Fe(''R_K=-c\k3R#1E9XjLZ+<Iu`b?&PIM^PZ*;rVMpr#m:;I4ZNp-Of;l<3nTq"1u=
cq!kXIcd7n%g[_WrB[:C1P4\;/`=2P0I92EB^@JF?AWLD*i(RR0)#qBkRmemt]AMIL7R[h-YT
ciLqkC)*(B&4i1jEE7HmGrIE"ac:tr':W;F;b4f^PZHt@UtNFQj$&*tYnf#f]A#n6D9Lbf=Y@
-DU7"P*."=qJ1=>XbOSp$?ug?TVc=ceu'pB+ADGMGO[aJ\k?VC`0j3C*9ZeG?01ek\'S@8Nr
O(82db)KFoeR#h%D,[Ug3_NcZ#,6R[Mqa\K`_QZAflQd^!,A?WAk4$N.#<Y2n,_au,oWJc=e
uP(JH>a:)=7i0AJ@cj73Yo%l@Etn!,]A(oo//[-5cgp=DM.\VtaO1fS?#I2l="+'J0NlMr6Qm
T5gr5u9'cCerf6>Nb<?8j.W:UGK?S547NAqT01G"A(pqSBNc129c4uh`GJD"^fW.?'+%,b=U
%V;0)^p5n25iFjTNOs2%=5`Nu:emfS[`JV"Q0%,<1Goq2EGT`\B7X,\I_da/Lh&.:KCcCjG9
$`i.Cb$g;=12hNC8;[d$U6PQME>8_EO#cUd1d:X_RqYI.CJ;.c$o=CM]A@CCjH@TX"l(?*u"h
XejX5dU)si+a,1n0q$$/2/L&ZR_Xf,@D+Y4H9Y"@o/aFqnbsl>a^J;'=iJ+E79Mg=6I>7fB<
@Tq9=Nh9o9NGoJ?_8'0`$L<U>JDgcV:WcAKakJW7B,ED-^?pAF`cqN'2CH:)5g5R:CCH._3k
XY%rXFVHTB,'.00^Go,W4FQiDTuMS%f-Z>JnfAOL6Paqd?<V&"GP+!ca9=aJd%XK<e[,2+Ab
eb'-h$FX@ugH>$O'Z,MRn"S;9$hZq#nh=08r[urr#]AqXVae=PlG%FRj]Aa"LP78L3r[**AD=F
@,#+4C&n6UGRe7%A-jdd<&<@4WeN62)6)Ts'L+2U]A@0boNCLAeqJ4k4De;<X7qq^^XI0!8%5
l)("FBkB?l=03@ek^.ZG`0QosuR:M44cfDaPag+XNWA;U43fniYkXsms3Fp;mE$=Bc,O0l"Y
*[6JMq\Y@Z-/D9iE8\l.316jB(f,9_roM[?aQ",`Ra;$=F9LPkWBFZkmXs[+u)*9\>dg`cc.
`Jo"eVM&%T?fN&_i)9j6IS:'=0mrd:G$[i4S_q]AHX'bGi^a_M,;<J4J&`_T?+<AOuiBlKTS<
TleYs@IJU`Y!2]Aa>St)Qj_S*^/h-$tH-Q:0hJ*tC<PT966)u_dm3gu)o$*uL!^3$>dri@;SJ
A:F"lf#a&mj6nAfa!KNklHARL2]A5s&prmOq'lKdl_"!)UI&6?SAnCISl&a[=,;@s.gK!d[mm
KpoOqLqluT8\.FB>X=<O&Hc=faI<>)0(Jam8e`'fC^"WUIcT7rFY3!50q=G-WQ]ADeu=BGDf)
("Z2@6Ih<^Dh>1P0%.S-#!HN`9X3Cn&_cBXSX:T#FMT!IF%('`,-iJ2*[GV(EL<;-^naddbK
HNC?O1NcB(kEaSHb^QgD->;>gILk.'G9rlG0MG@8**J*^g9GuY]Ag_GlS%,Olo[DS.d`JNrWh
5c.ctORR17jlqC,XO,3u.8.56G#qd\rpNUumGd#^ec%iH)#dkNh+j*oh)J<l]A)+d^a(Tn3]A=
>OVB@LAI]A'Bi4?UVB;`p*@j_\5PG)Qg6)4rudZUdf/[P6]Au:\L-k`G5e-KP]A/1-]AX-?A+i50
b@K"qO4mV='57(n`Zl\Js2khL5,Q[QSa`q%1HHu8DP47sFj\bn&k5Rn%TG6l`_#:%qB<t`Ap
l09c&&f.\!@.l&V8tek@9`O4>.Hcm>Ag6N!YH!kc%A57/;^E_$oc'".#QF9*6'\+HsQ/m49Q
!k1e$&cQ^m=@'+F]A+RJCG<pT]A[oH(FPY[)3Y5fa[PbgrXKK$,^,*n%gCnYXfZn5peL=%KFal
0JKp,p+KK*\1r+1LbL(OGNaG(BLbrMU/9o!RfEb.oTp5WCA<jLX\<e:,0(XRr5uh-A=7DhZ'
(t]AiPSihq%V+p9!`PDA?/X-.<aeD319GPClNNMa,SHU(ksbj2cHU[gmUttALkP%Z_+p=+5#[
EOp"g,a83Yf=V&sNII\0gRGN`0K?l9fb?Y$Y^_Vaq10a(A*8J[NA)F/Q>\e@9oL0EHqDZuS^
p&o-$dl^0p4p<?!P">mgeIM90@!g"=FR-4WVTBM/^*9oo$L7JFlT7-^MU-aXS'>DraN^j;^V
ftBkN[ei$!K9nlPb[:h7>u#e:Ckcdm_50V^'h!@Hnm<N+cTF'8QAZ%9h@U?$Rc1j_)DqU<L/
^l@DU7L2i%2U9?mo"tQsfYa192@>&+fY(,f?`W1jR7H+]A"l7pV<c#GI[Z,kP=]AnT]Ad"lF@qK
Y;s8*R@^:D'oks'65=*VpbBE9R9W\<r8`IH`bV^JD>2I(4<2a_;F.3F)9[ph\sFWNg7r@*;J
JlAW_pQ0;dVJZ(ffOJr5n]AZ)l^Eh*Cr'?&"^"+Ral88S)$+#]A%%0e&lXK=ee0c8TWPp!k6.!
oihgIXX?jQOJL?n"kUR_%""@Vu]A&XG8rdu]ANGcJ>/@)P_IUJIX"@6UKf'0ofZ;W^%,>@T7f!
>fYDSJVBssLe-q=NN-J<5=EE%a!pqNtjV/eod,3-r%SW3q7HU+]ANC<iaC<)%2kp[uV8*?-\l
`<6%u/-#C7MsZ"XIp<17BJUJm4[%;kIj1:<N,\em7fEhu;"94a\R"A3Y?h.)&cTgCi/&;]As"
a^U2_j7qA&fo0^6(mM&Z?,,mj8_=)+r]At>OW1M5PZG:'qMGVTN8s\4'h%K9QlYq><:s02Ts/
@!QoWFZF"?4MN&Sn7WD^ngVh"Z)'$)[A-8Vdh-j.*ih!n\f[#L@)9.Kd2#)P$o*a_PRP!n6k
PhI5j"b;$+C47SJ6aj,capfXp`r<8lJ'f!Le%iK+TG&#N>0UT%k&"iaT"VpQ^VHTh)+s]A3WD
$Q86VMB/sW*c&b^A7O"qPb"!ffdRLpiDWh*5\D<g6.l#(r'?Io1h^V9S(AC\04D-^N+^AAg[
01ND*r:fUVhgg5]AfQ$+In]Ad[U\=D@Z=2QWheC-r_T]AbT#"=E]A/O9J"f,+CG6#RVo2KgA3e<N
ul3Yb6R9CYT7HKT=/_GfS[pKF\Le5A'd-0&*//Bn7!gB$KuT1E-Y8M9j>J;tdtM5=9&LK*cV
ZD/Q%SA;gHGemTtcf9\u&L.hO/4&9eioRo?&0o?_&e-K#k=!2D]ArD>N*KcnXb!!3K,SOiam+
-\j"-J3k)/OXL5N6pak/<'U:c^Ma*J]Al!VdS2/pE<)5^I-QZI=8LMoXh.?LX$^]AJZ_!8O5JW
tL!oC#B.YP*1``?>4ao.cOK2:V9a\TeSUeTN6qqj1R2Arl%Q:.NZ'rAY3h/K(koSV`!m7i[`
X-g40Y:kI3eN\jB?5>6,9oQ8Pn\[F/?kSqfg;M+hP]A)BKK,QB*"Ed;27J6Zc'W/i/qahCq#?
lW)+5Y$91V@CIU#7;Rqu70~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="353" height="242"/>
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
<BoundsAttr x="0" y="316" width="353" height="242"/>
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
<border style="0" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16777216"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16777216"/>
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
<![CDATA[288000,864000,342900,288000,864000,342900,288000,864000,342900,288000,864000,342900,288000,864000,342900,288000,864000,342900,266700,838200,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[432000,3771900,3009900,952500,2448000,432000,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="3">
<O>
<![CDATA[北京分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="4">
<O t="BigDecimal">
<![CDATA[3286.91]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B2]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="3" r="1" s="5">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B2]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="4" r="1" s="6">
<O t="BigDecimal">
<![CDATA[-14.5]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B2]]></Attributes>
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
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320@UNSc!4"+7fRRN(3UM/%%*[Cr/rh(iF5Gd,Rk#+"d<;d9!UHhA%!
EH<%OR4A0IIM=rY)_!hHG<S;,VOM$"<>cqqTBTA7h-UTK%/qF"d2^rVJ_=mf%UGbP$8Vp\]A6
Dp\<&6.P0F"FoD*m*WJ$"7D;j8cQIB%XR<1C>:eC9Ks(FKp6L+$s)1u.l`=[355t&89h+<]Ad
T*![S!TE=/c@E-^8A@<QTP*0_B6792)),TaU0t4'34``p:eKc'75Igq@MK;e`K(oC49dmCA)
#hcLrK3<Mofp>e,'J57?4134Ep4ljdHn^!E9g&`9j=<T3qjC)6P.lsO@9MHQNnG@tlBH$(?%
C&*hU_%3TpQMh5"i*Q6Gm)RZ"Qe=R]A;#rHDH`jGN?JEaB&[cc[m/(_q1*1&S=aB?0<UV/Jfb
l%&>hSl>bA+`"5l#r4l_Aq^&Y5#/rGI63_^kfnKkUrh)d*;)<#Zg!(g3"]A'a2/e)C(V/NX1V
:_5'I0`4f8_R!*=1:]A.B,R/.4qNWYVKe_\Q.Ol%e3P#*"nH^X5QB,c:L0Q=5p<nb=enE+N2$
p&0B?/JlVs&kjThu%/:jpb7pijQ!sI5ScfnMOoJSY`Zqs,Hf#4.JP%nV=5R!uS3(A]A:+@s'=
n/IAlsE1LnYkXP!)!g]A'KerVP#*q:]A8sR-@a.>MmCReBd+Ehu@-Hf*MlcUURtNZh"(>YJ;FF
_:#i?afKDfmQQ!-oV;(ki=[Z44&o?@ft[EZ[mR:W_)m8NmAkI"X\9Fiq[?/O4(S5)FEm8Y?!
Xio1ApRlfPRfm^8reYW@P0.Oe6"DH>NDCL1t5<h9XSkq,RaShu@X?\X)&t_r!V@/4HRh\W=S
Ue^%ft;aM,t~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="1">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B2]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3" s="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="3">
<O>
<![CDATA[上海分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="4">
<O t="BigDecimal">
<![CDATA[3178.88]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B5]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="3" r="4" s="5">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B5]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="4" r="4" s="13">
<O>
<![CDATA[+46.5]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B5]]></Attributes>
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
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320@UNSc!4"+7fRRN(3UM/%%*UGt/rh(iF5Gb.$kdQEBRAD-Ke&04X'
BjHm".#3Hj<i!0ZUP-0G^U!C;!L<:Jb,7JnsAK#M%"(Fdi8.Y?YA(<n3ddHM-&RHSX8U0A[I
'GO*pP8L9\7IdN"KBolQ\BWes_`iY>/WT^IpfB)eq\*kopXfoB]AmI^4fq-jjKYHD%HRCrEhD
9_V2q7"g&XepO&opqGs->[I@er:W<Nj&IoG.N0A5/W$Ld*pb/Gl+LJgt``Io?,YF5k)p->7Z
4mk$S/O#_,;/16-_9L%\J09>A`b-gGa*O*0\]A?T2fr?GLN/D,g=NDuT\"`p<G#eph"]AmFXAh
V6.giQrTFG+,?oOe3)\uR;"TsSZm-Kacc)s%)o9^nWqb.qlTp-FmOX_65fErq3_Z"BAJ'1^U
Cu?lFqu0"W6rR-jY&R1e'0=*"d3fR)ZqKO[Yp1[D>.aOX5j*<!k+_AmqiEptg1K)7OPNdhb/
dqHbtqRC0N@)>CeJjZ%3l<NU?7qK#YuYe@ggk.[1QECTTOr![`@r**/cGClQ"jg3=kD7a]Aaq
m>Ou",P!5^[>=cqq0Xal`0b`5MTCAH?IQOl`5*)/`4as>`j5Qf>I3\A``[0f)N6WD:.i8W-@
pB]A3GllYPh;jETQp$%/t$\Q"@_5eo"r(*@o2D2d*JirI+K+WVuSO]A5I*mlegUj[Pm/[q0D"Q
_>3JXdke"A8+Z.t5P+l^eHc+<h:]AQMEI,L*'0BiT]Ab,QThDmpY%Hq>1HJoOMDQik'>BdpSX:
)buHHXN!(Y?q-E9#>![dAf"3.1,=T)iO_##A;bQZZ%09`P~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="5">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="6" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="6" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="6" s="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="6">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" s="3">
<O>
<![CDATA[广东分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="7" s="4">
<O t="BigDecimal">
<![CDATA[2875.32]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B8]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="3" r="7" s="5">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B8]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="4" r="7" s="13">
<O>
<![CDATA[+76.5]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B8]]></Attributes>
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
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320@UNSc!4"+7fRRN(3UM/%%*UGt/rh(iF5Gb.$kdQEBRAD-Ke&04X'
BjHm".#3Hj<i!0ZUP-0G^U!C;!L<:Jb,7JnsAK#M%"(Fdi8.Y?YA(<n3ddHM-&RHSX8U0A[I
'GO*pP8L9\7IdN"KBolQ\BWes_`iY>/WT^IpfB)eq\*kopXfoB]AmI^4fq-jjKYHD%HRCrEhD
9_V2q7"g&XepO&opqGs->[I@er:W<Nj&IoG.N0A5/W$Ld*pb/Gl+LJgt``Io?,YF5k)p->7Z
4mk$S/O#_,;/16-_9L%\J09>A`b-gGa*O*0\]A?T2fr?GLN/D,g=NDuT\"`p<G#eph"]AmFXAh
V6.giQrTFG+,?oOe3)\uR;"TsSZm-Kacc)s%)o9^nWqb.qlTp-FmOX_65fErq3_Z"BAJ'1^U
Cu?lFqu0"W6rR-jY&R1e'0=*"d3fR)ZqKO[Yp1[D>.aOX5j*<!k+_AmqiEptg1K)7OPNdhb/
dqHbtqRC0N@)>CeJjZ%3l<NU?7qK#YuYe@ggk.[1QECTTOr![`@r**/cGClQ"jg3=kD7a]Aaq
m>Ou",P!5^[>=cqq0Xal`0b`5MTCAH?IQOl`5*)/`4as>`j5Qf>I3\A``[0f)N6WD:.i8W-@
pB]A3GllYPh;jETQp$%/t$\Q"@_5eo"r(*@o2D2d*JirI+K+WVuSO]A5I*mlegUj[Pm/[q0D"Q
_>3JXdke"A8+Z.t5P+l^eHc+<h:]AQMEI,L*'0BiT]Ab,QThDmpY%Hq>1HJoOMDQik'>BdpSX:
)buHHXN!(Y?q-E9#>![dAf"3.1,=T)iO_##A;bQZZ%09`P~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="7">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B8]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="8" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="8" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="8" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="8" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="9" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="9" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="9" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="9" s="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="10" s="3">
<O>
<![CDATA[江苏分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="10" s="4">
<O t="BigDecimal">
<![CDATA[2377.76]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B11]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="3" r="10" s="5">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B11]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="4" r="10" s="6">
<O t="BigDecimal">
<![CDATA[-13.5]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B11]]></Attributes>
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
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320@UNSc!4"+7fRRN(3UM/%%*[Cr/rh(iF5Gd,Rk#+"d<;d9!UHhA%!
EH<%OR4A0IIM=rY)_!hHG<S;,VOM$"<>cqqTBTA7h-UTK%/qF"d2^rVJ_=mf%UGbP$8Vp\]A6
Dp\<&6.P0F"FoD*m*WJ$"7D;j8cQIB%XR<1C>:eC9Ks(FKp6L+$s)1u.l`=[355t&89h+<]Ad
T*![S!TE=/c@E-^8A@<QTP*0_B6792)),TaU0t4'34``p:eKc'75Igq@MK;e`K(oC49dmCA)
#hcLrK3<Mofp>e,'J57?4134Ep4ljdHn^!E9g&`9j=<T3qjC)6P.lsO@9MHQNnG@tlBH$(?%
C&*hU_%3TpQMh5"i*Q6Gm)RZ"Qe=R]A;#rHDH`jGN?JEaB&[cc[m/(_q1*1&S=aB?0<UV/Jfb
l%&>hSl>bA+`"5l#r4l_Aq^&Y5#/rGI63_^kfnKkUrh)d*;)<#Zg!(g3"]A'a2/e)C(V/NX1V
:_5'I0`4f8_R!*=1:]A.B,R/.4qNWYVKe_\Q.Ol%e3P#*"nH^X5QB,c:L0Q=5p<nb=enE+N2$
p&0B?/JlVs&kjThu%/:jpb7pijQ!sI5ScfnMOoJSY`Zqs,Hf#4.JP%nV=5R!uS3(A]A:+@s'=
n/IAlsE1LnYkXP!)!g]A'KerVP#*q:]A8sR-@a.>MmCReBd+Ehu@-Hf*MlcUURtNZh"(>YJ;FF
_:#i?afKDfmQQ!-oV;(ki=[Z44&o?@ft[EZ[mR:W_)m8NmAkI"X\9Fiq[?/O4(S5)FEm8Y?!
Xio1ApRlfPRfm^8reYW@P0.Oe6"DH>NDCL1t5<h9XSkq,RaShu@X?\X)&t_r!V@/4HRh\W=S
Ue^%ft;aM,t~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="10">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B11]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="11" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="11" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="11" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="11" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="12" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="12" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="12" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="12" s="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="13">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="13" s="3">
<O>
<![CDATA[浙江分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="13" s="4">
<O t="BigDecimal">
<![CDATA[1895.43]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B14]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="3" r="13" s="5">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B14]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="4" r="13" s="6">
<O t="BigDecimal">
<![CDATA[-24.5]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B14]]></Attributes>
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
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320@UNSc!4"+7fRRN(3UM/%%*[Cr/rh(iF5Gd,Rk#+"d<;d9!UHhA%!
EH<%OR4A0IIM=rY)_!hHG<S;,VOM$"<>cqqTBTA7h-UTK%/qF"d2^rVJ_=mf%UGbP$8Vp\]A6
Dp\<&6.P0F"FoD*m*WJ$"7D;j8cQIB%XR<1C>:eC9Ks(FKp6L+$s)1u.l`=[355t&89h+<]Ad
T*![S!TE=/c@E-^8A@<QTP*0_B6792)),TaU0t4'34``p:eKc'75Igq@MK;e`K(oC49dmCA)
#hcLrK3<Mofp>e,'J57?4134Ep4ljdHn^!E9g&`9j=<T3qjC)6P.lsO@9MHQNnG@tlBH$(?%
C&*hU_%3TpQMh5"i*Q6Gm)RZ"Qe=R]A;#rHDH`jGN?JEaB&[cc[m/(_q1*1&S=aB?0<UV/Jfb
l%&>hSl>bA+`"5l#r4l_Aq^&Y5#/rGI63_^kfnKkUrh)d*;)<#Zg!(g3"]A'a2/e)C(V/NX1V
:_5'I0`4f8_R!*=1:]A.B,R/.4qNWYVKe_\Q.Ol%e3P#*"nH^X5QB,c:L0Q=5p<nb=enE+N2$
p&0B?/JlVs&kjThu%/:jpb7pijQ!sI5ScfnMOoJSY`Zqs,Hf#4.JP%nV=5R!uS3(A]A:+@s'=
n/IAlsE1LnYkXP!)!g]A'KerVP#*q:]A8sR-@a.>MmCReBd+Ehu@-Hf*MlcUURtNZh"(>YJ;FF
_:#i?afKDfmQQ!-oV;(ki=[Z44&o?@ft[EZ[mR:W_)m8NmAkI"X\9Fiq[?/O4(S5)FEm8Y?!
Xio1ApRlfPRfm^8reYW@P0.Oe6"DH>NDCL1t5<h9XSkq,RaShu@X?\X)&t_r!V@/4HRh\W=S
Ue^%ft;aM,t~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="13">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B14]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="14" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="14" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="14" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="14" s="9">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="15">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="15" s="10">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="15" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="15" s="11">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="15" s="12">
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="15">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="16">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="16" s="3">
<O>
<![CDATA[山东分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="16" s="4">
<O t="BigDecimal">
<![CDATA[1589.33]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B17]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="3" r="16" s="5">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B17]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="4" r="16" s="13">
<O>
<![CDATA[+11.5]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B17]]></Attributes>
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
</NameJavaScriptGroup>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320@UNSc!4"+7fRRN(3UM/%%*UGt/rh(iF5Gb.$kdQEBRAD-Ke&04X'
BjHm".#3Hj<i!0ZUP-0G^U!C;!L<:Jb,7JnsAK#M%"(Fdi8.Y?YA(<n3ddHM-&RHSX8U0A[I
'GO*pP8L9\7IdN"KBolQ\BWes_`iY>/WT^IpfB)eq\*kopXfoB]AmI^4fq-jjKYHD%HRCrEhD
9_V2q7"g&XepO&opqGs->[I@er:W<Nj&IoG.N0A5/W$Ld*pb/Gl+LJgt``Io?,YF5k)p->7Z
4mk$S/O#_,;/16-_9L%\J09>A`b-gGa*O*0\]A?T2fr?GLN/D,g=NDuT\"`p<G#eph"]AmFXAh
V6.giQrTFG+,?oOe3)\uR;"TsSZm-Kacc)s%)o9^nWqb.qlTp-FmOX_65fErq3_Z"BAJ'1^U
Cu?lFqu0"W6rR-jY&R1e'0=*"d3fR)ZqKO[Yp1[D>.aOX5j*<!k+_AmqiEptg1K)7OPNdhb/
dqHbtqRC0N@)>CeJjZ%3l<NU?7qK#YuYe@ggk.[1QECTTOr![`@r**/cGClQ"jg3=kD7a]Aaq
m>Ou",P!5^[>=cqq0Xal`0b`5MTCAH?IQOl`5*)/`4as>`j5Qf>I3\A``[0f)N6WD:.i8W-@
pB]A3GllYPh;jETQp$%/t$\Q"@_5eo"r(*@o2D2d*JirI+K+WVuSO]A5I*mlegUj[Pm/[q0D"Q
_>3JXdke"A8+Z.t5P+l^eHc+<h:]AQMEI,L*'0BiT]Ab,QThDmpY%Hq>1HJoOMDQik'>BdpSX:
)buHHXN!(Y?q-E9#>![dAf"3.1,=T)iO_##A;bQZZ%09`P~
]]></IM>
</FineImage>
</Background>
</HighlightAction>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="5" r="16">
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="dept"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=B17]]></Attributes>
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
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="1" r="17" s="14">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="17" s="15">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="17" s="15">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="17" s="15">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="18" s="16">
<PrivilegeControl/>
</C>
<C c="1" r="19" s="17">
<O>
<![CDATA[河北分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="19" s="17">
<O t="BigDecimal">
<![CDATA[2345.54]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="19" s="18">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="19" s="19">
<O>
<![CDATA[＋12.4]]></O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ListCondition"/>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.BackgroundHighlightAction">
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320@UNSc!4"+7fRRN(3UM/%%*UGt/rh(iF5Gb.$kdQEBRAD-Ke&04X'
BjHm".#3Hj<i!0ZUP-0G^U!C;!L<:Jb,7JnsAK#M%"(Fdi8.Y?YA(<n3ddHM-&RHSX8U0A[I
'GO*pP8L9\7IdN"KBolQ\BWes_`iY>/WT^IpfB)eq\*kopXfoB]AmI^4fq-jjKYHD%HRCrEhD
9_V2q7"g&XepO&opqGs->[I@er:W<Nj&IoG.N0A5/W$Ld*pb/Gl+LJgt``Io?,YF5k)p->7Z
4mk$S/O#_,;/16-_9L%\J09>A`b-gGa*O*0\]A?T2fr?GLN/D,g=NDuT\"`p<G#eph"]AmFXAh
V6.giQrTFG+,?oOe3)\uR;"TsSZm-Kacc)s%)o9^nWqb.qlTp-FmOX_65fErq3_Z"BAJ'1^U
Cu?lFqu0"W6rR-jY&R1e'0=*"d3fR)ZqKO[Yp1[D>.aOX5j*<!k+_AmqiEptg1K)7OPNdhb/
dqHbtqRC0N@)>CeJjZ%3l<NU?7qK#YuYe@ggk.[1QECTTOr![`@r**/cGClQ"jg3=kD7a]Aaq
m>Ou",P!5^[>=cqq0Xal`0b`5MTCAH?IQOl`5*)/`4as>`j5Qf>I3\A``[0f)N6WD:.i8W-@
pB]A3GllYPh;jETQp$%/t$\Q"@_5eo"r(*@o2D2d*JirI+K+WVuSO]A5I*mlegUj[Pm/[q0D"Q
_>3JXdke"A8+Z.t5P+l^eHc+<h:]AQMEI,L*'0BiT]Ab,QThDmpY%Hq>1HJoOMDQik'>BdpSX:
)buHHXN!(Y?q-E9#>![dAf"3.1,=T)iO_##A;bQZZ%09`P~
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
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="64" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="64"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-4079159"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-4079159"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-1"/>
<Background name="ColorBackground" color="-10957699"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="SimSun" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Top color="-11841939"/>
<Bottom style="1" color="-15066598"/>
<Left color="-11841939"/>
<Right color="-11841939"/>
</Border>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="SimSun" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Top color="-11841939"/>
<Bottom style="1" color="-15066598"/>
<Left color="-11841939"/>
<Right color="-11841939"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border>
<Top color="-11841939"/>
<Bottom style="1" color="-15066598"/>
<Left color="-11841939"/>
<Right color="-11841939"/>
</Border>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="SimSun" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="SimSun" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.TextFormat"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-1"/>
<Background name="ColorBackground" color="-44719"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="88"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-15066598"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="1" color="-15066598"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="88"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-4079159"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[h3nRJ;eakPB,j"-'EK59+t<rJ6pUs-J;Qlt#a&_`,"db)>3#Zn!CDmFd\LCb6&H4fTc[5H&-
tZm#X'(uOD9TeU#-!M38]A,P45[_qX`pmo=UWfE_o_M?rTq4IqnJaKgrr0YGPf_@J,%t-?A%i
^0)b:WO"elJgt'`?/,SQ\7R-gD\8#tPa21&P,./_sVk09hW4m4[;NgNPWsQ*WqUfnQdiSOE7
^LM^Q!t:ZcML([!&n+4<IQR8G55a8kepJmOkGgX&N5FIB6,]A?fH4O8lPR56]A6[3O;XE)+V-q
QHrjT'()<<de&?(E#]ApMo-TroFN^a``f7tVc-,LSq(UI#:+j,MBWY@\&M;,9;EOY>/!a%,9$
]Ad.#*=9jPbMYYab/:O[0TqYIai%m*(oP#Fq'>nc_jW(Le@5r.lj<\eV$U^?nBn$BaYj`cf4?
""fWY+(??q9eZp$M7`it2.a?u1g]A7Qm,>JYOiq1k99Mi8Y"q6$[e7$9M,`,sAcY7geB85JIh
m)uBd'X\I)tTEahiUJihlWt.ETTfnJpG/6ASkM,9>XCCI?45`ZI&M.Z:\5-R7'tfk8M7_P5)
%P>FX!,Tr'Ka*=(HQN8p.H\R^'Fd@[G"CYbCN/'qV*'T&p.OtZEX7KNul_XL7o"t1)S]A/3-Y
9'.rq7&D<JfQ865p!I0fjD:1t8;)E-Bo:<P[,>)9*XmeVN[S"Yr=e:s5IL7&K[F<tJZmbriA
THcVu4?Z_BM[t+@SA1nI:m1Q*nsDV)"*6AX-FPKgi0UqU#qTE*lIBga")T>1>a>Lc?k+H18W
^<na5?U.=tUm3hPe>)5Y`LM'q<j*q&o#U-/oXHNLOTq2r*9.\g-2\[>";8*cu_W57Pscd&$0
]Al.Veq?*RHQeBIh7\IbSm&c'jIVXVFm-CL\PqaRI5JrM$VB=(_B5J/'fp<qppE%FS<m+`MD:
%$N]A"n]A&uGgpL#*b@N_MDA=opjc.lOmFY+;g/L;\a`>/88@ao!O$f5HDc9GJ40e%JHg>>K8#
f_6bCHhIZ%L?rBYP-'b=F>WagVT.3M2V'Kn\Va=$8T^V:D]A+%<H(Vb`.J:,0n()GJ$Q`mO4-
f28Y,Q('>,Uu\3\bjkg81LA>5hf:>F0:C&JmcE[^?^A/hC=j]AQQP%3f[Mp#]A(NLmuS*;NFVO
X0.m(aQXMdtK%RA6f8*cl1?^DSmEFUG1:Tu\/KJ>"F0@%//!]A7A1jbh:E.4,<CjHmKNY7't=
q!!X[@\jBCKHK\\7Q,c^p(F=6/99;J*p#UdkN,@9B=7`:6#AC#%^?mJ7'1YIfO\Vp\QHPUuQ
<5`&R9"k38niL5CRj@g$NOIWppkA1+W8oKm4=M-:`:WT3]Af]AtcjDIf/el+K`$IA(iTXZnGWb
%B+V5e3>$:C$485Rg$u4C`hT"aU.>q\3mYdk:cWU#^01/]A9G..Vmn]A:PJ[,%B>8R!>T:Om8n
Ht)U]A!'&[\!uCR;fD2*PYoc]AaguHiTYF[%aC-Ij-]A7;0If3$b<*c`7Lb_4dF*r9"mS5?f33Q
.&X5+gj30[Bg)'%OJ;(E).,1(?+4n4U;"H,[@MjUSuZ_rAARg(]AjdW%&=mH:UpU0\5R$m:\.
331gL1l#Ehe-i(a*5^,YV!TWmoBC/s,CE'Yk-6OK?;D)'GFpliK&EY(3%;>*;jOO$+.#mo1V
NRIsK1l0+N(4[\]AjtFX\P0rq@;BPsX;Kf>74IgK5>lbV4sF32L+/t+/$ZNNaSltTe(ko/q?l
]A:]AjG/6GMd(?AAtg'aW.Q5,=J+N[5(&R-(;db!5-[N8`,'lH0l.XC['n/4b81/i(u2T^RD-e
S1GBPE5cD:5qr0\N]Ahl"P^"ILZB?\42$LkT4Ta$'2DEKd0*GCRnAbalVYb-kaWa4^j%iQdA?
;N-dkGVQX*CeY=9s2t9Y>+L5=c:APCr,i(I$TEh4e"i)mI,&KZaBda53ZM9th-$q_4lXCe6[
JUW28aVojYR("KKm4E#MZO`;@>QN^s(?p9GrG"6oAWT+6N3Nj[hl=:BW=eg\RA6OW!IKHqAT
JbX^&jb/u74O:Q,92U.]ADd(<Z)YbRQ#,obWp.UZUg+tAILZq6P;VoP.LTk!PKf;)ls3B0!Ri
LaURHIni5ALQJ7TO?R#P+^$X/TFOq272%#OXa'3E?2]Ao+=t7'80:>blL_;V]A(^Vd^+`$QL>P
U&:L^5_YD$^10H:!JZ[oLY+]AS=fBqr?S@Vje+>IcS%[f]ALXE-9=FNk17)7%2`rduUg#i2TQh
LhT>H0.F>f/Yd10f<H$C.aK_s3u9M94:iEIbd<P**5b,\O1.<BGFM<MW$K>fWG#7:fgC=8cg
6%cnM&jPWie>hUGdi':+5Lk?C)5`h<XZ\?@%b'OUp^:Rp')TZ&i5=G2=nO\M57OYIaCYsQL6
Pah]A7rYV9C3e*5WAeE(,]AX9Teii49(L`%[8C)L^eK@Fc+<ME25B3sL+mpDQWPf]An:BLu?"`6
&jNHp/A=a=YO9W_T)J^$[A4g36.b/fNaE2*sc[_lb4J,d57gbQqO,AaIdhLWBe:&)^E:?hY8
^0ZPul4%#*MLO_fMG3Bc3Q39`)YBUs2Q_dar+11#d!bA]A!1)QU=cct%;S'Mdoi(L-Ba$F.Ic
S^EE'>X9kJA$nb2r-bJLjpeF<BM;5Q6Mi\CVR:LtKk5!cte(*i\(jqKP2Yq/1N74Uc]A.oa@8
CEms!ERq4*HA+_tVd;FROIS+Ie^jNP)V.i2\[[JG&:e'&4ke4q@32Y9N_d8_H@ud;32GAWsg
)9UBASCh[^4]A(p>XM<Gjm!Lod3`_t+Z#BW>B0P=S$"7&:hU8k>VM7)*@5A&GYkt%b9IX'R(#
]ArC=A"LI)u3aWq;U2=a'Y[FU*VKT?h=8X?B]AKs"[hu`3.uX4*mXNO]AJMG>;cG'j\a"a4\.6>
n0urXK&lM-/oc`YQSW1<03`/`=.b9clL+"W*d#nD_IiVk7RkK\A@45D^"--Uc7fAd]Acp.P]Ae
(96)?F?(*X=7t@fsO"ft$af2%YgK5cOF8RS"o-'6:ju?#.bd[g=8H6";5L\ce,jG4s'(1UVo
4;:eV)Z1BN?U)0-CA@,/C0hlXIZ(0Wr!Pk3R'5D0XI^3NF$&i$MF(R>VKslIH'!eo=\'rbFR
Z>I]AS^&h.`'95LAp:eKePi=BNkfHt_s>Pa4?NK]Aa9d[$8Yn2Va7=0pr:3/AX=!+\UgL]AE9/!
hQ2&SQ3Kc.)hODuXNRl4T.S,2gQ-Hr4Qj>P$f?FfSiY1"Z.B6;;CC>7lF<VbXQ#d@IS1UjMg
LI=3l4O7I,bGo[Q6TN\Zc9#C)UF>`G?^Ctb2X4%u!g8!9(ii14C=rXDqibK6`url?Q9f3'^e
Pu:,86c]AZQ$ts\'JC#dp3@Vk4\!.hQqJ@?go"]A$ZEs]Al(sV"E]AE#'?2!+5]A_YcS8@hdbVaR7
o0d!^\l:%5[@@2=h^K6F9$%X-qc[&`s*2a8N>N[HWfe^[D@cO)))u6:tehDF.*,Y"_ceV[k6
]AGqi"3`qY>QaYM_9UAemhG7$bKs[s$`+I%C`F[1ll<p:-O_dH>crB=f"EZ4c%^^(=[^oU5>2
u#derWVe@p:i"0N/OmR)1CV#)mS_kTi!M/V(&jM=5([4dMZajTtl90J=*R*<Ko#V_-'bMX+9
HCl`kn/b4XZYCjn=D//WqV_Pta*n74OpKcT&0mqU6Z*T8lXb+bg:K&6fm/D"Nb.Pu2.`(@k4
K*eK^SEtHTd,FVq5"IGp@\Q7Hg%bEDkDaG'l#C6Q5QJ@*]A>s"^q(Xlf(t0W,NQ[/q\!`-eCT
>^`dPkJ^U<eZcb-3Ma)m^`ZMBk*'-S%Zb7OGahFK,76!1h?lrF[/aNP,\MX.^Tk8E<FU^Sm[
B=]A;mE)-Lr71E@eBH(R"M:fY@uL^2a/*IC7P$\0TQ0+J"!<]AtTI7RFhA9a8)T5;^:2\FEbHq
,#"4ZqQIA)or[a_b+W>MorpXm=C_Ap[4ZQcFl3LnFsYe+#Wa8,46UbE9>cM[?ug5dd#ePKY1
rNP\*Ha8Nq(q]A)XMr+T_C)]ALUL1$@RRMY-ZcZ6GOhZ"UXrW,Fc#fUsnMIOq)a<'G_gCubVn;
Pubc.DFD'W&+/DqD9(rrJpFA,,_sB[jPjBA2`Wd[0<)FiaDkX;RddgA-\]Ao:H`R5,t$ujPF%
O6P_5=(f_p&E)inG8#9hU:i:Wj7:h9R<6bfu483fq3m+;aI^&>"IHWD2md&F&C3j@>La%b0V
)aPqgj8JT>f-&:eX#3nP9.muoArp;!Gsf+H?O;hU$*e10(1`<$Ia_qfAsoUAQRca_PicdJb6
*rQCcjQmBlXN/mn;8c,ha0"8:@K5@;98pd[%+'Xi'EOda9HSZ;2J![\Lh$TEF,e1n&?7?0^+
P'kXQ[)B[\3?dZI3^"%A-Iu3LgC!XF&`6ClS:]AR!YQF@kKO-H(oDQNu!pn',g@5":G]Ap'!ir
LKhV+%F,BUR7>Mfp\\[32EmC/KB;C4[H6m?ZW!:g2+@KopQP5jKX218bF'7Xla.hF\e'PjuG
/;`I8$AOBCf$8Ss#.dIlq2X(.R51SWU;qpgMq:Xl879u5YktUVSp%b]Ap)!Ju`%a0JFrH1S?K
V_J\Vdn[Lq&NW8@0g5P6!>*nK#c$3mYjU0a>XC80@;a_eDaVt;U6f@,I#KX/`m''j7_uFU74
K+qO/3>cGkMf*q.@G71-:?8I=,]A'/'N>6?*p.(8m;.$i<4cZ6,^lI#@RFR!2?f$HI_(1NSt\
Vtqs>b<hjKh!t)RP@b5HdnM\4Kd/s%`H/WYLVB/j6S:!f"Yb`K<0kJ+*s(2dW,Zd1\qn)Npb
UQHW<820;nQ"9@9DW?*WNcW?P`Qga.Bo4"9YBs.`$t.Z6L'K_W#>O%J$SQ,?n\sm6i-GVWCt
pk![JU2ttp`P7Xhu2;\'Z[o8"J'hT\nQNBm0>_K'O$g:Otqh0ZQJ&TO"'%mQ<RoK(t*.cEaE
)22Ok[`fUbSU/Nku:eYVh)MC8)B[g&t*HT;1>!c'(YY<>DgE5n+i\&\kHs&f"MKb9]A8;]A]A$t
ELcllF\=B.f_51U9$[c!s\,nQeMh4EO0.bM2%Q%t?WqX$`^..^3I5+hm3+=@:KR`B;T0(31]A
>QFKB[ZJBS^RiXoG*e=LP!j.c^;@3<cn\-m]A832_s&hVgjIhZCfT!bZ!%n#Gf+]AIF==D/<lQ
OQ\ZBHfbcJOX+o]AY-f[C.QUFduF%7;'@,D7?O7^ULj%6qeMXKF;?k($1]AM'<GRt(M#toT9!m
A6H1i?hJQ23bAX)c0,E1e#9O^5-JYn;\0`#e1PZ\45">E^DFIc8q1tOjg3)^Db<YMEp^(!cs
.t-gZW>kF3>#]AqJ=36gb73O'5(ZZ1A<A0Y8F7AJ'q__gW@K-kSTel,I$HWO8\;'DBNiUC*8o
-"?,_aAfK%JMaKbXh%fQdX`]Ao-VdB9aZqZ:6TMKQu79f!*(e>CWbX7hk9<P1NX4+m+bS5gG]A
>*XX"c:/dg>]AGHJVX?`TB\_N2K7c`E0RWJ!/tk0W#*;m6&>CCbDaoEOi5ss;%#/j^F??Ok%Q
">Q1J4%(EfK"-Of%\6;2Ti*>a-ot=#:N"[)A_UhcXqs>?_:nGaFc&;$a.:`qiSGQLHQapFhq
.nWfbkW\H>C5+R$i9"B_8rftsb.g30t^9Nd#Cq@hS:;%4thOM%K&hlEhB)P!o!2id9'#g<J-
8Iu+c>ec]A.8JK%85^[A\bcAG]A7)<RD4;6l:R^SOr)Yn[*![SPg,7Y`AK'eno4_M2V)g`pGL$
VY7W$t*A*R=>e7)rW&+X%W6LYBMTMo<@nk7sLW7kp=$7W8fD5@jG\XM#Un**%#.\B1ER=9?#
_]ADMIFF3]A&^'M"RRbaBL4Ro:Hbe0gE4R+uWSkc/JR0D9HGf4)+jk+(l+j:fb7=r7;<j]A/7eq
%0'2J9L8]AYrB/1hX^WMNT9h[j1tTKL2]AC5mLo8r:H1tTGbGqm4g:TMq4)r\TXOk,LN7gWph5
De[,S,Yj<l>M$S*#_60[QgZ5'+[i\!s,aXe%cZbTHZ.iUsBbl:hA.+'O?$ZcjY)P'SDK'&:7
47R=pChCFi$a7$]Ae!^.&Iff>'"6iDr(:';7uPEV-:=QW!YtdUa)\1RPA&Xt2-ini0o$)`F=7
jkEQh1<aA:]ArU7m$.#Ti_^_+!:VD0!1>f3ZuX<,XN9p(L>!:]AN.P]AAd4h=(W#HpaFhf<]AY;A
2tBdYf!VP)d)LnJ>Tr7BNFa)c^.o66V&??hQLLOVpR/r6SJ6QHU)e]Af'm$N7r7%E1f+p(f(B
O9A5(deg"ZPPZ[]Am!KPFHFN=I&d##<PC70HaG1jL3'0W]AQB%f/<Mp:84:_Wik/Lq8q>mZd-Z
83SWN0`Mq3[l0>Ir"d!V%h.$"YG!N;D?0<^4q)SsTob2iS1OE>T`c7KZZiN'R,&?p$K;XteK
YC;l@I41NEDQJr_+d3(Ab*s]A=OYX8>Ke?[j(Qf2MaE5#SjPBsat$qGD/6HK/t!WkAmGB[09k
Us?m;)t`&QAn"tCLn8BJ>HiJ0H/T'h*d+rKgb&$ecG*+e2>jdmueO5NhiKo'emNUn,hqXo6M
2MWMfG;djqY<N@e2rP%u*Ag^h!p.>Xf3<"D[9U+bkNp)Q4]A<KaE4>>fg#L_t^sCF4`o!"f@q
oq:'2ZgU#=>,ZUC9%hKG[HdYWhGd.5W7.TXXcd]AT)>W37HW<!(%0c$9rnT]AG">>maGiSa=u*
E!g!2(cbH]A4C?]A6[(m6dg!uT3]AH(okiMTU/&2=W;>jmVtkhu3eRR!oRVfVYq";M?N[I.QE#@
LbPg^.it=!DeTHl"`]Am?[-_)mIKMB#pc^eWiLAF2kVB#3p8#C[n9)b_<(!AXHr_7/pZ?`nJQ
eW9u20@T<H#O`7)c09r0!5cc,!Uo+M8<oQ1S'!8a.DV+.@,L@HMJ.VRVC\1.!617#-`Bc.la
&.;K[-i;k$nF>ccHC)**Rl9%s:C*[<8j@PF0RC<os0\+N5QKPhGuOBc+ZTM7,fh@<g@Y"&g*
5(54Z8`=%EeI[J*rO#*BF-G!QF+c$T4baBO8WKoFgO/'P,)4\4JMsRk/ga=%6Cr]A#+JHc2QD
Q_n\SQ#rDE=npItD(*Nu)1/5`*AN9OE_mkG3o`#@t3=7WqUJO=3l(&c8JK!<CUM\8M)pGEUd
:!f:r?;gKZ)-:m>]Af&BF"3/"(<`ERrKU2IJf;1*n+;@;6;F$=&Y/R[`6#k2kf^*bZEHH$"mY
pP"R4]A/;R]AY[JsO2ZaigGtWe6V.`E.F]ALTfSd[0k,2orlA7"kVhf@AeU`/8`W><(*Rn1meE_
k@h]AlBpQZDTnj1fF6sq@,3[3LeNu%0SFbn6mEk6J4H:>@QNrN*Lo3+kjYji;c2;_&L&#bf9!
Gon#.7R`6fn"+]AAdc8>V79,?n8"RYgceMBcuZ+E-!I?LSb+f*HRY!.:dT-*;/)g?JD*\H:ju
$%W"[Z7@U3'o5p$h;`,@\*]AdnC?dT2&;bY0nQQ_fU*$NVm$)\SWI?XdR.h!<^ph!g4B8>tKn
lK\K.I?S<n'p<hG'M6I:WqdOF#-hK6%PJn.*lYs4t)r%f4T2i2pV-`!KS:+<Re29D(2=hi:t
Vem,/a$@Fik)V"B7?'-j]A#_WtCGE9i[bPu'tm1Uk::0n]A"@8/H/&T99A;H?mV`0;2l9a*FM-
8-p8qX4R;,E`KHhp$?GZfBV1:q78?l7]A`ck3SPs20D%UbZ%a0OQ5_$Hq5R++E8d+O8=+8g_L
MW%`eMHHcF4L6o?i>7ZC<`X_&ks=6AoI4(?B#0J<4U%"ZO:<OUnF?^r.Tt,7U@M6&CRVKWOJ
4<4;1.+I+rGc$i>"OoeuB-9d$32apK4#(W_&lP-N(Cp@rq9^NETk^8(,S`isS*B7o'F_kb^F
(&I"Q(YMeBh)n&9.$IV%Qd"k_,,^?C_%E#&*,U^oQkT)W33dN49iYWfrdFQO4!krWUei*32t
$G'pi(pXVqr88?X/TlY-L=`6k9&7=8P7V8K&2=#)T(4%O#NoU42$PXl?)1k/AN4^+(<lZC\a
]AHMH\20&XqBtD?I]AUrfah7BaBc_2P:IQ[eWHZm(_hi.1!@Yf?Gk8&J-XUB`lp$;b:mlq.[9t
\*VD<s?=H1!D3G58CE`)puhmLoEQXEe6.VjhkM><CcngP)"A4]AaM/`cf.EHZ.T3U2HP5l$!T
=!I&eRD6ib4H8-(rNVH)&23770$<OkTondZ1'nMhd'11uSfBm5EOSpfPgpl$\ZV]AAne%p[ad
k$!jrWA/l9o`,e`u2T':$cq8(-nJ1#-gqaO4WD+s%76i<\qLrMCuO7r/s6:kIc%aJ+[)hfrL
<_+'se>c<9<C:jD\oYii#.h/QSK!JLq=F#-UTG]A)'=O5HO"ki^l]AcV7pf0$@hAiL8O"8>j`j
"h\o.Qf@m^YinR.8G+4N[W=C-+up9]A5:ajaA@@a!,sddn[8#QYr6Nfq`JTE\R;,:D7,BBEO:
qZ'3O9YAc(RohYO2oNYdEEY0-s^ir:SD:c8(^K877-)35mt:X%3.Z&f@6[!+HuX2s:!di;E7
05`ZBt=&2PL9O"K;h`F4$Q(bJX>/J*p)op)G*%P#=TmC,JL)saJ99O;'?q&42F3'mu`Zb'c%
_9o)afWN*pI,S&n+5KGTFM:qUun8JI3?XRGdPebI80S./++9kOMbVXC=(bd%fQY[>(q,MN)j
L.2_o_k=:ula0.lWtZ*R.7T]AS\DTj#HL_3XeK$eDI#+agSA;N(7I_Wo;6Zj`.d`1`Jefe&g5
D3(0E'<8tcM6"fhrUj$DYm&NIs)@21&73)DTWfro;#a,+3uACT6gkC4#bU&886T3EpBRdB==
X?iHOq"'DC<[>BiRSSQB.(u<\XrDNkTVhE0b+r5ki[3]A3"k,:b!%j8#^3sk,_)L0$<Un%%CF
jX%#5$g=kg)h=jYEDs@K]A]Afodek47\\_<%e>#L)A4[ppb+cPQ_[^>U61AofI1>?O8oH#N#VO
:'=V[cis)gKRBd5O4auii9l5l=,%\Q)Ni,99O6*?sbH^Aq2qb_IU)H:$39#Ps:80[F_X*:o+
_-c#KW[G$)_pr*dhSW-.CQ[2u5tm^t?sj@65Fi4D)u<\]ALE&LbDNe_Y@^s7U=#Plf44#`E)$
dflSkE0%Yd*aQSGm#t9m5;uVWb>auQA\>C=0l?1,MRFafQqM;aQ3=HYmK_AF%cN<.DJ.n+2X
o-#_Z#^E^8$OUBHV0+m]Al^f(B!8`6pRMtj0I"2(t(D9">1s..HP\iE=FR2lkV5h.>0`e8rht
'WA[JH?AM/MBInc`<?q4M0$mDD#bU$sr!M^nAL#n2B?TthAWJqXbgbJi]A#gDMZnTuEppcfrg
u(9o6L;9'/=9rSZn-uo7DpR/l`Bc1H-A(Tf&s?CWL2,$Pc@>k.ID^.7]A:NVEX?h:&)tK^6FQ
@WM*!Y\TH^h(s0tG4"<qGbVI!d>H1N1D^,^N^RV&1[(`N!A`D668$q)q^XmX0@8m#T#,&!T,
3Bc-LlTWV,E,?f.GVa-1aJleG8IhQ%_V@*,#i>i8UoKn[Sqc.]A$LW3&BP$"43(9,Uh`QIPWu
HAB\#/?#o-&nO]Alm-[:!Ni>q"=bSC\Ulm$RdLQJLm#_>j`KHk/<4490_ufa`omO_I9'm`^f'
Q-#-%8(NVe:lFO(@SFK`rQ+K#QBhYI/r,H1^H?S3Clc0jF\F*eG#TUst]A:;`WQ]A""fC1Tg&Q
8+h"hV;/GLH+6L='W,*R[>MQkFpN%p`=jj.4GDBio?VY>&c!1jNde'#W&*;G,hVR7bXpO9FJ
X=qr>K3e(b1VE09LrIr3(!)[m1T[:aX7bt?fe,#%`@IpV9aaW06:EJUGLA^+>[n&9f&^G:2[
d)92H^j%`.d]AW=\UU^(oB$8=@Vg$`/90a*k[,tNYXtX(5Fo"CCZ$lS'Gi8p01t%ZNkReo6g7
8('S"6/qW7>82LNfE?7pQ?L.`DQ]A")bAA-&[KAII;l0pQhBjBKL17&5j63J,+i^R2@W-M1:p
qp4bm`djA]A)i[bBSm3Wl&C5<_lf!KV`[,*CSo-=g.6pscaMOE8"&S`i(_K7jHTW*A&Sick_E
#jt#J?r-P;^\Ar0lcF]AG+L]ArN`)Gj>i9tmi<bnK=RgInSmC-25F^tq#p!r(0D4(F"YYm5U6\
U2Kq9ie!uA2[WA7IVbXGqcgbSKn;V`2n!&c^<J"098oZjLrQZ?1-2S2[8+FcfL;6E^K-%NKm
0_4t0Re(au@8k7hC8cK11E-JufVmPJ]AkMN"#.GfE3@KP[3+GQu5Q3XW_)s-oC1`J7,H)4/#M
c/kHaan]AH4q1!6H6#Shnfcq;dMeUPc:u%K(%5,D9am$0H1P9n0uKS^0AS%X:p?61*uTPn,Y1
/Zl5<'g."RrP!r8`jW#@31rtgH=Tc-2O>%6Q+/Ap,[WG8hq63K/9b;j:%U!bXY]ARM1T09O9r
V&^,S#-B#"@5;APe`nAXs#.E_c]Ao&cncnbl_6'r#]Ar/Dg,D-+:Z=]AS=e1,@RpS^Q!Hj+R[j0
28g@H%WM0YV_$3L%nY^7t'K?>8\D0s$PB3tcK@V5EhZZnJEHKLs`2_B]AIMDijt[Cet`"/d@:
=TArrZ7n.d:s'^n/V($]Ab=k-c]ACoZW4qX]A;i7Cc9qR<9J%j4kFGiE745mIh*N:d^0fV3#>#E
HL+0)E"$Bajrj^Z!C1,SYR4?8V2M3m(K(772cg6[*b-6p#`a2Ih7*gN8K8n#IR?>d>Gp`"Ho
beU`!W7P5?BI+8AAEC>T\MsHCdkLnQqF1WaDgthVf`,g^b.okE&NP4i\G<S3bUKM&!QAD(O7
t;#c:X-^MGG)deAq/UX)9!*I[;5453\eC0NpU(IrWU.Hrdck\GM,]A_kK@SD_@&l!\ml+`^QF
-:@XdkImi[f.#FT6Ojno0KrUb$C`02>P>?!Qhd[m;\^0gae&;M#R$oIdkp<H,R@N2EGY/6ha
pR+$^=PW@<8Mos=Sr+B1!dbr_U=1L)kU:Cehuo'j(aE1#m8f7noqnj.Sd1QtZ?mN77X@hCM?
`F/W6mieHr1/&$IVN\q<IG$^=<6"?Ja@N+h>$>G1XJKkXR%5g/%r3lKmH<!'Z+h^>6s\esKS
Q)\XE4\[&Zj0#g[Rs"oPWKlh)LLWV+"@;AMKLF[[0$l/QjT**g8Y?d9DRB8^(NaiNI'Y?b^q
XO6,).a4uYb,i<[fG)C1(CbleOXoLpH-;JEiOb/>kX+WIX9>+[GA"?I/UObs&e@N"#5(gLR'
A2.;+j+K`D):Qa8Z#p!=&KN8[Xs,jku)kEb)8cul)t5bbIQGM^&(@Ef>6S,)`\hoeU^`^h(!
D>hun3Du-'Y5-hC/Zb$jI=T#F:"_Kpmg'?,Qm2&B2[q%\#H)G8k&o:.O$rRS&b``1KN`T<@0
Y'KU?KoI>6dp,rI8n:5f@\FR(^L-LAZkqn*t-VmkaL)YE6G%[3PfQ/s4YBs)@-+<@mFWqED\
tV/TcLJ0"3q'<]Aa<B_?Jp+`kOW71*(,:8J_4d92C`J%nd/%'jm&3K:a@qU\lo*Mo=ml"(8[o
E$?7J*,l/et)3o8K+?6Au-<fDL-Bp4(kRGR,=t\;<pFE]ADHcM2Mg-(B=/$E^8LE"EA[/h;Z%
mO<;`%u)jI64]AUkZi+T'8^&"qn=+9Mj4Qn7+P=c[(*c7fC:`V'']AHG=gs$([t`]A^;SL]A?Ya#
A:We1B/q6#=l5:,lJO`^p@hsLZTQ:jg]A$ds,Y[u5.bE+orXP=^E6bh8eb)GF`@BCNq9B&,[p
jT?gZa2kc#Fr<5GlBg:Xhcc0L?";M;N0olX?_)m/Qk:50/=YmW)FZDb7(2FDq%`6fmb=:7^n
p_q^M6OjAbgEOK5.jQnfdYMLFXh,V-P$YbOc57ZVQ'?__;_aJ[@XlP,,eoOIq8]AbTL7PBA.A
+5OYjqoVBf@P1aI9Rm$q/8$;pDm<q9W40)9`IZ(F=.%V,g']Afo\E8@#Ns\@U%?fpIsa#!#.Y
nL\aAh,dU*kV.1YXOo&9IdR*<_V8_DYfo\bqS55e7Z$go:;^[:P7f]A4QUqnF]A"M:#Y!9-I@\
^8HkpE^YIVa9MY%aksUS7IO$TEhd,-Zbq#:^M[/C63m:P"*JCPpNDuNQJ6J":"dAJ9"c5Z27
%_Uc@><T56gt'c6Ooi#_7JsPio^F.Hm'<!L`GY>H@h"bNJNGWdMGOD;MCmJD&hW$7#0A(A]A[
_2uT%6DHAds@qDOOkLS7E3[rWlCZW>L9:c"%7__4XXjr;YT;mALEZ)"f`ZC?nDnWn6)V7S&_
f=Q#]A&+;f?D"7sW8*n-6)ni.m-l`8-__[XD\7(g=:L(ho)@,3#=YU82H8sk^&Z$%ncJfhm3.
\d+(c5JIuc2Zld/8j</K[SP/[+W&'$l2Xp5"Ug6[EO?B\WAmO'Ud_"f]AECN?:;pZgT7"T~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="353" height="316"/>
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
<![CDATA[288000,288000,1152000,288000,288000,1152000,288000,288000,1152000,288000,288000,1152000,288000,288000,1152000,288000,288000,1152000,288000,288000,1152000,288000,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[432000,3771900,3009900,2304000,432000,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
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
</C>
<C c="3" r="0" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="0" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="1" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="1" s="0">
<PrivilegeControl/>
</C>
<C c="2" r="1" s="0">
<PrivilegeControl/>
</C>
<C c="3" r="1" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="1" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="2" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="2" s="1">
<O>
<![CDATA[北京分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="2">
<O t="BigDecimal">
<![CDATA[3286.91]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" s="3">
<O t="BigDecimal">
<![CDATA[-14.5]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="2" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="3" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="3" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="5">
<PrivilegeControl/>
</C>
<C c="3" r="3" s="5">
<PrivilegeControl/>
</C>
<C c="4" r="3" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="4" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="4" s="0">
<PrivilegeControl/>
</C>
<C c="2" r="4" s="0">
<PrivilegeControl/>
</C>
<C c="3" r="4" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="4" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="5" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="5" s="1">
<O>
<![CDATA[上海分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="2">
<O t="BigDecimal">
<![CDATA[3178.88]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="6">
<O t="BigDecimal">
<![CDATA[-14.5]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="6" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="6" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="5">
<PrivilegeControl/>
</C>
<C c="3" r="6" s="5">
<PrivilegeControl/>
</C>
<C c="4" r="6" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="7" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="7" s="0">
<PrivilegeControl/>
</C>
<C c="2" r="7" s="0">
<PrivilegeControl/>
</C>
<C c="3" r="7" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="7" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="8" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="8" s="1">
<O>
<![CDATA[广东分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="8" s="2">
<O t="BigDecimal">
<![CDATA[2875.32]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="8" s="2">
<O t="BigDecimal">
<![CDATA[-14.5]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="8" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="9" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="9" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="9" s="5">
<PrivilegeControl/>
</C>
<C c="3" r="9" s="5">
<PrivilegeControl/>
</C>
<C c="4" r="9" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="10" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="10" s="0">
<PrivilegeControl/>
</C>
<C c="2" r="10" s="0">
<PrivilegeControl/>
</C>
<C c="3" r="10" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="10" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="11" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="11" s="1">
<O>
<![CDATA[江苏分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="11" s="2">
<O t="BigDecimal">
<![CDATA[2377.76]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="11" s="2">
<O t="BigDecimal">
<![CDATA[-14.5]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="11" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="12" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="12" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="12" s="5">
<PrivilegeControl/>
</C>
<C c="3" r="12" s="5">
<PrivilegeControl/>
</C>
<C c="4" r="12" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="13" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="13" s="0">
<PrivilegeControl/>
</C>
<C c="2" r="13" s="0">
<PrivilegeControl/>
</C>
<C c="3" r="13" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="13" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="14" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="14" s="1">
<O>
<![CDATA[浙江分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="14" s="2">
<O t="BigDecimal">
<![CDATA[1895.43]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="14" s="2">
<O t="BigDecimal">
<![CDATA[-14.5]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="14" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="15" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="15" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="15" s="5">
<PrivilegeControl/>
</C>
<C c="3" r="15" s="5">
<PrivilegeControl/>
</C>
<C c="4" r="15" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="16" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="16" s="0">
<PrivilegeControl/>
</C>
<C c="2" r="16" s="0">
<PrivilegeControl/>
</C>
<C c="3" r="16" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="16" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="17" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="17" s="1">
<O>
<![CDATA[山东分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="17" s="2">
<O t="BigDecimal">
<![CDATA[1589.33]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="17" s="2">
<O t="BigDecimal">
<![CDATA[-0.02]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="17" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="18" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="18" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="18" s="5">
<PrivilegeControl/>
</C>
<C c="3" r="18" s="5">
<PrivilegeControl/>
</C>
<C c="4" r="18" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="19" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="19" s="0">
<PrivilegeControl/>
</C>
<C c="2" r="19" s="0">
<PrivilegeControl/>
</C>
<C c="3" r="19" s="0">
<PrivilegeControl/>
</C>
<C c="4" r="19" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="20" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="20" s="1">
<O>
<![CDATA[四川分部]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="20" s="2">
<O t="BigDecimal">
<![CDATA[1289.17]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="20" s="7">
<O t="BigDecimal">
<![CDATA[-0.005]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="20" s="0">
<PrivilegeControl/>
</C>
<C c="0" r="21" s="0">
<PrivilegeControl/>
</C>
<C c="1" r="21" s="8">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="21" s="8">
<PrivilegeControl/>
</C>
<C c="3" r="21" s="8">
<PrivilegeControl/>
</C>
<C c="4" r="21" s="0">
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
<FRFont name="SimSun" style="0" size="72" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="88" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-11740572"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="88" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border>
<Bottom style="1" color="-2171170"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="1" size="72" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border>
<Bottom style="1" color="-2171170"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-50638"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00%]]></Format>
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border>
<Bottom style="1" color="-2171170"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="353" height="316"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="report1"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="false"/>
<Size width="353" height="558"/>
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
<TemplateIdAttMark TemplateId="15e16e14-aeb2-4819-a3f4-ed3e0ff0b9e7"/>
</TemplateIdAttMark>
</Form>
