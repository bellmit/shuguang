<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
</FormMobileAttr>
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<WidgetName name="form"/>
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
<Center class="com.fr.form.ui.container.WFitLayout">
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
<InnerWidget class="com.fr.form.ui.container.WAbsoluteBodyLayout">
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
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var a=_g().getWidgetByName('a').getValue();
this.options.form.getWidgetByName("report0").gotoPage(1,"{a:'"+a+"'}",true);]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[查询]]></Text>
</InnerWidget>
<BoundsAttr x="187" y="2" width="92" height="36"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0"/>
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
<InnerWidget class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
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
<![CDATA[952500,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="4" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=split($a,',')]]></Attributes>
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
<FRFont name="SimSun" style="0" size="88"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-6697729"/>
<Bottom style="1" color="-6697729"/>
<Left style="1" color="-6697729"/>
<Right style="1" color="-6697729"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94jA;g4P*1S:YQ[?UXVCo*jPZZS=3$:`/\;G=AH[Br=n[uQ)',Us.9^aPn0=*!'kXE?UTN-
-&1P)lmlj!Z$<GmMN/[=AT-P44Q7U7_gT7tb-1E0GY;?DtAfr;%:7S^1rX%Y+!,rZ:fXIcm6
i3T"m1T7%7hm.umc9.hhLY4'."cA+#)5J^T0?I8`M7<7HA_l)>0AHVf.49,<7PbPF6V>PReC
>QmN.(1Ka_;gk(*<)r'MaL!_md9o@8AYjteXQR0=3%f$D>GBLlDXRIYPrglIsgU'r6ClC-he
Zt):(PGn@)^!WUIR'ma=-MHeHpFMp%)/%`^f$e)Hb..pj*2j/L[k]A7sG<E(LPqCtZuC'[X=I
93E14Z9:s;"gM"o29W9OpE+fcKlD4'LJ(T.m?bbPQP_SdfM>;kd:$B7`A7%@*(#-".$-]A$[K
f_6qQ]AA'RE+""1;n#C9($R.ggNA&M]A<Ag09GL]Ar#.Nkgg7Qic+*'bbJ5,U5n7Xf1PLjT.YPF
=Y`X@I>dE8>I;(5e=X9e_7V)@>3Q&/*NDb<U=eSIUg*3^!PV,CN`\L<dq*jYC&6KIP/DCQu^
/X0IGpIaOab2l9-ZjJNEYYraDbF/rQ)Qm'fHK=iR01VXWigrS71hW"AQSdBdoCj'^ZIqs(Rk
63KRNeqiGM2H>Wf&:8@r!'RXUUpn;><e$]AY2NghVVQjL=1-.t.L==$a51G,">NbB^k%SqtHf
@;*X:2;suUM-'^F*ZnR(b<:V,LkMq$ELro<+Kb"Hp&bU(GA%5-faeCM4>#m1*QQ:)M2e,T8/
9"UraAgknbYmn_GS&l;cW)+SP)++UN8)&r*m$4-*/1!@l's185Gt5\E*B]A^m.fD,_pHu;S6o
GS",37Gq$EJ:G[_1M>a;p`JDdl>oao'mTNAsDh*KU)'keNb@0QA/D2%1$B.G3ACF>uWbY[,D
a?\:PpSQT0Y*5n/phWgINs5W9;6B"Gg!h$;EEtaq9-"Z)iNqFMAmeEnD4FaAUdc7H;WpX(.H
0+p`T[L"5D.DFi7i)r+aW<FKYRGhh?MEMWT]A'gg7lD4shZ(f"MgMA#J!4N\s^p,435+eA(.:
^%"dja@sPXgp2<:gJ\>A>Fjm)Pa@<HlP8]A"UepL.FK*TlrStFI-_."0=!J-##ui#m@`3DV\E
PkXlRp]A3>hV-G,Yt/(jC[MOPPRo%,Wlj`_NZ9MG>&[A[X:G@(.3a^D_Q'V!eqFEXZpT%6[AY
5F'*rSR"T&JSSR#^]A(;0m7DHNJ:1,QKk-dnSa'g]ANd\bLMd@#UA'Zk'[gt.gW.2Es()X1A8D
A`>Mj\0-e2Q?39mDDdIo3cPmh/OZ7=tuU(Y/o-J>'+?^6A'%B;1>!O8ejr1gu"gQH"eiCT6]A
urAf,[\)b##>U\NV!9oh/^QqF:g7pBjB[?J06VFb_@[IFZsOIiFSU;Lo1_*j44p6BWl,0J+H
nn$uQ>AQF.&EAn&iS"X=:ddH1s%;IA7.S#0UbIh"Z9B(;.BkE^;$\b+Rf$N$T83'i;S2WIl_
L:29ZrM2[br&@c^0Nm:5NCc!YNDk7h*n:N/+9.W:lD^U:5SVN+9Tf2RRoi?_c^RiQbJm0%t\
)7p=l]AH$p!XF1>;F2"U>C7ub6P.i@JfF'rhU?ZNfdK>m[JlZOdqPk_ZCF.jY_!U1iOkl@g+7
%ZMYD"f<GOK("RTsTA7_MgmOD!$$ISe!pN,DFp3UM)8gn^_Y<>8kgi`MU^_[fLkH`RnjVTka
qF1,O")NP9u;k\F<^4<DR5>ERoQm:P]AnL&=JFB-2+-Fh$NgEX0h28E/a$ZF_2id2)LOiAtNq
f=Q_Rit.\nfJQ[..Dn?D=m_&o8O3&jP:jG2:"XW&ElHKs6uIN5T5dO09'p_i@lG)?I@bt^;#
/3U'g1,[AskEol\q`9d(i`e+Wk1f:H8KOV]AR)M/7Lh)Sg3m$RH:?+$@dhN:rueD\<q3PhOdf
dN6X4iJOC;s#e,4kQWaRf61gJ.c[b@*7&HlqX/q_<fkc+NicQ/]AJrsbLCNm"C5T&!9P\DP+l
N8Vk\Pl\S8/\OlT($hqoa#K83Xq5-./*8bq=%:^\X'\+7nfnJBeE%p[gd&'.YK6bI3tXAD/o
rT=AHh\2+oMOppg5:\`gnBB_!dQ=f%kFco<62WbEhB@tM`J8rTsBn.+OF>aQiTlOfKRN=aCf
Au40M-S&XIc1fMk_DKdDGDJh8(f"af&/`13*f]Aj&dtH;u)rtpF_0c:7cQm&4kqSsMWYIq1V\
@3<<<fn="ui/sV\,iD)JnXkFBq>.+h<kLZF,fb+S1X2$q!_o]AWdOr,o$6Y,fbEp<+g,N8\*3
pbt.!8P%_drr4[-gTEl+IfNg/0LkB@_<?@LXfF0'eX&<qq5p.k$7G,:."7-&d>4+hZ(_[a5)
e#Bp.]AkfQH/3>LGdB9>Q;Tg;&_f%CiY'^$[*XeDPu$7YVnlL*E?/X-Dq!BZ[o+'V[X(%;2;m
0\mKF<`Z*XaejgM\3V:cJj"8D*\`_q5@q+`$gJrBq]A((23HE8(SlGO890\Y&,HNd^?ZOAZF\
M(^keG3U<@-BKr:-k:obV5>Y/C>OntTjm+?T\c,`gn5RS'(XE(O`\<#3(]AT#>/:K+:Uq)U'm
PEP/();C%;:PuG6gffoL\d8ih<!D=DM3EbG1iA3Dp,5Aa**H\?7agTPt!V09TXY?N#f[&^!G
aAslY<d`;\kMi;KC<=o>S]Ak5i?R\Q8)=8/,cG(hq/#>.A:'CqL^C%J*S8Eeg;o'4tKBjGV#b
%^WQpViMFEBVn:hitVBj9"-o]AuKq#'tMF0FKK1S(9DB?\_nmFQjhl0kYl56QB+6JF>L[s7RR
kufXAPF-]A3)hntDBBB[sDNM)"""Nu7GmZR<m*^KVsNI^1=OooN';%,<qmkPrD(?Fq2-[ORBS
N`gX<Kb@je_tkrKp2<`OfN]AICF6JhCq(4Z$KSIa20&)B^(Fmb\]AmQR+Bmlj;AoPdo]A^T"*3:
.qhY?JMFZtusK=C[>b/eE_)"-Y_&NdHD=J6&*!8,k\iq945/5rqWOIWhL8X2rL??2(Oq2sE.
1Fld&V6D;9D-3)4n0M7MYpD0>k&r]A''(W\m_eR(@/i5Htq-Z8q'&C8)We+=Pm]A@a]A)&np`#$
]Al.YKt<U7e"b%.a3*[pF74NR\8Zj_V1`fXKCTt(!2U]A"@]ADiK'S'KI^@ZHNe%6qP7%8Oo)G4
+r$Y?Cp3W#7a^VP3b6Clp$erSTUCIPa2T?&&VQtSefeN\pZ.rq1mg*K-b:CY0aJdr(@QQ&B'
/5m]A8d[\\nX.eVI[fA$7W4S10?Q*L/J*6I8:+r[gj;m;U=q[C"\QDQW3\8CcYi+ZoRNe/mIF
_FnGn6@X^lLQ_:-T_.AEQ,)UWEdCW77/d"3Pr6$IC'F,+\[9hJtlGr1,j[@GD.;(F@:Z3iPj
`$(M0U2Y("hk3!@OIjO*6T&UaJBI?)hH==>pJ,n)YdCNIUa_"Wa<RfYZFZ@Jakm/FH3LN/>M
_ZD/jp;.$(RKtE)0N3]Agt8?UOttll#5#GRABigVr(%M%/7c.=![-fCNip*kL3qq#$ShaVJlF
9`;F.u#Ldu5n;ptE6$I;mgeMd*X#m6PK!97^OojoL:oHD-`GO@L@i"W\Wo>A]A:@bmaIJ<B@[
4^VPQQFZOTPiLk4N_m2F_((hR40C`qUEbt7=tb7d-uaN7,@WPtQ.Z4#I]Asb:pfS2l@cTM01Z
Zfrm(t[GVRfQRJmX8[\gjr$R3)3e,lc=^`l.n\B%g%LBcR0%=H&L=?d;`d]ARl.6]Ae(VqF;64
4Pp7E;pJafi=M*7G[/+>87[5uNGi>/;Z2E:09;&W9Q*f)khHB`g-L7j8_]A,+&Z;TV7/UaLY2
W9l'P4`3um%+WI$*pO4?_ahe&N3AM"sC&9=doJg=`93F]Ao?d5Hg2LM+dUsHpU:r)=bT[a^&b
WE[]AZ"*:Q/sU(a-p7QK/$$daR$$jtdJh\n.kj"FLZe;#N.r&4LC8jJH"r:iEG)l'/LR+<f%0
c@D;%?R3Q/9*l11WapD8^=uZjn:Iro7_&+0$t>*Z@P'7DSRK:><>[@mKXr7RRX1FtGF0p/or
.ouG9I#.d.1kCE@T\HaGb.J(FiNuRKC>Xfc)6\/^NV/>S\`@`kV0e_,)?*m:33BJ:-0+$tW:
H#lDhTq\q9tRGL7ri']A&+4$Z&h28V:s;@qq5NE/7=M[[DNoRj)TS#u[nOn1MDana#>)5OP=R
i:FZB3`.]AN/sdVJDJVj,!8fY.g&rM<O/hAo,$$(R9aWt!3E?;__RdlUMe,BVt@M^fCsaDbp"
gB:cTZ$&r\0N!k<aN&<eM\`(-:cp]AY:5%#,<tQXMW\3"?o'l)L`;+OIKAdm77c):I>W#Zt>o
0Oq@/IV)>9!4'q+an4[T.b8e]A9L9Za`%k`<iBjXbC,^eIoGlP`L/&bE=r(7$Im6,h_'GPAiO
K5K4KC<1&pPbe]A>@u&".;[`iSg.lVhX7TVSJqqJCrRgjFd<lMpAf,;X8!!18'23cX4TYL)TL
Ea4Bl?LDp.(!#pCk@Yu"F'4_dZGiR'tj.\bZm.A/j3P,lW&;8;IP\P(9Zi.BO16W0AoWf(?n
@>Wda-q$gALoKPrM49n!'j&;N4LOWh*^m1Mp3\-/C$DL$rin*NtO23NJ3WW@HRQH<&Y_`e3'
8UPZG_ehQM\35]AqfR<Gm'CH/oniOLQ5;"(`^"R=HsOaX'ViH7"!lnZ@/n_%>Q5)KGfpBcNaR
*#YDcBc!UudZ=-5G(XqC59/TCbN)Qq8gR48kL\siWW%AJm.rg%^,P!:bL11-;.flg0OcWDo<
D;8b5,/!$1o[X4WS[?.<<4h]Anuu`qU&Blob0,E`W#eZc&,2VAp0qsDfj,+Uu[dH"`5rG.bnP
M=Vb\kB\bt)9F;fA,`0TNB..An14@.:;@U>(CfQZrB<;>S+p3R%]ANb%i^u.hb_;gT+o7h@'3
1=oO<>lE]A5F;<XJS`G7#=+T\]AbpgfV`O[qm(]Apu@s)D1Vi:Iq$/sM<O2N`/:/Eqs`ihphLX&
p;d(h!:M\d-U"a"#-)m9ksCn9SB+0aJEE[PQ3^rS<+n\>hT)ZG@u*T/Vm9APNPPj&;G<k&Dg
)*32EX[dC#RpL)Mi_Tm\@Q^G!JB()7rKRGSqWH2\O89lef]A5Bu]AY?>rSan@ZIfDV~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
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
<![CDATA[952500,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" cs="4" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=split($a,',')]]></Attributes>
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
<FRFont name="SimSun" style="0" size="88"/>
<Background name="ColorBackground" color="-1"/>
<Border>
<Top style="1" color="-6697729"/>
<Bottom style="1" color="-6697729"/>
<Left style="1" color="-6697729"/>
<Right style="1" color="-6697729"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94jA;qI>4Fa@c&L?[=kX-(W>:17*I=qlQTEa,PK>;3ab74FBD<U'[LM"4;MCGdkLdMA*,)<
Fl:&p[#mQir?k7?ISEOb=8g83dI>RFb#NDM!22Gg:6]AC%Z]A5<rQX%^%^7H>O,Ls,^m&MGN%j
\?FR2E=h*QHmr,P;R>8#OH"l3e5?Rn0\^?g]AA$c0gQM9:m*h09p;8-*XKX?c335t>hZNc!Mr
.l'VlLHb"B)hP-c*lNrH,$*DjgWF9]Ai.&=3o,?l*:VL9VNG3ZIleU\l2?*;EjF%^P.n?M-T!
d4R@_g7DqicmaWieb-\j>q"JSDk#op2QY6@%rglSetgkr_7i@HuC_@`iD`'F^pd4Po5VR[&T
e8&n[1DLV(-E3s>Mt-<cUOpd`g2C(`ai+$3h62VYC&Dj#f<.bu.J'A'WA(jZ3@5gao[]AaUa?
oglU7p>-O"6,;/r4dW%t,unfb?oiN!\)7oFQa"Zp*a*"6l76(PFr"QtdnC>Q4YAH_I;FZN*q
4MmCih+_^'aaVZGEULVnQ,TU!6qFf-%,t*33).k8%'H$"g5!$+`T-Y*CXm*C1D`T)ubMG.FI
hYDlpb<j`'`\A<A%^#H"^Y0PUM[K<b9GV/pY[:I2iQ73@7l>"4gkLNU5e0@4cm8B-`<HS.jT
ElORXC@rOh>GMJG&X.=ZE=6)X<:F<L$08rH306#9>hSe4K]AT";af-aEpS\inkn=:tPoUpGE`
i/BEl:c(p;4l(uah3FH3$so4`]APhgDY+#VkQ#[[q8%9J7+(S>\r0F!p66+N)Fpu%LG6+>d)B
P,XlT\J`:<#\>963``N;+K"=!5qt7-J`"ABWPP:,s?J3VP./Nn@Y$qQWKWeVGE,0mII.Nn_p
.F[!KF1jIbh.cYFH]A8J4e'/Q,hcX7DaCU5fX_MLR(.k$GJ)u`>=C9k]AAinho:r((6kA$h7I/
:I8WdBk+<BrZ7F'V@L'f@K`_]AmAN.JmrgtG),ViJb9lX7=KAC)tGP#.hf7tr*\2[JO+Srhos
_>d1ib0Cfe=PDD+PLdF=C\aW-85c)f*e:\)isYXf&p*@S^9[>,E.4`q,nL!!\\:nmZ\:OUR8
a/7F6h$]AimdW_"@/B?PI'SIBXU'"$'Y;Rm%DKr#^&&1KtcAR'<4NEK\gR(RVn,S)0*Np2Q`J
l;NHMYVo'/-e<ZmfLQGFXqt6D8)jM.K]A$7WEJ)*ptjbF6ZnrVK,5Sl`M34bMNYB/s!X?8b\h
*CE(`hVTU/K'[O-[S)J@pGN&&3s4R"$Z@R`$3^e%l&"Vf90?&+$@k*CHr#Qd,b^H4_5#Tq"C
_0konap#JEWiVUpQ/,;I!`I%FNXU7B/tg.(NC6hd]Ap&r`?V:>3.PJ#D0";^Lkl;ZDSQ"cgS8
L<o`\[NMkiuPTT?%X%PS/U.S[I`#npO<_+be0,&uW`B>OAkH80L//bL3:'VJ9m#\Z'd906/$
lj629kXOEn=`,^$lSQ8T'^7pOIR(,pBBskbmWR^#AA7l?>CC#5Y(0\\QB1iZ&ar9-:^M&<U)
k'_J9"XqGJ`Q$Xg(2=U,9$qV1NAZ5.ZZ!+%P^bW0No1:u3(!8<Y-t,D+`d8VfidUJ>SD$?V$
WL#$PCci9OMZEI`CF4LI;6[GuVdU6Up0gk7l(E%79o;ND7jaU+ms#WOdB?*+=WZ4u</F"o$C
XV.c/9Ms]A([&a!0n9ZRkeZuN%f*E<hR5hV73mj8N9@A'/pA$mV3C"s@*)R6b,LCi;OV;Vd#e
CJ,Yg`/?'>]Ak/hML9-ejH7"dr;;\:cB\1._O"7VZ[o.HUOjqGj\D%`%d[AWeWdo+>_qZ%WDk
W/`VB$1'M:i1_^6\Ja4;mSFhnkhYAJPjtC\A]Adf,5ag>[<?ci<mBXrWN12"Q)*h/;iYp)9M0
1rpKQro'"B2e!QT1k:!eC2(7:/BFCVF:Ta#1%ToiIqOF8ZF!Tju;Mg&g!el*"US*Le\PkCdE
\2p4._*_U<'d<J!HR7:0)*]A0k3X!V'S9g'R'JcpLWP\J^;cA9;.<I"9!(FhTi>@qX5l-Db2C
['&n<5rAlO&q"91Z-fiB]A'3eeUn0lBjBI*)f0%pNN+8,/hEg<FH=D#!Eq@oW[7f")kfuhkGZ
:>ZuXsD1`#fp82WDTj2";t;sn<46-U\DkkHu"PM9N$^LM:uSZ'+SdFmIiE3GQ2%NQ@,[?fS0
UOh_\@dr$)IV5)RW$7Zc`K;_:aP1Nj/?P-bT>k5i\O`,&OF+7\>9>0Q`@@82+f=]A0,71q36j
\!tNA>Fr<Dg,:5W<%&/s@@%)-2)c8\'Jokq&8h78oj(l5Xd*O]A?!BTaNqCabp84;>M3]A1>[8
*Q_=(4Ti0tX0M@4Rq-UFHF'Q)&=44fAbr2G7*i2)_c0";P8k7d)CG$p.Y\i2=F.ALLG`0(er
$*rRO<T^d'4[#?(:/ton8UU+E#J^*Jr3Hp_H0aVXM(djH/G);MdT7P#*iQ?]Anh6FdCq__Oel
\`d7/t0PoUG_ajg\[jL?u:Qf5Q%\2LZj.'MV1o2)"1M1/nZW3?`1TQDbs->Ef"ho=SJb3nNN
H0TkM[W7sB75*c%.n0gf#OcfS1gii8TBCV,Qpju*`_OHdm1jckl=f*A<4p$k-.@)`6G&OS]Af
Q[Z8]AEiF=LG#YaGENNi_1`ap2m:EWRX!=<(L)!!S_j>?fP'[^(VCTBCh*,>,Oh\R(T+*e1i3
l'DG>ZgeiY^T`6gP8B%GUdtg]A8KL5m23c[`ZQ7-;a;L?G\."V0p]AS#SOD8]AfH_!*LNI0bk6Y
Q$kMC8P-g'\+tNi/)BgZ*0u67^JVZ\gF411?IXFQ5?bDX$cL<'COYo`<5?5YYnJ1*i4"#9m0
T&#O^QqkJBl;-KYA%o>W8&ll@fH[k+_/g!`E%9,7>%n&M*0kZT&ZSN,b*3s#hH_(d#:Jpt[G
6j!9X"O:]A=EB3c_'XX)U`@sJ+?F]AFAUPS$8$7#<3(HT:$hS54:?=RedD=2iK$Oc>LRe?ubdg
KAmAT.7*kFOF/CClqZg?i`FT0+EEh8U:S"PXUB(`@LCf6RGAD-S'(]A#NN:@(1=M(-RMCRBm%
Lq'9%2SLL`?qa[t;Xl(S]A+/<W_EH'Dqhdrc:2reLrI*Z:/1M=[Kci*Gi4I=uSEA6]Ai(md6lc
,?N'(3s_C5un$)d^Ds:pSOMB'\d(mr)@]APTOiRcaaI2*3pPRl,JR7IV,L%NY]A@aG0t<rOkf*
(;4nd3#W>0PQg@"1+$61M=P/]A$_CX2NpHrbGW5=3kab`?9^luNV_n-/eR":Ft*H]A7'^N;K;&
lQ>&/P[h:d&Ak7I+h`%-%1GP$q%S;h"Df]AHA>kRd#,**;MnT>ip$WF-!XH;QoJ#g+l2%,T#e
qrtr@)[@:1WEG#U`5U+0.A08\lp+4q4mRO7e`e9!7"S!MJcN.K8k9MpNN'P4.$6V1CmiRIlW
9THJ]A?"9gM:P6h%<VaF^Xr!P'X;c]A)IXLm#>WjX*sHL3te,NDq_"[e(@Jl&o*HQ=(j=idW^:
;,#`QVMBNJ>9uMG+[AQ'Tf>M!P^m9.ENc4TIe4&L./7O"sJQFVY\o5pb23/OdR97^kc:JiYe
)Qr1)`([/U@=Q*I,jQSDbu:okOdF?KoI[G-k*(TipI60rV]A,6B=%m2becmm/YGg"_LXOfLWp
538ef3^C>N@9^MCH$Vi&N,k/XEY#)@bd_.m\o32PZapP@&^43iInSObI/jJ6IR3E+!cnmrCA
U\@KLAu6Z>8:-&V'8S(!k>fSh6^,h(,3.0,h@W]A*HN3iRP`&43e1&q^"&q*G_mLUda<olN,F
i(39s7*D?D5q\JSCa^i*T^g&n`SnfP[8EFC(>Z$Rbf;j\$Z:1jmUe+ph-kqen.Z3nqU<p$?Z
`q<=?=H4Kb.MWcoW11=&+Tn;:D+hl:Z)Dqmg/Joo!9-Z:f8Y5G@mU_V'b:Bk=0ru,*BHc0%u
CkXFMOip2j#S[O;5j@ih@p]A,tZ!$+%Ze-_ARSjP*98P;(SmXb$lP3X@P&]A--=opGV4)TnIUF
i9?7l*obLKS<YRh^/KW%/S[<7?35.Z380`MM]Ab%]A1"S`$a=>*B:`^_in=e,['FA"U7H<G.08
^\c:0GPMCk,9*JqrVlmXgJ&++![Dg/s:BR(DA_@6`1=Su%t)NhJK?dI'=cBQidh!&r_M.$HG
M?o$jf?_e(Hj2:&J_f(\+(qQr\JK;aU$UQKq9Kh>mFDQGM,)s%P'!0#Vs7O9GR%]AAMrf$8sH
%`B/Yj'%G*h8UN.J+uWBKTgEjaSCgNIM;>97.c>OQTo/=ckOi7iImgL,$I#@9#*s@97BBY9k
jaelioXiWa(5LIi^o,tpoFCj<iUR=D7XW[K<"R<)Jr!RiE"Kglce/51T)2NUP&.Yd75R?l4;
@+Mb)8jBP$SSo;WI=qK$cAQ;_,NE^K,Z+k/`X$m#(7aM6R_]AC+Hg[k%=`?goE^X(?'df]Ai0i
J*)ppGQg.C-*c%/V.uf[CIbG?;AoIa,XXqBDRr$E,[?7`Wnq=?pT5`Q01?K3DJ=h]A7#q??+E
t+YNWE[sj-&fU9AK8/*Je1nN\K%tKch0sCZ#MR04(T.QVX3=E1ZGM&ZM6GB@T[PO>2B4-PEC
RFkl>"mIrA]A(?9"hmO[Y-A,EA&CL?W]A6E]AQc8kk;Z[9KqXYL-<.W=Hfu@aj9o!G?bnN@f0kG
VmYaO\EcsaU5MDsbFe[1FMOjS;nn4.PFKdR91c6=Jo#iTIX#^Qs,Tp?a=mKBUMa3@:o+D1]Aj
A91Dq_:X[GaAUfXqYojU3E#teI.2\#9Ba8i#$aOnp;ea<@Juf=?^VE8LYE2lXoTQM^sQgF.g
RH<\KD!<*WLU_R=;2OkA<j(m.MVfIcgI2UA0B%?TWmmRoZI@*bd.n=c`(AJO"M9%6U$Rlh_t
]A_#3(1l3]AiI.Cn.EHFi5WAV-BZTZH"$UYtpY4_@4Oi6d86A]A$0!i>Gr1[PG$je;a>80Preq=
:;@p+/PZ=B!H;sa<JZf4CRA<^(0e>j=^\MnPHK@6Co[5-J!H=UBrXZ=khIsnqKu\@58&MYWJ
^"`\+]Am;n+9kB[$5jBNLKuVlc+oS%`#$Y^CRmr<<u/Mp1^q2'j"qD\D6K%<QnW6+rqh6&l:m
EeU!l(C5=?aPeZmWkFU/.:Ai3eQ6:i>Q(Qb*AXr>9[7,gQ5tbH7Spk.Usq@_kW;_,q>-Prg9
;C$cP)"7J+BnQ#.A5ZCX%t.+&)IFkR?/J4bqT5ki_*eGLU(FnMRU%?q5ZH=5N8cq[pgmRoj2
p[/Q+?imqMCRC1%Q?IV6$48]AVe3<AXGCiBK?c^fU:RC3&;1j?pTkhDNSfR)S+IDFOWP>E9`E
3o&h49fo@RYOYS)B<oE9/j\[I&$t8lhgQM<7fjO(PV8sOF>%q(uC/hCBfdQ88[YBOI[b7[G&
`S=j+8Wc_*Kg\#bgH);kjV(L5k9MXV4+RDW&P>aD$iNsISQ_>5oBiNn:#0Od';&qp;'K-1d$
b0gGja#JKV%i`^LQ;(T@07E<<0E24~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="1" y="38" width="278" height="150"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ComboCheckBox">
<Listener event="afteredit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[return false;]]></Content>
</JavaScript>
</Listener>
<WidgetName name="a"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Dictionary class="com.fr.data.impl.CustomDictionary">
<CustomDictAttr>
<Dict key="1" value="1"/>
<Dict key="2" value="2"/>
<Dict key="3" value="3"/>
</CustomDictAttr>
</Dictionary>
<widgetValue>
<O>
<![CDATA[]]></O>
</widgetValue>
<RAAttr/>
</InnerWidget>
<BoundsAttr x="66" y="2" width="121" height="35"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.Label">
<WidgetName name="label0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[序列查询：]]></O>
</widgetValue>
<LabelAttr verticalcenter="true" textalign="4" autoline="true"/>
<FRFont name="SimSun" style="0" size="72"/>
<border style="0" color="-723724"/>
</InnerWidget>
<BoundsAttr x="1" y="2" width="65" height="35"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="label0"/>
<Widget widgetName="report0"/>
<Widget widgetName="a"/>
<Widget widgetName="button0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<ShowBookmarks showBookmarks="true"/>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1366" absoluteResolutionScaleH="768"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="850" height="540"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<ShowBookmarks showBookmarks="true"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="850" height="540"/>
<ResolutionScalingAttr percent="0.9"/>
<BodyLayoutType type="1"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="4"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" horizontalGap="200" verticalGap="100" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="8f5bbce2-e7c9-4c64-b19e-4299736231b0"/>
</TemplateIdAttMark>
</Form>
