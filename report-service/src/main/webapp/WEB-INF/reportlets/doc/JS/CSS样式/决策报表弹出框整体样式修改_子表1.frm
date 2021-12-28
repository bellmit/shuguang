<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="城市费效情况" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[dw]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select b.BUName,rate,cos,qc,money,ccq,
(SELECT sum(jsrate) cityrate    
from
(select a.BUGUID,a.BUName, a.ProjGUID,a.ProjName,a.ProjFeeRate, a.ProjQyAmount, a.ProjFeeRate*a.ProjQyAmount/ b.cityQyAmount jsrate ,f.qc                               from (
select a.ProjGUID,a.ProjName,a.BUGUID,a.BUName,a.QyAmount ProjQyAmount,b.FeeRate ProjFeeRate from (
select ProjGUID,ProjName, BUGUID, BUName,sum(QyAmount) QyAmount FROM g_zy_erp_qyhk_month where OperateType in ( '独立操盘','营销联合操盘' )  
and year=year(now()) group by ProjGUID, ProjName, BUGUID, BUName) a                                                  left join
(select DISTINCT  BUGUID, ProjGUID,FeeRate from g_zy_erp_projinformation                                                                                     where OperateType in ('独立操盘','营销联合操盘')) b 
on a.ProjGUID=b.ProjGUID) a                                      
left join
(select BUGUID,ProjGUID, BUName,sum(QyAmount) CityQyAmount  from g_zy_erp_qyhk_month               where OperateType in ('独立操盘','营销联合操盘') and year=year(now())              
group by BUGUID, BUName) b              
on a.BUGUID=b.BUGUID 
left join 
(select sum(ifnull(CostAmount,0))/sum(ifnull(QyAmount,0)) as qc,ProjGUID,BUName,BUGUID from  g_zy_erp_costsituation) f  
on a.BUGUID=f.BUGUID                                        
) qh  
where substring(BUName,1,2)= b.BUName) -qc as fxpc
 from 
