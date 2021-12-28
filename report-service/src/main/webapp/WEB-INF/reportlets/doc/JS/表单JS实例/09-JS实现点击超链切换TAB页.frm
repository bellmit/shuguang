<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
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
<InnerWidget class="com.fr.form.ui.container.cardlayout.WCardMainBorderLayout">
<WidgetName name="tablayout0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-13400848"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<NorthAttr size="36"/>
<North class="com.fr.form.ui.container.cardlayout.WCardTitleLayout">
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
<EastAttr size="25"/>
<East class="com.fr.form.ui.CardAddButton">
<WidgetName name="Add"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<AddTagAttr layoutName="cardlayout0"/>
</East>
<Center class="com.fr.form.ui.container.cardlayout.WCardTagLayout">
<WidgetName name="tabpane0"/>
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
<appFormTabPadding class="com.fr.base.iofile.attr.FormTabPaddingAttrMark">
<appFormTabPadding interval="4">
<Margin top="4" left="4" bottom="4" right="4"/>
</appFormTabPadding>
</appFormTabPadding>
<LCAttr vgap="0" hgap="1" compInterval="0"/>
<Widget class="com.fr.form.ui.CardSwitchButton">
<WidgetName name="398ab6f0-05f0-435f-a71e-b55cd78b329d"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[标题0]]></Text>
<SwitchTagAttr layoutName="cardlayout0"/>
</Widget>
<Widget class="com.fr.form.ui.CardSwitchButton">
<WidgetName name="83b56b38-6560-4f1f-a501-8c3684d2bb3a"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[标题1]]></Text>
<SwitchTagAttr layoutName="cardlayout0" index="1"/>
</Widget>
<Widget class="com.fr.form.ui.CardSwitchButton">
<WidgetName name="99d8ec7f-e56f-49d9-be31-9050a486995b"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[标题2]]></Text>
<SwitchTagAttr layoutName="cardlayout0" index="2"/>
</Widget>
<DisplayPosition type="0"/>
<FLAttr alignment="0"/>
<ColumnWidth defaultValue="80">
<![CDATA[80,80,80,80,80,80,80,80,80,80,80]]></ColumnWidth>
<FRFont name="SimSun" style="0" size="72"/>
<TextDirection type="0"/>
<TemplateStyle class="com.fr.general.cardtag.DefaultTemplateStyle"/>
<MobileTemplateStyle class="com.fr.general.cardtag.mobile.UpMenuStyle">
<initialColor color="-1"/>
<selectColor color="-12802576"/>
<extraConfig gapFix="false" titleWidthFix="true" minTabWidth="92"/>
<bottomBorder lineStyle="1" lineColor="-854793"/>
<underline lineStyle="1" lineColor="-12802576"/>
<tabFontConfig selectFontColor="-1">
<FRFont name="宋体" style="1" size="120" foreground="-6710887"/>
</tabFontConfig>
<custom custom="true"/>
</MobileTemplateStyle>
</Center>
<CardTitleLayout layoutName="cardlayout0"/>
</North>
<Center class="com.fr.form.ui.container.WCardLayout">
<WidgetName name="cardlayout0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="1" color="-723724" borderRadius="0" type="1" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-13400848"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.cardlayout.WTabFitLayout">
<WidgetName name="Tab0"/>
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
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="432" height="318"/>
<ResolutionScalingAttr percent="1.2"/>
<tabFitAttr index="0" tabNameIndex="0"/>
</Widget>
<Widget class="com.fr.form.ui.container.cardlayout.WTabFitLayout">
<WidgetName name="Tab1"/>
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
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="318"/>
<ResolutionScalingAttr percent="1.2"/>
<tabFitAttr index="1" tabNameIndex="1"/>
</Widget>
<Widget class="com.fr.form.ui.container.cardlayout.WTabFitLayout">
<WidgetName name="Tab2"/>
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
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="318"/>
<ResolutionScalingAttr percent="1.2"/>
<tabFitAttr index="2" tabNameIndex="2"/>
</Widget>
<carouselAttr isCarousel="false" carouselInterval="1.8"/>
</Center>
</InnerWidget>
<BoundsAttr x="0" y="245" width="960" height="295"/>
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
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0"/>
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
<O>
<![CDATA[Tab名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O>
<![CDATA[标题0]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript脚本1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[_g().options.form.getWidgetByName("tabpane0").showCardByIndex(0);]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="2" s="0">
<O>
<![CDATA[标题1]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript脚本1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[_g().options.form.getWidgetByName("tabpane0").showCardByIndex(1);]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="3" s="0">
<O>
<![CDATA[标题2]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript脚本1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[_g().options.form.getWidgetByName("tabpane0").showCardByIndex(2);]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
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
<FRFont name="SimSun" style="0" size="72" foreground="-16776961" underline="1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="true"/>
<heightPercent heightpercent="0.35"/>
<IM>
<![CDATA[m94j='A'\eeoP%kH]A\t)/IGr^Rr\F1[FBdPcfm"g718@^Xk8N,/JU^:PQlEPA?N>*:rQ[T.E
X/rqT/2pVA'VfUt&"PPluqW0Hni:%Z`jp#g"?[bhPL$.l262+4ofBpZ[J5XP'pmn%I<KrI/P
L'Y]AtHlh.(BP@MUknVV[AhHOL049cK!7H5F:/C(/;GE3UTFu4*nJ%hIXhVZEe*HZdMrmL^/^
[Q&>^sc<P,s;(amW$/$D-nRSSmonOr:IOfa^:En%aT&tr:[dk07NTJM[O.J9qO'D^ONr@pjJ
oNpST*N:&a>27=Teq6I!?RZ<`0'YS:7kRs=TNFa6]A]AEJHm]An.-$#V]Ao>t<nf3t8r[hu.rrD4
Z=DQCdi1q+*:+"[F&%s(S^')QIA37.eM%E^<UlfWNG>X(5@0`-%WkcF4GHh#.)[*-aP\O.'.
Wps6F&AX%543C#ci@-Me*SOT"e]AJXntBJSJAE]A2l;-,`ndjunW&Nph77IKhOMd+^:[ktVIon
2n?,cTi#"</bV(&K$3/BffD`(d#[-rI02'YcKpf*p!omOWWG*/9U$kd#bt]A/kR2h36F\?B[i
]AbGg%k!c6MU)83FAW>6<ZgCF-&%c@ns2?/PtP0lWrq?$P....8MR1,9SS]A<)=RpR;`?UsPeW
qn4(T$5c+P+-f,"cMC\j/:_d&HF3g\$ERd78R</5NE(.*ugER[mFnAeu?Gbm=*G$96`7o>I)
R971r"9Y8l3)Ko!kPBjOX]A+l3[4bN>?Ll`U-9eQ2E-LJfN'/fqAjR?q9Zm>38C`fMI&Br*d&
>V\AD@V$Zbouc)5*b@dPo@n()$phar(102(.[()AeF8TZMj(8gV5(JVCK5P2jL6Q1p,cgAeH
nLm)SVbDlp\/iX<:I=tZIF"^_9#g[]A+)Vc&3\[?VREi!K_K80qc#mHe#056/-YO2&WK.]A./&
_!pHFHBVH\"!sn3NGqS43LE:lSVXT70(i6q8ESI+e+Ve>Gf)Tr-Ae,Y[h<=K#d_opMIQ[8Bg
'D\Tq&)>K(ZmFC"pI2Ku7m4:,L]A+7OatF!C[B67[qTVlG4Cm*-uo\\3]AT#:(MRQV"Gn9aOfe
j)1F<MhThEK9O?MXgNS>bGD=dJ%G<RbpSA[DrEQ#gZ4;p5]AKJ(GdFn("gGINGWujm4DJP*&Y
dADkR@]A/<4"i_J<j4?mpWfgCk8Q*+.'Tl4M$k*"PtZ^N:c3;4Z40chpGBO'?!>',^H!?Sp$.
#UW6;o^[1*a;LdI9riK^ET;sHH>Jd$.1i]AJ/D6:3@hCDM!2_U+U6bkma$$jZMq$.dD%019@D
k3Y(j$]A[P(PE19$8pT3NB4Xk_fV:3r*6o1+r`qOCm3,%'A3Ze>IPQ,IL^W*KI*Ru(,SReITn
CIg03'<WDKD#4J:N!J.L-b`UHrT:*ndnIHkiXHY_Fh1TN`;.e&.h&-9(8;C_#P5/0U?[Q,$m
VNn>d#omj<C#^pfS'@?,bjn<,q3pd.c!i$fZ`^jD&$e?Tc6Vd1o4o-8'P=bp-2<KA'Dd@!^B
*gp9nOhHMHie=(hAuV((@St<n2bOVb3SLYi[eBXkBJdAnVOQ5U*TK^+&R]Ack;/CpOm%r*"B2
mO+\&0&f[uuGQmdT,&tQ[m4s/AF[,ZH^lEZ'kKJ3+G/U/B2-nnX,[TQm\&mQ/N<"b"2IO^`G
-/>%VCMMdeu^NhNd#C1JR(IV-J[k6RtdP+NkcR$*fKF&$=TMV&:c]AQl]AW8M5C*]AWT>4^PCi'
Hl*tRsJfIp`-n.p!F0YGD/DB)udR=^^nS#=/5J`-bc7m/0d"P(p3Zu%8#iM*'1Drn.f/sFHQ
f'kg_MF$JJ`R%WpRu-O><]A6X\r1m+2&e=J;(,9.F_\Na6962!kX/1>Z4gQ=^e%uuilqj.(L,
8;=TWOt!EM>[&0q2)IS?TH`QV;a=<.7OS.`M"P;-kJtrZ3Vs<\$0T`)Gogs!o!)i<AH.UHF^
)EOmN'aVjj-HT*D606_>KNY<Y.)?!s@4HC16eWXE#(mR6j@&RN=5q.@H8=Q2T`k_W>!!_MlZ
'S!)Ze-A&pZY'31FC!I3O?Nh/uH6UfO(k\LV9iR\?R\7?H^1990GeAC/ReJQt*RN4/Do8ln(
\9nZIcnd[jCZ&b[h>Kug'H>+;uU4<F,geC8DA6qP!$Ps?+8<mAKb,N/\I[T%jgdTdLe+'L=8
6=t5m.j`a,Fj?tU-LBt-k+^ahd1boo&`BZ(O"e,grQO>oo3q4sUqZN+;MH(afmgn^,)$7O^`
9JV8D=2F0#fsTNL4Rr`;3Leklfc2&,R,*J]A\_=Hj@a:8#s(EF=Ir[:FBI.b5tY:Paq!.W3@!
]A+i6mR'T\ab3.T$R'_kQM79JD<a6UgapMW@QdRgZeUSkM1HUP(V]A]A's89uL#ihFRk*JCcEuK
p6,p3t]A?Umg\7<>:Q0mbRhT<V8f2/X_f!j5?>'1eQ[7anQn.Fqjp@<7QMRhm^?tA:P8e-f%Q
!@Zf='K@]A_4ZGR]Af*[ZJu/h'+,oo]A'-Jq9>GGcM'2\puu4#NfG<X$GRGM<K^osL`%sUKAWXW
]A-j1lCf-8ecoI6JB[`!Z&ai@8:Jk\`r2jZ!.]A.1IG&<"*nX%Ya8uSf*h7]AXugJk]AD>"o76O=
!''iZ?:KW05@J+6Pj[#5'u-m"GJeOMV_S,Nh=Z3@F;9h#Hdmk)uSPYuVTIk;BjEVR`jLp_RI
$09M<@'aqoC#(QUo=`P[!"3)_?=nLt>0r_S7"/0>h;'slJ/aTW.U&fXU6`RG<o-bf*M)\eoV
'3]A3Z'`(gB4)4KKMu$=!MfB>#f'WAGOo:*B\?NQ\FT$:^XW?jcc;b/[>NG):&Dk\#);9.d>6
f;FHD8<Z'6)I.2d!2TbFkSZ"$''mW)G=Y?MQrIpSB:bifHoQUO@RQ\@h>CP]A@Ho7ujYQXD.P
o)<Fg,#YFac%nXp.Vk5J=&QTs)O,I-TZkKf0R]A$Flp(t_Sf%j:DWVAA$&\`W!I>t1Qi#6eUW
au'$-2X(n1MS_r3D.=qSF,p`%Pk[nNB&'VDY2E"3#!MWfhTY;ht[*FAdsD[H&(./Gi"LC0^>
9'Nos`i0i0u're%HWsZ:Y"fH0t`mYmn;<B\XZ`F5gm.0>\YI[+KDkn]AqNHQ:SqK@..:'A0P`
@tYM,.!IS,;9!W(+**&ED:GO:lb`?%q7c%EW[eh[?1`o[S+ua!8SQ3O0qU(mk.':U9I!oJNH
tAPsnu/dOF)pK6CR9b#/;omSa>/5@$;b@$<D:1MJH`-@fO;b>cc.BM(Sd=uB[h/TiVgKO5/]A
)"uEIB6mKY9D!4T3cSf2XXRF58:]As%*%%t8k;)]AGP2gYM^r(qt$d_>"4Dfu91t-jU8(l=^!W
QP]Aa%pJTArW7"AI:;_;V=Gn`?roqn<g.:0[crLF2L"jYO2\]A,1G[(V8bOp;"[<@H<F,Wj`&s
VQ"t^TUY[,.SaP68&i,]A?\cU4K$6DV3*q?&[T-Q]A40Yk)JTJ_"<T1_+CO#hd0OC+M`0i"qN]A
nF]Apb,&)h@BI8Ks$.W8L3.O_/m1isE>0q2(t2Wn95=Sn]AgBSuZGm4%8jp:T4F"Va&hK"_/JB
cP;bh_F+M\g#9B/SGb9FXfM_P+]A'X.8p_qs]ACbWOK6mV>^F&_q0X6Kl`X7>D\un^q/N'/9@>
Op$T"<]AT+j4`-FO3YQ`SaA2.s85W!VhL:0KMIs_p$Ql*%s1nWiGMU#8BQ@'b.F)L52H&teR1
-DL?fP")P03Lq`JAUu;WmZ3-hftkHC6<uJ%I?Ih4&Y;Q\G+D;D/G^!fppLO<`l)>8#23T93)
G_?q(P>5-k%_#:Qte<?F*/_0Rjhj0Il<l4pR,J`l6mZrgd:1Hg#U-\n3$HOtOJb!f*bH&)]AL
4M[gB_6>*L[]A`6-5#j#=WVthF,tnH:+Vi2b9&bfTX*c"V274JSieF&p:K]AKnM05OmU!E)PCu
3cT<Q^l5Xq+?FEB#$kRV"Eq9pdUd$[g>OkRS7rD4-`E:(IP%a+"2O_QrDo,7q(V:?"Dg"Q_t
mq:=g)<i.)#<p"IrU4o,.5FuHo^*APHHI\Wg&-&^dLVY`rB?Gl^Q9iQ%5nJ!J'sKZfX7$fa1
o0O:AXpoCnGR1*THLTWPP1<RAd-0XgnHHPeEMCLB>L,'N'`*$GA[tXD05`VLgHpe7lhJ.3;c
]AVNIULEW^bLph2&?-dRi?$Oe=[Z?BN]AFT"S&Wmi5'IpZ@4WG9D^RsrUE(S4l&7bds`<R1QJ/
6T-_AomKTh5UmC01F/#SKN1I:!h<!,uJ)*0>Jt;i^kf1A4UnQ2+_M:qnp(e;$/]AGpCO`Q$cO
)Q5W?r67QfOQ8a)7kE`1Z%g!Pk6n\)0KMhEeH]A=g:VD#UsiP_>S^'e\/&9&Tg_'u2o._sBL>
ag.%^!*Y+7]A9IoL$grO"au8F7o&3tSZn-Cp"rD*WkF:.o*hot:j63EY1a<5WLp59<hD8WZo/
\Sg/2Q1jLo0(V1kD]Al4@>!f<#0)<`PSpZ'iL'+NgX0<3[[7:i%q=YO7WX;4[Z6)mHnUl3dn*
KLplR\'Ak;RpD`LC:UQ0g@.f>6lu[Ks%hSH`i,!WG%F/94c?1N]AO-;F>$eoH>:h2JLiNM9`[
,V9`7J4aHG9scS.WM5+0.epEhGh"-.FCTl%Ea0h%eGhe?nYNZdGhMc?!1NSQ.uiE;P1B8dJi
17!ht1ZG"V`$OZ(`WBB+He<Y1+W(6s=U.\b=!Fe9+kpI)b%>)l[=O&Sr&.m!jb%Fu($nbKag
[@Z9\+^96in2fUP;>lN0aKG!3GcG,K*"dN._mXg\8GD.R8rf9i&,)*W&h"jEl4a8*@9h'>oP
Oc$\+:D-Q<YgE^K?/*;<rj@ndEY:Far#<Nas@Y"Bb2H+7*?3]A-:raec=-;jq\hS#I`bpS;OA
YOr_@`/4'9nFn38!<SX]A.*/bZ)9DTcPD,.52S:9\RA%8qgp!TZc^>%36d#tOEO*b8&WC_0;V
:R[?Ak7^DanCpX2Vs!fqVR>&L.\@jFH_ZC?SbB.d"<KfLRn5]ABX&YP@dB0_Za\Pjasa'bdC(
?iM\,9YgU=T6[NG`Cr%,85Thi9`DI3VNhgp>F@-43ok<3rI7rhiFT#B^dl=RUo)[JXNjL[dY
KFjb\(h.)q,J>eL!2-NDWOE^G*D.Y#Viua%LpXudTp2[6"M49Ua:`_J:MOR4k"QlM=!(m!W]A
C?$+)^oR8dFOcY+*sDT>-=_G;Y_f@jhN7,a^,eIm4<<D2n1pK-7`P`\U%SoPmZlWe;kbBO[l
g)E.(39b8!pE8=.'^4C*%kZk]A`(c75Zr<)k3<3@^FO?Wb!<_9Z@.CHGTW:7O-_V9HD]A13PPC
X5kAl&8.6Zrrk$krQUG;gF9$S,GupqdbuP\p3P7i/"E/-j+jXgn.#2S:_aD"Q__r6Eo&nq1O
c-rY<*F8+SHR.QB]AV@j'F>b[:)XjUk2n:r>5M^"1bLbK!QY2a@8MKMQa@Q,(:7=6P0FET8lL
:'W"sCii%O!;!.?H=m]A:#]AiahBA!VL?13,p03ka/*A%c&UHD.u`QWF:C8_XQA@'[`WW?k&[U
co]AQuf]AU!pE:@e?RqA/F<1MWn2B!<.H>Ua.]AJQ*.1_VheR?EMbO*&gr^<]ALZXn<V:*<K/hg3
#AH,_kk>OHZ"FthX50$Yq.(Kl2LKd[sRPlW1luOQULC0a1J.jqLOIYQ;J>SI9#Em5YE'f=p3
UI2%Se'4t]A`Ma"7.dVa3=QWM(RVih5KZ^`K9R2C>bs<i5"&>G)GjP8^\K:Hcbcos$@uE1#,>
L@df"?Jn5C*>gPmWMjGB5f01\SQIn'!]A#l$3@\H2R>"pGc#`M.C^WH08Ns"K`6+&%I]A/:uDD
F?82?eh`t#@87a@a2ak7M'beXQ&kemO_@l"U!LHNVKY>serejJOo+0k5"h=Adj':248,t8f1
ia<7g+9"":j\-*$tnbhL39A=!#aKl)6X+b09LQX*o8%3*:i;<1Z$,*#h9CMjp=mn'Gk=J,6n
~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="4" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="245"/>
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
<FileAttrErrorMarker class="com.fr.base.io.FileAttrErrorMarker" pluginID="com.fr.plugin.reportRefresh" oriClass="com.fr.plugin.reportRefresh.ReportExtraRefreshAttr">
<Refresh customClass="false" interval="0.0" state="0"/>
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
<O>
<![CDATA[Tab名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O>
<![CDATA[标题0]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript脚本1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[_g().options.form.getWidgetByName("tabpane0").showCardByIndex(0);]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="2" s="0">
<O>
<![CDATA[标题1]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript脚本1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[_g().options.form.getWidgetByName("tabpane0").showCardByIndex(1);]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
<Expand/>
</C>
<C c="0" r="3" s="0">
<O>
<![CDATA[标题2]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="JavaScript脚本1">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[_g().options.form.getWidgetByName("tabpane0").showCardByIndex(2);]]></Content>
</JavaScript>
</NameJavaScript>
</NameJavaScriptGroup>
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
<FRFont name="SimSun" style="0" size="72" foreground="-16776961" underline="1"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94p;;to*\Fk&\)au7RFXI?O[eRqn''ZEsDU8pGc`u"TZ>9>Rbg0t\[Kr>T/#rf>/\'[@sGa
-[qn.rE1enLR_,"WB`X]A)#R,b`0i6\*[\#qn6M-9!*7Hg`T8op"7(B2n9iS5\UCr0#s\p[e3
0^7XjQrop9HRihUVU)ufm76UM,$_(I_ML#PhMY_neOdYiKI#i2F7RaS+^US"<YB)4ASZ<71G
*fo<HIna6K&+f$_j0qT(&"7NHT1U2Z/J,]AK(J4;54/!OX9fn1^EG)^TD,gL]Aqm)<d05H1Nns
0*YHMkHU>\ol#Gk`=+1Uhl<6!ak3M"r/h:q5P'0cF1^A'MqKh3r!8Ph_h;Vj5"k;?Ur.Ku<L
LlHi]Aq8if&PI8%%HMKKYU)/LPn_lF&htZWXJs,obPp1;<1cE>N6:qUF/s^>#=/&2fEso+=91
r:'qj&u/lI,0&XF5:LKXHa19jIMN^EgF_e'*G,BaN'dhW"`n_mNXY_^f^Qd?pdbF;/`m[HIq
/Y$UYKM-_DS443k]Ak%`MBg1'L`$e[D01tQ)i%/!cFS2fC4qrRhBatiU;lR%UrO=SX>O;/3VC
S3_IeT&doIIU)3P@=rdIEJ>]Adm\(2AgY0'8i_>J^JlQKM'%J3<iVjPlgSVleoL\'FSMcQ=4?
CA?b<]Ar)-SB5guUAdCn(E#B2sCqqfA2j3KdsF$b`jkN^/Z<pO"-.Sf>sq]AqR"<"OsCDLa?+G
F7M.k9o4tkb'OQI93#)PprboQG3rlF<tYt%2n)<pGB7Ng5at'ITMDM/N:C/UM5O"0\(Q/llr
?,o>HuMmqQE,%+f-^,.LVY.TB+L/&*B@nGL=_GVg@S`_T&ZTF21r(nL$@RhOB=r^3[E(cd5'
[qG,^*FMX#klXs=dVPE]A2nBok)6L9]ATBt-UZClC.Rg!e%I1jVkW3gD=&X.Iq+HZ!*l+WFF7f
]AK'PS;cH-<u,C)c]Aj-r4:2TVX3"qPe]AACBoCXCr3$X#riADBk_\Bt9`P`%"pU76C<Rd))$Pr
`TM]A6GCcr=IBP:a\A;_]A<b/"59n`0V-8Y[kq5>Y4-9dCe,76G&ofI>FX@]AD$)5!e8a^ogNUU
Wcco>0WDe6Nl"?p,4#\_InFu.U3up"DGrhbV*NGVP$?s`HJ"8%3I?m.,H+%%rFJ8ccB@X4G0
=MBAAq_a"<U\W;?<-:ELDEam@"YsHu+HeK&8<\J`)?(dhe!L&au*TMV/7#`WLfEUl!olXD)?
Ca(4lDV@j,U7(.c"S)be;(%SN)1*/rE+_i"c/!P>>*,:ua`CGAX]Ag*V>OiuXM]A/2,#L..>%T
W*#W`j=/coE+Pj/C&QgWe@b*,%&d,]A(XeA:O*q'T&s=YiB!]A`0<:7INY)^TA,X-ZV"S6+*oT
OqbK=r7]A$.25MPWm1giSbM`0Ij!MF.#pW,\RDFu'nHHs4coW07Xm*H]A/D:_HiQH?nXiDAK.c
'i+KXDIe-tEg=T%hU]A;$"s:.4:J)oq%T_L*E.tYDmRANZor&(Dg3;%5C)`"lF,gIk^[`4(pf
p6i*i=tqJVk%LrV"T/YHCG_op]AQ)r"_Q!lU!'KcE-.=km5>!;SeupliJYd.(G8r+n`J`"*=3
R3i?@rF]AIfXBOD>'Yni\B>mF`5qON:opQ6+u:u:Ec;`uLR-<-(KL?^e<OhjH%LH@25f&.,eA
'e?`=_[k/-fQ_B81V_SBM!54Lds6U36/"tS)\AcP-iFhG6^>`(9`d,76-pqL<F@P0@GAf:Js
3)N^:=%ej4rrPd4JKTa6&F3;/N$5F^_uR-T1SF\NIX,W+iGre=9tF!sYMa@qF$KiD9>s&sLd
GGL+X1C;,kl'21+4R67g'S@&kJdUaMFj_B')IfSXTI::_l_I6a>MBqnAp*S47CP_5E-!`oXJ
9GU)UhLQ55D$3s2]Ac6LoPpTLlSXBOHR[*Q,LZ_Qupi(4AqgOY(t2s1T.*[T`ATaX_=jp[>_j
CeY]AFd9k0o`80Y>XUt;VF%QKT>%g?4>h8+L@E9nH#Y*r'K3Lbo8U$clI6c$*%&amLpAh+0h6
2^/MZAK?CF&[h)eh>1WW1t+;NTj\?@T=iIA0]A6O@dG,-EH>a/"(akPDt'Vi#VV-!R>!M^/GR
X#EI_Ua0-8Kf-#.qj?B:[Fh4p<q-JLL%C\!d0S;PYYn:mI7`7rTWW,cN1=bVuXg*\c@q&1eF
G8LBu02]A[?O7J=So@u2)2m0rsF1TC5)>lJ3M-/BC4dAOXBHBIr+RG/,.0H\JRqLiXL9PM[^j
+)`.VNqrAZ6=lM1!65$YejL%BBqh2p>"]A\R3kELF5N.mp'V_$aV[HI!2!libAodk4ClXCT<N
PK&q^ZK<Nr:W4T%$;>W:dQj3,P8D3W@@cJd-5P'!dG:S,\C9(DjFlB!W=5Z9C9#[U2"0)ti<
`APqY\4i8T34_No%[H$+,["+BiB5`Th.P)F8r]ARTOUTgodEi5:&/9t@ndD3[1;UkJrgqMPtg
[(8usp7,]AfW%,#?*WiYE=1N`QCOIDL.^%X6S%Gkl^Q*<LHgZ;CO),Ytk#W?R(AgcO49UoINd
F1-khe+;#QW''_W]A*A=Xh/IG:2lH3\Ho&:_d0Dm?G6mmt9R47*[e)JB)I@WtdG1DY%mp$sJU
'-$3Y@Vh&`1PjcdcRFL4\`rEQi4'5hqE0'F_DG%X%8`'r3fHSYB>bpmoC=0!^b9++"]A*M*KQ
PSK^.$;G%!WC>Ui-X+LY+=jC,"V+HkqCtl>j)7ANX^%#8-j2rG"nbt<'<mPmCJja$a&Aj9dD
YO&e)bS#9aDefRJh*UWbVIt]A':F$*4r$u)!^>>4=.R#6d4,a5?c9>5FBR=KXZLs?a3ae%Nu@
IlAg)*Z$`to3#Jc,7Y2CM[H>SMoh:Sd+rY#j1"?r,p`NUD*aC+ilh&uJl_`KR&Hdd8'8%Xd!
imAE$mk5Hl+T56mj#JPqF]AIDgB(?:]A0O(c&W-%@]A*^dD-5A*5VS'XeeL+F@2Mn`io<_:dJgm
:8Vm-\p;ga5bVIO<Xp^!F&I_h,Y39t,hhHN9$gH>4^QW5,_s\S^<$[3M:u+KPJpKWXmTYT;K
r$ras#@6Ilm"]A%@I1d,ib$J.(DOD)-/6p:UA;RlJK:%sj@^luNVh#:TCp1IBT8N\-hZ-F[Z'
<$8P>XAc5?$N=on+U`^)5$r,ImsERdq.X3b(nMqR"!4f#3eNbbc?8O!%6X&YY80nT?J%XI\-
f22\Uf*.,5`BoZRPni)Mu'SKI!`fDoDhlr?Cg;ZGRr8pJ2lL:Zqe0aohCrjMP"$74o1Ynp5I
@dKW;>.XEN%UD*<ZN5S1mUB<!W81*/pt8F!@LqdTO5'dZU6]A[,AeQ1<_`fgfY^B682;MJPaf
P08,,,;9!i20ki%sh;WW5'W]AS]ANG9\o6uI/P<Q`r\tM^rT-)AcUstpS>!*ipafX\nLb%Ou4!
`O?M05aqB7&G&'4C,_SG5WR=du$<[ts90Ef8#r6B6dTf1H#'c391]An.!]Aed`>p9D)7Kps;UH
C6j\7=W&8SaY@"bYQ+Zl0^dj.seBoc@Rh*=CQfY^?<$dJ@?=kem*m?lb_Qu'.DH!HCh+TI0$
ndLoJpbnj]AncEd?jg>$!88@=k]Ar%k9W.3rr\b*eu7(jJ4KCVu*\XlXGm>>g<$NpXbi-2lls#
#KFOld&2Yk8VLN%Hm";rXsE_>&S$a:@0;do0f@>c.>cuilM/:o#*ClR*[AY`CoAZui70-KFB
4"bPqH>7hBdH*Rnl7fNRD_l)^V-<(O)%]A;3MUt'fCh77"JU0U]AGV]As2,<j^buq4)&WF1)!GR
4G5(kd'`\n2d@&a5J`&bsE;_:+39gQVOX+:%<BX#U!,>3`NemeT*)O%h;DeNuQnc8SkV^2oX
IS_46bb%k`8&HFVX_0iEiE@G0j3Mcn3(roH`)?R.$J>_]A"Y#Y\>M+^._Mpa[]ArJdbk\`mp_G
btRiUU#.`Mm"(1V"6a*',Qdl2LthXj7-#_##X/-Y\962-s%:iWZN_HA7JD#>\pDG]AEtNFRNC
p"p5t9D<%Ki)(RXQ7u7i<C$Em]A:S!`D;`49OORP4!+qF7i]Ag>GA9L9DP!'s?HUIaB-=@9F=B
LK.B:FTo$E,$:/S6.00nksAF$PYe`!7/YEnAPk+u9Km\Qo^naoljHJ.CLaT3r9m_<1;V".;I
%NuIHoW<6lV!r2sr*j?gGjku%^Q37f1L$S-p!>6;*?DDZ7"Ci`3lQGQ=oE*Z"!Sq?U!U?c\>
0'pmO:UE0^@t"fR`V87Hp@f1Z05m0g$t@N.-R8"nbuc`,L]AOllm4o7AhXDP<OC^&(J6`59q_
(PJJ6/&eq3c,_=gXb9'MK`&)>g.[d"N9pKB4h,cK8BSQ?(:29?$OS_rrO37m@DQkNt:%78>s
5@4g3]AtW^%Y43C:;P*fq;LT"MoX$]AUqsU2B7l5A(f`^,-of>@_R9!`BCDu#T8c^D]AEneqB&D
Y273\uEVn;Y_=TO1-&78sbt)_@9.plGqR1_4?PWTsaY1@JUFOOPqI:"%[el<Cc^lkhg'XA!9
lcCQip^`>UmO7VuSc%uClb^h.9k^P6L^O=Q9^O#:LIfK~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="960" height="245"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
<Widget widgetName="tablayout0"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="5"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="3aa6be98-a1a0-43c0-b8de-49f9d0c7ec99"/>
</TemplateIdAttMark>
</Form>
