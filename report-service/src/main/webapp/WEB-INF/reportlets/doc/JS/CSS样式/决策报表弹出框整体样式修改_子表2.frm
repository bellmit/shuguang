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
<FRFont name="等线" style="1" size="128" foreground="-1" underline="1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="等线" style="1" size="128" foreground="-16776961" underline="1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m99rt;chPa+X/=7`b@NC(6\Jd44\CcC0McBP$l[oI4/93`mnOS%^fs^iYVe#,WBT-O0+Jmg(
l0!`$.Z@9<_ANBcs*\'OQqE@aGBX+l#<g&b7-lqqoccW+/R`BsmokU2*b54nh)4rq<h9^!GY
iji_>$-;L$96-uEoQ7R=kK#[='-tI]A"GjJ5CB>-/.K)Q\fd\;GFhHDteRCl[THrdn'?q(QGa
ir3Sk-")Gp"Ne9n]A0e*Y:%*Yq10(>'J-*L)kVGaM<`aS-7?8EQ9VqJFmbP_:F>,*p:8oPLq"
FI:sgKOF3`bSC$A='K8<^i?'!=0e2$!BX>7pr_851j;.#f(K?P6<L;&Gs;(n1hO0ZOG1I9Wr
I;]AV_A4tN^NAH0Wf(JsS55I@*a%Lkn'h[*rqco.58'oO@on9l'kSV@*<ar:LE5'-@2`b::,p
0>-(VD/=g=MkLF0%jj3pM-uX4Y.MDq#=UX$o6HLlK0>`JNRdY!.61>nNC2HY6K0B=`6NnB&9
6JrNrP7rR/n'Df`3(qLju,ZnC7,sTCqF'udS3MQ:^IP85nc"1[ZVuC*khtYn>@[JicAD:^sl
.b27T-[h<]AO*V<oJf\mU"2i&h)^\ilV?X."N]A$p]A:4U-'42Hm>:+)(ei$QhT$f]AQjY_,#7pm
fmJc/.Xk5pnd-<(<tnAXkrBH,ibrAWVWXT!5dKn"N.ZVH4%-!<i"g_s[Z2%R).X<o)bDm'%h
!aF9sRkOM7e2bU42e!qN_t6`b^ldg]A[O9nE*UZ8'Z+$!<>j8C;nAWM+N9A0BeD8bqr#<[=[V
eScV3^nYL3/^U5'Aj=1Ro5tiNkUZr@H_"?%bg6?YirP1c9b%"f:bt;okX7X^8sOUerqYp\(a
dC`8_u*&B8[K3jFf36_gRN[Net-C]AR)U:D&TgQI&]A9SM@E=[LcEXI.0h:2uR\6EIT%ro*0iS
>G:*.VO7d-H*?aSso/C5Skc#YK-,<%\L3;iU[B-gW"S!R)@riVdR&RgXs8?hk#UY4PO:.ot0
JCe+q:%SX9`7O7"]Am5@Q(BU%P[,9<\_0'@bAa/C/8@`pWg>0imul^8_L;ha,q&i.6aN#aJ$I
\qCX.!L]A*\gOjC&k/lM<ilS`aCM>D:PJ)QH3]Ab$BH;72\j-7pe/E**7CqQCR94Zj7ed*f?F>
7+s;5/!sdE0t:'-55R3.8e<S8T^td#N"q'["8qo*!5PVC(90,;_Qa(Wt=ICgdi+_(>UtCP?=
*oBbGb)15Gq"/csF_hBpKcgV:>HY)XQZkRop1PfJEd/HG#:b89!FDtMQme&sW^L=^RH0Uip!
mTQX4%-t;KL%\!n>b9MD0Q6GDuo+F:Y6IsCen:(MB(@XoOp(B!dAWCMS1)*9&DP7hUeai0e3
rGY"R']A_u*QEoj<4.X,tOL>//ArFalHu+WPC6'OtRpk4?>_ia5)W.4>.1a8V.![Ip56H?k>6
:^-mjH2!?ZbLhRdBTc9aMZVUnN+X5caDZ8Ea4u+.^6+*c"0g(1^g>EYPE2R-*<#!l"X:2f/M
9D_?7h%NlaV$jEoj.CKE(pe+!jiPR@Ld^YOR=:g:kQkk=BLNMBCFLS+G<=AXUhV5OH\-obTP
YkNcU"X(gfEPZQJuQJ=rb54VeUbrY'4ahl/VU:/N1rEd5H.m(Y%jrk0+B)d4QJX6'W:8Nd1B
T5l#/``NWgb>t;XmkgFenlXZlmkWd6[?h*hdO?,I'R<)VteKo]A\?Ke*ZT+28j%@"Ah!9.=0g
R]AruiWbY0GkZ(pfj=XIU-1Z)mo!9QSs>gI0&>9t9k;HqW%b2D'6`Gc4L*OGWG]A>+8ec<&.np
iQ9,Tc5uY-f3,1+DC<k%_:36i/6&eRV80[jRp`7=7c^FDdRN5)qqQO&OS$NTf_Os]A?Yag@(/
[4Q\(b:GCYElRfVPo9XEK.8[7a7GN#s%0iZ67Q[g=B8(83X]A?/^Ok>d$nT7'/!&H9CJ;O$W0
aM!Z+3.@s<5%Gbj-_nAK<5)mt1f:i=%f6%sK:F`?0c+MeQ[k.Q7Jgh`LZPL%)=AoqD'A-RSV
F(i/&POa?F7SKDck5pa@JcIg_MV?,3C@-AfJ<>/CJrniSDh*eT)N\(maHA%QY"47F/:gX0H+
KZ>!mmC[2CL?.&GTm8N0"3a!kF"96j3dj^9Yk:onhB;jA_@IuWSl73BhGjJ%9A-CX\Nq.kum
1^<.$"I\UdL$ML\8I^d#VJ#RTJ"<=6\@E$G&'@qUeWC23[+BT@#"@fV]Ah[L)/.l9ZcR0E;>G
:Dt_f$90]AaSh5a^p@UX*-P@727AE@Vt5?(:f:gbjVF;1*[HrP8+F7H[ChAcqoVsH1l96:9-?
gBKY*%.OTQ_n$^)dcVes?()*MqCn^8#Ing7i@/OG@nE%:\MVQ:'fFp?.d@eZI7J]A@/F]Ap7mp
e-!i\m-H=Q183ZGh>O>SeqC"7]ARB4UUFftJ(n$=#Lu<bO=r618B/iSO&Rd'S.mE;7K,s'\MN
<Aih0s?7]Ar;WJT(FAYt,IN[#P3QTm!]AXbH?"*c'&h"KJ2s9CJp(Am5!0i#-R#]Am&3lJG(qnS
BATj^QLLFDIrarFN8,Fn8YcoXAY^9f/f@+M2UWC8ntQDJq5D6.AkYI`mNOO2G'+UD0BGn-.R
+ZOf-0S@ZQ*iZ_"kE.^\4=YEBUe)KnGf0pg)Y%IKA32UbnJHOZeG3X9L#oI;1&uoG'aZhP]AR
Z[_-0)Qs*3Q>#YIcG^a?skGR`Com\[$"@HVac$@LLKLN7@"gT:_WM%jX?nc=,X(38t%^.pOR
A\m.m!Y:5C2;(/_/k_gjU5jF<t4=JmUAl0\B?Th9$toDPG3?agsiFYeEDA;L:'J@8s_q?d^t
Wt=_PVm,X^=$d!9"kJ$68ff0-0)i>GZTSgEt)fpMZ(9UMiM0;%;8'eB:C@BN4.jO=GL8G&tW
36EiPIosG@HPWM\MH87t;6%]AWH7XXZ3-r,Zj;J[Y6hO"9>_HNM^E\cTR3\#MQ/p+53bF7H3X
K*7N*IPB9XEDQ/4LH3.r]AFs'Qu%ImKC$BAYe]Aaj67dFCE;T#k"<9Y!Ir:`U/OJAA0pU-kJKn
)Rb&uZ+bb0"m5W%oo%]AnU"M7nKLT@(R=$H:5YGaDlo,&rGX2p1.LZf+ll<I0Z[1nnWeQJB\S
Q`/_MV=F4<TG17n.fFknNa,8d^9fhZ(iPe:[Z\J_lK"n8kB/X.ik2sB-K^7KRXcIe:raQfl&
d4[H"sO$?fGA`\.'QQ%VFihgcB7#M#kG'Fth_B=<#V_WqL-7q@N'b]A2#]A[bG!Q\W?Tp:F58@
/p<;_<oX,NDSh4k^s4+tVl\!Yn!kUaf2(\f'cX'Zj]AJ!9*`Y.Z/$U)[qgEA1[CB(?DeGSeJs
BVQS-A4PEdrB+K.(&lq,lg4ci^gH?d5C*rL62Go.P4Ll;@p_&47+S^.YEs4-qC7@PM2C[/(R
S@D>UX\I/6V@nP+"BX)NVJ'jr1c^j)tbHTMI)g6GLd]A^.kedYg@Z(,,n[pp`tFlB2kSoaJd'
E%_W]ADd/R2q\G-2"VJa9r^iV75?Aa9A&DO\f2Ka9Don3WQJm/<+*$Trc]AND9Oo?HZ;C,8ISY
Tm(B6ijkbJdMPXGPs^G2Z1qEr'np]A]A`maHT@M=);bW\Ed23Fmb`mnG@flTE0jJiDk<Z:<f%4
+:2Jeh0qBJ33>!\=@30+2/ICDYAQIY^N.F///+NG_,YimN6U#IC6d]A@m;Td!hY>:E?2%UppA
B(I65OkElH3:;Z/3M3ON'?3V<YpJBe5UbGrMQA3=[$^/Qa''F83fJ+T<StO#%*0G+s3qIZW`
X7hi!IVuK_2p+f?H6t:<Q$loe;da?_JF<i+/]A7RTV1cKCM;a\Vqcm7PnVr,P#N3I&8<kAmf4
G$h`'-f4l&UC!2@+bWM<iMVSg$1^6)`IFkmr4<GAB]A[m4[@J)63C^A,jH5fD*Gq5WDN!`2r_
X-?eGG378fNB("Dmo0`K6_C4crT<'2R;j>=1Na+u*fie[i^'WGN5q9CnNJ"P0tTK)*Be_gSN
_!?-I2?3tkBa>5M9VDRC!u6e9;h2HlbPFd&(`<KG7O;K[[QY@)kH6D"Xoir)3H`g;BQf\Sj@
_0TLq]AB,rH/hcVN@<3ICH'ST'S[R38ZcMA"46lH$aQ-lu1Z0X6MiUBWt:N'%sUdo<<cf!s]AH
X&%nA5o[+C(]AqL@G+)p+\nkK<P7RR1tBF(:Zj(&FN"SA"`rN$F#c]Ap9*3%)4O6uc!qPQXP01
1!,_=i]A'S[1g*djc1(ElPbDBK_NN6\)"UOK\0M">K>P9:(4jFC50%S4<@A!f(L*9*N(,c.V"
]AM&f&H<$D#ItieG-Sks?9$e+;>@+U6c+'f:[DSeU1H#0_C7H'CYqbU1).TGg0rY\9c+1T^/H
^u-;`QUSHi3e$jU.oc]A:9UZ3#:b9PQ%-RoSoX`8gM+%A>?j\+l`UEk<q7HZ,WphL_N1\JWp4
:ArKpN@Fn+JueQF)p_53Sl`rGe_*qZi@^XQ?p/9-1.U8dK_#EX<R>;Do4j[L@<g"`Vn/*ilJ
qA[lKh#eX9]A-&:^#[2"fcZ"$gblkTMH1!VPpIjBt<fbP*;%\T5<afjM#FaaK>gCB&dDldU@;
tP>C;dkljJV'dI'Y1j]AZ"gS=hZ8aqif6s"q+c)[=;aR()W7lN"?Lk^S)Bp@Y).B?-ghE6hO6
7.3[5M\Sd(q5-@)FKIUMM/,maCnpU)``n/4`n=9OpgL1YHC+\]Asq"VOhG]Afu3Qp)bO0Ip)kT
`L/`L%p&4t&'['V&,lVHnB80?D??miI;diA]AN;c--bQ1`5KUGRYNiIH"e3&kZWD[&?oi!fdI
%,Df*tK&NI5CqjBV*P(c0b0!"tXK$YIP`'0CXEQ=`X.I+QI=ms-ch9-Z*N4ZE`Lmo]AeM16DY
:`2lPt"^c/O^QiF*8?L^25cO/ri3SQeB\jkY=pGV!OnIa7a6eafE&ed_N%o$t2$0'S"3@:cU
+\WJf7L?/1ihqO;_S1j`p18nD@;l3GS=cLp5#$WMWB*0^7[oqP)edEk:,o<-;<3#r3cp0:BM
$YOf6MT_5j$srgu?0ES3f+<a8]A#%i6WI^8NRV/.h$Ne8`d=MjU0K*XPZr16I+;a0>a,P%FU=
Chk^dS(,&W/.1lK$)^#6(LI6hVLGmdAV5B>s(k\ZI<E26!N7^rFZcWYZ*bH7jYOuK,ci]A9^a
nm<^'AoG?!6)+CE'`\er2I&K#$O5n*c;(FTJX"<r"Qf`G3R%5*0i6#qq#dic.Qkd(3E-'B&D
<O1U3af@'c*Q,6M5"hZ`9n@Fnh,3(":Mg=l\J.Eh(OpRn%`2/8%bXp5F9QXDI9,KNOYM[-YR
PGu_YQ.=VfmTcG5P1B)?OWfLb)0dYKETGXma*@'AJ#J]A)9c@,q%tNg1fB5o"W0!rXA>jP`ub
.g/T?'Xiot56YA/nWFP@=2cMt:X_=lgZfl<V3%$P.++P!=QDQ+o$6NH6T9O2A"k&1<cWtEOr
^HI8QotW:a1%I.%g:0=SftF6?1'RLZc+.s:W*10>>sDJ7AMU[(Vh*:iLq5uP=B7i>`f8Ig%m
#HAL4(S9EAB5(Rj2*sdmE9$TbJVqZK0u1AjuktT*0BlVM;gijT'XG#nT87SqrXMm'6!UcVLa
n^OPU\3aW9/AuSDYe4%GQ77Su*Ec>H4%K@Z!mrW^:/,YO'8#hjOrAS[MSO]A'oK4Rjt&7]A'>J
5,ehSnX&HY!E>47C5!I.)@APn?%&=D,u):g+d.\]AWtU_H#4Ou&M1`_0``UiOF$ENL86M38s>
nPr)4//VT^E6!\*n@,UUl3o,6SYA5bZ/d_6cV`<1Rodt9_-n!)csajO1sJ64t3_1L9Hq-DbU
R<!',.l9P`CMqLq'W,R5VZQK:Y-*d(Y2bhpJCIOP7A%G-JV:Yb24%BEh[+%jrOBRpH:K1WYH
au^6%EjVn*Qa$2&N:;YOXjTj7F;@_;EiSk"QJgC#ot,"7DQ9o]AP*tXOdOJ<I[Eup^5JFDkJW
`1mBRP?#gV;NjB\Pj1/TR1G]Ah2Z<6Eff-d0&cB%FBpcp0OHLGVchH7L-6%/>/qGShe9N=^tf
3`a:T=N=%@;r!f&%-WL3Rc?7dm&anmOb&Tn(L;aKts25p9T7iWqSJ9hS&V3(LiM)<m0\[Aao
gMcKD!#+Pd4T0DT%n7a1'B5-,UioWH3EomV:U9S)DB.pM@`mf=<PC^EO6/!<'`c[hjG=#D6g
Sqm5IMa;nVNk^Z"'@G.15T=k??*TV\%,*li:TFi-.%KHfXQIenX0iG6?]AAk(m9?0EU67ZAgB
0ZFbuAWsBFeb2QBkb=6!C/C&"Ek&iSMXDobL;("e'q8<g`4)p2&oIAd#;*;:!#brLQ7Sl<ed
L@mjXc5C<`-N5$0^%]A'!roRn@CTp0LD==R]Anbq)!u,OB"a`TGsD"u82ZC\h%`Z4Ymm6;*mHd
;%.lhcXPU=Eaa0<,9EU'9u9s=N7oYW%1d;KEoa\^^ke]AW\&=DrJZna$"U\Z`[1c?GsNkZNZu
<5kO9statM=fpuS%>Z`>"WjaOJ3&O:V";gN:or@@kE6f=e]Agl?+\C:qid7!K^X10;-7ha#R^
1Z=0qi%Waaja9mL&0hhh,R@rts6Lg7$q@YH/u[e<s%QIF<ug7"C]A0\H!K-Sb.A(M4GaD[HfS
%!]A*tI?'hc)&rFU0)1/L&UE5(V1D."S.N'Hso9rRcGM<WKX/2<$a=`"'b8^DdZObV>^[IoQ1
&,E9GrAhk&"G@9sqJ,,1CJZsW1>.eZKeVe5"rBC~
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
<TemplateIdAttMark TemplateId="f4939f6c-68d4-430a-a1d0-994a594ae1bc"/>
</TemplateIdAttMark>
</Form>
