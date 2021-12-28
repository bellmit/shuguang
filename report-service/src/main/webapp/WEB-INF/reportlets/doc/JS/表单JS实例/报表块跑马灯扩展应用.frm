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
<![CDATA[this.options.form.getWidgetByName('REPORT0').setVisible(true);
]]></Content>
</JavaScript>
</Listener>
<WidgetName name="button0_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="button0_c" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[显示隐藏报表块]]></Text>
<initial>
<Background name="ColorBackground" color="-14701083"/>
</initial>
<FRFont name="微软雅黑" style="0" size="72" foreground="-1"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="102" height="33"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    //隐藏报表块report0的滚动条（此报表块名为report0，根据具体情况修改）
    $("div[widgetname=REPORT0]A").find(".frozen-north")[0]A.style.overflow = "hidden";
    $("div[widgetname=REPORT0]A").find(".frozen-center")[0]A.style.overflow = "hidden";
}, 1000);

window.flag1 = true;
var self1 = this;
//鼠标悬停，滚动停止
setTimeout(function() {
    $("div[widgetname=REPORT0]A").find(".frozen-center").mouseover(function() {
        window.flag1 = false;
    });

    //鼠标离开，继续滚动
    $("div[widgetname=REPORT0]A").find(".frozen-center").mouseleave(function() {
        window.flag1 = true;
    });

    var old = -1;
    var interval = setInterval(function() {
    	if(!self1.isVisible()){
    		return;
    	}
        if(window.flag1) {
            currentpos1 = $("div[widgetname=REPORT0]A").find(".frozen-center")[0]A.scrollTop;
            if(currentpos1 == old) {
                $("div[widgetname=REPORT0]A").find(".frozen-center")[0]A.scrollTop = 0;
            } else {
                old = currentpos1;
                //以25ms的速度每次滚动1.5PX
                $("div[widgetname=REPORT0]A").find(".frozen-center")[0]A.scrollTop = currentpos1 + 1.5;
            }
        }
    }, 25);
}, 1000);
]]></Content>
</JavaScript>
</Listener>
<WidgetName name="report0"/>
<WidgetAttr invisible="true" description="">
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
<WidgetAttr invisible="true" description="">
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
<HR F="0" T="0"/>
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
<![CDATA[2743200,3314700,3733800,2705100,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
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
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="销售员"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品类型"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="产品"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="1">
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
<WorkSheetAttr/>
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
<![CDATA[m94j9;s0J89Pa%,Z-qLc6<fRWCV<InO#i/K5Vd(+[Q^EcX&1-G$E00l)\(-S<,J;Ne`_6(<0
1@C9!(]A"j:Ob"$5%!38LgYN!lTm?7M6>)8BBh]A1FKnODniARB1DnuQYWiAL[R>%gV:FOHiNg
8Ft?sanFE4.qU9N34nnM;o02s1\`]AP34SRXGc[=tGr&<r4Qgl0(koY=-CKYhK\NAgjF#nD[G
j`1PLH\j*s3S%DmrCuQm$N"\PpLd8^Pgm^Cj!^Cg<*Fa*LJuZ\p2B1^Du"jW4RM+nZ8Q?ls9
;\g[+!upKi@K^n.;QB;<%FT@<U2?-`<?1Kh8PS_8>#5*;6_lka#g9=2P3/jVa3fsd+.WcA4s
a4N(eA3a^lL"pg&UijC@mTlL0&.h\ZEDp7aL,3$5:Bf5GIO1i(db&GeF&Dm8cL"6R;4UBD'c
NT`BTl_C]Ak%!K<PPVoZqt_+d[kV3FCcSo5!]Ajs4(c1.N-#Y=[D>"srM"[DMN4*KVRtISkJmP
b5;/st2eV6]AB'&b5HG$;XJVu#4FUZsgeUdA@DK5duc]Ae8>mEQR%BTd+^=nJ6/V+`d_iu-P6I
f/Bn3J(@WRIeqSMfu@C1$+uA)BC`:Ga,jdH-g>]AnfFTh>K>'DC\;/u3]A/IH4jtQON=AH7R:k
OaBo]A,e*^&3-ED1Lqi]A-XPp02Ci+0t7%Fjt46@98/k^u.K<b/WaCokS^141c?[92!7"fETZ/
-(7\gZ#!J;QeJ]A\3]A/TrF%h&PTY%7g:*#09<G0mtZSJbT2;tp>EcPW\cc=V[4ULW$K)1k"J`
4n76$>FVi+afUkIHf:h5753V;pB;1Yk^eV6Z'(oZHYSenq:sH7efYgQ[%uMh6<T&js[_K0kf
4RFd0Ff!-(p;'![kCBW\(5@6@rAa$P/D($[[a&Qbq/6Xi;\nnn!OuF<ag\6BJ*OtEm\S@8QH
.d:t9%L7bX;Du8F%AT((ujamg)]AgXorMB+;i4!kIBNJ7%^*M#64/k"'!5thbl1:A['Sea-3)
st<oY5uD6i*X.V&@np07Pre1=6qa60Vc(NIcMF)BS'XmiJ:3sH>U0Eul?/2j)A<Zr[+7X"(*
&(:]A_R(,'[9\7i3OoiS!bJ$@*05THB[lTVh',6u$n'HK[ZfV0Z88/R5_Kd5%"%*@r9nbU'2>
Bkj,,5'3:j(b'jd^,&4ZUq*o".R$,ntE#k"@73o%;6taoEd&huLKdDMl+&Jo)L9?'&2(XW<(
@U3H?$#:Kah44Lei\2D90p*h>4>mf"7qRea9fEkcg_(iRr<diHHd2D'26BNVQ`nd:/,HM7^7
V8L)-<"".-%9@\FE(?\_7i[tZJb`Z7u,0T4*tUiESlB;<1a1]A<LLbTR>QNoZ!#!fREa(CW`=
Tn[eAOq*bT=X!<^3eko*jsl-P?hQ;nVgLbIMo-soVVKcSI2#cH.V'<2MZIu^EQs"33(0%(3\
cJYt8_AU3@5?7`HGk'Xq2_/KQ@OYHuGAb%XX$[P;9ppF)cW([#`T22qX>(SC';rmI9V?8`1j
B#O/k$^.U9DM7WKHip-e9,03:;OuI(R7=:'NSe]A]Ae+F4tf\^;m*CREgIdrhHj;K?5-QiIfJc
sXhtAP[=%DB_l5pa9$UlD/a+Ht.=88q++:SAK9#OkfAXsf7WMN5-6:--B@sB'$2biW(nm3To
c@*InNn0J='Lk3Rp>_7*O6AWZtnO//<>ll]Al$*9XfL)S7M]A$kY.V^BR(AI3#9u%aNd*r1iP?
9AD$:),mq%d-Q<68S[5ghiH@&LD=9$t$]A80=I!XAkm`E]A/'B>L%l&F%%3L)>O*;ZqB9((hO4
!gP0h^F+DOHZ;N?W$-L(\_es$r/uJ+g]A*,'6f<7ai.+G@JTE-Tkur<#VbX0=,O#COqZ]A\q'm
SZN7iFH:'$,5RCk'dhE(W8/Ulm/!lIl=_F*ck<-_IF\=:-JbSYFB.YbS<bM+s%uT%*5QrRep
3Q`-0:\p!%3hF.=A+0a;f)E/U=:=MKnn-X(.aC'\nJd@JM8aM#>6Adha\.VB\I8?lJ?2cO.`
\P.!qkCKa,FfiOXCIaDl8*]A-Hdj@Dl&'UfYkfucmY"t=$D$cfV]A6>7Cq,ZAZ3kh]AgQ<2@cB]A
1jK8`.Xo'nJp(=TZQZ+PY%)NU!@1OC>[DDJ1rV_1CV5&'Ji9i7=hkL5P=*`B>aP9imX/>Y?^
lNI-Q7M]A$S27:DUVL,q)NFn?UHVZ+W-L8;#Aht-B@I$;e=fsSc0WWT"^ZMW:jRPU+\P1A4ZU
50R;i@&hLB^hrl=B7>Rd_o#>/R-WBmk*^IRKZ0=pPkp%lmi9$LK@6D)Nd8$Vte/jS::KBc$<
]Aq<flr[Y$fnB=`q6<&q'+^@I)g^CC#2TSB[Dm6s?XC&-H9%W0DeBkK)/a(k1&R+Vh2EDdr)b
ATHS^.D@K+4EVLB\[RYRPBm^!56op1JS>gTZ:Z\\ZYRFO1[In.<ic]A:bQb)oQ5#.B#U>>b7)
MV(P_G!gQ%9H)<qQ.$==6;Wpn!>eadc,*h6QtFQi@iBm5JIG&<+3L%Z*gDjG[Odisg!K[KJ2
0f3mNW00+bB0etA:nl8QBfs9k$&gp`;D-He+<aJfe#YF-93TashSlord+@DG_]A^FmTXaIcU^
ZMo$4leZ,tr-sW*NKJK_[]A6?Y$OHnG)4K]AeJRl':Rc7W/tB!/46&Xm&L9DIC(;JLph>pW(_2
^C$3'N)]A[122mHOF"fIW"!j:JjWn_-hbK/Os;,^G\K(Q>0Wss0p_8%&FZW^@Vg`s(p3-FQ5C
iF^%VgkO>3I_`_N2e5sKYc(MEcKp0dT#N.qld??-ZADoh,K4'I44$Fl/!J&EC9W@-St5QC')
,l.\%g;o)#?5$[aQ=Ft"<#.Z-KMPj8A\MTr&qr>MYPT@VXCnT;IP.lH[K9/?L8gJk9^O`&B#
K[i[GSM3uJG6$kaSBW#!:H`P$:9)5hC^b?2cq(H-Vi5.VWub(oc6aA3SHPkU<03+kF_up2=2
6bN3?n[od$?*$Rs*Z.UBXaB>NaE=Va4X;^U,YgN#f+I<t9rQ)Sj'-aSIfdNN!T_enl:rqpBM
BXgjR*0-d.SFQ'^bh(q[,,XA-B07Kh.0#*(QBu.a]A*0UUjip9ac5q,4M"'/V<7L0:r\i"R<+
&0W]AY]AY&QQf!Vll62+@(7(,=`#NkZF=I>R`fR\C3.:'EDmL"#3=>0f\E=F2]A#iB/]An(#n_rC
R*7Z0EsAFnC6*,d:UUSp]A/[FWgr9!Q$\m,UJ8qL6L#dGSbjd3u-+:?WRNY>2*"F]Ac3:K1'qE
1nI?'6u@ur*&4'Z#ST0XMO7h)BR3BCs"W]A>eWE9*A1QePPu>is9,^P91-XBAnS!db8Sh,K9j
sPr:)nm,.:p#MC,Z[LFi2e&&ZI>`)UtZnkHQTs!P4KR^"!A\oi%SmUsd%l'i!,aNG:Ch@CK(
+?A5X+]A9(qb41=I4U>/Z1L>JM^8P1`/BK4`^Kk[dm]Amtd+jpc]A@FfC\#=W/n%a_8"2%>Q]A,%
SKT7c1_?T&`cUqjQip.(@60D2Pq4gQp"3<bEMj#>i$)kZS-c?Xd1p?%X:T;+t_3#7NgN$VuZ
+`i'Xrp&Qk#KaWLsnCEWRsNQpn9nC@lV:^+,Y>oe#VX`[Oo$DZJB2<RDR=295!Sr7O+kAsc*
Q>*,uW?e?8neTj)XIbJ1o7_/Ya$fILCYV4eoH]A?Q(+X'\LD&+j//:d.0_/Asr'h&'Kl5U@Cu
i`mqdC@?)]AHb#nl(Du%n?1ZcZ0rQG+@$BftRt%f%M\Nm0Y#f#@H(e>b05a]Atg\acF5R1qh`^
^Sa8@D9&&]AMM1UMjman2Sq>*;Mk!$2Tjl_(jf",E,ZFAE#*o+e+g3@^.5QP:5)k$&7*?mIaY
2*i>4*,qj'90eN(??LA/[R>UWN4G?:(>q!:\82.3^&QlX[q3b)GAh:&7$\X'jS"g-uNj1G\[
",GO6FmV<"cSb=:d<*'p>6)U*hgoF+\,RALRkV7M)?R#U;eQ:>6u[8L'e1tYImQ3?j,]AKOo/
IPkcjW&GjA/>C(I`>Qg`P*;EXI'0!5EC*#[>1]AU7_bHOJ'sM8Aa'1(8F0"kSKIGQq"]ApnfbH
S,n4lXH8jp!tC,%Pbtg6J_fo?<u4-8T&CR?R(gXN/S_O/6D16k9VP$To-/K8gnXa<0h#g]At@
eQ%=%Ymrn"^cSp-Z_03nH\eN#O#Te4;b=F@ZLG4%9VnG9JcXi%@TRe*bKL'WI_N"sep7j=Mg
H1C<!$3PQ2qJ\_<&1h.:92717Xe$e;NI.2hh;hC#&HW%.r2"t)Dn0W0*kj+A8_^H^p?\Ek)G
#%CXAOJX/!r^2BJr<F*+El`Ki1V`hQkA@WrrdHp)+W&X,6G6U1:.'0GDAOfQJ#51tk-)DmG'
lRmfX&->g)4r6QZW<X;S914j8+E9dT&%U)W=]Ai0&W2Dt?b_[MS+,SA(:Cnh+NYSM*#9pCq:/
7DqdSGVh#6Pr6[$dG$lR"]ARPet=cF<?43Y]ArXW6Gq*?16+."-HliB&@m)Ljpmo7#K:op4,"Y
mmd9DQ[LXOb71t7:0o')4FUoqr*,hD]AM]AkjA28S7IX#QD/@]A;4\i^m0C=<$gqs1Y9U3iZ#$'
Whn^;.0g]AhVY-m7e/dfLBIkp]A2Au=q8dcVcn-iD/#?CtV)j#f]A:I",F$QmJ*05^<G!ZOi0aP
Hhar#D1bI4MAmSn2W\j[1RK?u_hBf$gI!_/rc'p;"A>qXbNe`0*?Y*q2*/KQhBe'W6k"Z;5$
2_X:]A-l'6uMkW6'U]AqKaT;qKMW*^'FFkU))3p1,jm$^T="DGIsG)MHZ`IZc'CM&lFIU^tS9+
[[I=6fR#7Tu@e!L%dAj2H[5kPG:Vg``@8fKpXtGRYsn&+3E_kV&mdA@sP`,mI7I'6I=s:/I!
N0oua4jDLjU3CUhgj1<R.=V0JHHr<8t`Tmh*.'#2hMX\XTY[hn&$r;7S\Df;]A9]AI=uhLf9\-
dp^^1-8qd2"pbB,7#[7V,]A-s9mZSaom@sN?hEC,[hKg',XXHrBD?"ZJRJQLft/ERpmq!n">e
8t_CLFi+CcNhcc.;?B]AFP>_PX=,VZk=>G%uUU^-l![A"hgHiY6IC)f'WKjh5"<PAu-FTR&8s
oKWl8*'0BtnYn<:9Khpqpsr/N]AukM#e[M.\]Ar$9m\"aH:hrsd@nJTW[A!rm3Mosk,7.>d5*J
/^W4VcUKSbYJHl6TJ]Arr'unGEr?T]AY?1-kX.Je9*W,p#!G(1"C7\u9eqh7G^4I:jTK=+dV_t
_HLYZ\8c)BFI1p-+`M3P>p*%53EK=R5r`1!-fDV$Xp?]AHtdmagqnX+\,G(-eD&(@BKM0>;83
;V>s9l((m]A&`VbfUegAlTTk-Hs[\7Dq!SIaSfVM$ljUn27%@sak'DHZhC>4/T>3&"eXOeg]AW
+7!\CU*56]AN[D5t$Fs5>1[\1_7^O%tBeLAp$g"im[VQAClb\bm\eIi#9aXk(Gd+>^0Y-]AA91
T_jOgh6`NlY5kXPX<.Cd+9TZ9c9?amQL6]AUW9_-P,f>fDq.#$#:FtFPDIpGbJf/aC'EI_>H1
TH)h1,3e^<DNh\@)t0F^F:Q!MJ&HLK+r:ToHkg(\>F[]A7$2:X#X/[3$[qplg[4DNe)e*Z'.`
YZs7ZhpH8Wu-,"XI(KB<0LtbI[5.HCa_jeI4P?@$u>>)K45UhkAAN?&M>hD:BVf>=-jTJ-p-
mSOIc(4q9+PTD4q<)MYQ[_,,/n`EHMBkSd!%ndQM1)+gB'f3oi(?)k$XA8?q;9XWpsW`1*</
HI;.Y*CZM#7YE(3Ifk=o@IL.FYk;O946@f:<7=gA+JK`V,aZMTd0L\H[%PfN3@b)*/`4oQLK
3I._?#u@o\2rsfj;,H-GMAU<P`%?C>k,dp-c%#]AP+Q<,$/7D34c(`@:=$a#-R==plHh_nJpt
&;O*K9L<_04QPT1+<8&7YE-4R*m$]A_"S8\^YFojkIQR$UGsC=VMdYDm:@l'RW[#NWB`BHhq/
iFN#-PpOpaF4Tk<$`^.?,Zrc&5_o`;kB$&:JW/,f6bnf4BSY*?ekYe!_'i,h;hP1L+8\4#lr
0Q;fg,c.l0teBNT_h+@ELM6>YcL#=mVrDhT@U#5hJCu+L=^*pIg.+l3bFtS8Un4FS$O'`E&K
jno"QcJ&>(p/[9`j_XA:?up;&V.L,bJ0<;Am#Z*)cJ7TUap<I(+\rFj8mqhBC]A7M?>Wh9YZS
aJ*:NSCn/IkZS-8pbYOh4:+^[dW'-F-lnbMcM&jlmJ8YsNl4>u:ZY`nbgUZlJ2;^=.m/R%D=
^cgGJFl^M$..Fa:EaS.!'Fd"ISaN1Cd_h@/a2r]AFCC1#?S['Y@rAS[D+'RFEkM&S5BrQ%L.D
ua6^bW@^&V?R1L3-LmS8WrUT+=N+/pj'E\!n9Ir]AjC]AcON)lVHC4+XK)2.n7,\h2OJ]AYUk,&
44Vg4jtU!K"N86%+!:@#jfS<6G"`6H%,;W6@V=#2\l*UKhaip+8c8>CNX;1%m2CZPpQR%e%L
4BU"fNsi83j8*4NAPkVgb?p_lkqT-VAN[YB,Lc/8i@#crCg6u%ARdaoNogc/BLeHTL#m'c3F
m<aAYp):5TdM)@G*H%lbkKWY7nn5k33u`s1j-$`+9-c6emfH^S`\ogLbu<Pjg)#>CRZd=lH8
LE2n7XU%5NTc6&XbJ@8^HJ[j-H0`L,h5Vht+7>JN@;Y]ALVJI&!hgloNBa9@.eu[;fX#ND306
s3fYm#9h,V";&\WS4%e?<C-&]A)<bEo[%"l]AX,7-5VnAGXc>/Doo/jhP@"7)s:2gq8@YTQ)j]A
,JU<:LcW]APHTMBF/q]A"0X*apE@JJ9EP:Y,mRL+Ol%&pK0DP>q\V]A$Z-?s*0Glf,UD?NDWc76
WKEs40a9e<q1^'10lC9+`nY@H!::k&SI;IXq[=?heKA$V?bW3'[]A%5@)EMRcEGb0LNM)pSoa
q>hui2Vo!uNP]A*g"Fto^:aR$Z#.I`!BTm/#"CV6VDjE*_;S`+p?!R+leU:S]A"O!kal8m8&bJ
V.#o6B-P!YHCm'7;+VDA;=U9V=&nI69X(Rl'XI^3KT!Um?\I5^M,I!\o1fR@NE;4),TMV<%G
^N[qK*9V^WBd'GakKE-lO)kY,1K5YGQ?>2Bl;$";)"[[tom%l;*^B<XcQfQC]A4Rp4,P)kHbG
E>N"k%c>ljqYNVbQ5Q*fkP;*Z'Lh!#\nJ?e9*<^$?4sjeEh"AGlIVW,'^j\A^3D/MQc!fJ:1
?&SmI7,.,u3'$&tR\iU-g'NZ2bb9+$PWG_TSB9m$1s<MTpMIiEb]A!gfRj6pCu>r6i$,^nbUe
9-+;f5gLCKp;=dHOM^fXG2)qTP5G^j[e.rM"e0]A91]A><.eF[KALEEqrJ&p7T2LCo)r+2/b+.
=Q,2""_j9THfAY0F45+n1=hKK[$[Ci9-h,SgrGFh+n^k,SrYf45"OcV"VHb*fCW!NY5N'rW;
:,@$30kJB3qU4bIQ1T*n(C<NO,JB&^U[=)E;%;q_*TlG&V5GrcF`U&q8YG63,")*]AX@#0k/o
N;WL%QMq[CI8b:q7.[0UZP1s&"13P%%2_eYo%uJi*%<[3>^`YY6"+5+HZdJgGRh02hueop2!
^u>BJ?YA>#T??d_NKs6F7f1n[tC>AnraqEWNdQ90-u6k]Af#]Acd)?=.AYOcR!PD9s%b3Peq[7
?YJYd59'7p)W\V:85+`j!*"P?I@jNS50NrPoZ(Ha3A+.$$/$P9^h#5_S7!c%lC#C=jaYf'la
k<gEG[TYGZk]AGh[p/G*`'B(U(GeX7lkk1bEh>\Iilp@oKh$o[G1Zc~
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
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
<![CDATA[销量]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="0">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="地区"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper">
<Attr divideMode="1"/>
</RG>
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
<WorkSheetAttr/>
</FormElementCase>
<StyleList>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-16777216"/>
<Bottom style="1" color="-16777216"/>
<Left style="1" color="-16777216"/>
<Right style="1" color="-16777216"/>
</Border>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m94:-;cgQ-[53']A.@*^Z[;p7":Ml.tC;#4\fG]A]A3UXDo8.Ktu9KjYK(,1kVig6O-X9PpII\l
GhR6qBrX0our.MF8&bPY8rJ6qK^uOG"l)+nsZgX[.0ndAPNdkHt0@cd(B_IHu6ppO0fa2S7j
MLOK$@(SC?inTjuUeX4,Ipe4'MpcLGkrK#;G7SQjcO3c842_hN94hjlN&<jeQCY>$rN4uhTF
#C^S_3T,Kr@L,.$1<@/%l8H5cXf?8qVoAQH=),8CiJm=CO,8Aj/P`=gkd6GT%27p:[P4.PgO
aR6C/\Zic#]Aq-cW<qJ&$)jgDqiSK/Rh#_DXN]AKA?)+WnN7NG<5f6]Afp5WG5$mg9f^B*A(T&,
)Gj.`@a?2+CWKAOJ`f@WKoc$mYo[8*S.U>sj4%)gGYBLe4BF-'?E=*l*dI)"^;t/c44F=u)P
*U?(L0@=7ia(s(T$pk?GehYYKU=6#HFkVrAUUKYRQ^B6"W^QfNrCupib-tM!SQYXXS4i^NF!
?nMoh_dMcdSkBpP2iJo8c`_3D<*s7s&--iV^:6<N"Ffuuf_Z8c-IYHbBTO`Oi+t=:Xc;UrtW
Z?0(/YrfK2K]Aa8?XV<XorM\jk%6+043rkaa"iGhYXSd"?s%R\`)\#hJiZl3Qfq8u)[:"$1?[
\"`TlHW=N.bGK1,+lE*^foQ)(IVFO3!6"557',7QR:d;F9B3Lt#F$LChoFYS0gA3FCaJ(3#6
#Uu82Laj2:$U27:!:^l1$D3fU^m63X"b!RX*K2#JVXBhpJoYm?BL"F<&m:jp+*lCm.5dTRHE
_#W*fu<@o((oE7-qdh4lBr\&[_-u.7)Mma.Q:u_$2a^0QJB,Jt.i.fitJt_#VQ:Y*Q5`g8Zi
[#hMW)LhZM*L&UYBJACE#_aj\,l7(-Nc36b'TF?LjId@IL]A+KZ&&"1NBfQ;tq,AagJ_?d7u<
[!O]ALHP3VjC]ASkYuG/u8i>]Au<\RW@)nC(6NfPu?@*.0Y,]A#S4)R'_ip(uoo7F_MNV[]Ah!@<m
W[>#eW)`oX\OiIVK.Ids/+U[ctZ'NFkt_c>]AhXo=H82esHr(-2/P=mAe#5s1C[['h+d(V+/%
/"90I[!bNPF(bs-^Tb98dVuDOoH?NPE%Wt8&5X:s<kY$3^IJBgnFhrr2_QP.>,kitIceo@N'
3-P/Qk<OEA2ab2bS'Pdb9GS?Zu<)1i;;0=(^?9AC,Q=OmP2<\tKnlAe-_KH&Bg8f'*SQ.YUd
@?o@-;7kV#rIKG4))koA9&.oOD=S2EQ/.HC=;:>#fW_5HN2X`Ff'@JJXs7f"?o[1@]A-I>9&1
i;4!G4Beeq'>(e:":'%-Bkch@<H-MTO"s=HjdHu.&O]AL0Zkf)@B\A(jcO*dc,rA45ZG0UG]Ah
3EN,8A^XF_lBi/9g\h*QD-mg8T2BP@A6M2BBdYWl>QGOu;UBhWbU5a,W[bM^qTKi3!]A=kBU`
pFT(8`aHZXOnFA[$1XHOpfa!OaLJHV\=nGA1*6t23Qm>72U?@>GH=\mhp?^Z^6h-[XMqWbE&
F$qS4HAG0;+=BC,9nMX>ODl;FN`3\hM3!FcosM]A$[,6-!1`3O\iU>;2d*h%WXYHi]ABopZMo*
^m/,LNPpFPL5#o>]AqecK2'\5]A!\^joj<iI7#p/S2gpFst/LIOY=n4^B_6.qdfeXh#;J@S)#Y
`!-M[TT+JEI+cGCmIUQ`,$HYk+Jk$%!jpP`2:Xs:T3b)5UodL+MZVmdcf=.*iNMPW>QLrSrq
k^)$$d)A6W&9^R6$[Z45kf0pYJ\AfSN-::i2*XaMR7?rp6O-9$QV=j!N2Xr?14=_eAh\X&05
Y1c^j^us0io(BIDTP>g[(/bQ**(hf\7#s30$:5r_nB;7W4SgHpS3UY=VD<,P0tp"WkBkK=J`
)M+1+oKX^.2HZSl0f%5Xe'2/F,^,#`$"4cr%fD>jG>KqWFWh.a'-&;:"2]AemO[qNT$:5pcJa
U[ir`*ho/ksM2ABk4mn_c<Li`]AVpc^QDuQ2LOp/r8YCl5sc"cgWHsJ;)[0R&8I`jqg0Yu#MG
S@R7hb%/7RGa@L_Sar_$_L&\bAI^s,q$$nnnCJGhVr;h^i,JB5"1=Lf$#"qIXf&7r@jkdEkK
2V80l0IRp"m*U!BPBQr9&ZDJ0ciMBVnuIcNs)dQ0Vfjai*:bf_8cZ7s]A!"(l9o<i51,NH(7U
QM\0b=<(B$3)Dja?s8<7f+4fU!E0d\TEiKP;KfBrI,ODH&0!`'8$T\eRP0W:Q-Y[G3)A+)$M
nC$\h\d!Y9;ZK/o\2tl]ACtdS"W.`nJ-\VFfbN\B6')j*Uf(W&]AM43a\d94@!O.ekN`f@`b,X
=K=/>6UV=031tDH*&0(e9)31*.%_fp]A"U,\FF<Z1$JoEIGdj]A@ZYjPANhJ2#j>kUj[>:(+R>
jhc*;VR(@^XKPlJ<:mu7Eh&C[C@P[BnP73R&3r4+",kN.j2r__D.5,,GqupAe?Wa)n/C/CD+
a$luCW-2,]Aip_\)A^StPUZ31=-4QW!4:,u'AaM'QkDZH1TiQElHL`M2B(eHieQ.!8P`BUasX
gE\JpeUe-(%N?RC4KSdHJP<GXfI;3l\CpVL=;rU6&Q)V5npI)l<\Z1>Md$LD1mrq@mU9ci0q
R*hX)VJQ'+k'k>\d0Lls_-I9%FVmQ`r20NnlU&#*O6OThDRg\X'Fe_T9.(_D\6\R=&8d`P6s
CD'sb<'Uj58aaDd$#e.V<M'YaD!XJPOYq'\ifghQ!nY*AYeEshh<;;E*Z=7bjZG+nC.]A;oU8
dOHTSFV[X\Rh`aNVWgC.472?P%ut_[8RI(eIMICQ=iNs7NbYY4)WOrG3R=h=7`V7AhojU%Er
*bdG1/rCLj<qU:,Wb)JCcb1op]A7/D;l<`2./P5aKjtL0:./P*ZG32>Nq2K#mo'0*P.X(G6eh
5=XV92VY!`AU:mml`q)1HcgK<,ERa6gBT'5:j,QfXIZ4Km'<!UCBagTX<RVPcCTh*[0mV;0p
[gsODU6GCJ=m,W>K1>)PGZ*2Zr5(%3AqfRPXGJ8u%"`a?+Wh4S*SQd!qC?)/_S6=g>2:gIc>
.hfs%Fn$*#b,I.F"9n#u2O,O8sFDgAeL]A.]AJpNh%9V3uLP7kGLPP<3uW$\<D^eL["u;m$ZGn
]A-#5n@lpA9njVO,@+^%<)Od.=+bDe=G"HK$6m]A2(2g9m9*CuZ->fJMRRX,'b)Kdf/Tdcb4.F
56\C*'JD!u]A#[!Glr=fU86MCqbJ/OY-L20Oh&ENua'NCh40[uL-#b#.IC2!S+p+k8AaUP5O2
A$L!eY-e`r8PMWn4ckT)Qm;B,7nGjVkAt<g:*$%TV*_fl!dn@K/__\u*A_lJ$&7hao1#Z!I#
_.O_RZStDGGACNB%8=B&GRb:Jq^]A5;,WcOa-0B_#Ph"\<VS13H:eqg8HQ-3^S:m5Zun]AD:i<
tffhd/ghL24rsB%>jgbQTN#2<Eg?\'i-G`9XcRcE76&V4ZJ?ZX_el&M&T)?MUJkBFQk@2O2&
W7Y/%"QL4W3*j0WH4,-!bd-7rj5OOnO9"i'PEL\e3V[:)9a0U&ah1i\$ZX0#fUH/Ql4<f^JS
JEe79Vtd9>fu/%I;A;051P&rF)ikNDX7pM"0ADr1bdoYXK:mh0eBHc70F]A7e#LMG3OqcdeTr
@+K%U-.r\qRD@)d2*S2cVZ$U(U&I*%8VQJ!&nZ,g@O&]A#hs:*d9P3U-l%DIPK>s+gfQpaY0l
CNSWE5m#LluVjWK9kMe>C?[4\*#ia3"lEG[]Aceo3\d5)4)t2e2a@jkKnZfSCaV5WM@)%m!"<
W*@lZOc-SN_rR_9KD=N'Sq5LLUSUm+mAW';a1/G!um_*fm6MGeSQ8O08X94N)QejeNO>N`-S
kd;]A2<s6@B7qF[.nnO+6Co_/8?_GG]A]AaT4(<kuAZ4?;:`uAYq@:n;NR<dE,mW$PpnXS;mhjM
h)oK*.V[SYb*o>Se901,G"mJ8-j""is(K*i:r0Y<+Tc1;!4;<&_XZ^^lp&ief*T@Z4pO&Zb[
3s;K!ZFFFY.gA(8-U"`@&&*qTi]A>qK8MsTdHYsT4/;AEn@Su.2`TBHO#D^9aOA&^:_W-h.6;
T"p/ppcSK*T*i4*p#"C*4hiSN3#-pP-pFnF%/X-fj1Z3n`ennVfndaNfI\=7a"nr)05gotIb
2Vh-hmW!F6i4(`u:U@V]AC=hN\`p4n9cQQirAWL`p(!Vc^NZL_L5jb4_pan$c-J?=@binB&dV
;VN$/dC>i_<F`FUi8Q3LP]A?52Sta'j>JO'&&'M_$WG5X'un)e/n'Je#qjp+eC0IXcal+tZkO
<J=FEh=9=TP+LF(e`LpPVro$BF>LDMD/6s0U=Gepm<Ua"J"_H6ab39cFJ[6#\@+/,k9#2q)"
KMMRliA1q7W#_*AoYT3dK9o(&WUa!bi:9nDo+qGln?:HaLn,Np*_;FN.*TKaj+>;H*@&2_po
Wl<SCc%JRYWl7gZjNd<Q,slh\i+'FkZf5.M:&lEd%P(Ldq[A>X5s\mLZpaVJ/XgVkiGB8d%#
&WHi$Vb1q@<=u0,.RcQpoLZP59d$o5!D!D,Y#fAeg.ri,geKDZQV<$EqKuhHk#mmQ0\\$K?S
NCoI<7K[5\]AY)QNn6u'E/N@E=hO:q/:T'Mpsi'ch5RCDFAPu?hfm7@Q3Y[@h.hS[4W]AfL\+T
*IgfmGCX;"[;aKMk$-N1(T]AY--t?bOB]A<&8$9&]ANG3hJIhDU4QK\3^Il4D>L6S\-!u)>ZI>'
Z^mBU7Pm5i#U8QOnE=58lbkoAlW!.uA$V-9H=\0)7_C@T*P>RJV,K.^aMD!\-56JC8kB4_!#
7NYehK/k4V("Im0Cu)J&E]A(lSlT6AuPM?aV90GOG!&B.XA#"rHuL1niilUhKhO1[Ef7_Vbe#
$16STDn<Ko$lKNgh)%b2-G.*7jX8]AL!QLc^WQ^9f[QhQ*62B5TM%;'4]AN)I$@iU)p-An&Ui/
`6(MI*N'G9"\t2qb<DQKs=1:n3(f0"<sOU+489_aaRYD_+Rp2"3>:3b0`HQ%Qh=ndGEaDQnJ
7Via3&PrgZsqVC2p\NO?%S<jGToIO;\CZc2Q^27*dZi!rXLlt]AFh-":70q9<bZ<Ec>d)PHLO
*$E(=BE,eh"^S#F[e6T:]AeCjkJ<<=*l-U[K^$6IIM"6?)b[C_"k/oH;g&B;&4%"Vde9!.Bdu
,aS2Bb4O/[W\#mKM$lEt,Bpl(J#?&JQ8feSnC.5an$L$61C[id?h$.cWL.o"Q8MiOuYNM".>
re>(V;WT^IJ[CGg"b0%Kc]Aqr;-fp*(@iOoNN5ceFMc+:?iS.G2LTp`B3G?XlhAc#/u$Jnj<A
P.6]A(W?,kBS3?H#6RA_It?U*4FA:ia&fS*nbWiVYPNSC@AJG_P9u$*qPg.FB'3A-i*=HOiOP
_NR?cWORV\6QS"8MlGpm4:.7^kuS#(":p\O/.E8\&<]Ai]A^ULCa<bQ+qg/J^VqN/0#]Ai*it;C
!@2nXi\I)?Q0fkdd'e4Re&quckXB3)WV?W*Ar3kW<*FX2MG@I_1P'-%mWn<is8Vj6nQ.\SLd
P[u+Uj3H+)GOEYrK+WO#\>r7igQb41iU0iu=!b.Cf9LYA=b\OHE!m;$l1_0:traP.>^D+Z\+
W028A]A&Hr'CM+cdCShtBMi!95biG`fL[pG/?+BH=4,:+8/kYjg&4p\b1Gp6sQn*QnKra'!u_
ijrtX'JWYjPG"2_DO9+Gp-]AgqOmagQISg4<4qDUX<>c[CGs[g9C%c,8)makE)$(iXL.<N^rp
@3E2l?l;sks/+Vd/oB!ZaHl2m=]AU*dqOqr[%=Huom[gUD/C?LT0PpRc=KpF&$:&)pY`@N_Pu
6eI!p7^,*AjhE"+_Q,<mY"S!G9G6Q>F<Su"Q:uo-::]A^YGb4p.>,Od4]A'=/R>A6Y"7UO5T%<
$U<(pjCqnd!m]AEL2K\$_&/A@>]A_:rIMN5Yh>T>kQ!U@Sl[.XqTNJN_ru'S,ES<iSq&h+r&]A2
mPJ!&FV\e)c%DAR`\o?A.+02W_*V+q.FL4NiP:#dPMCs2CJ"b[H4Fs\HV]A>Ftd7FSgZ1@).$
bS1k+FpQ&UjJplM1DVu00m?2"``jB-f@),LXtA<rkEC2Vi,Gdr+16+W\':=bd`l6=dePiEX4
kTbEien2jU!/WYF@-aX;"RO=mC0]A6AU&ZFq7djXo:_CB%6::L^f:l6>KKa-TA(p4<5"N*V4<
HfP'crXA~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="39" width="442" height="231"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="button0_c"/>
<Widget widgetName="report0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetScalingAttr compState="1"/>
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
<PreviewType PreviewType="5"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="391de5e8-2d5e-46d9-8c6b-43bed0ec2a0e"/>
</TemplateIdAttMark>
</Form>
