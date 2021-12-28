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
</TableDataMap>
<ReportFitAttr fitStateInPC="0" fitFont="false"/>
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
<![CDATA[723900,723900,0,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[销售员]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="2">
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
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript脚本1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var obj = _g().options.form.getWidgetByName("report0").
element.click().find("td[id^=B11]A").text();//需要获取其他单元格值，任意改变B2的相对坐标即可
FR.Msg.alert('总销量',obj);]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="1" r="2" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(B2)]]></Attributes>
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
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-3342337"/>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-16776961" underline="1"/>
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
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p>PBW<&ctF`#FCu>i(UDl"m:.lH?B?@o3*LGc'\SbN(EruT$ec/dEXA)fe8]A/;O\T,5Ef
8G`Chk1$(^*/<b%TKQ"QW>!Kkgr*&kF/>AA:+8cV2HAH0Di`5O\n??bZGUkIfujmlB:G-i\J
Ui(D=<ShM!tjZC5*q_/H@+b[\e2nZX/qBRI]APR3!0NIC!Whu#[?5V9tTpIo1@b7oElfPeO/D
>fM+huEPYipE[gO'UCaFm&Q8^O)gU79.O-Y?pM(Pl*2KQP'gXr6G0sDjiig?.OJ4\GoXd^\Y
l),Bb8XrVfk/KO4]AKAPq5Me%MoW$<E3IUGKeR,F$aoBf+GjlP'dAn`lO!Ga?0"&YMgLDJY[5
adTeu=H4KU9n-o;YP&U.g@V;f4/dFl0\3Aq2R`4_c+c]A#[=<3Y)bs#/?Z-<D8@QG;s6^h%"u
Nm^cGN5"_k#a8O1mW<R!0[[bIi,&ncku4lce5pHhN^V_Y(sV#O#JR?``a01HR6?@&0/s^3Ja
.K[q>'n,q_#*;if]A>2HB1Gsh+c!TVXC#O#7tPoCr]A4d&Vm4sHCc\1;0o;mgULPijc(lG0"/2
c9FC.:!21]AXIAD`Y,.EQZ_#?dM%dHZ#i]A,#rJ7Z68ki(.;32Yb-nq0#.$M?A>T'0H,7#<M@,
-j%:&GPNSmbN'd`ich"LMf68nSC8:4BWpe)4.-2t>_q?&$QCi]Aueme\,JB%j5[dD=CkX%5Zn
mp=R7a7:9Y#?&V_L%Z3V#kO<HTB0H"\a@<-9_1Y=C$Q@P)C5tS4ro9K7T[Ke\g^El_r7VYiQ
79YS*`u=)&)r&>%/oRNWkk:KOo*q6Y<"/&J7u[kKmMuF?K_C8'0Q,naW;M^'W\QRlf469>mC
.1?X?MYrDtLioRB%Bi1t[JDQp2Zq#qki[aNqVSLS5f&3PhIYqL+F5fK*3@Z?R:'i6a8#X(Xn
RY9QCNapjA_@2sDYk8[A93[Jn]A*K"6'q:U>P]AK@e7d0<0<M^)3]A`4Uqe5hpF&Ta5p"Oq=5,7
?ek\dl=R<K.7<U/-`;#Y/f"^.[VQ^b2fIi\LZ0G_.Y[2^CO\k'nbIt*V$(5LX15L?kU$%\1@
CP.iK6=ii3_DdI,(u1[/CgRUXa\oi91&`qngO>#Fji/IB^'T!f^!.5OT#5BBqi)?8bh`X3+E
i#QcCF.sdd6[u29nif<V@tK[sGFR$Drs^etKj0*Ah`:UT"h$ZY^bF6LHCa\sJfbH/m`\44FA
G'nk_]A4s-u38B#8gH``;+D!T!HZ&]ArBV]A$KW(jo42ifR>4l5e94<qKr4VIKS2JNYsE]AR6\Pb
F*tDpMW3uH^U@N'X@E?qfaqQMMe%:oe(QP1E>(-oM$&]A)kOLWlcbll:OVTT_]A@[$_lsiS#k-
sm.CUQO\+FXRGGkAAWoQ$U`RJd6^sDDs3sIX89OCj+6^fe^U!gUPC4t;>)c1UG+k"3<k*V&_
ZpkXib<t[29%LUQ8e\i'G*K5Qj2@Gj<<p@c5&`/mC[d:\[df#2H=W!ZZo6`=5F]AY8bjiNs6o
Q!Tf?$*`pa4^WRJhZc^1Ngq>QGL7Hkg]ABWn+lP`d\9]AK4b#3?W[LGQsH9+Y6p$.GXJg:;)\9
$1_aq^olWI6Ct#e==>_anqE;54TdjW+]A61M<HGo<8>I;Jj\I+PZ`e*h?GRqs\pkeN:fj-9O"
5WEH2CC^ZpV;dUm-1755PX6F72]A$l4UCFeklD^C>ERoc^U5ar3NpM?Q3YKR&[iCJb#<)j5T(
fN;*m)I!"^m^#mmHS2(:p8$IHIYe]A;PNJ#$KKpQ;_]ARO5APT46Zl0_ZRB=DskT%4h<fW`3Vn
d=2XmfNq0"QlAu9YJE6!2HL+i4jH<=bA@_<e$31dOCG=&%uQ`cOUl*0<N0UW5@#r[--+6RAe
S*\RC(S&8FckadV0Lo6UNh,<)Kj<dO&Vj(e4\M3f"j?fRg>fZDqN<?589mV5[H(,-NLrX3o;
.TtF93lPnGQ^YE_AE2_2NggH^f-l>n*"0`kMW]A=]A*<&AqQilmXFQ.Ft4!p]A:L\d2TO]Api=NE
3'kGlG*peq=ZFP("M@iVCMp-$seb.:(M0:NGFscRR2QSmW/oMh7ek-nrY7Cb"[U''$*D*ora
/O@idPM[_Z3-P7k":l)54:]A#NSA>e+$G,.LtZ[Z<asF;r9-:6C,>b7!B0=0NF'I"r0egr;+`
Y+saSL-K\qEpYc#/fRCHpD94-VsS(dQ*oZ[W^[kC$^hI>o>K)^Y3lY-J:)))/*tNT2lNJSl^
25D.PXG^kiV=XN//MB4Ft5Fqup,\Yi)#r^gIh>Y)eZ\Ka.^^ir\WB<*?dWKk>5ilD&^k+!8d
Ene70snK#0P<.%Qj!+f,"i4O_"=3/kTP&RMUC6,leb:eM*GD/!8(*V*Wd]AjcV<B\3gmQ"7s(
:pqT^6RQ>9:rLn"9[W-FHr<_GX.udkb:Xb<#W"`K28>M_Ddis#gp&J=8dfn4AGf"6Was"7BB
>nGo<nD76]A5L'l6&n9JuUPh%R`-`:e50"ES_l,N>(Fp29U<Y4263a,N<j\OAmugSfMM5%Ghm
ia_FJ1=4SF6)=%N]AF_,Bafj014q>\G\b/m]Am@ZqD)e[i_Y=I'@1g@D&=?3?bBeYpW^PXDJY>
Ju5"fD4>>_Us^8a5hpf-Jpr*e4.1?<:"ihP3jh"S(bkS0::m.Hn6^=[D547i?!:Mi;X(OqAa
(lgbkCqV?-nqU]A@*42Pk%?HisCacUH"fWLC,j:4;l1_;&P+?18k(9:EYpQu;cBWu3'#+d-)D
Q:/;;.k&33l7k["Q)nFd9-83-B8<XeM?ra8sr2;S:r;ca?SUH[`JW(oJ<O]AeMh,^HV/1B>Jf
pXjpDP^K@ZVD/LS+uMd>F.rG0I+rW7\46GjImagQcP8&AiGr4knE9oVLYQq/#tkX9bV)mc)!
*@T+,>6n\BOdIo/O47eR@EmEk"'GN<&XD(4C.BHgO0%6[XbqReZ@Pb!igZ)fX/KM>7r5ErJf
pnBAr#0rF@ggUmMJf:WZCXM.JbQ\2;?17#AD7,$-dd[i0YA,&_G.6,P_CFIZB$b)0XA\@@K&
RC*V(lJ`0UT]AKLkL%96kS3Hh7l!IgotR\DNK#:;L!?$r)(M*?L=kr7#=BPNCZ0hSki.s"q6d
;R[IS@#Hj*;bs,bHhbE*-4,$:S@Hg:KWI6M4':mL?4#T;Onq/06a1:O+'t26RGMQ6-DED.:h
r4&[d"t(HR`85]AqP8+GAgpLTRjfDepDs\QrXUFj(5'/CiS;ZL';l.gB7KiWr_$dJ]AJTiSFnH
JL2^J[His/OGGrqU+]AGnpm.M]A7ES=f/P?nD.?g,CG?_@Gm#s&tAGZ[Q'dG6Pb3]A*Zkl6O?$V
?Y>3kdC?(:dAq\Y-u&2d`K(E[:q&(%nWs-[4.=>6Mqf.f/$*NGe5S$^cf;<u^Go;=(pmg"([
]A;5,i%<KaA]AY05u2h9=]AV)f(o?elXIH@iml3.pjSs*b.$d`$r=K]A1Ia*O3Z$B41>Bt%_!J.<
>@45TZ0ugj>H,,s#la-K'XrT@9cc9:i,W//@2uG"m5A.'\pBAj@0X7GhU,Wc:`E9Rrap;?K1
7=0O+>/$?h9l^#%oFLdJok6i&EphK]AU</KR5#j6E;!/F(H?WHP`t]A/G=gaA9k]AcFQGhEM:GN
"jV@MNEVB%T)p515`X`rX:0hLBdA=Z3o_L<i(t+<(N-_=j5K/UQTS#o>dYo5j$qc,\snqF&r
%KM^#Y$bL:=4SP00O_-lW2Ng*OS*+]At=O.i`1QGA0mO.L!U>%_*%R;3I+u"/5R-H-?rb6bbh
V4h<$lK>A7Lg9'S).Q[q".rIg?Q"PbL="j`,]A%]A;n+s?\:!]A"Y%,qRMDhrad&PoYZBlok+p^
sJM"V\%Rjecf5*ol9Ajb`uAYOug-)IFIbQKnd`k"j*lo8ZaGF+rD*m'FSpWqhqd9(nP6_^6p
_d"Yu)64$OmQf5J6T_<YbXH]A'"keW#8M0L$i%,X&afaZ82ns7rAfV*$Gu[:/$2q?4$:8pfS,
\ZuKQbeaH0h%Rl_O527[K:@CY@II>YeIb'4XDHqjCk%B5r4)>7TOm;MIXH^rD=+[>^F<?m-n
Bqt!\o)WNfa/ETP5OfAh,T`[Ahnta$R\gR1q>*HU.ad9`[R6,\Irb*Ad[Hi/A<k!]AR:e@HS^
]AOGFk795.dO)E>Q@Wufbc,\C?b7V-,iSLeA[$!reuX<%*($#[,k7G'71H+.@(F3ET`lp^j_J
/V4u=SFf]A+,dMJ[O/DHdaYUcnItt=[qD45S<^oZb#q7H2NWPh/Jq<]A7oa\)^W8Of)Nni\"dl
n$l%Midc`$4u6ao)aR+K@V#GZt\cY:^h.@t^k-WUPG&]A2^7AW*iL32(<[6W2ht\OS^XB'=t4
J:712GbM-)"YqAXF]A75Smab!lP%=%?R//pq8ok'*ehgYo#b9]ADg+bo*aKIdZASIb:l^>#V)b
>'V%LK%Cm0+96LM@2PQS"I.F!th>I<QN3p*!SeYQ^uWcWpsY*:gRMYi[D7'bM8ld(+9plGXO
ja8#lIR6'_!&U((.#u23369/=*lq.7*b$r^jSHIJA_&,%qj>B_r+<r6%DQ:PjXoYd]A]A-[`^n
6J,fZGKh.-.sKd"jI"CO$Y,M_XN=c+0%>a@:02D^C6\73Z4_S7FB:+4LN',N\p]A\ne109Me)
hb`'hiUTm7:E$k_)p#<`l7AM>oH7VI"!Trf4):I4D\9#ceJjO='*0Q3c&NF5W`+T-WZ!,-$[
6obASHCk*=9a7?h,V[Le1sG;a!9J1gBp+@Z`"fGc,"kj@C[5$t-I"mng`[)uG^;gD4OjRQjB
G>0ciH;T(*Y/R#8>m)=-%r*809Cqf:DL'0.ANbgMYLm1S+]AE[9hW@<rJ2i"Dc2fq/SkE]AF3q
gi,MP0SiGfT_OjEl+LHF3AC5Jj$F-@L&(2*i8h%8e-@$]A8=:u/EKKd;pjq]AW^c;<n4JLN\?`
kl^)<>Ai=\nDW?;R^)Nh$+sW,h+305c%Lemi-O)QW>I<[h1dlJ0-3.LVfY]A)rTa,+"3%k/!3
)Y5q4YF_uo;VWJDM(i6J=.$2&(?gcS@k3f/'/c7D,FrqWd,83F7Z*)?W[R!Mt<cF,jT<?VS`
S6d`'j1*K]A6]AhcmTI*)&$UYLG\8`H1g5W9V$`uH"Pa]A)O*gDL\K_@bus3*uA$dr+/($2oUoe
h5$8es&GFulh*WE9S+gPsYU%6f1a!J\3)$*4dIn%k+JRB9lgUH0W;Duf>KGZT%.?7pl9bpYj
Mk/S2$W^X^l\:`grRF-S$jqA/UZ:2l?C0E6KJ=4^2;]AOCG:V6@/@/%B3P*G[n_B\B&P9[dYh
qS:u6p+R3qo(\G&kDcLmiiCLIf\E8X]A(HTEkj7a2]A8[9[MILJX_b)!?QtX4(D81X_9c'm$f"
Je&8!XmKV,0E"'%JVri#*;)#aLXT?(@^9HfENcsJGGal7B`>qm67FB9+D=;o`/GT6h1?>s1@
@-!t!]AG6F!hT.J-d0h@1A+MuorN9[fI5NG2M),9h\1S"g5pOZCWD^MO[=jB^on?ol9E#bVpl
dM1p]AMkEY#7?L[.#-T[mY+rHQAIZqI4K><a]AO[WPF%="9sbGL=#>n6c8^A'`?9B3KZ'O_hSS
!d#52p<QUVd9Afds%+IL4(k[(/`V:(-We]AMH:%-3?qX?Yi7'RiKaP@&8,b2ObP.[bUiWe]AUT
Q]A:tk^6'W;@aoFa1>["=t-\hed,'HYG*?FT:[`Y2WkaH,Md)B9;2QU-dc8!3l5DBYa&(4at5
Z;7L$a4.N]A(/RO^WdEkafLgc>`U_3[_0;OZe5`ubmE1U`4HB>@-HbgH/40X]AE'B&+:&$KuPb
9itd<=VhWFL-63EO%.GJcH9P1SoDDT^QU?I#nU??mi.ZL4r_-'kDE"D>3&Ef!@#B>nf1OkiX
+%p`2Tt&besj]A7!2t5>7Je)Oo@[?dV'9g?'4k(bcOun5j#q$cM&pnWkb0_NRAXl?_gf>]AaDg
NNmp;Ani(Fb%L2mEZiAD,AU@o>r!r~
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
<![CDATA[723900,723900,0,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[销售员]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="2">
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
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript脚本1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var obj = _g().options.form.getWidgetByName("report0").
element.click().find("td[id^=B11]A").text();//需要获取其他单元格值，任意改变B2的相对坐标即可
FR.Msg.alert('总销量',obj);]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="1" r="2" s="3">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=sum(B2)]]></Attributes>
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
<FRFont name="SimSun" style="0" size="72"/>
<Background name="ColorBackground" color="-3342337"/>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-16776961" underline="1"/>
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
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p>PBW<&ctF`#FCu>i(UDl"m:.lH?B?@o3*LGc'\SbN(EruT$ec/dEXA)fe8]A/;O\T,5Ef
8G`Chk1$(^*/<b%TKQ"QW>!Kkgr*&kF/>AA:+8cV2HAH0Di`5O\n??bZGUkIfujmlB:G-i\J
Ui(D=<ShM!tjZC5*q_/H@+b[\e2nZX/qBRI]APR3!0NIC!Whu#[?5V9tTpIo1@b7oElfPeO/D
>fM+huEPYipE[gO'UCaFm&Q8^O)gU79.O-Y?pM(Pl*2KQP'gXr6G0sDjiig?.OJ4\GoXd^\Y
l),Bb8XrVfk/KO4]AKAPq5Me%MoW$<E3IUGKeR,F$aoBf+GjlP'dAn`lO!Ga?0"&YMgLDJY[5
adTeu=H4KU9n-o;YP&U.g@V;f4/dFl0\3Aq2R`4_c+c]A#[=<3Y)bs#/?Z-<D8@QG;s6^h%"u
Nm^cGN5"_k#a8O1mW<R!0[[bIi,&ncku4lce5pHhN^V_Y(sV#O#JR?``a01HR6?@&0/s^3Ja
.K[q>'n,q_#*;if]A>2HB1Gsh+c!TVXC#O#7tPoCr]A4d&Vm4sHCc\1;0o;mgULPijc(lG0"/2
c9FC.:!21]AXIAD`Y,.EQZ_#?dM%dHZ#i]A,#rJ7Z68ki(.;32Yb-nq0#.$M?A>T'0H,7#<M@,
-j%:&GPNSmbN'd`ich"LMf68nSC8:4BWpe)4.-2t>_q?&$QCi]Aueme\,JB%j5[dD=CkX%5Zn
mp=R7a7:9Y#?&V_L%Z3V#kO<HTB0H"\a@<-9_1Y=C$Q@P)C5tS4ro9K7T[Ke\g^El_r7VYiQ
79YS*`u=)&)r&>%/oRNWkk:KOo*q6Y<"/&J7u[kKmMuF?K_C8'0Q,naW;M^'W\QRlf469>mC
.1?X?MYrDtLioRB%Bi1t[JDQp2Zq#qki[aNqVSLS5f&3PhIYqL+F5fK*3@Z?R:'i6a8#X(Xn
RY9QCNapjA_@2sDYk8[A93[Jn]A*K"6'q:U>P]AK@e7d0<0<M^)3]A`4Uqe5hpF&Ta5p"Oq=5,7
?ek\dl=R<K.7<U/-`;#Y/f"^.[VQ^b2fIi\LZ0G_.Y[2^CO\k'nbIt*V$(5LX15L?kU$%\1@
CP.iK6=ii3_DdI,(u1[/CgRUXa\oi91&`qngO>#Fji/IB^'T!f^!.5OT#5BBqi)?8bh`X3+E
i#QcCF.sdd6[u29nif<V@tK[sGFR$Drs^etKj0*Ah`:UT"h$ZY^bF6LHCa\sJfbH/m`\44FA
G'nk_]A4s-u38B#8gH``;+D!T!HZ&]ArBV]A$KW(jo42ifR>4l5e94<qKr4VIKS2JNYsE]AR6\Pb
F*tDpMW3uH^U@N'X@E?qfaqQMMe%:oe(QP1E>(-oM$&]A)kOLWlcbll:OVTT_]A@[$_lsiS#k-
sm.CUQO\+FXRGGkAAWoQ$U`RJd6^sDDs3sIX89OCj+6^fe^U!gUPC4t;>)c1UG+k"3<k*V&_
ZpkXib<t[29%LUQ8e\i'G*K5Qj2@Gj<<p@c5&`/mC[d:\[df#2H=W!ZZo6`=5F]AY8bjiNs6o
Q!Tf?$*`pa4^WRJhZc^1Ngq>QGL7Hkg]ABWn+lP`d\9]AK4b#3?W[LGQsH9+Y6p$.GXJg:;)\9
$1_aq^olWI6Ct#e==>_anqE;54TdjW+]A61M<HGo<8>I;Jj\I+PZ`e*h?GRqs\pkeN:fj-9O"
5WEH2CC^ZpV;dUm-1755PX6F72]A$l4UCFeklD^C>ERoc^U5ar3NpM?Q3YKR&[iCJb#<)j5T(
fN;*m)I!"^m^#mmHS2(:p8$IHIYe]A;PNJ#$KKpQ;_]ARO5APT46Zl0_ZRB=DskT%4h<fW`3Vn
d=2XmfNq0"QlAu9YJE6!2HL+i4jH<=bA@_<e$31dOCG=&%uQ`cOUl*0<N0UW5@#r[--+6RAe
S*\RC(S&8FckadV0Lo6UNh,<)Kj<dO&Vj(e4\M3f"j?fRg>fZDqN<?589mV5[H(,-NLrX3o;
.TtF93lPnGQ^YE_AE2_2NggH^f-l>n*"0`kMW]A=]A*<&AqQilmXFQ.Ft4!p]A:L\d2TO]Api=NE
3'kGlG*peq=ZFP("M@iVCMp-$seb.:(M0:NGFscRR2QSmW/oMh7ek-nrY7Cb"[U''$*D*ora
/O@idPM[_Z3-P7k":l)54:]A#NSA>e+$G,.LtZ[Z<asF;r9-:6C,>b7!B0=0NF'I"r0egr;+`
Y+saSL-K\qEpYc#/fRCHpD94-VsS(dQ*oZ[W^[kC$^hI>o>K)^Y3lY-J:)))/*tNT2lNJSl^
25D.PXG^kiV=XN//MB4Ft5Fqup,\Yi)#r^gIh>Y)eZ\Ka.^^ir\WB<*?dWKk>5ilD&^k+!8d
Ene70snK#0P<.%Qj!+f,"i4O_"=3/kTP&RMUC6,leb:eM*GD/!8(*V*Wd]AjcV<B\3gmQ"7s(
:pqT^6RQ>9:rLn"9[W-FHr<_GX.udkb:Xb<#W"`K28>M_Ddis#gp&J=8dfn4AGf"6Was"7BB
>nGo<nD76]A5L'l6&n9JuUPh%R`-`:e50"ES_l,N>(Fp29U<Y4263a,N<j\OAmugSfMM5%Ghm
ia_FJ1=4SF6)=%N]AF_,Bafj014q>\G\b/m]Am@ZqD)e[i_Y=I'@1g@D&=?3?bBeYpW^PXDJY>
Ju5"fD4>>_Us^8a5hpf-Jpr*e4.1?<:"ihP3jh"S(bkS0::m.Hn6^=[D547i?!:Mi;X(OqAa
(lgbkCqV?-nqU]A@*42Pk%?HisCacUH"fWLC,j:4;l1_;&P+?18k(9:EYpQu;cBWu3'#+d-)D
Q:/;;.k&33l7k["Q)nFd9-83-B8<XeM?ra8sr2;S:r;ca?SUH[`JW(oJ<O]AeMh,^HV/1B>Jf
pXjpDP^K@ZVD/LS+uMd>F.rG0I+rW7\46GjImagQcP8&AiGr4knE9oVLYQq/#tkX9bV)mc)!
*@T+,>6n\BOdIo/O47eR@EmEk"'GN<&XD(4C.BHgO0%6[XbqReZ@Pb!igZ)fX/KM>7r5ErJf
pnBAr#0rF@ggUmMJf:WZCXM.JbQ\2;?17#AD7,$-dd[i0YA,&_G.6,P_CFIZB$b)0XA\@@K&
RC*V(lJ`0UT]AKLkL%96kS3Hh7l!IgotR\DNK#:;L!?$r)(M*?L=kr7#=BPNCZ0hSki.s"q6d
;R[IS@#Hj*;bs,bHhbE*-4,$:S@Hg:KWI6M4':mL?4#T;Onq/06a1:O+'t26RGMQ6-DED.:h
r4&[d"t(HR`85]AqP8+GAgpLTRjfDepDs\QrXUFj(5'/CiS;ZL';l.gB7KiWr_$dJ]AJTiSFnH
JL2^J[His/OGGrqU+]AGnpm.M]A7ES=f/P?nD.?g,CG?_@Gm#s&tAGZ[Q'dG6Pb3]A*Zkl6O?$V
?Y>3kdC?(:dAq\Y-u&2d`K(E[:q&(%nWs-[4.=>6Mqf.f/$*NGe5S$^cf;<u^Go;=(pmg"([
]A;5,i%<KaA]AY05u2h9=]AV)f(o?elXIH@iml3.pjSs*b.$d`$r=K]A1Ia*O3Z$B41>Bt%_!J.<
>@45TZ0ugj>H,,s#la-K'XrT@9cc9:i,W//@2uG"m5A.'\pBAj@0X7GhU,Wc:`E9Rrap;?K1
7=0O+>/$?h9l^#%oFLdJok6i&EphK]AU</KR5#j6E;!/F(H?WHP`t]A/G=gaA9k]AcFQGhEM:GN
"jV@MNEVB%T)p515`X`rX:0hLBdA=Z3o_L<i(t+<(N-_=j5K/UQTS#o>dYo5j$qc,\snqF&r
%KM^#Y$bL:=4SP00O_-lW2Ng*OS*+]At=O.i`1QGA0mO.L!U>%_*%R;3I+u"/5R-H-?rb6bbh
V4h<$lK>A7Lg9'S).Q[q".rIg?Q"PbL="j`,]A%]A;n+s?\:!]A"Y%,qRMDhrad&PoYZBlok+p^
sJM"V\%Rjecf5*ol9Ajb`uAYOug-)IFIbQKnd`k"j*lo8ZaGF+rD*m'FSpWqhqd9(nP6_^6p
_d"Yu)64$OmQf5J6T_<YbXH]A'"keW#8M0L$i%,X&afaZ82ns7rAfV*$Gu[:/$2q?4$:8pfS,
\ZuKQbeaH0h%Rl_O527[K:@CY@II>YeIb'4XDHqjCk%B5r4)>7TOm;MIXH^rD=+[>^F<?m-n
Bqt!\o)WNfa/ETP5OfAh,T`[Ahnta$R\gR1q>*HU.ad9`[R6,\Irb*Ad[Hi/A<k!]AR:e@HS^
]AOGFk795.dO)E>Q@Wufbc,\C?b7V-,iSLeA[$!reuX<%*($#[,k7G'71H+.@(F3ET`lp^j_J
/V4u=SFf]A+,dMJ[O/DHdaYUcnItt=[qD45S<^oZb#q7H2NWPh/Jq<]A7oa\)^W8Of)Nni\"dl
n$l%Midc`$4u6ao)aR+K@V#GZt\cY:^h.@t^k-WUPG&]A2^7AW*iL32(<[6W2ht\OS^XB'=t4
J:712GbM-)"YqAXF]A75Smab!lP%=%?R//pq8ok'*ehgYo#b9]ADg+bo*aKIdZASIb:l^>#V)b
>'V%LK%Cm0+96LM@2PQS"I.F!th>I<QN3p*!SeYQ^uWcWpsY*:gRMYi[D7'bM8ld(+9plGXO
ja8#lIR6'_!&U((.#u23369/=*lq.7*b$r^jSHIJA_&,%qj>B_r+<r6%DQ:PjXoYd]A]A-[`^n
6J,fZGKh.-.sKd"jI"CO$Y,M_XN=c+0%>a@:02D^C6\73Z4_S7FB:+4LN',N\p]A\ne109Me)
hb`'hiUTm7:E$k_)p#<`l7AM>oH7VI"!Trf4):I4D\9#ceJjO='*0Q3c&NF5W`+T-WZ!,-$[
6obASHCk*=9a7?h,V[Le1sG;a!9J1gBp+@Z`"fGc,"kj@C[5$t-I"mng`[)uG^;gD4OjRQjB
G>0ciH;T(*Y/R#8>m)=-%r*809Cqf:DL'0.ANbgMYLm1S+]AE[9hW@<rJ2i"Dc2fq/SkE]AF3q
gi,MP0SiGfT_OjEl+LHF3AC5Jj$F-@L&(2*i8h%8e-@$]A8=:u/EKKd;pjq]AW^c;<n4JLN\?`
kl^)<>Ai=\nDW?;R^)Nh$+sW,h+305c%Lemi-O)QW>I<[h1dlJ0-3.LVfY]A)rTa,+"3%k/!3
)Y5q4YF_uo;VWJDM(i6J=.$2&(?gcS@k3f/'/c7D,FrqWd,83F7Z*)?W[R!Mt<cF,jT<?VS`
S6d`'j1*K]A6]AhcmTI*)&$UYLG\8`H1g5W9V$`uH"Pa]A)O*gDL\K_@bus3*uA$dr+/($2oUoe
h5$8es&GFulh*WE9S+gPsYU%6f1a!J\3)$*4dIn%k+JRB9lgUH0W;Duf>KGZT%.?7pl9bpYj
Mk/S2$W^X^l\:`grRF-S$jqA/UZ:2l?C0E6KJ=4^2;]AOCG:V6@/@/%B3P*G[n_B\B&P9[dYh
qS:u6p+R3qo(\G&kDcLmiiCLIf\E8X]A(HTEkj7a2]A8[9[MILJX_b)!?QtX4(D81X_9c'm$f"
Je&8!XmKV,0E"'%JVri#*;)#aLXT?(@^9HfENcsJGGal7B`>qm67FB9+D=;o`/GT6h1?>s1@
@-!t!]AG6F!hT.J-d0h@1A+MuorN9[fI5NG2M),9h\1S"g5pOZCWD^MO[=jB^on?ol9E#bVpl
dM1p]AMkEY#7?L[.#-T[mY+rHQAIZqI4K><a]AO[WPF%="9sbGL=#>n6c8^A'`?9B3KZ'O_hSS
!d#52p<QUVd9Afds%+IL4(k[(/`V:(-We]AMH:%-3?qX?Yi7'RiKaP@&8,b2ObP.[bUiWe]AUT
Q]A:tk^6'W;@aoFa1>["=t-\hed,'HYG*?FT:[`Y2WkaH,Md)B9;2QU-dc8!3l5DBYa&(4at5
Z;7L$a4.N]A(/RO^WdEkafLgc>`U_3[_0;OZe5`ubmE1U`4HB>@-HbgH/40X]AE'B&+:&$KuPb
9itd<=VhWFL-63EO%.GJcH9P1SoDDT^QU?I#nU??mi.ZL4r_-'kDE"D>3&Ef!@#B>nf1OkiX
+%p`2Tt&besj]A7!2t5>7Je)Oo@[?dV'9g?'4k(bcOun5j#q$cM&pnWkb0_NRAXl?_gf>]AaDg
NNmp;Ani(Fb%L2mEZiAD,AU@o>r!r~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="294" y="93" width="354" height="343"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
</MobileWidgetList>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1536" absoluteResolutionScaleH="864"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="1.0"/>
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
<TemplateIdAttMark TemplateId="6e3fb290-d17e-4e5a-8208-8580821edc5b"/>
</TemplateIdAttMark>
</Form>
