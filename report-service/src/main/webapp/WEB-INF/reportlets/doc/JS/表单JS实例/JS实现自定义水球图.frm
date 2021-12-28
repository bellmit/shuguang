<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
</FormMobileAttr>
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var head = document.getElementsByTagName('head')[0]A;

	if(!isInclude("canvas.js")){
		var script = document.createElement('script');
		script.type = 'text/javascript';
		script.src = 'css/canvas.js';
		head.appendChild(script);
	}

	function isInclude(name){
		var js= /js$/i.test(name);
		var es=document.getElementsByTagName(js?'script':'link');
		for(var i=0;i<es.length;i++)
			if(es[i]A[js?'src':'href']A.indexOf(name)!=-1)return true;
		return false;
	}

	window._form = this.options.form;]]></Content>
</JavaScript>
</Listener>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16777216"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16777216"/>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WAbsoluteLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var minVal = FR.remoteEvaluate('=report~A2');
var maxVal = FR.remoteEvaluate('=report~B2');

var timer = setInterval(function(){
	if(typeof runcanvas !== "function"){
		return false;
	}
	clearInterval(timer);
	runcanvas({"minVal":minVal[0]A,"maxVal":maxVal[0]A,
	"absolute":"ABSOLUTE0","wordStyle":"20px bold 微软雅黑",
	"wordColor":"#ffffff",
	"aColor":["#329FFF","rgba(50,159,255,.6)"]A,
	"bColor":['rgba(50,159,255,.3)','rgba(50,159,255,.3)']A,
	"lineColor":"#329FFF",
	"name":"自定义水球图","nameStyle":"15px normal 微软雅黑",
	"nameColor":"#ffffff"
	});
},100);

