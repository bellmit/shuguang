<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="a"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT 
订单ID,货主城市,
strftime("%Y-%m",订购日期) 年月,
sum(运货费) 运货费
FROM 订单
where 订单ID=10001+'${mod((datetonumber(now())-datetonumber(a))/2000,1000)}'
group by 货主城市,strftime("%Y-%m",订购日期)
order by 运货费 ]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
</FormMobileAttr>
<Parameters>
<Parameter>
<Attributes name="a"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=now()]]></Attributes>
</O>
</Parameter>
</Parameters>
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
<Refresh state="1" interval="2.0" refreshArea="" customClass="false"/>
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
<![CDATA[2895600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[7886700,7734300,6324600,6019800,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[货主城市]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="货主城市"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[运货费]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="运货费"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[round($$$,0)]]></Result>
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
<FRFont name="微软雅黑" style="0" size="96" foreground="-13382452"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="96"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=p>'5+mP&>\S@4`r>_OS#iEm__Y5;,IW+p1>$C=1b;o9C,Q7QPRL^OH=")CeLm$etPXWAS
_sW[1\`K6p^rYM)ItfW-A=EKK)@P+W&42P*n,iZRtUe9M[h+g?1RP4T(<po3L5Ap\s%,qgKX
!f1)p13dLF0&rAGfjdWHU,EY(l,a([C',.WHm(D8G,gs9>Q*2ch/#0>`4Y3d5T2sNeBO/`:Y
l2Q"='cKOEK^(a(DkB@'9fIpril#ji>8,$<rCS:/QM**iotr<+V8l.HtV?5:6)E&-^jheT(c
OJT;^IdR/6aP;'6lef3%D_C.<FD6&c38IYhe;T;aV!QWBt?YnVC5QQA7.*/.,NZR?6T@-q2+
Bk\6Nf:GlblVq8fp*ER7itf7>aT;7sqp2kd']A8Dmopl"DmH)!jPhAAplJb?G^s!HVoi4B5;:
tHd=<$H5RnKO!mQWX6'OLghme<p8/u_O#/o42e.b^p]A9mS%Gp[\:GoKdeb^ouO6F0=NlgtM1
Mr[e#'Ka4@gCs_?0GcE;DjIRZoMSUT5rASAR)\5@_C;8fs]A"/5RIK0,O<-;Wl&\s\7fJmHXJ
4FM^Pe-2a)R@;1ZRCmbEM,E+Q7LV#rZ;ikV5R6'gQ?Zi;n-p!\5DNZFnY9m"1IlkhZT57Mmm
N9edPjHD[I-HYA1D),73V)-8k6k-r3^Vm?\T+#t>Qu,Z7]A5L!+Y`m]A!l2nFc]A$c+.L8qc\nE
,]AJ1.!)F)'C=Om'\sC_uo+&rC08Z\XW[\?c&\*r2pDb9r?bec%$2pVV`ER[lG<L8uQ3@RR3&
8.8W>Y\oY>#IUZ^qL5;i'S4;JJq(&h4<kbIR_.=0j+99#/Z3RJ[cCVTn3M0!1bgCZ=b_mp$O
h5ADCc'S#"Z`KITKaEI2(I.Ml-DD2Sp#YJ\CDe&:GdMmofMr"T;j#B<?_+<oel+b6G4`uoJP
YlHE_%1b<J)m)BNXcD5.O]AW_HT.6)f6U0cf*I8$jR<fYSBOXjO&VYWDBH8b`q3TiO2Zj5Z*8
5]AH."';CJ2fVLfuX@ASW_!"mF]ANb*(-]AkFXN!rl0cg\sD"kWA&cJ5n]ApVr6$uAj0%X*<\gBt
r$6#C4G'0U:HruVrm>NeD<lu0k%kZqNR")L^+]A)jS?bbC$eeZJZ0:YOYVJEG/$.2QrWT>kpL
/4'+]Ap>7@(#i$=:(lfU\Jn4RNghq7E(G'MV)K]Ab&u0Shfc[4i0hoT`KT$#:,TqSY?d=cj)8K
uU_V#Oe1C?qBihdu@6"[%5bI>TCRm>P4Uil1@(j\'4Z!r1Su#[*.@:Wp\I[CXGf?_/@'";6=
pMIASO^;6W#*nXTT@%YjO?2"h<2j:m9h*t]AaS*C[KjM8TCr_,rA;o(ddfVT[/+$%ioZi/L#=
8\k1m60^%M\U`K-#2pcBRHZZ\ZSg;e-erI!nd+EbJPB:E5nn\hG3Et=fK>kkVg_Zf:[+7='O
4u_oQI?O?2--D"pP32^/s5^k[kKUOW=#PdUT%nfVF?r7ck4aLGD&ds!Tr%-pc^Il2b4D]A-'3
V&bNRg>]Ao$&&Y%nA07<;Y%>]AsoM>@q.t!`RrWKD4AP3o(uP3O]ATmA"6p`O+>*l^)@#OTar^1
tK*=XTlnb4'4<k_<pY4SSCY16DfJY%>_1;F4aZtfKasgBm4I]A5USML9Y$#^gq_J5ubrV*R22
Dn4iF84^&(,Mq*OPqf!C0T[;p?lMo=6)2NH^4(mnl^NLQm8dT;)s>0+&p9K?!X*1%4_G`!P2
2[8+o>6f:c8GKn:#W+]A=ra;mAX!/me_)fjYTU"G"<djChVeG\L9]AMq2/ljcDUKR5P'!X#71>
k8h/sd@t[;kQmg^01/Pd,`e7cPi6Gm`]AfR]A]AfaN(8@/%Ff0HV?>nWW*@rpA+0(`q:,o.A+>K
p3ZN`n:t'WT4l]A3g'Jb87B'jX1^qPYY/_#W%>TeH/uTi6WTY`sMs4-`3#YjWm@(!Y)4#FBiI
?D4`Re#J$E$H+oLO+n?5/\ft&&`!kWY:=8OoYu3@EI2'[:gRVjaE`dr.ciG$$*#UZYMQ=Kc(
!mhSTj_8K:]A:AL]A!iAiN\s25&'U0eCgW&0gT@*F`hWI*%S\u+%Fa1")g#o._&hB5W6D.7b+7
cZ?:Cg<HuHL):8O.1I$cO%S[%;c)"I'1H<h;9K*JNoWF7=XSFq&rmW/=;aLU*R'=Bo)9&aqS
GFpqtH9+7ms3Pp"gBrT3W:lBuQWe::"89YpL*<02W;Ph5\m#bWAV6HA&i-1I#37fc1p^(_A5
5,Ngc_sp2k3K;L=3Q`[F7Q:n1Si%^hqIj_=AWh!#7P>cjt\Z-GUb4'P`qk]AJG2%qESD\QXrA
m9aUu'f;_H$=KSce`R@b+@'*',Dn;YHcPA=niV1mI6u3e_4Q%[@3J43'$Hbc[>3"A^a*I@Ao
.ETQe/5Tp=F,9,;LV;,?TAKR2rls#ZUU)P0`PFb/`:usVanYPI*/)m!?,o8"7T3Vd5#3G;s3
hXP,L<'&\khueF#%[@'.Ee^Pr<dT_C"0Pq_SWE`S:K)!b-,jT^'MJkG3))=:KD]Am)_HPh$Q3
o946M`0#q-C_f%]A)e(@=3O2+rd=&:@bMR![r?Jd[dMeNdT.q96pO*e$cl4_T"j:XP*;QXiVE
I,^!.:[YRm$A)FQ`4]A>Xu2&T:oeb!7lGi6?:#LZJf.R;-L#"X@D?TiU4JTMFE=P?$a_2o@Rl
JI.W\oNW\kjS^sNPaaUDQ^ng?6@f;9!>7&'9AZlH_P9GW8C[?eBdP(O:5IjaE5@X^0d@ueLO
rLuGgM6T,@Xs+kAqXYeiFQU[`uol;h(*^Tm@I>tWhFWJ3#^"5l\6<N_"Su:B\`cR0%9JO(RV
@a(tA%$BFM4V\1s(,&Ua/,AqB&KTHHfmi]A'a&RF;;^Nh0(]AQ`aP4[d3c&'ih.ti?fk'G,W\s
nZGM&-@`)EFb+m)7U?,e@riDrC@sIGS"6"JiRkL/FPT_k`hXO*[N\6RcYH#I6SCtd]ANXHR?5
53:$T$ki:-0D`bYoKSVJKo7"_"QJ)goq$/PEB8@4m3(?,P3d0;fd%JOaBm`F<S0QDII1XG\F
E+k,o92'eS#-4KmHT[e&80#(*WhBITd`NVlAI-;tR"fBhUfPOH/\VM^>X8*%V(X%Zr\e;s2J
ht@X^]AiqL9^`f:I]AMX>aSKB!\&bUZ&r6?se/C*Z2)W0AK*S84F$b3X\SrpB_WNi+Fj/XO$)K
VAC3?0kJ3m.8BhV(R@FJ%2lk;mhF&X]AIU0l0LQVM18HLuZ$R#lVKcFuDsY+TFul-,!,Y0pKC
\$\aXB-*oGn4f+M1A)7BhM+^$6a9l@]AC2%gD)^rl3P9qj64q`]A*u2ueR<4Aj]Au1Q.V\_7PM-
F=oN(H4V2R_0sT5i(LDR;Jg51i00fY.AD^d)J\Z<39*HVRdB#\pW9K-P+"Nm_\<X#G_*e(I@
?,%ZrSg9c9T7*>uVg1CWh"qruMJCVC5PuE\sS,/eIX^Yh;]AUISj/Xdn/2i'&^9]A>=\6(Tm`l
!X5p*sYun_`>]AYABoKXj9l:YS"jJ&i7`SkPY^Ca//Q+u/-nc#pD>G.Rju=$3a3+jOGs%]A@+0
KW<GK_DE'2#U=fJ>THtYE(@SC0:Lm%c0ieXSBKaT8#q%TM'IEjcVIOJBBMCP(r)`j'9Z2o#g
/'uZ^mE1\GXVVLX1RuH/>Ik8JfKoFDl#:jj.>A4NeNbHMndH4DGis3c7o*6"EOM(g=uDVLSd
Y3#(K)k@>b01!F:R&n8BX,MWdUCWQiRqh!G*m92W3h1hR]ADmU;442'00$+QTcL)\.H&!aE>+
.aq4fs!;u#=NnP6e&mWY-pRF9ZS^b9>$JAS\FpWQaF_*G<iN-V>"q&5"cEY_[\]A1[QQ0s=:+
]ASrh9^hgM#FAccKBdh$,%_%MkH`6UjIS'AZCbZZ`*Os-k=iI!$5[loGde("2/LRM4$\aVN<?
?HOhjZo\U9_YJ7#H9J?R@*q0*4F!e+G)`2&sUg=]A`U`she`HtcdhdT@]AsF69qJ+P5s,n7;Sr
j[93":.M@4Gj^;/hQA$VB*IB*mV'A>L_%^CY,(p2aJKJ)oTuelg)\$UeKk[U$K`hgd*54f?-
8ug6\\Fp@[P7kTO4YaG?AYhk&IDgI&iZPpTo$/VCANc0c6UHO\Bcf@i(r5&T1]A3hoG;e9Rcm
,,oBtEEP1Tu6X)J1Lf3#]A7A=(H%VGI;J8*D16*WQKHa2[l,DMaPm;/T7@F*MZd!GdD*-mbe4
Hj,6d\SeDZ!R&Vgh'dY)Xn1P0oO85!%QnhB!70I;%ZU(gMCb\j8JfdW4CLOXAI\jGL2jCb0q
qO\Z$&+d\I[P]A?E=Rg6'+oLQpud^'r\/W%\0c5#o^UZfk0?&$5aM:^P]AA[o=5O4(F8`]Acd9r
=@)_A)>5tgijZ.6:bb2A1IcNBVdCaa-Q+CP&BbS$qA@Q$H;&mA6RP7'#O12t.6o8XlYE"A'k
?u@UK"+W3!3@'Z@S]A5`YFp/9rcF4\!p!*K@*$;B=0)@<m]A&/C5$tp)p[WU+`PmN!&[8.\:A1
JR+/gh\&4'X@A8S-Yn&mA#?5FL(hDd>N.jMubD/R/[>/>_U'tNs=h_:@iqOobU5$Tpe?T'Sr
*aYj<-ePK,BGia;fi;?0iu$F!<5re>t@rrigR$sjnu\2'\dC<o/bXF7]A]A9?Yg-Ym'e7Zm,I,
O/9ecb;0X]A5'!SUC4a`lqhXR9gM,D0,#r_FPP%d(Xnp(H3sp"WeHB@T4:-+<FI#Y?f0J"Q.R
!]A%KSs1_2UNrnFM2KL&'l\EV+l&f$"HcsbA_4AV[bum_<;.R3<H@!<O+7gE,%[W['MN;tgm2
1gU?(6>;l'(m+cS.L/132010$u>,DCE?DKAMen,e/>r1VMDOR=/WC6FSJ"iXFnt4nu3>@Tec
W/`hYH92<%*MZMJ)>)V;9Rt-U^Yh56(_c6?2AU.bcP,S_7X=aJk]ApeVn%Xui+ghg-c/r"JEW
qiB.I.Tu,,3iZ0%5.Bb'Ee`PP@cjimc"'L"pi?I"NNI$ZZ=XU@#4'07Y%2iKKUH9hCQgk/2g
;Vl).)JnTQ63qXJT-a='8P]AB8-lE*I&X9BO09"NSQ&pI]A"$$lQ[^Tq-XM-^C'&@(*ERZua>)
e*8F:g*O)]A)lQef@le["8CklCB5/:OrR$S#`n);`li*)5.AZPHdrlIaIq1)X1j'W_5LpsDQq
C+Z<YSn_N0&&hO(,e_iHZBCM3qUC1AQ]AD#Om-9Nm?[A6'^3dADk=/>Ms/0O=P@0>W,c&S\5b
^7_aq+M7e=]A#q]A_i*CC3`M2$u0X.3;WqpBs^'HJn-emBP[%_5.mP'GSYl\XhZg_3QB$+USng
*udH"j[+TM?FR?0"CJ=R7X-V'sN+XZ*E,g$C9;ON_9QXahUIugYi++hd2g9#o!rl!NI86i`s
(^&UZ4,rGERpcFWP8n2?ZZRq%BP%R9SJf"=?GGS@Gk4/K^q,9@'K89#,6/&AqfeGj"XYi89`
1Ns2T2ii_!GD6TnOd6_NC/jfpR:'(m?)c<td67010'h<CS%fB8D-V*rT-t(Sr3=J6UbK_PTM
mn+is69]AFP`g_o8.-S,\[2bGN(j]AqJX9kl>dWgIurLIF);=B'XpEh+)_</[LU2E4SlTk(fn9
YhaY4FQAC.F's?L$!cd.gr/>Rh&F8IgA36COaTrJa2O2kU?A1rFg#hDk9DD4R@k,/1UA@u+O
S?FCO[@ee=8[:NEu"4IW"8ur@Mm0IHq@ms)WG(4l+_gF:uk.:al5%YjUI2N^M]A5bL;@5R^Q_
?QOW-J:*o58pESp8+FWYZ0E_=/O6\'oJ:s1t$Q(3=4T7.08j;s@0psX2fIk&"EfiCEN)L1=s
KJF6V:\G(3:NBGqRL-Z7i:fGC'n*&R9e#!)g'DGcR7gPRj!)r8X)c:\bNb17&1*N0+S_f]AD\
%^]A`PR$Iifk_1DC:+TBF651mfL8:5W>.o81-"-.r/;Il@\(9)*ZBr:u2#PCEf!+XtB.N70bS
D&,B9=JYL8QQ5jNu$>+`ST]A+rQ(VQDV_lGA62/@o@eV-qHFji%r2W+#k%%MX/D`BVS"[Dl=G
YH$^jh8'R\m]AnEAn\5l\5p]AN0ocbgHpii(LMeDhnuN<5"_CZOfqeE-WN%kJnDK/kS@=W2Qcr
a_,W=fD4ugZB=7Z`Q"s4kqS.k5pg8O0@.^n8jXu"2riG%=uPYdFp'cMk'S1ln<k_&"Y[>h,p
M.J&)/fj*kD^EaB^>Uin57B#]ANm9a>M?J/AWXgfJckJSFiB-2PFE1ucZ<lhpA=LqoA0$Kbe@
doG;1D'1Q3Z@F=?oBslCa)<;?IFWZIF/92R.pr^sT/cWo+$m,On"`^Ej)FOW2i_5l=ed@bMk
Zq00t!7;C='7gu/#@=#:<4iRe7T5m?%:GjX"Kt)RlSLV.Q7knR/Pjesh$[r,?D>!a<1kn!a!
Ki3d-]A%BIQM]Ac>fY*!5R:OK@p$_OCFD3i7M;CfM-RCsr1]A;Z,c$G:8<!`+8A+:!C-#Ja>?=F
A"R08*#DDVY5S)AS$1Z7t9I'RH2^4UHQ_ZUtiHT=q9jOHAb5]AWfDjIZ<7PL?n`C[NoChr60O
>P">sa7EI.MIJEHki'ESgH:)"2!f%E^*UR+Mg:Q6JRP32hGf,c,"rrJ:$]AZ>@1]A?-2naDSj"
>4rY%C#h#td7EQb;Js,2HSM8sUMBd&)Yc(RcdSa)>:oeJ.Na:=WNICC&@G]ACVMV;i8a!f8\3
j+s?Q(oX6I8V>.3>FabBsS.2K;OE,p.pQYe=3L2X2BK"M""S=7U2jWEqpd[PV#2*ICF[ucJ#
S,JJ65.B1>dlferMUF8\5r3lT+f_^7K\]A8TC8<M#gt?QiZF3B5dmR+e54pnpu.(Vo7U"aPYb
fpB@pl?V[b+3/Hi>B`*p+\M3**4^<&L/mr$";jO#KL0+a^X,^4@MT!bY1+9r3FeM>S+OhUUm
"Oui]A/dc'/-dBTi$r/]Ari,^Hal_NR>l]A<Zc_e2'a1]A9I!XB9?GCGEAGdf$_-8$>Zs@1a2bJi
V;,L#WF7G_o((\_:f4Tjm"3=]AIc/&h5b%aAmp?5a5VZR)gQ'm@8m,5(a34T[U,Cm(!55lm\E
7.A^R\M<Vt,_'Dq<UfCk3(?f`fn)FED=b*aKMKkqV:r?n69n.qW@PM;>Poq`^?E0'b-`V.BS
(\Q$p>kiX-<0^H_O3#pN;[PJdtQ@Fiq+\ZE,.G#5.L^+7Sd`;D@td*[W$9,DjB@&EU#;#,MC
VULbRR<Rs%&CVL2K@-.qXt;gtUdR[@9oda)=Z(KrP.O]AYYDhB]AKUic7[,KnZ#Mr82#13*cGA
E3Uk.9S-dSOQ>Jq36,c_HN[1p`2!(8:A$6RhC69'hf'QK_G;a;X^^-Wphrg#JB?j_*[?uZI4
>FbC9`Gq1tapG`C'Bm]Apt<R5Oo(]AkPgZsG:Ckse9)KncLAASn5<AdSafh^'+g)^pMV/I.#<-
.b+-<S&RA1s?4]ALJ_h2ahmdHH3hnO>"F1iiB7gZ(jEF%e.I3+TdH>s%gq/Y)qPbAg^O3*9"p
Y.&SAI6*K8(QAFS:CD!GZ9XYXJ9ANpO[h1LLqoZ2q?,sqGp#4an.Gu=d6L&YC>6bLJBq*U0C
3\ii]AZ6gmIj0eb)V[Cpe)!/ED4Fs/7c3:?!!8![@p[:a[2##O5r%T6i3^daXBd^&%_PDQa+7
Ql`Dl[q.3bk>Y5JCn&)8IpU4@g'?G<[KQ/7UkT\8m08'"%(4c<YReL_pSGlt$+Zg\0%Uu=LG
/G=h39?P^O?:+-#mVtEi@$KLi44nYCSjb]A,h)=d-WhpRhE<Gn\iP-nN4&B[bWnB)XpFY@`">
>45;\Y^$-f/rM%sn%jNrJX-pRfqt<,?;^DknMsc(gm/Gj;BWgt[Al/7Zr3'Mt0EIa*-fWI_?
%uBI[e-oQaYTc=GHuga5(7q@o/Zbh%i4*umJu'<ZD>j0>J'Fq;&NXf5A>GWX53.G[,`UkOSm
61B%r,7To*_#;n(ogLl]ABc+DKG7h>0OZDpZieTil0<Ka!!c8mL,<oi%;/=+CcnE$4b5*IHVr
?NGCWT(&O3`pFmRCj1hI%\'g53:@Wr%'jM;gU+h^^qZBT[;@`_(g('m_I=eL2+/F0Dnk%p+L
<h8lJ-B8b/bjGo%tZZfR8]AoT.V%d#i?n95:^*%I')CnNP#0/~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="78"/>
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
<Refresh class="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr" pluginID="com.fr.plugin.reportRefresh.v10">
<Refresh state="1" interval="2.0" refreshArea="" customClass="false"/>
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
<![CDATA[2895600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[7886700,7734300,6324600,6019800,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[货主城市]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="货主城市"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="0" s="2">
<O>
<![CDATA[运货费]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="运货费"/>
<Condition class="com.fr.data.condition.ListCondition"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Result>
<![CDATA[round($$$,0)]]></Result>
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
<FRFont name="微软雅黑" style="2" size="96" foreground="-16711681"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="2" size="96"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="2" size="96" foreground="-13382452"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94j=;cgEJ^?h(bk"bf)[aK9aoF7U5`[V$]A+&:@\12=3s+K9Jd)60Z*$m?d,lAWXJ7,cj';n
rqb7;=9IMD`&0ULHJ-a\cB,=+Z:N&HXtGON5K'll3;BEoL0?Dn`cSSNHXg-[Y6gJ*XSpqX*t
;j52Lso[!]A$4qJJFp<\uA$Wi(:h*m!N9f([V?XP2XrkGjpKuJk7/:?jPg]AJf"`sYPHG+e0ug
J4q[rJgb*GQ$b^c'!abo^*`2L@>-56VjnQG420;F$KWe;ou>dJJk#5s3<W[`BO:C-d!cD&"?
n57_V]A]A"CL7#]Ab"mlWXrs8%\o-C"_\boZQ&fti6aIo$W$c,rp[!hk`piJ-Y[CRX3!j$8V%nV
]AFkOu[\h;ch08\<_8;/-P*8\PT,kHZ$#PJTT&"L1pR5[Khej8l&pn4f<otk*@l6';hP>#$C$
V23^S&?,:nuHgY,ZoTjLN1/gS7t;?]A^O8\tZ*)`q09FM_MOS=nJ]A-<a&r,iSp3Uc0`9Yqq.g
Em\,k<\td)AqphPF.?U$6hR[%?2`ilg.eTRL;jW:#LZOO@o8N3lgE't.o]AlbE1O=okm0MOR2
hC`[qM5>!3/R>RXI5<K$;mX5fXRlCN=(QtHVKh[p:dh\N+7!'WM^?)S)XH_(H?&8j=X5_9u+
@ggpj%hNmn#?-%"#DX=,<Ja:XjMYWQ>ZY=9FWTkic^5IVJ[^L`c!\8g&\Spf%nA%RrWe8/Nd
RBZm%0W90.S71Hd5S/^$p.P@P]AWQeJ_mMrffk-ic&2Ak6BKXHU9Bb&C1FWWm!1UGM59[>h[>
;9LR(\5c"LQrI!,LrB6rufCC>bNqe_IHcHc`BPgrWleD-=_nk-UPJ(*:$+Fl7%FLut)&rF\'
W^Q4,D_B6QQ2R(uLlgiJ,<@-3jboKGTG8@T#5-W;TGU[NS`j_Eu[kFHr?/G0'_7gq%P6%2o$
\1t/]AXnUCarss[bRM10p%[bDKF.oJMe:Q!-q'^Bhq%QZb^JoQBXBWuW#fG&d`Tk/HZ'Im+dR
BCA!Q\s[Q%k@@Z\#:5f6/sWc.jd2?S-SKu_Bc)f,,eM`m&34BA7e?i^JFqh5:kc/F/\_e'H\
A1U5/Qg%j`l`1?%=E(I"Z0FkfLK#RJ=9Qk[0k,1tI(,hmF#4*c)+-eO.Uigim88_Q=atl!pr
><'4(CMsablfr.qX3Tmch/&9,V+7WcW62NKhL$@d2a+1J84kotd.)._kkgS+aJo)#DVkGQrA
jB.JA!l+0`M%%do#Ou-ImX(!R*IG:L5lka/f,3Lkn6d1u".g:/"$]A5`+W(OMF?Vi1fAQ*!J7
`B%M_iG2q;4GK1,m\m*41rJr)HJ(k8"^%"G1L]ATV/:g@_4h"P>m+p*"#gQCh9gq\3Mc`H>KZ
V@&fVa?@H+dKgL]AV%X]A+ne8+]A\;>u7!rV[;4`7H:Kp1B3.b#K\FKG@f_9h&3DmpCood\%@GN
>6s()8>9(q\\ba<Ad.8^'iSXWFtE0&YmA:JLE,7_IJ'),]A5lNpK^j&Q%cm?uUYAebns]A";Wb
%P;')5_M_3PkM8"h*;D.iHZ6U(#`k"BAr+EJ\V2EXNA6@Sf3$5c:&+IuHLJ.@T#mk+n$8a<K
f1u\kHc!A0<!kl(.%_7ROY$r*9=4;hOfu[CX7@ZD6T?Yt\H1qE8)`D^n#i!^h4G!D%b%+l^[
1nV2/_q#D-QZHZ5j2+rSh>c,$I;.s1T/R"@,?e:EMlF(<,uBdU5n?7r4PS=g>,`jHs#nk"oC
s,if!G42oN8.*;dA&jO10rOqZYT0LOTpeJm=jP61*tlg:cCSLt0=f?:_VMn!(QcAI0.2EbJJ
XHc@YbSRuQ[_UDs;V2k-A@5[H+8[QllUa&9m25=9Gs76D?L?`fnA/\MHa5Dlfeu=k#<#\nFU
:2HV*qQ+W94hN>s)s[&).-iO"XSp\1t[m;uP<*PZJM;4r/^[guGWB<(\`YZ!9Q3LP@""<!ht
l3"pM`5u5,81D3tFO5R#*FX\7,&G0!"2T!nXmHB0!S^W(fVLa)cMint.+*;K-O@\1%51.4Yf
"D'REC@!TBOA^L8md"TeX[nsJ08&O\B&CuEf(]Aa"oAPuUU=T`*5B0s_Y<J0^'=bSo5"ic-6'
$(gc.6*4hSJD?R"o6Z[2ti)"2/.k;j1qVfhhUjGuP/HF3tajn1413`GoE.[;=P3W<?W\(ecI
#@&:-r['b_>97[M95WUtOKHAJXL,t(]A2keFarQTu7)64D4or#_E#7!@nXpFHa`j]AP1O/gk'<
]A%#kI#DM:uDi)4)i_$IV]A-9g74j,<jOIhb>##eUKMXGq=^>tJ`Pf8X!5BS5YFfomZ&&E\Qij
42WBRektrkj:E<UhbI^Z`%ga3;gLO/oW8&!pFf(B<B6UF<98+:e#ht^+gb4egF4pFb%EulfH
Rs8-^/.M1mZOIgYj;ES@ZS<Rk(7n3lSh"WP!`6K4CY`hj&B.rD#"9kdsss0ouZ?&X8=[AKb*
fF5XJ,=&f/,nO-Xh/4M3nkE?bW'kJr1W\)B1!1),`&)obatZNo^Q+4_B^0(Z5*`PlHoBjok?
*sC1TG5T5G7-W2>/FBJjkn\Mk5+DCd"a%n,icDYhe,O]Aba?#RV&^,6^TcK9V*um-u:/km[#m
m';?Ml0/;Ic3hVekH0p[h^s8DD<.36(?PZb-jSNbB0bf;ZFn*&<fk@q/OV?[B;?1#TJHQdTW
k.Wp=Ra37)6J(El@18BRV>+tJUC"(#m!I#\"O8'>`4;FfaJ9"Ah&ap<AI:Fp'gC8h4qZ,(T%
;I2?$S`Bs0%G')(a*$+1?<DMb!c'XWb,+#(!MRVl,)e?H=M*3\iuBn[X2$RnP$E!7U^D(".q
d5=#\Ijp@"=A2-f0=R4*6Ie+aasBt0`]AjL;_YRCSX.nTsYUS_<M=\X7=jm;Q4iE_)skVDn@%
UK;33p;Rs2Yfe?>n9!mNbnG[:4HZtYRbK.KiL4d&pB$p(("Y[e:i+pO1RJ:r;X\l)c:@Zo5&
1($Hp#T08E]A2P;uP;6,ujh4^-/q\7A?'j]A]AP@8C4)Wu8(J$[&ilJ2RR+#cr!o+V2FgbsY&?_
E/pj%;@?iZU7'C5,l5iop@TRg5\gLY5J2Uks;L'n$Y!WOt+s/T>?G$>B/Hl_I!Cf%/I<anX,
:P8Q3&JOW9c5@$mI[-?,8/d([46@XHK3ZQi=<-h5c!68PdeSRNA4WBD>*!*/>\.uCcg:@m=l
NKd<atF3re#Q_L:ZJK$B8DWo^>Xc>9)%\@I);DsK.B2W1e,0Y+=eYH6X;_=@!c@$%7C\gd;E
rV^;*RIGJEQF";GH/jU/m;sO4,AKN7ITiL(I>JT^<90o<>#3)U(gFrR!RPJsoJ,B'b3Q4!Z"
\.Oo6$%@RC/8XmYKZ`qOomp0(_u>eG/G'#9Bsbc[C;jHfNM]AdR@s*1rK,Qk[=9jB\cNR4BAG
?\o>a@j(XPm/+[V!Hb.?L`*V=>!j?B@$T.@g4Hh0H`EDYalogn.'UT]An^fGpO0OQE0EFQ";i
LQ*e`Jh+_S89M\oh-c)Yd8]ArMst<KGeEes2qm9a`6C$]AkVI%NUF9Q"^Yr+7?d@<t-lJ:u$r<
#-\;IK6isPj]Ap3-1J=+WSUV14M37!\JlkD#.;SC8oV$]A6A(kj9IX#8e1'fSall"s\.G]AR"\g
$V)Ju"s?LH0:uUFnso%HGuoe^\nq?A$DJ''5-:Q5O$/c46tXl>d&Wh`TmMh,lr@$lDmQ]AA<=
G/>;:1G$92%n%\d.qMS^fi(@C5#6HEB8h#ma>P9CU;5=%1"d`(PmS\N]A@p*%qD/m(Cs2,+;!
#6V4ph):`6!S?lBa@6$rmG525E(bO-TNEa`q6MIouIWOj2kTX[8q[ZK,^1jJPXRkRP]Ap(n,m
H^_bP*9'JbX5De`o<WdO+R8^(g>.5rOo_@[dkM`4bRjh%^iJ;a,P8K!=YD:UZ^r</t>q:N@-
+n/Ei[l!q2>Y/:+&Rak]Ak6q-!+(<>k+6KdhG:%c&tlR)9WS+.#^':66]ASp@cPM!d1C`QH0-n
M\-'^S$EKUD%EfKSk`LI?a&8ah0&@LDTFte(#pE\T.(`7s4k`*`8A+g/cn"jSc?VC>5udXep
P5>kq/f'6=n,%2SO1;)TH?LhsXf"?[?C.rGUTgm&!2LqI&U99@'Mc#6>WY#.j%\PM0MJD>Gf
dp%M7qZ?JeC2KX.frPf(kJ0e#sf"^oW<WYNmRVX,9>.QoNmI(XGVuP'GDS'8u?l9=iZaZuJY
:ED_M%%_Y(^\I%^-DUP>n[7nY(#Ge?ZZMo>a)i6fF:r7l]AYBt4,0m/+X#)tL97*53:=4^S88
Y<<e6i_aRSQIR6an+%!K<R98X2Y*D5g,0k7Fl'?lpc*--!s8Qa8r!\%)O_'4m+UYRlFnNP+g
>s4QirXn$eoG`#'C\!D`E!4Ao;38LeS9Zq>f!.V9\__k`-3r:1)?nj@Z>F8p&7Wfa6ou!dg+
%\"W>#uAo!_MLU]A#qQ<Z;L5I22r)jejO_<NmG.ge]A%F.</R#`#im6e?,i17SN9#\_m^gFUM_
0b)H1V1]AH?8";6]Apbf5B$H4M0?j1cf+i<Q&h-1CLk$AfV@<7r"<G6?"o@3gHU\8IKejF^&n?
t-).,jKjO#)/L1cejpOI!@*+oAdYep2qWa9pN/h+u#DB@X@qIm6oFUll_+E#1@-/-m=k9#rZ
MoM9_1cjSBQ2"i5;NlL]AOAM">NbPVn"7f4M=PJZ:"hpUik*>i]Ab!N;#68+P2QQ'kgZQY3tBW
T%J!,a"Aj2UP!?f]Ao*[GN7Ls7iFWgDVt9LWAsjnmI87AM<1c&Y"PP#;`_R=Gk=SXkj\BeKk0
LJaYljADMT?QcPMkcFl*nQO2UdGSA`RZDculc6OIG)bbcUW&]AA=Qb:`n/!iE?r@"SYcCJLQ'
=JXgPG/=G]AO8g*:*\)/NVgP::$m$(4Q>c#NPlDg^/^(<\"qX%]AX^)`8E"63m:\^tFlcJPQ_1
M^N*>Cub&KP1c`D(=_X:^OB^=bcUKCYC+*\]AXuLNESMs#13TQ26tsg';`iTQf'V]AcB3eD`]AI
'*3=RP7N<t8iqeuP/X>::"67'l='#Yp+%Dkq)T;8`Gk7']A.#3V,7a]ADqV'Cddu=e?4^pkUmO
)eu,GJ&c=p$`Q;eRaoG#6tQ2'KM:mAJIH]Aik=tTY]Aortn7fq_Z!,?fjSkXZi@7hd*i<KMH@Z
h@c$i<9c_mdbAaTsn,MI<@Ej_r,\[<R<9Tsah=+G82!On>GV%Mt<O9+E?EiIF.hr`6O;QQ7:
i+F?m%>:E9Y5U0f.\h(WYjY`@['-^KG9T@.#E'RduC?+#<&jnCS++WIj/3Bi5$ae;()fp`B!
ALC3lE;N6qB+s[@O;FbrXJA^g_k@kc6>JMoNp<A`enfR%EkZ@UYMk@[O$1q4?jXY7EsF>-RK
&ZPF\[N_/X]AL_b*BpT@9M^l,jF7j.gsNWmZ[dhtAI"f.MJlIdWXd+.uiZ/dbnB2IdLJLJ3\,
&X5J&X"&!9KEKK*ihsgT^g:XtO!C`*CQe\t\a`GEqlC10Esj,:05gY5pb0`S18]A3O\65Eb$7
1HG4>c:;JTA8Y_Bn6`c9#(&P!24lWI01Z\S74F1JcC,g@V@.hO?gL9$_u?#R!TDSM5X>LF+m
/#1gihi$!0MhMKp($KIB*r`/G57dR*3VE@+m($l_>E-c:LS9h!83J;9T7sA9n1n3jISI-3mo
d)gp\ipJoHg3F29qU^L4*ch0Mu]Ap2CSr/eQqNi:TF4J<7:ek@.]Atj!?\Pl)Cn")7?ZLD^Q4X
l$a\j#1d-""]Ar?cq.PP>r`*)^SA?]A2]ArW;K^,c6^CqNIe0"s2i[4^LfqMdLOn)hU.[*\Q2Xj
6jQ<j#-d"kR6Q;sjEus><u#^^2-Ha484:=)$t3V[#a/:WkUEiH6qtbloF5)=b<J'as49pdaO
HeXn+oDS'!.uS0,+^,Wa[ADRbOsf=),7.h!]A-CO3f!f6R4i5o^9B?i;'9NfDd#=l<FZi=6cU
J7h:c'Z]A0[p.PoJ/IkQ(7Wmun4<aJ2K6766OKFNk<q81^MUB;,)W6pr<Q^"W[$[e9<DY_07f
AG?Y^A~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="373" width="650" height="62"/>
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
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=ds1.select("货主城市", 订单ID = 10001 + mod((datetonumber(now()) - datetonumber(a)) / 2000, 1000))]]></Attributes>
</O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="144" foreground="-14701083"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="2" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Attr showLine="false" autoAdjust="false" position="3" align="9" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="80" foreground="-13421773"/>
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Attr showLine="false" autoAdjust="false" position="5" align="9" isCustom="true" backgroundColor="16777215"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="0" foreground="-13421773"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
<![CDATA[#0]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<AFStyle colorStyle="0"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="false"/>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartGaugePlotAttr gaugeStyle="pointer"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="true" hingeColor="-10130579" hingeBackgroundColor="16777215" needleColor="-1740454" paneBackgroundColor="-197380" thermometerWidth="0.0" chutePercent="0.0" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="3" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=120" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=80" color="-9583361"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=40" color="-4791553"/>
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
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="0" foreground="33023"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false" gridLineType="solid"/>
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
<VanChartGaugeAxisAttr mainTickColor="-4539718" secTickColor="-1907998"/>
</gaugeAxis>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="货主城市" valueName="运货费" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="570dd328-a85e-4f06-b7da-99f30a42d76f"/>
<tools hidden="true" sort="false" export="false" fullScreen="false"/>
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<BoundsAttr x="0" y="0" width="960" height="462"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
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
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=ds1.select("货主城市", 订单ID = 10001 + mod((datetonumber(now()) - datetonumber(a)) / 2000, 1000))]]></Attributes>
</O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="144" foreground="-14701083"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="2" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Attr showLine="false" autoAdjust="false" position="3" align="9" isCustom="true"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="80" foreground="-13421773"/>
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Attr showLine="false" autoAdjust="false" position="5" align="9" isCustom="true" backgroundColor="16777215"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="Verdana" style="0" size="0" foreground="-13421773"/>
</Attr>
</TextAttr>
<AttrToolTipContent>
<Attr isCommon="true"/>
<value class="com.fr.plugin.chart.base.format.AttrTooltipValueFormat">
<AttrTooltipValueFormat>
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
<![CDATA[#0]]></Format>
</AttrTooltipValueFormat>
</value>
<percent class="com.fr.plugin.chart.base.format.AttrTooltipPercentFormat">
<AttrTooltipPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<AFStyle colorStyle="0"/>
<FillStyleName fillStyleName=""/>
<isCustomFillStyle isCustomFillStyle="false"/>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<VanChartGaugePlotAttr gaugeStyle="pointer"/>
<GaugeDetailStyle>
<GaugeDetailStyleAttr horizontalLayout="true" hingeColor="-10130579" hingeBackgroundColor="16777215" needleColor="-1740454" paneBackgroundColor="-197380" thermometerWidth="0.0" chutePercent="0.0" antiClockWise="true"/>
<MapHotAreaColor>
<MC_Attr minValue="0.0" maxValue="100.0" useType="0" areaNumber="3" mainColor="-14374913"/>
<ColorList>
<AreaColor>
<AC_Attr minValue="=80" maxValue="=120" color="-14374913"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=40" maxValue="=80" color="-9583361"/>
</AreaColor>
<AreaColor>
<AC_Attr minValue="=0" maxValue="=40" color="-4791553"/>
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
<newAxisAttr isShowAxisLabel="true"/>
<AxisLineStyle AxisStyle="1" MainGridStyle="1"/>
<newLineColor lineColor="-5197648"/>
<AxisPosition value="3"/>
<TickLine201106 type="2" secType="0"/>
<ArrowShow arrowShow="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="verdana" style="0" size="0" foreground="33023"/>
</Attr>
</TextAttr>
<AxisLabelCount value="=1"/>
<AxisRange/>
<AxisUnit201106 isCustomMainUnit="false" isCustomSecUnit="false" mainUnit="=0" secUnit="=0"/>
<ZoomAxisAttr isZoom="false"/>
<axisReversed axisReversed="false"/>
<VanChartAxisAttr mainTickLine="2" secTickLine="0" axisName="X轴" titleUseHtml="false" autoLabelGap="true" limitSize="false" maxHeight="15.0" commonValueFormat="true" isRotation="false" gridLineType="solid"/>
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
<VanChartGaugeAxisAttr mainTickColor="-4539718" secTickColor="-1907998"/>
</gaugeAxis>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="货主城市" valueName="运货费" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[ds1]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="570dd328-a85e-4f06-b7da-99f30a42d76f"/>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<Format class="com.fr.base.CoreDecimalFormat" roundingMode="6">
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
<BoundsAttr x="0" y="0" width="650" height="373"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="chart0"/>
<Widget widgetName="report0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="650" height="435"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="b1b9e069-e39d-42c6-8d27-d0bde8e52b4b"/>
</TemplateIdAttMark>
</Form>
