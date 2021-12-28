<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters/>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[dw]]></DatabaseName>
</Connection>
<Query>
<![CDATA[select 
case when LENGTH(QyAmount)=10 then CONCAT('0',QyAmount)
when LENGTH(QyAmount)=9 then CONCAT('0',QyAmount)
when LENGTH(QyAmount)=8 then CONCAT('00',QyAmount)
when LENGTH(QyAmount)=7 then CONCAT('000',QyAmount)
when LENGTH(QyAmount)=6 then CONCAT('0000',QyAmount)
when LENGTH(QyAmount)=5 then CONCAT('00000',QyAmount)
when LENGTH(QyAmount)=4 then CONCAT('000000',QyAmount)
when LENGTH(QyAmount)=3 then CONCAT('0000000',QyAmount)
when LENGTH(QyAmount)=2 then CONCAT('00000000',QyAmount)
when LENGTH(QyAmount)=1 then CONCAT('000000000',QyAmount)
else QyAmount end
from(
select 
cast(sum(ifnull(QyAmount,0)) as SIGNED INTEGER) as QyAmount 
from g_zy_erp_qyhk_month where year= year(current_date)
and BUName='鞍山公司'
) s]]></Query>
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
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var link='<link rel="stylesheet" type="text/css" href="../../help/css/dialogcss.css">';
$("head").append(link);
]]></Content>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[点击]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters/>
<TargetFrame>
<![CDATA[_dialog]]></TargetFrame>
<Features width="900" height="650"/>
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
<![CDATA[m9Fp=;caaLO-F<\MX=N1>)42se'?"D+BemTTl/7lh-6;p6Ccbi>CfCK,`q*;IP#`*B]Ag>*:W
9-+MNk*fOek<0FHl&)9gXC;AP(T"9gTt"ZB=7^2aJ%XaaGagST+ko+8A%VIC]A+-qtlrU46O9
Gmcp'ri./iL!!'jYQRuMO^]APtbIX<D>h7o?pq.;;\N8H6pr7m4BNq@Ot8H75KSE"BGf'tO4e
U/4-&O5kqpP+C&QD=85`JVXmai;[rieWl<k,9st7g#WaSD,i8RJ#)jDbi+/qK;KQ9=^2#3dA
'!qB]A<6q%?Q'A:o/Se#eN`>J$0ia"]AI!h'`@W[19dB]AZJ?q\KI3DqQHkSfp1:BO-AJ9Fn*-*
+EAn>rPopgLG*s"5tNgcq=s?^_\[*JYCTBIE6\t@(qM]ABSk[lS%Go[l3Pg]A5QMf`]A'YhrV\[
&8UFsXH%UFLm-S-)o".NK;]A[%H/]AV!;/2.8k$/]Apd)grHNACI^Z&Or7d++LC]A0,.:osZ#>0g
I^Sr-fX!"PU<5`9F>YU9G.C[\M>Ro%"RE4sol/ol>XR4u3IukYO7M?PfnE^BD,q,9:V;K3-[
:rh"1V'aWGrr;aXH/]Aa-u./%fW0m:.k9]AOIdh#+8=5$f#p)=O_@s'Z[i?pB\36.Ne2Gt)LJ$
Q`g9m(]AQM,9Es)70ph#H:%!QZRK22Gp-H&;"7Yf+4!=NBX-1tg"j,(I)<gq1@f2j^5aq'7hh
^jBQu.?.d.<Q8rA_k*EMjDP*9Sf4Bq&a`h4[rK7*P<F9dWg_+XQ:VG?8`>#!/LYeb))^j"06
IHLT-o`'DWB*(0O?]A=p?.4'/p:Ue/*$)cMkVWfQKpe:`PX(m&9C$fa0QYL8TNJ:K?i%A1[Ug
Bn_6JYEGIutN-+ed98e_^qVAW#XOY",F[Jf5'o&LLKm+QEqb3HG.FmP:iKj:2.YE*7?\_3l&
r%GnSpoWNoJVsV;s?`,2UY.+e3);BVhRt1Nj*'!)Y]A;]A[lUXnU8RC5-dF+#bj4<&X&F6c)6Z
C#0EoOS;ET3iHnPo\6OQLYY[f#EHR")W'Bh%.;)[e6_Lq;(:fad:VCAs#Tu&)O<0Z4>7b<uO
=Ga/V-j3F^4cWn!&-j5%WCF$iC?.eRbQcG"NhBE8k%VSN)N$apR[-#c<mDB1j4;m^5?tr@[P
CKcE0Bf%P=As>WX[F_Js,n+^sXDq5$R=2(`Y\<i>E5HS2CW:r$>,)FNAJOiNfD_S@C?m3^L1
e<2=Z\Se!ds,1-\k0GCDCdtqCA/+8jaAn9J[3oh07'9c%jKlZ,(#i-Ya4qb8pe$MVcX`>Tm?
jY0^HA&#kTk>:hn4n#&MN5dPWmn17K.Q6U^JbhBVBY0%Y]ACJgJ6>5QM<%IWf#5Q.bjp\`i]Ab
U?Tu/["kXrG>hT8Q[c3)&7p<+LL+u"FpD?a-g!IC%9JR=FWU03fkYT+./_tIg?eEIj%FdJdt
S90rWPP.h'4GI&d2p!kYZfStRJtIN276r`0MCSGL7co=<]AT[C!)N@urk8rB5BUK[1dqkfeX6
&/_)8ms5D3<$f(LYWq_SRNYEJp:\*8gZ'iAcL(iO^8p!Jb'\-L:6SbT7GlR_ASJjfhHRC]AeL
4TQfW2K]AH5;KR2k@e/P/[r(fGsQ-1:c5E)bf\Wf\.0GncGd(sd"o.n21Wd\OWEEJ;B#agV_i
2uW=g"s9fIe5$E<B;61%Q#SL1"pjLHGbOX_E%>-_YX2U2jD=FPqIhlH6Gq.Q\2W?;Q&:Kp_9
%sZ+^3c8f,2u'g:W<;;OE4cs:uQ@e*LEn9m+T7sU-0l*t'i<"_P[e-IZ44iS$0aQG\*&,WoH
4N?9_2'sTJUC-l\^P1g$Pi;;cDS$N#c7t!cfc/pjc9GoFXYUk1qtIKamd@S_PO&(V'gB$SOc
[#+QfJ;S2j!Tm*7Q_t:CIZlr9shtF59LYq\P[fX:g:91Ikh<om#d%nr08s<0_2..RdeT\Nu7
F$o,b[mcg2.l/jJ!:_ZoDF@1^mJqmtW?=3O5ek1Z>J#NthS.LOlgGQ_3h2JN4+>9td]A$BW!H
QF38o`Iu<+HP0MY!MJ$QpXZ30`HP9XeSD+E$VY_\9\P>Z'p!6fPd:QFUXb/lZW"_C:BXcJiE
G1=QeUS&GOBs%IOjY0+OjXKq"RFqs*5524,0Y\`j^i9&k4Wm9Gk<5`Qu(0kmZo`6Ik%f>.&\
8B)4OHlO!4*/FnBOuumtDAh'kI2VI+aF%G*_B=FNfTlLB>CnkC\c@9bL'4ht(.GKL["4oDI!
%,638_=kQ9i6eFq%CONLRg$h'g*.B(1so,>uP>PXUUZG!c_LRJJ^\q/@1kLgl?tj@Ub2Aur,
K=!Jjp(VrZm^RP?22P2#<3fePn3a4[e'33]A`eL2/9$JkaQ((`+f^)T>A9/Nbnr$r_b+dE??;
DGYY5NL70:!Z]A"q2]Am+%Q22,/?7-qjd)+pFh^Rg]Aq,58U@Gt&,elR0hYs%%b9At^-coP>.B>
+5-$/]ATgVj(55XK?/<QHBNLZ"f(o(un'HS7Wl<cWB)-pAO;PO\[\b%3c8[A@_lPKJC\$*/ma
ZESpTSc]A`"(<+;"#-uZh2;2&^He-J\\bcf2NoAI[pS"b9#45CliY#R#?$@9KNs:).k9X0DZ4
mfI/hCBk7<)LT%iL=#5/f#1djVjF]AIu!nq*ng2eAuVC_L:nq*W/XEX!Jqj8blt2_q0jBX@7.
6P?nTO:\)1.?Z,?lT@UL]A=U0uh<<o]AD/G-JuT:>ik\bRB`P&?W)^h$8/"I^COb97aR:XX-6k
I;#n(6Ck7nDaR7/(^:]A<,Ubl/qCEGSZ+@Xl>+rHo%r-RDah*XE:Ff@V92&dKMIqUKJc,'aWq
0/(.BK`Y[\qF5%LV3$.1/2.'4@2d"7*d#UQgH=!@)gNkljsB()Qn]AjnNc\ZaG&T-?H2T5fs.
>QOS`mg$uJY8,8#GhF7FOgLif6W#!6();AQ3hN.V%Z*n<@)eI2@5f`[7C;2[APfu2PbPCTkD
$]ALb<j0sg0KfA*.R&5.QEW!)u&:COjE1P@:+3)p5N%&N]A??!!TQBC5n-Bl8#t(.3b<$nEY;:
6YLL4aC&`q\.7m*"7/A[l.]AIn>8XpB3rV+E">U\+^)_dE\P7"F=Y+Y[dIAJ*P;"ZZ9jaPQG*
R1HNIBj2MDc]A1cD8jWW4r8_fU@/7#Bc;2Y@,Z0d&lK1W?[C5AXF%sJjBYFAUIj!FdU"T+%SE
*IeE_fg4*B(e(g=:W$op(M7K#Cg/Rh!9!q%F.?XLV#(V%AEj9`[[Vk)_cbi]Aulkm`$ClC<3<
.L_N8_Ghe]Ad3pF_^"1P[MeEW+hi\CjfOLk@*9II41$AX_-qTV@j3GT(`db*iBf3L,*.Orb:E
R[c@Vm+0UZ5\D^cPP9+K\#VH^/:5+B&U(m:N2%Xg.0COgMLN)@"SU?Ig2(Kt`'TJ^2.%I/)k
kHcSG*)K74,?[MgsPHu&2?MkZik2V#R#="%MNY[`,fPq\03B4_"BVGSR9>9S?/s?r4Ij,YSC
'R=!f=^OQCi<5E#P:'e<ic49JRJ0GkD(]A4p;i"2+3K@_<Yo.6i96iY>+f"JGb.JX)aeZqL-n
s#pKn&+Y<t,:PJN)TiFW>tHOH%)c9mE]ApCJD#L\OdU^V9a_X(p-pD35Pi5:l.b.$6h*8R!L5
/Rmj&.@IXaC?f&;Q/OpjUq_EN/\+Vdf>hjc'Lp5eB2,n![WeDLSiX*bJiK6k:\NXB_tDATfs
&b.K70dY$AAEsP.o9bE9pZhMr:YK,!`Gm7VN`3ru8mJ=OLH;.WIuD(T%Fc9n`C?2`;*C>n:+
VUJ_R,OQB]AU82Aea/c7>N8MIc%$fK5JfG3$ClQ9Z0ZX4b9;\$jOAWb>QGJoXi]A6KEV#?g[;i
k$h^iF%bg-sPi]A#q`lp$N/tg$5!3)(2KC-i0"-8^ZA*h/j?gbe*_\f~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
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
<![CDATA[点击]]></O>
<PrivilegeControl/>
<NameJavaScriptGroup>
<NameJavaScript name="网络报表1">
<JavaScript class="com.fr.js.ReportletHyperlink">
<JavaScript class="com.fr.js.ReportletHyperlink">
<Parameters/>
<TargetFrame>
<![CDATA[_dialog]]></TargetFrame>
<Features width="900" height="650"/>
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
<![CDATA[m9>!@PLgPa+Te3"d8m?)0m9qip,L!O=U\Y/<u!eDW9m7`,uAI/8qAJLJWSMJ)tn9'mTHA<+,
S^/8g3ROA->6a=pqH!U4>Nr^knLF9N'+Oq0:e@0,CrQ\Z_3pCGuZO_#&(hhK.#V^N7]AhS9+;
q\qISDl#Wa)!!8`rZZ6-Jds(k;m=*+]As3F%?G6IsXp[iMWT6[-'8uGj^q8>hlT?m3W#"fB0'
%aSk/E>[.K239(++<50r90Qr9,J'6\%EN^n13\<^5nk#bNAWN^%gYEA[Re3XW>1&$nP2=nN#
6E5,S4faEm&QiG;)KC&?Sp<OX#5*)'FCe?O0XWpT3U4^rc<]A^@l!'+`-b9*($bmTij1]A2@%(
Lq2^"6bK4He.Kq%kP*hYo3kVXcfbG:Nng$anZ]Ak!.bNu1=FA^e<XB;@_o_eAJNo-A=2<(9St
]As,&%.4h8>-)r2j1TuS&-mI)A'Z$0\OW+CF"1F=a;*nij'JtFp^U/m]AO1-7`*UG^<s.;h-&<
mY'.I*qCEDp&Jr(o;"?l$2+g?IQ1\fdPd@0j>jcGpk'<S@mg6Do;]A>ZaW=\T!1dK0RFr^,9H
qh?m<S")9g!A50Okq&?1qW)FfT#K_.Fs8FE5>D=F-O,4p9D,CND\FpAVUYEGdF^fiKiYpV1M
*mq8:X">h[T=XJcTFC5et"jEPq\M\;@blGm76k-Y%\FVb#32f`YFOfkecUVYF;'bKU43Vl_?
WVpYU-aI6Uk'iepmaT,2-(.5(=::J8C#+Ti,.24kCecH8e.,0W(sfEq'E@m<d70mrH$X(Yj9
YALI^[M9A\OYaJk2rHI-:9>D8*ucp_&>kis/N6VeHRI7W++#+4$kJIut:KesDucZc1/Z&VRO
_]A1JH.U$IeZNM6hModX2ae*&a#Yd<+4[/-P\Z;LN:ju$Muc`YQOga!fi6-\bfRB.?m[!J@\%
jG`>?.ded+!NXFeU[,(AUh_CGC^315/m4j;YXdq-)CDFA?B*l(!AW%E"$1OWpD@,)ZS@cepu
k6n<^T2a$3Yes5k6odI)W>kX/Z&c]AH/0f-MUao'L<k4+f_P`uZ8\D*71Ud9th`8^R&kk&Y"7
MrgK6?Ng32C?uOa?HlcCa#A6/a^SZGOco!2]A,7=A6Q^kF8uH0tDAZ#7Cj[I:Z^$keEFGbQZF
#K#?5qe12l*1*hA/K!/@m/\0)UfXiR.aG+u!Is.W]AQ41?;)YWZCrmM@a)qS!PIP>Ds<r]A&00
>I0)]Ao$Ec/5+\UWa3a.FGCg6>e1_a`Intlh0%ku8Jd8pgKek;=LU<Yf,!V0"H&50T5/`I:d=
WDO_V/n.PT(H[6R'RWPY-+\I'Z=rqbT7(b44Mn&*]Ab+Kc#8e&"&t`^!kfeDXLStN8sr\bdRf
'd^Hg5cYXgcR(`%d"?p^)I,PA$T2(\kP[RWQ#]Ar"PU^+V>Z<0q9Wbu>E=#]A_\%7RdEsW)+B:
mH44@)3s"AIJ'<)dSuB$H%i%K4aUjU0!@/!)@%`=6K4i@.EYsU2Z0u,@N/@+$7&i+?*OM>Ye
QAYau[p^h=):6dn@a5)VI83e=dT%=pbfm'^V?nJE]AO3Zqs9#3&c'>Eb&jf'Q[a=9[%Ak>(k-
<_bX$oC;9Hr*)AmN&'Z#c;6hcI>O24H[d)j/,O1p,0P$QG>LHnPgPR6<%.rR`r#!='C?:jAN
g[L,[1WhKV;rL/_3;--D)d7$Uf0Z%JVD5cdheZ(HS>?r>ka`B5CYl3L2Q>bnC&o+8GFK.gDs
p[EpT'C2YOp*'o)PfVqiPFHUR9KYj+=?6-'qr_$aL;h.!T.%iF`op_&QZI/VW;DN_WUTC>_[
QhfN.rnYo1bb&j1Dn4)aE,%Qho-[N.GF^t$:.br9:g/T0f5hgI2Z)W^*+,ko0f?^,m5Wo:qY
/?2HW:8fbl2j.\,4PtbLPdZDe6dS*sp`Q(5KC@nq<^Fi*`'lp2pD`K[L>$GmT3NppcpmRd?T
m5Ss(L#3d,Z:]A3ML0iX8A9<n7u^1AEDFdp+ql_e"::V40]A5_cQNq+#H9eKlnCXA?LBP=FMA#
4I-G7qJ:*Qn7,Li`;&oDK%TeXhE<aMOF4BI48Zoqi=urrsUZGX%<5K^uGAad<bi681uG@Ats
"'e:"gb?0)V:Vb\&7hZ)`<U2bCLJK:tYPP2S;B1+o-0o"jA:Z$(5O^)brVu$a-^)i=ZAe"[O
p[Zk[=c4H7lKpC9#smVrpS4-&hXYuRO8&GEn+c[.E?CgJJFf161WU0F9a&$@HF@0f(f=P*^4
9#%GmQAi\-O!1XouT.mdca[?o^UeXP>;^!$tH?k-0QNIpF/$/^*-/DYs6G]As__8PdbP/+umh
,OeLk!mt&BD.AR8Cq8oZ@"B30NU:*pA,=/gF?jqAC'gn1C6GL7F&0dq[9Ci`(T=_l*<ii-ET
@jQ"I.tgh@KjW&]Ahn8Xp!:<+-9=&>HW:m7ESD&933#=6.IYf\MVu^Q.3g+Q*ff3,M,sq4]A&d
Uu>)HXU"GIE%f%HSZ]A^V\h;3=3>5m^HJ.<CqPV<t[*=XuhM"7Ki1P]A3Ximj(ZBGJ)Euf+Z\#
P-Pb\L`JWX[oVt45XK@bO!#CJrtYOY6*Ds1GOP>QR$JWS>ndg/pX&[W\5WQShM"42c:ER,<@
6uaW8M]AP_?r&VTF=4nK$cK4GcF-0CB=9t]A?_\/XRblW#E[R+!<#W>X93=@B__XgNu#L5k5FR
d_F-B\&DMdRcuRBl_p%J^8lMpjilpLia:_]A1(9a5(.#0=Ir-Z$/\9/AEh/W8nMJD:<7le5UF
9t`YeQ>=^.fBlskG\gB8hKB:LrnX!<H.u^,5n<s2tQX5-+)9[g.E"9%5'6u9-_Qrl5r8lIu:
U-=b;Ah'J)BJ`AT8NTjD"<09%83L9!uZbUZm*l(AN4j`MIe8)^:@/'-rpqm;Ak67F.e%N^I!
l!t6S##&RYa,7biXY@_=pnAO13jn=UM<aW\FZaf;gPRP.R%^l(W>D@rSJ?*SYk(Z=,&Y&(2]A
j9Gs(DlN$&NJ+]AOguV&(Ru9?6s`Y18pIWMo\(e/d%6NJ1)JQK)1(sH2HMWDc>fgRWLUeNBnb
DD:ZJtBjc&6hIm!HQqhVpV8R^&8H\#/l%!VtT;3odk/M3_SLhMc]A3PDWfS3n_'jdm$_31;E6
rK.*S(Hp/(%$CE-?'S6OZZZ!V6(rZ0qtVSedrChL'Y1_:&J5$H\;EO.Vc&+p;c^m8ePbfL_3
;2]ALYjoZGQDH4HPVZ")Ftk&VguBCP33"3V(ZbmoJL(?U7-q(2"4ObsfKU+qdkBI^p#(N":W/
;hUF8Un#`A&m!iLLl`g7]AMHJ@<4H9a[D_N>'$NDpeZPAB+X7FT/6G0a`N`DJU$9U^8I.I*dR
fZo(&%t?$NFc]A_.HdQSdA;R7H'Hp*>APEQ63R*X$+c&ZCERt;nAEj=L>=_nEAB"7\3<K^R#6
s8B5X8]AFNmKIWHV^b7A,#&<n<lJ9I%]Ag3]ADJp`:_Jb;WoXkBeELK%jm&Zf\9?VEEgmqqVu06
Rh'0K(Ya2fg*n)n5^'AAE*su_+dYeoEAe$-gk3Jk(=r(qk@A8pMQ=9"VS.oJ<8^r[eL1q3ku
U@9-Z-%nRsMX3b2;Z0K5'Nejo:k=M`K77NO@)Fn@ZndC#khc.R&h9:@M7Kl@5:p>j=,SC(n1
<d?P"ZMu54o@c,r!+cZdD4sb3T2YM@g(-KP2"=/7o83P3GRIY<;XKPWAE,-$a5thDT'5)eMn
@48a%\Sq8]Aj4cUD0pdHi87"60gKpJ>"S]A#eiaFXC!^GeatPK\MUD/$AcO#k8_Ed8f9ucU3p9
`?cL8WON6;)IuqN?0FSeS_P"EuH83<YL$6@rO3ROkY6L6#>h,j2$j"Zim&a<pBG-$<gg6KN/
\b0@f*?('lo8_F+RCRbIo_YBZdT3D%I,U&:Z]AU6oAP((6$JGWRg&7Kj=3qAlIU<U`.&aQOML
fJ?<tW(?Gcd<cZ?-"<]A9k6mEL+_~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false"/>
</body>
</InnerWidget>
<BoundsAttr x="147" y="59" width="250" height="150"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
</MobileWidgetList>
<WidgetScalingAttr compState="1"/>
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
<PreviewType PreviewType="5"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="da53eca5-e983-4657-8fb3-c609be31ac24"/>
</TemplateIdAttMark>
</Form>