var t;
$(window).resize(function(){
	clearInterval(t);
	t = setInterval(function(){
		runcanvas({"minVal":minVal[0]A,"maxVal":maxVal[0]A,
		"absolute":"ABSOLUTE0","wordStyle":"20px bold 微软雅黑",
		"wordColor":"#ffffff",
		"aColor":["#329FFF","rgba(50,159,255,.6)"]A,
		"bColor":['rgba(50,159,255,.3)','rgba(50,159,255,.3)']A,
		"lineColor":"#329FFF",
		"name":"自定义水球图","nameStyle":"15px normal 微软雅黑",
		"nameColor":"#ffffff"
		});
		clearInterval(t);
	},300);
})]]></Content>
</JavaScript>
</Listener>
<WidgetName name="absolute0"/>
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
<WidgetName name="report"/>
<WidgetAttr invisible="true" description="">
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
<WidgetName name="report"/>
<WidgetAttr invisible="true" description="">
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
<O>
<![CDATA[指标值]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[目标值]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="I">
<![CDATA[25]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O t="I">
<![CDATA[100]]></O>
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
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[!Q"h"s+sQI7h#eD$31&+%7s)Y;?-[t%fcS0'F4mC!!&3S\C(?W!/X985u`*!mF8rZ!FZ[mdJ
No=a%1@h_DK4?Th+3iLAPnbcJn3*eJG^HS,cRZlN#j-R0V*W4Fed@!AlMATS[po:!<]AQ''&q
S!*(c/johW?rq]AB?Wn:%l"K74Fl&bsGYo0\N:_i;F8q<r*qCr#N@^/-F5ZK?G@ffUuGFT;=.
TZKo!NNm.OB:@7P:"i\!!!!j78?7R6=>B~
]]></IM>
</FineImage>
</Background>
<Border>
<Top style="1" color="-6697729"/>
<Bottom style="1" color="-6697729"/>
<Left style="1" color="-6697729"/>
<Right style="1" color="-6697729"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
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
<![CDATA[m94d7;qJUip,\HemHlF"<lsa:BRL[g/7>/<o!DCh)00Y0M\RCpBIPFK23f>HA5sr0;Oc3M"U
$hY;0$YCM@_L-C*N5=L_u@"L'Y3l0EXZHaE68FfNs-B]A6(HjhL!d;pqqO*52Z?ghs^:K4(%t
M,'`g&F.\pWA]AjA$GMd[qb*6OY;l36:IHGY<IkLM5d8-19gaD0R'd'4fC6^;m)`!f4qp?-W?
gToSQS!7[G3@0Kiq;\s<k*%>iVL^bB3q.6@(1pSC0.9N!:tI[IDsFp;kG59=Oir5O6nG-du/
FJ(4>`OiCatG`:(D5k@.;1`#]A(:aR;LHZ!JH76X>_^"s+AI;meVfK;mJLr+!D\3ZTAD=<ur!
*5?\`A$tuqZ-O6KYm:q^6''f_@.@eK+GV2b:<KT`m<&NgWN+_(i4X)I>Xd,oF^Fce$&Mg%P(
j]AM+*oea/\u!g2VepVjlWa99)cNRIYR2Ih*p%gDjp("#M\4(V)&E/kPe1>g/gJ/40n\.96,s
^e&CkEDdN*bnD2Kg&nA^;;O3>o=?lChTdRNmMQ.udgkFTEX#Domf]A)Ku]A)NQ'qOQ\<KdL'b;
u/(+E?u66R?uL9iVhb=/5m=^ZIHPH78)3g*:+5bB78rSAfARA\Y\Ge"4uT+IC.SHmKZ5M,A6
F-(O2T55'_#8@[;ctlW=bVE7XWVj"2ohE[Eqf_b)0[PpGU:.5NI.jFZl=T9GTc?aOLHd=C<P
F+a3j1uXpPO&EZ2.#!V.l1Hq/#U6F-s7u%<bJ+cC1pd]A)29W@^FMX8!ZWJN&)4OYN[9lfJ[2
%JQJ*;$C5dr_kNB?>J/JgmuFscAuV#?PshH^^hs30H=PcN-Ka^iJP"O%Q97315HnCd\0D8Mk
W_f0[YB2>/p^J6bU:(Ogq%`sSfFC@^bd1+=J]A7(j#a-nFjQkkJD8hT^SOXd*q$^IPsh%a[..
u&3rFsq.YVPDZ2L2E[^B73%[;2fkLN@PI8IDcGT]A5_U"W701C&':9jY'R#GS'I,W@GQR.XiH
qr7hjO4/JVEN.q+nshE3'!E9OUY(@cg^/bHpINL.;'Fi-RCA,\;CQ.F5i'n&k]AK3p2lK:Vt-
(bD?r>`\>W5iaLRA6bFJ6H9>Qlf]AXU)Q!edN>p]APFsn=$gojTBc.&*?^0Z+?DcMM>,(<io*3
-?%BKh>T,D.QI6o=gV_S]AF1O/(`6<n(50k68G:QYGW!S3&&Wb=T4MIu1T1KmC2@?')Y`NBQ<
2HMY59b8bR>o!/d[Mr&oM,o@@X1m6=$IW\!_H(1O5<c(+K642o0[/SpPJKE$upA(&'a&p3g.
6/u/]As*M2ka;2U"6'"_aCqWcaPmlt`jHd_XX?M1Zs[/K.5:Hk&Ma(#Qm8kpB/2+lE%CMnY/M
c"e<uT6j<_r9%m(.DDR/[6DB/M9o`.%<Xe"%X"MI%41V7^bU%:rEg?\=t0sZr;0tqNr6/rg:
3(nGZ$'"&P1;WWKqbS_f0N&aPQIM_m$XPpiQM^fpE2)MNLb;U"3a6/0@W0qG:$lAs7A=,sKZ
L]AGp.<9k_m8P_IG\>@25U8qnpP!LiKh(+$R[j&8"%AU)A:5&lC>oGaV>l='Vk#Z/AS2:ne$%
$FSD-ZU2L4GUaA%4e"("K>M1b]A0a[)8eI6n:="t!(:"9^I.N8WI=hU;#V="M9\/4+RY.\gYI
)d_3%:+$YhJ^c\73\8Y.]A+UC;`AIbmJISAcs=Q^M.f[!3Si=WgZO]A5D,00V&IZKi.aN?Yi\G
,U7[bIOrVb^%`o>VJ4it-j2V`F[ju@2@;)I'D<js>438/Bne`s2./F*N6*=6TT4mFKs"PFM]A
h_<<[-gG),:5;)1YSK!u57B$\I!_-(F5]An:Ytiu-IY7_I6d<OO0,D`cJIbSU+Vk,&&%:=QP(
!c*du^EEI/BC[Q<qMMIJpF3rhgR0O(9Asl9aI>XMM"\Z$O(KGhM_c:/=QV6l^VLQkLA<o@uf
oQk80C6l_K`.M`(Tk8,JV3X6<WccuV_l&ob\.'u_!DU53tXJm\e8QDUpNaTOO;P9QiCnH/%G
+eEerP8gQ1)r`^[Yd%*F-c3")*4^l=W19al[Jeo6V//5T-dOFmM&Un:5"B"P)D.KRDUc.37d
+6Q*"4Pd+RCiF=8/JF5f7:NO/(XZ;eCB`."%\7gDYC**JQWNo"JA-.qW4WtIFid.h-kjX^mS
+b9=\\Qn+?^*<bROk@B<BVM3_3g!^&G4URU\<"i7lgX7)WL76H(:"1@4T!A1TrPem/>$V:WO
;M"0lA:D35'/O5##Tn+<K#]A.5iA_Mn,-`n.?s.G&Z$%haEG/L^DfIBm-BbTalL):hr2.MdX6
PVFXJ;L@e/m+f_tt\:#U8=jgBnWN^h%2A7L<EZWr::8?KABY=/Rba.WG1[bUO4)pHAa.8]Ai.
E#ll96i&lFA!Km[PO8Tl"1u`+LeN2,SQ)p4#j0s.fDPdSUN;]AV5N/9kU#\<Oh,-63N-)8>E7
->N`Mni<h5G&%L<5^Xu%BQ7EQM9U"M%fMac3-MG:'G6;M<.Us`GcGY>\O0D2RloV<ogPA05l
)9[P:5o]AtY.]A"R4;5SC%1iVepmFgG*nMZR[\tI@hs8>>g&9PGj=]AoUZj_7&gSX5&qGZjbJ;H
5V,B</QHeK-,3N'UuW2-8SPru>i@lZGUU>,\FtBt:!3LTp04"NWV#`J$3srg!lW(bAsF?O3O
Fn&MdeBkCFDFJE?X/2-atUVP"AV&)qgCMa[^DckC'`n$Sgp6(J4h]Ana'7mNO$4/#i"")Q3FQ
^gfMLoN,"H'VW-*VTQ2hhR8fUZ?=m6I7RZot_;nf073po#BB;&dub9VGfjLXDrDC#neXHSN$
'BJr=_6i8L-m%+#mF-[J'SgUaC+rTq:O;h'PmLJ\WSKE3-XKbW0CfhE8s<K&rJA:FA[^^$TC
m/#LO0%0VU*dP[".$U()4hGdi\/1\2WOf\XmGWO]A:QGPOhE)K;8,^/L:>d/n\.m6lO99%;VM
\bhr"-eZ'.:h3&1eMf]A2Z8SJoZ/_kq9Z9peC6g71Fp-9hm/j\d*5K;d@iX;XYknnqKn[`l,]A
L)iUuZNi)Gg+D9J=m.%rX"To5P%+11*`%@e>gnu9TFrZ?r@)edk)+CUBm%V((Dr/foXKRM%[
#g@A2+-I"=SDP]AIOkPZ%SUcdbm&4T4ao:#l#Mqg$0WT>s$o:Y)&;'Pg?kn1QYnH]A:=#\aZiQ
O-&?X5D:V5%EBH<4:r8=l8"i#?3a0N-7S_dPHNFS2>n>buNT-i*5J>ANfC[lGeK]AjT3L/[7d
ima#n$Qb)bNtoh(IjucM.EI(?7+PYd-1RL$7c93:WgLhr/>OE)gm(T8<2DnV\1.jA'e8_]AQ%
B;IZ&9O+(6gjEFO$Oiq-`jChn/FBLZtE=e=<6/`$ZK%5PLp[N-:pgP8Sq8"Va?)Yb[Vakd]Aa
[WGgR%licc[/RGb*O(PZl;lmAc+<'"h640f(5mbklWG0AS8H,Tbg)oC:\^mCJGbQXe6E-boO
oh`2^+r='NdL"Pkta-/a((cGIhZRZ1CG'3?o.(.>8I+**JYakguOUKi+$i#BIL1D]A2A!AT(a
CD52ZO&[!qQ<`tFNlDJ6[R`+`4h29f5]Ag4%OVl^oVSUAEa1NDlY,+sW?=cbO\chC-#/kag;t
.D;T<#h2Y]A@p2=klq%NQhe$.$&WK"]Ad@,q-3E5]A`7o_oA4:F_a_uB$34TWu^XL$7.!U^hrdc
WdK=q&kYGAcXDlrr$kc&`*-iZ+CDV%GVo5kp)[6dhA0:WhC.i"Oph(G;,ORW2HXf>&F7]Ask]A
HCk@O$<qCfLm74P>ipYUJHI<)_T=/Z(cl5hr_SVf1?-+&aPL/*5'7.(s\*;sHP*CuH(Hi:<j
1g)YQ'$UDFslk?kilUoBa3M\M!Bs=$&E9-G<F\G)/otOEGL-s?B#Ft2+d3e%O"0q`H5pm)g;
j<JI5UINk8(71>+sTeHbZY)'@T=5.!*g2Ls&gPS1^99M<%V$Y!:oV0i)S5rdf*=.`#2&SO-W
>V>6WpHd2CX<51'L3i]A7'92:ohG6S[e_aTBZFUBi-&]A,YJ*ZlK9WB=k/arQI2suqSlQh9V@1
9kBCCi<]AG_@a=o**KLi%'i_q80Pcou9iP:DAf2"L(4UoVE@7GQ%^XQWd909-riW$JMbJ1bSo
.EXs`3!V3R$nCg1jr#NWKfSHFgDM^)57,"@hI;*j>KL'S5KXS$W8Qt*A$`e.]Ai*,]Ae(t;q9W
$2&Xg1Kbd+k:rLRNB(^2cIeZJf.hSGZtr=(uO>i.`st^l`6/<:2HS>jB,'#6%33C?M^-91A&
4adC9>-5p=8ToWpd]A,?k0<W[no-kh-%]A1a_^b0qp3(iGYBe^\l0iZd'-(NsJp0BetjTZ>GhW
m[#*jr=OoF5pWOgWoRDgq$G^&1?QkW4tl-i#0ND$:B!_/1:X!'XHU6MpDo*dX>U"QBat%EC@
M4N7mN@CB%$^uUHkUUndb$SJf=2\eSFEUV>%.q<ZPP@fmdC]A:.A=8,Y>Nlk<kb3ABpL,6BT6
O3TXFs!:hU?-3q>(TU[>#d9i"97b`]As$RFF*s%TkY7V@sW6@oa#i;q5<<li#ieGR?a>=R(RI
$+.[XG&\B9^Z"KFrFUJ<=kSZ1p!#"#RW$8>]A->k)BT?#WNi%4(IJi@/(_G%9nT-h<-6</1$]A
uK5B%$*Gr=F;..urLK?Z.+\8ciU%<-PkG99aPnrl>(;VQ1]AABl"LN[_bi=KTls^r@VlAb<=+
]A0(m9<0QH%QLbU_4<7#nljp0$9(K-n_[sa9jg`[&9(qAeRIMI]AZ-nk2Mu32gaTW:3oG-Y:7F
D4)@;0b>b2Du$nG9%c(IOF5*Ia"kM6cYC:VQh;NiBKKa'$Q->@Aa`A0_cU4D3OV]A5DSM<m-g
+_YEGPCY1k:)SuV`%)hiadE_tgqVD3(eo_L=-pL9!rX.(X2O;;s95qgYTINM89]A]A'>h6AG8b
!UV(#G8u[bCkh:+Hp%==i((T0%)8CYt8OjNT[nXglo1W#8'AX'Soqk]A37aVAd25(Rhs<>TVp
+cq?+3hRRC"Q>7`0Pcdk@hq7'_Al;#*bi?Ej.(W*c1(jen$^CaA0("RP*N:0A3c*!.Bk&4[H
A6Dmh>]A'otPn9.RTBLu2C)VD%oTR:;b6W=,c7$4"!r%NUfoIAm5n,M7j_RfIODF8#!^4E-k.
qedPP:]A#IP?WH79^Qj=2[&1"Td?LmLI'K[@eW4`7#,t\n.65JG(##"<pDC0#Oq>XDVm*7FsT
l!aZK;G8R1S?S&Q.PjG+7\"UprMC%:ZZ6"%YY$Qfn@sAOLCn%N^n?V=*oN*Xb1#8_*U":)U:
0/&fO?2;tp*"V10lXDs[%dQu.dB*R6\H"]AbE(NE8b<!Y4=?'-L;W<3]ALi">hP6rZe6T<;:TV
.?)(VUms$BaT]AQ7iL-enY+~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="250" height="150"/>
</Widget>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report"/>
<WidgetAttr invisible="true" description="">
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
<O>
<![CDATA[指标值]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[目标值]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="I">
<![CDATA[25]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O t="I">
<![CDATA[100]]></O>
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
<Background name="ImageBackground" layout="2">
<FineImage fm="png">
<IM>
<![CDATA[!Q"h"s+sQI7h#eD$31&+%7s)Y;?-[t%fcS0'F4mC!!&3S\C(?W!/X985u`*!mF8rZ!FZ[mdJ
No=a%1@h_DK4?Th+3iLAPnbcJn3*eJG^HS,cRZlN#j-R0V*W4Fed@!AlMATS[po:!<]AQ''&q
S!*(c/johW?rq]AB?Wn:%l"K74Fl&bsGYo0\N:_i;F8q<r*qCr#N@^/-F5ZK?G@ffUuGFT;=.
TZKo!NNm.OB:@7P:"i\!!!!j78?7R6=>B~
]]></IM>
</FineImage>
</Background>
<Border>
<Top style="1" color="-6697729"/>
<Bottom style="1" color="-6697729"/>
<Left style="1" color="-6697729"/>
<Right style="1" color="-6697729"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
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
<![CDATA[m944+;qJUXoi>60V73lY8$-KD60bG2=;@W@EZD),QQ`K<7EX+N2+C;*;*[H=*<<3fo3ALDKC
%Vaje.l)&-N%H;@efj6j+eI6]A<-25R%BIOC@#e[!b@9nr_\lO/bGB_:i&X>Mo=kml0UHs,2E
X;l@j,io&`KQ2:6?CE+<@@3$=os7jj\aBsC!Zpj<=YiJODb%4r!kGVt8p'pC_\:,N+pA0Pgl
TWrVc$Ng;S%Cl@mC(5Q\"hErIBS(_psHh/Y,e'*(4%@@9r'FhF7SY#&J+g703lk.PP:uVr(j
``q]ADslSXFrN-/3QBBh6#_.<3b`YN;\Z$Bo_A-'4>:M<`QKEh<248>+UUVQ-BES1<8E(>D<c
=jp$OK9qBcQZJ1-#luXRWg4gja?jb[fcTcI.kZTUC.E0aPKoMVf&Gk$McN3CBYQ^Z;+>`#N"
P_pl5_h-)#_FWM_-1IVt1`GKVQcK[^.X`pIEYi8I'=)]A^JTQO$/Dn>ZX]An3+^p2\a!<.R2(H
()_/h^Kc*IjDk'a.dN^lPl*qFFL+(\hoaAb%H=j9`%Mc5?F7bm&Itp.IMa-5#&#\Ib20j+[o
bQ#R5f0*Q6`.7Nbar@a<p!lirP$J\Ni0r1biMYq5$X_lr6\ZFL>&t<mkp^[F'<2NWs0mE!f,
6"1q5>s%<Qp/'I.Xd4u-N_`GuAO/GW@W/t'*e@ermlmk%Pr<skm7Zi0bn^!8MX-<H?]AE'/0h
Qc5bjE4itTFb/fL?`A+$g'<H:T'A1fO163WaXpZ%ZE?!XmTuaTT/a906VU3S=R)Xd\o',s3*
e%Ur8/71_85mtG^&>4[p%3ER(j[;Xgh$:1VLpYX-F:iD;JMMGmBJ#0G<ol2`uuROm[[S>nI_
#Re.=>AT)PlWI6sXMUK9._8\?/%J.rG+]AE0YMb;N=VP.=oG>=hmrUo.2&F;;lgUI`fSfM8EN
XS&LK<O(8[%b>3<F$akmRS1$5AXH;U._fbCOTAKIDh1^eTLjX:7pM&^S#R"R6S_Jlai7I5s8
hp[kT4nMP)b9P#.H07l"?IO1g5ZF_BtZ2gkH=e8p@"'6\2L=q7Z"N<EcPrnb?N^#[[g=Qbq2
"_srN$Qfa:"MS;koWAgn;+N:P/W[k\p+9i^Z=8rZDq,S6@sVTl2C%,uY#b9djMCZl`g:QoDm
L%&cTqA\5FJZf',tRq>3dgsc'2RU`l=pB7:0j/-0FW!HkSa?\3K.pO%4.,P<0;5BrI(fRYPH
s9Y.P!#r\kjm$ogJ8`//UqhrK4_>4r<W[82E2J>_X1g01\Ae1r1U+?q+5K=d+h)s%\J,RmFJ
,&,bYp7=1CN`?g_5/tV>hl@DLRJR-./NQ2[]AI$,'jP`X!C'^1?oi`6\.9..?c%W1dOuAD7$V
WnD*GRH\pqBRhQmKSdcMc5pI3LH7u]A<NYc!R>b?Q<,Zf#qpTso_=]AVSFZa\RQgE2Raa&\cd2
opJs0:Sjj9-#IM5)lIC!_nqeTEof/II<<6%ZDTR$c>YkFpH#/D\s#R>l;ONuGbM)]A14n,Uh&
:2aE"c;((+JNkA>r?6D\HYeMsi'84NncQDiHhZBWQie<'KC=9.`VuO0q>#SK1-:2A?pP&%@6
E8]A?M28jd+H<jK8h<&WSpS.L(2,"S)_m[^kI'/(pd>,(2:AT7gAq2Of:gFeS*T;HVI2qdKsk
P%8VgXS6)`k=hK:Z]APbI*e_UM@;o@:d/oNc"WX.JM"IdF2R@A#@3qB2Ud>F3e%?]A@S"q/cfS
=\XV!i%6?Yn[,+9R&eha(b;BeR>0"^FXh:7<beGs]A9G#I=c!%o&o,qW15&Z8)nY$f;S4hP"q
SoSt%7Zr)8T]AY=%NY'$s'ncG95>A)kSZW:@=V'sY*ao3\091[saZ5sXf@J7b_mu=8(m3Hc+D
1*4(0(7-=TpkH>2<2mXhiC:b)8O#XN8umnEHW$,/5GLi)JNuF-G29emtQ@$D`Z!$omq;GWSE
Fc;YmAYh[SV@LT.FmOsRcM^:<qQVn<s\2H80%/+-'\0X$9j#ssrBY:;jUj8`]ATeQ5VW"DgGP
CO7I)s,j7_!`U#FLZ2(Gd]Ab6Nk\8$)9F8mS\j;&F*n[6),FTObNj/Rf#kY8rGh++D+!]AH7aE
lVaSd[c)2V5h,F0KI^?qdQNb8o-F89k"0X''o.lVJ-j:C+6$dEQ$eT^XOSdGb74C/Mj+-RGi
'geV)82sj[/N78K+".Q+r(`Ys"YWd42*A9r,Z1]AnKreP_/,\=O:b>%L[IJ@2<'/e$=(oc+k'
fc6O#>a*b1na)ZsnbMps-LcBh>uXS)0(Y_1Ui]ANLg47q:6VI#Kr@3HJKW/P-'YX*ATWhc"HB
)SMC"eNH9O0-MiGKi1oI+JjEa7`bja?o[Pd#-a]A,Z%(9d<5cr/M[U1AjZu)s/H'=``JV?e/_
$G+#SeG8">l``icX5JiJ]AVt..C*R/pm)iN=H]A4c=j;j^gk2`A=9:f5UFq[LTj.B\W3n1Yo,]A
)/?=%WQ`d7WcAq=b\[)#pl49p^WXfYaJ]A0DJZhl[H_(_%2S&Ds?YAQWgfCglV8R_eB5j";\d
]AUr!f&.\@KJN#u+73sp]AX:SRHM2>M`noedP_5<'ii3hes<>3o'XL1K7:h8:Kd^6+K:_@W55o
tUGd?<qjCY@i>0tWmKlOo5nS[U3<EZ^<eF1+gprdK59Tj9uA`-?/R;TjfE,+*1%K'A_j]A%/-
\]A/<$]AFHKg37>=M+)+7e4:D%t>(1D!9C?Jo_ZWrYjX<Vj*:mL]A$J"r0HDEN@=f?oZUTa(?s]A
^mNaj.as^jjGQdT%o6r9V674G?h)3b%-`fn/+=5G]A;j-!VlL>nW,:P4XQd?^&b+\1WSSQ0t\
N."jD<EEW6A56[eLPa+$:-iD4@X&WSMWEP1"c`nk,64aAeLn"BbaPnr2#K;?4@[a]APtM77l]A
k$]Ae2``o-!SQt?I`hl$t=GSs!PDNNk3u@Y0+728YC,kU3p,%mPieMR66R_l)/Aju?><&e4G=
FS(WG><g(<=G2`LOqkJn,Q`r1I]A?HbXpV$!A3N6eWa?_7Nr1M1u?="#d?dX,da9k*W/*[_lD
Nr/Q6B*Q86<;DTWk*<bkq-th5d+1eF]A=+2q4?MQ5A.!R1nUCq%"'sT/'1L=NRViXE`GIp=3L
b80U:RX;$A7T?!L#9Z07')(PfGd$Fr/<0,'a5kT2KY;e)_u/5<&7^Tq&D_*$`!$"Vas[X)Z7
-_`hRYUCrEGD'![!5R"c5,8R&%W?DO$TO,a70C_n0o;b&@N$_LM5TNd>Q*aMspmfBut!+[C#
9PKtT'ssQJMVrla\.55h85kYJB2=7T%2oH1"FHIUqokcKF-B(AJ:n,qlJa;K?_fUue^ZcR:!
\@=Q;L6qgmQtU7C;K:Van&r,\r#sItDe+BIo#YY)dH_=6tX42F50sJ<:b2K,tt]AK^cX%ktuu
,7m4J'4us1=^$TcsX!6jT*?AL;"W?OGlu]AO"d\-5:U0EG*PJDnJlt4:"TjhlL>Q-t&pS_#um
Q*0s^\L_]ATtc*+#mJ[%N2W8C"j:V_Pqq'ASpEVM`^FR=;"M429ZPpg-qE,^\g!&P%4H-u&%c
4;C-X3VMR04_h*3$1:@Os(qUbGYdIUe0'Js]AU?LRusA`-Hgk3e1Z-r*MG!'&Fn=L'8W1RJ\T
%uR"rPAVIfU#fG*J);YoXlFn:1DRkO-$3L=I3R4pT-?EVH!mJR(>f+[XVLg&DU>UG@s@!b\c
O<F'>:PsiFOp!A';PIXm-+@;SRT/gPuuMA<X&s3a8#)Y)5&V(T%&c*4)IAUmaSo5[,(!UfMW
iXp4,>&XtKc<:W9_TumL9@[o,P!O,W&3Q4u59jK;HV\.4'p.BN.0\I1B+]AYWZ$tX\;p6d=fR
SZ*O&[@loM.cu!O-.!&L*Q_lV-fVdWn0Y+2,IZ(aqpYrE#,1'pgo3!S:%5Wbnl-/!Aam$o9;
D[$;\$oc1H6HA%cb!MNNVOph>s"Orsu]A#i)\lI#tQR5(Qmt%0`U4eF;e\9?DG/F$<3rjkRJ#
q10ZSDLj+WUY3iMT@0!V#.al2.i$R.KI8+uEU_qcnr=<Q2pfp$L<a\K"rDA>6oP.(U6i"tbm
D:SX,Vr5;Kg-BK<sq?G@u'<ZC.A24?$E*G%O>r8=rDe_>s(.QZ`FQhKLor,o@A>_"DZt_^>M
B^(4hNnhinVpIRnA^YbY(h=QME6?Vo8:arK+3;<,MFFKJjP!ZY"o-WNV"Dj-,BDmKe?bjadR
7@-k3:CYBWtt@s[&?CX[qIL$MG'q#5s;#^g'$&907P9^GEu\gNh_gJQ3B/dTH8@.j8Aq'SBi
o6=/%@`WIj!'fR+->+_W_)P`cq4FAY3'a-#/5^'ku:<t5AN(dP,$o2`hJ#]Ali5NNO"L"mBjo
[M(UeM[J[s$-hW4Bct[kGf)O1H36T#A*Jn%4oe-#]A@TrZ)QBMs7I+WMS4!NYG:YHZocj2d"1
99I_:>JXY((uq:Pde(R8p%c`8H(<n/^aNl)Z$*$dB+E&*LbAD?cb!b"95LDuXd]A`R=V.en]Af
FBYbN_M3sS8qVpIH\SkY2+,,]ANiXqXVA0_;\UP+0S?S&A0JmR6g23:-b0He-Z4KpZ=KWBJXO
8@*CddiiC?T)HtH@ln&i%K&+lZ"gorBsuHLd0M>(S?19SpKEHMOJdZ&&PJNlT5+I%V4`<<Eb
Fb[.k9>F.@M.5fH+\\H0"3/$BkbLDtHh(UKpfQ/2:-J:+mnpB`#3o]A\[?D\*M]AXOVj,ZlS7K
43FX2FHg^I\MHZr'm9&2ZBR9S,<C@@9oV[ZB31/t#LO-$.PWj_,j".bPU1GDUGAY/@=S[>ZM
okPl&==`5)m<@8=BuQ9=#&MNg"VL"08;&PtAgocDBl13(=2#RWQc20PWH@OR-N^[Xl[pOO"0
1?+O,KH+_FXTTHX(-e)bH1mc&ce/R7pI7qOK=r;>NYUL.U5Z@g<lr?^llpH-c,8WfC`F;YhI
lUZZC48s?qW^/L1#uX2FRn]A$!stV=]A/"qdU&/s1EKESHD>^UkmIMaU8d\/XT1f\igV*eUjkD
=S2UDlEcbTk-f_"sRTHoLhP0^C(__I7hRana2MI*mg\(g,0Ej[Hrb,XILr6`J<J1RO]A]A7`B#
!I>m$N0FtW*(/\";AGH_37'D_\hEtF=HX+Ak.*'r0q'D=cIneOgH+;Q5i&je^VYe/YNAu_d;
T$naobnb?=m:XNpNmR*hL^a&j`u:fIRp9mVen%<2+TNc\oiWraO4go*%ap-/tE4:,;@/P7JX
pJ(2dH<Yd=fDYO/3~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="72" y="11" width="270" height="87"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
</InnerWidget>
<BoundsAttr x="209" y="116" width="400" height="300"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="absolute0"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="650" height="460"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="650" height="460"/>
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
<TemplateIdAttMark TemplateId="60fd0283-49e3-4189-b1c8-9a41dd240eb2"/>
</TemplateIdAttMark>
</Form>
