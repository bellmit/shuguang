<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
</FormMobileAttr>
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<WidgetName name="form"/>
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
<ShowBookmarks showBookmarks="false"/>
<Center class="com.fr.form.ui.container.WFitLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<JSImport>
<![CDATA[scripts/js/dialog.js]]></JSImport>
<Content>
<![CDATA[null]]></Content>
</JavaScript>
</Listener>
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
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<JSImport>
<![CDATA[scripts/js/dialog.js]]></JSImport>
<Content>
<![CDATA[null]]></Content>
</JavaScript>
</Listener>
<WidgetName name="body"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<Refresh class="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr" pluginID="com.fr.plugin.reportRefresh.v10">
<Refresh state="0" interval="0.0" refreshArea="" customClass="false"/>
</Refresh>
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
<![CDATA[1440000,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[8229600,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="请点击 "+"<a href=" + "# " + " onclick=mm('/demo/NewbieGuide/行式报表.cpt&op=view')>此处</a>"+" 打开行式报表对话框"]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr showAsHTML="true"/>
<CellPageAttr/>
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
<![CDATA[m94j9;ci[ij%+;*E-r()'ZoX5UNUmXUF6g"X05qK`:TsMWOu]A9P[`^U#4hCPE4W!D74X\U*F
GI^+BEb$^a.I;U59L?8q7b1`/#[(#^PV40%O`REgU8rp7GM(J),0dp:4i)n(ZaVkC3'kqFH\
)/GtKsS!upt?f0_uju*>/k4Yj;=mj?EOXbdGRp3sfW41Z/0=Q>=pPfVR>8t$Lht==\Y8tYOe
XqNWJ$d8fGg!NG5"p+Sm]A.F6Z73,/gVk_8[6KFKS%Rp1f1V@`0\c0Snah-UIA+dnM[b7iX<J
CM+"Ht:d7D;V/ZT_b&M_Yt)Mum!dr&^4MP)XVGEKFIjfj+&_C&hn"DP85>%RIA(EsWjFF.7u
p%1b<"#3/7Us*kfUi+SBeF-G=,*^mq$1<SAT[YjBV%91U_dW/C]AHX09moJ8Grm#sr//E@nj\
N)FrPuL5:TQ\skcTh7nLe5&LPU1pI7FD-Z-(,Y?j=9J>V=</F@;rRCJ61Kbp]ASq?$[`,8(l"
F(k2t^WfDA'IhU8603bnqeT&^U+iRp?!I_C@HDi$&c#U5081D0EXudJO2-^)l>TO`<Xt1WLn
e)nUei@%i+-IUHZsZU,8^*s2*H+8n/66ueiMa,Xn^RB#Fl]Aehq%8bRXG4B8IU77qiI3\aB&s
-hjF1NUjEJM%3N\opX!Gg8;I8j4af![3Bbtb4>`mtBi9/buqE5S8LT0C32q21bTL%RsZA=YN
)Ui\:rHsN!Zi/b=MCA!kB^ba,lVLW!fZ*Q-HgW8a4tA[P]AV`rFHFT2`RS\/p6pB'R3HqX<g;
$@KLV-mfMD`T+1P>>$]A5hGNV(O6pYOl_TL-IE(O$lB^\Z8/mN;*)pOa4:b/Dg`LH-"B\)Gu*
V_0RVP:,S27o.@UbG<^HgOp2tV4m$1uq!1M:`<.8NQ,pNU2?/=/OAOYG>1`VjkC7aq7`[i#.
YHuH-K[=!YTl]A'eS_Zq(%<7d;3m9a>b#;M`-TP.%GO^TLGD5=\UL:6B7`a8NPk,M-t/>#Fc1
QDJ$?&A]ATPj1<tDoA(-WD$(WQ\(C>D1`!p8/U,!;&)EU2_i+KHL*JLiBV1QOGCo!6]ABk&Nsd
2GlpTrfS0Td1m;1q6Y#MmAae-V`6">_J=YgE/:`]ANlC=l?fTo4jBPIGi6HijGhi@@bc#3oh(
g\7cu*@8LnnZ**#BclN405X3(u/!FrDk4At"J!-B_FrRP[16qW9T3V"ZLW;N;Mi4t33G&[+>
PU(uS6['icS/\nY3T1**-\n@]Ape/H#&aP,1G%nJ%b6U=>k8/O3M+3KGSSAABeDeIcLQ%5-TK
$Z'grNb]Ae\*gZi/N&3[$b.7@![Z':NFB<Y`qqM/T=>r>`5Ygr<a]A%g^Ji+oedB\A`_RN'/J>
QrL5T]APa^l/eCE]AK8)Auh[#Xm]Aq^Em(;n.Ah;pL+UVJ7eL55`eP_h(<gBjUg:R^>'1Z./[OI
,1KKFPp4#cmUIbmNKeDuXRD2Ap;C]A1:4thG(aslac[(?sX@LRD3dT`H,j)&A:W\=BBD<gCFt
\K'FnTF;D8IWd^S?1X2Xc[5oZE,._?D/]A7(koEXD!;uk-5[#BR*VHrUhP1M7e!El)pjN)HYT
Uk4$"uY"c^-e)'QF=hI7`NkV_R)/sBh7?"qA0U)>g4AN-8HQ_@I1!uH?&RcSK$49&)e;F)Zi
WWS7/mnW-i92e4E40&nn7Y8\qN:sR^*JnBkLMpL]Am6*G0B9(H?n554K4VkJ92dZ[]A8^PO'"&
EagN3:ZGUJNuhZnh=OKIlLW.V=]AFAe;[(ld'([@:51'Y2@3]A]Ap\g_O`p'ffiJ]AoLnBZ5rVP\
phUF.HWM!MdWC;(6"lh&JqMea\(Rnl1>/XBR_C00Zri+r^/P1ho1>^5]A-V[cFnaFfM\eJDi6
UG,')S(nnb@c4Xar64pS>jHZ\7TL]A*0K+p*lGuE+se3dE:Rd=4t_dOe*6+=[GF]A&c=\$Co'N
#X,Ck*)^>J1';"s41%JIjLFIkpE.gt7l>cIB3#"EFR;tVpI>tBAN6irh?F>8reg@6gUKO]At*
.&)d3*IU-$@*60#DWj,1aUfZMptcOb@[21+EhN'R<Q@_RU*;1h6nMb`>GjdenHB/0QGc'*7^
,&ap6qo1Ml/4d4XrZl\K>UQG<$\AP6io"t@1AG1*^ZpE<PYhNnD%/h^8>P,TclVN78[Y\+Hq
L=so3"d_OU^kBHjc1n3he\4T4<d4YSZY[s=YX:Xr]ApDIom.mF&01Q<fS/khb&`$G#I3<@V82
+_@0L'CWlMN;OUCE5p=P+7/AJ'BT\H[uLV<oY;%9?M$[pU+`>58ojPIo=bD$u)4gKQ&TlW?'
7ncLH/+5PeN,"^W"Pjg6OK4uE@2)6@,k5"C3(>2_^?pZ$PRieu%,/P:Z@5CE,LKJLQF5[si/
mbokpQ`H#NQ@K<Vn'oW3n=]Amc2<O&*30<\@gg9uNA?n9%sREa*E(L!@JLqBq24*9flku7>a[
u8AK;b9+L^-0gr<drS8C"X_VpS_]Ai`jafKM#BFVr+_4j45A)XK%RbLgUen4\11KtO_omLbEA
afVdZ1KQJCd\k+N(7!>NQH?5#/$+%kP)fEIN28HG&u]AaVcd),7Xe[aT6b@3Dq5[rM[)C645[
:t`<?Q#$+>r!9L6_)34/"c^B>k^AOt![1Porl\HT!YbYHW`3j*',r;C#:u/F0<Hj=kr(HCZL
:8Pf,mR\d&4RQAP0*B$iKK.:JHM%m)/7Y7(eaKXi[H613$"Hl;Mg6M3:P!5l>.>Q0@naicXQ
l7,D.7uRnA$t<42MG>(bI#Bu7WjdLJn;6EYooge0u>(`llD;cPO[L[C1D=X^!XE<3%fqS5K%
%'6>]A/%LM&sN;'RIp&`RXjBh_Shl:m#s:s-W@+gGLN0UkPCl`bG-_CHWG'`F*Ym5o>8c>LUI
(!r=/$+]A*D&DjA4oWq^3=-Z0e#KT$nlfk:N>=9\JCnf6:(5W@aH,ZX#n2,&M]Ak,Mg7lUVdEf
3):o-uo4qt`(Z7$)+9m#na5Y\l\'NsQ.QcB8JhY:GVLb-D0nLh-331XWG&VLB_E]A+n7;buaV
#(HX2IPdO._+8MV8`/Jm`<TVZL)eE/M5I>Y%K9$$O=P4n?<08mEM!gM7(FX,\O#)_0.,c9d,
7^nSX2D5-D&Nh+a0mo<i^K?HUo)oI<hB!CrlEt%3eK51mFmA8eq6eVkXnmd<AOE;JM-cX8L]A
qdcluZYcp4Wa5#g;b52G"D;K^'Y`*'G6$,$7!X>>+bAcZdo5A?-B>*``b3m"H6g:o9J^.$a6
dbm.1J!q/1L?$t]A,rQh!_`to[Bte,Ad6T2&O#pO,lA_ZXs3Clpf=@]A>,>4%'l+oS4;49@jE)
!7gAf[lor&%s-%l@o[oqjpc.KT;=,P-p;'JMO&1+)rWjF%?d2G9YW[Epr&!bkC=BMTt(g3K4
f[St0*L0EhP'Hu]A)9_4dl+\7E77Ga(mMH#f>i_km\qKZ83k-Wi1N9<Lc;"J8F$.cO%=Y39m0
!&7.]A'\p5Vq';)eP!>[[^eIaY%$u]A(2reb[QVmEj$!=R9-foD^2X&<SFco"V!;tM5.2GPnoY
RZmrYGgjQ=miNiP1QD,6gXr81ZC42%754kFC.H9l.6cU8a,DP_3SlG>I-hGC,[`M-u>?ZS\k
V%;uV!LO:de(Ql1Mb)VhkOf:99PSDu!#ShWdb-6jkqS]A!iSZ>^S[eaTj8L$N<]Ar3I)Rk-lcK
0N`PTnXBo@Ep%YQ/MCOn+n`oFP.SLsU%7KP_j(J_X!dA@Rs_&2WjAZd4Bg\oUGiJ+)a/ek5N
1Y)fj/i<rn\X#&3eAm@uT60Kg^G*9R!3JAU$Nb@S6i=hWM>rWtYCX!40)*o7h&ZJR3c"6(Ye
0g;G/oFl*jlLrMn+?BJJ:#GmUPi]An9E$ROAfaZUL)1'69EGI."cttRfug)3aq)*s$7h03K.m
m8N%8>/S>MC8Y+YT94/=VAR*i[A_:?qbJ7p)s%"O9\M;C\#+dI=/gm/@h?Y/c2VtD\'?QAXs
*&<AA44Qu65\ghOE'0VM$ll=u'u(:'42ng^bh?PdEXK>:T;sNTfSoFl`8V"q%4)gATq05THT
%BQ$uH-E'D#IQe;S1=k=8=@HHVCH/.S''?LoWeruu_WNCPI+![t.:kmZ4-rAfT0duD^HOK&H
i.$0\mnjcK.cF$/dBsKNP_s1UUnirj[P&n]Am`pKGl-p_O75:R;URe7[-=FO3_L$+$!gWV'+e
JKm"2^[a(2H9iI^$=]AKrc8L<J$jA2cTfZC+_9K&4>]A@Qm_pDcRY/k^b<,1k)5N[G,$+Pt]A,&
:eE)N&L%+%;5U[CPkW*[>"3p4CpAm?U^nI$j=ShA\n=1g%HC1O$MM>;b%Kp3QTLef/ZYAH=.
^Lk\+c?c=dDS[o?+#)]A$-O'6mX%PSoJ.B`3Lq@3qZZC8SM!8u&$euGecCcAF6Ge<+BoekKg5
:.)\g2j<pcp+e50_b=1NUt8jBJ<S-.Qq:<6YhulD]A\oUU,DafRPl#rCO?2AC.CXOjZ0FX4h?
=btR4NO;kUWHmQ!]A?ln3-g]A/%G;@Y>2@g3q]AdXt!j./!@d3ZP`jpUEe=6.![3Rll/$1'.&hQ
-)s`q+m1$rJR<1Wic-Qr0O"ML$<Jc:llPm%Y(49#IWfHS_o!;7s$RphO#):0h&N#+T$'!,u9
YiOIAZ_EJ;_L++XrAY<R>d0qIah'1g);p(CV,CV.@XH04jR#=[0XmTL4fgFkIjHr@,Jk5Nj;
!]A7"f,ufn`^/^C`UKn&/?_8P[[?(8a43+2Tmt[5!^m#.7HS&H)3rK6cBWI=(,$GRo=9seOd0
&t&):[PmkV_K9FSDcu.hCc1rm\.Q:"pPA4t%,i$#Lc5Yk)p$qB>L1n;Rg!g+>S<95!DY"`??
LBc1Rt0otZ=+N4k+8Gnr\.8f<kK$@Z8o3Z_IMT+*-`HHf]A&RedD&?TGnBe]AVDaCBcj+QhRmN
WOR<:H[boQ-0-;H>NZ(:-OMti!au?kd\9JUJTaY8&HXgl4S3p8+dG9:L=15&n0BRqmV^]AlC>
$fG.'WZ)bT^B2at73H#Q>Lj*OV*Z=DSK26)rC]AYsZWekY+MgW.Lb[9`mM+g>kjT.m=l@Hj5I
,mK7@=H(mM.0dfkDQX$a[Qceh"8YF\oOYt-/f]A%qIT%>kUB.X/\YB.eKH6^iGsWXb>2<\*G`
c/ELCmCGR2[d=funq:5?[MGSS9>N2).pp"f"_Nre_8p?^L*fUcoN@m6]Aqb:gne.4'XKuOTW8
h239O%IEZUp6'I:m@>9X&(D(6?5,K0U1F.r3\KqafjZ(A)K]AGN9^tO#HQP@lX+STgG>e6g^H
=-HmLAQ#m$oh?k\H[4E(iYU1>@3,CVmZZF-k^Kf:h+jf$^en.)`*"N\P0>0G@##2`W>o<+RZ
a6`WQLi5r)?4Ma,'!e5L`[_d\c@Ri<k-JR,C!6t)rf"CaM>O\qJ7rQle_(Si#t9WCLWTq!I]A
=N+2ub?A1rWKZ*#Pn36<!k[Ao]A53AM`?_rg7kP4?6?fRZNq:IJDo(4qKCW-24M\@0L>jq=D2
EYGlco"H4GT3o2'u*F&d)cWYS\8on/;EZo:EOGZ+8JD2u\4iW-c,p=dO;Hnma=s(T4tlM0mZ
JFSVYikoi1FL]AH8rCoA4QNef(YMq!*"$FY2Z<F]A;F]AU/#(Zfa^aK!CO\^g\`.dr6$!j[2..I
F58*+i%'4YbYfe3pOa98&Oi9J/=4nj7Q>a[S^DAH0OiJ<#b>e1)USL*nT7meVm%!c=<)-![(
DW7.$b8JJqjNA.3;?7Im+m<BZ>.dhG<Eo-^\UGfGHXM6l`oM7;<&^SlA-^Kpa![f-4:~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
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
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="<a href="+"www.baidu.com onclick=mm()" + " >452,45.12</a>"]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr showAsHTML="true"/>
<CellPageAttr/>
<Expand/>
</C>
<C c="0" r="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="<a href="+"# "+" onclick=mm('http://27.223.11.42:8999/webroot/decision/view/form?viewlet=tanglang%252Fhuibao_C%252Fchild%252Fone_jingyinghuibao_weiqianyue.frm&ref_t=design&ref_c=d61ddae9-9aaa-4212-817b-75b5e26aad36')"+  " style='color:black;font-weight:bold'>452,45.12</a>"]]></Attributes>
</O>
<PrivilegeControl/>
<CellGUIAttr showAsHTML="true"/>
<CellPageAttr/>
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
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="404" height="242"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetScalingAttr compState="1"/>
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
<TemplateIdAttMark TemplateId="b5ab43cf-a73a-4460-964b-42407fd1feb9"/>
</TemplateIdAttMark>
</Form>
