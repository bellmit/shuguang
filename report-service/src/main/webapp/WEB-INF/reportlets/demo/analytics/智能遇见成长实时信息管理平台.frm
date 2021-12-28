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
<![CDATA[select 0.83 x,120 z, "tt" xl]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT *,"辽宁省" qi FROM 地图1]]></Query>
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
<![CDATA[SELECT  * FROM 订单 limit 100]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds4" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 货主名称,sum(运货费) 求和 FROM 订单
group by 货主名称
ORDER BY RANDOM()
limit 20]]></Query>
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
<FRFont name="Arial" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ImageBackground" layout="0">
<FineImage fm="png">
<IM>
<![CDATA['pj2*<:(XFc^:%jQ79Mu)#R35Z=M,q)LHXjI8@:C82JHu18I>n[ZiX[b7ZP_aV:1&@0gF?,\
,_+p>>+>fD4HUY&hm0T)3g$n+UnD=]AlS]ASJ4*k!!&AU<W-\5!8.ElhujuY@PAMn!!#b1)!gK
ma(I&$Hl6+&^p.#WogA5Pcmj7la<.*F567"^OG,=G!kZ3i';O!d*YADmX:='*-/ucOD.-mn:
m0J'Ot2;LF&CHTUD\dGmDS%;guAO+omklA2_O]AgYBa@#!7rU&@-U=di@Zb<(Z]A"/gL4V1EMA
A#9Y46</.af<I.6;$4J8+^5q"YH9s[!pnBFHZa<qqiYjJ*a\djD9E"+JjDg$,;V]Aecq%fMF]A
X,^88Sbk*^]A.)2?.]A[uSLOI/p62iL"D5A",?+1]AVmu-6?H30j6NNkBq5[2\]A[umI/'AY@eTT
Ikc.tsdPJ$R57E0f(+5C%Sh4qAZ^P[JEhY8jJ/]A,29m`Yu/T4US_JFS"MlG%[&I/Zt-t:1QK
Q7jGP^rAibpr4Tt:&@G37g4Kj'oZJpjs+`tJ6`S>PbI"*DY3JE^f/hiO_@c2g;WL7b*WHao!
<JQ?glZl/^8O2[$l-:0!Zo)Hjm"A8".\`d3'TR$%%1gc@f1)>7%1`</cG%8`bg.\FT;^6]A6.
ft.Ip)Q)?N`<m<Xgh_+A4kKC=]AL@kBa8kmhgtdT$.ajdb%Sqss9me'p7kT#[o0,cM9PK*.eq
T2c#5iIcK%\lh;G?k0*alOe[F;qP;;qU7)LF:j[`p7\)JdLXI%>!abFj3u)7_8kk]AqQuH/GK
7%_)IAm#A\#tT\k8]A=)9S/&65/le\psP]A@jJicc,PJ;@M/B`,PjNnIA.==HVS&,mOP(6ph"<
O@AlY"1lpG;2lk7sI`2>"id#>n-R,Wn)(dHCk2@iqacY`o0$^YE8`rU3E%Y2H:I4<B)N1e&M
M/!HGa2-ifK8!>]AJo#)2<3CkcEF=2h\+>5E#,?]Ad[A+r__Nm(B"/!f";9a:O3jhE9&WiA5W,
jL=nB(d[/7"I<WsE?7DVm\;iKY@rEJ5C2r@.=B>Z5h84e%+X_MeHM+<D$%KW**k92DQD[?:I
RQ/]A8)@elIQI#r^Gpp%lo>:=Q4S&JD#8A;[f3(e4E0lMo_U\aB8M(`;Bri\1gdfXZ)2u1>p2
eq3lVO-jkPQE>>YZTOq"bL3ST^?Y3`qj5Mp'EQah;J8pcJtuWpqc_28S9@o(r$a7A7LqM[iW
)OeKnQPHfGZ7[?gH4Mf?j0HQ;E!2+W1pR8q8,M-bIar1GZB?q=U_Zpm%hcT3Gl_8+SV,$H2Z
JW'S0_*\hb'VMH:C94_HW&Q(-%.]A'l@a"G+98a5KeYW#.;JGQ)``b.,bm,,ncGt5:U&8a#%E
=$3"mn$o.I4%5lV_TX3/$%F%g__)%WOc1f#EranY`t@&$`Vc%T"3.6fke%NPqX_=EFY]A3Sge
k)"pR@g1mKki#&R\;-)DA(C0Xp+irR,)P@C&6,%`dRYJbiFb>To,&!o7[Sph*G<'RF&$HK#>
Q"@#NRZIs0s<8]AR;*Y&Z#8;aBMU'Q.^N;^UU8/dh''dV<&OtEc_8p$UQXghB@r>rnibE#jOp
=jNkADJQpj^[%rWdA1j4BbYo?^F`r(55-IH,?@h!M5+ljA?,uJU"rUSn_a,3NZh<WfAE!KM"
T-MK)B30F"?`ALDJ9E1YBPNOY*afl%no&*X`trN+W]AU.\GlGaD'Dp#N!@?1K`2`STY7/\Q4F
M;'7S8D0%3$\7#pYuUCD=!B1H1[hES$m_uo;A!XWphHN^YiSd=MtHM#g(+>jY[QX5KMW&2%!
A[/[?m3XU.RZsMnG(1n![E;O$87>-eA3uoN/3QL-MKtZi@'\:&W^]AW\#aZDkfFYdMEoUhhiM
qg3moMp#lpNP=64;r=&lhl=(H61te;78n9>2!g(TtA+>4bVGbm1e^Zt!0V*7cg,G3,R`br`>
'.7844\ocuc<iF0Un2tBA=V`d``H]A71eQoc-++pHu(D@'+_:Y@n:C%,4.k1o/aBtJ1VltSqn
sEcjs8I=STXIf:]Alftu3'fPQEQ3.3h(.fZ4B$\L3+p-QdcB]Am65`C.?)G2<&!P^A$hJ49F?-
O\5l8EMq22hL]A&N]As3a64+K@)*1LnV)1<jguNIH#XV"d-Iq3#S-YR"bjED;@(C*jS7B@^Yks
FO#Co:Kdmd%/AZc"l@k\6b<^qL`+hU6n]Ajr5nF]AnPGS&'j.EOJbe39VH']AZ5Xc@ZG6;-c"Ms
sQY:VS4e`Cq2Ec<<6\[Ph<e8Z_g?_u@X;SDtAKJ_0_R41;ciZ(?D#QqMAKR1qGe\eSG@V(E*
6s3\tpiUbuak<jnqdE\<0]AK#7(?d@NX3hMPk([a87b7"M5mG2rE'h1jL\)k$%.(c#O*(GYsf
'F(N#!,$n\Jl^slGXtP$F4ga7FHJDbjgYpOQbGA\,%&_Xu'ISlMl%ql:\3<Jr5TWNV6)oa;o
1Shb&WYlRrY;UT@AZra^4d$3[fkAlVTcUa7^#,8B,'gFbKT?Gkid_0U1,2LFo\H/0JOOYjZY
=p5K(M?3b_Zdb*qCd.BkmF!-+<o@P'Pgb5^3>E3,Hu#LnX3"!"C.1O,CB-j(QLbRGo/=e.XR
!Z@d3B-ADB^[1%JQF@WPJde$DYa>\kXMWS-K4]A@=\JH;pfr`R%uQuA'L]A1R%u^@dI4[o0-'I
Xj:MG6)Gh,Uc]AJIALXaLI%?IFofa<997)p>d&sc/`]A-&Bb_L$e3@uOb`OF0FL;aJM4Neoo^6
ibkRbY2A*LHOf%W#W&qDuSs8,%H=XP.sk1-$^tUU^CS1Y0$tsV-fBgp<IK!EEOj-L#i7cp:G
CUEY0AVkaj'%NJ=mk)]A=Me[E7g*;5U^oVXA_\@QlcuO@FE(&oUhYg4ua$^l@>L!#pW;T=B14
6@h20LkfHLp2qM[f7?cS5*;p=H!?Ks]AsMQ4U=aAQ_(;(i^Zb]Aul"asTr3n1p?-Neq'eB1-+Q
Ws=iU=)0&,c`l6N2KSB<3EhTOM<uBo%\f?-_i?;'K?%6I5lN`'`L_\,4QXHdS@ucHoe"P\E<
Ob0\RCa<4.S]AX.G;/(Zh6,h2iH_+*=fc&A2E)7*k$#hZ9tlL-39(9Enq?hQ$'1ogSlA&@,25
G*8r3SR%,gQ+dqD[@">)NXG,8(hDi<C(NG;(n[Zs,?:t<N/5!GVp]AIT6_VnLpL0X-q;cOS3,
QfcO;aE#[:@?<78*0mhNk.n(`8nJsB.C2KXe[EQcnZf7RnAP!u5#M5t^3!r9N1Cb+Me:tlJY
LH=%PMKXira:"<;3;\b#6)W#oAZP;2P?<!+q^@9UC=2:h*RLdiU@[X1!\&gd7d`tI<MqbHWm
29[,Lk]AnI`u$;<,Jo&+p^a<p*J),En.&[Qe6)AbCNFc*,ejhM>O(<2sGTPa5$H57"%e\JX6e
qn"oCam)4#&m'X+mi<S=hQ[8XM58O0kP5;f)E.Bf4I`Y4UUa?R%+e!;?+mi02JGGKQePj5"_
7u;p8uXqB^Z%%3a\SFP4uYV65rne>*FSXiB<#aB1sk+F!8giRT\Xu'l:#6]A[.VgXQuJFb(kR
(/lYq-jE6\foV5OHAP`h-6SbcsZA:_UuQnhKYb]AFrg]AZ(%*Wf%[@ppAmCL<i!9A3\*F-b\_A
/o$[4F;)nN+"14Hi>ca$pZf9c=]AV/(dL+kqfq6h%S[um\q.&1,bJ#_X^8aP;;^KMPJI$#d0g
jSG)gG'p!m(hccEA`-b]A*k)s$8]A(`5)F1e#`l-<fnE0O]A)<m02G_(ORt1G+B9#1AN*`a<hrO
i%E>GtQ\-q]AG[l*f_5BC<4dt7!/2[\>/dE?4_<jQHO$X(#CnUVI/'IV1(A=pnX0$qbRgj"K<
[f'f%fsU(*2nXKWb-%<]Aq"X"Kj)rN'/'`3bXIZfP2e'&Tr"c3YAX(KfZ2lS%HkPa[NsXFqT/
H'QGEAtCN\Z8**._4dR)@taB)b#4?^CL5&g*g=Xg[aPkoM@&j/3`;c0-*`'`K7+&_qC,?&$Y
4F9lhL!p\U5MVlc?pFYbfF_-:MfVfL6VQ'HO#K3(nI"FuaT"s,N1n)_]AZit*gLi8\nA'=ZMN
tk0UNjW(b37Kq[-"47"-(;m:Cj5\4Hs1f59,(ZR/tJ`*BtQJa?h?XO.p*D1iVn?SCDGRZV<r
]AR0m4)4>,;M[:'KH>GU,@E4q?N]Af3"_CZ9<_4K7eI&'prrdPol8fE:?[\#g!2jkp0YCAVrje
)+icE;4mr>f^a7k%l_AOUSBJU',ASb",G6QWq+F_R4mXqfp_afou;N1ST1e"Lb1_!Z\@(m\7
DuoZ5:?UcY4pK)gbUPeMg$k_6e/YL@uniB8[-!:emkCuaHfF@`tF$K/(Yc:jgR8QIi.9jKcX
/sY6@hP900EY13E0H)COZZ*Ff\sGWja".^.7uL'pd,tT'C?LR+/F,cO9#p/?p\\KaB//T"X)
@O<p7@W<?SS1U'ID=ri-JG]AmtmF#dDiB).+i(h>o@J9/`&n8i;qfO-39&cm904;KU\b?NE\`
(_\FQM.#1'o[^r&lOsW*o[FXgFj7-g4*P\Ga<Unc.>6q%.FO^9[a6@o-O5.O)_:Cbf\)'"V9
N;#cfFYqR#,t8Wa&l*:_)ai]AQZVes#7i,'Rld>N!77e`gW%d$qCEQBRsgtmg6?\DmST<0jUX
ALG4VV#h<P<r.]A_0Z3%giNR_$uH<l/_7dQ9f^_b/PqaKqd^1n3P%PnQO+dX-r"VD[9S?u*<'
4Uss#`lee]AIc(.2ZD,J(l,4_.TRc$`3U?a`-H17%,*?VB'h&lIW_-W<fq>=M\QHIXArs\7KU
AdoC:D,&Ttg`i1tKo/Z8CG0)c,/!&!BC[V-7<D\R[u8^=:1Wi4AA"$O>m-f53m=;?o]As1uQ;
ahd6;)YifEE.&Lr`GK(%d)aA/UN>sdCnYb``,kWH=/8jZ!L2U[#Yi65+)d*h6K#STB'Dh:H#
Befn.S*!X0(V]Ar&&c\GH0U!,)J`%-3!2o1mW1;RClVhk]AU!%?[rtOm`pVPRl[!JP1D"$AH;!
rFS_bDi^h:o+e:TA_!hu$C=+^a0Siu^oOK@>,2U/b\k5gkF9@;TaX,t^YD!\YT5eJ9>RqioT
+g*!J]A6A@@@X&a$1=t"aAuVjYOicBoh!^I7[ss$5,+21nXDAopK$S/hZ_aF_b`#[ibKVQ4Pj
d,23I@C4;.US1)1&C6:AGgL*T\gVEm-W+Lg9K0+s(,)`cF9\No8gaqR[17aJgs`_bPSgr^l#
4qi4+-T(Ye=DlCk%M5:st?!NloG<`/2$C3L$,'iK\DM(^lT'9cUb-VfnjoXq6eJFoXdg/6Tq
GbIbC5WNPP/J)8bkbs["eYD'8[F(%p:Qd-LC+:'V9^f<lKr<,opaKE#E/5EWXt#cR3gD`Z?b
AR.3Tf28=sMBQ'uCnVG7`t6@C]A.T"qFNo1HZmdVE0N]AP't!(>fWM>2AR9bC7p)>r=*Xh?278
?"RjsC?<',.9@NT0d]A;sE(gKfdS.o6'AJd&(A?^&JZ`BN&X@8W4BLk@a&C%R\0Va7!+MLWMQ
(NVjHa9lj_OO;H*HHnR^EUHdeLAdp"CLj6j;r^FGL"Iq9)MdPXM2*IMEi9/fk[^lQ4JaYJ/a
CpK&o,&(Pk=bsZu?Ao!L3/W!n1RgYn!&'V`kQco8$\7d0K!]A=(V\kRZ(Sf3Fe)T;()2?qfHa
_P3coSs]ADFMt,%iIPC-OiI4RLCpA!/-8;6f^^3cVq?e1TUT\ZL0mYteLtt#4ufn8>41]At<Nm
>@04P(lrCU@VR/[6~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="0">
<FineImage fm="png">
<IM>
<![CDATA['pj2*<:(XFc^:%jQ79Mu)#R35Z=M,q)LHXjI8@:C82JHu18I>n[ZiX[b7ZP_aV:1&@0gF?,\
,_+p>>+>fD4HUY&hm0T)3g$n+UnD=]AlS]ASJ4*k!!&AU<W-\5!8.ElhujuY@PAMn!!#b1)!gK
ma(I&$Hl6+&^p.#WogA5Pcmj7la<.*F567"^OG,=G!kZ3i';O!d*YADmX:='*-/ucOD.-mn:
m0J'Ot2;LF&CHTUD\dGmDS%;guAO+omklA2_O]AgYBa@#!7rU&@-U=di@Zb<(Z]A"/gL4V1EMA
A#9Y46</.af<I.6;$4J8+^5q"YH9s[!pnBFHZa<qqiYjJ*a\djD9E"+JjDg$,;V]Aecq%fMF]A
X,^88Sbk*^]A.)2?.]A[uSLOI/p62iL"D5A",?+1]AVmu-6?H30j6NNkBq5[2\]A[umI/'AY@eTT
Ikc.tsdPJ$R57E0f(+5C%Sh4qAZ^P[JEhY8jJ/]A,29m`Yu/T4US_JFS"MlG%[&I/Zt-t:1QK
Q7jGP^rAibpr4Tt:&@G37g4Kj'oZJpjs+`tJ6`S>PbI"*DY3JE^f/hiO_@c2g;WL7b*WHao!
<JQ?glZl/^8O2[$l-:0!Zo)Hjm"A8".\`d3'TR$%%1gc@f1)>7%1`</cG%8`bg.\FT;^6]A6.
ft.Ip)Q)?N`<m<Xgh_+A4kKC=]AL@kBa8kmhgtdT$.ajdb%Sqss9me'p7kT#[o0,cM9PK*.eq
T2c#5iIcK%\lh;G?k0*alOe[F;qP;;qU7)LF:j[`p7\)JdLXI%>!abFj3u)7_8kk]AqQuH/GK
7%_)IAm#A\#tT\k8]A=)9S/&65/le\psP]A@jJicc,PJ;@M/B`,PjNnIA.==HVS&,mOP(6ph"<
O@AlY"1lpG;2lk7sI`2>"id#>n-R,Wn)(dHCk2@iqacY`o0$^YE8`rU3E%Y2H:I4<B)N1e&M
M/!HGa2-ifK8!>]AJo#)2<3CkcEF=2h\+>5E#,?]Ad[A+r__Nm(B"/!f";9a:O3jhE9&WiA5W,
jL=nB(d[/7"I<WsE?7DVm\;iKY@rEJ5C2r@.=B>Z5h84e%+X_MeHM+<D$%KW**k92DQD[?:I
RQ/]A8)@elIQI#r^Gpp%lo>:=Q4S&JD#8A;[f3(e4E0lMo_U\aB8M(`;Bri\1gdfXZ)2u1>p2
eq3lVO-jkPQE>>YZTOq"bL3ST^?Y3`qj5Mp'EQah;J8pcJtuWpqc_28S9@o(r$a7A7LqM[iW
)OeKnQPHfGZ7[?gH4Mf?j0HQ;E!2+W1pR8q8,M-bIar1GZB?q=U_Zpm%hcT3Gl_8+SV,$H2Z
JW'S0_*\hb'VMH:C94_HW&Q(-%.]A'l@a"G+98a5KeYW#.;JGQ)``b.,bm,,ncGt5:U&8a#%E
=$3"mn$o.I4%5lV_TX3/$%F%g__)%WOc1f#EranY`t@&$`Vc%T"3.6fke%NPqX_=EFY]A3Sge
k)"pR@g1mKki#&R\;-)DA(C0Xp+irR,)P@C&6,%`dRYJbiFb>To,&!o7[Sph*G<'RF&$HK#>
Q"@#NRZIs0s<8]AR;*Y&Z#8;aBMU'Q.^N;^UU8/dh''dV<&OtEc_8p$UQXghB@r>rnibE#jOp
=jNkADJQpj^[%rWdA1j4BbYo?^F`r(55-IH,?@h!M5+ljA?,uJU"rUSn_a,3NZh<WfAE!KM"
T-MK)B30F"?`ALDJ9E1YBPNOY*afl%no&*X`trN+W]AU.\GlGaD'Dp#N!@?1K`2`STY7/\Q4F
M;'7S8D0%3$\7#pYuUCD=!B1H1[hES$m_uo;A!XWphHN^YiSd=MtHM#g(+>jY[QX5KMW&2%!
A[/[?m3XU.RZsMnG(1n![E;O$87>-eA3uoN/3QL-MKtZi@'\:&W^]AW\#aZDkfFYdMEoUhhiM
qg3moMp#lpNP=64;r=&lhl=(H61te;78n9>2!g(TtA+>4bVGbm1e^Zt!0V*7cg,G3,R`br`>
'.7844\ocuc<iF0Un2tBA=V`d``H]A71eQoc-++pHu(D@'+_:Y@n:C%,4.k1o/aBtJ1VltSqn
sEcjs8I=STXIf:]Alftu3'fPQEQ3.3h(.fZ4B$\L3+p-QdcB]Am65`C.?)G2<&!P^A$hJ49F?-
O\5l8EMq22hL]A&N]As3a64+K@)*1LnV)1<jguNIH#XV"d-Iq3#S-YR"bjED;@(C*jS7B@^Yks
FO#Co:Kdmd%/AZc"l@k\6b<^qL`+hU6n]Ajr5nF]AnPGS&'j.EOJbe39VH']AZ5Xc@ZG6;-c"Ms
sQY:VS4e`Cq2Ec<<6\[Ph<e8Z_g?_u@X;SDtAKJ_0_R41;ciZ(?D#QqMAKR1qGe\eSG@V(E*
6s3\tpiUbuak<jnqdE\<0]AK#7(?d@NX3hMPk([a87b7"M5mG2rE'h1jL\)k$%.(c#O*(GYsf
'F(N#!,$n\Jl^slGXtP$F4ga7FHJDbjgYpOQbGA\,%&_Xu'ISlMl%ql:\3<Jr5TWNV6)oa;o
1Shb&WYlRrY;UT@AZra^4d$3[fkAlVTcUa7^#,8B,'gFbKT?Gkid_0U1,2LFo\H/0JOOYjZY
=p5K(M?3b_Zdb*qCd.BkmF!-+<o@P'Pgb5^3>E3,Hu#LnX3"!"C.1O,CB-j(QLbRGo/=e.XR
!Z@d3B-ADB^[1%JQF@WPJde$DYa>\kXMWS-K4]A@=\JH;pfr`R%uQuA'L]A1R%u^@dI4[o0-'I
Xj:MG6)Gh,Uc]AJIALXaLI%?IFofa<997)p>d&sc/`]A-&Bb_L$e3@uOb`OF0FL;aJM4Neoo^6
ibkRbY2A*LHOf%W#W&qDuSs8,%H=XP.sk1-$^tUU^CS1Y0$tsV-fBgp<IK!EEOj-L#i7cp:G
CUEY0AVkaj'%NJ=mk)]A=Me[E7g*;5U^oVXA_\@QlcuO@FE(&oUhYg4ua$^l@>L!#pW;T=B14
6@h20LkfHLp2qM[f7?cS5*;p=H!?Ks]AsMQ4U=aAQ_(;(i^Zb]Aul"asTr3n1p?-Neq'eB1-+Q
Ws=iU=)0&,c`l6N2KSB<3EhTOM<uBo%\f?-_i?;'K?%6I5lN`'`L_\,4QXHdS@ucHoe"P\E<
Ob0\RCa<4.S]AX.G;/(Zh6,h2iH_+*=fc&A2E)7*k$#hZ9tlL-39(9Enq?hQ$'1ogSlA&@,25
G*8r3SR%,gQ+dqD[@">)NXG,8(hDi<C(NG;(n[Zs,?:t<N/5!GVp]AIT6_VnLpL0X-q;cOS3,
QfcO;aE#[:@?<78*0mhNk.n(`8nJsB.C2KXe[EQcnZf7RnAP!u5#M5t^3!r9N1Cb+Me:tlJY
LH=%PMKXira:"<;3;\b#6)W#oAZP;2P?<!+q^@9UC=2:h*RLdiU@[X1!\&gd7d`tI<MqbHWm
29[,Lk]AnI`u$;<,Jo&+p^a<p*J),En.&[Qe6)AbCNFc*,ejhM>O(<2sGTPa5$H57"%e\JX6e
qn"oCam)4#&m'X+mi<S=hQ[8XM58O0kP5;f)E.Bf4I`Y4UUa?R%+e!;?+mi02JGGKQePj5"_
7u;p8uXqB^Z%%3a\SFP4uYV65rne>*FSXiB<#aB1sk+F!8giRT\Xu'l:#6]A[.VgXQuJFb(kR
(/lYq-jE6\foV5OHAP`h-6SbcsZA:_UuQnhKYb]AFrg]AZ(%*Wf%[@ppAmCL<i!9A3\*F-b\_A
/o$[4F;)nN+"14Hi>ca$pZf9c=]AV/(dL+kqfq6h%S[um\q.&1,bJ#_X^8aP;;^KMPJI$#d0g
jSG)gG'p!m(hccEA`-b]A*k)s$8]A(`5)F1e#`l-<fnE0O]A)<m02G_(ORt1G+B9#1AN*`a<hrO
i%E>GtQ\-q]AG[l*f_5BC<4dt7!/2[\>/dE?4_<jQHO$X(#CnUVI/'IV1(A=pnX0$qbRgj"K<
[f'f%fsU(*2nXKWb-%<]Aq"X"Kj)rN'/'`3bXIZfP2e'&Tr"c3YAX(KfZ2lS%HkPa[NsXFqT/
H'QGEAtCN\Z8**._4dR)@taB)b#4?^CL5&g*g=Xg[aPkoM@&j/3`;c0-*`'`K7+&_qC,?&$Y
4F9lhL!p\U5MVlc?pFYbfF_-:MfVfL6VQ'HO#K3(nI"FuaT"s,N1n)_]AZit*gLi8\nA'=ZMN
tk0UNjW(b37Kq[-"47"-(;m:Cj5\4Hs1f59,(ZR/tJ`*BtQJa?h?XO.p*D1iVn?SCDGRZV<r
]AR0m4)4>,;M[:'KH>GU,@E4q?N]Af3"_CZ9<_4K7eI&'prrdPol8fE:?[\#g!2jkp0YCAVrje
)+icE;4mr>f^a7k%l_AOUSBJU',ASb",G6QWq+F_R4mXqfp_afou;N1ST1e"Lb1_!Z\@(m\7
DuoZ5:?UcY4pK)gbUPeMg$k_6e/YL@uniB8[-!:emkCuaHfF@`tF$K/(Yc:jgR8QIi.9jKcX
/sY6@hP900EY13E0H)COZZ*Ff\sGWja".^.7uL'pd,tT'C?LR+/F,cO9#p/?p\\KaB//T"X)
@O<p7@W<?SS1U'ID=ri-JG]AmtmF#dDiB).+i(h>o@J9/`&n8i;qfO-39&cm904;KU\b?NE\`
(_\FQM.#1'o[^r&lOsW*o[FXgFj7-g4*P\Ga<Unc.>6q%.FO^9[a6@o-O5.O)_:Cbf\)'"V9
N;#cfFYqR#,t8Wa&l*:_)ai]AQZVes#7i,'Rld>N!77e`gW%d$qCEQBRsgtmg6?\DmST<0jUX
ALG4VV#h<P<r.]A_0Z3%giNR_$uH<l/_7dQ9f^_b/PqaKqd^1n3P%PnQO+dX-r"VD[9S?u*<'
4Uss#`lee]AIc(.2ZD,J(l,4_.TRc$`3U?a`-H17%,*?VB'h&lIW_-W<fq>=M\QHIXArs\7KU
AdoC:D,&Ttg`i1tKo/Z8CG0)c,/!&!BC[V-7<D\R[u8^=:1Wi4AA"$O>m-f53m=;?o]As1uQ;
ahd6;)YifEE.&Lr`GK(%d)aA/UN>sdCnYb``,kWH=/8jZ!L2U[#Yi65+)d*h6K#STB'Dh:H#
Befn.S*!X0(V]Ar&&c\GH0U!,)J`%-3!2o1mW1;RClVhk]AU!%?[rtOm`pVPRl[!JP1D"$AH;!
rFS_bDi^h:o+e:TA_!hu$C=+^a0Siu^oOK@>,2U/b\k5gkF9@;TaX,t^YD!\YT5eJ9>RqioT
+g*!J]A6A@@@X&a$1=t"aAuVjYOicBoh!^I7[ss$5,+21nXDAopK$S/hZ_aF_b`#[ibKVQ4Pj
d,23I@C4;.US1)1&C6:AGgL*T\gVEm-W+Lg9K0+s(,)`cF9\No8gaqR[17aJgs`_bPSgr^l#
4qi4+-T(Ye=DlCk%M5:st?!NloG<`/2$C3L$,'iK\DM(^lT'9cUb-VfnjoXq6eJFoXdg/6Tq
GbIbC5WNPP/J)8bkbs["eYD'8[F(%p:Qd-LC+:'V9^f<lKr<,opaKE#E/5EWXt#cR3gD`Z?b
AR.3Tf28=sMBQ'uCnVG7`t6@C]A.T"qFNo1HZmdVE0N]AP't!(>fWM>2AR9bC7p)>r=*Xh?278
?"RjsC?<',.9@NT0d]A;sE(gKfdS.o6'AJd&(A?^&JZ`BN&X@8W4BLk@a&C%R\0Va7!+MLWMQ
(NVjHa9lj_OO;H*HHnR^EUHdeLAdp"CLj6j;r^FGL"Iq9)MdPXM2*IMEi9/fk[^lQ4JaYJ/a
CpK&o,&(Pk=bsZu?Ao!L3/W!n1RgYn!&'V`kQco8$\7d0K!]A=(V\kRZ(Sf3Fe)T;()2?qfHa
_P3coSs]ADFMt,%iIPC-OiI4RLCpA!/-8;6f^^3cVq?e1TUT\ZL0mYteLtt#4ufn8>41]At<Nm
>@04P(lrCU@VR/[6~
]]></IM>
</FineImage>
</Background>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report5_c"/>
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
<WidgetName name="report5_c"/>
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
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1752600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[7353300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[实时交易状态]]></O>
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
<FRFont name="微软雅黑" style="1" size="96" foreground="-16069640"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9G!?;cgCD)2nYim'Pb.8K"Gn2hOZO.M<klC+XS0i07!5+p:OrN`;dn&0NX)f$paPXjpe_ii
=8\-AjqHO[QIOMC'g7a=@99"qjH4+ln's%JJZ4<mGkPm`^'!8,hG-AFFU5>3iYHla:0qb35d
\^=.MNN#=EkfmE180P8OIKO`#\]Aeb,-kEAT3h1@@k'#%NBcJ'G<jn6!o]Ae,h'kicCJ1;[&E4
O&b<hcFm6<LuVS7_KTt0p:+,Z6;H?V.\s5rpo?!kh;&$ngWs7081GXY.LXlV11.V+&g!p)J\
#mb@/HtPP8Ctf497b,Y#C=2DK[c5'6\aY1>\$O&;6WYFh%Z\LES!O;>"5^F3e7[;C@O\iB\R
kGqsl:&]A[%R[nhAg["8Y>2>AIr3Ya;>u0Uo]AcYEm&quecs8"UbAG_\S@t]A3lef>es.>$l8\T
MT9gnGGM6Z^k7*IoE#l[HD;nZ(2TWKMPYB9VfCil^.#<fT,cENf`SS,P$]AVkAp4@9%.K*#tB
ij"KF<Sa1itBoA4:<o]A/G<..@toCbB6q,29JWT*L"i-]A%DlXG$1@_Cl^C9;m@o`ahA<cEL_M
iJ[0RS>Xk8ufuS3]A8i7-@V_;\/LF'2qg?1`h*`25C^s\PG57XmlMmF.NsO5=OlC?EseF[-DK
8jAk9F%VMsl'#pI=N8o3$$)$=of/olem--jT(TtG$1hL_jLqN,Dn'<c!r($Wa&nYB8B#mFfP
Ze!Cseld@H>L=tqkd>rP08(XG#NlIIoH"r4/*GFdY02%XfK0b0>F$]A+V&NL:2j_rojBI+_+S
,X_I4JmMHITPJ;$nA&i^-!pqPJq9K]AUoYR'kTorc6t@G'Y<_fDTOjan$C*n4TGX4#gnGi*0,
tS)DS0531#N*V$'c;@7LB3tpV=ae"jB3W'ae`Sc3u^6J&Wq7bh&(5T<M'j3]A.DTuD@W$#&Z9
>TZe;g.[n;UAZLNtjOsc.GXB9W;-h]AtT#oW[\[-j=mVMP_i/WTN3"A;:=FX<7dN\_:?]Ab+X(
_Ui*a<$Q#9[RgH4dlWi!XC:XO<QZ>2=(?):AB<*Nc^[>5miG&p!_VT*+iQWfU41_-%?Gq:d7
=>d/L,kK^8#L@m4/j7Y0jAe.H,gUP]A[X#Uh>j6Eo@T>jda.CGus(Bq+@YBTmfX>\f^:Bs/?o
oJ0qqem8V^?b>4Y1#`YG/Pli>jLKc.7^pA/I2mBh(qt5hhLeZfV]Arnb&(h':sB/a3L/r_s?[
EDSOo'h(RA)VdJ4;ai=&q=1?Zgl[*+PbnNo;+F:A:hQWTBq:^a.O-WkF2gFS4C8fj#<tYsY-
L>Ql&\BX8d86B!b^lkGYf=I/JW%I.S)LXkNCkCoO.IhY&bA?MI:h^MZ/#O#h/$l?>OALOf3M
.'gQh/c>ENJGTLg+=&foP]A+eprdqIj`TL-uhJKZ1aK6o!J8ZjYAQ2F4N)9A@foEB3A^T%Y@B
Tl%dgq1M*d-S<?WAingpJOr5fUJm^u(bIAu>f40U7aAq?!9CPr9T->2LB>l)[BW\CFaCf'L!
,6$S_^bt@g3.Gl3=n!BG86'oGX*3VkR6D8n'MS[E/k'505R)NQG":=;d)mYsb?f%5mnYC#Sl
9hB+'$S$MAEkW$&-b)oX5m&0Ql8X"Ga#I:qEGj.(hmosnX\(Qi=-8.mdY7R>Lq38'G>2SlFR
s+PZqQcMUCmi1H([hkRi/!8erc.h$Oet=e&>%Nt`';N\cA^^R20Pfo^'[YKVbrk#L+Tu>D:b
-WF^9nVop;G2gd,Qc?'uIgOhF<c0fj=3@!ecIGLKm43hW3'e'cA$:i0/qHYI9)2T%4oC[:Ei
W[gBU3Alf]A%#JIuY'j]A[/rK3X6W\U:-gij<"Q6seUss\NN#usQo*X#h.c<C3IS'>\CkQfKNg
@S]AiY%2`T"aYu^6f04dl&!blci,1qA-]Ap53gjAf?,bWP`aDZ_)7iUJF1b&?;=8u]Adqn#G&MY
&Egk5g07"@CCJc[l[4CbZZ<g9F&^/jMs0,lD6[)W@<GUO*6sKFJoC`0c`0B1sGPYcF-eq"uC
ee/;3ip$SV<_7<`QOD'VR*IQV81r8kV<q`[F,._S`M6Kas3?T\4F\`%O[^uNchNpMlIg\=OJ
F[`<mlUN3j*5k"r,jC?m@V7L<B-BDT"'`Y-0QXmlQ3e)[?l'UDh-M01jFLg[oK.EjJF%,A"V
F\a6[1lMC^%JBDg.5MrYPO_>m=Y*=:%D*#$##e/*UbXR2)9H^ISi4r>E$H`nq*gLR<aBXf8^
&*SK<-t0SX8RZWWV[AEiO(n-H"82W.i\?mg\\U<ihr<]A-tVjO-9n`1MgWhQD<J\aIEUL]A7#0
#g-LFmd+D-,ra7\:PIJ*357"-MLgn'"ELE%Ufj_7(g;E*\`DSTKh^VS_gFR)a+FYeqbo(JT=
o;ltKgG87RTd*r\a!jW`cm=9P[b4S_ibeJc<=HBFGb8B%oWWE8&+IR*#?GM('jS>&n@>b)Iq
=\qYG6W[/rO<E=Ct6(2>.i'A(["^Ms[:rQVl&aM#iq&<UqAI&tCj:Em,4YD,jqSD0%K=YE47
X@9<=iGq)cFM8/"KSYU+ZD4bp^9r_0]Ak0G.rO:<aCsg"n^Iq>EG=_Qt4a^Z*o"J\sIT8r&En
(K[$??a`Z[IoG?cqm(LnTVY9PjCRh<ho"_X((nM-"@$JiPikobB=MbV,aI),[=g02jF\Zk'4
q89k"s_Q,X4T(3JrR18ba06<-nC!UV>*VcAh;"rFa6Zr.a38Q6J4l,;_TB"DpLKQEIf&#8#:
3h_l5i1!A[MY97IYm/0&N8a@./sD(^4.ho+%XjPUM%`RmYjJS**Tc(VN2J)l5X>tLp+Ve/`&
n#/d9Om`aSSK7R^U'aofLTD::*B!t[!0AsU:\28gKajpbSUnq_6`9VH47e^g*5h*ce^?]AdFZ
r;k69nK>jh_]A#F,"l1!*<B-<<9$IkA'o,A+UASjZkOn^2Y/e%%>:0Cb5EJHX"ReaH]A;YN+6u
/@/g7)f]As(r(238<8kBfP4VB^=6V*2(e9g=9Y]AD>@._l/nB^^/(a\=\:O%U!E-JPH7[)-<Eg
'k&'Me^;co.Xib->A1MO"Cu<X.j1gWP9fjDH5q/hDE$jnLoBt9o!p`MVNE,b$;o3dn/[U$OB
uYQ:/`(%)gG7D"W,E3DUS<X?\7]APXI?5Q@HE[0r[J+U3k4luj7;(A!QcB[,$SaLtonUr83Hm
BtXXO2C1tCBOP_[>gG0)I*Jq;#,cABBF6X9pu(cb"8XUMPoK^f;Y_+QG;&_qEb`It@!:#a-a
_7o)(*0+miLJ_MuUY9F&j7oG-%[nLd_j/)ZhQ_)M.pN/_A&Qo!HaNCNQ_=<;`#'#36&/6(PH
s4h<+TbGB*;(`1O)uu4/Pa5Z@!W,EW.)23h_))(A^CQ%:`r'#ECqqoTY=*KZ]Amd8V@@3VY^&
C=+YH#dU'QSVToktf6(d6m%k.3a0]A']AmOR*pkReDm1W?]A1W+'"_Xn[ht4*qticfo#R>H"egb
226ONRoB?V,]AWYrt';m-bcOkIlHSq#ePY<qg))P^t?\/j$X#iZVGH>LPP_h#5cmP?.qiUme?
h*g^'C.aX8S2k-YE,3RTgtfNlA!>@K:1!n<d\Nqi.aS9-coqBLkT>eacFSS,XK[WNPiR%/rJ
4.W]AqTUiK7%e)D[0#\*6<^,m+LkD,(Kice.MfO/)DA)=$J%N%@6Qs;V.Ba^ccjF)+>.`'kb9
OBJRO0H-lH-9S-92r]A'1Z/.UVu8ikQ/`t8ZaoM'Vue2YPl8=n;[[&2E]A)s3P^"?j!7tV)n*[
H5R9WOVlZ9aimn(q=2+oRZEu:F$:G]Ar6;inc-:;\dRN<)B#bcomf0_^jd0uRfhuD2a\_C"L=
Fnaa33$HUUPn@U3U,"_`;Srb_b)X83Ed3aXK+^aU^*jXASAj)1ms=Mo"$+QQ%jc:6I"gP]A;]A
9V67XJU[I@OAH(pm+\MJtW!>1iiV(KA)=hk&S^jR*;H^T`p3noD;c7]AuUYKcA"fs5/@DUFQ*
0McV:lu-KV;S$4CopjNhA&F^*]A0^f>SFgXBOClQ>-WLTY)L/dp6:iZT6hV)(;tb__i=V@kU&
>c!N&oC%g*tB)L<@RLBOIbu]Aib/riI8X-_Kl+aB_Yr941P`J`:qW^8,#]Aks&RqR$e3718Xb!
)8RsLXo[@Nu9?l4o!E$'k[C@a98&.QFF1kX\r:gcF"b`'nHq#GBi>MnCh<mnf'u\Gh&aVeCQ
Ml((Us3sNkb?B65XDVOeZc+qL=aMNn-O9AYJDSuN<Xn7e>U'?.W4"r6C\:dhN@DZF_VD4#9_
GCa;E.;"Zg<iB-mUB`@26:GAhg',u-b#N[@`1-;8]AJlVgi9+JX#':.3pjO/'0fI:oHH+ppl`
.W#;OrV6I<#Td"-7RCn8_]AG?-#tB!&97o\9&:kBjGbpY67".d7OV*oo%`$/+ciT<1U9<FFna
D+%^/Q6>T,-%FE?E#e+W6N>*Y6(2M'i::P>"$_*#DElNL1Z@CYB<6*]A[Gl5H6a957tKWh*Ym
/iAN[?oh]A2VQ=m&)jFlQ),+\-3Lo2Zra6>2W#tZH,37[$YJcgNgN_d6\?TS>&*YJ2gP=n)@8
s+e6QVWI:+8mGmW]A$r2D;6UIT"B1B:EZ6$m8!6j%7gZ%;,0[I9s$`Zc\!uX;:jkgIfMijie1
l!"bS&]A0hUQ>Rj&Mm8':<G25tZ]A`Y1#JZN'9s%&ii,jLir?Y3?m5B,HI%62Nsr3@=fY:@E:.
f'!>jSYB^r0Q&]AZMs!8i-80_rBFbdM*cK$[E[MouE.4lNXZ=dRBU3+\+`jTf4%G^(#AQ3Qk%
E(LQiJ\=8-91Y0uA*l&FoXJX2,oVIs-$D9$J/Vp:MO$d7CQ2L50<En2Kd*2E;M*K(S[d4RH[
66')1"iZ>3o$M,O9T0+4?OV3d`8(!15+rkUY^"T_C8ESigs3)s?J_s=_he;eS6FQ2DL]Ap-K/
"O!bMj?3>'%o30$Q78bZMtcTV@0tJTi]AO,ek"+-C&0V8XDCjmS6N(%QZ1rUe7HaC0S30jQ6i
Kh2M3HU$,5kg+G5WsRrUPJlAQL!!)ZL"q]A#[EH&'BQBejg2-nq%KKX*:NSS%jBpOE^S,p@nG
Os'oJQ#B52O3[QW92*l9]An/BKb&TCo'NRh4FEaA/OQ>]As8h"D_@L%ah7/:8Q+Zk*o%:A?PM[
9d-g#UeM3]A/!=B)AhB[KB+B&aE?7Pk52!qC7Ug+EGMmSV>`=&\S&T;+RiuSoWQ[er*DZBH3k
2d;`0'ou(tgWr^:jrF8KW.jJaU=1`76!8GAf+Q6"&3t8Icg+K@XkFuQiYQ,tWXZ0>OGmeZ=/
s@%_"RJo`9c=D`0Oji>3[_sbE?Zi80&EQ8s#5/h?8O/23K8onTgf2q5Ue8ccA1`XogR_s<7K
T3+Iqm)$-RknBT-cB%U]Al_Y9IP#c53pU2$&$IO;jEe%/PS<N:anC/:Ri5ODQFrV-<G!N\MFm
PolFDRp*nD#taq&#59#&M@A<f1g@7*Erier*$gcF2`;]AZ:.B&fS\[Dbn;+F2+/a9aJ6s;f%V
T--P&#7;W_!?:_SuF(T5lr$]A/\Y+0aUfBN/m\fej@Cp'"8=E;k=\K\%^<b*pj7IHSDe5,ng'
L',R6a&;7C.7(t3gAQ[BG9utRahdS(2WYoNWl5qGB7LBDVCWb10l(:eYdkF<cZjFc@[4dnke
1tsf&G$gI^j0%)oq1jG+%0:tdV$tq:hkr6&^i<Dq6#c1Zddq:Z@>gNpoN(:aL29Ieb0:L&Bd
X875(f0]AE%K.6-N2Pl1<&)M?_UoFOiE?/-cAc$M)]A'c2H3PJ9ePKYq6JK-Qb\K-i)9WAmhge
I"%o<mANf,J2b&gr,3Q1B^NM]Ae_]AETeRM,H'th+"Vp\b8m>iB+]A,ph4)X_LMBOVI4E+Zm*qO
:,o&IA5$L*XZue=#Q"IN,P-aK6RgPPsOMZlirp*05"L%=j457jMO(7:PR=TYI:&/:Go1IVp/
]A@d%j$J>tEf-Ng9ReVc/le,HSHk_.rVi'rEe^4Gp8TipL:Topu>Ynnfj)1spOlJ<,DJHh_3)
'-LYEmc;DdgE6W-2<'oNY*+iR&[aH02gu\R<A"Rr^+LpPM&[8^u!]AQ^P8HrNB/M<h?_.,p0t
QX6hao"n4l5-2t<h;RoTld!6Wu%3Y+AhkR:06f!8RH5CC]A#c]AY1P1&Cpf?+9jP]A,)VXo+H[>
c`APuhLmsE1qPs+R.DPsh!gtF_foJdB)L$c&<*4I$CHGBB\34o$YLdb=.9.>4kJ7+$Y``B&n
C^CSrsh_j.\NQk#5M,g$hsc"A^$,092+iPBcVBcL;Laq[N85Z*`mGq^9ZagGD#(@^WVV$64`
m7"F`\\&=/O*#k`QO^j(3V+<,4h?loVUZnfoBYV4=n\(%(mX!,C]A\mcA1Qog#oDC9n:d,g"b
22<1)`+#cgK_5T)*bmL;1MM:`kIA(r#m`k'L?**cc\5K.?eq#NC^+5kBrR-f,cXtr%AbPg\_
s@>BL;Pcm;@2TQm"'.jV&7UF3I'Y91>ULBMj,imC\h:Ojr`fA$_=Edj3"j\JC0LTA_+oVd=6
9UQ8jof#6lm1^_<oDQtW^CPS/kceo0Mc6XPaHSnb7a+$(E<0ua6kJ,e<+[:=^r+@AagN0gEF
!4,A3$=jC'm.V%9OlGnl`#'4"Jn`bG.mH;a:^VS:F?oEJbGIE=kTVgaT-=l>)Yc/at>cg[D5
OE0b]A5oijt)<bmq!=tb5'/4<[(k#if61.j_Hf-7oJ^jh&`'bjkAKp;\nbalFbNB't=s*=>`4
dG:!2g#8YjN.n'PAQm^gUYR8^`\@6d7+XLWK\0<@&?dp!>8[$W]A3U=ZO"@/HTFkc@hnm!8^K
FBFa*WE'VQ=J^4@mF.+)Ecb4H;$7R3fN03i0u)#u02Yep!8Kfg:kLG!X3kkja<?$TE1+M;`1
Na/,&pdLq5A=>Ib!Y/l_(*1GrlXUi7Cj/InZUe<8_=E5iWXRqk7!oUO,7jmlZG\05(i3>D=r
98H9*'&GD)n8'jFM]A3^te=I:NS(;q7=ba\:b-g6#/T697IhbcN9^$6;Rb&,h8i$B[AZ#8jt"
EW$:TS>C^6uk\+@;^p`lkUkC`jA,8Q64OCeH&"2;1k\q8e&-pAC\>lXU<T?0"(QfBqc'T]ArI
4H".e=fGEfE6mpM8hcEMA3un,?CLRY.<Eil!m]AFf%rUsRI!GfQCm%MN<g>3NJRO=37TrHGil
*(5E,.1/6a%kcM>FNTDph~
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
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1752600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[7353300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[全国经销商分布]]></O>
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
<FRFont name="微软雅黑" style="0" size="96" foreground="-16069640"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9>'>doVj5[3JE_7;(biMLju"3pg(P58pe`OJLH.ZlQR7:1Z.HZtR3X`#)DS(db<+S^,VNec
>pVdC%,A1e%p",(qMq)Cpn?O^Ntj@LNj3&0W<]AKTQ/PZ+H:iboSogh)UDmiApDAhtQ"%hY[-
(cb8f;eOM,'-b^SnE`cGr`9#2!>9+J;%LEMin]A$7Pk\kq1S:CtTHiJ`[mk+NK[a<UQZu+Aql
m'bPQH]A=mkBuAq=GGTorR0ib`e=0fp;IEC/N>Yk\B2ALQ"7k8F-X?ahMs2TrJXf1n*N*"=!Z
-`*"%TPi%c>rg"3KQAU5/39.2;YVcL3iGIIus8`pjH`"1U^_k(]Al+p#O?Kg-Uf5&=srEkJUD
oFF/M*[Rf[:Q,'VH'k*NoUG?$LOu%I2SM"BI;u\E&CE@8V)'(*1pVS"Gb;sf\J5#Bk/(15D6
0q-H7NG0#*3OTg/d]A]AhI"8Cnk"3.H2r`5N`53)G:g]A?\+qU[d)S56Lm7ju+'-.Ip%9:Ss/+o
Gjo`/09u"&;!qGm[)Q'!G4oFu'=k:3DHnP9*k"?c!IFY%Db,QEBkYKGL6gQt#="2$OHF[Vmq
r/a_3Y$/8rXuasO[BJSVKd+?mhk8GSNL/IYB:sJX5\=qo_!.#:g^QdI=jV:J((JKn?0Zc$V\
!g-=XV+B?i^U?n2/&d?:<=,o#Z'0"LWcMh8\U:d*4()[*FK.cT/aOZ*)GeSsHK'`k3dLYlJ5
*Nr'T^KQ^?>gXs/J,MH?G74G*Wia9K0kn.S4oM6hkaL4@Z]ANe")T.J->M5'EE`2_HcCdV#:S
[eU9tA8JEFPSu\G@mCZ%p<"=N[!0]A#f),;_%TVBl*5276MD:*Ja';@NG$In@]A\cF89"_B)W6
tC<CX?Q^NV=1Dd//`V/SoG_apY079$/DE`*2e/hqFU:L1l5"9CVaDH'*@=HpT"4rsu;XfA>j
51Tjf)GQaH?_8G.'8Ip3,5J.KfUf&s88ore+QiT)O*Su!O%&3-h7XsFPN(28');M9G-Ve__D
`@Z1lfc:HZ0Se4%TRaZbI4`qhngD]AXHU5%3_(s50`5p(De4J28mT)CeJ+0dr:Q"s_/\e[Xh7
/BUi?_`KfLGeCY"&kD0+I4C'+gnkK:<t03`S=XJ^B"PfPU'PEYepn/hl^'(pNd;f:DN6CSs!
n-<'h@tpqo*:*=uqFcWPbc!;<I=Uju<G?S\B?2-7sH=B-Y-Q5uJM9b0`#s83U^Xs.G.XAm``
<5d#Xs-s5KYNO,KAG]A(i]Ad`@[u_SU1Kfle?4=G`Pp@F\Kq$-kbN`+e$28Va]Ab.D.",,f)[sd
jaD;pU*r(C[TA#gPT&QinUk@od]Ak\?dJSl/pAUI'$^$4d<VJ)aD-C^oUpG#$W@gt$/kNFI&T
!<X#W``_u2KA7<Gu2G4in,#YFEqY5>/Gqu/"<Gd!a]A9=PmH5/KtZ5FKeDpeL:R;8dlDK^@5o
2Rc;;]AFM30Vtan#cQCB5(!=GH03-k@b1plpkiF;GX^iMT0I14k]A4/8l1E,o5#S3C/g5kj%8X
,FPc"ZpZMr"-RHmm)e\Qd"9+bmciWI0L@NVFQ&W*Ec7Nh^QR7<c4/iH&>k.GtqPES$Z5V=$e
;?A0!"")idL+j!r(.T1HSOt9d(TP#%'g";0#_5B.MM#)OeDeW[lX]A-P.(\["fY!NGOj%bmZC
>5-DCZTj?7-k7"pemp=Ygr_+0?T'lWIA2H+AU$OOk"io7b-9heI]AS*PJ/!5:l'*eGh6.sO$b
0S/`gu[<`*Kq^*?(qiS:h.BWoI$`(`aa'/2TtP5fMoE=@F8d>]A-R\LpG*6.XmqFSa3lop&)V
7TdBf_C++gI`gt,EeSEioHT+)ou)?;^G1=b;AZ=Y7:&A//`=keQB#_=G("4/@sQiEQL14jei
pL!e%g([2QVbj.m6*P%GBVq:;0d:NelpN2a=r=)=oTNIO:O8ThNGZB`W?Pe2-WmCo<AaS&]AZ
5%Ymi0S8Z%:Y"uA]Arkj)CiNH@`R[0VdCfHE^.,l[e#\I.0UUCkH_M_&@c?G-N2IPFgRh-BU@
2t$A`adn=$0rpJ^(H.EqZc).eMQWW)(qZPiQre]A[.#fk>"jX38(+q-j?2Di?O>LM8'dFEf(`
Sk?;HO#9PrE*9US:6SY6tP!l<B&Gloo/!3g@8O*cta^-dK=,t2kZ!a&B%qApcd>gmn0Rj2CH
EI`O_im[T2BJUQ09q`tN4i2sO"7uQTZN5/<=KU;k(9[c^2u8fTc(jbWg,.GYiru\V;&fG[!;
+AJE\rWna$_$h0KZ2[8^9<V!>-7ahN"OJBV8rB'OHEG"?L>2*m5K!5,s+dIK?RY7N`/kf47P
SqSt]AXT:ANr0@aJJ;laAfO-`%9a9P;;iSos!``0GG'e&_QPj;nV)aec&g"=]AuKk0(R\l^pBW
JXcRn$2),eC2q\[:),S[k.j[KPVfd<`!9++>[*>GtSFrA^>rE.k[3*b(,!YGBHI0\I`+hoSL
CbPKi0qE\tbQpi!=p2U2&:o-61P[a,El8HQd:bO5OqT;;#7]A$2:DkF4mb(irekMGRYiZ:0n1
OdU#J;;LQ>Wueh50U7YkaaSSI0"F?Neq?+SdbWL8U;-goiK=DW64cHHFD3_Q1O,g-K]AFJ#]A&
p8N9K$8CbC-U3Uk/LIRLN98OXLqh(@4Lel]A2.65m>1ZPDq?7Sj>>Q(NrpOZS6VDrMKo!NNKA
YKSN(rH**&\B1LZu3`Wkce?\MDU,:4V@]AKINJ#<@)([(geQ$fQ/r\qg58hX(5Y&jmU[PiSUe
mGPQVbe^I=SX5WME1kC<(.LiD"B)Ks+."ZaXT!0R>KHHOW5'm,41%\67TGN,07jcHiME,">g
k35GN\Q>^VW?MmF#r)cdpg(@8KhED?36"<#b,DOJVD>EpoS?E_7EX%YkN%;J.6O(M:8#h9hS
`DX['d@fnF,JH5hODYuD19,*UL^Z-*$C+@Kc=W0r\atJ]A@LE@"\WJmYo.3;@rM2fMD4Q5Y$D
$cCh_mIck#5>5@%.!:'36F7HL.ck$ddT4_>;^$'\97UG+mn`.g7r:Tj82@j!\9gn,h?*J]A@3
l^o3iY4<$'sY,.Ok1M(G_!l8cU<!`q0@dJ"hm`<dk[YF>h/c:od:'KqhKDTu".AA@&A?>o/#
7!XB<?4GAliErcJ,4&m@U]A.+Ugd0\U7cksS)Nt^&o1-UWDh@4Z._poTW!dm&uE-G-IRH'>Sr
IA-_9EKo*.J7]A*bBj_1C;W6gT!nUH1_qh+-F;GDrEC`j]A=O_L`g'4tF;noAC`p7dM4tm1k%G
7X>rM6NAISR=1@e?*1i.*"YrZ@#>V*o9!.Oe*(emFqOG5G]Ad>cSN5PZeLt*iD5RdT8dgU;.=
4A\Xn,gF8;)-@0qIqTk]ApDg%Ff?p9K$`pVIM)',k,2Qg-H<,XneNoYlS?ae1bURlH!X,;elM
i/D0umbEZH-?A=kj/.'NM5A^m_c7sofP`-BTN4mE,VcUYk6G#FH`4=KG0bJNA?9gcV1_?4=8
/9lgd[/!+;BOAIMJV$31l@^pGFWXab/Y-CdWl;a$mj=%_W>h^Gt0InYc1irUlNPO.\QAN\Dm
5r3VV[,)>3Ts:oLF*PR"?YRrp@Zl\9%ml?>^o;d%g:AZVEnO-Smj(^WTj1olH=ZpK86Jnja0
D+u)FU9O5(MaO.tD%3Q>=;13IXAVXJ)1lqQ!N<hl7pNqS52i)+8@R!`M^>0HeX5`'2lUm8LK
sCm"uh*f&!cHe?p!'FE!&M5!t?3N$C!CN:\bfQo/gekoU=7Z>!q3d";[#_l#-.hTuOUu^#]A7
,7?fS)ZsU>H_+2%G-LL?"S!&OG@i;p38<o:I_N%(:HL)QSmg-Be,U=IU(O1=_egA)XBmdd0m
)\\Q)'/mkD/@2G5\J3oCt5lr:aOT1Z>r8*Ng\<YqGC%Pk2;Uopb)eH=7R'lo404N]AC-GWG[4
4WVO!<?*^*j;Br/sSYh7$9^AX4t^D>BcW$gCZT7\,XIHS2Te#3FKl53r0e-6_%o=>:i<7"WV
'_SouN@m-k`]AI:?J);Re8$AhHVt<T4C;kTAk.[K%F1U]AV\509oZ5N;/:i.EBBfX]AKm=&a>B@
#YY;L8r'h)FOq=OtH-II+jMW`bG=e9ZsJD>5p!5M\_2O]Admp%sJ`B>?!Y'r`R.C%Er=.-JRK
&_h[pA&h,F1#hXZi%2,_JG3,)99!BpFcFcW$(frF+LSQ`3KSu[gj<DbVEYh+lA#5K0$.e[AA
53,8,kh;&c->\G<gk4Y8&k\$DH+>DMGm6]AN;smlD!GIFe2[i47CdsiU7i.BN%WFD:b@)JCn7
sgFY%=Xa)%d+T/1d36*tooeWe7&H/)4JR1Sr(.@Ig!"GR);6Q5,()>4WI\&gW*?48)i;MRo@
Q1B0m1(DZgcheKK2-qCGitAcU#9Wl/$`)@"X%1SrlibXtlkq9YXJC[-+-KVMIi5=i$M9ldR7
!/X$]AYNZ?<,(_AjV!:d%=C1UqXabid$[_S4*g8aI"u\G]AY6kONp\b),GbmlUHfQ`U7LO(mXe
8_N+C&#=el9#`TmL-3Q4ek'8,)VXNB1"*a_1k26\j^F)E>pMB5o>#Rg_g))J-&e2`%.5pd*C
C%HVb-qoGXPfq^Gj(5e53]A_BiOfYcK"NB73=H[rAqCMgEN2`p2F?M?Q)ds`]A`*EWom]AR6!D@
UcM0t.7Z.S\I[Ar$/>9.pA$WF'l.nOt\WIc%<E@A50ST,uE@?rqo]AL0Fk%?RDEDp,3/a915]A
mdJ"/cB_.Ymae"h>V]AnqhPYs,6)cCc*4(^uN%(]A<-HY0YZ9b;b=&jTELa;0=Eo975cZ#8f4)
sdK^K1XEP*U*NDYtd,r4G\opN:2%^:R1bc[ZM/>0H)T/!aMi5i$IKgBu7s'1`c]AV)tC;>0U>
n&ABZY@Jh8+dDl[$^#37<4R^dYU/Nl#'%P!o(F$hpMe_^i6ikp>Em1;dio&Z2!-!l=i:i7Oi
-fA7_rj%Y^Kd=tf!aKOHYh;s.W/i"Ip*KOS7V-Y&&FT?A4c&Kr'kWlGmr+J22AcVG#i#V3R[
;,S9,I*jZ?505Ir>Z9k!d:2'LH+#qMI9q%jZn.YW'@.^$XdaRUU!DA*"N"'\=i\iK7AN!X^%
""AY8@@dO$>!EaZ!I5#bq;S@e?@:dhchLA.oSklGRF5(H\`&h&]A>oEurgh#L95\2$q?l/n,?
VHt1>14-1.:O,;-cK15D4maKfuBU3<Y2/j\Y=B(8Bbp@;flb$c?4[5]A6q3rtp?YO'7aF'MoW
,o@@>kf=le/+mFlmk"V:crM>i8P:u$p(B&8LDVr-eKjj2[jmTC#n(bP@GQ0#~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="405" y="432" width="163" height="35"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
$("div[widgetname=REPORT7]A").find("#frozen-center").css('overflow-x', 'hidden'); 
$("div[widgetname=REPORT7]A").find("#frozen-center").css('overflow-y', 'hidden');
$("div[widgetname=REPORT7]A").find("#frozen-north").css('overflow-x', 'hidden');
$("div[widgetname=REPORT7]A").find("#frozen-north").css('overflow-y', 'hidden');
$("div[widgetname=REPORT7]A").find(".reportContent").css('overflow-y', 'hidden');
$("div[widgetname=REPORT7]A").find(".reportContent").css('overflow-x', 'hidden');
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
<WidgetName name="report7"/>
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
<WidgetName name="report7"/>
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
<Background name="ColorBackground" color="-16170125"/>
<Alpha alpha="0.35"/>
</Border>
<Background name="ColorBackground" color="-16170125"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
<FormElementCase>
<ReportPageAttr>
<HR F="0" T="0"/>
<FR/>
<HC/>
<FC/>
<UPFCR COLUMN="false" ROW="true"/>
</ReportPageAttr>
<ColumnPrivilegeControl/>
<RowPrivilegeControl/>
<RowHeight defaultValue="723900">
<![CDATA[1409700,914400,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[3314700,5040000,5040000,5040000,5040000,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[序号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[订单编号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[支付状态]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="1">
<O>
<![CDATA[发货时间]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="1">
<O>
<![CDATA[到货日期]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="2">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=seq()]]></Attributes>
</O>
<PrivilegeControl/>
<Expand leftParentDefault="false" left="B2"/>
</C>
<C c="1" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="订单ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Present class="com.fr.base.present.FormulaPresent">
<Content>
<![CDATA[=left($$$,4)+"..."]]></Content>
</Present>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="是否已付"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Present class="com.fr.base.present.DictPresent">
<Dictionary class="com.fr.data.impl.CustomDictionary">
<CustomDictAttr>
<Dict key="1" value="包装"/>
<Dict key="2" value="入库"/>
<Dict key="3" value="出库"/>
<Dict key="4" value="包装"/>
<Dict key="5" value="入库"/>
<Dict key="6" value="入库"/>
<Dict key="7" value="出库"/>
<Dict key="8" value="出库"/>
<Dict key="9" value="包装"/>
<Dict key="10" value="包装"/>
<Dict key="11" value="入库"/>
<Dict key="12" value="出库"/>
<Dict key="13" value="包装"/>
</CustomDictAttr>
</Dictionary>
</Present>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="3">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="发货日期"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="3">
<O t="DSColumn">
<Attributes dsName="ds3" columnName="到货日期"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<CellPageAttr/>
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
<FRFont name="微软雅黑" style="1" size="96" foreground="-11739906"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="7" color="-16744320"/>
</Border>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-11739906"/>
<Background name="NullBackground"/>
<Border>
<Bottom style="7" color="-16744320"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-4144960"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<Format class="com.fr.base.SimpleDateFormatThreadSafe">
<![CDATA[yyyy-MM-dd]]></Format>
<FRFont name="微软雅黑" style="0" size="80" foreground="-4144960"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<j1]A;cih1`?J5ZV41:M@-hL'/5["A/ST>Y<@Y@EAP]A&!-jYVG6HCLV[1mSZ7"l1VCFOi<A-
kmeTR[k%$CD+PA-ii7&g8MDA@^UU9WakXao'.kSsabnndtf1pZA"?IF%*TSBlQ-\X`XM,\YT
BAII\GS%!)&-\))(hRjXTk0:6Co;;cS.n=DO;s)=dnD<<BI^C]A%E'j=sDKL,Ua2<gDMr%5,_
"SQC7p,23OJ_Vl<9]A-@fLdNN>KakZZ]As'B*IL0$EpO&dGKn-5?PWdWA&#Bb8`G$#S`*WB)[f
G\@Csf=(O$S*iXKBYXpSoY#NFWF.c%E^^6]A:BhuNf:jj`Mm"!=`%3d`'V6f-e6Z$Y`/mYfrM
i\!_7'T`A*rH0'mH0`;[lQ%0u8.t:!>^L[9n+nNTLR3&h]Ag`?18AC0YX(AE<6`l'd-Ec&qE3
K&$?[$!sdadqRV!c2B=mP,fIqu47WmLoOHPS.*@AeC,:/_;T;XMg/G0\Cf^[=qmRC&03?@0<
\D:m#8G@qspRP#L/=_j1TCO<(&68#`Q;SUS"@'782IH*CB=4ua5em4iZ$U;('+.'BJTIoE^I
OERt*&$"Cn,%&%lpNtW4mcY(n5\V&3).!o]A/k_^V0AKfrakV)]A2,HPcLj+o\2plV)cT9,Zin
7B)Siin<*!7/C_&'i7aHMQjJDY.*22dD71*h:OX4"afm0Gg`D45ZL,fqVi8Frk0CsRg%cYI!
s8=ei\\m5^V*e=bW?QW/+qKM?2P+2;EZbah'<"Hg0`dO'$-dEdj149E10H_JkFO4^A@QeTDZ
8RG<f/&L3g1B;g^F1R58UZ9*t!GiZ;HIdKg*&='`f['9aaqE:>mo'>.aA.XfK!lp?(mf:H-(
5)'@FRI;48/ep]AtKG*QER\(BKV`;DqDpPM=fD^d"@fdb@4$8DID]AJi3O)*fLK!Uj?Wm$6oaL
,_RLrV^i,8Yb,Zs+<<uO@B'+ndFJ>ds5Q71(p=n&*T1gnl7&S\P(.$n'82mUP(ZrnFCD+8Sq
$3IX@5GT,YY_.?"g8:oDtTF(^kJ$@?WWW+puD\?N'=InFt=6b4[f"`9UCBF@\[BolU$mpgfX
K_^6]A4=.ag8dY.tr:%#gUPE^S^<65$kYZCG40pPa75L[=,]Ah5@NJ`r*`I'N1-NPP"ZHKqUg_
Dr)01_)RC!7[=eak5m,V3nKm"M#dq[G`9]A?msVP/>aXh+gt57doPY-\huJDU\;uD8m3++Ggo
a8V_errB)]A*?/%"S#pOV-f9f98BDrGP:RMH:1EB-&,^H*Hld0mjGAqbJe_\+"DS<27?TT'o)
jTib/(S;s1C:9Uf"V*(&r^3K^91nHd6I4goT?Q\:EH;D:)$e3\J]A5^*WI>)_4\*tDLtGP=;L
Z(lF&Vns%-,o#\W,#Fj-1O#@VCpJd3@2PB?TTa-i88B:0Whgd7'Z2qN\KNP0,A^<UZgg[rcI
:KhjrGe)0UB6s!W'C#D#Ye<iqh1G(]AO#,t%kCiJT!qppiE?4172n_qX]ADh?CrSJ9T5*brs=f
Ur9_WJ".4hnF\rUNn:dE4f5r<-rN?@@%hZEZP?TAlGJ%qtjS-^q6.c-Meao@SdRBl.Fqd>6?
Q3OcN]A3#msG0Pf0h$%Pmnb;2RErt%G8/kBbo]Ak!Y`Br+G7^I]A_0hS#S8;J$!O+TqgC/Bq,O)
@VJ0paYYS0a4-M?`+MhM)Q?lbio_2(J1g-VD("N,+RP$.N-4`auMC%c;nrci<a,`]An$`LWSa
sb`k+JF#Esr7i.*O-4Ir7h(DlNo64/sD$7O&\E'#LS.j&2894&K:6#I<nZ%-")1)`0ma>sr'
D\?r5.U7#mE74e)cngR[%Ns:7,O(iUf-?-Ep>4TTTGj2gp&La7N`unbg^u>rQXJ[Ci9O"N,`
_t>O&Sf:7P4kAfd1g@"K[F]A+"!Ihk_geu57@^7XiHn.`rsS32,dih]AA8V0h[>@tebe'9)[Wj
g_kA-^[;aDf4)Pq5$@S^9B*@N=:]ARU)WP!I,":qP;PR5/ZTKl6<96RX-ac6e"LrB=NaJTV4a
MUn)d<;22GOM?9Io7$`Xi4_hDLmA;ebLo\IcgIGm,>=Y%/iJc[PSqu$h0s=(5Xmq'@tg/+]A+
EuV1<mn7C<ZM&X4BKo]Ao%5+54g,qu^"M[OEAD`.F?R1oW&cLI:W>,"ZS(\k-qQ1XE1M2gWFm
]AOHs,abd.W]At*.,?04hFK45d%pc4b6QIpJT*QC:C4Cu]A93dNc17P58?b,T0g,;^=tP_0PaH$
4H:9IS@-W9_%!]AIHCAH)DmO9jf`8rU"sCP&l78nI_rVO$&R.;=\Om1A%VAGd6e#Tm!B<fjM7
jjsniOXCn.u'?Y[)D[J%<G-BOqe=mPjJ0q7Kd<40/l`W=Eji)JPfZ`t;>I8UJc8ngT%b:V&8
PE2dc!H#On-LD#)X9S!DQYehW2$ok'emTA;T:3Re.K[%A#qdF)WlbnBFNAQgN'-i*VK7><VN
!'Y.a6FmdRX_-ha\ra(,r)+)K&E4M9sY7I8UI(JL]A3EMuNuVC(6(/XM5gnOIcT,fW:.`k!kI
WCF6OEo.#bctXGhGu`1nT569>65@Ri^fT#FW[jJ53@IiH<Q0iQWE%ksi"0IU]Aapf!e=W^7O\
uV\&%@5Zn;fjP)7-K@VQ'LoVXLV><UEa-V1(lu<30\l2NjFA$Jhq!OM`^'W\n8"Geu)E6-"c
_s"uo@",)usAK*,bhhTsl<JnLV`LX!)?_q2?ZSOMq)I3h#,jS6alAjYT^PIkuANAi.OC61VI
OR%jZf4-e@kW%Vb"-^Q$LDeY!K[$)%frAA2P"kCZB:EO;]A0N=/))KZJ[:1)WT%i:8BW#0.,j
q-jF[`Z7[F0'S%Mj1(&N$79*Q4^$Y<f5r=3>--ACf]A;t*`%m;RgCGFJF`nI9e2!hm=`p*f98
_4c_OCIP!Snr`&X]Ae!uO$(nmJ#:ae`4qn6,mg^#c_af("[Q\0De8fb>If9l?-5TE%ZYEGp2u
ne#cqDCrK[=(V9n=Gh!s<&`X%?%<`jf1sYGDSK;%jIn.=fj/HCh*R'No53a-#&:H1XmC'eDR
d+3F;\'\k+5):F8965dP*6;EI-Y8t;^&dBhtjA0XiRSIW-q\+kX*P0ilL;n,ER-b_fRM3hc/
uMu1[!i\R;'DsB('J0`,*'2..jb+N(1h5*W9fo-KX-1e!5ZeRggNpU(9brimE$mG.>q[S)A6
4jA..T&j6R7q>@GFo<3:?JN4>&XAh;$4h9kr(Ql5qL'-[t]AW8P6cEcD?D=.KD`#^5%;S9AG+
7!&0/MC)1X[\p*rOU1,:okD&&i9V[T4*q%#;S6QBQfEclcABK442&Ht$m9U33#khPSfANW?%
Gj:Sk0"W*u!g!_Hj$K\YWA;UG=cW:$6Y+\oF`Ij,]Aclq<Dp$*Z4MBBs@@^6]A[e3O)m#6B5"$
n_T3bYBLEJlnk^GRij<3Vrc6QUBhb[3hW.r%G?j08-7[?rKo_In(Co`<LP?g7AQQi:>/uGn%
2ik`NNr#jP?A>:<V?<@Z+^TrC;kH-XG.U*AM-?rBcd_UoP+@`WpA;]A_%%(9*X1d>AIiH*RF0
$E0Rt4p-u0@JPG7*k@*-T%="OKWV&<;ED_F-d5#W5R-"102$K%,,c4+UoQ!JH)2X.i?KO`)R
7(r'&R,'%@8:bISe@hh-DVuOeO6)HaBHq;9es@iZc^$Pf65clKfY^9YlK$Uj(>c2FFa:^b`#
Q1.q3FGnP3?kh&*-P&E]Abtl[0dEYjZMWre-8h2m!)lY!mp@ZbZFC*1Dn9]A03^_n1:0R"oS<h
1gHYi.hAblUbeU:1fQ,I0:(+\sf*dLH$S`%d9c*M:\b4g+-R<Odi"57bZYXX\>8Zi]A`jFT&V
F/2YCukc^20lPHTVoSk2E`A>Is-&CS-#%#2_3CI$M+\G=k-m4ASEDWo%5q0.NBee)q?gJq#l
oL-aE5b32`_u%,6U0j3lJN1*arDR^L-/iJ=VGI9geX[TO4:Z&*`mSFHAui/3lb4_NQe_<rSr
h;VKSCh0;fNMaKul5Qg:@9I(K_ASni^PRH.YRKq`NU2-_1F.kU(83@1^la$sh]AP'p\#H.t'c
04L>^6lun2q0c@X`K=[-XT`%&R7;8+-k%pe$CmiJ4k'e'(:$R+&%PntED6BM`q#%C55k<#lJ
o,04:=V9_qk>j$I;j-luV>WF'pPuegT@XH.sGk$WC5\pkXe.Cs`&KE32d]A:V8Cb:)>+1mKWE
9KrGn($1-<M2)g67Qh<AZ,ol-F%G(F2%f'NQkQcBXH%IYo;mPR0:gU:WNW5B+9qVk/!qBN7q
lVUTh*f?-$CEm1aD-'`L_L`-8XpZ.<HD<nc+b_@PXj.Sop=#H%=+U%6hNPlT`>0j6B%Fk6br
[V'8#_+YAZ3f[D6cC9(Kle1/*3n"m4P-O%7,oj+U9rX31\<TCb>F_4<.R+d)n5\/Tb:"q!BW
@Rli5`.Q&&'Nj]A#sFhP!i#;<e@7kHoRhC7Wput+lr@g43jbZ3nRsfF2l_-QJ-@PP\>gXh^S\
(FQ*TPdquWEAi;C/#>*gF5]A(;(8iX6QK!_E<L,l#^,pLRM6,h4Vf:[H4Z"\?T8YIb&Ll8iWB
)tM$N:r9WX:u;rL1.pr3m#"i`8Y]A@@N"mbHLCScj*L'n>h&2#k#H:C1Ek&-[lAC489h9aKlq
$!i6TKDpCt8qK@Ht]A,fG1=0j_/6Ac@rmf_e-0Bn:#6=bLNQhNiMFJ;qLe2^:V"<6_,q*QegI
6kXTDFq-Ut?'-(QX]A9jJYL`t@Oe&KfE.r"@lk,a^0bSu)gqT^g*.f3CdH\*@Lpn\b^,e42c_
_e$\TdTmW1,urFm3&M3Hc6n1%*4e*Q=W2W1h(_beo&Za+G?V0GGC>6L.eh28e$OCXM]Ai\_U"
6A#ZEo1=Np-^2<`JqdgcD0YaDcika8,HnI%aF+TP)P/;Ag#]A4+"Ba-WKa>&&5hC2>2$J:9n=
d,APk/*9q;O^(uVD/tc_ZOGaqC:'t=uB@'f\>VPi+(7d2a7o+dVSH17fo0uY>?_PNN*ma8Fl
85=+c'FlU?":n?6l55Q*<eE^A`scT')q'$L[Qf#WN(m$mU?/J/mEHYB#eq&r,T@G7Is$^2<B
R-DI.:<pD'd3YUTYJb*H)`,KLqMT_&S]A!Ufmb9#,<#]A@qs':OgWRUA]A,6%FZ3WPY"hFem5=f
!:U?_s@*Eh8d3jN?Gd>+nJ&8&Cj4Nh^p044kr3=dc-9)hH[:Lb",.n/]AnJ=i6-FDu7YTCkVa
tXAo\n[4hM73fcCa-^H\%!-QnDA2o1*feaBa%ss,eJ#S%n-\(0lb+qjD63*i/h6kHA5)W-0a
K!WHo;1pYqTa8NqR:/uN2k'(&dBs!&<&>i]A5OcG"5=!E:IO7"Mg(!(LW!fT`*Qm!L<#dIL3d
A\85TC@;fgZVT`ff?]A7Z@+d+HNVmg=l+a,:@5%;U,rWCu:k0.X6Um`g%li)rnr3Z25raW0YZ
^>uOS'>Z(`XEhVBqABII.hNq@'4M!gPu"8[Ec*G6B!gWD,34O0(Up/sLb;2,B-'`q_rZ0hH*
Dq#ku?n`\X38a8epds7Wp!!YXc7cGRA2M)=SqHo9\LMO^@J9e0u4)jq#6>FBTs5M8JhQ1$08
9L_?[695IAWhQtFcm7"3U!<fEf=0I,>[b/Kg4WmNb<;0Z2n<^7-MosG9:8ed)W*1b2f;DHPC
$N]A>>]Ad0+%*KjmX3@n2W/P)m2ld-1YJ8'jqU_r,.51oZ:^+L=%:j@c,D[5!S:cfT@e0^2LWB
pZ'#KoK<bAjbb1K7264Yb2X!)ohW3:N'gmDlu*i+l8!KWu(MY68i5t1M58g-6hEBY?GF'P@-
d5\<dQpFYm&,?lTbK\\:%biXl(H'P$K$'CJ`DILY^dp6S9MP3-!ef%PacCsgYO$0TD$tT!>m
5"eX_3+hP:J!Nmi4^f$g=D=N1aclnJdDFHn#4<a@`HrCs/)X^Z!!"%hWNd,2"8hWU\\3rjsu
H*;)m-Xqp%;"Ztf(?3)3+jof;Kh&b)4lZQ4nXW[DjlU4]A^&J^rd'(/d>+Z$FlT(oQ$XuS!#Z
ZEKua(FIe:a$((h93TIj[97s`'G9e<JmDP0!M7ZU,'J(#3Racr)=@/@Te*r2r0K;VfA7eb5;
1gUs'(O"4nr(l7p#f2a-)IK:,AD=$^DMlI4RB)d_dN3W5B0K`1SR5AP3:3"l;0*?NOiA62X6
OKfdilQ&kj_bM?EIV_E8BmI=h9<m6:C@>!Cpu%h16W<K7+:]AUP=)0oKiV"f_.*YF[3*h)4n7
\K28U_-ts0L]AqK#^4*4d18(]AXX"Z-C"<5Y7QOm;ERF(Sl!S:;Bs<g1,XU.6p"E-J>/r0<Lk_
NNHh1sTfAB&Q>cl#R&J[S5e.,>AfoTPeDDN5*:l_9>jr>X<?'gdELUiRc0C]ANn[51"CLU4#S
fA]ARp&=!>g@Wt#V"H.P++H%*'H@\S"f]Am=G&(b8-<*XpO>4$.&7@,*[2b7L>#LJFJT^!&8O-
tXJEK8.52q9*_N-2K%`4s#-]Au^uDf0AbR5`6[8V;qkPGKR,ajfhD5Zd]AK&Fg64qN?2j;"iMj
9jNZ+XF[+XRd1JT`DaeKRD/)i<2\*X=K@Of3D<c>.gl$.`Q8;JjlZc<>Rd4,Hmn__=Ym-Z0)
]A?9pJ_PF$-PH@Ef:ea<]Aj399:.&C,i7QT'a:T5.#K88K".O2L"j_+dZ7KHaAST@V413-mIpX
nCACgjVaTZ\^E9lN)nKH]AmLAod4/:3TVc#cr&rBcna'KQS>LMFZ$r`saa"0q<#;aP_!YE"G9
,>dF.Tdu?aX.TN;dkGf/C*jW\0D/Uh82>A_UBU,(VLLAmTEaeNT6cFZ2A!*Pmd8t(r-<>*b,
u_4Hq7WIZfM`)T(>fI_?mZ*NE-/-=nGRq1,qBX*j.\mpZloS\O^VU#5==Qh53C#PkCa'L;7n
/jPR4S/;V6)$/CmX92@(GRk'L$D;!W)(;Q?hGh=G(V285Pc@XfK#o5FILaB"i?Q,.$\<k_EW
@]AGq*>gYC^9bCf$eg6aIGtMo@H)F[:<M+,f^]A?!KcVZnGXp58=Kp!%_Aul)1)+H?b(<0PF,C
0npbcUM$NE$r&VjsXC#Gr@^l59USar6^UVA_9j#Q)c`lBkOb8XVTn$<:JHBOA8mmAaKfjBkJ
GPsfl*S6U#j#m3N6F"@$nVO8.8VFc1>%;UH2c(NDk6$hhn=nUE(G!UNRSeMTE*]Ab7<q#/.u0
eD@mY_-O>8)\YW`oLd%o;EZiDFim4c?kfOf")WVh'^Z7?nMb"-5B:,9s^3pF"5_3A1*ro'H`
MN,+<mlV>J&$U./S_BoQoV89+@HE/,7Aek,UA-Ls'YpoE?Mr/<&U."(,n;S>(iDWj8P+7\l3
rdEB*P$PhlGcoT[,;#Le5]AtD$_!?0^f^Ua-!$BHNe<]A8tWeZ#i&i;XDFV\MPn-'T1CX_VJ'R
W`n6cJVS@R,-,.8$PsqH)>Ypj%ZDVF#;6UQ5G7+aR*aT(GcYUb&Ri0$%$H+Ck$V;EB#3f*M@
)17/[+%;>c/B_\QqD)("I[f9h"\u\[Lo9%?93HmWT1-u54"&=+b^^.e:<jj#&SPJEjoruULV
\;qE-jL>pq*&F.nTVh\"(s<nd3^kC3P&XiV6]A`IuPmR[`2,Qu(YgBS]A"t:fb3f"'p0m3Ic%)
n0%O:>'nm9`lrRO<HaEBM=sC'i53q^gWN_/8b"5=X:HBG1s*:PX4EJ6D!=j1OI-9TSJTsEO5
\KA6)W0sH'6V:`Ju'BUn5`rEf@6!dq5DdmM42dMNrDmmbCa6E)k]A!M7[uuNg>HP.AsYGU7dB
'ik,'29hh+-12PETj#unO+a@F/lq8f721ipg^`/#7P9'7q?eoHAHdI]AL9I_g@J[=>]AN-B?hZ
0OT&C)tN@/PI8DYVSHHB?Qk!h\4b;fKqC*+W,oB1!K0'^<>=(g25E1PPL]A:GbG@">DTuG8#>
p$A$fJ*gc$@5Fc$E.^)*)u3HI*Frn?L4a,&*h:eDW><&PVgTCcJ#<+a)/%,2&8b\,"Z`PZou
>uMr^MG^g$&sJIl]A+]AFuH'L0aPad]A-V:]A,;>Mc#.#pBjhn8bX\:"!Op]Akcfkg=q`gTU?GgC!
4C+3.I4k=@lTHrl#T(I:-4_pDJBmXqb-pqEkdQHWMKaqHH<Td6n3,"X8>1Ejc"hQ<"kE&F,+
pWqY,cgkr4_ItkpTf&V]AT6t'Inb)7tdL?6g=Ys!\;@LDgo-Wi[lZUlG/7s;P2=G0cbS9SR>i
W.BhlXtHZDZeU(eD3KhCa$]AeefD7X:;RV"5#oN?([NQ_LQF=D\IqFZ]Al8tQ2^`^rHJHUUi<[
kj0(7ai>?<0*LZF,\MCiHM-s;gq4qKSe_?3aa/7jM0)rFOia!X&SWN6MGOYSa@VXfZJP#>Jt
WUR9!m,F8f]AE!4[7bGP1P"\sRE[K>U^WJW[73B,Qm:+\Xoa-V9-Z*DC@=MUbGd.h#6B3_3hS
T/;4j@b?h*#FE5pAsIKt``/DV7m<k=iD<gV/`*H\k8QhtbD&X0Q_$_dSFS2BlEKI9/u\PMjk
(?/Z(PqPjGdlsOS&(t(V).Vjdm*.USR-cN+"q;#BDqbDpq-UpYkA*Pa'TlN8!jLHjMH_QrfR
CY@hV6Z4#%-Pf[kA`U76m5spY;]ArkX_(kZ$saI<9&A!&^@"0@/UdKlnS>T-0,78l=c#o+qrl
[Npm4j:A^5tYW><Y%Yo7X8oJ;!P8Sp&[Tp3$qZ+$4Z8qb1A)/SdNcF>.:b`bH$4r$c9YOMt/
'E2+AEM]Ara)")\QOJ`XN-/g!A<(KnAll1mg=uTUt`AY`=-Vs9&r9?g0)%=CSC^>_H7ID)+8m
JA'=F@C3agB1BQ\?EK8gq11WXd#Z4_W8NEa,J^N&ZNcjbe8N.fmX>WjF_!`o'g^dEkculkb:
=WAKsC58M#.&U_lcfZ7HLW/b@6C%gZQPah"7orh<PC@8_35r&1=l-`7\#]A7nqa^.(kV(lF><
8Ni6RbTaUi+QY9S>GJ3nSu$?7Jc0P;=jire0^<pl<tKp\Cc6+kq<Dqdr-[kSs1<P(ClbS_tK
oW=Z::AVVcOq3#k5&R*O6<.+)c!H3:+@W6l!V>M>(Fj]A3.\!h^I$6m]A#`?,7l=nJ^9ps.F>>
JJWj#$:#=?35Pc_4K1r(BUb"g3ufFub]A7GP`h$Ug':KhFC&IOT=28XBCYek"8CsE?S!o#!.C
B\[G=b3bV:E?78XI^S<92bRSXa1F$)ZK\`us#_*9PbA7"fXHk%%5qCYJ+_g=qXAYo-:6?LmS
_*btaK$A)iMoXJc$HG7M:5M?5iX+I'nbX`n@U*oe44/To."'j+$1U^]ABLV_+Mor_+j"UBhO;
A=7Y/hY(<J+l3Iml8.^PI@tA=,b_#<)aGKaXF;D2bn]APqTRD6V,'I0b+OVN%8#:/k+9M:*l=
9AB,nsf]A]A(AVU[,:WPbq=Gc>[Spfca)APc\FF*dttrWFt3a7^%:=6m_-9L67gjJe]AUQhe]AhT
pYF2)5@&F-4]A!#M^p),jU8B6o@q-hgRe28R[u+Fha4(Pp=^U2>U0(kqiLikI3OFkniN;jpb.
fT[ND2ulN3gS3S=iA6qg(4(s-$\-'hgcOij#>4a1AZG8'SB.W,6D.r:bZ>Y/0m7[2=Wm,&6;
Wl!.MLUPj+FT\a>0jFo%,AP@=_V5Q:o'k=/hnkkD-j]AleR+E#5=fI''?LmlaE'5u:m8bDA6E
;YY9qpU^aGXo8a\qY2S=u4.eUBY&$n(F8"L"%5D*$Cai8m'eHCn$hr_q7J%NJAki$kF`peq^
jbHUj)X.?@G;_1APnPh7p.O=kgMC.A&u&k]A=]AYi#3Q:ghaXIYWAG1>';\<aVuB["ADU(+/;V
16_)E:JK&NU\@=hDkeG41%UK`8fS3@<V)HGTKg%,S\^Eo3mf!A>!s0'qs$s^CH5\5\0rh]AU"
^h8kCGSTF]AH$3nf`NT$:,n@Er7Uf;SY]AHi"B\-K7,HK3aome%oGFr#Vc[2H$*2?K1#,'Tl%E
Q*?jhU28Ft[MH=pMPU'?ACjS,]A5#:,lONY<04`#Zl*/o/XYIs5]A0^o*hL6CL"#6ktYnrR,=J
l\n$CEd]Au[2GU5:*g57YW(i=(et@]A\?KcM<B_"NS=5T3W1-o#pb]ANOSOW5`EpM9ffP)qH3.]A
r6/i)6JL6;[^*JsdX\VI?%H/U3;rfo,ldPt_m=_ufei"klu\;XWW+lqd2Vp+uLL;.m:<AB%l
IR+Ci4Z(JqMGj%goN/8d_,#p`ehSeaaF<b??,PmG::Uum)3P(lIm."Cr\HTcDGZrfTIO&Ts$
+$PY8Xn3p@e4&@Wl`'/f-'`5tc0s@9,>R54+oN.dseLIi.3q3GtEKfTpFDbEDeb\<^X`G5?8
[Q4o_j9.bjmeE:m:O#aU!6JH(j@cBiFMa<N`6"PH\Qm<N*X)$iN_?)qaX0/RNaUD7u)HZd#<
MOupBsIWo!:7"taM]AGCQ."ma;4jC3k3i9C`/!s'b\)WTcO4eH0aPJ=Y`<QV(Q!Y*s6i_EgWf
tA`jGCVZ#Bs81IlrC=R_7\7QT<Zq`CnRGt-k5\mI1IUJ76KN5XBiq;GQmQ/Z-O!c(i+@q-N;
Z<Ua09uF#7"7?lt$:Vf&GUkXIVt-P92DU$+%Ob0[\`XE;*R#h#.0:YtBecL-%CaQoEU3-uK$
upmPjZ+m.a"&m4Cfm+N1#B2_=<$$kPrDD[O&Walu\(Pgtb]ALbM3O&7us8$h2Be.Sn?Qm]ASgb
h_aES@J)asg5!YLGYu\@,i[Jpq"G&_MmdI<31^C"OB``1`Vtm'7\a42"g1On8f(5)"p;b:i8
JWt%Qn)5)WjlV7!?YEO"LQlP(0.b-p#C,_rV"SQ[%FG,Xh4"%6!Ngq?-b?uP*p^:f(/31bFs
O8:Rg#sYK7CrS)h$:Q[^B!r(NIpVH`VaJ`9&_RbiGWCneT1GC:OZZ<6tkZ#X5V<t:Wn22ubY
6oi%t:,bjR09*AkBPFomh!#Hedd;B(pWsZ-Q`YePRahCf*[af8VTfa*o*8t/-U9c6S7?(+0@
oJq2#=bdKhfNqZh@oQR22L[(dW8AXL$K0Q!K44g<YVIchm+mbg`V^^(^Og%fbop.Fe-+30Zc
^gq)d;\u[L'^\iS&;[c-.AX\*>_4Ab1Y?IB._iq6<Lc^F\Q-VIpTdt:jMOdln8'M*5CV9tE3
:e,MBpMoI$[1q#iP5=pbea]AQlMp#$!<9dP!A"/bBrm'RrPu$8rp]A`?H@TXFSqX4.XfakcZVh
mJI\YS2^/6X%%_hfFM>hB`'[gYA;*VsdY.Q#o@H9082G4nU=ObrDDHoG'(0H1oei)EOMbEpV
.%#@@YBYBKaN%dPXAs7p"lhUmT_8&5egT"83%-#j$J/_tJSHCDnF3rZCRdCt"k6Oi^Bo[0j0
=*^'">>Sa!t9&B3WNYkF4U5:^-k&<R<t.$3j9H^`W'T.M>+4YN4kbPF=17CJ6VH<q5pldUsd
'?J(lp#(a240V)@C>aF/\ZhfJf.Ql*N@MqX\/AHFF.`Vs/'QHVf[!(0pK\dWR&n=:j-`lFlD
Z^_br6/]AV1qNRtVoo"KEp)rlc,54q=@?Pi1]AGs=HYlrq25#mZ8D(ZpWF0JQXD,KT)qd5,fpZ
6?Z_lSmerNqCh=t7S0f$$[Rfpk8.hF#hmsrZL5qHGClLS0$g-:tupVjM'p:fh!BP[:gH)Dar
NfN!]A2r;T;>:l"WO&`8M=@XNt#b<k/P9hRr_%l27hoZ&[*YfUX2qH%\AU2s[P3LU=7l_o!=J
d1KPfCr-J&p@$S#jm,Ro()PpK3]AgqWd>rIaj5U+0,VT(7&)TdD^i!Ra\@3,DBRD:.,@./P6U
ZmPQEBM@&Oula!6Yk;'(B&F%df/GTC_^UpeQ/'h4&+8\h^3%k!;d++cbdq.k2#i*V"jlogr"
Uorg3,";Jc*r[5P$eBj9L&0QO%))L1g!oYC??_`r!V-;+8o]A7p:"7eTgCA7)'#p^E[1.]A81O
gfh5u9E^"BV'.'2,LZ"kg4c3hr`?S(An_6lEBX=+lkd^LF=^V/P)j;@UE6VZ$3G5mIr<F_RW
F717M0><<D2>?"M>3u+qpX?aV,PFJ8O-4CUQ=4V6[l#.6#.9MgBS??+iYfROE(,T1:i9"3(;
I0;Gu!mc:)r'(?fG@b3,^6MnFP0P5Mm?M5?8>EpF?MWZP*5k)8^"oeL8?FoWgbuh/&ou+Xa'
6X$S!;&NchpVHSA?-QZFH*4_)5&JMS<7O0P,Rdg</IXb_sHiEP"LZCn(L4cTC,=+Mc"UCBK6
1LMl-O=DGhA,:nD%;k-@%6hs\a$fKC&hJf2NFb5D4J.n<_J2oN6<F!nH*be=UN)>`s@+DFIL
aVl>ZTdqIjXTr,Gj)^EDQ,jpl0d+nP6G3S$adY",,KBS)P<k>@H#Bq5btN7uh5#]AY4`qk5OE
b:TI9UT\^%2'L+NI&Zjp;QXdnct9sA4H6ok<qiD,\L)Nq^t5$S8E/h)VWUUrhpr22O'c/e)9
is2gR%hVrb$hJIsVR6C:'OF%A\8jRq=a>nV2tJ[tfXNQ\]AU@raKc%+Oq)qPHTd[dKZf'nRUF
Ug>IIdD6$E"OS"`Ven1"(l:i49ca0M=iT\)Nr`Bj2NG;uE^i)>YYP+LR=5V7(cqjZm4r:R+4
RJ\hD>in0gJOVtId+Pd@cOr25OQ4f@p-Y9Za&g+-U/=#WD?6Mg:LB2;u'$c-V'\mE%tV7LtG
EQX5(q4s%`:+c#;&h8l'WnK_^OG=ddFEj&a@R*Wd\OF0?4@2o8a3+)"m9C>>Adok)s*Oh6I.
85=UtLQ`)NF<sO[VXj(jp^4oMJReDl\c:9Q;rH!m>Y;4YZG]A_PqorMi(PrCBD+a93;a8l0>7
&CdIp1_2*3h!r\)JlUGt,fE2d>:g^LtgtF%=46pf[V(Ki'Orb8Yc9SV3CUC(3nl(_j8HFkqW
e`JgFJ.>>r7\(?6QqVO0"@".1=`WL:+^J,[8lZT8+48<Xh!YMDacgDUe)JX`0adOHV-hP$r`
m08gm_&Ekp>bIf4sBKR(U??QNb/6<#=h5VVXumlbr9Qd5/."mYeERQkA+@sJ4-lB/Tl@90>A
&,f&g=XXierFo@ZEe[uZ?EqsT9[0K>[T:Y4V@7akKjYZ$)Kq!BA2e&2VL=mEb:(KBcATlm=A
5i;\q`4O"Ya#qFWM<2b?0T1Kk0T\YfZNk5eNZ]A9<<\S5_<QOP$(IA_DXVM<OK+FAt/@Y*f(b
i$t@r1<>d.e9hJn?1HL&<%8NrKrfbqt&h']AR5<]ALINOqKd@O*dVuC8d9s/]A/U1[>lu>88BV:
D*,kbbp\7bNL7)6l4(/pVABH.OERDp$Kiui-8X)MM*RnJh_4(>>&hc$'b=%jc"O=md"Ad]A@B
GntI-j20R_@pH)fG6O#[u,5'G<"A,@KnN]A"*5lkEei9HnuM$tIf7$Q>Q<RLr2^=TPc-\p'-R
^370EoOC,oI]A0!P*rHFnl.GD.4B<@+JlTB/0XBBE_S?Oo(I*9<)]Ai(0qPL7F<lHbpDR)ICks
5ugG)2&6B@P[MP.P;0/l.*HLdMR7W1[9SRoJt#*Y>P@,:4g'14&rJWQ<Gl/.NSn6HB'.(iV$
=.D*aO\0).0Po$hQKofG_)REO\#SHc`jDV79L7"N$,Kk&C"K*'Sc]ACaB\;@Y'B5bt9N#+F`H
Y"^*2uO=Y&#*/_ib'4>)tLl/-)6CS]A0$f(U:J&(jR!oj9G9)DDO(fNTp:o\rH\qN8YfU[!@&
^iu_m,qRPITFSd-`,Kr1>4Lmrr8pP'FokS)6o>Fj1tf4$E3t9$31!V&ssKG/SP+2]A^NS%BE$
Ss.HI\o)\>O>%f~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="515" y="0" width="445" height="219"/>
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
<![CDATA[723900,975360,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,5760720,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[地区]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[渠道]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[成交信息]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[费用]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="最新交易数据" columnName="地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="最新交易数据" columnName="渠道"/>
<Complex/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="最新交易数据" columnName="成交信息"/>
<Complex/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="最新交易数据" columnName="费用"/>
<Complex/>
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
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-14370119"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-3355444"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="0" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="8" y="384" width="388" height="203"/>
</Widget>
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
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1752600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[7353300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[全国代理商分布]]></O>
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
<FRFont name="微软雅黑" style="1" size="96" foreground="-16069640"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=@.;chNc)&b.cn^4A-(<AIal:emeBU'2_BsB7t=E(VK,Jm$1/Y/W/@N^cUJSIgue9nBn;7
WLR>"$0E;FCYN-u)AZ6j*Q^+CN-T"EFbkjF5AjlqB&WBDXhS1\4.-IdhurDelJ!XnAj)2uD/
rK[#nXbd9s*-I#ZiqC<L3:&9i_d\OuDVEEi:9:"0niM9%bn#Us!jgUWQrEV'(k9V00rUrNX?
q8.t6Z3J7rW?%P;Xa5,jRuAiN]AduU<PG_RG1RN:!DgDO]Ac@u[67g]A4&9"*1iM6AQ;/Lg\l`F
>+f%XE&_45O^jYc/5rq)t&7B(&Z+#hInGa@X048GK=7b*@FKFOA&FH9.M\Ko&B`0b>_O`bhC
IA-B6a'Xchg="s-n8rsT>+"0So&i[DIFKET+T8bU7oj@rV6$l?g*i)f1u\;67BUH]Ar1BjHN`
r@Hc['g8T,YP@M%%FR3H]AGnJ[GYFUA]ANMa+anXi9#MQgPm\t@T0t<!_,%$2a8:oje:SgQsAn
>Uci.lO.PR>9>\kD9hIT\l<[L?5298>A0Om%Wm+*=cW8o0IG-+!?Pk2lE.;8hb4h/gJgU5!0
9e?&V+OfWo/mbY8)7(phQ^N;C)2hY.;9L=,k<%dYGr0*P0;WRT,4,77$Ue&Hg6Hf9OEYc[`I
<c:Nq_5SgAVB?<+uN:0UV(EB9=;3h\D[#9",kC>$/P8R67JK&Mg`j>,e,XM-#E[k+Ac>eUHc
M5D@F\SL!JQc6a0L#=J"LC;2k_6M`spS+UBNUloS3;J9a>Y\l-aGmL]Ah:8Wlb$3Do)Hi=492
c_']ADAP%ZGe^<X'r_=>jcm5l2ts%GN5WM(S]AA5I<EM3R9LhgYi&Z+?IQrS6DVT*U2'iDX0'd
j22u0ZcL.J09Cb+71<)l)TRLk=hRdD%:oFp_n74E45:f2$Xt;dcN]A/Ef(?1rk2DBV[TcL54d
[[`NeP\36@EY[@YU+s#8)^C5Y:T[_9dBHS_l]A"><BHE"MYKFMRrgPgI>!K"D,u?j.Pd*rZ)S
7iE&-1')"X?@>GD"t6tB]A92-R8c!=:iSmT-5iI[$&_SM%'A<4:QD]A$D!iH-76t-!P+7\gq]A2
a9D.?fVBG[:`^B'Y(2EG1I`/J]Alpc$/>TlmZ:siEqa_E$FV+U6V:u9GO#g(JmAF=08<^3d[P
[M$3QAAmT4,K(F7%.?[pP-.gCJaE;g[_0C%;g7p2oS/'82dICJQ>EpN-@<(K0)/2.':mTF2#
&TG2"BKD*%tS2"9pm9;`S"6cd$ESe6f32UJ;KG.i*X;*krTQApRT_(%dW.^Xu5CUt%rS5bf/
%UqpH?=C=mt1\#qQ8Ata'2?,XilPU1tTh,!(k$jY4<=F?&<Na"[e\eLR)"0QH:F0[RH[Y`0:
=fBpEBd#;Uu%H=[Z4i\5$oehYa_J$C+.;b*Tlpq.4Q9<\G59?/oN&jPdM\d(Uod?]A_1*dUIS
$Kck0Qna6:Z-%)q3LQ[`%5u`3A'W6u)+ttc`$Lc?qY1\4-k.+5r4[u"bTgWA+hcOZHgaCeWV
9skgL=<Nl^YC,7'&\6FcZuR[\)A9XJEC1D/,ZcFM.$b09T1.MdEbhqS>5iPoUA^R7.PA>uGF
a%e&_oZHlRg.RekjOLjIok)_%D;c+#bRY?U^8sj*GaIr,Y3t[beQj+amEk0gC[9_\(S*B=uO
h!;=j;j!U@tr?X^/:&1&[(0Z5GP2H!-8>:A22`#m,m0M<5OJUQ)!;?FC9[X&E-;.OgI04(JG
B(j^<RR;tmY]ACGtLnn5N3eibo5gc_#tW;Fo_p;u%b,C#-l`?'X4,JrJu0FO!q\G(>/8Lb[Fc
#/.3rMn6'`e(O_r+(eAT`o1i1-8LuWj'P=263:e?cjN6>J.*>gdq+r*i;="Z=;'LPgkM2e1=
XSmoDAd)G6h`EEClbS@;L,TefBd8OIa\Zk`>jYi_JVd<:b/WM[=$h6Lm-TOTBuEdoA`6bG\)
)'o=Y^gLB,4#5[+h22.hOY<0gP-7nNU%)Un,#fDa(_5.h^&P?c5:tYNBfJ1$0VfJ65o92/^>
7t'hME(HYk.Js&aMW_*JO.ds`9eD^0ZdL:1k&gp;n3`kK?:ijRJ)ebTK[_$PDm$bB^!O.dqL
&a=r4&hb+nk'/YnV)euR8ZJXKV;3T8>$A>e,5)?U4Q2BbF[[BI+(o$BHBAOhjoTB#I?$guXj
]AgsM;fbJp+TRW!k/2ac'YM/9Id12ELO&2AcUR#L1<7<BA>HZl'h_a4l<*63MRBk^+a'#11GK
sDo89:5@:.k*EX@RA@TicO-<'iAe&'AK$=nH*FQ`%\eo>ODIhSYh@DK4$u4NX/1_+epRc`jl
,R>8!G<9LX:4X[G,hjU!n-1G0uqfZh;lg"hKr"-6+/;RYHrX6!Ca-F+nV$GPZq7=()#M7%R0
j'N[K-V(-0Lr/3(9h+^QXD.^k5^EH&G2O:BFHg7q?f]AbRo[QU)<5mWF^_EN"30Xma45-"=H6
5VSfBiSe>l@f[QH^@7Y(FDV:WG?I.G,Gkbau8hXDsJb;te"R<+`#75/jI_a6BCa^DS[XaP!d
>cqD[d_lJ@-u^%]A6_4XLVAXd'I&D*s8OC=qKMktFp0cVSpn7>$6L!Ko^$q%0".b2FA6!%;of
P59c*>b=MnW6h]AW,>hXNT"egdQM!ip44"QL:&&EtpoMfaud+]AjX=Wl[7!jM[+o5]A1-(h*T9J
EZ"J<37t;N,Fm9cM>dmDtUnKg;3+adrGgLK3/=K-[KkTJ\3;k^&RTs*q^/XZ'"K+jl+!'H8Z
@,f[EI1)E)_I6N7nJ5*9oNe8&O2)LTZd)LnmWAE\O\^nAT9Ptcj25Ve3ZR]AmD$5eTTI@6MJ'
g4g=FRXP]AI'nXeXCOd[nZ>gu5kMD!*^eb#9t#WNRFVYB'Nfa;UgZS!_TO="-eKl5OLFMQ=T)
(233B=ehN$drrDG]A3X5smH#j:NXjdgRVe2)/n=\*o3`j!X_X_n@0lcu+nhW`D1mZYQnsQd/M
/=RJM,LRP\hK"8SD5EJ]ARTD)M^s,*>#\IR]A5,MUVZtIG<o^#o>l3K$I0.Z81cIa<7"[t:pkT
08=2'h`<Xe=`+s57HQ#TW)e!UVbkU??r-)TrQaCOs9`Fo3*t_AhG.$hWc:W,("OLMXk@B^n(
s?KPN<AAJ``#G%-=9UQq>GR'U&jK1E+`IQ"-HpR44$M)VYdU*]AoWBofMcdgm^@;`c#CO&L;6
2*9dRH2EWHb3SKYD9*KE(Eh:5><ZS'k\+\\s.ojK)n#m\k*3U'U@7__l0G=BWm<-j+2en$_b
d_^lam2dmI,586D*qDYQDWra_?^B[\T:#^5"uqZ$b:F;<$HS4Y%jAtDXUL!H'Do"dP<RWqDa
`tC9su(S>!;N3P#"r`=*l_9@D/Gp*IL07Wp'm,Tpaui4qIVCnicuMBoIGObq`MuKO^C'@"g?
]A`+lYUSIA&[`V?1L/*3ss%JZA#T[CrUA`&lmFlF"O;Z@?&Zq,G0\oSM7q,r]A85D)V2.A1OYF
nuhNl+t)?&r1Wa_)8=kMJ!j9fU\pB3FQJ<F,CLUi3,2e9ddn>U:J+SSKhYr4QVbt3P^)VM(o
'Fg$W1TbsUB8k>R@3AMT!),J[QG-&^/VO6*DU3P2^(E]Are?#o?lrL(>lTG,Ob5*B@._2/"mu
5k-U$V+GLI`[P,`+YO;F\nT+N-2Pqe>%Ph=nX&;jk;oG_Zdp;X3ima".)?@+>8b[NMsrlTen
@auA7%LdT-Tg3J;6??SK,t*9R;1W>9?2.(E`Q5)T^@&P8gK;oAMZHgn>9DjiT:r)sKmPb[c/
E@"trspmDaL6>I=hruWQFG9+(9+8$j,V>&\@rEU^16lgi.B<mT/du%r:q5KAY4e<ck*MIR,C
&0`1<i[tFh6C0@oQm5r:4'urBh.X433AUMKn0c#[f=_AespBne;`+L<REf<,+j5'GA_aPcG&
YB<BBIC'n)^Ud_;88d(a'":5LS$bich,pZi>'Mf,"!b)F</Rs4R%Z+O`ckR]AW*2jH]AociKls
=VWKCd6l5b%MYX3nr579[)Tks#&\f0)2D*O4Jp/H>rMk`&*OUsG2^Blc,OK+ff)*;KTpR#h=
lD)Y>ER@QgZbNTcD=FHGX`@N=VTfh]Asb,6DpXKnFX,!HkMAh1>pm%G!!?*m=6r_9C]A^:II,h
$O23mi_;!5#hASBB=pC";cW&9N"6c(4h\h2(k!:AC8^YJD_=R%R8#KU>c,[<;>L^"X*0.`U[
Zjr4raoaN4V1CY,]AjBrDM4FadtQ3\/*l[-[*=Ga"g7jG!"f?p)O:Q/X%R1qh`gNS7e]Au\eP)
P?5QjEp8!I'1<F45HAWGC<EdWgN<.G1'gq,teG=V'L5>&j%o"$h9h4E*-M)_5t2F5[GbOS9I
)+S?u?02(eanA=eKGT7-.2:nNd-2Sl%#tu=-:=,P6kT\`E`W-p>edn[N7/gc%$"8G.9-./=u
EM]A.N#`%W'OXr#J:.r6)I`ZVos`0W@rc-6ln'P]AQ[ts0.t\%+:u%R4%#FN)\=D`3qqg]A$QVY
jmkK]ALFXVu6=[H<Qh$lW,gU]Af6:>u@"4BDf^D\%XG(iQ#5hL4<_B$IHj@?ED65A8K-gm6P5;
qq?C\dYBWrliLCq"7hT7a)LZ.!V_i?l\ch$4@&gp3]Ac]A*8m\-UGf[_NbMK;-Mf0[?J?)0T0:
Rb/seD@9l&Gelmg8<4*co('mhWH]A[=cl/3;,UD(g]AiUi8o%1<f9MYau?c"RK1gb[eJDF>^l$
JVPmESFC[pY8,7P7^6c]A^;eI?1?q/-UJTMX*INe)17nYg^i&>BV$'&aks#OYG#S88+N??>Za
sfjLmg9H4&("Z0n(K$I^RNTK8^&:^4rLbco)S7h^WR^2ZcqDP"1;8&'%E'nMY*kcG'Ba)u]A6
>"jq8>:%"OuQMdrQN)=skC"4-D<\/f7q@$oqq"W/[I#?tOq?13(f/=u\e>X2:B3c#1)Oc3$%
$'u>*G1!iO:b%W3DP.<aHimte1Uq<GB[^1g?aI#(F'ldgHb81XNf?kS+ni\dTqb?%N[9Y@hO
5?eB[mr-[aMkQVcCIYYdHcG'2&1O<OGk:2^P&_aj+]A5QI7NPqJiZJG:@IfMgtbbeOm4S3"nK
Gr4SfW5H:q&`9X\=\.@2HC+=VY"(jOMf4Q\NVs+d-BONE#$4Pp>J@0+R@f#"3l(BV#akgk7O
X'VOJ[TpFW+U@Q'bfB>@.rOTo<ae=rMG;kJF`*M=qPW_UgrJ)[,q\e.2n76p2sdbnu`6,QA6
q#flMLpijV_+JDI28*%sj\7`&=n^hh'[s]AW7`fVrFgV.^XcAVfNl\l5^Q19-sQHfK-@:?#Lp
mI*4.MQV$Z5E4W1u0080;RO2q<nWdA`1oGW`U\Cja0WOQSO,7Cl78oLXAWlZX)e89]AKL$1ks
g[_ce>l";^>5dp&gf[.a'*G@*??YdNV?TptFeMTu6c=e4^fb5Ar<QZ1\d*Y&%8QTO*E7K*0h
3QHL,\N8ot]A:95AFtX\o"=cFjQ^4_"r;>O$0Cj/+<rbOeDdGtZNW=lr-1TZIH?o#MEJ?-Tfg
Ge-]A!_Fm^e]AGn[?^0CIYdG?*HQ>O-U(9kkkY+&i!rrT:D!583uC-.3PXs]AV]AsgRjS-Rj46'C
),k)R&BFT_W5VD"KRG?^T]AmDB'"I/_s3<CW.U1j&nT]AJ14kE#)*OuqB8$sPL`$t9`"+.h=En
+^Dhn@2=]A:#UC=nXNgi$nk^]AW:[TnGP;.fo;bD?_k8.Rlci.WL\4"WkJFos@j%A,/"YU%]AKi
_u<`0=n,u6=D:&LJnOAf,V,IF,4]ADZ9Z:DY0EP@R<5I#iZ^HVl@jXlg"@!BP5m*\XOH9M6Ag
Q%<W`Zck'm1]A:<9=Pri!TA"A=F0<q5f,%A'UGmH!oLg1Zp5.6W7`/5P9K2%k(9UPMiC97>=E
BAg]Ar@6$RJ7f5?TW.JRcV=4"Gt"g1;]AG?8P".8oj(stJk@]A#lVi?Lh%r``2HnKci8Ss@:3kh
\2a_ilJ7%a=EZ\\/RB,mPd`Z@?0mt*hAqWe`Ht-^aRf=a1p>[4L6mP]Aq+5R?A)1'@;KFTqZ5
1us"acDFj[VK@"$N`9>AqaQAEA,Kal_.VB2jg%ZW[jToLZFPB&qV<t`bbjm(Epc=b3_Zk+(f
UQ'VBgQn\^9Aq!b$(,H7u4;]A,Lqf*NV%Cb"J7bq<c3/e:FVJZbk/(/1:p;Nhb=5A+914J_Vk
q8EqS2,>+'s3SK`/eg@ks8C"HWu3'#.Fl^Y>91@u8)]Aip[("[_UslrS)f0R^XZ.+M?T$ui4+
2.ci=u-bUp7b*7jX(s\-Co`/2,0?@mXarc8c^i12mY)?:)::6,')")MS+I[`k,1TJ>hZY1U+
9Z4l!e->@f*f_Sf8C=UqU2SmC1,]AA+\VeI&1*AU>]AA)#=J$VgTN-g!Vm23.7`W*=eI0/rkPO
_HpEBg^cSY_C9Vg0?R>1U\sO=b+G?I[7IJ&p6\[Tc=:cmVi!mTLu6We9CdIGYF/7huS:Gng_
q$n;)dQb)ko*qQFqP)Y"A7EtV+ZYt!(O\&d:9-ul[3]AEd'ap_U.bTJ\'LD5$B8=pUWJpAI#%
:6bR4Z9H5>g`8eT`e/ffUG=DGL`X$qO&VN/bjsg)2[@F>o@`E;,(3`)#)?#bi]A7)B5r!q;n!
R1YBt^-jJm1dj93mQHMHUo$g-nr\^Hb7[hP-T?GZ'a9"@]AHJ8(:Ig2aZZ+W3sQT&EZ$%n?tp
-^?h6sat4:Db+NI5'SJ$!"@0`s=-7OrZSBmH`t=esO30;'VHqJcYUCM45dZANfb["">Rh2Nj
"u:<PTYW#4B,>_D[0nB[aVL]A$U[S\%f3ZpW1-1X?Raj`(HnT2&HR^-]Ap48IbjBYRbY_q-OAs
R4@hp<!P+)["6ZUeP%jTiLl,jZkK8=;X@5`Fu*W5N0(\tn3M3)ZVdnEI"`@]A85WJF,!!64$G
TH+tP&r(R2J=HUVo$H9(Rbn:"Kq(_2PcMarYMGJ3#sb3ghEirYh=cRR"h"mIOK\DUdgKD=$o
DQQEQQIGni>'BG]A>+@FHBHc'0L]AtL.5"7/^Iu,GpO=BkiQB9IY7o";g[_P<O(Wn"=bPNdbVb
3?rXJP!^[mJa_8T1V391S0HZoX1\@l?O[QZJEJlldFdD-bg`&L8ra/XCXL*V$(&-QO!tC(\$
"7%eV1g9ujU6Rcea"p7(ge_K9tE&Zl7puK[==A:BC2`3YFH&uJ,"K~
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
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1752600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[7353300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[全国经销商分布]]></O>
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
<FRFont name="微软雅黑" style="0" size="96" foreground="-16069640"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9>'>doVj5[3JE_7;(biMLju"3pg(P58pe`OJLH.ZlQR7:1Z.HZtR3X`#)DS(db<+S^,VNec
>pVdC%,A1e%p",(qMq)Cpn?O^Ntj@LNj3&0W<]AKTQ/PZ+H:iboSogh)UDmiApDAhtQ"%hY[-
(cb8f;eOM,'-b^SnE`cGr`9#2!>9+J;%LEMin]A$7Pk\kq1S:CtTHiJ`[mk+NK[a<UQZu+Aql
m'bPQH]A=mkBuAq=GGTorR0ib`e=0fp;IEC/N>Yk\B2ALQ"7k8F-X?ahMs2TrJXf1n*N*"=!Z
-`*"%TPi%c>rg"3KQAU5/39.2;YVcL3iGIIus8`pjH`"1U^_k(]Al+p#O?Kg-Uf5&=srEkJUD
oFF/M*[Rf[:Q,'VH'k*NoUG?$LOu%I2SM"BI;u\E&CE@8V)'(*1pVS"Gb;sf\J5#Bk/(15D6
0q-H7NG0#*3OTg/d]A]AhI"8Cnk"3.H2r`5N`53)G:g]A?\+qU[d)S56Lm7ju+'-.Ip%9:Ss/+o
Gjo`/09u"&;!qGm[)Q'!G4oFu'=k:3DHnP9*k"?c!IFY%Db,QEBkYKGL6gQt#="2$OHF[Vmq
r/a_3Y$/8rXuasO[BJSVKd+?mhk8GSNL/IYB:sJX5\=qo_!.#:g^QdI=jV:J((JKn?0Zc$V\
!g-=XV+B?i^U?n2/&d?:<=,o#Z'0"LWcMh8\U:d*4()[*FK.cT/aOZ*)GeSsHK'`k3dLYlJ5
*Nr'T^KQ^?>gXs/J,MH?G74G*Wia9K0kn.S4oM6hkaL4@Z]ANe")T.J->M5'EE`2_HcCdV#:S
[eU9tA8JEFPSu\G@mCZ%p<"=N[!0]A#f),;_%TVBl*5276MD:*Ja';@NG$In@]A\cF89"_B)W6
tC<CX?Q^NV=1Dd//`V/SoG_apY079$/DE`*2e/hqFU:L1l5"9CVaDH'*@=HpT"4rsu;XfA>j
51Tjf)GQaH?_8G.'8Ip3,5J.KfUf&s88ore+QiT)O*Su!O%&3-h7XsFPN(28');M9G-Ve__D
`@Z1lfc:HZ0Se4%TRaZbI4`qhngD]AXHU5%3_(s50`5p(De4J28mT)CeJ+0dr:Q"s_/\e[Xh7
/BUi?_`KfLGeCY"&kD0+I4C'+gnkK:<t03`S=XJ^B"PfPU'PEYepn/hl^'(pNd;f:DN6CSs!
n-<'h@tpqo*:*=uqFcWPbc!;<I=Uju<G?S\B?2-7sH=B-Y-Q5uJM9b0`#s83U^Xs.G.XAm``
<5d#Xs-s5KYNO,KAG]A(i]Ad`@[u_SU1Kfle?4=G`Pp@F\Kq$-kbN`+e$28Va]Ab.D.",,f)[sd
jaD;pU*r(C[TA#gPT&QinUk@od]Ak\?dJSl/pAUI'$^$4d<VJ)aD-C^oUpG#$W@gt$/kNFI&T
!<X#W``_u2KA7<Gu2G4in,#YFEqY5>/Gqu/"<Gd!a]A9=PmH5/KtZ5FKeDpeL:R;8dlDK^@5o
2Rc;;]AFM30Vtan#cQCB5(!=GH03-k@b1plpkiF;GX^iMT0I14k]A4/8l1E,o5#S3C/g5kj%8X
,FPc"ZpZMr"-RHmm)e\Qd"9+bmciWI0L@NVFQ&W*Ec7Nh^QR7<c4/iH&>k.GtqPES$Z5V=$e
;?A0!"")idL+j!r(.T1HSOt9d(TP#%'g";0#_5B.MM#)OeDeW[lX]A-P.(\["fY!NGOj%bmZC
>5-DCZTj?7-k7"pemp=Ygr_+0?T'lWIA2H+AU$OOk"io7b-9heI]AS*PJ/!5:l'*eGh6.sO$b
0S/`gu[<`*Kq^*?(qiS:h.BWoI$`(`aa'/2TtP5fMoE=@F8d>]A-R\LpG*6.XmqFSa3lop&)V
7TdBf_C++gI`gt,EeSEioHT+)ou)?;^G1=b;AZ=Y7:&A//`=keQB#_=G("4/@sQiEQL14jei
pL!e%g([2QVbj.m6*P%GBVq:;0d:NelpN2a=r=)=oTNIO:O8ThNGZB`W?Pe2-WmCo<AaS&]AZ
5%Ymi0S8Z%:Y"uA]Arkj)CiNH@`R[0VdCfHE^.,l[e#\I.0UUCkH_M_&@c?G-N2IPFgRh-BU@
2t$A`adn=$0rpJ^(H.EqZc).eMQWW)(qZPiQre]A[.#fk>"jX38(+q-j?2Di?O>LM8'dFEf(`
Sk?;HO#9PrE*9US:6SY6tP!l<B&Gloo/!3g@8O*cta^-dK=,t2kZ!a&B%qApcd>gmn0Rj2CH
EI`O_im[T2BJUQ09q`tN4i2sO"7uQTZN5/<=KU;k(9[c^2u8fTc(jbWg,.GYiru\V;&fG[!;
+AJE\rWna$_$h0KZ2[8^9<V!>-7ahN"OJBV8rB'OHEG"?L>2*m5K!5,s+dIK?RY7N`/kf47P
SqSt]AXT:ANr0@aJJ;laAfO-`%9a9P;;iSos!``0GG'e&_QPj;nV)aec&g"=]AuKk0(R\l^pBW
JXcRn$2),eC2q\[:),S[k.j[KPVfd<`!9++>[*>GtSFrA^>rE.k[3*b(,!YGBHI0\I`+hoSL
CbPKi0qE\tbQpi!=p2U2&:o-61P[a,El8HQd:bO5OqT;;#7]A$2:DkF4mb(irekMGRYiZ:0n1
OdU#J;;LQ>Wueh50U7YkaaSSI0"F?Neq?+SdbWL8U;-goiK=DW64cHHFD3_Q1O,g-K]AFJ#]A&
p8N9K$8CbC-U3Uk/LIRLN98OXLqh(@4Lel]A2.65m>1ZPDq?7Sj>>Q(NrpOZS6VDrMKo!NNKA
YKSN(rH**&\B1LZu3`Wkce?\MDU,:4V@]AKINJ#<@)([(geQ$fQ/r\qg58hX(5Y&jmU[PiSUe
mGPQVbe^I=SX5WME1kC<(.LiD"B)Ks+."ZaXT!0R>KHHOW5'm,41%\67TGN,07jcHiME,">g
k35GN\Q>^VW?MmF#r)cdpg(@8KhED?36"<#b,DOJVD>EpoS?E_7EX%YkN%;J.6O(M:8#h9hS
`DX['d@fnF,JH5hODYuD19,*UL^Z-*$C+@Kc=W0r\atJ]A@LE@"\WJmYo.3;@rM2fMD4Q5Y$D
$cCh_mIck#5>5@%.!:'36F7HL.ck$ddT4_>;^$'\97UG+mn`.g7r:Tj82@j!\9gn,h?*J]A@3
l^o3iY4<$'sY,.Ok1M(G_!l8cU<!`q0@dJ"hm`<dk[YF>h/c:od:'KqhKDTu".AA@&A?>o/#
7!XB<?4GAliErcJ,4&m@U]A.+Ugd0\U7cksS)Nt^&o1-UWDh@4Z._poTW!dm&uE-G-IRH'>Sr
IA-_9EKo*.J7]A*bBj_1C;W6gT!nUH1_qh+-F;GDrEC`j]A=O_L`g'4tF;noAC`p7dM4tm1k%G
7X>rM6NAISR=1@e?*1i.*"YrZ@#>V*o9!.Oe*(emFqOG5G]Ad>cSN5PZeLt*iD5RdT8dgU;.=
4A\Xn,gF8;)-@0qIqTk]ApDg%Ff?p9K$`pVIM)',k,2Qg-H<,XneNoYlS?ae1bURlH!X,;elM
i/D0umbEZH-?A=kj/.'NM5A^m_c7sofP`-BTN4mE,VcUYk6G#FH`4=KG0bJNA?9gcV1_?4=8
/9lgd[/!+;BOAIMJV$31l@^pGFWXab/Y-CdWl;a$mj=%_W>h^Gt0InYc1irUlNPO.\QAN\Dm
5r3VV[,)>3Ts:oLF*PR"?YRrp@Zl\9%ml?>^o;d%g:AZVEnO-Smj(^WTj1olH=ZpK86Jnja0
D+u)FU9O5(MaO.tD%3Q>=;13IXAVXJ)1lqQ!N<hl7pNqS52i)+8@R!`M^>0HeX5`'2lUm8LK
sCm"uh*f&!cHe?p!'FE!&M5!t?3N$C!CN:\bfQo/gekoU=7Z>!q3d";[#_l#-.hTuOUu^#]A7
,7?fS)ZsU>H_+2%G-LL?"S!&OG@i;p38<o:I_N%(:HL)QSmg-Be,U=IU(O1=_egA)XBmdd0m
)\\Q)'/mkD/@2G5\J3oCt5lr:aOT1Z>r8*Ng\<YqGC%Pk2;Uopb)eH=7R'lo404N]AC-GWG[4
4WVO!<?*^*j;Br/sSYh7$9^AX4t^D>BcW$gCZT7\,XIHS2Te#3FKl53r0e-6_%o=>:i<7"WV
'_SouN@m-k`]AI:?J);Re8$AhHVt<T4C;kTAk.[K%F1U]AV\509oZ5N;/:i.EBBfX]AKm=&a>B@
#YY;L8r'h)FOq=OtH-II+jMW`bG=e9ZsJD>5p!5M\_2O]Admp%sJ`B>?!Y'r`R.C%Er=.-JRK
&_h[pA&h,F1#hXZi%2,_JG3,)99!BpFcFcW$(frF+LSQ`3KSu[gj<DbVEYh+lA#5K0$.e[AA
53,8,kh;&c->\G<gk4Y8&k\$DH+>DMGm6]AN;smlD!GIFe2[i47CdsiU7i.BN%WFD:b@)JCn7
sgFY%=Xa)%d+T/1d36*tooeWe7&H/)4JR1Sr(.@Ig!"GR);6Q5,()>4WI\&gW*?48)i;MRo@
Q1B0m1(DZgcheKK2-qCGitAcU#9Wl/$`)@"X%1SrlibXtlkq9YXJC[-+-KVMIi5=i$M9ldR7
!/X$]AYNZ?<,(_AjV!:d%=C1UqXabid$[_S4*g8aI"u\G]AY6kONp\b),GbmlUHfQ`U7LO(mXe
8_N+C&#=el9#`TmL-3Q4ek'8,)VXNB1"*a_1k26\j^F)E>pMB5o>#Rg_g))J-&e2`%.5pd*C
C%HVb-qoGXPfq^Gj(5e53]A_BiOfYcK"NB73=H[rAqCMgEN2`p2F?M?Q)ds`]A`*EWom]AR6!D@
UcM0t.7Z.S\I[Ar$/>9.pA$WF'l.nOt\WIc%<E@A50ST,uE@?rqo]AL0Fk%?RDEDp,3/a915]A
mdJ"/cB_.Ymae"h>V]AnqhPYs,6)cCc*4(^uN%(]A<-HY0YZ9b;b=&jTELa;0=Eo975cZ#8f4)
sdK^K1XEP*U*NDYtd,r4G\opN:2%^:R1bc[ZM/>0H)T/!aMi5i$IKgBu7s'1`c]AV)tC;>0U>
n&ABZY@Jh8+dDl[$^#37<4R^dYU/Nl#'%P!o(F$hpMe_^i6ikp>Em1;dio&Z2!-!l=i:i7Oi
-fA7_rj%Y^Kd=tf!aKOHYh;s.W/i"Ip*KOS7V-Y&&FT?A4c&Kr'kWlGmr+J22AcVG#i#V3R[
;,S9,I*jZ?505Ir>Z9k!d:2'LH+#qMI9q%jZn.YW'@.^$XdaRUU!DA*"N"'\=i\iK7AN!X^%
""AY8@@dO$>!EaZ!I5#bq;S@e?@:dhchLA.oSklGRF5(H\`&h&]A>oEurgh#L95\2$q?l/n,?
VHt1>14-1.:O,;-cK15D4maKfuBU3<Y2/j\Y=B(8Bbp@;flb$c?4[5]A6q3rtp?YO'7aF'MoW
,o@@>kf=le/+mFlmk"V:crM>i8P:u$p(B&8LDVr-eKjj2[jmTC#n(bP@GQ0#~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="405" y="69" width="163" height="35"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
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
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="1.0" state="1" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1485900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[6629400,2743200,2743200,2362200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=now()]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="1">
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
<FRFont name="微软雅黑" style="0" size="112" foreground="-14248771"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m99rpP@qJ(Ff\lNV4L[9<lq]AiSV_B$A\F=$ZE:]AS':!HO/!iT&Xi>]AeP]A0:DMla@qB!$V(j:
YP>:Wg6e'dQCBdM4K;1g'm;)G#_k+^u',2tA>Grq"1dopd9,p[?&:3G3!a`#4;Wp\0*.kBYt
Rj6QHkUeI=ACN<NZf[TYE]A7LV12?=-mHTpi9(U2d:bP:biYcsB_hcoWr#C.&MmWWhl]A^sP@T
#=e'hf&K>p=g7qq0_,FkA%j`^Y_.PnbqU8a,a#eqX:bN57l\dGD/6FAbE9E:>'qt:6RI+m[k
[AidZ!+.o4=sMSg7?Yck7nPSWr%;:@9tK"KAKLQ4n_;bVoJU=GS)IF\qf?ZD-(<f:`kgEh^k
YoZ37o)0o">PBRlhs%deW)Q:CYZ4qt,LoB0m>`cZ>2N^M=B9kfl<qCVX.C:USMl8RITDh%6P
%agCGZCPUYl'Q/%rB8HU906DC3&>?JBI>h;\N;M`6dm<NK4W[ZZc`26O4fpO^EfRbOU2_fb)
u=q&?agd,(Yb(^rJaM$=.3M\&S<E7jcB$uWOf1q_;I>R$a=AMsoHR14%<?!C8RD./*;g9gck
"'9GIHFC2b;AXhJXhMk8)kC5//K\.]A^p9YE9nt$l5Jhg@C@D?AoqH+E'IYTnCt'*V/1d*YVO
gmmq5DY/8J9k86gUWm\=o/U:F<!7pfI<gf8X"/KJ0ZGIQitE<T3QkL"0h2T_@VPKJPY?UK1:
&p0h\CnP)*=4?ln<S1RIp`etWHV,_,eq9*9<S0"7#,8["q0X"'4AjkFBIfK$c`)IVoGm",s1
WR@gSsEoGCj.N.E_QQa&"feXA>pk_I>Wq;T^[&@Cu#1C`t1D>.HOhR4fU6GKltRZ]ACiTrY]A\
(_ZA4KXj6hWep0g6Il/84?/&O'n%>$T=^C2=o-&=^NT=*5;P>)TVqcW0d20(U/B)CDICs("m
X[4"fmRZhbFHJLM%s\b9\/UgBlpEg'f2>\pc6+Z?j=m.s(MFJ]A_tB&Un;rc^?^kC^4+b,.hl
%Bs&LO*OcF9f?iJn#ll<J$/YsFUO`SD+LIqDl*@E"RNol:^_I$H!=n,7d:0shp=H?;YD3NTd
UY37YF3Cp24,LDef.-g![E#Qom/9)$UI>.[p=aP4]AHa&hf'GQH`9l?nUbY+P@Sp7&rS1+_]Ar
NUj9E+?]A=LWD7`2:K7iqPQCJu7[/np-pYg655TRIBXLO]AbKGT^iqUSaTXT5*#L!6]AY<&(92T
)Ie5?'FW'*TP&>ScYO>$R=msf.lm5G^Ke&$rfKh,RGt+a^/@@&I)`>T5UEQW8L4&[H3*\\!D
B:@7n,-NS$?Rn3h#<E:-Q&?F:TjC.1k!LGo;R&eH"DB[W*KQ__tbnp$4k8RDC0+.,.7S'T?O
1D%%NA[Mrp#-W]A\^p%^t9DA+,PS"H=QgX<oYH&PG^-C/ff]Arr>(Q.J%+\6&k5XQYASLIJ@5T
[31[Q1mN.)n$4!VSUA)BRaf8n7Y/36)JU1p;gl(V<3PCN2&0ijkDLY7'bn,q)u<Cr+7<QmX8
4t_^>J&#P:1lo3.G&6rJlKd(8K-%he;f3_\jNrc<6;rZV@EQKHh:d4OnmlrA"K'OMm@M<t76
Y&S`)pYEtg=-o*$5?6e(MQH9_[O^;NRroW\#Q858Q2'WoD^#^-GH(@YgF/88'MVO$mMGn0JH
$?&/el*$AUca.EB"uAgKFUqCS0HIB[&\I9((YT[52HhQ1>bNoKPa*cX_\<LrX7dVgEfrG5hm
;@UNOWQhX*Htn=;HLYKE!MbIkdfZHe%AUlUY/72*]AWVm+Jk4god!XeoDLqU<Q,ULLKTPa)j`
WA)]AU0p]A;O5tt)Ygs*2fWMJ_kPPCJK.j7FG#jtUZg_V*6bW84g_prTjQh%edJtc<3;3d9hmR
-p3)d8qb!KKOsYDn)9.^)hk>N9WjAlE9X=Pn$p-;>BEh;a@MEua;F+euqkT2i$,i[iK/=kK&
<W2!V]A,5oB-l/XQ6de78mo%':0hq>KG'UtDI/B3aYfKPKi-iZds=Z'A1:A&8?.F,Q!Fj%`u:
:J:-ok;N)nD1EGGY,Ku1"5WWn^X6;U;t7iFj$hF-(WsPkLJR&7,$XNN15=nQ\?9NW@fihe>2
(E?t(:lTN:JdKGRGi12(`&%o$(q?r+f>?60Q4.K'+Vf"/`,>no;>cj6^eio/a*JWG59a`T,_
AmO*&q]Ad]AK>sdgWjU[n$j)sbjHU#4;_"EjI>3Dir&T:mgF>oTAEQIIif7eOk(>R^qG/3cq%P
H47oUa.&7I0@\Q>l;Qg!H$_Sa"LO2S'+B,%-kqluL6Ns8Nf<2uG=>[RHhK/kV9uj6+.ZYlE\
A)n<L_K5l-9YsYk1s-kW(NhmA,0]A9eR?"/'3_EGF#A+=38]A11:5-1C->7tNcVN$Gdg$gJRH0
IE,nOKB:Z'a1d`ZUDuq15p;qfJWOJ;PKns[tM$QB3\XT3mJ'c4Z>R%#As\)%%Z87ZM7`-*\:
o*qaq*/k$"JpiVXp^0O07rG3q#G6.n<T\t`Od`k*aFOqCb`5,^,Zh:Pb.D:@3YcT:e;j;<i+
kH9:L(^DP<Ja5*EicW6PAi6_!@9>6+G7VM[W9p+:(Wc&S[+lJti<lpAD^T(ZM^RFXTh$?Sag
60pj`/+TUiHuKdET#;%0M5^-8\+Eki?(T2dt]A?#(5Ca$sM>Fq#1j7V+1@m1huHN+GV>Xn+E3
9phS_S7]A^Ss2"CeIhMk@P6>0@0Xq-pnFiJ#)@Z7(mJUks9Vbt#(OeGYdAcLXY,Oh*a7g]ATN6
aZo_rpkbUm'gcqKSH$QP%F_,f\1j&!KS28Ga[*D&&MaK%1dWj&7Kf>o.tFE=#g6tWa/+F]A)q
OXVWt?giM(stM>;.ah2Yuu!KY7)Oc27^Xj*86hH(1OG(lI"9oR2XE2P)u:eCc4&Ni(),PP(7
UT>+&!97uJRG7M=s8%HT6kO`X?&El[!*'B=Z"lYQhkB4t!HHmTGV=;#X:t/d91Hgq5o'`h64
>RjA8Od0:1NC@5R!#/$(Lr(3giru+(ubFpmVP"+RhYqR=6,G8JIm&+FI\+^c&HJ3Z@<7N]ATP
/qi?>:Q1j)9>0i&<%+:4dW+RhNikf(B2pMcYVp]A9/hB0_tNNW?H.Qci^BH3dAdE7!aG0p=nP
5Fpm.DouN5T\#!Xse0E[-PbFSB:sK=g"9a88T\_lCqu`=\D93@>&>kjGn&TJAB-']AUkl;3uH
RI9M^@-R#e8WDF,_jB!@9gIUZ%Jm.Ag>56PK0%=nr)pUsWKV;2><4U^*$G0b52.L)Vm6QCgb
5;KD?qADXPQipZU"/<XaUgGq\g^Iu&DWoc[&\-b^<N8\,X-/qJ/r'*_-Ac:_l@f3@W-8c"$M
'5I/2BX]A1B"GUaY@TUEWGNH;?hg]AO%oeVmN#U<m$uMrJ*[9TS3(!;k=Wea>Ti]Am*.bm;"O'$
mmG@d05Zl^T,q!=N0tkjPU@TCa8>ATTO>n":.5&7D9Kig)=m<]A/!Bm\.lTW9m$!L['29<V&$
*H<u:0QRH;'dBpYE>^SJekVD8`NSTAX?n<649)b,,=bbK&iq,+<To<]A':(.Gp_'0E5!gY?P_
'9OAGrHP.3a`18Q("0Sjg0[=pms<m:/[eT9pB!eH\uK\R7-n#=S52-:]Aq7us3p*2lC%`RS^e
7gR%a)mVg_FVM<H!*9_V@O^=@r;'DVh;#&kL5J.pBi$Fp%9&?7p]A<1.-5H^,%TqmFUh_R`@U
r#Z?\!FeFV$E&Ih'&c0ocH_I&E5s]A(B*,mQQ#C*N]ASHB#iK"#/SA?$=6:BN@iV7X*D9q#e;4
3RJH:"lH77SEY`lG$>+q&:d#>g+=%>pNPI9=1dI0B><:B[8jQL?8X1D0AH!lSP8KdaT+r8fW
\KVj8f&9FDu]AH%Ts?kCK&#4+W0S8T[&'3(_HNc[:obk,JUg&&'aZ]A>V;WBs8n)6AHE&\p;;A
,cNr0BsoGKrU4H;g9."WH+VRUSg^307$.o8sp0Fa+a>*@i$&>"iU17h6">?nk9/um)LSH+`j
cgAuMGn,F2LPL8FL;=4!72"CYMKC-IQeK:Q3sK(f[5*a(\I)NBIr_Ju2&;XqJK25W@Ch_'Qm
<\!@#'t6VJRfSL&abhaRaNE"<[^RInD+G@Gb6rU-f\"C7*<P#M9kJLj6HN(cJaTYi4iN+YT<
5EtcdIFqTo957s=o]A(oNA?8AaF(^gX@Kt[r1`!'W[W]A_GK%kFHFm4t1%$5)f:q<=OM]AAM\&$
5c'$*__GqEkT"*;0\FYkM%DDME0W8('U8OeQ^Zq`3h@CAh*</",9tAk6:/+5dP=qW>@2`!ag
l$ANCE7-b4.JYe&tdc*`.A<*V1_Le@.=5&^\*Bet/?1jZ#iB;1mLRB$**U;2h(jME;JSk!;k
T(`\Q&RGVi?Y+X_#([DsF`.Y"If-Yf+&LS+F^k>u!oO`MX0i9gZM7_]ATo9+]A6j+Q6VL&S@gO
kH:qLc9,bi%A21oRp%I>$TR?6BV)!G0F;eTVCWWb(Ao_=s3*=>Zn!PYP2k\$%+J^BRR[@Zd_
*%,Hs&SNL,RQdq',ga:gn:jq%HJI4q.M1#g-DJ(`W]AR6n_(71,K'M(ZrK.?K@R&Xs(DJ1#`r
;\II,-/q0P"[hj%A\fi]AYbHX/9&V*kCi-ZpmD$DJH_iL>p3#a']Aq.OD[3hV3k4bhKV_O+:d"
7p-7SdV`Rf^-%cGq>`F1BNb=SV#ME?CgGuH6c)[MWYk;PW,"7fKK<sX*5D>:PF3sU/EZ&i".
6d8Rfm&-5BP$M8`*%!?aWn]Al%RV0!cLf:%LHii81bH#VJJkUcRMpc%;k1'_<Q*c'^%]A-Q-Q]A
^iVAYk1gHNtF-;,7cd..Z,j&Z(cE:0+7ohs'Ec9!@@'bLr"Q<u,?Z8=i:"2E\4bMtVb4O9n]A
GSF&Hd!PboH&^<*Y)?CT?as#=[=e8+P6]AUk"9M,.0LH(&L@Iu)n\73M,>X:3o(f5W'8I!`#Y
rl*Hp^$?Cpl7`"qmI''&;!K1J4)d`.>Kg&IIi%C'*KLX`B>EO/c$6I_['jh,pYR39EMp8ka\
Uc.Jq6F5>o:;>b*n[.U!EVEW>,uguS)Q1!65CbTCec;N7IeS>BUrl8A9JT7N/qJ9M\eG,p.-
2!/0mPD>gP'-P-<'+Xps+n.U=+c-O_rf/N\^>!%i,(ik9Eu1/u?3"$pm4pKe:WhGpG4[fn.j
s49o?$lf_m"XZX^&3loXg`Cq-O~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
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
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="1.0" state="1" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[6629400,2743200,2743200,2362200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=now()]]></Attributes>
</O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="1">
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
<FRFont name="Arial" style="0" size="80" foreground="-14248771"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9>!@'5+ad04(dH>2!P3rSSpUgKdp*CTZ_#!?k<UF\s<a?HZ?5=:lc%d"j*;0<OsE=9Rp;MN
PtrDq3>]A6j68LO4dg(8AW5:8Ha^rL/#Gd@gEah,al1V[Ctl]Aml\V1C2Z'1_t/+.1[iA0J*2$
)kIg]AL86S.OVpg.\kN=cai=?<_qt^&/rVZZVg2l;2s7qB9\R7WdG(7BGq>Zj92>>>@%_;,M5
8idbkPR?Zr1!WGKsTZ=g4+.uVVV[@fWG*:RJ-3aM35ARid_njdsnKDmb5W_^?WE+FgA^L2uN
dq[C_]A=FTmf1E)mnNVJR_#=5p"7[NO>tI>Y,BRQO4MqB*R!bXCXt]ADO1SFbnS&UE&&5/(1o;
!gPkM_\nj$gsY4FFRLUEZ@V^gKI<=V4&m`WS(V$FnI8+I`jW@/\]A$G-1NdG,A'R@:E5k%^eo
I3W14(*=)Pb/%Bp^=o9ul_9NA/s2#_T\4_9H;9ba:&+/&a-;q!&u;XpT7)o&DDc5-8O4)+0[
HiDtkR#Hjq.)>p(#mu)BT1IAqt$FM[bi5.b<Sc2mkKpH5,9$r#e:3kHg"V?'7Tu&($L@_;'*
N;-o=q^8-<6TmeGHgS3=><a7#Fp;tmbM;W^OURa1P2RclGA4^,0<p+bI*7/TFD'&a15f;c#`
uea,Qjd&T9FZE(c%SVS[Mt^1]AJN&7(S^<'O6I\nnb$Y[IhpV=]A@Tc:N.e7a['$CJ-$jRj]APq
\40bqTTnMJ$CoMA:BkmZndO>)[R+TW![o9j'`G;JE51h%p@Sq\b`fVF;tB8bq>b$.gBdDg?/
\Y5d<6P8e>^Z+S'cu,@H5HCgQ#Mp?J_a+9KFn50`,DHXAZFaY\)>sRSGXWE)_b=h\)".E?*i
Kc+Cr6ne6^CB^J3]ACR)mVmRk`poC-ilqolELR'qP_n2+]A(bJGYL%VjL_/Wh,^DRm\:?"Tt+r
.pauAt8ii7$c,WG=m]A\DT)5Y>;1lf`Nuc:;M;Qp=Zh3B;\b(CUJ;qpN=-k(GSDmn]AX!3ciJ;
"Yjp%Q_m,d>-Ld+G=+Q\g?G\gc-9ago#oU2"dp<F$j1V\'kCY[[Ml<3)`Nm$M?fX7%`aEl=#
:42%U*=jKo/]ADS2S"5;)YsGl%C7^l;@!8k,`F/(dk.,eWA_h1m*,21kn<`Qu#1kJ#]APYi[X8
3b2/'i\9R-mjs<u9K<X*ASW#e)itUAn@G:Dt<<&:J0jQtQ)N4itNFFX!9ajaX:`kO&VB4rEd
.7O`X5:5+tk-6/6<%TIW6QAPae2e><UI,/qu[o5N2[)<T4j8.qJP\53SBpbg5"<*%6iRppc_
qV$=NYkbbq/H.hKjtn?Tb)(KS%9uQD67j6V,;n\`)g`m+qmXkWIXI94=BaM7?bE7X4/$gSBH
IC<A/)<kO;2*+-haYR:ZPRT7&k8IGbspigLUE'j)ie);_,IT21"3-e=:Y+,Bogr6)l!eVdMp
q#Rm4/^Jm-(d?KjDK6KuF4YQ=[gLV9(l@%>$ed*,*JSVC<^Ep^g<;\dpsRfIbcEPRA1FaMOs
<Zr[0o(LGtBab1ASH=5mD`^g5p5.\Lc_-n>J"\5\m=$#p4:\#UlM)Cqcl``Rd$lGog=`R`=E
D6://n#G7ZVXfuO7J)^P,F[4W*/H$?(c9lQ"&(rN3/a!>lT7A(d.#e`hJIV/.1FFUi\kfQBa
3;'R3.cnTe[qS3<djtVSg\"`8MGGQCJmO'S#[0t]AG-U68;,:eUPD)reulI"\"t]A]ArN#8dnfP
C)I8Fsop>qc2g#XIu7RQEFbA?+8W_7*6\XD'q^=-8XL";9(pX,h0hYK%-Kq\A>HT/l#$$!#*
DG4D[h_PbdNb??oZ!DcII_ZrK.Oq;S`KG\d<ip:=7%SgF8,$uB(f[iM9FE1i&t0GD@(e8^H.
%80d#J?)kfmk/NBn1'B>J,/KfB!X2]A/ZLc4UD?RFO@s_4D0"`jDA.!STj"Q7/<V;Xg"(^J\'
TZ&R?thTM=]Af?q5A@oPD.I=KMqeK,c!O1Iu1XL62Ge+lP-PIYEE1%#,4@!<GD7UKjS[:2AX%
!\qBrr!d.EcbcWX0#cpB'T?>?WP$m$8;Q=X9g'D?I(?>LbNn+oLPQdWgK9,kQuXATYGib/e.
TGTYH^D2B7gZ!*8896b%_1Ed*EAKpc]AObJZIUajC+F3urhbqO`3)#.;0lKZ7K_VB1W-cW@$Q
lV$KgcjpF8)HoJgDgZNR2(^\FHt&,.cinTP$YOgW)4_NMi$bJg1fo,05R3#qX(]A[8hM2[3dT
OqU,-5Wb$980F-Up9eQrPUe/<S$#n8dZD8:L^-ah^ch5k#toh?mVl8ne1h9_)$Y0j>A$3ekW
qALINRROP(ghFmAo/?CGPn,j`pai?+16N'8a>&Uk3]A-=6,ngd_o$,jj7!keY/._DS\Qumu&U
Q9AF3)Pj&\^t$iX-W`r2`ZXEHfH<\Ka2qb#oX-R:/#TeW`m_4!;a'nK!"(*TaRS'IU"9IcrV
hW+nP(9r`S)Jq$&4!Dc&nKJQ"L4;T$<k@!^eA`m3?d[<@Wr*["/(;iI7#m)/^U$l,H=eUt-a
cf-o/e_i<%W(re#UIHd9HUEFlPcgMlo^#Ve'%5j]A??,pEfJJ@GDRhBmY<A07o)AphA]Adb2%n
l=Zd7iK87*I\lk&9+b[h1nu_<CnmBkoK)b)6+d?)(t`@u691s1O!N@<ijjUGBCW#WMgj;j1e
lk"Z[#(3OrZ/JA#5alKebf2Q<=BLNQ<7`SJ>;isN/7Fhip'N%3Cb-e^0'+oii_mBlWGgN*'@
/Js"du=T#/mgEo6G]A.8QY5NRmeDD3,^D%m&>[5+59D.#\SI\hk8VoKlUO,dGXNqC`g=3IAqA
uo]AWt4LWq&A#)VdVuJ3AUXd4YLp:gCosj@ZO)$U>%EN7[8(aM.^7(kWp&%T`'TMEiVnBXL#m
I&AQJ.p2%/%j-cW=@^QQ,)Xq=9@&N5WX)1[-W8=^XnGSqVj+ts<XDlM:dS%bSL\q*X?)/k?s
i+""m-;<jsejkMBLp%)]Ar8I!M2G]AlJW1+OpRHW"+.l<`k5SpXH#rI',HNpE20*omVHhJ)>.Z
oJopRW]A]ASJ%kQC&E@b3c>nDCoiWu`TjjaS#&d8uK,bi8Q+OS5^K[E^.5ppPd%p=0%Cd<mnfH
4$e\p<fGZpeVrq2[BUXAl,98Q0%jC??:-/5hU#=]AIJ-OnU>&;!bkI"``Z^3mlal&5XAdU%d:
^IARE"3TlP:g.^VV1:/5Z3B3%PO5iLquVn'<q)A7Rc^S\"I!W0:.U]As80^rSmlBqFo3kAJHe
/"WBnWC=4W:Dp=TXo[_U:E(3W-?s4&Nai13dh^F"D7eeu7P.&SL_Z+(4[VDKa!Z2>+XJN<pb
9rEWDSK,'U85IJh)Ym>esuO6o+"7-(H*="7tefW&dlB-^b%OqVseM68<RhDSA`:d&>-q*<T`
8E]ADm%_0HKpl+2CH61Ue+_:GEi!J=/O2X^aN-P?/#T]AXA=[;3,m+SH]A3BdH+3\.m1B!TP/N5
3_V+f/1cOC$#uM('Hh#=!sCI/)k*UH)H^$aSd8eR97s2;3*mm89q0Ri#,HM.>n`&JM?QL*Cb
,"JRckdhrs1['U]AQZ^,$)hAJgJALq$N9f%q+N2h4O1*[YkB78piHIHh:jL2ZYSU_PB\@4VqG
f[j73c:!F8]A\?gN7=a20<D2AC'SBnNEojSRlk!8nTCq`(5J(Mlg\jbPd^&M>#78C(0RnNii]A
5a<7d9$t*G>hJ38-"'L29*d"\$$ONN:&;.;/tF)080r2,tY"@6nCG7E@N%N;iS4oTh'=]A@)_
#-aM#Ong6sf=4N48+L(!c%k+"d:c':A$CNqY?E)bY*IpJ,;BIk#:eMc!5)iF!IR``XmAA3so
>0F_N:fNpE1M.Uf\[5g`n:lTRH>'UP'od'[5lo*hWag)YFZo2qk8iX/b`IOAF`3k%U>m<):B
,(+%=]AEi\T</#p.,gl859o'3_V]AD'Lg@.tob!7K)CN@+"F6XoJ7-Cf>lk,coe`A>@I**n;uc
gg,"/U1q2uh!u"N"JSF`l:Ym7fr^u^W\1:ELigcFOp(a*EGcAe$(de*ER#fE#dFR]AdN&$W.B
!?S<7tuCTq&'C[F=F@"JrCG5q0J)rmc`Q3K2KcY_2L%,!2oNN22%oDt,`"r*1^WTTV:f?a?k
5r'I:JJ>gC[FZ]A!$Tn==t%li=[kZj#biD?dTj<Y`G0Bp4)f-@2m2M<QYjQ4?e\S]AIi/"bbF'
sYSrmA1(&9o_De=pH!=6%t7f!=VEG*<q>*(.]AApg?%j(C00%&kcjgQq@SYA"AR4'_iBu)+\L
JA56!3p\CGq[pcM13_R+IVH\Lo=+LsX,WO4:CJ:HP7*QH*O(oku-J_Y=LY_Se&<sa$Y/\E*O
;rd#ort;O,?E!`5hiTG&=!c2:DA%MY=M:<7R8lTJ9FC<_H[5r6E;cISPUhNC?NP.NGq\^!k.
LaSG+oM8TPj:i9\62'd0KZG=""@><C@EFhF#Xee.(YOD^81*Ko/FGRC=U(s.i[>?6">Zf-I$
ipUh$j=VR%2TRZK^[cE0&bBHnY#D<0"@%eskp]AAs$9k9'?R?SGj_7/gt.8!tKisI\BFF#S]A^
p1IkRZ%EaLZqGua!RM-*#=ndk+j?Ip7.k?Klc'Q76)N0(PCB`K5^%H,#!9=,OsTbraB@L2N=
4qVa?&9>,k9/*@N9J6$Jgjb^Nrk:je^aRi0#E-*5ETZH#lD"^K=07drPp?nksdo6\lf9WC;D
-+X@h9'K@;Zcg:T9*GQ=a\/Wo\B@""%h?<h33.u%;-*76`2kF@V-<-1ZW/Ua&5DTVc#sa/:3
'IhmP$=1h4YkP:r/^+K8LqR</:`)<&t,5hXrQn<#n7r*Q0+>;`@as2:eX^S_mNb+3=P\J2FX
:9bBD8!S[HmMj2[M#6A[H!TStl('NWe^NH;)3[m+D8)4'0"+7*NR=d>)W0Tc%,Z$k:a)'+^J
IEN9S@aS9Fk9F+7WAoT7Q!?merkaZXP_:X]A^S24[k#2pZDJAA(Z/%o1oIaRIPj9dS2n(sWm#
&Shr_"jjP#mh(]A#$GjA(:Be9;dKhd#]ABqfRC"6s/5BMT6$*N&saMqXusma*H?^I,`pQ[;!dq
%+T%&ST+OBqt\m,&&31a.&6k=SK$177noS^K%+[dEHD+?Y<GJ;EhPE_-P2C#s%12qWB4/X&c
WJM:Kgl6@,8Hp$N~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="776" y="8" width="215" height="51"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report3_c_c_c_c"/>
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
<WidgetName name="report3_c_c_c_c"/>
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
<Background name="ColorBackground" color="-16170125"/>
<Alpha alpha="0.35"/>
</Border>
<Background name="ColorBackground" color="-16170125"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[5600700,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[31432500,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<O t="CC">
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
<Plot class="com.fr.plugin.chart.line.VanChartLinePlot">
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="3" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
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
<Attr lineWidth="1" lineStyle="2" nullValueBreak="true"/>
</VanAttrLine>
</Attr>
<Attr class="com.fr.plugin.chart.base.VanChartAttrMarker">
<VanAttrMarker>
<Attr isCommon="true" markerType="RoundFilledMarker" radius="1.5" width="30.0" height="30.0"/>
<Background name="NullBackground"/>
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
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<newAxisAttr isShowAxisLabel="false"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
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
<Attr rotation="55" alignText="0">
<FRFont name="Verdana" style="0" size="88" foreground="-10066330"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="0" secTickLine="0" axisName="Y轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="false" isRotation="false"/>
<HtmlLabel customText="function(){ return this/10000; }" useHtml="false" isCustomWidth="false" isCustomHeight="false" width="50" height="50"/>
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
<![CDATA[ds4]]></Name>
</TableData>
<CategoryName value="货主名称"/>
<ChartSummaryColumn name="求和" function="com.fr.data.util.function.NoneFunction" customName="求和"/>
</MoreNameCDDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="true" autoTooltip="false"/>
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[mC[^,PjR?m4=D[`(R:Pl<EY7$/u]AP=8qRBuOkiS%/JYQsAJr?YEXCF@13QeI1DmEeYo@201%
o`PkKjppI:'bP^@1O]A]ARKV@q59\A4HJOt*1u9iH%2.?3"+6e<+A,-I/jBO0jh\=q)/Mc-Q<2
_D;Ibn[.rtsbQ<?;lE=Pqd;b$?hWsVOh]A=4aZC)GVVZ%1-0n)=Kd']AW%:X&R\-91Vd0[XCBH
pSYh:!dIs$[j&_4Y-jkdpr'ofXD$4:ijUZ\L9emnaX-gDYGS/"ud=*c39KXcaVHN,XkQ/!'`
3%^:89'HLBTM1*ej3#tukTr?1+.V3<OC$3g50Ja_-a^)f$!q&To'.QhH?V"3[c3m$cNm+f+s
3-1OeT8dk/;1=Z&5Oh@)fb)`Rp*]A[Kkot_R9DUj%8^pZre*n\rZ;2#K?0:Kf"9E\8E?c!i6$
]A>s-=t^NZ6FF[,0[PsdN2iKIXIG]AP5Z=pc(M'6m?cfW\,Y"q(=beETS9Gra6,G5g_sn$6sic
&.-=l=OgNX-jJ2X_f5pCSPrbCk'CjqZjk_Rtj\dg^_V9*`e[+.:+8l]A(*YA9icdoC'9.B8mP
nSR@N7lcPFb$nYh$e:h;8\74>Eg5eg=>qMhh';3_'WB$lY\NPhc?MK;s033SMgKpgVXpWPo!
/fKW'qBJ/]At&4j<FIGc=D;na6)]ATGa9s&*b":8+1oIBObl>cdsD!8+/H5j&X0?B>Xb;J^U4B
mmAP1ciQGm:@b2+;F[A\:pZaB_?,:^'`icr^Q[t6GAfMQ`!H8XMtm=ar%#@/j2\cqb+H.MOk
*Q#HQFo8!J-\llXRMac76%K6Bb-+WIZ[#c$B^)obP%m.iGOu-55"`_"$jSqGH.6jkk7Tb<@g
+el^C!52PE#9VP<XNZ[:5c-8;o(YW4CIn88;l?BOnWpr-]A\9tjU,J!"Eec_?&/d(FM^n&Hb*
u(=uW1G4+@(cI*+3\RR'[$`7'1mUNh^tTO_m^t9UTT-<4X\]A`KR_8+KPeKjTaS;V_4*Sn!h1
tW3tJCV:#L3qG1:2H%+e:jij%>Q94#`8IEK\`kjVr*GgCh/ni%&d9PX_+YVm#-=DdBX<AZ2E
fM3QVBK$Nh<Uid"XLWm:TQ?JN;)Pt+2`'9,DTq4SX?;j'g;a`LerN`gcZi^jV.?2K4IkoVLE
!@k.R\]AOLUD_2QSLM#pK=p3DnFJ)V67RsLYL"=#u5'-h@#Q>\TG3k,e12U\V9WQ(QI=cSu\/
.R*T0p0jk/tr4A%QeQ1+CD/,CRjB.0;Dih9&!h481nf_R_pTrs#DVcEm2KnEUcILlTU3$m/T
aAo[rl9'q/+otYQ1tO@nDJ8seoU#87Ls?js&*F^=+!Z"N;XE`!dJeePU3i*cJ<u[K<La)GE\
%$ZJ\;h7'$m<dc"ua)&AX%H191lO2m#sef#V":<*_SQ%`^Bh`RWSU9MEF&EokR.ai]A8S#B9N
4FoOTO6_>Y--\V'2jB:9:@ZL$bhf*F4s_>O!li`eb$7d$)ntq;9_$M;!!N=QHft=[:@;849E
;DK*U8K:[u4NaqE%[#SUS$VAM;K+$*IfUHf3tmqj[hOf1F#4FcUh^VrD6%,.@i9%hA,"%L6`
S*@clsV,,m:>V2<#h5JMiF!.PaP8_SJ>UIKr4J$l\2IsIQkR!o+=@C;GX^6:poCjma?O.$.!
\i!ZN217BoW4)6dS]AF5d(Y_kW6?"/aVk@H/'O(leie[KYeu,L'4\TUJB]Amfp-h+a-K3/^*AG
rpOQXV;gsUfBl;0o6mqF>@!`^JaoAB[2r^h@ui,tL+2K+*485M]AXMM+,bANWV95A4nj$$d1W
5?oAFLfDZi$3AL%maiqm@Xl7.S1BEAd1.Y9bT)Q^7S]AKK,t7W@@TR*9:2FSt'!>1DH"[%I#O
turE#nt-Sgh9I<q\JfJ5KE0DiJ*^RWa`\$+"YV?;%='btt<e3c\'[Pn"V_bT_)smOLH'a88e
08CW(5rdFsEM<nuebbPtS2tFZL[P8/g3Ip?mkkr]AZ!d7ko`O-/!<<1[P_sks*.!G0'T+nL([
"'Qjf$W<lQ@2OUC\L7]AMTaiQeFo#o88'D&['/>1QE\/%LjU)m2`n(/C`I36l[%.*D7OSQW9R
XNlu!b/'IZuRH9.AV@R!TZEg[6)/RG'eKIInBOBk6i/_#G8@-S%cK<81lJU(C@FW/30=D32O
U>O\o2(r'HQ+\t8#[E0\PU$\^lLd1aO#TpTLC..)T2o[H'X6)l_h4`$2jBPk\N&*e5<e8-0%
I\&klrr,r8o0e=b!'eo_QV&aUuXYdAuqmmR'\SW;G+ABH_]AAddgO\WQ13-n^W]AmBZmcuR/6#
%p%aCpoO7]AX40!O$XjHhceO76/rA0(BpSR_P7ALB>N4u*ka&OP3RWoMZU=`^s?"_2`f"\uJo
6)h_cIp1qIoWhX)+U>'p&#t(``o;UL4pe+"i>#[$<c[ECrV(n.f'8T^N7+9..`*C!Lnf\*)A
'9d;/b2GU(/.>tpSmG\,/Q.e4?jS6J`-R\QOqI7""gY$J0GH,OO7l08,*msGHIm1uR+00fo,
MTN(F*gV6W+laoMhNlFP]AE7X.[-'_%/+6ks'S_\bi!FIS`erP&Q."5g8%[VhofUE0a3,4+iT
RBpnO->[j8tUNoWmN,(<1"Dkd`p^=sku@;5d9*Qr5j%UA$cLe5uE;jqh"/*oc&NmQ%nJ.7'>
_!N2LDWciSV;3IKVrlnAYZp%(?fJNs7>o%;$*u".(Kt=>ef"+j`9@p_NeQ+p(h(!OsDto,Y)
D&F)eVol9jdI&apr)5uYW*@G3QqM0SX"88KtMp*PW8-2#7`[cM1!Yh`$(+Nn7+^T?O%%A`l<
dm+VYFXY_:NsanlSdA1^&\-=)\#^rT@=*oG&2k-:o5P_cnt*>@UA`hZU#Cn!5mk[p;oO<KaH
m9<Ik+92=T\bjVi=su`L;gq,`<B5/]A*=C"#19"P">CTW;@?Sn=hX'LkU\Ha$4M142i:4WoL[
+SdP>lt$`bnE+n)GnYp&5N30A8-(1aK-*dS^CFDRD</:AV\FPD"?$(6oKjH>G"0Y;]A0nN6Z'
I,c["o;$)/.YJ"3QL$RTZ3@(#2(\6=RoM9E'&A:]AWa`?44e87=>X`=QOkTO`#*5\=R;u"V2\
$chW7MGSi7AlDO$qm4PpWB"[PW#bmq6Wq"oKT2Ip%=_BhqXDO3FPud-t*de2a8]A?qt3L[&#*
/s=!)@KB'<dFLg.A3.F7FcVH(Y=FfsCg2EIC1S_GfH^P`mLar?MD0<"rW^]AIhs6Fs<MGoClt
4bGaMnfpd/l&VeNi._T!j2$GZd:]A$CjsZ:t36*QD&]AHX4r=#J+`.]A?=#.lR59h!k>?5WS.[*
8W7Wi]Am;]AA0X#&2!F!^.dkL5KAUO*`I0i]Aq`=T%Le_!_N!-k3`q>'l@ugmjerVUebFb'QjWe
4>KLq4J>,;f;&/m1quU<HV2Ce4)l"3BT36u?9&:,S,=h_%$.#gGo#S.qa*A&2Z-V&bRP^r`e
u^*CRFl+LMVYqQ;XBf?kfB;UX,FP22eFb;60FFYkMdu0O-A`aG"5fq-_$W6=X_;:XF!jA"D7
snHfgF(PrQ.Zp^`.cCE_du"R<Q:C2Ot40ttWdL!_i[:g1N?DE2PKS!UV*.UXr]AVY+q3>B[jE
>FkISfUOOL[tiGsa\[gn\7)JT,pqk*2GeA&;b\C=;k+\T%c>Y&e:0OM%G*%7dDm0gFfrC-;+
&0229@/4W+tZ)qUB"n+[QK'>fQ]Au!<+s]A%8)t9nY_<HAUY'Q/u2oUBBWc,_7Cu@Kp+NDP^&G
&`=6,$dYl]AqF-Q!:(_%,&8!X1\6-LaBmhcQqDG&\Q!Vrcp%0a,,nPh*/M>.6$2q\<I(NJ5=n
6YK8N6=22m(7QN"fLGu0&^&J@pJ.8KQF4!>'&4s6(.*?UIfQ&oO!uhIHAN/9Cp>@TGtmBN&c
e`*CYT:ALF7f?8V&JJ(jD=D-D7oV7DK.AQ5V06rSru^s/9[%r:9J^@Bk-fOlbCXO><)iHo8o
"b2P23AYRsqf7#T@9B'cW%u,=qbg-EDK=YV+."2/UfdG@qC2QV>'JIe8T%*0[/>K[>1*H)PQ
:M9d$GQOi@0+.O\3&+$!R=:2:]Ag>F]AtmF4M\nCLd8>%kOnGE$b:,PHg_biND?8g-7t=J';B!
4?>u9&n\F%9.!s(?ojc_Z9(qOV6rJfgM+U7k'R_U?rlkO1>)qG0b\^juZtiB,PYJ0e6Cq;io
1T2GCaD@,aFQ8-))pbu=crISnsj`?Bn<q/#?+TO=MDpLGD7E>U**]A#)-Q+5ib>Ves2_9u&#%
,52,>&aTFg7+XKSSpGZ1iO+b6]Aha6iZt-t+``\A6cW^(`'iWVB-QF!5rL>op)1+!L`G25(1U
qXp7%c-Z6i:9cRG$>"8KEpXeM[*;JK<obGd-$1At[g7Y4SnDPOAau[dTZ3GonT_qSgtTn,I>
NAX!,^S+dM,t@X84N"P;rtcS(Y/@Y/6p]A$ht\L-YX!VBtt<7oGu@b+iA9JP5k#J5du7)[l6O
,)VK$Vb:?@JJG19-2U[IP_rB#'ot]AQ[mVdRS\oBLZF5`V$KD"sCMLLM>E&1:0"cfKtH_`\#h
%ehoM^VIH@'%4Ce(!+;jnLtIs.YT6PW8i:OV967%$oLE\?@@30U3kECh*Fr1f^(!Un&ks0#V
+b1D#?\!m3?\m"tRu3_ZHnk"H9G[=OL!2gu%unDJq?WqcRlbHHf5N6D>=&lkJI[=tYMr<?p^
grXgnbG28`]AA.AucRWY-_5iF]AX,h!_)<_^_',uh&.e1]A1DM.@CCYB+S9YEhNc4JM#:LdNIf,
cc^U3k3Rn[[LGSNN*RE?b9JN2[RSg*-k!OBtaG39-EjjK-PCD9C)t9j()D^4H>gNam8#:ZWF
^.PGjfP%t:Rkl#s&YZGL3$puN9#G/c9K@"U;@nhl1(E]A+)kro5h8W8#f7VOs:So6f[2[h0R*
GF6lC#&#7Mf;h<e[]Apo^b7I3]A[:9#J_Y4Z3]A)9"g;-boZ8\>2.MQ#+`k1#VkT$HrGWnV`h%e
N5"Ep^d_pF.Af'kYN=NL+P@PnU5Z41bZ<=[;aLISDklJWli#5pNNH!Ceb7`qbo:*Q"Dg=Cbk
AEMm#UUc!9,\Q:<#?UM7l.A:;fYuM((8YZ)4VaQkies*&k:D>ibh7e`c"1KnK/EBXXG.RlGQ
_ufa)%#43]AO%jmb,e8ah>a>4!L"RLVB\Fh2a)\B`N-=EKD43Pm6s@E0Bf[,*&<"'jO,h-Q-q
R8D$UZ=d!M&O->;;ko;fZgeu,cRbXX+CdUA'EPS(eRBV3d7r^R;4P^DZ]AogMcf[V%gE+5W<R
V<jO/HCDfh"r]AfNQGlm0OX[L3I6e-SULlJ:sM,/J0maSSG)gkpEWr(G%T,+-+GL'"N'h#B>A
.o1e/fMN#q6S\5/a5-&/mknk0#mmgb9.f;TbLK"%FcieDl=TG!k0)hXSk"N!>?.3;?g[/0=C
d*Q@arD+\%:m5o4DP+$!DH\\tGXsqk.CCre-QAbrTUi@d%!G*HhUBHhlR+2[`#tqcDi8.,FM
@M3J.2g5S2I<LUp.KuB$%2a$#LsnKEiPf.\3LAnp')5-]Af4K5+q63NP,7C.WR*1"\FG\O_G]A
><<3O$fcD@CU-7jO1b,]AU/"UXJW>t3Wp5eA!D]AA:`r9af$nsu?,"R+R9E&3XDZ;"RB;rm<))
m)cCVt5OW(X3FZ->:ibiR0X5NqC"XYKJ.sVY`K,l*WnqlM!)`RHqM2)B5dPs6Qm:*ZFL-)2m
Yn8^Os>lrO66NANm+M6)CAc:#C=>[5:Xpo?>,N5b"\C\:D3msN5e6LYb'Qd1@Cee%`R?C*ca
7Wu2=Qb9-m)*nD]A'dU4"SqZ)0+riB^I>>.%nOlY5J$72.k8`r1MW-p']AZIJ!`&/?Y9QZ5L_0
&<Q<4f_(K@60pQa;5rRGL5En%^!b?o9Dp*Nf#S6$S@$H6o2pP1O2>#5#9@51)$9(Ad"R@0Vo
.+?]AKI`j9-gBgKioPDN&?!Sb'FeQj5E<0t@E-7t>pC(nI,N&\9AVfa1:N;rm1L9XY^5A/>T8
WL(hi[gf58im\XXk8/2QF=dPLsMZk_L^TPV0jIUFb`FmV]A4p9(&eX:=&T:S`VeLtTkYpj<uk
2EnqTBspmo'V\pG*!,?f(4b7Fm_,Ka<5NOeMr$B$fS#5CafrmS]AgTh7@R210+qGuNBJ0I*0[
&Kr<.Xr5PcTiW9b.;X@G%H[L5\&-k!+_rcmd!ls\g)J,cfM;&>ais)QWWHI.?\i&prB@%;6!
,8A4K)pWB]A8uD@m3!U;Of0,M)e:CQ3t?Mr;%'FZuU(,+K)7oW7PsTnf`6P04M]AV"@BsnhY_n
,Oi3I]ANopikfC0Gn=A8tt9.<Pk&eV/YcQc_UbCj=dB!$(bcFW&-Ql/fi?2;>]A4S#`pasK_T%
23a6EFkgUX-2.e\nch8'D<c3)J1cGF^4;24;ADQ+Y1[@1sI,r?Rj/(N>(&jrot"Z\&=Qo`Do
-F9Y7H<h`1:5j[;M*\E)6gpjTC*7n'b"_dQ@T@=Le!hc)d0@?d^NXCGF_jGAYTlmH/5m)Ap1
&h;3&V$QrU3(HZQF[HUJgjo;:ou'ig`T*o#:1gKl\S%>]AF:D/4l*dl6KF-dc,#0<D>bS?aa!
b7\Nsl.;N>jCHM6H5OkHC!qq@r#c2fgdoqrFSoJ:2@ms24M7Ods+TN;N=(F-L26'pc8@7IQT
NB$IM/FnPEl,W"O=%6QQ+'Z:Y*O0fN(QOM#/:tG*[0m9aCJ`rsrF;.,@@%JDV$_6le+OHNFL
C7lUh)#i;^\l1P]AStiercG7Hbt,b^<C_;-[q3\i,rkfPPSii`IeM_+&n$@aL)26H4>1$X*n;
mO,.kC3\_(E2P"$fT@+?K0-&e\pm\Q/XP>4A*O8bi1ISoH`6nkXqa8`/?Pgt"0n"FM,pZ#aV
ie]A1,'43mrcP.E@SW^2bP=:B([2[+"hM^QU%co-IWd-"cJ!/pb9e+U#iDZp5_;0.[5CR0_"W
?4*F[.0.o()@qR[qN>h(>*+ej.SNO!K6H8;qBcLKLN>ruVB)pGbkqc*p.f^Sq1o&\<6/ep^p
(oR>->DDn1pqrPQRPrB-:?fqH>&L"0dU*Ro)UjU]ACIGHbQ1'-C1#KqeshK^uf+tMo03]AMX^:
[-D@IP8Nqc>I;W*L4\+,$BR+59]Ah;eSVRcpf>?po6!Es0O8%@,+3OirR'ChhT<W^/dqFbm$h
<IYp1E90.Y.$7/dBYem]A"7BgBZfoN>EVUS#9[+Nl9d!Jh:9h2A$p2,"Sd1/:W-WI`6n_OdR7
hZP%MVYU63Vr#E0ET!c2YCX085rX`4o_FHV'jcS05qCp-g$^UO^?#k]A4'Ds=F[1Zn<D<Qb]Ar
jgP8lEg!PfcsI[*]AasAp_k->;Qt7+M/k(s*1C)p$Vsfi.CY7P=6#Y,9XZT87om\*P@Dpd/Vf
b90rSt3W?B='B0nQG@:[cOUL/rS6PZB26l@d%-a3b6ot(4/tFG,O'Z&?K=aj/XoiN,B%c?4Q
0fer9Wf+_0ofC55K7a4RDko2O]AK+$<7,Un?+"&"_.HS.Rpju_n#+d&PUdp3.DC@OD6Dr&.:*
?S9tQ?VVJ8*dZ\.l]A+3g")a2VRn9'kmY*#@:A?g,<E@LN`-19&(O]A/rUErfe&eg1#"hL*%h'
RYR8RJ#u>0rF<`MD*"a1YQm?DOO'_jp#BO5TlHtJW^a//?11q3=B5[HTr&b,Y[tU_1>V\3Gt
3P*![o3.<qD'>Ii?"S+*G5BHnIG)pl1pF>`'KAk8k2TC"P($l\D`.!^Kc2B"^KF.!D#J?),>
MEISZC*57X"J8In,ZKeg3U&T6'9Z>U*$m&DIe*tGi\5\Vhou^!1eiH>0dURi1%3egN9rjJT*
;WS?_asgl:h)]A8'BH(;<3<'Eg"!6?+\08UD7[P&O`eQ(PA@$`IFTC-9IbhRC@;Y^fZKj;=F7
TaN8+PC,r;1N$]A:t2#koTE%J]A5!4'2M,;9W'k&!0PgJ6'W$jk_c7C%APu6dl6,Ac&oo#uZ(`
T5t^*DL=DL`#?*lI3dQBL\<V$C.56k5bR]A'A9UbDF'gRB.i`c3&FL`ANch_G6.Q,e7C0._c0
KBI("mHNIG*19TQ\Z58[KKn(YqlDJ3G<c,#shd`Dl<QJAB.jFKHg"46@GOpr8Qpm%%S%E#CZ
PQr5E.`-&:^Zp(#'Z4+geANu4[@9I<[F?eE^ed:Vd]AYg]A6*pf`Dpm9APL$f9jM?_bk=3.j4n
!358]A-aT2pWk)Ed@tnYP&5)e:W>?._g*h051G!s=k:=L"i1+n?g$@[Wnu7lTt,:j\I/nQTqO
0FP)IYR4h(sP(2L"EI=WA4'o02)mMXSKNPDZYmtclQSBMV?L(BKC1LmBq;_8h=]A]A)>30*lZ\
e6SQTggRTVl:K'bS0a#+)TJT8PD9oZ!2FKM*aDXaUK<?)9,Q@*3q851lJW.Dq!]AHu3q4Q93Z
3@3]A3fCdZ0onsT&/$a'^DQ[d?`cS`4Wb7[I>HLVQL@NDaH+b!5''kYGH@(RBM4+m6asr7L31
H3$*n<QG?hmk@LG]ACGpY_b)dh;H/+5!jQUVWPd)&2,jK,?ZsOo7Y8S()O3c.3T\^N?7&K'4S
3$"'2fd>rpe>%LGYWOEf"><6#"*CGd8>d:eeqCkZ:816EU'gND[=)S[,>%]A<k+:.Q]AFi1&g"
!(6IdMY8J6$^CEnl7la++"2,p]A;A%t9c.2',^Ki4OZ*iiq+Qut!jT!"W\(=J+9,t\[1OM89c
ou9/1eUQ?Y]ApIG]AJ0NfI,LX\RZ&u,gNhIIOK!74Y+)cf)naENbn%A]Ao_:R:8!pgB<U-=D$'c
8STNIL<K@eWPhQfPH$N)OO0"6@j2G&u[T#0dK<(<PZl(:>;OIpsO?&]A^b"pJ_rb5nbA0<M%<
%VlQn+jg]A?7FS%#Fa>b.%rgCUX?LblAZ[c./kR$q[aRkcd$E@"F0<J0cJe6'/C5sO8Fr/gCZ
uPnB1*<:29p6I44jEJ3Z-[siV+(cZYA<8:Gfqa6bH4ljS)sg`b^LV<`\Ppu?$^?hGaUAKI6(
JJQI)`Cj9JMFo=F8_Jp&8VWd/:7Jb2WjM>O`=LFMK7VSlW>[3Di!Rr$I(DSK`Bf7H`Yi9+eq
$@f")^!\j>Om83,%b$?a&rMaFpN]A]A\!]Ae$-17TLCiPAM8;66Gb=(9'6O;BsDCA[RT*Tb$ncW
fmZ.GcZ'b:$MB[\(U`C&SRrV=hg17'h(+X2@>Xr;:%`)Ds7@2q?DR5D=LZODU=364'2N+KHc
iiq`)-ES*ijf+UW`,k.A*!69s9OCkWQK9,88,>Jk-PqfI=GOHZaL$VpP9^t$9eHrIbQo/1Q?
CWVU8%Yr\k_DK[2"5cgLsHm'i.Un#;TV(pjpukl0pLtf3iQ:dMlpL5M'+2NSN1$T3dK2[]AgE
2+.DpN]A?\92L;X%G3`as'(E6_.P;lY9ng"Z'S&CUnn006h[h;j4:IgjsBeE/4>om!Jl`Y[_u
Js[S1<>,p"%ZfAVi.mudc=R9/)1o'mG:c8DC!,H)N!?/s\5$Jg*k]Af=':^A9=5:<)r>t86k#
fXWgg`Q&]A?EW6(WBTA$;UC)O(r#+q1&Lp#VpYEF9R:m9e)2`H-Qt;'-GBnMPZ&*-3\drd*_1
OL'UF<eHi$]AaBaa61/(g&):GZV0Bg]Ac"BF^FMGB4G2jDOM4JsHM$S6.<&_[O)%L)Xk9Ni:Or
Ve;^"R8Zh(S8<Bp8J+!EiE>L>*QroPcH.jm?p;2U>tm_Ec2F#`En+_R[3]A&D9gHJB<>libeg
<[b8qJb(Gq1%PN]A)MO%*s<G/=P%blm1i`#<t_0Niu'g7P>SKZ&F$+mZhV+%XRed>'KNN5m5c
LgM2@nff?^NSs%B3nQ'[cq?d0:j@[<k65Y(!!,1\!.f1DKDk4pb*4\R>k,_+op*)s58)P8IQ
2Gk+*CjNbZfWmgd@NNhAISrXF#cf%[:[k5@!mnrD\,.#^r1)]Aj15m4=gH;6G,@Eac.Sl:1Pt
VU(h`hP*D:J'rf>eC\H9b*@Q`\&u#_QI8N7U/Soqn.a:GIC=0)$7Grn!`6cIX^$ZhH4'R6gO
>Vc%LC4lqc4&S#b4NJ!G`(a?REaf<:/>T:IlaP-4sDQs_UqNNr>%$l(J=mO.&8T4CN]AgY)I1
h'G)<%+/7dNMH'>9HT2.]AuHb_f@f/ZoQ,lU2c`@'I6b$6TB<HQLDZGg$s@OdM-QW%pgDUL?U
dTZ*b`h4jW"!%F4FRTS-@*!HrRI-9i3LOqN!jMHM"U=&7)cjA?;.kCOK+"d?92V/]A\8iIK=\
\u]AnD$#fZQd7/Jq6oD\XGWCJt']A_gk8^'cAp^_M-_>NHJZJb3l>Gq"BESa*gJ2lOQXekAeD8
=$Z$P1mUsXB&%Bi:4e/G52D!HRE/!F00Y"aggS:<1K&H.mJK#'GV[lbHW@=1ZQ42(Gl4b[q1
cIofX&VtAc/&1@IUb*)e<WbVN&+@#g\,O&h.qh'c1W9]ALA(P3Q\F^3,I>`q%_uS@L]AlQ)WRW
+BWq7N*.pL[#G(8Sl1TK,_;@BXX@T-q&N2+E[qGO@>GXQZBj!TLST)8k=ddIN/:D1T)H+u!7
nHhhYC,T]A[IBplGq9$FfAb&@^1.HJ)-n"BH:q'KVSu[[@M(dI=j9,#EDLpX=MU!:j_VtWe'Q
n-]Aq^X9=Wp!C@gajp'9V4SX)iB+]A#`2=m6rdL6gm]Ani>u'rLA+(]A55.L`OJeFNuQ[Sor6NZd
E*pQ3Kq<H@Y1/U,B$@+2)<$HB"XCucr^2^uQPZUJ[IeNA&c/gqF#8e]A&VbG"Jr5D8k>a\U>\
j?-9`,9uPG5FDt)nBO*!;EC)+GcF94t[!8N$T*#a5..R!2WLjQ4,[iT5spGI;VFF'%ZD[fGf
MAML1P!*F+I$9O94%m(P3nqaqhm;)f8aRI?$gChS:_qO!d@pQ3m*hNE6VS@M"*SpQU-JO5EC
Zr!TFKQ;2=,!gSg[SXkCH(.tRAO_Gj82!\9$G=CN[mir#1M3?P2^8PTA@4Am8#Ji_*iV(=SX
1@ngF]A2kMOqf#47+tCLQag0q)Pe.qFX,p>D0SQ`J;I>XRGm11F/_Me*C6iQ/AN1e''5*a>76
,pV!3/1MJV&P7HdFRa&L1r%O+1jP;@_&GIYn3bsCI^.83;%farSfV7mtU$;rrhK+H18X@B^-
ES(qq9GCTitXsJ;+=/01I-VkVoY6RK*hj0HqOXTjJ@980L#g/BR>O9K>u-ijS:QTb@@^Y1QJ
RE2)/l4>CU]AVV:X5.8guui3;]AIW([sUh[d?%n;/4'iaae"5c-?_1&bpVg,V>e0K@SD/i6miq
4!)CtdDfD+II]A39hT7"mj60C.)""u+:R@9pNRqIu0$<<oCe1iJnBjE?+Y-UR$+:SK18$$ZC6
:HN?IW0P`VK`^;lt7h#7#$3D[_@n1D-]AARMnrQMZ:9/)[oO.LWF_ng=8t&J39W.*]A5F6icp7
g)Zi3JC6_JH%W^\!X/!gCD8lb45b`=+1OXFc&q-AUM4#!A4fH:qh4OQu-17naZ`fqD-4+S2a
$YPKVC4_\OBs$:i0^[l+Ek)W*?JJGD=_%<1.-Xbp4>057QnV,:g)b/^1KOi_?O_SF)?/`or;
_=9H[tq/qdfOdV,.D\:RJdbi5F;V>BZ1ddu=AKY$MaKu3QuQ72EH.90K^@3??:<7<Qa]AEN52
0i70)0/jLaAo\c\J<4Qg)I8glo0a&61E_hC5>QN?M;@h;&^h*%K_f<cTclP*jXB!Mpt,<MF[
JtVrU?UmTc_jRV:Im@3W(12L.tJ6e49,hWj6A;A`%FY$l\X(^J9%-DF>aD,$%49XPAR=i`45
u:G%'6IP&c)YS\/uXJX2^81\Ngkf:+L)j-J,QQ(e`5p:+Rc$K8"$KKJ3a_-c)`G@H]AURCK-q
+"[7)jj4+@9@HU)7_'licA0g:;DeMn?:NgO<&0(A<iAdUL4m?R4To=!-0WqRn'oi=``rW)uk
'!_i++kaqV"YSqDed.9Gh5]AHGhuk/PXT]AFIPq@+<[tPC>ianLV=iV:o5*U#jr@T2X7h0'2u<
IN;R\l$hmm89og"(7%DN_Q44s`#jj8PjWtGfh?i=C3[8-Hq$+X=E/3568kW&>Rk:32/@%D-2
0CL,4FkG^JesM:I]As9Gk`K#D9RAp,TR?oMTH/[Z&VMgGa1@AWIInSGr*LUl/ZQKXt3Mu#,T#
S5[D=W'E4DC9%$1tU$*4T$;EQ^CYgmjPrX&SDpg3nc?d$`g!bL34m93$p;5m0b-^$fe+NC5.
`ftY<s2b3Pk"J1q9,"c,Zc.OpU+;k2\,;!qjIU,;.V?Ph(HXSbIBlhVee8+EHLkIKqV.uL;)
+]AW?me@:R(@A]A$*g6p9Q+=GhT.Md8c!P`VrY>Osb9E22@9Ri(H$$+6C6q]A(cHcUmSIE;+I2]A
3A09,ge_K)"*33M0'RAp,n%o>[uCoK,,lJ5\n^[5&ad&&/F=+\@)EXOM(22G3Z*1W.8/RW2s
JM*9Mt"WT9ArdqjR8ABoYlA1PXp7g'!\OmS@NC]AU,980BfKhX9!`mJ!&4K?H]A)[]A,s*tC!/X
B%au'BN+@ILY&3mkr0e@<8IGA]A]A<:A/q?6M$'qh7op$DZ[:tckg-1(#>YWPCHl-isGHW<8qZ
XElQ(USZGX^XS']A]A(_#d(*8F+o_mL[28\F\C.n$*TajnDM4RV!DEARQSRrX=mofd[.gemp0C
NHnK1MrRs(cc`l*N1T&u/Od/Z(?#oRo;[3cD*)RX$]A3>%rR*iV2V\AncIDYAB%5YE@F-D9Fk
P^6+8i6*%KXnDrfokR7,L.8>uD\e[t;_I5Ej5L+Qp@i>]A(Z./9Kp!Lsfd]AOO]ACYn*;o_Kd@S
,sn[n'<XXES`S`fhDOh@I,9`P/,Bfcbks8HN%Q[?uFB?sV@1>'#_b0<,3lC/*5)&'u,4/*(M
2IaBjrchdL+6?sOGF13uKeptGVH/*>3Iua4Od@R;Cs0]A>sd`,0Ke9tF8b:m25O[q"&QN0U`c
R`aKF[<0JM!Z4MpaHiuT5Y.>ON&fW1!hibq7rR"RDn[OCFJM_cR)5hIEM:eAEmf(V=L9Vl62
Wc?V>Yo"Cf7=[`f/ZbpC:V@LS4Q-PkJK@Ima7=(8t#*HZs\EMDR@+@kV=cGa26\T&):N.s]A[
&(B-[3H2;"3W<\86XRQ-Ob`_qqe%$7VOt7uJBo:^jcFL-#:U(u#S3n%4NN_30;ZkeR<&<<DA
4,h5XiiB=Z0$[U*"DqD(U%:0$'-0>.o.iF\Wb:P%:>@/*,g.!*S/pe>)c&THhHoM9*$'mLtY
@V;1t>]AHg\Q(kF%B`\O4R(GQB8HNo/&[[DT(DHOAUT@_cu)1ls7d[-3g&V_u#f\3kf[^Hm\:
(sWf<&4M67hsH_g:"^=/SBcRS33T(pqd<$ao55%fd]AC<OV:W>*T!2urEH8<KZ`Pc'br:qe,>
GaZl.`p3dHte'XXKF&-uUH;Fee_9ia$3@59JHCmQ@_I'^(QK:rcd3D-iS=SC+BK%AeBH;5&P
`fqs8NbT?0:1MEIQ%']AB!7<447ucm59_7m>#$=U;(uefH?r#;l+Yqo&Pe9"^:WX(6K"WgO5^
9qD*cKVj<k1?G["o=L&%YQM&h7?5HWn"B@8sfF-T:+6>T5iWApo<EU=KJBR\=a%T0\/>)Lmi
9FB;><%#PS,!tVPm"oQa:>e#EgbGiMhMroB,"Cf\:%P.4eZ9'QIP58=IMc>e,)>?fM!SiLo)
YA9uBd1_(LPZ?S6Hm65D/S5Eh^h^/]AD::<+GBjQ8j7"sb)W=Rq4M!>UY(>js3kB$;]A%DLp:J
,=D-DEbQa,]AFB4_1:G>p,g0A0f8g)$e$$pJd/M1ckoKo<$Y$">&P&aWWBjV3:Z:Ip<u1D55s
&B0D="*V)%b`TXr$;jo4.&@$hN8o2-VD*MK,sNoNH[Vtpn.eKTRqcmi_hC,H2dK0TU<IMBj1
sF0`P)M#U%F*ZX_)6#/7A9(_f<#10M<\QM@pJG$QBfODciM`P!9K.e8'_-%c=hXH"=KDkg%`
cBGgku2j:'=gXH'%HOuu=]AdRjcI\uu4$J*Pi<<J:ch8=0US3C<E=>?nELl>@r5/+8^`K3,EH
%-M4AX?.#g@WJ->5qoD[q+AjN\k-N!^]A?7EkPf#@r+a-.?nCt_Nbu]AlD^9F7h.bEoE:!jVli
H:FR`sR,X(7L8IVA(;5^7p:G!qI>Z=j41?C"q%c]A!^"NUc=*o#[/*JUtahHonDqpP(/93A<E
7JEPS\f>Q/\]AolD@C(EQ14Dat'I?G\?1X,Pr0$hp6fi3>K,8A"7.Q:%@oo.':mI@!^d&7`'X
F;&.bb'V\]AnY#7KaCL(@Is'mPfjenVO&R'FqeaL9T9o>I)CSPG60ZnXRur',n6($V._onWcL
K-+H7?F*pZ3.Q!&4#6*3DmMk+A2_hGG@TX,:=@B^0Ms`]A5aC)'AE(%r&Gpo^lYOS4_hT>rMR
r1Z(&Frg:?8<)J3OBQ$F\V0dn2ZH"o+BPZ/c@"&B'Cq:gDF1u/J(r'S\#s,h;?G]A3S/6_[m$
q]AqVbP!-;uY[oL9SlNnNW#`2bo*NRu=/k2Ij:LF5h)$f31*Y*L_>(jXeQd"S"A6Fj0l?['p?
oNC9geaRq-=aeq2ngH,>RTIMhfiLH;?tQO<]A()fjffWhjb1G2nM6UNjl(q#B6KY4.`7[Z:hJ
8.rOju>-IS!kgm3"JMVg)Cam%[aCQtBkZV`5>:U6YA5#A#&U;5,1WB/C6.+]A\<^S28UOJn4N
=ioGhsZ^YeIVB7ES/J,4`H'<9.SC=b+"GiIlY=,1!!9Y/MlhLYSQ&mr2_B]A0QYT:QYF=)&S&
&j=1k6qiBiJ8Yo7l=`>bH]A@bPe5>=1;I!@/k\37;Db)HctH<^fQHV'D1CT#=1sFr/,?7(22J
qYjs"P*7/%DP11tj,e\Uq+.3KO!W@'EC8aJb!2$q^g(M+CUPZ1g9GFdai`#O_m&i>g!6WMiJ
E.eF6%Jnga34q'dGA=Ipn'*U!';#+('n0s4Lc5"*cM%:Cg#3OT*Zm2j91FRpFuPonJ^70GpU
R2[D/R8\1'YS1ZXQ(E"mJQj+$WY"9I@`_ET8F0[)?iMAHWp)KgA-TA4mYZh=2Mnlh;XdMS4n
lhNtAa")1SX$>q713s.LE7t4e[4#pq,U3g:3>aCI%pRT@W-$Gi"KFR[1-hI]A:nVB<;B01gMS
6A<g_UIcrr]Ak%J,8ql.e!]AcljHT<Fp5E=-"/;@gd#*o@4[.!oS0,9RC@I02d_(OY,jFVo+BK
sT]Al(&<-MNG[3]AHg?%ELM^>0He"4-GIOItR\?K"VM<]Aa"ZAF^FoI>U&s4UYbb)Phl[\i>7o5
B?QpGUkkX=N&s$-`7JVG[^K>'cP%Y)jm.l[an3^=ZT)9,G9C(C]AOM2<5#qEHf\6]A<Lo\()Nb
.uPG[4GZp&\O=lD[4-!D60]AWd_8_O<*H>)alt-khS]A[j)I:>23A"5pq(Bb=sFSHG*iV8DO11
t=H-n`S[GL//PheG4dOZhf+MH+"&H7#eC!NY9$VGt0q`pc@/0D%qo+;7&88A8_cV]Aj.8AQJb
`0"Vn*siWO"YH#0+`KF5*2%\N*Nu7j+bX,=X@%mF7gsq$]A>k`ho/C3D]A>"R32DG\Q0XO")W=
9@L3V%1;QU&b(m*9mr`7b5!t)n\qU@h7.npWB-98U7fm<Pik\4REV2dBZoL,2M(F]Am)Yk[T%
eUOd8k9de$WkX=0Q2P16YAXM.buB5^V/2LpYIitjp`6j$0.-_6F>7f`:U#>KhlZD>K<6q$RJ
@k!j8Zn&;?d5]As&5fjgFe/m%!kqpkpm[H=4*"L5l9bgO\bmsaJu4.Ie:H5r$\E->kig8H3I6
8qsklVn0;Ug^%J2GZoY;u1.U$tPohAjgd>DBpMBYVVR+ELYH>HaD"?32A1CYT7<Nd]A)6\Jc[
+>rL$FEd8s18u;rbqU7WH8,NebDn^413XKkP>I2PAV6oAK5,;c,V75bpHcOLX/jV7h9GgOR\
+2#Z:7IZF-k'"+N7lFb'Cnjh"Q!D^3J;2/LB]AAGZY/r1iJ6TU&k&p.nuXkXE_R=Dk;-cVNV>
X5CD7p\bnZGukg6(7L8-;F0`tW)s7s[6\STgNC"N#:$]Agr0cj:7"meuJuejlSLQDCk$I7Ff[
[sKMPc$D.Ua-n]A?e.B@2`2%6ah=j_IRG#n)4LW'2e6OI/fu*]A)1eddV[:f*L#Tu&,'J-+D5+
e3AsD%r&`j7IN-RV(u'6XM30)GoBtM>\b#Dcqf@6q#-^\C(-"^j8+sIW::IXIQ'\rVJ9Plo8
)34n[ga4a]AD$tu(r"n8h>\n73k(A(Xn<(:WE,b1s-Bi*!AhKWp\<m#+Go/`hL('PVeRc,2kR
_tFPgf+F-f4!Dc&^IV7^C9F%`UD0Tl2%LOp!/m8EFKdH3W+*^*<>pkrhb"Ua,J:q"*1L.4)X
q*c)5pWs0E/Y@k$Rd!ApT<#1ip^`JA^\-I,+/%XM&Ns-o/p`'`BT.]AGBA42Bce9[cf-p[<p"
'Q-%Vkmu8]AFaE1:ACaren*k<)5(.]A_>J]An*8VTjO-dod\`q-.DU.9Y[GWlrb%+Fh75Ki6Z+:
6s1u/Ah`qo3'WPh'[bT-*Xt!6$3)n@A?g.G1i$qfRn%84Yh+i!^.32o7Pd7?pr8X@(<:QUNG
JQSMh3"7Jl]AEj4$1&ag;PPnH]A#Dm0/n2kVpDpfOs,Kn#k=R0Wp0Khg;N.kYg83;ABD.RsQTB
1,.m^fATLChe.sn3Or?(3!N,gRa//-alNP)*2c)KVq]A5W!rXS>R[<L.I-UJ,[=2=-[(Dg1%?
s/S+g<u9Km!7@PTG-bd`Rich4OBcfqkEV&[j/;J:$bEIPf]A4+hNT98S6&t5+ipIcQT6E`^?_
VF`meqJOqF;]A@`A5aY.OH.)r!Uqiq,)qI:CEQClm_I?]AD>7_5[mK/T;d'YZEt!T^(9T79D`4
=$-T=T?5J2?*=%s#@^ll$"9jg+JN/j\+.Xd:CooYdZZ#>Hr*G8V-5"[U[$9]Aikp6$C]Au3"q,
?pnulFD.^Y)/H"m9+S'Y82Ph>hK9Z9?)dn747'6@EL!!>Hdhj[1pBHiC2%6h9sqMb.B1aK%Y
K!UCV<\]A`V9Gj>'mc^9\cCXs:s*WE"LY.bPmmU3WhRi%UJp'IE(Q;?ZMnOj=6H6W2R4@nU43
NPf,S/=\VFqM`b%n!Xc<9GOnddIhG/TYIl<=.diZ29I^m)<GS%h\0;?Bdh`CXWRfHD"7Y4,0
mGbQE^#efsr8k7F-uB!"WF/hL$G5q!i#9mTB7,#a\W&6--'&(*mX3<ngRMi04PqNf8i5VHX-
Ua6ZB1U=%V^>Dt.<@ZST2F4%BfeA35A3^t?q>043!FO8pZ]A\Gh`Ke;NU/D&[')S/_*93KtZl
H6#Jbgm^"[=EIABI7Z1h]AD#A/bkTle*Ql'#g8[$knLQ.jqB(Rqt.<Y^%$qAC9k_=r'QEO,^j
tS&^_6p0&I$7A5p#Ch/jbq:hieqS,XM.;e4A/_l9S]A3"7P@[dW`lL@i?Z0MFWQ9qYdXWm(uc
7\ML\kNNiM8&fF+)*(+@!/.Xf=><@p2Bb*/#QM.#NI0F:kbBJ9n$bP9&FC.aMDNrWhnnt[/;
Vbt'I]A+h':6kfLJausIB-B+8;b61[mR-gI3kGrE4UOGFY]At$fXkG"l?75a0^oiMi@K:Jb1l-
+9p'!>a_,$,FQ^GT_Cp@IO)W/:j\3j\M<gH0dqbXJCnuGmiPR(#,.?!.nbE\4Ye0epEa1'EV
<Oo/S*heArK#"r0,RV:P9./?@rZBXrnC0OlG49NgP,8^GA22IJ%GGN,uq&Hp*Fa7&dV<YjXf
<?4MPb3:8[?u,@9$M_k$MQbT7F##PgMc44NFu-p?F(D1n\Ym@!/;C\51e>hCK%k+&iC#I?'5
^=HH0auBU^\aEKMPBb]A9$)=&.GTZkR/JS1lB\KDhK9UZ+WQMu_NU[O/<=X_9eHCoYoBeg<s!
ku?Y%?RN8h,kiEcMQJ5?W,5?a*/5\N]A67XP#tl,X,'!_Tibm7Lo3nn-et1orXOWAr?(M\d:j
N3t10L:Aq:0n$:bag3eo[Yd4!uMdN)hhf_:A9O#C?C_\;*LGrhm;^;!?mI9=t^Kp\3AQ;p\h
7*#"P.3l37N<is;EK-^De/fb.MdqJ6P;k7lPtCgF*b]Agp7DBX^JQPuXKnash]A'>_e!]A_B^Jo
A.Is%SuVhUDo5<Q3]A`KgNHb&6bUcJHYP8]A461H#hT`:)YsQ0h\c5+tR6p^;4*>R9FU0La/gs
-7sCR5KCYTHiFK3rpM:bDK1e4P'&?%rJJ;J9&*hSj73*VMI/AZ@Y7sVCaq#4hPmIZ^-MIgQD
ONHDp2&$PG,ncc^j>*!XKs_rB$M_*a4e>`4gjXOt5`C>:-gHY00&lp8fZHg)]A*t#-J:$D#!2
OBPM9lfQ#Jcp_OC\3\P;>K)_dcs4q!F!2)F(3E>3hFXNWX%kK,DpMR.+[[_ZL_m[`!AM8@Tr
OHtWFC*71)>o$3[O&S.47m5Kk[^FQf@Z@ubG1%!pg2b.Q"9[=9Wh*T(%)DmVU'\M3<)c_LVC
JdAL0X:1i*CCk[N3Efc]A+]A$Nftb2H[0>R*MP!92HDOG&Ncc>>Y29kffk)W]A?=/n`&K1&G&GM
mSNf*Sbei+^qj;>[JloKHY)8R4.@9jY'U&c1tHZa3r^IjrdRVOeNWdefIZj/<S1'TUL^n/q#
7"C<Z8"Bp\p_$#/86C??U0e</%3oJ$$'%@%l=;;,QAa,&hKo._:*>0?e^jn9S7Zj>ni8MXmB
7?GE4[3V/EUoD]A$~
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
<Background name="ColorBackground" color="-4144960"/>
<Alpha alpha="0.49"/>
</Border>
<Background name="ColorBackground" color="-4144960"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[m9P'<;eNO'+_[ZPYf#k)O+a<qBt7/h`RZNBOJG[l>00lb$arTBm?h',$K2^B%rBj2eWO$PV#
k\EW2V#)[Zhts,Zl.F=q-@Snk:pg";##b+n+K`TX1Y6c)T[1eo8=@jH$lIh<1<sZ%)Z@g\X'
]AqNmH-3=e7G!.l>7QMgR?2\9LH"$uFKbaOCe+8[hf<sZ+0GKJ#]Ar-)TBp&7rqX1Oi3>lJaIo
@rKPh1`1[:R>DbL:N[q@F)?,Zc^UbDG,E2o>0AtCWinOhg0,d-[bah^SeAZ,>'gdTd]AX"'cp
?*E%&r_.2hJ/YB)hPX/%=7%l"%JAZ)l<*q?g!?>K!ej9d[9kjOF*?EPq*'BTqG[<I,3j82+`
O[3NBrSHT6EI#?gNFX:(GC-sjZ>)X]A4-%d<n1PmM!*/72P.e:tOiJe.NRR!uY4m6$?GYl\`c
@g6pStE'Ho9Uthg"k[EE`"/$^0<dq@p;.QBh&2D7QFgh.H<X27NTs<)Cr*H#8$qDkM?NqSJ!
kkVcVA,d04JYPM1KV?QkW("CBKfBqu\HpX$A#4+?gTN6MGVjS;m$)Ip;mNCeO&71+!6Z6f$h
\m\cADd<:BM3F>-,]Ac0#$<,ISr1A@ISnjJob1M^;/3N)V2M.5:Z]A1BeADoeX4V^;90F31Rm4
\lT8G6KQf`S>>"?9e:l5Mt_31T&m(Xp&-+e"o+)dp6?isQM*Q4NqE3DqtGc#rsDn]AaZ_L@W!
f$rC$\%>"aH[d<EG"$P/;B\^UGQ-Vs`dNtIC`K4j;^>t=kJLgC_\8%Sc"%`T0p$lRRop?kW4
o(^+7;[Dc&cuMMg,=-DjOP$(c\Bq'0SuhNM[,(iX_[]A'/-2Le\D!UroIg72R8KuE)Ap#/H:T
i2a?#4Ei^7)XGd(O\b^pW/%*jfr:YnSlck\Mc/`SVRn-X["Kr&73(6c'j/`]AZ\]A<*Zs$fFm\
Ka6`1\Wc4J<r.($R1]A_NC,.`I)d2?d:b*@q,^Zu0bSPKVk,8M`*T:9UHd7_;S8Wb9.^Y9Q;N
]AEMi<(@c/tb/&XuK6^LtZo%%d_Pl0X8n_s5u(e#)X5A\qac?<)h`3F*^"XmNEU+%nTgZYs4m
:E4;g2msH]A6G_UmRnS5URZZR:p*X1;@@6)J.<$hSdZ3e>/),+%6d+@.lqA;X`8A!Vf$m*GFj
&idNI*k%.JP+@<3q@'@WKYOM&,+W[ebEa04#[lBhW,JbP!o()h1Dj:VoXO`6%f]AfDZV(DDmI
&V!(4)HHBeK_jj?V%DQ:S.;c3$34P"b/Q\he`\/lGO6m$nR+Y>X@ISW47&J2;@TOGbj$m++V
6P(a[GWA]A5+tuXGT9p#NMcGK!D>/Hk_j[KcP"1Qj.c7#]AP&2"gikjVHtqg^X?.*49DK$OSW;
ulQm\9=SAuuH\QC2qDo&L"hZ6iOmaGbC(aMoU+'K\u'X&u''tB7[mg'SAJZ&+;hgY,,RZ..d
hmL1F=`CsVfCC"bH$?Yk4_C/@3qB$m?<F[1n8Z,B^fnbb5LL_4/U#HSq<G,H>@uA/hsJ4[HH
`XKX`p>.DjaV/L/Kcg21+J:%%Tt!A2RBG<=q74ast`]A<TaD6RVP9289?%C^(XsXd>Z$UYK02
E<OheT4!)^r"iub;+%6`"%W:KT(s%tu59hp9*DjD1$Kh1#6a+`_#I-JtZO9@7YoWNDQ]AX(X9
@PX4dsh&a\YVYh`Lre4!tTJT(]ArRcndQNMmSA??TtVE/E*>\OU.:-ebH,ssJ,;63=W6//h7S
g.ZAj-rNV*m514g>DqZL5:DDu/Ph!JBt<hoP7oY<U(or1T"^Sd4mH<Ap'9X%W[RX;K)B.ghT
la`f1>6-;3/K:u':itK/78+5gZ'Ku:@AqUrh5#`AbkT00(VErV9CEA*Rt*rFG[Y'['e2MZZK
,TT2jChtQOtA$0`ul4\qp(M>qV$kZ!bT2gAt>=*irNQYkkiQ_6aeS+!ZSg7jb$'CPmQE0m8`
]A_;<D"_mRX@a^QZh%*%(0f6I[):/[8u[*>V78cUfP0^/Ih4O>>\>Vu-X%Cg!G4PT0Fb6b2O$
\3;oR3,%"Ii2DaauqV]A;^:#_[b6G<"euTU[.^ud7'o-GHkoOgYcqWkLn^%d8lIC`>7LZ>8c>
DiVIMSk-@iBq`XJQ*.T"MHf##hZ@0EiW5D5*YRPuGa[it*-=7J_#q`N4E3$OAu,$:,a$&jmU
*E\\q#J?WV_S$(9SnVc3DoI1)X)E&N%J4o-1ifW.U=<GD\N`a6>C1m^!U;<^Bu6$k;mQ;cc*
X/&7m$6="!IB[77"trLcke:q7W\G\m`t+f>'etT3j2dQ@shcop<arQL3TWCg(!*?4j3q$hX,
Z\+uf,Jt/9DjATLXUnPmmWFm@_K]AHq'W$PD<n1O3lKPUA<FF=<Q"sOkVk"V^G9^n#09%5fjc
(MVq?;Z%Z3;@kX9.06*]A\b*2^]A\m>X_US`""q\;>Z-F)-$auj8s0W,F0Q:X(A&>(KR:t0]Ak^
72!I3#nJcV8Xd;dbgg*I;J(EtuUkFt[]A]A"m\n\#T)eD-XYB8&a<E24pV6]A]A#n?\hdc8?*9mL
j=lsMHK]Acr?32I[:L:a6!4]Ar+%P+=dI%"nQh,Hd()EdNeoZ`dO$*OR>\c>$_3]A1`/Rf=KuZE
<QEi,p(MGnC2Dh,j*Y_CHfcjP(hE^S6o'K7Zkb*4+q_!DrRoW5c(6aR--$B=2XP.la]A+@ij;
gMLUQ0?Oq_,_.\S,o]Aq9]A]AtA.8n<%N]A'MEBL!d0MtVEe8H&)5u,(%u3Un:#8?H[<L%T:,4cI
?a;nc"5OE4!ECCT-fl8LU@FKI9'@7#,WO@b8MS9?lJ-I&>C/BBtVW&m!JMogG!`?GqOI_f`;
B+1O.HkKU%h?)j,UN;*8Sth3Os3F5%UA$;N"4%8l'R77TGdegg0Zc&Un241%TcLT3S9$$ItW
n-5g=OtmP7SpU0%)9FA71j79K&Cq'98*Xs.!#+c*9_cb$7TP_iqtY3.oe8f#Y+)+J*2OWQ9&
U%?0q6LE;1HGfEiEWt^%HqU*b&=(U"BYmof9/J_I8)#Znr#,VJP*dgV*ZZquoks_%Ls!5>9G
5TH:9f:+gTE=$T`\BWg8FGZ@fMW-BrC/3&R:NC31P0nS`4(`1gPL/OhH:_QB/]AL=K0"V9/+,
ISDZ4r-n%]A`bngoH+22PlKfC9a\ASEZ,-b0`q_$lTuhi*>O5`/9+(+o+m;=]A7[R0EF\OSll\
U<(-qXh,t'N(Pnb3qH+$6N.iB$uX9GtR;c@hKf:r*JX^8$T1sc&2f5JEAZl5YG:7OeJ4+5WS
#aS`+ASCTp.%\aUMU14ac\(M:E.j%2CE/&oVCV^8_ao`Li(IA63.*,+P_.Kcl^MR_V%M99Qp
[LH_1T@(b7&5JKGBnMR[-D*HQ;J0X[,sr!R#nK)`2tZ",&(lM_t0T4A5":Z?-A&S/OR'70k2
qe\M#s%TZ"aKhjC&l-"<OPYuVc?MEX<,XcnsgjN$Rc!%7<Q2DJ4/b/uJp_dH)\41G[dcc@@Z
,S\i0'ubNd<7h2;o9,,%af5j$d)]A[m`<<V4%sOIq6;@L;p9bJA+C<<kWgahChHcQ%Uo,5&T\
\bbBH!WRcX>`W!mFW5XJ3dhphhXcbL77#tt7*'HJ\0iagce9SgK'"Vt4^:]AX6@q%<^kII&5G
2Eh@79fS><\HNYAPfC@K[XRoB2>XT9kB?i/Q>>p^7eGrNn]A)!fa8P+(qTimSgZjrc`Uf=gG<
/Aq9nTn0Oou[0/Rp7Vg<P1sfq417VJ_Ht$uObq4&Wh!*q3=bZKWQ*ggDD&d_7_cPdFnI0EbW
#*t"m'-qY3eQ);QH./h&:)c,?[r8qS@.&Z9B@BTEA[<cRE+MET(Z:8]ANX,JD,jDDGu^=;]A9U
W7>bAq2n*d&\#,nO/OLM%"hak`"mS$6)\R;Bo#"=Y-nN_Q2IS]AI&G'D31ruG3Al$-GTc,R'H
Tl!l13VUAD`9YH?l#et&>C017A_6i3Rpl/Kj]A8WM3M+REh*]AT_b]AX]A*,4hN>I0ODt=unbK^$
f5+_!nY2kZ&(4*&/V$)`O)<.e2f(nK]A7FmFdQk:&(.4&EN"N<F1u:oFW0@t6c6Pp2cerKapT
,MnT-k@;$j^2kCa2[kdJ/7n3fV\fp`1n/bL_=HU-g4YK0GHJg5'd3pbH\u.SXjo@e\-:9ErC
T'O<OA)UN:1'fSNt7b`i7TXMRO23m2_!\p3Do1h3Z7;*O><q?$P^fMsGN++pQjjRKBAN[^Pi
3W@#B<AJOQG`L!fdCQNPG*(l0-E(-nrCitgCJirPl1bPC-mq4Ue^M1i+/F9H/qfOk%I6/2UE
CFS)c2BS<+)8`b$$AGVog*%G:OJ7IkXF!8hVlK:*b$Yl#icC+uMO`-!L`O'%[>,n$r9K02R4
CX!X-C)h=*=(g%6"D8ra:^:&CYs"LOArM"80t&bF1):j/liL78*H#5Dq*qU2GT:[6kKAJ5]A+
3bq=8Xa1;YE/:qKW8>4&1L"i<E!uY6uM2Ke(=4P*,?PX!S.1]A9"&,J)kVckM8,'!W4i-nm+B
A)[7gZdn:Wg;ZF2UeO^oE'36.W<3@k@W:8dQ@L8t[=(lP5'?tb:#Ma8ubDaIS)>k?0R6PC^#
I5nqag0Qf_[mDNFiK#1dGl,uc"Vj)0K`C+mdpjlV@*5qkq-\M79>L(=1_B2N<Peu[*\`k/SM
a`##5c%bM^PaYttP_'3&"T&oOcf*+OE:o*'ml*7F?CPN<A!94TSS:)D<`-EB\.3]Ackm!a*Ed
Yj*.bVMXfk8!Fn5.+?mr]A(K)uIk8+SN0e*P'8k6*"]AbmTW(@QOQ'%OY[&d^:2qBFOB0j1@B>
esOge5plm.'@EN=bQGYJBJ:I'45HJ&IXu[(u#,!b,8;ooB6c_>mXs<F"Kf./k5+NQU<gaFks
)%A+KCAP[-(6WfTRTb/D(VLQ]AiknZOKBNIYY,"ZcphnK6oY4e/3VjF!-^kj]AgXAqoMItmGW>
bkN_/kC[uWV"d9W947FI"6tWp2XZeea^MbVa(Oq/5>Y@quWT;K8Kcn944]A`,2%r?Qkc6FUK-
?M8!9#ZH(M2_8e+q-4E2D:##$Q-XW;Csa1l<,h9l(JF_\ANSr8mJ`7d3HKV<N0%/2B&`(DF6
$;f/$OYH<b.m,D%mQm64BAVOmp7h5Q2KNVbFF$f?UK.EUQ?<^R-W*;s2_ZUL"(u]A1hh/gc3[
OB4dtGbP5M\!*j6WQ0<&)c2ZcH'i<Aoui3bGRLcur"!p>fbH_c6qSLiX-q1167U3Jb,&Kr:o
8^t`EZKe7gHp$Cj]A\fm7Q`mkQ"m#f0#CQT-6UT!>]A$YEWF&]A_:,!3Q=3@)/%hQl?9q0V.57S
VEfnVoWL^Cm"lj!+"=^7QZo^h?Xhu2ish^6L)q:!=$o#l<$sJ3gRjN&)Q`iYt':2A7U@G!\%
VF,UpRi*^Re<J/bEM\1tJO1;k3T"L7PmLbt9r#^0QnE5E/$8&(?dWOT3$i:[Y,WMMDtHkC&+
J8;mWfIJ\qY6="3/&-4[eA8)t"@Z"X@QgYnHC[TeAR#46JE)El5]Ag>>AQ6j-TfOh\"2sX70S
'/>!:GS&3lEI.HA%kiicSR/O<GJLD^['B`aKed<uj3nlk/^&6cRBb3Yo4]A@L@N8]AV)VleK(4
s8l:4>2A,<#ThM.1$e0Vs,J7S5;:uTb!Uk0gHEZYp:?-"&9OJ\N8P6",[DKjQ'nW+;o6]A*M6
(R_b!lQ7*4crSd!^2BK$\:FV$c<ilO^UIL<ra3UT!2?\,#jT40*U"E!.j\?LE/4*Vb')T7?f
Nq0a-T5Z)7\n>=mH5Ql!D8&A<l%QHLP2+p,Whe(7AeHh0B:jg<,4-1[SY4$GX!n9l$Gl%_Ea
@n7),fi;A\<PTTETiLkOr354bgV3OBSD\=D$_Qr+9&!MGqf]AXJ"7`_K`Phd=eG".ddPQ)LUr
<gbI/&XCkR:4q#e3GHV_^&/-$afV3oH<+"Ws)k[<?I%.j=872]A$0LZccb-aE$Ba`?$I5a!18
V0D8s-@6^*0/__t[l98s3MUg&BbH>.DI\1ft\4ST-LX<H69r:6E@K(laV^?cKb)VQnM2[B!n
/gS(;A,,.?OU72=8()H/fDm,(0@Heb<Ijg>]ABiC.iuh=$6.e1eQ!H^9>$nNiN'us1FLXY_!0
i@Y!t=<PR2DOlFjo.P"8^[V_rW(?KE5`BaPa4RSD+b'=Q-jVIWHSMLOB;E*%VRUnV=b(M6A4
=$>:BrW<%#`?)U'8R4\WZJZr5DJA0/ASbqnEs4\XP^0EQAOK>`-*%eD<@kFd:NSN\[FB6o]A"
G-V%(ZASP?O7Xm`^V6fKF%&Xd2,kKcrX=2&t4@pJ\Q[[5S%15+6P@eRfD.Hj>\sM[Ui0bFX7
[RZTFcU@p!CRYiXtpjIkl\f9&Sn=Kcc.XXrDcC65:C(A2HZIoSP:E<[Q*#iX#I2jUc!AAUJm
3%<8EkIDA%*]A2hM<$iE(\sR0s"'5--O>ROEE8g_PmMM4IYKaf=.!o^F1T)V&hRL'//T[t.:5
trld]AsV-M#71HC(rJl9Jii.b+!qQ1W6XWaP>e$l-"-4YutGpX68_#.UoTS=qD<KSf[@-@2d`
,tnDpO69GiRnuYHR]Aa2"R4$h*//JG#rX<q21OES%CGK-iAB5oXg\F9>A1.Vk:n@$!&VEXZ6j
mRc$o*kmoS<L`EIST@]AkfP8-3<jh.ki]Ae`6HO7LA3BabC\364sZ<.:`PbRoR(DJck`3B%gQ\
Z='*Qb[bcff+H-J(80.BU[71u8Z]Ao%:r7<Xld2KYi4'UL54pQ.bZ3WCtqgN;K36V!@;dZ%\?
d4O'(T`4^"N`_Ao_nZe-d;l.m'l&@PaC]AC>eTBO!<~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="405" y="432" width="587" height="155"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report3_c_c_c"/>
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
<WidgetName name="report3_c_c_c"/>
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
<Background name="ColorBackground" color="-16170125"/>
<Alpha alpha="0.35"/>
</Border>
<Background name="ColorBackground" color="-16170125"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[17160240,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[28712160,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<O t="CC">
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
<Attr alpha="0.29"/>
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
<Attr class="com.fr.plugin.chart.base.AttrMarkerAlpha">
<AttrAlpha>
<Attr alpha="0.71"/>
</AttrAlpha>
</Attr>
<Attr class="com.fr.plugin.chart.map.attr.AttrMapTooltip">
<AttrMapTooltip>
<areaTooltip class="com.fr.plugin.chart.base.AttrTooltip">
<AttrTooltip>
<Attr enable="true" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
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
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
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
<Attr class="com.fr.plugin.chart.base.AttrBorderWithAlpha">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
<AlphaAttr alpha="0.48"/>
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
<attr lineWidth="0.0" bending="30.0" alpha="100.0"/>
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
<Attr class="com.fr.plugin.chart.base.AttrLabel">
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
<VanChartMapPlotAttr mapType="custom" geourl="assets/map/geographic/world/中国.json" zoomlevel="23" mapmarkertype="1" nullvaluecolor="-3355444"/>
<lineMapDataProcessor>
<DataProcessor class="com.fr.base.chart.chartdata.model.NormalDataModel"/>
</lineMapDataProcessor>
<GisLayer>
<Attr gislayer="null" layerName="无"/>
</GisLayer>
<ViewCenter auto="false" longitude="103.23" latitude="35.33"/>
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
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
</areaDefinition>
<pointDefinition class="com.fr.plugin.chart.map.data.VanMapMoreNameCDDefinition">
<MoreNameCDDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds2]]></Name>
</TableData>
<CategoryName value="省份"/>
<ChartSummaryColumn name="利润额" function="com.fr.data.util.function.SumFunction" customName="利润额"/>
</MoreNameCDDefinition>
<attr longitude="" latitude="" endLongitude="" endLatitude="" useAreaName="true" endAreaName=""/>
</pointDefinition>
<lineDefinition class="com.fr.plugin.chart.map.data.VanMapReportDefinition">
<VanMapReportDefinition>
<Category>
<O>
<![CDATA[]]></O>
</Category>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<Attr useAreaName="true"/>
<longitude/>
<latitude/>
<endLongitude/>
<endLatitude/>
<endArea>
<O>
<![CDATA[]]></O>
</endArea>
</VanMapReportDefinition>
</lineDefinition>
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[I(O_Je,T.I:/Sk^7HG;KKS7LU>dmT`\SbsG8o/CUKt0Waj]A9MMGI`dF`8#CJUdD4?D9T-;(Z
e[c^Xp+_hr!n;hs2q=cLX0Eol89&NG$7TF[>?qlqC)HTKBVkVG'Z71a]A(E2/L<`:F>M4M_[#
04h:aE2@ml2D2sH;YF98prKhF]AC=G2]A<^4Znc[9Lg`mqo<;-g(V(GY_1%Gr!!po//E,A+%<^
1H";C?2cRBK>CTqtG%$pX?N5Y+H6ZR0rgSp\"L"XcX:Ts#g^m>KnYr*q)cI,*ZO9HX\Y:$UX
je3&7,!nbaIba6e"#mXL:of)KrelNhEm(k\<-o5X)fc'GsWn<o^M471tHg;/j'iI<3cac7*k
Ctg`FM0BYtobA!l_I0EVfX/Q>p_0Pk?uVBD8H=tup<p=\gOBW$s5!?tpHT/ue[\l[_Yl9Eb#
#)#:A95nSVrLV0poRVcMo8fDis5u\'>P3mO[Z!s0)-O._K/fnD@.ZShf,/rU7d+R*Pa%E\=G
e,f@]Au[uN0@mt!o&KDt?&*$uFs-74cLL'H24h9?V$It%F+g`'d#]AKZ#^p3O;W#dI_+/CI?r(
N'qAg=k6%d<js@>0K-V[_M4umltLATbKcoN\XaP<@C(AUJ4M;G^MkQBAP5oTMgpE:0'VV)_:
3DY]A+)<fQHq'WSjC=QmWs&2]A!5F2X#8W/b]AmWG!W"6,A]A3WiX`4Tet77DmbXsI2fs7<TO6fJ
7b'LAc1g/g?N\uM(/r<hhBlj39B"@*na'3:IuaKJCiU^MQ]AQ%rjh]Aq2Fe598POb7*R"-VAKD
[N\OKD1-Q"Ff+!R.ubSqqOcoTk+qWB<W&h&AKokr?l2btY5(c9$-e['PjC^Z=A$8f"-T]A$;o
=ogFlDDP4anN2_shU+YBH`$kmJ3Tha(AVa,<q]Asoaq>V%*H!;+UgB9u5msk8t8()M/P^R\!D
bunU?:<QU4S$kfp9HUYZ7UI"c02P>&^!f+XA-sa$9M(d'=:+V#E.cb]A:^Hg`io<sC7/G728d
R(B0L]AUQk,#9^\4eL+$!Aj`f]Acal/@D/iKA@Kmq4(*NfP9MU"5^J-QHI:b2W6G]A^rm23S:6-
HWQc:NS@(0s!(R4Mr3dd4Na>/h5&pg,)Nfb.F!YT3tRSgeN_ZMcS9*M[Yl-AC7=GQ-Hu.[%#
t(qWoJshKTYa]ALU&YSn)*q>CMV1a#n0$KjiKl<6mee.*aa>ihMsGF]A$GMt!OoibmGGBD<%Ga
>ql?8#q+0p&9@CfM^9,:TWZ(nb&lD&j;q"FLC@;5UN;$ImV0#8)#M0[N6k7AJP^+suLT+]AZ4
i$^a8!f_NJF.4!KpQQdrY'U1g5BHCo4E`4Zn="h#D8o!"CZ*VV'JY?m>BQ/&3'.sGa77V-Gd
g60"eGK%&u=?hQnD[0c\smCF^!91q^T)@E:l)`UK/nfV=g_Rj?(bn_<E&57Ko*A!&/eZ[F-&
^d2juqQ@c<#T$N_S(E$.JL(mQrelIVjST.TqlZcX-^Nf$I;hah_m//eMbbsl@RAllmV(r\V?
1I4R3o:\p>T",M)Wg@cO?^Aeil`V82^@l#7^C@IC^^prn7F2>8mK3O,8*Y_Xo."70N/VhT`E
(k;#3p:JK]AXpS97MYORD<rqL#*MFakW*Gr'b?eSq56s;7sCh6+_oG.8MWBu^%"@>`Vn!bg$Z
uh5<Xk?-T+Y/VnY;/jr7?@p8CF^+J5489A6cVM$]A/9]Ake&lcsPOf>sT7/sfos%mO4TYu*:29
V'p7,KEeF\CN;N-fN2n?UoIdIno.$o`)l0lR/=h`'K]A.]Add."/Ep@BXZs"^>eMcn0Mt<>V-Q
j=k9MlMi`:Z'hPiHfJVBTg5H/_dUePddj/FY:7sKTn)SLc;J#!nLpFQTe)#8%nVd!NU0K$8N
>:BAqtP4`\J]AE-f&?efNsTWF"2fpL5"oRb]A8EiQCl,L1P0IXn0s6U#E>'l2rf.)R%'G69[hI
=g6$,-^'=5$AAi=SPe1A@cHkfbPLK7Q&'MLaB75-*lG*^6!YR.Q3W+Z;W&@MW[bPA`5p#)om
WAhT>,i<OVs\MH.S%&7Z`C@YfP!s<F5O_Km':[0AD:QR9_,r:>Fn9Rh1'ph98A[.F_?3)@=k
C+Xu+2'IDJRO:A)Qa+mA/+b3;Q)iq^b+/8g[;N*qP?Hj?\"Y>=YNgn-:0]AC>UiX`Yr$3R2oF
!VG:^/"fbMIB(fjH+lY%CbaL]A:."d'<OV=(pQO#%WQd6`3c?=CfK&ehkic^Y2C2us]AiP7@VK
J+jiHWRrKej_o^'ioG!(H'%4+U_,ZY!qpY!5g`8SW<rj/SJq:bbL3RH4Kt=O5i<:j&5f28%5
O\?%ZQilM*?Km5%h?]Aa=:WDftL;_;?pp^jD>?urM1Lj-"78u*h3Sn&L(N%T*VmA?:qL9EBA'
:$I^l,iRhZB$4g,i<ID1Y^IIFa&90Pjj0m+qG/</%k-+IDKD!p9.8s?MOJgXS4E]A9jiL*YPs
T8-XQ[n2kpYdC^WZI96Uh(<l[=_3hAKVUcl/da:q-0)qcue6BjS*m?.VtHq^NnYAD6_DMs?B
OZXI=r,MKq!2PuA&Gi`bAmFRTVe0;)dMWeoi`nlW-n1=NKod*_T]A3'sQ6JT#XF(AmJfIN$c]A
HRh_Lb_]A+UtVBPV^>DiYutM!DPgBJ$etZX&N9n/\7a(-$`pV7_944^+dX^MEc>^&`^_1=E1T
+TsLZY]AItMElf/AfT;g's<Ts!*]ARqVdY1osm;<LtkUu[t!gX+(KgStiDPu06?H.#=SRM)Y_Y
J-,^l5\SK`/9XFMP^OfC7#V6<Ebnnn*iF4^+2Au0&9J!Y,=7i^F9l%59asrL5f/'`TeO^E;6
"H&ltbg#C("[BFqm^hJC^RJ(S6fhDS'#l@pe=q<A@]A%AR1Y`lpY-5KS^X$n7D.*M$>[`lifD
;#U0s^7+Gf'35(Me_hhbk%%s_]AaBoVZ]AK!0*>eD4&2Uk*JoV0sqYuCiM%BWdp5a^sFi.pO_6
oX\2gKs2r-M-5hQ"gD6dF`$:X>W[LDPBb)SZ""b7'3XDiii=WlOT`7KEsUf;@i[[fIl@-kIm
CUQNm0PWqNcQH6EcB_(,WG]AMg&r"9^oI7c\l]AlQ/)T@@r(cV<\6]A[m92X]AViH+GYAC.pMuhX
%tI2JN!2_rb"-&*mf#+O!l5]A&WDrYj;)Ia.3c!m@8[J#VhSZh9(63]AIPMi;kqgW2IkU>,0uC
=)Hhq!KYGaf3$8,0=g8X+RbY)$ULpEQffcnVLK]AI^-At*<JV-30"1/al[nBZt@AaZfs^id[!
PGflC*@IM#^PM\P&&TKlOXi@m,XB+Vh2Ym7Yl=%FBi_M=6ek!N:;5$oOQUubl1Ymr^>:%Y0q
17PYD0"G2r0=2G(M3U#N5E;l4IZe.aFI[FM:`p"JkU_:k\E)]Aua_QBS%LO>b4'N[$`4#ZBI5
VAuZhA%t+0-GMcA6<TKX+]Ap/`%=,WXS@Gmg=N/Gdc$jcq`Qud1&T(di74s7E)L6b"t(J$sU:
(1,j9uft\g=07fa%JE-=:`Xq9P5G&-e3[`nE$]AdC5/:&qmnJuACL^!Vq(sDam=uHa-\Bh0`B
$2UD3(\]A'*t"?6K]AF6='0FEN$Y'/u[<1C_a.6G<54n,AHL#=RgZ/6SD6\32u3Wlq=7spm4AN
"G-@l`sH4Gcf7r-'[0s"hUp\V&&jCLqpcKQkYsM4-0thfY/;%f:;nO(jchoX!3jB0%`K`hPZ
LQj<uC5&,i!g15Y!WFTlf8<WE9)q(*As;Lm9Wp"(pY^kp6Xl#Gp5:A*>CfXZ9GF7&e4>POD;
JKNnFe5t_T;D!sSsn@8j>3#Z(:ARNjH;\9c$G;G-(:uaZ#8g@b6&2:$'lf\Qcd\@/)0nu-M4
?[9T]A581g`]AoYkP-'-.%I^A?)jCuWAe@a,Zl]AA%b!k2JmH^*5P!!Cipu^\?gN!Zq$oos5_D?
eu(CljJiph@WGEW\,QDF(9(DJ'J;5NJ#bBL%N[0JVZ2dAEW$pQ?[Q9AE$S`;!i?_XaR>]A\ps
0\c'ST+BF!Xk^g-/FF,3P-O'o%5U)Ek&kI=A>!Ol6T`I]Aqd0IIf&09o)70'jW(1'O.d]A'5^<
S6cgp\k);\]AVd1LF)6.5g)15jjBSX+a?p0mZCPEZO96XY0-@_q-/$i'pR$<+i1=T)W/TQljU
&a?%4&+\4N4OM-24-hZ!57EI[Qkr!a7c!%B"fC5LlEhN!\VZ!f^GX)QEXl%dnihA\ma)pE'5
Fu=62Ka3+4':AhmMr0&84<kubJIJtAtYDK8&&RbN+?#]A%#99k+>28(Zs3c.LG$lI^CsSC"&`
Y)0JEG[Cmelc$W6):;54%j:Pb[=`fH1m1PdV*r?Rl8-TCmS:Lq3.6n)D+]Aau*S>K@MFdjEhn
[WplXdE2'9>c3iS#I]A1:\K\#*b*;/0p>j:Un^/-NBEj1PAhJ6(:AM]AZ9d?YQnufd'Mh`Gg@Q
&#+^W+m$^lHQ<3Y+F[!$t8C2X--.#/9:%-?g5Y0p.h=TJ3CoR%7Tt%9M0lp+T/1>kS<3JVk_
/RXFAA,sUk+-UlnPYCb1>j_,W2@Npd6O8b-ghoekGh\?-+MX)d2/]A^c:T#gZKr)7#*?f+r6:
JN0p)bHT'pHLF1Yh*'#[mJF7@XRM/DPRP7ha4ln#0I=d7XTo`'cIUN1CPi$ZraXk"^T1!3=-
+:C>4F0SUk;.bU3GQI=g*5aOb$E/%/UJn`i]AcimK0XWKf/^,k#dZq]A>gh0,dsK&`;<lJniXk
&GOEB*5cb@b7d8u.cN#9kMAFXhRA9//;_m"3$eF87Eglk>'r(Zf3Ql=.#roj]A6#!9.!5]A4a[
!><)/H9-5f`lcQ.X:IaA>.R.qY00(Ek!!&XueHl[?tBF3T'"RC!1h`H;O`pRYl3:NF*ki8AJ
K%`!Yo;<`/b0&LPn@03dLC,)34;WSDkKHUPhd/auWrt\!R"'ZS-6LPgW+VZ$elJ'Vf7Y#Hli
f3U$h+nV6\9_FHs3&eR>(mI9#s[T.T1$US?u(;1g..`fRIhTN)o4[ndAjnj;23>]A3,.ZsF1P
Yp<bG=K8',P@:BB,1rg"IPF%=WVe'u^M?l4PjF=;M:fP)5kZ$t_s:=mP'c@F12YN19&'sHZ3
A=Wt;[bJc@.6j>AB,tt"cpaha#LEC<+t3V6o\f%SrM!pm!i-<`/=F\]AQ4ZP174__M8rb=WlE
H>cgY?+2[UYgFT05Is6GU0+nP<7)$'IUF.+ukp4m'_4I^AG;nR(=$[?d!/9B,>jD)uRR-2jt
.Hf&_XqF]A8u37V0"_C&ZFL>/Y\$XPI+Nk[)cZ!uo/[>7O1=1K?M$E5VAT(7jCPk_1_JuV`dB
eY@/M-Bcp9)1RJWXO$k*;@_KbmEl9*m.,a%ESCr!rml%3:hn!n+U@&7p_Pok6b=>S7[F`8f]A
BT[glDuWsi$[e<['?Q3@B>-?<e7e&SN)q!LfYr3K@;opuH)3q,@7iUOE:]AY6'Y\BRumkY\3r
@WC*Ja++6=bHq3-o<7\,9gJC-J*Sba%WR^W0b4^#iMV3?9<'NaGK++$2@p/Da0(c4H6A;(A"
R\)^Q1(GI'=G_J^i'D>47QsAos!0aq)3n."1IO/D%\WHa26B(=,4#<4F;n4N>%!RAio:`=o-
L?SD&\V:/`<!4p.d11C+=7km;g2dPuMPnY4o'H<Yi)/]AG!ro+(ErS-qA_&-)`-]Al\Q2:'';i
u,Z:DasP.C&`"*)p]A1R?iTf",6O=^]Aq,4Z"m7[B%/DB*m&;Kt^4:H\9(.t*mBZQ!qa3W9=r^
_9]A)9hQ+Z^'VYRbmZM;YMLXR,<JAT*/!rpf5a#gp]AsejXknG_AYhonJ2*U6Ss,;$12pSLu0X
fif4HXEg8BY41cjA<sm0g]A?d(/NFMAQ!rLt#EAWt]A`cO=p"&<N,F!2PK[X=2Q\(k+rGa85/P
T_9G.\CBEJ;p`s'fN^rXqs]A^^&Ni@1gP?6Y;SuZ[q]A(I)8Z.$(960qKj+0Ms]A"E>-/\s!1X#
n&KL,5!%n>_YUFko[Hg7fTQ-Ni&Tkrr#18UK"I=9'f=4*1AA<Kla3H_jE59%i\*S7Y[hWV27
oVfkKpCW1-47e]AGS3D:n*qM4oVr+TElql+Q(Y^peVpc[O#mk[rN0kg-:XnY?sNn_Hbg*-UlH
Q<]AOWq#V>t_/nHpIJ>u[IhQrF=2)#Po&pH-Q/iB_Si&VQkp=jnKjnR\i]A5qSIH@<m`+k6@IH
ZKUI\#rHP4OaS4-P()*..`oDZ/^:;\B&&VC4p"bU@/lh@c,CrS_7WoeQF@-nl#+R5LNQsAJ/
6(`T@m^`i]A0Tp`mqsl&)9OP(e\"JnS7IbSN,DL"=bD,0C.U?BNN#]A)mCNX@CaM-a<D$&\+_!
Zd.d:mj]Ac$SD6HW1!$[hAdNK/4$]A`C_<<>if\2Y)6&V)@-k\)X_"2O+SOT25qNu`ksqC>l6_
8J_J#I7R1JFLD_F'T:n;N7(gP[Ri@G]A7)=)E%]A4)]A-K>`8m;p&kL&^5^$LWo*O5Z56c:UaU7
>@`ZMalSUnNUFR@7I4`Mss.nQ=_DQGiF:N3:Vn2<OI2]A#'Ufi2,B4-G,+iE02^YStI-f$C2n
fM4jm"KW/]A=V8.ZoCXif\MR\Jip#45!_4-Gr+o^3W%>CdZtg'22hIu99^,EK!Dlm*76,q(13
Dc$/-I'mD:f;+"n.L*1PYjI*M+T48Fk(kIi>aA7$s@EBOT2<c&fd`H@T-X`(1H$m\hp,7<\d
]AlMjLbj1!Ni?-:&kE5_jgeqF8udC#9W3WP!a=^Msf+h@Xn)$a]Au%e<,f1]A1>,D0c^(L9Cej?
T!Z"JR;TKq6[mYZ@t:>fejur"/up%Kc-rC`E7]A?"Gpt8:;e-T!(b]Ac]A;]A.YO>);u]AOD;dp*X
G3c#dB*oZg2[^%XR<2:Gc68SoU>kr\4?erNh&i4%-D=BsS4EeGmsmSorJMBKjgbTU8iDIMM_
iS+M0b/#;6a3_0.)ErRQ>mMj)=GBsbB7;i_qh,^),`qD1:BoDO.A^1PJ./2P%p&1Oq=X=B-j
6cdEBT<@k5fK$]A+PcU[S+:[*Q%s37HC>lYM,o]AWMAg1TM4+<c2fA!_5YZ$<1H4X6B/B$M`Pm
="A[64.)H3HeCAC[)Ca/_"JEudTl9Bnb^s6,$HB^%SBH<k@W<6+jPATJ+i1WjTpHc7AJt?c&
$Q*b_sau8f7qT\.U&2S(VcF&r+ghhUt(h^d?Rckr%j+SbScn]AJIYNgicnK[ll^P0$%T%&UKR
)"b=ju:%,1+k\B9PDFk&NXqajo7bUS\ua)a7KoTLKW._&h*J:UcQVa!H8_etjIH3eoLB\G)1
/5i$)Gtsb/R**r&E/A62j9.^-9gSQ>ijUC<IO?8GF6;YeZKq9ZRdKY_%\!FfUO;f=!cC+PL_
kPG#h\FQqnM`hi*!ip;f['!5rBsi<Z8aBk5n!r((\p?Q/u9OhZ)Y150#+h;erT8-#`$&B1/\
Dnp)rHm.=W#iP(@&!bJbK:$2VRhd6Z!]A!]AjJI07c^%#&0R5M8,%_7I8sDgYW[clL^FLnf;;M
^KjYr&$i,?Gi&*U_X63p;2b2;k)2`ik\njf]AI8kkr"kZ8Zq%G;#kdX7U:a4Gt<7TkT"4XNnb
%?A\-5,P[,dV%(NSLPP(n?ZXU'5VLY96*[@Gf1=\U-JAEc4^Q1*LEZ.En]A"7Au[b]A,>hNBtc
2HL7\?]A+sP!%2aMot37p2fTkV1p'g=!3Rdc3*Wo`ie[0>SQIA5[XCDVOI+d!dqLN_CGRZGEq
^j?XjIHJ9bulC@W74@!<Mjhm7bjJr-\gb,9'CKRhuQE%9]A.2fHA=5gY&E0'XIV)+WYVu:;JJ
gFEWHQ]AVp^c'F"dG26m?Ffnfri#=M>1pB^aoY(eCjXuNLU.\Z+I7=2j69]AlD;1b@Om[^Hf!a
XtJ@OBGin>>*"R&F9TIOdZ-Yo7d&f*7+f@E"U5qU(Eu*NAR#jJt`K9@!$qfH6G>5'"T$4h^*
+8"/>45324Xl:1j5XY[\Y.On&.n\gdk^$ArV"-1intLHqAm15)p`.\sc1p/SH0l^1hpido"T
0OA-t%7)!`V7?A+`6?UX\@g_Za#d86Ht:mFOP(@MA;.VHmDujFnnl0FR>WW%%D3Yl#\/U$$G
r)Go/qn=mlf4r[F-bOX>.mr_R`UBfK`B\4j%<.^"#$7ejRaEb;'/&1<6/MK>C=UOobEU)'o%
C0ZBsDES'-*^bFJFDPPC_VUP4-$[RNW"gsX<--9J0^p,J\YMVCElD5$+Y-:jH!7+=CG^_=MC
&@/a-'jAIO6&l:`I';S-C<3+$KLu2mi$o4SjQ!@@hfeCGs4!!JU@bKIQ4P3d'G\qD-$>M,p1
l2;6;kLZ'm%7J1g]Af:nDZE._K;K6MS#*ZAY@G+ZsdnOs/bk]Ad*EkXQU(VW&"5kktaV-F.@]A=
.Ws3UGi!4(YtO/D<E.VJQ@"HdMG<o2/qARE&PmOWRqqS$^&*.%H(2LJY'q&T0@0l;G:O`!H[
?M%QOJ@M3F7*r$^WiKQGDe53DdXG8b.rec5'RXI#/'IX%KUV1(<k0R<-4FUR&0j;tB/Nq-np
'*&AE+]AB<P*MpcL<o!&XGlL$9=OF/annlD4"?6E0cq98brIlur$%h:)a+1*CIm5qml2Rg[+a
=V;#_ZLB7$P*c'l"[u\GtY+ZitpN&9*q?<*1Cc5ke!:/fl_eqi\MiEB2qi^"3ioqK5W@^eRl
MV;a1'nHY&00J_$`js%^s)l[:R[0ce7Ks"oTq%N[+JeJ.ke"foa8gj,9Vd6DcM/@h4fmCr?H
jht5'b8RLL.$[6*n;_F]A_H[$1,ls"Q+5+7MCHKFDq5N6ieNA"iUB'*?Di8'0%qR[fLL)la@Q
]AO)&9tUIiFAUZc@;Fp9:L9JI-f5sW*O4Z4LH;G7TG5eg3M2_pZ4MP$:t!CDudmc@JD54F-I*
W;P8.El_@Va6(&c7R73Bd9#Qh-\bV;D*+1H>h5:\]A,,?[+DE]A1CT+NHj=,D*1)4n.j,\/>Mp
bDYW-:i$Dj\1qCM"s:4;mL<*!f4kla,`&)$b+T7@%\bAT3?&gikIEnCE=sRP63q3Z>CZBIT/
,"cY1V*=jjrol_]A4*SWK`E1R/DS><YYX\JkCRe$sG;D8l-dN!D.78:QP0VUJB*Kh5p><QA%c
*9Y(JCqOXOd.RhsWf?;pW9Q*G4:J8M:.*lQdQ`#qjk_e3rWCKr?9HAVcY9'fY(bcC7]AaX!3O
*XRk`FQrCX-SCaBPof]A9hd3.[OlDZ4`DbqS_P1/%00/XBF$-WA3NOV:hPdJ<qE:[h+>DYdp3
3i:2P9Z6I@G-Y1GNe?:0dXn@kQ>YqqHUHblH+DlXI:9=46gfQb/p=\u"`\1ZYg=<:'6_iO2(
(fSfi,HI..I`,9Z%Ga),m&LC4(J1`:`]A2=LH'FC;S@-9lVKFE&b]ALQ5,/1#<_pSq@Z\&H2%^
ZqO4#YiiGd&_pIB)J&)^0K2k%?#\9TK3>p*WA(T.RLW:*=n.0lRF&KHDNfqEWc/8r%tr338m
p=#YVAfM&A'\n8MPOALQ*g=@o?e;d)+$Gg=/mEh6fU49XEs"ol'L16#MDSDE4,+nW]ACWRcb%
`LA6k7?pN=m'0X>b\s^gg(EWqVC'2Inb8s#>@=++]A]AZRC#nJHto!^nu)$<Oi0tQhBo9FIH3Y
\.O(TClIW)S%s0VD4"7u\+htr=Zh5_A1-<`tX,F:(<(,H`_l3QPRn&P+ktGS'/N>*;(Q[<iF
Ek9\([SYuC5ctYb1Q$AT%IjA1&_t;D_KMj,$ihJ6%D4ZPcKnfRF*<K4Gc,4r'I/3s4+GE,Vj
lKj6TX'\+.($oQCoLfCV&(%pkkY-Q=bFf<UfeP5u6CP<@cO4SD59?jscp&jM_,*%aFtc]A)<.
lWCH^d;j&A:h-lOq(pX&+U?`qYO8FD,0bVdqB<gE(Z&"A4PukQOOkYFX>H"K^N')+i#tQ<=q
J)M>q9ECB4:s%s'A@KokU(!Ze\?N+VD2#Nd'?ZdNqK8_81de&+0[lO1HgUJ+T`?&90KS4'=p
^,]Ap[R`4WgRN+gAHScDG#8oLuoTNa,&<7'VmWO?cr>NV1$<_eij/jPGN]A0p"_KBmDO(OeX)6
8?TIa+#ok`%Ym\\_Fj\?Y[d'('Hp78"JW.gfY25W7=r9RL$@T;3gbXEW#W+d8[$1l0:E4"`j
FR`mB7q0=L)SBK`3'N*V3j[8JirebH=rn)![5Mu'MDqAkN4EOKiC5PHN8[l>ZWS<[19E/RhB
ZkqiA_:o`WL6B\ZDO)j`n%FPNH!km"B^GQ=Orh=[7W`]AqX@3b-Qs:N]A_+'n_a\XYjYm"[/fR
u\IK>7iU=jh,a50B(ORT;j74b'?]A-?r^"8T*VF;ad&?RQ/HpQ%+5MCMB'Eq8eAl!fmuKlB(<
/h,!PgFR&/7h]A+KXatYdZHf_UEX=H/!\/%\'L>e[1n?F13?I6H@;.d3h3f%QlB:E_?99.B"T
3jmi3P+$oaR,2T%f29Ghf8!gZ$uFj2c5f(jS]A/k%6uXe';lurb\p25i#N5%gP':?Y^4D8g6N
GhojXU>pn"aLP7<YsPFe[3(UM&<7!>]A]AO"5foa'&hh\#XkQ.e^%XNJO_%/1L5oZ3rG'jBIaR
l;#cP!sUu)[`,]AK^%l+O),_)SWFdAJ#flhW.Z.;IFALjn'M>tU:jq,DBl0YK_8_.]A<=K9i-B
9G@r$?<S.\,C:MV@Pa+)l=l#qF4&Ft-W<P%WjselOh6WJI.'W%L3V,bUh(@hq[J^FV3&1aTL
WZjmqo0&DEWC(V+.R>e-DlP;^%8ES7REIu>oHret9DS^Yq;dr;2H7R#+cCuCBkJRNhfcsUe,
i3j?LC[!e:K[p[*-`F.o(Ru*08rC/\#N_9rJdb?kiknMq9n9ml8fR<CcR_.LeWK>^Yd03S7V
WW86nUWZ/KD&1A531[5,!rFR&0u<dkSO;j?Kl5[Bu`YXVCM'Xb3U"7TgfD,ZpN<S$+%%7O#m
"i%?e55ijRM0?^14`JfETX4NP^u73G:X)8TS`3+ip=t72NqMPll!rOsVY&Zu92VFKn`Wa>#j
'#MMYu]A)$iinTC.@:+(ZXQu;m!K[8qK);kLjQ^&C1G*S(joV\S1W_\s]AO1!f`kJAVo24]AK,_
CdrD<I,o]AN/:QtK&Q]A"WeA$(XK[:!mEWpY%jIR]AaLHViU>>L&beDc@3cb%I19:11!M*qPJ9E
K:l"(o'eE;Xrs>ipiBl(4[/El>I$$1BEKj-hCGr7V3]Aj$SO^Tkj[U8m>P"hr>4u0j[,rrd+^
!K+[46.^`Pf*Gc1/MGD#.,-YPM<UW*Mi!M:q:TLn@DO]A<"$1B^PJ9d#X@C5:74bYCk&OOLB;
R+XL[.BiE1f$4^PXaaSVM_,*,NWfX0hNnM(]AMP##5W$o>_1XA2:_Uh=[PufrM^ZO8^7F8OaC
9#RFR;M5I=H<bqA8gi[ZuK004AS8]AEf8bEXKC8'OISJ[(Sqdcmol-*YE3D0_chWA4>leC*Ks
P,Y;@A-[ru>3"@o'?,Rj<l@tI:R(rr)."Q%KrB'6D)-b`:EVWp.nhd<*fC!P';NmFK%(9:?9
$,=^2ZuUS/u;G<0DM7.A"McYmI^cdIeKDFm9Ehm"Y'ed^]AYf=1iaT+'q"ZOUgHN65!XD-3Xl
o7V@Mm]A[3>FTNjMDH.fZ?%s!T%7S:YT[OTaDBT17u60KN>!.)4/Oakn,q7PRI$bW&RrYZV]A,
?P=5Y?(r4>A+_W<:a]ApnlEsqg,fY@p9m9tcDJ&gSdt\Xb%gbii2G6.#[o^.?^<"%;>!H'U0Z
*r7D7PWuY(bkLSsf0]A7r+pfVBim\,'d='q6A_g38pS&!KkC@*W=*Tc4&i9]A_5t]AO']A=pljI.
(6=H;Snj#R&e(Rtk80fqm[/b*mp,c:G2+&naQTHmW/iWLAL0*<[/D$(N%HHn,P.`]AS[%-kT.
aTp<6U_/o=7aSJ8t?gV#+=e#hC3Gs_IE*>auN\H$\(;$[*XBC8oD;p24pRSY\RTTX.%/jruA
MESWJ/gMs=Zhqe@%&mf[W^(#jXPP\.'taJpQe/ik))L2qZ_o<V?XXNlAO$Zl&e<VXTMA-34i
ZFou>YQD7L51sUiY?1c/Mj5,fXXTs3]ApFtgIW1;H#>)u4H8uor8"cTN_Ve$+mL7<8.<Y$*Kf
M'pnf\TP='IqGCk2O<>WL@f-kt"miN!*K<IF*V<.(H_:QufcQ04#h?Y&gdD^H5k^FG!2A5K^
VcCJcd(UG+]AJ1n^sWXK"rWHM-Q"qBP$7D#Z,+6G^IIfC&BeA'Ha.)^#A%1"@Z^$C8C:/QuRk
)J6[9)$-*Wn75f?pM_>XqiRs-u7M;+?1.%qXXkEqA->@V7Vn"Rd)Hsi_]A?0=Aus:O#M<NB1V
7$7>L4^OOo+L-Enj#0WWr>,?K2f]AUZoB0+m3I?N7f1S%]AAK?L<boPL7Sl/\8t9%mhre_0u"4
VNrl2-ZHSfpV@_^P`Rrc>qUpXa7bpe'"K1q)h"t:<_oA';O)'eZ_g#,0<cKDCA+DMjJ2,7Td
ipRBs>a3R&^cgULgiaDaT!CkjZ4.^efnsRjo@*3lpN@XRRq&,<c%O/=pYb2&Pj"C)=7[K^8&
^fJa!s@KpkGPi$kmo+cuUA.#q-<ZYGhQ1@Wp!S:Sf@c(B.ZBCL9YLOIeW^.]Ao'Q">DD&1h&5
5l34DGm9e@p/N"OBEmX8*@fE9kcN\*!%3s'P>_p<BEJtM19SCYRuG%r]A5rUh6qf`?aeXEjL;
7_,bmJEV7&cakf0"U62kbkkLp(qN/CA&8jXZ/5?QGrF.X0TGj08K,?;j(g#)1B%WA<.3=a_c
7`.A$F:W9\_B6odr@^0qX@aiC&\6CgUZEpScV25Oe+RrjR<iV-"-piHE7FA*(gPZSi=XeY:U
=T.^.'cO<e#"]Am%".V]ASJ+I\k3e99XL/Z0(;a8)WJB*1%'itRbBoWrum2@gU/Xe.J0?NWf%$
#<P+%("h^AnY>Cc&j;;s5eOkr7C_a^A>Ngl00#P*%pud/b*e(tL=#eIG]A.UXKqP:=]AgjC!F%
[P=-@9]AbB;n5!Y$^eZJnAbm?G\18.5*pjd[O'\Wm1sC"%ECBHaa3&mHTHh!j"]AK;+pI_!)%A
IODQ^Gb4sB^[WH\s=d2Aa7T.QsC^rdP7Z>!.npo2-nPmu:6Gir79"fUYX`Nr9,V(aOCCdoZ:
VF[J>Fo+%c:VKIP$"G#9gHPt"mn";@5OfIg-s/lCr3P=)@!;H`9)(lYR[0f-aC!+i+0,2n>q
)>R\_l+83oC&5N,C*C$NAb(DAC+OhTf;n)T)rp[E:es=lAtAU/c$UR*4ftO"'9Qk#n;WK<$a
G:Y/j^?O/<JVOa_og94!%$lKm(4GUR+KA/s4lc"iRqG5,u<f/IG&(Od-r*J!OY`ClpaWAW.M
iZ_EfWNjZeZ&/:<G$1sY^/h7fp9M#"-t_!(5Ip=RA,2k*u3SR!'t+aFRI7jF^>^195F&+3k'
Wf.AA5DRg+EuK@4agSoKQ<6r<i7TS,_0W`DHEWqD#0_HNEO9m*U\;Q[0HUCKI/'GZ"fFcJ!C
Rm^oA$(B4Fjh&S-L6M7EFWMNV3+D@aBFlu#)h3,6F#q/:Hc\t*L/huO^[h\]A/eKrn@%/$PCc
j*SOa:@n7Op-32dAH3Ink.;P?[1uNI_`Yh?P:ja_;u>AbOCJ1s\t#Cp]A.fQNQ;a"`?J=NPsc
s&KNL!fIm#$L%btfaGR\nm7i(bHMX_b[7o@qKe$'S29CST-TcU3>3N3`FblJ*-=.Im9Ksrp[
k>cSpr;#LH*[E#MeDr*OU9P9F$Cc<!0%!Oia1-8*Vj2,Jhu]AHA$4Oi9Adc7@(+LCQgFCk[?+
:5qP<AoMRO8^$>W/9VaVMW$Zt-0$4?06n2.+Z*T%jO@kA^_\'#2_!kCBF!/5pmXQ+A0-Y,tQ
qppV'N$bqkpSg>-boELtb:Y^]AVfP('WC))K?&QkjXNO^-g5]A]AmNE_.j>S5Ya4L'Vl>mPttgn
;`udVXJ24WnocL6E-<fIs+ODDu6Wm.T16(4A=#BhpM/Ch#pq=Q\J(ngU(&_m24p2Ae9>EoWA
KeFA@+,Pe5b)V3_MK?DEg:TI+ih3>-#o1\E9STQJY<g@a#4:7>g;+KUBRC.J7g"b?ZlNjjaP
Z1APX&7*9,eVS$!1C98CNl[e[6Lkf<ht&S+L`2?6*2*R,Xt'$cu8;,aIni2LUK_U(AR8lebs
pqUZmX'Eg+j^P<BX<;kfHs>`cFO%q>(6+D*ehal)i/l:B^<8o\4R2LY!Q<#74r_D[=%Uu7Y1
eBQ^fM5lQi/2FihQb5f:TUVEck]A-sCFc+%WF/rLELRBO3j))qFcr0Ta2B)Hs*k+"=q9r&2)b
Siu#2.F_kD.cb2d([DX1]A*le4C0)0rp%ok>Zk\_^A+)I#Z`$s(JO2?6<oQ2pq9=['3TggMeR
G=\lK=Z?EhKReh4dh+Q&IlcCN9`XXM*_l?.^CD(SFM4SO'LnhOgJu[i5frbBa$uqNkMj:-&/
P0<O]A9>9.QKhICf:22e;E2TcAM6.\lD<F2(()Jg8*Xp(T-eaHS$CADQir]A)%=;^,f&g$S(sV
T'X6`CCV5fTZbSgj:e/7a)e1I;T;+YTZl"</r5+-fB"`_cioq%4o\T-$5$>25+=3>pba.JVk
>!O]A;\f9J3?m>3)[u(*dR(%!g[BuF`$j:V(/?YC*)rbC&E3dJgkGaW(:3,kP]AO<1_j?2e>7J
/3d#a<Yn'a(V+Rj$hi]A2b)3<P.IhB`^$Y:Z7;QNYg14a+LColFfS/NgJ$P5L8%'D?/[d;49%
tT$WNm*?.ME5:66t9j3\(R?Br!A=524mY\X#XF\XgKqC4=;YG:dd&W`MF`E[m(Enkp;p]Aan<
/@Ht$:DB*EuRD4oaEk;Rl_b2I7sUi/_#GU]AK\/Ro4N,hT3jHXJ)U0U)EjP^Z&"G2OFE)o3p^
C@HVR0Y0,VA%$!"LfST`Fgh!oeQ@C]AW_b-`4,lr0p,HQV[I*_fF#e\/E6T3_q?rr.7s\$j)+
ld(sd#&.P+`D]A578?o>)ALRes>DqnHC507;JVfTOPi34f0C/mFdUSF1!MqF++3iQ*R7Ng29j
^+DM*R=JWFb@*4@X3,hn-S)U_P,ZBNQ"5gs9W*>nJgt\7VbL;<Q/Y7$KInP/C5H91!)pBBO9
()##-(\7IFpH]Afg!YPS8A0QnL%Xo&M4DP_D5gQV0"DnMqHo(r7;G`R$4`O#6>]AqJR)AQu&oE
g]A6J=;g(Qd8OIHlCuUZkZ]A;%M7M0laQM/O"51sqW'a@D=OAGm\!@QkB;fMSjZt$5]ARFQ$/TT
u39DUk?#:)r@p1#B4%mHbD+aDSsmr\o_Y\Y+^[;3]AU.-SJG8XHR6DLHlInsWBuDbf,1\nFlT
\aoHH?MOoE7R<\pZZ8l6AKqsTd7!1`lH%4@[T!4!M1U,6S]A5:@EMif.rMHeMIY+K_[0]A(L4'
*EE'2A8n4W3R]A?Y^offN4p0i_EQ[r4R"5i!5DYo=hOG$6UKR%:uF=#(oi<rq-Pq;(AX"P+9K
oP$N6qD$86nDH`:M)Rb\S*Y0XeP';+#;3t.<o7?:)<LX1"l7Fd;A^-9n#@O8jS;0@]APWL4Va
5]A0kDKb-\<p"K@^V=>[?l%p3WCi(5GYcm6U.HE6U+lG(=$dHH.#r`87auR$AKW!RTF$iTj>>
\CnW+as%mlP"kbVVoFur$7a`N,O&k&Ne[e]AWnLk5\*EDYDdf;^78pm0*&#eQL1.jFDd]A7f^i
/P`<[OK37qRF\BAJCuK]A[lkXsHO8tKXF!<j!^Rp>Sf\I*;g[VG8]A5ln5sCR;6=.5:e`d"76?
5qmmrJ$@5q7XM0cL1WXUPjH7m0jEZ)*5O6A:\WjA&R267/8a.=USm*B-F&i4TqX[=^1\V.Mh
W.EeH.f:m?iNX;8qOYY5:X\FeiAg'[oiFGi=KN\FBXN!W^)ob`Lr'FH&gsNSYe.17Jn"[iV+
k_i[[)FpG5u3X.;hH!`"7g6tG'(;rf6FDH?"kA8T"^cK5tE6HPr*/SW969ARM_S7m$<0)2-_
AZRq"!q5#U=!ZM>HKamJ<rGsq'N0Na\,4\OpLf9u8YfdIigX[*Gj(/UQD%G_q72Y/e'HN.HP
@Il3RI@P]A628]A=W8jea\deNOQUXYGq[=G:hTbf]ADG_KrO'@n2S1I(0DptCLAU<`%&#cnt:Qb
$:TN@@)mZe?Cpk.Q!?'jXNLqN?:gE990LVl:QV`sfo#G3ms2S3C1ZL(I59XH'V,BQuFCLAVW
4r+N*X;JC(WnR-UTI6*AelNTA?gs@dA__.P]AO)!/]A?Z'`3>2P@TI"n<Q%SUHKK03$T>]A?UJF
\n?cCiCCdSaU'?hd9p[h2B+t_O!Z>.[N)XG9:&gSBtL'Dl+?UW:d7;ABb'04*]AqdibHb.qm%
pKi?VP.R**3-k0WP,PPSRblAE?m<DB%?]AYf&]AI^NB[":Z)W9hQ@]Ar85Hk5'?(<MH^Fi80OG7
Z`.j.6T9nRTi?lBlN0"h`45r'b.$j,$!$Cm8+HAqZ$8HKPt8mQE0n_a9aL$Sm,3lhVq8%*\+
?p844>Vnhq:I#jn0/D)KH@ss7f>4DpDPD0!&/Q(KB-L+Fohi/$$QWkbVqhD@%=4))U]A*')aS
qp@n)b+SSr1=\aRb$;&^>4J3@JA(f*Zj]A=0"r2i.[3`"fch4-6'J'0+Ds*e\.`C_X4FNk1lF
E$O`H>gH-W0YC$&q[eC_J`U,p.a78D[<-7f@n^J>a#I7W2D2'VRBsV?YE.kNtY,)WFeO=eP"
X3lK4V#^7j[P<SX41"1@7CgSAgdc]AI=,Y.qC72E+ZZ*d&l1Ucp_aP-2KIC]A+A!#T.C,qq'lP
]Al]A:omE/ZR=7l5W_JP/p5dDKo08q<X^e%.G+(rYTbDRn(n64L0[$_koLloi3=icm,k5n@'Hp
Ba5`3eGJMeAeWr^iqCia!?CB@0tIpfk"JBoNYO1@'3Y0/Vl-^+QC"DkEoV_h@.DkL\0VHbN[
d-XtK+@@n'Hl!uifq7+NDA=?:Wc`R32r_C-N:.=otR\[FWdr@$+@'KF(8msYGiK9sT?_,2M*
sB)7CE#Gf$+:s2ksMTPs"">G)lg(P4`]AA'E\GR_D;b`'4.@gmDY_#=gg^.Rm@.4n#_jE(cP0
F,dqR'%dNaeUY<*i8B7//9R5KDV07`\9Me\E:q+`Jo1JqOaBJhpb[0#1=`RP&J7l>m*>UA*(
7)AZa+,*jCP]A"tr$X$VN#SaZZ[%q;nc/u);WYACh.jYJp!Fk44r3ub<./msk_pjdXQAnO#b^
-pKf;hR&CrkT2oIC@eat<<t$.dXQ8p'@\gucLboA9#P85>,GF"*NE#ld7SZ@ddVpuJV)r(u1
oOude_YQ\9-0D[IF#M029.FD52aoY/\.BN#DPL%'fN[:m+e20@.;sYJsLbn-bi(Mtl5LAIj,
RZ>;(A<qC1"X+$NE<">dmahM4+=nWK1'`*D[)KkD>C4!,J*2UjmVQ,gBLXW!+H.bo>[o,"Wk
Y13r%Kib+n9;<B=el,8>E1,^g(_H']A)R66F7m-@Y)1[B8#\=)>9K41jH,5kU.@#&cV.U'/&/
79`h-S0##e-a#ICp)j%YoCj2lEK4PiX)BA&MmqJ>,,'Vu\SaX92M1q`T_:p2UEZ"q;KB5q`C
W1dZWf$WTT<QS\&kp'RsOo(cs'dB;`X^%ViB8F<1Q>b2u2,#TJ3nBnNBs5!.EJSQOu1s]A`cN
bnbr2lPf-jANF.uQX\F+f&RhFDWQ2B3"5MB`p&3/]A/?^Gup9Q<VG]A$?&^.HDic+j/LD--GsY
M_EHTqf6sm%J`l;oa6O1XK&UPMG/<=(^I7,F%@G:`(_aom8e]AI_QM#J.]ApDnYUQ-j=2%cgg/
E#i3HGoY(@$4+ANT04Ud6CK$M`YSP@u*5NW[7O_C\_%k<qBZKDM8UXuVVE=V0-7E.?h<u2M?
=$DQJjplbb2P^a"QA&G$5l%9aqXQL,99ot+iQdN23_eCS]Aod;AYT<JhSUA_8R?Dse_@A?uY5
`T(TM5HkF_=U'5HX:jnE@4D*;DkbIXK]AB,&U0*MU+&VJ%,`si9oefZ:=\IS3,cjf,h).MgS[
B'k4<]A0X<%bjN<o!H<I#@R]A^G+l,`t[<-mhH8.pUpClI#Jh]A`@.M!qBb?\+eY<U?gc6ssN(f
g,/&OZtVEkX-UuJ3Z5s,'[LLd<)S's5Lc:\$(A^KTRUK?l"46FTK7<l,&sS$4u8J+s0MW$?%
GE6F9BFmP>tjs+.V%<JgV-`-tI'LcHOVfH^,=X#'S0!q^lf4`BY1s#ptIXC!Afd=T1sXX)=P
C-Rr^XeCdsi<V`WB(=V\c&ORg&%aL`fOHo;3QG)\&aoRVNB09IE5>hbff;<C_:mOf:;boQag
DhH$Ftm0\39e_s.gfX),Hr0l#@-tB12(WX/0h^"Pp]AR+Nls=4CO!W#f9iQ3Tb&F1_ti[+b$H
F3QOfcifSXrOE`/%glHZkl.hF3QeBkg6m*"KmNMM@cVubVZ)miUZoITLcgbh)X6#opWH*G3<
<(-<E[GA<.H\Z!D9S5RI_)1WS^L-M'2Fh'?GEiqKZO_8[AUWX,K`G(hd9qP:^*]A5gR(se45G
r:[KQH$5OhJs5tAM!/QQF3Lo%7CP?k+&TiHL4i<J^V1sdRs3nH9T`VZiFZaf8(Z/Oj%8lYY"
q`<>'ktfruWO&b,mQYl``Y9bOprJ>[:jV<=na7915pjrX3j"W)]AW\!hX^ajb(^d\4p!jX_UB
t)4nUR[c\Z;O*.4An?^.<]A'40\Jl6@EcR'N(tN/YgE&XX[QG[u/AlFaP72U?`R)7%it)e@b\
WE@g>Xg=#Md?U?j)/#t+;W\^]A_37%H45/qtGB45$&'EhC?K!O.J*iVH/%EOTjr[ZuX(Z#;=U
%LB"La,prG3+&5L&74q;>D'/V*o.%K:@b78uJHY&$jQR'W=1*#)[8qY!4OCi^lmTHiH-?Z;=
RI1%4=Z%aMeq'Nio5'YVA<0[GFGFS\7o$r,(Gd]AI_(-[1fGOPcliiQEVes5iBe@`dmqgKCt3
j'KVLq\1,@Hc&<Jg,>iHOBL.QiO*"t<W+fhQK8M.A+r@;0+&_;Ta$0C$hg_d[!YjpW^a^!j\
TXAlI8%PcK2`+?N9r.EulNPfbgRF2S.pG6,`'SSfP/gM5XA-Mtlle\]AS!TgfMmEo&"OS,6uJ
[Qe^R%=GjK^khLG;HDaC%M;l(jn\:.W,o,$f=5T)0mR`#-Z$*gt>X[VK#CH2.(Z^;UWAFObm
*'ce(Bf5Bo]AF0^>J]A?DWA!G,pUOAt9FME?V\AOp2Jnj_eYYmI)jGZ<_)'S\;0-3*s%^!T;+l
!D9..B)A+jZf'Fjh@eWa+<VKC*cqf#eBX`@`E:C6%;mNWHYa.7d?*t]AdL3L*-C7sE73NseB,
%T%)e/1/-tEUn3#R3pG.jJnfb/iRhB^$gKb9*mOb&'G@]A"s.a1=[=p&WG\%th/1Rt?0%9$X6
q0KM.&OZV8Y4+W:tGT\kF[S`[M?B_nG75B"kV]A]A1f[hhFN(ASlILGDSK]A]ACtc,dAF1n0="L=
&9322=Q3F=DU2[WERQm%G(**:t/J=JJRo63_j`DXXE7:]AkkXOOIX`&9\R6=kL(gcr,cUM3'M
Alb_7%e[Z1$`BX<Ws"/+Q%u^NGbt>XIHd'+&c@fqQOl.chD8)<ku^,=I3AW!Ckar$DsR-rY9
HY*FGcbpaUbtYmJl]A,f8.Y]AJSD5.U<B\H[I8FTFpc0/E?/h-)5A<A7,Q-aGXeILoX,GA7B[.
rC9I9q;R+C;qQ+7Vdu<@8inp#C!5'D4pWEUFM>q#*%;:o]ASOZP?cWq7S[3ZmDO]Ag4R[OKb/#
lD"i)`QPHAX[P<H-,V:jYe`^B;!;d"&g8UJ_^os"e)P-PFuA.l;^8(cEB4$M-WT;`/:unBH<
VT(L2-H?&A^4&VPrYOI()hQ&qF#U8dI?Du!PZ^gkL6@]AWd*2G3&c@=0`,O*I?=BVs>10+B6h
fN9?iEd)$YS9?0CB(pfE]AZ$96Rah>b=GHJh^:N(&3fYAdL7s>d!L'Bj8/tgbJFLe;BG'dC)W
JjChG$K4V@,fYnHVn(*E=8S9BkA"<.(jo>#Z<[t[o"RsocYmY4Q/qN!=3kf'AYJoZZYc<S0M
mj_?=dh@p*a+4lhZLfVoL,CG!=e=oE/ch9OIeC&<U"(F$Ek8Q-n?Zoh)cr=,:LMW:#1@9'Dg
pFQ[9,pYZJHRopiVW/-%8"W-dhFMI*hASc4F(FPFI$Q^D%Z,%j%9(jsd,@SM7n7F<\kK.2W-
Q_bg#<`*>_A=4bW"KJ.75im4WK)kB`F;7LO@0R$=%oQ)!(\PMaECXI8(b'lo&W>%_p,uY@.>
T,`Qj-5eU7m@2o,mYj^Bc_5F71uCp<JAB(=tk`)NiBQK]AgBisF*l*qhG$ks4K3c9:QV,$!J[
LOZpU'pK^sZ%Of(tg\1-ZX&\4`l\BZSHbMjBc)@V>Upu)%*78Ue]A<GA"8e9<lf2IU`&"A^Ib
E,JOuo^Qi#[h2*l@48+D07;??4ACB3JQ@ba'ec1Tc&i(<#1Zg<=`]A'V_c\1"*Z!^GDY1[^M:
#]A"J&b"Ed:;@3q#+Dap;G#Wm'e_'2B,*RS]AF&A@@e/".oI<;]AHbp.+;7AE-0Mi-f5F3$>gcg
7C#XJ#%0BUrWLLe5^F4F=g@6<J>.nfVdXqU;88Y-^'#OV'5;]A]A(7@SZYf9B-&E`cDFp`sVun
m*@ZWH3IpGs`ffghD.54-Q.*(:oN6A,Ic_ElrKRMNV]Af5r9V$9WbC>p":H=X]Ae>E8=Omu^GR
08mV::U`8;p'<ir:E@>X]AZ[NN^95tTr3>:u^jU7G0'cIC"/R6YM7-->EBF36]AoJdr=q`]AHkl
o)Te!C4/jD:K/_=#%;"0^2X?p^(8A`daMHhP_4rbG!)*GldQ$g8,$H^5/gaT@iLdaTCnXiAt
EPW3<Spo-5Y6h\g-c"<q&nM*eDY.W)<s;[mD%4HELG?-MY<qj'IsG?O_m&JOoVr]AW[]A"dANb
jdJ%^SN2j(DoHNR[;(?VXBcY&1W_MpBU$]AkLV\X`S^lT,E,iSO=A*lg!.o1Ff8C1AJr5J.K^
N!rhgXN;S>d5]A+BRIjan?+62QCtM4]A[;8&CJoB#eiLhpT\]AI8j%3B/g"XJ-6-Ij(L&HAYZ_9
$d*W*Q:i=tQnq\]AA+B4^0pMaj#*kL)f0+Fofr&IW\4fOc"+Db2(hJ+7>;-(8*_)=+r9;*$U?
jesWgpNQ,)<RGumiJ6tMV%;=(NOL/0G96TsD$fuAOmM?)O!V4:1bFlt"Hoci)chkkPk]AFkgZ
8<e8)2([R<sq8:!Lp@]AB%e4,U9QE5f23p"27jCH=4IfWlDgBdTJ9jA3^&=XYd[;8d*Z_O$cX
\AVNKt&:)4YnniKRK0D4t<k`cnn&(KGO$OZ1`96GN6f'umG+Vlus#mq@qE]AFr$).l^eE56io
mRq6lFi"b!s=r(XA:BeoVL`A;QjY7[Ru=I`&dGPTIIQ$rp/7bSK2;f'CsY5D998Mk6'?q@l1
=q]ANgRtHu=@nRXb81NMhU2@m"-A\1W7MFp'ZGNcMd,>HYS3IIh+B,22>o$=p)-=udB3KCll*
*UjDEnqN'tcIc`I:l!1/',Y[tWa>MlWs6Ut8[=D<W"iNj]AX`8oH2euL:9uNU6#"0#qg9dr(1
_mSKPSI``A0G[0@t+Z?c!F,Ah5N(XcQQWf9Jh1;8"b%:NB:YCC.$+:H^'.^KT!cIJt<0hSkW
As,S5!'"N[QjA,.NOiY44mZIB(f+;@-\Le63/_;UqRnN19@?d27/=03$Q^Nel`8'M'R*0<[l
gt"%^OO&EoC74`c8dP]A(Zu\/H(9CMZB',NlgdZ]AIN1%,J\DP9!jm)mFSLWShYRM#]Aq/[^*j\
_IS#J;[,qKMt,WF$U@+L?'agK]Ad=(Y:A"1PDdacp2#l`-3-Ke<Tb56#0iH;hGEe7`t@m3>H6
^[W?Wd+q&a7H2ko!2Id0a,L2.^T`j,%Se$mpr[`>oXKT9;.4rp;A.F!rL-ubN/ZK&og"SSr/
8#`m6)C8KA*[^^`u>"[gr&O[RBCZMLF$kjTPr+*30i\kduDZV6BVfh,K%NKiG0==V=4e+(sX
Jr2OE"2)WqE2\B$@P_@S[AhG)JFlI<A_'5E2M8Qica$"3G^_Q]AOX4<ukMS!_4l4E9f-3+&Mo
3#-NG'H*Kh-j8IBX+d&ZW!?jY;qAP(BAE$6eE-(.HM)D$8oC,q<#7:kVdc(4T_Mh3%fF=:0G
.#_[ps_AU#\&ag;HH"R:')6g3-ra;--NO,!A(c\%8Sm,II-qou$^?B",'$[$[ZP\Rb=9+Ck`
:U,I$O;3>LU\V5SVJ'DgID1^u@PhU&)9g(c)XVZ<O*8FMeL..r/\:D\olt@%#8(]APO#AaK5R
V*Ld%StG!aH/D\0J9p3#[MK<kW,b^P]AuN@fVen?>W6gRp<r%>f9fPo<pAPnVu]A.>!&=?leRe
jms2AE_p&Ihl$a4lDM33RjX)Z(=V\6i5]A/LYTuCW`+,`,UNu;\NFU>QHQn\>uO(PX&X*C=XX
e'!k+2@bf?R4rd&(=q.ZB1`O.rPVrJI`AR4I\kVbt9?SEp-K<VX*OLm@G>fdu5fXl\_a'<U-
&SdJ+&Q"*AU88S&A)4B;l4s*l,^K[WT@>cee#:)=l9d_FthS$a^VI!D\dT]Ap0mi*(?((G%]A!
+&_-EVmg*EMCT@"Jmsab^p%T#lh!etq6<*Q)-8VBFT;:?[^WBYouW+JN?X&.b9;JYYUj<IE.
.C\Ue+A",duC%eMC_g5H%EQQ_rrq=BgA<GrOCOM>E7!/lJGEU$p+aD2nKZKcVrIVSg(\Q5uW
&/&)XT)`iRZ^5!H@g/j0H</*U6BG4BO@P!*?Y^djFn4HY'@>:8Hn4u6shSQXA$-J0cW:aMug
:\=F;A0t8IVIUMYlTt*8["8lIcQno9Pe#39cmQA"=5emj"KpmR9Y<F8s,E+2,097)+_]AAO&P
:2iY>,Y<Gj8JK?aD8RNK?eI9$pGa;H@AoXGZ'SH=3_P?Eq\.X,m)=usf)_n!:eE"4X1fh>o0
G<g)]Ak+`T'04iR(Vme?SV<F)PIh86NDD#LE'jt%=qSR7keb\O!jWp,3:]A<nf1ge&O(E<mVZ`
,[GabnH5ZeMHM7UGQ2Z(pp1UJ*=o`^O<R\[FOF$1:'K"JN0tX%pT+]A2fu6BPBn>d0Zop)gBe
\adHL=)&ZS1&?),$rEI1Y75qBX/3p,i%b!WN-%m2?0R"bfg2g+H+LC%3:p512"LZh7lfiptm
9C*c5A1T<L<+>j*i_kO[';Ws1:no9L]A6&ec[)!s=r#0n<\)i;<_q6Y]A<9Rpei^F)?eKh'l_2
Vd\3p?oB*bN'ANoEkFMEaZ1SBK^&'Y<`^@:IR9o5tX_jT2.-&WL.C3+Hpdho_f$T!4^cKQIh
?DgFBI.=i7NO_^1m8_qrbnn!rYFAi&lKS)(2n@[53,g`uY[5"P68+q)Zbr'rS,8#\pN7:_gH
FR\!Rta6Ei`j^MQ)IV6;7tJSGk#`LORr]A>p-;1%H@X/Q(Go!o!UYdX^>7:IJ+t?"a<h!))u;
UOZr+3m<X+eJG=%e5K08$fLEOc^.CR:%@6unbt@5u(7DpS%=VI4%h!K7[f/IPb4f*%Z[A\(E
Lq!T>D9=tX"aZG;q(:Ip`/4L*4-%(lBRcnHM=s+XYT+5*BM0PEU@5`d5c(38A7^JpT\uJkhF
Ru#di#jZI=PZ&^sBq%U&SCa.LNup7Es9r/PI4_8mBf(pUlnotqYYWdfT!n1ceP^^SQA&)V0W
gj`LMlWRW):b74+.Cbb:(poY'(ft,B]A&;$SlNi:1Q83:fSsSJ8P\.h(k)gqVHGDFT!::jHe^
RI%CU)b4<5N$6Yh/qSpGtt3M#4N]A_Z-:'jMf*-5:=\B=l"m._J@]AkNe0W#,5AP;G++t'H6!R
$'(U(F<qALN>'=5jnu/n!5Pq\3ZY%c@lm'-_T!2H!M7Y4$C81BIM9SY[:E_:i?pD-5of"1ab
hVMR/5nM^(N;]A$\G)`Q-B78I%jXK;RD$f&VXHosj(RQAn"SfZK%Dl0l1KXDLsoONje"ab)Bu
8MJANfpao>3rgYQGd+c#erVXi..;GCppHRNKF]AlHp`1`3.i<$25:i9<V"eD&\[>SJ,dqgb<=
;pWCB=[AI[>(RKnDDFM2cMjSboq<JR!@MN0(,joqZO(j(H-]AuhrFi*s>NjboV%36(^o:O[$Z
KeH5\qQuMRoeYFQh:GIbc`jIWmH,Zc<2sjO(7[mng\HmJ"Xkq8Z@M'7lTIV&2k4/Zc!>bGg3
PW9;&%;/&TXcmNRB8aC!"SJTuJa,TicasDLoZjLot:,Ek_NM>5e=>-7BX9;dN?A3UX@(<"O=
nEhC4.Ak.oK$3C67T_tJ[+nQMr60T0_S+1[@8_TX_--Cm]AF(aA([U;=$jGs5d":'6GKkDX!.
[egW4`DZU%B%Iek-Y@kpB\5No[<\YOZj8Z/g!jT7"Sbj0hOi<7dU!"I=RNYdk^;epRTWq>*/
["ah$H0\T%;,7JQ7@b=YS>q-k$4H,6<dl2^'_uF3.Fq[b>1`PQ*6l\mSMd5W(]A;SPaN_-SXS
p`RH(Z^ddn>-rFl"]Ar4Wear2Pbe8(7T"UP2C8a^IbH4dIg-Dk4e/9+5GfkSWMI%TF"V^f*3-
:`,W>-M?\CEp5ii^%"q>annZGEFfCA%8qf6ST!UJa.Jt;@:4<sR:%)m3'DJs]A6k'coTj3o@Q
E0ISD\08!mY74Em#<KUNG*a4EU,ds!+]AuM*+AFM%Y&_Xh@@$T3:tV9IQ?T+WgjeWM>$hYCNJ
6eCU]A<$%SdMY[!<+[%h(rh9,0D;MgIfE:#hs'm$V/kM+Q$k(6#`]Afg45o<rID/`nCB^M05.1
A$\sh=PF`aIi:f:'c6Ts<p/Xu)gXQBO/C-tSl[qs",,Cn1e#4N-Qi7H'I"6>G5A^"*<Bnpd6
FM(fTi"p]A-,^0/r5Dn"-4=AKKe>DIO1rn!RTU`NM56N34A@tUh5@f4G-:k/F#3_k*@8q5;'#
==KP2EdoS9@Di+9i/UmXp3*'2^h[=JA)\)4`+ai)D9uR5p@S)/2CU^QCY^:3Dp#\c"-U*ga5
P2n=M2WbqT3JT53j@\O?ZNR?RSplCU*.-i_q`(`FAr#n<0pb#mgC>m%RR1b`fD<.1u[lW<8I
a<HnDr#5b]AF($.X\sMCa=Ke5KQaC4>Q$f*8b1+Ga8CWj-['\''PU=/DM0/`-6L[BfhsD`'c2
&mUJR61$_S!Duf?GhltKHEr=R&XMk0oTPhMrEno&=$sekEB-T^*bFYrgk_C.rQGl#7C5'ScG
f(hZR:49&<*HQUZqKS0'nq$lTaQA6J4*1_Wap:Er?9Rl<alWhj+so<QA6/j6U+.'@P:mIB\l
UT7raI]A^5fHhm?58-T.p=o`BGNI2RD7"I\CXD@hMbac;\>lCa2U*.jIn-)giFh92FEcN2M>A
s=#.B05c'&sHKgOjIYo2\]AEoJU(=s1K;@>?r*Ph4*LX%%J_;3n<`M=A,FV$qW?E\:NoD*GT=
18_oMW`X\m?f?0VNW6dt9EeO-bq[8sQ-M7h]AdgY$,`4Zt5QpG^7&d/F?aGWa9T;[gUa>+heB
9CuAp,5OIh"d(0G2*;n_>*Lp3*59#rr?PZ[qZELUpr#S)Re>G#F_bJc-5uhaa\qns9WdX?iK
H,s/a;90m3>[+Aij>_du9]Ab$d!j7>%KS1&)q&&pR6uhYIUB.mE14u7;Xl)I"]A)pkAH]AGkCBb
LpE'd#s5opOn)jqYMqf/`i]A!Eql\%'$0+\/_Q/ZcJqMm_7rKD#^Q*Kl!/(BMd#97`,_lMrT1
&OA5.#9J*Ugs*JK21m8O$>^6W'Z<SSg5O5h!mi-:.4gaa%SoFh(RKZ:2()HLb7=Be1;#/'>E
A($IuSqRE!h>nBB%(ID7E^afdbAR57PXo!pbEq7M_E5di1L/R8=tT6_fub_H05Br0]Ado5.'t
>EmZ]A.>F)MURWd`nN)H+K;j?1R#u`p-D?SEKR.bJU1(#?8$WCC!F(sdb/Q>T9-QO`o!H<`[-
P=a)@Yd<R46PC&ir+dP(]AEo*N`%9V0.NFAL"4hI*]A$@E"q!HO)L_e-L1/BR9(f?aCm'0s,&j
rU^]Ag9Y=6l!UQ8GDn=#Ws:hlTLQI5__AUBBZSI+)i@5#o"T!>:O\jF.@GTn[K<:r^Y2tiAFi
[EL%FX<qE+lKm1+k+Ja-^K(c&kUjAH.?H1d*AXZg-*m`VSrAN\2p9(%59ZIQDQU3f;!>TN%1
A4roY"$>0n*7db&5SH"OdfIn+*V20M@NU*TSmjnaP:,7?-4kPNe!;jdlVq?CChh$kPH"jrD^
G!6+1#mideX*:1a1cj*eatoCHB=W5%2->niYkica(,F(*DQBRTWcP$^/"@8)_^?'LB-9_'c&
Z-H!kL@>9ikX&3+EO.3l'(9^K]A8Amk&^T[ol\irES2:]A7&36[`j>qn?Z4L:_Vt,81;,^USh$
9$pT\Z2F?1PFbq_6#CJ:\H3-C#\n7jmX#&\g55SW4M!CTtQ\*Z.am.5%L26GT]Ar5elNLd#9d
STB`fZ";PfJIo[$-_?b:M^eJ^/HL/_m"Ij#iYgtTS@scTE)LP)58GF#b)`:.6]ACkDr0Tkq+,
EuXE$4oNM^@g>YNOSLY)2_!78Kjj'"nG*,rT>82i#T^jO2.s%3rGQc%L]A7lmVl;E_`=n0UN;
=uToR<-(7*-\F[)fbtIMdQrm!C0MN\"Je&5]AM@aIVDfVsMdJs;LfWGb\,G\YR:j5I1]A5JWE?
ER,gog3A(q<*`nlaP_5JbWl>i^O;DPrs!ff':]AN)eijWJb16JogN9;VbC*.TdX&`L]A7gHsN7
=7N7&C0Gp%TMnQPNj3tJHcI*.V8Iee1ONS$&H8kE'$G9MX,=ELr:9V^SPXKnU`1>/cDmoUE9
RRXp9C`jleaM1M[rc(Or\.G4W!!%f:'"!KHVj]A1Qd-*=1QfOO9jD=1c5U4i1sMWHk5H+Z)Ce
@e#W5Zk:oJXK$2*uYjDeW:)SV3qrXYV(l=f4342tJmpc#EZa3X%1*b6h%?g0d/:LBQK)U2_.
M=kC0kjQ0lrbbY:ke&j/ou172[KF4$okr:m"R`m=j5s1\Zbk!4d3gS):;I'&Cq_71a<ocm*M
^?bDJ0KaEt:GChYf4AdET7i"h!<P6c$129;g^-.gBhE?!1*W$R#l!l'C'<=L40c!bU3NCs1h
M.4Yb-Oi\bT4-ur3*0;KMFb\[ahT)aN@[cQYL0`J7#IRlX"O-Aq'H(4-lKfpTDS,AEns]A.?!
(;/OU!3O&VBB(R?`CMCf40FNW>;Q)IX'Dt=\P_/O=?J,;#ou_[2A;RC/S@3!VY\!"ecOVf)$
@Xs.Q(W+KA:fUpaW.d,V@H^m1ZTmr8?]AIk;c_$sO$rLkpRnhL-foJicp-1!;td`.^]A$IBfcI
\eoFu6omB^M/6,#;s3ETSQcJ:/_&cRN([)FWs>th\Zr3UR?sZ`-]AE*FJ-0;aho+GGdqu_'?Y
)m^^9\N:%@7(:4>G8snU2]AM&Ctf!S_P+>]A#qW`A'.BmhVf2#?W;BUY,oGSYtq7=bt40LSh*e
$@Gbu[%E.ctZlgg)E6m:;'n6PeZc_)EO-3Nudr1J%g9VgD5[:/8(cZ$A"38`"B"*j"YjLMu>
(pr@aE7N:A0NBukPV<?G,DeW"[nQ9YCcb_3s#4jKC,c304e'$Chg1ZEf"oF^c%0k2X^mp^p`
T272m,JE:k1.;sAmnYtidIZ?5+0IZ,R=aOL=1eY*g=6(5K57H]A?=NB10e>\nJAc<rLU%s@#L
#^<s;KPaHWplk)-RL^1^G!)9NqBKd7e)^3i*/;%L]Ac52AL.6ou7u*R27-G%_-`4,YBE'KT0)
8DKo9?7@\NlLs^K8"OEo@uX^3:nLqAFT=.ec;c.VIXu0,"2#496C&oK;:[QNbW-7r]AYe^p*U
WT1rI-r9jQ.c@Cmmh>\1VZtoodC<FAs;UM&("fSt!rk>n<(7SFEhscQq5/84dRFGuIORR_g&
>q8LhE'dh0R$7O>r3l-9c`laH-7#G4o2Ore'7RI#KWHY/`4B;SCr,ZH666#S*-QGIm]Aj^FX:
^d9h2a5F&tPV=j,HH"C9+6nC>i1[WrAVDrR_1eNk9[V^b^O4M7[SiS0=Sk]A!S@'/,'Xqm+KM
J(Y*jFH#9%"Ha07e\<DK7aj8U:3m0`[(cWL(3OUUNlg;U3bH,!D\9O9+ntk+lVb]AE2VqZhi*
.m"cA9l5Og>?sa(?%EDk`m6/uBb(i6<OdWM9Yn>#4XC=o<cc[nFdBimLut^oIC)asQ:h(5E:
bjA>OYAkn4rh)XC+V:TD=!?@&ti,GHHQhXj09_f[4^^V:aQSh=^V`8sUSq=*O[llB\KX=Y^!
sVFT.S3&oS;*:VqI5`ghLT:TW/LZoQY;4,:W)$_66>HB9q$EJ6#G',rKT1HeSgcTr3,,)@M.
Al%u0fKe9Kq?Fm#J(hOJpg%2^[!E,)k0Zl)I0(%l]Ae47cfDi$9"RR>@C/)D$%Ao"qkZ*6,3$
4eFrb]A&QP6=EF.WBCI<)69gT`S,*McqfXlW^X"]A,hcmGP,E+K%l":hT^0\3rn<f'O]A=lgEeJ
$-$\1.i[i4QWDc01FK8_$&-p+YV?PNm(t(b(9!cpOsrORtUc9"/mIMqOV)+$\+kO_V/t&X'a
kgS.fb(H2Ld#6dQCrmGCm7t_?'N0S1C?1L0]Ao0EuXKAJ%sdV*!p7[`G9ZP:$5TQX42[Ej9Wo
mFX+9%k(T;<\rSe9)7Cn2C2EXn7etIL2SA(r<>Y<1]A,Xr3=8BF\]AG"iN`l?U7aI?XHG,UIta
EKo5ZjQ-a&;=C/AuB:>Ik2_q-(ZU`)\1(!hl1rn$NM<=hI?.)O^aC.+\;MU50)K;4]Agfq`oY
P"5/o`KG0jf=RPH>iUq?4^",[$Gu`=UR_R_f"2-2h?ajMT\c(EBk((1iMLd1N"tt`3NLGm>h
s8no%!rrmM#7N\8"#/-$"+/8_]A<%^"ar[!eZ4P?Uaj_;HAT$,59_MKWr[ne=,5Lm?f0X1r8<
J1Le,1?f(k&P38B]AU+b13WTooIYi+88U0=pB$SpCqc^E=HX/!kL.Lok.A7]A]AP$m>E$"9(Yq=
Rk:gH$L<Rc/]A_j5'^\=%*T2p#ka(Mm(^a\F2On[4*'(EU,'gVb\<W:qm4mk7!1.H#SOWZ>WO
s:aGOQMdb6^R_Iq>'f.(2]A7i4)2SUoUG[<4b_WXYhNcE.'?C$TKf6`p!Q8p`LS"iS=!c?rfS
4134,h1dQE2]A*jTe[qKgde%\j!:72)-;X::4Y*:VK3*5r3AX>ngc5m"lRm4<oEF4B-B1e>=V
$eXJTua]A&%$nFf3%fW\^AN0p9?Nnrdc90OVktkq!Xi4_i8RR9jBZk_/jCkB)%Z6pN^^gh'E_
,F$0,ahl!_Zl\('ABD2kDF"q>^>okcO3O1Q5O&_cC<NOl+8c:9j;OV7U4'ImKH)r_b2o52ZU
Y\QSrC6I]AZ>!B<qRp!!-sCKjXQ?A?Mlm!S_7:prdJfiK*st8Yif[^bU>X_3:tP/>5o0IY"m4
62(9o!RGG&$h7j8UC[oi7iRtKC,aBfJJBDco@6RD&nQKcsEeE.C017/KP<LBmQr>W.Vqok1C
nu.o+PT0ug?iB"L.JunTVU8rQK2PRJ$'88cW5jee[5a\q#+!pn<k-,QJDUON]Ac0OA&!ClFXq
#kYa49]ANYsdPBQB/G/Ka:An"Ng_p@J[t!,:ADGD/<UiVW]A7qrG6OoId(O-3+$`NCN,Yc7%d5
.;8e0a#,OqK2)S1t-i+NO-I!70?E\d6Jqs=FYNSSd@`B[plA6MPh#Mk/l6(ih;K6j\4CWANY
m/N6^TC!`o?V<H".I2)qibg0*A8![(12:C+^KE2EDsZKVb06OJ1[C0ora`<U,QJYdC%R)koM
3%Il+b.ec"]AN\u6;q*)R2-.&Hk@9u3@f%jHm+rD5WS;CS/2hpV@=LeiNb0+QIgEi:&.Df.S3
le:k2P/S]A5/N?+LR"CO]A/iA.eCqa66W%j47TZu#oD:hq'4!,ls;7_Gd2L)9%I$57"WFg"/<F
/:eqBaJr6bC7^nrQ86*Yb.d&[i50O,?]A_3q4bBZNl<l7T:mdG4&Y%anR@q[5AI'[COh[eLKt
jrob4PkBqO1b)lP_K6J89Ebq@9O$Y9C_Z)'?q=toZ/ci$4nc`ObIeBA@VgO@L-NP^?-m,&N`
!rCrMAQ/*qRP+L$Sm3U`-$I8=3T2g"==+3VLZ0HA-?=6e&RX3N<!L0g<.TsoF$Y2)*%p6rV.
omG@-k(KMCG[&s$kY"F7hE=3jBmJ_ipT?0QTM>p[F+;C1bs1UHMc;An6C5?XT@?Q79S%Y`XI
kisQY<S^O2BqAeaSR=,c_6Pt'?Couo)=-I&-mo%<?NlZ+p["F8_-WAZR]A3u+pg*n84H\?::d
a*D0,;bCRbsA]A(AJP1cfbFn4Z[F`,pH7)hgdD%f@YDZ3kRX5-FUl_93Go26L@/FDS9!6*n2U
[n>)t]AnH,ufS?Jc;Md=:"KSqu_!m'@uNZOGkTJV9UqI!-Oa1p>UKmD-lSrM\TIDW,"QJ\S:I
u":Nnj]A<+hW;?Jmh6;gIinaBR6\&W=dI7<[nt:?#kU6>U+6RN*+Tg(ZaA!Nn0/8.1Mc*J2o`
);q16k9ZefDl[)Bo#Snm+Sd$d(*m9JG<4k86f#0k]AYYBS@A;F\b=?$Z7%INU$_U4)J*F_+B>
*FCSZ4DGp;#i,!2'R*L%`=F?!4TP+"ara-O1FjZOOfo,";]A!lN1RKOKdV1C#>dEetJk!N:m<
#Z0i^Q.5S-D%9h9(XIg4d08.RdPRM/,m1cga!'pe*JC'0@gC]AqumIKC]A-/ejs_G<u4,'^6Al
)X\jqTTT(go^#&j"T*F7)HT5047aX-n'R[3Y=6ddG,=ip9&Fk$!X0g:n5oXuQkp=d\5B^oB?
5<?5!lYXW^Dl3m0Zfj*fMll=d0VgA`#$kV=naCd"'7/0@hWKKnC^VIhPg<5nKu.q1cr^\C5#
2_XpuK6Z]AD"#['YquQi1N,ok&JKH2l-ChCO^s-CkAWd/"ZV:^^C"/BSVC0WBJf<N6cf8qU@6
^M6X2N:,.+WE'U(c=TMTBK++?^jWq;%_*(nZ3[us-!_9r8,"'HTsS',fpMt8AJM\#D&I>C#O
6:*bE\Q>(JGLMLRrF+@3'9rH3]Auk<VQ>(;aH.OkUS\Tdt)c.5\K:-rhN&M5k:CHF8KLI,@)A
6T[qUQGE;Q$5^I9W_!mb(Kt12b:uJQ1O3dVMDW=-T]Amp,p1`^@moa#ke1!L17Jh?B(?/S8O>
C4DGoHp$2;*r$eUm$!'YfM+.`?2^77I55+9t0ZS<eU)('l0VH_gR]Am;id":#J:MceaAtdhfE
e(KhI6E:8tuI:e+B(F.*UHMf9@`cI#844:.L%1q)M))p+2D4:!.77:,pK2Z[:B4n=[F^(,J\
JZIh=5f]Ai#B@5&!'!Gs)SF0,FT:YJ6N\:!u43rhrq*sjVn2[cD2:Ea>)6oU2@Mq!nBY?D\.u
`oJO.j31LF83&Vq@j(LE<fcRehRdJZIrcd,0<e+\RF7Y@L1B?2kq]A%dl6[C$SOq%i"\`eYet
cgouTL"#7XNQ,:N6TSqGs<[V7,1+%M=fGlQ:U=l.JjWg+k[*a6SEr@A8"g<P&5iJ9sios\DC
ohZm)pAA'AW(^eWeo[Kk).5HRrAVLc2m&+C\mS\Xct9q&:a:.Rh\aTEl;H^,[J97V,7#J%]AA
Z;._(LQ7j5J`Y>biuARP_)"7`+u_V$PmQ7fomnkLkj;.P<I1J.PTk8&J3$si=B@'9bD^=\>+
\`,a3(k.ub\/1ae<iFl+BT9=\DTh,p[#ZURW[9c'nn@*dd0p(JG$>&+]Ad9Wub_#`3+Y;!(I4
HkDEddJZ>D?+]AHPE*td#BYKFNEsRTc_Y6TfS'r-Br4\YL>+1JFJTJaD"V`a:rr$.Us';QS5B
+93&I\[9-K+ei[^UJqd'a:QAK@_AQ\+Pd9:031V,aWa+gKZJ"C$FSH4YHi%jNT^ol)WpuU_>
1:Q4[.@c,S8cM(hb,j#\,0pX2`+r*2Xj@9)cn`W's`!RC1",:?n.2nE.PQ0PN-B,(#%XRV.c
Ef.gc%CY3#]ACFP4_5&\R`Ohm?M@6WGKYO#7pVHZ\#CrkdfXSPfC"E0Q9e$)'T<D\;<ohfQ*p
![.3m@sNB@"1,4'[BF(WK,ieq79j5WiN+h^aI[=a`;C0>q7/$:Ip^mX=5!NrPKL_KZI#Gc9+
2^Z<rWLuk!X>?s7,hI]A`'uBBJ_93@]Af+9a@U.DK^Ml[i"Ml[]ASd=iXoknA5F.kPn+'S]AJiTo
9'R?`k[jV$;[u'e1CkV?<eAOFU+,]A\37=f$ShKj2O2",tG4$8"FSjlD=Ql4h$D3upp10iE1<
LT'_%0l[S:R]Aa\=O!#c^lik3VIHnQOgqDA7-Sup-W2sadrU2%>\EVFm]AN[U#Dc?B85EOKJEM
uc*eS(oiLi`LkboTtrWN6,0P:b0_4PV5Ao'`am\!<^\PtF^@C'C$Erngbh5i.ed;s)TQh?aJ
PrV>@W#(>W^$G<,E!OpP5b:6W5!VbC`@"mFb)tS#UKHVO<f`IteV/O^g+O>k[*CV(G1Hh^Q?
PV-;K5-r2Vmu'Hs%/_SHt)]A5:o83E`ia7(>VSR=:]AaTi2lB&qS!=mQ!A$!gtS[B$Wn190qJJ
f`F:Ng5tggt]Ah+iAl)5)2SZn"8n"Z)gLm6l?\VqdtI+(mi@SZ6>I68Hj81`nHBfBgRck@dP&
a]A)DK*$Jb=OoJTlf81BEe%W>&Jic.,dic-rPVU]A(2F_*Me?[ilMF]ATlcpA^3q6sMLi1J7\[A
7$m/XeKI6Ca\dip5f056%o[r]AO+G>2@^X%?c<k(FY4*>M_?_Mo[J=(g'GECW[AU]A&0g8Iq8!
*H0\^TZL8t:X-fV;"dnW?61)*6Ee*CMe-tMWj!SBPQ0d5&kFGXT;T.DhAFX]A.r?BklDp&Kn:
:)u!U^;32j+l_LdARf'ofd1j(3GOk[r9c2kh1h&cIaQ#rf8\Pk2DH]A<Q0X<5/BrLnD\R=mUC
)g;l/K[$=cpK/AQ'X)Bb@;*NA$,N-s#<d7R"#chLTR8/2QQM#`K4qA;?N1ho5ic1qI-0_RrP
J]Ak:@Sr@nd4&,Ijcl2goIGL=YQ,9T);N=KnTWp4E>%qHFO<Vds-&6FhRD-ZJ@GgoO*\35`nl
N$[dsE]A7s;PTZh#2=m"ZE[eu9\mo7_8$Yq,aWa*TJoJu8XnkEBrYToO9e._<<Cm;E1'iY8L8
10OY04n#Ce53#B`H5RO_a5qUB5:+$A0jT(>BNs(L8$>#;5Erg.;D]AH>aiYn74"upg$WJD)9f
JpcBiH]A9>:f4/"Wo1K_+i@-8gX*@mQ!GXhc?U2V[a^mBMS\Qne)-!@1rLhB3I@\g<C9FKhA)
6:2MgV+80m)G1ME1N0;U5E_Bc(I!Jt-g66sFp9'?78q4*YHH_cHIIhClF3ak)H=.2QfuSeCN
N@!"4e9>cq*a;?[/0#-m="g:3ga-"(M::ka4qu6cpJq;N_YWlpU/?2%,L=aE21q7q-#&(Fm$
n8",E)8gh4lnHa@:]AQ3D5+2XJ@Q1ssXi3$J%*,#PIf74`E^m-nTZ9N5;.l$AmI?"7/ul@=_1
M7Q,]A^bt?P.5pe(>lY$/4=p:ufV-'d:,]A#,n7#MG./#s"6dNX`12[j&?a"S5E:L_]AE_npoiU
5!"0^Zm(#s`LqBBA>;i&n^,_`P0&.8CX23E1+`%8]Aj!Q%%W5Q*6fCIDoM/>4&]A0k`d>r`k*_
F"\BYl&YoY\I>,f@JPa5ZcVNR4g]A[bQWCK\?%>/H<OjpO8feuC!/\aorHEk^0&Q<qmaM1f[\
G2V`p7CU@aME3?FbZPI[ledbI35f?<N+$AT<&.ED*Ih\[0@*uML?hP#&UH.LV%)fO5k)=ho:
BU%50%<_?$N%iksu+<=oh*U620bOM`06/r)-l-F:>46EN@=7B+3N*PoLe($j*iX(>k_.J4rf
#HRW9#e;b%[7V2/mSCD8_F$cC.>cE?qP.-aS.]AeC#OfIm/g?Y]AJ7?5Fca9?4:6(V[JOM+_SY
X(E@\JAr8!cKr;W9*U'R`q$FZ1Yh_i!]A%:MPM7`p2R-cB#qpWN=K(732u7K>k^4_$!kgJ``M
!@Z_T7Wg7IQ:_)SJUeo>p[:pX/8=7J=?aAlVH)ZO"7hpgo'on$V73bS/`Ks0R^eW2kHRC;5k
[djYll"W/_l+eSfT&u_6I#<6YU6IN&hMVf&:,D7O1<7f9Z1q=_;9.5c%+8%k1a&l*!b.K!^<
;%Uacp63Y`n^>-g7q?%+P_TCLn/h-WD$A)&-;^sO0In5A:gXl<]ANQ9/E<OlD5.f<#s96ia0e
&&M<F`1C%oMrbOJZ<^S?rBc1AF9F^G_f/!.R/f'XRaMtk?'J-r;ph5lNMn+5)tDV<56id+3g
C!.@#e6!@I#Z1`pV"oJAGk:,_KW\a0_7q#)[sh5?/%a6k]A4a<1TFUDG+W'eJ6[7-\*eXp3%S
>909hhASlE^/G^_Db^7S6eWC$UL!?Vpp3l!U097igGOBpLcR.[scW3C"-N3[GZ^59tS#8&dW
XZ2.LHR-h12ptdja![[:jLOt\UT??.CtOW"S6`P4$QtgY2lg(qBgiWX,X*YA?D8pCsV+;\DQ
\C($>QRdC9<l%qjtIqXNIXJ%nPD:$r5sX8dD5VGu)l5;_^D74_R<E*Y]A/J8f@45*'t/YR2>t
PQ1/`RdGkI6mbm\+lA'Mo%/@&\?3fr9+T>cZii)@UgR1R^$CSGOo:bVLO/b4(V)24[MVG>p[
rb(/&3c[aCt)E,og/O(o>Q5aPT,BGkpRcHepFLkMks-*oG(hq4^%o5Bc1uh_PR!Rqd.,C<s1
(dthBq/sZ*CWu)*eL8kigMY%p[Y2&9SVPFXAPEu2tG\fai",o!qg&g\sPc#U`DQZNP^uPk%W
_:C-`C6KhJU+_Ip7asue!l>C<_?D1TD'fD<f_,s!0=b("h1JCgqN((JXlbuhfd;QUY&ZBKk6
4`mbZfDbf/3&c3BR0b;=t!#Vn!u+'[T!TmCbKep.R)mNYb70lT$7T_IUo^7O97JU?7TPL&RH
B?L8jd\QiS?#o_[S8hWL>ZMU!GPl?q(NfHG*+<pq_T6\_;]A`F%G$^G,C:NNkKXg,9:%i\>fo
aro>?gs-.(d1%2!;OGp<$!LCp(O)0l)c8R)+JLqFgNVm[LM@9gYAo"TP^,>@IAa?UAgE=2Hd
>d:fgnd]A_'n$Wfhd'(Uren\51(W.dgc0fT*GUonT0&'>99C,UW`#QJj?5,thbJLXkI<&Q=pW
mXj,6rW+5eJJ\_[A-^hbd0P4)/2uVVT:4+Y&,G/aPGQpcnu$")r%R*&)2(mAb^b2?W_b=n%Q
o=`%%1TS66nEs';%D61eWnbPRX-hY4ndUoVah`t;;Jp&\2;1MbQ6Yncj)'of0;gsG]A26ZgQ(
h^c(k1TZN5)T'HRV-,"Y4X9G6/E=C6@G#CL'1CojJ;9L4d2geGDEok0FGOY&j>XR-Bn<S^gf
m&bffV>6aO)+dj6sBFU\W^Q&CR6c2?>N"f.';mN/-/HTu-=8lu@W$c`3'o+5RP)Ulps#@H&p
/]A\#oV?/6LqSTTe,j*"Y`)<o>DoTi(Ra]Ah*GP]AL.+qucRGGI%6W%dB(Mf]AYcuN0j.C@WoZ;+
+;S"nP/7e3AS.C4X8d/P8N=/NGdH[q0jPj*Ei2*X0EV>rJM0^/9_(n\o[>d2ofpA+@VuTf4m
srl&Y<\%?gRS/,VICdidnY\"J#=#pjAc]A^q0Y8aZMHj_E+bMX/I%eI?L#]Af5_:al2sth-j[7
=oNR&rk/j_"7P.nA<8f-o4fG$>CBGN'h:i2j&T./J"Nm7#=G$KZkI(#;b]A^2GFG`dDBFqP.i
`?[4.i^V5^7-"0-?hDVs)rp(BD$\:H?)f(T*nkN[_.*J(7/OSCIBt]A?;[MUR(eH@#E2?Jdku
rU<OY`Q"H=gT]AkbOGM0\"I&s5WnuSVa%BVCld)$hYg>9.T4H,Qi#">nSC`,o,"O^/>DPZ0#@
n9beD_I)8W40,E^M.V9@F!fuG14[Y?$_"(VcE0N2eD^9Tpg+C'^Kh41;<^?8!Z(;0u4&aK/$
uG(l&36=2A+UjPfQLT^N]A\omoC9JtbQ_n-f^[TLeWM/#n]An1_>j;A`R1U(E6CM7;r`-J,N(N
Kt,@#/cr%:mocW*m[n--2Q`;5hFKMd31Ge%0MnUGrs'^-+:Z)j6S?skc(EEoeb,Ye*oBaZ*\
tdC6q2+=&V^),QG$l00P1eGK/cq,:BOK7Tm@SPo6`Hq?IqDA.AN?&2uR(?mE]Aql&.qOIiOjh
Wf+.f[pXr&dqolZ31H>i6XfilqnX9m"bJV@2kEQM)>>$FtP%0Yt$bE?8O:oR+%.Qf>cCAlM^
3^na]A>f]Ahf77^l0`&q;Mq0#So,H0!a=9D'nuSCF$gQi>58&A?RcEs-2g=_T@f3?(X-I:DE`]A
3s]AcE9:SX!P5)(1Z5$o_`SVP@1^8)/12b&N*1,(`n$"fbuU6,;\`s4HeFp:t7*,n?MN4LeAO
%fNONNgagLiLV8\q_2pEo:>gTAIIN(p@!U`[>jX4b6"/n!D\@:k5KfQo:$mh"u=83=<jP5m4
^Q8?XD@9d>nKf9N,M(7Gd<Ni*TKH?9=S*fIVT`aLsopCq\/b'T_!C8=h([b_`iWG<%e_d'8d
>Is,p<A:EB*(!i_Y2DdT[V9ejJU-u);gD_`"(2peU`O]ALoSnATe'JVgroo!@:g/R:.YoN;72
'cDSoL*`4DQli)1cFpmAEu_FPHMIkUX"G2N(t2m`M4WD?PY@_]ASfGFl4]A8b3/A\F[A9JZQOH
'RXfVBR/lsSf$La5rg$?9_U?n6EkGs:"4cr:`ORbk87:tgp).t2i9,:;[n%Fi+L]A2RN;>$pV
<'-`oq2Hb*7o$L1WCQXjO5*4-m"/+mDAqd(=oDGd[m$*`EEa2qA^'L:hW8EMD69=Oi;V>&.0
I^$40SXdYAPesj5,\72a@cd0)U"%R:dJQrF#tOa';`<CFJb3Qh#R]AAio)GF0REWH^?8uc3Ch
Qn-;hT\NVjbW8RT4;nhHI.h753p$gQD?:I9\7Kp)iL<3_DSGba#=RmM%%s'l5EkN-CrU+K..
etaN]AR9hi<_(ZC7N<n8c`0mg*WG4in.43poGR%RH#on-/9MGGi=i$iXV!*C<'+QEhODBK=*g
-EqFbDQV"j_TjLsi#jn3mcR3M8k6A3hAlcj]AZ=/54Tar,pC*83Q)*V9RYX&dTHejs.ejF9+"
>N0&31ZN/J<%e$:JKD!.-?"TmfJ!",cQ+,r1^Q]Ak`-fC*Wp>q,)kV4:P^c#p?iQ):d.=?:W:
1-GAWi1lV*T<WT7gG`OIR4pRPDp?:A]AoEZR?:NheBkJ-oFD8KPeO:=8*nd[Hfef>gr`$0.:C
MlJ1h`GP5HR,jArW1tXq%#iG2d`PF7IaWSQ;89kg=R\-BfcSC)oOjr?i8/9!FM%=1NV>-Ff@
7H6f:2Y.&#?'r>c%P^5V^Kh-#F2(#Q&:LSL!`7V36/-8^RC8'XPh2p3`@A$cO5pr(qN;Qqk/
8/YV:EIFGOe(a2HIB;'h/>3:tVnSL=uXC),6SW@7`DH>ND;mPbN(q62L;9k-0VDX&0tm\njA
mJ5^eM"clfFZsZojSu_UI%3Ht"i4VWd_ECRs*T1iMC7-N,(iDMCuFROT'XJ0kP!L!;8liXk[
dco]Aqueq\mT"%5'YRW_1>]ALj.7P]ArCaEkf5athWqssZ+(KM5Xsbk=<Rb#)7K2YQ=.csb/FOQ
+5@p;0d[j_`j9WT8]A\4`@+%Om0FG=0"GL59"!0#NLPI'8OXF'2mQ!V,F[@;W:NTan<GhP6'I
]ASsr[;1!^McA:M.Ol2%Z7DiefdZf+T0EUrF&aN]AXjh*+R+@(2YY\E@#?+BH/RIlK"[:;Gg"\
P-:I"1Rn%ZcK6F4?\B2SdKm!,:4D2`H'YA6,ke&GKq%;oE#B<tY>]ARM[8b)_0.:JGT"nSgP=
[1du7FuSL=3<^5`q3d2D?AY2Q7Kg5N=\p_EK+I/qqQ^DI.89/bhF-C@8(>A.\STrH8l=]AKqr
q!))sXB:Pd6]AOAM+\nOp&?YokMj)&*UtQ:>q(BUgI3DaW.g-Z`PZ.`O[A:Ts$[,ro47I1-5C
OI\%Q9Th\.=F;I$)bQDTU,Z#71Ub=8[eCo9.dS_=!aF'W./NOV:Z</t6`@;?icF`[Ne6U?]AP
73mBXrmHZrjT*(P^JR4<29gq!`9.epNO`dY5)qf4r`CP3Y&Nur[U&0b@kTnd"b$eY9u:03rD
,@)QJuj?0%upTtF?K6"U-kWiIpZ*m4ookMC*gXfR3_S(p.\*kcjfg\Pe?\,.u?+X8@]Ad-AGY
6,K)OEjQu_1M==Jd!Ei7&U)+Wrl:\qaF1>^9g3E%b3t\g9dX/mq0dn(R/pZcs6o;fjBL-Zq&
#/=!WN:17mZC6O3ar'K7;N92I^9^_*kbrB:eNZe$]A(m.9hsg43"pY(S"aMP\p+@J[MZ9[`7c
1q'nq%96[uNa0Uatb>'A&Wp\h%4cbXp.?!77T'5FiD.h+m4F\23&gThufnWQ6>l?')@JlDR?
Q37t39bEDM-";Fa[ej.dGBqE)o>A;KM4,NV%J:+!=8&RNHnNlI?VDE[D;%\@NW(I=ClNIbMc
24Z7OWne#/Ye..?RCAY34dIKpJJ:MGFWgEGN\hM%Z&@*="W)!oD+0m?=e/p!g3H]AR#t`nc*,
ApUKpak45D\4Z=7l6u`>SB!_OMoN6R(fgO]Am>L#Bn=(aKS85iaNH]AAPDL:V_5_aKWpaMd%[s
7RhSKHCChTV2B(n(?).'WGNK6iG-AEh^T'^F]A.4*KXS1KZYKH'd^_]A^Wg1E^>+X:lWV^@^h<
*gg)"?Hc?u=O0/4aL"GS[8&WikcM/cJ2!sV<W0'_2P\64Ne<[4CH/H6UI%u(4D*aMZ>R:?dE
s$_I4Cp+FSLt^F-ZfYba-XM9l`^1lNd/gbE0j.FJTfJn3niOW*&['"C*%oaSMkfVmA*'Kk\j
,*HI)KlgVc$T`2ebmX0tN#;m&Q&#*k27eY_EZ7F[[q.Ea[tlBE:]A>J[aFHA^Z+W:ZkMg*gFR
NP%9LUheltI8m\u?/1<-K\?g(Jq@?-RQ/Too:KoI>i8`:d4$[I8SqKm=G\KhK6^0c>NF6`F0
Z`Xb6NE29Xum4fhNZDjBNIqV_NJ-':BVZ6>ZC4:/#D9<8'gFdYFLp5)_J``'?cLF<._#.(8[
-B`,QA'rN$.4"G(6`kG>aAehO/BIdB,-l!?(pXg6=/YgBDbtJ\oE1QtS;+j^*KLIS<'P\?WA
G<fS>:FAIeGa9RCgk):i)tfE&q6\[inASIQ)Wmm=?YE."5g>"r$_YPg@"$uOIT>lF[o?0ddK
=2nfN0C"WucdQRe,2geC_WLQ23=0K<CH_CA9.G.%[b[Tp"'SOPVfcQTqS>-BO;$g;<WE8)WJ
]AFXiW2nYFmC2_*a`J)mr(!pcjY93SS+n>`)7u.BDLJ9u'"&JYb:P6tHE>XUJ[ac\)U%aK\Du
Aa*a;Q`AbLe"K^5)-KXn;FlmNODIm[]Akc3T#E]AM4Fd>J-^4[&2s0sko^c65&=J6'43[g?*e>
e;S'?a(<kjX3mIWRqgmSd;>kc["iN$4,:=!p;fEb,9oD&q7!LWiEjqD>!X;se.\FpE-j4!AQ
&YXnPr6I=;%Q4@?3cVJKq.O/`jC0^j.6;_l@6GNoPC5UL?^qZq2TZ"IqG2C$h%.&0M#Q_"ut
86MeH0F9To^j9PGB?nScPrB2WDk8/1?@At*dte#?g5+`3[ipo2Zqi>u/-C3Eh"5@Y'q?(?P2
a\a!]AVBTR:aX8N;jg#eIbmnh#FA`P_n]ADI@7+q$1SuR9i*-HX8E+Q[jpT#YdVW:o=m<%"n*t
[h?:tp*4lIM3GRk.IG#NtTT&U/+tnGQe_O-P!.oLR0X#!OLIZ`,u7@*>QLb00XOpWbJbY&(V
T=8nFta$3DYpEWS0'bb>pX3dolD(Gt#WB!aG+\>'`Q-r2eqAUN0mgRmd"&1XY7-=#)Oa6/>(
D<\R5'0Y&^<.!#\h7;PPiJ$lA5mNIe789IG^6)-5H8@9kD.se2]A\4bl&\jcoM+^qZJk08r*&
9tG9*VG,GHc)Y)'Qc0s.FbUG'X.R1C_)/NH$!gTYZ9E;D!^]A@5NMRYejU5,@XQ7]Aqe!%W%et
F9/E+,+Kj9F$l^KSMiK0/5*fr&Za'#a!(cP5pO:<RN&S<%!h<^T:L`;pBEp9XaP(^faAl?pi
D&&Eg,u"mlR+`\\:[5SkZspB-mn]A=Le[Z]AAD)QRccQ_&pqRN?6q;Z2ZI\pH=mPmr8J>Un?k0
VE(Pe&$5+?UUUK1N.&%u:C4g,cX*Wm6qIu(4=<Hq:"tt3$e&cS=U5@B56U8$9=8_een8MWb7
RVTpCNTa_bLgP%IUKnn3,e5/B`?fmMfuZX__J.$e[bPsLA.cp3o9sWb-UHC!5W*p+.l"9gr'
jA,!0`%UrCVr$a3%]Ajo#SOpo"j2>V^j!9;>Hoh.:F?)&C`k*QMEfQ*Xd9o,>Znqi(\iJni0E
1"VIHSuES$_==M^;Hmb;ZO)(HS&Ci?\n0_HE6hp!=$#E2HdpU/.J[?Dc;"1NNPbJ(_J/M9D+
IJ_dP:]A_jk!2BQ#MR2T+5BRaN3FL>sdhr/".aB;)XhE&Jq>jABm2i(k4Wb%3O#Mbulo;77,+
)kI%)KJJ,WM2VZ.7JYpKQ9He?O+;quk+"U6;,L6\g2&!b!4lP[pneNp?M?;%f<fo<@TsF$'(
24%Y3k\[&mLq9rL>EEV77NDu:;1(J@+`pLs$>S0L.6j(X61EhB1/Wh_;DD)kPoTN<FYB40Sq
nshb/l\k,B+lFpBIK.n'F,3BSrCMRBWtHt!#JZ/:i&W1F\1F$JU"ZToYd[mKn@O1jZ>VTs`?
<E[.m0N+TP^q_(TolZVVE5+/P*LLWkW*.+8-t,toHN$*kY!oUtZA/i(Q1`aE(*OSSg.%Mnf?
3UdK;ZW+I<Hh;?3'eR@*6o\3uP@qb'(js;U!H[Uh[8Bi([plGTp"01BRd=:ITME&?JZL`>BH
OQ<d`>&"V-$]A_&mY\"_a<E\dIQr"[Ta+Y++I`S2V&e4-D5Lo_&EU(-)-!U`R9ffpW/mPh7hj
TB27oPolq]A3<KLgV3i4nS)\*[,0*Ln5-O8P;!M?@sTT5cr?Zd5I\2U*XLjunW+Qo$]ALf@b8@
`sGU?%%[25GROSgBeN@i?5l9?4*4gbU"(8&qDD0t"IhYd$(03srpaND+88\%FAiaQ:t=^"!1
T*G_L%?kjTLCs"O+D0;jN_^'mnf*'#7u^Ht1(3Ktir5g_F(KC*qK-$B=chmDj+lH[++o[n_!
pn3+)GpNh)]AuCP,r2%)#R+c19i>F,T3/Al<OW&'1U;Gk`7d%-q2_GeGIjNd,J#K8[6$.apM3
_T?ISp-1?SCKrNlh86eln2=-]A3$YsZ^YlFXA_=?CrWaPi[&,unhI/@bW]A`Mn#olk*GjpZ(_K
%M^L/TT"&MA0N[A/Q3n+*f2tN30,qr;;bR7W"#%JBiQF%42RK(\8quYcEMhR%+0JIuj!Rs.,
mUR:BVYdf<,jrj]A,gG/m4m%B3\0m3dfl0Z;u(p6M\]A8]AhiY(DV&b%XRDZMn"Ms*cukHpa;qL
pA+<WghW5;79OL:b6Aaq?EEfcI+mlRcA1kO]A3;1SjRoILs.fQ.lpo:feQ<"[Y/5#uS]A:SA-G
K2'l9k4TpA0l(%7::i$2/kCUi?,.:"3G0AO_siSRWtrSOq;KMU-/\hb.UqY3R#Vj-m6s#<i4
aTq?%\JtS&Bi7EJ)Df1gV=O7N-BAY4tOm+f'i+>c9RW;MgKm(BT0?,[M02Jbfk!ke++(O]AGr
`H'/8.<gs-;RPl:4]AI$Af*`IJ6L@AbuEfK3a0fq/.2PIKim-u8nE=T\db-!GugPVU6JCXA$:
70Y0DM93:1!/:>knWK`9HZ!]Ai[Ioh,PgX64?bX0r0)E@]Agm!^CPsJ.)i-,\gTr,&B#Zf2B<8
AP:Ud<"mk%UuSZkF=ANKZsE62UpOLuk(XnW:Nlb6Ke?1oU,YqFRZ`qrh4"(6+9/3FRY;\`hj
(pcOhr4>,[Mbkn^:(F=dH4h3VV?`Jaq&O1_nVgqNo!@XA1)u1s&l*1$O]A>mE]A#TT&sSm5g8Q
RUPiO*C5Cr@-oQg\RGfXcgc%fI!.OYoF'I1up2qTQpaNOIUXD!2J\-rIh@V(;)EK4a0C&F$B
NQs3Z4gnD$ANIIT3i+(m\ZInO342smIOhLkbI\!e!'R"dkt+sOCiTBX"9[_W%`[9\OePncZd
Zq;Zp5Vri9?A\Y^iPFk_rUA.&sh0j&g5kK&p4GLdipOC+@iYM%*5gh,ghT4&CBer1VA0A82X
^/,JCh1XZ<"\q,T2KPak6sAH2^`S#74PD9F30m^J+L1jFZYg#8p42k+=nEK3[4dn(N0qF:#B
4?/>HtH-C`kJk5[2#i7L5Br>*uZR^\\UQ4b\U1f;dBNI.c0g_IASO^Ujc[d"@:B4QX#keE\X
;)DJ(>1=p?jm!RpN$$VC':X-/@`%Pl1H+)7NYsFkW4cA#gQqQcq+[P^()N$H:!%l@XQ_f[cr
quepg.36321"fZ4#TGt5P]ATu#nsS*/)]APmhbh_ToqlMr9m+>6D%R#lWHq"N_u=dT<.4%[)'A
)>1C)RNTIHf=)j1's55s=-p`W/+>BP8P_.AU=g]A8Q'\^qg==K0gN>E2`B04"TRB&7:qJCnq@
9pPJ$21VB*SH'V6IEQYZXaPXMW94g`CgNff:68U#hM=RYdj(k99RCraaYb7pInm&k@ogU87^
UTCjG0"up)':abe'-KGP#kNH?8&,k:\B1Iuu(QJ6<sOcFoFNb]AU8&?$d/JoG39%j1+5@p./h
7jQcV,9mq4Jg[4`a\l>f[W`bS;C2H;bU@sHg<Y:hTBh?OC_eO%r6e+^uOB.>PX/u_$X<=rA.
739opARLQp@J7Y2fe=BY&g0"h!b"''q;k(>1g[Ob[$-g3?.H16C%;=oC9i!G4ZcZF]AK?tTrY
sWH#buRHQJf#)Kp"a0r)@)HhF$U@[[TPo?DC!h7A$g2bC(d+<VWUD?B(Qs(V<pJ3R?`gK2D6
q54[6#@5bClJ5ERemj&\)kTRMg;HN4,'I.s>LB/uZ6'f+U9C)o(/3]AB+q5I`hSh$9/ag%"FY
_q5nV8D,a1816l?):el%BW3Y&qo,MS)'k'%kkLT=VZ-msF06`aU`UI&Hi$ogc5AF4KS\'<H8
@Q[Po6$$K86i`*C&VGd?UqC^jaqNu"akOHHF[Z/W$%GN246@44ro<";$Zf8!h`jF6Pp*ZX<c
QukF@`te%(tqe7i>D-"*hef[G%BPh>[+F+7ji]A$,Gj]A_I<cF:PuCIL55:<Oq=pmK1_a!!0;[
3<lKoN@'S;;ij'9=;9k155V*RJm6d<m]A0%VRVjE]AusZ#uLp'YU%?rBrnQrT*>la3LjT**N$F
7?U-s5!$5SJ&MQp*1H)dQg-44(nCF`PIZ16?M`p8*Jh/1_JQ8.3`_:P7qU'eQLZL:D]AMl;7g
Y->LEl@e+o_HZY:-bW%V<o]AAIL1q=^,*W15U5Pkh$_pjY(HOpi\'dr>!5S4(km?4OYAL=@&!
,O18J*;"\t3gI9(&2aA=WQ4i_hqJoLe[4dB;O5=j=g]Ap]AY:q0suT5a<Pe!".r<gg`&/kO>(5
c_m-+2T4ueFD)1;q?!"LP]Ag;\HD@/E5A6)d[gi%'kUi:<S+h0ku>quCi+/+[Ze/*6'kFaH=n
Q&W;`05$gc+IXW]AeePEfl5n5]A4cpgn#Q:(.C";#1@,HCf"GH`Xe[?VC&Adnb#1*eX`+W$'\Y
D1pWV)2Y,,1]AY<KXeUEn)o'ZY#l#uYo9eT^L7U>_':=L,mK_r@PSWcYk6'fnoOTB:TR#QI[O
q%\5\aW5f1<isna>'&lSHPiIm9XEL]ARQPnMs4r^7e>_[ib>+22qM_Oph?Shhr_=N;&n;Cedd
a/B`W?MMO2#"prTOO:TBh+")D[Ys;kI#!^C:K5Slo;;E.f!cmtWan>U$!'lNPYhtDr#FW+*/
Xus:Y3VT40TdbQA0b]AD:b<85EGKG7\qBOP]A91N0e3=VdNml%h9tX<MdQP8ib+4`3;VfR,P+U
VW_l8K]AT>U=skR^mpHkS@V:)Rg?7/cU68;]AI6R^5SUs*$dtE'7T[C\.khA4Y8,U?S;$mHaa7
N3nr21L=#@#sgolT,o.,3M<2g5530Un*JfqcVggrh.A+(BB'ZkI>[`)%fsN9U=M9XR)\O2r#
PY<2)k$dU\_jlQ(MY61Y9X"]A>Jm\\%=#S`</NNk8f6S2p',S\"5CL!05&G^MJb9I$@&9]A%#Y
_ZP_`r$.".L0U[#1W;Wa;Y8+MP1f_`OKDPNS5e,%$Q+G'_KrIV9F`QhIqHG=C.un]AV\H)[bL
2M^C*[r.^f<9)r0PEnq).nkeT3D8Mqb%DoAt$-BGZmn$VcOG?/Vi%uh3T)!G:;VLl^K[kej3
!#OqrHAC(5_jWJQffIFIB_mdK6Q?0J)/"Qb;$H9[L0B$k=-U%HmjRie\NQQ:BT-L['C:h)sI
AnJ"]Aj+H7?E]AgnF"XDU,G1Us%Dbq$s/jP5AK["dH(ba?&E@H,,@\bfo/*QH8</M@^`'Z@X]AQ
CpELH0h1dN*A]ATBW=&I]A@=S@cfdKPmI*g\Ne6I46H?gi?iN58ZsqAn\#%hOjn-71h>E1NVSi
6ek)N^P5S-f9rW$nPLlA5;tf1ApO94ep=`$/CC0P'56:hJAMorP`G5H(rNIe=0ZFSkVi.2E1
P@B^^jqRTj6$MKpjDDu]AK-S*HU7%R^psp-_6p!i%kB-2^EMj>m[&(h.V7q2m/O_&`'iu."]Ac
hPONrF;eR"$j!)[D/SrUY%Q+GsF3q7U"X%E$X/$"(-qrh&NIOT"UC]A!9&_[!9m9[XDn,b=i?
H,mI?Kr9u#`l(MB9sUP6llluu<l.Di-ZXT4P6BPl3s3)q/)_=ccttd\kV=Rq:'fFA9o-d%Vd
d4/T$2<@)/<@?f9S=$hVJ$Op6lbOkLB5)TlAes/]A00B&s9gmL+B9-(3/XR,ZdJ^^MK(-'QOZ
I?T/`\PA@Z%hLD$bk=j(h+A2m0\+#&"9=6T\W;D.L6h@9)naReoqlf*'eCUfe/d6tJ9$fuVP
,<XPphHVGLI8../"msAiiDg=hgi+Ud2-!,m1E5?Ji2Ot(>E-HWjFdWCUM6jEEPZBXY\1pUQB
5i.H[IBh$VYf)6&^n+/5$hN_SXeg%U]A%XKE\dZ\j8*-jS,o4">A3k5PLo#)Ou.e>V@GL.^5f
8H'R(S5PaHoYsmeJi=c&0=`09KPI,b4(np"cg;o^jca%As7kXYZD]AUb[`qSL39`SI2,sD*7B
mB/>6/G6Yq-&`n%;emjSQ(h!UmGJH]A5SdGqaA#G[2ZK:b0^RBQA^mA8Vn@HPi=VWlXNcF7D>
MJ<EuHUpoO/"X$:UbHdBm\56>^EY=7#r(/:i:B[HcAY(]At3"b/(Z>m=:'3W(?;WK4-Q6%e5j
:?rG.m45,<h',l3IL=*i#936[E0T(hkL#`]AU&6lZs!J$L)beKS5[g-jhfl&9o2XG5H._]AKUs
Mp:SF7_8b9;1<&n*CLn@`)Y$p4`FqUcn5568c$C)*H>!hC"`Ym/PJ3]A>rrc"+,qn8WWc\@R1
8+NeAR="qT]AZpt0''ZqiG9ImPR^drTC-*>@m!Jl+6hL<sDahrL80ka`_lB7e0re0Y&c"^e_=
>cJJ67nmE1\PIbPc*cWr`$DBX20[VFdC9m]ALK6Wm$]AS7mG<T.p&Z)riOe_Y<(1K5fF1XrmOS
]A/UaMt!3,GmGI<W`SVlgYbu:Z_-\l#)[79i-P.igJ'Yie^agl"#Kfu;nUCP*eKJ'Ei8q;>PV
NMp&*tN"k%bfP8J<(9UGMH(Ac7hKOb>UJ2nTe]AY5&JZPmBcGND#EeOa=N<dMSWFgrOIL?F6h
h5eDda5>s,k5D@9;kGh%.QkW/SgT":H>*,htEIcV3SUpV"QHeV"SB?7]Ad%&jsgY'Z&)dJT4A
KB6%]ACjK7AX^Ke/ns%q#k(-=!FBjZu^!\gU?KJs-50rN8*pQ:50TTQQG)C+V;V\!#7NdHV#$
aQfT6Ph8G_=9?j+\$e9V5SWea<-h&gAlBC/Y'N+?:be+;ss&#[_JeW8b70R.MZdSQA&R\Si@
N17FiZ9WTW__DjXP3$GI*ZE7CC=+0q9'OfPg7Tq$sUE)$V=hUtEL\o]AKpXRIH.39OP<!6_>^
@mSP7P2F)VgX<2lUKl5!J%9L?DD#oPg<ApqcRG)k'knrLM5TCL:Y`#GD5@N9#g>5V.*Gekl(
7(RpqdBoH8p$,ZSbO$L%21od]ASR;E!8Y7H>Sf?Y2V#mH/qsD$@u.:nqNfPN:u;hI?X#2,DK[
3e4Nm(m0-`_F]A\Ro0(=WPP<XjPrQT1#OCM(qd^c$$2r;=$A9eL]ALb>Q`JQdVjYYO&eMs"FiI
;hBC(B7oC,DVs!]AX^L:0]AB6"MD-*TZ)1&OUbq&(fBKk-f===jh\R]A>Q.e8k+A6X0^GpoK[!"
93DUsoM19iYcKWi;N^aS%SWR]A&,jI]ApTK1)Z1+YOeD5=@mgnLtD^SpEJ$hVZVK>+2I@:oC#n
FZufq^Ga2\V=9#2g#Lg%9ROu?7MVkdu=a(J&20fh7:]A#@Q76I+'=oI"4jMXe1ETL;L_EB*2h
Q$,ItGL-Zt6W(7'9pH*n.!&n#8PcA$<GO,;)n:<\WRYMR8Jnl@*bIftYja_V-.EUfDL_A\4e
;sUQh$"rcFk&Bsq7S$E[m]A#O+gno0rO*@nPYTgH]AIh4S?gjAmKS%@Pdc<3V$28Vgt!Y1XcX.
?3tgfN*$nSo)5i]A&M-6UBZL[MCcU%Oq1$`KQ4A\9iKDkb:#lAQbR"!Lt2B6g)&o[FrQ,T23-
.N(U>f10F(Y8WmC6696('*[=qZPY)=+2)NH]Aks'Onm]A,_P&P3M"%9Rm6VZ;7+<pEQpL\<^6/
fc`/\A=Yc_?BI`9'6pd/0;NTYl3Jq!Lb,S7i6V&(;gYD)i_ug+f$:jX5P8b`JB=/2ZDkHQ;;
9lh#;.Qc1`R;0WnLpRQGu4Z'j&$Z;C^W;eQ"os)H$]A]A4&",Fa$ffc.62QJ2J;d4VG5k`Q1Dc
aE(rUKWkR7q]Ah/RQ32CbGLtZEE9VX##2.eM]AucdmZJFCh#%+b<)nD\*mW=pU"Pi[4?1!9:S=
#R2[l=2l^4?q`0]A#"(]Ao\\oG/H@$Wr1[lg/4/"lU*5a;G:,G5.O:Pj)4j&b&76sk2EuCHCY3
/-Jfq[oB9[E0Sh'ba(%?A<C'$!MEmHar32,t"g<46ctB]Ac!9s:t%K?N+U.FU#X><hr*[PsDq
Iep\o"@;TiXJ>'`-cB10XcCMTTo;HTpXF$UYE=NO25)bJbuU&]A-`TpZ`$Dkc]A?l^".ju:)"b
\tSNl"]AUPj9?rJj`<;+=%AYW?3'TCM)D#ub7$6qo5KX'KUV2GY@COnO7J7tuucn-Y"">hij;
E2tWu:'mTr(ph=e4uF+flXN1u.Eh%\e6X0f7RmElBPBH2:.`4mU'2RJYdrQ@d72T><"Ggud6
,ccgSg$g'/d+6/A<5;Ur<DY+*6YRch:;Z!E>PdoBumd\lC$sc1$068i)@PkS(0VENW$Uc(>U
0AFsjP>qf_0gjdZh0A(nbhFZ)1b;6`Q&A*"MIOW*e/2lUNKA*M8?Xa=T9l,V5.>`LH80^,m$
'Z2\7$["?f)Yl+(ca;;1Lun5gfO.1Qgi4cN\hMnRml.qP[>XT^bN+C'#hKR2ti3<4b#GTg#H
sN<EAG:aNkdu<cn[*</B-7)Fp6IkH$"3!ZIW;&A&=e0E`56kVhoda2U::,fQ(jT5pd?1k9+.
%]AC'qitZ_)RW=u[PUgdo%Z[<o4P^oa'sm%d8+kPFnbjFJC1TF%OP9$feJ,Z=Qpl@IcVr9a:q
^@2%`C3'@8OF!IT<ruG;[L#iQ$a3*^+p2W:6obM@-jal^BN[Wn2dK.DU_FZ]A(hr,sCtiO8fu
@bZi=DhlV.jH"G^#19`!rNVAg)2r%@>^QHPJ.?OZ@:MA%mVYcajN:BV7qY(87!s=kq?^@9/j
PAO96XJ"JNV?Mo9)f9^=3Vntn0D89eu//JDa7+NqVI"*QWlf0,,?jl!%SP=T3/5h"An.GAjq
jIJY9'Cns4LUAELgpI)"+VCLdf82r/]A709f4Sm/%8TXR':1S!;\"EgmP.<H=641j[VL7cbo4
djEP`n"-kf_Ld>LZW?)6!@=]A&KVb*IMAo+%^!>^[H@5ddNetOlmn29'+N0HKS`dNm_qr!5HC
ZZ2;_EKb>KEhdQ7$T.G[b+02V=_hUn(tMOe%0KnIj,4+DC?o(0P.F4Bt-NBGO_re?GB^5#PR
,k;l0WNS!^[6uQ40iG./b)LEe2n?45K(aX%Sla'OAq6BMF[YB3eFXr8sVSL,K7_hu8HtfXYP
?%$se`p!4ap)9`AuY'*KOG28NXpDE?3Q=ZR4hINMTJ%pcO+H#d"di:>&[?JE7tB,lFXO7\"7
:i!!&b.E9"YiM#`Jr9@q*Tfk<;^oB!QlWVkfej!n>#ftR"WS&n%hf+eL(Y6i`bCOM9(Uo3!c
U+?l3h6k\YM#.`'C$@?u=PWe-,[GN>m*h.=SR8N`gpYJajYYoUq;r72KI7;uWRBSt\.K5]Aca
RA-5t3^X=JYaHVi4<gJYGl+U#l-$;HQ^nn!I[VV]AddnC5nA;n\a%;_ekLG(L;NFoeQ8sb'"@
iWN"3c^Q%h?opgi1-m8T+G3>OT+,?fd!%7AX5Y4j\e10R77`K^\(U/R*BRB=*p%\Wt+cj0,j
.#`XCuF?AC,OW#7G*7/1jR^/`Z4LTgnCU<ee>BkRek/59mpXT:[+3&%g2`>WVugs<P[5nWme
j.4iOZidh.Yl:1-KA-p0WLK9&2@m9,L/:Fq=[*F3a172:bUZ@]A$e=JRZ-7JZWH^AO,BAH4ui
EU&SXZ,=Ogdu)__LD8G2ps#H6-'*uq8osKIp\2\#Uo4i`[einfMoWr2s4,*i5H_N0s)<r<!<
E$RTt6\g8b:?GZs4dQ18OjmaX8@fDHm&h]AhdFj?E.7C\<VsS,k;j@N#Cm;'2CQ#T!(jA6SXK
2g%r\qGcTNQ!f*%/IWB*qi);3HFZ&3#jtUt4hJ2,CW$*&0grqbKbjuN59UF8]A/+Pnto!+M00
fbBjOj>ag78Z:I+9HVbgHC=Qn6<qK6W#*#<6\nkoP:XJa([sl=)f+@rX)3B%%l]A0fPnB20C"
*<A1\Z4QXS*#\*qI%]AbmO7_pu<t[p.o3Wa"@h&`?1F/^jk"o$`,rVp4&LS1Y?m-kLGRs&QpZ
?-(\#L3!b`Fer#HI<]A,DW+ZWe0!kd(b(`:OVt>J8B=.mhYbaeK]AgRSS?2[_eI;gXp'-/2u):
)8.&m6Y-;oht2hW8huBsruJF,$#A6.lJF3IEXhkHn[,@0ukk$=Yd$-7@8%dOf#qpNYk&lfin
/&s6gPPBt)&^5;tU3kT0-I\WEcXUSj"P#?B;Ln+Ltc-.WZ<l#i7@#u+EF`>2BUColZ72[ouA
dXijn+7(2Q(LlhPt,LnEI)k_5Pk8]AH*>('3u.fm/DMqe@CU%M1l=Lqc->6J^W;cFVmT383MP
1*Ku&A\&-'sSW`oKGcc]AJN3USB-am%5#p"8m^H(YaL3F*iDWOpV0m*R:G54:=9KCEb]AHOpaf
:1)1j]AlrYjfY5C]A4ln*)XiqLr>,Qk-?U-OS[@/,^&p<CRgdU&&Rc!rZFQZ@$%bhQ1GB.I9C!
9W1.LK"1:^LEu+M&R8B`rl"I?lE5!sd!Ui!6%H[AE]A)W>EBpmN/U$*q'g#-6#?G-D9dh5I@o
gI2g?QaikY&:>L[hHg!C[LJn2f3@M%YHeKM<Q:jC&gVf7RZ<;d2.cX@8p$<Pk1-["6+`HDS@
`9l4h5P*a9G0$X<$dXMg-O,sL&_'*`F;/>,K[RZ6K6$QHiG.$i7O2\i;o'iI@mW09.Fq`dr2
ITGI;?FdAX9I<6<C'KH<PP!qX:H0e7GEGhd/:-GmiKMCc;Wb1PuDHN\5:R]AO_(RK\FHiE6(]A
,LCM'2D'fGcocVc]A_K0XNC8r?S5!UBJ9^*rVXZ`oOqXUR'=?R0D<Lgt'df>fr?P20T7bDh9%
t(MWe8B^"42(D6*59[iQM!(AT=k(lJ]AVYof-ASdW=Ob&']Ajt0<dlJ`G=qU%"G@#>Uc3>7TJ:
BBaN&,&>kX=XQLi/CAMP$nqU-rN@a9lrbi#NO.e//(Xq;&jXT:UYbWVA'\smTBdJCMn7l35c
TOV5c3P`W`V^J9O;$SZiQ_8+&'[(*#+Pa^^?l=QKn\F*A_YdD]A;[g^ER:r<TiTH<G,(Shq(]A
o8T0Q,,kH)uBq*jfMC7Rc6eK"[t.Xq0[m?8l=/M"r;B"NdO:-s')1p>$Y:fj-IG&Y(o0!)O^
+:SCuN>Z2#?7-#ZA]AD\9)V;WeRelf&7fA>T7X"Io@o1s!&CmAbT(\!^^s!7S0aF=4DM:ld)c
Irt#"CPCLjMdcF7>E(%g.%)%<leK]A.mG(9jkECRTHumf:>dkjnT^.1fmoBqWa&\!Wo3q^7EK
*[#3T/$V.r;:%)=Ef?XK!DD&JY#n!,pZn4.!^R>=ME(.NAoG>8?*g4kHXra]AYWA'=`p5YH.L
hBIj0""B>FJEQB8jo[,Hs(TA;trH$.nIBS]AXXpt"n;6*6/$jULd7p?Z-5"ZfAp2oH.DQP:1M
boA>n=%YD5egGA^YK1ut%7#-"am\]A;P?o[8AM%t>L!FF:%EYOhO"nDDaQ%UEcAPo^8sg?dfV
2*^d-/3_+`d,7?WOMA-657NA[S0..r:C3\BW`]AVfYIfh32n`rWc2"m"Ln\Hb$)L;BZ7qbK20
)IAJIn).c6P`dP#k"r+SV)RSL:ea5"D06@3dYR]A">A^I\4=GrmYdi`502he@he*4!JKQ@M37
e6*,(PPDI]AT`Wc)C%-6&CI46:4FUM!V!W1'@S@YIfRNfQ*1ZNZU%G:Kcq/d2A:T:5fq+-;2,
GkUTU"cCV4K*UFgj>>H#h3+m=:=&<3#gVDFNge-c:8Yj/"OkZdl$a;9HGL1AD)/68PL[1Dga
5)Cg>3[)RIW,.m!gE;dJ[*4g>t\q6&QFnJ,[U+Oo-U,"j=C6JU*l4`nFZ@:p(9,W`l!fj^JL
fga*H\.s>4)JRY&(_/b6H(A:Ya.CX\**$5G,&b9BA<m'dCash$_^5FWd0D[:MF@1+"Sgb\Ib
q`?h,.FbP5a+]AC<6SQbOfG)I^../0PaLAOa4GS.?U6Z"$0#0Kops^8hFpdS9%k.QPI5>k&i"
DAHTV(>clRA*L=LcVDM4T(,6fNb?hUmQ$@+=qXeN1]AWnJ(g7V$?M!-kMVBY*-j#5@fWkalWG
9JW3oMh"-b$JPq/&+qaD<6R<V5A&%n5j\rBL0i4deuR(\lg-2fs>K#7nUiC-PSbe1VeQ<6r;
Xa%&l]AA4"c]A2/c5EK<SWW7nUr^=NIc3?Mn=!aJoDo8h(&Z_=97]Aa#m'\fQ/,$bKdg@k!_P?S
mB^*VZSpqd>N9=[@qd(a\-gW$(qaU.c6.Jt-NjQ#$e@?(d;6HHXo^3cOZWeu)IGWV)I")/S:
.jZ?r7qBNM)4\n>TkuC/gN!brU,-AdF)M"X\jq)%tT)Bh:!D#!e!_n$_&4NSbtChV6H\h7)-
j]A""4SA*F:X$6-#.g/rec\@5.b?O+t?Br-h`hiDH^lI^JVNBODa:3a<ECh(5WHFmaOGB,G]Ai
b3Ea>]AoUT5U$kRq<a!rkG>GNi"eCt6]AK<tpef&SZR[<a!\SmlK#4lHoc-.:C>SCB!e#1A0E6
7Cg98$!j*C?PjsRd$pRVM:lWd1on)o3&4P'3Jp4R;**!M^TkQ<?NH+R;L,]AdgM[MLQ>.3QL?
k%pnr;:`]AJk>k3NTOMEJ>MfcjnMpbF)>`h^a,dhsZbb5\,2Ph@dK9`>7;bjj%W;WuQt&Y&Dt
]AU_27EB0b,W^:B"V^Op9s_<"L4kREjgDnkP^4&gQT.b)AZod\!jElp#5WPIoWR<o["k[EKVt
n5PRjhG[rC;;SsU:nTq/R7n0kha1.![NYP=fW!lU]A+'4N=F9jL)"iY/[Ck^Q!XV7C;bb76ph
tgS`=quc_6aQJ.pDrRa]Adl6h<Pa=C^d!5+UThU,8P'<<I@K,HmMJq-WAgA%JGNR4\VM>)LT*
@1hjBRj-"#o*^Y6MOl:]AYhRNn-jm]ATT4h:ZRT0R4)$+<fm_Xd58?k-b9[,uFX%5f=6a'05O\
B`qM:SmJ)7d^Xc/AD*!EBXYHV=m14J.QRONPlW-%F>ArfLf/DC,IK4pd"'BrK.ND[(u/+?:.
GUgWL\<+<3+^4ECCAE1*JP5`ME"n<kAejdQZNj,uE+TipM+!\Yj.HOh:js(hc)3V&c,ph_JV
)hm5WU?7+J7H,@@&M';KIif)V!H1gCok^5eb(2j!)]Aj8t+VD=2_EL/ZYSoAFF%U:mdpf"+bH
>qTKn01snOJJ$ZYZJs;G$T/iTUa,IF,`&c2eaNj.PhY\43j>k)?/9e\j[9fr9YXDIlED9T9V
3B;<$gWZ>UD)f&Pfb[I8)>R-g<aE0Un(Nn>N&GQkd'HYmQV;ObPQ@,&]ADr1i`f%[)m?2,$=k
iLq).\44j--.k4[T68ru+C:JSc+.>"Bl;!C6u+<$DYuFc6OEJW;F23GQ[Yb6(jsZiKFT369@
;Mun]ArGG*PR!cWjq'Ad#OhcP_G:r,$OnmPuD;MIU*aEO!-ip1)]A;#"a!Q75iE-*L^^,)k4L\
[)>UZeZabm]AJn<`/.@N=SLe#&AB;N,b7+E4JMt1dDS0bu'U3HX2O-/bOAAu0;"2BELXas>3a
BS[5/cMj\fO<pD?\8^[(>Us=V#75mZt2<#Es)1fF_.o4_:mQb7st#]A:RUZ.qt1!ZiLgO[C@?
Q@r%B$(Q/)r4K'K\8D4WApJ:#`A&'RMLguS!L5F_H1+aagnlJ%boR/&ReeX!P"^a%_)BDc]A>
#37@u\rkM`RO^sNg`Ks6+<,(qnB75S8#F=>2]AS.AaD9nPg<uXEi[btS:WV054^N#-GpHt23\
6f($sBknWX\DT1H?5"2p3!rO<QiB]A=u@T;g<*"b[1tF:00E_=&;KOp`Wsint;iA4X1Jul'Qe
D.n/^V.=pp&E[e]AT\&Z;$hFoi'<eJHb&DCqC'?9a]A&;7+`h'n[Pie=eU%t$PtjS?8MA!9Uh$
&H\tZ6B6^U[d1/?+t3KNRrT_*@U/lDj0tGE$NCe[1N@)oIWjcPgm(4m1"j1%HjRpY(DlTGWX
=BD=TlTn[oNB9NkfSS/Yf*SCW[r7]ABPLXY$f!&9>6Qe_8>t/gMg/D'f&1,q=PpK&-]A<ba6+`
lRXlJfg)%S)REOVJ9a9Em8-C8Vjdb^k8hPEp@b^$BD/E)5g<DApQE0ih;jZAd2SW&b/TX_S#
a)JPt+JaF^a^A(flN1MaXD=pS?bm1`J3O]A=_2C\6u"<.`,E`Aa2JbHC*Y5)+Y:;lF8D(38MK
#F:CW`6LN\E<UVT21n+]Ao3i>53_G3Qehq;AZ?C4oLhE4,4g1=YH!,bi^jUh\R5<neccAk1gX
(iGpT>gIm't1UF970"Xl[8*)[QF062dnI^_D,u=_-K23.Q%49b33Y5Cf&YOap?n.(pQSUD72
:PB2WHfU3MU%MNA$#D_D'a\B`8i;&i#hg"FrLiq"_8,U`VEYo4m2I!oNcH0QF93bVR3g*?N+
4r5'YC#P#>#W"b[F-K3/4!O+A>GINm%c1:/AR$B$&WFfAf56r&@Q<kkmToAV7qH$B#2a..$/
b^DVa=NJXufDP<O(3*<lV\b`PO?=+YFJr(Bj^jdMZilLiZf3%t07-p]A*n*i$ZpNKQP,N%D0U
Nr\/*rq)(?Z3gk@1ZL)k+mlZNPhpQ!6Wc<XL[$T<Ga/BOK.`eUrRFJlu=>TBU84/S%@+t/r)
-*DA%At=:Fg]A9Li">96g7gSqMrZ50(i`lL@Cq/7`2!9AY6n]AX?OSDs3ij^Nc0@Y\ZdNJLkOQ
[[O9GB,`?bQ-c>3@O.8H*sig%StM8\(>8`q'Er+K"C(?r/opL5p_%?9F(BNaHmish7UG'T<E
6@P:R3Al[DH.tFEdDb-HMnL'Fon3mt[.&lJ4lM;igB?<[l#qW8NR4-JhUDZH=V2+W(hjb162
\F%H;1sLP$)q=!:.AAi2_t8C94MCBAM\tKaL';r:<]APLWj`":`[^#qAe2Oib8BT$m%IhWi)>
gi8M$SjEN'D+Wm-lRTo8M^P?-l$lDfL0kn48rB9h?9AKG>a?1+7CiF5Qh^dD-0lC1"_pM6G6
=Th/HqpS(jPngS0TpV9Re*?'r,UImTVZWHGW"452(QTPZkAYBLq?:-#93CK&D:MV8N,.EZse
f.@?0,_p#<PalV"nepS/J3VS^79;XH9B'g1;jjSl]A\V@\_W+_sASlI[qO51,=r+On`:@;\Z\
6FrM]A,<MiRVbg<,JoL&7CrItD[qb'/D=Lja\k@)P`N`TX<n2m-LBFm2_^KtBs0nF:a%&5\G#
Me>+(ef*Hf2euSl^-Ne7nUPPJtG#h<)uq).kpm[@QQK3.+;2lNL*)-X8[*kuq*t5+2LT&Cs;
">tGcgmUq1Oi+EV.O&:,fYEZ$&_eKk&T#B:@0V[>"3"pY*[snffVlB.0_5f2=nY`BFp3\U<-
*mhD"q3KR?`O.YS?L)sQF&4^X<s>MWT(Sn^X=d43r\'mc@,`&CSiV>+$N[6*>[m9dZ,3OCr5
0f$i_,&P<.!O?ItR4m)a?f%,9\1ZaKPQ%Gh"r_Xls/ZhW0g^HhA12NK257IO9_bCCmJomcaF
Zk/([^AX'G=F:Mg);E)>E+[[6VsaGc[a*sqSGC5nkQ(P+>k<JSJ`-o^0QU6e`dG0CB[h.q95
#SJO4#Wg6kePeOW0=?kU?TYGUq@`.2,spS:N(:F\XY&02Co8,6Qgd%K73_D8c)FoU[(+XMRX
M&]A&]AH374]A[?1_ZHW>EB$&<ohB>Y'dBHcuEWWmK^j2feA6[ka$pCluse'FbA4bE2IN`?XEkO
]ARC3-S!m=Qpe-7oH3/7?0,PQ+kYFpWT.7iFMU^<C\0HrL$*<)s21;56A%S\4,b4?7"L$UId2
XWcNq3X@u,c*RhB_<%/KhjaIL5>67(6Q?@"Hr98dq`!8Yb3/%Y/d)$V0e.:JW*Gl2/RkE"Tk
l9db<90Au=&I:JlFM\!iXpKfaG-Pts-0.Vp5dQ,U`0ZJ-F/!KV9hS$0m)kDbIa:=k(:TU47u
3Y+g@&;,^3Crdp+P21Ln,u?N2')_`URAp*Y1JD0><FW-TW>H?J/>_na-$qi;V&++Z^^N9n/!
i+saq>cK8]AbXV59g+">\M"R?ZQPPrO/ptO]AJmhKVlfd*$R<toSoM21lLKR92udcb=YfiX.!&
Nng&m\4_+6@<CH*qh2%2hT6o7\5d4DQK?d:l=a\QY_CG_jK4Ql/lfch^9(6eH8Fr0YP$phBA
#B[7^EAa/BQNM#HeBk88g5\I$4Gh_TYp<d*^5+q\qViVkLFae$lu=8jgj\I9D!%^@W-b4Z;o
pPEC<h6K68Z4^1k%/iZVe>:J)`b-jYWEa?lI-V#2S%nK4eq_fr9Je$qm=3Jqitm5bMkO9;5/
#.6-ND%u?Lndp+LZb7SP4+MbhQS7QI%O&pCm4`+Pel<Mr1g1AOdm6VZna*%?mg;]AaZ(Tb!o%
7]A(!Qng98Hu2n?`jbhjQQACChQMg$\a>-b3alJT:S[d9J3ZMO=qkcK)JMg0\O>Nk@D<IHSGP
rCX/5;)0*,-0_BoUO<;(3=,Ola-*jY%%3uMi=aPcHTB</';"+>r&q)da1'TWfY#Ee+,_)e)=
MFPcZcp*T=4"hc;eK\X_eS0<n$\!pGBHW,'Z\O\+i_0_#`%O6:;r$+;k'MUgWSrPf#t^XK28
.sdeo'%i]A.0O&+\E>/,['2NG69([/]Aa+tKjZT4e18oCT%Z@%;*MDmR(2CQ<mn<G,^!o_!Ws+
S:ZUSfMQED9fo52ER8K%@KlX'5I6+q9]AE[U$iVeS3JZ)*K'(4t2Nce1g"Y`G9m.3p+^Dls`'
(-N%nfrF1D:$IkP`NWp@X\b34fB54sGD6t639^@jXdDGu,PY>cQD7J^4joj$+Q$-c3<n_.*0
Z^p+)u8nga=4/-"?Fq$1DhpH'uH3PlHDG!S=/nf*]Aq_ti"&Qp+#Y"VGKCLO`5bfug/_kcLAb
,>`WFL*op_rh37FGfP_EF87d8IZDns_ANm3NE4_3Me!pZ!Bg_kG/YJ,Y`]A3_57!VSEc`=*2i
$V.(BdLF*S5qb=DC<*(<^?I^2_Ikm%?!%"5VoVhEU$^70qacQ_doBSjW3uF:2Jn*)/4JeVNV
m9F)/i/C2r/sAS6PCBLHbE503H]Ae@f>mm4:6*#HdSW_nC^17"LL(,]Ab9eb@kp&lOdJ-\j0+[
uYf,.^/5Z9a*X68$Z46>O@E!aak<*2]AJhlRQG+O2Pj87Gh.[OTeVpfbEdE5`6jhN?qhsHsP7
@>7!i[&4!ZEqeJGE91ZQU(+9;u+oHo<@4b1O9]AQ)FE5T^mU@Aj3bUm-uf;l)+]AcnH.V`\9h#
tgleV4&WW=J(M]AI6]A2.<Wj02FU!B94SA?Qn27gK3I17ufKN=*t+&'DO,*%_o3SAW`kOoW1k,
mmRY]A<sE$Nh60U0\=M"`5L8VGU:`G6\?j;25SmF8kl,298EQD1#1?hTL<=cD@a5/Ll9$D]AIR
YVHQ\bZJ<RNOWmiH8r0kKRtq@.^^odG#IqGu1AB@XRUem2Q'@:qDThjR]A6;+9@H*2gW8EHQt
SLKiUo)S;(7rVmCq_U-Vr^$>8_;0'"b^jh<rX+.`c>cQ-N19m(cNA@RW1rUAH[6)'\hRQU-5
kl*CSj%e,0,jGYrNB@;Y7q>$Vj@A;aS)H5OX7@V3.0;RZGF.V2m^pAEl1!\Jm/^1#.D1'3>)
toi&-LDcZfI]AZ_*I"^6C.7rP1Z8I,f^"OBSgTQ*3oI#$D8m=bi:1V'E<sf"NNL!_p@hLMUV/
@W\@#Dl2%Zhm8hpK>"P(=Vq)1)G]AkVNOXM5*c@C\Ad%hbo7`XS#TLiZ_'6%<-eB1Gmn4:cMS
Et>8`G9'(TcrO9V`#_Y2igp<Asg2?/p24EHI`"ksfjc14I7@)_R8mr73lDDF3J,K^kKc8B18
LD7!5>s#U!9>rZZb+N78N<N-es9=_rtb^Z"6cPK\BDmpr6NYZMmJHkP_4g1$uE#BKUF<CIr1
rmEpQhEDuj@SIBaRN9_G,r.(!RNW$$J2.DAd7(;J\+`b&lLeh]AEYJu/Np>(=c`-jqA,d70ph
q'OXXS),E"o8Y-jM]A,N#OiUmBdg$YsV8o&iZhVWgn:EEN7nf]A'J!Pp6e&Up1;:-rctkk^V60
8O2L6Z(l$n"gL'9fDmhSQQ]A(M\l@#<htF$L_%17bmfe3b>/?R/@%dOJ#>IIfKKd#MQ%257h`
SRBff<eXs,N+hip,jM]A')`gh*(Q9.fgMXo_3%cmj4H+]Ap-(up+*=YUjM,*[[pkU+lI4(f-u;
:D`u]AiYSrp\W+.gOS*]Ak6s8LgdCWI;<5e?]AMm-5#qYNAj!`/L%K(D"CL4]AaA29T?>F0'XD#e
rF;LElW`h.Xs>:$I$otq(_q5r7Z868Q*N!ef:!4r&K)7=u<F,WU$BQ_Su?",k6e[c-OcWFWo
_mp$WU4Lh\#Z0O@s7U"rJ@IQ=.`q!c74m/2cW98pYNhMCr*\@5SD=3PI+\$:7HKJ89"@^K!/
mi=>+$p"Xu,gmpQl>BXMQU+1n?<K@Za-b^%h>>ZU=S[BG&,3l,22e=#W/jQDAHU&k$"Lnl6K
O8m@0["933[XB&>_?(i?%K#FNLuZOS?>biYAP_gq.6[%ZR4)K4&UpA9dW2c%>"iEW>FpNu/\
F;Nj6<(7ee!dJ`_JNU9Im\rRH^d\3:ML8/$lgP.%i?#Q3IOY?mDoCM?h5rFr@JFJ;N\;eK[/
d"*EN:TDtkXt.6'5$i1HA,Z>=o.QI;fp7!.@%=f;g>OUKS_X@T-ss#)n]A=E[\,t^QPK2R&r6
qTm#&9p;jL_?]A"ip'.#AaCEncCp5%lujq$.toqXc_4$_odXP?KqnARtGn)/aj`CF'&`<F?fc
c<s_*_8L1V0'MJBr[$"]A(C*jM1&U5fK2]A+8N%BGiN>a4`<M,a%bj';#QXTGE%HJjYAVp44"I
o;mOJ69?B-ZRi^FFVmTGI:q93/Zo`ArXsIi@UQR91ZI?r=52o*4u*P1@`&+9'72-s@-[@#Z!
E'9[]Au/_[d'%(p@9rtJWGatnWo^3GCgT>F%/0W!aq_&E=9r\':5G[Fr^>oc;gMe/DrnF?'h/
C1h-K%^n-k^CE/8n1%u)A%4',W1Fq*#kUap06!E2a;EK$B9=I5Sr>M#[0<,Q]A_?RlcP>lgr/
hLejp[d03fH$fLa9_3f-hHp-ao<+;4^`N6^X5op9Z5MtbUJ[gcrO=6/?\QMl/>FN_+imDg0G
,m!k6G(n,eI%Uk2++sQ:T%VRp^XLL]A+B1Yo`\]A?(TVUG,bos@A*`(btC[W?\LbgR\(Yjm-I1
M>SA$T`]AiE-$>pls7Og#ltC7LrY'W>C19nCVRSk*CB/.W&%?#fL%0j<+03H:36RN7%qt5!-$
1nINb3R0cr>L=dD8';&"W>3Lb)"L\BkUV,;%8PSk4s5@-_TkX824C9"a>.)W-U?s:Za(h[i0
%;[[2c.-5GR+mIf5e2_>H)UUr68FQlV-q!NJ]ASF0@P1IX%S+sb8!?(PJ0Ao6TSNOFfF%<)Aq
c%KnRQ7^95kS,B.]A1r#VfD"n>ri/n!gd7;L1l0,j`;igmO1(th9ApBt'hoJ$dVQi.Y<^n3]A9
1/EA/A"H0ic1Y_7'cOSL,;Mm(DgD"VAZ^Y--.AdP2K?+lS$hlgc>I]ApV"XH`S^P9KQS#L^N\
=9TcD->h/_%e^KNrtc-GpNRCI/N6eslo?KV2`ans29Op<M%u`c"(E#>ktP[Ys#W9)J^e^9R?
2b*aTA`ENG',XuUd\UQ6F)Ris=>d;GP[Mo1#n5Nj8rpa-k$<8[rTgYZ3lBHN5+[&7Bn@(1C9
oW4fQrJsX5'Z*`aC!.@ZI?HokIT\W9't-SVJ]AOOB1@Pu42/"0+1Q$f6&[a?jgp`><p1:W=0E
(5bYqbtV@OM=pC6[UN!icQ^@0iZ+TJ?;JuA^YH)Z^<r7V'(h(!Gj_D:KCS4UUL"$[65i_df8
DBX(RO4;`dbLa$k7i%iI7)cKMjib(IEUmZ8:f>)?0$CcGru3==?OG1PYp'mD-##6i_Uc($=t
%Bd98hI,-038X$^(U[mTBo>VNpM:3L+"Yk7ZID]A,$"lQVhK*eORZOjVnZm*\!U\"i8DHaJ#l
SlYl%spZ06a8Uua:Ua':tOW!k5U0g%hZRDTFB7>7Q&t-*@k*Hq,V/M$mRC)@^h9Nb&2RPR@O
@Wu#f)6<4O;JLpm=gfI%[tJa*`NV1=4#`#p\AT.G>G4bEmL&>GPOC7W3Z9B309q-o7A-nEcV
T6OgV$[83U47_j[6F`HgHr8;$C0Mm:\`h`u1G&Z1mt<?&i`.p6NEEQ_t4>)Z]AO\>HY0s*,[>
e@o/Cc3d(9m_D'n.'f#t!G#AhJ1.f!>6u-0^HB@JWgk:[>N(jFOY*ST?S&*amf8sn:?K^U+j
(hlGCN1<?ar("_*a1P9"6/?'pNgD2!GNB$Q,P!eo0"hJp>"\ah(Pc>oph5;ja@lk@D_FaE:t
pmTJ&.TqVP_rZTT/(ZZ$)."cHW4mSs+2A]A]ATieAhEa!ZAOPp\q-_bG.^+8F=EYlse#3k_`o"
-g=$S]A:XP=k/Mb]Ae\KKf[Or&Mg^uc;rIhGNTFaEhLqaAFE5Hh1d2S]A4TgH#CHn\OgOiOCm!T
b3)IkDFg/j5;"NG@(Xfqo8qth\Uam2H<&q<`Im7P%(nTrP=Mk4%39lUdt>f!['X7TFEm.?H)
gCX#J=O?4bE8[)W4).Igm/qQghMFsNi@kchR4EmMi2\NJer[^Co4cQV$aALM:9MAs3R)st+.
.JnotLa=WREoAeZ.r,)C/YORg)jND)CTP4:Dap[VME,%GZofZn(dS5/+]AB"pS)AkmBb=romm
.;1,Os9GqnqHNFld7sIVL(OEJV[XZ%9cMW#TYj-"pR&d"$m<QlKWH3p+/[gL!ad:sI`clfp7
Tk^Dj-2[qTV"D-990W*T(XuRAjILeBTD7%HG%N6IoFA@k/O\-;6dT>IK)dsm)5GZaNH<k9CD
Yr4&8EDTqB@+7/LsJ36[36$0aSJ(fC+2VOYR/cH#'L+uN`#MQM`jrYK!'0>sSIMb]ANHQ0]A\+
jh[SdU'3jrkKbN[J,U[?gpJ5WiC;fZ=u"QC2ImW:c>id?Je<$Y]AO6p$7*'q6,[`"U%M_R:UD
X/XIY"g@jFb9R0dH*9jD72AF_UR9)0tfmO*^Z?lY6CQqsNrQ/[uY4;]AC/T'dcp8`<h24SE5K
;ClN@l<'p#n%(_N1d'?TIqS;_)NcfaK;E^ZP*>dJdR\#L($.6s??R.VmoibDD+5j`8DGFN5Z
1\YuW0Y'^^#\eA9Y#;&@5GagH#TSt\?%,eAV[7aH'_F+JU=l;Q)0K%r$n9,Y0tQ8D7/m#lBS
1W4]A9f,s!:L*n)Hfkk-F5<kIYQ^kU^CN_C/-%poqle>HB]AcQ0!X7?(Z96m`c)qKGs]A\qVpb,
k$bT@kt<=kcEBG;'LrhD=R><1la+c[+gGftNdpA#PW#2;`7#_AT.3,?fq#iVfV?g(_<%0u?g
G(_/.nK,mq%mG4,2dCat!ed'+b314ttt<kf.OEhgN7=O8FO'B;;+F:\h[^r62fk4e%$_Q2SJ
]AfhteFcElo$^/on7+Y$le2Wth?ZUaQ$>Se_Vl,[`P6W`0R<M:^Z?'TMC8/NLodbMBHXUchbY
aWVi>lA!\L"5T%cTX]A_?QUnlM-$?E?o*G>HY2K&I'7'ROfOfHaZ@aHgr1uu*Y>WE#*]A&5hWH
4sk@HP-NEmL+=091(K5nZ+n%m!@%A3$Qf5UCYe?$Z:ntScPF9MinHpu#FP,[;Pi.YoqYU_38
5ScP3nQ3!Z5hCFRXic'"VLH5V?CjO+(W):!p(B"r=&c%cT92QtIG@\f@?W&roo&3uC/F6"A9
$mUC\5-7,l_#*k/D]Aj"G4V*HqpYP=;#9[$`dm^(-lKh"g96jqS_V.m4JQK*F=<G+:)q)4Og,
Jb(\$PJjH"UN'I,:EB955,q=5('p&l%;P2+fdeL0jdX5Iti4Kif;JWNk!PJ?\E-u3om^_:T6
$+A]A]AW?,^()a@3N0bSjO%-g=_u#`L2(N_C.EO+*1e,S\rlCPC,HplDj1gkJEq$3VqTou\N08
:NbFU3Bg-o^]A7udDZ?S'5jjI!!3mia<M$-LQr]At;6+33OVM8E;aG%I;/seRb2Za=^>4l%Sq3
(>SI"*9j>5,iO4WloA)JG;fg&MstKVE\NK&^Dg5gjont&r]AZ*FZQ:A*4*bC_KS"`/jcmmWT.
rgSgJ''/0rVX\5FL709`JMF/Uo!86)=#]AKMLi->F!^;4o<SZ:sT:p%^sem$P@Y=&?dc$G[V9
^mYFY2ei%5`\X$)c#R#fb^/NlfS0m"Qk+E27C$>7=8POjW$H1'B4-ok$$@i"ID6Dh$5<SBW@
f=6EqVG,=Ha>_'W[u#XP%.hZo6#I8,2Y@'b_\i5-1e&\Vd?<%G7^`l]A`_'bm$^`"-NkT7raO
;LJ*'Tu&h5U?+DOfak%;W(g[,ms';!NY!^56gmr8T1AT^86S'+BGL&_-(kmd0P.lP5@7ZKr2
Sp*Tq^Q]A48F:))^d$LBQG?\(!b".@Rmo>b/BOFZ0[U"M!/-/oRq0s*Y-RRkeFHD<amdm@kCF
,FpGZQM[&[.XZn*acjmRf\`6qeKBk:dTZoSQl2/_GjO!Kt7,fDs@iBt;!Re1HsQio&nhqS'h
ML'`):KnA."?9L[</FI<tJ,#d08s\gA7Aglf;*h8U]A2_o9`Yc4D/Z@0$s3$K\&!RKPZs=<>O
7O]Ah/h="&F=0Zm*UlkSSRdWU6B+&%h'IeZ,uHnjlKlEQ89`oT3@A,@Zak!tr_)5K@R2J)UA3
.i";b$]Ae0DmI&+s:RJ)TVc'hi`J\p(7/aqSm>37Jj3rR94Nd_r.I4TBm~
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
<Background name="ColorBackground" color="-4144960"/>
<Alpha alpha="0.49"/>
</Border>
<Background name="ColorBackground" color="-4144960"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[m9P'<;eNO'+_[ZPYf#k)O+a<qBt7/h`RZNBOJG[l>00lb$arTBm?h',$K2^B%rBj2eWO$PV#
k\EW2V#)[Zhts,Zl.F=q-@Snk:pg";##b+n+K`TX1Y6c)T[1eo8=@jH$lIh<1<sZ%)Z@g\X'
]AqNmH-3=e7G!.l>7QMgR?2\9LH"$uFKbaOCe+8[hf<sZ+0GKJ#]Ar-)TBp&7rqX1Oi3>lJaIo
@rKPh1`1[:R>DbL:N[q@F)?,Zc^UbDG,E2o>0AtCWinOhg0,d-[bah^SeAZ,>'gdTd]AX"'cp
?*E%&r_.2hJ/YB)hPX/%=7%l"%JAZ)l<*q?g!?>K!ej9d[9kjOF*?EPq*'BTqG[<I,3j82+`
O[3NBrSHT6EI#?gNFX:(GC-sjZ>)X]A4-%d<n1PmM!*/72P.e:tOiJe.NRR!uY4m6$?GYl\`c
@g6pStE'Ho9Uthg"k[EE`"/$^0<dq@p;.QBh&2D7QFgh.H<X27NTs<)Cr*H#8$qDkM?NqSJ!
kkVcVA,d04JYPM1KV?QkW("CBKfBqu\HpX$A#4+?gTN6MGVjS;m$)Ip;mNCeO&71+!6Z6f$h
\m\cADd<:BM3F>-,]Ac0#$<,ISr1A@ISnjJob1M^;/3N)V2M.5:Z]A1BeADoeX4V^;90F31Rm4
\lT8G6KQf`S>>"?9e:l5Mt_31T&m(Xp&-+e"o+)dp6?isQM*Q4NqE3DqtGc#rsDn]AaZ_L@W!
f$rC$\%>"aH[d<EG"$P/;B\^UGQ-Vs`dNtIC`K4j;^>t=kJLgC_\8%Sc"%`T0p$lRRop?kW4
o(^+7;[Dc&cuMMg,=-DjOP$(c\Bq'0SuhNM[,(iX_[]A'/-2Le\D!UroIg72R8KuE)Ap#/H:T
i2a?#4Ei^7)XGd(O\b^pW/%*jfr:YnSlck\Mc/`SVRn-X["Kr&73(6c'j/`]AZ\]A<*Zs$fFm\
Ka6`1\Wc4J<r.($R1]A_NC,.`I)d2?d:b*@q,^Zu0bSPKVk,8M`*T:9UHd7_;S8Wb9.^Y9Q;N
]AEMi<(@c/tb/&XuK6^LtZo%%d_Pl0X8n_s5u(e#)X5A\qac?<)h`3F*^"XmNEU+%nTgZYs4m
:E4;g2msH]A6G_UmRnS5URZZR:p*X1;@@6)J.<$hSdZ3e>/),+%6d+@.lqA;X`8A!Vf$m*GFj
&idNI*k%.JP+@<3q@'@WKYOM&,+W[ebEa04#[lBhW,JbP!o()h1Dj:VoXO`6%f]AfDZV(DDmI
&V!(4)HHBeK_jj?V%DQ:S.;c3$34P"b/Q\he`\/lGO6m$nR+Y>X@ISW47&J2;@TOGbj$m++V
6P(a[GWA]A5+tuXGT9p#NMcGK!D>/Hk_j[KcP"1Qj.c7#]AP&2"gikjVHtqg^X?.*49DK$OSW;
ulQm\9=SAuuH\QC2qDo&L"hZ6iOmaGbC(aMoU+'K\u'X&u''tB7[mg'SAJZ&+;hgY,,RZ..d
hmL1F=`CsVfCC"bH$?Yk4_C/@3qB$m?<F[1n8Z,B^fnbb5LL_4/U#HSq<G,H>@uA/hsJ4[HH
`XKX`p>.DjaV/L/Kcg21+J:%%Tt!A2RBG<=q74ast`]A<TaD6RVP9289?%C^(XsXd>Z$UYK02
E<OheT4!)^r"iub;+%6`"%W:KT(s%tu59hp9*DjD1$Kh1#6a+`_#I-JtZO9@7YoWNDQ]AX(X9
@PX4dsh&a\YVYh`Lre4!tTJT(]ArRcndQNMmSA??TtVE/E*>\OU.:-ebH,ssJ,;63=W6//h7S
g.ZAj-rNV*m514g>DqZL5:DDu/Ph!JBt<hoP7oY<U(or1T"^Sd4mH<Ap'9X%W[RX;K)B.ghT
la`f1>6-;3/K:u':itK/78+5gZ'Ku:@AqUrh5#`AbkT00(VErV9CEA*Rt*rFG[Y'['e2MZZK
,TT2jChtQOtA$0`ul4\qp(M>qV$kZ!bT2gAt>=*irNQYkkiQ_6aeS+!ZSg7jb$'CPmQE0m8`
]A_;<D"_mRX@a^QZh%*%(0f6I[):/[8u[*>V78cUfP0^/Ih4O>>\>Vu-X%Cg!G4PT0Fb6b2O$
\3;oR3,%"Ii2DaauqV]A;^:#_[b6G<"euTU[.^ud7'o-GHkoOgYcqWkLn^%d8lIC`>7LZ>8c>
DiVIMSk-@iBq`XJQ*.T"MHf##hZ@0EiW5D5*YRPuGa[it*-=7J_#q`N4E3$OAu,$:,a$&jmU
*E\\q#J?WV_S$(9SnVc3DoI1)X)E&N%J4o-1ifW.U=<GD\N`a6>C1m^!U;<^Bu6$k;mQ;cc*
X/&7m$6="!IB[77"trLcke:q7W\G\m`t+f>'etT3j2dQ@shcop<arQL3TWCg(!*?4j3q$hX,
Z\+uf,Jt/9DjATLXUnPmmWFm@_K]AHq'W$PD<n1O3lKPUA<FF=<Q"sOkVk"V^G9^n#09%5fjc
(MVq?;Z%Z3;@kX9.06*]A\b*2^]A\m>X_US`""q\;>Z-F)-$auj8s0W,F0Q:X(A&>(KR:t0]Ak^
72!I3#nJcV8Xd;dbgg*I;J(EtuUkFt[]A]A"m\n\#T)eD-XYB8&a<E24pV6]A]A#n?\hdc8?*9mL
j=lsMHK]Acr?32I[:L:a6!4]Ar+%P+=dI%"nQh,Hd()EdNeoZ`dO$*OR>\c>$_3]A1`/Rf=KuZE
<QEi,p(MGnC2Dh,j*Y_CHfcjP(hE^S6o'K7Zkb*4+q_!DrRoW5c(6aR--$B=2XP.la]A+@ij;
gMLUQ0?Oq_,_.\S,o]Aq9]A]AtA.8n<%N]A'MEBL!d0MtVEe8H&)5u,(%u3Un:#8?H[<L%T:,4cI
?a;nc"5OE4!ECCT-fl8LU@FKI9'@7#,WO@b8MS9?lJ-I&>C/BBtVW&m!JMogG!`?GqOI_f`;
B+1O.HkKU%h?)j,UN;*8Sth3Os3F5%UA$;N"4%8l'R77TGdegg0Zc&Un241%TcLT3S9$$ItW
n-5g=OtmP7SpU0%)9FA71j79K&Cq'98*Xs.!#+c*9_cb$7TP_iqtY3.oe8f#Y+)+J*2OWQ9&
U%?0q6LE;1HGfEiEWt^%HqU*b&=(U"BYmof9/J_I8)#Znr#,VJP*dgV*ZZquoks_%Ls!5>9G
5TH:9f:+gTE=$T`\BWg8FGZ@fMW-BrC/3&R:NC31P0nS`4(`1gPL/OhH:_QB/]AL=K0"V9/+,
ISDZ4r-n%]A`bngoH+22PlKfC9a\ASEZ,-b0`q_$lTuhi*>O5`/9+(+o+m;=]A7[R0EF\OSll\
U<(-qXh,t'N(Pnb3qH+$6N.iB$uX9GtR;c@hKf:r*JX^8$T1sc&2f5JEAZl5YG:7OeJ4+5WS
#aS`+ASCTp.%\aUMU14ac\(M:E.j%2CE/&oVCV^8_ao`Li(IA63.*,+P_.Kcl^MR_V%M99Qp
[LH_1T@(b7&5JKGBnMR[-D*HQ;J0X[,sr!R#nK)`2tZ",&(lM_t0T4A5":Z?-A&S/OR'70k2
qe\M#s%TZ"aKhjC&l-"<OPYuVc?MEX<,XcnsgjN$Rc!%7<Q2DJ4/b/uJp_dH)\41G[dcc@@Z
,S\i0'ubNd<7h2;o9,,%af5j$d)]A[m`<<V4%sOIq6;@L;p9bJA+C<<kWgahChHcQ%Uo,5&T\
\bbBH!WRcX>`W!mFW5XJ3dhphhXcbL77#tt7*'HJ\0iagce9SgK'"Vt4^:]AX6@q%<^kII&5G
2Eh@79fS><\HNYAPfC@K[XRoB2>XT9kB?i/Q>>p^7eGrNn]A)!fa8P+(qTimSgZjrc`Uf=gG<
/Aq9nTn0Oou[0/Rp7Vg<P1sfq417VJ_Ht$uObq4&Wh!*q3=bZKWQ*ggDD&d_7_cPdFnI0EbW
#*t"m'-qY3eQ);QH./h&:)c,?[r8qS@.&Z9B@BTEA[<cRE+MET(Z:8]ANX,JD,jDDGu^=;]A9U
W7>bAq2n*d&\#,nO/OLM%"hak`"mS$6)\R;Bo#"=Y-nN_Q2IS]AI&G'D31ruG3Al$-GTc,R'H
Tl!l13VUAD`9YH?l#et&>C017A_6i3Rpl/Kj]A8WM3M+REh*]AT_b]AX]A*,4hN>I0ODt=unbK^$
f5+_!nY2kZ&(4*&/V$)`O)<.e2f(nK]A7FmFdQk:&(.4&EN"N<F1u:oFW0@t6c6Pp2cerKapT
,MnT-k@;$j^2kCa2[kdJ/7n3fV\fp`1n/bL_=HU-g4YK0GHJg5'd3pbH\u.SXjo@e\-:9ErC
T'O<OA)UN:1'fSNt7b`i7TXMRO23m2_!\p3Do1h3Z7;*O><q?$P^fMsGN++pQjjRKBAN[^Pi
3W@#B<AJOQG`L!fdCQNPG*(l0-E(-nrCitgCJirPl1bPC-mq4Ue^M1i+/F9H/qfOk%I6/2UE
CFS)c2BS<+)8`b$$AGVog*%G:OJ7IkXF!8hVlK:*b$Yl#icC+uMO`-!L`O'%[>,n$r9K02R4
CX!X-C)h=*=(g%6"D8ra:^:&CYs"LOArM"80t&bF1):j/liL78*H#5Dq*qU2GT:[6kKAJ5]A+
3bq=8Xa1;YE/:qKW8>4&1L"i<E!uY6uM2Ke(=4P*,?PX!S.1]A9"&,J)kVckM8,'!W4i-nm+B
A)[7gZdn:Wg;ZF2UeO^oE'36.W<3@k@W:8dQ@L8t[=(lP5'?tb:#Ma8ubDaIS)>k?0R6PC^#
I5nqag0Qf_[mDNFiK#1dGl,uc"Vj)0K`C+mdpjlV@*5qkq-\M79>L(=1_B2N<Peu[*\`k/SM
a`##5c%bM^PaYttP_'3&"T&oOcf*+OE:o*'ml*7F?CPN<A!94TSS:)D<`-EB\.3]Ackm!a*Ed
Yj*.bVMXfk8!Fn5.+?mr]A(K)uIk8+SN0e*P'8k6*"]AbmTW(@QOQ'%OY[&d^:2qBFOB0j1@B>
esOge5plm.'@EN=bQGYJBJ:I'45HJ&IXu[(u#,!b,8;ooB6c_>mXs<F"Kf./k5+NQU<gaFks
)%A+KCAP[-(6WfTRTb/D(VLQ]AiknZOKBNIYY,"ZcphnK6oY4e/3VjF!-^kj]AgXAqoMItmGW>
bkN_/kC[uWV"d9W947FI"6tWp2XZeea^MbVa(Oq/5>Y@quWT;K8Kcn944]A`,2%r?Qkc6FUK-
?M8!9#ZH(M2_8e+q-4E2D:##$Q-XW;Csa1l<,h9l(JF_\ANSr8mJ`7d3HKV<N0%/2B&`(DF6
$;f/$OYH<b.m,D%mQm64BAVOmp7h5Q2KNVbFF$f?UK.EUQ?<^R-W*;s2_ZUL"(u]A1hh/gc3[
OB4dtGbP5M\!*j6WQ0<&)c2ZcH'i<Aoui3bGRLcur"!p>fbH_c6qSLiX-q1167U3Jb,&Kr:o
8^t`EZKe7gHp$Cj]A\fm7Q`mkQ"m#f0#CQT-6UT!>]A$YEWF&]A_:,!3Q=3@)/%hQl?9q0V.57S
VEfnVoWL^Cm"lj!+"=^7QZo^h?Xhu2ish^6L)q:!=$o#l<$sJ3gRjN&)Q`iYt':2A7U@G!\%
VF,UpRi*^Re<J/bEM\1tJO1;k3T"L7PmLbt9r#^0QnE5E/$8&(?dWOT3$i:[Y,WMMDtHkC&+
J8;mWfIJ\qY6="3/&-4[eA8)t"@Z"X@QgYnHC[TeAR#46JE)El5]Ag>>AQ6j-TfOh\"2sX70S
'/>!:GS&3lEI.HA%kiicSR/O<GJLD^['B`aKed<uj3nlk/^&6cRBb3Yo4]A@L@N8]AV)VleK(4
s8l:4>2A,<#ThM.1$e0Vs,J7S5;:uTb!Uk0gHEZYp:?-"&9OJ\N8P6",[DKjQ'nW+;o6]A*M6
(R_b!lQ7*4crSd!^2BK$\:FV$c<ilO^UIL<ra3UT!2?\,#jT40*U"E!.j\?LE/4*Vb')T7?f
Nq0a-T5Z)7\n>=mH5Ql!D8&A<l%QHLP2+p,Whe(7AeHh0B:jg<,4-1[SY4$GX!n9l$Gl%_Ea
@n7),fi;A\<PTTETiLkOr354bgV3OBSD\=D$_Qr+9&!MGqf]AXJ"7`_K`Phd=eG".ddPQ)LUr
<gbI/&XCkR:4q#e3GHV_^&/-$afV3oH<+"Ws)k[<?I%.j=872]A$0LZccb-aE$Ba`?$I5a!18
V0D8s-@6^*0/__t[l98s3MUg&BbH>.DI\1ft\4ST-LX<H69r:6E@K(laV^?cKb)VQnM2[B!n
/gS(;A,,.?OU72=8()H/fDm,(0@Heb<Ijg>]ABiC.iuh=$6.e1eQ!H^9>$nNiN'us1FLXY_!0
i@Y!t=<PR2DOlFjo.P"8^[V_rW(?KE5`BaPa4RSD+b'=Q-jVIWHSMLOB;E*%VRUnV=b(M6A4
=$>:BrW<%#`?)U'8R4\WZJZr5DJA0/ASbqnEs4\XP^0EQAOK>`-*%eD<@kFd:NSN\[FB6o]A"
G-V%(ZASP?O7Xm`^V6fKF%&Xd2,kKcrX=2&t4@pJ\Q[[5S%15+6P@eRfD.Hj>\sM[Ui0bFX7
[RZTFcU@p!CRYiXtpjIkl\f9&Sn=Kcc.XXrDcC65:C(A2HZIoSP:E<[Q*#iX#I2jUc!AAUJm
3%<8EkIDA%*]A2hM<$iE(\sR0s"'5--O>ROEE8g_PmMM4IYKaf=.!o^F1T)V&hRL'//T[t.:5
trld]AsV-M#71HC(rJl9Jii.b+!qQ1W6XWaP>e$l-"-4YutGpX68_#.UoTS=qD<KSf[@-@2d`
,tnDpO69GiRnuYHR]Aa2"R4$h*//JG#rX<q21OES%CGK-iAB5oXg\F9>A1.Vk:n@$!&VEXZ6j
mRc$o*kmoS<L`EIST@]AkfP8-3<jh.ki]Ae`6HO7LA3BabC\364sZ<.:`PbRoR(DJck`3B%gQ\
Z='*Qb[bcff+H-J(80.BU[71u8Z]Ao%:r7<Xld2KYi4'UL54pQ.bZ3WCtqgN;K36V!@;dZ%\?
d4O'(T`4^"N`_Ao_nZe-d;l.m'l&@PaC]AC>eTBO!<~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="405" y="69" width="587" height="351"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
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
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16170125"/>
<Alpha alpha="0.35"/>
</Border>
<Background name="ColorBackground" color="-16170125"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[723900,1152000,288000,1152000,288000,1152000,288000,1143000,288000,1152000,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[990600,342900,2743200,2743200,7772400,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="2">
<O>
<![CDATA[山西省]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<O t="I">
<![CDATA[98789]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1">
<O t="CC">
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
<newColor borderColor="-16499349"/>
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
<Plot class="com.fr.plugin.chart.gauge.VanChartGaugePlot">
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
<Attr enable="false" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="112" foreground="-13421773"/>
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
<gaugeValueLabel class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-4539718"/>
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
</gaugeValueLabel>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
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
<OColor colvalue="-14058553"/>
<OColor colvalue="-16589831"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartGaugePlotAttr gaugeStyle="thermometer"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="false" needleColor="-1" slotBackgroundColor="-1710619" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=100" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=60" maxValue="=80" color="-11486721"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=60" color="-8598785"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=20" maxValue="=40" color="-5776129"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=20" color="-2888193"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</GaugeDetailStyle>
<gaugeAxis>
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
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="64" foreground="-10066330"/>
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
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
<VanChartGaugeAxisAttr/>
</gaugeAxis>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<MeterReportDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<MeterDefinition201109 meterType="0"/>
<meterDefinitionName>
<O>
<![CDATA[]]></O>
</meterDefinitionName>
<meterDefinitionValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=0.34]]></Attributes>
</O>
</meterDefinitionValue>
</MeterReportDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
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
</O>
<PrivilegeControl/>
<Expand extendable="3"/>
</C>
<C c="0" r="2" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="2">
<O>
<![CDATA[河北省]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="3" s="3">
<O t="I">
<![CDATA[93029]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="3">
<O t="CC">
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
<newColor borderColor="-16499349"/>
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
<Plot class="com.fr.plugin.chart.gauge.VanChartGaugePlot">
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
<Attr enable="false" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="112" foreground="-13421773"/>
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
<gaugeValueLabel class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-4539718"/>
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
</gaugeValueLabel>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
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
<OColor colvalue="-14058553"/>
<OColor colvalue="-16589831"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartGaugePlotAttr gaugeStyle="thermometer"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="false" needleColor="-1" slotBackgroundColor="-1710619" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=100" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=60" maxValue="=80" color="-11486721"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=60" color="-8598785"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=20" maxValue="=40" color="-5776129"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=20" color="-2888193"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</GaugeDetailStyle>
<gaugeAxis>
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
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="64" foreground="-10066330"/>
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
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
<VanChartGaugeAxisAttr/>
</gaugeAxis>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<MeterReportDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<MeterDefinition201109 meterType="0"/>
<meterDefinitionName>
<O>
<![CDATA[]]></O>
</meterDefinitionName>
<meterDefinitionValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=0.66]]></Attributes>
</O>
</meterDefinitionValue>
</MeterReportDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
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
</O>
<PrivilegeControl/>
<Expand extendable="3"/>
</C>
<C c="0" r="4" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="2">
<O>
<![CDATA[贵州省]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="5" s="3">
<O t="I">
<![CDATA[34834]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="5">
<O t="CC">
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
<newColor borderColor="-16499349"/>
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
<Plot class="com.fr.plugin.chart.gauge.VanChartGaugePlot">
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
<Attr enable="false" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="112" foreground="-13421773"/>
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
<gaugeValueLabel class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-4539718"/>
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
</gaugeValueLabel>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
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
<OColor colvalue="-14058553"/>
<OColor colvalue="-16589831"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartGaugePlotAttr gaugeStyle="thermometer"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="false" needleColor="-1" slotBackgroundColor="-1710619" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=100" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=60" maxValue="=80" color="-11486721"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=60" color="-8598785"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=20" maxValue="=40" color="-5776129"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=20" color="-2888193"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</GaugeDetailStyle>
<gaugeAxis>
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
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="64" foreground="-10066330"/>
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
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
<VanChartGaugeAxisAttr/>
</gaugeAxis>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<MeterReportDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<MeterDefinition201109 meterType="0"/>
<meterDefinitionName>
<O>
<![CDATA[]]></O>
</meterDefinitionName>
<meterDefinitionValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=0.87]]></Attributes>
</O>
</meterDefinitionValue>
</MeterReportDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
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
</O>
<PrivilegeControl/>
<Expand extendable="3"/>
</C>
<C c="0" r="6" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7" s="1">
<O t="I">
<![CDATA[4]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="7" s="2">
<O>
<![CDATA[广东省]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="7" s="3">
<O t="I">
<![CDATA[29003]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="7">
<O t="CC">
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
<newColor borderColor="-16499349"/>
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
<Plot class="com.fr.plugin.chart.gauge.VanChartGaugePlot">
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
<Attr enable="false" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="112" foreground="-13421773"/>
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
<gaugeValueLabel class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-4539718"/>
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
</gaugeValueLabel>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
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
<OColor colvalue="-14058553"/>
<OColor colvalue="-16589831"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartGaugePlotAttr gaugeStyle="thermometer"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="false" needleColor="-1" slotBackgroundColor="-1710619" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=100" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=60" maxValue="=80" color="-11486721"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=60" color="-8598785"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=20" maxValue="=40" color="-5776129"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=20" color="-2888193"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</GaugeDetailStyle>
<gaugeAxis>
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
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="64" foreground="-10066330"/>
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
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
<VanChartGaugeAxisAttr/>
</gaugeAxis>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<MeterReportDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<MeterDefinition201109 meterType="0"/>
<meterDefinitionName>
<O>
<![CDATA[]]></O>
</meterDefinitionName>
<meterDefinitionValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=0.96]]></Attributes>
</O>
</meterDefinitionValue>
</MeterReportDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
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
</O>
<PrivilegeControl/>
<Expand extendable="3"/>
</C>
<C c="0" r="8" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="8" s="4">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="9" s="1">
<O t="I">
<![CDATA[5]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="9" s="2">
<O>
<![CDATA[海南省]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="9" s="3">
<O t="I">
<![CDATA[12344]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="9">
<O t="CC">
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
<newColor borderColor="-16499349"/>
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
<Plot class="com.fr.plugin.chart.gauge.VanChartGaugePlot">
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
<Attr enable="false" duration="4" followMouse="false" showMutiSeries="true" isCustom="false"/>
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
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="false"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="1" size="112" foreground="-13421773"/>
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
<gaugeValueLabel class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="false" autoAdjust="false" position="3" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="72" foreground="-4539718"/>
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
</gaugeValueLabel>
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
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
<OColor colvalue="-14058553"/>
<OColor colvalue="-16589831"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartGaugePlotAttr gaugeStyle="thermometer"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="false" needleColor="-1" slotBackgroundColor="-1710619" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="5" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=100" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=60" maxValue="=80" color="-11486721"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=60" color="-8598785"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=20" maxValue="=40" color="-5776129"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=20" color="-2888193"/>
</AreaColor>
</ColorList>
</MapHotAreaColor>
</GaugeDetailStyle>
<gaugeAxis>
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
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="64" foreground="-10066330"/>
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
<VanChartValueAxisAttr isLog="false" valueStyle="false" baseLog="=10"/>
<ds>
<RadarYAxisTableDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<attr/>
</RadarYAxisTableDefinition>
</ds>
<VanChartGaugeAxisAttr/>
</gaugeAxis>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<MeterReportDefinition>
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<MeterDefinition201109 meterType="0"/>
<meterDefinitionName>
<O>
<![CDATA[]]></O>
</meterDefinitionName>
<meterDefinitionValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=0.22]]></Attributes>
</O>
</meterDefinitionValue>
</MeterReportDefinition>
</ChartDefinition>
</Chart>
<tools hidden="false" sort="false" export="false" fullScreen="false"/>
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
</O>
<PrivilegeControl/>
<Expand extendable="3"/>
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
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-15805966"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-16681813"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96" foreground="-13464662"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="80"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<f.DPNUYK$\+&&$J&7P,&h@6XYQV>6jVgPX1"aqZ6CebMHGrlKG@Cb81anFar[N+1(m+X6O
4on:lVd?@#q+>,Y0$Y6@eG&)mHG7!RLVVms*.JVu/7;P@rb4]A'fHEhMXm@&$B6HERe93]As[^
;0>BLGem&08nCCen%p$=FI.FDn7JpA.STF_oMpECfElJ==0=f?=cRFJ?X^-)@_:l<q>9!8n)
>gTumX6h[bO>*E!ij$.M#2[dQ2U6\--%0LkAQq+)4$fmoJ>AX"@nsTmE>CU/a<R)n'dIQ]A7d
?sXDmXg\lU/<7sO")4SZQl9W#$?O5H+g)+oGenoE"Ml-hjkMBe3]Aac+uKl'@pta_.#'`Zhsi
GCDT9M74PnE_.jdbgiD*@(%jfX"h.(_G>;e4rfbE`LHXM5D8R;!.9sb1&?0!]A(:X3q=9d!?l
1_>m#2H<pk#JmItHj2K2^=-O>rH-```YA/S\$Vau'>"?cjmSp'fiTNpHa02/[5W(CW)DHu`*
;)jtljU=3&4]A-f^g;do\G=QdQ<W.QSEC7QH5"-%MV@2J1j/_'OZD95.H[2%:4kIZ6_]A-58&X
H.qPAo&4\8Q^QMq4tVM3%&5BIWiE1$Xc"<"#0)a00j$Y)e+^XGDF%Y',6mO+!=;-R."Lc,q*
Y5`U>S/QilBJE;9i$?e^$ue*)DIn0Q(GpKL3W'p#o]A\Q1K]AM=@K;2Or\*$esIlgIENVSmZ7\
pC-od0Z6'dm6SKoI5[`b&4@f.O?/.<OTX1#C$0h:I#qLo2dHQ9I)D&mX4WZbLNsXtD@pj^=t
%M(\ad$sZ'pl0F%.;nRaRC[j.lM5+,.7nP=WVa@h%HVoVpX#/UO;j$]AATIF*-M]AS[r@XW<q_
\mBQoS`]Aj$1a*Sj4_nnN0f+3#lp2`%!Dsl2=Bf1:ps7t&e[Ee&<?q,^5!<_uR9&o2TjCUFuC
OFm?Vqg@bmF,X'D:KTRgP3;)hs1p>?-`P_I1#>O'3(\#PLh/'mN`as:I^$,"s)>VE=/BLk`6
W(e?<OJX.5h8M)Wr@Z_;kdk&VVUJ#7'.@1'5!B<k6jEk.eNQMM,_lkf+;"-V"CYR(h(1=R3+
Q=T"XL@'%L%*Mb8FC+j!=iClAoX=9fHDtFN=SOtTbdcV)c1&'7;KuLLKMOcoe/f(C/Q,ltkI
7VJ'\<$0dj[e;rKT*(L7*D#jj]ALn*6?Vh`g!Z?K8(2N<@\]AR]At!J>,jW=U?frfYJUa,=YFhm
Sd7\Bc1Eu0'l6P1e9*=.iJ)A&+ZZ3o64IWI*C+oSu4tT<C7ScS+bW`[>ouDV_!sd7D4o1RGo
A^4hAq/3`e=$h;a<:h_QXA4rjpp^nZhS*C_.$,aE,6J4)S5>(hhHn&IA8KN-DLZ``u057qgJ
=/D/$e_A`"d@JU-j\!VQ):d".60r6D[%F92?o-c$2p98kHdF6Sn%4#M'U'UqYW1I&eU$7YL2
5GEYU]ADIA<S_>@(DS1!Ua"@Of5^91g)%kSdQIE12J1.ZUM2Bci?Li+dX6_H11<37,<N_Z#@;
SV=#'t&??nA`<Wqs5Gg.&N8WgHe&U'L_gGAF!mUE'7R(]AO*XVARu@C1iC/oA_0[4QGV%!W)_
\H*uP$$hTlgE^(90I"6LErbXenc[_mX%S-(8^OL%XQ\AEZnc>0ppeLMZr:[8U?_<t)UH-]AN_
!qcPl@hNh^MF7:99$_Ml2d=hB3EHm0W=OY:'M!uMcXX4d+"rQ.@K]ABF+/">Y\/Na-V?R2T`/
EGJ>%#SNH<$]A\08TN>AE<1#o,"f/gRUXo-H>Ms"CtU6YLF+KamI;3\l^Dk['to=>?%Zr)EQ1
GJq#1;S+c(c3l-%jTG[dK?7?NQkL5,U.!JFdTQQ7hE?ETrL1Mt55Tu9I'?gG@YJ'kfOtEp?X
djkN!O<W,AZ37kL\NpgbgenH#XDO\o(FH;mEf95h%l6NIK<e,C6Sn#k#!0R1:iNJ89r=\:\g
78:kueb=[_LkrVtm%qt"H)'-hnZ`$"Wg=BN&LnB"IWZt#2Z`/'t="kZ#Mu#E2d"oaiAIG*dl
'+5_k=(JU;DWCYko0f[irTY!!Dd0Ln^#+B1Z>Vt;4As[FRX^b:c]A=IScKXi85OIj=+L%U/h.
FQ7.8?OT4t(OF_(/V$VD._XcT_H&**,u=?oeg9\Eb4Jr+,a@s1-q>><`lN=/qW#91\eN3/(b
/c?j--3Xk#)a1g]AoCs1*R[[?oXnLt_3Lj@S[YBOMHU?D@@4j15['d;A/SA#N^8cg<.Xm`"Yf
HHiO5WsF9TVr)_k*MNqP6p0"ic)R9E%apo!@.C-?NPM&WsFR;PnW&gt`=Ra"qi'*kAe*e!1C
l!/0[ebuno;O8]Ah2R@9W'OgRKNEr,@+i8C09daHZAqjku=<K.A/gnYn+;?E?Dnec`fRY&Y@.
cF-3Z>0_SLaO\)$'&;MD#_*CmaAoB"TFtAro$g&^oH"<nJ.hDV-?"UBYip=cbYf4j6%M*S/W
l1Vd-0#*59J*GYEk#k_[Vsp/hi$45^VTpO_)j^2u,jaM/N#&"8/o7l0^#ErWKK:7jGQ94=f=
`ORne8->(J06N?]A^!i!+$lKZ+S,5YAdZ'BV_?g]A?,.@;(WZ2pdKao/)Jf).(B.7WaR,@)DCN
s+oN`4V[FP1>881f&4X41oSJIgPABc:&R(;T>+RX*QjGa6a(/L=*c,NlB([2p\f``2m2`jnX
s#9Ee**>LT:n7Ensn#R#_Z)?!:p64P98$e;=iIU!h'#3#mLL-Z8FH4b.RW".&oNL#*B>DL'o
V9k_B-3Y3Xi2dklY%#-r+f@@.Erns4l-pJ3&;\&'#Vte9%tEd"Gurm5:o$h'ZTa&IRu38Fm<
\SZDN_>$IL#F$EYbhpKZoL^0q`.EBLFL,%:g6]Af/9H=aZR.!f#ASW$f3RKRE0f%O&%q^`/C_
@:YjEJbRpt-U65m5.mL`.tOKd=dig33Hc"?/P;WE@,MC'>3G.AQhDUE$mW?JRf&n3%IS7iX[
Z2=52aCt]Ad-OmQChk8A%)W]A3S&5B:!cWb]AH*G?Y[Lb,qbD%lcNZ'V&t_muX%/Y&,!Ksm94uS
j;m(FhCG[q<g^lk7=RpK%K4Ne\S.hk7kr(YH%r:Wi8i(1@mED)$_o%@u,\%R>SC=%AdpV>_5
hA92QLY*,<,68sHi+!U<M+^k)fP:9+CrrC0'lh'd@^f2m2l*uHhgmZ@ar<8DNNff]A?u1W+8^
19Bec3j?LJ1r,r,+?M#[Zr]A[K.TP"H80l0CL;C7j;*bP-;$OYj19:ii)q8uaKB/?rRecCZhB
HLqO6>p6LM?@?47AshH*dY(kcod3>mgK-h^7k>gT1Z1B3NIJg"'Xc^&LlF6MRJCurP3a/D,Q
p[+SuEcqmjm5_8;`ofaAa5T2d0U.@rH;STA*36rpDGpWO6JMP]AFld&8h]A@s+:oW,&h[S]A:o_
#>=0>=-Tu'KIQ#&dSZW7bo0[XtB:q]A*=`%Hl(EJ"e@38pV:UuR&@s7uB]Ai!>XNo:9d#6$_0L
7pF55_%em.D*Ceg9Z*BmV_'i^E/r8qOD-2kPGoHfF"7ZS<Yuj2P+5]A?9`KZO'^Ct!\Kd`]A!E
"-!f?&Tot@3_9M$c@2bm3Xp+[cX2PGH%3i=ra\JpR@,&L#h8)8=BRJ]Aa*']Ar#f>a/>D&#nm/
)"LCgAW0klfW^Vb&#kRgJ34]ATZ"rDr/t+\Y`Tu^-IcE<B1?pX8bmYWb9!Ct8`^"n5b2i[;M`
[$qW__1^Y*lD*ed')2>nJDWX6pis0E9lW9&1qVUnqW7Ygn7X[>U;?$DZ?c&oQ!X)b[\3h(q2
f%UUAshA0C'H`QEp8d*mmmbHj*L@J+-_0it5np]A?0<@nO4d-ZCr>q-4"d[3#"+`sc8e#=Msh
:a/h1Q2`*kndPmM^bC>m)UBZA\*Mqm1HT\[$[I';?p1f<RWReb$(8+lT!;2Z'M_5&l;5Ykp[
gS^Yb[:T9[*i,tX(j^J<Y,cus;3X`g`1+`a4TW'c]A-`P\5lh(4Xn%b6.B$NK7"'6(hoYTFh)
<Nr2>KerXN]A;6+LH`eln2fu#Z0g@0(Pm>qUl%4fdZJGu;`h6W%D#M_$.'Z6c-I7;PF!8kFGp
45*cR02EKgpHEN9nX-c@`itCa="-EU`tNgKI,=eH<_[>nB'P#VMhVDfuPGkQ'@rgD"UrDq<I
9B*EHtKpPEs81SlT09"22;\hbRIfd/@-;a]AL-]Ap48":,t)*Zh9TM1*!i%5E=PKQOlSKo_\-S
rouL8M:sZ'eX3=#'h,-Jmj.JLu/q$2>6oN,U+Zec;=pKeO"a-%IkXhC.NdM*XL2S\0g(2HR>
8nI1fLu)+0tW6HVdcn-YVr`('?12C.^N!J-(I46cKb(u;'nZ(jq)$@[SL3i4O8"UW"nN03bt
^?Y_.&@Gp7LT!9D#SXGKY'[iVpdNr<G&3ck.sZfQ*Ya3e!L>j%K<JF^R"Y_I%l9N0m=8cHd;
2Q,)X(?OYdr)R<7,(,:$k?VcLA]AF5mX1K^+1DXF:a9WelS:Dj]A!<r]AS]A$?]A0N61-]AUo8(p*]A
pE:[5oB^^O]A!9$#G8DA+!nug+W'Pun\4%s+_kjpb)XR"0#\@M7&Q=cf(QYZj)IG+5W2]A!Ydh
F?IX:0ik'a*nC0g*fq:`e$;NR]AhE@I-l,/e^W(]Aq;EZEO+A0(rp6\M?m1p/<DT@$9Hs^MjKT
?2#Ar+378cTo!,M1t+^qa@4&UEBGM%(LA/P%ZH/bg%=V\LNn52Z'-;SMR,*AD-)HeXP+?f$-
oD\"RqfbtlQnE/dihE71p[9\"CAt'%5Go7,"Bl-)_Ipl`_m\lKN`"\`JRk!1n*.XY*SB-]A1!
qHLhhr+#:\Mro6^01AlGsdZ#W_:9)TrWdUKXRAll>K;_BQEpZ9)6!k]A782O>g*#rg)_A"WbG
d&Ke=d.;`lBABQ+Y/`Dl=k6,"WmoG%`/r9rU0/(cTi"'*B<Ot<"0P'TkEU#q=^[qS#_IH5a$
FfO6]A.QjUrkn^K(kp"![XG1^hu#1*_TE@/:]AP?l*'N^+>AIj6EMbV?Rr]A1@/]Ad@:N%*UTI;#
I5ganGJ=NoFC&T^>\r#C0G@Lq,V-Y&rOdZZ4S77YsU`KI>;;4hC89,Jpn^??&2g?`!8XuBjI
WL_JNiO\HT>Nh?(HSJi-SQ;2HJ<khQJd61"<fa=a(@/'uXV<L^NSSM-<+i?fj]AQL/7m.M3pW
.%"-AKi0p5FuBp94u7%*fgh[%/iu-m4b@msOW)CL(>=G9c*@/D"%H$4aY%EpE&O<IJWTjC;L
F[re_'jc'X:%,KI$>#VsX4G+G32qDmHY[!J&0TX.J6d)gp*[$DQ6ilgY;mOj7%;<RKQ@#t@W
YJ$L;qn"7ot-0\e/j"mqW,aiR&d:]A]Aql/%&=MYg59TUNke9Ahj/SNt=bHA4q]AC0=(0I7jPMU
8#n:!nf=*sO@FRU&VjDR^^65>:NI3k,^/2hgiWk0LKK@?Po!l$_$'0e+L"ZLJ2Z4Bdo12iOK
=%P%:r=O^4EV7J)2_WV7K!;st8CP_O`\:VpX#\;dQaF]A78Nscj76qsLRh<hm2X-*<LDph.jm
@2j-%`Q<nTVqRhG*^tB5OXR)p_4DLCSpIpiYTqp36q7'NsWf^L#>I=g0UMK5DT!ffTK`dV=H
hf"Ql-<mr(KKDeN_&Vd(I8-%'n"rGi7<sVIFT?HQakjZoHeOuO.)\Krd+kpJ=\&=R;eqJMSl
ul1;4mKIC/Ka\#N7X3aq"?pd_1Y2/2HYL?$&#GNW7c@s#']A+WS3eU,+q'IliS7Yu,%(+kU=N
CX8D@QuD\%-Q9Y0pf=`baikIG8/mLL@K\ln'uZ`293;GSd6ljOb+Tu^J`R*oYm:8!UiSRlCs
(!jD;3]A/cpeV<WjH-*-fH%BDFHD4FB[+Q0ng=*&M0_<$BfM5iAboiPtNU9fqQ[tC82AIe5E&
?Iu6Jg^pAA.sb>[aDm_6S]A#e`A0M5R^##?"sREGAt/a1iTQBeSpbU,EMl+/2YY*eqIgC!*E;
RN<fcX?W-1'EY,C6/BJX]AGUR:\0#/-U,aOEW#eTmkI^$7"b,D;Nj1obB;2P3Zp!e6/S-kS^h
)AU=mj89U;-%?G4=,r3bbV0F_]AZ(\ThQDr^L@MBO'=8Ak=6Yb9BkjO25d-'geKfHnA'bgQ]AJ
fhFoD/<Vik^fQ<$d)KIfUmW[,UFg$WOAi2P\;OE</#!9W>W<:o+pr*M?g.TO5D)`8FckI:fZ
7d%D!LY]AdR[R+o71*D7cD+;'t]A*[#q[-N$6jO/cE6,?uaS#iT)j\6'3RUIr.91F56V'j.=NZ
p&WWR&[a\!>%14r#]AH\2F8J:sG+]AE*QLa/4Ift&5(S'L(lj\FLXcL,@t<DImN5^7oZ]Au_0t_
W@FUpDHb#6-s"KYKHRb$m3U=2lXt@oKA,JG3L_\"W<r9ot52?4fdJO;;Rcs\WKH"ts4>%`mk
@]AF).#]A6p`LA2):=k):!>sl"]AJ[>LEF=YP$t<Y7#8qgVPo*Aio&sXmR#jFhR5]AOS[FMquG)m
7O[&0!O=OWRGp3p1K>/T1.GQ5lP'pQJWXF[]AL`#^]AWm)G^L>u.Cj),ek;k/.bjcpn_-2(OKW
4nu>i#!sS-aUb*u36]Ampf82E)@ub\N]AC>%Sl`C%NC[fhY0_C`0BitK>X`0[)V$jG7b"$@k'D
6)u^b$m/A^gs1[mU(5\L6/uBE0C"N(lDB)@G<#D;C&7cU?AT:VM]ASijE/W"ma5;iJo?Ak_P%
=%dG-+'V,9Mn\GL'2G26H*n>0:6O\H3Z,GNufbQ1,D"r+XG"mL[`t>!?m_E-D&<eI/(cs\Aj
\W;c0RYJD;#HQ]A_l\qDXP>N'Od-H`/5^3nW5ZNl/CU&`Q%=LLRE0pUg$cN[.b32KeC`q*ikj
Eh>imeA"HV*cW[N&LW$&j8gmL$m`nh&;Z2<8<Age=QLooEg[H+Oo"ROE_#=O*Y%Vr2tNX07i
`dgj/e0\,$II.F:NrOD[R4NaD(@adR*1&09jRj.%>N^P[1'h9TCGV89OkS>Zoafbk6ni9Am1
*5!_VOToZ&&,FgZd?sD+&&(-.$D\TS+eq1aL_)e"D31OLUT66Rn1<".e*mOhP7iA!ZeYjgUK
PUfgEM8rI.1^j+^8M:hrhT;c+[I/+Le70O.+qqc$Vdr4F#!%qrGYf='R+;2*1mC-cCP3st-i
Z%uOaa2N%1`e5"Y\/\)PD_n=lbaucXgP[qA3;oV%!cIXYJTpAF1#d3VW\@RDs4id!\Djc3"q
Ou=Hk1e=<nr."ulN<gd.m64B.Ji2BF_:*)B*=lnMTf.!SnhM:EH-OG/AbF8X?M"6*3'-&?S[
B9!="pb)7bqfj1,2\p]A9RElQG-[PiBlCDOTX8+b?[`a5If%!)8[[d8u=JFM*KYMOO.Q']AXrB
YjUrE56jCE[M.7*#@UB<qCP_]AiF*Za82q1UJ$JY'*^#Bo7]A_TDCAYhY6S<V,C9t@qU2K3f`k
%^OW`\!5g"CH\E0M2W+iUnrk2$S(B#M%9s>Y\Sp-aqiI_12oLqhr.D.*@>HLXo':g5O\\Ri(
PpuD<OGSMNb`]AUX\[\j?DS2MQAgj[-<_Z=#n;_I);4-["6%lnG2nE<7P`KjZp$pT\OSOoZfr
S.1T4I,:dY'N<Oj9eM-/!>a*o\Hc+I/ZC."!^>uNZSW`C$]AY=7C-YuJ,+g\%U%R__N@oos4-
+uOufK*BoD8%f.^k[XMdXe5"H5M3=gOSh94&3;J#SOJ("W[2o"C(S^.F\OEd,<mEf5<(DU>/
ba:>.Pli(Xe+6*b1tdLm[,B6Z\52^VLfYl@n=GSW7d0\0#7`-5P@"S$B,bXmoU5E8>CeIb.K
aoHG.K9jN0qNNW0ngMLhGnF>F2NpZ7:S>&bBl*2Y1q>GY5&>"C9kHOKel,2i?@t:VBR9\!!#
!nRAn5eW<8KR`qYPl9"4PIf,cf"#14E:85(MEZ$&qmdaR&Eg;%3<-ao+u-,YTKPAX,0=,61b
$qE5-rZW[uEil,*f@KnW6XetNgWJhu1`:[ZjfZ%+dqq,J@bG>Q)\?H8O.88i=0^fNp&B$O+i
m-6L!K",Y/g0d-OVM!nb-V6&lQMl]AgiJBmJrBXqZlY>_O5c\Q`613Qc.:X2V?RJoY.I,Nn(1
mm2l1q'W;0a5)Ch/j)]AqA:B*h$0^S]A,>YH_8'BD-ABU473iQO-0nEc3\,-a)WR*bQM%6)9Y\
cC_ZK7%rZ?o;FdC`QjKZ`6Vqg^iE]ACf9H`DH+L+q2:3$Z#%]A'HYV5!`>Mjakg.6a8o7!E-[-
s`m)N0I82CZN)^2c,a[m4m->\`iAI/"*OYFmfM+U\B_(*sm$=-#redS(.lcV:)rtf"WD,4s+
-)ii@$Fkdi9!njiMPA-'#]AiliOg&kiVkZh]AK5Zg%A@Pb`?nV\TbgqkcO'L+`?fB-n*ZVd/B(
DX3W)3/0ZD):a]A'mKmWm-%r'UpI(#i+m6>X6I'j$\rj/H)'AM:TBcoSCk#?0!)Q_>'S\_B&e
ct!,*2S/HW7O+CP=,d;4lW++,u47,VTrRl)loj/-Yq06pnk_]A6u1;)>NeO]A@L2krX;j+H4T>
4VGh[U+1LrC%[`8-'!tYN:%"tTZ$okt',)867kKsN&YnqS1_$f+rS-gJ*!:dQs+2oqm4_:?^
1UGI?smh_M3mC*O`7+s$sb6b`GML7XT]A\UEXi3>2IH+:(Dk(KNGWl"MAj;X1m1f9*b)A5m!`
9j*/p@1;fH#DW`rr;fKSn0U?,$;VE<H50d(S$C0fW[PC>GQY/BYUF]AB"XA+c@If8Ktk<h\,u
g`@4N@#c*CTq:aq4LJ&p2K4DV)F*OK]A:5EnU*kbpf@5'N7eqd6:3X=tq#S]AVNb@eKqROdun&
*(pI?an^M@s]A5!Le!<DU!:.Ps$D/Rq7jnr'WiMQ[B-dn/u9do@=3Y%q[1,5Q3D@%gONJm$M_
0:oTG(1N"956-=]AP:^`Ru]AcIeNQ$/+bcFHb=.iC$IV]A.%tiis]AHg:1Y<Hr>Z&c\d/iFnHu+A
]A0,0\RR%uE(jiM-NKCWjuf]A;NQrI#idd"n#_ec?%&s$8_VK<KBqqo]Aos%`d-CLMb,GH,$'oq
M9fD(q\4ZlD0^7,MRmY/uqGp(\35E-0/fn&_,,s7,,M0-2e[;=oD\$[g2%$)p,@;!S7,JnH_
!Ecrc)WZadmf9id2p/j[?*/`R:H%1j8g)ec>-Mg>0k`79&d?D7#eIDJc24#Rd0/S=\pfeg%-
3^D[<b8-/ktT<0P(82T%(E:1#LT7%)eW\%#EZM@-=\I8V-j`*oMW?km>lhGqTuh7)Yq5]A;;)
Y\b]AhV;3GZMW3=ONmnM=S/9ANE)V<sJK9_.%[(<SOFr0UZ%:s'L$Ca_f7N-B66*N.=e%12N9
d`\';:g$H!mTFd4jV7PYYSA]A620l&5CWm?'b(g(HHnN$2gIIs:f6W&_$^lMU6ag>HSGEJ!T;
mSS8`Orf?_dhS`-m\^0P;@BOZ^a'WH3bnB]ATKBNMB4M2H?g[pd1d^^`N8KiuYHlq1N:9kiig
P_H-OTTaOe`c(/0!TCn$Ob\dTUb#!t%nquU;$NAm!nWE3T.?W.W?b/,*W#DR+>5e"9\L^f)I
4X!_^\Wi<[?p>mD6E?KM\moXkoW[-nu6')B(!2['U`,NI;]Ad4Z_HUh7\9u<@A#$<pT[J[C=o
X<"df*"fZr>ZNXGq]A$!ZKeM'Hg%Po6OkWBNTYjMLmEUu&kQ0gqL7hHl&Y*']A]AU3bP(*d(%Rd
u+6!baeH^!+k0Y!Ql[^1"L7Sn'`&EU1:#5&B"BkLb<;D@e!"1U;^#Z<s(DUoD2K5DO^`0_-9
6k.G[K8E//S7oe?mShX5,YXQ!d482)kODB3NNWaZW"TT\Zg>M!+(&R`j<mO%m<p9onN7@R5G
O4;gA6F0WimF%TH5-Yf*Ip27T;5dilLjXo7ihY+mQ((mf,-2aq<pNsq3%\_3Qu+pdh%<ZuHt
#aMXIHNo8pf>*Yn]A+[(ZD:93ANHM?+[C$1k<%s0VJ)p)Z-[p_!@[u&gmuIGX:H#*(db3hFPX
KVJTNOe&!0:XuKNYl`C3`RIj^lJT**-qtNK:>UsN23']ABt.bLIeGJkN_:4Iel$u=cLTX)nCG
p,n"A$uA3Y:o5t:NK^\o/m&kpsK(Q^jn$)*[Vj,&YIBfNWh?]AX*?LN@BpQ'+;tlidMsjT0R)
@a-)3L$U<e^s=+g:3*M*V`=K:XZKF+r_VGF&V.p1KU-Xbuq]A&tpB(+kHM0t_r?bJcb*M+nu&
2rtG=@%EV#'Tmu"/)eua=EEQ#2"X/]A=-CfBct;7K\5mOgR)0@-5lXr46#c0P:(kA!?7tXII_
*-OeuN7K<H\8JBf/SjNabStV[DXf!)uk7AeSmF]Ac1@TD:7r@GE:q_cP)+\IeTDYmm`MiD%j/
QRV/Q6!#(>'AFM`i?Wk)-6"#=[:89b89%u'_nb)PEAnkFETckR)rRcjE#&t>hiD&;13Wk+B$
!)7jB0Id`F6/5f025:R8<=`3_sQ=8%gc<*a@d;9=a6,;2cW[8gcf,i$"<I^l.P]AXC[0H;`QC
>YBcE[nYht!#U_Zmo[]Aai+5sk$5GA-)f0qnahV(VuNN!R$H?A6pr4:MTmL)i7Rh6f]AiT/Du-
T$,\ia0.O/18P(gijc.1=/P`mE#,s/PP-356>Uo';>7iTmnKX-IjHsg56q.>$kD[0*W6WaO`
Z!0%`#Y7XY02e@[b%'6D&uQKt@49eoV*<=!t%h<NTbm2s_Pq`6N@B?_H(9h:6HFU[j0.jC/d
MSkSkq]A<V,4hO*J5ODi\5=t+"UK?<<_Zi3!S4FuO*]A`Kec-/3L<+nZ]AFetM1dn:lTTGBGgdn
/`K]AF5g@R6%-dA1i"4h-,X,5"OG"%Tj+gq>QILt@8)NG,0VU]AY*8HnpiL>GR@Us.CS3->IU]A
:TFNe2L^amtQRK>3PS+^m(+m7g8(cl^a29hL<c5#CIoJd:a6eKLolb8q>WPCc0$`&[_B7(l,
&W10qm\4mfTHg]Ag,ZB`(U+6V#Y:Ui\,`2lZbm\E^pMpH[-]A(TRlXot#*:2ct(H*Q2EA2PP/2
PDc[!?K;=9c$@m9G03609a-#.-$25!D/dh3(@>FmF\9]A6D"'`R@UdN:M/;)%fUYk,hPP]An\>
62>"\C]AH<=&"jFi<\4XqTlf_V/4QaI%d.5A%CJ"7jrck(&(;T-EbTCfP`<JE$["9=tEFF:Yj
rrij&\^U$^rH-opfg60)&O5s+;0NN3KcafPcP&-R3[E(TKuc:61Zqrdes]Ah6?T7&W"\R>qs@
BnUU-\,*L9)QlZQEUD#,E6nm2D%ll;`Nk]A0#r>nHZ9/;08M5ZP2R&&uX;-B[/o`P>PIo!5ul
p#]A,HGLja)%!\4sq'rGnV`(bdpA6G1(KGJ<%r+l"\&iEm;W@"T/R1bXa&22XB`)#.0n#1[VN
p`"k/5c%krD*t)=(FPq>)#P(SfNG--c'uA5F+AZjYg31><G]A&bclka@6[M7t`U.*GuSoOh[L
50\l;_e+?5mdhqloAT8=-",(`l:?DZHHlq-a;d65*U>2l'oea=^OmYOX;nuOkp5GM\G99qGH
JSOj&.$a#[4"u57pd2J(/gf\%*MqTS9sX'P]A5-p:,`u-0T7DONdpc2&cG$J/ITK&-bjkbI'q
)[E`V2-3b\4dCalX$M>VT[^T0qm28K4\$oDp]A5u`.%kpjfjr76V5,#AqX\ncAl[<T]APN3;06
Jgk+[n6!uW4g<J%h9F3hMt5I*"2_$RKt\ZrRCCAn?:]A6!,cX)',_t!"B+N&3[Ab==Q)R-S?n
DWb@+h$jnn\s7@r8IX2PEji#ubf-52__IaQJFjE9t]Apg)Ij*p18R#"tfW4Y>[]Ar<sgRtV(-q
!*k#[W12-%roHOuDeFN70>4W3LB-U4Kqng*@C/i"VP"WLkiAr%qZ.tD#;a"aT!ihV(^9g^RI
a/:UbSF]AB%J-Z?@n9j,Q`K"USD-YH(]A%rQ(omAtAr@d4-`=R/J%H]AoOHgjBIkD]A9-Z><kSe5
Z3KecLunVG30q,ALgR]A_8(\fYdCQM#"/i_oo*?!p6"C=e9n+Mk:';1E.sk;jC^&Q7=n:gR-n
l]Am<*-gjGkVXF0m[-e1;Wn$uk+6V)kg1Ur3QeBc+(3P3g9,Y*Q7qL1Ps(EkO'58XngH`8g.p
@AS5F`]A%*s6a\kA=`JM>'ZbS-i:R<[.3V=U.HAZSkj&6]A$Va=tj#'9^)<r&X'XCP8\U&o^.[
;GI3156R'e]ArdUTZ'09WJ*_EaE%-Sh[3R&"A!k-S-'(Ni]Aa$NE"QA:JL*/lUhLoZe69<L(6I
'*&=r$'%]A$-cLR:SUZ@BKs;G7RIL^r'F1'm5g<Z.kNhA=lHt.J>If(m9dTidsRuC']AgrO#:9
Q-mn_=HWT_Yt3Y.^a4K=6"2@^oi^Dk;K?_10sY-mfK*_c2T]A3r;WV(>DhccR5T?1_]Au-jXBG
gj6bSAi^S-fg52kHnFf26,`>c7^:$=!c?8j1:/enf4AV:W9c@AoGdF*eRAd/cib8R0N+e#/q
2d;%#i"4l^r2RgEP$bU<1$WR@NWtenir7q`&UV9a]AY:4;9g:>CDJhAhFQA3g.)^!5dmbc@)C
=(Bs'giNG;*WZgN]Ap#rR5!EHduNTk2KiRDO/pa@o9RudG4$T;3R)^`!Q>;%Zm?U$HBjqPCD)
CDJ4Hr>V[BYsePan<P[_[JfR6':H'`>q:^m'I0!dITt(W<sGq2b`GKI0IF3m/H8KMa;!&pj;
`TH6mCiM?qB8V?4t6=u,e^gG?Yt<!$o+bpiQYkIBE37f`5:@b1e.Rq`1TN$J?RN08FKi-n@.
LHM)MRGO4s'%orf:\d=[8VDS&3Tt^s&)p-p$FW'kQeD-^)elo:o)!*`=!cCr`RY,'LAk-DoR
O<ck3&'!M?TJp,3/b<ISNgb?#>&>-5O)fGTqDH5fOr7PYU:XM9/\96AcT3'iY8-0\7bSZ6[M
[`!;$O>.l?3f*#gEMa_G)K]A@/3-hd>G^;t5fnaR+Rr4Uqchs<6g,.8="7a"o74+pmkX"qjMa
UHdbrX-Q8meiUYYl"X@hrfcS]AdeJL+lOGfDdc1ZUSQ?b$H`ckX_f#m!Um[]A"&>\@H1&T]AhH(
rq8>#Ur'mRALlaO4>#@V443,S?3JC90G\fuk\Iuu%QQX'Sj-V:KSi.3gDKCuZ)G&KcOL2s'N
%^1:;W]AN8AF^]A1`*f(;T6e$L>iJ_3e'RM"AJF0\3S]ABb`'qo&=K9LNIkRVEKnF:psh9U_@f@
?BfiArC9p7&Jj*X]A.,"jXG>7j=7V]AKZY5P;>"i;01E@N>Ve69Qo9EN=jX7rs_7VETj[4B1D"
[=tjC:d8L;8qhlDrb)fD*TQsrGXmu:L]AQs>IQ'm*HUPW]AR/=gDq320'`C?77SD*K(Xm#h\aM
I$4p@gBaboi)IdH2LOhK<bEXW2K:Y1*(c8]ANe$Z9lIu&Q3+99cpZ#ERW=JsN\<Pm`er_uOkh
@Mc5t\nnSLY*-aTu^,GWJg=PpL=Vn362=sKq?qSh_emHXO'==nAW2G'X=)%cs-f[\"k7lK:\
>O\nf(ljhUT"(.T*b$k@>O^(T4rlqq:t0(S:jO*MWF+Nig!,((I9X`r7@ac0)dFO'g$<47Q[
=gr/L[6JS3-%JQ'IiShC//57`6pN(n;1XY'AZk$8@r/:`ZVS,]AIT;a;1AY7Qa'l7meC1[-dS
Wo\>sH(A`l=Tj`=H;GpcVX49<?'Po'$8"PNtem"!<g>+&'l7"?%.&9V1hPDbDPuabaY18Bke
CtN->fISd]A"YP[K)ZLJIR@k@4o'`(ZN5fK2%YAeGu]A"CB/#B#"fa`i;LrL:C;(6!A,;8:Oj8
nc0jZGW"B<SQ#/^%:g*N\5m>I(_4p\RE8j;Sp&b#f*=Coa%:Ar`_&ZU(N^'6='fq\3sA_(Ef
pl%l-.lm/+'uU:7AlAO3IX7S/D"_qh$/N?i[A5'Q#M4FC&V,?UWrq?,4ee6pSp5?2-t(\=RS
tF1KN,!feS0J)Tb':CI*NQL*F5['ZPH;AbIeX:gTJdLBioYu0,o"eICX"q_'`6PX?3]A#^ka[
:`J[-L^k,DTL9ha?#qtl#1hTaB$9-HU>c,dm/MP$!CQTT`DWn[BRX3PYlH!*U=U)g]Al)A*]AG
7)*,j::W^%hAso0CX8GD7j2J@A/(E%mMW6WL:FElJ.43jlV$qT0(hD!.b8*Frq;7'1W3#Bp:
$[_q#Pge2i^ki/$*t93ImfF!<fmiF6^dK#iXE.6A`MRDjj4]ARtW9$[2RcXfBqKc;XoCM=.eR
iH,*DP(a;V<Wfj,1hotM"#<^D_3>*?nO^),+t;p@SI,P<.YXZ#LHAM?Of"[61X4MjP#fP"3^
:QBcde0/-nkE3[oE.7JRNcN&#e_n0P?pD:b]A:ePA5/BHfo=K<7"3PP$dXd'</VL+(F':#n0b
mf@<]A@!&-U!hS<(sgh]AfnCO6MmfBl6*'q5),'#AH$1ZNbcBNDa6P@/R$H#0%Z%p"8:g`NAR&
7dI\`>*Lad(XsQ`'_dWFR7r-lKT:iA.3i3H?`eHK"9DbW9KZ9I3&Yq\&FJedI^F:;.8C0P`<
=XD^cMMdESYUVq%,S%f+`>:-:Xe]A46kGf2ZPq54V_37g9^a^<C_j1XXNiK1?g[^=HH5ZRH+"
2I=(c@u,ss"SNPtn:s1XKf'SVesW=Z?$8dj#nTcY6TK4CQODoN;H+=c&U09$r^m4hH-_ddK!
Lki5:_THl@b&\R.7Kc_cibpOQ:<o0G:b`[u5h3h%9QZOi'`W8hDK,+>:LIJ$\1Zgh0>&@qk_
d"GqoG#'pJ]A`@\Ff#)5`#@2LJkkD`dISW3kH2dLat>fQ)^>tPhMcuOD<J?=Ir-Is-qb8ks>D
i#WjNRY)X@-=t>K7,1"jGbd-P\Hm@i;>ccJEb64mJBY[%5Z(aMAblDGN]ApMRJLPD"j`^d!jn
Hp!Ms7_WUE,ZbM+YLC:O7Q68^`3`pp&'\:3h+gS`7b'6-\k63ofgc8&d$$YG5]A3kE;S[Wf<T
f[%!=7FnqDDUU.e/:'SnFakt)"tc_+Wc76&^.G]AAK`TK_WsJY4*FQCp)AuH!n%W\M[>O>RC.
VOXU7QjdS's`p0NM["Jm-[C/Tr/)K9;k(-FKK^408q8]AsbhK3\!iBiT1d0eiLTa/L;4^qI,1
^K.QA"?*Hp#;8dP&)rH#>I@X3rrMa->^H\Qc%3T1fCn^cIZS`S(j9Zu?Z(Boc!QD!i;:K`A4
rq[Q4amg\'CpSebuYO.mX(#&`InQ7fjhRar+Et/K'*;jF+>cCaOc*B41?j'CFWHV6!"l]A-qP
$l=AOLCjLZ%mmFG3UNic-pW(eG"O4q<^7)$8#6X_Q!Wr<KW(AZ@F"_GVPjWT-B*)h0u_3a[r
&a%DSS^SS^DS2q>qIg5krS<*?cAba&%0I)"RlXK<%W66")^7g[%5mEH]A<;dA3CW5t4]AUJ3OI
Zj+*q1LjN'OMlia.&\2'YEk"5@qWh2_+PV$Y\n':\oZDC"+Jm3H6"naaU@&]AAPFkml\,3b#B
,o&)T;:M=:/nBnH^C*=?bVOc4gnY+q'`1Lh"$6B`Sp8RcEEIka'Qko$lYg_]A&ggB6G#&`4><
!=41GI4>rY`YaD1imNh47?DZpY$H?0%Gja_U[.QEU"]AAO$?Y1[*GG,0&2Q3Xe8)la7u'Vc*i
`26`]A:3m%(Hka;3"ZQQP'=gV;GqXs6Df&85H#FAo;oI;"_?"DNnn70hro*;>uXY$T=6T*F9B
l8[sYMJ(G/SX:-oQ3oO?n^p&;d\=g/+/PRknp6<U`nrjU:&@b_N4'T9mKU\-cE<Sco;L>U<9
h4K[`.MJfbM<ZhH9?V%\J;f(Z`j%Gt'G:c4TRAo64(_Ldb%k$[5M=!Knbc:b^3Pa6o22-IBS
:r-@TJW\S#_4J;L55^dsqV?`,nS#=igeL7K$V\ZJIT#YQf=$K-<?b+qCLZK!MSc[,o*ai<K(
<d.fO+1dsp@H?!(2$-Up@F;T42uPA>Kc:JZ@g7d2f/H)[4B2R%KDP*UI'/mLT`,bX<aP62(u
FI(cn>(8gP43c:hF&C[b=$39>)4>C9JF^^W*VNhdB7L9MdS%%=KOY,nZuo$opse$%?Uk4P?d
X7ZFn./Em<)T5)J?Xhd(;*@-JlfA;]A4AWS&*`A3/Q^8Wkf`E4pWd1Qe0/N@>Sp"\Y7^DH7E$
$uJCL3cs;u?D)2_I<8J&qn^m#jBEP&fg1l,;5Lm?7$$rpfE,j^2?U`!\6oJ$78uIo0?NW\u+
hrBK`h!+,oq`3m)u05"'"O8`l^#hN?sJR!5#PO!4^,k?*'iK4Vo3u56:oVC"dOk1cFV=pAR&
&"VF6AYHOG%o3"MKrJn.YDnlqiZl6`qA1nI\$M=8,=.C=U:VRC[tfM4aW'AFT1l`i=ojh?F%
+*[e-'NV6r*YeId6e%$5omnTF=!S'h?,rmdklXhGRP22+^c^1sY7d''?)ansW:N\@MA.Tf["
CI!g!IDu*,m(&9Xelo(46O?g5Q?7tb]Au2.6VLebO9Ap5I"LST&RX3gF"VeMhLL;2egu&5n#j
T*!;ao^DfMY5cQ_p2>_0-+@A`7(/IYhW^[5[8b:VK*W^9c<I<Ppr21C(hC/N$fmglAY*M430
lm?Ea^qX@Dq",Nel*39Zm;AZQIWQe%?#B[9D!ZLO/!]An/5rs"Kgk_q^:>$;SJa^M[ZkAi^8F
G0bTD>fVI%A&L`L?r(Q*:-`tji`1k]A9f%J^VoWS&]A#W-ao1M-imXb_^pZ0T\RW-`($lc$APo
i+_S/>QPHgeO#,%g`grfAsiX&naKU?u3ROXV0G-lR.\BglRjFpSQ/O.bbR#&g.QdH't`+jNs
H"fV:r+]AR^$cQ%?%@qQX+!KIbQd>6r[/G;lpXnaEZG]AC/'[Z7f<Fagg,Mh1S0eYGCg?-/IN@
/?m2"&X7ishn";LhO3BDbO&@7(dnB]AQ;Pc8:0K%LPYAb,;BcPDq(g">jJ'2RV01IJ;3QH\o!
F7j7E!WmDg?KsoOBg?,8F"%N,/OKSt^4U/=GJ(2<O=IPk`2m69L'EXnF#d9?&fICtbkA`OdQ
(,mq&bf_[5nWN65VJ^5CKV#UJ*YQ58J<F8s5Q#q5E3,1e/'21N5TP^+\5[PAc\P#FPl8pd'p
<?*D?D2??H@m+lIj'.g(>U51;F1Ca**ib^<*uAj#e[lsJ!&6<dll>lX`'r6bZm@cnAP_^0K,
2!UB0h%eJc$,]ATtP7+p2YqJk`s+0Cu[jEiS3K()m%'c8o=lJ@mC;:`Z/^OFYiQb%B"^QM_=l
A\CSTi\4?(1eUREVCBUj1Ync'u%>*B33)#!R>,eYBnZ[bk>Yg#?WZo"R7k<!9=85X'P0cNh_
an^-NMRiAb[-dNiT*uh8!?Wp89:ud3\%(Td<<t/85rni)YO6=4l[S2^i/go_0kFk,K=0-1.8
TrU6*F>I+W,kgD6s,O3Nr/<?50OquS;97"bp!&^]ADV*0H^GlLce"H^A^t%'3(jXt5_)2lFM(
MfrlJ:`8ZU6EI!<NX.`-s*NBX/DU6dn*6AXWl1fZ,._;YiaT+>+Gq`!dPX<]A"@Ea!Q,ZUWss
&7HrDC>oja4t7mSHulj[LV+4BXKX>]A6]AG)&7tLdprGI<nc:Zu>,@p?52,K:4S`;M*4/S8g:c
EDTiO"4We^GtuPtC8-Q1V%@KGe7IIIRJ7Q`>3IUCYR#B;esB%:$9J@X6%g!J?:ubI'T,lrN=
%]A,i8Md*jGi1\7PP[N2:4-u7i2oIU$sLH/+B^o<4M3]A5ANXRI\1=Ohm=2t=XFS9XnbYhB,'M
_PG7[,\S)U$U^s6h<Eh[PE%mc%mq@)SC"+/H62]A+Okt9onk[?k)>To4&KVs+g`WL,s]Aa`HF:
/jjm>J6UF"udWlemn1gi&;91XFE/]Aq6m2R]A\3MAineV[b;f(&t\^s0jI_W/g0_TCK0!pD>QA
M0\DVAT=Jm1^iT,>#r'BW8ge:.)p;IdP<A&C?p-oH3q\dE%[#C!9!UrV'X_GG)!B*gkh^-MH
HNRNfPWU4tCm!=PaL,@Zs9hQc1t'^Cf$L^cGLKQ9dKbeUK9eSW>/+hfqu1gi+*963*tq'?M#
c/1V#/T_=!CYm`-4kO16-KnU5V>5m$)=nC;Zi;lZo;:b&<IffP)4I'><[@I!#6`YFn1\f-\-
VYGM;u)kOGaWg^m=0Y<rYL4bcO]Ad:0n?"9-,tl\q6KFt-9=maEa%,5R9`kkdktP_bkg"(`=A
a$H19a8o\OprQa#^sJ$q0u/m>r!cpThdU81Y[Wbt)tEq'?ZEWCo*TSQ1[-,BGb\%/f3l,nY%
qPdppYTilP+c$)[2YjNV,<VCdF^cAFc4Q,A1gl+=p/pX&3ta7J4_;'4,Sr/b(tg9NW`njEdD
:K1^U8;[(;jJ(>:@Jd=n_D`RW!mCJM)Juh#+?0^X3$K1f?>L/\/?\2]A\j+Wjb/CS)k_ka3O[
tGVX#UUTb!0`bJY0!/U)[m/(&MCP%%.YVDiE]Aq#H)q[$ZT/BXBTR`Ku_'ZAY(VZ'rhr'lT&H
1j**Q_u_k$W[m-$#B>:(dsFH-S!)5Qd#NaG86sm%OlC>_336K#8h+>btk&5Q[oTc0D]Aq?YAZ
m<@p(';1W7,9'Ht]Ah[!tlHX>e_(`kCV=@T`g6+Na+V=_9LFWp#:G/d-BA<]Ap?MiFjR$^T>qJ
1ghcSc@O.@;a)d+51?bf,)D,4'=YY%1MA4C4+!SCT6VGKF@OG.Y<sD:9SIT+EpK@c?h_Q.5Q
2m+K)[URO(QV).E,@]A:d8O$qm]A=canfp8qS2"Q^P2dOM524FDjOIWJ6dUuN,)S:#M<Okj@c0
d]AhH@Qi`kBZ\5@]AbWi?h^.6Krq75h*#!ufrsg(kdK8FT8A"p$Sm1*d^[A`Y\NFt]Alch]AQ+#G
h^kfrDuik3;QjpT<ItCISmNin_O3M~
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
<Background name="ColorBackground" color="-4144960"/>
<Alpha alpha="0.49"/>
</Border>
<Background name="ColorBackground" color="-4144960"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[m9P'<;eNO'+_[ZPYf#k)O+a<qBt7/h`RZNBOJG[l>00lb$arTBm?h',$K2^B%rBj2eWO$PV#
k\EW2V#)[Zhts,Zl.F=q-@Snk:pg";##b+n+K`TX1Y6c)T[1eo8=@jH$lIh<1<sZ%)Z@g\X'
]AqNmH-3=e7G!.l>7QMgR?2\9LH"$uFKbaOCe+8[hf<sZ+0GKJ#]Ar-)TBp&7rqX1Oi3>lJaIo
@rKPh1`1[:R>DbL:N[q@F)?,Zc^UbDG,E2o>0AtCWinOhg0,d-[bah^SeAZ,>'gdTd]AX"'cp
?*E%&r_.2hJ/YB)hPX/%=7%l"%JAZ)l<*q?g!?>K!ej9d[9kjOF*?EPq*'BTqG[<I,3j82+`
O[3NBrSHT6EI#?gNFX:(GC-sjZ>)X]A4-%d<n1PmM!*/72P.e:tOiJe.NRR!uY4m6$?GYl\`c
@g6pStE'Ho9Uthg"k[EE`"/$^0<dq@p;.QBh&2D7QFgh.H<X27NTs<)Cr*H#8$qDkM?NqSJ!
kkVcVA,d04JYPM1KV?QkW("CBKfBqu\HpX$A#4+?gTN6MGVjS;m$)Ip;mNCeO&71+!6Z6f$h
\m\cADd<:BM3F>-,]Ac0#$<,ISr1A@ISnjJob1M^;/3N)V2M.5:Z]A1BeADoeX4V^;90F31Rm4
\lT8G6KQf`S>>"?9e:l5Mt_31T&m(Xp&-+e"o+)dp6?isQM*Q4NqE3DqtGc#rsDn]AaZ_L@W!
f$rC$\%>"aH[d<EG"$P/;B\^UGQ-Vs`dNtIC`K4j;^>t=kJLgC_\8%Sc"%`T0p$lRRop?kW4
o(^+7;[Dc&cuMMg,=-DjOP$(c\Bq'0SuhNM[,(iX_[]A'/-2Le\D!UroIg72R8KuE)Ap#/H:T
i2a?#4Ei^7)XGd(O\b^pW/%*jfr:YnSlck\Mc/`SVRn-X["Kr&73(6c'j/`]AZ\]A<*Zs$fFm\
Ka6`1\Wc4J<r.($R1]A_NC,.`I)d2?d:b*@q,^Zu0bSPKVk,8M`*T:9UHd7_;S8Wb9.^Y9Q;N
]AEMi<(@c/tb/&XuK6^LtZo%%d_Pl0X8n_s5u(e#)X5A\qac?<)h`3F*^"XmNEU+%nTgZYs4m
:E4;g2msH]A6G_UmRnS5URZZR:p*X1;@@6)J.<$hSdZ3e>/),+%6d+@.lqA;X`8A!Vf$m*GFj
&idNI*k%.JP+@<3q@'@WKYOM&,+W[ebEa04#[lBhW,JbP!o()h1Dj:VoXO`6%f]AfDZV(DDmI
&V!(4)HHBeK_jj?V%DQ:S.;c3$34P"b/Q\he`\/lGO6m$nR+Y>X@ISW47&J2;@TOGbj$m++V
6P(a[GWA]A5+tuXGT9p#NMcGK!D>/Hk_j[KcP"1Qj.c7#]AP&2"gikjVHtqg^X?.*49DK$OSW;
ulQm\9=SAuuH\QC2qDo&L"hZ6iOmaGbC(aMoU+'K\u'X&u''tB7[mg'SAJZ&+;hgY,,RZ..d
hmL1F=`CsVfCC"bH$?Yk4_C/@3qB$m?<F[1n8Z,B^fnbb5LL_4/U#HSq<G,H>@uA/hsJ4[HH
`XKX`p>.DjaV/L/Kcg21+J:%%Tt!A2RBG<=q74ast`]A<TaD6RVP9289?%C^(XsXd>Z$UYK02
E<OheT4!)^r"iub;+%6`"%W:KT(s%tu59hp9*DjD1$Kh1#6a+`_#I-JtZO9@7YoWNDQ]AX(X9
@PX4dsh&a\YVYh`Lre4!tTJT(]ArRcndQNMmSA??TtVE/E*>\OU.:-ebH,ssJ,;63=W6//h7S
g.ZAj-rNV*m514g>DqZL5:DDu/Ph!JBt<hoP7oY<U(or1T"^Sd4mH<Ap'9X%W[RX;K)B.ghT
la`f1>6-;3/K:u':itK/78+5gZ'Ku:@AqUrh5#`AbkT00(VErV9CEA*Rt*rFG[Y'['e2MZZK
,TT2jChtQOtA$0`ul4\qp(M>qV$kZ!bT2gAt>=*irNQYkkiQ_6aeS+!ZSg7jb$'CPmQE0m8`
]A_;<D"_mRX@a^QZh%*%(0f6I[):/[8u[*>V78cUfP0^/Ih4O>>\>Vu-X%Cg!G4PT0Fb6b2O$
\3;oR3,%"Ii2DaauqV]A;^:#_[b6G<"euTU[.^ud7'o-GHkoOgYcqWkLn^%d8lIC`>7LZ>8c>
DiVIMSk-@iBq`XJQ*.T"MHf##hZ@0EiW5D5*YRPuGa[it*-=7J_#q`N4E3$OAu,$:,a$&jmU
*E\\q#J?WV_S$(9SnVc3DoI1)X)E&N%J4o-1ifW.U=<GD\N`a6>C1m^!U;<^Bu6$k;mQ;cc*
X/&7m$6="!IB[77"trLcke:q7W\G\m`t+f>'etT3j2dQ@shcop<arQL3TWCg(!*?4j3q$hX,
Z\+uf,Jt/9DjATLXUnPmmWFm@_K]AHq'W$PD<n1O3lKPUA<FF=<Q"sOkVk"V^G9^n#09%5fjc
(MVq?;Z%Z3;@kX9.06*]A\b*2^]A\m>X_US`""q\;>Z-F)-$auj8s0W,F0Q:X(A&>(KR:t0]Ak^
72!I3#nJcV8Xd;dbgg*I;J(EtuUkFt[]A]A"m\n\#T)eD-XYB8&a<E24pV6]A]A#n?\hdc8?*9mL
j=lsMHK]Acr?32I[:L:a6!4]Ar+%P+=dI%"nQh,Hd()EdNeoZ`dO$*OR>\c>$_3]A1`/Rf=KuZE
<QEi,p(MGnC2Dh,j*Y_CHfcjP(hE^S6o'K7Zkb*4+q_!DrRoW5c(6aR--$B=2XP.la]A+@ij;
gMLUQ0?Oq_,_.\S,o]Aq9]A]AtA.8n<%N]A'MEBL!d0MtVEe8H&)5u,(%u3Un:#8?H[<L%T:,4cI
?a;nc"5OE4!ECCT-fl8LU@FKI9'@7#,WO@b8MS9?lJ-I&>C/BBtVW&m!JMogG!`?GqOI_f`;
B+1O.HkKU%h?)j,UN;*8Sth3Os3F5%UA$;N"4%8l'R77TGdegg0Zc&Un241%TcLT3S9$$ItW
n-5g=OtmP7SpU0%)9FA71j79K&Cq'98*Xs.!#+c*9_cb$7TP_iqtY3.oe8f#Y+)+J*2OWQ9&
U%?0q6LE;1HGfEiEWt^%HqU*b&=(U"BYmof9/J_I8)#Znr#,VJP*dgV*ZZquoks_%Ls!5>9G
5TH:9f:+gTE=$T`\BWg8FGZ@fMW-BrC/3&R:NC31P0nS`4(`1gPL/OhH:_QB/]AL=K0"V9/+,
ISDZ4r-n%]A`bngoH+22PlKfC9a\ASEZ,-b0`q_$lTuhi*>O5`/9+(+o+m;=]A7[R0EF\OSll\
U<(-qXh,t'N(Pnb3qH+$6N.iB$uX9GtR;c@hKf:r*JX^8$T1sc&2f5JEAZl5YG:7OeJ4+5WS
#aS`+ASCTp.%\aUMU14ac\(M:E.j%2CE/&oVCV^8_ao`Li(IA63.*,+P_.Kcl^MR_V%M99Qp
[LH_1T@(b7&5JKGBnMR[-D*HQ;J0X[,sr!R#nK)`2tZ",&(lM_t0T4A5":Z?-A&S/OR'70k2
qe\M#s%TZ"aKhjC&l-"<OPYuVc?MEX<,XcnsgjN$Rc!%7<Q2DJ4/b/uJp_dH)\41G[dcc@@Z
,S\i0'ubNd<7h2;o9,,%af5j$d)]A[m`<<V4%sOIq6;@L;p9bJA+C<<kWgahChHcQ%Uo,5&T\
\bbBH!WRcX>`W!mFW5XJ3dhphhXcbL77#tt7*'HJ\0iagce9SgK'"Vt4^:]AX6@q%<^kII&5G
2Eh@79fS><\HNYAPfC@K[XRoB2>XT9kB?i/Q>>p^7eGrNn]A)!fa8P+(qTimSgZjrc`Uf=gG<
/Aq9nTn0Oou[0/Rp7Vg<P1sfq417VJ_Ht$uObq4&Wh!*q3=bZKWQ*ggDD&d_7_cPdFnI0EbW
#*t"m'-qY3eQ);QH./h&:)c,?[r8qS@.&Z9B@BTEA[<cRE+MET(Z:8]ANX,JD,jDDGu^=;]A9U
W7>bAq2n*d&\#,nO/OLM%"hak`"mS$6)\R;Bo#"=Y-nN_Q2IS]AI&G'D31ruG3Al$-GTc,R'H
Tl!l13VUAD`9YH?l#et&>C017A_6i3Rpl/Kj]A8WM3M+REh*]AT_b]AX]A*,4hN>I0ODt=unbK^$
f5+_!nY2kZ&(4*&/V$)`O)<.e2f(nK]A7FmFdQk:&(.4&EN"N<F1u:oFW0@t6c6Pp2cerKapT
,MnT-k@;$j^2kCa2[kdJ/7n3fV\fp`1n/bL_=HU-g4YK0GHJg5'd3pbH\u.SXjo@e\-:9ErC
T'O<OA)UN:1'fSNt7b`i7TXMRO23m2_!\p3Do1h3Z7;*O><q?$P^fMsGN++pQjjRKBAN[^Pi
3W@#B<AJOQG`L!fdCQNPG*(l0-E(-nrCitgCJirPl1bPC-mq4Ue^M1i+/F9H/qfOk%I6/2UE
CFS)c2BS<+)8`b$$AGVog*%G:OJ7IkXF!8hVlK:*b$Yl#icC+uMO`-!L`O'%[>,n$r9K02R4
CX!X-C)h=*=(g%6"D8ra:^:&CYs"LOArM"80t&bF1):j/liL78*H#5Dq*qU2GT:[6kKAJ5]A+
3bq=8Xa1;YE/:qKW8>4&1L"i<E!uY6uM2Ke(=4P*,?PX!S.1]A9"&,J)kVckM8,'!W4i-nm+B
A)[7gZdn:Wg;ZF2UeO^oE'36.W<3@k@W:8dQ@L8t[=(lP5'?tb:#Ma8ubDaIS)>k?0R6PC^#
I5nqag0Qf_[mDNFiK#1dGl,uc"Vj)0K`C+mdpjlV@*5qkq-\M79>L(=1_B2N<Peu[*\`k/SM
a`##5c%bM^PaYttP_'3&"T&oOcf*+OE:o*'ml*7F?CPN<A!94TSS:)D<`-EB\.3]Ackm!a*Ed
Yj*.bVMXfk8!Fn5.+?mr]A(K)uIk8+SN0e*P'8k6*"]AbmTW(@QOQ'%OY[&d^:2qBFOB0j1@B>
esOge5plm.'@EN=bQGYJBJ:I'45HJ&IXu[(u#,!b,8;ooB6c_>mXs<F"Kf./k5+NQU<gaFks
)%A+KCAP[-(6WfTRTb/D(VLQ]AiknZOKBNIYY,"ZcphnK6oY4e/3VjF!-^kj]AgXAqoMItmGW>
bkN_/kC[uWV"d9W947FI"6tWp2XZeea^MbVa(Oq/5>Y@quWT;K8Kcn944]A`,2%r?Qkc6FUK-
?M8!9#ZH(M2_8e+q-4E2D:##$Q-XW;Csa1l<,h9l(JF_\ANSr8mJ`7d3HKV<N0%/2B&`(DF6
$;f/$OYH<b.m,D%mQm64BAVOmp7h5Q2KNVbFF$f?UK.EUQ?<^R-W*;s2_ZUL"(u]A1hh/gc3[
OB4dtGbP5M\!*j6WQ0<&)c2ZcH'i<Aoui3bGRLcur"!p>fbH_c6qSLiX-q1167U3Jb,&Kr:o
8^t`EZKe7gHp$Cj]A\fm7Q`mkQ"m#f0#CQT-6UT!>]A$YEWF&]A_:,!3Q=3@)/%hQl?9q0V.57S
VEfnVoWL^Cm"lj!+"=^7QZo^h?Xhu2ish^6L)q:!=$o#l<$sJ3gRjN&)Q`iYt':2A7U@G!\%
VF,UpRi*^Re<J/bEM\1tJO1;k3T"L7PmLbt9r#^0QnE5E/$8&(?dWOT3$i:[Y,WMMDtHkC&+
J8;mWfIJ\qY6="3/&-4[eA8)t"@Z"X@QgYnHC[TeAR#46JE)El5]Ag>>AQ6j-TfOh\"2sX70S
'/>!:GS&3lEI.HA%kiicSR/O<GJLD^['B`aKed<uj3nlk/^&6cRBb3Yo4]A@L@N8]AV)VleK(4
s8l:4>2A,<#ThM.1$e0Vs,J7S5;:uTb!Uk0gHEZYp:?-"&9OJ\N8P6",[DKjQ'nW+;o6]A*M6
(R_b!lQ7*4crSd!^2BK$\:FV$c<ilO^UIL<ra3UT!2?\,#jT40*U"E!.j\?LE/4*Vb')T7?f
Nq0a-T5Z)7\n>=mH5Ql!D8&A<l%QHLP2+p,Whe(7AeHh0B:jg<,4-1[SY4$GX!n9l$Gl%_Ea
@n7),fi;A\<PTTETiLkOr354bgV3OBSD\=D$_Qr+9&!MGqf]AXJ"7`_K`Phd=eG".ddPQ)LUr
<gbI/&XCkR:4q#e3GHV_^&/-$afV3oH<+"Ws)k[<?I%.j=872]A$0LZccb-aE$Ba`?$I5a!18
V0D8s-@6^*0/__t[l98s3MUg&BbH>.DI\1ft\4ST-LX<H69r:6E@K(laV^?cKb)VQnM2[B!n
/gS(;A,,.?OU72=8()H/fDm,(0@Heb<Ijg>]ABiC.iuh=$6.e1eQ!H^9>$nNiN'us1FLXY_!0
i@Y!t=<PR2DOlFjo.P"8^[V_rW(?KE5`BaPa4RSD+b'=Q-jVIWHSMLOB;E*%VRUnV=b(M6A4
=$>:BrW<%#`?)U'8R4\WZJZr5DJA0/ASbqnEs4\XP^0EQAOK>`-*%eD<@kFd:NSN\[FB6o]A"
G-V%(ZASP?O7Xm`^V6fKF%&Xd2,kKcrX=2&t4@pJ\Q[[5S%15+6P@eRfD.Hj>\sM[Ui0bFX7
[RZTFcU@p!CRYiXtpjIkl\f9&Sn=Kcc.XXrDcC65:C(A2HZIoSP:E<[Q*#iX#I2jUc!AAUJm
3%<8EkIDA%*]A2hM<$iE(\sR0s"'5--O>ROEE8g_PmMM4IYKaf=.!o^F1T)V&hRL'//T[t.:5
trld]AsV-M#71HC(rJl9Jii.b+!qQ1W6XWaP>e$l-"-4YutGpX68_#.UoTS=qD<KSf[@-@2d`
,tnDpO69GiRnuYHR]Aa2"R4$h*//JG#rX<q21OES%CGK-iAB5oXg\F9>A1.Vk:n@$!&VEXZ6j
mRc$o*kmoS<L`EIST@]AkfP8-3<jh.ki]Ae`6HO7LA3BabC\364sZ<.:`PbRoR(DJck`3B%gQ\
Z='*Qb[bcff+H-J(80.BU[71u8Z]Ao%:r7<Xld2KYi4'UL54pQ.bZ3WCtqgN;K36V!@;dZ%\?
d4O'(T`4^"N`_Ao_nZe-d;l.m'l&@PaC]AC>eTBO!<~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="8" y="171" width="388" height="204"/>
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
<Background name="ColorBackground" color="-16170125"/>
<Alpha alpha="0.35"/>
</Border>
<Background name="ColorBackground" color="-16170125"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1943100,1562100,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,1143000,2743200,1295400,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[指标1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="1">
<O>
<![CDATA[指标2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="1">
<O>
<![CDATA[指标3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="2">
<O t="BigDecimal">
<![CDATA[56.7]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="4">
<O t="BigDecimal">
<![CDATA[33.6]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="3">
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="1" s="5">
<O t="BigDecimal">
<![CDATA[32.7]]></O>
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
<FRFont name="微软雅黑" style="1" size="96" foreground="-11544073"/>
<Background name="NullBackground"/>
<Border>
<Left style="5" color="-15870984"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="96" foreground="-11544073"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="160" foreground="-11544073"/>
<Background name="NullBackground"/>
<Border>
<Left style="5" color="-15870984"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="160"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="160" foreground="-16720129"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="160" foreground="-14189364"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<X+]A;X)na;c?__1g(]A-dYZ1GR;aK174LA:V+ihDPD;i[p7rj8Se.9LAs*pu:2)CnAW>fV<(
(:i`R'jNa[7+?;6^g+&Q"MF,%lE&Q`PW6qS(XpBn'2jQ[ee?f"9f3*:E]AQSp/0AE8<X)lFa+
V`f6JUZ=Ur!ET>&+J`"M3-NTNG&Y39f)n3r8A]A)+8&S]A+_k]Ajl3A:MVhk"W8Dfl+=Vr[r?h?
>k0i:XP&Y]A+%7$#&=8^=R9\Hh>bAgaIb(]A:5t_urm[h<?<:bl%6ebDC,%0B$bjOf7hQ6!^:K
n(ftuSrOAf/MCAs^F>+srDKWO;('g0V\:pl#K2K(g!/(Sub>P2%Nq#H.Z<0g&$;RM%VBrkiD
I6#,2gR`4=j/JcEL7X1t4^M;-@IAWaDCP>"h6)=_C]AdY347^V(cFEAYYOO9W[,24j<H5FokR
:2H:6,4b4YTChE))USgeKpNmEA*^R:SJGgk=tC\U>i_[a/V">lM$,1'.(s+'q\_&8aPL,Q$!
7YKfRLE2bZ^mXc=1Ysp&hmE:e#Z8t+0el=GWr>eiOr1s3KQI$NbffU>$cYJr)="Z$CWLq>)F
A;g]Ah11)<9_;8H?1KpnMAjXr't+o5OTek-Y]AYu<#@CaX0AXI4F=mR"l*2Uej0#=D4a:ReZnW
dS!5hi@T5?=HY/QqW+K`2KCXfqdJe%'GC+]Ak%-39\jY<B9?H!4VrO_->CVK0O)/i+P3C_(A6
1\[*>VYG/d7CB%(4U3r8Wj$WJbEfabUX$"[ONbVH7I>g_gSZ3BM446=7u!;TN(QjY^plHWUa
P4Emi4=tl$->@G@!1(l*0Gu`M1Fb#N65&D8J<e@8#g2ecZs!e]AV?!=(,b<PSi.k2l-`FgbUq
Fds1GU>hW,2oaK5IBbc^<]ALKukeDa3'n;I7+^#nFLLJgPO[a.X<^<IS5Z*@C;7EL#k>Uc<u8
h$[tgFbH4k#(G3+]A2CQfP/*K2Z->ZlV]A,[fd%+!TTC*FWDRbEXE*]Ajg1WkY>sD`BoP#[91Mh
^,(Sar!InO(7/smHp)=ES9?p:S[7=$l6RuHgY%56*3W@QM=Fa_[f+#Qo$DsMp-b9i@Yi\JA.
HXG4*Zo>/%AhU2jX^Xbc51+*SZ3mft)RLE6dp4g`DGAU6W,'UES@*nZeifW\'%<lAV+U`':A
`b3b9$C5r.@!A7!XAV@#J..;lMXbD9^J`J/7N^H0O]A/@Xh:OGiP:R9Tr+e"it@WGolCCA4hQ
?7@6>:p,ilo8:s,^JpnVA0CRQ)l&dOlQ%1-BnEJ/:2hFe$BSS7Vq&Qoa\RfOK`30"ThR*!^g
SUqVX@OPYE%!=Y0pKgb0(N(]Am$>*LOL0T)+frmAY2L*T5B:1bp(U0FaZLDg_7S=qip9oL.nU
@\:gMh)6F(H$bEl%)j$F0dK/Zro]AtAk03\jB$ksi`+CCr8Z/Wp72dFb&H>=C$&@-9F*ZMN*P
%Ffsm)1?&+s3S(B<4^djZrPJWHJkrVG@G66GjO7m6=5RDR<k[C,HhlfW$V<k9UYR-Jgnd>/>
sB589:iqqf/[E3=%:6;jl)!FZp;sR5^$J70e`c)fUiD>iUY12b]A)P*_<28=n$`?JnXH8SYNZ
t9;WQIBYPXi'0RdT0'<P%&=F"@H.tJ_]A2jY.N:l=uNGS'[.2/aR"Sq_adr7PLog\>-A4dia(
/H@T]Al3h\Upq$D:UcOI%-LU4E(6q%dbBeB]A^1;***nl-oN;[^T(<Ir"#G)GRh"`'CI&L7@YY
t?@?<3ea>7q!%WEN,rBd\H1n]A35WuaU!L_>DV!u>B8QW,!kbMkFU<pXDo[%oiI_MS&a&<"fa
MR`'t%LYq%8hX$kKYHN-jtsugTnC50OLeU_;bpZr*nftlG^((VLVUH4&KVtYB3+j!49l5sCZ
JRmKc(+Tmdh>2XB(7i(c.b/ku08$Q+hnu+/Dg9G(4r(oFNs)]Al?4f,e!ODb&q=dog`93Pf"L
t$g$6WgV&)r@NPomYU028Boh#S-5(k4.!AO+CG@V,nlXc(6X/Jfg@#O6MX;MUq$nh\hfZk`H
OD'i.%1+6N9>*@4j2&r\'0\1LpKR[3Z/ON1APpZqFnY2P:>=kmV9)IV&(^VAb3ARN\5i8#q&
-fKus\I^36R)Gh&O\fHTHYEL64&W=KrFGjRl"OL&i)JlP9N3O_hQMA_P`n)q2"YncKrDp*lM
P7&-,0A,CTGuiQPDBG:j8OU2g4[CH7bHO<A>]AihQ628,<BHEAH:"I(E\8+hV.Gt+P\6gMh;-
T*nm:/S66Ce)"ct`o9pO'E#\EN2DAY\+jKZ_Dg'PTfj8]AqgsE'+piJshd8o"'I_`sQ"6aOE0
\<f(em8o]A4.Lq"_O1W'CJYb%E6=2)25JF%lh6]Ak8<H76?-C'$[YV@el/(`W:racp)i$!H4*;
7Rs.>kEt8(u/h-CCU&nj/%/Vb/jC,d4>9]A@P"4W60U%[BsZ42;mE^I4`-Ft.HXM%JG[9@'t?
#E3fY1/pFp7eaOGO^Bd_"DWi#t"g$WsH1E>2*j4I@jY-:,Q]A2BDMo$m#4ctM6a%IbI^+B`\T
7YD)C\Jj5oRh'[5o-ugqZa-f(RKOc']AU#_uOUr+AS:au=%\h6n+N(M_a@@gL+7_Nc!SZp2P=
r42p7>T\?o;a@!,c$7'ZS3a1VOiJ)(\cfdfQe#aah-C=J1e5cfsp4,3F$(#C&XRPVcqn=\u_
U,=4hJ%K4<kgOM`*7eaD.F!`T*#^7nG8"SrHZQW+/cJ5&Mr8Q%W5@msSaeo%7\&CCNL`L5YV
c/0Tj9KQVA?c#oIouFfNZ;b,8L*4ie04FQItOC^G4BrA=O\B5;6Eqr-.dBSc+.JW'?kNdJ*S
$NJ'%G_J<Os8/Mn7S:+l^rSt761<M<Hr+E\j`Of,cC-XEIn#k*H3jOj825eXm%gl(ZNDGS"B
8ggBq;5/3gDj)W.Q6,:O]AtA0p:?6<PNlnJ&($AGpSo=DE;ZJP"TdU_=]Ap&J#3WU^K`_9T>`A
ZcVhtoKRBl*pigO#EJ9?AOL,7hG$/<mN?kn_&U;Q>dM,Z3hnH@$[aSh'3&/"``6d?c=(TIU4
0Zt1/0'%X!]AFLR3B`8%;CCp?II'IZ9nXbN*lBC9]AQ!.aJHXK#COQ-%2"%TJUpIKLh[`5irX[
A$M:^ajJ(7+4f[fSKBO)IW2qnkp2u'@d21db9rVA>i>[cpo]A<D;!6M7r-ul%T.;Y6EaFuKke
2ejlgY&ZJr8AA%SYefeQEn2-Eo]A4CQl:92P5e!c>7G=k+nllED4P>t1,Ja3aZ*g*(GXR]APHu
qD-]A<9hW&fk"%L*'d64P3m(PXi;E":]A%`)Kacqb>h7$LmEDOM9"f+QT5t5-M:BhnSAajKB$:
I@_0/)E4,0jFR.-hfO2MOXuhS8L%R2\XX4;[E5fWO;q]AO>ne+*CsFVM]AnC;S8,A&n*%=npmI
cQO68=0=%\7CW]A2/fPLeV^)J!Ij)NQJ.O,bgN>0X",1'oG2*(@V3K\\`NQ=2IWR]AL'/=h?3C
:r#q.a%Og?dfPGorlQXrpH.A6*$eilIQ*-dD\AGctIQG6D%Jl/3*,=42X=486)IJaQ+O8*fD
j!<,fro*Z$PlLSG0W)km(BS!r[;%A'\.[u(!!&To4Q2jf-T&c]Aine0RGe4\l?kA:a!7k/DR0
T=\3j]A4or;A;4f?UVtVD<t_VEQ:K\MUT;(?2JQjB,uM0lcANYJ7RlAd&\F-n*Mu&[-mSlFCO
5(`9niq]AB[jYliVZ"bhLW!^0W-)><L0;gJ,;cdm&r[Wenn'Ac"ScCUUBArBEggZpSY87PtQ7
XH[-=F4bRNUB5Qj6B/-C\cI?:UU+7+YATh-Xfk;R.hDOZu1OkXNI?Q(+d>0qa$Qt:da0Hdn%
oe`!Y/e#[2XRgW'!I2^?fh9A@<Um58r`US`'V*J;P/>qJG\^W'gKg<h+??n#pn3V<)4Hu4Y'
T#'BBB<]A3AbTHMA`;B.D3-?XhYCD[P9:E>on-fp?3h7M8b(r^1KJ;1tbWmYXSjC!)t-L;M([
TIL?J'n:R43XOj!)"o!1PrE9#ls]AJ<K"ID7'CjT-Vnh2b7%<j"eoAt&]AS\-s\-EkIF6C=07n
^uV-[BJ9SCUbkN@LS]Arc"Q%(=QJn9Jic&Tul]ANMcW!CWT#QH?nDa\r$IBfpC3YOc5%I^JrV+
Df>U-Y9DXi*V.B0[ZP8orS3XEe+^efG8+=-W.:)b>.WD\XHF@WPW8,5RTt[JmrfsPr$$YiLV
QK65$+mpI07A8oA+,ebP[a*a2q3]AT"+bsc\e(F8'DFInU7AU8##[OjAk.I8B9^38ohq;aRD#
U8W,KfBC3CZV*<88W!Q?9.""L"K'OH<8GXp\(3T@#s&4]AZdoRRFEJ9NlDp#%cZ6Z]ALAfe?:?
J@]A<!*1=apn9n\,H"XJA^tnK2992hJ]A\GXY_4EKPYi_9#D$TR5oarHEKW!5$H+D2GF6PgW4@
RSAomPt*da/B*qM7k'dQhfKHZ/5(MY"d_D+o4u;rgNJ^5?Yb]Al+ndjVn'E;!2Zee_ic3"q>n
_n?9H8haQZN9h0B,"49\%K6&B-JG":)%2jTV=mA8Kll&;Pii`fImq7XK`cHliKCI-f$\el>c
dd&?)"W*XS&]AjL9MGO-Hk.0up.&*W\69aMQ2<uNL0\[dXZ^#*-JtR*OrsM=c#,nC/u6[a&A$
KOlhkg?,s<BSK!@R4WllN3Bm0E:M"t<$n;J4.MD4ZoXE`,q'\tl^.&?*6l1?rR;).T_bmAt0
O>&V1K#g0jk-`YVhg0Y%%=DGMj\!c'>G'CaD]AqWM8d-CT(EH,F*)f&YaYR3W,,%<2':pdJ'U
,<2@@\h_F8NN\%u>si4E;sA0ekg)gR:pZI+pPC=!EV:=7B'V(pEOV6kY7n5(@op;i@]AV<W+g
7+>&B=3)l8=0rFaq:#eUI2sDB*5AbY@]A.U%FC\Qq@[*IT1l,\P`L9i>W_7^@"7pEa"]A>eNee
_GLj5P=MfQ$VT.YUO-bql:Es6a%nY&XW,<*(V)P^V;/*P]AH-#5k4b-=_keH_9sn-,,^=P,$2
VaZM*\G>A7e$r.&"L4EF<hRL[FUUOdGiA%q"):fGsM9:`gi^p%*Rd.'jn)i7S%9b%^PLL&5D
hJIi8Dj8.C@^afII\Fs!qTI;1N!G;ZK!-h7dc1(GP0\XP*)'iQA3<Tap6;hiM%dF@B?XC^=p
m!U&1Jf7<g@e)8dW:e)\+eP[TC7<-o^82:&2AYr]Al3=8g!aH$51/6+Gp=C+es6?H6V[t%en\
=]ARa6jHk5OFCqte#L\js"iO?(eja?/OG^;s.nE$.Rodpu-hX9=c0^&ih-#MO%>3PlMh'7F<T
Gi,6j(Ffo/8'sVqT5jU$hH&UW&NJ@Y/BZ>*-*J>i-;+n&F=^r8<@:@i$h2i_9;1>pZXf>aE)
BjQ7!0FJ4'%up/D/?^=t@I!DV\$[Z<-$nlPobfrM,6HGodOc:mE<l.UJQ41g-NE'@aNj[Q.Y
.n_1mHHP:FpAMhXWM4LY>"I@gJ>%V\Xps!9.rdSthpP/W$Y*9ujjS:Zr&&U[;_KPtce9Y11>
tt%iB?QjD3<so-p=/<hgI,VL-0,6.$1Rm7"c%nX0'Od70I7pk,8*'EUCHu3%L.A3p*HjV\Lf
/hT$Y9EV4177l.^5V9Mp1Ieg(7>GVF-`:lg/p)@K5[f248\p`F+:2\g6T*6`W!_)=Y&^-FEO
[Mn>aR_I/jc\&K,7L+^BKes"@M2h=&kuHZ<7-*u?OI4[D&3[t;ht1WMV<E<[1RPAMAc6%[ar
_q0RE0?ZXMIa[st:7hZTaNaX3-L-gT$uF/k;-I+it/VAdkrEWJJhF!$m+/?G$'"qWH(.IP2r
K?`Vj_?tTK7Al]A"_YHdsUa>c8i;D.sQ-12'dl1>FR5i%^5k?KU.UISDS%//<e'/]AZ(MOhp5t
7/[1u*!nT;7fX<4Z>8iVhV'"DWE2np@sW68gtV[&NZJ9>d9$Tr8]Alk%V:Ln%LQ"kR*`XA"A.
#;HC8sQ-t('5GOu,SM3(W''c^qc7IV+1=bN5(?Fq5+2/*'5<u-Z))0T1aNYaEIF$`LUEd2*a
lljK4#&^B>J?MC5PTUuA`jBf0o8S_)A+s*]A*IW:b[$@f%Kd8/I4_^eVGTA5CmL)^qkW\C?!i
*(WqYC%afi@595Tg."7dfA]AgP!?PBYWimD*K/#&C^6!kfWSjefdK]AW('CZ^Icd?R0Vtq2npo
N'g$t;+mZhhfFk5)UAMUa%P\bhfGTA-8qT3C!$YJqgLm5@2MVcMp*$%j$;hh=PEYg9$&ek2!
,YXrgk4;g:u).8"3Vm=P"S5LD'D>+?@G$,l(@m"f+t$]AI@93/fqUNC]A@_4VS/A/H=Ao-U<A=
&KO;Z<P@VfoOE:cOc,!kgA(UEBL'`%hkKgeWCZsEi,r^e1D2B&fO"p6AMp@:;ZECPuY3E*5C
MO^&5':A>BbfK(T_Q=@rV._k(!t/@DbBD0Rp:jh;f"U&e,cdUkR_f/4O"[EgDb[Ei'K[iRP.
7Z1KU]AbKLUtr3>#?pm<b<l#opPZ*;eJhA!$dWe;nD9QZi(U&NS+%-Zn!Wf4Z]A2'L3M:$K"S-
je5/plQKb7Z'KIfQg'pHlPhP!Ieedc5SW.kMAhs?FXs+sef=He`+J3E4&mrb0c6!SK-?8A=/
S3G1"P,T3NS"t3o^+:VV65Af@52/0<91"OBK*E`)V;8#(i4mRa(fiSltG$0VWu`kR0_a9PiF
cY?-?]A%i1pYc\*4B-g=_/s8F$:n^GPLZ;*gd)bhBM71:iAOO\?SFELe84\n=l!fR.h!n_Hfj
pNLaZJK!Pm'Ee"R&=<??l9u;d9lfuUeDeX3ps!==9@l_IY^7^-%63+r#B#`3]A.#QT^soMG]A+
@**E@,V0ISQVT^%@]Aac1>Ni0D%=F*o"PYm4Fn1H>D19XcPRXek+?7J?V]A!$U->.pnp,[l#IO
qDB-?UJ8XB>5%E,U<DDXrfqQ;+'mRp=]A#,9;`-hU6>a#LJf(;-1(B\@\i;WqRqYhY%&C91bt
++CoGHc427oPj-P0;XnMTuhN.BcM%.qDO>dL3lle*C'>G%FL]Ak'.5]AlIWO=>$m,\=J1(8HdG
ZXs5SIJ?5#l@9ZFt4/n8@OtL7O)or[:AbJs)9`a>qBG)\oEb/'Rkhl%L9KrH>LEQnR#-(su>
jDXJeH&_#;j?H'8-N<BQu4P;fEqC>"+;L#P;h&;+3%PKrR?j0qW4uo2#$R`bBAqch)&aqY1W
=.rUj0T+,(5:Q?<AVH)P6;[(cA,O"Ll+b[c95s*/@)?T_3\"c+oqKP=%>lb'L/W=c3hVNmO8
;_N%<P?$+`[m.cs`97F]A<ulhEA>GP4>M14LEfdk\UT_pnY_Vk:9Trtu`[5;N:"C[g4IIP)Km
f=Ls0$O_?,\50]ABRS$5Xn?fhfL.THZr8_>Z9WY's&M0psl9unEVT:1Vk#G4P4U!+-NoKjDo-
b@Y[)\$8QT1AVp'<V8sWUXI2*ldN<lMaSm7bj"bXLc[`>t$QFc#?d:Ks*@n5fof<B"Vj3_BP
=ePQ^qt#l)i$!d9?]AIXDhD?>5]AX^^M>WIOCiOIHdm_8]AcK!73rN@C5QI'>RGj]ABlEs"s(B_R
(.B;7_\@1[#;aM=\?r6QT*M!5q7,9(fW!)Ep.nIr^XYm6C6T_.anIh:(P%jZ\aXFl\5E\Q_5
]A^J_NipYBZ-6S/lO+QYh<6^&kkim`@&<<;T)?eJUJQrVX0C3&\Tb8jtKd-+Pa8/_lop(&#P[
-bF_&.NY3WEf'+dh-@NN='J#&2]AcbMiJs/aHb(04Pc/3_9/JnR_QHN$c%6@<>Q(4[9.,Nr]Ag
W"8Mirk77Z&o;d_Y?:ulDQh@?;p=@^^,q$aYm2QB%URk:8XrBHf)mkC?Qka)8H6&[oPaO0g4
St=unc+0E2p3:n;5;P(dqpm^eH4o+58[L\]Ap2lXL4O#Kq`>Q*78)lTUDu6NVYV!S8Xt8%,o5
EjE?l!Tm$aP3k-argrH2]A)hgZ]A8s4R"f6_iNQ,ur('-80pKPijN;WurE)Usd\O]A!ALkmc^=4
lEu([a,KtJ+=U?.N93T]Ac"_#S!po=Xd-UT0)('%Va*cHYIl9maF_BCp[2''XOAUg^dnJH]A3'
ZmD0X868Kja'lcg7@JF/M8MDq2rkAl"QT6[=hT.G8Q&kNkNsg36<EVr'GO-WA_!]AUB1J7fN4
^qk';W`lri#VTi'P"`be-RKN#NF-]A?Lcl[9+ZRUIcX5>j?m!FtTR[6^qX8]ApB/hoXo[gMIH(
bR2qN>tVt@bA5i3?lc/`Ljd65@Xcmpu?uT>GYJ\qBa4fe<Q6HI1%&9<=JB=)jn/4p5E>e#4-
^p%rV5!*T,$D>GsWLhJMV>>c]Al<E<;@h5FF9$n$!SQIl_%*qu$EOT/6Rfq;Op/&L6;K8"^\@
Alp4O6e$.\grRO?/\o+tF8^*I;W\:A4?YD(-jJ?Ji#`8t2?$r+kXW#ISWe>:+Zk/k#Q2)JFP
W%dIfG9)!W~
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
<Background name="ColorBackground" color="-4144960"/>
<Alpha alpha="0.49"/>
</Border>
<Background name="ColorBackground" color="-4144960"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[m9P'<;eNO'+_[ZPYf#k)O+a<qBt7/h`RZNBOJG[l>00lb$arTBm?h',$K2^B%rBj2eWO$PV#
k\EW2V#)[Zhts,Zl.F=q-@Snk:pg";##b+n+K`TX1Y6c)T[1eo8=@jH$lIh<1<sZ%)Z@g\X'
]AqNmH-3=e7G!.l>7QMgR?2\9LH"$uFKbaOCe+8[hf<sZ+0GKJ#]Ar-)TBp&7rqX1Oi3>lJaIo
@rKPh1`1[:R>DbL:N[q@F)?,Zc^UbDG,E2o>0AtCWinOhg0,d-[bah^SeAZ,>'gdTd]AX"'cp
?*E%&r_.2hJ/YB)hPX/%=7%l"%JAZ)l<*q?g!?>K!ej9d[9kjOF*?EPq*'BTqG[<I,3j82+`
O[3NBrSHT6EI#?gNFX:(GC-sjZ>)X]A4-%d<n1PmM!*/72P.e:tOiJe.NRR!uY4m6$?GYl\`c
@g6pStE'Ho9Uthg"k[EE`"/$^0<dq@p;.QBh&2D7QFgh.H<X27NTs<)Cr*H#8$qDkM?NqSJ!
kkVcVA,d04JYPM1KV?QkW("CBKfBqu\HpX$A#4+?gTN6MGVjS;m$)Ip;mNCeO&71+!6Z6f$h
\m\cADd<:BM3F>-,]Ac0#$<,ISr1A@ISnjJob1M^;/3N)V2M.5:Z]A1BeADoeX4V^;90F31Rm4
\lT8G6KQf`S>>"?9e:l5Mt_31T&m(Xp&-+e"o+)dp6?isQM*Q4NqE3DqtGc#rsDn]AaZ_L@W!
f$rC$\%>"aH[d<EG"$P/;B\^UGQ-Vs`dNtIC`K4j;^>t=kJLgC_\8%Sc"%`T0p$lRRop?kW4
o(^+7;[Dc&cuMMg,=-DjOP$(c\Bq'0SuhNM[,(iX_[]A'/-2Le\D!UroIg72R8KuE)Ap#/H:T
i2a?#4Ei^7)XGd(O\b^pW/%*jfr:YnSlck\Mc/`SVRn-X["Kr&73(6c'j/`]AZ\]A<*Zs$fFm\
Ka6`1\Wc4J<r.($R1]A_NC,.`I)d2?d:b*@q,^Zu0bSPKVk,8M`*T:9UHd7_;S8Wb9.^Y9Q;N
]AEMi<(@c/tb/&XuK6^LtZo%%d_Pl0X8n_s5u(e#)X5A\qac?<)h`3F*^"XmNEU+%nTgZYs4m
:E4;g2msH]A6G_UmRnS5URZZR:p*X1;@@6)J.<$hSdZ3e>/),+%6d+@.lqA;X`8A!Vf$m*GFj
&idNI*k%.JP+@<3q@'@WKYOM&,+W[ebEa04#[lBhW,JbP!o()h1Dj:VoXO`6%f]AfDZV(DDmI
&V!(4)HHBeK_jj?V%DQ:S.;c3$34P"b/Q\he`\/lGO6m$nR+Y>X@ISW47&J2;@TOGbj$m++V
6P(a[GWA]A5+tuXGT9p#NMcGK!D>/Hk_j[KcP"1Qj.c7#]AP&2"gikjVHtqg^X?.*49DK$OSW;
ulQm\9=SAuuH\QC2qDo&L"hZ6iOmaGbC(aMoU+'K\u'X&u''tB7[mg'SAJZ&+;hgY,,RZ..d
hmL1F=`CsVfCC"bH$?Yk4_C/@3qB$m?<F[1n8Z,B^fnbb5LL_4/U#HSq<G,H>@uA/hsJ4[HH
`XKX`p>.DjaV/L/Kcg21+J:%%Tt!A2RBG<=q74ast`]A<TaD6RVP9289?%C^(XsXd>Z$UYK02
E<OheT4!)^r"iub;+%6`"%W:KT(s%tu59hp9*DjD1$Kh1#6a+`_#I-JtZO9@7YoWNDQ]AX(X9
@PX4dsh&a\YVYh`Lre4!tTJT(]ArRcndQNMmSA??TtVE/E*>\OU.:-ebH,ssJ,;63=W6//h7S
g.ZAj-rNV*m514g>DqZL5:DDu/Ph!JBt<hoP7oY<U(or1T"^Sd4mH<Ap'9X%W[RX;K)B.ghT
la`f1>6-;3/K:u':itK/78+5gZ'Ku:@AqUrh5#`AbkT00(VErV9CEA*Rt*rFG[Y'['e2MZZK
,TT2jChtQOtA$0`ul4\qp(M>qV$kZ!bT2gAt>=*irNQYkkiQ_6aeS+!ZSg7jb$'CPmQE0m8`
]A_;<D"_mRX@a^QZh%*%(0f6I[):/[8u[*>V78cUfP0^/Ih4O>>\>Vu-X%Cg!G4PT0Fb6b2O$
\3;oR3,%"Ii2DaauqV]A;^:#_[b6G<"euTU[.^ud7'o-GHkoOgYcqWkLn^%d8lIC`>7LZ>8c>
DiVIMSk-@iBq`XJQ*.T"MHf##hZ@0EiW5D5*YRPuGa[it*-=7J_#q`N4E3$OAu,$:,a$&jmU
*E\\q#J?WV_S$(9SnVc3DoI1)X)E&N%J4o-1ifW.U=<GD\N`a6>C1m^!U;<^Bu6$k;mQ;cc*
X/&7m$6="!IB[77"trLcke:q7W\G\m`t+f>'etT3j2dQ@shcop<arQL3TWCg(!*?4j3q$hX,
Z\+uf,Jt/9DjATLXUnPmmWFm@_K]AHq'W$PD<n1O3lKPUA<FF=<Q"sOkVk"V^G9^n#09%5fjc
(MVq?;Z%Z3;@kX9.06*]A\b*2^]A\m>X_US`""q\;>Z-F)-$auj8s0W,F0Q:X(A&>(KR:t0]Ak^
72!I3#nJcV8Xd;dbgg*I;J(EtuUkFt[]A]A"m\n\#T)eD-XYB8&a<E24pV6]A]A#n?\hdc8?*9mL
j=lsMHK]Acr?32I[:L:a6!4]Ar+%P+=dI%"nQh,Hd()EdNeoZ`dO$*OR>\c>$_3]A1`/Rf=KuZE
<QEi,p(MGnC2Dh,j*Y_CHfcjP(hE^S6o'K7Zkb*4+q_!DrRoW5c(6aR--$B=2XP.la]A+@ij;
gMLUQ0?Oq_,_.\S,o]Aq9]A]AtA.8n<%N]A'MEBL!d0MtVEe8H&)5u,(%u3Un:#8?H[<L%T:,4cI
?a;nc"5OE4!ECCT-fl8LU@FKI9'@7#,WO@b8MS9?lJ-I&>C/BBtVW&m!JMogG!`?GqOI_f`;
B+1O.HkKU%h?)j,UN;*8Sth3Os3F5%UA$;N"4%8l'R77TGdegg0Zc&Un241%TcLT3S9$$ItW
n-5g=OtmP7SpU0%)9FA71j79K&Cq'98*Xs.!#+c*9_cb$7TP_iqtY3.oe8f#Y+)+J*2OWQ9&
U%?0q6LE;1HGfEiEWt^%HqU*b&=(U"BYmof9/J_I8)#Znr#,VJP*dgV*ZZquoks_%Ls!5>9G
5TH:9f:+gTE=$T`\BWg8FGZ@fMW-BrC/3&R:NC31P0nS`4(`1gPL/OhH:_QB/]AL=K0"V9/+,
ISDZ4r-n%]A`bngoH+22PlKfC9a\ASEZ,-b0`q_$lTuhi*>O5`/9+(+o+m;=]A7[R0EF\OSll\
U<(-qXh,t'N(Pnb3qH+$6N.iB$uX9GtR;c@hKf:r*JX^8$T1sc&2f5JEAZl5YG:7OeJ4+5WS
#aS`+ASCTp.%\aUMU14ac\(M:E.j%2CE/&oVCV^8_ao`Li(IA63.*,+P_.Kcl^MR_V%M99Qp
[LH_1T@(b7&5JKGBnMR[-D*HQ;J0X[,sr!R#nK)`2tZ",&(lM_t0T4A5":Z?-A&S/OR'70k2
qe\M#s%TZ"aKhjC&l-"<OPYuVc?MEX<,XcnsgjN$Rc!%7<Q2DJ4/b/uJp_dH)\41G[dcc@@Z
,S\i0'ubNd<7h2;o9,,%af5j$d)]A[m`<<V4%sOIq6;@L;p9bJA+C<<kWgahChHcQ%Uo,5&T\
\bbBH!WRcX>`W!mFW5XJ3dhphhXcbL77#tt7*'HJ\0iagce9SgK'"Vt4^:]AX6@q%<^kII&5G
2Eh@79fS><\HNYAPfC@K[XRoB2>XT9kB?i/Q>>p^7eGrNn]A)!fa8P+(qTimSgZjrc`Uf=gG<
/Aq9nTn0Oou[0/Rp7Vg<P1sfq417VJ_Ht$uObq4&Wh!*q3=bZKWQ*ggDD&d_7_cPdFnI0EbW
#*t"m'-qY3eQ);QH./h&:)c,?[r8qS@.&Z9B@BTEA[<cRE+MET(Z:8]ANX,JD,jDDGu^=;]A9U
W7>bAq2n*d&\#,nO/OLM%"hak`"mS$6)\R;Bo#"=Y-nN_Q2IS]AI&G'D31ruG3Al$-GTc,R'H
Tl!l13VUAD`9YH?l#et&>C017A_6i3Rpl/Kj]A8WM3M+REh*]AT_b]AX]A*,4hN>I0ODt=unbK^$
f5+_!nY2kZ&(4*&/V$)`O)<.e2f(nK]A7FmFdQk:&(.4&EN"N<F1u:oFW0@t6c6Pp2cerKapT
,MnT-k@;$j^2kCa2[kdJ/7n3fV\fp`1n/bL_=HU-g4YK0GHJg5'd3pbH\u.SXjo@e\-:9ErC
T'O<OA)UN:1'fSNt7b`i7TXMRO23m2_!\p3Do1h3Z7;*O><q?$P^fMsGN++pQjjRKBAN[^Pi
3W@#B<AJOQG`L!fdCQNPG*(l0-E(-nrCitgCJirPl1bPC-mq4Ue^M1i+/F9H/qfOk%I6/2UE
CFS)c2BS<+)8`b$$AGVog*%G:OJ7IkXF!8hVlK:*b$Yl#icC+uMO`-!L`O'%[>,n$r9K02R4
CX!X-C)h=*=(g%6"D8ra:^:&CYs"LOArM"80t&bF1):j/liL78*H#5Dq*qU2GT:[6kKAJ5]A+
3bq=8Xa1;YE/:qKW8>4&1L"i<E!uY6uM2Ke(=4P*,?PX!S.1]A9"&,J)kVckM8,'!W4i-nm+B
A)[7gZdn:Wg;ZF2UeO^oE'36.W<3@k@W:8dQ@L8t[=(lP5'?tb:#Ma8ubDaIS)>k?0R6PC^#
I5nqag0Qf_[mDNFiK#1dGl,uc"Vj)0K`C+mdpjlV@*5qkq-\M79>L(=1_B2N<Peu[*\`k/SM
a`##5c%bM^PaYttP_'3&"T&oOcf*+OE:o*'ml*7F?CPN<A!94TSS:)D<`-EB\.3]Ackm!a*Ed
Yj*.bVMXfk8!Fn5.+?mr]A(K)uIk8+SN0e*P'8k6*"]AbmTW(@QOQ'%OY[&d^:2qBFOB0j1@B>
esOge5plm.'@EN=bQGYJBJ:I'45HJ&IXu[(u#,!b,8;ooB6c_>mXs<F"Kf./k5+NQU<gaFks
)%A+KCAP[-(6WfTRTb/D(VLQ]AiknZOKBNIYY,"ZcphnK6oY4e/3VjF!-^kj]AgXAqoMItmGW>
bkN_/kC[uWV"d9W947FI"6tWp2XZeea^MbVa(Oq/5>Y@quWT;K8Kcn944]A`,2%r?Qkc6FUK-
?M8!9#ZH(M2_8e+q-4E2D:##$Q-XW;Csa1l<,h9l(JF_\ANSr8mJ`7d3HKV<N0%/2B&`(DF6
$;f/$OYH<b.m,D%mQm64BAVOmp7h5Q2KNVbFF$f?UK.EUQ?<^R-W*;s2_ZUL"(u]A1hh/gc3[
OB4dtGbP5M\!*j6WQ0<&)c2ZcH'i<Aoui3bGRLcur"!p>fbH_c6qSLiX-q1167U3Jb,&Kr:o
8^t`EZKe7gHp$Cj]A\fm7Q`mkQ"m#f0#CQT-6UT!>]A$YEWF&]A_:,!3Q=3@)/%hQl?9q0V.57S
VEfnVoWL^Cm"lj!+"=^7QZo^h?Xhu2ish^6L)q:!=$o#l<$sJ3gRjN&)Q`iYt':2A7U@G!\%
VF,UpRi*^Re<J/bEM\1tJO1;k3T"L7PmLbt9r#^0QnE5E/$8&(?dWOT3$i:[Y,WMMDtHkC&+
J8;mWfIJ\qY6="3/&-4[eA8)t"@Z"X@QgYnHC[TeAR#46JE)El5]Ag>>AQ6j-TfOh\"2sX70S
'/>!:GS&3lEI.HA%kiicSR/O<GJLD^['B`aKed<uj3nlk/^&6cRBb3Yo4]A@L@N8]AV)VleK(4
s8l:4>2A,<#ThM.1$e0Vs,J7S5;:uTb!Uk0gHEZYp:?-"&9OJ\N8P6",[DKjQ'nW+;o6]A*M6
(R_b!lQ7*4crSd!^2BK$\:FV$c<ilO^UIL<ra3UT!2?\,#jT40*U"E!.j\?LE/4*Vb')T7?f
Nq0a-T5Z)7\n>=mH5Ql!D8&A<l%QHLP2+p,Whe(7AeHh0B:jg<,4-1[SY4$GX!n9l$Gl%_Ea
@n7),fi;A\<PTTETiLkOr354bgV3OBSD\=D$_Qr+9&!MGqf]AXJ"7`_K`Phd=eG".ddPQ)LUr
<gbI/&XCkR:4q#e3GHV_^&/-$afV3oH<+"Ws)k[<?I%.j=872]A$0LZccb-aE$Ba`?$I5a!18
V0D8s-@6^*0/__t[l98s3MUg&BbH>.DI\1ft\4ST-LX<H69r:6E@K(laV^?cKb)VQnM2[B!n
/gS(;A,,.?OU72=8()H/fDm,(0@Heb<Ijg>]ABiC.iuh=$6.e1eQ!H^9>$nNiN'us1FLXY_!0
i@Y!t=<PR2DOlFjo.P"8^[V_rW(?KE5`BaPa4RSD+b'=Q-jVIWHSMLOB;E*%VRUnV=b(M6A4
=$>:BrW<%#`?)U'8R4\WZJZr5DJA0/ASbqnEs4\XP^0EQAOK>`-*%eD<@kFd:NSN\[FB6o]A"
G-V%(ZASP?O7Xm`^V6fKF%&Xd2,kKcrX=2&t4@pJ\Q[[5S%15+6P@eRfD.Hj>\sM[Ui0bFX7
[RZTFcU@p!CRYiXtpjIkl\f9&Sn=Kcc.XXrDcC65:C(A2HZIoSP:E<[Q*#iX#I2jUc!AAUJm
3%<8EkIDA%*]A2hM<$iE(\sR0s"'5--O>ROEE8g_PmMM4IYKaf=.!o^F1T)V&hRL'//T[t.:5
trld]AsV-M#71HC(rJl9Jii.b+!qQ1W6XWaP>e$l-"-4YutGpX68_#.UoTS=qD<KSf[@-@2d`
,tnDpO69GiRnuYHR]Aa2"R4$h*//JG#rX<q21OES%CGK-iAB5oXg\F9>A1.Vk:n@$!&VEXZ6j
mRc$o*kmoS<L`EIST@]AkfP8-3<jh.ki]Ae`6HO7LA3BabC\364sZ<.:`PbRoR(DJck`3B%gQ\
Z='*Qb[bcff+H-J(80.BU[71u8Z]Ao%:r7<Xld2KYi4'UL54pQ.bZ3WCtqgN;K36V!@;dZ%\?
d4O'(T`4^"N`_Ao_nZe-d;l.m'l&@PaC]AC>eTBO!<~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="8" y="69" width="388" height="93"/>
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
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1219200,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4838700,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
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
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="160" foreground="-14248771"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9>!<;eMP3`uX^&!JfK[#R%A)B?./TlY+Z7&Z"fMmF:Z9[i.ZN2?hTG?kj-iOCTs1kd6GE;a
D"T%^,0J+:Sl[$V=+.#U9e@Ji=5Z;C)mg67d\@&@cq&*)BYpH$a*"3.$\Un;k-S^A!'5ht-c
;ht+M,rSrXia8=*jRd9"oj7him=#TmLbeeaTIWfkZD!Dgd?10KiT='PX?]A"Z@G=i(!^Is'Z]A
o]AWV3<*@35/2G>J*W,EXF'YjIeU13QOh=-T6'`'?bQ#;h%D5>CJ8B30E4&7pZEn+T5OOKm^G
^R<B3&@^+G(]AS&brVokdqSH[H"3R(5t4@=:dO?d$Nnn`g-eA%*QU6Pu5gfIK+U23G1Wl7FMC
`I"F)n(mXMFba"`9#H&&h>*8tN_F/[`XpaKk>.[2BNVs,qoa=+RWD3ca<7HH"t$^_C/:`Q&7
^mhlZE6HDiu)!1I^Fdr$<OSmi9'_cu\4Q?duYf_L7KEm`dR/pes.8E`3f8)9m#('Ao9n1cOo
<Qq/hLO`C+j$JRq^%N+*c]AP8l@"h,jg\K$(6e\.7e&FWoG-OSDG9YYuo[WTskPWI,_]AqVpX5
Wpo8/"<%OO%93`TDBY3r3,Fc_68(I1?87e)ip"tUQmcV*1p=Q-;dgp[%)3ialib/hpS[&8Y&
7.2$RqN$Qlp,P@X&-"E]A*+eCHiU6rts!N_$nBmeqt;bajC5XL;n;7<FOJH*Nl0]A%9Hc,RGRk
PZlp"KR9&iOjodFJ(ClqWmH<NVl;Dbcq#^^f']A:%dEAT!QBmQ@&t)GfMMBS(\\qfD7Z6J@OY
Ju4IkS3m,nnQ>;60NESe[2![p*l"ZA7"K(GIa=4tNO#^*Z^$gPWGtgdlg+dT2W_C[i(CKkKa
!$C['k:b*U24MQ0S\od$(W_Gq(U/oHsSb]A!u/K$0b2ll!8L0>q\FRYKUlV"iJ`gXsfeGD_@^
CeIh5LUV^$7XRae'@3D`p_U\*`KgZeCqBt0,emAcJ]A-T2kBf,g)Io[Z^16)X]AV(g1#(E$Qgo
a!dqqdUJ$t6g#f*r4qkL_WejuKTP-&#DqnI6^/Zk6/Ya14.\QA5Xe!;b!%.5MeM-.VsP!b1/
#Qrb*bNe8sB;`/LY8r\c00Bgcqd#NRM2%u^UZYti_+Hn%>8^kti[L2:c@;F?l!-<Q9saiNYH
rIJ^)*\ANBL?QaCMU?;uIp(hcJcLm%8biA^V"b`"t1!ah5mIS?`&bOkc,kamLUa4j:]Au%WWa
rD0[@,=>A/di%tr8Cnm<[It?H\/c8*#(b@Z!4o_1oVR/n\%H)8_!0*]AIjg9SOX*[NsDYK79&
fh8gV^>N^0mf(hTA!e?UcB^oN:3HGE-s1:H(crD#-S>=JJ$&+d+<LFK7"SBcRH-3nJq9cS'u
+SO6%?^&gbDE7]A^,4<f.9DD,Gq\QAJPuDGJ>3L+*'84LO=M^Ka@C/pnI)<@`uP+25VVP&VlH
rl>bcp>9[:D>J?;NXQ#r<4K6-3S,b6-S:stYJQ,uGQStAhho:--/]A?q&N6OATW72(+;`oll&
KcTaU%B*TiG,LC.b_b<,UM$<i38%HL;_NB\TY_@C?JTc%O5/9I@<?U'A]A_X;+G,&uh[j6O"]A
Rgm$>V5meO=6Q_'IWOoSeC1seP&1ZN^K:EAiBJb0-)\YsZM^0C'p"P^WM7j]A`\n.,RR4M.eQ
le9ckLiE_oNLYhG.s%e$p6C1=Odog<$/$$@US+P=_cEkB>g?8_X;.8;TlkD9XW#G'$H>"EB2
K*Dm6bshnp+Qb6BAlLn&/+jIV;CF.:;kP?3N7I<_^5/PCNCd`"i.R.@0PISan$oS=@6d`-2S
S*p%D6do)TZ$RJ2QJHtfjKNXcP_&Ua$Z,/(l<m*9$g,#d<T!G)r&WTCDCSdML@nH%L%.e0Kr
L3*f1.iBlVL9+r=eC(E*cfd4HDm1WqEmj^/1tHaqSa(o0E3JlD?5402=UtldYgf+:@fW^fs8
"$aT"ip^uf2=go!Gccb3t\)d]Ab3Y8.pWWgD`PV*&UVrGRrHUa[J`C_]AJG1I+T]A>A9EEVX8=B
mZH.QNXB@K>L@sKE3$fh!`(%%<*UTAHoL,$-ml0>%*._#OSm+@DkN"$f5TM<p'1n26+$"$f.
6F2\8p^e5b(t!t2d&?3PpB,ICi3XtC@1;ZbcZ@*_K'`0VI@hBJSK9t1_(BAfj\clsJ'eX9<<
+g;tMD!SnU-Kq^Xj+<Hp#hHtl*FCD8Ui-<M4oEn%LU1=nV`)u-0M/R,f@564DuM_g?!k>P)h
b*eR@'O`HoS"SZ!Y)PESp?*Y_HuP"WSlG3=7PZr"@.B)n(EO@k#$Ne!$itK>=D5$Uf(V!qH]A
.GoT,-<\Ia%`QP?,lV(a9U3Ym7i1Yr\/66#(1fsQP'C9<Cj1DdpU*%RmWuqq_dZJU/G2"1A>
8Dn-IWc^d7:'><gitOoU2\_p[ek6V)<&5+A'e?Mc81mAB5[lu6MA;3&@gGf/f&fqII]AE#B*t
rab)%<qK!6l1OA_ST?0PsBbSt;f1ND0G+^!u;K+c`i%q$t&)tmN]A3-L@=;5`'t0)GWEG<,Nb
D0#TPm@=rQ7(58P8nGgLa-7tYR$&T=Tq!k@e,(%%5,VA%aup]ARAG0@B#W`9)mjuJkH[c5(ek
UuZqJct+3E1MKjs=RlYQ/cGe&SJ"?<uS5nH<@d4g.PI,L%&&g\LS:MLZPkQsA_i&8(FJf/X1
TZhmj@5JZ7n3C.81STPG2.]AA/XL!n/MP7L0c+q,]A%,T#brCb_77ea2$7QJH\Rc3:;'CXgEjE
Vf`pbE!k!I;\S`:'\33Q9Kb^6hsf\W<Ed!J%N2J1eaA/-j.pI<NoUd9</o8"9L64IIblTnjI
_cefL-(Cgt%5F32'0[iKR96rF('%eY2fkdV-I/%S:Y+")=j5!oT#`T^sadb]A/).cW=Jm;Pm,
KKWLmi;muk9h2B%K;J/!.XD"YL!.Z'nOkXmoMV(!rZIBqI+t]Ab@sKam%PP\t6ink-1mZ*dau
5O%&gnn2a(`/VLr@7NS@8a>a,Z%1?p5bieA`*J<s:(6<aeo@fIOYrqr#P^'#46b-8:dkE\tc
$F3P7`<m5*XP*gJhD?+3>*@&Q-[ZI&[eGM/,eG\pkQPr5&1_JWk(Pe_-/Rf5Ka)/7j7/.lXK
c57tFG58RPZl`Y-uX44gb)86oJL/))T-5poM"O;S<g83S<3nU;F7@LQ1&2DKQI5Z-61:PR^T
6!_0.O0W)hDJ3j'SX$%pI8LeI$U*XmP]A.lb?cR=o@oPl?2\A=^iG;O,NF!GhJL@39/)'&g]Af
bfuCC4hV5^,2]A'J4<,2C!IAAN!)j$ko7K#2).Os.]A#f\EX133Y'&%TfG+Bg($?=FU,Y6m-0C
FF-KP2j<<mWOth_@0VX:VuN7.a=/^"3>+dgO+n&]An#a5BoP3jr,KeA"mP'+>I-$Q.'T_OVq/
JInE&P=<3`?R+0quDk60q*CB@::%IjpA>58f&E=h6Jck@;MG[OC`V3[\U*/iq7$R_pidN\9;
C6m)"4+Hd,,*2L\DEdd.Ob/TW)MZjA@O,Lg(s/t")0GN;rgTr@k34;%$'MD.SP]Aa<,8ge!-+
S^Ornfn2Io0DrQ1?/nVD61MM%K!20<@<q/JPJ4ic\1'Ul%J:mlbm'-@S73U2^D#U<)<q_27d
qDOl-"uGd*+_blu%45\BlT0Ap>lh`?[E2F/?7Y$O8GnXIduDH"*a<&(\lIQf3]AW#Un2RJ7=Y
Co;S,Ye%K60&D3m!@pLUbm;;Y(2#EbJ"$LjfO/E["-&70p;U??[66dcX'p-VuEr<1m+63d[t
r&%<`*put+6!cNPh"n)\(A8kd6a0i`1F*$I@^he3LU?@?lp3+qb4SY4bWZ5>NFgl,VZm85*>
C'-tkP9!qnuZl&=sTE3Ol%<-f-s_"[54t`AO/5Uk]Akm7m$.EmaQh9I2_9a+\7:,NM*$K#(-f
jJ*ZS'iGV`V*j62+@U=aEZ@i0W[CX\4*+3[""C(7"H_IY0RTDP7<!6\91Z/E:`GTEa*PR#u3
1(ZQCUqeQXMPJU-SX5qG"_#AnFbUZkDLc4^Y7jp4buA?K,;h->@Z-H,jGfh^q0dL,ZbFX`%/
3+PY4[_b:G^4_GT5qT`?Ve)njk>fac8?bAYUbU*iW`3rJJa4L\&<+bdBSK6"S/7o@fP.*Tm&
*)A`8eeD!MCFd3PV3K2TDh+igII&?XK*^DbR/ML3'6prpI$\qO^k>4k80BXJm0LaakQ>;:WD
i2&g(oTXfqbl#DM'Gmm48%Hj`B2<$do_7Sn^4_GS5kU!X2q5E]AKZ)W-\1`iZRIKgY]AGs?5i0
2Gn`'$\a"+_"?c[!#]A/\iiEf"`3.*7bRE!/JVoA/*_);r>n7s[UJ+fc\iRoL4$gt-LRoumCc
bC<b9j![YQ9QA$ZSLR$EUS%+d6^olp&_.>;=^349+)%=)aH:?VIT?f*6VpiEK.5OQeptqC%&
F,Q$dj0_R6=n\?$<4`_kCpg(oGoll&oO$^W02SbH?F!FnsF`R$n]A\>[mSYI\#LIb]An-"9oju
O^+Mrul4kP"*\_MkrOZO?TkAfAIdf-$c5>m,qQ,fb"]A%H2OlQWp/OG]A/482=id9kV<=3_=EZ
s!nhL.GATh5#Z"I_66lh%8c9rs0>n=hKk\&_$2NR[8mR+VeXN&C!br2":'DB13(13M':2kmm
_!+cI2J7hM:ih-4hakYAAWjZ0"O#_\J(UP=rU]Ac8&mD)0M\9C__srSKK!ct\MdD/dBG=#UWs
&'qr]AgV%7OY#$ASRb)[;2VdBg*_J!^ZWgjTQ.9h7S.hY%+tJ&tgECO>*APStmV:VEkWL2FMB
/QJMR`CI?VU7!Xj*:<3bH2/B^nJh0j.Ke$03kH]ANHltD28>!=@0_@TF$4mfOp"hiZR+e:/W&
)L`+rq5cUna.8:5mO%u:7?<:.Q!@I6-SG^!SjQ(f!F#n&PN,`@>Dl#[cW,dP]A-7<'3-3X;n1
#K(i(bqPp2$Sa(1_Y(dAPH]A:]AB2KVN:g9gT(@kM!PlcF_I%[@KCN%3paQWo"&>A%bno1<M^H
@"\[Y6REEua"Or*?tc7"VLa7Q;Z57@K*6:+,;7ih>qe!_hof"@Uhn,i,LXUk*ne_gB*PtNdt
GkZ[g~
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
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1219200,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4838700,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
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
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="160" foreground="-4144960"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p>;cgEjlE**^[Ee0APQl`+nJgYdLfQfO+S!ht]ANe'E+,#J;BiNSlTFa@CKR>@HXVDI['O
%6.%Mn=@L]AIQE+edFYZk1*t"G.o,@)=G9PsC@ThilX]A.q,(I_-$PpjNj@:?@2_GqQ8XYH_")
R]AM<crZ-Ql40<]A#47D-4dbA0<&jYa5Yd/rC$Ab*Zb0<^3"?*A-mqq-VM5"ZHBP.J("_I#q@G
E!\)^<!k]Ahcdc@I`u!Lp/PcogI/O4X7(tMU^$YpnA<_]A[uLF/pK^E?WJ*?QZ&\$)Z!)USX(;
!JQ4A%t+0ocp((m9c-./9[Hf<<&-9i3SXN%t$@>=Wp8YO066_,&"\anOO^YF$(Mc0>a-RSeG
g`[=P^d&8\7sbY4kJ&EFD&]A#%X/fTkWeeq**1!Z.McV/M(6oH%N/*tr'?&\CE;P=hgihQ=im
9:3H,VX`S)<B4KQNIT?C%d*)X<MaYO!MDpM5"O[VBanZ\"+7j%s;REsrgfWH:!:f!*nN?_8U
ng26ks`5M)ThgF"d#?0LYVU]A!<N$g/NPu@:M$b=2\r?<T#X'`9O]A&t5AlEIo"g[>imrL[Pl1
gmuaIpmUi5e897$=P4e8ioOV]APgIXnF^L&3UFDsMZJikY`@l&G\"UB;;$c-93fp$-=,;dWOo
jbSot0\aSoGhl/M6Ec+=@98]A11blCF^oH8RE(*WrZ9Dtu]A'e&9Eh5p-W7+ChQC/C&-V-&tF'
l\#Zf-iMidUd=i?,;56;4E`r@\X'?7>f4d'f#SI#9<!6.I7T"7gf;H5L^:1L<p)+eQKi:KrC
Snq4-m>V0@pc5bWIMdGDKK7oAlUjFYs3[%p1L.9L]A-uhlF5]A8Ybo.molbTbV`7f=ZAjik,o&
OW64Q7l/@V3>o^@o1GW13+7a6]AJWAYC6Jk<P1u80Wcb9+uB=IYBQ*5.45\u<)*N#a)+=LN1b
`HEOG/9m(e+p_M:YH*Uj1+'RSL]Ai5mUM%EPOXPXm1eXMmp[u1R*5',3(5H"n*dNp`X`(k6LI
\C;/(&D/^"l'+1(0eY!]AbcS%P`2Z+*Q:TY`n"j'0.WStm79e^Igh%3%dYrHg5[ahIf#Pc_Uj
*gs&:/h<@&pU+Ps,,m&N'cZ/s5<_.e/$tW)^O%p[:>pV,(b1SrGD^#j4i\=L3\(_:RH)=0n7
AK6G<DtG[g:5*Y#tU3@omZrLpCq-1/?K=r_'8+BM:,fY"Ub&XMdAdN?`C.@`+R2D1XS<+_.c
<-1D]AlpVe9L/\>,6Z7V]A61aK$O),5b)H8l<+)IJtMY?m'>T]AOtI\7[3jgPZQ2rE8lC+,:eZH
/*V/8jb`=31gt8YeX!MKUjpRREcV1i>\1_L<#g1ZhSm6M#r&JMXtqoj]AYSVpBYTo,4"GHq\h
U59J=KB:MXQ*=O#T2?Hd@"=T<qG?.5HLiIP7B?>0=[^/cgV\[$i7cuei-+ELsDN;ptOgCPP8
4[GpDPL[ZASe-_KUoK98Il(8.SS*lE%qRZVD4]As*aY+gI)E/OprjrEkaeUCt9[4^!2+Na-eU
dLthW:>kf"q&br2r;@dNgXcBlqJq5U0;!.=>=c=FU9YRD:^#T57=bBZRJlgXraN^,/1s;7W_
RT4Pac\5HKuV4H%*qSaM&d1IR&"hNuN)@^7/EMBcl+I+Udl1&Mka/B&kU;Bp>g,7U#F0u;CA
$C[HT#%`Aoj9lN,U.I+HmqU\iBFm78;F<@k5tBQCT`3f2VGm)>?"dhT4;H0=aBA2!"]A+:Hag
M/mi`.8D=Y->+dBOKZ*ik;S-@p.gM4?[/9Gp)UWat@phIqL7br+MPUM>dFSEmemYna.:[unK
@KujRHHXRm>*Ea%Zh@h#A0[D\B]ATj(>.X%d9?`$72jLI_qX"1c4k&"CeGjCR71rq)\A-gk1F
;bg365*49I]An4Ieh8<&T`A<h^2.]A.\c&ON(O'nH79U&f`"a\)@Ms;1;p\el*!_[>Xc`*d&+S
uGp,$t6N^`AO+k"TcT'4SdM5pAIu*H":0GL(FFJL[QQc9cko/Kji?c#D5CRk9jO>WX+pj,&c
PNJT!K>rW6mFkGXfXMjgaPR"e'/!OouU-DM9h0!-3.HB-GV]Ar:$\Z>om3,pXX4\=$dJ;7f)L
[`s!7\QpsN+dW^d?]AO:2+582,9dRqX3XBN-?!Acf+BX]A(V'r1Y1_rUN2U@%YAs-(JD!'EA.X
RhF$9!W,93*k31<q_NKQFjQ:DdYOPF>"ZAmcH\2=5eBUflEs3+QfsiE4MpToo`"GIX>9XJ_P
X,C455^1%VP@-3ko9T\%T1pWp*&>e>IbL;inbdp9YAq&Hj6.G(Zs&7D+<[DgVkY8gkHQ<_WS
MZ:M4X2JB$sbIiA8=R,>TF4ED!9phLE!b8=`Or4\[_n5QKb;]AD/VKZ$<6R<;N$JT/]AGMWuG.
bNnK.&grke0^E&<,&?.GErbm9m[FmrplR1/IGLt`L:bmb6ai>":Kt*5Ze.S:K;+='NqP%:sY
Y5rZZq_FcHhb[AZ@UcIQ[d6R$^8<'KYCj-MPhb5H3hK&:BI(SLiu/g=ub<ramagci8cMh6<s
Y_n"iNRJW+1QI*<*6rt^6gh$lI[$[VXGcLrprG7jMS'e!#HH4W;P3gM8!o\p+I>b&Q5@B*qi
MopHbbn[,^3B<@a)o3]A7`'(2sOhU>+(5q5aclfVm7\!a%R7%K?b9k=]Akr6iBeXOUC;D]A6N*A
R7OXZABXDJSk)K-VOrkUi]AX!3j[r/%hYJoA@m$Ta@S_WoGBV`,5.^?%j%$$+dF7r=Xs'%)UB
5!/8^,S3TKS""Q>N@C;L?ljf5([bP85gWB.fIs=C0ofaBP3"[&7uB<LB3S@KKAj:%mnH+Qf*
I.,,*."DaXRIW@fJDl:6ZP9OQk4*\+2uZb-N$J=YOKSPea'>#D-+<NJBqY!ERI#f)_!V"=KQ
\$Xgs?rkbf`@*FebPQc^Xhg:t%#F)i$4VjY3KAO3I)?61)I2GraZKd;=>3FOa6\H3Q)*L90l
VdRKRR.QkQZ&O_*"ZW"Db0<<Q17;'I?-/dQM>d#_WAa9_fHX_06ulGR9>:GruXILD6CPd^>L
3@MC\R<to$P_dG=>UJ#WgT^_K=4I0GD.;-T[fJC9]AAG,'iCQ_c`p<Q=b`u\q`Uhqb<27$B+B
;57hd5*Pu0`u/G8MOe/")+A]A6s,n="/(U7du<Q3Q?`KRK!-%AgSW!"+W2[K0]AfCFNm&-pU87
9`Wa>/XVp"DCg)a\Vo(@Y(J_8KlqT2!OrY:%']A3)"Qr*a<+bqC%gT'g.t8IK%I!]A2ht+!Lk@
Bj<-[.@(9a!fOX#RtW?b\I")=X/2g&5.H-E/H`';'#=XmTLmO-\T:LH:Y&-PVZ)]A[Peuf.@>
<1!Mf7.?!'>.+P_aA%I`#M!emAhY+@JcE,6?BW&j`4QBRg7WPYgdk=bX#k*6^<:2%SGtpW(K
'@i76[QE`lSKg#!1R4l]Ac96=ZTc+on8j5BE/8Tj3s;P8*BZVk0T_t(N."leB8M5mG1F(@?2R
r`[6M2p&$DllaBi?1?A.91nlZ:"OT[!FeFMH%KGi,SmD`4-5)&.\(=D+:sh1.,rAi[(\gq7,
O-[JIp*l]A+rPZ-R]Au\Q<f:%pYOfGGH^.c4P\n%^#n=b2/2"d"2NM'M.]AF7Xg&['$Yat%(pC$
YGqmb#O$(,BQ1JU+D]A5e^K`/iWFo\7^)[AY<?tc_@]ATa)'/1sb[oK(,p7F0]A2gQ,0e>BTW'I
AO9QWBm3*bZ&qn[,^*2AelOTH)_VWluf>3a[+A>S&W+VXkmW!kK/[mM.]A\lGEW_WUk^tP^F2
Ci8:O1is'M/IZBRSr,ZA4L2LO"^q2ddTW$cSH,T'VDh9`A._8TmRSsLC^qJbfG:jg;Npdt]A/
)Yg>?.OdDK?t;QQ^C=mgT]Am5RTp9Il"&+TKY&,$TRhA-9[bp4R\!lhQAj-X082/c<Q-sM-b&
II_^e7=dW4N`!(\[Z5WS.qa)W'+%TGSUEYXjUk4&=Nn5XU'<HmP0Pj7iOh-I9RWCW:o7!1LA
JPb"rT^6JdDG(>(N=h*eiu)5C7j:]A^:2N?Yep(qf(dBlKKQGY&i%n9]AO&Wgc\U#)t91#]A>'+
c=0:J3;Ef<U231DKUBg;HVP3k,C%Smp.<Bau+BhaSU\pbT3@W+E+sjbYRjc>Z<TK9c!8=@c$
K<cW<KW]ADgiU7D3,&U5MY%,,Cu6/a3*DB;3pkO(To")$9"Fr\Ft)p%t.^p9\:*f\T#oouei+
.<IXZh20cZUr;ZRe"937q876\0s1BKK-;!n.7WZ1Bc;\lQSS7W"d9.41/fT(eg?%X6[TMLMi
n8mP6UKYXt!BL!ERd0ZmOu9P?=G+4TQKp`4bCcNj<,m0ELT?8r,@5l>EYD@"?).['#LHNfj4
nGe\1;8u:C2<Ss7(QVUUE]AZ-)rZ.5P-ea7#gi>Em#S%M=!`aN<3IsfXj\/`1Wq<UrhSB]ALc=
P&&T,h]Acq"J"u@[gfu[ol$$F6W45Q6'*:T_1;fl*8)!i.UpLR5?G*'m6tB5,G;MfG&kQeC&6
F]A;HWn\ac.n(j(EKam^>N5G;U_FkN483MrCje;:Y]A$c2dQPUq8*^26so#o6>okILpJY`Tle<
Y`QbmGR9lN"5D1o)aGH2A4k#f;e[?#'78Rf"mLlF9RZ6'pcAkLB<]AH8:/4AoS.CN:U[@mO*$
EKJ&77g<0<>^DMbX7B^LquIIZhJ/9'4^;\![2Ql/%IJsTIZ8#mMIM+]Ap`9n*@GXn;c/G/5e=
B?#3/M&6Ks!m]AVkdBQ'B"q15n,]A+0fS9R"T*(3NG5Vl(72GML(WIIK1eQ?_a@t"*(;cO0g+8
uqt[kZA26jL\,Z9DLoln/7c"tX"AXku_,q>!+oj"?S/n2*WlZ=?kYnV*P>-(J(]AO*D62^en?
!3[/-jHjV?5$M]AXc2@T)GKc!?`L%6*K1IB4&[5*uMB6sspnFq!pEkf-HX;0b?:AHN6\#.5TI
,eGYRUsg;d[Es=Cq,I3\6,L"rr@@,c/npMLXljf-WJBd!R(65!_g-J<B1lKXHrPooAGCO&Be
gm3GJDU!WZh"<.2C.?VWSE%MF`Gefn0q'<%9qaq;$33I\HG!9U]A?p!F,G&$TKc3UAlR)upm+
_qmN6"*Bo*TYl-]AG4LVXX@`Ipg!]A]AU#)QH7j#p.tkXc4`9f.CZAgAk`IF8.YblK;Z?)>tqCQ
(5g?dHU3AUpVk8#<ISES)jq)AN*T:@A5FS"X=`Igu_#G`eXQ@uo>"X"E1X!;/kE+&ui'c!LK
rF!8^InU^Ho^@rWhaosU,e.7es7KOT4YUu1#mf*;Ll7?B$a;,L?CLOm$oXJSfRWha$%hK?d%
Q1G6g&ob[]A(s?GnG8u\cOY=F^Kn%VrbBFGpHJF<~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="8" y="8" width="183" height="46"/>
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
<Background name="ColorBackground" color="-16170125"/>
<Alpha alpha="0.35"/>
</Border>
<Background name="ColorBackground" color="-16170125"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1714500,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[24917400,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[智能遇见成长实时信息管理平台]]></O>
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
<FRFont name="微软雅黑" style="1" size="200" foreground="-11739906"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<a+\PLm6,Kq]A>&>6GOl>-Ct;3e@b@!UG96!Y1k9#`pHgW"C4A`^""5?nDGf$HSSa"]AV?A6C
8ET6l?UB87Y=,#QZ,;#U+jppBt/]AU%e3QGM<&AoA'?*o451_[F`$FGHLghn\Dk%9/K[bW2QX
Gq>-_qgf.6:-E*lA5$>5m(ceXGYs_#lYN=BJ^,csjq7>08;=?oqL'(qpLej!_L/)WAgf-LUT
RL.j13r]A\J$.sQhu44*a3O)<lE9p=R(+!oLt<@Wf5...V#S?DWh*YqO<Z\;D1$C%3BM^aQm<
=ir@Vr4\B=7QkWrhUWgUo#SJ9nAZ#JG*@]A55=lZ0l<^\dUYn1KWMb]AW9k+FuUJp['.@h>$#M
RBsdJ%HDXO)>^IKqZ.ubmb.W`l7N"T2fqD8nmRY(iS@VmV;5A;VZF\ql7$Yl*aUGs0&Y,]A+f
mh8P&?GYRs.Fr<,DY2$e^mH]AYJLf0J1CaO68-qj"eoK[Y#_5Fs44UO+iX:nS9`0lhua_jptO
e.M'[U*6ub_^&\`ZDL;(G<7oaF&l^X/JBGTj1\ggq*PaQL10S4Maf_Fo<IV'!M7^7jDZUfDc
k4'*XZTG[O/#Lq_\N6(?/+%u>Nn&X?[@aSNBK.WL\cOh>Im<_JaeBso>I&aSar6[*lsJ9rS2
i4A66KEY[Yi]AFq-$;'UTU!!8lA1$T;;"p#.CW&b\oTPnHXkE8,5g28Lg.Us)!"Y@]A,XE6<5+
U'HC?_08\CA\R!Ih:Y?%RVRbQOY<Hsk"]AdqeQbKiZ;D,CU5m:u!k!@76WqELh607Qh*!bs30
46r97_kWZ%7>/GaIlPI<,9&eUj7)LcY-S;95)28Nq=B>l3bp&6qCMajr#=#R+=5pcH^]As+F7
)]A88:?4,CRD<CQ#V<nOfeAnuqu.umoInbk2r.Os26??lD)9%6j?%_Oc9rnb:O;Gm6GQ\UX.R
T_nd`ab8QOO'Wrd81FEr.+Wb?.uZ)$7:`H4,E]A<bJ%7XB_^`'%f-58l6ZDH=IDu2*g`*1FPI
0uD%[&1bU:CiNj@N%kXrFTBKV,'OAX6caQ?I))8;^`kr@NES=hO/?6h[UV"h6b":fhL%(dT+
UN2]A@lQp#D>Bm`S9[r$k@Q46tPU/;%h^"]A,0X+6MrGW.o&LI+=OAq0geFPf%^e_foKgZ:7%h
uKAY8nA/gBu*.7'?#FSItI`_l!*u0Elk]A"pSb]A'#cGc6a+A&]A>MqL_U9fc^5g04ch\ohpS1B
'-/K>Kh?Au49&-[[n&:G7T>*9HSuu3UZ_dqE=>:ql%oWpkX))&rNh,5(7lTU<Q`Z#PJp=bYV
VCY_aJuZS1V5fRm>E.T:HdkN5MPKgkQ!E_B&<"87>c^e33P-PLd.T3rgn%?;FT52W@Q+kL8K
0Q]A6]AL&+0Fi#K)&4MY<fRm>D1<"*g+e2O2n]Ai`.n`sY@Z*u:iP!^6o,ObTI<"F*QO4)nm_"C
'H[GLi8Tt8oDYW&`h@53L+tWSCM)he%J\537Lr]AtK^"a,W1YkRQpZ'7O8:Yc617(0(iu\"NH
B[R^>cA2Bec!`D$EhT=K'\:+16&2B`1rY*<hC_&T]Ao+7po$,7-TaBeO-Ln6(^/]A>i>0nmiBH
&@LTIndDbMSf[B-N>V@(H8u&VlWoSctL:0.f69O&*-^3X!O"rV2?mh(]AP'p>XdA-'a6@Eu[M
Y!Mp/b-(dSi7Fo*FD,1Tj^JS'3/-1TLrD4jRFR_WRH)T3Fk)+dL1pZ.dB$?n7MUM2ot5-or/
gM!gHYU't`!9J^nTe*pM"(4ODZ>j%s$$Hn3IY>aIDjJt4gPRG)V\jPC)?,8WX[k=sf4VtFjk
)T5K36"[DS0M\N.[RBU;Bn8-a:SkH)\fi>^+^rT`W""c*Rco3ogCr']A'7?Qg7:?6PbN]AWi+`
jbSPf6=]A.cTk\Y+Z5oFk]Apn)pS68@k##jR.Gt%*T64io1Q/plS4c*?M.e5?u8*)2POl)7<ZB
pK$gGj]A?`MS%VjrU`QKB9KF.s-lC`(`AQ93Ejbt^^7%Kj2*N0GKL&$&$\4oW"aB]AY^W!]A^H5
8o'O.^JciP55FX/LYYXil6o`!DEOOXMb09%ui^-@SR7\H]A\]A<E,;Xldk`1a9]AApt]APgndJ\u
8pML4V`fC,m`-"?6j!XR3T]A+`6RgGja4HaU.W)C.gA(Tpo.gp36KcP+8)H7ek.'=><H;km/;
M3!*jF/u)U=4YK4NA8%'D2YT#8>B&0`T3G9;$ZCYk&X''ZKr.H[B=<3M/eK,*L"b34'l+&]A'
:noUgkorg;k*7<HNcC+VJEO;KKul`SEnJ=sZCBGhkZn-Ua#co=1BE(SJ4(0ptjAI/kSp*L+!
Ys#>;aoCRhWLh7g!nT1Kd7h8`O4)$EH)6O-TZd'\F/SDfD5ajXNZ9.G2$5;k2SG3AN52:%1_
U<9V1^ZlHWFl@uO/\8nam&"DR;G[6#^YPq]As#Jj59'L(27b1ZAMjP8Xs*VlgH9pQXa/^J$^:
cp>htR,qP9**NLBDHN'*I?.k$sq&,pj"I/W]AXa:q(\XRX]AJUdJ=ucfC?g$[N?/17Zo6R3slM
$(Tt;X8]A>Z2!.(UQP`DV^pBKDM3'nl;*T)rd]A58#`q$pP-.K6#)uc&8@H4?n<l[O='Wr\5O@
rO6]AUf0$6<@@t*:TCU8B&h_*]Al2JqhW1[:"dgZ0":J'q4KUqBt<VWOWm%BiJp$KHb7DPj;%i
lG@KB=)3&b[]A1c)\Vl$7'+YQcf/doqT?)u`Q1>9&NnBs:<KK&%lO3qi7N(bEG_GCe^S4ZB$2
S'IRQfJ<cX1KX+f&R=Ao<+\UhAjABA!u[B@XS/qfbZ]AnW?jT5L]A`dR<FB=7d;l:(LBk-2\"6
1!2_-%;IHnR(o5TXB%.[\f<)E/@*icb`'-V;<Ugg?A+@VRE\7N'2.e;+LNN7fj]A4D)(RA$S*
N8D/5_QF!FctsJEI-Z;CktT?GbroO\#2+=k*L5sk\jiAENN#eMoNTnp`X]AJr.j^%Fdj]ACt37
?1/TSf6l?G3qYoZT*^rB_F+?Sq2<1d6K@GM-VBNN"pklpCo89>#[PVkl1n82sIi<j*ID(ST)
Z>P2TH6[\0"0&TT+X#8nDWecPH"Lt>.ABhr#p/Z>hF/7+(DD/.;7+bNtSMnaYr&!?V_`--tf
u'IH.8@.1V.YF^.HM#Kp?2X$]A\IHIdF-!Q<[SANGOpQ%K$CI8+]AR3p(TN9Gj/3^^":1<X08,
B1UoladjQi'nmA.gC@#q=bLpS@>8\>)`\hV-#'I7c>.=sWZ0phMF/Xu[qa!7BA0%;dP$bRgI
lm9rZP]Akr0j?mpDEZG+$.b/$Sk037tMBBE]A*=6RL,D)1K<6%$\?s^N75`QbD(crbXbUf[3"B
H6o+ZR-\I:2ao4Lnkq;NgFghKm!fFddus!XRt[G_$%J,6J4MrPXORbcjGaeUT$U5`]A;NR;=Z
mn&4V^+c4cEF"n;18[_@dcnNa'0h*<gZlYMk.\8faR`39sU^&;4FG<1A27EB(KRA9khYP"ZU
KeGk;cOFh(fu+dO+-O@ROGnf3kP`6MFfs3rqmYgTc+..]AndRj/BFl7_Nd&lZhZaYG`hXC7a4
8/aCjJsZn^C]A06JVIAd!XV-X1+\e;&u-W$_!Y4KHd%lj'p`45=:7PLXP!+\L#.UHLWJWl%Ca
ZGAq=/%$/h5[E/[lmLT5KT/Zas!JXrF]As,Ve?32dQhCU")4/#/YUrfu&]AM1(N!+?O_6O[jIR
=NdRa>J)K(ZEO("'e4?)'(h9/o>l$B9p:]A9UkRE:i)8UuKE1Qg9Q#D*ug]A>WgQ<4ogDUc;[O
D2\LDF17HPVkXkc(J<^Hrma,pm[A]AaNSZ'X,ED9b#jU_O>KnQ9#\FNL/TgXRb#3Yb?SA$q'7
'f_ATU75%g2t@lIOn\<dI-sTioggdL_0Y$UQ7t%1:i=89oAg'gRYD.4'Fu@ROZM:F`VPh406
hOGdV)9Z.RPZ_sd]A)4#<i>;6OuHI*r'P!#MNjrGn?a=UqFif>]AA%4YBaqH5kNQ=t)a%c!U_d
B4E3)U-NGXUra?]A!bEj.`HYbZQm#mWXCKFfAg4hLM3-To!Xs,ZW!EF/$:l(*,GWl.j%b.n5L
k-_gVg8.M=H-PZ(.5;c)d;:q?]AV'F&+]A\DAh.NNGTk?OhGM2nK."B;B@1pIqs4<Agp2cY?R'
kWg-c1m?*ma6;K]AVOO7,mW+2OTZrZJj`kLrto4'\%Qa\D)BpeD87'iX+Ajob]A8n*]A]A4a$LW-
1L#uocmdnf3h,T]AIY-#1*:S<pE4:/DXBlTM:G5&So8S1XV]A8\0V2neM?3kTWKWZ9%4aGTRG9
7gmgM:2US%ulGhHD?W"nRX?puGh!=aY;k9)Me++B(=Dq[9:Yt1GJ-Cj%n00c1.R(Hs&I&X\)
J"ffH[%/f4ae`1=Di<KhaZo:k+*4:9Mc8Mllc4*B5:qd"j(p&MCs@^E==L5V9X\b2&?FD.o?
es[CP7adW=-Ta2IL^IS/qP,;AF?POZKTpf?W&1Kc=m^KEdACHGpoM5IHp;jK$J]A,TtG&&qFr
sAUiLeg0d4X4Z(q&:ie7]AX:'3,K/RcK,560O?_Ur]An#_]A$<jJL+r'ADEK."nq^1If,ocEbFO
Gcj#IM1D&rL%8X!N$(OVCi7<S*WqZ,_X_3FIJ<*6NY?,UmL7bgN:61eF6Oq@tHP,&kb+^HX4
/kcI<hL.'0?_cfl5Y9aFeICP.4a7`<Q)3&m8]Ab7`Ou^KB0NH8(VRL98/@&o^,\hKDTN]AtX;*
k@*LU&>h(NS,UP(`7HYW\$p(&b@Eml8+TY#2laWL::b`^)FQOGfpp!Go.cj,G]A&X)/^D3Fdr
KOAKU&^>aGq^aqRD%ok9"a-N<sO?M=qpG/R`XIeS1SXa/bZfmPrB$Vjj]AV4SD$p$)-#D$@X<
@'uS7$L:g0>ZA#.h=ioFO%Q#?A^TNt\fQ%=%ol4aHL]A!OQ]A:Y9%FJB`q[#)Sk9$ConM<_GXU
H8e%_a"8tE#@VCdD.L)g9"LE@T:.,*`3.&+*;pSX6s,7/k<d>nSW6HYO:lLN'g_F31BcT,id
I^UOt[^#pCFrTrVS3BsK7=[">!+i$49=-D.B[5%ci.nj?\Zj^b8)m#PigN*3aL*gt($;ba^>
RP6+-VZ:7oY/fT]A(\m0%XMPs6&J.BHX6_CYqYC\a>WppS&Do",&j.Ple_qHPq5[0G&Wa^?Zr
al+'jOHh,,AaR\)fds'\j47ao,@=SN0sad+82j,[j;7jo,rVnkI2;FW1"/3pi&AB'[t`BOC9
p>IJElaPrKC1EVp>)MM:MC/*@4*F387BHV/e8%k>&"<jC"@X-t;2Bc%9F(CdAS1Z!6ne-@C$
bW+0bKO*(7:IpaPfATNEbi#)\fApo72U3WLjHaBOsgpbcm#i?-(*m\iFKC"ed>7FWlUJ"%r)
f9*)%%#'FAi8C]A2dT5ut!g>AJRi)q,8:a8An^]A<h0`)<%ATo:a'09#%`Or06qFXqrY9pmdO;
_GaK3GjsNEEdF3oWPC3fXmn6'Q^^?;[r3*pdiSV/Cgn,EY.:(:=8L,FX$I]A@2/&Wu3*luo@2
u+GE!M_0!>'r>GM/Kh<d5KMHM4G@^Wlpo)/5C@@V05?L/'?dY6758U(5%FHct=uhbBnF<EG6
r)=0`d2tgjAd$f[aI#HXVR;O&7IZGht8Fd@c/n+X63W3`B-:^qM\2*%-A?)S!L>fZ!0dn<nR
"t%CSX$e!5%E<5s%ab'_&Md(1b"/H\%McCXC9fq_6@7niC,dg)&H4Q1FF@[[D:QOL5Sah;O+
(R)fU54:g:k]AR5.e80^qH*PSs8P[IJho[\-`3Tko,+TWEfY3?4rS-lo>#rkJsiiJcnl0dti9
[=DAbQpFKon88t7X,bSRC?#_Gj/L9>ZmebRCKFnobIaTlZS.X#ARK;^`5a4PKu_[MbjG]Ahbe
W;Q=oP+W^)Fm8gSf*OP=n7+Y>#<hO+KllSe7fIUq:9FQi]Aa[beq8Zk@6$U@c00NZ4KMGBg+2
S:hLV1(WI7X1^`i<Sa2dS)HNlemT_FjN&8Xgd-D[*Feim&:AbaL<+:gS[!jUjq:#<XP<^mW[
)FSs%So1HMVGg@0o#mc<N:'\-C*D8Z9Kk,pSp:V=%XaA"t`A_iF6O+#/!0D2?"t#gdeA(*<V
>Cn.G;h)/?HU3:%_736a!iM^3Pp22GqWTS`c)("kh4\bK:_*GB.[AJfnAk#XN44!FKs8,HPo
"4VQH1out>P6)NO]A,Sh_q;K`tPisEOZ2<!=X/l,=^F;PCdt_)CE+-?@AeEX"UX3!KN-5&kU#
:MQO%t&D=1pCm!LSo=f3sCm9j?=YZbfU3U>RB]AmFZ8&.:832NXLdPdS*U<NU[O[4>;(>@gMB
^qN:]Aq@TAG4Z]A@"s9oU#ZKWmPSkX(i(!jj\EECpWV;g)(sr$1Ff"b<nYW285a3_#lup;)qCp
O-3=bau&d;4sL*GjZ&73!akWmfWT*1XQT6CO4L13[rC1:g!]Ab)T71g[JB`5'ui<Xd+ud"B.^
Af5OL<t!S;OglATo89@OCU`oU\4;4_'eERZsJ:<:H#'R"PF;_;A6#I7G^h/":;LApO\!89?U
l"48k9la34g;0/[ih>.tm&r\V93\mJ2*[49m[Q;cG@,<s;\7gHZ*#uf21ElV&):Q,ph'ZK<+
t!BdJd&kQh)&]AgHU5%lHM4DR&JKj*$ZLF#FQUel2RPHe5J.p:!TS*(9d?Z@-n%*6M=`?-=PS
ALJS2F$#\VVK$KOJ[$H8/#H#2m</,WlR$PdXVNZATpoDqZ]Ai=BtSNrp0)0""TS>i//M&K6T*
8*4s]A0=N8KhhihHqZchR?hT=qmD[Kjolk597&*/lS<m+"%d]A@86C3JhQ/r]AV_Tq]A[&SLDJ'p
IjQY=k-"jZ"H-;G'BI(]A4<:bo(#W%hUqD/Z%r$*0i2_RCR$al(1g1SUn@[*sA9jsTAaSo!SB
$Rth^/YQ"u@oct5^g]ADb3EKu3Z<LM6(c`s50]A?S7Tg.4@Li(Vgfs5BOEG3KpDkhj!H8_Vnj9
Qu)m,\>:/>'[o2i_6^Ap'Xajo'`^SdN'mC"mZ[GKTOBD'1(4hq2rLk*ipt^>>85eP_2Xp"R$
4D4?b*Hg2'hJVi;9eU2B%@+h%'1eB",$2#e&c5E3%hD1dU=HDVkHMEBed7CUM<c%XTg#q&R;
UGmP"*C\:=Ci<=8OkiK)T@2becCuS9Sfn3L/L;n"j7Q82h7Wm#(DT?p_]AJ8C_QL:NE9F]APM\
B%FW/0DAd1-g&:[J`/"1Fn26JV2:uaDL79$?2>5N"MleH+Wn6oXck1K"fBP:p5ni4YML)oAs
Ya7AuA9@mugW#pQZ^n`TdQusOcR+=!;-flBfZ2o$j4pOap0Y75C2rW.K(`?_2%t<V"",<15f
=+UgqTcD3ih\6.Uq>V_X_2LMT(\@D9aHJCdN"_L(5j\H94Mqbj]A*NTBM[-E7<2`c5O_!(DWt
RCV,cibM#r^d?`>E]A%,0jOi.%8CYXS9Z&J?%Vo+ulmG\iRIF6c[/Wq_m,U_Q==d`Zo2Vat)\
auFp0We0LYjBXW7j!3F5Obb_83fb-_F%"Df2Ou#;Q/db:MbSCXgPN6]A"/fo_HsIcP\G*QN#N
cJ'TD9S#L]ADZ+h>d1?A&GRff,c))'2/unb;kR%m-*0oe&a5>tcjo+p4ku4RpP%G1Z)Y[5_<-
X3bQIhXt2TCXDdrF*34Y_'eZMDi.5kTprfHMY?OjfGbt<,8bRaoZ3KlWo;TqN#alAa"R*LC(
M(Rmt4>R,5Y5)$/4<:p>D&2TGG%a'4D1?@:"ir1+c\SQ2*1e>$!Ck0N^.$,0tfpL0i4W8tDp
/gPbIm37K_b1.bX;dDXTh0t"/E]A0pFSK-nR>*eGqbGY.YbInlP[@!G\imY5!HL_5%\nV,TN`
9^pKl\@_s'&>dTkIWh&Wl[LKAD@om+)(d5Z"l"q4MbO`cf[KFBbFfk4`A]AfYM5Mb@SO4kCq\
gRD[1_bpD:^1rAQ\RRFL(l;,lS@+1&aGrkk/@k`&-6k^#OAgp\p_E?P?]A9H!'0ff#IECS6DS
L?=NC@i?Ko<?;HmPmchF,G(Kf@tQ-Lp=E-BC.cTR[o"6hgjD&6%\[0]Al1H%QTLli:/*;'I,$
qrUn%]AV>*\Y6.IsLBPIgo1Tf*&GQ)qrY6P\9K7U-?%W->fH^7na>I22eLSGt,Ho9qQE.Xr,9
J,sN`\O-_T=+<"NVj5qK[<>EeH.'\'9CM@5pl.N"l!H09-=ldg?",u+ZonpSVI?C:T%!F'^W
D$0,eZ?*p/ltn1S"gfQ<E3!V.8gFZB+7:&"m4]ADa@r$P.R3n#QT(klL<n(G6>XA7;'B;<]A9(
mkS7:X_P78u((9+76\bJDG"NdttGjbXpCbq^<1o'AK&Z[-NBdq0S3geUie]A0-hM%AakTOK`/
nC?]APO:$df/'6>p#FBn5U2p+Fk&`Y<pTf1B[/BZ)pYXJ$IM1MR0pEm[I.\IsA`9&,l&unIPq
(sUXX)_5bq9`rQiEQhG"MKqH>Z6qV_.L>m(@!XYQm1$Y.c.;,):QO:.=9hd+ir/g:pZXeO@(
\Ts)+2h'6^D#BY'2:Vba>IDbl+k4C%%jE]A4'4PlAE]Afb8e2dP^UU\q[BTX:4L#iO,g[9jf;^
lpbm<jI,ALi<7rW)b-\hM01Q]AU<_#GM^b-bsIFC9?CbD"j9n1:f1?`K3hhpe:iegMj3?#F1%
aT4FCaeR-2c?>]AsTeW@d<\h7rU0=Dp!(?iKZlGtJ5!:05VW6l7-2e,/`ADq%gijfKUQoW[TA
34UeN*rM[<X6`.rbN*`Sj>f;bVWt6#:m11KI5V&Nn>ABoS?o,./*DJnm[IP>JVL#mih,<u#d
sO&4TF16/j\/?B"\oA73AM!-)o.KeX3L$,ZdW:oDadJnDQgLngGAq7Nj@GC0uG(E.s1,c)6&
V'.GT\EkonTgHk1Ia62)C.jK;T?i&jf&=/mu:Ng(P=Uli\U&#mP()tH+H?3!c'2q6:<=cSpZ
H?eC)V@fS(hrq-F+1UTXb=GIkV7kio;VVn:_)C-`OL]A,f>@RS:L02o$e[FR+"9HRff"$Y[YL
G&c[DGpe@^0YIeecMIuBg\_aGoR;L.!/D&+VC"jnhbpD^4):H$aO#*(F*O\FV5>NbJ^i7b..
V-NTKB&4q@3T#4>1:e?.>c<aP1G;*aXk]AW:Kqm2]A:X??W+:Ru!rtP`04T-5]AnpeKPaoRk"Xa
_mtR[-*9f$Q30bJS3d&qT.,7&XY*fu2>8LFM7T_=E#r8)1?ZNQP-eWJ.MT3uLVM!6OHCN9d-
9S??+N2JS:t]A=G(,N1-q5]AYtt._`LM77/.a9P6eR\+n<`?fLu3ZCYA_)>_ZB!RBkH,o+Hn:e
H,CR*F2bq8sqsNh_BpW+3(GaY3ESQB1d,X0!L]AZe<#(gc-!3;-6R[..ou^<LDM+/*1!-63NQ
'Z;Mr]AS@C@1LItP**adL@*7Q`-4Kq^,G3Yqt'eb(Tp90KpY)k?r@`>U#foVX-N0?C,bo(Lom
QdlTrCR2+2L.E3)+H3;.CcDrAZ$riJAcKbeLfNMtcb]A_<0@(A6H;Q3]Ahg5"!(SU?C:*$M6np
^eoOR]AU(kO1*/$=`2,"4"r\%Qd:.,bWL6a1criOk6RplZ2M;ISasfdm+R.H^rb$q>P/KW^[H
lnHoM9'J=hnj/kS9#j0-9"bEk@RgF!I0/U-=`'do@(R&Gig@Opk6uC18e5qqWDC1Mt3<V70e
W7Yf3kiQuoZebtdfSiM9AcuW<R(_p[cDitG<Dj!rJ1S:488N*F/-*SgCjg"ro!%MG3BkqVih
Ah5QJ1u2_^iJnjuTdjJ/oWD_3X^(Wr.p\4dD!+/k%*)pEa*55TQENj=i"k&nFD#Q,VWA0b__
ROK_5o)[3[l+1fi9j!JIh9M>[aH;dtG1!&h45L-I$ZhP'e"3:J.%.d2W;9`(#Dgf-I-j^f]A9
@Z=')9Fkq/GW;m@V%M$B+)$]A?g_gH,Xu(rV=nKb$+iSkA;/*EO@_V:kD^X`6'Zb-$:-0T#D9
'Kotf&+2>5\^Xtq:M$p_6,lnF</M3Pq8\`p7Q@+KaV<p/>'oogC`48$L7ErG&mro*fns=gHk
.0U)clG(,K'NAoEWaCACt&&iAoW:rrtVO5h.oWW`F%?O,^R;4p6dW?`:2-<q+21eUp52'#(G
2]A7/RCG;*MHYZgdPSDh?%Nl]A>cI/?"ouCqN_oAAR.3k5UR[n8uLQfhI$AT]AFt+kU#2tnju-Q
1?D&,C<e>bT'IH`1,g$BbdJ:2btXtth:)Q*m94S6Q0LAjYa=@t2rC?$7J#fW2E4D+4b]AB'MA
V`<19p7I6]A?#EjqS=DKdKp)dWsV6k=,#g/K6_Ir"S[BXfZX.@SPVIJ$RAKT&gms-Y`5Ib.)O
G3-kD(f*i2^>M\3&BNW<9HfYJ]A^+C/"e%C_'.O?TC7n.'H,]A37O9_"s0MmF,%23aI7`0a@0+
5tLdI(eg',*TcKa5m'tl@G,*R@4l5&-Y=:Ek]A4='mSWASC`$9<^ht/f^l_Cf4WckfLOnN_!?
X1^J0Ij]AVLAR+GS$$4R]A\5M_!t3c/pkk[)$hlIe^Q&6:-u:W7p,<YWp82!PW9Z%KKZs6,D.g
N24ZF,g6kP*hmn.VlN*Kp(GV7$]ApG0q("4"AF99kFT>Mbs7USCrNESQ:T7J<p+ha9f?>`H1^
hK`2=kM)O?V<F6h9SjVdaG;*9UmTC\H5BD$kV(=9h3'(=%Y.S:VR/0;S:!NFpY=@dYODBAXn
!ju:[GNr@MS"of0AjtsgN18g!4SYqVX>d\9pSeNr\n(%H4ZYrVVCjt,B3N`o4XU[>.O1hAGF
!%)!q&@B0Z!m@Oo3T;%C'\_O4CW)"8>4fJ=eHIQQh3J'B`D?Oa>6Hfo3o4gUm',H%9DV2h$u
V0m\qu!?g3cuHO0)ORJSK[&JFlf+QYo#Ll5)1p(:hBrQN^CDO?m0OjV>ZWuO97>@3969K:47
P*'.<!6tI$4&V`>@HARI2eAdGo[eXF("H$f-/9jU8fGH*#M6JGpE/'s`8:LD(]AJ'oLNZ$[%.
u`>`2`oOOC8sd5H:mX*#?\^IW+Ili!4!E4(bI>bB%Ijj?MWPU4!5j-o1sZ;1-<_[:RE4Zg.3
JQ=T75>+]A6-cN7STj2KEr^lP.^>3\TXDD4Dp<oD$e5&cM:-5P7EaF>b_'(jl[PsdgMMVl$Pr
uDC9EBRND9dHlfgV,QsM,0s<]AL2+X!*oB;69(`$IG6h:HRG\`h/]A;acU013rf5Y&INrq;Y'O
hmMPE:L16UN&U+cI?PXC^oF#NsK0>c?(A(<Oq[TmFM?0O"$AG3%N=PfdW(!GIQpNOJI`it3H
W_.md3JNILT8rd><;@VgSWFSXWe<f#Ds#:-EIKDrr:/(Ag%(ImZ*l?5>MlMQ,tMr/a\hu`!X
g;kg=L5/YTcC=f=d1#^VFs-Yts'nIc\FNp\I=mD!@*L-:X;s1d%X20P+%9i6#)"A^kUj2"jG
>8')>/@\PS38;_AW69aIi7&^&u#:`k_\DV2%D&-_;NES+:/scKGhJJ_KjZ+?(<2@qaPbb;)a
/HE(jc'qAc3N2@p2`8YEjf!=jkTY'qQV<$=W&%u+p>a:aT^eOR7-g*E%5V0B$WSh:9'Pt.Y7
E-megu@Isr$<K\H]AO<,Ar:Y!r>]A"1aG#Z#I&1oe7X6[*%"4p['He!W2T*!<~
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
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[1714500,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[24917400,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[智能遇见成长实时信息管理平台]]></O>
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
<FRFont name="微软雅黑" style="1" size="200" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[h-#)$;camPQAU_U[]AJ4\!J-B8bRRhh#ZqEHW>)RMBltpK$;?'pL4I>NKKs5$.G+\6)0PI8g-
pnM5Ra*369qF0@>b<j'-KE=+@./:*[W3Wae"t,glolSWV]A2oq'.]AihK.$=cL65>n,%Oj3Ut5
3!>`k\Q6QZEYq==@-\@]At&`g@.V<6^]A$JriQ"E@&fk3)WFZjYm-:\,UDcIdM":rp2[@lbc]AR
3E0@cBbXO$.ok-Df>@6p@d@$o$H+hmk<AhmAY\t&XQO'"hQHi4?,4or94VgUVO'#IkH>AK')
;aGNndKR^7lkNI&D9)qK?-8O.W+4S'2V_6P<Pn,koT9+tSCg[JVSI+,VGnB27\A8tCB/"<KJ
mZue?VP&c2Gt/U?Vcle_hfi@mf.?kkEQ:]A9_FNLEp9UKM:SUQ/V_V5HOW$Z-^:HCJV57fZ8_
C3fp[sIDI(bM9TZL1MD-K5qm*74dVIE)b^^0a]A!ol[D0CLkdq,C<Yb+3fCZ;\jAIb#np49@)
Bl94I`6;FOMq3GU>).5ks1=c)rKDacZM?k<<GM#M\nd]ACU%d[Xq[f(VL)7OV`i!9%;pZ6B:i
'Y:d>4=NDIh66CFP8G^]A@3niXT"T/EnrcN>;,[&M`qU#cYn4\fKFJQ:f6WZ*Si.]ACe=Srgp7
IV]AqG@Oh6X\kBKEqHo$(3T#;0*Y7\!]A6/F?u6et6a<SYk*j_s;1L#jjhZ?jr%P4&2HYrZ8Gn
XlncPjIkWuE?pR$n-&8q\Xd?4*ZaFQ68Y?4=67ZUa"u8&Jc.(C(cpIuDn0[+/_(UF:UC<ioI
Pd:@4((k3kK&VF6'cK%8giih!u&g3eGjfr"D9T?"%_DkKslsRsO@g>Ujq1nCc&L7kUu2LE2q
V_sr5ahNPlfhHjJDppCAoa^o,I&Y_pfW&$Nq&Z#G5W)2uTs6bMLc(Saj6h0Nl>ke9eLJc+T6
69P)J6-nJTt+<]Ac4!97)abYiZj77BG;J*9I[1j,go":cO*YM\o&a_2g^TGc$7CM[OK)IS"\L
>JG=s=f"+'Ea9IOQ4&;9MhcVFn)eLd$_0VMJ_)2jT97r)?jK))urZW,A[*@3-'SN3JHcP#W7
adZbdYk#G(S$8]AdW.?%*Kcm-LXkaCt)eM`D\.SV_QQ`i/Wc\h*m"pPCmeMeQLEI"fIa$8"r/
:#RBCL@V`-/Ho!a'86-%$6UP5Z[llk/0)#+1UW2i%5?%g6mNrp>m?DP5*dmKGacHaKT<PB5\
%a+_e=m4sYe&kXgo-?&io2!*JMCZ=qX(EqCfFgLU]A,p'to&L@!dhnQS+dht4.&VqPANOnH<6
Jr@QLNMIcJ:Xa?oFG!a,',Xs,tj>uf6-E3ec3l4q80ErY=P!;k'8%k/AiD0Mn'W`1u9keVrW
'0DWU&*YH`&?1LQRAgs"r;HSpB$k<2AmQ[K?tdHRLSO]A>G%.'QC_SHEDfblR$')P_6GE`5'8
NMe_4b4i;H3-`%Z2a4eT`a@9.c\p5S=88071G^6?,)V1rg/PfSn:oqVc@B2`pDmX8@A;oq:t
U$aM]A^.Z1/j`1Hes='!ed33&M?h^*-ME/(rM(?9'\TiqJQ7R'@E2nZ%BN;*X4%T[/O6%Q@J[
^G5M%o7l"<%3(rYbF&h]ArB>?,+gg.u5j,H$o[[*A3_sLPd9J![@q<UpaJaU)?CG#OCHkDMuM
k]AY9YjRtXP]AX2;is'4_Qu>u*B@.b.9M'Tc'Z7q[cQO`indcXeobcUXf?WV$km8jdk41]Al"0i
@2&n:fFY&@4SngbV0WV7P(?h#aIpgm(A[<ZtfB)Qo^$s#caRN.9?@?>SOSfRC+(Qi'//Bh:2
HbsCBj3c=7c`G_!+jq"O+&5Ti[R;8d;j<4%4AjkoXgJCCc,8`A`2JG^b>1J#jp@*B;M[r<`.
WjHlBUdll[Eo\5BbK416<rI88KbH[c:MO'g9dG3387'H>[S7OfC4?6DU<4^5FJD\,U-;YK4'
lidNn8<OiAA]AqZ\8gh=DUS$B<K[FO9_H\5@6d3qP?k^1/>V#XZLVfAG"e3b,8VoBrsULjG\-
%^+B,k!Fn="o'gJ2u;`eWrj^"75C;B!2d<dL!3AI=F]AM$*=nW\I%g"Jci945S?sP:'(H=5@i
hI!MA]A$Obps6pi%KfPm6OL[$e2*:u$W+MDc[8i2h[!:SEX3Z=6AOHH@BuqlCMkkfgGTKc8Al
VWL#kF[u[7olDk=8B6U.b-C_]A?5:[(`Jf^q1.2l+3WZkin4TR5OYY-o=GkLJ!1n0<*%Fs_hD
)j2gqB"%PeLO3U9!"AY[S\W9;%]A..Ct/*cO^$%[gb$Q%l.,K..9RgjT`DN+;WA4U+L)&8De_
A1c)@]AbXUR=&jQu"6Ljut<*<<QUaHr;Y^qoNf4RgN%$'_0o1>WQ3JXM9%mg1AU<skL<WMc.S
S1ZTc0E>9&(*^eSV:ik!m.f`8>8+-3_H/>G8]ApIM6D#Yc%V)<$e3=`Tsu-gY9Ma:g<\k$d1t
4dWb#f.J7a3eg$k0id;tauP%K.aPrRC02F30UQHpm,<Qr.=b+_P]AKRG%XZt6WaAlk]AWNl1pt
1h^4K"%''MP8ZtRcWbq,<VcJ>'P[lS2jtfV-mNZ]A8Y%8+aAd4+(6r/&!4:(5nR3hFBoDYj-6
@M^/lRulmnq.LLSA5#B"5BMB//)SAZ?$R@1tr.7BaK;aqqH'>ZX5'%[Ea$Y9lC.5[dJ"_MMB
%=U'-ZEO=V0W15>ma99!Y\jD4%<d>!%A]ARCZgL\B\n+3jCMT^ko98,?<AjU/aK*Y3[<0W_bB
8+I=cj;s#S8fLR$>.ptJNRgc+/RrYX.sJFXZ\dR[,ubEI#c/Y0;-!aL8!I&/p<mAZ$2@<\.N
EeRkOm/R*QQA._CU5<qJL@]A+A:(C5O*tV%+gEjg4/R<89i4S<7$se2)&>lTc(#c.5QO'47rB
[`rk^f.aR;;X1$3MWh'8X>eh2>C3sG)H]Ah?;U/51_@Goq;_#nAD7#RAb:">7DZVfpjdCoULq
7#lNTJnu\7u>lTRlslcQ0KPTh\_!l=b_fF.gDfVLBaS9`ed\Wt$u>FJ#@G!';%b`-ZF4b^<3
tWW6Y/:na$R$gXqeYaS9<g2>KC!t"c$p0.:dp7+cEb9Cl"?G4G,(8lpbVJi/_/>!#Z\=Y.fR
;/dMV=kUS4?^(iU>m?K8%a;6W4F^Hfr,5_FF?\1$Q9HUPZ6obYO2'%'Etn<Ke#]AE"_fmo\o_
`gmLt6X"h/cHC>kjrmp:lR)J^%-BDLP`b3'sd;TdPcUMfXt`+-KB'96(W#6]A8(WoT6g7u=lJ
o,<MD)qB"L=!H#B.'p@<mu'4^@XtEg9X"^%M2!:WRi7-;Pu9M[1aPXL1KBT<#AF?<K'%D!pQ
TUG8iCU?9%`Q[iNhHY>'`,LP#9Qjf/<u+0NdiJS&6ps[.j>%alBTS-KnRgkpgBu9Kq,qU]A"E
0-=mK[M^&VMQ[WLf:cIB'jS=0-95VICoY7W6@Mpe!p4e(%go*R=Q,gD5Oum1BJ_]Ah78WdYcR
e&\L.=\P:W.APb;,/imf-hHO-U'YIOZ5V&4.SsG`:.kCjMjgF3@o6H9JpjDJ0Fh\Tf%&)FE*
hMG%,UaQTXp<2t>M7-&@CrTD)/!p^qsH[hs%C<L-s=8KNfXT^A1!2CJdme_R6*)c>>(X_5.V
Z$ZjZU+M9B0mZb]AQ=Jp#1E`D5U*DEsdG:0j35As#j_KPkqk);D91!DLCJ(h/Vt17\(Ip(?%%
DL7RKM=JV%Nt#ocG$1l@e:P*Zf6BaJe#80J@/JLO<fP_Zj(A4rcLtDn\]AZ<9L::?KDl[n?0h
rH<n-ppRacF4qlYFr#'Ke[OYE_jd!!n?.H9@-0Y[G<n"m1lk1Nd/ft4i"tb'crD[Xnh<P4KQ
B0RFC^qQ1B*T]AZ:B.Kjou(;E9K8`$6*nS!*[!:SSM;5nfj.i'h**TYmCL2Uk0R@rb,^KFo"[
e4d<$P$D?dMRbF3`JVr6#9kLJYX*k;7@KIN$P@u=\.9NpIm+hc2M_"L_fN!\^RVJ^W;cR)rF
*nrc2SFi1#o4W]Au(H[#3Y'2dJg6#a?L0BmAc&ir08"6ciVmT2iYYg@K@,ZUmW0RR?^\BCW>S
NjQJ<.<m5LoXT(R2nt+W4GSbG\C1Nt442[N5m;BV&i+iu_<E;%@k4,N-&u_iOaG5);8r?N~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="8" y="8" width="983" height="50"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report0000"/>
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
<WidgetName name="report0000"/>
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
<Background name="ColorBackground" color="-16170125"/>
<Alpha alpha="0.51"/>
</Border>
<Background name="ColorBackground" color="-16170125"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<![CDATA[m9FF+;ca_]AA,mF%ES@E;BNQn=kc3k1<%U"sU]Ai9IUU70r18p:,/Lp=+N'*;S=KPW"*>`Bn+J
nA?2N`YBDb,I>TOJR2#mc(r.0d;L#Vkf"gs?qH;sm4Y>$Ji4K-0i-cIqB.Hgbl;DN;H=3S<*
8a4&#H`I!$Sg+=p0J)\#K\@,(NLTb6u*TalBO:SL$qo1_>q8;$,a3E:Y+KkD0al;B8XVV6*8
P(R_5KC-0fC)l^JpqG.bb1Nm/>`>o\&3oun?^/Zmp"*e&RW/P))Z(E)RIY&@Oci*U"rA^"mh
9hOLA+';1mqb7G\3^5@3Q(C:acsO]AJlk\,b'LZ1@,=><dCV._.k3GP:M^G$!Y?Z`(E.Ze52[
l(gBg[`bDM:-7^Vfd7@[XX"@Q;u7m1s8L"<df&en#qhn3VERWu2frg.?Js\i2n!?XjpK[.Sl
<%`j&g`M"@n8*Jatj)1MLctn.c9oRqp7eh#P2'6RiZABcg'=<$K^J[,GQM9@^R4@s)QuGaB7
rl?^s!G5T#,-t2f)O(c)BO8qq%!hW1C%kT8e2_j=?/KPGV#,IM'jSn9'@JF"e?*X?[nd(`)Y
M'<Ydkp(\`Yh5MI%P[G6QJdCP;FTP'&qVX6seI;/h5e&BJ-Ubo1,+$GXhu5i)n-3Z,E(?nh8
XSR^V[*iPdo6'`W(J`u#GR^6LObcR+Ho7mH-J2RA_uCTD'oE^^!;@Xs<Gee]AC(7_>^IMpaMf
\r'\2pFDBimiB$U]AmY\"iCb(I_@m;.&c?UJ/5XrKJlLlM.t&mM/;Mh%;6t,C)64Cnb#sA$>g
^^'48s^DG_b$7)$b1Nf(1;GAb"i#Xa8<hs)&=_HLNijIM&u+0.b:Q3I>=a<T6#9!!tFSmOa5
JkqHp%TQfk]AY*WuW\ToQ4A\-5rpkI%ro7T"U5:Ou3H-m/ZolY;?hCR3"lJWuTcl[nD1i1u[4
&S6KU;N_r#o+a!h*%5I"Fd7hd/O*Y4"'t#>7<tAe5RWnQp+7&DgLI8fB#3t=^nOi`7Wf7h,[
ckJaL$1VcSo3'(kdNdW;T^_\f0%Y5O&uQphE$+0,&&RLTUC.SHDa<:$HG=Y1@fd;/<#gHXru
/eMSpM[VgX\oiVYD.uQaN&u[s(QG"8s#Yq#?GC\(7;o/O=6O]AMUM[r/rNeRq(Jlj0cs]AYT39
0=n4/[NWH#=nR#=13@j\KUMNAe298e/Ol4@3N[=qY$Ij*W"XS;>CSVbueEYH[P_+S%up..3T
=9?B_*-u1^Oa/?!Yf7koQGeYmOdN*-C8+n-A6O%5`ls3;ghR`N.n+NX]Ak0L)NKs&]A2'1FuDc
**E$kLb7c)+=[Z@0<=R,HNFG8XPr5:Eun7^1?Kk2<M(VFQn:[SPP6W*ZMdLR]A4!3>d#l0:tX
mm,:Xc/N$G(EOq6T`9BCsXOM+96]A+If3f_nu'dO$4^\A9n]ADO(C;d%GJciPtB!$:1T1[]AZ!T
Zu[&m\Z=Lj4Tk<T'#Xg6G7+kinF#C"okrfng[5]Aq)D%<ViIf_n"_dfM$L/KH8cgSs4M!f:mr
ourEVi?Mid"sJ:7(?<>%M%o$A.aOMW?+9%'V5"hrPC!F3[XFU"5,]A5)fMh&]A"%q6CRW4TV>;
GTsdh&Hem?5r5Z7Y<TV>jLF>M(pED)!MIU%?`qBUpjd!k)C&V37Ak#rZe?hm!d8rfN&<SQe4
sp`n/PIH5,O[eSk`S<mNJL6,F`gYLH`k_Xhb;VG+T!UUrHMZ-8]A8kZCn>niW8A9Q9FlAe::4
/:QRap-q.!*j-mV[&M\'Gj/+=kj%K,q>oCq.3dXB!XEhar1j;NQM_:LGU^0I$KYBS3*^eDbf
qGT<TPpYUUNU.c41KeB^bom(GEJ#f]A(%MhdVSAM:"fgAI$o/ctfmeN.FVkY,e&Y1mn]AL'eqt
6[RZpZ_ecP?9Z,0HRpq=5$)r4XfFl0M,O&LsZY>Tc03A0K(3E&5:PKD"Bc[OEI522E1CkLFl
L<3=5*JE^'eK(R,rKEU@=!nZQk$9eHXh&)Ftnt/0lI`/2F0F&Y/<\]A]Aq-CKmA%./!>IHVQC8
<3/`=A3CjgZ=p)"If+\r`PN-h?i4:f(+TN(';&8.hqs+bOT]As*Y/qN.V,T/1Oe0f$Js)p@4a
8;Q'po@OJFVrXFG,X-jCK@[-VZEe??T)^/9OOG\i>>cH'Vu8AnVH3.4[aUGZg8Z!A9!U=J:5
*l("PjajLAj0pG8cqZi=:VY>HfJ5RF5s-0F27uLQqYd=?3XFu;bDV1<on[CqZ3hYXjo!Z.&X
`VT4A:):`24n[":TA)nlS35^Hn_'lbhbT#h'hB1GXA!>=,o&DZV=F1an0/oOTbu7i4I/Ne<+
j[1`Oq&Y2I9,5E$.+V3]AZ%,[f#mggXIr\P$2a(cLm/L8ZJjH)GnIKnK:$U.5aMNZ$Ll9,>n5
SG]AL;7`2o=3IdWV,V]A@I[mr>H<BJ?aL64Vq`kbO6m>Vhr0l0T;M$5M(mMs5gatA$SMf3F@=n
*X>Tkg$n-;L/`%pqiEZJ@U@#.+iSgJE13$;a-UaW/G/STsNN1VDmnK&W\C9Z>4OI=1m'=e2X
ckFMMXg1f8/TI,_NsP=FNC>8ZeLgEDGOEpSKqP2:G'h6/6fo\M,+H<GbE8bhb1OKEiZMm]AA5
X]A*Vd[YPRWd6e`f?q,p+TeDktieU1'mO4Ffrt,l?kkRX^\m'#:_ME1t<u]A>1hH("D,VG9q.q
DNnq38R7V`2!iZXqXC/Khc_uYLfehp"iQmVqO(;S3+6Agl`5`I6NcNr77'j$5R&MldEmb4c:
r9EhA$IR]A[iKbgr-q.\BQSe,;#%jF[S7A/?E!OY:g5<,3If=iJr.Udc>k^VZpnjQ["I8=f?4
6(Qk`S0$IIRk.'n2Q+!ZPIF*Ro*qnc;`#_rU_'O%44Pgn*Dp=5q2H4CUQ<>fM5"]Af@EjG6M&
)W\!l"+QmJjB3g/l.6e<QH(cR,@K#3#GEG8kVe_gRmjP[mK8en0#c.@",oK`7:b-K0!^Sh`0
[AUf7HmL5e:l$\?8;*O#&bppN&a9c(S/0FJTuc?%e)l5AMs<'!m.g5$O'N0r;!('Zcfna*O*
/7&KJ&Co'QcWFsOZGLSGSS$ZQmU?-$sUf$UuA<(9mjBQdbHVBH3Ns?P6YtXc4'fgC8Hk;KJ[
=Vk:n&nc\GRkqMAu."E*R4aGHZ3*-8V4=I_@^SbV(?to4%K8b1+A.*Ue)n0iS4ars++5YXH1
]AtiUM?2[QNmYduZZj4CFp*\?_JS1j$p/5S=NBZMOgiCk$#nHFoAK4"E/&H%fk\/tq6-`LdTi
D[ICi<b@Y3odk(B?3X+T2Zrnbm8Ng&Tl/Hq2"m(\"_pP3&=rcHcd^ekZ1[Q?\jPjalP*IRNa
H1-8(.;TV4ZC>$qEEE(Bjh'J*BBfTinfoOEI6U+OPM9$2,-Q+OA!AE9_Ik5s_Kp0D-Tq<eL(
=kDK)F1VGq3db?9gh0]Adk-j>e$au#dmpD2R?dW:ua<!YH%PaQYW+EX]A13;0bH*X)5)q"(_6Q
j&<@(+=b@g69<t9N\Td@0s=T7*t7a"E7rp;g6_NgY#4T@@0[6=>,nJTq=R[3n>Pb=dC'T0^@
pC5$L^OLSQ;nJ(k[g&S.)_j^DeC)-UF:0)dIjgnY'"FW+Yf?(<,qg<"6@)nVT(^e/5N4B\Gc
)1,7,MdV$OqhC63fAZ+bn$l!pHcYstVAVcJY^W!&J@@tME8V&&&>CJPFc3c&UgKj6PLOn)6T
k\Dc%&jIBrhH#Sg"jOdC!!4%tb'E8]AAfs+ES6ap,JlU*.ho]Ae?jUUU</rN066QG1DkMR(L]A?
F"Nnamdf,\jJl9Cc>S.^M]AF+#KKmEJ3@aia.^g,ac>j$-!+AUu#qW-Zj2*#cbe?3N+rI]A"9K
k@`Hri.)?7ec0O-S;Sc9uPF7/N@ItpY^s%lVX>.DQr5>'%%?Wp]AFVKP(LH_&G>`iDC_iq(OT
-W_l'R@bOVAOYB_Fc5D\_kO@>k`bpaUt'1Y]AAN$YlF-l%"f/K:Ol&W16G[B&AE\]AU]AhnWR<8
Cr6XPNtcoi*ar^WIBA7=NP8)Hb]AHX+E4Q[;XA#$Z:;Ykd!bD%%ctAJ&n1"tNNq?l1Pd<oH6P
%%rf9IP]Aeo"a:$r"YC\dVrm$4+g(3rfR&"roA=h)R3?ee]A$r;:$g%^_KAp[@H`Zrb\t=BS-u
g=q*c*<nm_HBeg+5J8i.nL,X^g&O2atn_msIVl>1VJL&AAMd`PAla9[4%4W38E$IF]AF)O,On
@?]AY'Tf1M.#"X8UB%*cn.&FLFX_C_!1/)5;UAL;5AA!c4r%TZd&N]A(8IQ7cI6OrsjKYqs/t0
TB#G#N2QD_e6b98RiNh3ilkkZ)K6c^Y&O+M[EeCJ;&-I(:ZfU[cq[l<Z-H;j5b0^ApAS&=D!
',\,7KOto#nRmLNlY:4P/n).LZ>).T)C)/CgV6.J9EqgLWccm(#RpAc_<TFi"Ec[LV5H-5!Y
s`CrJ*c:kN+'c`^3Sj%656gVjl(s]A%3QU3L9_lWg>\`"_G^j+S&CG>dR^;]A`8W2RkX8Mo-81
^L)b8cs44rqH#6+qjktUg~
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
<Background name="ColorBackground" color="-13382401"/>
<Alpha alpha="0.24"/>
</Border>
<Background name="ColorBackground" color="-13382401"/>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh.v10" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0" refreshArea=""/>
</FileAttrErrorMarker>
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
<BoundsAttr x="0" y="0" width="1000" height="600"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0000"/>
<Widget widgetName="report2"/>
<Widget widgetName="report4"/>
<Widget widgetName="report3"/>
<Widget widgetName="report3_c_c_c"/>
<Widget widgetName="report3_c"/>
<Widget widgetName="report7"/>
<Widget widgetName="report3_c_c_c_c"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1536" absoluteResolutionScaleH="864"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="1000" height="600"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="1000" height="600"/>
<ResolutionScalingAttr percent="1.0"/>
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
<TemplateIdAttMark TemplateId="d8eedd99-7a4a-4372-947c-749a88cae410"/>
</TemplateIdAttMark>
</Form>
