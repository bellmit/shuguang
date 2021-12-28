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
<ReportFitAttr fitStateInPC="3" fitFont="false"/>
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
<HR F="0" T="1"/>
<FR/>
<HC/>
<FC/>
<UPFCR COLUMN="false" ROW="true"/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="4" s="0">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O>
<![CDATA[地区]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="0">
<O>
<![CDATA[销售员]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="0">
<O>
<![CDATA[产品]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="0">
<O>
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="2" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="8" r="15">
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
<WorkSheetAttr direction="0" indexsToCopy="2" start="2" oppoStart="0" end="2" oppoEnd="3" maxRowOrColumn="15"/>
</FormElementCase>
<StyleList>
<Style style_name="Head"/>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94j9P@p=C+@XI%13T<j+J*"G[V(;mX`@.1m9&+O+OebQV*L3(Xb\)*iL[+((1!qV`stt_7)
tSV:e6h?!KuB6Agp'n+Q3r(+lI"SN=\WIDVonfkoe76*mQ2rB6WDY3.-#/pN`CSn+V1#0?8M
`[qjNPlX!i)CtYi_hYk\(bEf[PQRtS[?h"3iT3mRlhd*ZAqVZ_8g$rK:Km&8k8`QK:c(+h-\
!L.n@JPV*h]A*-r4Ebh!rsmP^GF&0;Y2Pphr0neBBR4Cb]A%?g@rc3,"h7#2t?N/fMQD=+kh,l
>oqsBC1Yn+'/a\qE%/%!>,`9o.j08X'S:'7,^'-mZukYES%3D=%+@@)9TZ3t$O=)u:mq1dsh
`7fJuZA!%:?CS4G'!.Ar9u1Wuh;NSTbE95B8HYj3T7$bgjs[&1<Mdr)%V--@j+RF(NC7+p'=
0iY)ISH"=B^#o1$:`/ae;+3L2rA^V``C=D8kAu"4WhH9Yf[/@a.sk$*AJdMIj*.GDR)'1c$,
8hV]AZ7U__#Jo%HQXp..mn,)RJe\gISZmPjRU5*`cJT'WZG`)BC&eJ2^0!K-,Z+=1uk,ilU-h
D0%<o"(6Y_$*n+J&SV1Yk>1X^&jk&WV&@X3*V8658\a-i78iBO4P/R0T=tX3IC7[3,SQfH1Y
pl5kuE_74r<Vc;8\X4Udt&,ZE)0M.ZBI:Q8Z5`TFg#Sf0#DYgNV&VJ[]Al7X#_ed<(C[OXDBn
&Yh.ZZ)MUVZs2]AW'ZOjN$RXY&4/:V*]ARkgf;!`B$T_(QYi.`0/C@W>Bjc]ABHkX'-5$J3Kjf/
7T=d<2$s_<L.lD[aZsRD';02FQ%"1Q+t;V#?_MO:D/@NX<eFBXL$tFX,;gH]A>s@32=$UJdWi
"([eI7jpG]AXmJ!f^GgENlO*:n`WMm<eUK7"=1og&ad8aM$aoj2:,I%jXoZ]A!Z=F7/Y]A]Aqf(H
0+5mm<.>k%o!Wr]A5Si%Z>+<K+4AC#o&6_FjcSbO1mu4j`pkT':E!R22o"q1Vl&]A\7i$B]As5^
B9)(91?]AVBV&+VO`Z;ns+P)EgsD\NhDV0J(Z`I[EqNV\,[;lH,WG1V*JGV1TC1"q"mF(f,[b
ej5lZgfFi:]APukF0F)HoQs.NP*&uUXq)qn8Ueu!0$Y,CnHPDrh461/$#\'#um#Apl=s%9BOU
Y\chB\kc8Gf)f),6qK\(qtP:DF(UdD41;?m+'r_hQ*IHP(ldP9XonM9+a*ARiiE;Wl1K);:D
)0QUn)@1LV[CtK:AognP=9l\_s[Y3Hh&'Olso<"ARI(bq%)3I\m,%]AntDg\-$PTKkon#?=8e
&umR42.2_:f\?'ou+crkPVilfWASG=5Q]AJid/N"Xa"rY-HVlB2"Er4c4I:p;($S2CHkVB3C;
e#>*_8^`*S>dlP+%g\jbaiQ;p\g]Aj?^[I-9L^KL@%F8.q(l@68U=.QVYo?lgf=lPX=;?`%L!
1hE7Kj,s@ns.hL`]AO_XqfJ]AL&7&lE3b$+e+gjDLDG/tL+$I!c5Lh=FjdPbn_H>l;N%>OA'iW
&r\:1o>/3sB4fa;O0@FS1#7h=o9T*;WkXMMOW;`)*p]AE?L&n3Ja]A)e++^sBlr9cPJ6uM?FTi
Q7p6fk?FQa*,2;@@ks#=`c(XAXMY\0Ano!m^DKCS*dFp9=.V2.=<+A3LGlpW\[5(gHer$)'Q
Bf_>o!J@A3%qN#*jF2hlXNmgcm9YkQs2=Ps(\H96g-Y:RD%\3=,h"UO%TH]AX-XnrA>Y-VO#E
I>K6<c<NIf]AbjUAMqeiKiXWk7d>(6,'>![W+_EgKQ4B;iA+cj\dS/M%N84#h?V_8pp!-58&`
%(spJctV'Y:;/VE4JS"#YBU"1/V?_0bf#7*nc[U+AmY()Pg_+'[B[Kk&C@0Q>)p`]A:)7Rpl_
%<\0%dE.?Mk_LOm-tJAL+W@KY->;`)YmlZrS39qF?tBVHp;@SQfFcS\IcC+o>"gZpF;M%_:]A
#h/B7)It8Q-HKbWJLqJ,]APe/PFZ.G+KNaBO?W97u)FnkG1Yo#8]A;*om6,[k%4-k_8bfM`1<W
6=)GP.qWM!&"cm9H39daq]A>VKN8bR.HE/b&`eMh8k@J9l`DG*lK1L<Zshi3Pg"c.p?/0FHs\
K;\ki0Z@3(EIX9F1WI!K"3X\FZI[gU]A[X:coeTea*3deUpi#UKP'h*XmFW_YG/[i)4SAB+u#
'uI#q:a.]A73W8o^Y7p.a7]AA+Vs4*V:/r-@t0"q%,FfuBmFr';^><Z5XnJ`_.9\9;leFNm%qL
(;<mQQ.IakFrY3!n(JhFrfGlYU/[l4XIWYnNZO!$q^V[Y]A'ZM.P56W>N\P^>A\I)!]A;nO&[Y
H$S1mi*FBHJ'"sg*@1AUeaX:[sl9&r_BQpU7L3fbmk3`!i)*[-SnaGsmVJsiWcAU-2O5WMK6
#ihl#NaV\Tq]AUCnN\(=*bdcedq3Sh%_[c?21@ULm6+#*bOZ3:(GZ>ajTqh6U8YkVL[2UhTbe
_fgd5q?C>q<q-r![ArOtXI*g!;XQ"PTW0VrF^eZ5muS]Aq29PWRPBHTa?"5rqO"PkJ?t5F8+M
-l$n=)s+kB/lHIrPXJCo['C:JajTPA(sL?&(C#Cf(I[>/U:k>r&o`+n(#]APd>(YcE:A@,(4+
O&@EPhq\::UD4&AVa$gY!pBWDlYLKdJEM"<d(:`$2m_U,SI=W_@0D<s7i3A`g'MPG$4`"8Vf
t[sWF6"YHrm7T$+-f.uHp?B[P9P'LQ!<)4oZStSjhL`-dT9[%NASld-`lp2&giQ`U9;Gs]At&
`$l;]AB(6gSrRG!/Y`fe?`'kU</"I1S2ZB1;$#P3%=5Hg6$Ic_8d@VZ^(d(S&S&[>#S'n)_$g
gtPSCO;h]ASOOBIf=fYTcfn<N'[CbtVS*k`X0a)[Y'_d6\XCBS!?&<]A`M\6#Og_Wr4-PB[n[M
c&J_jr1F1n4cdae2t^R7)#8=R.B`$#gfU0\Ta.KYMj</mmk:bbQI?:5N"dt5C%Md,^+]A0GQB
hEVq?5jFJ5QbC_uOK>F)8.as+nU;fC.BLg6=rfP-&pJqU.a+]A_NgmRoC580#I<LM1."cS*A,
&[Q?O`&(@n=Ld3-!`OC5U[+K;D#Cp/2As:2k=Y^7RP'_3,/k*Jpc&SB;O@;'N['N,.djKcB.
974eb:'Ej!:hSl/:ue&^X6BE)!R?a<T>krIJu-%j4S8jVI`jRfpT`A4VGdHWnX4"/(LC0ofq
u'D(F#HOhe)1%+0ftrZ`$NhZ@hd&5[eJd%Ml@<\pGG$V,=XEM62USeb2j;4t"TWgaXTgY#sL
)m!+!$>9B5/2DV>P^%rb^23Z<8Yc6o_ICI2ZtWu%2%g;fNV;t*_1&=.h*_ed`KsYu+SLc;NW
=]A*W@\5,89"X2r0ol5C@`0>(Ta<EM!"WgaC^0-'AQlNZ9^T<OspW`Xseo/g4L.[$I@jB3,17
jRf,W_+^t!/3)E7L>.BS[>^p]A21oZU/>p1&(-5`+U.l%N4YjLu=FUfFjU/)W>f?e[9a:n3fo
qK=Pbhn+P[EYPdUt!.n+^Yqjhjp=g2*3XcJF?'M-C14%F/\3\_mR+?_+_tp:[D'$L?k"V>p2
VAD<8.36^WpJV@>OUKA#2;ITTebTQ#iqFZMoh<FQ$N#>NVV#FdV+-ipF,YcVVKK(E%'$'@"P
EhcTEkDP;L"QsKh`-.;]A9?]A_4<+>>9jTl`?<n]AsMC1S7,^l]A3#,,b3(0m9ReX6#gPoVFF6#:
4%:5es'P6gfXrX<!C2h6r`B\`s8^frH$?/GMP!mX3FSSW"`e$o'_t43,@1U`Pt2c_Gqo7@:F
30\K.ONTSY9N1cp[lnYfIShupfm49""lj#rD%^ElEV&Q7/n,\^_)VC]AbW%P<9SKg`R3/0e)f
7VlZniOl"3[CZa<m:!0Rk6'5/%JWTcF<Nt1I3$eB+]AbBgp;SlXM^S?krcXPs$u[f_HOtg\Gj
93Zge&"#r7s&<#"'_:J1CDNCg&qa"KjQZ_V@mgN)tnP-#+;UXQ8>>,ZLXi39]A>^KT[Jk=Nn,
f/\0let?Iud\^VsV_FOn70i5;+RP^,jn(3%7d<@J%TDbR>Fo/j,/GQ$O.<(uOh"c\P&tA`4c
\p>0u&UR&5Ul6g"Qgi>?K/5!^s@D-OA9jC^aHKCneI!2'e`PHL':p%o4lIgQpX;b-L]Al8udu
/oe`megc7AHMD\IcAL:,gnP_i7,2+(H@LidC:QBFL87)@"=Mk_e[[g`'Oi#,j;V;mFp8rp'B
%aF+N^Vf]A#$4Kkg#_L&p=%_STS\dm'5pXA]Aql8-k/pe**Vt`*K!PKM-GaYC&Vl:o<-?;bmF,
UV]A@D*+^HQ?d6Za'+C@!KrN9bk!nAFu!.f%gbkTP5'Uj`1WZY;Pp^Xh[C1U%6Fb>1PpaZMS*
UUZOrrEbYL<M+JUDHTp`A&u6a,mJ.qG)`u2"l&+*D`ar6VK69j6m,gho180`P$7M"b?9$g-'
'"*Ve!SJVqX/AJG:3TaHq*l_-NY>)C7VYj[5<i_E\4IF0%0ulZ-8O`%Gu<>d!j:p34qP5F!F
RWe[q>P?'e\iR3M7R-\sQZU%-'H%TDlSp,IA1,dq\!lMH\f6=Rf]A^>R3mR821#Ee5rFtLYL?
/<-B,<a5H1YW-*oqdLN/-"qs7rc8VITPi*959>paK'7?TRq,$0##R>Q_dWQ,KN618uIX,b!9
<u=.f>T/*$H)j[Ca]A$*EtVQR9sc'Ydt^4jeC36]A\g==u=/LrWogFV4f^fj3LX85?D_&PGTLm
ZAdkQjjp27)^,B1Db3YH8_rsSr@t2_b@-=^NsBJ/fV`&?VcY`.6$a2^bEN![E?.hUiYia9?T
;jM%>%$EpBk!pD0'EKrR^fD1!tY+Y7YWX=W@Jos6T97k5Oln@b&mR<fMms?oTI\r$GYk__U7
FrOCoD*aFQl;bo<>0?KHDmKWf$Njp+EL4FuW?3o"9f",+QJD3rA&E"AD=SW$%^H_kE,tY<AO
^R6pXQMk0@USnB,=f8*4mnsn(ls;uX!MP,HL=]A&FMR0N8S9oN1F0@U(^Y.D%$:P$6Zs>"+(,
+<#)UR'h#3Ak]Ah;OO9#&WL,phD:LLG>&6@d=:#i>&QO)<277jJ9iW@;7(@WC-n/Y<,P[H0gf
)u)kR")^UA/<n6/goYN\_E*']AEHgH[@@$Du`%00Wpr>/(c[pl-ao`<W]A=F)3/t/Y[pf8DXh.
Y*EC=agpf("X&M?16MdH]AdM+[iIT*3Qcdk%.t>r#0u5*(E%h4Y1l7@?Kq)%@;*(ho(\H_Jb#
mO(=75%(%=W_0IR.87^anaYldA>e<E[qeUkJh+QV=PRi*XjEJWT4<Od=Um[WN'43r,nk*+aE
pN""@gYH@.YXqpi5SF<5NQM4C!*^OiBAL[q+(*1Q2*Ki:$Kc^N[37>*;oFj^Gcse;?%`-Y$=
AZ5:3s/<EIE5K`fr3(NM=cRnO%=F9$DK6slPP38B0uG`#i:]A=-Ut0B=#9Y0^-s:_&E\/XEM,
)>s+3Wd)G;),"8pnA!H15YDnn1%R8=&hgXlK+"LTY`MPFB?+k=S-jUt_qc5s(7Ju)+UM5?&o
lL0$/VNLF'Tpq1(<Of9Y@)1c"=jOgaFB4YU._o!WJ"L%\3qG.<%HI#nqQCnh50^[GmgRrVq]A
I*@[=$BNRCXml9Y1>tiI4<*^:+**)SA!KO]A[!"[>qRpLL`Y*GQ.JTgatRAO41/bu3-'aLf/L
HVs$WW''3eK7(0FST0Ibes1kAahq5I.c@6C!<A=H5W=>3@(fbqCgBun2'\Z,5aD*7=0$]A*Z"
ZlmV6Ta61[*Q9Am%l74B5E9!S61]A=I@SY\YXFQ[L15\(!crrsPU3$+IS:$>bK43eg@p5D/l4
gP[H^ork711+:-oYN(&6(k0!T[gBNrEYbgpUHK#AUTW;KoqT5`k0:l)Ode;fMq6,^je?$!6d
8<44QlU>pqG[iE9aK0X"*`)E&1dB*P4ijn3Gp+qdTrH2'YC(pTr99*7nP6r/9b_m-s7)8^S#
-r;(%Eds^T'CE$ugXb\3o8qV1L0&u[kflq.$m"&d`r*HmWDK&N5#R#BQ47(=#*5BKh]A\#8X,
rS'UOm<WAOqd)N;u@;d[nL]A#8-A=1!`oFNfD"cA<=an*Yj^G;9,XsP6q_oQM6*nt@22&TWkO
B(C^06bXe%7XS;$A1%.S,kB=naTri;KnEHMdCh`'m9S6P?lK-eQki^Wl#ZE[m+IlnMCVnu1_
Wg>X<Os$IUQ9sk5!O1Bc$'YZ2X5ZN1M_,p_C+HQR\InP#H4qM`n_-GVb2nO^dS+J%buk<L&m
U??JpX(=#O3C9f(k]A&.Ar*(K2$3G[/==TIm/qnO7Cha)I^Ur?fNGm=0JnD(%OA4P,*aB!K7T
(hWf8"^,VSE>&n/dch%ZtZK-b`/?uqO0:A*C^Y$4Q@gq%&@7No*IQS*^4o"!Ti9EJOU#:D&$
CGBD+i3WYpT@]AW'2uo1LTOV`%!3*-(u6+Z>&2F3@-<72*ZA%>M@"V_gZfLFL$(JcoAkF,^ds
J`^s@]AbAW3sP=/#U'7\^E`WsM?W_%:F)X^&_4Xq`+-H%%@b5lN=!B5rnbMfGFY*$>K$StR-)
MIW6mfm?gT#K&"FQl(-,D#8<k#<KDmMjb,3:O>Q@p4DGpQG%6I\>o3D#-=n[CXf'ILZ]A2`:T
!:<!,[aNe[/Yp"'!ss@G1hZoP=H)eE9c'3RV^_RI%,5d]A.i#Xmj2X_^W.J-C4JYE#.3:c[?,
/kZL9gM&124n+L$<A]A&t4i+'_BapF-bXn\5fD?,!ch"focgF#J=Q9db(m>Up:c!/-(rs\~
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
<HR F="0" T="1"/>
<FR/>
<HC/>
<FC/>
<UPFCR COLUMN="false" ROW="true"/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="4" s="0">
<O>
<![CDATA[客户]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O>
<![CDATA[公司名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="0">
<O>
<![CDATA[联系人姓名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="0">
<O>
<![CDATA[联系人职务]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="0">
<O>
<![CDATA[电话]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="公司名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="联系人姓名"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="联系人职务"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="电话"/>
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
<WorkSheetAttr direction="0" indexsToCopy="2" start="2" oppoStart="0" end="2" oppoEnd="3" maxRowOrColumn="33"/>
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
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94j9P@p=C+@XI%13T<j+J*"G[V(;mX`@.1m9&+O+OebQV*L3(Xb\)*iL[+((1!qV`stt_7)
tSV:e6h?!KuB6Agp'n+Q3r(+lI"SN=\WIDVonfkoe76*mQ2rB6WDY3.-#/pN`CSn+V1#0?8M
`[qjNPlX!i)CtYi_hYk\(bEf[PQRtS[?h"3iT3mRlhd*ZAqVZ_8g$rK:Km&8k8`QK:c(+h-\
!L.n@JPV*h]A*-r4Ebh!rsmP^GF&0;Y2Pphr0neBBR4Cb]A%?g@rc3,"h7#2t?N/fMQD=+kh,l
>oqsBC1Yn+'/a\qE%/%!>,`9o.j08X'S:'7,^'-mZukYES%3D=%+@@)9TZ3t$O=)u:mq1dsh
`7fJuZA!%:?CS4G'!.Ar9u1Wuh;NSTbE95B8HYj3T7$bgjs[&1<Mdr)%V--@j+RF(NC7+p'=
0iY)ISH"=B^#o1$:`/ae;+3L2rA^V``C=D8kAu"4WhH9Yf[/@a.sk$*AJdMIj*.GDR)'1c$,
8hV]AZ7U__#Jo%HQXp..mn,)RJe\gISZmPjRU5*`cJT'WZG`)BC&eJ2^0!K-,Z+=1uk,ilU-h
D0%<o"(6Y_$*n+J&SV1Yk>1X^&jk&WV&@X3*V8658\a-i78iBO4P/R0T=tX3IC7[3,SQfH1Y
pl5kuE_74r<Vc;8\X4Udt&,ZE)0M.ZBI:Q8Z5`TFg#Sf0#DYgNV&VJ[]Al7X#_ed<(C[OXDBn
&Yh.ZZ)MUVZs2]AW'ZOjN$RXY&4/:V*]ARkgf;!`B$T_(QYi.`0/C@W>Bjc]ABHkX'-5$J3Kjf/
7T=d<2$s_<L.lD[aZsRD';02FQ%"1Q+t;V#?_MO:D/@NX<eFBXL$tFX,;gH]A>s@32=$UJdWi
"([eI7jpG]AXmJ!f^GgENlO*:n`WMm<eUK7"=1og&ad8aM$aoj2:,I%jXoZ]A!Z=F7/Y]A]Aqf(H
0+5mm<.>k%o!Wr]A5Si%Z>+<K+4AC#o&6_FjcSbO1mu4j`pkT':E!R22o"q1Vl&]A\7i$B]As5^
B9)(91?]AVBV&+VO`Z;ns+P)EgsD\NhDV0J(Z`I[EqNV\,[;lH,WG1V*JGV1TC1"q"mF(f,[b
ej5lZgfFi:]APukF0F)HoQs.NP*&uUXq)qn8Ueu!0$Y,CnHPDrh461/$#\'#um#Apl=s%9BOU
Y\chB\kc8Gf)f),6qK\(qtP:DF(UdD41;?m+'r_hQ*IHP(ldP9XonM9+a*ARiiE;Wl1K);:D
)0QUn)@1LV[CtK:AognP=9l\_s[Y3Hh&'Olso<"ARI(bq%)3I\m,%]AntDg\-$PTKkon#?=8e
&umR42.2_:f\?'ou+crkPVilfWASG=5Q]AJid/N"Xa"rY-HVlB2"Er4c4I:p;($S2CHkVB3C;
e#>*_8^`*S>dlP+%g\jbaiQ;p\g]Aj?^[I-9L^KL@%F8.q(l@68U=.QVYo?lgf=lPX=;?`%L!
1hE7Kj,s@ns.hL`]AO_XqfJ]AL&7&lE3b$+e+gjDLDG/tL+$I!c5Lh=FjdPbn_H>l;N%>OA'iW
&r\:1o>/3sB4fa;O0@FS1#7h=o9T*;WkXMMOW;`)*p]AE?L&n3Ja]A)e++^sBlr9cPJ6uM?FTi
Q7p6fk?FQa*,2;@@ks#=`c(XAXMY\0Ano!m^DKCS*dFp9=.V2.=<+A3LGlpW\[5(gHer$)'Q
Bf_>o!J@A3%qN#*jF2hlXNmgcm9YkQs2=Ps(\H96g-Y:RD%\3=,h"UO%TH]AX-XnrA>Y-VO#E
I>K6<c<NIf]AbjUAMqeiKiXWk7d>(6,'>![W+_EgKQ4B;iA+cj\dS/M%N84#h?V_8pp!-58&`
%(spJctV'Y:;/VE4JS"#YBU"1/V?_0bf#7*nc[U+AmY()Pg_+'[B[Kk&C@0Q>)p`]A:)7Rpl_
%<\0%dE.?Mk_LOm-tJAL+W@KY->;`)YmlZrS39qF?tBVHp;@SQfFcS\IcC+o>"gZpF;M%_:]A
#h/B7)It8Q-HKbWJLqJ,]APe/PFZ.G+KNaBO?W97u)FnkG1Yo#8]A;*om6,[k%4-k_8bfM`1<W
6=)GP.qWM!&"cm9H39daq]A>VKN8bR.HE/b&`eMh8k@J9l`DG*lK1L<Zshi3Pg"c.p?/0FHs\
K;\ki0Z@3(EIX9F1WI!K"3X\FZI[gU]A[X:coeTea*3deUpi#UKP'h*XmFW_YG/[i)4SAB+u#
'uI#q:a.]A73W8o^Y7p.a7]AA+Vs4*V:/r-@t0"q%,FfuBmFr';^><Z5XnJ`_.9\9;leFNm%qL
(;<mQQ.IakFrY3!n(JhFrfGlYU/[l4XIWYnNZO!$q^V[Y]A'ZM.P56W>N\P^>A\I)!]A;nO&[Y
H$S1mi*FBHJ'"sg*@1AUeaX:[sl9&r_BQpU7L3fbmk3`!i)*[-SnaGsmVJsiWcAU-2O5WMK6
#ihl#NaV\Tq]AUCnN\(=*bdcedq3Sh%_[c?21@ULm6+#*bOZ3:(GZ>ajTqh6U8YkVL[2UhTbe
_fgd5q?C>q<q-r![ArOtXI*g!;XQ"PTW0VrF^eZ5muS]Aq29PWRPBHTa?"5rqO"PkJ?t5F8+M
-l$n=)s+kB/lHIrPXJCo['C:JajTPA(sL?&(C#Cf(I[>/U:k>r&o`+n(#]APd>(YcE:A@,(4+
O&@EPhq\::UD4&AVa$gY!pBWDlYLKdJEM"<d(:`$2m_U,SI=W_@0D<s7i3A`g'MPG$4`"8Vf
t[sWF6"YHrm7T$+-f.uHp?B[P9P'LQ!<)4oZStSjhL`-dT9[%NASld-`lp2&giQ`U9;Gs]At&
`$l;]AB(6gSrRG!/Y`fe?`'kU</"I1S2ZB1;$#P3%=5Hg6$Ic_8d@VZ^(d(S&S&[>#S'n)_$g
gtPSCO;h]ASOOBIf=fYTcfn<N'[CbtVS*k`X0a)[Y'_d6\XCBS!?&<]A`M\6#Og_Wr4-PB[n[M
c&J_jr1F1n4cdae2t^R7)#8=R.B`$#gfU0\Ta.KYMj</mmk:bbQI?:5N"dt5C%Md,^+]A0GQB
hEVq?5jFJ5QbC_uOK>F)8.as+nU;fC.BLg6=rfP-&pJqU.a+]A_NgmRoC580#I<LM1."cS*A,
&[Q?O`&(@n=Ld3-!`OC5U[+K;D#Cp/2As:2k=Y^7RP'_3,/k*Jpc&SB;O@;'N['N,.djKcB.
974eb:'Ej!:hSl/:ue&^X6BE)!R?a<T>krIJu-%j4S8jVI`jRfpT`A4VGdHWnX4"/(LC0ofq
u'D(F#HOhe)1%+0ftrZ`$NhZ@hd&5[eJd%Ml@<\pGG$V,=XEM62USeb2j;4t"TWgaXTgY#sL
)m!+!$>9B5/2DV>P^%rb^23Z<8Yc6o_ICI2ZtWu%2%g;fNV;t*_1&=.h*_ed`KsYu+SLc;NW
=]A*W@\5,89"X2r0ol5C@`0>(Ta<EM!"WgaC^0-'AQlNZ9^T<OspW`Xseo/g4L.[$I@jB3,17
jRf,W_+^t!/3)E7L>.BS[>^p]A21oZU/>p1&(-5`+U.l%N4YjLu=FUfFjU/)W>f?e[9a:n3fo
qK=Pbhn+P[EYPdUt!.n+^Yqjhjp=g2*3XcJF?'M-C14%F/\3\_mR+?_+_tp:[D'$L?k"V>p2
VAD<8.36^WpJV@>OUKA#2;ITTebTQ#iqFZMoh<FQ$N#>NVV#FdV+-ipF,YcVVKK(E%'$'@"P
EhcTEkDP;L"QsKh`-.;]A9?]A_4<+>>9jTl`?<n]AsMC1S7,^l]A3#,,b3(0m9ReX6#gPoVFF6#:
4%:5es'P6gfXrX<!C2h6r`B\`s8^frH$?/GMP!mX3FSSW"`e$o'_t43,@1U`Pt2c_Gqo7@:F
30\K.ONTSY9N1cp[lnYfIShupfm49""lj#rD%^ElEV&Q7/n,\^_)VC]AbW%P<9SKg`R3/0e)f
7VlZniOl"3[CZa<m:!0Rk6'5/%JWTcF<Nt1I3$eB+]AbBgp;SlXM^S?krcXPs$u[f_HOtg\Gj
93Zge&"#r7s&<#"'_:J1CDNCg&qa"KjQZ_V@mgN)tnP-#+;UXQ8>>,ZLXi39]A>^KT[Jk=Nn,
f/\0let?Iud\^VsV_FOn70i5;+RP^,jn(3%7d<@J%TDbR>Fo/j,/GQ$O.<(uOh"c\P&tA`4c
\p>0u&UR&5Ul6g"Qgi>?K/5!^s@D-OA9jC^aHKCneI!2'e`PHL':p%o4lIgQpX;b-L]Al8udu
/oe`megc7AHMD\IcAL:,gnP_i7,2+(H@LidC:QBFL87)@"=Mk_e[[g`'Oi#,j;V;mFp8rp'B
%aF+N^Vf]A#$4Kkg#_L&p=%_STS\dm'5pXA]Aql8-k/pe**Vt`*K!PKM-GaYC&Vl:o<-?;bmF,
UV]A@D*+^HQ?d6Za'+C@!KrN9bk!nAFu!.f%gbkTP5'Uj`1WZY;Pp^Xh[C1U%6Fb>1PpaZMS*
UUZOrrEbYL<M+JUDHTp`A&u6a,mJ.qG)`u2"l&+*D`ar6VK69j6m,gho180`P$7M"b?9$g-'
'"*Ve!SJVqX/AJG:3TaHq*l_-NY>)C7VYj[5<i_E\4IF0%0ulZ-8O`%Gu<>d!j:p34qP5F!F
RWe[q>P?'e\iR3M7R-\sQZU%-'H%TDlSp,IA1,dq\!lMH\f6=Rf]A^>R3mR821#Ee5rFtLYL?
/<-B,<a5H1YW-*oqdLN/-"qs7rc8VITPi*959>paK'7?TRq,$0##R>Q_dWQ,KN618uIX,b!9
<u=.f>T/*$H)j[Ca]A$*EtVQR9sc'Ydt^4jeC36]A\g==u=/LrWogFV4f^fj3LX85?D_&PGTLm
ZAdkQjjp27)^,B1Db3YH8_rsSr@t2_b@-=^NsBJ/fV`&?VcY`.6$a2^bEN![E?.hUiYia9?T
;jM%>%$EpBk!pD0'EKrR^fD1!tY+Y7YWX=W@Jos6T97k5Oln@b&mR<fMms?oTI\r$GYk__U7
FrOCoD*aFQl;bo<>0?KHDmKWf$Njp+EL4FuW?3o"9f",+QJD3rA&E"AD=SW$%^H_kE,tY<AO
^R6pXQMk0@USnB,=f8*4mnsn(ls;uX!MP,HL=]A&FMR0N8S9oN1F0@U(^Y.D%$:P$6Zs>"+(,
+<#)UR'h#3Ak]Ah;OO9#&WL,phD:LLG>&6@d=:#i>&QO)<277jJ9iW@;7(@WC-n/Y<,P[H0gf
)u)kR")^UA/<n6/goYN\_E*']AEHgH[@@$Du`%00Wpr>/(c[pl-ao`<W]A=F)3/t/Y[pf8DXh.
Y*EC=agpf("X&M?16MdH]AdM+[iIT*3Qcdk%.t>r#0u5*(E%h4Y1l7@?Kq)%@;*(ho(\H_Jb#
mO(=75%(%=W_0IR.87^anaYldA>e<E[qeUkJh+QV=PRi*XjEJWT4<Od=Um[WN'43r,nk*+aE
pN""@gYH@.YXqpi5SF<5NQM4C!*^OiBAL[q+(*1Q2*Ki:$Kc^N[37>*;oFj^Gcse;?%`-Y$=
AZ5:3s/<EIE5K`fr3(NM=cRnO%=F9$DK6slPP38B0uG`#i:]A=-Ut0B=#9Y0^-s:_&E\/XEM,
)>s+3Wd)G;),"8pnA!H15YDnn1%R8=&hgXlK+"LTY`MPFB?+k=S-jUt_qc5s(7Ju)+UM5?&o
lL0$/VNLF'Tpq1(<Of9Y@)1c"=jOgaFB4YU._o!WJ"L%\3qG.<%HI#nqQCnh50^[GmgRrVq]A
I*@[=$BNRCXml9Y1>tiI4<*^:+**)SA!KO]A[!"[>qRpLL`Y*GQ.JTgatRAO41/bu3-'aLf/L
HVs$WW''3eK7(0FST0Ibes1kAahq5I.c@6C!<A=H5W=>3@(fbqCgBun2'\Z,5aD*7=0$]A*Z"
ZlmV6Ta61[*Q9Am%l74B5E9!S61]A=I@SY\YXFQ[L15\(!crrsPU3$+IS:$>bK43eg@p5D/l4
gP[H^ork711+:-oYN(&6(k0!T[gBNrEYbgpUHK#AUTW;KoqT5`k0:l)Ode;fMq6,^je?$!6d
8<44QlU>pqG[iE9aK0X"*`)E&1dB*P4ijn3Gp+qdTrH2'YC(pTr99*7nP6r/9b_m-s7)8^S#
-r;(%Eds^T'CE$ugXb\3o8qV1L0&u[kflq.$m"&d`r*HmWDK&N5#R#BQ47(=#*5BKh]A\#8X,
rS'UOm<WAOqd)N;u@;d[nL]A#8-A=1!`oFNfD"cA<=an*Yj^G;9,XsP6q_oQM6*nt@22&TWkO
B(C^06bXe%7XS;$A1%.S,kB=naTri;KnEHMdCh`'m9S6P?lK-eQki^Wl#ZE[m+IlnMCVnu1_
Wg>X<Os$IUQ9sk5!O1Bc$'YZ2X5ZN1M_,p_C+HQR\InP#H4qM`n_-GVb2nO^dS+J%buk<L&m
U??JpX(=#O3C9f(k]A&.Ar*(K2$3G[/==TIm/qnO7Cha)I^Ur?fNGm=0JnD(%OA4P,*aB!K7T
(hWf8"^,VSE>&n/dch%ZtZK-b`/?uqO0:A*C^Y$4Q@gq%&@7No*IQS*^4o"!Ti9EJOU#:D&$
CGBD+i3WYpT@]AW'2uo1LTOV`%!3*-(u6+Z>&2F3@-<72*ZA%>M@"V_gZfLFL$(JcoAkF,^ds
J`^s@]AbAW3sP=/#U'7\^E`WsM?W_%:F)X^&_4Xq`+-H%%@b5lN=!B5rnbMfGFY*$>K$StR-)
MIW6mfm?gT#K&"FQl(-,D#8<k#<KDmMjb,3:O>Q@p4DGpQG%6I\>o3D#-=n[CXf'ILZ]A2`:T
!:<!,[aNe[/Yp"'!ss@G1hZoP=H)eE9c'3RV^_RI%,5d]A.i#Xmj2X_^W.J-C4JYE#.3:c[?,
/kZL9gM&124n+L$<A]A&t4i+'_BapF-bXn\5fD?,!ch"focgF#J=Q9db(m>Up:c!/-(rs\~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="0.9"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="99fb215b-b86e-4165-bd16-e655fb2a0eca"/>
</TemplateIdAttMark>
</Form>