(select distinct substring(ParentBUName,1,2) as BUName from g_zy_erp_bu_project) b
left join 
(select substring(BUName,1,2) as BUName, 
(select ControlRate from g_zy_erp_city_costsituation c where c.BUName=s.BUName and year = year(now())) as ControlRate,
sum(ControlRate) as rate, 
sum(CostAmount)/100000000 as cos,
sum(CostAmount)/sum(QyAmount) as qc,
sum(QyAmount)/100000000 as money,
(sum(CostAmount)/sum(QyAmount))-sum(ControlRate) as ccq 
from g_zy_erp_costsituation s 
where year=year(now()) 
group by BUName) a on a.BUName = b.BUName
where b.BUName<>'集团'
order by ifnull(fxpc,0)*1 desc
]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="费效" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[dw]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT BUGUID,substring(BUName,1,2) as BUName, sum(jsrate) cityrate,qc       
from
(select a.BUGUID,a.BUName, a.ProjGUID,a.ProjName,a.ProjFeeRate, a.ProjQyAmount, a.ProjFeeRate*a.ProjQyAmount/ b.cityQyAmount jsrate ,f.qc                               from (
select a.ProjGUID,a.ProjName,a.BUGUID,a.BUName,a.QyAmount ProjQyAmount,b.FeeRate ProjFeeRate from (
select ProjGUID,ProjName, BUGUID, BUName,sum(QyAmount) QyAmount FROM g_zy_erp_qyhk_month where OperateType in ( '独立操盘','营销联合操盘' )  
and year=year(now()) group by ProjGUID, ProjName, BUGUID, BUName) a                                                  left join
(select DISTINCT  BUGUID, ProjGUID,FeeRate from g_zy_erp_projinformation                                                                                     where OperateType in ('独立操盘','营销联合操盘')) b 
on a.ProjGUID=b.ProjGUID) a                                      
left join
(select BUGUID,ProjGUID, BUName,sum(QyAmount) CityQyAmount  from g_zy_erp_qyhk_month               where OperateType in ('独立操盘','营销联合操盘') and year=year(now())              
group by BUGUID, BUName) b              
on a.BUGUID=b.BUGUID 
left join 
(select sum(ifnull(CostAmount,0))/sum(ifnull(QyAmount,0)) as qc,ProjGUID,BUName,BUGUID from  g_zy_erp_costsituation) f  
on a.BUGUID=f.BUGUID                                        
) qh                 
GROUP by BUGUID, BUName        ]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[dw]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select b.BUName,rate,cos,qc,money,ccq,ControlRate,ControlRate-qc as fxpc from 
(select distinct substring(ParentBUName,1,2) as BUName from g_zy_erp_bu_project) b
left join 
(select substring(BUName,1,2) as BUName, 
(select ControlRate from g_zy_erp_city_costsituation c where c.BUName=s.BUName and year = year(now())) as ControlRate,
(select sum(QyAmount) as Amount from g_zy_erp_lf_month  d
where year=year(now())
and  OperateType in ('独立操盘','营销联合操盘') 
and s.BUName=d.BUName) as ProjAmount,
sum(ControlRate) as rate, 
sum(CostAmount)/100000000 as cos,
sum(CostAmount)/sum(QyAmount) as qc,
sum(QyAmount)/100000000 as money,
(sum(CostAmount)/sum(QyAmount))-sum(ControlRate) as ccq 
from g_zy_erp_costsituation s 
where year=year(now()) 
group by BUName) a on a.BUName = b.BUName
where b.BUName<>'集团'
order by ifnull(fxpc,0)*1 desc]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
<TableData name="ds2" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[dw]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select sum(CostAmount)/sum(QyAmount) as qc,ProjGUID,BUName,BUGUID from  g_zy_erp_costsituation
group by BUName]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<ReportFitAttr fitStateInPC="2" fitFont="true"/>
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
<FRFont name="宋体" style="0" size="72"/>
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
<Content>
<![CDATA[var link='<link rel="stylesheet" type="text/css" href="../../help/css/dialogcss.css">';
$("head").append(link);]]></Content>
</JavaScript>
</Listener>
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
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[c!1QW'DJredMA*7Coq)V8ZB/.+JGnE+saSG]AndC<>Te&a;,Isg,sW6FL9sht.O%lW86r/<#"
9d]A&5C:s(h=5p5R]A%PO=Z%r6FN5p=4D8Fp[5`@gO&,Do8B$\b^[q7425iK^0eKa\`KXOPK&S
$B=W'%=6mkcHV6>9gmcb?]AP[LWPHlmnoWQ0TYEl7;SUTG\Q"!f9HUhMX7.Jk(#tLR<:0f``2
!2L3ZefqJW^hP!n]A:oT40..tS0jdJRd2$B?%M0']A?X-\GOk>uHdcS?In2fWg*`.V:Mtu'[!0
Q-eDIhhcs9V[B3hlfHtoIUCmt.$jnQ_K[,/%83,fp_pbqaCa"*4qr;EfFS8"Ko66!^r[G9__
@[Fr=>X6d1n)\0Fe?-"hE^YZgG>#eq0f%3irm)aN<J*9g@>=]A"*hhlUi*rS2RB.GBnR@jiod
&A$@t4D+YVe.S\:]ANn&K!V4Dpl#%R>`0?*QEbprEb'Ojdb?*3dlF))GeU7hG<ATfk'&UUrsa
:+[^[&mdrp$LFVOKA'l7&j+$E%ko7i'C+FX8-_$F(]Ak7`kq*+5&et<R#3Y;dh3of9c^-%_/j
'_4*SKanMFL\ORq!NO\X`E,WBu]A6brqKs<XLlb;DWDAoKYV$gLO]A6*%[G^7QX*[Nk1T^1oG%
5fDp+eLK?)rRfSi&nC&!#iM%:UsrROo_PVJ,rKj.<45)Ud`G"L^Wl6NWh3SLAI+4eA4a@>X?
5i]A2c(_Kf/m!i15C89I<E/#Cbhj0nH6nTF,Fn4@?n6N0Hi9Pq_>Tu\tr,$+gkih>VKqP4:I(
I<QT/:j1lY@ub/ed$O9f;P0j8?RX"Z-7iO9'\l2p-L8kVJ'5U*QQObkh.n)VeKGR&B)lp-9Y
ZB21Pb]AiRLNM2G.CHJX=QZIO2=s16X_?)%"M^B7Xb=kDJH_)rT`12I.XEnZHSLmp/,al?7XB
MQ(DK."JA#Mb!<dgO$*Ol$=/i.hJo1Q#>;(#YCRI+MNsr324/-aV=FDs2_EBrR=3iJ")Y7ZX
1XjaV'=X]AtM9Cg8l<E!N[h:D/0lFTIAVlCkN16"YBU$=#M[lh^!+c-^np+Co)m+]A2Hi!_`Ej
6ra7=JC)LG4gnlWf(dLMqnS3@NV%Ir/Zd?tV"S7\;snBORFhCg#p)>r'RDON9lbQW8GsjYVk
_#QQJ,N\N4?uhKeGZ;C_s_]AnTc.m=Z@$0H]AmX&7pO+63cQG=?1PFiNefBhJepBZ:TPIOU]AjH
<+%EHSmCDu@*6fF1oo'q"o`@5irRtt(muP$9F925<#*Gm`]A*lP,R%?V)0$F`^l#@*DrAjL&h
2mDMO<@*(Kt(iOA$6QVd9W80i.>X#VF`]A5!c@b>F(+",=a=cj-(AOtgZmM2!=!A#hJ=tle[i
VI?G`0kX8htMi:pL*pM1)_3*63u$^HcYs'WXhr)b]Aj8FliX=?:Yth)ZB(DRc;"8BO7*TutVF
=tIZl?6A&[+%A#O<]Aj78l6Bu[c6m83UfSsp5Kh$jc[#?8jro!6FX$[Zd_`X=A.s)tPbKJ0,/
;)d%e^WS[!<gGh3>ZHeGoHgf3TgC'lVl?_jhV*b9@jQoX4)&5i='!j"66pLK9b"OR>4^p`$3
i17B%`JKitlQnV.P04bjJ=ltfR0[^E@c/)t!$?bmOKdt(XIH#BQ:f7r0e+M:BP&%uB^m_u-j
e\j\:g)Yi_!i+sXX:u+g-7tr^$BPE=o_3);H=>kZL(DC?m=4YasGI;(3'm@;E*=-Aj=t<Hj8
rb`L.>O(K1Dh'P_(3[ARq(\=<HVh2!1#\Ll5J.31Q4XJs>9<ZZk7Rc<<UDCZ-]A,QSXUQh3'e
B&pIS33-a&)`Sfj3S4D,8,7lJ,)p+E:=3p<p,5/Rm!fkfac^LL!2VlT<+'U:(8XBgTkid%Bi
i&\-!@YDl]A-Zo4W1(Qleb*a)Xl%q3sZ]AmRSOEM^1NJuic1k-%+%GLo&N%[UXBb\TA"jeBp5s
;,MW,F",%$:E>P=S5M#<>F9e,@ZKqWZLn@XV[MbZm9:L]A^iIH(.fG>)*qc"[qo@p,bqfbd8L
LhPh$fle'r;S,McjUZRd';_B`<P-o$oq;C,KnX"NE0<b]A]ABRV8hYGX_,il1,1aD([9j.Jj;-
K=Gi6tg2*<(HSq2Zri*$YPiWI"]A]Aq(0jcK5<[Fo[*^-\qdMZ6uhpENr;/6bL"=OD'RcNuJ1P
QH&2;Wp;2h5^^SWQKF[poVrR4`DnQJ1Xj'X^P2OC7MZ+^?EY[O>?[tB/tt&kT30Ij:e'fq[q
_#6VODU6%L0`a"%^8g3t]A-APQ^qss.'.$O3P`te.)Eu\+Yc`AcLs#'^mB>-QF`1P!^X7;N<\
Q!sp/-"3%(W%Bicoh%S<La!2QInu)&9UKkVR5,S]AoWd\^>/,,EAdSj1l-[ojtLVT)q0LEY2c
o`GlFfL;`m"+8n!Jt]AHHApA#mBg@4UWMYgG>Rt_$[?VW?+>$q^mEI*j:DR#nrV(/AJ3gB1T]A
cJ$pNTpd:O:$FRiSGh-ua$7@IanrND/ZYX=LB";8,:MVWs.oIY=Grf:'q<:PqR@c-?VEQqFX
=fJjs]AthFqROg#>3m)>r8j(Q3%iRENQr:0/FT!e/St7'h;5O19]AWVe=7mb0,2RNM=-X;<Z2-
)<.2)&iURoi'PlWT?[E4`a$)$PdP_&qQeFMJaXeL?bPAD=<:IL3s:@:OuGqku21&DM$ON[7)
N3*G2@7!HgRkcjlroc]A3iPk#;dA)\BupnEsWCoZta`$W3lGp4FkaSl$9E8IZKEEj\Le."-3<
;KX4/IGsAIuPZN>Qb1H+O3&tE$Q!>>hpn?C9)1Q9/HK$eX(nITIWN>ftUP!nq2Dp6[Q28I0-
f"0J@dp-N9X:Z8PfYboXX2$!KJ.[=OP_0c<M/Y?+/RQe^87'Moeg95Q:_Z8)1`K;&$DE5PY<
%uWtlrE4XfD=f>5'X&/O=RcYjPqQDCbQs2>V#BK`WO,kXbgc-cX\Lr'M7Be<;<H96X6JJfR\
5XuA2M":j+;+Q=9=MHqrS92'Cd/D!_!<4BT36M^iEX+1-)FU:[&Jtm67sTVepF[%dsPc+gVU
?WuLIA9L:#<jO9n"BmJtA[N4VZN@5P!r1LE&`;ft;`An)"B^UXYb1:<JW_NY5p%.h%GiNjqH
FZo:c+?NR\ae"]A1-;foe-hpF>5u*Gm>1+,oDT3c^u),pk!WHc-r_$p<nl3E%78"8aPGHO]A'/
<Xq$moPe-Eoe[k7(ZdrDm3/V-Lg=K%]AZQ6G^l#?!8=52h,:h,@)#c4A:>'#4f8o?]As$%DkG!
`.ef_NSDBeJJVZ^m>HpMo[Llt(`\8`8f[%O[pV<eYcO\;G+KF$ASb('5[R-+WlUELC6+>1;:
+JH[pO/Pk6_^89N%aWMCDZ?#Uhp4PL8\4_]A77-FldA(LiXZ8!OKY48h5Q]AL`,JkImjlN\rKM
"MBY":JgGu0TqU)g>em]AKrPa"Aht=`$`kLF=.c,bp;t@-TNRtu2Q*aE'g^en?[GaVpcuWDi\
NLI\1=8\G+Ze;HasTJ/Z0*<:qafMF;l0%-G@1?GL\htdj*!d_WT-6!Yeg<n%]AVp2s5pjTB&5
ikV0@eqVbu@-O:E9hS\@7R`8BAZcsI[PB?Le><@eDc.9CNKP0V0WL$%/7U<%,D.#seu0fK%N
[k7mG[3C==<?obYo7-Y(#)VI5KESnhG6!Oo0eUO/*\_]A,[WHuu#=.(-]An;*J7sU,FCs&C_*Q
#:,UA.7Q!q`:0?VOhX\a2XU0LCo4?l(4S\NB;305g^#b2-=E$FR]A)GA7lnnYUt"-EogXkJLi
Fd%YPb+64)<eR,=/@ck0dH;9lRjFid8S;1-]A8GPs>\/N.:>p@8$Vc*i&JT6;76MN'p?B5F4l
.Tr<"@(E&<DAV<n.GL.7<-(&Z?IXI,8>-nHt%omNdi,"/u,E!<-@Sk+L#ufIW9IcB+dUp:VN
W:7O'b-$;JP5!jq=o?P:eD5b7MGB+Y+=?DYD.4`RrB#uYZnKr!FrAE!'`qHOu()P0-2)9^#,
KL"n.V>=W_/YIE+Cg[gP!5Tq02)$\d)DTRXIukQ%0/h<]ABf@7:NbJCno26DSBYP%TI!3>Q1B
9AF'9o3HDk0Qlgt4.s)em?6l5N?"&BA`o;,NIc7;(M57B4JT:*F^[E;"(LABU)kUa&%CFu+)
tS!4X)A2\+8Wfs[/!V.F-O4*'f*A&,T_!BJ=H2;i(IArMU?nIQk+g!*>&r.fHP#%`ZK=0P4g
P=;K#Y]AE<cc"/b&3'Mr(.K,c@lSj!e`nTB#C<q_Pgr>T%$%<3^(&..L/"Sd)mClTDchT,A/]A
cj@rh_k!";S!EneBbQOtX-CD2ut:%1W]A?741pWK';'H)gC#_baLb7QX.'W.T5q-e]Abo0&EP+
L-1_-BKac)Se7sI.KDZm#,6`N^HIad,efOnk#677Yc/b<\4_t$-"`49Ql9O<!Li.6D1mD_[4
#KOAO?i'Ir$IhGG?1k`@K&CoC7#I/[Q`"#jTknN5NhL*4SXbCUFEM#U[GKITs-_$rPuJ4:Vs
%L-S"tI>VO,VBNHme;KeYp<_&e86CKS?hR1-;sXZJqCkgm_/L2Mlp:(0``A`bgB6_C-`0ioO
lWt#+:t"$SSu7KgPUK)?]AA.)4En9i0*dejX;sh?[+8q]A^Y)!F,(O?A)9m9c>k99naSTZLq<I
N`6,o<,=R&Jk'qMpgH]A_KPQ1UmEeVs;HR2?@;,VT.!]AT%/Kii5Hu#ZdJ!/.!8D.a:(mcV(Ds
8+7VSmNh&Cm?0\9htm`H#]A?l/'##t=3a)`.[E6-WWk*(kN/nOnji\hgW@U-s5Qh;=>>eLgTR
g#Fkb(`[om9[e&?i?Ok^&lDhTG;k*WpE5^j"">g;UK-97#qe#[j%N;Ip=u+pS-Rr]A5-\?=1;
V7R^/S#Ccr%D"Pm'Kd]As4mk:.=<Nt.:^!lAm'mJ;qH,Oql#GK'D6._r+rN5K"!.f_ZC^o=,e
luFV^g4k$r=7J:-KANaM&R/El&r1J*2/Z4UO?kkl[Z)8%1a?u67C`F[G+1E2V]A4l5U^UGMQ,
psmi^'ejP;-L'@eO=,,?j?Q/41)2$=go!_06'VLA@uDb)g?Rc).J`c2^^Ao,l:GKL+-qYom<
2;L[QBVB>*a@r:uVJKV$cl<4k\VZs89\3CH;=*&!-[2;&k(mtCf@D/$/=?<Sb$^ZF:siSH'G
-WQ0t-t!HXPH1=m%'cZ:0O&5_EOH=N>n0n`]A>k6T&_&(<Y"-^B`IS<Y';IR*t:->n1urV&<(
]A_&7b3`hsS,UOOqD_V`f>@]An'a`$$%UF/[+:2rS.FL*2+^b`+k+*,&AeG[<6).>ZWVH*4JQp
CLHE9k1PcEecaB@#`<B)LeO4Du>q+n)$Jb3d*9KAW-S09=&c7U$l'pP!F[QhpD#pFJW+tE`Y
'!X2[`0Zs<ijG\@[!&b>.p&^WO2S'4Bi%gtfui9kNuW;@f=FKKhp(1h?:0M5IE<K9$)(^Nmh
8.iR+[kWKdp@/(_74&B]A$;FMjr*Q5B,R\`'_SDfnTH6(C0U!*3B2ZQC*C?SZ:Ffe9\AbQu"e
gVcqS9R4pfNdIO%#F)dH806o0$XTZ<s"b/D2>p^qE7US4[opK8J7j27pqZd?SBIY1OjSYdpV
L8i&Z4"DFU(b?MkWC#c(Ucu@!c@!0HZSl4:L$3o.;!"O>]ArgprIST_rJN84bVAUu9;)&g_ih
K<_\]AaZNTr4LfV(t3nHkTVDR!>LpJ(H;549D+_%l!m`NN(*0HKh)Zr>3qG2nU=1CjEf3[iZO
;gdBhc(Ot!6Lc>^10EYmWY*2E\dYn%qta^Ud.O6V>'YEK:P/mHR0EYrn?P*]AIaErt6=/3"B.
eQ*P5nj+Bl1?^HVCgn5WS(^o/*(I.Z\TIgppV*V[<5u)c*&KPF,.2T*5=>hUr:ZHj3YK_\H@
2)=*Yuae)bp""L,K]AAr)#5I*Ad^K?S_j#b`BhTYC.VGFuG"2,pbq\6K]A--#bJ,p%MhADV1+<
odr>35&8P-'eq:+OR?@0rP7UXO=N9qs]AP-"77Z)h%WXBqT^Ip@[4]AdY#Z9Wgm/,e@>5j%YH@
_/_T[GJ376<FPP&'PA+Z&rW##d"O$3.`AcjY-W=[D=+NIFiKE:s&$dl&/'h:Q.F2$_/@;41l
2do*F'uB`-l2!6Abm:td5F>(KD;&e:P]AL0h=3#Fs54Ks\[l%YXiDQ(c*!_'Fd'MJ=R[W'X"L
qp"gWB^G=f\Ar8&PQi^(jui*m>k-:Ok"DVJ-RDij4dXK6%G@6;S=`Kt-!j5>Nh1;RRp]AhL65
X>i,t\!QLXL1@i4_eCXm4LW=N]A(\Vu2d-mMeg<%g-lbE(THOg"LjqP('&%nb'SeIVhWi*is:
nB^8;\55%4>rZ'B2.TQ>n.*d::5pB;@AKHqO'GhZ=eYHJ.#Z43.WR`_ul3f<Vo(bR:j0YGV>
,Wmar8(fdlp2J/D5mGB`><s,BkNZ[\s,a_(!OZ\Y>iWYJ?:"-OaHOglW)FpkZ64r!*g<N2&+
=W&Pm&7WV!fJ$`rK#D8WY!K^l(>!0.%)Hqi,Y,Uq&5'355Da<7?40to:AQFqQI<U"tp^VNce
Hs._ogoMOEpWH]Ark._od6fM.m,bd[GXMQC75Z""[9"7`;(.@2QHKZ+_;L$jEcWlf\,&[^t;X
)oNF/W':,%!S1hdU,GlNA)fq\?0PF>m4(BCa:"X1/_S[5IX!SsU6lfc1,^o14;2d3XU$D4:0
r__`?WihdZT/EAkI?6!)^"%@.lXtOXVQ>L<<Dp`hE!SkqZ`OcDF[LBPhHjVDSBq=gf0)6CmA
t,.DFDO[t%=5[i$E-O1/$h\<d$aqED#tYBYA<.R*T0ba#B'6o^EtS_jZTfhS#CD""sd'=p?T
?FP+[>Y4d1;M*cG0gI0:35q'AA\?E-g6m30a+]AoXafaS(4L!GU!Y4I_Bb&,qPf"!jQWc6\@*
8h.\R+i?c87b2h0*m_5b1BK\]Ao7Nih)E:Xe;KOF[U0;f\q2O=j\)Kito%b$.2N6[f!3/Q>BG
:E"6+aRpVq50rLM#cljs&T4Je=?nC-Obd[MM!+-n),HeQ.fO%IR@9_""f;I%)a4II[<h(3@j
,W[gQU$5-fS<;2DcV]A]A*$\jb[dZ.g%S\7$2INPC7lQ391_@P&$DL!)Vu(A>;@1rjDC6=p+r.
8aA<C&i^`+*u$?fA(jE+$LP#(48/2TIGPD3kN\BQDQZN+`tBMj!M^gD9@EX(C@NGe6qrTSh:
k+SSYhCBgo5h"&h<f1@2u,PE%aD@hd&.pKdZ@*1ZP!544u6=Oeo&eNC7Fj;O-aLc8m`Sb:E]A
GW>p?YJmpg96H.1I%I2QCEJQ_fPMB*_o9'd:,<'tgC`nL+CotBNum2&<gTI_!#B"_%HQFM%e
mpP>qhkEa<!d8%h.I0RE^+]ATrn1$WpknDX8?n@I[.@l\#K^$Ye)s9BR3bQ>RRrcLA;A!O:H.
RCe$YO>18pq!@N`=a`\GC7.ik,U21l;qolB-%A\qsb$nB0^_5&H7:a-H<19fO/X`8b3VC6]AK
t'q5+6=!aa7:kq@h!X*%#olorka>]Am3^GBi5<@2P$=sD4'E,h60&7\fFmW4<#ed6'9Hb%n$<
i&U>Fu?LWHiVPY`cnYF#4m7keDWB*O1u"/8X_#oq`7=MNi>AM;(2)DXC!qFtQ4V=4LIcC/hF
jpHd`8gtDu$o>qYOhm7rk"uoO>JM2]A(lg2%;nB7*\DjqVfDBM^@(e9lqoCJp%2t$<lAh4IA,
bIh!m,gg=H!V;JSAjASZZi-_)r%PF-u4g7'62;XD,AuP2h>#Y0hfrNB`#gY3VTf='#[T''[V
1dheX1SY!lE%?<*5,P:cS-8)cJnFO-^_8cQn]Aa=h=^^<3*?.+.RF^4OONX=Ejkf7DIf`uu3I
uiDo%VliCcT+t(Gnq1JS'CR$EIm_`0#:LX%M8pthKF$`UI7K$7%UYo4#n!Y_<IXWrVH:oG@J
B[/p3igfH<*c(VhKg7EBdhH''%``GSd'8&30&32NbL)H+A9@$`crCtf'gYH(Z_0D!ilqL?Pl
\D8,K/Zp*;0)&SaUAYk!5!d5;F7cYp[:E"SB>/ZJm85n]Aa&)+qRbC9Y_/8bo^N5sWs4Y-_OQ
!o\Ag8$,7Y+E#9PYDX#'N0:!m8.OJY]A<@m0Q@)Llq+9l"X2j@RitY@TMSn]Am-YJc)]AZlRm^H
/_>JP6GM'>J,/G=dBPlYeAh32&d,4p;geECgI.cm?eo7!YJ?P;P`hY@NTQIat]AN:k`_GmZoa
YF]AAE9WlAR>HsXGn*.bni?=$ZI?<o&^3FUs.'2b_1;T&[dHH!G?HWq5@n06#oilriQ/nT1Bj
Tc!_[r@!/I%,-Epa*Z'OCeK1&D'r4FE,C5ss>&VIqYh]AHZD^q=F%)C.`+TXir5+iba:VK9Ii
bmCjGI:*:3U6F"lOLQ;\HD_/0jW;>*cT90E9q(S\F&n,0:7K`gkh=d#1Te,H"+`Z-3T'n8_8
m.uh1SCD\KD4.=1tfM(L_d/"5G=9$rCArk-^O/4&+kS+ou^*,PX>MV-S@7iuW>Up6>s>b?LY
G-p@tmkmK8rB')9,T@.3RCRYu<:Qjd_Cs&6d$\T)Pp\J$dB$l?rXq5#'k-8*1Dm-2WSeFJ_r
f5ES%kPG6o%id3fYb"$9\o<B0)6-5rHGMZ#*=@r:]A8HdFCiU%`?Ahod^;g/<!7$(j_9L%_ju
i_L52pa,95o6h^lA?eb<`R!NfCeqGraGX`ard_R4(%]A1;8?![H:_gYJ&eJjkVWD(CD(&5FgF
a7Y3A:2/"B1_ffu$2[T^e-]A%a9ZSlLAplMU7-l7sR&="<_llRDn7[<DLj]A8"#/4c1WNLn!Wn
^8DjaQD#,b^q%0,&L"CY/A58kQ@#k8UcWmm'7lm[ci>>6K:srDC*nj2Hg-:;_i=m]ARk]A.Ob`
mkk_07Ls/>D>p*4N<_L0]AQstLa')1[g0el9Vc\q_++g:EJM\i2Ui[+$C[@rI_+s]A7:9#b9UM
\04'`GiQFn9ZN93#eR5:!S"<D%K.gWJNAqMppm.HpG[u?B<^RM&mW0;0mTq9QgcCHF]AZ_\8t
HiMP>^GeM'+RU=NMo)a!#=['@,M)f'/2ebCj)><c\eRkP9$!#]A9_53b4!UGI:heb"4[h_l?[
r6qZYLkp;5DU?a5+ANc*ahQ04d7sEfp_b0)heYO2T=IaO:u]ArCP^!g\_ikQdR2('5S9]AR>ZB
g@`?D]AuSB$fRZ@a'MXA=2QXME\G'e%b4R%<4ad#`OutXQ*mlZVJU%($B58J\gUY9m$]ADD1NT
<-$QWk(ur:b&4Y&r=aq3DAH-8%6e,:[Z7IZ9M\+.?F%*rIV?kAX@<eHr_6BHF$KCiuY`*/n^
EUmJB-BHK#U(-n((k8cm>eE4@1Dd3Q@qN9JmUKR/'[f@6Vs/WrnaFqmKd_fWb&aIBC#e4Mro
V7i3PKsAd@$ufB0omfQW5G=t7q[6.i3HRF1g?d!?;9Uj]A?)hj[\_^n;\HXR@<:jt\HBGkbaT
_3E95bMorS"Prc";n]AP\d>+rJ_<ehQH2#-"KLf5=b):bDe*5TQeoZDbU!"%0=!(\/Is-eF72
<e1MG$0TaEl&\5'9[&#IPH%7YeKCkQ8TOda=s(h9C90o)abXr[LP!GS&u$-+:39%PDc.dNT'
4lTT<-m=GI@!nK,slk(Cj"H0&:e9.CNZ"'"m3?Zsgkh8Rjo%/=7XFbQK:(3jhO'!<L!19u'>
Ds:Ef,u3&<>t>_**ob._YFeXTu!PX@Onp\%CNl6+b8K+#ARZ5=VXJ4h!MU<,#'BJ]A;Wo##e1
N'0hP]AUm="aV[\&]A;+GK]Au;g'n>=\.JO)EqRQ"Dcb>KSCg;7#Z8?eOcBA=3j(YLU<gNE9=S&
-P;K#e5tr,M`oF.O>IDtad/F=#P`TU9m=;K+K>&H64\ZR0,HjU$I1s$$pJgfE=i3`Z7\PTWZ
h!GKO1tMlo(ead9BA`=BY#]AmI9rA5(RGI9F]A1b+]ABF)X<s:(iAS.tL/4`2mUYk:>KmG+]A@;%
\&;6Rf:ifUt=s?C:m>Q`M9OQ%M;%2>6eJunk7mP6L6/1oDSB/]AbQ5+4YX(QJo?bE#:JB+EQ[
>tRU@,#E/[?i+A4aqp=d_?d_Z/=RmoLC^GkUtt)60VK9<pBCi#n91(L--Th_<?Dfl!u;C5't
PMfCV<<[rj0J9H4dN6e;ZR.$7p3\_Oq[[G,u-9F;0KJ_e?"a-/Wco<e"?qJB[kM72nq,Jkj_
MPo>No6GFS#q[e:Tmfq)D+=jZV;=3@_-H2+KV7m;hBr&rB%aqGSE1#^>Ot!/]AX^&?QKn)k!"
>976R6QJVPXD$T<P&gg;a;JHW@cb:`j@:Ws4%Ua47(V*k45#rnU*=789?2U:ZALII)9=J>ue
U-5blSM6oC*9:J.FO,ZO=DX?ZDWsY-ASE&mle!6!)\'YDs=)O^[0`E$fS4#i@l+6!@/BZ>:b
slj=o>*9V'@@D1gdLiMLb:7`N/H1u[UK.kA+?9FHB#Xn`g&:8&0(lQej4ZDr0V*Cj3P3]AH3o
;"6X27]AW0sB6cQ;)+d,Lh.,1.::6!PWGl1[\*QPa;1BS-XdNhL3Ygd4HL^7+1*Q40UjH1E6L
"!`'FR??&Qb+KRnVu(?g%_"J6E.a)U%;B(B5E;^C)9n[ccZpPC:\]Au,D(CsUq<I73gV3V>~
]]></IM>
</FineImage>
</Background>
<Alpha alpha="1.0"/>
</Border>
<Background name="ImageBackground" layout="1">
<FineImage fm="png">
<IM>
<![CDATA[c!1QW'DJredMA*7Coq)V8ZB/.+JGnE+saSG]AndC<>Te&a;,Isg,sW6FL9sht.O%lW86r/<#"
9d]A&5C:s(h=5p5R]A%PO=Z%r6FN5p=4D8Fp[5`@gO&,Do8B$\b^[q7425iK^0eKa\`KXOPK&S
$B=W'%=6mkcHV6>9gmcb?]AP[LWPHlmnoWQ0TYEl7;SUTG\Q"!f9HUhMX7.Jk(#tLR<:0f``2
!2L3ZefqJW^hP!n]A:oT40..tS0jdJRd2$B?%M0']A?X-\GOk>uHdcS?In2fWg*`.V:Mtu'[!0
Q-eDIhhcs9V[B3hlfHtoIUCmt.$jnQ_K[,/%83,fp_pbqaCa"*4qr;EfFS8"Ko66!^r[G9__
@[Fr=>X6d1n)\0Fe?-"hE^YZgG>#eq0f%3irm)aN<J*9g@>=]A"*hhlUi*rS2RB.GBnR@jiod
&A$@t4D+YVe.S\:]ANn&K!V4Dpl#%R>`0?*QEbprEb'Ojdb?*3dlF))GeU7hG<ATfk'&UUrsa
:+[^[&mdrp$LFVOKA'l7&j+$E%ko7i'C+FX8-_$F(]Ak7`kq*+5&et<R#3Y;dh3of9c^-%_/j
'_4*SKanMFL\ORq!NO\X`E,WBu]A6brqKs<XLlb;DWDAoKYV$gLO]A6*%[G^7QX*[Nk1T^1oG%
5fDp+eLK?)rRfSi&nC&!#iM%:UsrROo_PVJ,rKj.<45)Ud`G"L^Wl6NWh3SLAI+4eA4a@>X?
5i]A2c(_Kf/m!i15C89I<E/#Cbhj0nH6nTF,Fn4@?n6N0Hi9Pq_>Tu\tr,$+gkih>VKqP4:I(
I<QT/:j1lY@ub/ed$O9f;P0j8?RX"Z-7iO9'\l2p-L8kVJ'5U*QQObkh.n)VeKGR&B)lp-9Y
ZB21Pb]AiRLNM2G.CHJX=QZIO2=s16X_?)%"M^B7Xb=kDJH_)rT`12I.XEnZHSLmp/,al?7XB
MQ(DK."JA#Mb!<dgO$*Ol$=/i.hJo1Q#>;(#YCRI+MNsr324/-aV=FDs2_EBrR=3iJ")Y7ZX
1XjaV'=X]AtM9Cg8l<E!N[h:D/0lFTIAVlCkN16"YBU$=#M[lh^!+c-^np+Co)m+]A2Hi!_`Ej
6ra7=JC)LG4gnlWf(dLMqnS3@NV%Ir/Zd?tV"S7\;snBORFhCg#p)>r'RDON9lbQW8GsjYVk
_#QQJ,N\N4?uhKeGZ;C_s_]AnTc.m=Z@$0H]AmX&7pO+63cQG=?1PFiNefBhJepBZ:TPIOU]AjH
<+%EHSmCDu@*6fF1oo'q"o`@5irRtt(muP$9F925<#*Gm`]A*lP,R%?V)0$F`^l#@*DrAjL&h
2mDMO<@*(Kt(iOA$6QVd9W80i.>X#VF`]A5!c@b>F(+",=a=cj-(AOtgZmM2!=!A#hJ=tle[i
VI?G`0kX8htMi:pL*pM1)_3*63u$^HcYs'WXhr)b]Aj8FliX=?:Yth)ZB(DRc;"8BO7*TutVF
=tIZl?6A&[+%A#O<]Aj78l6Bu[c6m83UfSsp5Kh$jc[#?8jro!6FX$[Zd_`X=A.s)tPbKJ0,/
;)d%e^WS[!<gGh3>ZHeGoHgf3TgC'lVl?_jhV*b9@jQoX4)&5i='!j"66pLK9b"OR>4^p`$3
i17B%`JKitlQnV.P04bjJ=ltfR0[^E@c/)t!$?bmOKdt(XIH#BQ:f7r0e+M:BP&%uB^m_u-j
e\j\:g)Yi_!i+sXX:u+g-7tr^$BPE=o_3);H=>kZL(DC?m=4YasGI;(3'm@;E*=-Aj=t<Hj8
rb`L.>O(K1Dh'P_(3[ARq(\=<HVh2!1#\Ll5J.31Q4XJs>9<ZZk7Rc<<UDCZ-]A,QSXUQh3'e
B&pIS33-a&)`Sfj3S4D,8,7lJ,)p+E:=3p<p,5/Rm!fkfac^LL!2VlT<+'U:(8XBgTkid%Bi
i&\-!@YDl]A-Zo4W1(Qleb*a)Xl%q3sZ]AmRSOEM^1NJuic1k-%+%GLo&N%[UXBb\TA"jeBp5s
;,MW,F",%$:E>P=S5M#<>F9e,@ZKqWZLn@XV[MbZm9:L]A^iIH(.fG>)*qc"[qo@p,bqfbd8L
LhPh$fle'r;S,McjUZRd';_B`<P-o$oq;C,KnX"NE0<b]A]ABRV8hYGX_,il1,1aD([9j.Jj;-
K=Gi6tg2*<(HSq2Zri*$YPiWI"]A]Aq(0jcK5<[Fo[*^-\qdMZ6uhpENr;/6bL"=OD'RcNuJ1P
QH&2;Wp;2h5^^SWQKF[poVrR4`DnQJ1Xj'X^P2OC7MZ+^?EY[O>?[tB/tt&kT30Ij:e'fq[q
_#6VODU6%L0`a"%^8g3t]A-APQ^qss.'.$O3P`te.)Eu\+Yc`AcLs#'^mB>-QF`1P!^X7;N<\
Q!sp/-"3%(W%Bicoh%S<La!2QInu)&9UKkVR5,S]AoWd\^>/,,EAdSj1l-[ojtLVT)q0LEY2c
o`GlFfL;`m"+8n!Jt]AHHApA#mBg@4UWMYgG>Rt_$[?VW?+>$q^mEI*j:DR#nrV(/AJ3gB1T]A
cJ$pNTpd:O:$FRiSGh-ua$7@IanrND/ZYX=LB";8,:MVWs.oIY=Grf:'q<:PqR@c-?VEQqFX
=fJjs]AthFqROg#>3m)>r8j(Q3%iRENQr:0/FT!e/St7'h;5O19]AWVe=7mb0,2RNM=-X;<Z2-
)<.2)&iURoi'PlWT?[E4`a$)$PdP_&qQeFMJaXeL?bPAD=<:IL3s:@:OuGqku21&DM$ON[7)
N3*G2@7!HgRkcjlroc]A3iPk#;dA)\BupnEsWCoZta`$W3lGp4FkaSl$9E8IZKEEj\Le."-3<
;KX4/IGsAIuPZN>Qb1H+O3&tE$Q!>>hpn?C9)1Q9/HK$eX(nITIWN>ftUP!nq2Dp6[Q28I0-
f"0J@dp-N9X:Z8PfYboXX2$!KJ.[=OP_0c<M/Y?+/RQe^87'Moeg95Q:_Z8)1`K;&$DE5PY<
%uWtlrE4XfD=f>5'X&/O=RcYjPqQDCbQs2>V#BK`WO,kXbgc-cX\Lr'M7Be<;<H96X6JJfR\
5XuA2M":j+;+Q=9=MHqrS92'Cd/D!_!<4BT36M^iEX+1-)FU:[&Jtm67sTVepF[%dsPc+gVU
?WuLIA9L:#<jO9n"BmJtA[N4VZN@5P!r1LE&`;ft;`An)"B^UXYb1:<JW_NY5p%.h%GiNjqH
FZo:c+?NR\ae"]A1-;foe-hpF>5u*Gm>1+,oDT3c^u),pk!WHc-r_$p<nl3E%78"8aPGHO]A'/
<Xq$moPe-Eoe[k7(ZdrDm3/V-Lg=K%]AZQ6G^l#?!8=52h,:h,@)#c4A:>'#4f8o?]As$%DkG!
`.ef_NSDBeJJVZ^m>HpMo[Llt(`\8`8f[%O[pV<eYcO\;G+KF$ASb('5[R-+WlUELC6+>1;:
+JH[pO/Pk6_^89N%aWMCDZ?#Uhp4PL8\4_]A77-FldA(LiXZ8!OKY48h5Q]AL`,JkImjlN\rKM
"MBY":JgGu0TqU)g>em]AKrPa"Aht=`$`kLF=.c,bp;t@-TNRtu2Q*aE'g^en?[GaVpcuWDi\
NLI\1=8\G+Ze;HasTJ/Z0*<:qafMF;l0%-G@1?GL\htdj*!d_WT-6!Yeg<n%]AVp2s5pjTB&5
ikV0@eqVbu@-O:E9hS\@7R`8BAZcsI[PB?Le><@eDc.9CNKP0V0WL$%/7U<%,D.#seu0fK%N
[k7mG[3C==<?obYo7-Y(#)VI5KESnhG6!Oo0eUO/*\_]A,[WHuu#=.(-]An;*J7sU,FCs&C_*Q
#:,UA.7Q!q`:0?VOhX\a2XU0LCo4?l(4S\NB;305g^#b2-=E$FR]A)GA7lnnYUt"-EogXkJLi
Fd%YPb+64)<eR,=/@ck0dH;9lRjFid8S;1-]A8GPs>\/N.:>p@8$Vc*i&JT6;76MN'p?B5F4l
.Tr<"@(E&<DAV<n.GL.7<-(&Z?IXI,8>-nHt%omNdi,"/u,E!<-@Sk+L#ufIW9IcB+dUp:VN
W:7O'b-$;JP5!jq=o?P:eD5b7MGB+Y+=?DYD.4`RrB#uYZnKr!FrAE!'`qHOu()P0-2)9^#,
KL"n.V>=W_/YIE+Cg[gP!5Tq02)$\d)DTRXIukQ%0/h<]ABf@7:NbJCno26DSBYP%TI!3>Q1B
9AF'9o3HDk0Qlgt4.s)em?6l5N?"&BA`o;,NIc7;(M57B4JT:*F^[E;"(LABU)kUa&%CFu+)
tS!4X)A2\+8Wfs[/!V.F-O4*'f*A&,T_!BJ=H2;i(IArMU?nIQk+g!*>&r.fHP#%`ZK=0P4g
P=;K#Y]AE<cc"/b&3'Mr(.K,c@lSj!e`nTB#C<q_Pgr>T%$%<3^(&..L/"Sd)mClTDchT,A/]A
cj@rh_k!";S!EneBbQOtX-CD2ut:%1W]A?741pWK';'H)gC#_baLb7QX.'W.T5q-e]Abo0&EP+
L-1_-BKac)Se7sI.KDZm#,6`N^HIad,efOnk#677Yc/b<\4_t$-"`49Ql9O<!Li.6D1mD_[4
#KOAO?i'Ir$IhGG?1k`@K&CoC7#I/[Q`"#jTknN5NhL*4SXbCUFEM#U[GKITs-_$rPuJ4:Vs
%L-S"tI>VO,VBNHme;KeYp<_&e86CKS?hR1-;sXZJqCkgm_/L2Mlp:(0``A`bgB6_C-`0ioO
lWt#+:t"$SSu7KgPUK)?]AA.)4En9i0*dejX;sh?[+8q]A^Y)!F,(O?A)9m9c>k99naSTZLq<I
N`6,o<,=R&Jk'qMpgH]A_KPQ1UmEeVs;HR2?@;,VT.!]AT%/Kii5Hu#ZdJ!/.!8D.a:(mcV(Ds
8+7VSmNh&Cm?0\9htm`H#]A?l/'##t=3a)`.[E6-WWk*(kN/nOnji\hgW@U-s5Qh;=>>eLgTR
g#Fkb(`[om9[e&?i?Ok^&lDhTG;k*WpE5^j"">g;UK-97#qe#[j%N;Ip=u+pS-Rr]A5-\?=1;
V7R^/S#Ccr%D"Pm'Kd]As4mk:.=<Nt.:^!lAm'mJ;qH,Oql#GK'D6._r+rN5K"!.f_ZC^o=,e
luFV^g4k$r=7J:-KANaM&R/El&r1J*2/Z4UO?kkl[Z)8%1a?u67C`F[G+1E2V]A4l5U^UGMQ,
psmi^'ejP;-L'@eO=,,?j?Q/41)2$=go!_06'VLA@uDb)g?Rc).J`c2^^Ao,l:GKL+-qYom<
2;L[QBVB>*a@r:uVJKV$cl<4k\VZs89\3CH;=*&!-[2;&k(mtCf@D/$/=?<Sb$^ZF:siSH'G
-WQ0t-t!HXPH1=m%'cZ:0O&5_EOH=N>n0n`]A>k6T&_&(<Y"-^B`IS<Y';IR*t:->n1urV&<(
]A_&7b3`hsS,UOOqD_V`f>@]An'a`$$%UF/[+:2rS.FL*2+^b`+k+*,&AeG[<6).>ZWVH*4JQp
CLHE9k1PcEecaB@#`<B)LeO4Du>q+n)$Jb3d*9KAW-S09=&c7U$l'pP!F[QhpD#pFJW+tE`Y
'!X2[`0Zs<ijG\@[!&b>.p&^WO2S'4Bi%gtfui9kNuW;@f=FKKhp(1h?:0M5IE<K9$)(^Nmh
8.iR+[kWKdp@/(_74&B]A$;FMjr*Q5B,R\`'_SDfnTH6(C0U!*3B2ZQC*C?SZ:Ffe9\AbQu"e
gVcqS9R4pfNdIO%#F)dH806o0$XTZ<s"b/D2>p^qE7US4[opK8J7j27pqZd?SBIY1OjSYdpV
L8i&Z4"DFU(b?MkWC#c(Ucu@!c@!0HZSl4:L$3o.;!"O>]ArgprIST_rJN84bVAUu9;)&g_ih
K<_\]AaZNTr4LfV(t3nHkTVDR!>LpJ(H;549D+_%l!m`NN(*0HKh)Zr>3qG2nU=1CjEf3[iZO
;gdBhc(Ot!6Lc>^10EYmWY*2E\dYn%qta^Ud.O6V>'YEK:P/mHR0EYrn?P*]AIaErt6=/3"B.
eQ*P5nj+Bl1?^HVCgn5WS(^o/*(I.Z\TIgppV*V[<5u)c*&KPF,.2T*5=>hUr:ZHj3YK_\H@
2)=*Yuae)bp""L,K]AAr)#5I*Ad^K?S_j#b`BhTYC.VGFuG"2,pbq\6K]A--#bJ,p%MhADV1+<
odr>35&8P-'eq:+OR?@0rP7UXO=N9qs]AP-"77Z)h%WXBqT^Ip@[4]AdY#Z9Wgm/,e@>5j%YH@
_/_T[GJ376<FPP&'PA+Z&rW##d"O$3.`AcjY-W=[D=+NIFiKE:s&$dl&/'h:Q.F2$_/@;41l
2do*F'uB`-l2!6Abm:td5F>(KD;&e:P]AL0h=3#Fs54Ks\[l%YXiDQ(c*!_'Fd'MJ=R[W'X"L
qp"gWB^G=f\Ar8&PQi^(jui*m>k-:Ok"DVJ-RDij4dXK6%G@6;S=`Kt-!j5>Nh1;RRp]AhL65
X>i,t\!QLXL1@i4_eCXm4LW=N]A(\Vu2d-mMeg<%g-lbE(THOg"LjqP('&%nb'SeIVhWi*is:
nB^8;\55%4>rZ'B2.TQ>n.*d::5pB;@AKHqO'GhZ=eYHJ.#Z43.WR`_ul3f<Vo(bR:j0YGV>
,Wmar8(fdlp2J/D5mGB`><s,BkNZ[\s,a_(!OZ\Y>iWYJ?:"-OaHOglW)FpkZ64r!*g<N2&+
=W&Pm&7WV!fJ$`rK#D8WY!K^l(>!0.%)Hqi,Y,Uq&5'355Da<7?40to:AQFqQI<U"tp^VNce
Hs._ogoMOEpWH]Ark._od6fM.m,bd[GXMQC75Z""[9"7`;(.@2QHKZ+_;L$jEcWlf\,&[^t;X
)oNF/W':,%!S1hdU,GlNA)fq\?0PF>m4(BCa:"X1/_S[5IX!SsU6lfc1,^o14;2d3XU$D4:0
r__`?WihdZT/EAkI?6!)^"%@.lXtOXVQ>L<<Dp`hE!SkqZ`OcDF[LBPhHjVDSBq=gf0)6CmA
t,.DFDO[t%=5[i$E-O1/$h\<d$aqED#tYBYA<.R*T0ba#B'6o^EtS_jZTfhS#CD""sd'=p?T
?FP+[>Y4d1;M*cG0gI0:35q'AA\?E-g6m30a+]AoXafaS(4L!GU!Y4I_Bb&,qPf"!jQWc6\@*
8h.\R+i?c87b2h0*m_5b1BK\]Ao7Nih)E:Xe;KOF[U0;f\q2O=j\)Kito%b$.2N6[f!3/Q>BG
:E"6+aRpVq50rLM#cljs&T4Je=?nC-Obd[MM!+-n),HeQ.fO%IR@9_""f;I%)a4II[<h(3@j
,W[gQU$5-fS<;2DcV]A]A*$\jb[dZ.g%S\7$2INPC7lQ391_@P&$DL!)Vu(A>;@1rjDC6=p+r.
8aA<C&i^`+*u$?fA(jE+$LP#(48/2TIGPD3kN\BQDQZN+`tBMj!M^gD9@EX(C@NGe6qrTSh:
k+SSYhCBgo5h"&h<f1@2u,PE%aD@hd&.pKdZ@*1ZP!544u6=Oeo&eNC7Fj;O-aLc8m`Sb:E]A
GW>p?YJmpg96H.1I%I2QCEJQ_fPMB*_o9'd:,<'tgC`nL+CotBNum2&<gTI_!#B"_%HQFM%e
mpP>qhkEa<!d8%h.I0RE^+]ATrn1$WpknDX8?n@I[.@l\#K^$Ye)s9BR3bQ>RRrcLA;A!O:H.
RCe$YO>18pq!@N`=a`\GC7.ik,U21l;qolB-%A\qsb$nB0^_5&H7:a-H<19fO/X`8b3VC6]AK
t'q5+6=!aa7:kq@h!X*%#olorka>]Am3^GBi5<@2P$=sD4'E,h60&7\fFmW4<#ed6'9Hb%n$<
i&U>Fu?LWHiVPY`cnYF#4m7keDWB*O1u"/8X_#oq`7=MNi>AM;(2)DXC!qFtQ4V=4LIcC/hF
jpHd`8gtDu$o>qYOhm7rk"uoO>JM2]A(lg2%;nB7*\DjqVfDBM^@(e9lqoCJp%2t$<lAh4IA,
bIh!m,gg=H!V;JSAjASZZi-_)r%PF-u4g7'62;XD,AuP2h>#Y0hfrNB`#gY3VTf='#[T''[V
1dheX1SY!lE%?<*5,P:cS-8)cJnFO-^_8cQn]Aa=h=^^<3*?.+.RF^4OONX=Ejkf7DIf`uu3I
uiDo%VliCcT+t(Gnq1JS'CR$EIm_`0#:LX%M8pthKF$`UI7K$7%UYo4#n!Y_<IXWrVH:oG@J
B[/p3igfH<*c(VhKg7EBdhH''%``GSd'8&30&32NbL)H+A9@$`crCtf'gYH(Z_0D!ilqL?Pl
\D8,K/Zp*;0)&SaUAYk!5!d5;F7cYp[:E"SB>/ZJm85n]Aa&)+qRbC9Y_/8bo^N5sWs4Y-_OQ
!o\Ag8$,7Y+E#9PYDX#'N0:!m8.OJY]A<@m0Q@)Llq+9l"X2j@RitY@TMSn]Am-YJc)]AZlRm^H
/_>JP6GM'>J,/G=dBPlYeAh32&d,4p;geECgI.cm?eo7!YJ?P;P`hY@NTQIat]AN:k`_GmZoa
YF]AAE9WlAR>HsXGn*.bni?=$ZI?<o&^3FUs.'2b_1;T&[dHH!G?HWq5@n06#oilriQ/nT1Bj
Tc!_[r@!/I%,-Epa*Z'OCeK1&D'r4FE,C5ss>&VIqYh]AHZD^q=F%)C.`+TXir5+iba:VK9Ii
bmCjGI:*:3U6F"lOLQ;\HD_/0jW;>*cT90E9q(S\F&n,0:7K`gkh=d#1Te,H"+`Z-3T'n8_8
m.uh1SCD\KD4.=1tfM(L_d/"5G=9$rCArk-^O/4&+kS+ou^*,PX>MV-S@7iuW>Up6>s>b?LY
G-p@tmkmK8rB')9,T@.3RCRYu<:Qjd_Cs&6d$\T)Pp\J$dB$l?rXq5#'k-8*1Dm-2WSeFJ_r
f5ES%kPG6o%id3fYb"$9\o<B0)6-5rHGMZ#*=@r:]A8HdFCiU%`?Ahod^;g/<!7$(j_9L%_ju
i_L52pa,95o6h^lA?eb<`R!NfCeqGraGX`ard_R4(%]A1;8?![H:_gYJ&eJjkVWD(CD(&5FgF
a7Y3A:2/"B1_ffu$2[T^e-]A%a9ZSlLAplMU7-l7sR&="<_llRDn7[<DLj]A8"#/4c1WNLn!Wn
^8DjaQD#,b^q%0,&L"CY/A58kQ@#k8UcWmm'7lm[ci>>6K:srDC*nj2Hg-:;_i=m]ARk]A.Ob`
mkk_07Ls/>D>p*4N<_L0]AQstLa')1[g0el9Vc\q_++g:EJM\i2Ui[+$C[@rI_+s]A7:9#b9UM
\04'`GiQFn9ZN93#eR5:!S"<D%K.gWJNAqMppm.HpG[u?B<^RM&mW0;0mTq9QgcCHF]AZ_\8t
HiMP>^GeM'+RU=NMo)a!#=['@,M)f'/2ebCj)><c\eRkP9$!#]A9_53b4!UGI:heb"4[h_l?[
r6qZYLkp;5DU?a5+ANc*ahQ04d7sEfp_b0)heYO2T=IaO:u]ArCP^!g\_ikQdR2('5S9]AR>ZB
g@`?D]AuSB$fRZ@a'MXA=2QXME\G'e%b4R%<4ad#`OutXQ*mlZVJU%($B58J\gUY9m$]ADD1NT
<-$QWk(ur:b&4Y&r=aq3DAH-8%6e,:[Z7IZ9M\+.?F%*rIV?kAX@<eHr_6BHF$KCiuY`*/n^
EUmJB-BHK#U(-n((k8cm>eE4@1Dd3Q@qN9JmUKR/'[f@6Vs/WrnaFqmKd_fWb&aIBC#e4Mro
V7i3PKsAd@$ufB0omfQW5G=t7q[6.i3HRF1g?d!?;9Uj]A?)hj[\_^n;\HXR@<:jt\HBGkbaT
_3E95bMorS"Prc";n]AP\d>+rJ_<ehQH2#-"KLf5=b):bDe*5TQeoZDbU!"%0=!(\/Is-eF72
<e1MG$0TaEl&\5'9[&#IPH%7YeKCkQ8TOda=s(h9C90o)abXr[LP!GS&u$-+:39%PDc.dNT'
4lTT<-m=GI@!nK,slk(Cj"H0&:e9.CNZ"'"m3?Zsgkh8Rjo%/=7XFbQK:(3jhO'!<L!19u'>
Ds:Ef,u3&<>t>_**ob._YFeXTu!PX@Onp\%CNl6+b8K+#ARZ5=VXJ4h!MU<,#'BJ]A;Wo##e1
N'0hP]AUm="aV[\&]A;+GK]Au;g'n>=\.JO)EqRQ"Dcb>KSCg;7#Z8?eOcBA=3j(YLU<gNE9=S&
-P;K#e5tr,M`oF.O>IDtad/F=#P`TU9m=;K+K>&H64\ZR0,HjU$I1s$$pJgfE=i3`Z7\PTWZ
h!GKO1tMlo(ead9BA`=BY#]AmI9rA5(RGI9F]A1b+]ABF)X<s:(iAS.tL/4`2mUYk:>KmG+]A@;%
\&;6Rf:ifUt=s?C:m>Q`M9OQ%M;%2>6eJunk7mP6L6/1oDSB/]AbQ5+4YX(QJo?bE#:JB+EQ[
>tRU@,#E/[?i+A4aqp=d_?d_Z/=RmoLC^GkUtt)60VK9<pBCi#n91(L--Th_<?Dfl!u;C5't
PMfCV<<[rj0J9H4dN6e;ZR.$7p3\_Oq[[G,u-9F;0KJ_e?"a-/Wco<e"?qJB[kM72nq,Jkj_
MPo>No6GFS#q[e:Tmfq)D+=jZV;=3@_-H2+KV7m;hBr&rB%aqGSE1#^>Ot!/]AX^&?QKn)k!"
>976R6QJVPXD$T<P&gg;a;JHW@cb:`j@:Ws4%Ua47(V*k45#rnU*=789?2U:ZALII)9=J>ue
U-5blSM6oC*9:J.FO,ZO=DX?ZDWsY-ASE&mle!6!)\'YDs=)O^[0`E$fS4#i@l+6!@/BZ>:b
slj=o>*9V'@@D1gdLiMLb:7`N/H1u[UK.kA+?9FHB#Xn`g&:8&0(lQej4ZDr0V*Cj3P3]AH3o
;"6X27]AW0sB6cQ;)+d,Lh.,1.::6!PWGl1[\*QPa;1BS-XdNhL3Ygd4HL^7+1*Q40UjH1E6L
"!`'FR??&Qb+KRnVu(?g%_"J6E.a)U%;B(B5E;^C)9n[ccZpPC:\]Au,D(CsUq<I73gV3V>~
]]></IM>
</FineImage>
</Background>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
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
<![CDATA[723900,838200,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,6604000,2743200,6680200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O>
<![CDATA[切换报表一]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters/>
<TargetFrame>
<![CDATA[_self]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/城市费效情况1.frm]]></ReportletName>
<Attr>
<DialogAttr class="com.fr.js.ReportletHyperlinkDialogAttr">
<O>
<![CDATA[]]></O>
<Location center="true"/>
</DialogAttr>
</Attr>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="2" r="1" s="0">
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="1" s="2">
<O>
<![CDATA[切换报表二]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters/>
<TargetFrame>
<![CDATA[_self]]></TargetFrame>
<Features width="600" height="400"/>
<ReportletName showPI="true">
<![CDATA[/城市费效情况2.frm]]></ReportletName>
<Attr>
<DialogAttr class="com.fr.js.ReportletHyperlinkDialogAttr">
<O>
<![CDATA[]]></O>
<Location center="true"/>
</DialogAttr>
</Attr>
</JavaScript>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="4" r="1">
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
<FRFont name="SimSun" style="0" size="72" foreground="-1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="等线" style="1" size="128" foreground="-16776961" underline="1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="等线" style="1" size="128" foreground="-1" underline="1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=@.;eNPq+]ArK[0Lr%K.M/5mA!q&/7`>adgcBO(j7/;@G"Nd>BGlS/'IlBkJR!;/A55WdD6
$OE,TK=U@j+hT"sHAVVF:WoK.%N<A13cb84Y!4KO"9)@7I:=pW#;!>0lfu%t;V!/o.S5^#-H
Qp[OkoDIh"@DS^B*"O19MX#JmiTKk47"oRW<B<_D]Ap=4St+m@OqCYsC8SZDm&pP-=4EC]A-\q
5qb#Iid=2I$QS_XdIm&\'E[(8g)1PEVRS@k*:<,\TOkd2EKM[28$AJeLU9,q+IN`B@DrBeu&
.?SLA.+X['qRj)MgZ7IE'_8KVb%0UTL,l;e?8W?1NalWbku6/Ne8@CMgLBg^<2,e",gUQ,_W
dUJR,*sj"VZGA%3Rg%%D8h`&gBeIE>)eueJ`.m)?&VQ$#h@;<sA-=4@*A"kVi',Nnog?1Wqr
V]A;kQ@H[M3(#T?K@EFUBoC1o$\_,e%g5fnrY)kl`;L:F`iU5+G5G\YTc--Za8H1Q^r^C/!E(
1hOB/(K&fg0>Pi"C2nG9IIofYl'e>2]AG)`,44MIJm(d9-Ah:aCeM2mim2qT*-jWi%*Z``$)(
tm07GIB\TAC."eSM*lur^a"if=<DFXbsCZ:A;eW\e/$[)(I8]A@odlW_j`eTIub_I!MUBYAo2
^/.6Q)O=l`\-FSs7$gSd+bB%(CK>(`cT=b"&omZMktV9q6D38<7^7]Ajb5R-[,!2U^`4Tg>N?
0p6"=_P%;(2agB<GELe`4Xo(42;bs`nbfU6C"UkK?;RM:e,N,"p3*Fi"rcYdJ&["$+1!,ZkE
!<g7!kZZaZ)@sfD#;\>2A8H"MGOQ+!`L@\dc9'jH9(AMXF5D3'%TaQScY:>I'Kq^4?^]A3i'K
Mlp;A!PYJI)kZC`ESF(3on+;)+d8@L?lGU8P&U8cEGdFD*8T0#7:K5:EWRE$)<s5]AtRNR'+"
:_US;O"1sS"kE2N=_Rg2G#-VL:MX]AJ!i.CB1F^,Z"5c5J158G8!%AVpE2Q,!/oG9n#$g/mX[
]An<AAOG]AB*I1p$0Y%),-<07t+tP(9(Afj=^+bJ]A?a&ek94*4"'B-$dUYMPP!sSaS%*9OC"o\
:<o6fg$:dV3%N"Q6#L%cVMMkgUJQ8.U1V,@S2CkP\]A21%Jo6&l8qt7?=VrFK^#WQVCAI(lMH
NdBRnI_N&=6aA1i=(o_Ujp["_\6XDk8&p,`>:4`!PkkOl)=cq5.;HU4RG<o:<;/[$$D?HL:e
#dr93Hl8[!fBrUA*Yt5.;V5W?Y40Nr>R5*QVeo"1faCo@'=MHQPXMh@N_;H4<%l4t9'i%([-
<=/BmnVVq`N"Tn/X6l)1VK->:1RT#iH+,+@iTBb3)nZH4..@]A[l]ALcARSe>YEr8/@ptkmhNp
A0?TtnX0kkI7C+p!h5g%l3V3EY3C<#s4d?dFSMaKiB;H@>@9<oo-&O.FRZM"7A9jd`s`lf2B
9Gn!S-)''(0)t(1`5s)SWX$[D`lIJ@B,.PZ4,pjUF3b\%mO%Tk#@]Adig9uDqN7PH9PoB&G?e
f(E&!dT`IA:+#3-NiZqX-$)D!O%G8L<QB2ipYWf6m=N4*:!:mZOkX+#SERfe2;;N:[`?@hX9
PD%qJ<F.%M!WaD/k3E6#R7b'X0r$r!?4-ps;h!J?iUjMi!j+^_n%"6KVA+]AGN8br#O4#6ErZ
,q9bgDL1.<YEj7d9.*fWO)ICRnh=S=4)X$9>\B"J.12=(ZI;"n]AfM+AYpO;hB.3hY9b6R<Dn
OR%\7:9IV.+Ni#H!>pu[?O0BOK2M'6tf'okKF#V:"TE3(ZlSPE^-s-]A7Jm6$4-drXVo7phJF
BR^aTg1=F43hE?<%1P14=+e+N@W12GC2/H\pt);c:SuKMaKci\\fL"Z4pgRa't/6sAurk#\B
u_o;PC3ZpOqY1p,l=r<Ri+4_sQ!3_K+s_GcO^)\2cA?kFA8S(@7lr"ME-rY%&i#926/T8nI3
aF^=grLOu?D9*(<ec*]A<o8OBX-p-?n3oEu$c-#@E""nL`Ud!nDWgZ?4G":bOb,reAPi+k4=L
.N"ehX5RO4O6r2lEZ3A0EgFHE'[go^5V7N2`b5Md1@ns4bpIi:_G'uHd27%I&GYgEujLU+GY
"a#rJTMJ#9R%N)lW(&s#$l^AQscEjP:'SA9;i\n\4+^br9VctZ<PQ+IHf$$6.4"'):@GnFbc
WQo,*E`-98Zu7$)HI(_k/;ssFB'.']AN?+iF*)H4\+j6CZ,^V2mF^ED_Y-P1==pBaGZ=jO?l?
Qo,:&&AX$,_*jpPsH*F>N(V+*D5B)61QT$N!KP)nG>BcX`E0Cg.a&?t32^`6!cp,WD+4Ot_K
Up;/Sp]A`+oG,8LLKF`E$()0?O&6g"B"PJHC[b+st;CV<n#'mK6q`Ao"bLWDl#Dkjsf`7V#<f
Y39qm%a1.H_?:/UVN]A'Z/JlJ>ZFb_YV_.=k\3-$F,ZXWgE9U=T%-3X&%UKUg-j4\@p;l[D4o
8s;fgr,a\b,f_bLQM)@KaX,cDbiERnj,c'a46LG3jW_<4RS"Qb,ZVXEhH1KiAS0F;oeZg+.J
i)m*G4[U!A2L">V:,kJ@di3;(Vrg6l9\PX*MujAtZ[I9s)M8eDRVIPT.sp&7DIco\#K^PI%7
UXQ6r7l0a.WuHoh@"boca39^-)fa,_)bBFb.$a1._\F+=0]AdkGdk6&lNVS?LDTmVMk6H#@UT
V8\O&jDCFMS5#,M/:rA]AKKU$J;IY(b1\e)0'Ia`%:ZAk(+bn5+Z<nq"K7Gf."eQQ:nc<70?T
&SJT*E%o=-]AX;n]ACD'+a$rX4CDB^a+Y3?83ORo3)D'eTYCPlAk)8"9aEBCLHPs_#\n8-bN/$
H7Y&ME3gL)M"7.[5FGtbsHqH$Sg$_@m,=<YB8@ASBKV#'UXk9_*Mp0Z?s+fBp6H[JaCgRHSf
4gq/Ej5gVRG*fZgH`iqK#=jn:muLgA]Ai'SY`dj8]AL@Z%531%Ht,?0Y*r^qcpA(Li^;X%$P`#
P77=A`8>R:2?($d+NE32KMZ0NMn?c60`QA#G#L0mHZJd:o9eL1=ZV6FNoF&g!T9K07YujZ)V
0=^eu7K87Q:e@i2e4a8he[n5IfXqH0)huqe?j_JC5_qMkY'aTu^n0Gop2FjBL+]AF`H.$.VE+
&2E^hQo"&5.;f,,sN7ma!PAekG?Y54DqWnAC*)R0"p:$-:X*W06aG1Ql/iol,+XTV,$7Fp-g
/5DVRC3^A0P!.>`,I%/j&?l)3dq$mCIo8t052qc.YP^*neS09EJMMck)YTqBK"H&S\9BE9NY
7:_!#$f?o2ri3KUQSecpObkQ8pXB[)fu>e]AB`#pG0+n.aV:@StEDJrbHIWgUAamO\CPn_]A7Y
%oPqnSDO*W_uZ[uAblLS;39\mN':HBZm]A]ApOCB"Mtg\@C&L"JT.3R5)H7qcZ+Lin]AEb?0$`p
R4<Rc$cG6Hr)_%s`Y!5W<b!158K3Nf>i%>T^gZs?X4e>>4MM[dQ1e.Z@6Qc\D@^G@NC\kO<A
&5.nSC]AfgM5d"/4['&_ZicD,O+5+g>V?OD'5SA12iM^%%'S:,T`)p[PiGCs.KMSVS3-Vcmjd
n:6P3+C,?*4R(*rMlY\qFY`**Zn?E=1(W@W6BKdCg2;Li.+-@R-R$`Ni.RXk7$Cm2itRaYc9
ZOC?FPQ"SH`JT13R[@E7%sLfJ2XLU.#sUh!@DG]ANB$e)"Y91?6V<3]A2JPUY<5hiEM$2V#Dp[
7MX+IG'k+UTJ#`C0bQ#Du8t=8U<GTbdr$C#1Ed:L9J4)`Z/gs"rQinH\(HP>&p>lN"o[W`b#
aZliHLm1D*q#tV1+-Yb*!!s@WjR1Sg7G0C(#Chj5j0E87;a=BXFN,-23B/l+_>Qcoc-]APgIj
8,,!MA8<'nX?n2&Wp-bP#:Z71qu]A[ouj\hIX<(F3[c(YF6d'j^`'`4@bW=g-fY>paD*m:^_Z
CT<cI7,g#2f:;feUi>F%#u21/K%4RB+jLsND)dK8n!T)\CcTl_\V0YcY>8;9U)TO0J_mNX`9
33rm2^'@adebR@ri9u>?g-7c?Y#O(Uh+'D76;6+ZVV(8<e`B.V_eWP%.9'!eqgP&b9r5hj":
Cq]AnJ30<Z"A&r+IEd2Bbo7T3hUfWPPaFj0ePR.gU%it=FF5GS7JuhoftN_rVZ`oNB16?d;*q
"[,WHp.lp5h1-UQ#1`O?)f<M`BP^O_p=A8D65hZ\m4]ANK[q8]A%n\Hl<Gr=Bt]A_$aVhZUi^TR
##)UGb/!WN(isSr34uk/m^$Xl38^`.c;UOKHdFE0jGV)-^.LkJ>_JW<f?%i4kJ)G39@m57*0
+ZrH,rm++7n@'Y4CgX;2^uRQ<_;o@^d.GZI\M::LrSMV\WrNQ"b&o0AIKC'<+ICImH(aqT/m
B?b'E^ho)M*Dp/HeD<BD6<H!rPhj+BZ5L"hANU<+38N?]AS#8Y@WM-#!&0)2:jm'PXeb0S(n3
F&qROMSpdfHM$_9&Rk/Gq-0hj#rDk'1t?P#G;MH=hbG3I]A\-B-;mn,9K]AI%^bSrG:a3U:OXN
RgG;3adO'V7']A&am;"Cq+4t6piC<rZ8<?km.3@9GY1&bHP1di!6'D*@DB\k*"*E?P-*]AAJ.$
7dAMi58V.:l4WRWpSE)'!b)efT9!cqb`^D&(@J'"B5hlF@'r.fEnN+@9KJ3S-5K%\!BF<VeB
;%UuLf_?rsG!%IldGG5OG;qB5T*0F\*6##_N.Gh5V_#IZO$M4Emdg!c>Z)cJ!D1s\Rq/3@DM
Z:]AqX:sfd7'Fj_]A2FOn,J@WIrNLlZ^#;e\8j3?ojgb*6>!\qP*pnn:?qQjYmpkiY8!@Yl>=,
lP@b0/7srQun&j)_Gp;Y'Z;og(JsOJp#:?(^rKTSE&*R$(9Cg#HKH30lA<=@&O0Jii1ZXa>B
&:LDKD@6<'NV,t:22-UpX/+(.;VX\qSl!db_$>q^D\*#N!RA=u8W36Zc"^hfji<q^#ols[-P
Ked/P7ft<HjS[Pk^Mj;l$HoigfrgNXc_iPSEEXK-V\U5V1eA4!nSmj@i$#IG#(3tIo9>c;6u
W`e,iWNR2t:J=CH.nTp;:cJn+pu"P8fRi:QLZHJQsL[&L2WXW9&;@FK8>+5;()(H0?CM=*Kf
Jo5$kFcn=qYOXc9A[srl^#LgAV,TBNU\m8eWXHSLh7[E-SUGbp8AjWUM#%HM%&B-Gp\tQrJM
(RO%;8KrJ$.YbiV*NO@ldm8c4QBG/kCH_0!&A/:SADmpdTeBpJ8M#.#=4$ruJ&OitPAO&1Xd
Pgj2VC'`<85)X;L+-NhhfRf<!DS?H>u7k:!b-q+t;<(oN,?geBblDnr!e_h4@.S/"i1Vg]A[+
M!_pM#f<L)4).N:.+@[+NC,`S%oR[7oeZua%"h^D`!7@6fdW[e:`?l5k/FCD_:7,DZ]AZ2P8Y
S-n#HLW@Y,pb9uXITaHea>VZ6d[CYVjqAf=l<4LQXucpbQZMXHcW)sWHlABjISd,;fS?9pJ"
S..O3I.UFV&/-oYpd]A#2R:*YW&16gR!V)F!0MQQ7URZ&AT"_r/a*%hk,&S!mP@Z5:XG>-I/!
)Y'`p5B&53#MMlh+Y_)?14/1<EC's8E<O]A7tjAC[@R5'DVKJLGMjloI60n*O[aS5S&+Q&=XT
r$(iger=R^^B^8QqN,(s01GYl$0#\`L8.8^(Q[,(0e*fJ]A<O:t469kabh2Tf_HVko86q[a8N
7G(t245X:8=oXUd_<Td)@ZrM0gqU@X^_-?I$CD2SXp-`FYo7-0g9/%WWPIBkp:'[ZTZef?ia
H$X=fJDecm#n]A[;]ARW=f$3Du-&_no5RYNt*qKKaT4:hADl#cs*^+Zb]AFNC4_lI4@\*?omV_0
MXKGT+4con+LD@+=eIOme-.m?S)Sn>-c=AL<]A9`D,2nHkXr=#=+:9(1U<HtqG5O^4Vc^sGG2
H50q0-c9J(L5+nAI6e[(UJMn%'p1*&kD#T^Xg?"]AL[j5X87biCp>#j8i]A>Fr53U:^MZQ-*DZ
KgaiG3,9jh8p1thJpJ(e$IXFc<<!8fI'Bl5dG?+ZMFB+G(&QU':J.JFLrduSgfE;':Ijr1e)
?_^@AI"&gAV"s'cJMp]Al:"Xr_/F+-mJk;KFUiZLJV.<n17KM;V<)W354q?uI)-kQId=s[NNZ
USo:bmW+B=G]A6Ku3,9mZ5Y35h"IR(6>_^ZU0u6D[2.2S,J#0&W`YaQ*kS+WCd[iPB0mXp7eS
ej.`$a&$s(kOa`1J[AWHI2i>68E)CWZSkSB]Ai$<*`&383gi(`PS/-F?JR:EDh%hUY*l.B5N(
ak2h1([oa[Np*b/olm+P`e+^]A!2aUT_]An.,UAc,PNE`l^WdDSI=5^Y2V>;egKd1i4q*CW_+Y
Ed.^$TGL0:57HQcDSGGCQ0h?2l82n#Nb4jpR27UAu$GL[cpE:q7*<1l_b#A?Z>P(\P$mdMi@
&Y?M/mu=35LP+3L'*96"ZfjeZHa8'9/5T+SWD#;T\)lJc5CnS%-^26JC'J;IBFf9p[`dsP77
b1TZkcc2;;]AM')<Op.c?c,*.^nSDh%pA"T?:p3-tXfliK.BG+'Bs#o=!p0^pTl.U\@2:X\9K
b@/4$oC0PrgI,j_XGsG-R`cbOh;<8\+#P38"l5Zs!CDdeZVIYa%t[I^3e%3"NCOgA<1qQ2Jc
]AI+-p8hAEdSMDJtgkHmfVN'_hd-fmU<eSQ`)=#VORYb10f$VMh<`YYfeE$.dr]Ab]A+'1Aa"bp
AV/L3@PSPbWa=XOc$<Rr;Qo,3F<>1.L4g)WP1P<K>mu13-bV8<=\/Y.2bV>^+2q:"5^&;YT-
:^W#DqD;or3h^f@K4pX@cJD=5MrpL^B"~
]]></IM>
<ReportFitAttr fitStateInPC="3" fitFont="false"/>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
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
<![CDATA[2743200,6604000,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="1">
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="0">
<O>
<![CDATA[主力项目完成情况]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1">
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
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m99rt;cgOo+k3l"P<N#q<(Q5_lE"]AXBsfG]A8CFOKj%uT>Zt0pt[^6KE+\J\pKKRs@905=Hoj
4ln#d%"Y!_*QL#U*A?n-2FFdQe@q]ATNOD+UJH&O)s`G^#K3<maBX*Y$/qNXKtYsZN'mln,N+
Us8U@\YH<eU3W84,&-U6_rKidf5Z"*jJc\,cGT(bgiTEFs#sg)&X^:hGRi-LKmPUuS`r--L+
,O;I>;b2^0%UZGm4!<'.I>5B]ANiF510VsdT(_[n<rt]A>qa8)H5#U,*7AD0Yqf,JTrgQ?dD9h
Z(B<a&r*EF<20:M/_.&''Jjs5t7g+gH_rAngt67D;UU&CH?j^qXS1(S@T:c!G>U%"BT&hUA&
[m9o!ka=$)-*(R_jQl1T[[,G')T/5YM*3LR?J4>k=aKI!G!QDuLYge1D<i=/NQqImqL/29+R
s@I^6aT*,Z6,;1E3OT[stn.>!unk5IbOUD%)ZB/6Il'duf)sO%Uea_Bi3)H,h1CE/g3/%ae.
:5#r@XUf.0N3:dNhRr+t8='SdoUm+r(]AIM)7*IjNe,]AB[Uh=Ap4Xh2dH21D1d"Y[ta?a$S%)
+uj3fo@jXWBM#IHhs+h/RELdqO]AZgY*[6tG<DE-Yi@EDrO.@kmip.=hD"/oZ^hnH'D%;V[Ya
i%'%"=*:M%S%Z+e&]A+!+`#J)u@?(8mT90iJ7H3]A!YBT\\>(7f#O%Gkk`B_nfb^N4YE>YulHA
Yn):?9pmT0.>m/Mft?AZX^>Em1?"4MLZ1g1%MJYF:%`!P69&ED>(Yt><+"eDfnB7*IdpdsgT
Q8jKo=rG.uP&81su*Lj)()0Nm.qE'7J^[15<*>V'5h!PcaY/LRL(RpgVAB1%MaBaiD$"Gp_1
m?=(D?lsatW2CR@[W*4;l">(#2;:sq`TO=D@VTM4UgIg>[,'R"0C^u(#Q3e$3dV9k"MF#RmN
omdTWk:emK*UhodQ1W2F/X<GemYoW=OgeGr79$aK_Q3L$&Qgeb+FG>g1Jk\J$8+a3FHFBl)Q
KU_#VuaAN!Sl]Ajot=*5NN@>==9RoQ9*\Qd3qIrB!O\X3l4?q:24[_X.Ar$.Rj;%h@s#=<8^t
qe#P[,G3gP\8RIQ=&V&#&?\7^]ABFmmm8#]AiN-+*D5"pJ*XBbZ5V;d4WYm^J>0g+5o>1*jU?C
c'o^^MFI"lhs+gk@[Nl1;aK00VG;n]AHhenkN^6TSb@#OEg$DW<QNrnS2U/+0#kPL0"8"k<qV
-gS?!Bm(mOV"\iGY_`V#==T9>2&I'7?EgNq86BJ]APkXOnN/6)Nt(4H4;<-q`op6ARY9@YM3T
?k7sM"*Js@ihtlD/I6A;QRfq4@PR!.HCR$_Np^$Q'<%1&8%U,2jc6;Q$Ook,2Ldr=9O!;K0L
g/rH+SD<^mH3S`M!7SX8u@0,sj'8X^\NXh/*Q)RJ),:89OH1!i]Ad(V185oSMsf[^P0t%>@/M
AFd+uIXSqSNg!;>5MSq&h(p!j]Ak4$'91dNq.!-_H!eQe1S!gSsd5g9)LI(5U_b6@#1fK:6o[
"?tK.8b4@&]AsZhLUXp2eV5s[guZ'%lBAU]Al(JWY9_2p<-lu?3[pLi=s2?DH9/CL-DFa!?f;?
OLkML9A3,mKB5J22GNj1SIZR#8B,tU<2l@U]A>rdQ7h#6UST78[!cWsYJMPVNYkh+Mu/9tqqf
oI!dV<Yh+Qfro</>J_l-S]AX4IuU4Q\euOF#k+s3CZnT29bCt-1F(;189D_<Qj8[)Mo$DOW7D
S;nsXtnM_fa]Al8p;\]APb,p?_e)(i70p%q=7[#)94IU+hcf,Nh\"'U)2p<EC4U9/G4aVDjtnj
DE8G0?'c?fT-_^2'GXN_Z+3:Q'^E/td]A?`[AsZg;Ni#irZWkGnWa^B?a8SE)8@V0C[#<#maS
6]A%ca%N+;>;E$cS9UG<)HVNNfVm$&k(4Bi?(^Bod5dnHj#-n\MeC.Xb7q"A;P-]AJd\_@Y42>
>:?E,C]A+&(:UCnG)_L/?JA@6&Z2XrY!/OA4Mhm_`08qHUoa3j]AP0h#G;.1`W*o:/dB:u9soa
@p<L./)l'__Tlk0E@r?!sM!cNa.P!eidXUj41"n]AZdU="':clM.tNgNob:U:n%'RaL[<@Io[
[2L%<[>VXgKtFnd5n'SX)DO\A_i[*(-`@@02<AnW5@,9`^8R29+L!LL0A"'6oHg.<%n7AKE]A
n\SJ<apQi!glAK,2CI1SM0kVnEBgoeit>;<U72*<bZ`iY2lG]Al8bA_#+u1uc;370#=m^afN$
l(nbJl_%o9D:fU#Z;o-!pO4eJmpMrNU\sX/d\p)9rm9JXp5j7>FS+"l+i6gN*><FOO\uFE=Y
%5!50_\t7[9Ai^m:S64Rsgl+l5mn(D4$(]A.n&ohG%G+dGj[$&.I*ZIi@h;pJ5H%RDInq5eC0
P:l"1u[D&:IG`M0VF9\NDq'fiqBNF?0R+iYZ8]A\p3OmL(<'(eQPuCm-+cjp5GpT5VI$q`*:i
McL'4\#\S9]A->Z(j$d,_4cb<6Y3.DeS9XF'Xp\KA32*q*l[e#V\TI@C03`!^Dhpt8AkN%USi
(1R_^l;a-+;b*N<q"_4CpHOh/_B<(+Ga)VB>.J]A0qU<<<N34Coj-!g?0.LMCkTDugqOcW7NB
38V*\E9\pPcQa]An7RD$4e[L;2;^8rc'"Gm-ra(Lpk-:De25ZM,I4-fhisRbt6*R$e-dE=0@Z
3;O0[jKHnmtL+IWOc2H<.8M#_$K=H88<1/Y\[ZE>Um#.*%-sZ7b>EREP)'$:O:`jnD#=!@YO
d2QVS2kW#?A;cu6/Kff7ZN(hV/Ye<X6F:<Zc=s%F;Th+LHB6A$kbWH2SVECgo8#:*hniJ2Im
lmO/IdI1<7S0-SjJ?O3hEoKOFdCD1Y+ef"PW5F2-Y@NJW>XY\7Ju_tMd0<2MJ-hB+<J:-CI0
.Qu5o8uNg<*PE4]AB&eQp11?Z?:X(F?e`8;h(o9cAWD5Q/1!;Y")/.[NW'"L<VpoR+NXkF9\B
'eMOUO\t.&n%8Z@BfP$AP(j=]A?@_RD,XDFpUqMG@/Ydp@XGm[NiVp6NH_!\Tq$UhCHei`UjW
TlnQBJ@r!A@<+_\<E)?@;6jd-X3qt/D8#$]A[?Dt9N'Xob61h9'a"%=qKWY1o#9@g)dh2?.Kn
t5UgT-[(._ANt5:#S)UQ+TN:V2n"V$MA%9l&&@:Gg6=h6_]A>jJd#BCnYdhKbEPi41:[Oe'<%
fApNV;EWF%$F0s$\F!p$3_`7+.M(2GAPflWN.0-!,-=>'ALW?e"hG*81@gnD0ABD3O8$bRL&
)oB[:Qb--ph=6cKA1q,af9O2^\Za.$9Xhr^MCa)GDNlX(ZO)=@>ohbUdRc*>l'hU%3T]A[NB&
6*;i"Kr66q1#=4f,E0"H_3A6SUEie0o""C3f2#PfC%2%m<$?qTH%@AdpAci]A*$CiDe9Rr/L(
M^KF"g1<KX#)gf>D*<74K#+jX=iA*lDs5-b6414'?N#OWjSQ)O0Tr>.diq&aV/H+C?QtkG\r
c4Z4^+8b.Y`iH&/ubj_[!2Y%&Wg&1C0ZqCZQ7IH^#_4KVhg*Z=@SVcH7rAoPgHWto%U-2ELf
'V>)um8Bc1;,=hsk3TQjiLR-$08MEoWo/>ftn?ZrWHd#XqjDOa#R;5&oni%0r=q8qajZ'7[O
mLN,\rAF@n*UH7$1=Z-G2lCLZ=+<QLal_]A^EHVJl*=WX>=4#5l9t"[-EX8"sq$DDR!uIh$!C
SnD2q`m(#,&aSlXUre`$ZLD%u1TVR5rr[gV:Zk[]AT(\'9aHipBO#Un%MoI'N_F]A1/:%Ym>\j
u*?,<=h'pWnK>a#aTQ)[!2Lp?KIlk+7-kCq`E'>`%)'NTs`>YC$$^_G^iKA54%V>I(0\<j;N
+F1COLMQ;CBONaiOuN`5.IA@R=Oc6)n'k:&)m;IVfnVmo-.L#kW\((r$+[tEijM>N!Q!I``U
BhVI@dE:Kl2Y7/I?n9O[.gG3+Vs\W`#[ZKVpZ^Xrntk5D67^C,m%m40n]AWM7N3gj\J9@c9..
hFQ<Y$a>iF?jRlkQo,AL7/uho8*@:2,NckB,?A0-3K;!YbcR&_i!M">pe\71n5_KA0Kpu)\`
TL;3ZmRD>At409+88Fc$Tj0[ac00MnpOA?H0%41e9W7V<g-1%,-/12.<0GNi[*_!@f/hnkOW
1?*T9p@?A:jn/Bb[&DWjmH!$8PSdOc[*"]AXq3V^Xhmk*LO;;.&!DAh5c:XoIs2hscrAAQ[T!
AI=_q_@Ei9q_=1dX42/R/ia&`LOYbDiBhW-'Y=]A_=P04g(Fp(qCdN$O&SnOnM\7H<M2:Aclp
on"6R',lW\hHqpY^.@_i&I)H<KJAEnuC@`O,l_0rQ$=1b,_0"L-_2`nM&!JWiGOeLYO.J)9%
J8pZ20p_nn$/E)<&j1;q'2;n1'BTA/&kBt]AI^pKPE>S6-m20[,KBu[#kq%#l?G>7s(8XFY6&
j`JZ2T'6#+9Yu7?'`POi]A7[h=FXs.ob.G8hfiPi;UDIj70TRYE_N;e9OEtT\SIr;&E*DT<kH
3HCSERarIJ?3(QWQYAO$TV2T-(?2Yg,-*tG>)+[$dH)mAmJ;^)C[g7\XkQ21pT)e?,1s_*l6
=I0lXd0FJ`VA,B7j*/ehlJ9GYs<.1$eW9=1G6r58"R5^gQWF)4j2t<F$=RFog\6A@W@;"I]Ah
)p0pkRD0GOYO/&,?-\>BVqf_">C"&Z\c1^KY!r8%e5.sQ2oe!`.g80632EHMuEY7]A>hLIqb'
r1kBA5oXZI6sKah8guhElP%=%!OcKsE#V<]ABFso06SbkZ=!GqNE+P+<6%_:-h"ueF(S^f\SJ
ui^HM[`H7S\#`NTI<(5<8Emrj7)uohl?<VF$'j*(W^,<Ei%R/9D#pL]ASkr6/o<K6f='JI-S[
e5?+-$6YK^UY)IhFZG/>aXl>D@ME/phI)Z>J>CnXD+sq%F?qmo'!,)%)cqH`s34?e%eJ>mQR
ApBD1G'8$j`#QtLM4NZHo2jYG=*>JaI[PiFDC`f/of]AQ-ZAHE-)@).fKJLCFc+sCAj;iXe97
A@ig4sa=$hS4R[1.Vhd\7'qBP#+M#0_u+#:jsJdHOR`H/G\Hg:jBUU#Cj`qMFbi03pH8dE,C
dK*@tLia7-mE_/qPn0JSLFPVV)g`je&?8A^!m;(&/AVlL+.V8JhTfd3(D.Dir\WSm"NTAI0,
"2S0C5;=6t2`4("c*fMh<^+*1'XDLK?HW90&mL6P",Lgj0G)'S2<RK>a$jDRmT;-"?0DkS5d
:WOTlNf.n;5%ujh/D;[e'#=(n:"a.G+HPel,#'[%'(rW49=47I?"jW1%nFQaa8]Ap+H'=G.r-
l$tr;7VG"?utKPUk\q!\W3=81,JmZ+7SPA3]A$ORmLU+7j/:`'CN#oN.S/&%omgiH>HVhXqLd
Zm<LN9"56nK135_Rnf!pPg\;_Q-ZDIkFYVc2:5?gH`aFZ3&Jd,7SEu><T10Db@SU>+GCBpOe
\J6)jq&E\O^Ig-I8OGW-_;VKmZ$UiZl4<TWX3H#,Gk'HD.%d"q7,a'-)aaMrnY*<eW=F=.g5
"k2R*u<YN92*hPj]ADeTYBuc6CghI!Z'KQ?8=km7#UtEQ<UB6IL03WLclGWf6EC:h)!_d30oQ
S0Q#Kl]A<jc_7^u(V]AOeR9O)m/q;2/:2*pIV\J2s91^gB9k?(q^K*V+!,=\($lppK66#Idb``
db1ZrE$BM&Gj&bH)s>Yi'I_H>J4e=b+o!Al'),J~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="8" width="1152" height="56"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report2"/>
</MobileWidgetList>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="540"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="body"/>
</MobileWidgetList>
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
<WatermarkAttr fontSize="20" color="-6710887" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="8142cf4c-1171-4334-a8e8-d8e61cc933fe"/>
</TemplateIdAttMark>
</Form>
