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
<![CDATA[SELECT * FROM S产品]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
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
<Margin top="5" left="5" bottom="5" right="5"/>
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
<LCAttr vgap="0" hgap="0" compInterval="5"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
	//鼠标经过时
	$(".x-table.REPORT1table tr").mousemove(function() {
		//单元格所在行字体颜色为红色
		$(this).css("color", "red");
		//单元格所在行背景为蓝色
		$(this).css("background-color", "blue");
		//单元格所在行字体加粗
		$(this).css("font-weight", "bold");
		//单元格所在行添加下划线
		$(this).css("text-decoration", "underline");
		//单元格所在行字体:11px  
		$(this).find("td").css("font-size", "11px");
	});

	//鼠标点击时
	$(".x-table.REPORT1table tr").mousedown(function() {
		//单元格所在行颜色为黄色
		$(this).css("color", "yellow");
		//单元格所在行背景为黑色
		$(this).css("background-color", "black");
		//单元格所在行字体加粗
		$(this).css("font-weight", "bold");
		//单元格所在行添加上划线
		$(this).css("text-decoration", "overline");
		//单元格所在行字体:13px  
		$(this).find("td").css("font-size", "13px");
	});

	//鼠标离开
	$(".x-table.REPORT1table tr").mouseout(function() {
		//单元格所在行字体颜色为黑色
		$(this).css("color", "black");
		//单元格所在行背景为白色
		$(this).css("background-color", "white");
		//单元格所在行字体正常
		$(this).css("font-weight", "normal");
		//单元格所在行无下划线
		$(this).css("text-decoration", "none");
		//单元格所在行字体:9px  
		$(this).find("td").css("font-size", "9px");
	});
}, 100);]]></Content>
</JavaScript>
</Listener>
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
<![CDATA[723900,838200,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2880000,2880000,2880000,2880000,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[产品名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[类别ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[单价]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[库存量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="类别ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="单价"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="库存量"/>
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
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m95!A'5+bnb:^/f+)ThN"U\t1[e=TWV.q_7P/M+C?!=#T$<;b$CFd@<U*9_kA/]AS`l]A7oICJ
tBlZ'M7&1#%RTKOh+5;F*hV+[R<H+s[^d+A<D'airO+gV#*sUZK)#H_3t1n9OLs^,b(phXd?
5S1bs8>Il))Eqj005JQ-C?<m,pIX_835JM\pm@F=KIg2[n5N1e7Csu%8(;-)?gt+h_]A@'VD1
C?/M1Ud1qpqauk^KpIRTAG(#C<l.e!Jphq/GeeV1QVMqa)c"3j8X;f%g%G>F5"*lqlGQ9a+I
^U[V;Ve(3Petg[;0*pV%P1$abA!C?t-Om5a1>peGlJpD%!4:75jr5msFmThY81pSJkcEI&d;
G8cTLYbo0`B-!.q4t#(1H3O50gSF1MP(0U7pIrc:CY2Hff^bf\#MPmGUeO"Wr3mBO]AT)gfrn
NL:8]A9LDrCOsm5#\)N3UuZ7-<<9bI6#!j&=XYO3L[PA-IXL'3X'#dU=$Qe4:#()1]A8ZLQeYa
?d+m/7g%C1&ki%;.2jp,!@@"_)jiGPd:]A$&R`d48Z+9k/h!GaIQW_$:-mHsMYWWS/TAJ*muI
`)ui<\70?:$Z@'D13@$,<+>BRZ2f*AJDEQQ7TH6Q(7L^$c,t6_6F]A+rH9>W?"=g6dU>a(\_9
L$2>4c(=q5Xs:2W:7iU3g[4Ef!G@r'UDGo`;Cq.1uUW,6O>X6l-gdPpo+GNa8QGdQOBof"D:
UE/=%`Yp`g6i!OnGX-HUWm'a'BNNFbb'E&,K'U-^W_Zeu_TJ**gSO<_,J,jeQuKfM;abIGU)
^o-GU-H4G/)dVW%K:>3S.A^@CjNMA/WS45EQi[:fe33o[r%^+3g?6=me.`W05!nf;o5q\%O_
db4.A2@3XrU88J#]A@u_l$Gc,?rBPTH>URWt#]A4cX`]AsnD`[=Mo5SDL"G,LNKV!&RQdU<H`&o
?Qk/<MZn5\X!=YgE^KomUsX&SG"4T@sT:K9tgUZQ_p-&*R_?-m`t(]A,X>idq<%dLc!=5Qg@M
!7l.oQ:^u(Uk2&E(r1$<ZO5HK!B.H;)jQX')<CpW/^[2mW]APDGo:SX*5!eu.bd;8:XiI(f2\
G$F'7$-RY=+1tiUZG/GqL)"p6;)rAEK=W>'PIXq3=fYrCXanAB"Z:!1K^$c%0J^pcZ;q%QaB
%&-lIjYRaZ<Xd10XVD+6.CMX<g0ZGA/>f<IGZRp4j;qoh.(Za92$Cl]Agf>*GD'<G:C^@M2AR
ODs?Ou-[pYd:7-`4R,9!sXQh/Q=%&d2B\u'*)*CO0P*i;-[sH-Bp:^AgKD]A<bD[>67pI(L/]A
pDDB?`e('\N$WuY5<3IQ7_KF\T\RVZoh>e=MiA>E)Qg>.!pa+oZ';g4sJ2+Y1*q=l#?XkS<D
CP.FoW;gHTmUb3jRJ;pj1l95VK<^MXa$'&9ZZGM<P#_h1=K7'A7,/&LBC8:6C,kV41AeV/+d
;00#2kXifWGK3kg%3Dk$nLb;N=h2M:VGCYC!.L/ultYMJ!r:9J&thT2%([q/\'Z?iiT<c<N3
PXchFX(UGVTR7ZZ(&<"#l?<7[6@]AA_,,Q1E>S6Kj`N$aG5FNJTPjtBgn,Paf_l\.@t&$Uf_,
P!"ZJ4LL'ddMHPTHb!XRiVGEI)$[:hKlpXpUK-D8qmUb_X^mpg=:`arY->IA?nJB<qo^(;`F
*GTB;o8[;JruM#(M$1M5tKdG?k'a=<=n;'@kBiYTYG4jN:-n>O%Y7/BR&XQ+-sm'C3daT&>U
3)4'RK9NZ`/3N[%rFbP[.:,O#6ml"NMi-^;1OieqNYN6Zhf3lnBj>YBu/-%slr!A#C.9<u5)
bNQbelZCG*C[r5,04)JLO1>d(2&QF?6^+N?&[+>KbV8sKcSi\O>eUj-\7d0fW%C4`_&4T:W_
9VM[d5CL3a3=?GpNl]AU4&V2pE=+!b?&Y`V#nJB33&f+#"#W<n\"kj2l^!7[#U[Ik9,,+B)_B
_*fd;_8B/cjhtJ[khoGhH?g=dK/*Cj"1)Sm`h,J%bYt8=(%m;0&Rc8+sP&2$9GO7,?0+Z#OH
WOKejUf&["DX$BA`Pb^[j8i]A1uXpRXX5tl'JoTqN55c5f\tZaKmg[!-Fb`,;;#DGK>6WPbO)
R(qlQG?4SrL<O8!_c@Dp;0C2FS!GijE=nF-<3_SqLckV[G\O4#VfR:E^@bZ(g/S1%#J>?;1?
bn>#WH/FBaq/Ujs/bfij^]A<pAd+d=85(_k>2f&f9<.p+eA-0FK9,N6iqjBpMHK`k6A?:bQcZ
X\Yk8U]A3S1\;(>aG`r+T7lVmK\THr'ff\\OpN5-s5)BJFB@@j^LNS&N4K'W-i8*n_Od7):+a
[qSo`TpGn]AjKA.]AO\id.*I3Htj[p&NOKOsL"m*"qebJ"YM+9<;#-"lP,P^HCAGLJD*f=f.+\
h/M1A;CML]A>7%QF+9l^&:H8:SlRPhk\9hkE]AoIj.SJ7tjU24Rd&<F6K'a+PM]Atb7+<SZXQC&
$W"?-o7l[h@`#s8Z_BRNCEU?HL^2d&;#9,^Ij:l!#RSC2lqS_[^F5N]A(F)T6AV=e.^$O*gV-
6aEW;`Dl@BbY]Adhi3m^Z;@-9Xs(;&-$ubN1!e!>MVoo;b8n\<qAf"Hd&Vae4&k(fFFl[8@S2
I8<>TJ`e";gAl5*J$i^]AsABM;a3tN+3MYE2nUG?@CD*<l0(f4;U(2($5']A_-sF-4^V_qK<.T
hke5>RL56\4D]AUUV>tT3%9_=ThCR#,X58O4\/pg+\H>0At-s%)!d[0Gr0cdqpV,4$N"8&,O)
'XgYPc>R2PE6)t7!qKmllPjc7[If8`>]Ab)gD$M+hR+p9I"\C$=dTXBKIs0@!l*Hq!d8cF-#!
Tq8qG;VA>?'h^@G)p",'=WTQhS^3\-!9>K;6#4a&_cAQUJ]A]AWMq"nXr'0UVNob^c2[YXb`,,
:/M@SZd@uDVpF0P'*&j)dKGi%ch8,/Hs`JIL!seljj!)W2S75<YM(%H$ifAS)7(^"nD$^MLj
\_MXspQ>(d,m\.tNFofIVE+^gk,OKRCi%J!Pk;*1:.tJ"-40e+[\_>o86]AG,[FC@US`b)IJ*
tY_/V"X<]Aimj8kUMM"kWq>7E^$a.T[VDjeS?>V+OC866@60qe;^53,.?C]AGq=:V!LBIGA;6p
F)Pk]A'T3k-8T%TR.8sBXW''@%.<BbTpD'9fM<$*(A)=eh^SfmTr\OOac#DDQNq>(D@Okn]A^0
CM!%*UKKW)e3DW#Ja:t'?5LA*QFI9/+LIN0Vh^BR$fP+HnYb26Rd)8uq#Z$LuNEUH7D?4Z[<
goZ596?b=BnToaukh\T]AaiusG#G)pMX<DW32M9SW=?Z_CbrfAKP,(OhP^ikTLJ&&I")a]A5=9
qs_i8s.iIrar1RaeW[l(r++%okn>gu4!X:IY#%jI9UdXm2-?7?dlnd#4U3F47:T\11t<5o3u
<Jo6_I+%0;VC%[n4@m%*,)tK7QZ-bm]A2L9o4nMJ5[qK_ULs/r#M4KLfbPHY.0e3O@Dj1?'/`
D+?<]A,0]A4d&*C>/(]As&)WY>Cmi'mA9MLa]ABME_DBS6O-`-Fc*2P@f3[t?@)\5M\"SGYKn(Z`
Td^j79Hk0gDcY%1U>8?jgqR_@!,j&pfXk]A33WNudHV*Kf9,m(e7f'g-D?',)M<4Q(K,JFA-u
0F*-Ji7F(HDY>QS)^=S'4Br/%gW@<nUY1`=M*'oTO2.6L@S<+EaY$R#n5G)@W!?bg&g\>5jq
V!q@"l5Bh>jqVa0-XlVC"$jU%FgS!dc(u\ds%j*V9g7(kD%L9)GjQoNLp\9E;\AHdb/jqlIL
m2p]AI-_*VQoHkPC>gBVeLPci^QIg[7?eLRhA_@ARK\^inOc9gp:3V?;&`?b;,L&aPML`ubt,
DLPq>[Wtu(W,\h1G/ca>,Ki$q*H0H$HVg$%!g+kiA2ld/p=)mIE4T)i'MZ42]AO1`P_kq?>V1
WsiQ]A4*p3P:ReR(6K\Tu(4\D5H3atGW<^`O3PgYh&):q:`lM\hGBSo9L>$M#3+iFHi:+Xr.m
A%\InYq6Sh7s0u;D?^`,Fq3B'B"hCVHOA)0CkUt[9q\DZCI'.hfhaW0o6$;4j[cBOH:FhF4h
WcPXZ+F+;)57=9r@TE\lmu-!8XF2MVp+[JJDWiQ7jA8#=bo2?GYK'Cl]AftA@9'+$n:FZ]AE^d
cW<9ci]AEDK@&L_/I<7/8j->@rn_i$5TUh:"pagG>qK3IG.4SL_qN[LZdYncGHKLKb8o;l8#e
Zn1EAs+L]Al`_,LDlN?K1b-&e4H8ao6;?_nnL_gn]A`Rt>QJS9]A+8s'EkN2qtL:8H4U+5NB$H-
d=g?-nl0a3Du4n#7"MGtMN,ej`Ncoq6^\IH$rJC1@d;`rlF>8f3&NW&-(KfnsKog5'gKdoMf
P=RH%fIa*cY^O>7Qr36ed("4'Gc6J_?a\tELXGDqV_cInMbKb/cPFFiLq++5`X2]APqI^V\@a
#]A7mRF&R_Z^9PYC#3[!gtlJ'e(g^`b>SpXKODHJpf1?6s(AiWkDgVrof!"^S?="4h[?NoD?'
G9!U4C%D4U(Th\)lp5S8?F8I\=0Ca<h(3%u.S5gp&+cb\'4PKpUmZ[4VAgK/_;3pS!op0)\b
H_4HXI:.klDu>/`u[(]A.pn(5^WD&sRRfk-"D7MAT:E*o<p[T:01h\!GOm?4MXgbs[[\)`o68
Nt2#CEcaCugp7h.ADI)Ti3o<-T4V_"p.3_=ff]A./C0C('8ud\qGJEK^G@fLD1Chc%0q^I6;>
IWl%W62,FWhHQ0TE)kFN.c76OpbM-JdCf=YS(dQ=,7hia8rd^B1XF:Y/ih%'7.LNNqrH&FS(
95nN>b0p?53Ve]AAL?'R%T()#Z=XO)EPr:NVG8LABgm\q;[N<bSr>Q4=Q!%V#L@L_K<GC"3`e
JN*%`0XW;9k82p?SGuV&WO[r47P,+++Gm)E(UZN,`%"UR*a::"r#,bmc[?5cVTi\1Z&$lCfn
S=QP><0KlUUb$P:qZ9FS2AGIR)>l=^hp75:u[9X/E8N,D]ATd>GkC)-)QZ>/6G(6[S/G!`_pN
$gUS4J'IuQ@?T%+1e+%[k0B7s>u=h"j=-u6Kt'#-\bTZo=C>"H!q+N2%bf/!<<4N0UI2>kZK
etF4.1"GM:He0_MM^\9_9-=#i>r@cB:UVn+g3Z1!3gOqP$gu_Bo15:&-#2s]Ad"(+Srsm]AgSr
B+gge[P]APglA4)UJ7qQB#I-,A9)1ptk;V,AHpih"gI>O'XlMcVi#E>8.:1";K9rpi1f2bQ]A(
F7uR^UWW[So&D^S"d_,O]AfA>R"Ui9^n:[TXeIN!o6TpMj`VeGn%T#6#ARVHg6pS&22br!hJ_
GM)N"!e\fK?*k0/C'DH_c2TKE=p`,2s#N>_68$I29"9tn'1Z2d2p]A[Z:%NoT_rT)7/ObQ5F1
i-7P7WFZA),fP"V@gah`f'@):(,pi/#W7NdT<[E$`G&Sk8Fs0Duhi;e!c@F'N*FQM760(Q0N
Y0^oj*d#pST>IL-:"Y#:&`R(T<D8=T`2%/2V$o3ehZ(+fRF1ib.'[c%gj\56,/N;J(+,4ghE
+\LC)>8'c"@+)P\?[i!-clpB*K6DXV1QVIt`M&*A/XqpCl`EZ74`JK[a7u:b$[b<9cu<MKe0
(NGg_7G24SZ7pQ]A2%jo4[3s5(HW"qJ<!O0]A:"i/?3Kk\Lnr`D>9,0o)bbO_OVB>E4-j`5"VA
K@aT$AMV?`8kRAl,G8Q/]A-<G?EL0iG$kVApJCQ&Od7cTr>s2j(l&o>VQ1Lbrh=44)/K^IcHT
c.dI.2o?SmUDCOu0Oe=sqWL4UF"0!TTO9t#*Dkk-$o<=_N'rL%^V=ZU;fjF>D]ATS1tRBCLi[
[(a89fB)mq.:gehNo;&#DH,'fOJI2TC4jfmPa#'\R#la&PY2`OYsKakW)6#SSi2V@n4\_()W
;5/Xr%4A<J=9<&YD+XB^T-QaEXObHgkl%eRdFn>S1eJkBq'nZ[-R,<Toqm?tcE%[ZQ;(e51m
O4e9_+X$l=16,Qtd%M.#DBjJr@:Zr<VC1pQaA)"M4He2!lrR2HVnA_)oZNa+P1I%ec74T8TU
?GrY$dA=b]A">Qj_HP>i=q6<t^U$F8]A.N!SY$d#^#@<>:c[K*r2>!0]A`W><@BnUY]A:a_8!9d#
Hq!eK":$-@74,YpMq8$e/0C2X35KP6uPf"n0H`,?45M:h9[jKUr^XgFe(=a:ACK5R`]AMCG;O
:7H6nPJ[C--mfBiY0V]A88gh.3mVpMHW_MJR@hO2]AWhkt8fCe\CP+Uq`0:a=?J'hL#KV:(+I$
At+bX.<?$#TUTViBq5Pu3-W^SO)G%8s3r:^"bf[/JHchGnRnpq$&dLu/^rJ0qYns/g!BL%Hp
OkF!cp60EAu#uWPolmQV:`:A^uQOnKWg?kH<I`@JOGp5O=1F@Z2,PP9P#bs+oBBsin"kb989
Ao\s^2.J%D@)se7VsZG1T9Wl>p!+1-S!3]AA=_@'K5"k:^5M'DVc5G.eT$RNj1QGJ]A[mjH-%K
19)M8T>5c"V0V8I;NOTSIsbLRSfQ<q\+rh7Y/D_;Z[@(Kl?@p#O>-gmu%kalPPNI.Z8Vs%jk
'3,8I)e&,TD0?iiAAmg$W9XbYUco]AS#ZK'k:9a2`Ei;MQ:4GK[<g4Hh+lsJ1,qJlj1$p6L_,
rNup@b7b0+GJU=F/!4$(61=Jq.[b[j>sd*S-D['(0r(H(GkF"MTncT%!Fm1fk"5*?Xdm7PT=
FAG=N4NTD>fqhNo8QIW!7nH[S-(d[(=':%lbY$;iaH@*BG8.S"5F^ns(4%VXf)Z#$RE<i2.(
T?Bo[-f0%TU&D9X$uEp1f'(SX0bl14,]A[YCT^E;L#136s6TF9KO16&,&X._e2?Y.Gda1e)[?
h98R7L$o%B=:Rc2C?]Ali4ERc-'UfMr(`)hAIc[Bdr&g@CFQNuUGo,;fh!03>$uQb`t@qILpO
Q($b+p=!Om.=n2fC:M'[>p(a<QTVbYUE)'.+XtYIZ(;%@0;GAQlh6M&O3u1B7UFV)i4@a[=\
ChqSrhC8=A#LH<a#j+Fu:Q1;;(3Wfkj4'5;?&K=;L?T5"mc!9$r&Q4*7'/Ol76SGZ2j)o^sW
kH^_rk6<4%a<-8r80Z(]A]AT]A!n3XWrmEg;b=g"5clJ6:650/TCO2e(h:cM887lVeP!WKH0>Ke
%LmO>X]A@qQV[d1JrtL:,F)@-VUMGde`&/!hh0r-ooDd_i`+MBg;3/5jc$qp_Ii(H`er@[$6f
!DoH<7LRf[72RSda;GLY!J')65U_s'6%:/A8OR7p(sD4UV`U58i+Te9qr>SkjJQq+B-^^+Jt
i.LC>%":J@BCp&+b4jPC!:&G.#(U7<G3hNk.iN=Q!)KJ@A-DL>#Ogq@]AU9>`6P@<*IJ9]A]Ah#
t"LP[L9cKrN&%LUOpm4J7n:QQ85%>PZ'g0=b3mhFEEBH1gFB`pB>;'\CA+nql=WDQW1%I5a@
8K!"#sXL;8@Q-CD2g##l4&7iq;oh/8*bB([6=U9%Tj'=MkkNE(aUM,^ZI<C+$o\ND<DI[doa
@P1fd"12.q3I.#p,A+lNNRsFIgg^LSfV-?(9-Q$BH%'(P&:cg.I.@-bZ&pfcILZZOF,A%$9+
Oa7"7ki+oYsJ+Sf@CL]AaDd[Or9!1JfF5OQIC9^sG1nkND,??Mq3)OZ<b>!u.uFG_.a7Dg_+X
$ePtd*.6&4N'R#f[t!q1NR(S/iXq+&B[\SB(C_e:1rg%0%`:CQVh,CZ#>sBSYN.4BmdBTfIc
T[g(R?[7o).fXrsJ~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="500" y="0" width="500" height="600"/>
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
<![CDATA[723900,838200,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2880000,2880000,2880000,2880000,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[产品名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[类别ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[单价]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[库存量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="类别ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="单价"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="库存量"/>
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
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m95!A'5+bnb:^/f+)ThN"U\t1[e=TWV.q_7P/M+C?!=#T$<;b$CFd@<U*9_kA/]AS`l]A7oICJ
tBlZ'M7&1#%RTKOh+5;F*hV+[R<H+s[^d+A<D'airO+gV#*sUZK)#H_3t1n9OLs^,b(phXd?
5S1bs8>Il))Eqj005JQ-C?<m,pIX_835JM\pm@F=KIg2[n5N1e7Csu%8(;-)?gt+h_]A@'VD1
C?/M1Ud1qpqauk^KpIRTAG(#C<l.e!Jphq/GeeV1QVMqa)c"3j8X;f%g%G>F5"*lqlGQ9a+I
^U[V;Ve(3Petg[;0*pV%P1$abA!C?t-Om5a1>peGlJpD%!4:75jr5msFmThY81pSJkcEI&d;
G8cTLYbo0`B-!.q4t#(1H3O50gSF1MP(0U7pIrc:CY2Hff^bf\#MPmGUeO"Wr3mBO]AT)gfrn
NL:8]A9LDrCOsm5#\)N3UuZ7-<<9bI6#!j&=XYO3L[PA-IXL'3X'#dU=$Qe4:#()1]A8ZLQeYa
?d+m/7g%C1&ki%;.2jp,!@@"_)jiGPd:]A$&R`d48Z+9k/h!GaIQW_$:-mHsMYWWS/TAJ*muI
`)ui<\70?:$Z@'D13@$,<+>BRZ2f*AJDEQQ7TH6Q(7L^$c,t6_6F]A+rH9>W?"=g6dU>a(\_9
L$2>4c(=q5Xs:2W:7iU3g[4Ef!G@r'UDGo`;Cq.1uUW,6O>X6l-gdPpo+GNa8QGdQOBof"D:
UE/=%`Yp`g6i!OnGX-HUWm'a'BNNFbb'E&,K'U-^W_Zeu_TJ**gSO<_,J,jeQuKfM;abIGU)
^o-GU-H4G/)dVW%K:>3S.A^@CjNMA/WS45EQi[:fe33o[r%^+3g?6=me.`W05!nf;o5q\%O_
db4.A2@3XrU88J#]A@u_l$Gc,?rBPTH>URWt#]A4cX`]AsnD`[=Mo5SDL"G,LNKV!&RQdU<H`&o
?Qk/<MZn5\X!=YgE^KomUsX&SG"4T@sT:K9tgUZQ_p-&*R_?-m`t(]A,X>idq<%dLc!=5Qg@M
!7l.oQ:^u(Uk2&E(r1$<ZO5HK!B.H;)jQX')<CpW/^[2mW]APDGo:SX*5!eu.bd;8:XiI(f2\
G$F'7$-RY=+1tiUZG/GqL)"p6;)rAEK=W>'PIXq3=fYrCXanAB"Z:!1K^$c%0J^pcZ;q%QaB
%&-lIjYRaZ<Xd10XVD+6.CMX<g0ZGA/>f<IGZRp4j;qoh.(Za92$Cl]Agf>*GD'<G:C^@M2AR
ODs?Ou-[pYd:7-`4R,9!sXQh/Q=%&d2B\u'*)*CO0P*i;-[sH-Bp:^AgKD]A<bD[>67pI(L/]A
pDDB?`e('\N$WuY5<3IQ7_KF\T\RVZoh>e=MiA>E)Qg>.!pa+oZ';g4sJ2+Y1*q=l#?XkS<D
CP.FoW;gHTmUb3jRJ;pj1l95VK<^MXa$'&9ZZGM<P#_h1=K7'A7,/&LBC8:6C,kV41AeV/+d
;00#2kXifWGK3kg%3Dk$nLb;N=h2M:VGCYC!.L/ultYMJ!r:9J&thT2%([q/\'Z?iiT<c<N3
PXchFX(UGVTR7ZZ(&<"#l?<7[6@]AA_,,Q1E>S6Kj`N$aG5FNJTPjtBgn,Paf_l\.@t&$Uf_,
P!"ZJ4LL'ddMHPTHb!XRiVGEI)$[:hKlpXpUK-D8qmUb_X^mpg=:`arY->IA?nJB<qo^(;`F
*GTB;o8[;JruM#(M$1M5tKdG?k'a=<=n;'@kBiYTYG4jN:-n>O%Y7/BR&XQ+-sm'C3daT&>U
3)4'RK9NZ`/3N[%rFbP[.:,O#6ml"NMi-^;1OieqNYN6Zhf3lnBj>YBu/-%slr!A#C.9<u5)
bNQbelZCG*C[r5,04)JLO1>d(2&QF?6^+N?&[+>KbV8sKcSi\O>eUj-\7d0fW%C4`_&4T:W_
9VM[d5CL3a3=?GpNl]AU4&V2pE=+!b?&Y`V#nJB33&f+#"#W<n\"kj2l^!7[#U[Ik9,,+B)_B
_*fd;_8B/cjhtJ[khoGhH?g=dK/*Cj"1)Sm`h,J%bYt8=(%m;0&Rc8+sP&2$9GO7,?0+Z#OH
WOKejUf&["DX$BA`Pb^[j8i]A1uXpRXX5tl'JoTqN55c5f\tZaKmg[!-Fb`,;;#DGK>6WPbO)
R(qlQG?4SrL<O8!_c@Dp;0C2FS!GijE=nF-<3_SqLckV[G\O4#VfR:E^@bZ(g/S1%#J>?;1?
bn>#WH/FBaq/Ujs/bfij^]A<pAd+d=85(_k>2f&f9<.p+eA-0FK9,N6iqjBpMHK`k6A?:bQcZ
X\Yk8U]A3S1\;(>aG`r+T7lVmK\THr'ff\\OpN5-s5)BJFB@@j^LNS&N4K'W-i8*n_Od7):+a
[qSo`TpGn]AjKA.]AO\id.*I3Htj[p&NOKOsL"m*"qebJ"YM+9<;#-"lP,P^HCAGLJD*f=f.+\
h/M1A;CML]A>7%QF+9l^&:H8:SlRPhk\9hkE]AoIj.SJ7tjU24Rd&<F6K'a+PM]Atb7+<SZXQC&
$W"?-o7l[h@`#s8Z_BRNCEU?HL^2d&;#9,^Ij:l!#RSC2lqS_[^F5N]A(F)T6AV=e.^$O*gV-
6aEW;`Dl@BbY]Adhi3m^Z;@-9Xs(;&-$ubN1!e!>MVoo;b8n\<qAf"Hd&Vae4&k(fFFl[8@S2
I8<>TJ`e";gAl5*J$i^]AsABM;a3tN+3MYE2nUG?@CD*<l0(f4;U(2($5']A_-sF-4^V_qK<.T
hke5>RL56\4D]AUUV>tT3%9_=ThCR#,X58O4\/pg+\H>0At-s%)!d[0Gr0cdqpV,4$N"8&,O)
'XgYPc>R2PE6)t7!qKmllPjc7[If8`>]Ab)gD$M+hR+p9I"\C$=dTXBKIs0@!l*Hq!d8cF-#!
Tq8qG;VA>?'h^@G)p",'=WTQhS^3\-!9>K;6#4a&_cAQUJ]A]AWMq"nXr'0UVNob^c2[YXb`,,
:/M@SZd@uDVpF0P'*&j)dKGi%ch8,/Hs`JIL!seljj!)W2S75<YM(%H$ifAS)7(^"nD$^MLj
\_MXspQ>(d,m\.tNFofIVE+^gk,OKRCi%J!Pk;*1:.tJ"-40e+[\_>o86]AG,[FC@US`b)IJ*
tY_/V"X<]Aimj8kUMM"kWq>7E^$a.T[VDjeS?>V+OC866@60qe;^53,.?C]AGq=:V!LBIGA;6p
F)Pk]A'T3k-8T%TR.8sBXW''@%.<BbTpD'9fM<$*(A)=eh^SfmTr\OOac#DDQNq>(D@Okn]A^0
CM!%*UKKW)e3DW#Ja:t'?5LA*QFI9/+LIN0Vh^BR$fP+HnYb26Rd)8uq#Z$LuNEUH7D?4Z[<
goZ596?b=BnToaukh\T]AaiusG#G)pMX<DW32M9SW=?Z_CbrfAKP,(OhP^ikTLJ&&I")a]A5=9
qs_i8s.iIrar1RaeW[l(r++%okn>gu4!X:IY#%jI9UdXm2-?7?dlnd#4U3F47:T\11t<5o3u
<Jo6_I+%0;VC%[n4@m%*,)tK7QZ-bm]A2L9o4nMJ5[qK_ULs/r#M4KLfbPHY.0e3O@Dj1?'/`
D+?<]A,0]A4d&*C>/(]As&)WY>Cmi'mA9MLa]ABME_DBS6O-`-Fc*2P@f3[t?@)\5M\"SGYKn(Z`
Td^j79Hk0gDcY%1U>8?jgqR_@!,j&pfXk]A33WNudHV*Kf9,m(e7f'g-D?',)M<4Q(K,JFA-u
0F*-Ji7F(HDY>QS)^=S'4Br/%gW@<nUY1`=M*'oTO2.6L@S<+EaY$R#n5G)@W!?bg&g\>5jq
V!q@"l5Bh>jqVa0-XlVC"$jU%FgS!dc(u\ds%j*V9g7(kD%L9)GjQoNLp\9E;\AHdb/jqlIL
m2p]AI-_*VQoHkPC>gBVeLPci^QIg[7?eLRhA_@ARK\^inOc9gp:3V?;&`?b;,L&aPML`ubt,
DLPq>[Wtu(W,\h1G/ca>,Ki$q*H0H$HVg$%!g+kiA2ld/p=)mIE4T)i'MZ42]AO1`P_kq?>V1
WsiQ]A4*p3P:ReR(6K\Tu(4\D5H3atGW<^`O3PgYh&):q:`lM\hGBSo9L>$M#3+iFHi:+Xr.m
A%\InYq6Sh7s0u;D?^`,Fq3B'B"hCVHOA)0CkUt[9q\DZCI'.hfhaW0o6$;4j[cBOH:FhF4h
WcPXZ+F+;)57=9r@TE\lmu-!8XF2MVp+[JJDWiQ7jA8#=bo2?GYK'Cl]AftA@9'+$n:FZ]AE^d
cW<9ci]AEDK@&L_/I<7/8j->@rn_i$5TUh:"pagG>qK3IG.4SL_qN[LZdYncGHKLKb8o;l8#e
Zn1EAs+L]Al`_,LDlN?K1b-&e4H8ao6;?_nnL_gn]A`Rt>QJS9]A+8s'EkN2qtL:8H4U+5NB$H-
d=g?-nl0a3Du4n#7"MGtMN,ej`Ncoq6^\IH$rJC1@d;`rlF>8f3&NW&-(KfnsKog5'gKdoMf
P=RH%fIa*cY^O>7Qr36ed("4'Gc6J_?a\tELXGDqV_cInMbKb/cPFFiLq++5`X2]APqI^V\@a
#]A7mRF&R_Z^9PYC#3[!gtlJ'e(g^`b>SpXKODHJpf1?6s(AiWkDgVrof!"^S?="4h[?NoD?'
G9!U4C%D4U(Th\)lp5S8?F8I\=0Ca<h(3%u.S5gp&+cb\'4PKpUmZ[4VAgK/_;3pS!op0)\b
H_4HXI:.klDu>/`u[(]A.pn(5^WD&sRRfk-"D7MAT:E*o<p[T:01h\!GOm?4MXgbs[[\)`o68
Nt2#CEcaCugp7h.ADI)Ti3o<-T4V_"p.3_=ff]A./C0C('8ud\qGJEK^G@fLD1Chc%0q^I6;>
IWl%W62,FWhHQ0TE)kFN.c76OpbM-JdCf=YS(dQ=,7hia8rd^B1XF:Y/ih%'7.LNNqrH&FS(
95nN>b0p?53Ve]AAL?'R%T()#Z=XO)EPr:NVG8LABgm\q;[N<bSr>Q4=Q!%V#L@L_K<GC"3`e
JN*%`0XW;9k82p?SGuV&WO[r47P,+++Gm)E(UZN,`%"UR*a::"r#,bmc[?5cVTi\1Z&$lCfn
S=QP><0KlUUb$P:qZ9FS2AGIR)>l=^hp75:u[9X/E8N,D]ATd>GkC)-)QZ>/6G(6[S/G!`_pN
$gUS4J'IuQ@?T%+1e+%[k0B7s>u=h"j=-u6Kt'#-\bTZo=C>"H!q+N2%bf/!<<4N0UI2>kZK
etF4.1"GM:He0_MM^\9_9-=#i>r@cB:UVn+g3Z1!3gOqP$gu_Bo15:&-#2s]Ad"(+Srsm]AgSr
B+gge[P]APglA4)UJ7qQB#I-,A9)1ptk;V,AHpih"gI>O'XlMcVi#E>8.:1";K9rpi1f2bQ]A(
F7uR^UWW[So&D^S"d_,O]AfA>R"Ui9^n:[TXeIN!o6TpMj`VeGn%T#6#ARVHg6pS&22br!hJ_
GM)N"!e\fK?*k0/C'DH_c2TKE=p`,2s#N>_68$I29"9tn'1Z2d2p]A[Z:%NoT_rT)7/ObQ5F1
i-7P7WFZA),fP"V@gah`f'@):(,pi/#W7NdT<[E$`G&Sk8Fs0Duhi;e!c@F'N*FQM760(Q0N
Y0^oj*d#pST>IL-:"Y#:&`R(T<D8=T`2%/2V$o3ehZ(+fRF1ib.'[c%gj\56,/N;J(+,4ghE
+\LC)>8'c"@+)P\?[i!-clpB*K6DXV1QVIt`M&*A/XqpCl`EZ74`JK[a7u:b$[b<9cu<MKe0
(NGg_7G24SZ7pQ]A2%jo4[3s5(HW"qJ<!O0]A:"i/?3Kk\Lnr`D>9,0o)bbO_OVB>E4-j`5"VA
K@aT$AMV?`8kRAl,G8Q/]A-<G?EL0iG$kVApJCQ&Od7cTr>s2j(l&o>VQ1Lbrh=44)/K^IcHT
c.dI.2o?SmUDCOu0Oe=sqWL4UF"0!TTO9t#*Dkk-$o<=_N'rL%^V=ZU;fjF>D]ATS1tRBCLi[
[(a89fB)mq.:gehNo;&#DH,'fOJI2TC4jfmPa#'\R#la&PY2`OYsKakW)6#SSi2V@n4\_()W
;5/Xr%4A<J=9<&YD+XB^T-QaEXObHgkl%eRdFn>S1eJkBq'nZ[-R,<Toqm?tcE%[ZQ;(e51m
O4e9_+X$l=16,Qtd%M.#DBjJr@:Zr<VC1pQaA)"M4He2!lrR2HVnA_)oZNa+P1I%ec74T8TU
?GrY$dA=b]A">Qj_HP>i=q6<t^U$F8]A.N!SY$d#^#@<>:c[K*r2>!0]A`W><@BnUY]A:a_8!9d#
Hq!eK":$-@74,YpMq8$e/0C2X35KP6uPf"n0H`,?45M:h9[jKUr^XgFe(=a:ACK5R`]AMCG;O
:7H6nPJ[C--mfBiY0V]A88gh.3mVpMHW_MJR@hO2]AWhkt8fCe\CP+Uq`0:a=?J'hL#KV:(+I$
At+bX.<?$#TUTViBq5Pu3-W^SO)G%8s3r:^"bf[/JHchGnRnpq$&dLu/^rJ0qYns/g!BL%Hp
OkF!cp60EAu#uWPolmQV:`:A^uQOnKWg?kH<I`@JOGp5O=1F@Z2,PP9P#bs+oBBsin"kb989
Ao\s^2.J%D@)se7VsZG1T9Wl>p!+1-S!3]AA=_@'K5"k:^5M'DVc5G.eT$RNj1QGJ]A[mjH-%K
19)M8T>5c"V0V8I;NOTSIsbLRSfQ<q\+rh7Y/D_;Z[@(Kl?@p#O>-gmu%kalPPNI.Z8Vs%jk
'3,8I)e&,TD0?iiAAmg$W9XbYUco]AS#ZK'k:9a2`Ei;MQ:4GK[<g4Hh+lsJ1,qJlj1$p6L_,
rNup@b7b0+GJU=F/!4$(61=Jq.[b[j>sd*S-D['(0r(H(GkF"MTncT%!Fm1fk"5*?Xdm7PT=
FAG=N4NTD>fqhNo8QIW!7nH[S-(d[(=':%lbY$;iaH@*BG8.S"5F^ns(4%VXf)Z#$RE<i2.(
T?Bo[-f0%TU&D9X$uEp1f'(SX0bl14,]A[YCT^E;L#136s6TF9KO16&,&X._e2?Y.Gda1e)[?
h98R7L$o%B=:Rc2C?]Ali4ERc-'UfMr(`)hAIc[Bdr&g@CFQNuUGo,;fh!03>$uQb`t@qILpO
Q($b+p=!Om.=n2fC:M'[>p(a<QTVbYUE)'.+XtYIZ(;%@0;GAQlh6M&O3u1B7UFV)i4@a[=\
ChqSrhC8=A#LH<a#j+Fu:Q1;;(3Wfkj4'5;?&K=;L?T5"mc!9$r&Q4*7'/Ol76SGZ2j)o^sW
kH^_rk6<4%a<-8r80Z(]A]AT]A!n3XWrmEg;b=g"5clJ6:650/TCO2e(h:cM887lVeP!WKH0>Ke
%LmO>X]A@qQV[d1JrtL:,F)@-VUMGde`&/!hh0r-ooDd_i`+MBg;3/5jc$qp_Ii(H`er@[$6f
!DoH<7LRf[72RSda;GLY!J')65U_s'6%:/A8OR7p(sD4UV`U58i+Te9qr>SkjJQq+B-^^+Jt
i.LC>%":J@BCp&+b4jPC!:&G.#(U7<G3hNk.iN=Q!)KJ@A-DL>#Ogq@]AU9>`6P@<*IJ9]A]Ah#
t"LP[L9cKrN&%LUOpm4J7n:QQ85%>PZ'g0=b3mhFEEBH1gFB`pB>;'\CA+nql=WDQW1%I5a@
8K!"#sXL;8@Q-CD2g##l4&7iq;oh/8*bB([6=U9%Tj'=MkkNE(aUM,^ZI<C+$o\ND<DI[doa
@P1fd"12.q3I.#p,A+lNNRsFIgg^LSfV-?(9-Q$BH%'(P&:cg.I.@-bZ&pfcILZZOF,A%$9+
Oa7"7ki+oYsJ+Sf@CL]AaDd[Or9!1JfF5OQIC9^sG1nkND,??Mq3)OZ<b>!u.uFG_.a7Dg_+X
$ePtd*.6&4N'R#f[t!q1NR(S/iXq+&B[\SB(C_e:1rg%0%`:CQVh,CZ#>sBSYN.4BmdBTfIc
T[g(R?[7o).fXrsJ~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="500" y="0" width="500" height="600"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
	//鼠标经过时
	$(".x-table.REPORT0table td").mousemove(function() {
		//所在单元格字体颜色为红色
		$(this).css("color", "red");
		//所在单元格背景为蓝色
		$(this).css("background-color", "blue");
		//所在单元格字体加粗
		$(this).css("font-weight", "bold");
		//所在单元格添加下划线
		$(this).css("text-decoration", "underline");
		//所在行单元格字体:11px  
		$(this).find("td").css("font-size", "11px");
	});

	//鼠标点击时
	$(".x-table.REPORT0table td").mousedown(function() {
		//所在单元格字体颜色为黄色
		$(this).css("color", "yellow");
		//所在单元格背景为黑色
		$(this).css("background-color", "black");
		//所在单元格字体加粗
		$(this).css("font-weight", "bold");
		//所在单元格添加上划线
		$(this).css("text-decoration", "overline");
		//所在行单元格字体:13px  
		$(this).find("td").css("font-size", "13px");
	});

	//鼠标离开
	$(".x-table.REPORT0table td").mouseout(function() {
		//所在单元格字体颜色为黑色
		$(this).css("color", "black");
		//所在单元格背景为白色
		$(this).css("background-color", "white");
		//所在单元格字体正常
		$(this).css("font-weight", "normal");
		//所在单元格无下划线
		$(this).css("text-decoration", "none");
		//所在行单元格字体:9px  
		$(this).find("td").css("font-size", "9px");
	});
}, 100);]]></Content>
</JavaScript>
</Listener>
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
<![CDATA[723900,838200,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2880000,2880000,2880000,2880000,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[产品名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[类别ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[单价]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[库存量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="类别ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="单价"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="库存量"/>
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
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9>!@'5+c*Em]A'*dSI)_-+.%Eg'W+/MC;"n<nhAgd+/t#KQLc_!ei%b6p;qr5te=%ltrf.Hm
P*,X@5Z3+#1]AEWYS`+8-C)&#dGd'!Km_V&2M?QG=(&t/TaJJqXj3(*qG)3*]A!?43Ucd7ht`i
7Nr.^iH?JWtrqM6d1M4j:[s$##1M1mrB?[l(j8H<g^Pqqp?9D`;Rh=u;7(g4FA6AHcLIO,tR
W[$7]AukOp/:.'<J@DsGT7(:qK2:`=Y1UI3EC]A3jh5&R9Ju+5HrjY2]A]A;M6(l]AYr[bC(JI47^
mp5<QmNHhl-upB;YBg35SGj7gBJiMY6qG<`Dt,@a_0a7osEelBTfFLGW'[MC4IXn2*<QNa:)
lU4td.(.8O$>3N<qCC;Z.-Tk,7Ie*W@cT:sHY(mh`(f2p.=pkQ6T^fs]AEf3KfCQb9QH&EP/[
LR#]A#2*i!NEmS?-<&":-P"lBm[(pEU1@lqr'/7f^&%d'`"2:>MAikY>f+.=fk.YhQcEAbMke
oo6e@^Rp'iUIeL,&4dUGhmjIKUU:dH*(/R6/P1"]AMq.I0`/mG3FrC#_sh#5nQor[6Z@k<J+I
ssJ&fsV1?oo5RS6saG=5Od?0>@d&Hi>4s-[>Y57A9Q&=ert.;U&]AU48;Ml0J@a>Om4jR%Z5F
sr5<+aq*,N(WR+S*i5WCb4c<AJ:B[XWJ:qdJiYrb#!.&`X@R$M+,V+H6hT!9=Z&24R&?-5g$
!66@j<Q<L'NGV&I$`^YCY`3f$;UTc?'..l-!j1h*d0ih(=))Rt)2,Sreu,R0/#p!?.kQh9Q>
BTB'&!D9gna3lG$66+Atd+eCITZ*0f*AI;S*JXm^u\`=V:D(J,KRX#^_Y5+((UiN4W6XWoaI
nn0nB`GRdX,fTR\LY;*Up]ASQfN)*6a*-B7<(->qJ9QV*6I=0"\NIW\Xr9FHJSW^dI6),fhu"
L`u+V7Pqa+M`0]A=><1r[B%DOk^glTCK`'Z0<O]AClMt,@_rC8o=$I(=E':thjKNbtGBG!/Hkr
OPq=tq(\&?qPIqU"RKD'DX3$LIZ]A9:4Z.<CGb<7:+D>C4;<<@sQg2i,$TU[6h*LT1dqL?1ZZ
[^5X4-T^AjXii%CLSf_.9@=0G^2SO("Q[oU>^/5qrcsoAH&q6Ub.*mn*94MW/(0*sLlL"'_A
K(,!@]Aa0m6=R-I<BGj;-htg"DE8ejq+i=(R?g[33*P1gE4_JFj3ea3Iq2Pi0f<RZhdb$e?MD
&[^c8ZZJa0iK\gLb'aqmE@if@d__OB8]A%naG::,+OVK.$=iK^h@I#[H23/90KLN"Y%l",:uD
nD2,,7hLTW@IebO7ske1"Y(:@Cc'3q5&`nis@NRdt5Paq"8ut3RnQ?>//PGmM?nM*K-`,FDa
:Ce363Bmn>e;r.Xr<eY+/65g6aV:ZIYLqc_SM/VdeeZKj(;Z[%`@Te\mJl>dq'C&]AMga2,\0
YMGE6mn".4E,eK!G(6aBodNoodg$ol8*6ZaIE/A^6RM>Y,CR)JYJlsVpV28oAYpa2H*G7,W+
]AFqQK@JZ3a;q/Ouh('NiVJZ"#nJWQSSMtR`9[KC8kc6mk;=JP6gs1G/+ffYG<ZlKf\L4V]Afp
+aAijN_0i\Hj+n/g0eFH.:gLKlk@r(.PH%o,__tYLO0qGBkKgm8$VF!SHD<'@?)g;h<d2>r$
ZD=fD!\Edptn'`)r)on,\\6BNZl$&kqIe8V4n]AUF>T1'$<[)sAg-4p`q`QE@m_#]A1#/WFW+O
QM&O>CYb&Y0Jk&CG%Di9*29L`AKTiFA*SG5:/R[$%9BoZ%e7D$>*7SiP<DiPh,"$m.qar2Eh
0[d/_RY$3?Vb%R0)#7i@\Q4r`bn#tZ^dV^V&IB=kg(^.=>1]Ad-.&?4O]A+_)!pF=KQjeOY4cj
ZP+2Q[7":d5F[g,Rp+#@),LK!)DVdj..]AkD&!3rO-+5)+Zm)JYG*4J(Z>JG6H*mr&''dan-B
@#S8cb>')6e5M20!((@#G_>H\u%ciVhdL]A'mlfk9o2Dq+"07UPY_YjX*5lm\m/AkU/Wr/Jfn
,ou/Ai"O9o'"079!POqfnS42<W.46$n0qK+&N==I$Z@=FuEU/cCJaRq6PQk"@'Ad0tKQ1TsW
XCH"KsnJL#U5.A'Lgn)^>$Y/.hGD'*EnjCUV\(g9o%W4$"(KurA$"ZR-dDWjj=RJ.]AS]Aq'g]A
ddh6X$a9g3-7u/kO+>Ts>J0)\DXppjbY<LjRh\5MgL5usjZdE;U!=Vi`R3hShpJX%J7C'QVk
M39a-L^fo^R!o+CuSkJ8QPS2fHbOlISJ5FW6Ub^OeCpU%8Ji2boslQMSGG)#;UNg*',hG]A5.
g@KisQ!F#,0Eg=1S8Ab6[6OCSLRNVeR>TT=e-VmIHQFTcol>746UDj:4*`7nMg1f%*3ZOFud
JuPg>6o]AC%3Qlt>e\LP_44J@Km(4h.P,P!rhf3Jf868J8(8O5Jj49(i)^EO]A%m2):g)>NriZ
L*3:fSHbT,&72Rj0(Ja(2*WO-V<btnP`j3Yfu<jA=-RC+Vu?aHW04lmF+^ncsVG3?C_FA"-B
!g1fonFf9@(*o/">2>5ja6;jIo)hE4b@_/pT4+]Ao_UJAZ[5BY3FfTEMi#so1CBcE0Im^9jQ>
Z"]Ad*;:[V(Q1X;5<6)pc4laQ+Gamm,8cOH7)W)4beV(n%3H=q$KSJD*3B(ZrU/S5?5+0+_OF
U&g;R4N?b@.W^g/&.Pnh9b7pYflG6>'B@flL9b0r9R_k\f'UioG7in"f332doQZa>Q=\,ps)
@\Y=QDIJuRtOWqVqjWT,Pt`+1WI6SF+("`KW=%n!P(RfdBt5pWA"c=Wi:=[<?:!!0&L9eM5S
9fJWb!W$/ofC4uFrBiJrL"IMCuq"T$jg+qPK#QHD^PTL$*7l([Q7#,4tZ,J*Q<2@K>:knrR=
GGaW,>L^`82c"28aK,:q;fAS`/__T;(SbpuQ1'#l.WRC7Qce\&B7INoB&R3Ag@6:cr(Q1mbl
V%'K:cAST/&0YGCZgr4bMehO#fn)>LP4e%RFI.2%Ur]A9BVb[SHt5@$Aj?G\'mhQU&tS4P"V%
p)_D79pV==k\aA8;nO?JQdu*%XE^WWY*sGPYFQWa9OE7(WibTRnd2Wc&k+rc5W=BnXREoQhR
dKBRXm6?YR9/c(f37d"*,&]A6*??2fA89RO-@?f6.#XsC!q]AEr-kS_1ZNZ<2rhC%6V8]A)3d8"
Vh"r_@'PT.e_(6u'=%!]AB>)QKNj$V;-Fb@4WY[+bOpl#p)A\t%aQkF(UaK:84_QGbXbC$tT0
BV7q:\5r]A8gP=hs>pilNj"Qs@2YbAMRmNsC.\NZVgs<Z2L>2u3ga:E@e3nqQQnjlP?,I(@>p
B#Em4.OL)@KEQY(KKugk>2[qZ9qgX-S)q'H/GCEaG]A3+78\iN;IeO(YlK-/=S<bVY,[?\CP[
=GWqa95#b]A4&!'qk4jsI/ZlG'WX,\co#B=[m$Z8P;<7(T&ijE8lGYP:[BlsJo_PJQ@Lk`4Y?
!3QYic#.D-![M*F^1GM>>I`UZ`tWBe&S"'51dSlnZbEeO)iHmZ*;pp$)'EZ3%U$NF2M_[c:j
T(<L%ugdqc[c/Gc0q/G3^ab\g&>59<eBGKrM@"uF1Pe=Z(JTOdd(RC'$HcORJj`$O[=&\l-<
Rm%$WlKEPC5BE0G,mi"h'iRFI(%;r,)KZR^!EipCHC3Y\5mPVUrO7%UC3QGS+"^E$QB>H_)a
4?#'S'X`Cp0_h`nn;GK$4<Ne(Ha?-8q5@]AIe/1/2VF'.UCW+]A?qkh>nX=DYF!_nNAfCr_OVT
R2DV.,^BsPTS0@qS'doMc+eR6&!9]A-k*]AFtd-iJOC.j8s<=.A@sA%-u`+lEf3_KVC3O9r:dT
Tj;7]A(h(b89\$18uT11gA$q^/_m>NrEdG`Y\&K8jP8eX(*E8q_ID4Y03[H5hCf5AG4F\^"2g
438Q5uZN/V)`^#TW=^%dkP<PLhA`*:je9q9J*.<O2Tl!kIhLMb9[\nbF/Y%fUX%AHj9.s,Ra
!?&:oUg6[>[`\c$A>l37ONk'F-CtO1K'<RNLO9jQg9QTs%)kpm>Ku"i6]A"hpE.`$9\-$/)-;
KCjicYM477aq3(G`coqu/Qe#270u1t$ePKnU-_dsuU*E[%)8;CI_hdBTMZbs#4@;k^H4#i-_
'QdT8_<b%F9ArcaWK;4M9Q>mHZKPh?a,uuIUQSd;)/dT\KUR8Vg[Bd1[=U"8Kl6O`NEpm]AYk
Lp8aCZS_Y2]A_4%e=SEnH=R%?i*k,=)!ECB@p2!&T<EB0G'+6T5Ws)Pj^1SJ>?n3H?*lD:]AWp
8slMNoS@oieD-6XC6LQ4=-X,(32#;b&TDf/0,)KYQ3@og#p*,ko-f'O(;.U#;o*MD*Z;gU*B
kWl2aHuTcilT=ZBnn,M(K_VZ^W`^V[g5?]A(SSadl^giunEiii@^M.U&a"$5MFB;N7P<a>c[`
%j0<g*=d%HIDFrObP->l1=,LHM_gp>jrE-6X:S`^V8P]A]A4-M[h84<c.-E<7(AeA,ugq--?I=
R+.H/]A4U/%#i7BY^>LWhUeF"-Vf/IPIJoDRfL>DHIrI!<LVb2Wq?Z]AO*kXk_B!.G>3:q;H!.
P$s@NKu]AY1!U0/CZH]AR1os4fR[*C8l:NL0T7N+P]A.W>.IQTW0Wc\'4pXDCt)=(.Rqj(ZCG,-
JYoN#:ejO*^dr>u:*Unoog:rUqD;l+/jjt)A?DjPg_C:%K3$-ga?WS2E&VYY8"[!AouXSYV4
<Gjj:Chijpe<'.*8qpA(F"QoZ+kgq!l?&OJgD&ZacJh:D)s`N^`S5a$-R\@KFhIe/D'+Eq%C
2bl0?gV6/o`OiDka5B*3?ahrCAC)rm]AYr/#a1&dA12=WqDU,\Yeq;=_DboH0j%+aks@M-M5[
70uoJXgY`Aiouh3iG+8@Aem?lq@B1/@Vr5G5:LEen9-V]AN'4'7d2R.^pdN$p>a^WMd7*jR('
WtF7_PeQTbOUND]A]A;'/"VO,H7um:$]ALQd=d^W(/Pl/50h79mbDiN(lkrem9;<q_ZZ<r3!]AN>
/2AnRIeds^o]A[5\c<n2M'hXXY#h2.b4O/2gg+5EhS)I;,POTc^jbllUI@^AK,o'9&E2AblR3
E;"Du(Ot9,32OsTh[Zr<@igsOPk,1[G1H?ME>)OYo[0l!`TimSf='k]A!notm@#hpc*W_!.^f
_?&:C(QL[MO\*KD>/0jrd&_eJ<DOU40<D,f!8oAUpA8-k?dU0/7tO,?3eQARe0h6Q?cD^;^s
QZlKBu^9C\n4iNOE,m'6#Z5>;HPs]Ad[GR>od\-O/B8R"Wq>4I80'-@Y9ZHWNe@kCq&o5>6!_
tY$)"R/q,$!:"&3LK9HH1/GY]AC[AaQna;J*8l[JU0$Ws[f"&pa8?gtOO%o?8>n*3#+iQ<r'l
2k/@1`.l%t;2pt=aH[E+@s+5K@N595%!n5O9[_'0a/[RY2O$@Gs$.MXRc`%-W>qsFru*k:hs
Be:6ZR(s[Z!`eg[Q)"Or@b5n#NQn^p@>SRS^\Hp`_BAc&;r,icLKgeHIC3$MSCK;tSgD/S<`
1sp`8N3SXJ/SP=p\;aLk,[+?pb-Bi-3CaX?=%lG@g,HVBpcu$#("0aCp?;W@mqqT,;-gqi*V
W/,qW`[\]Ags.HoY&\&9!k"C@,F$"i+`E0Q"U+SS5)3_Jqhd?*oOq..)7goKuZmjUKNZ3LYN$
^\2a^W\AB%_W6,erpY)fn=kD&"O%`@)*Qo*[_L^<l.>,:7qbSEpP(T*!eCT`ZIib&D5u&Ekn
#YpRY"`!;F6LIf&+"5;bl:2Ed)(A6"7Rp`fLilgQ`<\@@nNb)d.CXSRsM8NnHIMYGQWd0,&L
5N'An14=!7n!08.M"N&;'m/Dbl=BZp!dgPH0o_AYa,*0J@2P7B1aUeOB0?5L++e[h!TM:WSm
.?'%K9."!Z89fHa8dO)i<WiRg;rC2J[WgEb@uTF.$So^!k,fI9(lj@^Q6NYiFrLJjal?iP%\
M4E!%[(RfelLIt%3PN^0AUR!$Sib6T#2jEe<>(,`T(CA'$rC3X:OdRDj`7s0@-0E9_cM>5T:
&Z*H%F>pN%JS@WO.'q&I1.A4aa<JOC9o.$X8C$Lj2%oRO)%R_YQq'qYJsdT9*@6;Y,'gckL+
i#&?t4ks3t0n2!g`d.sS\lOH'$=q4=X=Zsmrq?srVt_+Z11oiUYB-9iVNBo(6,:-Y5'>H,1q
8P!jm14<RVIcqsI)fKgGhF>OaoGlfs6KYff]A5:LW/X$>%$_sW)F-!O]A:s(RamDL)W`@o]Ap!j
;D'guPO^"IL\4CB=AF6.&!V1#fL'8C8%j#UCgP+qD>Xl9KbJ_1;a/M/?(nNN(ugJBpolq9p"
H46nklHR:Xalq0=jO=;L\7V(SiZ.XV*gVV]AYjIWl"@p=ku",,AT]AFc/-@$e/_f8FmN<;FA`p
;"9Q[3"tI[!,Ba-UttB$/A)*0g-/`Z]AX`4I7T/SYs5_@\<Kh4!*a)n"Z1]AqXg;Rc\:#Z_F]AV
ds>JY^=4aAs^CbQ1+5u>bGq9p^UG:"fK76m"egU"KRhTMsVRr'9gHo5me\XgsDpW/k)"@T#^
kcKZ0^l3S;KCC^8/\9a`#>@!h"gmMccMt5-+7LBZ\^]AKBO9cU=Y^/7+7t@WG,kaq_JIuX8e?
kp)gd_Wg$6!+"5sN=8f)!kVF/S**`UeD/I<S@-</A;#3FgpE]A]A)U-o\Ef\c(<[jT$qL:8+8t
X`e*.B7CT7NnpY_QCDYi61[%G'5d)gl/13uB;FdJK62Z<o2N6mjS^TX_N]A8uSUN8cC$:#;$d
+=Qa&WlX5/_Ak1`[)9V8R-*gO._-Fii?qk=Ep:KO'l.Zr"+T+.R&St7s>O.(<ffuqb5ua:fE
FWTn(cjnptNSq+p%CKT50!%VmGCB]AoDT:hX?XkO0V-66R/l0&1W_*0n=q:[IDHBao0>n%O$'
:iRYRJ(-cSOQQ=26_fcsZ5-TFOqJaAKKJa0SOZ@nkD7i6$q*8[hmX5!0qa#V`#B`MNfP?GX-
eU'e.i#n[0!(m&#V^800uCL)^<_%'4i^rCEo8K'C;9fZ4+G/G;Ks@/8i'@V]A:#rJS<%c0s;:
D4*l_%GR[+A*2BKtE1E+t\s+uY+3>QRo:8&k]AsCYc`]APl3AJsakFD4^C-:/4$E^mR)dFBLh_
VMo@K8#VZYdCLX73H&&3lpeO;Sb/%&6?.#)#kj@7e:Ri>G#mFdXHnK*Qqm%B;*H%VdX\n@'h
pu6So%b&A[7meJ(&R!Jh-;V)XC%GTg[NTe'jW6dn$2PekcDL$*;`,nUK-S1^aP'b_CZ[_=SM
GE.)CLdkk!o[ueeYGT/.)o:%^1_#j.#WGa6r`$8X9Q5A5(?*Xu3eDSKN*Un:\/Hu0.#@jU`,
ZKrK^_-;:L+>&hA:&R;IR`FdNcP4U=jX%n83KaA^e\uGC2A,0JIGj:?&&^#N'NC[))S->p\Y
EfX\QB'T;iTBoQb[WKgh>*,Zr;ma,]AAkRFY.`.g`&&::1,mJ'\DUQ_aq^E[V!2GTm@hXShIo
CdN*#2so^==(6VoOlkoru7EJd.oBIVXPl3L^-?:CD/?7]AfNMHJo4r&K4#FTKM*AK_n0`I`+L
qGM5.lrahlUU0:R%NcUFf.NO*cZY'D>.-[U3d3e+'qgQ4@G/ct-1IK#$e>S7^9h3)#%[;$n4
>k+le/$%<A;1$HdP@HB;'(,-IGHqA4[?V,2"1?r`4BSTTq4cng,&<B^$IYNmcYgWAj*BKlI/
8LFl&",#Z_T9cbI/KViHt\)6TiQL-I50TD6GHkH$l*O#IBOUQslskR"^a1`s[;%1FuXa=Z@9
"Hall2C]Ao+"(Wr$Fl(b2&[uuGd^X4_SiOkT_\<dbF\<aKucOo1':`rC2U@5=!,:JRj[EVM2q
%WT)7$IP<SaUIjf`hAs1kP``%+/HpVuLNlThX`taZUHt]A6b$FH*A*FQaHu)ob8"eD!Pe=n9i
U$Vku"K8Qg]A;r:$ls^!OOL;c"$9L\/2A>cEHmdjUg(c`T"T$QY]A#195'rNS4tq%PK<nLi]AF>
X5V4LSqEqQ@%VQp8$/cibI`'"!r~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="500" height="600"/>
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
<![CDATA[723900,838200,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2880000,2880000,2880000,2880000,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[产品名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[类别ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[单价]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[库存量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="类别ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="单价"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="库存量"/>
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
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9>!@'5+c*Em]A'*dSI)_-+.%Eg'W+/MC;"n<nhAgd+/t#KQLc_!ei%b6p;qr5te=%ltrf.Hm
P*,X@5Z3+#1]AEWYS`+8-C)&#dGd'!Km_V&2M?QG=(&t/TaJJqXj3(*qG)3*]A!?43Ucd7ht`i
7Nr.^iH?JWtrqM6d1M4j:[s$##1M1mrB?[l(j8H<g^Pqqp?9D`;Rh=u;7(g4FA6AHcLIO,tR
W[$7]AukOp/:.'<J@DsGT7(:qK2:`=Y1UI3EC]A3jh5&R9Ju+5HrjY2]A]A;M6(l]AYr[bC(JI47^
mp5<QmNHhl-upB;YBg35SGj7gBJiMY6qG<`Dt,@a_0a7osEelBTfFLGW'[MC4IXn2*<QNa:)
lU4td.(.8O$>3N<qCC;Z.-Tk,7Ie*W@cT:sHY(mh`(f2p.=pkQ6T^fs]AEf3KfCQb9QH&EP/[
LR#]A#2*i!NEmS?-<&":-P"lBm[(pEU1@lqr'/7f^&%d'`"2:>MAikY>f+.=fk.YhQcEAbMke
oo6e@^Rp'iUIeL,&4dUGhmjIKUU:dH*(/R6/P1"]AMq.I0`/mG3FrC#_sh#5nQor[6Z@k<J+I
ssJ&fsV1?oo5RS6saG=5Od?0>@d&Hi>4s-[>Y57A9Q&=ert.;U&]AU48;Ml0J@a>Om4jR%Z5F
sr5<+aq*,N(WR+S*i5WCb4c<AJ:B[XWJ:qdJiYrb#!.&`X@R$M+,V+H6hT!9=Z&24R&?-5g$
!66@j<Q<L'NGV&I$`^YCY`3f$;UTc?'..l-!j1h*d0ih(=))Rt)2,Sreu,R0/#p!?.kQh9Q>
BTB'&!D9gna3lG$66+Atd+eCITZ*0f*AI;S*JXm^u\`=V:D(J,KRX#^_Y5+((UiN4W6XWoaI
nn0nB`GRdX,fTR\LY;*Up]ASQfN)*6a*-B7<(->qJ9QV*6I=0"\NIW\Xr9FHJSW^dI6),fhu"
L`u+V7Pqa+M`0]A=><1r[B%DOk^glTCK`'Z0<O]AClMt,@_rC8o=$I(=E':thjKNbtGBG!/Hkr
OPq=tq(\&?qPIqU"RKD'DX3$LIZ]A9:4Z.<CGb<7:+D>C4;<<@sQg2i,$TU[6h*LT1dqL?1ZZ
[^5X4-T^AjXii%CLSf_.9@=0G^2SO("Q[oU>^/5qrcsoAH&q6Ub.*mn*94MW/(0*sLlL"'_A
K(,!@]Aa0m6=R-I<BGj;-htg"DE8ejq+i=(R?g[33*P1gE4_JFj3ea3Iq2Pi0f<RZhdb$e?MD
&[^c8ZZJa0iK\gLb'aqmE@if@d__OB8]A%naG::,+OVK.$=iK^h@I#[H23/90KLN"Y%l",:uD
nD2,,7hLTW@IebO7ske1"Y(:@Cc'3q5&`nis@NRdt5Paq"8ut3RnQ?>//PGmM?nM*K-`,FDa
:Ce363Bmn>e;r.Xr<eY+/65g6aV:ZIYLqc_SM/VdeeZKj(;Z[%`@Te\mJl>dq'C&]AMga2,\0
YMGE6mn".4E,eK!G(6aBodNoodg$ol8*6ZaIE/A^6RM>Y,CR)JYJlsVpV28oAYpa2H*G7,W+
]AFqQK@JZ3a;q/Ouh('NiVJZ"#nJWQSSMtR`9[KC8kc6mk;=JP6gs1G/+ffYG<ZlKf\L4V]Afp
+aAijN_0i\Hj+n/g0eFH.:gLKlk@r(.PH%o,__tYLO0qGBkKgm8$VF!SHD<'@?)g;h<d2>r$
ZD=fD!\Edptn'`)r)on,\\6BNZl$&kqIe8V4n]AUF>T1'$<[)sAg-4p`q`QE@m_#]A1#/WFW+O
QM&O>CYb&Y0Jk&CG%Di9*29L`AKTiFA*SG5:/R[$%9BoZ%e7D$>*7SiP<DiPh,"$m.qar2Eh
0[d/_RY$3?Vb%R0)#7i@\Q4r`bn#tZ^dV^V&IB=kg(^.=>1]Ad-.&?4O]A+_)!pF=KQjeOY4cj
ZP+2Q[7":d5F[g,Rp+#@),LK!)DVdj..]AkD&!3rO-+5)+Zm)JYG*4J(Z>JG6H*mr&''dan-B
@#S8cb>')6e5M20!((@#G_>H\u%ciVhdL]A'mlfk9o2Dq+"07UPY_YjX*5lm\m/AkU/Wr/Jfn
,ou/Ai"O9o'"079!POqfnS42<W.46$n0qK+&N==I$Z@=FuEU/cCJaRq6PQk"@'Ad0tKQ1TsW
XCH"KsnJL#U5.A'Lgn)^>$Y/.hGD'*EnjCUV\(g9o%W4$"(KurA$"ZR-dDWjj=RJ.]AS]Aq'g]A
ddh6X$a9g3-7u/kO+>Ts>J0)\DXppjbY<LjRh\5MgL5usjZdE;U!=Vi`R3hShpJX%J7C'QVk
M39a-L^fo^R!o+CuSkJ8QPS2fHbOlISJ5FW6Ub^OeCpU%8Ji2boslQMSGG)#;UNg*',hG]A5.
g@KisQ!F#,0Eg=1S8Ab6[6OCSLRNVeR>TT=e-VmIHQFTcol>746UDj:4*`7nMg1f%*3ZOFud
JuPg>6o]AC%3Qlt>e\LP_44J@Km(4h.P,P!rhf3Jf868J8(8O5Jj49(i)^EO]A%m2):g)>NriZ
L*3:fSHbT,&72Rj0(Ja(2*WO-V<btnP`j3Yfu<jA=-RC+Vu?aHW04lmF+^ncsVG3?C_FA"-B
!g1fonFf9@(*o/">2>5ja6;jIo)hE4b@_/pT4+]Ao_UJAZ[5BY3FfTEMi#so1CBcE0Im^9jQ>
Z"]Ad*;:[V(Q1X;5<6)pc4laQ+Gamm,8cOH7)W)4beV(n%3H=q$KSJD*3B(ZrU/S5?5+0+_OF
U&g;R4N?b@.W^g/&.Pnh9b7pYflG6>'B@flL9b0r9R_k\f'UioG7in"f332doQZa>Q=\,ps)
@\Y=QDIJuRtOWqVqjWT,Pt`+1WI6SF+("`KW=%n!P(RfdBt5pWA"c=Wi:=[<?:!!0&L9eM5S
9fJWb!W$/ofC4uFrBiJrL"IMCuq"T$jg+qPK#QHD^PTL$*7l([Q7#,4tZ,J*Q<2@K>:knrR=
GGaW,>L^`82c"28aK,:q;fAS`/__T;(SbpuQ1'#l.WRC7Qce\&B7INoB&R3Ag@6:cr(Q1mbl
V%'K:cAST/&0YGCZgr4bMehO#fn)>LP4e%RFI.2%Ur]A9BVb[SHt5@$Aj?G\'mhQU&tS4P"V%
p)_D79pV==k\aA8;nO?JQdu*%XE^WWY*sGPYFQWa9OE7(WibTRnd2Wc&k+rc5W=BnXREoQhR
dKBRXm6?YR9/c(f37d"*,&]A6*??2fA89RO-@?f6.#XsC!q]AEr-kS_1ZNZ<2rhC%6V8]A)3d8"
Vh"r_@'PT.e_(6u'=%!]AB>)QKNj$V;-Fb@4WY[+bOpl#p)A\t%aQkF(UaK:84_QGbXbC$tT0
BV7q:\5r]A8gP=hs>pilNj"Qs@2YbAMRmNsC.\NZVgs<Z2L>2u3ga:E@e3nqQQnjlP?,I(@>p
B#Em4.OL)@KEQY(KKugk>2[qZ9qgX-S)q'H/GCEaG]A3+78\iN;IeO(YlK-/=S<bVY,[?\CP[
=GWqa95#b]A4&!'qk4jsI/ZlG'WX,\co#B=[m$Z8P;<7(T&ijE8lGYP:[BlsJo_PJQ@Lk`4Y?
!3QYic#.D-![M*F^1GM>>I`UZ`tWBe&S"'51dSlnZbEeO)iHmZ*;pp$)'EZ3%U$NF2M_[c:j
T(<L%ugdqc[c/Gc0q/G3^ab\g&>59<eBGKrM@"uF1Pe=Z(JTOdd(RC'$HcORJj`$O[=&\l-<
Rm%$WlKEPC5BE0G,mi"h'iRFI(%;r,)KZR^!EipCHC3Y\5mPVUrO7%UC3QGS+"^E$QB>H_)a
4?#'S'X`Cp0_h`nn;GK$4<Ne(Ha?-8q5@]AIe/1/2VF'.UCW+]A?qkh>nX=DYF!_nNAfCr_OVT
R2DV.,^BsPTS0@qS'doMc+eR6&!9]A-k*]AFtd-iJOC.j8s<=.A@sA%-u`+lEf3_KVC3O9r:dT
Tj;7]A(h(b89\$18uT11gA$q^/_m>NrEdG`Y\&K8jP8eX(*E8q_ID4Y03[H5hCf5AG4F\^"2g
438Q5uZN/V)`^#TW=^%dkP<PLhA`*:je9q9J*.<O2Tl!kIhLMb9[\nbF/Y%fUX%AHj9.s,Ra
!?&:oUg6[>[`\c$A>l37ONk'F-CtO1K'<RNLO9jQg9QTs%)kpm>Ku"i6]A"hpE.`$9\-$/)-;
KCjicYM477aq3(G`coqu/Qe#270u1t$ePKnU-_dsuU*E[%)8;CI_hdBTMZbs#4@;k^H4#i-_
'QdT8_<b%F9ArcaWK;4M9Q>mHZKPh?a,uuIUQSd;)/dT\KUR8Vg[Bd1[=U"8Kl6O`NEpm]AYk
Lp8aCZS_Y2]A_4%e=SEnH=R%?i*k,=)!ECB@p2!&T<EB0G'+6T5Ws)Pj^1SJ>?n3H?*lD:]AWp
8slMNoS@oieD-6XC6LQ4=-X,(32#;b&TDf/0,)KYQ3@og#p*,ko-f'O(;.U#;o*MD*Z;gU*B
kWl2aHuTcilT=ZBnn,M(K_VZ^W`^V[g5?]A(SSadl^giunEiii@^M.U&a"$5MFB;N7P<a>c[`
%j0<g*=d%HIDFrObP->l1=,LHM_gp>jrE-6X:S`^V8P]A]A4-M[h84<c.-E<7(AeA,ugq--?I=
R+.H/]A4U/%#i7BY^>LWhUeF"-Vf/IPIJoDRfL>DHIrI!<LVb2Wq?Z]AO*kXk_B!.G>3:q;H!.
P$s@NKu]AY1!U0/CZH]AR1os4fR[*C8l:NL0T7N+P]A.W>.IQTW0Wc\'4pXDCt)=(.Rqj(ZCG,-
JYoN#:ejO*^dr>u:*Unoog:rUqD;l+/jjt)A?DjPg_C:%K3$-ga?WS2E&VYY8"[!AouXSYV4
<Gjj:Chijpe<'.*8qpA(F"QoZ+kgq!l?&OJgD&ZacJh:D)s`N^`S5a$-R\@KFhIe/D'+Eq%C
2bl0?gV6/o`OiDka5B*3?ahrCAC)rm]AYr/#a1&dA12=WqDU,\Yeq;=_DboH0j%+aks@M-M5[
70uoJXgY`Aiouh3iG+8@Aem?lq@B1/@Vr5G5:LEen9-V]AN'4'7d2R.^pdN$p>a^WMd7*jR('
WtF7_PeQTbOUND]A]A;'/"VO,H7um:$]ALQd=d^W(/Pl/50h79mbDiN(lkrem9;<q_ZZ<r3!]AN>
/2AnRIeds^o]A[5\c<n2M'hXXY#h2.b4O/2gg+5EhS)I;,POTc^jbllUI@^AK,o'9&E2AblR3
E;"Du(Ot9,32OsTh[Zr<@igsOPk,1[G1H?ME>)OYo[0l!`TimSf='k]A!notm@#hpc*W_!.^f
_?&:C(QL[MO\*KD>/0jrd&_eJ<DOU40<D,f!8oAUpA8-k?dU0/7tO,?3eQARe0h6Q?cD^;^s
QZlKBu^9C\n4iNOE,m'6#Z5>;HPs]Ad[GR>od\-O/B8R"Wq>4I80'-@Y9ZHWNe@kCq&o5>6!_
tY$)"R/q,$!:"&3LK9HH1/GY]AC[AaQna;J*8l[JU0$Ws[f"&pa8?gtOO%o?8>n*3#+iQ<r'l
2k/@1`.l%t;2pt=aH[E+@s+5K@N595%!n5O9[_'0a/[RY2O$@Gs$.MXRc`%-W>qsFru*k:hs
Be:6ZR(s[Z!`eg[Q)"Or@b5n#NQn^p@>SRS^\Hp`_BAc&;r,icLKgeHIC3$MSCK;tSgD/S<`
1sp`8N3SXJ/SP=p\;aLk,[+?pb-Bi-3CaX?=%lG@g,HVBpcu$#("0aCp?;W@mqqT,;-gqi*V
W/,qW`[\]Ags.HoY&\&9!k"C@,F$"i+`E0Q"U+SS5)3_Jqhd?*oOq..)7goKuZmjUKNZ3LYN$
^\2a^W\AB%_W6,erpY)fn=kD&"O%`@)*Qo*[_L^<l.>,:7qbSEpP(T*!eCT`ZIib&D5u&Ekn
#YpRY"`!;F6LIf&+"5;bl:2Ed)(A6"7Rp`fLilgQ`<\@@nNb)d.CXSRsM8NnHIMYGQWd0,&L
5N'An14=!7n!08.M"N&;'m/Dbl=BZp!dgPH0o_AYa,*0J@2P7B1aUeOB0?5L++e[h!TM:WSm
.?'%K9."!Z89fHa8dO)i<WiRg;rC2J[WgEb@uTF.$So^!k,fI9(lj@^Q6NYiFrLJjal?iP%\
M4E!%[(RfelLIt%3PN^0AUR!$Sib6T#2jEe<>(,`T(CA'$rC3X:OdRDj`7s0@-0E9_cM>5T:
&Z*H%F>pN%JS@WO.'q&I1.A4aa<JOC9o.$X8C$Lj2%oRO)%R_YQq'qYJsdT9*@6;Y,'gckL+
i#&?t4ks3t0n2!g`d.sS\lOH'$=q4=X=Zsmrq?srVt_+Z11oiUYB-9iVNBo(6,:-Y5'>H,1q
8P!jm14<RVIcqsI)fKgGhF>OaoGlfs6KYff]A5:LW/X$>%$_sW)F-!O]A:s(RamDL)W`@o]Ap!j
;D'guPO^"IL\4CB=AF6.&!V1#fL'8C8%j#UCgP+qD>Xl9KbJ_1;a/M/?(nNN(ugJBpolq9p"
H46nklHR:Xalq0=jO=;L\7V(SiZ.XV*gVV]AYjIWl"@p=ku",,AT]AFc/-@$e/_f8FmN<;FA`p
;"9Q[3"tI[!,Ba-UttB$/A)*0g-/`Z]AX`4I7T/SYs5_@\<Kh4!*a)n"Z1]AqXg;Rc\:#Z_F]AV
ds>JY^=4aAs^CbQ1+5u>bGq9p^UG:"fK76m"egU"KRhTMsVRr'9gHo5me\XgsDpW/k)"@T#^
kcKZ0^l3S;KCC^8/\9a`#>@!h"gmMccMt5-+7LBZ\^]AKBO9cU=Y^/7+7t@WG,kaq_JIuX8e?
kp)gd_Wg$6!+"5sN=8f)!kVF/S**`UeD/I<S@-</A;#3FgpE]A]A)U-o\Ef\c(<[jT$qL:8+8t
X`e*.B7CT7NnpY_QCDYi61[%G'5d)gl/13uB;FdJK62Z<o2N6mjS^TX_N]A8uSUN8cC$:#;$d
+=Qa&WlX5/_Ak1`[)9V8R-*gO._-Fii?qk=Ep:KO'l.Zr"+T+.R&St7s>O.(<ffuqb5ua:fE
FWTn(cjnptNSq+p%CKT50!%VmGCB]AoDT:hX?XkO0V-66R/l0&1W_*0n=q:[IDHBao0>n%O$'
:iRYRJ(-cSOQQ=26_fcsZ5-TFOqJaAKKJa0SOZ@nkD7i6$q*8[hmX5!0qa#V`#B`MNfP?GX-
eU'e.i#n[0!(m&#V^800uCL)^<_%'4i^rCEo8K'C;9fZ4+G/G;Ks@/8i'@V]A:#rJS<%c0s;:
D4*l_%GR[+A*2BKtE1E+t\s+uY+3>QRo:8&k]AsCYc`]APl3AJsakFD4^C-:/4$E^mR)dFBLh_
VMo@K8#VZYdCLX73H&&3lpeO;Sb/%&6?.#)#kj@7e:Ri>G#mFdXHnK*Qqm%B;*H%VdX\n@'h
pu6So%b&A[7meJ(&R!Jh-;V)XC%GTg[NTe'jW6dn$2PekcDL$*;`,nUK-S1^aP'b_CZ[_=SM
GE.)CLdkk!o[ueeYGT/.)o:%^1_#j.#WGa6r`$8X9Q5A5(?*Xu3eDSKN*Un:\/Hu0.#@jU`,
ZKrK^_-;:L+>&hA:&R;IR`FdNcP4U=jX%n83KaA^e\uGC2A,0JIGj:?&&^#N'NC[))S->p\Y
EfX\QB'T;iTBoQb[WKgh>*,Zr;ma,]AAkRFY.`.g`&&::1,mJ'\DUQ_aq^E[V!2GTm@hXShIo
CdN*#2so^==(6VoOlkoru7EJd.oBIVXPl3L^-?:CD/?7]AfNMHJo4r&K4#FTKM*AK_n0`I`+L
qGM5.lrahlUU0:R%NcUFf.NO*cZY'D>.-[U3d3e+'qgQ4@G/ct-1IK#$e>S7^9h3)#%[;$n4
>k+le/$%<A;1$HdP@HB;'(,-IGHqA4[?V,2"1?r`4BSTTq4cng,&<B^$IYNmcYgWAj*BKlI/
8LFl&",#Z_T9cbI/KViHt\)6TiQL-I50TD6GHkH$l*O#IBOUQslskR"^a1`s[;%1FuXa=Z@9
"Hall2C]Ao+"(Wr$Fl(b2&[uuGd^X4_SiOkT_\<dbF\<aKucOo1':`rC2U@5=!,:JRj[EVM2q
%WT)7$IP<SaUIjf`hAs1kP``%+/HpVuLNlThX`taZUHt]A6b$FH*A*FQaHu)ob8"eD!Pe=n9i
U$Vku"K8Qg]A;r:$ls^!OOL;c"$9L\/2A>cEHmdjUg(c`T"T$QY]A#195'rNS4tq%PK<nLi]AF>
X5V4LSqEqQ@%VQp8$/cibI`'"!r~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="500" height="600"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="report1"/>
</MobileWidgetList>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="1000" height="600"/>
<ResolutionScalingAttr percent="1.0"/>
<BodyLayoutType type="0"/>
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
<TemplateIdAttMark TemplateId="ac771adc-a7fa-4da3-82fe-a9b8cbda0a84"/>
</TemplateIdAttMark>
</Form>
