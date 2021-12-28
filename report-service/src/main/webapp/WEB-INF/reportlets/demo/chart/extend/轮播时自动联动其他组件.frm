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
<![CDATA[SELECT * FROM 总公司面板]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O>
<![CDATA[江苏分公司]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 总公司面板 where 分公司='${c}']]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds3" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O>
<![CDATA[江苏分公司]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 总公司品牌 where 分公司='${c}']]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds4" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O>
<![CDATA[江苏分公司]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 总公司车系 where 公司='${c}' order by 销量]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds5" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O>
<![CDATA[江苏分公司]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 总公司员工 where 公司='${c}']]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds6" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O>
<![CDATA[江苏分公司]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 总公司月份  where 分公司='${c}']]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds7" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O>
<![CDATA[江苏分公司]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 总公司影响因素 where 分公司='${c}']]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds8" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O>
<![CDATA[江苏分公司]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 总公司销售分析 where 分公司='${c}']]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<ReportFitAttr fitStateInPC="2" fitFont="false"/>
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
<ShowBookmarks showBookmarks="true"/>
<Center class="com.fr.form.ui.container.WFitLayout">
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
<FRFont name=".SF NS Text" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WAbsoluteBodyLayout">
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
<FRFont name="Agency FB" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="JPEG">
<IM>
<![CDATA[S@j2R'5-#`4@og6!/`I<5d2@6Al'n,'G7_l^gIc\-52H&FtkI@X=/>/HK]A6uJiZAS&.g%s0d
I`TEBQ_/[WVZX*`33ma5UMBcW8fNlLnr(IJ.mB1I8Xfp\Eit?['t[L6.i#^9?l!I_XKCrdFb
&GE;f=[Nlf#0G/s=reLFK#*O[bH[:!`ZG!ra]AR5+>3I[pAT<GG*BOK1h1a;:WM%O1TiK5jOE
KQH^7#-P-;t2;.I"=QP:>E4EBur.D;Zt539,KiV5(:4p>^q6@E`@9_o)Il;s6`:pH$c?4ku`
m9#]Agu<23.4q*on>\T54`)#BBcP0NfH5:G6W.N\C1CM#`4N@'Q(?/2l8#"8qdin8lNX*'5j3
TA^Z`=Q23;"ARHK+7tT=4PCND%/_=s7D5SBLD)G7)]AH_SNPk71_0`.[e#&sSbn=U6(/(IG\b
"r:=L^o0FP66%O#5Q*CY,[Ah=/P+9j>)l=)?d!P;LjXC?tTE2jg^A2LBL<CYPOm`uQO&T'A<
67JGIVBCED&(G<9'LVC=m?e<%5^,pB(re#ue*^=rHioQ6)?N9Vps8Mg1\1S!&@Z:<^?e7.FX
Gc?%_=I)IlXDP,^^lCq?q9`M"#CWt91jd_-0r7lbj'gN]A6p>OLjoQ.f*lUb@K^n#0At9WQ2S
I%=P<_XQ+^>QgN<rMIOk[_4OuLQ&'0Y_bT?+S6fM9mJfJK5M".0i.<m5V;5lRC1Ee3/<+,eS
@R0-%6KLd>&k<3D0c6PpTa4/B=dX?O]A)"/pZ`pPW`Xni$g^N[4k("k2ah]AN#`,;nc^cUI-<g
+O$>TRT*<?<PSijuEVn7CO)3\`.A^_b;B&Ks9d5qm70Y$k^E?I:,5:d=Ql/IfO$AGkARN#[u
[WREiQ)G"<qE%`%0?T,O@_?>&_?O5'*:;e!:h8r#\Ka:78'gahT@!BL4h9QJ_Ret-H<#I^S*
JKT-CE9=BUFpa33o&']AqmD.uM(mAXcN23_`,WON[faAJ2*'_k\6ot)66]A<T?;?YG=Z3g-G5N
[bQti]AYl?A*uG``t[oq[QrNciV;n_jBt9>G#QJdJ6l6bVqZkq@\CY3Ss%5:MBRVn\Z#h]AKO]A
H4<mqghDG/[rkB.ft;TKa,LSK*biT#coEQ$1MUHM>Tu/8'XKHRGKs&rA))AlZS?"*W4_2ap2
6hnaG4)kEg>B/""Z7FQ@8QYPftNI,"AhY)J@UHqFUPmYFEt3#e/(.i`]AQ9;B4uM=K=4m3MK&
h7BfkCV2KO:Q?W=ud3V%1noMlWH'6F?J0O[;3j@VOWHXemJu,7WA+6gEN>%/fnWN5t^C's,T
:dQSGj]Ae=AUY`6aiqDqTl+%?+q]A"96Sd453-r4h4q2(gL/L+O6P5)F6(Q+7m)IP%%ph1*SiY
QVnFNQ&LIM'o/F.bJ0C0_4Mg!aDN"U+.lZOY^UE;/2+]A[ffHFoBpB(1*gqiJn#1qC8/-<g'a
I!A\%XfL`leerTB&!"g3qr^b_VVdse0Ds9@V9>&<4QRl^"SWXsdP8"($&gG9(j/'\I6X8P*7
qFC9a!agIN0Lt3U1RrgPg?c"bL)""QtL-:e<T)p\&4SRit!\DdI#'X_B@[54GnAr__dU6L4_
+b/b1T)L/\!%C@DjWXLkSo+BF7f16Ha$drLM/_qnP9U6.&%n"1pF$s]A&/Abhc[>ac$3*<gP0
W+?QeY]A$_\%W.Gl\8m,8^QVi*Os+hj'^dk#Q>#=Pg;ga45u1)rIOQjNT>]AWjUqZ5]A*0Mh&'F
$nUj`Q_V"BKM7r=`%2l^DE&gH^$L.q)9$gnFY`dbVj]A;$22LMr>34X1&V\t5cins77iNEa[t
Ru<@`i;<.*@eSqTg6A:$7m'88,IHY.mC^ffWZe^,Sh8Hg4;#Re)3GTIIcYiqK#j/JUoph]A#^
t[>M-\gh<YDH(V"AMkotGfrSbWu)r(l(o5fn^oJr[:,*AO!%6Zo)nc_Y8Mqh#>a+p$PHDXt&
n[(TXA#IHD5`Z1tp$T[H/,^/.(#YAB:*e4-tOor5Y@H#qOR(k&g?>5^eiLQ^M10[nE-uNVro
UWd\/KU.)j''/NYikPMY.ZPWZc7-qoQ3luGO^%@<QiXFa/+\0BNk9,gTj5(Qq_I=.)99*&AL
cLT-hnsqV8afdgBSkaq3[dVN,^IG"IJfam9O4(1hG\YBH*r3rYSNU[`l6@#W"+]A;,:f!sp_u
dk'XU8OhiG&-KErWk]AFE#5=7\JFP;_Smg;8(HLbdq1Q>:*K-7\O\sDZ-OD[@\-/::pX4@V@-
U1m-GDh+[l0gO9)H_"3MMWs(#X^Bat$??enPes_rDOb*ER]Auir%>_6W41P^'ECG<^3Zk[b_,
T'F^&B+r)jl]A=PWIK&Bc)J6;=FWJLp7bP7tuk3.M5ml0Ps"q.dsTjpQJOhIgYCs&)?hX*u>&
V6RH$f42"3kr3qO*P*6]AbFL]A.he'9bGUcjfYun%=mZG^Fi;6/Y7n7]A+"tIc&_lX,jIX#jL-_
FKj!HQoR;rYi[_A7:@juR#+G^)kn/fOR.O]A);B$X7-Rm#DomNi)D'C^U@Q8u\;>l-)1TZO2F
2]AahT&/;VhltC.G8qnttb'@FXdhCs'K+45<@Xc/lnS1k>%&I"bP_]A@Ad&5fp&L04Z7fkr-8]A
%DokSE#`8tH%lFR1+0T9'`g^,l\3Zpt1:SPBGi'G^a8dnl/-hW2>:V!-C<jaS'@jS8K)dmV-
po0pFh[')fQ&Y%onc^p/Q>A(/b;q:C<5hQ:@/Gm`Fk\Jr5Y;d?"W9LZllSn6Z"%(M\M1V\[[
e,f(mRN/$,e8i4/Vf>r@MhEV1Ya]A%Q[,Nu/3m"cXL`VMUS8,WS]AN.K7'?dt(^,LLC>XI&+*R
pdXh-:Y[hWbnH0N8_]AK^8>eZ8o*b)1K?c@m?5?Hui#k?1&khV]A.(iUb)jYWtEs^gI?<@fd.7
P,d(S4(e"7oCTSnnjkYs17%3QhCg\Mb5nspI-T5Jf(t8DZ50b^\;`fh$,[8)A9t*UqZJIHas
Sh]A]A3CC+:^LVaWgE)EAbJ!i7c:G=;sKrnKq+c=Z'LP1m7bqVroumP8&X'uV9S:2rmQ)RmB0_
a>WTQd>_P0_K6L!$F$oD4,)?9*+;SDf`W.u\J>)>dO[_6`hXRl#of03@W_/D>*QpW$kdFUfm
p$NS*j+m(`%3HWS$]AYT)gD+XTQC-5W@t$Q3Nfb46#]A`fo=DJ%CsuDC4uR3LHjFatPObVqlc-
-rCO@BEp2D6^'C#gs#PK6A:>r_`K\j9,*kMJIHVCVHT56J:gfHKiXG>/p^gdE:XobL*IMnY-
AU:5a`oO=AI<LJ_Z!rHXZ?Z/X0L"]A>3_n5@93o$lJl$UG?BfLAX>O?N1@LD(S=J@<5q)@iDt
Cu?!UDZ$P87dt,/,k1[L*(\<Zh_5'QCVlR_r"s8DQE1:56_<#q/TOq:cdL#kaVP"2g:YE64=
1[[&t+ADX;%\YeOO.'-5F$jS%CX%a0LE]A\$A*hO>oZmb'+FZ8;kNU^$'$'uhpZQHNK4V0(Fe
->DX4BC\EAH6)"Q-8S0PbQ&A2N('=fKSRM^eH8mj!/`*!r<lU8Vf<Sm[Y,A&3.PXR)T4`bgl
hu,Hc)9o"9=5J'I:40l+)%J?L/q_rU=,7D&cWD'>2NXgW&b"C*@#r$f@&.<JN'\d18\Mh_t"
6k3U[%kpi9>X2,k-+:n@!]AF%f$^MN4'M!@LN'50<F&]A6[42H+ecSEhh,X6Q-![WH!l9Ca_aR
5Z8j>ZZALa#@5*@BbnRnZZR5.CT^,+8inZ\H0u=EXc25jI`R);>p.=8XKBDV3[K,d,eVFlQ,
uM2p<""1MppkYFp"dYIbI?WdVo,D2\NNpc+hqMlaV>_&j$Yj<L"LXZ=eV=*)B"'2L%5u3ph*
8)*13FX6g^g.X@VqooLn5cCHSG:^n;RG>1QeR:?<Xh=>`Qo-CJ/A`?G#9gICNS<:]A^Y/2r02
3'\Ie?9m$13aaX@ga7lI&bE$:lY8B\OMia:fjqGD;c[qC\;lgq[;ra0SJ_S9]A_8s_1nC3).;
#ITJ"f,K%V?IYDRDDj;brBAY>nAtBJp<h1J+Q9=hk)j?ahZObAhK1.VpM"fQ\n@Bl^T9mK6?
g6gmfte4PPHd0UCE]AMW-IHVX1):]A&d8u*o_+en46<qQD%nOkE7>us!(:G3G'M5uZ(%Ps/VW<
OaJ'42K7a3"6Xbq)pd'[)*g-O'6bhBr@tbaic2K:rmBEiP/S]ApdG:1MYZg.-`;5B?]A>t`-Q*
fe40dfH7eR$=_tPBT>$K#*YF%,b/,3[q:S`+41_M<f[I0S"Dp6E"VNJcl7GKU_Oa?-b%T[m9
g=g]AdJ*e:!Z;DGpU(@3m;!P]AYOEbA1^QFm*XakYYt>/g<o5TC$nda.ACJM!nH,fhfu10n9E/
ph;7Y$]A=hTE]AeZNpRaBn>3h]A]A?*h,U_F4lhQYNIdZmC0Fr:Vu/[=&Zrnm^)9fLaJuXd]AQ9ZG
%cW%tYZ7*'FX=!)RYe"^:9b.^uQB&V!`,UjhWqAujHeH<h<<!W8.<_OjpR]A!B_9$en\tO7@e
kKF&J<OAp,="H+aNlN)n%nK8d*&eOC1j>ZR@Hk?fl^acnG3Zt!'9l:G/]A91`Yd]ADFIqc8!F8
B3,Z$%cOd!_BHh<l($TCWDu5H#"0;"$Ee62!kT'n2gDNRH!.]Ab6(\`IlH]AXXq^=T$Vnpkd3"
ueZ.AT7S=lTVn4"s()DF[?UGUUf1=2]A8&b`^Z,#_VW=7b@FqlO6:94I1t'iI'o,7655\prUq
Q]AFHb=^]AR]AhCrZfRJ9fJWKrcj['3c#U3CDUlG[1<p)1-a"CEG\N7s`FjPk.(l44mU9`ej2;i
NK*O*q4BI%o'/%*2oN2@ilNKLe,%%uB<OeJrOE58jPo0%A8kORPOa;X6:B;^4SiLYfj(JS5&
WKOXg@\,67B-YC)TSS@3;a#lGoOo%NNR643f@Ap*lRNdr#41t<J-M3^XZLif<%(ZH>'fW%+A
ubS,:]AX3>`@P0G&f<O+UDs.M8C:i'U>kV\4tkK%a%?AWBl$HHEW5KR]AonrRo=D?[UDoSN-]A\
b$E6E6?i%BgTq<jUYq)AV,:Z8;Vi4;K'Fo-3"1C'JmNW+O.mUfs*pkiGO\$"$lS;q"tW7>'O
lG7KAd3$n5$FR5E;uFuagu>L`j%LE<q<!/&jc.g"Y+ihdf6+eLQ[fXgToL`.3b=l:g)2>t-/
k9Fn)1<DZVT20\#oR/U%i6r]A%EuX;%>2[H;4t"*cQ3.P>s*3,+!_O2nA"+,Qf_JKs2OPAk7'
QfcejA"OcA(+oT1UFlqK@>D>A'D,3%SDF,1A6TiDjct@lG42Df-aIjg)).hjC-+O4?;[oF+#
egQUC:?T=9K?"#nq$ek'*[p$DJY'.#3AX0%5T\2a>_ZU*,4i_OhaIge=LBVXXuoG%i-W(NU(
BpFe]Ao_op.p&!Np7FW&KYlfA0c3=S,s)+)dU[ASX.H.>_aJhS\.9n\]AZ1TWDf1(S2,;7$MT=
"]Ai/<i3B6mWGd%/Kl_F=2gZ2=DRFAHp)#2f!J,;rgR[o&Z!RlUA%^O%6hZ]A^i^Ur(P!%;h4S
+oUA*ac`&WW2-o2^.d"`+&RiO5`YMpm,-q+fLOIn,3g%s0_CDq^6s6"3u]A^_C#b$d8gF)C65
:DL=Ke@rWd[[Dd4*I;R\0o0g(,kIDWe[17,S.drY?%u7`Vm7%*p$)B&M>C+^N*nin9._+_cH
g,3n<5e.58W82o`qq4C8[(iA!#+qni.HAR8,%h.<8mI=\g%G]A%HCY)5UQm_Q4;'l'D,iSHT(
I<0T%>=I7F4R7SO@+#FmIo[GLu(K/KPg_[UBmUP8h7Ipcmos87Q:NQj"^AlUMmkP6R,=L=^b
EY3q'IZ<$:407S;E<#QV]AXtRtaBD#o#Pm)[mAY7$4*`mu(:5$\&,7N=Xh@3,5%"0uX;e#cK`
3bbQIj]AfDKk@S-L_#/im8;B=Lq,L;+]Ak5O&M]Ar(o/o4OVSK\fa1=p19AJVrKqkHDB2WcopSa
M6a<gcB*,)Di&u53hU`?S'BRkG<.YqmN>.%mPQ4bc?=ZQ]Af/"jT#%ZBi_Ffj@XiB>6m\POX1
38smhW6gj^#-J?I0$c)M@XM8Jg^=o'c+UKd,/Y=hVW$QZeM]A3/%;LT0Bq_kYuOXYQ[aEo?XU
A2g,YR^CYYGqrUL!Y>6VE5-XbRdU:`#1=M+(j*7]A(o(&-h88LBKGQ*h^&?EVD6n]A&kpRVV3q
(G[?_8(d3(oV!"M3LD5%nf`o+&*e&01ocPNpQ]A:l-ZpHIGY+L10OKIh!O5W'[GtCbJYY-*3D
\DOOI4pLoQWL(o+E7_UAI%^+DS["".c-gOU2`=B2hW`#Ql./Dg+t>gA7`R:W5c<i*a(N9<e[
^YK0^e[hoq]Aq9%>ok>j-(IEG(57b*$W-9:TZKk$1?XaXRQ+W1FX.^1=?kJ3%?T_tP$\s(fEh
ZWOc8JB0mTV.Ys^tabY]Aar$^(%Zn1M^^OM[JGA9=_/=4N8IBnQ')M,rO^qdpa-1h<s=Z>U5#
Wo8qM*@m'>:2't=aT"o$1dP\i#>d+_Z@ERUrf*BU9QR5]A^E%Ar%AK3OKdd:eMBr-lJ`a3rXa
MdKJ3TC^^g^uV[F:ZG0-ANMQ`<iZ3XWO`dals/ahrYk6fK50:/:W?)d(n^3=$+eW8NZ^-Rp^
U\m3Or:0>&.FBG(kT->&a"K&5[qAg?cW@:cY)QX1NLM10&/J)o5:\(WTu'aj6QWMqZ!1g\=r
\N%Q-J_*>ElH$@rJYr-"pgPipY;!6;Oj5PNpI\*qo>2OGXBBIZ!WR^c@&UKOcYb4GV</\Y=Y
%5lc"LnDC!Y*W]AV$o,J%PfL+MZ'(NdN0EFmZ4a.D71_%^a*RRm@N#t']AT\6hj2jW/M(t>l,R
R/?)EP>s%NKgE(Z&:WMF?h&\g&>>TicQh>]AmG#UfIY\"`P=4)6GF8ofb+E0[;.p![+Khjug8
.^X,Fm@4'#_)M>#1VZ!)H['#J\aXg7a9OIn4V*#PXM*JVA8<?%StU7*m@,KG9maI'K0P8W4;
poGs7\%!eSF@`,uj2%ZK9S-m8!c%m.WD&c>>3IA'W3kr:iuj-/sPRK'JFH`L\iGi0\LgL$,@
&BV)@A1/joV_K*OY3ft9:IH@dM94%>;fqabd:q*4GLhB0Rr_`M?e2H+_bh,=[-$Y::$e;R0^
)_kC1:1rXK#b.+ct\a8]A+'EWB<L`i_r:(m62go[NJf'D33s=8HF8uNQX:0PL]A;GL';84"XY3
^bS'L7)q)Op-'m2?`O=\7FjDiY0ms*WM5<3_'CDGu9WJT/T>f%k0>s\+gH^lc*N#iqF+nOM<
q:EddpWR2kUdo:h[^TB?*,-8/.[)%qB;cWG2<2\&buXd#LZHXM`Y\deb#Z[i)N?)1UE*1&"2
O0*,Q`F\LmJAX:IWef)kJ^Bmg3]A(:1<D8kOOML1Y]A(a`n[R2+P3CC97Z27Dfu%mD$37-]A6N7
Aos^JbqW7_..qm"Tp_O/49POk`/!8mJ[t5.lWLu?An!T]Ao2^W%6?N10!#'XgBG2t1cM`ipU)
Eg1c2#2cge`<XFBe0R0&6/X&c3F8`GoX+K7W82-U6RfU>eci)OJ,H-=`'n_\lRaQ%V<FSPt'
[3.TR`c!luK!!\Pd389#VS&TCCc;5e"+SL6saZo:M/D(,/gn9$hY2?np&)!C[:mTa5j9A^R/
B/)l4#^'D1:k]AmiEA,O.!/1JG/"b,dRTslF(r1cAj6rB='I*^$G5E0;R>gk\;5qZ[q^65cP8
rd"iCpB3[/sTB"hL['!7?j[#aL5W<Tq+B1\8!Q\.+N[=qUh<5uil>FIAujNSm/)N;C>Bb,"q
L7T6,"<k34uq<U1q39F@bZh/_o6jDMZWfTLV?r7u!)%6KR5%Lhe./8c8n,=W9qYpGf?flfHe
I)lB_qKq!W^+:);;:3&ln:o3K1j<0McE]AqLTtK2jFG"HJeTm="?iG+k!X&/igDU15*c6f%kW
J#QX`Qbn>)BDZrZg%U,p_BRTVAOD(359M_hLkBlcM^ViQTGR%)`@NAJb#\iK0Hof!mIqKR]AE
$GJJu0d,KS0#ZM81qO*rXZq-j(S[#.s/Kt*#_8QQ`Lr\WmY.m`a9K5/)2(%!)@9fMIN&GiIU
F1H;c5T/]AKbf_DWtfQg+U2f3+\7'Rg2T,KV*1hD?U83njRJB*nFfDICcZ4nub"oP!CtQd*eC
9ecC\S"$s]An(^AP,cl;m]A>Ki\P&Vndj+m^bBQrBsC'o7H`H<j<JH-"e?UqXd1^=52LpuOrb2
Rs0S,dVh_P-&/+;^>s+<(^W%W?IM>"$-UrT,K"H%8cQMqfDY_lc\ZRCV=YH=c*BQ[Mhk4?r0
`E"9M%dF7OoU*[OnMCg,+WR!s,U&NOHsEb1dkLn+jbR9VkOFiFVYXp/J@^^<p'D+`:[M4T7S
Xo/!%\?UhKLNOl3mgGLU%kBL/b4LguL)2J2!l25(1sgu.Q&`.F:REol8o:Fm+nS[$_0Ibb[o
&*sAlh4g+Xp]A\r)q^LN\O6a[6'7jmW&M3b'6j8jglt3I#8YX@9i>1roMtL*?1?8]AQY\+Hl$`
%-WSo/;SE,f15L/\7l;sjUWDpf(\PX#'%cDB+RigU]Ad#Du#``mP+1oiS%*ENg?01NCPhhU?s
0@5P:)P,R"]A6W2T7^k_NYZe$QZ$R*fmg]A>[\RDu8*-9qklti;d*N<J1*f_4)it>td8;D'(]A7
lJDs]A%%Y9aagi5ftrH.gXS)7CD8QIMPAE-HT-IDqdS6%cZQ*T9Z0C+*00`e7EFP;;D#@mM@@
Omeqi!0F0ml7rgqd6:;J?B@Lp'sNul`q6sl'-6a"$6*AAcCf]ApaTVtrTO#*r+NT38Z[4)>B@
873\HtJ#bhh;d"-i&Seq4J`7*O'DSYmj>BAm&E8l_<-+#C-kWGh?3n4,h^!kC<75IUi^_H[F
+RTY)l8I@LWQ-66@N92)[MIfATe7YR[F@jJHc_>6-6D.[`^aJ]AH[3MN`cpa)W"V"0-dhjIbr
41rq<$hl#GReaoM@Z4Y=/GSI]AQ6G-a)D-fnK75R=q[gslO@n=\,L-AIGS]AB8t8<1g9i]A'g$/
[S2dSbr`E4V",>,8M2^:.9oR#BPkQ*;O@5j`KLte^017gf7-Hp$%3KTAeS#ZNsU"t98:htJ^
?.I_]Ao/e=EmTKG4(SB^<`R4CF$ZY;J8%c>V%H%FfN^j._CZ,uC$EB.TWhD:0<!V7uCB#!TFF
eRB"F"KJJIh>,>m2q6"VV2[Z>4K2cbgU;X@m'BRbEu@*q*oK7lZ[FjC>'B/UEe)nH5<u\$O%
[hYs]A!O'ulDW:4lL0D$Ciq.TiLkjLp3k0!K?eiI<+Y;@]A))K7r'\8#l.EJ@Q0$Q9CP7Bng:&
$U[p'icF;!gACV%HU99o$[9=nr_UT<Yb0'Rt:g?5K)W3\#6Bc\Vo9n;aLBhpBF*)RX8aHN(C
PXe?Z=$75N48(cKQap2pdZ"U\.,!E]AP^c#Tbr.mg?B=/!>pM[TDon/*I_+N!/Dm-_#Nb+'U5
M5@g$Y56+hMCP>F"pdPSqeW42pIFa5YP6%M?^r2tGs>$MD_CEa1p<mYaY5?VDfX"mOJTtA0"
GoJX>g58!5+-I-$r!`fI4A@^fB6sY.;i@pb&o<7-n*^G"efM1njuUjQ,*S1`-XR^:U@teMaX
S@/\T@j\(.k=*_`9OHTsK&M0Y3!G7.N3_Wp#olSK:E2e!=hHf;o$%LLlElf1EL&OR4pW7s,5
ghlFp?hA\=KM!H%e&NCge4]A>Z_W!IYc]AD54Iu5Q=k#'kjT70nni#7BF47m?/Q<47IY\Y(M;P
qoK/3_dq*Rlm9?P[g[b]AsrL513$=%fF0et;_A'gXS;$DX2g((1&_$Dd>6Gb9aX<Z.ss3el6]A
N\2g&GU41BQ5Nq9;U7ajc.9oR0/+-l#V%T_<k\:,<XXiY+<&'s)@tc1F/kWsB3Ec1h((>oe%
j:f9?_.^,m!!/j]AnaZN!:W-o2Pr#&Wa'jZ+I%_\*\L&YtJZ,al+!]A_k[Wjm6Q,,W1JOp+@rm
YQ<+qa3@Ds!,'BC3;;jtY+rK?&HK<Z8RE<dqPcedCaIOCS/+[2tP3eN"h0FGq70Y]A0$7-f#a
T,P,@Q"FL(U,"aHs$O*EjBVk2a.S(A\nP>N@V]A?mf\kg#CsK;:5<1sEm"31(\dJUI'8/3l_L
c-@?O,pW'r#*gN*;hk?"^^J+Ccf_<K$\#Q/kHSDQ*r;P(O]A*-[&tN\!e#OUJ3,1WGe1WO_Yl
h$F?'3ae2]AXX1')'eh>VW4=\SfDg_RFm)\9)L%oiU#`g3YbG8dQe85dIHlB(k1=f&dD!pQA1
e/Pb+iDTQa8m(m`%bo%YUN5?cH/eFdpG\M<'Tkceg\)U/kq2LOHgHWI*CK%a'2^R)pf:K,m3
FB*fH!RD&@8Q\/HmU3qm^oM)1,KWDa=El,6(_#6;Vmt#06QM1<o,PcV$_25+)!n6*>@#VN>$
a:P:c5`tVl>o+i#=A-)/IOm@mR7EQeC_,-<VhOu;jfuURIaWt%1tg3q6Y"_=c#iQS-NQMP\t
,8/Ef?S0ufUAij>b]AR4p$4M:XtB1T'5h&ogP@MtG22O4cX>h8(+:0@FLNj/E$C7^ECek_Sio
JX_O5/Dt^m3Udcbp1^Oc>EE3UM=/R/49I#KUjsV+2?nh_^1&o$O!@%99IP,2p6P<*2_0H?)(
A>+HX#/(Jq=%$N:K*n6fTSP8OQ)tAa1!(Zrm_o&Ti5TWIcU()RkTuG)i;de=VN]AHt)M&R\<*
(@CB:R9D=_pqL7$aP@%pY7"d=IJ]A*r:KJcNI7j(FN5QaQE\&rflIb*Z7&PqFeg?Z%lLj%/F7
WJRn./Wd'dYi28lFh)%(O\Ij[&YjXUj*V5_P04j(UWIV-HS_-ki=O"2kcHKPmsVUT+EmuH%<
4-EUC_n3aZCC/*9#_GaNVR!d'l?/#c^\-i(sPkdUj0lr7J#rTBE'')EMp2EnUYi,#I5@n*sp
HMN2!':XtBs)Sk)PWY)T`>+8]A1"'?\)<O3Ueh)e"2cQ`FOG=fEedI44_K2&1ainU+/+3lJDH
o'nJE#[LRtd!6IC!Ri@-edYMaD:Y+el29(ICG@CQFDJoU7aM6N4('Ha>Teq#%e?i_5'.(H.$
;46Xp3]AMP/MFQ,1jkNB^sH0e-#mr)+sVp6P,aUQ(YVit#hoLO)i1lI.V]A:>At5HgK&05[-kM
=LrEmQt]Ae]A_qTe%lZ!6A-'j.ZiV$<7<I2b3\7,5Wa#YcV4r&6G]A,XC0WRQU"T)ALT<U%#^!:
&0WqN3A+%6C0[2q9M`R?sCfI--*(O18q-^nG?IceNWUA6;M]AaBc*D%6k,-o5$6X8E,0F6Q@m
%#9,kZPrcG>=.KJo63n^n]Au:OEGZ5/-S9UIT,9uEq&$k_BFWkaGU5="[b(_CMQ0N6!KL.I"'
eS;HU,9WaL5-,7*Kq<f5i`oa-VPib8T!p);gFugh,1`Q2UHqg'OaJPl7VGBoTnq\)!MBr]Aa<
\3E)5K/I@r0HfXd8Hg4s`=om&O</a)@"2^&P_R:_eOp!DJOp5l_"8hDS1E4(ge@6cnc&B(un
ZIW24!`i)9O/lB?]AKa@F4M2f*3GCQg)iArmulXC]Aq)0;_;mH*c<(O@46V<G6ZH8/PS?/)WF=
DEc@QM-Hb<2;lmrbW49Mp4.+inrU6;B=FJP"V:c=!P%CEBF<W7F9a?n^:fDi?kKus#S[qiaL
4e2A4`KTje9E=NR,">fkd/`j+\Hlk`#0\ZLT7qQb_ta;K`A146oNHf=laIl/.bbd%$/VC!C^
A+X";@#+nS8J\`Ho#Kr)$3I7/i`'l0p2d[/620PLi<Uh'J^n`h4`8((/,^h;9ma\@:o;$)/E
--LO]A(I<OslliDoiEr=.u3XA0i#H2g<ULU@nTBBMUdFC/.GcR9m=bqOX8@>iTrEf-(lM<1"Q
5V]Am33#3fqY%h=3C6>oiB*ui/*u4&Bfp^S,!P*fNQd4ob(VPl*QAUbIh`6i`SFMTD$!.39Fc
[%Ctp]A@!?/YN!^K@KBS=hb2!rC=&=.liU_Hs1`=5Kaf,]An)U*hJ5`/nbYTphgI,=&4";TS\I
hNCoY'08d2:o2bjlp>WJPK+(d1%W&na]A/DU!.B43MQCVG\5@4CI]AuqnX#^2q3dS3m?h8jqpO
g-&h"=GWHC=;ER#FWLDrBRCbfr,tO.d-QD#`d=.6lLCc>D:k]AtJ+VK<+4?l;KSl_)g(G:uYU
C+\@mj7A=D]AHcbs[a$$Ua*8i>n/fnMOf<Ip8A*A7]A=,juNEV5:K?:fNB8uKq2r.YenS.c"R5
7.]ANZ[>h=qctY&fWW)+QcC"9geTXJ3MlZ5?^4DtJif;NX_PjSgqfL)g$nZ/dIA$.Y$:3A^?5
YiCi`(R-tR3;QQE)YW@#9[Pg&_[1S$>]AdB]AV5[e[ghNW#&>B:PC^7E*ULaj5%1]A.1E%`s^H>
0J,/W[F;cN/"<TN)n/PELlO$o=nQ9>\"6sja3"QTFERsaSEY'Za+^VS$R>$\biTo)?;5cUec
n;dXBs9!D.2Le?6pq@UBkgQi1Ol]ACO@LI786VP3TQ;s?D6OY7?-7NfIZ;U]Aoe[DFN;o"]AnNI
g3S_`'WYnhFX"t3n^8P<K'NkKnF\p3-72Lc?IgNEps1noSFfcHHe*[3$%^k!b>-(<RLY:muq
N>[Ic9imu(EN;09)6nR%J*WY2ts&UXojG)l"lB^DmZ_d$5=*D*4!d7kAICO&c,%sN#@[C=2$
m0[i8G5^41/glW;#'I3l&`g@:YGDY#gKj"DKL!3?r`SUJCG7@2WIU39b#2$-!B&N]A4$Ubl$9
k6&6#B<#:VeF360LR+VU^O,4g0lr^Dmq?15pItLO;BbSre=Se[.,T0S0W+!p2c#<S>l,7:BL
q?Y!RR,?-mo#0?3!\5Qp*TgVFrW':=ZWom51VkJ(U1^jWJa,7c:1p'EcXI">a$q7&F5Uq`hn
/=S&Mb"g_rYhgIMlc67\^[N70>9hU$/+/nD/7_O#njc"9ETLA"GohQOV_VVLUD1)@$pE9AZC
)me&eS%<r3>@U[64;f]Aa,FlI5&]APiR:GVeF662hd<RnSdu=T\SkV4KDo8`MJ(76B9p*B5r+'
U:]AaXhG7*JDJ#"&[)3R\TREUmG5lQ<X4X_FL/n\$IQE6UN2PkqcFU.T1e+#+bpQ8'Frj#8<b
`3YbBcWn:hFj":P8aCmP7grN`h\.XT,m#-#h+pYnJQ6<3T_`0+o0Ub-LU,X3U(:Y5@U6&6E%
C'EY7]Auo=J+MA;(jQS%g&4BN&U+;K[`&r0C5Jmhl:"3l$q&uaTb=CD$4diX8B`@OabOi/5AG
7mM2*!C60e7#<S"";*[61?/!cY]A5"mh.Bk2an]AV_HC..8o_<8H6f`.V]AKbbH(DIdgX-gufO5
tEFWGNh.kiUY(pWf)B-\-E0?^,EG=a#.rWedlpA7+rkn*3:t+6A_S9\7>h\UkRaJmN$-=[WZ
Ouq%Ke(_;J#*LKkbT$LBRODE.Q)bl=28+Lk^cX2ENG[F_0)`O4$.rjX:=^6XIjDgrKjAOV/#
\VlUf<>4MH.$`Q5lK2^q^TSIm>'K/_Tt,R32*/rh;k4rE@L<.%EZHihipPEp$aBmP!GJUOV1
9-mEXAC96g]A<+8\P5X;9J*!!I+jdiM5D(!)#m0DINeY!$<bQZ<$WG!Mq8P,,);%0Y*CY?T(g
j5"it\Cj);@![]AgkC/?pnEra</L\`NfBd/dQ7m1tRLm$Ou.A^P4gCu^Q0:6@L<$S')n6^8TQ
j77dlqTa[,pd#=MqiIj'+05/?1fWkCl^V\/j<XKZ1aTAo&=a)_-,)[E[G6L+&C9ik`[]A#]A0=
[!4DqKdp8>f%.kFrlbA28E:#R_qkf7+ZhJ%Q,b9OH.LZ_GgbT/h<\a<ZO=Yi9Y_h&1=5S%8j
Q4&^6$rKke"T0c0q#^:9^]A*?nqsS+XA<mEOi$]AjKWjLpi$dd*pfu0"@Ng35u[PD8p`!bLl*>
ZpJM"55rNfa2DKil@hQ,Sna@27ni>eA6PkPi<rX#ZfF3b<)WJlL\jUtQT,%>rpdk*C'5eS0W
U;(3,J!-6t>jQG77=_-Y%8Q4&_`K%=oirc\F4\mc>4tXI_:.X\D3AYARbE4dkih_-o608g?K
`9O`@l:bN\s((eIH#GjaDeEL6[$rdolr+9.l@t0'RW>H+pMq?b,tfHmE0US8UE)3?/VU\%a;
^W$W$3ajrKT/N\@Ta]ACp_Oe$i`l4$c+oe-,ga^_c^sgS/jrMA2?rGU44t&qKh>lrlV;q94lj
OY<u*P`TU!Z,Sa$-f1`l'fTX+2SO6%!VWt1ltlo_C4p_=.X$ZD,qeh8T(?7;=^h^7^u9hB',
%Wp"nV)#Z"fn6RWW-DGW<h(i)D3sTaq!Sap9+j")Me2e\J3>[-HA(BV_8S;&P^dR!hoB=RN5
4'-q2J_FJ.t]AUc)n4<$[NKXqEH9=d(0(!&NfOmPoeK\Ddq&h,T+\+(3kY41dpD[Ohk6iD(,q
]A(^^o\"GCV9^X6]A[LJ-^Mh,gO(r<bhQ&jZo31(uG?!(/'ebh@f``]AegcrW]AWcnG`<2iIs]A/T
qs/PF#c%</9p]A8]AE)Ak`h?ortn+5U@se1R)%]As7e/ET&6p+2A&eCh5ZnG#<?1p6YM%1ZI#R3
RNpRQMMJ*)q/tYo"-k6c'*PQ?Rp_!Rm)>oua",pTG_\%Q)3`=gbNQ5J:0DWj[VG68)deFd^O
C>7Ir<NYK[6ZT!NJX+d*2/>#u9F-$<:A%Fi+98nN@a$n87MUP+PtG!6XdReWp9Q$N.r:C=Pf
?*c:(M4pB5r`%nLK-nm[B/=Bmb2r1Ku2<NH8:#j[_>od!7aTd$b'uX_ojhX4_EM(W1SBSI!#
mG8`'gL#'f?<<6R1iVLienU.hs[@CZd>u&Fp1*IR?H.bOorI;6G\7$OSh3PSS,lZS<C4Slcp
i_dc?S3J3>3g0`4=qdeDjsSUe)R`B-0L.$+3DS-!`;?au9Rhn-!eD$fKDhS/o=j;k3YDaV@S
C\91nj"\sZ_@+fq[8Zp7`-@lcSBP%G)V^nlL1>?jj?E_('(b1ASH[l2EPE]A^ORl+k2_4H:kA
F&S2u`)O#S0G"GHa.91\)]A96CU?UMsNHIW0P!cU"/Sl*\='I`9Gbrn<\G_XQ\1:>3PJLS/jU
fA(56dEa`%->0fL^_C:G"4\A;+eFOiaq%HCJ1ZNkO^p9umV^npP/T+com&dFJ'SS;]A_h+rpH
S2E\GeETi6ipOXeWT=$R'A+i`a!W[A\NZGYnR@=F.bs0@\,TrJ_dBJ._JF/V<q558WAsHG-h
\#pp$fQ!N4iY@#2\^LW3VbB6PfV!j&o'n"MS!%0.WPUc-O^hZn3O#uLbf4K1n[!j:4d,JT-)
'_F0h9uiG/U[X*Cl8"5P9$Embf,?')6ElcMM2R8::4&.>o2\TO/TJd%9<.9l8P3`S#)nSd*A
A_BZ:j:n\-fRSmKAFah4o+hd9#2/hss^$1O<uS)?t14.sRN'f&o&%X/d&4?0IQMIptr,h&P0
Xm978Mgj7U8KVB3c`Klr"Re/u;l/"G#op=R.AY86JdFr+ck"5Cd+-chMS@"91Q.l9N26bO6i
1s%c:Bo4a5AIYr+`(c%J7(`:LlV)?fchJD\8k`3?@Q@=F-)-gib!4Z&uT/99'R[5p\jtVJd:
S,aQcjOm6c7#<(?LYiZD&l*"HNlr=lEf@71*]ATF:h'LBT*0/Qc[]AqU>A?\4N+!@YA!UP(Xg7
>h:>G"p:*,Nk;MPTaZ1'=^cRe2U[3c"91gO<+Ttu4cBY5"&`QDe,k%p.gTgm%AuYeV(OUbS;
Ek%5.Pm]Ao$-1p,:2CcUd$Lf6H%'(c;\Fq$iZ,Fc0Eco5cJAs?`'9pkV`WP32('b>diq]A%GS$
fajYK0fTDU_F*[#i3gu?k?95JTo);.E6c1SmgCf#9AMISK/Z.WD9=A1(bPgMiA*Z9I.X7ja@
t$:b(=[OKd-VrYJoDGWf_09VBQ@\YJ;C2b/\G>;GsuH5e^t)"3ggRCZWPZrV:EqO-@d682;F
)CVYEOA_?Vsmq\^S#FT[gF:Sbr;X-.k%#h`oa9clT]AQ1JS^cm!hoFRneDk*c^Os&0*U6iVE$
jc;&:V7#a2Rtj`G[Z<)#?5p7A-'_pVbT(Q-$1:B<]Aa%6.B*pCEPcV%c=0^p^/3_PSS/9<0YD
SN995I@<*g4^f@ob&%P8C7DU@bI7mp([F>a&[c[=:@kN=9H10&Sa)^\-cuI)3ML[!n``fW3X
2:-&_I?in4*[OpYIs)CXrad!V*lFaa(A"GD4I;aFAk3]AgfE$-u%E"c@mUn&S)\4(7\f4$C`*
JaMf%1Q:5F%Uk)Gb%Cd&%P"0gIMip-]AS=Wi^Mu>D/R$53c!HZ,WA$A25?;?@&Hl+G2S5W,/7
5&U^0X?V1rGLP;(ZLHhn.294N6alXPX1dTdTA5Q.0rf_DuMA:pp3^15<P]A'$qI2TolOFU$([
0P>%3V5@4<S,aCcou1\E<4ROB0$JMOKoP7K*pP2gKlIY'HDT7EU<TAC-S0O!PNXX"_u;^QS)
G.Z"m]AbI3M4I@AkJr>$)m9'f^X\`l3=SX'm3M/WmJh^3_]Ag^m0C?3PA>JmoAK&dWpV@'.kE1
^G+jsC;l8bUh4/XQH\CWij?6D)2@XfA3c^qsc6jj@\5?U.WBUsT3<]A`W(.R4lgX3;f4e%o$Z
ql#237I'2[4Z7d2r"%MS)2Rs=?k:KJ$$D6o%D@dgMF.X4/r-/eJ\a@=29u4c\+.f@6)OF"7^
(%KT'.rZsL1;Eg*)]A,/fPS1(W:'Yh,ZQcB&cI3O&NmZ'Kb7B]A6=#&nBQ"jmLX`bV2aaQo'^N
L>4-I?9-dU>sRhPi05G9M;836fGT7DkUGc1pAG6rm!'_D]A^qk=Ko-C(Ql'B'V<kQWD:b]A\os
u4%n!$s+l)tu+mrIlGg)%/3m/cV7SJsJJM$ms=2MX+R&ss.:b%%J;5V.Zn?fB:nGpL6^$EM!
RD9X.]AoVe>$6eURL<:/<:?M'8]AY8F_HE:pM$nni@061i@k#]A38tDF]A2pb0VKO0L"XcO4i4kH
f')*0muoNRfhfk&3j6imIH%=s.&XdE3(1Q/-=4]AC.BCFnebSks7TD4$4(1l:+FEjRr?,m)a2
0A[^MPD]Ao4J1,DS^`8t%D'[(,7bIKkYDr8KP[_.rV:8%m>.gL+h(D$K9b'`d7j"fcUgpE=N2
bZPjNbpnbQoFb<+UKt**dj0H0#09?JFAUROIKK=rj8%;e^L9?0]A0#l(/d%+=SB[l:BmSHCl&
/aAWb(8I@s.8.9^-Bd3EW^4>\m&p!ADOoN$AG#d[b2_pVkEl8&Gn^eg(7p0jp:SBkRT2QE_4
0<EiAEF=**9SmuDJWSr%7iYHq=_a<8.=?HhoR%M\.W\>sA8>a1U.o_W?9`h!\CjeFsGQIRr4
`#XUs5U_@m?@L_$!IY\NU(K*i/dS42F0KDl"?B[o=C7;kRV?Vr*5/@qte.pk4.&Leklcd`X<
TNq\L"'M%#Y'i3!-P5=C!gHD.#Hk&RD4_WJZm7g8P.rZo=a;Bj]AM/a_g"`(MM$#g;h1<+QWG
UBIQNXY+]A3$;@E-9ENEoM5/57COUngRIl+4W9QLm%mH87_d7R\L<pFfUH2u=nV84_<i(21/&
\jP_`c2[2kY8]A,^7f\?e^J8>M<BUGRbf%'M!C_j3.HpA47kg$&59Ya6*c(ZTkZBa;g<7-#3D
V;b\lr>HpD)ogI[qjHVuS=9#;fd2+7__u9LK]A;NU(qM#+eR#k7'2M\jRG,lI4M4[j_ID24.6
SZW7XZ^5k5o0_+)U8KX[\H9.:b9^AKYp(\2+P@sE/IYlfTgR?dA_SRCLquK(RVU^r$c>-;N7
KWgo:IhfK%Y95h1nq"\L.n\'apU529OCf?!f=S'</s[r0IM0/]Ao[if<D!f&i?X:d:u!JdS(,
d%AE_>!T2u3!K0b%Ocj0hZ>5;CM"ZtbF&M1Go[%9`[`.i/1`tHWQ@?c1,:./IG&"aF\R0RhF
E8jO:8!)E(rS\OKd65-6d*@+?jctdce$6@Hu:/D$O]A7U*I#i45[SRoekX>8eZ/>L)_jK0.=2
-CtN(jpCSdJh%:R3H5pG9n>^ctVt$@NIT=04gO=XBRtQ*Q353lPJPF9e*gN*K22n#pWQKkF,
miZ#ItU%9,-:YhC0pRTPr;)-?m/XD0^TDFkiP1l1t?"<Gc)WK1U(5s"%(<K$S<=,U?<kp#bK
08jN"#WY,]ALn:VW$#+ppp*34\u!;Q=,5O@b]AGYeEr$7gY?7R>m!JYA*O^-UMeF\X<8uEo0UR
\dET?"i.t$MGWX=eb/\!"emUO2NC^<(A_&hl:Ga;Fm/Pmi0dq8oSUJ^IA`IHH;2`6=T&I`JS
H;]AX=[u^_@QGEPq1:^aFM&Q+[f)W;P7P9CB<gcN_(+i-9VM7)=E&g=Sc+:<#%dOU=;N3).cr
/E'kgOq(th==5rh<g#_gKGdWHMKY,#!*ZZkIG$t_MC/R;%q-/@OpAI?#1kED_0H-RN*0mn@R
4dC7Y"=.F5g.2@`m']As<Y;Z35MTdfh?M5UCAl`rJ>\+24'LEn%\U:a='=oM43tO^(/EAug]An
["c7.X3&a4j&4k132WB=MoPuOSj35II$W4jI\qY>63_=f;s\onhF/s5n?*ln1aJQWos^1^Mj
GTF.E\[`\+>]A@cB:NZ\7Z]Af)+!J1JXRNc=<cX]A2P3%bl3)&udWj+)e*k[H5^DZXH4'.+=@/m
()fr5P(<YI<-\(e`FlCJQkq%SmUoIWk&=f'5l0a]A,3#i\35lX.01@j=IoV2Bt>`G7ED;,oLO
k_-MK]Aqn62e`,YfkaXYESai8^Pk#j\>b$`l%"#C/OQd0W+5P)JYgSXu(Sm18P#>A"9IDZp[&
n+sSV6uoR&O*?.%B?DLo9Q8dcjLA^;I,<)8%+)Ni94/.mWURhhLDif^L^sW,>u#(V'd3'q+n
ZbE:H]ArD_4RH)c/a6[XMNN?/T4H<=-!u&L#C08ZsFU,n`!DnHQE^g+;NE'QsIO&#+=k+E'+=
WXK2A,$%?TaY%diMN2H,U=5+[(C08:LX'Y3Iq`5LQ@E/"RL8`"bgI1[Kj@S+'/6WV#VY5n;P
Ck5ns*FL,uW!6J41]A`0^%EtfR+4SNJ-&Y.9CU1:=o2!WU+5u`klVFqQ]AL+g`(Y]A$8nb/oH'Q
tUK\9a!CG.)E>G(k&]A+ln-XEX)*HkE2-Cr:9R3>?-9F>C9U`>]AE/cZgtlgn(MSH2tR[p#"cS
;S"38![+J6YeJ*:0K`^jrH"T\_dY;D$c^&b(aS%#_=H<I0r'6W+cE58s]At"T`P='"oO/nqM3
/$pYkJt>DG1,M\R=_hkePGZ")1U27IaAM0uJLo%1L3^6tb0R:EjYhK\A,9hGkkhlODF2uWiM
8cXF(0p8$.e3#dU)#a#h5J/pZ1d2bt;F)o'9cH6%(ln_e#U2I&Y(ADh+c/ou%aLA&`YX?*`h
2pcgHP4.Q;)QD+0WDkB$R8&$*0p)!X'SO;dR=_h*[fL"W)(UYruj0<%B(PT#$Ah)A[k6&0=M
+._nsW^C]AXt]AP:o2P;d>]A]AV1qorfFTInfB</e"C."Uqgt'CCGjjRM*Mr-a:\A,cF8`oVDU&K
P@g`!Y_-np@A3kl3K+g^#8&.?.&2@?fhBi=aOG^g[7HM'sY9gR?fOtF&-#'kF<2l+rJcF)nr
Jc3cY/[OZHAib`/$3]AGCP;Q_BdtD#@o'&_KKA>7)>NDQ3Sbbh@*?[<Y#>"t.M+@EC!P*)5G,
p3@5K(NVqRak/W:n+A&dhGNAI,PpY7QI!C5[\odYL?=j+o[Zikp"kgDUhg<UdKL6EJAu"Uro
Id`T'DJ)!!CeXj^f^a92<)i)]AiprOgRS!93RoF8cORE;!HC'gurq<A:@r\,=#p%6]ABJ\##I/
WU&^a3WTIS9Q2dNC4Mo5%@kph4Qk?L(h@Z[;^UDP&bR]AKVe7`e5'L>,0d,A9cK:fScIZUsR1
^"QM46=n-Gao+l5`t6j>!,=54?t)Q=dW7UJ6rhb'Gs3!*KJtYp80WiPT.JBGsfidT;Pu^;[e
JD.OrV.%DPbH=Rj!3..p&s&2hkQ*D'6+ctF?I_GsLJkSXQc+e4nY7I?=M=Ne,Z9[1R,.dF?K
k9MUjCCd`"c07SW*__r;\/0&X``id+m>(rFQtdK`'p:Zd)jjU9+'pHi!uW?>>2u7Y)+R[lnM
aQDnXZHN<jD.c^T0ZQ]AIa/Ldt@-<N;)V)G\5W^Bf:'861ptg,`>8h9qoKp*-g-&egf=6!"=7
YqiOU?[8-/s+G\2HN24ZtmS'#9F+f5OM.Jg3OW[8L8C8o,p_\dtnQ>WuCdq;=b'FpgM,\&Ce
^R@Po^eq&-`Eb/DYlcf$RRebXkm55ZY6TCS-_qu#1aXt4mYjAQ44n[la^/k.NQf9^r/?s"\9
_`Er")<:#NP>X;`.SAGcug[,$'G3mo-;]A@a'fH:(m84b9aAIIoheJn`L.N4ehtL@Buq86%tP
,lPo9,Gf#2c'$)-@+jk(SRaqC.]Ai1eEI`?$m=dF`V[<cTPuIAdRhjhtnZge\#;`FK*_R84c\
;<;.7.AjpTJXYPo:UZN2OHrD$/nTKuPU?XY819=aEIUkJ_2BohAZ)IWq"-K2mqi*cHhE!5VN
'6r<qEE/^JbJ^n0AJ4E3i@'.:Y+C;uq<11u2gNB$Nd!Ih[$%BM&_?Mn-31r(Yi,Y0dIubA>F
M[@VCWYoL$Or.np1mcM7=o$TPKmJ-0U.Za,i^[ACf79i:f&E*8(G37P#@51OF`g#OM+\^^O0
!#@-Z4Se$q1>HB8="0kq7;V?X$f7"67qUB`Ol2G:f>4*Za"@YB@Snj3';,#>PRf;qC\YVN>$
kNSGEZ%+ILCM(.#MI]AP2$o50VoQ7_o33#<Tn)AeO.qgB53P1oQ#5etX/D-Ts+!cJ(4k5b3qQ
#.!NJhdN0lSZc`i!;uS'0s?#EH*e\mn_glA`2F,9CP[JN9+6#uE6OOFZ"\CY;%!8&3==5.R6
.nggg!AqbO<p5@t\e4'qD^T]A]AK@;G\=6?`2FMhQHni[%KG)Q2)ka#D=:0!!@9kK'IMeWI^Nb
dpf!_5!Z9nV6f9'6ntW']ArH6DP.?PFSi%JYoMoOGpq(MlsB92+E1n%c:Bp&Sci]AV7s#dE^q/
to(+ok=)fIhl?ZpM7AgO(nIo6$$\F/f'>E2,KGC26B4Z5LIpJAj"aKRuSd,Sk]AD>&0^Vhs=b
Pe^A!7+1:o:dtn,?=ZsX16u_UjNl+)>+g/kXN4+LV`E'[:;qknOagM0CZk<E`)J]Ad;WU9_2M
n`N)Q:"a)?r&q\n`ij0s]A#^jL^ZR_bu99HL;FnAC?O(b6>a^P-%%HB]A<s:0jiW1hNrn,?Wrn
A%_]AsQ2+rMSpImhj]A"fJb817iD@S<'e0s5V.CMP"j"%1]Ani9G_fj_8Q1naU6ph=oWeh[G8?*
X3R.'n8G5c&V%aeCZq7!=g&Y_<qJi?s@\]A-K[rC%"8-a2s;S/.?HZ_M4_L<<gqbb;m-bUAr`
s^=.&IP7(a3R!KVBa1mP33F$C0Xadq)[O25>MrH'I&J`#kVS#Kpli-$"HrAYGp4m`;;)fQE!
SX+p+0#LlCO(_u!/dhZ20&jg8p)F%<o%pD2Rg-o*nZbsrS416kTrJFJ=9^N9`*+bke3Jc.U2
]Ai;F4R8WcXg;d@PGS`_1WZWmSMu<"&e%gNn"a/s8'n,)c_8am)`AKS.b4$@d_FP4j<9Vi<='
',E&R0gh-np5jk"qFd+i4H+@sqWB&>6,<KP5@<Qn3L=R`@TEWil5PPL()6b7+2P>a`\-T=R=
9pJZ:%(NCIp(Ut#jocE[e]A88F4Ga@lZH#EiP;<u,]AHpYn3KO8P6irO0?Nm;'cughSH\$E=!q
jGc`bT+IY%X<#W(a-1Ft.,JAbVnJHu4@jN%H=5f&*5Tm8j39Qg!g\M?Ee!3#PF)<,F>mQ\7S
jQCZ0pfkTcDf-m%H?jJ!>4NXU-r7DGaihuncA<oJ,Z]A#aa1-@fo(5JIVtDcnU2ZlImdBm,^D
)M+js7).eT#qHNiYPj]Amd<[\(a7s33^%;p\VW?'qbmnfjC"-n'k4<PO)7`1(;52HE%'JL&`1
=f(q\0Lc@NW1ErKHpo2uUo&td>n4Db"MMA6eSUOCF5X?-cl%>eM"sUW?PTh^4(Q[OuD`5#?_
Gg9hBlBToU_#.X0tK]A&G4EKqNP`tB5d4,3!>NF4/XK37;"tJ5K5^hQR0'<9*nPkteq\468/t
qu)ra?9A4P=[HX8>22FT_3!"Q,Rr,@PQ$(mTC:iC'\Gh_dPou6GML;nA^L#8Z&rs`]AAYF"="
nW>cKff^dYqjiJ8'@kUI$>c:T#2R:U%MR[c>fEq`!cf(C5:._G*"u%q7IJ4KM*2uR.P$.D@O
LZ4oj^fWCQ`tLdfDpW0NKa_2_jJhs$j`FI51M'"O?n=d5<S&2WRgC7g4T0Wh<QR!!$-ebOjD
:Gi,%fcO7gl3!O%Q*6O%hIq%Ug;"d`a&^1f,91_L.U#`i@Q6l>V$;1TG4?#VqK$HqFJ7OqC2
o'uYIgFF0E@T;/&0if&51,G,<&pSg`_as,l5&S8c#jU,XZoud`K%6.ot.>K7[oiCre+jhfAn
6["ba%":g4!k!m[pP-t^7?q>gZ6BBLu[KJ\!X_!M-=K\uQ(F$9Z?^2F6[URr2A4lpWMg:7_)
]AXln5Yf=1B]A0i#K@W9RLKi66rKH.IR8i"9HNFm^i@<?e*W[f12+Wn8gU*_.*WrKQA)kE1b-^
OVB]A0]A,j845nE%KK0D+b4NT;ulD%?`JnlQR>`@:p_Qnn?2[8EXVoNESc[J&#.D;cLm[I;6E7
914eehciV;:nH)6Gbddh_O9L:OaE!Y"NXPr8ibn$A.r`9d?@5skG@_(#7##D(aQ.mu[8]Ah)o
RY;tB#76c(CiBJ=;#ma_,hWPRUXT'-^YqO[)MB6)2`g<0GfFHJ>kIl"GfBmmBa84[kKt1^9L
@HNMAj8h>8Q07/hW7R27WZSl=&<i7dn>1o4'QYkZ3'(At_WhuDI/$IZH.-Ph-(kkFnbHupd/
Y1pPK8\]AW-)*J0%7`L&g17HH[C$Qj6Vu)mE,54dG+-fe@R)1O0S3ckQ]AWrb>SUF@2KWq_UJs
-#\ll#0O+h7/P$>-(1++c9lgF7=?%KW^GLu&<>TsIYaoQ5)Nk+h0*%[,7FAS0rk$ta84qVZW
\&u>fI(GbVNh?$8V;rMkQ#RE\hh<)7Ld-7uP5_?aU$UhTTJaY*U*BIq51GCui9k=H6XX>:JN
">T3TA`8l?-IC$i2LjMFk#G$7+;cu1>m$[4<Ugo_r06b:%7jCEg:gMoFBg0#'j)[4m4;hSpT
DT%_LKFi+OgA.V8+GT%ZqN=j?QNSs+bZ1q[DAr3CAkV?[hHH`<l[;M+B/"c?6I0B^60E%Q*o
;"'_VHYi*tK.q=Rj3OR^+0W7Ri`)JM]A2`t519:1ar;K*s730sah4nru\6*l#5s26"MR8E\ai
PREerK";&2[M^Q2(TE#"_q]AA#"SSqdu2e;iPHgfNB,7^d6%mW<4j@K2D8bYWW/%<<pab:b/&
cI;":4@l,]Ak+p*p(pOd)TkiW\d\P3-Ne0dtSp&Qr@/PZ8;-+o<%&mcR4*ee&gpthoTJWa-qg
B[#W@=Pe6G*1`JROrLm*^kqb5NK'GKtA:R(t!N,eS)`l'g?fgcRk=Xa)_X$2=puShE3TKWfR
$JkXRp-d=Imth^UTV#-Q1URX0:%/^`P<Oscu[<aU?V)u`&Q=e905d^)sZWA\la5CWUl']Af4X
YF>hrPH2RaieO*-o<_)JC\qZparh1AfMGUkfo_Y_,$O#9)@;e%U:Y-T?\EsXe=8hh#O"=[@B
4_I@_=LqZ1R#=ciou(;S]A!0!!``F_HOSM:^j"`Bn3+[&"HiCLeR0l8!XAR!*e_67Wu%`eOJ:
=ZfXgXU=<a)YNBOn$;"ACCpCa4(L(uQHtUsS)hPr\A_?0:pgAuXqs8;H-L\Q":Lq_25(.jOp
u(.4qdtiOolm./U<@W3'3H57m%9h):H=!@19#JFK[t7V3RScK3+X3is5OW^2/%;*PZtal3ne
&U(Z4uVgL(-%rm\@S4'*1k2#ZHb@hm0.omX5rbf.=h1OB=)`3SnFgOX-S]A;qOn0\#(N<h<4H
flHBb3*<sBJYM0$o;8;U;"'JqR&'rTTWC<p=?JM$a_CW'm=Z2YF2/mm5bYVS<dcaFi.<s45T
:o.'TsmXZffQ*c/gNYa6p))E<"AeBARITJm-o-c2Xd1blaCsZaTBL&=*<Rn:1YXJnt_p"c3L
/:4NP45VNQg7;NR$-<)CL@]AOXcTRrUVAVgZnBadQ5[n9*.>%_dTQPKr0*<gR"OLhpPV:1i]A1
Z*W<:D*`'mC^4.pW$bmC[sUN53Fui>j".T+$T0>$tQfl6"1A([@8Z_em&Mt592^1HCk^'#$;
)0(:Xe04neEE$642(0r2k#e7`6aCTX,hE(b6./La"(Aef2DZ`Wl2pMC6lO9H*oSHdpbaA5ds
O(_j+;03iP[eqm$QE/u[52#nU^_biRETZa&Gc^F=fl:1U(MZK>B:\beIIq/"pXH#h8!hX%Po
S\mj"W(`]AGHt/XN`H2T/s47X20rh_]AKni"-05ME*HA6Jk!DUiHO`ff%\c6kcnK33P,1+l-md
'PdmUtG+O[#m1,<l5-:=K`S6NEmK:d*\S;tr16>DmhuQG&6hRNB!QtP,a[lJ3/5U8]AG'&N=g
,%QI@?:^s8Ghd`a?V?OJDdK-%'rlLh@4DN9;YmkRMP8^r73_QN-9!0(`,?YP#=X"iiu8AMl,
sUVCX]A)+^9YkD)Mr+a?6N*lA"D5=57X.COH_1*iB7'Jl/%K]A^p[82jge-M-+.lF,ggamks@p
&uV8^Q?^ViN0:Q1:m,Um9f"-S2#+t[b"T]AT$-YS@bGDpE@_N?'UCmo#ZFjOpbRS:A6'>mc%@
+DSET'+VO(8ih$Y#b/Q3Cr(]A;f?^L`q$>+r/6+Lc;7)N';Y-!";EPPGOZP68=RCZmHrmd!l:
Oc;]A[sg9&t_>VaQ<QMJLh$MBG0=nG5Lju!oO/KsYN6Q,r@.`L+0nNn%7T=%Q?R2[P-GN<mV>
Pl6d!L,e_!aZ5.oKP#Wm/]AI*+UnoVVtVD'(D6oU.ETqLP[UGdF++ue''bHKkP&#^Gm*t1\Sd
VL>-5nJ#>%6J_XCW$J6j7Ll0]A+P3!jhTj:qXCgB>n9h@7fc2&(3n[H"!%8Z]AEn^eBLgP+a"o
E>*GSgu609^&j<=!M2UWPOqM5;if46a:C_ne2Mmkkpr0Bp'@b'.U_6EJJJAMlu@iPH2BTSGH
F,D%YCff$%A9&[E8K:FFe8&4+b*B\66^,D3*ApV;"*<\)`n<YYY.)I&g)*TG^-J!#!@f=Cje
t4QAcHc%K2$CmEYUo9EieICnfn_X6NFb([T.%pL=AdA@:<+Tl-6ejUgQ?"u_[S9J[rVUp<K/
4t:l$K\(*qDkIV\2eVD8CN$X7P'4mk";1$0lOe.JnpCW-9rmth(Zb!a<9hr[*>5VY^_2#NZ>
7+;i<%e$,(.C]AS!=aF=,rW=m<K\',5_t@+MM8b_3bf+^?/USRkRko'(o.[FSk>WB>XjdT^06
(QY/e3EGn=WD>9.,sjr^,HkhRPg'.=c<1V,Z@WV1ELdo9.L<E^m<*f$>#U)K[_WS<_H)cgX.
`e"^:%'?.>Pl"7[pF^)WcM7@IW'.MF5JD,1_,ef'IrN!2mrJmc$K!9#9\5F.G!^mL]A,O/FB2
Ald,dU3?3Rc[h:=3]A:>_-=J]At-$::46XXUUI1Wg9G@)2<%@k'HhS&V@glH/rB$D7<2rNVaL@
:`qDo(Mc$cFqRmP,;c0^pN,6g8Bi,Nd7GV(bhA!o+aA`a=+Bln:DD+g+)'(#uRa%E%ACQjD%
uK:4P5fP0aEY[u:g6l3WHYY=70r8>Y2+2%b+7"cmR:epj*Y+=u*R?l/2ZKd"6*m+XGGkE%j5
$0hK<3Q=2Ps47b(\)+i&G?O8b<PZaQ/CZn`rX:-aD4*h3s$/b--?LRs/Kq?ihbD:amgpoL#:
7Ie/]A\aTNp\sCr*8+i+jMlBo#h@]A$G]A;10-j,>`NhQ;WeYm/;V33N_eVE6Dj"Li\Aur5OJO&
8@8esZgs*VF+B@/(?l8f=Bc8r&o)IB!qq*P&"TCjtJP5KU+GN*k`OjquEuG2/F-]A$3%hp^KT
`X8T;h3pj'#$,hj`1@cI&I$"_GW]AK:1VDiOO>1(ccu/dCduL]ARaBMufEMN<)Q)j$gGS>'E;W
2^D`KE\+#7]Alq.T;t]AfUK0/QtY7o0$YM0f3i3a$M9Y$m48:>FLoX"MtlJ',H<T%!]ABr#'k>7
;Af[V"=.?kXAY6d!5Y>XqHiA2)%]A]ACpAq7]A%jXV2R3-iT1NoLGL,rO*&$-N(!nf7A$=.']A[Y
G>&_npWHZ>BP\_<#jgg,:r?V3d/4`*kBN`o'L_p*4&Ik$Vf2*\/M8M2FHp*`JYk$;6Rh35sO
fC)$b*G#D`3=bimL.]A`*V1WY;YUU$4$N7CRrBUpaom$tih!Ggsn[?kfS6db`trumnKO::QJk
=)EFhcMVpl,`JfF7$gSGX'kdQe:O1$Uaa,=DJ(==nG,+]AmXu!;_FIO&4`YBbeI.Q.<Kmq94r
"T0oC(s-T!\2.HM6TDFf2XE>"c%Ei`;Gea8is)`\']Ab7fko.;),nHPJ8uoM!kJJ+\6^Dlf!E
$s'E$Kf!\'Lh2dc:="iO)I75V%`nA+/_Z:T(H:KQOTAoAOg`F68lZ*T&Xd>T]ABj$/T-NaNTj
6o</m!1.q6/L%%VrY8Kc&mjdaZ*uZ@?,NP+PkHgI7UPAl6P=YkFrV^pipi7'6!bJE\k_^69F
hX9@:X6\7GJPtZCn=mN/TQ%eYONSRndCWg=tqO2:_,HL+&%nQE1pMcmo6g>GW6\#:==h$3>p
-N2-88S\/OdVd`XK'Sg`+gGp=`ZRa,#o#rh]AZWV77*X3g8ao14o(p'rR^f7IkFrWX_hFs3YN
-N4H&AmfI"0AfIsJtH5/`.ku7NtU>*X]A<FV"(Q+XXj?$DVn1HoV^p>-Yf31;gdP9()%UBdV9
jmtnEMp""q?fEmO7asupp\st+^mlD<IN&GsKO>D/[J"#J?>=iGoW?"1;X3A1s8H,>j@2qq7d
Gt`Q`bR_21%;EVfNHnlo;a++Zo0u?.VpP0pP@gJuSa..ZgroZ04XVl._=nDRAn'&ink;rFk#
ZC:tAq/ma:O6:2.`H'M\RW1hbPGiX2YZZAkV_[8CF'N>Zeai@r>!M%FX1]A!'Q]A&PI.Ve]AOP%
MM=/?-o;YU#Ffnp(L$,2+uSacYF,1Wn>iJN`gjm`A/MQKjmIPVM08r8f(.j?ZNe7]AK4>T/h;
M%F;P?,ZCgPH=6AXaF-23r_TkHWa>`;b^qYfITKU[.&I`8BUP^S]AQko/K098QW**<K%75R2=
%;^eg3[l'%f:kGS\<jGKM6ntPCW^bBLI)_s=<J(:JP'PQn8%^r>7&.j-?bl]AN^nc%66mQsO;
6J?8XjFRR$AM8iKKX.^8[O-43-&+K<q:5JtWko2\gsTd8esiPbl?0n!L9JI1RW;".&\(BcgW
D?ALn%;^\0]AT_b`1VM0)VBn^3gr]A!U=$=st8.sFFLDi$jI>M:rr2<eq`6^Aj@)Fll:Ld<JCK
:_Un?,`FM+-fa0,[5Cq'(7C7W4V;Q/IEgO*3(AJgH^&B5tQOb\Jo#IU-#?5F''3^=2EEM?$,
lK,sdm.ZX;<k1;@(V/"rjW:'YK-AbfI5!"Kl/9IP".b%&r$I6T4d0"k6IE^Hfl8\19[$if'H
l@/^V!']A.7U\fd2!D-aEQCe7L$\gn,AH3X#NuPZ)%pRuEEPUXYke\82(3XGnGJ,2%X:,BO8R
ZXg8-.lg6do]AB5DHgUI;<e#BQT2]A=PVE>g)el(Yq7:>+fXIjln\<\%cIe>\RXm,:dL;LU@b9
S'7+3R!M:ZkT3*Z,lkd[]A0C7s`)^cN%39rU>p`EiL!e0jQTF:<>0NPt"i(3s")jZc<+bbb\.
Mk)<HGR:VN+losEC"Y:oJ$AlYbKrJJ;7Qj`*H-aADX)6R$aoBq%S73hZ^$Cd(;`XEMe+"A%,
_DLWV!Ha3=0K<MEItM,kmZ-abn=e*rE++b[m\J9p2lPh9u5EL+FLFpY0^M"Q[6RY6Fp%_*0h
*HW(5[e0Cp:c2g2AgZ\Ld]AP):E\DWS8-:7Fj@@aI[_a4p8$Zb]Ap(Nu:SieM<VDV)dUl)tTcD
=*)>N)sMbhPXmAOeo3QF3PYP`VBURetKke0P,X>=$;\6T3&eFcIH\%#:3IKg[]Amp<AZ0B9^+
%;JS+A6N-sZZW93`Trfh2K!a&2>RN8Oio`]Ad655t18-=,\'5H>rV8=E/H2aBUm5NeRQ1[I6V
A5/c&90\+$,(h,J1?L(?7YROlO1<2U<S*>q[O\TVW=SUa"mKpk7Qq;o*;8Qct?B6?s-1?!(d
9.*^aU.iB_5g7WhKFm#.gNi:i7lmsjg]AZc-YK!7-#%_`T@5Zf5gE6VlpN\hS-jpP_JTd_"Q2
%^u^[*4pI#Z+ZXY0;6a,1H/CR^e?<c2[XLN%)$R$BQIl]AQg^p::VD+L,eTJLE:J[8_LSJ_^l
t'S)=]AX1/&7"8@p%1BcM_o$h2>B(Oi>+@]Anu[f(sUnZG;j[?2?C#9^c.g/)9[KI,r8/bm!0N
*Po*\Jh8kXn;@D._/ueTY@$pVg_GO33dA6?+H3f5WYU5nq]AUh'*$CTC_j56.ApI+822Lr1_2
(X$YreE/:ZHoTs+&U(WS5_AnJ.hN>TE&sUX4j@XKC]Ah:MbOJEkUP-5b<sdf?7U&BJct,!]Akh
q6nJZ^)92>q'RmXkToS^jXC(Lc&h25g09Z9=s!3iecb/b-Y5t))>MV\Gr#n'Zh-`!Td3p/&_
octb3dJq,O[^O,5ldVWA)Xl_C<A4'B*8^;*B7<^j/<cHW8IH;t'JUKdE!i:qQr7??6Q[%oK-
A2T[r%8P9=>?<JL]A>6EOu]AZE0FbE[\g6UISni2h?'bDl4EL)Y"1i@djeV_-O\le9,.#6lHWO
f_R=D0D:7J_27>p_UUs.DqbP>K<<jY"!\Rk'/gcjAMR6SL3uTt4>2%7*8ZiNQ/e/uK?0q:&e
aee[IC65T0o+4I8Y::j1Rji]A^e!giD5Vu3kgu,FF[gh%OIe`khU0F2?VaQNe^_XTq4A6f\gU
aW,l?.m`Uap(YZ[%ZSNST[]ACQf8.f9Kg9&ssKPEH(7WkiGKHK#$7+\r%fp(dHSGTP.q+KaOU
&e(DsGhik*keM7kPD>3AqpX.qXN^!]AcR6Xa=SPX^NEMUDiifsW4uFkV;K2QH.l[!GVZ/7%7o
T$Fb%Ib=n?@<Id%u7SbT25P(Y#f6.m:k.Rq=Bu*mLngTLq]A/g$%Vi]Ag:D$AOS&L]A@InU[=Nr
N-6o=rA;8[IE[MWr;[-%Sp&9=@Zm?:U"F<[!Y$fb$i2^"m;EPa''"Tg(-&,eo^dFK=9-IPU"
eth=?'uEB?cuK<O,W]Ad4XR>%JTYgCj/"maI3^P(+Q.+!;HNh0C'k!W\['&@FbH#EOQopkn%L
_;>)BaeL/sCfbCogB1rWse(t()RWp"-5ZeJS"2kq5HA.b('ajMPDG/cO/r?$MMHQQ]A#f!;O*
jkr4&A4Wq]A0p!&V73?Z!!*XB]AcS(f)dm=Z`NS"ci^(r6q]Ag9+]Aiq\4JjN1F+@AACM/"\>GE.
21L.qZdjHfhP8M`*T'%OssGH`>QHRU8'\@[io?ZTdh1pUu"78bW'=EPf^_k@^.Ej]A=-%b.aE
qB(sBm?1U8VI\C#8S/Tl.6B9"0F[/Wt"M^i;kJZ_B$'1$q@Z_?m-[J&kqa"m^2mk)!p!5=c8
C4b/RL`(iOG>hiM*;A3"ZZG=6Ge";4'W*=Lpaq;n6.]A]AEb""cF;]A$5#t`Q/N[u99b@'MJZCJ
SSPIb3[bT^:B@%Ap47&rDc&J'la3nKRZGI//X3eYIn4h),[Ol34o]AGSa"MTqF9\fHR(ZoO3c
omOL+Rq$VW*I-^pS<h]AB>8Y=2G!<S1bAmAr*ohG;r:JjY[hQ+*iS1.+4T\ctLtL;0N\B=i"\
.ndOB=3<F'3;0*/Joj71$eI6_DWG.Sd>ts&tA7ejSP`]At8^g02-SHD(_nC5c.UT>ga7g]A=B5
19S[;."]A'4%BnKr37Rd9jA6eO8S*[qhOd$kgSCuXG0:M>WhV;K7jMqIMH4H@CDIeujTq4!@<
djVE"0dDrD<Wc3dBFSFXCRBkIL#&'![(i)2'AJOFYq=bND`fri6R/R3o6+O6U-4hQ_V(ke,8
'4N4Ti1N6bU!0SqH!e'i+"]Al"js[Fd^2rM-#e_9LW?H#*o00Ja)^IH,q6X77t_i*EuuN#sBF
S0oC3O$8T!BoXOuMT8b1TGi#G\Wp9Rcd%[fP5^X8Et"G\4@l77O+HL&9sTJ+i&#&q)_LcWBr
/W0d[\t?1l[[8pTKr1b>V=0S2_!!'L%N+5Sh(TN$'+'V-e5X:2!-jHh._3l;cnQklHnT1S.J
EX9f@Y6a^)WF?lk/.430781`6@Okbk*KPmE,Q[NS5cb0N.@96G$8M!HFCrBPrA"TaB:8?Pu#
4f/hb.TW0^%6+m2$de51]A))LSVj3OJ)!HcD,=,`*G_.1V_dF!&Xn43]Ap6%s/)DlRb.`-fR/T
=R<Bj/&8R6UMok1O\"L1A>cml?Yc%M:5j`l8Af]A,77?79aha(S!gNUZ"_<b/D6Y$tp5h%;#?
X%;7`5f0>!eEjQ&<gDuagfP-5Qa=YGE!V[h,_HMR@27ju/*N19VtA@7+#j#9mbY#nCV*!@3&
ImoBILCM]A_f,DfdUuISI)GNo._f/1R=_?L812A;@6@O4=k*7$AOBi'^_;6Nu=QPUVG0XfBO9
TP5@R\Vhn!i/DYN+\q]A&2;CgZN"<\Vfa\.C84UmWU#fO&Z\Ok`f*"k*A-.VJE0Hp*Zj8DpiU
)Y#ib4#M?"`KksW<A%u80n[nh_*8SNJ>Z5cNUE"c/9*9]A?sU.24LX>Tj3*jNda^g8V\MX4R9
nU#R)MS"FtuifGt*MJnS9WX@8A\S05rI[.HO:0G8VkCt!apqMh(C(V/oQ82+^\:F(aX-K&k-
g[eU%WYXsDXEQH8B4]A")7G!+8^2A@"/_8RB&&X&Q^kKrEade_,`No2mE=t?"Uk`o#h\re5p(
Ejoh(r4<:Xn4Lh07KW5@Al:"!JeMEJK;uq]A'8Fo07,QDDFkb%@iS3.=lori)h$fV]A_(tIY;'
3!`i_o&C[JRV,=<JFloA:^Kp;cgn7$[LC-&N3;':IN*1S1l"7G[6;C<-gRM-HKu;S8B'?7>/
MqceS-3C:l4?N+cI'W++h1f,6=6ub>lF;J\eC8oY)C"9%[Y"S_M8ac_<HL-9Bp)`33S(3m%L
p2>J/dMD-i[D3'T\T>n2c]Ag^!`CpO=m/lHpri]AXEsmA2`KU`_u":p>2HT.">[@BJ=gki4lMV
/E)H/LbS&Dq@B$"?DplcWJEm+hVYZq2K>#V]A[]AT@..L")*Re;]AVO(,7^CS@eb,%^#%q25g.m
UNQM0GUmo1u=DFu2TrL@#gV&j/k'@DXmn]A7j!i-P?HYXu)h9,'p<s=V?UpQjk?QH!Y8Mm55Q
IEfegXfQVjflqV6s>s8I2jEK345:h,hi]A&k%4*)<9O6d.1?)3)\TC%G.hE]AOnVfE6Z"@M>Ue
q_Y/$i6s5N4a.YH+WlU-Tb+Y[J$H7()A6<B48p:"^F*=q`HBj5`;H8fQB8dGo/9@1V#K;T]Ar
74"Z3m(\k?nPFtUho\hh#%/ui'G%_mT9ALAUD,J-[L9@DHK&%r^>8/Z0IDVY`a]A^Eu,hfZA.
ojim:etgIZ1OF@CNs$i55Rh<3r)lgP(qAh!Xe^8`&3[YRVj5qG-Hd->g&JOrp]As[O3Sh\A6K
aGF\iDih3#)aNS?Gq:;tI=Afb-bkbA)/SE'p'7I.^;,%4ddTBpmGd3Ot82rU:*MNj-;V]ArrP
REha+>$\S(q\r@/';S\UN/naZ"r`j)`bodJ'9T]A[U#6hnj(m%KL126hh^QY@EZ]Ac\;a_$B58
#'8jE.QO#%8L/Gn)?#KoRbMc8#!nQ'!d]A[OpZG+HaJ&,MLhHGioHpC@%[G/]A75NH&^rC/,qC
qmRCf2NBkO_66&k'!D$+L*Fc9XZrZk$Ni=rL."qZmeQ!.ZM?*j[QK*.007n6C-UKS'k7X.Zh
Po0u6h7.GJ(IgSBaI`ND#kWBO#P"(t]A2E77s5mL4`lcf;QQ'.WOBcU8(#fl_#"g1jiPUrWE`
"3gAh3e$aFtk"cY'G#0S)1X2;>9jC3cG('Kl'JOnq:M!C8BpUmu3g1@Ui[)H(-=Cj`VD9FNG
NlN2LOP>s/A=hq37Z`?/9^oeK;>%T@/`0?us/ii<i)n9WoB0KQ9B>m:BGnt,'Aa(jEr2!XDe
aCM3[7HX4LC]A*\$:-0!-60&(a$.po3cI\XXL[s??'.fi%4mje;5@Pr/Y]A'8dEg<g[7F&+\jN
@1SlJ*B\7.HCX+ofl<NbbrDAfsuFlV-td`@]AtCnJ,P,:)J^pJqMjQlrYDdYFh>s(HeG>$=n$
p`ObMG@W\ij]Al)haAYRKM8AR86D[lLkR&X%7C8r)_s5WA]Aske$9Z+XqKOImb)-.AR\(P15jK
mLmH"s\n-E<@V(@d%u*r78D@an@Zcum@+r:=3*6tO^bP8-acN<`K,ZbY7uD/rQ39=WWB>2;>
HqNnhn+h&/u*>\gWn*/L=i":"6pG,P"Yiop84%ce3g`+ED6ZIg*gm%-NW;>Phao.e]A8a!(J?
g#%\OMoe5(1p,cX'.hY!"Ma[_uHpUCR1=AnrcG#9,U5KYGH@bWO:?01uisF%SL4*XkSWom9b
;!,;4M:P?eGVm0PgYkCPatB5[)&_0aLi9?3M\PS@GSPeT6h$9b)*)=U0V,s$&G4#[F>6=HKF
9r!<oj8-``"TQ"&Y>s["]AgY@kCZoCFNYo!/fuO!HC$cHAaTX,pXpQRF1TQB[;0U<PqCg"GV"
nbT;,O7[^n9leM.bOhkgre>b]A`t5$g"U9?FgV(S^IU"?gjTP_GKsg[<aN%BX?d&qdjF/>AGB
KE@MIZWFXPG@N6Q,%C*eR6J,@+F`V1u)E6,G[tU_0#Ok/7kBdu[#[3$L#jKQHF^;W]Am/U?NY
'PkIEdT/93E1gDL!<tT/`Br;1lN]A);$XL'N:^dCR,)8L0#)Yd^JTBM<"$D[3:8e-a(R*2##5
p1kR.c[3+kA]AQYdIu9>fsbd_?#o(gFO/%ON=3N7f]AsX@5I\lDi:!`c%C._XfXOZ0G^tjm*<g
%\S`"oT4cnphbA;6fO8*P@cWaV$7eAC.FN+cWfA/1eJ-*)X'`G;+c0=O3$;@N.ul#!?pXbF*
pP4n?Hdt-_!BJ7J038/N%LL/dRP%`q=o<=K\#gP(7Arl)QfpMSk.4['j'q3m4:TZ58ZOqRk4
1*^_nX'C`4fbSjK6[Pe`YKkriNG"]AJWIq>:6\IQ:4[&aN(K^Fj"`5nrY5f2D7%eObV:.en]A1
mI+D3#$C=diAd.c#s)'$A.hp#a/CeW1cQ(ZT)akiOh\ml9@J)*>E32<eG@@Lbr;SO98/8`%0
cW'/Sc>k7VW'Vs!?Z'4E-mJ@O9;GLen7QYk^s\H&(XQKkbdKL.DXRn*cRX21mg@8\+*A-Ul/
8m*^\Fko5(O[@;no_dp-)\koZF"G6&5FdcW(D710X<n]A(BOZ^<g6et:+k?*64Eg="A3oI!WD
5E.9HB-i/+!M=mZ)BOj`j@D>[bNHcO7[t.srIDWn5*;L"((:m5Y?'=N?oUdTW`op+LN6EHp=
<`!Y+A['So\ZnOZAU@k"jLAmt+o<)74RfAW3`ea<u!*=(XC/UbW(Bnr+NR:0Kff6l[(EC@Q!
GOurY<sQ$%6E=h[T?*;gpC13C#340gd,57*n8rKZ,]ApiV-LL53hrr+*-UA.E?e@&9X+s-5g?
VcCtelGT3FBHlR(VG7,T<S=2##Id2C":FUC9CoJ8AsN[sm77h\7#ZkF>A*1j,\mIBOEeLK^<
g(/-:o9)#?bS0'FqFYQlTcD#a6l-SRnumJc!8k\\gdl]A`&^!I@]AKuT=Kj>uEdM2(]AT.^`:+?
^K59O<<a0SDs-d98umHCHA><+@X.=,\DBYT(7_i$Vs>"Xf5(<i5H%g)&h5S=Pkd\WN0X/2e8
-STKrI.6]A>OF1p);rLSZr!7.6+.nbNOQ+hYe>U#f+Q:"Zl2L!JWnBHu4i!_fK*)759'6QUiQ
#%LB;N@3;<!YN=\7Rf@\m/pPkcVJ&I,H7[aoa#U,7J@L;(5*0"d\g6.5:OpI--JQj6'J9WW`
1U#lJjdm7?c<d^]A#4IfhCi,U6kOg<*KA/LYs@OGhVqQ+h><:DL_1</"1oEAlM<kK65k`jRN>
3`X0V'2@CZ3$-QU?@@cgJ9/H^*:!4/;gX>s$9ibbY855'Y<oFji^uc1PJk]APm5m25/S&86f[
f&e3=$O5]ATq79pYbgs0,aMsS"H[XX6O?4Zgp4i*CD5R/J@=sA>/sS[%-s:pjfXS<`l2ZF@*n
Z!AV#X_:>$O'#6s*^Zdo,OP@_=8FPYSOFXKm3IeO43p]A=C6d1>gU#S7L:M0m-<NT]A@o72$G@
-'PX;3k_S>cIlj-+IV(rr%SoO3\,RQIprAb=Z=2HgkC4Ae?:;^u`<CJPJDkD2aB5U%0r=fW]A
!$(c]AM!IB13?IreIsk@eP*m0TntO,2-`+rdEFd)_,?-t&6i_hHSH<%6:+:Dj,Mn9'mg7cH_J
Oi@QcPQ\'DF)2#hknI,XnKEt`X4ta$O/Y`'hhn:&%QM_G(koTk&o;+3PC9De^Quo&Q.0"MJn
\\f[%Br[>]A"jj@ji.j2-D&!_OY\Z!r$D31/#AL;>2_R&X:,f$f!M61I)QV"[Wr;NbsAGREpP
piu-OgqSKa8T8a#8Dqc4MJf+F'(?;mB^l9e3]AHmp6[8NQGBtBeXHr;KW^,j"u_KUBfrB<_j`
7s9\Mo.t7g>&&1`I[U\8G)8(V;3Zd.IN)c5+:c^o';9oJTP0Z%*nVu`XCe?@[pH`]A5u&bIDE
FeVk5,N)f.aK]ADs\H?n2YOB5G@(7O::W\a;X%/FDOQVeEHa9cp2P/'.N0s7:lj$E`:lVl;PC
X]Ai:kn(30jj:0nO)=9W2`Jk:2r0>bjA@7R7'D#"&=g^*HROp=IH<Vb)"&i:O6!CkV[d8Lm_.
8BNf'5`$OQp&cl:;<E%PDE6i8h$W'em*NTIeH3#\d!S#'a;4]A`X@rT9b,G4:TRglAM(/dtPf
]Ad0ZJ7hWU8-f#s;FVXOFFqVP<ANR[^bd+F9u9:.7WJUN9S;MZ,.kCsJlml9<mnZpnp2,A;1D
XddZ\)8c3H\4r<2>%67s66X%Fu(Z\Mi0I90WnjO3UOmaMLYL*s7tTPjMW066ZRQaBGl<R^60
S!=<F]AiJ$sC211:o$;.A681bN5`9qF:po9"_O993.Y%XMbNY%tdtgs>jj&hL\Fmbda,J3M#=
OO]AP/!#UoqJA1#En@*^!4nL;DcJY(]A1SltM5C:+;LJOt!>Lm]A3!$),5R(Z!^+8(3kXr?lf\\
:4TGcoB'RBa\R[sk>(+EaLZb-KM2\<S2.]ApqLp@GGf(c/A)^H#MI=Fd6@327E(J3mh[P1l4I
SU=Vo^1sIZp/@cb0fU^lZ?oU]AG@'^85?Kbaaf'Km;f8o)*hH+Z;W$&d'!$F`L!PM&$'<3N!_
W)A6!$i-%p$j/XG*[-sdBYmG/c76tG$*bk-:BW91`(f\^&#:i.;'bUk*n6G2DV)f,8(e2.`&
ZGBJp/W@Mot7\7"#A0)8E_ZdrsO;0V:9%$-['ZiAQaoo5s#k2lZ(GCA#D.Rqp/o#C#]A6PTk-
?F4daV:%m3RIfY!7Y\*P3o[7$]ATac:?5IY-=mCE;!lOuJD1AIMoBG;1$.#(e[._%Uh>67*`5
J=\1"DDE<qd>JPt)+G^/>3(Mc6K6"!=2f2n76?qH.)gk1.7Q,8ft_Er\3S><?Zlk1_Dm>iSs
+j16[aX2WSo8-[92?+Vm;DTT?sWkBO9k+4IH/\(\DD/R)/fP8`=0J286Jc\iK:lW^f:UoKG4
gjg3"6p"WH6;=1Kmg&u#JS+Q1`Y8cd%%Jn'lCM'?d&.5VUt4M`W`5q-tG'GMMCGgRr0f"h7G
^,r-j:u6-[B`*4&'YWAn=MM_CV?4(qIT$`_ArB!a4,>#<$#,ba>hCl)8.dDoJ<a"qehrF+!r
[`:$^I3Q;pVg\Ol3b.kE]A+&q>K\(s'"213aM6m^3(E53[7*GYO[='h2#I+um2bBc#2-S<a3.
lkG]AY!hKQAtHG4;H...LWrZ:r'1BQlrYU2LFn#p+Hi)I<FUp?+b4,J!0"_#Kqm84#Ol2Uso0
gAr]AY1M`$tFkJ%$FgmDSg]A<Z=#M!H^!>L&_Oj5fQ4cWGW8kZAs&)i?TFL\.JINSC0e3-rgqU
GAX7&6@J+ib+2<rVXeFq"uX2O\dM1nZPP=[(Ghmi(;&K6Wq]Ad9;A(X5B2@Rg<o^c`A?8__dE
+<oN5=Dh!Sa!auFiH3Gp&<)j`N1J1NuGhSuCV<sl5]A34-M]Amqi2Y0W)6ParX7P,2Y;N*-/m?
[88gQD"b9]AE118i:-DXjo$\rL^p`u;;$V/9<0d@QY>9UD<0"nAPMCoK7e[U8=rf]A=1?Y57[t
-6mV@TmUW'cPJRq4/*U_1(Mm)2;`Og9P6o3TJh10f2]AOnQB>iEZh_"tU4!?X6j-KSB2<Jl.J
F]A#+ZR`5jQb2.u/<7`9Fbl<QU_aU1#rXV2uZ\9quuaFEA50K\;Yp(ftPg;s4Nbtupnm?Ks&I
t]AX1Y"T)Y5e>\TQ3^jVb)/6J\;B[Jl,5t\CGL4BKY&bi$WHKa-rX,eCj/o3MZ,:O$IDm/FJ>
=7jZSEKca"197&mR4dM('Ni'6$s4X^h8"P?7Jd$%HW>'`1RPA0:C-?K$hH-bNac4W]A^"4,#o
$on0dROK.k+O,:bCDq0hadpVe'Tdf;q'>4f/XF.8gY@6B5n-'p/s9W7Ld$'A8r`f\YLB)Y*B
h(9p&dV5&e4:J>2@W7!FW:.m,8rck8L_mZG:hCmC#TF'2<rtqN+4a$b*n\kgpD528J#jB5t<
]A\X2f*puNrH>gIb5QrVEZ``_2*VqREiB]A75\G`i`<>#a';=da@1+LiXCo@r:7H,68Js(;8k`
rqLFCeo6pOB'RSfc!UZ]AQ5<S5rs-0?nT$^bYp3]AI84&_mHX=XlbC`Ve)dHU*VSf\TgQ5Ye'W
.q*Ri0*,]ApO1!Z!L;FmXpW$q$Y*U27`JR@`8JUUO_0_a3p`8kf.Bqqpjb%fA?]AKfd<K`a6Qs
d+9KhUkXpL^C>!dl'CocDql9.8p"Ie0n]An:nShOPB_t,eWuHLkOPe]Ap;pZmPGYYNk7aE"+Na
b#_".02`)"8Q^),A"Tr"9n6mn22<4d/$^4<,?LoF"N6ac"6s/s9bsSY2mT`A*qAJ`F$_n>6c
nm,jZ#>[8Z0HAT(^S`;5_pr<Df`oOR^@Vmdi]AE!u.'WV4)`?tllWCW^8!NBcl6TSMK'SZ[[I
9T``.GSeLB0$<ZqF\5sp3'/KEgd<8+#XI^7Vj3P3L#rC%[KGKO\db>c58iU*+7T'aH4M0&q<
Zra"b3^k&E?H3-t?]AKY70kF>AZg1"^fhZPS#V$2>2h1bg./30`=D$@Cc<Gn#98p"jMA(Mh('
97<)*4m.+oE=s)oE)Bo?0bo;/]A0lf:Td$Q&=9R-0R8DdfojX,.-+.nmiOC!;oG]A#UnahZE>d
+5]A]Abnj5]A=a_W@:7rtoF"hLqF&c4('WI@h.%WlVFF5V6NoMinMNgoHEo"0e+]A-(]Aruj>L:3)
">hB^FrrlqtDCZ+jRN6!4[9D%D#CIuR0Vt$G%2Vp;H6V;K*"#+@]AOC.CK+J4rX4QR3h;Gc<g
%e%cKEWk^$C)CJ8\<F<4LYDK9`B@:7M<!CC@EQMDm#=EA">-ZJ7BQ0%1uWb,mCe-##Am=ks;
>_rf4jn#g#0R#&ka9^C$A@3Ek>Z\klWD9j;LG59XS5\VCN6UO,%QbO#cPRuFAGMMsW>n0/G7
]A4BQpjuXfj4rg0CZQ5%ip$ApAo"?.&URtAWHA@`^P.-fY0Tk/-i_$uQdU=U?]A-Q`ZXfAkl#J
O\!@_oVW*4#7aMkhd1blX8Z!WW-[K9R>%c,@W?)/3CX$R9ul;/8L]Adr"\AR^i+Q%e!]AbL&mV
1p4b*(iU>1f27;3jI4``-&cB]A?>K8Q"9$\>d>S>&g271K-hf#)k5\`t`ar/efRFGA87*8>M&
j.oK@or'5)sLZW8^L3+[liGn:RSMa]AgN(Ql$*u09Jrmr0THo;Zg\Ph!k`(u>71KL<W>gbjW]A
PKf[!([T!h.;^iZl^CFF7u_f#nsB(f8kKGSB7$rj%\LIab@-plTLO2(q4^ICZY@gqs._Y1LD
CD*#j.Bci(d(g/W(PF>M9Bgj.nTm!-niq0]A$IuU\_=((*cXVZ^,USes=fK"r,J4u&YV?.l2X
"QaKQXd;SOfih'YmnW5.75cO0jLp%-9/d6Ni<"B^-2_EAqj'*QVM]AIJ0R]AG9=F51d'Bj=j'V
!8LnKhAA=h^.H!=Em.&teW&ikl(et=kFedm[>@XTfKd?G0iiLC[Mf)P_cDN3r0XG,DEt5u%c
HdhMYV5?MdnSY17Qp4YPtA.G`bn*[Uf-XbR;)Ej_?5'+AkSP_:\[L/b$n7S!c%Rl`"ejm<Cg
B3UEmse@s>Ul\XAY*C[$.h!megrCIT;e/AeZCn_^^$Yp@gbgI(`j5ukk=9]A5X>g&kW1LaG()
._Q%Z_Lu:mlD7o&.]Auh'?iH)D=2ZYX#cHp/Se'e>Gi0<k->^0Oo?Jlga'ceq\/#RW!(-Lr#u
!WOZf$RgW!B@)OQP!BG&DbO?$l^a-"XZtFNY?N2TAZKZG4TCW0[DJI/^:5$O$kF!(gU6Q/^*
*nKT<,p2MD4._7Le(R*g;0:5a'(;+>;[kY$J_)_-sPP_X(c.]Aa2T:N"QrKL=Plb5qQ'3iHEo
uRPj:^(0oIiP[Ji+$seC&n`okUHdj@8rQns'c(G#jL8e&Sa2Z;m6PMRRiR.#RcADI8.5@g@A
?OluYu<5OJ*WkI8_f?4*7)3Wt1\!n=%+Rl+u(4_Dr0S(S&3id`fh:klc1=G0hh)-4+^X;7<D
Zo@@XCEDMhP0f+G=E-?0pmiO?A=G:RfHi-Cr9\RFeEY&N:Nde1_PsrU6058!Q@PGSQPg^Kj4
[c=Yc+A3Rk=AN(NfSB:uOI]As/^Lob;`AG[EDG#e6d[lroWX2mc<2T(O48(5A+NKr8Z27]AG^.
lil&Y0^tE-"pcWT%)(em2&Is<=Tfl*)DWu:#)!B+>26<H4MhLmK4P5CrcE'guXtIl-0u(m&S
n.<&Q#jf7j%f%aD/Q%?dNs2l02feGalLHEX+G,u1?Xq6ZD%;G_>l"`-PmcqRP:LXYNNcn7A>
Q"A[H.EU65!mUWm6o)Ln55(sqZsXq1Gs3!`V]AE<^@ll'H'`!C;>GpDRcJE3UTPjZ4CM"X+2_
)H\ianV!Y2OO$K/APFO/*W&%)Yj1;K:gfi<Q:pSCC>\TR&qpI_>@;!':+fp)2+pt^>fpQ1ab
%b1>ebI#Xbm?*3(,JJe8Af-i9>BZa`Ja/%MD-gG2Vn1Ai;cN<5]A.\O8!(p)3?'D"o/0I$AEF
&r^,[`MT:=4^9`KMIekf_o'dt5np!2!#q)?`W`ubkidA4E'dh#r/3riM;h/AWhDlf>cD0jac
U,97?k)bD(2.qFJlg2Y6U)jN-IoG/N.2;Q)?U?u%Fu+WU4KKa`74l&PEgoC.Oc:%LSq?1kC7
AU`!QAKgHUR8%9G(gVahP'N]A/P[5u3h2F`]A(7cbV61f<8;\5q"4)!+8jb0Gr\Y8Ka;l)KK]AI
aH]A;lcp))-<![phP^/(EEJ;#ZL%YkV*EGK?hBXjo>l"*2juZfA^[9e(qQ8e=8NP//42M3:L,
"pbMS*%0!*?\.doS\J25UuO?#"gWl#Wu)BsUh.>I%cQ^b4CIOe=KEiq.*g]Aje&7rY"+&efPF
R-RG<7,CE9b3;tf3SC7S@mhb1U%85bD+4o7FZG[Vfp87peC5Y-HE!DS\;DMi,)K%<N<D^;#J
TS0.d?6Tq5,VS7hl@l8.Uf:Hd+NPD@Fq%GTbd*(I!GrI\2%4qm<>1iB%<nG"ja2XF?mDa'I5
TZ_2fh7_e'BEUXi*m5!rK*N1kD%c\R%qOPV#jNE"]AX\\'7rQ:YZ$0cBijf2tEqV@eQLp4bTH
2dDr#jg@+a!B,F?H7/:-l\SX.\1)Um,@'EbSmlu!'F*@R70NhV)S*UR6L!$$@)<a>$C<W8g?
W"m,Rcc/RNWBn\4HY6QfU4SH.8SF3m6OFe\)miMTm739m&[Ug_$]A-AeIm//&%BJQHrL[^m%@
QJm0nb=[;VVM,XI"N.F@8.]A=@Z':pR2ChfY.jn:=>lc"*1,k<s\7JGKo4TZ6#>6D:_YeAIEh
T:OU3<YRC?jd\iE*8?A9V9#_hltA1SnO`@-en#pgeYLWgR!8*QZHFsGY0PEoVe!iQlo-``nQ
iKSW%6"#2m,!`95Gr<#4)(j=q?WkIUd#)LCKE%P)QD_LA"pdH9oMcK"_Q^D(dEHt*%$1BJC\
@:C)R$XSn(`9V[<JUt=,s7Gos[JW2,[,XY!Au,>g\843U\G_gQGj3eUW\&]ANPJ:!LGgf*gK:
=ln$WmHSb:2H'nrH71<p3^hVm['d"W=)!(M6lhh755]AMJ9UDBs$SPRianPSJ#a2Rt*N,,5HY
f_/0G?aS_*gj)dRLFIU&[;/GYVMqL6;cLI0]AiR8b5&rrXb6bW0@m0FJM>R;GBnZf$oLfSKZF
B^iFRPmQ*^*2G"X*<Ac&nCQ\eL!LUkZ[7OepiN7H.faQf^!Gn3@_c':h_eR*2^4hn%2'(?l!
X/HUFE-4blrWpCGGM[2`6.jr!;k#qo%d=Ef.bp+GSpEUn,/X]Ae/,_D2mO"gQ?47YN!,ML?BI
M%pn1QF?-0-W=Id*4<$nS%IIbi=$Wt9V]A.XeR/fA"32,&j5=k'@!#Gak=,FKrQQ]A+r[FS0e/
/.7\r?.n?V_m@?b`Ii(gW.bIXF7:0oFg71(Lr_h=>RT"VW0Xs!9PcmpR/'`,R*]AkIZI%?gX#
t7>ZZJqk%76]Ao,+[flr8!8XQhs`ilp/#fEduhA@HdX5Y]A>U#$c3#Tkrd[>5Q#/FDK2/LlYNH
<VL#Bp6/L=[Y)s0B>dma<mHIXmr&6dWJ+tF2'YD:JL3M^3(bMY\P"a)^U`X^s2ONd/n63Pb^
-"`36!lhVu$s9X\7!;'.ntqEo#;H!S[Z.4i&SA\m76'$a3p,dWhj\<QlWs+bQr5TiTg1!Q$+
!M(s1Cj3OG`RT:'=]Ag,n?QdE\8D!acOVp+7DWq:L7`3T\_8e`.Q9&Td*TPO9hV>LCR7]A4mRV
\f197>c>T/Z0e><>BQ;.eQA2)jDkI9'UE;5TLVEY>0n(/b)&M4]A/J>>5&Ca0$pZ4-PF+nlia
,S9"U0ihK<(S*qdtMm:P9eZOecc"_BWfHaehg$BBPn4hb"Srn$g=2,RY3W:Q^Ud8>Wh,=s[U
+(<MeCC[:q4C'J9nW68a+`ehI!11TAG_guGGur=X+D+:$2&%;Aid\:]A<Qi;C&PGrXT/iZ]ABY
UHBRlMoA2MfT20Fbf6T3J]A^(hP4G$LGG8DraGHRkL^l/I>cr7me]AFH;OpA-%b_$YE`rH'@sn
CTcT8[9CIqgD_e=5:>pj[LC3JT%Ojlrrr8T"Gr-[VOpGTRBBX3@K,SnCE;5>HN<%Y@Zr/VR_
@S*FO7.TMB'sJP_"koWls7H6F2@E/2B(L[,.L^\l*sD+VtK*V-W]AqU.*"X!l^1_X'W@u?FG&
b&(_L2"V%4_4>oW?r+el8R3>1gkI+TDi7q&9RC]A8-=m[lrELlnH0p1&X==&m9p#4qkiEM:_V
GTQ2r?.gj;Nh(Z;S1g2pD$r[XiS7>+"l)84!TYp7IfQJ#$CNKY;kb5Vn>F;-l.B;F[8X?*_/
slFQ1T4iN#2Y#3a"(F(Z+@-ie;KhVNsDVm[.lTEGZ_mA0Wt[5Sh`F@b0&VJVI3>o$n7??o3F
GF8[FQ`_=Z<Q\XEFB?J!hthDDl.!;CDQcPHY1I=;Z6FM:SgmkiV_8%q>^Fnm;[K$+cSg9%6m
YVl<K;5omCFEXU0;L0[Btn>nj<s_j#&_`E-$Y18up*Z@D?SI+BEN>?63u4o.hVf+c9MR:=BK
#KShtsT1&j%Gsq-sPUVh]AN0f5bU84QFW6ooT(Ri;jPiFY(^;@^`dPCiK-</@$lT1/KmXmn+]A
3oPW3FH#We0AK;=NW#fR2lF6^-(Y'WG=g2Oj0Y9HQpS!8:gl@.S$7r`\@]ATmeYHR'(L.;ogP
-8G&qcI.Q?'pbHcJ!+!$&1_K5XEBR<H,&`<=XTJ9j;)\]As]A^@)CKWV!5nFQlc5IpU)/-nMBg
ff%d&ouhgV6sPj^!bmh\M\_/SUFj_Hd,JGJf"aF\WJ:3HL'*8PL4FL7apnI+/'<2"/k<$K[7
pBQ6%`;j<GJu>O<j)>rDspW"j;L).3'dM7c"?l<FGoG&jU$$X\]AEpWJ,Hc23tVYgiit;\@l@
EiTYq&B=F<"9+/0LGS+fP)ZNW[Q<]AXN9lo#.kms[i<*LFjW72(1"':pDTD2d._!>@P@K(#5d
5Sh3ZF6KEAZiYtn^r%G=;FNPCZ'":0pHGi"sDfUoY,!uG4q/<`0Zu']A2`R"b$=F<#"p$3Y,p
h'3n*Kf[p6%89NdUgU31>qICK6k^5A:n+ee9dGnTOaTndZAHi[EC%G(dASCoX*SiehXNt$..
NcET,Z"ZP5QXa@Q0/r^5JYm6pV7G`2G1kk;?!LQtfR'YreY.46mP.^1#c*o<dVL>pX-Up@%h
m\&IqW,.C[-ZAV<_*-T!sG[L"!VO/-P$F&Z"SAO%E[$EYV4"I_$HX7%'o@B^tV*S&6<UpcVU
`j*L*&!^=U?BVpU/1NP)+WFY'#_7BTJl/uurRr\*8UmKS&HE)HK<HA&AIgB;"B%L7\?MBk1n
SA'C3CG#S>%/oY6e':j]A$nFgip8cLY3eZX>_o#2Z@Y`RVe''\.0,e3[S]AS19h991$^)o\UGW
9!'.$sN?J[=bLf\+Jj(m.Yh'UBD?'`t\D)rs&bFT&XF[qrH(=m.h97,S,?DKagKtQ>KK+G]AL
66C'FERg%?b_\/Z7QN75`!nQKL9Nrl8;L8E^>'X#n*:f`'?$S5<r<NO#2(I=P(=.[)lIU04O
Oi)bk!E$h!_nd%0IKa7dhWSFdS@_:T&Ud+5qRT:%G&sm[G<Qk25@c$P"L&$#tGb;*#B6/.^#
Y?K.B_k[7L)WpCWERcp74f8nu'hWLkqi"/!"[5,$;]A1ql[@guRJ<J,'a`Y1&reL?C5_iF@7;
Fa951.I@m[s,H_JiAhe,+#/A^)T8,IpOp$(?>lU@2'?!N<4==gVTE0a:PTMp1ICJbi_@2b&j
CO:@'X8@!R,6dOK$=<.!HKn'[mUh:0,&\J="?T[R-ecqe>d4]ABgPUM+u)A9K,sMF$*Rg,I;R
aUHbPi=(YK8F"`?&eZOEWVq16%X!\"7A`2s,9tWZAL[;`Pi,ZY61i^TZV.KA\OOJ.)s`7"g\
;rP^#84[EA]ACmCMrCAm.k_nYKAULZq_G1/%u$;5T\7f*8]Ap`FEYflUuD7C#u>hPQUY/E[6Y,
']Age5NH-"QD;X=4]AplG@!qMm>W]A&-`'j'LIkQ2Gf*;:0g;"F_a>m4Q<X4]A86j(_n[X-;G!e,
c&mn4t=kiArY=Be3EtgYjS]A9h5S;ZFn)*8(NM\uUHU5]A17dY@Z'U:Hb7a^Pb,%"mpBjMcTC7
3=4#1^M;3JhM9Zt(:\:!#t]AFFgT"jG-,j')SM@m6cc)XU=d_)dNo[)du;kFno,rI6;5XTN>r
a!oMo:6Z?HF#^t5acQ8U/&lXO]A]AP/'1FAJ5$TrY+HuMRu5?*`;!pAa-p3S6q-9'!9?lH;P9u
+,jgDine:aXcWU-_L[TkcqE6?"2SK-EGue]A*9FE1BiU?n09J1upK1,L*2l'Qmd.pH8Ws't/E
Hpab69&+5j"G!6'H(H9+/9TEV<'f(*:]At0:K[t=J#B#YTD.8uQ+a&tt5h,VG@O$(;5["Z$E8
GLVSEhI='AF.4lr.]A[ZPK:V-A!18a;,N:S("cKU-=Ak0kY'OEd2K[4dN+//r`&sH+d?nKDZh
0K/P>OaYu9KBI^e[MOGcsU/LbJp0e^-5>U@QaD.a7Cci*-7OF0j3,P'oUfsAeOGX:$J&)Rgj
kL`@mX")-sc(s2Ti+?D;J@1g.-O]Ah5G:S!s9g1[LbE"B<5L4kY#77hH-$#\0JVN]AWPK?@4E7
)5OHn/TWljskUm,_E?gZPj.Zkk`;:D`Jf.[`!IJTHqs0:@(2SI")J]AVt0-ji-"^H0s(lUKQ&
ml9<DoG"':`hWU'>"s,"#hn,A9/o&spYO/fi(6=a-c,s"4(-Kn:I7`'Z^Rr()Y8'gj+il#%S
++FeS+[+_9^5gS]Allc8_rn@E#G7Da$oEuE7`c?^om*lr52kiaF/6Q.<RTX'-BaR0;M$[Ik*C
2H*a.NQ$?N_'*5'D<S)5'0Tsd."HZJj444\dVh;TkJ:!4c-?/7R'A^:^0S:@&o]A#@b-m9)u!
]AJ&J/r>o3Nnc--tCT]AD`#._Q,n)!G=LfZfplWXfh`>iEh*!7#"A.k\9YKWnW&VZZ1?E!_hbT
)\3BEhWThn4*r2pIDJjXO8/5aeu=PP@t?1[d]A9cc-4B/#i&l/,\p&aPT`9-B<RK#5_82!]A+@
[r=;Eg:9i/Wbc$r0hef.D^#"(jm,UD%F[^IE+9sC)b@8#(3s%mF@?]AiOP?eshq"5`,n\`gJq
l5u.d9MR(=Ho;Fp%RScP"Rpu)f]A\>O'_Wc+*nG=2k*sRK@oq3i!lGZF\D-?I>3Ii>"u]ALGRg
F=1<.jo!B_6Uq/2,)NNi<GJdn,jj729$YH2bCmC<0f3\rZ=84Tl$Zl`TLlF2pAm\3`#VFfL]A
G?*"\_%T^XhPTY9gNMFml,tce]AX=Z>rQ>s(CgUgin,o,OpD7PK%RkX=&-A$#&K:Q0dq3JB^F
1[qj6@L<3WoK+<?.&GS$lS(a`ETIM0.k-r_h7,W2\c_'"2LngVPI9)#ra+^e$erGg[!1$6Do
F16TA&f?GTIfFk,XL4_R6P-h!YU4EIZ9B?D:b1e2DS*70').%7KFH?6qosfeIKjXNM^F\8)H
tlE=a2G!5l_h,4&-pM>'bOVNY'g`f>04r?:[]ACRSh/%3rd=F]AE##"knakQS^t\G`gb5rF]AIL
FgNmC+s:sYBBKqp)YP/(9M*g==_:_8tPg<qU.)Gi9IO=_JKP^>8t=Q<P(Ar:o5N^[u\jYF+s
._7G&NJ/FPAt1`(A2[\r-"Q%5>MoH-^P^K73]A1RV$4<%!LH-(Zd]A_JHpVIQ(iHB&k(BlJ^"+
22[J/_4%R,8/CEdIYd7F9"lb/Q'SL+ruR7/!QEq$4!;Cn1L4:es,"O61"f;)EuLlcpE,`?P#
nek76*DY_)O3%_@9bO3s1c"(#bMu>>TT_Xl9nG)Lh4*%n$OCNA]ABGG=PjRY^%,Y/Zj?%.T]A$
75P@=5MdR\#J&p<jNSpkF<u>5.rd\:)!\ZN=2DNlmlssEET5A#2f1&**EW:A(dr4o+K<Q9Bh
Ns^W4e7R29A?T2$Gi/rJcnGKYI&0[\r[+F&AW4V`6r2!"Od^]A=g5oq!%N=DNMBCJG;OcL3@o
=2a/1.&+tq>V/Q,m;]AdJJ]A*r@E&6DBbu<sg[bBt)#`QO[4A#LJC"5]As+6K=Y3;kVWi:1@k'@
nq?a<cW1GJ%qRYf`u`lVra6;8lDQbB!(L*goILq`B!g.-5Q>gZKpa>TDu']A@D-sh/ME\:X<Y
K>pB-e55]A<mZ)pTl]A=)&erofiNN(ZPucW1)P(L8U>6s\3f^NfakS\OLG^s\+M;7bGS3V0S#\
6\_XlrpIsPJ+*OI-DGW*Kq9,TbA]AU55VFkQ>\s3r6u@EN-FX1\Ou8nn;n7D+^f9<(FssJ\M8
@5>fOqW$%$]ACS$F)_`Rl%R=<s'geCKr1FR+i$Q-lZf6/`<KMWRhjX4!&7r1`Cq6%1<^nI,qK
&`L9DmG,pF>/f86-+QUXkK/9$BGI.i93b=N^@%dff9m6R$,ZX9&L'2]A!!"`+HW]A%Dd*qN.fj
D6F]Ad6pSOXt9E%Mbfb"W'Q]A%Ph(+cj+6'DBV0&$LU_i2CJ"<4)YjG;s(fh<Gr8!-*7G45CeK
=NLf*L4INQ.O8^0saVY!?BTbtbkf\Bn#(1SpYn;")8VraIguQ[+s#dR"!)MA=C"RE;o5(kY2
h)/2+c=!h#7gKoq]ANrYEea$FgQboaU)V;<G4:\$rY5+1bs-dnk&3AmB"P4$[LG$1NNEf.0:A
C;Q8,85K3o*WDcJ.`Gse"D^W(@D_2oH\7!#X0gQ"^&17G'^C7GGhRp^h>/eOfAf3BtaS4HDA
=B4.kG^OE8*`6?dOZU`%2WXt`[2o!#s8.^B.Gt,/`THig1AVL5X=\E0VmZ.B1;AAE7D1C(#h
.OZf^\GcLP8l*lF\H3\1&S.X5H<cf:9*S1Yc:5\#,P1I6Lm6]A_1p,YqiW^+0J/Iq8-;ui,WS
KItL)ff.Y>I>lT9~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="JPEG">
<IM>
<![CDATA[S@j2R'5-#`4@og6!/`I<5d2@6Al'n,'G7_l^gIc\-52H&FtkI@X=/>/HK]A6uJiZAS&.g%s0d
I`TEBQ_/[WVZX*`33ma5UMBcW8fNlLnr(IJ.mB1I8Xfp\Eit?['t[L6.i#^9?l!I_XKCrdFb
&GE;f=[Nlf#0G/s=reLFK#*O[bH[:!`ZG!ra]AR5+>3I[pAT<GG*BOK1h1a;:WM%O1TiK5jOE
KQH^7#-P-;t2;.I"=QP:>E4EBur.D;Zt539,KiV5(:4p>^q6@E`@9_o)Il;s6`:pH$c?4ku`
m9#]Agu<23.4q*on>\T54`)#BBcP0NfH5:G6W.N\C1CM#`4N@'Q(?/2l8#"8qdin8lNX*'5j3
TA^Z`=Q23;"ARHK+7tT=4PCND%/_=s7D5SBLD)G7)]AH_SNPk71_0`.[e#&sSbn=U6(/(IG\b
"r:=L^o0FP66%O#5Q*CY,[Ah=/P+9j>)l=)?d!P;LjXC?tTE2jg^A2LBL<CYPOm`uQO&T'A<
67JGIVBCED&(G<9'LVC=m?e<%5^,pB(re#ue*^=rHioQ6)?N9Vps8Mg1\1S!&@Z:<^?e7.FX
Gc?%_=I)IlXDP,^^lCq?q9`M"#CWt91jd_-0r7lbj'gN]A6p>OLjoQ.f*lUb@K^n#0At9WQ2S
I%=P<_XQ+^>QgN<rMIOk[_4OuLQ&'0Y_bT?+S6fM9mJfJK5M".0i.<m5V;5lRC1Ee3/<+,eS
@R0-%6KLd>&k<3D0c6PpTa4/B=dX?O]A)"/pZ`pPW`Xni$g^N[4k("k2ah]AN#`,;nc^cUI-<g
+O$>TRT*<?<PSijuEVn7CO)3\`.A^_b;B&Ks9d5qm70Y$k^E?I:,5:d=Ql/IfO$AGkARN#[u
[WREiQ)G"<qE%`%0?T,O@_?>&_?O5'*:;e!:h8r#\Ka:78'gahT@!BL4h9QJ_Ret-H<#I^S*
JKT-CE9=BUFpa33o&']AqmD.uM(mAXcN23_`,WON[faAJ2*'_k\6ot)66]A<T?;?YG=Z3g-G5N
[bQti]AYl?A*uG``t[oq[QrNciV;n_jBt9>G#QJdJ6l6bVqZkq@\CY3Ss%5:MBRVn\Z#h]AKO]A
H4<mqghDG/[rkB.ft;TKa,LSK*biT#coEQ$1MUHM>Tu/8'XKHRGKs&rA))AlZS?"*W4_2ap2
6hnaG4)kEg>B/""Z7FQ@8QYPftNI,"AhY)J@UHqFUPmYFEt3#e/(.i`]AQ9;B4uM=K=4m3MK&
h7BfkCV2KO:Q?W=ud3V%1noMlWH'6F?J0O[;3j@VOWHXemJu,7WA+6gEN>%/fnWN5t^C's,T
:dQSGj]Ae=AUY`6aiqDqTl+%?+q]A"96Sd453-r4h4q2(gL/L+O6P5)F6(Q+7m)IP%%ph1*SiY
QVnFNQ&LIM'o/F.bJ0C0_4Mg!aDN"U+.lZOY^UE;/2+]A[ffHFoBpB(1*gqiJn#1qC8/-<g'a
I!A\%XfL`leerTB&!"g3qr^b_VVdse0Ds9@V9>&<4QRl^"SWXsdP8"($&gG9(j/'\I6X8P*7
qFC9a!agIN0Lt3U1RrgPg?c"bL)""QtL-:e<T)p\&4SRit!\DdI#'X_B@[54GnAr__dU6L4_
+b/b1T)L/\!%C@DjWXLkSo+BF7f16Ha$drLM/_qnP9U6.&%n"1pF$s]A&/Abhc[>ac$3*<gP0
W+?QeY]A$_\%W.Gl\8m,8^QVi*Os+hj'^dk#Q>#=Pg;ga45u1)rIOQjNT>]AWjUqZ5]A*0Mh&'F
$nUj`Q_V"BKM7r=`%2l^DE&gH^$L.q)9$gnFY`dbVj]A;$22LMr>34X1&V\t5cins77iNEa[t
Ru<@`i;<.*@eSqTg6A:$7m'88,IHY.mC^ffWZe^,Sh8Hg4;#Re)3GTIIcYiqK#j/JUoph]A#^
t[>M-\gh<YDH(V"AMkotGfrSbWu)r(l(o5fn^oJr[:,*AO!%6Zo)nc_Y8Mqh#>a+p$PHDXt&
n[(TXA#IHD5`Z1tp$T[H/,^/.(#YAB:*e4-tOor5Y@H#qOR(k&g?>5^eiLQ^M10[nE-uNVro
UWd\/KU.)j''/NYikPMY.ZPWZc7-qoQ3luGO^%@<QiXFa/+\0BNk9,gTj5(Qq_I=.)99*&AL
cLT-hnsqV8afdgBSkaq3[dVN,^IG"IJfam9O4(1hG\YBH*r3rYSNU[`l6@#W"+]A;,:f!sp_u
dk'XU8OhiG&-KErWk]AFE#5=7\JFP;_Smg;8(HLbdq1Q>:*K-7\O\sDZ-OD[@\-/::pX4@V@-
U1m-GDh+[l0gO9)H_"3MMWs(#X^Bat$??enPes_rDOb*ER]Auir%>_6W41P^'ECG<^3Zk[b_,
T'F^&B+r)jl]A=PWIK&Bc)J6;=FWJLp7bP7tuk3.M5ml0Ps"q.dsTjpQJOhIgYCs&)?hX*u>&
V6RH$f42"3kr3qO*P*6]AbFL]A.he'9bGUcjfYun%=mZG^Fi;6/Y7n7]A+"tIc&_lX,jIX#jL-_
FKj!HQoR;rYi[_A7:@juR#+G^)kn/fOR.O]A);B$X7-Rm#DomNi)D'C^U@Q8u\;>l-)1TZO2F
2]AahT&/;VhltC.G8qnttb'@FXdhCs'K+45<@Xc/lnS1k>%&I"bP_]A@Ad&5fp&L04Z7fkr-8]A
%DokSE#`8tH%lFR1+0T9'`g^,l\3Zpt1:SPBGi'G^a8dnl/-hW2>:V!-C<jaS'@jS8K)dmV-
po0pFh[')fQ&Y%onc^p/Q>A(/b;q:C<5hQ:@/Gm`Fk\Jr5Y;d?"W9LZllSn6Z"%(M\M1V\[[
e,f(mRN/$,e8i4/Vf>r@MhEV1Ya]A%Q[,Nu/3m"cXL`VMUS8,WS]AN.K7'?dt(^,LLC>XI&+*R
pdXh-:Y[hWbnH0N8_]AK^8>eZ8o*b)1K?c@m?5?Hui#k?1&khV]A.(iUb)jYWtEs^gI?<@fd.7
P,d(S4(e"7oCTSnnjkYs17%3QhCg\Mb5nspI-T5Jf(t8DZ50b^\;`fh$,[8)A9t*UqZJIHas
Sh]A]A3CC+:^LVaWgE)EAbJ!i7c:G=;sKrnKq+c=Z'LP1m7bqVroumP8&X'uV9S:2rmQ)RmB0_
a>WTQd>_P0_K6L!$F$oD4,)?9*+;SDf`W.u\J>)>dO[_6`hXRl#of03@W_/D>*QpW$kdFUfm
p$NS*j+m(`%3HWS$]AYT)gD+XTQC-5W@t$Q3Nfb46#]A`fo=DJ%CsuDC4uR3LHjFatPObVqlc-
-rCO@BEp2D6^'C#gs#PK6A:>r_`K\j9,*kMJIHVCVHT56J:gfHKiXG>/p^gdE:XobL*IMnY-
AU:5a`oO=AI<LJ_Z!rHXZ?Z/X0L"]A>3_n5@93o$lJl$UG?BfLAX>O?N1@LD(S=J@<5q)@iDt
Cu?!UDZ$P87dt,/,k1[L*(\<Zh_5'QCVlR_r"s8DQE1:56_<#q/TOq:cdL#kaVP"2g:YE64=
1[[&t+ADX;%\YeOO.'-5F$jS%CX%a0LE]A\$A*hO>oZmb'+FZ8;kNU^$'$'uhpZQHNK4V0(Fe
->DX4BC\EAH6)"Q-8S0PbQ&A2N('=fKSRM^eH8mj!/`*!r<lU8Vf<Sm[Y,A&3.PXR)T4`bgl
hu,Hc)9o"9=5J'I:40l+)%J?L/q_rU=,7D&cWD'>2NXgW&b"C*@#r$f@&.<JN'\d18\Mh_t"
6k3U[%kpi9>X2,k-+:n@!]AF%f$^MN4'M!@LN'50<F&]A6[42H+ecSEhh,X6Q-![WH!l9Ca_aR
5Z8j>ZZALa#@5*@BbnRnZZR5.CT^,+8inZ\H0u=EXc25jI`R);>p.=8XKBDV3[K,d,eVFlQ,
uM2p<""1MppkYFp"dYIbI?WdVo,D2\NNpc+hqMlaV>_&j$Yj<L"LXZ=eV=*)B"'2L%5u3ph*
8)*13FX6g^g.X@VqooLn5cCHSG:^n;RG>1QeR:?<Xh=>`Qo-CJ/A`?G#9gICNS<:]A^Y/2r02
3'\Ie?9m$13aaX@ga7lI&bE$:lY8B\OMia:fjqGD;c[qC\;lgq[;ra0SJ_S9]A_8s_1nC3).;
#ITJ"f,K%V?IYDRDDj;brBAY>nAtBJp<h1J+Q9=hk)j?ahZObAhK1.VpM"fQ\n@Bl^T9mK6?
g6gmfte4PPHd0UCE]AMW-IHVX1):]A&d8u*o_+en46<qQD%nOkE7>us!(:G3G'M5uZ(%Ps/VW<
OaJ'42K7a3"6Xbq)pd'[)*g-O'6bhBr@tbaic2K:rmBEiP/S]ApdG:1MYZg.-`;5B?]A>t`-Q*
fe40dfH7eR$=_tPBT>$K#*YF%,b/,3[q:S`+41_M<f[I0S"Dp6E"VNJcl7GKU_Oa?-b%T[m9
g=g]AdJ*e:!Z;DGpU(@3m;!P]AYOEbA1^QFm*XakYYt>/g<o5TC$nda.ACJM!nH,fhfu10n9E/
ph;7Y$]A=hTE]AeZNpRaBn>3h]A]A?*h,U_F4lhQYNIdZmC0Fr:Vu/[=&Zrnm^)9fLaJuXd]AQ9ZG
%cW%tYZ7*'FX=!)RYe"^:9b.^uQB&V!`,UjhWqAujHeH<h<<!W8.<_OjpR]A!B_9$en\tO7@e
kKF&J<OAp,="H+aNlN)n%nK8d*&eOC1j>ZR@Hk?fl^acnG3Zt!'9l:G/]A91`Yd]ADFIqc8!F8
B3,Z$%cOd!_BHh<l($TCWDu5H#"0;"$Ee62!kT'n2gDNRH!.]Ab6(\`IlH]AXXq^=T$Vnpkd3"
ueZ.AT7S=lTVn4"s()DF[?UGUUf1=2]A8&b`^Z,#_VW=7b@FqlO6:94I1t'iI'o,7655\prUq
Q]AFHb=^]AR]AhCrZfRJ9fJWKrcj['3c#U3CDUlG[1<p)1-a"CEG\N7s`FjPk.(l44mU9`ej2;i
NK*O*q4BI%o'/%*2oN2@ilNKLe,%%uB<OeJrOE58jPo0%A8kORPOa;X6:B;^4SiLYfj(JS5&
WKOXg@\,67B-YC)TSS@3;a#lGoOo%NNR643f@Ap*lRNdr#41t<J-M3^XZLif<%(ZH>'fW%+A
ubS,:]AX3>`@P0G&f<O+UDs.M8C:i'U>kV\4tkK%a%?AWBl$HHEW5KR]AonrRo=D?[UDoSN-]A\
b$E6E6?i%BgTq<jUYq)AV,:Z8;Vi4;K'Fo-3"1C'JmNW+O.mUfs*pkiGO\$"$lS;q"tW7>'O
lG7KAd3$n5$FR5E;uFuagu>L`j%LE<q<!/&jc.g"Y+ihdf6+eLQ[fXgToL`.3b=l:g)2>t-/
k9Fn)1<DZVT20\#oR/U%i6r]A%EuX;%>2[H;4t"*cQ3.P>s*3,+!_O2nA"+,Qf_JKs2OPAk7'
QfcejA"OcA(+oT1UFlqK@>D>A'D,3%SDF,1A6TiDjct@lG42Df-aIjg)).hjC-+O4?;[oF+#
egQUC:?T=9K?"#nq$ek'*[p$DJY'.#3AX0%5T\2a>_ZU*,4i_OhaIge=LBVXXuoG%i-W(NU(
BpFe]Ao_op.p&!Np7FW&KYlfA0c3=S,s)+)dU[ASX.H.>_aJhS\.9n\]AZ1TWDf1(S2,;7$MT=
"]Ai/<i3B6mWGd%/Kl_F=2gZ2=DRFAHp)#2f!J,;rgR[o&Z!RlUA%^O%6hZ]A^i^Ur(P!%;h4S
+oUA*ac`&WW2-o2^.d"`+&RiO5`YMpm,-q+fLOIn,3g%s0_CDq^6s6"3u]A^_C#b$d8gF)C65
:DL=Ke@rWd[[Dd4*I;R\0o0g(,kIDWe[17,S.drY?%u7`Vm7%*p$)B&M>C+^N*nin9._+_cH
g,3n<5e.58W82o`qq4C8[(iA!#+qni.HAR8,%h.<8mI=\g%G]A%HCY)5UQm_Q4;'l'D,iSHT(
I<0T%>=I7F4R7SO@+#FmIo[GLu(K/KPg_[UBmUP8h7Ipcmos87Q:NQj"^AlUMmkP6R,=L=^b
EY3q'IZ<$:407S;E<#QV]AXtRtaBD#o#Pm)[mAY7$4*`mu(:5$\&,7N=Xh@3,5%"0uX;e#cK`
3bbQIj]AfDKk@S-L_#/im8;B=Lq,L;+]Ak5O&M]Ar(o/o4OVSK\fa1=p19AJVrKqkHDB2WcopSa
M6a<gcB*,)Di&u53hU`?S'BRkG<.YqmN>.%mPQ4bc?=ZQ]Af/"jT#%ZBi_Ffj@XiB>6m\POX1
38smhW6gj^#-J?I0$c)M@XM8Jg^=o'c+UKd,/Y=hVW$QZeM]A3/%;LT0Bq_kYuOXYQ[aEo?XU
A2g,YR^CYYGqrUL!Y>6VE5-XbRdU:`#1=M+(j*7]A(o(&-h88LBKGQ*h^&?EVD6n]A&kpRVV3q
(G[?_8(d3(oV!"M3LD5%nf`o+&*e&01ocPNpQ]A:l-ZpHIGY+L10OKIh!O5W'[GtCbJYY-*3D
\DOOI4pLoQWL(o+E7_UAI%^+DS["".c-gOU2`=B2hW`#Ql./Dg+t>gA7`R:W5c<i*a(N9<e[
^YK0^e[hoq]Aq9%>ok>j-(IEG(57b*$W-9:TZKk$1?XaXRQ+W1FX.^1=?kJ3%?T_tP$\s(fEh
ZWOc8JB0mTV.Ys^tabY]Aar$^(%Zn1M^^OM[JGA9=_/=4N8IBnQ')M,rO^qdpa-1h<s=Z>U5#
Wo8qM*@m'>:2't=aT"o$1dP\i#>d+_Z@ERUrf*BU9QR5]A^E%Ar%AK3OKdd:eMBr-lJ`a3rXa
MdKJ3TC^^g^uV[F:ZG0-ANMQ`<iZ3XWO`dals/ahrYk6fK50:/:W?)d(n^3=$+eW8NZ^-Rp^
U\m3Or:0>&.FBG(kT->&a"K&5[qAg?cW@:cY)QX1NLM10&/J)o5:\(WTu'aj6QWMqZ!1g\=r
\N%Q-J_*>ElH$@rJYr-"pgPipY;!6;Oj5PNpI\*qo>2OGXBBIZ!WR^c@&UKOcYb4GV</\Y=Y
%5lc"LnDC!Y*W]AV$o,J%PfL+MZ'(NdN0EFmZ4a.D71_%^a*RRm@N#t']AT\6hj2jW/M(t>l,R
R/?)EP>s%NKgE(Z&:WMF?h&\g&>>TicQh>]AmG#UfIY\"`P=4)6GF8ofb+E0[;.p![+Khjug8
.^X,Fm@4'#_)M>#1VZ!)H['#J\aXg7a9OIn4V*#PXM*JVA8<?%StU7*m@,KG9maI'K0P8W4;
poGs7\%!eSF@`,uj2%ZK9S-m8!c%m.WD&c>>3IA'W3kr:iuj-/sPRK'JFH`L\iGi0\LgL$,@
&BV)@A1/joV_K*OY3ft9:IH@dM94%>;fqabd:q*4GLhB0Rr_`M?e2H+_bh,=[-$Y::$e;R0^
)_kC1:1rXK#b.+ct\a8]A+'EWB<L`i_r:(m62go[NJf'D33s=8HF8uNQX:0PL]A;GL';84"XY3
^bS'L7)q)Op-'m2?`O=\7FjDiY0ms*WM5<3_'CDGu9WJT/T>f%k0>s\+gH^lc*N#iqF+nOM<
q:EddpWR2kUdo:h[^TB?*,-8/.[)%qB;cWG2<2\&buXd#LZHXM`Y\deb#Z[i)N?)1UE*1&"2
O0*,Q`F\LmJAX:IWef)kJ^Bmg3]A(:1<D8kOOML1Y]A(a`n[R2+P3CC97Z27Dfu%mD$37-]A6N7
Aos^JbqW7_..qm"Tp_O/49POk`/!8mJ[t5.lWLu?An!T]Ao2^W%6?N10!#'XgBG2t1cM`ipU)
Eg1c2#2cge`<XFBe0R0&6/X&c3F8`GoX+K7W82-U6RfU>eci)OJ,H-=`'n_\lRaQ%V<FSPt'
[3.TR`c!luK!!\Pd389#VS&TCCc;5e"+SL6saZo:M/D(,/gn9$hY2?np&)!C[:mTa5j9A^R/
B/)l4#^'D1:k]AmiEA,O.!/1JG/"b,dRTslF(r1cAj6rB='I*^$G5E0;R>gk\;5qZ[q^65cP8
rd"iCpB3[/sTB"hL['!7?j[#aL5W<Tq+B1\8!Q\.+N[=qUh<5uil>FIAujNSm/)N;C>Bb,"q
L7T6,"<k34uq<U1q39F@bZh/_o6jDMZWfTLV?r7u!)%6KR5%Lhe./8c8n,=W9qYpGf?flfHe
I)lB_qKq!W^+:);;:3&ln:o3K1j<0McE]AqLTtK2jFG"HJeTm="?iG+k!X&/igDU15*c6f%kW
J#QX`Qbn>)BDZrZg%U,p_BRTVAOD(359M_hLkBlcM^ViQTGR%)`@NAJb#\iK0Hof!mIqKR]AE
$GJJu0d,KS0#ZM81qO*rXZq-j(S[#.s/Kt*#_8QQ`Lr\WmY.m`a9K5/)2(%!)@9fMIN&GiIU
F1H;c5T/]AKbf_DWtfQg+U2f3+\7'Rg2T,KV*1hD?U83njRJB*nFfDICcZ4nub"oP!CtQd*eC
9ecC\S"$s]An(^AP,cl;m]A>Ki\P&Vndj+m^bBQrBsC'o7H`H<j<JH-"e?UqXd1^=52LpuOrb2
Rs0S,dVh_P-&/+;^>s+<(^W%W?IM>"$-UrT,K"H%8cQMqfDY_lc\ZRCV=YH=c*BQ[Mhk4?r0
`E"9M%dF7OoU*[OnMCg,+WR!s,U&NOHsEb1dkLn+jbR9VkOFiFVYXp/J@^^<p'D+`:[M4T7S
Xo/!%\?UhKLNOl3mgGLU%kBL/b4LguL)2J2!l25(1sgu.Q&`.F:REol8o:Fm+nS[$_0Ibb[o
&*sAlh4g+Xp]A\r)q^LN\O6a[6'7jmW&M3b'6j8jglt3I#8YX@9i>1roMtL*?1?8]AQY\+Hl$`
%-WSo/;SE,f15L/\7l;sjUWDpf(\PX#'%cDB+RigU]Ad#Du#``mP+1oiS%*ENg?01NCPhhU?s
0@5P:)P,R"]A6W2T7^k_NYZe$QZ$R*fmg]A>[\RDu8*-9qklti;d*N<J1*f_4)it>td8;D'(]A7
lJDs]A%%Y9aagi5ftrH.gXS)7CD8QIMPAE-HT-IDqdS6%cZQ*T9Z0C+*00`e7EFP;;D#@mM@@
Omeqi!0F0ml7rgqd6:;J?B@Lp'sNul`q6sl'-6a"$6*AAcCf]ApaTVtrTO#*r+NT38Z[4)>B@
873\HtJ#bhh;d"-i&Seq4J`7*O'DSYmj>BAm&E8l_<-+#C-kWGh?3n4,h^!kC<75IUi^_H[F
+RTY)l8I@LWQ-66@N92)[MIfATe7YR[F@jJHc_>6-6D.[`^aJ]AH[3MN`cpa)W"V"0-dhjIbr
41rq<$hl#GReaoM@Z4Y=/GSI]AQ6G-a)D-fnK75R=q[gslO@n=\,L-AIGS]AB8t8<1g9i]A'g$/
[S2dSbr`E4V",>,8M2^:.9oR#BPkQ*;O@5j`KLte^017gf7-Hp$%3KTAeS#ZNsU"t98:htJ^
?.I_]Ao/e=EmTKG4(SB^<`R4CF$ZY;J8%c>V%H%FfN^j._CZ,uC$EB.TWhD:0<!V7uCB#!TFF
eRB"F"KJJIh>,>m2q6"VV2[Z>4K2cbgU;X@m'BRbEu@*q*oK7lZ[FjC>'B/UEe)nH5<u\$O%
[hYs]A!O'ulDW:4lL0D$Ciq.TiLkjLp3k0!K?eiI<+Y;@]A))K7r'\8#l.EJ@Q0$Q9CP7Bng:&
$U[p'icF;!gACV%HU99o$[9=nr_UT<Yb0'Rt:g?5K)W3\#6Bc\Vo9n;aLBhpBF*)RX8aHN(C
PXe?Z=$75N48(cKQap2pdZ"U\.,!E]AP^c#Tbr.mg?B=/!>pM[TDon/*I_+N!/Dm-_#Nb+'U5
M5@g$Y56+hMCP>F"pdPSqeW42pIFa5YP6%M?^r2tGs>$MD_CEa1p<mYaY5?VDfX"mOJTtA0"
GoJX>g58!5+-I-$r!`fI4A@^fB6sY.;i@pb&o<7-n*^G"efM1njuUjQ,*S1`-XR^:U@teMaX
S@/\T@j\(.k=*_`9OHTsK&M0Y3!G7.N3_Wp#olSK:E2e!=hHf;o$%LLlElf1EL&OR4pW7s,5
ghlFp?hA\=KM!H%e&NCge4]A>Z_W!IYc]AD54Iu5Q=k#'kjT70nni#7BF47m?/Q<47IY\Y(M;P
qoK/3_dq*Rlm9?P[g[b]AsrL513$=%fF0et;_A'gXS;$DX2g((1&_$Dd>6Gb9aX<Z.ss3el6]A
N\2g&GU41BQ5Nq9;U7ajc.9oR0/+-l#V%T_<k\:,<XXiY+<&'s)@tc1F/kWsB3Ec1h((>oe%
j:f9?_.^,m!!/j]AnaZN!:W-o2Pr#&Wa'jZ+I%_\*\L&YtJZ,al+!]A_k[Wjm6Q,,W1JOp+@rm
YQ<+qa3@Ds!,'BC3;;jtY+rK?&HK<Z8RE<dqPcedCaIOCS/+[2tP3eN"h0FGq70Y]A0$7-f#a
T,P,@Q"FL(U,"aHs$O*EjBVk2a.S(A\nP>N@V]A?mf\kg#CsK;:5<1sEm"31(\dJUI'8/3l_L
c-@?O,pW'r#*gN*;hk?"^^J+Ccf_<K$\#Q/kHSDQ*r;P(O]A*-[&tN\!e#OUJ3,1WGe1WO_Yl
h$F?'3ae2]AXX1')'eh>VW4=\SfDg_RFm)\9)L%oiU#`g3YbG8dQe85dIHlB(k1=f&dD!pQA1
e/Pb+iDTQa8m(m`%bo%YUN5?cH/eFdpG\M<'Tkceg\)U/kq2LOHgHWI*CK%a'2^R)pf:K,m3
FB*fH!RD&@8Q\/HmU3qm^oM)1,KWDa=El,6(_#6;Vmt#06QM1<o,PcV$_25+)!n6*>@#VN>$
a:P:c5`tVl>o+i#=A-)/IOm@mR7EQeC_,-<VhOu;jfuURIaWt%1tg3q6Y"_=c#iQS-NQMP\t
,8/Ef?S0ufUAij>b]AR4p$4M:XtB1T'5h&ogP@MtG22O4cX>h8(+:0@FLNj/E$C7^ECek_Sio
JX_O5/Dt^m3Udcbp1^Oc>EE3UM=/R/49I#KUjsV+2?nh_^1&o$O!@%99IP,2p6P<*2_0H?)(
A>+HX#/(Jq=%$N:K*n6fTSP8OQ)tAa1!(Zrm_o&Ti5TWIcU()RkTuG)i;de=VN]AHt)M&R\<*
(@CB:R9D=_pqL7$aP@%pY7"d=IJ]A*r:KJcNI7j(FN5QaQE\&rflIb*Z7&PqFeg?Z%lLj%/F7
WJRn./Wd'dYi28lFh)%(O\Ij[&YjXUj*V5_P04j(UWIV-HS_-ki=O"2kcHKPmsVUT+EmuH%<
4-EUC_n3aZCC/*9#_GaNVR!d'l?/#c^\-i(sPkdUj0lr7J#rTBE'')EMp2EnUYi,#I5@n*sp
HMN2!':XtBs)Sk)PWY)T`>+8]A1"'?\)<O3Ueh)e"2cQ`FOG=fEedI44_K2&1ainU+/+3lJDH
o'nJE#[LRtd!6IC!Ri@-edYMaD:Y+el29(ICG@CQFDJoU7aM6N4('Ha>Teq#%e?i_5'.(H.$
;46Xp3]AMP/MFQ,1jkNB^sH0e-#mr)+sVp6P,aUQ(YVit#hoLO)i1lI.V]A:>At5HgK&05[-kM
=LrEmQt]Ae]A_qTe%lZ!6A-'j.ZiV$<7<I2b3\7,5Wa#YcV4r&6G]A,XC0WRQU"T)ALT<U%#^!:
&0WqN3A+%6C0[2q9M`R?sCfI--*(O18q-^nG?IceNWUA6;M]AaBc*D%6k,-o5$6X8E,0F6Q@m
%#9,kZPrcG>=.KJo63n^n]Au:OEGZ5/-S9UIT,9uEq&$k_BFWkaGU5="[b(_CMQ0N6!KL.I"'
eS;HU,9WaL5-,7*Kq<f5i`oa-VPib8T!p);gFugh,1`Q2UHqg'OaJPl7VGBoTnq\)!MBr]Aa<
\3E)5K/I@r0HfXd8Hg4s`=om&O</a)@"2^&P_R:_eOp!DJOp5l_"8hDS1E4(ge@6cnc&B(un
ZIW24!`i)9O/lB?]AKa@F4M2f*3GCQg)iArmulXC]Aq)0;_;mH*c<(O@46V<G6ZH8/PS?/)WF=
DEc@QM-Hb<2;lmrbW49Mp4.+inrU6;B=FJP"V:c=!P%CEBF<W7F9a?n^:fDi?kKus#S[qiaL
4e2A4`KTje9E=NR,">fkd/`j+\Hlk`#0\ZLT7qQb_ta;K`A146oNHf=laIl/.bbd%$/VC!C^
A+X";@#+nS8J\`Ho#Kr)$3I7/i`'l0p2d[/620PLi<Uh'J^n`h4`8((/,^h;9ma\@:o;$)/E
--LO]A(I<OslliDoiEr=.u3XA0i#H2g<ULU@nTBBMUdFC/.GcR9m=bqOX8@>iTrEf-(lM<1"Q
5V]Am33#3fqY%h=3C6>oiB*ui/*u4&Bfp^S,!P*fNQd4ob(VPl*QAUbIh`6i`SFMTD$!.39Fc
[%Ctp]A@!?/YN!^K@KBS=hb2!rC=&=.liU_Hs1`=5Kaf,]An)U*hJ5`/nbYTphgI,=&4";TS\I
hNCoY'08d2:o2bjlp>WJPK+(d1%W&na]A/DU!.B43MQCVG\5@4CI]AuqnX#^2q3dS3m?h8jqpO
g-&h"=GWHC=;ER#FWLDrBRCbfr,tO.d-QD#`d=.6lLCc>D:k]AtJ+VK<+4?l;KSl_)g(G:uYU
C+\@mj7A=D]AHcbs[a$$Ua*8i>n/fnMOf<Ip8A*A7]A=,juNEV5:K?:fNB8uKq2r.YenS.c"R5
7.]ANZ[>h=qctY&fWW)+QcC"9geTXJ3MlZ5?^4DtJif;NX_PjSgqfL)g$nZ/dIA$.Y$:3A^?5
YiCi`(R-tR3;QQE)YW@#9[Pg&_[1S$>]AdB]AV5[e[ghNW#&>B:PC^7E*ULaj5%1]A.1E%`s^H>
0J,/W[F;cN/"<TN)n/PELlO$o=nQ9>\"6sja3"QTFERsaSEY'Za+^VS$R>$\biTo)?;5cUec
n;dXBs9!D.2Le?6pq@UBkgQi1Ol]ACO@LI786VP3TQ;s?D6OY7?-7NfIZ;U]Aoe[DFN;o"]AnNI
g3S_`'WYnhFX"t3n^8P<K'NkKnF\p3-72Lc?IgNEps1noSFfcHHe*[3$%^k!b>-(<RLY:muq
N>[Ic9imu(EN;09)6nR%J*WY2ts&UXojG)l"lB^DmZ_d$5=*D*4!d7kAICO&c,%sN#@[C=2$
m0[i8G5^41/glW;#'I3l&`g@:YGDY#gKj"DKL!3?r`SUJCG7@2WIU39b#2$-!B&N]A4$Ubl$9
k6&6#B<#:VeF360LR+VU^O,4g0lr^Dmq?15pItLO;BbSre=Se[.,T0S0W+!p2c#<S>l,7:BL
q?Y!RR,?-mo#0?3!\5Qp*TgVFrW':=ZWom51VkJ(U1^jWJa,7c:1p'EcXI">a$q7&F5Uq`hn
/=S&Mb"g_rYhgIMlc67\^[N70>9hU$/+/nD/7_O#njc"9ETLA"GohQOV_VVLUD1)@$pE9AZC
)me&eS%<r3>@U[64;f]Aa,FlI5&]APiR:GVeF662hd<RnSdu=T\SkV4KDo8`MJ(76B9p*B5r+'
U:]AaXhG7*JDJ#"&[)3R\TREUmG5lQ<X4X_FL/n\$IQE6UN2PkqcFU.T1e+#+bpQ8'Frj#8<b
`3YbBcWn:hFj":P8aCmP7grN`h\.XT,m#-#h+pYnJQ6<3T_`0+o0Ub-LU,X3U(:Y5@U6&6E%
C'EY7]Auo=J+MA;(jQS%g&4BN&U+;K[`&r0C5Jmhl:"3l$q&uaTb=CD$4diX8B`@OabOi/5AG
7mM2*!C60e7#<S"";*[61?/!cY]A5"mh.Bk2an]AV_HC..8o_<8H6f`.V]AKbbH(DIdgX-gufO5
tEFWGNh.kiUY(pWf)B-\-E0?^,EG=a#.rWedlpA7+rkn*3:t+6A_S9\7>h\UkRaJmN$-=[WZ
Ouq%Ke(_;J#*LKkbT$LBRODE.Q)bl=28+Lk^cX2ENG[F_0)`O4$.rjX:=^6XIjDgrKjAOV/#
\VlUf<>4MH.$`Q5lK2^q^TSIm>'K/_Tt,R32*/rh;k4rE@L<.%EZHihipPEp$aBmP!GJUOV1
9-mEXAC96g]A<+8\P5X;9J*!!I+jdiM5D(!)#m0DINeY!$<bQZ<$WG!Mq8P,,);%0Y*CY?T(g
j5"it\Cj);@![]AgkC/?pnEra</L\`NfBd/dQ7m1tRLm$Ou.A^P4gCu^Q0:6@L<$S')n6^8TQ
j77dlqTa[,pd#=MqiIj'+05/?1fWkCl^V\/j<XKZ1aTAo&=a)_-,)[E[G6L+&C9ik`[]A#]A0=
[!4DqKdp8>f%.kFrlbA28E:#R_qkf7+ZhJ%Q,b9OH.LZ_GgbT/h<\a<ZO=Yi9Y_h&1=5S%8j
Q4&^6$rKke"T0c0q#^:9^]A*?nqsS+XA<mEOi$]AjKWjLpi$dd*pfu0"@Ng35u[PD8p`!bLl*>
ZpJM"55rNfa2DKil@hQ,Sna@27ni>eA6PkPi<rX#ZfF3b<)WJlL\jUtQT,%>rpdk*C'5eS0W
U;(3,J!-6t>jQG77=_-Y%8Q4&_`K%=oirc\F4\mc>4tXI_:.X\D3AYARbE4dkih_-o608g?K
`9O`@l:bN\s((eIH#GjaDeEL6[$rdolr+9.l@t0'RW>H+pMq?b,tfHmE0US8UE)3?/VU\%a;
^W$W$3ajrKT/N\@Ta]ACp_Oe$i`l4$c+oe-,ga^_c^sgS/jrMA2?rGU44t&qKh>lrlV;q94lj
OY<u*P`TU!Z,Sa$-f1`l'fTX+2SO6%!VWt1ltlo_C4p_=.X$ZD,qeh8T(?7;=^h^7^u9hB',
%Wp"nV)#Z"fn6RWW-DGW<h(i)D3sTaq!Sap9+j")Me2e\J3>[-HA(BV_8S;&P^dR!hoB=RN5
4'-q2J_FJ.t]AUc)n4<$[NKXqEH9=d(0(!&NfOmPoeK\Ddq&h,T+\+(3kY41dpD[Ohk6iD(,q
]A(^^o\"GCV9^X6]A[LJ-^Mh,gO(r<bhQ&jZo31(uG?!(/'ebh@f``]AegcrW]AWcnG`<2iIs]A/T
qs/PF#c%</9p]A8]AE)Ak`h?ortn+5U@se1R)%]As7e/ET&6p+2A&eCh5ZnG#<?1p6YM%1ZI#R3
RNpRQMMJ*)q/tYo"-k6c'*PQ?Rp_!Rm)>oua",pTG_\%Q)3`=gbNQ5J:0DWj[VG68)deFd^O
C>7Ir<NYK[6ZT!NJX+d*2/>#u9F-$<:A%Fi+98nN@a$n87MUP+PtG!6XdReWp9Q$N.r:C=Pf
?*c:(M4pB5r`%nLK-nm[B/=Bmb2r1Ku2<NH8:#j[_>od!7aTd$b'uX_ojhX4_EM(W1SBSI!#
mG8`'gL#'f?<<6R1iVLienU.hs[@CZd>u&Fp1*IR?H.bOorI;6G\7$OSh3PSS,lZS<C4Slcp
i_dc?S3J3>3g0`4=qdeDjsSUe)R`B-0L.$+3DS-!`;?au9Rhn-!eD$fKDhS/o=j;k3YDaV@S
C\91nj"\sZ_@+fq[8Zp7`-@lcSBP%G)V^nlL1>?jj?E_('(b1ASH[l2EPE]A^ORl+k2_4H:kA
F&S2u`)O#S0G"GHa.91\)]A96CU?UMsNHIW0P!cU"/Sl*\='I`9Gbrn<\G_XQ\1:>3PJLS/jU
fA(56dEa`%->0fL^_C:G"4\A;+eFOiaq%HCJ1ZNkO^p9umV^npP/T+com&dFJ'SS;]A_h+rpH
S2E\GeETi6ipOXeWT=$R'A+i`a!W[A\NZGYnR@=F.bs0@\,TrJ_dBJ._JF/V<q558WAsHG-h
\#pp$fQ!N4iY@#2\^LW3VbB6PfV!j&o'n"MS!%0.WPUc-O^hZn3O#uLbf4K1n[!j:4d,JT-)
'_F0h9uiG/U[X*Cl8"5P9$Embf,?')6ElcMM2R8::4&.>o2\TO/TJd%9<.9l8P3`S#)nSd*A
A_BZ:j:n\-fRSmKAFah4o+hd9#2/hss^$1O<uS)?t14.sRN'f&o&%X/d&4?0IQMIptr,h&P0
Xm978Mgj7U8KVB3c`Klr"Re/u;l/"G#op=R.AY86JdFr+ck"5Cd+-chMS@"91Q.l9N26bO6i
1s%c:Bo4a5AIYr+`(c%J7(`:LlV)?fchJD\8k`3?@Q@=F-)-gib!4Z&uT/99'R[5p\jtVJd:
S,aQcjOm6c7#<(?LYiZD&l*"HNlr=lEf@71*]ATF:h'LBT*0/Qc[]AqU>A?\4N+!@YA!UP(Xg7
>h:>G"p:*,Nk;MPTaZ1'=^cRe2U[3c"91gO<+Ttu4cBY5"&`QDe,k%p.gTgm%AuYeV(OUbS;
Ek%5.Pm]Ao$-1p,:2CcUd$Lf6H%'(c;\Fq$iZ,Fc0Eco5cJAs?`'9pkV`WP32('b>diq]A%GS$
fajYK0fTDU_F*[#i3gu?k?95JTo);.E6c1SmgCf#9AMISK/Z.WD9=A1(bPgMiA*Z9I.X7ja@
t$:b(=[OKd-VrYJoDGWf_09VBQ@\YJ;C2b/\G>;GsuH5e^t)"3ggRCZWPZrV:EqO-@d682;F
)CVYEOA_?Vsmq\^S#FT[gF:Sbr;X-.k%#h`oa9clT]AQ1JS^cm!hoFRneDk*c^Os&0*U6iVE$
jc;&:V7#a2Rtj`G[Z<)#?5p7A-'_pVbT(Q-$1:B<]Aa%6.B*pCEPcV%c=0^p^/3_PSS/9<0YD
SN995I@<*g4^f@ob&%P8C7DU@bI7mp([F>a&[c[=:@kN=9H10&Sa)^\-cuI)3ML[!n``fW3X
2:-&_I?in4*[OpYIs)CXrad!V*lFaa(A"GD4I;aFAk3]AgfE$-u%E"c@mUn&S)\4(7\f4$C`*
JaMf%1Q:5F%Uk)Gb%Cd&%P"0gIMip-]AS=Wi^Mu>D/R$53c!HZ,WA$A25?;?@&Hl+G2S5W,/7
5&U^0X?V1rGLP;(ZLHhn.294N6alXPX1dTdTA5Q.0rf_DuMA:pp3^15<P]A'$qI2TolOFU$([
0P>%3V5@4<S,aCcou1\E<4ROB0$JMOKoP7K*pP2gKlIY'HDT7EU<TAC-S0O!PNXX"_u;^QS)
G.Z"m]AbI3M4I@AkJr>$)m9'f^X\`l3=SX'm3M/WmJh^3_]Ag^m0C?3PA>JmoAK&dWpV@'.kE1
^G+jsC;l8bUh4/XQH\CWij?6D)2@XfA3c^qsc6jj@\5?U.WBUsT3<]A`W(.R4lgX3;f4e%o$Z
ql#237I'2[4Z7d2r"%MS)2Rs=?k:KJ$$D6o%D@dgMF.X4/r-/eJ\a@=29u4c\+.f@6)OF"7^
(%KT'.rZsL1;Eg*)]A,/fPS1(W:'Yh,ZQcB&cI3O&NmZ'Kb7B]A6=#&nBQ"jmLX`bV2aaQo'^N
L>4-I?9-dU>sRhPi05G9M;836fGT7DkUGc1pAG6rm!'_D]A^qk=Ko-C(Ql'B'V<kQWD:b]A\os
u4%n!$s+l)tu+mrIlGg)%/3m/cV7SJsJJM$ms=2MX+R&ss.:b%%J;5V.Zn?fB:nGpL6^$EM!
RD9X.]AoVe>$6eURL<:/<:?M'8]AY8F_HE:pM$nni@061i@k#]A38tDF]A2pb0VKO0L"XcO4i4kH
f')*0muoNRfhfk&3j6imIH%=s.&XdE3(1Q/-=4]AC.BCFnebSks7TD4$4(1l:+FEjRr?,m)a2
0A[^MPD]Ao4J1,DS^`8t%D'[(,7bIKkYDr8KP[_.rV:8%m>.gL+h(D$K9b'`d7j"fcUgpE=N2
bZPjNbpnbQoFb<+UKt**dj0H0#09?JFAUROIKK=rj8%;e^L9?0]A0#l(/d%+=SB[l:BmSHCl&
/aAWb(8I@s.8.9^-Bd3EW^4>\m&p!ADOoN$AG#d[b2_pVkEl8&Gn^eg(7p0jp:SBkRT2QE_4
0<EiAEF=**9SmuDJWSr%7iYHq=_a<8.=?HhoR%M\.W\>sA8>a1U.o_W?9`h!\CjeFsGQIRr4
`#XUs5U_@m?@L_$!IY\NU(K*i/dS42F0KDl"?B[o=C7;kRV?Vr*5/@qte.pk4.&Leklcd`X<
TNq\L"'M%#Y'i3!-P5=C!gHD.#Hk&RD4_WJZm7g8P.rZo=a;Bj]AM/a_g"`(MM$#g;h1<+QWG
UBIQNXY+]A3$;@E-9ENEoM5/57COUngRIl+4W9QLm%mH87_d7R\L<pFfUH2u=nV84_<i(21/&
\jP_`c2[2kY8]A,^7f\?e^J8>M<BUGRbf%'M!C_j3.HpA47kg$&59Ya6*c(ZTkZBa;g<7-#3D
V;b\lr>HpD)ogI[qjHVuS=9#;fd2+7__u9LK]A;NU(qM#+eR#k7'2M\jRG,lI4M4[j_ID24.6
SZW7XZ^5k5o0_+)U8KX[\H9.:b9^AKYp(\2+P@sE/IYlfTgR?dA_SRCLquK(RVU^r$c>-;N7
KWgo:IhfK%Y95h1nq"\L.n\'apU529OCf?!f=S'</s[r0IM0/]Ao[if<D!f&i?X:d:u!JdS(,
d%AE_>!T2u3!K0b%Ocj0hZ>5;CM"ZtbF&M1Go[%9`[`.i/1`tHWQ@?c1,:./IG&"aF\R0RhF
E8jO:8!)E(rS\OKd65-6d*@+?jctdce$6@Hu:/D$O]A7U*I#i45[SRoekX>8eZ/>L)_jK0.=2
-CtN(jpCSdJh%:R3H5pG9n>^ctVt$@NIT=04gO=XBRtQ*Q353lPJPF9e*gN*K22n#pWQKkF,
miZ#ItU%9,-:YhC0pRTPr;)-?m/XD0^TDFkiP1l1t?"<Gc)WK1U(5s"%(<K$S<=,U?<kp#bK
08jN"#WY,]ALn:VW$#+ppp*34\u!;Q=,5O@b]AGYeEr$7gY?7R>m!JYA*O^-UMeF\X<8uEo0UR
\dET?"i.t$MGWX=eb/\!"emUO2NC^<(A_&hl:Ga;Fm/Pmi0dq8oSUJ^IA`IHH;2`6=T&I`JS
H;]AX=[u^_@QGEPq1:^aFM&Q+[f)W;P7P9CB<gcN_(+i-9VM7)=E&g=Sc+:<#%dOU=;N3).cr
/E'kgOq(th==5rh<g#_gKGdWHMKY,#!*ZZkIG$t_MC/R;%q-/@OpAI?#1kED_0H-RN*0mn@R
4dC7Y"=.F5g.2@`m']As<Y;Z35MTdfh?M5UCAl`rJ>\+24'LEn%\U:a='=oM43tO^(/EAug]An
["c7.X3&a4j&4k132WB=MoPuOSj35II$W4jI\qY>63_=f;s\onhF/s5n?*ln1aJQWos^1^Mj
GTF.E\[`\+>]A@cB:NZ\7Z]Af)+!J1JXRNc=<cX]A2P3%bl3)&udWj+)e*k[H5^DZXH4'.+=@/m
()fr5P(<YI<-\(e`FlCJQkq%SmUoIWk&=f'5l0a]A,3#i\35lX.01@j=IoV2Bt>`G7ED;,oLO
k_-MK]Aqn62e`,YfkaXYESai8^Pk#j\>b$`l%"#C/OQd0W+5P)JYgSXu(Sm18P#>A"9IDZp[&
n+sSV6uoR&O*?.%B?DLo9Q8dcjLA^;I,<)8%+)Ni94/.mWURhhLDif^L^sW,>u#(V'd3'q+n
ZbE:H]ArD_4RH)c/a6[XMNN?/T4H<=-!u&L#C08ZsFU,n`!DnHQE^g+;NE'QsIO&#+=k+E'+=
WXK2A,$%?TaY%diMN2H,U=5+[(C08:LX'Y3Iq`5LQ@E/"RL8`"bgI1[Kj@S+'/6WV#VY5n;P
Ck5ns*FL,uW!6J41]A`0^%EtfR+4SNJ-&Y.9CU1:=o2!WU+5u`klVFqQ]AL+g`(Y]A$8nb/oH'Q
tUK\9a!CG.)E>G(k&]A+ln-XEX)*HkE2-Cr:9R3>?-9F>C9U`>]AE/cZgtlgn(MSH2tR[p#"cS
;S"38![+J6YeJ*:0K`^jrH"T\_dY;D$c^&b(aS%#_=H<I0r'6W+cE58s]At"T`P='"oO/nqM3
/$pYkJt>DG1,M\R=_hkePGZ")1U27IaAM0uJLo%1L3^6tb0R:EjYhK\A,9hGkkhlODF2uWiM
8cXF(0p8$.e3#dU)#a#h5J/pZ1d2bt;F)o'9cH6%(ln_e#U2I&Y(ADh+c/ou%aLA&`YX?*`h
2pcgHP4.Q;)QD+0WDkB$R8&$*0p)!X'SO;dR=_h*[fL"W)(UYruj0<%B(PT#$Ah)A[k6&0=M
+._nsW^C]AXt]AP:o2P;d>]A]AV1qorfFTInfB</e"C."Uqgt'CCGjjRM*Mr-a:\A,cF8`oVDU&K
P@g`!Y_-np@A3kl3K+g^#8&.?.&2@?fhBi=aOG^g[7HM'sY9gR?fOtF&-#'kF<2l+rJcF)nr
Jc3cY/[OZHAib`/$3]AGCP;Q_BdtD#@o'&_KKA>7)>NDQ3Sbbh@*?[<Y#>"t.M+@EC!P*)5G,
p3@5K(NVqRak/W:n+A&dhGNAI,PpY7QI!C5[\odYL?=j+o[Zikp"kgDUhg<UdKL6EJAu"Uro
Id`T'DJ)!!CeXj^f^a92<)i)]AiprOgRS!93RoF8cORE;!HC'gurq<A:@r\,=#p%6]ABJ\##I/
WU&^a3WTIS9Q2dNC4Mo5%@kph4Qk?L(h@Z[;^UDP&bR]AKVe7`e5'L>,0d,A9cK:fScIZUsR1
^"QM46=n-Gao+l5`t6j>!,=54?t)Q=dW7UJ6rhb'Gs3!*KJtYp80WiPT.JBGsfidT;Pu^;[e
JD.OrV.%DPbH=Rj!3..p&s&2hkQ*D'6+ctF?I_GsLJkSXQc+e4nY7I?=M=Ne,Z9[1R,.dF?K
k9MUjCCd`"c07SW*__r;\/0&X``id+m>(rFQtdK`'p:Zd)jjU9+'pHi!uW?>>2u7Y)+R[lnM
aQDnXZHN<jD.c^T0ZQ]AIa/Ldt@-<N;)V)G\5W^Bf:'861ptg,`>8h9qoKp*-g-&egf=6!"=7
YqiOU?[8-/s+G\2HN24ZtmS'#9F+f5OM.Jg3OW[8L8C8o,p_\dtnQ>WuCdq;=b'FpgM,\&Ce
^R@Po^eq&-`Eb/DYlcf$RRebXkm55ZY6TCS-_qu#1aXt4mYjAQ44n[la^/k.NQf9^r/?s"\9
_`Er")<:#NP>X;`.SAGcug[,$'G3mo-;]A@a'fH:(m84b9aAIIoheJn`L.N4ehtL@Buq86%tP
,lPo9,Gf#2c'$)-@+jk(SRaqC.]Ai1eEI`?$m=dF`V[<cTPuIAdRhjhtnZge\#;`FK*_R84c\
;<;.7.AjpTJXYPo:UZN2OHrD$/nTKuPU?XY819=aEIUkJ_2BohAZ)IWq"-K2mqi*cHhE!5VN
'6r<qEE/^JbJ^n0AJ4E3i@'.:Y+C;uq<11u2gNB$Nd!Ih[$%BM&_?Mn-31r(Yi,Y0dIubA>F
M[@VCWYoL$Or.np1mcM7=o$TPKmJ-0U.Za,i^[ACf79i:f&E*8(G37P#@51OF`g#OM+\^^O0
!#@-Z4Se$q1>HB8="0kq7;V?X$f7"67qUB`Ol2G:f>4*Za"@YB@Snj3';,#>PRf;qC\YVN>$
kNSGEZ%+ILCM(.#MI]AP2$o50VoQ7_o33#<Tn)AeO.qgB53P1oQ#5etX/D-Ts+!cJ(4k5b3qQ
#.!NJhdN0lSZc`i!;uS'0s?#EH*e\mn_glA`2F,9CP[JN9+6#uE6OOFZ"\CY;%!8&3==5.R6
.nggg!AqbO<p5@t\e4'qD^T]A]AK@;G\=6?`2FMhQHni[%KG)Q2)ka#D=:0!!@9kK'IMeWI^Nb
dpf!_5!Z9nV6f9'6ntW']ArH6DP.?PFSi%JYoMoOGpq(MlsB92+E1n%c:Bp&Sci]AV7s#dE^q/
to(+ok=)fIhl?ZpM7AgO(nIo6$$\F/f'>E2,KGC26B4Z5LIpJAj"aKRuSd,Sk]AD>&0^Vhs=b
Pe^A!7+1:o:dtn,?=ZsX16u_UjNl+)>+g/kXN4+LV`E'[:;qknOagM0CZk<E`)J]Ad;WU9_2M
n`N)Q:"a)?r&q\n`ij0s]A#^jL^ZR_bu99HL;FnAC?O(b6>a^P-%%HB]A<s:0jiW1hNrn,?Wrn
A%_]AsQ2+rMSpImhj]A"fJb817iD@S<'e0s5V.CMP"j"%1]Ani9G_fj_8Q1naU6ph=oWeh[G8?*
X3R.'n8G5c&V%aeCZq7!=g&Y_<qJi?s@\]A-K[rC%"8-a2s;S/.?HZ_M4_L<<gqbb;m-bUAr`
s^=.&IP7(a3R!KVBa1mP33F$C0Xadq)[O25>MrH'I&J`#kVS#Kpli-$"HrAYGp4m`;;)fQE!
SX+p+0#LlCO(_u!/dhZ20&jg8p)F%<o%pD2Rg-o*nZbsrS416kTrJFJ=9^N9`*+bke3Jc.U2
]Ai;F4R8WcXg;d@PGS`_1WZWmSMu<"&e%gNn"a/s8'n,)c_8am)`AKS.b4$@d_FP4j<9Vi<='
',E&R0gh-np5jk"qFd+i4H+@sqWB&>6,<KP5@<Qn3L=R`@TEWil5PPL()6b7+2P>a`\-T=R=
9pJZ:%(NCIp(Ut#jocE[e]A88F4Ga@lZH#EiP;<u,]AHpYn3KO8P6irO0?Nm;'cughSH\$E=!q
jGc`bT+IY%X<#W(a-1Ft.,JAbVnJHu4@jN%H=5f&*5Tm8j39Qg!g\M?Ee!3#PF)<,F>mQ\7S
jQCZ0pfkTcDf-m%H?jJ!>4NXU-r7DGaihuncA<oJ,Z]A#aa1-@fo(5JIVtDcnU2ZlImdBm,^D
)M+js7).eT#qHNiYPj]Amd<[\(a7s33^%;p\VW?'qbmnfjC"-n'k4<PO)7`1(;52HE%'JL&`1
=f(q\0Lc@NW1ErKHpo2uUo&td>n4Db"MMA6eSUOCF5X?-cl%>eM"sUW?PTh^4(Q[OuD`5#?_
Gg9hBlBToU_#.X0tK]A&G4EKqNP`tB5d4,3!>NF4/XK37;"tJ5K5^hQR0'<9*nPkteq\468/t
qu)ra?9A4P=[HX8>22FT_3!"Q,Rr,@PQ$(mTC:iC'\Gh_dPou6GML;nA^L#8Z&rs`]AAYF"="
nW>cKff^dYqjiJ8'@kUI$>c:T#2R:U%MR[c>fEq`!cf(C5:._G*"u%q7IJ4KM*2uR.P$.D@O
LZ4oj^fWCQ`tLdfDpW0NKa_2_jJhs$j`FI51M'"O?n=d5<S&2WRgC7g4T0Wh<QR!!$-ebOjD
:Gi,%fcO7gl3!O%Q*6O%hIq%Ug;"d`a&^1f,91_L.U#`i@Q6l>V$;1TG4?#VqK$HqFJ7OqC2
o'uYIgFF0E@T;/&0if&51,G,<&pSg`_as,l5&S8c#jU,XZoud`K%6.ot.>K7[oiCre+jhfAn
6["ba%":g4!k!m[pP-t^7?q>gZ6BBLu[KJ\!X_!M-=K\uQ(F$9Z?^2F6[URr2A4lpWMg:7_)
]AXln5Yf=1B]A0i#K@W9RLKi66rKH.IR8i"9HNFm^i@<?e*W[f12+Wn8gU*_.*WrKQA)kE1b-^
OVB]A0]A,j845nE%KK0D+b4NT;ulD%?`JnlQR>`@:p_Qnn?2[8EXVoNESc[J&#.D;cLm[I;6E7
914eehciV;:nH)6Gbddh_O9L:OaE!Y"NXPr8ibn$A.r`9d?@5skG@_(#7##D(aQ.mu[8]Ah)o
RY;tB#76c(CiBJ=;#ma_,hWPRUXT'-^YqO[)MB6)2`g<0GfFHJ>kIl"GfBmmBa84[kKt1^9L
@HNMAj8h>8Q07/hW7R27WZSl=&<i7dn>1o4'QYkZ3'(At_WhuDI/$IZH.-Ph-(kkFnbHupd/
Y1pPK8\]AW-)*J0%7`L&g17HH[C$Qj6Vu)mE,54dG+-fe@R)1O0S3ckQ]AWrb>SUF@2KWq_UJs
-#\ll#0O+h7/P$>-(1++c9lgF7=?%KW^GLu&<>TsIYaoQ5)Nk+h0*%[,7FAS0rk$ta84qVZW
\&u>fI(GbVNh?$8V;rMkQ#RE\hh<)7Ld-7uP5_?aU$UhTTJaY*U*BIq51GCui9k=H6XX>:JN
">T3TA`8l?-IC$i2LjMFk#G$7+;cu1>m$[4<Ugo_r06b:%7jCEg:gMoFBg0#'j)[4m4;hSpT
DT%_LKFi+OgA.V8+GT%ZqN=j?QNSs+bZ1q[DAr3CAkV?[hHH`<l[;M+B/"c?6I0B^60E%Q*o
;"'_VHYi*tK.q=Rj3OR^+0W7Ri`)JM]A2`t519:1ar;K*s730sah4nru\6*l#5s26"MR8E\ai
PREerK";&2[M^Q2(TE#"_q]AA#"SSqdu2e;iPHgfNB,7^d6%mW<4j@K2D8bYWW/%<<pab:b/&
cI;":4@l,]Ak+p*p(pOd)TkiW\d\P3-Ne0dtSp&Qr@/PZ8;-+o<%&mcR4*ee&gpthoTJWa-qg
B[#W@=Pe6G*1`JROrLm*^kqb5NK'GKtA:R(t!N,eS)`l'g?fgcRk=Xa)_X$2=puShE3TKWfR
$JkXRp-d=Imth^UTV#-Q1URX0:%/^`P<Oscu[<aU?V)u`&Q=e905d^)sZWA\la5CWUl']Af4X
YF>hrPH2RaieO*-o<_)JC\qZparh1AfMGUkfo_Y_,$O#9)@;e%U:Y-T?\EsXe=8hh#O"=[@B
4_I@_=LqZ1R#=ciou(;S]A!0!!``F_HOSM:^j"`Bn3+[&"HiCLeR0l8!XAR!*e_67Wu%`eOJ:
=ZfXgXU=<a)YNBOn$;"ACCpCa4(L(uQHtUsS)hPr\A_?0:pgAuXqs8;H-L\Q":Lq_25(.jOp
u(.4qdtiOolm./U<@W3'3H57m%9h):H=!@19#JFK[t7V3RScK3+X3is5OW^2/%;*PZtal3ne
&U(Z4uVgL(-%rm\@S4'*1k2#ZHb@hm0.omX5rbf.=h1OB=)`3SnFgOX-S]A;qOn0\#(N<h<4H
flHBb3*<sBJYM0$o;8;U;"'JqR&'rTTWC<p=?JM$a_CW'm=Z2YF2/mm5bYVS<dcaFi.<s45T
:o.'TsmXZffQ*c/gNYa6p))E<"AeBARITJm-o-c2Xd1blaCsZaTBL&=*<Rn:1YXJnt_p"c3L
/:4NP45VNQg7;NR$-<)CL@]AOXcTRrUVAVgZnBadQ5[n9*.>%_dTQPKr0*<gR"OLhpPV:1i]A1
Z*W<:D*`'mC^4.pW$bmC[sUN53Fui>j".T+$T0>$tQfl6"1A([@8Z_em&Mt592^1HCk^'#$;
)0(:Xe04neEE$642(0r2k#e7`6aCTX,hE(b6./La"(Aef2DZ`Wl2pMC6lO9H*oSHdpbaA5ds
O(_j+;03iP[eqm$QE/u[52#nU^_biRETZa&Gc^F=fl:1U(MZK>B:\beIIq/"pXH#h8!hX%Po
S\mj"W(`]AGHt/XN`H2T/s47X20rh_]AKni"-05ME*HA6Jk!DUiHO`ff%\c6kcnK33P,1+l-md
'PdmUtG+O[#m1,<l5-:=K`S6NEmK:d*\S;tr16>DmhuQG&6hRNB!QtP,a[lJ3/5U8]AG'&N=g
,%QI@?:^s8Ghd`a?V?OJDdK-%'rlLh@4DN9;YmkRMP8^r73_QN-9!0(`,?YP#=X"iiu8AMl,
sUVCX]A)+^9YkD)Mr+a?6N*lA"D5=57X.COH_1*iB7'Jl/%K]A^p[82jge-M-+.lF,ggamks@p
&uV8^Q?^ViN0:Q1:m,Um9f"-S2#+t[b"T]AT$-YS@bGDpE@_N?'UCmo#ZFjOpbRS:A6'>mc%@
+DSET'+VO(8ih$Y#b/Q3Cr(]A;f?^L`q$>+r/6+Lc;7)N';Y-!";EPPGOZP68=RCZmHrmd!l:
Oc;]A[sg9&t_>VaQ<QMJLh$MBG0=nG5Lju!oO/KsYN6Q,r@.`L+0nNn%7T=%Q?R2[P-GN<mV>
Pl6d!L,e_!aZ5.oKP#Wm/]AI*+UnoVVtVD'(D6oU.ETqLP[UGdF++ue''bHKkP&#^Gm*t1\Sd
VL>-5nJ#>%6J_XCW$J6j7Ll0]A+P3!jhTj:qXCgB>n9h@7fc2&(3n[H"!%8Z]AEn^eBLgP+a"o
E>*GSgu609^&j<=!M2UWPOqM5;if46a:C_ne2Mmkkpr0Bp'@b'.U_6EJJJAMlu@iPH2BTSGH
F,D%YCff$%A9&[E8K:FFe8&4+b*B\66^,D3*ApV;"*<\)`n<YYY.)I&g)*TG^-J!#!@f=Cje
t4QAcHc%K2$CmEYUo9EieICnfn_X6NFb([T.%pL=AdA@:<+Tl-6ejUgQ?"u_[S9J[rVUp<K/
4t:l$K\(*qDkIV\2eVD8CN$X7P'4mk";1$0lOe.JnpCW-9rmth(Zb!a<9hr[*>5VY^_2#NZ>
7+;i<%e$,(.C]AS!=aF=,rW=m<K\',5_t@+MM8b_3bf+^?/USRkRko'(o.[FSk>WB>XjdT^06
(QY/e3EGn=WD>9.,sjr^,HkhRPg'.=c<1V,Z@WV1ELdo9.L<E^m<*f$>#U)K[_WS<_H)cgX.
`e"^:%'?.>Pl"7[pF^)WcM7@IW'.MF5JD,1_,ef'IrN!2mrJmc$K!9#9\5F.G!^mL]A,O/FB2
Ald,dU3?3Rc[h:=3]A:>_-=J]At-$::46XXUUI1Wg9G@)2<%@k'HhS&V@glH/rB$D7<2rNVaL@
:`qDo(Mc$cFqRmP,;c0^pN,6g8Bi,Nd7GV(bhA!o+aA`a=+Bln:DD+g+)'(#uRa%E%ACQjD%
uK:4P5fP0aEY[u:g6l3WHYY=70r8>Y2+2%b+7"cmR:epj*Y+=u*R?l/2ZKd"6*m+XGGkE%j5
$0hK<3Q=2Ps47b(\)+i&G?O8b<PZaQ/CZn`rX:-aD4*h3s$/b--?LRs/Kq?ihbD:amgpoL#:
7Ie/]A\aTNp\sCr*8+i+jMlBo#h@]A$G]A;10-j,>`NhQ;WeYm/;V33N_eVE6Dj"Li\Aur5OJO&
8@8esZgs*VF+B@/(?l8f=Bc8r&o)IB!qq*P&"TCjtJP5KU+GN*k`OjquEuG2/F-]A$3%hp^KT
`X8T;h3pj'#$,hj`1@cI&I$"_GW]AK:1VDiOO>1(ccu/dCduL]ARaBMufEMN<)Q)j$gGS>'E;W
2^D`KE\+#7]Alq.T;t]AfUK0/QtY7o0$YM0f3i3a$M9Y$m48:>FLoX"MtlJ',H<T%!]ABr#'k>7
;Af[V"=.?kXAY6d!5Y>XqHiA2)%]A]ACpAq7]A%jXV2R3-iT1NoLGL,rO*&$-N(!nf7A$=.']A[Y
G>&_npWHZ>BP\_<#jgg,:r?V3d/4`*kBN`o'L_p*4&Ik$Vf2*\/M8M2FHp*`JYk$;6Rh35sO
fC)$b*G#D`3=bimL.]A`*V1WY;YUU$4$N7CRrBUpaom$tih!Ggsn[?kfS6db`trumnKO::QJk
=)EFhcMVpl,`JfF7$gSGX'kdQe:O1$Uaa,=DJ(==nG,+]AmXu!;_FIO&4`YBbeI.Q.<Kmq94r
"T0oC(s-T!\2.HM6TDFf2XE>"c%Ei`;Gea8is)`\']Ab7fko.;),nHPJ8uoM!kJJ+\6^Dlf!E
$s'E$Kf!\'Lh2dc:="iO)I75V%`nA+/_Z:T(H:KQOTAoAOg`F68lZ*T&Xd>T]ABj$/T-NaNTj
6o</m!1.q6/L%%VrY8Kc&mjdaZ*uZ@?,NP+PkHgI7UPAl6P=YkFrV^pipi7'6!bJE\k_^69F
hX9@:X6\7GJPtZCn=mN/TQ%eYONSRndCWg=tqO2:_,HL+&%nQE1pMcmo6g>GW6\#:==h$3>p
-N2-88S\/OdVd`XK'Sg`+gGp=`ZRa,#o#rh]AZWV77*X3g8ao14o(p'rR^f7IkFrWX_hFs3YN
-N4H&AmfI"0AfIsJtH5/`.ku7NtU>*X]A<FV"(Q+XXj?$DVn1HoV^p>-Yf31;gdP9()%UBdV9
jmtnEMp""q?fEmO7asupp\st+^mlD<IN&GsKO>D/[J"#J?>=iGoW?"1;X3A1s8H,>j@2qq7d
Gt`Q`bR_21%;EVfNHnlo;a++Zo0u?.VpP0pP@gJuSa..ZgroZ04XVl._=nDRAn'&ink;rFk#
ZC:tAq/ma:O6:2.`H'M\RW1hbPGiX2YZZAkV_[8CF'N>Zeai@r>!M%FX1]A!'Q]A&PI.Ve]AOP%
MM=/?-o;YU#Ffnp(L$,2+uSacYF,1Wn>iJN`gjm`A/MQKjmIPVM08r8f(.j?ZNe7]AK4>T/h;
M%F;P?,ZCgPH=6AXaF-23r_TkHWa>`;b^qYfITKU[.&I`8BUP^S]AQko/K098QW**<K%75R2=
%;^eg3[l'%f:kGS\<jGKM6ntPCW^bBLI)_s=<J(:JP'PQn8%^r>7&.j-?bl]AN^nc%66mQsO;
6J?8XjFRR$AM8iKKX.^8[O-43-&+K<q:5JtWko2\gsTd8esiPbl?0n!L9JI1RW;".&\(BcgW
D?ALn%;^\0]AT_b`1VM0)VBn^3gr]A!U=$=st8.sFFLDi$jI>M:rr2<eq`6^Aj@)Fll:Ld<JCK
:_Un?,`FM+-fa0,[5Cq'(7C7W4V;Q/IEgO*3(AJgH^&B5tQOb\Jo#IU-#?5F''3^=2EEM?$,
lK,sdm.ZX;<k1;@(V/"rjW:'YK-AbfI5!"Kl/9IP".b%&r$I6T4d0"k6IE^Hfl8\19[$if'H
l@/^V!']A.7U\fd2!D-aEQCe7L$\gn,AH3X#NuPZ)%pRuEEPUXYke\82(3XGnGJ,2%X:,BO8R
ZXg8-.lg6do]AB5DHgUI;<e#BQT2]A=PVE>g)el(Yq7:>+fXIjln\<\%cIe>\RXm,:dL;LU@b9
S'7+3R!M:ZkT3*Z,lkd[]A0C7s`)^cN%39rU>p`EiL!e0jQTF:<>0NPt"i(3s")jZc<+bbb\.
Mk)<HGR:VN+losEC"Y:oJ$AlYbKrJJ;7Qj`*H-aADX)6R$aoBq%S73hZ^$Cd(;`XEMe+"A%,
_DLWV!Ha3=0K<MEItM,kmZ-abn=e*rE++b[m\J9p2lPh9u5EL+FLFpY0^M"Q[6RY6Fp%_*0h
*HW(5[e0Cp:c2g2AgZ\Ld]AP):E\DWS8-:7Fj@@aI[_a4p8$Zb]Ap(Nu:SieM<VDV)dUl)tTcD
=*)>N)sMbhPXmAOeo3QF3PYP`VBURetKke0P,X>=$;\6T3&eFcIH\%#:3IKg[]Amp<AZ0B9^+
%;JS+A6N-sZZW93`Trfh2K!a&2>RN8Oio`]Ad655t18-=,\'5H>rV8=E/H2aBUm5NeRQ1[I6V
A5/c&90\+$,(h,J1?L(?7YROlO1<2U<S*>q[O\TVW=SUa"mKpk7Qq;o*;8Qct?B6?s-1?!(d
9.*^aU.iB_5g7WhKFm#.gNi:i7lmsjg]AZc-YK!7-#%_`T@5Zf5gE6VlpN\hS-jpP_JTd_"Q2
%^u^[*4pI#Z+ZXY0;6a,1H/CR^e?<c2[XLN%)$R$BQIl]AQg^p::VD+L,eTJLE:J[8_LSJ_^l
t'S)=]AX1/&7"8@p%1BcM_o$h2>B(Oi>+@]Anu[f(sUnZG;j[?2?C#9^c.g/)9[KI,r8/bm!0N
*Po*\Jh8kXn;@D._/ueTY@$pVg_GO33dA6?+H3f5WYU5nq]AUh'*$CTC_j56.ApI+822Lr1_2
(X$YreE/:ZHoTs+&U(WS5_AnJ.hN>TE&sUX4j@XKC]Ah:MbOJEkUP-5b<sdf?7U&BJct,!]Akh
q6nJZ^)92>q'RmXkToS^jXC(Lc&h25g09Z9=s!3iecb/b-Y5t))>MV\Gr#n'Zh-`!Td3p/&_
octb3dJq,O[^O,5ldVWA)Xl_C<A4'B*8^;*B7<^j/<cHW8IH;t'JUKdE!i:qQr7??6Q[%oK-
A2T[r%8P9=>?<JL]A>6EOu]AZE0FbE[\g6UISni2h?'bDl4EL)Y"1i@djeV_-O\le9,.#6lHWO
f_R=D0D:7J_27>p_UUs.DqbP>K<<jY"!\Rk'/gcjAMR6SL3uTt4>2%7*8ZiNQ/e/uK?0q:&e
aee[IC65T0o+4I8Y::j1Rji]A^e!giD5Vu3kgu,FF[gh%OIe`khU0F2?VaQNe^_XTq4A6f\gU
aW,l?.m`Uap(YZ[%ZSNST[]ACQf8.f9Kg9&ssKPEH(7WkiGKHK#$7+\r%fp(dHSGTP.q+KaOU
&e(DsGhik*keM7kPD>3AqpX.qXN^!]AcR6Xa=SPX^NEMUDiifsW4uFkV;K2QH.l[!GVZ/7%7o
T$Fb%Ib=n?@<Id%u7SbT25P(Y#f6.m:k.Rq=Bu*mLngTLq]A/g$%Vi]Ag:D$AOS&L]A@InU[=Nr
N-6o=rA;8[IE[MWr;[-%Sp&9=@Zm?:U"F<[!Y$fb$i2^"m;EPa''"Tg(-&,eo^dFK=9-IPU"
eth=?'uEB?cuK<O,W]Ad4XR>%JTYgCj/"maI3^P(+Q.+!;HNh0C'k!W\['&@FbH#EOQopkn%L
_;>)BaeL/sCfbCogB1rWse(t()RWp"-5ZeJS"2kq5HA.b('ajMPDG/cO/r?$MMHQQ]A#f!;O*
jkr4&A4Wq]A0p!&V73?Z!!*XB]AcS(f)dm=Z`NS"ci^(r6q]Ag9+]Aiq\4JjN1F+@AACM/"\>GE.
21L.qZdjHfhP8M`*T'%OssGH`>QHRU8'\@[io?ZTdh1pUu"78bW'=EPf^_k@^.Ej]A=-%b.aE
qB(sBm?1U8VI\C#8S/Tl.6B9"0F[/Wt"M^i;kJZ_B$'1$q@Z_?m-[J&kqa"m^2mk)!p!5=c8
C4b/RL`(iOG>hiM*;A3"ZZG=6Ge";4'W*=Lpaq;n6.]A]AEb""cF;]A$5#t`Q/N[u99b@'MJZCJ
SSPIb3[bT^:B@%Ap47&rDc&J'la3nKRZGI//X3eYIn4h),[Ol34o]AGSa"MTqF9\fHR(ZoO3c
omOL+Rq$VW*I-^pS<h]AB>8Y=2G!<S1bAmAr*ohG;r:JjY[hQ+*iS1.+4T\ctLtL;0N\B=i"\
.ndOB=3<F'3;0*/Joj71$eI6_DWG.Sd>ts&tA7ejSP`]At8^g02-SHD(_nC5c.UT>ga7g]A=B5
19S[;."]A'4%BnKr37Rd9jA6eO8S*[qhOd$kgSCuXG0:M>WhV;K7jMqIMH4H@CDIeujTq4!@<
djVE"0dDrD<Wc3dBFSFXCRBkIL#&'![(i)2'AJOFYq=bND`fri6R/R3o6+O6U-4hQ_V(ke,8
'4N4Ti1N6bU!0SqH!e'i+"]Al"js[Fd^2rM-#e_9LW?H#*o00Ja)^IH,q6X77t_i*EuuN#sBF
S0oC3O$8T!BoXOuMT8b1TGi#G\Wp9Rcd%[fP5^X8Et"G\4@l77O+HL&9sTJ+i&#&q)_LcWBr
/W0d[\t?1l[[8pTKr1b>V=0S2_!!'L%N+5Sh(TN$'+'V-e5X:2!-jHh._3l;cnQklHnT1S.J
EX9f@Y6a^)WF?lk/.430781`6@Okbk*KPmE,Q[NS5cb0N.@96G$8M!HFCrBPrA"TaB:8?Pu#
4f/hb.TW0^%6+m2$de51]A))LSVj3OJ)!HcD,=,`*G_.1V_dF!&Xn43]Ap6%s/)DlRb.`-fR/T
=R<Bj/&8R6UMok1O\"L1A>cml?Yc%M:5j`l8Af]A,77?79aha(S!gNUZ"_<b/D6Y$tp5h%;#?
X%;7`5f0>!eEjQ&<gDuagfP-5Qa=YGE!V[h,_HMR@27ju/*N19VtA@7+#j#9mbY#nCV*!@3&
ImoBILCM]A_f,DfdUuISI)GNo._f/1R=_?L812A;@6@O4=k*7$AOBi'^_;6Nu=QPUVG0XfBO9
TP5@R\Vhn!i/DYN+\q]A&2;CgZN"<\Vfa\.C84UmWU#fO&Z\Ok`f*"k*A-.VJE0Hp*Zj8DpiU
)Y#ib4#M?"`KksW<A%u80n[nh_*8SNJ>Z5cNUE"c/9*9]A?sU.24LX>Tj3*jNda^g8V\MX4R9
nU#R)MS"FtuifGt*MJnS9WX@8A\S05rI[.HO:0G8VkCt!apqMh(C(V/oQ82+^\:F(aX-K&k-
g[eU%WYXsDXEQH8B4]A")7G!+8^2A@"/_8RB&&X&Q^kKrEade_,`No2mE=t?"Uk`o#h\re5p(
Ejoh(r4<:Xn4Lh07KW5@Al:"!JeMEJK;uq]A'8Fo07,QDDFkb%@iS3.=lori)h$fV]A_(tIY;'
3!`i_o&C[JRV,=<JFloA:^Kp;cgn7$[LC-&N3;':IN*1S1l"7G[6;C<-gRM-HKu;S8B'?7>/
MqceS-3C:l4?N+cI'W++h1f,6=6ub>lF;J\eC8oY)C"9%[Y"S_M8ac_<HL-9Bp)`33S(3m%L
p2>J/dMD-i[D3'T\T>n2c]Ag^!`CpO=m/lHpri]AXEsmA2`KU`_u":p>2HT.">[@BJ=gki4lMV
/E)H/LbS&Dq@B$"?DplcWJEm+hVYZq2K>#V]A[]AT@..L")*Re;]AVO(,7^CS@eb,%^#%q25g.m
UNQM0GUmo1u=DFu2TrL@#gV&j/k'@DXmn]A7j!i-P?HYXu)h9,'p<s=V?UpQjk?QH!Y8Mm55Q
IEfegXfQVjflqV6s>s8I2jEK345:h,hi]A&k%4*)<9O6d.1?)3)\TC%G.hE]AOnVfE6Z"@M>Ue
q_Y/$i6s5N4a.YH+WlU-Tb+Y[J$H7()A6<B48p:"^F*=q`HBj5`;H8fQB8dGo/9@1V#K;T]Ar
74"Z3m(\k?nPFtUho\hh#%/ui'G%_mT9ALAUD,J-[L9@DHK&%r^>8/Z0IDVY`a]A^Eu,hfZA.
ojim:etgIZ1OF@CNs$i55Rh<3r)lgP(qAh!Xe^8`&3[YRVj5qG-Hd->g&JOrp]As[O3Sh\A6K
aGF\iDih3#)aNS?Gq:;tI=Afb-bkbA)/SE'p'7I.^;,%4ddTBpmGd3Ot82rU:*MNj-;V]ArrP
REha+>$\S(q\r@/';S\UN/naZ"r`j)`bodJ'9T]A[U#6hnj(m%KL126hh^QY@EZ]Ac\;a_$B58
#'8jE.QO#%8L/Gn)?#KoRbMc8#!nQ'!d]A[OpZG+HaJ&,MLhHGioHpC@%[G/]A75NH&^rC/,qC
qmRCf2NBkO_66&k'!D$+L*Fc9XZrZk$Ni=rL."qZmeQ!.ZM?*j[QK*.007n6C-UKS'k7X.Zh
Po0u6h7.GJ(IgSBaI`ND#kWBO#P"(t]A2E77s5mL4`lcf;QQ'.WOBcU8(#fl_#"g1jiPUrWE`
"3gAh3e$aFtk"cY'G#0S)1X2;>9jC3cG('Kl'JOnq:M!C8BpUmu3g1@Ui[)H(-=Cj`VD9FNG
NlN2LOP>s/A=hq37Z`?/9^oeK;>%T@/`0?us/ii<i)n9WoB0KQ9B>m:BGnt,'Aa(jEr2!XDe
aCM3[7HX4LC]A*\$:-0!-60&(a$.po3cI\XXL[s??'.fi%4mje;5@Pr/Y]A'8dEg<g[7F&+\jN
@1SlJ*B\7.HCX+ofl<NbbrDAfsuFlV-td`@]AtCnJ,P,:)J^pJqMjQlrYDdYFh>s(HeG>$=n$
p`ObMG@W\ij]Al)haAYRKM8AR86D[lLkR&X%7C8r)_s5WA]Aske$9Z+XqKOImb)-.AR\(P15jK
mLmH"s\n-E<@V(@d%u*r78D@an@Zcum@+r:=3*6tO^bP8-acN<`K,ZbY7uD/rQ39=WWB>2;>
HqNnhn+h&/u*>\gWn*/L=i":"6pG,P"Yiop84%ce3g`+ED6ZIg*gm%-NW;>Phao.e]A8a!(J?
g#%\OMoe5(1p,cX'.hY!"Ma[_uHpUCR1=AnrcG#9,U5KYGH@bWO:?01uisF%SL4*XkSWom9b
;!,;4M:P?eGVm0PgYkCPatB5[)&_0aLi9?3M\PS@GSPeT6h$9b)*)=U0V,s$&G4#[F>6=HKF
9r!<oj8-``"TQ"&Y>s["]AgY@kCZoCFNYo!/fuO!HC$cHAaTX,pXpQRF1TQB[;0U<PqCg"GV"
nbT;,O7[^n9leM.bOhkgre>b]A`t5$g"U9?FgV(S^IU"?gjTP_GKsg[<aN%BX?d&qdjF/>AGB
KE@MIZWFXPG@N6Q,%C*eR6J,@+F`V1u)E6,G[tU_0#Ok/7kBdu[#[3$L#jKQHF^;W]Am/U?NY
'PkIEdT/93E1gDL!<tT/`Br;1lN]A);$XL'N:^dCR,)8L0#)Yd^JTBM<"$D[3:8e-a(R*2##5
p1kR.c[3+kA]AQYdIu9>fsbd_?#o(gFO/%ON=3N7f]AsX@5I\lDi:!`c%C._XfXOZ0G^tjm*<g
%\S`"oT4cnphbA;6fO8*P@cWaV$7eAC.FN+cWfA/1eJ-*)X'`G;+c0=O3$;@N.ul#!?pXbF*
pP4n?Hdt-_!BJ7J038/N%LL/dRP%`q=o<=K\#gP(7Arl)QfpMSk.4['j'q3m4:TZ58ZOqRk4
1*^_nX'C`4fbSjK6[Pe`YKkriNG"]AJWIq>:6\IQ:4[&aN(K^Fj"`5nrY5f2D7%eObV:.en]A1
mI+D3#$C=diAd.c#s)'$A.hp#a/CeW1cQ(ZT)akiOh\ml9@J)*>E32<eG@@Lbr;SO98/8`%0
cW'/Sc>k7VW'Vs!?Z'4E-mJ@O9;GLen7QYk^s\H&(XQKkbdKL.DXRn*cRX21mg@8\+*A-Ul/
8m*^\Fko5(O[@;no_dp-)\koZF"G6&5FdcW(D710X<n]A(BOZ^<g6et:+k?*64Eg="A3oI!WD
5E.9HB-i/+!M=mZ)BOj`j@D>[bNHcO7[t.srIDWn5*;L"((:m5Y?'=N?oUdTW`op+LN6EHp=
<`!Y+A['So\ZnOZAU@k"jLAmt+o<)74RfAW3`ea<u!*=(XC/UbW(Bnr+NR:0Kff6l[(EC@Q!
GOurY<sQ$%6E=h[T?*;gpC13C#340gd,57*n8rKZ,]ApiV-LL53hrr+*-UA.E?e@&9X+s-5g?
VcCtelGT3FBHlR(VG7,T<S=2##Id2C":FUC9CoJ8AsN[sm77h\7#ZkF>A*1j,\mIBOEeLK^<
g(/-:o9)#?bS0'FqFYQlTcD#a6l-SRnumJc!8k\\gdl]A`&^!I@]AKuT=Kj>uEdM2(]AT.^`:+?
^K59O<<a0SDs-d98umHCHA><+@X.=,\DBYT(7_i$Vs>"Xf5(<i5H%g)&h5S=Pkd\WN0X/2e8
-STKrI.6]A>OF1p);rLSZr!7.6+.nbNOQ+hYe>U#f+Q:"Zl2L!JWnBHu4i!_fK*)759'6QUiQ
#%LB;N@3;<!YN=\7Rf@\m/pPkcVJ&I,H7[aoa#U,7J@L;(5*0"d\g6.5:OpI--JQj6'J9WW`
1U#lJjdm7?c<d^]A#4IfhCi,U6kOg<*KA/LYs@OGhVqQ+h><:DL_1</"1oEAlM<kK65k`jRN>
3`X0V'2@CZ3$-QU?@@cgJ9/H^*:!4/;gX>s$9ibbY855'Y<oFji^uc1PJk]APm5m25/S&86f[
f&e3=$O5]ATq79pYbgs0,aMsS"H[XX6O?4Zgp4i*CD5R/J@=sA>/sS[%-s:pjfXS<`l2ZF@*n
Z!AV#X_:>$O'#6s*^Zdo,OP@_=8FPYSOFXKm3IeO43p]A=C6d1>gU#S7L:M0m-<NT]A@o72$G@
-'PX;3k_S>cIlj-+IV(rr%SoO3\,RQIprAb=Z=2HgkC4Ae?:;^u`<CJPJDkD2aB5U%0r=fW]A
!$(c]AM!IB13?IreIsk@eP*m0TntO,2-`+rdEFd)_,?-t&6i_hHSH<%6:+:Dj,Mn9'mg7cH_J
Oi@QcPQ\'DF)2#hknI,XnKEt`X4ta$O/Y`'hhn:&%QM_G(koTk&o;+3PC9De^Quo&Q.0"MJn
\\f[%Br[>]A"jj@ji.j2-D&!_OY\Z!r$D31/#AL;>2_R&X:,f$f!M61I)QV"[Wr;NbsAGREpP
piu-OgqSKa8T8a#8Dqc4MJf+F'(?;mB^l9e3]AHmp6[8NQGBtBeXHr;KW^,j"u_KUBfrB<_j`
7s9\Mo.t7g>&&1`I[U\8G)8(V;3Zd.IN)c5+:c^o';9oJTP0Z%*nVu`XCe?@[pH`]A5u&bIDE
FeVk5,N)f.aK]ADs\H?n2YOB5G@(7O::W\a;X%/FDOQVeEHa9cp2P/'.N0s7:lj$E`:lVl;PC
X]Ai:kn(30jj:0nO)=9W2`Jk:2r0>bjA@7R7'D#"&=g^*HROp=IH<Vb)"&i:O6!CkV[d8Lm_.
8BNf'5`$OQp&cl:;<E%PDE6i8h$W'em*NTIeH3#\d!S#'a;4]A`X@rT9b,G4:TRglAM(/dtPf
]Ad0ZJ7hWU8-f#s;FVXOFFqVP<ANR[^bd+F9u9:.7WJUN9S;MZ,.kCsJlml9<mnZpnp2,A;1D
XddZ\)8c3H\4r<2>%67s66X%Fu(Z\Mi0I90WnjO3UOmaMLYL*s7tTPjMW066ZRQaBGl<R^60
S!=<F]AiJ$sC211:o$;.A681bN5`9qF:po9"_O993.Y%XMbNY%tdtgs>jj&hL\Fmbda,J3M#=
OO]AP/!#UoqJA1#En@*^!4nL;DcJY(]A1SltM5C:+;LJOt!>Lm]A3!$),5R(Z!^+8(3kXr?lf\\
:4TGcoB'RBa\R[sk>(+EaLZb-KM2\<S2.]ApqLp@GGf(c/A)^H#MI=Fd6@327E(J3mh[P1l4I
SU=Vo^1sIZp/@cb0fU^lZ?oU]AG@'^85?Kbaaf'Km;f8o)*hH+Z;W$&d'!$F`L!PM&$'<3N!_
W)A6!$i-%p$j/XG*[-sdBYmG/c76tG$*bk-:BW91`(f\^&#:i.;'bUk*n6G2DV)f,8(e2.`&
ZGBJp/W@Mot7\7"#A0)8E_ZdrsO;0V:9%$-['ZiAQaoo5s#k2lZ(GCA#D.Rqp/o#C#]A6PTk-
?F4daV:%m3RIfY!7Y\*P3o[7$]ATac:?5IY-=mCE;!lOuJD1AIMoBG;1$.#(e[._%Uh>67*`5
J=\1"DDE<qd>JPt)+G^/>3(Mc6K6"!=2f2n76?qH.)gk1.7Q,8ft_Er\3S><?Zlk1_Dm>iSs
+j16[aX2WSo8-[92?+Vm;DTT?sWkBO9k+4IH/\(\DD/R)/fP8`=0J286Jc\iK:lW^f:UoKG4
gjg3"6p"WH6;=1Kmg&u#JS+Q1`Y8cd%%Jn'lCM'?d&.5VUt4M`W`5q-tG'GMMCGgRr0f"h7G
^,r-j:u6-[B`*4&'YWAn=MM_CV?4(qIT$`_ArB!a4,>#<$#,ba>hCl)8.dDoJ<a"qehrF+!r
[`:$^I3Q;pVg\Ol3b.kE]A+&q>K\(s'"213aM6m^3(E53[7*GYO[='h2#I+um2bBc#2-S<a3.
lkG]AY!hKQAtHG4;H...LWrZ:r'1BQlrYU2LFn#p+Hi)I<FUp?+b4,J!0"_#Kqm84#Ol2Uso0
gAr]AY1M`$tFkJ%$FgmDSg]A<Z=#M!H^!>L&_Oj5fQ4cWGW8kZAs&)i?TFL\.JINSC0e3-rgqU
GAX7&6@J+ib+2<rVXeFq"uX2O\dM1nZPP=[(Ghmi(;&K6Wq]Ad9;A(X5B2@Rg<o^c`A?8__dE
+<oN5=Dh!Sa!auFiH3Gp&<)j`N1J1NuGhSuCV<sl5]A34-M]Amqi2Y0W)6ParX7P,2Y;N*-/m?
[88gQD"b9]AE118i:-DXjo$\rL^p`u;;$V/9<0d@QY>9UD<0"nAPMCoK7e[U8=rf]A=1?Y57[t
-6mV@TmUW'cPJRq4/*U_1(Mm)2;`Og9P6o3TJh10f2]AOnQB>iEZh_"tU4!?X6j-KSB2<Jl.J
F]A#+ZR`5jQb2.u/<7`9Fbl<QU_aU1#rXV2uZ\9quuaFEA50K\;Yp(ftPg;s4Nbtupnm?Ks&I
t]AX1Y"T)Y5e>\TQ3^jVb)/6J\;B[Jl,5t\CGL4BKY&bi$WHKa-rX,eCj/o3MZ,:O$IDm/FJ>
=7jZSEKca"197&mR4dM('Ni'6$s4X^h8"P?7Jd$%HW>'`1RPA0:C-?K$hH-bNac4W]A^"4,#o
$on0dROK.k+O,:bCDq0hadpVe'Tdf;q'>4f/XF.8gY@6B5n-'p/s9W7Ld$'A8r`f\YLB)Y*B
h(9p&dV5&e4:J>2@W7!FW:.m,8rck8L_mZG:hCmC#TF'2<rtqN+4a$b*n\kgpD528J#jB5t<
]A\X2f*puNrH>gIb5QrVEZ``_2*VqREiB]A75\G`i`<>#a';=da@1+LiXCo@r:7H,68Js(;8k`
rqLFCeo6pOB'RSfc!UZ]AQ5<S5rs-0?nT$^bYp3]AI84&_mHX=XlbC`Ve)dHU*VSf\TgQ5Ye'W
.q*Ri0*,]ApO1!Z!L;FmXpW$q$Y*U27`JR@`8JUUO_0_a3p`8kf.Bqqpjb%fA?]AKfd<K`a6Qs
d+9KhUkXpL^C>!dl'CocDql9.8p"Ie0n]An:nShOPB_t,eWuHLkOPe]Ap;pZmPGYYNk7aE"+Na
b#_".02`)"8Q^),A"Tr"9n6mn22<4d/$^4<,?LoF"N6ac"6s/s9bsSY2mT`A*qAJ`F$_n>6c
nm,jZ#>[8Z0HAT(^S`;5_pr<Df`oOR^@Vmdi]AE!u.'WV4)`?tllWCW^8!NBcl6TSMK'SZ[[I
9T``.GSeLB0$<ZqF\5sp3'/KEgd<8+#XI^7Vj3P3L#rC%[KGKO\db>c58iU*+7T'aH4M0&q<
Zra"b3^k&E?H3-t?]AKY70kF>AZg1"^fhZPS#V$2>2h1bg./30`=D$@Cc<Gn#98p"jMA(Mh('
97<)*4m.+oE=s)oE)Bo?0bo;/]A0lf:Td$Q&=9R-0R8DdfojX,.-+.nmiOC!;oG]A#UnahZE>d
+5]A]Abnj5]A=a_W@:7rtoF"hLqF&c4('WI@h.%WlVFF5V6NoMinMNgoHEo"0e+]A-(]Aruj>L:3)
">hB^FrrlqtDCZ+jRN6!4[9D%D#CIuR0Vt$G%2Vp;H6V;K*"#+@]AOC.CK+J4rX4QR3h;Gc<g
%e%cKEWk^$C)CJ8\<F<4LYDK9`B@:7M<!CC@EQMDm#=EA">-ZJ7BQ0%1uWb,mCe-##Am=ks;
>_rf4jn#g#0R#&ka9^C$A@3Ek>Z\klWD9j;LG59XS5\VCN6UO,%QbO#cPRuFAGMMsW>n0/G7
]A4BQpjuXfj4rg0CZQ5%ip$ApAo"?.&URtAWHA@`^P.-fY0Tk/-i_$uQdU=U?]A-Q`ZXfAkl#J
O\!@_oVW*4#7aMkhd1blX8Z!WW-[K9R>%c,@W?)/3CX$R9ul;/8L]Adr"\AR^i+Q%e!]AbL&mV
1p4b*(iU>1f27;3jI4``-&cB]A?>K8Q"9$\>d>S>&g271K-hf#)k5\`t`ar/efRFGA87*8>M&
j.oK@or'5)sLZW8^L3+[liGn:RSMa]AgN(Ql$*u09Jrmr0THo;Zg\Ph!k`(u>71KL<W>gbjW]A
PKf[!([T!h.;^iZl^CFF7u_f#nsB(f8kKGSB7$rj%\LIab@-plTLO2(q4^ICZY@gqs._Y1LD
CD*#j.Bci(d(g/W(PF>M9Bgj.nTm!-niq0]A$IuU\_=((*cXVZ^,USes=fK"r,J4u&YV?.l2X
"QaKQXd;SOfih'YmnW5.75cO0jLp%-9/d6Ni<"B^-2_EAqj'*QVM]AIJ0R]AG9=F51d'Bj=j'V
!8LnKhAA=h^.H!=Em.&teW&ikl(et=kFedm[>@XTfKd?G0iiLC[Mf)P_cDN3r0XG,DEt5u%c
HdhMYV5?MdnSY17Qp4YPtA.G`bn*[Uf-XbR;)Ej_?5'+AkSP_:\[L/b$n7S!c%Rl`"ejm<Cg
B3UEmse@s>Ul\XAY*C[$.h!megrCIT;e/AeZCn_^^$Yp@gbgI(`j5ukk=9]A5X>g&kW1LaG()
._Q%Z_Lu:mlD7o&.]Auh'?iH)D=2ZYX#cHp/Se'e>Gi0<k->^0Oo?Jlga'ceq\/#RW!(-Lr#u
!WOZf$RgW!B@)OQP!BG&DbO?$l^a-"XZtFNY?N2TAZKZG4TCW0[DJI/^:5$O$kF!(gU6Q/^*
*nKT<,p2MD4._7Le(R*g;0:5a'(;+>;[kY$J_)_-sPP_X(c.]Aa2T:N"QrKL=Plb5qQ'3iHEo
uRPj:^(0oIiP[Ji+$seC&n`okUHdj@8rQns'c(G#jL8e&Sa2Z;m6PMRRiR.#RcADI8.5@g@A
?OluYu<5OJ*WkI8_f?4*7)3Wt1\!n=%+Rl+u(4_Dr0S(S&3id`fh:klc1=G0hh)-4+^X;7<D
Zo@@XCEDMhP0f+G=E-?0pmiO?A=G:RfHi-Cr9\RFeEY&N:Nde1_PsrU6058!Q@PGSQPg^Kj4
[c=Yc+A3Rk=AN(NfSB:uOI]As/^Lob;`AG[EDG#e6d[lroWX2mc<2T(O48(5A+NKr8Z27]AG^.
lil&Y0^tE-"pcWT%)(em2&Is<=Tfl*)DWu:#)!B+>26<H4MhLmK4P5CrcE'guXtIl-0u(m&S
n.<&Q#jf7j%f%aD/Q%?dNs2l02feGalLHEX+G,u1?Xq6ZD%;G_>l"`-PmcqRP:LXYNNcn7A>
Q"A[H.EU65!mUWm6o)Ln55(sqZsXq1Gs3!`V]AE<^@ll'H'`!C;>GpDRcJE3UTPjZ4CM"X+2_
)H\ianV!Y2OO$K/APFO/*W&%)Yj1;K:gfi<Q:pSCC>\TR&qpI_>@;!':+fp)2+pt^>fpQ1ab
%b1>ebI#Xbm?*3(,JJe8Af-i9>BZa`Ja/%MD-gG2Vn1Ai;cN<5]A.\O8!(p)3?'D"o/0I$AEF
&r^,[`MT:=4^9`KMIekf_o'dt5np!2!#q)?`W`ubkidA4E'dh#r/3riM;h/AWhDlf>cD0jac
U,97?k)bD(2.qFJlg2Y6U)jN-IoG/N.2;Q)?U?u%Fu+WU4KKa`74l&PEgoC.Oc:%LSq?1kC7
AU`!QAKgHUR8%9G(gVahP'N]A/P[5u3h2F`]A(7cbV61f<8;\5q"4)!+8jb0Gr\Y8Ka;l)KK]AI
aH]A;lcp))-<![phP^/(EEJ;#ZL%YkV*EGK?hBXjo>l"*2juZfA^[9e(qQ8e=8NP//42M3:L,
"pbMS*%0!*?\.doS\J25UuO?#"gWl#Wu)BsUh.>I%cQ^b4CIOe=KEiq.*g]Aje&7rY"+&efPF
R-RG<7,CE9b3;tf3SC7S@mhb1U%85bD+4o7FZG[Vfp87peC5Y-HE!DS\;DMi,)K%<N<D^;#J
TS0.d?6Tq5,VS7hl@l8.Uf:Hd+NPD@Fq%GTbd*(I!GrI\2%4qm<>1iB%<nG"ja2XF?mDa'I5
TZ_2fh7_e'BEUXi*m5!rK*N1kD%c\R%qOPV#jNE"]AX\\'7rQ:YZ$0cBijf2tEqV@eQLp4bTH
2dDr#jg@+a!B,F?H7/:-l\SX.\1)Um,@'EbSmlu!'F*@R70NhV)S*UR6L!$$@)<a>$C<W8g?
W"m,Rcc/RNWBn\4HY6QfU4SH.8SF3m6OFe\)miMTm739m&[Ug_$]A-AeIm//&%BJQHrL[^m%@
QJm0nb=[;VVM,XI"N.F@8.]A=@Z':pR2ChfY.jn:=>lc"*1,k<s\7JGKo4TZ6#>6D:_YeAIEh
T:OU3<YRC?jd\iE*8?A9V9#_hltA1SnO`@-en#pgeYLWgR!8*QZHFsGY0PEoVe!iQlo-``nQ
iKSW%6"#2m,!`95Gr<#4)(j=q?WkIUd#)LCKE%P)QD_LA"pdH9oMcK"_Q^D(dEHt*%$1BJC\
@:C)R$XSn(`9V[<JUt=,s7Gos[JW2,[,XY!Au,>g\843U\G_gQGj3eUW\&]ANPJ:!LGgf*gK:
=ln$WmHSb:2H'nrH71<p3^hVm['d"W=)!(M6lhh755]AMJ9UDBs$SPRianPSJ#a2Rt*N,,5HY
f_/0G?aS_*gj)dRLFIU&[;/GYVMqL6;cLI0]AiR8b5&rrXb6bW0@m0FJM>R;GBnZf$oLfSKZF
B^iFRPmQ*^*2G"X*<Ac&nCQ\eL!LUkZ[7OepiN7H.faQf^!Gn3@_c':h_eR*2^4hn%2'(?l!
X/HUFE-4blrWpCGGM[2`6.jr!;k#qo%d=Ef.bp+GSpEUn,/X]Ae/,_D2mO"gQ?47YN!,ML?BI
M%pn1QF?-0-W=Id*4<$nS%IIbi=$Wt9V]A.XeR/fA"32,&j5=k'@!#Gak=,FKrQQ]A+r[FS0e/
/.7\r?.n?V_m@?b`Ii(gW.bIXF7:0oFg71(Lr_h=>RT"VW0Xs!9PcmpR/'`,R*]AkIZI%?gX#
t7>ZZJqk%76]Ao,+[flr8!8XQhs`ilp/#fEduhA@HdX5Y]A>U#$c3#Tkrd[>5Q#/FDK2/LlYNH
<VL#Bp6/L=[Y)s0B>dma<mHIXmr&6dWJ+tF2'YD:JL3M^3(bMY\P"a)^U`X^s2ONd/n63Pb^
-"`36!lhVu$s9X\7!;'.ntqEo#;H!S[Z.4i&SA\m76'$a3p,dWhj\<QlWs+bQr5TiTg1!Q$+
!M(s1Cj3OG`RT:'=]Ag,n?QdE\8D!acOVp+7DWq:L7`3T\_8e`.Q9&Td*TPO9hV>LCR7]A4mRV
\f197>c>T/Z0e><>BQ;.eQA2)jDkI9'UE;5TLVEY>0n(/b)&M4]A/J>>5&Ca0$pZ4-PF+nlia
,S9"U0ihK<(S*qdtMm:P9eZOecc"_BWfHaehg$BBPn4hb"Srn$g=2,RY3W:Q^Ud8>Wh,=s[U
+(<MeCC[:q4C'J9nW68a+`ehI!11TAG_guGGur=X+D+:$2&%;Aid\:]A<Qi;C&PGrXT/iZ]ABY
UHBRlMoA2MfT20Fbf6T3J]A^(hP4G$LGG8DraGHRkL^l/I>cr7me]AFH;OpA-%b_$YE`rH'@sn
CTcT8[9CIqgD_e=5:>pj[LC3JT%Ojlrrr8T"Gr-[VOpGTRBBX3@K,SnCE;5>HN<%Y@Zr/VR_
@S*FO7.TMB'sJP_"koWls7H6F2@E/2B(L[,.L^\l*sD+VtK*V-W]AqU.*"X!l^1_X'W@u?FG&
b&(_L2"V%4_4>oW?r+el8R3>1gkI+TDi7q&9RC]A8-=m[lrELlnH0p1&X==&m9p#4qkiEM:_V
GTQ2r?.gj;Nh(Z;S1g2pD$r[XiS7>+"l)84!TYp7IfQJ#$CNKY;kb5Vn>F;-l.B;F[8X?*_/
slFQ1T4iN#2Y#3a"(F(Z+@-ie;KhVNsDVm[.lTEGZ_mA0Wt[5Sh`F@b0&VJVI3>o$n7??o3F
GF8[FQ`_=Z<Q\XEFB?J!hthDDl.!;CDQcPHY1I=;Z6FM:SgmkiV_8%q>^Fnm;[K$+cSg9%6m
YVl<K;5omCFEXU0;L0[Btn>nj<s_j#&_`E-$Y18up*Z@D?SI+BEN>?63u4o.hVf+c9MR:=BK
#KShtsT1&j%Gsq-sPUVh]AN0f5bU84QFW6ooT(Ri;jPiFY(^;@^`dPCiK-</@$lT1/KmXmn+]A
3oPW3FH#We0AK;=NW#fR2lF6^-(Y'WG=g2Oj0Y9HQpS!8:gl@.S$7r`\@]ATmeYHR'(L.;ogP
-8G&qcI.Q?'pbHcJ!+!$&1_K5XEBR<H,&`<=XTJ9j;)\]As]A^@)CKWV!5nFQlc5IpU)/-nMBg
ff%d&ouhgV6sPj^!bmh\M\_/SUFj_Hd,JGJf"aF\WJ:3HL'*8PL4FL7apnI+/'<2"/k<$K[7
pBQ6%`;j<GJu>O<j)>rDspW"j;L).3'dM7c"?l<FGoG&jU$$X\]AEpWJ,Hc23tVYgiit;\@l@
EiTYq&B=F<"9+/0LGS+fP)ZNW[Q<]AXN9lo#.kms[i<*LFjW72(1"':pDTD2d._!>@P@K(#5d
5Sh3ZF6KEAZiYtn^r%G=;FNPCZ'":0pHGi"sDfUoY,!uG4q/<`0Zu']A2`R"b$=F<#"p$3Y,p
h'3n*Kf[p6%89NdUgU31>qICK6k^5A:n+ee9dGnTOaTndZAHi[EC%G(dASCoX*SiehXNt$..
NcET,Z"ZP5QXa@Q0/r^5JYm6pV7G`2G1kk;?!LQtfR'YreY.46mP.^1#c*o<dVL>pX-Up@%h
m\&IqW,.C[-ZAV<_*-T!sG[L"!VO/-P$F&Z"SAO%E[$EYV4"I_$HX7%'o@B^tV*S&6<UpcVU
`j*L*&!^=U?BVpU/1NP)+WFY'#_7BTJl/uurRr\*8UmKS&HE)HK<HA&AIgB;"B%L7\?MBk1n
SA'C3CG#S>%/oY6e':j]A$nFgip8cLY3eZX>_o#2Z@Y`RVe''\.0,e3[S]AS19h991$^)o\UGW
9!'.$sN?J[=bLf\+Jj(m.Yh'UBD?'`t\D)rs&bFT&XF[qrH(=m.h97,S,?DKagKtQ>KK+G]AL
66C'FERg%?b_\/Z7QN75`!nQKL9Nrl8;L8E^>'X#n*:f`'?$S5<r<NO#2(I=P(=.[)lIU04O
Oi)bk!E$h!_nd%0IKa7dhWSFdS@_:T&Ud+5qRT:%G&sm[G<Qk25@c$P"L&$#tGb;*#B6/.^#
Y?K.B_k[7L)WpCWERcp74f8nu'hWLkqi"/!"[5,$;]A1ql[@guRJ<J,'a`Y1&reL?C5_iF@7;
Fa951.I@m[s,H_JiAhe,+#/A^)T8,IpOp$(?>lU@2'?!N<4==gVTE0a:PTMp1ICJbi_@2b&j
CO:@'X8@!R,6dOK$=<.!HKn'[mUh:0,&\J="?T[R-ecqe>d4]ABgPUM+u)A9K,sMF$*Rg,I;R
aUHbPi=(YK8F"`?&eZOEWVq16%X!\"7A`2s,9tWZAL[;`Pi,ZY61i^TZV.KA\OOJ.)s`7"g\
;rP^#84[EA]ACmCMrCAm.k_nYKAULZq_G1/%u$;5T\7f*8]Ap`FEYflUuD7C#u>hPQUY/E[6Y,
']Age5NH-"QD;X=4]AplG@!qMm>W]A&-`'j'LIkQ2Gf*;:0g;"F_a>m4Q<X4]A86j(_n[X-;G!e,
c&mn4t=kiArY=Be3EtgYjS]A9h5S;ZFn)*8(NM\uUHU5]A17dY@Z'U:Hb7a^Pb,%"mpBjMcTC7
3=4#1^M;3JhM9Zt(:\:!#t]AFFgT"jG-,j')SM@m6cc)XU=d_)dNo[)du;kFno,rI6;5XTN>r
a!oMo:6Z?HF#^t5acQ8U/&lXO]A]AP/'1FAJ5$TrY+HuMRu5?*`;!pAa-p3S6q-9'!9?lH;P9u
+,jgDine:aXcWU-_L[TkcqE6?"2SK-EGue]A*9FE1BiU?n09J1upK1,L*2l'Qmd.pH8Ws't/E
Hpab69&+5j"G!6'H(H9+/9TEV<'f(*:]At0:K[t=J#B#YTD.8uQ+a&tt5h,VG@O$(;5["Z$E8
GLVSEhI='AF.4lr.]A[ZPK:V-A!18a;,N:S("cKU-=Ak0kY'OEd2K[4dN+//r`&sH+d?nKDZh
0K/P>OaYu9KBI^e[MOGcsU/LbJp0e^-5>U@QaD.a7Cci*-7OF0j3,P'oUfsAeOGX:$J&)Rgj
kL`@mX")-sc(s2Ti+?D;J@1g.-O]Ah5G:S!s9g1[LbE"B<5L4kY#77hH-$#\0JVN]AWPK?@4E7
)5OHn/TWljskUm,_E?gZPj.Zkk`;:D`Jf.[`!IJTHqs0:@(2SI")J]AVt0-ji-"^H0s(lUKQ&
ml9<DoG"':`hWU'>"s,"#hn,A9/o&spYO/fi(6=a-c,s"4(-Kn:I7`'Z^Rr()Y8'gj+il#%S
++FeS+[+_9^5gS]Allc8_rn@E#G7Da$oEuE7`c?^om*lr52kiaF/6Q.<RTX'-BaR0;M$[Ik*C
2H*a.NQ$?N_'*5'D<S)5'0Tsd."HZJj444\dVh;TkJ:!4c-?/7R'A^:^0S:@&o]A#@b-m9)u!
]AJ&J/r>o3Nnc--tCT]AD`#._Q,n)!G=LfZfplWXfh`>iEh*!7#"A.k\9YKWnW&VZZ1?E!_hbT
)\3BEhWThn4*r2pIDJjXO8/5aeu=PP@t?1[d]A9cc-4B/#i&l/,\p&aPT`9-B<RK#5_82!]A+@
[r=;Eg:9i/Wbc$r0hef.D^#"(jm,UD%F[^IE+9sC)b@8#(3s%mF@?]AiOP?eshq"5`,n\`gJq
l5u.d9MR(=Ho;Fp%RScP"Rpu)f]A\>O'_Wc+*nG=2k*sRK@oq3i!lGZF\D-?I>3Ii>"u]ALGRg
F=1<.jo!B_6Uq/2,)NNi<GJdn,jj729$YH2bCmC<0f3\rZ=84Tl$Zl`TLlF2pAm\3`#VFfL]A
G?*"\_%T^XhPTY9gNMFml,tce]AX=Z>rQ>s(CgUgin,o,OpD7PK%RkX=&-A$#&K:Q0dq3JB^F
1[qj6@L<3WoK+<?.&GS$lS(a`ETIM0.k-r_h7,W2\c_'"2LngVPI9)#ra+^e$erGg[!1$6Do
F16TA&f?GTIfFk,XL4_R6P-h!YU4EIZ9B?D:b1e2DS*70').%7KFH?6qosfeIKjXNM^F\8)H
tlE=a2G!5l_h,4&-pM>'bOVNY'g`f>04r?:[]ACRSh/%3rd=F]AE##"knakQS^t\G`gb5rF]AIL
FgNmC+s:sYBBKqp)YP/(9M*g==_:_8tPg<qU.)Gi9IO=_JKP^>8t=Q<P(Ar:o5N^[u\jYF+s
._7G&NJ/FPAt1`(A2[\r-"Q%5>MoH-^P^K73]A1RV$4<%!LH-(Zd]A_JHpVIQ(iHB&k(BlJ^"+
22[J/_4%R,8/CEdIYd7F9"lb/Q'SL+ruR7/!QEq$4!;Cn1L4:es,"O61"f;)EuLlcpE,`?P#
nek76*DY_)O3%_@9bO3s1c"(#bMu>>TT_Xl9nG)Lh4*%n$OCNA]ABGG=PjRY^%,Y/Zj?%.T]A$
75P@=5MdR\#J&p<jNSpkF<u>5.rd\:)!\ZN=2DNlmlssEET5A#2f1&**EW:A(dr4o+K<Q9Bh
Ns^W4e7R29A?T2$Gi/rJcnGKYI&0[\r[+F&AW4V`6r2!"Od^]A=g5oq!%N=DNMBCJG;OcL3@o
=2a/1.&+tq>V/Q,m;]AdJJ]A*r@E&6DBbu<sg[bBt)#`QO[4A#LJC"5]As+6K=Y3;kVWi:1@k'@
nq?a<cW1GJ%qRYf`u`lVra6;8lDQbB!(L*goILq`B!g.-5Q>gZKpa>TDu']A@D-sh/ME\:X<Y
K>pB-e55]A<mZ)pTl]A=)&erofiNN(ZPucW1)P(L8U>6s\3f^NfakS\OLG^s\+M;7bGS3V0S#\
6\_XlrpIsPJ+*OI-DGW*Kq9,TbA]AU55VFkQ>\s3r6u@EN-FX1\Ou8nn;n7D+^f9<(FssJ\M8
@5>fOqW$%$]ACS$F)_`Rl%R=<s'geCKr1FR+i$Q-lZf6/`<KMWRhjX4!&7r1`Cq6%1<^nI,qK
&`L9DmG,pF>/f86-+QUXkK/9$BGI.i93b=N^@%dff9m6R$,ZX9&L'2]A!!"`+HW]A%Dd*qN.fj
D6F]Ad6pSOXt9E%Mbfb"W'Q]A%Ph(+cj+6'DBV0&$LU_i2CJ"<4)YjG;s(fh<Gr8!-*7G45CeK
=NLf*L4INQ.O8^0saVY!?BTbtbkf\Bn#(1SpYn;")8VraIguQ[+s#dR"!)MA=C"RE;o5(kY2
h)/2+c=!h#7gKoq]ANrYEea$FgQboaU)V;<G4:\$rY5+1bs-dnk&3AmB"P4$[LG$1NNEf.0:A
C;Q8,85K3o*WDcJ.`Gse"D^W(@D_2oH\7!#X0gQ"^&17G'^C7GGhRp^h>/eOfAf3BtaS4HDA
=B4.kG^OE8*`6?dOZU`%2WXt`[2o!#s8.^B.Gt,/`THig1AVL5X=\E0VmZ.B1;AAE7D1C(#h
.OZf^\GcLP8l*lF\H3\1&S.X5H<cf:9*S1Yc:5\#,P1I6Lm6]A_1p,YqiW^+0J/Iq8-;ui,WS
KItL)ff.Y>I>lT9~
]]></IM>
</FineImage>
</Background>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart8"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart8" frozen="false"/>
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
<WidgetName name="chart8"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
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
<![CDATA[销售分析]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="144" foreground="-16724737"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="2"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="exponential" prePeriod="0" afterPeriod="0"/>
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
<Attr position="4" visible="false"/>
<FRFont name=".SF NS Text" style="0" size="88" foreground="-10066330"/>
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
<Attr rotation="-30" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="64" foreground="-1"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-1"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="15" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds8]]></Name>
</TableData>
<CategoryName value="因素"/>
<ChartSummaryColumn name="数量" function="com.fr.data.util.function.NoneFunction" customName="数量"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="fef838ea-26c6-4f4f-b18d-e07080428788"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
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
<Attr enable="true"/>
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
<FRFont name="微软雅黑" style="0" size="72" foreground="-3355444"/>
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
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16166505"/>
<OColor colvalue="-16737537"/>
<OColor colvalue="-16724481"/>
<OColor colvalue="-16712472"/>
<OColor colvalue="-1128433"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="70.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="分类" valueName="数量" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[投资产业分布]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="fca7673c-fad1-40f3-b289-b91987a9a71d"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="881" y="475" width="260" height="154"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart7"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart7" frozen="false"/>
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
<WidgetName name="chart7"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
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
<![CDATA[销量分析]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="144" foreground="-13382452"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="2"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.radar.VanChartRadarPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="1" lineStyle="0" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrAreaSeriesFillColorBackground">
<AttrAreaSeriesFillColorBackground>
<Attr alpha="0.39"/>
</AttrAreaSeriesFillColorBackground>
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
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
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
<Attr rotation="-90" alignText="0">
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
<FRFont name="Verdana" style="0" size="72" foreground="-1"/>
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
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-1118482"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=100"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<VanChartRadarPlotAttr radarType="circle"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds7]]></Name>
</TableData>
<CategoryName value="影响因素"/>
<ChartSummaryColumn name="比重" function="com.fr.data.util.function.NoneFunction" customName="比重"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="7598c15b-d319-4364-a089-3b7ff9bff7d2"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart7"/>
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
<Plot class="com.fr.plugin.chart.radar.VanChartRadarPlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="1" lineStyle="0" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrAreaSeriesFillColorBackground">
<AttrAreaSeriesFillColorBackground>
<Attr alpha="0.39"/>
</AttrAreaSeriesFillColorBackground>
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
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
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
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16256784"/>
<OColor colvalue="-11184811"/>
<OColor colvalue="-4363512"/>
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
<Attr rotation="-90" alignText="0">
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
<FRFont name="Verdana" style="0" size="72" foreground="-1118482"/>
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
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-1118482"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange maxValue="=100"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
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
<VanChartRadarPlotAttr radarType="circle"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[公司产品能力雷达图]]></Name>
</TableData>
<CategoryName value="分类"/>
<ChartSummaryColumn name="数量" function="com.fr.data.util.function.NoneFunction" customName="数量"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="b26a104c-8db0-44cb-8db5-a27b65b058cb"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="20" y="475" width="250" height="154"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart6"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart6" frozen="false"/>
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
<WidgetName name="chart6"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
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
<![CDATA[每月销售情况]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="144" foreground="-16724737"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="2"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.area.VanChartAreaPlot">
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="exponential" prePeriod="0" afterPeriod="0"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="2" lineStyle="2" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrAreaSeriesFillColorBackground">
<AttrAreaSeriesFillColorBackground>
<Attr alpha="0.15"/>
</AttrAreaSeriesFillColorBackground>
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
<Attr position="1" visible="true"/>
<FRFont name="宋体" style="0" size="64" foreground="-1"/>
</Legend>
<Attr4VanChart floating="false" x="70.0" y="0.0" limitSize="true" maxHeight="12.0" isHighlight="true"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-1"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange minValue="=0"/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this+&quot;人&quot;; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds6]]></Name>
</TableData>
<CategoryName value="月份"/>
<ChartSummaryColumn name="销售额" function="com.fr.data.util.function.NoneFunction" customName="销售额"/>
<ChartSummaryColumn name="销量" function="com.fr.data.util.function.NoneFunction" customName="销量"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="b58ae3a0-485b-4fe5-800a-40044452be73"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart8"/>
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
<Plot class="com.fr.plugin.chart.area.VanChartAreaPlot">
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="exponential" prePeriod="0" afterPeriod="0"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrLine">
<VanAttrLine>
<Attr lineWidth="2" lineStyle="2" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrAreaSeriesFillColorBackground">
<AttrAreaSeriesFillColorBackground>
<Attr alpha="0.15"/>
</AttrAreaSeriesFillColorBackground>
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
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
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
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16724788"/>
<OColor colvalue="-11184811"/>
<OColor colvalue="-4363512"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<FRFont name="Verdana" style="0" size="72" foreground="-3355444"/>
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
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-3355444"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="false" isRotation="false"/>
<HtmlLabel customText="function(){ return this+&quot;人&quot;; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[咨询客户数]]></Name>
</TableData>
<CategoryName value="时间"/>
<ChartSummaryColumn name="客户数" function="com.fr.data.util.function.NoneFunction" customName="客户数"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="8509f4e0-9aee-4964-98a9-5cdf191bf320"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="881" y="280" width="260" height="175"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart5"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart5" frozen="false"/>
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
<WidgetName name="chart5"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
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
<![CDATA[员工TOP10]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="144" foreground="-16724737"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="2"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.wordcloud.VanChartWordCloudPlot">
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
<Attr alignText="0">
<FRFont name=".SF NS Text" style="0" size="72"/>
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
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipNameFormat">
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
<Attr position="4" visible="false"/>
<FRFont name=".SF NS Text" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
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
<VanChartWordCloudPlotAttr minrotation="0.0" maxrotation="0.0" autofontsize="false" minfontsize="9.0" maxfontsize="40.0" shapetype="0"/>
<FRFont name="宋体" style="0" size="72"/>
</Plot>
<ChartDefinition>
<WordCloudTableDefinition name="" wordname="车系" wordvalue="销售额" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds5]]></Name>
</TableData>
</WordCloudTableDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="c2ad3b27-10c5-4c00-a92d-9258fc0fca73"/>
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
<series class="com.fr.plugin.chart.base.format.AttrTooltipNameFormat">
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart5"/>
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
<FRFont name=".SF NS Text" style="0" size="128" foreground="-13421773"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.wordcloud.VanChartWordCloudPlot">
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
<Attr alignText="0">
<FRFont name=".SF NS Text" style="0" size="72"/>
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
<Attr enable="true"/>
</AttrToolTipCategoryFormat>
</category>
<series class="com.fr.plugin.chart.base.format.AttrTooltipNameFormat">
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
<Attr position="4" visible="false"/>
<FRFont name=".SF NS Text" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
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
<VanChartWordCloudPlotAttr minrotation="0.0" maxrotation="0.0" autofontsize="false" minfontsize="9.0" maxfontsize="40.0" shapetype="0"/>
<FRFont name="宋体" style="0" size="72"/>
</Plot>
<ChartDefinition>
<WordCloudTableDefinition name="" wordname="车系" wordvalue="销售额" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds5]]></Name>
</TableData>
</WordCloudTableDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="f40c33d6-d1e4-4bab-8759-d4a870123837"/>
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
<series class="com.fr.plugin.chart.base.format.AttrTooltipNameFormat">
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="881" y="89" width="260" height="170"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart4"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart4" frozen="false"/>
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
<WidgetName name="chart4"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
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
<newColor/>
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
<![CDATA[销量TOP10]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="144" foreground="-16724737"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="2"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="exponential" prePeriod="0" afterPeriod="0"/>
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
<Attr position="4" visible="false"/>
<FRFont name=".SF NS Text" style="0" size="88" foreground="-10066330"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</XAxisList>
<YAxisList>
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
<Attr rotation="-90" alignText="0">
<FRFont name="宋体" style="0" size="64" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="true" columnWidth="10" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds4]]></Name>
</TableData>
<CategoryName value="车系"/>
<ChartSummaryColumn name="销量" function="com.fr.data.util.function.NoneFunction" customName="销量"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="33e59329-333e-483e-9c56-80c181ff2641"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart4"/>
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
<newColor/>
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
<FRFont name=".SF NS Text" style="0" size="128" foreground="-13421773"/>
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
<Attr class="com.fr.plugin.chart.base.VanChartAttrTrendLine">
<TrendLine>
<Attr trendLineName="" trendLineType="exponential" prePeriod="0" afterPeriod="0"/>
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
<Attr position="4" visible="false"/>
<FRFont name=".SF NS Text" style="0" size="88" foreground="-10066330"/>
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
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-13312"/>
<OColor colvalue="-11184811"/>
<OColor colvalue="-4363512"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
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
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
</VanChartAxis>
</XAxisList>
<YAxisList>
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
<Attr rotation="-90" alignText="0">
<FRFont name="宋体" style="0" size="64" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="2"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-1"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false"/>
<HtmlLabel customText="function(){ return this; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
<alertList/>
<customBackgroundList/>
</VanChartAxis>
</YAxisList>
<stackAndAxisCondition>
<ConditionCollection>
<DefaultAttr class="com.fr.chart.chartglyph.ConditionAttr">
<ConditionAttr name=""/>
</DefaultAttr>
</ConditionCollection>
</stackAndAxisCondition>
<VanChartColumnPlotAttr seriesOverlapPercent="20.0" categoryIntervalPercent="20.0" fixedWidth="false" columnWidth="0" filledWithImage="false" isBar="true"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds4]]></Name>
</TableData>
<CategoryName value="车系"/>
<ChartSummaryColumn name="销量" function="com.fr.data.util.function.NoneFunction" customName="销量"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="ef86dda6-5f89-44d7-badf-718630ed1e1e"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="20" y="89" width="250" height="366"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart3"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart3" frozen="false"/>
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
<WidgetName name="chart3"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
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
<![CDATA[品牌销售情况]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="144" foreground="-16724737"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="2"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="0" combinedSize="50.0"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
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
<Attr enable="true"/>
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name=".SF NS Text" style="0" size="72"/>
</Attr>
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
<Attr position="4" visible="false"/>
<FRFont name=".SF NS Text" style="0" size="72" foreground="-3355444"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="70.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="品牌" valueName="销售额" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds3]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="4afd3aae-2d06-4503-9235-d739e7b5feaa"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
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
<Attr enable="true"/>
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
<FRFont name="微软雅黑" style="0" size="72" foreground="-3355444"/>
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
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16166505"/>
<OColor colvalue="-16737537"/>
<OColor colvalue="-16724481"/>
<OColor colvalue="-16712472"/>
<OColor colvalue="-1128433"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="70.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="分类" valueName="数量" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[投资产业分布]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="4074a387-f698-44da-956e-04e9db769b32"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="596" y="487" width="235" height="145"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart2"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart2" frozen="false"/>
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
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
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
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[]AiO;T)\*h;W_n@#[7dQDNG;=TFI8nJ7EqVt.TV$R;C#/Ie-N1ieLItAPRp,sC6Tk7Xt@b"Z_
ESk^]A37Umt^)_k;i:,qrs]A!SgZB.I4c!q%ONML/jf^]AMn^8/GAPr8s!"PS"i/'pd;#WOd->`
h3U7Mm#o2g3[("$6`YFkX9%aem)7X)b4c$1lVe-F$[&H/NIFCkI`dh@(6WVE8qPp^!dsuH5o
q_NUcS7l;M:LQ3%q=OUMq@\.cq8XSlU*(=puhn;f61UVEdt%N-D%RaTbc4KO6BdiRI`!=?`g
^k,!A]AQc6B8h/qCsd=Y)Bj=?hn#SG#SCi(JDU0OK08>Z"su3#$FGrJHK%a->Y?Ia(Z)S'090
-4,PNK5:E'[>iF/`X3,Zp`(2)j\)gp#9h/>N>\Lc*!o.keGmju48t<)WF#:?5,j;)*:CsDO3
HQkkEX7,oaWj7HQC`i?pE6A?9]A2?*8s7L]AX<9+M=F1,r.B'hD_-'Vq<$>/rk-"pQnOfT=Da*
WY[%An;:W"j2O3fb)m07-9e4;]A/Ft"ZrZ`>s=7o7P)80,h>5$!3Yk:LeHa67Z7oGmjZug"in
r+f!f$\/+\X'rH1NrQLhhjg)h^kDs&L$fFEle<dcJGZroNc:FMPohj9OWVmo>1,ea3E/[.N6
%2iTI;l_^DFM.h&4S/"$OQL>_U/=k@?5)Mh)U-9Tn]A0ED1&[Ilcl]ABi$Yl`*_MDjmS]AS:MXE
4o@:u[MMXHKT#4X?7Y?tK(J<jQ)em(jI(*p#&]AQ)\.MIJjX%,-M\r[hAXDrRpX8X:b#)dS'2
^Wo.VB6P!#*uNTeC[$bXW8/5-oVhPk;"tHRj<,XJgZoq12W%aj9RM1*;bj:-eL<d:uRt&81E
bisttTE(CA>ldpcbj5mM.ar&EF7b$q3e`h&%*1S;NOUuBo<8!(YXIi@NVrI0Fe^1p^D2c+/#
.V?'d--4:fioa+p%5k"#,Rj+9FtTs>2A5?ZW-#uc^^J(&\QKp%@V<ipfnC!6r;(b6Dd"9n9m
a'&JpI/19:[jVF:,?jei,7hb3:pA71PPI(<>+-Ap&)8/=&MMM8*3C8ISkBr1b$I*C!;'uJH/
^SFG2'Yf`npGi^!n*PLT4cGR&9\dUpWeD;mIM0k*HZG1-oKG[6WLZ:XZS7[Us#i^C`uQF-=T
r^7o=niA!$7\@A\EPY3W)3Wkem,tJs[#bI,TgC')TZJ^HA"6[#8d48H)EPQfH@1k>0oXT@4Y
5?UE0F/+2=aqLki!iZ`%b&teRt'pqZT+qT^i:KuA*"d)^b$T>c'"+8ie*h"G'jL?DcB\VWBX
fQi71YV]AMUu/jRB^N4;O4k+!IU!6ZAc%>i>><Gj"4OOOKV^Q/<LqQ=A?AP/$eFsS-iI.68^\
Sd#/0^Z5A(OQ=8BH&3W!.i1ZqYCq$a$=:O*sXEjO%MHq"XQ&Yl_V<?_6"h8@"qX+=oOpKf5J
=;_tFTPm(NBDetiXQQ.f[4%gce4F0TE$';7p2kM_X8U?$AB.CaA*h"g\9jBDm>ggh3?JslfO
l':Bj%+[\ZeG58(;igIqaUNm")t*PtPho#PB%L%JrR;\(f!R:Ea5a]A[a5[B:;Q?C)X.E1L_c
`5TXe>h?XefhULe;qZ2!=Z+BTLV42)"%u_-YffRf69?CGR>]AKP*i@cHs9>5)"gHX;)iS`0.[
B\hGe`#1GTd"#SOTWHPT^VDnDs*si+Fg,(bqF"^ai98fFiE9,+%N#R$dg+jD<l+eL73/ad"T
d)FW,g76.!?_SWK*i^*#@.[m9j)n5us"eRX.g2Plr-ATK;A+$0(r:WN]AM1a+b-jL6fZ&787Y
HOTU*;#A=H$UbVXUWTQZ\aJbTL]Auq]A+De=BaG(TB2bAHe(DfrT#EhKQ)M\!oqI#H2LCJ&3oc
YMG3,bSJAXd<#%t^c8G;Z`GmTGl&Nc2.9=tuW:3Gd10dEgLS)&Y8ee52(#6>.cI2Nk-J!B`#
]A.cdnaZrtl;jeT'l;Rd154#ru6R@a.>Y(Lg3#&\GuQ`QKq^ACE4k%Zh=HsTmJ)O[@()J\IPD
(-t'/e&*>,3'Kg"r3FZ3r-%7Heu2Ig[VlIGBSa+CZi(u6@?W$4FjDt)YEC!nCKSPW!<$WLk0
NkV?O6:Fa&6frEr>!K.[o+mO$.+?[q/C?f0bc82^W]AWfVbZBU&LuaspWkbdaqR7fsl%Z>NRW
^,Rh&EC[RTi3:U(W*uV+8T3iroXB`rH[os3$qn(NSH3as8`0n'03aN'q<o4H&A$^oC[qKAIZ
c7p3D,U<%!YRf6RE+3aXImc7T[Kpn9(V!">OmZDLgPJq@4834#GS^G,4>.XJ@..0fkh+U?JX
3S",=(qG(M\3$Lk=*oBj*U<eoQ"*8A[_2Q4HYPWkoJn8R,XH%*rD(rs?]A[[TA;r4FDXJ/i;&
\p9Iq/9KDZ).bUeiikNn#=oq476)tK%6Xq#65V!D;B1#]AmTTrkXh93g3,%i.L6bsE.Uq7AuR
$,M]A2VGJ-2rKfmAnsdIh]A^_E;c-em^TF8i+d3T0B0TGG$;NXkuoagOmT%=ADBG[Dt823LLLj
+3CcY'HpfQSDd$V]AhLR+eT$@@9=X#rfV<Wg>JAT(jT3&$be5^OIKmFRV*T9:*N>)`Q-LGnIO
BO)[[;>:W*n(;!Coof1?QOSiU8"A3YV&-Q_KN(,]A6;8I2YnWTS/+6PWnV^?QU$_2Rd7a/[%k
8]Ak3qK%1FXE2)![Dp2du.Tj<pamDt-`g5e?lP$>eeA\3!\PTN1[HZJYI^81`u8`\Zb+%8'+g
SAK%fnY0$(P.fnLp9]Acip*Y";'j3CQ#0(Beabml8?9SH"tCD.:j?3BcH"nZr:&]AL=_JPB4+i
;E`Im=c'BJH_eOe$_bkF-T)SOqI4qeh]Ap-jYO'Lc.U$(5;YItJ;FORidQg=a0M4!t(J]AJ:4I
@741s7A526r4,lo\pegoe]A/7\C=9W-P:JC:PNj)%.TcJTi#GoS/sb^'"Xtg7+!m=&_37OSWQ
cVYh.qROY38;qT3dg-/Uh#om-Bd$Z8.-=-aGC,:/QK3D9ZN2V:rUsgnJ2:Gj@Fs#t-3R&g&k
&*7V5l?KR,A*NP=<p`$i?`'L2%!pJAtV'aqjpYY'ch-V.5]AlX_!el;rNgSRK)<8A*,6?_s1m
1bqs)Zeecr2fW1?i<jYX!Es0!IZ-pgJ#Btpl/72_rpU07;H#tbG7Z"SnPJ8M:Sg<HV8gAnMM
&G`qIZb-4X'K(G7-0(LU0BQ.(Z`A8=7KGfe_^[Yq:?M9W4pTT0A`#V<M.D_?HHEOXYZ]A:pIp
d*hCo^6G!]A#.<7bB!ofO-/,N3l1lIhK!:Y$bn0E>_;gp\rV?9FOro7A?2URIhR'lT0qacV0'
=V5V`_[2q^If+h\CW*=scUB':m99i3<4_~
]]></IM>
</FineImage>
</Background>
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.meter.MeterMainTypeChart">
<SubChart class="com.fr.plugin.chart.fluid.FluidChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.fluid.FluidDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr value="销售额" value_customName="" goal="目标" goal_customName="" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title="目标完成率"/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="微软雅黑" style="1" size="144" foreground="-16724737"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="false"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="false"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#00%]]></Format>
</ETooltipItem>
</item>
</itemList>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
</EAutoLink>
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
<FluidGraphic>
<attr animation="true">
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="1" areaNumber="3" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=1" maxValue="=0.67" color="-16724737"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0.67" maxValue="=0.33" color="-6697729"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0.33" maxValue="=0" color="-16711681"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</attr>
</FluidGraphic>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="false" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
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
<Attr enable="true"/>
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
<FRFont name="微软雅黑" style="0" size="72" foreground="-3355444"/>
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
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16166505"/>
<OColor colvalue="-16737537"/>
<OColor colvalue="-16724481"/>
<OColor colvalue="-16712472"/>
<OColor colvalue="-1128433"/>
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
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="70.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="分类" valueName="数量" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[投资产业分布]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="9a6a4da9-b5e5-4afe-bdf8-41c585cfcf67"/>
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
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="322" y="487" width="235" height="145"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report00"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="report00" frozen="false"/>
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
<WidgetName name="report00"/>
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
<![CDATA[2171700,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[13449300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[2018总计销售额]]></O>
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
<FRFont name="微软雅黑" style="1" size="216" foreground="-16724737"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[j]AZG%;eNP!V:Bb;8ingADieVcTrjS*>"4WYQqf;0AJ?Ysg_5qs;?ijb#`tS[a$s7+Am/F.#+
<j?m88g_$R%GJ7T/:["XQck&-Ki^EZkbU5]A14MV]ACqkoB;]AWH@Bi.OWXJ+^@,:l?13*kg3gJ
[gZI[JZ18\:"QHt9s7k.T"Mt[7+J)^6oO@#V?,ac8"<u(dmTXVB?Z9%1,WrRdWVN"t6!r(DF
5l"KBeK8Foj">@dlZN8T'Xa0gJ"3k'_[mI/'78#mi#R(gFL$do"F\)\j!qk[suSi#l[cle#P
pbdGi8]A[uTEVUmJ2rYEO^4ba\JMm508B]A-a\a2FgEsj;N#"`GSgr\gk)cUhgcUpVrhCC\pGK
p>tQ+2IqHKc^OOQ(\^6,/e;eHT)A,P#Bu9rp$F-Y]A-MltB0F@;[_BFk$FTWKLS-`bP0C6)Hc
s@ncJ\`qH3+Rq:D;P'`d?OdVGiu1[]A)r(q6$kIZ9MiCOBZPG2fqGl_cu6kJ2]A:f`Tj.=2YEi
Kj7jpFN\r88@'jYp[*kpr!I2f:&)X=-0ahuU^WWq\Bu:_2%r4+A^9:jAfm^2!-b]Ai0j=Ma'6
\cS+P>.T-0G-.NHIgM73&;D$jsA_H[$A[[R0;>,^NgK&:_gQc2ZXYQAC"#4iu#C`qr2f5*k[
eUmYA5Mo^S)DWnGXrOgY11V=r"F>?TBp,eNP=?EJHXKaZ+XFpd)(hh#a827:(A\#i5,lZ<op
X+aT7QHVPF=@ecZ4:]AS/0=T_i*9Qe.;#CX&2%.&?Ps<S!T@U4j!U+5dYY:3/1-[l(g:sKIiA
hA)G,0Y41f_X.".-U5Q81T_(.p,7W,Q$Ak?Q,Sl@&6TZ3U[51cK@qG\?Ad['_TKoblhj2oV.
/[>.e[GGTLP4.(`-T7U>^f3hdE?FE1[lhX\eBr@B)jlp'o+,uQJ6C!/h@_4o1"9lU-!#]A\BH
K[*8;=Y_hYp.3e`c28Cm^lE?jl0l0n`,Nl/MU(^%nj/N=tiKY@F4`DTL)mAV-WaU2@k.J7">
PGnGA^H+R4/N6<WCLlMTH]A&oH9`7;pL6KKfO;Hn+E"qrXGld4Q0KGfd/hL!9T[+p:0!MU0P"
n<.7XnVR:iVLZNCL.L6iPDWYb0(mj6=,aPBMk)o.gWZ(VSfJaP),<[ROtf)7TkQOb6\-P$UL
n\.N>@a7>l=&0m^[#o%*.b2VY,d7`iLlu@odnV=/Z3Y7es>N(5>+tJXVO;Nmf,CeL>3SRO)0
&V#rUYLb,,&T?7F-IG?b>g1#*L%2u.`q*id!)fDC>Y`8'i`9[p--?LmV&B>@NgroRQV0*c7&
eW*mEg3oQSU+Xfq(.P$^tO=ok!RO3fjaUYVcc&t\]AK*1,o@E3r.BT!P9&^K:!u^9<Zkma8@<
-26qYp&DsPn5jtt9BG%XVFjO2<4.P)\o4O1Zgq8QQM3/V%^$Mc(a-)+eil#F,9B'hVLG7r9Z
EK4e<Zrj<,3h^\>:i0%uM4#5_;XMoM>8pO)QHXYRV8abDUmaJ2IKMY$>_$f9"rL<G9'n(*J&
VfrN.t&2-VPnfq&9eL908tC$pAaYiFWn""p5u@#C;lbVMTUcJ5YHOCC39fb]AW]AP$uE/Yfg\G
*"l7!*:V%CY&9EJcau3&"KQS-ZJmXp,R?V`.En.#)Q#n-fn@DTHq#/1*[j1cL0?&>XQ2Kg"r
R/A8nV^76Cp'P++[AF-F,b1P-m@$dG]A5'chOSK;=`p/lB6RZn+3E(j<\fX>@3OEPYhQ@Flu1
lmW@?qgl>oB78'IeX;0L;7nZi7X)7j*eVqOQ56_#NnAn!40n;a@sPRC9;Ch)WZ@*pL8A)uP+
V:eCNY:R?FH_A'$"'(WGBWu4sdV(DYI,9;B5V.<1qRNY=(,Ffd73dEHpAt"/EW>@J.E#ibk7
q+W6Juh4B?&D_T9`Amn!+pT+3LIR\:Wf=]A]Aub[F.ii`R^"EW`c3%,Wk<9jCmic9#*EOcB_4!
pVX'a!PB2W+Tr$/Fh!^%J^.?/-#Db@+D_6SblK-\JKZUs+_:i[mDNpE\'/)f1+f$sgisf+dE
aVF??(4)<)=@bb5cn-e(UpgCMCHfdMb;\iG6k^;WdAM28Xq,/e4%cENm359VQ\Uc#pCtA2(0
/E^TWp^S\QNOM_-u;(=t14<-`G8khUE/CRSt(fDl3D]AX>"'miAsdP`\,[dfupZL>!^*nE[n6
$#$3Lb8\"j)D:4D[/N#G!KH@\)h)Fq4#JCM\l5PtE#5]A&K4EQt;EIK2!p\&$K#8=f\21+j:1
oKWc/CFFG?dtbF?*rDDS[-Q]Abg;b]A6aNpA9@(IJEao6,[6>K$ZO]AgK>O&9K&%G!en]Ad%ES@E
;IqtiUJt+c_q2s_J$$Z75p[I;]A?NXM+C$G^G8UER^?mk534*1P[$_:RCaR8=QbYV?$CW0*N/
XE[s`gj#6/(iDJ3R\g&T=seoCeIFTPTQn"GE16DkL*Hp9]An[8NBeI$jfiE0P`;DIlAJ?rIrY
&m$eKm5#-#8,f$%uE2HAH=3L$$nWW9g,^pINpZ?igWmgWcm8hnA4.KI5cE#rXMG=j9_1EjQf
rh03Xe)EG7oZ,V5VP>@B(gqXk6CmY-oT<ro0$dLbnrM`1b6P4BGI3=ZrLSOK7AE7]AZ)3t:Cf
=p<;mo!F/08*h_@!*eh[@9]A&nCf6o[dnq`%#b'DBcit?M.:eV.:NRB2Be4(8tD'(jFkuLQ$B
LG/t@sQ(h,0Zqo5oE^,0qfR^K\B$aL4ViH2!:Su-C<o'82A&!Up^,b9e&P@@mgtKiVA:/.nL
@09r[t:<?,fi[e[_)%B1(<7N)/W9QQZ2h[e.PCAiJu"X87Zp;9hYQGX-le:$0.hV$jC+2D;6
.8bEek?_`J;3f1.<*U1YD*IPZB.0Veua#ga9T%E1\'W`?QAlSItS9#o:N$#&Q\X6#2fPiH2l
pUJJF*%e!3YXNsMCp^'<WiD=^^?doh6U[Q4)s)d.kf*)m;%ne%MT0tnX0<WDXp!b]A@t$u14U
tW8jO!i=;AD-;-!]AicgVKC3iE7XFlU]AiqrJ#`cZrMSf%g+"W@T"[D"g#=,jTNd&1"/+!=Vp%
g1?NBA4kd^/,LIK,7a^N,LG""oiRiL>8g;\cS%n^2f(#S/CJp&7("$u_2KCmJ6V!&Dgm602k
E3p/g-_Uqe>4Dq*iQ=&62+tEK8Sn(,.L_nCM&pE+S.P1)HS*L"a`C1a]A"A7E%""F=1$=]Afc.
\^,Vn2A[dI5eRr=WLNM2$FZ!4kUA&Sa51"fS]A2SXou&X4<Kg<_>7G5"_.0j]A5A2k4dF`\:X&
ZMe!=Gum[7Bu,;T)n>Aa?!Kh7I:Vb2.]A[Ljk(ma&X!i2g>JOlfVU=&\+Vp;8H\A?0_4?"`:c
Z1XMUt4#U@`*\^>EANTju$e^Y>Lh8>-QhM'&`3[LEJlAD8Q/&i2^Dh@D.u"`0S2YN=o[i(uL
H>a(@PkTMt3KN&4e>a@OR1r-i)\Lp]Ais#TK`LZP$d%"?;J]A_>`_M!0k>+#S*XX?2OY^8]AEB<
]A+#21,gG#DTO9jX!h:eB9LOdcYUP%?;&s]A<Wf+cZSn##CX"au2j?#]A"T,"t0Cj9^$30N.#,l
4+p`KW>\4-"/Tfk1fldNp3/)<>Z<67medlOud;ajFR@dt_48Z5hd.`6Y$T80;G]AH8\:Oi8Ut
f)Sc@e7p^$mC#bn2O:&_IMJ2+7j,7P#Ogd1P^j'":4dFl-SOF=Vco:'%6+Ql'Ose$Cgl#D5F
0:UG2BE]A67UMt08^_trA<&&1Qir2feAM,5O*?k.EuD>dQTlqSaa,<U:;PQG^,sL>JFH7N@bX
jpL!t2aco87#!!h\!CNV(OS>=bFR4i-d>=\jV)II__/*)./Z?Om3]AsatBLnWk[A8YuDgD=X"
VI0Je**MX)I>:#p!2]AtZmoYk%SIdsh"c^PCUsui2OgFp6:LnK,56@&&C7::*SNM8aYXJFp"^
T#ggQ&b!?i\1pA/-ocM]A3soAWaKdja:FajURM<9eYc+g?:f]AZDX!DQU_d=Yol[&OJ]Ad?P<Y8
k*8CU"qY,SR&&?\ko;%QC9a>!C(#!k\JZkH"giCs?Vc7qV+)"SGl;$a_<L_#?b\%4)@pAEJ#
Y[fE=FIYZs9G%IsYW>Tm3r`>)>2Ko_KA@c=m#-%El^Ho@*La+b5rr:`OB,HcEI?cei=49]AJM
mM*<,2)4)W5VfrkdQZ.qPbMA![*.uOaF'`R7k]ALBIK=>kW->*tRS-)IFrSD=:3rMN+5ZIe3a
*^euCHO!`cJss=H_gd^5U18QIYk^9c)sSYa\T7RVT*t#0.@tP:(V<j-tG[ab00Ya;53j0(aB
-]A$oZ!"MbA=cR)W3m;+81kPXBa'34HVI@hSIsB2.;GWrdlh`9Hj3dYn\m?:\pMDX_l.$CLd+
-rKZFam2Vuf!gZ1%"NB!4$e<WnPcW;)$7l`Db;EY47DPCQB&2n]A!#BP-D8=7KQ_*%;cbG:1T
uYU%<atL;EgU5,+I.0;cgJtP`4h0^qgEILedP8A+?C(O:'#7V*hrh%='G8g.?]A=\*>IZ?XDG
kVX3YL&-T/Q$Se(QLUsf%V@:7OF[D?f3%*FH,8uS>MGL\$Fl'Wq@37WpgUhW7't1\^QuZS"6
8;_<,VU(9!^s;lo1b0^>'(<)m8p-[=gsYhMI&9_0hX2S*I%QHhPi!7+hX<Pgt/YpmEPKT2+;
l\n6GFD[#W5bO:AmP@iO\;e2NUm1_guLY9;K%M,,Ynm+G/#8:Nt(o3$<2daVQAjtI1H2fo;5
a0I"F+3A7.@0qA4`&:6.:gYX4P+bFc+3ds9J'$NS7r3NB:Fq72*2$kAG%),7fm[NTE@R9.qM
'4_=ub$S`0BkNP-sE,qG1j5FWLBtW2g5iPN3RjNZkm*Eb;_PM:Li+3*OfFfs[P`Sdf,Tp$10
fi?hEAQaX1HRkQ[qAV6:hKNb;/7^Qa6Y>&sn3=_.S[,/.!'hd>o7r1<B08P#F]A!m"`Grn[;Z
ej.JKPPs[G_j"s1ER?X/./"U[[qj2HkT)83Ig:`AD>#l;)m<U<=6/#\R\4s0"p7#W.2:,Vq[
5uC.rjMJ[4t/`f8Z>3+JWY`=FJK>//a:Q_q'14OFe$<+\)kDpR>,Y5O9rBV&Gq)4<Eh(kBQ2
C;4UK2=4NL3W-tr8PpR%e/(gDm+%VNb#EWZ4-'[^)K@%0AcD$^-T;cXH0)&2oka%Y5Nj9XP;
^l`JD''P<Fcs.G;[ol1Bq;-=+1U`Y/8XI,YTG]AWA#R7"bFYMXVg\tT.F0Tal=6en;B*sHORl
Z>TN@e,S[?AO#gU5b!'<Xe8&+Fg6Y/=KRUN)dQi3MP&V"23SJS??bn@U6_.ic5B<+g&>5gn-
(W7aO6@MRYdfs3gK#K5`3SD)Y%eGk`%msiV3^@1P3j,qW)8ANrmaQJkHff%7`&k=c6BH)";j
?V7uVpE?h>aZ4U10ma!YL$7&2d[NnQ:skFoeeT0`;rbe4,?p]A+D9*[daE$"_SXm;)2_#e&5m
.uG<H,aJcTjg703Cn/p'-jD`Yo@Dh";FM94%82J&;e8`9%/,d?GgacFq2CdD'H9C"L##`K^R
k\en;qM*!6V;kilkQu[=87lN9>"k>@P2/@B'o,K7`FHp$ZL$c5bpDe\g3^B07:^%!HVFmnrV
]Aa%(Z^b7Wb>4WPnfdq$?9QNJRJ)bl6@k</\a$"lV_%<rA?S(bc4L$H=Ca3/E_Y$i4`I?r/.Z
J\6^YNMW',Qs\14FK'pK&,DTIhrJ(+U9VOn\t25Q:WKu:SNEuS78"]A=na%6G'[eYji_M[S84
]At1Ock!PuZ*ePn.p#O]A3aa;Thr5foeLh8J3TQ=W@oa,"88`7P1@l,p,YU(kM2pohtE^5I-'D
B'U2\U8W57/h@pi!^plT;r?X:(t\*<,r3RTdfqI\@"FP_@3(jqi&f7^.9\*?KWq9I?h*UboB
H\W=m_n1]AFAA$=>t@B-<S:><'JahQ4:&%_?`g6'8?$4K(>,\Pa;<TZn<`\&!XjH-"S\@7Qpd
<U/Jk!cDpM6Tr556"?5P>GSiER=V,k[oLue=Ft_0sXRoda"B,msO._%?_*/1(f?S!L_?iFVS
<F:\,cmReHDM8C_YZ<'=+c3GDatmk1s8Z0_Qf_B.kbfe]A4p)?cfbH#E:rcDWc6MlD/-?oP*T
1?jFurFZ[\-d-6&gm)RGOJ"5ft$:\K^:"/Up!@6IOHn7U0j2<$2+m6U0!?;=V<I8i<9Wi]A%r
E4SBf-%KQM%7JgQ6Thhk*3NhLQa5R2*pTsd_+O3<=IA_6,F-*9_u2c:i!/JCY_:0fj/C8u83
t6s4l"[u"c5HI[fWKui)pGpg"MgkMHi=2ri;J<m-0*^/S@>cD7ik_2u:U]A+epK!QHfshQ?c*
nTar6&9Y8=).^/4ooG7jq)gK5akNRL\@Pp0Hj$?NHAY+%U?2*s?@//$U,*gqe9guFdi5_R^S
f;0iM9MJl1_QZT+!^&`=s<::MtpOeHjO+=)k"^3)g.O\Bc7nm+*.rV&>o?\?D0ZAj<WsQK41
A+P;A2E7Z%X`39+"V-'X2iG\>(]AIE'Gd&(72/UF&?S7'rVTb%)((AbO$o@UkEJ-2E!#VEbW`
H1@.LKapRZHu47!-Ci`+KFaP\6Np^qfkGE:=UIf+k6DS[SSdg^0,_B793-sbH98j^QV-NrDr
iJ?F1%<o-YuYO=`]AF/_t#F12glDH2UjAg`d!IdR'QLC7:*k<I8/LLiPZl,&^G]Ai(:AF//+LU
PnHCtGfU?DV=ou^&g2]A`u'8->Dh]A#t;aoN`<$:)/t0uc]AY-^_n,1.XHnmDkKpeEirSiP#$'C
#*Y]ANVa+=D]AW!*7"jQIM:^T)'SDdjK2:?WD4+5d(#^$\37qn#\))3GAAEsl:cX"^4J3Ce%[P
3["N2k@alnD2Z:X!RNKci54QWV=PHqo^"I6\n_lsKR%g73mclF6dXm4NTg?lm9[-*YU^&k"$
W>&ql]A^,P=MTeHLUud)N<rls["[l#ps8Rtq)c^k8qPY_DC+KWIHPsJgK,\j7'QIV5I=*le!h
+WE*t=`H`^TbcGpFK-=2@b1f^jO(J,sC(X>G1q/taA&edhk[=Cmj?L>Of[VFp1uP+.p3N*sX
A\l7aM/)Zh,jnpg@mdM":FdRQTl1+$q[aK&ZZ_3H`VoU^B;3DE"N5G>>CoZjd"hOpGH/sX(3
,2/FqjK=Z#DfC43?hZ(@hdf=#/j7u1i"QQ@(1>FdMdKB\KBm(3D&Q^ia^ZM*ad"qopq>'Bmh
CQi?q5$?uLWar_'2WFBfrnI=-oc2-&?@pa@nGihK<No$MbNb,ko4B7SI\6tu6q-N_F5^=O"[
%7%?W=68%#KXIP0b!dQtA+bO3&'7[;Y$N$Q=)ZFWR3m>"\QU]AlnA[Y>T+Af$1[CeBT9(<pk!
EGkcHMis`%(A,o\g=*bBQ4Jr>"CL`G:G5;<nMt"Nc?ZYPj$[6>-]AXQpuf(J4[1d.%]A4RkDl@
#@<^ZjV^8bt`+dQoR.mH1q@2":-b'2KMmlMCiV#^%^F4'pl5'n$QL#F*14)9hRp??tVRh:)<
Y)rGUDmfmLmc7?8>Z$&E(2C&JNg%"&lKT@RJ,%Tk@cH+78ErX.mBBuCH;OnHhZoEDdE4WY.s
ln~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report6"/>
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
<![CDATA[2171700,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[13449300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[2018总计投资金额]]></O>
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
<FRFont name="SimSun" style="1" size="216" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9>!8;eO[r+UGGTd]AQe]An0JB&cB^Lq7+UHm,)L*IeEIbFYqp:@7rYk6b:>l1L&h$Wl)sEcC2
3-n<?QDLQ9$Y:C,+fp"ICR2)^)Yhf.%=^Op$==#U/:#^<b>i[;qnO@pUXs1[uPWmsUdSc?Xm
%SDQ?iah'4&V(R+:jeE*5/DL+#Z`bKBTG0Znh?aJirtFt0nmr*4H"cfU)h1dk2*UeUr\`YsR
]AY`&);S*U83$M?^f@@^D*l3,?pQk#Zf'>iaqMPhZe4LV8u\GdY.Gh3.`X0ErnlinQZq)LWl#
e`/9cTF@C`)B6?kWS7JS=qrH4n61HV/B&04EseYF.(@SN6AK"8<",MVt5aqM.he%'na]AlIaS
[!an(o?O,g]AI&q^W0k>XN;?jsk>L+mFZfSKbOAT?U'U#_fH%q$QoVb3H^E]ApA=.:b[H">-.1
Q.11@WH:6=t2t[f,/B,JdI4RJQC,q1u)Z"EDSg3>qO!WFQ(`^;=")<9D,N[X!eZMPXJ.)G`t
)dRUlA-P*#@phq0;[G4c)nTjre?coo8_EmQf;/tntb1LN:EoVjD7+q)HN*Ob!GrYTW:fq\tf
sO\Yp4dB:Z`c"O,Ft.ZFB>g%hgUuCD0)"&l(maqc3%dU_j2mWGFiPRdX<sgJ:;2oM[@8%;>_
B_DQ9%-I<#%cbFP%2CVL]AbejWlCX6qcmrffJ>,MetThqMZ\>:CF[DULNL]Agtg57nC8$a:C(I
U/[-Gb2ppKb;TT%1JQ?*S0#B''A^(']A]Ag39D=u[Pns6kTVY)=E0i>BVp%9,%Mpbk,2XP*RNE
8?%D"#qj-s_>gI[>6S0OlGE-L%>g"ah8!SKm+B(Ze9>X<YH^BK^o#$2T/mh<\Le@(UNkQmL=
V!r@kJf]AR]AOP]Agj-T9"dHgl@q&EK9=KM76D]A.`ULS`cHp)h1`&R:\Comrhi5h0[MhNmS-a6"
X$(`8CA3k5f".PlBPX]A2`T@408]AqJP957%Zp/Dl^ZUkSDV?]AAT%?2UeN)g(P]A?m'<>"eC_j@
K:W(]AJP:r*4rOQLPtI0sXMC5nR9YA."Oajo^#VO=O>GJV[1cY,7=K.V!)cRsFQbpVM[7*PuX
7`,Q+VE13nTT991!/0nB:*&6k4TGL^ghOrE!.UOW8I=L#L94t1_`g?/EdbGl$WK>L!43'ep;
^DCATm<d(jCE=h;OCg398T'e6^@ua5-gAGhC*WXR$%oSRL:Ha'tTeLIpKGIuX,PIi6`b/oH'
TQo"<qY'H&grJ^S4JiscPHgM)[npY5gjU93+JD.J*9:NP##2IN+fD^D33]A@]A<^>G8rk7.b:6
&Mg>;n_/]Agaj&2ln:bqG<%)(;?G`FI[,QpSo[ON5m/N#5)t`eB8G<q'a1ZkI6!OeN+QF`Dk%
nF*cHP^YNC=pRdtm#S4`18P`Y3\.Y)AR:-l0rCp4:k+&jig^R6O)F*5n?-%XN%-6ho/^1<sZ
/E,5,AMVAgG/'J1S=8W6/4mEMX`eCq@U^jt\)BG!(@07Fg"'YO&K5/=^($QQ*3t$Q705f1jm
toZWbfq(;NsJn?c*RpFcK*9#:*-?'^")H"_dC$Gk9RXopamnO^b*rfNaa>RslOj]A0l/rZR=0
UIf5K&#?W0X9%R$W\^kuo&"]AmG,.C]Ag<Fr0mocQadKr!f7-P5$Mle[6mq'fN-[n^-#=BIYrQ
"Z_9?UL!8+_`nk9W@;''TXRJR43PR6VXYZ%hOi^+^6orp=k59"m_Ae(nJui#=M*SP5TOeo2_
rAK)klfi-m,K8T8dS?)T';D'Ef=]A#qN\LSNog<'&3WMLu`7'0[rJVJFI^,O&>758um(5FWg*
,R$T"S545:SuX=HT/+tBNYQ?+MD#:F+/1kWbW"'MrqQ0^qp`8!11]AL_@64`L-kJObKg\QUI*
.3jPruaumI"`S>h=6;"nDe'9X'UVdI?(en]AKf#aCmJBIS30:T7cJqHltn"?E^(*`ucQ`Zns&
-RJ?We:nN,^DKQFW?Y]AbGHo<fZW]A3eU[$k`UfnWCZkE[+rZp_&s>1d<Sc-5/9]A.r9Cm_:=]Ap
^(<'C;K`1#`l0>-]A>h!>.Y:W<S,[-AGcb4e`?iI[4<&ePV"-*18oV>'(kEf-qr):fDEJO!5]A
S7m%>dJgmQ)o+8t.]A%D%5]AR.Y-7ZDp%<>p+f'KfI'?=@B-iI2#UgP?Z*(*Rm3!H^rL?Tj*dc
9>W50X&AUKCCC'"9u2tgJ=t$(/&X/qqAt\aSL]A9$3KM$I!p3Mr.ntfbQ;4]A>K8B4Kifo0,X3
@2+d&n2`i;NpfdWDBAfc+!+HT,:k>2__r85d![U8CWQ)s0VgH%]Ak:'T_KEKHA/`6V;7AnJ`S
*]A)S3j/h$W:QrFL>Qku!C_*dA+2*7#H/o6od)]A6#+)\32q$9a&8"VQ?/A/ZbP$tu/NjFZpKW
kLfc[%E_gJI:ioU&3ff"L1M!,,Ab$XZlmkcW##3"%+2b$58*?@VG/u5liAYnmRApHGUtl1R$
2FmoZ@$g6oU(TC>9!1UV.sd#lBD%!iU^A&k\"->V3HDu,Q%!@q`+i_RIU7YdKh1WJ#/62.fG
a9)iAi+*gI>051tb`^Jns,*iOlNu4,"<-3ZHAU5(aQ732^(V6FW1XOi4jLg**3fYr"M&sZ@P
T_rSPhLW)V28oX)s"fPqe,Y2uWZ:81H,t$E`0DTb7n6OOLO[2l,#tTc85+mkK'm?0m62`Ek;
`nsQa(jdq1H43K7B,f^E!(_kaHAL?"DVh?uIVLNNtHH:>6_1`\l/9bbQi22AA63/X_'rO^ZB
&R1=#ae9L61u(Wa=[de;8FKh`#[,HdD5*RSYKeS`0,rqjq9PA+cfF'/>rI>"d)bNUcHoic"K
puNb-#P6$Q]AqdD[1pA'"M0Ts^k:D=Bos;nA2k$7)eOO@H@9PcfESC)J$YL[(\8]Ae`T[l80+Y
R>Jt1RJoZ5@3:SbA?Fg!8"<Xji1A\L4d3nFd=o(#e/@aiJ;_\ZKq:P&-qBiQ7fj<a':/eMLr
YtiTR2ta\7>cp<k\p]AXS>Xp=YjH4[=-+6&SZfF3a_gj,7T\+Lkek;Hoq/`+3=D`)GT1u;BJB
E!FM#9^o(C_Z)@(5E4W#>:sb-ll"/NnK;((]Afpp/IV3DJ,$W"gI!-'QLPk'[)nI;np@kJS/3
S_GiB\_T?o3[-J`7\.jTrie#5sdjV'P^jI5+7FT>Wu]A*U\XP&Otif%C8qkjh"gH&o1lJE;,3
XBp)qi33NtN3G:oX2JA:gf_K9LC\*M"o?HG`tPZ%Vkir/B9R?i_$@u!4A(j\+8fYUU_n=9$B
TdaAAbt6d9"c_h-"m($$Sg0Z%Rp0d?bPdu+,,omeI3[3aIA(<U*IsdG=-*%:V:K1U`UM!3Fn
/9@S9V7u[pgrU152:iBq#o&/WkSao>$mM:He`-e5LrfUSt[MRZ)X&m_.:a)lk7/+'k+mlXQh
!:Ol$'RN5oJ:SoP=08#/l_J<@@5KW12a"WS&nch\+*cH1T'^;KRDo#f/5A?oVj/8S=*9-Zsl
r[u1U^)?:4a>nHQ[#6Q\9lRZ[SA;a:BM$C.SNZ"n!O=3-F&`$.#UH)l;;dMCLL.9odCjWS;!
oAd'gV5h3A1J\TbUa=p;2$ZIW!T%?67]AD=F'(Gg\QLDR=K8H[]A5J6gEm6fQ2^Vk.W,CT;]AdB
0*bj`hh4K^#D<h/Wmt=qkd&mRZo1qO-0ajH3&*mql5>)XoYHk6^,'"7YWDC6ab6!dOIbaN36
$<iLO2!`EluVf6Q3W5eO)I:M+NpG\sqW:@)2`N.?ua<R<;',bt6L&6V<M4?]A_7eNHG2ZNS&E
`mqlr=W-==+P9n5gO4.*8OuUi$>aqFqOGr+:Ug1Tc'HIT2b(D6QO+K5cTH)"<!$*q0Lp%HL=
<,4_EeMcf):Im.4>&($fuLqcoiErf`k-Zfq_CbV-<c,dKu*lL":EEspHcp+/Y'_R=5]A<p39q
UbYH!bapV?X/TrEcN3\g1+a^%8aO!-Z6lLktc,uq]A,)+["dbZdsHS`tUC9#=]AXlr$=u8ojsG
Ff:Q_mWR=L.s*H>`*8;9WXg5IN?D-L$Na;'ePcN2ctST+""8R%\Vcc4W,0`:GnVa0.T7l:P5
D*9W?j%oAKM2KX43.XKO]A:"172<bAXVB20r<Jo6<n1tWEG&B/EB#U.+QDsNRW?kI7g8Ph[3W
q%4aQ+!J)RD:H1kH2Ue?a>*qck$F=#>8a:_`p]AR&P%kAo67@>`I1ffbo=<:kS"'-5[8B-(/@
_,7eO$J9JgBYh)qB,sH8UIO`=^9Z=gHqKa9W/)c6Z5jd'b@[e.k6&#A5Trc[sspV*JP'r+A8
gpcs[>^f0_',ZQ4ZFQMV>V&Q9$gIs)rroAf"=)"uC'hX5tq5C3?SkJmO1)EHuA[Qe:$bDPJW
js.sSf;u9%"!a<[#3![kE\A(CTOBRu+cU[3ROc'1p"k;2FSM!;]Ae]A-n[;U8BS8YWsJnU>Sq$
g8GJXJ.%AP7`l!`-rT"c-j0oRQZS-00A.ENqBR'Ns.l[Rjl<Jh1A[iV[Cs3^s9VBSg^eC-e<
Uc!1VKMg.dS1%!6;-faTh9>ki\4DA>Y0_<7mG4?ed'KcabM0X"_A2t4/kQFA]A]A<(`oe:Pa14
iHM^I(i0+EtW'$?V0fNYnik%>!qKmkqnE`Bu=kj-51`k%rT5n=9e:`E=`4]AKeuBZG`*(d?dP
Q-=G\Npng`>+4P&B"I*k#k_hfh^U!d2UQ`sY3$o!Rn]AH5PN765ZM*gK;MD>]AW/)qBqYOK`mk
nMic3_>UBMm"JW$a3t2.8LY-a'/R:f8VIqRd)QZr;_g5tSFmr35&no5cTP5I!!ACN&pN$@qh
(`7?9l;uc-N4Lg[NCVhu=b~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="354" y="67" width="442" height="78"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart0" frozen="false"/>
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
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.catalog.CatalogMainTypeChart">
<SubChart class="com.fr.plugin.chart.catalog.CatalogChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.catalog.CatalogDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr themeName="" nodeNames="分公司" nodeNames_customName="" nodeContents="内容" nodeContents_customName="" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title=""/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList/>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
<NameJavaScriptGroup>
<NameJavaScript name="char1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1" animateType="reload"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="char2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart2" animateType="reload"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="char3">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart3" animateType="reload"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="char4">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart4" animateType="reload"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="char5">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart5" animateType="reload"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="char6">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart6" animateType="reload"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="char7">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report7" animateType="reload"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="char8">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart8" animateType="reload"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
</EAutoLink>
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
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart0"/>
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
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.catalog.CatalogMainTypeChart">
<SubChart class="com.fr.plugin.chart.catalog.CatalogChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.catalog.CatalogDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr themeName="" nodeNames="分公司" nodeNames_customName="" nodeContents="内容" nodeContents_customName="" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title=""/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-16712452"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList/>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
<NameJavaScriptGroup>
<NameJavaScript name="当前决策报表对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="c"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=NODE_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart1" animateType="reload"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
</EAutoLink>
<AttrBackground>
<Background name="ColorBackground" color="-15329992"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="241" y="181" width="668" height="277"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart1"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart1" frozen="false"/>
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
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.kpi.KPIMainTypeChart">
<SubChart class="com.fr.plugin.chart.particlecounter.ParticleCounterChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.particlecounter.ParticleCounterDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr title="" count="销售额" count_customName="" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title=""/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-4652801"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList/>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
</EAutoLink>
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
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
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
<LayoutAttr selectedIndex="0"/>
<ChangeAttr enable="false" changeType="button" timeInterval="5" buttonColor="-8421505" carouselColor="-8421505" showArrow="true">
<TextAttr>
<Attr alignText="0"/>
</TextAttr>
</ChangeAttr>
<Chart name="默认" chartClass="com.fr.plugin.chart.kpi.KPIMainTypeChart">
<SubChart class="com.fr.plugin.chart.particlecounter.ParticleCounterChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.particlecounter.ParticleCounterDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr title="" count="销售额" count_customName="" customName="false"/>
</AbstractDataConfig>
</DataSet>
<ETitle>
<attr align="left" title=""/>
<moreAttr isShow="true" useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxPro="15.0"/>
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
<FRFont name="Microsoft YaHei" style="0" size="128" foreground="-1"/>
</ETitle>
<ELegend>
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
<attr visible="true" type="gradual" layout="vertical" horizontalAlign="left" verticalAlign="bottom"/>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-4791553" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14374913" dist="1.0"/>
</ColorDistList>
</GradualIntervalConfig>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
</MapHotAreaColor>
<FRFont name="Microsoft YaHei" style="0" size="88" foreground="-1"/>
</ELegend>
<ETooltip>
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
<attr show="true" fixed="true" follow="true" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
<item>
<ETooltipItem>
<Attr enable="true"/>
</ETooltipItem>
</item>
</itemList>
</ETooltip>
<EGraphics>
<Attr timeUnit="HH:mm:ss" themeColorFirst="-16712452" themeColorSecond="-4652801"/>
</EGraphics>
<Label>
<attr useLabel="true" intervalAuto="true" intervalValue="1"/>
<itemList/>
<AutoCustomTextFont>
<textFontAutoCustom auto="true"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
</AutoCustomTextFont>
</Label>
<EAutoLink>
<attr intervaltime="6.0"/>
</EAutoLink>
<AttrBackground>
<Background name="ColorBackground" color="-15329992"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-16777216"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</SubChart>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="273" y="112" width="608" height="89"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report10"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="report10" frozen="false"/>
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
<WidgetName name="report10"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[!JLT$rJ=?G7h#eD$31&+%7s)Y;?-[tDZBb;/I2O\!!$H^lGil)!_c0R5u`*!mG7)H9*$$QiI
chl84X:X"<m1V6AS9>1=s:u59Q(kf5A7ko&op/7i'>&ol;[550<D2?.[O^hf`;;(<'H>.gGM
UJ<n:4hT13L[);@kY3O&snd?N>BW0_NJ(B[Wnc\C^BUIT<s#,[W!&Vnrp#@V(%5CcVg^4&Sg
/[qg$NTi:X4;G(^l47Y`'S`=Y5jrPgtdgo3T)OkR'MPXGL]A;:(;4qn^m*p*:8L)5[<N1sKM?
<nWs3RFoFuf^q&60.45<j3K*LiKJ2,;b\>tqUAC6IY!:8FMfa`5V8mEuup`fc.Pl"U!lZ&n+
NJ@KB.<i*1%H:uFJK=fqKA=X>eW9#b0CAqHNSMWF[MF)0$[<245NG!d\j>+(0B?C=I`Yl37<
oCG^nt-iLg'k#YEee@#/K#/FfTKk@.&-2?<dZ$)kAEeDg=TL!'2e-Y#FE_ja6e(\uKhh?'QN
#ZQ9n`3[\Bdh%Mm+nc7;$!^LC?I^lHL5R29MT_4W2E8?^=g[]AmlE>G4Tk&.]A0^;Q_>(M<_Ok
XaMC>&X4f`[c$ITJ^c!<92ftO)B=l-:$&A?>3&X:\HqH!&U!3gtS&pbdTPAco?c,L[nN0]ATN
8`_G_aJ[M"K;J&qkm>OIVC-soH2YFuq)`.>KQ[\O*dlDDemlaY"&EuZ1Rgq5dMdTDL7.iP11
1qg5LXl$GVksk_QG-rih5WR-EgUHjOFl,`?Af>Ab1r8b_jSj#Bes"U3irGNbW*O+/562qQIl
'%!,4>L3!!#SZ:.26O@"J~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[!JLT$rJ=?G7h#eD$31&+%7s)Y;?-[tDZBb;/I2O\!!$H^lGil)!_c0R5u`*!mG7)H9*$$QiI
chl84X:X"<m1V6AS9>1=s:u59Q(kf5A7ko&op/7i'>&ol;[550<D2?.[O^hf`;;(<'H>.gGM
UJ<n:4hT13L[);@kY3O&snd?N>BW0_NJ(B[Wnc\C^BUIT<s#,[W!&Vnrp#@V(%5CcVg^4&Sg
/[qg$NTi:X4;G(^l47Y`'S`=Y5jrPgtdgo3T)OkR'MPXGL]A;:(;4qn^m*p*:8L)5[<N1sKM?
<nWs3RFoFuf^q&60.45<j3K*LiKJ2,;b\>tqUAC6IY!:8FMfa`5V8mEuup`fc.Pl"U!lZ&n+
NJ@KB.<i*1%H:uFJK=fqKA=X>eW9#b0CAqHNSMWF[MF)0$[<245NG!d\j>+(0B?C=I`Yl37<
oCG^nt-iLg'k#YEee@#/K#/FfTKk@.&-2?<dZ$)kAEeDg=TL!'2e-Y#FE_ja6e(\uKhh?'QN
#ZQ9n`3[\Bdh%Mm+nc7;$!^LC?I^lHL5R29MT_4W2E8?^=g[]AmlE>G4Tk&.]A0^;Q_>(M<_Ok
XaMC>&X4f`[c$ITJ^c!<92ftO)B=l-:$&A?>3&X:\HqH!&U!3gtS&pbdTPAco?c,L[nN0]ATN
8`_G_aJ[M"K;J&qkm>OIVC-soH2YFuq)`.>KQ[\O*dlDDemlaY"&EuZ1Rgq5dMdTDL7.iP11
1qg5LXl$GVksk_QG-rih5WR-EgUHjOFl,`?Af>Ab1r8b_jSj#Bes"U3irGNbW*O+/562qQIl
'%!,4>L3!!#SZ:.26O@"J~
]]></IM>
</FineImage>
</Background>
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
<![CDATA[1097280,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[6918960,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[XX汽车公司驾驶舱]]></O>
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
<FRFont name="微软雅黑" style="1" size="96" foreground="-855824"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9G!?;cgCl+^CbT9!#-r:eBkneBV/b6q'FKN_X;HYGj<XWt9"sEB`7M9L9"W>GbDW1X:ft,B
WjJ>*4=`a>7"a3$mY(U:_f##U+p6OoU&djP5rBVX9=TF$KNG=7N?hOZuD15'sL+>JKi8C[^?
ES_JeZ^9Hf)#_M3Qq8S`0+ilM]AJmA:7b74ErXHc<<6I:bl2n*W">tWu3K8Aa1J#EYMXl<eeq
l.n&\mH=`/KXVqo&\.M['_O\(B!9\^O;N_l)jC14#*cWY5;3@6*I3#UT++/[0b;q=%f(gZ.n
\mPRh]A7\RX67/#tjnhlQWE0fOS:`BPMlT_f-DjoV9.)8bg?-0`GtBT9+Qn)!rXLY\AXF4N>]A
6k)7=6S,T&]A[4/Bia;]A\O&+_1qVk:ApH1GX`?0MskFp#PSPRJ-XQkKLb*d":egKWZS2`qc'\
[]A2XrJDIc:#6*+u^K/(<JH)G9$qZ*Vj"#CO:[kIm:P@Eo)1enW)0qDUdddki23r/;5CT4XoZ
:gK316%4<(?,$CVURYH]AU_*R:X+q(SbE*>sDFcV9>bn\DH[&NH-f@Z%:n(*cZg7$6Bb$P_m=
a*.DJaaV1Dob"$6Iod2'$lA%kl)d5[1t!UXm"gq9qqen+!$X)I-RH/TmabGO/:jO#[L/ML#U
VYMU5XtCI6Ij*:N[?8XX+21QBA"T@Rebo7o?,o%Vl^<:<_.9G1ZM:bA9.DhpCcA\!3FI.4F;
G#%Qro>)gU\e=Fr`n#sd[>e(VUa>9rN]A>,&C$p'_TT)Ot_U'`88!V@KCu'GSaD[eS-.GT@L>
_V14V:ktd1Xe$i=od@7]A8uTG""(Tqh&(<BVN2mkgS79e@GP$Fuh^?>,o_?.sfKH<Z<lQ:?ta
HAb3_OQeUqJotm(1\!$gn[NKZ>UYcu!OKjQ"&>1Z1Z`d"5Ts(T^VtdaXj'J^`7u]AdibLWdk%
(3'25kPX=U$H7Nl)fo#M.;Ce&t'TEfG9IkCku/ZRQKDlPaN>]ARod8gFQRqMCSRT\QuZP%";U
TLK2C&UB"4;#mcgW#mR&SYnuk#>k;k-mlWWqEfAa%9MJak0nBl[']A+dj9O2Ise0N>0B'I1%'
[QJ.B@V-bLj1^KokY$/78%3=g+W-?,q"F#`=Y6`7DXkK:q/mb>E`rq.1X7Vm#Q`P9=Qo:bCF
_le*Sn(78kaAaGTmt`68uTEUf6JIemiM@Cn<`;9714]A7Vo8r74?C6&b]Abn'+/MQ;L,7@<3&k
g-5AN2qIOUX>K*`V1Fa4q$aFanFR,M(Ms;<PdcG5/8hDJ@GGL8L)cS>`Atb=1&3A+Z7.qVZ8
c2?:MUEjR\e-,#C=r*S]AZFiVb.qTt8IRqc!3o)6,a*N*/-`Y:X@hg$bZZ8()9%P\L4/^VV(^
=B_[^4o@Fr@*ptP(e;QF;<=@VEYlU2.Z!QebK1Y0QV;7fRZ`/a9(]AI[De$pq'ml2+'`+!e,>
io$K99B1rBmN(L0[>Bpcc=S$jrU<0A-6q"`8'J8n(=g8Y"og9bn]A*NJFHS>@ki%0,kSQ4-D4
*(nK$q5Q]AjFR**$3[V(jT2(r+r[6adH;cLF-;3OVj5!B:d>m\8sC=;(/NhP\)P59&jL'*k_&
WWL4RBZfqO;_C=+.$kjtohWoa)B#8!t.iknei!'V3ZFU8Fb=)1tB>718>HObMR;-II6>2tkg
coL"13^YJ?^;F32YQTF`.o_DO35++r!KsDb4LFF(L&a2RpaYI`hfJuq,C:4[_GeMM]Am^Wi%"
hZ.Mg^GPYe:n-c<]Al,XFOsjLIlcM-ZtCcKMDTRn_K@Z>t[G9eIs#-O+%tjhr\;bfl^jrA0Md
EW/eX0QgUM"]A6iIHFqC`bJ%ED_X1TG5cY]A`.4_orct',eT9F]AH9>J4o0d*I9\b[gI7&LhK<*
8$iRGURHe?g)Q(3A1Skc1;N]A:k.8pMt5L%Vq-#k3B&bn)-B.^U30\Pf#\A=13LHdG1Stnh0L
5<%HEjZPC/,C1Rc`@`oVYJ&m2#Glc<!NbAU`V8j,E,"kgAnLd/%4elM'=b8K;@!#LYn0kc!"
uATXlI#n(I\0ThKkA"16Y+bM4F*7gV;dr&]A77[JKue$u0o.Ck&Vs$5V$Y$Mf8L-cr\c#,X==
aa]AQlHk8i4`hAbiqHEUe>ehr1eNrje#0S6F)N('_Pn\@,ZIo'<!NP#7D\:-7!qTk_,4:4?^u
;cd]Ap\JsQOShQ=_r4,e%ZH4[0Ka[Dj1btUbn0"(>-jYK>Ohr:/]ATL*n29iBW0uj/*UCA#i%?
p_lW&&J38fC*uj8ZEmf6/sX;&#BI&[u>`g3K^)R/ug!J@(L@UP!$H4&\OTjZM,knLhcLe(EB
5QJ9*`#7P>&3KbnLd1m)N/dJ,5,:d5\-["1hW]A'>M]ACac?0*r_qX%Y@GeOb-hfoG*XYU5TqC
o:$g`u/U3ko.nHD=Q9;Fo=&%c:dqm!9-iq"[;G[+3-6s!lt:f;0Iii@Di?HdX*q'U%&\J\hi
7?3@fp\1;P?e2N1R!$GMX'Q`"V9po6tECr@hMGW5s<"k94\aB.KWpC@ls=\KYbUtF9kq/P&J
p<\(?:Lu)B%<;sF\#Ij?dY768Rp9(P]A.<#DJSjp(1?j$]A:faD9AAiIO3ll6&dTL@hK8&J0qt
hA.R$T=74Om!m=o5c,Q4+79S'Q_%e$_Rb_>6c`8u?B%<#Cp,jU;*S=UEPA#In>?1.8Bb/RTO
cRlhS9L:n"0$gs*.3&'HG]ASj*g:O\QSFr/bj3'+JpDhPc<Ds7IMD\!gld#^\5/7UP#+>Wb='
s6WKIdDaIgPQq`0&P/Ws,+A)Vb,5\at:-6<cIF=Mfi*61f!I^n,u=,:t)Cb/2\GjLjZ;l:mO
?dbW7k3.p.*`&j*,(\bKJ#q!:h$VG&sN-*$-g:>hCt>RloPnQ&bRVAXU!@]AWKcoL^qu#L*2d
m^5X3[>6RgI;o*`qcdkC4>^J'`D[mdbge.^"8^iO3g!3]AC>=4e*S9\K:ZU;l44I9#ZMh'%h)
N.Ec3fk[[(lET0PT&SarkD>@p&?S)k[e`-"4l.ER?d\(/&i,^0*)1:fLR?X1<Lq]AYPXmKNF:
iOjXae=Y#;%7VGTH^qbl34T_!ANZ/i0<L[efh.,a#?<_aNaUh8;6;n-04\C+hMIeKNguklkZ
(LQ>B?jWER>*PtU#:<G")adVn$A1)]A;c1::]A7K&8nhm\CQ!aRBNsNi\>T%tq*UEbBP!X(Z#X
d`2t4%)N['QV$3I#4J&-`b#SHYF&u<'P]A2?b">sYOCbBFIpqM*nb$H$dVE/c\L8PX:Q)PlV^
'Q1N[I9TWG;#WVIQ_.V6c"`?-Xs)glY9hu*3pB`"V9QS2Nbs?S8)QCF5oh-DW7@WW4!;r5Ci
0jNMhQV%&KiaaLS=#-:YS<q8tT[Z0iP5>CuTIS'pfe*eneb8iX9B?[rX0u>&aY^V<24ZS_TK
$MOCi^M"hp/$<1lT,buTkHpZcoo^P(_1((skUUm!@NkKOed(TSG*_R/;<#VMopkFA=(tfui>
RnniC*GH'[*C5j400+Go_#K_&Qg?C]AcoqUoQLC<eBJM@\ifE"G%5^,=0=M0CUK-uaG<F8[O'
_e;>DYGQ**n*]ATE)[=$W.e7qn6M>;cpcUa1o6?)QYClr<l[dKU>:V>BZ,dXHHG?jVPiqFaP$
+"NO0eB&jt0J-@h-a&hI=URY^n.Hdbii<I!i:-$G',";[c8`@(Ebj/g/hu/(5)R,k>sZOhTg
rk)h/P2!"m,)&2-S2!02RC[obp\Np=<j0_a-;dokk7"q7^3:&q"h2FM[,Di]A\$pTOMo&Pm;"
r"Ile",48iG81"52rd-<`(b-_@$+F.!0CK3`[?i'0<07^7@CM/+juCW(p#P)n*B%=cK<2$O9
@V#]A8!?3\Z9#GurZK"nY/ni:DEB=I+Ep73S1ghs[\dOrV"LHcY#4$8pS!2Dmnh=?Mdp<^NF\
Yu.9JI%CEMZoj0_t8/^&m+=QU4DY9auqf$VYbNR102lDSqFUbBDE5/=W(8,.@N^;*LA%CfPY
^&MNobQLq?Y5!2!Qq1&s^iGhYGK6H^IWS@d7DsMa$a7d?W!\7\Je:>Kmo&rGUEY$V5:_CLZp
OQYlA-!Tlt=K,gbcsS*rE"?jklEoL-*khk.SIrbZ-t=VPs5a.XPXThjWcE6L8aTV\i;uJYG'
`Aljt/9(2)UNE+#C&.0B%_<Z`dKkH/9V8ofe%Vt4WZt">,Y([C+B6t>b$Al^b7pNMi$$mi:i
dJ^$GQ=Kfd<Imq7A.hSR`[@Wl7XG3Y7Y&HK3)u9@U#dS8q\U<&L>sk"k_/PDC^\)c3r[X8?Y
u($@Q#Sg4T/.KPs0ObtHLl%U(c"R`ZM$[kR4-%%.tn"Eb8*DLLO;5R3Vhd3;"!ObF!?Y9aV+
:sj!`cf!t$Ma7&`Zn/:8'\iD3ds"IPludj'N3=6+Kn)2Y7YtNM!Y%,Bc%iCGXsB"ES+f_V20
tL2VmrF:s(h]AF32CO5Ts2)BRf#&5V)0Bn("A<7b(Rkrk\/t@BbK<OgnEeKn3@]AAc;pJ0Se[p
W"0j.[HJ4A%37<i/f"!9^:))@_^kP^kfEYl3B9*!:Ag!cb)>eD<c&`Tbgd"eBbRR:;G11Y3A
BPLa4dg(t@G9&,#0Nh+Z)9_f77;$7s+C8^?oIpGThn.J<D&hE`;WZd_+k("1^?:=DC7$_d8@
\]AiIWp88BKMFoE:rH0>3Z*Y;d>fQ7%uP[F(neOf.QW1)_F*Yj/V"I^D=gT9T(>;t73LhOB?9
rZd.Wq(B=m6SYF=l=<TVO*<HmcB]A(s%HV@mAR0SM3uP!1VTR*Icg`Bhdr/8sMpYdq90_L;@o
%;Q&sn&NH<B!Q1pDEI4FUA#Zt+P3"<5\Z252?\n(@cos&XmKa#AXY4p-9>2lfr5M95/gW4IJ
kS3MFa<C2I-plc`*T>^R)6I50-?r/uI*"*h9:.h]Ac[>uWL3M]Am:,OdSj!h=$ql*HXbVj(uY*
OToAn&agU(J.F9i%i^LJLY/u[e0[D[7`obb[JTqVWV.73R\Ho5#C^nBVPBL-f31?M3j\6k(b
_oLdJLER<YX&*j>2KJ`dojeo;V$D!8\WZ:H:WMF62QR3M0q<HpSbFLij_Q\+u)>Rf!6*^D6u
MHhFfJk-ud!2en4."u50ZQ0_Hi9X28-@>dk.5h4fNaIL^ZKUt&W5Zf]Aj(ia3+e8,9+nuaWjA
^[cC"#K_)(=Ur?fMVn9M5FWnuUjfJQ^G>KMU'$iPGbp%;bg68B_0QK\bp[:00Yu`QZD(N&ri
)fk+>:M"H`5+!@@EPb^fL,Hu!A1BpXP40s#j+JWPH2c!E*JKUg;5\K,TW(i5CqB)^*#9MXdi
"?D"^iC3e6X,Z)/o$u&HPkOE&#\@kr(,.,Q5M[#<sN$uW'saraHHHX5SP1_'XE>uJs"PKi(%
h,\:j!:)mHC]A=5rWNhZsV+Aol8iGoeQh:!8fPY[EOZ`W[]AK(eMs"e^'*D=X6bEiUB[q(jQJF
4R5`ob+`Y9ots$k'#.G*\$5su$?Z^:kh?OhI!$VQK(g<1i'7c2&A&k9o!stp.\?ql/kM*-l)
;/n4B]AsN;f\IR&tc&lnk.f$gKYu!k66KBfMopBE-OTYHjNnep2cl<)t5,MW7Eg=>S)`dF1_r
A<up5;RY&hd#H(h2G'0h-GQWC8R-jXbOS(S0:s<%>rLAb7&`YKDdPqe@\+.V!94<Tl[F^P_p
V&?:`$W!V3J@L<2'<V>hou:B+'_8ZTG^-mYB?B<kbY2+)12O/U">>RYDYa'S;/XPm%"l4+@.
5+AnGjOqq7(.7J4C&h6ES2<TU>B,"&[h!0iZq-rguf#hi\QU^fZU1dC:##2Hb0WlWTZ%lM/B
U9Dk+.5jqNYtt8>aqut`VlMB..n71dVonKYiuDuu&"]AmI%4me=3j7YtPB5q&\H,WAERTB;L(
/#_O3I+u?Era#('Yl`j/7-5<=AuNXpo&%oOBpKll`T4(*%Sm.dcD)TDr$FVh%Y]Ai.ErjcZ"i
V(5O`GcNJk]A'rp]AjZ3/'s1P,9&CCjk-V0&n+Qf\:g+E.E=]A+b28^9Mq[gLJ)#11M#s+Zo29Z
?'uM8Gj\fC:KM$FSn]A74KLh[C^=Sl>os58h(^M`/K9DY45cLTM=*?fPd/UXB7pmUb3Gb;`Ds
IkeTq]AB*&H0@O[LOs5)ipOfh+Br4bK>4$q!ND4^6SW49XccD_?,BG4brK=54XWORfUXdHh5:
FOsJ37i?q:@"ttNR%Z-M3Y"^+DYN.a4:qXn7/W+qpN92M4masU]Au3)=m`_j]AV7UNs/";*fjm
96WVJtUAO)cQW=np6)g\:SD-gq(-L>tDEVJGC06dte'':VEn<$sfc"2U1MG`$P_?er.Wai"m
kRjC1+@5+*XRAu+jRqE\D!mV?0SNRTRIP*ONFVLFgF_>=<X3.;Y1dP9Q<91\f$S8]A;K1KYk9
#Nn0/+7Jcc3L95qg9gLH6#5R3!6+Fo@U&Pi/VA=N/RZ&o8_pkr-fFSRr8K8N9\7@cpg2eTai
ZZJPTLIW4,b$JU>+TJB'XJeBI)/5DatB'2YPW=3B_u)m`+7-1Y_2*SbF#'=i_G,4bn/KgG_`
Ne-@fbF(<mp"bAXk,'H5p>3r>E7]A-Wq7ZNCRjOLFAl$0XBH0G+(!HO'.5"Mk.-3$^?51i.JG
IhH3,\g:Vu&t]A_*B)o5R0dB7^q!K7,T`_bd<N=&I'SKI^S<Lf:D4=,kH_s@rF$uht+RF:]A*E
_+Lk\dEEt.,5LfAE:EEqg6b!\g/)VXk($u5B3V*+437lD?]A`D@]A(h1;%")=/s5Q_$!%_-hF<
_X>*Lp<P"gf^n(Cb)H("-2\Z4&RH6OBh@\rB0lm\n\f)[UhcDCpn)Ai^O,Y@ar$"[:(`YZo_
2STD`G7==aonWmM"cMF-?sUCl-^@>EZ5fqWV$`m2O5`F10G+dul0AWocP636:Uk77bZ7@.@W
F?o;rq84!$jV9oiqY0,j[VmVjFkG+A!r~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report17"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[,ueSX'3>m^6OZ?oEJZp]AM+=oR.VRX7.1.OISc`(<BL*2,NZ3aBWY5q$%>uD3'c2b#acS%i2K
<GaOGsUW:u&(&KZO@2A-jO2,nUmg`r;A^n%JVdrU0E.n)eKp$+.-_YQOslTFL3G".6(8IjDo
OI:N.UjT(Zd$X3!8c7L"'krQcTG>`*pZ=Z&iI_XobE3^Qm1?V&:V/gecG7n\?kW_r"iV;]A]A6
[NDt/u^i^CA]A>3C$2eJN8pW3P\jd?C^K:<Ogn!:!!pI`M3:f<B93+SYB4rsM54#kCaq\Gg+8
u$d@4Mh"8(J6VYLjmiFV^Cc/9%MrN%$[V+H)%cQjM9*)g^U!058aSGkSL"Yl,[^sc)B@fWL=
ZbHW^6Vf"*afJ,$M;t^IYQ/!^P62Ejg7)u;0RO+e>e*)#48kGrmi[Zfg^!>^Fu@=m52*n.C^
8n3ChFB[/\$]A?/YK9&M+DL$/IeQKZ?ZFuL[^GtQ]A+l!']AQ-tk@FXO!kPKD^71$ZSXetMX-&-
L<.LM3Gm]A'pb->_PA$d9P0JOH-R!?l;3+!H@>N8JuTorX>oX2Xoe3eS:/.>ut,:DeaoLc!E2
OSJ[dV8AG;3UYbYG6>!=L>"R#9T\U[4)]A/O2KHZ*li9okXDi(#nAJEr\WQ0[1rCX#I3gHFcP
,L;Nin%qV2VYfp\MG'RQZ%@aB1dmR9XPjK0kNP3EDN%04lU_nHJ;[)`r5*L2J?*=,R2*t_:Q
W`Er?1t7n<\c_077>@25.GRaI>eEQ&08c(ooXqO=K/jZrSJ)r12Ot6Y)rT?Fmha2#HS'><k\
ZNU?skXLF?-FIq$Y&L%hLHKMS$J3$&A-<0G*ZjDhsTH0F)Ea!O/TFV^X88SJh7D;0OsR@m+f
0H!Bt@J`a\Aa9D8@EQsY=$MG8K8[iiKmK)%O6YurUMifTESS.l,n^F8]AKaKh<Drm,&=nY6V/
!;GWa]A;;UQ?O*F*"XL]A.=PD8!XodeHH/-`III46QM:A-Z(q*%i(/o3!*k=igTh)6KkYa2\dd
YHC%R$!;.^m'YcA[`UB@%Ue0b0sCdfCRU&=.Q9775V&>R6\Lho21Ea#nbr8&cNf(^P9?@aI:
IXCHiB_*L*FfNt=5(5g!XA\m;A(83Apme!fQuADt4Cr#bA7^[=#]Agah1B8-M[Z6pe]Aq7j<Wk
IWTpk\b-%GkM2/15RJAuY>7^q1FhUGC"`;XJc"NTf?NFJ`YRlMW,%FVZq@r>.Oo19L2k<hg)
a2"44;eXAQ2R0BO_+c\Bm+r]AC8SRe*LP/C6`UmjhB7.i9)i5N+B3<%,RS1A[!S@s[-Aft>Us
3.!%Piq<6G\oX?(C%#C\skor)(WJQ7P8I.;<>F4,h@\rS1f&o!,;8s9nD3#o)THH9*haHb4A
KXMTl#!eZ&Y8LTBbnh8DShd;BmXB<'Eo)(p-W_"Wh$?_UeJ_e'S^qQp`j@am^@8(CsNpc@-^
bG#<3N"3Qhb7BD=ge^P(BW<3m%4mjC;JF6]AhU=%P!Db=7IjKD56/RKoBGX=O2!((npW`stZH
lRBRmnl7Q>3bjnjXo(Jp#kq_0-R\XCuF0@Nbr6Q=u:ph5PN4mc)G;;f#,ul`2gtB4HjZE2@:
\e/Rs*"#$u,-LtS@ig#;"I9Cd#_k0_4BO[.Uok1Rh$_e49)8g3.:\&N::OmldKa$2KgTl1,I
0JR7H%M^%"AXbrFV4u3`-GEk&*mWe=0_rYg:L`c_m@&L#D^ol]AVWL_HhlK]AOmtWTL(D*BYVB
5*>>-eueYQ&+@(c#n*`#\6(j^\p@W[OGOYbjK#A/7tFXlJ'8\6RGA&%[s$Qj6J#nji7KA*n$
%LJ#X!]A8[K<kNcUIm2#I]AC8s3K13P.8[9QWNt.BmnnpPS0Q;og2qD!h4mq^."Uu7@Hm[NI4K
f(O`TohpcFpE@b[/ut]Ab/hd2Tn)>2cCjbRSJB"GK>,M<`JRhIB>!QPTZ"A>4\c\>FChd^gIX
UEVG#F/k`tW0Qh60D?&j)Q+Kt<4altqB()Fl_/m.Ec#Me9(R:<)j_WZa1i+>V5@f30g0"?(
~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[,ueSX'3>m^6OZ?oEJZp]AM+=oR.VRX7.1.OISc`(<BL*2,NZ3aBWY5q$%>uD3'c2b#acS%i2K
<GaOGsUW:u&(&KZO@2A-jO2,nUmg`r;A^n%JVdrU0E.n)eKp$+.-_YQOslTFL3G".6(8IjDo
OI:N.UjT(Zd$X3!8c7L"'krQcTG>`*pZ=Z&iI_XobE3^Qm1?V&:V/gecG7n\?kW_r"iV;]A]A6
[NDt/u^i^CA]A>3C$2eJN8pW3P\jd?C^K:<Ogn!:!!pI`M3:f<B93+SYB4rsM54#kCaq\Gg+8
u$d@4Mh"8(J6VYLjmiFV^Cc/9%MrN%$[V+H)%cQjM9*)g^U!058aSGkSL"Yl,[^sc)B@fWL=
ZbHW^6Vf"*afJ,$M;t^IYQ/!^P62Ejg7)u;0RO+e>e*)#48kGrmi[Zfg^!>^Fu@=m52*n.C^
8n3ChFB[/\$]A?/YK9&M+DL$/IeQKZ?ZFuL[^GtQ]A+l!']AQ-tk@FXO!kPKD^71$ZSXetMX-&-
L<.LM3Gm]A'pb->_PA$d9P0JOH-R!?l;3+!H@>N8JuTorX>oX2Xoe3eS:/.>ut,:DeaoLc!E2
OSJ[dV8AG;3UYbYG6>!=L>"R#9T\U[4)]A/O2KHZ*li9okXDi(#nAJEr\WQ0[1rCX#I3gHFcP
,L;Nin%qV2VYfp\MG'RQZ%@aB1dmR9XPjK0kNP3EDN%04lU_nHJ;[)`r5*L2J?*=,R2*t_:Q
W`Er?1t7n<\c_077>@25.GRaI>eEQ&08c(ooXqO=K/jZrSJ)r12Ot6Y)rT?Fmha2#HS'><k\
ZNU?skXLF?-FIq$Y&L%hLHKMS$J3$&A-<0G*ZjDhsTH0F)Ea!O/TFV^X88SJh7D;0OsR@m+f
0H!Bt@J`a\Aa9D8@EQsY=$MG8K8[iiKmK)%O6YurUMifTESS.l,n^F8]AKaKh<Drm,&=nY6V/
!;GWa]A;;UQ?O*F*"XL]A.=PD8!XodeHH/-`III46QM:A-Z(q*%i(/o3!*k=igTh)6KkYa2\dd
YHC%R$!;.^m'YcA[`UB@%Ue0b0sCdfCRU&=.Q9775V&>R6\Lho21Ea#nbr8&cNf(^P9?@aI:
IXCHiB_*L*FfNt=5(5g!XA\m;A(83Apme!fQuADt4Cr#bA7^[=#]Agah1B8-M[Z6pe]Aq7j<Wk
IWTpk\b-%GkM2/15RJAuY>7^q1FhUGC"`;XJc"NTf?NFJ`YRlMW,%FVZq@r>.Oo19L2k<hg)
a2"44;eXAQ2R0BO_+c\Bm+r]AC8SRe*LP/C6`UmjhB7.i9)i5N+B3<%,RS1A[!S@s[-Aft>Us
3.!%Piq<6G\oX?(C%#C\skor)(WJQ7P8I.;<>F4,h@\rS1f&o!,;8s9nD3#o)THH9*haHb4A
KXMTl#!eZ&Y8LTBbnh8DShd;BmXB<'Eo)(p-W_"Wh$?_UeJ_e'S^qQp`j@am^@8(CsNpc@-^
bG#<3N"3Qhb7BD=ge^P(BW<3m%4mjC;JF6]AhU=%P!Db=7IjKD56/RKoBGX=O2!((npW`stZH
lRBRmnl7Q>3bjnjXo(Jp#kq_0-R\XCuF0@Nbr6Q=u:ph5PN4mc)G;;f#,ul`2gtB4HjZE2@:
\e/Rs*"#$u,-LtS@ig#;"I9Cd#_k0_4BO[.Uok1Rh$_e49)8g3.:\&N::OmldKa$2KgTl1,I
0JR7H%M^%"AXbrFV4u3`-GEk&*mWe=0_rYg:L`c_m@&L#D^ol]AVWL_HhlK]AOmtWTL(D*BYVB
5*>>-eueYQ&+@(c#n*`#\6(j^\p@W[OGOYbjK#A/7tFXlJ'8\6RGA&%[s$Qj6J#nji7KA*n$
%LJ#X!]A8[K<kNcUIm2#I]AC8s3K13P.8[9QWNt.BmnnpPS0Q;og2qD!h4mq^."Uu7@Hm[NI4K
f(O`TohpcFpE@b[/ut]Ab/hd2Tn)>2cCjbRSJB"GK>,M<`JRhIB>!QPTZ"A>4\c\>FChd^gIX
UEVG#F/k`tW0Qh60D?&j)Q+Kt<4altqB()Fl_/m.Ec#Me9(R:<)j_WZa1i+>V5@f30g0"?(
~
]]></IM>
</FineImage>
</Background>
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
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="386" y="0" width="372" height="67"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report10"/>
<Widget widgetName="report00"/>
<Widget widgetName="chart4"/>
<Widget widgetName="chart5"/>
<Widget widgetName="chart1"/>
<Widget widgetName="chart0"/>
<Widget widgetName="chart6"/>
<Widget widgetName="chart7"/>
<Widget widgetName="chart8"/>
<Widget widgetName="chart2"/>
<Widget widgetName="chart3"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetScalingAttr compState="0"/>
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
<PreviewType PreviewType="5"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" horizontalGap="200" verticalGap="100" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="2afd8b69-1955-4a38-8d2f-20658018e542"/>
</TemplateIdAttMark>
</Form>
