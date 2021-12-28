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
<![CDATA[SELECT * FROM S产品 limit 10]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
</FormMobileAttr>
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<WidgetName name="form"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="form" frozen="false"/>
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
<InnerWidget class="com.fr.form.ui.container.WAbsoluteBodyLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[function dynamicLoadJs(url) {
    var oHead = document.getElementsByTagName('head').item(0);
    var oScript = document.createElement("script");
    oScript.type = "text/javascript";
    oScript.src = url;
    oHead.appendChild(oScript);
}
//封装的方法，无需任何改动
dynamicLoadJs("/webroot/help/css/Qbutton.js");
//调用方法，参数为js文件路径]]></Content>
</JavaScript>
</Listener>
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
	$("#Qbutton").click(function() {
		//给开关按钮绑定点击事件
		var state = this.checked;
		//获取按钮值，返回true和false
		_g().getWidgetByName("report0").gotoPage(1, "{checked:" + state + "}", true);
		//传参并刷新报表块report0
		if (state) {
			$("#REPORT1 td[id^='A1']A").text('显示单价');
			$("#REPORT1 td[id^='A1']A").css("color","#11c75d");
		} else {
			$("#REPORT1 td[id^='A1']A").text('隐藏单价');
			$("#REPORT1 td[id^='A1']A").css("color","#FF4651");
		}
		//动态修改A1内容和字体颜色
	});
}, 500)]]></Content>
</JavaScript>
</Listener>
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
<InnerWidget class="com.fr.form.ui.FreeButton">
<Listener event="click">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[Qbutton.disabled = true;]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="button0" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[禁用开关按钮]]></Text>
</InnerWidget>
<BoundsAttr x="179" y="13" width="59" height="23"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="report1"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="report1" frozen="false"/>
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
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Margin top="0" left="0" bottom="0" right="0"/>
<Border>
<border style="0" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[新建标题]]></O>
<FRFont name="宋体" style="0" size="72"/>
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
<![CDATA[1181100,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2552700,4419600,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[显示单价]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="1">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='<jelly-switch id="Qbutton" checked></jelly-switch>']]></Attributes>
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
<Style horizontal_alignment="4" imageLayout="1">
<FRFont name="微软雅黑" style="1" size="80" foreground="-15612067"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style horizontal_alignment="2" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94j=;cgEj:*6nk_Gr[Z.O<W'<H!/jo`68,ee_479Zq9M'On:1:`rV3#UGqKR\UGE-p$9#,h
%A,3jaF3]ALb^N1g2@@:]Aj%?i>"Zd8Ou2)kKKXrFc+l/CM--!hTtcS0D<[tCTkcsrRP:8p:H-
b=Yo6cFE);g'u"\D]A.P^3iE]A]A;oC(!gMuC]A:;s!K-')6kL\i9X>]AjOgjGbQ<(j/c_?&XMM4d
p!V=2S+XM&Y"T+nXf358smBFA_6Bh+*4.4RJ!Rtc%l)4]A,rI#nj`WT93Z9Ohj554::,sJ9:o
o0)Vjm&9s=:Gg]AW*Z]AAB7EjAgu0L^*7TUgMuQ:/kPp>F.'/$f0&(1:!ZCp%k5t,5Wc'2-iG/
4WsDW$X#9"I*LrLaEu)NL'2>e#']AdKrsMJX4kR!,".N64R'op7m-D:V,(C[nrrC\ZdcR-BPA
lYch:M>+4fdNX\2TZ@F=2g]Ao>:297qNeO%CR#lmMRuA04YO<(I`6S<KdBtM$@qVP8kb'mrN<
0aU]A.RGB>)Snr8tP3lC[:n3)nXM@<c.`I>YYRsWYkkP:i"Mo2Xf2'c?&#'CX60L@u2rJmgq4
-`/1>`t$5W/a"]A[]Af9"Uce)4JK"t+?/nQ!0(-tib#?m0O>TJi2$#r:`K1<$N0$l7h2&[aS;u
stC,o@J\2X,g^_PZ5HU^?Ro-U&='#20SH.[)4EJ`H0TSXu=.cQ/JeqM,HN2&V*kA6XTe7SjC
CA;h:H0mit[Wr[#0CiqS.]A)lk=:)Z@K)K=Y_JAu%2`F%ZWj]Ac?p/ADu<sqSmVT<$aoV7ZYf,
jc5"T)d0FCPQR7\j>L;;og7d^mg);'4;UB5p#g;%iHfl*]A;Ajf'(s#P#Ifp;01&o]A"57g`(U
P=:0,;E+.(q/TaV]AjnbXm+j4rFdEc*M-QPO*)N::BM&4m'N:^\KB]A\UVQp:HG\2REoXbdh,E
Y\>gDAC2RjA&qWfD<2MT2s;8>GQFM2D1/`]A+j`mru6<Sr.pq)23bA0_WpgaPd?=0/4G..8mC
UqrJs.T^L>Fn%pq(;:srS?'%ANod3&/DVkKlKXqn6PaNKMqRq'19]AH;_iIR/!S(i[WGs/V<T
`TCg,$ZVn`eD<A^?64"ge7]Aq:W91rj$XKqPUR?;Il0Ss-nP0iQ)Yn1_)3sd6.j2n-_%%sm0j
L:LdhT-_Nr.btb'Ns;f`SrJd318+`)9\AYp92jFBk^jg/jui.t;.F_i%_*0@s&Ml.O`*;F<^
epgCbJD^DN8r![j]A+rDsu&h,\fGjtX5KF#.R:*c.[U2_C7#E:QFe#`;!)D`-8A/<^dd_88]A`
h8bq`%[lu7OMNJh3<4oBkT`05XnFp)T?-mTlso\4;YDoK$4Lmd9,B]A=$f;%Cm1o<RN/pfk!>
h2am4E#0/_RJ.>)44/mo.]A%4-4]AaG)8,L4V:cP@#UZRuoo>j25iQ&rr^%QB*N0@$nrY'-?0Z
6#S%00GoL%okV,N.8`hp1CcZi?bs.fEb9\i,\:(nKuF]A`D6^CXjQ@"&9"PpJ=-Ksaj#f^<f;
Bj'roh:4_,)FO[uBm>XE5-`EB%.#Lk1`pY;4BiN,Bj(SLd0Aj4?3T]AND(':lQ?u<=^O83N4k
XGR@j-E`X[44CJ_.A.U#4Z,DrSJ00^41Qm1VHSkU,@Ql?t6AT$(5`67e::Y@S5"i-n[`Vc1k
i&5GaFlNK&:ECMS0q]A^-QmS*4B`1U`qfVD-7WKVAX_039qhQ-B?91['%F/C2>=UMJ$Si?0O>
0G@+?/gWEqpUb_kbQUUSNsI+ag.fL&SuZP9NCBQ^7X<C$E#p?ikG5E"F-qQ5FUqJETU37Hm.
>H__L>j%gCnT$B9;[1RQR@*WjqB_`IYK7gGD]A#XO$_4q_qGLKcHD9?P8-j+s'PO-,kOM:uLd
tt20R>b0I[M1#QjPd`#l5<iF/\:gZR:*h#@!m6DPd#&dSm$-S%8_p-h=[V/r^G-O"\iXa?OM
g^Gd<4(KA.b[jdLd:[2IGCs.SPB\dOMBTr@qJA24RUN4MEa3bMkYjpt/e(NV;2*9Mc(*h@'Q
@0IgcT\9>C(D5i?hm2-<!>)]A9sJeV,DN]Ac]Aco-'IL&r#*@ZR([2N,/ZMsZG\ZI6n1,Dm??n!
;BiV(qso2FhMAGL1\&&mnUmpVV'4:;YQ]A\'NVZkuP?7H/?p5#36b(qV;0=R;,:0k_cm*NMrd
WMh$C,ILOudB4]AOM<snq5>=Qa5BlfLbG'@'%!b-QGPdjM(&11B[EVt!;WssCdF,u7E5.L#R^
U\:-.e<p.<hi#<8eh&[E$HGb$ITpW-r/J2:Uuc"K'MmFg3?l+HG3844*]A[Nb4bI=CJfQ<hB!
XLmM>U<D=ZMTi.kCB3p+8$9Cdd0f9H%YS;_6j&%F0=QPX2K5`VbDYXm5rFBn.oUeV?@\VJ4@
fZaHDZlm$&luZB:RpFe$kc=bP/g_d(tA$KDMTNj,'n8LjI'DU[UY>gMZosGDp^l'+Xtt:iR;
+TojGs"3b'[u7G0QJ28q8KqT/0;6O@@R>CR35#%Hq3fr/0H+6[EI$8()!kGLbVj#4_6bCQit
ascDbnkER]A>TZ=XIs9l6.2HnjKh7e*+$L<<4Q_?FE_Gib5<H'hHEqc0'#a.pMhO""E;(l6=d
rPnUY#F[=$@0(\Xf,ii^FhBiVeCqRbP,@PLqip==f$L+:Ah(03W:X#5;!=gc$;4.`^30]AI_B
k2O7ZNl4%qoCN\_KIZ&G;>S0=/%!ur7>]Aj]A711'?Ja5r:F`'l+/%`@sY#dBkhST\>MMX(i47
V'bIjYJtm2pRb:LrJK5gBU5W#2N_08*t=PU/lB"@82k0e(@/:o=<p5`2_iDo=onu7L6EeeB)
^8UHIj_j2E2n7KU`U2)U3$0tG^J;j'']A<(=/AJIO5f>uL)Li21dp!%S`RF+IIPIIh\4DE'[o
-%GgHR]AJHWYN?kn;j6Tki\Zh!5aasigeD3ukcmY_VuF;kk^)78V>MZ_O?'HB_4oVKKG3U_f_
o'B0!Fub0,AO'E?kG8U\!<pQ0A-p2.G+:"f"o3/$oU?do$GrQ=8B\?=Ze-g)S_]ASUL'+Vh-G
N"Q?>>!B9*RO]Ar*nYnc=OKG>cK\$R"T\'$omSI*;;\,@<C:9c3SoXda!p/GO4paX:'<(Th@T
.G.Vdu>/"8NDM;b:Ip-!.IjN\\Gp9PbT(WL):"VQb&`V-g9+ZAiW1p(fQL,]A1FD_U/TUDP>V
Ign.(.#.3dhQHF5\arnl>HP2M(DX`5_V5G[V*%T-BK0R<lF's%guXQX4$bttCc`>ss0Ir>k4
\!5pjUq]AmJb;$pF5A7MV^7c^]A_co7PQQ`ZqWUtU]A#b4Y.WVgl)*cD@$[B7`XmbLtkGb17@dJ
\W\`Q2!JUU-AUYF8NSE>.M&lS9l>N,p(Yqh/>9FS>3r9@<`-MVRfA)FAgi`tpeUq;j3]A%tKh
emeNqgGTG+%V5!lCKGs/_[8,Qjn,"5ZFIXu5/?fmNJ#U.Y7)QF#SE4.T_3;A.(dR>d\E_?=H
nte?^K5?X.gCDJ/*cQoc9RB'7e@N#!OXR1rNV27p8e:?-T]AgEnpeC-B@[k;JCE$V_VNnR^!6
ul'^a"OC6>h,?6F/rYg`%$c%0g?i*PG*hcgNAbN]A;%J0j9rlml`CF?GZ`00NbU!nFkUcTnkt
\U3J^%X<jY56WB[cf5Q>e@t<2::**ZgOD+;dWo\N1toXD8P"IT;/@f4.+n6F&)IF[V-CE*7E
uB8%1a:/r@,IRCpGT$#=3u'>V[)A'BX*SO6$6'ZJ1J`,&5e3R$9)7n$ZQa!R>0d"P,cj(S?2
*GG'$faDJD*U.,]AaJM.Xo_'3YSdhrA0l9oiKn>8*V?V5Nkl2t3,E!='L)1UH&aeh8DLm[WpM
280e+<oK)nsfG/6lc"4]Aj3jd""mBd]A*lL;)aG!SK]AC;cG*\C/idFNl,<]Aeh1dpH,js[g3nT\
<+1j>CE!/QPl4UsbCm>"26Pfg.`<T$Q/9d9WO+]AU1k[9T_6"J?64Kp(c'JjWa(5skQJpj-sQ
)2<LM.kMX\5fQi.L2caEDTdGD<+j"$>6$3<$'[]AM,S*:9YHH,qA4nG(&c=.Jr*Lls3(o$/MV
FXi+PJt@U^[#!Z,D1WAr67__O*-[*%p8rbCI04!31&ESb;%d@\9m!lT">G;a.Yg,uCHroKcP
bfUcM5$R->-HkDGC5,L$WHE7"]AU*f;TNTZfXqY.(YY&M-70O*?rr6lcRddR#L"G*uTo>AYD.
Pf(uli]AcFGGWCE]A<m!i#+CW%&Bu&Z5#'5%9>e_#9sl;h.'fEuO-u>XRmpTu!F>C+'"+_'Ho2
_!GenX+M$A1U;FY+4&D-jM;BO[P+A1A)-"-SU'&k>%!+/$N;)te)7n':WIF3E-5r&&NOHO47
5J@EjZLMM6lTg3&2oGNYeef`?"kcarLni&QAqQU9`bJ\HP?d[>4G[Arokq8BfW7StYI`_amO
B,t5)QMAH4167d*,(8Kht.S@+U.?H_0h.15T^ukmEs1"-h*L<qn6n3+M0sK%T6iI@i6#>RSg
qf-;ic@6e$;6TD0H%-Z@EI#CjD]A<_P:<o&3s@-Dou)H_]A?#`pMD`HPTV8Pco&=,=H,,p9e5U
jOB7pZXg7UH?<IcrK-7.YicEDW,`R<0f="9dtb$&hlHPVD1&7-fK-"NX%K\:J?h24o$jINN1
Dl-?+iN<A7u_PJZJF$[t.A\;HTMrRUYo07hI.ZH38H99;rlq1)@Ap^<IJP*e<BKWf:j[JJSO
+s=9U'5`s04RLEpKCa"AWI+=`^PMdu%-A@L%(2n71M23\nWO-HhaVeZWG-f(&%iRRj2K77CL
k-g_SMLX8jtuU^i(32!-DYKpj$Q(7aOH>S7TI2F"#.Y>!f2KpM8[U:[T1'?a:B<4lg`3^JI<
;lN]AXM980_gnbQTl6DGQ6MPE(dQF@-@6*CM&[-m06jbcS8.B[kWYpfC]A-hX1g@/P+0nU*:kl
HBNr2P;^RJEU6J+*>L!VG6XFn/*1_;:(U1o+FE)7Jk+e5fKiR4#2G,#fVXZ!1)X6A>a+@"1A
=$_%/2"h@p'sj'OpEl`s_k]A3mB_j[UWG"Qk?RMQT?r@2'#Aqn#sfoEBdS>L+8rAM:8rkb6LF
!O\q(1cbTp!3&n6o/AuS?ioE*1GC(`+++BfjB@0i!!!GmQ:qd%7s4t"oY:1WF..TDnp'`2k?
Z;m!1oRXq`VMBQN\@ga"lBA^)@:0'1P>/76=V.ail=#KhqW(Y(*>c-_'c"P2M[X1FB1?mTZ\
S=kjHWY4Q<c@4@22a8hIl:mIm'UEX+CB#%5964#J[!<IFA87P%AJ!fgq;q3\uL466uqj`H;1
@EhO`["1$ViOFr1L*AVK2"\dHiSJYS(/*@=Gdi$-f25$L]Adnkk/A6;$Es]ANXd+TM@<)(oHn=
MVZX3k/M5_0cd.H"^NQd&7"(-N<TmBk4[@H7t$QN&U8)';=b6g#@;l[Ss+ic,i0b=?_^`op.
BtAa5P[C/,o;C$;g:Zk&*]Ad*``BH2nA1*(1%qPH^b0A_SO$qM2LOL]AAeV6hu)(H:P!7neM7X
4"4`uK##"G"F"]AhR4ne_[<&YWplZ5'SM![#?Geejd33#10b9^loT-OF-o?nYZEd[s$K5Y>[B
YP?kE-nF[CZb1>qSpoa~
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
<ShowBookmarks showBookmarks="false"/>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report1"/>
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
<![CDATA[1524000,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[4457700,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[='<div class="ckbx-style-9"><input type="checkbox" id="ckbx-style-9"><label for="ckbx-style-9"></label>']]></Attributes>
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9=@.;eNPqOa?$N'W-!e*A3`iVoNKdUI@Uh8Zf,I.]A/b3LK]A@E&b#ii5RE)h)saG>&Q6Y!Ef
H)2WO4$%8d'1P>?!4PK45*35`cg*,U%`opHBO#XgZoN@30(,q#N^)*rP_<hdsLe?11too<WY
m76a$8GLHQmGlI*\DuL%C]AYje(`IHTk+o`/j0?F<H<U@3P>k.!IEa>$XJUT*/Sh9g.rPS@op
qnK:9UYYOesh$0O1kMTKAD#^;7GN7Q#XJR1NqPUDp-kLe,7M8&^Jl.hHO<6Dc90U`;n/O)8=
.O$^STGrbm2]A`kn'[i'LlP5?J-$RZ>le-a)6eQF]ASjCeW_n1AY9KAVDJR1c3u]AG;ToFbik7g
,e&QmV1_(=,AWNaD+G"H:M#/"3jATOM>k]Aqk,n>'N)E!2;SsDrr,)CJ9R-K]A(j6WCU7"hf'+
N2/k<';_`UK;\?ZlACE!p7`&Gk.2Gd&8l[ZWLdLQ&!nN=TMpHW`-HaF8B^le\h?.`LGf`eXN
3hVCoH8O#?%]AeRHq1F;\6\[$aAI/JH1%o7rA\(mMKA/tnp@\W6$YqF8Z11h*h;ek@u6#VSiW
!lZ<K'",@G(*B"l9WM"ZFUhl#MN4bQef[N"k*.\Kg>b!!`@)iHdn3;fQ@GPjIeN.i1<pZC@8
dWY#seAWi>Kn^9Ytpqns@&q"fQ&s%4\q1=mOH&S'J:1>+#\YORKL>tVtadD79ESWa!&_RNSJ
+e^!KGgDu!4FaRlQ.h!V:"c)q!XS+d%A/fp%otRnRHL0+r;?eGZcsesKgBGFhar8[5bPQ4(V
icEP)pEc9E*Y;fQFZPLD]AB1)1dcOibSL0R%[[=GD]A?\379nLp<9&M'_S!VdOd2]AI`J;HYh-E
jA2]A5CkWpen<.(\YL53KqCo0C=mR_-UQGQYUO:KaJVU^W*I/n>7TpPGgnr60;*\YA:hGd`i'
\T[,E/nWB6N2]Ak,&juJYjI+S`:%6cOb<Ai_Rj$d,Qtr0e$N,-BCB4!fr1>`keQ\MG>&kS:@N
l65[36MBoIKgZo)NrHl[PO18nRaL`oCuERhkXUot&,-^Oc"CR-<un)X0.BI8g"2<VVR%Zf55
]A8Ae+A_nDOS-;^#X$kgRT0l@*oHK$^@h-i*pY4!bT%JeOG%[(5oKn(qFDsDANo^=lHK<&53G
h1Dda0r*@>lEX#:Fb4m`A[;(oou@@/.@876@&/R/e:+cIH*9O"X<_KatI9V^$'n;9"%`&c5U
?,-i?3ln'B(9]ATtR!n'jP"D0c$%C.qi5_%rR`i=$GQ5u=9]AV'Z]AD[\eQ]AG9t7mXk]Aa!j:/S<
QGEt1%[+mK-G!ulC>cpMihAk#Gjf.aMI!-RLerA*C%Q\,I>I`k:R4CJ`J)c6]A&@(aP$mf&<d
@Un![<HWUHIUJTGs5S2R5<EEk["64TXqD+Q)BGag5oi$aq8RD7k75;;qrPW<Z?Pa0.K9=p_%
\C(N3oBlP%>Z%h[>?MEYnH>?2+?iA&8"m>D/>P;\:/]A\qRQFp'=lLu1pLEMR9bu+6A`]AcCG&
+qj/2!upC,a)hl5n`<==7LbMe;1c\2H'Mk'(%Z!+FKpp\Q*A="kIb*O_j+DS3-G%d;keZY]AQ
<h.9A7r<MB96f-XNW(;Y'30XQpjLuMXJTIrcJf%^8'7krs2#^r1>1q^dTFsb4Lri;t=,7j(g
/)#TraQ8d.,JZpVoGt:UOqK.a`qV+5lQRf?Zc$B(AHfaNdkLAg[bF9k7JE*HKX5/#/8e]A71f
+A[BGk,RW+/>[Ou/=%H7]A&8.stVp[.LGJ4ri4r=j2i9io,:)P>Ql?<.%EW-Fc-QYZN#/0Tu"
'd?8XB?c(!k$;65YZCmM#D]AIIJlhE^Y,;Oad^2-h:s%^o?J!\dg4G^o'+\a3O(VPVNn./=WZ
3tl9);9TKffrG+i-ZRF*grq[91>ur/8+S#h<tIm=eY0`%n3QlK[o&m@c;EBXgOt+h2iQ%qA_
YO^"&Q&=1SR@P/irR]A_jUh</R\?h9rhg7DteW2k8/4EdQ62F1,^IMPug>8jhYRUKi#JA*14X
g9_JSRuYW?n&oI:W-k-hdIn;2)[?-2P8t86$o5^V9a/,>?2h7Z`qmQXsa`3P+#X*5=lUPl4j
#p_ncuB>'OEWchLcAcf'b#I7Ks?\GqMRVh2pq]A\2On3GJJ\E5l;%^&+ihd"g7Aoi@JI%is(^
O*;@$lo[V/=p$BVMQDFu.u?rABr&:LAm^Zp607EN+ie1Y_.C!@Dht&_%Mc\rPXc:6MlbKtAU
P+aN"hYoB.&Z!JkWQ^Y8XI#W7'.uJDgQXUB%Il.FOgg7M\8Wld6JHB^Otf,1*m(E92$^3+=R
K'>YXt9;&3uV:rNX'.p3Qb>:h47t(\:[r\O2(HP^$j@rIUAM&h>29G!3]Aj3Gd!fErtDQ\s7A
uI#cHMEH-J>ucmJ)3/N`cEU+eaS4FL+7SHh.jY>L$Q+#[DuLR8M'9fMq$h;:fYdV&m]A"UPF1
'^&ns+Xc0WP]AV=2Va>u_G[6B,)q3Q-h#&>GJ4&R@95:na31BsRGiP-Jo"2_:T495g+nQm:B$
[%STrd9b-B7kcR7kprg.J\eAG0#QeH4NBnIDcFU$OMLDiC@@:KUkU_'V+M[M*9b`ZUd/:&.5
$l0OEfV9a:<t7d1g5KG&&XRJ#=fX&E2n*W307CFM[W.X!PU1?:A;0/sQlO7\H:P.e]A$hjm)X
KSLN!1=d,DUeq%HB.JY/i>",60RAslqiF&!#;g?j4^$kNe0XAg#Cn3Yn>Cp.fZ:<u"Ire75Q
Mhe<irZ%Pk0Q+IU$o[7?S]AZ]As8<LDJL(R"`V6gP+eF+Eq)i=e/C=IEATLO,^Xl'<\qX!GS+h
oLF_Q2?\p\(<hGe:Vqm$upj1+fCC.Cple#jM7lCeoFkD=FdiB.Hs$G1.MUq;0G#F$bbjgUfA
%GRh*LU@mOa55/(e&#rp\YVnJI)nhMOCg@?=InRqXjT40?U:@.U)28%j,AAX;<d1+#`V^Wi9
&G*YtUd-n?gS2VJ:K==M9tt,rZ9W3p*PuBf&NmXs-\&b4$S5(elT2\;Y22O"9W;gmU;<R?)u
2XI.@fMi72lYQic9-=i%j$)afXBj-=<4&H\=d-3qA;738j/JT1)<2o)\`]A^i,_ns6)m3/(JE
g@hB>'M+5r^Sq&BK5!QV'2iD[!a+Y709dsZo[sNU^Na@[C++N+%Z!LY-Q,]AD.@?n.s\S&$N`
V;I1kqm%//\*#Chg[U.PpSC)"pq@R$6_(?KD9\$R[]A`d>[N;<LR.3XXbf4naDtD_%'#n"UiD
i3CHUiA>q;0itpG'"J,pi'o;YJrDD4hQac*Us]Aqb1/5d$oGj>\!5Eo$Q`5d=^5h-?.^QXRK?
@;ro/]AedPeASV[9J[]A)Zc2Ic1.nQHIl.GW8^?X=J5At2@CLE6kE#NI4dLZr009tSRj"_O>^b
V#Z-MLcHJAL1asfV1sjfIPio!0i:%RoiMYDEh2^P8S_eMXWWX<C`.<mu78!d'$AE[_<L@4p]A
$%]AiJqkO!%D)7ETQ>j$%$'jd(djW=Kf"uJMs%\!YL^VV4AHYS<`R3c4"a1./-Q^8=<MR?s!"
pWnpE&T*a$3_b^u)VX)JL'p8V5.'_/@O1f/)%^*'M\PlV\?Le!/W"Bp2L^cLLCCD]AWSr;8k3
(qI,>>`p)MTS#5E4\?,/:I-.nK5ZWP@m#off.GkFH71]Am2@`Z]A/1(%^8PO=E=.%>7nMG_Jn#
TP,Ker]A$GO"G41YUY7L?0U,G?b"G<k-7X7#Z2Uonl:r:np*C9m9c,)`V*M)T>CZ2lHJgm72S
12t@6tUNR-J845GI7[r_B#PI3C6>^]As'RcEaX.mW/_OP[uS^'d>gtng7:`uem:%&pf\_lJtY
oU,$fcD35\uL;2Hc_bArU;f&ces>c9Sni_9p<i"=OOI@gku^J!:"cZC%f0-%3[=h,.H/7O7I
BQ.QhfCZZb3:a?5p"JI(,$BAY;MXGqar.Gp`u6DJBR=g4#W_S`5FZ>0:")Nnk-I@m4Dh&oXf
`S%sX1<Y-B*G3L[U560S_FQmqC#Z;"CI9N_HsaC2=Jp]A#Na+6EYWH\?WWmXTq$E8&NQo0l7-
A4S^_4']A,*l19a%gX4JX&DRZ/B:DWElp,Vn=l-gPE9Eo&'-s1MDcL@%4IrHmWsFLI>[]AZ(p-
dYLG)#9Jed\Q:/.bL[%@WXpf'FB5i>.j.]Ag,ru3884X5R)!cXcpeuEo%b\r20*I>R&WIK6(P
InN@U?HI':]AJs3Nf&U-aRg'f:YWuH:2$!QI^+f(mr"'AKF7S=2B[*j'P]Ac7?VH(=AUgu9f/=
KC$6'7Ea[p5@W*nQ5LrR%r[_gi5]A=eX']AZrPqqgKJ=8%)JuCrqXk1%9Sn'[D7PeqSff0`<\L
H\2jDhs6uLG.k#\n>tt>2mroV!5<V9%._,\3;hTA=*X's4)l0:^RY)\;1:`eE>mKRe9YVRO\
!2f'M[+o(uXiYR_[V:*0%B(,bim%04O.UDG#r:W-er?=e7=ipI/QUjYD^lmk[U<etj^R5,[p
,D'bIMhDq=L*u4'>!cplcm;9\F;[M"Wg($h>d3XTNGXD<9)9r/'NDU4Tn&!b$WGne8/&s^[8
m#f`eP?_o7U&m:DOce`lb=YrXTnGK8PhB//<D`O%Hk*bZ`;\N,Gih6cc/J^.p$iN"B21ea3f
EpJq_QLaIna!WQ]A-4GD_oKZ%f(/DTW7nTX5LGdl)@m^>5-P;*"BJODo?ldakS+q.2Mj6kiP:
/JLfINoRc*_1(HEZ7M%jHII6n"hK._NOrR8XL:n`@(@",GekeJXUifcVo=g94=\#M*h!Ci7l
bKc;Yc7^TKJ-\=-XQL`_mYXq76[+10R7E7(f.b_Y:2_<*;@RfQB4)XsD^SReIF<I?j_j4p(/
T$]AtVQ*N`Mi0Oomo8e`W*@l5KH[]Aq57^X?@dL]AcD8C/!Fu\4hGN5JN-gh7Gl,ZBWp_fJ"!<;
1X$9C`pqer%oBG5+UUiHM5N.R-#>$%'5O:&S^ZBrTJ5]AMO:\/g8>1J*?%Xo/&d6oKLf9_fI+
L\0"5T>?i95UF`'UrZ;(g84jQ1d6))lrn.:==@#RX=%nbUepL39h8G)"8>b&FnFR27bYWu(:
&HUAJnZ3-5(Y"Po7P=q$M_CW<B0@IhS)B-MNrfGRs3T%E6K!oS^qo$27o'8.JXY3GDKXE1#0
IMB'PK\p#ElR+m<?5(dk+A?hXm./B^Y((mj;%I4r6.i5N>N85>MI(k!T,WM;.riG0fIqn+Db
V'Wj#F*9'bfIj^j2]AS;67>fh0$SK@7f(*@Vpmu>Z(4jbnon=\`g_X<r`=g*e,-aCQ5!rA?SO
,.1`s#6,:ipW>lfuZG#n4+?_Rps5<>1s(^S7Q\$cA-)iblaam13V&u-:8Is,s^\-&M4,#X9A
85a3f-#Nc.N4U,b/_fJm4hWC2BmHuZK>Y)CHCmmUZLBBJ*Lc=?f,hIU7DZS)bXMLPIMd,nms
QQT2%eQMCG9A.to*pt`Hl`bs8\O]A2g06,fWOA!8Cqr&@[(-L<D1c(TjB>WVU?+J1UmQcmIL/
!c+:b'T%FL*gQRpRIA5+;I"Gi/8AM.$c5fmIO%9jrcS6Cu<0m>"'BJDd1RIYi9X\d;qC`7oQ
9Ns;?rG`#L^A9BP:N:krnr"P-47u^SFeDp.-TFl+O&%.d"4iYAX``r2/@-jNQ$;".4Elr%&:
W.?L\JJ=O'.W,Ckk^*__3F(u&,S.T0ocgMU^V*t&O=ul#(;(N@Iara+gAN4At(#J!1kPd>h.
ol,J*6U'$$-gSrXuH%2cn-mEK\=mo,l0%nU<,jr9qYpJTS/SPU)_kEHVT$M$=npoD4]AO&oA+
K!6N3+t;or;,&U!aCrWroW7Sd1XE_1dums:1EA'K&LZo<lX,W8m31S@bI+7C.Y]A+j:u&;g1b
*OihGT9_9cB7$RA_#hpo=g9^!']A3?j=k,rpYE%CS(Atn2"`?9JY!m#oJoq*U'+^SmAZ5d:>-
*<67rkRY)1r-t;oOKY-YZa,*UfUFSM=\4[[H<n.&ZdldasqC;q#W0*]AT!_4[mN7/TfGE7eq*
#<,LfgYh[Gf9J+cgi3r$_fdP4F56bOWR3.aa;k)?J_6m)U^'\[#dbe*mp]A>'b_,P#?&>o.4G
3V6$_jk?o\#!LZnC45#?4@^N%-\Ce*JfE=ArfI0bfQMOm+aTL<:`#j1$WX<6#d/W2X,r9=>Z
jp`t1e%Z7]AON5otU#`.bG.sn?h+n.*KWOP0CF.ou`ui1UA&[T606,/SM]ABo;L7VNkh!m#JOI
C%Ahi(Ku$;g8CLlUi9iDK8PW,1FRKO?a#]A_h`+C>Thn?gQJ]A%K5]AF[<qmk`mA'^gYF[PS"cG
CnN\g!>$cd0%HL#BKfN:Ql<,)+impjm1Vk#Akh0Oc2G@YL%XP*'OLuH*ZSc5:M4)kLS]A+Gm5
I>sp1g\BFVD"D.mr^>L2OZAu;0#u0AG/5ijS[$>QI6EoEnn9251Jms6EFI]AG6Y1[fFKBQf=D
/4"fR(nL(LL)dlbV:5&Og=9GJ'ie*C>^!;%1OmH4]AT9"fuqU`%(biF*S>Kn;u]Ak;qXDI^%N.
CW1`X=U%!VJ-1lgi!Sm00Qm9Fa.CTM?pd$!G<L8dTJ4?9fP3(?ea3H907.V:9juE)*cU5!j1
q?^0MA$"m^!LS@0)ghS"TU#nG1hYA/(gI?^rYXSii,9Uc^U<Xqjh@cjIWY=G'NlTA=_C;-W(
36(@oj,ro5IM;q]AHq5ejTlnO6Q5T;Rg%DBMFU\j(8h(GD!L$oT.(nC7e[qnqlr'RI1ZI!JW5
B.sbG'S9=]ATrMc5YSM1jbQl4,V#9F0cPoTRm!R!!=*Aa6-'j93T,)3UL#s$p?60^4t@<ViJ0
?e((3>W(2=j\Ys9?Dl9)"sida&n:,+`1ee9e!n+ECf4nK0'^0I8UQshm"Tu_kki0uqgKRX3(
$V^Q-$$QjW`GqndrfH-,CMpWXqBS*!e)V)/I*!Y5-$^,ddbRIAmsuC+K,m]APs.YI`XG9uQ8;
1+5:V8Q*eE[2.D%P9KK"=OIi(b[OKYFZ:`msj18e4@]A!q_j"VdbjV^NdS/[rRW4:nmgHNj8(
kUZ25YT\;a&:acak*BQ&C&54F*s*%`oh(-SD)@t(]A69[&!60lJ9SaOOTq6@IS1f9(Mc!K8mk
^D]A880IT$8i1*p/,D'QG>:plR\)dOnOBi8'"p6qdPs06C%SqLV=<Y(!58&->'.1(*&L25@(B
qckX.%IL?G0p0o_5b%1o;V)rD@$7>D58:D"G6R=rC0@F1k3iDAurq25Wb_dbZR`95W<oJ1s#
?koHZN*\b`>ToV@RYKDIB_%2XOP-[eg+d^D/W"o!o.Ao_!lU72[/FQHkG]A(j(%LjBmVe__7%
#`Cs85d<6VnKEpet",0E+Ho2MR4k#M-S/WOfp04*;(JQP<4PQ9'JI2&,k6PhB]AAr;]A:+[peK
/*pt[*q>J%*qhtI5fNo)ZbC1S-*1bHr)/oM>a_pb"qJc@aj-[rWmNI7^&[Xi6dK9(5Dm<Yob
Ce+b#[<1=g3Km^aCm/F6.#lhJ4%kk0)e;h+pP=@E.s+\]A@EiCe)_#?$JlGn5+cX[5:m(Tj?j
_"jM@F4eX*,r@L`Elb)'+M%o^H9U.n0>fSi_Z<6'H>&H3h0_WQ2A*T3IT7+Hd`Tr"g5(d:$I
.[WP`7,u)!_[-V,aHJG8&Xped9/SIeV2^/L<$%7\hUjO@(=5F7pnMdX%6[Gu'p8!dKJANVN$
EOb4),Y=4MNkK2?@k6N@'CU%f;Gk*@J8V5GKfooEs*&5GAC/1E'M]A-De#p6iG&!)!u',(\Kd
$XD[*?/nHt-Lk^im_2C[#a3^:[B4:+s"C+ZTLP;>5F;K"+;Ihs1%]Ab`/50)fe/tEcbrZm$l]A
`$Em2lkigprWO;~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="5" y="13" width="162" height="80"/>
</Widget>
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
<![CDATA[723900,876300,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[3467100,3695700,2880000,2880000,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[产品ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[产品名称]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[单价]]></O>
<PrivilegeControl/>
<HighlightList>
<Highlight class="com.fr.report.cell.cellattr.highlight.DefaultHighlight">
<Name>
<![CDATA[条件属性1]]></Name>
<Condition class="com.fr.data.condition.FormulaCondition">
<Formula>
<![CDATA[$checked=false]]></Formula>
</Condition>
<HighlightAction class="com.fr.report.cell.cellattr.highlight.ColWidthHighlightAction"/>
</Highlight>
</HighlightList>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[中止]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="单价"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="中止"/>
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
<FRFont name="微软雅黑" style="0" size="80"/>
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
<FRFont name="微软雅黑" style="0" size="72"/>
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
<![CDATA[m90fq;ePg5>.'V<3d+1r,SbE7/PYH]AZ7(f<'XY;XFd00W0qqQWQRs@+)2f&1Kik=KJtuGL$a
M9e85_u9-peK-7@(.`!>6"f,S0I/p\r!?7VuK3oh=,.F"6Vu%O[<bmsb#fhs^R4rSCVF2L:h
<=u^<?PUYb\f<*^gA]AcjZPUTPo]AJd\&M4N`.I`dlc#M-(]Ar=<Y_:j[?-3Vh7p]A&8f%?(qheQ
&hrFAf_/BrU[gU"1o<_c0a.=r6JHAR2lS/YY$WoRb-L/6]A^SRn+jJ0Bj@2r8[!JV?es7`IU1
q'bLSR0*M:)ff"*S1)M]A>(c2)cq5s`PrNfi,7#B0@nXLVPV?:$@$-.$_MK8H[grRn]AH%H8/s
UFWpjn-WF>RRgrYRRb*\/470l&*j_tTRrqWP#g/Fh0sTSEQ=f;^.QM5F&qAX>=K]AR!pq(9H*
\CfR(oaSas#:6\1LEp^.0>mFKHO@TUb4a/q]A[kq%4"_;@4us?1L$r,3A\%\aJ'6?oAM6S[]A[
Wl88dIGSa@cn1>"ooAB0N(<U*::VTTE4Ls31cHi\\^9ps`2=n.dG&gkr[2XJ6EP\J5Y;*IOn
0-eiE&A+_'5)TYO;*OuC[u[1Tb"H.fe2VO8G([NIIYTO^.05[onln5T)"fBMT9,@#Fi9uQ7\
UdEc)fLgkK@K#!_oX[KBr3/uErbX9e$6qDc[3`^A^0bIh/NoYo?L+^j/3h;QAE9ub65:`#96
Fp4FXbK>,I79!-Q8P9RmoB5VX]A*MiHmMk$#n$T.^I.+)sq0QatgLqDrcIh_%HeK3gaJ46=D^
s.Hb\,p+qV=Y2*&A6K_dP[M5<h]A-gpJ\jFaM1crL)gj-*Ig.3\0NBe=Hd2J&FJSfOm0[:<WW
+G9q]ADrEjO]A/%%;H]A1$U6ZC(oUjK0.reDeSe[i@5LPce=PE[,H<f&7jV_859f_D@?ZM-X2t.
18mg#jc\&e_i15%l6PR[&O#Oes4u(gd&._r\>q5@;"%TL<!W\2a?H]A>=U78(bFH4.2fj+#u)
3V7%7P.k\,,JlS.4[>L?7=%/F:<mbTf!EJA:<[c@gWrk]A1hS=Q]AZ`%JQAG;;jBm1&beg;\nP
Ck"VTk3Dke*H.EBONDj;kSMLh;Bc<8F&S8CX>o2\R^m5:=a*j#1I?6S-<-TR/hcLg13CD0$a
Npp7SVh*MVU,!@Z)Bo3@<6/7%[>OXf'7b:Sg1?dZMA1Mbo7c$P$_k5;-3n[[eppSD2g"FHQt
P]A&9N]ApY8KhKg8CjYXZ]ACW3qN`TlBpOA2iOfpi9a4=d:qH-TN/a(WpSsULs[_E-IpK[Fg%8;
QlJd/dab[H6<C`]Aj\_t:b)f%<U`_f;WBN5OEL5FK)t0c\haeq.F*+c?-cuFj%rfFk$$pq)a;
B<:a99q-+SuC=pcpo7"(q.jP\Ge_i6".SdAV"!DQMGG:1\:Wgg<AJY'CH=NSqPr,U[R@`Zb3
B*B@<IBk'XX>aXMO&Nqk:lCn.?fWIrFS1*>rD*2ShJC5E=l*Y$"C=j!!8kO-P=aWQcUjqJQ"
(JWM.k3EQ.,oLM(N'@HT%jYf&GEl,;(S&&PKt4WX[+!Gn-[`:P&nX)jJGrLP>G=IX1p[UkZ#
iTV[r0GqIU<S"HM@%X=)fV-JAfE2^(_6<Zh1q6pG&@(\jeAeQQURU@)F/<?HpF)$u.BYPFgb
10ZrgZ]A*u=UHd'L;NSe_W$Mt-<'_h=eC%U:8tp"fKB0^!&EJ/*-_*#7:c""/t>GK'KQ0.Thq
16naU,`]AJknaf8_InGP-"$?-`+3'0"aM""(Zra_e(&3kH+]A-_'p3Y%:*;*MO-o!,l]AMa?e`n
42&Dr&.;P$Wqb"dm*(s?(aX73Q#VepC*SuE/'/@Xo3?6,Dn2U<L,:m`ReiBfIfZV82Fub%gp
2I\X'$&Q'5C*T^*$gD!NkY9=D4AR>ls+i)"*3QbhJs8nfSA`Nb&Eq9*oU^1GMsWA!uAG)I"X
14Bod&f@jdCoeeV8nEuMEMO7:<j\KR4N5!%s!'1hWP$;5?X(@8T&E!q5Q6`ua2`p3Ji21UaX
-/l[("^=+Q-6`O>;tLW2a/5hs':lZ7LMc#=,-0O#,jV@KiUm/"UuBV]A3SNOW'Z9%!BRY'hYN
F;]AZ(U\/d;=[%/#3+KQb;RCBFn'M--KYb"Q\h)Pdm"b/Y_ii>fK<SHlZZ):2eC716'Cp!#9Z
jW9qU9,NY2q-eiKf".M"7!;94Fg_LFoa6>_5sj?I76kZlN_7!HS(5)%.nEI#^o[)()ANAJN$
T:rm;B)X&nF6g;-m`39SU]AURu&+n!9W12(sNfXdj2IX@sgg,dNgB?8O-m`\lWGSX/.?Qm82%
C;F.jH8Ss=Q<9$O]A?RUoc\#bjO@3'nE#R+0ZYi.D]A/D!=f\MS`Na;2,ijUKgs[/m*.2&<1u%
$WJe=A;.\_]AV;rOJ#`q_!-`(TD0Hg\Li:-6t/l`&AZSUp([q./(8DAJD+%9ds+!;fV=>ip`!
5]A]ATJ\&)E;;K"7UQ;O>s%;ZB*U.TF,+j'Z(2I0@b:RJ1^#1"usGi&/3(\G>H^sV0"IT5*R#'
m,b:DqHMg<VH;%_9dTD\J.c;m]AraQd5bp+OrUa,ZMsS0E)sDSWX>_J&_1YRd@>%0_C7lE.b>
`SVQP2O"duOZQ*!s:Q-oFfc#)/VE.:UG<LjAnI3I<!eK%'>1U`tm9F0Xh1MV0h3GQMCK'/%Q
ZYh&u]A7[01S)K57k'^?Eu_eJW(bRoFf/M)k6bmt27'Rg\i2F.k!3`OFYLUBiCFi?!+$6bq$T
V=6r^#HZ1n59RgiX--7?/RY!Wq^e2Oc2h9]AC$@8OD=).$GFU@?#&UlVn0;O-l%DeJUeFO!o#
(t=PZEb&>YSSANl8E//@4Y':9S6#(ddrJt/?nfYpZL,c[!>l=LVqOB?SQdZi'4pfQnZ><$\L
EZ5D!THa*pgUZhP5-\'-PTD?FBQ)q>#YT99&ue-!bR0%Sjc";e:9>/I[rY[J>`sUFL=(thl]A
`1-W"B`M["VU@g6-J*Q,+a`>'O/KPbHBF&<:>m<+(1B'L.meS^&OlPu!('O=3t7TX%&Cc4R'
I_@8rJa0`24h/Y_;RMj9.O';tf+t21^LZV/Vf\OQJnVh!/1V?`Q``k%)0p-<]AQd.J>fZgM3+
>WS^:Y_YX=i4hE9Q7iFJV:'fDSr;mPq#<cI]AF"(,t:2VBosn?PcR&1[lkK4YrGHc`%1ojYje
bVS<4+352T]AZO5eMu2!Yos>P/H#B55:5K^)m17bDjn.-RhnY[l(fj[7M-BXqsN+=Y3ePu@JM
`0KU6@?6-+lC<3fFZi\cET6u]AOpX-+j+Qt!SF,J+M\5TG<oumk,%d'Wb8TI6jBJ?kr/?;LV;
jQH$ab/p"5iIgKG<r?J'@(OZ[(W:`3(5^54b-@X3d]A-<`]A(4-gZDB;%5o[5%3>Drm=ZES`F?
HTe9"%>J0HXmIId@2f#l_oCu22\:brMSeAU>ZfTI=:4Bl'E2qa!*G0$F>08U%*%]AnA1J#GdL
uO`]A\X&reRNcB4']A"'fQlIp0RUDlT:2OWjYsYpkb)/5Xi&ofNe:"6.H$AA2cXc6d>YuQA]AR6
H^YOCq!honueXJR=M<TcB>m*"89@co319/e*8o"X#8IX+=/lY,VF0f?"a45+B/(:1_0)>IY$
kAW=tK1H4OW(RD!ZGP$CZ=MSlH[\iPr6k1h]A@F=R'b^&rk=aE7jdb@BDc)p8.ab0"1`iRa\>
D]AVk0!cWCSZ!P%]A!e`/.EtqKV23]A$g\.R"*j=VSa3E4/k7;Ei*jHLJ8]ADfl_lNYih=b=lKc_
*S1tn0`0(8f)Gj'+\Pci`T6R2beek3GI`*i#N!R[@_IGn2(_T!`j-f9F=6@n#lu,kcn\8K9W
>dK2)F0KN&CA`1@Rk*on.+-UpCEefB!0Lp4;omaob5?Q%-2jgJ.fI<*'6]Ap@EVHJ)UDIj3:7
a>PXct<"3PO`L:9,maMT#\?p!0:0qH8d1;<n<NFG3k<lS=MZ+W1Z^5HK/9mcs-4nl<R?@Nap
DYRo>SeAa2>VCiOnDj33Dh*Z6OA+Z'$J/("qHK<Fh[HEI0Le+99/nQ3na_*Y]Ao1f<0ffOM0i
Wa?Z*2*EUs*60')5Sd.W"F"R_RE'WVZ<$\qN\c3=iRu+T]A>mPQj1bXl6?EI$TJb$U:PKLs[h
Q`MWuV8]Aah*6Rfjsf_E)d#c>TN=tBL%<`J-4Q.!u*=QI6ZlJK'Xns[,:=?L";XK0aQ:o41+O
!V./VZP"AIba_S<N@[`b7)?>Ga3uSkJ\'Q*8,23.(gK5-mWIWfkIP%.^@!,`c0W/)(!8>=%J
9_:col,08J1QgK=[jL=WeKB?[^Q'S'@_r%+tsH=CuE2P$$_B$\R)QSEHi<\r7]APdP>DfAka7
G5WgAPuHe4aXS]Aj9!)/%H[Z*gTUcE$rh6@KiP+F2!W+d<V,5XnOa:'IN7LAUfQ";W!\3W,AL
:$BCDrhoNJ`<J^6mTV5BYOX(Qg.!b\?=]AFObd+m<EEDi;6Uk`GVS>c*l?-<fWPYNE>7n&]A9G
amPKC(-qp(]A4.s1@foe>]A=,`0Wj(=Fs<AH?BDs$I9QIYb`>Q:KJj^;p@/sjN/9;-O/?'>uM`
beqcps7oHZWCB`c7Co9dX9qH#sdWk'/iTB(H7S2_l4d3@'uP'daYZHY^\mISG5QDr&herH0t
9uXRi!E(`]A%a#>7jYZRL%BMH(<W+fb:5]AFr4u^`M$(9uhM6H[<.RrQ_>u`k)"6Mr<(N`T;e=
RDDQl7o(eHGaB>>T[th_K8t'sH9+U2l(r;[Ye^fLCr'fd:O'F_C',J:B9c_\p"i96!nj#m(?
R!LMqViH*r=%'^2N6$2JXjB=LK"Z!l5-gi;s1MWiN0/J+1PJJu[h4T+]AckM+b.H!<O;Gb-&8
a9[rdcESHN[,(ZhJYu]AWQ4h,I/IVhMSg1%YBXU]AF.="97Z8Pn2Y;8-m@a1tfJl=`QALmi:8,
8&?KkgZf+bG&5_n<PC_Ybt3M*i']A9+^'1+f<\e4%>SDiSXVY/[&\-/.mj4RA!N;kGO`+Bims
MD]AkK4&JLm!s9i!rgN#,L>1DA;F*upB=\N&kOgM@%o2Gknu;&"?5J.5aEF[&G[5A<*#.Xbjp
QaSEaQIe@gi"CCUGMGC>Tkj-XRC/#a/N&28:g/"DcbA*@=4B)+1a,T=1R]ADl56%$sdo0sFpG
(LeURt*kA%0&0EL"r>pj!mA/GQ2U\X<!ts#sG2RL_Q6^Zs>@$gATc2'0/hIBAI/n[LUT7qN=
A[sl_!?!@9NJ$!tmR46@pX?OH[nRO?oC0l@rCZ(_u/5,hdca;=(2Y`Du*kCO!T,KR)X#%'U.
B`]A_kr'kVF[<R]AK#Q:iQ)8F4%4_;A-dSE)s62=<2H[o"4)-*'@DZbs6Y31md2T\lQpS3*b&d
U_52?EVejVSS<-Ri:!;pa$mO/"#DMpFK3M@_:jQC,.o[F]AKY+8X-Agi\jG]A6QV(aEe7O[WBO
T$Dt<ch<sP[:b55^Ob&?M%lS1XE+[+\s*@gqh.m%BtrLUgN\^>pkD#q`\%qZ5*,lcVS.j^MJ
+a7Yc^A$]AI*KJdk"Fe<?6MFeC.s/^#G!IofZ7>6(LHZ*tBJga6JKk[>AsM*8+"?bt.b^G_-s
'T<ud3H2?Oe\XjL6\'[e6_)W*Gr-G]AZ1L6?]AI23.?AaBblrSa4m*(?n982b(tc<lWJmdMs)K
S&0OauPC,oKO<>O%K,!miNcSgdB6rdsm9lGqlY=p/4(=hQ,HMAJubV^uN,9o6:mGaE.!-^iB
TFe;]A@s03KAnBHk>Dbn;SY0/OCRoloc!V5D.jYiA\'DS7!Y')[oJInkGVa&gr#rWRfu;EcfM
\2[*GSQQ^pck5hg4jah.QFN3.n8-tM`Ir_YW)>":g3^.J!Gq^e/CsYI4*(N>Kk,i5;3O'r2G
!7&\+9m)j0j'UMg1O<1;-P$S&=\22[%JK[rq=`9QLMDo)tRm[1`f?*TMlRqh?Wmm&I*bmk!^
PbLGpjHSBR6LsNr'CFg-+5Ul6jG@HUl+KRR=M('(7+L&7!6*LA]A8,m%hNa'!G!s/)7T'Q."7
0kLOV!V)N7G]ATf(o':B9-!U4b5L3cc8h6FgJo14IE4j[(D,ltbhR2rGGeeU$?HfL4$^h/aPP
g>CAN3K(]A42#?Z`h+s(,/(7((4c=Zabk@+pJh./IeYWu+M+`"I`Q)6lg1_qTum7[XB[=6-8f
lV5h1Q5/9QLV)"_Zd#?&J+Ulgf-b<)dc8b!ZN%i._F>NFH6<'!@p4]ALD5=t]A"1`?K\0P(r&P
B:QC'I!H3_>JFUAYW=V@?VPnX7-$nSW+(=bJOAWK"i4*'dC)]AVS!*f6R-YnNlm\[+ommlQ7`
i,bknH#5X>a-MNQ`_6T?m^O_3^I7?X/L(suGkt""F+H9cWB5KIZaTrX,.a#WA:P0Y=an\lNI
m/?#8?B%<j]A%_<N\Su-\Sq8kf\CLR.d#n,mA@,h8eJe_&'[EhYLMl)goKGp;rcSQ&-1]AF5)J
M9PJY?V;hU4-?k/Ui8P,9dQE&A^^<-26R0Z-\%(rF/re(jF!(ju1<Dn;;bIQ-/AnUmKNpeG$
5_D-"9."n9m.k"+0rl!27Ch;*kqKuf%)\3K>7g&N($3H4=<dCa!WbXE+VZoj1#gT0)@B%frj
hp&<&7C&,r8QM.jbuUj-^Y[Fu,oT'&m>N;,CIA:TPfJkS`$Jmou2LmGu><eRTH*+#U(j(RUI
M7'jje;.ikD97`)T2UU,"']A%`]AKRu31p4l'`B>^mNDjj"SB)W($+p"66<KQradq^3^S5C;d;
YXgK]AL4IUMVD74RU*%X+!X5KCUe8'p`KP\OD_(+Ec1,[lCf!Z:RNtDh3kdF,g?*ZL0<<Cf_$
bu/fhDX/IMLLKt%pk^OgCKgk&Xcp$C^GD@oL1OChIp-lf7b^r/TUI50:g)j$HA7m_4DlA.u'
]AWUf2NId11[ENdd83PiTH0r>pkO[p;EB$T\n#6_&(`=;s<]A9i92CSL^D#14'[_suB9i]A7_MU
U+PFk&n(@*>`\oHO[=R5bR_raj<]ANUOSn9GLJ<h<6cTbknW_^;8=)PnH^!l(c#sUG-m5/)+O
<#H8@6YFHtOrjUb7W0f\G8s@fIXcXn!fOYh68[H6p^T@;<P2-%gi\UiD+KR9#C_0^SSs*Psn
^5O!o8_*gH]Ang(6#%`iZj_cm?72tDJ@]AP"P<(5c=L7hGDdUd]A9Z!O>%e'(`Q]A3,%AGq"3cRd
gH@XdlWVgeq,7H)($:S&/fSB-g^\:o*-ORd40^#UHu/<`.8M:ou%TD4+Rp\0PP.PqfN:H$ZY
RPg[eoE(*:jIsQYA"uTi9dLZ_hi?3$:WE<jadDfF`S>Z<\`oD`EmrU))a>!k''5OYFXQQ?FA
dc@RnN)_>kd#R8EZCjC=Uo2n/sJaTt0Y#n:po`h@JO<\#[8sDHaR7!EnLIf3IDY`1-'=Cf1i
QWW+c/RbHYVFJaea;.a#ua-[jY^>s:Hj@n9h:l//?/c#.;jB%7mKPPKUj=g3^=5?#kld>g0$
CXiK9o/<PjWg@gr(#tZU^JM>i2JA"i6Rs$i#]AU9.ji4ZFk\@@&J]Aa5:IrjTh2r$B(M78crI]A
'[lRZN^m:%iNmm-<Vqr:2lR=(XTQ9,jH\-kUA^L[!:OO8EkgfC47qlD`t5(90)FU/;65%^N^
]A.!E8?;HEr=N.6hOkq[rboN,nT4h!8m6FSf5u_#C`V?cgmD_"D+)V&Krr<~
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
<![CDATA[876300,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="0" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品名称"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="0" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="单价"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="0" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="中止"/>
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
<![CDATA[m94j9P?51gK"qX\HH-UY;G"KH>?A!0Fp'Qa8F1"_]A,45>FG^W9*@cl-:lQ#/q3%\YBW9EoX#
QbAlNh>rKCTZGUl6F*#RDbD#QYM=668\=qf0AQ]AsgU/B'JTdCY]ABuOY:?skNr0%^\upajPYB
SU]A#S+qB-=T^N\SgoKE?8+[dGL0E/s!ra\`D)\mpKo7qNK^%jtnEKrZL]A."EC22sfucVFX<+
5Qg\g39!H^@gH^k<J>(rqj1&O*j3Kn%u7(^o-P>V?<Ifr3lC99?H>c9j]AMdp<NO#!TPO!VM!
jr/M?q(J!%bZdXICfI5.TWFs^.&.o;Bij^dIqXqJ'E3M=cu+l8:cFmM.B;fC]A9PVbO8ot'4[
'TjP2E:9Z'FL"\T\ku(<RJK(B>LVMKRHW%0RDY^RCb2UghbtCs^*2%T0@aU:'F"u."-7SkHm
C`t*l^rT_@M.ih$dgWJmG>@<Y#&-O6XhXHWLe[pW6:D[<ACN-esdQ*&3;,8LAS?\(0u$?dPD
iP%s`$M:lEbAuuDET&:"L0/Y-MA2^Am,Ar:/"PHN(M)IuX;Y(Al[rNkDZC9Q)Nj[N;Wm2[U]A
S3Y/44lJB4d5+8ePjM8%S7q6IeAX/WP1U1r?sR[G^!54//0OqC^Yfq1H-VnG:FeY$'U>@IJF
U*;:%(QDPS2%DRdHF]A,+\VZ9sd]AU7VK!8YUQJ=mXp;ebSo@icr_P\#r0J45+%RhGJF044.k$
O9pS<,&r"-2aj%^[R)AoAp`Z%:c4EYlGKh\ihcMO2($`FZA@XHps"Jqb9^G^l%ec-nguSt6P
6kgobHsKM7#jY7#UUgCqcorWHek-^3X!-P;VQlThF8U<t_4q,Lohj&<1#H_h(f(82$Gi9Bp[
JDIiR^1]A7W"n369k>c`FE3&.-=B4iT!3.G,5B:XjW/'Ku0in73?7,rlI[J/fe]A3)n:;#Cu&f
Z6rZ'"?K/rXKckoU*dBkJ)4%i;PHr.tfZI<8ku"78E&(Q_uF!DTdb[6N?5T@q/)el)]A]AP)jT
U4MHe8mOpH'/6Ziut0k^foY0J):/OFXn_+lf.;jc!+$eQQgl/StdR\t@_XZD)PaZIsWZ5#9.
=#s2D1j`6S@u3)?Yu8]AGD/a/K<T!Pd!&,>0IirX4\[W_3@uqtI9]ArJLi`kHgJ*V0-<<"B9d3
WE$q-G_47RN`H(A^B4_>rUR28\[8c5]AGaL'Z*/5E]A?u0WTYa46)ji>Qc:0Hac)*7CH1%*b=+
p<.c:c[s/gr9"_Qe?05:bq+TCdCh@K<bP:<@XY6K"nV2O*P@hf(1P$R3>9gDmB)b;Cc-);.$
#(2&aQ6N(#+?g4/Pa4^:SKpficcLM[3"ImQ,96uD&HB-)PHKPnM;qfkh3^K3,*fiG16(U(rI
g`+2_JmW(^.Aj<,bkl-oq5ee:kq(OY*1S1bu?l!a]A-KDQuqN-3>a@D(Q4@2W;=g)NTgb9A4X
[&#0eNN_>"BfP)kSm,=jg_fYZ@U3$McreZ8O(S,4:Le!e?"iYn$+#mPE"4Cif(Q5=h(a=0od
%_sVfW917>%WEL.4TF<5dpS$V%;;.mC_D($,YsRPk<XAY'Qn_usp`O/kG#:%@.:GtS:&!Y**
#eH[fu`M+E\C&&1Tnfqa?@8]A)Um(PX13V(R,0n-!Q:2V]AW;hbX3bW9;@V.$*2V,2iUqX_d:<
<kmF`PGj[JZB8te1g+7E.ML36S_7OH3og&dopF1_j]A*7JOVOQj`\Ms3.YQ+H@++cgemC<n^h
aRHdQ?n3V#M3+ZRLrfo"Hf0OUT4H=WH0'ebjP*!irb!es,B?Q1U@(h<GT==-*uUi<\#-8)%%
i+s]Al(e9pD8%,HINc(%_T[XCs[D):VOM)O+gOp,FfaRC(.`iMnCU.Fb0-<c8gTo6A-.Or6T>
fQhGO94d%_CAH7F4aeS3J[0j!6@u'/NWEI[;*)'Eg$42>')0`7)eb1.%#5R<5J-KO_I&NLg=
`2lA\t_J!KVa=LPcC9M^*/^7_D@D+o!ZFEkAPHnks7ncF1Aj1!/c]A%Y"B?h(lRN'\u_,O'D9
]A/`ZSbO^$;<:2`[nIkfi2A5OM`&OQqkr3:A51RP2?s_[FBJ(ea'J8f,ii@]A7ok2jJ%X=HMJ]A
sAX^p+:(gHA^3;jn^dP9E?_APLV"86fm"qs;=pR,l2=hO\NU(3^0\CY+T+!2.5qf`B(I#FH_
\'>+'lR"MZ+M#uk(9i_%3WjXH&tA@2Z!UcYjg\cZdO2u;3+i0olV;OP'BYt]AF&-9U%ebdN%A
hf*8r'@!4cRJ+No<(Ip0QAt<7N-*+k:[WT.DjL9$3^-'<gS6Q8:(Im-Ca^d)RUFefSV4S=Jn
]ASp<Y_HepK3(\Q_^\P%b3^=o%TbR*N;jn.'*CQmYA,\u`!qpG$c(8RKR?>d)LS]A*rDcMD;CT
/jUWLH/[E.<K6B<fs?adSN=?/"2Sh=8Hu>#n0XF6,"Y10c[=6fnotYn-Y@J)+Yj-_pGT#W7I
^%SCCs$"GMuhVQk2S"B8<^&oBb13@:,X1Uc$c)"qa9D;#9u%cb(T_#Esm2Nn'5jc21&&)LcI
2D='N"!^,2,R^e!\4q-Dr.^o<j6ohDe]A3h#H\nqLXgQ\?>WPDklVA&P-HB1-?%6GJ)p4a[A\
6oJp1u]AQZmiLpGcN/44eNlGG0hPE.f#V^?gUT:s6I_2]A?E(Gj!&@UmbeEqn_Sb!H<!@JZL7B
bj`d<93coWng1n=GY,TGkmG<3aQ)Lip\a>I@ni11k2K'bP5Cg@n+tJMlWeFj;:C6*1gaK58Z
`FAiOPi(Di,)u(U?QW&_E@uiNM>f*&)MR?%=,W[L>lD22R#<'eZaaVfRe"=L4^#5Z5pEV!u;
m+.kT]A'[\o;g1j<pD1AE>VG,m(]A=9H32'cK&2,1$dg*%R"G&uF=;b:Y3MI%d(:Ld!X.`qYpm
/!N@ZI'jc3;KBgS;$T\f8JM!shM+**![[5%`"-_3h7.5H[^AT.Jch&J)II@I,e=tmC?8#sBs
J/d]Ai4Ym8HDGtEg?6#O&H)0&h7G\8.I'g4n#@Q#BR/,a"RW#o4$4OkJI#YoIODYm=!b=Q:9&
Lq$a"I#b<L/:oB2'_!,_Pi=LrFEsm_@eW.QUB$Zo<m=X_h)LGhQ,O!d4oNSK58kQAY1iF2$,
UG?"9DVQ8=RK>N[)@GMo'uPl=VB;=hi>X8j]A:H6V)U:fIGPFGr!*2/_69(_^[uceX!mBqB>S
9G/iC*2=l87To$X_b43,L35LnEW7RP3HB1bT)Sc$icHIKlLoLB\s<*+<Q_F8/U?gSs%ei=83
7<\X7a$9IchZThNSBi5\,gG\m'MLC[8A=?*%rIp]A>[!Z]A$-993`G"+aWV5!,g&d5;5J(6JR-
n$]A\NpheC/kFdh=gE<4Hgc[ikpJ.X5a-"FSSUs[@HDkHstDOQ\U:kDgUVFO7!'PhCPVeI/2j
D=Xi.hqK4JjXE&a/)MC=_s#jFQDph`FCCi0HmK4\$GPRSalVee@l*[gSB_$6$>qSp0c0gVQn
8uK($e>?R''7f-U/>/K=+07W/+/YaV!Bfp5/lc-p-o&5QkV4LJbNg^,3NZ,))0S,qS;#WN=@
FuT7K&D&.aBMHE;J3k8+Qgdi9Sn9\?[u+D`DZ+8[iJ*GeLrkt,5Ri/1FLp]A3aPZ`[Y?>i/U(
"#g'qP*tX:"8FJ:7X8/^(ldQ+KP`uT^Skdr!6K,ibUq;*,JDPIio#aQph,cUDPC-h_"[1BJf
PmD-EMT8^\,@"hh0_q,2KmN9@DH`r]A8D)Ed<J&'(,O\8\id[jBP=IT]A#n@f/6B&Xs1cU6+-k
%XENUs^$U2ehhr#`60eoZ$^7/V[KDQkZSYnjMVM0^kt!:L3\"WJ*`t1&C.juV[=b!]ADECbr]A
5^RlJ.;Y0n9aqD&/=,!JY<'5hd82g<J6Hc#7(A8<Vb=+)h->M.h<)#O'.L$O=r^q"D?GSr.8
$tRUa.4h@gqF)7'NLmurn,#fSPaQj]A&/$o\769seG0(HXJNT8>s?2o4.U)[Q.MjQHh3!u^WZ
#1B]A9msI#c"O'SAI'qq<-"C>FrG?^!Fi#.0J>5uP!N8=OKH.W"H!ibo:-Gl:a!-%!gF8Uq31
oqq]AIun_G$hIa;k5ccHhGsRf2#L\[UK.2J[N<6+d=a,hggsnB*3^a3UGH--Zm*B#OgBKnol`
`rZ??<c*/63.CPCNZS10)Pe#YnW#(6A*i5?d"QcaaD1ap#J7jK.qXNWFPRsE,o+#?,CF:[UW
Qq#P$0LC2A[j$_F/Con3@I+P&uIG<j`TTu%MT30mU)O9GeQ6;-&tHe%-%3=Xee+u6QhUXUP.
C8ZQMT.gp'EFYTj!/lG>>+q;>cDI=aRa+f"Z=j@'dal*[d!S[cibiekP,mSaR4o3qVm\^s@T
noMFHp8_%_$dcC4[Mmr.G1->r08#MuAIlOQ2os6]AGPH^#(A0<Q]ANB,=R\@Tih3QE.f``Zu[Y
8+,--)r[QSc5<,d:c7DM%>I,l>.G$Miq`?%WM38ROdlf'*P?[.5j$4OFcuooFAL\AU_9,BO]A
k`egMVE_hSP.ftV2c8=!uSeq_%KWfPW4()_+&@uadXf*L5W!!\0f)\7=f7DCXarYc7K.fZ6:
c'_2e$Og`5L5CUk:H37*6K`U6/8ChTQ#+REi",^gP4g"TYc%b\#5_=Sj$8drsOW/Mbg_p_#b
%(%]A;!RCg<(#)=qhq,q!bfi*+dQ4$,6*/-#h3//AF\rL03j!QD!+Z:,q]A^n>nl1J<qebnpiV
cPTF[W_J<=,ROV/$qs[</Z/<eP:H<Ec3=`#Be>9%^mOXG2+G+n#ao>#j<F'mOE4.,a/XoiVR
K3!4Hn%pI#MW.bfU6?Eb'h#34QlU2gsId#>F4'9doEMZLl6p0(0j=qsi,D.G`dPkX)m4p@ri
@J>)D0^U8A/#@JMsMdLY&+#laq2PP96^jA[;C'Tnt!)WOX?2K-bo+T4Eg+PI2Hn]AM1nXs:j9
V_9T^,A3%Sg2\Wq.dh/r/S&YaYglNhVjeKrJEX"P3oD?KEs"]AJ;,b?=h<J7(ihAuBYcI<#*0
p)[-PkGTJ8bB$O%ONXo/PAbqV^'I_\%R^&Y1b4>>pm'dea<_XJD*![oC,S7ZnQiM7N@nkr:K
*[/Id-M_E<2h8+prL"?g$O1"YptD#X;@Tj""UMd3'e6_7ES]A7mL'hKLP@Unp/;>+p([!l=\T
9,$^.5Xa%jES.VNW?W_]A'N$jt#<O(;!.Pmc4Wb2mmQ9W[]AqUd%D!8'fBDY9K/Wd14Q"YFZ1m
*_7F_:*QJ/?c8n)ZWHjU%Gq`ZC*LN0-/n?2AI;I5tKO'm?@$qjb$criEK4a2$a1es"Z&sYJG
7&b?-9uu$ntL<ais^Pk0;C,%8p<X7:/l%OZa3LC2_*F2"WhMn.2b?7c(98bEW9W4UKth>Adm
3MYfr7$97UiP6d`p<1,)qJd&;"P"#N=!(Gp:2<Tt,m2<\tu1p8b@D./=:"]AU+R&$@8*='G[g
T`^'LIC"nP_&LVOS%E??@K7YXXSos2NNu?O6D?_,OBC(40ue8;VASg_-Z@52VVct73sd.B+W
CsiOX@.aN4g"r'b*KU,UK%]Ag$dXB8E&PXnpD/Q<0=n"R9l9dFM49aQ5-H4gPQ%:Z_(L!&Saa
t\5WAll\e<aQ4"'WQ@.U*Cn<PnHBI#6@&O:F.(?#Y%7^nmYHYrA9L:ipUDg@cB?gVh2:!+)`
h<5K`lOip2/EWppXL_\-^l_Ue4mD"?ge&29N!b2_?)2lEk28t#H%Fh879Q\SgH3%EY9!h!`g
6[2lFk=+KT,\kC6@pU(h7*"cN/]A#$ccdgpck4^9r&*#FsZa'Q'>/8S%ml]A"R805sR<[m&*)9
mK+DH?RojKWOrlc,rTNAE,$ISA6(6S!#HI)hu:t%[)0i(T7#l5Ai/!>!WTk1f&f[Crfjkc^B
"~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="5" y="106" width="264" height="246"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report1"/>
<Widget widgetName="button0"/>
<Widget widgetName="report0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="650" height="440"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="650" height="440"/>
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
<TemplateIdAttMark TemplateId="842928db-013a-4a22-b87b-cb856470efb0"/>
</TemplateIdAttMark>
</Form>
