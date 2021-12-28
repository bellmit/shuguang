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
<![CDATA[SELECT * FROM MAPDemo5]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="PDp"/>
<O>
<![CDATA[店铺一]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM MAPDemo5 where dianpu ='${PDp}']]></Query>
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
<![CDATA[SELECT dianpu,index8 as a,index21 FROM MAPDemo5]]></Query>
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
<FRFont name="Al Bayan" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="5"/>
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
<border style="0" color="-1777440" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\'  \'+\'店铺信息\']]></O>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
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
<![CDATA[1584000,1008000,1008000,1008000,1008000,1008000,1008000,1008000,1008000,1008000,1008000,1008000,392762,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[392762,3600000,3600000,3600000,3600000,14744700,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" cs="4" s="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="dianpu"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="5" r="0" rs="13" s="1">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="pic"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[toimage($$$)]]></Result>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="2">
<O>
<![CDATA[铺位编号：]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="3">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index9"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="4">
<O>
<![CDATA[业态：]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="3">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index4"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" cs="2" s="5">
<O>
<![CDATA[合同状态]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="2" cs="2" s="5">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index5"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" cs="2" s="6">
<O>
<![CDATA[商户名称/联系方式]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" cs="2" s="6">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index2"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" cs="2" s="5">
<O>
<![CDATA[是否主力店]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="4" cs="2" s="5">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index3"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" cs="2" s="6">
<O>
<![CDATA[合同编码]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" cs="2" s="6">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index1"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="6" cs="2" s="5">
<O>
<![CDATA[合同生效日期]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="6" cs="2" s="5">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index6"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" cs="2" s="6">
<O>
<![CDATA[合同结束日期]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="7" cs="2" s="6">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index7"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="8" cs="2" s="5">
<O>
<![CDATA[签约面积㎡]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="8" cs="2" s="5">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index8"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="9" cs="2" s="6">
<O>
<![CDATA[免租期(天)]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="9" cs="2" s="6">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index10"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="10" cs="2" s="5">
<O>
<![CDATA[合同到期预警（天）]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="10" cs="2" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=DATESUBDATE(todate(E7, "yyyy-MM-dd"), todate(C7, "yyyy-MM-dd"), "d")]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="11" cs="2" s="6">
<O>
<![CDATA[租金单价（元/㎡/天）]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="11" cs="2" s="6">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="index12"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="12" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="12" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="12" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="12" s="7">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="12" s="7">
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
<FRFont name="微软雅黑" style="1" size="112" foreground="-10642722"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="4">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-10642722"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-10642722"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-10642722"/>
<Border>
<Left style="1" color="-1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
<Background name="ColorBackground" color="-1838849"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[e@^!)PA.KaNhR`RL*/ph6n&V%+:&6$Ys>*VMb6o6WYB.J6AUX'<mV\<%RpSs7VW3jJ>+/Y<<
>K0OWl(p!=U-5&Fp!;c9"#\rk<O+3bNm0O$=_XkH91#CL>]A,n(b7X4=`$..j?)Y+Dte;b!nX
(,="LZE7a5Hr$V(bkP>-K=u8<`dOmY1C(NQD"ci"^,s8ZoMc;mTmH?GS=hmV)S^PpC&%lHMg
MTlkLr,[3[\`)s4ne2[1nD"MPIKZ!hskQ.Tm?2=i)LJ+IG,d\lIU_q2dGi(ZSCnp^6;]AKF[`
n0&T'!h>c$_\IsNtbADTR%b92&"'MM;)Cos2Pcq;BKqp`s-)RmTgTq3qSL=*LED"st633UBO
Lf'a\+2CMl0<LSpc`2(:VUGbIPZWc:jB`R-QMILYVj_r]A]AS%5d$hAmPDT[Y&6:)*)WIsrBAO
#a>a4YEX=JtoNT3r,2o"R^FZ"]A0=Gs*dd+:l[T&d>45loP\3R*]AH!d<-LO5U4b-!<p`]AH_6o
4k7>9%oJK`%.NW/f'nb/d^GW>u+X@e;!0MU=bF)pqdc4oIDXGRqljg\f4eoL.W7ebT_o,6%[
^fmE6GEtr;(Fhfs0TShLR`d;p[5sIH_9S)g4'R]A'=C%Om*<,U:rX?OI;i,HX7m5XUNYO_;l;
r/<KB1HOH#.4eY.hqPZoA+%,M+sDHd;MN;EuldFue=p;*m.p?o$h8X*4>,]A;,e0J3H:lKnXA
Ihbqqi&aqW,n<2,PXO(e"7lpDbr!%*c;<8'be]Aa5=UpNX%hQ"aYF.7]A>oYmS-m/_6C-96MdP
KgjpN^8Is,[P^G!,_jF;C"'RFXG`bd]Am`9?Q$R4r'U<CXSB?hj=pU^R,_tW^5[+3qb_$MZ4p
u*"EttH$abpn517iU9[o-8KSVULY4C7OQCZP?kt^bXo@PZ(ur?-9?30ng1b9L3Dm/0n]AG72e
#<>RE;No58\E4O_r<o4?/M.Y2\=epYMF>KRsMYa\8AI]A&UFh`l%]A4Y:O63"RY1gZlCs0mS>'
<;2AbOPJZjRiE]AT]A).^@WkXhJ"p8IEVgpkF!d5W#r^A;La::d)FjXi!TJie&,9Z%4<i4\2K;
l:8Ti^YhMKeKAKQTBH^!@.-*l*qR^kLbm6-IpPo8.<*V\NZl)qqY^!t8OVA/Y8Nms+R`'R_>
3`#nD'h,CHi6:hq9>=e;Ug1M,);Mjf\f9gR2o8ftkPRrO:WkA+Wec8%Hg(Ns2n!([J5I,pI#
As6,[1AI(HUdI-@QNW-P>\$X#&HQTik@RT+<nZB5;]ABC*uDhPj_Utf#aDf1'mO52FRC8]A,NX
[6(>iY76.-#SY!RgZIT0\#dUGn,=LEDiHH)0r=hc0YFCs39dFjpLYBpi%pL*6d>#7CcZs[pR
de(BnZ@Stj*Al.'(rqZ"Rt1>Jl3j+#^-TXp7c0j=uqj7VlrN'HiUKcg$Arbah_n`Be+CP8\u
]AtlW+2dN^*26+N@Oq?`8L`TNSG^B,P?-Asi*cE>SnD21@3MT'o/JOo,3bYrWoF9S!j/"`93T
ra0JIAddp6TSUmuN>(jZX8^W6r-:Ft3f>4VP&Dmp>tMmiV`9hX3h09E+V(iZSuAU&B@_DbCc
F`&nbG8Df7B4oSeps$.4$&[;t.c5RmMR'%7XEngH3m_?KsqZMC.Ha\LV$QN2(Bt<_?U$>o'+
rKm95AJOY4hs+0A8/bpr8mU1kJ.k75leYc5lMt2<']AC4DNMC#+.KRkpiFbi+%#DY:M>[Ub#_
_1#IAc$n#E!80bY*8bPO:LnQ/NYM=icXRu8a\LDTs\S*WX_&@`_Y^Xe)Td`<.>1ejaX]A7bo/
YOfBu4>s3oa5UXgN^+'AWS@EPZ019pCEqMXbFqVo//TpBRZ*lh":ks5F^;T(S9"t?f,ts@Hs
-A4AL6ht(^kAg\`F+K#hW?U(ZYI<BX<mJ:Il8)4,n3f\H8U\.iTR;U7japIm(;dBb::@GL&T
ISHM7i,7A_DMn)B(LDk0/:."]AK+Z;9C=d/<M=i@l&8K(%rF%f7Ele<W'm_Peg5PESd-W]Alpm
1.AY]AiZ6+:=-F>T1Vg]AH>Wkjo/dpK/.Le<6f]AlUF\C?q86\#X0lqU9Z^O@shOYb&fg9o+U\b
#[.Tis`<i_k?k#?NFW:fmI;Gh%JK[uhf_rbBtJ*=tK2[sC*hq5uK<uM0uir\-pqUM^Q^ZuY/
:g)WZG8@C%Bdd<!"U^Cljh@:R$qH;KSXrRiA#BO3>96!:opYchAYtg1!:=q17$qV88G"W0rU
k(+iIYY67'91J1uaKLh]A+8]Alu2m".^c%Yj@iN5qePpfhc-TP[d+==OsAh-:><m-MV%.:lSYh
hSG;5e+PGH)`^QZ9S2p2cr+<\UM-:j1a;n%AV+%C5[)\APDbR'dp:Mg,(HdskGOADZ0,N0"^
EqP^HFnKY/s'G"inn$WMS17q!3.O(+)h?F;QYfmKMe7Jd.!2V97k2SFPcXK,%9($%:4$hJ>r
u/fbZ]AW$k9fl]A%gGq[e\,f+;\['@6hR"2dlWCTIZ$0g;O4gR>ToAaTfEC^sYi\7DXGYm.NXX
>4QQ6#BeN,(^ZDLI5ja"OoLM\h@'r;b/LOVT]A>l>Vi:rq1K=\6ir[JX,<ht*Gp6g(pXAJ/&"
(!6#6>M",%oft3fY`A2OG;pGu[W53DJ*o"7]AA\7$6n__.IoQ8AVB%^_UPHV3;SBi`B^'O9^5
aQUaJm7mkc_=="3Kp=DBgo/b65Y/.%k9p6?_hZ8]A=Va@E8RZfmJ>A/S4h!NlFA\HIcL(9#]AI
S.]A!XXNqJ$P?F)=3JO))>V:n<o8q=0kP"odGo@GG69!Lot3kSE%kgl_S:*;#@BqTIA6P%Ro>
6-6V3]A)P(L[*=Cj!INeL'JlXs-;7ZTq;e'RPh9G?5=T6AC7W(Dp/6LUa\0@:knbjE)_D<FX;
*Xm4\s)Q!^[BENX4D47V"6(g0'UPk%dVm1O<qRhtCj4u00gAXK9Smq>L]Ab)ho)n)k"pC]Am=X
-=`l/+.j>V%-$@!34tV!;O*d?_enM;<#[%:+LG9A+o^\"6=U)I9u25^tYTk8IVF3D0^-X<4f
'Ik_->)t<[T@hY3=[J]A:fM%\6!NKR"onZ=!EeE"^AfFMBsrJZ0rG,.E8A6lna<[gl*))[U8M
K1q#m!+=tbC!\SCB&3aCKL*VR%,U@/ctNKP]A^]A]A.@7;Y<DLs9^i;Gk%iI8e(Ai19*X)7hCYL
XnjhekI"r1L&(ZSr>X<?#+k:=-mQH6PdA_UQl2%g*A`!kiGS?rAY(LX*k/C:VS>a6O'f]A8"^
@Hc0/jn!p/YGK>E7dTT(P?8.3_LR0-<=uX-O:fg:[k8Z%T\AfaP'%Zud+f]AH8T0$[.>YQ]Aj=
p"f<OOBMQc?_a*6&S&BQ#c1Ud-Q&\N)9b@H,,]APm&Q`D#MERgDpM-9k+a]Af41-d/4,Bs1(a+
T/f*P@bTCOe#i0Ch9,qcp.mpj7lJ4*@?YZX\3"%M8*)Kk$@!U(p,A<X,`LENd-UT[FP*s]AV]A
Y##]A:TJY5rXuI3I4ATU0[Bjg2mP[hld[l^kq>a8L_.X*UIiZ,.k4kD1Ko*4d\*A4/ARj=dLU
7Jk_r`qNaU<%R?Q.l_4YJSJ`HqqQ..EeCfu>BIDe+cH0jdRbJ0R!5'n_PAfce7Y2U.Leq@2u
H.H2YP^"g'%Q?jS#\I=KAN>$^M)V:/aS`hji93<sA^l/0!J^!f'OY^QG4)3qX[fQMBhqG:pU
;1>j\)9M0cJ-tm^6<cZAS^$FgG'*0@:S80QEp\lOKZVb3Z`/1XCKaCs+h[9GVQ??2dL<$&Q0
F[U("r0JTW]Anc,*>id\CpoD(LC6%K6R+^TcTWg,Se9)@3XYJ/p*IQp<&qh\e=Zbee8fVjej.
MKJjRH*m.^rg'Wb\?s_DbVc2m"cSO/@>*GOb_fOkFnmk=<;jbJA<`9Kos9RG%U(9+a+*/.1_
Oe-u^tiFWYbVen(Xj_QE!4U\I&Q=G[^3J'<,gC>\/$R9+uF59`+\&=<(fICK"UfApT8VioO5
":f5F*7%Y@Gcrq0r+dV?TM'^?N!gWG7S2/OLXtTbR]A/ch4JUgA2t]AB!C$IXLQB#l"SGP<(gB
7YBPLf<gMDbijL@O%30k5TO6nrcGVPWe;qFlP8>2t<-js5"\!8)PiFj`+$VaY:=5[bu*fmPS
ZTBp9hV`$QUZ'[i7@"rn8h6-ItD9QouK"m\.G:UnA67Y^iR#_i'agZ8[4#7qGos%^We^#mLM
--$ljdSC^0'`!5%-J>`RR$i(f%qct+bjq%LJn++>&uV`e\WTg@nS8<VAkoJ]ApVSgETJ*2"f:
[)4]A\EL\\2eMd1WenZYp@)nH4rnXMtd@in.nI(sX5c5<BA+o%Ka>H\aFWJmQ6kV2KUu&OS-Q
C2L6GA4&&j`fI(]AqDJ3i2En?*bMh:?]AI]AY"PnmMr+D!T]A3bH(!IC'rJ9#l`cqgkY"ZoW[X8^
E3\dW77K6gn;YHQ[GiRI\LQ=+cM`dtrQX[/XXTd88Hm#YA;adno@RF$D*j6sLD_G?!?WM:>b
'd2_DnC"HIlO1q7R,GiT;hEhT1YS:?K]Ap^E/c,951RhA^rc0,LE>!\o61n/(5h(rc;LXG2>o
_G,=UfZRuB:g:2%5r%7H+ETJ(S+3Yh1=>0]AX^4E!C74]A/[9[#1ibUTA!G9;+KLJj]A-b58N^X
e.3$R\8POiWbHl0#pT%6_b8X(<2cMbC`]A*J`HVe4.*:ldrlk]A)[1K;DKF<J<BS1+$ubq.&Yj
?kii8TYItH3sqF`%'8=rPqj[M^5Sb6;;,gSf<+80r<DSj>WV9>l8>LWe1H"FKc49L#p@G&b^
@7+QCQp<<3*&<0c[TYITE1;8'cd0OB9P.UYJQ]A:J9PaC8tF%K?FDB\EFSZq0k;ho2?36Z"q@
DZY-c\AB&!^65u;KMih`UX6DI\GT<cXF^$>oinmo]A!@K3?5G/JJ+4d-WoHi?t*0CfaA"-:l6
dINZ^"B>qrr&:+K9%f&'3UjgF6K3<)=(`@r[`%"AtMX&;sUZs`&i;@ef[1TmqFnQp<%gfW$A
BF)>Z=bN=lL"LR^>WgWi`(%Nl:7c@']AjEF=1gX#eB@e>Vr`SY.G@q2+)hZ4T7qEAlmt_sM[/
o*);Pf=9X]A`diU$XL"eu%8$\#4#6I/S-kcq`YMPX:ji6aoVpcgBdE0ebaHC@AT'G1g\5uX]A>
<=qD<@7(asiR0gk(0d;ihI,`1t7q+6_'g59ZS<*k.]AWc8'!@DoW&1rg)mcqa*if5/K01<o2N
0YZQ+2'DuFT$nfq,<(sK0e!>qHM`L7[/-=t8nMq*+>73<7U*82EghWqX^+RWp(!dso2b0J\Q
eO!(c6p1U5=]A^7Ac&Vc"[$VJbRY-Qf73^sS`2Db`RW`l]A_W9,Qb@ftNf7:Zj>LK@_,AG4T.8
`"LE#h]AIc@m5lDqTA,J?XI#hsL?ASuk\Bh4@--.X>J)#n(3joCm5mkBBXc?tb.Yol*]A=7+c,
mR-8&#A,O0M?&HhM(Rbq,R=h'Wn0rk9BJTo5I3\S1Dk@Z$;?>L&-e;H8uo\(,E`O,E<[/Q\^
>]A=`W?6Nb%G2r*//D/<T-O"<r\.rd@oI3`;&(Ld#pV5n;s57Nk9kuU]AES+^'7)ms.1G_nkCu
+2SMgdHs=U2g)YInW6)k*]AbakKAN(Hak&"2rF#CN^Bo[7Lp="IH-cgj\`mZISl63f5dl0B[r
C\s-&N`XaCD%!sQ>,-1=XS/^oHt]ASJkVLgc1G#Up1g&NA)7#K2m<^rm_([0f11`6C.lLqB$@
[5U\45X$pnHpmEes&h9E'p_-[.\K:nonA(FAXU#j4m!9KmLA+u5CH?2`*FiF$BfEb@l0QQL>
AIo10Aq<Au(#[p`)\KN2U4([al3O/).(n5h+-?9C2KE^>[`b<APjn+B8bk*>PQHob\ND*BS5
#)rOLn:G3O$'FFq9d2Ur&)$Ca,S-qM;;F[S8HdpSm+;6+'EY-(js@Rin;2P6`Ot=D&d>cLad
Md67l;.!JZ0V[&6$L&;Va`c$n]A%iHR^<#ep1_CSn:UN>+pP$s'\Cko3Ci"n5WAZ_ngKJ4N$c
9Q%6"^(-rVS0#d!`Y*/[HBSd3iVac3<KQqHUa?g$U<.d?i:H/T[fKg%sQ!ol)+pNmO0XlRR]A
=q*&&)lM_kli*Y_Ss;ajH<.7Vjn(\2;ZH0fuS#M=Zm;m%-"pnKlq:1gCY##p@qmd)dXD1SOH
L$h[Uc6l/i\Q'BE-Q)!V$\-4oDtmt4H7E3QI1'deKJ1eX)DS>g/p<f(:59)"%i$('Kcs)abj
?EOBV^#6C?SO$'.iOg'2E;g*gA`2mM8CaO=]AEs8Okd&PPs$UdS4-NDhD>b0Y!asLtQH`gi$V
M=nDN^.o9.TqD8fWN;.P()WgC:D5Q<c.\HsBnED351euX^qKd&3HT:r^U*_R,s0iGQKBbi&>
:(BTLtWO+K?0mWj<8QIPE:%JZVh*hWhE9B]A\Zl'cX7"Pc@ZdcL@>IDUoHj5jsZuFPCPM[?^J
C1:Z9+CUrB)q=E]A%b#6t+_IoB;NTf`AIlHRt8Zn5@U5or,202=Wr5$*^b`G]Ah#&J/WSqa[Z>
;Ad7`:XIY'Ede$a5BMAEs.)pl4ShgHR`4qI[-R&)f044-AcDNhFr'0"=btQ:I>nNaO%HFq6g
Fkm7]A<CK(MJHmAuYRY]AI^SMHr\cL>qJobg/mapq-P&DKO#]A9iMlo(o`hPO,B25NkXU4sjUq?
T[R'BKO[>EcR<u!D+=eGO:uXuIPPAP&IK%1Vpk+]A9)2qljK)$k-e=DUL_?0YM'AaC]AVM9S3=
6inYD)TMah=RmbrV>._G+IdQ)>D8uJ_rQ0+5QeF^91gg[,m<FJCqVK\o$QV9r5CV<>kUn0/o
Obj95Jd'cU0p.;ag%AH&1pO4R/+1pk]A9^1P=cdUEO+0#:(>C3Z7dL[cbTBDTH151h47@<TPD
QAon8L8bPW`ELnGCc0ja&Kb*43*kj+3s)ZME8uCL<hV@bft<NLVg$JH/qihLo(pJoP7!X.cg
0qa(BM%sJdqB0%m*ir.Gp'1e%SJFlA^D^NhVVAlmj8kb]AAr+RmIQ<diVLt&a7'jh0"?T(S>B
eU3c&5fmG&MpX??(1.%6B9UVZiBlJdn'?*s#gQ3.bd^jcP6FZ/a:e=f%3Gm?3M:X2=3i3Itn
OCOndP=<k5IiECTZq^3Y+3YU3<&K"#U#-qZS9I[XlBr,q97a6NsJUC#S!n0Zn<#]AJ+It"nF`
^`YQYhffj.a!dV0Wr+u4-4EPU1@H&]AFroa/)h(d=q.o;1<>_(:SL!Db-<Z#A^=%ERNZke1_p
cGgu?8jT,f.S-JNZor-KCt4jdl_)hHF@'+/bo:\I?/dbE*JdG9m?W*rIJ[@72rr2oP>5M&HK
Jk</K!I>H"(XXh!e'_VnbL2?q=<h/YndS!<6rp?6O0X2lt%Vm#%1-hX>PG4^"mJnPX'KJ+"'
M[Mm0-7SF5"r1"pX1cmuYm\ZdW[--@V!9@pGM[FW0?U"Ct1KHSZ)5FAl83SPC94/^CLm-N9o
bODtCq/Y/+K!n`TmoVZH#&-[5khUZ2Mof:q7XtjNYm!Cj&aURW%i\<aMl=[D8:+;q=EGU\#S
U-et6PfN3U\%E+o3k*CT@d^$h__;$C_1P`\)Y-Cq6&F5-8M^nFU4YDtN,;n)OS9J_TM>Rt_^
_-j4dgOlNIW;3I\FmigtoC"VbKVr4)\qc:;N3A7anI>DEp>6WmA@(Z;T.%JeGgmLO_?a9lTr
*khV(gVN^1(`t-2*QdJ@MX<j;&2_Mg`a?0/SUWa,V+I<P8^/LqD]ACg_Zr*Mn7,QQBB,sqMg.
?<Er"og76G;gc%9;R!-W(bdnh2GN+@.T5F)&SUD;P:S*A_a&'F-BAQ:EUL-E<cTcQh>#?g0q
W3q/KfcXSDa)V(_'%7Y7`[IAn!=_9[gTmjgC-'B:jt68FGA=PE<67<;S1r<XUeKYH&TN7iu=
!+_23?[RfRQ=9FiUu8Z9%*Y-dGC0bn3dE8Q",+6Dopmsk+A>_$[oS4kEn#a=r7*b6]A.BQj/F
(N-h-@,pLU=iD,J:Ytbg&/ak.6nkAIA'!^"0aW@2#ICaHK$.m<]Aq2I\cOE)5jt8+B[p->dIV
ZEdR5Er,LG\HN5-qEm^Y"t%LQDZ"[Ai@i-kUW9">QtfG\ou`RQ*7_nhYT;1iK30\Q.JTF(%p
28"*X%,-F;p+tYDg`b>210HI:PPtMS[?cML$B>:ums7"TMLidfIRqmM16rZUO%5YR]A-VTC5o
!QMLcJ+8@?Pjn'LY,p7NF0,Agn,\:IOOrs2`]AUg@RkJHBDK.^T<BMY?QJ&o2[6NQ/T2G89j4
FfmS^*J\r_)"hq8_&]AX?&A>NKjWaqm()3XJs/&8=/+/UM4@0pUj')GhHoQ5LQaW4T"0p[1>,
3/hHU_M7#1QD&Gq`m(X!hPFt7]AKNbc;R_IB^(_T8pe5o'ofLp<c13X^52/uKSDo9AJ[a3A/F
M\jb5.aI\;Seg@cUK1UtS>K+l0,1krq!lRh#j-]A7,Y1%I]A<^H(C[Pn7]A9n5Bo`s%n69n61A:
o%N6o'Kn>1()P$Zo5-bIuF5Qlm;oKPL9.t#ZhS%7k<q:d%h*sm[<[ksk[lQbQ"gXa]A'lrj]Ak
e)%Rf1+`,fu/d5[!urTo0:]AKa?J8+]AsE)g_`>T2*\A"W6u_K>*/-H`HPuYP*jcn#F*Bt5(Q%
*,(YGNJZ[iln&=8*!X[rji74rCk%VMXU&;l;NG15s&RJD/`oCU#3O?AB!*^A:VbUGOY?_6Nh
k=T"*QSA(!?6Z11Eq"%X?^5&uT3XP_1,?)*@p?ch_C8hs9)uqW1fZtep[l^9/s27<]A5tGU$*
DQ8\GtkmVjh4:!c&m*$AEgB#UsK"d7ZUuVKT[[Q\a",hm:@Or(A^.K>j>-$sjj;rT0QhAfm-
Ve4D#-fclUs[QoF,oJ8aro7P!_bmROpoGN&H0>rD.?A(`NOnV.*S,Dm<k>AE'b<M5sQ;Sd]A.
-F#Ll\/k#._4<^0(NDDl0Kt\G[G]AgZh#mc.*-$/66sXE'#C`%ciRI."!*.2^i>i]A:_i(jam4
8VE0<ABU76#?YN+Fo(`CA#51n($K842X`]AlC>,%q.G!Qao"p_2!#[u]A^>T[A`YEOEki;+^ZV
@;k"5Wj\R4?9(gT8V?#897rQeWsC(%O7UfT*\*Jl9Q*B`<40B')o(\j_>YIVj/Ttd<Q_bR\[
EkiLYc1Ldb$0DWrKH/bT3@>ok(/MOIZ":2("MPN=6h<Od\ORn-P.1bp&*jQD_#);1*@K;O:[
>mVb2sp&R8NS(qo1A4U8??61<GK)%P*cIagmg2?M]A=W]A05R2N+uV%W46GiA#O<c@p=e`N.Lm
JYYf^E&hU&WJqQA(%3QIZ@;.[#l>U>1:oH"Ja57HL"aOdHUlh<Y-L#5:mX:lU2U;$rtjQS4`
3)l7)':n.Pne6KdA#\Y5A%Q8mGUUWHT#g0Wn.MUnA:6n/XQGX.LS1nKXmb,RK/dFp]A?mFrCK
&(2cj:.TnZ<TO6m$G2X^?mp4oo?GZ7P>>G@0Rhdb:VV5h1RfSW>K-KZ*W9:?c`PSV'-mS-_`
#Y`QMGjo<J`HL6Z(INg4`"T/^ZmNVbRq&e+c&O:,-C09IX_`gU_KgKLZJ[\HcX$$SoSFUtfF
-P-XJh9oh$-2iDm2>`9EXN]A4j8nj*AVl1C(-dH+Oe.1@Aj_K_4/iY@sDL9BmYgl9ZeX5<91!
pSJu7?&?5TLN4-3mP#&f=f?:*`1W9n*BuXkOV/^/CVu]AR!ggtCj7ZE_3K&Z7]A>"77QG,Mr!i
gPPrCU>:J=bsg:KL7%npi?/u\PQ6VjtGn[DMH9(fjJ>+7!6l#1skcP6:A`%K<]Ar8fW2;*Gg0
4\B`?:+Mdiag?b9Tgr+KD>B=2*4=LGZ@OT-G7`R/3s^Z1&A\g-O:!GDS8V(L/dS6,8KJ#CbY
$]AFSa8_<7e>Bf\RcFMr"=c5A?]AEl@$QKJ-e<6aq2\5(12:<>9P!RGlFtQE?_cE?kL>XG_4@2
BeDrNKWNMHNn.%h!Ce>8]AhAIXjK-->,S@KFb+5AnV0KaQ$53AFUCca%dfGMDJWu\6nb&o%JH
i>XmS@JOH-A>+D+$s9SFU7qtdAJ?60#D.@<&g!<`D0UEWMG>VYk=3Ba5bf@>OPeA.d=Or#/n
`YHp4@^6*,Q6VL9/(IC`KgRr_iM#at9F(]A\CJ.j'o#$Wqk3E,tOI4b31YFt&K5>KJ4m_lr-t
M)./eeWt9;J1\XS8s\=7QMCs(3##u8-U$]AQ/3Ae(\NT^.5un*gDD&Rr(t-k7qg$6Does7L>t
@QKWRG\B$W"\AB;a%7B01OX)]AJiAaD\PH!?JaV'\,ep>"]Ang=pO_u,6Ul=)cfk'q8J6.\*`;
g%U[%Z#j.o;9tf(^Otd;Yakm:@Mc1qDD?_BY72T_`q;Ja5ms6OlGfp]AMFKKs>7h&+r/u*ilQ
[>7rka6)UHd=BSQG+Bj6/t>ZD!R,S1:<%[6L1-:Z,>Iqe&(DaXuA6a$e,-K5^WEQ5Hp6s^np
tMJ3CG=$GFGNH*jN%m1.s?'snW))$l^K:^B?'5%fZ]Ac\\:>.eDrMW=N*h"NR.QWWLU60aK`-
B;68tHHaP&78+Q6h<.mZq$:-TY$<Vqe3ihqa$P[QbdJ[UP2B7mk+mk7cYPoZIAt'o=D/H*4&
AsG\IGk#(>AOKmRi<#^dgc&2SH5_Z&;PTMo!Z8rb4,^;4jtCmRr>XZ27IiR%25K)XF.I3L+9
'0u*/+=r.,9PjJ,:@I(2cJ`@tl:n=i=ZiN=gi5]A&(NBFK*JW,6$Fl`sS.W@Rk4(,mqKV;?0?
KN2N_\tV+Ml(IA2iY3Br!f:19l:WoB:/Mp69'hlOU>(73crRjB6:lc9VQCrP=OIp2(IY]An\]A
:[&&_bF(\\*grrd"&LJLba_)p6Qc?jccm;.!DlTrH@C'2`[6ZS7f:?.7sm9*:gr>^S>L$)P?
^Qjs1Od1Uc?YS/bGoR+bT;XXF+qqX;j&52V(s]ABs:;%G>93VtMWe$,iep[#D7js\(gaI6/;+
HKh%oO%FBh.UiaGfo=*#&?$+o`6O[q)p7E1Y/,YS%pCP`Ul%Pf:Is*ANHIR8JF`hAo)/etLS
rBo0E&,;BJ`j3b&1!^M%a[smq_GZWY$7JdBhoN=Yi=2!h-*2j3MbN2bV05l9[%h9PNXe+gO8
)s5s0@p`ife@)fUnaf!VFc>U5G>"3?p8G#M+lgT8WP<H!ua^Jr4SO*m>5"!aVm4t_6&jMK=0
Ga-0[AE+Aqm72eh4f*CU4cM@V>+k`&0Vn`LRpMTQuA7Fp#=:e[.Zf)BhB.3;Anh,g*WC/5%[
<F4-024Tf&Kfp(=\n)g_Vr[;/Uq7`k5YCR\`V.AN0t*<b&o5FY"oBkT-4Tb9I7Ra'pHuUo[:
g#_9LRBR5*spb)F5U%RNB9Sc[+5NKWP@<Y#hI6Rff42QIM,&0#8r-FEh,RV.gH_)`s`9m1`#
0(d'cK?cc9Q`(lnXT(d#:*13Shl%;b.3,e2NDK#o,ReHYRXX)e@:T`L5Mm?t'OOkf;;g,$JW
_Xh>cRYoLmrI%hd+ih`:CiRCN#Tet[Olm2Kq.8]Am,X;i%p,>r9H-!l#RXL)[0;$?pB886D$Z
QC2",NW\GuHi%Y#MCDg]AT=>K*1m]AG@NKK3f'ffGq/U611n-.I;I2-6'0.1fg7q.1-@-02d%u
ObsS!NP*NcD?7H%&X(a'Y.s7jiuT)q1q4_`ClG,UW^8L)_Y\qC6PoAC6IMSR^?tAmh8r<Z:[
*[N7m)sJm[#8leJ/j1'E?^=aLBigGJI'3?"C18&c6,86Ekd>VOsh2%iS\n^uRB/i37B(/[:.
5AR"oc0+If6rXRT>?'/Rk\9%G-Ysc=lBW^"bW(m]AIUug?VYarZ&Yf!##a7R&c."Vpg]A&`!]AW
=)3jbZ[eB"_lTrIn9iZ)J`1J8PM-oAT=sUj+<n>mi\prqbk`D/T<=LH:Ba$j1++D*ijCbM`L
476=MUAonTuVmJrkqSCi)Q5\jS+@*WHLq\7/S:m2YEj,pS'7]AGJ"DX4T#=5Dr37Z<5'R<Y2e
$C`iN$HTi3E.c>YiTo=bK?G>*2Qpd<XfG\hT`h?c2i<$rGp6-dg3./*bbV(^=SfKO$k")MDE
+2d\Nq\_1q$KhCjtbC8$tQG/@<eLs0QFh.'UZP(aO)+n[)&g+IZ'/B3Y4I1aF,AlqXro;+;W
*;ooXmdna&j[L!j0d8.#"Mbl)^9>=9;Wa-QrlmLR\W,a:/8UbA'H(H065RB6]AGTQ^\lLkSYQ
_<ZR^F16_OuD/&>"=Q!Z?L1=A*a%D?1:GAq/1OIY6%@Hk#eDO&]AIfphEq,#**fAl/#)`A[ZG
*URDDIXXEfcnmVBC*m_%Qj@#^GDRq7O5_eg_P.PBP+'>C&(K^!AGNFHX7SMD:A:+6&TXKJ#G
?$t]AIR&dGJk`/86iJb_hN)'V0,8):G21&Zn!H.-mbB0U`D>W"7q#fCCYh9!N=D=o?W2[u9/M
LgekI7`OE,/Z9J"sJ8pc3;oruirISut$:]Apg*LL:dFl#PthFZq=j.n\2DIh#*@_G<qk)WW?@
u/+q*U^TF0glGDW^/CECjfsi\OU,9"QJu9M")EZef0F48"C@+K3B;c5E]A)=I5)FtFH)Ftg3I
L`_@DT%X0)"nb+#0*ut7:QT#ie5;=:Xt%-Ek>n+a`jp-gG'O*'f10R-4=*&Hq5ua`3ZET0<E
K8B/FGV-k^poA?ZjUY&R@$I7rMFN'Df@ZUj!_^be54J#Qn]A%1hhl3l*!:gFJW\>9B1/63%Q#
)_=iT7=9UKm7"gN*_f_g8.9(.-UR$?>@t\Ve>jPMc:4T+olDFtL\mu8$M]A8[nm;aGS6k^W'K
bTQ"Qd0c9,ls0(j(8+]A$qOKIjU;:(Y7Im'VF8Zm^r>_9Z(c.;4#&@nG-`Xb0>EadOF:BL#\)
>qQD70<ZrXo=t9rm@#)M6HZAJ[a8P&SKZ:,BpL)O`q"sLbOB[q"S<WDA!9l>S=*ab]A3[hj8Z
HkWTrHj+6Q=`57Ot*IPr+NoqlJo'8K"r.GWA,Kh"#1XkIkMj#+0o5$po22N.Nur/9e"_>\+\
2ke/`1uIUYPC?I-lgIU($.b.&(1WrdVAI?KsD7LGD9N@%dM;80[9n12@N=g(`\7N(M[@%9)X
\c<m^/Y=VBaTroj+1tVBS`A"dDF=*:M37`_f9)ga2_3Pk!P1+MZ<*TV45.[bY@d,a5D4=ZXb
5&p.e;onciiK2*%ePo69s7sPW`n8<h@DGjmec05>Gk9:bRQZj2F99&K98.(8N2MT$>>p^93j
4.lg!lX1?Kj9\UUm0]AL^2,6gC8X&V3`'\L(p*%MkuQKD@SD\L&&\iF-j[-iA=_9)t/XZH:9@
N`hlmIA\Id_F-5`6#QVg&s5e,%4ZP/K,eoY$=&ma1VA]AT&uaW8"h;I6\G2f\Dd^N4R:`#!)C
Jh&7TJF^:9(%C19NWVXHHudND^k,%:)TjB(Df*G#$9Bn'T-AN+?j;"1f&"VEchZ&Tp//pmLq
Mq0oKLDl0A<N_DsRH[5P6V)@T"ul-l_`Gk"UE2V0L:\qP%D/<QokGgVZ1b.6+Lp%L85I"#m+
19]AD6GeVY1E"M`(]AbGp3`RKU_oLPC21-k'X-[#<Gt/tB;jT8Yn"@LQC$-mGdIkY7<`jY$0>L
j412FRNL!R]ATT:)6KS#GnU]AUrC[OODc8%D"Ql&R4aUZ?[a&t*LH5a`oV5p0aPH(j-9,ZReE!
GeKjbR/NY*oLPk!6Ukoe196or3NFIiF7bK'Z_Nm.Cjr;X0Pm7Xj;tkR,akR?_l]A_Z%j(;ma&
3mgtV$)k1:N,pcO:qh[DI]ArZhqZer`J$Fk,\&YU%Y5f?W?b54Z3(57*bUJf<GY%&.AK9'gN)
;)'$9S%kuJXSn[;4M/gF"o@@X4FYBE:4X38DBJ<@5HRi9%*/f@mgAf"d[M@,$4P`1p0lVFYL
^_1Lg0_L3UYe";J*6-p+lu+r&fNs`6kStY,$cmd_n"JKH;]AL#/G!Zaf3*goBP5F\$pgEIkm[
lq/A'47<5*cbZ/Cd.1k9]AqZ@Y36ih.>[D.gF1p!1;-9N@C-^qG+]A4)>gae:i'%XMhf[VPgdk
@8]A_[Y<L@&aOSk#5ll.PiP48N&.@-VVeT'_7;&H^FB7lY,#F!mN)7Di-9""nuopEo?5oVa+E
Q]AWP77%)kL3mB=]AKsdi@SC4>rGJnmZcVgdr;Z7]A\2V1_l)h3=DH0V$Oo,Fk[*BMX`1K\&lg+
$`t+LNRu`[#)/qAq36PGNagCVh`BtIA_hj%I/^k=lf%'<=gG09Zh#F4OE=OnE\!(B#GlY?5.
ZP,9;o%(=%t=U'$(HlgFX3igBhgg)EseL%uIT"Wf\)j-jK1%8S)U*:l]AIf,%ohoS*n-R1+,Y
F3#c]A#!2J(V)I@XdqIXTD.DK5*]AXA/faYfOF7Yp*t;$e94+]A3j2;PA2@4d-<G$Y<@B`=Ho'.
?NaW>qgZ#&T&Ti>@pc<Q^D;;CltAJB`Xi]AJALobSnBb,jLTUsF9.e6?Cicrb]Adn-X4"Rhkpi
W/9N54_Ki,eTnoF^%JTgh:`r\dPO+p;H=_oJc0ec*'^_PcFO&]AC<4:>b"#HT7mR2=.QBAgqJ
:)B0H&,&VW's"Hde[isp,,(FV.S)7lB2T,a94QF(URMp9V:)%#8N1kp)c*("/V%o@V":fZE&
<YZ8;a?W-bu-i<H'p!Z-"jbWhtO,?`ut6KeG)L/DG._Z`LsXjUr%X\rPI1[6Q)P!g\nYT!pX
jN#+a(rFe$;F3&MaL*=3_IEF.D*a[_n?7L-s/6@C8f.XH9X<e@+mnt<+VjX7OCubHW)7i:;e
NuEIRYT'=&T3F)Pr1GD0!aDQThImm$q6:k)ut.f(s"3u=Sd%W\rHoSjCQ.D'UM.R@mnomr5/
AVJJIWp<mgK-ks]ALI#a\fB1Ocks=t^8W#N4N==`SrXkk]AUo@r[:O,!7o*YXah(X8W`LVS(b(
2CiY*$l]Aq5RtWU,Li12m%rhj\gbtCBP]Ar,OC!?QMW5ldteLq's?EkY"pjK!OhLt2E2cG2hAo
;$iBk<R?<^SH+%:-cETOBM+c04Ak%iQh=LHLo+`aE\Yn7SNX:RprD3/0AAL7)o&cL,8EAba)
%>e_g?GrJ&#\#-,Gat')Bo1p9R_TCg1(/I0=>$e/Bm`/SlEd?cAb6$T$DgG$n0aF\i[!Z#B?
sQgIdJ$L0n2,ni4qASuZ\o.=*&fBpNR1HQ4>^9`C5G4?_[s.V$m;U'iC8JfZe8WAd2hS)H;7
]AsC@i`2`#]A,;g4eZ(Wu43Jj`'+d@<2.:jmh(9@X,d%HSE!WGHSmG]A0O[=;l=ZK9+tBImKTR8
BeYo>mc08;7)*7D=U"<`onIY8Y`Z$Q;%!k-N:BS>+e1AST:D2TV7S?>K*_b*_pNC#$sA:O_F
!6)i)M@k2p'1Y628,^:NJV']A\j1rbN[-g:ej<9?\[@t,QF![T"X`KIoS!a0.b^:_C4NK09(2
W7eVRA+Zs#g/!i9aikjaLo&AT<lkZXulV)+"4MLk\K;FZuiM4TWH>"nVQsEnaFS+pBq!968*
B1fWEFGFI+!onSfTZBmkK0Xk=a18P)o[AH(X)"Df7d!g65jc6K6>8"O[GfY#J=<%o"EaDR'3
+t<(%Z[6sY_030OXYE42pM]Adq:,d%,1%\<]AN1]ANnoUnPJG$')Zt^7?RHZ;kO^[<)0B!RW'i$
_)PS>ST(SO6$IiR]AFF45(UAei-q'E+*KB6P$2(B\I*\g4jVegOcZU"#51VCPg=7upiDJCeB)
FT?63t&UC^h<a__6a"j3]A1J=bCBAF5eabH4VP`+$+VJ2Ym5Q4ADph]Ai:i\P0'OH/3T[liEV@
/4BoYP(%r5KkesTVcfdjlZY(^RGZn>q=>m4YSb4UDkqY%HqAn5&IP_LpA(-G#=fg7lj3QRmX
^X6eH#B,AJft0>a`5!"lN9WXR=8\,Ys$F(K?5TG?GLMUGS1&\7b/u3j6]A7pMd/gpm;j1nq3,
I;acZG\@nGQ1A1lo%m9l]AeRQ%4Lq^6<ghGTCp^J=QE`;a/O;OKcsC^EPPR#\O_:'>@sNac'b
7Nhr,#H,^l35qu8@[G35D_[J/U1>4uU>b/u@:k`S^!Ge2SehbRaWF-JlqaG,ZX(huct9M:CW
cSOh@cu1fs7Zgg!s',8ASj2(/P<`#[9qM7opTtdHFhB=aVQIR=L23b,X\`CnMc'qN3ZaLWZF
X0CS/=HZ#WXahOAD24+ZFUpMS2e79+69<GuF%f%An\n"B1M*I?TT,+:K-$8`Nd8B*`$pA-Kr
EC@;)tL8'6KE\#X<NMPY9j3;I,hn,\$7E$bdBb7lXs9_pWSCb<k=!NZnijPk">*FS9,[1W0Q
R.K.EjGC;JU/TZ2(+<^d\`c4t/B8lIiqM3]A^iS\L'Q57%st.:n]A%+cW=G-?K8GMA1=p8Ve6P
&TpP0,N%;4RYWXP1ER,l3P`qm*As]AMh:e:$/K\S=I+mY71,`<gZbFC[U1eA,JAADlX7e1cfM
YgRBIF9+Ir75BG0\VV*!^VE]An*=I(Q6'9>dfKihfX-8kZ0)dCsUYpWkiU(kqXH5>*mJ6c$MU
WL<aCZgit&\4ShG.e:?TqMl)J*K/sR0)$UFNim&XIO#mG,+%iQB",(pY#AWtX4[\n%(%D#EA
$s`rS\lg`]A.$,A&r_d>EYcBL5^F,H@H3l_6q!Iim(3[UKPr_@DTA?nEobY"RA6P`E)o`mZb<
Ah2O/VAgOM`Bb:m8[P_0bNHhZGM:N,jC.iehB^mY=Q78R)5pTFoM8G'YHPemR\HhSG*V"/g'
:#"j3jAnESqDM7a5XI8!@<+P8<uf!]A2L")'D_Hl:"LYr795UQe>A?[M0bB%6G_n)Q*Fh.hMX
WY#SBGVo3Wq[g<Ws%o`@<PZ?Vd_0pM5+JXM]AfSg@j*:YJZtp)h=Gs4eBg*V21c1=LuUC\PLd
DcY"ClnS)5.`dpn""./YD`jJbU*@hO8NsgKN\W#77)H+BDYsAshO6M+#luS8N^qm;,A[lTNH
Zr/#8c^hZCAeA7%Gj5bW(QU)#BgF,U-&n6Tc1%3%Ms4pk@nBiMm3jPb'HQ"mf;b#;VC)0&6,
;&$<)l=Uq0E`BRXqF=3cWjI"N_pUh,S`D;S$UbRgRoY8Gk2U=c?nDS;(PbS$l?7ig&U8TEA^
WajId\=Ha)/Nh[R+VQ90[RdFYTursX8WsoEToaQbj.d4V2<tXi,PRUn"bZMtr**afWb.Q)#l
[#Y=308,:qT++Bh@oHmd]A>cTJ.VTPp<l)T-ARCY+-=3F3A4jc/i3Nj^_Y*5b*H>HmZTdARiX
_fp=5^992KPD,G^/$baoa^Zh'e'LA%4Y+pYD+$K#+gp2Ks0>EJteY3:7)g]ACJf;OYif&'\mK
dl>Mdh)`2o:6biDK<m@i*1ia2J)m4Cfs9f2<hq/TW;]AKR]A1p\RqS%@Z]A8eoe&JP.i$Xi`gD:
5e18!CtYY&udOfejfjhWT$dHgA\KsFA4"9ar7<[mVlU4\p9j58u3&bd+[9$!upZ!,Na<0=&^
=&8JO/V#F%?bZ9pE1PZ.#]Ah[n(f8rV6\WbZ=bSsEA';7S4e8JL,X^/\Y&F1_7<&aVnE^C.>H
)^,]AfNGhGfYZ7d^E0jhTnnTQ@AVgpP37b_6:n\"ce8%B"SW&/qIc)gK]AZi>MX8)4USHk%^Y2
([KC2l.LeOaC$4/TG]A>+mUndV/Vtk?0Q.XDl@UQ22KtstEahii2:iuH]AJ):!cl^tqtdf#jX\
DbaP?MXT,#PP0$4=EZgd4A=I#,,dcaf`1Rp)bqM55<Wt)?-@bq*hY1F8PAu]A=$h1AHMn]A-Bm
X'Oos)b,'iA>e`179R"8`J^hk25iCN)U."ObJ=GKdoOMV-j-6,'iSWD:`bC7:ua"G>bs3N+i
-IIFI.\N.jKd3@2)H#'OKX#p>Qct3O/.!O+-ooe^GDMDJ!=!ZCOnf"5OJ]ActgbaLQjbHS[DM
Wjp%*Pnnf81)EFoK6/;R@S(aj,sE;,#%QQU?4+]A+CC,%N2U=H-g1G!\='X^!@a(XZ*AR/12e
\T78ZqOp[KhXlT-fpa#mj=FgNj^&i!I*%<$pXPZ;PrMhZ>0mGft13ebrd@BWDEfpBfDP<am(
Anp/cck"'64=')=&,pi<n>H:;q-`b*'fLh"88\q@M%8ec!nRRdrPt%R#(9737=$,9^u0U&-b
*4J!ko;\Js.,R.t[IaeWJ[=WlX$W:@/)fV7&$%45*WZ"'V0lYM:N8&>6Um&]A9>NX`kM_LPhD
(=8s"Q#U#eQd#-kqAQ]AqU6XSV"hM:/^E+PWbo:'pDQEhY*^6_UZBS>->cpa#K=,f>J+btk'u
mu""ECp*;B)o2JUM6<VJs[Upco7^g"$]Aug6a!8R2#V-e:8GJX_#;>Jm,a\+GKJK7Z&Bf_6:$
G<JOdqOZBW)D.uJEosl$A^t-bFZTC4rS69]A:]AhZo<=u,9\A^gPY$Jr@Q4-`kK/X!"-Tc;lX&
s:2PH0obWD-cnP=JqQs*iTZ6rBbVWlM:P)c,mEO&('l7/Q"/dAfk!fM&>b9WMn4kc+5nX%==
?nhMpiigMkoXM$VD609g/4injZ62Jc2`UY1K=mBoSC@;Kf@YlV#LW@2t25pb*nKJ]A7si3^-4
Z>Pdk>T=s9B'bf-/1M%<<ETQ$0Rj%U^NXqTB-l?s4HnCa7^ml_D_0o:K>#Hp.JF'b&FiMXQ!
44;UT?i`p7QU$$M((.j<O,<SXch)K(Zr/3N&1abPo#-EF^R:,Qc&rl8ru&/Gpe6DBi>2I=;_
TQgHK:HW<AX5f,R2Jm*JYT"M!@K?.9$i^\gB0rqpL_o"T7\%To9M_&UZ%MSVjnHg^Zb6PG6%
3%PMh/13,$n@N'F+e'S54]A@'k6`pQ;8>D:`C8*4[9.nh8OV4"[$1XHdu5Nje-!,@?qMpM<9r
_Ze"\AKFR(]A@o6-<54(qe@bY!B]A(2C3TA5r-p%?0\V>T6'\kdU8kjZ3\O:+#/+ouO>;ES(ci
gD;BmQ3G\VO@+'$9FVK*#I'ZAiWo2#,-.8/-Z:j'00ulk933]A:66&6)FdsqQcaYYfHm%-+%T
CgTq82AEN=$'dRQ\_.)>t)W[)(DrJ(E?'/4MdC5&DDoha:$hHTqJH:Zr$Op1>&qVdJ5BLR&B
aAY#^MBNY0O/TcZ_c\,I=KKWE1>rEEFB56V?k9>ejiiX_tUjF]A;lWfL<,e6U=#Fek&%#Imf6
hbQmANn+"3CMT,Ncp2=_8NlY_'s8s^cJX]A:BDl$BgdRVG=NfSYhEkS5D2/%V%ASXe!,!^f?U
WM$b5Li5=XG&[Ioc3%6I86\u!hL_n9SN<D=3qZuTOk[-neS.OG(g8foCQ,ZS]AGIGdkBZOG\7
,erK\U(g:AC2Jfeg"tnd"QW%uG%Xod=42["&-2Ur1laJ_D+Q4aoslub-ai[>R8^$MT!,Nr2j
XIDpLi4[H.CICFN3YP=md0TR"?t"A5ISc#)%#Op5]AF4GQ2b!ls1E^M4Bt='!aSMQ5i&'cP@L
B-1[)`ZJ4%tWVLP)#ICO69YLg'BjhIf>tGT$j>D7dq="l_&Qj\CSE9.1=ScIKc/N3+XN2n!@
P=$o,\3MW\K,p?SajNS@=O'p)+gT"1phh6FEHt[-YGu<G_8("/a8t)chSUL'SY*[gmQ-0#]A(
BV:H8Z$RZj,?eB9r)*+,2[M4n358nAg[p[5R#HYB&L8m!eBb4pW\UG/o9%J=/_BX#=J?9qZT
P0oDRND2>aD!?q`6Jnq,s4Uc;lbW9/d=L.oK*Z5ugU_43rQD&c/HmpG#"pcMfo2KtWT$=Ac#
Q*Mi&]AMH[dhc)ScS'%4%aOe&k_2LSCEpXbbhA!0*M6NY8c8kIe=,^?uS)JAE+-!8H!="cAE*
?Rg8pJ(!I_+mRFan(WP>%;<-Hr?X+'JMPs?=3@"Y>N-Zk/d/"tT9B^p-,1.XBeN)#_B?KiV!
bS(WVqToh)^#OZ-WUPcL.Q[A_X!TtK/h#afD?kcCP<alIS7l&PtF3:qD@#+o)%`n:Zfn%*%d
WNFIM?g;*[cN_2a2K.`l\K`:^4i!N.lBre!oDIBt@#Sn0_EfRR&DBoI^jXQl,=E3c;(p,sdN
ZfW>CS6fN\PP]A1EidASnQ'^<RV,5*(53D5J.W*fHHL`7,FZ(uCn(U$mm\NS'^9;VN]ART)t,0
_V#3gZ"N_f?k@rpT#c#KgD*o0pRl\HCliMdI:^CEoXI?6R&4UU[tlD;c$9M"Nr6;!]AhCEbOb
EE)Pp>^ahfR81t>2,+u1e=d<LDmd8)6V@FeMH=*]Ah<MW[*oJ@O['Qt@Be&,^a;3/32PYGMJZ
VQ0-#+A^s.NKS&?*tV;b'KbN1"JkSrD%h\d?lH[I8IX/>lnt5J__@!nUutjca>.&b8g800$M
\p\,B):pk\,t\#"YJr`QAP=Hk_g8Ci'Klh1KC^2;s=OR/Dd5fnpgY:p+*Yaden20@7CJ%g"[
h@^5H5WTnsC/[]AmW:%>VlsDmkIA/CPjg]A`a.!NUC@3NPWgLh&R]AtsbH^^ZpA6tcq+iB/9@VI
pns,E'm0r,J[6n):>(6FRW#oJM\IB&4sm98NC`T"/U&,qm(SPp7heW',@7@T"sJ2Kl)+Xa>@
!d7[$tE"9Z#`du>D4oFnLOI&=>!)TfTlI\mc\nK3KHh5?W4%3EA8)m(>)jeM_!ZH64R`[%$(
q!WIi0T]A=IJCoQRrqF@YlOkfgfEgt'&P:>rK``jaajfqo/P'CHWoIrO+RUghi7S?]Ap!h'bBK
T-Pf2aJ9lXj*Z0nga"XTR$?gjbP:<hD$Y-n#@/if#i,E,Q"S<@"#9#rJm(YqAn7b,Ygq5<'V
$iQN"S601B:m-X(T)AH-^DSJ#H-jl&#%T_Mb[qfaUeig[c<EE7F)2mokF?\jEE@Thg_CGLmf
]AVfB+Ea5<;>m/D'\W7q3q<)$\\V,-8#'kiSQ0=$`jDKk!qLp0OX5$'i(lr,h]AH>W<(<<!uX>
k#3?oRX7Z9!*VT75CpU-\eR1;<]Ae)bLk8S@;BY%^&cM._#_3<?'Y'rjjG+/<TCHZ9i3&I@!)
sSWO&`3`HrsbO5&^oQ=(aigsJectQ8QE43H/7N.s'(1Q5&-#u&;$PQ56nAL>^R8#YQ#8X-%>
b#9^XtI&:XVoeu`-k**70Ph"`<VAH$SOpl!@LT:0Tn<CuZ%n^3fLl-Jt6[F^*3+#Mgug0#7h
B5JL)5uDM?UaoF7c9VekkgOM9r&t2&23eu'pQ$+uE]A"]AS_m.8h/5ib#I+&]A[V0m6Pnh0)Afo
u'7"N+Bk;!L0812f,I*Lqhk`N@oM.`;N>k]AapmWC*8DRP,#E%W\O*#<uS7I=rZV0=,"RZhpB
IC)pLEFF1@-#0TMURuWnS71#Gt/%Q-)16E&_+gC=S=GihY&BTb1&QlXbU0oQ#H'(Z$a4'*Tf
Jm2'VZ\GX52XZa,+@(:Vn_+?"!(773HW[lHri<;-2$8^)P=g#Aoal4crNVYZ&<1>YZ+%d]A]AQ
jnC?'rgnNK#o$Pl7b9d#o[lZI4"mV.50jZ6j$5Mh:=,&@@\K40a><.Pk2I:)j7S+qP_\II&?
Z7a&8UT/DKN"7%h@*+a1K=\ZPDc_^C,;h/-e4[Aac&N[PO\e's:CPR0A@1%UO7ba5cIu;Pps
13!X,AglQiQ3H,Fr<(\!iUj$pi$a$6FqL9\gn./ciK\7TT15NA;-5AL;N0bm2RMCq&1m1>A@
?qZhdE2VkOtZYWP=8eI.,9Y,i%Z5n&/n+\G\%+ZJTR%1=/,fp?6E>`)pW)b4c*?]AK+g0o2_d
f[!eI82tCGPH.$N#nYdFlUF#)sk@@Q0,`?%X_a1/^Yo?<YOfgM<FU.SoksmWE)8f2>p&6iYe
&?jaL5TFhhB0%q&%+-OnV"5r0'SCIK1j5/5)No:9&*[,qndlm`m_^DieRi2Wr^O?Hs^GH=#u
!A4t8aLsVACb]A9>jOrX"c4_"WLJ25\J3d1h_CSC#kioA4/HtUgK^4V'.S%&4arZ>FT&W?IXM
#s9!l`qdCUe2ni;u,?(dKVd7,RulT-W)3X=PEVI(S#"n:2:_@q5E9C%'3^P0DW=e]A_.Ei`&i
WDDal9C::YSV+i3eC5Yfse`:70MFsVa<`<U)"\FrH$^houWKep$!gXR`>@r_L8g[/fLk:/5^
^lp2RdoeA^>JU3fCWA#r6lb+F5H(Cc[DDJ^7THVcA1"VfOaZ<m^E9`oZZ6\Q4e:K(?j-?[*:
<i-PPX.(DZ.k-\*(3EJ"+YK=uU(b>GULm;_X(_&a/Jq!17PZq\+mXKtEs[&`$32EUG6W#u*V
Os(1?E=?G82a+<8mVGaa9rBRGI-sTUD>_*:$V/LFB)>:R&4G6eH?aj"e)UX(rT-kJRqo96?+
LgC/9]A7bCeslT@6/(n)6OLZRm<B)Rj-n6YOk2+s7VjTb'*45QTh4HA-<sDL,iA_?Z:Q6fi$C
Hq+5-pjFO(5=C->u&u]Ag2_SVIIod<YphjRs^hL<a$fUTWhIMI(SiEYbX3;YA%`P1[QgmGWG=
m-`0fFW&;`iWXh_+IirZX+.U"tHIK=DR6$2^+,TA=$n$_q7\SSe5c(cj(8tl90Vfo;U?tNsE
\]A]A#oVNs*-B:cWlS^]A&s>WWad:RC8Z8)%O`KE'<:U4QBaJsA8"eqG*r&^+!>fb#TH9oC;FTY
$*&.?U"kN"Xur\fo13a?MO8_$,88$Q'@luZ6^M6m/<mh]A3G?%-->@@tT4S&L.AHN@.*sE;=%
1BE1Fk,lHE-WRLR>MlB@[gTqeMLbMO9ilM=JU@8bB-C.eO3/%kTu(ZJC!nF&'s`n#Xm?h>;[
/G[1:HH1_WRpS=M37@Xf/S1`N;m((frl(V_Sr5fDb772jRRSL(AF*Hj#c1B/VWFDebcuVc/$
;6,%nBKnCk=3ccQek]A7F+[!^Z4CKTY<%MGEb4EfqN.i'Ws`u3iKc]Ae)ctPNj3[XsSXNUQ')W
IoN<M#iFge&iY4Y,b6AO1t1csa[bpNU;PO6a.nJBc6ZSs/`LRhSB*,[>MSpL5#%o7Wk5@-9]A
JNG)+[aHgX^_68Wf?j7V'K;_s0mT&3FOF!0Y3e5kfgN"G]A+/EM8lmeIS!(GFbHRLM3Pff!o/
Cf.UbAW1hO)q9p[/rWZV[&V%Y<gcVX(>RSc3Z_ctsD@^Hf'@/Q:Ff%X#Lgqjg\NKuniPHX)h
SV6Nen98Y[Y@QC(=c$m't`S]AJ-?doYL?p=ihWP8'SDh<_@dC)ikV+8`#bZC0^5f_>;HILX7=
1.hVLYd!^ZY_%1n)V=W;7F<uVg>9oW=@3molC8iL8N6$CJ1DQgXJSFQ<*e_HjDf9cj1@M/'<
[A_Y7u6mt))*o2tM3332$`+47HYms$QXjLXAu,R6>EV8_:M]A`XM\m%:n>8.Fo\'"81YSXd2J
b@p"OP$+cY2/D?+5X15*GiDJrg\[G#^?E9/BHlX+\?V9:&`anCS9NXo/^L!4U,Ce6+m]AV<l.
1of-q'>+,Ik;7-O)L6a/cC/qH-kcY69UeYis`FH.`f(4E/oO?[uH1ID!%50!#a,'\IN-11p6
7h,@Qb8s+G=$.72ohn"pWC=R(Tl<qe^iuqn/$%,E\8o1iFof,tGTL%%f+f&p)\<r,_Q0FZ,B
0aZ0rZk^f#u?<AP6Y)e3^^>W[>T$Vl=S888&!llKc\A5i+B;sjIio7j2TIE]AdFo?/DRYh.;e
AKoeFtWW`:Y?-I&F,=0'@nOcKr9]AJ))<[$-;n-QohciI*qnGsfI#=Nm5&FDX!3o*1=hd#6//
GP!L',E$.bhWe+m*/)CKnl5GnSOp;"6Gf4Ui_aI$/8Q3&I)foZgWRTa^6L.pFrl#$5f8KYB!
ni'\i;JiMg0+Bkk1YF)-'+\htL@<muLS;qoWfh[p\6`]A%RC,m?\)WNoN[O0@.2rH/4<Q<_V0
.dm!?N",WX-F5@G?j0rMQ0=d'VQ6tXTbJt"A?7^Mlf>j=db^o5oSH7Oir2)Jk]AWcFG8P2*;$
]AFGYbLg8?Okl;;\"-S!7CgMuO7)?cnLhghB\;.X<?jc0^\tkW>6Xl:mOTBiT6.V>P7^G6bjl
2Slh@:]A/fZuEBA1dO;<\Z#_6L/U=0"*=]ANEq4$&4&]AgP*DthmE>.Ym2(+NtB#el7"n"Z@0pm
AKfO>4#I`aBAK#R40H6>ND!LB;FP_0lrH:g.)qT*)LSf>lN0oYQT/5(0-i:8cphKpR;lUkXM
(Z4LsDHZP+rg4=&.0lEO-Kkp3SN/p'dSQEpY$Z;&jM2Qsd4j0]Ac!Hoi:mdWE8b/R)%OjA$n/
UQXd"GbjenCH!+`^q0h"Ib`=mgl[RU;=hW.L0>8aHJD[,0%Q)nsFEO"XaQ67RWNqacMRl\tN
I%J'\)cN-H)&2LWI(n,B()V%@"pQ'!'?U^-/@*!JR(9#<R`fqk$Fr#5Btagb^"A'.(["brU1
5#*Hh3&WipUjH5;`)YPopXRh9<NN/`(?5(heH*SkaWJW3H(_RNT\[p)I6VK>m[YL=^e_8rkZ
Q_LFR19F6@ph_\.Z82osel1+aV3+hBQPBj/k6^I#_B-H[nCf^dB9(p3$a0b:gJ:u&*<?,ERs
-?,"sIb8h:@QG!bCmY\kA/@qu%<)M2d@iQZ@b&@Tn%4b%D7YDMpcdqqfo$_Tq3,]AX7#=[gX2
!6N!OE=V3kXC&D$<id)*f"EnGGPJ5[15@st4`3+Nf(QXQeRl8QoEne*L.64EB;97&;Mk!Mr2
RV"]ADk[NSe-+)Z5tWTG\Pj9=.OI+A;)Y+(@YGm#\$j1gk#lN;CtK"iaVG+.<*&e`Ns'@od,-
0YkGc,8Bb?1@lS(1JK&d6:OOV2r@bfA?D`sGPdXpFN=#Mtu[@AI_Y[GI2o5:6t2.(d1YDG8S
7pNCbanos/[G;Fc"6&7>S:`Mii:0(7^!:c(JW7'nD1CCj>V^RSPJ#@s;/NKH>VN9/AYF,t]AR
(G7'!SkDHrgAjkiU-H%8t0VpV4O?H1_$f/%7C=cEo4JIZjq6:"m2^RGPnu1.6P^iClV0BD=.
MadWMW("Q+s"1G[R^0sGA^'rh01ZiXbqFp,urkVr2K0Y\dUSWh^oCii!J&^[!*#N#<Sf2k-H
ojY095Q_WP2^brg-$]A'S"kUK'9aL2H/>+%TMaNpm`Y+[e.QHm^!SLhF7OpQ^'9.+>?'`biBm
N'e)N"g0f`<;'gi]ATTjNpci'5W":k$L4,A!PNQrmKJReq:)'.B_Q139aBYI;r#LH6!OSa$JE
\fs%J5:OSG7G^S^O`/2J@Yk!'go`gPDhGaf#Ze\\1Wl:.Z<d.PN_(\K&@L_gY=S:I>Jd"dEr
5>LREp+t&(4B0$lrf)N6Wb,UYeuhX&Cl;&9BAh^Hl+?V_ubkpf3+f.n]A*!2PVWWpd1W5BsK7
m8R)s^MDcE=i&Mn6J*q0@gJ+3kW'^3ce?>K^QuR940=K*+-raWU51j876b`T5@^OV:b'Q4+(
#C.6mE$95Fl=52:R$,uWS]A6'43nDAWq8n__g0!9R)%mp2!bYG/\Q3M=X&.VNP@St`<oS=q=B
uck1Ae=6JjiV<eOpQZP)qk0:c]AHIQ<2A:$F&4h.l1V/TuG^aRAbQGE%`=+,.M\I<WZ_Ng:1C
b`uf35^t'os'g2KjSQOq)^62K)-c_2Q=]Ah0Z^aD0`u>f]A/^[m+i:Mam`t.>d4O66/rmMAWSG
L4]AYBG)3:?,Y1TBOEJL\TRuMF`)R>0`E*dSD/1VfMO`-oK2qr)I?99,?ouc0^@37#h7-f`#U
K4feh@"WQcg6gRP5pUuETQ"Dds@6nEf:,lJ+ZXaE;\UW=Y7ou/_<c#ej.9t8>j6'2/j$q<kZ
f(>m'^\.k'.4/@*IC`N[b6J`NIMj.osUd2R_/K2c/hYsgNIu!m`I>pgq/M"%\+t6cF>l:mMT
]A%J?-&T>CX9++(g"DcC`nhYbgFFPe$G?KR9'kc_B<k^+2E-@19\dhmq[A0.1CFO35n.Yurba
EOFW6Hb#.\FgX2TjS`i02-ER2W^]Af5B1#q.YgJFGOW8s9VnMYHa1CpRFk7luC'1gA@TP2%1F
a%m$$6=KSrnnlW(@XM<o4eSWBVE1Sf[d^pRk[u-lG%JFoAG]A7r'3LE.39"$K5gTpj3'\eERa
7`OeQ7?]A>KTD>dr?)R6j"@ds`Cq6#hj>k`*m2kn+[^;ITBhd9a^oqplWDR6;&ld7+XdOapVg
*B>\=h?&J9NQa:DUl0Z`6B'YEt#Y3hIUcdN850FPnB[;GueP%k)M`'*Re$n_0O'8!JUV@W<^
I?=t1a8b"CoEV%[-C:"$DVOE<71U.6@`*H]A`q`N_bc5aBc$X!YXe4"-m+NCH,TZ1`olq%Q1L
b1+eA`]A'OD8`X^7kLAD932#qR.83?0@.m)-)#JW[^B.\e($ut5hTa.4E0)a+><$$rM:aII"W
X7X5VPQ2>F4A*,%QO:+!4-'l.V_uaULi[#s(&EKt.Wc`>aW5]Ak)2@>-X:-?qWO2%]AJq@q&1.
rgR)F$*=Oapj[D`PG\N2[X*L"s$#Km:7#fe/)UnsU),[(;9mD3*3hOcW.p9+\GpJQ,/uX-DH
A)]A%jp$W=L07S^Cc"aFr@E_rJql4FN]A<JZQ=_TSHCCHe\lDMOEcacQBh%eh[Sj.SeG+R'h1R
AnJi0*&Yr6o<A(3c2emN/sK9X>HjQ^]AD6)SJB5q`j=J#@Of/I]ADN`0Vs-ZBQgO[_H/$8r2<3
*2^/jZc%BJ)7U@dM.fVeo@Mne/G\QsmOpL7OUVH%_;oF`O=O#<AM`u=GiBGr=Jo\J(rcp.du
p[o-;Tc@M`Mln8'VreCMG3)XeY28#IMchM]APEOf-1Hpq*^X>W%T>nXo"Eu=I+S&.8Zh`1c$A
gWp0f6=3DRZ=)b\48IQ!DMm1/e9#(%cTHcP)\2!8kQ.*W$pK)#h]Au$U/;3DnlE/leXD/gkk&
6GqohPrRf'm%H5ht.DM`+U1uRC4dEP[J`.o6mlii*b"cMbtI]AY$ZD5AfG)`]A%*:FQc>bG)k@
nY+KLsY1uGjWh^2eg?q:h.aqb"@JRIe`>>.89MNpb,mc+/JmZn4Cqj9L/d1+k26S3"hrV=K]A
3j.(\gCfmX!@`Rd-f4<oWi8`,m:+s4-H440mIlj![BSZ;-^'ea6:2",7;s&2MK![,#0G@DSg
+groR#9o>i\;$2]An>Qbj7X,bfB#+O>MqVp2uC_G>#rT<ULS\+BtXfLn>pfQIQ>:ZYP`gUbYL
e;)_LCm'/a-TNC"8G(.AIB9ji!'D#i%>0XkpNHi2Ml(l-RP^U]A:@NDS)Aa=JC2<em?R9J\_M
j4(b,PUj\`HkP7)iZhh:!kW1b1/&>N%l9s6Y`e"s7h=+mUN%!Pu7Y.Pe%^W;tr\/6AsWE8CZ
1/C#0h6c4@jRo2HkihkGN*Rt,99O;\X@77oMlV!&^M#l,qC.@OCuZYiPrlGM9%Nq.%hD.93f
2H`A<YbmgQCF)#C.;nIRH<$.UH;%s;E`MA-Es##dl0;F^UR"Y**`P@t^snrSI8G^j<0mtTaR
M6!%\-5SbB<\F(5r=Vm\5Z*SP<9?8ufV,BS,gu-G4,.U,M1$U3>)QGZJs@BtuY_4@5P=kI&D
ULp$;"$Ir0IO?sNXckp=EQXeD6M%j4;\V>Z$5-__RJRE&IMWbeZTS7>3\$`IRPtT%[$l.55Q
]A[ZL6uupl=&6Lqgs/=($nJ#P?qOgoP$THsA1P=h(m]Am]A]A4G;hDmm&1!^,IP^inJifBej/Fj_
5!!4HI8($?2D6ZX-diZgUN`I.fb@6gQq58cnciJAjn/AXDGDDpKfNR=&.^([4[S'8-DY#U]Ae
O^.`9PK6]A@EL#e74rtrBR\0r*8fr\g<Kc.=7u-C5bT+h!_DaB,@W_3,A?DTkNo:=?@T-q^!-
\[m]A^\9U3W1JEY_QP*JQW)qA["NKUpk+E@T8C%di/O@`$)*$L_/BM8"-ABLog012b<l!:V>a
a,mU*S_6UT%Vamf$jLQfcf%UTg%<F+lK$jj>k\CtYPr9P>#K"X2R$uMVH$i65^e./JO!HWO$
<k.)KB:I]AlkXi5`d,Aj<S`WNN6rMICXB`Yhqi1fd&E26eu%#TI3j_!(0KDQ4?<_r[?5T+j@r
*a(hL#q2+V';qJjn7?LhagJ?h?k^X8Ho8:HsVMp.g_Oojej4h@rg@hfR.<Ljbn<W&N&NLlH&
-G%*m'o11Ac973JLCg1Q>W?nLH)),,Q=,S@ZI0SnoT'WR4"l&(WL#_;*/Pr49bT[Zot&%6Xu
5N"-s@)S#9t,rm0hoJNg:\!2OLo*64r$uGXkf6Yh_sd/2Pc8H4u.dW!IYU$]AG>n'ro\D=Z[;
^$h:ba7a=Vrk4%[d1YFZld080:-N+=%_t#4(KEW,DFb?ZQPIu@E=D5d8PkjXg"0cZ7&Aqn[d
7*b`_FWWe+R02VcV5&n`>W#tTYM\il+SPuZb7e4C^HH0'`6p)NP16qr8tEa,Sa0WUaCH^ikE
HP?Et+U/Nn677;knQgecF)@!Gp0*u6!WZEj6B_SE<V^AgGk_k%DGqNekc!m6/Jn]AmbWd_#R"
B+`Oa1=Qg;p!nAB=)C2DdV3d,+/e4H06rB^"9I'O37p7e(GUQPKKYO;95HXW#U@L)_Bi^"Hj
?)^/h8#l+q`@E8,h%\%h_9)e4S`GA/"UjA%]A-\6<I[$r:;F8&F82EI-3mr<A_)lo[,EQ^36$
K^O:024/ABX9NW:S%S)1:QM!)k@O.@tCIaQ[H&VVD`6,Cp[L;lGUt2PlL$#sT%Hu+Q^ETp40
(QH"5?`1&n/3e'!aD*Y=iPBg@Jo5h?;0+#7^ICTW;IcK8[3\gjZ2roA0mZ?AD5m\P<Ymh$uL
^gA*EMKqj_QuA=DNA?Wb@`:6*7MN2clYk4";4]A+[5A)iW[aoYgNa-LJsfG#.O+']AHJ/M3D56
)+3`e=1<rKE5eG,53o[tLZHB)p,\uKmsJ\VBeIMs1Td2sBg[<rY#_?3gi!_HK_+b,>I-;VTd
K&eL_";ImdBQf%H:4+IldP;WJInc'==g!+'Q<!,048-ds(K,S@I[1]A^6!",^]A1?/=&V>IQR1
G1qPtsZCd*#_OTu)4ESRmoZVYt'Qh(O`I[PH]A*W$Db4$(Zjnt,WZ)oItEC6[Eer=bAlTaTt)
P7"#4GW*pa6T),c&3*Pm`7KY5@SgDiVd;s+8<fujIc0jm[+&jYNJ[\,HPZF2ZnC6Pu4=I-rf
RC,#]A<N2/HYIpGKJDJ<j5%rs29iG$d#X-^K[DWG$!e`j/PsK'0Bm0,B/^F"+k"To?MCKbnn*
n,l3tYkh",F)W/;\VBR^dI\<u<rIamemX,V:3,7D+@TAtmkHWh\9RXKTZ=@FX!U;/1Ba2BV<
L',Q\);kI]AGc?BXK[TJ"\M9kc%`=Y$!_90U;*V?^f9t*<"t2X=ESTmn7E@n=r#h-N-XC!+<2
)ptIq-cP["Je9*PlLdZ4C4!<rT8(ok0?6JF97[CPt*Sc)kjkkhbhaU1,@sMD1Qsl*s$(6i;%
YU$r6)fA@3;?to7._gMeaWUlj!B1JE<\,!57b!VMBu!@*$a?F%(*%-.dr,5d!l&jf`4hMqao
7D97aJ'n6Can7%$+XB9fpV:n!n43WUD'k<'KG6H24l"R3O5"s4U9GeVc;-43Hdf$II$$AJXJ
[,-^%H<AZeZF$W8Agkr\m2*[O>N^&mp]A/Ce^TUi"#Pp8gV*:0.P3b@4>8$]A.k>snJIg&n>^P
hRGn8/>^Rb;0V*"E[$:%g]ARHE]A"&.+qaT:CV6oSL-rh-@5G/$[@e2;+X%o2EZL.>dT<8jPP(
m&Bt0&!tRGWKT.J9q=F.f:j5JC[H2>ukW`[P0oF#hdJBs]A.EUSKE;-?L`[cSI+PY<OV,Mts^
1$u#jl(qaM1>m@]A0'jA?qt5G'*#Y3_=EL\3L8R-Y0?u<UtV=:Y!@+G6^omuRL=_ZJ+kl>5R@
OFD(UZu``.C`=nbt*]A)QUgrPo>bM/ZcqLW3gO'U.Nd!T#ha]ATb>_0N#e]AIQZOIqM1;Dp[H,^
1ms52`s@4RAK/=>Qh:[[79Y9lK*D9.iqP#8kGg,rQiY%*3H?Kr"OM2c%&jdY9$kb.0'7TpFQ
S50/+-.3hdLSV%/*\iW8hi='#Zg2Yjb\(^1ir\Dr:\Or"E?$R]A*)?/T$Ld0EKd#1aZ#>n.Jm
1cCP>Z\)CI!?V-)D$kJ2VfO5r9K0kRD<UK_f:<f8cO+2VD=OYlqLlZ*oJK6qqHY4<IoDWq@/
"f1g("e.9ds=YgB*FYAMAQ(m=jlucg=1)W%Yr77Of_O`enj%<70BPtJ&Liu2u[etWtCKpAQ!
M+Ug%nq!L.\a[4B'-I6Sk!1qoZ#OH^[s"s@@iCp%>7alfQS!"BbdFH0"6gS+r%5Cd6(en+j\
Nk,3i<6Cn/<3)ZA/msS@X"[T5PJsQY`W%Q5')Ue708t_PP7"lVL'</`X)=:i*=IO/+[.Jtp*
Z#D;pKclV"GDn_$95HLU&!(6&fZ6.mEZf%f%Y2I]A$%u3'f#m-olflUO@KVoGan('*7R2_+MX
p5i#X52P9-tls`YHA:RRj]AskGHqE20$Gk2:>qh:In(?#$H5\M]AJL&l%1isUUn'"`Z-G'$nsr
',VEcb^e)1=UR2edqBS@]A^KZ^"C:I>F,T%I0&+Cn_Y6;=l!N(o,g0\'ef'srFdI0/3lOXZn=
s;I]A>HPFH4/+2RHrDg@TYu7t?h`3in_@q.\pa4-:W-O*6kSruK2hBOD7*9Cc[eY3JcM[1BJC
3W5iB:@B<t8NmA-LOh1hj:%YTciPO9,NqH:G(lPdg."sW66$;U`sb`Ve4;t5Tb1,@7+3'LFm
F,2o=ThH+5mu-Sc<P@ooDB60+*q2]A"5kr!bHqtQ,ShM1&(hP[M7#>Geg#'gEqA]A:\*Ym#2]A#
,dSA>fFHj#cAlpuhm-9HZMBe5T/IF1DfiBk)/N6<$[TfAK_gcjg5/V-jhaRVnGO=9,fjUTL'
D1`MREGG\g5naAio0*#?bkK*,<Z1!n7$nN_[mq6T/[,hagiau[YCK,g`(j@YJ/oZT8qkp*Q+
SXQSC#uoENr8291,?aLc4+mb_qhS*dfakPkV~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="568" y="36" width="622" height="346"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='  '+'店铺信息']]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<border style="0" color="-1777440"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="622" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_report0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='  '+'店铺信息']]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="Al Bayan" style="0" size="128" foreground="-11976882"/>
<Background name="ColorBackground" color="-1"/>
<border style="1" color="-1777440"/>
</title>
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
<C c="0" r="0" s="0">
<O>
<![CDATA[店铺号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="guitaiming"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O>
<![CDATA[店铺名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="dianming"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="0">
<O>
<![CDATA[铺位编号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="puweibianhao"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="0">
<O>
<![CDATA[合同编号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="hetongbianhao"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" s="0">
<O>
<![CDATA[店主姓名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="dianzhuxingming"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="0">
<O>
<![CDATA[联系方式]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="0">
<O t="DSColumn">
<Attributes dsName="ds2" columnName="lianxifangshi"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
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
<FRFont name="微软雅黑" style="0" size="80"/>
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
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="568" y="0" width="622" height="382"/>
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
<border style="0" color="-1777440" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\'  \'+\'商场分布平面图（联动）\']]></O>
<FRFont name="微软雅黑" style="1" size="112" foreground="-14898964"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
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
<newColor borderColor="-3355444"/>
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
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.map.VanChartMapPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="0.38"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapLabel">
<AttrMapLabel>
<areaLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
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
</areaLabel>
<pointLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
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
</pointLabel>
</AttrMapLabel>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrBorderWithAlpha">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
<AlphaAttr alpha="1.0"/>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapTooltip">
<AttrMapTooltip>
<areaTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Al Bayan" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
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
</areaTooltip>
<pointTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
</pointTooltip>
<lineTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipStartAndEndNameFormat">
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
</lineTooltip>
</AttrMapTooltip>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-13312"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="4"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="Al Bayan" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="false"/>
<Attr4VanChartScatter>
<Type rangeLegendType="0"/>
</Attr4VanChartScatter>
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
<OColor colvalue="-10642707"/>
<OColor colvalue="-10237754"/>
<OColor colvalue="-19351"/>
<OColor colvalue="-9111"/>
<OColor colvalue="-15033779"/>
<OColor colvalue="-7239179"/>
<OColor colvalue="-7881222"/>
<OColor colvalue="-97670"/>
<OColor colvalue="-6812999"/>
<OColor colvalue="-4520142"/>
<OColor colvalue="-15714713"/>
<OColor colvalue="-945550"/>
<OColor colvalue="-4092928"/>
<OColor colvalue="-13224394"/>
<OColor colvalue="-7881222"/>
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
<VanChartMapPlotAttr mapType="area" geourl="assets/map/image/广场一期一楼平面图.json" zoomlevel="19" mapmarkertype="0" nullvaluecolor="-3355444"/>
<areaHotHyperLink>
<NameJavaScriptGroup>
<NameJavaScript name="1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="PDp"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
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
<NameJavaScript name="当前表单对象3">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="PDp"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
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
<NameJavaScript name="当前表单对象4">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="PDp"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
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
</NameJavaScriptGroup>
</areaHotHyperLink>
<lineMapDataProcessor>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
</lineMapDataProcessor>
<GisLayer>
<Attr gislayer="null" layerName="无"/>
</GisLayer>
<ViewCenter auto="true" longitude="280.0" latitude="290.0"/>
<pointConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</pointConditionCollection>
<lineConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</lineConditionCollection>
</Plot>
<ChartDefinition>
<VanMapDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<areaDefinition class="com.fr.plugin.chart.map.data.VanMapOneValueCDDefinition">
<OneValueCDDefinition seriesName="dianpu" valueName="a" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds3]]></Name>
</TableData>
<CategoryName value="dianpu"/>
</OneValueCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
</areaDefinition>
</VanMapDefinition>
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
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="568" height="626"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='  '+'商场分布平面图（联动）']]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="112" foreground="-14898964"/>
<Background name="ColorBackground" color="-1"/>
<border style="0" color="-1777440"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="568" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_chart0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[哥伦布广场一期一楼平面图]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="112"/>
<Background name="ColorBackground" color="-1381654"/>
<border style="1" color="-723724"/>
</title>
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
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
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
<Background name="ColorBackground" color="-1"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-6908266"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</GI>
<O>
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.map.VanChartMapPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr class="com.fr.plugin.chart.base.AttrBorderWithAlpha">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
<AlphaAttr alpha="1.0"/>
</Attr>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapLabel">
<AttrMapLabel>
<areaLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
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
</areaLabel>
<pointLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
<AttrToolTipCategoryFormat>
<Attr enable="true"/>
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
</pointLabel>
</AttrMapLabel>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapTooltip">
<AttrMapTooltip>
<areaTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
</areaTooltip>
<pointTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##%]]></Format>
</AttrTooltipPercentFormat>
</percent>
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
</pointTooltip>
<lineTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipStartAndEndNameFormat">
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
</lineTooltip>
</AttrMapTooltip>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="ColorBackground" color="-1"/>
<Attr shadow="true"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="2"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="false"/>
<Attr4VanChartScatter>
<Type rangeLegendType="1"/>
<GradualLegend>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<LegendLabelFormat>
<IsCommon commonValueFormat="true"/>
</LegendLabelFormat>
</GradualLegend>
</Attr4VanChartScatter>
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
<VanChartMapPlotAttr mapType="area" geourl="assets/map/image/2Fmarket.json" zoomlevel="19" mapmarkertype="0" nullvaluecolor="-3355444"/>
<areaHotHyperLink>
<NameJavaScriptGroup>
<NameJavaScript name="1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="Pgt"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
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
</NameJavaScriptGroup>
</areaHotHyperLink>
<lineMapDataProcessor>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
</lineMapDataProcessor>
<GisLayer>
<Attr gislayer="predefined_layer" layerName="高德地图"/>
</GisLayer>
<ViewCenter auto="true" longitude="0.0" latitude="0.0"/>
<pointConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</pointConditionCollection>
<lineConditionCollection>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</lineConditionCollection>
</Plot>
<ChartDefinition>
<VanMapDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<areaDefinition class="com.fr.plugin.chart.map.data.VanMapMoreNameCDDefinition">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="guitaiming"/>
<ChartSummaryColumn name="dianming" function="com.fr.data.util.function.NoneFunction" customName="店铺名称"/>
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
</areaDefinition>
</VanMapDefinition>
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
<attr moreLabel="true" autoTooltip="true"/>
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
<category class="com.fr.plugin.chart.base.format.AttrTooltipAreaNameFormat">
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="568" height="662"/>
</Widget>
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
<border style="0" color="-1777440" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\'  \'+\'累计欠款明细\']]></O>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
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
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Al Bayan" style="0" size="240" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
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
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="72" foreground="-1"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
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
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="false"/>
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
<OColor colvalue="-7881222"/>
<OColor colvalue="-7239179"/>
<OColor colvalue="-10237754"/>
<OColor colvalue="-16750485"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="0.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="90"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value=""/>
<ChartSummaryColumn name="index18" function="com.fr.data.util.function.NoneFunction" customName="租金"/>
<ChartSummaryColumn name="index19" function="com.fr.data.util.function.NoneFunction" customName="物业费"/>
<ChartSummaryColumn name="index20" function="com.fr.data.util.function.NoneFunction" customName="其他费用"/>
</MoreNameCDDefinition>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="891" y="36" width="299" height="244"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='  '+'累计欠款明细']]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<border style="0" color="-1777440"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="299" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_chart2"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[累计欠款明细]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="112"/>
<Background name="ColorBackground" color="-1118482"/>
<border style="1" color="-723724"/>
</title>
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
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
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
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="false"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="891" y="382" width="299" height="280"/>
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
<border style="0" color="-1777440" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[=\'  \'+\'当季实收现金流\']]></O>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Position pos="2"/>
<Background name="ColorBackground" color="-1"/>
</WidgetTitle>
<Background name="ColorBackground" color="-1"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-1"/>
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
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="3"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="72" foreground="-1"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="linear" prePeriod="0" afterPeriod="0"/>
<LineStyleInfo>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
<AttrColor>
<Attr/>
</AttrColor>
<AttrLineStyle>
<newAttr lineStyle="0"/>
</AttrLineStyle>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
</LineStyleInfo>
</TrendLine>
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
<FRFont name="微软雅黑" style="0" size="80" foreground="-12759706"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="false"/>
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
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-7881222"/>
<OColor colvalue="-7239179"/>
<OColor colvalue="-10237754"/>
<OColor colvalue="-16750485"/>
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
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-854793"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor mainGridColor="-854793" lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="0" size="80" foreground="-5065027"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="40.0" fixedWidth="true" columnWidth="20" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value=""/>
<ChartSummaryColumn name="index13" function="com.fr.data.util.function.NoneFunction" customName="前期欠款"/>
<ChartSummaryColumn name="index14" function="com.fr.data.util.function.NoneFunction" customName="当月实收"/>
<ChartSummaryColumn name="index15" function="com.fr.data.util.function.NoneFunction" customName="预收金额"/>
</MoreNameCDDefinition>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</InnerWidget>
<BoundsAttr x="568" y="36" width="323" height="244"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="Title_chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='  '+'当季实收现金流']]></Attributes>
</O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="2" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="96" foreground="-12759706"/>
<Background name="ColorBackground" color="-1"/>
<border style="0" color="-1777440"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="323" height="36"/>
</Widget>
<title class="com.fr.form.ui.Label">
<WidgetName name="Title_chart1"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[当季实收现金流]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="0" autoline="true"/>
<FRFont name="微软雅黑" style="1" size="112"/>
<Background name="ColorBackground" color="-1381654"/>
<border style="1" color="-723724"/>
</title>
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
<![CDATA[新建图表标题]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-13421773"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="0" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
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
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="false"/>
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
<AxisLabelCount value="=0"/>
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
<AxisLabelCount value="=0"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true"/>
</body>
</InnerWidget>
<BoundsAttr x="568" y="382" width="323" height="280"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="chart1"/>
<Widget widgetName="chart2"/>
<Widget widgetName="chart0"/>
<Widget widgetName="report0"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="1190" height="662"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="950bf13f-adc7-40e2-9d22-3f2a81644ca6"/>
</TemplateIdAttMark>
</Form>
