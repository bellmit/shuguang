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
<![CDATA[SELECT * FROM 销量]]></Query>
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
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
	var a = 'report0'; //获取对应报表块名称
	var b = a.toUpperCase(); //防止大小写出现误差，此处自动将名称转成大写
	var wid = ($("div[widgetname='" + b + "']A").width() - 17) + 'px'; //获取报表块宽度	
	$("div[widgetname='" + b + "']A").css('width', wid); //重置报表块宽度	
	var height = ($("div[widgetname='" + b + "']A").height() - 16) + 'px'; //获取报表块高度	
	$("div[widgetname='" + b + "']A").css('height', height); //重置报表块高度
}, 100);]]></Content>
</JavaScript>
</Listener>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[地区]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[销售员]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[产品类型]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[产品]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[销售]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品类型"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
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
<![CDATA[m9=pB;cgC;0LuRODQBNI"LNA(@hkq2%I^)jlW?ELdalQ>9.a6g&Hb@E,]ATo+/VVh\!JM(%.n
CBd5lq0%/P9iS8H8o?J]AREc!XTE/aQo@Cib7WlEh$RlhlXTgLDom3g2!hhXgPmVp?("7[r5P
cBk)udTb_PCb?I&)W'=*T.+A3AD?9[!kLL\XLdHeTDL:]AopQ>nR]A-GU\V3C=BDgAm(fp@26l
+-"5hlh4U9RM'WX/T1[0<@:%n58`;20i50IcPT`IJnYdgu5`JI-eW7L@;iWpAR&OaG\q.H[E
Z"h\01#8pp&qXjhdqMmsepfe9pS4RnCgnI9@MVSPPuc0n6SGB?82F_]AA";KIU:pR')M<L:@T
O71F0LkAW+I=p_Uo`/<*mW-I8b4DRei#urR'."(O&o\JGTK^(q*Q?SE\gqBKnE7G*@ZnT5pH
td[lX#M&(G_2?ZhS.kl\;T67#?u=ZcD-:PF@hO[dBY0.&T!F$slcCj/UH)DM\VP0]AaMD&h<Y
n05Hs;6s7*kQ5I=hTn@U`'PZkS9:Dtkb(lp$W:IEJQ7:ZgCuuOp[.7k%H2S"eTjc))au[ZMI
j[X]A6s17AGrpnle&EN,7/UrZB&KUrb#7S(m+U%eohF!!Jlg5so@@LkUpS>FlsEHU2,(8YBiT
9'@$1;c\UY#ZW2/$4TZ/n=BSJ37\#DWFl>L2!M%MNM_nt\7J`61JKL/Q,!FT8[WT`1!F,E0c
H&15)NA:^?,NTr]A[[h0&)kJ'CbK+SGAHeTJU<&uBJ_F%V?,Y4<di>a,^tn<)iT-a.@H%1tZS
26p7tYUZV;>i"q?daTH")Q?MAIN^?h$0h;#Q'&33P^"nVt)PH]A%mtn$c=d[b8)lqr6\7gXK5
M91gYij!u@d%VH,#?I!+6jD5r1fT%o*k-[-a+%p*>f>n_S#0*GFM4ZSfQB[nS,CUnKQ6`\\%
u!IVMaIo%;5p7i/_=*`Op%CQ9r;Y59fD2WCufo2;\r4le&LU2P<9E+mLeZ8o3\]A()-oUNXDN
hL(eR$+iVON/_ls0Ir8bl/NTH&lJil@rEG:Y^("r-.2;[>S^gXEel#!"A,Z*c)V5S*:cq;>a
k^Z8JD=\j#X^2iu8h?BJg'cpdJIX]AYqN%MjZF(NeTrIU;fb\5H%pn?)?c[Ri8O?iUihq=8]AJ
Pc-7I9TK5I8,XmZN/N:(C=+U#A4`9MGK@Ohh5h+.,GTfe@F=.*i"EnKC_Kg$gZB1?jOQrU(+
$14O!ffCXfs`.sQ,1-g<I<80RbkiTdN>3jdIotHR?5n"OtUWFCTWOQ6#X6BU#ef^g6m`<,XX
UVLp+[N>PBl&&h&%qpf@'C`clQ;k<W*0V-SQ+TnYV:>Z:S7pf'AZnHo@P^#G6''#kWJfkdZP
,u*WRj/NE$S>cbX@mr>>b5Slgk]A^rZDo,bZXfk:_lRWOZGn+TmgE9mKnH)8`JB&G^rm[,te)
_H-TAgb?D4L'd@$k/Y^MmhfU!Y*qri'R)@HQ:HD/*m\\&n2so]ALB$!@RW&f<F8>X'X9/5_U<
=+CFnJ&Fl=&(-.1]Aq]A@I+inP_`E&Dgq?;ToRG^qQccC]AmeAB-'(5(5I=D\R(/q2O#/\LB_Q&
PDcO`lEJoOmURCB*S(0p<-8YkGl'AIp0Ir(SmMO5aW1n:!Y8:'"!]AX?,MWoc#OjgBu5J*JPS
Hi(XfJpp'Nm;*n11pR\k(6.i``cqLMQjBfRipb+lZ?u@\oj^Da'2.%))*"j!BnhMqhPkr=QT
^*#pCC,qRJTDQ;perkJbkGi3[;)8&S..`k7QKFI2!+<&]A(bL&ClJ;:@[5Zi3$ZZ$c$3U\3Ks
l'1h0g@@n8L+/BUjK)G+)fE]Agh&eo:3EpZW<KRr]AcDa>D84p5^rm88(fkor2c\&.GF9P*Yq`
7NV@LjGjqN:#_O29?@Rq@:JilX@+Yl)0]A27HUbf,fc_E*A`,b)!HQC]Ag+?3:9ju9SenQ@]A"^
D&<uo'71$C'AZPXG(:B]As)oG/OZ+KA9DJQl"PGCm(W0T^?^,/rMo5<tp`eCYeE#*LqH[D"RO
hjq#o/iJ+0bW<>]ANM;VMHBCi8e^S+b*]A3B_ps+;&g[D1^NL\Nc.NnbCG3#o.CUA]AFn^VO5[W
!`JD0']A!"<NTIgRR2g_kI-n14bnLRnFugeL'RKds8nF_Sc;cHMg$b!ruSLZ^?3@p`GF%q2fQ
=VXBf"QMAf']A'<$p[:s,blt0$>@D1"b&sb0YG+_\_^pnH9q"[bNEK:NV9\GRr*1Y(eMlom]AR
"u8@k8b1h5[Dh^%H#s[*"u8k(4[)L8E+0nRi@80_XpOQQH`_*;`;4(C1dD9nSdAe=+^;'6h^
$#Wb1ucBdVCp/>DEVNS7SdR*VOJQ)bd'sI1Co0CjI$Lo!#h,.L]A(T^cE6F^60GA5;H6[eu3l
*OE&_KL-;Y77ODEmoA4LK8(P`%+M-<SU"^h1lJ7k(h[F8Su91h0%=Kk7X:ups?ad*N,(l*Db
#8P?,-fW5)A1N/>X:J;J#,SEN5I+.8E6Nf"4N05*nTbH]A-%?\T"`D_1&epA&@t60Si8:9]AKG
3BXZiHtZYr&9'CTmCVW(:C.')PlD2NX]Ad='+5t,#Ks5r#.'U;bL@%_&D<EC5a6g^t((D8H!U
"c7__k33$1<pH0RO$I[8>RHVW5O^<K_rkZ\g:uLRl*:diOdZZ[BRLn20kdhn)u3\;^5A5l,9
[q:q73Kn#UFYkU'%.J;FIK$3I.p7BLb]A<8=QC1[Im@t[JPglFk2ltfV0SBZcj@ANGAXOMh48
'sF[dl9qI+S/d/VYbAOPtBAAf']A*]An)#,<:rmIuf?Ea7DL?Zj^tg!r-N$80Y>)HBY/^Gd]AMm
p\\fD5+]AK*hZUrbl2Ie(h2-Q&5kj48L)*$k@AngVqVR9QWn$A-.N3oDj/CEH<[mQim`RIL/5
5"\c?TII=420>'F=sD(Pi.Ff$e.#n]Aa(EeAZWD]ANX7f.-#Ybg-W4Vk4,A1lK^#`A'okV&GZo
i4oGPc?IPBDj9*4TeS#sJW&N`]AQ)`rN#Gc;c^!(TKY'<2:5,RBAa)l-+a&O&R.Y79VVaVEhj
Y,s7H`E**[P%eWJ*D)MqiRp!S+[));d-=.]A/#cX^Gn_=Tq>q;Xn6!Ui\+!&?tm8T6=L!TgeC
#(aN]ASQZc6Y(M#32hDq6kb&T$F%r3pU>h;L;.[nUiqg)A<lg`G47NfZX&]A$ZD"&4p"`&FXQ_
HToeF0u*%Vi&<]A_]A8:lBsm7c0sW\u7;*T<+7GV[q1_<$ch*5sM16,`g)pTFSN07pcU(<ViNH
@8@>6MTOf1ap>5EQKl0@F7=mX8H2Om./j4Z`ZGS3-"cn\gi76rof#X@^*TS?8=>1rM!LqcIE
Ptaf6S1B-2*@7&m9A(#r0Lu6JN_5Ic=+PO.9(?X;"$eoQq-OWQrmQ5^5$j3mS#P@o+P"-:?l
;egFTj9`'\"@XSE,U?d,>_&WTA:/C8e,8@(,qHfg"ooLBpim2#48(='1@l4)jO$t8+329!/L
S4f+2lgP![ZP6dT]A/]A!7\CNCqt:?M!5W]ArXY)N58N#i<GsiH?^G).q<e63QT2%Fl>0d4>R1A
nQ9(GLc-M\-W>MBSk"`@^8=(HRlgfucoaYt^3IXns71Qt"d^-1g\S:3m<eWq;lK+V[UIe=gt
Z.4giA.jBAg5t&?<:n\5)VA!VT0-I:Q7]Al0qPV3gc*=mhHe#^(N*H_+5g4ja3RfD#`Q(Zn5V
'.!hF6jCUc3o\lp4Dn>HV@0IAtYT5iY^6]A"/il!m7o@WFHe7ZFC*r'2K/)0q]A*#>`@q&W`Q7
>!+3YcheDPS*ROMaA]AkEn4q(Tf'(rOhZlH.9F<HhGi,13J8/B`IDp`m3a2[nG\=4_SpOiZ?k
>UWfT1*%9[C]A8E]AR2s'[RG39#+Ta?a-nR.bN+c]AY_-C")iVX5M=pmCh?-bF!)oq-9jZFud\/
(]A8J6,>$gI,bFe-Y\Op\=%:WR]A/+`2nD9HQ!1(i%epQa6+[:c^&>5ES+&Q\$H?n`4b^*>Sl?
cD'_!1/"'oSru(chbnn-lk.k@[W1W-<hQebHSUTsY"9]A%;d?3"Dt:R9Z\"Q:=pUI(%+U%e6s
gZ>.I_)_;m%-c)W[hH'c*9H*^mU>ai&Z(]Ad66Z\QE(]Ae72I?nkl\*+;(<8'@,@/TW/Up+`!Q
%qDOE8a"N+#^5qO.2p!:D;>0:p;P/lH,EXJj=:KX7)1c:`?-3N89>."0M$nsn<NdO$o#0g&5
']A!!mq_316,+ol4j0g;Q=;l"AGZp;q>R/@oR'2?/dTTGhl^LPS+'b%l$-7&(>95+WjK4g,d<
moK<C-?G1o2eXo'6i9@dU+&DoPBceF5kX^X&V:S'"pkT\3MacaP@Kq=m-^4'N:5=m@14fRKM
JChci$H?3P@4@K\VA-;;euh8"1S23O&q*l`n>/Kt>D0oLqK=@G90Csq.5+:;F"pWAap2guI3
9ZqTerZT2[87uGq+6S49AoC2cd9*B=(B:.kNT:d7:1'J[uE5oEm0a9RBT/eS?2t3$1jW8_1'
3/p0]AkQ*n"i*e8f)-qt0@M!-dW518=TB^ek3)TJM7[ILJ>8j21&BVfg7"O4k6@!8O(<bX;m]A
?;tp0-Wr"X]AG]A%rD+]A;H]A\^j<D5MHT50MF2o3JWj=Ap$(1=k*)-PdkYCsh+,%uKiC$*bjNmP
lmd`7X4$#?JG+4rF!#-MNiUeqlm&p2&\G=.:D]AH,KTbJ?@lH/&Y"7#TO7qOnjg:kcV>?-Cf[
4$m)sDQ''S(I?9$)2s`\]AT]A.K!7+$GM]A(gO=pKk=mt0%n5p?H#28J&bC.OjaaHBRADc614I/
c]AF<&`S9_*`m'Z[0JGHS3pF>pqHl]AOu!f<lJiGf&"_u-894&<E39`@<[@kQ0]A8Xb>NL(j8qo
9jNpo3DF0E8.E-*?X<T!(!jAJ8j_A^iB7\Y>M)Ajj'o=R[Oo(rU:Y=C5SR\#n4#$j&9d96tA
<loi"Ph%]A#0t"rZ4B9gl+/OW@^Jh>m[2j>`f;STV*<$Q/RC(T-UNr+229mf9A0IgVX$^f9$D
O?f(r!-S4Ut;J4hD^S,jg[lf-+3"m\0OJ+UNMOg_h4C.1Wk$^9"nl=Y1rWD"l?Qn,"d^1ouR
ph43`Jdbs6E@R!N3MXQ/GgOO^d]Adk/<.?i36XueX!a,aUEa6rDVs\.*7O[M;Pg*#a!2O0,?=
qTRq#7Qkea]AS+n&`/@blBOd=PQ-L54e6'eAZ[kK\CH)Km^k>k`<C:a\Dra>8?hs0'hc[J@1G
AGf2j?cJ,*OY33:+j)3#g[$SDtRd==Lm)aDj+Qi?"_//4$3D(:\s&rV7qG"&H^.Zq\0^;RR(
)`Jdqa3;%"1eWj\lQ3bmm>1NCg6hIs4'[*/c>KraY/'[%7*#I"a`;o2h/G<FrDaSB0!(?a*I
J*c#!_bM8@/QFg!ujS\to(o#_ti*8DB=T?8bqa3GC"fMeg,?uk5O1NV!a,aWIN(S.e$phg(*
pHCTA?XLM$5l2aDXp.@hF[X3Wa!okT-#pAu:\>jS2/7eS4p@)Jef,[JfK[V:Y^fsN%p6UODo
%Ss,s?e?2l2aEW%\IOMl#5f]Ab,@_,1l$>n:C(o3JGdC$2f0ifqIo]AN65/?gg;u*90Zl/0ZEV
Nq@\_8S*(]A>"M(FD:`eJ\Fr:C<rp[ol>#U0jEc%SC.N@M7kg5"J)Ttn#*BmFX#>uV7['msdE
&j;^fZn@*>6Nh>,]AY66UK!aKN#CbW'rk]ASd=<Ai9I4*57\lE_LGoMoNbb9G/O_L[KQNttTkK
EPfu9a$XdNg$rRM/fZKp;:=op?bjWe0N-h[?2"]A(<ks+0hhjoQMI26QY[IC(:u8l,3t&cGA-
RDoIP^!0&SDDqqgaHmr[2e!?iHlIVC`s-[8H!W3>OPPb.^_s=3;\ATDX]A*lF3<dX()o$s?7t
%7^lq]A'UkWo.U^G.Tq/aM@_,I:TtfY%H&dstZ>!bNcB1'`_n5'5S2Ci:b!JXkVg-sl&2.Ds0
WZ<A,Z_Sj]Akb94*j[+fg=KakK>cY*?QK@6VkLgg4Gn]A94:aZEl2@I]A=!n_Fh8/,9&SjaVtQ^
C_*/`1h[7?W_#B,X#WOg=1)+?+$ha4X)%'NIK87C<slhWjm.T_uTYsNqk=d(oZ+IcH1Efk\'
(qIg`RVK!$5HMAhHESe>ZY.\U*fEP=lB&i#6$2?A=&^PJ[mC%,QS")#4C7m,sSZWs+P)s0A@
G+fmdG%VB#5i`CM[I1&"+3qGbo5=lLk>)WM^>4U\0m*8G:sZ%1#;a=W[eWWrM8hM^3`0R:&D
<S'J]Ag9+]AoW3Vm>XrlfYP^d_4aLgpQ#pVF2G@.,KD;:9G8YVmPB7?-);jkEq]AKT#)V_lPLSg
Trm!#+bDbWIjMM?)BDM@I12-%`kN@fHah*0T6kW3TO@cAl+@`gimLEEm6DBsE^U;NJ&AU1G"
YTmV>_b8"p_$H6c\qi"r#+%'D34PNH*f[&lLFO3B'ai+S(Z8$_u'bipJ%D#WVY5n\_XnuM!9
dL]A'iWTi'%<X&IYm3qL[biK#BRCWZTD!ifMK\0YcMGL-^Yq6,%OtD7Y@%4JLI9"NX'+oh-qn
rTMJ\3;*1/aJ?n@p5Eqi^@HP^jT363Yn`)J:At,XT!)Lp0:@MaVSc7Z4,"<8m^a7HILX<'F4
>\[X#0X0!9(KpYAr95m?HJ;5`jW5<5B!M)h5b(Q.V_jDFUYo[D8ZE%`CKnLiu-i'ln?-i0hZ
WBh,bn(G3K8E,JZ$jK>7WA/NYK.4,?Nr_dR1YOV=,%B9FBLeNjH'Vl+3(s^X.7E8[G'hPam]A
-<Q/>#b!(:X<EE;mQ6u19^]AQNuo_8c8QjHIHJs4TM8+9]A]AlE_k*+:hE&7uofMD@S"MVO.H^k
]A:k/,6/qhctbPT,(Y0rp`+NnZ7s,)YZuBSe-D'KpY]A%Z"<G.]ADg>CRlEPI/ZWc0.(Ytccgfq
8>fgEb887fIsZ(!l@[2@G*HWP>kt4S(XbFPD',VJmMI\eZP8m5qD/DXFmEAAnOQi7[V>KSe>
#p9Y7dRV,>Af:,H,D!m;lRA4D(ho?D'kIWaQL&P\NBsdK\J%k0H;p;"*u4O<AU#GhiolUVbI
4o]Aks<F59STk.M!V^9#fEM/+Cj&.kSuW+j$%:<ZRd+8>dT(%#uENBg>2e*fWJJMC)[::@,hU
O05\cW`,L0rHdI((]A64'q+0X:Ibr7dC\_lrb2.@MY9&^iV3QulGfMSj/Rj1]AQm*/eWHa`)A9
8D>gmbH^i/ePri3L/V]A(%,k=cqV97"IKp9<QE=+G+*`Y0shWBOqAgg-;C-ZT$727krZ+B=3=
,o7.GU?MG"Y?M`5Mrr]A-Kdf6aE5Bmj`7cBhR6o[,lt+?.`;0^@f>(L$H6UfJh15&0mb^"$&e
BZ82@n=q=W9-rZX@0#.W-%NqNYqC)i#:FDrpfl$q0oNELD\#T!%,?ZP2XXH=C`>^hOjK1[m9
61D(/(Kdk_06j.e)a8Ui7FuW!))UHfj=,A6pInB@')e:RBM&>9bUC[<jTd+Ic[Ocp.Cmb6q`
A0!N>Iq/TIe;Qm-`#r05k/T<0h.*k$j2>:"n;aIWP^Q]AgO9=2R>I&DUtDS/!TnsL:Mg"!8X=
Rmgk^XJ<CdT(NMmFqf]AUf_<';j]Ap@8cg0k13sXSL]AR[4a9F)m0gKLD@9'WMelQ)B+Ai;In'@
`0g%n6[@1?=3Y`JIB;[tM03LAM9(cr<LQV;*F(Z&!;`'Oor&(RFjnu_j9H4SJ'b\iqq[%baW
S7Z?"4*[GeSF]A7u5[U9Ls`cWfTe!`NGUq#)K#f`Eo<q6r'.UIE@e!Vq[2t*ekWa.:jMDf9gh
P:tk\tk%T9tRH,tfHlBPAR7]A`JWESA-a*VD!bFcIpH2=2)o7Y;.FfU(`dn?7R2s4JbJH/&`r
JD81"f[&MGEp7="H]A7_+*KA5^\NP,$B=>1PXT"g*[:.WSLH=C:bLlD`3>7T<>7R]AWVg)(5@I
RANDoMif'AEcX[Di2YF6p"a<V#\SZJOZ/P/6W?.1T@K=,kH,2T$qLM[EHrTp:U@2?l?ArU^X
Z6l9HMU!10+emWt"Hg5<87MS;>b:M\"F0Y2r-'n%ms-`WR-mF\dN^O]A.W3#F--9Hq#LW0+O2
UkQAs01-`t8JWT\TZ`c[Tboi/Q+^>]AtoK\YN5QrC$@">O%8s&R[A:qihU%e_lD5]A#hm"36t?
us6cNug*ar.-jX\hoB2`rY4(ab(]ANfn<NF2nfAh,2Y&VUmDf!]A4B3r2kXaXXO4]AK:*$M*$"n
[Ld1[JFBd`PAOeJ:IJ>3,'&Km\QO>s3lW2g-TVqcbAm^6:kXNR:+Pb&e.'M_NH0'<1%_ZT/k
^`*Q8;m#bH)<p]Aj]A';J3^c&'ubb3m:Ba@b8nH\UCrC;epUK&Q]A<E<fe55g7=\hm3-_p\1#U:
5p2S),&s&MO^j(<"pJ(EOs!OZ';QQ>*0=SUBTd-Ym'-%l7U=>@3]A;OXM+&fT<2EAi,E#!9r+
XG0]A:mD2D(1C%)nVcaf;S]A!-BdE\>;Ceq%S\A@4Vl'E4EmIV_-bnAi;$_kZ2Xq'&=9c4.,k_
3&lh,p*Cp0>1%0lq4kXY;8fglJ<c/m$0%SZIYA$ET''e"[2Zb"X0dQA<-KB&]A2[;B=@,X+lK
K1Z?8BO74M>)LWlKN32kUo8gA&rLbCW&bKn.ah-9s)odphZZ,p.*%7A7D"fD[`/NcB=P0'^&
^Rd%(MXWhg_k4tmJ<mcgDTM;ebrM6i+HD,_QUBd1)HUM*)R+4*&[VMq4N3;5TDrj-Ibp9uM0
n,D)A!W~
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[地区]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[销售员]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[产品类型]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[产品]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[销售]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品类型"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销量"/>
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
<![CDATA[m9=pB;cgC;0LuRODQBNI"LNA(@hkq2%I^)jlW?ELdalQ>9.a6g&Hb@E,]ATo+/VVh\!JM(%.n
CBd5lq0%/P9iS8H8o?J]AREc!XTE/aQo@Cib7WlEh$RlhlXTgLDom3g2!hhXgPmVp?("7[r5P
cBk)udTb_PCb?I&)W'=*T.+A3AD?9[!kLL\XLdHeTDL:]AopQ>nR]A-GU\V3C=BDgAm(fp@26l
+-"5hlh4U9RM'WX/T1[0<@:%n58`;20i50IcPT`IJnYdgu5`JI-eW7L@;iWpAR&OaG\q.H[E
Z"h\01#8pp&qXjhdqMmsepfe9pS4RnCgnI9@MVSPPuc0n6SGB?82F_]AA";KIU:pR')M<L:@T
O71F0LkAW+I=p_Uo`/<*mW-I8b4DRei#urR'."(O&o\JGTK^(q*Q?SE\gqBKnE7G*@ZnT5pH
td[lX#M&(G_2?ZhS.kl\;T67#?u=ZcD-:PF@hO[dBY0.&T!F$slcCj/UH)DM\VP0]AaMD&h<Y
n05Hs;6s7*kQ5I=hTn@U`'PZkS9:Dtkb(lp$W:IEJQ7:ZgCuuOp[.7k%H2S"eTjc))au[ZMI
j[X]A6s17AGrpnle&EN,7/UrZB&KUrb#7S(m+U%eohF!!Jlg5so@@LkUpS>FlsEHU2,(8YBiT
9'@$1;c\UY#ZW2/$4TZ/n=BSJ37\#DWFl>L2!M%MNM_nt\7J`61JKL/Q,!FT8[WT`1!F,E0c
H&15)NA:^?,NTr]A[[h0&)kJ'CbK+SGAHeTJU<&uBJ_F%V?,Y4<di>a,^tn<)iT-a.@H%1tZS
26p7tYUZV;>i"q?daTH")Q?MAIN^?h$0h;#Q'&33P^"nVt)PH]A%mtn$c=d[b8)lqr6\7gXK5
M91gYij!u@d%VH,#?I!+6jD5r1fT%o*k-[-a+%p*>f>n_S#0*GFM4ZSfQB[nS,CUnKQ6`\\%
u!IVMaIo%;5p7i/_=*`Op%CQ9r;Y59fD2WCufo2;\r4le&LU2P<9E+mLeZ8o3\]A()-oUNXDN
hL(eR$+iVON/_ls0Ir8bl/NTH&lJil@rEG:Y^("r-.2;[>S^gXEel#!"A,Z*c)V5S*:cq;>a
k^Z8JD=\j#X^2iu8h?BJg'cpdJIX]AYqN%MjZF(NeTrIU;fb\5H%pn?)?c[Ri8O?iUihq=8]AJ
Pc-7I9TK5I8,XmZN/N:(C=+U#A4`9MGK@Ohh5h+.,GTfe@F=.*i"EnKC_Kg$gZB1?jOQrU(+
$14O!ffCXfs`.sQ,1-g<I<80RbkiTdN>3jdIotHR?5n"OtUWFCTWOQ6#X6BU#ef^g6m`<,XX
UVLp+[N>PBl&&h&%qpf@'C`clQ;k<W*0V-SQ+TnYV:>Z:S7pf'AZnHo@P^#G6''#kWJfkdZP
,u*WRj/NE$S>cbX@mr>>b5Slgk]A^rZDo,bZXfk:_lRWOZGn+TmgE9mKnH)8`JB&G^rm[,te)
_H-TAgb?D4L'd@$k/Y^MmhfU!Y*qri'R)@HQ:HD/*m\\&n2so]ALB$!@RW&f<F8>X'X9/5_U<
=+CFnJ&Fl=&(-.1]Aq]A@I+inP_`E&Dgq?;ToRG^qQccC]AmeAB-'(5(5I=D\R(/q2O#/\LB_Q&
PDcO`lEJoOmURCB*S(0p<-8YkGl'AIp0Ir(SmMO5aW1n:!Y8:'"!]AX?,MWoc#OjgBu5J*JPS
Hi(XfJpp'Nm;*n11pR\k(6.i``cqLMQjBfRipb+lZ?u@\oj^Da'2.%))*"j!BnhMqhPkr=QT
^*#pCC,qRJTDQ;perkJbkGi3[;)8&S..`k7QKFI2!+<&]A(bL&ClJ;:@[5Zi3$ZZ$c$3U\3Ks
l'1h0g@@n8L+/BUjK)G+)fE]Agh&eo:3EpZW<KRr]AcDa>D84p5^rm88(fkor2c\&.GF9P*Yq`
7NV@LjGjqN:#_O29?@Rq@:JilX@+Yl)0]A27HUbf,fc_E*A`,b)!HQC]Ag+?3:9ju9SenQ@]A"^
D&<uo'71$C'AZPXG(:B]As)oG/OZ+KA9DJQl"PGCm(W0T^?^,/rMo5<tp`eCYeE#*LqH[D"RO
hjq#o/iJ+0bW<>]ANM;VMHBCi8e^S+b*]A3B_ps+;&g[D1^NL\Nc.NnbCG3#o.CUA]AFn^VO5[W
!`JD0']A!"<NTIgRR2g_kI-n14bnLRnFugeL'RKds8nF_Sc;cHMg$b!ruSLZ^?3@p`GF%q2fQ
=VXBf"QMAf']A'<$p[:s,blt0$>@D1"b&sb0YG+_\_^pnH9q"[bNEK:NV9\GRr*1Y(eMlom]AR
"u8@k8b1h5[Dh^%H#s[*"u8k(4[)L8E+0nRi@80_XpOQQH`_*;`;4(C1dD9nSdAe=+^;'6h^
$#Wb1ucBdVCp/>DEVNS7SdR*VOJQ)bd'sI1Co0CjI$Lo!#h,.L]A(T^cE6F^60GA5;H6[eu3l
*OE&_KL-;Y77ODEmoA4LK8(P`%+M-<SU"^h1lJ7k(h[F8Su91h0%=Kk7X:ups?ad*N,(l*Db
#8P?,-fW5)A1N/>X:J;J#,SEN5I+.8E6Nf"4N05*nTbH]A-%?\T"`D_1&epA&@t60Si8:9]AKG
3BXZiHtZYr&9'CTmCVW(:C.')PlD2NX]Ad='+5t,#Ks5r#.'U;bL@%_&D<EC5a6g^t((D8H!U
"c7__k33$1<pH0RO$I[8>RHVW5O^<K_rkZ\g:uLRl*:diOdZZ[BRLn20kdhn)u3\;^5A5l,9
[q:q73Kn#UFYkU'%.J;FIK$3I.p7BLb]A<8=QC1[Im@t[JPglFk2ltfV0SBZcj@ANGAXOMh48
'sF[dl9qI+S/d/VYbAOPtBAAf']A*]An)#,<:rmIuf?Ea7DL?Zj^tg!r-N$80Y>)HBY/^Gd]AMm
p\\fD5+]AK*hZUrbl2Ie(h2-Q&5kj48L)*$k@AngVqVR9QWn$A-.N3oDj/CEH<[mQim`RIL/5
5"\c?TII=420>'F=sD(Pi.Ff$e.#n]Aa(EeAZWD]ANX7f.-#Ybg-W4Vk4,A1lK^#`A'okV&GZo
i4oGPc?IPBDj9*4TeS#sJW&N`]AQ)`rN#Gc;c^!(TKY'<2:5,RBAa)l-+a&O&R.Y79VVaVEhj
Y,s7H`E**[P%eWJ*D)MqiRp!S+[));d-=.]A/#cX^Gn_=Tq>q;Xn6!Ui\+!&?tm8T6=L!TgeC
#(aN]ASQZc6Y(M#32hDq6kb&T$F%r3pU>h;L;.[nUiqg)A<lg`G47NfZX&]A$ZD"&4p"`&FXQ_
HToeF0u*%Vi&<]A_]A8:lBsm7c0sW\u7;*T<+7GV[q1_<$ch*5sM16,`g)pTFSN07pcU(<ViNH
@8@>6MTOf1ap>5EQKl0@F7=mX8H2Om./j4Z`ZGS3-"cn\gi76rof#X@^*TS?8=>1rM!LqcIE
Ptaf6S1B-2*@7&m9A(#r0Lu6JN_5Ic=+PO.9(?X;"$eoQq-OWQrmQ5^5$j3mS#P@o+P"-:?l
;egFTj9`'\"@XSE,U?d,>_&WTA:/C8e,8@(,qHfg"ooLBpim2#48(='1@l4)jO$t8+329!/L
S4f+2lgP![ZP6dT]A/]A!7\CNCqt:?M!5W]ArXY)N58N#i<GsiH?^G).q<e63QT2%Fl>0d4>R1A
nQ9(GLc-M\-W>MBSk"`@^8=(HRlgfucoaYt^3IXns71Qt"d^-1g\S:3m<eWq;lK+V[UIe=gt
Z.4giA.jBAg5t&?<:n\5)VA!VT0-I:Q7]Al0qPV3gc*=mhHe#^(N*H_+5g4ja3RfD#`Q(Zn5V
'.!hF6jCUc3o\lp4Dn>HV@0IAtYT5iY^6]A"/il!m7o@WFHe7ZFC*r'2K/)0q]A*#>`@q&W`Q7
>!+3YcheDPS*ROMaA]AkEn4q(Tf'(rOhZlH.9F<HhGi,13J8/B`IDp`m3a2[nG\=4_SpOiZ?k
>UWfT1*%9[C]A8E]AR2s'[RG39#+Ta?a-nR.bN+c]AY_-C")iVX5M=pmCh?-bF!)oq-9jZFud\/
(]A8J6,>$gI,bFe-Y\Op\=%:WR]A/+`2nD9HQ!1(i%epQa6+[:c^&>5ES+&Q\$H?n`4b^*>Sl?
cD'_!1/"'oSru(chbnn-lk.k@[W1W-<hQebHSUTsY"9]A%;d?3"Dt:R9Z\"Q:=pUI(%+U%e6s
gZ>.I_)_;m%-c)W[hH'c*9H*^mU>ai&Z(]Ad66Z\QE(]Ae72I?nkl\*+;(<8'@,@/TW/Up+`!Q
%qDOE8a"N+#^5qO.2p!:D;>0:p;P/lH,EXJj=:KX7)1c:`?-3N89>."0M$nsn<NdO$o#0g&5
']A!!mq_316,+ol4j0g;Q=;l"AGZp;q>R/@oR'2?/dTTGhl^LPS+'b%l$-7&(>95+WjK4g,d<
moK<C-?G1o2eXo'6i9@dU+&DoPBceF5kX^X&V:S'"pkT\3MacaP@Kq=m-^4'N:5=m@14fRKM
JChci$H?3P@4@K\VA-;;euh8"1S23O&q*l`n>/Kt>D0oLqK=@G90Csq.5+:;F"pWAap2guI3
9ZqTerZT2[87uGq+6S49AoC2cd9*B=(B:.kNT:d7:1'J[uE5oEm0a9RBT/eS?2t3$1jW8_1'
3/p0]AkQ*n"i*e8f)-qt0@M!-dW518=TB^ek3)TJM7[ILJ>8j21&BVfg7"O4k6@!8O(<bX;m]A
?;tp0-Wr"X]AG]A%rD+]A;H]A\^j<D5MHT50MF2o3JWj=Ap$(1=k*)-PdkYCsh+,%uKiC$*bjNmP
lmd`7X4$#?JG+4rF!#-MNiUeqlm&p2&\G=.:D]AH,KTbJ?@lH/&Y"7#TO7qOnjg:kcV>?-Cf[
4$m)sDQ''S(I?9$)2s`\]AT]A.K!7+$GM]A(gO=pKk=mt0%n5p?H#28J&bC.OjaaHBRADc614I/
c]AF<&`S9_*`m'Z[0JGHS3pF>pqHl]AOu!f<lJiGf&"_u-894&<E39`@<[@kQ0]A8Xb>NL(j8qo
9jNpo3DF0E8.E-*?X<T!(!jAJ8j_A^iB7\Y>M)Ajj'o=R[Oo(rU:Y=C5SR\#n4#$j&9d96tA
<loi"Ph%]A#0t"rZ4B9gl+/OW@^Jh>m[2j>`f;STV*<$Q/RC(T-UNr+229mf9A0IgVX$^f9$D
O?f(r!-S4Ut;J4hD^S,jg[lf-+3"m\0OJ+UNMOg_h4C.1Wk$^9"nl=Y1rWD"l?Qn,"d^1ouR
ph43`Jdbs6E@R!N3MXQ/GgOO^d]Adk/<.?i36XueX!a,aUEa6rDVs\.*7O[M;Pg*#a!2O0,?=
qTRq#7Qkea]AS+n&`/@blBOd=PQ-L54e6'eAZ[kK\CH)Km^k>k`<C:a\Dra>8?hs0'hc[J@1G
AGf2j?cJ,*OY33:+j)3#g[$SDtRd==Lm)aDj+Qi?"_//4$3D(:\s&rV7qG"&H^.Zq\0^;RR(
)`Jdqa3;%"1eWj\lQ3bmm>1NCg6hIs4'[*/c>KraY/'[%7*#I"a`;o2h/G<FrDaSB0!(?a*I
J*c#!_bM8@/QFg!ujS\to(o#_ti*8DB=T?8bqa3GC"fMeg,?uk5O1NV!a,aWIN(S.e$phg(*
pHCTA?XLM$5l2aDXp.@hF[X3Wa!okT-#pAu:\>jS2/7eS4p@)Jef,[JfK[V:Y^fsN%p6UODo
%Ss,s?e?2l2aEW%\IOMl#5f]Ab,@_,1l$>n:C(o3JGdC$2f0ifqIo]AN65/?gg;u*90Zl/0ZEV
Nq@\_8S*(]A>"M(FD:`eJ\Fr:C<rp[ol>#U0jEc%SC.N@M7kg5"J)Ttn#*BmFX#>uV7['msdE
&j;^fZn@*>6Nh>,]AY66UK!aKN#CbW'rk]ASd=<Ai9I4*57\lE_LGoMoNbb9G/O_L[KQNttTkK
EPfu9a$XdNg$rRM/fZKp;:=op?bjWe0N-h[?2"]A(<ks+0hhjoQMI26QY[IC(:u8l,3t&cGA-
RDoIP^!0&SDDqqgaHmr[2e!?iHlIVC`s-[8H!W3>OPPb.^_s=3;\ATDX]A*lF3<dX()o$s?7t
%7^lq]A'UkWo.U^G.Tq/aM@_,I:TtfY%H&dstZ>!bNcB1'`_n5'5S2Ci:b!JXkVg-sl&2.Ds0
WZ<A,Z_Sj]Akb94*j[+fg=KakK>cY*?QK@6VkLgg4Gn]A94:aZEl2@I]A=!n_Fh8/,9&SjaVtQ^
C_*/`1h[7?W_#B,X#WOg=1)+?+$ha4X)%'NIK87C<slhWjm.T_uTYsNqk=d(oZ+IcH1Efk\'
(qIg`RVK!$5HMAhHESe>ZY.\U*fEP=lB&i#6$2?A=&^PJ[mC%,QS")#4C7m,sSZWs+P)s0A@
G+fmdG%VB#5i`CM[I1&"+3qGbo5=lLk>)WM^>4U\0m*8G:sZ%1#;a=W[eWWrM8hM^3`0R:&D
<S'J]Ag9+]AoW3Vm>XrlfYP^d_4aLgpQ#pVF2G@.,KD;:9G8YVmPB7?-);jkEq]AKT#)V_lPLSg
Trm!#+bDbWIjMM?)BDM@I12-%`kN@fHah*0T6kW3TO@cAl+@`gimLEEm6DBsE^U;NJ&AU1G"
YTmV>_b8"p_$H6c\qi"r#+%'D34PNH*f[&lLFO3B'ai+S(Z8$_u'bipJ%D#WVY5n\_XnuM!9
dL]A'iWTi'%<X&IYm3qL[biK#BRCWZTD!ifMK\0YcMGL-^Yq6,%OtD7Y@%4JLI9"NX'+oh-qn
rTMJ\3;*1/aJ?n@p5Eqi^@HP^jT363Yn`)J:At,XT!)Lp0:@MaVSc7Z4,"<8m^a7HILX<'F4
>\[X#0X0!9(KpYAr95m?HJ;5`jW5<5B!M)h5b(Q.V_jDFUYo[D8ZE%`CKnLiu-i'ln?-i0hZ
WBh,bn(G3K8E,JZ$jK>7WA/NYK.4,?Nr_dR1YOV=,%B9FBLeNjH'Vl+3(s^X.7E8[G'hPam]A
-<Q/>#b!(:X<EE;mQ6u19^]AQNuo_8c8QjHIHJs4TM8+9]A]AlE_k*+:hE&7uofMD@S"MVO.H^k
]A:k/,6/qhctbPT,(Y0rp`+NnZ7s,)YZuBSe-D'KpY]A%Z"<G.]ADg>CRlEPI/ZWc0.(Ytccgfq
8>fgEb887fIsZ(!l@[2@G*HWP>kt4S(XbFPD',VJmMI\eZP8m5qD/DXFmEAAnOQi7[V>KSe>
#p9Y7dRV,>Af:,H,D!m;lRA4D(ho?D'kIWaQL&P\NBsdK\J%k0H;p;"*u4O<AU#GhiolUVbI
4o]Aks<F59STk.M!V^9#fEM/+Cj&.kSuW+j$%:<ZRd+8>dT(%#uENBg>2e*fWJJMC)[::@,hU
O05\cW`,L0rHdI((]A64'q+0X:Ibr7dC\_lrb2.@MY9&^iV3QulGfMSj/Rj1]AQm*/eWHa`)A9
8D>gmbH^i/ePri3L/V]A(%,k=cqV97"IKp9<QE=+G+*`Y0shWBOqAgg-;C-ZT$727krZ+B=3=
,o7.GU?MG"Y?M`5Mrr]A-Kdf6aE5Bmj`7cBhR6o[,lt+?.`;0^@f>(L$H6UfJh15&0mb^"$&e
BZ82@n=q=W9-rZX@0#.W-%NqNYqC)i#:FDrpfl$q0oNELD\#T!%,?ZP2XXH=C`>^hOjK1[m9
61D(/(Kdk_06j.e)a8Ui7FuW!))UHfj=,A6pInB@')e:RBM&>9bUC[<jTd+Ic[Ocp.Cmb6q`
A0!N>Iq/TIe;Qm-`#r05k/T<0h.*k$j2>:"n;aIWP^Q]AgO9=2R>I&DUtDS/!TnsL:Mg"!8X=
Rmgk^XJ<CdT(NMmFqf]AUf_<';j]Ap@8cg0k13sXSL]AR[4a9F)m0gKLD@9'WMelQ)B+Ai;In'@
`0g%n6[@1?=3Y`JIB;[tM03LAM9(cr<LQV;*F(Z&!;`'Oor&(RFjnu_j9H4SJ'b\iqq[%baW
S7Z?"4*[GeSF]A7u5[U9Ls`cWfTe!`NGUq#)K#f`Eo<q6r'.UIE@e!Vq[2t*ekWa.:jMDf9gh
P:tk\tk%T9tRH,tfHlBPAR7]A`JWESA-a*VD!bFcIpH2=2)o7Y;.FfU(`dn?7R2s4JbJH/&`r
JD81"f[&MGEp7="H]A7_+*KA5^\NP,$B=>1PXT"g*[:.WSLH=C:bLlD`3>7T<>7R]AWVg)(5@I
RANDoMif'AEcX[Di2YF6p"a<V#\SZJOZ/P/6W?.1T@K=,kH,2T$qLM[EHrTp:U@2?l?ArU^X
Z6l9HMU!10+emWt"Hg5<87MS;>b:M\"F0Y2r-'n%ms-`WR-mF\dN^O]A.W3#F--9Hq#LW0+O2
UkQAs01-`t8JWT\TZ`c[Tboi/Q+^>]AtoK\YN5QrC$@">O%8s&R[A:qihU%e_lD5]A#hm"36t?
us6cNug*ar.-jX\hoB2`rY4(ab(]ANfn<NF2nfAh,2Y&VUmDf!]A4B3r2kXaXXO4]AK:*$M*$"n
[Ld1[JFBd`PAOeJ:IJ>3,'&Km\QO>s3lW2g-TVqcbAm^6:kXNR:+Pb&e.'M_NH0'<1%_ZT/k
^`*Q8;m#bH)<p]Aj]A';J3^c&'ubb3m:Ba@b8nH\UCrC;epUK&Q]A<E<fe55g7=\hm3-_p\1#U:
5p2S),&s&MO^j(<"pJ(EOs!OZ';QQ>*0=SUBTd-Ym'-%l7U=>@3]A;OXM+&fT<2EAi,E#!9r+
XG0]A:mD2D(1C%)nVcaf;S]A!-BdE\>;Ceq%S\A@4Vl'E4EmIV_-bnAi;$_kZ2Xq'&=9c4.,k_
3&lh,p*Cp0>1%0lq4kXY;8fglJ<c/m$0%SZIYA$ET''e"[2Zb"X0dQA<-KB&]A2[;B=@,X+lK
K1Z?8BO74M>)LWlKN32kUo8gA&rLbCW&bKn.ah-9s)odphZZ,p.*%7A7D"fD[`/NcB=P0'^&
^Rd%(MXWhg_k4tmJ<mcgDTM;ebrM6i+HD,_QUBd1)HUM*)R+4*&[VMq4N3;5TDrj-Ibp9uM0
n,D)A!W~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="118" y="66" width="404" height="234"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
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
<PreviewType PreviewType="0"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" horizontalGap="200" verticalGap="100" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="cd1a4f16-1d6b-4aa9-8b32-f28c9551e6b3"/>
</TemplateIdAttMark>
</Form>
