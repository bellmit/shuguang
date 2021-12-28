<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="明细表" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="name"/>
<O>
<![CDATA[江苏省]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 销售明细表  WHERE 1=1 ${if(len(name) == 0,"","and 店铺名称 = '" + name + "'")} order by 销售时间]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="日销表" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 日销售]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="区域销售" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 区域销售]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="月销量前四" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 日销售 limit 4]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<ReportFitAttr fitStateInPC="2" fitFont="false"/>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
</FormMobileAttr>
<Parameters>
<Parameter>
<Attributes name="p1"/>
<O>
<![CDATA[]]></O>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="JPEG">
<IM>
<![CDATA[be+Ba;s0`*]AL.d2#=0k]A$8s&0Ce6a%.1rFO![0j"OGF2b4``Xo"'3&<I!_(h&4i6#UocdR9L
NR!1aK=t#bF+M3sU\P`F(8#g*;F\6`aBTK5P.W&X!D+anC-@4n"qef:=dChKRed1N@85550N
?rkFNooA;[FrHo+/aNc<WK.mlKKE;+GkL7d!-orgb(UYk6LVq*L7'DYGfF[q<?c4;)YW4muY
_AH<fRh/ACtk)<o(&t_nT`LJjBnf`1,C_CBf$$:=8%Z5b['F(0JP::Z+l)hgoqrAf2hh#?2p
N=c\J2gdFe3EBs<tkTnlD[lmBsJmiWffk;pi@"8_Tk'7l-J(PRd-=oSm!L!(F/#RhIf&W'f&
/i"&e.KZ50Odjq8Ya(@j[CQ&agrm?`>qPI,UJlAdEW%O_DEZgAEObFIbkb\\R$b8BPulF*jR
&@bq:b.6)L]Aq=mENi3=26G:j#;JdgGf/p@-*ONp3'GYbu&q<_hlC1AjPa0msf<A_r%qomHFK
3j0!HqFFU1<l$Ekf(]ANobIiPurjkuE)?[[:]A]A9,t>f_G2AgZ1Rnfl-n8kMCX-r.BdoK(ICLP
1SlH[_*4YUA_qo=Npg:pfI@#$1o_l1(kO_6i2RoBLKV>.<=6F9%46Z`ONC%RpY%&&tQ8=rVR
J`@o>*TT)Q^f\m+KM\LmF0.uS)mep@&Yle'>Z?Tn9.*W,#Kh19%?(bg6f9b7taqE=)LYN'@^
CEl.Fl_@_>c\=Ena1O,?<JSKRFT-;bLMEb+oA,Ekn\:qIq0M##&MaNZQ6o*_+cYh";QlM[N\
\.'=2C/&cZI$NMtR<P_uX$/[u5,lgj)Q8DmAu`M\>,[rr+4_E_C=C(43rK+#?i$#8k^1Ue-p
/iT@XD&j;JiBG.OpE429S9cbM!(02;&f[1oM6b>l#gu!ULKM@?*lA\"\fH^FiX>Ic^Hfieu[
R:jh$C3L6mYX*"N*lS0F:hm1s.EbLb919q6T#<a'0@l/;g[8'm5""_Tr!]A+W:7m&ojkH'A1]A
[lGBKIHZ\q\?_9CMdC<59QW>]A:h:U+=$i_/?68#n@Ds(@m8r\il"TVNu&Y#S8fJ[><IFa8eR
Sib@:]A$<B3n(<)"@<'G#MY.Xnn'0a!L>D9@cJdA6b>3OMP)56m?.Z%#3>,dM'=`BFB4^H]A.X
LZ=,##_\qD7A^&pefr64$in@aIH.`RX$H=>9nR.BFemh,8!JNi)e4\c-j<'+*O=q=_sr^Umt
#)47*G-%;cUMa.l7lC'aISX1Y*Q_a&A[6LBEC9T9>N95*G$TP"_'C-m7-9\4ZCK"/`78Qnq,
d<lec`:s\*nNYeO/k#-gAFWsZ<fV+^EXQ'RkI)fXAr$gBff8qYH7@H\=^c'#j&X.6p9t@K[#
Ghpp=,HWNO+o`#DKpqR"J?dB(m-c[7\<%ok]Ah]A<h"GEba%.#+Ia.!4QO1jA!bB0fD;#,gL6U
j*C>uU['DRg^^U4K]A;hfHFmWQark,kT62cajnu&'r\+K['W:pRP<Z)q-naTi5a[(=kTA8BjK
.;1]AM1FQ;.[-U`gjd.S4tsG.pHWkVrW%PI3Vk)]A%Fp;^qC.BWV!%,WdYH6HK]AP_>;(&E&U]A?
4K(//K+"U%Km;f&2Z<,i979_YlXbGgh4brCtVn-05asqZGjgnlaI'TZ9Gt(<G\s6V0-D^G1X
dME"cbFVd#srVYe61"(,tZRi=#EjaEYC\7GPpn/^d7E9Bs?dnOk*uBr5<Le'2@.+7i#BrU7Z
Zg=.Z'WFk=(lLanL0RI%-ii=J"4DO:.VT#nO,oV+l,<MVYG*6K[Y@:-=/?+=P`,6K=U.OQ<e
n0I-j2SF@`g:fmamtijmhle3\:99u"S8!gVlX*5^f0i/FF$K!/Mb&p9cgM^%lBr`V(r:06WV
hu,9S,`ZZIn9_RB4hXB;`L(H/Q)r'S&0"`Hr8k=<Cu11\h#M]AO)T'7)%Z2"`Q=NgZhG"\'D1
PNFs=?nd[WLBjc?8&a1O#q?`!JVho(3Bd-_k1>c`8Q-Z.^/Bmcec,6lYcSY7!NZ%'hD7oCTG
ahK!ok>U5q\Q1s$fb43,fK#'9(Hr27_UC&[kP@JdRIT/IhQ$Kk%[u%+i[uL/,p6kr0ul2>*-
/EDL5>=(@t:+Y"R_Bpk>g,ZkDd>*uoM<1I8MKn4QqgpMjF;F6I#T<o\u37s.K`*I6Zapf_`P
+n=&b^>7o5cmGKZ>u+]A8OV9p)7t*C%;tf9kdmN@m/TTuDOc=DhrQ\;*F$c>0MloZ"@42dd$o
6_a]AU7h:l,e"SeJLk\4aa.pnQ)f*JR7=Y>\afE2%XpSGHTe$TX\E@l:FgK_UTZ\),=mSD8<S
.*Yc[5(ICDO+u.t*Qr_ilOYBjE`KZl6VWs_<2MU5DU_HF@A;A8eW_QFa2Pk^d)Y[/>=fbjk/
;l_0oa7!^SbRjh)8?LFgk.9rBMU%#3X3bTnNh.j+(RuTl4Y0B7sEX[V]A!dBp!&N?:g0Y[FC<
/'B2>6m-G54-YDn540:6ba^;ClG&M_Q,*HcXZTX_^KgjF5imG4f,2X!O;Tn)tZ@bP)k(6uJD
B*!cpoSrdKd=aq9&bjjd>QB//r/j%c@%-s5q+98RM.i^a'Tr:DSTC/DoVZ)NM#O?_85;88Sg
F^pn_'V(R/uPFrT@JOd;r!As&TZ,P^8*:oGjUKj9#<j]A&e6s^&MC[c&qC7>`;on&m0doN"'!
@IlBBcZf)4Y/?"j+5d)G<0pTo!A3b`P\>2q1AP0P*bZbC;Z\gG`8]A_(tmf0>m((pM$Z8_>_)
cWCbcrnRrQgeACT]ANjiQ'.YWi)*3S/\p(1XW+qGo0OBTabaR-iPh7t[Cfqs_>anXlm^.!XeY
`#I9L@QUMADp[k(QKq<UMM-%8gN/"_K()momlpKdWtf^))PmLhCDc2"$_.6H>U52hF5KEVY"
D%RIPrpHR_;=;%p[b(e(^A/X999t%^#!]A+2@<OlO=#&AS?PeY]A?#8>kU7@If)o'Z\psru@JE
.i7L$F@1W/f%,N,)T/R[[7HL(DSRiGOh^SKDlcgQ9XIFL"`>Zq4X#**6k_Q6^oTrfb&F[JT_
bLq[F@a^`Na^O1&3b*$l5hYkaSF<A>u408<tdq@s5Ae:Qso"QFjC/&a'ofJTiGUa;TpQlH*i
F,6b(iN!l;1CtN9p`*-<ZOg46UcKY!;c"j(!W8;ct^V=3Lt<&@uLTVW8MN,XK7Re?4A@LK,0
orh-DZ(ROSdghFu!b*2*^T`<03ui@B?`e@WWn)Ul?@Id:cpq1dBaY_;1hQEZ#k2:F[=;BCu>
6JdNC.:hi_Pi&V;J[Goq`Kp\BYpT&Wa.G`6_'[[:bTHm&IDHSjr4r7`j-N)h>#6X#%[GR5nR
joZ*pqE4RSDoAr,TMaW[1-+cQQ^3I0nV#c/pF"HA:Zt'UBp71MZ2]AEs)N&H2TeQbZN5lKO&7
VW&gV1\iAr'a!G>6aW/Xf6mUeX"/qYGOi(nOmq"UL7(thH=_l]ARNq#UCpEGi6V_3VRls.$u9
+mBQ%,=aF=YS=X%V;n>NEdgU0BO?0>L%nd%L@pTP"QVAjTEUKY@l:`>`tHV,>_!gillm[D)L
-*A`CBqKm-!)(D\mo&S80MC!p;n#p_!@<7@f0$Q&-KQcsGdhfC3Xf]ARA=)p9/u\eL3<@Gkoi
5I4h!_gjN[rL,_-lJ(L]A#u*\c0q%J^Ji;YLQPJ9*Ag]A*tgOG8?&RXMh'MO/YWnEB.A(f2Tk!
R?k3-5GUqH*Jg(tH?^1YU4l8bP.<c`2YYrda1I3.Yhi[`Tm^FJ'sj:ZS@15Nil&#m!Ekdguu
$BsrI?Mj7Y=*'V*W.46oYY*hKd@[\C%A'BA`$T]Aj53V8(Z.M\81[[N3ugP]AjT-R/4*Uci!HU
%>L>?8Ld4\#HmWMbmkZEgdOJoC%tr1IPa8$?%jR_o2NIS5^Q[UeST#N!R&h:G,CG--Ds<Gsk
'd<Ek`?8nYsk'u!rf_/g@XT>&7MFtp-HJ+AsHqn%N-'2Xs0b46ZhN>L3Kh`'\h3noLpEYcms
(Q]AooLT,@(resP\R1EkYq![uF(2S-aePYgUr4uiFV=EOq4%/O)8Y\DkXm%+eHoGVgKOJFng=
lc/gGulI95-#4[:_oTg9iR)eR+I5\0j3U0`MGHT[$b+B#I9TQ/UmUJX3hPD(U\(abAQ@F"+0
@\\?/Ci_&_lQKGPeIdjp4iL@h@aYi<o`U"o0D-^eC28;l0M^*L,WN&c^k+/H(id:bp8oj;F,
ro,TfR/;Pdr'AP"A7uh?sfXW^+ik%]AeqM<bgC_Z2;oKc6rW60^k-RZ?L@+TESP@nF2F>`^XB
`ZB"CZ_oAj]A5ToK4HO6OXOICP5s[k6;i^F*_*`PikRHT:1[DJqZ]AT\7iK]As0Zp:B`uY1X1:-
=iLCt"N1DVE<L@(4k6BVg?`_;nD)TYlan<RK#`lmIX$T#?dZdSR9c5c35`@g[To/EWf%G/F6
:FmjF$MpME+$?HLi?+`fY&AQ'UM7ETVJ@@7$NHn(5!#nFQ0_q4Z8jYKkR_W\KS,MB>m#[?u+
4L<RC#2T^t1hL(l[U>j@U0WO\<N!kl#A:&SE<1R=p*/ZtjB/=$6PKm991p]AurDl]A<pZ"9fmb
:Est'7(0Q/!(oJ3:f^l+Zb]Acf1`\G,1%?h$W=eIUpJV0b26]AUW\o$n6S?P_@HCEn[UQ5i"CN
ipnhK$C('h]AW3$4KL:NKbR#&RFk-'u%_P$aPrFpZ*3E#n!pG-gS)+HD\4P4'LDdj>o/2!8Z\
8U,)R/WG##"N!q1dd6=e)b?'HbY:F!h%@"pSeQTt1M%dV699nmbJcq1e5jtXC(#s-%e=+bTW
L&>q^)X<^k\>(S34^VfCkQ3OftFpWbTPjZH'+-G_4OU)Dn++m%b5b6\^,\'n^[WM13X>[]A"H
]Aq6+p!cr0`o6T^Kp>8Qta8%GkciL'mP>p1W)`)f&)hlpL0<0Q3U7Lg!p,\NVl3:Iu.<okYI3
3Co"Mqus_o9fA)fVQ;BM;%:E5kE.L%Ns[LGu1_2HWrXQZH8(_]A32XR,$ZRPRd+'DofJcqPD?
FhlI!o/-$DLN5Kr4]Alkb.OWT's(n_R*7.?Skt&-t9(-<LX\GRaI'VmF.R%1Wk6??>p`-nL0e
p(-*sa%+Igmq>/`VNg!3;72/UHah2\j&A0!+2/bfr*Drkh"e/WYP@fi)(&bi`U?lQ-8s*pkM
InXq1.Mk0g/p(!30..7!"g"kSBOI2XGOjhKtL[bgTU=<jZ&9*9PQOONdIY@FfXD!7)8!qSEo
Ka'!qPqK&fS?^>S9V##!V%`;76!dBuV<T35iC4-.o@>74!-?_C&>]A%Va3;5jqa\Cp6+`a;%T
dRb3nmV2RE=Y-GT;L-FeAaan)ert.e4BLM*i88-?R`Vel!B/c$09BG.&*-0HW7,`%KY(smFe
[B]AN]AtOfh6eU/*lErdo`[C,llU19BjuJU7.$<Os<Tq(BS@hpRn28Q@P5Z"cH9u>cu3AAanc$
VU?8e[r\$86qTS,')0k/g3hbAiWh>&5116`MgRBl6:.T12X0Cc/B+s=3g:hgB&0mC`.NakbQ
&m5jO%X%F!A'[d#L/Yks9mVR,G3@jK$H+\s+Feecs`4`eS7[UJr6W*2`!LPj:88aeCd^D81^
Hm`m:Z?Ql>fF#cM*9i(7k>%AR@?ee=JdN!.-ce16>Y+(6j:ZtBR2FY"N]Af`'<hd5M`)39K^8
buc(=Km;1cWG]Au%:<Y;U=!6Ch6s:X36CXRL40BoSi!,r8S\lV%q]Ap*gk;WJ"n3h@J3%Kr*Mj
I?D"_YNr>HZa[9\f7Ie/,9cU]ACt7K'@*=h*4eCiHibeY"<j<#gkSUKl"Z?^^Yof&I1,<t`QF
6`GA5ArN^.b]AMc%`\T$O9^#&(c)F*``d.hNX+<W/B1's<Vn/V.qs]ALqj'_i"g_J$2ITNici)
-Dl4Z\dJXV8:(;$u(#&XZ_;VMfe%_9X4Sj=E9G*s7-P$muY!!j2SnYoWVS93q=P=Zd8#(F*L
9n'YGQ^jehf`Y@o]AORap!^`s%D^*NQ;"DH1UEZI7K-c@c.U?>@&c?!Zn$O^6jQ#TlQ8EUA1]A
?TdFV`@q*-J+b',!g9"b]ATgZqoT`?kKmQpCEUG5:p1[[m40d)=A)%uD>l\J0^-t?@F,0ZWLR
11@)AVqqE8SuUd#Oh_usC0LYdNG@X%7LbCC4PE"ghRBFJO\6/4Z%4-/B%38%$5;)2_,dB;]A^
YQP#!a%.CjTHQdJGo4r?=JKu3!ed_Sh1H!7F6):M;uf*&aFQ2'K$a4ViW_A,pP6p@0?NKHN.
k]A<bF/7OC"AKg9`tGdhN1[io1.`/,sAd05.;tlB-3r6SWiRVnCAB+eg)`>IQssiG^;lGTX[E
C&6cM_)3Ts_?krsrNk9bBCDpQc9FHcY':"SKHk;t2#ejfWs1$[YrK:k)3:i13ikQQZ\L/ERn
YPrkiqgZOAs\qqPn2uS%:4Fi0#fbHYiXnRHt+"@rK0bqD`c9uTD&9,QfOSEi;s<Q=^]AnJ)2t
):*h3\T$LXH77["m[U='\Cm;Gi[q@ZXCI&@pI2OiDaFXGBi.4P<Re<@n8TGjBs+VQ9<h]At2t
B]A+S.4Z6&J@@E&>h3+9T%pKSJ5Cr=:d;%:(Y'*gI!;:,g2oEjrlA_'C=(17c*B@Q>p^KeWpV
p/1[eVp@3grYKjk>tFT?!5;TAZ$kC,$HhS4N3bYVo`FK)pK\CY<-7pZi*DYH<ZU*mql9MYiE
-P[Tgr]AF=@dHRoXi!=)#)K$pi\n0,peT$1A.e`6HM:h7Vjfe-81am=s4I3tkGs#m@>s"fF"J
>t5J;%-l#A-[?[S$MR&,;!h_?%l@.HSQ'dQiF"rSo)<&c&:n72DD^kGc+0qZd<+WPk7R8Y]A3
bQ_FU[i,77BCcR.e%.ub3lU3H7S'j]ALI2%AM'g\Z<+k%NM%`N9op\#hoLUrYsSR(Dp'_GsrT
UB_A_,W[K^U6nG*Nc'ZraUMDVPtO%\kKaKe-(@Y%5"\Cci)Ej,[K-$tcZA)6'L9aOk_FaV)l
?Q7@W!j6l7qo)2*%T^f=o-b(,b^RM6Y!*98'TC"sE-Bs0FV>7!7Z#8<?kI2;D\CV4HPP(7G_
"Umq-s'')lOZjBo/eVk2P4rOn)jp]AJ3V#Pb-G,e3lPcW_om%qXhN_dd)HLd9pjA3/7BK;V]A<
YWm*T^8:eFLh)O=1oXOjit.D#a-D['!S8(;uQQ`LO#bl=jNe4Kgch7esC[#O`JW$SY3fG6]A-
E/2e>a7(D]AfiT0jEEFm)A:X25g[2C)Z&H!$0OAC#AMYmZq\YZ-!bVR,Oqa'^$sJ>o<b[7!t"
^"Qn%W$Ve$f!BO.O4@fpa\pA:h`2n!qfSZ\G\2RQQa&3G^C8b4k2.t>PElo<RS=SFX4534+S
VrYhdKSeoh0i5bV,ReP(JL[\]At\_h[e^Z^9i^t`+rRK,Mrr:1J!?i?]A<D`mC#hK?<KoUV]A#-
+%DHWY`7@sX0CrBsiNB!9#6:Mi'B_6`WT-#2L^@F92==%j4kQ(>ZWOHp'%4G&72-]AOV0?M%L
=FnLmGqE@U\-W("YP$\i\j;TWdd`s`\H!oi69N@^EXE%`WXXXU<Tp<3R![bZ*S+KQ?^GE(OS
CuQ%08D+"2qiikT0LWpUWk@%=@./+*0IW5VFQs--0/B5Jg=`4PDcGS01n:A$F<@hZM&M$17m
glYBb%2,ilrtt?^1Y</u>[9fKi1O'q0kVhg;%@le8tL]A^R_SpM[/J$/lbk8t$<-/^I+XVQM!
lh_L#X99A_`R;"B*sV=bVX80B0&%+u3F0cnSp6oceu3HCPl`F[nbu"q/e5/S:#Vh+hde3=13
YSElhZHY80M!88#s:Lo*qVpDLZ/-Aji0:8'Oc53<2_@0Zl=<u$+K,7$sXeoT&_3.e!%i+E`Q
LlGmr2Q5B_;S:#-L=.oB(S7804uN]ASjP2F&1b`<&iSrY*n@L^<73Q@=Kk@,oipjHLmQ+%N#H
S@-qAR+H+2jfDh/,^.A[W3Sa);8K>f<DFsX`2J.?5k*miK</[<,k@<bVq17\%XqPX:h01dkj
HH9b0A4hdR0g?NZMd--&+$0$T5j0f?W2AWSefODde=jd]AQsb\b7a>,CgI'.fCh,\JkX.EK`i
WY"QU*!VYTeCp?Uo1JB6nr$KLE$05gE/6&r-79\XjueWo8*QN*`XGG_LT^Mp%&X%:,Vf=8^d
un&p!bQ_e]Ab@#[775]APom:2%S:&FmEYPg/*(]A3A/IYui\0YUV+)7D'-&I!UQJVW*Tn$jRpo@
H[d+]A7f<nk505VqEO<ho1)@h&rJaMNe=tteG"k2p9#3q>Ur5THu"X4ld^:SbHh[O@ujURP8J
T@R?!>,nFa]A4-Sq8K;LHuX",)'6L'TjKA.;9M47BGSN[#t.BUcuK253FfR60R$.$q>uG_H,I
c^fl)A6dGC/[(E>R:jh@WOgOE1NAIBNN%RqXp@Y.6HRJ!m_M7C`=JA:^%(+Zda+P51*655=u
i]AER*JEJZZ.gc";uds6Mu]A;Eb99rng_nZ)Hq[Q=-J+X@2NtciN;'Uiqg2knX=%hrD,LSnVm4
9*ML`aW<n'k@pSF\\j0XT8M&^k=so*F1SIS"Tl,mo!Or:`TUpf[/p9hLZ4s>fXS%r7K.QN@b
X6)(Ya4n<Ba>J_9`6YL2!hNVW4A11J+r!0i0M8"nWek"!C8<(fTTjWPb4?=%uhMOa8u2a,hR
.WoR<6?C&>*I6#T?F<S2F1Q(9k/$`gLPriG*]A%E13Z?2dQ(55/Gi/XIJH"q/ZD.;2SLF5-=T
Y+Qb[%fJ'am3k6EgN)KZh/>%MV!X+>JlOq9F0?RL`pX3JqkI\%"t-&r#P/Z(B]ARIRee=c5Ck
Ftj\IY'JmJ8BfFXFR^E&2pK'6Sc%PNH9i+ural"ei/rV:$F*5<sIpL^1>5]A5T&j3_*Y"%o2+
)_1ir'e`*j,-/p17\3>dZ55kPEkS"&*+qV"d!mLZ>lR"r>cpIQUd2t&,Qjm0X-jra6gJQY:0
^(2/]ANV>WO9V48eEF.0JX.rAEsZ1rOpQ.Ed6$/iI+O\dR_iRJT=C2Y<s7sc/Bp?Q4k=SKn+u
^Q,iZIi?(2bc0pY6*V"`F6;IthQ`'-mLIOC4u\_\^p+6V]A]AZ4R9VHHTVCHp8^,N3:@VQ[IH?
i[`)Y=]AMY%$Dc@Fou-b7;]ArF`lZiO*DTg2^^-dLp_;e>/eZa(Xb=i%@e^$i58<RUo="[oYEF
TPb72,K5Se(ZjMHk6P`LWomStX)AceaHOB1<^.SK6kl_#8.aGQI$4m:+pc3mWEOgt1le4NIn
&kY[2_Q=1t"6tjRfCY(>D=-D^!;X,)+ji4UVi:<?,cO.ZE.cW0K[8H-_"rsWi1kU>KRJj^.V
OUciZ_E,R8(NF%1U`29_c%SGK=%Yl^j*t*gRq%(>@Al+^cjq1%]Ar?TcC]A%aSf,aMd.^*=Hi]A
Qi$`g2$S'OmkM'ThgOiKhImPV-LerJb1.eG]Ac:]ATWIgPb(n)FF/34r\5eLF6(,YV!c9po;[s
brX3"PS1IdWi4NTb'NP]A]Ar,FfXZst<!t_oenatL*U*J'5r7L'neV[iJ9]ARuM//84?`&CBNqo
fd]A:EG.kRPD\'$2KWo-?;giAlmb0G6\XO\'tHO&"$OIr2mR@VLF;.fb=!&D[:O#,6Mrh:4Va
b45$^o1$46df"?Ka2c8s%raiHDZb+U9W8Kl'L41`8gJt%>ic!`dOK_KjnAI,RHWn[BY[>(Vh
!$7i=j,UgR1N=ma^+t9OM_22:2ha65B-WrN9PBB]AV'DQGhO4dQ,Lb?Bd3)PMCD\5X7mrA'.c
%]A?2LjiZIP+*@GZIZ8E,&3*6k1U/=>g#2)%R^plg0/bjTY[D@iDBgTaDeo4q)tk;QOTI!NAA
#YHTg2jaDr9FE+j<qT1^DJXTD=bB]A]AYQ\m?ZCkS5qgUrih^%IcSN-S_qtN/E)#Q3Mfl?3eXM
4I6>Z=dGYNk.L9RlqJ'@lVPIlBT)i!qbuH?OP>Z\>f5A"-uZg5ug`hn>kD0u#24rdNnV3i`(
-F#kI^eo6lALn%O?S9s.`(o#o&?03Ytq4r(OC@4B&;A,GtJgbeBQ8nIXU:2FU;g!NjeN<17E
=9Hq=smp,Jm=Z6M0g=UZpoS:'UE-qhna6Fo?d]Ak+[;l9IR:E5/>.ul/&O5BgFsf2\pfl!JX_
hn>UZ,-)[NeSTE@,3&k&X;3@gL,*Y[_j^nKZEQfj:2bpMTM)uP_S4*P=Bn$OM%"V9AO5]A8le
?5nLA9/.Bg'Dg:9ZMZ3&HpPMA,e<\R=10MM@#8"Z1SU?2j&D?bLT1tgaUt`Q421tp]Ad,/bW:
",)gj)$*VRp%\PC,67:[J!f--]Ar/#csCoV8V8H971"9@I:Y0ipL7Un5Whf^m,:"cJLU5;86\
-ok7m<J.sKGcGHNQr$NUh*jFo,'usn@2Y^GhdeS`f@uultS_q+iSc@MMZM>,AX+pfQ9`7VW+
`3?U/i0$$_:]A7\%SaJ'rH6^#rIJA2JQ']APbQP\"a6S!,[*fj,/)f1L/1Eq3_6C)g8^Qf/nF;
OnJuVY7/6EO5Kd'4;)YXo0bt#\S<S=BN(Y1L>TQ4mm*NH9TmX95beGefGIHe6`i09T$R-m,*
JM_)l]AmU^9?(eU9,pKPHlXW"'QftKZJqD7B=d%5GR!'p,d=PQi"J2,ijn5<,>.H4[V=]A&eaq
dKa?XLf^L*n670<0&8$df>B.\JmA\L9_[QZn6K^_fVd%T[[t_75=[?hICYRqPR1W]A/0]A3C7B
\,'SX<G0Kg#eX/>M3+HA:rkc)&f;RO72efJ7N8m7T)o"Bg\*R3P4[9XEa]ASV^Qr:&k*t14BP
I\k.cPYlI>1-j5BKUNobs1VR93h?gpXJ:o=H*`c'sE-b;1C6IZ,L;l'H>;j)LD;jV[<]ACcA$
>"Z;)fFQIJ]A,@D=$`'[\li\M84RY*Er0\<[Ls$dg<5JJf4W@YR%>4q&*7H:RaR(BMEeRZNo7
V"@bt*>#$"X$N!]A&uldX29bF?OTblK(ZF7FXc7P'6oT*5o-@qNPD(OMZC)d:GG$=C5/D6$b:
-rMVHcO;Y1Xgh<l:oc5c>CPNqGD!8<TR[#`=VGi[EDP0S7]Aa@:9(K'qetm0mp(C30k]AHSpq5
ec?5TG'!<$A)^T)>]AnXgR:'2!ac,537Z-`c#Z(PA/)i='Y#)a9g),0XAY>tRYa1dA8(g)B(`
:n7#7'=H3c>Yr>MUS2:"K%T.L.6-=:(s"/C1mXFd6)1NkA&(PGG,<p%+X>2jE%h<+E(4Cgb+
[!/SpZaQo+/"N#0s'jY\cOk+S@7+@%j7lPbq)Vh/^[Oh]A9`k,YY^%]A+1?-K$593QmFsHQ2pF
_Ih;jH+I[dJVN=#I,Pu)s0e`-f\U(?-?f3mI6!*eZ!sCp;mUu142?!G]A@>E7m+#)Be*G/O5u
L:e+S&OToQe;RSOoI(+g2PYo`n_,*".]A/-q2SC!uD6'UCNDK4pHBE=>U2=j'tDkU/K&C=-#T
)n8+L[gd%ebMf2hJXNMKml7&t1-T^lk0^YS3-[I:FR<5Jh<DQn:T='KTkD:/jYE]Ait&m!8!i
RO,\9Wkd5OKd`V#n\""I,fft9,W+@HWfNXPj;jKB1suCoVSDrS:'Hh*T`o5>F%s*=oX[Eo!E
]AkiQ/.7&2\TH$LjKHNW^Z/Y5+[eppbV1AO$0Cc]A;=s,hBE9;@rk-X3rQe^Z+I\W&Rm]AbLhmX
V$j=TejJk^3jM"IFk[FsGefK6`Xpp'2$(:,fmbo;><W@AdkP$6Xos41Y3d0$$B]AJ8O`4b/$W
kPDAk1ft+.1QKhfjL!@`@8'ml]A1CH7T+i^cq_-cCrB13Qp3`.qW8!?a(pGVQ"s_LTe\4qQsa
6Oj<(Q;9XF<X3strcq[sf>t#X2SBcH#C$8R^9lsm.E8]A'3W1;84d+>^p)kX`E;7cu$&,&oMY
D7Y?NlCce^q:KOWT?4>BmRWtPs\-?a>ue_,Lt5@Mf<'&qm9*+C5p%*!@"f"e_rFhR?]AIb/+Z
!IaJ69,Ugtc&+4,JqZ-Sqemr=O5#]AcQ7Xu:(C5.daH_H@dTDqhj6&Ib\["5GMUrC\3)3^_3j
@nfJtg^TYg_g&5#,"F00%leW/o-iY:f+"Vf;,hXK_o"9+\_Ag5+rZ9/;GI70U7!DWD&>j790
RKTBtd-r:C*s#k25G`b?*Tb2u%Ki1re-^G197h)PaHe-NZO(+rM\p11FB$+fbX'1?]AE0`6IP
Gi=o*5q#I+A,n7sZP'F1p!Tu.ZGWHfd!)&m!o:[L=;.'%V(_tp>)G)IF0qNEY)%)LKh_GjOd
d5i?.W5COIL`VsfH`t=DH!4V2@HaUc!?i,ZP78.@%@j;>#/HB2WpUsphY5?M5FCajlfO8S!(
c/'K:gPTD#Vm3sj7d1HD#DN:Vl87QZJa,g8nNnHS:V+6-#b]At3Qo)9QM7**UQ6E4P+HeW?h3
2@h1bAqdBn)i,=%Vn0&+*UP?is2%6@dl']AkpkkY?!%SU*&9Odrjs;#mld4o'(X(;607pM8IC
<^\U`B_T"&J;QdmS/+h.9Z_=fc4VWQGK"7\$G?5=*8TW,4@6jF^m<$2e[&7)c.T83\l:P<+\
(.Kl8eS3"JK%o)VeT-a\0Otef>Kuo_L6OV,,p"7?c&=OR$gpk?[YGlK,M#3Lk.`H%ZdN*sUk
r/lQrurU5f`>V?bol)Z;6%9edIqULi2?<\LK\5HQJqnK"qlkX-rD(F9XDO$(^k$oI.P8`Y1J
kee`H=8I)A6)B[IAL2P,8*WhmV1<7mV[+6)QF=WN(%\9+Fr`+U3CG.t$P';Dph\iI6`c_2L,
!T^"'O#A<A5V9fdBIe3fq-hnB\5Ou8fSKHVWsS@gO58*IclpK`qA:M[k.rg?jr?n#,!$4X+d
op>[JWU!&]AUe>kEep]A`Ru"adPtX7\A43m/6p/HPofL1,0*#Q'(nWt_sD';!9;SF1.Org=#@I
oC&S!A^73m?P4M)]AmDn)k)<7)IjQpDSF7M7rer=KjddP=oLA58USB5<ZOs;*89Uldt3O5H;F
C:+,&&:SJ_s.S1mb2td5DO\T(n6U4%mW;&CD&&l&^LQD8Torl_&Vt1(0GVj<Mu&_fEYKonMN
:H-6%QY!<!]Al.d%`+;dE/Q?m*tnW309YV0\@8RWV^iTG]A6,AdmH?g?mgJ_P1b+a?:H,p?nXs
f=t2r-fEnH8oF$/;pA_H),&6<MTDlh%[!6#Qf:"-1JB]AkSmu;07ZtXon3TDIKVl<.l1I'NWs
nT@W*GW>NEt?QoifsT3@Q=,mc:W,3%E?ZM7Xe@2)[A8\<*&Kf;-7>Q?V2"bZ=Bf(')h8\Odk
;X@HD`\KcOEEm(J-(TkYir&:m0#YHN%qnMZ/F*PTZR`Ku@r5\<nN'?Fkb@dh-'/i*;\D+r9Q
ZlmDMf7@M!Vg[`-lfQ;XbsCspO)nN7G)?</UMV')^=ll^6/I2na<bq'h*4lKI)^(`<fm15=6
+QIYRHDFM[e!Qf#'o3J+qMf;)pKBlME'Cl>rOc/J\2(CaZ?_$(M'Ba<qu6]A?)?mptGec3)6'
nV:oN\[eeKfStQR44A/4?i^FmJVK[K]A&Nm>20JHXqKjH_nO\e/jqVk;`%A(U1\D!72^B7fY2
J?T9\^s:3h?*DA)F/M>fA#7D36hDjNR6;8HaqI=l6,!"KMr^TOm2NY;SP@92I))^^[#g2pld
)j,qfK0V_b\gGI<_mgC+7Kbk7_L\-ObmGe\2W2V]Ak:FlYmFIMh[rl?QeApNi,`]Ab3EQi)lDH
.bK3%_^Z.=kW>@fd5Bn/<8/Y;Cf7`+H)]A5fcA!"L!^;s<5[LV'n;IVN<bO`9oR*ZKDQO\X.#
EGMI1M]AN1%bVgXQE4Y>&&?>?GSFgt[?:SNh[O",ZcM]A:Z7PRpe]A>eLM8DEQ`AdGDUU_Y9e)Z
@F^VM&5l`q*Kr\=C^:nLLh2R4SnYo1#ZFD$M:\ZAb$%d6:g0PXYsaV,b_>QEAa0G(Ul^?3@e
h0#O,";*o3/1O2U5\q5qopL)(=[$\=`QF?tfJWXXBP!b?4*?^0QRdOK[l;!!4t84'cS_o3t=
dUR);Zil,;&$;oVt9PD3[M=1O0)us/)22u"Hn,gS3Q2i2#*VAW[`Y_r)3fV?c,dSt[@77R@S
?*;(PVeLKQGBY.S>"j0+A?Z2p"NKQYW(N`Z&"/h01jA!NouN[\]AA1:I`Id7"8:stR]A'U0TK-
tWpEPmZ';EJKFon!l4t8tWW-?lt!F2^C3"30'$%NRBUPm0`WZ+]At&?[B@Lh:`rFUSV\EM(#,
#o.)\G+n"F#M)?8gW`DOBG2eE)ubjFmkLOPOJ[(Q.fa.b,BqZ9aJd91Qr]AuMqY(<3=9e\X"p
4``<!F,IED.#\oE@Z/dp'oMZp:M)B"Srm[t7u,>@m>Yn]A-A06(^dBM&n=bVM50gN!s.aJU-'
:<#F#rRIF'WYRs>2BM2APnWX-tJ&&X8L-qS[(.LqJrupSnPS3YKU"=@?BZm"GZX3]A5l@B4#7
QtLUT\/fd+QD8$:B^jm8-`0Znj[`?nZi@[[;0WsHc:k"'2'1^n/FO*<<1W,JgABHN>E8kAW<
HYk#6-N$-<s?/G@af7/S)BP42$cfBG\VetlN''1ljS'g>!5,au9\3uI1Sp3]AW%pqMur$g/JQ
6o\R[io8q]Ad&Mdd?Q@AmCooLs2!F+(iRuSXp^R-N4I?\n8#8D)e5tW\Yaiqfi(nTF>-Lc?b5
)6iLU,&.n3)+G'A+YtF6LS+W&!,6R36GoG:$rS\rgUl8HFF/+YnBeP1-O&--8FmfBY&.WJ6d
Eb-`i;Y4($F.6BQ6[oe*B*%[%5cAkT)-[1%"K<9/cnm"-JL0*ut"B@r$$@#KTL5IDtIh5$0^
-fL\ZCnT\4'6;k&2lL#8%e%+p:0_OjV?QVD!*(4R!leK9H5C!+fk!Tij/9pB4l+hZ^EsjV0a
BV4?Rae:<DAq?lgnu>T[=oA&[d\q]A3t6s4#3Posc96]A3s&:gO^;o86G*75Q'&=FDShr0734J
s!TVHFA`0A+#3";[h"_:/,e"lH?XA(rmq<;SaQ?caMWMA@6eo74]A,/fe^;(2qHrT<A>)GH71
?o3-t8T*P.$-?$!:!YKLkN>"5[f[9/-2Mn+Wc$I@60H<-emA^#(!RQ+CUm$n68_S.or5nY8O
#O'Ltp%=(*[_[mu-":/:jR8`fBVS]A%rL7Hl0QO9>WiGq52s/Df:"eJ0cg/V>OnaG@W-hYHF!
>NkX\knf.=usE:6ASKrlX/hgns1FR"SC>8WqQoPNEK-;?*VDWqEi2pYI31tVZf_B9s8jMgqm
$'8#stC,,+&*M>q05`Mm&2KrQm)k$Z)1qP$2f4^idb)=90ZJa6g[:S(s''jf7XF7:_JBRQR7
=H>sNTS?"Fpua38mPSf9pmo@FJjrP[=/q"\%_&%S\,4F$KQ@/c)upu>nkRmmUHjGnQ?FEANK
U!M>6Fp/=I6URZMH:,>,gZ_^)]AM_+h6Aek]AkBX'mS$&*hQfjasB`&G?K:i'0_"_XPH113,L?
W$9/QTEbM]A/`MT(F+HhV=(7APBO`UaJa5WIOgb/Wgb6EPD[Zk>=)o*2VhOFCgOF^UTIQuc+\
&`trOa,Cp$;rMsI>q8oO0+K<oWNT4o%-LeeBWK:Y>1_tT:=EZ@0T9A*%m?R"S)airpiLj!%e
1M]A)nCC$jEXrfhHoBCg7Pk&(V:+!>M^g.;lLgp5h5JUaa5m0'\'t8T<6#nkmVtR^+6>ffrUf
EI>C)5fG9A4$Q&`d@(Y$T"T/A1CAuC@VfQ#dk#lX#`8=u\fY_#a%^-8>*5bSJWu"=H\`Z"HO
'H]Ai*X<B%[uSX,34-6\2+AD<ub1"*@SdRh<pe=7%:3+I?9;oPYqAb2-C0iqO@h!6gf/c.kXh
_#t>O9$kj:d:8nIs2U="D!_C;.'#4^S_G@!\QNsI;!6gsT*)XsX_bU`VUCleBUhl^fWl)o#,
,q:5-U2S*F_![b=$,&Qi$)5Y>@ka.3Ls4lalcJA11-&dBbmW1F7)8\`rLXK<#dIP\%'<1Q(f
[_+K,Vj$0rU:,8Ru!A36]Ae#m%*FB9:\BAeP1GgiNMB$rm?Fake!8o<-*PSROHmn7s$=H@O"=
bAUHf!H/nuYhfc:f16L@UkUc"'mJD`:?Yg%$)A6G2Z`^T*<YQsWJ,!"9QmN@7FfU(6BL>k?r
!Gp=<]A%t+!5s>9>S`3`WqgG3jnese!>SckRKb?emJF$P-?^j2C^H76f#`1?fj)2hJUPh&G`q
QaDH7DT`?fh:r[E.Bi't?=Y:F3Dc@4\j"ih(o`=WN"S!r!S:_-H`]A7-BSX/)^2MQK2NpH2PU
&39)D":BW*!Qmg>SAHCNJJ2JFQV%)LA!rJg\d!Wm3P5L+>Zc@OC9W><#fFWnWf:N'l!GGM"J
2;dCDb@HROlEpUdM>Je!KUs$XL%OaNBQmhc0ZlOPSQU8`r`Ep/b>D3Z+S-Z%"B!^-PoqjVl!
C1mWn1Xn@mW'7eNmLNJT*EVA764MrhnMD,\br]A/dSQ1V;FkIsSK4F1_:TNl7o>#KSqTh2A!,
Y'm,f%EjV$2-D(MeH%q;rQWPC.`3&_5!!d/n=TH%;K.lUS^YP.n/TUrDA5GI^`XB'%=l9o?p
VW+l`P["]AA.=B4<57O"lGPOHL#5diRKlC[Yps$_s(jg;H/O]Ah%_;`;D/&U$OLAst7"k>ojag
IE,nA@U:!lf"9CMbSm8B<IlCW6+Ms'nAlE^]ABsYgE8sF/2:Me;0UK]AGm\WX1AUgR1%UCg1,t
fn)WnWSPRXA3^L3pIYOQ06"^6_U9C\DUl$4HcFdq!XU=(lRG/]AE937Q_&eulhB*(4T.>5@,N
98lP,FNk(UeME;*]AI(Ngal!osReULO/!tHcLM'dcM8/9T91q_!odIIr`TW`c3)L64q4qtp#_
]A"sS/Wf)Vu0h6D-qnj#-paAm=R%9\/XAO&d]A4?Z(lZ8pAKGf,Bf46.%#g_kMFss?E;WMC\C<
^cmKV!)t-d))$5ZkLSY@i/fUDHG,PBHLWR"e8*09S%%ro]AcDYEu-@g/Ab%Eu@,8KU`jf#V6[
]ACu7hGF&scIicXHq7'?e0h91H9:J,UjTNoSa(4tmV<+hA[s]AGC^>^9_iDYm:jB!5<EoT,27O
!*(X]ATY6?EHnW;s[*`Y]AP48'(Li&I^7<O8L@J/CAJOp;Zo<`,_a.&M0nT)Gf[+b.OAD3%moe
^mcW\`I!`\lX#YYWB&%]Ad<tB_oCkNBdW;=m%p<+lnZh),5Ma+h6>3Wa:Zpp\-I,g3<K_tLms
<^h9b^HtU_$^JF7`Ruq=D!\8cN'7G%n(#quCS5Y\XXL.i3>rR6L6Y)7.*U7n+8g%SN+fM4]A=
,=,aSfcFN&\mHZ]A3)$.b;6J[SXVW`6/S'qWC8A6V-KI/sG]AU"af"UO)rmWB1u2[^r\jKE`36
:\uV1TYI``9NC-^VmQUpTr%m9NspZ5^mYe,/bEoX"(<'fRamPN's7D2+<Cdn!:mL58"jo&L4
qq_7Ai5/"=-#@]A#SR6:j6_I/iq!on/YbQhe-)ah70Q%K^:I]Ai[1"mpGBh8rFJ@Q>r4\fta<l
S)09Dn,.DK5r[2453P1P.qs7rKEi*5@RljKj1s8/,*kmG!THV&GT0hm%U7\3Di@>eR!>PqS[
ZW+"-[kB`ThXrITWXbcU)&B-/f3^^Z95"5Y7u2jM8qf5?D'C"Va5N?DK$NdMHN\;\JB#M/sj
A\feW6S,<poW4#g.U">c2L-F8#kX<3iOc3__WU'j_+Fqrh2lA3r"!M<@F+sBV#jE<9,Q]A"o<
'>qMZHr*kJ:Bc:M+o"4ak)E4qK#T"EZEd8i0hMO>8u)%JelMkph%\b?(hWj0k8h8W2=YM'[q
?'*n'b'SI4gW'&_nQ0RmT`SP\Dd.[(.fB-stkY#[Y!"k_5[gL-m1qu7id,pM%p$VK.-hAUU-
%6i$#/fuq0,*&WV5)7:-*p)/.L-9GbA'Wp)f'HCITUshd0V0KrhJ-$k+m%1W`1L`1b10_Fdq
WD!-Pq'!L"Nc>l-/lOV_=4r:S>-c_4d:h><`h;fmJ]AbLJ,:jZb-N;RsI4K*7a-*57,Mg5auj
'_Nomjg_k)ZPh]Ae-#WN]Af[YhqfRfmJ`iZ1:"HT(WLT[cc"EcPF9rsYCoj[.!!(B:U3p+0GXV
.D#iB3NR>XHHU!CmRq8Ug+G58lXj^O"i"sa<h)"QZQp0edjQQ;t?Qt:;@Y9?9g:f:7?3e7H/
M$B[cSb[$^UKRj(/?Ij!7)Bo29XQ'HX=Z`,Ul"pds+?\B&LGUiRD-S!9mNCZL*r3V:*e&D`C
nrF)RVZs?g5;j))6Y=SE,s\^FR9H4/?g76=0$N*9(HqY7"H/SCSh.=H)XZ+YCmFeNg>8]A#h"
_u9:j)o"P+`n=GEicbcR,[@BW5(XFqpEB.mWRE8;r`@$`teG>irouUO-U>_W2g-'Ki(_G.8U
I,hKU7Cc'c1bX^h[2G:QTnlFFBC?blI@[:l71R`<!&3dBeW:"$'e[gi\GY#tposrHA%lR1>[
2GP2*)X;C_Ag"-<ZpDV/e[Fls"htq-n@#t(m:9L,SN'"H$'/gH'H+HKc$.>;#9qdNJ.O>5)I
,B%lb.bLQE5i"JEnA)%9+5o^f?uIk%J!6tGQ20K!+Kj3:W&c)![[ncNlIfe5K+KRk"cHM_@H
Dc5b^S$G[d"V-mYqp+ubBnOgkj<d_u$oARTF0[u7<1AhiPah%<]A/.<erkO^u/FaG<rSmgBf!
-r[WE[qlPV^R"+nr2CQZI!Z+QWbTM3\:=q'%"(37YbFir)VF=Wu*d,HA;i6%+i^AlhRJQZqk
0A>&VSO1n>d\,f(kD2aCr'$WtU=,>i'dW&p#"aQk5@_Tm?#IFQ.C$:1LXJsXOo:\VLjs9^61
Oe=gFH#OJW$X!b=Y\<h(a;7d&SY`";!WCs4Ske9jhpsbaF7&J`PpO2iu;DNljQWq)&maZ+VJ
!-pg$)QB7]A&C+6X=tQ4lNgq-Li`%BL9n+oMWpO!X`>hOB?3FLRS25*Rhb0Ws586jaP9%tGu]A
A'^$'VLXO%<iq3;h&B>rO<+SpptbIFnRPDrnjYGPGVoQSIbsPMhg-=3Sa'<^93q6WfmN\mRl
f%eJL$IF-9A*LSfMqKO+=Mh6Q(c*Ggh9F5d+!uk/&u%Eg,3<N[BgAh)6BB[bljI\e88-pP*J
CRY+oh3K_Mi)Y)=*1<#IdghS_uM%P_ci<$"L#b9`d5LH#t?+\8ef&J84PlSsg"N[l!+%ok..
c1.r=]AHAR=D?-nl(h.[$>*mBhNN(+GToi(N'.d]A?CM!@42bgpC4,jSH:QDP2u`eMLKTRJn+m
,%bh17pq4hML]A.)b]ATGUArb=U`(_rf2,Qna=,7#_>p4GDX#WLgjN,9CXFX>3bDJD>tBbh>D)
)ct7R(#m2#[iEUMPE!2+g-3Vm-u%IFoXj^`bf>CG&FS7YJDjdCKs_(c=;:mC>0hUuiZG`SLr
8%9'oJ/-T67(ZA%?dC']A@Lb_GrCIM^f`;?AAE1-?gZRj$HSGr.j#$j1SSA.WDfGP%7)F#K.-
4Zt6p8N"V3eO'c0:(Vhl*a*KALfq2D;Gm&MHBE'B$oShC9Ak,<KX=93YS;[crQLaespBZf,%
'3BD2/\N#R@CC#ZL_Osh*9p-/hcP=h,BkZc6qX5VfLJR.2[WWk%n-WYc61;cD-B=<P)BgrrO
PkSdTsiVi;mQ0.&rFKAi9RZbqnkI\HS/JmE38p.a%a(gi"p+p3&CLW<+o_pKC$@o32?bj!FR
Y",VUc\K_6N=Pi#(Eo[i13Dm+>=Y9Cl[3VW/VncX_!JPG0jlZu;^5i0@@[ULnNS:=<#!=I=7
76HE!gKsP!m8m>82c,5RPigdT]A.^58Y3r_*.A2+YpXR5eGo_iL?_*1h=C@[Pg;_P1GMU[a4c
XCQu8;3!OGPD>'^`gN+Gfb(0=:<s&OX'FN4kaI0G<,2b*T[Q=.9E+lJuV[A*1\&OgC:,RdlR
`u`.R3.;UY]Ai.o9uUj'nF$Emi]AQK6e]A:h]AH'cAe13QrPI4<3L?uV/J8mKGO[=spS1uCj`K[>
bg>l@5Gfn2i82!`R6iB[GfMQ&BZ9S7p6a&=,Hg$,^#r<lke(Wh>6Z&(c=I[":+'qc&1,0;QA
/gAap0*ukU<pM"0UOj&fQIk-)qhVJaAWj6?Tpp/kX9f:Y&<__4@r[j(>\YCt1idp(F6K5-5R
RB5E)8Y5^%34),0`W!!RAR<[<_R4=84%HG62'h%pa.rNEfEG@CpWNRrBYc#f$*YY&'Q(&Man
(ECqAaR/LfMQD'7Ml:4e!(\,6r4'L#OfPo2(@H<9(C,DVilpKkJ&cV,AP@grlZ&No!k5rV=9
+'0d6@jg9*A\-+r@/\cg>c[J\:&diMbO6sfTH(%-G01JBOQK^&04;`(sk@rp%UO3#jf=>F08
M8c'G#LH&:,pJ[pa3cF+`H<diHl:l5RCKo6W:7X%.k_2n"u5>F+c"ML7!^FuY31$EgH%UtcR
#K(ia[kA35@ZLd@C,Uir+r!5`k1iM>oCqQ?eRIFrpihn=7e;MFE]AV=mP..IuSrp`4PPKba=]A
DP-g[0me9%pn,rSgIj9!IA$Qc_7O?R.V)fBd`&a.0LtPj3l+8K2Rk_FcsDoWVujbb?cD(L)N
qG?AZgc8U\BO_1.uAUTsW0N9Tt2Y]AE3m>MI'cmo's1KJtTI9j0(_]ArD<rF4?<1.HH/-M1UF3
doPIGV2lrmmP@CE0217d).I<]A25=Mc<HsSgsG4e`KQ3,"2NtHrLCc[QH^gNq6LG1902$I6-2
X76j$VO3G"##D+m$Y^:0Hc35Uk[A'ufH"skZXGe=Fq7Z?P"%T&L,\IT7VnY<3"0kRH=%iRJ#
ZDE>mAPO"0eM:m-AiOIpPM\i2_&q+3r'7H<6%6Lsc1hID9%4]AFP;9=Pea6RieuO`_Vd7n_6=
288473"$cuM*ii)Rpukh89HN6=\LiM08GGsC*Q7lk8jASB)^L[=lj-m.NWje;G%94.&W\_Te
^Fgh$^UX'`m#a;f]A+R7<rFs6/W=rJ)[qf<"b31fAU<2S2Y0=oMhii=ZR.eO8JPeti]A.)HB__
ROC29\%AR7+_6CF#e,TJEopUBLakb*-UGl,(6.'\i<"1cW$uJ[beeQjhBG#W-@&X;]A@$A^#(
\L.<>n`31'1b#TgdSN3C$.BAnLJZhUH`1um)Rd8elbg[]AU,0qV=jGE^Af/;h6q6KDh/nYr[,
P0F0ZWc]AcCl050B+V9b$kq<<q\*SN6B.W5D)G38&4&u2<b?H[urs&.Pq;g9kce\dQDYrDj^\
\UL1E*1cYc1Skc9P!EC!1Xifg&N@IgZiMk>1_R[K.)_Y&0"&W.-t%Phc't$)3Fj&#0uA-(s^
nd'p4[T#tsLa5m1*)ubhMN?_FecrlX<\Jm"C::uY2(6Q]AtPXgBj(FptI?I0P#E$LOTQ9h%M`
FBRPG1Z_5Cg'?fDD]AnW4ZZ!FbBM[2lm=9T."GDD3dN^o7`fF9,blB;Vg@@9<Yg^>=,$G7PC%
!>a!+DrP)4.['0:=XDC[pVKEn%)c[EUFfaPp7LsUb8TrTFDpe0d$Wm1iQnPt\aoqGJ%(3^6E
G/[%1c'_c8j/agPXWL7_1<;C,na1cJO/=c"9P'1k''9^kIs&J-2>b;QbZ[&'QHW;5I"p[gQP
A68&p+5]A9"E/f+>3he:I10UH<b6D;Y]AVYB(#W8RQAp1nUmN'XX/.$G+b$@3&guqh.hW31]A]Ap
0I7k-6n>#UC/B`VsIL#B-$8MH6Paik5l*>")Kaqc`&kbQ5G%p4O(2_#]AWs%h2_1XOD<7%:n(
b%\4!/W)DpN_ej]AlpT-htR$9cL/;+J"5h&/kF8Jch#rdrOMCoo=7#2J*#67_@kq>EcWN#VJE
fd(N1mR=II-Pn_KQi^_tccg(<Y@?O0YbAaXQW5"4I%e<G8/3j".N9Gucg*;"[INNUOGc"MF,
2)sF(F#.Yi,0=[0G_0ijLD=Tm^8U2GPk(-P+6&_bU>fKB=W[STb.a(L`Z*gWF*V'j+%?V^\.
bgthWuik#WAW+nn98NWi5/K@ZJJ"a/SNt\k22nWo!#dQi(VB#Z.:2?#;T5SH82ljPmZL[``J
M]AlCLY1\B_sHII[Xbr#!>A)ZZRUNmDFk^8?K>$(bM>L'l"_^W:H^9JT^^ii<[2-%T;(Rq!1G
UF;+Npim:]AWYM)OqJ=6IZ[MDF`q)^\nXKF0E6;K4J%SXq7]AYZOS.5b'u%MsFo0?VJ,V+0e;J
X0S@_fcnE6m`0Q)[;BYp_'AcJ$A:q7NGVk_]AJY3/3s\:XP*eh8L#n!F8Q_1H@tc:AiB4+19F
+JPfUnK@VeB&;XLT#g<Hdf?NQ$kX7f^j)``<KEVKFNG7?^-62b))pUYd@1Bkg2!Hd_"+2o='
mSr<'5E`,]ALU&n/pBI2U,o:dN#0%!fB+/et:q*Dtg)U>:qj:B4t*4o2qB4d!1VdM$X.lnsbs
Mj`l9<B-.k%'2<P)lQsNKn5Q$F!Yg'0kCh[cZ?E@*S;pE4_A`Y:D50Iu2V*-(d#MqF16m^Yf
OpWh7dqAA09BDilJ7jjg>[9u"@!?4"U;?e4Xp]A[gqK@Elda.nj-1u1:(REH9;Yoj'0RC;0d2
tCjCj]AO>7\s?dBK%-+KCnnE1hsQX68EBK]Alq+P2tT"8>p.Z?Ehuc]AE8(4f"B$M`/c.RI281L
^?-@tl4J=60L9gRA$H>Kh`V2Gk/n.>[5b8>>^g63Ig4FV)BmXsB;"M5kuQSTjP*H'WQNu*;q
;3F=lDZ5HLNJ8FWp>^q3N]AF[q&*8VTp==;t*%H?OW,!8UE)]A+b=%HQ@:G(\ZIjF[E?NABDXk
/ri3gND;uMEN+J@?VhpJ7WtSG2Lf2dIZFg.L/\dc$QjZP@`C51Hq`[XaGULG>c@diF:8I3"m
reK$OI#C$W.k1:J=r2YXZ7?do.Phr:gN/fgh+V[#Hs(-F7E+;@t)H(c[[#!S5`50f3Tpjn.a
9;"&XK,c?Z_[A/L$B4aV)k%$0W3Q*_jpQuR4DRWS+/cgqrC0E<up_VE!!X"j47Y>m>jqO/R;
dLS:q!:.hGNAXhrg<6WtJeW1r6.?OlF:AD*'0BJrg`tWV#0b^$g!D[SX^)4bK>';MYp;ZQB9
k^o#TBXR0]AdNMg*MP#NG/fBR`>4Q&Y-.@QpXS;[)h4IVQ]A8\m0^auS8X8HS1AS1OX]A'#_m;'
4a]AJ2*<D8p-W;DM<1!]AYFp)bo;]ACVTg7C2P)m*W6%kXAF&7jSGZbWgUQB.CFn5e0gn]Ag&P*/
rAh.c09G*j%3COH+(3GCT.:*mK:uf`X0bm,@hoD5$ZcC^')4i*b]AhU%s_&t[Q2SY^g^\Oc:3
Qo'4SpUD(Ti9am6"27!mLf2rBip&S0CDP$JJd>MXA"0t0SaWS@bV=Y31Gkg.DtLb777TCZ=f
lI_khD8H*aD&qrlVO^stV0iY)7^=K9rGM<rjX*"Nd%J$3\?R1#oD1N+VgJDC:,T2ooVqE%l6
CbMkp"MsP/jr]A!CYhRq^)'$duiFd@2<rI:=n;n9\n^@gMUM>^o`<d69gQHJQ4ei:]A6='rG;:
\!hG^tOEfSu&P6.Em)R3A"Vh$M'M&?90KXCm2l#jBpVq):(*eu'ZJV&6*8SYJ`!N$FXAjhtV
)%59Se%,d_/^=H>S"c`[oM@_U8)#=IKATV2Z1[Q>=/o#jKks7L.s?&6-AmahBOTEY3G=#l#L
FkLTO;^5XjUB8%d_XY.)AkNktY8^;mj]A'RaBI$4ak&hh^5p&tLV1DY`''DfLt<]AjdmLfj$*;
_qYrdnCX.8]Af@.>Ju,3b=5keX;B>NX^ZK:/Kmf9Rg<p#9qafTJ^@8V#jb,>0CQ0r1!C4lKgt
j?Ui4uVm+lhhW&WDF@U\_cSjc;->*sW;jS/N9_FXbWZ#&[I(7=+!bHA`*gI2$Z\N"=4aWSW%
bhNfZ*P=V+?AV_Er;q?<&2,KFoq?6!K'q<R!'FdAm@!9K>)6\Nr`8bc'5E+QBFGWRI8=10h%
\Ym5*'@B(eQ[:M?8_>2b&89^\+=9r_@`lFl[6t;I9Z[AD@ps0FZV6m2!HdM7_>A!%GfBYlTQ
rt=^1-/jTRL_dH-ck_O[()G>kWk1BG_D/%fX^79FX2Op:!Q"71EuE'q4?a7o(5l*r!rk%G/&
=^-6ZHAraWSEcfe(uEDj`:a-]A+TORh2g_rK@7l9sRk<5]Al3<A&=57Qp)b]AV/TDa?j#*8&4g>
kf07R@S4`-ANA8tO/fg(qnj]Ag@cEomPq1?G+6mFA9oSU9X>%[r$U=hW]A"-M4jft2lNmDYk8q
/5+#BKX3(OF-Cu_nCM0fq6@"WCE2fV="-Xu&U.`\/:Q\\MU#i=Mb[N^K6]A2c?)XgkG!WW*>A
3k:AS-\6`GJhGT4aPdMVW.bF@L`N"+rO=jmlV_E!:cRHLZW5>r.Pen&E.lNkT1KXoH?A=>kK
+FY_=Nbgd%st>j;bl6o6$/1I8dL2L5N>-oN7XJYH3W8oSoJ=\@!*a4mZ4g^Zt"BQkWM6B67A
02-FX!`%RkNYML!f\.H14uSb4$CeP2\RWqf+9NgX;SjN)ji]AS$LCgV''e9lNhRK[Lk+[`fj.
0Pu(.cQ\2nhr?R)M_+#4DhMdfZcp(#Mi%_0bAg'n^XC4q8hs?L<"/GN]ANUN^MoH;gb>imkr[
_S*DG!GC;T-R)Xal[+!4p*6nWnSI*g=lpf&Cf8k-QiBsG$$.BAS"nLB&c!(ga[M=3m]AP@HFL
O5%-n&X@0\*Fmn&@`6Dp"qC>QFPp=gWHV:=AL8m6sh]Ack.0<W]A#l1q*/V*qEk>MDQ+7M!W:'
u_@VrC%`S$*jJ#bcZ(?N83fiEqg@)q$LhY8k\K:kC(=K&e[_2So7a<N%dgT0bDPVQO&.JBT3
?Cs5Q7qgu&01+Q_m]A1!sgnH=SUTcfE%ikd]AC&7=G]AX"6Hr17N.Q335d5$HT9DY(aCXgV,c'<
:67^c%#a_sW$85,(81ro'9Z5jT<u`eq@lhX(@Fh;0"i%:-$Jh=`3\4lV"qp4J,=X!?]A$n6T>
(=8rd*UId5@2-.*jS!Qa\L=7)H8fk$UBFR5!e;ai=!7c[tq<;i8:+Tt$*8;n-qYJdo3+dS<$
c7i*48J!$^5jJW!HR]A!*_:Ykg6Id$luYA?3$0["8LIjP)t@UcXF9U_3bLBRJ)i_p4%Dl7FFG
AF8oAaCE;:TLj2-k$n8&RmJHi708;:/X>9!a,,H'4i:;,P5ntI(rL2VqGm=uWsBoGI_D&^pq
5333JHIL3lhXC/J/dd`+4GV:2ACju@Wpj>\:m[>`HX"4Koi`q@/PenE44Xa\XkUJcpF[:Ci(
7V09hCcY)$9G?RT362po;>T);0"mVX*KY=oK;CS4m;IP'd1oEg+55ku8=JgH5?MPDt\[d0mA
f*g&%o'R)U(A(oe$r;KLV3n^pj;e'_$Su<^S&!_MA^[C%(/AlNREBK,=fba)RI=NZu<qC$L2
ur]A0\CmG'P[mjL&hk]AFCON@YimZd47fTra-sXCSrsjP-'ignRFUsar'>a"'7I4DRQ@HQQ+m/
@,-jEjTZlZ.$Ni^H\\\_kfE]AlGt<_Pa!:pFr6_,FU@lb\d"iA(l)am`\#_gZp7^@Z1/Gh<9%
Ld^@8=_@JK>.LYV*^d\kGPfr/XZX)p28"9'm`_50.6[77SVTO'kqJ(%cOt;J9NZTp?i;TOD1
uG/%2\j#W30U9Em*YN,))1=j5FCXDm"nWj4P]Aik;/seRgZ]AohbV'm#S;K%/@BG?Jo*JRq=gY
9ZdCbk2pl@)p%'kq?@b4J*4jsj0FLREqjWd`Xs'gk/oRcD=9HjA]Am7N*ba7H.i^34f\NLoL(
P[oXVf<n+=Y"%Dpo$+"9tA`t7-4';L@6Xh^/\8bRDu"dX&GR<&8bB_*gZ\3EPLdr_MoQsO6U
f-2s`Y.N1UBCRBt.RANg_aoQ0n=b8/oblO2`PZ2/(kqs/M^OF7-N9[FZ;l=$H(7@63#$Zc=W
n)S<GG;Sehfo1.Qn>,sEn7T;_MJXZQ&VATmk]AuPNhaH(D$[ufF]AtI\:\&C#oBAK$qb0C:N(<
ODP)6drA^)Cf9*:b/89dgd"!S:HEpT!L#Wlh3bE&YKB("S]AG_&ot2&r5FU*39gL>ef%m6JS!
2F1E`WW5k$oF5*\6'Ak,hAk=)oQ>oTtqGD[='#!9KJ<abXkS.&[=IAIr1XWqp\bBF#<HKCn?
YnTJdcm8+n(P8k.K6&N]ANX%R,fm._@q.u^h#fl=l.04:>Bj8Uf?)U<X]A=6<89nIq$!-ouNB,
'T3et(g"2P'=9I'_M3:QV@0Va6EJ5g<*`Oe)O#FIhXVHF\'?J-W1;clE8b`%L@.D5em[.f\I
8X&BaPY#ZA57#QeB7OcL!N<oB:K[N?L_FVrnR?%X3T14_1n#AIGSdY17#1P"fEY>t<S9Y3Kt
r-HA&LQ^&H1]A'j!n=n%$""\bKXF*WkE:-:/eQjJ>?D]A@GBbaq3.UVLemgT*SShFT1tBUX5u2
p1_=AU?&V?RIg=dtbsLp20%:$c=;LW*KC[#EU_+-u26P5$m+,_>]AcF%gRd_95Z$<C!ef-mIU
RabKgBf[TQ]Afq8W\NjP4[*t+:FN'p'HSNT/(1kf;+PW++G$[!pJBY`lh;:-he3UiIm-fT1V!
'`gVM\aMd=G@FN(ld`G$2:VG<P_Q>4rW.iGrP,6oP6CPW+*?C5Rh5!/;<"q[4N'<K8VeYtMB
Ohf:LoNt(:elJ-5)uN,',]A?ufhP%Q]AQ]AURF]A@*\C1E.S,Fm>!S(ePj_%E[;Ak"#*_G\m*9mi
.4?F^=ipT11!AY>a`Ds&X>;rYLp4.poZGLSrgj$rQ-U$d?8+MbVO&!m+_n1hF=4.f$aRfEtS
5\@JHRICsgHj!PI(&<-/qaJd1LDPnV>DTUG;jj=O)$/&a8VkDgm-m9%%S+g6O#l#Ig]A5^4dM
.SPQk/i$FYXoop4#pB3gE^JD27&BT-O_$>]Aj33#,tc]A(L+adT^sjEF&n?ZV^qHNsb,H'"(>p
YtNJ!C,Fu&L+l5K&r1SH)-_KnRj8ZJO2F^@[Gk'EsuVk>5M(Ork7?S]AmM,FZRdZK@N>_R<oA
!oL,DUT[?8#'Jh*B([lt1q'GK6;'ra<cPQ&Pd":pG,oDt+1+[@/m.3U%bZR<.gDG"%O.hF]A@
5)YTFNlZKc8.\d*ek!aZS;7ITt*KbQ<7CYPC"gXuZXpH84'[R&=?4IHWQ0_+U,(4!>7eIBkJ
o=mIM]AHZe1o*8/??R00/*S"G(A.oW</.<+enes_7Xhe!JQmJ5$'92o8rP+HPdT?aSLJ!"E@1
?qR^kA%KF"`/h,XmB7IZ$)ss.d99JG!X]A\Y<T)eEDk0Oh;]A?@]AKRF]AgF@9A]AqtG1/D^>+cQA
ZthTj09#4o^]A(OY]A"Hj^=/?UqO[8TWX%Z4Z*GAC#X(KIS6O/)\&LJkSgT3dJG9[\0n]AC;1`9
i31Ki:`#Pm_<d@TW^H#IdNmNqY,[(;BNmd>mYOrrDP4aVI`CO>IbuuPDKQTdHSRC%'Q[&pp#
s?+X$6GaMjA\#Q%Q\iq7'A$ZN?=LG?=O`;rfaKOh5&<s-#).SM(_Jg&,501_ENGJS_lEVMp&
LMU.MkTFYD9fA[j`[aD-;:SKhRfW#D+\t$6uktZZ3N,5M]A9.a'G;;DV:T#Z51lWESq04Zu_m
P_OHqRJoAn!;/Z2:-qtm[4ST'#5fA:M76]Ajj68^@\$,[2Rnbp:&"n_$,JX<F'Il0?QZVB\==
,tp>9el@I(8o8%YFs8uTTr[C`H`OE[jb6snfaAa%uF?_9WG-ioF>">-@g&5@5%9iu`%'2;5]A
\R)Kk#A3qPm=O;]ANQ>ncO98[jb/]Am)'9a2lk;ueSiN;S+4XWd:3ns]AVGT]AX"L:q:WF(PdiVh
0=2QM^@.)n7cM]A8q-pn\P6k2tl'@_r^8I-2^lckFX&cJ!QM.P^R_0j1Ub$M:'4W)8hsH8Io9
_Tu>%L@A@:8P]A=#P"Il/N>e`'R;_1JIVsi:SGol/,GEn4ul)_h-D18h^Y=07?lCdP$'breU)
?7>37VdFl\OtI3<-(jj4oIglMJ<+D&bX'0Ql'&9m$/Y_8,H?a48im$ad\HtJZ\kM+q9T_]A"2
W:77_T:^8;#G9$AV-!P@NNc.DE-q"cOnV@#F4DTT-[b86Wg5+&FFe"7Hk?.t@d\6E64=X[-]A
r4rsHh#Xb%<nmaqSV('/7?GX2g$dbGRSi1--e1@9?BL6=D6a2TVX<3oHtb*q+@H?no.-3P?H
ollltT`h,F`=mq^$n-^(Mu!?%FEni9kSF9Un7$c0B0Frn*?'i]A'S]AR)9r]A<q]Ao/9=/H>"`..
4:<E@W>#:[4Mq)h';l8qRbp,"J_+m3?fnIc'c(\$KEQ:-J%]A"'"?PA0C0j@Jpmer"Fo7k0#0
X;pQ*=;-\S#]A1^r$@0EU<<95*p,D;`-593,K:e%QeppQ`.$af-nJU[:95\d_.pB1n7FKOSnZ
ii(Meq(^TWPd/Q;CAV4Gdkhu"l9KX+%R1:,T=GAO."*h4I]Af//pfO#-YmVXX9L#9%4pn`5!X
,CQj&2EY8"8>f76Ve(_?;f:!=c//C&jkp6mBV"0X+Y/?aV5hE,;]A,5gr-:MKnm.T*\3"UdA!
2E<2q<!]A;%Fk*0RI"jJ*_YQ?H&K(j8D2,bu>Ze0od`aD(M'1*S?6#df$%5%3lC1a0T`_\O8C
OI\rnP^riCO46\nW65]AXVp2:$+^S1Mq9>/AQhu;p(/1\1&ik?.&7_g`*BSH6^;O/U]A`iT:2l
@(M"F>UmI*^\]ABkg<?ED#3CbA]Apag=A:]Ar]AkZL9Dm8(05oX\51[E<Y/h/+$mI/*G_q*6''.Y
T))p6EpPk<b6F-o:S_t;m[.D8H*n29GU'g:AQ7P-ON=@15MoPr)mJ5k3TDfnA7%2@$0O;rY)
8-hkj#@S^5,j-HCIadR1K4YVgP4b8K(ZkGC\RKA7M"ttfFo'P6:A.D,n:r0mLAQNr([fRBjd
<AqqsJW'7Qs6@S>]A\b1]A-7C?n"?1Q,WJ>@=?4k:Wi,b\!CaM2sOla=eNR\l#A9IlNG(k&Vb)
;Q7[)P,p-f'n@A9SeNDm;n<VA[%KHD,I:?Lm(.q*9S.BoCT22H(@+Y%_7-j&$r0D?CZhGhc2
UbiG&eimle!<N/Y45gkkkY!So`fL;V=3j/qsY\64j4HQ<WB]AqHkYlcn*PIKJhIj_d>s.g_4U
bePE*">#ueC8Tn!*QNSNQd58MMH>ORHY3kZp@\o).?m^[-0ZkL!_@0F%HMk9tJM2gnLDN'Y<
JuA5,;3b&_%%WILg49ZtQMI=Q[!t5E?/6cNZkGZTZ-KnSqK!9_?R4L]Apfm"^^\F2fZQn,lCR
=V<c:$q@Y0"=?[QpfZSb_m3`ddbdgTDlKIb("g<9B:B[1iQDeWo+cEsA)+Z#O9.g*bD[ef*`
\V^5J,fm$dMDk>9cQj%,r]A%`q*F,Bg>Xj00A.Su+/FN">ukb[digFFlX@/XQ3$`_%-S2J\i@
'Bl+.XKrpW)n5_KJBG8\R^X"bIH!`*(%D@7`sjCqM$9a'Elr\:E5ki@j&3Ba:L1e"UjTs9pc
&.mOL6TnSG6QC7(,Y"PY-,#`@ZL&XZ-CM14:YGCDkb]A0MlYC3G@FB(^q)[Y(FT>X+aK<79N)
qFK82aIi%>*Zd.BJ=0'F,qADMBrI=H9L0Mo"U0H?IK9Vl/buKKAP093A[NbF0k"rL^c=rS=&
WSYpeQ]A(,rpX':bc)<d\?Rao5il[)h<Jh?0XrD8<&Pf+J,?PJE,B>J]A!pI@.jqned-ld0n>Z
@Bc$_gV'pYu%[&d@IE[t7j;WMeFOMiZ7s9Ou@R`ItcX,E`]Aa%hS.]APd-h4g4A/$"EG,h8+Vc
F#R5[N5'`G9N%/+[2#B0"]ANt3-gWGh%jui.4=/a&%g4hk#;L3"D;HBmU:bP*8Oj!DXinA/k-
>ZG&!<V2[JIJB-5U3KbB)cdgf.:m9b%.>VU9Jp%\%"Mar"CEcCe,.#qBUJW0@4oM2eL=FR#4
p6G,Oq^8uJ(U$)4R8K5q\Dll8`0#:E$T2YXl>]AC?H!2fYhK.dNcq?9"HagT,d4o&.QJ5_E*S
T'G/#m</CURocK-5jMado!l9>:':I6mZsl@qm^U'[mW.>aNTgD:[d[]AR6-hF$IR]A_-3p(4#B
WZ\t/>WIk&fSaoLZFPGp;\b_"lD\R6(nO5AP=<UEsN6Bc9]A'681696ck+H)lV)^+[Fa<k$NT
X+^oeLa,cZse]A;@<-e9>bZJtr\D)4LU=puQL9d'7`m*%@^uj5'/Xs^V9`bl;7ar-Qj(q*ZGL
It.Zu*VLc[Vt5FA1U#XU%JY`W[fi#*`c*T-_j=A(\ojG4qj;EC75d:3RsYT<_=/T<^)#,CaH
GMCgJO>KEu'$N=/^0<t&hVWZug3DsG^YUoGUo>;6(2RB.I.PobG32\f+5He>)t0pC%(f6akL
C3e^n5M7M<84E#HL`+@@'F?`H!"X\Jb$7*HAcT[c^`anJ%BC3U1Ma4T;*$ZK]At*HtTePBW?]A
TaD;u*q"aTT2/VP\rX7dUGQ(]AQqtfMH#k.o1A$>eCf@a9oGI@D`@o9Y(pMSI5H9B@KZg]AY?i
8KMeYK%H-df&="/"!sfd@Fi#jn-MR#I\@YmO^`t$]AH0GIJE?@S$Lf0a$O(X_XYCu^@uPh%K'
0ZqoW>eeCTK;D]A+P9I8PoVg7sME=b"'hG4X5`k<ij+F[n[UhGT8*/?UHU9Qr\qQ<Koc=4aDF
>`4*$4J3b.`7.cJU.%;-M5<3KNPL,1'pF@iWIm0j6m'^!go)&PQ@9mP[d5#5F@Yiso^p8`IH
?MH)9j%?^cBbrXAR$@:m2r:HAR7[k3JA4p-<u&BE!.5+&@N<Gj#!(YeV=k.@&Ssn)?^cAGM-
^NZ1.RHnEHIfR)B'14Q@ZmJMR!,(&*$5;hM:6V7KL]AS3?FKceEs.L&-59sY=G/n&Ph0(-(Ep
Y<Wa7&Uob^TL2RUg%-c^)_n;=?r6bR$6,<5"7O4F8aC.^V*=>6+mXD[Vb@Fa-Y<g%E&peoRY
?X<`jF$@i:HsH#-S=7.kG)?QIc^BFnN/5X?9<8@J8o(UW%O-TO.kW>N'uhPg%`dRZ=b2_lUD
=;aB3'/Z`Fd?Bp&M=[jhCY-5`JCSYuHO4D,r(P'.'W+rain6gUFLbj#Gc,%R8T30;mRDb2d<
/d0jO$\g?>\:HBqHF&<t8;JN`9+,?`Vdg<Mc9M:=G6?\]A/Y;nZ[dTplQ]A`1/GJ7fT_fVe/F#
Lk.f4?pU4h=5Fa7[F-CUB"b]A(i$L<K;0h=V&D9a:GG#7ahY/+A$8M'WXV\pWQReO:gK=fVK@
bW^p8eM$uKo>%lQ&5FUcaM+77/&StTj,;V,d3C;G=/gFq0Pa/"`+J2kOGIDM[]A)^#Y/.E%bN
hf3RR:?9CJ!;-R![_K]A5k7:6K`F)3Lrai(tU]A=J">Mn0Q4:?[%t-_>_o,c5^Z:%Kp9kF:*oh
Ts,IYCBG`4ZGq7AqH6et?k9M3f6\<%)b,359-EFX(E]Ah%7Z/NIX]A4Y_3<OP2P<,jk4Clp2a-
kfLhYY\4h!'RAN_be<8qs1l/DjIji^Ffn+%a5nCk?t)cJ,D\)b=nqc#U;'Y_>I_=1b.@o<or
.b,)^=Q^CE0Z`4f!$QH0_HX;DY#HXcqcI5le^_et4p`$&G%0dD'g3$;0GcTa%8=G0Eg")n%d
?LK9.`knON*IB3Im,W:b$:3;<JYopEB;q6?/PO_A9bD0gnNPEm:Rns&^kr8$nKQsRr;c?6dL
V`:,Vo5^&L)HrefQslWSH=RNf"I8*hWU[`bg"'#UWc4Q._Vq"u_oX@jkcpWY"C$O76/gi**D
8$ThDqG%#dSZdGj(LU<p7?g[-*W;eID"1]AG]AZn,[lPC=j^VY&Q$PEuJOFe0n)6G-J.+R'cD>
UfkG:V(-_B<Kr#H,2Q:f%4u3_rW.1o;%RGV?C9)8IA#^Ze@Sk91;E?,^n+05:AOCjq9>]AdJ`
`i^(O/Uj*UnAh!AJPoPg0W(^B!!a%'L4e.B&dQ>QNFBmXGmM\_L#T0.kfiH[ue*6D2lGTkI`
3AII'6UJ"`gLiqQkV?F)A7d>2T^D=rpV--<epA:b_i2RoK=PAgu!p2A2?DBnp!Rk5oFN=s,N
k:*Is=,HZ"/7T`JK3S,nm2-Fgghor'CTDaM&brG<Eq62[h53qqu0Wf3=Lq-5miM$0a2c-_+W
1g;5s6+]A/PU5?Si'6,XS5PH+.)!m8"Kb_O)hn#_`LPf']A(BF#7/11*':F87R;t&`B!IX/Gcj
O\Z]A<Z5gG_Z#`9__c=RU]APcCqV^ZE\uYRPE>(Kb.p<s4/I_d-eAo)^2DAlk.B8uO]AG1sUe^q
2p\FW#/EqBm=6+"j%h>`IbI[`2Wu\Q1%![6(kAG3=AgO2Ea+AqUG%F6L`aSQ_324Nk[C4ofG
<5*XQ['fnBV'Em^Z4VI/-W_\8k+i\E[H/IS$ue3Y3"d<MYg8p9@iI]A_/*:GQ9-S&;:47V:Gb
g2Cj8fX$>3es'E8%g]A1Co2DG)Tcl?*`?]A@P#"BACs";&/,(T`[ureae5iFHnp05nd3Yg=r@r
:-uc3X@Vu`-+>VD';GWkJ]A)"Caf00Z(;bW9jna0l4UqNPW<]ALs?j5mP9qf-I'"pW%'Jq[MT;
dk%PLfJi=Ph-s;rgf]A\NeV7TV5_-O//KKi)5A^msE7^4(MWfCRH`mn#?\t^G"a/%.9Z<n5F^
GI5:A2SM2>H,q7LL%F+6m"NFTU_&=M+F,f(R[?jfI0i9g"S$GkEJA:"Kg8!!kn,Ne8lbKRPA
O0cXIsu6E%]AV0FLu^\g)8_EV?2d$l)]AU2*J5[`I^@V#iVQsAu[.QoH5G&\EG>oSNE_"YODXb
E0j;KGNH58MKVs*;@Imil?B`h5MNQY#r("F/N/lOb*7[`k6njhtZ]Anrlm-iXO&%P+8dL[W=H
WpbJoa2<0jqV^'&knBD-7S0LdbJL(I/,!-g&&ZKnK?qLk+ZlDgKU-sm5I@#NG?U1@e"u!Hp^
DJhU)ucN%Wt-Br_9f=@eosjWE+p;]A=@A2A$J77,]A-"L$<HXp*:,'Kodf9=[_A]AI:EafaaXEt
JZ!dO3lc^mMZ?*0_h-epq]AZ.QQ%dni+<NfgX,fUiU?]A=7Z1U1lY'4jKmC=<4'4-ADdHnGcDd
ierWY`N!>>P$/SF"FoQN.Y?!fp77s&O"]Afp:a-$ag8'BH&^)*D->UKXV5/Z'C26+R>5g)UIT
BL!fmR*UPt7AQM,deS[33nK+gSbGEba\f\-Kf#ZUedE*HN]A]AgD'nnN52U!c"gR`%J4\Aoc*c
*G`t`I__MX1G>.6OMZkY*d75b)'HfL)7fGX&d<Rpa9GsX9O+4mpjX'?=k9,5'E;gk24EKdU=
YsQ*^'1F6ZAlOS_EFPn'-1Hcaj&fqd@+i'LO:[qEH->'G?JT0mrRq)7UU;mK<$Va-AuM4c$I
o-5PU__lZ:FG"qF+IQI""bOeU;\oIJ**DTU`TICW6BTjQ1j!D[fL6+d_Fb1hPG[g]AZ.c(T`3
Y"8ce5-9/+o\<i')N7>NYeVnQ3[d]AIdVIi[1QsIEcS4WjNcj-I(%QsbUD,-f2_]AFh4ToiV_B
J;%a=]ALL%l+:NR0dVrZ^i6Vf/DB>(uf&i$f"bhYHD-32iR#G3ObXk+%*7>5'9#\'a:)JA'eA
2Tr?/^X1h#AU.KTBj?'2lk\T0"1*GO?;@To>nFnSNWZ[ARKXIf5*&jnaEb*B=RJ"0nB-@"Cr
,kVViHQ$:]AM"VYd+F,esCHc']AF2^'LFBXR)3^R7qod]AYDQYC\X\!<NWr7!.$Ucf:Ef2XY!<G
jaN5OFFQBtrbsBb.flEf=;HT/-]A,Y/lnZ4n@;8oG*)TI7t(&**$.DBRg[-&@NSXI(Ca6E,Me
i3N3bcHBI68ibb\h[-&<;E;qTeN+d'm5g,"+nAK:&+GEkJ:':n]A);4(-FNTpl`GK=:XBG7Ru
+AR/$oXaVGmY;eqn1PP\$+_3_qH*CNe?BDcIAnN@mQ3.1E3h_c+XbfUh=#V(p5SkYSf`M70[
+ma,JeW?Wfbsr`E_GjDj2(^c!jFVXhl:WI4OLFB?Zb5,L8[t%E;S9,"^+Aq:M5>#[JIM7O$_
7Cik!s*(&j_d*"RtKdi*D-#<[QMZVE%b?ltYM6.*\r%jHX!@]AH!i?M4LE:)8K]A0(EkMqf0jg
ZnTQa/N2AO4org,frV,NkN^MAa&f$!3]Afg)J2=roZC@p:T$P]A2U+2@5@0uiRfR+6VtRLb#lM
>%oTf*8]AD^#gnJ[`P3#Xsng>'oED[Lm=.X9/*A#gO!,uf*J=QI#l6Bdoum&itVX'5T)a4V'S
J[Db_!'Kn8(8Vjc"4O_8ju"'"2)PA_OD$slPN,V3h"8%Yn6kS9l5g#:$gf^?-jV,c.ceo(V.
Fe4DWD_!h$mC^iq>+]A%2H,2Faks%N$ORD!"IEI`*S.eM2Pc>qHO>QgTaL.=9Y9%OOs6cSl2a
G*n\b+)=%?DsC;#_tQmes;Vqjj&M("'/KA)dm[8udp<rS?l;"cj`t+tL7mINrNr,rlY"i.hn
n@"6E%@NV/h&AiiBVM^p^`B?NXq;5'75Oj1HlpH?UBmhBLfXd_#'SF;4MC0YeA"=rXYb<e1g
ZeI%k[p^8m%Ue.5Nt9jY;$8_qr4Rc>r6(OVM!LPAgt`M8il($,5fC$QnaI=LsR]AW,p\O9&(N
dsj#OY4;b1$U]AqO6B^B3(i>-a(Bg2)DTh2RPZBW1Q<kHqXaeDCpFS/jr<$iAqY7l+R0:Y1a]A
cEU[H^]At,%+rK2BMsDruR!Q?oI00!YkG[WH,.`<p/.S7X_Jo%e"nt?<b5hE#@+/9i57WS?'l
7m0o8.6E,]AGLV`[$T__W-@2YHo+ljFZT.+j)k-`mj<^S^,NA2^Ke/i67%#hFT,c,"=Jc?am5
Y"nHdR!8M]A3BK,ldpu,$KK4Qb:^oJN"n&TX2:TjT7;m3[o\r]A+(p+Gcu0au<%;*\KmfL.sY^
K!#dj[4.uSEjKQV]A.bi\`5bqXbW3f\H3Z?9\/5l0X&<fa#HLJ\AaTeeMn4I\r:T#NiE0_ED]A
:E*^e%lA9=bN4S,<1VqHA0F(o.g`erHKW$\``>ikT%3t@r;'k/KPQc'Ah`\!4E%Jfj1K.%FR
A]A\4EF!+QrflZ?H/(I3iN^^iilLTJKN+V6D*SM=/A5Ml^[dI<#/NQT5Xbhi"6HX5Qc(5N2B2
kr4-Hq9_H\-m^hCYHHos!6Z[Olrp`\o3f5cBqu$r5BabZ,.1rXSqAEFKsFq&%\/SPZIna.84
pa..#I5tc6/Fcq(\eP<2MTk[`e>KuHFds+,4(b+?A%fRYsH,h,qroW-9(\>%s0PmIIjsSCeS
18HUJelD8hLi`hYQm/gZFSQoRPDpnDXj1>p:2Vu>Q?chm[WDb8/H>m%I-+l33FX4bh@&;C:O
tr9^=T^RtCn@*-KRY<>E3,lFP9#Ok-Qrmr<5e>OqN6LsaAh<9\MSqP&IVoDej:[D<aQAk@$7
9Lol1^1b"Cc!0S%(9V$mqmJeh(j^hj_bn9]A$J7$d%O2UK]A2njR?9`E4%T_5\R0p*cEN97*%G
L^7M_i(-#nt%MJ5O+7$B#DrE69YBY,['f&-4=kGd:)Bki`A2ns!5ILm"@Y:)r5WH:')]AQ>Y0
(f,0h@Z9A8,C8URW`Umlcb%LW$A4nFfL3$3%V;nYi`1*lM0?f3)fRNc>`J,e/ILo[KmY-M;B
n_PkM+DQKf,[-L-;FkUDdTt+(BgWHp2o/_eY(US4=4;'=q^1sFY"G$B]A5$sW;d(1.i^uIRnh
7nl/8YC]A?ME;M3$91Yi-U'>-LTn;N<t:/%_L`dB+^@C,ubd2aWa0gLnbOjR`gI.)'M.SR+Zj
%]A=`h&lh]Ae)pfB4h45lC2@rN&0S9N6BHo;:X(5qClA!G?_Jp-fL,,`&@TScg%97[0Cqjsc7k
$ClJo?(eXfdtkg%0Cdo2rljK'BI%Ha,rU%>OBLD7iZ0)iKdJj-.+S34MT?6=;e=:!Am-@".T
o\&W+eT+b\UB1M/+NoPE5gC-'qf?g4:E4>#dR#<3aR9,OkVX9/F`jURN`S:*%%O(sjdU"l)c
.1mm\h1rh5A*5^=b;;<DdEif:ShB,DVdUu'h)G;gQqfX\L*g-?ts3=%'#Y\kZ\'u+$egSElL
$\G2gd$43]A&XDWg2-d':.T@@'))^"#,S=kCn7aB'2!,DJ-qiY.<M*lJ+q/e=@Y*,d*4@%oo'
"l9]A=+GBP9W2SsK5p`;N='`E&`5XB-S;E*TEJ`0'mn5_B1OOPI/K>2Acl8C\o-u`Ja;-oCSt
%7IEki-J_`MlU^t^dn$5(bhAUM$eeQeKg"T*s\"V7'sY/Zt9J,uI[_tacO:Q`0]AGL!h:p@ap
0rY5q2+T05eP;kg0;lX+>@$c-6En#C<s,7mhaqb7/)6ed]AbO\U&f[%M!@fj#]A*U@nJA?CbYF
rg']AJj_WJ7h3.l[A""VG.pIP.Bl:51I]A?2>!+e_Th;kfal[33pW@+f$iC!XIej;XNDc,?olu
m\Y.1Y9#AQMobUb]AK%k;d!>OXch>M=eaon[32?TV1MA?e(C.,[k2rJ"HI!$;[K122Ug98duj
E!lK6g9b)am:X:5c#TuVV;fR19%EInWY<>:>?_W/LJmA6SD=a;H1Y1M(u:W>p"hPpBm.dr&@
6s)SYlp6GU14t`(Fbi3\Ci+ih(Z8L3GbDL>5:*J;Rp[;(Mnt_D5=,q2M.6nSSj\2#BUkb!$1
<G,,F_Zp1`Q5=PPY7^0PWG]A%=,fU4XDa^)70lOaN=o\0G`]AQJJo'0.Up7eN>_i7<P9RnP4YZ
>.5jE1R$c2_WnC88G5m/Y>G[C(4&ulm*3m(lmqY5k+>0e.RRRV,TDnoeO54:VLhY$,jBE-0(
1^Y$i6np3'r*-NfaiU)\ZO(UFV<E32jQK$_)1@]A:hLQ*.fs%_,*gacAs1(GFIiI6WflZX`^?
SPdiCD#ltnp)/JD;=*mY3i<,rQpQ2O\?;,=05r;q&S)6_/7<[=b5U:_6R.#/$pb?`3;UBc(n
nQ1Z#Y2q`cW8pZhp80WO874A-CL(/G+:XSH),s/f3ekFL$[++(Q^4FeMDA"+ar;;AS/0`D@W
*XtF8-<4MbK$obV25cW<1U2#BW:3KNnepI/TcLbVAkXu1enD"MQau<7;>j1fjLUk5n#4l<[C
9as\k[%K9.UN`iiUNb<0:,*jCmii\p;Q3%$qg7hbViI;B".>4YCZ<@]AARf:91^/EW4qE%\e+
^\o*=iYX^?U2V^gdmB^f9(T3)pRXlPg?j%Xn;E!3O$KT$-RT$bF^mL`Mb^riO:MpWb[.1?CN
BV.Y)k9hb#,An-ECAKhKlF2L_=q!]A"+HP(l#7"?<-/KT@c5&9`L$U&^]A>l_Uc]AM8[Rb`>0<b
`j-ojafk=ac0Gb.gJ85giCpeY-^@NcfU)=^0it([Le5cqe*sCZa@G$()k^Rl:/l@HEh==mpB
G0S8XpY;i'5JE\1-dl!s+BJB@'K+6`8.#[[KZ.f4N*ZG7D\j9V;+F(TK!uhqYCqG<qo!XG.]A
u.'6;o+.8j"KNf7W@Pb!jCEUCULZZO*uo(6IPQL3*ElUe$^a,8+aHh^VMlo4Vbq2E]A=<@^!>
ZMBhgH3GHb'Y3gC<;VM`WHC;$3[:UFPpDHSk)\;gm7)mpKe#M3(GK#VPsOh3+pIOMpaCb-tr
D)7[-UiF,BaN=5Sl4sXIKHG.2>:gicY_*)R4!#cU!:HM$bUsMKTU@eQ06mC.DmlTZ0Ff)\rc
1T&#BnX;-LL-9E&4jK`2XE-KCN\0/^h4WIYuL!05m1m]AbI*m/O"8j.9,!YEkki&TgV:O:4kU
:5s$^Ce\c*uHW6,%.3("*<He_KPGWJd8@`djIe`$Oj"DnHjpON"/b6D)&db&RdA,C1('X)G3
Gp9[0U1J/Y!20C5UD_K&b5(;+B_W9F0L>MKRG_H&FKP&4I3qi=:'5eL$uPtqJt6TMk5HIKso
(=4_.LM.@/R1g+[(m,e#bhf2?Ml%g0H<YT9+t)sq&A*KW3%?eDI+9tZ=(//%ER:Sn9SVauYb
o5^kS,*?M_DCUg)ZeW.N6ltM\qrJR`NSDnAdTMu5fXo]A*9YEcGjkooI;:"]AdEW,E4&b9<1WN
A+.Wf5Z]AZM,TS69!O@O?0Jr-+b#`;8\VRm'P_:9e_VLLsR3gQ/jn_?_;@49^lkLCTm7&_EtN
EB$EeC2TZq^FdS/PSM:l3=StRP(H.&/=#S\60UTPM%aECjiF)!QKW.uKL.>1960X@6ma3UEH
S0B3T.D30<p:195=/.DE%G(jWb!JK48m9jMJ"&SIP$TsG>m<&$)Rlg&Q65%@fc-*rHVgdXtI
m4./54]ALasOhrUd(\e3h%mcS*hZ!dWW`0=Vf4F,Ac_:d%$XJH3[YgpHW$.[qYB)4':R9Eii<
i_/M!KA=i`2BGhAs12.No20fd_[,'"H+t>GQV7M#B3pXsV(7*74'2s@'JP%Y&?Y-MXRJ'>g"
$!;s"b$n\JhHOL>DrNfeDf'W1n%7&J'5[T;#GN0C5o&oFBfhO4h>=/&fVY4!]ATA5i8'D?MGD
.Lmqu]AVM\<:X+*?3\1si:4h]A>u)Rc)@!?d+78u0oVd6tiM";nKZL=`#M"U\LdPAq@uhBh_PX
=!F3T-CBBMlP"XoYJ8/1j)5h`?3`3Zpf34@Ai7=^MfP8b3i.J\DMbWmDB)T<u*F*j@#d2ok:
G,C<7$lS;C_G&m5FAV>5rPj5V@:95`AYoRK&gYV8Oc`>@*XQPD&V@u6b@%5K*dEIicqW0F`h
^N0u1Srmgj7QQ+@!O%%[<Gnft,Y'pfiQ"X)"#JgN?CHBbPkamV1"Q)8jOX9qN/SVlASNb,CM
6&%%6X5s)L`<6-+d+jP&_JM1*e/4)j,)nDFq;I,_DD"!Nb:HO&3pE"(mH!#8"E5[ekkKWTI"
Y=U$>SYr83RpWL;ir>9)q\kN-;-s'KG_+mZQfgsl7WOFWAMSsG0%k$(QoMS75/B&^g4g3]A&,
o(_>nnK)CZ$6"R^!=T=?b+K1`9jS11`,c!`4@NM036L,\_3ji[$o%>l[p0h%J/@O)JO[")A;
HS#l(:("*3$=0C]A%[oBURg9'3RkDcd"*:7n""CFf*BOq^LEWUg8`f%#`4@bf!sjGi_X1/hLU
)f=Bpo'FKfL]A43+)#+&E%*F)!("j+8mC?n(GdTW8>Q>WI"0ioVps`tD(s4NYN+R*%GsT^AAq
Bgbn1T$ZE:`l\*SB3;oN!aBOFc=Gd[)]A>8Z'\1DG#_&r+3'<ijITU5FfS[?V^+rB-&t[F.6(
pX!?8dlG47Z"i[..L8h[:M:<Db'!O!ub!1B'"8S:MfDXodla/r=70Ur@G/`tJ<(kMrm\r$VD
%nk&YAi\'6mDAF"8Tr>S7TYLmL#GmV7ejTYdo-BhD,D!^LmW2_pQX2/irAEhC-O69C/FCKpm
D:A\&XT,gq`sC\O.t,8q2,B7+e(2tJGSg%&66fkmZqp3ank,ifW!X'7#Oim&5Gp#)08P17'a
/(Y?jh#<0T3,$R^-/c/AR58j`*5>4NM.*-h0%a_nA#o-Y4@6<"A>=_$"2U/DB@pQQ7Y=S>*#
"D'9f.ualG'maUYP<YBYN"JE<]Am]A\ZF?i#pX>1$I!9+<\EVkW;Y('']AXg>5:?d\n1IPRi,Gt
T/jP`^T$5)HRcM^Mc.e*sdj%`$:QJX`H[Vt3\/S@,heJ$no8^8j#1j"m/+jse3aAr#ccLGs;
9V-6//79GocL(QhnPp71$SC@MZ)ER0,:_;f91%\p-+m0"CK<TcgT8mK(=L8TH*OHf!b6R<Ui
Jb4MS4$;tO7(]A"SF^_4=T;eAiJfi":fKn`n6I8N"]At%<)4""fKLoahF[KC-71YNl@7q^CBm%
KdsraY!kqlKJ!h.Ut2Md1=uk$YJB]A;;'ff*P49>q=%>/Gh6!qr*#ha(^;GLkc9i)U]A0q6V_A
T"`=lP\"]A?Pq,C4$lbmoR"Y%S2n%I$Fk*Lg[*^D0u'<^iM8?*m7-g.uWl$G>C',@,%(JA6AG
J"3Z*pei=$A23BaJ*<@#lD2c+fn"A1/&XL#eU>)5i$g=DtZB@Z\+./GmQScZWQ:/mGq.X6FZ
K2=WTY#K[.Za"(.gUHlBu'I::@&ZK^-EhZlr;+<^hpa4&0LO1&ho@o5kT\`b0@>=g0<i(2YT
(0F'=Qs;<h7?+KN!uRn)YScP7DS;c.ZsO+:L9oR>c7<^/k/dX]A@'lgGg[XZ`(Sa%gr<4>K%H
qA6F<Nl`<ks!jif\s;sIs-1clft/h2&3eoePD3k6kD&f-OPYDh2#QEJrJNTBrcl@t`$G_<V[
Tphqf8Kc*[msGiU5_3,6k\B17-p>q"o/g?U,5dc1;F^.k[tN.WFd4V^#+_e!RW=.,V9=-(e\
i$hin(q^GZ4S53>5!Pa2/N]AgMIJlg/:aB*F?(FQ";B,Y,Dl4,b!(P!`7:"Eqb;LcH*A5:rd@
KMD-@O9Bd.I+7k32(skTpM#*A@-DXb`*'9Io0gF_4_*]Ae;q%N6N$iKD?1supf91'q?>l:M_j
6(+$.HnIiBe$2fS>?"^9euS5+C*=m>5/`Lh@_V(g++PdEoN]A+X.I:'*(OP_tdZ^6"3&Vl8lm
A:)=M^Ij_DXe?YO?OKnGDalk7]A=kr]AVr2p/F@6+6mL#)dJYfo->&_l#%M_A<Hdc;#+(JG"j:
FMI^Nu$P@%F^7N'/['hron:Pq+g.3L)OHEFtV6R<W-G1A-GuQlcLjNe'(iI&Y(,9"dF-Zb_%
4a"0eHC'3\$19"Q"dM&/'C8:U(O*NC0JGBfZ`:XttjRPCk2iF8dFdacj^dPoaPi]Agt*2!ntB
8I]AEX(M+0\q$o$G+MGmY_$p]A'M?+_E.:]Aca_tLqeVfi%m;VnrGVV%A5Q>Y94Ht\\O@1_j=QX
F.qI$Mf5o)5f6F`")a5=Iu`9Zq'?Z9K;-p>"n88q9tGn"=`6ZGLBi<lL$3o1ptZC5)'M_R&M
34n1\d:ml`r-e,_+WJkk8i2lRT$*ZV9U".mDne*PS&s]AfW,GUu;bB4+7i%U\S<.<@r]AW,K0*
beq>)Wt(mAu>Qllh'1N!h+M>jcW$>hZMC?c5TYC##qaT1'^)<^GHJc<Se4J_fbj`SES'.jYu
Z<NSA_7\0]AHjCTL-?P%k)-)4Ws^rCgOBWc7?R+A$=]A/$BL[j`+6fUdD^bj:"UC.B9"l35#*d
:U1e!9\e".<3B9>BD!8,-B_6TBLJp%IAo:7kkqP,VKT$0WVZYbD)#+pG/K-1%UhQ?%*Puj1s
0u7c^u$VLM!@:24sXB9jOZF[D)+*$FC`lg%WO0'`OKl[k:\d<oI6qBIChd[.D0_%0PC2E"I>
S,gW5(!UO.<k%Whq<i%b=[a2?]AWN\.L+ABmK_TT<^l`=R'gc/ZPNYXP]A_R:;XM^b*oenE7V^
*XL=Ltn2'JG4T-/SQd6@\(DhO?L^k-,s)j%)?3dj6ULqm$4RhH0\I^h5P@PaTE:(qI-S-I-E
8^kJ1*ZQ"V%,RQAEgR0(>mgL'cm_'k5C"eQlB(qbMgQFoc.QJ<XY2??Gp_m5Si*>XR]A=SkN)
#[caHa_MP\,neLE'1&kL/q3N""sr%D/h9"]A3U1h-<e?4YQf6(rd(Vab^n+g<m1e^URB!W>rW
BYXA^.F@LpC'@8*;QWdQ("F"';')r-iOig58)aXrgPrmY-#O/3oT_Rq9`FE-r@ir6r&gU8%#
MHQg=_QY(Cpfcf$3WW4m&2'KF^1:@$_c-mS"H80bnqi+1_+-*q1=Y2p,R4]AWjfrs4E8h>ZCD
InepbF7qM8"qu>Ncd[eYdK-GfV;nIN@3!J"M\dZ/)``4kL#I1#*_c?co7=5^(!CfuR2!&_b8
d46BEii"#D*79auRQq7UB4"1L:/ZZOndnl_ui$-lQrAJF6\8d"uC0?:V<d?RZCTMTm'L,$0,
pk'>bJ4AL#0?<$(EIlQm^ge>cO"?d]AF0)Z3=6NZlp6/**_8!N1XPCR(JU5]A*Z+WUGPnfZr,]A
=9LhfW=b;YFPnT8/j!iq(3i,4=5).k]Ae72dG`b#kB\4j#g0Y0e>ShGVNE[T46K&AtD$qYuUo
0^B#?$\<XHlP^CCk]A@<@)QPBL(kp6SmH`khKeK8D=IG^f_uM4L?A%Ul/%C)Z#URKDq!iZ&hd
ZI1UN_Kq6LV=aIts<aH8!&tWI*X\;bk$nQF[kA_'-$S=MVU82I:=<m&HICFlf2OV0(u?%iK*
3_>%lXs!\RcJiYTBI,YE2UokYV;]A:@O(MDOKZU%j+Co"Wi^ktf]Ab,-c+na8A]A8Bs4]A>,h#uA
TG0mj5<b?4o5NY"M?b:;Vr%^QSB8>(/!#%(J%\03(Zh__D<.'e-S?uJ%I8gm&'QL?Sk>&ae4
7l9R5:F?#X,8.H[@aXb4fZi48&m_;TVE4[!1'#oK$75*Oe`H?bc=dRJm&-2HTN:s9m$c20HC
)+DMUC)MiM"-CCd<f:+slVfLl.#,E-eG,e9ob!Nj!BqB/KJ8b'7!B'PK1O.R7]AigE^kj[;Ft
^@jWu?mYBlXqJk2nAt,=p!OmM2N3/3.1f02.rFlC(Nt62t*ZLMg#B)i[G5#EpJlp<F$pa$h`
PaUF/F_,pGme&HA[G!@-$[Dm>^]Af$q_k?Ko]A;V@g\7oSaX*J(*1%IX7mo:m*Jr%VVS':i'Tn
AiM'HtCl]AOb+BO.<RfVm1dk<m[`@V(pPo3G!n2(H[F<4)I)s,M5`(21plJ<=:<)&o9dICX82
4+BB,'EZ/'17;_/8Jr;9"VF>"G5))9N"e37^tbI!qNQGTnf6\_8o2cR-+ePC:-EdLjsVqWfW
(r7HsHB)YG6>gfEe7)]A)Z$CA]A_d_b]A:qPk1F4o688+aT3nm1Xa%&;_IDJ/GA+F6,%r$esNbp
\EbWalgDKcKRAK_QBbl=$^?C6*;i%DkVabZGE`-3ql!\?n#s.033pT3@!"3*g(C'JNN6KWau
kkT+tipk?,A.n%A/8@#UD!Y.ZjL(Y%/-b,!0?.Dg[Da5Z-H&a?4HP!D7;h>H@g>WrhC.>V!*
lin(LYlJ;=,u<]A*Be8'K#Li]AfY2KO7Q*\sDlM'd;LH=@lT(f<:>q1M34qlYiQa"jW/(F;qbs
M[%Z]AJ8h+s.=6pcVIQ@o?c@@b[Wo<=qQGaR4p53raC]A[$\\pi:M5i?:3_Q)ro0l)!H]ArO2p/
#PR6t^b^]A6([C4(8iX/t5VieX_#cK4/q#^e+Hieq?B2;H]A9^BP/.34Ec.aX(7M`:%%^*umTu
:c]A'<FV32n^8lCOV0G:F56T5:E\FQ@OBa_*7bc?^EQQ-h[:p`,G?a,brC)d6I4ABcdn[b-*'
QF+-m&HT"ICqUq-A]AfEjp^U$DQLmdS(@R$BsZttU5Ll2WY1qgkVI!:>=YRo"'^$27gl7DMDN
>ri3qQ$mpB_0XRpsGH@HWq'e#EL/k=B(O8BoO"T9\6$<PCJlKKu;R:3mrQS+m&DY+uN0Q0b"
1kHLA#I.#$JZ:hu94dA"J3^a^a@9'kS?(>%B4*?elfj,8>*Vrgf,)51Kt:D"G'o1a>&f(F;"
m3ImBcOjVYej;/$<R/XS5N'LTED3W"-N!N%]A_G4f9b<!LBF?k-\oj;W:`[Z=J\s8!=W=;*cG
GXO2ltX_-%j+l;$l+kcHuoN(`k(2iWJN_CK_e:<_OqEYD^>scGVdrfd166k@:mr!?C3*lg[3
h=:/n6.Ge[-(&+0P\oZJ42Zpst(;=$YpK353L?Zrb#'@u(:n:d).m4`cdR;Un,UJXeq&!9bh
lS=8jO"lYR0CAZ'ltA+aWB'Lf&hJ)aAY3[6n+F&!0phfIb@_\%Nt9Hs/<"85b./O\(0:6W"$
k43ndIL&lE57>WsrpA-t9pSB%Os?`QjL+,[(9S62]A>?c%j3,pdS%Au_V+QlXK-ImE9U-&ad6
^uoV,9paD;SO]AK;2&B[J;=9&AZm[4[lSFc*`n?W5]AW%l.\d!bOLC"#R<M>mu0THJbV/F]AP5%
/X6peI3]A]A!np,p5>IOD1QJZ6]AH4cXp3(1kMIfqbED-$<MhSdeusGWUT9luC<OYBD@iKqcl8N
iZe&:?WYq5gnr3F^0oF[a5]AT7@(@_!b"d(65+m/@hl:A)'M@gTE>iZ:ZDp6l\&a8%@MF!\om
[f2$:gapd@1<4<h$W7XmU\t!Yk5c<I+N3<>0GDj32e$#g7,n;6-oI5`>c:Ua5!n!`c#<A@G1
/i^L=mf?c&:X!rOTp`&Mr1,%8HFrWdL_+4oSAV@DE+8CV+C27j;m6Ei-FSSf0NVb&!Ve<N;K
KTC:hQ=[iM[t'O7KRc<c8<BiM2GNu3W&,c66O:o=V9[G^XVe!jTJLUu_@eA"d%E<P,!lo/6B
]A(W'k_1Bg%k$UqnCDE5(CMCnC-IDI:(TO*Tq\hkA`'=j"9epMG!'tIGW<^g^E/S5\pk+>T+R
QRuTQfk-bh8<.jOA'`a_0_N\l;p%^pM*!^'CBr?E=$N!)!r=4TL_]AWrIr`pm"r:$$dll+eI&
:ZMBLl2GeA;%C)3kgR@I>IB10W!KAY7S:,k>Plo'KTknPI-d6).@ZXiaN.*O'5fJm\9E;K>^
EkLLeM`md2"B(lD;,DQdG?DQ.n4\<tGm7(6Uhk53&njb8*fi0QP"BC$r,6]A:ssRZgXaracb]A
.p]AMCFVN,`_CA>_=NPEdL#5Ns(^:Y&8X$\&1u^HYZ3qGe),$oV@cnO>;U"I1LUCuWKn5fsYk
h=ko<Z`9hec.Y'Jco-W@Wp,ljOjo^in,mThKRQ*$+=m&tcP:d+nVh^LN=0_[dYOR<rI(*nMk
G'=o3sfhgFHDuB.c^hq(ulE;9d_FL9TiqbUZ>,[5hnEbLVU>Y&BK,IsZE_sQ@C4N%Q4)mM,f
Yo\9"pQN![/nk2#[&al%LfT^'>$d\(_GF4?pgAn"VD/j"q,EiNq'4O*td,re:T=E.G5X,'=Z
K51I((@6D;*BG7#;R)/#ZKN.)I:i4gd1\&@:k)ON2ZRV#s=]A9C9mHq3/'m:]Ajq>d>o'/_lVk
EjjSq<<_Z6T()#7E.':5M78?`[Bh7-kG?3V:/@6-Z+'$T'eF`8A3'&Rif)p[%Sj__gYMoPlP
J6V*Dgks(9l2F)f^;M_7Hp&fXlo*PBSrr5>WCY2mc]Aa6AW40LKpXH8\),l2]A9uDM8Nl*FB9F
&/XAS4@Zj3Z8;p4\hc4ip91JZBQ"(BCEIHPWM=N&qOaLR6O-<4LQ^XWomW_CF?dmqgTu4S0<
>&H;SqP)gHdX`D.HD06M<3DfE1nP<FQ\q(PCJ^*pI[!o5h*W<R/D_p0?=$EN\8Sg>OoFVQ1W
XH9NBDUZH]A,ks7nQ=WW!&OZf`(KObb(lo42OYK%f*uq:3H"6FE;SFV\`%hQ<h,r5U`\l)K,h
oqTE&TD_HJNM_4&rOkm5CJro($_ce>T;:'<9eUnoo7hY8i$G*IQi2MmSI?b?`\I!V3YH9neL
VBg6LJfYc%k$fU1on)CVdBZ[i'JY]A:ra;'I*_>0b3BKi2l4^h87]A)Fg;)j5hI$WO6_2UB7.F
gM'1nA(Ge0#)]AMkgXjGb;*tge7E8iaoj0G!UN*cCq;).$Hjc/CP>Z=n'5Cnt#Zk,ES.&'2U;
M["`!@lK\'qEIscCNsI\6^*s?Id:RB_YiHe+J&#QiYGnCnt3c1Q[t1#fubH-R50#A7VTGHJf
W-q>l6Y,q0<#-J-2$kak_@;%V[qp>]Ae9TVA!:4''CRQhA7<s)&VME&I(e\Y?6K>\\5^)rj`"
2Wdd0[O?Zk9"%`VL)hn,2S'jHA!-Rsn5Uorpt#".VPamtpK9_(KdodaiLp889[l@4jj-Z=5G
t13-'if+1bUG(#)OX"V^@5^`Au9LC9EhJAp%T\A"!rgbD_e9Yfs:9`,P.6\P!*\8l16I:ac4
]Ac!f_8ZY65F0uH&'VD[57G*/j1pj$OjSD1P^a>C,9IO5)$f^ZZ0k<Rm:o1`Io4Q-Q;-?Y&/6
7&^T]A(J&KVa-96/6;mmj=LUs)4#mCS6Q:'FFB@Z=8QF*opMLt/m(,RY0,hV4`q:O.0cf,;F\
"1Ks'k2PPP-T[+YB6s.+ARg4/EalS+Q$*EdKJ#j&Cf9P!c(iat]AihlC.?#):BuTZ^S+>Kguk
_o:WjiP6f(2&7NKY.PI/U*LWkr$g=-Tu001+.Bi'orljL"b:eUn[DKccb?51=g.2jR='6F#o
gNC%C]AkKN'>UB]AM%R8KNZkVDm+SU$Fn*dg$RtJZt=OuJ8?gg\]AcKhr15O1/(h!tP<7:q&>Ch
jI-0$Vj5n.d"4Yf$JA>NXCarbW#2VA@nq;tZ21=IOT1p@)Jn+KNWq<(16'HM(7pDJ%Jg,"*@
CDV:E\.Jo:+'l@LRd2Lb\[h'(jSq'1=XEhV,Y+b7JEIUi?9/\'L!7O$rK3p!'=T8e"S?p"9c
F$Y4]AhuVN(6TJbjinD1q9(I=]Aj7$#(Tq,P29XFIf.&RU8=qBo7h>NiYV5)Nu1Tm_d%@M_$M(
M66m,p@VOndfd[=eq"50P]A\mB'RZXJO(p'c!3.WtKn8ZckSN2gFQ?Wcj6q`bH[%?WG@XO5l#
FrRjgem!14tdo^,gQ92(!g'fh2U;f2l^XnmKT\-rIW66Af=X^VhM)$&,^;BrqV"PO+^AL`T4
"bqFSAgWbC__^4u&LHX73;6uCK=9%DZQj37ejP568s5J4igS4'WB+6"&E"j)OP<P,@Cu&Z:?
iOk.P[M4aNSi"@0E3NfQab3e?;#L(73,7gZ;5(Bc)Sh!]A$OW3_ck9VUip'l/=@?(nK25u\2C
\;qKCWag=G'9ks]AacJaoZ;l@lpH\fTY70SZ=q0B57p<%"#W]A!blA_.P1Y>.$15NoK%j*5NPA
ch("^A;eKOPg;Nd'he*]A*('6$f0.eY(:Wtd?-HD1p"6'fA=R#B9mGXsL?Z^;X9-&QUEeA2+H
[_d?><Hb-aViVP(cLRMj`)2&Gg`-,%nGCQ:'Unll\hnNL<HR2a"Yl4&HVl3C;QNi6nU>*F<,
l,`jN97i:p[NVh>38oaDiQtU"HiFj5G!\9V/i[de9bV*ll.aH^.TQGh!UI\oZCW/I2k[pU5g
$-\r9Ik]Ar*\!8D,]A[pV>G'O''kO^.6(f;4X;pl_d6`hLpuD#uhrL*N<7-0MiIg]A/XFDhSN]A2
,oJ<JBb[p&_@:@:aC,T$l]AS/PTNnF&OU:Z:n4e8SHJ>[F_k'n$fc6W4FR[`Ed(qN4r:[hPhc
Fl7_(UP5ZH"\$18L6A9+/GUmN3$Er"bo]AY0c2huj4)alZq[#pp7.'=\i2q>Hq`bdt+20(T=i
dm$NER_?VAri9FnnHDl*ed_1\\3c9%G&k08lU%Tb3lA-Jr2:'tqP4:5XI@Tg!b>hbP5pPP2O
b/e'$^qeARZmTLrD"ND3^>F&Kb8.!S2]AC=,u`K^oclPd8WrXhJGW,(m$o86l^]A*!2PQj<mV!
2_&l7P,=-\FS(-\RQb$+B4!(XXDWMZ>0,]A'a^<i5p=:i]A8"&_`oHgONt(dT#4rHp"6<+(i(M
W8+WM5WZO2_t`XK1RE2Ynagj_A9Eb="$\!sUG5+7Lm1.e#Eqc#oDNUnmd3&]A#g``Y0_D/s^Y
Q?d'kk*m*[p/,%hFd^p8HfggM?MZ$*h?Uc)j$3A'd\A^o>r2>Nl##%A[0S1U\9fD[(.1^QX4
_huB]AH_ura=f`5<Hi[ZS^\%8)#T[Z@*:q$L[`no<a37gPA/:Ahi'OMK:?`OC6m(%qL;t#&HY
Z3]A2ujN*p^;I63lGJ]A*,F(R0@1F/N@]Ag&8oFrD;!983aB;FQuA[!p,Y/<.=YQ/*A-'FCOrOI
e%a^\NnTWC!,)sR7UP?[B5"n0/kgnUAUhhdmTI.HtsX5$m!Z!->`;9OfHg1b'cUg1l59k$)s
h<Jt8ubB(+8CJb8Y,'LNj1Z;hr*6rR9oW?isQ\^u"fYuY<SE,2`&>1dUo^#b=0nl-7ghl=!0
&>'iZ(e$5/Yh:"\.Z8fBG@jE/_>]AUc&E,u;Ib+Ni[b9oT)7?F%jpjoW!>%e9S;@F1.bq:,]A>
Gn+i:"3%/e>#@%K*U7gM3<N/b\B4aE]A/QdCYVURG6BPEp;ebrO,g;8Y<4]A>tSI/SUcCb['&=
%f*KZq*Yb@#4Ql<LZ'okOHP7e9eQSQ;O0lt4eq6D8<Pan\M8rsmW)&#UFLlrCU;O&C.JtFGg
pVPb.%*#a4/I8,3\&:D=!DSS"f/6XA]A(3OSEPpbV&@=ffU%UQ.YkVo?-J1XQaLA3Pj7mEQLP
(<ooUn,JBS)>8^@>'#J\hU_Q:A/\''=N?X7KX!"r!>$i`Qg]A-l4iQUBb_+-"N6TDjp1S@4_R
6Xo1'*IF)$UjM*(]AYWTWT_e%CS+5d+oWSiY_s!MX?bqN_8Y=f<q7[YO^]A$lPGB;1cBlPXd%=
VXo\^C&2pYb"orS1Hm`aV/=#`@Np,bN??nHYRfjc)/!pM1JtT)PAK6\V(jV'IC$f>4']A6!^E
W]A9mkod[L9t%*R@IN_tg>E=RH5/A!&fR:5E+@onh)Rb#/8l#o(."Eua%8eXC@(7nSN<@:6_>
?ebNE]A#(c_JNZ@^L12>JaRr!$@2,8XI-XQ4\Y5f%fX>`2kQ;mALefY`a0dU#/$!8jOH.A)F8
8<`\m4fRB>)bQH;2PIZ[gV.S*aNq0dOpe?@AEF=*GQ(:?#SH8$B+p`?%h=Euq+q)G=RP:'5r
V=_pK.!.-U0-O.TER4sE4Lao7IWqR'A=SfOKd7M3SND25LI+W*mJ*bk,i-P$TFICcI7KqS>Z
&H9T[^snh0">f&56oM7*o$%o_2tGLW12E!(GT,Kl%/SfV36<Uc+L_pdf5(i1VPJPE/WGNT7<
87#iHTC8dc`rsBJ*R\-]ApVAg+g4-JI.QEsl0Fdd(75#NFT:\"j@*IC9Aera>insT2gp"Y^qL
"JBjP=!PJ.0BA+OMhTL,lAP[Go*P&H=H1rJm32DT48V+Cr)aA7:/oB)Fq#42bdbQ6.cco]AL3
(5p3_-8Vm=Wp@7Mn\=0;HnZB_HFZi:K?B_4IgDI4mp#u]AecbZM7I&RUQC%7-&8l&`-:Bt7(,
MY0Qs4cepW#JUDEMRUqco8h+Kc1`7-T@P=fKJ^ht"[grh[\.hl^ZOkRk@RGoUhOcHB1Yf&+.
apb[Ft0Bh`17LdO<U&8e8Z3\85F2bmB"JDZ03Z2n-?C\[o^R<g5pi@Bb0aZs-8O4tYUg3'jJ
"p!:UcD5%>rQ.T+(\G6rN-?aEf.HHFGGgUCI=OrOjS)1Z<PB8eoJ#UVr)DiXH?*P:qM"Y]A_T
CD`jk`,E9LQSgJ8EcnE#mEa;$rO-h&(;IH!/SfMXD:Shm,X`4l'%rXdQ!0S0O+-G'_=rtKR$
7'*NM.ZJ@N0`i^56a6g.%0FO;n#Ik<#e/d,]A7YJ3bU]A,HJ`^!'q*Wt79X9HttT9dC#BpOg?L
Y;EeJnF>VG_2R[[$7NSHED]AEu3/dVroG6S+M*qF1\i%':!YZ#AkQCie;?FSl<L7lH<a`js)%
7o\#)t?e'<F,:^FkWn1LRAeO%6P852Co#YqLGu=I.BCGb2%m4_u?5Z[hO3L%m@C;LP^c<KA$
CS'WmYa_R'rO:h%fi)#t@\l8H0n"I)3m'0C5d_F>96rU6;g#ciIhfL#A'd.bWY!?Hra+R..?
1(il;fZZkLPE"e.,RCqR[ko^=5sTfi`4MeFJE,tH,=_))e<X71r'Xl6%W0)1Yn9hN,@:`G&)
KfcmB%q731e>RP8nJNi7jaj+fmYAu[l46pE`\'E$PXd!3D=H%f_jCiTXgm%DVG/%`Y."pgjc
<(eh:Oe$d0Fsj/&DTM=`Df#\fUZINk'qjk5/_=&t&(qo!VqRST*L;^PjD'+XB$Y!/7!&0E)s
s'##H\H5b:5pF@"2gg(-B<CQ@:$)Bua@":1M.H8-osmZLNOo</YEH5oZfG$%&*B@H_!C'Qn@
EUu_IJa6&\'II<\<If<Xf3fMYa6P[C7@tLLqFXE2&V/,]Ab#R51`F_t5ZcL%"fU"9p4'!ZFl3
EX;'9\RbkD\EEV^L.#p7n5L/Z*VJDU%"qrPP<@H`SWNp7T(A"iffT($b.%]A\hc&AP<lr)n&R
Qr=M$l`qDFDt8F_QSh8egD'1hSX1SgpTY^GTVgPed@Jl!:c4PJ^1[V*be/W9MU:g.s&FJV$5
8Cr,KDQ_\X%ppVV2-An=m7iAe+Uo3F5=dn$]A60el7goYVl3'_*UO=kY5a#,FcT&FVPL4?NN(
,G_fK&W4VSWXu`\``#':%[['de]AFi\,G.,Ol*N\e=\TLAa,@(!-h(72q?\HOo0B"^eL\'>Fu
^Jp[h#e:P!YC&fYtnt(kU*)A8N&8L-Jn2%1M'V]AeA(5^"Ud8qSgT@ju$fp"371_j^9F2Q"dk
YB,)H:d@PA/L9HV39Hmenc8f,%iN<bdM%p&%U.B#=Rdnf3Ku?*G:j2Yn94Zp@D<gg$Oi("No
Vr;<lr<@OCk#.F`Th9B^&9*D=X:M#qV!3?]AJ6Tpgf3,Z=t\nlCQ&TLu:OQLq%<C,&7TTS"V.
IJ8<i\j$&J_)%aYc)`FZmUFqP/C<#ul^AJWee]AP[6UeR_Y_JJgl8GmA:'\7o8WVJj_!0hA_H
%Ia!"&lG3ZCj,Tr0Jb\TE'9LI+$;Ehs8d.O[>F8SULF^Gp7)TXa-JqMank&(*2L+qY3bX?D+
:YLN\+c>,'"`t`D;^W<7`/$sJa`dd>:Ub?JQ,QIuuKD3L?7Rdk4pW]AeP/KG!$QqKu7WBP=mg
nS47j1'S$=%ko(gpWinVn5oV)0[p-K"n1A.0rF0=>>(ChlB%0(Cd+k5^dZ<Cp!t&K%25[[/C
a$2\nWh*"Pk*hWK7fQ.]A^e5TtHe_6KV+%3!JCDmVMun+sgu5spq^$0LjUV7:25i4*k$QG]AEt
NRbF0_04kj&/(F_TNkfVd:a8r4HDTP-q-R18()uWnR49N$<6[r`5+GPI:`[S5-3)>KI^.i>q
c/)Qo/LYnp]AOjD/Ckt=14mD5)DI1QhHN]A#<jKi9dZLZI@?-ZZM?r!%obB]AN,Wf_W\c/gGLlq
=#t/%_g#>1LVm+T<$P1b;c'-G8T@%J$gb^P5&S13,TlOC1<NOaJ8;7qg=JqoqFdJIn_Qedi#
\LB]AO]AA#<Ce$-g"PT:4!1_T(1EUrP,uLOLRPX/OH;>XsDZpkJgrTZga,c.:K1>+30)H=;KeP
ieC[pP&S>'O?\<&7B1n+3(@-O=-j*f9:r2&d:TOr`+);SK*l$eqsEFs4mi]Ae"ObUUKa=UZRX
Uo?mS[Y7+u@$/GS^GQE>,20?QJREt/5j4Ko@"GCpqIDZjZ"`C+ajWI"DChkbIiuY$Tb.(m3s
S1,RR3M>C`5=fGRE0mBN#'I)')/R*`j-r3sjK&Wg#^NYj,'CP*8P[kjjR&n'>R[TOrTh[6e]A
U_;%*kmY^SsJ[KWWR5DA#p"uf_foGNcDWE-PZA)l6XU<[pPcI:lAuG<)QYm?Y+h4E?QTpp"X
/`H;U$1;`0%4I!&HFC9ft*tj-`bRl'>G3LN'u<K1DJn\dVM)&=>+,h$C)rWQf.).PJXMB#02
;\-70t5R#E(VFBb@VKkf!RNEN\OF)bI!ba;U#:UD=s:9]A<AZXI=C4sgJP8eVCAR.O649=4.p
#PT63+D]Aml9AP?Lg<qTl=F^c,[b$OsJGM2(\22&1eAc9]ASQ6uPH01mq4_,'0"'PKapRLZ409
4MbUId'saj%I8XRBN"\hlQR4"Iu<nSIZ9MkUu8.bEX,NRM[l^S6/2o@:\@.jCSJKqId1m%PZ
Pcam-"d_5a#T^:gKkTj!N)f8':W[nN\mTNBlD\t:8-))rqqE8o(en5(R2^RpMbB+.]A6k-G)`
#I?eQ-i<.YLG]AWX@:S?`1C,lhZ+X*_HUP.0C/!cBPO7IboLuQ^Llc-J6Ba@Xs\RF6N6AK-*-
PqpV;,UjIf[thqR\M)\#h)fX_JqN8`/U6)jk0?atN=NkMg8Gq1^bifiF^a_8b$:Uf%b=,3N4
9j&.mO]AY,HN:W?L^hND,m1RSEV6%hI``9"L1]AbX-d\t6mi>B[Kkd-8]ADR>*7N<O>;g"?D6V'
/L7OEOs9$/P)92!m4)-Me3<.Ip0!k.tas2j/1EZ`CQ4^f[@K;_=u`DG*&GMI%10g1n%jI3%:
G1&,i"iadJ)E\PG\#SN->LM/MRn,d9D'^U=3NkY%QZa2:,DfY$6loEg5G.-WC7S!-DSU!9i5
Vr)T35`2CZ7[*57!B/r8lGAr?XK;kreJCYH_#(er<[;]Aas-OHUN)N6/:]ArZ8!e*pI1O`(F64
5uQ08"7r5G+,.ds!9mGC+=!N2:c\sR3o$Adh!H2VD%H9I-r_YME-^2UV=Y@+]A+<PZ4NBfIhR
qc!nIJFkRb*AX.[JE\Q7WG%LOoUnmZ;D85+P:KH:@hl!<)8:0liVdb9841/*S7ViUe:ik%WH
4Y"&A6VidIgI@,aruE2#5>[QK4c:jm$LFW41d.2O>R;CmfQ)Fks!H/8A8mek*p*P(r-SSrg"
o/=nbQroJJts,)]AB#d6/R_2b?l\K*tUnBLQD9TZ)%Sl.d!7A<(2OP!./=@6DVhn6Fk0tHp'G
.RNt5WW5?(<tS/9qG>?I[Y;G"fLIE"k0,k1Y'2r;+W>DdP.6+?j^FXX[,n%&&[Lbo!s.1lJN
!)<A[ihj,S,>8M@_'*RfmN%[5M2GJ_%,&r2\58d6]A#d_9[-W1$kmrba;`TO;m._&F!bp*fs/
*M(bO-DU=O8!]AN0ZBs'qVGuN9(3;X#iR))]AW!p;G&T]Ak]ANRi8ar_4$]A5,7$7rk=SZ]A_oSsdm
LHXX#58_08Qr4&e4J'M@Am`@Je:t(ld-n$9e+E[XeK.@D;C.ZU"Q!"cc5k9>fU`d![oN`hKa
=03NSIhTWO7")s`Ypchl2`pMHWjHTbR@>0a0]A;8'DA8["GF+_-T2F=t,YlP;tIEVdjj2pH7p
;>5-6*Jhkc"G/[pp)Ct!i=M:r9p>RF/R#6,>ud-d1#Am;-GO$[VlAoqBR1\.gqlf6D_5p_/<
:I6:PO4=-\f+Hm.J`.L$D[dZ:4cfc-=fhh*O$M%7SS5nTJ7a/G`_mN!7UW;66Ql)Bmf.:`\h
BTV6H0NR"Yr]A2jWXnr0*f,j=joDgE22qmD1BFZlK2X\Va#Yp;m9N!$FTg*IRLl-Tb,0?F)Sd
M/[%cY:FlcQ[QpA+`#1s+2/6m(MQm4lfB-Jgmjc;:I<qG\^29*ApIG"jX55BHWI_M%=g3O5S
^8m[]A:QtYoqL?A4<C9=KFBGm@[1XR5>p3)S<EVjD?0@.5oc\i;qGdr;91UdJ'N*=4=PML0T@
g/pkn%m;q3us5XQo'NmQT#Z10Aimj<V5V#fg?p*W1Khs(G4aL?Te<'F;A##\`Ep3s,L4uikZ
ksG56O,oC:mS:=+77%%/VYQNBip+-Mb/SB#/WXS+&c!Bsh!s8<6i/'dHn_.%RM.sNpK*)%TE
86l_YmQ+BWmW?*k.4AaqljO\lUG^,uI_9jOC&h@uecj;IOo\aqC+I?Q]An$$+$<#mm<3lWC,m
K:<Qoit\>WfUR:5'p4.^BlsjFkWQD=]A=2k[7mb*ljt"_rQKt'rpJBo7&Q8>B(+cEBD,OnaXV
ChbaBrQd`@:IJoNFeAr#@j3%0?T3n/A4qIR%=SfDISrDVbVhMN#Kt"g+J"'n=0!->3Ls'_j)
i$7uZM0Q[%7Ak=3.1J2EFFd)i.%RfQ1D]AhW9kkd`"+:u*;S@(GkpglDJ^meOSJQ30XfVYY;u
DI.K'!7J+bn![5Qm'<r\NV"c[PC/!/DmkFDoDXK!d^:HUhcUTuHM2,!,9NJ[YJJ!KFFXn_(?
$J@8jV'4dOeGf!jca7r]A21K(uJj=XN.L`W&Q!42<#9[)1(gCc"KKCcjX:Iih/&@<u#Hf^e'B
3$c]A#LQc)/(5QRinM(+-;RqX`OZR6,<P$[IqYBMP*V8m#1Fn`6_%/E;^AhGL\"qh$=ahs'k/
?ko"-C0,WSJ4F/ofU?6GIi";UU?=nN>>+DAk!:#uK/so4O1t[Ec@V3GMQS38o/""dPPJt@rP
L[B>fUb5aS1`8Al%d__+X`J*-nsjE<]A<Y?#2b*j'8(:&QZ`\l?Ve=\hY!hli#I*H0"0'.<oj
VK@SJT(0Jp^INA;ci<l9BUEjRhnN*+W,Bapp@3gsYSNGQ(NQ[kAMJ-I+^jRFn/o\h\D!'6G4
EQJ?:URAher5g2;Gk-gR5ePpL;[HDqZ]A$BW7[PN4b.S4hg2nK%*kgraR_L]AWe,imNmb_c%Fn
o`hH_sV.^!V50Qb3ofHAmI6l5uPT5LYA)A?:3O_f&>A<O>h)H8\\2jBF4(Fc>*qk*I#3ijnj
a"AS80X6*ODIg7!SEU,hA0N6='j`.oEcuZ$*r-2TsZ!CS^E)25%>E'j%NWb0TU$3R,<m.%7q
)528)O_Zh`WC=hgngnoC`Zg`^$n38n$9!@8U9[fj;#>DHu:/ri-03e@^RI`GqO8jVpT"f6;%
P_,R(JNC)9o'!MI6Y[6d%:@&J9t@LIjCKGMaqo&CH]A!))Q;9b2"YYZfH$8\sA)+NrWhBSge/
lH8BE.Vbnq-Iq!uPp%:jPnlX/9.R`aquQ@SEoZdko5*>k1)O50-<I!82/#NP.g^EnWSN.bVg
_&Q.-X9;\D&_)S[ePi'82*[TW)0+`KuVnZ[G`\PC5T;#)+iU1?2gH;%&iPj\cA0[K&pH(O,s
p+)=n\Y[;O_Xqh1KNuHr8T_,g=h5_oh1M=KpY\2s+*UMu\'t2bC!cP5V\VAfE-":SqkB]A9G$
S('*H.@<M-4Rq)nAFT]Aj_q9&J:RTMJdDe49CD5f)<:6aeaE)r,B>m@Ag>le*hLq#:d$s1Y4g
J&#_>B+"C(iQ$`#/5Wb<MZ''=4:N>*/p8g,]AaU/<B;RD0P^[*#fcK[Q)T,6_UtPnnRG0S+[a
.akB7/d,=.[t=p/Ldi[<6Hu_fX)6'%N)5'+*#(YTq<bObWYnPH49K`PG)6D;4c6/aAC2G`1K
.d`"W2iV^9Shj2)mR8bB<#_0YdETF57C(OlRFbd@KcWQ\sup0_QA\rM_#Z;-X\iWl1#58_9\
k=iEnLWl^Xs+]A)F:R_,^5a+c'JJ!k`!5:[,R6W[!%RTNL\`'7Gd4C#"LpZ@e)DFR/LP.rRF0
urLj8Qnr$I1MH]Api1*5"]Ak"Ml#Vp=d7\qcVdr17JSO_BAB>1704-Y:pOFD[\H#_TYD,N-&#r
X3WSh^*dim,Kq,mG.oO.6VDEbS@>Qhq!4ZR34JO,gX.\tJ-7ChWX`2U7]A7/FQW)Iu^RMaP<i
F)qM_O5`SQdUO:Lp+\'"N!(-U1+mD8pHU"YZS(#&1a.1`)5'[c,p6jAlJ1pi;_TJ\NlnT.=\
Shb^@.ak:Q"5=m*No-d=tuO#Doe7G:SgVP?8>m`i+\<4&CjVS+<[;?E(q`AH*!J%2ep;H8)c
+)S[;Z"(HTYHm;3Oi&_`14BSnh^(Q;S@JV>76Z\"dC!UE9.4@;2UB*B.e#ua/GOI.)Xa(9!%
R&KZm:2-b%1LTRYXFbVFa"]AEQTOfDSn/;S(n&e<DY&44g'-pJT*8s\]A(jIM!ktL@%l)-TGb_
"!jt@CJq2S>Em??);eIID'm!@0A)#M8LUXc-/Y`jiQ(mRpGmZBhIRir.:2PS$&9n<HHh9p/.
_biIn`1Fs.nb5gee2=hgqYp1Uc!N!tX3_ut]ACb!;E/EW*58MCom<AOKjlg\]A$ibZH%6r?APd
HhVDkl9^oaL?mV$R'JV_Lqf"GMhcOCk]A23eL*`[oh`<9t?2,W?1%1:[D?!F*>>a%3.s.VQr3
2fJ]Acl:q@Ohpg-."N27`pR*f*U?p%>I7+%_6HQqI157<^QOZ/.1nhN#`=[4Z)AM3'>EoS,DM
#rRq)_m6oIelW`;Ic2+T:uPeH_C`sKlGi#PVE@Y9:]Au!PP)$8Vj!hBh93*j!7Nm*b8@(^!qi
#Z*4sXHp&d[l&#2-+r#CU_99Y#(aKJU*/%j:72Sr)\S=?mkZH*'%85:!7Rtc&:#&A=i&"fML
'QKSu85cF&WA^m^']A"-MQl3H/o6Z:_TfcLI[g/b)m7Y?oDO+m;f5!a[E/uo)%UopRi5&AR/r
ED(8sb@)^B%Zn`Giu]A=iFZea^+-9:jd^"N,IRF0f"jEiY(f;7B47@;U'5+8FVOMW;"t)i'@9
6+l[UFJs0lr0KFo%S?#WTSIUdnK"kZ<4?*UD_))CS`KT",+GD.C&&iA&'iqK)GjXf'7Ouk/T
*mZc;lA>QGL=EHr]Ajr#5h2Ms5A:6>.0j<J?r4!m]AEDnXFgp]AHNCrB6fu4(/JRTRF4lO*VNSJ
sa?g8WZI7F$1Wk$OFJK8DZ$u<c'nNn)oI>_$+Q6HI6S0sN^TKa0=ZQsuB^:P01:EWul.N3`K
Gu[^j,F:Fc)/*7Q%%9O_/$UPMPq!+cUhcqH_KtM\.%.%9+T'Su,H]A2D4qE!3Gng4lrgmQnZG
V)HOdZ0X%ceC^XQE=[pB)gK*+HhQKB)'$=Of+%\0?U2kY6%FDm[pV$VH>nXZbaOI7s_6s,!_
a<*S&XWIW+ls7UFFqEJ#%0[nFYD>lD6@p@.4V-s6JIkQ5q*TCjM`Z$9;IoCU9>fjY.Y\HX)G
ZnZlrh1+l'<4LuVD\7Mf?e/Q/^8-)X06>U:=l!^?9o9l@;7Od94*d9AoGPB'k1m^F6[S3r'd
k5A?kr=$Fs=14nK)-E_hns4er^%DMZZW9g)S`DMWO]AP576bN+=ofP=$"LDaE?j(rY.59>'pY
9B.&/`_K"c7;`-KGF/"4:LNXOLno`e"N9Q>guYfsOhI!6(gMC!'#kBRppBr]A.CpO\DTu"qRJ
dR`QN":dCWgiW`)Cp),d/(k4anIkLK.=^kiuNLk/2MNbp(Ia\WlNF0gnY5&]Aj'@bH%*M$>cH
Z[-P`"@"rm<m8MeVlE0Rp3`@KI7%oAbT*qLHP=8aK]A6t6?BS/kV#3oU-#;0bHZuQ3?C]AU4Gk
473um:S&4V7WDaF/+SFU@p*hX6K"qfCGS1']A&f'4#uEm)qQK-K%&OcT-o&L=hFIKenpq$&*D
ug+%;'1]Af1^3FU=j.\>.BR/H4SNr6kB%"ZB,G.(2jX5**]A&6LdoMqHmOe[K#Ct&$P@m9NYic
a*BO]A]AaM$-S$D1Z1LR:"oR&lDBWM&aSO_RJ&i:>16c1$FOr+(ggOWhk$)d"n`YG0288uY_jo
aCM^QX)gN\1G6C*4[]A.]At/YFeL[:@Fs(E(NHZcI1>t-<;T=D>h%,>:tMl?qTB6VcF^pF&=oK
t?,4.f]AG1Qk9;#s^9@nK4E)6n>W%WeC8dcbW'#28:X(thGI?U3K1nj(&K*,fHj>/=D?5NcI0
UA)N&D,D(GJi9EpdttkKN)94+%=Uf?1dHt5N0Mg[OeK]A7pn/ei+_,9(FYVTFIhD`8Xeiip8,
.6Lt*5Wr.(jc]AH#Adpep]A4dlHf&arX>ZW:,_g,"2iHFRhGf0^^m'Pu(nadB*8J:WiN;[^k*Z
mA+TJWQ"`(aS)unWl+K\Uds%f)SrXT:I-u%0dGZrTutf:p=nMHWI0=4h6:g[/#1X`1[[L0L?
;DM#,UJ-M)h3WMh^%lo7VE7HI]At`kABHb<:pq^gIMB'ee2PTqbauG*n"ZI,&(93hZ?YrLqq2
Q"&#6]Amk1H,,CsV$]Alr;a?nJt7-FVqSVV0Dd+duno>&7(^MD6`1BE08o+t6+.:g`Va,:t'B.
iRHnkW(iRCLRuNX\0gSF0'LNW,sY2q1T&j1Gb\Z!ZU#$6a=3/2`LrX3;4HM#AqqjQ\i9pT0h
W()B2!k5n+V[4B_DBqLf`.C=-&S9]A-2I#oFP118'V%<jN(We[eZBea.9)W1t?k(J5Tr?-8,K
-hb*^E(amdf]Aup`/?a&KnDCQ)&i5+qJB%P!6LhlQ1qWbb22d_N[b8p2D2a,4msn6d+gDU28r
3QKYL.LLXb-4S7"l+08E!-IA&1\1m>%qnlNM+ZD+uD[IK2gQ(4AN.C1T;:%#ERO\9Q@VG%jA
\hZSU^aT]As-F;96BNUmA/Z0M(R1B8()9p0?i4VaBqe+!@#/0+/r@\%fsK6R;S(h)*Dh:+qPb
:=CfmUVtq7d\A>Tb&bJg=]AMej(<[YO`Xh&cEo''q@H1>XQ;kiM^A\VLUb*P]A"_.$^=CV8_k+
gUs3ecaRIi=]A-tGnIe[$U^%mc1i-@4An"!S9m'-Y@"M3XjhdI!C+`=L86?SnT"I*d9.[Q9P=
Cqll)E-_E/IC2aZ<p7tM5EZ]ARkK<S4jJ`>gSBsUhjLDUKMUI2?Z:GLrd[nY]AqVM`73T]A7ERK
\=?_GF4S;jn"L,S-]A4V=*2V>SOc$%;O9bU9R6f_!D":OWF)6GAn4gplqpOXoF=o(#?0TH(lF
ClS\^/7D`>Ap)hZ@n-LbhFVfLkR,=;0T+$Z=OrR.oiRWju4J^S?mspSN6E;ZB^2ZUah\2^#`
DjY`G,(17irrdiI`fEtKh:_7isp@C[$C0-,LUQciIu:.HM^$jCd.BR5[+NFl:B.L:gV>_P4I
.k]AC8MYKj@P3T\E4@<Bd,PncCpK61P!rNCXJ$b]AWdB11=\1PHo5Qn:Ct&g!b*/n"IHZ0DppF
a_lBo=M*K8l-e(h[\i+tiI!)OL<5NJ2$c-Nkh'HNXO6pD3UgRRJk^-nF)Jm4dAg?(Via?:RR
o6-%h0*W$gqFT'J0Une*aa[Y#l$h>P+JC>V_jjDQ`tO$'=Cq\0u^$3*K5!"A-cbA'(uOL5?8
;B7TD3)u"O1oH;+G\J-2;k7iN=WCS/HQ@9;X.<2&k$K"cn%,9:%d"kcoGkpdWC63c\@e*Q4-
[):`'iK-P.6cX@<A9r/^O4ccDh&4En`a)O&k!JoSJMI>>d&s)r&C'@pUe#lG4k-qZd$/D;5s
QV1FLUGpeH?66XJo3Al+H*&D>)(l'RlBa$b-Xb:s=CQVE0s#E%uUK'e1cKu$)E#/`@.aAG2+
3(u;XbZ\MW'L$DPa#@0KlB6e<]A8%]A8-R"nIPGad1<^?k#BXVGSGH\Q/#!,10D)7,^+bS`RE0
2Jshl7S('tj"Lb"2XJ1^OPUcTp=AQ5,O@*Z&)8SFfg$:0=9jfOUOmG9Jer#p@f>_Lh@4N'1+
FK^KUq['#0R4V^aK0q!,hO,9_/4?aaW$n?sI]AOT>-m5dK+@Z@P<jf<%IfP)Xa@JK::-[H/@q
rQnH#G3-3%k_E-Xg/M=IkBhga/(S/M@I94s2lSJ*X0:`oR6p`C.L5p%@5;=P;:C$56eP.La-
DW[oftA8f")`59e3[o`s6F5utRt'(E]An4;ZcS$8VIK`=KWq0e5O!R(V(;A601g,mElKhkIY[
#GZ-6XVl%:@m'T7:gL=%=)<L/lgr6ecZeNtglZIPPcj\<"]Au3*R/?,=r#UQ-I5.2S8/ZbJpK
)FVV/X)^@ulRo[Jb3AD<A9Pl`.uaJNI%EV:bS[(OZs*>HO5d8)DmX/r/^?+OheXY=S,JD2iJ
(Z)1L*k&R*B]A"3%oV[QmU/@+["M;%am$6X,f3(#eE-^r6mmY,>t;P1!G2-88%D3[e2:XBP?X
_?VS*;R&f7Zlj-@#Z#%OI/90O\B"K]AX)$:kZ<mNpHl3Ig'(>@X9'dq&mU57D*:P@L4=6p8s\
OoA;m%_s(Yli@2l$J%A;hQMG2$Zp6U7dlV@>T\)kfT>l;0t-TaM'E:Db:TtM'E\bAtp8bB;-
:8&g_@M?$;h_#QR,:>HZc$>,F$0()Ne(`Vif.X3HP!ts]A\b4U1IMN$0[/T-A&iAcsef?Q"*I
*r9Wbm/:<T\aO?]A4L/J.3cEX"t"DaG2g"r9]ADFR,[s>D/=!NN5pMjr3Fqr!r;1n4^u2%it:;
E3O&gfd>Nobf0Tdc)C:;HKs$;Mqj6$ll)qeATBYAPbofCT\R$tW@&$7;K@Ch^k59P)&\(ZCP
708Qa'g'lD\p`76Ln]AYfrXe]A8m1]ASSnQ^nUYu7]AD`Vp.l[jbc&<7IR&7CJFE\']AZBQG1IrG&
t4DLHX,:uO^_-!'6nhbJ@j%bFY(+'uf%aAS<KMo0?:LaOl(.l(o,YRrk":1Fa<kX!8#n^6_U
RLhX(@JMmIq1$5,<I.,nbAC^A3r&(D>e#<i2f*Ol*cNDddHub_-P$-EJ'<'.9%?Pt"CU6n/M
O6XjBn8EbXBu@3\ocJR$&di]AH6su^h;\8&fG]AX$-&F!d]A(50o\XTJ9C#jbXZodgfM5Y%=bo!
Q&8."gM.13Yh3Sq+L<8/:heAfH64+igCX.VB45<mSA0DcKdq:l"ahsgW&#'j2C-lI!"?tWFj
M5jmWGb7%g#2$larl;rNoCKD5ScA,)I7@H-:[_)KJUC]AS9lq2^hgbUs-/3]A)7Y3p.WL4@hrK
#=fKsV@AWWp^C^cOhXRsIs=e0ddlO@iA;^,0(&(jf"r4R5H$(.V8Nc'&leqPE%%]A;l_TX)`e
ol"0a4O4Z`8jt4U5hp#l\3GR$=@5)ei/#`7r`i?>,#$A+X@VeEO./_?*_,UPpXZ-qme3&-D[
f6G4-t#)YkPH*DsD/.ce/?XP<f`UL12d&;IV$eb&crV0Y#PM-H"]AYW2Jo0(r&JspZ98+_9*o
`N`p+^,;Ud>6AN;l6`tq1^"7!mhT(HiIo#Ri]ATT<^'PK/(7i!W_@!0;B]AoAu]A">-MKTb6Q0)
WR1N-LXQna#/HI5.hcjB:4pr']A$=2P1#a5*ItN2>gos`81oNu;#E!t';'de"V86mP1=tlaj<
PGU;CGF#Im3/Afr8A>aYZX#'#q0B,;1DGn;,c(!I_)miS>q_?D2Af)2ePdN=Zsrf(!HW^P7k
r^Z1,1B2ht9gHFYK?;%Zht`@h(H#3fVgY2hhh11k12iTPDI16O;\PMlm0N>OiYD-qEUGGrR,
YKI?)SH)$2WU?b]AqeIIi!Frgdr3c_^E<A\^r#DjA*#i/!$c-fqL0J;/1Euc(YYU'r@;s*8K(
o*NBe+iD*`T=Oedfjk6J"olj!\dqU+OSZk=SD=HZK,]AS,G(:Xbl,uq1E6SrpDesO:n8(hOD(
a=6J(O9`FU'tsVG`:nDc5F+pE'%+FXJ(*I6_aB[/f,qK`,F),AeI7bNW\c#]A"h2R#LMhh*t2
$8'4,oL6&:Dh7Zim<1d/P%j\S6n;Va-!G,Ljb"OBDH\//i#$*u54Pafcs8oLTRJjqCpGF6G2
a;FKmDTT!+D7j0=#=`>FB!L]A^i\dgn1)ONeF88Jf^*nD7ZrdSq[CG8E5M]A\_G]A[\::ZID1UZ
<Wqep3iUQp9-j;pmGi.?>iSlD&00!]A@PFYNr'Xfq'-VSR5?'R7R0[7CM$47b4m@)H._t0Hgd
]Am/r5[_1S+03YRl"H#th\1p0BnFUT'`gbPbOJ13rTb2@2i@\>c@;p^'d'`@dmEE0>'He'@<8
m*]AfhGGOMQjRMGL;WRUpP!:'!jeg@443NLi7?%a5q-hEq2H:]A'2SkXE_hh1i011apr0J`]AZ$
R1J-Y*AdO00`c!.%cPD;n*'FDUMp@Zu3Wl$+_4Wk!NKPP9^,#_o@T0+sCeK0PJ/aV$#'A,BZ
eXaPU1PY,D0H!<-.9NZV^%J15fTC$unBUe2$$Ih?m?!9/JO=E^gD:K2:tcepBO+fcp2Hf<n&
\m6!6ZZF0$$2\^)WTt'UnR9ea.L?BsBCd,jX$(2jfE9T+/,ab1Lc%-,O!7&6^^5b3O";'3fM
#6CXkdfB8U$U%9cF5D+WZ=3S)R+K<f@#J?B+OX9^`BLD"CX'F9Dqc'4Yp.nh0Lt%Bk&0ZU%X
_35_AEUX<VtFO82e$E,/:+3,)'qF5\r^nFZZJrMeO.iF)<H*a"V.IM1b*AJ'%^Vl+@H#"&Dt
;=_,>4rl,^]AdFkZp[$&t2"ka,3>j!->Fk,0"2m$rGr)uB/?9!WU!6!GF3XsluE@''.\8&L:3
&o'?TUUjKJ$7Qc`XjTFG\@RFBd,EEn\X5PMD)7ga&Ah?3U@A=M,XRh>Mlq7UrbH8W*rb,"aA
f4We,^-g&fuhn>DrO'<9sRkODs1u^RS*^k+9\3$F?"P,["#`am7*&dnNYnb;T,6&*MHGl"7R
gS7<sdK8U-2W*S-J>2KH7c*i9P7%>97.KVPApSQ#;69/$SE=5PF%R:KilQdZr`=l$%EUcV\V
hph@Og[>'mCIpCY3m,4Y?`qK_gT[$7"IIMg$j<P%0H$E8d8gEh%N?e:38^'CjA6FfQ&aJ/*V
p>,=W]A9pd`0ejSHCM57lXpTRX0Q&k7'<EC2GO-0DCpfM=ot<nVB!()MhT==;=.AD?%^Y;B*t
Ss2n?(FDlah;(<lJ2DWo[-Q5LX:*dN4]A-DX$[8%0E$84,*_oX=n.)(ti6+@&Is>?g'.Cl1gi
(uhRVaN;qI@fjPMaj^.fCel@bJ-OP2-Jf%g<gYnd"R*l,I2q*P'5`W&^T<ni\g9C7gGsN@K:
-7RY"DB"oR;WQ!pp]AlOrpNplj1b_SQ^kpGD%2Qg6mFFk]A@*[fc@rErQFH*W$%E#a2D'RFiNi
ZO,p?BcREV.2Q_oC<6F'bD1Lig/(#S&HPFTYH8RmM?'?gej*2k2l/;k-G2L"O2LqR]ACBGPMa
uC-D[<4f^#5Fhc>,[-5ep8A<:9(^Pqg<3GEA<#<Vpp@,KctO$@(#KQ'70$ZYqOrp`23PJ[VU
6(@:^\!dlcP"?`A4@gUN[ht!Q2Fu7U;f$fXrV7j+q@%$k3"FT*Hp.+j(_EOel[k\8g6/$U0N
FK';9/%>eC\]A7+9qYoEI]Agc+mMVGmHZ!1'_Iq[-`q'7n"C-)NnZdL^"B3*h<SV1'/ScL6[G.
jGhHc/AON3%l82?W\L,iXl'?-d%57jaVZr/H\YH-'_]AT6upa/n$B-DEp9`Fe55MVB<<f8O')
M[EpT9CZ74H[8>?>s]A3g#OMW]AP;***L<:SQPL`f/sBZ78;<NH\S/Q&170-[E$sJmRr1W!?&t
gNrQs+5ld>mSHOAL;q%AS1aPT?K'-BAh,HHQ#DNY<dS)hSeN-C^rZ[meS3es'%a"`c!ZO25m
o4m^LqE7Gj"O9S=l<fXRC%dZ0aciI`HY;dO8g,/8EX:uOl8?40qNG*R\'!!VfYq+<E&9&JI8
4h,fN6<N&k#G*?VI[^D=Bs#2NPY<hIQN?SU2:.)tp\<ECb9-+1*BTs6jtk!3atX5P+UJ>nq5
/"q%/d[uNZ9D!P/Jr+-*2qnaeOGh\(eY.:t*mT\8&^%g5i]A.te2\GoR'e8Gj6W0Y*,M\`WMq
T2N;^Cptc?tg>!_EKr7[pe7@amptqP4D?F_mW7m=IPn8IZ,AXeA`q(2cj&t7TEo"f![TOq-P
ZdBIn%h4-2^B+0:%<D(YYGc+!hRBF\CYSnM&u##ft`o7P.>c(R0fraq:eqeWUC2/0>[Qj>$F
=m#'(08$Wr5oN16+P,7[kD`!YOI<fsMe`Im@_9bkE8lE[JDATJ0AedpB$=4*k+WGPjoc$WD^
O2bN%r.6d=-&k0?M%rQ"PFFA\t*RWjCSPUL=Cf]A_\B6i$-^)ABAkiq3fUDONF#J*XgMt6FO"
WceU0fFD8\q(9]As.pmZ+4)_]A4U@2?2I5Gq`r,[g&?;`uXl'H+uT0<`eNO*2+q%:5Pc-Td9RI
`Qj#Hu9m'E.0e6OEs&JAd*22KYD^[C2d_%Q(ZH5,BMBG*#9W,R(1Ka7BdG#).Y(fl)[6lT%I
1aYkib]Af^kMIZ;1IX8?QroSiF%+,.4p0'qldmm5bSAB_C5f7*jL@Bk`1FG:qFD5]ApNbE90MJ
a!B_u.pWO`-1bM,^SkCNl3h;65C%TA\R3J7F)lIVflHq3`N8i&(Co2P!%F)4Y(KpnSKS)eVU
SbcnS=gQ9SQBg7Xrs$pVtR;)I!smDQ_ad30R^bE;J?26Y2ci/?)FMbf&BsD]AZRP!p16nI,)M
)cF_=(gV3!&)Ih&\nP&(6Qd8ojG]A/uuXDJ\n?Y!D;Dhm1"@A6'ASo#VS$Ql`+OoWjKgDOalD
\CLk0R&clO4%=*/nUdUaog`mFTNnR_k]AM3e':sRfs?I+0RpE)D:8\]A1"jHYCZYsqI:]Ae>=Fd
.l2*jYXZbiBF3cd[V]A`+SKR"".Z%+"4hI<Lg;hHlb%aZg=3B%MF>]AHug+okDrD3e1B9#nY38
^hY$6L7/Op6pb-/h/SXa9B&E*j0$Yd0R4b2]Af91O.n/L1LBkA@eGML5N<;AcSB$0Rl-540E_
/^ZlfAb#6X,*0ok5N'5ZI6t.NC,a-D/M`V3E^qnonW,6(5u8VgIETrK>/(/jE.;Oof(A34_?
-/>"M\mW?%OE6T8?CK2TYjUm<B6B@qm'/2@pZI.CB[p)b'pY;ut"3d:5/Tbt+KZl*EB)af_;
3WfR[/Y=ZD(rCQ3rWJ_0ECGgI&Hq00e\Co4>+TlRtZa2">!J3e_^/I@H;F]Ai&o#/)N/1T(-'
VYm]AO(3h96dHA58*H_-*<%M8\12h5K%n"Rf^@$&cN6T8>O1@DWpS>rLfog!:EMfMGbYE&@+0
Or,.H[qjQ3T6NlJKA\0F_=dC>MOaq^R*Rr:8Fp]Aq3CGA@^XErk>&id7[jmc%)kpdE;6ppEF6
^kBmt8&7EX-[f'A"R<B%m,4m=3W+c$S_=0j?$<q+!oKk]A*_Lo\M*o5E@!0(<u':+1H%N?'WX
je;:ELPNi&'6b?)fX#7ZURCDW*Grp@RH)#5<R$/e1U(%8V46O^G@2eI.Om#&>2T!,,g;me!<
c167_1eGE/DKa?*DEXXc<!#="sN:-B\1_66*J>b/H?#bn,:Rqe-n;*@/Ina7n<):[?L^2ac]A
[PZH$e$T1qWYhTorX,u+8"2rfs+@jXh'DB'8:`D-a?VZF!<pXbTE/c)#dd_Zt0_D^L6N7W_3
1bu(j'-j%&G_dL(9:ZG.EH?1I6iddui>6@b_-XJcQaME\,*bLHa*29g;]Ajdh)GPr6d"$*YVK
^[iLHJZJW&+'Kr4[]Ag+;s&-PQa8[$8YVuZ:mfn/hn+2"3NDe@8n#:OM\gDDoUlh^K1'F[]A)/
sd[84[%JN!ZaP.ebDK0$QWAmj1Y$P3Fk<V(WH,;Q"Os@;FFS@Cq<k2GeTK*'".pN^2[&YgeP
&ATTPRqE:Q<<;U\O;!B2%.pn-1b9.@i.rSZ*2Ok=1>k?lh9.4HXdcWjb.O6?3lH09YCl+:,e
*+2$FE)k@$$_.uPG$Ng\4;*j$g@fjq19K2fdOPs#NlMt^<^Ajn(ubMHC:JQ]AUPD5)KJn:Yk9
?t=\9`S\ZrfPZ&%,S.FX0VGqg[4W=ZTL=6kFPD8`)=%0Z[/A18l"'>lYu/>4TfP^';*`+WBd
dW>?\dZGi"US%OtXS72#*?)oh&Q8BHg0KhAc/tVaaj;jQFJ&U<Zc*DVFYMl#idHGpMaq-@G*
.e//"SH6ok(/W\!(L4>pWrPu%J\+5W?]A?Acd,TJSX4HQZabnT?*D\sPepP.C2jb+"0W%orCa
a6?%YC%TgT'cuM]AbEgk&2dhH\!'U`jj&6W+n96NjJ<_WDDH5W6d\]A$CU9m4KIt;_"(E;llQR
AqYIKB*QlIA<dAgiU:GP@53&KabbD]Ah&@'6]AkQF4uc5\3s^$S_TIG\]A_s-Ga:pI?OfK>Q:Qo
:7_a7h5<fhAn1&KUd(tb<GKDc_M,[f:!`Q_amqZO2W$F4[R-,37aj]ARG7QQ9++dE@GY-M0Wi
?bhO@Rd2*/j]AAd@Yg-00CXh@_]AC^Lgk"Z2m:%sLp9j(l"Lm\D$OeDG*-WKE)t3bBP!m:BFkV
T?B>)fmpm"LJo<ub*"(8t\(%KVGnkBoB7-%LjFZmCMOAMi_H`)oKO_ht]AZfZZ.X2*%,R7eKT
_O'(BM-5dT#H2VlR(hpeX(.8EtOce8ZkqTGToohf,I(Le]AeOWf$Uj_^;.beoc/ipQiZ)JTG5
\dm1,*g'GRS5b,'iS,lM^TNTlUT5q]AGnSbJB)F[0oj!f6uTRkI-\1uZZlO0!PD[I+f9?f[)]A
l7T?0A?dQ:NKnPdKr/o]AKih8i(lX5"iVVsX`bUrp$e-p$6oeZn[^0I.oAA@&rmT<fTM!-s^Z
$0b50eK!"d(sL&',f=p'=AFpS:(;iV\Wh)bJY67]AF*12TE#[BO[JS\[pd'7BXcIk_Yes=Nj7
@Z16E=(\+5#nH?cYd9MJ_n]A0C15EXom$+f?G7"0>/-?gOL_g<\BRD*ng^ONE/_>=U_SaTFrf
"k3"2F:hnH#sb^IH<8U>3rV>5^Q2#KksM+HQMFocTPQo43F>\(0HKtmRG0+%MVcpHoM?DCNF
QRh0qks/5cf-(p^8P4FrfIY8n!pj(hn'_HGu6_^74(-N0"LXoAX0r0A9d^45&S)%ekXRD@j:
TuM=^#=&uK>mYLNp2'0Cc5jQb!r[[EF@GE7h?tX^3f4JL;-D?4UU/p@;!pqYT/9"p.i@R*3D
7h=2sG$sTM4YkVD%:kHD,Nl4.<3>CJqU^a`RnEMAbN0mI+\F9D#F<+rspe\kqa6ltaj0k.Mi
%m&i/j%biOe&?\=\>W=um34O1-cTRmkKslQub4#7`d<44:OIH/>(.fpiOB5ih&UOH*_urc"/
!-3$Q(eY1RM?-rIg.$?Z.`4VM7b;@>^g&08-@(cU>U[9KYkC9G]AJ5T*IR-A.0"#G0[@JGXR!
PphTC\O;`c/DKc#gUj5^:)K=7.\cD(L]Aoi)QQ+Xk%dnE,bYk43?o9ID&%RhNCDcFSfV_MI90
]AWDNef.?Upl0eD#dQI!nMl<#m@:E=L;Z)ZW&U$5L,"hr*Tq9"i7DN5r>*mt,7Hp7?6;7'[SK
LYT15`#@9dtK:&S0SF`[kF7[h,5`_-AH4E15M!(ni+=W\L5F'("I6=gR7:D:e`mh#;Qtif8B
Sq0npuqtWA3?T'iC7Y'`A*YF[;h="Ge8e+Cq\k`^[QM"j5"@cAA$Rl5M1b5*^=;#"'&"PDJl
1X88Z=i`'\X>/MTC9pf#.sgFZt)nH>T9"@R;^bR^foNkMP_![Fl.b`iucbQ&stS=ZbHNah!=
cBXDM.qq2'-5@lUB)h4MnJa+sjM;rr2>^M?Gc1`1"g#0t-<_g;6_7a'Uke@G*jZCZ_9589A(
3IqZ`?&"UGmT.p:Z9Sg9NNdICQ*UA4S7<1R#ip2YdOkbS1pi\5oiZ?:QjM:cP5lVX$tb,Lfj
M()8LTslrX2jK-:NOi,3>IAWB%bO_.<!d"LI5AdmJeF2o)kR9FFeo<iJ1$dr/RWK>ODi<K%b
->5"8rGd/XLc@BgIFX;?1=4JBCrec,0e$O.=iM_po>eW;.W+*^k,TW*JZ,8j8nRiTnT!V55l
NGfR'$SV/)MlPiC%E#h#)"DRq04C;qm`@lc?\LCqY-=c4[:cO1kC_)A=(\c;1\G(oTpH5/51
p*b]A2#M*]AM>U,,GfHJIf6/Q_lDDf#euC,Kd/q>oU`?TsRuS$J#EWm-dGMA1]A"sZ'NYmFZI7l
,%@BL5p%(r[X%uQ0pQa/2X'q'"$FgCCHK_Mh/X..C&N+8`3#m#VAN:T`e3JkE@V:Hc!2W^`,
lB7$a\lVSU[,XA_6f!0%aKn#5g6aQ8mK"hKm[Qp5^[3lrn.rY.Q0H+cR2mfqR<ZhW1iACf0o
jWp:m/$bDZIb-o\#1:G;YN8;b!,O+2S[Zu(N#V8%<H=WGCee_+(%$Rk8r+p53H#K..-LL11B
N7CZ&.nK*(-_Ef)6_(4QAp'RB0;2cB$/fp*hm%D>Frdf<d8J\D7YN9]AM1hhJE4OgRc?e3!Y`
KjV[?m2^1;gW-$:%L'en$eiKB1'@bD+0UM/]AS;OK;`]A`p@-`)iVeD/FuR\=i.F>G>:FfZTYS
/HDX7FJQ:[Om8qKmhJN%$;RpO7V=$lLP>\&WkHCg6%B&E/3h45!$XYg6nsg=cR[8@ZeVH^kS
kg`lHK@TB,#'bNIXU+[j.lPVMoBE6Z-K)#gM=>/]A(^);:SA_g@QZroIC^DTIV'Wl=Q2U0;*%
BZNN-&L&@@sG6B%<_4$)\lD[7@)[a=40`nE8kU%*qm"[u3UjqaY3WWdbYKXJ4/j:fbe]AJ\m"
5jc/Br[A.,:m)$eE&ah0OSS-p?$KdHb$!BL1>D>B>A^?YTD^c.upe6aJJ58`Q\I=lef58e4!
=&NOl-'#ms_(,XWm#9Mb"Rhn,S(hRgWVDgYciJ64JIJ_(jA?V+9`2V7g:+p*fkih84(^U?Fi
8'M,CmZmDW\+ceHWKLQVKh1OH\..N2XbJZ?;ei7`,86IO<9VO?B1pY3D&jt;a>`;'I4$i6Er
C[go;sAp:mcW$8Juk>*h2E6M3S/JdR]Aai<l5+J/[m*eFFH\$=8lES!joc>,X'[ZfeK@'mggr
U%XKA_7WC/pak<4-LO"bgX&_(r<Y>p+WG+n!8K:h+&,M8s;O8(bZR#j)_cN&`/<Q+NR(CR:N
QkBZq$lS"gtON[J%5!"bY$4OW;D^&+$=jI`BKYmPr!e.8;c+h5AbS$Z[GX751g2aD2]AG4@RM
NJ$a93WS&45G2]A;p[P<j'+o!#Fk6isnA'4QhUdFh-<P.,Sj>0nr0>r&>9VtnV6>N!Egd\ASe
m/F$b%l@"Hl`fgn2@G?JkH8;.+cmK`:blg;G^:1RH[JEq]A_F+q*bW>#:0Ztq_tF/SeI\q*Jm
\Lm1"@a763qE!Zo!LoGuojd_A&DbfgY]A/JYFQT<\38E/$"]Ar+L-)B:n5"YA')c^n[/R'T/u2
Ve1+;%jJm<r4MrBN(lCSd1d@aKAKO0jR6%+3.-L(lYIPqEVueVh;L.Ke[t4><bR7GO-TIZ22
Br7j5/,J`0qf9rO)+./Zau^+_Qj5Hp@&n-HX0Wp&?.nWZW;.::rV>NKP.?E3IS-6:^s+,76E
.Vq"7<L9t*["LB5*16ZkX?Bc&/8!AZJsN5Z.!*pSY:^+`d8_W8P/@T&oQrtNF:/pTcNk%<>W
T)7\FeG4.(*VaG\#Re\;S)I<<e&_Yr1YphLX8rH_f(5mke#&V:C_j?:`rg^[pq\t5/@I(VA0
S5Jl9^\LdMJHLpDWo6^E<i!;GLY8jW"MPQ,B>N5QO(krA,h$4?$lD+G.mps"-+S(oJa>>OV:
d^jnL1h:\q8]Ai8CFmQ_B#/sZn8k<VDWG&==q(T.^4!Ml]AfEDq0Un!0'>^sK21=$&Ljc5`RO]A
Z_<$*oE\pn&VY<e:hf&!u]AGafmA.UA4kORMhU!rP?/313.TI,`0t=^8[+!(O;&/g8q+eRO5.
3IH@%*TlaT0g*'h#"U)Ne<aPNAX9SEL=X#tiNh[T,o.PKd^C&apaJ0DA*ofBJE5q#_eiFW#e
%POQJb4c<kS?P_1'7Q8Nf[@-YlhQoSES.d0oeL-nqioaQ2HqX':ZR]A`K=Di%0.`(DOBEC$Bb
/fY`#01[aAL_0M_9#Wq+BQ4oCN4br?hfsf*M.[6tVn&)[XMGW-l$T+o^`ADo+TG)?gXg=Y@\
RYA<joW8Cf9@Z,YCAFP8ec?m[$[$iiee0hGB-Ot*=8+]AOlN%[n0[m$Odm`3o+PDZq1KF$'^U
\F0:QY`P8,UK]A<T*1\%/YpCtCQk$3J0&uQI,H$Yj2u&HUPFs(oV,+7\#+/2Ku)Zt?W]AQ7G6(
Q)Y5c'9BXg>TFf",rOWd;@7gdd^#rj(@WitdIUcqg?MM&Ug=1)@jUdH(lOU>eW#OCd\J`!$#
U$96?iQUI$dUR"-masV8^#ZZ-QT-!88mo;AGK(&$d;Uh7-usfn[pFVd$sS=<gl#Y_1=(>FUu
@591P6jbU@Zp@@b)oIlkY^V%]AI72YlbK3J&!,T%3_l5]A11K#[IM>m<JoS`X,Q!*@h\14:%#[
3^3Y2W?hF9X<CoZA6dkT$q$A>Xn-2Rr:-D@Ul6uYZM/tnfX#"'sd6I1gjM+o[,JNa)P9G0h2
?^O:!1Rk;+.n6j52u=H^ZAV%j[[l4iHT$?k]AB.0Xs%@OGo%AI-iIW$g6lQbd))qr6jJgqa^a
L91]Am:19bUU2Nl]A>,%23%VYV6ZZjo5N4+?^NCMYe%V=8+3&+0pa&eHNQ:4-3`;3d1"tb(k#d
rr3c1S'V*@~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="JPEG">
<IM>
<![CDATA[be+Ba;s0`*]AL.d2#=0k]A$8s&0Ce6a%.1rFO![0j"OGF2b4``Xo"'3&<I!_(h&4i6#UocdR9L
NR!1aK=t#bF+M3sU\P`F(8#g*;F\6`aBTK5P.W&X!D+anC-@4n"qef:=dChKRed1N@85550N
?rkFNooA;[FrHo+/aNc<WK.mlKKE;+GkL7d!-orgb(UYk6LVq*L7'DYGfF[q<?c4;)YW4muY
_AH<fRh/ACtk)<o(&t_nT`LJjBnf`1,C_CBf$$:=8%Z5b['F(0JP::Z+l)hgoqrAf2hh#?2p
N=c\J2gdFe3EBs<tkTnlD[lmBsJmiWffk;pi@"8_Tk'7l-J(PRd-=oSm!L!(F/#RhIf&W'f&
/i"&e.KZ50Odjq8Ya(@j[CQ&agrm?`>qPI,UJlAdEW%O_DEZgAEObFIbkb\\R$b8BPulF*jR
&@bq:b.6)L]Aq=mENi3=26G:j#;JdgGf/p@-*ONp3'GYbu&q<_hlC1AjPa0msf<A_r%qomHFK
3j0!HqFFU1<l$Ekf(]ANobIiPurjkuE)?[[:]A]A9,t>f_G2AgZ1Rnfl-n8kMCX-r.BdoK(ICLP
1SlH[_*4YUA_qo=Npg:pfI@#$1o_l1(kO_6i2RoBLKV>.<=6F9%46Z`ONC%RpY%&&tQ8=rVR
J`@o>*TT)Q^f\m+KM\LmF0.uS)mep@&Yle'>Z?Tn9.*W,#Kh19%?(bg6f9b7taqE=)LYN'@^
CEl.Fl_@_>c\=Ena1O,?<JSKRFT-;bLMEb+oA,Ekn\:qIq0M##&MaNZQ6o*_+cYh";QlM[N\
\.'=2C/&cZI$NMtR<P_uX$/[u5,lgj)Q8DmAu`M\>,[rr+4_E_C=C(43rK+#?i$#8k^1Ue-p
/iT@XD&j;JiBG.OpE429S9cbM!(02;&f[1oM6b>l#gu!ULKM@?*lA\"\fH^FiX>Ic^Hfieu[
R:jh$C3L6mYX*"N*lS0F:hm1s.EbLb919q6T#<a'0@l/;g[8'm5""_Tr!]A+W:7m&ojkH'A1]A
[lGBKIHZ\q\?_9CMdC<59QW>]A:h:U+=$i_/?68#n@Ds(@m8r\il"TVNu&Y#S8fJ[><IFa8eR
Sib@:]A$<B3n(<)"@<'G#MY.Xnn'0a!L>D9@cJdA6b>3OMP)56m?.Z%#3>,dM'=`BFB4^H]A.X
LZ=,##_\qD7A^&pefr64$in@aIH.`RX$H=>9nR.BFemh,8!JNi)e4\c-j<'+*O=q=_sr^Umt
#)47*G-%;cUMa.l7lC'aISX1Y*Q_a&A[6LBEC9T9>N95*G$TP"_'C-m7-9\4ZCK"/`78Qnq,
d<lec`:s\*nNYeO/k#-gAFWsZ<fV+^EXQ'RkI)fXAr$gBff8qYH7@H\=^c'#j&X.6p9t@K[#
Ghpp=,HWNO+o`#DKpqR"J?dB(m-c[7\<%ok]Ah]A<h"GEba%.#+Ia.!4QO1jA!bB0fD;#,gL6U
j*C>uU['DRg^^U4K]A;hfHFmWQark,kT62cajnu&'r\+K['W:pRP<Z)q-naTi5a[(=kTA8BjK
.;1]AM1FQ;.[-U`gjd.S4tsG.pHWkVrW%PI3Vk)]A%Fp;^qC.BWV!%,WdYH6HK]AP_>;(&E&U]A?
4K(//K+"U%Km;f&2Z<,i979_YlXbGgh4brCtVn-05asqZGjgnlaI'TZ9Gt(<G\s6V0-D^G1X
dME"cbFVd#srVYe61"(,tZRi=#EjaEYC\7GPpn/^d7E9Bs?dnOk*uBr5<Le'2@.+7i#BrU7Z
Zg=.Z'WFk=(lLanL0RI%-ii=J"4DO:.VT#nO,oV+l,<MVYG*6K[Y@:-=/?+=P`,6K=U.OQ<e
n0I-j2SF@`g:fmamtijmhle3\:99u"S8!gVlX*5^f0i/FF$K!/Mb&p9cgM^%lBr`V(r:06WV
hu,9S,`ZZIn9_RB4hXB;`L(H/Q)r'S&0"`Hr8k=<Cu11\h#M]AO)T'7)%Z2"`Q=NgZhG"\'D1
PNFs=?nd[WLBjc?8&a1O#q?`!JVho(3Bd-_k1>c`8Q-Z.^/Bmcec,6lYcSY7!NZ%'hD7oCTG
ahK!ok>U5q\Q1s$fb43,fK#'9(Hr27_UC&[kP@JdRIT/IhQ$Kk%[u%+i[uL/,p6kr0ul2>*-
/EDL5>=(@t:+Y"R_Bpk>g,ZkDd>*uoM<1I8MKn4QqgpMjF;F6I#T<o\u37s.K`*I6Zapf_`P
+n=&b^>7o5cmGKZ>u+]A8OV9p)7t*C%;tf9kdmN@m/TTuDOc=DhrQ\;*F$c>0MloZ"@42dd$o
6_a]AU7h:l,e"SeJLk\4aa.pnQ)f*JR7=Y>\afE2%XpSGHTe$TX\E@l:FgK_UTZ\),=mSD8<S
.*Yc[5(ICDO+u.t*Qr_ilOYBjE`KZl6VWs_<2MU5DU_HF@A;A8eW_QFa2Pk^d)Y[/>=fbjk/
;l_0oa7!^SbRjh)8?LFgk.9rBMU%#3X3bTnNh.j+(RuTl4Y0B7sEX[V]A!dBp!&N?:g0Y[FC<
/'B2>6m-G54-YDn540:6ba^;ClG&M_Q,*HcXZTX_^KgjF5imG4f,2X!O;Tn)tZ@bP)k(6uJD
B*!cpoSrdKd=aq9&bjjd>QB//r/j%c@%-s5q+98RM.i^a'Tr:DSTC/DoVZ)NM#O?_85;88Sg
F^pn_'V(R/uPFrT@JOd;r!As&TZ,P^8*:oGjUKj9#<j]A&e6s^&MC[c&qC7>`;on&m0doN"'!
@IlBBcZf)4Y/?"j+5d)G<0pTo!A3b`P\>2q1AP0P*bZbC;Z\gG`8]A_(tmf0>m((pM$Z8_>_)
cWCbcrnRrQgeACT]ANjiQ'.YWi)*3S/\p(1XW+qGo0OBTabaR-iPh7t[Cfqs_>anXlm^.!XeY
`#I9L@QUMADp[k(QKq<UMM-%8gN/"_K()momlpKdWtf^))PmLhCDc2"$_.6H>U52hF5KEVY"
D%RIPrpHR_;=;%p[b(e(^A/X999t%^#!]A+2@<OlO=#&AS?PeY]A?#8>kU7@If)o'Z\psru@JE
.i7L$F@1W/f%,N,)T/R[[7HL(DSRiGOh^SKDlcgQ9XIFL"`>Zq4X#**6k_Q6^oTrfb&F[JT_
bLq[F@a^`Na^O1&3b*$l5hYkaSF<A>u408<tdq@s5Ae:Qso"QFjC/&a'ofJTiGUa;TpQlH*i
F,6b(iN!l;1CtN9p`*-<ZOg46UcKY!;c"j(!W8;ct^V=3Lt<&@uLTVW8MN,XK7Re?4A@LK,0
orh-DZ(ROSdghFu!b*2*^T`<03ui@B?`e@WWn)Ul?@Id:cpq1dBaY_;1hQEZ#k2:F[=;BCu>
6JdNC.:hi_Pi&V;J[Goq`Kp\BYpT&Wa.G`6_'[[:bTHm&IDHSjr4r7`j-N)h>#6X#%[GR5nR
joZ*pqE4RSDoAr,TMaW[1-+cQQ^3I0nV#c/pF"HA:Zt'UBp71MZ2]AEs)N&H2TeQbZN5lKO&7
VW&gV1\iAr'a!G>6aW/Xf6mUeX"/qYGOi(nOmq"UL7(thH=_l]ARNq#UCpEGi6V_3VRls.$u9
+mBQ%,=aF=YS=X%V;n>NEdgU0BO?0>L%nd%L@pTP"QVAjTEUKY@l:`>`tHV,>_!gillm[D)L
-*A`CBqKm-!)(D\mo&S80MC!p;n#p_!@<7@f0$Q&-KQcsGdhfC3Xf]ARA=)p9/u\eL3<@Gkoi
5I4h!_gjN[rL,_-lJ(L]A#u*\c0q%J^Ji;YLQPJ9*Ag]A*tgOG8?&RXMh'MO/YWnEB.A(f2Tk!
R?k3-5GUqH*Jg(tH?^1YU4l8bP.<c`2YYrda1I3.Yhi[`Tm^FJ'sj:ZS@15Nil&#m!Ekdguu
$BsrI?Mj7Y=*'V*W.46oYY*hKd@[\C%A'BA`$T]Aj53V8(Z.M\81[[N3ugP]AjT-R/4*Uci!HU
%>L>?8Ld4\#HmWMbmkZEgdOJoC%tr1IPa8$?%jR_o2NIS5^Q[UeST#N!R&h:G,CG--Ds<Gsk
'd<Ek`?8nYsk'u!rf_/g@XT>&7MFtp-HJ+AsHqn%N-'2Xs0b46ZhN>L3Kh`'\h3noLpEYcms
(Q]AooLT,@(resP\R1EkYq![uF(2S-aePYgUr4uiFV=EOq4%/O)8Y\DkXm%+eHoGVgKOJFng=
lc/gGulI95-#4[:_oTg9iR)eR+I5\0j3U0`MGHT[$b+B#I9TQ/UmUJX3hPD(U\(abAQ@F"+0
@\\?/Ci_&_lQKGPeIdjp4iL@h@aYi<o`U"o0D-^eC28;l0M^*L,WN&c^k+/H(id:bp8oj;F,
ro,TfR/;Pdr'AP"A7uh?sfXW^+ik%]AeqM<bgC_Z2;oKc6rW60^k-RZ?L@+TESP@nF2F>`^XB
`ZB"CZ_oAj]A5ToK4HO6OXOICP5s[k6;i^F*_*`PikRHT:1[DJqZ]AT\7iK]As0Zp:B`uY1X1:-
=iLCt"N1DVE<L@(4k6BVg?`_;nD)TYlan<RK#`lmIX$T#?dZdSR9c5c35`@g[To/EWf%G/F6
:FmjF$MpME+$?HLi?+`fY&AQ'UM7ETVJ@@7$NHn(5!#nFQ0_q4Z8jYKkR_W\KS,MB>m#[?u+
4L<RC#2T^t1hL(l[U>j@U0WO\<N!kl#A:&SE<1R=p*/ZtjB/=$6PKm991p]AurDl]A<pZ"9fmb
:Est'7(0Q/!(oJ3:f^l+Zb]Acf1`\G,1%?h$W=eIUpJV0b26]AUW\o$n6S?P_@HCEn[UQ5i"CN
ipnhK$C('h]AW3$4KL:NKbR#&RFk-'u%_P$aPrFpZ*3E#n!pG-gS)+HD\4P4'LDdj>o/2!8Z\
8U,)R/WG##"N!q1dd6=e)b?'HbY:F!h%@"pSeQTt1M%dV699nmbJcq1e5jtXC(#s-%e=+bTW
L&>q^)X<^k\>(S34^VfCkQ3OftFpWbTPjZH'+-G_4OU)Dn++m%b5b6\^,\'n^[WM13X>[]A"H
]Aq6+p!cr0`o6T^Kp>8Qta8%GkciL'mP>p1W)`)f&)hlpL0<0Q3U7Lg!p,\NVl3:Iu.<okYI3
3Co"Mqus_o9fA)fVQ;BM;%:E5kE.L%Ns[LGu1_2HWrXQZH8(_]A32XR,$ZRPRd+'DofJcqPD?
FhlI!o/-$DLN5Kr4]Alkb.OWT's(n_R*7.?Skt&-t9(-<LX\GRaI'VmF.R%1Wk6??>p`-nL0e
p(-*sa%+Igmq>/`VNg!3;72/UHah2\j&A0!+2/bfr*Drkh"e/WYP@fi)(&bi`U?lQ-8s*pkM
InXq1.Mk0g/p(!30..7!"g"kSBOI2XGOjhKtL[bgTU=<jZ&9*9PQOONdIY@FfXD!7)8!qSEo
Ka'!qPqK&fS?^>S9V##!V%`;76!dBuV<T35iC4-.o@>74!-?_C&>]A%Va3;5jqa\Cp6+`a;%T
dRb3nmV2RE=Y-GT;L-FeAaan)ert.e4BLM*i88-?R`Vel!B/c$09BG.&*-0HW7,`%KY(smFe
[B]AN]AtOfh6eU/*lErdo`[C,llU19BjuJU7.$<Os<Tq(BS@hpRn28Q@P5Z"cH9u>cu3AAanc$
VU?8e[r\$86qTS,')0k/g3hbAiWh>&5116`MgRBl6:.T12X0Cc/B+s=3g:hgB&0mC`.NakbQ
&m5jO%X%F!A'[d#L/Yks9mVR,G3@jK$H+\s+Feecs`4`eS7[UJr6W*2`!LPj:88aeCd^D81^
Hm`m:Z?Ql>fF#cM*9i(7k>%AR@?ee=JdN!.-ce16>Y+(6j:ZtBR2FY"N]Af`'<hd5M`)39K^8
buc(=Km;1cWG]Au%:<Y;U=!6Ch6s:X36CXRL40BoSi!,r8S\lV%q]Ap*gk;WJ"n3h@J3%Kr*Mj
I?D"_YNr>HZa[9\f7Ie/,9cU]ACt7K'@*=h*4eCiHibeY"<j<#gkSUKl"Z?^^Yof&I1,<t`QF
6`GA5ArN^.b]AMc%`\T$O9^#&(c)F*``d.hNX+<W/B1's<Vn/V.qs]ALqj'_i"g_J$2ITNici)
-Dl4Z\dJXV8:(;$u(#&XZ_;VMfe%_9X4Sj=E9G*s7-P$muY!!j2SnYoWVS93q=P=Zd8#(F*L
9n'YGQ^jehf`Y@o]AORap!^`s%D^*NQ;"DH1UEZI7K-c@c.U?>@&c?!Zn$O^6jQ#TlQ8EUA1]A
?TdFV`@q*-J+b',!g9"b]ATgZqoT`?kKmQpCEUG5:p1[[m40d)=A)%uD>l\J0^-t?@F,0ZWLR
11@)AVqqE8SuUd#Oh_usC0LYdNG@X%7LbCC4PE"ghRBFJO\6/4Z%4-/B%38%$5;)2_,dB;]A^
YQP#!a%.CjTHQdJGo4r?=JKu3!ed_Sh1H!7F6):M;uf*&aFQ2'K$a4ViW_A,pP6p@0?NKHN.
k]A<bF/7OC"AKg9`tGdhN1[io1.`/,sAd05.;tlB-3r6SWiRVnCAB+eg)`>IQssiG^;lGTX[E
C&6cM_)3Ts_?krsrNk9bBCDpQc9FHcY':"SKHk;t2#ejfWs1$[YrK:k)3:i13ikQQZ\L/ERn
YPrkiqgZOAs\qqPn2uS%:4Fi0#fbHYiXnRHt+"@rK0bqD`c9uTD&9,QfOSEi;s<Q=^]AnJ)2t
):*h3\T$LXH77["m[U='\Cm;Gi[q@ZXCI&@pI2OiDaFXGBi.4P<Re<@n8TGjBs+VQ9<h]At2t
B]A+S.4Z6&J@@E&>h3+9T%pKSJ5Cr=:d;%:(Y'*gI!;:,g2oEjrlA_'C=(17c*B@Q>p^KeWpV
p/1[eVp@3grYKjk>tFT?!5;TAZ$kC,$HhS4N3bYVo`FK)pK\CY<-7pZi*DYH<ZU*mql9MYiE
-P[Tgr]AF=@dHRoXi!=)#)K$pi\n0,peT$1A.e`6HM:h7Vjfe-81am=s4I3tkGs#m@>s"fF"J
>t5J;%-l#A-[?[S$MR&,;!h_?%l@.HSQ'dQiF"rSo)<&c&:n72DD^kGc+0qZd<+WPk7R8Y]A3
bQ_FU[i,77BCcR.e%.ub3lU3H7S'j]ALI2%AM'g\Z<+k%NM%`N9op\#hoLUrYsSR(Dp'_GsrT
UB_A_,W[K^U6nG*Nc'ZraUMDVPtO%\kKaKe-(@Y%5"\Cci)Ej,[K-$tcZA)6'L9aOk_FaV)l
?Q7@W!j6l7qo)2*%T^f=o-b(,b^RM6Y!*98'TC"sE-Bs0FV>7!7Z#8<?kI2;D\CV4HPP(7G_
"Umq-s'')lOZjBo/eVk2P4rOn)jp]AJ3V#Pb-G,e3lPcW_om%qXhN_dd)HLd9pjA3/7BK;V]A<
YWm*T^8:eFLh)O=1oXOjit.D#a-D['!S8(;uQQ`LO#bl=jNe4Kgch7esC[#O`JW$SY3fG6]A-
E/2e>a7(D]AfiT0jEEFm)A:X25g[2C)Z&H!$0OAC#AMYmZq\YZ-!bVR,Oqa'^$sJ>o<b[7!t"
^"Qn%W$Ve$f!BO.O4@fpa\pA:h`2n!qfSZ\G\2RQQa&3G^C8b4k2.t>PElo<RS=SFX4534+S
VrYhdKSeoh0i5bV,ReP(JL[\]At\_h[e^Z^9i^t`+rRK,Mrr:1J!?i?]A<D`mC#hK?<KoUV]A#-
+%DHWY`7@sX0CrBsiNB!9#6:Mi'B_6`WT-#2L^@F92==%j4kQ(>ZWOHp'%4G&72-]AOV0?M%L
=FnLmGqE@U\-W("YP$\i\j;TWdd`s`\H!oi69N@^EXE%`WXXXU<Tp<3R![bZ*S+KQ?^GE(OS
CuQ%08D+"2qiikT0LWpUWk@%=@./+*0IW5VFQs--0/B5Jg=`4PDcGS01n:A$F<@hZM&M$17m
glYBb%2,ilrtt?^1Y</u>[9fKi1O'q0kVhg;%@le8tL]A^R_SpM[/J$/lbk8t$<-/^I+XVQM!
lh_L#X99A_`R;"B*sV=bVX80B0&%+u3F0cnSp6oceu3HCPl`F[nbu"q/e5/S:#Vh+hde3=13
YSElhZHY80M!88#s:Lo*qVpDLZ/-Aji0:8'Oc53<2_@0Zl=<u$+K,7$sXeoT&_3.e!%i+E`Q
LlGmr2Q5B_;S:#-L=.oB(S7804uN]ASjP2F&1b`<&iSrY*n@L^<73Q@=Kk@,oipjHLmQ+%N#H
S@-qAR+H+2jfDh/,^.A[W3Sa);8K>f<DFsX`2J.?5k*miK</[<,k@<bVq17\%XqPX:h01dkj
HH9b0A4hdR0g?NZMd--&+$0$T5j0f?W2AWSefODde=jd]AQsb\b7a>,CgI'.fCh,\JkX.EK`i
WY"QU*!VYTeCp?Uo1JB6nr$KLE$05gE/6&r-79\XjueWo8*QN*`XGG_LT^Mp%&X%:,Vf=8^d
un&p!bQ_e]Ab@#[775]APom:2%S:&FmEYPg/*(]A3A/IYui\0YUV+)7D'-&I!UQJVW*Tn$jRpo@
H[d+]A7f<nk505VqEO<ho1)@h&rJaMNe=tteG"k2p9#3q>Ur5THu"X4ld^:SbHh[O@ujURP8J
T@R?!>,nFa]A4-Sq8K;LHuX",)'6L'TjKA.;9M47BGSN[#t.BUcuK253FfR60R$.$q>uG_H,I
c^fl)A6dGC/[(E>R:jh@WOgOE1NAIBNN%RqXp@Y.6HRJ!m_M7C`=JA:^%(+Zda+P51*655=u
i]AER*JEJZZ.gc";uds6Mu]A;Eb99rng_nZ)Hq[Q=-J+X@2NtciN;'Uiqg2knX=%hrD,LSnVm4
9*ML`aW<n'k@pSF\\j0XT8M&^k=so*F1SIS"Tl,mo!Or:`TUpf[/p9hLZ4s>fXS%r7K.QN@b
X6)(Ya4n<Ba>J_9`6YL2!hNVW4A11J+r!0i0M8"nWek"!C8<(fTTjWPb4?=%uhMOa8u2a,hR
.WoR<6?C&>*I6#T?F<S2F1Q(9k/$`gLPriG*]A%E13Z?2dQ(55/Gi/XIJH"q/ZD.;2SLF5-=T
Y+Qb[%fJ'am3k6EgN)KZh/>%MV!X+>JlOq9F0?RL`pX3JqkI\%"t-&r#P/Z(B]ARIRee=c5Ck
Ftj\IY'JmJ8BfFXFR^E&2pK'6Sc%PNH9i+ural"ei/rV:$F*5<sIpL^1>5]A5T&j3_*Y"%o2+
)_1ir'e`*j,-/p17\3>dZ55kPEkS"&*+qV"d!mLZ>lR"r>cpIQUd2t&,Qjm0X-jra6gJQY:0
^(2/]ANV>WO9V48eEF.0JX.rAEsZ1rOpQ.Ed6$/iI+O\dR_iRJT=C2Y<s7sc/Bp?Q4k=SKn+u
^Q,iZIi?(2bc0pY6*V"`F6;IthQ`'-mLIOC4u\_\^p+6V]A]AZ4R9VHHTVCHp8^,N3:@VQ[IH?
i[`)Y=]AMY%$Dc@Fou-b7;]ArF`lZiO*DTg2^^-dLp_;e>/eZa(Xb=i%@e^$i58<RUo="[oYEF
TPb72,K5Se(ZjMHk6P`LWomStX)AceaHOB1<^.SK6kl_#8.aGQI$4m:+pc3mWEOgt1le4NIn
&kY[2_Q=1t"6tjRfCY(>D=-D^!;X,)+ji4UVi:<?,cO.ZE.cW0K[8H-_"rsWi1kU>KRJj^.V
OUciZ_E,R8(NF%1U`29_c%SGK=%Yl^j*t*gRq%(>@Al+^cjq1%]Ar?TcC]A%aSf,aMd.^*=Hi]A
Qi$`g2$S'OmkM'ThgOiKhImPV-LerJb1.eG]Ac:]ATWIgPb(n)FF/34r\5eLF6(,YV!c9po;[s
brX3"PS1IdWi4NTb'NP]A]Ar,FfXZst<!t_oenatL*U*J'5r7L'neV[iJ9]ARuM//84?`&CBNqo
fd]A:EG.kRPD\'$2KWo-?;giAlmb0G6\XO\'tHO&"$OIr2mR@VLF;.fb=!&D[:O#,6Mrh:4Va
b45$^o1$46df"?Ka2c8s%raiHDZb+U9W8Kl'L41`8gJt%>ic!`dOK_KjnAI,RHWn[BY[>(Vh
!$7i=j,UgR1N=ma^+t9OM_22:2ha65B-WrN9PBB]AV'DQGhO4dQ,Lb?Bd3)PMCD\5X7mrA'.c
%]A?2LjiZIP+*@GZIZ8E,&3*6k1U/=>g#2)%R^plg0/bjTY[D@iDBgTaDeo4q)tk;QOTI!NAA
#YHTg2jaDr9FE+j<qT1^DJXTD=bB]A]AYQ\m?ZCkS5qgUrih^%IcSN-S_qtN/E)#Q3Mfl?3eXM
4I6>Z=dGYNk.L9RlqJ'@lVPIlBT)i!qbuH?OP>Z\>f5A"-uZg5ug`hn>kD0u#24rdNnV3i`(
-F#kI^eo6lALn%O?S9s.`(o#o&?03Ytq4r(OC@4B&;A,GtJgbeBQ8nIXU:2FU;g!NjeN<17E
=9Hq=smp,Jm=Z6M0g=UZpoS:'UE-qhna6Fo?d]Ak+[;l9IR:E5/>.ul/&O5BgFsf2\pfl!JX_
hn>UZ,-)[NeSTE@,3&k&X;3@gL,*Y[_j^nKZEQfj:2bpMTM)uP_S4*P=Bn$OM%"V9AO5]A8le
?5nLA9/.Bg'Dg:9ZMZ3&HpPMA,e<\R=10MM@#8"Z1SU?2j&D?bLT1tgaUt`Q421tp]Ad,/bW:
",)gj)$*VRp%\PC,67:[J!f--]Ar/#csCoV8V8H971"9@I:Y0ipL7Un5Whf^m,:"cJLU5;86\
-ok7m<J.sKGcGHNQr$NUh*jFo,'usn@2Y^GhdeS`f@uultS_q+iSc@MMZM>,AX+pfQ9`7VW+
`3?U/i0$$_:]A7\%SaJ'rH6^#rIJA2JQ']APbQP\"a6S!,[*fj,/)f1L/1Eq3_6C)g8^Qf/nF;
OnJuVY7/6EO5Kd'4;)YXo0bt#\S<S=BN(Y1L>TQ4mm*NH9TmX95beGefGIHe6`i09T$R-m,*
JM_)l]AmU^9?(eU9,pKPHlXW"'QftKZJqD7B=d%5GR!'p,d=PQi"J2,ijn5<,>.H4[V=]A&eaq
dKa?XLf^L*n670<0&8$df>B.\JmA\L9_[QZn6K^_fVd%T[[t_75=[?hICYRqPR1W]A/0]A3C7B
\,'SX<G0Kg#eX/>M3+HA:rkc)&f;RO72efJ7N8m7T)o"Bg\*R3P4[9XEa]ASV^Qr:&k*t14BP
I\k.cPYlI>1-j5BKUNobs1VR93h?gpXJ:o=H*`c'sE-b;1C6IZ,L;l'H>;j)LD;jV[<]ACcA$
>"Z;)fFQIJ]A,@D=$`'[\li\M84RY*Er0\<[Ls$dg<5JJf4W@YR%>4q&*7H:RaR(BMEeRZNo7
V"@bt*>#$"X$N!]A&uldX29bF?OTblK(ZF7FXc7P'6oT*5o-@qNPD(OMZC)d:GG$=C5/D6$b:
-rMVHcO;Y1Xgh<l:oc5c>CPNqGD!8<TR[#`=VGi[EDP0S7]Aa@:9(K'qetm0mp(C30k]AHSpq5
ec?5TG'!<$A)^T)>]AnXgR:'2!ac,537Z-`c#Z(PA/)i='Y#)a9g),0XAY>tRYa1dA8(g)B(`
:n7#7'=H3c>Yr>MUS2:"K%T.L.6-=:(s"/C1mXFd6)1NkA&(PGG,<p%+X>2jE%h<+E(4Cgb+
[!/SpZaQo+/"N#0s'jY\cOk+S@7+@%j7lPbq)Vh/^[Oh]A9`k,YY^%]A+1?-K$593QmFsHQ2pF
_Ih;jH+I[dJVN=#I,Pu)s0e`-f\U(?-?f3mI6!*eZ!sCp;mUu142?!G]A@>E7m+#)Be*G/O5u
L:e+S&OToQe;RSOoI(+g2PYo`n_,*".]A/-q2SC!uD6'UCNDK4pHBE=>U2=j'tDkU/K&C=-#T
)n8+L[gd%ebMf2hJXNMKml7&t1-T^lk0^YS3-[I:FR<5Jh<DQn:T='KTkD:/jYE]Ait&m!8!i
RO,\9Wkd5OKd`V#n\""I,fft9,W+@HWfNXPj;jKB1suCoVSDrS:'Hh*T`o5>F%s*=oX[Eo!E
]AkiQ/.7&2\TH$LjKHNW^Z/Y5+[eppbV1AO$0Cc]A;=s,hBE9;@rk-X3rQe^Z+I\W&Rm]AbLhmX
V$j=TejJk^3jM"IFk[FsGefK6`Xpp'2$(:,fmbo;><W@AdkP$6Xos41Y3d0$$B]AJ8O`4b/$W
kPDAk1ft+.1QKhfjL!@`@8'ml]A1CH7T+i^cq_-cCrB13Qp3`.qW8!?a(pGVQ"s_LTe\4qQsa
6Oj<(Q;9XF<X3strcq[sf>t#X2SBcH#C$8R^9lsm.E8]A'3W1;84d+>^p)kX`E;7cu$&,&oMY
D7Y?NlCce^q:KOWT?4>BmRWtPs\-?a>ue_,Lt5@Mf<'&qm9*+C5p%*!@"f"e_rFhR?]AIb/+Z
!IaJ69,Ugtc&+4,JqZ-Sqemr=O5#]AcQ7Xu:(C5.daH_H@dTDqhj6&Ib\["5GMUrC\3)3^_3j
@nfJtg^TYg_g&5#,"F00%leW/o-iY:f+"Vf;,hXK_o"9+\_Ag5+rZ9/;GI70U7!DWD&>j790
RKTBtd-r:C*s#k25G`b?*Tb2u%Ki1re-^G197h)PaHe-NZO(+rM\p11FB$+fbX'1?]AE0`6IP
Gi=o*5q#I+A,n7sZP'F1p!Tu.ZGWHfd!)&m!o:[L=;.'%V(_tp>)G)IF0qNEY)%)LKh_GjOd
d5i?.W5COIL`VsfH`t=DH!4V2@HaUc!?i,ZP78.@%@j;>#/HB2WpUsphY5?M5FCajlfO8S!(
c/'K:gPTD#Vm3sj7d1HD#DN:Vl87QZJa,g8nNnHS:V+6-#b]At3Qo)9QM7**UQ6E4P+HeW?h3
2@h1bAqdBn)i,=%Vn0&+*UP?is2%6@dl']AkpkkY?!%SU*&9Odrjs;#mld4o'(X(;607pM8IC
<^\U`B_T"&J;QdmS/+h.9Z_=fc4VWQGK"7\$G?5=*8TW,4@6jF^m<$2e[&7)c.T83\l:P<+\
(.Kl8eS3"JK%o)VeT-a\0Otef>Kuo_L6OV,,p"7?c&=OR$gpk?[YGlK,M#3Lk.`H%ZdN*sUk
r/lQrurU5f`>V?bol)Z;6%9edIqULi2?<\LK\5HQJqnK"qlkX-rD(F9XDO$(^k$oI.P8`Y1J
kee`H=8I)A6)B[IAL2P,8*WhmV1<7mV[+6)QF=WN(%\9+Fr`+U3CG.t$P';Dph\iI6`c_2L,
!T^"'O#A<A5V9fdBIe3fq-hnB\5Ou8fSKHVWsS@gO58*IclpK`qA:M[k.rg?jr?n#,!$4X+d
op>[JWU!&]AUe>kEep]A`Ru"adPtX7\A43m/6p/HPofL1,0*#Q'(nWt_sD';!9;SF1.Org=#@I
oC&S!A^73m?P4M)]AmDn)k)<7)IjQpDSF7M7rer=KjddP=oLA58USB5<ZOs;*89Uldt3O5H;F
C:+,&&:SJ_s.S1mb2td5DO\T(n6U4%mW;&CD&&l&^LQD8Torl_&Vt1(0GVj<Mu&_fEYKonMN
:H-6%QY!<!]Al.d%`+;dE/Q?m*tnW309YV0\@8RWV^iTG]A6,AdmH?g?mgJ_P1b+a?:H,p?nXs
f=t2r-fEnH8oF$/;pA_H),&6<MTDlh%[!6#Qf:"-1JB]AkSmu;07ZtXon3TDIKVl<.l1I'NWs
nT@W*GW>NEt?QoifsT3@Q=,mc:W,3%E?ZM7Xe@2)[A8\<*&Kf;-7>Q?V2"bZ=Bf(')h8\Odk
;X@HD`\KcOEEm(J-(TkYir&:m0#YHN%qnMZ/F*PTZR`Ku@r5\<nN'?Fkb@dh-'/i*;\D+r9Q
ZlmDMf7@M!Vg[`-lfQ;XbsCspO)nN7G)?</UMV')^=ll^6/I2na<bq'h*4lKI)^(`<fm15=6
+QIYRHDFM[e!Qf#'o3J+qMf;)pKBlME'Cl>rOc/J\2(CaZ?_$(M'Ba<qu6]A?)?mptGec3)6'
nV:oN\[eeKfStQR44A/4?i^FmJVK[K]A&Nm>20JHXqKjH_nO\e/jqVk;`%A(U1\D!72^B7fY2
J?T9\^s:3h?*DA)F/M>fA#7D36hDjNR6;8HaqI=l6,!"KMr^TOm2NY;SP@92I))^^[#g2pld
)j,qfK0V_b\gGI<_mgC+7Kbk7_L\-ObmGe\2W2V]Ak:FlYmFIMh[rl?QeApNi,`]Ab3EQi)lDH
.bK3%_^Z.=kW>@fd5Bn/<8/Y;Cf7`+H)]A5fcA!"L!^;s<5[LV'n;IVN<bO`9oR*ZKDQO\X.#
EGMI1M]AN1%bVgXQE4Y>&&?>?GSFgt[?:SNh[O",ZcM]A:Z7PRpe]A>eLM8DEQ`AdGDUU_Y9e)Z
@F^VM&5l`q*Kr\=C^:nLLh2R4SnYo1#ZFD$M:\ZAb$%d6:g0PXYsaV,b_>QEAa0G(Ul^?3@e
h0#O,";*o3/1O2U5\q5qopL)(=[$\=`QF?tfJWXXBP!b?4*?^0QRdOK[l;!!4t84'cS_o3t=
dUR);Zil,;&$;oVt9PD3[M=1O0)us/)22u"Hn,gS3Q2i2#*VAW[`Y_r)3fV?c,dSt[@77R@S
?*;(PVeLKQGBY.S>"j0+A?Z2p"NKQYW(N`Z&"/h01jA!NouN[\]AA1:I`Id7"8:stR]A'U0TK-
tWpEPmZ';EJKFon!l4t8tWW-?lt!F2^C3"30'$%NRBUPm0`WZ+]At&?[B@Lh:`rFUSV\EM(#,
#o.)\G+n"F#M)?8gW`DOBG2eE)ubjFmkLOPOJ[(Q.fa.b,BqZ9aJd91Qr]AuMqY(<3=9e\X"p
4``<!F,IED.#\oE@Z/dp'oMZp:M)B"Srm[t7u,>@m>Yn]A-A06(^dBM&n=bVM50gN!s.aJU-'
:<#F#rRIF'WYRs>2BM2APnWX-tJ&&X8L-qS[(.LqJrupSnPS3YKU"=@?BZm"GZX3]A5l@B4#7
QtLUT\/fd+QD8$:B^jm8-`0Znj[`?nZi@[[;0WsHc:k"'2'1^n/FO*<<1W,JgABHN>E8kAW<
HYk#6-N$-<s?/G@af7/S)BP42$cfBG\VetlN''1ljS'g>!5,au9\3uI1Sp3]AW%pqMur$g/JQ
6o\R[io8q]Ad&Mdd?Q@AmCooLs2!F+(iRuSXp^R-N4I?\n8#8D)e5tW\Yaiqfi(nTF>-Lc?b5
)6iLU,&.n3)+G'A+YtF6LS+W&!,6R36GoG:$rS\rgUl8HFF/+YnBeP1-O&--8FmfBY&.WJ6d
Eb-`i;Y4($F.6BQ6[oe*B*%[%5cAkT)-[1%"K<9/cnm"-JL0*ut"B@r$$@#KTL5IDtIh5$0^
-fL\ZCnT\4'6;k&2lL#8%e%+p:0_OjV?QVD!*(4R!leK9H5C!+fk!Tij/9pB4l+hZ^EsjV0a
BV4?Rae:<DAq?lgnu>T[=oA&[d\q]A3t6s4#3Posc96]A3s&:gO^;o86G*75Q'&=FDShr0734J
s!TVHFA`0A+#3";[h"_:/,e"lH?XA(rmq<;SaQ?caMWMA@6eo74]A,/fe^;(2qHrT<A>)GH71
?o3-t8T*P.$-?$!:!YKLkN>"5[f[9/-2Mn+Wc$I@60H<-emA^#(!RQ+CUm$n68_S.or5nY8O
#O'Ltp%=(*[_[mu-":/:jR8`fBVS]A%rL7Hl0QO9>WiGq52s/Df:"eJ0cg/V>OnaG@W-hYHF!
>NkX\knf.=usE:6ASKrlX/hgns1FR"SC>8WqQoPNEK-;?*VDWqEi2pYI31tVZf_B9s8jMgqm
$'8#stC,,+&*M>q05`Mm&2KrQm)k$Z)1qP$2f4^idb)=90ZJa6g[:S(s''jf7XF7:_JBRQR7
=H>sNTS?"Fpua38mPSf9pmo@FJjrP[=/q"\%_&%S\,4F$KQ@/c)upu>nkRmmUHjGnQ?FEANK
U!M>6Fp/=I6URZMH:,>,gZ_^)]AM_+h6Aek]AkBX'mS$&*hQfjasB`&G?K:i'0_"_XPH113,L?
W$9/QTEbM]A/`MT(F+HhV=(7APBO`UaJa5WIOgb/Wgb6EPD[Zk>=)o*2VhOFCgOF^UTIQuc+\
&`trOa,Cp$;rMsI>q8oO0+K<oWNT4o%-LeeBWK:Y>1_tT:=EZ@0T9A*%m?R"S)airpiLj!%e
1M]A)nCC$jEXrfhHoBCg7Pk&(V:+!>M^g.;lLgp5h5JUaa5m0'\'t8T<6#nkmVtR^+6>ffrUf
EI>C)5fG9A4$Q&`d@(Y$T"T/A1CAuC@VfQ#dk#lX#`8=u\fY_#a%^-8>*5bSJWu"=H\`Z"HO
'H]Ai*X<B%[uSX,34-6\2+AD<ub1"*@SdRh<pe=7%:3+I?9;oPYqAb2-C0iqO@h!6gf/c.kXh
_#t>O9$kj:d:8nIs2U="D!_C;.'#4^S_G@!\QNsI;!6gsT*)XsX_bU`VUCleBUhl^fWl)o#,
,q:5-U2S*F_![b=$,&Qi$)5Y>@ka.3Ls4lalcJA11-&dBbmW1F7)8\`rLXK<#dIP\%'<1Q(f
[_+K,Vj$0rU:,8Ru!A36]Ae#m%*FB9:\BAeP1GgiNMB$rm?Fake!8o<-*PSROHmn7s$=H@O"=
bAUHf!H/nuYhfc:f16L@UkUc"'mJD`:?Yg%$)A6G2Z`^T*<YQsWJ,!"9QmN@7FfU(6BL>k?r
!Gp=<]A%t+!5s>9>S`3`WqgG3jnese!>SckRKb?emJF$P-?^j2C^H76f#`1?fj)2hJUPh&G`q
QaDH7DT`?fh:r[E.Bi't?=Y:F3Dc@4\j"ih(o`=WN"S!r!S:_-H`]A7-BSX/)^2MQK2NpH2PU
&39)D":BW*!Qmg>SAHCNJJ2JFQV%)LA!rJg\d!Wm3P5L+>Zc@OC9W><#fFWnWf:N'l!GGM"J
2;dCDb@HROlEpUdM>Je!KUs$XL%OaNBQmhc0ZlOPSQU8`r`Ep/b>D3Z+S-Z%"B!^-PoqjVl!
C1mWn1Xn@mW'7eNmLNJT*EVA764MrhnMD,\br]A/dSQ1V;FkIsSK4F1_:TNl7o>#KSqTh2A!,
Y'm,f%EjV$2-D(MeH%q;rQWPC.`3&_5!!d/n=TH%;K.lUS^YP.n/TUrDA5GI^`XB'%=l9o?p
VW+l`P["]AA.=B4<57O"lGPOHL#5diRKlC[Yps$_s(jg;H/O]Ah%_;`;D/&U$OLAst7"k>ojag
IE,nA@U:!lf"9CMbSm8B<IlCW6+Ms'nAlE^]ABsYgE8sF/2:Me;0UK]AGm\WX1AUgR1%UCg1,t
fn)WnWSPRXA3^L3pIYOQ06"^6_U9C\DUl$4HcFdq!XU=(lRG/]AE937Q_&eulhB*(4T.>5@,N
98lP,FNk(UeME;*]AI(Ngal!osReULO/!tHcLM'dcM8/9T91q_!odIIr`TW`c3)L64q4qtp#_
]A"sS/Wf)Vu0h6D-qnj#-paAm=R%9\/XAO&d]A4?Z(lZ8pAKGf,Bf46.%#g_kMFss?E;WMC\C<
^cmKV!)t-d))$5ZkLSY@i/fUDHG,PBHLWR"e8*09S%%ro]AcDYEu-@g/Ab%Eu@,8KU`jf#V6[
]ACu7hGF&scIicXHq7'?e0h91H9:J,UjTNoSa(4tmV<+hA[s]AGC^>^9_iDYm:jB!5<EoT,27O
!*(X]ATY6?EHnW;s[*`Y]AP48'(Li&I^7<O8L@J/CAJOp;Zo<`,_a.&M0nT)Gf[+b.OAD3%moe
^mcW\`I!`\lX#YYWB&%]Ad<tB_oCkNBdW;=m%p<+lnZh),5Ma+h6>3Wa:Zpp\-I,g3<K_tLms
<^h9b^HtU_$^JF7`Ruq=D!\8cN'7G%n(#quCS5Y\XXL.i3>rR6L6Y)7.*U7n+8g%SN+fM4]A=
,=,aSfcFN&\mHZ]A3)$.b;6J[SXVW`6/S'qWC8A6V-KI/sG]AU"af"UO)rmWB1u2[^r\jKE`36
:\uV1TYI``9NC-^VmQUpTr%m9NspZ5^mYe,/bEoX"(<'fRamPN's7D2+<Cdn!:mL58"jo&L4
qq_7Ai5/"=-#@]A#SR6:j6_I/iq!on/YbQhe-)ah70Q%K^:I]Ai[1"mpGBh8rFJ@Q>r4\fta<l
S)09Dn,.DK5r[2453P1P.qs7rKEi*5@RljKj1s8/,*kmG!THV&GT0hm%U7\3Di@>eR!>PqS[
ZW+"-[kB`ThXrITWXbcU)&B-/f3^^Z95"5Y7u2jM8qf5?D'C"Va5N?DK$NdMHN\;\JB#M/sj
A\feW6S,<poW4#g.U">c2L-F8#kX<3iOc3__WU'j_+Fqrh2lA3r"!M<@F+sBV#jE<9,Q]A"o<
'>qMZHr*kJ:Bc:M+o"4ak)E4qK#T"EZEd8i0hMO>8u)%JelMkph%\b?(hWj0k8h8W2=YM'[q
?'*n'b'SI4gW'&_nQ0RmT`SP\Dd.[(.fB-stkY#[Y!"k_5[gL-m1qu7id,pM%p$VK.-hAUU-
%6i$#/fuq0,*&WV5)7:-*p)/.L-9GbA'Wp)f'HCITUshd0V0KrhJ-$k+m%1W`1L`1b10_Fdq
WD!-Pq'!L"Nc>l-/lOV_=4r:S>-c_4d:h><`h;fmJ]AbLJ,:jZb-N;RsI4K*7a-*57,Mg5auj
'_Nomjg_k)ZPh]Ae-#WN]Af[YhqfRfmJ`iZ1:"HT(WLT[cc"EcPF9rsYCoj[.!!(B:U3p+0GXV
.D#iB3NR>XHHU!CmRq8Ug+G58lXj^O"i"sa<h)"QZQp0edjQQ;t?Qt:;@Y9?9g:f:7?3e7H/
M$B[cSb[$^UKRj(/?Ij!7)Bo29XQ'HX=Z`,Ul"pds+?\B&LGUiRD-S!9mNCZL*r3V:*e&D`C
nrF)RVZs?g5;j))6Y=SE,s\^FR9H4/?g76=0$N*9(HqY7"H/SCSh.=H)XZ+YCmFeNg>8]A#h"
_u9:j)o"P+`n=GEicbcR,[@BW5(XFqpEB.mWRE8;r`@$`teG>irouUO-U>_W2g-'Ki(_G.8U
I,hKU7Cc'c1bX^h[2G:QTnlFFBC?blI@[:l71R`<!&3dBeW:"$'e[gi\GY#tposrHA%lR1>[
2GP2*)X;C_Ag"-<ZpDV/e[Fls"htq-n@#t(m:9L,SN'"H$'/gH'H+HKc$.>;#9qdNJ.O>5)I
,B%lb.bLQE5i"JEnA)%9+5o^f?uIk%J!6tGQ20K!+Kj3:W&c)![[ncNlIfe5K+KRk"cHM_@H
Dc5b^S$G[d"V-mYqp+ubBnOgkj<d_u$oARTF0[u7<1AhiPah%<]A/.<erkO^u/FaG<rSmgBf!
-r[WE[qlPV^R"+nr2CQZI!Z+QWbTM3\:=q'%"(37YbFir)VF=Wu*d,HA;i6%+i^AlhRJQZqk
0A>&VSO1n>d\,f(kD2aCr'$WtU=,>i'dW&p#"aQk5@_Tm?#IFQ.C$:1LXJsXOo:\VLjs9^61
Oe=gFH#OJW$X!b=Y\<h(a;7d&SY`";!WCs4Ske9jhpsbaF7&J`PpO2iu;DNljQWq)&maZ+VJ
!-pg$)QB7]A&C+6X=tQ4lNgq-Li`%BL9n+oMWpO!X`>hOB?3FLRS25*Rhb0Ws586jaP9%tGu]A
A'^$'VLXO%<iq3;h&B>rO<+SpptbIFnRPDrnjYGPGVoQSIbsPMhg-=3Sa'<^93q6WfmN\mRl
f%eJL$IF-9A*LSfMqKO+=Mh6Q(c*Ggh9F5d+!uk/&u%Eg,3<N[BgAh)6BB[bljI\e88-pP*J
CRY+oh3K_Mi)Y)=*1<#IdghS_uM%P_ci<$"L#b9`d5LH#t?+\8ef&J84PlSsg"N[l!+%ok..
c1.r=]AHAR=D?-nl(h.[$>*mBhNN(+GToi(N'.d]A?CM!@42bgpC4,jSH:QDP2u`eMLKTRJn+m
,%bh17pq4hML]A.)b]ATGUArb=U`(_rf2,Qna=,7#_>p4GDX#WLgjN,9CXFX>3bDJD>tBbh>D)
)ct7R(#m2#[iEUMPE!2+g-3Vm-u%IFoXj^`bf>CG&FS7YJDjdCKs_(c=;:mC>0hUuiZG`SLr
8%9'oJ/-T67(ZA%?dC']A@Lb_GrCIM^f`;?AAE1-?gZRj$HSGr.j#$j1SSA.WDfGP%7)F#K.-
4Zt6p8N"V3eO'c0:(Vhl*a*KALfq2D;Gm&MHBE'B$oShC9Ak,<KX=93YS;[crQLaespBZf,%
'3BD2/\N#R@CC#ZL_Osh*9p-/hcP=h,BkZc6qX5VfLJR.2[WWk%n-WYc61;cD-B=<P)BgrrO
PkSdTsiVi;mQ0.&rFKAi9RZbqnkI\HS/JmE38p.a%a(gi"p+p3&CLW<+o_pKC$@o32?bj!FR
Y",VUc\K_6N=Pi#(Eo[i13Dm+>=Y9Cl[3VW/VncX_!JPG0jlZu;^5i0@@[ULnNS:=<#!=I=7
76HE!gKsP!m8m>82c,5RPigdT]A.^58Y3r_*.A2+YpXR5eGo_iL?_*1h=C@[Pg;_P1GMU[a4c
XCQu8;3!OGPD>'^`gN+Gfb(0=:<s&OX'FN4kaI0G<,2b*T[Q=.9E+lJuV[A*1\&OgC:,RdlR
`u`.R3.;UY]Ai.o9uUj'nF$Emi]AQK6e]A:h]AH'cAe13QrPI4<3L?uV/J8mKGO[=spS1uCj`K[>
bg>l@5Gfn2i82!`R6iB[GfMQ&BZ9S7p6a&=,Hg$,^#r<lke(Wh>6Z&(c=I[":+'qc&1,0;QA
/gAap0*ukU<pM"0UOj&fQIk-)qhVJaAWj6?Tpp/kX9f:Y&<__4@r[j(>\YCt1idp(F6K5-5R
RB5E)8Y5^%34),0`W!!RAR<[<_R4=84%HG62'h%pa.rNEfEG@CpWNRrBYc#f$*YY&'Q(&Man
(ECqAaR/LfMQD'7Ml:4e!(\,6r4'L#OfPo2(@H<9(C,DVilpKkJ&cV,AP@grlZ&No!k5rV=9
+'0d6@jg9*A\-+r@/\cg>c[J\:&diMbO6sfTH(%-G01JBOQK^&04;`(sk@rp%UO3#jf=>F08
M8c'G#LH&:,pJ[pa3cF+`H<diHl:l5RCKo6W:7X%.k_2n"u5>F+c"ML7!^FuY31$EgH%UtcR
#K(ia[kA35@ZLd@C,Uir+r!5`k1iM>oCqQ?eRIFrpihn=7e;MFE]AV=mP..IuSrp`4PPKba=]A
DP-g[0me9%pn,rSgIj9!IA$Qc_7O?R.V)fBd`&a.0LtPj3l+8K2Rk_FcsDoWVujbb?cD(L)N
qG?AZgc8U\BO_1.uAUTsW0N9Tt2Y]AE3m>MI'cmo's1KJtTI9j0(_]ArD<rF4?<1.HH/-M1UF3
doPIGV2lrmmP@CE0217d).I<]A25=Mc<HsSgsG4e`KQ3,"2NtHrLCc[QH^gNq6LG1902$I6-2
X76j$VO3G"##D+m$Y^:0Hc35Uk[A'ufH"skZXGe=Fq7Z?P"%T&L,\IT7VnY<3"0kRH=%iRJ#
ZDE>mAPO"0eM:m-AiOIpPM\i2_&q+3r'7H<6%6Lsc1hID9%4]AFP;9=Pea6RieuO`_Vd7n_6=
288473"$cuM*ii)Rpukh89HN6=\LiM08GGsC*Q7lk8jASB)^L[=lj-m.NWje;G%94.&W\_Te
^Fgh$^UX'`m#a;f]A+R7<rFs6/W=rJ)[qf<"b31fAU<2S2Y0=oMhii=ZR.eO8JPeti]A.)HB__
ROC29\%AR7+_6CF#e,TJEopUBLakb*-UGl,(6.'\i<"1cW$uJ[beeQjhBG#W-@&X;]A@$A^#(
\L.<>n`31'1b#TgdSN3C$.BAnLJZhUH`1um)Rd8elbg[]AU,0qV=jGE^Af/;h6q6KDh/nYr[,
P0F0ZWc]AcCl050B+V9b$kq<<q\*SN6B.W5D)G38&4&u2<b?H[urs&.Pq;g9kce\dQDYrDj^\
\UL1E*1cYc1Skc9P!EC!1Xifg&N@IgZiMk>1_R[K.)_Y&0"&W.-t%Phc't$)3Fj&#0uA-(s^
nd'p4[T#tsLa5m1*)ubhMN?_FecrlX<\Jm"C::uY2(6Q]AtPXgBj(FptI?I0P#E$LOTQ9h%M`
FBRPG1Z_5Cg'?fDD]AnW4ZZ!FbBM[2lm=9T."GDD3dN^o7`fF9,blB;Vg@@9<Yg^>=,$G7PC%
!>a!+DrP)4.['0:=XDC[pVKEn%)c[EUFfaPp7LsUb8TrTFDpe0d$Wm1iQnPt\aoqGJ%(3^6E
G/[%1c'_c8j/agPXWL7_1<;C,na1cJO/=c"9P'1k''9^kIs&J-2>b;QbZ[&'QHW;5I"p[gQP
A68&p+5]A9"E/f+>3he:I10UH<b6D;Y]AVYB(#W8RQAp1nUmN'XX/.$G+b$@3&guqh.hW31]A]Ap
0I7k-6n>#UC/B`VsIL#B-$8MH6Paik5l*>")Kaqc`&kbQ5G%p4O(2_#]AWs%h2_1XOD<7%:n(
b%\4!/W)DpN_ej]AlpT-htR$9cL/;+J"5h&/kF8Jch#rdrOMCoo=7#2J*#67_@kq>EcWN#VJE
fd(N1mR=II-Pn_KQi^_tccg(<Y@?O0YbAaXQW5"4I%e<G8/3j".N9Gucg*;"[INNUOGc"MF,
2)sF(F#.Yi,0=[0G_0ijLD=Tm^8U2GPk(-P+6&_bU>fKB=W[STb.a(L`Z*gWF*V'j+%?V^\.
bgthWuik#WAW+nn98NWi5/K@ZJJ"a/SNt\k22nWo!#dQi(VB#Z.:2?#;T5SH82ljPmZL[``J
M]AlCLY1\B_sHII[Xbr#!>A)ZZRUNmDFk^8?K>$(bM>L'l"_^W:H^9JT^^ii<[2-%T;(Rq!1G
UF;+Npim:]AWYM)OqJ=6IZ[MDF`q)^\nXKF0E6;K4J%SXq7]AYZOS.5b'u%MsFo0?VJ,V+0e;J
X0S@_fcnE6m`0Q)[;BYp_'AcJ$A:q7NGVk_]AJY3/3s\:XP*eh8L#n!F8Q_1H@tc:AiB4+19F
+JPfUnK@VeB&;XLT#g<Hdf?NQ$kX7f^j)``<KEVKFNG7?^-62b))pUYd@1Bkg2!Hd_"+2o='
mSr<'5E`,]ALU&n/pBI2U,o:dN#0%!fB+/et:q*Dtg)U>:qj:B4t*4o2qB4d!1VdM$X.lnsbs
Mj`l9<B-.k%'2<P)lQsNKn5Q$F!Yg'0kCh[cZ?E@*S;pE4_A`Y:D50Iu2V*-(d#MqF16m^Yf
OpWh7dqAA09BDilJ7jjg>[9u"@!?4"U;?e4Xp]A[gqK@Elda.nj-1u1:(REH9;Yoj'0RC;0d2
tCjCj]AO>7\s?dBK%-+KCnnE1hsQX68EBK]Alq+P2tT"8>p.Z?Ehuc]AE8(4f"B$M`/c.RI281L
^?-@tl4J=60L9gRA$H>Kh`V2Gk/n.>[5b8>>^g63Ig4FV)BmXsB;"M5kuQSTjP*H'WQNu*;q
;3F=lDZ5HLNJ8FWp>^q3N]AF[q&*8VTp==;t*%H?OW,!8UE)]A+b=%HQ@:G(\ZIjF[E?NABDXk
/ri3gND;uMEN+J@?VhpJ7WtSG2Lf2dIZFg.L/\dc$QjZP@`C51Hq`[XaGULG>c@diF:8I3"m
reK$OI#C$W.k1:J=r2YXZ7?do.Phr:gN/fgh+V[#Hs(-F7E+;@t)H(c[[#!S5`50f3Tpjn.a
9;"&XK,c?Z_[A/L$B4aV)k%$0W3Q*_jpQuR4DRWS+/cgqrC0E<up_VE!!X"j47Y>m>jqO/R;
dLS:q!:.hGNAXhrg<6WtJeW1r6.?OlF:AD*'0BJrg`tWV#0b^$g!D[SX^)4bK>';MYp;ZQB9
k^o#TBXR0]AdNMg*MP#NG/fBR`>4Q&Y-.@QpXS;[)h4IVQ]A8\m0^auS8X8HS1AS1OX]A'#_m;'
4a]AJ2*<D8p-W;DM<1!]AYFp)bo;]ACVTg7C2P)m*W6%kXAF&7jSGZbWgUQB.CFn5e0gn]Ag&P*/
rAh.c09G*j%3COH+(3GCT.:*mK:uf`X0bm,@hoD5$ZcC^')4i*b]AhU%s_&t[Q2SY^g^\Oc:3
Qo'4SpUD(Ti9am6"27!mLf2rBip&S0CDP$JJd>MXA"0t0SaWS@bV=Y31Gkg.DtLb777TCZ=f
lI_khD8H*aD&qrlVO^stV0iY)7^=K9rGM<rjX*"Nd%J$3\?R1#oD1N+VgJDC:,T2ooVqE%l6
CbMkp"MsP/jr]A!CYhRq^)'$duiFd@2<rI:=n;n9\n^@gMUM>^o`<d69gQHJQ4ei:]A6='rG;:
\!hG^tOEfSu&P6.Em)R3A"Vh$M'M&?90KXCm2l#jBpVq):(*eu'ZJV&6*8SYJ`!N$FXAjhtV
)%59Se%,d_/^=H>S"c`[oM@_U8)#=IKATV2Z1[Q>=/o#jKks7L.s?&6-AmahBOTEY3G=#l#L
FkLTO;^5XjUB8%d_XY.)AkNktY8^;mj]A'RaBI$4ak&hh^5p&tLV1DY`''DfLt<]AjdmLfj$*;
_qYrdnCX.8]Af@.>Ju,3b=5keX;B>NX^ZK:/Kmf9Rg<p#9qafTJ^@8V#jb,>0CQ0r1!C4lKgt
j?Ui4uVm+lhhW&WDF@U\_cSjc;->*sW;jS/N9_FXbWZ#&[I(7=+!bHA`*gI2$Z\N"=4aWSW%
bhNfZ*P=V+?AV_Er;q?<&2,KFoq?6!K'q<R!'FdAm@!9K>)6\Nr`8bc'5E+QBFGWRI8=10h%
\Ym5*'@B(eQ[:M?8_>2b&89^\+=9r_@`lFl[6t;I9Z[AD@ps0FZV6m2!HdM7_>A!%GfBYlTQ
rt=^1-/jTRL_dH-ck_O[()G>kWk1BG_D/%fX^79FX2Op:!Q"71EuE'q4?a7o(5l*r!rk%G/&
=^-6ZHAraWSEcfe(uEDj`:a-]A+TORh2g_rK@7l9sRk<5]Al3<A&=57Qp)b]AV/TDa?j#*8&4g>
kf07R@S4`-ANA8tO/fg(qnj]Ag@cEomPq1?G+6mFA9oSU9X>%[r$U=hW]A"-M4jft2lNmDYk8q
/5+#BKX3(OF-Cu_nCM0fq6@"WCE2fV="-Xu&U.`\/:Q\\MU#i=Mb[N^K6]A2c?)XgkG!WW*>A
3k:AS-\6`GJhGT4aPdMVW.bF@L`N"+rO=jmlV_E!:cRHLZW5>r.Pen&E.lNkT1KXoH?A=>kK
+FY_=Nbgd%st>j;bl6o6$/1I8dL2L5N>-oN7XJYH3W8oSoJ=\@!*a4mZ4g^Zt"BQkWM6B67A
02-FX!`%RkNYML!f\.H14uSb4$CeP2\RWqf+9NgX;SjN)ji]AS$LCgV''e9lNhRK[Lk+[`fj.
0Pu(.cQ\2nhr?R)M_+#4DhMdfZcp(#Mi%_0bAg'n^XC4q8hs?L<"/GN]ANUN^MoH;gb>imkr[
_S*DG!GC;T-R)Xal[+!4p*6nWnSI*g=lpf&Cf8k-QiBsG$$.BAS"nLB&c!(ga[M=3m]AP@HFL
O5%-n&X@0\*Fmn&@`6Dp"qC>QFPp=gWHV:=AL8m6sh]Ack.0<W]A#l1q*/V*qEk>MDQ+7M!W:'
u_@VrC%`S$*jJ#bcZ(?N83fiEqg@)q$LhY8k\K:kC(=K&e[_2So7a<N%dgT0bDPVQO&.JBT3
?Cs5Q7qgu&01+Q_m]A1!sgnH=SUTcfE%ikd]AC&7=G]AX"6Hr17N.Q335d5$HT9DY(aCXgV,c'<
:67^c%#a_sW$85,(81ro'9Z5jT<u`eq@lhX(@Fh;0"i%:-$Jh=`3\4lV"qp4J,=X!?]A$n6T>
(=8rd*UId5@2-.*jS!Qa\L=7)H8fk$UBFR5!e;ai=!7c[tq<;i8:+Tt$*8;n-qYJdo3+dS<$
c7i*48J!$^5jJW!HR]A!*_:Ykg6Id$luYA?3$0["8LIjP)t@UcXF9U_3bLBRJ)i_p4%Dl7FFG
AF8oAaCE;:TLj2-k$n8&RmJHi708;:/X>9!a,,H'4i:;,P5ntI(rL2VqGm=uWsBoGI_D&^pq
5333JHIL3lhXC/J/dd`+4GV:2ACju@Wpj>\:m[>`HX"4Koi`q@/PenE44Xa\XkUJcpF[:Ci(
7V09hCcY)$9G?RT362po;>T);0"mVX*KY=oK;CS4m;IP'd1oEg+55ku8=JgH5?MPDt\[d0mA
f*g&%o'R)U(A(oe$r;KLV3n^pj;e'_$Su<^S&!_MA^[C%(/AlNREBK,=fba)RI=NZu<qC$L2
ur]A0\CmG'P[mjL&hk]AFCON@YimZd47fTra-sXCSrsjP-'ignRFUsar'>a"'7I4DRQ@HQQ+m/
@,-jEjTZlZ.$Ni^H\\\_kfE]AlGt<_Pa!:pFr6_,FU@lb\d"iA(l)am`\#_gZp7^@Z1/Gh<9%
Ld^@8=_@JK>.LYV*^d\kGPfr/XZX)p28"9'm`_50.6[77SVTO'kqJ(%cOt;J9NZTp?i;TOD1
uG/%2\j#W30U9Em*YN,))1=j5FCXDm"nWj4P]Aik;/seRgZ]AohbV'm#S;K%/@BG?Jo*JRq=gY
9ZdCbk2pl@)p%'kq?@b4J*4jsj0FLREqjWd`Xs'gk/oRcD=9HjA]Am7N*ba7H.i^34f\NLoL(
P[oXVf<n+=Y"%Dpo$+"9tA`t7-4';L@6Xh^/\8bRDu"dX&GR<&8bB_*gZ\3EPLdr_MoQsO6U
f-2s`Y.N1UBCRBt.RANg_aoQ0n=b8/oblO2`PZ2/(kqs/M^OF7-N9[FZ;l=$H(7@63#$Zc=W
n)S<GG;Sehfo1.Qn>,sEn7T;_MJXZQ&VATmk]AuPNhaH(D$[ufF]AtI\:\&C#oBAK$qb0C:N(<
ODP)6drA^)Cf9*:b/89dgd"!S:HEpT!L#Wlh3bE&YKB("S]AG_&ot2&r5FU*39gL>ef%m6JS!
2F1E`WW5k$oF5*\6'Ak,hAk=)oQ>oTtqGD[='#!9KJ<abXkS.&[=IAIr1XWqp\bBF#<HKCn?
YnTJdcm8+n(P8k.K6&N]ANX%R,fm._@q.u^h#fl=l.04:>Bj8Uf?)U<X]A=6<89nIq$!-ouNB,
'T3et(g"2P'=9I'_M3:QV@0Va6EJ5g<*`Oe)O#FIhXVHF\'?J-W1;clE8b`%L@.D5em[.f\I
8X&BaPY#ZA57#QeB7OcL!N<oB:K[N?L_FVrnR?%X3T14_1n#AIGSdY17#1P"fEY>t<S9Y3Kt
r-HA&LQ^&H1]A'j!n=n%$""\bKXF*WkE:-:/eQjJ>?D]A@GBbaq3.UVLemgT*SShFT1tBUX5u2
p1_=AU?&V?RIg=dtbsLp20%:$c=;LW*KC[#EU_+-u26P5$m+,_>]AcF%gRd_95Z$<C!ef-mIU
RabKgBf[TQ]Afq8W\NjP4[*t+:FN'p'HSNT/(1kf;+PW++G$[!pJBY`lh;:-he3UiIm-fT1V!
'`gVM\aMd=G@FN(ld`G$2:VG<P_Q>4rW.iGrP,6oP6CPW+*?C5Rh5!/;<"q[4N'<K8VeYtMB
Ohf:LoNt(:elJ-5)uN,',]A?ufhP%Q]AQ]AURF]A@*\C1E.S,Fm>!S(ePj_%E[;Ak"#*_G\m*9mi
.4?F^=ipT11!AY>a`Ds&X>;rYLp4.poZGLSrgj$rQ-U$d?8+MbVO&!m+_n1hF=4.f$aRfEtS
5\@JHRICsgHj!PI(&<-/qaJd1LDPnV>DTUG;jj=O)$/&a8VkDgm-m9%%S+g6O#l#Ig]A5^4dM
.SPQk/i$FYXoop4#pB3gE^JD27&BT-O_$>]Aj33#,tc]A(L+adT^sjEF&n?ZV^qHNsb,H'"(>p
YtNJ!C,Fu&L+l5K&r1SH)-_KnRj8ZJO2F^@[Gk'EsuVk>5M(Ork7?S]AmM,FZRdZK@N>_R<oA
!oL,DUT[?8#'Jh*B([lt1q'GK6;'ra<cPQ&Pd":pG,oDt+1+[@/m.3U%bZR<.gDG"%O.hF]A@
5)YTFNlZKc8.\d*ek!aZS;7ITt*KbQ<7CYPC"gXuZXpH84'[R&=?4IHWQ0_+U,(4!>7eIBkJ
o=mIM]AHZe1o*8/??R00/*S"G(A.oW</.<+enes_7Xhe!JQmJ5$'92o8rP+HPdT?aSLJ!"E@1
?qR^kA%KF"`/h,XmB7IZ$)ss.d99JG!X]A\Y<T)eEDk0Oh;]A?@]AKRF]AgF@9A]AqtG1/D^>+cQA
ZthTj09#4o^]A(OY]A"Hj^=/?UqO[8TWX%Z4Z*GAC#X(KIS6O/)\&LJkSgT3dJG9[\0n]AC;1`9
i31Ki:`#Pm_<d@TW^H#IdNmNqY,[(;BNmd>mYOrrDP4aVI`CO>IbuuPDKQTdHSRC%'Q[&pp#
s?+X$6GaMjA\#Q%Q\iq7'A$ZN?=LG?=O`;rfaKOh5&<s-#).SM(_Jg&,501_ENGJS_lEVMp&
LMU.MkTFYD9fA[j`[aD-;:SKhRfW#D+\t$6uktZZ3N,5M]A9.a'G;;DV:T#Z51lWESq04Zu_m
P_OHqRJoAn!;/Z2:-qtm[4ST'#5fA:M76]Ajj68^@\$,[2Rnbp:&"n_$,JX<F'Il0?QZVB\==
,tp>9el@I(8o8%YFs8uTTr[C`H`OE[jb6snfaAa%uF?_9WG-ioF>">-@g&5@5%9iu`%'2;5]A
\R)Kk#A3qPm=O;]ANQ>ncO98[jb/]Am)'9a2lk;ueSiN;S+4XWd:3ns]AVGT]AX"L:q:WF(PdiVh
0=2QM^@.)n7cM]A8q-pn\P6k2tl'@_r^8I-2^lckFX&cJ!QM.P^R_0j1Ub$M:'4W)8hsH8Io9
_Tu>%L@A@:8P]A=#P"Il/N>e`'R;_1JIVsi:SGol/,GEn4ul)_h-D18h^Y=07?lCdP$'breU)
?7>37VdFl\OtI3<-(jj4oIglMJ<+D&bX'0Ql'&9m$/Y_8,H?a48im$ad\HtJZ\kM+q9T_]A"2
W:77_T:^8;#G9$AV-!P@NNc.DE-q"cOnV@#F4DTT-[b86Wg5+&FFe"7Hk?.t@d\6E64=X[-]A
r4rsHh#Xb%<nmaqSV('/7?GX2g$dbGRSi1--e1@9?BL6=D6a2TVX<3oHtb*q+@H?no.-3P?H
ollltT`h,F`=mq^$n-^(Mu!?%FEni9kSF9Un7$c0B0Frn*?'i]A'S]AR)9r]A<q]Ao/9=/H>"`..
4:<E@W>#:[4Mq)h';l8qRbp,"J_+m3?fnIc'c(\$KEQ:-J%]A"'"?PA0C0j@Jpmer"Fo7k0#0
X;pQ*=;-\S#]A1^r$@0EU<<95*p,D;`-593,K:e%QeppQ`.$af-nJU[:95\d_.pB1n7FKOSnZ
ii(Meq(^TWPd/Q;CAV4Gdkhu"l9KX+%R1:,T=GAO."*h4I]Af//pfO#-YmVXX9L#9%4pn`5!X
,CQj&2EY8"8>f76Ve(_?;f:!=c//C&jkp6mBV"0X+Y/?aV5hE,;]A,5gr-:MKnm.T*\3"UdA!
2E<2q<!]A;%Fk*0RI"jJ*_YQ?H&K(j8D2,bu>Ze0od`aD(M'1*S?6#df$%5%3lC1a0T`_\O8C
OI\rnP^riCO46\nW65]AXVp2:$+^S1Mq9>/AQhu;p(/1\1&ik?.&7_g`*BSH6^;O/U]A`iT:2l
@(M"F>UmI*^\]ABkg<?ED#3CbA]Apag=A:]Ar]AkZL9Dm8(05oX\51[E<Y/h/+$mI/*G_q*6''.Y
T))p6EpPk<b6F-o:S_t;m[.D8H*n29GU'g:AQ7P-ON=@15MoPr)mJ5k3TDfnA7%2@$0O;rY)
8-hkj#@S^5,j-HCIadR1K4YVgP4b8K(ZkGC\RKA7M"ttfFo'P6:A.D,n:r0mLAQNr([fRBjd
<AqqsJW'7Qs6@S>]A\b1]A-7C?n"?1Q,WJ>@=?4k:Wi,b\!CaM2sOla=eNR\l#A9IlNG(k&Vb)
;Q7[)P,p-f'n@A9SeNDm;n<VA[%KHD,I:?Lm(.q*9S.BoCT22H(@+Y%_7-j&$r0D?CZhGhc2
UbiG&eimle!<N/Y45gkkkY!So`fL;V=3j/qsY\64j4HQ<WB]AqHkYlcn*PIKJhIj_d>s.g_4U
bePE*">#ueC8Tn!*QNSNQd58MMH>ORHY3kZp@\o).?m^[-0ZkL!_@0F%HMk9tJM2gnLDN'Y<
JuA5,;3b&_%%WILg49ZtQMI=Q[!t5E?/6cNZkGZTZ-KnSqK!9_?R4L]Apfm"^^\F2fZQn,lCR
=V<c:$q@Y0"=?[QpfZSb_m3`ddbdgTDlKIb("g<9B:B[1iQDeWo+cEsA)+Z#O9.g*bD[ef*`
\V^5J,fm$dMDk>9cQj%,r]A%`q*F,Bg>Xj00A.Su+/FN">ukb[digFFlX@/XQ3$`_%-S2J\i@
'Bl+.XKrpW)n5_KJBG8\R^X"bIH!`*(%D@7`sjCqM$9a'Elr\:E5ki@j&3Ba:L1e"UjTs9pc
&.mOL6TnSG6QC7(,Y"PY-,#`@ZL&XZ-CM14:YGCDkb]A0MlYC3G@FB(^q)[Y(FT>X+aK<79N)
qFK82aIi%>*Zd.BJ=0'F,qADMBrI=H9L0Mo"U0H?IK9Vl/buKKAP093A[NbF0k"rL^c=rS=&
WSYpeQ]A(,rpX':bc)<d\?Rao5il[)h<Jh?0XrD8<&Pf+J,?PJE,B>J]A!pI@.jqned-ld0n>Z
@Bc$_gV'pYu%[&d@IE[t7j;WMeFOMiZ7s9Ou@R`ItcX,E`]Aa%hS.]APd-h4g4A/$"EG,h8+Vc
F#R5[N5'`G9N%/+[2#B0"]ANt3-gWGh%jui.4=/a&%g4hk#;L3"D;HBmU:bP*8Oj!DXinA/k-
>ZG&!<V2[JIJB-5U3KbB)cdgf.:m9b%.>VU9Jp%\%"Mar"CEcCe,.#qBUJW0@4oM2eL=FR#4
p6G,Oq^8uJ(U$)4R8K5q\Dll8`0#:E$T2YXl>]AC?H!2fYhK.dNcq?9"HagT,d4o&.QJ5_E*S
T'G/#m</CURocK-5jMado!l9>:':I6mZsl@qm^U'[mW.>aNTgD:[d[]AR6-hF$IR]A_-3p(4#B
WZ\t/>WIk&fSaoLZFPGp;\b_"lD\R6(nO5AP=<UEsN6Bc9]A'681696ck+H)lV)^+[Fa<k$NT
X+^oeLa,cZse]A;@<-e9>bZJtr\D)4LU=puQL9d'7`m*%@^uj5'/Xs^V9`bl;7ar-Qj(q*ZGL
It.Zu*VLc[Vt5FA1U#XU%JY`W[fi#*`c*T-_j=A(\ojG4qj;EC75d:3RsYT<_=/T<^)#,CaH
GMCgJO>KEu'$N=/^0<t&hVWZug3DsG^YUoGUo>;6(2RB.I.PobG32\f+5He>)t0pC%(f6akL
C3e^n5M7M<84E#HL`+@@'F?`H!"X\Jb$7*HAcT[c^`anJ%BC3U1Ma4T;*$ZK]At*HtTePBW?]A
TaD;u*q"aTT2/VP\rX7dUGQ(]AQqtfMH#k.o1A$>eCf@a9oGI@D`@o9Y(pMSI5H9B@KZg]AY?i
8KMeYK%H-df&="/"!sfd@Fi#jn-MR#I\@YmO^`t$]AH0GIJE?@S$Lf0a$O(X_XYCu^@uPh%K'
0ZqoW>eeCTK;D]A+P9I8PoVg7sME=b"'hG4X5`k<ij+F[n[UhGT8*/?UHU9Qr\qQ<Koc=4aDF
>`4*$4J3b.`7.cJU.%;-M5<3KNPL,1'pF@iWIm0j6m'^!go)&PQ@9mP[d5#5F@Yiso^p8`IH
?MH)9j%?^cBbrXAR$@:m2r:HAR7[k3JA4p-<u&BE!.5+&@N<Gj#!(YeV=k.@&Ssn)?^cAGM-
^NZ1.RHnEHIfR)B'14Q@ZmJMR!,(&*$5;hM:6V7KL]AS3?FKceEs.L&-59sY=G/n&Ph0(-(Ep
Y<Wa7&Uob^TL2RUg%-c^)_n;=?r6bR$6,<5"7O4F8aC.^V*=>6+mXD[Vb@Fa-Y<g%E&peoRY
?X<`jF$@i:HsH#-S=7.kG)?QIc^BFnN/5X?9<8@J8o(UW%O-TO.kW>N'uhPg%`dRZ=b2_lUD
=;aB3'/Z`Fd?Bp&M=[jhCY-5`JCSYuHO4D,r(P'.'W+rain6gUFLbj#Gc,%R8T30;mRDb2d<
/d0jO$\g?>\:HBqHF&<t8;JN`9+,?`Vdg<Mc9M:=G6?\]A/Y;nZ[dTplQ]A`1/GJ7fT_fVe/F#
Lk.f4?pU4h=5Fa7[F-CUB"b]A(i$L<K;0h=V&D9a:GG#7ahY/+A$8M'WXV\pWQReO:gK=fVK@
bW^p8eM$uKo>%lQ&5FUcaM+77/&StTj,;V,d3C;G=/gFq0Pa/"`+J2kOGIDM[]A)^#Y/.E%bN
hf3RR:?9CJ!;-R![_K]A5k7:6K`F)3Lrai(tU]A=J">Mn0Q4:?[%t-_>_o,c5^Z:%Kp9kF:*oh
Ts,IYCBG`4ZGq7AqH6et?k9M3f6\<%)b,359-EFX(E]Ah%7Z/NIX]A4Y_3<OP2P<,jk4Clp2a-
kfLhYY\4h!'RAN_be<8qs1l/DjIji^Ffn+%a5nCk?t)cJ,D\)b=nqc#U;'Y_>I_=1b.@o<or
.b,)^=Q^CE0Z`4f!$QH0_HX;DY#HXcqcI5le^_et4p`$&G%0dD'g3$;0GcTa%8=G0Eg")n%d
?LK9.`knON*IB3Im,W:b$:3;<JYopEB;q6?/PO_A9bD0gnNPEm:Rns&^kr8$nKQsRr;c?6dL
V`:,Vo5^&L)HrefQslWSH=RNf"I8*hWU[`bg"'#UWc4Q._Vq"u_oX@jkcpWY"C$O76/gi**D
8$ThDqG%#dSZdGj(LU<p7?g[-*W;eID"1]AG]AZn,[lPC=j^VY&Q$PEuJOFe0n)6G-J.+R'cD>
UfkG:V(-_B<Kr#H,2Q:f%4u3_rW.1o;%RGV?C9)8IA#^Ze@Sk91;E?,^n+05:AOCjq9>]AdJ`
`i^(O/Uj*UnAh!AJPoPg0W(^B!!a%'L4e.B&dQ>QNFBmXGmM\_L#T0.kfiH[ue*6D2lGTkI`
3AII'6UJ"`gLiqQkV?F)A7d>2T^D=rpV--<epA:b_i2RoK=PAgu!p2A2?DBnp!Rk5oFN=s,N
k:*Is=,HZ"/7T`JK3S,nm2-Fgghor'CTDaM&brG<Eq62[h53qqu0Wf3=Lq-5miM$0a2c-_+W
1g;5s6+]A/PU5?Si'6,XS5PH+.)!m8"Kb_O)hn#_`LPf']A(BF#7/11*':F87R;t&`B!IX/Gcj
O\Z]A<Z5gG_Z#`9__c=RU]APcCqV^ZE\uYRPE>(Kb.p<s4/I_d-eAo)^2DAlk.B8uO]AG1sUe^q
2p\FW#/EqBm=6+"j%h>`IbI[`2Wu\Q1%![6(kAG3=AgO2Ea+AqUG%F6L`aSQ_324Nk[C4ofG
<5*XQ['fnBV'Em^Z4VI/-W_\8k+i\E[H/IS$ue3Y3"d<MYg8p9@iI]A_/*:GQ9-S&;:47V:Gb
g2Cj8fX$>3es'E8%g]A1Co2DG)Tcl?*`?]A@P#"BACs";&/,(T`[ureae5iFHnp05nd3Yg=r@r
:-uc3X@Vu`-+>VD';GWkJ]A)"Caf00Z(;bW9jna0l4UqNPW<]ALs?j5mP9qf-I'"pW%'Jq[MT;
dk%PLfJi=Ph-s;rgf]A\NeV7TV5_-O//KKi)5A^msE7^4(MWfCRH`mn#?\t^G"a/%.9Z<n5F^
GI5:A2SM2>H,q7LL%F+6m"NFTU_&=M+F,f(R[?jfI0i9g"S$GkEJA:"Kg8!!kn,Ne8lbKRPA
O0cXIsu6E%]AV0FLu^\g)8_EV?2d$l)]AU2*J5[`I^@V#iVQsAu[.QoH5G&\EG>oSNE_"YODXb
E0j;KGNH58MKVs*;@Imil?B`h5MNQY#r("F/N/lOb*7[`k6njhtZ]Anrlm-iXO&%P+8dL[W=H
WpbJoa2<0jqV^'&knBD-7S0LdbJL(I/,!-g&&ZKnK?qLk+ZlDgKU-sm5I@#NG?U1@e"u!Hp^
DJhU)ucN%Wt-Br_9f=@eosjWE+p;]A=@A2A$J77,]A-"L$<HXp*:,'Kodf9=[_A]AI:EafaaXEt
JZ!dO3lc^mMZ?*0_h-epq]AZ.QQ%dni+<NfgX,fUiU?]A=7Z1U1lY'4jKmC=<4'4-ADdHnGcDd
ierWY`N!>>P$/SF"FoQN.Y?!fp77s&O"]Afp:a-$ag8'BH&^)*D->UKXV5/Z'C26+R>5g)UIT
BL!fmR*UPt7AQM,deS[33nK+gSbGEba\f\-Kf#ZUedE*HN]A]AgD'nnN52U!c"gR`%J4\Aoc*c
*G`t`I__MX1G>.6OMZkY*d75b)'HfL)7fGX&d<Rpa9GsX9O+4mpjX'?=k9,5'E;gk24EKdU=
YsQ*^'1F6ZAlOS_EFPn'-1Hcaj&fqd@+i'LO:[qEH->'G?JT0mrRq)7UU;mK<$Va-AuM4c$I
o-5PU__lZ:FG"qF+IQI""bOeU;\oIJ**DTU`TICW6BTjQ1j!D[fL6+d_Fb1hPG[g]AZ.c(T`3
Y"8ce5-9/+o\<i')N7>NYeVnQ3[d]AIdVIi[1QsIEcS4WjNcj-I(%QsbUD,-f2_]AFh4ToiV_B
J;%a=]ALL%l+:NR0dVrZ^i6Vf/DB>(uf&i$f"bhYHD-32iR#G3ObXk+%*7>5'9#\'a:)JA'eA
2Tr?/^X1h#AU.KTBj?'2lk\T0"1*GO?;@To>nFnSNWZ[ARKXIf5*&jnaEb*B=RJ"0nB-@"Cr
,kVViHQ$:]AM"VYd+F,esCHc']AF2^'LFBXR)3^R7qod]AYDQYC\X\!<NWr7!.$Ucf:Ef2XY!<G
jaN5OFFQBtrbsBb.flEf=;HT/-]A,Y/lnZ4n@;8oG*)TI7t(&**$.DBRg[-&@NSXI(Ca6E,Me
i3N3bcHBI68ibb\h[-&<;E;qTeN+d'm5g,"+nAK:&+GEkJ:':n]A);4(-FNTpl`GK=:XBG7Ru
+AR/$oXaVGmY;eqn1PP\$+_3_qH*CNe?BDcIAnN@mQ3.1E3h_c+XbfUh=#V(p5SkYSf`M70[
+ma,JeW?Wfbsr`E_GjDj2(^c!jFVXhl:WI4OLFB?Zb5,L8[t%E;S9,"^+Aq:M5>#[JIM7O$_
7Cik!s*(&j_d*"RtKdi*D-#<[QMZVE%b?ltYM6.*\r%jHX!@]AH!i?M4LE:)8K]A0(EkMqf0jg
ZnTQa/N2AO4org,frV,NkN^MAa&f$!3]Afg)J2=roZC@p:T$P]A2U+2@5@0uiRfR+6VtRLb#lM
>%oTf*8]AD^#gnJ[`P3#Xsng>'oED[Lm=.X9/*A#gO!,uf*J=QI#l6Bdoum&itVX'5T)a4V'S
J[Db_!'Kn8(8Vjc"4O_8ju"'"2)PA_OD$slPN,V3h"8%Yn6kS9l5g#:$gf^?-jV,c.ceo(V.
Fe4DWD_!h$mC^iq>+]A%2H,2Faks%N$ORD!"IEI`*S.eM2Pc>qHO>QgTaL.=9Y9%OOs6cSl2a
G*n\b+)=%?DsC;#_tQmes;Vqjj&M("'/KA)dm[8udp<rS?l;"cj`t+tL7mINrNr,rlY"i.hn
n@"6E%@NV/h&AiiBVM^p^`B?NXq;5'75Oj1HlpH?UBmhBLfXd_#'SF;4MC0YeA"=rXYb<e1g
ZeI%k[p^8m%Ue.5Nt9jY;$8_qr4Rc>r6(OVM!LPAgt`M8il($,5fC$QnaI=LsR]AW,p\O9&(N
dsj#OY4;b1$U]AqO6B^B3(i>-a(Bg2)DTh2RPZBW1Q<kHqXaeDCpFS/jr<$iAqY7l+R0:Y1a]A
cEU[H^]At,%+rK2BMsDruR!Q?oI00!YkG[WH,.`<p/.S7X_Jo%e"nt?<b5hE#@+/9i57WS?'l
7m0o8.6E,]AGLV`[$T__W-@2YHo+ljFZT.+j)k-`mj<^S^,NA2^Ke/i67%#hFT,c,"=Jc?am5
Y"nHdR!8M]A3BK,ldpu,$KK4Qb:^oJN"n&TX2:TjT7;m3[o\r]A+(p+Gcu0au<%;*\KmfL.sY^
K!#dj[4.uSEjKQV]A.bi\`5bqXbW3f\H3Z?9\/5l0X&<fa#HLJ\AaTeeMn4I\r:T#NiE0_ED]A
:E*^e%lA9=bN4S,<1VqHA0F(o.g`erHKW$\``>ikT%3t@r;'k/KPQc'Ah`\!4E%Jfj1K.%FR
A]A\4EF!+QrflZ?H/(I3iN^^iilLTJKN+V6D*SM=/A5Ml^[dI<#/NQT5Xbhi"6HX5Qc(5N2B2
kr4-Hq9_H\-m^hCYHHos!6Z[Olrp`\o3f5cBqu$r5BabZ,.1rXSqAEFKsFq&%\/SPZIna.84
pa..#I5tc6/Fcq(\eP<2MTk[`e>KuHFds+,4(b+?A%fRYsH,h,qroW-9(\>%s0PmIIjsSCeS
18HUJelD8hLi`hYQm/gZFSQoRPDpnDXj1>p:2Vu>Q?chm[WDb8/H>m%I-+l33FX4bh@&;C:O
tr9^=T^RtCn@*-KRY<>E3,lFP9#Ok-Qrmr<5e>OqN6LsaAh<9\MSqP&IVoDej:[D<aQAk@$7
9Lol1^1b"Cc!0S%(9V$mqmJeh(j^hj_bn9]A$J7$d%O2UK]A2njR?9`E4%T_5\R0p*cEN97*%G
L^7M_i(-#nt%MJ5O+7$B#DrE69YBY,['f&-4=kGd:)Bki`A2ns!5ILm"@Y:)r5WH:')]AQ>Y0
(f,0h@Z9A8,C8URW`Umlcb%LW$A4nFfL3$3%V;nYi`1*lM0?f3)fRNc>`J,e/ILo[KmY-M;B
n_PkM+DQKf,[-L-;FkUDdTt+(BgWHp2o/_eY(US4=4;'=q^1sFY"G$B]A5$sW;d(1.i^uIRnh
7nl/8YC]A?ME;M3$91Yi-U'>-LTn;N<t:/%_L`dB+^@C,ubd2aWa0gLnbOjR`gI.)'M.SR+Zj
%]A=`h&lh]Ae)pfB4h45lC2@rN&0S9N6BHo;:X(5qClA!G?_Jp-fL,,`&@TScg%97[0Cqjsc7k
$ClJo?(eXfdtkg%0Cdo2rljK'BI%Ha,rU%>OBLD7iZ0)iKdJj-.+S34MT?6=;e=:!Am-@".T
o\&W+eT+b\UB1M/+NoPE5gC-'qf?g4:E4>#dR#<3aR9,OkVX9/F`jURN`S:*%%O(sjdU"l)c
.1mm\h1rh5A*5^=b;;<DdEif:ShB,DVdUu'h)G;gQqfX\L*g-?ts3=%'#Y\kZ\'u+$egSElL
$\G2gd$43]A&XDWg2-d':.T@@'))^"#,S=kCn7aB'2!,DJ-qiY.<M*lJ+q/e=@Y*,d*4@%oo'
"l9]A=+GBP9W2SsK5p`;N='`E&`5XB-S;E*TEJ`0'mn5_B1OOPI/K>2Acl8C\o-u`Ja;-oCSt
%7IEki-J_`MlU^t^dn$5(bhAUM$eeQeKg"T*s\"V7'sY/Zt9J,uI[_tacO:Q`0]AGL!h:p@ap
0rY5q2+T05eP;kg0;lX+>@$c-6En#C<s,7mhaqb7/)6ed]AbO\U&f[%M!@fj#]A*U@nJA?CbYF
rg']AJj_WJ7h3.l[A""VG.pIP.Bl:51I]A?2>!+e_Th;kfal[33pW@+f$iC!XIej;XNDc,?olu
m\Y.1Y9#AQMobUb]AK%k;d!>OXch>M=eaon[32?TV1MA?e(C.,[k2rJ"HI!$;[K122Ug98duj
E!lK6g9b)am:X:5c#TuVV;fR19%EInWY<>:>?_W/LJmA6SD=a;H1Y1M(u:W>p"hPpBm.dr&@
6s)SYlp6GU14t`(Fbi3\Ci+ih(Z8L3GbDL>5:*J;Rp[;(Mnt_D5=,q2M.6nSSj\2#BUkb!$1
<G,,F_Zp1`Q5=PPY7^0PWG]A%=,fU4XDa^)70lOaN=o\0G`]AQJJo'0.Up7eN>_i7<P9RnP4YZ
>.5jE1R$c2_WnC88G5m/Y>G[C(4&ulm*3m(lmqY5k+>0e.RRRV,TDnoeO54:VLhY$,jBE-0(
1^Y$i6np3'r*-NfaiU)\ZO(UFV<E32jQK$_)1@]A:hLQ*.fs%_,*gacAs1(GFIiI6WflZX`^?
SPdiCD#ltnp)/JD;=*mY3i<,rQpQ2O\?;,=05r;q&S)6_/7<[=b5U:_6R.#/$pb?`3;UBc(n
nQ1Z#Y2q`cW8pZhp80WO874A-CL(/G+:XSH),s/f3ekFL$[++(Q^4FeMDA"+ar;;AS/0`D@W
*XtF8-<4MbK$obV25cW<1U2#BW:3KNnepI/TcLbVAkXu1enD"MQau<7;>j1fjLUk5n#4l<[C
9as\k[%K9.UN`iiUNb<0:,*jCmii\p;Q3%$qg7hbViI;B".>4YCZ<@]AARf:91^/EW4qE%\e+
^\o*=iYX^?U2V^gdmB^f9(T3)pRXlPg?j%Xn;E!3O$KT$-RT$bF^mL`Mb^riO:MpWb[.1?CN
BV.Y)k9hb#,An-ECAKhKlF2L_=q!]A"+HP(l#7"?<-/KT@c5&9`L$U&^]A>l_Uc]AM8[Rb`>0<b
`j-ojafk=ac0Gb.gJ85giCpeY-^@NcfU)=^0it([Le5cqe*sCZa@G$()k^Rl:/l@HEh==mpB
G0S8XpY;i'5JE\1-dl!s+BJB@'K+6`8.#[[KZ.f4N*ZG7D\j9V;+F(TK!uhqYCqG<qo!XG.]A
u.'6;o+.8j"KNf7W@Pb!jCEUCULZZO*uo(6IPQL3*ElUe$^a,8+aHh^VMlo4Vbq2E]A=<@^!>
ZMBhgH3GHb'Y3gC<;VM`WHC;$3[:UFPpDHSk)\;gm7)mpKe#M3(GK#VPsOh3+pIOMpaCb-tr
D)7[-UiF,BaN=5Sl4sXIKHG.2>:gicY_*)R4!#cU!:HM$bUsMKTU@eQ06mC.DmlTZ0Ff)\rc
1T&#BnX;-LL-9E&4jK`2XE-KCN\0/^h4WIYuL!05m1m]AbI*m/O"8j.9,!YEkki&TgV:O:4kU
:5s$^Ce\c*uHW6,%.3("*<He_KPGWJd8@`djIe`$Oj"DnHjpON"/b6D)&db&RdA,C1('X)G3
Gp9[0U1J/Y!20C5UD_K&b5(;+B_W9F0L>MKRG_H&FKP&4I3qi=:'5eL$uPtqJt6TMk5HIKso
(=4_.LM.@/R1g+[(m,e#bhf2?Ml%g0H<YT9+t)sq&A*KW3%?eDI+9tZ=(//%ER:Sn9SVauYb
o5^kS,*?M_DCUg)ZeW.N6ltM\qrJR`NSDnAdTMu5fXo]A*9YEcGjkooI;:"]AdEW,E4&b9<1WN
A+.Wf5Z]AZM,TS69!O@O?0Jr-+b#`;8\VRm'P_:9e_VLLsR3gQ/jn_?_;@49^lkLCTm7&_EtN
EB$EeC2TZq^FdS/PSM:l3=StRP(H.&/=#S\60UTPM%aECjiF)!QKW.uKL.>1960X@6ma3UEH
S0B3T.D30<p:195=/.DE%G(jWb!JK48m9jMJ"&SIP$TsG>m<&$)Rlg&Q65%@fc-*rHVgdXtI
m4./54]ALasOhrUd(\e3h%mcS*hZ!dWW`0=Vf4F,Ac_:d%$XJH3[YgpHW$.[qYB)4':R9Eii<
i_/M!KA=i`2BGhAs12.No20fd_[,'"H+t>GQV7M#B3pXsV(7*74'2s@'JP%Y&?Y-MXRJ'>g"
$!;s"b$n\JhHOL>DrNfeDf'W1n%7&J'5[T;#GN0C5o&oFBfhO4h>=/&fVY4!]ATA5i8'D?MGD
.Lmqu]AVM\<:X+*?3\1si:4h]A>u)Rc)@!?d+78u0oVd6tiM";nKZL=`#M"U\LdPAq@uhBh_PX
=!F3T-CBBMlP"XoYJ8/1j)5h`?3`3Zpf34@Ai7=^MfP8b3i.J\DMbWmDB)T<u*F*j@#d2ok:
G,C<7$lS;C_G&m5FAV>5rPj5V@:95`AYoRK&gYV8Oc`>@*XQPD&V@u6b@%5K*dEIicqW0F`h
^N0u1Srmgj7QQ+@!O%%[<Gnft,Y'pfiQ"X)"#JgN?CHBbPkamV1"Q)8jOX9qN/SVlASNb,CM
6&%%6X5s)L`<6-+d+jP&_JM1*e/4)j,)nDFq;I,_DD"!Nb:HO&3pE"(mH!#8"E5[ekkKWTI"
Y=U$>SYr83RpWL;ir>9)q\kN-;-s'KG_+mZQfgsl7WOFWAMSsG0%k$(QoMS75/B&^g4g3]A&,
o(_>nnK)CZ$6"R^!=T=?b+K1`9jS11`,c!`4@NM036L,\_3ji[$o%>l[p0h%J/@O)JO[")A;
HS#l(:("*3$=0C]A%[oBURg9'3RkDcd"*:7n""CFf*BOq^LEWUg8`f%#`4@bf!sjGi_X1/hLU
)f=Bpo'FKfL]A43+)#+&E%*F)!("j+8mC?n(GdTW8>Q>WI"0ioVps`tD(s4NYN+R*%GsT^AAq
Bgbn1T$ZE:`l\*SB3;oN!aBOFc=Gd[)]A>8Z'\1DG#_&r+3'<ijITU5FfS[?V^+rB-&t[F.6(
pX!?8dlG47Z"i[..L8h[:M:<Db'!O!ub!1B'"8S:MfDXodla/r=70Ur@G/`tJ<(kMrm\r$VD
%nk&YAi\'6mDAF"8Tr>S7TYLmL#GmV7ejTYdo-BhD,D!^LmW2_pQX2/irAEhC-O69C/FCKpm
D:A\&XT,gq`sC\O.t,8q2,B7+e(2tJGSg%&66fkmZqp3ank,ifW!X'7#Oim&5Gp#)08P17'a
/(Y?jh#<0T3,$R^-/c/AR58j`*5>4NM.*-h0%a_nA#o-Y4@6<"A>=_$"2U/DB@pQQ7Y=S>*#
"D'9f.ualG'maUYP<YBYN"JE<]Am]A\ZF?i#pX>1$I!9+<\EVkW;Y('']AXg>5:?d\n1IPRi,Gt
T/jP`^T$5)HRcM^Mc.e*sdj%`$:QJX`H[Vt3\/S@,heJ$no8^8j#1j"m/+jse3aAr#ccLGs;
9V-6//79GocL(QhnPp71$SC@MZ)ER0,:_;f91%\p-+m0"CK<TcgT8mK(=L8TH*OHf!b6R<Ui
Jb4MS4$;tO7(]A"SF^_4=T;eAiJfi":fKn`n6I8N"]At%<)4""fKLoahF[KC-71YNl@7q^CBm%
KdsraY!kqlKJ!h.Ut2Md1=uk$YJB]A;;'ff*P49>q=%>/Gh6!qr*#ha(^;GLkc9i)U]A0q6V_A
T"`=lP\"]A?Pq,C4$lbmoR"Y%S2n%I$Fk*Lg[*^D0u'<^iM8?*m7-g.uWl$G>C',@,%(JA6AG
J"3Z*pei=$A23BaJ*<@#lD2c+fn"A1/&XL#eU>)5i$g=DtZB@Z\+./GmQScZWQ:/mGq.X6FZ
K2=WTY#K[.Za"(.gUHlBu'I::@&ZK^-EhZlr;+<^hpa4&0LO1&ho@o5kT\`b0@>=g0<i(2YT
(0F'=Qs;<h7?+KN!uRn)YScP7DS;c.ZsO+:L9oR>c7<^/k/dX]A@'lgGg[XZ`(Sa%gr<4>K%H
qA6F<Nl`<ks!jif\s;sIs-1clft/h2&3eoePD3k6kD&f-OPYDh2#QEJrJNTBrcl@t`$G_<V[
Tphqf8Kc*[msGiU5_3,6k\B17-p>q"o/g?U,5dc1;F^.k[tN.WFd4V^#+_e!RW=.,V9=-(e\
i$hin(q^GZ4S53>5!Pa2/N]AgMIJlg/:aB*F?(FQ";B,Y,Dl4,b!(P!`7:"Eqb;LcH*A5:rd@
KMD-@O9Bd.I+7k32(skTpM#*A@-DXb`*'9Io0gF_4_*]Ae;q%N6N$iKD?1supf91'q?>l:M_j
6(+$.HnIiBe$2fS>?"^9euS5+C*=m>5/`Lh@_V(g++PdEoN]A+X.I:'*(OP_tdZ^6"3&Vl8lm
A:)=M^Ij_DXe?YO?OKnGDalk7]A=kr]AVr2p/F@6+6mL#)dJYfo->&_l#%M_A<Hdc;#+(JG"j:
FMI^Nu$P@%F^7N'/['hron:Pq+g.3L)OHEFtV6R<W-G1A-GuQlcLjNe'(iI&Y(,9"dF-Zb_%
4a"0eHC'3\$19"Q"dM&/'C8:U(O*NC0JGBfZ`:XttjRPCk2iF8dFdacj^dPoaPi]Agt*2!ntB
8I]AEX(M+0\q$o$G+MGmY_$p]A'M?+_E.:]Aca_tLqeVfi%m;VnrGVV%A5Q>Y94Ht\\O@1_j=QX
F.qI$Mf5o)5f6F`")a5=Iu`9Zq'?Z9K;-p>"n88q9tGn"=`6ZGLBi<lL$3o1ptZC5)'M_R&M
34n1\d:ml`r-e,_+WJkk8i2lRT$*ZV9U".mDne*PS&s]AfW,GUu;bB4+7i%U\S<.<@r]AW,K0*
beq>)Wt(mAu>Qllh'1N!h+M>jcW$>hZMC?c5TYC##qaT1'^)<^GHJc<Se4J_fbj`SES'.jYu
Z<NSA_7\0]AHjCTL-?P%k)-)4Ws^rCgOBWc7?R+A$=]A/$BL[j`+6fUdD^bj:"UC.B9"l35#*d
:U1e!9\e".<3B9>BD!8,-B_6TBLJp%IAo:7kkqP,VKT$0WVZYbD)#+pG/K-1%UhQ?%*Puj1s
0u7c^u$VLM!@:24sXB9jOZF[D)+*$FC`lg%WO0'`OKl[k:\d<oI6qBIChd[.D0_%0PC2E"I>
S,gW5(!UO.<k%Whq<i%b=[a2?]AWN\.L+ABmK_TT<^l`=R'gc/ZPNYXP]A_R:;XM^b*oenE7V^
*XL=Ltn2'JG4T-/SQd6@\(DhO?L^k-,s)j%)?3dj6ULqm$4RhH0\I^h5P@PaTE:(qI-S-I-E
8^kJ1*ZQ"V%,RQAEgR0(>mgL'cm_'k5C"eQlB(qbMgQFoc.QJ<XY2??Gp_m5Si*>XR]A=SkN)
#[caHa_MP\,neLE'1&kL/q3N""sr%D/h9"]A3U1h-<e?4YQf6(rd(Vab^n+g<m1e^URB!W>rW
BYXA^.F@LpC'@8*;QWdQ("F"';')r-iOig58)aXrgPrmY-#O/3oT_Rq9`FE-r@ir6r&gU8%#
MHQg=_QY(Cpfcf$3WW4m&2'KF^1:@$_c-mS"H80bnqi+1_+-*q1=Y2p,R4]AWjfrs4E8h>ZCD
InepbF7qM8"qu>Ncd[eYdK-GfV;nIN@3!J"M\dZ/)``4kL#I1#*_c?co7=5^(!CfuR2!&_b8
d46BEii"#D*79auRQq7UB4"1L:/ZZOndnl_ui$-lQrAJF6\8d"uC0?:V<d?RZCTMTm'L,$0,
pk'>bJ4AL#0?<$(EIlQm^ge>cO"?d]AF0)Z3=6NZlp6/**_8!N1XPCR(JU5]A*Z+WUGPnfZr,]A
=9LhfW=b;YFPnT8/j!iq(3i,4=5).k]Ae72dG`b#kB\4j#g0Y0e>ShGVNE[T46K&AtD$qYuUo
0^B#?$\<XHlP^CCk]A@<@)QPBL(kp6SmH`khKeK8D=IG^f_uM4L?A%Ul/%C)Z#URKDq!iZ&hd
ZI1UN_Kq6LV=aIts<aH8!&tWI*X\;bk$nQF[kA_'-$S=MVU82I:=<m&HICFlf2OV0(u?%iK*
3_>%lXs!\RcJiYTBI,YE2UokYV;]A:@O(MDOKZU%j+Co"Wi^ktf]Ab,-c+na8A]A8Bs4]A>,h#uA
TG0mj5<b?4o5NY"M?b:;Vr%^QSB8>(/!#%(J%\03(Zh__D<.'e-S?uJ%I8gm&'QL?Sk>&ae4
7l9R5:F?#X,8.H[@aXb4fZi48&m_;TVE4[!1'#oK$75*Oe`H?bc=dRJm&-2HTN:s9m$c20HC
)+DMUC)MiM"-CCd<f:+slVfLl.#,E-eG,e9ob!Nj!BqB/KJ8b'7!B'PK1O.R7]AigE^kj[;Ft
^@jWu?mYBlXqJk2nAt,=p!OmM2N3/3.1f02.rFlC(Nt62t*ZLMg#B)i[G5#EpJlp<F$pa$h`
PaUF/F_,pGme&HA[G!@-$[Dm>^]Af$q_k?Ko]A;V@g\7oSaX*J(*1%IX7mo:m*Jr%VVS':i'Tn
AiM'HtCl]AOb+BO.<RfVm1dk<m[`@V(pPo3G!n2(H[F<4)I)s,M5`(21plJ<=:<)&o9dICX82
4+BB,'EZ/'17;_/8Jr;9"VF>"G5))9N"e37^tbI!qNQGTnf6\_8o2cR-+ePC:-EdLjsVqWfW
(r7HsHB)YG6>gfEe7)]A)Z$CA]A_d_b]A:qPk1F4o688+aT3nm1Xa%&;_IDJ/GA+F6,%r$esNbp
\EbWalgDKcKRAK_QBbl=$^?C6*;i%DkVabZGE`-3ql!\?n#s.033pT3@!"3*g(C'JNN6KWau
kkT+tipk?,A.n%A/8@#UD!Y.ZjL(Y%/-b,!0?.Dg[Da5Z-H&a?4HP!D7;h>H@g>WrhC.>V!*
lin(LYlJ;=,u<]A*Be8'K#Li]AfY2KO7Q*\sDlM'd;LH=@lT(f<:>q1M34qlYiQa"jW/(F;qbs
M[%Z]AJ8h+s.=6pcVIQ@o?c@@b[Wo<=qQGaR4p53raC]A[$\\pi:M5i?:3_Q)ro0l)!H]ArO2p/
#PR6t^b^]A6([C4(8iX/t5VieX_#cK4/q#^e+Hieq?B2;H]A9^BP/.34Ec.aX(7M`:%%^*umTu
:c]A'<FV32n^8lCOV0G:F56T5:E\FQ@OBa_*7bc?^EQQ-h[:p`,G?a,brC)d6I4ABcdn[b-*'
QF+-m&HT"ICqUq-A]AfEjp^U$DQLmdS(@R$BsZttU5Ll2WY1qgkVI!:>=YRo"'^$27gl7DMDN
>ri3qQ$mpB_0XRpsGH@HWq'e#EL/k=B(O8BoO"T9\6$<PCJlKKu;R:3mrQS+m&DY+uN0Q0b"
1kHLA#I.#$JZ:hu94dA"J3^a^a@9'kS?(>%B4*?elfj,8>*Vrgf,)51Kt:D"G'o1a>&f(F;"
m3ImBcOjVYej;/$<R/XS5N'LTED3W"-N!N%]A_G4f9b<!LBF?k-\oj;W:`[Z=J\s8!=W=;*cG
GXO2ltX_-%j+l;$l+kcHuoN(`k(2iWJN_CK_e:<_OqEYD^>scGVdrfd166k@:mr!?C3*lg[3
h=:/n6.Ge[-(&+0P\oZJ42Zpst(;=$YpK353L?Zrb#'@u(:n:d).m4`cdR;Un,UJXeq&!9bh
lS=8jO"lYR0CAZ'ltA+aWB'Lf&hJ)aAY3[6n+F&!0phfIb@_\%Nt9Hs/<"85b./O\(0:6W"$
k43ndIL&lE57>WsrpA-t9pSB%Os?`QjL+,[(9S62]A>?c%j3,pdS%Au_V+QlXK-ImE9U-&ad6
^uoV,9paD;SO]AK;2&B[J;=9&AZm[4[lSFc*`n?W5]AW%l.\d!bOLC"#R<M>mu0THJbV/F]AP5%
/X6peI3]A]A!np,p5>IOD1QJZ6]AH4cXp3(1kMIfqbED-$<MhSdeusGWUT9luC<OYBD@iKqcl8N
iZe&:?WYq5gnr3F^0oF[a5]AT7@(@_!b"d(65+m/@hl:A)'M@gTE>iZ:ZDp6l\&a8%@MF!\om
[f2$:gapd@1<4<h$W7XmU\t!Yk5c<I+N3<>0GDj32e$#g7,n;6-oI5`>c:Ua5!n!`c#<A@G1
/i^L=mf?c&:X!rOTp`&Mr1,%8HFrWdL_+4oSAV@DE+8CV+C27j;m6Ei-FSSf0NVb&!Ve<N;K
KTC:hQ=[iM[t'O7KRc<c8<BiM2GNu3W&,c66O:o=V9[G^XVe!jTJLUu_@eA"d%E<P,!lo/6B
]A(W'k_1Bg%k$UqnCDE5(CMCnC-IDI:(TO*Tq\hkA`'=j"9epMG!'tIGW<^g^E/S5\pk+>T+R
QRuTQfk-bh8<.jOA'`a_0_N\l;p%^pM*!^'CBr?E=$N!)!r=4TL_]AWrIr`pm"r:$$dll+eI&
:ZMBLl2GeA;%C)3kgR@I>IB10W!KAY7S:,k>Plo'KTknPI-d6).@ZXiaN.*O'5fJm\9E;K>^
EkLLeM`md2"B(lD;,DQdG?DQ.n4\<tGm7(6Uhk53&njb8*fi0QP"BC$r,6]A:ssRZgXaracb]A
.p]AMCFVN,`_CA>_=NPEdL#5Ns(^:Y&8X$\&1u^HYZ3qGe),$oV@cnO>;U"I1LUCuWKn5fsYk
h=ko<Z`9hec.Y'Jco-W@Wp,ljOjo^in,mThKRQ*$+=m&tcP:d+nVh^LN=0_[dYOR<rI(*nMk
G'=o3sfhgFHDuB.c^hq(ulE;9d_FL9TiqbUZ>,[5hnEbLVU>Y&BK,IsZE_sQ@C4N%Q4)mM,f
Yo\9"pQN![/nk2#[&al%LfT^'>$d\(_GF4?pgAn"VD/j"q,EiNq'4O*td,re:T=E.G5X,'=Z
K51I((@6D;*BG7#;R)/#ZKN.)I:i4gd1\&@:k)ON2ZRV#s=]A9C9mHq3/'m:]Ajq>d>o'/_lVk
EjjSq<<_Z6T()#7E.':5M78?`[Bh7-kG?3V:/@6-Z+'$T'eF`8A3'&Rif)p[%Sj__gYMoPlP
J6V*Dgks(9l2F)f^;M_7Hp&fXlo*PBSrr5>WCY2mc]Aa6AW40LKpXH8\),l2]A9uDM8Nl*FB9F
&/XAS4@Zj3Z8;p4\hc4ip91JZBQ"(BCEIHPWM=N&qOaLR6O-<4LQ^XWomW_CF?dmqgTu4S0<
>&H;SqP)gHdX`D.HD06M<3DfE1nP<FQ\q(PCJ^*pI[!o5h*W<R/D_p0?=$EN\8Sg>OoFVQ1W
XH9NBDUZH]A,ks7nQ=WW!&OZf`(KObb(lo42OYK%f*uq:3H"6FE;SFV\`%hQ<h,r5U`\l)K,h
oqTE&TD_HJNM_4&rOkm5CJro($_ce>T;:'<9eUnoo7hY8i$G*IQi2MmSI?b?`\I!V3YH9neL
VBg6LJfYc%k$fU1on)CVdBZ[i'JY]A:ra;'I*_>0b3BKi2l4^h87]A)Fg;)j5hI$WO6_2UB7.F
gM'1nA(Ge0#)]AMkgXjGb;*tge7E8iaoj0G!UN*cCq;).$Hjc/CP>Z=n'5Cnt#Zk,ES.&'2U;
M["`!@lK\'qEIscCNsI\6^*s?Id:RB_YiHe+J&#QiYGnCnt3c1Q[t1#fubH-R50#A7VTGHJf
W-q>l6Y,q0<#-J-2$kak_@;%V[qp>]Ae9TVA!:4''CRQhA7<s)&VME&I(e\Y?6K>\\5^)rj`"
2Wdd0[O?Zk9"%`VL)hn,2S'jHA!-Rsn5Uorpt#".VPamtpK9_(KdodaiLp889[l@4jj-Z=5G
t13-'if+1bUG(#)OX"V^@5^`Au9LC9EhJAp%T\A"!rgbD_e9Yfs:9`,P.6\P!*\8l16I:ac4
]Ac!f_8ZY65F0uH&'VD[57G*/j1pj$OjSD1P^a>C,9IO5)$f^ZZ0k<Rm:o1`Io4Q-Q;-?Y&/6
7&^T]A(J&KVa-96/6;mmj=LUs)4#mCS6Q:'FFB@Z=8QF*opMLt/m(,RY0,hV4`q:O.0cf,;F\
"1Ks'k2PPP-T[+YB6s.+ARg4/EalS+Q$*EdKJ#j&Cf9P!c(iat]AihlC.?#):BuTZ^S+>Kguk
_o:WjiP6f(2&7NKY.PI/U*LWkr$g=-Tu001+.Bi'orljL"b:eUn[DKccb?51=g.2jR='6F#o
gNC%C]AkKN'>UB]AM%R8KNZkVDm+SU$Fn*dg$RtJZt=OuJ8?gg\]AcKhr15O1/(h!tP<7:q&>Ch
jI-0$Vj5n.d"4Yf$JA>NXCarbW#2VA@nq;tZ21=IOT1p@)Jn+KNWq<(16'HM(7pDJ%Jg,"*@
CDV:E\.Jo:+'l@LRd2Lb\[h'(jSq'1=XEhV,Y+b7JEIUi?9/\'L!7O$rK3p!'=T8e"S?p"9c
F$Y4]AhuVN(6TJbjinD1q9(I=]Aj7$#(Tq,P29XFIf.&RU8=qBo7h>NiYV5)Nu1Tm_d%@M_$M(
M66m,p@VOndfd[=eq"50P]A\mB'RZXJO(p'c!3.WtKn8ZckSN2gFQ?Wcj6q`bH[%?WG@XO5l#
FrRjgem!14tdo^,gQ92(!g'fh2U;f2l^XnmKT\-rIW66Af=X^VhM)$&,^;BrqV"PO+^AL`T4
"bqFSAgWbC__^4u&LHX73;6uCK=9%DZQj37ejP568s5J4igS4'WB+6"&E"j)OP<P,@Cu&Z:?
iOk.P[M4aNSi"@0E3NfQab3e?;#L(73,7gZ;5(Bc)Sh!]A$OW3_ck9VUip'l/=@?(nK25u\2C
\;qKCWag=G'9ks]AacJaoZ;l@lpH\fTY70SZ=q0B57p<%"#W]A!blA_.P1Y>.$15NoK%j*5NPA
ch("^A;eKOPg;Nd'he*]A*('6$f0.eY(:Wtd?-HD1p"6'fA=R#B9mGXsL?Z^;X9-&QUEeA2+H
[_d?><Hb-aViVP(cLRMj`)2&Gg`-,%nGCQ:'Unll\hnNL<HR2a"Yl4&HVl3C;QNi6nU>*F<,
l,`jN97i:p[NVh>38oaDiQtU"HiFj5G!\9V/i[de9bV*ll.aH^.TQGh!UI\oZCW/I2k[pU5g
$-\r9Ik]Ar*\!8D,]A[pV>G'O''kO^.6(f;4X;pl_d6`hLpuD#uhrL*N<7-0MiIg]A/XFDhSN]A2
,oJ<JBb[p&_@:@:aC,T$l]AS/PTNnF&OU:Z:n4e8SHJ>[F_k'n$fc6W4FR[`Ed(qN4r:[hPhc
Fl7_(UP5ZH"\$18L6A9+/GUmN3$Er"bo]AY0c2huj4)alZq[#pp7.'=\i2q>Hq`bdt+20(T=i
dm$NER_?VAri9FnnHDl*ed_1\\3c9%G&k08lU%Tb3lA-Jr2:'tqP4:5XI@Tg!b>hbP5pPP2O
b/e'$^qeARZmTLrD"ND3^>F&Kb8.!S2]AC=,u`K^oclPd8WrXhJGW,(m$o86l^]A*!2PQj<mV!
2_&l7P,=-\FS(-\RQb$+B4!(XXDWMZ>0,]A'a^<i5p=:i]A8"&_`oHgONt(dT#4rHp"6<+(i(M
W8+WM5WZO2_t`XK1RE2Ynagj_A9Eb="$\!sUG5+7Lm1.e#Eqc#oDNUnmd3&]A#g``Y0_D/s^Y
Q?d'kk*m*[p/,%hFd^p8HfggM?MZ$*h?Uc)j$3A'd\A^o>r2>Nl##%A[0S1U\9fD[(.1^QX4
_huB]AH_ura=f`5<Hi[ZS^\%8)#T[Z@*:q$L[`no<a37gPA/:Ahi'OMK:?`OC6m(%qL;t#&HY
Z3]A2ujN*p^;I63lGJ]A*,F(R0@1F/N@]Ag&8oFrD;!983aB;FQuA[!p,Y/<.=YQ/*A-'FCOrOI
e%a^\NnTWC!,)sR7UP?[B5"n0/kgnUAUhhdmTI.HtsX5$m!Z!->`;9OfHg1b'cUg1l59k$)s
h<Jt8ubB(+8CJb8Y,'LNj1Z;hr*6rR9oW?isQ\^u"fYuY<SE,2`&>1dUo^#b=0nl-7ghl=!0
&>'iZ(e$5/Yh:"\.Z8fBG@jE/_>]AUc&E,u;Ib+Ni[b9oT)7?F%jpjoW!>%e9S;@F1.bq:,]A>
Gn+i:"3%/e>#@%K*U7gM3<N/b\B4aE]A/QdCYVURG6BPEp;ebrO,g;8Y<4]A>tSI/SUcCb['&=
%f*KZq*Yb@#4Ql<LZ'okOHP7e9eQSQ;O0lt4eq6D8<Pan\M8rsmW)&#UFLlrCU;O&C.JtFGg
pVPb.%*#a4/I8,3\&:D=!DSS"f/6XA]A(3OSEPpbV&@=ffU%UQ.YkVo?-J1XQaLA3Pj7mEQLP
(<ooUn,JBS)>8^@>'#J\hU_Q:A/\''=N?X7KX!"r!>$i`Qg]A-l4iQUBb_+-"N6TDjp1S@4_R
6Xo1'*IF)$UjM*(]AYWTWT_e%CS+5d+oWSiY_s!MX?bqN_8Y=f<q7[YO^]A$lPGB;1cBlPXd%=
VXo\^C&2pYb"orS1Hm`aV/=#`@Np,bN??nHYRfjc)/!pM1JtT)PAK6\V(jV'IC$f>4']A6!^E
W]A9mkod[L9t%*R@IN_tg>E=RH5/A!&fR:5E+@onh)Rb#/8l#o(."Eua%8eXC@(7nSN<@:6_>
?ebNE]A#(c_JNZ@^L12>JaRr!$@2,8XI-XQ4\Y5f%fX>`2kQ;mALefY`a0dU#/$!8jOH.A)F8
8<`\m4fRB>)bQH;2PIZ[gV.S*aNq0dOpe?@AEF=*GQ(:?#SH8$B+p`?%h=Euq+q)G=RP:'5r
V=_pK.!.-U0-O.TER4sE4Lao7IWqR'A=SfOKd7M3SND25LI+W*mJ*bk,i-P$TFICcI7KqS>Z
&H9T[^snh0">f&56oM7*o$%o_2tGLW12E!(GT,Kl%/SfV36<Uc+L_pdf5(i1VPJPE/WGNT7<
87#iHTC8dc`rsBJ*R\-]ApVAg+g4-JI.QEsl0Fdd(75#NFT:\"j@*IC9Aera>insT2gp"Y^qL
"JBjP=!PJ.0BA+OMhTL,lAP[Go*P&H=H1rJm32DT48V+Cr)aA7:/oB)Fq#42bdbQ6.cco]AL3
(5p3_-8Vm=Wp@7Mn\=0;HnZB_HFZi:K?B_4IgDI4mp#u]AecbZM7I&RUQC%7-&8l&`-:Bt7(,
MY0Qs4cepW#JUDEMRUqco8h+Kc1`7-T@P=fKJ^ht"[grh[\.hl^ZOkRk@RGoUhOcHB1Yf&+.
apb[Ft0Bh`17LdO<U&8e8Z3\85F2bmB"JDZ03Z2n-?C\[o^R<g5pi@Bb0aZs-8O4tYUg3'jJ
"p!:UcD5%>rQ.T+(\G6rN-?aEf.HHFGGgUCI=OrOjS)1Z<PB8eoJ#UVr)DiXH?*P:qM"Y]A_T
CD`jk`,E9LQSgJ8EcnE#mEa;$rO-h&(;IH!/SfMXD:Shm,X`4l'%rXdQ!0S0O+-G'_=rtKR$
7'*NM.ZJ@N0`i^56a6g.%0FO;n#Ik<#e/d,]A7YJ3bU]A,HJ`^!'q*Wt79X9HttT9dC#BpOg?L
Y;EeJnF>VG_2R[[$7NSHED]AEu3/dVroG6S+M*qF1\i%':!YZ#AkQCie;?FSl<L7lH<a`js)%
7o\#)t?e'<F,:^FkWn1LRAeO%6P852Co#YqLGu=I.BCGb2%m4_u?5Z[hO3L%m@C;LP^c<KA$
CS'WmYa_R'rO:h%fi)#t@\l8H0n"I)3m'0C5d_F>96rU6;g#ciIhfL#A'd.bWY!?Hra+R..?
1(il;fZZkLPE"e.,RCqR[ko^=5sTfi`4MeFJE,tH,=_))e<X71r'Xl6%W0)1Yn9hN,@:`G&)
KfcmB%q731e>RP8nJNi7jaj+fmYAu[l46pE`\'E$PXd!3D=H%f_jCiTXgm%DVG/%`Y."pgjc
<(eh:Oe$d0Fsj/&DTM=`Df#\fUZINk'qjk5/_=&t&(qo!VqRST*L;^PjD'+XB$Y!/7!&0E)s
s'##H\H5b:5pF@"2gg(-B<CQ@:$)Bua@":1M.H8-osmZLNOo</YEH5oZfG$%&*B@H_!C'Qn@
EUu_IJa6&\'II<\<If<Xf3fMYa6P[C7@tLLqFXE2&V/,]Ab#R51`F_t5ZcL%"fU"9p4'!ZFl3
EX;'9\RbkD\EEV^L.#p7n5L/Z*VJDU%"qrPP<@H`SWNp7T(A"iffT($b.%]A\hc&AP<lr)n&R
Qr=M$l`qDFDt8F_QSh8egD'1hSX1SgpTY^GTVgPed@Jl!:c4PJ^1[V*be/W9MU:g.s&FJV$5
8Cr,KDQ_\X%ppVV2-An=m7iAe+Uo3F5=dn$]A60el7goYVl3'_*UO=kY5a#,FcT&FVPL4?NN(
,G_fK&W4VSWXu`\``#':%[['de]AFi\,G.,Ol*N\e=\TLAa,@(!-h(72q?\HOo0B"^eL\'>Fu
^Jp[h#e:P!YC&fYtnt(kU*)A8N&8L-Jn2%1M'V]AeA(5^"Ud8qSgT@ju$fp"371_j^9F2Q"dk
YB,)H:d@PA/L9HV39Hmenc8f,%iN<bdM%p&%U.B#=Rdnf3Ku?*G:j2Yn94Zp@D<gg$Oi("No
Vr;<lr<@OCk#.F`Th9B^&9*D=X:M#qV!3?]AJ6Tpgf3,Z=t\nlCQ&TLu:OQLq%<C,&7TTS"V.
IJ8<i\j$&J_)%aYc)`FZmUFqP/C<#ul^AJWee]AP[6UeR_Y_JJgl8GmA:'\7o8WVJj_!0hA_H
%Ia!"&lG3ZCj,Tr0Jb\TE'9LI+$;Ehs8d.O[>F8SULF^Gp7)TXa-JqMank&(*2L+qY3bX?D+
:YLN\+c>,'"`t`D;^W<7`/$sJa`dd>:Ub?JQ,QIuuKD3L?7Rdk4pW]AeP/KG!$QqKu7WBP=mg
nS47j1'S$=%ko(gpWinVn5oV)0[p-K"n1A.0rF0=>>(ChlB%0(Cd+k5^dZ<Cp!t&K%25[[/C
a$2\nWh*"Pk*hWK7fQ.]A^e5TtHe_6KV+%3!JCDmVMun+sgu5spq^$0LjUV7:25i4*k$QG]AEt
NRbF0_04kj&/(F_TNkfVd:a8r4HDTP-q-R18()uWnR49N$<6[r`5+GPI:`[S5-3)>KI^.i>q
c/)Qo/LYnp]AOjD/Ckt=14mD5)DI1QhHN]A#<jKi9dZLZI@?-ZZM?r!%obB]AN,Wf_W\c/gGLlq
=#t/%_g#>1LVm+T<$P1b;c'-G8T@%J$gb^P5&S13,TlOC1<NOaJ8;7qg=JqoqFdJIn_Qedi#
\LB]AO]AA#<Ce$-g"PT:4!1_T(1EUrP,uLOLRPX/OH;>XsDZpkJgrTZga,c.:K1>+30)H=;KeP
ieC[pP&S>'O?\<&7B1n+3(@-O=-j*f9:r2&d:TOr`+);SK*l$eqsEFs4mi]Ae"ObUUKa=UZRX
Uo?mS[Y7+u@$/GS^GQE>,20?QJREt/5j4Ko@"GCpqIDZjZ"`C+ajWI"DChkbIiuY$Tb.(m3s
S1,RR3M>C`5=fGRE0mBN#'I)')/R*`j-r3sjK&Wg#^NYj,'CP*8P[kjjR&n'>R[TOrTh[6e]A
U_;%*kmY^SsJ[KWWR5DA#p"uf_foGNcDWE-PZA)l6XU<[pPcI:lAuG<)QYm?Y+h4E?QTpp"X
/`H;U$1;`0%4I!&HFC9ft*tj-`bRl'>G3LN'u<K1DJn\dVM)&=>+,h$C)rWQf.).PJXMB#02
;\-70t5R#E(VFBb@VKkf!RNEN\OF)bI!ba;U#:UD=s:9]A<AZXI=C4sgJP8eVCAR.O649=4.p
#PT63+D]Aml9AP?Lg<qTl=F^c,[b$OsJGM2(\22&1eAc9]ASQ6uPH01mq4_,'0"'PKapRLZ409
4MbUId'saj%I8XRBN"\hlQR4"Iu<nSIZ9MkUu8.bEX,NRM[l^S6/2o@:\@.jCSJKqId1m%PZ
Pcam-"d_5a#T^:gKkTj!N)f8':W[nN\mTNBlD\t:8-))rqqE8o(en5(R2^RpMbB+.]A6k-G)`
#I?eQ-i<.YLG]AWX@:S?`1C,lhZ+X*_HUP.0C/!cBPO7IboLuQ^Llc-J6Ba@Xs\RF6N6AK-*-
PqpV;,UjIf[thqR\M)\#h)fX_JqN8`/U6)jk0?atN=NkMg8Gq1^bifiF^a_8b$:Uf%b=,3N4
9j&.mO]AY,HN:W?L^hND,m1RSEV6%hI``9"L1]AbX-d\t6mi>B[Kkd-8]ADR>*7N<O>;g"?D6V'
/L7OEOs9$/P)92!m4)-Me3<.Ip0!k.tas2j/1EZ`CQ4^f[@K;_=u`DG*&GMI%10g1n%jI3%:
G1&,i"iadJ)E\PG\#SN->LM/MRn,d9D'^U=3NkY%QZa2:,DfY$6loEg5G.-WC7S!-DSU!9i5
Vr)T35`2CZ7[*57!B/r8lGAr?XK;kreJCYH_#(er<[;]Aas-OHUN)N6/:]ArZ8!e*pI1O`(F64
5uQ08"7r5G+,.ds!9mGC+=!N2:c\sR3o$Adh!H2VD%H9I-r_YME-^2UV=Y@+]A+<PZ4NBfIhR
qc!nIJFkRb*AX.[JE\Q7WG%LOoUnmZ;D85+P:KH:@hl!<)8:0liVdb9841/*S7ViUe:ik%WH
4Y"&A6VidIgI@,aruE2#5>[QK4c:jm$LFW41d.2O>R;CmfQ)Fks!H/8A8mek*p*P(r-SSrg"
o/=nbQroJJts,)]AB#d6/R_2b?l\K*tUnBLQD9TZ)%Sl.d!7A<(2OP!./=@6DVhn6Fk0tHp'G
.RNt5WW5?(<tS/9qG>?I[Y;G"fLIE"k0,k1Y'2r;+W>DdP.6+?j^FXX[,n%&&[Lbo!s.1lJN
!)<A[ihj,S,>8M@_'*RfmN%[5M2GJ_%,&r2\58d6]A#d_9[-W1$kmrba;`TO;m._&F!bp*fs/
*M(bO-DU=O8!]AN0ZBs'qVGuN9(3;X#iR))]AW!p;G&T]Ak]ANRi8ar_4$]A5,7$7rk=SZ]A_oSsdm
LHXX#58_08Qr4&e4J'M@Am`@Je:t(ld-n$9e+E[XeK.@D;C.ZU"Q!"cc5k9>fU`d![oN`hKa
=03NSIhTWO7")s`Ypchl2`pMHWjHTbR@>0a0]A;8'DA8["GF+_-T2F=t,YlP;tIEVdjj2pH7p
;>5-6*Jhkc"G/[pp)Ct!i=M:r9p>RF/R#6,>ud-d1#Am;-GO$[VlAoqBR1\.gqlf6D_5p_/<
:I6:PO4=-\f+Hm.J`.L$D[dZ:4cfc-=fhh*O$M%7SS5nTJ7a/G`_mN!7UW;66Ql)Bmf.:`\h
BTV6H0NR"Yr]A2jWXnr0*f,j=joDgE22qmD1BFZlK2X\Va#Yp;m9N!$FTg*IRLl-Tb,0?F)Sd
M/[%cY:FlcQ[QpA+`#1s+2/6m(MQm4lfB-Jgmjc;:I<qG\^29*ApIG"jX55BHWI_M%=g3O5S
^8m[]A:QtYoqL?A4<C9=KFBGm@[1XR5>p3)S<EVjD?0@.5oc\i;qGdr;91UdJ'N*=4=PML0T@
g/pkn%m;q3us5XQo'NmQT#Z10Aimj<V5V#fg?p*W1Khs(G4aL?Te<'F;A##\`Ep3s,L4uikZ
ksG56O,oC:mS:=+77%%/VYQNBip+-Mb/SB#/WXS+&c!Bsh!s8<6i/'dHn_.%RM.sNpK*)%TE
86l_YmQ+BWmW?*k.4AaqljO\lUG^,uI_9jOC&h@uecj;IOo\aqC+I?Q]An$$+$<#mm<3lWC,m
K:<Qoit\>WfUR:5'p4.^BlsjFkWQD=]A=2k[7mb*ljt"_rQKt'rpJBo7&Q8>B(+cEBD,OnaXV
ChbaBrQd`@:IJoNFeAr#@j3%0?T3n/A4qIR%=SfDISrDVbVhMN#Kt"g+J"'n=0!->3Ls'_j)
i$7uZM0Q[%7Ak=3.1J2EFFd)i.%RfQ1D]AhW9kkd`"+:u*;S@(GkpglDJ^meOSJQ30XfVYY;u
DI.K'!7J+bn![5Qm'<r\NV"c[PC/!/DmkFDoDXK!d^:HUhcUTuHM2,!,9NJ[YJJ!KFFXn_(?
$J@8jV'4dOeGf!jca7r]A21K(uJj=XN.L`W&Q!42<#9[)1(gCc"KKCcjX:Iih/&@<u#Hf^e'B
3$c]A#LQc)/(5QRinM(+-;RqX`OZR6,<P$[IqYBMP*V8m#1Fn`6_%/E;^AhGL\"qh$=ahs'k/
?ko"-C0,WSJ4F/ofU?6GIi";UU?=nN>>+DAk!:#uK/so4O1t[Ec@V3GMQS38o/""dPPJt@rP
L[B>fUb5aS1`8Al%d__+X`J*-nsjE<]A<Y?#2b*j'8(:&QZ`\l?Ve=\hY!hli#I*H0"0'.<oj
VK@SJT(0Jp^INA;ci<l9BUEjRhnN*+W,Bapp@3gsYSNGQ(NQ[kAMJ-I+^jRFn/o\h\D!'6G4
EQJ?:URAher5g2;Gk-gR5ePpL;[HDqZ]A$BW7[PN4b.S4hg2nK%*kgraR_L]AWe,imNmb_c%Fn
o`hH_sV.^!V50Qb3ofHAmI6l5uPT5LYA)A?:3O_f&>A<O>h)H8\\2jBF4(Fc>*qk*I#3ijnj
a"AS80X6*ODIg7!SEU,hA0N6='j`.oEcuZ$*r-2TsZ!CS^E)25%>E'j%NWb0TU$3R,<m.%7q
)528)O_Zh`WC=hgngnoC`Zg`^$n38n$9!@8U9[fj;#>DHu:/ri-03e@^RI`GqO8jVpT"f6;%
P_,R(JNC)9o'!MI6Y[6d%:@&J9t@LIjCKGMaqo&CH]A!))Q;9b2"YYZfH$8\sA)+NrWhBSge/
lH8BE.Vbnq-Iq!uPp%:jPnlX/9.R`aquQ@SEoZdko5*>k1)O50-<I!82/#NP.g^EnWSN.bVg
_&Q.-X9;\D&_)S[ePi'82*[TW)0+`KuVnZ[G`\PC5T;#)+iU1?2gH;%&iPj\cA0[K&pH(O,s
p+)=n\Y[;O_Xqh1KNuHr8T_,g=h5_oh1M=KpY\2s+*UMu\'t2bC!cP5V\VAfE-":SqkB]A9G$
S('*H.@<M-4Rq)nAFT]Aj_q9&J:RTMJdDe49CD5f)<:6aeaE)r,B>m@Ag>le*hLq#:d$s1Y4g
J&#_>B+"C(iQ$`#/5Wb<MZ''=4:N>*/p8g,]AaU/<B;RD0P^[*#fcK[Q)T,6_UtPnnRG0S+[a
.akB7/d,=.[t=p/Ldi[<6Hu_fX)6'%N)5'+*#(YTq<bObWYnPH49K`PG)6D;4c6/aAC2G`1K
.d`"W2iV^9Shj2)mR8bB<#_0YdETF57C(OlRFbd@KcWQ\sup0_QA\rM_#Z;-X\iWl1#58_9\
k=iEnLWl^Xs+]A)F:R_,^5a+c'JJ!k`!5:[,R6W[!%RTNL\`'7Gd4C#"LpZ@e)DFR/LP.rRF0
urLj8Qnr$I1MH]Api1*5"]Ak"Ml#Vp=d7\qcVdr17JSO_BAB>1704-Y:pOFD[\H#_TYD,N-&#r
X3WSh^*dim,Kq,mG.oO.6VDEbS@>Qhq!4ZR34JO,gX.\tJ-7ChWX`2U7]A7/FQW)Iu^RMaP<i
F)qM_O5`SQdUO:Lp+\'"N!(-U1+mD8pHU"YZS(#&1a.1`)5'[c,p6jAlJ1pi;_TJ\NlnT.=\
Shb^@.ak:Q"5=m*No-d=tuO#Doe7G:SgVP?8>m`i+\<4&CjVS+<[;?E(q`AH*!J%2ep;H8)c
+)S[;Z"(HTYHm;3Oi&_`14BSnh^(Q;S@JV>76Z\"dC!UE9.4@;2UB*B.e#ua/GOI.)Xa(9!%
R&KZm:2-b%1LTRYXFbVFa"]AEQTOfDSn/;S(n&e<DY&44g'-pJT*8s\]A(jIM!ktL@%l)-TGb_
"!jt@CJq2S>Em??);eIID'm!@0A)#M8LUXc-/Y`jiQ(mRpGmZBhIRir.:2PS$&9n<HHh9p/.
_biIn`1Fs.nb5gee2=hgqYp1Uc!N!tX3_ut]ACb!;E/EW*58MCom<AOKjlg\]A$ibZH%6r?APd
HhVDkl9^oaL?mV$R'JV_Lqf"GMhcOCk]A23eL*`[oh`<9t?2,W?1%1:[D?!F*>>a%3.s.VQr3
2fJ]Acl:q@Ohpg-."N27`pR*f*U?p%>I7+%_6HQqI157<^QOZ/.1nhN#`=[4Z)AM3'>EoS,DM
#rRq)_m6oIelW`;Ic2+T:uPeH_C`sKlGi#PVE@Y9:]Au!PP)$8Vj!hBh93*j!7Nm*b8@(^!qi
#Z*4sXHp&d[l&#2-+r#CU_99Y#(aKJU*/%j:72Sr)\S=?mkZH*'%85:!7Rtc&:#&A=i&"fML
'QKSu85cF&WA^m^']A"-MQl3H/o6Z:_TfcLI[g/b)m7Y?oDO+m;f5!a[E/uo)%UopRi5&AR/r
ED(8sb@)^B%Zn`Giu]A=iFZea^+-9:jd^"N,IRF0f"jEiY(f;7B47@;U'5+8FVOMW;"t)i'@9
6+l[UFJs0lr0KFo%S?#WTSIUdnK"kZ<4?*UD_))CS`KT",+GD.C&&iA&'iqK)GjXf'7Ouk/T
*mZc;lA>QGL=EHr]Ajr#5h2Ms5A:6>.0j<J?r4!m]AEDnXFgp]AHNCrB6fu4(/JRTRF4lO*VNSJ
sa?g8WZI7F$1Wk$OFJK8DZ$u<c'nNn)oI>_$+Q6HI6S0sN^TKa0=ZQsuB^:P01:EWul.N3`K
Gu[^j,F:Fc)/*7Q%%9O_/$UPMPq!+cUhcqH_KtM\.%.%9+T'Su,H]A2D4qE!3Gng4lrgmQnZG
V)HOdZ0X%ceC^XQE=[pB)gK*+HhQKB)'$=Of+%\0?U2kY6%FDm[pV$VH>nXZbaOI7s_6s,!_
a<*S&XWIW+ls7UFFqEJ#%0[nFYD>lD6@p@.4V-s6JIkQ5q*TCjM`Z$9;IoCU9>fjY.Y\HX)G
ZnZlrh1+l'<4LuVD\7Mf?e/Q/^8-)X06>U:=l!^?9o9l@;7Od94*d9AoGPB'k1m^F6[S3r'd
k5A?kr=$Fs=14nK)-E_hns4er^%DMZZW9g)S`DMWO]AP576bN+=ofP=$"LDaE?j(rY.59>'pY
9B.&/`_K"c7;`-KGF/"4:LNXOLno`e"N9Q>guYfsOhI!6(gMC!'#kBRppBr]A.CpO\DTu"qRJ
dR`QN":dCWgiW`)Cp),d/(k4anIkLK.=^kiuNLk/2MNbp(Ia\WlNF0gnY5&]Aj'@bH%*M$>cH
Z[-P`"@"rm<m8MeVlE0Rp3`@KI7%oAbT*qLHP=8aK]A6t6?BS/kV#3oU-#;0bHZuQ3?C]AU4Gk
473um:S&4V7WDaF/+SFU@p*hX6K"qfCGS1']A&f'4#uEm)qQK-K%&OcT-o&L=hFIKenpq$&*D
ug+%;'1]Af1^3FU=j.\>.BR/H4SNr6kB%"ZB,G.(2jX5**]A&6LdoMqHmOe[K#Ct&$P@m9NYic
a*BO]A]AaM$-S$D1Z1LR:"oR&lDBWM&aSO_RJ&i:>16c1$FOr+(ggOWhk$)d"n`YG0288uY_jo
aCM^QX)gN\1G6C*4[]A.]At/YFeL[:@Fs(E(NHZcI1>t-<;T=D>h%,>:tMl?qTB6VcF^pF&=oK
t?,4.f]AG1Qk9;#s^9@nK4E)6n>W%WeC8dcbW'#28:X(thGI?U3K1nj(&K*,fHj>/=D?5NcI0
UA)N&D,D(GJi9EpdttkKN)94+%=Uf?1dHt5N0Mg[OeK]A7pn/ei+_,9(FYVTFIhD`8Xeiip8,
.6Lt*5Wr.(jc]AH#Adpep]A4dlHf&arX>ZW:,_g,"2iHFRhGf0^^m'Pu(nadB*8J:WiN;[^k*Z
mA+TJWQ"`(aS)unWl+K\Uds%f)SrXT:I-u%0dGZrTutf:p=nMHWI0=4h6:g[/#1X`1[[L0L?
;DM#,UJ-M)h3WMh^%lo7VE7HI]At`kABHb<:pq^gIMB'ee2PTqbauG*n"ZI,&(93hZ?YrLqq2
Q"&#6]Amk1H,,CsV$]Alr;a?nJt7-FVqSVV0Dd+duno>&7(^MD6`1BE08o+t6+.:g`Va,:t'B.
iRHnkW(iRCLRuNX\0gSF0'LNW,sY2q1T&j1Gb\Z!ZU#$6a=3/2`LrX3;4HM#AqqjQ\i9pT0h
W()B2!k5n+V[4B_DBqLf`.C=-&S9]A-2I#oFP118'V%<jN(We[eZBea.9)W1t?k(J5Tr?-8,K
-hb*^E(amdf]Aup`/?a&KnDCQ)&i5+qJB%P!6LhlQ1qWbb22d_N[b8p2D2a,4msn6d+gDU28r
3QKYL.LLXb-4S7"l+08E!-IA&1\1m>%qnlNM+ZD+uD[IK2gQ(4AN.C1T;:%#ERO\9Q@VG%jA
\hZSU^aT]As-F;96BNUmA/Z0M(R1B8()9p0?i4VaBqe+!@#/0+/r@\%fsK6R;S(h)*Dh:+qPb
:=CfmUVtq7d\A>Tb&bJg=]AMej(<[YO`Xh&cEo''q@H1>XQ;kiM^A\VLUb*P]A"_.$^=CV8_k+
gUs3ecaRIi=]A-tGnIe[$U^%mc1i-@4An"!S9m'-Y@"M3XjhdI!C+`=L86?SnT"I*d9.[Q9P=
Cqll)E-_E/IC2aZ<p7tM5EZ]ARkK<S4jJ`>gSBsUhjLDUKMUI2?Z:GLrd[nY]AqVM`73T]A7ERK
\=?_GF4S;jn"L,S-]A4V=*2V>SOc$%;O9bU9R6f_!D":OWF)6GAn4gplqpOXoF=o(#?0TH(lF
ClS\^/7D`>Ap)hZ@n-LbhFVfLkR,=;0T+$Z=OrR.oiRWju4J^S?mspSN6E;ZB^2ZUah\2^#`
DjY`G,(17irrdiI`fEtKh:_7isp@C[$C0-,LUQciIu:.HM^$jCd.BR5[+NFl:B.L:gV>_P4I
.k]AC8MYKj@P3T\E4@<Bd,PncCpK61P!rNCXJ$b]AWdB11=\1PHo5Qn:Ct&g!b*/n"IHZ0DppF
a_lBo=M*K8l-e(h[\i+tiI!)OL<5NJ2$c-Nkh'HNXO6pD3UgRRJk^-nF)Jm4dAg?(Via?:RR
o6-%h0*W$gqFT'J0Une*aa[Y#l$h>P+JC>V_jjDQ`tO$'=Cq\0u^$3*K5!"A-cbA'(uOL5?8
;B7TD3)u"O1oH;+G\J-2;k7iN=WCS/HQ@9;X.<2&k$K"cn%,9:%d"kcoGkpdWC63c\@e*Q4-
[):`'iK-P.6cX@<A9r/^O4ccDh&4En`a)O&k!JoSJMI>>d&s)r&C'@pUe#lG4k-qZd$/D;5s
QV1FLUGpeH?66XJo3Al+H*&D>)(l'RlBa$b-Xb:s=CQVE0s#E%uUK'e1cKu$)E#/`@.aAG2+
3(u;XbZ\MW'L$DPa#@0KlB6e<]A8%]A8-R"nIPGad1<^?k#BXVGSGH\Q/#!,10D)7,^+bS`RE0
2Jshl7S('tj"Lb"2XJ1^OPUcTp=AQ5,O@*Z&)8SFfg$:0=9jfOUOmG9Jer#p@f>_Lh@4N'1+
FK^KUq['#0R4V^aK0q!,hO,9_/4?aaW$n?sI]AOT>-m5dK+@Z@P<jf<%IfP)Xa@JK::-[H/@q
rQnH#G3-3%k_E-Xg/M=IkBhga/(S/M@I94s2lSJ*X0:`oR6p`C.L5p%@5;=P;:C$56eP.La-
DW[oftA8f")`59e3[o`s6F5utRt'(E]An4;ZcS$8VIK`=KWq0e5O!R(V(;A601g,mElKhkIY[
#GZ-6XVl%:@m'T7:gL=%=)<L/lgr6ecZeNtglZIPPcj\<"]Au3*R/?,=r#UQ-I5.2S8/ZbJpK
)FVV/X)^@ulRo[Jb3AD<A9Pl`.uaJNI%EV:bS[(OZs*>HO5d8)DmX/r/^?+OheXY=S,JD2iJ
(Z)1L*k&R*B]A"3%oV[QmU/@+["M;%am$6X,f3(#eE-^r6mmY,>t;P1!G2-88%D3[e2:XBP?X
_?VS*;R&f7Zlj-@#Z#%OI/90O\B"K]AX)$:kZ<mNpHl3Ig'(>@X9'dq&mU57D*:P@L4=6p8s\
OoA;m%_s(Yli@2l$J%A;hQMG2$Zp6U7dlV@>T\)kfT>l;0t-TaM'E:Db:TtM'E\bAtp8bB;-
:8&g_@M?$;h_#QR,:>HZc$>,F$0()Ne(`Vif.X3HP!ts]A\b4U1IMN$0[/T-A&iAcsef?Q"*I
*r9Wbm/:<T\aO?]A4L/J.3cEX"t"DaG2g"r9]ADFR,[s>D/=!NN5pMjr3Fqr!r;1n4^u2%it:;
E3O&gfd>Nobf0Tdc)C:;HKs$;Mqj6$ll)qeATBYAPbofCT\R$tW@&$7;K@Ch^k59P)&\(ZCP
708Qa'g'lD\p`76Ln]AYfrXe]A8m1]ASSnQ^nUYu7]AD`Vp.l[jbc&<7IR&7CJFE\']AZBQG1IrG&
t4DLHX,:uO^_-!'6nhbJ@j%bFY(+'uf%aAS<KMo0?:LaOl(.l(o,YRrk":1Fa<kX!8#n^6_U
RLhX(@JMmIq1$5,<I.,nbAC^A3r&(D>e#<i2f*Ol*cNDddHub_-P$-EJ'<'.9%?Pt"CU6n/M
O6XjBn8EbXBu@3\ocJR$&di]AH6su^h;\8&fG]AX$-&F!d]A(50o\XTJ9C#jbXZodgfM5Y%=bo!
Q&8."gM.13Yh3Sq+L<8/:heAfH64+igCX.VB45<mSA0DcKdq:l"ahsgW&#'j2C-lI!"?tWFj
M5jmWGb7%g#2$larl;rNoCKD5ScA,)I7@H-:[_)KJUC]AS9lq2^hgbUs-/3]A)7Y3p.WL4@hrK
#=fKsV@AWWp^C^cOhXRsIs=e0ddlO@iA;^,0(&(jf"r4R5H$(.V8Nc'&leqPE%%]A;l_TX)`e
ol"0a4O4Z`8jt4U5hp#l\3GR$=@5)ei/#`7r`i?>,#$A+X@VeEO./_?*_,UPpXZ-qme3&-D[
f6G4-t#)YkPH*DsD/.ce/?XP<f`UL12d&;IV$eb&crV0Y#PM-H"]AYW2Jo0(r&JspZ98+_9*o
`N`p+^,;Ud>6AN;l6`tq1^"7!mhT(HiIo#Ri]ATT<^'PK/(7i!W_@!0;B]AoAu]A">-MKTb6Q0)
WR1N-LXQna#/HI5.hcjB:4pr']A$=2P1#a5*ItN2>gos`81oNu;#E!t';'de"V86mP1=tlaj<
PGU;CGF#Im3/Afr8A>aYZX#'#q0B,;1DGn;,c(!I_)miS>q_?D2Af)2ePdN=Zsrf(!HW^P7k
r^Z1,1B2ht9gHFYK?;%Zht`@h(H#3fVgY2hhh11k12iTPDI16O;\PMlm0N>OiYD-qEUGGrR,
YKI?)SH)$2WU?b]AqeIIi!Frgdr3c_^E<A\^r#DjA*#i/!$c-fqL0J;/1Euc(YYU'r@;s*8K(
o*NBe+iD*`T=Oedfjk6J"olj!\dqU+OSZk=SD=HZK,]AS,G(:Xbl,uq1E6SrpDesO:n8(hOD(
a=6J(O9`FU'tsVG`:nDc5F+pE'%+FXJ(*I6_aB[/f,qK`,F),AeI7bNW\c#]A"h2R#LMhh*t2
$8'4,oL6&:Dh7Zim<1d/P%j\S6n;Va-!G,Ljb"OBDH\//i#$*u54Pafcs8oLTRJjqCpGF6G2
a;FKmDTT!+D7j0=#=`>FB!L]A^i\dgn1)ONeF88Jf^*nD7ZrdSq[CG8E5M]A\_G]A[\::ZID1UZ
<Wqep3iUQp9-j;pmGi.?>iSlD&00!]A@PFYNr'Xfq'-VSR5?'R7R0[7CM$47b4m@)H._t0Hgd
]Am/r5[_1S+03YRl"H#th\1p0BnFUT'`gbPbOJ13rTb2@2i@\>c@;p^'d'`@dmEE0>'He'@<8
m*]AfhGGOMQjRMGL;WRUpP!:'!jeg@443NLi7?%a5q-hEq2H:]A'2SkXE_hh1i011apr0J`]AZ$
R1J-Y*AdO00`c!.%cPD;n*'FDUMp@Zu3Wl$+_4Wk!NKPP9^,#_o@T0+sCeK0PJ/aV$#'A,BZ
eXaPU1PY,D0H!<-.9NZV^%J15fTC$unBUe2$$Ih?m?!9/JO=E^gD:K2:tcepBO+fcp2Hf<n&
\m6!6ZZF0$$2\^)WTt'UnR9ea.L?BsBCd,jX$(2jfE9T+/,ab1Lc%-,O!7&6^^5b3O";'3fM
#6CXkdfB8U$U%9cF5D+WZ=3S)R+K<f@#J?B+OX9^`BLD"CX'F9Dqc'4Yp.nh0Lt%Bk&0ZU%X
_35_AEUX<VtFO82e$E,/:+3,)'qF5\r^nFZZJrMeO.iF)<H*a"V.IM1b*AJ'%^Vl+@H#"&Dt
;=_,>4rl,^]AdFkZp[$&t2"ka,3>j!->Fk,0"2m$rGr)uB/?9!WU!6!GF3XsluE@''.\8&L:3
&o'?TUUjKJ$7Qc`XjTFG\@RFBd,EEn\X5PMD)7ga&Ah?3U@A=M,XRh>Mlq7UrbH8W*rb,"aA
f4We,^-g&fuhn>DrO'<9sRkODs1u^RS*^k+9\3$F?"P,["#`am7*&dnNYnb;T,6&*MHGl"7R
gS7<sdK8U-2W*S-J>2KH7c*i9P7%>97.KVPApSQ#;69/$SE=5PF%R:KilQdZr`=l$%EUcV\V
hph@Og[>'mCIpCY3m,4Y?`qK_gT[$7"IIMg$j<P%0H$E8d8gEh%N?e:38^'CjA6FfQ&aJ/*V
p>,=W]A9pd`0ejSHCM57lXpTRX0Q&k7'<EC2GO-0DCpfM=ot<nVB!()MhT==;=.AD?%^Y;B*t
Ss2n?(FDlah;(<lJ2DWo[-Q5LX:*dN4]A-DX$[8%0E$84,*_oX=n.)(ti6+@&Is>?g'.Cl1gi
(uhRVaN;qI@fjPMaj^.fCel@bJ-OP2-Jf%g<gYnd"R*l,I2q*P'5`W&^T<ni\g9C7gGsN@K:
-7RY"DB"oR;WQ!pp]AlOrpNplj1b_SQ^kpGD%2Qg6mFFk]A@*[fc@rErQFH*W$%E#a2D'RFiNi
ZO,p?BcREV.2Q_oC<6F'bD1Lig/(#S&HPFTYH8RmM?'?gej*2k2l/;k-G2L"O2LqR]ACBGPMa
uC-D[<4f^#5Fhc>,[-5ep8A<:9(^Pqg<3GEA<#<Vpp@,KctO$@(#KQ'70$ZYqOrp`23PJ[VU
6(@:^\!dlcP"?`A4@gUN[ht!Q2Fu7U;f$fXrV7j+q@%$k3"FT*Hp.+j(_EOel[k\8g6/$U0N
FK';9/%>eC\]A7+9qYoEI]Agc+mMVGmHZ!1'_Iq[-`q'7n"C-)NnZdL^"B3*h<SV1'/ScL6[G.
jGhHc/AON3%l82?W\L,iXl'?-d%57jaVZr/H\YH-'_]AT6upa/n$B-DEp9`Fe55MVB<<f8O')
M[EpT9CZ74H[8>?>s]A3g#OMW]AP;***L<:SQPL`f/sBZ78;<NH\S/Q&170-[E$sJmRr1W!?&t
gNrQs+5ld>mSHOAL;q%AS1aPT?K'-BAh,HHQ#DNY<dS)hSeN-C^rZ[meS3es'%a"`c!ZO25m
o4m^LqE7Gj"O9S=l<fXRC%dZ0aciI`HY;dO8g,/8EX:uOl8?40qNG*R\'!!VfYq+<E&9&JI8
4h,fN6<N&k#G*?VI[^D=Bs#2NPY<hIQN?SU2:.)tp\<ECb9-+1*BTs6jtk!3atX5P+UJ>nq5
/"q%/d[uNZ9D!P/Jr+-*2qnaeOGh\(eY.:t*mT\8&^%g5i]A.te2\GoR'e8Gj6W0Y*,M\`WMq
T2N;^Cptc?tg>!_EKr7[pe7@amptqP4D?F_mW7m=IPn8IZ,AXeA`q(2cj&t7TEo"f![TOq-P
ZdBIn%h4-2^B+0:%<D(YYGc+!hRBF\CYSnM&u##ft`o7P.>c(R0fraq:eqeWUC2/0>[Qj>$F
=m#'(08$Wr5oN16+P,7[kD`!YOI<fsMe`Im@_9bkE8lE[JDATJ0AedpB$=4*k+WGPjoc$WD^
O2bN%r.6d=-&k0?M%rQ"PFFA\t*RWjCSPUL=Cf]A_\B6i$-^)ABAkiq3fUDONF#J*XgMt6FO"
WceU0fFD8\q(9]As.pmZ+4)_]A4U@2?2I5Gq`r,[g&?;`uXl'H+uT0<`eNO*2+q%:5Pc-Td9RI
`Qj#Hu9m'E.0e6OEs&JAd*22KYD^[C2d_%Q(ZH5,BMBG*#9W,R(1Ka7BdG#).Y(fl)[6lT%I
1aYkib]Af^kMIZ;1IX8?QroSiF%+,.4p0'qldmm5bSAB_C5f7*jL@Bk`1FG:qFD5]ApNbE90MJ
a!B_u.pWO`-1bM,^SkCNl3h;65C%TA\R3J7F)lIVflHq3`N8i&(Co2P!%F)4Y(KpnSKS)eVU
SbcnS=gQ9SQBg7Xrs$pVtR;)I!smDQ_ad30R^bE;J?26Y2ci/?)FMbf&BsD]AZRP!p16nI,)M
)cF_=(gV3!&)Ih&\nP&(6Qd8ojG]A/uuXDJ\n?Y!D;Dhm1"@A6'ASo#VS$Ql`+OoWjKgDOalD
\CLk0R&clO4%=*/nUdUaog`mFTNnR_k]AM3e':sRfs?I+0RpE)D:8\]A1"jHYCZYsqI:]Ae>=Fd
.l2*jYXZbiBF3cd[V]A`+SKR"".Z%+"4hI<Lg;hHlb%aZg=3B%MF>]AHug+okDrD3e1B9#nY38
^hY$6L7/Op6pb-/h/SXa9B&E*j0$Yd0R4b2]Af91O.n/L1LBkA@eGML5N<;AcSB$0Rl-540E_
/^ZlfAb#6X,*0ok5N'5ZI6t.NC,a-D/M`V3E^qnonW,6(5u8VgIETrK>/(/jE.;Oof(A34_?
-/>"M\mW?%OE6T8?CK2TYjUm<B6B@qm'/2@pZI.CB[p)b'pY;ut"3d:5/Tbt+KZl*EB)af_;
3WfR[/Y=ZD(rCQ3rWJ_0ECGgI&Hq00e\Co4>+TlRtZa2">!J3e_^/I@H;F]Ai&o#/)N/1T(-'
VYm]AO(3h96dHA58*H_-*<%M8\12h5K%n"Rf^@$&cN6T8>O1@DWpS>rLfog!:EMfMGbYE&@+0
Or,.H[qjQ3T6NlJKA\0F_=dC>MOaq^R*Rr:8Fp]Aq3CGA@^XErk>&id7[jmc%)kpdE;6ppEF6
^kBmt8&7EX-[f'A"R<B%m,4m=3W+c$S_=0j?$<q+!oKk]A*_Lo\M*o5E@!0(<u':+1H%N?'WX
je;:ELPNi&'6b?)fX#7ZURCDW*Grp@RH)#5<R$/e1U(%8V46O^G@2eI.Om#&>2T!,,g;me!<
c167_1eGE/DKa?*DEXXc<!#="sN:-B\1_66*J>b/H?#bn,:Rqe-n;*@/Ina7n<):[?L^2ac]A
[PZH$e$T1qWYhTorX,u+8"2rfs+@jXh'DB'8:`D-a?VZF!<pXbTE/c)#dd_Zt0_D^L6N7W_3
1bu(j'-j%&G_dL(9:ZG.EH?1I6iddui>6@b_-XJcQaME\,*bLHa*29g;]Ajdh)GPr6d"$*YVK
^[iLHJZJW&+'Kr4[]Ag+;s&-PQa8[$8YVuZ:mfn/hn+2"3NDe@8n#:OM\gDDoUlh^K1'F[]A)/
sd[84[%JN!ZaP.ebDK0$QWAmj1Y$P3Fk<V(WH,;Q"Os@;FFS@Cq<k2GeTK*'".pN^2[&YgeP
&ATTPRqE:Q<<;U\O;!B2%.pn-1b9.@i.rSZ*2Ok=1>k?lh9.4HXdcWjb.O6?3lH09YCl+:,e
*+2$FE)k@$$_.uPG$Ng\4;*j$g@fjq19K2fdOPs#NlMt^<^Ajn(ubMHC:JQ]AUPD5)KJn:Yk9
?t=\9`S\ZrfPZ&%,S.FX0VGqg[4W=ZTL=6kFPD8`)=%0Z[/A18l"'>lYu/>4TfP^';*`+WBd
dW>?\dZGi"US%OtXS72#*?)oh&Q8BHg0KhAc/tVaaj;jQFJ&U<Zc*DVFYMl#idHGpMaq-@G*
.e//"SH6ok(/W\!(L4>pWrPu%J\+5W?]A?Acd,TJSX4HQZabnT?*D\sPepP.C2jb+"0W%orCa
a6?%YC%TgT'cuM]AbEgk&2dhH\!'U`jj&6W+n96NjJ<_WDDH5W6d\]A$CU9m4KIt;_"(E;llQR
AqYIKB*QlIA<dAgiU:GP@53&KabbD]Ah&@'6]AkQF4uc5\3s^$S_TIG\]A_s-Ga:pI?OfK>Q:Qo
:7_a7h5<fhAn1&KUd(tb<GKDc_M,[f:!`Q_amqZO2W$F4[R-,37aj]ARG7QQ9++dE@GY-M0Wi
?bhO@Rd2*/j]AAd@Yg-00CXh@_]AC^Lgk"Z2m:%sLp9j(l"Lm\D$OeDG*-WKE)t3bBP!m:BFkV
T?B>)fmpm"LJo<ub*"(8t\(%KVGnkBoB7-%LjFZmCMOAMi_H`)oKO_ht]AZfZZ.X2*%,R7eKT
_O'(BM-5dT#H2VlR(hpeX(.8EtOce8ZkqTGToohf,I(Le]AeOWf$Uj_^;.beoc/ipQiZ)JTG5
\dm1,*g'GRS5b,'iS,lM^TNTlUT5q]AGnSbJB)F[0oj!f6uTRkI-\1uZZlO0!PD[I+f9?f[)]A
l7T?0A?dQ:NKnPdKr/o]AKih8i(lX5"iVVsX`bUrp$e-p$6oeZn[^0I.oAA@&rmT<fTM!-s^Z
$0b50eK!"d(sL&',f=p'=AFpS:(;iV\Wh)bJY67]AF*12TE#[BO[JS\[pd'7BXcIk_Yes=Nj7
@Z16E=(\+5#nH?cYd9MJ_n]A0C15EXom$+f?G7"0>/-?gOL_g<\BRD*ng^ONE/_>=U_SaTFrf
"k3"2F:hnH#sb^IH<8U>3rV>5^Q2#KksM+HQMFocTPQo43F>\(0HKtmRG0+%MVcpHoM?DCNF
QRh0qks/5cf-(p^8P4FrfIY8n!pj(hn'_HGu6_^74(-N0"LXoAX0r0A9d^45&S)%ekXRD@j:
TuM=^#=&uK>mYLNp2'0Cc5jQb!r[[EF@GE7h?tX^3f4JL;-D?4UU/p@;!pqYT/9"p.i@R*3D
7h=2sG$sTM4YkVD%:kHD,Nl4.<3>CJqU^a`RnEMAbN0mI+\F9D#F<+rspe\kqa6ltaj0k.Mi
%m&i/j%biOe&?\=\>W=um34O1-cTRmkKslQub4#7`d<44:OIH/>(.fpiOB5ih&UOH*_urc"/
!-3$Q(eY1RM?-rIg.$?Z.`4VM7b;@>^g&08-@(cU>U[9KYkC9G]AJ5T*IR-A.0"#G0[@JGXR!
PphTC\O;`c/DKc#gUj5^:)K=7.\cD(L]Aoi)QQ+Xk%dnE,bYk43?o9ID&%RhNCDcFSfV_MI90
]AWDNef.?Upl0eD#dQI!nMl<#m@:E=L;Z)ZW&U$5L,"hr*Tq9"i7DN5r>*mt,7Hp7?6;7'[SK
LYT15`#@9dtK:&S0SF`[kF7[h,5`_-AH4E15M!(ni+=W\L5F'("I6=gR7:D:e`mh#;Qtif8B
Sq0npuqtWA3?T'iC7Y'`A*YF[;h="Ge8e+Cq\k`^[QM"j5"@cAA$Rl5M1b5*^=;#"'&"PDJl
1X88Z=i`'\X>/MTC9pf#.sgFZt)nH>T9"@R;^bR^foNkMP_![Fl.b`iucbQ&stS=ZbHNah!=
cBXDM.qq2'-5@lUB)h4MnJa+sjM;rr2>^M?Gc1`1"g#0t-<_g;6_7a'Uke@G*jZCZ_9589A(
3IqZ`?&"UGmT.p:Z9Sg9NNdICQ*UA4S7<1R#ip2YdOkbS1pi\5oiZ?:QjM:cP5lVX$tb,Lfj
M()8LTslrX2jK-:NOi,3>IAWB%bO_.<!d"LI5AdmJeF2o)kR9FFeo<iJ1$dr/RWK>ODi<K%b
->5"8rGd/XLc@BgIFX;?1=4JBCrec,0e$O.=iM_po>eW;.W+*^k,TW*JZ,8j8nRiTnT!V55l
NGfR'$SV/)MlPiC%E#h#)"DRq04C;qm`@lc?\LCqY-=c4[:cO1kC_)A=(\c;1\G(oTpH5/51
p*b]A2#M*]AM>U,,GfHJIf6/Q_lDDf#euC,Kd/q>oU`?TsRuS$J#EWm-dGMA1]A"sZ'NYmFZI7l
,%@BL5p%(r[X%uQ0pQa/2X'q'"$FgCCHK_Mh/X..C&N+8`3#m#VAN:T`e3JkE@V:Hc!2W^`,
lB7$a\lVSU[,XA_6f!0%aKn#5g6aQ8mK"hKm[Qp5^[3lrn.rY.Q0H+cR2mfqR<ZhW1iACf0o
jWp:m/$bDZIb-o\#1:G;YN8;b!,O+2S[Zu(N#V8%<H=WGCee_+(%$Rk8r+p53H#K..-LL11B
N7CZ&.nK*(-_Ef)6_(4QAp'RB0;2cB$/fp*hm%D>Frdf<d8J\D7YN9]AM1hhJE4OgRc?e3!Y`
KjV[?m2^1;gW-$:%L'en$eiKB1'@bD+0UM/]AS;OK;`]A`p@-`)iVeD/FuR\=i.F>G>:FfZTYS
/HDX7FJQ:[Om8qKmhJN%$;RpO7V=$lLP>\&WkHCg6%B&E/3h45!$XYg6nsg=cR[8@ZeVH^kS
kg`lHK@TB,#'bNIXU+[j.lPVMoBE6Z-K)#gM=>/]A(^);:SA_g@QZroIC^DTIV'Wl=Q2U0;*%
BZNN-&L&@@sG6B%<_4$)\lD[7@)[a=40`nE8kU%*qm"[u3UjqaY3WWdbYKXJ4/j:fbe]AJ\m"
5jc/Br[A.,:m)$eE&ah0OSS-p?$KdHb$!BL1>D>B>A^?YTD^c.upe6aJJ58`Q\I=lef58e4!
=&NOl-'#ms_(,XWm#9Mb"Rhn,S(hRgWVDgYciJ64JIJ_(jA?V+9`2V7g:+p*fkih84(^U?Fi
8'M,CmZmDW\+ceHWKLQVKh1OH\..N2XbJZ?;ei7`,86IO<9VO?B1pY3D&jt;a>`;'I4$i6Er
C[go;sAp:mcW$8Juk>*h2E6M3S/JdR]Aai<l5+J/[m*eFFH\$=8lES!joc>,X'[ZfeK@'mggr
U%XKA_7WC/pak<4-LO"bgX&_(r<Y>p+WG+n!8K:h+&,M8s;O8(bZR#j)_cN&`/<Q+NR(CR:N
QkBZq$lS"gtON[J%5!"bY$4OW;D^&+$=jI`BKYmPr!e.8;c+h5AbS$Z[GX751g2aD2]AG4@RM
NJ$a93WS&45G2]A;p[P<j'+o!#Fk6isnA'4QhUdFh-<P.,Sj>0nr0>r&>9VtnV6>N!Egd\ASe
m/F$b%l@"Hl`fgn2@G?JkH8;.+cmK`:blg;G^:1RH[JEq]A_F+q*bW>#:0Ztq_tF/SeI\q*Jm
\Lm1"@a763qE!Zo!LoGuojd_A&DbfgY]A/JYFQT<\38E/$"]Ar+L-)B:n5"YA')c^n[/R'T/u2
Ve1+;%jJm<r4MrBN(lCSd1d@aKAKO0jR6%+3.-L(lYIPqEVueVh;L.Ke[t4><bR7GO-TIZ22
Br7j5/,J`0qf9rO)+./Zau^+_Qj5Hp@&n-HX0Wp&?.nWZW;.::rV>NKP.?E3IS-6:^s+,76E
.Vq"7<L9t*["LB5*16ZkX?Bc&/8!AZJsN5Z.!*pSY:^+`d8_W8P/@T&oQrtNF:/pTcNk%<>W
T)7\FeG4.(*VaG\#Re\;S)I<<e&_Yr1YphLX8rH_f(5mke#&V:C_j?:`rg^[pq\t5/@I(VA0
S5Jl9^\LdMJHLpDWo6^E<i!;GLY8jW"MPQ,B>N5QO(krA,h$4?$lD+G.mps"-+S(oJa>>OV:
d^jnL1h:\q8]Ai8CFmQ_B#/sZn8k<VDWG&==q(T.^4!Ml]AfEDq0Un!0'>^sK21=$&Ljc5`RO]A
Z_<$*oE\pn&VY<e:hf&!u]AGafmA.UA4kORMhU!rP?/313.TI,`0t=^8[+!(O;&/g8q+eRO5.
3IH@%*TlaT0g*'h#"U)Ne<aPNAX9SEL=X#tiNh[T,o.PKd^C&apaJ0DA*ofBJE5q#_eiFW#e
%POQJb4c<kS?P_1'7Q8Nf[@-YlhQoSES.d0oeL-nqioaQ2HqX':ZR]A`K=Di%0.`(DOBEC$Bb
/fY`#01[aAL_0M_9#Wq+BQ4oCN4br?hfsf*M.[6tVn&)[XMGW-l$T+o^`ADo+TG)?gXg=Y@\
RYA<joW8Cf9@Z,YCAFP8ec?m[$[$iiee0hGB-Ot*=8+]AOlN%[n0[m$Odm`3o+PDZq1KF$'^U
\F0:QY`P8,UK]A<T*1\%/YpCtCQk$3J0&uQI,H$Yj2u&HUPFs(oV,+7\#+/2Ku)Zt?W]AQ7G6(
Q)Y5c'9BXg>TFf",rOWd;@7gdd^#rj(@WitdIUcqg?MM&Ug=1)@jUdH(lOU>eW#OCd\J`!$#
U$96?iQUI$dUR"-masV8^#ZZ-QT-!88mo;AGK(&$d;Uh7-usfn[pFVd$sS=<gl#Y_1=(>FUu
@591P6jbU@Zp@@b)oIlkY^V%]AI72YlbK3J&!,T%3_l5]A11K#[IM>m<JoS`X,Q!*@h\14:%#[
3^3Y2W?hF9X<CoZA6dkT$q$A>Xn-2Rr:-D@Ul6uYZM/tnfX#"'sd6I1gjM+o[,JNa)P9G0h2
?^O:!1Rk;+.n6j52u=H^ZAV%j[[l4iHT$?k]AB.0Xs%@OGo%AI-iIW$g6lQbd))qr6jJgqa^a
L91]Am:19bUU2Nl]A>,%23%VYV6ZZjo5N4+?^NCMYe%V=8+3&+0pa&eHNQ:4-3`;3d1"tb(k#d
rr3c1S'V*@~
]]></IM>
</FineImage>
</Background>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report5"/>
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
<WidgetName name="report5"/>
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
<![CDATA[1463040,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[LOGO]]></O>
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
<FRFont name="微软雅黑" style="1" size="112" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p>;cgEb0i%3=1=Ne9$3P93X*u(,9j;"'_2u$+Xb04f1KHdI\$UdV&ZS,aaAZ_TluY(1HS
qAb8D4P`k>R0P@YRAcqG=,('!W`H+Ai\GQiI^.iK3ds_7%X5S$TYlhKB%lj7A=Qp=X6K]Amjh
"hcagYc0%!dM3nr6#65=?p=WG!!&.=Y^l85u./9!/EI/o1GWoX)Wi(;O<7]A[35!O@oT^k;OQ
@53mf)F[`Q;sgnrT9BuN0_d<N@!oa<?thqV`_0^Ub2-LH+ptsDY\EQ9'\.]AeG\0n<ds7jSnf
1.an5AQH63n!SC`63\Q&;E2\gVU&5aL>6nccnFdVY'\QZA\X`)Vm499qK4BO#fnG]Am%V'9Q1
o>mf$C]A"G:rq*[,^0s"Z1f0F9q7c'`YCHEqrkMh]A_(+\ge%=aqGaUs<:QCadX)f&(p0`]ACL&
!*@^B?:'m+2,T+^Wk`2EAEU?oU<"s6[c&m_=j"]AB[$RWZ*lg[]A>drC3-RTh,.F6mcm"8V1DR
Q49%^BpjEjUhpKf25<Ya=8$tt?W![Z/NhaZBDlbqu4d9Gn%IBlA,!X[D&m1Co;4HnDEJ'u'q
aN-?h4:k)/lZ11r`]Aj24_KNoZe'QsS_bYQZuFT#MbWBu>t=OVR+;[eAmDl=aJ;l?k$O,uO,b
`_Ik=+<juR-4QE,/mm7HY7:M9^&:ELANQ:QS-[2$1lmVq9m)pMd#m\jS_e&FW&cc)gOc<^CJ
2*.J%BmodqZ8%2AV;$$+qqoaR1T\bZjf4(kBtL^bT!$BqOPe"84&&T#51MkEdAa0(pfNkBIl
,N8=QgRL['U2o30"h^nr!`J3(4ps]A=+>P^5s.=>i*unH)2/@a$,YZ[8-dWhWf/7OQn<U(cYY
%<JA"-?cOLRVp',<Lr&XJ<OW&bg_Dr'Xj4$U.S5V_qAYf4gBa"on^Rgl,Tkc1`PEeYGWN8Xl
<?jPUU]Al=78!t#NWudqXfN]A!%Ls\05Xs8[UUm)A<t+nEFcI:Q7Z?)M)U5LY0tanKM7Ine(-`
-r`gnF:=!d,eHA*D;is8#!R6Tmppprb$D)O.f/]AfI+pk7K]AqgY7bY$A+hZ:j#n0.4bTDC+":
o5I-1DrZT@NqkpmE1cl$"/p?8;.,1qkUPU2<Zo6Y0_W</(#q>VkO2V#RVm!Y2*mTBEf(d`IU
b#*`sLo$QU2nrG9BILqQRsmAV[X0HsZnCBlK9TY7iI;]AKhorNuWS0a=;9!:Z7He,TAgQoq=7
7&+*&ON/h1S3o#S+_+tbIZc$d%N4rL:c74$mH09iC2glbrN-T$GET![D90Qaof]A2*!?>ESLZ
5_rb15OZJ@Bt`PHbm;QC'U3j.q`48Pl+dXO/.7dPY4KW.8'CT7jXq5!.2>abn8TbQu<@tr8M
Qp;'r<WEQd<h2u@1-*X]AX^_p([L]ALQX=\rg\IjO6L$!R4a%.quK('")Ag(Rl^F.atle`[Qi3
6^$gjWCL!(e;?^(+*=CkN\gYTP&4`<K&aYP5V_O2gn$%LLjsCKp;MAE7kV^j@K^iCcPlW`Ng
5Q66l,%CXRM5@klAhkjT"r[()FVXjbn?X2Q%@5Gh[UuCY>%0@E0]A.;+u7t/<-DH/X-Oqbejr
W[NG5REs$n9m5G^Ims0;10-HFI^-1-jKs+50<!E&cJmiTh1=>rMa2.1elKu*C!AEQDTK_LLM
e@3&J]APT.db^T[i9l%upK%e4[+'?YCNOj,MOe"u]A$b@`OhENnW(dE/:A,tDCtVef,<<&^XI^
OQY`_n2%QCr,EHkcggj_X7]ARa7\V1+&s2\BXtOUSO40uf,ZWB."X02c6?'\!JRcZRH)^k<+&
AfcD/*$DHT?a9&i/Z'[q&_^hdH'7h=kXk_H>$^MlT^D8<fcDDcW%G,0jbXI#DD>C`Rd<2QSa
<]A7G`i.VfWP""61m5H8,>$70F83i&kpIS<nLoD;oWNG'1Da5:^HJniVT<jn:8=38$-6(#nQk
>\EW`H49<rb.d2O,IlEO'EQ#$9TX3+L*%LEXk_t`4l<Ejnlh#da=1GR'X[la?ekol>38k(LZ
*r]A_5`N*4U6UJ/%puYP;^b]AG2;o%eGJDk)=Wsd:G7o`hKIL,PL(MAJ%+?35l#iL(FYTWCKmV
Rr<>3j<FTV_5&"HV$-.J*H_2XG:<5Tt(-'jte`R#3Zg+X1f"MWf0%H_,2BV^XL(5'Xs3julu
j'FfURT1.@Fo@^EEUt-`M289]Aaq"u[ac0[J4YuX'm0ouo(fs?V+-K8c=bP/d><(Td.:lA52o
)n11mM1DP3a&2qjjguWH$Sb24.$g?p5l^"bpUYedHU*R1pscRDKEQ>W,W$8F.Wj<#3N)?A&j
R,b+Wl3A<Td?>Q&H;MVMr9eHA/<[2Q"6UF6`?u2H/DA]AtNXVqFC/A@B<rgIhG%<Y]AX"_ii:(
EWOUa!u0o"7"O@Th?b!An>NRd20%&r&^#4#K!Yj[O!r>9p)j%l8+45(HG#?pKj#2m8XuKYJa
'WM!!)c=NEDG,shmY!_'-MWiuok.4@et2d\qXrHMdU'+NE:_BO]A:&RVllfZ73KgoYUB%.W55
Y^aLcTH]Ad1+D\MMnFndf@)pAfXY?%heuVohK!VM($$%cH[o*=7%9ss(p18:kZ)C2/c2uM/0T
tp0.(3Q%6#8V0j[Ue#BuJ$Qn(%uu_Nka]ANr_US@$PuMk8Ph]A^.AC:HH/YK,XUIC70]AN2cRp#
nVh-*KeHh`H+pN%aF;kXtN,mG^55*'t's`J9QgB&Xrm88EF[(<o*\Jd>J0!qLa:nuqR<9=h"
'Bj*<$;fLOZENAhm-Kr)(4lk0=uB>3W,)C'5!K_R=3"EoU<R7Rm_(GnX$s93>5]A5!2^SjPFI
^k9pDQ-*=n)@ANnm*3AS,?CW<+an9A7BMMGQ`Jb`UD;k,?K.i_CK0#;hL6E)T':lsS!eeEH<
5/m<0rIL/OSi'63-OF5/-f_R:iqkBUF2\+]AMS@NLL:"ohJR/T#+4D1CLhnjHj^-Y$T'tQ3b#
_N0'L1b)Tp*CVQa*IAnml1P%6.Q5)EQG/17/@QD4G&/:&[$i^V?8H)<T?apXm1dc0;$Y(dYT
C1_!'O4;DMV\J&i^h/gO)X+"I9Tl1:_AKDGhE2jIZW(:]A_FLbX053L-aa"!`bi.W6rQ%or'g
R8.q1f[407'^gd@*OtN$EN)0-lFE7om,CTW$%s]AW<n3;OLQ-*9+A8Xk^9Y76C)(kmP2lXZ(]A
>R,T4Lsai?76#fU$bZ=I9+[$?#ke<8CYH\f1%&jF^>H>eG*l]Aq=C`88af9WCg+(I]AfRcObF(
p1rZG'<\lAPE^ERUpuIC3X+cp(f$]A1AoC#>[\3!t#.3MD)-t=X^qdi]A!"@8#0YA?f7$:7m4'
JYR#K[NCTqDD.Q>f/"=a(YQ7t#-21_d)lV%Vfn1<2r/Qpg&Mqn!9bH)G"_emM#,eAQX5E\F0
c(hnbOiV2mCr-F167kW?S_+3R;?jGaA@\s\eNV3+?-(4Punj\TbbQ\SP\]AcrnH(':[)$V^+p
:(@P@Ro7!'O6&'EA^spi>7kR'Vn'*NOU/G8t]A7Gob-.0mYsV+)e\ltM5qG/na(I=Y!:=W!/_
X=='M.4_b=ZOCQS@`<CK[WB;U7mLj>YBMr4Jh=iSg(TW(mV2'!'38V).ik&@h.+Ei,rnTuJ[
M\PbE>]A^9]A>II>b%+A!crSqu7A\*aSOX`sENKajjG-N&d5eTChM[?e]A@*p5]A8egpP`3I3GHW
%V$qX3u$8MYP,l_Z/N0&Pb`E;D*IIa$\mPOC2@`oA/E!;F+D<Zn:g"kf-hYd?45-32a3NtH\
8%]A:_[/h#Crak8I>i$K=`enq`#NT8f%Z"gk_qkgPu:BeD[n,-4Z'-3<)M25pBPI(E@"X7mEK
Wc^/blY\Ro#"Vs[p^"C/U__l<(A)i4D_^Zl@H6XUqo[3WFaK(Bt0gB.i1;*a^nMh$t%dH0pr
ZmjC^G,BiSPXNm:n.A)D^3/uGQki)W'\>64su#t69Zqqd$Re-DJlRWL!?<8\17L[B((HBbpA
1Jl5Wgr/,"X@!u,>U'P?ZN0GG:ai"4/;!,Hk'[DYT!=[mD)1DBY;[f<)kDWD$2=,>/-fnaC.
n;a*-MXnS;-@dF5q3I]Ak=<9-M5/,Y7h^aBkB^oRedbldum:f>_&9]Aj\,NnF]A37JXo,r=Q!c7
7T\r+cZDFl`UtJWC`gM7:]A1]Ag4A_?9M,B-.=$Ma8,@P%()+IZ"uOK]AkRhao_oQ,Ej"Gq9CG_
Vht]AcG!U63A]AL0S-i!7d]Aq[UT\1A.2BLp:h1dcb07bN`U=G3)QKl7a'PQ)PlSV?*dR,:L*Ki
`nB=]AU)\F5./50#,K#E&%6\CS3NMVD,8KOE4%qB,VOr*87Y_$O<n$4N[!Ni88U9hq;j%A,+/
:7reNh^3:/0a`5J21"URTsXYhkH-n6q/3+gpVs656QlL?lu:0qiS+;*(]AA?#+F2cecJ"S>(V
IHO)Thha$M"K`N=oqbH0FPiZI1,b47g'F5-*Ghkt:%SC/iVVot8u`OJR\]AHM8c\Vfs2hQP[k
!4.\V\9.RFtR=5l[7.<JH:FDSggeW>,BNGcUn_]A@n<a4cQ]A:*\p3!j+M*6n$%Cg'AHStoF69
*X%f^/R0E0#S0BQA'8$Z\0Ol!):+,4@"[h1&0\1]A7ZX3HOrahj#fi9k^U#J4ZZf8)H,(Sge7
<"6k=tce^[MYJ4&s;_S*i1)KKuq"+/BEIV=l.@ERa#6)7]A.1IK!djY_rI\U_ruGr=u92\Kk^
dY:N::nBg2F;Eg7eipEK<V=g$qu.N~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report5"/>
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
<![CDATA[1463040,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[LOGO]]></O>
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
<FRFont name="微软雅黑" style="1" size="112" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p>;cgEb0i%3=1=Ne9$3P93X*u(,9j;"'_2u$+Xb04f1KHdI\$UdV&ZS,aaAZ_TluY(1HS
qAb8D4P`k>R0P@YRAcqG=,('!W`H+Ai\GQiI^.iK3ds_7%X5S$TYlhKB%lj7A=Qp=X6K]Amjh
"hcagYc0%!dM3nr6#65=?p=WG!!&.=Y^l85u./9!/EI/o1GWoX)Wi(;O<7]A[35!O@oT^k;OQ
@53mf)F[`Q;sgnrT9BuN0_d<N@!oa<?thqV`_0^Ub2-LH+ptsDY\EQ9'\.]AeG\0n<ds7jSnf
1.an5AQH63n!SC`63\Q&;E2\gVU&5aL>6nccnFdVY'\QZA\X`)Vm499qK4BO#fnG]Am%V'9Q1
o>mf$C]A"G:rq*[,^0s"Z1f0F9q7c'`YCHEqrkMh]A_(+\ge%=aqGaUs<:QCadX)f&(p0`]ACL&
!*@^B?:'m+2,T+^Wk`2EAEU?oU<"s6[c&m_=j"]AB[$RWZ*lg[]A>drC3-RTh,.F6mcm"8V1DR
Q49%^BpjEjUhpKf25<Ya=8$tt?W![Z/NhaZBDlbqu4d9Gn%IBlA,!X[D&m1Co;4HnDEJ'u'q
aN-?h4:k)/lZ11r`]Aj24_KNoZe'QsS_bYQZuFT#MbWBu>t=OVR+;[eAmDl=aJ;l?k$O,uO,b
`_Ik=+<juR-4QE,/mm7HY7:M9^&:ELANQ:QS-[2$1lmVq9m)pMd#m\jS_e&FW&cc)gOc<^CJ
2*.J%BmodqZ8%2AV;$$+qqoaR1T\bZjf4(kBtL^bT!$BqOPe"84&&T#51MkEdAa0(pfNkBIl
,N8=QgRL['U2o30"h^nr!`J3(4ps]A=+>P^5s.=>i*unH)2/@a$,YZ[8-dWhWf/7OQn<U(cYY
%<JA"-?cOLRVp',<Lr&XJ<OW&bg_Dr'Xj4$U.S5V_qAYf4gBa"on^Rgl,Tkc1`PEeYGWN8Xl
<?jPUU]Al=78!t#NWudqXfN]A!%Ls\05Xs8[UUm)A<t+nEFcI:Q7Z?)M)U5LY0tanKM7Ine(-`
-r`gnF:=!d,eHA*D;is8#!R6Tmppprb$D)O.f/]AfI+pk7K]AqgY7bY$A+hZ:j#n0.4bTDC+":
o5I-1DrZT@NqkpmE1cl$"/p?8;.,1qkUPU2<Zo6Y0_W</(#q>VkO2V#RVm!Y2*mTBEf(d`IU
b#*`sLo$QU2nrG9BILqQRsmAV[X0HsZnCBlK9TY7iI;]AKhorNuWS0a=;9!:Z7He,TAgQoq=7
7&+*&ON/h1S3o#S+_+tbIZc$d%N4rL:c74$mH09iC2glbrN-T$GET![D90Qaof]A2*!?>ESLZ
5_rb15OZJ@Bt`PHbm;QC'U3j.q`48Pl+dXO/.7dPY4KW.8'CT7jXq5!.2>abn8TbQu<@tr8M
Qp;'r<WEQd<h2u@1-*X]AX^_p([L]ALQX=\rg\IjO6L$!R4a%.quK('")Ag(Rl^F.atle`[Qi3
6^$gjWCL!(e;?^(+*=CkN\gYTP&4`<K&aYP5V_O2gn$%LLjsCKp;MAE7kV^j@K^iCcPlW`Ng
5Q66l,%CXRM5@klAhkjT"r[()FVXjbn?X2Q%@5Gh[UuCY>%0@E0]A.;+u7t/<-DH/X-Oqbejr
W[NG5REs$n9m5G^Ims0;10-HFI^-1-jKs+50<!E&cJmiTh1=>rMa2.1elKu*C!AEQDTK_LLM
e@3&J]APT.db^T[i9l%upK%e4[+'?YCNOj,MOe"u]A$b@`OhENnW(dE/:A,tDCtVef,<<&^XI^
OQY`_n2%QCr,EHkcggj_X7]ARa7\V1+&s2\BXtOUSO40uf,ZWB."X02c6?'\!JRcZRH)^k<+&
AfcD/*$DHT?a9&i/Z'[q&_^hdH'7h=kXk_H>$^MlT^D8<fcDDcW%G,0jbXI#DD>C`Rd<2QSa
<]A7G`i.VfWP""61m5H8,>$70F83i&kpIS<nLoD;oWNG'1Da5:^HJniVT<jn:8=38$-6(#nQk
>\EW`H49<rb.d2O,IlEO'EQ#$9TX3+L*%LEXk_t`4l<Ejnlh#da=1GR'X[la?ekol>38k(LZ
*r]A_5`N*4U6UJ/%puYP;^b]AG2;o%eGJDk)=Wsd:G7o`hKIL,PL(MAJ%+?35l#iL(FYTWCKmV
Rr<>3j<FTV_5&"HV$-.J*H_2XG:<5Tt(-'jte`R#3Zg+X1f"MWf0%H_,2BV^XL(5'Xs3julu
j'FfURT1.@Fo@^EEUt-`M289]Aaq"u[ac0[J4YuX'm0ouo(fs?V+-K8c=bP/d><(Td.:lA52o
)n11mM1DP3a&2qjjguWH$Sb24.$g?p5l^"bpUYedHU*R1pscRDKEQ>W,W$8F.Wj<#3N)?A&j
R,b+Wl3A<Td?>Q&H;MVMr9eHA/<[2Q"6UF6`?u2H/DA]AtNXVqFC/A@B<rgIhG%<Y]AX"_ii:(
EWOUa!u0o"7"O@Th?b!An>NRd20%&r&^#4#K!Yj[O!r>9p)j%l8+45(HG#?pKj#2m8XuKYJa
'WM!!)c=NEDG,shmY!_'-MWiuok.4@et2d\qXrHMdU'+NE:_BO]A:&RVllfZ73KgoYUB%.W55
Y^aLcTH]Ad1+D\MMnFndf@)pAfXY?%heuVohK!VM($$%cH[o*=7%9ss(p18:kZ)C2/c2uM/0T
tp0.(3Q%6#8V0j[Ue#BuJ$Qn(%uu_Nka]ANr_US@$PuMk8Ph]A^.AC:HH/YK,XUIC70]AN2cRp#
nVh-*KeHh`H+pN%aF;kXtN,mG^55*'t's`J9QgB&Xrm88EF[(<o*\Jd>J0!qLa:nuqR<9=h"
'Bj*<$;fLOZENAhm-Kr)(4lk0=uB>3W,)C'5!K_R=3"EoU<R7Rm_(GnX$s93>5]A5!2^SjPFI
^k9pDQ-*=n)@ANnm*3AS,?CW<+an9A7BMMGQ`Jb`UD;k,?K.i_CK0#;hL6E)T':lsS!eeEH<
5/m<0rIL/OSi'63-OF5/-f_R:iqkBUF2\+]AMS@NLL:"ohJR/T#+4D1CLhnjHj^-Y$T'tQ3b#
_N0'L1b)Tp*CVQa*IAnml1P%6.Q5)EQG/17/@QD4G&/:&[$i^V?8H)<T?apXm1dc0;$Y(dYT
C1_!'O4;DMV\J&i^h/gO)X+"I9Tl1:_AKDGhE2jIZW(:]A_FLbX053L-aa"!`bi.W6rQ%or'g
R8.q1f[407'^gd@*OtN$EN)0-lFE7om,CTW$%s]AW<n3;OLQ-*9+A8Xk^9Y76C)(kmP2lXZ(]A
>R,T4Lsai?76#fU$bZ=I9+[$?#ke<8CYH\f1%&jF^>H>eG*l]Aq=C`88af9WCg+(I]AfRcObF(
p1rZG'<\lAPE^ERUpuIC3X+cp(f$]A1AoC#>[\3!t#.3MD)-t=X^qdi]A!"@8#0YA?f7$:7m4'
JYR#K[NCTqDD.Q>f/"=a(YQ7t#-21_d)lV%Vfn1<2r/Qpg&Mqn!9bH)G"_emM#,eAQX5E\F0
c(hnbOiV2mCr-F167kW?S_+3R;?jGaA@\s\eNV3+?-(4Punj\TbbQ\SP\]AcrnH(':[)$V^+p
:(@P@Ro7!'O6&'EA^spi>7kR'Vn'*NOU/G8t]A7Gob-.0mYsV+)e\ltM5qG/na(I=Y!:=W!/_
X=='M.4_b=ZOCQS@`<CK[WB;U7mLj>YBMr4Jh=iSg(TW(mV2'!'38V).ik&@h.+Ei,rnTuJ[
M\PbE>]A^9]A>II>b%+A!crSqu7A\*aSOX`sENKajjG-N&d5eTChM[?e]A@*p5]A8egpP`3I3GHW
%V$qX3u$8MYP,l_Z/N0&Pb`E;D*IIa$\mPOC2@`oA/E!;F+D<Zn:g"kf-hYd?45-32a3NtH\
8%]A:_[/h#Crak8I>i$K=`enq`#NT8f%Z"gk_qkgPu:BeD[n,-4Z'-3<)M25pBPI(E@"X7mEK
Wc^/blY\Ro#"Vs[p^"C/U__l<(A)i4D_^Zl@H6XUqo[3WFaK(Bt0gB.i1;*a^nMh$t%dH0pr
ZmjC^G,BiSPXNm:n.A)D^3/uGQki)W'\>64su#t69Zqqd$Re-DJlRWL!?<8\17L[B((HBbpA
1Jl5Wgr/,"X@!u,>U'P?ZN0GG:ai"4/;!,Hk'[DYT!=[mD)1DBY;[f<)kDWD$2=,>/-fnaC.
n;a*-MXnS;-@dF5q3I]Ak=<9-M5/,Y7h^aBkB^oRedbldum:f>_&9]Aj\,NnF]A37JXo,r=Q!c7
7T\r+cZDFl`UtJWC`gM7:]A1]Ag4A_?9M,B-.=$Ma8,@P%()+IZ"uOK]AkRhao_oQ,Ej"Gq9CG_
Vht]AcG!U63A]AL0S-i!7d]Aq[UT\1A.2BLp:h1dcb07bN`U=G3)QKl7a'PQ)PlSV?*dR,:L*Ki
`nB=]AU)\F5./50#,K#E&%6\CS3NMVD,8KOE4%qB,VOr*87Y_$O<n$4N[!Ni88U9hq;j%A,+/
:7reNh^3:/0a`5J21"URTsXYhkH-n6q/3+gpVs656QlL?lu:0qiS+;*(]AA?#+F2cecJ"S>(V
IHO)Thha$M"K`N=oqbH0FPiZI1,b47g'F5-*Ghkt:%SC/iVVot8u`OJR\]AHM8c\Vfs2hQP[k
!4.\V\9.RFtR=5l[7.<JH:FDSggeW>,BNGcUn_]A@n<a4cQ]A:*\p3!j+M*6n$%Cg'AHStoF69
*X%f^/R0E0#S0BQA'8$Z\0Ol!):+,4@"[h1&0\1]A7ZX3HOrahj#fi9k^U#J4ZZf8)H,(Sge7
<"6k=tce^[MYJ4&s;_S*i1)KKuq"+/BEIV=l.@ERa#6)7]A.1IK!djY_rI\U_ruGr=u92\Kk^
dY:N::nBg2F;Eg7eipEK<V=g$qu.N~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="23" y="17" width="93" height="38"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    //获取报表块宽度
    var wid = ($("div[widgetname='REPORT4']A").width() - 17) + 'px';
    //重置报表块宽度
    $("div[widgetname='REPORT4']A").css('width', wid);
    //获取报表块高度
    var height = ($("div[widgetname='REPORT4']A").height() - 17) + 'px';
    //重置报表块高度
    $("div[widgetname='REPORT4']A").css('height', height);
}, 1000);]]></Content>
</JavaScript>
</Listener>
<WidgetName name="report4"/>
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
<WidgetName name="report4"/>
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
<![CDATA[1713600,1523520,1560960,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[1066800,1728000,2880000,2880000,1728000,2880000,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" cs="5" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$name + "实时销量"]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="3">
<O>
<![CDATA[序号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="3">
<O>
<![CDATA[店铺名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<O>
<![CDATA[销售时间]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="3">
<O>
<![CDATA[分类]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="1" s="3">
<O>
<![CDATA[订单金额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="5">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=&C3]]></Attributes>
</O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.ObjectCondition">
<Compare op="2">
<O>
<![CDATA[7]]></O>
</Compare>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.RowHeightHighlightAction"/>
</Highlight>
</HighlightList>
<Expand dir="0" leftParentDefault="false" left="C3"/>
</C>
<C c="2" r="2" s="5">
<O t="DSColumn">
<Attributes dsName="明细表" columnName="店铺名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0" order="1">
<SortFormula>
<![CDATA[C2]]></SortFormula>
</Expand>
</C>
<C c="3" r="2" s="5">
<O t="DSColumn">
<Attributes dsName="明细表" columnName="销售时间"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2" s="5">
<O t="DSColumn">
<Attributes dsName="明细表" columnName="分类"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="5" r="2" s="5">
<O t="DSColumn">
<Attributes dsName="明细表" columnName="订单金额"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
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
<StyleList>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="1" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="112" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<j7c;cgQ=f$g=pX\@JFX^a6^W-+2+Q$VDTN4l&7<SBDN&X'M$Ll-_':Ne@mN-/<hN"q.J+Z
a=0'dSFJ6qDK!lNKn28.Zu",Uu+d+`,5b,U>AhVru]A59]Al5RB4elMlp"$C4);NVXnR8#kBYY
dT>/,^rS03DHY1Eb.F5,EpFY0b.FWN?VEFD>;nmn`1?6GS#ud]AHrcRU4C]A7FTgnXtW?<3D)3
iG^2Y:m4F@85EOUckub=d"6!*V\Vf+92=8DJX,`p+,A#CMu/?Fo8JG^FAsh1]A(J27ANR`]AX`
7QkY"]APSGj\u6QK%h)flKlR,]AY<eBp`t8nt*i6C3So&XfD6lYigr7AC$Ko)uo(/n:7)DL^@f
o%AF^e+mHk)>o&eO6.9<`YXM%/V)e.rSFqbKT+j&Gl1HBBN"fd%baZZ>YpJA_42;%]ArO1#_,
`b5rcUrVRH2UoW#HI1e&TLc0r]AtU=4\<*f'jbSp@L:^M?qrDSgKN"A2&IH<$O&/muc/R.A=(
@CPeTZ"`mNrG#A#5HWRAT(Dh.$mIC%Z-.CtFq1E`6LH%>2j3Ybl=+dWEJ<;QK/qeZb6@d&/$
rM!A++S-\TI@BE?P^;]A$?:J-9adf$OFP#-]A/#^KB!a#,,\6-_ZD=#Gd'4nsX38i;L$MW\^A]A
HlQ!-Wm*'L2*4-VcK8H5&EC:15VCsqMPd`A8S3pBgO.fA4s-<Ab_an^[Xj,S#`0;*a"01ASN
DSku1']A&$UM<\4e<Y[N71"BZ'r?gMhR7gG$VEKl%m_54&oBaUY3JkI-]ArfVt?$5E,+WRKYE9
IZA4I2I+Nf+2/"Y4E:-jt.eTP3tj#Z=^ZGVB)?;O!Hp>M,PgQ(-EH:#EPJ:oZfH>ai5dM:::
$[?5+h&[lm+gNMO1.+a]AGZ33:l^T_u;8>u4h.M?B\-E?+T6KnBZcf\j4-?:$Cf$c-2b/_)$"
'W^Ia]AAeMPc,&XbO<8c]A=+%M"WhVUb;JiP;:[lRnTnG640HD.7&.JuApie`;N)Y\PFAj=2+(
`FFJ<kjlY3'1o;/+X<cM,h#`hii?;"G0J[7tQi[Z&XO6PLiQka3"Ascc)mJIB\M+l[?))d<$
b]AO?5ZoW6_!d]A>>NifD='GV3,&-@#FELuq:3A.=E7lp:MA:q'*CT&n%#YQ2FU^Guk=@]A(<a%
lSPj&p[`7Ssf7Mb4JQ6\T=.b!rsA0n(+=gtB(gjmQtMkuS[+M,K[afi=T,Y(C<d`At*m`>.f
U)/2Gt:L1qnlF.0W?g+]ATjP.P%1T?5#O[`h6(T_RFq[?TE&A/QgZ_HI.o9[/48S=C/[).m)b
6D/-9@pUW-+e5n3`K1)d8Sl+Rl"X]A=DY9BJML,THa=idmW^qjl=n22]AVG.442[q*^L''O_n:
"R`7o&,aSo&$[EQ)Ok-6qLQ2iWCHVJ]AY`gPDYjOB%4Bjg8Yms]AgmZ`EQlj]Atm9hdlHG*Qhs]A
fDat0>6[2c93-Q15K\D^`CS0s0ZdPLfK38e]AJV=.N?=O4))jdRV`rs3)Qc>R9nD3+\N3R9DY
Vn?3Po7/blr*m]AFLRQkH/7e_4U%0j;$j82]Au"I"p:d]A55t`$`c[^#du/\c1\4b/0@H"<M5",
,#8<0lpM8ahWURDBHU:Ue_q37"a+H[&TF:d[")U'DQ6k0iT!.(XYT_$!8n@P>!#:i>^g_pS`
:);>/9]AU.,d5tUW:RR#=dD6Bi5jaAF"<!+Z'f?`#IT_58_;G-EK@ZdU1M&&\b46SKu^(E[k7
4@#]A1sulcXUD$6c8ZDh_!<Tl0!.ATX<6)sD*n9N96/HZF&P&j`_sX9mKD5A"qS.30$,,fA=W
Hp^9kfo5hpmS<k-:P`l#<c;1%7Em6tY<r:q#<EgZY2$2Ef7>ik9fbC'Kb?,?9\F[ZA\&&E_A
ZYdiH1!X[4s$%F[o"t"Ku!7meZlX]Aeq!s-W6E>I+[da0`\qfFRaQ;CFqR:H>+cB8@V?LjPak
iAFLCLnW;/aEo.We^ejC%=_d\kIdD/nej?O1_fUem,:P5q"B9R$^LdfoceA:$e_1SNX8#!]AT
!"jGi1d:T%a3g4(LdhVYsWV$?e3uD4j1<BA1pT_9t1De8l``%,k['m1DR#"E]A$i`%E4:e?S6
+i9j!)]A"h8F*&-3I%WV8;\%JI"/75EnLX53_01pW:70HCJ`F>L9bD.B+%3f/Y*riq4XPZ6^?
FfHZ"QipP222'oJ?81O[fk9q1&MPcU?`l;/2E8)`>=#ZFY[\cl=]AeXePC*<ZB<.65'K'5I?Y
H+!@8H-uHIGi;pP&ju0i=\6`)jAVq1c$%,i:4e6$,CQ#W]Ak9.5W!sT2e:$%+t?q=V3cDAn-^
^9(CQJ\@p\"`ANJ:_Oi>.<Q^-AbZ[MU&6dTLYrR'CTkB\<K.Tf=9S9k;#0eUJfq/lb/*7Q><
2HXNUd-bJEg1'Z6>UUc\pE7Ne(0K2Q)<XEl/$LCQFRVVjh_IH1Bs!/Q?G!:&jA8WhaS+9WRi
)mW2n&PcEC8-bU`Oi7]A%Z6s.Y)$0W]A[<mFsbW*d+5fP3!CaPnS$PB1\u@gsN-W$/c9N['BS2
6e-,/hI^i$-PbjpU#4jlrR?l6Z>h>D=`QM;YYdO7DK)WjX;H'eXkqRH\_-:50Cps;2*)bigl
\)dL0nWob-YRcg@X"'Zp9IoP46M%iksNXJ,Pkg_VJkeX%9RXR+*e0cZ<oH-HdNTTrO8EWVcX
fLfKJPH:88U2-A+QX%#Q6%lT#e@_V$M:V-%F'lb/Hdfh'gT?U4>'nmYOi=TTKBQ6(I)!=M_,
aQWdZo]A*l<Yt&5rh5n]AFGg^GhQSZ4Nkj!#JAoUG0@W=@ldTYHYp/RJJ8Y3CghkS%dL9U!B%Z
DN&.N5dhbKZAB@T%i7B3U\q<Tb)L`5a'=-V^3Xr;`#hWl)^U<5^/W/UWVNFk<iCXDLoY1n[f
@LNY-U>,`<_0G?NM'-;hNmb&SEhq@Cgoa2^$qZ4]A$L8JL*#O=+1R,p?oueIj3"9>E9rX'BVb
rnRU*rn4?1>H!#Gq#jCNQQ16K5Jeb$t:BI3DhZ^>RkBeH,#K`Fb'=QIrQAao'FdNRI>L[Wgd
o;HN;i),.DT6VQ(FN_j&ji>5&-;7CbXLF2YFk*V!5cd#2*&0F&+%#Id6>]AHq!Hd5M>I9GPkC
`.g:Ti'!8?h^tjU^,.Fp?g3FTrAqhqCc)S^6S@@),ft*dJ(2`)<]AH)KdekD;CZe)2h5Zd:h@
8GNDZoQDHMP44_KXMAG#tbYhLbo8iT7QoA$^7<c*j"*3.jJ-Kp`/Ua%VY%47Dq\R1[Mm9'aW
TkmGW_jWA1N:5EJV;'pCZ5g#`nNMq?hWZ3R$&QTX5qH@cM0)`G\WuR"iPXr/Hee-=!5=97,U
Z"%Z?XrFhmYQ5O4HeW[>s!]ABd'p;jeeF^_5\/<!O9Ct0-X.g74-.14=.>aZ92U1qopdJ=@6E
,LA3m2iJEr'GE,Ch"q'?LH)2rmf"kdFru4/.fd&hsA,H9VC]AC^Gap+Bm?@A'Tr1P8sEP-UMB
0lCt>;K<dQ(lp_=EjRE7:!?K6N1s=EPrDa'Nqs9b1`PPe_,AGQk2O,m9')20Vl76@9o1fP]AH
[7&S?jj-ae"^s.u(KGl_%&Q;!^rh?aD_5N>Eu5nh,;HdeK2[<S(g:-)7,a+jN?O>`Kul(96d
jb("92H@V68utWaLgkgj,,KUX&<M'G^TO5jUa%]AXSNRP5kEWVpCmfmnA.C]AD7IC@]Ap.edu(t
>%Ln*Rs7]AM5EOKi2?YH5GCscnJ"k"R0Gmeu4[U.=F<O\Jm:>3,amhZHV!i`lon;75dJd4&K'
YE<MKq;e:6_nG4$NrqS5+_g%s[UtoN2W84Q"TU_*'oOP8Jl0GaVJt2$,j*AreCoT8XX_l0lH
5+"3K:l\o%Vjki#UKT:^q%2@=.s?miKe69#Iap(2nVm8S3Bn_?W2m\=nHbs]AV$g!iIP(pnVH
Z]AdOM1ShKSYD,cnnBUP^p4>Ds\fB+1fZZYOB26_M>bLc#13Ur`:$,R&\!#0#O*=c@bHmKH;,
Z'6k>QG!^]AA^;^!Nn[hZR&DSW4W?9^%W25f1FX.$CFa/2_WfF''Es/'gl5ko;H#X?Z.Q(edo
?ckL)KW.ms_]A:2;4kdRbZ"?P:LLNg\4r`:aT+C9oP[&eV<\bZ]An_F\jQsqs*i.I<R]AeV8g%h
un"f%ZpI]ASip>PMEd0XS7Hu6X2c-8e]A'!TXn(Q$^W/]A"d7nhE3Q,i#.qgkmT#2;FLTWf(n,N
qph>)_bBH]A\b(7hY;-hg^i4O[3?E=/dW?7di$O.qdI'"r4nl)[TT^ZUWJ/4%>b<ro,NKkTj9
)e!JB[e:ng$GYNQ<WMD!`2XboF1Q5%gXbQGo/cIaX2?H_l<>1GG[`aB<kY:H4%C4YQWHc('-
W8SKCNA9RCrUYo5YIe)O4=qZk=Nja.0@[/LnC5T9Lf,m6`47e`:K\nPP@_<NS3_.j<8$F7nF
$^h<sGNNaiLB%8Ji&MIX+(bMb;ji;W/MFT9%m3"e$V+8n6rnhJ(]A`Kh7rUlb1lcA]A[%t2;0t
!&L+mTUQ3j08sO9=A>*;F\LR<L6"sPsZid.kd61QiHngu\-J$OCU'r>6!1AG%l]ATmmr?VBEd
]Aj2W0"_gH2-E$=4?`cL'->b)#Mj"Q$5al5qNdAr`+JL3_dt*jY9./BQS4k7.L>,RM$O[U1:r
L*<tmJtNr.U76C^EcU!hcgF+J4$3E^P0i$;RI20dqr3-_)@=G%p_QZP-6gJeV;`A%oJqZ0_j
;cgsto`2c"-:U;'$t0tTcnM(Hg37_niH7[ERQn`'=50TT_91,!Zil#%Vl(Tt_AF1J0Rrhg>U
pqO&3/ISdkX.DVlC7T,,-[VWrEaX>Nd$IZ6=B^X,0O6)4.;PV^2p/Lbleffo.sqhKcUQDgc<
gE`"/e5OdncGQ+3iAW`k+R2Xg4Htg!IW8N/,6Va`Y/0^cd'/>J'KAgOtA:48akKE>D'Is]Ao;
3cs-]A!1%hnJKqAR`)#V=#KqGh?SQoIGA@Sn5Z.f-3Xf0GBdIdXk,O)FaY]A"+qsV<j"B1,R*:
!5hmi2RL^$t-S&BM\06X4?BV!if+TB;,,7#,n/_(jK1VpE:&U[^u:VQA&0T^kD?<=NqTAF_K
,(Ami+rOlE)@%sN4Ki3%7YD<3gTN!`d)b1")80:@\D.2Pa_J+F`?I->H.C-`@m]A?j1tI2Sf&
(D(dfa(R@GfQL^Zr(=\=m\G2V7GE&'X15;"@4Y1=nCc,OR!d)oMX[C*maPJrDR/"q=uoJpf*
!_#\XU3#VWu21O(/fJ_*=0>`J)-Hb%I>E>t,[T(75FA4W<26b&:cN`aIr?/k-b@!p"V:a,^U
Eeu[;,<&81MJmI5H#Zm":p2-n't8442.nHMd"?s,O1r&G_F"NCQSLOG%CacWp8`aIPF%s,"s
;Gi\_;r/fpbp^+P#Gk1!;dWp8p3lOtLg!3.0lZXWRQTfbh\j*B)J956Y_Co9a`X]AEc1cBGW$
+KUlp8gklg,cbH.=A;6"'*W>iBWtiR%-A;4Ser#))Zk@6YqIc6`&\%&<tNJs?I!1R<X[2(?i
Mk\;H`3/[1J?<mM=*%S7mCrrE(Zc2R!*"I">N92[KKa]Aa1KAg:PFd3F6Vf58f5.^U^:[=4P,
3f0f<)2tp(eQmZXuNt(p"0nc<f3tNeMV)/II)>neC9R8;B"[n;epq+'3G8[Hebs_-UF:=u,O
"augq*[Cd@qKXGq)ra4ar<)NX`+,?oL3bqDNn5]AgZ.[lC=:5:XJcNURP'p%]A]AXm]A:[\E.5cP
NSqVheV5bp(,6=F@o)9M%Rq/"YY-U#Oo<)s95<dW^G;;3D#>Dt=ZCS86%/_loH*=>KoY%J>#
03/A7neS+Ke_Cf%o8IV<e'sO=p<uR[iG>.@;R!81F\8KFD)#W&OU/j^%GVlL1/1Wk,SY=4*[
[o'bP.nY`rr-G\%*:TcimY17u9Ea)qh'[OZb@F%9frQX#?\CZhC_*]Ao-e>,k%rhEOaFXDS^]A
53_&+mH]A0HTDOY=(/OOeVA-f_l&f5N(;DuA!RBt+Hs3t3bF>K*E@mGo[^3b0!e(ugKbn1ETA
Yd$]AWKql*7bdb!LI6@K<"4[UH<4:6l4UZjM>ZeP\_nWbG6X&d;7rGeIfNj,$At)fB!R&bE[-
,jKjh/bZt\Er6`PF@"Djp"Gcblg,(Eff3U['8H>MemYr#mp:?FX?)R+6C&ak1K@D8@q0Xlfl
%PG3Zb?=#f(8/*D:t1o1"Ja3E3S=m4hjn)&/F'Z0bgCgmRkF6J=O5#+gc:Qh!e#EINkf%c4K
dfohF+l/Wqh,i:e4IRoMm8=p(U#*U+haUXWno63%Q!@)T<4B%kQ:p)PtXd@'QF$,fN%p]A><j
4E8K':^#'""P-VW_\W@40?&)jOE,HW1;6?He.%j9ePuaQTTVtd7ENOR9h<$kJg3WS%;0K!%8
]AL4oG>.UqDSt7B0rWN\UNQM+jeII<QjqgpdAU)!FGphdNK_I*Z5(/7B"^HX/_3%<7VRV5,e%
I<-%RA]AjqV%ET^;'"0!OUTjCDL`SQ'<e&bWZ$m"Lk@^2]AW*r;g3h#>::h:_T9NMVIMS;a"("
fU[jS?Y0inS"^":O0YN`a=ca:kXZcdA%noG?qMGOQq'TIFE+r5X_<r:Tebe&A:9YN;uCAe^;
A_2dpLc\n$pk]A@C=Hg=4h^:"G3h]AC/1ZO5)]An^aG(rs:NSJRP>!&Mp7g6p"kpF>XTgLB*k8D
70g\GZ0mK^(LY>@ch0NF,qKRll.W"0Kl1[]AWp#^rOo69>JV(MBK-MnTRB"Mqln57kXgi("G$
f1AF534,nDC>U,*!0\ZO@AStFWK'7V9hdkUB_5qgP`ibVMo+*>1&O%CSS=Es43_nnQ<<FTe/
]Ask<2b8>OUod.r>cBGjr>@9et`+[j$Dg6#.L8nf[Yh$.0/MFOCH;BO;QM\1:#2MC7@VFi>6)
gCIY?I8:+lnsH[s_ZWgcc=!\>a\[:Wr)9+iG#QZgQ)qo;&>+QB$^?l,)lC?D+a.19J$]AJ(HT
Z!B+"A7Em"%<^q]AN8ZMS!lO+BB_1B9>:bpGI(.9PAY?--2/Ef/ISV1*2+[S+%9oENi=eoe>Z
T2L%tL;QJee!Ss7DKd-pZ?!R@oAU^LU)4I5I6r_7>h5BNYjm;P`0YQ*50p./.fUcq59lO*;"
ar6e74MYbNm8"WK*ULA1Y//e+%1%s9@amedELJWG\jD'h>u-:ATAHGe)bqUf(%ad3_O@]ABt%
(il>]A2Gg2<^3cI#L#\aFN:&%%7'GAKaqdj'5]A<G3_cY;/7>Ve\5NJ#eZ,@Y\mh9*HR5i0Q';
KC$*+(hVoG%,0X!e2r@/E3sHSS/>&REd>Z:>i"m?Y/SV-?kF/?V@JObI.ScR%!CIpmHr_[Zh
o(rWLOAIBh+[a0]A1?+adusaL67)LW[;Hd6(6E%'8&-@!p5Z;A:DJKUeQ5V^:;n+^,e6dO4A3
*0Y%EB>rYoprbrR$p<JEMM2n)!Xs(ET:rQ@T$/4@S-Uu6HO<(T6+H0euXit=QKP2PFU%Qj)e
nUr7<*,[[EJ'Oa:hqs&P\r*\Z!$DQB(g9>N=Hg,O3fio:orFlGuM1-15Y6aF"CuMl2`1IFK6
\T';WggI<$^s0C.\c[52Sm\->GTS:?>V]AE9H-dA2amlI*Z1n#L_7DmLDf#*6m4o`Df_c"Rhf
bJW3;&GbtN@8LR)BrO/O[?j$FRSTE2THi!mkl>)K3-&%f$6jO2AqgAdB@.P,TM2Gj..6(1G:
;]AiM9O.F]ANop%7$uYXON92X:boac8$*S+098M4lDinc7'7aP-NCj0e+G30i&SZ(MTE%=[2m:
->(&Ktca4sZ"[X#]A*"g=0Go7sf^;Opn.9*5?D_9E8OrRkIL2WKO/#o/1h6h7E6r]AAJ_.hBT5
4=lUTR=fl8qmW;@]A2?`ZY8Ku>dmZC/"K(&`(='XP_AU8",Ogg%'D%H*Wmr6F7%k67]A,Ps]Ah-
o\AHJ3BHYRtF?Wp;T%j4/Oe>5!A!T[C.a_Z`Fo>*<jfXM,F/(nuEUj8nZ-(p/#:gjG^&>DHP
8EEu_VSO#[,$3kgB`mlB:,a.7[31$#ap0B5Hs&k34KF$n!8t7MXO(]A^;5MFQC<&Tc8\%mPTe
u*CnI23(:!U'reWir?qhg?r`#)*uL=L^]A@-Qf%Y*j\IK_BWpVN&gAcuJrg_-Mu9GXfYBoR05
%Q*<g)Pg'c&p2`h#M*hB&rtNpX]A9Oi<+IcrX\`tO]AB*3jSj*@tE?uWoh`pK402rA^KSMn'n:
EG:a9^s0'4,-M$;HK/5B=;m7$7i1\J)a@N\lE8hD-O)1>;*&Hh)s)o'h&RgGHJm::btp6<7e
?Te`md1]AUq)^6B%AT89\<B#_?ui[1`lJ)i>M`a7b5(rl'DYT#q#HeQuA-QF^X#%>nY9'PHW-
mpKO"<QB/\G'XAaHDaed`>i`hZUVQHkG3INdh4u(ZWQ7'cs1t0MH&\6l1j"K1-9<l#_[0t&Q
sD`i.t54"-Yp]AjWH43nm,KfE=kp=Z*,#iNC['c/``JcL2DclN1jDq02U?.Om\D]A;Edr;>c[]A
4r9Z1dhCbAECfZM6dVKa&N1&`U6Zh4A<\O/=<4]A2@cU[H1c;+X$G6q0[-#^hi800mKDr2H-e
=L6D<<>LBeAUu2*#<SNBVGbN-rG!ghJlE&@I#OhRE;h@4j?@W)(T>i`Xa1Y]AB0Q=Xk"qcA)=
E>"TY(B*OLJCD2H]AST$sV$RXgaX&$4uh'cu,>qs!_f$2g$K`g]A"C3k(;6QSn5]AVZ^8(0(8Z8
AEPlM>G)F+o=q)mb9>]ANm<nX6=Gst7ieNBe59`!amafO'_j>i%='rD(\s"mddoO<RS1cAGIB
l.dAq!KKlM^So'jMiePCK3B0jkIOTQQEs09U*7Y$-!M:Ys)gg\=EjH,T$&HT,c!]A`/,es*M(
]A"@j1K!^F^Dl7\E&?/PJ7?+&@6i90:@U?Xq*L4:5[A$K*JcV-BAGNK\DpE,.cMkB-fbbOajK
/cp59JL9DO5?]AOB4q8(r1*>aHK&-3$mK^fs&.hEmKu:>rSr6_19iCP7#X[OPc`1'dnq>[)Z-
M+#I;i%Epf/?)r]A?Iilm\"?LT99Mj$4=nJH@rE<Xq>/[2^uqoG3pC^:2[GKH&WJfc!Cr^DPK
GWARpHh)[BB"`4o4+S!%%kt&#=JfY``'8pYBl-nqo+=*u5K=Sl'YE@MX:i;(8<V[*SV.hXre
K5[)gAE/oe+f(?2jZ!aa+6o*^k"dh]ATY8\WIEs\.@2]A&ZK9#e+tNB#=n5CRR0)nH%`EnBJJo
>KV*O:&+9-UX"Y_8;f]A;ub7/Dp3h)$tX-,1E3$V_c[.S<KqLSG5UUltZ]APEcD'(+9eMHs8BI
]AX]A1JuGgjXC80%E@/3S,(F3DUddkZ?TTZ"<[ZP<ZR5R[B9.POI^nNdOQILiPj-*#r=RQIrW4
!*+!m@>dUj?$e0_f?^=iOf;H+sP!-iMrCM8%3Flf;tB?lLgd'K"<r8Z\0NBjcCaD21^7n`nY
I^nP?Y)R5k;X`_m7R=`k(ZUPlo`"/7B2_,^9nk;R^oVR\F8PR#T9o<oiLqS_!.Ba).j"Do%f
.Q4]AcHnZPEs3=r3(),F-_*44m_(V_`5,M3S"a5!0/ts.#sB\"/9O^/iSGim?d6B6:Cq-^d#a
T/#9H-5L,e6@WNjH@'[DF,iGh=Ee%ik=mDBN&EkkOW!?G5"V?dYMq9WWB/M'K$tA/1\%]A7?)
uU@7I8XG!A*s)-C<mk:h!CiNJ\EE00cli^RX%0%2,gU=p]A(^0s!Un#__jr/X;]AP$e:>l<3%$
@Lgr*&7r`Sd\@u\_gle5,7XTIi@H%`0odIMl!Si+j]Afr-2(O%0@o[1_DJ^)I[g8@Z,qGl^NU
'=fWX.1DI"^g^d4]A=RV5E<Yp95mOF:+M8H3!"Z!cEMR('>H16!RBpgmJ&AEW!7J^aAFbj<T-
$jY-b[k&8RAe@r<ijTJEHM<K%,@L!8Udp!S7/G`o6CoZ:l(-"JsB)jkN`.kBrft803r^f^n;
lo.r:bH*t?;00MbOo/!#J!>r`f5IbbsZNP:21%,"_\/3>Ukn<@S+3B4.I;1()Y!C)h'*)Sd.
;f^[>4!uD'<h]A4Eji:D?2l2cQNIH=_u@=G>]AE5P!0eB\M#A-@(p;h@7gYGn?02Y6S\2Dha5u
/bZe/fu]A<1#bfRN4:;^c<Z1WZohH<TnN5i&(140dmJ,ubJl?]A[,$2(6CahHq9l;I&:b&!6Vb
6c&u_Z))h`J7;3:SXZCdn6!:$b0j;R>FHFdEX>bLn2sQ(pN!_*XPiY,s%>%9MPKkS%X]A#,qh
FQW2QZXW'Yt6%j<I"iU`eBY5od^.YTHVOE%=F*[aG#(lP2nZE:!D@CA(_0ZOCH&Lj^UHf%SB
6SB[Js;SE*Y$"[N?&89D_+&Wmc\^*$n@[XS!TCF"@qAZEZWW130%o]A*i`DOQbY)0np)Xd!Q,
sFqOIAG-8"AG[`8*Vn,aJ4+\e)m-hl9tjk*eld"dKMT=dT?hOV9fhtKSLa_qfgbNn)6IK6;E
>=d@VcdcJKCdS#H-;V>JdlS]A;-$etf`7!E1LnfeVVZ$FH(;GO*dt;.Bug+1G&-heeoc*Q@V5
c9M]AIjrY/X_'ll>!,Kb9<'nL]Ahr&,Ng<EWfCGen7]AGpPp2AcAk>6=K[J0i]AL0]A;n4.;(eKk$
q%7F23B^@O0>c$nFs-B.I-a#-('#$k/s8@!3.+"@KekJ3j!bEa1so^s!1*)PQ"k=G2(7/,!a
:T:bl`Hcug.,n;W4d61n^XPW&(*uLLiJ.iO!"t^P18_GW!*2u$Q0D-$tD*\d9<7ec1\;p+I!
%=9&">FL2m8a3NnW,\=?G5GT!</GiTQU.1eKFRd-.e'D=^bZM#1tZp5ifR!f^:pO,T^ng++R
_&Q)9a]A`8_kss5iD!Im8mD-V><[6/YY]AX:M7\era2;0/aXg!$mhiio0Ba!.,Oi_^:8FJBG4l
qRn=;-AEQEVr?(EC&$RI%<GCF!1NZDN"YftCadX_6_JH<*Hm1NYUY+DDuQm83qSmA"0H3BD:
cpaqZCDV=euko#L`3'%/qh4^cm]A436(VjJCsDr]Airj?;Zo%g:4[oG^:jNJRQ!Fcqd0~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report3"/>
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
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[D0gIM<;ZRmR5nac#%Pi5@Lstk-A>:A80k/X#n]AWu8qnn=2D]AC@9,fA([,#!/&k+">#$htm+t
>F38@EWH0>DSjf(XX]AkP+fDSF_1ocg,j>cguHID^1+Ib=5B78K7^18_`W5NW=S5!<<d-^a!b
M.NaWQJ5o1L<`Gu\\KfoB3@bU$EJ$/deXF(d9VGW:CP5oX>Wb@JD%H?d<N\/-R61pU0l4-17
R%R#b"be]A,]AToU`jq)%I#SJ?_%i<&;:ZLa1*P:*HM&%R;,[,67K;6iIkR7Mr]A2l8<"`+O)ms
qXAu)EH6;5=QWh'Ln06raO!O>Kd0;Vq'J1j@erVZE:lGIK7e;.3a^n>PLpI_L/goEB.WqeZ&
*(Ohqm!R"8V5BS51#pB"[oI\2din[_m&9#/8g!G,/_i&7_DNKTV%tmO\O,`BhYcGXE]A`gUf2
KP/HOa--D%tX9[$[Gc:Me&)Dc\3iK#gn\MS%YCd_\D[8eM>aE7%N5$[?`fs%hfMJ._Z:r<4J
B$(d$!/]A'Cjc;Vbhmi.8/c5hkPQN_q%pN@#g-nC-(^6&oD!e^8n#?^D*,MRh&AVf59jTg1[l
:<V9/)-E@Th,:+FqTdX.F.Am]ACq?+D4%Tg;m@FnEL1^tP+91!L3j'h$N:IIZg$`%EtFFY@81
\<jHdlur\UK:@9NakT$F#)J\frNGFCPr$)GAMniLo*ngCTIY.s&#^X"hq)?hqbp?e5AC%R+m
B$,]A-;@FMA5N5"'>4g6dReGu_XOW!rqh=0TZ8gr-e^d9aBqJH@:)pX_W,$KKJ6d,HpdL:D,q
i%B1>oXa5-$@&;X8#0e]AYDp]AZC8CejV'(6`ql:?q1M[&a#6^35iQQ_VuhNVfkt1YA8B)GOj'
facU/DPTjui:m;l.$i.;:7bB=N:,8$^&0&ftrR)'(Lt&r?)osXsmgcG`Gkn`@[C&LRC-+t/X
aI&Z<u")^>De!X]AkjLUa:H:1g:$?#q'2A1SlU7>Ke(g8UofXBR_q2.OGp'W,W;/A1Ee#RQgR
]A;8X34mUOrY<jM/HWk_lN*EWWMU%(l9MLrJnM:JYDN8ZPGLX74q]AmUeN_'!?+m>B"[)q=bg?
h$D`$T+K3FKL0%-X\KqdO!V"&J-rKC>OG<t8KiSsbC1?j)0J>$SF0A95mLf,Xor`=1!J'#fE
E+Xgi,^:>EFl),o(#`3`-b2g]AQm('<VQlTOn@)C(#6;$T!Q10HR1c&=LbAD"Gm"HK%[7\=u>
u2!M3hN[LN!.5/55%YT-j7]A8;S.7&?7nM$m902(jslm2:fMi0^c\L^O>XW+pWg#9Z0S7aXhX
BJe9LDc*P<3inPB_t=I*<kj;)9MWUGH=="CsCHEb9*Y@c=??drGJa$(1)]Alf>oOJYT>'!:RH
=Ga.*$OUT-56#0ULFOa%l#\&PV<Anat`ZXB2:j:QG*hYaogoRcAVCnqWEIaG5[FsLPH!Su;r
AA\?=j"#;UlKAbGW!ttPpDm8;hh@G:#8>$olXgY>=T1]A=EPtftM\f&phk$c(DN%+Rn=&:3'S
\*k;J!)--ZDgqJolqtT_h4]A<NrH7\kkmC-I;2"lF'7\f,Zb;+E9sLS#&hnSR+F8p0>7H"#DI
n&F%mHC@`CM\?WDtGDR0P_'D<oo7n,GJ]ADF,"!'Ye\id.ZSG:LNG9:UMoP^2>/PY*c?((i2S
-ou>I\@AHbp'O>:4rP35bVgGPr(RaTieCk]A"2`J3p8R'W&gML\DD"fCnr#"(S0oG`2i5W!u$
43('B0>3d71tXg\*;2O)JQRlOJ?@EdT,_HQQhT!=rG[<n_bL@/2L]A-p7j0hk^]AYtH1i-\gut
niba-J!9'$eV+5R-qp#K]AY=QBVd%,*=_O$n^^"a<2HHND3=oc6DPCGeJ=+td&GGM9^/&q/da
,e!g>@B]AWDr9ne/qfZ0m[e6Ds#H-ICSNXOeJ3Ai+[`869lU<V';@N`I)&0jlq@0S'I;$^Jsk
[C?MG>ShBgOS0Q@o<0`h8n8G7n=@_$hLDbLJ=9@5o_)Q>O(#$sl_7Le2gLf(b=(g]Aq'SC2)"
"Zpc%9R(cI@,Z-h%S!)I+F9;_QO7SI0gN%c?&8A:s^MA`&>O-YOIe_Cn4@k$5=.Ig[]A`KL%s
<gCh`$NR_uZ-io5#[OXAcr]Aqhr%%!V#o4=a"sP<%$"(3qsgI%[4#Z[n,npgSm=%hTd/a>s>_
oS7*r3.P\3,'K*!13fT_d=]ABLhp5YY:,H0a`QHGhTbFtK]A[NeB*$n8f5a[bSB',Wrf-hC7gX
chTjEh_)#7u/if`#:07M8HRHQ[WIl7Q4YPXO71G@m&+:#DcpMc^bK!*c!c!gKe'7pNc&8Y=R
41bjt0hVIaNbIVohqS'`9\GF0n6D`+;[=i?X-Fo0aJk.(P5hob(g9$,<@*;cu;)2R<DVJF\U
8$\pn3,<ZX7=ETj]Akg$gCjeZKJJ$]A$_4U+e:?jc-Zh6'>*)G&"ITA2S$%#bJ2JTA)rA^p&sl
bZXYdNeRg'43k6_$R3:_d4/B:_G^X.E7eTl0CidH&T1qHsH-I(VnGfa@PmL:2^DA?P>SSOod
qFf>@98:r77tAM>j'l\P<>(JL-d9n/G3dCdh\/nGF"!j`^l/)u_?p:ndh903:$Ji@/3+.%EZ
3"UD%']An3"-9=Kg.k^2K;ta1Y)h4^j)X?DCA6"@@76@pj2VrhGu)?D"/OfOGPQ[F"G05EQks
E1N!;=l'$[d(F$)Ud%$KK]A4!\n"u_"AmK.Kn?f[nU\>2A$B@YQ@!2>k/\;r%Z)\0CAkT4B+-
13uHH/=\o='9Ou<!X*f'@`qs&OBD@5Ru+t#pUPh"+Cg&OXG]AKRV\!7U6hJf1duKX-K3Bo;qY
\n9t'gn3ibr<>t3O3+s@/G9e?!i[&;l.9hC_OLTC^Wdl0$*g5$s3ZGLhf4R2/=4R-;;a-04^
r4&#N#q)iIAf[$<5Erp[O@#LHASaat$NT8ZeF>t,c?j&]Afd.GGE*tc4lYA8Q>;s8%ijXep>%
kbQr3`gm=Kk]Aa#"0UTWb7dZ#oEY=@RA/c1eLMB!_;-@BJ_N3;DE7n?trt=@[3@<cQAGu+iBT
ChAm:4]At9B`f\P*:Is!g@4e/d&>>3Md]Ab?.$Oj=o<Sc&mLH9Ll;EXhs-\`W^l(Gd/iIehB.J
U#hWgrr\tipoS:RiTFW.1eJJO7"leF+9k[[8D$U(A1[e;6s-`bR_XKj"j;8b9*):Fo)@gkW@
)bGe%op%&UH)^73EFIK]A^gg_+/)nJ$7;kcDJi;eWW7jD@tU6+\>&Du:g_!5s(2MTqUNgm`eg
g0uh,j*,SGar*eU)?goJKf:XE=%4S=aFd+`g@Hj.8]A9!O=JU;mb;nFFgWJG#!fa_BJH]A^n)p
aU(MYi^hp8qYnhp\un_JhK)Z1.HESL]AII+cM>"AHGL7jiogd^nnC:?,;6.>WM^LD2)^kO]AfM
'8uRdoO)(<);VmUjnK^g*@V]ANXVrFLnJrrnfStmVqC+sPCdO/_m%6iFqoQjm)rS')8&W)Uj]A
OF#!kpR1;MZ#$>X5&R\5r*fQZG8*+^7hHHgN"B2X>>,:c5\QN-uC+30^169/9Ps-Lhg7,gpu
7IKbYCA2fi:fUG-(?@\G)lUU&gP(o.)S\_XYc!7ULM',?i-@g`oV*C!,2l5426gQodX:V3uq
V4:p=V)QrK4D`8"<#28`^!H'M!)@-;O<>Fq2SmfdXGnL$*shbHZ+aq:jHE7=,N&;4:ZmBY_%
[o@'ZL6ainc$l,]A@>gUs'h^QSiLP#6+(P^3EVFM99^B2;,DZ?SXB=?>?MB5#%[pdb#$a&t!a
dU\ZSfZ">^b`iW@J=WB#Fs4I1<nqe$lT=@:$GO!t83`f/qTiWDY9tbjGLbk".'C-mVH1<ThC
q-Md,Y2Q\P53pB-7@O=G+8>]A;!;3e4n_t).<.RFNit;R.Ha=n^%,2:fTG*HS4s)?GCm50]A^+
[qk?Th9K]A1QLUqFZJqobUj,L6QQ;"GB2G;XMcp[Y]A>=f_enj6epi+\@u:F#3o*G.#NPKAp9+
1Vl.V2'i?V'K4ok?'2t]AoQ0Og9Cc19!s$W\gr;)oS@?CC+;6B1I'OO&ji*.QT$,CJ^P<&ACL
eW"h*$om680,dW0mn.aL@gW=1[>8%UuI:C:nA`GjS!;Umh"RC6.DRr*\+G1"_u`27SS#!%$L
,64O48brV1baBo8(<,h:en]At.V]AK@C4#0^%AD/paC!s@Jj=7!0TUFbq.ReYbal!=B&oO\^"&
]AZ6.o/"pLph<$_!5M2M<77Hppplh.&##okgcNO\r^0^@?!o&gNi=2rVX6o[@^F#q9hHI[_"[
<\L>1+0CUTQiEOq*CDlpG]A!J%VpG"U3/84F@0"T+P5?q^GaiVT1S`kn(-M.QCYGlS#a'P-OI
q)nIdQnJ.`$P(gbZ/UfSa-l3e07"fU/[3&GKluTg,Bj1%5p.6s%n$WJbt-H\ei5E,M_T<QPq
)6A&4KJS[mGMQ$gek6TYSV^0ft+l]A3>c8ZVG(//l6"lp$`JL-k,:b'LBsNM+&?p+PR.K3O*V
]A>NoHp\^8H[O%POMOT"[/6r`!TWfQt#=_o<`MX\,VW7kArN?VQ>20VW%]Aspl[]A`ML(XJ?++N
NRlG-+e"MNt[Z/q3BsWOnVLl.)n(:7'!n?g_9Ru9dtI^2V4-U$h^P4)Ym/[nCG6/f$.dFG89
3N9!2O!:cS@&'.Y8?b+RECA5kqjB:_9K6[nWpMRm-'@10en=L$3]AO%t<-h;=cJ>$#<48m/,d
+,r-6I[ElJO_#Q97gX^4.4Ko+G.;bR7G)^u`L3n]A.HiD)QT<Fd6V4L+f_0^-BDsf_O/>CBjB
5_^FsYtFKZB>`5bo17UjZMLI$GG&]AUI;_@LdD-g170u.K$Y=WYS7<Jb*"Mp$fA0MEm&!(AF;
J('/Ps-e"Mti_sZP5C7dsE!At11ho&c9jVMcql)g7,1Y9'XdbMV+)M?o<[&r[G("K'A5bjKE
m"d<;o9K&[S8,CVjm#rC*4Ml>sIEQ2n`WdKPhHeKt;^EWOC4*j6s=C(TlLN[79"R$uF_2#Yi
&P[VImY<f?"kB]A<B9im1BfSdEXD.t@l;CgIa&Ca_eR*]A,tFiO5S-I3-NFXgEoi%lQ;@X3=".
I#,%$SP&d1<q4/'ebEBAqas]AJpft?jrk,P8fn5ElQIlF;$<Zr4CPre]A\qVgKSpS2>_8I_^fQ
_POJ*SjF$?oWV&rZd?0U0NHGbq=A\j?M6.mJEe,9Mo!^Khq2cRP(-d$O5ZM7*-gpP[Q03p6H
mQtQ<:()0RlXMZFjGOEqj1En6,B:HHe06%ImDnHi4htKPL7$p'`fS=pgZn7"f:.CF`pd>XRT
9=FN#=^'-'2if+R^dG=]AhPQsf"W_c5.m+Q&4AsN%/9S55?=[=UNtZ<+`&)G*C=cGG4ZVKHYh
4G;gdoM'5tIKd,gc6eI]AL93@C%nCJP`d5nF$1#\SbNi"$m<Tst#>=8p.@;',Hu=t7_(2ZcDs
h_,5W#n`'mh0t-'&_F!/@g3iK[mVtmq8W=\^'m\+)A#rE;]A1h[0QaK`$WWKK(^d$HfW]A+IN>
C)T-KJWe.(;Y!$F&@X'(RN-rAS$lr,6[17M6V+c=s5=a,I*4lJRR%`DJd<'hMhf-*`o&I[\m
f'3%-BaG!H8VD99W7P<_.$=Q:ikEa-qlgS%Q5h2[l@.nZ.q]AfReOP,Uu`rQsp8!*WP.1(kc9
dr3mG1>1/^BML8(R:CbiF8Q.qF^_T=*jPdFB7[_8!&ei=G5UFG!(Ue3^.VSOEW2Z=@2+Ks%/
(\fI'qpEem'-\3E$!aOjr,RL*o"kcR]A!-O?8%cin\.g_&Mb[4]ADW$_k[?AAC2C,_FKo0;-,_
"dG]AKjtEH^VLL'5&r^OXi/Kj)i.g5e7\6)Q<T^B:M-E^V$VZ:QGJ?Qnm[8M,#]AgG0OgRF0jj
`=i=WbJQ+e#QZ2Zp^WciNH>%Zl@DIMj[q7U]AOToV.T'dKTV:ZSLHRc`K_PWRcR*r%eAb,k)^
eE/7O!F`tGX)QW6uN:KFc3dW\oLU-qAqU-M5dsV/mf8$;&EN&oCnol#.;]A<1Te61']AE=O*`&
c.E]AA(cqOC,YZr?<WcDoZN:SO4=sB/@4S"b.HiW4&A0]Arir^G3UkB<[lY_.05eBZ;B\_pec1
0hs&G5>CN\*l4N'@-)cD\SReWt]A<7]A]A>\)?u)-n6N*O"2fiBe_XgD$rSVhmTV3gtjm6<N'$t
djRk8'F#??oOR%AT4:1ch[Or:nj>iXqp2q@\.IiZko=t9HqYC;b?6W0AB94nj=;?k@qYt("W
IF".?Tl_adfY%C``D;;\efUf-JR*2L=RGrKHIAJeLF+"CZ>55f6c@.\g8F'@=Ous""LE1-U1
d=nH3:["Tbk]A&8%#`9_KOs,,"pqrW@fK<enNAX'ftBHd#9k6NO6?_f;U"C"f$Q%1E8ZE0%p2
Q^0F:\V`"oN6trZREMH36M#$:il#Uekb2Er;i>Pc3b5U"6J#@T0bbur/3]AY)R7k=SqXa>KNS
%!A`s)YiS5Q5TN50+C.RbjI!c;G2:.*U%7;SV);s*)/!LEX,<+]A<"r'gj\3+H7UDlr$+91Um
PQ,h)//pWqp6Y6Sk(=AAEs35)PE8_g"Wsqq#;$jk*uoTEVda6pd9NORN'K",pWUhXfEku"e)
>`f.h=Q<)7Q",bMJ3bNi90shd@l;W#(KDs!%2MW5o1\Id30%Mi2!N,Q0^EKYm1<XSQgS;KU;
!fg+QPnDSKMGJu+bqQg.c(]A#Re<r\MVeniat2;&P9hDSgl%Kg7u6ca8*i89/8AZ@.G3*cepW
":#N1B5nNc-3ePo4+P/-P6&)'`O$jR%"@<%TH_=dJP]A_=,^k!TJ;>cHKrgg.#1JmNlbK)9m%
//T@+IS!qL<sCr:l)IjS?#"^EM(]A'stRAJeVh9FR?UCi6Qr:p/D<rH^I::%ZhiLO2rb0h=dE
gkC!fCj'RSkursqOWuUAI1",J-0]AnM6\cu,+;Y4SpV7-$^m).ZFWEW^\a9knc/drOaO"R]A8R
G4mcWgJX''!Saca"kBUTV\^1DC&^12=Pe?Zh%S@8uc)rEhE@V=,0r+;DH\s,-eg%3d-iT1;-
%QS/arF#Ph]AD(WKBBV>-On,u5&4m>;rV#96'7^8aOq`i4$?aYi#(tV;fm1CE'c5`dhgrj3)d
to]A%nga/O^t$`p/B=d%;f=3r59=]Atr617l\m-K/N%hLbMG2t0]A!>_Z-)d:&\]A?<;4nhM&:<9
7*H&#EUL5dU:IqfV^aA_,uB!-cWW/m9H(b>nu+JY<G9+h22,JH?)nI*.S?$%a_X&.)/HAXoC
^J1]A3#sN8S:;?q8cB/9DH@AX07F6/W)<"?tY:*(WqC\M+YtRlm'&gM=<9XN1QS>oD1DJ92%m
U:2r#U+sA8"\M/OkA4'[fmP]AR^rpg=p3X\K:&YL*P16LZ`f6;'F8Y:':-c3SDX<CH_N/5)34
k;o%?H%`fc@a+PX-c"9fiqfAH+XhM>i-BteLDf523VDiP?7]A(?ieLZqlJHglb0L$PVWHB<n"
)okddlF`M%1(UE$@_LVA<JcUo-@RL0M<UC$7!J:>8cbR1*OL>GVB6Kr49Y<_pF>hL\""5_%+
rIKL^.PEgps(6/=+s+8CAM4gE@8F?Y**BcN#ODbZ3A`e.sQkGsHch7=.d@;Jl9E9?8N-<:R<
ht2'`YgdW)=o/:K;KNb$*T=X5]AE3*JB]AJUg9<Nu/+ITY$b.>`*G#l9dn6D:lrM(M6_7\WBU(
cN0JJ=0CT%-=,j3KSeaaQf$-*UC8)G=?0Tf7\eSX.@N4Ma`!o&7[L'VG#OX4DZURj-u5$`)F
Y2Ehk<M1apZ<`jl>0_`]A;_nEc8>.V"4?]A]AST(\*p:\+3]AENkGkR:@>Jdq4JU;^ZEYHW<bEVJ
NHGid,dMJ7*<Mf7l#p_X&-liQ\sa-'SDR*a4><Rf\!9A[jGN".5jD8-nA+A>N-6a]AM@8()l3
o9)cIbaA;=rC0s/ph5d'Mon[tG"?HT1`$Ql"R3^/%$QqU&jmY+2@a;@+6EiVif-Agt>Af<^'
N@8u4L(\\Gc=`HtdZK>loa`Tq38BI08QS7L5a0)I@$,X&'e!(]A-SSIPP\@@pmim-_$!t/9kb
aFhcRKjC`dH@PWV<&moF.4)BZE]A_H9sSR('*]AHL1(\Z>X`H@`3;Z#BUHjBF.^L=5hdfQBJMQ
pVet7SgL'EK`oC4<=^2,m70<SlLWnSrO^5fX?j;27Z`WTnd[LCah(///\L\R9TH;Mia--Gem
-?mKj>WQU46%)K6)?MSR2C5pBWLPs#0't)NH)KpG>*,^8M$4\8(Ams?WR9[a>>(`\$Ik(hs6
8Mq?QR!2^`Q'g2W8fYUB=X]AB(:<i)>i-F+cU-T<Kf!-$H)@l2M"abqS`m?(6^P>L;$/$fU\X
/!7[^8:gJFUn.PNlH@SqR]AGuEc"Y=&22X\^$mkMoZH$3*Ej!rYR^]Au@G\]A,cq":tn.(V=Qnf
#P>b)(ftX(!:/qm*s@/;JX>pU[M[DaH':hVefB@dH/3D<UF7[8l?0c'j=`9*V56^16K-!V.T
[_(,gZ1o)0;LhZ`Y^-.[te;tN,gr/;8(+"g=+^$;C,J3)TA\c[ui`DaD+oPtI'k@e:BG"apR
J,DF0HeS$#fP`*R..62CN;Y@"DB+i<T;;NS#_<FK'bi\3G^H-5,JVQ?!c7OgbdSR0sEMW9>O
;ILtEaaIDcn'I`k"V7Q2fOomgB?=BjjMgO?@\JGeNM(!8&hZQksa@r^4gjAkuL3>'qVk4`hW
HV`[^VjNikC+@:p8@bc;@g9nOCM]A<s>"iU1Q7jLn$ihCum8E$.k,*aUn+t@'4%?SpT0$VhEK
HN:\O_^`$@5ZSkk4o,S*Z#H$]A&^Yao@%#>YR2L/UDdtNnS%kd>MpFs/k>Om[h1bgBQ4;4`,3
GDVsUo/FA^$=gG=gnc&NCMh=*W^Mn8X.&gR2IX+u*CJRbL9QW<Hd^6Jte;>GXi'1So,Nj_Kb
kuE$aV,!'6;;oEFiF;;]A`fNs?JRF5CcD3;cCr7@ma<Ime!^)M1)@`u(NTbceBJ\r,[391eR7
h,`(-4H#tG<g5"OEaUuKqA(;380iG3#$9ecXsc/V*WITn)t3%+/(GC%>>+)J$rYIF(;?OlMk
0XWl"\ul!nXAelPW`\fqEgaEghRiE4UCDSl+Nt24U]AK]A@h6.W$*TYQDfUW@d4-=*5$]A,QP\&
15pTEqE_hr?m*BXa9(O6I6g+,Dn\aj0I0Y:6]A\gNck4)8M:S:VZsJmk@o'7^0.P5ap5rM:H<
n)"+0jAM]A3HMQV0d7+k'#>%2<>"+QRLL9qBLN?M#r@=ZMuO'0KTXpWAj9C@g7p_@;EV0Xck%
ifA4:E(k';nKEE/QW?%376j1j>9sIDoDd,Rfe`]ACeKe,.C5QI0oEm$?6A"c%b`L78^(*:":8
nG4>*4?\7Yq<1mK2#Qkk[9SQ(r<")HVtR=8E^YI<=D_M5!47mrc.4I@b;)i['CI)ELXbc$OH
+.(l('QJ?l,ne`MC@.`r5H#Ero@`WYcOEMsWH\7t\9/`MP*fH)MSR0-p5\%d\eaf<lO+N<f#
5DEG(L,\a2$$T]A*^?YKmE=Q\#W@[!8Kb9Gp=>33lYEc/&jM(I;YQsAIEBgS_E+PrX]Ao!Ila/
NF1N:uh:oO"R/ck%+)0+Zi;2$ogR,@o9C$=XNY:L1><q@+`5pI:]A0#[DYi;Z2b<Lod/NuCm1
>^WhU`-t+lo2X3g,2S-W2pDJAeW><Vdc+HoP08g<HR"eKt_#%%KblSD,t8Wp=,8M71mCc`<k
e^55?Ppab?E,gt@P:LKZ<F:GJ`LND,u8knIBTP)]Ak-WYN^/aMt4`3fG\&g`[$CQj?EMkm-A7
nTO1T)g!G/CC\ip4NX_S'e+42fchG]AT_cn68$D'`qD.ZB-_DL;MY]A/Gf92fZ?6#OgrBjIU45
C(AhiU>>%*G'QNuX:(%R?`44)cJ)3:N.1YICN=D8iB4%%!;eQjQHa^2)K"p<\p6Z/e`'C4U!
bi3bZ";k[tUd:-6k6"<+U6qi2d6I,>eZhEYL;_ZHnheWL_ZupkCRY:l.&PH^TIGcNqM`k^SG
NoTEf'Kk$[f[ruf'Vq*+)%IQ=bm4=>EE_9ZG1;#-C\eE@f[Ki/nKMa)16@>:VpsY/`f(&;i/
gfe=H@B9BDp(!FoIglFh,uS='r)+iDV`AG+gT?#7?o6h5Jkm.<0<.Xp,!LAKh;ls?@=(mFBE
ri8PkVgaM86nEA[q]A7ag1eF%;^HeCQCofbJYh;cJY_Eq>dN`&'m/khM*a#OnL0b3K5>*.Yh&
A!Y[<T#)VYc(.B0\kpEKRI"]AJmlQ>\!+?LFonVAk'M7KZRSH<>N3]AJ+L;D;b5qorOrbmA8A>
8A_c,]Aq)$A`I,5FLB]A<[l+I,cj`*=PsQ<WR54jW72LknL>+Q&Kmp_BW_D;.YMV$(s>el7)+#
;i(*,-lBR?bgUT@,I0B4-=Haokn4N3I.3%3(,TcOuGHnR]Aep#I*HHBfgW[1=-4=/>M561hl'
Ok9CDSfTEYBV\mHW#:6NS_iUXdSB\S@(%i",kMLjOYWcIWN+_;if7iH5g=mrPA2`d'[\a2t#
p$(!K6hg!ITd/6%l.<\#N2#AALln^!EW5XUK:DW&#W(l'F5u3DCR^l<KW2SUh)#&W?-0H:0W
8Vu)?<IB.-!tgD=I;]Ar5(>4ljq`dgLXAol,EXO~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[D0gIM<;ZRmR5nac#%Pi5@Lstk-A>:A80k/X#n]AWu8qnn=2D]AC@9,fA([,#!/&k+">#$htm+t
>F38@EWH0>DSjf(XX]AkP+fDSF_1ocg,j>cguHID^1+Ib=5B78K7^18_`W5NW=S5!<<d-^a!b
M.NaWQJ5o1L<`Gu\\KfoB3@bU$EJ$/deXF(d9VGW:CP5oX>Wb@JD%H?d<N\/-R61pU0l4-17
R%R#b"be]A,]AToU`jq)%I#SJ?_%i<&;:ZLa1*P:*HM&%R;,[,67K;6iIkR7Mr]A2l8<"`+O)ms
qXAu)EH6;5=QWh'Ln06raO!O>Kd0;Vq'J1j@erVZE:lGIK7e;.3a^n>PLpI_L/goEB.WqeZ&
*(Ohqm!R"8V5BS51#pB"[oI\2din[_m&9#/8g!G,/_i&7_DNKTV%tmO\O,`BhYcGXE]A`gUf2
KP/HOa--D%tX9[$[Gc:Me&)Dc\3iK#gn\MS%YCd_\D[8eM>aE7%N5$[?`fs%hfMJ._Z:r<4J
B$(d$!/]A'Cjc;Vbhmi.8/c5hkPQN_q%pN@#g-nC-(^6&oD!e^8n#?^D*,MRh&AVf59jTg1[l
:<V9/)-E@Th,:+FqTdX.F.Am]ACq?+D4%Tg;m@FnEL1^tP+91!L3j'h$N:IIZg$`%EtFFY@81
\<jHdlur\UK:@9NakT$F#)J\frNGFCPr$)GAMniLo*ngCTIY.s&#^X"hq)?hqbp?e5AC%R+m
B$,]A-;@FMA5N5"'>4g6dReGu_XOW!rqh=0TZ8gr-e^d9aBqJH@:)pX_W,$KKJ6d,HpdL:D,q
i%B1>oXa5-$@&;X8#0e]AYDp]AZC8CejV'(6`ql:?q1M[&a#6^35iQQ_VuhNVfkt1YA8B)GOj'
facU/DPTjui:m;l.$i.;:7bB=N:,8$^&0&ftrR)'(Lt&r?)osXsmgcG`Gkn`@[C&LRC-+t/X
aI&Z<u")^>De!X]AkjLUa:H:1g:$?#q'2A1SlU7>Ke(g8UofXBR_q2.OGp'W,W;/A1Ee#RQgR
]A;8X34mUOrY<jM/HWk_lN*EWWMU%(l9MLrJnM:JYDN8ZPGLX74q]AmUeN_'!?+m>B"[)q=bg?
h$D`$T+K3FKL0%-X\KqdO!V"&J-rKC>OG<t8KiSsbC1?j)0J>$SF0A95mLf,Xor`=1!J'#fE
E+Xgi,^:>EFl),o(#`3`-b2g]AQm('<VQlTOn@)C(#6;$T!Q10HR1c&=LbAD"Gm"HK%[7\=u>
u2!M3hN[LN!.5/55%YT-j7]A8;S.7&?7nM$m902(jslm2:fMi0^c\L^O>XW+pWg#9Z0S7aXhX
BJe9LDc*P<3inPB_t=I*<kj;)9MWUGH=="CsCHEb9*Y@c=??drGJa$(1)]Alf>oOJYT>'!:RH
=Ga.*$OUT-56#0ULFOa%l#\&PV<Anat`ZXB2:j:QG*hYaogoRcAVCnqWEIaG5[FsLPH!Su;r
AA\?=j"#;UlKAbGW!ttPpDm8;hh@G:#8>$olXgY>=T1]A=EPtftM\f&phk$c(DN%+Rn=&:3'S
\*k;J!)--ZDgqJolqtT_h4]A<NrH7\kkmC-I;2"lF'7\f,Zb;+E9sLS#&hnSR+F8p0>7H"#DI
n&F%mHC@`CM\?WDtGDR0P_'D<oo7n,GJ]ADF,"!'Ye\id.ZSG:LNG9:UMoP^2>/PY*c?((i2S
-ou>I\@AHbp'O>:4rP35bVgGPr(RaTieCk]A"2`J3p8R'W&gML\DD"fCnr#"(S0oG`2i5W!u$
43('B0>3d71tXg\*;2O)JQRlOJ?@EdT,_HQQhT!=rG[<n_bL@/2L]A-p7j0hk^]AYtH1i-\gut
niba-J!9'$eV+5R-qp#K]AY=QBVd%,*=_O$n^^"a<2HHND3=oc6DPCGeJ=+td&GGM9^/&q/da
,e!g>@B]AWDr9ne/qfZ0m[e6Ds#H-ICSNXOeJ3Ai+[`869lU<V';@N`I)&0jlq@0S'I;$^Jsk
[C?MG>ShBgOS0Q@o<0`h8n8G7n=@_$hLDbLJ=9@5o_)Q>O(#$sl_7Le2gLf(b=(g]Aq'SC2)"
"Zpc%9R(cI@,Z-h%S!)I+F9;_QO7SI0gN%c?&8A:s^MA`&>O-YOIe_Cn4@k$5=.Ig[]A`KL%s
<gCh`$NR_uZ-io5#[OXAcr]Aqhr%%!V#o4=a"sP<%$"(3qsgI%[4#Z[n,npgSm=%hTd/a>s>_
oS7*r3.P\3,'K*!13fT_d=]ABLhp5YY:,H0a`QHGhTbFtK]A[NeB*$n8f5a[bSB',Wrf-hC7gX
chTjEh_)#7u/if`#:07M8HRHQ[WIl7Q4YPXO71G@m&+:#DcpMc^bK!*c!c!gKe'7pNc&8Y=R
41bjt0hVIaNbIVohqS'`9\GF0n6D`+;[=i?X-Fo0aJk.(P5hob(g9$,<@*;cu;)2R<DVJF\U
8$\pn3,<ZX7=ETj]Akg$gCjeZKJJ$]A$_4U+e:?jc-Zh6'>*)G&"ITA2S$%#bJ2JTA)rA^p&sl
bZXYdNeRg'43k6_$R3:_d4/B:_G^X.E7eTl0CidH&T1qHsH-I(VnGfa@PmL:2^DA?P>SSOod
qFf>@98:r77tAM>j'l\P<>(JL-d9n/G3dCdh\/nGF"!j`^l/)u_?p:ndh903:$Ji@/3+.%EZ
3"UD%']An3"-9=Kg.k^2K;ta1Y)h4^j)X?DCA6"@@76@pj2VrhGu)?D"/OfOGPQ[F"G05EQks
E1N!;=l'$[d(F$)Ud%$KK]A4!\n"u_"AmK.Kn?f[nU\>2A$B@YQ@!2>k/\;r%Z)\0CAkT4B+-
13uHH/=\o='9Ou<!X*f'@`qs&OBD@5Ru+t#pUPh"+Cg&OXG]AKRV\!7U6hJf1duKX-K3Bo;qY
\n9t'gn3ibr<>t3O3+s@/G9e?!i[&;l.9hC_OLTC^Wdl0$*g5$s3ZGLhf4R2/=4R-;;a-04^
r4&#N#q)iIAf[$<5Erp[O@#LHASaat$NT8ZeF>t,c?j&]Afd.GGE*tc4lYA8Q>;s8%ijXep>%
kbQr3`gm=Kk]Aa#"0UTWb7dZ#oEY=@RA/c1eLMB!_;-@BJ_N3;DE7n?trt=@[3@<cQAGu+iBT
ChAm:4]At9B`f\P*:Is!g@4e/d&>>3Md]Ab?.$Oj=o<Sc&mLH9Ll;EXhs-\`W^l(Gd/iIehB.J
U#hWgrr\tipoS:RiTFW.1eJJO7"leF+9k[[8D$U(A1[e;6s-`bR_XKj"j;8b9*):Fo)@gkW@
)bGe%op%&UH)^73EFIK]A^gg_+/)nJ$7;kcDJi;eWW7jD@tU6+\>&Du:g_!5s(2MTqUNgm`eg
g0uh,j*,SGar*eU)?goJKf:XE=%4S=aFd+`g@Hj.8]A9!O=JU;mb;nFFgWJG#!fa_BJH]A^n)p
aU(MYi^hp8qYnhp\un_JhK)Z1.HESL]AII+cM>"AHGL7jiogd^nnC:?,;6.>WM^LD2)^kO]AfM
'8uRdoO)(<);VmUjnK^g*@V]ANXVrFLnJrrnfStmVqC+sPCdO/_m%6iFqoQjm)rS')8&W)Uj]A
OF#!kpR1;MZ#$>X5&R\5r*fQZG8*+^7hHHgN"B2X>>,:c5\QN-uC+30^169/9Ps-Lhg7,gpu
7IKbYCA2fi:fUG-(?@\G)lUU&gP(o.)S\_XYc!7ULM',?i-@g`oV*C!,2l5426gQodX:V3uq
V4:p=V)QrK4D`8"<#28`^!H'M!)@-;O<>Fq2SmfdXGnL$*shbHZ+aq:jHE7=,N&;4:ZmBY_%
[o@'ZL6ainc$l,]A@>gUs'h^QSiLP#6+(P^3EVFM99^B2;,DZ?SXB=?>?MB5#%[pdb#$a&t!a
dU\ZSfZ">^b`iW@J=WB#Fs4I1<nqe$lT=@:$GO!t83`f/qTiWDY9tbjGLbk".'C-mVH1<ThC
q-Md,Y2Q\P53pB-7@O=G+8>]A;!;3e4n_t).<.RFNit;R.Ha=n^%,2:fTG*HS4s)?GCm50]A^+
[qk?Th9K]A1QLUqFZJqobUj,L6QQ;"GB2G;XMcp[Y]A>=f_enj6epi+\@u:F#3o*G.#NPKAp9+
1Vl.V2'i?V'K4ok?'2t]AoQ0Og9Cc19!s$W\gr;)oS@?CC+;6B1I'OO&ji*.QT$,CJ^P<&ACL
eW"h*$om680,dW0mn.aL@gW=1[>8%UuI:C:nA`GjS!;Umh"RC6.DRr*\+G1"_u`27SS#!%$L
,64O48brV1baBo8(<,h:en]At.V]AK@C4#0^%AD/paC!s@Jj=7!0TUFbq.ReYbal!=B&oO\^"&
]AZ6.o/"pLph<$_!5M2M<77Hppplh.&##okgcNO\r^0^@?!o&gNi=2rVX6o[@^F#q9hHI[_"[
<\L>1+0CUTQiEOq*CDlpG]A!J%VpG"U3/84F@0"T+P5?q^GaiVT1S`kn(-M.QCYGlS#a'P-OI
q)nIdQnJ.`$P(gbZ/UfSa-l3e07"fU/[3&GKluTg,Bj1%5p.6s%n$WJbt-H\ei5E,M_T<QPq
)6A&4KJS[mGMQ$gek6TYSV^0ft+l]A3>c8ZVG(//l6"lp$`JL-k,:b'LBsNM+&?p+PR.K3O*V
]A>NoHp\^8H[O%POMOT"[/6r`!TWfQt#=_o<`MX\,VW7kArN?VQ>20VW%]Aspl[]A`ML(XJ?++N
NRlG-+e"MNt[Z/q3BsWOnVLl.)n(:7'!n?g_9Ru9dtI^2V4-U$h^P4)Ym/[nCG6/f$.dFG89
3N9!2O!:cS@&'.Y8?b+RECA5kqjB:_9K6[nWpMRm-'@10en=L$3]AO%t<-h;=cJ>$#<48m/,d
+,r-6I[ElJO_#Q97gX^4.4Ko+G.;bR7G)^u`L3n]A.HiD)QT<Fd6V4L+f_0^-BDsf_O/>CBjB
5_^FsYtFKZB>`5bo17UjZMLI$GG&]AUI;_@LdD-g170u.K$Y=WYS7<Jb*"Mp$fA0MEm&!(AF;
J('/Ps-e"Mti_sZP5C7dsE!At11ho&c9jVMcql)g7,1Y9'XdbMV+)M?o<[&r[G("K'A5bjKE
m"d<;o9K&[S8,CVjm#rC*4Ml>sIEQ2n`WdKPhHeKt;^EWOC4*j6s=C(TlLN[79"R$uF_2#Yi
&P[VImY<f?"kB]A<B9im1BfSdEXD.t@l;CgIa&Ca_eR*]A,tFiO5S-I3-NFXgEoi%lQ;@X3=".
I#,%$SP&d1<q4/'ebEBAqas]AJpft?jrk,P8fn5ElQIlF;$<Zr4CPre]A\qVgKSpS2>_8I_^fQ
_POJ*SjF$?oWV&rZd?0U0NHGbq=A\j?M6.mJEe,9Mo!^Khq2cRP(-d$O5ZM7*-gpP[Q03p6H
mQtQ<:()0RlXMZFjGOEqj1En6,B:HHe06%ImDnHi4htKPL7$p'`fS=pgZn7"f:.CF`pd>XRT
9=FN#=^'-'2if+R^dG=]AhPQsf"W_c5.m+Q&4AsN%/9S55?=[=UNtZ<+`&)G*C=cGG4ZVKHYh
4G;gdoM'5tIKd,gc6eI]AL93@C%nCJP`d5nF$1#\SbNi"$m<Tst#>=8p.@;',Hu=t7_(2ZcDs
h_,5W#n`'mh0t-'&_F!/@g3iK[mVtmq8W=\^'m\+)A#rE;]A1h[0QaK`$WWKK(^d$HfW]A+IN>
C)T-KJWe.(;Y!$F&@X'(RN-rAS$lr,6[17M6V+c=s5=a,I*4lJRR%`DJd<'hMhf-*`o&I[\m
f'3%-BaG!H8VD99W7P<_.$=Q:ikEa-qlgS%Q5h2[l@.nZ.q]AfReOP,Uu`rQsp8!*WP.1(kc9
dr3mG1>1/^BML8(R:CbiF8Q.qF^_T=*jPdFB7[_8!&ei=G5UFG!(Ue3^.VSOEW2Z=@2+Ks%/
(\fI'qpEem'-\3E$!aOjr,RL*o"kcR]A!-O?8%cin\.g_&Mb[4]ADW$_k[?AAC2C,_FKo0;-,_
"dG]AKjtEH^VLL'5&r^OXi/Kj)i.g5e7\6)Q<T^B:M-E^V$VZ:QGJ?Qnm[8M,#]AgG0OgRF0jj
`=i=WbJQ+e#QZ2Zp^WciNH>%Zl@DIMj[q7U]AOToV.T'dKTV:ZSLHRc`K_PWRcR*r%eAb,k)^
eE/7O!F`tGX)QW6uN:KFc3dW\oLU-qAqU-M5dsV/mf8$;&EN&oCnol#.;]A<1Te61']AE=O*`&
c.E]AA(cqOC,YZr?<WcDoZN:SO4=sB/@4S"b.HiW4&A0]Arir^G3UkB<[lY_.05eBZ;B\_pec1
0hs&G5>CN\*l4N'@-)cD\SReWt]A<7]A]A>\)?u)-n6N*O"2fiBe_XgD$rSVhmTV3gtjm6<N'$t
djRk8'F#??oOR%AT4:1ch[Or:nj>iXqp2q@\.IiZko=t9HqYC;b?6W0AB94nj=;?k@qYt("W
IF".?Tl_adfY%C``D;;\efUf-JR*2L=RGrKHIAJeLF+"CZ>55f6c@.\g8F'@=Ous""LE1-U1
d=nH3:["Tbk]A&8%#`9_KOs,,"pqrW@fK<enNAX'ftBHd#9k6NO6?_f;U"C"f$Q%1E8ZE0%p2
Q^0F:\V`"oN6trZREMH36M#$:il#Uekb2Er;i>Pc3b5U"6J#@T0bbur/3]AY)R7k=SqXa>KNS
%!A`s)YiS5Q5TN50+C.RbjI!c;G2:.*U%7;SV);s*)/!LEX,<+]A<"r'gj\3+H7UDlr$+91Um
PQ,h)//pWqp6Y6Sk(=AAEs35)PE8_g"Wsqq#;$jk*uoTEVda6pd9NORN'K",pWUhXfEku"e)
>`f.h=Q<)7Q",bMJ3bNi90shd@l;W#(KDs!%2MW5o1\Id30%Mi2!N,Q0^EKYm1<XSQgS;KU;
!fg+QPnDSKMGJu+bqQg.c(]A#Re<r\MVeniat2;&P9hDSgl%Kg7u6ca8*i89/8AZ@.G3*cepW
":#N1B5nNc-3ePo4+P/-P6&)'`O$jR%"@<%TH_=dJP]A_=,^k!TJ;>cHKrgg.#1JmNlbK)9m%
//T@+IS!qL<sCr:l)IjS?#"^EM(]A'stRAJeVh9FR?UCi6Qr:p/D<rH^I::%ZhiLO2rb0h=dE
gkC!fCj'RSkursqOWuUAI1",J-0]AnM6\cu,+;Y4SpV7-$^m).ZFWEW^\a9knc/drOaO"R]A8R
G4mcWgJX''!Saca"kBUTV\^1DC&^12=Pe?Zh%S@8uc)rEhE@V=,0r+;DH\s,-eg%3d-iT1;-
%QS/arF#Ph]AD(WKBBV>-On,u5&4m>;rV#96'7^8aOq`i4$?aYi#(tV;fm1CE'c5`dhgrj3)d
to]A%nga/O^t$`p/B=d%;f=3r59=]Atr617l\m-K/N%hLbMG2t0]A!>_Z-)d:&\]A?<;4nhM&:<9
7*H&#EUL5dU:IqfV^aA_,uB!-cWW/m9H(b>nu+JY<G9+h22,JH?)nI*.S?$%a_X&.)/HAXoC
^J1]A3#sN8S:;?q8cB/9DH@AX07F6/W)<"?tY:*(WqC\M+YtRlm'&gM=<9XN1QS>oD1DJ92%m
U:2r#U+sA8"\M/OkA4'[fmP]AR^rpg=p3X\K:&YL*P16LZ`f6;'F8Y:':-c3SDX<CH_N/5)34
k;o%?H%`fc@a+PX-c"9fiqfAH+XhM>i-BteLDf523VDiP?7]A(?ieLZqlJHglb0L$PVWHB<n"
)okddlF`M%1(UE$@_LVA<JcUo-@RL0M<UC$7!J:>8cbR1*OL>GVB6Kr49Y<_pF>hL\""5_%+
rIKL^.PEgps(6/=+s+8CAM4gE@8F?Y**BcN#ODbZ3A`e.sQkGsHch7=.d@;Jl9E9?8N-<:R<
ht2'`YgdW)=o/:K;KNb$*T=X5]AE3*JB]AJUg9<Nu/+ITY$b.>`*G#l9dn6D:lrM(M6_7\WBU(
cN0JJ=0CT%-=,j3KSeaaQf$-*UC8)G=?0Tf7\eSX.@N4Ma`!o&7[L'VG#OX4DZURj-u5$`)F
Y2Ehk<M1apZ<`jl>0_`]A;_nEc8>.V"4?]A]AST(\*p:\+3]AENkGkR:@>Jdq4JU;^ZEYHW<bEVJ
NHGid,dMJ7*<Mf7l#p_X&-liQ\sa-'SDR*a4><Rf\!9A[jGN".5jD8-nA+A>N-6a]AM@8()l3
o9)cIbaA;=rC0s/ph5d'Mon[tG"?HT1`$Ql"R3^/%$QqU&jmY+2@a;@+6EiVif-Agt>Af<^'
N@8u4L(\\Gc=`HtdZK>loa`Tq38BI08QS7L5a0)I@$,X&'e!(]A-SSIPP\@@pmim-_$!t/9kb
aFhcRKjC`dH@PWV<&moF.4)BZE]A_H9sSR('*]AHL1(\Z>X`H@`3;Z#BUHjBF.^L=5hdfQBJMQ
pVet7SgL'EK`oC4<=^2,m70<SlLWnSrO^5fX?j;27Z`WTnd[LCah(///\L\R9TH;Mia--Gem
-?mKj>WQU46%)K6)?MSR2C5pBWLPs#0't)NH)KpG>*,^8M$4\8(Ams?WR9[a>>(`\$Ik(hs6
8Mq?QR!2^`Q'g2W8fYUB=X]AB(:<i)>i-F+cU-T<Kf!-$H)@l2M"abqS`m?(6^P>L;$/$fU\X
/!7[^8:gJFUn.PNlH@SqR]AGuEc"Y=&22X\^$mkMoZH$3*Ej!rYR^]Au@G\]A,cq":tn.(V=Qnf
#P>b)(ftX(!:/qm*s@/;JX>pU[M[DaH':hVefB@dH/3D<UF7[8l?0c'j=`9*V56^16K-!V.T
[_(,gZ1o)0;LhZ`Y^-.[te;tN,gr/;8(+"g=+^$;C,J3)TA\c[ui`DaD+oPtI'k@e:BG"apR
J,DF0HeS$#fP`*R..62CN;Y@"DB+i<T;;NS#_<FK'bi\3G^H-5,JVQ?!c7OgbdSR0sEMW9>O
;ILtEaaIDcn'I`k"V7Q2fOomgB?=BjjMgO?@\JGeNM(!8&hZQksa@r^4gjAkuL3>'qVk4`hW
HV`[^VjNikC+@:p8@bc;@g9nOCM]A<s>"iU1Q7jLn$ihCum8E$.k,*aUn+t@'4%?SpT0$VhEK
HN:\O_^`$@5ZSkk4o,S*Z#H$]A&^Yao@%#>YR2L/UDdtNnS%kd>MpFs/k>Om[h1bgBQ4;4`,3
GDVsUo/FA^$=gG=gnc&NCMh=*W^Mn8X.&gR2IX+u*CJRbL9QW<Hd^6Jte;>GXi'1So,Nj_Kb
kuE$aV,!'6;;oEFiF;;]A`fNs?JRF5CcD3;cCr7@ma<Ime!^)M1)@`u(NTbceBJ\r,[391eR7
h,`(-4H#tG<g5"OEaUuKqA(;380iG3#$9ecXsc/V*WITn)t3%+/(GC%>>+)J$rYIF(;?OlMk
0XWl"\ul!nXAelPW`\fqEgaEghRiE4UCDSl+Nt24U]AK]A@h6.W$*TYQDfUW@d4-=*5$]A,QP\&
15pTEqE_hr?m*BXa9(O6I6g+,Dn\aj0I0Y:6]A\gNck4)8M:S:VZsJmk@o'7^0.P5ap5rM:H<
n)"+0jAM]A3HMQV0d7+k'#>%2<>"+QRLL9qBLN?M#r@=ZMuO'0KTXpWAj9C@g7p_@;EV0Xck%
ifA4:E(k';nKEE/QW?%376j1j>9sIDoDd,Rfe`]ACeKe,.C5QI0oEm$?6A"c%b`L78^(*:":8
nG4>*4?\7Yq<1mK2#Qkk[9SQ(r<")HVtR=8E^YI<=D_M5!47mrc.4I@b;)i['CI)ELXbc$OH
+.(l('QJ?l,ne`MC@.`r5H#Ero@`WYcOEMsWH\7t\9/`MP*fH)MSR0-p5\%d\eaf<lO+N<f#
5DEG(L,\a2$$T]A*^?YKmE=Q\#W@[!8Kb9Gp=>33lYEc/&jM(I;YQsAIEBgS_E+PrX]Ao!Ila/
NF1N:uh:oO"R/ck%+)0+Zi;2$ogR,@o9C$=XNY:L1><q@+`5pI:]A0#[DYi;Z2b<Lod/NuCm1
>^WhU`-t+lo2X3g,2S-W2pDJAeW><Vdc+HoP08g<HR"eKt_#%%KblSD,t8Wp=,8M71mCc`<k
e^55?Ppab?E,gt@P:LKZ<F:GJ`LND,u8knIBTP)]Ak-WYN^/aMt4`3fG\&g`[$CQj?EMkm-A7
nTO1T)g!G/CC\ip4NX_S'e+42fchG]AT_cn68$D'`qD.ZB-_DL;MY]A/Gf92fZ?6#OgrBjIU45
C(AhiU>>%*G'QNuX:(%R?`44)cJ)3:N.1YICN=D8iB4%%!;eQjQHa^2)K"p<\p6Z/e`'C4U!
bi3bZ";k[tUd:-6k6"<+U6qi2d6I,>eZhEYL;_ZHnheWL_ZupkCRY:l.&PH^TIGcNqM`k^SG
NoTEf'Kk$[f[ruf'Vq*+)%IQ=bm4=>EE_9ZG1;#-C\eE@f[Ki/nKMa)16@>:VpsY/`f(&;i/
gfe=H@B9BDp(!FoIglFh,uS='r)+iDV`AG+gT?#7?o6h5Jkm.<0<.Xp,!LAKh;ls?@=(mFBE
ri8PkVgaM86nEA[q]A7ag1eF%;^HeCQCofbJYh;cJY_Eq>dN`&'m/khM*a#OnL0b3K5>*.Yh&
A!Y[<T#)VYc(.B0\kpEKRI"]AJmlQ>\!+?LFonVAk'M7KZRSH<>N3]AJ+L;D;b5qorOrbmA8A>
8A_c,]Aq)$A`I,5FLB]A<[l+I,cj`*=PsQ<WR54jW72LknL>+Q&Kmp_BW_D;.YMV$(s>el7)+#
;i(*,-lBR?bgUT@,I0B4-=Haokn4N3I.3%3(,TcOuGHnR]Aep#I*HHBfgW[1=-4=/>M561hl'
Ok9CDSfTEYBV\mHW#:6NS_iUXdSB\S@(%i",kMLjOYWcIWN+_;if7iH5g=mrPA2`d'[\a2t#
p$(!K6hg!ITd/6%l.<\#N2#AALln^!EW5XUK:DW&#W(l'F5u3DCR^l<KW2SUh)#&W?-0H:0W
8Vu)?<IB.-!tgD=I;]Ar5(>4ljq`dgLXAol,EXO~
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
<CellElementList>
<C c="0" r="0" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=now()]]></Attributes>
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
<FRFont name="SimSun" style="0" size="72" foreground="-65536"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9G!;P@p?a'!Tbu_c1uNY)r8q=.(*k0O%"$+XGsPG,ae]Ah6e8=\TtX>`Y!bo+uJ@qSK<")+h
F6c<.s1<Pj)#$%MjdlLu#%i*"-*f"Vh["+:0;=OpbW7?a=%Oc-1'N?hOU4m4S4PF$B0W]Afbc
Pq>HoJH/HqTrSsc6B:f*lV\'&WB&-[,ru"+3'aR/)\+rg^J!n&lcSGjtmP!1orRopIT<K2R$
C=oqj7JV3RkrA:hRRq!#QC>oJ%DiV(&,_8*I7nfK,),QFftp$Ec^:/s(C>j=2V5U[8kiYZQ3
LpfZP]AOS$c[iiTA)fgse,DiY>)<g]AUW&VQT@/UHtgsF#%[W6fp(42=4sYNU!RUDf=Ak_PO#L
[-k,Hrg,3_V"'=e`MWYn^n2]AE#M("KV!<G'iUPqtHl#\>Y]Ai_eBi/S'+[_')o\5ZDGt^)_LF
F!NfW4&k(3NoqU8]A7cjLB8m<cd1S?M.HY2k+*&jX!Lm%`-4P0)r>k4]APjX=)seb<jRgJInoQ
g7_AVHrC`<.NS\&,5B,Cjgn>ADV=DmF2^\;7-K>J]AUPlWE?T9QFL9Sp0<.eI&>Glh#a4-/_F
47B;.Bfc#I.cX<m^o>]AIG0fY]A_f.1f6$0i3PL^0bmP3\g+?K2W&Bu'iRs!Gb*d?N=o+@47?g
OG]A=pY`lB'$l:O@fnqlIpF5OMM@!`bfcl;N)`m)gh_,sgER<>YJp?%\*$('oE=IG9FF+&k$1
U%1lFRNRMi'UkY2$WM6XM>?mML9uio*Ek?;:OCaKaMHrR(?#Y$p&&:VkQYonpP7fsUGPcXH\
ErGj=)FXL58p`c,b01Kn>!i575^EanX.Gg)"`Z,_V7$PhR]A4F))("ffat'<F;3pSL:%#4*#T
Shq92Y?/+T9\m!N3a[`YY/_6'MUh*\a$lEQfG0@nPqmk2h6MlCi&8jhimuf1`U>$]AVUU;^`W
(=\`WG'*1ibMIr,ijX0o2F9HPRb*L_hu:;]Auh2*N/lmMS/3;;.2GE-<q"./)dh#C,^YCG&98
0E,Br*taXr)Z/%LD:P,,iLS4()\:Pcc3?oW5C.cV2^<Zu0u3gupE=_%).b>H!?b-hKeZe9]A1
j]ApU&l,a7%kd9^;nu[h[Gu[osMn"1,r8Pf,ndq_nfZk`T6[n@jL:fJ?/lk2c@u&4,Y-]A\)'R
kV(ar8LaO-!u>Sre7h1W]AR7`H3[qgLsrYL[tK!#>%bg[2jO'\&UbZp97g8[,(c-.>'6@3#(W
cLPj%)_OeF0^OGtI;f\k:<qXj@l+=uB1YA7:r]AW"bJ]AR^;Domc+%(_ABecNp&T`DW'jA3I/A
^[0W35lM,H8p,5?6QCe*?FNcl=(n@[eGGlrX`o@32Rlk8"t@7MhL*2i3nS9%2Pth7'<.=YK\
3CO>niLRkQ9uq/?PkBgl(<'7QF2at&"%=h5oXR!-oXPS5)K0392MC4"PI_cH$]A:kXkIP4W5P
0#ZY^RTO=2Hoq?C[UQ=Q`,F3J8Yi,,nsmomktZB]A)V>Zjr]A3KZ30rr$KZ#lj=Q2@8.17"d"c
foL'@H`ODD9'C:oek^6LH%b7>-3WSAJ')FEAU%fB.,>iCf5^@0kF/aU*/eb:*`_ebS`KTSIa
<%\B,4%/D#NB%_m&I1Cp#;_tSpQ8SPX'Ru\&Z/%#Qpu^PUem.K?If5j[-d-%=Z!^.K^MROWM
M32Ddfdk_iY@EqI3?ja@?U(#(l/=d`GbeEOW*)5d5Mf]Aa,Z^NRG9oeE>Ygqfh?*;Mn5%jSlD
7&=`Vr.,!M<lV&cR4&;:aI`f!<I]A56NiX2:=Zad2W3A49JW++!XF!s_NNd.Ack=`YiZX0EGk
W]Ad^0gDd6qki`&oU*=!9Cqh%22s8d\1OO\%5\E:9L)bR4\_/I,DVl`@7U5E$X3/1I&8ZPDrW
u0U#CnL%:pe9sE8XJ:7^k'W-(57t$JD-rE3!8Gf3bKU"^?H[3!s4tG7QV09r!ik<=[6boS<O
_l0VY0]A+0qE<&BTBKTD(b)5)\al5Eb>-?:l%YG2/CIWsZi*Q:n`,dKB*:,k!QJ]AFr[1FL)(#
(/Lkmpo5OJ=rnT'iQ^Z?-n7N16M3X#lW'@7R]An5N>dd]A_J!(skV:PWWD&gbXM\;&_+"e.-Zk
:KLeC5TYs)?Z8_1i6TTkmL7_L':Lg6SiO*m'W8$t"%qVb8d>Kr:PN\aMf=!3"YPIL;2M<f8A
lXpfL%1c,U!:'-s]AV=B\AB\`gdYE:^q.?2!,8Jet)WfED$SibKl>eL@q`^*uegFB!LgLh?Y&
QRn0=94#%c&-&AgOknrun/#/ein/B#2[;OH$6<AqdOO6b7k$WOd5>,7_/tE'D2)KrsN$'nZ!
tr9Ob+A9cYW(h%Gjb#(9]Am<0CU686:ejLS.?hVY-"fDl;Bic5/28hLmo-e]AS3VIY8*`^UAcW
s1IUHgK%VG)E(SY[I`:R6R60g\2\)Wk(/(lWCM-(-di;Lu3-A!pS9)b4h"]ADtu:")A>dj;)O
uG:o*j0jV6201aR-Ts+Iq]A7:OFV_kNKkH03u$Hq.Nc1`.KI%n2o]Ac86F*,LbF([7Y%)3"7\R
IeXT.TB:B:R_:tFghQ-+hD=HH0T,+"fmW=P^oA219[0fWD*7MRmJAMa-Dc\`0bEiGJQ=8>#L
0Wd>[i$8N/o4H$fn#=>h4$Sog*+61\[Z]A"OVlrC4I<JoOK/A";\j":ar[$_[;,5EMtX!Scl/
j#?U,SX'pm#_NA'u0L\.(U.u78(\,3pp`hG.H=hn7F;!#8QDk?$JHn,7MI1`kjj>G+203rSa
Mq%5NF@IY^)MJ60Ri=mfR7?LAlPh/O:9b1\NC]AsS.>rXM4j*]A*2UhZEtQe4$`#7H4Ei\6KB]A
&=kEL3lPW5=!`Po$u))6tK/^C<g&)bg7Y`*mb@OVJIj_-Md--;Ys?lUUar1SLAN=*n!FL84m
2Q,JSB.q-3Yf%\bE%#T?csku0=BoF%-)=[ul,26ZV3KRQZ!cU0fBe5eQp$T<o41bD%Z"XAW`
,DO*&10!S\id-"^3"3"W*f]A^`166+qrt<5g&aEDO\0HdM)8?_nGl_W)Ndt!g,X@9V\4QKr7-
M/dX@IF(Git?mT':29R[Y3L0Xsb#HQ_QGZW]AnX+nN[gOE.*oK(<:U]A1pN#oSMD6AK*p(I[a%
:''.GM-4t3+G5trXQ"d.+3p+/sm2o,IkIr"J%j`!^2OR(Sb?7;lti"BfZl<]A_45s=qNm+Z9.
LLS/+o8(5IK+Y#%^ue?GIIrUp6GBd<PXd*"*mU_rV@JEpCQ%6Uo=Zl4E*TBVONLB!NUc/i2?
$m^?4bqngX/(+8:.FIpn:b7APp;$AXBLu:XV'.Scb%QC$g)#Fe9:8;(XqH)62p^(PXQ?Ym3s
G41?,IT;p:8q,e*A5&1-ls[V`Vr*@,MmKOC[RiVK8hrnn%mBbs2N!*'1**$40eQ1t(<^M]Abh
5;YM"1Y]A(0NPCk'$R-5se!8&'h`\Z9".d.>68i6J#\Y*nfJNEG]AXLQ1u:r5t+"o*OtoX6D`'
ka%iU6\J$>!5pMd2tl'EI@:Z'+dS*kKV#13`t@]A?AWEf/Ipup5mE4g6I;Ge#K^uXRc,-jmI.
'CZ[?=3=p41CE\LK2G3T<n?Cq#?3kSjDl^=9aIFLH8%t:6aO/TI.e:^=2Xu"5[C;_!KA4f)i
'GA[Xi/h"?b96TT`Z!AAVU(@CeS(2"YQL0/34,k1hTXcL'BRLgoljcB?#If.#G)#Lguu_5Ja
NA[_$pY=L9b5r\+-\?#t/@ZMPut/k&&%B$be\jNZ%U:7+a:5MM>$0!)E"N'me`lRj'Y?84+M
e$VM=/PLoHkWj8uXLgLiR46g.,SX:60DG"7iKKr9n!\<ofg_+9lhL#PuauoG'f]A=:!n#G4(Z
$NX4lncZX'Bdg/l1H#oQMR),0=$s^Tn+X@F\tM2>+X<4k06Y\2Nn_rI9*%l-4Gmn03dq\eSF
aH?irkPfbTSo':&O3,@rH$Y>M/?`2d96>)WLAj#G,fEK#-8rSh(IEI*%p'W.aZ5X*J1L/pQ1
eb&2`#Lj7#!sNU#(J/gW_sf+f$9D",<sLg(Z&qm68X.E-J_sc`)2p.g]AFW2WV[U_RjsaD$^q
4@[:0blKLRUqNok3j)551p%/3f#8/H9hVo:I!d^"ZMtB'0\k]AQ!8/rI$osqXmT"Ll[=Bq5ct
.PH"jsSd>3]Af2Asg_XPGSE3m@</q=3h]AA1?^JuQU#"2^Mm5]Aq17#tiu)Y->EtXWI^9FM/C3o
l9R<=GOSd#iQY"Ab>?b3VZdrM^M-?H-4SjB>CoID6,s)*\bBaSKN]Aq%Yb33m7=AL#TRqC]AFf
geQI:\cg(C]A@2E-a>*JV;/W%>&OX\.6[)ef3DgF('Qh"M9]A66nd;_4^"]AJ"ma+W;VRGAB78g
ZP7N^$^DHP<+>K%N*Zo[@8;+t;O.aM:_@%e^(0h3$<5a^Bgd!7!6g#'+MtlS)AC#CB5tK9N\
,FsPF><UpmL>lN,h36%[*J(DbS'Qj7MrY-pB`eU[E0*-i!QeGsGb3;ok?#l>SNf4SL6>gBC[
:^'Se3hc]AJ$f-c>`k\(P>Z2K:hiP^7MTj`Q>j[@nkB!(#.&G?o"p:L;f8q3@BXI?iZTKfo>p
>e]Ah=CpdAU*$fd-R+N$@<N,6+(frKQ`N%D2-2q7/Xq%8@H@=n?q$W=Us#rIVHC4@CTk`+2^=
B`!NO,#kA0F9=p\?U6m$6ao[ss:aRIjW\Bgp!NdGeAe"9f#ZapZO&1KeYb#MH[fHs^NG'RZ.
CQB@bWlnc4Dkt&ON8tB.Z(KKPLFV#kkB.XYH@bNj7;3f:eIh,V%;p_kfXYAI8\+;0h8cCs1n
,E7."6>r"=AFAPKPGia$:`IE)Vm=G2n2")#^U^?CNJ?3CI/XHh5H>b@XEWPO!eHr1c"Cj5=#
MH(4&<5'n(?h]AcJk/OelC5cIf07L\N)%W#J=`,\"_njBJ%2R?7U)J&GfQs7_P<T^eO4FRh1G
iO,2\6/obUsS^/a\5="#^@FLg-DJ9/V76V*)F+`"YP0rW?AqMs-==.@H?W*n)tO:F)q^UIb)
3CBl1cRgHPEd~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="667" y="63" width="282" height="185"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report3"/>
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
<WidgetName name="report3"/>
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
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[D0gIM<;ZRmR5nac#%Pi5@Lstk-A>:A80k/X#n]AWu8qnn=2D]AC@9,fA([,#!/&k+">#$htm+t
>F38@EWH0>DSjf(XX]AkP+fDSF_1ocg,j>cguHID^1+Ib=5B78K7^18_`W5NW=S5!<<d-^a!b
M.NaWQJ5o1L<`Gu\\KfoB3@bU$EJ$/deXF(d9VGW:CP5oX>Wb@JD%H?d<N\/-R61pU0l4-17
R%R#b"be]A,]AToU`jq)%I#SJ?_%i<&;:ZLa1*P:*HM&%R;,[,67K;6iIkR7Mr]A2l8<"`+O)ms
qXAu)EH6;5=QWh'Ln06raO!O>Kd0;Vq'J1j@erVZE:lGIK7e;.3a^n>PLpI_L/goEB.WqeZ&
*(Ohqm!R"8V5BS51#pB"[oI\2din[_m&9#/8g!G,/_i&7_DNKTV%tmO\O,`BhYcGXE]A`gUf2
KP/HOa--D%tX9[$[Gc:Me&)Dc\3iK#gn\MS%YCd_\D[8eM>aE7%N5$[?`fs%hfMJ._Z:r<4J
B$(d$!/]A'Cjc;Vbhmi.8/c5hkPQN_q%pN@#g-nC-(^6&oD!e^8n#?^D*,MRh&AVf59jTg1[l
:<V9/)-E@Th,:+FqTdX.F.Am]ACq?+D4%Tg;m@FnEL1^tP+91!L3j'h$N:IIZg$`%EtFFY@81
\<jHdlur\UK:@9NakT$F#)J\frNGFCPr$)GAMniLo*ngCTIY.s&#^X"hq)?hqbp?e5AC%R+m
B$,]A-;@FMA5N5"'>4g6dReGu_XOW!rqh=0TZ8gr-e^d9aBqJH@:)pX_W,$KKJ6d,HpdL:D,q
i%B1>oXa5-$@&;X8#0e]AYDp]AZC8CejV'(6`ql:?q1M[&a#6^35iQQ_VuhNVfkt1YA8B)GOj'
facU/DPTjui:m;l.$i.;:7bB=N:,8$^&0&ftrR)'(Lt&r?)osXsmgcG`Gkn`@[C&LRC-+t/X
aI&Z<u")^>De!X]AkjLUa:H:1g:$?#q'2A1SlU7>Ke(g8UofXBR_q2.OGp'W,W;/A1Ee#RQgR
]A;8X34mUOrY<jM/HWk_lN*EWWMU%(l9MLrJnM:JYDN8ZPGLX74q]AmUeN_'!?+m>B"[)q=bg?
h$D`$T+K3FKL0%-X\KqdO!V"&J-rKC>OG<t8KiSsbC1?j)0J>$SF0A95mLf,Xor`=1!J'#fE
E+Xgi,^:>EFl),o(#`3`-b2g]AQm('<VQlTOn@)C(#6;$T!Q10HR1c&=LbAD"Gm"HK%[7\=u>
u2!M3hN[LN!.5/55%YT-j7]A8;S.7&?7nM$m902(jslm2:fMi0^c\L^O>XW+pWg#9Z0S7aXhX
BJe9LDc*P<3inPB_t=I*<kj;)9MWUGH=="CsCHEb9*Y@c=??drGJa$(1)]Alf>oOJYT>'!:RH
=Ga.*$OUT-56#0ULFOa%l#\&PV<Anat`ZXB2:j:QG*hYaogoRcAVCnqWEIaG5[FsLPH!Su;r
AA\?=j"#;UlKAbGW!ttPpDm8;hh@G:#8>$olXgY>=T1]A=EPtftM\f&phk$c(DN%+Rn=&:3'S
\*k;J!)--ZDgqJolqtT_h4]A<NrH7\kkmC-I;2"lF'7\f,Zb;+E9sLS#&hnSR+F8p0>7H"#DI
n&F%mHC@`CM\?WDtGDR0P_'D<oo7n,GJ]ADF,"!'Ye\id.ZSG:LNG9:UMoP^2>/PY*c?((i2S
-ou>I\@AHbp'O>:4rP35bVgGPr(RaTieCk]A"2`J3p8R'W&gML\DD"fCnr#"(S0oG`2i5W!u$
43('B0>3d71tXg\*;2O)JQRlOJ?@EdT,_HQQhT!=rG[<n_bL@/2L]A-p7j0hk^]AYtH1i-\gut
niba-J!9'$eV+5R-qp#K]AY=QBVd%,*=_O$n^^"a<2HHND3=oc6DPCGeJ=+td&GGM9^/&q/da
,e!g>@B]AWDr9ne/qfZ0m[e6Ds#H-ICSNXOeJ3Ai+[`869lU<V';@N`I)&0jlq@0S'I;$^Jsk
[C?MG>ShBgOS0Q@o<0`h8n8G7n=@_$hLDbLJ=9@5o_)Q>O(#$sl_7Le2gLf(b=(g]Aq'SC2)"
"Zpc%9R(cI@,Z-h%S!)I+F9;_QO7SI0gN%c?&8A:s^MA`&>O-YOIe_Cn4@k$5=.Ig[]A`KL%s
<gCh`$NR_uZ-io5#[OXAcr]Aqhr%%!V#o4=a"sP<%$"(3qsgI%[4#Z[n,npgSm=%hTd/a>s>_
oS7*r3.P\3,'K*!13fT_d=]ABLhp5YY:,H0a`QHGhTbFtK]A[NeB*$n8f5a[bSB',Wrf-hC7gX
chTjEh_)#7u/if`#:07M8HRHQ[WIl7Q4YPXO71G@m&+:#DcpMc^bK!*c!c!gKe'7pNc&8Y=R
41bjt0hVIaNbIVohqS'`9\GF0n6D`+;[=i?X-Fo0aJk.(P5hob(g9$,<@*;cu;)2R<DVJF\U
8$\pn3,<ZX7=ETj]Akg$gCjeZKJJ$]A$_4U+e:?jc-Zh6'>*)G&"ITA2S$%#bJ2JTA)rA^p&sl
bZXYdNeRg'43k6_$R3:_d4/B:_G^X.E7eTl0CidH&T1qHsH-I(VnGfa@PmL:2^DA?P>SSOod
qFf>@98:r77tAM>j'l\P<>(JL-d9n/G3dCdh\/nGF"!j`^l/)u_?p:ndh903:$Ji@/3+.%EZ
3"UD%']An3"-9=Kg.k^2K;ta1Y)h4^j)X?DCA6"@@76@pj2VrhGu)?D"/OfOGPQ[F"G05EQks
E1N!;=l'$[d(F$)Ud%$KK]A4!\n"u_"AmK.Kn?f[nU\>2A$B@YQ@!2>k/\;r%Z)\0CAkT4B+-
13uHH/=\o='9Ou<!X*f'@`qs&OBD@5Ru+t#pUPh"+Cg&OXG]AKRV\!7U6hJf1duKX-K3Bo;qY
\n9t'gn3ibr<>t3O3+s@/G9e?!i[&;l.9hC_OLTC^Wdl0$*g5$s3ZGLhf4R2/=4R-;;a-04^
r4&#N#q)iIAf[$<5Erp[O@#LHASaat$NT8ZeF>t,c?j&]Afd.GGE*tc4lYA8Q>;s8%ijXep>%
kbQr3`gm=Kk]Aa#"0UTWb7dZ#oEY=@RA/c1eLMB!_;-@BJ_N3;DE7n?trt=@[3@<cQAGu+iBT
ChAm:4]At9B`f\P*:Is!g@4e/d&>>3Md]Ab?.$Oj=o<Sc&mLH9Ll;EXhs-\`W^l(Gd/iIehB.J
U#hWgrr\tipoS:RiTFW.1eJJO7"leF+9k[[8D$U(A1[e;6s-`bR_XKj"j;8b9*):Fo)@gkW@
)bGe%op%&UH)^73EFIK]A^gg_+/)nJ$7;kcDJi;eWW7jD@tU6+\>&Du:g_!5s(2MTqUNgm`eg
g0uh,j*,SGar*eU)?goJKf:XE=%4S=aFd+`g@Hj.8]A9!O=JU;mb;nFFgWJG#!fa_BJH]A^n)p
aU(MYi^hp8qYnhp\un_JhK)Z1.HESL]AII+cM>"AHGL7jiogd^nnC:?,;6.>WM^LD2)^kO]AfM
'8uRdoO)(<);VmUjnK^g*@V]ANXVrFLnJrrnfStmVqC+sPCdO/_m%6iFqoQjm)rS')8&W)Uj]A
OF#!kpR1;MZ#$>X5&R\5r*fQZG8*+^7hHHgN"B2X>>,:c5\QN-uC+30^169/9Ps-Lhg7,gpu
7IKbYCA2fi:fUG-(?@\G)lUU&gP(o.)S\_XYc!7ULM',?i-@g`oV*C!,2l5426gQodX:V3uq
V4:p=V)QrK4D`8"<#28`^!H'M!)@-;O<>Fq2SmfdXGnL$*shbHZ+aq:jHE7=,N&;4:ZmBY_%
[o@'ZL6ainc$l,]A@>gUs'h^QSiLP#6+(P^3EVFM99^B2;,DZ?SXB=?>?MB5#%[pdb#$a&t!a
dU\ZSfZ">^b`iW@J=WB#Fs4I1<nqe$lT=@:$GO!t83`f/qTiWDY9tbjGLbk".'C-mVH1<ThC
q-Md,Y2Q\P53pB-7@O=G+8>]A;!;3e4n_t).<.RFNit;R.Ha=n^%,2:fTG*HS4s)?GCm50]A^+
[qk?Th9K]A1QLUqFZJqobUj,L6QQ;"GB2G;XMcp[Y]A>=f_enj6epi+\@u:F#3o*G.#NPKAp9+
1Vl.V2'i?V'K4ok?'2t]AoQ0Og9Cc19!s$W\gr;)oS@?CC+;6B1I'OO&ji*.QT$,CJ^P<&ACL
eW"h*$om680,dW0mn.aL@gW=1[>8%UuI:C:nA`GjS!;Umh"RC6.DRr*\+G1"_u`27SS#!%$L
,64O48brV1baBo8(<,h:en]At.V]AK@C4#0^%AD/paC!s@Jj=7!0TUFbq.ReYbal!=B&oO\^"&
]AZ6.o/"pLph<$_!5M2M<77Hppplh.&##okgcNO\r^0^@?!o&gNi=2rVX6o[@^F#q9hHI[_"[
<\L>1+0CUTQiEOq*CDlpG]A!J%VpG"U3/84F@0"T+P5?q^GaiVT1S`kn(-M.QCYGlS#a'P-OI
q)nIdQnJ.`$P(gbZ/UfSa-l3e07"fU/[3&GKluTg,Bj1%5p.6s%n$WJbt-H\ei5E,M_T<QPq
)6A&4KJS[mGMQ$gek6TYSV^0ft+l]A3>c8ZVG(//l6"lp$`JL-k,:b'LBsNM+&?p+PR.K3O*V
]A>NoHp\^8H[O%POMOT"[/6r`!TWfQt#=_o<`MX\,VW7kArN?VQ>20VW%]Aspl[]A`ML(XJ?++N
NRlG-+e"MNt[Z/q3BsWOnVLl.)n(:7'!n?g_9Ru9dtI^2V4-U$h^P4)Ym/[nCG6/f$.dFG89
3N9!2O!:cS@&'.Y8?b+RECA5kqjB:_9K6[nWpMRm-'@10en=L$3]AO%t<-h;=cJ>$#<48m/,d
+,r-6I[ElJO_#Q97gX^4.4Ko+G.;bR7G)^u`L3n]A.HiD)QT<Fd6V4L+f_0^-BDsf_O/>CBjB
5_^FsYtFKZB>`5bo17UjZMLI$GG&]AUI;_@LdD-g170u.K$Y=WYS7<Jb*"Mp$fA0MEm&!(AF;
J('/Ps-e"Mti_sZP5C7dsE!At11ho&c9jVMcql)g7,1Y9'XdbMV+)M?o<[&r[G("K'A5bjKE
m"d<;o9K&[S8,CVjm#rC*4Ml>sIEQ2n`WdKPhHeKt;^EWOC4*j6s=C(TlLN[79"R$uF_2#Yi
&P[VImY<f?"kB]A<B9im1BfSdEXD.t@l;CgIa&Ca_eR*]A,tFiO5S-I3-NFXgEoi%lQ;@X3=".
I#,%$SP&d1<q4/'ebEBAqas]AJpft?jrk,P8fn5ElQIlF;$<Zr4CPre]A\qVgKSpS2>_8I_^fQ
_POJ*SjF$?oWV&rZd?0U0NHGbq=A\j?M6.mJEe,9Mo!^Khq2cRP(-d$O5ZM7*-gpP[Q03p6H
mQtQ<:()0RlXMZFjGOEqj1En6,B:HHe06%ImDnHi4htKPL7$p'`fS=pgZn7"f:.CF`pd>XRT
9=FN#=^'-'2if+R^dG=]AhPQsf"W_c5.m+Q&4AsN%/9S55?=[=UNtZ<+`&)G*C=cGG4ZVKHYh
4G;gdoM'5tIKd,gc6eI]AL93@C%nCJP`d5nF$1#\SbNi"$m<Tst#>=8p.@;',Hu=t7_(2ZcDs
h_,5W#n`'mh0t-'&_F!/@g3iK[mVtmq8W=\^'m\+)A#rE;]A1h[0QaK`$WWKK(^d$HfW]A+IN>
C)T-KJWe.(;Y!$F&@X'(RN-rAS$lr,6[17M6V+c=s5=a,I*4lJRR%`DJd<'hMhf-*`o&I[\m
f'3%-BaG!H8VD99W7P<_.$=Q:ikEa-qlgS%Q5h2[l@.nZ.q]AfReOP,Uu`rQsp8!*WP.1(kc9
dr3mG1>1/^BML8(R:CbiF8Q.qF^_T=*jPdFB7[_8!&ei=G5UFG!(Ue3^.VSOEW2Z=@2+Ks%/
(\fI'qpEem'-\3E$!aOjr,RL*o"kcR]A!-O?8%cin\.g_&Mb[4]ADW$_k[?AAC2C,_FKo0;-,_
"dG]AKjtEH^VLL'5&r^OXi/Kj)i.g5e7\6)Q<T^B:M-E^V$VZ:QGJ?Qnm[8M,#]AgG0OgRF0jj
`=i=WbJQ+e#QZ2Zp^WciNH>%Zl@DIMj[q7U]AOToV.T'dKTV:ZSLHRc`K_PWRcR*r%eAb,k)^
eE/7O!F`tGX)QW6uN:KFc3dW\oLU-qAqU-M5dsV/mf8$;&EN&oCnol#.;]A<1Te61']AE=O*`&
c.E]AA(cqOC,YZr?<WcDoZN:SO4=sB/@4S"b.HiW4&A0]Arir^G3UkB<[lY_.05eBZ;B\_pec1
0hs&G5>CN\*l4N'@-)cD\SReWt]A<7]A]A>\)?u)-n6N*O"2fiBe_XgD$rSVhmTV3gtjm6<N'$t
djRk8'F#??oOR%AT4:1ch[Or:nj>iXqp2q@\.IiZko=t9HqYC;b?6W0AB94nj=;?k@qYt("W
IF".?Tl_adfY%C``D;;\efUf-JR*2L=RGrKHIAJeLF+"CZ>55f6c@.\g8F'@=Ous""LE1-U1
d=nH3:["Tbk]A&8%#`9_KOs,,"pqrW@fK<enNAX'ftBHd#9k6NO6?_f;U"C"f$Q%1E8ZE0%p2
Q^0F:\V`"oN6trZREMH36M#$:il#Uekb2Er;i>Pc3b5U"6J#@T0bbur/3]AY)R7k=SqXa>KNS
%!A`s)YiS5Q5TN50+C.RbjI!c;G2:.*U%7;SV);s*)/!LEX,<+]A<"r'gj\3+H7UDlr$+91Um
PQ,h)//pWqp6Y6Sk(=AAEs35)PE8_g"Wsqq#;$jk*uoTEVda6pd9NORN'K",pWUhXfEku"e)
>`f.h=Q<)7Q",bMJ3bNi90shd@l;W#(KDs!%2MW5o1\Id30%Mi2!N,Q0^EKYm1<XSQgS;KU;
!fg+QPnDSKMGJu+bqQg.c(]A#Re<r\MVeniat2;&P9hDSgl%Kg7u6ca8*i89/8AZ@.G3*cepW
":#N1B5nNc-3ePo4+P/-P6&)'`O$jR%"@<%TH_=dJP]A_=,^k!TJ;>cHKrgg.#1JmNlbK)9m%
//T@+IS!qL<sCr:l)IjS?#"^EM(]A'stRAJeVh9FR?UCi6Qr:p/D<rH^I::%ZhiLO2rb0h=dE
gkC!fCj'RSkursqOWuUAI1",J-0]AnM6\cu,+;Y4SpV7-$^m).ZFWEW^\a9knc/drOaO"R]A8R
G4mcWgJX''!Saca"kBUTV\^1DC&^12=Pe?Zh%S@8uc)rEhE@V=,0r+;DH\s,-eg%3d-iT1;-
%QS/arF#Ph]AD(WKBBV>-On,u5&4m>;rV#96'7^8aOq`i4$?aYi#(tV;fm1CE'c5`dhgrj3)d
to]A%nga/O^t$`p/B=d%;f=3r59=]Atr617l\m-K/N%hLbMG2t0]A!>_Z-)d:&\]A?<;4nhM&:<9
7*H&#EUL5dU:IqfV^aA_,uB!-cWW/m9H(b>nu+JY<G9+h22,JH?)nI*.S?$%a_X&.)/HAXoC
^J1]A3#sN8S:;?q8cB/9DH@AX07F6/W)<"?tY:*(WqC\M+YtRlm'&gM=<9XN1QS>oD1DJ92%m
U:2r#U+sA8"\M/OkA4'[fmP]AR^rpg=p3X\K:&YL*P16LZ`f6;'F8Y:':-c3SDX<CH_N/5)34
k;o%?H%`fc@a+PX-c"9fiqfAH+XhM>i-BteLDf523VDiP?7]A(?ieLZqlJHglb0L$PVWHB<n"
)okddlF`M%1(UE$@_LVA<JcUo-@RL0M<UC$7!J:>8cbR1*OL>GVB6Kr49Y<_pF>hL\""5_%+
rIKL^.PEgps(6/=+s+8CAM4gE@8F?Y**BcN#ODbZ3A`e.sQkGsHch7=.d@;Jl9E9?8N-<:R<
ht2'`YgdW)=o/:K;KNb$*T=X5]AE3*JB]AJUg9<Nu/+ITY$b.>`*G#l9dn6D:lrM(M6_7\WBU(
cN0JJ=0CT%-=,j3KSeaaQf$-*UC8)G=?0Tf7\eSX.@N4Ma`!o&7[L'VG#OX4DZURj-u5$`)F
Y2Ehk<M1apZ<`jl>0_`]A;_nEc8>.V"4?]A]AST(\*p:\+3]AENkGkR:@>Jdq4JU;^ZEYHW<bEVJ
NHGid,dMJ7*<Mf7l#p_X&-liQ\sa-'SDR*a4><Rf\!9A[jGN".5jD8-nA+A>N-6a]AM@8()l3
o9)cIbaA;=rC0s/ph5d'Mon[tG"?HT1`$Ql"R3^/%$QqU&jmY+2@a;@+6EiVif-Agt>Af<^'
N@8u4L(\\Gc=`HtdZK>loa`Tq38BI08QS7L5a0)I@$,X&'e!(]A-SSIPP\@@pmim-_$!t/9kb
aFhcRKjC`dH@PWV<&moF.4)BZE]A_H9sSR('*]AHL1(\Z>X`H@`3;Z#BUHjBF.^L=5hdfQBJMQ
pVet7SgL'EK`oC4<=^2,m70<SlLWnSrO^5fX?j;27Z`WTnd[LCah(///\L\R9TH;Mia--Gem
-?mKj>WQU46%)K6)?MSR2C5pBWLPs#0't)NH)KpG>*,^8M$4\8(Ams?WR9[a>>(`\$Ik(hs6
8Mq?QR!2^`Q'g2W8fYUB=X]AB(:<i)>i-F+cU-T<Kf!-$H)@l2M"abqS`m?(6^P>L;$/$fU\X
/!7[^8:gJFUn.PNlH@SqR]AGuEc"Y=&22X\^$mkMoZH$3*Ej!rYR^]Au@G\]A,cq":tn.(V=Qnf
#P>b)(ftX(!:/qm*s@/;JX>pU[M[DaH':hVefB@dH/3D<UF7[8l?0c'j=`9*V56^16K-!V.T
[_(,gZ1o)0;LhZ`Y^-.[te;tN,gr/;8(+"g=+^$;C,J3)TA\c[ui`DaD+oPtI'k@e:BG"apR
J,DF0HeS$#fP`*R..62CN;Y@"DB+i<T;;NS#_<FK'bi\3G^H-5,JVQ?!c7OgbdSR0sEMW9>O
;ILtEaaIDcn'I`k"V7Q2fOomgB?=BjjMgO?@\JGeNM(!8&hZQksa@r^4gjAkuL3>'qVk4`hW
HV`[^VjNikC+@:p8@bc;@g9nOCM]A<s>"iU1Q7jLn$ihCum8E$.k,*aUn+t@'4%?SpT0$VhEK
HN:\O_^`$@5ZSkk4o,S*Z#H$]A&^Yao@%#>YR2L/UDdtNnS%kd>MpFs/k>Om[h1bgBQ4;4`,3
GDVsUo/FA^$=gG=gnc&NCMh=*W^Mn8X.&gR2IX+u*CJRbL9QW<Hd^6Jte;>GXi'1So,Nj_Kb
kuE$aV,!'6;;oEFiF;;]A`fNs?JRF5CcD3;cCr7@ma<Ime!^)M1)@`u(NTbceBJ\r,[391eR7
h,`(-4H#tG<g5"OEaUuKqA(;380iG3#$9ecXsc/V*WITn)t3%+/(GC%>>+)J$rYIF(;?OlMk
0XWl"\ul!nXAelPW`\fqEgaEghRiE4UCDSl+Nt24U]AK]A@h6.W$*TYQDfUW@d4-=*5$]A,QP\&
15pTEqE_hr?m*BXa9(O6I6g+,Dn\aj0I0Y:6]A\gNck4)8M:S:VZsJmk@o'7^0.P5ap5rM:H<
n)"+0jAM]A3HMQV0d7+k'#>%2<>"+QRLL9qBLN?M#r@=ZMuO'0KTXpWAj9C@g7p_@;EV0Xck%
ifA4:E(k';nKEE/QW?%376j1j>9sIDoDd,Rfe`]ACeKe,.C5QI0oEm$?6A"c%b`L78^(*:":8
nG4>*4?\7Yq<1mK2#Qkk[9SQ(r<")HVtR=8E^YI<=D_M5!47mrc.4I@b;)i['CI)ELXbc$OH
+.(l('QJ?l,ne`MC@.`r5H#Ero@`WYcOEMsWH\7t\9/`MP*fH)MSR0-p5\%d\eaf<lO+N<f#
5DEG(L,\a2$$T]A*^?YKmE=Q\#W@[!8Kb9Gp=>33lYEc/&jM(I;YQsAIEBgS_E+PrX]Ao!Ila/
NF1N:uh:oO"R/ck%+)0+Zi;2$ogR,@o9C$=XNY:L1><q@+`5pI:]A0#[DYi;Z2b<Lod/NuCm1
>^WhU`-t+lo2X3g,2S-W2pDJAeW><Vdc+HoP08g<HR"eKt_#%%KblSD,t8Wp=,8M71mCc`<k
e^55?Ppab?E,gt@P:LKZ<F:GJ`LND,u8knIBTP)]Ak-WYN^/aMt4`3fG\&g`[$CQj?EMkm-A7
nTO1T)g!G/CC\ip4NX_S'e+42fchG]AT_cn68$D'`qD.ZB-_DL;MY]A/Gf92fZ?6#OgrBjIU45
C(AhiU>>%*G'QNuX:(%R?`44)cJ)3:N.1YICN=D8iB4%%!;eQjQHa^2)K"p<\p6Z/e`'C4U!
bi3bZ";k[tUd:-6k6"<+U6qi2d6I,>eZhEYL;_ZHnheWL_ZupkCRY:l.&PH^TIGcNqM`k^SG
NoTEf'Kk$[f[ruf'Vq*+)%IQ=bm4=>EE_9ZG1;#-C\eE@f[Ki/nKMa)16@>:VpsY/`f(&;i/
gfe=H@B9BDp(!FoIglFh,uS='r)+iDV`AG+gT?#7?o6h5Jkm.<0<.Xp,!LAKh;ls?@=(mFBE
ri8PkVgaM86nEA[q]A7ag1eF%;^HeCQCofbJYh;cJY_Eq>dN`&'m/khM*a#OnL0b3K5>*.Yh&
A!Y[<T#)VYc(.B0\kpEKRI"]AJmlQ>\!+?LFonVAk'M7KZRSH<>N3]AJ+L;D;b5qorOrbmA8A>
8A_c,]Aq)$A`I,5FLB]A<[l+I,cj`*=PsQ<WR54jW72LknL>+Q&Kmp_BW_D;.YMV$(s>el7)+#
;i(*,-lBR?bgUT@,I0B4-=Haokn4N3I.3%3(,TcOuGHnR]Aep#I*HHBfgW[1=-4=/>M561hl'
Ok9CDSfTEYBV\mHW#:6NS_iUXdSB\S@(%i",kMLjOYWcIWN+_;if7iH5g=mrPA2`d'[\a2t#
p$(!K6hg!ITd/6%l.<\#N2#AALln^!EW5XUK:DW&#W(l'F5u3DCR^l<KW2SUh)#&W?-0H:0W
8Vu)?<IB.-!tgD=I;]Ar5(>4ljq`dgLXAol,EXO~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[D0gIM<;ZRmR5nac#%Pi5@Lstk-A>:A80k/X#n]AWu8qnn=2D]AC@9,fA([,#!/&k+">#$htm+t
>F38@EWH0>DSjf(XX]AkP+fDSF_1ocg,j>cguHID^1+Ib=5B78K7^18_`W5NW=S5!<<d-^a!b
M.NaWQJ5o1L<`Gu\\KfoB3@bU$EJ$/deXF(d9VGW:CP5oX>Wb@JD%H?d<N\/-R61pU0l4-17
R%R#b"be]A,]AToU`jq)%I#SJ?_%i<&;:ZLa1*P:*HM&%R;,[,67K;6iIkR7Mr]A2l8<"`+O)ms
qXAu)EH6;5=QWh'Ln06raO!O>Kd0;Vq'J1j@erVZE:lGIK7e;.3a^n>PLpI_L/goEB.WqeZ&
*(Ohqm!R"8V5BS51#pB"[oI\2din[_m&9#/8g!G,/_i&7_DNKTV%tmO\O,`BhYcGXE]A`gUf2
KP/HOa--D%tX9[$[Gc:Me&)Dc\3iK#gn\MS%YCd_\D[8eM>aE7%N5$[?`fs%hfMJ._Z:r<4J
B$(d$!/]A'Cjc;Vbhmi.8/c5hkPQN_q%pN@#g-nC-(^6&oD!e^8n#?^D*,MRh&AVf59jTg1[l
:<V9/)-E@Th,:+FqTdX.F.Am]ACq?+D4%Tg;m@FnEL1^tP+91!L3j'h$N:IIZg$`%EtFFY@81
\<jHdlur\UK:@9NakT$F#)J\frNGFCPr$)GAMniLo*ngCTIY.s&#^X"hq)?hqbp?e5AC%R+m
B$,]A-;@FMA5N5"'>4g6dReGu_XOW!rqh=0TZ8gr-e^d9aBqJH@:)pX_W,$KKJ6d,HpdL:D,q
i%B1>oXa5-$@&;X8#0e]AYDp]AZC8CejV'(6`ql:?q1M[&a#6^35iQQ_VuhNVfkt1YA8B)GOj'
facU/DPTjui:m;l.$i.;:7bB=N:,8$^&0&ftrR)'(Lt&r?)osXsmgcG`Gkn`@[C&LRC-+t/X
aI&Z<u")^>De!X]AkjLUa:H:1g:$?#q'2A1SlU7>Ke(g8UofXBR_q2.OGp'W,W;/A1Ee#RQgR
]A;8X34mUOrY<jM/HWk_lN*EWWMU%(l9MLrJnM:JYDN8ZPGLX74q]AmUeN_'!?+m>B"[)q=bg?
h$D`$T+K3FKL0%-X\KqdO!V"&J-rKC>OG<t8KiSsbC1?j)0J>$SF0A95mLf,Xor`=1!J'#fE
E+Xgi,^:>EFl),o(#`3`-b2g]AQm('<VQlTOn@)C(#6;$T!Q10HR1c&=LbAD"Gm"HK%[7\=u>
u2!M3hN[LN!.5/55%YT-j7]A8;S.7&?7nM$m902(jslm2:fMi0^c\L^O>XW+pWg#9Z0S7aXhX
BJe9LDc*P<3inPB_t=I*<kj;)9MWUGH=="CsCHEb9*Y@c=??drGJa$(1)]Alf>oOJYT>'!:RH
=Ga.*$OUT-56#0ULFOa%l#\&PV<Anat`ZXB2:j:QG*hYaogoRcAVCnqWEIaG5[FsLPH!Su;r
AA\?=j"#;UlKAbGW!ttPpDm8;hh@G:#8>$olXgY>=T1]A=EPtftM\f&phk$c(DN%+Rn=&:3'S
\*k;J!)--ZDgqJolqtT_h4]A<NrH7\kkmC-I;2"lF'7\f,Zb;+E9sLS#&hnSR+F8p0>7H"#DI
n&F%mHC@`CM\?WDtGDR0P_'D<oo7n,GJ]ADF,"!'Ye\id.ZSG:LNG9:UMoP^2>/PY*c?((i2S
-ou>I\@AHbp'O>:4rP35bVgGPr(RaTieCk]A"2`J3p8R'W&gML\DD"fCnr#"(S0oG`2i5W!u$
43('B0>3d71tXg\*;2O)JQRlOJ?@EdT,_HQQhT!=rG[<n_bL@/2L]A-p7j0hk^]AYtH1i-\gut
niba-J!9'$eV+5R-qp#K]AY=QBVd%,*=_O$n^^"a<2HHND3=oc6DPCGeJ=+td&GGM9^/&q/da
,e!g>@B]AWDr9ne/qfZ0m[e6Ds#H-ICSNXOeJ3Ai+[`869lU<V';@N`I)&0jlq@0S'I;$^Jsk
[C?MG>ShBgOS0Q@o<0`h8n8G7n=@_$hLDbLJ=9@5o_)Q>O(#$sl_7Le2gLf(b=(g]Aq'SC2)"
"Zpc%9R(cI@,Z-h%S!)I+F9;_QO7SI0gN%c?&8A:s^MA`&>O-YOIe_Cn4@k$5=.Ig[]A`KL%s
<gCh`$NR_uZ-io5#[OXAcr]Aqhr%%!V#o4=a"sP<%$"(3qsgI%[4#Z[n,npgSm=%hTd/a>s>_
oS7*r3.P\3,'K*!13fT_d=]ABLhp5YY:,H0a`QHGhTbFtK]A[NeB*$n8f5a[bSB',Wrf-hC7gX
chTjEh_)#7u/if`#:07M8HRHQ[WIl7Q4YPXO71G@m&+:#DcpMc^bK!*c!c!gKe'7pNc&8Y=R
41bjt0hVIaNbIVohqS'`9\GF0n6D`+;[=i?X-Fo0aJk.(P5hob(g9$,<@*;cu;)2R<DVJF\U
8$\pn3,<ZX7=ETj]Akg$gCjeZKJJ$]A$_4U+e:?jc-Zh6'>*)G&"ITA2S$%#bJ2JTA)rA^p&sl
bZXYdNeRg'43k6_$R3:_d4/B:_G^X.E7eTl0CidH&T1qHsH-I(VnGfa@PmL:2^DA?P>SSOod
qFf>@98:r77tAM>j'l\P<>(JL-d9n/G3dCdh\/nGF"!j`^l/)u_?p:ndh903:$Ji@/3+.%EZ
3"UD%']An3"-9=Kg.k^2K;ta1Y)h4^j)X?DCA6"@@76@pj2VrhGu)?D"/OfOGPQ[F"G05EQks
E1N!;=l'$[d(F$)Ud%$KK]A4!\n"u_"AmK.Kn?f[nU\>2A$B@YQ@!2>k/\;r%Z)\0CAkT4B+-
13uHH/=\o='9Ou<!X*f'@`qs&OBD@5Ru+t#pUPh"+Cg&OXG]AKRV\!7U6hJf1duKX-K3Bo;qY
\n9t'gn3ibr<>t3O3+s@/G9e?!i[&;l.9hC_OLTC^Wdl0$*g5$s3ZGLhf4R2/=4R-;;a-04^
r4&#N#q)iIAf[$<5Erp[O@#LHASaat$NT8ZeF>t,c?j&]Afd.GGE*tc4lYA8Q>;s8%ijXep>%
kbQr3`gm=Kk]Aa#"0UTWb7dZ#oEY=@RA/c1eLMB!_;-@BJ_N3;DE7n?trt=@[3@<cQAGu+iBT
ChAm:4]At9B`f\P*:Is!g@4e/d&>>3Md]Ab?.$Oj=o<Sc&mLH9Ll;EXhs-\`W^l(Gd/iIehB.J
U#hWgrr\tipoS:RiTFW.1eJJO7"leF+9k[[8D$U(A1[e;6s-`bR_XKj"j;8b9*):Fo)@gkW@
)bGe%op%&UH)^73EFIK]A^gg_+/)nJ$7;kcDJi;eWW7jD@tU6+\>&Du:g_!5s(2MTqUNgm`eg
g0uh,j*,SGar*eU)?goJKf:XE=%4S=aFd+`g@Hj.8]A9!O=JU;mb;nFFgWJG#!fa_BJH]A^n)p
aU(MYi^hp8qYnhp\un_JhK)Z1.HESL]AII+cM>"AHGL7jiogd^nnC:?,;6.>WM^LD2)^kO]AfM
'8uRdoO)(<);VmUjnK^g*@V]ANXVrFLnJrrnfStmVqC+sPCdO/_m%6iFqoQjm)rS')8&W)Uj]A
OF#!kpR1;MZ#$>X5&R\5r*fQZG8*+^7hHHgN"B2X>>,:c5\QN-uC+30^169/9Ps-Lhg7,gpu
7IKbYCA2fi:fUG-(?@\G)lUU&gP(o.)S\_XYc!7ULM',?i-@g`oV*C!,2l5426gQodX:V3uq
V4:p=V)QrK4D`8"<#28`^!H'M!)@-;O<>Fq2SmfdXGnL$*shbHZ+aq:jHE7=,N&;4:ZmBY_%
[o@'ZL6ainc$l,]A@>gUs'h^QSiLP#6+(P^3EVFM99^B2;,DZ?SXB=?>?MB5#%[pdb#$a&t!a
dU\ZSfZ">^b`iW@J=WB#Fs4I1<nqe$lT=@:$GO!t83`f/qTiWDY9tbjGLbk".'C-mVH1<ThC
q-Md,Y2Q\P53pB-7@O=G+8>]A;!;3e4n_t).<.RFNit;R.Ha=n^%,2:fTG*HS4s)?GCm50]A^+
[qk?Th9K]A1QLUqFZJqobUj,L6QQ;"GB2G;XMcp[Y]A>=f_enj6epi+\@u:F#3o*G.#NPKAp9+
1Vl.V2'i?V'K4ok?'2t]AoQ0Og9Cc19!s$W\gr;)oS@?CC+;6B1I'OO&ji*.QT$,CJ^P<&ACL
eW"h*$om680,dW0mn.aL@gW=1[>8%UuI:C:nA`GjS!;Umh"RC6.DRr*\+G1"_u`27SS#!%$L
,64O48brV1baBo8(<,h:en]At.V]AK@C4#0^%AD/paC!s@Jj=7!0TUFbq.ReYbal!=B&oO\^"&
]AZ6.o/"pLph<$_!5M2M<77Hppplh.&##okgcNO\r^0^@?!o&gNi=2rVX6o[@^F#q9hHI[_"[
<\L>1+0CUTQiEOq*CDlpG]A!J%VpG"U3/84F@0"T+P5?q^GaiVT1S`kn(-M.QCYGlS#a'P-OI
q)nIdQnJ.`$P(gbZ/UfSa-l3e07"fU/[3&GKluTg,Bj1%5p.6s%n$WJbt-H\ei5E,M_T<QPq
)6A&4KJS[mGMQ$gek6TYSV^0ft+l]A3>c8ZVG(//l6"lp$`JL-k,:b'LBsNM+&?p+PR.K3O*V
]A>NoHp\^8H[O%POMOT"[/6r`!TWfQt#=_o<`MX\,VW7kArN?VQ>20VW%]Aspl[]A`ML(XJ?++N
NRlG-+e"MNt[Z/q3BsWOnVLl.)n(:7'!n?g_9Ru9dtI^2V4-U$h^P4)Ym/[nCG6/f$.dFG89
3N9!2O!:cS@&'.Y8?b+RECA5kqjB:_9K6[nWpMRm-'@10en=L$3]AO%t<-h;=cJ>$#<48m/,d
+,r-6I[ElJO_#Q97gX^4.4Ko+G.;bR7G)^u`L3n]A.HiD)QT<Fd6V4L+f_0^-BDsf_O/>CBjB
5_^FsYtFKZB>`5bo17UjZMLI$GG&]AUI;_@LdD-g170u.K$Y=WYS7<Jb*"Mp$fA0MEm&!(AF;
J('/Ps-e"Mti_sZP5C7dsE!At11ho&c9jVMcql)g7,1Y9'XdbMV+)M?o<[&r[G("K'A5bjKE
m"d<;o9K&[S8,CVjm#rC*4Ml>sIEQ2n`WdKPhHeKt;^EWOC4*j6s=C(TlLN[79"R$uF_2#Yi
&P[VImY<f?"kB]A<B9im1BfSdEXD.t@l;CgIa&Ca_eR*]A,tFiO5S-I3-NFXgEoi%lQ;@X3=".
I#,%$SP&d1<q4/'ebEBAqas]AJpft?jrk,P8fn5ElQIlF;$<Zr4CPre]A\qVgKSpS2>_8I_^fQ
_POJ*SjF$?oWV&rZd?0U0NHGbq=A\j?M6.mJEe,9Mo!^Khq2cRP(-d$O5ZM7*-gpP[Q03p6H
mQtQ<:()0RlXMZFjGOEqj1En6,B:HHe06%ImDnHi4htKPL7$p'`fS=pgZn7"f:.CF`pd>XRT
9=FN#=^'-'2if+R^dG=]AhPQsf"W_c5.m+Q&4AsN%/9S55?=[=UNtZ<+`&)G*C=cGG4ZVKHYh
4G;gdoM'5tIKd,gc6eI]AL93@C%nCJP`d5nF$1#\SbNi"$m<Tst#>=8p.@;',Hu=t7_(2ZcDs
h_,5W#n`'mh0t-'&_F!/@g3iK[mVtmq8W=\^'m\+)A#rE;]A1h[0QaK`$WWKK(^d$HfW]A+IN>
C)T-KJWe.(;Y!$F&@X'(RN-rAS$lr,6[17M6V+c=s5=a,I*4lJRR%`DJd<'hMhf-*`o&I[\m
f'3%-BaG!H8VD99W7P<_.$=Q:ikEa-qlgS%Q5h2[l@.nZ.q]AfReOP,Uu`rQsp8!*WP.1(kc9
dr3mG1>1/^BML8(R:CbiF8Q.qF^_T=*jPdFB7[_8!&ei=G5UFG!(Ue3^.VSOEW2Z=@2+Ks%/
(\fI'qpEem'-\3E$!aOjr,RL*o"kcR]A!-O?8%cin\.g_&Mb[4]ADW$_k[?AAC2C,_FKo0;-,_
"dG]AKjtEH^VLL'5&r^OXi/Kj)i.g5e7\6)Q<T^B:M-E^V$VZ:QGJ?Qnm[8M,#]AgG0OgRF0jj
`=i=WbJQ+e#QZ2Zp^WciNH>%Zl@DIMj[q7U]AOToV.T'dKTV:ZSLHRc`K_PWRcR*r%eAb,k)^
eE/7O!F`tGX)QW6uN:KFc3dW\oLU-qAqU-M5dsV/mf8$;&EN&oCnol#.;]A<1Te61']AE=O*`&
c.E]AA(cqOC,YZr?<WcDoZN:SO4=sB/@4S"b.HiW4&A0]Arir^G3UkB<[lY_.05eBZ;B\_pec1
0hs&G5>CN\*l4N'@-)cD\SReWt]A<7]A]A>\)?u)-n6N*O"2fiBe_XgD$rSVhmTV3gtjm6<N'$t
djRk8'F#??oOR%AT4:1ch[Or:nj>iXqp2q@\.IiZko=t9HqYC;b?6W0AB94nj=;?k@qYt("W
IF".?Tl_adfY%C``D;;\efUf-JR*2L=RGrKHIAJeLF+"CZ>55f6c@.\g8F'@=Ous""LE1-U1
d=nH3:["Tbk]A&8%#`9_KOs,,"pqrW@fK<enNAX'ftBHd#9k6NO6?_f;U"C"f$Q%1E8ZE0%p2
Q^0F:\V`"oN6trZREMH36M#$:il#Uekb2Er;i>Pc3b5U"6J#@T0bbur/3]AY)R7k=SqXa>KNS
%!A`s)YiS5Q5TN50+C.RbjI!c;G2:.*U%7;SV);s*)/!LEX,<+]A<"r'gj\3+H7UDlr$+91Um
PQ,h)//pWqp6Y6Sk(=AAEs35)PE8_g"Wsqq#;$jk*uoTEVda6pd9NORN'K",pWUhXfEku"e)
>`f.h=Q<)7Q",bMJ3bNi90shd@l;W#(KDs!%2MW5o1\Id30%Mi2!N,Q0^EKYm1<XSQgS;KU;
!fg+QPnDSKMGJu+bqQg.c(]A#Re<r\MVeniat2;&P9hDSgl%Kg7u6ca8*i89/8AZ@.G3*cepW
":#N1B5nNc-3ePo4+P/-P6&)'`O$jR%"@<%TH_=dJP]A_=,^k!TJ;>cHKrgg.#1JmNlbK)9m%
//T@+IS!qL<sCr:l)IjS?#"^EM(]A'stRAJeVh9FR?UCi6Qr:p/D<rH^I::%ZhiLO2rb0h=dE
gkC!fCj'RSkursqOWuUAI1",J-0]AnM6\cu,+;Y4SpV7-$^m).ZFWEW^\a9knc/drOaO"R]A8R
G4mcWgJX''!Saca"kBUTV\^1DC&^12=Pe?Zh%S@8uc)rEhE@V=,0r+;DH\s,-eg%3d-iT1;-
%QS/arF#Ph]AD(WKBBV>-On,u5&4m>;rV#96'7^8aOq`i4$?aYi#(tV;fm1CE'c5`dhgrj3)d
to]A%nga/O^t$`p/B=d%;f=3r59=]Atr617l\m-K/N%hLbMG2t0]A!>_Z-)d:&\]A?<;4nhM&:<9
7*H&#EUL5dU:IqfV^aA_,uB!-cWW/m9H(b>nu+JY<G9+h22,JH?)nI*.S?$%a_X&.)/HAXoC
^J1]A3#sN8S:;?q8cB/9DH@AX07F6/W)<"?tY:*(WqC\M+YtRlm'&gM=<9XN1QS>oD1DJ92%m
U:2r#U+sA8"\M/OkA4'[fmP]AR^rpg=p3X\K:&YL*P16LZ`f6;'F8Y:':-c3SDX<CH_N/5)34
k;o%?H%`fc@a+PX-c"9fiqfAH+XhM>i-BteLDf523VDiP?7]A(?ieLZqlJHglb0L$PVWHB<n"
)okddlF`M%1(UE$@_LVA<JcUo-@RL0M<UC$7!J:>8cbR1*OL>GVB6Kr49Y<_pF>hL\""5_%+
rIKL^.PEgps(6/=+s+8CAM4gE@8F?Y**BcN#ODbZ3A`e.sQkGsHch7=.d@;Jl9E9?8N-<:R<
ht2'`YgdW)=o/:K;KNb$*T=X5]AE3*JB]AJUg9<Nu/+ITY$b.>`*G#l9dn6D:lrM(M6_7\WBU(
cN0JJ=0CT%-=,j3KSeaaQf$-*UC8)G=?0Tf7\eSX.@N4Ma`!o&7[L'VG#OX4DZURj-u5$`)F
Y2Ehk<M1apZ<`jl>0_`]A;_nEc8>.V"4?]A]AST(\*p:\+3]AENkGkR:@>Jdq4JU;^ZEYHW<bEVJ
NHGid,dMJ7*<Mf7l#p_X&-liQ\sa-'SDR*a4><Rf\!9A[jGN".5jD8-nA+A>N-6a]AM@8()l3
o9)cIbaA;=rC0s/ph5d'Mon[tG"?HT1`$Ql"R3^/%$QqU&jmY+2@a;@+6EiVif-Agt>Af<^'
N@8u4L(\\Gc=`HtdZK>loa`Tq38BI08QS7L5a0)I@$,X&'e!(]A-SSIPP\@@pmim-_$!t/9kb
aFhcRKjC`dH@PWV<&moF.4)BZE]A_H9sSR('*]AHL1(\Z>X`H@`3;Z#BUHjBF.^L=5hdfQBJMQ
pVet7SgL'EK`oC4<=^2,m70<SlLWnSrO^5fX?j;27Z`WTnd[LCah(///\L\R9TH;Mia--Gem
-?mKj>WQU46%)K6)?MSR2C5pBWLPs#0't)NH)KpG>*,^8M$4\8(Ams?WR9[a>>(`\$Ik(hs6
8Mq?QR!2^`Q'g2W8fYUB=X]AB(:<i)>i-F+cU-T<Kf!-$H)@l2M"abqS`m?(6^P>L;$/$fU\X
/!7[^8:gJFUn.PNlH@SqR]AGuEc"Y=&22X\^$mkMoZH$3*Ej!rYR^]Au@G\]A,cq":tn.(V=Qnf
#P>b)(ftX(!:/qm*s@/;JX>pU[M[DaH':hVefB@dH/3D<UF7[8l?0c'j=`9*V56^16K-!V.T
[_(,gZ1o)0;LhZ`Y^-.[te;tN,gr/;8(+"g=+^$;C,J3)TA\c[ui`DaD+oPtI'k@e:BG"apR
J,DF0HeS$#fP`*R..62CN;Y@"DB+i<T;;NS#_<FK'bi\3G^H-5,JVQ?!c7OgbdSR0sEMW9>O
;ILtEaaIDcn'I`k"V7Q2fOomgB?=BjjMgO?@\JGeNM(!8&hZQksa@r^4gjAkuL3>'qVk4`hW
HV`[^VjNikC+@:p8@bc;@g9nOCM]A<s>"iU1Q7jLn$ihCum8E$.k,*aUn+t@'4%?SpT0$VhEK
HN:\O_^`$@5ZSkk4o,S*Z#H$]A&^Yao@%#>YR2L/UDdtNnS%kd>MpFs/k>Om[h1bgBQ4;4`,3
GDVsUo/FA^$=gG=gnc&NCMh=*W^Mn8X.&gR2IX+u*CJRbL9QW<Hd^6Jte;>GXi'1So,Nj_Kb
kuE$aV,!'6;;oEFiF;;]A`fNs?JRF5CcD3;cCr7@ma<Ime!^)M1)@`u(NTbceBJ\r,[391eR7
h,`(-4H#tG<g5"OEaUuKqA(;380iG3#$9ecXsc/V*WITn)t3%+/(GC%>>+)J$rYIF(;?OlMk
0XWl"\ul!nXAelPW`\fqEgaEghRiE4UCDSl+Nt24U]AK]A@h6.W$*TYQDfUW@d4-=*5$]A,QP\&
15pTEqE_hr?m*BXa9(O6I6g+,Dn\aj0I0Y:6]A\gNck4)8M:S:VZsJmk@o'7^0.P5ap5rM:H<
n)"+0jAM]A3HMQV0d7+k'#>%2<>"+QRLL9qBLN?M#r@=ZMuO'0KTXpWAj9C@g7p_@;EV0Xck%
ifA4:E(k';nKEE/QW?%376j1j>9sIDoDd,Rfe`]ACeKe,.C5QI0oEm$?6A"c%b`L78^(*:":8
nG4>*4?\7Yq<1mK2#Qkk[9SQ(r<")HVtR=8E^YI<=D_M5!47mrc.4I@b;)i['CI)ELXbc$OH
+.(l('QJ?l,ne`MC@.`r5H#Ero@`WYcOEMsWH\7t\9/`MP*fH)MSR0-p5\%d\eaf<lO+N<f#
5DEG(L,\a2$$T]A*^?YKmE=Q\#W@[!8Kb9Gp=>33lYEc/&jM(I;YQsAIEBgS_E+PrX]Ao!Ila/
NF1N:uh:oO"R/ck%+)0+Zi;2$ogR,@o9C$=XNY:L1><q@+`5pI:]A0#[DYi;Z2b<Lod/NuCm1
>^WhU`-t+lo2X3g,2S-W2pDJAeW><Vdc+HoP08g<HR"eKt_#%%KblSD,t8Wp=,8M71mCc`<k
e^55?Ppab?E,gt@P:LKZ<F:GJ`LND,u8knIBTP)]Ak-WYN^/aMt4`3fG\&g`[$CQj?EMkm-A7
nTO1T)g!G/CC\ip4NX_S'e+42fchG]AT_cn68$D'`qD.ZB-_DL;MY]A/Gf92fZ?6#OgrBjIU45
C(AhiU>>%*G'QNuX:(%R?`44)cJ)3:N.1YICN=D8iB4%%!;eQjQHa^2)K"p<\p6Z/e`'C4U!
bi3bZ";k[tUd:-6k6"<+U6qi2d6I,>eZhEYL;_ZHnheWL_ZupkCRY:l.&PH^TIGcNqM`k^SG
NoTEf'Kk$[f[ruf'Vq*+)%IQ=bm4=>EE_9ZG1;#-C\eE@f[Ki/nKMa)16@>:VpsY/`f(&;i/
gfe=H@B9BDp(!FoIglFh,uS='r)+iDV`AG+gT?#7?o6h5Jkm.<0<.Xp,!LAKh;ls?@=(mFBE
ri8PkVgaM86nEA[q]A7ag1eF%;^HeCQCofbJYh;cJY_Eq>dN`&'m/khM*a#OnL0b3K5>*.Yh&
A!Y[<T#)VYc(.B0\kpEKRI"]AJmlQ>\!+?LFonVAk'M7KZRSH<>N3]AJ+L;D;b5qorOrbmA8A>
8A_c,]Aq)$A`I,5FLB]A<[l+I,cj`*=PsQ<WR54jW72LknL>+Q&Kmp_BW_D;.YMV$(s>el7)+#
;i(*,-lBR?bgUT@,I0B4-=Haokn4N3I.3%3(,TcOuGHnR]Aep#I*HHBfgW[1=-4=/>M561hl'
Ok9CDSfTEYBV\mHW#:6NS_iUXdSB\S@(%i",kMLjOYWcIWN+_;if7iH5g=mrPA2`d'[\a2t#
p$(!K6hg!ITd/6%l.<\#N2#AALln^!EW5XUK:DW&#W(l'F5u3DCR^l<KW2SUh)#&W?-0H:0W
8Vu)?<IB.-!tgD=I;]Ar5(>4ljq`dgLXAol,EXO~
]]></IM>
</FineImage>
</Background>
<FormElementCase>
<ReportPageAttr>
<HR/>
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
<![CDATA[1066800,2171700,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
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
<IM>
<![CDATA[m9G!;;eNO'M;Y>CPZlZG(">4d4*B@*%kUcq+c4ZCR9l^`kAa"`TqsYh&E5RGR!83UVm@(8l`
Gs['$D]A4X+dJp.7-QG7m[GS&>fRU,Z9;Z(eB1@&/4W%'<R4/L7F;4[pGd'LOUTHbPCl+mJYe
SrAa`6(N1W'/IPhg5u>oI5C`'e&/67;+T.!f(8IKQg$h9="T@o,)mAZ(FhtV^hApm@WM_Q0;
LN_.pSm<CHd?'@=_+DaY+5?\2t<"TpamB`p9,kDRprQ7Ae;CKGOL#e>CUCU6!WnnUqY7R^bO
nHHX2lcei2jL\Q-23IYC90GIV($]A`taEoGkK6%0&4b<3M/mX+E`JYqc'"]A+-]Aq"cql\DSCA=
G4)D44-C,/&_7R?W7V.-"eY]Aq,D5#&qIC&h[^o2Hh=tS`?F5*F,HN?W&j&9KgrF[0Tkp:!'e
uF,kF&Nj:&/nJ>,;.`FoOVEMpgp'>9*C)ipHY=a!\(.<RL(=<90Eb$QYrI6*"Qi8'=KCgR,U
SKjIgPm#"F)*#f>-b#!</+,%=&(X_*c2od,4s7PO46QoFt35\r^W2!1V\GWqFN5<qg;gM<J4
0bj,[iIAsX#)"0hr:tNc$d,:2F_a?+PeKD09Ae3#Q=-d'r.\.dUnJQ'GaC+_s$t&<V50g:SX
Q=GHIT\Jk2X-8:kq+HF)8:(\c'C@8F7U4K52'UI\VXEX\r5e^$gj="F9f_F:rD".#^^*7/8&
</bkt>2hRBX^SD(Wp4Wm&dc,6:?NE;ojG:#h9akpf%f1!$J_Pl/Y15P[DU;.T&^G4]Al\D:;*
)Hi2o-sgh^B;?4*T.BPi$IA\*\i[Q0M,I1JFD`)CC10[\^j58(WoR"ntqRMJoWP0#!krRgQ]A
!.`ILU6LhLuYI$Ka<%*C%5YcsR,'JZpe'N_"p`i\t'hJrlo<aChQeeH8UkilWj5#ETEn,Cg;
/r`Oq.=3<pbQHWGDDs'q>g?_lMOi,),6nZdauS-dL%;7)/feII`$X0CGeY,.!gaWKm^A/3b#
e&h')7RJ.L6S-0LlT,<Z@eXQm)-=6K0%;TPhn;3gEPdRA.+p'm)<lE/Ld,X]A\8B]A(A`@QNlR
(RgIbMcu*$<*\ZYN`G/7]A;4o!j^3<fnG"9j5PdhKhHZbS]Ac1ldTDNf2KRZ5Qh!2nj*a<>qFe
h9&qu!#!bm!.bh6iqBF09P.6U!MqM;$oQr)A=l'YXFjCl]A&Z2h]A<b4!!Y1n7On:H*FfJdXjD
kg0r5Yn\B'0W8#)!jLu)e`AgYL.b%sX&a@?ZaNDg>ns\nO#N0@HJCO5i(KKZjNdcWKT"A/g]A
U=[mi3F%cU[#nj-8%f\0fRb3X0:#g'<e-Bj9k20ga+VS:liPb:rOp<%GCo:%eM;VP:iYf:d[
*l]A`AjP.K4R-lqY(%"*KO'CXmtX3I9RmD&\BnTuj\=N(Lc=f&Z]A%Ei,]A:mWdqUa&Ia^'p1P%
W73fRXP]ACb[+BNur#:ADO:?oDFF%*<,I`\m\[Yo`fG4pk^nNL;eF[5$$/^67GN7ICb*..$>i
0*$YJ5MUr*pRgm(eh36-Kddo!;h%!l6s>AK`]AG7@(I'W3_J-DkhP^\E+fu:EGd-I_.4q+"kV
_p++#"J,$@uJ(h[`L@23is.;4=(_ONDKd9G^VD.RnMbsX8BZ);pSDnK/g5;!?N_#+^28f6fH
a>f2Gg7POHiCN^`D`JR:O++cOm=#V8sjY@Uu@mn#toU0qfqrFolB8">,24+h@UBE#KoKnqV#
eF@^7[-C]AQq=pmkK`E"@TpN7g)*L)eAiC&[=?F`8sS9tD]A(OZ&A.;*FQ_IsY_.0M'N?r/>QQ
(Tc<.]Aj<<h>m^,g[Z,*noq6Rd8Bg*FO$<bhm5]A:c$[2Q"C%jIInr3d/`kOcXKYY!kbLGr=[*
8=+fdjB,#Gps\]A?,LVhm&As+9-;?iAK1QA>$t<U569m#bg-YoVSKAFe]A!/_eq1d^Hmsg$s`L
t!o53oC<nohQkF>ir;I+qkEb/nK?FfVd4`dd1gAlkXk4h@]AO=p3j#iL/?*l!!YLnq1J>%"g0
&Y"^W\hPAR,Hp4Dh4F+%ae(^7:r7_aNUZG=!f);'=32Qr8>ngRb&4P`@V2!_$i#0LJV^#BTD
ni.//n-F^W.#iLAM^I#WWqd&oR"N$U/!JE^]ARO050K'E&iY_IJau?^ZnJ$O6MC2?1A9JG7h#
gXRGOZfnnYiA;9cl!(S&aYkKm#11<\?G]Ab\fd9=I`n"^1fp[%h-4pW"YqetmGs-U1*\6",]A/
As3pHcW'0CI/Yc7&.+q?o<DEOSh?=4rW3h9=d"Mjld8bZo3GYj\c:nX38t&Lf>bY!B(5N-#Y
M3'!Z2`Yp6f(u(34_ofrh_749`]A)SD@%4UEC\^o"TB7@k'^6kVW]A/YFW;;'u=Y>*tmq/`ptb
C"-cI!(m]A"TcNgY`j1j3!r9S`]A_TCX`6\,D/(af%JZN>r"&!-1m9"-Lb$h*)E[m?;YWm5,'#
13gtM1%kQs@B@8%-kQd91]AZm/Xp;]Aj@JRr5^cp#X8m69B6rgVA8.4:7FtG<S19]AV/b.AJs_8
M/;>g-pQbTFUJ@TWq`fJWC4(*`]Ao!%(.7VZa,C3<kP\\IW]A<fQ:W)@'2d\qepqFrpj/Ck>'e
Uo@FR;!&A,ac&9Xk$t/gg-i<gfS\SBO">]A'6::A+u+pqMefMfQU*LfKM8Q'\$Qd]AGojgDB4C
kIk):h,-1WZ\)oB4+=Ya_omF-uQ]AI03Vbqre.7F^bOldIcB12fPUBGAfRk=`:c4(0,C80sDW
Mf'6*rd;,b*lo3":YA46f:;[oMU\QP4OWGmcu%.9XHk;O@K+&Y]A?7`'[_=;GDc&Y(.*!EHkb
5!e,CmEhE4+@HRDCifQ5TXOUh\r8.2iqGu5MVK@1trhG8f8:FCsC4$"Qb#JNZLT>K\V"W^^g
Ha^6X7[$Lg:ESb%8+!oRm3W5<"<7i7HMVL3))$qp3'>\oQdI^JgeFo)lfX901LA.Y'LW`.h&
A1;pXrMR^_Q&"e"r(=SOb4aK)]A0Db@VDBp]ALn)%l4qc1K$%pfB<\k\03k58t?0G;-Ir7/O3+
7dc1k83EK&fWhi5(^aM(S<?8C'%L_]AT7;1_$en2Dn=@CJ[3"b)@L6^#.)Q*RZ02Y%tjZh2H;
SUYV3X^Nc(lt$&Cuo<UZ,5SZCG[CcfumdPMYTQO0c.i_:lAPBr./UjHTJ6uR6JKe\`Xf=-@j
@/JpS;H9u0@)#ne/U)Ifhg/8%/>'-s%<rk0HYP)IgTA9APE@e1M@PtWdIg'_P&Go:1Roo?90
*,!.;%nFO=*KpL&@WY;sr9,/0CL60CB(:nt18G%TN2Yqh[1hS26o"j(@\p:5["0_?I+Pr'):
2d)&!jB+i2iXOh5;p1X]Au8[*gflg(2$%4=<?65!H_p"mt,/&^W[f!@e]Al@ekb:oJUuNd>RZu
!6p6tbRmqT:-1ogIcc"r'$==(((GLM>*@W?3"Dn&@8DYY*!o%bLAt6q_X]A_('7>:pD+-EC52
\G1b-gCrYEYP*@9Zj*o5sCbT8#/b$?p]A:SG[f"#GV7/"H`Op-7]AVTRPF]AH+Ylj0<os1'I7<<
;3lN^0m4>pmlANV?D"?=kYmJ:82f1_AHb[<t(hj[.R@u_g>('`f[&s+JG[t2F%6GY5>H!]AX*
$U(7tWb2H5;P7>A.hbHu.'-g5ON[s,Kq<J#iM8L@9_oF:!rMR60>L@6FNd0Y?^QS<;Y).ULD
d=3O_JoE.,)9B194o`T2[QbEe1#jE;=3X]A-6uCR'Gl?aCU/BjPQ#\6j\M#M?XiTf`s@e]Ard5
)A/rRE@h<FfKkij]A,]AMuV]AR0SYF.Wu3Mga8nMrh0s#puqT05ho7^UqbN.)4-K"qRK,5]ASq;"
EuWiI/@=I,u'*nj?g;?'Yt+mA7,rbc3f2g"4">8FuC''FO7g?Kh-bi`>7&-'+g0f.up74:l_
/Pk$A;e>JRoN99e$IO_uK,j]AT:/;6O27.0#-cKO\Lk^R7TSoe#1LJ^A`ojoaJRBl%,E?:1!?
4X\(f3'eaXQB&VOs"''hZS564%l?d$rPQ*=bGI!=j6BhWb:fgY,]A>KCA`"<V<iN<ULNKQ_&<
$($J&58hOKQ9t9c5Is,W2MV1ccJA39ptOT4PGu:.%6XZ&+VRa[LCDTkV^r`-gKGYsA'H&`)V
P*T/(C;VjTCj<<>IF^U\QVA8WG!BF_21*h>ZKBZ`e%)&n]AZQ1![%rf[Fe*4qWGek#l7l27a=
-m&["57e",$9@OBq[UG+#HKm/j6LBO6DfhSiIioJ-hO\0LE2u4Ihp*UiTRT4^%1]AElP$+R:Y
IH,I:]A1PZ&:3H*]Au\95QE?+>br8Jk:Vd>jYL1`2+;07pPh"m4q8ARX]A-VdEU^[+"&u8N&(Fu
^TQE.]AYs^?YkloCpEqb11q6[87TX*5F3#GaP4ROR*hgoVCd?Z0TnI,0:#H\7-:qDq:[B+n/P
n7[YdMPMB@EtM*ME_e&PBsd[1&uRp/DANRX;^d]A,E\e2j@;2<+oO9`Jc<=\]Ah)KdJe9V;N/'
goCc2'0>3.`@/WrRV:KZ]A3*($Sia,?EA]AQRK8;D0[gGCtb]A--un<V)2.!pI_f&fKmTG5!4YY
j$qp!^Q4%Q-ugH\]A<bm_+r?UR^5pdpR'^;JSK"uX<:DpV^XHq$[AA;Q_$4*g&Bbs&GkjTT=t
&(eP@u^r!`~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report3"/>
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
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[D0gIM<;ZRmR5nac#%Pi5@Lstk-A>:A80k/X#n]AWu8qnn=2D]AC@9,fA([,#!/&k+">#$htm+t
>F38@EWH0>DSjf(XX]AkP+fDSF_1ocg,j>cguHID^1+Ib=5B78K7^18_`W5NW=S5!<<d-^a!b
M.NaWQJ5o1L<`Gu\\KfoB3@bU$EJ$/deXF(d9VGW:CP5oX>Wb@JD%H?d<N\/-R61pU0l4-17
R%R#b"be]A,]AToU`jq)%I#SJ?_%i<&;:ZLa1*P:*HM&%R;,[,67K;6iIkR7Mr]A2l8<"`+O)ms
qXAu)EH6;5=QWh'Ln06raO!O>Kd0;Vq'J1j@erVZE:lGIK7e;.3a^n>PLpI_L/goEB.WqeZ&
*(Ohqm!R"8V5BS51#pB"[oI\2din[_m&9#/8g!G,/_i&7_DNKTV%tmO\O,`BhYcGXE]A`gUf2
KP/HOa--D%tX9[$[Gc:Me&)Dc\3iK#gn\MS%YCd_\D[8eM>aE7%N5$[?`fs%hfMJ._Z:r<4J
B$(d$!/]A'Cjc;Vbhmi.8/c5hkPQN_q%pN@#g-nC-(^6&oD!e^8n#?^D*,MRh&AVf59jTg1[l
:<V9/)-E@Th,:+FqTdX.F.Am]ACq?+D4%Tg;m@FnEL1^tP+91!L3j'h$N:IIZg$`%EtFFY@81
\<jHdlur\UK:@9NakT$F#)J\frNGFCPr$)GAMniLo*ngCTIY.s&#^X"hq)?hqbp?e5AC%R+m
B$,]A-;@FMA5N5"'>4g6dReGu_XOW!rqh=0TZ8gr-e^d9aBqJH@:)pX_W,$KKJ6d,HpdL:D,q
i%B1>oXa5-$@&;X8#0e]AYDp]AZC8CejV'(6`ql:?q1M[&a#6^35iQQ_VuhNVfkt1YA8B)GOj'
facU/DPTjui:m;l.$i.;:7bB=N:,8$^&0&ftrR)'(Lt&r?)osXsmgcG`Gkn`@[C&LRC-+t/X
aI&Z<u")^>De!X]AkjLUa:H:1g:$?#q'2A1SlU7>Ke(g8UofXBR_q2.OGp'W,W;/A1Ee#RQgR
]A;8X34mUOrY<jM/HWk_lN*EWWMU%(l9MLrJnM:JYDN8ZPGLX74q]AmUeN_'!?+m>B"[)q=bg?
h$D`$T+K3FKL0%-X\KqdO!V"&J-rKC>OG<t8KiSsbC1?j)0J>$SF0A95mLf,Xor`=1!J'#fE
E+Xgi,^:>EFl),o(#`3`-b2g]AQm('<VQlTOn@)C(#6;$T!Q10HR1c&=LbAD"Gm"HK%[7\=u>
u2!M3hN[LN!.5/55%YT-j7]A8;S.7&?7nM$m902(jslm2:fMi0^c\L^O>XW+pWg#9Z0S7aXhX
BJe9LDc*P<3inPB_t=I*<kj;)9MWUGH=="CsCHEb9*Y@c=??drGJa$(1)]Alf>oOJYT>'!:RH
=Ga.*$OUT-56#0ULFOa%l#\&PV<Anat`ZXB2:j:QG*hYaogoRcAVCnqWEIaG5[FsLPH!Su;r
AA\?=j"#;UlKAbGW!ttPpDm8;hh@G:#8>$olXgY>=T1]A=EPtftM\f&phk$c(DN%+Rn=&:3'S
\*k;J!)--ZDgqJolqtT_h4]A<NrH7\kkmC-I;2"lF'7\f,Zb;+E9sLS#&hnSR+F8p0>7H"#DI
n&F%mHC@`CM\?WDtGDR0P_'D<oo7n,GJ]ADF,"!'Ye\id.ZSG:LNG9:UMoP^2>/PY*c?((i2S
-ou>I\@AHbp'O>:4rP35bVgGPr(RaTieCk]A"2`J3p8R'W&gML\DD"fCnr#"(S0oG`2i5W!u$
43('B0>3d71tXg\*;2O)JQRlOJ?@EdT,_HQQhT!=rG[<n_bL@/2L]A-p7j0hk^]AYtH1i-\gut
niba-J!9'$eV+5R-qp#K]AY=QBVd%,*=_O$n^^"a<2HHND3=oc6DPCGeJ=+td&GGM9^/&q/da
,e!g>@B]AWDr9ne/qfZ0m[e6Ds#H-ICSNXOeJ3Ai+[`869lU<V';@N`I)&0jlq@0S'I;$^Jsk
[C?MG>ShBgOS0Q@o<0`h8n8G7n=@_$hLDbLJ=9@5o_)Q>O(#$sl_7Le2gLf(b=(g]Aq'SC2)"
"Zpc%9R(cI@,Z-h%S!)I+F9;_QO7SI0gN%c?&8A:s^MA`&>O-YOIe_Cn4@k$5=.Ig[]A`KL%s
<gCh`$NR_uZ-io5#[OXAcr]Aqhr%%!V#o4=a"sP<%$"(3qsgI%[4#Z[n,npgSm=%hTd/a>s>_
oS7*r3.P\3,'K*!13fT_d=]ABLhp5YY:,H0a`QHGhTbFtK]A[NeB*$n8f5a[bSB',Wrf-hC7gX
chTjEh_)#7u/if`#:07M8HRHQ[WIl7Q4YPXO71G@m&+:#DcpMc^bK!*c!c!gKe'7pNc&8Y=R
41bjt0hVIaNbIVohqS'`9\GF0n6D`+;[=i?X-Fo0aJk.(P5hob(g9$,<@*;cu;)2R<DVJF\U
8$\pn3,<ZX7=ETj]Akg$gCjeZKJJ$]A$_4U+e:?jc-Zh6'>*)G&"ITA2S$%#bJ2JTA)rA^p&sl
bZXYdNeRg'43k6_$R3:_d4/B:_G^X.E7eTl0CidH&T1qHsH-I(VnGfa@PmL:2^DA?P>SSOod
qFf>@98:r77tAM>j'l\P<>(JL-d9n/G3dCdh\/nGF"!j`^l/)u_?p:ndh903:$Ji@/3+.%EZ
3"UD%']An3"-9=Kg.k^2K;ta1Y)h4^j)X?DCA6"@@76@pj2VrhGu)?D"/OfOGPQ[F"G05EQks
E1N!;=l'$[d(F$)Ud%$KK]A4!\n"u_"AmK.Kn?f[nU\>2A$B@YQ@!2>k/\;r%Z)\0CAkT4B+-
13uHH/=\o='9Ou<!X*f'@`qs&OBD@5Ru+t#pUPh"+Cg&OXG]AKRV\!7U6hJf1duKX-K3Bo;qY
\n9t'gn3ibr<>t3O3+s@/G9e?!i[&;l.9hC_OLTC^Wdl0$*g5$s3ZGLhf4R2/=4R-;;a-04^
r4&#N#q)iIAf[$<5Erp[O@#LHASaat$NT8ZeF>t,c?j&]Afd.GGE*tc4lYA8Q>;s8%ijXep>%
kbQr3`gm=Kk]Aa#"0UTWb7dZ#oEY=@RA/c1eLMB!_;-@BJ_N3;DE7n?trt=@[3@<cQAGu+iBT
ChAm:4]At9B`f\P*:Is!g@4e/d&>>3Md]Ab?.$Oj=o<Sc&mLH9Ll;EXhs-\`W^l(Gd/iIehB.J
U#hWgrr\tipoS:RiTFW.1eJJO7"leF+9k[[8D$U(A1[e;6s-`bR_XKj"j;8b9*):Fo)@gkW@
)bGe%op%&UH)^73EFIK]A^gg_+/)nJ$7;kcDJi;eWW7jD@tU6+\>&Du:g_!5s(2MTqUNgm`eg
g0uh,j*,SGar*eU)?goJKf:XE=%4S=aFd+`g@Hj.8]A9!O=JU;mb;nFFgWJG#!fa_BJH]A^n)p
aU(MYi^hp8qYnhp\un_JhK)Z1.HESL]AII+cM>"AHGL7jiogd^nnC:?,;6.>WM^LD2)^kO]AfM
'8uRdoO)(<);VmUjnK^g*@V]ANXVrFLnJrrnfStmVqC+sPCdO/_m%6iFqoQjm)rS')8&W)Uj]A
OF#!kpR1;MZ#$>X5&R\5r*fQZG8*+^7hHHgN"B2X>>,:c5\QN-uC+30^169/9Ps-Lhg7,gpu
7IKbYCA2fi:fUG-(?@\G)lUU&gP(o.)S\_XYc!7ULM',?i-@g`oV*C!,2l5426gQodX:V3uq
V4:p=V)QrK4D`8"<#28`^!H'M!)@-;O<>Fq2SmfdXGnL$*shbHZ+aq:jHE7=,N&;4:ZmBY_%
[o@'ZL6ainc$l,]A@>gUs'h^QSiLP#6+(P^3EVFM99^B2;,DZ?SXB=?>?MB5#%[pdb#$a&t!a
dU\ZSfZ">^b`iW@J=WB#Fs4I1<nqe$lT=@:$GO!t83`f/qTiWDY9tbjGLbk".'C-mVH1<ThC
q-Md,Y2Q\P53pB-7@O=G+8>]A;!;3e4n_t).<.RFNit;R.Ha=n^%,2:fTG*HS4s)?GCm50]A^+
[qk?Th9K]A1QLUqFZJqobUj,L6QQ;"GB2G;XMcp[Y]A>=f_enj6epi+\@u:F#3o*G.#NPKAp9+
1Vl.V2'i?V'K4ok?'2t]AoQ0Og9Cc19!s$W\gr;)oS@?CC+;6B1I'OO&ji*.QT$,CJ^P<&ACL
eW"h*$om680,dW0mn.aL@gW=1[>8%UuI:C:nA`GjS!;Umh"RC6.DRr*\+G1"_u`27SS#!%$L
,64O48brV1baBo8(<,h:en]At.V]AK@C4#0^%AD/paC!s@Jj=7!0TUFbq.ReYbal!=B&oO\^"&
]AZ6.o/"pLph<$_!5M2M<77Hppplh.&##okgcNO\r^0^@?!o&gNi=2rVX6o[@^F#q9hHI[_"[
<\L>1+0CUTQiEOq*CDlpG]A!J%VpG"U3/84F@0"T+P5?q^GaiVT1S`kn(-M.QCYGlS#a'P-OI
q)nIdQnJ.`$P(gbZ/UfSa-l3e07"fU/[3&GKluTg,Bj1%5p.6s%n$WJbt-H\ei5E,M_T<QPq
)6A&4KJS[mGMQ$gek6TYSV^0ft+l]A3>c8ZVG(//l6"lp$`JL-k,:b'LBsNM+&?p+PR.K3O*V
]A>NoHp\^8H[O%POMOT"[/6r`!TWfQt#=_o<`MX\,VW7kArN?VQ>20VW%]Aspl[]A`ML(XJ?++N
NRlG-+e"MNt[Z/q3BsWOnVLl.)n(:7'!n?g_9Ru9dtI^2V4-U$h^P4)Ym/[nCG6/f$.dFG89
3N9!2O!:cS@&'.Y8?b+RECA5kqjB:_9K6[nWpMRm-'@10en=L$3]AO%t<-h;=cJ>$#<48m/,d
+,r-6I[ElJO_#Q97gX^4.4Ko+G.;bR7G)^u`L3n]A.HiD)QT<Fd6V4L+f_0^-BDsf_O/>CBjB
5_^FsYtFKZB>`5bo17UjZMLI$GG&]AUI;_@LdD-g170u.K$Y=WYS7<Jb*"Mp$fA0MEm&!(AF;
J('/Ps-e"Mti_sZP5C7dsE!At11ho&c9jVMcql)g7,1Y9'XdbMV+)M?o<[&r[G("K'A5bjKE
m"d<;o9K&[S8,CVjm#rC*4Ml>sIEQ2n`WdKPhHeKt;^EWOC4*j6s=C(TlLN[79"R$uF_2#Yi
&P[VImY<f?"kB]A<B9im1BfSdEXD.t@l;CgIa&Ca_eR*]A,tFiO5S-I3-NFXgEoi%lQ;@X3=".
I#,%$SP&d1<q4/'ebEBAqas]AJpft?jrk,P8fn5ElQIlF;$<Zr4CPre]A\qVgKSpS2>_8I_^fQ
_POJ*SjF$?oWV&rZd?0U0NHGbq=A\j?M6.mJEe,9Mo!^Khq2cRP(-d$O5ZM7*-gpP[Q03p6H
mQtQ<:()0RlXMZFjGOEqj1En6,B:HHe06%ImDnHi4htKPL7$p'`fS=pgZn7"f:.CF`pd>XRT
9=FN#=^'-'2if+R^dG=]AhPQsf"W_c5.m+Q&4AsN%/9S55?=[=UNtZ<+`&)G*C=cGG4ZVKHYh
4G;gdoM'5tIKd,gc6eI]AL93@C%nCJP`d5nF$1#\SbNi"$m<Tst#>=8p.@;',Hu=t7_(2ZcDs
h_,5W#n`'mh0t-'&_F!/@g3iK[mVtmq8W=\^'m\+)A#rE;]A1h[0QaK`$WWKK(^d$HfW]A+IN>
C)T-KJWe.(;Y!$F&@X'(RN-rAS$lr,6[17M6V+c=s5=a,I*4lJRR%`DJd<'hMhf-*`o&I[\m
f'3%-BaG!H8VD99W7P<_.$=Q:ikEa-qlgS%Q5h2[l@.nZ.q]AfReOP,Uu`rQsp8!*WP.1(kc9
dr3mG1>1/^BML8(R:CbiF8Q.qF^_T=*jPdFB7[_8!&ei=G5UFG!(Ue3^.VSOEW2Z=@2+Ks%/
(\fI'qpEem'-\3E$!aOjr,RL*o"kcR]A!-O?8%cin\.g_&Mb[4]ADW$_k[?AAC2C,_FKo0;-,_
"dG]AKjtEH^VLL'5&r^OXi/Kj)i.g5e7\6)Q<T^B:M-E^V$VZ:QGJ?Qnm[8M,#]AgG0OgRF0jj
`=i=WbJQ+e#QZ2Zp^WciNH>%Zl@DIMj[q7U]AOToV.T'dKTV:ZSLHRc`K_PWRcR*r%eAb,k)^
eE/7O!F`tGX)QW6uN:KFc3dW\oLU-qAqU-M5dsV/mf8$;&EN&oCnol#.;]A<1Te61']AE=O*`&
c.E]AA(cqOC,YZr?<WcDoZN:SO4=sB/@4S"b.HiW4&A0]Arir^G3UkB<[lY_.05eBZ;B\_pec1
0hs&G5>CN\*l4N'@-)cD\SReWt]A<7]A]A>\)?u)-n6N*O"2fiBe_XgD$rSVhmTV3gtjm6<N'$t
djRk8'F#??oOR%AT4:1ch[Or:nj>iXqp2q@\.IiZko=t9HqYC;b?6W0AB94nj=;?k@qYt("W
IF".?Tl_adfY%C``D;;\efUf-JR*2L=RGrKHIAJeLF+"CZ>55f6c@.\g8F'@=Ous""LE1-U1
d=nH3:["Tbk]A&8%#`9_KOs,,"pqrW@fK<enNAX'ftBHd#9k6NO6?_f;U"C"f$Q%1E8ZE0%p2
Q^0F:\V`"oN6trZREMH36M#$:il#Uekb2Er;i>Pc3b5U"6J#@T0bbur/3]AY)R7k=SqXa>KNS
%!A`s)YiS5Q5TN50+C.RbjI!c;G2:.*U%7;SV);s*)/!LEX,<+]A<"r'gj\3+H7UDlr$+91Um
PQ,h)//pWqp6Y6Sk(=AAEs35)PE8_g"Wsqq#;$jk*uoTEVda6pd9NORN'K",pWUhXfEku"e)
>`f.h=Q<)7Q",bMJ3bNi90shd@l;W#(KDs!%2MW5o1\Id30%Mi2!N,Q0^EKYm1<XSQgS;KU;
!fg+QPnDSKMGJu+bqQg.c(]A#Re<r\MVeniat2;&P9hDSgl%Kg7u6ca8*i89/8AZ@.G3*cepW
":#N1B5nNc-3ePo4+P/-P6&)'`O$jR%"@<%TH_=dJP]A_=,^k!TJ;>cHKrgg.#1JmNlbK)9m%
//T@+IS!qL<sCr:l)IjS?#"^EM(]A'stRAJeVh9FR?UCi6Qr:p/D<rH^I::%ZhiLO2rb0h=dE
gkC!fCj'RSkursqOWuUAI1",J-0]AnM6\cu,+;Y4SpV7-$^m).ZFWEW^\a9knc/drOaO"R]A8R
G4mcWgJX''!Saca"kBUTV\^1DC&^12=Pe?Zh%S@8uc)rEhE@V=,0r+;DH\s,-eg%3d-iT1;-
%QS/arF#Ph]AD(WKBBV>-On,u5&4m>;rV#96'7^8aOq`i4$?aYi#(tV;fm1CE'c5`dhgrj3)d
to]A%nga/O^t$`p/B=d%;f=3r59=]Atr617l\m-K/N%hLbMG2t0]A!>_Z-)d:&\]A?<;4nhM&:<9
7*H&#EUL5dU:IqfV^aA_,uB!-cWW/m9H(b>nu+JY<G9+h22,JH?)nI*.S?$%a_X&.)/HAXoC
^J1]A3#sN8S:;?q8cB/9DH@AX07F6/W)<"?tY:*(WqC\M+YtRlm'&gM=<9XN1QS>oD1DJ92%m
U:2r#U+sA8"\M/OkA4'[fmP]AR^rpg=p3X\K:&YL*P16LZ`f6;'F8Y:':-c3SDX<CH_N/5)34
k;o%?H%`fc@a+PX-c"9fiqfAH+XhM>i-BteLDf523VDiP?7]A(?ieLZqlJHglb0L$PVWHB<n"
)okddlF`M%1(UE$@_LVA<JcUo-@RL0M<UC$7!J:>8cbR1*OL>GVB6Kr49Y<_pF>hL\""5_%+
rIKL^.PEgps(6/=+s+8CAM4gE@8F?Y**BcN#ODbZ3A`e.sQkGsHch7=.d@;Jl9E9?8N-<:R<
ht2'`YgdW)=o/:K;KNb$*T=X5]AE3*JB]AJUg9<Nu/+ITY$b.>`*G#l9dn6D:lrM(M6_7\WBU(
cN0JJ=0CT%-=,j3KSeaaQf$-*UC8)G=?0Tf7\eSX.@N4Ma`!o&7[L'VG#OX4DZURj-u5$`)F
Y2Ehk<M1apZ<`jl>0_`]A;_nEc8>.V"4?]A]AST(\*p:\+3]AENkGkR:@>Jdq4JU;^ZEYHW<bEVJ
NHGid,dMJ7*<Mf7l#p_X&-liQ\sa-'SDR*a4><Rf\!9A[jGN".5jD8-nA+A>N-6a]AM@8()l3
o9)cIbaA;=rC0s/ph5d'Mon[tG"?HT1`$Ql"R3^/%$QqU&jmY+2@a;@+6EiVif-Agt>Af<^'
N@8u4L(\\Gc=`HtdZK>loa`Tq38BI08QS7L5a0)I@$,X&'e!(]A-SSIPP\@@pmim-_$!t/9kb
aFhcRKjC`dH@PWV<&moF.4)BZE]A_H9sSR('*]AHL1(\Z>X`H@`3;Z#BUHjBF.^L=5hdfQBJMQ
pVet7SgL'EK`oC4<=^2,m70<SlLWnSrO^5fX?j;27Z`WTnd[LCah(///\L\R9TH;Mia--Gem
-?mKj>WQU46%)K6)?MSR2C5pBWLPs#0't)NH)KpG>*,^8M$4\8(Ams?WR9[a>>(`\$Ik(hs6
8Mq?QR!2^`Q'g2W8fYUB=X]AB(:<i)>i-F+cU-T<Kf!-$H)@l2M"abqS`m?(6^P>L;$/$fU\X
/!7[^8:gJFUn.PNlH@SqR]AGuEc"Y=&22X\^$mkMoZH$3*Ej!rYR^]Au@G\]A,cq":tn.(V=Qnf
#P>b)(ftX(!:/qm*s@/;JX>pU[M[DaH':hVefB@dH/3D<UF7[8l?0c'j=`9*V56^16K-!V.T
[_(,gZ1o)0;LhZ`Y^-.[te;tN,gr/;8(+"g=+^$;C,J3)TA\c[ui`DaD+oPtI'k@e:BG"apR
J,DF0HeS$#fP`*R..62CN;Y@"DB+i<T;;NS#_<FK'bi\3G^H-5,JVQ?!c7OgbdSR0sEMW9>O
;ILtEaaIDcn'I`k"V7Q2fOomgB?=BjjMgO?@\JGeNM(!8&hZQksa@r^4gjAkuL3>'qVk4`hW
HV`[^VjNikC+@:p8@bc;@g9nOCM]A<s>"iU1Q7jLn$ihCum8E$.k,*aUn+t@'4%?SpT0$VhEK
HN:\O_^`$@5ZSkk4o,S*Z#H$]A&^Yao@%#>YR2L/UDdtNnS%kd>MpFs/k>Om[h1bgBQ4;4`,3
GDVsUo/FA^$=gG=gnc&NCMh=*W^Mn8X.&gR2IX+u*CJRbL9QW<Hd^6Jte;>GXi'1So,Nj_Kb
kuE$aV,!'6;;oEFiF;;]A`fNs?JRF5CcD3;cCr7@ma<Ime!^)M1)@`u(NTbceBJ\r,[391eR7
h,`(-4H#tG<g5"OEaUuKqA(;380iG3#$9ecXsc/V*WITn)t3%+/(GC%>>+)J$rYIF(;?OlMk
0XWl"\ul!nXAelPW`\fqEgaEghRiE4UCDSl+Nt24U]AK]A@h6.W$*TYQDfUW@d4-=*5$]A,QP\&
15pTEqE_hr?m*BXa9(O6I6g+,Dn\aj0I0Y:6]A\gNck4)8M:S:VZsJmk@o'7^0.P5ap5rM:H<
n)"+0jAM]A3HMQV0d7+k'#>%2<>"+QRLL9qBLN?M#r@=ZMuO'0KTXpWAj9C@g7p_@;EV0Xck%
ifA4:E(k';nKEE/QW?%376j1j>9sIDoDd,Rfe`]ACeKe,.C5QI0oEm$?6A"c%b`L78^(*:":8
nG4>*4?\7Yq<1mK2#Qkk[9SQ(r<")HVtR=8E^YI<=D_M5!47mrc.4I@b;)i['CI)ELXbc$OH
+.(l('QJ?l,ne`MC@.`r5H#Ero@`WYcOEMsWH\7t\9/`MP*fH)MSR0-p5\%d\eaf<lO+N<f#
5DEG(L,\a2$$T]A*^?YKmE=Q\#W@[!8Kb9Gp=>33lYEc/&jM(I;YQsAIEBgS_E+PrX]Ao!Ila/
NF1N:uh:oO"R/ck%+)0+Zi;2$ogR,@o9C$=XNY:L1><q@+`5pI:]A0#[DYi;Z2b<Lod/NuCm1
>^WhU`-t+lo2X3g,2S-W2pDJAeW><Vdc+HoP08g<HR"eKt_#%%KblSD,t8Wp=,8M71mCc`<k
e^55?Ppab?E,gt@P:LKZ<F:GJ`LND,u8knIBTP)]Ak-WYN^/aMt4`3fG\&g`[$CQj?EMkm-A7
nTO1T)g!G/CC\ip4NX_S'e+42fchG]AT_cn68$D'`qD.ZB-_DL;MY]A/Gf92fZ?6#OgrBjIU45
C(AhiU>>%*G'QNuX:(%R?`44)cJ)3:N.1YICN=D8iB4%%!;eQjQHa^2)K"p<\p6Z/e`'C4U!
bi3bZ";k[tUd:-6k6"<+U6qi2d6I,>eZhEYL;_ZHnheWL_ZupkCRY:l.&PH^TIGcNqM`k^SG
NoTEf'Kk$[f[ruf'Vq*+)%IQ=bm4=>EE_9ZG1;#-C\eE@f[Ki/nKMa)16@>:VpsY/`f(&;i/
gfe=H@B9BDp(!FoIglFh,uS='r)+iDV`AG+gT?#7?o6h5Jkm.<0<.Xp,!LAKh;ls?@=(mFBE
ri8PkVgaM86nEA[q]A7ag1eF%;^HeCQCofbJYh;cJY_Eq>dN`&'m/khM*a#OnL0b3K5>*.Yh&
A!Y[<T#)VYc(.B0\kpEKRI"]AJmlQ>\!+?LFonVAk'M7KZRSH<>N3]AJ+L;D;b5qorOrbmA8A>
8A_c,]Aq)$A`I,5FLB]A<[l+I,cj`*=PsQ<WR54jW72LknL>+Q&Kmp_BW_D;.YMV$(s>el7)+#
;i(*,-lBR?bgUT@,I0B4-=Haokn4N3I.3%3(,TcOuGHnR]Aep#I*HHBfgW[1=-4=/>M561hl'
Ok9CDSfTEYBV\mHW#:6NS_iUXdSB\S@(%i",kMLjOYWcIWN+_;if7iH5g=mrPA2`d'[\a2t#
p$(!K6hg!ITd/6%l.<\#N2#AALln^!EW5XUK:DW&#W(l'F5u3DCR^l<KW2SUh)#&W?-0H:0W
8Vu)?<IB.-!tgD=I;]Ar5(>4ljq`dgLXAol,EXO~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[D0gIM<;ZRmR5nac#%Pi5@Lstk-A>:A80k/X#n]AWu8qnn=2D]AC@9,fA([,#!/&k+">#$htm+t
>F38@EWH0>DSjf(XX]AkP+fDSF_1ocg,j>cguHID^1+Ib=5B78K7^18_`W5NW=S5!<<d-^a!b
M.NaWQJ5o1L<`Gu\\KfoB3@bU$EJ$/deXF(d9VGW:CP5oX>Wb@JD%H?d<N\/-R61pU0l4-17
R%R#b"be]A,]AToU`jq)%I#SJ?_%i<&;:ZLa1*P:*HM&%R;,[,67K;6iIkR7Mr]A2l8<"`+O)ms
qXAu)EH6;5=QWh'Ln06raO!O>Kd0;Vq'J1j@erVZE:lGIK7e;.3a^n>PLpI_L/goEB.WqeZ&
*(Ohqm!R"8V5BS51#pB"[oI\2din[_m&9#/8g!G,/_i&7_DNKTV%tmO\O,`BhYcGXE]A`gUf2
KP/HOa--D%tX9[$[Gc:Me&)Dc\3iK#gn\MS%YCd_\D[8eM>aE7%N5$[?`fs%hfMJ._Z:r<4J
B$(d$!/]A'Cjc;Vbhmi.8/c5hkPQN_q%pN@#g-nC-(^6&oD!e^8n#?^D*,MRh&AVf59jTg1[l
:<V9/)-E@Th,:+FqTdX.F.Am]ACq?+D4%Tg;m@FnEL1^tP+91!L3j'h$N:IIZg$`%EtFFY@81
\<jHdlur\UK:@9NakT$F#)J\frNGFCPr$)GAMniLo*ngCTIY.s&#^X"hq)?hqbp?e5AC%R+m
B$,]A-;@FMA5N5"'>4g6dReGu_XOW!rqh=0TZ8gr-e^d9aBqJH@:)pX_W,$KKJ6d,HpdL:D,q
i%B1>oXa5-$@&;X8#0e]AYDp]AZC8CejV'(6`ql:?q1M[&a#6^35iQQ_VuhNVfkt1YA8B)GOj'
facU/DPTjui:m;l.$i.;:7bB=N:,8$^&0&ftrR)'(Lt&r?)osXsmgcG`Gkn`@[C&LRC-+t/X
aI&Z<u")^>De!X]AkjLUa:H:1g:$?#q'2A1SlU7>Ke(g8UofXBR_q2.OGp'W,W;/A1Ee#RQgR
]A;8X34mUOrY<jM/HWk_lN*EWWMU%(l9MLrJnM:JYDN8ZPGLX74q]AmUeN_'!?+m>B"[)q=bg?
h$D`$T+K3FKL0%-X\KqdO!V"&J-rKC>OG<t8KiSsbC1?j)0J>$SF0A95mLf,Xor`=1!J'#fE
E+Xgi,^:>EFl),o(#`3`-b2g]AQm('<VQlTOn@)C(#6;$T!Q10HR1c&=LbAD"Gm"HK%[7\=u>
u2!M3hN[LN!.5/55%YT-j7]A8;S.7&?7nM$m902(jslm2:fMi0^c\L^O>XW+pWg#9Z0S7aXhX
BJe9LDc*P<3inPB_t=I*<kj;)9MWUGH=="CsCHEb9*Y@c=??drGJa$(1)]Alf>oOJYT>'!:RH
=Ga.*$OUT-56#0ULFOa%l#\&PV<Anat`ZXB2:j:QG*hYaogoRcAVCnqWEIaG5[FsLPH!Su;r
AA\?=j"#;UlKAbGW!ttPpDm8;hh@G:#8>$olXgY>=T1]A=EPtftM\f&phk$c(DN%+Rn=&:3'S
\*k;J!)--ZDgqJolqtT_h4]A<NrH7\kkmC-I;2"lF'7\f,Zb;+E9sLS#&hnSR+F8p0>7H"#DI
n&F%mHC@`CM\?WDtGDR0P_'D<oo7n,GJ]ADF,"!'Ye\id.ZSG:LNG9:UMoP^2>/PY*c?((i2S
-ou>I\@AHbp'O>:4rP35bVgGPr(RaTieCk]A"2`J3p8R'W&gML\DD"fCnr#"(S0oG`2i5W!u$
43('B0>3d71tXg\*;2O)JQRlOJ?@EdT,_HQQhT!=rG[<n_bL@/2L]A-p7j0hk^]AYtH1i-\gut
niba-J!9'$eV+5R-qp#K]AY=QBVd%,*=_O$n^^"a<2HHND3=oc6DPCGeJ=+td&GGM9^/&q/da
,e!g>@B]AWDr9ne/qfZ0m[e6Ds#H-ICSNXOeJ3Ai+[`869lU<V';@N`I)&0jlq@0S'I;$^Jsk
[C?MG>ShBgOS0Q@o<0`h8n8G7n=@_$hLDbLJ=9@5o_)Q>O(#$sl_7Le2gLf(b=(g]Aq'SC2)"
"Zpc%9R(cI@,Z-h%S!)I+F9;_QO7SI0gN%c?&8A:s^MA`&>O-YOIe_Cn4@k$5=.Ig[]A`KL%s
<gCh`$NR_uZ-io5#[OXAcr]Aqhr%%!V#o4=a"sP<%$"(3qsgI%[4#Z[n,npgSm=%hTd/a>s>_
oS7*r3.P\3,'K*!13fT_d=]ABLhp5YY:,H0a`QHGhTbFtK]A[NeB*$n8f5a[bSB',Wrf-hC7gX
chTjEh_)#7u/if`#:07M8HRHQ[WIl7Q4YPXO71G@m&+:#DcpMc^bK!*c!c!gKe'7pNc&8Y=R
41bjt0hVIaNbIVohqS'`9\GF0n6D`+;[=i?X-Fo0aJk.(P5hob(g9$,<@*;cu;)2R<DVJF\U
8$\pn3,<ZX7=ETj]Akg$gCjeZKJJ$]A$_4U+e:?jc-Zh6'>*)G&"ITA2S$%#bJ2JTA)rA^p&sl
bZXYdNeRg'43k6_$R3:_d4/B:_G^X.E7eTl0CidH&T1qHsH-I(VnGfa@PmL:2^DA?P>SSOod
qFf>@98:r77tAM>j'l\P<>(JL-d9n/G3dCdh\/nGF"!j`^l/)u_?p:ndh903:$Ji@/3+.%EZ
3"UD%']An3"-9=Kg.k^2K;ta1Y)h4^j)X?DCA6"@@76@pj2VrhGu)?D"/OfOGPQ[F"G05EQks
E1N!;=l'$[d(F$)Ud%$KK]A4!\n"u_"AmK.Kn?f[nU\>2A$B@YQ@!2>k/\;r%Z)\0CAkT4B+-
13uHH/=\o='9Ou<!X*f'@`qs&OBD@5Ru+t#pUPh"+Cg&OXG]AKRV\!7U6hJf1duKX-K3Bo;qY
\n9t'gn3ibr<>t3O3+s@/G9e?!i[&;l.9hC_OLTC^Wdl0$*g5$s3ZGLhf4R2/=4R-;;a-04^
r4&#N#q)iIAf[$<5Erp[O@#LHASaat$NT8ZeF>t,c?j&]Afd.GGE*tc4lYA8Q>;s8%ijXep>%
kbQr3`gm=Kk]Aa#"0UTWb7dZ#oEY=@RA/c1eLMB!_;-@BJ_N3;DE7n?trt=@[3@<cQAGu+iBT
ChAm:4]At9B`f\P*:Is!g@4e/d&>>3Md]Ab?.$Oj=o<Sc&mLH9Ll;EXhs-\`W^l(Gd/iIehB.J
U#hWgrr\tipoS:RiTFW.1eJJO7"leF+9k[[8D$U(A1[e;6s-`bR_XKj"j;8b9*):Fo)@gkW@
)bGe%op%&UH)^73EFIK]A^gg_+/)nJ$7;kcDJi;eWW7jD@tU6+\>&Du:g_!5s(2MTqUNgm`eg
g0uh,j*,SGar*eU)?goJKf:XE=%4S=aFd+`g@Hj.8]A9!O=JU;mb;nFFgWJG#!fa_BJH]A^n)p
aU(MYi^hp8qYnhp\un_JhK)Z1.HESL]AII+cM>"AHGL7jiogd^nnC:?,;6.>WM^LD2)^kO]AfM
'8uRdoO)(<);VmUjnK^g*@V]ANXVrFLnJrrnfStmVqC+sPCdO/_m%6iFqoQjm)rS')8&W)Uj]A
OF#!kpR1;MZ#$>X5&R\5r*fQZG8*+^7hHHgN"B2X>>,:c5\QN-uC+30^169/9Ps-Lhg7,gpu
7IKbYCA2fi:fUG-(?@\G)lUU&gP(o.)S\_XYc!7ULM',?i-@g`oV*C!,2l5426gQodX:V3uq
V4:p=V)QrK4D`8"<#28`^!H'M!)@-;O<>Fq2SmfdXGnL$*shbHZ+aq:jHE7=,N&;4:ZmBY_%
[o@'ZL6ainc$l,]A@>gUs'h^QSiLP#6+(P^3EVFM99^B2;,DZ?SXB=?>?MB5#%[pdb#$a&t!a
dU\ZSfZ">^b`iW@J=WB#Fs4I1<nqe$lT=@:$GO!t83`f/qTiWDY9tbjGLbk".'C-mVH1<ThC
q-Md,Y2Q\P53pB-7@O=G+8>]A;!;3e4n_t).<.RFNit;R.Ha=n^%,2:fTG*HS4s)?GCm50]A^+
[qk?Th9K]A1QLUqFZJqobUj,L6QQ;"GB2G;XMcp[Y]A>=f_enj6epi+\@u:F#3o*G.#NPKAp9+
1Vl.V2'i?V'K4ok?'2t]AoQ0Og9Cc19!s$W\gr;)oS@?CC+;6B1I'OO&ji*.QT$,CJ^P<&ACL
eW"h*$om680,dW0mn.aL@gW=1[>8%UuI:C:nA`GjS!;Umh"RC6.DRr*\+G1"_u`27SS#!%$L
,64O48brV1baBo8(<,h:en]At.V]AK@C4#0^%AD/paC!s@Jj=7!0TUFbq.ReYbal!=B&oO\^"&
]AZ6.o/"pLph<$_!5M2M<77Hppplh.&##okgcNO\r^0^@?!o&gNi=2rVX6o[@^F#q9hHI[_"[
<\L>1+0CUTQiEOq*CDlpG]A!J%VpG"U3/84F@0"T+P5?q^GaiVT1S`kn(-M.QCYGlS#a'P-OI
q)nIdQnJ.`$P(gbZ/UfSa-l3e07"fU/[3&GKluTg,Bj1%5p.6s%n$WJbt-H\ei5E,M_T<QPq
)6A&4KJS[mGMQ$gek6TYSV^0ft+l]A3>c8ZVG(//l6"lp$`JL-k,:b'LBsNM+&?p+PR.K3O*V
]A>NoHp\^8H[O%POMOT"[/6r`!TWfQt#=_o<`MX\,VW7kArN?VQ>20VW%]Aspl[]A`ML(XJ?++N
NRlG-+e"MNt[Z/q3BsWOnVLl.)n(:7'!n?g_9Ru9dtI^2V4-U$h^P4)Ym/[nCG6/f$.dFG89
3N9!2O!:cS@&'.Y8?b+RECA5kqjB:_9K6[nWpMRm-'@10en=L$3]AO%t<-h;=cJ>$#<48m/,d
+,r-6I[ElJO_#Q97gX^4.4Ko+G.;bR7G)^u`L3n]A.HiD)QT<Fd6V4L+f_0^-BDsf_O/>CBjB
5_^FsYtFKZB>`5bo17UjZMLI$GG&]AUI;_@LdD-g170u.K$Y=WYS7<Jb*"Mp$fA0MEm&!(AF;
J('/Ps-e"Mti_sZP5C7dsE!At11ho&c9jVMcql)g7,1Y9'XdbMV+)M?o<[&r[G("K'A5bjKE
m"d<;o9K&[S8,CVjm#rC*4Ml>sIEQ2n`WdKPhHeKt;^EWOC4*j6s=C(TlLN[79"R$uF_2#Yi
&P[VImY<f?"kB]A<B9im1BfSdEXD.t@l;CgIa&Ca_eR*]A,tFiO5S-I3-NFXgEoi%lQ;@X3=".
I#,%$SP&d1<q4/'ebEBAqas]AJpft?jrk,P8fn5ElQIlF;$<Zr4CPre]A\qVgKSpS2>_8I_^fQ
_POJ*SjF$?oWV&rZd?0U0NHGbq=A\j?M6.mJEe,9Mo!^Khq2cRP(-d$O5ZM7*-gpP[Q03p6H
mQtQ<:()0RlXMZFjGOEqj1En6,B:HHe06%ImDnHi4htKPL7$p'`fS=pgZn7"f:.CF`pd>XRT
9=FN#=^'-'2if+R^dG=]AhPQsf"W_c5.m+Q&4AsN%/9S55?=[=UNtZ<+`&)G*C=cGG4ZVKHYh
4G;gdoM'5tIKd,gc6eI]AL93@C%nCJP`d5nF$1#\SbNi"$m<Tst#>=8p.@;',Hu=t7_(2ZcDs
h_,5W#n`'mh0t-'&_F!/@g3iK[mVtmq8W=\^'m\+)A#rE;]A1h[0QaK`$WWKK(^d$HfW]A+IN>
C)T-KJWe.(;Y!$F&@X'(RN-rAS$lr,6[17M6V+c=s5=a,I*4lJRR%`DJd<'hMhf-*`o&I[\m
f'3%-BaG!H8VD99W7P<_.$=Q:ikEa-qlgS%Q5h2[l@.nZ.q]AfReOP,Uu`rQsp8!*WP.1(kc9
dr3mG1>1/^BML8(R:CbiF8Q.qF^_T=*jPdFB7[_8!&ei=G5UFG!(Ue3^.VSOEW2Z=@2+Ks%/
(\fI'qpEem'-\3E$!aOjr,RL*o"kcR]A!-O?8%cin\.g_&Mb[4]ADW$_k[?AAC2C,_FKo0;-,_
"dG]AKjtEH^VLL'5&r^OXi/Kj)i.g5e7\6)Q<T^B:M-E^V$VZ:QGJ?Qnm[8M,#]AgG0OgRF0jj
`=i=WbJQ+e#QZ2Zp^WciNH>%Zl@DIMj[q7U]AOToV.T'dKTV:ZSLHRc`K_PWRcR*r%eAb,k)^
eE/7O!F`tGX)QW6uN:KFc3dW\oLU-qAqU-M5dsV/mf8$;&EN&oCnol#.;]A<1Te61']AE=O*`&
c.E]AA(cqOC,YZr?<WcDoZN:SO4=sB/@4S"b.HiW4&A0]Arir^G3UkB<[lY_.05eBZ;B\_pec1
0hs&G5>CN\*l4N'@-)cD\SReWt]A<7]A]A>\)?u)-n6N*O"2fiBe_XgD$rSVhmTV3gtjm6<N'$t
djRk8'F#??oOR%AT4:1ch[Or:nj>iXqp2q@\.IiZko=t9HqYC;b?6W0AB94nj=;?k@qYt("W
IF".?Tl_adfY%C``D;;\efUf-JR*2L=RGrKHIAJeLF+"CZ>55f6c@.\g8F'@=Ous""LE1-U1
d=nH3:["Tbk]A&8%#`9_KOs,,"pqrW@fK<enNAX'ftBHd#9k6NO6?_f;U"C"f$Q%1E8ZE0%p2
Q^0F:\V`"oN6trZREMH36M#$:il#Uekb2Er;i>Pc3b5U"6J#@T0bbur/3]AY)R7k=SqXa>KNS
%!A`s)YiS5Q5TN50+C.RbjI!c;G2:.*U%7;SV);s*)/!LEX,<+]A<"r'gj\3+H7UDlr$+91Um
PQ,h)//pWqp6Y6Sk(=AAEs35)PE8_g"Wsqq#;$jk*uoTEVda6pd9NORN'K",pWUhXfEku"e)
>`f.h=Q<)7Q",bMJ3bNi90shd@l;W#(KDs!%2MW5o1\Id30%Mi2!N,Q0^EKYm1<XSQgS;KU;
!fg+QPnDSKMGJu+bqQg.c(]A#Re<r\MVeniat2;&P9hDSgl%Kg7u6ca8*i89/8AZ@.G3*cepW
":#N1B5nNc-3ePo4+P/-P6&)'`O$jR%"@<%TH_=dJP]A_=,^k!TJ;>cHKrgg.#1JmNlbK)9m%
//T@+IS!qL<sCr:l)IjS?#"^EM(]A'stRAJeVh9FR?UCi6Qr:p/D<rH^I::%ZhiLO2rb0h=dE
gkC!fCj'RSkursqOWuUAI1",J-0]AnM6\cu,+;Y4SpV7-$^m).ZFWEW^\a9knc/drOaO"R]A8R
G4mcWgJX''!Saca"kBUTV\^1DC&^12=Pe?Zh%S@8uc)rEhE@V=,0r+;DH\s,-eg%3d-iT1;-
%QS/arF#Ph]AD(WKBBV>-On,u5&4m>;rV#96'7^8aOq`i4$?aYi#(tV;fm1CE'c5`dhgrj3)d
to]A%nga/O^t$`p/B=d%;f=3r59=]Atr617l\m-K/N%hLbMG2t0]A!>_Z-)d:&\]A?<;4nhM&:<9
7*H&#EUL5dU:IqfV^aA_,uB!-cWW/m9H(b>nu+JY<G9+h22,JH?)nI*.S?$%a_X&.)/HAXoC
^J1]A3#sN8S:;?q8cB/9DH@AX07F6/W)<"?tY:*(WqC\M+YtRlm'&gM=<9XN1QS>oD1DJ92%m
U:2r#U+sA8"\M/OkA4'[fmP]AR^rpg=p3X\K:&YL*P16LZ`f6;'F8Y:':-c3SDX<CH_N/5)34
k;o%?H%`fc@a+PX-c"9fiqfAH+XhM>i-BteLDf523VDiP?7]A(?ieLZqlJHglb0L$PVWHB<n"
)okddlF`M%1(UE$@_LVA<JcUo-@RL0M<UC$7!J:>8cbR1*OL>GVB6Kr49Y<_pF>hL\""5_%+
rIKL^.PEgps(6/=+s+8CAM4gE@8F?Y**BcN#ODbZ3A`e.sQkGsHch7=.d@;Jl9E9?8N-<:R<
ht2'`YgdW)=o/:K;KNb$*T=X5]AE3*JB]AJUg9<Nu/+ITY$b.>`*G#l9dn6D:lrM(M6_7\WBU(
cN0JJ=0CT%-=,j3KSeaaQf$-*UC8)G=?0Tf7\eSX.@N4Ma`!o&7[L'VG#OX4DZURj-u5$`)F
Y2Ehk<M1apZ<`jl>0_`]A;_nEc8>.V"4?]A]AST(\*p:\+3]AENkGkR:@>Jdq4JU;^ZEYHW<bEVJ
NHGid,dMJ7*<Mf7l#p_X&-liQ\sa-'SDR*a4><Rf\!9A[jGN".5jD8-nA+A>N-6a]AM@8()l3
o9)cIbaA;=rC0s/ph5d'Mon[tG"?HT1`$Ql"R3^/%$QqU&jmY+2@a;@+6EiVif-Agt>Af<^'
N@8u4L(\\Gc=`HtdZK>loa`Tq38BI08QS7L5a0)I@$,X&'e!(]A-SSIPP\@@pmim-_$!t/9kb
aFhcRKjC`dH@PWV<&moF.4)BZE]A_H9sSR('*]AHL1(\Z>X`H@`3;Z#BUHjBF.^L=5hdfQBJMQ
pVet7SgL'EK`oC4<=^2,m70<SlLWnSrO^5fX?j;27Z`WTnd[LCah(///\L\R9TH;Mia--Gem
-?mKj>WQU46%)K6)?MSR2C5pBWLPs#0't)NH)KpG>*,^8M$4\8(Ams?WR9[a>>(`\$Ik(hs6
8Mq?QR!2^`Q'g2W8fYUB=X]AB(:<i)>i-F+cU-T<Kf!-$H)@l2M"abqS`m?(6^P>L;$/$fU\X
/!7[^8:gJFUn.PNlH@SqR]AGuEc"Y=&22X\^$mkMoZH$3*Ej!rYR^]Au@G\]A,cq":tn.(V=Qnf
#P>b)(ftX(!:/qm*s@/;JX>pU[M[DaH':hVefB@dH/3D<UF7[8l?0c'j=`9*V56^16K-!V.T
[_(,gZ1o)0;LhZ`Y^-.[te;tN,gr/;8(+"g=+^$;C,J3)TA\c[ui`DaD+oPtI'k@e:BG"apR
J,DF0HeS$#fP`*R..62CN;Y@"DB+i<T;;NS#_<FK'bi\3G^H-5,JVQ?!c7OgbdSR0sEMW9>O
;ILtEaaIDcn'I`k"V7Q2fOomgB?=BjjMgO?@\JGeNM(!8&hZQksa@r^4gjAkuL3>'qVk4`hW
HV`[^VjNikC+@:p8@bc;@g9nOCM]A<s>"iU1Q7jLn$ihCum8E$.k,*aUn+t@'4%?SpT0$VhEK
HN:\O_^`$@5ZSkk4o,S*Z#H$]A&^Yao@%#>YR2L/UDdtNnS%kd>MpFs/k>Om[h1bgBQ4;4`,3
GDVsUo/FA^$=gG=gnc&NCMh=*W^Mn8X.&gR2IX+u*CJRbL9QW<Hd^6Jte;>GXi'1So,Nj_Kb
kuE$aV,!'6;;oEFiF;;]A`fNs?JRF5CcD3;cCr7@ma<Ime!^)M1)@`u(NTbceBJ\r,[391eR7
h,`(-4H#tG<g5"OEaUuKqA(;380iG3#$9ecXsc/V*WITn)t3%+/(GC%>>+)J$rYIF(;?OlMk
0XWl"\ul!nXAelPW`\fqEgaEghRiE4UCDSl+Nt24U]AK]A@h6.W$*TYQDfUW@d4-=*5$]A,QP\&
15pTEqE_hr?m*BXa9(O6I6g+,Dn\aj0I0Y:6]A\gNck4)8M:S:VZsJmk@o'7^0.P5ap5rM:H<
n)"+0jAM]A3HMQV0d7+k'#>%2<>"+QRLL9qBLN?M#r@=ZMuO'0KTXpWAj9C@g7p_@;EV0Xck%
ifA4:E(k';nKEE/QW?%376j1j>9sIDoDd,Rfe`]ACeKe,.C5QI0oEm$?6A"c%b`L78^(*:":8
nG4>*4?\7Yq<1mK2#Qkk[9SQ(r<")HVtR=8E^YI<=D_M5!47mrc.4I@b;)i['CI)ELXbc$OH
+.(l('QJ?l,ne`MC@.`r5H#Ero@`WYcOEMsWH\7t\9/`MP*fH)MSR0-p5\%d\eaf<lO+N<f#
5DEG(L,\a2$$T]A*^?YKmE=Q\#W@[!8Kb9Gp=>33lYEc/&jM(I;YQsAIEBgS_E+PrX]Ao!Ila/
NF1N:uh:oO"R/ck%+)0+Zi;2$ogR,@o9C$=XNY:L1><q@+`5pI:]A0#[DYi;Z2b<Lod/NuCm1
>^WhU`-t+lo2X3g,2S-W2pDJAeW><Vdc+HoP08g<HR"eKt_#%%KblSD,t8Wp=,8M71mCc`<k
e^55?Ppab?E,gt@P:LKZ<F:GJ`LND,u8knIBTP)]Ak-WYN^/aMt4`3fG\&g`[$CQj?EMkm-A7
nTO1T)g!G/CC\ip4NX_S'e+42fchG]AT_cn68$D'`qD.ZB-_DL;MY]A/Gf92fZ?6#OgrBjIU45
C(AhiU>>%*G'QNuX:(%R?`44)cJ)3:N.1YICN=D8iB4%%!;eQjQHa^2)K"p<\p6Z/e`'C4U!
bi3bZ";k[tUd:-6k6"<+U6qi2d6I,>eZhEYL;_ZHnheWL_ZupkCRY:l.&PH^TIGcNqM`k^SG
NoTEf'Kk$[f[ruf'Vq*+)%IQ=bm4=>EE_9ZG1;#-C\eE@f[Ki/nKMa)16@>:VpsY/`f(&;i/
gfe=H@B9BDp(!FoIglFh,uS='r)+iDV`AG+gT?#7?o6h5Jkm.<0<.Xp,!LAKh;ls?@=(mFBE
ri8PkVgaM86nEA[q]A7ag1eF%;^HeCQCofbJYh;cJY_Eq>dN`&'m/khM*a#OnL0b3K5>*.Yh&
A!Y[<T#)VYc(.B0\kpEKRI"]AJmlQ>\!+?LFonVAk'M7KZRSH<>N3]AJ+L;D;b5qorOrbmA8A>
8A_c,]Aq)$A`I,5FLB]A<[l+I,cj`*=PsQ<WR54jW72LknL>+Q&Kmp_BW_D;.YMV$(s>el7)+#
;i(*,-lBR?bgUT@,I0B4-=Haokn4N3I.3%3(,TcOuGHnR]Aep#I*HHBfgW[1=-4=/>M561hl'
Ok9CDSfTEYBV\mHW#:6NS_iUXdSB\S@(%i",kMLjOYWcIWN+_;if7iH5g=mrPA2`d'[\a2t#
p$(!K6hg!ITd/6%l.<\#N2#AALln^!EW5XUK:DW&#W(l'F5u3DCR^l<KW2SUh)#&W?-0H:0W
8Vu)?<IB.-!tgD=I;]Ar5(>4ljq`dgLXAol,EXO~
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
<CellElementList>
<C c="0" r="0" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=now()]]></Attributes>
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
<FRFont name="SimSun" style="0" size="72" foreground="-65536"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9G!;P@p?a'!Tbu_c1uNY)r8q=.(*k0O%"$+XGsPG,ae]Ah6e8=\TtX>`Y!bo+uJ@qSK<")+h
F6c<.s1<Pj)#$%MjdlLu#%i*"-*f"Vh["+:0;=OpbW7?a=%Oc-1'N?hOU4m4S4PF$B0W]Afbc
Pq>HoJH/HqTrSsc6B:f*lV\'&WB&-[,ru"+3'aR/)\+rg^J!n&lcSGjtmP!1orRopIT<K2R$
C=oqj7JV3RkrA:hRRq!#QC>oJ%DiV(&,_8*I7nfK,),QFftp$Ec^:/s(C>j=2V5U[8kiYZQ3
LpfZP]AOS$c[iiTA)fgse,DiY>)<g]AUW&VQT@/UHtgsF#%[W6fp(42=4sYNU!RUDf=Ak_PO#L
[-k,Hrg,3_V"'=e`MWYn^n2]AE#M("KV!<G'iUPqtHl#\>Y]Ai_eBi/S'+[_')o\5ZDGt^)_LF
F!NfW4&k(3NoqU8]A7cjLB8m<cd1S?M.HY2k+*&jX!Lm%`-4P0)r>k4]APjX=)seb<jRgJInoQ
g7_AVHrC`<.NS\&,5B,Cjgn>ADV=DmF2^\;7-K>J]AUPlWE?T9QFL9Sp0<.eI&>Glh#a4-/_F
47B;.Bfc#I.cX<m^o>]AIG0fY]A_f.1f6$0i3PL^0bmP3\g+?K2W&Bu'iRs!Gb*d?N=o+@47?g
OG]A=pY`lB'$l:O@fnqlIpF5OMM@!`bfcl;N)`m)gh_,sgER<>YJp?%\*$('oE=IG9FF+&k$1
U%1lFRNRMi'UkY2$WM6XM>?mML9uio*Ek?;:OCaKaMHrR(?#Y$p&&:VkQYonpP7fsUGPcXH\
ErGj=)FXL58p`c,b01Kn>!i575^EanX.Gg)"`Z,_V7$PhR]A4F))("ffat'<F;3pSL:%#4*#T
Shq92Y?/+T9\m!N3a[`YY/_6'MUh*\a$lEQfG0@nPqmk2h6MlCi&8jhimuf1`U>$]AVUU;^`W
(=\`WG'*1ibMIr,ijX0o2F9HPRb*L_hu:;]Auh2*N/lmMS/3;;.2GE-<q"./)dh#C,^YCG&98
0E,Br*taXr)Z/%LD:P,,iLS4()\:Pcc3?oW5C.cV2^<Zu0u3gupE=_%).b>H!?b-hKeZe9]A1
j]ApU&l,a7%kd9^;nu[h[Gu[osMn"1,r8Pf,ndq_nfZk`T6[n@jL:fJ?/lk2c@u&4,Y-]A\)'R
kV(ar8LaO-!u>Sre7h1W]AR7`H3[qgLsrYL[tK!#>%bg[2jO'\&UbZp97g8[,(c-.>'6@3#(W
cLPj%)_OeF0^OGtI;f\k:<qXj@l+=uB1YA7:r]AW"bJ]AR^;Domc+%(_ABecNp&T`DW'jA3I/A
^[0W35lM,H8p,5?6QCe*?FNcl=(n@[eGGlrX`o@32Rlk8"t@7MhL*2i3nS9%2Pth7'<.=YK\
3CO>niLRkQ9uq/?PkBgl(<'7QF2at&"%=h5oXR!-oXPS5)K0392MC4"PI_cH$]A:kXkIP4W5P
0#ZY^RTO=2Hoq?C[UQ=Q`,F3J8Yi,,nsmomktZB]A)V>Zjr]A3KZ30rr$KZ#lj=Q2@8.17"d"c
foL'@H`ODD9'C:oek^6LH%b7>-3WSAJ')FEAU%fB.,>iCf5^@0kF/aU*/eb:*`_ebS`KTSIa
<%\B,4%/D#NB%_m&I1Cp#;_tSpQ8SPX'Ru\&Z/%#Qpu^PUem.K?If5j[-d-%=Z!^.K^MROWM
M32Ddfdk_iY@EqI3?ja@?U(#(l/=d`GbeEOW*)5d5Mf]Aa,Z^NRG9oeE>Ygqfh?*;Mn5%jSlD
7&=`Vr.,!M<lV&cR4&;:aI`f!<I]A56NiX2:=Zad2W3A49JW++!XF!s_NNd.Ack=`YiZX0EGk
W]Ad^0gDd6qki`&oU*=!9Cqh%22s8d\1OO\%5\E:9L)bR4\_/I,DVl`@7U5E$X3/1I&8ZPDrW
u0U#CnL%:pe9sE8XJ:7^k'W-(57t$JD-rE3!8Gf3bKU"^?H[3!s4tG7QV09r!ik<=[6boS<O
_l0VY0]A+0qE<&BTBKTD(b)5)\al5Eb>-?:l%YG2/CIWsZi*Q:n`,dKB*:,k!QJ]AFr[1FL)(#
(/Lkmpo5OJ=rnT'iQ^Z?-n7N16M3X#lW'@7R]An5N>dd]A_J!(skV:PWWD&gbXM\;&_+"e.-Zk
:KLeC5TYs)?Z8_1i6TTkmL7_L':Lg6SiO*m'W8$t"%qVb8d>Kr:PN\aMf=!3"YPIL;2M<f8A
lXpfL%1c,U!:'-s]AV=B\AB\`gdYE:^q.?2!,8Jet)WfED$SibKl>eL@q`^*uegFB!LgLh?Y&
QRn0=94#%c&-&AgOknrun/#/ein/B#2[;OH$6<AqdOO6b7k$WOd5>,7_/tE'D2)KrsN$'nZ!
tr9Ob+A9cYW(h%Gjb#(9]Am<0CU686:ejLS.?hVY-"fDl;Bic5/28hLmo-e]AS3VIY8*`^UAcW
s1IUHgK%VG)E(SY[I`:R6R60g\2\)Wk(/(lWCM-(-di;Lu3-A!pS9)b4h"]ADtu:")A>dj;)O
uG:o*j0jV6201aR-Ts+Iq]A7:OFV_kNKkH03u$Hq.Nc1`.KI%n2o]Ac86F*,LbF([7Y%)3"7\R
IeXT.TB:B:R_:tFghQ-+hD=HH0T,+"fmW=P^oA219[0fWD*7MRmJAMa-Dc\`0bEiGJQ=8>#L
0Wd>[i$8N/o4H$fn#=>h4$Sog*+61\[Z]A"OVlrC4I<JoOK/A";\j":ar[$_[;,5EMtX!Scl/
j#?U,SX'pm#_NA'u0L\.(U.u78(\,3pp`hG.H=hn7F;!#8QDk?$JHn,7MI1`kjj>G+203rSa
Mq%5NF@IY^)MJ60Ri=mfR7?LAlPh/O:9b1\NC]AsS.>rXM4j*]A*2UhZEtQe4$`#7H4Ei\6KB]A
&=kEL3lPW5=!`Po$u))6tK/^C<g&)bg7Y`*mb@OVJIj_-Md--;Ys?lUUar1SLAN=*n!FL84m
2Q,JSB.q-3Yf%\bE%#T?csku0=BoF%-)=[ul,26ZV3KRQZ!cU0fBe5eQp$T<o41bD%Z"XAW`
,DO*&10!S\id-"^3"3"W*f]A^`166+qrt<5g&aEDO\0HdM)8?_nGl_W)Ndt!g,X@9V\4QKr7-
M/dX@IF(Git?mT':29R[Y3L0Xsb#HQ_QGZW]AnX+nN[gOE.*oK(<:U]A1pN#oSMD6AK*p(I[a%
:''.GM-4t3+G5trXQ"d.+3p+/sm2o,IkIr"J%j`!^2OR(Sb?7;lti"BfZl<]A_45s=qNm+Z9.
LLS/+o8(5IK+Y#%^ue?GIIrUp6GBd<PXd*"*mU_rV@JEpCQ%6Uo=Zl4E*TBVONLB!NUc/i2?
$m^?4bqngX/(+8:.FIpn:b7APp;$AXBLu:XV'.Scb%QC$g)#Fe9:8;(XqH)62p^(PXQ?Ym3s
G41?,IT;p:8q,e*A5&1-ls[V`Vr*@,MmKOC[RiVK8hrnn%mBbs2N!*'1**$40eQ1t(<^M]Abh
5;YM"1Y]A(0NPCk'$R-5se!8&'h`\Z9".d.>68i6J#\Y*nfJNEG]AXLQ1u:r5t+"o*OtoX6D`'
ka%iU6\J$>!5pMd2tl'EI@:Z'+dS*kKV#13`t@]A?AWEf/Ipup5mE4g6I;Ge#K^uXRc,-jmI.
'CZ[?=3=p41CE\LK2G3T<n?Cq#?3kSjDl^=9aIFLH8%t:6aO/TI.e:^=2Xu"5[C;_!KA4f)i
'GA[Xi/h"?b96TT`Z!AAVU(@CeS(2"YQL0/34,k1hTXcL'BRLgoljcB?#If.#G)#Lguu_5Ja
NA[_$pY=L9b5r\+-\?#t/@ZMPut/k&&%B$be\jNZ%U:7+a:5MM>$0!)E"N'me`lRj'Y?84+M
e$VM=/PLoHkWj8uXLgLiR46g.,SX:60DG"7iKKr9n!\<ofg_+9lhL#PuauoG'f]A=:!n#G4(Z
$NX4lncZX'Bdg/l1H#oQMR),0=$s^Tn+X@F\tM2>+X<4k06Y\2Nn_rI9*%l-4Gmn03dq\eSF
aH?irkPfbTSo':&O3,@rH$Y>M/?`2d96>)WLAj#G,fEK#-8rSh(IEI*%p'W.aZ5X*J1L/pQ1
eb&2`#Lj7#!sNU#(J/gW_sf+f$9D",<sLg(Z&qm68X.E-J_sc`)2p.g]AFW2WV[U_RjsaD$^q
4@[:0blKLRUqNok3j)551p%/3f#8/H9hVo:I!d^"ZMtB'0\k]AQ!8/rI$osqXmT"Ll[=Bq5ct
.PH"jsSd>3]Af2Asg_XPGSE3m@</q=3h]AA1?^JuQU#"2^Mm5]Aq17#tiu)Y->EtXWI^9FM/C3o
l9R<=GOSd#iQY"Ab>?b3VZdrM^M-?H-4SjB>CoID6,s)*\bBaSKN]Aq%Yb33m7=AL#TRqC]AFf
geQI:\cg(C]A@2E-a>*JV;/W%>&OX\.6[)ef3DgF('Qh"M9]A66nd;_4^"]AJ"ma+W;VRGAB78g
ZP7N^$^DHP<+>K%N*Zo[@8;+t;O.aM:_@%e^(0h3$<5a^Bgd!7!6g#'+MtlS)AC#CB5tK9N\
,FsPF><UpmL>lN,h36%[*J(DbS'Qj7MrY-pB`eU[E0*-i!QeGsGb3;ok?#l>SNf4SL6>gBC[
:^'Se3hc]AJ$f-c>`k\(P>Z2K:hiP^7MTj`Q>j[@nkB!(#.&G?o"p:L;f8q3@BXI?iZTKfo>p
>e]Ah=CpdAU*$fd-R+N$@<N,6+(frKQ`N%D2-2q7/Xq%8@H@=n?q$W=Us#rIVHC4@CTk`+2^=
B`!NO,#kA0F9=p\?U6m$6ao[ss:aRIjW\Bgp!NdGeAe"9f#ZapZO&1KeYb#MH[fHs^NG'RZ.
CQB@bWlnc4Dkt&ON8tB.Z(KKPLFV#kkB.XYH@bNj7;3f:eIh,V%;p_kfXYAI8\+;0h8cCs1n
,E7."6>r"=AFAPKPGia$:`IE)Vm=G2n2")#^U^?CNJ?3CI/XHh5H>b@XEWPO!eHr1c"Cj5=#
MH(4&<5'n(?h]AcJk/OelC5cIf07L\N)%W#J=`,\"_njBJ%2R?7U)J&GfQs7_P<T^eO4FRh1G
iO,2\6/obUsS^/a\5="#^@FLg-DJ9/V76V*)F+`"YP0rW?AqMs-==.@H?W*n)tO:F)q^UIb)
3CBl1cRgHPEd~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="667" y="63" width="288" height="200"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
$("div[widgetname=REPORT3_C]A").find("#frozen-center").css('overflow-x', 'hidden'); 
$("div[widgetname=REPORT3_C]A").find("#frozen-center").css('overflow-y', 'hidden');
$("div[widgetname=REPORT3_C]A").find("#frozen-north").css('overflow-x', 'hidden');
$("div[widgetname=REPORT3_C]A").find("#frozen-north").css('overflow-y', 'hidden');
$("div[widgetname=REPORT3_C]A").find(".reportContent").css('overflow-y', 'hidden');
$("div[widgetname=REPORT3_C]A").find(".reportContent").css('overflow-x', 'hidden');
}, 500);
window.flag = true;
setTimeout(function() {
$("#frozen-center").mouseover(function() {
window.flag = false;
})
//鼠标悬停，滚动停止 
$("#frozen-center").mouseleave(function() {
window.flag = true;
})
//鼠标离开，继续滚动 
var old = -1;
var interval = setInterval(function() {
if (window.flag) {
currentpos = $("#frozen-center")[0]A.scrollTop;
if (currentpos == old) {
$("#frozen-center")[0]A.scrollTop = 0;
} else {
old = currentpos;
$("#frozen-center")[0]A.scrollTop = currentpos + 1.5;
}
}
}, 100);
//以25ms的速度每次滚动3.5PX 
}, 1000)]]></Content>
</JavaScript>
</Listener>
<WidgetName name="report3_c"/>
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
<WidgetName name="report3_c"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[店铺名称      营业额    同比    毛利     同比]]></O>
<FRFont name="黑体" style="0" size="128" foreground="-16711681"/>
<Position pos="2"/>
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
<![CDATA[1713600,1523520,1560960,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[1440000,2880000,2880000,2880000,2880000,2880000,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" cs="5" s="1">
<O>
<![CDATA[店铺日销售排行榜]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="2">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="3">
<O>
<![CDATA[店铺]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="3">
<O>
<![CDATA[营业额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<O>
<![CDATA[同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="3">
<O>
<![CDATA[毛利额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="5" r="1" s="3">
<O>
<![CDATA[同比]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="日销表" columnName="地级市"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="日销表" columnName="营业额"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="日销表" columnName="同比"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="日销表" columnName="毛利额"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="5" r="2" s="4">
<O t="DSColumn">
<Attributes dsName="日销表" columnName="同比1"/>
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
<StyleList>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="1" size="112" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<j1]A;g6g-mBk>2VbB`f`'Nsp[Ugig<Rf0[GH-oH/@8MN=\Pi/.'c6a#UJZA0"!*q.Z5DpXn
54C6Ae%FQGcBQ<L-/e@^qMU&OHapJ1MLW8b0K8Gq6je:>\8,p3e!qcbgj[qd%MAI*Uo&cFFa
P^)\-iQR>is\R`gF^MJ7<9DFYrIfboeH._6K59mpglK$u699t7fh6uDemq.oe#M5ia7'[QM>
@F/T'upH(5(lL6`VJ-'_(5MN6@[`hs"?^eQd//]A?&'^fasu^5[LZr2ML(L&^>d%#;P&X$Ne6
Gcq!^X2p)f&-qG/`Cj]A+W<9XbpO(>1^"s&nY%b1cRU8<Z"OeX#OFWMZmZ6&20,,XYPR>E%r6
?AZ?]A*!L>JDhY:HhQ,T^Mr<e"<Y61bqL6p$/ucGPCtMN"HTs"@S9r3Tf<D]Ak,R#0?8?1>/YY
$J43O/S;:KN31epRX>Z=RpYFii$C*0Im-)m:?!\5O/BqVrk^:L4-n9i$K>";A;G8PGPPRPkk
)*64%pl#HUbMJf'f2[_u1;U]AZ#HXi3^GAc*6DPm3X"Ol5"/uGn^YY9.UE"O%i6BkZfe\]AsI2
CE_O*)O>\7&80VTo%h1XBX92%Y7M3Jj3o?:6tM9:6CE<?Zq.TZ\C0&QFLg&V6_Vdh\(21aos
ODIf3kJX<X.B&POB^X5TG^9O(4&OT(AqEMJ^SB@2:e3&^1p_b#F$9P9]ATR`Nm?SK`buqlYoB
L7F7NQ'%qX\>]A#]A?K0odiZr9PoL`='B6gb<j56*%METK8XSp6EOY#\Zc9>694Xm(b4qVc1$_
`VcB<4m`UuS==P'ELiXM_e>=;T5DFPM[&"4GMA!8ooeBSc52_`u!+O3Ujer7G1IBM%J/83RF
TomJ4",)A2M&H[`.2K!-Fq#or[,\tKncC=]AYFthq@2O"6Z6n/StB5.)q`(Nb:JN,iA+2RtkM
CNdp6Rk'-5eOg&[3:hb+`WQ2&Coes-gJhrl+E]A<o`4F9R2cmT0tO0%)7b:(dQoc.Qjs9)*>I
DAJc;L*aGIs.`G**NNV8\['rF3S*,o/fW4'&nOYEY*A8eU!:I6,#cWu_e,G0t6`3k>h<jR[Y
>YO$:%SJ.IL=s7D6_E:X0uPM6+Z/V[NdUY;:L)m^YLB>FJCE')CWNjElNFQ3&KcF<+YMn'Y0
">&%3Y08k.f`5m?F'rl\]AFL16<'3`>Ha4-#:#DjEfg?r)-U>9Bd80g@jnKKMc#F>&j[Q^Fo%
qO9"Y&cM9e9l7ch0=2QZ/f*!(2^UV-CeA>DTj8"p_2o56&MM(h^X\I0OZ>^jBb*0u3SN#R)Q
lcYAUHUm&M`]Anl0Q/E/C(U/]AO8T@"T;)ndO/++*NJo"XC8;Z$n*lf.i,%k99E/s2pFFT:,&o
UXk"pK//rr0((ASQB0_T!`@dq-*,QtcmK]A6J.MOP\u%o414ok3&AM]Ahr>95TmH]A_VK`^;N=Q
f9OVlod'Y=(FOTZG*4an5c);KJEO!S3e;S4oJDB-,?sY7_1m)@OA9\NKiE;VN5D;!#gG/7J^
l#XEG0te%bfYmcttUD8ZAg3R,=OW7oKUaX6p7"!hiSG[1`^@%Hf1A9<-I)]AXX<kYH"#8=UW!
?.[`iL"j`u&F:;pShEVpR!hae:[_`jK(CZddK*!7Eh[>6i5pk]A<mK4,ZYTYF*_&@j\miH/9Z
H:/WIniY\A/AEa:TLhT5qiuj5,@VfQMEM'fDTrBO1d+"`T`X_N&[i<Piq;WG9pAC_SE\P5YR
Ph^tCb6TA9O*6#3G[]AU:6B\OKJo<01ll9nfaW"?;9'+:r@F_H>FN(!'01bi\^lToL`!6?*jh
B=;bL@gU?:rX`Ye2;"?\V!t/KjEkpjc$(o]AhB.-&Tqtl1iR8.?$IFi^EDCHVVR;CFe]AD7CVE
DFMKpY(^RHs/B^k\ldcS["\GmaC.;\FtkrRskqKddnA+=Ps3H97nnQ^/@%Mlsfi1=Z.??Khc
L\S58=?#mjj%^g:mnq@J@nCkZYK9F++>9WN#-igt*`<^/V6-gIhpCE7t1-mW%1)_d<9%oC0B
REV<5ie-C3cLqi\aHK+P]A?#]A0),)!DCZKIY%d(j68M<5"sW-f"b:5DOp^VTY5*A1&Lb:t959
Usn5cC)E2G&''<%.fO83BA81(ke@?q\MAA(F+,\JS*ZN8?'Hps]A0#:N-/ksA"VQ&B5Gc(nKD
(,)iH]AsY^[RnG_^&!Rs)A<1XbVQBQI*(8(30LXT`\Fe&.b7TlC>s+$6a@g`,99?u=^V]ASA*:
$(P\[2M6W-!44aj2V!HZJb<!k&4XT0-*@kKq4$]AF:,*dNE1mHUFLG]ACLh_ga3:FjPrEQ5efF
OR*n;N!ip>nrE/FDKs&)Deu>a``oODSffWA#efbVu:uWNK<%a=ar4QtYm[FjO$gTpui'TQ%7
;]AGe*MGVai@V:PaW#aTj5$cfXFOXr*Ii+22ufRWSk$X?e*BHA,.#/4X*`1qJ;&a.2$ioid$9
Nh"r4jY-X]AT;G@LDb)=6GQ#=j?7ko:#;5lX^F-/f%g=h+E:;'O%/]A2G*3iZ/U(B[UA"qc,)s
`aZC<2,h'3Epr^6$0hH6]ACq3h<U%OCrIjV$I8EjDc25U+acJ0+`GT4sPUJ$I@`N)_fe%I;Th
DDkqZQ>s;diZYH1DaN@9%7"CVlODe0R&nMF=Dbf7O6f:64kf@F^HY/$Xhs1'*"#lQ_T"n'ZE
I<gSVO=nkZ?JI:<*(=X%)%Nr,n997q&:]A2i9IhYAL@VT22-"(`l>Ks@qZsQc(U[t.u!O=Bg_
cHtJT$=O'c@J:@aW_qPoa]A(t6eCD#rL;u!QrUUG-Re^##b+C]A9;sf2O/sNJ4]Ac5P'Ne49RXE
?Sd!`.;_N?jX%Jk:I06U#1qLl"K["!ieSW\aeP8!]A<]Am*fo&s^<B6b-lY-1bfR0C2jRU7;pH
W@4!c#0Dsri7]AHEI*sLZhNn+9NmBAlOk.tI(R;=s3/iqdDW^XHLfJIp%EVc\2'nOYU'C5N#6
'kOf`F#RF:Y1se1l-.nt'I7gQ(r[S'i\E&2-+E!pS8uWP!Eh3KMIS)N<bkj;3BW^b%5QAXb1
eTg>j11CHtKHAF=%mcrDDK(OfNG@1%Hi-ePWO/a0!]A$loLQ84k-R$Q4Fn88=eJ'&0s8snWW7
J9BOYsul1TfY1?"6"=[QjQ)C0'Fi6UPcsqUDIlUYmBK"B/PbdO+H"r3^pfmcY52Q3c%Zs:%T
+#Mo*gDA4%9TK:r_aaFb+Hd'/c2D)fYAl<S6*P\&R)cHVTUk'm*l7-9*7Y5tR^#[GQ8A-+iZ
(EM2$gjWNqo8nnme^jc8^dHI$nfSUm/+8Y]A&5Sh]A._^Rb9$7RQODPGKEOqmJHQ1sCPsqE.Ke
V_P0Wd4&'[W]AHYWRj[*.n<@]A'$'\F@G5C3G44TLYcI"/6'Un1DI[d$MZN)-.nOKJMt24*ri7
r,#%!#kuPI9V3S&>r,\`V@<QoEH]At9m357:po_-XR<mZ5Zs6PM2jKb3@o4?,Yf)hthkPs+d"
$mW"*jmfe.=]Am2M,R<9jiX++18Fo(06"tsa1@\T/ALImo=*X/@YQXD\Kq.LSlb&-qI%\0K4N
kb>>Kh6&R%A/Fd?3DNHhMGL!RBf9ia46-HUqS>%+.nDOqQK(g]AMJBo<medr?hVU]Af6&V,[rP
h@8ZaZ,?uHl$3@nQ.-)9;W59X?jYI%4+@Y7irP+.B89JZ$Me_C\Oh2cC+t_.P[AH&lgU#RcD
,SAnQd^K^!u/s7lg$HW6TZ/I7$VmN6'tP_h(A;AqLK$ij#6@cc`FGWg(i3NnH+R7FEhk-nNE
04.,AO3p#./)36($6V_!X[i&;pjN`.X*lfa(G&%arU""#,EsRU=$HES>/FQ@;B42,[ns*,0!
8I2=]Ao7LKM;S%/f6#3HNRo*j`SH^rd-Yb7\7N"XR<;jLALcLL;`u$R2FPZ=La</cJ3gp&.M!
#:el5p+WljG*]A=#gn/*@l$Y\143/I#i"7UT-RQM0-IV.=OF;s+DA!L*VAee>j^2H+ZmPgLSr
aid_i+P"TWN7uU'IGq&IV]A405.K5q_l.*iXdeSa[edg7CNn-7Q)mb.A^"Rf6@Ip=D$)IJD=q
/k6=9*MHOq=>S!ROAV.S*1%-b*3CI+PJ8(>mVGSD1X?$gACAX>ssA4XG'e+O%!j'LuRo]AYD*
m[BL[JBT3j+TOtD="PFdeFjWTdZqHDE3^hNli2Is]AkYr?B/P`g:ns?r']AW?c,OVeu=mS:bL8
(6T:=!GY$V6hP]Afseqb[8EFQ:+jOL:@N0?"MGmCNmXSU:F`DQNgj-DNmH"0EM5UrNLn@fL-_
$^,U;SQqXL#kTJ:S9P,`\33S##B?ShuoQh2:+cmKsH&Cji;6:7U^J7f:/@PNh4\3nVVG".3D
LCFEU%7UIu'`OP%#T,&)o#6EJ:5jgdgi7K."DE&g:C.m+5r=CF3i9&T&Ka!RkFU%[@PRX>8=
FqY1u:4MMRJbV$-YEYjrT9X?LXK1TME.6Xd*N$q@ZnHIGohBoc132:S$^pQ(_UG0\^P^?7HI
gH4J:0DL&u[#jC/S'kU>fPbluG#Ff6bdG$I+]A856/=%0:@&QJ5gok8GRQ8L?e/WH.f"[GQ&_
FP?S-:Chkfm%_Y=:6/YI2?(uY7-QPj(SPG_oq97/7YaEXZ6'?"t?q^,,s_Q?*'[meiN`Z)U"
bUe+@c/Lb]A*K>U,u)TG5t>V89:pVPM"eo16GXkYN;[CfnB?7+YP9GVu0rko.3Y4EsS83T!SO
+%2HoQ&hWGh91nT?\.3\)KfpSlpZ4I,E!HOL;"+`.>':c3$%@M:mX_)`0"nG]Ah^82N/m8(b"
(dGW<3O18kLa/f9c5R$TaSd?B$Ne`FVFT7XHYj'bR;:OXE>q4WOe9Se#GD,/e\RIX9`B-Gk?
[4[Optb[2Wk_[ct)+-Zt*.s9t`$K(AA*T([$?!SNKHmsguSEk]AAVn^/"j;FOKZ1dH3`bTi+=
5f:id@XpuLptI)"8F^Kd->\!'7$/nSZ$__SrPo(ZXt72&R!B0=h_mj)5^SZe4u$aC)'qfVaF
&G5RG_k#-:>;`giCaXVrb]AK3d5Td1q[/$L;)a&h(VZ3Y@rTSA2]AZI-fVK9q_e26jX%Qbkp3D
V1q>kHA)(`9_MGUKp'#;5BKCio]A7l6W.'0[/_U-K\9#?sC[kqjF1mjcq)kB:Rkfr?lC^$@!]A
=McAQR`rV"X:57)fBirZ!J)pk?58-%P$p3^?&K_->Ts%eUOno'1>_T]A7u<6]AOJO6JjULFZ\1
A%.#s\1WkYs6Ugl5LK;#FM$6u"\#;+oaE1g)Op\6&nDrWU=hT'+p-/4G67\1IoNlNq^3s(HS
_@&J--7`u.Pk#%le\[MT"O<<mg:#=A58UGHZe#JY[a"%T#Y&8b;BOR+6R\hYd1cIN,h.LJ4P
53X?']A\T#,19Xr<^'H49*:DdE6g$@f.)!-#U5n?K3AVAYtVDl]A%DPO1opcH:M&G37nVbU\&<
f:'>-/o-$pOK_AWr<WRsA@;o!i2t'MEQn1K2iU6SOm-kbi5J$7O7Y<gQ@"gJ2P:A2nSVdnd;
C/B#$&n+Rna-HN#bS^o>rec-b&q0+UKoF(0ipr;iVCMU;s]A9EuWDk#7tJ+KJe:g=r`NG-PO$
J]AO[36ifJ88<YsZt=+2Y_XO68*QGE#Qe$3)_D4"esgU,4t&jr,=KG9>rP73sIlWL+2*#n(@W
I0):62k(kmc9is/Am*5mH*MX2^Ln%lK2Rm'r"qOHEk9E@t4NU&?O&ULX"&15[XB'9!lJ+Lup
!/\%slVhDJ7(.,^*@r*p>XLP)uP0eNVrHpG&3KI11R(8$oiGL^u='Ahm)q"qf.RmX9L[UhY?
`i"]A9/5b/=?C^m85"QAt#nGZN_/7!K?d>0qC$';g?ud)c8odFN#16^U]ArSABEo;CYM3P[XO=
]An&J!d+VdW+?`jd^i;^sGn7I9%Q,S/1V>XSNDE52!e8U:$)h5+:=;q$KJ2O\LgX7!$AZU@C%
Mm5UD?@PCoT@@01/L29%-%pF5=4OLRQaVQhpC;--ESRHmC7DP>pLCOsqI7)eq"KWrU7M[J]A%
S`%A!^j`WjZ7S"'O>;dcT$^P?/UTd0/2Q5/ad/oX#74dP:po<6fL&Iah$m6EZ'("Xg@2j34C
FQDcXjGY<(>\^BS;A0S%b?a?f&d+#(U$"J"X5:b`fuJqeEO.58h#.c/^'AqtJXrJT]AEhmOX7
d!-TJ0D0e>"7eNZ#G+C)h/Zjm?b*TQ6C]Ab-54od'\pIgq>_fA)eah6adY'WXi_m(`NQ1S=8Y
`eM+qZ*k+;7UJ$-nG<OBd-fFs#d!VSYnVK,.f4Y:un>&K?EK9l5c.EK..<Ks5qpEhaE\&;?<
W[eT#9O6sqVA,'1=IS]A]AH=J`hbdsDHD73!K]ADsl@:pOr"dq`W=C4uUoR:uJjq>$i&G5JN:h;
h4M$:0'ph8&(',Ffm%`$5RU9HPj4d3G%:B`tJHX9-on:&7J=_Ug_;9ZZ(2jl*FA^_5:@V]A,3
\OgN+X#nMlD@</e[L9&CYI>WD:rF@G@)Djg7a/*PMEcOis9RT_Bh&ZPrcd5+80d$K=l*'h6R
$5E?)OeT'i"`@!NV4eab\f#QN#G"AYMPg:,54S.E^l-]A_EdfPVS=#FYH?@CfkfWU4URs@ea@
47lDrKVYaP:cYHF`s,eRq<KliM@LkpLY.`$_fcZHPWHA:-n8SMEkrm!;F_Oa.0MHB(dZ/.p!
KH"9ci5\,uV%(SPG:,`kRr@D1?<Mr@>RuHqAP4@CR$l!.l]Ark!fI\0$J'0X`+M.t;k@g#G\@
35\0'(dV(o7:LCb,SM0M.2T7$WOC3ZTlT$`j>MT\\0'fjdl!j7SlcPBh7[pDQ6^S*5K5I.>i
ml7_JX_+[SX\+Q)ujoiSh'`Dj'c<NO6U28&gL&)r/ErN'IjH(2etL)gM/oAtj7D=oEN69;"Z
rs6S)V;="*[S(@EB,KM'=Ikl!/o2kUBA)sr'I-I+Cok?XLj1UkXCNk.):/4$G&1b?/clB'P1
sCZ:'j]Ad\m:=KR4DU0-_b]Aj1Ag-_>)]AbS)Ne#17T=uORf,"#Xk1O\FWmik1s@)'Ve+J(p4f&
aOq[8I[S<C*]Acf?jUCOoRG.]A5Q69U`X<cE)tX9bO*n0#apq<MC/aU)+)5Zdk06N-b2f9tqjL
e@n'kfb^-:t.,7W*u!]A6q-aI8[NaOb9M*$5HIn/S6^I`(1q*,`1c#h,th*,DL"nY=4b[0iM=
+b3_NC<oZ-7%5(U1&7'jHGUDmrdV[FX.gmZ2O1\Fd]AT7"D##&LSQAIX?!#n+s:))f<SXAp?q
ZM,2)8EOntf,,@&DCfmpYmp]AC)t'(^'9dWo5o=4F5L\p9(f5X8ijG-q47:Ii9sLGlX36d+1)
d#Td7.3/5AQrS.fR"7Cd6H2ALQQ;+i29d&hed4^[m?R:r.Dk-h,lIn[gTL7=?8']AT:&YX&AN
(9&>PFp>4jU,XN>*Kf'R3UeHZ4?!b+J1Oe<F6T%@"T@lu5&h,+l_MuF*o4ErtDi]AZ:O&t.p_
dS3UH]As7@r+(<@LT'KE8UrA!5ZeU0]Au!m&DeWRr%Ec;;*CVMmR8U(l69nfD$(t:7_Yl=:d+o
6)qhkT7)Sg?[$),_'>ed0g'7fW%2?C]AELY\Eceh(:[RS+ggq/p)6Rnu^3aoa(oP<LD7TtGV:
q4/BTIXm1u7Bbnh2W#=]A@0,9H*hSi;2GjEg?C"q<Tl"LND'65&BXqU2#PIg!gs?.92SuR-_O
r,(\U%0HrG_?r.%Pm/]A,[[&+;ipk\kdEKgqF_G9NcM3#a]A]ACB..'<*=k:e[3#=(a8<oZa0Q]A
V"7p5-pqmmVjbg8-3[IS_,<?f2$hId5-o4^'F,`"r>->FTR`Y"8W=CF?[e=HO=,crn6LfY;7
b"t!TuM+XOmi`G6G.hqCTIDLJCrqEGqITYb+2NoBt-E+Dg]At1=,GQ/AYWqF4fF6B-r&iuf>Z
TeVSs41]A?"@u1<[CYO!['TPRsgedfXIBY:X#R-*h&(aY8UREpp&&`miroccT_EpG_NLI/RrB
dlS^FP)X3i#7a,UZ,,kj-HB;+)5h_s48gd$).d=K*aX*+rOHbaL'hqXr9p6^-jX<r=h_c/a4
L=[<r5kP+2%+^(-gF<PATbO[Q?,"CoK!#[2/=Ng3pgJ30DEL=Q%ucQ+c"OkPgq^k1:<16>cQ
rG=F8+Kb9r^i@1jFj)S>qje</fkSa)+7^%;l&,Z,mR)=^0GGWXEMTJeMZaOQJ^\)q:D%B).]A
/!LmRSLKnVl5\u8$k/.D"L_^JX^LZ@_fu9IZWj@s8,9cHF![Rdbh@hd%$NtZ01NCqKHOgKlO
Cg!@Muc^%]AFB0uqk*,f]AbeqcX8h'0.1Y&Mh\s/d`,o:"VFas&*F!?*:DaiqeWTRb(8js3]Au*
\*:5#mDm4b@L>2LR`Mi3),$CCV=2InVN1KO%K"1p7oi_9!nBc6o$lC`@tOA;[[u4*+^`bA\N
Ohu^O0n]Ap4(ginfaJhTrOfJDC4bYM[V\bmDNs/Pte=P)`sSV>PpR0i+MTVdjK8YP/cBG5D&p
f+@K=&P<%,h_'4_G\#WGOf,h]AaR&h1Fn:'rg>5N*&b=-19:A\bNM/0*ldQMH1>?dCX_(L&I8
^kfTD,n7-+B(LTNVu._N<j?2Pj`]A:gUYi%=aaL;PrS#;[X;IAp5qsU^\]AY;s16/d(,)$Q$'J
1c2f/7=RlMGa+PkZlZtqANb\jSG;XCI]AUSiliGB2>]A33Kg0IW8<Y7hAcJ_k?p9P/6?lT;27<
]A<nb*W+tZth>sFPiL0_Bi$A-NDiG`r*gYUO24;)D-P%tO1VDDk`]AdQrLK]AS.Ud[$@%cag]Ap(
c`M8&*brQcX?%6MHc=.BsFScJj>JO[[LD^1n`^dXAt;$KaRCMKEJnJ-nh\\(\d.(6YOPI^Xn
\k-kJu0mr=a6!pIJ]A-=,<mWjQ$U\Q>X`Z=)Ef08Dpr!M#S6,1JeqCKWH3`/X>p/sq'^[$EnS
"h!./nt:"I8EOTht=76dJY4\$WJ\20PX7:L+#tqfWW9!17O+/^rj/4gc5lf_3U=R%Td2+4^a
VqfnDeh:s85BYE#elY635<&%X@pQunubG*2^]A+Bp+@j?Z5n6?dIAXd9@N^u-F7AZ(fI^ud5B
a!*Ulj"SiTK\tE/pn)uBFl>ir=e<(,k4^1/hg:Hk(Vg&aNWejrA)pnlH+n8GcBA`ZN^)E22r
);Y9`4D4W76:pHKG#.mUfZY-OLNSQY!4LT$\P*7U]A,PJ%q&;+)A\R$lR3)dTI;!hX(2+(]ARZ
EXEIf9bT+BB1#7kjk*Tb<fD1on)7:5L4>2Thr@?\a-%e"gGNr-(85H7TIHj+dr@?$,cln/GD
Hsjl^X,..PqnU&jOB70GQOm_)uDUh[HUR:Da8$F:N7cffl&FPle]A.>L(SoDA*M*kR>M6+kZ9
#R%VfaES[?rH=f!eSU'$#CB`"g6SlHV]A&_j<bOSn(r0N;If_2-Po/1LV,7:&uJpZGZGkIMEC
cej3O^n#8h8G5G?^<*Sirr&aMZQ>@bp'8.Q!D1PgOTUs(Dj(T"jKLj/bJBPE`WHZKq?$/O*n
q\UK_i9Pfl('s47d?3O!YU""BDneQd5[dg%GP=OkQj/qV#E2]A$UsM!6q\K9q%4=koucDjM^g
ZI"'SW6Ml6UK<$Z$Rk<5.H".R\Rb%,c+E=$)26>fA&>cK=N_eLJPpNP#U>8FY#:22J17u"Y@
q%,K`:D7@5GV%S4Q)eq'),f*X,mtZ+#^E8>P1t`@bR2(Zufl6PX8I\6-<%^G*/<]A*Z.M$MsM
ZZHVq&Tj1tsUFpC&,n.EULejWn'.8g`m[&""<fg!j_&ETk'^qT7;73Nkf?$S$1>(k'Yf=UK"
0hSu.1f#)@36\lHDORd3TjH(ZTS;'R:R;2j7/$^WIO<YOjd,j2hp21%2Wh@bkoq5@2@[;c>H
4(f)e`HH=*n<>d!(Qi5"F]A,kI,U]AU9s'I^&(H(<cHTYY1_-]AZfRnoMZ#4o#Zpid<uumo2rS2
f6Nt+k5,GJIVY>uAMHLS9?\841p."83Rb-Hskl1_~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="250" height="114"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[店铺名称      营业额    同比    毛利     同比]]></O>
<FRFont name="黑体" style="0" size="128" foreground="-16711681"/>
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
<![CDATA[0,1066800,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[14020800,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O t="DSColumn">
<Attributes dsName="File3" columnName="地级市"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="<div id=\"demo\" style=\"height:250px;overflow:hidden;\"> <div id=\"indemo\" style=\"height:200%;\"> <div id=\"demo1\"> <br />" + REPLACE(A1, ",", "<br /><br />") + "<br /><br /> </div> <div id=\"demo2\"></div> </div> </div>"]]></Attributes>
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
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="黑体" style="0" size="96" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=@.;cgQe1"9l`1pn?"lR=)C_g1OknPjV/-qGt'g1cST80-FCbb2llCGV_p=p)`KGH@>r@k
js*Ag.<0FQbtO<`;X;%@73YX^J\U/4.9?U?.PGJ.T$(-C:9n*BVa7-#:iJo[s.n+$:(8[F\a
N>OV<8n+WrlJ,A;=_4j$km7mq=(2a!<7t1q5$#0a7pR;?Xao<noH!h3eA\YqKdJnN-J,.a4Q
G%RI]A_5+'k51u>IegC2fH/]AIkuepN*=a(>6&VqFR>"(b*VME5Xo2&5*T%2&cYGTE[9IkJr+W
+Ski&MNnd#m$8!iTk$(N*:\!XI_lKmr-d;J;P_2":'\62@.'07,'+7VaU.(:<r?*\&F/K\Ua
kJuhcV8'V(_XZgT?qA$*k.a+3j6AIF1&o,h4"g.oBOa2t4nR>I[a9>VSAR*ZM"+7.(P[qB..
@X3c]AkMVG*Xd&Uq&#g[j<6`N*N'aL6"l!>Os(Rp\W\A->?sUHu+2V]A<@tm]AoP;fgf#=UHNpU
$/GS'GV<X4Wg3g/or9r3%W2FO-EfneamZNdc'.P/4a4/IC%G[nq:;>3jMDA_GOH"LOe'T$2b
H0)I=kco(Q^tbZWZG&mM-\+71\\!-_($8`p>fD?/nFBF;aETNKNE-1CO_bACPT+IUAi#VPuh
>Tg.jJrI3&8L+k`'S';kS"=*oG-M&e>\'L>Y*QLBp7P#R;'p0<"]A]A/blB*j/_CXBR42r<T\A
;6P[*-b.0Jh,D#JO:PG-c<Zp$PFn=7*RPSVR=f`SafXdi/`r+MNuoN8;-#`h6oeF5%5U^I]A#
,t8Ag+TtO1=mZh;g9=?WbF;F(A3j8`0\beDb3ONis='<pQShWFenf[ejkeeIRO-]A@/GeVcD3
dCIp#`ORG;7E24!8i[:Q/YHC1BMmXfK7S\,5?uG-c7ophu,[Z5[Xtk%ZV"oKQI.&6?kq>4Z&
<&55;O<arG2A4E.WYJ&MqH9sWp!)'(F`-iVN+K]Ag`ah>.T"^hFZFS7Nhg)]Aa3L1nZ>g;8Un'
AKm;`i5Nb\Lb!bJ1j,Ft8+>NrKW)=MZp&SS=Ns.tKkai.),MJQ9TF#ercdm!p00M6DOqI"oW
K1DYE92nCkojR.\=HrHd2"JJG2&F-]A-.;&AMdnL:_jsS>.-pIHr1<F/mbu?!ndgT3)ks$tJe
51g0'B\)'$>9@LgN"?(##RRPD69PFZ5MF/*;+]A=00tY+6L-&-"NN`6`AQ6^AeE^@C1lfPj>M
Kq0XXp&"?_?=u9,0,5R*HHWmp"5?9(Pp\I"*8U0(OYmKi#@uJ\X?X@4_r_j,fiOd`cb1ZCn<
&e_hQ^+h4%t%U6;Rq7dPYmi]A0o/qHQBEraUS/_.]ACWN`_!;7d:s=KFf6X7g*;I1aTl"&AR,:
dHX5P^7pq:ZYDk_Q_UPj]A]A:Z.Yu*@`q^D/LZ^O.JNpOK.RWj@?75lNDfc;:5t`UI4lgTYsGZ
3:%,.mLDi%eXD(<']A&H4?0S20@BI;qAf`!2o-$2Jp\[3NqpA*<41Y[26$&l?&8+=$8^A]A_om
"h=7<@,oT6k2`BX"u)KW[ru4uSiCSs>`7:kMb.D<8jIiU?Yr@No17oT10Dr]A.MP<5U&K,o)i
="ng03T$\@OM4\G9a_Da[1ZGR3bNE+_cUXmlra+>CO#oK]Ae`5-u77#,dR):1NFc+%(PMaX`A
BuE-,1SMBZX<&ur,Fs4ih2+7[T/3=1.#l*:g"B$^t[6`I,+?A<MXOdD@W;_E.hQU:<>]A2KNe
:cU,DQt8).-[bt3IL#6skr0j0nk+&@mgZ_b>'CVrOP'NE_0c'GQMhp&$,4Rpe/mX-<+_hsVG
qQY`<>B[PR$HYVK("*,"=?;NuQ.V=TS,9ZSX/nq[?$87ci7f)@AlNLa3?fe7#7[itaWOa9Xn
Q^8bSMPhabPY/2F'k(+d]A?blF3pR[_FAb8$oFD&+OQ(?HM7Io;A3-nRcb++1ZVg#@lfba.+o
3.=P=5>U=E(H2F7\^BADt_HDqDAeK9_p,ZO@JJ"WT/QMZmMb6A*3]A#'q-N$d..?Gc7N(R?*a
>J>jYjd9C_0qZF;m9n'Q<uPDI-DWkU>l+;)W<+P>!0YmPo/V=#=HcX6Qf:VmuqJPF]AH_':TE
<S=2OhhJ$ul>D_h=!8MaOHDV'q1#CKIEW2@&Kl7%hVFZ*:qSc2[@qbE48;J9RO&-!mmD?8`L
]AS05))\?tTa^93&e)AbB:N=>.ai_aJ5AK!@p'ckmb=D_"#A)Bso^Ku+n;.:g+^'k$m<-R+[U
d7Y$.s0)%=@$[Q&@KNQ2#1$NDV.VOtd,l+(S@=Ll[=#jh7h3gPMn?/nZ_:)`iMQ)PqY3!bG'
>5NQ7.Kb;NGrIZ71OtGeJOH5s!N8;;4"rmrc=r-L6A^4:4pun@Ad6@*DE\*th-cLa'P?h;\W
j\$TVb.s_c0-2!.a(@)i`g.]A1Nh-.D>o2F]AH@-*.%Hk0!hQc[k(6UiK5=l(/9m.""V#ZTrdc
F1C7<js(RbXZVmf>N99E_i]Ah+8Y."`MEc/Xs^>D"6=6+thC2Wm*M%=c0-E$iI@+Q*,C8ZgF_
=kC>+_O]AB;&I*ikFi?0uZ^*0hZt:RTq6::EjB@7K&G7Y=oNF"bVkY,>HHmHEdVU'-bp"DrVZ
/WIJiD?T]AO=#`)"[r;$IqVSVQnVher^eI7-fHlf'(S-ZR6qEqC!\X+*46;^"@8DRukfE,%V$
]Alr/.0R#L`0Sn6uIF't=;P?$5M:FUc5Nf4>'C&tsS-f(qFN&/gpN]Atj2YY)qB9lH%)dk.<5I
/cD1DYr@ZUIAe@[F+q=KYW5Jd6o\TVs,JQ4]AbRW`;$BiH4Q(fi882]A7I0J7Z18]AMiV%e;M9C
&1N9i#!2N+?(eEGJ?!YoGX[(="*i'lCM0$8F%1US1i7FNQ$cTEhp<ZH!4j.;qjN62pO)g`Ee
!hiU.Y4k#:gu#uERE'/u,=T6oVqc]A:U<5H1f58$k_!8?'dj1if.a^YlMO<WQD&Q9+O-(3KOV
;7RPY$E9i?j\IQ!)s)=B!"f#%U;[%.a-N4RqsT:#RS,6_sLN$?^E<k(&BP)@I.RoI$+:R8[f
,Co<XK2q0W_/Ij_.!ED\f!:h"EZS8t6m_<MA\&q^30FTgbTttqPM"c5.Y&TZske(eWKtU7NF
DWW-X@Lp[0fWGB)e>)FLrfRN._Mt9E)IFi)`8mhUXu]AWqbV?\*thb7o:$rfN*J@u*\$'U(ps
tEpF:%M;5:GE>4:@r*\l4B$R=Kt;*&O2ffUP9&:'"07dU5?Wf-3`P=XbAa9HhGYU1`i\4HUI
gaCc,p5,oq5PE.J4>o5WM'T)_^"u\-dYrkAf#Z]A$m;d$#M/)8R5"@`qO_cke0-H]AC_h"K1:r
AX>]AGZli3i"HAE\;SIfL>)Ze4dTnb`1@lE^N3`R\:"&\i=#6["JsTN-W,oZg)@r]AnNm[I?YS
),<]AZ\liK>fUGDp/?j6"E8%s`b27MJ3M6uoeU#`]AkHQJWbr>Bic>ig@VCYEN(7_QN6aeJ/]A;
/u2/r]A1h^QFCroo.=RT=6W&OO/=)!4motRVg!@]AB1RPI(;F?.j*jEgYSkWXa5ZNj,^@2hSch
97jB0Tb!qLWEj^;?2P]ANNFUpc9aD1=J3g`oHn"((B\>'FA#!?$jD\/(T2-$2"qa.:0jY(p\k
hn:o=J:2U+_]A53R[%$3]Ar[Ss\eefITI[ui.c8R'YgP2U7l+?$goIOBW+mdHn)Kblm5oAVh$F
Z).QLN.X]A:5ps\;OlI$ZpJu/(e&a$hl*;,fF#GCPU+>.^&j$g[hnnjqKE^QmQ!5;<M-UB?X0
r*S@!'>Bm-^k[m;IMI0dXlI,GKC*%*MK_m&%=2A2-L\aAV,"AfK'dosq$h&"WS<(F7lq^hM"
B#&2#11]A4_/EQMcJosL_B^1pR9H=__m)4RLhiLJRfpQnoR]AHMlN/ITU+)>s0oaXeqE_Inpc'
!44&#F.jQu:(f1ZTiG3f4Wi%-_:f.&2=;r]A#QJsPk[G'p/+I.c>"[go<=bkJ<k$4b]A[C/1[R
Zs5``^hip>VZsu@@55M9B(l?%B',]A8[ql%"5_'-mZ.;JkQ1sRtYKM.)<cI9@%QLO,fh2:'"t
gn)*YEdj>k@uc5YrrZ$/u3Y!/1V<i1R]A`0QNIG;$^kKB\lDo^euVgL)(+/qe)@9;]Al'Yc<o!
p/+g:V0iBitN,(>OANr`r&`UFR)WF.?d!:_mCJP6.<G[R*\:LO(&q6qG`qTbW)3PZHo1C1g7
@WP!K)!MhS-r<A64_tNq1!]Aq.QL6#>B\]Af^6!_h@alZ&BPL>rk8s:1!;#ZY_\18E'8Q4*fa'
Q/c^a_=44IL(*Fm#*B5dMO0VTl;_16hA#s]Abh6U*W&*Ejrn.g"tG;GF$=V\4Wg:HVcSmS[RM
+S"'_/#;kQn/`TZnjOI'/k$#g6L:W.AgYa'U`d&^>M,rP>>7PZk*cml:7P6HV^hb=G+IdL(V
jD2MG3KiJ-fZL`RH047MW@1VBH&U0N^T1B.OkERm20V^=d,'=u*L"?$B]AUb_@Vj$JCUTS71q
2@9Hje9b]Ae3rT.;$&Q_S:6JGNYKl?2F2W0=n86Kl-USj9]A3M'.`]A<6k]AOeV=p!&O7"6VT+Wh
$-PZa0eKRaLhj&3?a?UR+c#;2]Al"s,$&S?R[L>d_!_cjX_34n/[m);Jp5;3#XnrgWQp!/rYo
Q.m_)>7HSL_fCE"&[<4nbes#F[C)f:lT:.i@=`RgBVVT<BOP<*h!3;$7pr2"uO:2#k(0K,)n
[)^p5B%Km6W0PahXRs&T<SW[`T$1,_?to:p)YJ>.'mUWRR:>626Af5a5I$k:<?%0SMrVNC>,
tCm"/H:<Y"r9^=2oX>;f:`u-Z3qgjh]Aa7]AujnF$^sM*pm;LSVEFU@\DuVa"+"'m^:5t=J*a-
*6/_!]ALakA]Ae/8utL*UU3>+&Z:6V)"^B:hO>MDN3nc6Y!uBX0D'W>WYLk8BPj800`V&-?tWA
Hk4D5Q;Vs)-%]A"GH3cb;NSh<e]AI#$Xi!UC6'd.!]AG3&tE29TR$orKm`Ad(W<+dlV/e70>cmY
BP.C,t@$LM0ZCA0c5JO)G=fJf//`WlK:]Ah6nrPDkGA")G<)]A/g>)[qZoOcCu^n@qKi9P"Du:
43iD1Y^d=t;dm0Q^YQ1,]A@7:MA*F+d]ApCN?>B"G'HsY*ORZLAq"Y8/k(Li!,gG\RH`i#_H=C
Wi5Id7F.4j6SSXDi[X@;rB3=U:Qp.DTuAH$O,j&1ssd"Lu>^G?=-Gc<3CAB&*L<OAlW@g2;Y
5Q#\-BOc2*l*g+ZAOgCDbGnnSF-R`t5eXL4<BXotp%%)ct0;^'FDi>TIiG7X]A*a58L2'tQgK
oGZDCPj:8U\=?LK3``!&klah-IM$We-N%IlJOK@nmu^2'.!,1Hh#S&f1r=d0Z=/Ef8o#jgq@
-s2>In[?md7Ono0aKFp#c'ItU2`Puf9PMPZ33pb88\WalGIO:b+j#\b&Df;Y2J^]A?u5^f*7b
<XPj2G9HIG\P*4cA%K*W`8hG-S6;*4=,sahN4+hC#^*V&3pNBMHIoNeHGL'3Y4()g\VI1j4H
IAt-bu-/3pNDIXu;0$!9bj`k)J3jm"ca`PkVbr=6AeFnSM?@g);C\rKRUj#8S9WNV5iu=?,N
XFO&Pr$V*iCl?Fh5[XfW'1g]AL7rHm-FWlkCIJ9/fY9Hn0ai*ojuMVUihBhM@d.1\Tj#N&3rn
4*@t,nXl9C&GS2@cWsU%Z:0sJFRhuW0/)I;E:AVJBOmSrT?dU$Y,eE!'WN$@7?7eY,+06*3/
`LK%$ME#ZF_b.;Jh[Gd`PeVbO+sa^,HCq!9mg<d@#^Uo*&a8%=U6.]A]AN9ME3ENO:8`hFe4*^
Y"f#ZJcGPu(RI<I`>.M@MiVqS3cNo1V7qN%Mnu<KrC`]A&eTXhQ@G<7P4OQuIYD2,';--N$A`
kWBD6oCNo9:=]A"%#N:O>QU6(<dMWAUHD6"'cH6#NALW<*r=]Aak\B*_]AR*6_V\<>N(peX+r9Y
PVIr4u%))2XB4\JiFF<$%j2lN$c+asLnt);o;nTmF]A<;I?4-ooI2t>."00B0l[`a/BD2sft=
V?<"n22V&nok)f/r$Znd*gHZk>"hSWPSiuquU\mn\[QQ\!Lhd3i`i_b-djs&q8frgBFFioj$
ZM:'thnHA<mnGhFMI>>Y(YS`7<Wq_pf^9g>0]A/+CS4cXtIscBrW[5(JGho3%_9Vs9;I/`5D'
I7p'a(<Z%ZHsWAb#8c5LFn0o(P"OAmJcedBb0KMZ::ES9Gl8ggHVCQs??GpkJ4F=p9f/e_iV
6uDfrUJS]AZ2np8Yrr/W1%X*Yp-lGF4Peemgu>Yk<&&"546)0S_Z7HaM(qm2k%RNns)a(`S&7
*+bCip]ARY+:G#BfGj/\.D7]A!2aD<5pHD2UJ91Qg&7kFc3)As3!I$g*$C>pQYRdR/"C.3uK:;
8"YWFZ7hos&S#OrT:XX1@1cD"mi*'nPOOFRmC>B,U6["58HI<i]An5Q2hkfl+T;l8B-GNBHof
'"^`Uk*ZHSeeo@mI[)'&@2'.]ATpTDLT+d,_l'+3+l=>jF7\H3fj9ZA!/"'/S)*DYCaO6:*_$
$U4d(4@15`(5^a95*<jO.n0XKatRjEqt!,Jc!2nuI+s-6jT%4r7M.7t=X/N_Zalc"+nB:+O#
B4KqM3g4r'(~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="4" y="63" width="288" height="185"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WAbsoluteLayout">
<WidgetName name="absolute1"/>
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
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="11"/>
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
<![CDATA[新建图表标题]]></O>
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
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="0.75"/>
</AttrAlpha>
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
<Attr class="com.fr.plugin.chart.base.AttrMarkerAlpha">
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrBorderWithAlpha">
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
<AlphaAttr alpha="1.0"/>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrEffect">
<AttrEffect>
<attr enabled="false" period="3.2"/>
</AttrEffect>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="RoundFilledMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</Attr>
<Attr class="com.fr.plugin.chart.map.line.condition.AttrCurve">
<AttrCurve>
<attr lineWidth="0.5" bending="30.0" alpha="100.0"/>
</AttrCurve>
</Attr>
<Attr class="com.fr.plugin.chart.map.line.condition.AttrLineEffect">
<AttrEffect>
<attr enabled="true" period="30.0"/>
<lineEffectAttr animationType="default"/>
<marker>
<VanAttrMarker>
<Attr isCommon="false" markerType="NullMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[lO<9(kN.ld@UNU%p%320!n*#MbeXK/j[G5P!\&"_(WVVp3QVR99FM?2%#e2+X6kJT%%aVN0'
f#?RK*~
]]></IM>
</FineImage>
</Background>
</VanAttrMarker>
</marker>
</AttrEffect>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapLabel">
<AttrMapLabel>
<areaLabel class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
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
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
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
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name=".SF NS Text" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
<Attr4VanChartScatter>
<Type rangeLegendType="1"/>
<GradualLegend>
<GradualIntervalConfig>
<IntervalConfigAttr subColor="-14374913" divStage="2.0"/>
<ValueRange IsCustomMin="false" IsCustomMax="false"/>
<ColorDistList>
<ColorDist color="-14548993" dist="0.0"/>
<ColorDist color="-9583361" dist="0.5"/>
<ColorDist color="-14548993" dist="1.0"/>
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
<AFStyle colorStyle="0"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="false"/>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartMapPlotAttr mapType="custom" geourl="assets/map/geographic/world/中国.json" zoomlevel="0" mapmarkertype="1"/>
<pointHotHyperLink>
<NameJavaScriptGroup>
<NameJavaScript name="当前表单对象1">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="name"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report4" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象2">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="name"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="chart3" animateType="none"/>
<linkType type="0"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象3">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="name"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report0_c_c_c_c_c" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
<NameJavaScript name="当前表单对象4">
<JavaScript class="com.fr.form.main.FormHyperlink">
<JavaScript class="com.fr.form.main.FormHyperlink">
<Parameters>
<Parameter>
<Attributes name="name"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=AREA_NAME]]></Attributes>
</O>
</Parameter>
</Parameters>
<TargetFrame>
<![CDATA[_blank]]></TargetFrame>
<Features/>
<realateName realateValue="report3" animateType="none"/>
<linkType type="1"/>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
</pointHotHyperLink>
<lineMapDataProcessor>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
</lineMapDataProcessor>
<GisLayer>
<Attr gislayer="predefined_layer" layerName="深蓝"/>
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
<pointDefinition class="com.fr.plugin.chart.map.data.VanMapMoreNameCDDefinition">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[日销表]]></Name>
</TableData>
<CategoryName value="地级市"/>
<ChartSummaryColumn name="月营业" function="com.fr.data.util.function.NoneFunction" customName="月营业"/>
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
</pointDefinition>
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
<attr moreLabel="false" autoTooltip="true"/>
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
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
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
<Chart name="默认" chartClass="com.fr.plugin.chart.vanchart.VanChart">
<Chart class="com.fr.plugin.chart.vanchart.VanChart">
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="11"/>
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
<![CDATA[新建图表标题]]></O>
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
<Attr class="com.fr.plugin.chart.base.AttrEffect">
<AttrEffect>
<attr enabled="false" period="3.2"/>
</AttrEffect>
</Attr>
<Attr class="com.fr.plugin.chart.map.line.condition.AttrCurve">
<AttrCurve>
<attr lineWidth="0.5" bending="30.0" alpha="100.0"/>
</AttrCurve>
</Attr>
<Attr class="com.fr.plugin.chart.map.line.condition.AttrLineEffect">
<AttrEffect>
<attr enabled="true" period="30.0"/>
<lineEffectAttr animationType="default"/>
<marker>
<VanAttrMarker>
<Attr isCommon="true" markerType="NullMarker" radius="4.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
</VanAttrMarker>
</marker>
</AttrEffect>
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
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="5" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipMapValueFormat">
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
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="false" markerType="NullMarker" radius="4.5" width="26.0" height="42.0"/>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[!V$='qMA$D7h#eD$31&+%7s)Y;?-[s)#sX:.0p+X!!'fuK$F>r"h7,l5u`*![8q'ZP>rI&r\
2a<?c5?"N!?%$Y[056)e7)tlo#+e#*aq[7&('oD@FN;%(9Ij)d=1!N3sZH.ft@=O9o2o@)uW
_Y'6920Eaa"G_m#S.0F"^hfC,e?1.p2jN5M(kC&ucH,"Fr52N#6A\:DR;;%g<ebslPes;;=r
YF5rM<;gJP\R==b-^jH\[MN.RDq_\3dW!UrmW?"o]A99RcZ5"n4%,8JOf[kn>,phjP%ee-hV!
?dXJj$.+7'r?O1MLun_sJJoWHkAMO$p0&1:I^298E]A7'*]A>OK#?W3.>e,O-)*'>bRle5:t[X
UO88l[HM*?o?/^l6G-"Jk=>2"73o:S$An'CR%i]AZIHGXt(!8I]A8J)2`6[V2c`P7P]AnLTMFJq
`;5D\j:HC&-Tt.PUZHceqM7LdrLmXebZk;0gECl/<hUHEF_P"(TM+="/'SLN4U>j/=N[4Nl?
K*TQq/%M9<)**FGg4*LAs0(/BLI$EIL#m"9`lPa2,&VCjIN),F%5%<Sq]A`IZCSkkbFRd.h4i
XJ&%`U+.=]A_`&L^!dVrN*uhLlN_*pWgCX]A4fhX?St`&n0.enKc2f#3_9YcT4IdSLF>Nu1$[-
b]A:h]AJ7enACW;,[-JkkeSnl2H<joYYTsdPe.]ANIu&0aWHfV54F/r2n/'[fn(f(!N==_FU%q;
905/#Ru`cpr&'B(=C!j6;uJ#s?$KA5o>(Z<n8jj73gQg2a51AsM`V42R)7Ft<7GTXLZ@D,7I
[D6H'/ldkJQl<#;>jtBb^kk/SoK)c;$$h2nO\jB_PG#iu-8c*Ju=sa)KHs3V@RhC+&1s&%pi
`M9>SK5pr_`6h5)s+IEG$gsYAC.:P;aQ#^tI.Q$ofo"bQ;dJEgN+`\'(6!:43"bAkW=73<3'
Ii9/6eMnNPh5&UQk+Y;@%B_J?XpdQf]A=>LP:Hp']AVsXRZK_O6IcP*/!T/lD8QrZsF6m@o4ZO
U3)4J9d_X]A;o:K6&Y=Amg8MK7l<3^`DXNO7[Q3cb$7gV.p:&a\rOP[8K_XW+:-RL;<!7JC0+
2<KV*Pi@]A>Kkkir/EEr:)<W-E_&7oNl\)%Gk&2*1W6@p=R_tAuFq583BtDC:%WR^(^]AQc/WV
HSifsN"Pm2X9uSYiJ(08\tb+\:^Vg$E9\MUl3*N#)Anm9V\82$Z73\q+kj#4F1Yd^VuXG&JM
<c-MQ+>9ZG?eP5Z-*nEf=_`2moAX7j3Bj;8bR8mjJL9EH4Ok"]A=jE`cTFi07LjF\gKC&6r=Q
SNg9o2clQ\BqNcI5GU.n7)JC\N@;q:jFJ)nWelX:U!+>CE(H3r0G$Vfp^lqJ\93]Ab64E)X4s
L?r;:U#HFUbB^85uuL"R@LgB_NZ]A2=87l8KdLE04"IM<<C$Br9%80X-P!a"K?1VQOZ`53Ca[
T\,o//aj0WgEQHQ]A(eN1i^N2,"o-'VlF=SsDuho^\dOb&F4'<]ANjq@)LegkdmnBi-lH^L+^f
<jfLQT6XE@<:jO#WZ9aZCuTaK/1]Acf3_NeRqI\fhF8;L8"b",*"`$^EYD2Ipp(>:]Abk*N?6B
MK3m@f'F'M+*iGL.rpuq8\8_o7e;@7=8>(+R/(K8\^c:HgL\RZ6-4X_dB>W*"J?6T3SA/>%+
4/%fjonApa0BkuMW!p]AG1o`kRnhEPQ'8(H$jMigjt9#=4Jg\P1YU`666cHd!\4C"STi%62lk
l5nj1j$O)`Caj+o2eQ^lgrU)H,<WcKp-[%\$.W8`-9.M<A^fP/?:E1P!#1tD`1TVQ)adJ)XW
M"#Z]A'^,<S\Au,l<nkXA:F%l-&QVW2Tb-XGW^0YhqG1@,@S<3Gr6d2siD(^Q.1sjR2?uSG^P
PViKjWs3n..C_4rP/WO:hNn!!#SZ:.26O@"J~
]]></IM>
</FineImage>
</Background>
</VanAttrMarker>
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
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="4" visible="false"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
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
<VanChartMapPlotAttr mapType="point" geourl="assets/map/geographic/world/中国/浙江省.json" zoomlevel="0" mapmarkertype="0" nullvaluecolor="-3355444"/>
<lineMapDataProcessor>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
</lineMapDataProcessor>
<GisLayer>
<Attr gislayer="predefined_layer" layerName="深蓝"/>
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
<pointDefinition class="com.fr.plugin.chart.map.data.VanMapMoreNameCDDefinition">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<CategoryName value="地级市"/>
<ChartSummaryColumn name="营业额" function="com.fr.data.util.function.NoneFunction" customName="营业额"/>
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
</pointDefinition>
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
<attr moreLabel="false" autoTooltip="true"/>
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
</body>
</InnerWidget>
<BoundsAttr x="21" y="27" width="320" height="211"/>
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
<![CDATA[SU,WF<,:7g,3<Vj>EUT*&oFIBMBYO%K2ee>3\f><b$e8:jKQ_)UQgX,K8N9`,UHW]A;GoN/&7
[!*E<]Ao(F'/1KB%6G?T!rA$ht-_tq5h!lqnB61H[W*dFksCdoJTo(AVE,O&-OFdDd'mXJtt.
!:I]A4?IV5:^IK]Al=*O/qcf&?Ds_7&:F/rgMaqg@+c1Se0Fg%3Ac#`@Ui=Pm%:1j,q5Ng,Lb_
rh3sf#RIH.J,c@Hb7)AiT9d3mHNA@%pdkr4N$!GDJl[X[V;P1oZ6^@6ge:2X$5<A[?NT5Iup
q[nTo03p#,*?VHH:4$4tVnfQ]A8p8)eYWX-m?\E7q/^M>:+!WHX\olO0=@bM4Vn.C\_]A/>_-S
C)h'bONG=_8U4aJ+&*-LT,O;H\5)asGfZ9*eWlX=I;"=EJjnpL%ASRM)0ts$(_0GXGE]AR8^@
.5N\VoGW-jqgG+Ie*i"@%9k7R)$P=,2VWjEXN[1gJMni4GP9qlVBh?"lt/Q+^*[j&7H&>#fW
?)`7o4_^Hhk(i;Va&H!b)C^M]As6'e6ZQo"r"o9G#.n[i;"W$;5gB>RNd5<_t+LsTKUAt9#ip
g>Cn-:--rZu\a.;<7+uk/:8f"JULMIP81J)[`2t96n(0W^YBU&=cNAgj??bFd6bFH@-q(`$$
&`c"T+`lbodJ=1FJc9toL+^L1hoGmi68XHV2pbf-8h+K+U)fPKO,>I:B3I*UD;YIE_=c;&/0
-6k_8k5@F(b&qKDeFf=?Q@b"Tbsru6^tNHU<*"89A=m2,9S<4\Qfei2\2/?s@Vl>OG@HHNcd
2E2eOlGhK*1:QFkF%TP):=mEYWX2\W:<FSa<<3_G>,E8]AX-$lIak#M@&R)"e?NjDq3>Fj-G-
cg9N),4".$lmF'NEiR^VO1pdAu]ARQ29#P?od)+IWk,aNUEMhTU"R8E\.V/7"Da`:"sKdLe'R
k,O?2t.LcefsZCq'PF?Q9gh+T;[b59&J3`8k6\&<Ltl-i(f(!CR:jV3uu]ARefe[tC2l,5!dN
/?WD0etkF"oHk$jcT.!]A?5)A?8-IUH/tNEfQ"XF_2%;3,t@e!UjX6&MhJ;tfNK2p5D`21W)3
5d*NK'(u0674b35h;RBX.MNq6?+;n6B)E,%<[%+C!eeLDg[:MsbVgNt#TPfFcFhOha:A`GS:
r)O5%T1j.b++k,:jEfb9/E-Nd1BW1.(;BBt28'p%/!5RLliDZ'kM:4(tE'G%2_>S54Rr.rR)
.a;24T6l(d'MWhTN3=lm3lQ[F"*di,6'6542)JsBWVY8P%IMF_a<**I^YD8H2OJ,[9CA<]A^f
V0?6j8KVL\8`?e!B*DW=(Y@.6/em;PG)4+@'gSjV8I)q7!IW6Xd+$@r;(5<F,+p->.Zm"l,T
^Em/KCu5!j<.UF?18N%s#9$7-R_bW_N=mYaSN)$]A;ZJCS8[d)34C)lPA.oV0krI&cqN7Vhpj
c`TjMVF%<<OETod-ZNC6d=sF9d4YZ%+Nmt6\kn4s2X2RY;?hhP$rjUl<@t>TR\V9VIfJZ]A0s
4mI@T4H,K>$I+l&4r^Qr\!9T,^[eA8$ma)q1_9Orr:!<D8:JJO1#.aRgkb<A7UjR'_T3ll:2
(o#8-[c`eif.5C:NRU6OcWQY@u(HW's6*6jW"Q2"o[(GDnTSBVeE%d+NY&$)3CsRf8rOKu_c
XmUngCMiej0?a2A<Y_Lf(!MfD^,tQhAD>Zq`&#;?YJs(Lq\t^<">:64DHs,GIaqX^^"<Io:)
(JYt2gH+SY4+$d]AYp7T5rCeaE8cMt)lD?]AQ^&KIsj3I=Ks4R<^&d<[ZluK>01(L&&985PKcu
ei;b<0X@-l@[-CX%eCaG"/Vl6r::Q1pTl!`Z`*K&AXUb)<H+;B32?X8O=2s\!#%O9>S&fep0
`c?J02E9]AN)W*&a]AaV47!hNYSB)IP*DT_(o@OJGBYq'GglVN]AkV]AF%SPD)`*/S_r!==/Ed56
q*!RGAI`qEMM5,fi-5N#iDtIVLaK"TX"c8H0r=`>P+bXPpd#(!Rie2T`7q<DDpk1M?:2q,Od
5okTUGc[:Y]A:LJZ)N/^aH0kilJ3_b$7F7n,B`t[`eR(VUokkcU4LseP/6dZg86Ami72*C#RS
fQi(XI4H)J9!1Bm^Q>1/c]A48Zl5_%8#@4`\d@`j<-.s892YRO\?J"oIkoH=U84hq'^B1X_Q"
-=;'/Xc<HB^AR15V?mcoa3epXq6_M-7tGH.TqYYUL(ae(!T-N<Os@;eCu-8!4gai$k0<.i#l
?mFV'Y,ASDU_S7R7i:2,$X&OQW]AjEVS0`V[p+I3ORR$LIgk:oS\PPU1:]A?)FC2-P+(lC*_d?
Re@]Au8*g>o^XXt&VDpu[9Vdn%aaBEFP_?5W!(=-aSNs?_nQPYb8ppYCnMsmqg%n^L\O"9]Ah%
TQirEqP/,"J&?(o@jI)FTVBVUf%[8i'%+.JXU81!cP/D%epJC<dcA;c$$W(=6RQ5kjrI=Jf'
6c_@#%@SMYQTpj>Xcp2EPLHi<<&qB9:$BN2`$&Y=g/XuqcEhg^s[G6JMmm6ABBU,3R5E)DdW
s*7H-#V&3Hd2<cu/hCr%ACk==g,U)mU/DZE]ACm-\U=U&J!mb?uol5DIC&pWmHOSCVQX=Tj&f
'QggFGI.ZiK(aDNSeK:c?<$]AO"5^kqtN&6:M%Q\2SGCBtM+meV(6Fg-&>1>4%/JE0DLP6uu9
>3QNrDq*C7_BC,a.81&^Im;C=mDdJCmD5M\D4*E96^Z?d862!?Go$1Ul"SSkS"#n$'@F,`[$
f%KT"Nt$j"gZ8QHMeM/F#9;S=37bkj`tOGh?PEfkL)\[W%NX3E:l$1N\]A6K6Pp]A<MOVAaTc3
q#Xk89?Efe'f)Eh"/:3@g7->p43)NSRpBT`I)m\5HI#BL9D"f$.N#G9Mnc=="g#Z(YKX#!8,
L`'c6c"ONR\2C6qNtogg#RU3K5C*=O(>VrlZt?a#6n*CUT50)$E)1"a>6]A@\$=rr,`(P@=*`
s'b?Af1Z<X.f6+c0A&2fs6I0jD#Ajp.oloEnH6K7[>@Oakqr6<9=""dODkqLWNPZc4@@";#M
u(**.EGpt[.")50OW3:p4H0sI?PK@Y@huiPEc^j_MgdDmYk>q_V#kl#@]Ad;<5/?mdB,gF<Nk
UoV^@s0)PJ3hj5,7F-908oV)1I4rMcXb[J$%*[0."(j]A@><]A!7Tg.)]A<7t&-:u;)cID92:EC
daH'Pdf5?^hEiZ!/Ej>5YnKf*Gh_*h=\+JPa*S[2EX3k$cmFV*0p`[J#E^hlo'%1?@$J;V7[
q[%Qu%"_qn(]ArZ0*-Y+j0WH/ApRXhk$uHH1#$bXV?MNdH\Y(m5pl*h]AQC']AF\,$+YZ+8>>AY
KT!Hq,s7rb.#&3dV7`pRkKlpE#$YIU?$$cNG1;\5.l\QS!N86I<XFPm`7]A\L*XgARJk?3c=-
^K6-]A/jghKF1GJ^WORAhY,$t]A2\Yr:AV"W$N]AZNfA8ZLE!J`%B9'^?)"UX_XEON(B(5tMU^F
?!.4*:\Yi?dtii_BH+:`%o0(hu2`;EeMthJstEaKmYkVg=o1A/(2B=[9%W*8e$2^64u2a4.J
jHlUuHH(.$<1SVeqO=bRNMl1!-POD1G';NR_YN/g`^NdGUIAoqJV(VF7\Tq\n*T2@YK"<hJf
0"g_\3+0'6lUHHu7t[ED'o8U"cnd"cgl:]A^<rWKd55pF`Jt%bBJIAe4Ha*WKclUQnVG)O`a'
BZ3WDt4$%!0IsFn<1OY\/o\`Bp*f;:2_TI#GK,`nL49pCuh1f)[e@QSiAuZlEs5Mh2PA+9d#
\&P08&/LGN6<rFM`>_.ogB%UDL<<)gfGC5sPS.#SR".]AKfch8o&,Z:J3l1rf&!,MPS#J[^3B
G01#hUZ)O2G\bIEb(m5+uu#^J1P9+&H$:7F?*Jd;i4/b=#q)A;9/FFh@`i,mERXai\9`hVkk
OmZ#X\0>je@:B#[ce1_Z<eqgI#aOkUMl@R*$S%-h.8bs]A'I#2j\Xs0^MA$Cb]AUpmf]A[_N'=c
,eff3S4OZr_J%jXShb0#UmZKpnb=!N-Z,rP-Zl5%?E2?d\d:<4l&n.29;)Se`?XhfM55cFe%
+N0iX8EWU3J_c-B?ZcTDJTJ_@En,g$>U.+9r<+.0nDb!FWOXJA)ZcTQ)G6`41C`&LN*2']A5f
U(-"]AY^l6C'56?!S"uM.lT4@gupD4+WF*!q:,tlRPN\)U;l.8.DVV"K5Is1CP.BuZg/n#g&0
rq8A=J!^:C1./Zme^S12L'rZI6>_De-k7a^aA%oU"/r^Zk3t/@)H@AY44abc$#$*qXE5DPq7
Tl>FQn:eq__jV_,N]AkQ)*sQeV03`ofFAKX<*O-X53a_KOr;W]AL-2$+(%dT*1H,.Y/5;kO3ki
5ECVE;XiI^n>9r]ACik/o^,QR<Vl5-LcAttiR`q*jXs94J64'P6ru%g(R99:fnU76)WE>6W$/
oPOM\t?\K!b'876]A_=qK2_uF5jBupVrt56(SoKh0Y'5lIl9+_-j@ZKXM[ED5Nl_OSf@0Ml.n
?rP<pqK>T+*-\O>^k)6oYqHaZYAC!lrCr>-iqQk:M`8s=hm"NBm*SXG7d*'sUV%TkZ<t$4A'
MW1W#)DRFM4a?//k&bk9C%(0T_(8c4P#`n,kYePH#?$$]AjJ:&)_6@NRclF>R[0uQG?d8/MPX
sFdZH6q=*22">J+#!>sk;#H*!7ij;L6*Og7>6e^N97?['Cl^Lo,A[S^7W;BqpPTAk5:rI;g7
ia<IV9HUU1A3#)^8fqg]AMo_G=@4[cr+\0ZcY&?CTK!nosQhWEr@L)2gCsN;j$N/n:q]Ac?fSK
I7W;6u0t,]A3AF/^:os`ICk-#_0:?Y&IY*NQKLsX#\3$4$?"X$+,QeE]A=Fk_8XsoeJ0@8<Ph/
p-"OVT&lQ]AL(j`4$J@Eg4h=(ReSZ58l]AZl,OB#4q=ij1t>!(+Y5Fne2kK]AbM<?:bu4(WE(bC
00.!%f^1H)HFbJMg^tpo/H45ZpZS"l86I:,-Kf<;6>TsW(s8*p6^Cg75ZhIRI1moJnY(1/<6
``ET\_$Ef4FBcO#o%Q;#hI,.KsA9gEId,WjXsa@&.23JaAJ,F(=m,>T+o0`#">h;C!XE]AK)S
]Air%aACaeld:%2E#7gD@mJ5("V%eTg,u-nflII;jUNGYQP3!YrmrRu'74i>2(`+.gIAll'CR
WV:`@2A;!;pNE0R\m3W>^W<0qaJm]Ai8[N0P+NS_a(NN^F=t',q_ra>a5OZ?4qoYQ=qn"2UgF
jrhb9p,8FP8=jFnYUaQu+T'r2W8fc0O7&p;fM<bQK'm5u1DEsYlJQ'_MY>Zs8C\S#=?>';@j
NYR<-)%pH+2Y0Th;44Dn1Vl]Ahj(Y/YQaRN(eRu!KO6Y1MhR:SR./:)g3c(&e()!nM23[(l+C
r(gDgBAK,.p.`[#s?Kf<1;-kNRFHcIuR/Ihn4!V&)aGcZNF)17H3T">pI,T$(P=VYm!EL#Va
2cnJ(Qn[--O)/XmUmMrYbe^6O##X;:*pAa*]A4GIjiP"+t9lGVFQo+u14sOL1AA9Sn*8>Do78
`PqR[5"e1MZ]A,^UDohAX6e?%qt+4lMc:<#'XaO/Ma2jhFqA&^n.Ss!CXq$U)*'TQ%PY`M-pP
cEs_Y$*P0OIf6iac8LmpY,K]AI$$7u(l=>sS6>U"o<hmNump//Qp?]A/0KJbZj;r?qu$DZHgcM
p*IX1G<`=52QkWmkK_BfP-7q_@Aqi^Rgolb,,ti3A(q@<;maLQC6+fL!sCUOVN1/A7gkFmud
j=jZs?F&F1Y,-pR-Ha@(2DqU,=[l)KA_)lmHY,$VcoODO400;7XT,Y;ckkbhDs#FJ4=d/+X5
R,?gAM>OWgU?pe,+`8(drrqDtqe9H<(f+)cGEPFU\YL+k_BXGtphZnBAmellr[-\$r_0M+lp
f5"D2(I>s0pQ^r3\uh2gJ9>ZjBG[G"C0u+-ZJ?^oXAc.)?-Q97Du0G!9?rYe#&iOZNN47efO
WW$>YLe!o%U&kDG#P]A(dDMq,CdpkTTIFRetHS<D.S8]A4YCE]A*Vb3$U?1-S*L<'eg;`oE/n/:
?H$(8!0R?:O/-@j)cHFk-WD5-\?.rJ0LMtkI=19ha:U2qt\$_RXkhR&iakc\02H:/YPkVS]Aj
Kf"W_s44#e,!&<q%7J`RN5g@bIp4iQgXSeG1n8BO`:Jr=tbZkl)]Ap/5H^d#`;44gX8GIkFD)
hces,!D3=hDuJB#D(o!#]A0GDf:F]Ad4LKq'0[E795g4+B;9B6XN`Skg+rGBj@Tu0p9.;\DGk/
OI7@'WmNft^"qCLR3=blC>P_=_QjT_%:NADmm3(J1KBqM%2:$q7ggP!%@p)5D.$Y?d<o:X9U
fIIAeFLUFL16J"khrljn1@18MViTST&;$!%k/J/Ji2o?sk*:f4:rL`ulWDO;-`LCl92Oh$&H
XM1rSkm)5\;V79R_gD4=nikb\9!3\7)O-n.4QH:^"PeDQ1PqTq/RV):;!FV\p4+ho^GoY92m
*_RA>Qr>"n^.^g.F[A3EPkB]AUqV80m=l'RWt^dXruSb\4eX1J'ke+G#B&fnsRjmarp@O<rcZ
^%Z#pWp^SRpZ;bqagJ"#HNd1k[iNNEf]Ac_,lg?/udPo0PC(8:\_qeF^,^6EfIb*\A(Xbn@&P
6Wf3t'5MB##rraj]A?G%9Tlr6l"k>R`F<Kb>\-Q^%Rl10]Ar&M_FS<Pj!9`VkHRO1Z0p-0KXU:
O"]A4N$c:N"L8W^I\Y4,:?UT&2`o>u['FNgaEVZ:=caS6?r&=i;,g;O&ATbs+57orHn!T,;0P
gC>C98B"?;8ZG;&@BEf['esL3!>f`8_`_`Vn)qlLN#".rkdP(bg5/[ZC*jXI@hl+RgVjQktT
lIGaB=so8c,4d[nDZB"o4S<*XKs8S]A8;:^;6[]A=!!_W&Z#s/m&Z&8<%bDpQ&>@\(9_D7=ian
ps>q-=nmJA3JY+k9KZW1`P3n,[GCiG=hE;:((![%F4Z)=D(:^^WEP.Tdm;]A6PjJgQUqAn0>Z
d9/Y<E,jinbt7dMk4/N!=3Nb7Xok52[#?.g*e`fQG=IL;)Hh-(#%/Hj1ApO*-Vadc1"r.i7^
?bK.8B`Bn[kYU)BgmO=_E4c$DGP">4BTc?On7>"*"m4Z\;FN(BE*PnIkK,+j0L`;l/VECV/Z
9CK;$iRJHW$9aE4+ur\$7i`kedr!>&UR`HR%26k[J,b>Yb5e`HC0:'/2_eg'5#D104X'S+i5
&JJe(<K?KpnrQd9&BR2O'i+VfdM28L9C<Q(S-`igfkhE=t+9%tgp[h6B;m&eGUs!#,ALip#U
CPn./"1?L*_WmpU[jN)35AdY\(@:A]A9l;&Yrrjq;'Y@N4:n8J\G^A%[k@W!+:0j%m,2@LI2d
gm8W(<`<`.7-o=8;tQfG`Jb\D/j(J(m?C,<"Y,WiI>F<Zf&kU7#U<>2<c`78`"BMh8e]A%W1Y
OGssu\SZblG:-A*m]A2PGWi1,q$%H-8!;-En'%-/F`h677_?nA+NcG3n'H"3,"?n7*B?:>r`6
4>t^7o`r='(NR8.a:EU*b2KU/[Z#]A!e_^0ea)b_XV>O1RPj:7Gjn`E+mIYca^$t,RLB\a[T-
[m2"G>F6C/*YJ.joCR"6r=/:'`//HTik1jF#"_oR$eeR-/[IIL\0RajB-_[lIaM6>sf</!e#
@H<>dRrON'QrnSH2TF3cI!f0"P\c21'@CP2Oe!M7<#>;4AlWp)i&OY]A)+d2[i%YG*F2o5)16
?iH(8:NVo/7V/7mO9"]A-O>u7_3I_/.JL9@q>=+,1g>LdhEOu5cY;`%HQm0#[aVC3TrX&]A./!
TSk1Erdgg//$q/c%#2W5UcN@8!n.4*3AO$VjkJLqDI]A/3<ZN'7+-uAn`<?<+3,moW`YEaoK$
7G`d60Je*l&-7N*)ApA_O>Yl.%mW=lPeF+MEjembUX?m1-=Sk^KB]A>o>OoCnecFsg-Xq>A,E
%3m?dRm`'R$F]AM5XRCk7]AhYP6LBJdU-bbZH4"$/^<6'K#+[2g0Z7+14UZpJBZR5LD*OB-YqX
>5_lKmsX:T-=a^2mHYFXef0s?5t39Rl7C(jdPScEeaUn_`@kHkoGMP=`f>Sg8"<ssrn4i`5n
==\ECeo.N>E86A5aH,0N['0S^j80p99U90fgjImf[.!FZ":%GC3"8Mqt-qMeg*0)236ej>0m
BLPV-kct?:&VY5+un4DL$Y2Vg;Q]AC<eQ9ho+2E7Dn<dW'e0Q#u8Vmu#gr$pU_k/D'pF!dUaC
]APOoD=eVl7%khB0UWb5SsUU5h7WT7?H+4bLMgW";8bUmCH4<!W]Add8csHT)Uq!k7,fcB9hU5
PV`#!f)5LRhcHW-6<UjJk[&,99DGQY9;Zb0l-bgQ&TTt(c_%iEl58ZL,k@EJ&=j3N*gBe#d9
^l>\N0o:M(Jkn6CRTW#^i%''lOTPJj,;\)_H!Ka$*@]At.Aj%OTHT3T+qkRV\0ji;_6n<ZH_5
KM(g&IMLb+,:?<6JApbg4:\L1qu4Mj!1./eR/;br.bY.jM?R?BQA@WO:8p]AQY9PZO_&MCj%\
F#Wf5-4-urQI"uG'UU1ue/NkOradNiJR@o6&\%9of?)c9:XJ:HKep<&O^Re"Ib_65,BNN]A+Z
Jt4gbl)4j%uuCR$U+-T%1*SfOE*X%d:cC>c*S'gWA\$1^E##.dR\&in]ASni0icVUCf*!HTnh
ge6qITg(%q?R1s*rtRh6^D0*#dR3!98T/t%Xhi__TnQAj#^:0q%4s4VYR4X$[?Ue`<fKX.e@
4N=C&$L<I]AWKR2Dk!p\U#>6"f+re':)sMue;MOae9kl=ar;8.!_C]A<I;etoXFKR($TZfT("Y
<^a+'9%"BfD5"[p:EeN&/%DR]AS0r<2&gWUKV/E>uT]Atk.%#t#$bLCEb[Q:)7.9Q=E>1J2Xr_
:DWs_7.^<q0acT1'.^DorU`.)"R7EAt9T]AdnOU9n]A:T`2L^qqjg:e#-]AV[o]A;>&)\+ABkNWA
'^n-G'r@kBYu(1JDPbqp7P0Z4m;]A!?rqiWF`ltHnL8Qq$]A$b3!TAIH;QLq=%bCh34S,[!QLV
cgCBg`]AkL8-5>\Xt3T$L!6Eh5bjH@3;^O&Ppjb]AEAb.Wsic]Ap$:$6Y5j!4W7A(1mp.!lUMAp
cbrkZ]ABCHp:Q9h="?f.<a@E]A5f3J6(88Y(lobE:gRWR=s/"*OIT;WiRNE=,gf>@ZIbI@r:9B
ppP_opcKgrFIHrkhO9-CF//Np`XQ\%s_!fm)WAM6^`)l=RdOFKKl(_(RIF)Lp%n>c:o[Isaj
nAp.n+^@Dr>D\?6kq5M^o,(n=!G14='M8?`lM4V1#8^@dM]AXX-n%f@`Ld"bC`BrQ@+A9fIto
%qc@:2bq\)XFjB4B<>U1>]A#AQnKe\q:WXK>"!V4Z&Ubbh2bh#f4V?j.-5`R[Y%8hc412)ABg
ZW99T,+0@`p9D-9sI=k`=Wmg:Nh5;(#\^lK10gfTgNDrK"W:J,N,d(PZQASO0sD(B5U4hJ]Ak
5q5"g9mcl-PDJB:\8V4T;4Ya0(-B!14$9k&%'@Kk/@gr5kUrIJ!kUdE9:fb6=ktNgc,CL[g[
#$e,smrfYlFX`3CE%AV8*W/A@t.7nK7TAdrj]AFhM#Z%0[UN*<3)5F\K%W3Bb#;Cjc,iWOn^9
7n;JM/6iP)*.Ks`A=#BQ6a*uaKd1h^hi@/'m202uDnS+E>]Aj5J]AFlO!<b=Gg2Cm0R0;1e3DO
ZdGrc=/:)gGmmS97=15/K\h@j>3%oY)n-sU'*@+T'<X5*+'Q*81pVWLd^%%+?h%E1%M]AjceF
t:I_i$Y7n.mm1U.5U:SUsFhNqEX-&=<(AJ6EEKUEt4'X8DnC6k3l<8GlDm/9ncM"S&E]ALu"O
i$4Z?IHjqR3=VaaF1a']Am;Neb"%j&0,n0)r`(G5_B)E+KrH_DPHB]A!ZLB[&0^&7RS0='HX@D
CjQOM%XkL?[?u:IHPk>uG"?(;<*U/T7$.N)>0dLRJOg(H\`N"6[^or!%FQnn+k&?uHZmnImf
c>VI;kmk`*2QSXej\Z[f)%$/.4(LF=*nc[g0WnYTW8s&>M@aZejnl;KjRIRqg]Aa#)<GYIUnO
tmcoPR<,%3RdT0'Pe3<jC?rC:#1$c:GjNOUuLE-Sb@Nh4l]AVG8/t,%Z]AUBi[<]Ag9;RAedka&
+A*`-5>,R*XaQu:YMj)oRH?\Z\?iZF["m[Q'4C@c0rFE=6s*N*b<E#s==I1'Jin(F&oPI(d)
[$.\+KpUuoT3pVs*GFH':#UL"NS.G'9pqQK/VG&%kr2LOF]A7*G^"_6"Kmf&\o%G*nA/j3o]AR
?tML'*hZWe)rJp&='B7$eDleaV]A+ZJK!$53EHcPQ(^~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[SU,WF<,:7g,3<Vj>EUT*&oFIBMBYO%K2ee>3\f><b$e8:jKQ_)UQgX,K8N9`,UHW]A;GoN/&7
[!*E<]Ao(F'/1KB%6G?T!rA$ht-_tq5h!lqnB61H[W*dFksCdoJTo(AVE,O&-OFdDd'mXJtt.
!:I]A4?IV5:^IK]Al=*O/qcf&?Ds_7&:F/rgMaqg@+c1Se0Fg%3Ac#`@Ui=Pm%:1j,q5Ng,Lb_
rh3sf#RIH.J,c@Hb7)AiT9d3mHNA@%pdkr4N$!GDJl[X[V;P1oZ6^@6ge:2X$5<A[?NT5Iup
q[nTo03p#,*?VHH:4$4tVnfQ]A8p8)eYWX-m?\E7q/^M>:+!WHX\olO0=@bM4Vn.C\_]A/>_-S
C)h'bONG=_8U4aJ+&*-LT,O;H\5)asGfZ9*eWlX=I;"=EJjnpL%ASRM)0ts$(_0GXGE]AR8^@
.5N\VoGW-jqgG+Ie*i"@%9k7R)$P=,2VWjEXN[1gJMni4GP9qlVBh?"lt/Q+^*[j&7H&>#fW
?)`7o4_^Hhk(i;Va&H!b)C^M]As6'e6ZQo"r"o9G#.n[i;"W$;5gB>RNd5<_t+LsTKUAt9#ip
g>Cn-:--rZu\a.;<7+uk/:8f"JULMIP81J)[`2t96n(0W^YBU&=cNAgj??bFd6bFH@-q(`$$
&`c"T+`lbodJ=1FJc9toL+^L1hoGmi68XHV2pbf-8h+K+U)fPKO,>I:B3I*UD;YIE_=c;&/0
-6k_8k5@F(b&qKDeFf=?Q@b"Tbsru6^tNHU<*"89A=m2,9S<4\Qfei2\2/?s@Vl>OG@HHNcd
2E2eOlGhK*1:QFkF%TP):=mEYWX2\W:<FSa<<3_G>,E8]AX-$lIak#M@&R)"e?NjDq3>Fj-G-
cg9N),4".$lmF'NEiR^VO1pdAu]ARQ29#P?od)+IWk,aNUEMhTU"R8E\.V/7"Da`:"sKdLe'R
k,O?2t.LcefsZCq'PF?Q9gh+T;[b59&J3`8k6\&<Ltl-i(f(!CR:jV3uu]ARefe[tC2l,5!dN
/?WD0etkF"oHk$jcT.!]A?5)A?8-IUH/tNEfQ"XF_2%;3,t@e!UjX6&MhJ;tfNK2p5D`21W)3
5d*NK'(u0674b35h;RBX.MNq6?+;n6B)E,%<[%+C!eeLDg[:MsbVgNt#TPfFcFhOha:A`GS:
r)O5%T1j.b++k,:jEfb9/E-Nd1BW1.(;BBt28'p%/!5RLliDZ'kM:4(tE'G%2_>S54Rr.rR)
.a;24T6l(d'MWhTN3=lm3lQ[F"*di,6'6542)JsBWVY8P%IMF_a<**I^YD8H2OJ,[9CA<]A^f
V0?6j8KVL\8`?e!B*DW=(Y@.6/em;PG)4+@'gSjV8I)q7!IW6Xd+$@r;(5<F,+p->.Zm"l,T
^Em/KCu5!j<.UF?18N%s#9$7-R_bW_N=mYaSN)$]A;ZJCS8[d)34C)lPA.oV0krI&cqN7Vhpj
c`TjMVF%<<OETod-ZNC6d=sF9d4YZ%+Nmt6\kn4s2X2RY;?hhP$rjUl<@t>TR\V9VIfJZ]A0s
4mI@T4H,K>$I+l&4r^Qr\!9T,^[eA8$ma)q1_9Orr:!<D8:JJO1#.aRgkb<A7UjR'_T3ll:2
(o#8-[c`eif.5C:NRU6OcWQY@u(HW's6*6jW"Q2"o[(GDnTSBVeE%d+NY&$)3CsRf8rOKu_c
XmUngCMiej0?a2A<Y_Lf(!MfD^,tQhAD>Zq`&#;?YJs(Lq\t^<">:64DHs,GIaqX^^"<Io:)
(JYt2gH+SY4+$d]AYp7T5rCeaE8cMt)lD?]AQ^&KIsj3I=Ks4R<^&d<[ZluK>01(L&&985PKcu
ei;b<0X@-l@[-CX%eCaG"/Vl6r::Q1pTl!`Z`*K&AXUb)<H+;B32?X8O=2s\!#%O9>S&fep0
`c?J02E9]AN)W*&a]AaV47!hNYSB)IP*DT_(o@OJGBYq'GglVN]AkV]AF%SPD)`*/S_r!==/Ed56
q*!RGAI`qEMM5,fi-5N#iDtIVLaK"TX"c8H0r=`>P+bXPpd#(!Rie2T`7q<DDpk1M?:2q,Od
5okTUGc[:Y]A:LJZ)N/^aH0kilJ3_b$7F7n,B`t[`eR(VUokkcU4LseP/6dZg86Ami72*C#RS
fQi(XI4H)J9!1Bm^Q>1/c]A48Zl5_%8#@4`\d@`j<-.s892YRO\?J"oIkoH=U84hq'^B1X_Q"
-=;'/Xc<HB^AR15V?mcoa3epXq6_M-7tGH.TqYYUL(ae(!T-N<Os@;eCu-8!4gai$k0<.i#l
?mFV'Y,ASDU_S7R7i:2,$X&OQW]AjEVS0`V[p+I3ORR$LIgk:oS\PPU1:]A?)FC2-P+(lC*_d?
Re@]Au8*g>o^XXt&VDpu[9Vdn%aaBEFP_?5W!(=-aSNs?_nQPYb8ppYCnMsmqg%n^L\O"9]Ah%
TQirEqP/,"J&?(o@jI)FTVBVUf%[8i'%+.JXU81!cP/D%epJC<dcA;c$$W(=6RQ5kjrI=Jf'
6c_@#%@SMYQTpj>Xcp2EPLHi<<&qB9:$BN2`$&Y=g/XuqcEhg^s[G6JMmm6ABBU,3R5E)DdW
s*7H-#V&3Hd2<cu/hCr%ACk==g,U)mU/DZE]ACm-\U=U&J!mb?uol5DIC&pWmHOSCVQX=Tj&f
'QggFGI.ZiK(aDNSeK:c?<$]AO"5^kqtN&6:M%Q\2SGCBtM+meV(6Fg-&>1>4%/JE0DLP6uu9
>3QNrDq*C7_BC,a.81&^Im;C=mDdJCmD5M\D4*E96^Z?d862!?Go$1Ul"SSkS"#n$'@F,`[$
f%KT"Nt$j"gZ8QHMeM/F#9;S=37bkj`tOGh?PEfkL)\[W%NX3E:l$1N\]A6K6Pp]A<MOVAaTc3
q#Xk89?Efe'f)Eh"/:3@g7->p43)NSRpBT`I)m\5HI#BL9D"f$.N#G9Mnc=="g#Z(YKX#!8,
L`'c6c"ONR\2C6qNtogg#RU3K5C*=O(>VrlZt?a#6n*CUT50)$E)1"a>6]A@\$=rr,`(P@=*`
s'b?Af1Z<X.f6+c0A&2fs6I0jD#Ajp.oloEnH6K7[>@Oakqr6<9=""dODkqLWNPZc4@@";#M
u(**.EGpt[.")50OW3:p4H0sI?PK@Y@huiPEc^j_MgdDmYk>q_V#kl#@]Ad;<5/?mdB,gF<Nk
UoV^@s0)PJ3hj5,7F-908oV)1I4rMcXb[J$%*[0."(j]A@><]A!7Tg.)]A<7t&-:u;)cID92:EC
daH'Pdf5?^hEiZ!/Ej>5YnKf*Gh_*h=\+JPa*S[2EX3k$cmFV*0p`[J#E^hlo'%1?@$J;V7[
q[%Qu%"_qn(]ArZ0*-Y+j0WH/ApRXhk$uHH1#$bXV?MNdH\Y(m5pl*h]AQC']AF\,$+YZ+8>>AY
KT!Hq,s7rb.#&3dV7`pRkKlpE#$YIU?$$cNG1;\5.l\QS!N86I<XFPm`7]A\L*XgARJk?3c=-
^K6-]A/jghKF1GJ^WORAhY,$t]A2\Yr:AV"W$N]AZNfA8ZLE!J`%B9'^?)"UX_XEON(B(5tMU^F
?!.4*:\Yi?dtii_BH+:`%o0(hu2`;EeMthJstEaKmYkVg=o1A/(2B=[9%W*8e$2^64u2a4.J
jHlUuHH(.$<1SVeqO=bRNMl1!-POD1G';NR_YN/g`^NdGUIAoqJV(VF7\Tq\n*T2@YK"<hJf
0"g_\3+0'6lUHHu7t[ED'o8U"cnd"cgl:]A^<rWKd55pF`Jt%bBJIAe4Ha*WKclUQnVG)O`a'
BZ3WDt4$%!0IsFn<1OY\/o\`Bp*f;:2_TI#GK,`nL49pCuh1f)[e@QSiAuZlEs5Mh2PA+9d#
\&P08&/LGN6<rFM`>_.ogB%UDL<<)gfGC5sPS.#SR".]AKfch8o&,Z:J3l1rf&!,MPS#J[^3B
G01#hUZ)O2G\bIEb(m5+uu#^J1P9+&H$:7F?*Jd;i4/b=#q)A;9/FFh@`i,mERXai\9`hVkk
OmZ#X\0>je@:B#[ce1_Z<eqgI#aOkUMl@R*$S%-h.8bs]A'I#2j\Xs0^MA$Cb]AUpmf]A[_N'=c
,eff3S4OZr_J%jXShb0#UmZKpnb=!N-Z,rP-Zl5%?E2?d\d:<4l&n.29;)Se`?XhfM55cFe%
+N0iX8EWU3J_c-B?ZcTDJTJ_@En,g$>U.+9r<+.0nDb!FWOXJA)ZcTQ)G6`41C`&LN*2']A5f
U(-"]AY^l6C'56?!S"uM.lT4@gupD4+WF*!q:,tlRPN\)U;l.8.DVV"K5Is1CP.BuZg/n#g&0
rq8A=J!^:C1./Zme^S12L'rZI6>_De-k7a^aA%oU"/r^Zk3t/@)H@AY44abc$#$*qXE5DPq7
Tl>FQn:eq__jV_,N]AkQ)*sQeV03`ofFAKX<*O-X53a_KOr;W]AL-2$+(%dT*1H,.Y/5;kO3ki
5ECVE;XiI^n>9r]ACik/o^,QR<Vl5-LcAttiR`q*jXs94J64'P6ru%g(R99:fnU76)WE>6W$/
oPOM\t?\K!b'876]A_=qK2_uF5jBupVrt56(SoKh0Y'5lIl9+_-j@ZKXM[ED5Nl_OSf@0Ml.n
?rP<pqK>T+*-\O>^k)6oYqHaZYAC!lrCr>-iqQk:M`8s=hm"NBm*SXG7d*'sUV%TkZ<t$4A'
MW1W#)DRFM4a?//k&bk9C%(0T_(8c4P#`n,kYePH#?$$]AjJ:&)_6@NRclF>R[0uQG?d8/MPX
sFdZH6q=*22">J+#!>sk;#H*!7ij;L6*Og7>6e^N97?['Cl^Lo,A[S^7W;BqpPTAk5:rI;g7
ia<IV9HUU1A3#)^8fqg]AMo_G=@4[cr+\0ZcY&?CTK!nosQhWEr@L)2gCsN;j$N/n:q]Ac?fSK
I7W;6u0t,]A3AF/^:os`ICk-#_0:?Y&IY*NQKLsX#\3$4$?"X$+,QeE]A=Fk_8XsoeJ0@8<Ph/
p-"OVT&lQ]AL(j`4$J@Eg4h=(ReSZ58l]AZl,OB#4q=ij1t>!(+Y5Fne2kK]AbM<?:bu4(WE(bC
00.!%f^1H)HFbJMg^tpo/H45ZpZS"l86I:,-Kf<;6>TsW(s8*p6^Cg75ZhIRI1moJnY(1/<6
``ET\_$Ef4FBcO#o%Q;#hI,.KsA9gEId,WjXsa@&.23JaAJ,F(=m,>T+o0`#">h;C!XE]AK)S
]Air%aACaeld:%2E#7gD@mJ5("V%eTg,u-nflII;jUNGYQP3!YrmrRu'74i>2(`+.gIAll'CR
WV:`@2A;!;pNE0R\m3W>^W<0qaJm]Ai8[N0P+NS_a(NN^F=t',q_ra>a5OZ?4qoYQ=qn"2UgF
jrhb9p,8FP8=jFnYUaQu+T'r2W8fc0O7&p;fM<bQK'm5u1DEsYlJQ'_MY>Zs8C\S#=?>';@j
NYR<-)%pH+2Y0Th;44Dn1Vl]Ahj(Y/YQaRN(eRu!KO6Y1MhR:SR./:)g3c(&e()!nM23[(l+C
r(gDgBAK,.p.`[#s?Kf<1;-kNRFHcIuR/Ihn4!V&)aGcZNF)17H3T">pI,T$(P=VYm!EL#Va
2cnJ(Qn[--O)/XmUmMrYbe^6O##X;:*pAa*]A4GIjiP"+t9lGVFQo+u14sOL1AA9Sn*8>Do78
`PqR[5"e1MZ]A,^UDohAX6e?%qt+4lMc:<#'XaO/Ma2jhFqA&^n.Ss!CXq$U)*'TQ%PY`M-pP
cEs_Y$*P0OIf6iac8LmpY,K]AI$$7u(l=>sS6>U"o<hmNump//Qp?]A/0KJbZj;r?qu$DZHgcM
p*IX1G<`=52QkWmkK_BfP-7q_@Aqi^Rgolb,,ti3A(q@<;maLQC6+fL!sCUOVN1/A7gkFmud
j=jZs?F&F1Y,-pR-Ha@(2DqU,=[l)KA_)lmHY,$VcoODO400;7XT,Y;ckkbhDs#FJ4=d/+X5
R,?gAM>OWgU?pe,+`8(drrqDtqe9H<(f+)cGEPFU\YL+k_BXGtphZnBAmellr[-\$r_0M+lp
f5"D2(I>s0pQ^r3\uh2gJ9>ZjBG[G"C0u+-ZJ?^oXAc.)?-Q97Du0G!9?rYe#&iOZNN47efO
WW$>YLe!o%U&kDG#P]A(dDMq,CdpkTTIFRetHS<D.S8]A4YCE]A*Vb3$U?1-S*L<'eg;`oE/n/:
?H$(8!0R?:O/-@j)cHFk-WD5-\?.rJ0LMtkI=19ha:U2qt\$_RXkhR&iakc\02H:/YPkVS]Aj
Kf"W_s44#e,!&<q%7J`RN5g@bIp4iQgXSeG1n8BO`:Jr=tbZkl)]Ap/5H^d#`;44gX8GIkFD)
hces,!D3=hDuJB#D(o!#]A0GDf:F]Ad4LKq'0[E795g4+B;9B6XN`Skg+rGBj@Tu0p9.;\DGk/
OI7@'WmNft^"qCLR3=blC>P_=_QjT_%:NADmm3(J1KBqM%2:$q7ggP!%@p)5D.$Y?d<o:X9U
fIIAeFLUFL16J"khrljn1@18MViTST&;$!%k/J/Ji2o?sk*:f4:rL`ulWDO;-`LCl92Oh$&H
XM1rSkm)5\;V79R_gD4=nikb\9!3\7)O-n.4QH:^"PeDQ1PqTq/RV):;!FV\p4+ho^GoY92m
*_RA>Qr>"n^.^g.F[A3EPkB]AUqV80m=l'RWt^dXruSb\4eX1J'ke+G#B&fnsRjmarp@O<rcZ
^%Z#pWp^SRpZ;bqagJ"#HNd1k[iNNEf]Ac_,lg?/udPo0PC(8:\_qeF^,^6EfIb*\A(Xbn@&P
6Wf3t'5MB##rraj]A?G%9Tlr6l"k>R`F<Kb>\-Q^%Rl10]Ar&M_FS<Pj!9`VkHRO1Z0p-0KXU:
O"]A4N$c:N"L8W^I\Y4,:?UT&2`o>u['FNgaEVZ:=caS6?r&=i;,g;O&ATbs+57orHn!T,;0P
gC>C98B"?;8ZG;&@BEf['esL3!>f`8_`_`Vn)qlLN#".rkdP(bg5/[ZC*jXI@hl+RgVjQktT
lIGaB=so8c,4d[nDZB"o4S<*XKs8S]A8;:^;6[]A=!!_W&Z#s/m&Z&8<%bDpQ&>@\(9_D7=ian
ps>q-=nmJA3JY+k9KZW1`P3n,[GCiG=hE;:((![%F4Z)=D(:^^WEP.Tdm;]A6PjJgQUqAn0>Z
d9/Y<E,jinbt7dMk4/N!=3Nb7Xok52[#?.g*e`fQG=IL;)Hh-(#%/Hj1ApO*-Vadc1"r.i7^
?bK.8B`Bn[kYU)BgmO=_E4c$DGP">4BTc?On7>"*"m4Z\;FN(BE*PnIkK,+j0L`;l/VECV/Z
9CK;$iRJHW$9aE4+ur\$7i`kedr!>&UR`HR%26k[J,b>Yb5e`HC0:'/2_eg'5#D104X'S+i5
&JJe(<K?KpnrQd9&BR2O'i+VfdM28L9C<Q(S-`igfkhE=t+9%tgp[h6B;m&eGUs!#,ALip#U
CPn./"1?L*_WmpU[jN)35AdY\(@:A]A9l;&Yrrjq;'Y@N4:n8J\G^A%[k@W!+:0j%m,2@LI2d
gm8W(<`<`.7-o=8;tQfG`Jb\D/j(J(m?C,<"Y,WiI>F<Zf&kU7#U<>2<c`78`"BMh8e]A%W1Y
OGssu\SZblG:-A*m]A2PGWi1,q$%H-8!;-En'%-/F`h677_?nA+NcG3n'H"3,"?n7*B?:>r`6
4>t^7o`r='(NR8.a:EU*b2KU/[Z#]A!e_^0ea)b_XV>O1RPj:7Gjn`E+mIYca^$t,RLB\a[T-
[m2"G>F6C/*YJ.joCR"6r=/:'`//HTik1jF#"_oR$eeR-/[IIL\0RajB-_[lIaM6>sf</!e#
@H<>dRrON'QrnSH2TF3cI!f0"P\c21'@CP2Oe!M7<#>;4AlWp)i&OY]A)+d2[i%YG*F2o5)16
?iH(8:NVo/7V/7mO9"]A-O>u7_3I_/.JL9@q>=+,1g>LdhEOu5cY;`%HQm0#[aVC3TrX&]A./!
TSk1Erdgg//$q/c%#2W5UcN@8!n.4*3AO$VjkJLqDI]A/3<ZN'7+-uAn`<?<+3,moW`YEaoK$
7G`d60Je*l&-7N*)ApA_O>Yl.%mW=lPeF+MEjembUX?m1-=Sk^KB]A>o>OoCnecFsg-Xq>A,E
%3m?dRm`'R$F]AM5XRCk7]AhYP6LBJdU-bbZH4"$/^<6'K#+[2g0Z7+14UZpJBZR5LD*OB-YqX
>5_lKmsX:T-=a^2mHYFXef0s?5t39Rl7C(jdPScEeaUn_`@kHkoGMP=`f>Sg8"<ssrn4i`5n
==\ECeo.N>E86A5aH,0N['0S^j80p99U90fgjImf[.!FZ":%GC3"8Mqt-qMeg*0)236ej>0m
BLPV-kct?:&VY5+un4DL$Y2Vg;Q]AC<eQ9ho+2E7Dn<dW'e0Q#u8Vmu#gr$pU_k/D'pF!dUaC
]APOoD=eVl7%khB0UWb5SsUU5h7WT7?H+4bLMgW";8bUmCH4<!W]Add8csHT)Uq!k7,fcB9hU5
PV`#!f)5LRhcHW-6<UjJk[&,99DGQY9;Zb0l-bgQ&TTt(c_%iEl58ZL,k@EJ&=j3N*gBe#d9
^l>\N0o:M(Jkn6CRTW#^i%''lOTPJj,;\)_H!Ka$*@]At.Aj%OTHT3T+qkRV\0ji;_6n<ZH_5
KM(g&IMLb+,:?<6JApbg4:\L1qu4Mj!1./eR/;br.bY.jM?R?BQA@WO:8p]AQY9PZO_&MCj%\
F#Wf5-4-urQI"uG'UU1ue/NkOradNiJR@o6&\%9of?)c9:XJ:HKep<&O^Re"Ib_65,BNN]A+Z
Jt4gbl)4j%uuCR$U+-T%1*SfOE*X%d:cC>c*S'gWA\$1^E##.dR\&in]ASni0icVUCf*!HTnh
ge6qITg(%q?R1s*rtRh6^D0*#dR3!98T/t%Xhi__TnQAj#^:0q%4s4VYR4X$[?Ue`<fKX.e@
4N=C&$L<I]AWKR2Dk!p\U#>6"f+re':)sMue;MOae9kl=ar;8.!_C]A<I;etoXFKR($TZfT("Y
<^a+'9%"BfD5"[p:EeN&/%DR]AS0r<2&gWUKV/E>uT]Atk.%#t#$bLCEb[Q:)7.9Q=E>1J2Xr_
:DWs_7.^<q0acT1'.^DorU`.)"R7EAt9T]AdnOU9n]A:T`2L^qqjg:e#-]AV[o]A;>&)\+ABkNWA
'^n-G'r@kBYu(1JDPbqp7P0Z4m;]A!?rqiWF`ltHnL8Qq$]A$b3!TAIH;QLq=%bCh34S,[!QLV
cgCBg`]AkL8-5>\Xt3T$L!6Eh5bjH@3;^O&Ppjb]AEAb.Wsic]Ap$:$6Y5j!4W7A(1mp.!lUMAp
cbrkZ]ABCHp:Q9h="?f.<a@E]A5f3J6(88Y(lobE:gRWR=s/"*OIT;WiRNE=,gf>@ZIbI@r:9B
ppP_opcKgrFIHrkhO9-CF//Np`XQ\%s_!fm)WAM6^`)l=RdOFKKl(_(RIF)Lp%n>c:o[Isaj
nAp.n+^@Dr>D\?6kq5M^o,(n=!G14='M8?`lM4V1#8^@dM]AXX-n%f@`Ld"bC`BrQ@+A9fIto
%qc@:2bq\)XFjB4B<>U1>]A#AQnKe\q:WXK>"!V4Z&Ubbh2bh#f4V?j.-5`R[Y%8hc412)ABg
ZW99T,+0@`p9D-9sI=k`=Wmg:Nh5;(#\^lK10gfTgNDrK"W:J,N,d(PZQASO0sD(B5U4hJ]Ak
5q5"g9mcl-PDJB:\8V4T;4Ya0(-B!14$9k&%'@Kk/@gr5kUrIJ!kUdE9:fb6=ktNgc,CL[g[
#$e,smrfYlFX`3CE%AV8*W/A@t.7nK7TAdrj]AFhM#Z%0[UN*<3)5F\K%W3Bb#;Cjc,iWOn^9
7n;JM/6iP)*.Ks`A=#BQ6a*uaKd1h^hi@/'m202uDnS+E>]Aj5J]AFlO!<b=Gg2Cm0R0;1e3DO
ZdGrc=/:)gGmmS97=15/K\h@j>3%oY)n-sU'*@+T'<X5*+'Q*81pVWLd^%%+?h%E1%M]AjceF
t:I_i$Y7n.mm1U.5U:SUsFhNqEX-&=<(AJ6EEKUEt4'X8DnC6k3l<8GlDm/9ncM"S&E]ALu"O
i$4Z?IHjqR3=VaaF1a']Am;Neb"%j&0,n0)r`(G5_B)E+KrH_DPHB]A!ZLB[&0^&7RS0='HX@D
CjQOM%XkL?[?u:IHPk>uG"?(;<*U/T7$.N)>0dLRJOg(H\`N"6[^or!%FQnn+k&?uHZmnImf
c>VI;kmk`*2QSXej\Z[f)%$/.4(LF=*nc[g0WnYTW8s&>M@aZejnl;KjRIRqg]Aa#)<GYIUnO
tmcoPR<,%3RdT0'Pe3<jC?rC:#1$c:GjNOUuLE-Sb@Nh4l]AVG8/t,%Z]AUBi[<]Ag9;RAedka&
+A*`-5>,R*XaQu:YMj)oRH?\Z\?iZF["m[Q'4C@c0rFE=6s*N*b<E#s==I1'Jin(F&oPI(d)
[$.\+KpUuoT3pVs*GFH':#UL"NS.G'9pqQK/VG&%kr2LOF]A7*G^"_6"Kmf&\o%G*nA/j3o]AR
?tML'*hZWe)rJp&='B7$eDleaV]A+ZJK!$53EHcPQ(^~
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
<CellElementList>
<C c="0" r="0">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p6>M#:X6J)XM/N1UI-4NN1:m^QqMrG;;\"qT38]AJd8QCZCP2Y&,-7?)j%J[$Km^mV<o3(
EFa&r_*@\SNFl%C-3X_V7gX77**s.L(M68h<6E'$)1=TbhpVeoi+Hd5c:O6TWcrK:0nG3p1n
Jp\TEsj4sOochF8SHMmZ$,7(U>V&,d("bMD"&1$?2O0-J&o@UMB9aL=5n%JAk:]ALAqNHsmKi
?.3rfC3_/RB.pDVcteO^t'3GDV*MVTgnaBLb8Xs2Bd380@@2rk2+`%qqplBd.Ger;E7_EYhd
#Ma0R@s`(Q'7.sn5jR`9'$mC07]AK]AhP:BERSDgJJnq4,K54/ib8g*@6Q*A=mL_qq.su6JqB_
ht[$!7qg\78.nhtIhqhE[_Ygs'HtR[KRQC$=S0Z;;LJ9>Dt:_]AosfV#:G@7kD.<9XGBU$_,a
m;[Vdak]AHg?QNdFLFXpd@55&pLS#Z<+"`gq@e:s*H1J:3kTupQY'S*2E01'KqhFPZ]A)HCVT>
OV0$_,:V%#]Agbc6NB6'n2D[H#h1GsZpFcRU.18jKBUuB)`"NsTXjn*:'FA0%P+72lD$@5)N2
oO*DFd+1`U*j/g,k%GlfJ@k1H;\p[/X12m^@[2@iOgILY-*K'^g<lMgoc\t$fZ,gb[#R%<Q.
1Q&)=s4bd_ddm@3a9NC)aR7&Os5#$"HHM6j.3*m)MUIZM3BQHq;1:N",<@6^-@HnsPZLl=AI
P%<47/4:=,MNWdmT'4ZfqK;S,5t@6\d+YeC3P/-*Vd,aq4kX:[:X$br\]A6:<h9DAM[TY<[DK
^)[HK;)`@,+>:$+!!r&DLe*&So/8g=r!$FIn3DQn1u%h@(>UrhB(T15kC6/biq-7iu?@E"86
4NZLc@]Ae'PY&[[#B41OI"1UcP+HPi<tF<p6?itjm=M@\n_NE2T#CW,D4MMYD7.)W1bCiZ2kF
eGVrjt!URV8Jh@o4(S-&s4%adP7]A<bjJ\'2)Z$sBu]AoUb/JK::RTm2@Lam./Wc-@`o8dm'5l
Z@DIGmP>PX]AGmZCGj^FC4+.4/=oh0CGH:"QZ)C)7#M]A=:2%,Y'@%_^&AX<4VT!]A&Fk<l[qT1
>%,-f6.>2'\SuW.<AXEGX"<Km+%g.')YS00gb2VTrb0YkJ+6XcFKY.i:h`^@m8,AC%k]A8:hE
PV^gLu_Yi"MYt&[I,#XBD;>D-*\3;T_LllDeb&gP:h9XTQ%B;EP6N3(RAlB'/V@CT:F\B,KU
OoNAHNJmD*U;mA@JD]AW44Nhj2%.W:l&pW%ST-1)NEIT^)N94iJS#rt%N_<$!X/44#e^d1f1[
-8uHX#WD[EMr'7;;mjN;N#P(*[DRI@?VR@M<`2O4_!`q]Ad*\aTAlJc_IWZQ*J7UFLT'X/ef@
?fYiGIsc:RLhA^9hDC`I^b,;\-bi]A!S4,l+KiQnlRa/XOq_9QF)O<"8##-$<3U7G^Wf]A0%$=
R:\8(!V(og4BX"SV:TL6qf*CI+="Q6I["A$*i)]Aas$jSVQ`9kI*t1OI2G'sM*aE#uCHZP'7H
mT-GFIMR1\38IP37%L01`D5nqt%M0pTu`BkX]Abf_<blrhM_Jcf:dkiCn*hG9j&:PjC=uqB16
bA?_Wm8hCB57ks0$F6n[T4:@T+eO*bWLY+]Ae3WB2/j=9I1kXf$,J"f(k>^)Pe*k([i"U\1S(
:%_YK4*b%@'WouokC7R.Nf?!#uE#iM>ll7cs'idAGIGR(+Pb:]A=IsjLY'm\`9OK2'=a8VC!C
_.V!eaGp:H1mlKtHi>bR-9n^qnslC1'6>c<nF\I\p$):++4Cd$:k!r5S@+Y^aP6i#J&H^K0I
o15Nq2WBbfH^GJ.f@G80jW?6d>koVS*>o5aXT8cF]Ahc_s;V[3\R'Wi;(lCG0M!JFklFS8\F=
)>I&T8Wlg/bE)QPTXANFrb0ZTB;n=0Mu-JK^_I+-FHm\]A80H/3O*pO9*?B.S81r'SMRS4cnc
0gDj*k9PN/FO-3#^<7pK=#S"4Q?a.3jJ17q_NS#iekkD)N)F<k*S,]A;I!Q3D1o+YeT3Z'MqP
si%A-HME&(rrt@\k),R5]Af)eQBK61gT<0aWH#@^(!f(;+\$*pX?>5n<Q=MZ,JiCMD`-<d'_P
Emq301?kILp&ZQh6?q=8;j#`cHO.kREae!>8"LS"b6$3kM%E%NW/>iXlS[_,Z,<Z"SUL-?RV
i+a^GL9@XGN0.Xrg8//o;sVaRQt-I7;<UPSOf/.oTG:#f+O@L;h%#@<WuMNd72nBE6+j<,hT
TrMl^0^u'iL2]A!_m^H6B>r<'H/k=gB,PVoihq1B#uk8g_A")XL#akofHKk_FTHkLtIXgf:b>
<%u&+@$g"U/MWN[b$q[=s#EJ$YKtC8(5O<P%]AG%XI1/q8iU>g*M,@a,aX4OsJNp7[fK@u`<_
`?Q1i\9K2lq%eCoFm1[hrRi7+CgtQZ!L$K@OWGmF0O]AU56S$EPngetJ;gq2idiF*)cd05af6
4<D.q_$C6E&0o4@U1ARj?C\Y8]A.kjdC8<t%G'n[#G8A<5_Pp+pKR@0Wa6On!Dq?$^/L3tKXi
Wg]As>fKMgCR9I4tTY8R>.t@emM04Qm(tH-$%SQ;9Md]AEA1%:7.(g*[K6<f1&K!A(Sl@X7cFJ
5jR(^4Q1_I[H.1tM?[6TQZ%a#L)^*_0Yn]A<+j<k("s6E$nt3\D;!,kk8dgF3Pf9C//h$_o(t
C.9%=?U2[d@=tD$d1E?i0bieJiRhoK9]A18iEb@S&45#*r#!a@ZXlO5G^<###]A%KS@A?G3cH@
)sQf*9lC8X=9'K*6?[C2XqjIli;uf\ir>D:2R%&?"g]A4n`L@0LT%Y[JLj9&ZDh)^?ONC&$um
l\P%;Jg?8Y;dH5DmCiPG7gE+>&"kZjc:lQBCe1Hl4ZKWC7JcH+[T3NU$nfJLYneTJX#\d#93
dg.kqnL(FtoS<j/r(>I_bTR33@A#!bR8T.q^^Sb[,6"l0>RdgRUtupLZ,'Ug2/6P1?46K[KB
)G0ETjdGXftk\QVe53l]AuVSZ&F4'>2hX8!;B`;:k)(kmKi%7Gp2d*IeUiX"\AAOTP3cBftjJ
'oo_Z#Y9B.IatB&U\t=e#mfk`'E!4$.&Eqf&&EtmMrn3(1/85c7nYAHX.kC#Ih12a()Q.u0"
ABrR(/p,@R?(IS28=$g$hlJ':TNfPSA-8?JH=n,A.;_).d69Q$0u8P&M&^$Lamt`7Mn*P_s-
]A>3]A72K2\#<ZbAu;nXE:Ir?jh,^f-Cmn'b=!P`n&-s+;IOBA46%U,%2OG,#o_iHlk9I?_QIR
,K2/-a[hbX_JFj7cr]A!nb@'"rC4Jd(8nhs.6gU6hQIQgm2^d%*Z$:s+k4pJ?eZ<hS"cYuWT6
lH4(SnWT",r<.VDLjTPm?>B1IYb#is#N#-=[ADj6j6AFY<IF5R5`B:l#qATFMBg,&)46%!MD
kAVQ/W4kRJT=b5auX*ImuCMO_Op!;r>Vr'/F(7OO=:t4J.Kkl"E%;sY(oNMhT^eid_Ct^1h2
1BL0MPdmEpfi\k&rP:>#sFM@3lY;NS4A3%.]A\<G1$]Aeda5d]AmZn[cu$S,muI*N`J+CiObGd:
ua2`?_$5FF=?$B)a,eP+!\1:E7=d7tKDS#!%)62<9j.4>DWLV^i=AHi(#W,Bin+&soQI!'1p
Ts:18oXkG-7YiP!Mfd<P@qdknbt=n\#juAFH@'-IAc>d;\2VmrrfOc*,ES<M+PV))LrLT,Zm
<84-0VTOBB?iM,$+r1-ECMl\`3^*WQ94AW&=gJ3p&rI&LGP:"?)VkC\XCr$-!Sp%N.Sh+3)s
50t^OF6f1a'6^%!>Web'VmK3*2p^tNI?tXh?456`*T%;3'NToFh^Kfc%q-;lqVe]A`=L1)o(7
PH[^md"3!$-Us*AQ*Dj^=P4L*Jp+=hXLP1php5iam6"VB+`nm+n"%EE6lA7?jZP:=0JdP(Zn
*$;&reLdOQ>SS]A_/\ds-dAYptq<Y:`N3XI9DaG)/9bQ:5Q6pqA^'J;7^W*:tZcgjD+m^@2Wn
A-Sr''40`c?O$=Q5c#4EG.qs3kh<eco`l;Qf_`&D^HOdQpl-(I*HNZ,45UP&E:701Eo3JBh&
7luWG'dsk,&rQbED,;BEd^;[q!CU?>h\--7N:*p4@9p2!URJ^A]AVl:5H\'ES_W3p1M+oKJB!
X\_mD1GFRQ$Nl!26-\'7eAnW;Fq;"SEa#(JM\@SMC<K$+ORduer@^_Dend:*c2ITf#d3pZ`;
U:K/qVC/dFG%3+lgla3L\G%q:'jH,So^r9m*o43bOuR.ChsE1NT21363X3AX5L/#T8N5%<8H
;n:M#W:'to>k/.;qShcWo^4dW%6(\Jp<:TcL@NL;7V$poeA*Xmr)l.:^e5\d<XXD*Mg=ZGFI
T*BtLB<s<c'dX_^#t*[bG+r.?;H.Hl;jH@n?A*Z'W2g[lLs2q)kn<0p.=XEV1fhr^'V#knMB
U0Dk2r0Ah*p9u5Q$6c?COb&fSk@g1dNfB<hNqbd87+7(<B+&q;^8*[L7hk!-nS#Y9V]ATrDl^
41^#qJE$g?>V`TGkRKLW+"13*2PDX"@gH^qY=X)$m%&.lV01]AGp5W>KI1]A(SH0?C.b#h0sJ(
on!t@G(*ARcMZ`6sUU;<RD<j#hQaR;0:=GHj-DGJITbaRuMRW*KXsfmX=(4P&(@7f96?4ZTa
l&`:?/Lc)hN#(lOlC^h=<1:CsWE#pI/jmbJ23\8Z;;)Jo3VWCh&R=+G\o8o5*"\>jNj-4h5G
'SRU7\Y'Z!bm1N^lbZSI0k&1ijP4;=4V/OAm[-?fs7@[@YbK5Qkom/j6)u%7:d%I[X_J?sEu
;4A1KcROd;h6n6Cbe!dDU<l6)ttp"(fJ#.a$=ZF!+h=at0p&?dlZ<7?LR5(Ir3L**@<BUEss
/;8260/6prYY=D0=rX@$D82AfoPu:14'o4!q-J:gBP\Qa-JoPtZE"f[`)*D$=2BOGFi\2?Fm
ZC]AF3!sXuO4KOFc3`M#mf8Enj>8X@dKb?a74I-ifPPU2B[o?]AQ(Cf9\2H.66He%I\&]AA@RcX
Qg2=V;NQWgnG0[)+8nEZnep-%=2ZU;e5#6~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[SU,WF<,:7g,3<Vj>EUT*&oFIBMBYO%K2ee>3\f><b$e8:jKQ_)UQgX,K8N9`,UHW]A;GoN/&7
[!*E<]Ao(F'/1KB%6G?T!rA$ht-_tq5h!lqnB61H[W*dFksCdoJTo(AVE,O&-OFdDd'mXJtt.
!:I]A4?IV5:^IK]Al=*O/qcf&?Ds_7&:F/rgMaqg@+c1Se0Fg%3Ac#`@Ui=Pm%:1j,q5Ng,Lb_
rh3sf#RIH.J,c@Hb7)AiT9d3mHNA@%pdkr4N$!GDJl[X[V;P1oZ6^@6ge:2X$5<A[?NT5Iup
q[nTo03p#,*?VHH:4$4tVnfQ]A8p8)eYWX-m?\E7q/^M>:+!WHX\olO0=@bM4Vn.C\_]A/>_-S
C)h'bONG=_8U4aJ+&*-LT,O;H\5)asGfZ9*eWlX=I;"=EJjnpL%ASRM)0ts$(_0GXGE]AR8^@
.5N\VoGW-jqgG+Ie*i"@%9k7R)$P=,2VWjEXN[1gJMni4GP9qlVBh?"lt/Q+^*[j&7H&>#fW
?)`7o4_^Hhk(i;Va&H!b)C^M]As6'e6ZQo"r"o9G#.n[i;"W$;5gB>RNd5<_t+LsTKUAt9#ip
g>Cn-:--rZu\a.;<7+uk/:8f"JULMIP81J)[`2t96n(0W^YBU&=cNAgj??bFd6bFH@-q(`$$
&`c"T+`lbodJ=1FJc9toL+^L1hoGmi68XHV2pbf-8h+K+U)fPKO,>I:B3I*UD;YIE_=c;&/0
-6k_8k5@F(b&qKDeFf=?Q@b"Tbsru6^tNHU<*"89A=m2,9S<4\Qfei2\2/?s@Vl>OG@HHNcd
2E2eOlGhK*1:QFkF%TP):=mEYWX2\W:<FSa<<3_G>,E8]AX-$lIak#M@&R)"e?NjDq3>Fj-G-
cg9N),4".$lmF'NEiR^VO1pdAu]ARQ29#P?od)+IWk,aNUEMhTU"R8E\.V/7"Da`:"sKdLe'R
k,O?2t.LcefsZCq'PF?Q9gh+T;[b59&J3`8k6\&<Ltl-i(f(!CR:jV3uu]ARefe[tC2l,5!dN
/?WD0etkF"oHk$jcT.!]A?5)A?8-IUH/tNEfQ"XF_2%;3,t@e!UjX6&MhJ;tfNK2p5D`21W)3
5d*NK'(u0674b35h;RBX.MNq6?+;n6B)E,%<[%+C!eeLDg[:MsbVgNt#TPfFcFhOha:A`GS:
r)O5%T1j.b++k,:jEfb9/E-Nd1BW1.(;BBt28'p%/!5RLliDZ'kM:4(tE'G%2_>S54Rr.rR)
.a;24T6l(d'MWhTN3=lm3lQ[F"*di,6'6542)JsBWVY8P%IMF_a<**I^YD8H2OJ,[9CA<]A^f
V0?6j8KVL\8`?e!B*DW=(Y@.6/em;PG)4+@'gSjV8I)q7!IW6Xd+$@r;(5<F,+p->.Zm"l,T
^Em/KCu5!j<.UF?18N%s#9$7-R_bW_N=mYaSN)$]A;ZJCS8[d)34C)lPA.oV0krI&cqN7Vhpj
c`TjMVF%<<OETod-ZNC6d=sF9d4YZ%+Nmt6\kn4s2X2RY;?hhP$rjUl<@t>TR\V9VIfJZ]A0s
4mI@T4H,K>$I+l&4r^Qr\!9T,^[eA8$ma)q1_9Orr:!<D8:JJO1#.aRgkb<A7UjR'_T3ll:2
(o#8-[c`eif.5C:NRU6OcWQY@u(HW's6*6jW"Q2"o[(GDnTSBVeE%d+NY&$)3CsRf8rOKu_c
XmUngCMiej0?a2A<Y_Lf(!MfD^,tQhAD>Zq`&#;?YJs(Lq\t^<">:64DHs,GIaqX^^"<Io:)
(JYt2gH+SY4+$d]AYp7T5rCeaE8cMt)lD?]AQ^&KIsj3I=Ks4R<^&d<[ZluK>01(L&&985PKcu
ei;b<0X@-l@[-CX%eCaG"/Vl6r::Q1pTl!`Z`*K&AXUb)<H+;B32?X8O=2s\!#%O9>S&fep0
`c?J02E9]AN)W*&a]AaV47!hNYSB)IP*DT_(o@OJGBYq'GglVN]AkV]AF%SPD)`*/S_r!==/Ed56
q*!RGAI`qEMM5,fi-5N#iDtIVLaK"TX"c8H0r=`>P+bXPpd#(!Rie2T`7q<DDpk1M?:2q,Od
5okTUGc[:Y]A:LJZ)N/^aH0kilJ3_b$7F7n,B`t[`eR(VUokkcU4LseP/6dZg86Ami72*C#RS
fQi(XI4H)J9!1Bm^Q>1/c]A48Zl5_%8#@4`\d@`j<-.s892YRO\?J"oIkoH=U84hq'^B1X_Q"
-=;'/Xc<HB^AR15V?mcoa3epXq6_M-7tGH.TqYYUL(ae(!T-N<Os@;eCu-8!4gai$k0<.i#l
?mFV'Y,ASDU_S7R7i:2,$X&OQW]AjEVS0`V[p+I3ORR$LIgk:oS\PPU1:]A?)FC2-P+(lC*_d?
Re@]Au8*g>o^XXt&VDpu[9Vdn%aaBEFP_?5W!(=-aSNs?_nQPYb8ppYCnMsmqg%n^L\O"9]Ah%
TQirEqP/,"J&?(o@jI)FTVBVUf%[8i'%+.JXU81!cP/D%epJC<dcA;c$$W(=6RQ5kjrI=Jf'
6c_@#%@SMYQTpj>Xcp2EPLHi<<&qB9:$BN2`$&Y=g/XuqcEhg^s[G6JMmm6ABBU,3R5E)DdW
s*7H-#V&3Hd2<cu/hCr%ACk==g,U)mU/DZE]ACm-\U=U&J!mb?uol5DIC&pWmHOSCVQX=Tj&f
'QggFGI.ZiK(aDNSeK:c?<$]AO"5^kqtN&6:M%Q\2SGCBtM+meV(6Fg-&>1>4%/JE0DLP6uu9
>3QNrDq*C7_BC,a.81&^Im;C=mDdJCmD5M\D4*E96^Z?d862!?Go$1Ul"SSkS"#n$'@F,`[$
f%KT"Nt$j"gZ8QHMeM/F#9;S=37bkj`tOGh?PEfkL)\[W%NX3E:l$1N\]A6K6Pp]A<MOVAaTc3
q#Xk89?Efe'f)Eh"/:3@g7->p43)NSRpBT`I)m\5HI#BL9D"f$.N#G9Mnc=="g#Z(YKX#!8,
L`'c6c"ONR\2C6qNtogg#RU3K5C*=O(>VrlZt?a#6n*CUT50)$E)1"a>6]A@\$=rr,`(P@=*`
s'b?Af1Z<X.f6+c0A&2fs6I0jD#Ajp.oloEnH6K7[>@Oakqr6<9=""dODkqLWNPZc4@@";#M
u(**.EGpt[.")50OW3:p4H0sI?PK@Y@huiPEc^j_MgdDmYk>q_V#kl#@]Ad;<5/?mdB,gF<Nk
UoV^@s0)PJ3hj5,7F-908oV)1I4rMcXb[J$%*[0."(j]A@><]A!7Tg.)]A<7t&-:u;)cID92:EC
daH'Pdf5?^hEiZ!/Ej>5YnKf*Gh_*h=\+JPa*S[2EX3k$cmFV*0p`[J#E^hlo'%1?@$J;V7[
q[%Qu%"_qn(]ArZ0*-Y+j0WH/ApRXhk$uHH1#$bXV?MNdH\Y(m5pl*h]AQC']AF\,$+YZ+8>>AY
KT!Hq,s7rb.#&3dV7`pRkKlpE#$YIU?$$cNG1;\5.l\QS!N86I<XFPm`7]A\L*XgARJk?3c=-
^K6-]A/jghKF1GJ^WORAhY,$t]A2\Yr:AV"W$N]AZNfA8ZLE!J`%B9'^?)"UX_XEON(B(5tMU^F
?!.4*:\Yi?dtii_BH+:`%o0(hu2`;EeMthJstEaKmYkVg=o1A/(2B=[9%W*8e$2^64u2a4.J
jHlUuHH(.$<1SVeqO=bRNMl1!-POD1G';NR_YN/g`^NdGUIAoqJV(VF7\Tq\n*T2@YK"<hJf
0"g_\3+0'6lUHHu7t[ED'o8U"cnd"cgl:]A^<rWKd55pF`Jt%bBJIAe4Ha*WKclUQnVG)O`a'
BZ3WDt4$%!0IsFn<1OY\/o\`Bp*f;:2_TI#GK,`nL49pCuh1f)[e@QSiAuZlEs5Mh2PA+9d#
\&P08&/LGN6<rFM`>_.ogB%UDL<<)gfGC5sPS.#SR".]AKfch8o&,Z:J3l1rf&!,MPS#J[^3B
G01#hUZ)O2G\bIEb(m5+uu#^J1P9+&H$:7F?*Jd;i4/b=#q)A;9/FFh@`i,mERXai\9`hVkk
OmZ#X\0>je@:B#[ce1_Z<eqgI#aOkUMl@R*$S%-h.8bs]A'I#2j\Xs0^MA$Cb]AUpmf]A[_N'=c
,eff3S4OZr_J%jXShb0#UmZKpnb=!N-Z,rP-Zl5%?E2?d\d:<4l&n.29;)Se`?XhfM55cFe%
+N0iX8EWU3J_c-B?ZcTDJTJ_@En,g$>U.+9r<+.0nDb!FWOXJA)ZcTQ)G6`41C`&LN*2']A5f
U(-"]AY^l6C'56?!S"uM.lT4@gupD4+WF*!q:,tlRPN\)U;l.8.DVV"K5Is1CP.BuZg/n#g&0
rq8A=J!^:C1./Zme^S12L'rZI6>_De-k7a^aA%oU"/r^Zk3t/@)H@AY44abc$#$*qXE5DPq7
Tl>FQn:eq__jV_,N]AkQ)*sQeV03`ofFAKX<*O-X53a_KOr;W]AL-2$+(%dT*1H,.Y/5;kO3ki
5ECVE;XiI^n>9r]ACik/o^,QR<Vl5-LcAttiR`q*jXs94J64'P6ru%g(R99:fnU76)WE>6W$/
oPOM\t?\K!b'876]A_=qK2_uF5jBupVrt56(SoKh0Y'5lIl9+_-j@ZKXM[ED5Nl_OSf@0Ml.n
?rP<pqK>T+*-\O>^k)6oYqHaZYAC!lrCr>-iqQk:M`8s=hm"NBm*SXG7d*'sUV%TkZ<t$4A'
MW1W#)DRFM4a?//k&bk9C%(0T_(8c4P#`n,kYePH#?$$]AjJ:&)_6@NRclF>R[0uQG?d8/MPX
sFdZH6q=*22">J+#!>sk;#H*!7ij;L6*Og7>6e^N97?['Cl^Lo,A[S^7W;BqpPTAk5:rI;g7
ia<IV9HUU1A3#)^8fqg]AMo_G=@4[cr+\0ZcY&?CTK!nosQhWEr@L)2gCsN;j$N/n:q]Ac?fSK
I7W;6u0t,]A3AF/^:os`ICk-#_0:?Y&IY*NQKLsX#\3$4$?"X$+,QeE]A=Fk_8XsoeJ0@8<Ph/
p-"OVT&lQ]AL(j`4$J@Eg4h=(ReSZ58l]AZl,OB#4q=ij1t>!(+Y5Fne2kK]AbM<?:bu4(WE(bC
00.!%f^1H)HFbJMg^tpo/H45ZpZS"l86I:,-Kf<;6>TsW(s8*p6^Cg75ZhIRI1moJnY(1/<6
``ET\_$Ef4FBcO#o%Q;#hI,.KsA9gEId,WjXsa@&.23JaAJ,F(=m,>T+o0`#">h;C!XE]AK)S
]Air%aACaeld:%2E#7gD@mJ5("V%eTg,u-nflII;jUNGYQP3!YrmrRu'74i>2(`+.gIAll'CR
WV:`@2A;!;pNE0R\m3W>^W<0qaJm]Ai8[N0P+NS_a(NN^F=t',q_ra>a5OZ?4qoYQ=qn"2UgF
jrhb9p,8FP8=jFnYUaQu+T'r2W8fc0O7&p;fM<bQK'm5u1DEsYlJQ'_MY>Zs8C\S#=?>';@j
NYR<-)%pH+2Y0Th;44Dn1Vl]Ahj(Y/YQaRN(eRu!KO6Y1MhR:SR./:)g3c(&e()!nM23[(l+C
r(gDgBAK,.p.`[#s?Kf<1;-kNRFHcIuR/Ihn4!V&)aGcZNF)17H3T">pI,T$(P=VYm!EL#Va
2cnJ(Qn[--O)/XmUmMrYbe^6O##X;:*pAa*]A4GIjiP"+t9lGVFQo+u14sOL1AA9Sn*8>Do78
`PqR[5"e1MZ]A,^UDohAX6e?%qt+4lMc:<#'XaO/Ma2jhFqA&^n.Ss!CXq$U)*'TQ%PY`M-pP
cEs_Y$*P0OIf6iac8LmpY,K]AI$$7u(l=>sS6>U"o<hmNump//Qp?]A/0KJbZj;r?qu$DZHgcM
p*IX1G<`=52QkWmkK_BfP-7q_@Aqi^Rgolb,,ti3A(q@<;maLQC6+fL!sCUOVN1/A7gkFmud
j=jZs?F&F1Y,-pR-Ha@(2DqU,=[l)KA_)lmHY,$VcoODO400;7XT,Y;ckkbhDs#FJ4=d/+X5
R,?gAM>OWgU?pe,+`8(drrqDtqe9H<(f+)cGEPFU\YL+k_BXGtphZnBAmellr[-\$r_0M+lp
f5"D2(I>s0pQ^r3\uh2gJ9>ZjBG[G"C0u+-ZJ?^oXAc.)?-Q97Du0G!9?rYe#&iOZNN47efO
WW$>YLe!o%U&kDG#P]A(dDMq,CdpkTTIFRetHS<D.S8]A4YCE]A*Vb3$U?1-S*L<'eg;`oE/n/:
?H$(8!0R?:O/-@j)cHFk-WD5-\?.rJ0LMtkI=19ha:U2qt\$_RXkhR&iakc\02H:/YPkVS]Aj
Kf"W_s44#e,!&<q%7J`RN5g@bIp4iQgXSeG1n8BO`:Jr=tbZkl)]Ap/5H^d#`;44gX8GIkFD)
hces,!D3=hDuJB#D(o!#]A0GDf:F]Ad4LKq'0[E795g4+B;9B6XN`Skg+rGBj@Tu0p9.;\DGk/
OI7@'WmNft^"qCLR3=blC>P_=_QjT_%:NADmm3(J1KBqM%2:$q7ggP!%@p)5D.$Y?d<o:X9U
fIIAeFLUFL16J"khrljn1@18MViTST&;$!%k/J/Ji2o?sk*:f4:rL`ulWDO;-`LCl92Oh$&H
XM1rSkm)5\;V79R_gD4=nikb\9!3\7)O-n.4QH:^"PeDQ1PqTq/RV):;!FV\p4+ho^GoY92m
*_RA>Qr>"n^.^g.F[A3EPkB]AUqV80m=l'RWt^dXruSb\4eX1J'ke+G#B&fnsRjmarp@O<rcZ
^%Z#pWp^SRpZ;bqagJ"#HNd1k[iNNEf]Ac_,lg?/udPo0PC(8:\_qeF^,^6EfIb*\A(Xbn@&P
6Wf3t'5MB##rraj]A?G%9Tlr6l"k>R`F<Kb>\-Q^%Rl10]Ar&M_FS<Pj!9`VkHRO1Z0p-0KXU:
O"]A4N$c:N"L8W^I\Y4,:?UT&2`o>u['FNgaEVZ:=caS6?r&=i;,g;O&ATbs+57orHn!T,;0P
gC>C98B"?;8ZG;&@BEf['esL3!>f`8_`_`Vn)qlLN#".rkdP(bg5/[ZC*jXI@hl+RgVjQktT
lIGaB=so8c,4d[nDZB"o4S<*XKs8S]A8;:^;6[]A=!!_W&Z#s/m&Z&8<%bDpQ&>@\(9_D7=ian
ps>q-=nmJA3JY+k9KZW1`P3n,[GCiG=hE;:((![%F4Z)=D(:^^WEP.Tdm;]A6PjJgQUqAn0>Z
d9/Y<E,jinbt7dMk4/N!=3Nb7Xok52[#?.g*e`fQG=IL;)Hh-(#%/Hj1ApO*-Vadc1"r.i7^
?bK.8B`Bn[kYU)BgmO=_E4c$DGP">4BTc?On7>"*"m4Z\;FN(BE*PnIkK,+j0L`;l/VECV/Z
9CK;$iRJHW$9aE4+ur\$7i`kedr!>&UR`HR%26k[J,b>Yb5e`HC0:'/2_eg'5#D104X'S+i5
&JJe(<K?KpnrQd9&BR2O'i+VfdM28L9C<Q(S-`igfkhE=t+9%tgp[h6B;m&eGUs!#,ALip#U
CPn./"1?L*_WmpU[jN)35AdY\(@:A]A9l;&Yrrjq;'Y@N4:n8J\G^A%[k@W!+:0j%m,2@LI2d
gm8W(<`<`.7-o=8;tQfG`Jb\D/j(J(m?C,<"Y,WiI>F<Zf&kU7#U<>2<c`78`"BMh8e]A%W1Y
OGssu\SZblG:-A*m]A2PGWi1,q$%H-8!;-En'%-/F`h677_?nA+NcG3n'H"3,"?n7*B?:>r`6
4>t^7o`r='(NR8.a:EU*b2KU/[Z#]A!e_^0ea)b_XV>O1RPj:7Gjn`E+mIYca^$t,RLB\a[T-
[m2"G>F6C/*YJ.joCR"6r=/:'`//HTik1jF#"_oR$eeR-/[IIL\0RajB-_[lIaM6>sf</!e#
@H<>dRrON'QrnSH2TF3cI!f0"P\c21'@CP2Oe!M7<#>;4AlWp)i&OY]A)+d2[i%YG*F2o5)16
?iH(8:NVo/7V/7mO9"]A-O>u7_3I_/.JL9@q>=+,1g>LdhEOu5cY;`%HQm0#[aVC3TrX&]A./!
TSk1Erdgg//$q/c%#2W5UcN@8!n.4*3AO$VjkJLqDI]A/3<ZN'7+-uAn`<?<+3,moW`YEaoK$
7G`d60Je*l&-7N*)ApA_O>Yl.%mW=lPeF+MEjembUX?m1-=Sk^KB]A>o>OoCnecFsg-Xq>A,E
%3m?dRm`'R$F]AM5XRCk7]AhYP6LBJdU-bbZH4"$/^<6'K#+[2g0Z7+14UZpJBZR5LD*OB-YqX
>5_lKmsX:T-=a^2mHYFXef0s?5t39Rl7C(jdPScEeaUn_`@kHkoGMP=`f>Sg8"<ssrn4i`5n
==\ECeo.N>E86A5aH,0N['0S^j80p99U90fgjImf[.!FZ":%GC3"8Mqt-qMeg*0)236ej>0m
BLPV-kct?:&VY5+un4DL$Y2Vg;Q]AC<eQ9ho+2E7Dn<dW'e0Q#u8Vmu#gr$pU_k/D'pF!dUaC
]APOoD=eVl7%khB0UWb5SsUU5h7WT7?H+4bLMgW";8bUmCH4<!W]Add8csHT)Uq!k7,fcB9hU5
PV`#!f)5LRhcHW-6<UjJk[&,99DGQY9;Zb0l-bgQ&TTt(c_%iEl58ZL,k@EJ&=j3N*gBe#d9
^l>\N0o:M(Jkn6CRTW#^i%''lOTPJj,;\)_H!Ka$*@]At.Aj%OTHT3T+qkRV\0ji;_6n<ZH_5
KM(g&IMLb+,:?<6JApbg4:\L1qu4Mj!1./eR/;br.bY.jM?R?BQA@WO:8p]AQY9PZO_&MCj%\
F#Wf5-4-urQI"uG'UU1ue/NkOradNiJR@o6&\%9of?)c9:XJ:HKep<&O^Re"Ib_65,BNN]A+Z
Jt4gbl)4j%uuCR$U+-T%1*SfOE*X%d:cC>c*S'gWA\$1^E##.dR\&in]ASni0icVUCf*!HTnh
ge6qITg(%q?R1s*rtRh6^D0*#dR3!98T/t%Xhi__TnQAj#^:0q%4s4VYR4X$[?Ue`<fKX.e@
4N=C&$L<I]AWKR2Dk!p\U#>6"f+re':)sMue;MOae9kl=ar;8.!_C]A<I;etoXFKR($TZfT("Y
<^a+'9%"BfD5"[p:EeN&/%DR]AS0r<2&gWUKV/E>uT]Atk.%#t#$bLCEb[Q:)7.9Q=E>1J2Xr_
:DWs_7.^<q0acT1'.^DorU`.)"R7EAt9T]AdnOU9n]A:T`2L^qqjg:e#-]AV[o]A;>&)\+ABkNWA
'^n-G'r@kBYu(1JDPbqp7P0Z4m;]A!?rqiWF`ltHnL8Qq$]A$b3!TAIH;QLq=%bCh34S,[!QLV
cgCBg`]AkL8-5>\Xt3T$L!6Eh5bjH@3;^O&Ppjb]AEAb.Wsic]Ap$:$6Y5j!4W7A(1mp.!lUMAp
cbrkZ]ABCHp:Q9h="?f.<a@E]A5f3J6(88Y(lobE:gRWR=s/"*OIT;WiRNE=,gf>@ZIbI@r:9B
ppP_opcKgrFIHrkhO9-CF//Np`XQ\%s_!fm)WAM6^`)l=RdOFKKl(_(RIF)Lp%n>c:o[Isaj
nAp.n+^@Dr>D\?6kq5M^o,(n=!G14='M8?`lM4V1#8^@dM]AXX-n%f@`Ld"bC`BrQ@+A9fIto
%qc@:2bq\)XFjB4B<>U1>]A#AQnKe\q:WXK>"!V4Z&Ubbh2bh#f4V?j.-5`R[Y%8hc412)ABg
ZW99T,+0@`p9D-9sI=k`=Wmg:Nh5;(#\^lK10gfTgNDrK"W:J,N,d(PZQASO0sD(B5U4hJ]Ak
5q5"g9mcl-PDJB:\8V4T;4Ya0(-B!14$9k&%'@Kk/@gr5kUrIJ!kUdE9:fb6=ktNgc,CL[g[
#$e,smrfYlFX`3CE%AV8*W/A@t.7nK7TAdrj]AFhM#Z%0[UN*<3)5F\K%W3Bb#;Cjc,iWOn^9
7n;JM/6iP)*.Ks`A=#BQ6a*uaKd1h^hi@/'m202uDnS+E>]Aj5J]AFlO!<b=Gg2Cm0R0;1e3DO
ZdGrc=/:)gGmmS97=15/K\h@j>3%oY)n-sU'*@+T'<X5*+'Q*81pVWLd^%%+?h%E1%M]AjceF
t:I_i$Y7n.mm1U.5U:SUsFhNqEX-&=<(AJ6EEKUEt4'X8DnC6k3l<8GlDm/9ncM"S&E]ALu"O
i$4Z?IHjqR3=VaaF1a']Am;Neb"%j&0,n0)r`(G5_B)E+KrH_DPHB]A!ZLB[&0^&7RS0='HX@D
CjQOM%XkL?[?u:IHPk>uG"?(;<*U/T7$.N)>0dLRJOg(H\`N"6[^or!%FQnn+k&?uHZmnImf
c>VI;kmk`*2QSXej\Z[f)%$/.4(LF=*nc[g0WnYTW8s&>M@aZejnl;KjRIRqg]Aa#)<GYIUnO
tmcoPR<,%3RdT0'Pe3<jC?rC:#1$c:GjNOUuLE-Sb@Nh4l]AVG8/t,%Z]AUBi[<]Ag9;RAedka&
+A*`-5>,R*XaQu:YMj)oRH?\Z\?iZF["m[Q'4C@c0rFE=6s*N*b<E#s==I1'Jin(F&oPI(d)
[$.\+KpUuoT3pVs*GFH':#UL"NS.G'9pqQK/VG&%kr2LOF]A7*G^"_6"Kmf&\o%G*nA/j3o]AR
?tML'*hZWe)rJp&='B7$eDleaV]A+ZJK!$53EHcPQ(^~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[SU,WF<,:7g,3<Vj>EUT*&oFIBMBYO%K2ee>3\f><b$e8:jKQ_)UQgX,K8N9`,UHW]A;GoN/&7
[!*E<]Ao(F'/1KB%6G?T!rA$ht-_tq5h!lqnB61H[W*dFksCdoJTo(AVE,O&-OFdDd'mXJtt.
!:I]A4?IV5:^IK]Al=*O/qcf&?Ds_7&:F/rgMaqg@+c1Se0Fg%3Ac#`@Ui=Pm%:1j,q5Ng,Lb_
rh3sf#RIH.J,c@Hb7)AiT9d3mHNA@%pdkr4N$!GDJl[X[V;P1oZ6^@6ge:2X$5<A[?NT5Iup
q[nTo03p#,*?VHH:4$4tVnfQ]A8p8)eYWX-m?\E7q/^M>:+!WHX\olO0=@bM4Vn.C\_]A/>_-S
C)h'bONG=_8U4aJ+&*-LT,O;H\5)asGfZ9*eWlX=I;"=EJjnpL%ASRM)0ts$(_0GXGE]AR8^@
.5N\VoGW-jqgG+Ie*i"@%9k7R)$P=,2VWjEXN[1gJMni4GP9qlVBh?"lt/Q+^*[j&7H&>#fW
?)`7o4_^Hhk(i;Va&H!b)C^M]As6'e6ZQo"r"o9G#.n[i;"W$;5gB>RNd5<_t+LsTKUAt9#ip
g>Cn-:--rZu\a.;<7+uk/:8f"JULMIP81J)[`2t96n(0W^YBU&=cNAgj??bFd6bFH@-q(`$$
&`c"T+`lbodJ=1FJc9toL+^L1hoGmi68XHV2pbf-8h+K+U)fPKO,>I:B3I*UD;YIE_=c;&/0
-6k_8k5@F(b&qKDeFf=?Q@b"Tbsru6^tNHU<*"89A=m2,9S<4\Qfei2\2/?s@Vl>OG@HHNcd
2E2eOlGhK*1:QFkF%TP):=mEYWX2\W:<FSa<<3_G>,E8]AX-$lIak#M@&R)"e?NjDq3>Fj-G-
cg9N),4".$lmF'NEiR^VO1pdAu]ARQ29#P?od)+IWk,aNUEMhTU"R8E\.V/7"Da`:"sKdLe'R
k,O?2t.LcefsZCq'PF?Q9gh+T;[b59&J3`8k6\&<Ltl-i(f(!CR:jV3uu]ARefe[tC2l,5!dN
/?WD0etkF"oHk$jcT.!]A?5)A?8-IUH/tNEfQ"XF_2%;3,t@e!UjX6&MhJ;tfNK2p5D`21W)3
5d*NK'(u0674b35h;RBX.MNq6?+;n6B)E,%<[%+C!eeLDg[:MsbVgNt#TPfFcFhOha:A`GS:
r)O5%T1j.b++k,:jEfb9/E-Nd1BW1.(;BBt28'p%/!5RLliDZ'kM:4(tE'G%2_>S54Rr.rR)
.a;24T6l(d'MWhTN3=lm3lQ[F"*di,6'6542)JsBWVY8P%IMF_a<**I^YD8H2OJ,[9CA<]A^f
V0?6j8KVL\8`?e!B*DW=(Y@.6/em;PG)4+@'gSjV8I)q7!IW6Xd+$@r;(5<F,+p->.Zm"l,T
^Em/KCu5!j<.UF?18N%s#9$7-R_bW_N=mYaSN)$]A;ZJCS8[d)34C)lPA.oV0krI&cqN7Vhpj
c`TjMVF%<<OETod-ZNC6d=sF9d4YZ%+Nmt6\kn4s2X2RY;?hhP$rjUl<@t>TR\V9VIfJZ]A0s
4mI@T4H,K>$I+l&4r^Qr\!9T,^[eA8$ma)q1_9Orr:!<D8:JJO1#.aRgkb<A7UjR'_T3ll:2
(o#8-[c`eif.5C:NRU6OcWQY@u(HW's6*6jW"Q2"o[(GDnTSBVeE%d+NY&$)3CsRf8rOKu_c
XmUngCMiej0?a2A<Y_Lf(!MfD^,tQhAD>Zq`&#;?YJs(Lq\t^<">:64DHs,GIaqX^^"<Io:)
(JYt2gH+SY4+$d]AYp7T5rCeaE8cMt)lD?]AQ^&KIsj3I=Ks4R<^&d<[ZluK>01(L&&985PKcu
ei;b<0X@-l@[-CX%eCaG"/Vl6r::Q1pTl!`Z`*K&AXUb)<H+;B32?X8O=2s\!#%O9>S&fep0
`c?J02E9]AN)W*&a]AaV47!hNYSB)IP*DT_(o@OJGBYq'GglVN]AkV]AF%SPD)`*/S_r!==/Ed56
q*!RGAI`qEMM5,fi-5N#iDtIVLaK"TX"c8H0r=`>P+bXPpd#(!Rie2T`7q<DDpk1M?:2q,Od
5okTUGc[:Y]A:LJZ)N/^aH0kilJ3_b$7F7n,B`t[`eR(VUokkcU4LseP/6dZg86Ami72*C#RS
fQi(XI4H)J9!1Bm^Q>1/c]A48Zl5_%8#@4`\d@`j<-.s892YRO\?J"oIkoH=U84hq'^B1X_Q"
-=;'/Xc<HB^AR15V?mcoa3epXq6_M-7tGH.TqYYUL(ae(!T-N<Os@;eCu-8!4gai$k0<.i#l
?mFV'Y,ASDU_S7R7i:2,$X&OQW]AjEVS0`V[p+I3ORR$LIgk:oS\PPU1:]A?)FC2-P+(lC*_d?
Re@]Au8*g>o^XXt&VDpu[9Vdn%aaBEFP_?5W!(=-aSNs?_nQPYb8ppYCnMsmqg%n^L\O"9]Ah%
TQirEqP/,"J&?(o@jI)FTVBVUf%[8i'%+.JXU81!cP/D%epJC<dcA;c$$W(=6RQ5kjrI=Jf'
6c_@#%@SMYQTpj>Xcp2EPLHi<<&qB9:$BN2`$&Y=g/XuqcEhg^s[G6JMmm6ABBU,3R5E)DdW
s*7H-#V&3Hd2<cu/hCr%ACk==g,U)mU/DZE]ACm-\U=U&J!mb?uol5DIC&pWmHOSCVQX=Tj&f
'QggFGI.ZiK(aDNSeK:c?<$]AO"5^kqtN&6:M%Q\2SGCBtM+meV(6Fg-&>1>4%/JE0DLP6uu9
>3QNrDq*C7_BC,a.81&^Im;C=mDdJCmD5M\D4*E96^Z?d862!?Go$1Ul"SSkS"#n$'@F,`[$
f%KT"Nt$j"gZ8QHMeM/F#9;S=37bkj`tOGh?PEfkL)\[W%NX3E:l$1N\]A6K6Pp]A<MOVAaTc3
q#Xk89?Efe'f)Eh"/:3@g7->p43)NSRpBT`I)m\5HI#BL9D"f$.N#G9Mnc=="g#Z(YKX#!8,
L`'c6c"ONR\2C6qNtogg#RU3K5C*=O(>VrlZt?a#6n*CUT50)$E)1"a>6]A@\$=rr,`(P@=*`
s'b?Af1Z<X.f6+c0A&2fs6I0jD#Ajp.oloEnH6K7[>@Oakqr6<9=""dODkqLWNPZc4@@";#M
u(**.EGpt[.")50OW3:p4H0sI?PK@Y@huiPEc^j_MgdDmYk>q_V#kl#@]Ad;<5/?mdB,gF<Nk
UoV^@s0)PJ3hj5,7F-908oV)1I4rMcXb[J$%*[0."(j]A@><]A!7Tg.)]A<7t&-:u;)cID92:EC
daH'Pdf5?^hEiZ!/Ej>5YnKf*Gh_*h=\+JPa*S[2EX3k$cmFV*0p`[J#E^hlo'%1?@$J;V7[
q[%Qu%"_qn(]ArZ0*-Y+j0WH/ApRXhk$uHH1#$bXV?MNdH\Y(m5pl*h]AQC']AF\,$+YZ+8>>AY
KT!Hq,s7rb.#&3dV7`pRkKlpE#$YIU?$$cNG1;\5.l\QS!N86I<XFPm`7]A\L*XgARJk?3c=-
^K6-]A/jghKF1GJ^WORAhY,$t]A2\Yr:AV"W$N]AZNfA8ZLE!J`%B9'^?)"UX_XEON(B(5tMU^F
?!.4*:\Yi?dtii_BH+:`%o0(hu2`;EeMthJstEaKmYkVg=o1A/(2B=[9%W*8e$2^64u2a4.J
jHlUuHH(.$<1SVeqO=bRNMl1!-POD1G';NR_YN/g`^NdGUIAoqJV(VF7\Tq\n*T2@YK"<hJf
0"g_\3+0'6lUHHu7t[ED'o8U"cnd"cgl:]A^<rWKd55pF`Jt%bBJIAe4Ha*WKclUQnVG)O`a'
BZ3WDt4$%!0IsFn<1OY\/o\`Bp*f;:2_TI#GK,`nL49pCuh1f)[e@QSiAuZlEs5Mh2PA+9d#
\&P08&/LGN6<rFM`>_.ogB%UDL<<)gfGC5sPS.#SR".]AKfch8o&,Z:J3l1rf&!,MPS#J[^3B
G01#hUZ)O2G\bIEb(m5+uu#^J1P9+&H$:7F?*Jd;i4/b=#q)A;9/FFh@`i,mERXai\9`hVkk
OmZ#X\0>je@:B#[ce1_Z<eqgI#aOkUMl@R*$S%-h.8bs]A'I#2j\Xs0^MA$Cb]AUpmf]A[_N'=c
,eff3S4OZr_J%jXShb0#UmZKpnb=!N-Z,rP-Zl5%?E2?d\d:<4l&n.29;)Se`?XhfM55cFe%
+N0iX8EWU3J_c-B?ZcTDJTJ_@En,g$>U.+9r<+.0nDb!FWOXJA)ZcTQ)G6`41C`&LN*2']A5f
U(-"]AY^l6C'56?!S"uM.lT4@gupD4+WF*!q:,tlRPN\)U;l.8.DVV"K5Is1CP.BuZg/n#g&0
rq8A=J!^:C1./Zme^S12L'rZI6>_De-k7a^aA%oU"/r^Zk3t/@)H@AY44abc$#$*qXE5DPq7
Tl>FQn:eq__jV_,N]AkQ)*sQeV03`ofFAKX<*O-X53a_KOr;W]AL-2$+(%dT*1H,.Y/5;kO3ki
5ECVE;XiI^n>9r]ACik/o^,QR<Vl5-LcAttiR`q*jXs94J64'P6ru%g(R99:fnU76)WE>6W$/
oPOM\t?\K!b'876]A_=qK2_uF5jBupVrt56(SoKh0Y'5lIl9+_-j@ZKXM[ED5Nl_OSf@0Ml.n
?rP<pqK>T+*-\O>^k)6oYqHaZYAC!lrCr>-iqQk:M`8s=hm"NBm*SXG7d*'sUV%TkZ<t$4A'
MW1W#)DRFM4a?//k&bk9C%(0T_(8c4P#`n,kYePH#?$$]AjJ:&)_6@NRclF>R[0uQG?d8/MPX
sFdZH6q=*22">J+#!>sk;#H*!7ij;L6*Og7>6e^N97?['Cl^Lo,A[S^7W;BqpPTAk5:rI;g7
ia<IV9HUU1A3#)^8fqg]AMo_G=@4[cr+\0ZcY&?CTK!nosQhWEr@L)2gCsN;j$N/n:q]Ac?fSK
I7W;6u0t,]A3AF/^:os`ICk-#_0:?Y&IY*NQKLsX#\3$4$?"X$+,QeE]A=Fk_8XsoeJ0@8<Ph/
p-"OVT&lQ]AL(j`4$J@Eg4h=(ReSZ58l]AZl,OB#4q=ij1t>!(+Y5Fne2kK]AbM<?:bu4(WE(bC
00.!%f^1H)HFbJMg^tpo/H45ZpZS"l86I:,-Kf<;6>TsW(s8*p6^Cg75ZhIRI1moJnY(1/<6
``ET\_$Ef4FBcO#o%Q;#hI,.KsA9gEId,WjXsa@&.23JaAJ,F(=m,>T+o0`#">h;C!XE]AK)S
]Air%aACaeld:%2E#7gD@mJ5("V%eTg,u-nflII;jUNGYQP3!YrmrRu'74i>2(`+.gIAll'CR
WV:`@2A;!;pNE0R\m3W>^W<0qaJm]Ai8[N0P+NS_a(NN^F=t',q_ra>a5OZ?4qoYQ=qn"2UgF
jrhb9p,8FP8=jFnYUaQu+T'r2W8fc0O7&p;fM<bQK'm5u1DEsYlJQ'_MY>Zs8C\S#=?>';@j
NYR<-)%pH+2Y0Th;44Dn1Vl]Ahj(Y/YQaRN(eRu!KO6Y1MhR:SR./:)g3c(&e()!nM23[(l+C
r(gDgBAK,.p.`[#s?Kf<1;-kNRFHcIuR/Ihn4!V&)aGcZNF)17H3T">pI,T$(P=VYm!EL#Va
2cnJ(Qn[--O)/XmUmMrYbe^6O##X;:*pAa*]A4GIjiP"+t9lGVFQo+u14sOL1AA9Sn*8>Do78
`PqR[5"e1MZ]A,^UDohAX6e?%qt+4lMc:<#'XaO/Ma2jhFqA&^n.Ss!CXq$U)*'TQ%PY`M-pP
cEs_Y$*P0OIf6iac8LmpY,K]AI$$7u(l=>sS6>U"o<hmNump//Qp?]A/0KJbZj;r?qu$DZHgcM
p*IX1G<`=52QkWmkK_BfP-7q_@Aqi^Rgolb,,ti3A(q@<;maLQC6+fL!sCUOVN1/A7gkFmud
j=jZs?F&F1Y,-pR-Ha@(2DqU,=[l)KA_)lmHY,$VcoODO400;7XT,Y;ckkbhDs#FJ4=d/+X5
R,?gAM>OWgU?pe,+`8(drrqDtqe9H<(f+)cGEPFU\YL+k_BXGtphZnBAmellr[-\$r_0M+lp
f5"D2(I>s0pQ^r3\uh2gJ9>ZjBG[G"C0u+-ZJ?^oXAc.)?-Q97Du0G!9?rYe#&iOZNN47efO
WW$>YLe!o%U&kDG#P]A(dDMq,CdpkTTIFRetHS<D.S8]A4YCE]A*Vb3$U?1-S*L<'eg;`oE/n/:
?H$(8!0R?:O/-@j)cHFk-WD5-\?.rJ0LMtkI=19ha:U2qt\$_RXkhR&iakc\02H:/YPkVS]Aj
Kf"W_s44#e,!&<q%7J`RN5g@bIp4iQgXSeG1n8BO`:Jr=tbZkl)]Ap/5H^d#`;44gX8GIkFD)
hces,!D3=hDuJB#D(o!#]A0GDf:F]Ad4LKq'0[E795g4+B;9B6XN`Skg+rGBj@Tu0p9.;\DGk/
OI7@'WmNft^"qCLR3=blC>P_=_QjT_%:NADmm3(J1KBqM%2:$q7ggP!%@p)5D.$Y?d<o:X9U
fIIAeFLUFL16J"khrljn1@18MViTST&;$!%k/J/Ji2o?sk*:f4:rL`ulWDO;-`LCl92Oh$&H
XM1rSkm)5\;V79R_gD4=nikb\9!3\7)O-n.4QH:^"PeDQ1PqTq/RV):;!FV\p4+ho^GoY92m
*_RA>Qr>"n^.^g.F[A3EPkB]AUqV80m=l'RWt^dXruSb\4eX1J'ke+G#B&fnsRjmarp@O<rcZ
^%Z#pWp^SRpZ;bqagJ"#HNd1k[iNNEf]Ac_,lg?/udPo0PC(8:\_qeF^,^6EfIb*\A(Xbn@&P
6Wf3t'5MB##rraj]A?G%9Tlr6l"k>R`F<Kb>\-Q^%Rl10]Ar&M_FS<Pj!9`VkHRO1Z0p-0KXU:
O"]A4N$c:N"L8W^I\Y4,:?UT&2`o>u['FNgaEVZ:=caS6?r&=i;,g;O&ATbs+57orHn!T,;0P
gC>C98B"?;8ZG;&@BEf['esL3!>f`8_`_`Vn)qlLN#".rkdP(bg5/[ZC*jXI@hl+RgVjQktT
lIGaB=so8c,4d[nDZB"o4S<*XKs8S]A8;:^;6[]A=!!_W&Z#s/m&Z&8<%bDpQ&>@\(9_D7=ian
ps>q-=nmJA3JY+k9KZW1`P3n,[GCiG=hE;:((![%F4Z)=D(:^^WEP.Tdm;]A6PjJgQUqAn0>Z
d9/Y<E,jinbt7dMk4/N!=3Nb7Xok52[#?.g*e`fQG=IL;)Hh-(#%/Hj1ApO*-Vadc1"r.i7^
?bK.8B`Bn[kYU)BgmO=_E4c$DGP">4BTc?On7>"*"m4Z\;FN(BE*PnIkK,+j0L`;l/VECV/Z
9CK;$iRJHW$9aE4+ur\$7i`kedr!>&UR`HR%26k[J,b>Yb5e`HC0:'/2_eg'5#D104X'S+i5
&JJe(<K?KpnrQd9&BR2O'i+VfdM28L9C<Q(S-`igfkhE=t+9%tgp[h6B;m&eGUs!#,ALip#U
CPn./"1?L*_WmpU[jN)35AdY\(@:A]A9l;&Yrrjq;'Y@N4:n8J\G^A%[k@W!+:0j%m,2@LI2d
gm8W(<`<`.7-o=8;tQfG`Jb\D/j(J(m?C,<"Y,WiI>F<Zf&kU7#U<>2<c`78`"BMh8e]A%W1Y
OGssu\SZblG:-A*m]A2PGWi1,q$%H-8!;-En'%-/F`h677_?nA+NcG3n'H"3,"?n7*B?:>r`6
4>t^7o`r='(NR8.a:EU*b2KU/[Z#]A!e_^0ea)b_XV>O1RPj:7Gjn`E+mIYca^$t,RLB\a[T-
[m2"G>F6C/*YJ.joCR"6r=/:'`//HTik1jF#"_oR$eeR-/[IIL\0RajB-_[lIaM6>sf</!e#
@H<>dRrON'QrnSH2TF3cI!f0"P\c21'@CP2Oe!M7<#>;4AlWp)i&OY]A)+d2[i%YG*F2o5)16
?iH(8:NVo/7V/7mO9"]A-O>u7_3I_/.JL9@q>=+,1g>LdhEOu5cY;`%HQm0#[aVC3TrX&]A./!
TSk1Erdgg//$q/c%#2W5UcN@8!n.4*3AO$VjkJLqDI]A/3<ZN'7+-uAn`<?<+3,moW`YEaoK$
7G`d60Je*l&-7N*)ApA_O>Yl.%mW=lPeF+MEjembUX?m1-=Sk^KB]A>o>OoCnecFsg-Xq>A,E
%3m?dRm`'R$F]AM5XRCk7]AhYP6LBJdU-bbZH4"$/^<6'K#+[2g0Z7+14UZpJBZR5LD*OB-YqX
>5_lKmsX:T-=a^2mHYFXef0s?5t39Rl7C(jdPScEeaUn_`@kHkoGMP=`f>Sg8"<ssrn4i`5n
==\ECeo.N>E86A5aH,0N['0S^j80p99U90fgjImf[.!FZ":%GC3"8Mqt-qMeg*0)236ej>0m
BLPV-kct?:&VY5+un4DL$Y2Vg;Q]AC<eQ9ho+2E7Dn<dW'e0Q#u8Vmu#gr$pU_k/D'pF!dUaC
]APOoD=eVl7%khB0UWb5SsUU5h7WT7?H+4bLMgW";8bUmCH4<!W]Add8csHT)Uq!k7,fcB9hU5
PV`#!f)5LRhcHW-6<UjJk[&,99DGQY9;Zb0l-bgQ&TTt(c_%iEl58ZL,k@EJ&=j3N*gBe#d9
^l>\N0o:M(Jkn6CRTW#^i%''lOTPJj,;\)_H!Ka$*@]At.Aj%OTHT3T+qkRV\0ji;_6n<ZH_5
KM(g&IMLb+,:?<6JApbg4:\L1qu4Mj!1./eR/;br.bY.jM?R?BQA@WO:8p]AQY9PZO_&MCj%\
F#Wf5-4-urQI"uG'UU1ue/NkOradNiJR@o6&\%9of?)c9:XJ:HKep<&O^Re"Ib_65,BNN]A+Z
Jt4gbl)4j%uuCR$U+-T%1*SfOE*X%d:cC>c*S'gWA\$1^E##.dR\&in]ASni0icVUCf*!HTnh
ge6qITg(%q?R1s*rtRh6^D0*#dR3!98T/t%Xhi__TnQAj#^:0q%4s4VYR4X$[?Ue`<fKX.e@
4N=C&$L<I]AWKR2Dk!p\U#>6"f+re':)sMue;MOae9kl=ar;8.!_C]A<I;etoXFKR($TZfT("Y
<^a+'9%"BfD5"[p:EeN&/%DR]AS0r<2&gWUKV/E>uT]Atk.%#t#$bLCEb[Q:)7.9Q=E>1J2Xr_
:DWs_7.^<q0acT1'.^DorU`.)"R7EAt9T]AdnOU9n]A:T`2L^qqjg:e#-]AV[o]A;>&)\+ABkNWA
'^n-G'r@kBYu(1JDPbqp7P0Z4m;]A!?rqiWF`ltHnL8Qq$]A$b3!TAIH;QLq=%bCh34S,[!QLV
cgCBg`]AkL8-5>\Xt3T$L!6Eh5bjH@3;^O&Ppjb]AEAb.Wsic]Ap$:$6Y5j!4W7A(1mp.!lUMAp
cbrkZ]ABCHp:Q9h="?f.<a@E]A5f3J6(88Y(lobE:gRWR=s/"*OIT;WiRNE=,gf>@ZIbI@r:9B
ppP_opcKgrFIHrkhO9-CF//Np`XQ\%s_!fm)WAM6^`)l=RdOFKKl(_(RIF)Lp%n>c:o[Isaj
nAp.n+^@Dr>D\?6kq5M^o,(n=!G14='M8?`lM4V1#8^@dM]AXX-n%f@`Ld"bC`BrQ@+A9fIto
%qc@:2bq\)XFjB4B<>U1>]A#AQnKe\q:WXK>"!V4Z&Ubbh2bh#f4V?j.-5`R[Y%8hc412)ABg
ZW99T,+0@`p9D-9sI=k`=Wmg:Nh5;(#\^lK10gfTgNDrK"W:J,N,d(PZQASO0sD(B5U4hJ]Ak
5q5"g9mcl-PDJB:\8V4T;4Ya0(-B!14$9k&%'@Kk/@gr5kUrIJ!kUdE9:fb6=ktNgc,CL[g[
#$e,smrfYlFX`3CE%AV8*W/A@t.7nK7TAdrj]AFhM#Z%0[UN*<3)5F\K%W3Bb#;Cjc,iWOn^9
7n;JM/6iP)*.Ks`A=#BQ6a*uaKd1h^hi@/'m202uDnS+E>]Aj5J]AFlO!<b=Gg2Cm0R0;1e3DO
ZdGrc=/:)gGmmS97=15/K\h@j>3%oY)n-sU'*@+T'<X5*+'Q*81pVWLd^%%+?h%E1%M]AjceF
t:I_i$Y7n.mm1U.5U:SUsFhNqEX-&=<(AJ6EEKUEt4'X8DnC6k3l<8GlDm/9ncM"S&E]ALu"O
i$4Z?IHjqR3=VaaF1a']Am;Neb"%j&0,n0)r`(G5_B)E+KrH_DPHB]A!ZLB[&0^&7RS0='HX@D
CjQOM%XkL?[?u:IHPk>uG"?(;<*U/T7$.N)>0dLRJOg(H\`N"6[^or!%FQnn+k&?uHZmnImf
c>VI;kmk`*2QSXej\Z[f)%$/.4(LF=*nc[g0WnYTW8s&>M@aZejnl;KjRIRqg]Aa#)<GYIUnO
tmcoPR<,%3RdT0'Pe3<jC?rC:#1$c:GjNOUuLE-Sb@Nh4l]AVG8/t,%Z]AUBi[<]Ag9;RAedka&
+A*`-5>,R*XaQu:YMj)oRH?\Z\?iZF["m[Q'4C@c0rFE=6s*N*b<E#s==I1'Jin(F&oPI(d)
[$.\+KpUuoT3pVs*GFH':#UL"NS.G'9pqQK/VG&%kr2LOF]A7*G^"_6"Kmf&\o%G*nA/j3o]AR
?tML'*hZWe)rJp&='B7$eDleaV]A+ZJK!$53EHcPQ(^~
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
<CellElementList>
<C c="0" r="0">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p6>M#:X6J)XM/N1UI-4NN1:m^QqMrG;;\"qT38]AJd8QCZCP2Y&,-7?)j%J[$Km^mV<o3(
EFa&r_*@\SNFl%C-3X_V7gX77**s.L(M68h<6E'$)1=TbhpVeoi+Hd5c:O6TWcrK:0nG3p1n
Jp\TEsj4sOochF8SHMmZ$,7(U>V&,d("bMD"&1$?2O0-J&o@UMB9aL=5n%JAk:]ALAqNHsmKi
?.3rfC3_/RB.pDVcteO^t'3GDV*MVTgnaBLb8Xs2Bd380@@2rk2+`%qqplBd.Ger;E7_EYhd
#Ma0R@s`(Q'7.sn5jR`9'$mC07]AK]AhP:BERSDgJJnq4,K54/ib8g*@6Q*A=mL_qq.su6JqB_
ht[$!7qg\78.nhtIhqhE[_Ygs'HtR[KRQC$=S0Z;;LJ9>Dt:_]AosfV#:G@7kD.<9XGBU$_,a
m;[Vdak]AHg?QNdFLFXpd@55&pLS#Z<+"`gq@e:s*H1J:3kTupQY'S*2E01'KqhFPZ]A)HCVT>
OV0$_,:V%#]Agbc6NB6'n2D[H#h1GsZpFcRU.18jKBUuB)`"NsTXjn*:'FA0%P+72lD$@5)N2
oO*DFd+1`U*j/g,k%GlfJ@k1H;\p[/X12m^@[2@iOgILY-*K'^g<lMgoc\t$fZ,gb[#R%<Q.
1Q&)=s4bd_ddm@3a9NC)aR7&Os5#$"HHM6j.3*m)MUIZM3BQHq;1:N",<@6^-@HnsPZLl=AI
P%<47/4:=,MNWdmT'4ZfqK;S,5t@6\d+YeC3P/-*Vd,aq4kX:[:X$br\]A6:<h9DAM[TY<[DK
^)[HK;)`@,+>:$+!!r&DLe*&So/8g=r!$FIn3DQn1u%h@(>UrhB(T15kC6/biq-7iu?@E"86
4NZLc@]Ae'PY&[[#B41OI"1UcP+HPi<tF<p6?itjm=M@\n_NE2T#CW,D4MMYD7.)W1bCiZ2kF
eGVrjt!URV8Jh@o4(S-&s4%adP7]A<bjJ\'2)Z$sBu]AoUb/JK::RTm2@Lam./Wc-@`o8dm'5l
Z@DIGmP>PX]AGmZCGj^FC4+.4/=oh0CGH:"QZ)C)7#M]A=:2%,Y'@%_^&AX<4VT!]A&Fk<l[qT1
>%,-f6.>2'\SuW.<AXEGX"<Km+%g.')YS00gb2VTrb0YkJ+6XcFKY.i:h`^@m8,AC%k]A8:hE
PV^gLu_Yi"MYt&[I,#XBD;>D-*\3;T_LllDeb&gP:h9XTQ%B;EP6N3(RAlB'/V@CT:F\B,KU
OoNAHNJmD*U;mA@JD]AW44Nhj2%.W:l&pW%ST-1)NEIT^)N94iJS#rt%N_<$!X/44#e^d1f1[
-8uHX#WD[EMr'7;;mjN;N#P(*[DRI@?VR@M<`2O4_!`q]Ad*\aTAlJc_IWZQ*J7UFLT'X/ef@
?fYiGIsc:RLhA^9hDC`I^b,;\-bi]A!S4,l+KiQnlRa/XOq_9QF)O<"8##-$<3U7G^Wf]A0%$=
R:\8(!V(og4BX"SV:TL6qf*CI+="Q6I["A$*i)]Aas$jSVQ`9kI*t1OI2G'sM*aE#uCHZP'7H
mT-GFIMR1\38IP37%L01`D5nqt%M0pTu`BkX]Abf_<blrhM_Jcf:dkiCn*hG9j&:PjC=uqB16
bA?_Wm8hCB57ks0$F6n[T4:@T+eO*bWLY+]Ae3WB2/j=9I1kXf$,J"f(k>^)Pe*k([i"U\1S(
:%_YK4*b%@'WouokC7R.Nf?!#uE#iM>ll7cs'idAGIGR(+Pb:]A=IsjLY'm\`9OK2'=a8VC!C
_.V!eaGp:H1mlKtHi>bR-9n^qnslC1'6>c<nF\I\p$):++4Cd$:k!r5S@+Y^aP6i#J&H^K0I
o15Nq2WBbfH^GJ.f@G80jW?6d>koVS*>o5aXT8cF]Ahc_s;V[3\R'Wi;(lCG0M!JFklFS8\F=
)>I&T8Wlg/bE)QPTXANFrb0ZTB;n=0Mu-JK^_I+-FHm\]A80H/3O*pO9*?B.S81r'SMRS4cnc
0gDj*k9PN/FO-3#^<7pK=#S"4Q?a.3jJ17q_NS#iekkD)N)F<k*S,]A;I!Q3D1o+YeT3Z'MqP
si%A-HME&(rrt@\k),R5]Af)eQBK61gT<0aWH#@^(!f(;+\$*pX?>5n<Q=MZ,JiCMD`-<d'_P
Emq301?kILp&ZQh6?q=8;j#`cHO.kREae!>8"LS"b6$3kM%E%NW/>iXlS[_,Z,<Z"SUL-?RV
i+a^GL9@XGN0.Xrg8//o;sVaRQt-I7;<UPSOf/.oTG:#f+O@L;h%#@<WuMNd72nBE6+j<,hT
TrMl^0^u'iL2]A!_m^H6B>r<'H/k=gB,PVoihq1B#uk8g_A")XL#akofHKk_FTHkLtIXgf:b>
<%u&+@$g"U/MWN[b$q[=s#EJ$YKtC8(5O<P%]AG%XI1/q8iU>g*M,@a,aX4OsJNp7[fK@u`<_
`?Q1i\9K2lq%eCoFm1[hrRi7+CgtQZ!L$K@OWGmF0O]AU56S$EPngetJ;gq2idiF*)cd05af6
4<D.q_$C6E&0o4@U1ARj?C\Y8]A.kjdC8<t%G'n[#G8A<5_Pp+pKR@0Wa6On!Dq?$^/L3tKXi
Wg]As>fKMgCR9I4tTY8R>.t@emM04Qm(tH-$%SQ;9Md]AEA1%:7.(g*[K6<f1&K!A(Sl@X7cFJ
5jR(^4Q1_I[H.1tM?[6TQZ%a#L)^*_0Yn]A<+j<k("s6E$nt3\D;!,kk8dgF3Pf9C//h$_o(t
C.9%=?U2[d@=tD$d1E?i0bieJiRhoK9]A18iEb@S&45#*r#!a@ZXlO5G^<###]A%KS@A?G3cH@
)sQf*9lC8X=9'K*6?[C2XqjIli;uf\ir>D:2R%&?"g]A4n`L@0LT%Y[JLj9&ZDh)^?ONC&$um
l\P%;Jg?8Y;dH5DmCiPG7gE+>&"kZjc:lQBCe1Hl4ZKWC7JcH+[T3NU$nfJLYneTJX#\d#93
dg.kqnL(FtoS<j/r(>I_bTR33@A#!bR8T.q^^Sb[,6"l0>RdgRUtupLZ,'Ug2/6P1?46K[KB
)G0ETjdGXftk\QVe53l]AuVSZ&F4'>2hX8!;B`;:k)(kmKi%7Gp2d*IeUiX"\AAOTP3cBftjJ
'oo_Z#Y9B.IatB&U\t=e#mfk`'E!4$.&Eqf&&EtmMrn3(1/85c7nYAHX.kC#Ih12a()Q.u0"
ABrR(/p,@R?(IS28=$g$hlJ':TNfPSA-8?JH=n,A.;_).d69Q$0u8P&M&^$Lamt`7Mn*P_s-
]A>3]A72K2\#<ZbAu;nXE:Ir?jh,^f-Cmn'b=!P`n&-s+;IOBA46%U,%2OG,#o_iHlk9I?_QIR
,K2/-a[hbX_JFj7cr]A!nb@'"rC4Jd(8nhs.6gU6hQIQgm2^d%*Z$:s+k4pJ?eZ<hS"cYuWT6
lH4(SnWT",r<.VDLjTPm?>B1IYb#is#N#-=[ADj6j6AFY<IF5R5`B:l#qATFMBg,&)46%!MD
kAVQ/W4kRJT=b5auX*ImuCMO_Op!;r>Vr'/F(7OO=:t4J.Kkl"E%;sY(oNMhT^eid_Ct^1h2
1BL0MPdmEpfi\k&rP:>#sFM@3lY;NS4A3%.]A\<G1$]Aeda5d]AmZn[cu$S,muI*N`J+CiObGd:
ua2`?_$5FF=?$B)a,eP+!\1:E7=d7tKDS#!%)62<9j.4>DWLV^i=AHi(#W,Bin+&soQI!'1p
Ts:18oXkG-7YiP!Mfd<P@qdknbt=n\#juAFH@'-IAc>d;\2VmrrfOc*,ES<M+PV))LrLT,Zm
<84-0VTOBB?iM,$+r1-ECMl\`3^*WQ94AW&=gJ3p&rI&LGP:"?)VkC\XCr$-!Sp%N.Sh+3)s
50t^OF6f1a'6^%!>Web'VmK3*2p^tNI?tXh?456`*T%;3'NToFh^Kfc%q-;lqVe]A`=L1)o(7
PH[^md"3!$-Us*AQ*Dj^=P4L*Jp+=hXLP1php5iam6"VB+`nm+n"%EE6lA7?jZP:=0JdP(Zn
*$;&reLdOQ>SS]A_/\ds-dAYptq<Y:`N3XI9DaG)/9bQ:5Q6pqA^'J;7^W*:tZcgjD+m^@2Wn
A-Sr''40`c?O$=Q5c#4EG.qs3kh<eco`l;Qf_`&D^HOdQpl-(I*HNZ,45UP&E:701Eo3JBh&
7luWG'dsk,&rQbED,;BEd^;[q!CU?>h\--7N:*p4@9p2!URJ^A]AVl:5H\'ES_W3p1M+oKJB!
X\_mD1GFRQ$Nl!26-\'7eAnW;Fq;"SEa#(JM\@SMC<K$+ORduer@^_Dend:*c2ITf#d3pZ`;
U:K/qVC/dFG%3+lgla3L\G%q:'jH,So^r9m*o43bOuR.ChsE1NT21363X3AX5L/#T8N5%<8H
;n:M#W:'to>k/.;qShcWo^4dW%6(\Jp<:TcL@NL;7V$poeA*Xmr)l.:^e5\d<XXD*Mg=ZGFI
T*BtLB<s<c'dX_^#t*[bG+r.?;H.Hl;jH@n?A*Z'W2g[lLs2q)kn<0p.=XEV1fhr^'V#knMB
U0Dk2r0Ah*p9u5Q$6c?COb&fSk@g1dNfB<hNqbd87+7(<B+&q;^8*[L7hk!-nS#Y9V]ATrDl^
41^#qJE$g?>V`TGkRKLW+"13*2PDX"@gH^qY=X)$m%&.lV01]AGp5W>KI1]A(SH0?C.b#h0sJ(
on!t@G(*ARcMZ`6sUU;<RD<j#hQaR;0:=GHj-DGJITbaRuMRW*KXsfmX=(4P&(@7f96?4ZTa
l&`:?/Lc)hN#(lOlC^h=<1:CsWE#pI/jmbJ23\8Z;;)Jo3VWCh&R=+G\o8o5*"\>jNj-4h5G
'SRU7\Y'Z!bm1N^lbZSI0k&1ijP4;=4V/OAm[-?fs7@[@YbK5Qkom/j6)u%7:d%I[X_J?sEu
;4A1KcROd;h6n6Cbe!dDU<l6)ttp"(fJ#.a$=ZF!+h=at0p&?dlZ<7?LR5(Ir3L**@<BUEss
/;8260/6prYY=D0=rX@$D82AfoPu:14'o4!q-J:gBP\Qa-JoPtZE"f[`)*D$=2BOGFi\2?Fm
ZC]AF3!sXuO4KOFc3`M#mf8Enj>8X@dKb?a74I-ifPPU2B[o?]AQ(Cf9\2H.66He%I\&]AA@RcX
Qg2=V;NQWgnG0[)+8nEZnep-%=2ZU;e5#6~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="7" y="5" width="348" height="254"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report2"/>
<Widget widgetName="chart1"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1536" absoluteResolutionScaleH="864"/>
</InnerWidget>
<BoundsAttr x="300" y="273" width="358" height="267"/>
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
<Chart name="默认" chartClass="com.fr.plugin.chart.catalog.CatalogMainTypeChart">
<SubChart class="com.fr.plugin.chart.catalog.CatalogChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[月销量前四]]></Name>
</TableData>
<AbstractDataConfig class="com.fr.plugin.chart.catalog.CatalogDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr themeName="" nodeNames="地级市" nodeNames_customName="" nodeContents="月营业" nodeContents_customName="" customName="false"/>
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
<attr intervaltime="4.0"/>
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
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
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
<Chart name="默认" chartClass="com.fr.plugin.chart.catalog.CatalogMainTypeChart">
<SubChart class="com.fr.plugin.chart.catalog.CatalogChart" pluginID="com.fr.plugin.bigScreen.v10">
<attr refreshEnabled="false" refreshTime="10.0"/>
<DataSet class="com.fr.extended.chart.ExtendedReportDataSet">
<AbstractDataConfig class="com.fr.plugin.chart.catalog.CatalogDataConfig" pluginID="com.fr.plugin.bigScreen.v10">
<attr themeName="12" nodeNames="12" nodeNames_customName="" nodeContents="12" nodeContents_customName="" customName="false"/>
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
<attr show="true" fixed="true" follow="false" textStyleAuto="false"/>
<FRFont name="Adelle Basic" style="0" size="72" foreground="-1"/>
<itemList/>
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
<attr intervaltime="3.0"/>
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
</body>
</InnerWidget>
<BoundsAttr x="300" y="63" width="358" height="200"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WAbsoluteLayout">
<WidgetName name="absolute0_c_c_c"/>
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
<WidgetName name="chart3"/>
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
<WidgetName name="chart3"/>
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
<![CDATA[]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="128" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="false" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="true" x="40.0" y="40.0" limitSize="false" maxHeight="15.0"/>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name=".SF NS Text" style="1" size="160" foreground="-16711681"/>
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
<HtmlLabel customText="function(){ return this.value;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<FRFont name=".SF NS Text" style="0" size="112" foreground="-16711681"/>
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
<AFStyle colorStyle="2"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="true"/>
<ColorList>
<OColor colvalue="-16427648"/>
<OColor colvalue="-16711681"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="sameArc" startAngle="0.0" endAngle="360.0" innerRadius="80.0" supportRotation="false"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="分类" valueName="数量" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[明细表]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
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
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart3"/>
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
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
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
<ChartDefinition>
<OneValueCDDefinition seriesName="营业额" valueName="营业额" function="com.fr.data.util.function.NoneFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
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
</body>
</InnerWidget>
<BoundsAttr x="17" y="48" width="254" height="181"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0_c_c_c_c_c"/>
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
<WidgetName name="report0_c_c_c_c_c"/>
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
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[D0gIM<;ZRmR5nac#%Pi5@Lstk-A>:A80k/X#n]AWu8qnn=2D]AC@9,fA([,#!/&k+">#$htm+t
>F38@EWH0>DSjf(XX]AkP+fDSF_1ocg,j>cguHID^1+Ib=5B78K7^18_`W5NW=S5!<<d-^a!b
M.NaWQJ5o1L<`Gu\\KfoB3@bU$EJ$/deXF(d9VGW:CP5oX>Wb@JD%H?d<N\/-R61pU0l4-17
R%R#b"be]A,]AToU`jq)%I#SJ?_%i<&;:ZLa1*P:*HM&%R;,[,67K;6iIkR7Mr]A2l8<"`+O)ms
qXAu)EH6;5=QWh'Ln06raO!O>Kd0;Vq'J1j@erVZE:lGIK7e;.3a^n>PLpI_L/goEB.WqeZ&
*(Ohqm!R"8V5BS51#pB"[oI\2din[_m&9#/8g!G,/_i&7_DNKTV%tmO\O,`BhYcGXE]A`gUf2
KP/HOa--D%tX9[$[Gc:Me&)Dc\3iK#gn\MS%YCd_\D[8eM>aE7%N5$[?`fs%hfMJ._Z:r<4J
B$(d$!/]A'Cjc;Vbhmi.8/c5hkPQN_q%pN@#g-nC-(^6&oD!e^8n#?^D*,MRh&AVf59jTg1[l
:<V9/)-E@Th,:+FqTdX.F.Am]ACq?+D4%Tg;m@FnEL1^tP+91!L3j'h$N:IIZg$`%EtFFY@81
\<jHdlur\UK:@9NakT$F#)J\frNGFCPr$)GAMniLo*ngCTIY.s&#^X"hq)?hqbp?e5AC%R+m
B$,]A-;@FMA5N5"'>4g6dReGu_XOW!rqh=0TZ8gr-e^d9aBqJH@:)pX_W,$KKJ6d,HpdL:D,q
i%B1>oXa5-$@&;X8#0e]AYDp]AZC8CejV'(6`ql:?q1M[&a#6^35iQQ_VuhNVfkt1YA8B)GOj'
facU/DPTjui:m;l.$i.;:7bB=N:,8$^&0&ftrR)'(Lt&r?)osXsmgcG`Gkn`@[C&LRC-+t/X
aI&Z<u")^>De!X]AkjLUa:H:1g:$?#q'2A1SlU7>Ke(g8UofXBR_q2.OGp'W,W;/A1Ee#RQgR
]A;8X34mUOrY<jM/HWk_lN*EWWMU%(l9MLrJnM:JYDN8ZPGLX74q]AmUeN_'!?+m>B"[)q=bg?
h$D`$T+K3FKL0%-X\KqdO!V"&J-rKC>OG<t8KiSsbC1?j)0J>$SF0A95mLf,Xor`=1!J'#fE
E+Xgi,^:>EFl),o(#`3`-b2g]AQm('<VQlTOn@)C(#6;$T!Q10HR1c&=LbAD"Gm"HK%[7\=u>
u2!M3hN[LN!.5/55%YT-j7]A8;S.7&?7nM$m902(jslm2:fMi0^c\L^O>XW+pWg#9Z0S7aXhX
BJe9LDc*P<3inPB_t=I*<kj;)9MWUGH=="CsCHEb9*Y@c=??drGJa$(1)]Alf>oOJYT>'!:RH
=Ga.*$OUT-56#0ULFOa%l#\&PV<Anat`ZXB2:j:QG*hYaogoRcAVCnqWEIaG5[FsLPH!Su;r
AA\?=j"#;UlKAbGW!ttPpDm8;hh@G:#8>$olXgY>=T1]A=EPtftM\f&phk$c(DN%+Rn=&:3'S
\*k;J!)--ZDgqJolqtT_h4]A<NrH7\kkmC-I;2"lF'7\f,Zb;+E9sLS#&hnSR+F8p0>7H"#DI
n&F%mHC@`CM\?WDtGDR0P_'D<oo7n,GJ]ADF,"!'Ye\id.ZSG:LNG9:UMoP^2>/PY*c?((i2S
-ou>I\@AHbp'O>:4rP35bVgGPr(RaTieCk]A"2`J3p8R'W&gML\DD"fCnr#"(S0oG`2i5W!u$
43('B0>3d71tXg\*;2O)JQRlOJ?@EdT,_HQQhT!=rG[<n_bL@/2L]A-p7j0hk^]AYtH1i-\gut
niba-J!9'$eV+5R-qp#K]AY=QBVd%,*=_O$n^^"a<2HHND3=oc6DPCGeJ=+td&GGM9^/&q/da
,e!g>@B]AWDr9ne/qfZ0m[e6Ds#H-ICSNXOeJ3Ai+[`869lU<V';@N`I)&0jlq@0S'I;$^Jsk
[C?MG>ShBgOS0Q@o<0`h8n8G7n=@_$hLDbLJ=9@5o_)Q>O(#$sl_7Le2gLf(b=(g]Aq'SC2)"
"Zpc%9R(cI@,Z-h%S!)I+F9;_QO7SI0gN%c?&8A:s^MA`&>O-YOIe_Cn4@k$5=.Ig[]A`KL%s
<gCh`$NR_uZ-io5#[OXAcr]Aqhr%%!V#o4=a"sP<%$"(3qsgI%[4#Z[n,npgSm=%hTd/a>s>_
oS7*r3.P\3,'K*!13fT_d=]ABLhp5YY:,H0a`QHGhTbFtK]A[NeB*$n8f5a[bSB',Wrf-hC7gX
chTjEh_)#7u/if`#:07M8HRHQ[WIl7Q4YPXO71G@m&+:#DcpMc^bK!*c!c!gKe'7pNc&8Y=R
41bjt0hVIaNbIVohqS'`9\GF0n6D`+;[=i?X-Fo0aJk.(P5hob(g9$,<@*;cu;)2R<DVJF\U
8$\pn3,<ZX7=ETj]Akg$gCjeZKJJ$]A$_4U+e:?jc-Zh6'>*)G&"ITA2S$%#bJ2JTA)rA^p&sl
bZXYdNeRg'43k6_$R3:_d4/B:_G^X.E7eTl0CidH&T1qHsH-I(VnGfa@PmL:2^DA?P>SSOod
qFf>@98:r77tAM>j'l\P<>(JL-d9n/G3dCdh\/nGF"!j`^l/)u_?p:ndh903:$Ji@/3+.%EZ
3"UD%']An3"-9=Kg.k^2K;ta1Y)h4^j)X?DCA6"@@76@pj2VrhGu)?D"/OfOGPQ[F"G05EQks
E1N!;=l'$[d(F$)Ud%$KK]A4!\n"u_"AmK.Kn?f[nU\>2A$B@YQ@!2>k/\;r%Z)\0CAkT4B+-
13uHH/=\o='9Ou<!X*f'@`qs&OBD@5Ru+t#pUPh"+Cg&OXG]AKRV\!7U6hJf1duKX-K3Bo;qY
\n9t'gn3ibr<>t3O3+s@/G9e?!i[&;l.9hC_OLTC^Wdl0$*g5$s3ZGLhf4R2/=4R-;;a-04^
r4&#N#q)iIAf[$<5Erp[O@#LHASaat$NT8ZeF>t,c?j&]Afd.GGE*tc4lYA8Q>;s8%ijXep>%
kbQr3`gm=Kk]Aa#"0UTWb7dZ#oEY=@RA/c1eLMB!_;-@BJ_N3;DE7n?trt=@[3@<cQAGu+iBT
ChAm:4]At9B`f\P*:Is!g@4e/d&>>3Md]Ab?.$Oj=o<Sc&mLH9Ll;EXhs-\`W^l(Gd/iIehB.J
U#hWgrr\tipoS:RiTFW.1eJJO7"leF+9k[[8D$U(A1[e;6s-`bR_XKj"j;8b9*):Fo)@gkW@
)bGe%op%&UH)^73EFIK]A^gg_+/)nJ$7;kcDJi;eWW7jD@tU6+\>&Du:g_!5s(2MTqUNgm`eg
g0uh,j*,SGar*eU)?goJKf:XE=%4S=aFd+`g@Hj.8]A9!O=JU;mb;nFFgWJG#!fa_BJH]A^n)p
aU(MYi^hp8qYnhp\un_JhK)Z1.HESL]AII+cM>"AHGL7jiogd^nnC:?,;6.>WM^LD2)^kO]AfM
'8uRdoO)(<);VmUjnK^g*@V]ANXVrFLnJrrnfStmVqC+sPCdO/_m%6iFqoQjm)rS')8&W)Uj]A
OF#!kpR1;MZ#$>X5&R\5r*fQZG8*+^7hHHgN"B2X>>,:c5\QN-uC+30^169/9Ps-Lhg7,gpu
7IKbYCA2fi:fUG-(?@\G)lUU&gP(o.)S\_XYc!7ULM',?i-@g`oV*C!,2l5426gQodX:V3uq
V4:p=V)QrK4D`8"<#28`^!H'M!)@-;O<>Fq2SmfdXGnL$*shbHZ+aq:jHE7=,N&;4:ZmBY_%
[o@'ZL6ainc$l,]A@>gUs'h^QSiLP#6+(P^3EVFM99^B2;,DZ?SXB=?>?MB5#%[pdb#$a&t!a
dU\ZSfZ">^b`iW@J=WB#Fs4I1<nqe$lT=@:$GO!t83`f/qTiWDY9tbjGLbk".'C-mVH1<ThC
q-Md,Y2Q\P53pB-7@O=G+8>]A;!;3e4n_t).<.RFNit;R.Ha=n^%,2:fTG*HS4s)?GCm50]A^+
[qk?Th9K]A1QLUqFZJqobUj,L6QQ;"GB2G;XMcp[Y]A>=f_enj6epi+\@u:F#3o*G.#NPKAp9+
1Vl.V2'i?V'K4ok?'2t]AoQ0Og9Cc19!s$W\gr;)oS@?CC+;6B1I'OO&ji*.QT$,CJ^P<&ACL
eW"h*$om680,dW0mn.aL@gW=1[>8%UuI:C:nA`GjS!;Umh"RC6.DRr*\+G1"_u`27SS#!%$L
,64O48brV1baBo8(<,h:en]At.V]AK@C4#0^%AD/paC!s@Jj=7!0TUFbq.ReYbal!=B&oO\^"&
]AZ6.o/"pLph<$_!5M2M<77Hppplh.&##okgcNO\r^0^@?!o&gNi=2rVX6o[@^F#q9hHI[_"[
<\L>1+0CUTQiEOq*CDlpG]A!J%VpG"U3/84F@0"T+P5?q^GaiVT1S`kn(-M.QCYGlS#a'P-OI
q)nIdQnJ.`$P(gbZ/UfSa-l3e07"fU/[3&GKluTg,Bj1%5p.6s%n$WJbt-H\ei5E,M_T<QPq
)6A&4KJS[mGMQ$gek6TYSV^0ft+l]A3>c8ZVG(//l6"lp$`JL-k,:b'LBsNM+&?p+PR.K3O*V
]A>NoHp\^8H[O%POMOT"[/6r`!TWfQt#=_o<`MX\,VW7kArN?VQ>20VW%]Aspl[]A`ML(XJ?++N
NRlG-+e"MNt[Z/q3BsWOnVLl.)n(:7'!n?g_9Ru9dtI^2V4-U$h^P4)Ym/[nCG6/f$.dFG89
3N9!2O!:cS@&'.Y8?b+RECA5kqjB:_9K6[nWpMRm-'@10en=L$3]AO%t<-h;=cJ>$#<48m/,d
+,r-6I[ElJO_#Q97gX^4.4Ko+G.;bR7G)^u`L3n]A.HiD)QT<Fd6V4L+f_0^-BDsf_O/>CBjB
5_^FsYtFKZB>`5bo17UjZMLI$GG&]AUI;_@LdD-g170u.K$Y=WYS7<Jb*"Mp$fA0MEm&!(AF;
J('/Ps-e"Mti_sZP5C7dsE!At11ho&c9jVMcql)g7,1Y9'XdbMV+)M?o<[&r[G("K'A5bjKE
m"d<;o9K&[S8,CVjm#rC*4Ml>sIEQ2n`WdKPhHeKt;^EWOC4*j6s=C(TlLN[79"R$uF_2#Yi
&P[VImY<f?"kB]A<B9im1BfSdEXD.t@l;CgIa&Ca_eR*]A,tFiO5S-I3-NFXgEoi%lQ;@X3=".
I#,%$SP&d1<q4/'ebEBAqas]AJpft?jrk,P8fn5ElQIlF;$<Zr4CPre]A\qVgKSpS2>_8I_^fQ
_POJ*SjF$?oWV&rZd?0U0NHGbq=A\j?M6.mJEe,9Mo!^Khq2cRP(-d$O5ZM7*-gpP[Q03p6H
mQtQ<:()0RlXMZFjGOEqj1En6,B:HHe06%ImDnHi4htKPL7$p'`fS=pgZn7"f:.CF`pd>XRT
9=FN#=^'-'2if+R^dG=]AhPQsf"W_c5.m+Q&4AsN%/9S55?=[=UNtZ<+`&)G*C=cGG4ZVKHYh
4G;gdoM'5tIKd,gc6eI]AL93@C%nCJP`d5nF$1#\SbNi"$m<Tst#>=8p.@;',Hu=t7_(2ZcDs
h_,5W#n`'mh0t-'&_F!/@g3iK[mVtmq8W=\^'m\+)A#rE;]A1h[0QaK`$WWKK(^d$HfW]A+IN>
C)T-KJWe.(;Y!$F&@X'(RN-rAS$lr,6[17M6V+c=s5=a,I*4lJRR%`DJd<'hMhf-*`o&I[\m
f'3%-BaG!H8VD99W7P<_.$=Q:ikEa-qlgS%Q5h2[l@.nZ.q]AfReOP,Uu`rQsp8!*WP.1(kc9
dr3mG1>1/^BML8(R:CbiF8Q.qF^_T=*jPdFB7[_8!&ei=G5UFG!(Ue3^.VSOEW2Z=@2+Ks%/
(\fI'qpEem'-\3E$!aOjr,RL*o"kcR]A!-O?8%cin\.g_&Mb[4]ADW$_k[?AAC2C,_FKo0;-,_
"dG]AKjtEH^VLL'5&r^OXi/Kj)i.g5e7\6)Q<T^B:M-E^V$VZ:QGJ?Qnm[8M,#]AgG0OgRF0jj
`=i=WbJQ+e#QZ2Zp^WciNH>%Zl@DIMj[q7U]AOToV.T'dKTV:ZSLHRc`K_PWRcR*r%eAb,k)^
eE/7O!F`tGX)QW6uN:KFc3dW\oLU-qAqU-M5dsV/mf8$;&EN&oCnol#.;]A<1Te61']AE=O*`&
c.E]AA(cqOC,YZr?<WcDoZN:SO4=sB/@4S"b.HiW4&A0]Arir^G3UkB<[lY_.05eBZ;B\_pec1
0hs&G5>CN\*l4N'@-)cD\SReWt]A<7]A]A>\)?u)-n6N*O"2fiBe_XgD$rSVhmTV3gtjm6<N'$t
djRk8'F#??oOR%AT4:1ch[Or:nj>iXqp2q@\.IiZko=t9HqYC;b?6W0AB94nj=;?k@qYt("W
IF".?Tl_adfY%C``D;;\efUf-JR*2L=RGrKHIAJeLF+"CZ>55f6c@.\g8F'@=Ous""LE1-U1
d=nH3:["Tbk]A&8%#`9_KOs,,"pqrW@fK<enNAX'ftBHd#9k6NO6?_f;U"C"f$Q%1E8ZE0%p2
Q^0F:\V`"oN6trZREMH36M#$:il#Uekb2Er;i>Pc3b5U"6J#@T0bbur/3]AY)R7k=SqXa>KNS
%!A`s)YiS5Q5TN50+C.RbjI!c;G2:.*U%7;SV);s*)/!LEX,<+]A<"r'gj\3+H7UDlr$+91Um
PQ,h)//pWqp6Y6Sk(=AAEs35)PE8_g"Wsqq#;$jk*uoTEVda6pd9NORN'K",pWUhXfEku"e)
>`f.h=Q<)7Q",bMJ3bNi90shd@l;W#(KDs!%2MW5o1\Id30%Mi2!N,Q0^EKYm1<XSQgS;KU;
!fg+QPnDSKMGJu+bqQg.c(]A#Re<r\MVeniat2;&P9hDSgl%Kg7u6ca8*i89/8AZ@.G3*cepW
":#N1B5nNc-3ePo4+P/-P6&)'`O$jR%"@<%TH_=dJP]A_=,^k!TJ;>cHKrgg.#1JmNlbK)9m%
//T@+IS!qL<sCr:l)IjS?#"^EM(]A'stRAJeVh9FR?UCi6Qr:p/D<rH^I::%ZhiLO2rb0h=dE
gkC!fCj'RSkursqOWuUAI1",J-0]AnM6\cu,+;Y4SpV7-$^m).ZFWEW^\a9knc/drOaO"R]A8R
G4mcWgJX''!Saca"kBUTV\^1DC&^12=Pe?Zh%S@8uc)rEhE@V=,0r+;DH\s,-eg%3d-iT1;-
%QS/arF#Ph]AD(WKBBV>-On,u5&4m>;rV#96'7^8aOq`i4$?aYi#(tV;fm1CE'c5`dhgrj3)d
to]A%nga/O^t$`p/B=d%;f=3r59=]Atr617l\m-K/N%hLbMG2t0]A!>_Z-)d:&\]A?<;4nhM&:<9
7*H&#EUL5dU:IqfV^aA_,uB!-cWW/m9H(b>nu+JY<G9+h22,JH?)nI*.S?$%a_X&.)/HAXoC
^J1]A3#sN8S:;?q8cB/9DH@AX07F6/W)<"?tY:*(WqC\M+YtRlm'&gM=<9XN1QS>oD1DJ92%m
U:2r#U+sA8"\M/OkA4'[fmP]AR^rpg=p3X\K:&YL*P16LZ`f6;'F8Y:':-c3SDX<CH_N/5)34
k;o%?H%`fc@a+PX-c"9fiqfAH+XhM>i-BteLDf523VDiP?7]A(?ieLZqlJHglb0L$PVWHB<n"
)okddlF`M%1(UE$@_LVA<JcUo-@RL0M<UC$7!J:>8cbR1*OL>GVB6Kr49Y<_pF>hL\""5_%+
rIKL^.PEgps(6/=+s+8CAM4gE@8F?Y**BcN#ODbZ3A`e.sQkGsHch7=.d@;Jl9E9?8N-<:R<
ht2'`YgdW)=o/:K;KNb$*T=X5]AE3*JB]AJUg9<Nu/+ITY$b.>`*G#l9dn6D:lrM(M6_7\WBU(
cN0JJ=0CT%-=,j3KSeaaQf$-*UC8)G=?0Tf7\eSX.@N4Ma`!o&7[L'VG#OX4DZURj-u5$`)F
Y2Ehk<M1apZ<`jl>0_`]A;_nEc8>.V"4?]A]AST(\*p:\+3]AENkGkR:@>Jdq4JU;^ZEYHW<bEVJ
NHGid,dMJ7*<Mf7l#p_X&-liQ\sa-'SDR*a4><Rf\!9A[jGN".5jD8-nA+A>N-6a]AM@8()l3
o9)cIbaA;=rC0s/ph5d'Mon[tG"?HT1`$Ql"R3^/%$QqU&jmY+2@a;@+6EiVif-Agt>Af<^'
N@8u4L(\\Gc=`HtdZK>loa`Tq38BI08QS7L5a0)I@$,X&'e!(]A-SSIPP\@@pmim-_$!t/9kb
aFhcRKjC`dH@PWV<&moF.4)BZE]A_H9sSR('*]AHL1(\Z>X`H@`3;Z#BUHjBF.^L=5hdfQBJMQ
pVet7SgL'EK`oC4<=^2,m70<SlLWnSrO^5fX?j;27Z`WTnd[LCah(///\L\R9TH;Mia--Gem
-?mKj>WQU46%)K6)?MSR2C5pBWLPs#0't)NH)KpG>*,^8M$4\8(Ams?WR9[a>>(`\$Ik(hs6
8Mq?QR!2^`Q'g2W8fYUB=X]AB(:<i)>i-F+cU-T<Kf!-$H)@l2M"abqS`m?(6^P>L;$/$fU\X
/!7[^8:gJFUn.PNlH@SqR]AGuEc"Y=&22X\^$mkMoZH$3*Ej!rYR^]Au@G\]A,cq":tn.(V=Qnf
#P>b)(ftX(!:/qm*s@/;JX>pU[M[DaH':hVefB@dH/3D<UF7[8l?0c'j=`9*V56^16K-!V.T
[_(,gZ1o)0;LhZ`Y^-.[te;tN,gr/;8(+"g=+^$;C,J3)TA\c[ui`DaD+oPtI'k@e:BG"apR
J,DF0HeS$#fP`*R..62CN;Y@"DB+i<T;;NS#_<FK'bi\3G^H-5,JVQ?!c7OgbdSR0sEMW9>O
;ILtEaaIDcn'I`k"V7Q2fOomgB?=BjjMgO?@\JGeNM(!8&hZQksa@r^4gjAkuL3>'qVk4`hW
HV`[^VjNikC+@:p8@bc;@g9nOCM]A<s>"iU1Q7jLn$ihCum8E$.k,*aUn+t@'4%?SpT0$VhEK
HN:\O_^`$@5ZSkk4o,S*Z#H$]A&^Yao@%#>YR2L/UDdtNnS%kd>MpFs/k>Om[h1bgBQ4;4`,3
GDVsUo/FA^$=gG=gnc&NCMh=*W^Mn8X.&gR2IX+u*CJRbL9QW<Hd^6Jte;>GXi'1So,Nj_Kb
kuE$aV,!'6;;oEFiF;;]A`fNs?JRF5CcD3;cCr7@ma<Ime!^)M1)@`u(NTbceBJ\r,[391eR7
h,`(-4H#tG<g5"OEaUuKqA(;380iG3#$9ecXsc/V*WITn)t3%+/(GC%>>+)J$rYIF(;?OlMk
0XWl"\ul!nXAelPW`\fqEgaEghRiE4UCDSl+Nt24U]AK]A@h6.W$*TYQDfUW@d4-=*5$]A,QP\&
15pTEqE_hr?m*BXa9(O6I6g+,Dn\aj0I0Y:6]A\gNck4)8M:S:VZsJmk@o'7^0.P5ap5rM:H<
n)"+0jAM]A3HMQV0d7+k'#>%2<>"+QRLL9qBLN?M#r@=ZMuO'0KTXpWAj9C@g7p_@;EV0Xck%
ifA4:E(k';nKEE/QW?%376j1j>9sIDoDd,Rfe`]ACeKe,.C5QI0oEm$?6A"c%b`L78^(*:":8
nG4>*4?\7Yq<1mK2#Qkk[9SQ(r<")HVtR=8E^YI<=D_M5!47mrc.4I@b;)i['CI)ELXbc$OH
+.(l('QJ?l,ne`MC@.`r5H#Ero@`WYcOEMsWH\7t\9/`MP*fH)MSR0-p5\%d\eaf<lO+N<f#
5DEG(L,\a2$$T]A*^?YKmE=Q\#W@[!8Kb9Gp=>33lYEc/&jM(I;YQsAIEBgS_E+PrX]Ao!Ila/
NF1N:uh:oO"R/ck%+)0+Zi;2$ogR,@o9C$=XNY:L1><q@+`5pI:]A0#[DYi;Z2b<Lod/NuCm1
>^WhU`-t+lo2X3g,2S-W2pDJAeW><Vdc+HoP08g<HR"eKt_#%%KblSD,t8Wp=,8M71mCc`<k
e^55?Ppab?E,gt@P:LKZ<F:GJ`LND,u8knIBTP)]Ak-WYN^/aMt4`3fG\&g`[$CQj?EMkm-A7
nTO1T)g!G/CC\ip4NX_S'e+42fchG]AT_cn68$D'`qD.ZB-_DL;MY]A/Gf92fZ?6#OgrBjIU45
C(AhiU>>%*G'QNuX:(%R?`44)cJ)3:N.1YICN=D8iB4%%!;eQjQHa^2)K"p<\p6Z/e`'C4U!
bi3bZ";k[tUd:-6k6"<+U6qi2d6I,>eZhEYL;_ZHnheWL_ZupkCRY:l.&PH^TIGcNqM`k^SG
NoTEf'Kk$[f[ruf'Vq*+)%IQ=bm4=>EE_9ZG1;#-C\eE@f[Ki/nKMa)16@>:VpsY/`f(&;i/
gfe=H@B9BDp(!FoIglFh,uS='r)+iDV`AG+gT?#7?o6h5Jkm.<0<.Xp,!LAKh;ls?@=(mFBE
ri8PkVgaM86nEA[q]A7ag1eF%;^HeCQCofbJYh;cJY_Eq>dN`&'m/khM*a#OnL0b3K5>*.Yh&
A!Y[<T#)VYc(.B0\kpEKRI"]AJmlQ>\!+?LFonVAk'M7KZRSH<>N3]AJ+L;D;b5qorOrbmA8A>
8A_c,]Aq)$A`I,5FLB]A<[l+I,cj`*=PsQ<WR54jW72LknL>+Q&Kmp_BW_D;.YMV$(s>el7)+#
;i(*,-lBR?bgUT@,I0B4-=Haokn4N3I.3%3(,TcOuGHnR]Aep#I*HHBfgW[1=-4=/>M561hl'
Ok9CDSfTEYBV\mHW#:6NS_iUXdSB\S@(%i",kMLjOYWcIWN+_;if7iH5g=mrPA2`d'[\a2t#
p$(!K6hg!ITd/6%l.<\#N2#AALln^!EW5XUK:DW&#W(l'F5u3DCR^l<KW2SUh)#&W?-0H:0W
8Vu)?<IB.-!tgD=I;]Ar5(>4ljq`dgLXAol,EXO~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[D0gIM<;ZRmR5nac#%Pi5@Lstk-A>:A80k/X#n]AWu8qnn=2D]AC@9,fA([,#!/&k+">#$htm+t
>F38@EWH0>DSjf(XX]AkP+fDSF_1ocg,j>cguHID^1+Ib=5B78K7^18_`W5NW=S5!<<d-^a!b
M.NaWQJ5o1L<`Gu\\KfoB3@bU$EJ$/deXF(d9VGW:CP5oX>Wb@JD%H?d<N\/-R61pU0l4-17
R%R#b"be]A,]AToU`jq)%I#SJ?_%i<&;:ZLa1*P:*HM&%R;,[,67K;6iIkR7Mr]A2l8<"`+O)ms
qXAu)EH6;5=QWh'Ln06raO!O>Kd0;Vq'J1j@erVZE:lGIK7e;.3a^n>PLpI_L/goEB.WqeZ&
*(Ohqm!R"8V5BS51#pB"[oI\2din[_m&9#/8g!G,/_i&7_DNKTV%tmO\O,`BhYcGXE]A`gUf2
KP/HOa--D%tX9[$[Gc:Me&)Dc\3iK#gn\MS%YCd_\D[8eM>aE7%N5$[?`fs%hfMJ._Z:r<4J
B$(d$!/]A'Cjc;Vbhmi.8/c5hkPQN_q%pN@#g-nC-(^6&oD!e^8n#?^D*,MRh&AVf59jTg1[l
:<V9/)-E@Th,:+FqTdX.F.Am]ACq?+D4%Tg;m@FnEL1^tP+91!L3j'h$N:IIZg$`%EtFFY@81
\<jHdlur\UK:@9NakT$F#)J\frNGFCPr$)GAMniLo*ngCTIY.s&#^X"hq)?hqbp?e5AC%R+m
B$,]A-;@FMA5N5"'>4g6dReGu_XOW!rqh=0TZ8gr-e^d9aBqJH@:)pX_W,$KKJ6d,HpdL:D,q
i%B1>oXa5-$@&;X8#0e]AYDp]AZC8CejV'(6`ql:?q1M[&a#6^35iQQ_VuhNVfkt1YA8B)GOj'
facU/DPTjui:m;l.$i.;:7bB=N:,8$^&0&ftrR)'(Lt&r?)osXsmgcG`Gkn`@[C&LRC-+t/X
aI&Z<u")^>De!X]AkjLUa:H:1g:$?#q'2A1SlU7>Ke(g8UofXBR_q2.OGp'W,W;/A1Ee#RQgR
]A;8X34mUOrY<jM/HWk_lN*EWWMU%(l9MLrJnM:JYDN8ZPGLX74q]AmUeN_'!?+m>B"[)q=bg?
h$D`$T+K3FKL0%-X\KqdO!V"&J-rKC>OG<t8KiSsbC1?j)0J>$SF0A95mLf,Xor`=1!J'#fE
E+Xgi,^:>EFl),o(#`3`-b2g]AQm('<VQlTOn@)C(#6;$T!Q10HR1c&=LbAD"Gm"HK%[7\=u>
u2!M3hN[LN!.5/55%YT-j7]A8;S.7&?7nM$m902(jslm2:fMi0^c\L^O>XW+pWg#9Z0S7aXhX
BJe9LDc*P<3inPB_t=I*<kj;)9MWUGH=="CsCHEb9*Y@c=??drGJa$(1)]Alf>oOJYT>'!:RH
=Ga.*$OUT-56#0ULFOa%l#\&PV<Anat`ZXB2:j:QG*hYaogoRcAVCnqWEIaG5[FsLPH!Su;r
AA\?=j"#;UlKAbGW!ttPpDm8;hh@G:#8>$olXgY>=T1]A=EPtftM\f&phk$c(DN%+Rn=&:3'S
\*k;J!)--ZDgqJolqtT_h4]A<NrH7\kkmC-I;2"lF'7\f,Zb;+E9sLS#&hnSR+F8p0>7H"#DI
n&F%mHC@`CM\?WDtGDR0P_'D<oo7n,GJ]ADF,"!'Ye\id.ZSG:LNG9:UMoP^2>/PY*c?((i2S
-ou>I\@AHbp'O>:4rP35bVgGPr(RaTieCk]A"2`J3p8R'W&gML\DD"fCnr#"(S0oG`2i5W!u$
43('B0>3d71tXg\*;2O)JQRlOJ?@EdT,_HQQhT!=rG[<n_bL@/2L]A-p7j0hk^]AYtH1i-\gut
niba-J!9'$eV+5R-qp#K]AY=QBVd%,*=_O$n^^"a<2HHND3=oc6DPCGeJ=+td&GGM9^/&q/da
,e!g>@B]AWDr9ne/qfZ0m[e6Ds#H-ICSNXOeJ3Ai+[`869lU<V';@N`I)&0jlq@0S'I;$^Jsk
[C?MG>ShBgOS0Q@o<0`h8n8G7n=@_$hLDbLJ=9@5o_)Q>O(#$sl_7Le2gLf(b=(g]Aq'SC2)"
"Zpc%9R(cI@,Z-h%S!)I+F9;_QO7SI0gN%c?&8A:s^MA`&>O-YOIe_Cn4@k$5=.Ig[]A`KL%s
<gCh`$NR_uZ-io5#[OXAcr]Aqhr%%!V#o4=a"sP<%$"(3qsgI%[4#Z[n,npgSm=%hTd/a>s>_
oS7*r3.P\3,'K*!13fT_d=]ABLhp5YY:,H0a`QHGhTbFtK]A[NeB*$n8f5a[bSB',Wrf-hC7gX
chTjEh_)#7u/if`#:07M8HRHQ[WIl7Q4YPXO71G@m&+:#DcpMc^bK!*c!c!gKe'7pNc&8Y=R
41bjt0hVIaNbIVohqS'`9\GF0n6D`+;[=i?X-Fo0aJk.(P5hob(g9$,<@*;cu;)2R<DVJF\U
8$\pn3,<ZX7=ETj]Akg$gCjeZKJJ$]A$_4U+e:?jc-Zh6'>*)G&"ITA2S$%#bJ2JTA)rA^p&sl
bZXYdNeRg'43k6_$R3:_d4/B:_G^X.E7eTl0CidH&T1qHsH-I(VnGfa@PmL:2^DA?P>SSOod
qFf>@98:r77tAM>j'l\P<>(JL-d9n/G3dCdh\/nGF"!j`^l/)u_?p:ndh903:$Ji@/3+.%EZ
3"UD%']An3"-9=Kg.k^2K;ta1Y)h4^j)X?DCA6"@@76@pj2VrhGu)?D"/OfOGPQ[F"G05EQks
E1N!;=l'$[d(F$)Ud%$KK]A4!\n"u_"AmK.Kn?f[nU\>2A$B@YQ@!2>k/\;r%Z)\0CAkT4B+-
13uHH/=\o='9Ou<!X*f'@`qs&OBD@5Ru+t#pUPh"+Cg&OXG]AKRV\!7U6hJf1duKX-K3Bo;qY
\n9t'gn3ibr<>t3O3+s@/G9e?!i[&;l.9hC_OLTC^Wdl0$*g5$s3ZGLhf4R2/=4R-;;a-04^
r4&#N#q)iIAf[$<5Erp[O@#LHASaat$NT8ZeF>t,c?j&]Afd.GGE*tc4lYA8Q>;s8%ijXep>%
kbQr3`gm=Kk]Aa#"0UTWb7dZ#oEY=@RA/c1eLMB!_;-@BJ_N3;DE7n?trt=@[3@<cQAGu+iBT
ChAm:4]At9B`f\P*:Is!g@4e/d&>>3Md]Ab?.$Oj=o<Sc&mLH9Ll;EXhs-\`W^l(Gd/iIehB.J
U#hWgrr\tipoS:RiTFW.1eJJO7"leF+9k[[8D$U(A1[e;6s-`bR_XKj"j;8b9*):Fo)@gkW@
)bGe%op%&UH)^73EFIK]A^gg_+/)nJ$7;kcDJi;eWW7jD@tU6+\>&Du:g_!5s(2MTqUNgm`eg
g0uh,j*,SGar*eU)?goJKf:XE=%4S=aFd+`g@Hj.8]A9!O=JU;mb;nFFgWJG#!fa_BJH]A^n)p
aU(MYi^hp8qYnhp\un_JhK)Z1.HESL]AII+cM>"AHGL7jiogd^nnC:?,;6.>WM^LD2)^kO]AfM
'8uRdoO)(<);VmUjnK^g*@V]ANXVrFLnJrrnfStmVqC+sPCdO/_m%6iFqoQjm)rS')8&W)Uj]A
OF#!kpR1;MZ#$>X5&R\5r*fQZG8*+^7hHHgN"B2X>>,:c5\QN-uC+30^169/9Ps-Lhg7,gpu
7IKbYCA2fi:fUG-(?@\G)lUU&gP(o.)S\_XYc!7ULM',?i-@g`oV*C!,2l5426gQodX:V3uq
V4:p=V)QrK4D`8"<#28`^!H'M!)@-;O<>Fq2SmfdXGnL$*shbHZ+aq:jHE7=,N&;4:ZmBY_%
[o@'ZL6ainc$l,]A@>gUs'h^QSiLP#6+(P^3EVFM99^B2;,DZ?SXB=?>?MB5#%[pdb#$a&t!a
dU\ZSfZ">^b`iW@J=WB#Fs4I1<nqe$lT=@:$GO!t83`f/qTiWDY9tbjGLbk".'C-mVH1<ThC
q-Md,Y2Q\P53pB-7@O=G+8>]A;!;3e4n_t).<.RFNit;R.Ha=n^%,2:fTG*HS4s)?GCm50]A^+
[qk?Th9K]A1QLUqFZJqobUj,L6QQ;"GB2G;XMcp[Y]A>=f_enj6epi+\@u:F#3o*G.#NPKAp9+
1Vl.V2'i?V'K4ok?'2t]AoQ0Og9Cc19!s$W\gr;)oS@?CC+;6B1I'OO&ji*.QT$,CJ^P<&ACL
eW"h*$om680,dW0mn.aL@gW=1[>8%UuI:C:nA`GjS!;Umh"RC6.DRr*\+G1"_u`27SS#!%$L
,64O48brV1baBo8(<,h:en]At.V]AK@C4#0^%AD/paC!s@Jj=7!0TUFbq.ReYbal!=B&oO\^"&
]AZ6.o/"pLph<$_!5M2M<77Hppplh.&##okgcNO\r^0^@?!o&gNi=2rVX6o[@^F#q9hHI[_"[
<\L>1+0CUTQiEOq*CDlpG]A!J%VpG"U3/84F@0"T+P5?q^GaiVT1S`kn(-M.QCYGlS#a'P-OI
q)nIdQnJ.`$P(gbZ/UfSa-l3e07"fU/[3&GKluTg,Bj1%5p.6s%n$WJbt-H\ei5E,M_T<QPq
)6A&4KJS[mGMQ$gek6TYSV^0ft+l]A3>c8ZVG(//l6"lp$`JL-k,:b'LBsNM+&?p+PR.K3O*V
]A>NoHp\^8H[O%POMOT"[/6r`!TWfQt#=_o<`MX\,VW7kArN?VQ>20VW%]Aspl[]A`ML(XJ?++N
NRlG-+e"MNt[Z/q3BsWOnVLl.)n(:7'!n?g_9Ru9dtI^2V4-U$h^P4)Ym/[nCG6/f$.dFG89
3N9!2O!:cS@&'.Y8?b+RECA5kqjB:_9K6[nWpMRm-'@10en=L$3]AO%t<-h;=cJ>$#<48m/,d
+,r-6I[ElJO_#Q97gX^4.4Ko+G.;bR7G)^u`L3n]A.HiD)QT<Fd6V4L+f_0^-BDsf_O/>CBjB
5_^FsYtFKZB>`5bo17UjZMLI$GG&]AUI;_@LdD-g170u.K$Y=WYS7<Jb*"Mp$fA0MEm&!(AF;
J('/Ps-e"Mti_sZP5C7dsE!At11ho&c9jVMcql)g7,1Y9'XdbMV+)M?o<[&r[G("K'A5bjKE
m"d<;o9K&[S8,CVjm#rC*4Ml>sIEQ2n`WdKPhHeKt;^EWOC4*j6s=C(TlLN[79"R$uF_2#Yi
&P[VImY<f?"kB]A<B9im1BfSdEXD.t@l;CgIa&Ca_eR*]A,tFiO5S-I3-NFXgEoi%lQ;@X3=".
I#,%$SP&d1<q4/'ebEBAqas]AJpft?jrk,P8fn5ElQIlF;$<Zr4CPre]A\qVgKSpS2>_8I_^fQ
_POJ*SjF$?oWV&rZd?0U0NHGbq=A\j?M6.mJEe,9Mo!^Khq2cRP(-d$O5ZM7*-gpP[Q03p6H
mQtQ<:()0RlXMZFjGOEqj1En6,B:HHe06%ImDnHi4htKPL7$p'`fS=pgZn7"f:.CF`pd>XRT
9=FN#=^'-'2if+R^dG=]AhPQsf"W_c5.m+Q&4AsN%/9S55?=[=UNtZ<+`&)G*C=cGG4ZVKHYh
4G;gdoM'5tIKd,gc6eI]AL93@C%nCJP`d5nF$1#\SbNi"$m<Tst#>=8p.@;',Hu=t7_(2ZcDs
h_,5W#n`'mh0t-'&_F!/@g3iK[mVtmq8W=\^'m\+)A#rE;]A1h[0QaK`$WWKK(^d$HfW]A+IN>
C)T-KJWe.(;Y!$F&@X'(RN-rAS$lr,6[17M6V+c=s5=a,I*4lJRR%`DJd<'hMhf-*`o&I[\m
f'3%-BaG!H8VD99W7P<_.$=Q:ikEa-qlgS%Q5h2[l@.nZ.q]AfReOP,Uu`rQsp8!*WP.1(kc9
dr3mG1>1/^BML8(R:CbiF8Q.qF^_T=*jPdFB7[_8!&ei=G5UFG!(Ue3^.VSOEW2Z=@2+Ks%/
(\fI'qpEem'-\3E$!aOjr,RL*o"kcR]A!-O?8%cin\.g_&Mb[4]ADW$_k[?AAC2C,_FKo0;-,_
"dG]AKjtEH^VLL'5&r^OXi/Kj)i.g5e7\6)Q<T^B:M-E^V$VZ:QGJ?Qnm[8M,#]AgG0OgRF0jj
`=i=WbJQ+e#QZ2Zp^WciNH>%Zl@DIMj[q7U]AOToV.T'dKTV:ZSLHRc`K_PWRcR*r%eAb,k)^
eE/7O!F`tGX)QW6uN:KFc3dW\oLU-qAqU-M5dsV/mf8$;&EN&oCnol#.;]A<1Te61']AE=O*`&
c.E]AA(cqOC,YZr?<WcDoZN:SO4=sB/@4S"b.HiW4&A0]Arir^G3UkB<[lY_.05eBZ;B\_pec1
0hs&G5>CN\*l4N'@-)cD\SReWt]A<7]A]A>\)?u)-n6N*O"2fiBe_XgD$rSVhmTV3gtjm6<N'$t
djRk8'F#??oOR%AT4:1ch[Or:nj>iXqp2q@\.IiZko=t9HqYC;b?6W0AB94nj=;?k@qYt("W
IF".?Tl_adfY%C``D;;\efUf-JR*2L=RGrKHIAJeLF+"CZ>55f6c@.\g8F'@=Ous""LE1-U1
d=nH3:["Tbk]A&8%#`9_KOs,,"pqrW@fK<enNAX'ftBHd#9k6NO6?_f;U"C"f$Q%1E8ZE0%p2
Q^0F:\V`"oN6trZREMH36M#$:il#Uekb2Er;i>Pc3b5U"6J#@T0bbur/3]AY)R7k=SqXa>KNS
%!A`s)YiS5Q5TN50+C.RbjI!c;G2:.*U%7;SV);s*)/!LEX,<+]A<"r'gj\3+H7UDlr$+91Um
PQ,h)//pWqp6Y6Sk(=AAEs35)PE8_g"Wsqq#;$jk*uoTEVda6pd9NORN'K",pWUhXfEku"e)
>`f.h=Q<)7Q",bMJ3bNi90shd@l;W#(KDs!%2MW5o1\Id30%Mi2!N,Q0^EKYm1<XSQgS;KU;
!fg+QPnDSKMGJu+bqQg.c(]A#Re<r\MVeniat2;&P9hDSgl%Kg7u6ca8*i89/8AZ@.G3*cepW
":#N1B5nNc-3ePo4+P/-P6&)'`O$jR%"@<%TH_=dJP]A_=,^k!TJ;>cHKrgg.#1JmNlbK)9m%
//T@+IS!qL<sCr:l)IjS?#"^EM(]A'stRAJeVh9FR?UCi6Qr:p/D<rH^I::%ZhiLO2rb0h=dE
gkC!fCj'RSkursqOWuUAI1",J-0]AnM6\cu,+;Y4SpV7-$^m).ZFWEW^\a9knc/drOaO"R]A8R
G4mcWgJX''!Saca"kBUTV\^1DC&^12=Pe?Zh%S@8uc)rEhE@V=,0r+;DH\s,-eg%3d-iT1;-
%QS/arF#Ph]AD(WKBBV>-On,u5&4m>;rV#96'7^8aOq`i4$?aYi#(tV;fm1CE'c5`dhgrj3)d
to]A%nga/O^t$`p/B=d%;f=3r59=]Atr617l\m-K/N%hLbMG2t0]A!>_Z-)d:&\]A?<;4nhM&:<9
7*H&#EUL5dU:IqfV^aA_,uB!-cWW/m9H(b>nu+JY<G9+h22,JH?)nI*.S?$%a_X&.)/HAXoC
^J1]A3#sN8S:;?q8cB/9DH@AX07F6/W)<"?tY:*(WqC\M+YtRlm'&gM=<9XN1QS>oD1DJ92%m
U:2r#U+sA8"\M/OkA4'[fmP]AR^rpg=p3X\K:&YL*P16LZ`f6;'F8Y:':-c3SDX<CH_N/5)34
k;o%?H%`fc@a+PX-c"9fiqfAH+XhM>i-BteLDf523VDiP?7]A(?ieLZqlJHglb0L$PVWHB<n"
)okddlF`M%1(UE$@_LVA<JcUo-@RL0M<UC$7!J:>8cbR1*OL>GVB6Kr49Y<_pF>hL\""5_%+
rIKL^.PEgps(6/=+s+8CAM4gE@8F?Y**BcN#ODbZ3A`e.sQkGsHch7=.d@;Jl9E9?8N-<:R<
ht2'`YgdW)=o/:K;KNb$*T=X5]AE3*JB]AJUg9<Nu/+ITY$b.>`*G#l9dn6D:lrM(M6_7\WBU(
cN0JJ=0CT%-=,j3KSeaaQf$-*UC8)G=?0Tf7\eSX.@N4Ma`!o&7[L'VG#OX4DZURj-u5$`)F
Y2Ehk<M1apZ<`jl>0_`]A;_nEc8>.V"4?]A]AST(\*p:\+3]AENkGkR:@>Jdq4JU;^ZEYHW<bEVJ
NHGid,dMJ7*<Mf7l#p_X&-liQ\sa-'SDR*a4><Rf\!9A[jGN".5jD8-nA+A>N-6a]AM@8()l3
o9)cIbaA;=rC0s/ph5d'Mon[tG"?HT1`$Ql"R3^/%$QqU&jmY+2@a;@+6EiVif-Agt>Af<^'
N@8u4L(\\Gc=`HtdZK>loa`Tq38BI08QS7L5a0)I@$,X&'e!(]A-SSIPP\@@pmim-_$!t/9kb
aFhcRKjC`dH@PWV<&moF.4)BZE]A_H9sSR('*]AHL1(\Z>X`H@`3;Z#BUHjBF.^L=5hdfQBJMQ
pVet7SgL'EK`oC4<=^2,m70<SlLWnSrO^5fX?j;27Z`WTnd[LCah(///\L\R9TH;Mia--Gem
-?mKj>WQU46%)K6)?MSR2C5pBWLPs#0't)NH)KpG>*,^8M$4\8(Ams?WR9[a>>(`\$Ik(hs6
8Mq?QR!2^`Q'g2W8fYUB=X]AB(:<i)>i-F+cU-T<Kf!-$H)@l2M"abqS`m?(6^P>L;$/$fU\X
/!7[^8:gJFUn.PNlH@SqR]AGuEc"Y=&22X\^$mkMoZH$3*Ej!rYR^]Au@G\]A,cq":tn.(V=Qnf
#P>b)(ftX(!:/qm*s@/;JX>pU[M[DaH':hVefB@dH/3D<UF7[8l?0c'j=`9*V56^16K-!V.T
[_(,gZ1o)0;LhZ`Y^-.[te;tN,gr/;8(+"g=+^$;C,J3)TA\c[ui`DaD+oPtI'k@e:BG"apR
J,DF0HeS$#fP`*R..62CN;Y@"DB+i<T;;NS#_<FK'bi\3G^H-5,JVQ?!c7OgbdSR0sEMW9>O
;ILtEaaIDcn'I`k"V7Q2fOomgB?=BjjMgO?@\JGeNM(!8&hZQksa@r^4gjAkuL3>'qVk4`hW
HV`[^VjNikC+@:p8@bc;@g9nOCM]A<s>"iU1Q7jLn$ihCum8E$.k,*aUn+t@'4%?SpT0$VhEK
HN:\O_^`$@5ZSkk4o,S*Z#H$]A&^Yao@%#>YR2L/UDdtNnS%kd>MpFs/k>Om[h1bgBQ4;4`,3
GDVsUo/FA^$=gG=gnc&NCMh=*W^Mn8X.&gR2IX+u*CJRbL9QW<Hd^6Jte;>GXi'1So,Nj_Kb
kuE$aV,!'6;;oEFiF;;]A`fNs?JRF5CcD3;cCr7@ma<Ime!^)M1)@`u(NTbceBJ\r,[391eR7
h,`(-4H#tG<g5"OEaUuKqA(;380iG3#$9ecXsc/V*WITn)t3%+/(GC%>>+)J$rYIF(;?OlMk
0XWl"\ul!nXAelPW`\fqEgaEghRiE4UCDSl+Nt24U]AK]A@h6.W$*TYQDfUW@d4-=*5$]A,QP\&
15pTEqE_hr?m*BXa9(O6I6g+,Dn\aj0I0Y:6]A\gNck4)8M:S:VZsJmk@o'7^0.P5ap5rM:H<
n)"+0jAM]A3HMQV0d7+k'#>%2<>"+QRLL9qBLN?M#r@=ZMuO'0KTXpWAj9C@g7p_@;EV0Xck%
ifA4:E(k';nKEE/QW?%376j1j>9sIDoDd,Rfe`]ACeKe,.C5QI0oEm$?6A"c%b`L78^(*:":8
nG4>*4?\7Yq<1mK2#Qkk[9SQ(r<")HVtR=8E^YI<=D_M5!47mrc.4I@b;)i['CI)ELXbc$OH
+.(l('QJ?l,ne`MC@.`r5H#Ero@`WYcOEMsWH\7t\9/`MP*fH)MSR0-p5\%d\eaf<lO+N<f#
5DEG(L,\a2$$T]A*^?YKmE=Q\#W@[!8Kb9Gp=>33lYEc/&jM(I;YQsAIEBgS_E+PrX]Ao!Ila/
NF1N:uh:oO"R/ck%+)0+Zi;2$ogR,@o9C$=XNY:L1><q@+`5pI:]A0#[DYi;Z2b<Lod/NuCm1
>^WhU`-t+lo2X3g,2S-W2pDJAeW><Vdc+HoP08g<HR"eKt_#%%KblSD,t8Wp=,8M71mCc`<k
e^55?Ppab?E,gt@P:LKZ<F:GJ`LND,u8knIBTP)]Ak-WYN^/aMt4`3fG\&g`[$CQj?EMkm-A7
nTO1T)g!G/CC\ip4NX_S'e+42fchG]AT_cn68$D'`qD.ZB-_DL;MY]A/Gf92fZ?6#OgrBjIU45
C(AhiU>>%*G'QNuX:(%R?`44)cJ)3:N.1YICN=D8iB4%%!;eQjQHa^2)K"p<\p6Z/e`'C4U!
bi3bZ";k[tUd:-6k6"<+U6qi2d6I,>eZhEYL;_ZHnheWL_ZupkCRY:l.&PH^TIGcNqM`k^SG
NoTEf'Kk$[f[ruf'Vq*+)%IQ=bm4=>EE_9ZG1;#-C\eE@f[Ki/nKMa)16@>:VpsY/`f(&;i/
gfe=H@B9BDp(!FoIglFh,uS='r)+iDV`AG+gT?#7?o6h5Jkm.<0<.Xp,!LAKh;ls?@=(mFBE
ri8PkVgaM86nEA[q]A7ag1eF%;^HeCQCofbJYh;cJY_Eq>dN`&'m/khM*a#OnL0b3K5>*.Yh&
A!Y[<T#)VYc(.B0\kpEKRI"]AJmlQ>\!+?LFonVAk'M7KZRSH<>N3]AJ+L;D;b5qorOrbmA8A>
8A_c,]Aq)$A`I,5FLB]A<[l+I,cj`*=PsQ<WR54jW72LknL>+Q&Kmp_BW_D;.YMV$(s>el7)+#
;i(*,-lBR?bgUT@,I0B4-=Haokn4N3I.3%3(,TcOuGHnR]Aep#I*HHBfgW[1=-4=/>M561hl'
Ok9CDSfTEYBV\mHW#:6NS_iUXdSB\S@(%i",kMLjOYWcIWN+_;if7iH5g=mrPA2`d'[\a2t#
p$(!K6hg!ITd/6%l.<\#N2#AALln^!EW5XUK:DW&#W(l'F5u3DCR^l<KW2SUh)#&W?-0H:0W
8Vu)?<IB.-!tgD=I;]Ar5(>4ljq`dgLXAol,EXO~
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
<![CDATA[1866900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[853440,0,1981200,5638800,853440,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" cs="2" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=$name + "分类销售占比"]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0">
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
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="112" foreground="-16712452"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9>!@'6gmnh/2!!>IiSE65>0U0&').A1^dao5V/f2Sl[.[jps2G#>T'(gE4A6HJk(Qb!MQRK
`r?XHsE]A[=F&M+cqqg7-.o(:np:3L_'V7!AYh?9Kqm_mG=b+)4o$"luqj(njf:,biJM<c?nF
>T(AF3o\6ibT6kb?ZZG9QH1%g-CKPZ?quiNGkK0Bh4cOkGs4GZWkKJ4Xl`E%FraYt"PA\p+J
N(ogQdh^HVu)D&Vs&o1SeBq/me"BDp!8\:ZDElEjb6En8:Q'GKo=82k5>.g@cNo"e:cGWe=&
/UmJ,9p3n,h$6Df'+OVasM6L"3eP`nHY2oKSo\LiDOfmeU^!@Wlp.Q;Oe4-2CS4jceB2<Uq,
G1GQ!Ya`&,MD!F1^oeB$k=-nY=g9eac&-f<]A>#qp[Bk%>Q48mkAoL6=p=3N"PfO+8>CmVFNW
8R_EKE,H'k$e+dPTCOe))0Y"0e=q-WuhI2[kZChDiNXL^OiV<ohi[1Ip+`)Q?<%:fMu7^fY[
ie(@1\EgF:RY%Ok"]A)DSH[n+aa&4sf,PPhRJ6I?W&)E-rbkR<7BGB3#4h"j5_NE@,1dGf:3W
lVh.Zk9`g<1Ya$>Mg*I0TFD_%tf4Mk81gQfJo1B?+7[^7lG3mKM^=)mQQFY-L3NtE$),/<o.
\,Y$BQI)9ofc!WFQepM$SS1H0fdr.^nj(u6"-=/%W6^T=T)Uo&is<2i@DSqMOa/!BTc]AK:4*
P=@=V2pBmU#3M4rl*ef^0LEFa!]AlF-^@sLYbEAj'_u;kP#2su,T^GV!+Gjal5T8*"lIa>-0/
)cqKK-rOjEGJk1PW,.e^lsOr]AlY4B'W^4`m>^>O2%$<X`@'KOtecK52$llX'[BlTk5H&5-)#
!.+hGg3=J[?C*9XiW:@4'YuQ:l@Bb%$P[GL>Qn,p3Z4V*?%9_rJs/Q0QfA+D-KA#TSX![SuY
ar=(>ETijef'B#UE:r9YkO9KQ=3/M99OJdRjSX$r6RtNfd:EQ:r>Sf<24T:_ncIHU)a$n>Z;
+5/S#cHLZuhVK&,%qVs;*XaS@*Voo?D1dK%_nG#rif_B5_&.Qr'DP"*kj6JX?,9IZZlZJ]AU1
ZeDtNrJ7=H[kIq?qD$\+k6$o@hV[.L\U!Z&I*<ec#^NlkoDa&TDc\$]A`9/);.S:1TDH,?TlW
,Xi)/c:+gA%GPg^Pp6.u!]ALU5R5$:`IW[Lc-MSp.r@#!.A#AKY))XJ+EPAM@3^^l`#bi,Q:6
klSCmf%$\'G$4=S^Is`[<DG)k2%[#?q$;<Oe[,iV#,M$8R5]A^VAQ]A(Q:5k>%1nfAjQUJoE`G
$[=MqA`kUA(FA42WMD08s.s*IMJr%?Jn0:B'nala'I/d+M5d3e%.,/9q^$308^"Y?:W;Nhlt
(XMFFnR*mT5HZc`g=WU(Cca(,e<jGBgK&.mjs'!b_>*AJT?XEAUW_XP)9HHY59m2kHB>,U$T
fuBR(GGNj>,kBZ2QOFAR9D.YHfZ*Ojp9F(UU)8P@b(s`GGuoki#N"o3HV4!kJXdqT2XG0F@>
@nHK]A5G;6qso[5dQ%.S[d-s`M=G8C=b?GqtBT%>dl='W#(KC4S<"keZ[N))c/'47_P.g(FcB
o%[ZPc_IZq(n`aA%!`B-P]AV%\`NdI/9Z<)m7_k)D.[:PlAB>8KY7;$i#A0K5[5^ThKATD.\?
sA>(o)@R?7<F*T'V,%W2oa<)I&qX-/Fk'ZU'M</E]AhUp>V21=69IU&8IdG_WJRO1RH>,9LR,
SM7SDB*U=Q=TRdQ]Am,erU\E2EF#rFRf:EAUa-9JddJ]AVGlbA':GTflut-m#^!jMtgNd'Y:AI
J*o]A0C/91*]AmL&,OP+PoJOh<Lbb-P^Tnq0"/Ni^'l<[E;^JEV?bOIio^f/J:B#CMHrfSqq@:
6a_VT:Rg<O5pFS.Z'OOC#hOcdU0F<hbD$lFtq]A&a9=p\4PUmF-VY_Ptq2Wmk6$.UN9L3VF/-
6pPTgCdgtUJc/BD?WTtC6ff^r5UI6CB*]Aq=CA@]AAX??MnKC"%)')Du6:dedW+g*b#XDDX^a:
YPn#e_0!mM^CA'Jk5KEcaurQgoY'hWhp+oX$,m=fQACY^?am(0%@$?-p\\2_!Mgor.=(>`]AK
CWiLn8O6>h'27+7KD<*AqqHSs-4FlU(^08m$!-q@L6S8op-#-7/`fSFS@-4o$4\:<^c*L\nT
lG2!bc3s[n(o%U,3VOohVYegYU$csA2%]Aa9,\ZaZGG=3L2N#Lt-]A44c!LC>WM$:P-e1&"Fid
T]A":r(UZI;[?]A_HbEi0tGGG2/j.mK9P>KhsB8*=kuTM[^qNnU'h"$V%.b-_7A+$'F-*#9iVt
L(S0Q)O'"7D&+$;0cY`&q9f-[?Co6&@IPgFrXo3r*7B$]AG]ASgY8Qa9]A04MX`-3b9$(@D>I39
(L?nJosiSTNpOA[@l6Z?ol5:CW0#=KcC<ki3\%3WOQ+r(-TX[V'5FL<JqKT8p''ZHJ4WOQD[
Sci9a!0]AcZ'O02puj"FIj2/.W=QFmlT[+$/D_K26B<B\r?Bg<76t,<U/'0X%rTb>lEU8P4R6
ZMp]Ao?9/Y708"0^-KC'8DbYlDMm95d?IJi`<PQN.+`&0-[Ml_"2f.+p)s>QNg7Cs2`Z`V8@_
8Ci"$h'V7;En:gXjrFhfs2HZj#OFCsNEVnrcQZP&&9+&.H'Sn9ptcmT)4qXeoird$<j8lu`?
"l.%&gl*d,L`FV2IZ^@>7N9I$GZ?NCml"+F5]ALU.6%7O--4j66i6B.-`Y>Sa<GpUV(8>_E@6
e]AWqHH;*$/b^TiAj84W'Yj+N\sXbt@-lk^;3<L@9K$VhiK%b"*F7-V(Bf^MC.-;NXn'Wa`uo
2hfJX%j=r0O=R4Y]A2[Djt(5sNsUl-^SNGm/LRr$uF8<)KSocdfXib4G/&jh7c3X7p8eO?Cm*
*IbKQV3VP<9Atr/AJCb<l'T`[oCVUP,tNY62eK#eP>jG-9Kj5qheZQA1tH[OP_)h$Ibl_>a2
fB+jrPU+"J,ibW@m_!mPs5L:k[M@0X==\#_h"?c3$\1[piOD#rkOA2fs0sI$;GJ<4&tgY>_G
e/O4!pAFW[\G1f[oe(>sMXqojN&-8eFL>&S5`T2,@kf&d3:)ARiOQ6bIUB;h778EA+-K9sa=
h/MZXl(ONE%-M*CfiuCfDh8`QLhQ)o#CeJ/OJbr7*;i?AXENrd3f1.cBm+8OL+_4b.Elff!F
.FH$T&rQ.D%5Stg*b[#j^(LSB!r3%e+"j`!Gm\X%g'>dr?`//Vr0hbVAb1W2)/m@=d"/bM_W
3WjEmoh?5mP#o1p'UE9I/YmYqhJjX*@KL'9d.(o,S:T<3NNSB$?r^2U]Ac8(b:DBPcjUZ3:':
WDbS&<%MrQ1g,FMSd[rd'@t($O*q\W5fY:^n*ehmoE^4Ou-%M%X]A7j=s@d+FH';05Ah4R%3Q
'rLj_u--g$\)"j!e2Z8>D]AX/RXl6ROdem\U,4MgnmBX7,u-VRS+T[;)L9Xb@NMMJUPN:^)cL
R.>V"T(]AcI>[:#s3@h8]A;L5HKb`4/8u@^ihQZemlB;8^r!EXil(`/0Q,0^W#LY6Qrmu32Hk!
K,Z0dnYBe)^<nK%^*kFL>)oEo8XY=De"3IApm%N;+[fu_3S+\@VI$\&`2l!807TPRQ*]AT]ApI
[42ut2rFtA4koPi%>Oq*]Alud)I1*fTKp\urTf*8aO(SgI5,kF?cT',.2<SeN\bfLMpqMarWr
&bj'4;TL#-/Q"1Y*PYM7Y-($T<0HmHIa+W]AE`O)\4G';Y"\'W=3Zmh:[-+MO*'@).&<XYo6H
M;Y`g"i;?WPCk9Qe<0N!E*[:qtUTbVXWj>GR`^X*`UCp/?D$El;mmilo!U^K4%U@L/QoacGY
RKC[Z?u'U;9TV<e?lgUfmq:PM3MjLdF5OAXb7He.?Y.=5JoA=d[r4Qg*h!c>WaZ0$kE@^fkl
Uk+?,c86ju(4:F7=c?@VjZr;H;R2DW?r>(K?en.,B!lE%p6/R)"o7>pEiDAL+US3B4/mn%CB
F.HtjZK1rc18sbm`LFYMEmam7;GT-:8)HLH'[-:8-4B7!:@978?!eP@n?s]AtN[Yu320k,$[>
9kO^Z1ZjBAA[gnn"PZ%[04*6<rrR:?TEcT)?.AI2NCHLUXC\1m,*kXIPa;[+U(1(9><P[*rl
u&qd(EOr@nT>RK-RD'@:>'F7;,5KPUl0:h5g;ii[AN;?g5s7d/NXs2/Z-uF23&TBf^GWT6nE
Hp)-^KR/s:@Z`'1*;0T(Ja'<:;bo`1Z9Gjcm]AJU$P!6HcYbW9NFgpd95;WRlYft)KiF?IKIc
4cJFMFPN_<g0:tP;uIUludlaP:[iLiJs4)rD,Oq9-WXZa2&=P#cX2%hI?GsiF4C=_BJ%P&_=
j"EPmWj.?+qUlqp/(ag'VB!=X^C4G(5D'!;YFD-(*NL%4>DNZZG#lL\>e=o%V/4+nUB8WF4J
"\\N6nRB,bacBjgM"o4,u6*S0GFY@6e)qnIa>c09=>n]AkEBM0Ga0_!uH[O_p2E[)Zj*;l':K
YWp106D"^`YEDE8#N_an[>0#5Ir0um"/WiG@^+qU"C!=?b6f[^:cg_uQdi\(9B[eD;G*8;Sb
g(*/hgk2$$gK!*nR%&OG0^h/`d[=1b^e.3iF5h!\X:#'h?f1L(I.J%,iRDT4ZUKB&b$@b8i6
s0_U?<D:;t!,h'*U[8\I,Go)tdd.86Km"5ct;,^TXP7EZ*j)G-=>KOs!(%!)42(Gfibc(9-c
U`f$L'AJF?k)hMSII$anjYA#lmtjLl1Urd$BL#TI-2flq4&SZb1K=mIi.t9K@N9LoXdq///"
qPH%/oVI'0he*Hl!lucd&.[TiG8fC'T=V!"tA>K?Y&kIV=KM3p@_i$-J9YE$N)&CH07i.mFI
'-E[jabBmk^.@[#LpiHlg?N(&-Z1Bgrc_N_LrA_a`@t$&(BhUUV7dF5;r,BV[FnmS:TbR&)M
'PLnLI!0h9o@WlY'L:HMj-C8p:k\%LN.n"5Llq[^6YuS,cncOS'WY%7FcAM_U^*TM#?ML?71
m"EBU[Vb+p.REfViG#bb`8@i?GZqFCFf5Q;K(04jRG,\/REhb5A%6NiQBaB'hFI&HWfOW&A%
7&It"R1g]Ajl`RRLS\u)IiS/N2%P-%9s3ZEldA*oK-`qBl5>G&G=&$[PTn:rRfKY5i"^UD4OH
(FF$3pr!]A0A93`^VS4YSpl,aW=`ZS:,!6dtF0l:Vi\@_e1&TJj89L6s13J!PA=!Y^0%g6SEK
H(L?&_(?^25s5RgG-T$DL4F!l*$ki5c*[*"?AK#\P?)O&n!5]AFto,]A62C4Ae?l!Y`LpidGKK
HUr"$W1ua$uNWh<qc4:4%WBl5-Z6r@f(k&!^U"qImXCuZ@/Yg=IecW8]AV?&65]AOuYf'r;3#b
hg8&)M>Bh\<L#m0e@.67,e80q55rrWlZRQ0\5'+cVI:=qWJnJ-XDrLR0$#;`fOD!PO:5ac*0
n\jDdQ'stjmZ<RdM6g8IO-%k+_a"SmVbqStP#M--.+6ga-K9hR!oG4m"KCHZ2DIAt@C(5McV
dJC4YYNB^S_&i,=L6_j:<DVD\CbX:fu:CfPS4=66&Y'_B4:*,u!>!VF;X*nf]A]AD!Q7bFTraW
ZT0Ygmi<C&CO[6oW7/C"Q&82@!>Wm_qH`e&sd[5c2k"$gR,Qq[t3+7uqnibd<OH<aiQQndIS
qDk\grN3!l7(j,G/pK($<r-P9@u$Y]Aa@=n@>(]AX-U(89mQ4Be@<o`oZ<,nP.<1HeAU,GNN#@
Y@Y""$)Pa0q?PZ,`+i9I;lpmGM+a_"E^oW<5%+.QH0bnBPKra6uMWRW7Dn/)qIL5"I.jZ@>h
]AKg/&PT1<YS+/U<6+VFnc:FBQ!tf.q:(W^2KKjd),lU&@1?neU0+cR#id)k@Dk6Q)s)2&pC\
<"l9*7q"p1.;e2U%7Hpqj=eJ6H9I=1A).1s1Qt^@r.c`tS)l5EE&=bN$YUgFXeb/dQ^6EM6:
;A[)?kMY$aVb%9/skX:rM%0C8i+23a+hL]A?V(jPW(oI$j"I;gZ9)BoGr)Yoj?dB?5j;rN)B$
2u%aG=XTnB5qQb$@0rR8VZK!=Pb1B/qY*_7kEffB=l&U!kU)Pn/#8bX9b&$Ss+9S6"HfkdtP
D**tkBNNb^U-Fu4SuNJG\1fKpeQ]AFh&<I78nHgc$8:5tSgR_B3QH/XlNA$D*r-L=<.l>$($P
gIoY+`>`]AQr3''dDV$J%%`77O/dB,'%_nU$e]A,P)CF+WAL1SSg5sMS17J2l)2/7S(@>%-!-,
E.e2EGFm<#RQ8Zd)?S%)O#-:0!<r"rjhlTR2k>qT1I$Z8feQ$]A.sRdj&W-Xos7!gj]AaHq>YQ
d)=cX`;co2Jb9_$36o8<H=>Mj+-8$GIjnVYF%jY'Z>9TA/(1o[-8(_GP/1N"$PU=p_nLM7%U
f^P`gJ+E"nRoV[1g6ZPd^>+SeP:QR6/,aC=pb[#4qGK1=afHVm5=6q#A:mXN?.'B7DQ=>Q\b
MYiLnnWKG)=<:HCk!RYUAoWLFM<ln,S`cqoLYTqVC9gj@cNI):Kr0@_?,=>bMIVFo'oH!ac5
Yr:-II@H%*f#?&j4m%hJKgUMT-=Eo_W?i`#pPG:`Ct7UX=K^,XPSenF=GfEc9]AAY`bAkgF2;
.dB+?pc0Hl\+dMkG^L_OCZIN&=\Jj;+/L:_)#]Al&#TQ22"A\lMdb^;u8Q/r+Oc<WU^UEZr6U
\KZZHoBKBQHFG?Ns$jN@#L6YIXBQ$O$fe)$uQhs9BDQ.2'FS"@#-Qo68oh6sqYm'S]A&gD;G7
E$AW*r"9+WRkrEpBI>#XDC=L!5BOX\IG1M7p&=-m2X`F<dMY;qiaaH#T;siZ&)7uc@gaZC)W
gTdga7i1j4&6!PW#Sq\-k8UCJ[4TH'-ABIceOaNc)V//872kOJ/T\/NZee<X*5h:+b2KII=[
HnnLYep)ne\S.O5P9='t-19.VaQ-B4WKQ`f(.3s_[Zn;BqB4=]Aa,*T,Q":I9mg6S0^'>3i$J
[efPZQ=Tqalh_.9Qg207E%cMuQo5r02@7p@m.l\(=T]AId]AN$)Z~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[KmJ#,<:ATARDgm?1:E-iaPLAqM%n2W+_?lafge,t-m?eC6APuB@bO8:Ou5#G8n\PTCS[&RA4
1[f9RWMi\(C6I4jRJIk1o^=]AW_)&^9;lQ/7m.&@6kbs!!%[(bY@g5!"oG8LenKcJ!)GM'`n@
b_%rnYgU#MHp)QeI[S(8(4F"jBi/UbIi<Q.pcVD7BCKQ!]A5Y6b_&VElJS05d)0M"KN@tkh''
TIu?BeVu_DL"If*DNj`X(ttAX2?V87.`.Ydk9S-3G0=,qqNn#luW#;CXqYa`NUn7eNUYO_(4
.9VoIdNHoBW'Vto"Bcm.Kc2q@6cG:ZLW!5!Z*8kp3e$n#@8X=rcl\F([,i$5_BNe1=QqR%aO
Y5^kmeeC8PI*/0q;3W??b6rnIX=Z+:Ktc@_>@PbdGnSc*Hf<A=N,OI-r%8k_rGYP,L`!eM':
P2nDH`1HdlU&<#HJZ^gG%b^qZs\mpKoq!r<*tS1R\+r(`YE%>"T+HO0_dT&pF9*Vhd:;LjM#
6g$XDBA6\_%(Hej\H,t!L0?C89c)3XUL4W*M]A:.XqYlR(LKi#$&1EX]A@F'HL2!-dGY1e)8K>
*sZjB8e[E'(m.EH5'q@%-n!p<Y]AmBIkIbo"J7n>%EVeLpF)JKfnVdr8!;uWMn;/.A(7X9pjR
K"?ou+<6g9m%=\9_o+XI-"5c*K#-"m=iH>Im$p^g)Mcd%CI`/W=g1LWQ[\sBquIY_B_(+!DK
Znr#8@%uIUG(a*P1FRq_6WK$i.>M2<$DC3J168686@8+sorc:,=nh;j_'5%/90<HYH>#2!rA
$:a0#H($r+J:J6HrH^,ia4sp'uE,?)AO;p\p!WEgCCOdUl9UW9ni%k#tDtT`m!d!SNJV#VU7
\RNk`.r<18[Q3<Yg::,]A>:7o;9pqjIO41a"-[Qo>R2JI*^ah\,@PU/I4l_bmu6[-Kl30%L"p
XYtlZS#,]AiJSbb#MMUm?"h>9EN"9uo6Ffl#XoW83#Ve=Xm&'8Q+k(="Q=qrCVkBI`cONY6Dl
3)46@6fd9HBXXW#>3<@"EVXH=:(^ZcYqA<B]A\')&OCWDoH,7q7Dbh#N+Ge^HG_#&qM@7PN/&
]AG[Z'H5*c;a7jG,"BY&U,.f_[dBnf(mM>lMk]A)eE'L6:,a<$<`ot,X<fTM1:5m]A:<91'^GHT
p>Inl'#o#gMh2NRaTQM2u,qP"I<o4dua#,$GBeF1]AqC&GUGE*6H/@UPS8$J_5`$r-AIqRZ_=
RK5a/O57U$oWgQqmANAE1kTNl*#YdWVQ7o<EUm"q?9n?NDGrV>Fk#X&l+)9)>f4CPOEL)/j/
081d<]A3OX4Rac8CV\f=06`<tZdu0*+tp',c1.3U28g_8Vr3!-!V0:aYb>6A"*!3c_PGg-m:[
V/0thuLO/pP4^qrBH/g#bh6EZ,H@*Up0KKaSJ`=boO@1b.BZ!7gdS=J]AB;U9k7/\k6l-FfO0
7md9-Y;D'NcgCniUp_%u]AFN&3!P@!+3<g(O+6\3o0$g:D(4kM>a(\Z(^:6L>$HFrKE\(u2>S
cHJ;s7J%Q%aoO&d=cX:P)PVcnK[9F#c[pnV,7G<GabIgi,1dK>9^0ADl5"1n9YGmGRCQ['>@
U8'eO0#['V=2g,_eNQL#`M'H^rf0h<nlH4%^6-qj0iqHF9HIc8um/mO1"Q&%/>qEgrQ;U#tP
/IJ@bk2SD)G+WnHS:f*SdPTaQ)NpoiFm$$5\5f;<GdfOdm]APLmHXVRCJlr0ZWNQU@kXL,n[Z
6es+a%G:EQ<U7LW9pYjniY-$3Fh5N>mpqS&?$Coe6\ftNA<.C(4h4ERZN7PdZ:CDh'$L'e'?
D$DYrp>+aeESWf*/="5c0-Z,r@>GMs.<jmW=uDA:HAhJ8@7k:cnX2L^\/m=Ns/oc.`3l6Vq+
598<,Fd6e"bpOQi-I$SV>WaL7b2KJak!MCmr$;.!Orh,CTM@b@-`c=&[TM**k?iM#nq).n?(
flRG+@G&)Y:""f2u1.qdfT56h$bT`mkn\K`+Rm?R(p1AsL@m.'=N,XY6Ap$i-(s*$tKO@_P,
jT>^SWBJ&%]A-tr^/[7Moipse<p'uQ,(N+FX=?3l9!6fDH\$>LFqe5:^VUsuT=p"SP^n2@r2_
bBaMrk1?H8V'W0Pu*i-Yc<*0pCfFp)OZ4<Zjk,5gaB8IDOA&`,pLB`5CZa`fQu(@#$06`"aQ
FYfjf]A+sgK>3&fc`i)0ck()a()4?*"DPmJNrHfC,H+KU*0BupheQ3RR?"4bTWDo6\O[l;$d4
I(aDXiX,lt7Pr0b?_7oP:?"C<\f=_LeTj[Hk/UI'C5pNH@n2GVWi>3Cn9I`7+\+[juO#N`=9
AF`7f6k6XV\U*GL[jPaJY8O`hh6h"//8I>R@]A[j6hnpmQO!3^C4b&WX23fkqPkfY,TrsChDV
i=^0&q"78A!,:EbQThbE*!>4.rj-+n6?)0VZ`#A8e)a_Qr9^Xf%cL#q2XJZA)UFsKhuX([El
o.8/c("^fP+W*6%V'jtK4@)E."",+('cqA"Zt1V+OVoHN:M1VGIY)%!P;'c[IT0T#T01.;@+
.d)CFH^c:>*$2#K]A3'nScegu,F%3ZI+tk'b'Ji<L2?TO+*k,5aTEc)qnf4]AABk-.D;%6C)75
J.ihJ%W&h9%G,i8+JfYX3sR78>)D`N7G%aJ/PnkS,4^CN*GUH=W(G.S2p84H@6'!:G\]AYY1i
13\2(YkeuXMA^*giEfm/p#?0@l_`UI.^t;p9jQS(U0I%g'Tg9Y,*-0BF'e\$E52%HVPGf:P;
jUJ$<9d&8g[bZ:#hCnsOr*r$gmglfe3KIUB"\b5JM=i7+>`UjEV'0/PKa\kH$;RFZ7lVV&=5
YG3NHuno^Kf`I6XioreNqK[7#Zo9%Z@<ASUtb74Z`^pJ-qJnT$7$Mt"c.KV)o_Cjm7"NDfr"
.tZDpdBWc1.&Dl2f^f=niYEGH""tnaiR'#1dTN/\6;g5\[ESaQBSR5[l.LoVa0S_P6Yc\[ki
Rg6H8^luFGkkU!.:<h7ClsWe<UC.)\`"kc4S@pPY\f7.A@d^GcRTg%4]AH.`g!R9:O,n/@LHB
\ClaBQISP4A`ZOs"`;S]AK,7BM6iUQu1G0W&7C3/^'$rue'XI_u:acWFHSZeSp?[DJ`TE+nT>
HQX@e#mfH:"VH2*@\"$@D7`ALqba/$QFNm5kk&D$ioMH4FD-=9S?S3h6>a#3]A!GRC]Atpe03n
U/(BbO:3g+leJ`QOgfPI`ipk11BULrF?gG.;QmZ]AI,F:Y7\^"8m:@k;j7b0FP)[:)p:@so"!
KBfQ(9PL]A`eZ/]AhP^<Gm.A7rD&p`HdC2O+GDY%#F@GPppUl%j3"T"u1\*PX7X$$Lak,P<i9>
`!7?p"AOAR*p)?Hp9^g.E6?323q.c(?%*HZC&f?S[>of!*D_cl`k=dLGYZ=7,uD5,DN19%m7
j6@PCT9\iQ,T+?r3Zh.lDT7R&[_PWW5+IHu#[)S]A6\p$;OE(_ci=BsInK"epF[aXnKkm$bn;
2R?['X_e,56*gS&;\ETT\'Y50N^aJ8]A<[Eg(J)rgr9@\#k?W3Zl>PfF_ZB1!/-ItIZ%45f3O
JXO?2S=e5DlWOWN5\CQ3JuLGonn9!JfRP]AfZ5@-]AJU^cF-V"dAK0Y4=Qida)OFf$K);'"Iak
=6aB<Y]Ah5fHhJ5gQ,<(_j=G-@!F^j@o+P,4e$'V#n$rRn#D4=X9'N#'2R)b-@Yruj#&G+=R;
SCup8E-CgP(VZKO]A!o-=A+HG#!Ga)oYZl10[g4AmK0e-(LkRd3ZU2VbdZB-4=/2@:d=j7b3Q
_<5F25pC+m.f4&Q'&Q9hU\/etSf*a;'46YMb1%]An)X^[Z,6V<Ur1BD0GlMX"QK)f4';ojS[#
&Hl\aF-?h,O=Yh`^l@W8rp;q$X4UI<6B,!8\,T0Cdg/g$/d,3C^,TU;nJfb@r4jA[rI(p'4[
:h5#!LV2isblqH[i`]AqI9053;B$KEArT0LjMuO-AoQkkaY$CVB`1*fHaT-7,Q/Bl9<@j]AuO"
02R5?'\T`Yqap7Tb`Fg^C^s/Qc.F]Ak(6&BYHp">QEV(X6I_XWPi0dj\:qMd%Um!&n**KRVMu
[>P,g]A"UI,0X8iIm$K>\#(fE9ShV+;n)C8Y,Q4F3fPt<hkHsQs]AQ7hCEtE+=U3\,FhKHDqqu
1H?*1RG=:&V4,t@eMXb!j5laL?VaS;Zb/:,?WVFjp7,)Zk,QKO178^uPWPTr1IXng"PU%a=)
Lj^$+!4e[4n4_/E#`e)Id]A-@ZGV#2A6gWkZmS.9T`XLVQAW9p!3dQ&?(aFRr02E%r9='k:k4
*aPLmG7=F.E`(`!;%%NeRR_%b3]AjZn/U%FEbK5'rb&#m,Vc"X<%P6=DGapaA^E8]A*'^Y5r,E
!3,W]A(c6$%"YQb6:s8T%:(:Y5]A_.4/8h!V?%DJVA;2O33W@b;;lE47+B&'T^iV'H*..<9`R5
@q@\\Gq&V/oZ$I!c$13u(f%aG*ebqTTB&dB/>3V$BmscDuerlHW7p"YeY&Ye:muDVTW\X1*!
T<BT+9Ie#X-94U7g#L=f>TAf*lfPrcYXrZGZkFc(+#Nm!?IjlQ[%MV1rO/J&Xh.Sl\^Kj/@<
8MN19ack$+_!HH[@)8%C]AKZ@',P_9!$D=CM'hb_2Q9_Mi!<+\E>Oe-4BV[e['Akb)'+O*9@$
;j%gU^Zi"KUN1h_s.D)(]A'HrRf"BkTh^1\e5]Aos3W+*B0q1b3la:)Ve3-0:28#DR<0rnRUeF
0ht$hbda#c?,FQ3g&<l1eWX7.p^E"4i5l(?*^E&>4Y8?(5Ise5+`A*#+969H#gGt%]ApCM41k
f9qS@O^mU>a%''CPUl[=_"P1uN8/$>e3L!\[l#>h\BLPqBp"`[Jh0p))T+<QfEHfbY=/7Z$F
=La02I57ArU*cF%KSX$:Cq%aFsm-<NP#@DT:/[)e]Ag%!rj,X3YFD/K<#J\RoA.m3NMpo5UOL
$u="auI8nLqKot_G.f&H*0-1bu*J>0(6k)GuC'EQ\g>"hSZ+r@C&,M!G^b_LgWdSn(mcl[rQ
'!6a8!X2h0f9)NT06hioZ.s3]A>Kd(/tASaR#ja,3c_/r+ojN5sA7)u7&;A&.bi05e[_m&TmX
6M!C(^X:C0c!EJWD$8n$-"5&`LBFEA20tDoBeeC&En"P0",e;*rgSk^1K.Y#UU"(]AW\#T@iK
^JTl%sMJU,09E=;DXWEd!\p/6XGS,8QGP&RT`PEfXDB6j%5o>HT:PbD6%CI3FE97^gEKicIp
YlejWEjgutp%[,RoA3JPPEXaWQ\1p=^1f/\"Xq#[tD>9T41`SHh/Ik:DZD&%N@'A-=ZH<eYk
,pZrRJGEtgpikK>1Z<_Bb^e5<>"$r]AQcD&<:6B>cJm/AMVKN1(^0aaahOEOR29*,4Co'1,I>
,1n3fKq'"B&Y*>Esd"[?CkU-7=FNeqT$hAGTXO6b3q`F-cu`:Q=$<Y[TaUOmu`OX"m64k1GT
rN_e5F,:dDKKEs(@Ej3Lo_[Ud5C$?uA`&]A^!=k%,dpj+C3I3`qV76[hBE9@LY^(>#/.D@7qD
6L0JBX6#_c1M5X8hasi=R\Z(]A5K!iiac5_`eTYqI.S/Z.$;IZsO3QFUI-<Kd]Ag7Tu?\)/U$F
7IOu\"6?,0$<Rq.E]A5Y9q-C=".kpclrUg;!Ef##quad-c[cHh'6LDi60))j%\BP_'V^T',"n
02$]A:1lM8ETEPFZl**[LJt]A-r)^$_+ma]AWSLP-2q:TsU]Am2GP9d?_2TPG<=eonkUq"1h(g#V
Yh+]A6QO7!e#./fJ$8Ir4UKTI]AP;m>.V%B/uGK>q<PF\]AJ=+F3mRXi&-j(Km*WumT2-]A_O;EN
GtVdU+^9;""7/bW*RIf)aSW:8Op.0_lRW=7=\JS:BUt6:2=SL"@Xh4-H1'jZh!&YJViLY^$d
'^oUBo.DcWNG:#KP'f$F)r*ebMrU"^c0VJm'FF&Tf>IiH$H7$b,l>34^Co17iB(AAInZG6j)
,&#h%QW8!(0)e`ISqmq%f<W/DY_k-9Y+j&r*UQ\u>lnN.<K(AR6%-Ad0Io;$DRWF*O6jr,6O
m&fr$X=,/8H.R@%8MC:Z<62<`7"0dgsN;+X]AIQs\UYBo^;T8k[0.P>lPJ.U=)Wp7d1$fiTAY
X%i,%nTs"r)m-Zbo\^G`Q6f;Er+02Ok<"LXWdo#:U9:nWq.4mW`ooC,^,O4#Ls(t2,\bP.J&
Vmu,9:uK+CVE(J/R9M*.7rkQ:<;iPP]A%1?jG8u6JK3GAVf`kfEUL/:`k(S<<*Lb=o^;>.Ud:
^`l'b9?YcHm`doW%:`Wk>jsf^N*)2Hf<q(Fq^"E*kh/qX);m,$-A87s(flA5KI/5p(5oM`GV
\LXRj]A+s)N0htDPEaSQD@2nBf^URq%F@0_Ee(q:Ct=I(@C(/).3I+@S[Q`C`0p)71;DWkA/e
8*N83_pm38pI/0l@C'r0B+Y.;FIb`VZlu!gZhT*X$'$N8uFV7\:q2;\_j4ac?U@(SOp"gftk
p==.k,`*OfBLKO3[3\6$Zsn-jL_`^1#tJiDT2P<If-,.rNQH+N1ubl=SWr@snE%^*CZ1E-J7
J!ubuNtbjeqCS2!<-S&,,+m'7Ms<Dq/Mo_3O7RHiHBf/Q:lk'Vif_aI9;8.batIe1ao,G/@7
sA6$Tt1I`'SX/]ArU#)khi#"Y1c3CqOU^3rRF%+g^VSHJ[:W\3[(=/1%^d"MHCoIpKs`6B<td
'b9`aRX[GJgfCA]Ae3tD7^Nc3ZKE6Wt^:S+0%C,Jg2V6bk'1/WNNU(/6W3)4$;>u0st2Cd@t4
*#M\MI'2F`>c)g4H90SW:!(Me;NH_l[MY6dc!4NIqb9+T'dG5'i+ZY!`Scn%AHhrLusQ+s+k
rl@aIV^GhbOjon4:S`h.6NC"*a4a2Q#7d]AV9H*]Apk^+.3"2qc*F$RR$uNHEWcCGPi\*;ltV`
;r^Xr<Jm`QU0JU/s0mNSKtZN24?78_k+>[LS,3calLi+lbq'Y#o7u1\k%DecaP^.T4ON[Pl2
?+-9goPgF'mbq!e&C-Z1Mos?Je\hIui%Iqu>0@>m0Z4l>qmB4]A2J3\.0M;7^k,mY.:P>?>Qh
FIEk(!fa'MEpLm,Z"LIoAo0gnh&ci*cTMm\Dp)eG4q7d3Zb=:&81"8tn`#4144.W,2h>a!H5
G.FX?H&a\208*^=3Y/.^IO$s2-eT`PlD^KlKI,[G5b7L>b=PHor/do,#+UP7:,/?bMAQNT[n
==#'_j%9ET(;ghXE^ogH31?$c6`!Q;,A.nVMN">V1WTJHO;C1%]AFBU%f=r:16!U$<qc*^0oE
([2t0nSl(.#O,L*3%Cba56[B3HZO2[M)pIP*ESkOS2fc60G/]AcIF4qK#I=1`=S:A\]A$@-tdM
WRB53"hD4H]A1cLu8@u=gqF\_o@dt>\e,\f-5&Opt?P[bZ\9GFXqNQ1OmQc::j%0]AL;o@=I&F
LXO0_sraAr3:Uj2)lIF;3LP"@bb"",Cr2Rkej.u5k$ka['*^d4YOJ354_'PK*C]A(B#OpZFXY
Y>h4M71Pq7^oal6h0CNnu0Z,'U_M/_J.&*,&d:5AEP-?l<3L!G@BjD]AZd(TkGiUr[)Lk!q<2
99179Bg6U?lAVMbokdokt'D7Ra37D:e")0*r[M5C+B/><U(I4"0\^eBHH/1c/5K#(DeT`NVH
WZ;PD-uC18Ho#1`#V_^g-6kMK_)1ISP>@CsRrXWqmr&7ljaXIHam=Y?Q3tUH=dU5kYEf3F-H
s@N9K?'XU=59QH?o;^'e>"b-C`*EoRZ!2IkJr)U,mpSPUkVpSgLur;&CuEXU'<M/]AaGR;%/+
;'LIsB*&b:)j'-8TO]Au0,W,;[]A_Ei3SIOXp[-El:L%j%lGUZ+-b0R72VJS\3M(3GD:Ln)bWm
tn4hL>!NW>Ac`.&t(qXG>uB7@Bn8DXDp.D0EG;a(Y!8kV2(pb5oQ@C"kgGq:M;DF5#"_MU/t
VlJ*Io._bi19,+AGUNILf>bB&=M>gDkML?,q<JFE6&8+h;K6#lNQ)n>\3cQ5[6!;oa7+2l6;
k+jK@N*j_cAC3oO?#RZD@77Pn'Pc8Djnu0Yd^s$6_)d.=dm;<rZVAQRiPHr+fU96@!L@ER4>
7C8-[temM/D[nYDSp`m's!u)]AJBHfOSOOXptZjo5EC_`;HSh1DZqse'VELWqe5;'17^#VcdN
h\R/;V.P]ABD5?k(@^=BF3r^(/aPXS='`2)="H=c'gAonoXGB\4SYR;/2FV0=^aQ!c/G&1@Wo
@JcgNI'KAc*!&'q:OXRQB6iI+r<AAUeso6V$osJTl$rq0Rj:<q(hSN-;(^.hM#!D(d]A9Gf%<
n!>r00_;mP@]A^DDX-iInZ"#dqjM9N#ghm!:@1;R&jqI8=?6&ggHOLDui*JPg!A!1baVqOo)q
<*MRmqfVk??!B[TnWU#q/PAWm"r<Cs`+%+3)Z.:8qfTVo[Y:XKVQJC<&;*l:I#mr?^6c(q;9
aO#iXVr#+DIWAVK2BWA\WnUJ77&tc]ApG*+kmXG*%H$&lXs7)/AbIt(/=OgBQ&S&O6tb,HMi/
8PSbSXg\%piHr#(Q<5h=^=MP__>4+XrOW<ahc;**]Ajj]A4+m9SSnY=2t<)__p:qlmM`a!f:+D
]Ae,#)J!?.,f<<:2"clbJW7E2ONbs?k=,3q@oYQr"/9c^Oeafq6sDuGPo*+p:Gg]A;:Va>QA<3
%g+3GkFa6sH>6BP&8VSTGM+Bs=V/[mZSgO-%#H=KWqPnme:pho:l,HK2dX#Q_W0']ALa%IM+"
`QPNPd&cU-Pm+#IB>SOE/T173.=6/EkP1+>H2<k4@h?F&/8V5\l^"ChSe$rJpZo3-bP9G4fC
pB'Uh/Q=q1A,e8&t,`S['>E="3r1B;]A:H]A="s1=j%cUc]AWU`T3FK2dQ13F_Pp'Q]AYRF1PBHG
/-uC@@J)kXsP[*FucL98Dc*%SA^]A-91'+]A^I,+Wd?1+e$(b]AXBjh$[K6btK+R<%s\*>,EU[/
-s!J'_=Kc(TI`5<3Km58pgoYIMe`G)Q'55Zo8,d2.Gd&*`Tm=?Z7>Um?Hokl5"JjJ-eCT7kh
<Fr5ejKh\+f#2TM9AmCtD8B9QIM#EXg]A#bQCsARcpVCo?(daS+B7bfbV1N3DjYYBI<2.8>;u
`5!j/OiPNKiGVn5Sf_)sF_t]AqV"9]A*Ek-9bd'-ud</GcG!*&:rLa75\r/H:PaY?^d0<s4!'o
k?/)r0^^l5HthGZj6\Q+<h#r,b,G<n"[l`hRWF%ga4uoiRub!@\hdhIPF.Tr<jMNMMR0h9>[
e5ITmtE0F2YSXEqE8X)pPMu,R?/Ap%QUhQM.JkaYs6?^:6<LPZ6<)nI3H\Ft`=,?a#Yp8t'(
4S&rWr/pi4@rH7$4TTJ4KY3U75HcoTnfZZ.]Ap!so4K-m;)$[q"TkE#ZOO#i8$Mh:2niBNP1&
jd*V9fA!7UhkmK63b.qTAaZ4pt^0>sg.\$Sr]A/V`E[+T&FmcW6?j#G4gQA2?YtbE"`V,[ob5
.Tob*^r'G3EZ:hT!GmW<giU:B&aUY4<^GYM6(8#:*d5+plicImL/Y4rj\(.P0-AC#QpJ.hee
:\Y.=Jb+mA$W(8!2Of(kj?$eAaQtQhoGk'GI>\@Y5'N]A$l>[S*@90jo%qBB"hC@>/#FeRh*V
!+0XN%r#f$_>1!S)LYAb<^/5]ANb!f9;Zd2p)TJU>hIep^k_9mYU)/+I(>n<7/QC$;<!@P.'`
VA",YU''JP6<(g_`qnQCtiEIprDYY$A\6G[(uC=-ssM^AOL_6K!,`"Gc^r!4`eb"?3@2b\qF
)$HG9d"d6CVYbLK'h$p9YD2-jQA8s;9!Y@dhjO9fQ0R,&TP5UpnVCC^*aZ.m5T,Pb/?Wklt?
l1F!3FPY;6)[?S2+oRG8`0MctN^Q/Lm0"mEOXULi*uG0.lL5^D<Z]Ao(D%W8i>Yc[Q^lN&7+6
K*biE7f`2)8HG4'4/C\pRoXMtEph&)OM4nerifRWBJ6^IrX2kC_&!3^-]A'n/220**L"S1V)e
HQfTl9VeCQMV1mt%!HEZE]A)/nW0=)cq##WbKkb:#;58.@dYe^`8VofYZk"t;U.+ReaO2M2N5
%(iqjB6`%*F%.`UH3]A/DhURI@'$O[YRWc8,D16u!>)Al#;+-:[,Xi7S6*kC_og^A6[<1RY,=
dL01$(oTjJ-:OMQ><)oaAkH^JpZL)n7=CRDL+ro%C=+hZV.$JuEh?Q+ZQ3qSZWH-8.g#:FF2
n!p=SB5&S@9UKtapA(9Y6aYeLXkXB;G.j4tOPOMX*GSh(!?RRH74R\B0<d6;XHuB.jW[0uAU
Eu0I-Zc@\GC8:5;Tb^;gpprSY[,P1C3o9h+"Y-nd?0EY:C:BJe_32@qhhpcJ"hadZ9EEF^B^
2Giqg#O?fB0$bP"N`#FK!EKO%5U8roP/a^nr+#A65SY(oSf0/oGSO;9UB?o6lh=iQ]A^^:g+D
F_mB]Ad(JYoP;u03)A@]AU.EkuOUQ011YY!oFqo(rdUVa3?@5R5&uC7iXEn+c.X#%dLY%T`IMM
K2b(NWX-g+5*GM"^$H>KOV8McfKmC[:OmF^P"2T"Xs/KR?9*60a3YrDF1-`]AZLIoa98KDB)E
Xb-7<od[>:rK?*XrNbMS?mM"I&`pHpEU/I"FDBnP[6B9!kK&sSFcBTGBCF\\DK#9/1TEJV>"
+rfWQNOt8DZ%!KEkGk@`KP=Ht.B?IfT~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[KmJ#,<:ATARDgm?1:E-iaPLAqM%n2W+_?lafge,t-m?eC6APuB@bO8:Ou5#G8n\PTCS[&RA4
1[f9RWMi\(C6I4jRJIk1o^=]AW_)&^9;lQ/7m.&@6kbs!!%[(bY@g5!"oG8LenKcJ!)GM'`n@
b_%rnYgU#MHp)QeI[S(8(4F"jBi/UbIi<Q.pcVD7BCKQ!]A5Y6b_&VElJS05d)0M"KN@tkh''
TIu?BeVu_DL"If*DNj`X(ttAX2?V87.`.Ydk9S-3G0=,qqNn#luW#;CXqYa`NUn7eNUYO_(4
.9VoIdNHoBW'Vto"Bcm.Kc2q@6cG:ZLW!5!Z*8kp3e$n#@8X=rcl\F([,i$5_BNe1=QqR%aO
Y5^kmeeC8PI*/0q;3W??b6rnIX=Z+:Ktc@_>@PbdGnSc*Hf<A=N,OI-r%8k_rGYP,L`!eM':
P2nDH`1HdlU&<#HJZ^gG%b^qZs\mpKoq!r<*tS1R\+r(`YE%>"T+HO0_dT&pF9*Vhd:;LjM#
6g$XDBA6\_%(Hej\H,t!L0?C89c)3XUL4W*M]A:.XqYlR(LKi#$&1EX]A@F'HL2!-dGY1e)8K>
*sZjB8e[E'(m.EH5'q@%-n!p<Y]AmBIkIbo"J7n>%EVeLpF)JKfnVdr8!;uWMn;/.A(7X9pjR
K"?ou+<6g9m%=\9_o+XI-"5c*K#-"m=iH>Im$p^g)Mcd%CI`/W=g1LWQ[\sBquIY_B_(+!DK
Znr#8@%uIUG(a*P1FRq_6WK$i.>M2<$DC3J168686@8+sorc:,=nh;j_'5%/90<HYH>#2!rA
$:a0#H($r+J:J6HrH^,ia4sp'uE,?)AO;p\p!WEgCCOdUl9UW9ni%k#tDtT`m!d!SNJV#VU7
\RNk`.r<18[Q3<Yg::,]A>:7o;9pqjIO41a"-[Qo>R2JI*^ah\,@PU/I4l_bmu6[-Kl30%L"p
XYtlZS#,]AiJSbb#MMUm?"h>9EN"9uo6Ffl#XoW83#Ve=Xm&'8Q+k(="Q=qrCVkBI`cONY6Dl
3)46@6fd9HBXXW#>3<@"EVXH=:(^ZcYqA<B]A\')&OCWDoH,7q7Dbh#N+Ge^HG_#&qM@7PN/&
]AG[Z'H5*c;a7jG,"BY&U,.f_[dBnf(mM>lMk]A)eE'L6:,a<$<`ot,X<fTM1:5m]A:<91'^GHT
p>Inl'#o#gMh2NRaTQM2u,qP"I<o4dua#,$GBeF1]AqC&GUGE*6H/@UPS8$J_5`$r-AIqRZ_=
RK5a/O57U$oWgQqmANAE1kTNl*#YdWVQ7o<EUm"q?9n?NDGrV>Fk#X&l+)9)>f4CPOEL)/j/
081d<]A3OX4Rac8CV\f=06`<tZdu0*+tp',c1.3U28g_8Vr3!-!V0:aYb>6A"*!3c_PGg-m:[
V/0thuLO/pP4^qrBH/g#bh6EZ,H@*Up0KKaSJ`=boO@1b.BZ!7gdS=J]AB;U9k7/\k6l-FfO0
7md9-Y;D'NcgCniUp_%u]AFN&3!P@!+3<g(O+6\3o0$g:D(4kM>a(\Z(^:6L>$HFrKE\(u2>S
cHJ;s7J%Q%aoO&d=cX:P)PVcnK[9F#c[pnV,7G<GabIgi,1dK>9^0ADl5"1n9YGmGRCQ['>@
U8'eO0#['V=2g,_eNQL#`M'H^rf0h<nlH4%^6-qj0iqHF9HIc8um/mO1"Q&%/>qEgrQ;U#tP
/IJ@bk2SD)G+WnHS:f*SdPTaQ)NpoiFm$$5\5f;<GdfOdm]APLmHXVRCJlr0ZWNQU@kXL,n[Z
6es+a%G:EQ<U7LW9pYjniY-$3Fh5N>mpqS&?$Coe6\ftNA<.C(4h4ERZN7PdZ:CDh'$L'e'?
D$DYrp>+aeESWf*/="5c0-Z,r@>GMs.<jmW=uDA:HAhJ8@7k:cnX2L^\/m=Ns/oc.`3l6Vq+
598<,Fd6e"bpOQi-I$SV>WaL7b2KJak!MCmr$;.!Orh,CTM@b@-`c=&[TM**k?iM#nq).n?(
flRG+@G&)Y:""f2u1.qdfT56h$bT`mkn\K`+Rm?R(p1AsL@m.'=N,XY6Ap$i-(s*$tKO@_P,
jT>^SWBJ&%]A-tr^/[7Moipse<p'uQ,(N+FX=?3l9!6fDH\$>LFqe5:^VUsuT=p"SP^n2@r2_
bBaMrk1?H8V'W0Pu*i-Yc<*0pCfFp)OZ4<Zjk,5gaB8IDOA&`,pLB`5CZa`fQu(@#$06`"aQ
FYfjf]A+sgK>3&fc`i)0ck()a()4?*"DPmJNrHfC,H+KU*0BupheQ3RR?"4bTWDo6\O[l;$d4
I(aDXiX,lt7Pr0b?_7oP:?"C<\f=_LeTj[Hk/UI'C5pNH@n2GVWi>3Cn9I`7+\+[juO#N`=9
AF`7f6k6XV\U*GL[jPaJY8O`hh6h"//8I>R@]A[j6hnpmQO!3^C4b&WX23fkqPkfY,TrsChDV
i=^0&q"78A!,:EbQThbE*!>4.rj-+n6?)0VZ`#A8e)a_Qr9^Xf%cL#q2XJZA)UFsKhuX([El
o.8/c("^fP+W*6%V'jtK4@)E."",+('cqA"Zt1V+OVoHN:M1VGIY)%!P;'c[IT0T#T01.;@+
.d)CFH^c:>*$2#K]A3'nScegu,F%3ZI+tk'b'Ji<L2?TO+*k,5aTEc)qnf4]AABk-.D;%6C)75
J.ihJ%W&h9%G,i8+JfYX3sR78>)D`N7G%aJ/PnkS,4^CN*GUH=W(G.S2p84H@6'!:G\]AYY1i
13\2(YkeuXMA^*giEfm/p#?0@l_`UI.^t;p9jQS(U0I%g'Tg9Y,*-0BF'e\$E52%HVPGf:P;
jUJ$<9d&8g[bZ:#hCnsOr*r$gmglfe3KIUB"\b5JM=i7+>`UjEV'0/PKa\kH$;RFZ7lVV&=5
YG3NHuno^Kf`I6XioreNqK[7#Zo9%Z@<ASUtb74Z`^pJ-qJnT$7$Mt"c.KV)o_Cjm7"NDfr"
.tZDpdBWc1.&Dl2f^f=niYEGH""tnaiR'#1dTN/\6;g5\[ESaQBSR5[l.LoVa0S_P6Yc\[ki
Rg6H8^luFGkkU!.:<h7ClsWe<UC.)\`"kc4S@pPY\f7.A@d^GcRTg%4]AH.`g!R9:O,n/@LHB
\ClaBQISP4A`ZOs"`;S]AK,7BM6iUQu1G0W&7C3/^'$rue'XI_u:acWFHSZeSp?[DJ`TE+nT>
HQX@e#mfH:"VH2*@\"$@D7`ALqba/$QFNm5kk&D$ioMH4FD-=9S?S3h6>a#3]A!GRC]Atpe03n
U/(BbO:3g+leJ`QOgfPI`ipk11BULrF?gG.;QmZ]AI,F:Y7\^"8m:@k;j7b0FP)[:)p:@so"!
KBfQ(9PL]A`eZ/]AhP^<Gm.A7rD&p`HdC2O+GDY%#F@GPppUl%j3"T"u1\*PX7X$$Lak,P<i9>
`!7?p"AOAR*p)?Hp9^g.E6?323q.c(?%*HZC&f?S[>of!*D_cl`k=dLGYZ=7,uD5,DN19%m7
j6@PCT9\iQ,T+?r3Zh.lDT7R&[_PWW5+IHu#[)S]A6\p$;OE(_ci=BsInK"epF[aXnKkm$bn;
2R?['X_e,56*gS&;\ETT\'Y50N^aJ8]A<[Eg(J)rgr9@\#k?W3Zl>PfF_ZB1!/-ItIZ%45f3O
JXO?2S=e5DlWOWN5\CQ3JuLGonn9!JfRP]AfZ5@-]AJU^cF-V"dAK0Y4=Qida)OFf$K);'"Iak
=6aB<Y]Ah5fHhJ5gQ,<(_j=G-@!F^j@o+P,4e$'V#n$rRn#D4=X9'N#'2R)b-@Yruj#&G+=R;
SCup8E-CgP(VZKO]A!o-=A+HG#!Ga)oYZl10[g4AmK0e-(LkRd3ZU2VbdZB-4=/2@:d=j7b3Q
_<5F25pC+m.f4&Q'&Q9hU\/etSf*a;'46YMb1%]An)X^[Z,6V<Ur1BD0GlMX"QK)f4';ojS[#
&Hl\aF-?h,O=Yh`^l@W8rp;q$X4UI<6B,!8\,T0Cdg/g$/d,3C^,TU;nJfb@r4jA[rI(p'4[
:h5#!LV2isblqH[i`]AqI9053;B$KEArT0LjMuO-AoQkkaY$CVB`1*fHaT-7,Q/Bl9<@j]AuO"
02R5?'\T`Yqap7Tb`Fg^C^s/Qc.F]Ak(6&BYHp">QEV(X6I_XWPi0dj\:qMd%Um!&n**KRVMu
[>P,g]A"UI,0X8iIm$K>\#(fE9ShV+;n)C8Y,Q4F3fPt<hkHsQs]AQ7hCEtE+=U3\,FhKHDqqu
1H?*1RG=:&V4,t@eMXb!j5laL?VaS;Zb/:,?WVFjp7,)Zk,QKO178^uPWPTr1IXng"PU%a=)
Lj^$+!4e[4n4_/E#`e)Id]A-@ZGV#2A6gWkZmS.9T`XLVQAW9p!3dQ&?(aFRr02E%r9='k:k4
*aPLmG7=F.E`(`!;%%NeRR_%b3]AjZn/U%FEbK5'rb&#m,Vc"X<%P6=DGapaA^E8]A*'^Y5r,E
!3,W]A(c6$%"YQb6:s8T%:(:Y5]A_.4/8h!V?%DJVA;2O33W@b;;lE47+B&'T^iV'H*..<9`R5
@q@\\Gq&V/oZ$I!c$13u(f%aG*ebqTTB&dB/>3V$BmscDuerlHW7p"YeY&Ye:muDVTW\X1*!
T<BT+9Ie#X-94U7g#L=f>TAf*lfPrcYXrZGZkFc(+#Nm!?IjlQ[%MV1rO/J&Xh.Sl\^Kj/@<
8MN19ack$+_!HH[@)8%C]AKZ@',P_9!$D=CM'hb_2Q9_Mi!<+\E>Oe-4BV[e['Akb)'+O*9@$
;j%gU^Zi"KUN1h_s.D)(]A'HrRf"BkTh^1\e5]Aos3W+*B0q1b3la:)Ve3-0:28#DR<0rnRUeF
0ht$hbda#c?,FQ3g&<l1eWX7.p^E"4i5l(?*^E&>4Y8?(5Ise5+`A*#+969H#gGt%]ApCM41k
f9qS@O^mU>a%''CPUl[=_"P1uN8/$>e3L!\[l#>h\BLPqBp"`[Jh0p))T+<QfEHfbY=/7Z$F
=La02I57ArU*cF%KSX$:Cq%aFsm-<NP#@DT:/[)e]Ag%!rj,X3YFD/K<#J\RoA.m3NMpo5UOL
$u="auI8nLqKot_G.f&H*0-1bu*J>0(6k)GuC'EQ\g>"hSZ+r@C&,M!G^b_LgWdSn(mcl[rQ
'!6a8!X2h0f9)NT06hioZ.s3]A>Kd(/tASaR#ja,3c_/r+ojN5sA7)u7&;A&.bi05e[_m&TmX
6M!C(^X:C0c!EJWD$8n$-"5&`LBFEA20tDoBeeC&En"P0",e;*rgSk^1K.Y#UU"(]AW\#T@iK
^JTl%sMJU,09E=;DXWEd!\p/6XGS,8QGP&RT`PEfXDB6j%5o>HT:PbD6%CI3FE97^gEKicIp
YlejWEjgutp%[,RoA3JPPEXaWQ\1p=^1f/\"Xq#[tD>9T41`SHh/Ik:DZD&%N@'A-=ZH<eYk
,pZrRJGEtgpikK>1Z<_Bb^e5<>"$r]AQcD&<:6B>cJm/AMVKN1(^0aaahOEOR29*,4Co'1,I>
,1n3fKq'"B&Y*>Esd"[?CkU-7=FNeqT$hAGTXO6b3q`F-cu`:Q=$<Y[TaUOmu`OX"m64k1GT
rN_e5F,:dDKKEs(@Ej3Lo_[Ud5C$?uA`&]A^!=k%,dpj+C3I3`qV76[hBE9@LY^(>#/.D@7qD
6L0JBX6#_c1M5X8hasi=R\Z(]A5K!iiac5_`eTYqI.S/Z.$;IZsO3QFUI-<Kd]Ag7Tu?\)/U$F
7IOu\"6?,0$<Rq.E]A5Y9q-C=".kpclrUg;!Ef##quad-c[cHh'6LDi60))j%\BP_'V^T',"n
02$]A:1lM8ETEPFZl**[LJt]A-r)^$_+ma]AWSLP-2q:TsU]Am2GP9d?_2TPG<=eonkUq"1h(g#V
Yh+]A6QO7!e#./fJ$8Ir4UKTI]AP;m>.V%B/uGK>q<PF\]AJ=+F3mRXi&-j(Km*WumT2-]A_O;EN
GtVdU+^9;""7/bW*RIf)aSW:8Op.0_lRW=7=\JS:BUt6:2=SL"@Xh4-H1'jZh!&YJViLY^$d
'^oUBo.DcWNG:#KP'f$F)r*ebMrU"^c0VJm'FF&Tf>IiH$H7$b,l>34^Co17iB(AAInZG6j)
,&#h%QW8!(0)e`ISqmq%f<W/DY_k-9Y+j&r*UQ\u>lnN.<K(AR6%-Ad0Io;$DRWF*O6jr,6O
m&fr$X=,/8H.R@%8MC:Z<62<`7"0dgsN;+X]AIQs\UYBo^;T8k[0.P>lPJ.U=)Wp7d1$fiTAY
X%i,%nTs"r)m-Zbo\^G`Q6f;Er+02Ok<"LXWdo#:U9:nWq.4mW`ooC,^,O4#Ls(t2,\bP.J&
Vmu,9:uK+CVE(J/R9M*.7rkQ:<;iPP]A%1?jG8u6JK3GAVf`kfEUL/:`k(S<<*Lb=o^;>.Ud:
^`l'b9?YcHm`doW%:`Wk>jsf^N*)2Hf<q(Fq^"E*kh/qX);m,$-A87s(flA5KI/5p(5oM`GV
\LXRj]A+s)N0htDPEaSQD@2nBf^URq%F@0_Ee(q:Ct=I(@C(/).3I+@S[Q`C`0p)71;DWkA/e
8*N83_pm38pI/0l@C'r0B+Y.;FIb`VZlu!gZhT*X$'$N8uFV7\:q2;\_j4ac?U@(SOp"gftk
p==.k,`*OfBLKO3[3\6$Zsn-jL_`^1#tJiDT2P<If-,.rNQH+N1ubl=SWr@snE%^*CZ1E-J7
J!ubuNtbjeqCS2!<-S&,,+m'7Ms<Dq/Mo_3O7RHiHBf/Q:lk'Vif_aI9;8.batIe1ao,G/@7
sA6$Tt1I`'SX/]ArU#)khi#"Y1c3CqOU^3rRF%+g^VSHJ[:W\3[(=/1%^d"MHCoIpKs`6B<td
'b9`aRX[GJgfCA]Ae3tD7^Nc3ZKE6Wt^:S+0%C,Jg2V6bk'1/WNNU(/6W3)4$;>u0st2Cd@t4
*#M\MI'2F`>c)g4H90SW:!(Me;NH_l[MY6dc!4NIqb9+T'dG5'i+ZY!`Scn%AHhrLusQ+s+k
rl@aIV^GhbOjon4:S`h.6NC"*a4a2Q#7d]AV9H*]Apk^+.3"2qc*F$RR$uNHEWcCGPi\*;ltV`
;r^Xr<Jm`QU0JU/s0mNSKtZN24?78_k+>[LS,3calLi+lbq'Y#o7u1\k%DecaP^.T4ON[Pl2
?+-9goPgF'mbq!e&C-Z1Mos?Je\hIui%Iqu>0@>m0Z4l>qmB4]A2J3\.0M;7^k,mY.:P>?>Qh
FIEk(!fa'MEpLm,Z"LIoAo0gnh&ci*cTMm\Dp)eG4q7d3Zb=:&81"8tn`#4144.W,2h>a!H5
G.FX?H&a\208*^=3Y/.^IO$s2-eT`PlD^KlKI,[G5b7L>b=PHor/do,#+UP7:,/?bMAQNT[n
==#'_j%9ET(;ghXE^ogH31?$c6`!Q;,A.nVMN">V1WTJHO;C1%]AFBU%f=r:16!U$<qc*^0oE
([2t0nSl(.#O,L*3%Cba56[B3HZO2[M)pIP*ESkOS2fc60G/]AcIF4qK#I=1`=S:A\]A$@-tdM
WRB53"hD4H]A1cLu8@u=gqF\_o@dt>\e,\f-5&Opt?P[bZ\9GFXqNQ1OmQc::j%0]AL;o@=I&F
LXO0_sraAr3:Uj2)lIF;3LP"@bb"",Cr2Rkej.u5k$ka['*^d4YOJ354_'PK*C]A(B#OpZFXY
Y>h4M71Pq7^oal6h0CNnu0Z,'U_M/_J.&*,&d:5AEP-?l<3L!G@BjD]AZd(TkGiUr[)Lk!q<2
99179Bg6U?lAVMbokdokt'D7Ra37D:e")0*r[M5C+B/><U(I4"0\^eBHH/1c/5K#(DeT`NVH
WZ;PD-uC18Ho#1`#V_^g-6kMK_)1ISP>@CsRrXWqmr&7ljaXIHam=Y?Q3tUH=dU5kYEf3F-H
s@N9K?'XU=59QH?o;^'e>"b-C`*EoRZ!2IkJr)U,mpSPUkVpSgLur;&CuEXU'<M/]AaGR;%/+
;'LIsB*&b:)j'-8TO]Au0,W,;[]A_Ei3SIOXp[-El:L%j%lGUZ+-b0R72VJS\3M(3GD:Ln)bWm
tn4hL>!NW>Ac`.&t(qXG>uB7@Bn8DXDp.D0EG;a(Y!8kV2(pb5oQ@C"kgGq:M;DF5#"_MU/t
VlJ*Io._bi19,+AGUNILf>bB&=M>gDkML?,q<JFE6&8+h;K6#lNQ)n>\3cQ5[6!;oa7+2l6;
k+jK@N*j_cAC3oO?#RZD@77Pn'Pc8Djnu0Yd^s$6_)d.=dm;<rZVAQRiPHr+fU96@!L@ER4>
7C8-[temM/D[nYDSp`m's!u)]AJBHfOSOOXptZjo5EC_`;HSh1DZqse'VELWqe5;'17^#VcdN
h\R/;V.P]ABD5?k(@^=BF3r^(/aPXS='`2)="H=c'gAonoXGB\4SYR;/2FV0=^aQ!c/G&1@Wo
@JcgNI'KAc*!&'q:OXRQB6iI+r<AAUeso6V$osJTl$rq0Rj:<q(hSN-;(^.hM#!D(d]A9Gf%<
n!>r00_;mP@]A^DDX-iInZ"#dqjM9N#ghm!:@1;R&jqI8=?6&ggHOLDui*JPg!A!1baVqOo)q
<*MRmqfVk??!B[TnWU#q/PAWm"r<Cs`+%+3)Z.:8qfTVo[Y:XKVQJC<&;*l:I#mr?^6c(q;9
aO#iXVr#+DIWAVK2BWA\WnUJ77&tc]ApG*+kmXG*%H$&lXs7)/AbIt(/=OgBQ&S&O6tb,HMi/
8PSbSXg\%piHr#(Q<5h=^=MP__>4+XrOW<ahc;**]Ajj]A4+m9SSnY=2t<)__p:qlmM`a!f:+D
]Ae,#)J!?.,f<<:2"clbJW7E2ONbs?k=,3q@oYQr"/9c^Oeafq6sDuGPo*+p:Gg]A;:Va>QA<3
%g+3GkFa6sH>6BP&8VSTGM+Bs=V/[mZSgO-%#H=KWqPnme:pho:l,HK2dX#Q_W0']ALa%IM+"
`QPNPd&cU-Pm+#IB>SOE/T173.=6/EkP1+>H2<k4@h?F&/8V5\l^"ChSe$rJpZo3-bP9G4fC
pB'Uh/Q=q1A,e8&t,`S['>E="3r1B;]A:H]A="s1=j%cUc]AWU`T3FK2dQ13F_Pp'Q]AYRF1PBHG
/-uC@@J)kXsP[*FucL98Dc*%SA^]A-91'+]A^I,+Wd?1+e$(b]AXBjh$[K6btK+R<%s\*>,EU[/
-s!J'_=Kc(TI`5<3Km58pgoYIMe`G)Q'55Zo8,d2.Gd&*`Tm=?Z7>Um?Hokl5"JjJ-eCT7kh
<Fr5ejKh\+f#2TM9AmCtD8B9QIM#EXg]A#bQCsARcpVCo?(daS+B7bfbV1N3DjYYBI<2.8>;u
`5!j/OiPNKiGVn5Sf_)sF_t]AqV"9]A*Ek-9bd'-ud</GcG!*&:rLa75\r/H:PaY?^d0<s4!'o
k?/)r0^^l5HthGZj6\Q+<h#r,b,G<n"[l`hRWF%ga4uoiRub!@\hdhIPF.Tr<jMNMMR0h9>[
e5ITmtE0F2YSXEqE8X)pPMu,R?/Ap%QUhQM.JkaYs6?^:6<LPZ6<)nI3H\Ft`=,?a#Yp8t'(
4S&rWr/pi4@rH7$4TTJ4KY3U75HcoTnfZZ.]Ap!so4K-m;)$[q"TkE#ZOO#i8$Mh:2niBNP1&
jd*V9fA!7UhkmK63b.qTAaZ4pt^0>sg.\$Sr]A/V`E[+T&FmcW6?j#G4gQA2?YtbE"`V,[ob5
.Tob*^r'G3EZ:hT!GmW<giU:B&aUY4<^GYM6(8#:*d5+plicImL/Y4rj\(.P0-AC#QpJ.hee
:\Y.=Jb+mA$W(8!2Of(kj?$eAaQtQhoGk'GI>\@Y5'N]A$l>[S*@90jo%qBB"hC@>/#FeRh*V
!+0XN%r#f$_>1!S)LYAb<^/5]ANb!f9;Zd2p)TJU>hIep^k_9mYU)/+I(>n<7/QC$;<!@P.'`
VA",YU''JP6<(g_`qnQCtiEIprDYY$A\6G[(uC=-ssM^AOL_6K!,`"Gc^r!4`eb"?3@2b\qF
)$HG9d"d6CVYbLK'h$p9YD2-jQA8s;9!Y@dhjO9fQ0R,&TP5UpnVCC^*aZ.m5T,Pb/?Wklt?
l1F!3FPY;6)[?S2+oRG8`0MctN^Q/Lm0"mEOXULi*uG0.lL5^D<Z]Ao(D%W8i>Yc[Q^lN&7+6
K*biE7f`2)8HG4'4/C\pRoXMtEph&)OM4nerifRWBJ6^IrX2kC_&!3^-]A'n/220**L"S1V)e
HQfTl9VeCQMV1mt%!HEZE]A)/nW0=)cq##WbKkb:#;58.@dYe^`8VofYZk"t;U.+ReaO2M2N5
%(iqjB6`%*F%.`UH3]A/DhURI@'$O[YRWc8,D16u!>)Al#;+-:[,Xi7S6*kC_og^A6[<1RY,=
dL01$(oTjJ-:OMQ><)oaAkH^JpZL)n7=CRDL+ro%C=+hZV.$JuEh?Q+ZQ3qSZWH-8.g#:FF2
n!p=SB5&S@9UKtapA(9Y6aYeLXkXB;G.j4tOPOMX*GSh(!?RRH74R\B0<d6;XHuB.jW[0uAU
Eu0I-Zc@\GC8:5;Tb^;gpprSY[,P1C3o9h+"Y-nd?0EY:C:BJe_32@qhhpcJ"hadZ9EEF^B^
2Giqg#O?fB0$bP"N`#FK!EKO%5U8roP/a^nr+#A65SY(oSf0/oGSO;9UB?o6lh=iQ]A^^:g+D
F_mB]Ad(JYoP;u03)A@]AU.EkuOUQ011YY!oFqo(rdUVa3?@5R5&uC7iXEn+c.X#%dLY%T`IMM
K2b(NWX-g+5*GM"^$H>KOV8McfKmC[:OmF^P"2T"Xs/KR?9*60a3YrDF1-`]AZLIoa98KDB)E
Xb-7<od[>:rK?*XrNbMS?mM"I&`pHpEU/I"FDBnP[6B9!kK&sSFcBTGBCF\\DK#9/1TEJV>"
+rfWQNOt8DZ%!KEkGk@`KP=Ht.B?IfT~
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
<![CDATA[5562600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[1790700,2743200,2743200,2743200,2743200,2743200,1066800,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" cs="5" rs="4" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="0">
<O>
<![CDATA[ ]]></O>
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
<FRFont name="SimSun" style="0" size="72" foreground="-65536"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9C*"'A'[a(dtK&DTf`$W$SV0'm82e#lYQ0U:#%?JS=GOYLdO%[2&VUW)sYLA7BDZ[8k]A,=q
m'T(R<b<;q.O=#m?mOpr`e;5lsME*.ihL$2L3]A2aOH/_p#bt$@3X-CUmKOkNmWBrm&!fIEoW
ofl8bG0+<4/!!*&`J!p-C!;K(cT[t!.IE>:]ANnW\-!<6%\IHom;f4WZ99jX7`rqB(JB9[#`b
EuHQZ?c.qI^<#^F5Qo.Ca?dibbCG:r;G]AbIIEC8gVGi:r'tJGMm__SM2j>e)Hg&G&re6#MQl
[XeP;7Ki)&EZP7f$T6:*7nmC!_A1!/CU"s":`3]A@g<+=PL^Q^X6NE`P*:&4Q^GLuSj>n?<<u
);6Wl;/ft9Yuq^B,R\X(iqRi0bp29T($E-Ao#\O#jVDnD.W?jrI'hXnlA?d%P;')#gWk?pD]A
@o\h^&$iW'R&8^m:5I$Y/Q<iOgZ5`I&G:94MA$_,U0Ra%`F#PH,TZ>Lh"7f2&Nh>J^Ai8&+X
CEb&FR!kg>\npRk`[AsnQ;.aYSoOmL.qSg68F5YV;!=-R7OFo:F?UWD"8HtOH36!>pH>K]A87
hoj"$Ic"/[Bl0oq@GVl[5-"[n%>$T"G++;nUH6GD@82fY'<e&G,jY-X`YL)rd<6`;dG+\Ze9
O6;*m@Yd'24QL0QM9*L-f\qE5oQK1D)#V.cdD`3KaCQ/%'nBRt!Ga@#%gEA2^sPE]A@Apj5+l
E/==SEu'P5JP:YM\-9HXi3AeU:*7O1m%s-G6qrm20I!t`2(g.JcZu=,'#MR)E;oJ5_'.($12
\G_i'>ZZl*6CkH]AdWRO&f#,,9a#$Ql#h_4(YW2ZrG'bd\M5+M@rLanLA'VE1g-t)"Bsr*\63
</UTF;DE_g@g$G&ICo@Yu]A`S;_'7RH=FrrZRFC;3,oMBL>q!OXAl2A]A9+pEf85CH"Q!Hf8g/
#MgrQh&e:f*-X1_0f9_U^:\u_[g%sj,+&[er<l@Ug%SeV7psPJL)=L-/?\3/C5cP)*-J[4Lf
Yt(m;%!"6N9X/P$?jJrAQ7\#3%Xe%4$L:bSP\:e:2\E\oLtH@^FnESX/_Kc8VlgS7doo8$f/
-2G'_S4C]A2IsKJ&do!I:OsV.uq2QC"Xk>De&/#T=:h&OuY:h-PKRM$8c6.:5e%pJUrfXLP`M
/R9ffZc)i6O'iI80uB2[[(]Ag/5fV&/Q9=#0`A07L/:!<5pPOfIp1&.e\Rpg4_`9IcE5eL_j3
<%;)"Cd$h5!_\_^%N#fYLJ^f;J_j+opNg>nZ?^$4\RiKM5KLDm;RfA-KMqF(i[3,6+]AJ$F20
sI?R-T#EpX>d1Dj!O4cGEK4.5b\E4JITp!H'%8\Bo's1rd&s&U;:rGjIh=QP!eU@]A7O9Q,NM
``^=Rq=e7KaW:SjL-Fp9d!-XQUZ$Cn;MG_30uaOu"%P62i.#+[kO+)qS.;lN?--5-n*^dR">
nD70s8?\k7i<Bpob;./?1iQ)$C=8o_Ge`i4]AuSs"f+0uoCTXCLaA_ZNisKe@+`,Eo:.^"abV
eU1_37T=ii%g$TFm,C&Ik2rV;0kapHs0F*l=,Z6',!kQC613M2TL#,DR);&3FW$3OCn<KE9_
*X-(2W=_PsATA0<e"q`T)e<t^N`WIgYE4m.K3RPs`?j\d\a76_W=+0EcJ):>iI=>24ra7Z8i
=ul^pc"B;R(!2hJ,>S[cRr"Q/D:se>uK[)kB5s1kB5s1kB6Ofl4+U)o+fu[J5=R(:EIoiC^o
S8"'B%kH5\cnpR,/'ZrGSj4?c6KD,,&JDXH'9!O*njCXWmm3pES^i9EN0m50N$k_rV@ptD93
If(:'J(_`PO<Nn1E\(Gd\qe#aJ)*HQ$4u`mLL/6kcKidAcKidAcKjnBm1(jaXW/DP+@#"S$e
U0i+BCU^6;6I$1(f@mX9ie/$&Ji=FJ(]A>jTJ4X.^TbRr<FJ>,>[;I^n39;JW[)46.o-8P4qp
G):13V&YMhl,^$WW!-"K5>s4YGW[C@o=4PnBYNCr*6(9u"?YEaU"A+1;_`YM:%r?4]AnH%a<*
aN8_nXY+rJ;%(<M!9*$R.c<@_TAn*n17Igq_O.\>t(@_F4pTIS8<,ecZV>uaWOgNl^q5AF+r
rj7Qof!cRI4t=/3?+l\GrZ?rB$ns45XeKu*3O(g`>l%`)(#h:%=e2268A'Yi*gMUukkpYJUD
s0c\D!IWR+249PY*rWglf&UGd=FI%;4=5,j+1VGm5O=5EeAbguFP]A&a$ghL2n!%T-iDQh"r3
V^OTi6CIQ$-fc%rWCTTsLi?Nt,Jr_"(N-3DNdgJnKfLBNgsMas"OJ+uR2bogL]AWQS&2SF.8O
ZVWSP!rm)A&_0fG$k`i_//_p6:5.i2*c@2mfH3@\*kkhcLrC&")==5^'kOu:&kqPsc*)8;tD
l_'3HZ)_@.YG[M!>VfhOB<D"1G<p/VWYg*V65oafdH!X^MJPM/F.",]AIe$l8jV)*L`Qnc<%h
6%'uYFnB\9/fFBDk!ec(EMI\gs("5AKepA0$X`<>ld[F2LI!b(:qp=H">l8NB1It\*)DEa21
9'']AgWrF\l6O<?!93-ja`LuAkLk#mGDfY/&SX/j0_BR*9_:4:`hD",t/8Gu5A^N34m>C:FW)
dQsHR;/0>up@(Hn<N'LRoWN1bSY,2&+iR4oRR6hf@(7#mhrO=^E)Om[CDl_?)TC<BRqUI+/e
]AqF.=\_=M*/&Et=(/&N5%PGo!V`Dre.[es1"k=VJsHRQh`StZcFV_;H5]AEcU25]A1hM/kJs7c
g,]AM+ii45_@*Z$hAuQ&o]AedjACCl"oO_]AK\[U(4DlJe4.J9[!8q"S,PXn=0&t^f(A)(_tZs5
4m+@l[S5e`1"ZC,B-D"Jq/+UqCa(-5bc_IQ"LRYNJ(Zmtp?7GPlSMC+9_%mMQ[S[D<t8@sa!
B5O_&l(JYehBZN&3799VL;p<!oZ:?Z`a3f(<%l]ATJ1_WGiqj2lINE5Ha0D:.1;RBl]AMh.Jcm
[j&gac+2=dlK>>C6a>VR.70$nD+QH^-<g3r`k<kVP&naB[G.b+Kr&MZpCh1>mst0d>3-OUOX
hR_'"<=Q,OEh&<#\O'HH3'&P_bdC7$XP9^u.;)KaJ$Yu9Y+JCeXGLs6ZW\^%llt-!IB0F,)e
><5+$aq;a"&&NANVGa&RkI_Fq9.e9#`A[\=K&ZL<>'/-A<`(i^e&LMWHVF6gH@>Sq8n%Z@p@
MiBPi#pkfN+l4"]AL6%eNSRV6&Bnc8*pu4Af_(6=98+oIi*tbDM'[2sG0Rq[pV`"kGC>N_t?*
jY=M-I3Ub#L8ar<"OMaD&Ek%RVi!D`F<Zt-eZ[f4?]AE".Ai,kUq0]AEHJW5J@$F!Gq#$Rb_QN
7"Mfd6IcAYO,Dqb^/FT4&5;Y8eHVH)g!W=6)+bfVAj^,%baT5EVi>C3UUqe8!<TjP'L4*3=;
)iLImFPeiH\.XQSJ`9)s3'1HGs=r(&j6Qt`G[J<gIND*DuN5#5HhdFUaLu,^Q@S3_+R?VHO:
]A5/XG,43?AXS\Z<7_Li^jM'<U7[:eO&6Qk=2rsuKYac[V+BM1<<YOZ$8CEW_ICJ7EuFN8m6N
06lpUbJ#&U\<:ZD*M_cs5ipT54-E*_aXX/M2=QuUm),!S9]A!Q>IN0$rhdXUtuq-"PWEYuK16
V7ZH*5Q.>(]A6mX1@'.;ePMfN+AbXoKb+]A%Kp3(f=Ya307NpWMOF4_C`,o=.KBdM]AXoXMKbr*
UpW`i=`*dLkjM=PP2mm%h]A+5B<=CG0b.kf:4KGYg,H8SR\lGO8d7JK^ek"6;XRO5Gr/NG9M>
TK@1^]AlMp0Gs7Y`(f-b4SK^?EP1Ge\n"2&i`^T57)RBKk]A)H>"4`aVo]A)DNJX\skClAa_qlr
KaI5\T+'C[[!3'jO!V69HJi8]AC'sRXt)%Q+90`L6Lc/P8D5KP-V4,Yg3W<)_YYqPQjLF/L#V
@i5t^\cHWBH<*M1e-n$]Akt68NhaN:fU]A)VKQ)c=4S??DG*$_VUTr?j<0D\P()p!mq,N9;5>(
COcjhM%sTB+ilNj`@&)VO,"!AN(4_A-EbT/9o!#N:j4U*B<:V-n%G>nmXj^R>YR2&mZn;#5!
ST-ei&DTs*7;q.*a"*->O75&VDrS@QG=!$1M$L%!V<L"Z@\p`IJgLkCnN%gCGDuSm`(ELj\9
(W/]AQ/prXb(QAsk(,/C9T=E4\kC3]Ai]AF74![]A3d$]A-9kKS74%)\i_GAep$]A^KLMXH[mj'lei
]AW0D<F>>[4Hm]AA8b@hfDf@7dG(sn?DTo""D6WYUTamR1f'q,OVU3p'/"]A0*\'J2=Rk.J_V/!
;$gAL='kJ)HDbC>?;n@UPK[6Z/Ze_s_ELh7Ep]A6eSs)^dN??iTRhH`eT+I^k-0ZYD#K`/)l8
J;TA$3(I$H0m:G_daP1<UAM!Ir"Wk<+K;2)k/SQ4)Oh]A^I#mrsT-g8A&e=p,\q5RpH'kob1T
>U3R[F2UP=&u&LuEDb--d=JV[d3Kc*<$3No*jM7lY:+k^e`rb#fm#4Z6$t>(`VRM^a9oje0s
1>;adPo(Nm0:O0RF_j\QqmpKp3%u.2f;!Y3bCXQLS5<Vk%Glp*(g(nZ"J4ssk!0hm/iHM+Tj
-$t*TdtN52]AgOdY)]Ao5>a'$g,#ni4g"G5\n2q[%R%5+h!dd-\#DS96r=Ig:m"OgpN4WH!M4e
"<.-rh*N2mj9G%kZSG2C[t\?H&9KNKk4C+XS*;%1,#@M9@4/pcFI3MBfl[d\81pV.;"P*F,+
!XJt-Y'-SM"j0ELA4`u5c;0srl!t$F.)=^I]Aqu]AKD2Blj3<RIja["CL'\>ufW*BRgSg^'Db[
['[H7Dn*?EW*X!,PBtIO^E@A7,GJ&o\O]AH!CHA/eG'7(XA#]A\L8(SD$.]AsW&:=ir?68-5(F^
4*;8`)1b7q5ru94+$3NJi$&<'f?Q[A_Znd?cW0E9dMVA?B=O>@hHe(Z!8P]AN"#'b39[HXmuq
e"$A3'*e8q(sP+U!gKAXYCm\!m:0FP"I&tnnA%_m*B%FC6M:>17;SjVQk15$Q*hrMBc#V>F=
pNIGeD&^3[;REjWEGEoJHm>^&c2\d-W%F\T1CM_kqB1?f*QSYo/4D4TkG!*tbDSdKG@FF1Di
.>FnmMJ*4%n8e,QFleem8'.<tj^"VrQH*De*Jk)j2CXrhX98sQ4:(hSG!);@:bb)l/-6g3WB
cQ!^F2=&q"%L"'HmfijNl:XX!=H!b&VKlfN738#sFspP+.f]AR&L5UC(sM[3cn%`7:?lAD48Y
#dM[kdit!W1><%_*+#Wj0(Vi4/0h'SOH8JRG!LM/h!2dP3,TFpY):3H)JZ!E-^gIh!7Fo$Vh
h+:;77LPiB27S&m]AAnN_!RX6C8eRaI*K.T!k]A&F*NV^HJ.h$c"bWXIH[)rk"Pp18hIIc-?se
1Q26rX$"sI+H"n7c786-g_r&=TA\>b7]A/Gf#=!!~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="288" height="250"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0_c_c_c_c_c"/>
<Widget widgetName="chart3"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1536" absoluteResolutionScaleH="864"/>
</InnerWidget>
<BoundsAttr x="667" y="282" width="288" height="258"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WAbsoluteLayout">
<WidgetName name="absolute0_c"/>
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
<Attr alpha="0.98"/>
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
<Attr lineStyle="0" isRoundBorder="false" roundRadius="3"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.chart.base.AttrAlpha">
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
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
<HtmlLabel customText="function(){ return this.category+this.seriesName+this.value;}" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<FRFont name="宋体" style="0" size="72"/>
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
<OColor colvalue="-16711681"/>
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
<AxisLineStyle AxisStyle="0" MainGridStyle="1"/>
<newLineColor lineColor="-2039584"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr rotation="-63" alignText="0">
<FRFont name="微软雅黑" style="0" size="64" foreground="-2039584"/>
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
<FRFont name="微软雅黑" style="0" size="64" foreground="-2039584"/>
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
<VanChartColumnPlotAttr seriesOverlapPercent="0.0" categoryIntervalPercent="100.0" fixedWidth="true" columnWidth="10" filledWithImage="false" isBar="false"/>
</Plot>
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[日销表]]></Name>
</TableData>
<CategoryName value="地级市"/>
<ChartSummaryColumn name="月营业" function="com.fr.data.util.function.NoneFunction" customName="月营业"/>
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
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
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
<FRFont name="宋体" style="0" size="72"/>
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
<Attr rotation="-63" alignText="0">
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
<newLineColor mainGridColor="-3881788" lineColor="-5197648"/>
<AxisPosition value="2"/>
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
<ChartDefinition>
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File3]]></Name>
</TableData>
<CategoryName value="地级市"/>
<ChartSummaryColumn name="营业额" function="com.fr.data.util.function.NoneFunction" customName="营业额"/>
</MoreNameCDDefinition>
</ChartDefinition>
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
</body>
</InnerWidget>
<BoundsAttr x="14" y="45" width="268" height="193"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0_c_c_c"/>
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
<WidgetName name="report0_c_c_c"/>
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
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[KmJ#,<:ATARDgm?1:E-iaPLAqM%n2W+_?lafge,t-m?eC6APuB@bO8:Ou5#G8n\PTCS[&RA4
1[f9RWMi\(C6I4jRJIk1o^=]AW_)&^9;lQ/7m.&@6kbs!!%[(bY@g5!"oG8LenKcJ!)GM'`n@
b_%rnYgU#MHp)QeI[S(8(4F"jBi/UbIi<Q.pcVD7BCKQ!]A5Y6b_&VElJS05d)0M"KN@tkh''
TIu?BeVu_DL"If*DNj`X(ttAX2?V87.`.Ydk9S-3G0=,qqNn#luW#;CXqYa`NUn7eNUYO_(4
.9VoIdNHoBW'Vto"Bcm.Kc2q@6cG:ZLW!5!Z*8kp3e$n#@8X=rcl\F([,i$5_BNe1=QqR%aO
Y5^kmeeC8PI*/0q;3W??b6rnIX=Z+:Ktc@_>@PbdGnSc*Hf<A=N,OI-r%8k_rGYP,L`!eM':
P2nDH`1HdlU&<#HJZ^gG%b^qZs\mpKoq!r<*tS1R\+r(`YE%>"T+HO0_dT&pF9*Vhd:;LjM#
6g$XDBA6\_%(Hej\H,t!L0?C89c)3XUL4W*M]A:.XqYlR(LKi#$&1EX]A@F'HL2!-dGY1e)8K>
*sZjB8e[E'(m.EH5'q@%-n!p<Y]AmBIkIbo"J7n>%EVeLpF)JKfnVdr8!;uWMn;/.A(7X9pjR
K"?ou+<6g9m%=\9_o+XI-"5c*K#-"m=iH>Im$p^g)Mcd%CI`/W=g1LWQ[\sBquIY_B_(+!DK
Znr#8@%uIUG(a*P1FRq_6WK$i.>M2<$DC3J168686@8+sorc:,=nh;j_'5%/90<HYH>#2!rA
$:a0#H($r+J:J6HrH^,ia4sp'uE,?)AO;p\p!WEgCCOdUl9UW9ni%k#tDtT`m!d!SNJV#VU7
\RNk`.r<18[Q3<Yg::,]A>:7o;9pqjIO41a"-[Qo>R2JI*^ah\,@PU/I4l_bmu6[-Kl30%L"p
XYtlZS#,]AiJSbb#MMUm?"h>9EN"9uo6Ffl#XoW83#Ve=Xm&'8Q+k(="Q=qrCVkBI`cONY6Dl
3)46@6fd9HBXXW#>3<@"EVXH=:(^ZcYqA<B]A\')&OCWDoH,7q7Dbh#N+Ge^HG_#&qM@7PN/&
]AG[Z'H5*c;a7jG,"BY&U,.f_[dBnf(mM>lMk]A)eE'L6:,a<$<`ot,X<fTM1:5m]A:<91'^GHT
p>Inl'#o#gMh2NRaTQM2u,qP"I<o4dua#,$GBeF1]AqC&GUGE*6H/@UPS8$J_5`$r-AIqRZ_=
RK5a/O57U$oWgQqmANAE1kTNl*#YdWVQ7o<EUm"q?9n?NDGrV>Fk#X&l+)9)>f4CPOEL)/j/
081d<]A3OX4Rac8CV\f=06`<tZdu0*+tp',c1.3U28g_8Vr3!-!V0:aYb>6A"*!3c_PGg-m:[
V/0thuLO/pP4^qrBH/g#bh6EZ,H@*Up0KKaSJ`=boO@1b.BZ!7gdS=J]AB;U9k7/\k6l-FfO0
7md9-Y;D'NcgCniUp_%u]AFN&3!P@!+3<g(O+6\3o0$g:D(4kM>a(\Z(^:6L>$HFrKE\(u2>S
cHJ;s7J%Q%aoO&d=cX:P)PVcnK[9F#c[pnV,7G<GabIgi,1dK>9^0ADl5"1n9YGmGRCQ['>@
U8'eO0#['V=2g,_eNQL#`M'H^rf0h<nlH4%^6-qj0iqHF9HIc8um/mO1"Q&%/>qEgrQ;U#tP
/IJ@bk2SD)G+WnHS:f*SdPTaQ)NpoiFm$$5\5f;<GdfOdm]APLmHXVRCJlr0ZWNQU@kXL,n[Z
6es+a%G:EQ<U7LW9pYjniY-$3Fh5N>mpqS&?$Coe6\ftNA<.C(4h4ERZN7PdZ:CDh'$L'e'?
D$DYrp>+aeESWf*/="5c0-Z,r@>GMs.<jmW=uDA:HAhJ8@7k:cnX2L^\/m=Ns/oc.`3l6Vq+
598<,Fd6e"bpOQi-I$SV>WaL7b2KJak!MCmr$;.!Orh,CTM@b@-`c=&[TM**k?iM#nq).n?(
flRG+@G&)Y:""f2u1.qdfT56h$bT`mkn\K`+Rm?R(p1AsL@m.'=N,XY6Ap$i-(s*$tKO@_P,
jT>^SWBJ&%]A-tr^/[7Moipse<p'uQ,(N+FX=?3l9!6fDH\$>LFqe5:^VUsuT=p"SP^n2@r2_
bBaMrk1?H8V'W0Pu*i-Yc<*0pCfFp)OZ4<Zjk,5gaB8IDOA&`,pLB`5CZa`fQu(@#$06`"aQ
FYfjf]A+sgK>3&fc`i)0ck()a()4?*"DPmJNrHfC,H+KU*0BupheQ3RR?"4bTWDo6\O[l;$d4
I(aDXiX,lt7Pr0b?_7oP:?"C<\f=_LeTj[Hk/UI'C5pNH@n2GVWi>3Cn9I`7+\+[juO#N`=9
AF`7f6k6XV\U*GL[jPaJY8O`hh6h"//8I>R@]A[j6hnpmQO!3^C4b&WX23fkqPkfY,TrsChDV
i=^0&q"78A!,:EbQThbE*!>4.rj-+n6?)0VZ`#A8e)a_Qr9^Xf%cL#q2XJZA)UFsKhuX([El
o.8/c("^fP+W*6%V'jtK4@)E."",+('cqA"Zt1V+OVoHN:M1VGIY)%!P;'c[IT0T#T01.;@+
.d)CFH^c:>*$2#K]A3'nScegu,F%3ZI+tk'b'Ji<L2?TO+*k,5aTEc)qnf4]AABk-.D;%6C)75
J.ihJ%W&h9%G,i8+JfYX3sR78>)D`N7G%aJ/PnkS,4^CN*GUH=W(G.S2p84H@6'!:G\]AYY1i
13\2(YkeuXMA^*giEfm/p#?0@l_`UI.^t;p9jQS(U0I%g'Tg9Y,*-0BF'e\$E52%HVPGf:P;
jUJ$<9d&8g[bZ:#hCnsOr*r$gmglfe3KIUB"\b5JM=i7+>`UjEV'0/PKa\kH$;RFZ7lVV&=5
YG3NHuno^Kf`I6XioreNqK[7#Zo9%Z@<ASUtb74Z`^pJ-qJnT$7$Mt"c.KV)o_Cjm7"NDfr"
.tZDpdBWc1.&Dl2f^f=niYEGH""tnaiR'#1dTN/\6;g5\[ESaQBSR5[l.LoVa0S_P6Yc\[ki
Rg6H8^luFGkkU!.:<h7ClsWe<UC.)\`"kc4S@pPY\f7.A@d^GcRTg%4]AH.`g!R9:O,n/@LHB
\ClaBQISP4A`ZOs"`;S]AK,7BM6iUQu1G0W&7C3/^'$rue'XI_u:acWFHSZeSp?[DJ`TE+nT>
HQX@e#mfH:"VH2*@\"$@D7`ALqba/$QFNm5kk&D$ioMH4FD-=9S?S3h6>a#3]A!GRC]Atpe03n
U/(BbO:3g+leJ`QOgfPI`ipk11BULrF?gG.;QmZ]AI,F:Y7\^"8m:@k;j7b0FP)[:)p:@so"!
KBfQ(9PL]A`eZ/]AhP^<Gm.A7rD&p`HdC2O+GDY%#F@GPppUl%j3"T"u1\*PX7X$$Lak,P<i9>
`!7?p"AOAR*p)?Hp9^g.E6?323q.c(?%*HZC&f?S[>of!*D_cl`k=dLGYZ=7,uD5,DN19%m7
j6@PCT9\iQ,T+?r3Zh.lDT7R&[_PWW5+IHu#[)S]A6\p$;OE(_ci=BsInK"epF[aXnKkm$bn;
2R?['X_e,56*gS&;\ETT\'Y50N^aJ8]A<[Eg(J)rgr9@\#k?W3Zl>PfF_ZB1!/-ItIZ%45f3O
JXO?2S=e5DlWOWN5\CQ3JuLGonn9!JfRP]AfZ5@-]AJU^cF-V"dAK0Y4=Qida)OFf$K);'"Iak
=6aB<Y]Ah5fHhJ5gQ,<(_j=G-@!F^j@o+P,4e$'V#n$rRn#D4=X9'N#'2R)b-@Yruj#&G+=R;
SCup8E-CgP(VZKO]A!o-=A+HG#!Ga)oYZl10[g4AmK0e-(LkRd3ZU2VbdZB-4=/2@:d=j7b3Q
_<5F25pC+m.f4&Q'&Q9hU\/etSf*a;'46YMb1%]An)X^[Z,6V<Ur1BD0GlMX"QK)f4';ojS[#
&Hl\aF-?h,O=Yh`^l@W8rp;q$X4UI<6B,!8\,T0Cdg/g$/d,3C^,TU;nJfb@r4jA[rI(p'4[
:h5#!LV2isblqH[i`]AqI9053;B$KEArT0LjMuO-AoQkkaY$CVB`1*fHaT-7,Q/Bl9<@j]AuO"
02R5?'\T`Yqap7Tb`Fg^C^s/Qc.F]Ak(6&BYHp">QEV(X6I_XWPi0dj\:qMd%Um!&n**KRVMu
[>P,g]A"UI,0X8iIm$K>\#(fE9ShV+;n)C8Y,Q4F3fPt<hkHsQs]AQ7hCEtE+=U3\,FhKHDqqu
1H?*1RG=:&V4,t@eMXb!j5laL?VaS;Zb/:,?WVFjp7,)Zk,QKO178^uPWPTr1IXng"PU%a=)
Lj^$+!4e[4n4_/E#`e)Id]A-@ZGV#2A6gWkZmS.9T`XLVQAW9p!3dQ&?(aFRr02E%r9='k:k4
*aPLmG7=F.E`(`!;%%NeRR_%b3]AjZn/U%FEbK5'rb&#m,Vc"X<%P6=DGapaA^E8]A*'^Y5r,E
!3,W]A(c6$%"YQb6:s8T%:(:Y5]A_.4/8h!V?%DJVA;2O33W@b;;lE47+B&'T^iV'H*..<9`R5
@q@\\Gq&V/oZ$I!c$13u(f%aG*ebqTTB&dB/>3V$BmscDuerlHW7p"YeY&Ye:muDVTW\X1*!
T<BT+9Ie#X-94U7g#L=f>TAf*lfPrcYXrZGZkFc(+#Nm!?IjlQ[%MV1rO/J&Xh.Sl\^Kj/@<
8MN19ack$+_!HH[@)8%C]AKZ@',P_9!$D=CM'hb_2Q9_Mi!<+\E>Oe-4BV[e['Akb)'+O*9@$
;j%gU^Zi"KUN1h_s.D)(]A'HrRf"BkTh^1\e5]Aos3W+*B0q1b3la:)Ve3-0:28#DR<0rnRUeF
0ht$hbda#c?,FQ3g&<l1eWX7.p^E"4i5l(?*^E&>4Y8?(5Ise5+`A*#+969H#gGt%]ApCM41k
f9qS@O^mU>a%''CPUl[=_"P1uN8/$>e3L!\[l#>h\BLPqBp"`[Jh0p))T+<QfEHfbY=/7Z$F
=La02I57ArU*cF%KSX$:Cq%aFsm-<NP#@DT:/[)e]Ag%!rj,X3YFD/K<#J\RoA.m3NMpo5UOL
$u="auI8nLqKot_G.f&H*0-1bu*J>0(6k)GuC'EQ\g>"hSZ+r@C&,M!G^b_LgWdSn(mcl[rQ
'!6a8!X2h0f9)NT06hioZ.s3]A>Kd(/tASaR#ja,3c_/r+ojN5sA7)u7&;A&.bi05e[_m&TmX
6M!C(^X:C0c!EJWD$8n$-"5&`LBFEA20tDoBeeC&En"P0",e;*rgSk^1K.Y#UU"(]AW\#T@iK
^JTl%sMJU,09E=;DXWEd!\p/6XGS,8QGP&RT`PEfXDB6j%5o>HT:PbD6%CI3FE97^gEKicIp
YlejWEjgutp%[,RoA3JPPEXaWQ\1p=^1f/\"Xq#[tD>9T41`SHh/Ik:DZD&%N@'A-=ZH<eYk
,pZrRJGEtgpikK>1Z<_Bb^e5<>"$r]AQcD&<:6B>cJm/AMVKN1(^0aaahOEOR29*,4Co'1,I>
,1n3fKq'"B&Y*>Esd"[?CkU-7=FNeqT$hAGTXO6b3q`F-cu`:Q=$<Y[TaUOmu`OX"m64k1GT
rN_e5F,:dDKKEs(@Ej3Lo_[Ud5C$?uA`&]A^!=k%,dpj+C3I3`qV76[hBE9@LY^(>#/.D@7qD
6L0JBX6#_c1M5X8hasi=R\Z(]A5K!iiac5_`eTYqI.S/Z.$;IZsO3QFUI-<Kd]Ag7Tu?\)/U$F
7IOu\"6?,0$<Rq.E]A5Y9q-C=".kpclrUg;!Ef##quad-c[cHh'6LDi60))j%\BP_'V^T',"n
02$]A:1lM8ETEPFZl**[LJt]A-r)^$_+ma]AWSLP-2q:TsU]Am2GP9d?_2TPG<=eonkUq"1h(g#V
Yh+]A6QO7!e#./fJ$8Ir4UKTI]AP;m>.V%B/uGK>q<PF\]AJ=+F3mRXi&-j(Km*WumT2-]A_O;EN
GtVdU+^9;""7/bW*RIf)aSW:8Op.0_lRW=7=\JS:BUt6:2=SL"@Xh4-H1'jZh!&YJViLY^$d
'^oUBo.DcWNG:#KP'f$F)r*ebMrU"^c0VJm'FF&Tf>IiH$H7$b,l>34^Co17iB(AAInZG6j)
,&#h%QW8!(0)e`ISqmq%f<W/DY_k-9Y+j&r*UQ\u>lnN.<K(AR6%-Ad0Io;$DRWF*O6jr,6O
m&fr$X=,/8H.R@%8MC:Z<62<`7"0dgsN;+X]AIQs\UYBo^;T8k[0.P>lPJ.U=)Wp7d1$fiTAY
X%i,%nTs"r)m-Zbo\^G`Q6f;Er+02Ok<"LXWdo#:U9:nWq.4mW`ooC,^,O4#Ls(t2,\bP.J&
Vmu,9:uK+CVE(J/R9M*.7rkQ:<;iPP]A%1?jG8u6JK3GAVf`kfEUL/:`k(S<<*Lb=o^;>.Ud:
^`l'b9?YcHm`doW%:`Wk>jsf^N*)2Hf<q(Fq^"E*kh/qX);m,$-A87s(flA5KI/5p(5oM`GV
\LXRj]A+s)N0htDPEaSQD@2nBf^URq%F@0_Ee(q:Ct=I(@C(/).3I+@S[Q`C`0p)71;DWkA/e
8*N83_pm38pI/0l@C'r0B+Y.;FIb`VZlu!gZhT*X$'$N8uFV7\:q2;\_j4ac?U@(SOp"gftk
p==.k,`*OfBLKO3[3\6$Zsn-jL_`^1#tJiDT2P<If-,.rNQH+N1ubl=SWr@snE%^*CZ1E-J7
J!ubuNtbjeqCS2!<-S&,,+m'7Ms<Dq/Mo_3O7RHiHBf/Q:lk'Vif_aI9;8.batIe1ao,G/@7
sA6$Tt1I`'SX/]ArU#)khi#"Y1c3CqOU^3rRF%+g^VSHJ[:W\3[(=/1%^d"MHCoIpKs`6B<td
'b9`aRX[GJgfCA]Ae3tD7^Nc3ZKE6Wt^:S+0%C,Jg2V6bk'1/WNNU(/6W3)4$;>u0st2Cd@t4
*#M\MI'2F`>c)g4H90SW:!(Me;NH_l[MY6dc!4NIqb9+T'dG5'i+ZY!`Scn%AHhrLusQ+s+k
rl@aIV^GhbOjon4:S`h.6NC"*a4a2Q#7d]AV9H*]Apk^+.3"2qc*F$RR$uNHEWcCGPi\*;ltV`
;r^Xr<Jm`QU0JU/s0mNSKtZN24?78_k+>[LS,3calLi+lbq'Y#o7u1\k%DecaP^.T4ON[Pl2
?+-9goPgF'mbq!e&C-Z1Mos?Je\hIui%Iqu>0@>m0Z4l>qmB4]A2J3\.0M;7^k,mY.:P>?>Qh
FIEk(!fa'MEpLm,Z"LIoAo0gnh&ci*cTMm\Dp)eG4q7d3Zb=:&81"8tn`#4144.W,2h>a!H5
G.FX?H&a\208*^=3Y/.^IO$s2-eT`PlD^KlKI,[G5b7L>b=PHor/do,#+UP7:,/?bMAQNT[n
==#'_j%9ET(;ghXE^ogH31?$c6`!Q;,A.nVMN">V1WTJHO;C1%]AFBU%f=r:16!U$<qc*^0oE
([2t0nSl(.#O,L*3%Cba56[B3HZO2[M)pIP*ESkOS2fc60G/]AcIF4qK#I=1`=S:A\]A$@-tdM
WRB53"hD4H]A1cLu8@u=gqF\_o@dt>\e,\f-5&Opt?P[bZ\9GFXqNQ1OmQc::j%0]AL;o@=I&F
LXO0_sraAr3:Uj2)lIF;3LP"@bb"",Cr2Rkej.u5k$ka['*^d4YOJ354_'PK*C]A(B#OpZFXY
Y>h4M71Pq7^oal6h0CNnu0Z,'U_M/_J.&*,&d:5AEP-?l<3L!G@BjD]AZd(TkGiUr[)Lk!q<2
99179Bg6U?lAVMbokdokt'D7Ra37D:e")0*r[M5C+B/><U(I4"0\^eBHH/1c/5K#(DeT`NVH
WZ;PD-uC18Ho#1`#V_^g-6kMK_)1ISP>@CsRrXWqmr&7ljaXIHam=Y?Q3tUH=dU5kYEf3F-H
s@N9K?'XU=59QH?o;^'e>"b-C`*EoRZ!2IkJr)U,mpSPUkVpSgLur;&CuEXU'<M/]AaGR;%/+
;'LIsB*&b:)j'-8TO]Au0,W,;[]A_Ei3SIOXp[-El:L%j%lGUZ+-b0R72VJS\3M(3GD:Ln)bWm
tn4hL>!NW>Ac`.&t(qXG>uB7@Bn8DXDp.D0EG;a(Y!8kV2(pb5oQ@C"kgGq:M;DF5#"_MU/t
VlJ*Io._bi19,+AGUNILf>bB&=M>gDkML?,q<JFE6&8+h;K6#lNQ)n>\3cQ5[6!;oa7+2l6;
k+jK@N*j_cAC3oO?#RZD@77Pn'Pc8Djnu0Yd^s$6_)d.=dm;<rZVAQRiPHr+fU96@!L@ER4>
7C8-[temM/D[nYDSp`m's!u)]AJBHfOSOOXptZjo5EC_`;HSh1DZqse'VELWqe5;'17^#VcdN
h\R/;V.P]ABD5?k(@^=BF3r^(/aPXS='`2)="H=c'gAonoXGB\4SYR;/2FV0=^aQ!c/G&1@Wo
@JcgNI'KAc*!&'q:OXRQB6iI+r<AAUeso6V$osJTl$rq0Rj:<q(hSN-;(^.hM#!D(d]A9Gf%<
n!>r00_;mP@]A^DDX-iInZ"#dqjM9N#ghm!:@1;R&jqI8=?6&ggHOLDui*JPg!A!1baVqOo)q
<*MRmqfVk??!B[TnWU#q/PAWm"r<Cs`+%+3)Z.:8qfTVo[Y:XKVQJC<&;*l:I#mr?^6c(q;9
aO#iXVr#+DIWAVK2BWA\WnUJ77&tc]ApG*+kmXG*%H$&lXs7)/AbIt(/=OgBQ&S&O6tb,HMi/
8PSbSXg\%piHr#(Q<5h=^=MP__>4+XrOW<ahc;**]Ajj]A4+m9SSnY=2t<)__p:qlmM`a!f:+D
]Ae,#)J!?.,f<<:2"clbJW7E2ONbs?k=,3q@oYQr"/9c^Oeafq6sDuGPo*+p:Gg]A;:Va>QA<3
%g+3GkFa6sH>6BP&8VSTGM+Bs=V/[mZSgO-%#H=KWqPnme:pho:l,HK2dX#Q_W0']ALa%IM+"
`QPNPd&cU-Pm+#IB>SOE/T173.=6/EkP1+>H2<k4@h?F&/8V5\l^"ChSe$rJpZo3-bP9G4fC
pB'Uh/Q=q1A,e8&t,`S['>E="3r1B;]A:H]A="s1=j%cUc]AWU`T3FK2dQ13F_Pp'Q]AYRF1PBHG
/-uC@@J)kXsP[*FucL98Dc*%SA^]A-91'+]A^I,+Wd?1+e$(b]AXBjh$[K6btK+R<%s\*>,EU[/
-s!J'_=Kc(TI`5<3Km58pgoYIMe`G)Q'55Zo8,d2.Gd&*`Tm=?Z7>Um?Hokl5"JjJ-eCT7kh
<Fr5ejKh\+f#2TM9AmCtD8B9QIM#EXg]A#bQCsARcpVCo?(daS+B7bfbV1N3DjYYBI<2.8>;u
`5!j/OiPNKiGVn5Sf_)sF_t]AqV"9]A*Ek-9bd'-ud</GcG!*&:rLa75\r/H:PaY?^d0<s4!'o
k?/)r0^^l5HthGZj6\Q+<h#r,b,G<n"[l`hRWF%ga4uoiRub!@\hdhIPF.Tr<jMNMMR0h9>[
e5ITmtE0F2YSXEqE8X)pPMu,R?/Ap%QUhQM.JkaYs6?^:6<LPZ6<)nI3H\Ft`=,?a#Yp8t'(
4S&rWr/pi4@rH7$4TTJ4KY3U75HcoTnfZZ.]Ap!so4K-m;)$[q"TkE#ZOO#i8$Mh:2niBNP1&
jd*V9fA!7UhkmK63b.qTAaZ4pt^0>sg.\$Sr]A/V`E[+T&FmcW6?j#G4gQA2?YtbE"`V,[ob5
.Tob*^r'G3EZ:hT!GmW<giU:B&aUY4<^GYM6(8#:*d5+plicImL/Y4rj\(.P0-AC#QpJ.hee
:\Y.=Jb+mA$W(8!2Of(kj?$eAaQtQhoGk'GI>\@Y5'N]A$l>[S*@90jo%qBB"hC@>/#FeRh*V
!+0XN%r#f$_>1!S)LYAb<^/5]ANb!f9;Zd2p)TJU>hIep^k_9mYU)/+I(>n<7/QC$;<!@P.'`
VA",YU''JP6<(g_`qnQCtiEIprDYY$A\6G[(uC=-ssM^AOL_6K!,`"Gc^r!4`eb"?3@2b\qF
)$HG9d"d6CVYbLK'h$p9YD2-jQA8s;9!Y@dhjO9fQ0R,&TP5UpnVCC^*aZ.m5T,Pb/?Wklt?
l1F!3FPY;6)[?S2+oRG8`0MctN^Q/Lm0"mEOXULi*uG0.lL5^D<Z]Ao(D%W8i>Yc[Q^lN&7+6
K*biE7f`2)8HG4'4/C\pRoXMtEph&)OM4nerifRWBJ6^IrX2kC_&!3^-]A'n/220**L"S1V)e
HQfTl9VeCQMV1mt%!HEZE]A)/nW0=)cq##WbKkb:#;58.@dYe^`8VofYZk"t;U.+ReaO2M2N5
%(iqjB6`%*F%.`UH3]A/DhURI@'$O[YRWc8,D16u!>)Al#;+-:[,Xi7S6*kC_og^A6[<1RY,=
dL01$(oTjJ-:OMQ><)oaAkH^JpZL)n7=CRDL+ro%C=+hZV.$JuEh?Q+ZQ3qSZWH-8.g#:FF2
n!p=SB5&S@9UKtapA(9Y6aYeLXkXB;G.j4tOPOMX*GSh(!?RRH74R\B0<d6;XHuB.jW[0uAU
Eu0I-Zc@\GC8:5;Tb^;gpprSY[,P1C3o9h+"Y-nd?0EY:C:BJe_32@qhhpcJ"hadZ9EEF^B^
2Giqg#O?fB0$bP"N`#FK!EKO%5U8roP/a^nr+#A65SY(oSf0/oGSO;9UB?o6lh=iQ]A^^:g+D
F_mB]Ad(JYoP;u03)A@]AU.EkuOUQ011YY!oFqo(rdUVa3?@5R5&uC7iXEn+c.X#%dLY%T`IMM
K2b(NWX-g+5*GM"^$H>KOV8McfKmC[:OmF^P"2T"Xs/KR?9*60a3YrDF1-`]AZLIoa98KDB)E
Xb-7<od[>:rK?*XrNbMS?mM"I&`pHpEU/I"FDBnP[6B9!kK&sSFcBTGBCF\\DK#9/1TEJV>"
+rfWQNOt8DZ%!KEkGk@`KP=Ht.B?IfT~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[KmJ#,<:ATARDgm?1:E-iaPLAqM%n2W+_?lafge,t-m?eC6APuB@bO8:Ou5#G8n\PTCS[&RA4
1[f9RWMi\(C6I4jRJIk1o^=]AW_)&^9;lQ/7m.&@6kbs!!%[(bY@g5!"oG8LenKcJ!)GM'`n@
b_%rnYgU#MHp)QeI[S(8(4F"jBi/UbIi<Q.pcVD7BCKQ!]A5Y6b_&VElJS05d)0M"KN@tkh''
TIu?BeVu_DL"If*DNj`X(ttAX2?V87.`.Ydk9S-3G0=,qqNn#luW#;CXqYa`NUn7eNUYO_(4
.9VoIdNHoBW'Vto"Bcm.Kc2q@6cG:ZLW!5!Z*8kp3e$n#@8X=rcl\F([,i$5_BNe1=QqR%aO
Y5^kmeeC8PI*/0q;3W??b6rnIX=Z+:Ktc@_>@PbdGnSc*Hf<A=N,OI-r%8k_rGYP,L`!eM':
P2nDH`1HdlU&<#HJZ^gG%b^qZs\mpKoq!r<*tS1R\+r(`YE%>"T+HO0_dT&pF9*Vhd:;LjM#
6g$XDBA6\_%(Hej\H,t!L0?C89c)3XUL4W*M]A:.XqYlR(LKi#$&1EX]A@F'HL2!-dGY1e)8K>
*sZjB8e[E'(m.EH5'q@%-n!p<Y]AmBIkIbo"J7n>%EVeLpF)JKfnVdr8!;uWMn;/.A(7X9pjR
K"?ou+<6g9m%=\9_o+XI-"5c*K#-"m=iH>Im$p^g)Mcd%CI`/W=g1LWQ[\sBquIY_B_(+!DK
Znr#8@%uIUG(a*P1FRq_6WK$i.>M2<$DC3J168686@8+sorc:,=nh;j_'5%/90<HYH>#2!rA
$:a0#H($r+J:J6HrH^,ia4sp'uE,?)AO;p\p!WEgCCOdUl9UW9ni%k#tDtT`m!d!SNJV#VU7
\RNk`.r<18[Q3<Yg::,]A>:7o;9pqjIO41a"-[Qo>R2JI*^ah\,@PU/I4l_bmu6[-Kl30%L"p
XYtlZS#,]AiJSbb#MMUm?"h>9EN"9uo6Ffl#XoW83#Ve=Xm&'8Q+k(="Q=qrCVkBI`cONY6Dl
3)46@6fd9HBXXW#>3<@"EVXH=:(^ZcYqA<B]A\')&OCWDoH,7q7Dbh#N+Ge^HG_#&qM@7PN/&
]AG[Z'H5*c;a7jG,"BY&U,.f_[dBnf(mM>lMk]A)eE'L6:,a<$<`ot,X<fTM1:5m]A:<91'^GHT
p>Inl'#o#gMh2NRaTQM2u,qP"I<o4dua#,$GBeF1]AqC&GUGE*6H/@UPS8$J_5`$r-AIqRZ_=
RK5a/O57U$oWgQqmANAE1kTNl*#YdWVQ7o<EUm"q?9n?NDGrV>Fk#X&l+)9)>f4CPOEL)/j/
081d<]A3OX4Rac8CV\f=06`<tZdu0*+tp',c1.3U28g_8Vr3!-!V0:aYb>6A"*!3c_PGg-m:[
V/0thuLO/pP4^qrBH/g#bh6EZ,H@*Up0KKaSJ`=boO@1b.BZ!7gdS=J]AB;U9k7/\k6l-FfO0
7md9-Y;D'NcgCniUp_%u]AFN&3!P@!+3<g(O+6\3o0$g:D(4kM>a(\Z(^:6L>$HFrKE\(u2>S
cHJ;s7J%Q%aoO&d=cX:P)PVcnK[9F#c[pnV,7G<GabIgi,1dK>9^0ADl5"1n9YGmGRCQ['>@
U8'eO0#['V=2g,_eNQL#`M'H^rf0h<nlH4%^6-qj0iqHF9HIc8um/mO1"Q&%/>qEgrQ;U#tP
/IJ@bk2SD)G+WnHS:f*SdPTaQ)NpoiFm$$5\5f;<GdfOdm]APLmHXVRCJlr0ZWNQU@kXL,n[Z
6es+a%G:EQ<U7LW9pYjniY-$3Fh5N>mpqS&?$Coe6\ftNA<.C(4h4ERZN7PdZ:CDh'$L'e'?
D$DYrp>+aeESWf*/="5c0-Z,r@>GMs.<jmW=uDA:HAhJ8@7k:cnX2L^\/m=Ns/oc.`3l6Vq+
598<,Fd6e"bpOQi-I$SV>WaL7b2KJak!MCmr$;.!Orh,CTM@b@-`c=&[TM**k?iM#nq).n?(
flRG+@G&)Y:""f2u1.qdfT56h$bT`mkn\K`+Rm?R(p1AsL@m.'=N,XY6Ap$i-(s*$tKO@_P,
jT>^SWBJ&%]A-tr^/[7Moipse<p'uQ,(N+FX=?3l9!6fDH\$>LFqe5:^VUsuT=p"SP^n2@r2_
bBaMrk1?H8V'W0Pu*i-Yc<*0pCfFp)OZ4<Zjk,5gaB8IDOA&`,pLB`5CZa`fQu(@#$06`"aQ
FYfjf]A+sgK>3&fc`i)0ck()a()4?*"DPmJNrHfC,H+KU*0BupheQ3RR?"4bTWDo6\O[l;$d4
I(aDXiX,lt7Pr0b?_7oP:?"C<\f=_LeTj[Hk/UI'C5pNH@n2GVWi>3Cn9I`7+\+[juO#N`=9
AF`7f6k6XV\U*GL[jPaJY8O`hh6h"//8I>R@]A[j6hnpmQO!3^C4b&WX23fkqPkfY,TrsChDV
i=^0&q"78A!,:EbQThbE*!>4.rj-+n6?)0VZ`#A8e)a_Qr9^Xf%cL#q2XJZA)UFsKhuX([El
o.8/c("^fP+W*6%V'jtK4@)E."",+('cqA"Zt1V+OVoHN:M1VGIY)%!P;'c[IT0T#T01.;@+
.d)CFH^c:>*$2#K]A3'nScegu,F%3ZI+tk'b'Ji<L2?TO+*k,5aTEc)qnf4]AABk-.D;%6C)75
J.ihJ%W&h9%G,i8+JfYX3sR78>)D`N7G%aJ/PnkS,4^CN*GUH=W(G.S2p84H@6'!:G\]AYY1i
13\2(YkeuXMA^*giEfm/p#?0@l_`UI.^t;p9jQS(U0I%g'Tg9Y,*-0BF'e\$E52%HVPGf:P;
jUJ$<9d&8g[bZ:#hCnsOr*r$gmglfe3KIUB"\b5JM=i7+>`UjEV'0/PKa\kH$;RFZ7lVV&=5
YG3NHuno^Kf`I6XioreNqK[7#Zo9%Z@<ASUtb74Z`^pJ-qJnT$7$Mt"c.KV)o_Cjm7"NDfr"
.tZDpdBWc1.&Dl2f^f=niYEGH""tnaiR'#1dTN/\6;g5\[ESaQBSR5[l.LoVa0S_P6Yc\[ki
Rg6H8^luFGkkU!.:<h7ClsWe<UC.)\`"kc4S@pPY\f7.A@d^GcRTg%4]AH.`g!R9:O,n/@LHB
\ClaBQISP4A`ZOs"`;S]AK,7BM6iUQu1G0W&7C3/^'$rue'XI_u:acWFHSZeSp?[DJ`TE+nT>
HQX@e#mfH:"VH2*@\"$@D7`ALqba/$QFNm5kk&D$ioMH4FD-=9S?S3h6>a#3]A!GRC]Atpe03n
U/(BbO:3g+leJ`QOgfPI`ipk11BULrF?gG.;QmZ]AI,F:Y7\^"8m:@k;j7b0FP)[:)p:@so"!
KBfQ(9PL]A`eZ/]AhP^<Gm.A7rD&p`HdC2O+GDY%#F@GPppUl%j3"T"u1\*PX7X$$Lak,P<i9>
`!7?p"AOAR*p)?Hp9^g.E6?323q.c(?%*HZC&f?S[>of!*D_cl`k=dLGYZ=7,uD5,DN19%m7
j6@PCT9\iQ,T+?r3Zh.lDT7R&[_PWW5+IHu#[)S]A6\p$;OE(_ci=BsInK"epF[aXnKkm$bn;
2R?['X_e,56*gS&;\ETT\'Y50N^aJ8]A<[Eg(J)rgr9@\#k?W3Zl>PfF_ZB1!/-ItIZ%45f3O
JXO?2S=e5DlWOWN5\CQ3JuLGonn9!JfRP]AfZ5@-]AJU^cF-V"dAK0Y4=Qida)OFf$K);'"Iak
=6aB<Y]Ah5fHhJ5gQ,<(_j=G-@!F^j@o+P,4e$'V#n$rRn#D4=X9'N#'2R)b-@Yruj#&G+=R;
SCup8E-CgP(VZKO]A!o-=A+HG#!Ga)oYZl10[g4AmK0e-(LkRd3ZU2VbdZB-4=/2@:d=j7b3Q
_<5F25pC+m.f4&Q'&Q9hU\/etSf*a;'46YMb1%]An)X^[Z,6V<Ur1BD0GlMX"QK)f4';ojS[#
&Hl\aF-?h,O=Yh`^l@W8rp;q$X4UI<6B,!8\,T0Cdg/g$/d,3C^,TU;nJfb@r4jA[rI(p'4[
:h5#!LV2isblqH[i`]AqI9053;B$KEArT0LjMuO-AoQkkaY$CVB`1*fHaT-7,Q/Bl9<@j]AuO"
02R5?'\T`Yqap7Tb`Fg^C^s/Qc.F]Ak(6&BYHp">QEV(X6I_XWPi0dj\:qMd%Um!&n**KRVMu
[>P,g]A"UI,0X8iIm$K>\#(fE9ShV+;n)C8Y,Q4F3fPt<hkHsQs]AQ7hCEtE+=U3\,FhKHDqqu
1H?*1RG=:&V4,t@eMXb!j5laL?VaS;Zb/:,?WVFjp7,)Zk,QKO178^uPWPTr1IXng"PU%a=)
Lj^$+!4e[4n4_/E#`e)Id]A-@ZGV#2A6gWkZmS.9T`XLVQAW9p!3dQ&?(aFRr02E%r9='k:k4
*aPLmG7=F.E`(`!;%%NeRR_%b3]AjZn/U%FEbK5'rb&#m,Vc"X<%P6=DGapaA^E8]A*'^Y5r,E
!3,W]A(c6$%"YQb6:s8T%:(:Y5]A_.4/8h!V?%DJVA;2O33W@b;;lE47+B&'T^iV'H*..<9`R5
@q@\\Gq&V/oZ$I!c$13u(f%aG*ebqTTB&dB/>3V$BmscDuerlHW7p"YeY&Ye:muDVTW\X1*!
T<BT+9Ie#X-94U7g#L=f>TAf*lfPrcYXrZGZkFc(+#Nm!?IjlQ[%MV1rO/J&Xh.Sl\^Kj/@<
8MN19ack$+_!HH[@)8%C]AKZ@',P_9!$D=CM'hb_2Q9_Mi!<+\E>Oe-4BV[e['Akb)'+O*9@$
;j%gU^Zi"KUN1h_s.D)(]A'HrRf"BkTh^1\e5]Aos3W+*B0q1b3la:)Ve3-0:28#DR<0rnRUeF
0ht$hbda#c?,FQ3g&<l1eWX7.p^E"4i5l(?*^E&>4Y8?(5Ise5+`A*#+969H#gGt%]ApCM41k
f9qS@O^mU>a%''CPUl[=_"P1uN8/$>e3L!\[l#>h\BLPqBp"`[Jh0p))T+<QfEHfbY=/7Z$F
=La02I57ArU*cF%KSX$:Cq%aFsm-<NP#@DT:/[)e]Ag%!rj,X3YFD/K<#J\RoA.m3NMpo5UOL
$u="auI8nLqKot_G.f&H*0-1bu*J>0(6k)GuC'EQ\g>"hSZ+r@C&,M!G^b_LgWdSn(mcl[rQ
'!6a8!X2h0f9)NT06hioZ.s3]A>Kd(/tASaR#ja,3c_/r+ojN5sA7)u7&;A&.bi05e[_m&TmX
6M!C(^X:C0c!EJWD$8n$-"5&`LBFEA20tDoBeeC&En"P0",e;*rgSk^1K.Y#UU"(]AW\#T@iK
^JTl%sMJU,09E=;DXWEd!\p/6XGS,8QGP&RT`PEfXDB6j%5o>HT:PbD6%CI3FE97^gEKicIp
YlejWEjgutp%[,RoA3JPPEXaWQ\1p=^1f/\"Xq#[tD>9T41`SHh/Ik:DZD&%N@'A-=ZH<eYk
,pZrRJGEtgpikK>1Z<_Bb^e5<>"$r]AQcD&<:6B>cJm/AMVKN1(^0aaahOEOR29*,4Co'1,I>
,1n3fKq'"B&Y*>Esd"[?CkU-7=FNeqT$hAGTXO6b3q`F-cu`:Q=$<Y[TaUOmu`OX"m64k1GT
rN_e5F,:dDKKEs(@Ej3Lo_[Ud5C$?uA`&]A^!=k%,dpj+C3I3`qV76[hBE9@LY^(>#/.D@7qD
6L0JBX6#_c1M5X8hasi=R\Z(]A5K!iiac5_`eTYqI.S/Z.$;IZsO3QFUI-<Kd]Ag7Tu?\)/U$F
7IOu\"6?,0$<Rq.E]A5Y9q-C=".kpclrUg;!Ef##quad-c[cHh'6LDi60))j%\BP_'V^T',"n
02$]A:1lM8ETEPFZl**[LJt]A-r)^$_+ma]AWSLP-2q:TsU]Am2GP9d?_2TPG<=eonkUq"1h(g#V
Yh+]A6QO7!e#./fJ$8Ir4UKTI]AP;m>.V%B/uGK>q<PF\]AJ=+F3mRXi&-j(Km*WumT2-]A_O;EN
GtVdU+^9;""7/bW*RIf)aSW:8Op.0_lRW=7=\JS:BUt6:2=SL"@Xh4-H1'jZh!&YJViLY^$d
'^oUBo.DcWNG:#KP'f$F)r*ebMrU"^c0VJm'FF&Tf>IiH$H7$b,l>34^Co17iB(AAInZG6j)
,&#h%QW8!(0)e`ISqmq%f<W/DY_k-9Y+j&r*UQ\u>lnN.<K(AR6%-Ad0Io;$DRWF*O6jr,6O
m&fr$X=,/8H.R@%8MC:Z<62<`7"0dgsN;+X]AIQs\UYBo^;T8k[0.P>lPJ.U=)Wp7d1$fiTAY
X%i,%nTs"r)m-Zbo\^G`Q6f;Er+02Ok<"LXWdo#:U9:nWq.4mW`ooC,^,O4#Ls(t2,\bP.J&
Vmu,9:uK+CVE(J/R9M*.7rkQ:<;iPP]A%1?jG8u6JK3GAVf`kfEUL/:`k(S<<*Lb=o^;>.Ud:
^`l'b9?YcHm`doW%:`Wk>jsf^N*)2Hf<q(Fq^"E*kh/qX);m,$-A87s(flA5KI/5p(5oM`GV
\LXRj]A+s)N0htDPEaSQD@2nBf^URq%F@0_Ee(q:Ct=I(@C(/).3I+@S[Q`C`0p)71;DWkA/e
8*N83_pm38pI/0l@C'r0B+Y.;FIb`VZlu!gZhT*X$'$N8uFV7\:q2;\_j4ac?U@(SOp"gftk
p==.k,`*OfBLKO3[3\6$Zsn-jL_`^1#tJiDT2P<If-,.rNQH+N1ubl=SWr@snE%^*CZ1E-J7
J!ubuNtbjeqCS2!<-S&,,+m'7Ms<Dq/Mo_3O7RHiHBf/Q:lk'Vif_aI9;8.batIe1ao,G/@7
sA6$Tt1I`'SX/]ArU#)khi#"Y1c3CqOU^3rRF%+g^VSHJ[:W\3[(=/1%^d"MHCoIpKs`6B<td
'b9`aRX[GJgfCA]Ae3tD7^Nc3ZKE6Wt^:S+0%C,Jg2V6bk'1/WNNU(/6W3)4$;>u0st2Cd@t4
*#M\MI'2F`>c)g4H90SW:!(Me;NH_l[MY6dc!4NIqb9+T'dG5'i+ZY!`Scn%AHhrLusQ+s+k
rl@aIV^GhbOjon4:S`h.6NC"*a4a2Q#7d]AV9H*]Apk^+.3"2qc*F$RR$uNHEWcCGPi\*;ltV`
;r^Xr<Jm`QU0JU/s0mNSKtZN24?78_k+>[LS,3calLi+lbq'Y#o7u1\k%DecaP^.T4ON[Pl2
?+-9goPgF'mbq!e&C-Z1Mos?Je\hIui%Iqu>0@>m0Z4l>qmB4]A2J3\.0M;7^k,mY.:P>?>Qh
FIEk(!fa'MEpLm,Z"LIoAo0gnh&ci*cTMm\Dp)eG4q7d3Zb=:&81"8tn`#4144.W,2h>a!H5
G.FX?H&a\208*^=3Y/.^IO$s2-eT`PlD^KlKI,[G5b7L>b=PHor/do,#+UP7:,/?bMAQNT[n
==#'_j%9ET(;ghXE^ogH31?$c6`!Q;,A.nVMN">V1WTJHO;C1%]AFBU%f=r:16!U$<qc*^0oE
([2t0nSl(.#O,L*3%Cba56[B3HZO2[M)pIP*ESkOS2fc60G/]AcIF4qK#I=1`=S:A\]A$@-tdM
WRB53"hD4H]A1cLu8@u=gqF\_o@dt>\e,\f-5&Opt?P[bZ\9GFXqNQ1OmQc::j%0]AL;o@=I&F
LXO0_sraAr3:Uj2)lIF;3LP"@bb"",Cr2Rkej.u5k$ka['*^d4YOJ354_'PK*C]A(B#OpZFXY
Y>h4M71Pq7^oal6h0CNnu0Z,'U_M/_J.&*,&d:5AEP-?l<3L!G@BjD]AZd(TkGiUr[)Lk!q<2
99179Bg6U?lAVMbokdokt'D7Ra37D:e")0*r[M5C+B/><U(I4"0\^eBHH/1c/5K#(DeT`NVH
WZ;PD-uC18Ho#1`#V_^g-6kMK_)1ISP>@CsRrXWqmr&7ljaXIHam=Y?Q3tUH=dU5kYEf3F-H
s@N9K?'XU=59QH?o;^'e>"b-C`*EoRZ!2IkJr)U,mpSPUkVpSgLur;&CuEXU'<M/]AaGR;%/+
;'LIsB*&b:)j'-8TO]Au0,W,;[]A_Ei3SIOXp[-El:L%j%lGUZ+-b0R72VJS\3M(3GD:Ln)bWm
tn4hL>!NW>Ac`.&t(qXG>uB7@Bn8DXDp.D0EG;a(Y!8kV2(pb5oQ@C"kgGq:M;DF5#"_MU/t
VlJ*Io._bi19,+AGUNILf>bB&=M>gDkML?,q<JFE6&8+h;K6#lNQ)n>\3cQ5[6!;oa7+2l6;
k+jK@N*j_cAC3oO?#RZD@77Pn'Pc8Djnu0Yd^s$6_)d.=dm;<rZVAQRiPHr+fU96@!L@ER4>
7C8-[temM/D[nYDSp`m's!u)]AJBHfOSOOXptZjo5EC_`;HSh1DZqse'VELWqe5;'17^#VcdN
h\R/;V.P]ABD5?k(@^=BF3r^(/aPXS='`2)="H=c'gAonoXGB\4SYR;/2FV0=^aQ!c/G&1@Wo
@JcgNI'KAc*!&'q:OXRQB6iI+r<AAUeso6V$osJTl$rq0Rj:<q(hSN-;(^.hM#!D(d]A9Gf%<
n!>r00_;mP@]A^DDX-iInZ"#dqjM9N#ghm!:@1;R&jqI8=?6&ggHOLDui*JPg!A!1baVqOo)q
<*MRmqfVk??!B[TnWU#q/PAWm"r<Cs`+%+3)Z.:8qfTVo[Y:XKVQJC<&;*l:I#mr?^6c(q;9
aO#iXVr#+DIWAVK2BWA\WnUJ77&tc]ApG*+kmXG*%H$&lXs7)/AbIt(/=OgBQ&S&O6tb,HMi/
8PSbSXg\%piHr#(Q<5h=^=MP__>4+XrOW<ahc;**]Ajj]A4+m9SSnY=2t<)__p:qlmM`a!f:+D
]Ae,#)J!?.,f<<:2"clbJW7E2ONbs?k=,3q@oYQr"/9c^Oeafq6sDuGPo*+p:Gg]A;:Va>QA<3
%g+3GkFa6sH>6BP&8VSTGM+Bs=V/[mZSgO-%#H=KWqPnme:pho:l,HK2dX#Q_W0']ALa%IM+"
`QPNPd&cU-Pm+#IB>SOE/T173.=6/EkP1+>H2<k4@h?F&/8V5\l^"ChSe$rJpZo3-bP9G4fC
pB'Uh/Q=q1A,e8&t,`S['>E="3r1B;]A:H]A="s1=j%cUc]AWU`T3FK2dQ13F_Pp'Q]AYRF1PBHG
/-uC@@J)kXsP[*FucL98Dc*%SA^]A-91'+]A^I,+Wd?1+e$(b]AXBjh$[K6btK+R<%s\*>,EU[/
-s!J'_=Kc(TI`5<3Km58pgoYIMe`G)Q'55Zo8,d2.Gd&*`Tm=?Z7>Um?Hokl5"JjJ-eCT7kh
<Fr5ejKh\+f#2TM9AmCtD8B9QIM#EXg]A#bQCsARcpVCo?(daS+B7bfbV1N3DjYYBI<2.8>;u
`5!j/OiPNKiGVn5Sf_)sF_t]AqV"9]A*Ek-9bd'-ud</GcG!*&:rLa75\r/H:PaY?^d0<s4!'o
k?/)r0^^l5HthGZj6\Q+<h#r,b,G<n"[l`hRWF%ga4uoiRub!@\hdhIPF.Tr<jMNMMR0h9>[
e5ITmtE0F2YSXEqE8X)pPMu,R?/Ap%QUhQM.JkaYs6?^:6<LPZ6<)nI3H\Ft`=,?a#Yp8t'(
4S&rWr/pi4@rH7$4TTJ4KY3U75HcoTnfZZ.]Ap!so4K-m;)$[q"TkE#ZOO#i8$Mh:2niBNP1&
jd*V9fA!7UhkmK63b.qTAaZ4pt^0>sg.\$Sr]A/V`E[+T&FmcW6?j#G4gQA2?YtbE"`V,[ob5
.Tob*^r'G3EZ:hT!GmW<giU:B&aUY4<^GYM6(8#:*d5+plicImL/Y4rj\(.P0-AC#QpJ.hee
:\Y.=Jb+mA$W(8!2Of(kj?$eAaQtQhoGk'GI>\@Y5'N]A$l>[S*@90jo%qBB"hC@>/#FeRh*V
!+0XN%r#f$_>1!S)LYAb<^/5]ANb!f9;Zd2p)TJU>hIep^k_9mYU)/+I(>n<7/QC$;<!@P.'`
VA",YU''JP6<(g_`qnQCtiEIprDYY$A\6G[(uC=-ssM^AOL_6K!,`"Gc^r!4`eb"?3@2b\qF
)$HG9d"d6CVYbLK'h$p9YD2-jQA8s;9!Y@dhjO9fQ0R,&TP5UpnVCC^*aZ.m5T,Pb/?Wklt?
l1F!3FPY;6)[?S2+oRG8`0MctN^Q/Lm0"mEOXULi*uG0.lL5^D<Z]Ao(D%W8i>Yc[Q^lN&7+6
K*biE7f`2)8HG4'4/C\pRoXMtEph&)OM4nerifRWBJ6^IrX2kC_&!3^-]A'n/220**L"S1V)e
HQfTl9VeCQMV1mt%!HEZE]A)/nW0=)cq##WbKkb:#;58.@dYe^`8VofYZk"t;U.+ReaO2M2N5
%(iqjB6`%*F%.`UH3]A/DhURI@'$O[YRWc8,D16u!>)Al#;+-:[,Xi7S6*kC_og^A6[<1RY,=
dL01$(oTjJ-:OMQ><)oaAkH^JpZL)n7=CRDL+ro%C=+hZV.$JuEh?Q+ZQ3qSZWH-8.g#:FF2
n!p=SB5&S@9UKtapA(9Y6aYeLXkXB;G.j4tOPOMX*GSh(!?RRH74R\B0<d6;XHuB.jW[0uAU
Eu0I-Zc@\GC8:5;Tb^;gpprSY[,P1C3o9h+"Y-nd?0EY:C:BJe_32@qhhpcJ"hadZ9EEF^B^
2Giqg#O?fB0$bP"N`#FK!EKO%5U8roP/a^nr+#A65SY(oSf0/oGSO;9UB?o6lh=iQ]A^^:g+D
F_mB]Ad(JYoP;u03)A@]AU.EkuOUQ011YY!oFqo(rdUVa3?@5R5&uC7iXEn+c.X#%dLY%T`IMM
K2b(NWX-g+5*GM"^$H>KOV8McfKmC[:OmF^P"2T"Xs/KR?9*60a3YrDF1-`]AZLIoa98KDB)E
Xb-7<od[>:rK?*XrNbMS?mM"I&`pHpEU/I"FDBnP[6B9!kK&sSFcBTGBCF\\DK#9/1TEJV>"
+rfWQNOt8DZ%!KEkGk@`KP=Ht.B?IfT~
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
<![CDATA[2160000,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[720000,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" cs="3" s="1">
<O>
<![CDATA[店铺月销售排行榜]]></O>
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
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="1" size="112" foreground="-16712452"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94jA;g4P*j0&)!0\1CM"O\03`]A1)[#`--VZ*p>/+VX+#XU8cb"He91#R(9$g1cgU+dj2l<%
pmX%$Y`WD*b-d&h?t^80U>o&P73]A6\l:@hD_XOTsM/drNQ_P]A!Hn%-i\/SrOpMPT'uKHF77N
6V^2%$)>'_cO,n5=X"SDanQ@.0nbH>Wj,;kHIcZV(I=,?n:OIH:CBZ*+q_oO#q8TQAd_34:P
dP.L<Gfh]A:Mi=$3Nst.S&`.\h]AN,%[Q[(8o\3Gq$B2#l%9pQK=7c!RrCOkj^QLB3]A43dZh7m
m7(QKHb*Z#;V:Joh79\7?`=[J]APf<$c;?g+K<p6`.Y#^R4DR9rVRqa*J[X*3_jDWsk2BcfjR
hc)#tn,1QHh>sI1J7l!Xbf3J1m[U'4NJ[B%5J$*-<Y0hs,jnM/ULUZBgjPj4n_'3Mh1VEf6C
3fEkRCD#Ya4'Fk/i`Up(:'PrS!edB,@\WTl1!tH%+=KBPM*/4>sXg7a#oljJ@b-_>CX9"7j9
UkNd0!\7DsG?u"pAj*3UT6M1W8qKBWN+JXZn>pDRkh5sArfGCf\^WPTjf-jeWF^<I:'O.!QR
ORQ@Pq0f38^IG.GkM,Tejm%=/Lhupr!rk"$It_Lh@!U.J36uI_c*&((<X7uUnqU\*K&JmGQY
89[oM$e3?JN4K5c0iqrsVU]A;9u^5:B>V<M?tWC[asP>JqB_,]A!mQ`&ik$3J)+,N/mIHKkStI
N#c/qaqs[SFX]A@nLT8[B?<KlR<DY$8;^R]A`j4Pfid?dWum;1hf69CsBlBP!s<tcLk3?p3K2f
.XN<h4mSf3Lu\a&P:8WG<:Bl1,M7[Co)7G\n.$)iN=.P1>S.lWQ&qhFOrp`GEt>-`/D^^IKE
H4=TW"8nZ,FU%eS#L&u[=@p\ea)S&ujSpD;5r#(cqa<eO%>6!?6G^$-]ADmAk/R7FL(ZAc'0p
1'<B>;0E"9Ng>Oo==ANJP^<Y:fL`XH"@ASQ'!)LKS%GimQEF"@&dPNj/=:f+0V1i:_'njZ+E
7;0?rM^g,=l$DL?Z+Pb,-ULh(*RF5t@\2@8[6a(rp_h+D"-C`Pg[^S`RX5dl4dpDc5^[@aM>
2)_$#>V8dXg@OR)o_#gHb6+H%f=lr5ThNWHo3st<CHbVQe\.4(TW^O6;:FhgfB5TRo+FXtF$
_a8,r@\2=t(Un=QBYFQ.HZDap2n3(_,)n?4am^h_GkD]Amc"1O+A="_@'[t<p>B.D!;o*m^ta
8n-dgV:-94qoI<b#?q<.K\[@p\s0cQM-iN^nJh*7=cAkXU3]A0TTh=*nb5CZ>fDiLr,f:i1]A<
Ul&2+4XBIZlscmJnG:Z9kR3/AEnm07<ab@Vh]A33rZ30IOEl0!0"DX86!W?:*ib8iq<l9S9Kh
",$i9E>ED.:1k'#u"flUCZ)q4.'EYgu%HZPb&9P,^o3aF"kDP+;)fi%0pDQCir9\,_FlgCl'
Nb0i]A\]AkganP1se,%Zed*VLSA91@u,-'mfSH(:RUT%e_7m?TM7AE$[`PkmFHnTh$\\>-"i<q
?M8_s'/JM%4-X2m[Xp`@WtafcAci2`F7S)k=%MP!;&3U(!(>$Yc#3"5G,&Z;*#`p=g,$NqCp
0rJ-7j%+L<5<>L"l7EK-PSdU=l7&A_tE#)Ue'YDYo&=>q_RC32@$,-"uZ8ddSG4X.o.&Hke&
i>G\*7j0S8b*e"`;r<r&r:DkkUDa>1,D\-+7_WPh,FF>Ra.Li$0mXdk25%8?Bqb*9RO<4]A'K
QR(aO7ge0=lEL1:4Q_;R+OCAnum!):_Mc!r7BR?jPb!P;<<QF[#(H_k[#8u]A3bmR$H3?8FY!
,Ia2fm^KjKAOa56n!iI_GOlHSHiHB==elT+Z7am;n>fkfT8SS4-Yf$m>5'NoNYqtfZSZVh1:
"sEpT_r/Chn9h4IJ?n_1]AZ,R'A0^V.n6%8qAGN/k.__OS=0.12Q@-NjmElEK2[iXT9NE)9%O
iPio`<g0q6B\3`&u9cD"+)u3=I_eXbB<d_<fTs:"/J^0R!,6l9?%CG?^0[a.)iQMJuMmP0)k
7B\-Z'0P<V)4)M<-(Lc$K=uWL*n>"g`MABAIE7CAm0NjQ4`00c/IWQBFp`.pY1^gZC<($L8!
tp8tP/(YGX&._t%EjUb[X-im0A2^KPM#'nh"k+CuD`^!H@.3cB46h6[C6d@IZ-a+cYGHMY'[
7+R!_ot/PJ0c@m0Klpg!PO")1[^"T<F7Du+mj4]A3rNqK*gbr\S6LMon9)c%]Ao;@e5pc@RfM&
(J23egCfWI*t>g\^#RP$]A%_n6t]Ace@h:[5qN-YrI!fMjf3R^c_NI&Dl6hnO>nRVqMn/%S>^o
ZEY`JfS'Pe+LKCsBmO[6K6Z%=/@W-;jVJV9ocqs\V0i<*6I=1PHEcKF+[-aQtc&Be/W9P>pf
Q\pOOr0&i(UQq"GO&0+','Iu.Vfn01--ud@Ep$odcb/185do8IJLLEb2_`X7PtBg@'?_sqFb
S<7IU%ug6,I3R/;XeCAR^--1OHNga8@7k@9H-nFpM]ArjF9[Zba.eSQM)S]Am5nE-#/LC(/aEu
MUTh.aRmaM7Q2[13HDst1>Nj2/_:N\L`5r!g3Z+RU>;.tJ@*[?[*.OSM3'\Ma&f9./6&&#a\
&9+2WX(),,SSTC:^h9@Y_K=<`3]AgKpD2:X!T'F)CB3@oSL0oMQ9@3lM)iY@tSFI?q:%cD/SQ
J,?pX2&MRnlZt!H+]A%m9[1qds#C8IDr0Q+2E3UV7UMJB[G.]A+Na_Qa#SD"1W$3G^9?Yu7HT8
qp)seqBQ)lI"UJ4NKrjQeC#h.IT)j#HD`;dg>V?._#$*K,_NEdmYi#QlQ"j=IqT.+(pNH7cC
rHXfc7kU/?3-[c<\!T)8B)6X8aiQ0mV?BW&\JbH+D$4hZ-r(1HLpl&CRWEVMC]A0p8phjN`DO
OMg?cK%/V$>3(U<fHJjNIp%7;!Ip1io@IT\'ZA3q_t$+dk/&pu0"[;,[`bl'XF9omSa=?Z3-
1mW&p0`)883*Egqmk@:pjH'Mp(SRIY1foOHtm!pqp]A#h0e<8l0?Tl7oq05@HEpI>'mfYP2*Y
=m67o#d\NKTDcIFB,8/BE'XbG1Au!iDr-N2E9bKj:=ZP+kqJnb(UWR9)8DpCB2PjoO4%nCp0
U]Ad77T4H\F]AVJ@du&Wj?R@Id*=A+HiY^@b,>0JoI*JF&>&]A"MkoA8RU^Bfil1\HeNX;hq/bn
GdApl^mqjhTkH-N(9p=9hI-WIE%J)(^2qKJ^K!id^:m1T(Rg7'@Vh5H*d#V-Opp3_o\f[7o@
FH`K:Tt&OQ't2AbVl.ZAO\pJ)r#:*hE.qJ/k1=0u&O`4qj1&Hp[Q>TNI3e!6G!.lE8-fTqVT
XD`,15Rtd36`qIi$ZMgC5<"%uuuoH/6/YX2/2:F?KuJcYSnrrU;8FT<(i]AGIpn&nD4V]AbI(r
e,4!L]AEJ6KI>j3ra1i.9&G1h,3U3%q8V!N[f9PWkh=<dF_+&(0r'E="I-#2o715gUJ*k0-H3
P>(?3X3U_pe=)nUNCn"(=Q,Zk\b$>c:f1Z+<NZ&&sA0)=q$FpO(13tI8M$!W:@8NFc8+]AC,(
-SQ0K_Y7!.9?AT@!LKAI^%roa@eC*/qT1ekEp)>D=UGDZf*cU7A&TQ!bGcp2oH4(?pHhPjiZ
jU)T;@I!ZX3[]AZ4$H:slKe.]A;"1_^[=tP]A8+\jP'^&O\9\!o2Dr\R(D5V=6cs*jiF[7IWL[:
D&0p$^4-Mf9\O>*GU"gi$\e.dWp$\$^1ooQ,?A\k#//c0Ub_.8s@I[L/t<e*!61bhFE,eu!k
cg]AXDd-:\a!iNJOr`^*fY>#J.2$$gXHAH%;qZktE$G<S%tcJqlT.?d28nO4?Ujj:%'o#-7oT
g,$m=2o@o0-:Jfpu`1F;6Jh`7l&X*9,JiPV4ZPt;e8B.U2=:tZ[ZI>%^.GgR?$ulAC!b3_S5
=9D_(@--de:]ADo2XuW'D?Dg.EW(KX8YK<qYZ_#3&f<,b!J88lA;V6+hW2"K?QcE]A=$jOd2H0
D'.>B,3=RM#7rlJD)asiNG.;YH78h7F7A?,D'<3b^-%B?N#c#G#Q*Jfmiu_R8RU6Xa(4i/E>
=$0%.f4ZrTB:Gbpap-f*2VJ*GFjqUmS7/jh)EP$'.J"V:lJ(O!@:aUm>,7qG=.8BCmR\juG#
^?Joq6h2Q$JBDmo"_IIfj_XIAR9kmfp]A/A`;Zt`YL9A0V(Kb6/6!;lk%Fgc>q$h]A4b"u'91p
#W.Sl:O;i6fA^g..tC1L%dr&oPtX3,]A1&bjN)lIV.]Al4[=Hn\6(iir+9;18ZeMjqr<Bk5#Tt
fIB'lLlQ?YfIO/'scr^),HWGg?4_oq@!a4ohYR*7g/rTJLg;^0U-DE6]A_%rrrH!'il?=3f(T
+[Osd03s@'J,CH)"D:Wmf(;1X#b*t*!HOKn$OGns&D6(C-1*L.JTB.4&383!P!+;GPsY63&n
Y0rFpCjrbra)@d<P&5TAIM7<":gH_+6KhirX`"Hq.QM&YSl=H99KpTU9'oJ/(fD<#a-u+>e6
DPb.<0:rQZ9AhB`b_H'6;E?J^M784"t!O$a<V>/Kulf#)2080:/_VVjCPW7X7=u*\L8H#WJ-
ncs8f<t]AHDQ:>t%0@Y)Q*lmOk#MT49tHR,?+]A=O%/EGXSQMc1!EHm'm"L<.d3l,C/&J.,$#6
F`)R0j/BAHc7Z0W6nY_Wr\#YYuVp2OW4rfsC3DeVWK_mYKTO:`L.^^VlW!AYN\HHDhKTgF\9
&P9SB*UbBE[dA_T"XXGhqDZP3jH>"(K+#DiD7u;(IpL-V,3Th.0IIFef;-3Ypj7f*S&&MRd:
D8q&G2Fm`h`ghi3gCW!4a7,XaL^4-<Nf;4+<1@K'u_r33!g'Q`qMHPDaut6J+c1RhuSe^Q\R
0W#PB45m;GQanIbKR`e/u=3,k9j;,2B""D@_Cu-jWEnNjnM'6\26KOmfN^#:!-PioYo>_Pg,
OTAi*u'9_5lsM@=u--G.u5>r33DR^)aug6S('uTeI(`>Y1^i%od^oEF'5<+--/m[(c=kMecl
JN92bsm5K""8P"NEVi#&og=NVkKF$8o%Sb&V_h)()ni=c\Ml4@dpAJ#+!2'dMJ"<2@*#@NsP
jh$oQaO(nKm$.21NBjeqO7^BH<O0:Ad@T`T16VJU5UNYplo)O#b5V1cf1<af4/#FjqaH<%K5
##3E?m!(MEiJ%L7"]A"E[3CV;@%ZKm!TqCB,V,e@Kgf-J-Kl#M2i+L?m`H'Q\5-#<?ta%2lXL
<h&?fP">[.d&5)s3E=!FBhK=tqit(F(H9MSiFJ6\+9-B3cj/=)G$D='q6ROD@(TqX^"DrB$\
.s-XGOILH?Z0_DV/'^&BKf):3B29OW:7\YMWl2aA@e,Nb@F6CB]ANDQ+Z>s,9`S0?-:&<?\-q
YKM4)*kO<[ZVhH(Z;^$S'n&ik+d)q8F,'V`;CX>qZ[K*TZeCPmC[r="<Vb7s>^3KmQ)WC\]AM
"MKsJ<<rg;h1nD4WA>`.B:3Q@O2VJ3[$_e%^&<^RaLr.n2[ckklJr^b'F9p65et:3>5Y#gg9
kd\>&*7&A^$8ldbTBlQ?2l6^tOe]Aa9@Du\8]Au,53TH4IP-(4n@eS&U;h%@mMW2:&2l1AX9*R
G]AdZr@]A/a;dDeG(P,K6_a5fWIVYaO0"P\2-^QW_Y4B8U:]ABKeY"Qrb>u@F15W`A"J41b"ka&
C88S,N?cS_]A6"=frHZmos.Y\N!9[<-FY"o=iR<E7PS\M8:tfDaJgnm"\ej/`.Xtn<3\f50cN
MHnH:$g+"Ed1E=h8e85KIG(HbfqQ8jK3)f%0FPMUkT?Nnc;*nbH5Hh_Z4!I2uY>(LD7?o1nu
Cfc)$a%"`q_,fW@SPqsnb5XnZRm@k,Z02'>.bAQF#.!fTUmRfNbf6I3-faJtS93pANIt@R=X
'O)6=*dsc;2rGNM(2>f"dR9[h3$(WQ[3@dt'ZC0EUZ0?pB$f1O(cM;:6W^p,#f7@#^.tiC%5
.2m%3*!!iau39QNCB,Cd+YG?Tn&a3ub!U:)iQ7@"4L$iAX9ajjHi!9Y!oNbiH?l4B1eu'&[a
JcaV#?Cn=]AU4!K0J@\t(\DPaXEgI:GAfC<MM5K(&2n;Y1cMfuO%n'uc#ba<>f?BocS[eT++O
^K9BZrEDTMhCfr*p\89A4ODmK!O,'Q-==uf]A+HLk+rH_t^H<U;=Jj(kmKol<MB""o4Va)[ji
PQmN?1VCCt]Ar$CB!l[rPJ5GaYm5X@^1UJ#N\qWI(iuB:6"RoBRfG/pgLn7O1gZiXp,'&E_,k
4eGi9hnufUV@-mLSpscjZ,3EX":0mog8?s-(O&E8,"L?LpFdlWZ3TA<rFs,;3K6#CME\`W9E
82Xrs<&fO(,Y^84PF#Tf'mB@3s*3.+nDNlXYJW7ue^rGPm,`&hS`L*n@'^V``O(oRq]AecGuE
G0J89"NuX\[7g`dC**bQ@<fJQ77KfCQi_#N#DI?Khm?Xn\]A(AnoF,4+`_GcmE^Vck"*j#*t-
Tg^S?hGRAhFV@o,]A`6)j&&4eq2FG9?.sarP^ibQn,?HmRn9L6pcpo7$c~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[KmJ#,<:ATARDgm?1:E-iaPLAqM%n2W+_?lafge,t-m?eC6APuB@bO8:Ou5#G8n\PTCS[&RA4
1[f9RWMi\(C6I4jRJIk1o^=]AW_)&^9;lQ/7m.&@6kbs!!%[(bY@g5!"oG8LenKcJ!)GM'`n@
b_%rnYgU#MHp)QeI[S(8(4F"jBi/UbIi<Q.pcVD7BCKQ!]A5Y6b_&VElJS05d)0M"KN@tkh''
TIu?BeVu_DL"If*DNj`X(ttAX2?V87.`.Ydk9S-3G0=,qqNn#luW#;CXqYa`NUn7eNUYO_(4
.9VoIdNHoBW'Vto"Bcm.Kc2q@6cG:ZLW!5!Z*8kp3e$n#@8X=rcl\F([,i$5_BNe1=QqR%aO
Y5^kmeeC8PI*/0q;3W??b6rnIX=Z+:Ktc@_>@PbdGnSc*Hf<A=N,OI-r%8k_rGYP,L`!eM':
P2nDH`1HdlU&<#HJZ^gG%b^qZs\mpKoq!r<*tS1R\+r(`YE%>"T+HO0_dT&pF9*Vhd:;LjM#
6g$XDBA6\_%(Hej\H,t!L0?C89c)3XUL4W*M]A:.XqYlR(LKi#$&1EX]A@F'HL2!-dGY1e)8K>
*sZjB8e[E'(m.EH5'q@%-n!p<Y]AmBIkIbo"J7n>%EVeLpF)JKfnVdr8!;uWMn;/.A(7X9pjR
K"?ou+<6g9m%=\9_o+XI-"5c*K#-"m=iH>Im$p^g)Mcd%CI`/W=g1LWQ[\sBquIY_B_(+!DK
Znr#8@%uIUG(a*P1FRq_6WK$i.>M2<$DC3J168686@8+sorc:,=nh;j_'5%/90<HYH>#2!rA
$:a0#H($r+J:J6HrH^,ia4sp'uE,?)AO;p\p!WEgCCOdUl9UW9ni%k#tDtT`m!d!SNJV#VU7
\RNk`.r<18[Q3<Yg::,]A>:7o;9pqjIO41a"-[Qo>R2JI*^ah\,@PU/I4l_bmu6[-Kl30%L"p
XYtlZS#,]AiJSbb#MMUm?"h>9EN"9uo6Ffl#XoW83#Ve=Xm&'8Q+k(="Q=qrCVkBI`cONY6Dl
3)46@6fd9HBXXW#>3<@"EVXH=:(^ZcYqA<B]A\')&OCWDoH,7q7Dbh#N+Ge^HG_#&qM@7PN/&
]AG[Z'H5*c;a7jG,"BY&U,.f_[dBnf(mM>lMk]A)eE'L6:,a<$<`ot,X<fTM1:5m]A:<91'^GHT
p>Inl'#o#gMh2NRaTQM2u,qP"I<o4dua#,$GBeF1]AqC&GUGE*6H/@UPS8$J_5`$r-AIqRZ_=
RK5a/O57U$oWgQqmANAE1kTNl*#YdWVQ7o<EUm"q?9n?NDGrV>Fk#X&l+)9)>f4CPOEL)/j/
081d<]A3OX4Rac8CV\f=06`<tZdu0*+tp',c1.3U28g_8Vr3!-!V0:aYb>6A"*!3c_PGg-m:[
V/0thuLO/pP4^qrBH/g#bh6EZ,H@*Up0KKaSJ`=boO@1b.BZ!7gdS=J]AB;U9k7/\k6l-FfO0
7md9-Y;D'NcgCniUp_%u]AFN&3!P@!+3<g(O+6\3o0$g:D(4kM>a(\Z(^:6L>$HFrKE\(u2>S
cHJ;s7J%Q%aoO&d=cX:P)PVcnK[9F#c[pnV,7G<GabIgi,1dK>9^0ADl5"1n9YGmGRCQ['>@
U8'eO0#['V=2g,_eNQL#`M'H^rf0h<nlH4%^6-qj0iqHF9HIc8um/mO1"Q&%/>qEgrQ;U#tP
/IJ@bk2SD)G+WnHS:f*SdPTaQ)NpoiFm$$5\5f;<GdfOdm]APLmHXVRCJlr0ZWNQU@kXL,n[Z
6es+a%G:EQ<U7LW9pYjniY-$3Fh5N>mpqS&?$Coe6\ftNA<.C(4h4ERZN7PdZ:CDh'$L'e'?
D$DYrp>+aeESWf*/="5c0-Z,r@>GMs.<jmW=uDA:HAhJ8@7k:cnX2L^\/m=Ns/oc.`3l6Vq+
598<,Fd6e"bpOQi-I$SV>WaL7b2KJak!MCmr$;.!Orh,CTM@b@-`c=&[TM**k?iM#nq).n?(
flRG+@G&)Y:""f2u1.qdfT56h$bT`mkn\K`+Rm?R(p1AsL@m.'=N,XY6Ap$i-(s*$tKO@_P,
jT>^SWBJ&%]A-tr^/[7Moipse<p'uQ,(N+FX=?3l9!6fDH\$>LFqe5:^VUsuT=p"SP^n2@r2_
bBaMrk1?H8V'W0Pu*i-Yc<*0pCfFp)OZ4<Zjk,5gaB8IDOA&`,pLB`5CZa`fQu(@#$06`"aQ
FYfjf]A+sgK>3&fc`i)0ck()a()4?*"DPmJNrHfC,H+KU*0BupheQ3RR?"4bTWDo6\O[l;$d4
I(aDXiX,lt7Pr0b?_7oP:?"C<\f=_LeTj[Hk/UI'C5pNH@n2GVWi>3Cn9I`7+\+[juO#N`=9
AF`7f6k6XV\U*GL[jPaJY8O`hh6h"//8I>R@]A[j6hnpmQO!3^C4b&WX23fkqPkfY,TrsChDV
i=^0&q"78A!,:EbQThbE*!>4.rj-+n6?)0VZ`#A8e)a_Qr9^Xf%cL#q2XJZA)UFsKhuX([El
o.8/c("^fP+W*6%V'jtK4@)E."",+('cqA"Zt1V+OVoHN:M1VGIY)%!P;'c[IT0T#T01.;@+
.d)CFH^c:>*$2#K]A3'nScegu,F%3ZI+tk'b'Ji<L2?TO+*k,5aTEc)qnf4]AABk-.D;%6C)75
J.ihJ%W&h9%G,i8+JfYX3sR78>)D`N7G%aJ/PnkS,4^CN*GUH=W(G.S2p84H@6'!:G\]AYY1i
13\2(YkeuXMA^*giEfm/p#?0@l_`UI.^t;p9jQS(U0I%g'Tg9Y,*-0BF'e\$E52%HVPGf:P;
jUJ$<9d&8g[bZ:#hCnsOr*r$gmglfe3KIUB"\b5JM=i7+>`UjEV'0/PKa\kH$;RFZ7lVV&=5
YG3NHuno^Kf`I6XioreNqK[7#Zo9%Z@<ASUtb74Z`^pJ-qJnT$7$Mt"c.KV)o_Cjm7"NDfr"
.tZDpdBWc1.&Dl2f^f=niYEGH""tnaiR'#1dTN/\6;g5\[ESaQBSR5[l.LoVa0S_P6Yc\[ki
Rg6H8^luFGkkU!.:<h7ClsWe<UC.)\`"kc4S@pPY\f7.A@d^GcRTg%4]AH.`g!R9:O,n/@LHB
\ClaBQISP4A`ZOs"`;S]AK,7BM6iUQu1G0W&7C3/^'$rue'XI_u:acWFHSZeSp?[DJ`TE+nT>
HQX@e#mfH:"VH2*@\"$@D7`ALqba/$QFNm5kk&D$ioMH4FD-=9S?S3h6>a#3]A!GRC]Atpe03n
U/(BbO:3g+leJ`QOgfPI`ipk11BULrF?gG.;QmZ]AI,F:Y7\^"8m:@k;j7b0FP)[:)p:@so"!
KBfQ(9PL]A`eZ/]AhP^<Gm.A7rD&p`HdC2O+GDY%#F@GPppUl%j3"T"u1\*PX7X$$Lak,P<i9>
`!7?p"AOAR*p)?Hp9^g.E6?323q.c(?%*HZC&f?S[>of!*D_cl`k=dLGYZ=7,uD5,DN19%m7
j6@PCT9\iQ,T+?r3Zh.lDT7R&[_PWW5+IHu#[)S]A6\p$;OE(_ci=BsInK"epF[aXnKkm$bn;
2R?['X_e,56*gS&;\ETT\'Y50N^aJ8]A<[Eg(J)rgr9@\#k?W3Zl>PfF_ZB1!/-ItIZ%45f3O
JXO?2S=e5DlWOWN5\CQ3JuLGonn9!JfRP]AfZ5@-]AJU^cF-V"dAK0Y4=Qida)OFf$K);'"Iak
=6aB<Y]Ah5fHhJ5gQ,<(_j=G-@!F^j@o+P,4e$'V#n$rRn#D4=X9'N#'2R)b-@Yruj#&G+=R;
SCup8E-CgP(VZKO]A!o-=A+HG#!Ga)oYZl10[g4AmK0e-(LkRd3ZU2VbdZB-4=/2@:d=j7b3Q
_<5F25pC+m.f4&Q'&Q9hU\/etSf*a;'46YMb1%]An)X^[Z,6V<Ur1BD0GlMX"QK)f4';ojS[#
&Hl\aF-?h,O=Yh`^l@W8rp;q$X4UI<6B,!8\,T0Cdg/g$/d,3C^,TU;nJfb@r4jA[rI(p'4[
:h5#!LV2isblqH[i`]AqI9053;B$KEArT0LjMuO-AoQkkaY$CVB`1*fHaT-7,Q/Bl9<@j]AuO"
02R5?'\T`Yqap7Tb`Fg^C^s/Qc.F]Ak(6&BYHp">QEV(X6I_XWPi0dj\:qMd%Um!&n**KRVMu
[>P,g]A"UI,0X8iIm$K>\#(fE9ShV+;n)C8Y,Q4F3fPt<hkHsQs]AQ7hCEtE+=U3\,FhKHDqqu
1H?*1RG=:&V4,t@eMXb!j5laL?VaS;Zb/:,?WVFjp7,)Zk,QKO178^uPWPTr1IXng"PU%a=)
Lj^$+!4e[4n4_/E#`e)Id]A-@ZGV#2A6gWkZmS.9T`XLVQAW9p!3dQ&?(aFRr02E%r9='k:k4
*aPLmG7=F.E`(`!;%%NeRR_%b3]AjZn/U%FEbK5'rb&#m,Vc"X<%P6=DGapaA^E8]A*'^Y5r,E
!3,W]A(c6$%"YQb6:s8T%:(:Y5]A_.4/8h!V?%DJVA;2O33W@b;;lE47+B&'T^iV'H*..<9`R5
@q@\\Gq&V/oZ$I!c$13u(f%aG*ebqTTB&dB/>3V$BmscDuerlHW7p"YeY&Ye:muDVTW\X1*!
T<BT+9Ie#X-94U7g#L=f>TAf*lfPrcYXrZGZkFc(+#Nm!?IjlQ[%MV1rO/J&Xh.Sl\^Kj/@<
8MN19ack$+_!HH[@)8%C]AKZ@',P_9!$D=CM'hb_2Q9_Mi!<+\E>Oe-4BV[e['Akb)'+O*9@$
;j%gU^Zi"KUN1h_s.D)(]A'HrRf"BkTh^1\e5]Aos3W+*B0q1b3la:)Ve3-0:28#DR<0rnRUeF
0ht$hbda#c?,FQ3g&<l1eWX7.p^E"4i5l(?*^E&>4Y8?(5Ise5+`A*#+969H#gGt%]ApCM41k
f9qS@O^mU>a%''CPUl[=_"P1uN8/$>e3L!\[l#>h\BLPqBp"`[Jh0p))T+<QfEHfbY=/7Z$F
=La02I57ArU*cF%KSX$:Cq%aFsm-<NP#@DT:/[)e]Ag%!rj,X3YFD/K<#J\RoA.m3NMpo5UOL
$u="auI8nLqKot_G.f&H*0-1bu*J>0(6k)GuC'EQ\g>"hSZ+r@C&,M!G^b_LgWdSn(mcl[rQ
'!6a8!X2h0f9)NT06hioZ.s3]A>Kd(/tASaR#ja,3c_/r+ojN5sA7)u7&;A&.bi05e[_m&TmX
6M!C(^X:C0c!EJWD$8n$-"5&`LBFEA20tDoBeeC&En"P0",e;*rgSk^1K.Y#UU"(]AW\#T@iK
^JTl%sMJU,09E=;DXWEd!\p/6XGS,8QGP&RT`PEfXDB6j%5o>HT:PbD6%CI3FE97^gEKicIp
YlejWEjgutp%[,RoA3JPPEXaWQ\1p=^1f/\"Xq#[tD>9T41`SHh/Ik:DZD&%N@'A-=ZH<eYk
,pZrRJGEtgpikK>1Z<_Bb^e5<>"$r]AQcD&<:6B>cJm/AMVKN1(^0aaahOEOR29*,4Co'1,I>
,1n3fKq'"B&Y*>Esd"[?CkU-7=FNeqT$hAGTXO6b3q`F-cu`:Q=$<Y[TaUOmu`OX"m64k1GT
rN_e5F,:dDKKEs(@Ej3Lo_[Ud5C$?uA`&]A^!=k%,dpj+C3I3`qV76[hBE9@LY^(>#/.D@7qD
6L0JBX6#_c1M5X8hasi=R\Z(]A5K!iiac5_`eTYqI.S/Z.$;IZsO3QFUI-<Kd]Ag7Tu?\)/U$F
7IOu\"6?,0$<Rq.E]A5Y9q-C=".kpclrUg;!Ef##quad-c[cHh'6LDi60))j%\BP_'V^T',"n
02$]A:1lM8ETEPFZl**[LJt]A-r)^$_+ma]AWSLP-2q:TsU]Am2GP9d?_2TPG<=eonkUq"1h(g#V
Yh+]A6QO7!e#./fJ$8Ir4UKTI]AP;m>.V%B/uGK>q<PF\]AJ=+F3mRXi&-j(Km*WumT2-]A_O;EN
GtVdU+^9;""7/bW*RIf)aSW:8Op.0_lRW=7=\JS:BUt6:2=SL"@Xh4-H1'jZh!&YJViLY^$d
'^oUBo.DcWNG:#KP'f$F)r*ebMrU"^c0VJm'FF&Tf>IiH$H7$b,l>34^Co17iB(AAInZG6j)
,&#h%QW8!(0)e`ISqmq%f<W/DY_k-9Y+j&r*UQ\u>lnN.<K(AR6%-Ad0Io;$DRWF*O6jr,6O
m&fr$X=,/8H.R@%8MC:Z<62<`7"0dgsN;+X]AIQs\UYBo^;T8k[0.P>lPJ.U=)Wp7d1$fiTAY
X%i,%nTs"r)m-Zbo\^G`Q6f;Er+02Ok<"LXWdo#:U9:nWq.4mW`ooC,^,O4#Ls(t2,\bP.J&
Vmu,9:uK+CVE(J/R9M*.7rkQ:<;iPP]A%1?jG8u6JK3GAVf`kfEUL/:`k(S<<*Lb=o^;>.Ud:
^`l'b9?YcHm`doW%:`Wk>jsf^N*)2Hf<q(Fq^"E*kh/qX);m,$-A87s(flA5KI/5p(5oM`GV
\LXRj]A+s)N0htDPEaSQD@2nBf^URq%F@0_Ee(q:Ct=I(@C(/).3I+@S[Q`C`0p)71;DWkA/e
8*N83_pm38pI/0l@C'r0B+Y.;FIb`VZlu!gZhT*X$'$N8uFV7\:q2;\_j4ac?U@(SOp"gftk
p==.k,`*OfBLKO3[3\6$Zsn-jL_`^1#tJiDT2P<If-,.rNQH+N1ubl=SWr@snE%^*CZ1E-J7
J!ubuNtbjeqCS2!<-S&,,+m'7Ms<Dq/Mo_3O7RHiHBf/Q:lk'Vif_aI9;8.batIe1ao,G/@7
sA6$Tt1I`'SX/]ArU#)khi#"Y1c3CqOU^3rRF%+g^VSHJ[:W\3[(=/1%^d"MHCoIpKs`6B<td
'b9`aRX[GJgfCA]Ae3tD7^Nc3ZKE6Wt^:S+0%C,Jg2V6bk'1/WNNU(/6W3)4$;>u0st2Cd@t4
*#M\MI'2F`>c)g4H90SW:!(Me;NH_l[MY6dc!4NIqb9+T'dG5'i+ZY!`Scn%AHhrLusQ+s+k
rl@aIV^GhbOjon4:S`h.6NC"*a4a2Q#7d]AV9H*]Apk^+.3"2qc*F$RR$uNHEWcCGPi\*;ltV`
;r^Xr<Jm`QU0JU/s0mNSKtZN24?78_k+>[LS,3calLi+lbq'Y#o7u1\k%DecaP^.T4ON[Pl2
?+-9goPgF'mbq!e&C-Z1Mos?Je\hIui%Iqu>0@>m0Z4l>qmB4]A2J3\.0M;7^k,mY.:P>?>Qh
FIEk(!fa'MEpLm,Z"LIoAo0gnh&ci*cTMm\Dp)eG4q7d3Zb=:&81"8tn`#4144.W,2h>a!H5
G.FX?H&a\208*^=3Y/.^IO$s2-eT`PlD^KlKI,[G5b7L>b=PHor/do,#+UP7:,/?bMAQNT[n
==#'_j%9ET(;ghXE^ogH31?$c6`!Q;,A.nVMN">V1WTJHO;C1%]AFBU%f=r:16!U$<qc*^0oE
([2t0nSl(.#O,L*3%Cba56[B3HZO2[M)pIP*ESkOS2fc60G/]AcIF4qK#I=1`=S:A\]A$@-tdM
WRB53"hD4H]A1cLu8@u=gqF\_o@dt>\e,\f-5&Opt?P[bZ\9GFXqNQ1OmQc::j%0]AL;o@=I&F
LXO0_sraAr3:Uj2)lIF;3LP"@bb"",Cr2Rkej.u5k$ka['*^d4YOJ354_'PK*C]A(B#OpZFXY
Y>h4M71Pq7^oal6h0CNnu0Z,'U_M/_J.&*,&d:5AEP-?l<3L!G@BjD]AZd(TkGiUr[)Lk!q<2
99179Bg6U?lAVMbokdokt'D7Ra37D:e")0*r[M5C+B/><U(I4"0\^eBHH/1c/5K#(DeT`NVH
WZ;PD-uC18Ho#1`#V_^g-6kMK_)1ISP>@CsRrXWqmr&7ljaXIHam=Y?Q3tUH=dU5kYEf3F-H
s@N9K?'XU=59QH?o;^'e>"b-C`*EoRZ!2IkJr)U,mpSPUkVpSgLur;&CuEXU'<M/]AaGR;%/+
;'LIsB*&b:)j'-8TO]Au0,W,;[]A_Ei3SIOXp[-El:L%j%lGUZ+-b0R72VJS\3M(3GD:Ln)bWm
tn4hL>!NW>Ac`.&t(qXG>uB7@Bn8DXDp.D0EG;a(Y!8kV2(pb5oQ@C"kgGq:M;DF5#"_MU/t
VlJ*Io._bi19,+AGUNILf>bB&=M>gDkML?,q<JFE6&8+h;K6#lNQ)n>\3cQ5[6!;oa7+2l6;
k+jK@N*j_cAC3oO?#RZD@77Pn'Pc8Djnu0Yd^s$6_)d.=dm;<rZVAQRiPHr+fU96@!L@ER4>
7C8-[temM/D[nYDSp`m's!u)]AJBHfOSOOXptZjo5EC_`;HSh1DZqse'VELWqe5;'17^#VcdN
h\R/;V.P]ABD5?k(@^=BF3r^(/aPXS='`2)="H=c'gAonoXGB\4SYR;/2FV0=^aQ!c/G&1@Wo
@JcgNI'KAc*!&'q:OXRQB6iI+r<AAUeso6V$osJTl$rq0Rj:<q(hSN-;(^.hM#!D(d]A9Gf%<
n!>r00_;mP@]A^DDX-iInZ"#dqjM9N#ghm!:@1;R&jqI8=?6&ggHOLDui*JPg!A!1baVqOo)q
<*MRmqfVk??!B[TnWU#q/PAWm"r<Cs`+%+3)Z.:8qfTVo[Y:XKVQJC<&;*l:I#mr?^6c(q;9
aO#iXVr#+DIWAVK2BWA\WnUJ77&tc]ApG*+kmXG*%H$&lXs7)/AbIt(/=OgBQ&S&O6tb,HMi/
8PSbSXg\%piHr#(Q<5h=^=MP__>4+XrOW<ahc;**]Ajj]A4+m9SSnY=2t<)__p:qlmM`a!f:+D
]Ae,#)J!?.,f<<:2"clbJW7E2ONbs?k=,3q@oYQr"/9c^Oeafq6sDuGPo*+p:Gg]A;:Va>QA<3
%g+3GkFa6sH>6BP&8VSTGM+Bs=V/[mZSgO-%#H=KWqPnme:pho:l,HK2dX#Q_W0']ALa%IM+"
`QPNPd&cU-Pm+#IB>SOE/T173.=6/EkP1+>H2<k4@h?F&/8V5\l^"ChSe$rJpZo3-bP9G4fC
pB'Uh/Q=q1A,e8&t,`S['>E="3r1B;]A:H]A="s1=j%cUc]AWU`T3FK2dQ13F_Pp'Q]AYRF1PBHG
/-uC@@J)kXsP[*FucL98Dc*%SA^]A-91'+]A^I,+Wd?1+e$(b]AXBjh$[K6btK+R<%s\*>,EU[/
-s!J'_=Kc(TI`5<3Km58pgoYIMe`G)Q'55Zo8,d2.Gd&*`Tm=?Z7>Um?Hokl5"JjJ-eCT7kh
<Fr5ejKh\+f#2TM9AmCtD8B9QIM#EXg]A#bQCsARcpVCo?(daS+B7bfbV1N3DjYYBI<2.8>;u
`5!j/OiPNKiGVn5Sf_)sF_t]AqV"9]A*Ek-9bd'-ud</GcG!*&:rLa75\r/H:PaY?^d0<s4!'o
k?/)r0^^l5HthGZj6\Q+<h#r,b,G<n"[l`hRWF%ga4uoiRub!@\hdhIPF.Tr<jMNMMR0h9>[
e5ITmtE0F2YSXEqE8X)pPMu,R?/Ap%QUhQM.JkaYs6?^:6<LPZ6<)nI3H\Ft`=,?a#Yp8t'(
4S&rWr/pi4@rH7$4TTJ4KY3U75HcoTnfZZ.]Ap!so4K-m;)$[q"TkE#ZOO#i8$Mh:2niBNP1&
jd*V9fA!7UhkmK63b.qTAaZ4pt^0>sg.\$Sr]A/V`E[+T&FmcW6?j#G4gQA2?YtbE"`V,[ob5
.Tob*^r'G3EZ:hT!GmW<giU:B&aUY4<^GYM6(8#:*d5+plicImL/Y4rj\(.P0-AC#QpJ.hee
:\Y.=Jb+mA$W(8!2Of(kj?$eAaQtQhoGk'GI>\@Y5'N]A$l>[S*@90jo%qBB"hC@>/#FeRh*V
!+0XN%r#f$_>1!S)LYAb<^/5]ANb!f9;Zd2p)TJU>hIep^k_9mYU)/+I(>n<7/QC$;<!@P.'`
VA",YU''JP6<(g_`qnQCtiEIprDYY$A\6G[(uC=-ssM^AOL_6K!,`"Gc^r!4`eb"?3@2b\qF
)$HG9d"d6CVYbLK'h$p9YD2-jQA8s;9!Y@dhjO9fQ0R,&TP5UpnVCC^*aZ.m5T,Pb/?Wklt?
l1F!3FPY;6)[?S2+oRG8`0MctN^Q/Lm0"mEOXULi*uG0.lL5^D<Z]Ao(D%W8i>Yc[Q^lN&7+6
K*biE7f`2)8HG4'4/C\pRoXMtEph&)OM4nerifRWBJ6^IrX2kC_&!3^-]A'n/220**L"S1V)e
HQfTl9VeCQMV1mt%!HEZE]A)/nW0=)cq##WbKkb:#;58.@dYe^`8VofYZk"t;U.+ReaO2M2N5
%(iqjB6`%*F%.`UH3]A/DhURI@'$O[YRWc8,D16u!>)Al#;+-:[,Xi7S6*kC_og^A6[<1RY,=
dL01$(oTjJ-:OMQ><)oaAkH^JpZL)n7=CRDL+ro%C=+hZV.$JuEh?Q+ZQ3qSZWH-8.g#:FF2
n!p=SB5&S@9UKtapA(9Y6aYeLXkXB;G.j4tOPOMX*GSh(!?RRH74R\B0<d6;XHuB.jW[0uAU
Eu0I-Zc@\GC8:5;Tb^;gpprSY[,P1C3o9h+"Y-nd?0EY:C:BJe_32@qhhpcJ"hadZ9EEF^B^
2Giqg#O?fB0$bP"N`#FK!EKO%5U8roP/a^nr+#A65SY(oSf0/oGSO;9UB?o6lh=iQ]A^^:g+D
F_mB]Ad(JYoP;u03)A@]AU.EkuOUQ011YY!oFqo(rdUVa3?@5R5&uC7iXEn+c.X#%dLY%T`IMM
K2b(NWX-g+5*GM"^$H>KOV8McfKmC[:OmF^P"2T"Xs/KR?9*60a3YrDF1-`]AZLIoa98KDB)E
Xb-7<od[>:rK?*XrNbMS?mM"I&`pHpEU/I"FDBnP[6B9!kK&sSFcBTGBCF\\DK#9/1TEJV>"
+rfWQNOt8DZ%!KEkGk@`KP=Ht.B?IfT~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[KmJ#,<:ATARDgm?1:E-iaPLAqM%n2W+_?lafge,t-m?eC6APuB@bO8:Ou5#G8n\PTCS[&RA4
1[f9RWMi\(C6I4jRJIk1o^=]AW_)&^9;lQ/7m.&@6kbs!!%[(bY@g5!"oG8LenKcJ!)GM'`n@
b_%rnYgU#MHp)QeI[S(8(4F"jBi/UbIi<Q.pcVD7BCKQ!]A5Y6b_&VElJS05d)0M"KN@tkh''
TIu?BeVu_DL"If*DNj`X(ttAX2?V87.`.Ydk9S-3G0=,qqNn#luW#;CXqYa`NUn7eNUYO_(4
.9VoIdNHoBW'Vto"Bcm.Kc2q@6cG:ZLW!5!Z*8kp3e$n#@8X=rcl\F([,i$5_BNe1=QqR%aO
Y5^kmeeC8PI*/0q;3W??b6rnIX=Z+:Ktc@_>@PbdGnSc*Hf<A=N,OI-r%8k_rGYP,L`!eM':
P2nDH`1HdlU&<#HJZ^gG%b^qZs\mpKoq!r<*tS1R\+r(`YE%>"T+HO0_dT&pF9*Vhd:;LjM#
6g$XDBA6\_%(Hej\H,t!L0?C89c)3XUL4W*M]A:.XqYlR(LKi#$&1EX]A@F'HL2!-dGY1e)8K>
*sZjB8e[E'(m.EH5'q@%-n!p<Y]AmBIkIbo"J7n>%EVeLpF)JKfnVdr8!;uWMn;/.A(7X9pjR
K"?ou+<6g9m%=\9_o+XI-"5c*K#-"m=iH>Im$p^g)Mcd%CI`/W=g1LWQ[\sBquIY_B_(+!DK
Znr#8@%uIUG(a*P1FRq_6WK$i.>M2<$DC3J168686@8+sorc:,=nh;j_'5%/90<HYH>#2!rA
$:a0#H($r+J:J6HrH^,ia4sp'uE,?)AO;p\p!WEgCCOdUl9UW9ni%k#tDtT`m!d!SNJV#VU7
\RNk`.r<18[Q3<Yg::,]A>:7o;9pqjIO41a"-[Qo>R2JI*^ah\,@PU/I4l_bmu6[-Kl30%L"p
XYtlZS#,]AiJSbb#MMUm?"h>9EN"9uo6Ffl#XoW83#Ve=Xm&'8Q+k(="Q=qrCVkBI`cONY6Dl
3)46@6fd9HBXXW#>3<@"EVXH=:(^ZcYqA<B]A\')&OCWDoH,7q7Dbh#N+Ge^HG_#&qM@7PN/&
]AG[Z'H5*c;a7jG,"BY&U,.f_[dBnf(mM>lMk]A)eE'L6:,a<$<`ot,X<fTM1:5m]A:<91'^GHT
p>Inl'#o#gMh2NRaTQM2u,qP"I<o4dua#,$GBeF1]AqC&GUGE*6H/@UPS8$J_5`$r-AIqRZ_=
RK5a/O57U$oWgQqmANAE1kTNl*#YdWVQ7o<EUm"q?9n?NDGrV>Fk#X&l+)9)>f4CPOEL)/j/
081d<]A3OX4Rac8CV\f=06`<tZdu0*+tp',c1.3U28g_8Vr3!-!V0:aYb>6A"*!3c_PGg-m:[
V/0thuLO/pP4^qrBH/g#bh6EZ,H@*Up0KKaSJ`=boO@1b.BZ!7gdS=J]AB;U9k7/\k6l-FfO0
7md9-Y;D'NcgCniUp_%u]AFN&3!P@!+3<g(O+6\3o0$g:D(4kM>a(\Z(^:6L>$HFrKE\(u2>S
cHJ;s7J%Q%aoO&d=cX:P)PVcnK[9F#c[pnV,7G<GabIgi,1dK>9^0ADl5"1n9YGmGRCQ['>@
U8'eO0#['V=2g,_eNQL#`M'H^rf0h<nlH4%^6-qj0iqHF9HIc8um/mO1"Q&%/>qEgrQ;U#tP
/IJ@bk2SD)G+WnHS:f*SdPTaQ)NpoiFm$$5\5f;<GdfOdm]APLmHXVRCJlr0ZWNQU@kXL,n[Z
6es+a%G:EQ<U7LW9pYjniY-$3Fh5N>mpqS&?$Coe6\ftNA<.C(4h4ERZN7PdZ:CDh'$L'e'?
D$DYrp>+aeESWf*/="5c0-Z,r@>GMs.<jmW=uDA:HAhJ8@7k:cnX2L^\/m=Ns/oc.`3l6Vq+
598<,Fd6e"bpOQi-I$SV>WaL7b2KJak!MCmr$;.!Orh,CTM@b@-`c=&[TM**k?iM#nq).n?(
flRG+@G&)Y:""f2u1.qdfT56h$bT`mkn\K`+Rm?R(p1AsL@m.'=N,XY6Ap$i-(s*$tKO@_P,
jT>^SWBJ&%]A-tr^/[7Moipse<p'uQ,(N+FX=?3l9!6fDH\$>LFqe5:^VUsuT=p"SP^n2@r2_
bBaMrk1?H8V'W0Pu*i-Yc<*0pCfFp)OZ4<Zjk,5gaB8IDOA&`,pLB`5CZa`fQu(@#$06`"aQ
FYfjf]A+sgK>3&fc`i)0ck()a()4?*"DPmJNrHfC,H+KU*0BupheQ3RR?"4bTWDo6\O[l;$d4
I(aDXiX,lt7Pr0b?_7oP:?"C<\f=_LeTj[Hk/UI'C5pNH@n2GVWi>3Cn9I`7+\+[juO#N`=9
AF`7f6k6XV\U*GL[jPaJY8O`hh6h"//8I>R@]A[j6hnpmQO!3^C4b&WX23fkqPkfY,TrsChDV
i=^0&q"78A!,:EbQThbE*!>4.rj-+n6?)0VZ`#A8e)a_Qr9^Xf%cL#q2XJZA)UFsKhuX([El
o.8/c("^fP+W*6%V'jtK4@)E."",+('cqA"Zt1V+OVoHN:M1VGIY)%!P;'c[IT0T#T01.;@+
.d)CFH^c:>*$2#K]A3'nScegu,F%3ZI+tk'b'Ji<L2?TO+*k,5aTEc)qnf4]AABk-.D;%6C)75
J.ihJ%W&h9%G,i8+JfYX3sR78>)D`N7G%aJ/PnkS,4^CN*GUH=W(G.S2p84H@6'!:G\]AYY1i
13\2(YkeuXMA^*giEfm/p#?0@l_`UI.^t;p9jQS(U0I%g'Tg9Y,*-0BF'e\$E52%HVPGf:P;
jUJ$<9d&8g[bZ:#hCnsOr*r$gmglfe3KIUB"\b5JM=i7+>`UjEV'0/PKa\kH$;RFZ7lVV&=5
YG3NHuno^Kf`I6XioreNqK[7#Zo9%Z@<ASUtb74Z`^pJ-qJnT$7$Mt"c.KV)o_Cjm7"NDfr"
.tZDpdBWc1.&Dl2f^f=niYEGH""tnaiR'#1dTN/\6;g5\[ESaQBSR5[l.LoVa0S_P6Yc\[ki
Rg6H8^luFGkkU!.:<h7ClsWe<UC.)\`"kc4S@pPY\f7.A@d^GcRTg%4]AH.`g!R9:O,n/@LHB
\ClaBQISP4A`ZOs"`;S]AK,7BM6iUQu1G0W&7C3/^'$rue'XI_u:acWFHSZeSp?[DJ`TE+nT>
HQX@e#mfH:"VH2*@\"$@D7`ALqba/$QFNm5kk&D$ioMH4FD-=9S?S3h6>a#3]A!GRC]Atpe03n
U/(BbO:3g+leJ`QOgfPI`ipk11BULrF?gG.;QmZ]AI,F:Y7\^"8m:@k;j7b0FP)[:)p:@so"!
KBfQ(9PL]A`eZ/]AhP^<Gm.A7rD&p`HdC2O+GDY%#F@GPppUl%j3"T"u1\*PX7X$$Lak,P<i9>
`!7?p"AOAR*p)?Hp9^g.E6?323q.c(?%*HZC&f?S[>of!*D_cl`k=dLGYZ=7,uD5,DN19%m7
j6@PCT9\iQ,T+?r3Zh.lDT7R&[_PWW5+IHu#[)S]A6\p$;OE(_ci=BsInK"epF[aXnKkm$bn;
2R?['X_e,56*gS&;\ETT\'Y50N^aJ8]A<[Eg(J)rgr9@\#k?W3Zl>PfF_ZB1!/-ItIZ%45f3O
JXO?2S=e5DlWOWN5\CQ3JuLGonn9!JfRP]AfZ5@-]AJU^cF-V"dAK0Y4=Qida)OFf$K);'"Iak
=6aB<Y]Ah5fHhJ5gQ,<(_j=G-@!F^j@o+P,4e$'V#n$rRn#D4=X9'N#'2R)b-@Yruj#&G+=R;
SCup8E-CgP(VZKO]A!o-=A+HG#!Ga)oYZl10[g4AmK0e-(LkRd3ZU2VbdZB-4=/2@:d=j7b3Q
_<5F25pC+m.f4&Q'&Q9hU\/etSf*a;'46YMb1%]An)X^[Z,6V<Ur1BD0GlMX"QK)f4';ojS[#
&Hl\aF-?h,O=Yh`^l@W8rp;q$X4UI<6B,!8\,T0Cdg/g$/d,3C^,TU;nJfb@r4jA[rI(p'4[
:h5#!LV2isblqH[i`]AqI9053;B$KEArT0LjMuO-AoQkkaY$CVB`1*fHaT-7,Q/Bl9<@j]AuO"
02R5?'\T`Yqap7Tb`Fg^C^s/Qc.F]Ak(6&BYHp">QEV(X6I_XWPi0dj\:qMd%Um!&n**KRVMu
[>P,g]A"UI,0X8iIm$K>\#(fE9ShV+;n)C8Y,Q4F3fPt<hkHsQs]AQ7hCEtE+=U3\,FhKHDqqu
1H?*1RG=:&V4,t@eMXb!j5laL?VaS;Zb/:,?WVFjp7,)Zk,QKO178^uPWPTr1IXng"PU%a=)
Lj^$+!4e[4n4_/E#`e)Id]A-@ZGV#2A6gWkZmS.9T`XLVQAW9p!3dQ&?(aFRr02E%r9='k:k4
*aPLmG7=F.E`(`!;%%NeRR_%b3]AjZn/U%FEbK5'rb&#m,Vc"X<%P6=DGapaA^E8]A*'^Y5r,E
!3,W]A(c6$%"YQb6:s8T%:(:Y5]A_.4/8h!V?%DJVA;2O33W@b;;lE47+B&'T^iV'H*..<9`R5
@q@\\Gq&V/oZ$I!c$13u(f%aG*ebqTTB&dB/>3V$BmscDuerlHW7p"YeY&Ye:muDVTW\X1*!
T<BT+9Ie#X-94U7g#L=f>TAf*lfPrcYXrZGZkFc(+#Nm!?IjlQ[%MV1rO/J&Xh.Sl\^Kj/@<
8MN19ack$+_!HH[@)8%C]AKZ@',P_9!$D=CM'hb_2Q9_Mi!<+\E>Oe-4BV[e['Akb)'+O*9@$
;j%gU^Zi"KUN1h_s.D)(]A'HrRf"BkTh^1\e5]Aos3W+*B0q1b3la:)Ve3-0:28#DR<0rnRUeF
0ht$hbda#c?,FQ3g&<l1eWX7.p^E"4i5l(?*^E&>4Y8?(5Ise5+`A*#+969H#gGt%]ApCM41k
f9qS@O^mU>a%''CPUl[=_"P1uN8/$>e3L!\[l#>h\BLPqBp"`[Jh0p))T+<QfEHfbY=/7Z$F
=La02I57ArU*cF%KSX$:Cq%aFsm-<NP#@DT:/[)e]Ag%!rj,X3YFD/K<#J\RoA.m3NMpo5UOL
$u="auI8nLqKot_G.f&H*0-1bu*J>0(6k)GuC'EQ\g>"hSZ+r@C&,M!G^b_LgWdSn(mcl[rQ
'!6a8!X2h0f9)NT06hioZ.s3]A>Kd(/tASaR#ja,3c_/r+ojN5sA7)u7&;A&.bi05e[_m&TmX
6M!C(^X:C0c!EJWD$8n$-"5&`LBFEA20tDoBeeC&En"P0",e;*rgSk^1K.Y#UU"(]AW\#T@iK
^JTl%sMJU,09E=;DXWEd!\p/6XGS,8QGP&RT`PEfXDB6j%5o>HT:PbD6%CI3FE97^gEKicIp
YlejWEjgutp%[,RoA3JPPEXaWQ\1p=^1f/\"Xq#[tD>9T41`SHh/Ik:DZD&%N@'A-=ZH<eYk
,pZrRJGEtgpikK>1Z<_Bb^e5<>"$r]AQcD&<:6B>cJm/AMVKN1(^0aaahOEOR29*,4Co'1,I>
,1n3fKq'"B&Y*>Esd"[?CkU-7=FNeqT$hAGTXO6b3q`F-cu`:Q=$<Y[TaUOmu`OX"m64k1GT
rN_e5F,:dDKKEs(@Ej3Lo_[Ud5C$?uA`&]A^!=k%,dpj+C3I3`qV76[hBE9@LY^(>#/.D@7qD
6L0JBX6#_c1M5X8hasi=R\Z(]A5K!iiac5_`eTYqI.S/Z.$;IZsO3QFUI-<Kd]Ag7Tu?\)/U$F
7IOu\"6?,0$<Rq.E]A5Y9q-C=".kpclrUg;!Ef##quad-c[cHh'6LDi60))j%\BP_'V^T',"n
02$]A:1lM8ETEPFZl**[LJt]A-r)^$_+ma]AWSLP-2q:TsU]Am2GP9d?_2TPG<=eonkUq"1h(g#V
Yh+]A6QO7!e#./fJ$8Ir4UKTI]AP;m>.V%B/uGK>q<PF\]AJ=+F3mRXi&-j(Km*WumT2-]A_O;EN
GtVdU+^9;""7/bW*RIf)aSW:8Op.0_lRW=7=\JS:BUt6:2=SL"@Xh4-H1'jZh!&YJViLY^$d
'^oUBo.DcWNG:#KP'f$F)r*ebMrU"^c0VJm'FF&Tf>IiH$H7$b,l>34^Co17iB(AAInZG6j)
,&#h%QW8!(0)e`ISqmq%f<W/DY_k-9Y+j&r*UQ\u>lnN.<K(AR6%-Ad0Io;$DRWF*O6jr,6O
m&fr$X=,/8H.R@%8MC:Z<62<`7"0dgsN;+X]AIQs\UYBo^;T8k[0.P>lPJ.U=)Wp7d1$fiTAY
X%i,%nTs"r)m-Zbo\^G`Q6f;Er+02Ok<"LXWdo#:U9:nWq.4mW`ooC,^,O4#Ls(t2,\bP.J&
Vmu,9:uK+CVE(J/R9M*.7rkQ:<;iPP]A%1?jG8u6JK3GAVf`kfEUL/:`k(S<<*Lb=o^;>.Ud:
^`l'b9?YcHm`doW%:`Wk>jsf^N*)2Hf<q(Fq^"E*kh/qX);m,$-A87s(flA5KI/5p(5oM`GV
\LXRj]A+s)N0htDPEaSQD@2nBf^URq%F@0_Ee(q:Ct=I(@C(/).3I+@S[Q`C`0p)71;DWkA/e
8*N83_pm38pI/0l@C'r0B+Y.;FIb`VZlu!gZhT*X$'$N8uFV7\:q2;\_j4ac?U@(SOp"gftk
p==.k,`*OfBLKO3[3\6$Zsn-jL_`^1#tJiDT2P<If-,.rNQH+N1ubl=SWr@snE%^*CZ1E-J7
J!ubuNtbjeqCS2!<-S&,,+m'7Ms<Dq/Mo_3O7RHiHBf/Q:lk'Vif_aI9;8.batIe1ao,G/@7
sA6$Tt1I`'SX/]ArU#)khi#"Y1c3CqOU^3rRF%+g^VSHJ[:W\3[(=/1%^d"MHCoIpKs`6B<td
'b9`aRX[GJgfCA]Ae3tD7^Nc3ZKE6Wt^:S+0%C,Jg2V6bk'1/WNNU(/6W3)4$;>u0st2Cd@t4
*#M\MI'2F`>c)g4H90SW:!(Me;NH_l[MY6dc!4NIqb9+T'dG5'i+ZY!`Scn%AHhrLusQ+s+k
rl@aIV^GhbOjon4:S`h.6NC"*a4a2Q#7d]AV9H*]Apk^+.3"2qc*F$RR$uNHEWcCGPi\*;ltV`
;r^Xr<Jm`QU0JU/s0mNSKtZN24?78_k+>[LS,3calLi+lbq'Y#o7u1\k%DecaP^.T4ON[Pl2
?+-9goPgF'mbq!e&C-Z1Mos?Je\hIui%Iqu>0@>m0Z4l>qmB4]A2J3\.0M;7^k,mY.:P>?>Qh
FIEk(!fa'MEpLm,Z"LIoAo0gnh&ci*cTMm\Dp)eG4q7d3Zb=:&81"8tn`#4144.W,2h>a!H5
G.FX?H&a\208*^=3Y/.^IO$s2-eT`PlD^KlKI,[G5b7L>b=PHor/do,#+UP7:,/?bMAQNT[n
==#'_j%9ET(;ghXE^ogH31?$c6`!Q;,A.nVMN">V1WTJHO;C1%]AFBU%f=r:16!U$<qc*^0oE
([2t0nSl(.#O,L*3%Cba56[B3HZO2[M)pIP*ESkOS2fc60G/]AcIF4qK#I=1`=S:A\]A$@-tdM
WRB53"hD4H]A1cLu8@u=gqF\_o@dt>\e,\f-5&Opt?P[bZ\9GFXqNQ1OmQc::j%0]AL;o@=I&F
LXO0_sraAr3:Uj2)lIF;3LP"@bb"",Cr2Rkej.u5k$ka['*^d4YOJ354_'PK*C]A(B#OpZFXY
Y>h4M71Pq7^oal6h0CNnu0Z,'U_M/_J.&*,&d:5AEP-?l<3L!G@BjD]AZd(TkGiUr[)Lk!q<2
99179Bg6U?lAVMbokdokt'D7Ra37D:e")0*r[M5C+B/><U(I4"0\^eBHH/1c/5K#(DeT`NVH
WZ;PD-uC18Ho#1`#V_^g-6kMK_)1ISP>@CsRrXWqmr&7ljaXIHam=Y?Q3tUH=dU5kYEf3F-H
s@N9K?'XU=59QH?o;^'e>"b-C`*EoRZ!2IkJr)U,mpSPUkVpSgLur;&CuEXU'<M/]AaGR;%/+
;'LIsB*&b:)j'-8TO]Au0,W,;[]A_Ei3SIOXp[-El:L%j%lGUZ+-b0R72VJS\3M(3GD:Ln)bWm
tn4hL>!NW>Ac`.&t(qXG>uB7@Bn8DXDp.D0EG;a(Y!8kV2(pb5oQ@C"kgGq:M;DF5#"_MU/t
VlJ*Io._bi19,+AGUNILf>bB&=M>gDkML?,q<JFE6&8+h;K6#lNQ)n>\3cQ5[6!;oa7+2l6;
k+jK@N*j_cAC3oO?#RZD@77Pn'Pc8Djnu0Yd^s$6_)d.=dm;<rZVAQRiPHr+fU96@!L@ER4>
7C8-[temM/D[nYDSp`m's!u)]AJBHfOSOOXptZjo5EC_`;HSh1DZqse'VELWqe5;'17^#VcdN
h\R/;V.P]ABD5?k(@^=BF3r^(/aPXS='`2)="H=c'gAonoXGB\4SYR;/2FV0=^aQ!c/G&1@Wo
@JcgNI'KAc*!&'q:OXRQB6iI+r<AAUeso6V$osJTl$rq0Rj:<q(hSN-;(^.hM#!D(d]A9Gf%<
n!>r00_;mP@]A^DDX-iInZ"#dqjM9N#ghm!:@1;R&jqI8=?6&ggHOLDui*JPg!A!1baVqOo)q
<*MRmqfVk??!B[TnWU#q/PAWm"r<Cs`+%+3)Z.:8qfTVo[Y:XKVQJC<&;*l:I#mr?^6c(q;9
aO#iXVr#+DIWAVK2BWA\WnUJ77&tc]ApG*+kmXG*%H$&lXs7)/AbIt(/=OgBQ&S&O6tb,HMi/
8PSbSXg\%piHr#(Q<5h=^=MP__>4+XrOW<ahc;**]Ajj]A4+m9SSnY=2t<)__p:qlmM`a!f:+D
]Ae,#)J!?.,f<<:2"clbJW7E2ONbs?k=,3q@oYQr"/9c^Oeafq6sDuGPo*+p:Gg]A;:Va>QA<3
%g+3GkFa6sH>6BP&8VSTGM+Bs=V/[mZSgO-%#H=KWqPnme:pho:l,HK2dX#Q_W0']ALa%IM+"
`QPNPd&cU-Pm+#IB>SOE/T173.=6/EkP1+>H2<k4@h?F&/8V5\l^"ChSe$rJpZo3-bP9G4fC
pB'Uh/Q=q1A,e8&t,`S['>E="3r1B;]A:H]A="s1=j%cUc]AWU`T3FK2dQ13F_Pp'Q]AYRF1PBHG
/-uC@@J)kXsP[*FucL98Dc*%SA^]A-91'+]A^I,+Wd?1+e$(b]AXBjh$[K6btK+R<%s\*>,EU[/
-s!J'_=Kc(TI`5<3Km58pgoYIMe`G)Q'55Zo8,d2.Gd&*`Tm=?Z7>Um?Hokl5"JjJ-eCT7kh
<Fr5ejKh\+f#2TM9AmCtD8B9QIM#EXg]A#bQCsARcpVCo?(daS+B7bfbV1N3DjYYBI<2.8>;u
`5!j/OiPNKiGVn5Sf_)sF_t]AqV"9]A*Ek-9bd'-ud</GcG!*&:rLa75\r/H:PaY?^d0<s4!'o
k?/)r0^^l5HthGZj6\Q+<h#r,b,G<n"[l`hRWF%ga4uoiRub!@\hdhIPF.Tr<jMNMMR0h9>[
e5ITmtE0F2YSXEqE8X)pPMu,R?/Ap%QUhQM.JkaYs6?^:6<LPZ6<)nI3H\Ft`=,?a#Yp8t'(
4S&rWr/pi4@rH7$4TTJ4KY3U75HcoTnfZZ.]Ap!so4K-m;)$[q"TkE#ZOO#i8$Mh:2niBNP1&
jd*V9fA!7UhkmK63b.qTAaZ4pt^0>sg.\$Sr]A/V`E[+T&FmcW6?j#G4gQA2?YtbE"`V,[ob5
.Tob*^r'G3EZ:hT!GmW<giU:B&aUY4<^GYM6(8#:*d5+plicImL/Y4rj\(.P0-AC#QpJ.hee
:\Y.=Jb+mA$W(8!2Of(kj?$eAaQtQhoGk'GI>\@Y5'N]A$l>[S*@90jo%qBB"hC@>/#FeRh*V
!+0XN%r#f$_>1!S)LYAb<^/5]ANb!f9;Zd2p)TJU>hIep^k_9mYU)/+I(>n<7/QC$;<!@P.'`
VA",YU''JP6<(g_`qnQCtiEIprDYY$A\6G[(uC=-ssM^AOL_6K!,`"Gc^r!4`eb"?3@2b\qF
)$HG9d"d6CVYbLK'h$p9YD2-jQA8s;9!Y@dhjO9fQ0R,&TP5UpnVCC^*aZ.m5T,Pb/?Wklt?
l1F!3FPY;6)[?S2+oRG8`0MctN^Q/Lm0"mEOXULi*uG0.lL5^D<Z]Ao(D%W8i>Yc[Q^lN&7+6
K*biE7f`2)8HG4'4/C\pRoXMtEph&)OM4nerifRWBJ6^IrX2kC_&!3^-]A'n/220**L"S1V)e
HQfTl9VeCQMV1mt%!HEZE]A)/nW0=)cq##WbKkb:#;58.@dYe^`8VofYZk"t;U.+ReaO2M2N5
%(iqjB6`%*F%.`UH3]A/DhURI@'$O[YRWc8,D16u!>)Al#;+-:[,Xi7S6*kC_og^A6[<1RY,=
dL01$(oTjJ-:OMQ><)oaAkH^JpZL)n7=CRDL+ro%C=+hZV.$JuEh?Q+ZQ3qSZWH-8.g#:FF2
n!p=SB5&S@9UKtapA(9Y6aYeLXkXB;G.j4tOPOMX*GSh(!?RRH74R\B0<d6;XHuB.jW[0uAU
Eu0I-Zc@\GC8:5;Tb^;gpprSY[,P1C3o9h+"Y-nd?0EY:C:BJe_32@qhhpcJ"hadZ9EEF^B^
2Giqg#O?fB0$bP"N`#FK!EKO%5U8roP/a^nr+#A65SY(oSf0/oGSO;9UB?o6lh=iQ]A^^:g+D
F_mB]Ad(JYoP;u03)A@]AU.EkuOUQ011YY!oFqo(rdUVa3?@5R5&uC7iXEn+c.X#%dLY%T`IMM
K2b(NWX-g+5*GM"^$H>KOV8McfKmC[:OmF^P"2T"Xs/KR?9*60a3YrDF1-`]AZLIoa98KDB)E
Xb-7<od[>:rK?*XrNbMS?mM"I&`pHpEU/I"FDBnP[6B9!kK&sSFcBTGBCF\\DK#9/1TEJV>"
+rfWQNOt8DZ%!KEkGk@`KP=Ht.B?IfT~
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
<![CDATA[5562600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[1790700,2743200,2743200,2743200,2743200,2743200,1066800,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" cs="5" rs="4" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="6" r="0">
<O>
<![CDATA[ ]]></O>
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
<FRFont name="SimSun" style="0" size="72" foreground="-65536"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9C*"'A'[a(dtK&DTf`$W$SV0'm82e#lYQ0U:#%?JS=GOYLdO%[2&VUW)sYLA7BDZ[8k]A,=q
m'T(R<b<;q.O=#m?mOpr`e;5lsME*.ihL$2L3]A2aOH/_p#bt$@3X-CUmKOkNmWBrm&!fIEoW
ofl8bG0+<4/!!*&`J!p-C!;K(cT[t!.IE>:]ANnW\-!<6%\IHom;f4WZ99jX7`rqB(JB9[#`b
EuHQZ?c.qI^<#^F5Qo.Ca?dibbCG:r;G]AbIIEC8gVGi:r'tJGMm__SM2j>e)Hg&G&re6#MQl
[XeP;7Ki)&EZP7f$T6:*7nmC!_A1!/CU"s":`3]A@g<+=PL^Q^X6NE`P*:&4Q^GLuSj>n?<<u
);6Wl;/ft9Yuq^B,R\X(iqRi0bp29T($E-Ao#\O#jVDnD.W?jrI'hXnlA?d%P;')#gWk?pD]A
@o\h^&$iW'R&8^m:5I$Y/Q<iOgZ5`I&G:94MA$_,U0Ra%`F#PH,TZ>Lh"7f2&Nh>J^Ai8&+X
CEb&FR!kg>\npRk`[AsnQ;.aYSoOmL.qSg68F5YV;!=-R7OFo:F?UWD"8HtOH36!>pH>K]A87
hoj"$Ic"/[Bl0oq@GVl[5-"[n%>$T"G++;nUH6GD@82fY'<e&G,jY-X`YL)rd<6`;dG+\Ze9
O6;*m@Yd'24QL0QM9*L-f\qE5oQK1D)#V.cdD`3KaCQ/%'nBRt!Ga@#%gEA2^sPE]A@Apj5+l
E/==SEu'P5JP:YM\-9HXi3AeU:*7O1m%s-G6qrm20I!t`2(g.JcZu=,'#MR)E;oJ5_'.($12
\G_i'>ZZl*6CkH]AdWRO&f#,,9a#$Ql#h_4(YW2ZrG'bd\M5+M@rLanLA'VE1g-t)"Bsr*\63
</UTF;DE_g@g$G&ICo@Yu]A`S;_'7RH=FrrZRFC;3,oMBL>q!OXAl2A]A9+pEf85CH"Q!Hf8g/
#MgrQh&e:f*-X1_0f9_U^:\u_[g%sj,+&[er<l@Ug%SeV7psPJL)=L-/?\3/C5cP)*-J[4Lf
Yt(m;%!"6N9X/P$?jJrAQ7\#3%Xe%4$L:bSP\:e:2\E\oLtH@^FnESX/_Kc8VlgS7doo8$f/
-2G'_S4C]A2IsKJ&do!I:OsV.uq2QC"Xk>De&/#T=:h&OuY:h-PKRM$8c6.:5e%pJUrfXLP`M
/R9ffZc)i6O'iI80uB2[[(]Ag/5fV&/Q9=#0`A07L/:!<5pPOfIp1&.e\Rpg4_`9IcE5eL_j3
<%;)"Cd$h5!_\_^%N#fYLJ^f;J_j+opNg>nZ?^$4\RiKM5KLDm;RfA-KMqF(i[3,6+]AJ$F20
sI?R-T#EpX>d1Dj!O4cGEK4.5b\E4JITp!H'%8\Bo's1rd&s&U;:rGjIh=QP!eU@]A7O9Q,NM
``^=Rq=e7KaW:SjL-Fp9d!-XQUZ$Cn;MG_30uaOu"%P62i.#+[kO+)qS.;lN?--5-n*^dR">
nD70s8?\k7i<Bpob;./?1iQ)$C=8o_Ge`i4]AuSs"f+0uoCTXCLaA_ZNisKe@+`,Eo:.^"abV
eU1_37T=ii%g$TFm,C&Ik2rV;0kapHs0F*l=,Z6',!kQC613M2TL#,DR);&3FW$3OCn<KE9_
*X-(2W=_PsATA0<e"q`T)e<t^N`WIgYE4m.K3RPs`?j\d\a76_W=+0EcJ):>iI=>24ra7Z8i
=ul^pc"B;R(!2hJ,>S[cRr"Q/D:se>uK[)kB5s1kB5s1kB6Ofl4+U)o+fu[J5=R(:EIoiC^o
S8"'B%kH5\cnpR,/'ZrGSj4?c6KD,,&JDXH'9!O*njCXWmm3pES^i9EN0m50N$k_rV@ptD93
If(:'J(_`PO<Nn1E\(Gd\qe#aJ)*HQ$4u`mLL/6kcKidAcKidAcKjnBm1(jaXW/DP+@#"S$e
U0i+BCU^6;6I$1(f@mX9ie/$&Ji=FJ(]A>jTJ4X.^TbRr<FJ>,>[;I^n39;JW[)46.o-8P4qp
G):13V&YMhl,^$WW!-"K5>s4YGW[C@o=4PnBYNCr*6(9u"?YEaU"A+1;_`YM:%r?4]AnH%a<*
aN8_nXY+rJ;%(<M!9*$R.c<@_TAn*n17Igq_O.\>t(@_F4pTIS8<,ecZV>uaWOgNl^q5AF+r
rj7Qof!cRI4t=/3?+l\GrZ?rB$ns45XeKu*3O(g`>l%`)(#h:%=e2268A'Yi*gMUukkpYJUD
s0c\D!IWR+249PY*rWglf&UGd=FI%;4=5,j+1VGm5O=5EeAbguFP]A&a$ghL2n!%T-iDQh"r3
V^OTi6CIQ$-fc%rWCTTsLi?Nt,Jr_"(N-3DNdgJnKfLBNgsMas"OJ+uR2bogL]AWQS&2SF.8O
ZVWSP!rm)A&_0fG$k`i_//_p6:5.i2*c@2mfH3@\*kkhcLrC&")==5^'kOu:&kqPsc*)8;tD
l_'3HZ)_@.YG[M!>VfhOB<D"1G<p/VWYg*V65oafdH!X^MJPM/F.",]AIe$l8jV)*L`Qnc<%h
6%'uYFnB\9/fFBDk!ec(EMI\gs("5AKepA0$X`<>ld[F2LI!b(:qp=H">l8NB1It\*)DEa21
9'']AgWrF\l6O<?!93-ja`LuAkLk#mGDfY/&SX/j0_BR*9_:4:`hD",t/8Gu5A^N34m>C:FW)
dQsHR;/0>up@(Hn<N'LRoWN1bSY,2&+iR4oRR6hf@(7#mhrO=^E)Om[CDl_?)TC<BRqUI+/e
]AqF.=\_=M*/&Et=(/&N5%PGo!V`Dre.[es1"k=VJsHRQh`StZcFV_;H5]AEcU25]A1hM/kJs7c
g,]AM+ii45_@*Z$hAuQ&o]AedjACCl"oO_]AK\[U(4DlJe4.J9[!8q"S,PXn=0&t^f(A)(_tZs5
4m+@l[S5e`1"ZC,B-D"Jq/+UqCa(-5bc_IQ"LRYNJ(Zmtp?7GPlSMC+9_%mMQ[S[D<t8@sa!
B5O_&l(JYehBZN&3799VL;p<!oZ:?Z`a3f(<%l]ATJ1_WGiqj2lINE5Ha0D:.1;RBl]AMh.Jcm
[j&gac+2=dlK>>C6a>VR.70$nD+QH^-<g3r`k<kVP&naB[G.b+Kr&MZpCh1>mst0d>3-OUOX
hR_'"<=Q,OEh&<#\O'HH3'&P_bdC7$XP9^u.;)KaJ$Yu9Y+JCeXGLs6ZW\^%llt-!IB0F,)e
><5+$aq;a"&&NANVGa&RkI_Fq9.e9#`A[\=K&ZL<>'/-A<`(i^e&LMWHVF6gH@>Sq8n%Z@p@
MiBPi#pkfN+l4"]AL6%eNSRV6&Bnc8*pu4Af_(6=98+oIi*tbDM'[2sG0Rq[pV`"kGC>N_t?*
jY=M-I3Ub#L8ar<"OMaD&Ek%RVi!D`F<Zt-eZ[f4?]AE".Ai,kUq0]AEHJW5J@$F!Gq#$Rb_QN
7"Mfd6IcAYO,Dqb^/FT4&5;Y8eHVH)g!W=6)+bfVAj^,%baT5EVi>C3UUqe8!<TjP'L4*3=;
)iLImFPeiH\.XQSJ`9)s3'1HGs=r(&j6Qt`G[J<gIND*DuN5#5HhdFUaLu,^Q@S3_+R?VHO:
]A5/XG,43?AXS\Z<7_Li^jM'<U7[:eO&6Qk=2rsuKYac[V+BM1<<YOZ$8CEW_ICJ7EuFN8m6N
06lpUbJ#&U\<:ZD*M_cs5ipT54-E*_aXX/M2=QuUm),!S9]A!Q>IN0$rhdXUtuq-"PWEYuK16
V7ZH*5Q.>(]A6mX1@'.;ePMfN+AbXoKb+]A%Kp3(f=Ya307NpWMOF4_C`,o=.KBdM]AXoXMKbr*
UpW`i=`*dLkjM=PP2mm%h]A+5B<=CG0b.kf:4KGYg,H8SR\lGO8d7JK^ek"6;XRO5Gr/NG9M>
TK@1^]AlMp0Gs7Y`(f-b4SK^?EP1Ge\n"2&i`^T57)RBKk]A)H>"4`aVo]A)DNJX\skClAa_qlr
KaI5\T+'C[[!3'jO!V69HJi8]AC'sRXt)%Q+90`L6Lc/P8D5KP-V4,Yg3W<)_YYqPQjLF/L#V
@i5t^\cHWBH<*M1e-n$]Akt68NhaN:fU]A)VKQ)c=4S??DG*$_VUTr?j<0D\P()p!mq,N9;5>(
COcjhM%sTB+ilNj`@&)VO,"!AN(4_A-EbT/9o!#N:j4U*B<:V-n%G>nmXj^R>YR2&mZn;#5!
ST-ei&DTs*7;q.*a"*->O75&VDrS@QG=!$1M$L%!V<L"Z@\p`IJgLkCnN%gCGDuSm`(ELj\9
(W/]AQ/prXb(QAsk(,/C9T=E4\kC3]Ai]AF74![]A3d$]A-9kKS74%)\i_GAep$]A^KLMXH[mj'lei
]AW0D<F>>[4Hm]AA8b@hfDf@7dG(sn?DTo""D6WYUTamR1f'q,OVU3p'/"]A0*\'J2=Rk.J_V/!
;$gAL='kJ)HDbC>?;n@UPK[6Z/Ze_s_ELh7Ep]A6eSs)^dN??iTRhH`eT+I^k-0ZYD#K`/)l8
J;TA$3(I$H0m:G_daP1<UAM!Ir"Wk<+K;2)k/SQ4)Oh]A^I#mrsT-g8A&e=p,\q5RpH'kob1T
>U3R[F2UP=&u&LuEDb--d=JV[d3Kc*<$3No*jM7lY:+k^e`rb#fm#4Z6$t>(`VRM^a9oje0s
1>;adPo(Nm0:O0RF_j\QqmpKp3%u.2f;!Y3bCXQLS5<Vk%Glp*(g(nZ"J4ssk!0hm/iHM+Tj
-$t*TdtN52]AgOdY)]Ao5>a'$g,#ni4g"G5\n2q[%R%5+h!dd-\#DS96r=Ig:m"OgpN4WH!M4e
"<.-rh*N2mj9G%kZSG2C[t\?H&9KNKk4C+XS*;%1,#@M9@4/pcFI3MBfl[d\81pV.;"P*F,+
!XJt-Y'-SM"j0ELA4`u5c;0srl!t$F.)=^I]Aqu]AKD2Blj3<RIja["CL'\>ufW*BRgSg^'Db[
['[H7Dn*?EW*X!,PBtIO^E@A7,GJ&o\O]AH!CHA/eG'7(XA#]A\L8(SD$.]AsW&:=ir?68-5(F^
4*;8`)1b7q5ru94+$3NJi$&<'f?Q[A_Znd?cW0E9dMVA?B=O>@hHe(Z!8P]AN"#'b39[HXmuq
e"$A3'*e8q(sP+U!gKAXYCm\!m:0FP"I&tnnA%_m*B%FC6M:>17;SjVQk15$Q*hrMBc#V>F=
pNIGeD&^3[;REjWEGEoJHm>^&c2\d-W%F\T1CM_kqB1?f*QSYo/4D4TkG!*tbDSdKG@FF1Di
.>FnmMJ*4%n8e,QFleem8'.<tj^"VrQH*De*Jk)j2CXrhX98sQ4:(hSG!);@:bb)l/-6g3WB
cQ!^F2=&q"%L"'HmfijNl:XX!=H!b&VKlfN738#sFspP+.f]AR&L5UC(sM[3cn%`7:?lAD48Y
#dM[kdit!W1><%_*+#Wj0(Vi4/0h'SOH8JRG!LM/h!2dP3,TFpY):3H)JZ!E-^gIh!7Fo$Vh
h+:;77LPiB27S&m]AAnN_!RX6C8eRaI*K.T!k]A&F*NV^HJ.h$c"bWXIH[)rk"Pp18hIIc-?se
1Q26rX$"sI+H"n7c786-g_r&=TA\>b7]A/Gf#=!!~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="288" height="250"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0_c_c_c"/>
<Widget widgetName="chart2"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1536" absoluteResolutionScaleH="864"/>
</InnerWidget>
<BoundsAttr x="4" y="282" width="288" height="258"/>
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
<![CDATA[=now()]]></Attributes>
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
<Format class="com.fr.base.SimpleDateFormatThreadSafe">
<![CDATA[yyyy-MM-dd]]></Format>
<FRFont name="微软雅黑" style="1" size="96" foreground="-11337729"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p6;eHlD>IUH:elpm0&BW5G'l]AG:fL6iQg>W_`'cO&oX:j7+MF`<LTKo*TS[N8:;G5g?^t
`[`/9R)[(0M\MAppKkjd#m9JIhuo&s,LP6HFLlHuur_mrN#Ar?qN*+h>/X4859&q42SUhp(S
5;TR<n%"9oR+CNpXaXbpF"?\1E#l%+bi,dNu0:)p@"pC$b^:iONGDdr*<*+)rN<^qOQFC_r%
@mH7pl^p$2dVAg.DH1gT!G1GOO:N:9#/:'nc_61*4`KKQq8]Amk1t9g0uDIgn[DMhV"2:^ibS
+E,dt`Zo$fL6$cf+8;WX0lD8Z4T!`g:\*`@a+4et/ffU_5'Ea8r'?IU%TUAT&qQZ&m)i34GZ
Ju<ksfcr8I>dm:Am)_d-&pKmLj?Z!j:M"@KZ,d#u`6,Bf"]AGt0B1:TQFDo9'!Vqam(TM1P?+
((:3)(l<&@mFts6)7qT'fYDAn#&D[SrU"5"M3>=3#Zt"(Zg&;&d[;TZRM_FrQU>E:Y\rgKR=
nHM\[0ikN%"/\>H>H+k<E[u@8_J)0(?/Q"Le5=>/lZs^)")'kK5`95B3%"Ufe[&nZZ/&c,Km
N?GSl&mdu2AaTSEJ]AB[\kNu3<%k(mIYU>&k(o2mRPOc@T8/D[>j8/Mn&/sOZZ\lNEC=C9619
-lJR*Ll_Y.,'g97Z3LtEU3K%H*g*E0T\@kSDd1<:]A`NL1YeXoc)j<Z,Y/E]A*Mg/$KEZKTPKY
^M,$Y7q/q\'8a`K9_Io\Z+"pd4e9J8W`5,6<:*.2q)uk<XKqh$Hb<tFojsU^@Yq*&/E\M'OO
H[;%'+#L$`9&]Aj1aLjCCJ>V&aYhL8ebN)('XA5#3*tS^(SQ<D+(ps9o!Mgi"`t,Nm;Fj-GRT
9&<0]A.rRq8G5abn@K6JQ(:CB4\'q$;lR3OJQOdYO=H*+ZNAOnfjF<iDNhA,FDH2l9L_Lf&ED
G>//f0(PAM#6=,<S1CW<u%IUd;:%i*MFF,kjIh[pU(Ca>AB.pOY#b4Y4k.ga)7`t^@2>M?:_
/9S7/Ss+fgW*3!BW[Ajn%2=M1ua=2'3GR&j86\Wu5VEZ=!IGcklo]Ad6]A`6#UacWCGXh.47KN
BdnXYp>,hi_[(259l)@dit>6-NP!a01,&K-2<o487!1]An42OA*qV"u>QV_:4k4ds0`cMJ.3]A
=(3DkA7T?c../mQ5/6aYV06oNftl("XHE>@!`[_iX,ZMRJJBO81WMZ8YYpc)I9hMu4#cN\`^
f5;]A!r2ACg,FRscJ;3f58/@ea-ll'niVZ0>!0lGTNVr@K6BM*`tn=cuJfY^9DPFY*8ZOd):?
QSDn=4q9$glJfR[8'X.=2o:tBnf@I^-8TqmCH?;37AAOnQ=!ab<h.RHNf.OKNL`$B!-)WcB9
/ien1=NM\8/'jZ#Ydf+V!M21e@5:Z'i!/c(%F@hOHX4>%fg?0NNl&l"7KKqnprX#,raa%TXd
BZ\.hO(m,s(7Fnd8\.DdbFN^g`lX%H0+a:(F/)Pi`G2Eh'AQ5=k7h5#JFF.14O3(%L"/441+
Sg"#632+_g2+PamkN$AVB@LaPl<@;MT.p9.qu>$p@REIXMHYGa-iTUZ,ppo%bC?X?D_860e3
$bX.89SB$h5`d]Au/Vg0onm9qjA.LJ2`H^fb8@K^nL:b=C`3,T&&2XBq?4PdDcM8PthTuf)71
:N1J5")%3G;qE6Do?XdMWgfpRE$X1<m)u;mH=!E`s9TLQBDe6Gu120\,V8M]AAr&c\cC^$T$)
'1L*B38p1id\c=e;r@n:)IEeW[\!YTei9A_a$0N?h#9M\9Ob<1k]A#iHFH2k$/"pbh(^,^/Ko
Z@2tLY`rU5<r1s2-m*8E5:_/0IP2r?q"WX6'o#Pd[^d<t#BtTg280RWNICY&'5JD(l)O6J\&
Uep;'m$=APj^(.hf.5kkk7&0.YI_;TE;*rqX.R11BkuIhMT;h&Gc]Af.)4$l4qpGa'4#EQNep
lA,XT0O2_jM]AhiF*hagN2NYp)mO7HM3iW?ok,^o+uT\1ag']A@uu&c8N=]A^W%!mh'fG2X584"
G/YE7]AAcZ?"Ypoal)C4kBn\JhFddqMcYF#6AFkQe]AnAd28+)@?L;b89LC1XoHKD'j9%>P5Nk
onL=*^+-\1Y3P8^:#k.rZ?C<m8(m\?GLYs9.o*qFE4Ra+jm12l1_J-dE>Wl\J)NMV\O::Fag
l/Fq,FpVR@_F9g=6u5?V!G$*n=`75]AJ\ft!0E>LA<;,O)N#&I,qt@/V::-AnV@@Lmn>Hf>I8
c7jipd+rSOQQPE(<eM*md6_fX0r61O4##WmBYeFcCR\r/ML,F<]AR/e*q,G0eaUq=B8F1NJ%u
[;jnhaccH/H+U\@h.'/B#k5gKec8673Z/E5VDt.;Qb(R^WS)ZVUNA/!ui">#:ooQsBo6(]AU0
\g>>7l6UfZ-HDHfN:g(8dD[N1(p7dYgmmk3sSmN^hmI#!nWAf'H:i(oGbX'oX,7cPCcZ54n=
;$pBSS$:FKREm27RE+<Amn-PMF8,DYg\hYX';96qTSK_beT;-8*-P:JFS)CrDiC]Aa#Qn"mIL
,Z5;V&=C)^E%@,q5WT#*\I]AI*4^.dRLW!6_C6P)l?j!a&`GDF7;Z)[=pY/3LTI'HVUc]AnG%F
g-fXr:u1W*PAMe:jGD!7,puOR4=Z7JN@-_+\p1P\)#?1$1fW8;RF(UHE!D0XWhUia<G"J$]A1
o'n[*Ob8j_7-Q:hDF6KI.`S![CG"XPX-5\hK7V$*pAYbqL<(iOZE:$l2SdD&Ok$$%ZJ'['%W
%/]AZ#Y:E04tTm^#o@9\MeKq_1/ug7B%r!Ga_YrE,cWT#.ap24U#D7JBhDPH0DjF01OT&]A<eT
asR\VZFPH2-DWO70UIN#d`7a)*eAND8W/"`"9*]AGD!9H5<KH7B#NFd#@m>,U\;<!NjF.=q+7
?DO6NAlGSW=h>NfDn!(#n;4NiPDf3smr%V1LX19s'n`7Uplh+&+X0g?\80Sk=V9ropBCnGXO
DuD((Vl<m%UW)U>Tit&2!MAS4N]AGD'J)K\;8HBM*Sp\rLg/%5E1Qn0;(2genJ_<o"tM3M]AJt
-!EaYXTZAaYbnXDWTeHttU6I-d]A)`M^kTsXnT0iUKI$8<S.?jBr&>Xosg3)>]Ajc)a&%#9D/`
jHDrX=P(4D)eP!n:'?=\+a>\Fh*MU--Z2DZSganige>K0bF0t7`#:$Q;,h1#ME8o+H_t'EQ0
%B3tUesi2[XpP71/E8iMj_0hstZ[F+^e;O@4,!CFiR#Zai6UG-;qkbIjKc$!h(Ci[KU&-dGa
KKuoK2A%i7b/-GEM)q;D"=dt$JM@uI+:(&4%#mm5fg.<=R'(;Qq_a@'or$LWi81Ybc\Se_S'
P4?DE!N_CZL>.LC"*@1$Da$jN7gR1#[S258-@cm,jG0OE1#Ik,U9>AXsQMRebK^Yqrgbs7D)
\]AJ;pY_j-P)@s;!A5]AL7$EMpo6BS+G:GG<&JH7OgDD;[QOE]A2ZD#NKNC6o=uW[teg#]ARj-4^
SikA;+&A%8UlWL#T3]AQXec1/lT"5Pn5hq:a!(?<peh9ZFuCDsVVka\oX-f!*^lkV<KN&*a;&
5:.n$/E2la5>T@6k-FPaT_I`2E99&//N&^'VTXl0E&*NrGcCF6?tKr/?iA8tJN=?RI^`cQZ*
n.BJ7kloG'['d;F?D=C/!!o_X>-&-lTu_Zn+?kq@)9'<!`DC9:<8kk&"<Ru]A!OC7[:8"lVSB
es@7*?L)QF)oQZWh0QRr[DU7oTbeVl[(/=AtY<1,C(5M8hQe+e"bpJ:^I86[HpmI%a`fK1UP
nK$*e@IojUe;0PObmFDCMKSh^cmh:)ZKts[Z8DuJZ%k)JD[:(j!89pl3ja73c^IPKG`%h+^o
!e^WPZYpJ0_H($^i0eE&uR2caHd/!.8K;uH!8WAQ$E-9^[kiK^4)pP^lr19YYjM+TC"0tqZN
!'Ff[1?hK?5%$2M_(Onl$N0LR$tj;^*/mU1DD'%#q0(Z\@b7DWK^Xh"%&c=nR3]AYEqu]Au&5I
c\FG+XA<O'>,pC6OhQL2qe`PsK*T?8?r3<54:'Y.<pOjb9ULk'V%WJM*QB?h#\R7?l)j,7K5
hMa,MQ!>&0fMo1@`d9om5G1NU<0UbYgTVd5-nQ.pR1a@5C12C96LCQl"#<2<Z2seS284SKlG
T$@k!((8ZbI#NR'[)/9bR"nLHQ?Sm*dPN4la8N:T\/XHq2?4ml!`o8"N3qSaF1ra-pXEs3E-
?DbD^^,6mpb#Z"ql09m:hAZ.2g=tH#Gt!MYrp,l_b@h6#4Pr*fBD-a!lo]Ar,*gQb;ea'<XfN
SbZU$K<K]AMge<(MEsl)bK^@6[,rMNDX'oGUhu681^7&\V2cg]AuSn,\O_Mn4DOnjHi[AcghD%
H/AM_V,RENRT^b_rOr0]APSGS'qV\^[!W~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<![CDATA[=now()]]></Attributes>
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
<FRFont name="SimSun" style="1" size="104" foreground="-11337729"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9G'=;eMQ60hAZ$m-&OSeg,Lk@llK;JMTs$&BI@S]A?Tu@17"Il/J.:$@r)kk",)%l]A/*4&.I
#Ei6]AmG!=cUoZ-*bHVq$\<7UUB.e&L(S=")%ae":T8@S\7=*h9KtHjogF,=n7@2E:99ChtMD
FcguLThttE;J$PESiJFJ(J08OCb<LIG'aT\Y6dVi?Xbk!!XEjNX"pk(Y`KsqZiBN4B`s,kkg
&BQ@&+*"S33F`)9<nJfChOT-T6VmYWLpi0qE)il^L-Q[/)ApFlb!nYC\I`'`sAr$HU$VE?:I
12V6gAR8FS3[iFI`$PqT!h.'S-HaUB#=T-ZMrSWb:>(Q247eR`nuAmg8;&bO8@b)#L"%7tWM
.j"`NdF]AUU(s3qk]A;-fZ-Whl?]A0FfU#L.m]A6aVHU9,;@=eDA76:QCt>-:!Da`M6'gCq/u&a6
pS14!eDt=haXXV*o-EbGN>JM]A5/';620HoqP0RiMLh6^/&5IjMPOSV(@GFK'I?*d2.JRI<G:
k^Po)5@q1r)oD6)3O\]A<C:*eDtT8WH.htU2$s#e\j$?p36)c)TsZs<PD4[0QSi<K2`^XRV?L
+Qlb*g[Dfcd85O/dC%PfDkcT?HYf?=4)K#.GiVAK[W:E$?f.e:,#SU'+HaASZ"N(9f:f)$0.
a>7[E),*d%]AWoRjf=h6H+")jX\k^X3Q%U&4X]AT7JLF><^G?$%6_/2>V_2[B@L/GK*?q]A\m1T
?ObH)nelm%*&#CKr_7*l28[2[f%lN7qI:)=o<OBaD>;+\H2!^\@d(pI9mT;q1^pA?e)/k'-H
iB%%;(j@G^4']A1Q#3$r-":5U4&CJHEBkI+I*[\B3pe7R0O&q[q)o;7_[1sd18GY-l'd'Zs1;
ki-H]AufH9rOZ]ADCWq+QrL-Wp6hd?*dVR6Zt]AkR'XPnl@+IaZe#hm&V%ZVOU?Gra;M8&8I^ne
g-eUcG__uq+kLTNMi=:8qUTm^N"O:qg+"ESA`*3HO&4i]A')kAkk:X?Od26R1P<pOXHCo*=C1
>pBsK2p)dm7fPjXiDX%qq)=M&]AQXFRLPZP6X-)<@<"9>so%6Z(`Z2"T\;a`=usRef.)%f+Gd
KRCP>L4:e!S+^kf'S(ku%Lc7A`r@9J0:Bt84<gb`DZ>H4\q@sjOYPKTh>RJX-=CQs_'4aO?-
G-gPT'*!rPXHgDD_%Xct0K<iiR*e8NbJL,M%(R(<&&$K&\:8rGebj?qTcR0d"?FS_Pda`<L.
gbu#60M6:O$*7a=\ok):h)9f\3/k/Em5<oT"+hu=8hi%Sg;AM#V702!T8`S&a;@'C/Ya5fAL
6*'jGgF@@aqO;t'LTJafH_M@cepEn-jC&%g@=*39^n$/F.[UJ'`\#mS(d2P:M&C(1-Mte;Es
r+Zd*'CMHRD41I`?$^NNF:Prb?.]Al+C+4Las+ZlDJSaX7.*)=2-g3ooW!&1u*Jc@]AfVci5]A8
.!-oM:HM7SG$)tFX7i3C[.st$8>&GX/C5-N&h0!<=LtR)^H6+`#ueC^L&RY9N)jF'3niUnIq
F8Z;<,IHSm@)25Apc6SC<:Q7'k7k6O8m2==A4g4Hg*IXaGjU[o3d]A,SrM%j@EI4G=AcQ6h=I
u:1Fj;[VIpg._B=<bO>H3cYt8%?P9GGk@I$gcMNh(Bl0.-<A!'e(YB(l[m4IrZ[-$B(M[8oV
tNOK4PpP*WDXRIGW&h-IB5k1M2'gH'IsFXNP,ClfA"tpV[)RPeo().Xe(##YTa%mg@-9bSVd
Q+mcd6k%FaO.Ilc7mBt6*dX/s1E&65mJ;g\"W/eET;P@:!'P3)^+cb5,&)';0r`rBe*9'8B$
NS3["%qC7ldMNY;A$HTV3ZkG\;g/]A7#`&#ipuq.J)ctKMoB/'\/3m3*o[=hHdd77S5V2dB]A'
qg=-%Z88ml7K/frA5@LeqZfHUtMhV`p$Xl),!Uk9s@]AmL*ri+Rnsg7)SZ@o:SU!dR8e^5ss"
q"M@;R5X1Xfh%/Q8F?&st^&D]A(N1hik?[_mr\PIErr6dn?'KCe$o"&Z+&Hq/cNU88m1*sHF#
41lE2R6R+LC9XSkeY7HAd+bR.LG'ZdM*kC29b-t]A:3e=7L<_XbjJ5r'_thG9:0ZeAgT*)g\"
89,Iea781'(HQS:4iAV=`qUSILo6-5sr]An%`PE3q,8o/*E[c)7cpRGYn&V+P8J0N2_q!Wkej
?U)D:j"?t7qC&Ll"XAAYFJ9Kj`:I.[!X\0XDd)/5&>l)1?C0,H0HA8^<1DJ#KH&9HL76*t.Q
bmt+;_ls,j#$2W;5bLKijB\>^Ih>7%he'laJk[;^1"A3G0m:K_uhF9XZ##$Zq/!"Aq)9:,k5
T8IB[ljQ35D3X$2B@bXttq$;&Mqcf@F4f;1EjLTm<c@/0B>X@B&8e<s<9?Og7Zr7&F<uo8sk
f\kp$c;S$[c2N$((f(1JTW9Kfa:i%hG,@VW@Vo1$N0nWn8!nnp!rm(a0TLOGJ=ZP$l8sk)1`
$]AqJt#)/)1?ip.e9,&9;ZVfo>%fi<6CG?0NZ$]A9`McTP3^5!87_0mO[MT!;u.\g;:1c#_3%'
PK,ZlR`+b4Cd=t/G(68qh><6IPhIn\o&>.X!G2#M(b=JF(m\smC%5;r?[aM1%tmj=fZgkr3"
"KCdO'rS3jbbJ&EfrF=+e!t8Vn[Jc@`WU6&dA6jC>$ZW@p&[roG>q+EDc)lRQ'rlh@<q.O:%
&+:e`12\ZZ.VBu[9"&kW+lNAa<Dcp.<_5do[efe='E%c+ILbB,m.uioTk!eG9f#ZEXLJ:-*:
kq(/UuhAj6\2WGFk)(QJ!l!6+TG-5[Aaj^PcF*P-.^Z4al\U;ldTLKrA5.ZgIF?>H!C4%-[c
f:]A_3-U6+qDEfo:?N3p<5_lC#/8qVl#hUGW;+G`9j)*]Aa"Qfo?.Y#!)bTqG!-9Y(KZ'4K)OI
AHKi&$);Gt.(I8Zh`\bS0UOSCcDnM,TV=\>6e6g[\BGBckk0?b(f>57'E3+aPF;R9qW0:7:6
Cdo?>iC>eSD/dq=MKpT'a,<3b/=%j9Qt>nLt@_,6VmtGfKjPVPQtcPmg3faU/T_V,,mGX\tg
RVZN2A>UQ(t<]A2`LGr+KAKYr4]Afuk"T'<"B=^7,_"N#!d:2o%[]AfXH;K8SQ2rjXB]AN(`jr5i
pNL2+HQCuU;;bkr=BFe+,@J1&RoC"9D&*dW!-8cUG@=-Bj^*kQCnW-Q?cb&:hc0:aj$Ln;(5
rh88As!Cgd?e*9U!f)g*NI:qXH/A.X<8"Z=)[$f'o!V@mW?K(([s<8]AKLfm^2X_BhJ+5i@9a
6RW&eJFd3!dK?+dX95EDKPJGF5rXAqH(-O8[,hh.;T\:6#X0$E9B:q+bN<c6PK@&mB"HZM_&
bi82T1PJs(I+OQ[k>'JK6$tWTl#WV;/;_<t[4qMEoo@j6hAFR#Y?`LD';EGI;h6]A5L"=>'ge
[Wh=)qS65o57Mbp[k!??;2NWoZj\Q(ZM&=n"Fs>:Qa>8>cjuX%QdHC$#PA#$UdI=Spr855Mc
AllUVNPmcC3&u>*V^/?7O./"Ar@l8CJ#OODO1L95`(4>YB9Y,1!pe-!(PU.SU/O,Mi.hq,r.
]A!H#EkKMi5YUlKj80BE=jp^cHm\<@V:S%[3$b7M4]AWIZOP-'oO2Cr^7eQ`':.LhIL1J5h089
Q=O[V;<4V'\,cglRVEG-G02\.Z*^&PlLj.+erA>mH+_le"6sTGkc]A8p]A5I@R99fOfB>U?."!
/6`PgGju$(P"L)>JHhg;(2[JQ0:=4j#="5ENiOLt'4+OSo)O1s=N.h#LKe78G(2=2aDncIU+
D`tr?NV)=oX\S6,13ZAk238o$'.MQXY%o7)UJE#/mlh!aVBN1_N&Y;m+/k>ujGj05u33cuea
Ac*G7\G!iC(^A]AV/9AjkW2(/B(lIT/&C*'TdP+DCRW+f"K:&d%9;\j*$F-qTE3c!,*6hdC3)
D)9gog_fVa<48Qh+$XN$:>!W"gLm9agU'Q15Ic`WdD?/0fZR/LUOJCb%]AfX#XZW>%qsN'b='
#k54&M.edF&F4K:p_8*2%>d%Sg3USSR'+M0"#Uo[Q)0eTK7j<+I'f,8$0\?i.`\Be*"NtH-?
'W.CS-'niZ2Kl?sB#3,b/R"(TtFR6pHrXbYI&+2<fXHJJL7)U*bXj"?JAWB+%oP/!+!K8=D*
ZKoi^<5Ldnj>Ck`",Uqu6a5+(Ph]AgZmI1%4bF<i(&1XFOC,-p6:'[(J2pGZ4b6G=\*Ur5DJM
kru[ho8TFP4#WN18o(^qH8"(c2[Uba2-]A!L^-0>DZ<6,oUG#Y[q`+ZETP(G9%>H:f+-uV=nW
C74FCW,`J%d+!`;&Y>P,<Ke1)SqpI)=bGlYA/!LZ<Ak8P@_43a%%;3u5X<X'pQ?d.F5AB5h?
lhC^nBM]A'gOF.i5^j]A(D;VUJ\*i9fEECp/G8Z.$cdZLa"_C]AcrQ*>l7Ej_Q_oEV+T1;>aR)E
1@b8894h=LGYO5>Y?HmW4W1"j?,JIoKD4SXT/g>$Ns7fj(\Z8u6=\#!`I;%uC97DMo[6isL\
ac7s`eZ5+2eXY'<e9i5NMH%=a"KYeW8.+9M1FN7Ld)B;<f6VYlQJ0^?g@-040$GIUBM*\>sk
q#<r$i8I:fB-[3GY\`h6!+pePTVQb\6nPanLAErG]ATFiVk^gYCM0#[+@eaR34:'W?m,M4c,i
ccV+;LL,m.]AG3,O?3SudoZXi2kc]ALd-lV>!Q/+UTId9%6q7"Np`;bWOA50]AR/@YtC%-Zcpai
cr]Ae6`#6+mkQ`XEZ[MA*X/EhO"^+&J/qM3#L!hRb>L'J-aRX6Ho/S(m73P?Xr:RSlZSbEhUa
UHGT2%/P[^`2#M[B"W<ZE_d/hp!H1)S`rrH?%ZU6#'JL8F![^k+92U$*g\)XY=EJ8"'OXpQ!
Z^&Zlpchb`hWs6%A)-=nk?MFdo<mAmAXu1ELEH)YhH/+r:/D0Fi(bjoJ:7b&rfd`g&)A]AP6s
(?jJ9220iAk&@o/=!12M-p4?H)?#Uo%!sGLfQXVj]AB;AG&:p,>>C<>\pk']Alb42;%sf8r5N&
3W8#nV.<ARNX"Bfoi<<3C[eY\Lo9feXNRTDXAf"fBdh_4`uHQ?A*oEctFDou'AID9"&j50kY
RqP_,J9)/:,ZTfNHeT&6pY[+9%^SuOD7&<sB'N[M8>u`9iVmb$cp%L0XXA;4Uh-d\IH21U#l
D:4msAD@rTM&=rri~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="800" y="17" width="138" height="38"/>
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
<![CDATA[!?D@'qMA$D7h#eD$31&+%7s)Y;?-[uU]A:Ap.L64Y!!$S?8LOQ="QW-B5u`*!mG1!ej?sj)iK
b6[/0'1J1X_8>8;PBu!"f5<&qhJA/-k)IUmu+p@0egn+b-MS8&eMn^N-rum.U%7?B*^Bmjm/
QEkO*[h5A^dI.ZI2O,8[KLkpB@CdqdFI<nUU6psGm6OYCU&J5Ute`h]A.*^:el+sJ5rU%9l5g
k+TdF47OBil-fl04);9n`%Ns`h,al?TpTd=[OWHl17_[CVCr=2bc\.6q$dC'<Xk!/[Cf+;5L
=Mg@N)DT0&X9;spAhXS3fcrH'V[/kB0ghHEPj>"bG[b$MYcil0ebI.),GlhTY*F%pcRY>P#W
p"02@7u4qP\Er>-!*m<YEALjmPYMXt:+j"\]A_Hi4+3O4nYP9d"_3q,[VWYd2%e<9uH\UQ23q
1TR5d[C\D5>[i+rUZD`dc$]A84s2g/jHWAFD!QF/317/3dC2SpDi)&I0'Cf(q09s"&U_bpYF<
]A^"Xh5!/I:mk>/H2\HN6=5USbnroo@2882da4E!913c@&BQUe^`?Ld(;7RmIZkX9_F5M*1'3
r-=)5a\$B1C)pYpP9pRV$jWdD%Qbu6hB<,hnCWZcDgVuR+,BbgcY,dHcD\mA/bLKoOYOT:NS
Fi$aSXV%fcU"rdEguk\+Ep!<Cah0>@GaX40_=QTKp3]A/6'W>!?Wt3LY"oI[[aQ>eKj.nS(&?
^@0nG!!'fjoRbQE<6cB)Ot!N$e=(C%Q(?)K?Mou]AUiZXg]A?L?VJ./E@c?8Vt-8e]A02SV/E'p
X%6d<N.c!<Cb3&Q;</BDIuD!!"oPlE#=O#,5nTXa0sIG/>q*R`t"u6`47[c7)h<0$i<pFSV)
%p"sE\bU<Gk4/6=;F!e4o0!]A;.*,>:jo5j&h[*H'r;?)'o?Wm\h'EEZSOZMroW@Sq3nL.De6
psacD0aY+/hO:+rRF-Wac$9$YZ"_".FD2Xk<T+KfI?(NF65O[oG'Etl'AVhHMBUh,FX*rH1g
X8rfTJBl&T1t[j;"SDulpoT,NHhJW(M(EEjs5\D1?GBWbD-V#Gel+43*8>P.srm%<nX:8q0q
):@/OB?@YO$r,Ar"ER"tePs]AAZ5kO_]A>s<2bI+_F\U:7OBp?AV)Zg!>/!*4lUZS=PVIj3h0d
Nmee<7:QSsS"a*Vm=6m4ZFJlCX)mQM+8#qRBN9!5P-[Qr9YaaK9ag!:Gn*7Pfm=WSVIr!!$8
Ts,E2%T.M(\-dS8o5Nh$0EjXnuc5WfqSSIgfl]A&iLQ7q't>M/pQrR#a:X[Aep>P.srgbI,.3
41/L88b7a6PJ!N[\IFofA?L1SgF8OaZ^/un(g_=!5OkF*[PYX::8E1R+D&2O_]Aq\X8]AL8kql
ahhla3o[J`u"TBAm^bC0f"J+#8s!!$n%P:;J.Q<*%S[]A-mH[P5S0p3O)PGUtu^.WK@V!s!9_
2Ii\A7S8`+oPPTJ!8rU%7nGXI6AX%nP0m,,!"R)E(S!6Q7Y6Z&23+Bem/[1eFQ*ohO^_\2I<
s^r)pk59GZ]A8C]AmP'PdsE#6!'j&?(l@d=f!dmDod:b]A_m,:U:+e\q!!'f#@7Fr?V;i^^#1?s
QR8Eqan4_>R0G'TrRF:<hP^^odp\FsT_r$LP*"fXH=1A51!!#SZ:.26O@"J~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[!?D@'qMA$D7h#eD$31&+%7s)Y;?-[uU]A:Ap.L64Y!!$S?8LOQ="QW-B5u`*!mG1!ej?sj)iK
b6[/0'1J1X_8>8;PBu!"f5<&qhJA/-k)IUmu+p@0egn+b-MS8&eMn^N-rum.U%7?B*^Bmjm/
QEkO*[h5A^dI.ZI2O,8[KLkpB@CdqdFI<nUU6psGm6OYCU&J5Ute`h]A.*^:el+sJ5rU%9l5g
k+TdF47OBil-fl04);9n`%Ns`h,al?TpTd=[OWHl17_[CVCr=2bc\.6q$dC'<Xk!/[Cf+;5L
=Mg@N)DT0&X9;spAhXS3fcrH'V[/kB0ghHEPj>"bG[b$MYcil0ebI.),GlhTY*F%pcRY>P#W
p"02@7u4qP\Er>-!*m<YEALjmPYMXt:+j"\]A_Hi4+3O4nYP9d"_3q,[VWYd2%e<9uH\UQ23q
1TR5d[C\D5>[i+rUZD`dc$]A84s2g/jHWAFD!QF/317/3dC2SpDi)&I0'Cf(q09s"&U_bpYF<
]A^"Xh5!/I:mk>/H2\HN6=5USbnroo@2882da4E!913c@&BQUe^`?Ld(;7RmIZkX9_F5M*1'3
r-=)5a\$B1C)pYpP9pRV$jWdD%Qbu6hB<,hnCWZcDgVuR+,BbgcY,dHcD\mA/bLKoOYOT:NS
Fi$aSXV%fcU"rdEguk\+Ep!<Cah0>@GaX40_=QTKp3]A/6'W>!?Wt3LY"oI[[aQ>eKj.nS(&?
^@0nG!!'fjoRbQE<6cB)Ot!N$e=(C%Q(?)K?Mou]AUiZXg]A?L?VJ./E@c?8Vt-8e]A02SV/E'p
X%6d<N.c!<Cb3&Q;</BDIuD!!"oPlE#=O#,5nTXa0sIG/>q*R`t"u6`47[c7)h<0$i<pFSV)
%p"sE\bU<Gk4/6=;F!e4o0!]A;.*,>:jo5j&h[*H'r;?)'o?Wm\h'EEZSOZMroW@Sq3nL.De6
psacD0aY+/hO:+rRF-Wac$9$YZ"_".FD2Xk<T+KfI?(NF65O[oG'Etl'AVhHMBUh,FX*rH1g
X8rfTJBl&T1t[j;"SDulpoT,NHhJW(M(EEjs5\D1?GBWbD-V#Gel+43*8>P.srm%<nX:8q0q
):@/OB?@YO$r,Ar"ER"tePs]AAZ5kO_]A>s<2bI+_F\U:7OBp?AV)Zg!>/!*4lUZS=PVIj3h0d
Nmee<7:QSsS"a*Vm=6m4ZFJlCX)mQM+8#qRBN9!5P-[Qr9YaaK9ag!:Gn*7Pfm=WSVIr!!$8
Ts,E2%T.M(\-dS8o5Nh$0EjXnuc5WfqSSIgfl]A&iLQ7q't>M/pQrR#a:X[Aep>P.srgbI,.3
41/L88b7a6PJ!N[\IFofA?L1SgF8OaZ^/un(g_=!5OkF*[PYX::8E1R+D&2O_]Aq\X8]AL8kql
ahhla3o[J`u"TBAm^bC0f"J+#8s!!$n%P:;J.Q<*%S[]A-mH[P5S0p3O)PGUtu^.WK@V!s!9_
2Ii\A7S8`+oPPTJ!8rU%7nGXI6AX%nP0m,,!"R)E(S!6Q7Y6Z&23+Bem/[1eFQ*ohO^_\2I<
s^r)pk59GZ]A8C]AmP'PdsE#6!'j&?(l@d=f!dmDod:b]A_m,:U:+e\q!!'f#@7Fr?V;i^^#1?s
QR8Eqan4_>R0G'TrRF:<hP^^odp\FsT_r$LP*"fXH=1A51!!#SZ:.26O@"J~
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
<![CDATA[1828800,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[13075920,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[企业智能运营管理平台]]></O>
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
<FRFont name="微软雅黑" style="1" size="144" foreground="-13332767"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94j='5+mHHX(KiCbI;>/U:OO[O`\,Xb\AsHI5*L#h3f-\Kr_*<Y,fS&P+[b>AJSaAueH:OL
1hd7>+cp,.GMMC5NTI#nXWO6jOr$+HnKQDK?$Jp8MJJ*d6:aT$nb[i/d[hhtLH,n*b\fqrHQ
us$[<]A/T>]AL=nL"oS!Yf\:Ot,NZWGBJ*VO%6l$5QI/q0;Hg%<:6*Q]A>dU;]AX<=$@_J%Yf3&A
\Ie$H*m/ND9qVG'7l\qF8g3frXGLYCS*Fh]A.mshr2Iq04$(WgUf67H>LVAOc<Beaf)#l65B4
W)FOb[C]Ak"PhBP>H7W8#K7a7o>3iFZ!jKEXZA6Ck,a_KQ0#>jANn3@DW)V,?']A!lpic`ktg*
5"g/5f*f7%<.+Y7jfMYE,B&$p\Ld%>iohQ;P0;Jgf03H_BpUPa5$biV[tn)AWr.=0WO95Q!i
'H%c/Aa\59Me7%QG$%69Lse@Z"UO'oNN]AeOu+eS.>p71,L.N]A+UOP4\:XTkMO\L?h(OObb['
rEngNtTso`&ZiO.geA>TSMXL$(*XH3h[:qU-&]A,B`]Apnkq&9>8TBs%E"YLK!Wfu[*`V4gr'F
ET5egNC+KH'MEJHWN8@d_$WMg9Ld"m,MT&k'VYF;g,;I4$8k,Y-g$?kXF[co&X52c,9FW[p2
T$7oYfr#j(GhSpHU*$UR3[g+n-CrFZ)GV#(64FX[1kE\beT4$ApKHqVaYD"A`@**m:L2;2%B
oClH5XrIN.q+;\$jId_!*b?&I(p`$jEfg#4m,LaXHa-M*Zt?W:D+Z&I6`Rm(B_RQJ8/G#`D7
LQq9l:[1]A=B!)@.T\#e`LIUbetDds7%YWO8r_]A-;/l*lH%*safM#9[TgoM9'piHR+j(C:iUq
u<d*kIs2cq<>7LKU'.1:Cl't%a\6HSng[\Du2&k#DaaZO`OILj7I3,_(^4GU[MNgm3omu,p?
R0ZF$AL/@,,!tLRnKM??&efpI;ag,JKe"DnA]AJ"=Zpc=iAj-hDqZ"Dh*IZUpq*tXVqi.oHE4
=D#6EFsgRG0FbkGI(G\*G0GdE2*0#%79!!0rZQhfQ\4-/Qj"?>Z/ZLRqn\0'!m'aBUbH-Ehr
@W\^:dE)'l[eS_r;7Xb3TpN@^/H$Tt^/6<eEC]A]A8=uH@Saohujhqk37,NaGs;Tj6;Mn:i_=*
0Ub,%EHu!di&:GD=JX.Ra^6:ig(^$Gd&3*L3HNXTB(cU5^K+BmaqQ%tTJ!&6M(<E%AEj.?4d
?H&LZ\3sb4NqMIie7f'Ep,^/ZSCE-LaUGd86S,mnpK7'SYE?Ck-%[GR@&1EUL`KPV3bl]A:_7
R]A7`,pMYA;>Q^iL/Pu\!/F#!_XB,lK-n[,3`2cr1YH1ScZ4s+?u6.Kp@.FN_t>)O)l1$)F(j
9\72RFYa,m%5KP0jHA`MX2I0L/1+UZXkBG[t4'ghO0%5mX%7E:*Im5GB*[Nq1rR'7@>>&>Ed
CT\$R7EOaU.-b'kPu>u^U7F(5?$=1i8M@D"X_9l`Z]A3phUt`s/KVPjj'CFgpm2X$;q4V5="&
7Kl%.(IrdHnE\Loh9'Y=J[7iOS@n'!EJ@A;9K'676\KH*TMb%Rb_2'[>ZQp3BKG/NrjXgp<V
@l\SC9[G8tNItabbm3%Q*@V_I#(0Ok"]AJuZ`Su!tH.@3FZE69B7eTJr[*d5i;2-HcK!\l,(1
Xfej[a[SMGh="3@7S>j7*#.Zmb;I+s)#0PWV&<?E?l"og6bm;EE&_K6<4`/Y[NCPn%@.bG%5
5-qL69VOpWQnf&\"uEDC*eBe)GWD\7-L+#?G4:t,Ao;K=afn)I3+mMf#)!$&@",GLZEKG?\S
e,m1YqsPK5?/+nTe:sd-N61IDJgGo]AiVAHA:9glQc*m#JNKfA+Vd6jN*Bi2<euNXGl]A@)[Iu
320+E\NA+@iGo$cCaV6Gd3dI6H"cESdt+:-E7&:(<]AhWmN5j?Q`LL-0WUFk8!43pQ-e1'HNL
E[V0_TaSAS+X;I-e+G>tb)I"T6e]A>bMm6l*tVi@*VFL1W<D>"?NNIi;i!pn;+)VVk_b`ecJb
%IBU3b;Ii:'Z!._>)'V)]AD7#fONkf^r6oU/ZrHLSrpotfV)V'^rqQu&]Agt3"=Q+%GsH5>oHR
(2SbNCGBjNHpLX_()dWU)aOR&5H_,iIt6?KFgPV2(EBWU(3Z8$HJ\$BW\(43L=7X&d\\">b&
$""se`rFhOYWpL`gQ(BClS)MsP\/C#`4jAPLS8Jk#c3Yh/%SGEJLX1/@ac!dqm98;WE4I(go
?o-CM+D+0"GOtA'W'Y8n.s6ef3TG<se*A*o5`Q@-8lEaTOrYG_U":8M1Q3W!+KTC">%n-m=Z
;TE&sQ'Qe%18kZ1`6VV79)?+s:Yi@'I@(.FS=<]A5:19_^,h-6BMLYHQeo$XT9Pa7H+0M/0]A9
Gt'o+F9*;bP(&M)!2Eb:?B7JDDYB]Aeh&0,Mp_%.RQV]A+A:9QU0PPE6905?qC"ZDD(Xa8u"qp
7VlCPGLipQJi'J0nF_ad_knm!r^K*3Z;Hkf@*:dQ@k$EcOB2N^A0!tMGRjT:n#0\S`W4%Xtr
F#Q.R/b^Xq"un_KP?<-3fGQI,W41NJ/3+2dk5(0*J]A9F?L$fc4+K'^\YD<!k'5Q35iAW0IlP
g"QrI!7j5XklK2/?!6g2!8XTP:,S3n,nYR(s(t/SW%LKcsWnk$n1s<GQp`PjnH'4'*Te.Ipg
ECHcF()nlLNLY,:JUeE0<(qSQ+QSNh"2Ir)B@JM[5Y"p,bRb-H$P(k$a>QG?Y1&?>;$8Eo_O
`gS\M1o*Tb<EV%BqL$L$.`:2'(el!n3_?mQt_kICHR<fUo)VppZrRbA:/EDqr"t.>s<I"Q0H
ifj:%S]Ae9,U!puW^0ptjJtJZlFrmAgu('os\5Ys@":ArHs@dGhI:0^(s#rn-#h_]An:INI&7q
,!]A]Am'7([bGt(hZJV$aM"dYQ<6jnV[b9f%F%'Xr0o6Q(!Ue`G'm.RY&[3XHJDZ`sE(df)Z*R
^?G\M3S$,SP<V1eVS7R_;E0&h#&G'5Sm.C9JPa<8/f5Q$Q./$'ZSa&+6ZIs/a;K2C,f3q2AQ
NeJKXQ$bBo`[C7F;\hc\l+_?3bc[I<H^V&eCN!c7oY$LO`E,4[s6a*n0(_Fu?H]Ajq6g&FLCN
CP,(5$0I""Rj`8!MmC_fXJ;,6Jo5WL#f`.[atIrA6:sH\lmlSpKm,NTSJ*4H_Au>E@B4t(n$
8=C<NV)UH?XT98/nu%[r&$[>cRR;D`g$S'\AaJ,%`hF5Vr:,H#Ra\MaR3)IXuX\?]A%CVPm(u
mZ0+7Q4==f'h@`hp;fP)6<[!=KAArQ&LhNZ7-g=oj+u%;W,T8Yh`a&6C,<06j`cDKC)!EmcS
,);ca,,XH!L++CiOH=@1]APg,0&?fONXN4F&QCc#)o<FJMPH?9EKhRo)>u!`;SKnJ^Rb9Kg6<
G)Y'k?VBgIaX>NXC^LTp4,<hj%Ca>+9duYu%R]A^FE(cgb)<?."[1A>!;qm(lH47^m(>Qormh
$*+o:rar_-Xh1/3n+>+X)-fL?U,l&W"L,E[pMZ`XuM\CPNaJ7clI9WH%B'02F>6Ppn]AY=bUi
48_rr"oia^>W96Me<:BUuH+,\>"c6_t(nWcC$IAasne8$mr)>dOL(]ABgPThHRkm6JrOd'T4u
?FBOlNpdN@96:-s8P)p-VW:!H\h,@lU)5W]ATc7)^"hf4>PJt6YSF!ikCL)/8[98eY!XqXs^7
i1(Cu@EkPS]A_&=Z'/cG9lPa<lEp/,K-!4M6B,5BuJ"?ct=dp\:N%5/9)3N)F<q@qp+FG6_4L
Ra9q#G(,C!c/5ZO5Usb">)G6>ldmdR"$'NWZhaPK/J@Zlj.@Wa=8E_i'6G_\ggdW/t<qX.:;
^B/Y#YoZ+81k\m_S3!$0FP4NSZF8.$DW$D*U/+]A8t-0]ArJ;2Ke.qEN-8U,F1kdWZHR_=Maf9
[OYU#DK?I%;=?-3ONhE8*KX3bt;'@\XN<U*/?&03"a\.PaA)DbR9F.]AQi&+uu\5M%0*e=sD?
.eN.q&Z*]Aba]AXgH.9"ZUCH:aScN*U)<INS)G9-ob&2(D[c#?.sL7fi@Kn,3'nq]AO97FHi'U%
/pbT;mD!W`Pu[WTFs)&V]Af)Kn$*b?q0:3<$k#]AU"%[-->PuM&P"T6W^^1Q:rT-[L,LF/AkMc
CJ^6CICXZnHKHEEH]A?'rF[\'6tNZHC7TWfo`_O?,4rNnV%QOc/h1i7Yr]A0KG[^@n_Po0\LcT
UOh\mR6?VU^e+JF;kBoT)_A\&JQ_ri[9;,0J*C>gLBcqT^u#e?jIOp_fVGHBTAQhdr"B[B:b
>Y\eJY7d5og%2><cU,@$015u\Us&q7d\.gd0dU&936N'Tb>`_X8lH,2L,Ri-lZhV*)0)%GZ3
X&4rbE$_+b#0X),*L-l<N@CTMQJ4pZ!)[5C5Jh;;ja>U,TRYa?Pga=8jQH]A+!g5pOTR(ILEL
g>3G&&uEPUL#$]A0#uZ1puoBMTmN=flu!2c-UW$`>4!*c9'ZSjIri=:XqSQ`Z?Z&da-Zqr#b-
`E('?cjAl;*&"_R!@eKIIj&s`QJ$%BErI[aeeh8ZP72F_PpiU`@NTNkdiQe0O?J^r;rj'DA+
Sbg"rUhsDcpq$eglO:l>Nd?B,%143a[08HhC4`$UF03V@s5L\F;D#kO:#$Y?++8k_tb:2/3o
o6\F8(A)5PS\hO2BB@Xt.>-W.q<RnL`6A"CktU"CGGrR\XgXiH:P>-r,7VlONt'u9^m:O-t?
Gqj&6TY.lA]A<1/HRnKH&=2bQ_[D[,N3XppYGdo$?R-b';GM*DfT_bXK+1`7eWJ8XLQXb<!9D
hYOLbZK[hNSS_`=C2^)%!-`3:#\W`DJE-N(L[t\04rFj[\!6;3#'CkP&-%Flq_MYTFh\$te"
pbUZ,hB!p(/ei8`H#NtJ:55bJ1iJ4c-^,TD1kfXr5(k#Go!%\YbIj)pSFr^MA43`G_fP.<!*
@NtV+\L\j!p5Xi/.L-e-;X\"4P&,jUG6++<rb<+djcFJHJGopbcTH6i94r8cP#r6c56@(gLe
S$r5^_B&9nHgg6*Iu&L@AO?j1<fNaIj2m_R)%cLP9/DG4knCXPo9HtX+Vq#"\S>\Hmr+l'MR
5ke08a.+Bib`hRQ.<9``V)CL06KM0G3<NEsKUGkGZtn6X?QP#a<sQREiJjlk5/c6QijGUYUf
:S-Rn?4+^:CIaA]AN^\i9@)om%^Q!L$dBe1JAX,'\cc+E`HX!pfn?]AH2FXe-7Z0+@%0imG?1?
t\5_Q-Y\<!l<W=#71%C)7Df#Mg3j>3>(VU&C:LOWs$#@uM]A/Ot]A?-@8SAUBr?K(T2a)fI;lN
8DNnl\R[\>\@h.Wt<]A:=g+#!5@E;@_D4="'KP9ZV8#HS7@T]AZlW<0'%`>@Fg<Cd_,3WKt4$O
ee9j,M1J":T!'eFr?s+T@VfPIN]A_0)<\J+d`!!PWbWA\Xh%o_[9:+1fPZK]Aa$-^0$Z1hE.#C
^D]A=Pc>#->M&A?:4MrK8)#9Q%>Q@-h"PBp:S^>JuRopa);/mX(FaSkdrn1F#DB:H+&WRL,j@
8NmHK_'9q]A_V&,D)VNqr6Y1U^XmK/<7:/pOF_/IPg6UW>XY,Mk3a,hgioF&LtjlCKp=e=$A8
!.5Km/l+!H^Tk*n-qM0W:r;PYfmhNV;HJQ?qqQbU^NR:7G'&BX`"Y3SC*&#J<PX]AT(#^rQ/(
3fpr(FeiGJ1sKX3G!'d02HuD9O6T=k28:e)M5:,qXFJk4eik2$C$ma*Y\m$n,fmNeD3h"p-V
H_!0;146Cu74A37acMTeiEoeQqodTfWq5;2j@*k;4IjuNM.*4t:G+*mnX1O@Ws1J_Jo,:mHj
HH0h&-ctV`Z$8_DMEf#"csC>E]A?uE$J_N:qNH@dFhAnNYh8j!j&nO6Z[,%4`+UikE@A/9f>^
!]A<01iAD3)?ip5ui^;K+:ejjP#RYlq]AD!_d7V&lU*%K)S5:W"EGptl\H]A+?cd7rkrIOHgI<C
LWHm9Dr452G&Of%!pW0E@']A-QQ-7oq'$C.S3k1BQ;$jpVOgQHIt&\f:p?80BgVG4oFdG!JCh
Ani_L6D@naZ::F.IVtJ2WAm\/.U=V`3C;_4Hh8*NNH#skXc<sLNU^lBeBmj.gg8^QnMTu_e\
)=YgQ:-`7mmY:6m;X$`V_3-Gm6[Yk(j\<b![jYS*S>Gs5iU/!;$M\9(3p&jl%:I'.,93"&B\
>-f*5UZ4d^W-PU.=fTEZGX\,QB`5?frQ6UKm`Q7REY)&q4rYRg=^cbFm)*hDcTe;X74'=MRW
2L.3-/c+g;P2(equ[/)qU1B_^%YA@+&k`;6u<L-*(dpaGNu$f4id[SIWspQt_cTP5o^D.(+u
AhcG!cLT>mHX&%fo<)U",AOW2tIXFq"0KrZP`b#B`,/[TSKSsEX1EUnt@-FnGlH%ud6E9<'i
cb='EEW=f.K:D`8at-c*/0MHZR$G)\f+IYm]AVk':F?O<n&'S!OiK'9JA:XeI7,@:osbb>`jW
SegelM9ieT9QOknHB5MMhIJ^=!_"1"@4$n\+=`iXh/XI6.HHP(^\"AN%a+M>jS5pi+]A40'66
8eEUOGsCG<CM6/8V!_;Z8e,;1\X4DnJDX'/1W'$S085Obn')QuU0G/"+L`K+cY-E$AVIlj"C
3k55'j]A^h,#u6"qQL.=SjFQ`I#gsFPqs-"P&XEOggm%V^Zf4YBs1<b4fu:/69_ah#+`%RR]AP
K.$^$H9u?;a0`!E[EU3/`1P@sec>mi,4VcS5F/J)-HlNs"ea!<W)tt+mMK.YM.jRqA2H@`qB
#c;3&+1Hk6H3$8,lZZob4:TtjlCJ=*R9Bd$^H[0_C=n4NUdg>pHNPGP%6b&1-QF5KaX)C,8:
n(2cAp,'5+7Nn=,u@r"T(qXN>"L=!uu&I0rFI97ek[EeNR+Db.jLfSi/Ca5Nt.0kN;fq.]AR<
VFkJILsll0\M/r,dXV]A.k1BQ#X+(d3=D>)!UX4u5,H!K;Ed?q:_"nq75VhkF/?IddC%thYdB
`gs"j=Y3emU&reWbuT+<<hU(N8P%1G!(ce4>9OSX^0"HN2.b]A@0]AbYfR=_nj]A8[(V5bBoDR>
-#QQf$5/h03@4[GLIPJmY_BftonRi!$B3?!kjl91E<:U`8UQg7baOD7t\)L^^WM!d^0)TSU(
fKa72SKQWrRgFg!3T(SO0L\HV0@`F&2*S,[X:>cB'\Jb'_Er4QRnYU^T*NEVs1K0PWh`,_kS
uaK6U')MabSMEB83SV>H+GciLcrII[cTq!q-3f\\c["#lLZ\atIR!<~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[ja6efPjeY@4U,%^7<XQ!$SD(clkP6@CR1'LahE`PmBXDO=qFPA*@:S<PDTp7I2t1ZloS_NF-
#28chDh53WHqc_`Z.+fBZUHce%`8?.X3H?El9GjV$MR!.`\<gt2f2p7]A;%DfW(7T1O7Rmf5U
oh88sBeYo#"WT^jmZ`hs2>?;md^KYTsEEA\'4l>9mD>Q=H*`4"(c;qS8D`N*!hno;b!+4ZK=
+t>_aP2KKiKj#^TU$Mn#3SNY^<,i?'m8!SX`]AH=c/-e\I[Y^/^#,E*IacR*>YTi"4qG?`Hh-
?&!]AB6h+h$r1&JY#d+"DCjG(jp4n%n+6s8=V[mL60Z<YPB!-f7-06p^hDmALcWs2X$p+]A3=,
C7,%sm!i>:j_!Cs*3l@">X3EH!!)%C@[BEPl^qD1^2JIR*>nV!j"<-\*dqa,DJ))SBq7I_b3
`M".)]AchAY9iP&D%[R.k%pZpUap=`K'UM6nie?<mYs2Kc4k4"qF@l%:=1u/t_jp^)jd`+#B%
I>Bd&mpAJ_upA;@bd7:O:*EQ$KlSQW+:\JE`rMGhX_'aL,o9W:__h($Dc`?trf6?\;-?U_"F
&@p1^&Gt3kHj@gl%Od0>[=o[(G?H\4mQCI4lCF`s7&p@]Addf&=-9Cdbk1DXS'fuQ3d@^\bLY
cZ6\I2pR,Rct)k&1GG2D()XW+[N5hJ;=Z&`iAKDB\$l8_8BNCE[V10Y_BE;@j<3?qqH)Z<GI
BOg2q05J&%W5lVX.VJId*E7DuUW=aNgL@_LTE"u&:9d\se(9ShYjlu1*FL<"F!%p!b6Aq4F1
#(_Qb@g^de%fVG?.ON:O(jkJJUg0P`"b\+C9Y'd_.)8or;2b'2+[lg&t0RIcEel$lJ2ae&oa
JCd@u&q3(=$G7H,cZ\"p$`H`0QA0)Hp:KN^u5dYPAI?X9@"lsT/?^(X6]AVi[<kOT<@i6#moU
M4)01Gfe"UO.#_N-`&$>b)_qg[>i39D-Q^!8ooo'Ja)'(&t.j`Gp*`0d#.i#JX4?JDp%BD_D
En_7ItCiAhiKqtD(IY$8hJk!J<a]A7#&h3:6<uYfff=lJ3SO:\lfB*hEA)c)oMP1@nhS91=2G
^]AN0VDQ*(_df"'q]APJ>`8@r=80p;W[S/>%G\H,Z'FQ(\hjiSOSAi?^S>q3F8ARthKTUtWoQZ
DXB5I`s3_1Xo<qjmgNJ0DbS2>UrCK$2:[IXTP<q=G6RKVVuHK"@6.*3erhVOJM_V;t&3gg_"
rku,q/gMO4YKaK!*p%"d(4pqXH&r$Ym^g.V3n5&Me*QuOr5jbs7FslHo[NQo>dhb?Q_3f6'D
l`jb4X13MS*W]An*5F.u+#hX,N'*<q;p^,SdoUrZdSE<Xb.ef8R-Mt\D1mm261ZYe,Mf5WgF,
R]A+%KgG_qaPEBTulPdI#6f#X9P.Y&E<rI!bRb?WHR_O"XWBZ5jekZoJb06eB#_fG`6"d,)d3
]A$onIA\I2-1SlMNO^?LQ`D]A"a^<DE32C3Q\?fUNn$t2?so?;WO<:'P^rm9Nfl)I8>Bs66'9&
7-4i1EV1p+&^gcgf>)5kD[CXu9VE\#a6tg2;uS!<4<@M*ROE3pbmf`s_8O)$7Q8L(A<H5L2n
%cFVdg<mRc"gmMUf2kV3hiDZ4aB@Ygo\l+N6n/qFnc76M`gQZYuSZR!hKiZ#5N/%<BU;V8A.
clO.g!lS3n6PRq3jn.U6q*n>.BiI5$_^,q^WT3&#kCTg),CVb*"<_MW6/n<1]A>[*db-sl9`O
cPG`Zf"L#HXE_^t8Pb1//'V`Xo^P%;7QZ0nN?<9Vbu=RW?Dn,kjY+mJE`AgBV*+(mJ<2qCLA
IU;B]A`+<lF=nj<R<Grd[qpI/;*T`;mA67+\5*u=X1q7.uMjPn\Qml=#5L!Zi+_sP/Z)$n-\*
,PE;"_oa=r<NE,Zp&N;iAh=44ZK*HM4)T!@)@AU!7J=2T)HX,+@AhZuMnbZ=kG$;GscY6n%9
rWF7pg65q^XUbj^uIV3uB<99j;PR_HOUt9^64RjJS@pLlAML>#17/-0n]AsD-Jas.5a'%!Iu&
IRaJ^H$_/]A22'RXH-EohlP^\-9"?iD3Pg7cgTW`#ObuT*mWjt6u*kPd4c-c-Lb0L;)06lfZ'
kpbub7/%E)>27tK!ojTk/[?C.hi%MA74!%Y?fS<b7@=(*ItC+YKbM7fS>AjrOG?J>m7\JrVB
bme0:GI8>OK>Nt#$Pm*>5l5*Q\oX#;`p"eS#FmE:eae4&nn2b4pRI!t@-*+o;.g:&qde8u7K
caJ3>)<o.=4UV&VV6n?R5()A\Fsa8sPOA.FAr@<a*]Aq%C>NMA'/>=pm.0>/GI%=Ra=`/6[Lr
Iok9qVSSpk.;bBAQ3$\=^%Q,lfm!pJu[eiN8LkWAB>a:;J]Ao`3Oo4jq'EP+@0bf?XXjd'_n2
9kPQ8t0n9`t5]AdnW]APJM=SH5.GEO[mdCN6^.>X#^:t.V(VeU/s2JiYQ"\rkafZ5nO5"N:COJ
(I]Apa&AB:md(Bj[P>Wp-LGTXP8<']A1(%FK;\Dojq,cdK_sCr.=Nqqim^SRnR-t@\[7>L=`Vo
%'R^f:KKl0YV2/'D+#`6Cc&M5P"1bZU,!g#![1u"5BZ$YS(c]A"GMUgO4f?GEc`$2HlklU6f*
7gkqu_=2="u.PBSZ->$m;V?#[>FQ*uP\b*&Ni&AuYW*'m;bc3+Ppl,d&:W;YYgZeu_R_!?2=
\OW(MG,=2:UcI=!c4=uK]Ah/qer'Tk:+MQ39GmI,n,6.n@>Pi9Or@cGf`=S*Q<PN(e/nq\,T9
aN$mJ%!tD)PILl?QaWbrnc(U23PIJ0#UXF!gPHSFr*!h+2p#]Abd&#282Gr_]ALIkZb&K3mq98
iFH@htFOC`2EgFO#D]A(%Xf75Y5>i8UKQ0Au*HQ?AV/h\Jqa96CqBVghjm9eDq0Tg/h5mj\AD
E&$K;BYEH'+1fh?/q+N/_TP<p]AfJfS9dFs<-^LW+)mDLMKUR&'7SIord]AF&-.(fY-pcHO^B5
%?GI7+P<DIN,l!-kC-`LRBY`O2=nLD;OaOF6tP16n0$"aI-7fE1aO'C8h6mP]A-_LCd`dMkXt
#:2E%U#A!]Ag2[:/$>6jt$Z*e$AB*CKO!EfM:`K_a$:6_)[pH75=Fd9]A5Ok2aVQjJ4@W,lUCB
\fD2X()dNp!!6,G]A[tI7*"4OQ7@#tSN%l%47$EM(Pd.C!]Au4)+j6P3B*H`)_#qP*nE]A^lY@%
#b/jZ4\G>Th,nV[kapNqLQfLElo&OuD1)LLUZ/@oM@n6a2ZE"3IdK'k"kkC.T>JLfn(_XSQ8
Y!T4;XJ+,d?L1UFPmc!5WJ;!a_pNiVE\LL-*2<7kQIFna;[!%(;Xa0j9'T\e6b<oP2EPj0$a
$.).tWO:Dq_?fA3!qG`LhD`j:"Oice[=*K)o#8N.rDH)M<2+R?KoDF"WgE7%s*67,O!87:bl
V1H`l<W*3L)6cR34eD95cNE?b9(cB<D`.^5C8)*V1Z'B5d5N@J!h'G:N8"YS6^13(1/KpPgY
6i?V2#5($WZ%pNK1&_GV%r5A&(YEf6J=MJj<j/d:U81:1G_[TQB:sW2YQdl&^S5Kbd:I%.>;
H$+A:kPCYR"UNkUW0EHqip;&\E)U<.tK$iL1)?/Ob)+XMN*`FGgh$G6X?VZGCQH<mY\``ZE?
#(<0c7P3EKoOfR>*qHVihY?Y!d4`QVZ,-eI97)MB<8d<Mq(L.,"O$m11DMrlLQRO&.2[*D)&
CmA;JRL-GuHZA:Y6A;UG>6.S(A[\=&"ph(7A@jEi3Tn#XbXt.3lYi%6NbS+NHK#.$2$b3jYR
!GZ@%,1nuWZ.ZL(-)-I+fC\al.s13KM2\3?>$^`Ij*=O^+>+N8PqqM$]Aq&b^&<;EJLLRgQH5
*Mb>Pe&[.7$4\Wp<VU$FZoUJ5Qg&mKajI@=S;:bFiY>[W8DDk-iQfU<'W9M"'Xi253hKFRf_
F#80]A^5iOBSXUZjX@:k:=#ip6=FjGo)'=RF'"h[Kh+5qj!M9tZghl,WU8Un#`60'#+(7uq\Q
)sfA;'<-h@^psZhDsf9n2[sj4&f*3W1@!lD=kU2iD4Nb)%'g\UdQT8(d=7T:Op!EqY-1CMe,
Og+F4NKtn_WlfAg07GDAr&"r)FB/[GC:;0>H1G)1#:\Ra6R#A8?Q),]AAGX\#W7l2m8%()r1@
AQfG_o3h4-:A=^fm>nr`aon[`r?QH$^DeKoDUCMK04:H\=qtc5+(2oSlR+T3[!iYZ+lparBT
.8T]A]AU<=dA@-e&m07)9-MVIi%\0RE[Uf<;2oGAIYA8gcRr7E.MCO-BEjG;gLMWd/rPeGlT^Q
S-_:;K:,0D39_r<'$#'k)E?V[br#=FZm!o;+N;b+6'Orq(L+*7AQLg1]A0T%@!$[8gA]AChq\J
EO2eL6o\_IIt7P\?=Z)FP,$V\/<gIMV_I]A.AhHe__&KMm16VA;>@LR-5g"fA\**dq/+?oed]A
pt'AO.1>&.RX-QdYLt+ZluTZj-(%['&!HGJ$4ZfOdcTQcjhIe9FI?PWlf*dN`t",fmKCs5sn
c_$XC7Zf,n98S)T5jQ\GdPs]AN5T)&Z'oR]A9#5EOALKe\AMQV[(>Z%$nQ1\XjjVRc-[OoFat"
3)Q^j<;h4@Hj1<@8t_*f&IDE5=mpM+5/mIr80_>6H[\PYn\BO(LJ6"k)#GroEKV[7*FKS+1X
XcTq0W^f:9bH>fh/q,P$Hd1*K$2->;"gF1EU\EW;`*"[[0>D4k]Ah^rtE54u11&[2$^ChB3i&
M!IFR9(`'JnYTOYMf`sD)<#\Upe[T4+L&@^f-gJQ$FVB^Hoh]AKoiV99`2Qr#_3W1@6`:5D^#
SiW,S!C-c]A/k;EadY<0..edM!oF[`G*$jEmBoYI:-EO9>]A5:ZRr-X#G*0VoU_pun,ZkKm<19
'Ucjpl;0H#sl*Jjf6DujAMtC8^KES@IF==;bMi=0#F*_K69f`;7G,fdD<rL.iLd+NMa?\mNf
5*=U$mpR2C;Ynh(C$uel;F5hSbO*h>d?XH):n8q#\/<6GDm^sA1@0R9>:57DSPXrMQpRBDX>
[1D\H0s8r]A0hI>):>X(KYT7o@jf%m$SnB3C;>^[ke[@\5]A6*3ZP[/>NR^Ekh>eZSTSgSN8:i
-C(p1<J)m0!k(A_,Z-Ds*>*E<SG%\/e5JW'\%@iCm6E\#%?-G:koV-tNqN[@N%Ut*g,?!bkc
T=EHu[H'7iKo%YpT*Y]A29sZli"9d'[*ZE<.(CZk:4V2+&0;3hAhX/PG!;LVBFH#plT_rQQPc
'DbT$"%Xun0\*\5-]AW0RN2An"II=kEt4+q__i<l&"5HPc/,>RT`^pHM!ggmXf/.^*7R8(W4Z
@j@?91<&-;3S_>UT#dVLJTke0qpW]AWjUQ\E0)t8cF;hk%%af@0oppH9g-bS5;%U=*_%Weo78
S8$T-.n.k0!t+M/[!:,`%GQ6@:/-a%Nk/NiflP8,gA4HhMJ6_Yn^Sql:&"QbdC%>>:dN(u)9
0F1cAhtb]AfSU68G5mDhm0)"ti75jjfa>4*OhV&UoCcLB-)=:3Jf1;1J+Vpj/h:?O9K7Gr;#0
+;d?<hli,X(FDDUt)c3o)!^<Cs!Grr$uN%-_T890V>2V!PFJrRWc*,`RoFLccKahmBjII;u=
,rhQ:4oVgQ&QYF&!UptGnBl4-S%lW1O9b@)Cb@KT(D1oq\iW5GK%D*`]A9kYpi27P3;BgoVR"
e>;YHp7:Rj&!KD6c5<!Wb+TtOBZF+;"`p.\j=bMMT^%aO1<hf=/Fa,6u7Z/Hj\:TUU'USopI
&!cmgcqRqcJ.=%N:!<q[j/;p2uh<_!Zt@Tg(sZ!!&.&4P9`9Re]A^l-EC5G`=:<A=X$%l5e4c
Y>?da[PAWhH.b.44Z4T!isCF'aa+-/T66@Hr*r>KpSgn\%7CNGX;(C[BB'93a5,dT[4/Gt8`
:7hG]ApYB[Tf"KCc^/^<p'Uf2r%pZ7JpP0OG9m\4UK\@\3Ds\3nKP%%@eDHW3M!*bNKBU=bpS
bFCGZ$C9"Z`F,$::]AfX%P8$s&_[M.I,*;!Grn9HY!0:QNI<U/(XGofhA<H@=P]AO<&7&u[k!S
EL4$Ygn5l'E1q_^@$%?T4.8)rS9>!2?K1;S+f9"\[s_eDJoSnpAco`iN0/Va,Op,Q[O@U2*E
Qbq&5L8l5cE)dsZffL-@h=^Zb9TpOklbg`MCbR7:oZ2G?XG`(7gb2fRp(]AoRH]A2i-Yb4-]A7=
'"-0RFEk(TEBHsD+2g`F@S;/TF%a^tNRRPZCGr[+!<+(45jmc.h<"ikWNn)4"t:]Aa=b+uI_u
!gCk)`#N@Q'0Dk]ATFK6+d9GD2g-*W#7[:&i,=mDr4P7Zd]A>F4bbTQiKsqSd<N`I@!g^'$@![
nEmm5,;sYj8dRr#E!EnUYNqC:ud7LJ*A>^9f/YY2hJ/4l*'i/H9`6%0k<G\bu/nj?Z9!HUb<
C"\q_s-AsLh#'.es[Y&.i.n^X`P-tehr4%Ht@*kZ'5G2)#MMf8ZKH3GC@srrWHPu@=9STC3(
i^45rta;;BI`=dCK!1+K9k5&Ku.IrCSMh<*'I%)`HON.-TImHe>m:N,;I)q[96UEm3G)j#Pj
G>G`&roo7ahC0F27a5r`os&KBZG8K[bdQ.G?#"e3q7\t3U79"?`n9cr5PWQZrj$69=4$<()b
s=[]Aem?]A4f5O`VugsEY]A/0_[B\'J!&hN9e\B),I<h<EQ$.D++D\dKhXSq<n]Alp(Uurn6\F`5
<%X.Pc!GTgW([VAHe>R"QLuu$q-NW8j4Qqk7I^Zj2Bd@P-`_r_ZYWt3BG%@A%?DA"Xm,Yd,R
)dc_<(;0*3Z$@6UNuc`2OnpPooO`>)LX*Rp,fiR%!#:+/^1b0<%?TW@og>bA@g8<jpE0]Aj]Ab
9Hq(*Y^\8SQlgT*=DFI_;j^Lg#ba)l,.)FjHmY:]A$fge(*&LPM/$cM1<amYd_Z:k&kNf/36a
s/d>j9:Tb#5>[uSIe,uu%#jXi+-N*4+atJ`>hChPI`ZITcF="YU433-`?Ag`J7&ju;gj7_$]A
?E^WH)H:CRu>HW59.R[<f<EfWm[Ra]Ad6;%Q>j'i%L$sSI#Sr8GY;ba!3t,E9V9eJ27pln7DO
[R!.c!ME>]AT3Mdc-XdXJd\k<!jPEEo$;<>Cn9B,UR2>L'<5%8HkNH1p@cAW*'k@>]A_l8LP/C
tm:pn_O.\<bAcaB$r`1O4lb[I@Th^g4;NST@iI=r<m&SUT=5Da"+'0r*J1n/+qM*LBp%:rRJ
=2[j/*@7UkB^XR0`+,<tM\VU#"jWp9(]AVjAEiKAApc`L*Jlj]AI1e0Z(+,-2=Ro1aj!4A]ARm=
)^'"JS+1Uhq`QRS;@1IE?"HZ1`SVYkD[2U-QDfWX^eIn/j$H#Pi:65(nH":S[-Do+K<.MAF6
Ce[-_J3IOi+-(2gSO'6:cn.4(t=%)OEd\3s5%cCL8-"SpHpE19NpX?B:=)0016R)W>\E$-7A
s2]A=OU(5_$0J?21q)0qK7((rL6""flj,$Hk*le)?WY)HDlr[@`V;CL=T@0?'b7c@q=XoO#;"
Grnhe"G>JI[#s6-$!n/m#q^t/8qjtN^Q)__'!]A#f2GO5Xpm#*m_mX"i2#f`-UsdX+RX3ojM*
VN<$9gG-5-k:UL.IkE9[`\n<Hl!VB7R*"77*pNOlG]AJDPuH1l^7^/]A&R>ktQArI)WIY@$9@g
WZSd\:ZRf`\/BRK4SXb>b8Y_KYoMcu-2%JK.et$OA)-\-3t/rd\rZ/ejlrVBWY3`EncXJPIO
jg<fQ1D[0V(\9MA-Z6415#tmS#"m_qIV(c]AjqE0gieA@?_#9^K)jrW@QE9#k\mtC"fohSK2n
"^,[0s0h:td3nKNuZo]AF_C/QZ!YW+]Aa:l)c#14pBWTYo'ab%cA3!?mFuJW.a^Ctafe!%[?k'
dPXh.=a/bMc6lcY;9Ck$c8aRJ"Dpu_:Jf`&ft[cTcrO-aB05QcL]A]A7?mY"l--O#slE<7O_Ch
pu,^:\Aa%Ftg^%lq7MAJ@gfXbI*$`k1AU^!.L/6..'X+&NJfYX.KM-oEL><l>P=B?'Aln+sN
[["Vck&i!!N3Nso'HVaZZT_n,[o(O]A$&NE/5F3@qO&(0&cA@=;([e3SjghUgEl[b1G+Nm./`
H3'CdX$=$W:mog9PuM2<)DWW#+N%+>3F4\3(=$*jiWeMGQ%5G._+e^ldroP"-C]A9D-M:]Asoo
AW+jd(j4F.MM([&)pZqC[`4JH453EW#7OGdJ?sT^\[i@B(=;&8n.b&i?YT*-N,+L@a&9kJjX
o'(cn'3PnUm6GG!JMu/cHYb#$qpp.lg<8K?X"X>S*WsCRTNncG`G:,o<_3sD($=[fGjl#XRj
q5f[E:#Zc1c*g6&qnA?_S1KhTSN26BbkJscFI$LaPA\[2B0.QqjP#ds[8;#F%oY_W8UR@T&C
i^VSc'K'6G2ADL39Dk;VRBN5+Xor!'GD80D]A744>pJO^A55Mh,\?L02aHeiWaC:'0Yje')Z%
S'*ML-NjokQR"+=/HHo_k?c)m&bTr;&:1HKjK.O[Vfho1o7S!NGI[_$SV]An@r2B[bK1b^hEQ
g,F,O*,"S;=[f@nQ[sJCdb>!WpV0N=@p9W%4S?eRo'JN0_,V4?0eU0Ju6PBFLXJ'*G\Xg2-<
f@F4E?.XCT!\'eY15m$q=.kij86ttqkJE"VM"4inQ'8(F_7<6Mh&6ch8E>7G:IFNGud`Zpri
c)iPG/BM</^Xe=@sg0fWIqXi@Z4\f+^:<`p5/,P3i\7Q]AcqK]A=ii@JhD^oS9(;=s\AC&M;cU
hR6D,PM+so,Mb[7`5msBf4Z]A>#FmX*.&t'"ql!8:l-QHZ=23`'DhBTlm,nhZbZ5tTnWlW&a<
i`=@.<"(n:\+XY`Z-\\Lej$**B=FkOUD4jH[`F1B>h-@B.1Z.r8')=i_^n$@9C\F6?<9]AK\c
a>kcc=QBdMBkI0GkMdCup<cTiNV80kH'mo2eJe)q;`,bI8b%5=L#EUfNfPQU._qNAfC!lh's
*a6Qn%iZW`e!;5dao>(p;t`tTAGP^8@nNb21>b&SbdV'fD;K\"&1r'4Ff`(!]Anj9[&%RHb0$
)i\BFO10AO=aXaAU1PH=bD.*A`]A[Sh8c<aTID]Aa8CG!%.HL?h"j:9F6*Tn?GJ^KSe-8-@9IK
Pl@r5(R5TSda+SPni)aS`,K8[.6UOLdd[N>G@IaG-F`_\\N,8edFN?t6YXTYG!@mLPPb:F2_
m`LN%)/DEtTE&8=(28Wgl:'4[t&-H[qV:S'Xm?)'(6N4(9@`KVLlo<;7S#$CX9d0u<Hj^r.6
\bT;8^8)ME.38ZlkQdI[!'d+FmP69RBL7/SKSuud-%&nFoGq%-0TN0M)[m[X8*)ihOVOk;jo
@iROln^_=]A\)<M'eQj)0OXp.c,*>6k!+S3+i)91GYQ$.`XZ^qXX+)9T&#p3^b-eP)Nhm-p%@
<*%=?4\jl^-!0<#OFI^0CZc*##L;GpF\+e^j4efnr^HM59Rh`d'K/8uV1Xq7mRD<j8V7`26T
4/LY"G$(\j>dWO7U;t9cjD2HkWM\W/-g`A['Qa*b+/>Hp'#+U.P1',g#L$IOrp1/*[$NoT6l
S)@4FNfuCT;4]A?*B22f636SSb&g3lmO$k$U?4*4MC#Lg;i,Rmdo*d=%tg^jC[$Ir='78!SoV
(?g830C7ke,modPEookJ4,Dk&3qio$BY3F?6L\SV[%5[)%NcYT$c4Xig5"g3nE/[.fQLm%0/
18qa>T<:>[FbgiV,+6]AkLC^4,caXA66**eVl"J25_G-R^,'YU%G`I1!nkHg3lV4Sqt@2i"36
SMG4cPO1t7E=36V$3/7U:j-=b?G/hm-t2gX(<\]A'j3?Q8)&lB[OnE9@/pds-rm**+Cl\hpoj
qPMEDFaQf,MX[?=0UAWL@C/6qFp+cA-UIqZY99Q@,c!?MqW$#R[9(guJO*fJ4Dk>tr:1bT,g
fd\<7q;GcO%t5mcnVsGS_+J0_)EgCb6'KpqZ,iAs6>o3puue=WkZ1^DLo?32kKYi_]A->5[DC
3Yi8lZ.rW*jYF7Q3^O"o4gCJaiYZ315_J0IFbeA/pi^p..>b!d,)Ii<FLn@F;(a;ugcdD2(k
c^GIK!D6_0h]Amga1TiZ-]AFT*O27'ra4[7pNH!)KSd+89>Q>s#WfkBhDn?W>mc#n`Y$SR@rV(
\`h?/3@D]As-?=:/:+YXmSQDBqjYC&=+lK]A?9QcUh_Oq>[ZmV<hOYq='^l*D*aS?',QKj%$:W
+I^%C-dld2KnFV5bI3P?>DH&0296JfA*Qf`;dU$oEjeqIEn`>UnaO!nG58$aC>abcs(dtaT"
ZKqhl"mrQ%4:D[B;I0"2s!+Z`h''!JaMY]Agr+R"q8H_[59%mUICS2%Eju4IE!r$;Gfd!Gq4V
"icc='.a8\356gDtmDG#B3]AX.(,)!/2-0]Aic`hdWFoCc>=!R?N9p&2lCKt?XGe").\'p]A(KI
QAXrODn`*-tVpH2mHRX%D!OHg_#Z]A/RA'HKKJR/$HW)HAaFN9k>'o^Z,&"#n0dl2PLi7cTH=
*Q1nF]A2iSF>al>f`/*V%j#0,:jtPq=V$J`M4WQ8U,!Kh2(AL']AasXl\is5^1!o)B0aJ0m\i`
?9c=!E[bs]A&7[f&T5iGnLl'$GIF8[C@Mck/e6qffLTHo,^;c,#YD;Loh/Q`;DZV[LG64AD5_
.Y'pl4*.O6+2$Pas*W-oiP^^gJmWR/Z@pj*?[0fZO'Z+KJ]A6cU*o?WVuD893,b<BA$o_C1%5
-.$'@oD]A]AB,%]AG3CDo`)#l=dU%l^Fj=.g"f6=g7aah;-F**aaJGa(-]AVP&PDm<TAgge1oSZF
,20BkUG_3e!FS#cf+r0<8oW6Lh9C)+Ih&*BYJ*>Tt?K1o/>6:(-)-/Em_%a2uK#9".%@k4lQ
&>gEH^Z+sG0qZNFP%B\es"8)2"#Sca/_Pe#J!f]AO@UT:>"V;,:NkdXt?qXWt;M3mCh]AV$jN3
SDn$t.K%du\HF9fp"Rp*g%19MC5Y:>@:i)66*@P&Qjk(XqQO:X#ITXsBlqQNM;9]A<:*X!H[;
@C>(ui%WkD<:=Ice&Bg$ZAHE+&6RZFR;XIs2UP-=?c:ZB3b/qqc5!hAer"!U[RjnRkKC-1@@
qS1)*f8A?0oZ7b6[TMWegbYdC]A13p'DTcI`4c;_SAK,D>6_Qm2k>8VB:Oh3OAZX&dbq>'jN]A
R/@K+)9K\grrH@BK=R?l@8%!IPoBj@od&o.>40R-#40*ZNnLZqCG1DkJW95^gt^7,'j$=&8h
GY]Ah$ob1\-6M,kW+1\)48<7L#qU<71hnYg>t6Wg%Zk<Tc?G3d`#ZWM4XBkIm=En]AmiD<tZAg
5^<N#'ML`0Sm2r=L'TP]A8Z@jXbj[Ghp6f8FC%l643O+<Y(F<)-4h7R)=&'Mm"PgH:AE:sh-k
OBD&KA$m&SC]Ac^/[tYqL9q4Z;YJ7^.lV#YZ<?#mq_5gVOJV3d]AsD&!5m5b@)>h'dW@Z2.TGj
-Ff6.Fos$GX&iWb?Tii^LL_.96.Q=$J(PA@W\APJPa>u*qeX%LX8:@_G*WJ+f'?GE"]AZ"F"]A
&qQQ3s>R`C<Nd\(J1;NLSta&JQp1`6`W!B@=^X94#RPM;UGT&rpe"]A>hU8a^3*G7J2lDXb/k
89BD"[^cIBFtAb5Bk#u*\_p[qb)-R\L;BfFocS,?k-9D^cYh!\(Kpa#j[H!8uUkRdCCZ.;%A
:9X/j4b>ZJTmTsS"He1lG#eFcXl\$_V&ASDHmlCoNYYQS$Ct\(*j`o2./,Zqq,!/,eiOZf*Y
jph7$d*_=]A(csWOW5S]AtJFJ[Vba3cc!3JKASIm%5;8i7C3Qqr2.oN^cmU*b37Mq_XAk#B5t;
Hj`aFa@g1q+$1N.iDh<G30*B*E2a9[gEf0oD$(J(=5tUB*h6)9Q?,Z=`K#3Qi?-:uBC=gpml
=B8=ME6]A6oIW0':Ch#E"PlKQ%4Q5q7rB&8V4-gKDq:ha-\?KRiS8qHGK*Sd4#i&*hT#3DehW
6Y+Q)Hp4RE^La3B8.<@r0)`I3\<bn9RB[nti<C9Q-(nrK2r9H8uY;Rlg5\I44middQn`,j]AE
&7UDee:1/<"n6h1$R9IX#*V4m25<.Gj2SO]A)Ea)c0$YiDCMHqRPh!__e'tns,1tmd".?t;ji
>fJC;F"Q>!/3qk*RPjORuLRj[(7+[g_YZI7`J7WcMR!gW0/F*[rYK.LJ%Y<@hsT!?=IFmhn*
VhL=Q@Gm_;lLL^TWO0;'?G".uIj*'I<\BQ:>UO"D3Qj5U`+-Yr(Pml'@9F=DN`Te86E`m#p'
<u*>$[C3fK\Mp(#19Pe4SPp&/dM9mSW&jse)m`5`02t+f*]Aj;'hcDH8R,djf79G"9fLF4pP9
T[YLHR)(=MLB;51;j11&jQVa<Q"F!NDqg2Z&p,^H,&pOK'kcdNk9PdX7t\B8trKm!;N-q]AA>
-D/4?oAPn7lMQJK7%E&#EQt4Q<GP1UlrIK7CRA]A73o(7LlH3>Z"T1(bNlN[UobYRX(Z3O7/a
&rVoAMMk$mapMWnl4q!qm9qYgTau%e?s_emX#lRPXQnmM\C>LWa%heJ%`Q<Q&2B6Kphj>C@>
=!%`I),K7Kh5UUV_hc"!W<U*ha;LhLp]A=,Cpe`kfr"&r2P8SR/'hZtChHO2=^qTNJSW^pQ,#
)+ISY9]A*G8/VA1^JsSmrmE`]AVT>ddgQ"9kQeYPsXE2:tL0]A5LVS7s9FmlLH7Q?;Dk"lbtYcD
[l;l=\1:*-q<&.n`s\0(U<&heZF8)dK2Y0=!ETG^-RF>_o+D1]A(U;@ncT1CbQK17OrH5TT=*
C%6ph;$\>bn<h#-9V8'l+cJ3=J!OQa78YfLnaLp0*6=5B?&e,J5UL\M#l_g,emVM^HbD/fM%
K/4dUuJje:f"p<0W*1Qi8)<+GGL9)?K2PMt^.4A52H/2S3is9*50LgSo=Tmot56&BC@+>)9%
+BH`5brm["r!VnLfK=@ahT7&#M223bk_t1@Gq^WF(N!QuDlGK&ZEW2\e8*Hanjm%C_;ic90B
Y1.cBZqXVl8QO&o`c#%hi=5K2c+3jIaYTQq'o+[Sp8VpWe2+75Hn5@+WN-P!:>lge3aWf/F`
,C^t@_i!akm/]ACBBUE"!W#hIt\>Sc\6@2j?&MX!-LG^PZ$\riIV=@J:Irr=lCR15.MsSb\@l
;0CapKVR"@HU*d*\/;7BHI.ekSJKmK_.Tc-Y[E=0Vo:5s>kbSVFT)i/'+7Vip<"JVI*mtBc0
Y*OV`#V"!7ig")9uT.E(bpf(f6^HY5MYOZ\a;>!5'qfIi-ejpU%e$4opOM!H%QRl9Sfd4Qnk
l^1U0e#gA?:B\;/B]A'G%6&A_i7+[f816iD-idZJ,.@?Ko3D,_2;JbUiWb:/5Y$E8/dds-9kk
jhr+&#Mc.-Eb]AnkWriqTpj=X9L(edeFWSe!"cX939hIXW(_mDHnb9e7ZnTbE0?aqhKCtOl4s
sbA[2'475+$g$[a1C2;mEVd_YqEhFN`Pd(d[_D8J^n\EgN0h-&=e5q/D^5j\r&D;TO"rh`iX
oPU<%9$_p]A-n[j@94O+eRfJ0"=?gL-8DLMBlQ:BXgt[5Iepm"Rf3gf(_V+D`pW."XXC7.+gU
@7Y14#jVaDmlQme/,/fcOY(MNP`1;)2^5Qp6)NPVY3`$Cpk.+IUH1]ALB;.AgET=^qgP>o$!:
3I3-JnE*a&c<2onATL%le$[0f(LDm;;]A3sLqOe?1Cd$&Z0mT?4i*rRIN>P@V(gb:Fm4QCWPr
l*t*F([meiCL]Af:qVJVhKDWaJT5C]AC7QRcek&FZnT,\H9"$^<\N=^;m"g/5DY,TP_p]AQ"drt
k6G8a^WLXKC-*K#Bh]A$+4KW`D/3T25hEmY7oWO-TPngI]AB$P3,s.TAh8,=c#Da?eH>:O:#E1
2#Ftn1KQIHr[8Xo'T>.$"jma#C+4Itq<%P+$ZW/u`3G)T0=c#jcFfT*n5)=U[&eoP65V&ui6
Wi_9tC/3aKDq.+32gLNP(4nW8ur2OJott86OE3aAKptlBQGSB$1ouEN/e/UrjhpjE'S:\\iX
7)LieL+s[6*dH$M+@+ZBnOt";:c#X<@!ZCB,r=0]AWfb$s:r!3:26A6f6AVtCr$`N7_TfAhJZ
f=<U\Gdud%BUAX;0m`hC!.]AY>o%\^1t6edB\Qn;9,&,Q'&+po6MLa[h_7*AFeZN24nUZ-j3-
8orRc;bE]A!M'pu?Pjer.)h%RBRn4!g11>Q2hLH<$[5l/%J9;8+]Al-</^;GVThRO9=5K)?&;6
;PZ`qA+`ea/]A]At;g5LM:U?ejQGV?GHp60N]Ai=f9^Jm?2#s2a:\h@_%GLYlbQ,C(Gm"*'NU>=
4C*]Asd8Q]AIS,cO`RGrC[j.[oV!;H'Y#'aVCcU&!rsFTrpl^;ICt0?kX5(0J;eZoH$Zgb(V1%
V%)46W"Db_6mIPQI8K<gQD@`kDpmkiU-&98,[P0:[,H:XoZ*g]A`ER^E\IXMl:5knK,+pM/;^
?KO.2:UXU(,?e"o?bVg?-NubSL'Pr<]A`W@Un07\.j]Ae!=t3YV-\*rnll1T5ME.U/?%o'%MQE
nN7pD$]A-R9ILO]AP7U?)!5m6CDoE\,SIo3nU?;Ln##AjY@?F',$_sSGZjNf5tlt=\Y5T'OZ0+
;(`FDB%1j0Vm7TZ>PM6[:L7@P&^t)X3nOIn:Yed^9R"-s2_9iT29-U.SP.e2AS]AXET-*@bcJ
\i+1p,&ud[%]Ako4GD86'lc=H.u/Q&s(6;X6b/mN*tPtR;[[tC+9)l_%9^&`k;34f7&mm+RsU
"f&j^&h;p&sIK(7AmJKa01ggM/^EH86(S"nW'$<D%D[P1l&8QE;c[hoSPb"=]AVUgQg=sdN)0
n<^.66j-ZY-G7\gG6oNl9qMlk(T#Pifi^$`-'*>c:ndLeL-L2q0PTl]AkJ,D:9ic!kg6SQl@I
H):=ZM0eQ^1[8'.nuIUt-3^Ut<IUHC"B;a.`9YX$rh<bLfpKFYAQbjt1N;*@r3Z0%>6+0Y*D
dCZg^"m':!or-\.`f/X8q^52!-#p`H\8#G?^lZDqNT@Pg$Yrks1]AieY#E]A:bnu@jCXMG1X-7
6c"iVpoE%03cA*d;TsY3Oe5fLV1X]A?WWaSA<J8@#;plVE0-Egho(U!</-mmPb:.BjWu[$^"'
.W[Tr(fn6%@*48?[RFg1l?A/8+C+BmDGh66tM$J1l8osIk@e`U,P3+XI+i6CH;-_mEM;9d;5
B>GA1!]A2iArX3]AL7^LR"BpF[jaoI.7nDI!o]A6'OG4#uFPaPX;Zij%pAO-lV&i?Q@.2__EA.T
HH?]An2@nO2dOl@-"gE)V[iC3?Mo1Be6dde3R.iH?.0"lqX,!&.1@h5s8e,S\5d(IChAZd_1W
[Rqq.hY"P+bT2?/-JWKR.?(Nh]ASe$K^:fYSfJ>&RAHqFs9;p&Em\\WNC^\6aR*rgD4#Q1U!J
k#jXVKl%3ddF:"tG[pE^;u[;_pY<A0^@&hOWo]A6gsb%bsm0((sXQR]A.uHnC1j3J@om9e]AE=1
GN%2'A<,aIHhOaN/R=,cCS_Ef[GP[RT^1aAA]Aub0!pWgQI?X>R=ALUcnZk!MTo\Eg'X13d!q
t6*J5A3_^*gE4PJ-AG[15J3pVd"=3QpJ\P;pq#GFZf<ne;c^D")@!6=_pkGY_tBoQrfoJj(k
m;)Qit(WBE/>_=#0F`V9'%U7fV5SU`d@8]AUO*JUSU8k\aPN3_\]A*;\aYtILZ@>lDT%SU(`p-
2Bd\-[MP4(CNEp"&!!5hh=l^cK0j5,5.cAOPB#s$/sSqR@-DdUkK(6g:#oSGdeA;5oI,fln)
6D!BKRD\K;4a5%2?^8fX\m+q*46F,GagP<9cg?bLfl(Ta'FKSF#pu`-uI6#/j9A;EfFOXdQ[
<$K+$_?#/qT(+j,*Qr>P0an=\Mmnl;BK;lkdBLu#&RUn-j/60?oO,IE;CuUpH_+mR4%d4pB2
aV;=Ul3J)Rf1bmO+Nl!Kehb9gMJO>*)"_pE`K34351j`GA6f>.f/tZMGY1bV=]A-Y8U=0$.Y!
#@MY+=De,<AAA6J37K_*,Mn>AW%.,-W?[65`<)&-E0k_)(<r9m%7#"H:WgT>3+OBk.n2pQG"
if,M4K[eb\&MkQ&?![sW@P\SHrkgNBBX'Kb$)T@?Yo@#S6WAYpCqFNSJG,/CI(.&`MQ<1WJB
Ugs/ttkm-N4`1Zb?'Z+(DUJ`HEnNP2OjSju!eW%MKW0`Up::99h/f6n(WjYJe3Q[rG/IF;lF
<#(;U]ALB%?O]AD5/#2=1*D)PEab'9cffVfnZMG'M=phJt'pIKaiNr?"aH9*8X,3s'4S4\e<:?
5o%0Tb']AQjps`aOl6/tjU@Mrj]A:No<S+Q9WS4\1QH\dfV+%OFg9AoY=$@(S#M8Y7_$9Mm,Oq
[C7Im!meXPC21tO@FS3=24Hi-[B/6LBKBD'X[3#VGH<U_1)$Ie>4]AgIQSBi0WN2RA"%s7XWp
iFWMs0sKIXfIkJG5OE^7eIWG=U$BlM*eo1-H="-!YFju?oKV*i_7"[=J]A9Pdgi=C#2QJBOkt
D9A?Ua)pYCXu-r)^Qi9_IQ7#Ct-PZcmWh[.PFj#::fM7%\]A@T3JYTQ[H%.74n\&H%9FWojE^
QnrmN0&oA$fi?EC;@jm>/Yp;'NE")_t'*j-,=H8IMRW4i4[,K1G%Nq_-V4<5nh[k>`T4[DEF
P3RA=*,QR?IX),o$/N[5b:e]A+,D.V:NPhDm03bj]Aa`0?i0K-KLOSoQm[m#!h)16T_),9aQ.=
7)>J5<#W"bX>$V=@mp]AC^E-%4/?+l-+IUP&)ZgSa9lejP&]A7.G:##1bB>rLA^1=?U8h_:3L7
a3?-/F^t(^a3?.+(J&ks`uaD`K((\>1K!9j!%jADh4%J3^!4O%W-,GOH#Ds<"QRmJ5Gn,Js"
#1Kp;"(u\hJSMR(I^#2FJ9(EK-Pb^Vn>7(J2j$mf"aH./bO?.6K>9>B)7%^LW#8qU?I2p6eg
Bo1Wp(`:GBO;ZS55gC3ZWgUC/)M'Xi\5/DY6p4G?aR'pG@`:7MVrJ_WB?\NSR8G<6V]A6gkq<
4)>TOJQL'hqEssRs'(N(UE*>XdlY(]AZe0>E<$4PeGr[%Gp:?=UFIB/.7P=<YGc;CjYGEoS.5
<T'SJeI4a\'2c.'i;3)M.9E-893]APM$N#q85@ZDY/O%[&")-U(kX`RSI]AA0_-C0"2lT[)8@3
,hp6bYQ]A0(s.!?]ADqSfDp.<5.oU:nOU36>a/H$D^(GCX8JWGZ3icP%aHbW$g2!suob%Y/diQ
=mk$X/>A5Ze4d$>>/lgfF%,`E0d.s85TGD]AkRf'^.V%'Ie3::JgRO2.`[p:7bmb.HkM?#&!)
gAkU+/:-3io9u+^QGWBO0;+h&!DqDaF1]AZ_6Mj_mU,iJKW^m+Ur0IORKF#iJ&=dU(fr,'bWi
ZntpU\30*8B,:fVb`2VOmA-QH"CA_kWSYV683pHp+?/jIY)@h*l\q`"H>+=q&Aakh/mEbRlG
b@%31p97+UH'%=^T0o')Xgceh7pRnO@$0X3C94R[RmIfT~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[ja6efPjeY@4U,%^7<XQ!$SD(clkP6@CR1'LahE`PmBXDO=qFPA*@:S<PDTp7I2t1ZloS_NF-
#28chDh53WHqc_`Z.+fBZUHce%`8?.X3H?El9GjV$MR!.`\<gt2f2p7]A;%DfW(7T1O7Rmf5U
oh88sBeYo#"WT^jmZ`hs2>?;md^KYTsEEA\'4l>9mD>Q=H*`4"(c;qS8D`N*!hno;b!+4ZK=
+t>_aP2KKiKj#^TU$Mn#3SNY^<,i?'m8!SX`]AH=c/-e\I[Y^/^#,E*IacR*>YTi"4qG?`Hh-
?&!]AB6h+h$r1&JY#d+"DCjG(jp4n%n+6s8=V[mL60Z<YPB!-f7-06p^hDmALcWs2X$p+]A3=,
C7,%sm!i>:j_!Cs*3l@">X3EH!!)%C@[BEPl^qD1^2JIR*>nV!j"<-\*dqa,DJ))SBq7I_b3
`M".)]AchAY9iP&D%[R.k%pZpUap=`K'UM6nie?<mYs2Kc4k4"qF@l%:=1u/t_jp^)jd`+#B%
I>Bd&mpAJ_upA;@bd7:O:*EQ$KlSQW+:\JE`rMGhX_'aL,o9W:__h($Dc`?trf6?\;-?U_"F
&@p1^&Gt3kHj@gl%Od0>[=o[(G?H\4mQCI4lCF`s7&p@]Addf&=-9Cdbk1DXS'fuQ3d@^\bLY
cZ6\I2pR,Rct)k&1GG2D()XW+[N5hJ;=Z&`iAKDB\$l8_8BNCE[V10Y_BE;@j<3?qqH)Z<GI
BOg2q05J&%W5lVX.VJId*E7DuUW=aNgL@_LTE"u&:9d\se(9ShYjlu1*FL<"F!%p!b6Aq4F1
#(_Qb@g^de%fVG?.ON:O(jkJJUg0P`"b\+C9Y'd_.)8or;2b'2+[lg&t0RIcEel$lJ2ae&oa
JCd@u&q3(=$G7H,cZ\"p$`H`0QA0)Hp:KN^u5dYPAI?X9@"lsT/?^(X6]AVi[<kOT<@i6#moU
M4)01Gfe"UO.#_N-`&$>b)_qg[>i39D-Q^!8ooo'Ja)'(&t.j`Gp*`0d#.i#JX4?JDp%BD_D
En_7ItCiAhiKqtD(IY$8hJk!J<a]A7#&h3:6<uYfff=lJ3SO:\lfB*hEA)c)oMP1@nhS91=2G
^]AN0VDQ*(_df"'q]APJ>`8@r=80p;W[S/>%G\H,Z'FQ(\hjiSOSAi?^S>q3F8ARthKTUtWoQZ
DXB5I`s3_1Xo<qjmgNJ0DbS2>UrCK$2:[IXTP<q=G6RKVVuHK"@6.*3erhVOJM_V;t&3gg_"
rku,q/gMO4YKaK!*p%"d(4pqXH&r$Ym^g.V3n5&Me*QuOr5jbs7FslHo[NQo>dhb?Q_3f6'D
l`jb4X13MS*W]An*5F.u+#hX,N'*<q;p^,SdoUrZdSE<Xb.ef8R-Mt\D1mm261ZYe,Mf5WgF,
R]A+%KgG_qaPEBTulPdI#6f#X9P.Y&E<rI!bRb?WHR_O"XWBZ5jekZoJb06eB#_fG`6"d,)d3
]A$onIA\I2-1SlMNO^?LQ`D]A"a^<DE32C3Q\?fUNn$t2?so?;WO<:'P^rm9Nfl)I8>Bs66'9&
7-4i1EV1p+&^gcgf>)5kD[CXu9VE\#a6tg2;uS!<4<@M*ROE3pbmf`s_8O)$7Q8L(A<H5L2n
%cFVdg<mRc"gmMUf2kV3hiDZ4aB@Ygo\l+N6n/qFnc76M`gQZYuSZR!hKiZ#5N/%<BU;V8A.
clO.g!lS3n6PRq3jn.U6q*n>.BiI5$_^,q^WT3&#kCTg),CVb*"<_MW6/n<1]A>[*db-sl9`O
cPG`Zf"L#HXE_^t8Pb1//'V`Xo^P%;7QZ0nN?<9Vbu=RW?Dn,kjY+mJE`AgBV*+(mJ<2qCLA
IU;B]A`+<lF=nj<R<Grd[qpI/;*T`;mA67+\5*u=X1q7.uMjPn\Qml=#5L!Zi+_sP/Z)$n-\*
,PE;"_oa=r<NE,Zp&N;iAh=44ZK*HM4)T!@)@AU!7J=2T)HX,+@AhZuMnbZ=kG$;GscY6n%9
rWF7pg65q^XUbj^uIV3uB<99j;PR_HOUt9^64RjJS@pLlAML>#17/-0n]AsD-Jas.5a'%!Iu&
IRaJ^H$_/]A22'RXH-EohlP^\-9"?iD3Pg7cgTW`#ObuT*mWjt6u*kPd4c-c-Lb0L;)06lfZ'
kpbub7/%E)>27tK!ojTk/[?C.hi%MA74!%Y?fS<b7@=(*ItC+YKbM7fS>AjrOG?J>m7\JrVB
bme0:GI8>OK>Nt#$Pm*>5l5*Q\oX#;`p"eS#FmE:eae4&nn2b4pRI!t@-*+o;.g:&qde8u7K
caJ3>)<o.=4UV&VV6n?R5()A\Fsa8sPOA.FAr@<a*]Aq%C>NMA'/>=pm.0>/GI%=Ra=`/6[Lr
Iok9qVSSpk.;bBAQ3$\=^%Q,lfm!pJu[eiN8LkWAB>a:;J]Ao`3Oo4jq'EP+@0bf?XXjd'_n2
9kPQ8t0n9`t5]AdnW]APJM=SH5.GEO[mdCN6^.>X#^:t.V(VeU/s2JiYQ"\rkafZ5nO5"N:COJ
(I]Apa&AB:md(Bj[P>Wp-LGTXP8<']A1(%FK;\Dojq,cdK_sCr.=Nqqim^SRnR-t@\[7>L=`Vo
%'R^f:KKl0YV2/'D+#`6Cc&M5P"1bZU,!g#![1u"5BZ$YS(c]A"GMUgO4f?GEc`$2HlklU6f*
7gkqu_=2="u.PBSZ->$m;V?#[>FQ*uP\b*&Ni&AuYW*'m;bc3+Ppl,d&:W;YYgZeu_R_!?2=
\OW(MG,=2:UcI=!c4=uK]Ah/qer'Tk:+MQ39GmI,n,6.n@>Pi9Or@cGf`=S*Q<PN(e/nq\,T9
aN$mJ%!tD)PILl?QaWbrnc(U23PIJ0#UXF!gPHSFr*!h+2p#]Abd&#282Gr_]ALIkZb&K3mq98
iFH@htFOC`2EgFO#D]A(%Xf75Y5>i8UKQ0Au*HQ?AV/h\Jqa96CqBVghjm9eDq0Tg/h5mj\AD
E&$K;BYEH'+1fh?/q+N/_TP<p]AfJfS9dFs<-^LW+)mDLMKUR&'7SIord]AF&-.(fY-pcHO^B5
%?GI7+P<DIN,l!-kC-`LRBY`O2=nLD;OaOF6tP16n0$"aI-7fE1aO'C8h6mP]A-_LCd`dMkXt
#:2E%U#A!]Ag2[:/$>6jt$Z*e$AB*CKO!EfM:`K_a$:6_)[pH75=Fd9]A5Ok2aVQjJ4@W,lUCB
\fD2X()dNp!!6,G]A[tI7*"4OQ7@#tSN%l%47$EM(Pd.C!]Au4)+j6P3B*H`)_#qP*nE]A^lY@%
#b/jZ4\G>Th,nV[kapNqLQfLElo&OuD1)LLUZ/@oM@n6a2ZE"3IdK'k"kkC.T>JLfn(_XSQ8
Y!T4;XJ+,d?L1UFPmc!5WJ;!a_pNiVE\LL-*2<7kQIFna;[!%(;Xa0j9'T\e6b<oP2EPj0$a
$.).tWO:Dq_?fA3!qG`LhD`j:"Oice[=*K)o#8N.rDH)M<2+R?KoDF"WgE7%s*67,O!87:bl
V1H`l<W*3L)6cR34eD95cNE?b9(cB<D`.^5C8)*V1Z'B5d5N@J!h'G:N8"YS6^13(1/KpPgY
6i?V2#5($WZ%pNK1&_GV%r5A&(YEf6J=MJj<j/d:U81:1G_[TQB:sW2YQdl&^S5Kbd:I%.>;
H$+A:kPCYR"UNkUW0EHqip;&\E)U<.tK$iL1)?/Ob)+XMN*`FGgh$G6X?VZGCQH<mY\``ZE?
#(<0c7P3EKoOfR>*qHVihY?Y!d4`QVZ,-eI97)MB<8d<Mq(L.,"O$m11DMrlLQRO&.2[*D)&
CmA;JRL-GuHZA:Y6A;UG>6.S(A[\=&"ph(7A@jEi3Tn#XbXt.3lYi%6NbS+NHK#.$2$b3jYR
!GZ@%,1nuWZ.ZL(-)-I+fC\al.s13KM2\3?>$^`Ij*=O^+>+N8PqqM$]Aq&b^&<;EJLLRgQH5
*Mb>Pe&[.7$4\Wp<VU$FZoUJ5Qg&mKajI@=S;:bFiY>[W8DDk-iQfU<'W9M"'Xi253hKFRf_
F#80]A^5iOBSXUZjX@:k:=#ip6=FjGo)'=RF'"h[Kh+5qj!M9tZghl,WU8Un#`60'#+(7uq\Q
)sfA;'<-h@^psZhDsf9n2[sj4&f*3W1@!lD=kU2iD4Nb)%'g\UdQT8(d=7T:Op!EqY-1CMe,
Og+F4NKtn_WlfAg07GDAr&"r)FB/[GC:;0>H1G)1#:\Ra6R#A8?Q),]AAGX\#W7l2m8%()r1@
AQfG_o3h4-:A=^fm>nr`aon[`r?QH$^DeKoDUCMK04:H\=qtc5+(2oSlR+T3[!iYZ+lparBT
.8T]A]AU<=dA@-e&m07)9-MVIi%\0RE[Uf<;2oGAIYA8gcRr7E.MCO-BEjG;gLMWd/rPeGlT^Q
S-_:;K:,0D39_r<'$#'k)E?V[br#=FZm!o;+N;b+6'Orq(L+*7AQLg1]A0T%@!$[8gA]AChq\J
EO2eL6o\_IIt7P\?=Z)FP,$V\/<gIMV_I]A.AhHe__&KMm16VA;>@LR-5g"fA\**dq/+?oed]A
pt'AO.1>&.RX-QdYLt+ZluTZj-(%['&!HGJ$4ZfOdcTQcjhIe9FI?PWlf*dN`t",fmKCs5sn
c_$XC7Zf,n98S)T5jQ\GdPs]AN5T)&Z'oR]A9#5EOALKe\AMQV[(>Z%$nQ1\XjjVRc-[OoFat"
3)Q^j<;h4@Hj1<@8t_*f&IDE5=mpM+5/mIr80_>6H[\PYn\BO(LJ6"k)#GroEKV[7*FKS+1X
XcTq0W^f:9bH>fh/q,P$Hd1*K$2->;"gF1EU\EW;`*"[[0>D4k]Ah^rtE54u11&[2$^ChB3i&
M!IFR9(`'JnYTOYMf`sD)<#\Upe[T4+L&@^f-gJQ$FVB^Hoh]AKoiV99`2Qr#_3W1@6`:5D^#
SiW,S!C-c]A/k;EadY<0..edM!oF[`G*$jEmBoYI:-EO9>]A5:ZRr-X#G*0VoU_pun,ZkKm<19
'Ucjpl;0H#sl*Jjf6DujAMtC8^KES@IF==;bMi=0#F*_K69f`;7G,fdD<rL.iLd+NMa?\mNf
5*=U$mpR2C;Ynh(C$uel;F5hSbO*h>d?XH):n8q#\/<6GDm^sA1@0R9>:57DSPXrMQpRBDX>
[1D\H0s8r]A0hI>):>X(KYT7o@jf%m$SnB3C;>^[ke[@\5]A6*3ZP[/>NR^Ekh>eZSTSgSN8:i
-C(p1<J)m0!k(A_,Z-Ds*>*E<SG%\/e5JW'\%@iCm6E\#%?-G:koV-tNqN[@N%Ut*g,?!bkc
T=EHu[H'7iKo%YpT*Y]A29sZli"9d'[*ZE<.(CZk:4V2+&0;3hAhX/PG!;LVBFH#plT_rQQPc
'DbT$"%Xun0\*\5-]AW0RN2An"II=kEt4+q__i<l&"5HPc/,>RT`^pHM!ggmXf/.^*7R8(W4Z
@j@?91<&-;3S_>UT#dVLJTke0qpW]AWjUQ\E0)t8cF;hk%%af@0oppH9g-bS5;%U=*_%Weo78
S8$T-.n.k0!t+M/[!:,`%GQ6@:/-a%Nk/NiflP8,gA4HhMJ6_Yn^Sql:&"QbdC%>>:dN(u)9
0F1cAhtb]AfSU68G5mDhm0)"ti75jjfa>4*OhV&UoCcLB-)=:3Jf1;1J+Vpj/h:?O9K7Gr;#0
+;d?<hli,X(FDDUt)c3o)!^<Cs!Grr$uN%-_T890V>2V!PFJrRWc*,`RoFLccKahmBjII;u=
,rhQ:4oVgQ&QYF&!UptGnBl4-S%lW1O9b@)Cb@KT(D1oq\iW5GK%D*`]A9kYpi27P3;BgoVR"
e>;YHp7:Rj&!KD6c5<!Wb+TtOBZF+;"`p.\j=bMMT^%aO1<hf=/Fa,6u7Z/Hj\:TUU'USopI
&!cmgcqRqcJ.=%N:!<q[j/;p2uh<_!Zt@Tg(sZ!!&.&4P9`9Re]A^l-EC5G`=:<A=X$%l5e4c
Y>?da[PAWhH.b.44Z4T!isCF'aa+-/T66@Hr*r>KpSgn\%7CNGX;(C[BB'93a5,dT[4/Gt8`
:7hG]ApYB[Tf"KCc^/^<p'Uf2r%pZ7JpP0OG9m\4UK\@\3Ds\3nKP%%@eDHW3M!*bNKBU=bpS
bFCGZ$C9"Z`F,$::]AfX%P8$s&_[M.I,*;!Grn9HY!0:QNI<U/(XGofhA<H@=P]AO<&7&u[k!S
EL4$Ygn5l'E1q_^@$%?T4.8)rS9>!2?K1;S+f9"\[s_eDJoSnpAco`iN0/Va,Op,Q[O@U2*E
Qbq&5L8l5cE)dsZffL-@h=^Zb9TpOklbg`MCbR7:oZ2G?XG`(7gb2fRp(]AoRH]A2i-Yb4-]A7=
'"-0RFEk(TEBHsD+2g`F@S;/TF%a^tNRRPZCGr[+!<+(45jmc.h<"ikWNn)4"t:]Aa=b+uI_u
!gCk)`#N@Q'0Dk]ATFK6+d9GD2g-*W#7[:&i,=mDr4P7Zd]A>F4bbTQiKsqSd<N`I@!g^'$@![
nEmm5,;sYj8dRr#E!EnUYNqC:ud7LJ*A>^9f/YY2hJ/4l*'i/H9`6%0k<G\bu/nj?Z9!HUb<
C"\q_s-AsLh#'.es[Y&.i.n^X`P-tehr4%Ht@*kZ'5G2)#MMf8ZKH3GC@srrWHPu@=9STC3(
i^45rta;;BI`=dCK!1+K9k5&Ku.IrCSMh<*'I%)`HON.-TImHe>m:N,;I)q[96UEm3G)j#Pj
G>G`&roo7ahC0F27a5r`os&KBZG8K[bdQ.G?#"e3q7\t3U79"?`n9cr5PWQZrj$69=4$<()b
s=[]Aem?]A4f5O`VugsEY]A/0_[B\'J!&hN9e\B),I<h<EQ$.D++D\dKhXSq<n]Alp(Uurn6\F`5
<%X.Pc!GTgW([VAHe>R"QLuu$q-NW8j4Qqk7I^Zj2Bd@P-`_r_ZYWt3BG%@A%?DA"Xm,Yd,R
)dc_<(;0*3Z$@6UNuc`2OnpPooO`>)LX*Rp,fiR%!#:+/^1b0<%?TW@og>bA@g8<jpE0]Aj]Ab
9Hq(*Y^\8SQlgT*=DFI_;j^Lg#ba)l,.)FjHmY:]A$fge(*&LPM/$cM1<amYd_Z:k&kNf/36a
s/d>j9:Tb#5>[uSIe,uu%#jXi+-N*4+atJ`>hChPI`ZITcF="YU433-`?Ag`J7&ju;gj7_$]A
?E^WH)H:CRu>HW59.R[<f<EfWm[Ra]Ad6;%Q>j'i%L$sSI#Sr8GY;ba!3t,E9V9eJ27pln7DO
[R!.c!ME>]AT3Mdc-XdXJd\k<!jPEEo$;<>Cn9B,UR2>L'<5%8HkNH1p@cAW*'k@>]A_l8LP/C
tm:pn_O.\<bAcaB$r`1O4lb[I@Th^g4;NST@iI=r<m&SUT=5Da"+'0r*J1n/+qM*LBp%:rRJ
=2[j/*@7UkB^XR0`+,<tM\VU#"jWp9(]AVjAEiKAApc`L*Jlj]AI1e0Z(+,-2=Ro1aj!4A]ARm=
)^'"JS+1Uhq`QRS;@1IE?"HZ1`SVYkD[2U-QDfWX^eIn/j$H#Pi:65(nH":S[-Do+K<.MAF6
Ce[-_J3IOi+-(2gSO'6:cn.4(t=%)OEd\3s5%cCL8-"SpHpE19NpX?B:=)0016R)W>\E$-7A
s2]A=OU(5_$0J?21q)0qK7((rL6""flj,$Hk*le)?WY)HDlr[@`V;CL=T@0?'b7c@q=XoO#;"
Grnhe"G>JI[#s6-$!n/m#q^t/8qjtN^Q)__'!]A#f2GO5Xpm#*m_mX"i2#f`-UsdX+RX3ojM*
VN<$9gG-5-k:UL.IkE9[`\n<Hl!VB7R*"77*pNOlG]AJDPuH1l^7^/]A&R>ktQArI)WIY@$9@g
WZSd\:ZRf`\/BRK4SXb>b8Y_KYoMcu-2%JK.et$OA)-\-3t/rd\rZ/ejlrVBWY3`EncXJPIO
jg<fQ1D[0V(\9MA-Z6415#tmS#"m_qIV(c]AjqE0gieA@?_#9^K)jrW@QE9#k\mtC"fohSK2n
"^,[0s0h:td3nKNuZo]AF_C/QZ!YW+]Aa:l)c#14pBWTYo'ab%cA3!?mFuJW.a^Ctafe!%[?k'
dPXh.=a/bMc6lcY;9Ck$c8aRJ"Dpu_:Jf`&ft[cTcrO-aB05QcL]A]A7?mY"l--O#slE<7O_Ch
pu,^:\Aa%Ftg^%lq7MAJ@gfXbI*$`k1AU^!.L/6..'X+&NJfYX.KM-oEL><l>P=B?'Aln+sN
[["Vck&i!!N3Nso'HVaZZT_n,[o(O]A$&NE/5F3@qO&(0&cA@=;([e3SjghUgEl[b1G+Nm./`
H3'CdX$=$W:mog9PuM2<)DWW#+N%+>3F4\3(=$*jiWeMGQ%5G._+e^ldroP"-C]A9D-M:]Asoo
AW+jd(j4F.MM([&)pZqC[`4JH453EW#7OGdJ?sT^\[i@B(=;&8n.b&i?YT*-N,+L@a&9kJjX
o'(cn'3PnUm6GG!JMu/cHYb#$qpp.lg<8K?X"X>S*WsCRTNncG`G:,o<_3sD($=[fGjl#XRj
q5f[E:#Zc1c*g6&qnA?_S1KhTSN26BbkJscFI$LaPA\[2B0.QqjP#ds[8;#F%oY_W8UR@T&C
i^VSc'K'6G2ADL39Dk;VRBN5+Xor!'GD80D]A744>pJO^A55Mh,\?L02aHeiWaC:'0Yje')Z%
S'*ML-NjokQR"+=/HHo_k?c)m&bTr;&:1HKjK.O[Vfho1o7S!NGI[_$SV]An@r2B[bK1b^hEQ
g,F,O*,"S;=[f@nQ[sJCdb>!WpV0N=@p9W%4S?eRo'JN0_,V4?0eU0Ju6PBFLXJ'*G\Xg2-<
f@F4E?.XCT!\'eY15m$q=.kij86ttqkJE"VM"4inQ'8(F_7<6Mh&6ch8E>7G:IFNGud`Zpri
c)iPG/BM</^Xe=@sg0fWIqXi@Z4\f+^:<`p5/,P3i\7Q]AcqK]A=ii@JhD^oS9(;=s\AC&M;cU
hR6D,PM+so,Mb[7`5msBf4Z]A>#FmX*.&t'"ql!8:l-QHZ=23`'DhBTlm,nhZbZ5tTnWlW&a<
i`=@.<"(n:\+XY`Z-\\Lej$**B=FkOUD4jH[`F1B>h-@B.1Z.r8')=i_^n$@9C\F6?<9]AK\c
a>kcc=QBdMBkI0GkMdCup<cTiNV80kH'mo2eJe)q;`,bI8b%5=L#EUfNfPQU._qNAfC!lh's
*a6Qn%iZW`e!;5dao>(p;t`tTAGP^8@nNb21>b&SbdV'fD;K\"&1r'4Ff`(!]Anj9[&%RHb0$
)i\BFO10AO=aXaAU1PH=bD.*A`]A[Sh8c<aTID]Aa8CG!%.HL?h"j:9F6*Tn?GJ^KSe-8-@9IK
Pl@r5(R5TSda+SPni)aS`,K8[.6UOLdd[N>G@IaG-F`_\\N,8edFN?t6YXTYG!@mLPPb:F2_
m`LN%)/DEtTE&8=(28Wgl:'4[t&-H[qV:S'Xm?)'(6N4(9@`KVLlo<;7S#$CX9d0u<Hj^r.6
\bT;8^8)ME.38ZlkQdI[!'d+FmP69RBL7/SKSuud-%&nFoGq%-0TN0M)[m[X8*)ihOVOk;jo
@iROln^_=]A\)<M'eQj)0OXp.c,*>6k!+S3+i)91GYQ$.`XZ^qXX+)9T&#p3^b-eP)Nhm-p%@
<*%=?4\jl^-!0<#OFI^0CZc*##L;GpF\+e^j4efnr^HM59Rh`d'K/8uV1Xq7mRD<j8V7`26T
4/LY"G$(\j>dWO7U;t9cjD2HkWM\W/-g`A['Qa*b+/>Hp'#+U.P1',g#L$IOrp1/*[$NoT6l
S)@4FNfuCT;4]A?*B22f636SSb&g3lmO$k$U?4*4MC#Lg;i,Rmdo*d=%tg^jC[$Ir='78!SoV
(?g830C7ke,modPEookJ4,Dk&3qio$BY3F?6L\SV[%5[)%NcYT$c4Xig5"g3nE/[.fQLm%0/
18qa>T<:>[FbgiV,+6]AkLC^4,caXA66**eVl"J25_G-R^,'YU%G`I1!nkHg3lV4Sqt@2i"36
SMG4cPO1t7E=36V$3/7U:j-=b?G/hm-t2gX(<\]A'j3?Q8)&lB[OnE9@/pds-rm**+Cl\hpoj
qPMEDFaQf,MX[?=0UAWL@C/6qFp+cA-UIqZY99Q@,c!?MqW$#R[9(guJO*fJ4Dk>tr:1bT,g
fd\<7q;GcO%t5mcnVsGS_+J0_)EgCb6'KpqZ,iAs6>o3puue=WkZ1^DLo?32kKYi_]A->5[DC
3Yi8lZ.rW*jYF7Q3^O"o4gCJaiYZ315_J0IFbeA/pi^p..>b!d,)Ii<FLn@F;(a;ugcdD2(k
c^GIK!D6_0h]Amga1TiZ-]AFT*O27'ra4[7pNH!)KSd+89>Q>s#WfkBhDn?W>mc#n`Y$SR@rV(
\`h?/3@D]As-?=:/:+YXmSQDBqjYC&=+lK]A?9QcUh_Oq>[ZmV<hOYq='^l*D*aS?',QKj%$:W
+I^%C-dld2KnFV5bI3P?>DH&0296JfA*Qf`;dU$oEjeqIEn`>UnaO!nG58$aC>abcs(dtaT"
ZKqhl"mrQ%4:D[B;I0"2s!+Z`h''!JaMY]Agr+R"q8H_[59%mUICS2%Eju4IE!r$;Gfd!Gq4V
"icc='.a8\356gDtmDG#B3]AX.(,)!/2-0]Aic`hdWFoCc>=!R?N9p&2lCKt?XGe").\'p]A(KI
QAXrODn`*-tVpH2mHRX%D!OHg_#Z]A/RA'HKKJR/$HW)HAaFN9k>'o^Z,&"#n0dl2PLi7cTH=
*Q1nF]A2iSF>al>f`/*V%j#0,:jtPq=V$J`M4WQ8U,!Kh2(AL']AasXl\is5^1!o)B0aJ0m\i`
?9c=!E[bs]A&7[f&T5iGnLl'$GIF8[C@Mck/e6qffLTHo,^;c,#YD;Loh/Q`;DZV[LG64AD5_
.Y'pl4*.O6+2$Pas*W-oiP^^gJmWR/Z@pj*?[0fZO'Z+KJ]A6cU*o?WVuD893,b<BA$o_C1%5
-.$'@oD]A]AB,%]AG3CDo`)#l=dU%l^Fj=.g"f6=g7aah;-F**aaJGa(-]AVP&PDm<TAgge1oSZF
,20BkUG_3e!FS#cf+r0<8oW6Lh9C)+Ih&*BYJ*>Tt?K1o/>6:(-)-/Em_%a2uK#9".%@k4lQ
&>gEH^Z+sG0qZNFP%B\es"8)2"#Sca/_Pe#J!f]AO@UT:>"V;,:NkdXt?qXWt;M3mCh]AV$jN3
SDn$t.K%du\HF9fp"Rp*g%19MC5Y:>@:i)66*@P&Qjk(XqQO:X#ITXsBlqQNM;9]A<:*X!H[;
@C>(ui%WkD<:=Ice&Bg$ZAHE+&6RZFR;XIs2UP-=?c:ZB3b/qqc5!hAer"!U[RjnRkKC-1@@
qS1)*f8A?0oZ7b6[TMWegbYdC]A13p'DTcI`4c;_SAK,D>6_Qm2k>8VB:Oh3OAZX&dbq>'jN]A
R/@K+)9K\grrH@BK=R?l@8%!IPoBj@od&o.>40R-#40*ZNnLZqCG1DkJW95^gt^7,'j$=&8h
GY]Ah$ob1\-6M,kW+1\)48<7L#qU<71hnYg>t6Wg%Zk<Tc?G3d`#ZWM4XBkIm=En]AmiD<tZAg
5^<N#'ML`0Sm2r=L'TP]A8Z@jXbj[Ghp6f8FC%l643O+<Y(F<)-4h7R)=&'Mm"PgH:AE:sh-k
OBD&KA$m&SC]Ac^/[tYqL9q4Z;YJ7^.lV#YZ<?#mq_5gVOJV3d]AsD&!5m5b@)>h'dW@Z2.TGj
-Ff6.Fos$GX&iWb?Tii^LL_.96.Q=$J(PA@W\APJPa>u*qeX%LX8:@_G*WJ+f'?GE"]AZ"F"]A
&qQQ3s>R`C<Nd\(J1;NLSta&JQp1`6`W!B@=^X94#RPM;UGT&rpe"]A>hU8a^3*G7J2lDXb/k
89BD"[^cIBFtAb5Bk#u*\_p[qb)-R\L;BfFocS,?k-9D^cYh!\(Kpa#j[H!8uUkRdCCZ.;%A
:9X/j4b>ZJTmTsS"He1lG#eFcXl\$_V&ASDHmlCoNYYQS$Ct\(*j`o2./,Zqq,!/,eiOZf*Y
jph7$d*_=]A(csWOW5S]AtJFJ[Vba3cc!3JKASIm%5;8i7C3Qqr2.oN^cmU*b37Mq_XAk#B5t;
Hj`aFa@g1q+$1N.iDh<G30*B*E2a9[gEf0oD$(J(=5tUB*h6)9Q?,Z=`K#3Qi?-:uBC=gpml
=B8=ME6]A6oIW0':Ch#E"PlKQ%4Q5q7rB&8V4-gKDq:ha-\?KRiS8qHGK*Sd4#i&*hT#3DehW
6Y+Q)Hp4RE^La3B8.<@r0)`I3\<bn9RB[nti<C9Q-(nrK2r9H8uY;Rlg5\I44middQn`,j]AE
&7UDee:1/<"n6h1$R9IX#*V4m25<.Gj2SO]A)Ea)c0$YiDCMHqRPh!__e'tns,1tmd".?t;ji
>fJC;F"Q>!/3qk*RPjORuLRj[(7+[g_YZI7`J7WcMR!gW0/F*[rYK.LJ%Y<@hsT!?=IFmhn*
VhL=Q@Gm_;lLL^TWO0;'?G".uIj*'I<\BQ:>UO"D3Qj5U`+-Yr(Pml'@9F=DN`Te86E`m#p'
<u*>$[C3fK\Mp(#19Pe4SPp&/dM9mSW&jse)m`5`02t+f*]Aj;'hcDH8R,djf79G"9fLF4pP9
T[YLHR)(=MLB;51;j11&jQVa<Q"F!NDqg2Z&p,^H,&pOK'kcdNk9PdX7t\B8trKm!;N-q]AA>
-D/4?oAPn7lMQJK7%E&#EQt4Q<GP1UlrIK7CRA]A73o(7LlH3>Z"T1(bNlN[UobYRX(Z3O7/a
&rVoAMMk$mapMWnl4q!qm9qYgTau%e?s_emX#lRPXQnmM\C>LWa%heJ%`Q<Q&2B6Kphj>C@>
=!%`I),K7Kh5UUV_hc"!W<U*ha;LhLp]A=,Cpe`kfr"&r2P8SR/'hZtChHO2=^qTNJSW^pQ,#
)+ISY9]A*G8/VA1^JsSmrmE`]AVT>ddgQ"9kQeYPsXE2:tL0]A5LVS7s9FmlLH7Q?;Dk"lbtYcD
[l;l=\1:*-q<&.n`s\0(U<&heZF8)dK2Y0=!ETG^-RF>_o+D1]A(U;@ncT1CbQK17OrH5TT=*
C%6ph;$\>bn<h#-9V8'l+cJ3=J!OQa78YfLnaLp0*6=5B?&e,J5UL\M#l_g,emVM^HbD/fM%
K/4dUuJje:f"p<0W*1Qi8)<+GGL9)?K2PMt^.4A52H/2S3is9*50LgSo=Tmot56&BC@+>)9%
+BH`5brm["r!VnLfK=@ahT7&#M223bk_t1@Gq^WF(N!QuDlGK&ZEW2\e8*Hanjm%C_;ic90B
Y1.cBZqXVl8QO&o`c#%hi=5K2c+3jIaYTQq'o+[Sp8VpWe2+75Hn5@+WN-P!:>lge3aWf/F`
,C^t@_i!akm/]ACBBUE"!W#hIt\>Sc\6@2j?&MX!-LG^PZ$\riIV=@J:Irr=lCR15.MsSb\@l
;0CapKVR"@HU*d*\/;7BHI.ekSJKmK_.Tc-Y[E=0Vo:5s>kbSVFT)i/'+7Vip<"JVI*mtBc0
Y*OV`#V"!7ig")9uT.E(bpf(f6^HY5MYOZ\a;>!5'qfIi-ejpU%e$4opOM!H%QRl9Sfd4Qnk
l^1U0e#gA?:B\;/B]A'G%6&A_i7+[f816iD-idZJ,.@?Ko3D,_2;JbUiWb:/5Y$E8/dds-9kk
jhr+&#Mc.-Eb]AnkWriqTpj=X9L(edeFWSe!"cX939hIXW(_mDHnb9e7ZnTbE0?aqhKCtOl4s
sbA[2'475+$g$[a1C2;mEVd_YqEhFN`Pd(d[_D8J^n\EgN0h-&=e5q/D^5j\r&D;TO"rh`iX
oPU<%9$_p]A-n[j@94O+eRfJ0"=?gL-8DLMBlQ:BXgt[5Iepm"Rf3gf(_V+D`pW."XXC7.+gU
@7Y14#jVaDmlQme/,/fcOY(MNP`1;)2^5Qp6)NPVY3`$Cpk.+IUH1]ALB;.AgET=^qgP>o$!:
3I3-JnE*a&c<2onATL%le$[0f(LDm;;]A3sLqOe?1Cd$&Z0mT?4i*rRIN>P@V(gb:Fm4QCWPr
l*t*F([meiCL]Af:qVJVhKDWaJT5C]AC7QRcek&FZnT,\H9"$^<\N=^;m"g/5DY,TP_p]AQ"drt
k6G8a^WLXKC-*K#Bh]A$+4KW`D/3T25hEmY7oWO-TPngI]AB$P3,s.TAh8,=c#Da?eH>:O:#E1
2#Ftn1KQIHr[8Xo'T>.$"jma#C+4Itq<%P+$ZW/u`3G)T0=c#jcFfT*n5)=U[&eoP65V&ui6
Wi_9tC/3aKDq.+32gLNP(4nW8ur2OJott86OE3aAKptlBQGSB$1ouEN/e/UrjhpjE'S:\\iX
7)LieL+s[6*dH$M+@+ZBnOt";:c#X<@!ZCB,r=0]AWfb$s:r!3:26A6f6AVtCr$`N7_TfAhJZ
f=<U\Gdud%BUAX;0m`hC!.]AY>o%\^1t6edB\Qn;9,&,Q'&+po6MLa[h_7*AFeZN24nUZ-j3-
8orRc;bE]A!M'pu?Pjer.)h%RBRn4!g11>Q2hLH<$[5l/%J9;8+]Al-</^;GVThRO9=5K)?&;6
;PZ`qA+`ea/]A]At;g5LM:U?ejQGV?GHp60N]Ai=f9^Jm?2#s2a:\h@_%GLYlbQ,C(Gm"*'NU>=
4C*]Asd8Q]AIS,cO`RGrC[j.[oV!;H'Y#'aVCcU&!rsFTrpl^;ICt0?kX5(0J;eZoH$Zgb(V1%
V%)46W"Db_6mIPQI8K<gQD@`kDpmkiU-&98,[P0:[,H:XoZ*g]A`ER^E\IXMl:5knK,+pM/;^
?KO.2:UXU(,?e"o?bVg?-NubSL'Pr<]A`W@Un07\.j]Ae!=t3YV-\*rnll1T5ME.U/?%o'%MQE
nN7pD$]A-R9ILO]AP7U?)!5m6CDoE\,SIo3nU?;Ln##AjY@?F',$_sSGZjNf5tlt=\Y5T'OZ0+
;(`FDB%1j0Vm7TZ>PM6[:L7@P&^t)X3nOIn:Yed^9R"-s2_9iT29-U.SP.e2AS]AXET-*@bcJ
\i+1p,&ud[%]Ako4GD86'lc=H.u/Q&s(6;X6b/mN*tPtR;[[tC+9)l_%9^&`k;34f7&mm+RsU
"f&j^&h;p&sIK(7AmJKa01ggM/^EH86(S"nW'$<D%D[P1l&8QE;c[hoSPb"=]AVUgQg=sdN)0
n<^.66j-ZY-G7\gG6oNl9qMlk(T#Pifi^$`-'*>c:ndLeL-L2q0PTl]AkJ,D:9ic!kg6SQl@I
H):=ZM0eQ^1[8'.nuIUt-3^Ut<IUHC"B;a.`9YX$rh<bLfpKFYAQbjt1N;*@r3Z0%>6+0Y*D
dCZg^"m':!or-\.`f/X8q^52!-#p`H\8#G?^lZDqNT@Pg$Yrks1]AieY#E]A:bnu@jCXMG1X-7
6c"iVpoE%03cA*d;TsY3Oe5fLV1X]A?WWaSA<J8@#;plVE0-Egho(U!</-mmPb:.BjWu[$^"'
.W[Tr(fn6%@*48?[RFg1l?A/8+C+BmDGh66tM$J1l8osIk@e`U,P3+XI+i6CH;-_mEM;9d;5
B>GA1!]A2iArX3]AL7^LR"BpF[jaoI.7nDI!o]A6'OG4#uFPaPX;Zij%pAO-lV&i?Q@.2__EA.T
HH?]An2@nO2dOl@-"gE)V[iC3?Mo1Be6dde3R.iH?.0"lqX,!&.1@h5s8e,S\5d(IChAZd_1W
[Rqq.hY"P+bT2?/-JWKR.?(Nh]ASe$K^:fYSfJ>&RAHqFs9;p&Em\\WNC^\6aR*rgD4#Q1U!J
k#jXVKl%3ddF:"tG[pE^;u[;_pY<A0^@&hOWo]A6gsb%bsm0((sXQR]A.uHnC1j3J@om9e]AE=1
GN%2'A<,aIHhOaN/R=,cCS_Ef[GP[RT^1aAA]Aub0!pWgQI?X>R=ALUcnZk!MTo\Eg'X13d!q
t6*J5A3_^*gE4PJ-AG[15J3pVd"=3QpJ\P;pq#GFZf<ne;c^D")@!6=_pkGY_tBoQrfoJj(k
m;)Qit(WBE/>_=#0F`V9'%U7fV5SU`d@8]AUO*JUSU8k\aPN3_\]A*;\aYtILZ@>lDT%SU(`p-
2Bd\-[MP4(CNEp"&!!5hh=l^cK0j5,5.cAOPB#s$/sSqR@-DdUkK(6g:#oSGdeA;5oI,fln)
6D!BKRD\K;4a5%2?^8fX\m+q*46F,GagP<9cg?bLfl(Ta'FKSF#pu`-uI6#/j9A;EfFOXdQ[
<$K+$_?#/qT(+j,*Qr>P0an=\Mmnl;BK;lkdBLu#&RUn-j/60?oO,IE;CuUpH_+mR4%d4pB2
aV;=Ul3J)Rf1bmO+Nl!Kehb9gMJO>*)"_pE`K34351j`GA6f>.f/tZMGY1bV=]A-Y8U=0$.Y!
#@MY+=De,<AAA6J37K_*,Mn>AW%.,-W?[65`<)&-E0k_)(<r9m%7#"H:WgT>3+OBk.n2pQG"
if,M4K[eb\&MkQ&?![sW@P\SHrkgNBBX'Kb$)T@?Yo@#S6WAYpCqFNSJG,/CI(.&`MQ<1WJB
Ugs/ttkm-N4`1Zb?'Z+(DUJ`HEnNP2OjSju!eW%MKW0`Up::99h/f6n(WjYJe3Q[rG/IF;lF
<#(;U]ALB%?O]AD5/#2=1*D)PEab'9cffVfnZMG'M=phJt'pIKaiNr?"aH9*8X,3s'4S4\e<:?
5o%0Tb']AQjps`aOl6/tjU@Mrj]A:No<S+Q9WS4\1QH\dfV+%OFg9AoY=$@(S#M8Y7_$9Mm,Oq
[C7Im!meXPC21tO@FS3=24Hi-[B/6LBKBD'X[3#VGH<U_1)$Ie>4]AgIQSBi0WN2RA"%s7XWp
iFWMs0sKIXfIkJG5OE^7eIWG=U$BlM*eo1-H="-!YFju?oKV*i_7"[=J]A9Pdgi=C#2QJBOkt
D9A?Ua)pYCXu-r)^Qi9_IQ7#Ct-PZcmWh[.PFj#::fM7%\]A@T3JYTQ[H%.74n\&H%9FWojE^
QnrmN0&oA$fi?EC;@jm>/Yp;'NE")_t'*j-,=H8IMRW4i4[,K1G%Nq_-V4<5nh[k>`T4[DEF
P3RA=*,QR?IX),o$/N[5b:e]A+,D.V:NPhDm03bj]Aa`0?i0K-KLOSoQm[m#!h)16T_),9aQ.=
7)>J5<#W"bX>$V=@mp]AC^E-%4/?+l-+IUP&)ZgSa9lejP&]A7.G:##1bB>rLA^1=?U8h_:3L7
a3?-/F^t(^a3?.+(J&ks`uaD`K((\>1K!9j!%jADh4%J3^!4O%W-,GOH#Ds<"QRmJ5Gn,Js"
#1Kp;"(u\hJSMR(I^#2FJ9(EK-Pb^Vn>7(J2j$mf"aH./bO?.6K>9>B)7%^LW#8qU?I2p6eg
Bo1Wp(`:GBO;ZS55gC3ZWgUC/)M'Xi\5/DY6p4G?aR'pG@`:7MVrJ_WB?\NSR8G<6V]A6gkq<
4)>TOJQL'hqEssRs'(N(UE*>XdlY(]AZe0>E<$4PeGr[%Gp:?=UFIB/.7P=<YGc;CjYGEoS.5
<T'SJeI4a\'2c.'i;3)M.9E-893]APM$N#q85@ZDY/O%[&")-U(kX`RSI]AA0_-C0"2lT[)8@3
,hp6bYQ]A0(s.!?]ADqSfDp.<5.oU:nOU36>a/H$D^(GCX8JWGZ3icP%aHbW$g2!suob%Y/diQ
=mk$X/>A5Ze4d$>>/lgfF%,`E0d.s85TGD]AkRf'^.V%'Ie3::JgRO2.`[p:7bmb.HkM?#&!)
gAkU+/:-3io9u+^QGWBO0;+h&!DqDaF1]AZ_6Mj_mU,iJKW^m+Ur0IORKF#iJ&=dU(fr,'bWi
ZntpU\30*8B,:fVb`2VOmA-QH"CA_kWSYV683pHp+?/jIY)@h*l\q`"H>+=q&Aakh/mEbRlG
b@%31p97+UH'%=^T0o')Xgceh7pRnO@$0X3C94R[RmIfT~
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
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="959" height="54"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report3_c_c"/>
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
<WidgetName name="report3_c_c"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[店铺名称      营业额    同比    毛利     同比]]></O>
<FRFont name="黑体" style="0" size="128" foreground="-16711681"/>
<Position pos="2"/>
</WidgetTitle>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[KmJ#,<:ATARDgm?1:E-iaPLAqM%n2W+_?lafge,t-m?eC6APuB@bO8:Ou5#G8n\PTCS[&RA4
1[f9RWMi\(C6I4jRJIk1o^=]AW_)&^9;lQ/7m.&@6kbs!!%[(bY@g5!"oG8LenKcJ!)GM'`n@
b_%rnYgU#MHp)QeI[S(8(4F"jBi/UbIi<Q.pcVD7BCKQ!]A5Y6b_&VElJS05d)0M"KN@tkh''
TIu?BeVu_DL"If*DNj`X(ttAX2?V87.`.Ydk9S-3G0=,qqNn#luW#;CXqYa`NUn7eNUYO_(4
.9VoIdNHoBW'Vto"Bcm.Kc2q@6cG:ZLW!5!Z*8kp3e$n#@8X=rcl\F([,i$5_BNe1=QqR%aO
Y5^kmeeC8PI*/0q;3W??b6rnIX=Z+:Ktc@_>@PbdGnSc*Hf<A=N,OI-r%8k_rGYP,L`!eM':
P2nDH`1HdlU&<#HJZ^gG%b^qZs\mpKoq!r<*tS1R\+r(`YE%>"T+HO0_dT&pF9*Vhd:;LjM#
6g$XDBA6\_%(Hej\H,t!L0?C89c)3XUL4W*M]A:.XqYlR(LKi#$&1EX]A@F'HL2!-dGY1e)8K>
*sZjB8e[E'(m.EH5'q@%-n!p<Y]AmBIkIbo"J7n>%EVeLpF)JKfnVdr8!;uWMn;/.A(7X9pjR
K"?ou+<6g9m%=\9_o+XI-"5c*K#-"m=iH>Im$p^g)Mcd%CI`/W=g1LWQ[\sBquIY_B_(+!DK
Znr#8@%uIUG(a*P1FRq_6WK$i.>M2<$DC3J168686@8+sorc:,=nh;j_'5%/90<HYH>#2!rA
$:a0#H($r+J:J6HrH^,ia4sp'uE,?)AO;p\p!WEgCCOdUl9UW9ni%k#tDtT`m!d!SNJV#VU7
\RNk`.r<18[Q3<Yg::,]A>:7o;9pqjIO41a"-[Qo>R2JI*^ah\,@PU/I4l_bmu6[-Kl30%L"p
XYtlZS#,]AiJSbb#MMUm?"h>9EN"9uo6Ffl#XoW83#Ve=Xm&'8Q+k(="Q=qrCVkBI`cONY6Dl
3)46@6fd9HBXXW#>3<@"EVXH=:(^ZcYqA<B]A\')&OCWDoH,7q7Dbh#N+Ge^HG_#&qM@7PN/&
]AG[Z'H5*c;a7jG,"BY&U,.f_[dBnf(mM>lMk]A)eE'L6:,a<$<`ot,X<fTM1:5m]A:<91'^GHT
p>Inl'#o#gMh2NRaTQM2u,qP"I<o4dua#,$GBeF1]AqC&GUGE*6H/@UPS8$J_5`$r-AIqRZ_=
RK5a/O57U$oWgQqmANAE1kTNl*#YdWVQ7o<EUm"q?9n?NDGrV>Fk#X&l+)9)>f4CPOEL)/j/
081d<]A3OX4Rac8CV\f=06`<tZdu0*+tp',c1.3U28g_8Vr3!-!V0:aYb>6A"*!3c_PGg-m:[
V/0thuLO/pP4^qrBH/g#bh6EZ,H@*Up0KKaSJ`=boO@1b.BZ!7gdS=J]AB;U9k7/\k6l-FfO0
7md9-Y;D'NcgCniUp_%u]AFN&3!P@!+3<g(O+6\3o0$g:D(4kM>a(\Z(^:6L>$HFrKE\(u2>S
cHJ;s7J%Q%aoO&d=cX:P)PVcnK[9F#c[pnV,7G<GabIgi,1dK>9^0ADl5"1n9YGmGRCQ['>@
U8'eO0#['V=2g,_eNQL#`M'H^rf0h<nlH4%^6-qj0iqHF9HIc8um/mO1"Q&%/>qEgrQ;U#tP
/IJ@bk2SD)G+WnHS:f*SdPTaQ)NpoiFm$$5\5f;<GdfOdm]APLmHXVRCJlr0ZWNQU@kXL,n[Z
6es+a%G:EQ<U7LW9pYjniY-$3Fh5N>mpqS&?$Coe6\ftNA<.C(4h4ERZN7PdZ:CDh'$L'e'?
D$DYrp>+aeESWf*/="5c0-Z,r@>GMs.<jmW=uDA:HAhJ8@7k:cnX2L^\/m=Ns/oc.`3l6Vq+
598<,Fd6e"bpOQi-I$SV>WaL7b2KJak!MCmr$;.!Orh,CTM@b@-`c=&[TM**k?iM#nq).n?(
flRG+@G&)Y:""f2u1.qdfT56h$bT`mkn\K`+Rm?R(p1AsL@m.'=N,XY6Ap$i-(s*$tKO@_P,
jT>^SWBJ&%]A-tr^/[7Moipse<p'uQ,(N+FX=?3l9!6fDH\$>LFqe5:^VUsuT=p"SP^n2@r2_
bBaMrk1?H8V'W0Pu*i-Yc<*0pCfFp)OZ4<Zjk,5gaB8IDOA&`,pLB`5CZa`fQu(@#$06`"aQ
FYfjf]A+sgK>3&fc`i)0ck()a()4?*"DPmJNrHfC,H+KU*0BupheQ3RR?"4bTWDo6\O[l;$d4
I(aDXiX,lt7Pr0b?_7oP:?"C<\f=_LeTj[Hk/UI'C5pNH@n2GVWi>3Cn9I`7+\+[juO#N`=9
AF`7f6k6XV\U*GL[jPaJY8O`hh6h"//8I>R@]A[j6hnpmQO!3^C4b&WX23fkqPkfY,TrsChDV
i=^0&q"78A!,:EbQThbE*!>4.rj-+n6?)0VZ`#A8e)a_Qr9^Xf%cL#q2XJZA)UFsKhuX([El
o.8/c("^fP+W*6%V'jtK4@)E."",+('cqA"Zt1V+OVoHN:M1VGIY)%!P;'c[IT0T#T01.;@+
.d)CFH^c:>*$2#K]A3'nScegu,F%3ZI+tk'b'Ji<L2?TO+*k,5aTEc)qnf4]AABk-.D;%6C)75
J.ihJ%W&h9%G,i8+JfYX3sR78>)D`N7G%aJ/PnkS,4^CN*GUH=W(G.S2p84H@6'!:G\]AYY1i
13\2(YkeuXMA^*giEfm/p#?0@l_`UI.^t;p9jQS(U0I%g'Tg9Y,*-0BF'e\$E52%HVPGf:P;
jUJ$<9d&8g[bZ:#hCnsOr*r$gmglfe3KIUB"\b5JM=i7+>`UjEV'0/PKa\kH$;RFZ7lVV&=5
YG3NHuno^Kf`I6XioreNqK[7#Zo9%Z@<ASUtb74Z`^pJ-qJnT$7$Mt"c.KV)o_Cjm7"NDfr"
.tZDpdBWc1.&Dl2f^f=niYEGH""tnaiR'#1dTN/\6;g5\[ESaQBSR5[l.LoVa0S_P6Yc\[ki
Rg6H8^luFGkkU!.:<h7ClsWe<UC.)\`"kc4S@pPY\f7.A@d^GcRTg%4]AH.`g!R9:O,n/@LHB
\ClaBQISP4A`ZOs"`;S]AK,7BM6iUQu1G0W&7C3/^'$rue'XI_u:acWFHSZeSp?[DJ`TE+nT>
HQX@e#mfH:"VH2*@\"$@D7`ALqba/$QFNm5kk&D$ioMH4FD-=9S?S3h6>a#3]A!GRC]Atpe03n
U/(BbO:3g+leJ`QOgfPI`ipk11BULrF?gG.;QmZ]AI,F:Y7\^"8m:@k;j7b0FP)[:)p:@so"!
KBfQ(9PL]A`eZ/]AhP^<Gm.A7rD&p`HdC2O+GDY%#F@GPppUl%j3"T"u1\*PX7X$$Lak,P<i9>
`!7?p"AOAR*p)?Hp9^g.E6?323q.c(?%*HZC&f?S[>of!*D_cl`k=dLGYZ=7,uD5,DN19%m7
j6@PCT9\iQ,T+?r3Zh.lDT7R&[_PWW5+IHu#[)S]A6\p$;OE(_ci=BsInK"epF[aXnKkm$bn;
2R?['X_e,56*gS&;\ETT\'Y50N^aJ8]A<[Eg(J)rgr9@\#k?W3Zl>PfF_ZB1!/-ItIZ%45f3O
JXO?2S=e5DlWOWN5\CQ3JuLGonn9!JfRP]AfZ5@-]AJU^cF-V"dAK0Y4=Qida)OFf$K);'"Iak
=6aB<Y]Ah5fHhJ5gQ,<(_j=G-@!F^j@o+P,4e$'V#n$rRn#D4=X9'N#'2R)b-@Yruj#&G+=R;
SCup8E-CgP(VZKO]A!o-=A+HG#!Ga)oYZl10[g4AmK0e-(LkRd3ZU2VbdZB-4=/2@:d=j7b3Q
_<5F25pC+m.f4&Q'&Q9hU\/etSf*a;'46YMb1%]An)X^[Z,6V<Ur1BD0GlMX"QK)f4';ojS[#
&Hl\aF-?h,O=Yh`^l@W8rp;q$X4UI<6B,!8\,T0Cdg/g$/d,3C^,TU;nJfb@r4jA[rI(p'4[
:h5#!LV2isblqH[i`]AqI9053;B$KEArT0LjMuO-AoQkkaY$CVB`1*fHaT-7,Q/Bl9<@j]AuO"
02R5?'\T`Yqap7Tb`Fg^C^s/Qc.F]Ak(6&BYHp">QEV(X6I_XWPi0dj\:qMd%Um!&n**KRVMu
[>P,g]A"UI,0X8iIm$K>\#(fE9ShV+;n)C8Y,Q4F3fPt<hkHsQs]AQ7hCEtE+=U3\,FhKHDqqu
1H?*1RG=:&V4,t@eMXb!j5laL?VaS;Zb/:,?WVFjp7,)Zk,QKO178^uPWPTr1IXng"PU%a=)
Lj^$+!4e[4n4_/E#`e)Id]A-@ZGV#2A6gWkZmS.9T`XLVQAW9p!3dQ&?(aFRr02E%r9='k:k4
*aPLmG7=F.E`(`!;%%NeRR_%b3]AjZn/U%FEbK5'rb&#m,Vc"X<%P6=DGapaA^E8]A*'^Y5r,E
!3,W]A(c6$%"YQb6:s8T%:(:Y5]A_.4/8h!V?%DJVA;2O33W@b;;lE47+B&'T^iV'H*..<9`R5
@q@\\Gq&V/oZ$I!c$13u(f%aG*ebqTTB&dB/>3V$BmscDuerlHW7p"YeY&Ye:muDVTW\X1*!
T<BT+9Ie#X-94U7g#L=f>TAf*lfPrcYXrZGZkFc(+#Nm!?IjlQ[%MV1rO/J&Xh.Sl\^Kj/@<
8MN19ack$+_!HH[@)8%C]AKZ@',P_9!$D=CM'hb_2Q9_Mi!<+\E>Oe-4BV[e['Akb)'+O*9@$
;j%gU^Zi"KUN1h_s.D)(]A'HrRf"BkTh^1\e5]Aos3W+*B0q1b3la:)Ve3-0:28#DR<0rnRUeF
0ht$hbda#c?,FQ3g&<l1eWX7.p^E"4i5l(?*^E&>4Y8?(5Ise5+`A*#+969H#gGt%]ApCM41k
f9qS@O^mU>a%''CPUl[=_"P1uN8/$>e3L!\[l#>h\BLPqBp"`[Jh0p))T+<QfEHfbY=/7Z$F
=La02I57ArU*cF%KSX$:Cq%aFsm-<NP#@DT:/[)e]Ag%!rj,X3YFD/K<#J\RoA.m3NMpo5UOL
$u="auI8nLqKot_G.f&H*0-1bu*J>0(6k)GuC'EQ\g>"hSZ+r@C&,M!G^b_LgWdSn(mcl[rQ
'!6a8!X2h0f9)NT06hioZ.s3]A>Kd(/tASaR#ja,3c_/r+ojN5sA7)u7&;A&.bi05e[_m&TmX
6M!C(^X:C0c!EJWD$8n$-"5&`LBFEA20tDoBeeC&En"P0",e;*rgSk^1K.Y#UU"(]AW\#T@iK
^JTl%sMJU,09E=;DXWEd!\p/6XGS,8QGP&RT`PEfXDB6j%5o>HT:PbD6%CI3FE97^gEKicIp
YlejWEjgutp%[,RoA3JPPEXaWQ\1p=^1f/\"Xq#[tD>9T41`SHh/Ik:DZD&%N@'A-=ZH<eYk
,pZrRJGEtgpikK>1Z<_Bb^e5<>"$r]AQcD&<:6B>cJm/AMVKN1(^0aaahOEOR29*,4Co'1,I>
,1n3fKq'"B&Y*>Esd"[?CkU-7=FNeqT$hAGTXO6b3q`F-cu`:Q=$<Y[TaUOmu`OX"m64k1GT
rN_e5F,:dDKKEs(@Ej3Lo_[Ud5C$?uA`&]A^!=k%,dpj+C3I3`qV76[hBE9@LY^(>#/.D@7qD
6L0JBX6#_c1M5X8hasi=R\Z(]A5K!iiac5_`eTYqI.S/Z.$;IZsO3QFUI-<Kd]Ag7Tu?\)/U$F
7IOu\"6?,0$<Rq.E]A5Y9q-C=".kpclrUg;!Ef##quad-c[cHh'6LDi60))j%\BP_'V^T',"n
02$]A:1lM8ETEPFZl**[LJt]A-r)^$_+ma]AWSLP-2q:TsU]Am2GP9d?_2TPG<=eonkUq"1h(g#V
Yh+]A6QO7!e#./fJ$8Ir4UKTI]AP;m>.V%B/uGK>q<PF\]AJ=+F3mRXi&-j(Km*WumT2-]A_O;EN
GtVdU+^9;""7/bW*RIf)aSW:8Op.0_lRW=7=\JS:BUt6:2=SL"@Xh4-H1'jZh!&YJViLY^$d
'^oUBo.DcWNG:#KP'f$F)r*ebMrU"^c0VJm'FF&Tf>IiH$H7$b,l>34^Co17iB(AAInZG6j)
,&#h%QW8!(0)e`ISqmq%f<W/DY_k-9Y+j&r*UQ\u>lnN.<K(AR6%-Ad0Io;$DRWF*O6jr,6O
m&fr$X=,/8H.R@%8MC:Z<62<`7"0dgsN;+X]AIQs\UYBo^;T8k[0.P>lPJ.U=)Wp7d1$fiTAY
X%i,%nTs"r)m-Zbo\^G`Q6f;Er+02Ok<"LXWdo#:U9:nWq.4mW`ooC,^,O4#Ls(t2,\bP.J&
Vmu,9:uK+CVE(J/R9M*.7rkQ:<;iPP]A%1?jG8u6JK3GAVf`kfEUL/:`k(S<<*Lb=o^;>.Ud:
^`l'b9?YcHm`doW%:`Wk>jsf^N*)2Hf<q(Fq^"E*kh/qX);m,$-A87s(flA5KI/5p(5oM`GV
\LXRj]A+s)N0htDPEaSQD@2nBf^URq%F@0_Ee(q:Ct=I(@C(/).3I+@S[Q`C`0p)71;DWkA/e
8*N83_pm38pI/0l@C'r0B+Y.;FIb`VZlu!gZhT*X$'$N8uFV7\:q2;\_j4ac?U@(SOp"gftk
p==.k,`*OfBLKO3[3\6$Zsn-jL_`^1#tJiDT2P<If-,.rNQH+N1ubl=SWr@snE%^*CZ1E-J7
J!ubuNtbjeqCS2!<-S&,,+m'7Ms<Dq/Mo_3O7RHiHBf/Q:lk'Vif_aI9;8.batIe1ao,G/@7
sA6$Tt1I`'SX/]ArU#)khi#"Y1c3CqOU^3rRF%+g^VSHJ[:W\3[(=/1%^d"MHCoIpKs`6B<td
'b9`aRX[GJgfCA]Ae3tD7^Nc3ZKE6Wt^:S+0%C,Jg2V6bk'1/WNNU(/6W3)4$;>u0st2Cd@t4
*#M\MI'2F`>c)g4H90SW:!(Me;NH_l[MY6dc!4NIqb9+T'dG5'i+ZY!`Scn%AHhrLusQ+s+k
rl@aIV^GhbOjon4:S`h.6NC"*a4a2Q#7d]AV9H*]Apk^+.3"2qc*F$RR$uNHEWcCGPi\*;ltV`
;r^Xr<Jm`QU0JU/s0mNSKtZN24?78_k+>[LS,3calLi+lbq'Y#o7u1\k%DecaP^.T4ON[Pl2
?+-9goPgF'mbq!e&C-Z1Mos?Je\hIui%Iqu>0@>m0Z4l>qmB4]A2J3\.0M;7^k,mY.:P>?>Qh
FIEk(!fa'MEpLm,Z"LIoAo0gnh&ci*cTMm\Dp)eG4q7d3Zb=:&81"8tn`#4144.W,2h>a!H5
G.FX?H&a\208*^=3Y/.^IO$s2-eT`PlD^KlKI,[G5b7L>b=PHor/do,#+UP7:,/?bMAQNT[n
==#'_j%9ET(;ghXE^ogH31?$c6`!Q;,A.nVMN">V1WTJHO;C1%]AFBU%f=r:16!U$<qc*^0oE
([2t0nSl(.#O,L*3%Cba56[B3HZO2[M)pIP*ESkOS2fc60G/]AcIF4qK#I=1`=S:A\]A$@-tdM
WRB53"hD4H]A1cLu8@u=gqF\_o@dt>\e,\f-5&Opt?P[bZ\9GFXqNQ1OmQc::j%0]AL;o@=I&F
LXO0_sraAr3:Uj2)lIF;3LP"@bb"",Cr2Rkej.u5k$ka['*^d4YOJ354_'PK*C]A(B#OpZFXY
Y>h4M71Pq7^oal6h0CNnu0Z,'U_M/_J.&*,&d:5AEP-?l<3L!G@BjD]AZd(TkGiUr[)Lk!q<2
99179Bg6U?lAVMbokdokt'D7Ra37D:e")0*r[M5C+B/><U(I4"0\^eBHH/1c/5K#(DeT`NVH
WZ;PD-uC18Ho#1`#V_^g-6kMK_)1ISP>@CsRrXWqmr&7ljaXIHam=Y?Q3tUH=dU5kYEf3F-H
s@N9K?'XU=59QH?o;^'e>"b-C`*EoRZ!2IkJr)U,mpSPUkVpSgLur;&CuEXU'<M/]AaGR;%/+
;'LIsB*&b:)j'-8TO]Au0,W,;[]A_Ei3SIOXp[-El:L%j%lGUZ+-b0R72VJS\3M(3GD:Ln)bWm
tn4hL>!NW>Ac`.&t(qXG>uB7@Bn8DXDp.D0EG;a(Y!8kV2(pb5oQ@C"kgGq:M;DF5#"_MU/t
VlJ*Io._bi19,+AGUNILf>bB&=M>gDkML?,q<JFE6&8+h;K6#lNQ)n>\3cQ5[6!;oa7+2l6;
k+jK@N*j_cAC3oO?#RZD@77Pn'Pc8Djnu0Yd^s$6_)d.=dm;<rZVAQRiPHr+fU96@!L@ER4>
7C8-[temM/D[nYDSp`m's!u)]AJBHfOSOOXptZjo5EC_`;HSh1DZqse'VELWqe5;'17^#VcdN
h\R/;V.P]ABD5?k(@^=BF3r^(/aPXS='`2)="H=c'gAonoXGB\4SYR;/2FV0=^aQ!c/G&1@Wo
@JcgNI'KAc*!&'q:OXRQB6iI+r<AAUeso6V$osJTl$rq0Rj:<q(hSN-;(^.hM#!D(d]A9Gf%<
n!>r00_;mP@]A^DDX-iInZ"#dqjM9N#ghm!:@1;R&jqI8=?6&ggHOLDui*JPg!A!1baVqOo)q
<*MRmqfVk??!B[TnWU#q/PAWm"r<Cs`+%+3)Z.:8qfTVo[Y:XKVQJC<&;*l:I#mr?^6c(q;9
aO#iXVr#+DIWAVK2BWA\WnUJ77&tc]ApG*+kmXG*%H$&lXs7)/AbIt(/=OgBQ&S&O6tb,HMi/
8PSbSXg\%piHr#(Q<5h=^=MP__>4+XrOW<ahc;**]Ajj]A4+m9SSnY=2t<)__p:qlmM`a!f:+D
]Ae,#)J!?.,f<<:2"clbJW7E2ONbs?k=,3q@oYQr"/9c^Oeafq6sDuGPo*+p:Gg]A;:Va>QA<3
%g+3GkFa6sH>6BP&8VSTGM+Bs=V/[mZSgO-%#H=KWqPnme:pho:l,HK2dX#Q_W0']ALa%IM+"
`QPNPd&cU-Pm+#IB>SOE/T173.=6/EkP1+>H2<k4@h?F&/8V5\l^"ChSe$rJpZo3-bP9G4fC
pB'Uh/Q=q1A,e8&t,`S['>E="3r1B;]A:H]A="s1=j%cUc]AWU`T3FK2dQ13F_Pp'Q]AYRF1PBHG
/-uC@@J)kXsP[*FucL98Dc*%SA^]A-91'+]A^I,+Wd?1+e$(b]AXBjh$[K6btK+R<%s\*>,EU[/
-s!J'_=Kc(TI`5<3Km58pgoYIMe`G)Q'55Zo8,d2.Gd&*`Tm=?Z7>Um?Hokl5"JjJ-eCT7kh
<Fr5ejKh\+f#2TM9AmCtD8B9QIM#EXg]A#bQCsARcpVCo?(daS+B7bfbV1N3DjYYBI<2.8>;u
`5!j/OiPNKiGVn5Sf_)sF_t]AqV"9]A*Ek-9bd'-ud</GcG!*&:rLa75\r/H:PaY?^d0<s4!'o
k?/)r0^^l5HthGZj6\Q+<h#r,b,G<n"[l`hRWF%ga4uoiRub!@\hdhIPF.Tr<jMNMMR0h9>[
e5ITmtE0F2YSXEqE8X)pPMu,R?/Ap%QUhQM.JkaYs6?^:6<LPZ6<)nI3H\Ft`=,?a#Yp8t'(
4S&rWr/pi4@rH7$4TTJ4KY3U75HcoTnfZZ.]Ap!so4K-m;)$[q"TkE#ZOO#i8$Mh:2niBNP1&
jd*V9fA!7UhkmK63b.qTAaZ4pt^0>sg.\$Sr]A/V`E[+T&FmcW6?j#G4gQA2?YtbE"`V,[ob5
.Tob*^r'G3EZ:hT!GmW<giU:B&aUY4<^GYM6(8#:*d5+plicImL/Y4rj\(.P0-AC#QpJ.hee
:\Y.=Jb+mA$W(8!2Of(kj?$eAaQtQhoGk'GI>\@Y5'N]A$l>[S*@90jo%qBB"hC@>/#FeRh*V
!+0XN%r#f$_>1!S)LYAb<^/5]ANb!f9;Zd2p)TJU>hIep^k_9mYU)/+I(>n<7/QC$;<!@P.'`
VA",YU''JP6<(g_`qnQCtiEIprDYY$A\6G[(uC=-ssM^AOL_6K!,`"Gc^r!4`eb"?3@2b\qF
)$HG9d"d6CVYbLK'h$p9YD2-jQA8s;9!Y@dhjO9fQ0R,&TP5UpnVCC^*aZ.m5T,Pb/?Wklt?
l1F!3FPY;6)[?S2+oRG8`0MctN^Q/Lm0"mEOXULi*uG0.lL5^D<Z]Ao(D%W8i>Yc[Q^lN&7+6
K*biE7f`2)8HG4'4/C\pRoXMtEph&)OM4nerifRWBJ6^IrX2kC_&!3^-]A'n/220**L"S1V)e
HQfTl9VeCQMV1mt%!HEZE]A)/nW0=)cq##WbKkb:#;58.@dYe^`8VofYZk"t;U.+ReaO2M2N5
%(iqjB6`%*F%.`UH3]A/DhURI@'$O[YRWc8,D16u!>)Al#;+-:[,Xi7S6*kC_og^A6[<1RY,=
dL01$(oTjJ-:OMQ><)oaAkH^JpZL)n7=CRDL+ro%C=+hZV.$JuEh?Q+ZQ3qSZWH-8.g#:FF2
n!p=SB5&S@9UKtapA(9Y6aYeLXkXB;G.j4tOPOMX*GSh(!?RRH74R\B0<d6;XHuB.jW[0uAU
Eu0I-Zc@\GC8:5;Tb^;gpprSY[,P1C3o9h+"Y-nd?0EY:C:BJe_32@qhhpcJ"hadZ9EEF^B^
2Giqg#O?fB0$bP"N`#FK!EKO%5U8roP/a^nr+#A65SY(oSf0/oGSO;9UB?o6lh=iQ]A^^:g+D
F_mB]Ad(JYoP;u03)A@]AU.EkuOUQ011YY!oFqo(rdUVa3?@5R5&uC7iXEn+c.X#%dLY%T`IMM
K2b(NWX-g+5*GM"^$H>KOV8McfKmC[:OmF^P"2T"Xs/KR?9*60a3YrDF1-`]AZLIoa98KDB)E
Xb-7<od[>:rK?*XrNbMS?mM"I&`pHpEU/I"FDBnP[6B9!kK&sSFcBTGBCF\\DK#9/1TEJV>"
+rfWQNOt8DZ%!KEkGk@`KP=Ht.B?IfT~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[KmJ#,<:ATARDgm?1:E-iaPLAqM%n2W+_?lafge,t-m?eC6APuB@bO8:Ou5#G8n\PTCS[&RA4
1[f9RWMi\(C6I4jRJIk1o^=]AW_)&^9;lQ/7m.&@6kbs!!%[(bY@g5!"oG8LenKcJ!)GM'`n@
b_%rnYgU#MHp)QeI[S(8(4F"jBi/UbIi<Q.pcVD7BCKQ!]A5Y6b_&VElJS05d)0M"KN@tkh''
TIu?BeVu_DL"If*DNj`X(ttAX2?V87.`.Ydk9S-3G0=,qqNn#luW#;CXqYa`NUn7eNUYO_(4
.9VoIdNHoBW'Vto"Bcm.Kc2q@6cG:ZLW!5!Z*8kp3e$n#@8X=rcl\F([,i$5_BNe1=QqR%aO
Y5^kmeeC8PI*/0q;3W??b6rnIX=Z+:Ktc@_>@PbdGnSc*Hf<A=N,OI-r%8k_rGYP,L`!eM':
P2nDH`1HdlU&<#HJZ^gG%b^qZs\mpKoq!r<*tS1R\+r(`YE%>"T+HO0_dT&pF9*Vhd:;LjM#
6g$XDBA6\_%(Hej\H,t!L0?C89c)3XUL4W*M]A:.XqYlR(LKi#$&1EX]A@F'HL2!-dGY1e)8K>
*sZjB8e[E'(m.EH5'q@%-n!p<Y]AmBIkIbo"J7n>%EVeLpF)JKfnVdr8!;uWMn;/.A(7X9pjR
K"?ou+<6g9m%=\9_o+XI-"5c*K#-"m=iH>Im$p^g)Mcd%CI`/W=g1LWQ[\sBquIY_B_(+!DK
Znr#8@%uIUG(a*P1FRq_6WK$i.>M2<$DC3J168686@8+sorc:,=nh;j_'5%/90<HYH>#2!rA
$:a0#H($r+J:J6HrH^,ia4sp'uE,?)AO;p\p!WEgCCOdUl9UW9ni%k#tDtT`m!d!SNJV#VU7
\RNk`.r<18[Q3<Yg::,]A>:7o;9pqjIO41a"-[Qo>R2JI*^ah\,@PU/I4l_bmu6[-Kl30%L"p
XYtlZS#,]AiJSbb#MMUm?"h>9EN"9uo6Ffl#XoW83#Ve=Xm&'8Q+k(="Q=qrCVkBI`cONY6Dl
3)46@6fd9HBXXW#>3<@"EVXH=:(^ZcYqA<B]A\')&OCWDoH,7q7Dbh#N+Ge^HG_#&qM@7PN/&
]AG[Z'H5*c;a7jG,"BY&U,.f_[dBnf(mM>lMk]A)eE'L6:,a<$<`ot,X<fTM1:5m]A:<91'^GHT
p>Inl'#o#gMh2NRaTQM2u,qP"I<o4dua#,$GBeF1]AqC&GUGE*6H/@UPS8$J_5`$r-AIqRZ_=
RK5a/O57U$oWgQqmANAE1kTNl*#YdWVQ7o<EUm"q?9n?NDGrV>Fk#X&l+)9)>f4CPOEL)/j/
081d<]A3OX4Rac8CV\f=06`<tZdu0*+tp',c1.3U28g_8Vr3!-!V0:aYb>6A"*!3c_PGg-m:[
V/0thuLO/pP4^qrBH/g#bh6EZ,H@*Up0KKaSJ`=boO@1b.BZ!7gdS=J]AB;U9k7/\k6l-FfO0
7md9-Y;D'NcgCniUp_%u]AFN&3!P@!+3<g(O+6\3o0$g:D(4kM>a(\Z(^:6L>$HFrKE\(u2>S
cHJ;s7J%Q%aoO&d=cX:P)PVcnK[9F#c[pnV,7G<GabIgi,1dK>9^0ADl5"1n9YGmGRCQ['>@
U8'eO0#['V=2g,_eNQL#`M'H^rf0h<nlH4%^6-qj0iqHF9HIc8um/mO1"Q&%/>qEgrQ;U#tP
/IJ@bk2SD)G+WnHS:f*SdPTaQ)NpoiFm$$5\5f;<GdfOdm]APLmHXVRCJlr0ZWNQU@kXL,n[Z
6es+a%G:EQ<U7LW9pYjniY-$3Fh5N>mpqS&?$Coe6\ftNA<.C(4h4ERZN7PdZ:CDh'$L'e'?
D$DYrp>+aeESWf*/="5c0-Z,r@>GMs.<jmW=uDA:HAhJ8@7k:cnX2L^\/m=Ns/oc.`3l6Vq+
598<,Fd6e"bpOQi-I$SV>WaL7b2KJak!MCmr$;.!Orh,CTM@b@-`c=&[TM**k?iM#nq).n?(
flRG+@G&)Y:""f2u1.qdfT56h$bT`mkn\K`+Rm?R(p1AsL@m.'=N,XY6Ap$i-(s*$tKO@_P,
jT>^SWBJ&%]A-tr^/[7Moipse<p'uQ,(N+FX=?3l9!6fDH\$>LFqe5:^VUsuT=p"SP^n2@r2_
bBaMrk1?H8V'W0Pu*i-Yc<*0pCfFp)OZ4<Zjk,5gaB8IDOA&`,pLB`5CZa`fQu(@#$06`"aQ
FYfjf]A+sgK>3&fc`i)0ck()a()4?*"DPmJNrHfC,H+KU*0BupheQ3RR?"4bTWDo6\O[l;$d4
I(aDXiX,lt7Pr0b?_7oP:?"C<\f=_LeTj[Hk/UI'C5pNH@n2GVWi>3Cn9I`7+\+[juO#N`=9
AF`7f6k6XV\U*GL[jPaJY8O`hh6h"//8I>R@]A[j6hnpmQO!3^C4b&WX23fkqPkfY,TrsChDV
i=^0&q"78A!,:EbQThbE*!>4.rj-+n6?)0VZ`#A8e)a_Qr9^Xf%cL#q2XJZA)UFsKhuX([El
o.8/c("^fP+W*6%V'jtK4@)E."",+('cqA"Zt1V+OVoHN:M1VGIY)%!P;'c[IT0T#T01.;@+
.d)CFH^c:>*$2#K]A3'nScegu,F%3ZI+tk'b'Ji<L2?TO+*k,5aTEc)qnf4]AABk-.D;%6C)75
J.ihJ%W&h9%G,i8+JfYX3sR78>)D`N7G%aJ/PnkS,4^CN*GUH=W(G.S2p84H@6'!:G\]AYY1i
13\2(YkeuXMA^*giEfm/p#?0@l_`UI.^t;p9jQS(U0I%g'Tg9Y,*-0BF'e\$E52%HVPGf:P;
jUJ$<9d&8g[bZ:#hCnsOr*r$gmglfe3KIUB"\b5JM=i7+>`UjEV'0/PKa\kH$;RFZ7lVV&=5
YG3NHuno^Kf`I6XioreNqK[7#Zo9%Z@<ASUtb74Z`^pJ-qJnT$7$Mt"c.KV)o_Cjm7"NDfr"
.tZDpdBWc1.&Dl2f^f=niYEGH""tnaiR'#1dTN/\6;g5\[ESaQBSR5[l.LoVa0S_P6Yc\[ki
Rg6H8^luFGkkU!.:<h7ClsWe<UC.)\`"kc4S@pPY\f7.A@d^GcRTg%4]AH.`g!R9:O,n/@LHB
\ClaBQISP4A`ZOs"`;S]AK,7BM6iUQu1G0W&7C3/^'$rue'XI_u:acWFHSZeSp?[DJ`TE+nT>
HQX@e#mfH:"VH2*@\"$@D7`ALqba/$QFNm5kk&D$ioMH4FD-=9S?S3h6>a#3]A!GRC]Atpe03n
U/(BbO:3g+leJ`QOgfPI`ipk11BULrF?gG.;QmZ]AI,F:Y7\^"8m:@k;j7b0FP)[:)p:@so"!
KBfQ(9PL]A`eZ/]AhP^<Gm.A7rD&p`HdC2O+GDY%#F@GPppUl%j3"T"u1\*PX7X$$Lak,P<i9>
`!7?p"AOAR*p)?Hp9^g.E6?323q.c(?%*HZC&f?S[>of!*D_cl`k=dLGYZ=7,uD5,DN19%m7
j6@PCT9\iQ,T+?r3Zh.lDT7R&[_PWW5+IHu#[)S]A6\p$;OE(_ci=BsInK"epF[aXnKkm$bn;
2R?['X_e,56*gS&;\ETT\'Y50N^aJ8]A<[Eg(J)rgr9@\#k?W3Zl>PfF_ZB1!/-ItIZ%45f3O
JXO?2S=e5DlWOWN5\CQ3JuLGonn9!JfRP]AfZ5@-]AJU^cF-V"dAK0Y4=Qida)OFf$K);'"Iak
=6aB<Y]Ah5fHhJ5gQ,<(_j=G-@!F^j@o+P,4e$'V#n$rRn#D4=X9'N#'2R)b-@Yruj#&G+=R;
SCup8E-CgP(VZKO]A!o-=A+HG#!Ga)oYZl10[g4AmK0e-(LkRd3ZU2VbdZB-4=/2@:d=j7b3Q
_<5F25pC+m.f4&Q'&Q9hU\/etSf*a;'46YMb1%]An)X^[Z,6V<Ur1BD0GlMX"QK)f4';ojS[#
&Hl\aF-?h,O=Yh`^l@W8rp;q$X4UI<6B,!8\,T0Cdg/g$/d,3C^,TU;nJfb@r4jA[rI(p'4[
:h5#!LV2isblqH[i`]AqI9053;B$KEArT0LjMuO-AoQkkaY$CVB`1*fHaT-7,Q/Bl9<@j]AuO"
02R5?'\T`Yqap7Tb`Fg^C^s/Qc.F]Ak(6&BYHp">QEV(X6I_XWPi0dj\:qMd%Um!&n**KRVMu
[>P,g]A"UI,0X8iIm$K>\#(fE9ShV+;n)C8Y,Q4F3fPt<hkHsQs]AQ7hCEtE+=U3\,FhKHDqqu
1H?*1RG=:&V4,t@eMXb!j5laL?VaS;Zb/:,?WVFjp7,)Zk,QKO178^uPWPTr1IXng"PU%a=)
Lj^$+!4e[4n4_/E#`e)Id]A-@ZGV#2A6gWkZmS.9T`XLVQAW9p!3dQ&?(aFRr02E%r9='k:k4
*aPLmG7=F.E`(`!;%%NeRR_%b3]AjZn/U%FEbK5'rb&#m,Vc"X<%P6=DGapaA^E8]A*'^Y5r,E
!3,W]A(c6$%"YQb6:s8T%:(:Y5]A_.4/8h!V?%DJVA;2O33W@b;;lE47+B&'T^iV'H*..<9`R5
@q@\\Gq&V/oZ$I!c$13u(f%aG*ebqTTB&dB/>3V$BmscDuerlHW7p"YeY&Ye:muDVTW\X1*!
T<BT+9Ie#X-94U7g#L=f>TAf*lfPrcYXrZGZkFc(+#Nm!?IjlQ[%MV1rO/J&Xh.Sl\^Kj/@<
8MN19ack$+_!HH[@)8%C]AKZ@',P_9!$D=CM'hb_2Q9_Mi!<+\E>Oe-4BV[e['Akb)'+O*9@$
;j%gU^Zi"KUN1h_s.D)(]A'HrRf"BkTh^1\e5]Aos3W+*B0q1b3la:)Ve3-0:28#DR<0rnRUeF
0ht$hbda#c?,FQ3g&<l1eWX7.p^E"4i5l(?*^E&>4Y8?(5Ise5+`A*#+969H#gGt%]ApCM41k
f9qS@O^mU>a%''CPUl[=_"P1uN8/$>e3L!\[l#>h\BLPqBp"`[Jh0p))T+<QfEHfbY=/7Z$F
=La02I57ArU*cF%KSX$:Cq%aFsm-<NP#@DT:/[)e]Ag%!rj,X3YFD/K<#J\RoA.m3NMpo5UOL
$u="auI8nLqKot_G.f&H*0-1bu*J>0(6k)GuC'EQ\g>"hSZ+r@C&,M!G^b_LgWdSn(mcl[rQ
'!6a8!X2h0f9)NT06hioZ.s3]A>Kd(/tASaR#ja,3c_/r+ojN5sA7)u7&;A&.bi05e[_m&TmX
6M!C(^X:C0c!EJWD$8n$-"5&`LBFEA20tDoBeeC&En"P0",e;*rgSk^1K.Y#UU"(]AW\#T@iK
^JTl%sMJU,09E=;DXWEd!\p/6XGS,8QGP&RT`PEfXDB6j%5o>HT:PbD6%CI3FE97^gEKicIp
YlejWEjgutp%[,RoA3JPPEXaWQ\1p=^1f/\"Xq#[tD>9T41`SHh/Ik:DZD&%N@'A-=ZH<eYk
,pZrRJGEtgpikK>1Z<_Bb^e5<>"$r]AQcD&<:6B>cJm/AMVKN1(^0aaahOEOR29*,4Co'1,I>
,1n3fKq'"B&Y*>Esd"[?CkU-7=FNeqT$hAGTXO6b3q`F-cu`:Q=$<Y[TaUOmu`OX"m64k1GT
rN_e5F,:dDKKEs(@Ej3Lo_[Ud5C$?uA`&]A^!=k%,dpj+C3I3`qV76[hBE9@LY^(>#/.D@7qD
6L0JBX6#_c1M5X8hasi=R\Z(]A5K!iiac5_`eTYqI.S/Z.$;IZsO3QFUI-<Kd]Ag7Tu?\)/U$F
7IOu\"6?,0$<Rq.E]A5Y9q-C=".kpclrUg;!Ef##quad-c[cHh'6LDi60))j%\BP_'V^T',"n
02$]A:1lM8ETEPFZl**[LJt]A-r)^$_+ma]AWSLP-2q:TsU]Am2GP9d?_2TPG<=eonkUq"1h(g#V
Yh+]A6QO7!e#./fJ$8Ir4UKTI]AP;m>.V%B/uGK>q<PF\]AJ=+F3mRXi&-j(Km*WumT2-]A_O;EN
GtVdU+^9;""7/bW*RIf)aSW:8Op.0_lRW=7=\JS:BUt6:2=SL"@Xh4-H1'jZh!&YJViLY^$d
'^oUBo.DcWNG:#KP'f$F)r*ebMrU"^c0VJm'FF&Tf>IiH$H7$b,l>34^Co17iB(AAInZG6j)
,&#h%QW8!(0)e`ISqmq%f<W/DY_k-9Y+j&r*UQ\u>lnN.<K(AR6%-Ad0Io;$DRWF*O6jr,6O
m&fr$X=,/8H.R@%8MC:Z<62<`7"0dgsN;+X]AIQs\UYBo^;T8k[0.P>lPJ.U=)Wp7d1$fiTAY
X%i,%nTs"r)m-Zbo\^G`Q6f;Er+02Ok<"LXWdo#:U9:nWq.4mW`ooC,^,O4#Ls(t2,\bP.J&
Vmu,9:uK+CVE(J/R9M*.7rkQ:<;iPP]A%1?jG8u6JK3GAVf`kfEUL/:`k(S<<*Lb=o^;>.Ud:
^`l'b9?YcHm`doW%:`Wk>jsf^N*)2Hf<q(Fq^"E*kh/qX);m,$-A87s(flA5KI/5p(5oM`GV
\LXRj]A+s)N0htDPEaSQD@2nBf^URq%F@0_Ee(q:Ct=I(@C(/).3I+@S[Q`C`0p)71;DWkA/e
8*N83_pm38pI/0l@C'r0B+Y.;FIb`VZlu!gZhT*X$'$N8uFV7\:q2;\_j4ac?U@(SOp"gftk
p==.k,`*OfBLKO3[3\6$Zsn-jL_`^1#tJiDT2P<If-,.rNQH+N1ubl=SWr@snE%^*CZ1E-J7
J!ubuNtbjeqCS2!<-S&,,+m'7Ms<Dq/Mo_3O7RHiHBf/Q:lk'Vif_aI9;8.batIe1ao,G/@7
sA6$Tt1I`'SX/]ArU#)khi#"Y1c3CqOU^3rRF%+g^VSHJ[:W\3[(=/1%^d"MHCoIpKs`6B<td
'b9`aRX[GJgfCA]Ae3tD7^Nc3ZKE6Wt^:S+0%C,Jg2V6bk'1/WNNU(/6W3)4$;>u0st2Cd@t4
*#M\MI'2F`>c)g4H90SW:!(Me;NH_l[MY6dc!4NIqb9+T'dG5'i+ZY!`Scn%AHhrLusQ+s+k
rl@aIV^GhbOjon4:S`h.6NC"*a4a2Q#7d]AV9H*]Apk^+.3"2qc*F$RR$uNHEWcCGPi\*;ltV`
;r^Xr<Jm`QU0JU/s0mNSKtZN24?78_k+>[LS,3calLi+lbq'Y#o7u1\k%DecaP^.T4ON[Pl2
?+-9goPgF'mbq!e&C-Z1Mos?Je\hIui%Iqu>0@>m0Z4l>qmB4]A2J3\.0M;7^k,mY.:P>?>Qh
FIEk(!fa'MEpLm,Z"LIoAo0gnh&ci*cTMm\Dp)eG4q7d3Zb=:&81"8tn`#4144.W,2h>a!H5
G.FX?H&a\208*^=3Y/.^IO$s2-eT`PlD^KlKI,[G5b7L>b=PHor/do,#+UP7:,/?bMAQNT[n
==#'_j%9ET(;ghXE^ogH31?$c6`!Q;,A.nVMN">V1WTJHO;C1%]AFBU%f=r:16!U$<qc*^0oE
([2t0nSl(.#O,L*3%Cba56[B3HZO2[M)pIP*ESkOS2fc60G/]AcIF4qK#I=1`=S:A\]A$@-tdM
WRB53"hD4H]A1cLu8@u=gqF\_o@dt>\e,\f-5&Opt?P[bZ\9GFXqNQ1OmQc::j%0]AL;o@=I&F
LXO0_sraAr3:Uj2)lIF;3LP"@bb"",Cr2Rkej.u5k$ka['*^d4YOJ354_'PK*C]A(B#OpZFXY
Y>h4M71Pq7^oal6h0CNnu0Z,'U_M/_J.&*,&d:5AEP-?l<3L!G@BjD]AZd(TkGiUr[)Lk!q<2
99179Bg6U?lAVMbokdokt'D7Ra37D:e")0*r[M5C+B/><U(I4"0\^eBHH/1c/5K#(DeT`NVH
WZ;PD-uC18Ho#1`#V_^g-6kMK_)1ISP>@CsRrXWqmr&7ljaXIHam=Y?Q3tUH=dU5kYEf3F-H
s@N9K?'XU=59QH?o;^'e>"b-C`*EoRZ!2IkJr)U,mpSPUkVpSgLur;&CuEXU'<M/]AaGR;%/+
;'LIsB*&b:)j'-8TO]Au0,W,;[]A_Ei3SIOXp[-El:L%j%lGUZ+-b0R72VJS\3M(3GD:Ln)bWm
tn4hL>!NW>Ac`.&t(qXG>uB7@Bn8DXDp.D0EG;a(Y!8kV2(pb5oQ@C"kgGq:M;DF5#"_MU/t
VlJ*Io._bi19,+AGUNILf>bB&=M>gDkML?,q<JFE6&8+h;K6#lNQ)n>\3cQ5[6!;oa7+2l6;
k+jK@N*j_cAC3oO?#RZD@77Pn'Pc8Djnu0Yd^s$6_)d.=dm;<rZVAQRiPHr+fU96@!L@ER4>
7C8-[temM/D[nYDSp`m's!u)]AJBHfOSOOXptZjo5EC_`;HSh1DZqse'VELWqe5;'17^#VcdN
h\R/;V.P]ABD5?k(@^=BF3r^(/aPXS='`2)="H=c'gAonoXGB\4SYR;/2FV0=^aQ!c/G&1@Wo
@JcgNI'KAc*!&'q:OXRQB6iI+r<AAUeso6V$osJTl$rq0Rj:<q(hSN-;(^.hM#!D(d]A9Gf%<
n!>r00_;mP@]A^DDX-iInZ"#dqjM9N#ghm!:@1;R&jqI8=?6&ggHOLDui*JPg!A!1baVqOo)q
<*MRmqfVk??!B[TnWU#q/PAWm"r<Cs`+%+3)Z.:8qfTVo[Y:XKVQJC<&;*l:I#mr?^6c(q;9
aO#iXVr#+DIWAVK2BWA\WnUJ77&tc]ApG*+kmXG*%H$&lXs7)/AbIt(/=OgBQ&S&O6tb,HMi/
8PSbSXg\%piHr#(Q<5h=^=MP__>4+XrOW<ahc;**]Ajj]A4+m9SSnY=2t<)__p:qlmM`a!f:+D
]Ae,#)J!?.,f<<:2"clbJW7E2ONbs?k=,3q@oYQr"/9c^Oeafq6sDuGPo*+p:Gg]A;:Va>QA<3
%g+3GkFa6sH>6BP&8VSTGM+Bs=V/[mZSgO-%#H=KWqPnme:pho:l,HK2dX#Q_W0']ALa%IM+"
`QPNPd&cU-Pm+#IB>SOE/T173.=6/EkP1+>H2<k4@h?F&/8V5\l^"ChSe$rJpZo3-bP9G4fC
pB'Uh/Q=q1A,e8&t,`S['>E="3r1B;]A:H]A="s1=j%cUc]AWU`T3FK2dQ13F_Pp'Q]AYRF1PBHG
/-uC@@J)kXsP[*FucL98Dc*%SA^]A-91'+]A^I,+Wd?1+e$(b]AXBjh$[K6btK+R<%s\*>,EU[/
-s!J'_=Kc(TI`5<3Km58pgoYIMe`G)Q'55Zo8,d2.Gd&*`Tm=?Z7>Um?Hokl5"JjJ-eCT7kh
<Fr5ejKh\+f#2TM9AmCtD8B9QIM#EXg]A#bQCsARcpVCo?(daS+B7bfbV1N3DjYYBI<2.8>;u
`5!j/OiPNKiGVn5Sf_)sF_t]AqV"9]A*Ek-9bd'-ud</GcG!*&:rLa75\r/H:PaY?^d0<s4!'o
k?/)r0^^l5HthGZj6\Q+<h#r,b,G<n"[l`hRWF%ga4uoiRub!@\hdhIPF.Tr<jMNMMR0h9>[
e5ITmtE0F2YSXEqE8X)pPMu,R?/Ap%QUhQM.JkaYs6?^:6<LPZ6<)nI3H\Ft`=,?a#Yp8t'(
4S&rWr/pi4@rH7$4TTJ4KY3U75HcoTnfZZ.]Ap!so4K-m;)$[q"TkE#ZOO#i8$Mh:2niBNP1&
jd*V9fA!7UhkmK63b.qTAaZ4pt^0>sg.\$Sr]A/V`E[+T&FmcW6?j#G4gQA2?YtbE"`V,[ob5
.Tob*^r'G3EZ:hT!GmW<giU:B&aUY4<^GYM6(8#:*d5+plicImL/Y4rj\(.P0-AC#QpJ.hee
:\Y.=Jb+mA$W(8!2Of(kj?$eAaQtQhoGk'GI>\@Y5'N]A$l>[S*@90jo%qBB"hC@>/#FeRh*V
!+0XN%r#f$_>1!S)LYAb<^/5]ANb!f9;Zd2p)TJU>hIep^k_9mYU)/+I(>n<7/QC$;<!@P.'`
VA",YU''JP6<(g_`qnQCtiEIprDYY$A\6G[(uC=-ssM^AOL_6K!,`"Gc^r!4`eb"?3@2b\qF
)$HG9d"d6CVYbLK'h$p9YD2-jQA8s;9!Y@dhjO9fQ0R,&TP5UpnVCC^*aZ.m5T,Pb/?Wklt?
l1F!3FPY;6)[?S2+oRG8`0MctN^Q/Lm0"mEOXULi*uG0.lL5^D<Z]Ao(D%W8i>Yc[Q^lN&7+6
K*biE7f`2)8HG4'4/C\pRoXMtEph&)OM4nerifRWBJ6^IrX2kC_&!3^-]A'n/220**L"S1V)e
HQfTl9VeCQMV1mt%!HEZE]A)/nW0=)cq##WbKkb:#;58.@dYe^`8VofYZk"t;U.+ReaO2M2N5
%(iqjB6`%*F%.`UH3]A/DhURI@'$O[YRWc8,D16u!>)Al#;+-:[,Xi7S6*kC_og^A6[<1RY,=
dL01$(oTjJ-:OMQ><)oaAkH^JpZL)n7=CRDL+ro%C=+hZV.$JuEh?Q+ZQ3qSZWH-8.g#:FF2
n!p=SB5&S@9UKtapA(9Y6aYeLXkXB;G.j4tOPOMX*GSh(!?RRH74R\B0<d6;XHuB.jW[0uAU
Eu0I-Zc@\GC8:5;Tb^;gpprSY[,P1C3o9h+"Y-nd?0EY:C:BJe_32@qhhpcJ"hadZ9EEF^B^
2Giqg#O?fB0$bP"N`#FK!EKO%5U8roP/a^nr+#A65SY(oSf0/oGSO;9UB?o6lh=iQ]A^^:g+D
F_mB]Ad(JYoP;u03)A@]AU.EkuOUQ011YY!oFqo(rdUVa3?@5R5&uC7iXEn+c.X#%dLY%T`IMM
K2b(NWX-g+5*GM"^$H>KOV8McfKmC[:OmF^P"2T"Xs/KR?9*60a3YrDF1-`]AZLIoa98KDB)E
Xb-7<od[>:rK?*XrNbMS?mM"I&`pHpEU/I"FDBnP[6B9!kK&sSFcBTGBCF\\DK#9/1TEJV>"
+rfWQNOt8DZ%!KEkGk@`KP=Ht.B?IfT~
]]></IM>
</FineImage>
</Background>
<FormElementCase>
<ReportPageAttr>
<HR/>
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
<C c="0" r="0">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9G'=;b%Uq&-P:MX_uZ]A-2,^:h/Y!RY+e0PXKGM_9#&pf5iSqiZ,RD&=<N/?fX5C>D!YkQH>
4S%Y$/;h8.f]Ah,d^RVdO#![p]A3tb^dNfCH%]AgnQXjI%9&%U[m&&N@B_"[=iP!@gRJVI-2ns]A
P*a>7@S/rD*0bOHt[G]A_`0)uP5!$Ggqa>!P7aFs+4!&>':QoF3)@W?VToD)iBVm?!5(\`B_T
Qk)(1\m*Zff'^ah[(K&$DQ.rr9@Y$^8S!*a[\29hgI0O7ef3ok;hXj\sg#8[G'Tjo.^GqXQ[
[UqGW94fQ0c*BVHtdPEQ@FboJkC_C?k[j\Rm]Agd[@(70?SCjK1X_E9A%'as_R)LEl%]AUlV"e
8aRt3JnTjtFn@sA2[f3f'[6cE$7s1K#sN6q4-j%$J`J?Kl3EXO\>`EV(s[]AL'/G#a[>nW%43
9Okl[mi%l?(ld5h:&mf_[h,Vo6sTW^PDs$Vd78[3nq2@MuK'Eg\]AVW]A^V6R4m%(Wm?hEWd<(
gX6`mY%ZUD'F_o&WFTj#5N#BuKIMM)MG_tbGDgug'26?cj%AOfSB=k+uiN?n(YH>(CON2#4W
VsKp:n(bde>\BR_Z[fcFQj:dd0+n-:K^aNQf08r*Q<3A8g`t]A_VB:Z[Y:R]A54/5QGc58AFZZ
'/ROZ1MT'B#-HAFMKc=#@Ae2oL*]AunF-<n^!12UQADA&`?F/pM68[<4XG1o!r4fSSj[3mkXb
d^XHr/O&[<5@J(jT5AS<a,LtE;'(VS%1U3)6h`.HY&(G:1hnQ,<;Zh;<E\XPVpFmCAr^rDCY
J,5oM^l;3?9LeX`!MU^e%1teCU`2j$,f^X(4.EP*;1WF+oD,<_H>XAY*J!d^lcTrO(pdM+m@
d0cfI6\g!qr&*MpbeH9jYR)=Z@k7@@TGh*&GOqd^#]AqXYrIBC^c@91OkALDB^pUX`&QR0bYJ
t<;Hg_b?,]AWNjNd\V1=s$39Xc#0fXnf)oogGq.4XrSG[J]A#%n_m!`UGA*;RH24``.bq,C=aY
j[2n3Cg1oc_TVl2>T82.#NP]A=m2+60RZQhNo"g-%t^0qs^^1`b?FBPuD/`l4h8S34+FOX':2
T0;dUe9QDsB%)E)kN9Bc2f'fH)@mcH:-'gFKU,R'EEbRh@^]AmWanpcIH@!nJmO^jTpDRu*cI
,W<WiCGM3!YK/T)otdq;3eN$6903j'"BEm:2(W'-N:3=5#(3+!c`uQ/MK>:Qq:snc8Kr4BFL
\1Vs"2'*QDmg<BbpY\sT@Q[?jm^&rS^2cu[Re43n)MXWmFeI;;mY^(1TbTKTZEPu7I"S<Emm
CmknIaK"\D]AZIcKA.Bu(b(sdoCP.0NILSXe3N!,+]A76:F]A`1-;sh87OrG8bXJk%NGdK!NG([
-k0'5kKP(llh(O_kE?[[Xj1/o/r`>eAr^49'R>'&G+C!dS=7he>"PZLkJ543If?5.-Gk25u>
N:(rT+$N'cNXpO03#M-K(Fd*G92SNaaDi`Y%tnR-G@)Vb/Ph_/c^3AL^=ULh5rmBVfY&HK]A1
j"sL3CnW7Pm_J/lT+RZM"\sqB4a)1#5Scs#@C)IM?a]A_HPk2mW8$M`N@fek\7cC[44TLo1-U
a^)p/'ILFP-jc.L+VEpY(1IV_G.0ON$-YZFNQ\5a%K`4>ldoWh91b7^M:Pp#t31=^.1pRU*<
VWqES:f"\ab_9\/tn^$J6h$F8/aiX7pZo'#[pQ`?c9tfSc+XrSg"N$mrG%:#V3DXhI<puW+m
_T+^gMd9q84^qe;\>\;POoC/-R=XCJ^OiRFnKM;cl=_kau&C[B$eBIW/_e^5'tZ<)#HV[S8K
PAAr>@Q,G,%&)k)d^A=m5H"!;bg0M9QOFB3A+21rk"N!S4E1n]ARPJAEaDbQrQq:i-5\;Q^d/
.%TQO!H$Z2GjIol!f8,U$XQVoR3pA@!9XakR7Wj/H[!m#H1"]AT!;_I,Yal):jfE!`iBI6:2`
-GXAA;L^]Aq'<S20#"^Pl1Z2L8S(->MeBV>=[LFE@BdT[;tl0=P,>@:7NbGAK'OV8rM_$JdtG
9tdZ>+5Np2kH+K`t=gI,M_-1#>KOO,>c76pj`Ku2`uJ0/.rRcf4GS0,EG:H".eS9U877XPeG
W/ikZTHXdh.OKETf#OU>,_'mU-_5@QTo3t+p+QXjC7T54uu$s^iK-G+?e6Pd\VY&C%#(Q0G*
<@pOoR`Fl#5ou7+$>W8r&=UI3=JNC!5%gmn,JK^",PFr$]A3)gP)]Au6k(p)MKZOf_,&]AlTaQa
OVTms]A(aT_%?`ge,\#Vu8IYjWKffHeqI#<6*GmTt9A1$64lW$Ep./59b>C:aLcOG\j3qR@f)
S6$QNF=#FWa%f*Lqik&c84ajP^dGr`8*A/aoRUek974i9K3u4$lj%63)XVdnVNn;F,N,0=p.
(kWTTMPKdHG7qLOT'FpAR%N[-Os_L\(I:GPj1P/-IR-VLMKCWe<N>)XKn^dL.SBMJ)"*kTq]A
r"DK'&Y31ppA)m>J[bp?DD[2ap8lEAEM.a&ET16.8g0H7Um39)XG7g83u_]AejSL1Dp@lY-O*
%S=R]Ad$iYacV'X,'Xh4bm!kE0E$=^'<!]A.n2hY#LEJgimqV*"&Q;k#=((i:7PuQB;h[*bS\?
d38=9hhHab*<Zf=dk-'.R@D'TiQAao>/'ctoDeGQn9(KF1<qcE[cN'([j\0270sAqdb5IO,>
$;"YTD&L_k:]A58s5)ZJk:]A>PpFmAm!J([7=W@S7AD3.\I%F5bY4QaaQ(J8Bsn8SO@PJ/Uf!O
2ZWZY9B<,TUk;oGG%9i77r,1.W*j5"tjJ-->XYOf17J</'-2WKr;O"BtQ2.<(#,T=GD[Oq,(
jH)8(2*@X\;LpC)1Xp\Rci,+!i:34a8CEK1qbLiYSLV*F1Xi<o76mbK:jraiST=ir2j,'_+;
3#pLYKDHR?4'o*jUNIc1ClZfE&kO!RKKsMu6R*=\?_f!bK-ANe3Y0C.hSW'%&8dt/_A@mp^d
?f@G]AKW\3VmMDF@ceUHs"5?D%l'Yd,$VCGp`N'L5tLW-O?_YLEqrn;Qd%73FHCe3M8PH;uAa
-@6e`MjI=ZGnWb$M7)fH^ftg:hmu_3=774UIc/F0o+K6(^!mN)3Gb`PrmhVXJ1R/fjgs>@*_
<PC3qY.a+s#apZTsgd(WcZa\(b)J:(F66`,kF*<3DAK&?"4A]AX4RMVO7ujm*-,R14NL#@U;O
YLq6Z]AKr<RMCffF6kCKh^RbY8P6k">;OTR=7[1gR]An[]Ag_Xo:Fec*q<!+8=>/P-8eAO?[>P(
laW*'Ql:1P#FmPMfaW1P>Fea]ANXbRUE8^DHJQ(,HKB#'/8qBE(`Hnhu7;.Y9Ck)ob[=DbtN;
@g:CfYFE56fe*I1n@!<^EPcZ:ISTr1q1=7^BR)m\tbtG8=#TNi69:n,F5+V=KDkJZ7Q!pA:2
d=A`IB[+#Odi=3:(hlTV>n?i;[LdkSgh7>0QLN=pJcC9n@J(aqD1WAcp2r0DTG*B2^0:ml3S
gl.@f//fs%fd(IR>.ot!s\h*&jDPsYb"Q[]A,>>U"!:\rk]AF&f(%NP[\1TjL-k]A?"LRAk6G"a
GY-1sD0$&b?r,F1.q:e)(@j.m"jc:3tB2KT83E%Obn:b,F5oGN;Yo#!c'diK8u't)CbK-#JR
b.(o/XtYQ0[LR99IL<X3_*p49Y`d"aKiOgB6h_MdO6]AnZ4gqZH0ZNcP4jBB"YO.*<KiNQ16?
U4:"AB8Z:^?;On1F<AT\M!Dhn>k(@E[4"n<C$'G1CaI0jW(R?-E-PWT]A+.CIV5d1oseXB3D2
GI>2A/,*?CEF^.q5PK\N0]A_uLiobgG#Y`2OCfF;(C"`IKM;)=/H[YUWANs8I@UY<l_>?d4K=
,JD@<,VW`T4UCe]AdgREWQEH(MIEE`9KVcl+>]Af=i[j0*3W(XT(<gj6U$01P]ADh<^Xr3gdSsV
D'fMQ@BldG[OZIm,LFLFXcN]A;)ZnOf^M/*V-!Z-X.BjBq13Wn>H8CO06l8A'"EdWZL6kr_Hk
Dnl2$O)?')klujf%Q==&mB!&65k:]A-n*>>0ALT'9d[2,F\p(d"EfqRA5=T\VJjg>IYOF8Cmt
JFo9pU^HY1XK9aOPdToC4*2IMPS%iR58#4gpW(WkK8#/\eY[q8K#@g@?^sATY\1+^):9YSS$
U=^j654$9'hUCRr`L;+<[\o/hV@9rCo^#D3-]AJ5H@!<--jkJd%mT38_hhZ~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="36" width="250" height="114"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report3"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[店铺名称      营业额    同比    毛利     同比]]></O>
<FRFont name="黑体" style="0" size="128" foreground="-16711681"/>
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
<![CDATA[0,1066800,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[14020800,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O t="DSColumn">
<Attributes dsName="File3" columnName="地级市"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[="<div id=\"demo\" style=\"height:250px;overflow:hidden;\"> <div id=\"indemo\" style=\"height:200%;\"> <div id=\"demo1\"> <br />" + REPLACE(A1, ",", "<br /><br />") + "<br /><br /> </div> <div id=\"demo2\"></div> </div> </div>"]]></Attributes>
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
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="黑体" style="0" size="96" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=@.;cgQe1"9l`1pn?"lR=)C_g1OknPjV/-qGt'g1cST80-FCbb2llCGV_p=p)`KGH@>r@k
js*Ag.<0FQbtO<`;X;%@73YX^J\U/4.9?U?.PGJ.T$(-C:9n*BVa7-#:iJo[s.n+$:(8[F\a
N>OV<8n+WrlJ,A;=_4j$km7mq=(2a!<7t1q5$#0a7pR;?Xao<noH!h3eA\YqKdJnN-J,.a4Q
G%RI]A_5+'k51u>IegC2fH/]AIkuepN*=a(>6&VqFR>"(b*VME5Xo2&5*T%2&cYGTE[9IkJr+W
+Ski&MNnd#m$8!iTk$(N*:\!XI_lKmr-d;J;P_2":'\62@.'07,'+7VaU.(:<r?*\&F/K\Ua
kJuhcV8'V(_XZgT?qA$*k.a+3j6AIF1&o,h4"g.oBOa2t4nR>I[a9>VSAR*ZM"+7.(P[qB..
@X3c]AkMVG*Xd&Uq&#g[j<6`N*N'aL6"l!>Os(Rp\W\A->?sUHu+2V]A<@tm]AoP;fgf#=UHNpU
$/GS'GV<X4Wg3g/or9r3%W2FO-EfneamZNdc'.P/4a4/IC%G[nq:;>3jMDA_GOH"LOe'T$2b
H0)I=kco(Q^tbZWZG&mM-\+71\\!-_($8`p>fD?/nFBF;aETNKNE-1CO_bACPT+IUAi#VPuh
>Tg.jJrI3&8L+k`'S';kS"=*oG-M&e>\'L>Y*QLBp7P#R;'p0<"]A]A/blB*j/_CXBR42r<T\A
;6P[*-b.0Jh,D#JO:PG-c<Zp$PFn=7*RPSVR=f`SafXdi/`r+MNuoN8;-#`h6oeF5%5U^I]A#
,t8Ag+TtO1=mZh;g9=?WbF;F(A3j8`0\beDb3ONis='<pQShWFenf[ejkeeIRO-]A@/GeVcD3
dCIp#`ORG;7E24!8i[:Q/YHC1BMmXfK7S\,5?uG-c7ophu,[Z5[Xtk%ZV"oKQI.&6?kq>4Z&
<&55;O<arG2A4E.WYJ&MqH9sWp!)'(F`-iVN+K]Ag`ah>.T"^hFZFS7Nhg)]Aa3L1nZ>g;8Un'
AKm;`i5Nb\Lb!bJ1j,Ft8+>NrKW)=MZp&SS=Ns.tKkai.),MJQ9TF#ercdm!p00M6DOqI"oW
K1DYE92nCkojR.\=HrHd2"JJG2&F-]A-.;&AMdnL:_jsS>.-pIHr1<F/mbu?!ndgT3)ks$tJe
51g0'B\)'$>9@LgN"?(##RRPD69PFZ5MF/*;+]A=00tY+6L-&-"NN`6`AQ6^AeE^@C1lfPj>M
Kq0XXp&"?_?=u9,0,5R*HHWmp"5?9(Pp\I"*8U0(OYmKi#@uJ\X?X@4_r_j,fiOd`cb1ZCn<
&e_hQ^+h4%t%U6;Rq7dPYmi]A0o/qHQBEraUS/_.]ACWN`_!;7d:s=KFf6X7g*;I1aTl"&AR,:
dHX5P^7pq:ZYDk_Q_UPj]A]A:Z.Yu*@`q^D/LZ^O.JNpOK.RWj@?75lNDfc;:5t`UI4lgTYsGZ
3:%,.mLDi%eXD(<']A&H4?0S20@BI;qAf`!2o-$2Jp\[3NqpA*<41Y[26$&l?&8+=$8^A]A_om
"h=7<@,oT6k2`BX"u)KW[ru4uSiCSs>`7:kMb.D<8jIiU?Yr@No17oT10Dr]A.MP<5U&K,o)i
="ng03T$\@OM4\G9a_Da[1ZGR3bNE+_cUXmlra+>CO#oK]Ae`5-u77#,dR):1NFc+%(PMaX`A
BuE-,1SMBZX<&ur,Fs4ih2+7[T/3=1.#l*:g"B$^t[6`I,+?A<MXOdD@W;_E.hQU:<>]A2KNe
:cU,DQt8).-[bt3IL#6skr0j0nk+&@mgZ_b>'CVrOP'NE_0c'GQMhp&$,4Rpe/mX-<+_hsVG
qQY`<>B[PR$HYVK("*,"=?;NuQ.V=TS,9ZSX/nq[?$87ci7f)@AlNLa3?fe7#7[itaWOa9Xn
Q^8bSMPhabPY/2F'k(+d]A?blF3pR[_FAb8$oFD&+OQ(?HM7Io;A3-nRcb++1ZVg#@lfba.+o
3.=P=5>U=E(H2F7\^BADt_HDqDAeK9_p,ZO@JJ"WT/QMZmMb6A*3]A#'q-N$d..?Gc7N(R?*a
>J>jYjd9C_0qZF;m9n'Q<uPDI-DWkU>l+;)W<+P>!0YmPo/V=#=HcX6Qf:VmuqJPF]AH_':TE
<S=2OhhJ$ul>D_h=!8MaOHDV'q1#CKIEW2@&Kl7%hVFZ*:qSc2[@qbE48;J9RO&-!mmD?8`L
]AS05))\?tTa^93&e)AbB:N=>.ai_aJ5AK!@p'ckmb=D_"#A)Bso^Ku+n;.:g+^'k$m<-R+[U
d7Y$.s0)%=@$[Q&@KNQ2#1$NDV.VOtd,l+(S@=Ll[=#jh7h3gPMn?/nZ_:)`iMQ)PqY3!bG'
>5NQ7.Kb;NGrIZ71OtGeJOH5s!N8;;4"rmrc=r-L6A^4:4pun@Ad6@*DE\*th-cLa'P?h;\W
j\$TVb.s_c0-2!.a(@)i`g.]A1Nh-.D>o2F]AH@-*.%Hk0!hQc[k(6UiK5=l(/9m.""V#ZTrdc
F1C7<js(RbXZVmf>N99E_i]Ah+8Y."`MEc/Xs^>D"6=6+thC2Wm*M%=c0-E$iI@+Q*,C8ZgF_
=kC>+_O]AB;&I*ikFi?0uZ^*0hZt:RTq6::EjB@7K&G7Y=oNF"bVkY,>HHmHEdVU'-bp"DrVZ
/WIJiD?T]AO=#`)"[r;$IqVSVQnVher^eI7-fHlf'(S-ZR6qEqC!\X+*46;^"@8DRukfE,%V$
]Alr/.0R#L`0Sn6uIF't=;P?$5M:FUc5Nf4>'C&tsS-f(qFN&/gpN]Atj2YY)qB9lH%)dk.<5I
/cD1DYr@ZUIAe@[F+q=KYW5Jd6o\TVs,JQ4]AbRW`;$BiH4Q(fi882]A7I0J7Z18]AMiV%e;M9C
&1N9i#!2N+?(eEGJ?!YoGX[(="*i'lCM0$8F%1US1i7FNQ$cTEhp<ZH!4j.;qjN62pO)g`Ee
!hiU.Y4k#:gu#uERE'/u,=T6oVqc]A:U<5H1f58$k_!8?'dj1if.a^YlMO<WQD&Q9+O-(3KOV
;7RPY$E9i?j\IQ!)s)=B!"f#%U;[%.a-N4RqsT:#RS,6_sLN$?^E<k(&BP)@I.RoI$+:R8[f
,Co<XK2q0W_/Ij_.!ED\f!:h"EZS8t6m_<MA\&q^30FTgbTttqPM"c5.Y&TZske(eWKtU7NF
DWW-X@Lp[0fWGB)e>)FLrfRN._Mt9E)IFi)`8mhUXu]AWqbV?\*thb7o:$rfN*J@u*\$'U(ps
tEpF:%M;5:GE>4:@r*\l4B$R=Kt;*&O2ffUP9&:'"07dU5?Wf-3`P=XbAa9HhGYU1`i\4HUI
gaCc,p5,oq5PE.J4>o5WM'T)_^"u\-dYrkAf#Z]A$m;d$#M/)8R5"@`qO_cke0-H]AC_h"K1:r
AX>]AGZli3i"HAE\;SIfL>)Ze4dTnb`1@lE^N3`R\:"&\i=#6["JsTN-W,oZg)@r]AnNm[I?YS
),<]AZ\liK>fUGDp/?j6"E8%s`b27MJ3M6uoeU#`]AkHQJWbr>Bic>ig@VCYEN(7_QN6aeJ/]A;
/u2/r]A1h^QFCroo.=RT=6W&OO/=)!4motRVg!@]AB1RPI(;F?.j*jEgYSkWXa5ZNj,^@2hSch
97jB0Tb!qLWEj^;?2P]ANNFUpc9aD1=J3g`oHn"((B\>'FA#!?$jD\/(T2-$2"qa.:0jY(p\k
hn:o=J:2U+_]A53R[%$3]Ar[Ss\eefITI[ui.c8R'YgP2U7l+?$goIOBW+mdHn)Kblm5oAVh$F
Z).QLN.X]A:5ps\;OlI$ZpJu/(e&a$hl*;,fF#GCPU+>.^&j$g[hnnjqKE^QmQ!5;<M-UB?X0
r*S@!'>Bm-^k[m;IMI0dXlI,GKC*%*MK_m&%=2A2-L\aAV,"AfK'dosq$h&"WS<(F7lq^hM"
B#&2#11]A4_/EQMcJosL_B^1pR9H=__m)4RLhiLJRfpQnoR]AHMlN/ITU+)>s0oaXeqE_Inpc'
!44&#F.jQu:(f1ZTiG3f4Wi%-_:f.&2=;r]A#QJsPk[G'p/+I.c>"[go<=bkJ<k$4b]A[C/1[R
Zs5``^hip>VZsu@@55M9B(l?%B',]A8[ql%"5_'-mZ.;JkQ1sRtYKM.)<cI9@%QLO,fh2:'"t
gn)*YEdj>k@uc5YrrZ$/u3Y!/1V<i1R]A`0QNIG;$^kKB\lDo^euVgL)(+/qe)@9;]Al'Yc<o!
p/+g:V0iBitN,(>OANr`r&`UFR)WF.?d!:_mCJP6.<G[R*\:LO(&q6qG`qTbW)3PZHo1C1g7
@WP!K)!MhS-r<A64_tNq1!]Aq.QL6#>B\]Af^6!_h@alZ&BPL>rk8s:1!;#ZY_\18E'8Q4*fa'
Q/c^a_=44IL(*Fm#*B5dMO0VTl;_16hA#s]Abh6U*W&*Ejrn.g"tG;GF$=V\4Wg:HVcSmS[RM
+S"'_/#;kQn/`TZnjOI'/k$#g6L:W.AgYa'U`d&^>M,rP>>7PZk*cml:7P6HV^hb=G+IdL(V
jD2MG3KiJ-fZL`RH047MW@1VBH&U0N^T1B.OkERm20V^=d,'=u*L"?$B]AUb_@Vj$JCUTS71q
2@9Hje9b]Ae3rT.;$&Q_S:6JGNYKl?2F2W0=n86Kl-USj9]A3M'.`]A<6k]AOeV=p!&O7"6VT+Wh
$-PZa0eKRaLhj&3?a?UR+c#;2]Al"s,$&S?R[L>d_!_cjX_34n/[m);Jp5;3#XnrgWQp!/rYo
Q.m_)>7HSL_fCE"&[<4nbes#F[C)f:lT:.i@=`RgBVVT<BOP<*h!3;$7pr2"uO:2#k(0K,)n
[)^p5B%Km6W0PahXRs&T<SW[`T$1,_?to:p)YJ>.'mUWRR:>626Af5a5I$k:<?%0SMrVNC>,
tCm"/H:<Y"r9^=2oX>;f:`u-Z3qgjh]Aa7]AujnF$^sM*pm;LSVEFU@\DuVa"+"'m^:5t=J*a-
*6/_!]ALakA]Ae/8utL*UU3>+&Z:6V)"^B:hO>MDN3nc6Y!uBX0D'W>WYLk8BPj800`V&-?tWA
Hk4D5Q;Vs)-%]A"GH3cb;NSh<e]AI#$Xi!UC6'd.!]AG3&tE29TR$orKm`Ad(W<+dlV/e70>cmY
BP.C,t@$LM0ZCA0c5JO)G=fJf//`WlK:]Ah6nrPDkGA")G<)]A/g>)[qZoOcCu^n@qKi9P"Du:
43iD1Y^d=t;dm0Q^YQ1,]A@7:MA*F+d]ApCN?>B"G'HsY*ORZLAq"Y8/k(Li!,gG\RH`i#_H=C
Wi5Id7F.4j6SSXDi[X@;rB3=U:Qp.DTuAH$O,j&1ssd"Lu>^G?=-Gc<3CAB&*L<OAlW@g2;Y
5Q#\-BOc2*l*g+ZAOgCDbGnnSF-R`t5eXL4<BXotp%%)ct0;^'FDi>TIiG7X]A*a58L2'tQgK
oGZDCPj:8U\=?LK3``!&klah-IM$We-N%IlJOK@nmu^2'.!,1Hh#S&f1r=d0Z=/Ef8o#jgq@
-s2>In[?md7Ono0aKFp#c'ItU2`Puf9PMPZ33pb88\WalGIO:b+j#\b&Df;Y2J^]A?u5^f*7b
<XPj2G9HIG\P*4cA%K*W`8hG-S6;*4=,sahN4+hC#^*V&3pNBMHIoNeHGL'3Y4()g\VI1j4H
IAt-bu-/3pNDIXu;0$!9bj`k)J3jm"ca`PkVbr=6AeFnSM?@g);C\rKRUj#8S9WNV5iu=?,N
XFO&Pr$V*iCl?Fh5[XfW'1g]AL7rHm-FWlkCIJ9/fY9Hn0ai*ojuMVUihBhM@d.1\Tj#N&3rn
4*@t,nXl9C&GS2@cWsU%Z:0sJFRhuW0/)I;E:AVJBOmSrT?dU$Y,eE!'WN$@7?7eY,+06*3/
`LK%$ME#ZF_b.;Jh[Gd`PeVbO+sa^,HCq!9mg<d@#^Uo*&a8%=U6.]A]AN9ME3ENO:8`hFe4*^
Y"f#ZJcGPu(RI<I`>.M@MiVqS3cNo1V7qN%Mnu<KrC`]A&eTXhQ@G<7P4OQuIYD2,';--N$A`
kWBD6oCNo9:=]A"%#N:O>QU6(<dMWAUHD6"'cH6#NALW<*r=]Aak\B*_]AR*6_V\<>N(peX+r9Y
PVIr4u%))2XB4\JiFF<$%j2lN$c+asLnt);o;nTmF]A<;I?4-ooI2t>."00B0l[`a/BD2sft=
V?<"n22V&nok)f/r$Znd*gHZk>"hSWPSiuquU\mn\[QQ\!Lhd3i`i_b-djs&q8frgBFFioj$
ZM:'thnHA<mnGhFMI>>Y(YS`7<Wq_pf^9g>0]A/+CS4cXtIscBrW[5(JGho3%_9Vs9;I/`5D'
I7p'a(<Z%ZHsWAb#8c5LFn0o(P"OAmJcedBb0KMZ::ES9Gl8ggHVCQs??GpkJ4F=p9f/e_iV
6uDfrUJS]AZ2np8Yrr/W1%X*Yp-lGF4Peemgu>Yk<&&"546)0S_Z7HaM(qm2k%RNns)a(`S&7
*+bCip]ARY+:G#BfGj/\.D7]A!2aD<5pHD2UJ91Qg&7kFc3)As3!I$g*$C>pQYRdR/"C.3uK:;
8"YWFZ7hos&S#OrT:XX1@1cD"mi*'nPOOFRmC>B,U6["58HI<i]An5Q2hkfl+T;l8B-GNBHof
'"^`Uk*ZHSeeo@mI[)'&@2'.]ATpTDLT+d,_l'+3+l=>jF7\H3fj9ZA!/"'/S)*DYCaO6:*_$
$U4d(4@15`(5^a95*<jO.n0XKatRjEqt!,Jc!2nuI+s-6jT%4r7M.7t=X/N_Zalc"+nB:+O#
B4KqM3g4r'(~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="4" y="63" width="288" height="200"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="report5"/>
<Widget widgetName="report1"/>
<Widget widgetName="report3_c_c"/>
<Widget widgetName="chart0"/>
<Widget widgetName="report3"/>
<Widget widgetName="absolute1"/>
<Widget widgetName="absolute0_c"/>
<Widget widgetName="absolute0_c_c_c"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1536" absoluteResolutionScaleH="864"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="1.0"/>
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
<TemplateIdAttMark TemplateId="2788a2af-6e9c-40c3-8a50-5e97d2420ce7"/>
</TemplateIdAttMark>
</Form>
