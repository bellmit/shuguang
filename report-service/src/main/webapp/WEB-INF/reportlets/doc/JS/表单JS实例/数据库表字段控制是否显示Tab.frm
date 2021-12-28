<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="true" allowDoubleClickOrZoom="true"/>
</FormMobileAttr>
<Parameters/>
<Layout class="com.fr.form.ui.container.WBorderLayout">
<WidgetName name="form"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="form" frozen="false"/>
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
<MobileBookMark useBookMark="true" bookMarkName="body" frozen="false"/>
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
<Parameters>
<Parameter>
<Attributes name="a"/>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=split(SQL("FRDemo","SELECT TYPE FROM SEASON WHERE SITUATION='隐藏'",1),",")]]></Attributes>
</O>
</Parameter>
</Parameters>
<Content>
<![CDATA[for(var i = 0; i < a.length; i++) { 
    for(var j=0;j< a[i]A.length;j++){ 
    this.options.form.getWidgetByName("tabpane0").setTabVisible(a[i]A[j]A, false);
    }
} ]]></Content>
</JavaScript>
</Listener>
<WidgetName name="body"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<MobileBookMark useBookMark="true" bookMarkName="tablayout0" frozen="false"/>
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
<ShowBookmarks showBookmarks="false"/>
<NorthAttr size="36"/>
<North class="com.fr.form.ui.container.cardlayout.WCardTitleLayout">
<WidgetName name="_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<EastAttr size="25"/>
<East class="com.fr.form.ui.CardAddButton">
<WidgetName name="Add_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<AddTagAttr layoutName="cardlayout0"/>
</East>
<Center class="com.fr.form.ui.container.cardlayout.WCardTagLayout">
<WidgetName name="tabpane0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<LCAttr vgap="0" hgap="1" compInterval="0"/>
<Widget class="com.fr.form.ui.CardSwitchButton">
<WidgetName name="7e6f1903-4d03-4c9d-987f-0ebf167095fe_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[春]]></Text>
<SwitchTagAttr layoutName="cardlayout0_c"/>
</Widget>
<Widget class="com.fr.form.ui.CardSwitchButton">
<WidgetName name="c322c06c-88c8-4edb-b2ea-57061b0db158_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[夏]]></Text>
<SwitchTagAttr layoutName="cardlayout0_c" index="1"/>
</Widget>
<Widget class="com.fr.form.ui.CardSwitchButton">
<WidgetName name="a54cf125-a670-41a7-b175-e8a5054c0640_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[秋]]></Text>
<SwitchTagAttr layoutName="cardlayout0_c" index="2"/>
</Widget>
<Widget class="com.fr.form.ui.CardSwitchButton">
<WidgetName name="faab7789-f148-4f6a-a497-ef8dc8e1f16d_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[冬]]></Text>
<SwitchTagAttr layoutName="cardlayout0_c" index="3"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
<DisplayPosition type="0"/>
<FLAttr alignment="0"/>
<ColumnWidth defaultValue="80">
<![CDATA[80,80,80,80,80,80,80,80,80,80,80]]></ColumnWidth>
<FRFont name="SimSun" style="0" size="72"/>
<TextDirection type="0"/>
<TemplateStyle class="com.fr.general.cardtag.DefaultTemplateStyle"/>
<MobileTemplateStyle class="com.fr.general.cardtag.mobile.DefaultMobileTemplateStyle">
<initialColor color="-13072146"/>
<tabFontConfig selectFontColor="-16777216">
<FRFont name="宋体" style="0" size="72"/>
</tabFontConfig>
<custom custom="false"/>
</MobileTemplateStyle>
</Center>
<CardTitleLayout layoutName="cardlayout0_c"/>
</North>
<Center class="com.fr.form.ui.container.WCardLayout">
<WidgetName name="cardlayout0_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<WidgetName name="春"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<WidgetName name="report1_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="report1_c" frozen="false"/>
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
<WidgetName name="report1_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<![CDATA[1333500,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[12687300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[拥抱春天]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O>
<![CDATA[当我得知你怀抱了春天]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="1">
<O>
<![CDATA[我为你感到欣喜]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="1">
<O>
<![CDATA[为自己感到凄惶]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" s="1">
<O>
<![CDATA[我虽感受到春的气息]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="1">
<O>
<![CDATA[但触摸不到它的胎动]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6" s="1">
<O>
<![CDATA[因为这个春天是不属于我的]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7" s="1">
<O>
<![CDATA[我没有跟上时代的步伐]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="8" s="1">
<O>
<![CDATA[要么太超前了]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="9" s="1">
<O>
<![CDATA[要么有一丝滞后]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="10" s="1">
<O>
<![CDATA[我仿佛听见千米外的喝彩]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="11" s="1">
<O>
<![CDATA[我也加入了喝彩的行列]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="12" s="1">
<O>
<![CDATA[在喝彩的同时我为自己加油]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="13" s="1">
<O>
<![CDATA[我相信来年还会有春天]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="14" s="1">
<O>
<![CDATA[并相信那个春天是属于我的]]></O>
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
<FRFont name="微软雅黑" style="0" size="120" foreground="-13395610"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="微软雅黑" style="0" size="80" foreground="-13395610"/>
<Background name="ColorBackground" color="-1"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[[CUQa;qn`E]AYL2%8<n]At0qief!Mp`m,Tn5'G7bB8+rGVb&eRrr+@QtU%3cpJNc_+R8.WC';*
[0?V16=0FRO;=\*rdUq`H6UXd-<VRU_BRNd&-Gc5CV)=-F\Q&e"a?4US0K"op+S!)T05*iAi
[^)Hk\!7!esW?Vh"T?a@Uj]AOL^^;Q[R^@crO*lnop>@*]AQ]APuVq:++nGPa_J-)r9U<C@3YPm
._X+pdObeo62$!(FFe'k.b%0$N3*Wi:EesKds2p[R7K5p33A\^AT2BDq0FN9OU&/Eo!It<]AB
bfpUBn-fP"D9s!]AboJKeL/Fm:[AchiM+GU>U*N:tn@(`Z+]A,X@>c`RA:)QU'1npHF]A5pGOS*
mpF#jF,V@LDpNq$^VH(r%n$'ZT;)3\]AY]A:0/%^>0D6ZOfo>4Hi'80-8CVGYZ1a0UVhLQ7ki*
YGW"]A%LQZ0J4Yg=QTl(P;6</W6`o(gZJX3BT//Y#&arLb"=]AiPm&P+eGb*4B31qV7^dD^d`M
p-R8;/Fjm)sXM+5`3Ak`4EA;PHm&JE.He&,GhQR!5r0b7e0d05TJ(;jL/""Wr"fVKP.hs:Z"
T_\<462DFVu`m%WM^=^.c>OlVHk2Q!(T8s3V"9ni9o%BZciR.i1PL*^Q#,7Q82/R>+c3[B&5
`Gq)!9&^V=>7UoH?B7sY,o*P]AjiM;=$9QC,4IS!&[n;#KhWYS@/*UVa1F,IT]AmQaSgGIr+Qm
C.Q_"+HkKkq-ba&=i;,X%m(T_X2q^G=gc(rY;6h60:#VbHFa7(cW;(0A_dF!RP`8laj<8`pj
Fu]AQtTF?FUUf(!Lac/P<>TV,%#fWj/+qm,6=Z'YS98HVD4AK'u\'X+FP?4%SXun*0]A"e@^H\
o;R_?,lR$J"c,fYe$jfJ\JXsm!Y:$BE)1/&6_@H-`$[&pe<#:R($29i<VcL[t(A;goq49kYe
!OuDM$nP.Fr5djm)%,4_LuHh:D\-f2dS7cVZSrUV&&m<peW@H/a7rp+jl6_SV<^q>B^=4pfP
8'+;/d5Kfa@%a0h9]AP[a51"F,>]AXH(?gOV&G$T.HZnVa-0k\R3s.+8iBi[_A_Pd:Rf[mjQP&
'^HVE'=VF9;pC[nk5l1>qj)(G0&s'Or,?'P$fneR(TEp`XLWgQ?Uf+s#k/$+;tAPD:B5%0qI
@h:Nd[,[fXA^M4NY;t)OR6s+l!"qWisQR,8L96N+!s@i:'4V"!D:C28W<-@r3i<qIGmWJ!Q:
TFHAaVg$mZF&p/%,BOfHa0,b?]AJXj7J+`\<tk2iR#k)FdX>.W6l2`<DFXE+n'VuVL`e.C3X\
J^S2Mmo@V2aYZC>kOV57K3Qo&q_>F=UCZkXr16@<LJ\Eg-X(c>jJHo#<+5_Wq\D3TZW1L&-i
j1Upe>Sc#LLRP.)3Z[;Uh7J&$X+\8I-m1eXcm^B<*LBg>_/q6Tkt',]A0F"K>JS#BjQ,(:RbB
XM3I>:A*YrA'C`''-@4Qq6iMbAH>-VMGoj,FMM+XrrD$XBO8=*'XG2gbMMfS"G.7>`E;UoJ#
>@BB([EE,R$?M0Mp>/,;(sd07IB_MHoC?[=<A^3-Y1F!H'JNSP)m3,Pr4]AlFB+Eer/Y'qLR1
rX)_8<H)?=hPYMVbRkP?CP<d7OU3Qo3^_23:B-m2N57ZUfN0m0`,^+jG*IA&lM\Vt;/G7GVi
XL14.\_k*phg>9GKl(b4mpgJiH.DYmIO&oN3?rH;uZA.\\bgb?5C+6Nh_keRUV9YpFbBHoC-
Xo$oj_>%??qIY/E:AIg-?%+1Ph3b@.EQ;>g+#CR+Y,93.:6a0VtT.;++b'uF7UBi!)4W#Ti_
&d;<'@D_&9jC!nH1%<>U/$IVQB:%WFc18CBP=*Le/Oe+C5NE^"7lrCS)ma<gcT>88:[gEiA`
sc3%<$s"b[I?0R`K8;S*4EHNJ2[qVsF1u:sfApO#KHQ:qpWT4_=1IDo75I?EKPsj(4;"76o8
4rS&Oa+6E9p'o`dHPJiep\**Qc^-(7^?s2;[Y0gOV%c.`#9Xe3\/i)8<26Gs\C#O?j[L#Skj
.]Ae*VBka'=qqd2)Bl;:0BP(e#Y!\K6sR0BjdVk1`ET[^AgFin4\r?M(c"sVEA$`+fD5UQ.4:
_]AA.KI2gZ.7/qacgsPN=rBdJ)GjBq?Oo!2i8$cH4_.AAXFa_Q$n)2&gQ]Ar,Df%7.,C<U4&%i
i@@:V7\gTBGQ<uM7+u-R6L6IE41+$k)u9#'O9jFab-dFi&F+s7r\1g.-1PK]AaATu7hsJJt)!
d#CNl(LoKO:&Wb-Lq[>8;33*?7#7jAC5i#[Z#m>09DfItcfUDR#<4'XXt#8sp,pggBJO4&H_
mZ_G\#Wrta(=B8GO8\DV"97%)/L*;UGFEOR.@sX(/#/6YIOA%9h*$GmY/q"#:6Ln.@-&'"]Aa
DmhX$d/dPCu&=)jsjcLU7Bq=Q)g03HlQ/uc"HM,SbgB>4L!7[m3,J\^(D;nnHj44>C/-(HrU
gXYq;H(c)4aT*r4_GlO%6fl8aRRRS!"m>"*C;F?3KsBU#]AmgUFj1=6>76CN_r]A$jfC:fcfnb
'h9<rSE)>(-%/Ors'fLX!S^m1Z$p=mn(dk(@PRUC!=).TaA3h;cDJUjQ9_@K1?ZaDDdN.24b
,K#@9gjFL_?:V$V;O?cR"q;Gb3L`-Q6/p<LqITf<qq%flf\Q,^m0U;49XFE:;%JQ^:LG$*qd
`-JAq]A:=Y!4\'e-@5js22!h:5V_JOR)r<j:/&k"3pJ'D_RmCs6"/Fe^EVK@)s.'qPQdrn4Mq
5l#*6on_7_XqHmi[Cs(%7t)&htYQFio&"/m(D%W'5N+0lA8ZnSf/E3`!j[LWjhQ+Ud6CiPA;
tN4L3r]A9if#SPq1:9kh`esO<t<kob8@(bGlOG"=VF>`q#?t,fL0Ugh2b2hZ1!1.q,IfF3@cM
X)G!jaP8pkcD&?`!gDU8kUkmGStnEgPITYdHXiJD,-?cp$mrOT>%o:s^@YQ%,O,c"=3MDL1P
9hIo?C`PibG`c28Sr\N[5e4cqJ2mb^EeoQEt0QbQKs(]A!8uaqfB0"O?#epZco#g0nNuSd#-X
Apt2ftkgU3_$tnh/!s?\71KAq%[CXUfOnE_!H)4S^l;#j\kb5rX+8SA3=@!%m!V).?P'PQNc
laZeJI(5A_X`+>j]AB`he=GAm[Z1hB.fjFDoj&.A?EE<Eh+$S:+8?NJGXKdt_V/AmOQWL?*+u
?sS&TZ#1YCg$NCjIn'_A*r.ooJgoC^)9EcUPKkR_qP!dLb>92]A8d\N]Ab=a=:6Sf[-nN$I;`D
mGdJ1I!#:6?R/+<2b5kP)sNr`ddb[T.9?Vg2,4B]A6f@&RA%\G4bnTZEAS2KY[]A\kBAq4fe(n
sVm,-_[5UZ,kZ0%N=j[EiLV\&H<j=do!`9AUKS%/7MbNZNFi,\NLk&Kg1%nSU'&Oi5(&KI^A
M9!@u:lLTCr;07/1JanKg3`LbcZja`r-GU1H4Dl^28a\JHC6CiIc@6>?%X*H+_X`NtXuCbY8
4TSH1n7pUJT(!W/+\s7]A5tagVOF2(4P/`o\k#J2&R`a<M9'*S1DT)fh-8?-_]ALp^(a1!n+g3
+OOR2>4_*[o@hV'_"OXCtk@3EJM_lPeis-i6%Bc*#65[1N,Ged>YO\,,fO)?q1eAX=[P,B(Y
B)Q2GESLEg:)*l.-I@-Uhs,qY`QBn9>'0Q0)_;-%_X\%IRM1:@=9Ci\o4'XG<H`]A']A9hJUMF
n,Y_:Mi2S#4W9A=puDdO`"G$<^M3DD<@&Q<tj)2KQ0W*ap6\VcO#KADPT@G'Kj@DUjT"n!pl
t=]Aopll.96'oSFKNAN+eg"G%V?(h8MPP,NipWi\%P2oaP:;=:4`-u2#H8&:B!TM3cY2(r9eT
S`0.11$5^<HD`DCn0BE/C.qnFhfj6eTE/J^mCOId-aDkLSE8-2BQCd"njfJPAE;2es?9?!Cm
VA%rPo(,@/&uB]A`aAN+Ge6QKlS>hG`3`#-HurU4)E_;XCnLVs_"#let)C]A;FI0'8'`u*la"S
k7On2GBbFlr2LslK>(EqW(&D-&t=?_A9'LJU/I4<E!-FHN;cTmV#>m%C]A1&6UEeu7oZ1o]A9d
[/hj5h$#1"[IB1Db$d?Ri<rf\k;j<bF0B[S2E:e;g7I=:"?-:?&^Bo7#hD$LEVc$Hj1A8tN\
=(2rm*n6U+qMc(L?XJiD@q><N=oO4mAp#/`J$i'EXI$i0k7t'!a4g<Cjkc0KW(=tEj>\m>1/
C<P<l/7]A(M9a7Q(]ATt24g/4<p2U6/Do6+gVR_hu5/PrJ]AVANOB);q\lAsf4f]A09qTDInF$N=
&8G\]AQ&^3G.7VcJCtZoI6hRe#gpa)!A#LkK8H_P$-8i%0;*p#7FRMYReAF1l-#8M45J&_PWF
e1:DuJNR7tVBHR[Ud:N%+[%MBVsi-J.@`$Q?2U"YbM;V-=8n`$3K/2#5#GCH3]A3FK--03YSB
!u.PiZc\"h]A6XS2W5I^N)\Rn01J*pP5C!c#cs)loHFtN"IiL;N0"CW<Q/+7GHc^;nK]A.;X*P
0!r3Y4e@n8VBLc<I'.6uWWMapl.1IJ0%ubhsTG_QI]A:"b>/*E+bfIYIr:^*bVY,uXtP`\('D
Q)S\mY[6PY,,$Q_)T+6WF\7IgQ<OSJr5P$Wd;S=q%"fN"*b1YULT/k\74l"Bh$cjAYkc7s4:
YckBmil;dnWr[2+iW84sSMU+\V11>DB[Jpa_B=gJ-($[f#d+*`907%L5VX1IGY;(BPpR"!`2
kUu=^mH_p]A'/Fn2gDD`A2qEnQ.F&7-LYd@W`;(gNbU#-1moL#EO?=URLD/0QHD;4cXF'a?Ec
ISK03#@Mb0#e"QJYDR#KHj2qo*%'6m?lo(4$<A7/Wjn_l1_Xcqb9X.uG5[>.RKsOroU81Fk"
0.;j%T*U[n[b*=@FQmVJrbG!l4.kX3!'5)*scmqk$%*[5afg+"q'QAhbr4V`?W-\lH`C_j(r
epD\A0La(EH%"u*hog[Ij[DVPc;%!3Muh7<99Y(:eP*E;#Jk&N]AP/;dU<=L:.+6CUJ-=b)CO
$=)GPj.jYE1RgY0bTVha,aQF%7X\@ktdHe9ZJDXCLO=[Q4RXZW2hdb1ICr>jciSr7idSPln`
iVWDUR0ZHnoaU&eFKZS3=MC>W`dV%Mr:j.LL9:#Hd!9tB#s.^Vci>kr^-N+&\t#L.@_5$DDu
I(]A2SK6rc4Tp?ou@1/ok)jr$6t3dDS.$Z_2dBVc/VL"%,3aWDk"m'(mJeU=Yb&4leHJ-!t]AF
(M(dmKC4B1oC#HLG)4ukM.f.)m5:h\Nc0oBA4IKg&eG;%)X1:RR^>G6Bd_.MNTdXQU>c(s'*
cOQ/M84(Vk`!2NN^-=s>W2S^X`+J<LV1<"?)24aGNS#Rm)!hE!i`OX>11&':<hl3gS>RMQ*Y
;i$\4m6IgC]A?7TtJkePq='hU2s(ikQt>a!$pNjs="Ig%[Tl'aQ6?b37i!\de6V5qK(T#_)28
AA*U""CiBhA`Wiq5=$U#/lDii3/[*co-nR+G#JpF,siG5h1J"*\J+hc4e]AKWdrSWmQ,kn5=_
C$[D=Ut(#?d3Bs,EbYfk)G1S"J:]A^m0d$-OPWgn:hcj*IdY3>83Z>Dike1d4NBf<h/l[oMf7
MlGkH\YYNXmm@7ZAf[[*=7b]Aeg+D%_=C3Uki$K4d24YT@6KL:RSlQ\5lOITg$mn28D*p(L(W
8g<#(J[:Nc-S4SABm"$^58/M#K0Q*Yofk(Oo[MI!u_#A8X^a[T>h_$RX-OUjQ6m,VX=I2g_?
)EMK&:/c<g>'BQT[0*A&\_quJdgVFW1#1%EWF</N`NW/kTQ:a\^`[qJhX\chJ*X?r/:f+Sg+
=a+Glg]AkWiBKt'F,l\mietQot.,-!Zqjk\R.k+1;J3g\Ge*63:j%5mm'UBq:!WD,7^TTjrPE
$Zbh>D'A\RLV=?E@a&Qgcba/pT8YZ9'-DIE62t5A*]ArU3fFWkkibkDL!OgZM)PZ)G[Ns$I&u
@:YIHr<K^WW2"gb_Jn,[JN[%ZPCm^D:kAje44qD?mplT$)cXB$HHA?G39pEfRVt\l@4#5`c*
sj12p*ND0*q0D.m(06CjM#;41N/B*5@&XT>6_sI?:K`M#pa_rD/PE=(c=Xc7d^V$ijd5IL"Z
c13(pIY,$nP9@;Zd:Si2XBq``(<;n$buFbD60&VOW%Q)smIR;DtFi\.#Ra4Emcks$(aF)M.l
SH23]AiD*j7%L%d^<[QR$2;(K>lVmQ`$?'!`_n=i56Y,Y9CBYLZ=u$9[XE*<<8gXeTC^-%:(X
[Nr1Dm6Xm(JlU'iWtc`^g^/obJ/#",Z9M;]AlG[JgsO@rX6VAi'Dqc0O'egA2)"7M;hsCE!VM
d8E!741B+;ij:h2l0/5lY[5baJ[<YZnqdi^-&hDp.Jnh"<W>tA7>X,tV1:I?spJ/KEj+slo'
-WkUIH=_6Y-ghYNaU<>7=]A0$PWBf9n7f^.WPr(%k>1UNDTMRU;952tQt3I`?6?d@#=dgE0i$
Q,Mk4,7,k7MRe\HYaVsl:]AMelL"92M/"Ut#uml\]A\s2+uUr"0$4m2%nZuO"So$OtNb7-%;,_
X^3kQ,rZ5o2$XN<.r_*gk>PIkY+d`Cb,YF8T'OiPV;c,4F>T:2DjkpDnE;7W")_-0^GH?Fe\
6jPU>(dQB>I@48g9QR:9Ud2Z!:q7.lDdRJMQ8Od*<pBL^u+Ic<$gV=gf=L_+Cqs&T:9A2DFT
NV/[W^;`"c0Fe#.[f$b(i`G=)0YS(G&%S,8Sc7tBho#n8kr.3T<Q>k8NZ-h=39:/pDh/,Xjl
);XP\($m2hfrtEGqs/$KgiO%Y]A]A!jK7Pt:']AHEp/?Esm6Vj$C+Xmf`*bi3on32Cu!@=gU(7p
bHdpS[%p/mM9_5^meom&:i@%RIT!>dYFFDucYINJ3F;G9Z9`LbVB"&L^NjOeu/gn@kGYqMc#
KoZ,ao`R&anQ)8E]AN]Ael[d:O6]A=iL\?4^2Te*H#&NgJDQUfj'j:>fK[_pG6*@E.G=2B;AR(.
.*Sas^B0?stJc#6A$.T*M;"Q;Eab[LPfE]A+8a_Rr>3Y"mOqT^F3eHIGX19Y2VaH9E3/:b,7j
@[9bNTKY63f8AH@emfUm$#8I?SKh54LKF9Jeba2D3-&A^^+f@I_ge)Z">4N(DEcE23\BbkbJ
u8EnZKOkO'0]AWO7bC3C_4>1<"6j%jRZ[qF$/Dl4[hE-[#%^n&:5`LgQMAr42:[\jB>QHUjHb
C=VSV!=*rj.>haCZL.bGmI]A&?tWT,?#ER[[#[Yc$d:4@>oojU-^,WM!o:A==q>Q@s<?hd_[S
J"<-!bicemS"hl\<:8R)XPk+$5R@7.nRb-+MO'_Q<Hm(&r';+3h3HReW$H);?R8))+X7h0;E
Jn:a81FJDPO^X_P[eh^(M:1s,]A(S";irb*Pr^1kNUQ0/ppZ`R1bG-9YZ!]A"F9(lhuj6E]ArMm
eY"[G5+ROEe?+/t2-QBq<IkrT/?)(Tjr5!EAmYO9O[t6_XjUaSaP0J(ng6_*:eI.RF&i*1Cf
1^)P9cIk'j`Sfq;Un0oFbp^B%bA/lD5,DD6n\#`-bZjmCT++WP;I9K&ceM$)WhS<^eUF8>?[
C>d6,t_F]A2r^+7h\r4hY<#3Qn8B\t'UQ19Vp;4_+E&KIiiRVoh0IK:=n)NY+ICq@%_=ocu**
0*7AIr/d@+s6Rt2,9b:<CrTZ?*#ajS+>FAG5"SAUiiO2RI=H&N[k'EYrSBA>,(T#d)O^P1]A5
(qur,eCDc?Ca'K0h(rFF?Tsk6ggY4Yb?'rB,HN]AJ>CkHWN;%He)[GY;IBiMB'=@a[JEK0dWV
N>:3=J1)>"T;d1:GN*O/>T+:=p[7Z:3'ZmY[!',>'6B.nDUF%R)YS_j0X6%i7\bHllGr$bNe
o>Z(%I2u'XXj:dF!9hX8$SXee/<\k<qtu3]Ad#+6ZL:^i^En+PhHaJ/,ErAD:4??JDjNi>ikr
9d.iI5u]AJ.'!=!B+p>FDMO4\skB<f+0MT,TOKbg8q@OOpAJc*PL+?YD$Q')]A:s!r_;)g<"Y9
3uS"%qdu[G&dHhZ\QHM)I;#,!ip^VD9^&sLO^7:Zi)grY"V_\b8U!Q.+/5C\Z%j$h?]AShhQk
OCsg7'nK^RgKm%]A%Z&/&iC@J1?!p68o1XGg^&]A'jk.<^S0?J-'+B/D4l?qm7>g)pR/B-k5]Ao
k7S-5,X+aCOWDHI/o+-L;Q,6IdgWlP/K*h#hg\25VRunG:-!DjNl,=4eOUaPHB%;kaPI"&#?
JIP8>Q/&$WcEAu1R(!hO<8U"BV:Bs2*))Wje+o\)r]A]A3STCRXEJ`;]A1Rp\"\3(BO^!l-k-.4
7k^7qrlD33`Y-j;T=8&<_$i/LLWh+sDio;_pcAt7:0.kEVT3OoG4Z]Abbr<@Ro7bTs0Y,iVmP
ohEnZ=QbK`E#17h2[sL&'2\`$l]A##t&7rgZ'Sjt"YWX?`cbB_VYWOOn19'cgD[<9'>Uc_fVu
<UCF4]A+G:FS-(W)0JsGU<+Y=RLmK`AEEM=S9ApNDgdpTEhpVNEibLJ_4^*X`_[@_0F"j_1a7
<(Ag[9m58l+@%3=:)$Y"E$1V$9<1S>*EmK7TOQJo8&s$fpJkNWJf2je7<KqPT+JWUjF^X9do
5G42p_cMZGpr_X%O:&2S9+-U3'3F,[N"rtf($3kdS]AVhjLN=ZI;=#YkN_=")n5#sW-h+TQoL
S)@dep^4p+Ht#?<@0H!cR2:F?(KdP'0DGHaes_#8>b0jS-f<HRp>mB%2%koJ>Br&b'PNZjI1
$/]Ati[WsF-;5J;`V@cXf)+>n(TQjW$:TJE<Ha%.,7+/ha#r6BpP]A*i6.=B20.>#Dq`s<Neec
7>7Z3V\ugt_\5S8YN(2c`-\(f;WA*pA,^%I(/bXC>**,`k_98`?1?>T.b/&M<sbG^Y:Aeb\a
MW87N1m!JR\ihcQV!FU!eY.X?;Z0/jQ4nR!fY>s%,mJZ&+4jcb)k:ODH^#c)POpGKfOr//-e
UC86Ef#>Xhj_oL@j>?+4NPK-\h$WB=[F8:!#M4h%p@"7?6._-iGZXsA8qRnTL;"_UrN=(LW&
._SpM_Ci)B&\=:,RZat--4QmMH2=.LrXr@YQYhP>D7@/"XXfd8f@]AbK@FR\c.=]A.8nR8`qG&
A38^kfNXWH>n40>$C)1I+C/tfJqG`Hl?[q+-T<_Lq4ni_djuqB+7=91@D[l@kp4i&+Is;TF>
.0N9H=6\3X6!.++4`:]ApZf2E5/<s6s)!IEV2G:FHENFM2?387A'K:+iqfh.4KG+UfCnVh,8R
-!'8e0k7Yf^'=L(l.1U9:r82;3Qt$96g9)BX,hH6aMcX>)f@nft,:XJ(5&jm9D\`FMNY9Yjg
A31g"<#BpH^>!u%i@615I[fg^iG_LI55EIXtnDNYJRgFR$cHe(,llGp.>/l/;52AmeoCNJ.M
X.(`T4=?B9h[WR#]AJoSC`$`*n^2]AlX`faZD(rQ)4??C*tSB)n&2JGpMW``MbY7mOs^![lb^d
kUq3p7sMG;*r7-WOW%8>3gAXJ7JuDX>ME;a/gbtnN@8ch,mNgTV.i".+nL!^r02;G>ba4Ro0
D=Ej*no1QCZql)sr)<Bj>f1^!?04R,Sq]AQ4l%uK\5+2%.muc0/-qLcrs3=C-`Pne"*J]AI;YJ
ah\MJ$coBUtn/t+&Mr=N>ce0uM/bUG4Ue8KBBhcl$kH$jr#`A&n]ANH;I:r)[Gpb2lN&,?oph
Id'Zd8%E>HKiIt7;j9Ic0r`k2jELmJqRp.[QKEe:QG/;55qcW%,'NB7NIP4V*e=aC(YCFiO_
$ir$RLWX]A$KZ(7lFtLuaA<hqM>&W/'Yda9]AgTdC[qXWsuF`nlVWp8lLC>(T98(<2kU"85,8\
Z(?7jOGAoH<;s1kbYfQtLb-<h#BC/HOG=3:9'M8%;u,:$+_-lFSlCM94+0"-AEu@B`/2CPfN
DOYPm9<a4c94.Lg<JRKWhqWV`MYNqjPqS1R,89<(g9J)`'EgNS)d'Q`Ss+?G1X[[BnWe[Ekb
YZ$cGGaJfm<`M.!EMHH7o'aBn8:pn)h$GiSo![`l4AKr%-Gj#=N_Y@-C'Gp.P2O:6o,:XOYg
7!,\?Ls:9W.Qcp8/J14H*X8<H[Ks>hmVrZFZ2-#A+Og+3Ujid[p*0ZJk]AaAj#A2p8FV=Y%o^
(BZD@b_A`YojVm-N0c%-M_O,KJ">i#p5plOh??p+/[k&q5NU8=aeo35S5TSf;r00-`i@Zl1!
a4T8Z+%#b;K'CdFmCs5Im,F@?-(nb=P?,Wfd>>t!r['$dn]A.e1(ij)LBf,^Xmr;4S;V!Xn\r
I3Y9djF`Bi7@qqabX/1]AHu`E^E=HFlX1H/E_h-4U_W[/`0A@a@6_Vc<J]AoY1OnPS;::Wm-YY
?/;%snZa0u,c"i/n6:n&_I#NLo\6rm'//[Dn/qGS.eLZQ$Rs$npM(p7MYJ^/F7G7Xt+2ZK=Q
0sOT'i(=)3nmR(:6_&dRosIdb%W^Z\QV/b,sCUi"jQE0+#/tg=h3aA^ib5u<o_J<S9UScnB#
n3Nu4=4>d(E*<!;=Gk@_q$S?Z[P]AL:o(5""uWHOu&KQ/;;.X)pS2<aP$-mp\11oh^5K:*BMd
Qd2$)KSaI*TEOfnn#mR%+'S5PIZ]A-%XRF(=!'>3DB^UbeG2%B,_qOrK?-S/-kmlA>hlGHJek
[EdG^k,hZSAP?ggK"!J0BK-5F"2JJjJ=)_&D,tnQcSCm3T@M\Z3ka]A!6GcA7"`.M*1pjLC/'
)Yb4``hM=_2F1%.3Uh?1X=0$mL^"i#'Z;rA]A^/>tsd`pb;L0\'%Jlp2?Q,7S3"21)^R0)$[1
HD$L\dK5JbflPVi2iLL?A-Yn\<F)Y)I.9.g\-S;>`CW?A[&Y'Ih&/7^^n/IlEI70<DtnQ]A(S
TTEsBYOiQ["X%q0hWiEZ:OIL'PMI"[e"CTfa-.>j721^f<]AGUT#YZ1SuA;ejSSe&i^lqb/E7
:)+O_>q?2R=;#LZ=cKtEl:n:`T(n/%HX)"Y?CgeT(jp)0qLs]A))FX=Y*TTVFr\CH@O>'UITS
B:ag*dq$=La`55K&IB@NN`Qn?An\g[[bl>m?'3FFQ-WU0g-N1RH<DH1EjkB/=A/N!@8pW"mB
D>2Y_fa@Ao7$p-4%:!rq\ht'ObH%*>o*ePH05rmW\YnB*]A'lQ5m!i,\u0n[$V>>Kl9/APF)R
2)%RcI*#Od9S2tT%j>F!aasQUV(&l9t/bm*]AS>AcEaP)$^Eu?lJi8C#XZO&)5!r7X'NVcoZ3
oDkHs,GEn)-cpG3g/2QCotl1^fRH(_<Tm('p\`2s]Am63q[T:<_iN(MrP7g3A_g?e`-p#0'9A
@)h/\;pUM-)>MfgI$3G06L)4!!B<tt/0,)u(k2LW3p,$8!Sc\O&Z(:rWrJUS!aik/b6uQ&G9
kI>'G"#ZHKE:*?P';?H1mh[^PjO1X@tmsnq3[$kaE,iVU5jA/*msqKB3d!BC/G:%kSbFVELU
bSpgmUl)GE!i4>DXpgpPCMKM%+AfYInSq"VqkGneS$IZnr-[PL]A]Ado]A,8:necXBNj[I4T+:4
"`XCe!-CICQ9O*[?cf,#7qd1Jn`X1lU=]A+qE>q*Y+*tKRe:9.I>N53PhM74WGI'NR(+g0\i[
@X.PmsbZ-CU++[5^u:UbVASo<MU\,XMa)Gh3q`UNKf[IOS@DETYlCW-aF'7TK,bbDC%T`ZK0
T.P@i'8-Shdl\E.AW4!h5s8456V(&$"S00T#`+sdYt9fkHH=(SU3<PhLPT:aO:SdhC3RWDRJ
D4\@E:ggZ\nH?:OI#"ilCX"Y":]Au/h90nj4H^;3'u3FMHmHUQEj7Gbf^,,=e;H86<LWj;T"g
J@Qf]AjdK(<.h?V`6KasU&3A@Kp6HWF1X0mIh:*Nj(_/??tnQc!X8NFIuCXi@Bc-7g;VoKL6a
?g02'Ao5"V+"V2Qkf%.Etf*o(p_q?\[`N/@_\%^m7$K@@^>M1*Oq.%m]A41SZ66_5$!MHG-Wc
e/:._7jGc_>f]App:\NP@q;DT^6Q>7gf/'L2.EVf?Q`Q"#A;`=WYe.MS+In55MurOW.l"oHBL
H.eqiUBGA6OpGF_g9rB6A1C_Bm5&"@naMW0fL<'e(K9^k/6,^UMG"4H*Yp>j7P,<"OC\u,PJ
P@;onaF?^;Y<VbgVpfn.!u8*t-^HB%O3WWj4M;F<J`98i_!NlCaH:e9#.!Gtn#:PgpJY9j7!
t5,TI442ECDdPVcSc6C+;q6`c$'&G]A113%cRLa>AG@D28upO']Aq.=%1T8IC6)S6eI/>)EGA0
)13<83?FN/g%MqJRu50G)T+ga0MZ^);?&=^<IhdQb=oKFt2*k7Kil;j==5Co#Gml71.(Q1cr
D_G[Z:Ui'$&/LTFV%\GFkT=JsbjA.EY,qt[Ha%Eqr7cs%)cQFB0p'Kbd/D#"T:+7n/!<Ero!
3]A]A5M7YL,O3S,J`U]A`)T)()=Gfm>H/7XjRJ`*tsOX*#Z]AH2.2UIXQ]A;aqn`*d2MH0kIsrZll
(NOO3b2']AVj;E1mR=/@9XuR/i.RON)1K[S#=S<\Dt+;R"GWN%#E/Sk3gqLRDgBsj3b8<P4Md
lh=)oPIKXZ]A'n2^5:^GlR1(-ZgR$;_ucB(;SXfZ@jVI?7Af56<]Ap71Im\oEd;1o-,t#EH76Z
/j]AL^_ImYNphcGFtI<)VEO;COToX__)_m!>r[H'&Orq549;.G)O:$((9JVRj]AkG!gA,e*bbq
2-l@%>LR<D-5>e.u2A.=OY'2K0S>="BqQZd"IY+(qE=;^7Q,<qAD-Lo'p,A#/!BC\8ZI"s_+
(hATdKTg1cl`ZPS]AUVSb)V]A`[.%+;k\(r9sS%:T8U4#H^4VrUR,C?nB4<SKj*k9"1@#<@fC&
@nHnM(X:/[U15O`a[V96b$oNV3$i2,;T46Z<!,2:SD99d`OMk3SM&PIZ06>9Fnq`5Y&b`Y$]A
lKD#-j;Ai(Q\mPLNeh/QkYAeY0(r;p3n7dt<C+K$E:,S8XMo!WFA]A)N03j\V:O\c3bZsC>0'
=SVZ7jo7Y3U5l,NO/,Aaf>[W4"(o'1R@J=@3uA&cW@"cgLH^7Aklq&8YcZ("\lbFa1ZF&]A$Z
V9jp`3n6dRu8+E+d^Zp>UWc)[D'.qNER\-Hd^jY$stnsOpaQabnI3`#?s?hq26&tYHWo\Tg<
<pD":d?<UdY8\-fjm*&L7`U1:eHk%PfZQ70^7A-2ifL%Ec+G\<]ARV.C.Dj1$/Bt/,&Og02'.
XMsi/_A/(nP$No#.jnl8V`Hq/7/4Ua:\[%\IFk(apq/>kDh!AU>qHYU</t=lV8<TG3&X9h[B
H5?nm]AN?i*&Dkn8h(uqC_n6/a[,6?cneXfeZ1\%ah9<FGQi]ALXo,JJ0tHH+`OIk8n6h,=V<6
G)nB%=S$fJNtCr,qP>;&`\T!Ir*rU)PXOJhmR8O)Cj$#LcjP+K9rDV>b`T*Yt]AP7BbB<N8*@
ilGme8HAg#tY&GTG:,,j'9aI9/J(_)74CS:0Bo`\FI/1F95%a$8!PrHOg(JES[-*9$g4(G^G
8+eD#`&Wk>rl*^i$6<Isj?jC;R5.It,(8`f'E?[*&1C`81BR7[EsHmKG[WOaA@fFM,&!9AAh
oa3,$O_)1',/NF?6;cg)TI=,8:qdG/14he+G0'EIaS/E74=9I/I=",t6gq1-'D3U0XkQ<N.?
=hLuRh=\Z#;Vqefu./HI,Cr'P"2;ip3b_m7M\=F3ag'6pVM8AQ:=k1/,bdu5bVI_u9<OWf*f
?@BcZmADR]Aq</W)6[[dcnC_aEg-/lPo,A4:nnUliF`8M>Oa.LMLs&[c4.T?caq\MGIVHCAu&
SrVhVP'HI).kHh%P=+L0PZ'LO<;ra)C@8.iQ*r9c&Hqh5lS<uX9fmbE',`-8bB=N_Nu(M.\P
)*aMO@[qA*#*g2<BWL/L1aFsg)<%EOf1Y01X%BZLMPh!'\B9VW@ISDEET"60b<E$7+b]Ac?JX
r;-l\f@PKKc!sfkM^!j<JjD7Ma1i7fC8IRp<\8kdD8pd?oX2$)LIRKCoI1!-%pE%UDCTfRjj
f45Rs:<dN6q+TMI*R-=r)Q.T25YE8nEWM_"l$^.KVCI\/(c]A?WKZ1)rkZ3/G*4628K<Cqo`^
L*APLRkk<!p$CY0(D.Zh2bGd-fGDscE=,Dg$).B$6K[EbOGV,5njJ\GT?9m*=i%;aSi^oE#)
&#"q5ZX6@\^b$AY^aSs</PYlmCq.C\PLeB6GBWGi-i)>0=SCjZbR>CGCKVZQerV`$[4`hjCI
YOMs=fMVVIQ9G8FPl/.nl%sYcKl?H"!2udMN3Z1?ef+HEHN?f1)<Q4!%_4R/_B5u5@grN>)H
Q-;J8^ZMbl.^^5C!G(U$h/rU[=BHCQ<:?r&r>TZqgdQ^/0j,9@nOjL;uqI!bqO-[,>A.<K!?
=WI`T>3Cc$r6C6$5D[N?;5Q//B4O*S<*?i.3X31W4,ghMIHRFouZP'h&N2V8LMU04/['El\7
2GqhdpKaqKL*a7o)/[%XZD5i87^]A:Z%15'S!EdQlSVH0[+DJtZXFa@lMeBPo3j$?mlBoVQI,
VC(mJ_D%+`&^$bq>c`te<o;4d_2peW,j9><_s.Gj>V7/[dlqYp\_)[+X`QP_BnQ*9YJp_D^$
k4lh$fD@E)(3KSRi;^DL@k^R@DIEN/*Bu6RBu#&;oQSX]ABHcPRCE9)?)r1sGS:>'b4KdK7Ib
nE:V0(7on^hPtV,B^f-ec<Ws-LsEC<Q+eo-VpNg5f]Ai[O`YQ9a\l5G!$T1G4-PEfd;J4P^YW
`_BZ$#^TF9L42l`sh5-`be\,DjhmT$6p@-&,hZ*uGZ?+.js+J[)iO-]AlBg-K$o>>^.CDtL5_
pd?aWW2rUhd7M51A9mEeCZ+G[t"#9-/&%Bs/YDaO6qS7)`bl7e*HXV46HWVm9[Q/pUU%%>YY
=9ieb=jn\;;#ih#T4^P#%+b*Tl%aT,'>E/%;gc/+aaX9!68s7[jjdTbjCae'0fA`p?PrnG3&
@.eRRD!oRL5=p`qc5]A(HMA6DOPEL9eli2p"hrO[lbGLG<DrUa!NEE4inLk^`7C\H\@/CI9\l
^cfe^##LbeHV^nqQF:l">JBm%69=\%,FYp_O7T4Xg*7aRGP3\I>D4GHfZP`\o:FVZfcs@fe[
*F8B7l-7-M-9Uh9?f(U8V%:-1;M5m2sE;gC..@0nEKL70-"$lLjru0qtk2Q"4&lB?mCWj$_W
GHAo/*OuCj*sAg7e$:^bW%oi[tqmC]A'GkhP0<=ir,6;%?gs$Ra&Mc4GuFqBp,ch.,#&0]A7p$
7aeg\`Ls8'R1PCI[Z361OojmO::&YF>lDIJk8T_qV)]A'=R@=0V]AH~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="470"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report1"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<![CDATA[9372600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[12687300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[春]]></O>
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
<FRFont name="微软雅黑" style="0" size="480"/>
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
<![CDATA[m8nX2P?/M1"Q`X/Ba%?V(dMpMLk`<h,XanL+Gc*/D%TTT&RReU4<u'e=[Nlo9%Sn$MM2J;76
tJg'S?o'770s@7HKXU)FQ:lP;FV;YkLdS/DVe!G*pt9RIX/AI.oaPkPWUcpV9lM)E>XQec^O
hbQoEhY<sH)3!b1dNmVt<"b/W6)$2NC434%u+g(N>%jA^RH2XH+=,Y3BZSiOD[9^+L?HWhhR
ap^?B'Ws>p:r',&`3^FnBQgFjX62O\t4o$:TD0leGjUK-lq3G+\-*WUn-!t9Jc->5C`FA0/W
#M[=nD.\V)9Ra-:6]AD0mnTEq5h+Q:dG?-1eWM6/2AHQ@<VM'Rp\#r`]Ab*s4Afki0K*SXgKJd
Fo5s8dG=Ks$_PaG7p]AH3:HCTbh_R=O+mLs8n9/!M`50jDl!(>6_OH,jR*YA$a(7O`GO$1mjn
OkoPk+TG@:bKl&AoI:$(%<ULb;7'6*-.e`P%np#;9KcDl6gLf=&`]A@_+m,Z7aHN2VZHod[Zs
P%/_=tHqDc--&oI&k"kaZM=@@?3osADL;#G[P>T!8gsc\I2D.8Zq-,fjoQ*hsRXkWnARLIkT
o?+$%1$!0=[p1<_@j@IbW#Asn:)ZbHFm5::;<Xnj,oUnjX#1$5QmRJ6(op'b@)n^CX7tdf$"
8+XgRS:P'f's]A4<S\@3MkbVOSu[UCp@8S-($l5CFi-7V4Ltn4*ICLWShb2$XUo-_N#==U#;h
"A+mUa_EXOBR,ou.t@(Xc2eb'hL+&7\HdOrA3Q.,1`LgeRg0)1!goQ1"!DahljP%fH!qUFR0
gIBs1fM'K@8@/#frn1kOoWf+PB8#1;>o?nCH\,:_)OCff/O6kg0MDFDqf\P^u1^i8Eh94Ht#
4R/:S&co,ni6:&lB7^\!Q0,?BoDr>-%Ojtu'Zr[KR^O!u,'fn!VMeci`gB!l\7nlnah5_f`M
p]AQg%o#3Q)(kpbV7PaMJue)L.+/89V=Ri0r^11'mGs8)W=S:-\)AJdVt(#"Mi4YWet-.I<0/
54Qo..cd)q3L%8*M.'DRLi>4tAR3M(u5@/#`L0OV8);)P^:lNcFM\^nt?9?mPWA#lc5(-)kk
DUuftV/a)UbYU82`N4i?]A.dSaOO`^=S[4j*!PjFcSSC`W9%]AXn4R2>e9JQ__E(k?,l+p5*:Z
jdi*<f2Bh'gQ5*K6,oABK@%<1e=:)*Ya(R?Jb#rTMqOoI*215s/m^nB%,)gcAO40,iBmUEo<
3)Y]A=/Y?"IhMPnq-BeB9qN."p/"a^pZpG2$-`"!:aT2a#$m$&'th$r`6-Y$2uY!\=W`qD's&
ndOL;@E^l;^set0=_Z\c&-E'QNiL9nX$Y'5?Gta4A_dGN4eDf7qMCd'BGTT6T#`oSAFm#g_B
t2(c+S>7^O6&()ME3#/\$Fk>RQm`XH1C4ugoVpc:OV**36qhh]A8d7Q.6jmN/3b[\hkF&(TP2
77m-i=AsSI/+_#r^(<q64HAO"=SL,EZT*Te`[]A`6&34=@/(4Igk,;A6*0*%nl:;L*ifE+29H
FM"hGiLDDY@nf^7bR;3_8L3(9/B!hTj![(+N<0bo[7$:&4TYai/lY:_*+'V$DNhD\Ne!nSu8
."0<N9ELY12XCb/'>qn0mfUiiGs$<m"0Cb`n=ZqP5DtI3^5.rTMk6bPG(qu[AStKbg-;?(gZ
oq;Ie6n_*K&P?4,lF$A1uj%]AB%sWsA#JlO/sPpkfV86JP:Q%ZB/[Ku33^Y!O#+'3AVj/5pEm
=Jpu;;@0E#=Jc2EBnEVH)6=/*TVEpF9JKrO/^4Th?r9B)>qC)63ck"Ro((<i%D.A,hemd*+)
BC>$<:/95AjXK-l80%5Ekd3`FW"]A)b2J_XB%8sq,`o\gOS+h4EJqfPDJ2$CL/=gC3,"h[;S=
g7Q*6kP'=`rH:n8QWq>"jj1#f8[\'>Y.sOW]Ao!`W\mu<6?7;0TNRD2Vp?/G(6;h=cjbL`G^>
5fik1Pm6ZD*bqMWUr>8p]AJ]AHomj>(OL5NH.+99dLAZgFpa#9l\RUrW?b6*2#u%fAiB$t[FZO
Hc[XW@G>2Xj./:(3s[_%VKoGl;a.Ha/Pe=58J2H=SDU9ILgU2'ZH<!lid"Rbu%C<4Ig]Ap#+/
'>AV@.X-V&9seDk@kSD+eSYeAS0YBS9Po_O+Wg]ArFU'FW`q^l<WGrqdpqFn.\]A6$o0t6]AAk^
Ks?Ao!AeIl/P8Zqf?JT&codKnp(63a<,76YK6L5O3_A0d0bl9F/QE:kdlq/FkLOP:hHZ#_2N
Z0T&c8th\LMT#i?XBBaD;;s+Wb_^/>^*`@Tuk;hjSqk"K.,-4-"@igVGZq+tFY5>U(\S8e(V
+A$Q.tXhja6P3=+/fpI42<#m0dS)#tW+\d*.qNIe;_U^a4ir6)8,%UCT>!Ot1[f87MVfn.L0
oJ7GpDe,<LWd'I#;8d)0'-afpb0ZPf@t#f0d,;oi_ra%IT/Fa1F(]Akc4>_p<HDSTb_2"fIen
"n<`TY!"+H1@+r$Rj2NQOTnrTAIO(geDP=.?s1m;f`2b39C_C!VEN5o#8oC$0@&9nUDKksmj
lH`'T+u]A`n?%-kUZ*4A&325#J"hGIj_jM$!QJeu.ZlOH,D-ig\D+3KZD%al@*Uq/<6hBeW]AD
6$T5<,Z)/4JL]ARK#dT[&$=ZIEd@U^6/O6G_-ao6#[/t!KK;VS$nZR?'!G"%b$,T=!3<SL[!:
s,@1uV<EJH1^$\Tu#@tOO[gPr%WAfT8!\0,f3Net3FXi?(&:Y5=an71.+@a3"I<5Q#WX,#E4
eC[_H;mN6(Qc7p6'GIDg/:>up8K^]AL#8Bd/"?i#e=^ot\uX36mJ:PEM0)!9.9!2*EZBD&3ER
QF'j?jX)+;Ed*E-'h?+"YI2#rL?qK5g,\0`N,)R^S3;RLGtWlWA&Em&)j9TIq0ZRME>&`f(O
DTS_p:M:g1M:^q8WGbc9";ghDKYj.@%JD%B*b0,8k"T^gf0@$VEPOafo,\=7.,P-1:fP63/L
T#`^@r9(6s`8J*dSg`A&3sHRF26^g&<q$3BVA7\Rga3+pE2rXf'-&<PZ_4+OcMZ'd`A)K6qm
$=+elQYqJp56^`+>_<^;X+&;<U"6dd&LeXZP/9kRiPeQXVd@'6nK,N93a3(+P.fi@p+]Aa]ACW
LZuDNF4MR/dm=OT,s:[qK<ZofEeui80#ueXrb>dH8NbsOkt)IXo)8BY>Z>2EMh`6^P\>Ue`P
oH:A48Yp@74'l$9FQ3Ib6a5DB/f5lm\Yk>t.%rXA~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="470"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report1_c"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="350" height="470"/>
<ResolutionScalingAttr percent="1.2"/>
<tabFitAttr index="0" tabNameIndex="0"/>
</Widget>
<Widget class="com.fr.form.ui.container.cardlayout.WTabFitLayout">
<WidgetName name="夏"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<WidgetName name="report2_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="report2_c" frozen="false"/>
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
<WidgetName name="report2_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<![CDATA[1066800,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[12687300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[在夏天的原野上]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O>
<![CDATA[在夏天的原野上]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="1">
<O>
<![CDATA[我们的步伐悠闲而又美丽]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="1">
<O>
<![CDATA[我们年轻]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" s="1">
<O>
<![CDATA[风吹着]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="1">
<O>
<![CDATA[散文诗一样的情调]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6" s="1">
<O>
<![CDATA[抵达我的心灵]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7" s="1">
<O>
<![CDATA[与夏天的原野一起摇曳]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="8" s="1">
<O>
<![CDATA[我们是幸福的恋人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="9" s="1">
<O>
<![CDATA[有一种无言的默契]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="10" s="1">
<O>
<![CDATA[我有长长的读后感]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="11" s="1">
<O>
<![CDATA[你有潇洒的风韵]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="12" s="1">
<O>
<![CDATA[我们宁静在夏天的风里]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="13" s="1">
<O>
<![CDATA[我的畅想]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="14" s="1">
<O>
<![CDATA[恬恬欲醉]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="15" s="1">
<O>
<![CDATA[我的猜想]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="16" s="1">
<O>
<![CDATA[浪漫而已惆怅]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="17" s="1">
<O>
<![CDATA[夏天的原野有一万种风情]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="18" s="1">
<O>
<![CDATA[我爱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="19" s="1">
<O>
<![CDATA[起于水]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="20" s="1">
<O>
<![CDATA[为一种情绪潸然泪下]]></O>
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
<FRFont name="SimSun" style="0" size="120" foreground="-236032"/>
<Background name="NullBackground"/>
<Border/>
</Style>
<Style imageLayout="1">
<FRFont name="SimSun" style="0" size="72" foreground="-236032"/>
<Background name="NullBackground"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[[9@o>PPtC19ZX3'%q22b,XNjs.u,^MP".N,St+r)MB"Ol8p$6\5i`IlWKtE&BI<C74:rIYbX
PC)XdLV"`/NTu;a'%,p[rsK^YeX2]Amfs$htu4'I=89aID)iYFkuf&I9plXAbsS\A7SY_,@%l
mA<md;A3bnaQ3(\M"d`T;aB8*'G1l,D>g-^G\\rW+gj")4h5*<3E6hSLWToR86+46?:kNUNQ
!XAVeYAB_7*K@-n`@`5s/4P^oL@oXN@s("X.OBY'O"Zc[8uls$(Vkp5@a'5B4q#I))ZT8"Z-
*g?:(4G2lsjMH1q4)#uNPNlt\:.JuKuPW_`reE+TL@X!0jA+fM+bcX-*P(sU@t7YN&&oB`QC
T:R+Y.gGC\</Hu6,LBD"45rN8!hj!C5/@M7BH`,)4<Z+WZ6"hn$[-fWQ@%2-_hTCAD$ul+?$
37T7WYG@UW=!h`6c3M.[WqD2/;Lo*8r1oHl7LT4[;JZGQlU8WXUq^e*UT^i6+TV)\L>0-GYK
t$YF'B16?e&b?L"^0YpiDo,.7c.b)h-+_@0O*GBYh?#7Iu4SU[N9(VqWL;)RY_BnSEF612<:
eVIbNgd.U+nN&/rBcV*iDgYG*]AUBHhR2Ps)!H:dMp2JL90>O*'Udumf$p78=hGfJ-K.Kbk,7
)/;!/LAf9tsjWF[n4"pLpL$etLAo=TZ._Zfu6kV-&+'k=CoP**`u;mFOQ-gKK?n^-4,;7^;8
0XsPm$leLh[otc.c(/dP`kmqg5cB(_bie,Nq%?\ZqnqrYi->ou5<h:I/`H%f$!-67m#;9CT4
o2SNpai_0WW[cM449RU_:sfjg;AY,K:t&cQgH-VS\eNVbInX#:2NB5O.U22M/mI,)i.d2V13
i:X;R9W,+?AA1nMZ4:H'jOH'r,T.<mP48A]A7b.3G4Nf=LkAilW,2,Qe5a/i%uPJ[o[Ol\jC`
g*5#Jg/</QIVZZYU1<:>0X/"r7k\-$Ch!Vr3it_3baZs6<,n*-:k_t?5l%gc4+-)4)l^OF86
cQc?^`nh;n&G=6^-G("G=pfG7'j,PN9m>JVFD$Xh-ro-2n\V[\jIhJp+LljOrC3jVCJho[s=
F=MORpX_bD8eu'C'32Dg:1;eBT3]AVQF/hBe>;dI/YdWO(dT>:3q4-`>m5tI02"H7ocI-IAPD
G4:S2cM_LqgJ*,57%E%u03b13N"II$Ld"Ltt;q.P7'(OoqRMDsB6H2-2MK&Y<Q2Vjs\dV4+#
`g&s*;T>hCN^Y5J6F(,Nr-`*.EWn\`Fm4/pG2%XLbPA=i!#8!fVaEoK=K,X3G]A/[WkAFA`UT
>@^uP@5#r(]A19^+">BYpL$+pG$L3.iBKB2!hm&]A:saD=h,:lD7&3)U_RS3CL7!4J]A-UCS?:q
;KZg98\).E$qRTp6Cb]A:W,ktDgrkO^iBrF;%'1&n<RTlX9DPj*_)cj0oRCi#EFB3j)lHUkGU
$f79b94-,@"=MRbgpsX^VQXPkd@8PnB4dV+T'<;Yjd8lm)[Dmg7/diBcKYYkDc\Oc":DsiXd
Wj+[$B[2SC%B/"bRPrdD+R4itRapP]AgjP3O!C/:k2n")4HCfRTh4hfE5#&!n2V7$2!&DUr*N
cGqcUX/YDh.9A5Q-0]AiuC=&eC6OHKFN@b<CcJNSGBD5Rmip/t'r5cTBaQTQj[ECChJ?L/0?,
4`E;1G;h(mRCc!eYoJZRU^!FVR<&qRD1B1I[Y%JNW?It7E%M6_U`@Cj9dc1Sp*AaoQGpSbH+
ZXpR+5f,Bgf??Yb`aKi'\]AFq&'`&3KQfAQXSOHc51oQkm`GhH/'p/2=h4dqR#r#EG%7$k-2Z
L1%b#Y/dleT<T\Q=Hh,!!+ic`ihSGfEsFVplXu^fY0.eEY2`i8-LlX>CZ;%4kC[Wk`X(U;`s
`kV"Sf><^>;bk]A@r(_6QcjG`9EN]ATo:EoPn40a:Nbq"DUu35L9T&sU;*CtH0\(4`/'QCHJZ8
MPNH6gHJ^iH\op2jCMiIm>u0U_S=Ys]AptUnt<[Npo<C4PfS8t,q;h2BkYD$=6S/1A'.NlKSX
QD"tPe9ph9;\#88S<qW3C=>36Y;D:qp>?(fO=K.B!kEM_hZ2q7odUqjI.c`9&c$e<#(?cnD<
dAZc4DEO+q7=coUaYmUP:p[?J/G<Q:b#bEEr4W$WbCUB55Tj+u[faltH_#Y>fq>3A96e'e`H
oG=kmZhE/hA)um`&gsMO+ouVS)Wu`*A'ZAlp+tuf1\3aA<g:hc7&i:f3rH@;cs(S._EDA5ne
:-iP$WY-S?e5+1<M]AXT(tDM$KCEmfmQ^WrR.8ppLWcp0Y*MhbUOE,`6*/8$Khe)H]A#Y'3J5R
<;N#2R\p8#<JYBord1,\OC:Ts]An.2U2#t9=0BtB3r\iN/Pd7gG-?TEB_BjURcRdO-NR=,4"G
L8Z4)Bkm7pGdPg=iS"!J[TUaXY)i@l,j2iL^EdIHSi/%-?&q./F0*/8/>7grb=QOGPe-Ae%k
kn4S)AtQH8<M%BYY`>"h[A&7E'p(0hVDBWid2obWn@O2bWX_Lf=gM+ZDLT`X0SLkBMg1SIpp
Re;0[YcIVCkIF8c>X0H^BrWE!]A_@S<?)M^7$/KlTls3Ls'tFht)c\7^=TQb'r*gB.lLo91NG
Wd$Vhi$q7HhF"^MPa<klrTaFLq]A<0A,CC)2qSXbgfc]A#ouU.pQ`H&K5%qbI-bY*cAaNfok5Z
JWA51iZlQnrA2iQu)XIpL2tHC(1c(^g.@%TNoq";[QuE@[]A(MgIhCAeJkcCu_E-t;AY(914G
]A4oBe%>Cu)H?jb-BGRL$@^NE@uSRBCIl0GQ6tUB&Pd9Yp.=?^@9Z01C1lN_/aZ_*F!<'AOG6
4tJr3[XXWPgW)cb-n?Mk\+>jK3p?18r2+Qq!<hk\faI;%s]A=1L@H!]AJW+diI@P$&1;1)%*GZ
[]AH1N-j7*[:*Ja9iPm'/o?t8m\C6_`HX%2SaIO+L&J"$]A=M&uIDj2MsKI9iSGHVIC6N:M9i+
rCGX4<Es@-]A#>-PKW,)k;*1g;$R8O?(!YPq[Z=A5^_dN41W'N[V:Ec%Vmu.G0n*B%Z%!6E8N
HXSZ7bnGWHPN=SWL<;R*mnUGk=pXVcI66pO_=m14oNJ'/N'cMA!Gu_ne`KmbIEEb`!.q3m*/
KA;"?d^eZFM&bc&pkf@Y6DI&ScX1<!Rp4.)F.;^_a\UL2rRR9g8+J=>Dk:0dYmb+CsAO0]Ar_
MHM1&+oi7&]A!7\B;JUCi/FRRO!NjRl45,m?V*)VJ!*0>c^pL(3S9[^66Y"(-gMSVcbJ0D1>o
2in9%8l]Ag4:O67<oN#sW4U<oEcWoc`0^Ti7_>>,=,RTMtEeG,T:=F/V1iY&5L7U6(E71F?>L
O+n?m)?KejLi4%hAL_l(g/3U<nS2DBNCU*)Sb*WBj=dPDOnbGO`?Kr`K([m"He:j#BhjpHQ"
[^cDnrbeW"6ODWkUPghE@%)[<GEA2cD,-A[BiP;]A@c4[(DN2-C`'L:CSq\D"4a5e<GPib1RV
'%(*3j2JjfG1]A<BKXnXQ<8D_iA8)5Y$M=-88us64<FN+LVhu]Aj@<]Aa#2M@p"%pVBi9^@a@0N
3R#8L1*.jburk*pb:!"b-dJYMI6Xo#rk^H_!(LCtF:jI9r\GDSqelTs[fI("L?\&:O:F4\.O
5PLrhbF18XN!,QHXQ8.pnp^UAl(FP*cUC##*4QrnH>h:PL@^>_4!i6)]A+YufkU!ZTpf@^,nU
]AXN0Ta[W8<>/b*;.hE7O'h4s'Q<F<-ec"ZjlK]A*aV.Ce^<GbB(7Xc\5<1W)Oqqf86s6&p`hk
Z6LHL!Bu.RUbI9?UoalE(;o9l5A2d=Zhk_]AZ,XLni>@IHpJl$o`]AX7c8;h7c%7M)]AJ3hf^?>
G%'Sh?)\!`G)2c6]AS:q6G'YVX!<'LBr9g9PY1OT1^B,;#4Ccd#nNKd1Qs(uZEll!rmZtSLff
YObW?1OC71ae'Q:,q"RiQs0UF+IaiB.np?GLGp0DdjfdlpucAA?X)R&bCSg3(^f,(H+:3$:a
LDETQ,H0:oSg*-FCs=Q&Q$MI$^nDih#Vt"5TYo"">W#EO]AEkC*!AO*/Dt4-2QdLa(qnB6NlK
nlgjZb;@S=J)=+.+\4ZPOh+?t&8po,h'N_D+tPb6S_8S@Q0Sd>Bu8Q+E+>\m,9lEEb+!-oQN
5P@E&,7l_6.=E'/E\<eV+\r,n_orm8'\S(W%RP]AA=DFo-q49q5-+)Y<iXl0S6O+h"i8QaY#1
]A/\,_Xhteh,cm\Ys'5jRo0SAEbPHLd\.?HE[lFA"Es(*$3"uB-'9[JFQlJ4gY06S"Jeg5S#?
Lf['#TJPknN7.lVf'#!,+PB9.9*a@brQ\=sS"Yc&jm]At+To;o5X+$L0DM68j8r6:[W%:c!%`
845Z1_#(me>(s+/D[[Q;P)T8D_5fdnKL=LcLmehAYaqeigptJF&$`7oZ&H?a[``f=6;$*6!7
64YT4d4*MQl"?i!t0o:ohtH`8bCuJrK"c1[VUID@@4&"2nh8+J^oIaKHV63!`&^S>P.>Ph'J
==J]Ae*UQN$>:NW^UH(WNf_Z1FY_*Br?r^^LL69NUV`h'BVrp*<.'`eWQYGG$?@iJd'MLEoQ-
:.fc92)g0L2K/1h;>:Ikde^eSSR_^)J"T+kS36ik.oak9k@03QL"FX*2[2!p<T!IKdQR'%#@
9T*a7iG-%iY">g1)J&KVDI+0+^nC*QoU\:jelV@*UUgM1V?XGG';FC)$-.;IhW_]A"?=BD.7*
]Ae)%q]A^4MU9Uk<LmfbS$\1]A7<)s>/rEmdd;J$%t\6Sl(8j/\Ms)]A"<Xc0!A)l3LkF[DkCJ8r
4=8.+R#V4@RV3dQBd+I+U4ieX<HhEQre[&p0nHA,or?4J?"i7qkZQNPaO%J':[[kJgXC5jtm
?S"U?t#&BYDMsM=Yk3%uNY'[:sB'g'+qI$W:$kf@o+RN@SHg9&H_`b>=m(.CGjO")XRnajhE
eKGi0=*:hD\6\@3\ps430J:BrI(O=ho=^OqSY#Q9Z@Z5!mDc5Fd`J<Ih!?!o_@d=q@X<@GVs
u#'>p*Ml?s^s3TA[\J4;!n,^HWc=hh7E^$HAKs#sYiQ1P9o]Aeo:q6PYGX`cR;X=cM=;9Scig
d+CgU-LD_[GXYA_[l'8(qstjGd*'E`LQ-9[Dt-L(jIaq_TjJTuCODE.Ai=lF+<r-@J8/)"(h
ddBKI#+P]Ao+_'5pP;k6FM,%p-/9b+;;\q$`3"l_NKT=oRdbfRuDS=6;`%.kni9V=G&s$hG@]A
*IafElqAo:O1\[J0;g/Wq7t_\T<Y$KFT%S`GMrpTmC,MgmnU^li.&0+OM;lSO!!O,uH?\I#d
td;gW?Gk4/#^knX`C-NLp`5>=l\pud=#]ArD]AA"8=SaTcPVK>#"hi+%]A+T[4e[@nqdLG3H'#@
*fV#HD=X+[)<<=gA5K+:cI%sa-p`/kM&Xmi+?(5TGLAku(Jn<!]AVdq98Hj[=c>(e33.`&h?[
We>WG;Ml`JW-l4e(0+e=hYM/4e;1JJ?;`]A0'4YUX";:\MYr:0&'Im88lQ!VqX<,b_m$M?3]A(
%FWT>9SYT,N+d7(epk^at2q$+f;TAepZ2A./`[o=+`>>fY4c1V/#9[OcJa<uS&=,A>dNf40Y
AQ%V0@qJ\6II!VHbT)1i"!3>S@qF?ZbDTY_I#24?%Q>s-5RY"c(eIgtP02rh5q+#NO=EGg\'
rcq&J7%H[?#(E"RR]AgS6#aooGR_U%,D_pmc7`MZ-Z>"7Q)'/;4$iZ:>-_RW#M?Yk6k=iGqTR
f<^$@t'.#'QQOugl6acp+_\Z=/rg+;6=OqP4Z-$b0MUhMGu(Aa+TUf[3K'tl<3Yr2="cA<Ck
R\[a!/^S`R<&;pg^TPlW<nJle0K7\Em(XCYoJcpmke;630X@@O@-eeP`'BC61ibGC/0Ee:6%
*[ND`":J*lp&r)TDG&;Zi-ud[FO"60,<KbdsOG0!RVoR0%i&pTE<f!NOi<U<WKRj4)\YeD.H
gJD@bsG2U5jQ*o(J<q@*K+Ed+IfTsqL-?JlFoHf3Dm=E;08t^f08G?of"C^.o;P'CZ,e.8(V
[&oMLd8n89#2@8h_,/E0`9rtRu&OkZcuKN0$=B>4Vnm!ft/O2bn%U(Ra8f-?[4oRniJ5ICWQ
e_dN$]A)j76<gA"m82J?pc3)qO!rJi3cZ%1l=e8bN\FZ317=]AEepK9-"%$""4E]AJ.L&6@='/+
kbMGX87B";Kue:61/%=E@/Zs[C`57R"hbG<);6dCDYWfd\Noa6BJ'Q=MAqlo,*rgEQ)S#A64
*WQ7lJpgbQfg_7?>e?Z#R;mO%NjE<d`7e(2-6gm#s,K(%*QXTL7-8Pb]A8#5b0F)+*TYXpt3B
kc"]AJc[?NGnL*ai5nGmkM[$[0C5@Vnt%0f/e'lsp_gt[pWH=71A*<W=hK(Qd*hVea/;F`.WH
\^$ZC'g]A'39)E#"`"a-.[/?6@>qPI%5Ie&-$S+4/'s&1m@7ig0/E9s*d>gWX@Y<Rd']AO90n6
Bo*J.NXHD0fMj3fe%hFlEk5"0[2h92tGXqO/X4hDl,rHW*)o-A7WJSL>hEqUk$5Ki>G1oG3t
?'UXp4*I\$Y)+k.Z3rqMp7!F2D"Rr7[<jKT"F_N&2G'H;\MJ-7/+upG6bh"#cWB'SOCUBrr)
"F(]A5nqWK,bo'CN4BtZJasFKePk_!j>1`^:6Rk(7?*R]A'[tc,7'J)NhqcYo5SbFk$pZ6R,QX
jR"V.bl)b/=NaE4n7kE1Ji<"BIGKC[)^LgoQ8q@UaXDX0348&jI*+B+0kQU<03*?F3&(#IoM
KT66o3spL768iUmh"+)LE>NjFNmk&D-%jil'>!n9^s$YJ30uI.QcUAO3%Pd.dY:n#AZ2%_Tr
uDrPpTX74fejdDglm>iN^3:ATd`f&&moBSo:c\Ar_h0AEl&E0IEoSV1)-$G,QGI@&Id*E[.*
^',7Z;3c[ee0Aq3GifCYqd:=?(D0m_I4uq(DSM>8,`"Q*QbQ#hXk4A<C3rlO):UQ,$2&W%\B
D>3pj)rlWaS0Q#b2t`rr"&-4DX9]APEH8rabgF^G#2EpSXh%p.$*[qW2/PQKYb?&+s<11N4CD
a^"9]Aj"GgPOOrA8;>`R2P4Jhsu0ujp^+J^`4(!-Wc\e'4TdZ`oKLbJ[fdBfeF.RJNK&I.as`
$(8Fl&l.-mZ&krcmk?[@cr<:_&6]AO%ih;FVKKu*fJlBWq^S3]A$d2`Y,,4raT]AL!:)s:-fWJm
SjcL.(oZdS^?3]ARF1aTrBe:K?>UWa+moIeYDZ<6lTkDd-7/![K\6J-@#&bPiD+kr#EOE6iW\
Z:^j0=9SoM'qbtt8$`>`GCZ-oN"FIlh`0i77`1Q@]AHiRnj_bNm0.R.C4:/qd>>FNe*_7^+T`
5o:EQqOC%VZEq?@3$/%<Pt5<7K4Tk2Ua.6]A#/caaB7n*:!uXqJeCLBI/7.:1bNMka4*dIYr8
7/$H#>*h!DNIJgZ#2=8.N3jra(-+Qa2'':LG^I_RAV&H%M3r0G@fWc4F&TpK:U;4-Oc^B,OR
o$&jL394FiC(^:)t/]Aq$4f41coN*7B<+gEZ9G^;+dBF3\rmE&?%ifXarH11cQ.uQQ!uGYVE"
T"SY4oN,1rl>77*pkB,pVSA!/QU"\))-DS/peXHX_c.WS4odC=?ADC*SGq)2177(SMMSXE%G
(M)s&\sL)WFd96iK:OFLE^5n;#1M.P1<BPAmq-@(e+0^Zd\u2IjFPqH+Fh1^m2?6>3UamV24
H%!q.XPDr=]A>t5Uh3r#+Ep#)N"\4M;!:[K$3B#'Q7W%X\eT)]Af*GXFH"=s='ZW;2k*EcB06f
!,/E&T2d.;T]Ag$CJp/]A6`'hKp_A8[+S0!+E]A<IRFV.-1=BMM>16P/g+sN[o>28A,l>03,:Q:
3OC%7,3UQXprW/,9ao>DWuts)u(N>R9j3M//YltEf`$,@:/g]A.'M-CEEI=&BgUfM`Y!oY-4f
<)E;fXI#):hJZp1drI7)\q%B4@3PpSjSQ5BfFlguauR-K1QoEl>1=:H9H9V#^Tg#;cPM3?IV
T,"4O8J/Ou)`T,k*.0ajVfhm'-!9[ePQ#,r*C/I.flX$hNc"ijDIt8L6XEiNNtrduO@_H"F)
7$d\Ie[M>f)K=?VJ*,U^9J\C%@_(TF;4Ddh!i`h++S:KRP"lo@>23)._e8=Y5P@>Z)nj^%,r
p\8"S#><EdfSBpgqS_\6OI2>CW$<^.5:fC'=`:\';]AUu\HeW?m>]Aja!D?r>r+>OL-YW^tJBi
"312>adp@@Z'c>UqZkT+O2X8??'t8BVF>p/KJMFA&5K8B<!'&_Bg1W#hrD#H^LGXEP!q_(/'
&@Qsol!Z!HXaPDIb7h"*Fpie^1/RO/81n5`J<\/jEH*?c:7%YX!9R2ML/'XA?,l505c<19?h
Wb-.ACRPsJNeoQs2&qSVRF@`"<=#j*WIkE=$`lnI(2?an':+X%_;trXXDp1%N[TpTZXICC3t
[mb*Od^ZiNZ+o/t'4Eg^>USLB9Ij*/goG)-F=r@2CPXY`HVH#R^R[R;8QjN'&7LrQ\.#MN:n
!V2k@FY[89Wrj2snJ`?l*"?jupkh-EbgC)3D/AK-S8d0eN@frmkiMfD4PtZil6;<_QOYn26$
ggd(IL\:-*_J]AKRb'4lUa*&q'DCVT48AL4`HRZqIj;eDO-?2eC(bif4NH7Fge,]AKSdUJ^GJX
9Wk*=pe/lQ/U^P:0a>3m>j"Y0;-o8=>G@A5eqQmZl2b=!^P>T3$IhEl$P,;09>m=q]A3,;;S1
Bsg"&DK.'*F'%OF7#:tmL7SGI#Lg_*VDL!uWB,>q00HMV[ZD:K(^_^CL*"!)\pZmYRak5_:_
&#'_i]Arc2XH-<aYgFGV-[EC+C^[tH@Rt:EBXl,GcJf2aq7$WKF-L!JmJL(#/\LkJ6qL,.t;e
q@r(%qUT,3r'2oGliCtoW1W*8P47I2Gs+A]Am9YQfSi:_kUJ5S\bVH\ki((TR5)'Hmgk@&%kR
USbSh]A1(cH]ARt`ednBg:0N!f5^;3N$KM`kY'aNTCHs=Y8okFiICit;hG5hPo;=age(smT3q#
>O$NLlmemc_u)#hhoHAialmV2)eDnC\B&`_8:R(dT\D_m]AZbHZ/[nC/o7H+iReJ;!WM0dm^>
i!iCDY6u;J&IKI3b'+#:p'KY.$A)V"dVkb#'fnih5LFg;P";_'Yp7!Jgo(E[d&T3m^MN@CkY
CN8l<'03eKVm2L5noB5-2jEJ$aQN(L2pnB<Vi9UNh4R7U2!%A96Mm`%%FJM@rDSoBY-emjf'
+7,;c$-W:dY4M#$t/bZl"5>H:srG+ZbRO-52]AU7Ze+%.$eTj8eD"k4.S\>)ainjQ7X355a`@
/b<ZlP[k3%=(#&kU'2[/IWp(DONeXD0lr<W(_Lu?J5!:D/09[fR_4IlWF3@J&N6/K:qSbI%i
PLgn?EsbH!"-n6Sd=S:<)Nm`)T0oROeD2(RH!Y;d61*0%qks5$EEek&b>`'Ef#R5CuKD.BEY
o*lF]AO_8_^N:2$C^`!^VmUX/b;rY1<]A7Qq/.7C.&1F*e'/T;goNnh.:"PsW1$RX-k(rWOY;L
U!<GAVN?qZd@S6_V2gk&TmgJ6l!S<#/0^#79[<I\E4iqQ9A8lZ$Xt)\og<'o)S:@`iF:1#WJ
c,(SOeW6V(X-rC6<hKD72.+m9XI(&h3dUV6I;2fX`3[maEfsL=G,q^>D_RugM'pBUe$d`k-`
9X'-^T%eHD3!E79!2;NX!U"pa?3C#/>A5im.:n!3/&omE!h42Tc38I><?g#_NSlR7NqbM0d(
sP?SVpL%Inll>3^P1lVT%-")?uCF6!&Rg1n3^):\#S#!urON=oCn65;*mTM$i76Mi*1LOf'Y
C`Y*G$QdP5F?]AA8EK$]Agbt.(.H.!<&l'Cg]AgU[A$1&,l?W.ao0jLE_e_(X`\%NAFM6gk+4/a
i(m7l;U0JC#0[InnkJ!iV+kNbY(@Ii`<EM=2q,IRq=/bSJrc1ZSSiamhf6l3^oqbH3Y='YdV
uga4@^rnTijB[j@%oFFSe947TXZ4[f#bgBK%i4u>G+EK\6KIYg.aLjVASE^JK[Gum-)k(]Al:
a6R4>89U.*p`Mm,f-[BgMUdO0P/uFMp"*+II1h,MONn9%fA?RQKL$"3O#B'pkdV#C:<Wo.'+
/IIZ&8UEAEUKnCE\Dfs-5koRA(pe>UrX&c@t["]A^.uB]A+87'F^O&H2L7VXTuC&QLq[PIVQJH
8E4J*_7YbOKd`B3p0I#norr@rA+rL4r5pejA2@ITMAQ@K'P=^OArjRabV!MS'a]AY<Gq+Ij77
E(7nP\lrI*,ONedZ#*l\jTZ-E]A?on&1:gb.XZmAYiJXU\Q0,PJGS[[c-4-GcU3=`3iHg,OGd
F+)HLP.g9/81O;.`[C'c.'h$)&(&AZ?*MSh@CDIWRd+$U^QpZp7'c.?1?r85B%(A`Fe-Iph;
r(jh?l;3sduT1N1[Wo/p^rP6^StJqf#429ft[,3ViXV[Cac'kNTS:E2&F?"?%LgG(k?X.M'(
T>U\U%MoW[UCd2\ULFL%&Obp@[*@rp>YJN80OZ2?<kS<%.@OG'1r;ZN%i)i(k'7oW@n]AR+EG
NTn3$jFgnhe`-t2a2-UjG`_uUs/QcSkaf98Q[onQ'(0R9-gd9?C_=!5XT7e&*7,]A>aMt?1!=
.5$q![jhYshrb!kBP"+>b*%ihV%uPiA&:rc3g#1aLYO9Ef)r"=!EpeG7=@Z2Ru:.K?k6m76t
+pT36M3$4'5]ASoBi-Lesb\$kSQ6m8OlY*;:n:>D[ZZkmDC]A(9n>kRpbrJg`+E4lJ8m-##R@]A
0aM+!5=)#`HramJNdCn?QTmH$?9eTG_M_[3,%>:$R]AFiXR9p3Jil3%5i;%t$0/@g1$(D>Ht2
?i!=,+I7rYepqEA0]A$6[a0#mu^dGlK4^G._&6WkRU'j.2!0Q/n2<,A5W`o^S,/&6D^s_KB>*
*.-T3qk5>rn.]Ai4)L]A`A`ZOtEB0NIb'l@r5O@%\#_aruq<5WSC'7<$86=eo=>U;Ms`63LCgs
%YRqkAN%N\@C/ZeE.I7.=jTf#_eKGd4gY`]A?d4qj/*o)ds+E(iSSVoP`WICf?U7[Sjt,)!aG
]A58;M1$*hmL[=6TlEZP.?_jda8s0o2&r\'Q#a%!Nkm\a#cBlPNoG]A[RLEV:D$'M.b1j,g#VW
h[[dl#5P4i?)jMT/R*+p%rX`A''T_OQ,I^?kLc:Of-R`2neaUgg<8=DiDcP3SZXHRT_UIs38
A1dsf#Tj">"VglI@oU,/]Am'GtN"\$f1g.:.DY%nenOlXZdLKAWHaiRG<2\a,`gj4Ps&(t4cd
o8dG[!3b\c$MrX2q7eW?[t"Pj@IagrG:`ZmEPs]AuCCBs-%H"=g1(Gb`]A8q74mHcOR6d[0tC[
$BfpOHYqX;I^frPKtl-;04@4qqi=inKK[l$dm:AO=Im^Ef4cNp!J\.pDk2:@3Q08e,f6W^CX
A\%icVr>`^<q&PY!XY'A-k@!I@R<ARgWmJ1_]AOC."5Kq%C]AV.2od6XeiPe?@W!H!Vk13<F!B
7;WOf_j51FMe!g>6%phH;M@(a2Ba>,g"L')!DN=^;Y4<&stlR5PPjT`XMWc&J,)?P4TjC!]AC
mbpAFfrcTM-KnG\E-jZDph!Af0AZ1cf=\@SPe#/i`NF:n%.$6Let!t&2k1>VV]A5mi;Cs5F$g
miUf)koZm:_1l1]Aq\no$aUUl0VB9?rQYYE`Z2RNAa%+LCXBaZgmY\7as"EU?F?&iLkg;Aq#Y
ekf\tW?I'OZjjhM\X8g"h`9.fhTZr"BJ3m8dB&Tfp_0nK*2;U=o?b,UMIB]AulsYJA9pC?7tt
r*Bs-\~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="470"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report2"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<![CDATA[9372600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[12687300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[夏]]></O>
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
<FRFont name="微软雅黑" style="0" size="480"/>
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
<![CDATA[m8nWoP\D'YLD]Ai-XOq)<R7M*4[[\8("u2F?dVcJ#79Je"3D>(ANcpi6cm2%OXr&UqW83h!Ao
)6sE[Z^[7Q-$i*$WZ"NPh.ZSp.ic<HMO(jcAlBY4^+&n+5>\n,>KErO_Om7bd4m\npW/9+_H
J1,ks!Fp(-lM*T-0,*fqNraADP!jR-BRknKna\/fu2BDZg[%b_;Q.\05juMQ__o^N_m_f-+(
29P'b=)QIGB;R%f7*B$-[*ghI0Cd?2't_J3u7;c/]AD`UihN>g`KCO&Z=T;H`hTW?,.qHI*B%
-u67Nc/1R!e^nZ.)5M'77DQ'&T7K3=coIT(lYIFO'S9dMU;AFeQ5c?RNI'/VFJW]A0'*oriKm
-CU%(W599p:9bLa&XcjA08Q4\YbS!.h9^e3>Z@`VKd$tk<4=D*[$e2CQ/OHX=^osY`c[@#m1
,B3r=pWLeGTPHm8;o@eV]AI(<1fh;n[=f-D&Gt=ke\a:p"p+r[K4Yfi0U6c"jm"B8#AV69)-f
8*)tB&)d9<.=8QI.)DAdI3P@%:e?VO-n$4A'(XJ(t#W*LDGG-L$3&21JN2T2q>+67XQT-6*6
UYT;\hObs!0j"[#)47mBLDuXKRXF12[iirm!GImFH1I[VnR=^@fO_!(U2G=$<Zj"7#3aG]A4=
U2<dk4cV1<+r1jH>/bA!_!E@ZMemAMY(npF,f<+jqLNb<!DW@]ABXYs7`J;t3DcPN5oHN?NE:
Tjji/4#GcDa-.*i_$1*nUB05>Y$JK&O$53A54A`#9F]A35'sQoC=9G6K-0/;4m`a1FqSGZPp%
p0.DdEiVTJ5)?Wj<_U`-.qW0>Jg,M.scEWZR5/Gs\!eDs,9g.FLt=!*%chX62tri6@I<RI<H
>_*VmJC4_./,BIV]A<Vtq'G(^M81Lr`:e>j&:.f&KJ0XH^UAO'">,2!J]A>3U,,d1$T=^8\'h`
n(/`^@oGld*f\-_N>,/>)u[d$]A]A2a9?:3m5&@>/[V5GBRf;bi^o)JhIq!pT$J`A!+?gd&Y=?
6un@%7Jh9uqDo'nX>)i&38A&&S;AF6+/r>4&u:orIIp7S?G`@-BL+O2r7"Nud<@#hdK'-"S0
aigWJOHC`t7$OK<;ZTQmh2J55l\dE>*u\rje:rYJ95$6W+_P379(1*<TUmpgbF]AUY%gmA+*G
`PI8ir&r,kpmBB8=&29n6VJk-7>[LBlha4!,($3Ko6;Nit>>*eLr>XU2,lgkg?>`P(l`#$,W
d=B1.X%?N'I+K%[$\aN7U>%+2MJqXEnl$QMOf[/BjIb[h%Bf0=icq@QoPV5$T.9Z1s#cR'sM
H^b)$<j<i:&O@&X]A:(Xd%]A@e]AGpXuj#;S3Y8d0-J2"TaOr3?Gfi(MOA_>nA3"O!p:s3P;2qX
Q14`G36]AN:WLgMO18O$YMi`PG*9c^mJD)X,l+<)Mp5`=T+H-H\3a6HRN[3V$_+=[7m5b&$Y1
@rja.R_L.ohLY*\VoN[.q/aE]A`HH+-E7FFBpJ>S3>.[SM,`+0Ha^mP;^&ghbQE^:El`PEIf!
-uK*#-L7Z,[T5e(:@<aPn*LCprNCYM0"5Yl>rc;o=8UQdYWK8kP\GE/=7d>Oo`"32GbY?1@h
1n`[*aS0\p,/<\$"-?+i6%U?$ARDI'%,9dC08QUo4'SDua8;\3Y\hRm2,ZTaMAa=K1"Q1<HY
-H]Aq2t$OL+`DH9#2:JK,bH'Ddf2aV6U#>PWbusc+Z`$Z.PN:_0ha3/f2_/KeF1q#:@9_Ol]AV
"H)YPk\00qL^X^@f36kn-(a2AkGiOg$6l@r^XJipDKK>0&TFFEFE\)^VahBt7OFWW@Q3o39:
,pQEO5,bF>UK<3YNBV;;I1T9(<49LC4mQ).2*&aWYgl3d3r[\m\?J?\=tjSLdrFhtS4u1H+4
D+#>atrtiuDS;_P<ZmdZEd69VuY!%3M&P&^Q!&)eq#n&"$F#!seXS&8(=]ATT(H;-SHO9OV)I
(.m9LQ-kZ`D3s:$caZ),:<uB%BQ8=e`g94-iSib'']A$).A&d>WF0tGI_LN`BB@/[fT6"`o\]A
II4%<?Frc]ArAW(PYtJg.O&DIOb4FX1!9EZMgkp`D2F&BQQ0VD&Df2lKn0XZPT[FLMj<mIAr9
F;WOnI(\d_5u:Y8U!;GsfE#q-C.3]A]AG.jM6cLok!sXpqupLrQW5UGT@RcqtGgCIS@^/hd^b$
P6:49q,04YC;9=/hfutB;GLHSKm[gKC>elu8'($]AORr%BMiR\%J#l7-0kkg3W@SlLiHJ4\Ju
=2`7o8Yrd;Oaaa:tE,JOYV0KYgt1\"j-s(hsa_7o.Vb(uB^cRT%*-K;U;?D]A)OW/J'E#H/l*
7.=eeF&I=02?G*jqU<1T>@2Hc11BP8/&E2mmRdUq!kF/(E3HrhM>u7%1n$hV7s*IjCndETU-
fo6^n8/>3+q/m0L)s()Oj`\dX2sRaf.UO]A:CaqQo[I1saNib6Ml6H'%OWFD'E6g)>g&6NiT>
=#8mEW9Kq5N=eY`R2g4&9q2B9FV:\rgF<(/dF1=Btt3ekB7Lklh.ghPH"m-uh&i_h7Qq2A$>
Qhc=jkT1uF%Z\Ab+U1$J"LrpOc8Nt!+O;M?\fXu]Al9eKX.Zk7lT^BGOVoC""?9rPR;YH=OL]A
[5HkVQQXEBu2I$G&s,T@lKe#Mpk,Ye'H!j>9.CeDjHiRH41-4D1ejLgY+!=QC'm@ZPGr&hVo
"(TE\Wm@T7Y`,LWM#&*0r%UjeCM!nK+M]A4VaISi%cgBun'C0DoJ>N<#T]A3'0p7qm7>JT,Clr
iD'O)B1aeE,%_W)<WMWHI)BscAaiGJ':T&(U$=*)nTnTnDluTd,ZKDb%A3i$4Ln#!#<V@M((
"7K=&c;FY[VS:Ku#<>4%#g#IdKF!#Y+r!kM^Sj:5h>UDkL&XC'H`hdLih=raMK"W\!Q35)W,
Pn4Bc<3,1UX)3Y:k1AY0a?'B^Ft8N(_%$]A2aIq!;^8sdiH[ttE+Fd#F!e85)q2^<s(V:IGkb
;;9O!FIdJLE&NA(8rH.sSt/l0f3^i/Pc3hgH@:F$:8DRLEl:kn;*[=&-"srbi%20mn7?137Z
d\OZkuF5,^=>j1B=r=8~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="470"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report2_c"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="350" height="470"/>
<ResolutionScalingAttr percent="1.2"/>
<tabFitAttr index="1" tabNameIndex="1"/>
</Widget>
<Widget class="com.fr.form.ui.container.cardlayout.WTabFitLayout">
<WidgetName name="秋"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<WidgetName name="report3_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="report3_c" frozen="false"/>
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
<WidgetName name="report3_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[12687300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[[9.3@;eI!YLq8<EAjpIA>F=bFjedQeAJ%Ifi$B'QJKUpEL11j`W_b;O+[T;%_!&Xs;Ac&1.0
IWu!X#jL&0MD3YelN%Bo@KQXHGDT\X8TuEng%f&q\\XIq8@r_T@/=n,8jPH(Oan\J,p$cjmf
*O2KrC6>?=kEB(!'*7"EZ5HFs2Ft-.?E&bmrU&0FOc;jm,FmSZ:7OIldjGp<g23B?Ffa&s;a
PV\7g3iF/DimT,qHN#FeHOb%>N]A.t,rN2XkD'X,0E%N&EV*`\K;:q*d4Yhq"P-gh(Nb*$QR.
O'etB.I.&QDJ<GE!Vh+4WA.K[)1=6,QiHrDJApJM\7ItthnI_b_i#5p1pmGad83\>>$<<q;r
Nu-qm-l7<kmo#"U3mNr0g68%OmYMJ?H-crF:%pk*frnqNQW?3Z:S'JOb\%gA$&W_(&qdW@p\
b!p*q[t2$%,m?rQ(8r,rppb*#EtdfK".Fb+q[S-R:`-$i1a#FpXoUX)>i.fLV?ts)Y^B82Zb
1g%'bJn?e6U"]A:,f?#ZYE#?Dr,"]Am^[mc#&jI70@XW"ic6]A&ME;B+4o0j&Qp@8\M4oUfu*Kn
Y&@On44IH_Zq!Vd57BRVYe4-3<%b.e/qg^\2IUI%IUXUoAX'_.]A$<MI_jdGZ/:;*&UAmm:1B
=Fnf@8kL!,*D)uEFCP.3F,_'#<H/Lg%92M3to^aJsZ!%U@/mSQ2SgunbRqag\(PJ\:.6-R[7
gQQt03j/hklN^S9p$:;a`,`s9CMm1gS'd"kY46H7A\N8\eK<UB8Z0)@7:t\BTgILpR]AEKp^)
B2ITr'@Z::K!qZH/mt44g"'8P=1)C,fp#YFE_<rbLGagj_Q"r8Ni]A[JMCeUP]Ajbk-0+X`ntu
h=2Q;UJM&XfR:Ts`l71>Q&3UX9Nm^a0"jTm7pPnV\ct9]ACg&oWo+u!]A?OO_ITH6Te<%=G4d"
-1NiD[YLW/8Ul2!iH@N[KY`QJZh1,q8?Da#$FD:@Y\2j''/VeNo+":D7J$/PJJC9#r<7H:*0
eO>rG(>hX[KU:+pd"`OXo1W\oacoJ'NNH97#1eO/@fBVWJ:S)E&\[)D(dT)H=/7!Gu%=7,<!
"mXeo!nI=Q-pd8>Ud3uFqWp.Vs*E=bBa(h!+::r$4q%$oE1[8qo#G"OmUJH_lC9Ku*CZ<cT(
8B]AYs-cTTkpN%7"3*:rMKZ%5=q>[FkTWr_`LGPP/SOpC.4585Cn51#du6oB_>[B@i\p`p/RQ
KmsAR&+b9gQ]A(nCTbbKJ>]AE8!JCLO$,h(\6NUILose?l@mKIP![:jo5@c?d6*^mVB:),M6BU
oVFV,f(P!/]AL]A(>jtjgH\4:R+,XbZ/9>Bb;)Z[>r1,p[*k<iTXW4ae<j"P6^f1_q5#7<JSA0
nrJgEn4`,D$rK8<7[Lr':KH&^FcUAD>-4se"EFjD7\W2`,M6[6%5r15HlI0S0L9A!*g,$(&^
HlYZ1U%-o?.XB-RiVY7&]A`%gKdQs"GND^Hsj?`XE-n*:A'!epM7S.[O=XBT=-nOr@a_&92-p
AZcAYCsY(a").+PL,>.r+[/LJ^bNp*#ZtO$LT$?=(q40oS+/a9_OJFTq,P'MXoV#Pek2FH;u
g/hR3.]A`_iX7>,hoi6Nq-KaQ]AP9e4M!YgjJ4^bTh$9C+QD_kGm*n5N(O&s<C^X"]An0&m>6\P
<cT=RZIr%iOChS&i>K9d?UFm2g'$4(<ZCuR.fB*<gP^BF#`,HX$?l>k309K4PZ2;i0JV5Afi
%I<p>>eZKK5X[Rir3[ij-oArTlrk8&/:7[2KdW\?`$[A?fWY^@JNM:i@XB!R2leDH-)Yci;F
+l+Jop(ek>cS@m-cI-5)U<,NZoMgGWNS51YM$F0-E((["qi:W6)OS>u?I+K$nCBOGhD-aF4^
<n/F$kEtO!H>"9M_!Qho$iu.Q`Kf*b:?'q<n1gYk<FW@ZmIa0hrW.%I>T]A#$NT0T$NmIqc+m
8CA$_.k+""pq")91N("ZWJ2k``fN4WDpB9K^'t/.6N.OnTkDoM`9$F%:F(e@X%-o8JbpT;o*
+;[nOi/\;k:n4)<4opbID$4[lJCkkeBZPO@@7gGTHaQe>8F93R$l'5pk?4\8D>$4f0.mmVDi
aK66F;6/Q>KuK@tDk&B+Pm=E7"^_;NbVX=L5lVP?<uOG#/L9gut,?h9Fl?cqr60tCK1mNo8m
K1<Ic4kGKUk<m?df'#4s$rdkH<+Tm3^lb0^Nm(2<';rHMY$8cAP\@FU<TB-O>'NJPV#[*lb)
eIAdKeDGR#I09<PNkH!b1FXWPlALG0b]AITkM$]APUEAE6L4MuBF&75[D9kk//M)j7:e4fk-gs
91[?;dOlN\Q(!uB*(e_u)REu.C\HrjL9!'H4Is[;kp%B%2I!s`*KU^'n$no"XoiCjE\m^LG2
]AL<0Kn(X.[(;I71nufk0/4`M8kuMPrfA9CUC1m61*PJ:Hiuh>6e]AC`jR%[RI]A(NU+t1A`fj:
IKI?"mo_ul`IFCrFJ]AQPUg/0LFq#f[D.a:rHY.`3]AK*h%KGD3j\!NWF)r$%_Kgn.?<(/"Ydg
oa#_k)t44JehSr2ZA9(oA(?&N1e4FH0^HlS`&J.8PHm%29V]AF5M4':bQk+O0inTn9=jJV\NJ
*.j'N-n:(7eNW*_l6/ECZ5MOe(X"L<Bd-#N1pbi)(2o'T\1U5fLf]AFG9kk5"6\rnIUL.dY*g
or^^fZ?OQRcY7#df^_2ChVYbVIR0>2^(#mU*XTF]AN%&N-7PQfTgnS;*WXOX(3IL+!qT2)_aB
*H(8U*r]AR\EJL.FmG,^7u@I?F&aUB1g*))"h?t;Fl6KkJ5."hhm.ORE,cJ!XTcm?:\jmUIBC
@S@4;]AaL$k#Sf1,_s_LDR~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="470"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report3"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<![CDATA[9372600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[12687300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[秋]]></O>
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
<FRFont name="微软雅黑" style="0" size="480"/>
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
<![CDATA[m8nX>P4om8e@_k>c-t1T@%,Zg@78@6Kp\a)Q'prI?<B4cAjf(59;&Mc/krXBe5BG-DTfER@c
!cCBcRWq[i?`X3AD_jTd#&,??fhdG#n"sF$/Whq9LS[F8nGMoDSXEm.KZGB:@PC_I0U95rel
R0UNmp#+Loi4<8[!q<,"h(sI;1O?`HfP[^TlHgL!A;.FfE>M71qck"=b\Z=/Sc7L,6hj_0?[
WD;aCnE6VQ"r:V_Gn@i*jW\iHi%RF2A]Ar#Zg$@*<3`EkVP[E%#$"r[Cb2q@nVaPl+fGb5KLF
H.5gM`FTeXiJgVg1-Gjg9oemCR@`L\KZf-#%,MPW]Abd6hE-'7Td34A,!(S6@sC2LK`o(LH`T
O>63Qh^mYRWtG9(RVHsKCU;$`d6/<6jgSbaC$'D[d18O?q8#X^C[A8u`$_XRkGY5\IA88hCI
u\9d`YUFQsG:8@]A,rJKq/m#8[^F8p:7/9XR&5tISfQCpaX+I1qs1H=SILqD<1/mcZ>1MX[Wf
K!d]AM[15f[fbsY)r%PgBuI9KX7H9+[OI)te57O$[>`,arjXJO*+A"!c8Aml6=LWoo.eFT<oJ
t822a3b<-@HdtCE#K]Am2#'`6:F]AhgY,\MUX>-m$!YkPaR*gG+egc!7#p?Oj^75]An#W9+knGa
,MT.<=9<,SR$G"[B54VeL,H5^>'"8+eNO=6UPjd:uMor'X%=q2X["Y<rDdcO?,2i/k,9jU,O
0%#,iKBs,heY?YiHkJC9qcVjHE#*R?"lm5SWN>j[mDbG,:,lFpd_qpAkJa.=+6fYg,To(PQ&
'WAH?#]AC(N&+Dm13<14&E)u#OLm14Dr09n[S10o`/l`#t'M`GohFmEHH>o)pZUC"ZqbLO##D
H!PgeCR.h-dW9#,7;AmpVELKpOcI[?cg:`E)+?o9pM1ofRI0snG?s&E:Q0s.s"Dsr/=_^F*]A
\DD2K<]A#D\m4NTiZL0Dek?d%`tnpd6[]AR1#YQ2$re4BHqgo';M,93MO)&W9=7S3C..)dtTH)
uSHtC5!]AY0S:Afr-gg^mn3'034KHkd1Go"sF=/kf'&=P+el6R^b`;V8[LiOfC(dWYI=#PPR1
5#C"A:mO!*/aMA<^:&=>[4"$IUYJS9mdVN$T5.%5rF;.`F.ISTU\ZE![&;hn3S<cQF\?\!V=
R>2R*KW_q\YZ$1>b%h@K3ZT22cR12]A,M]AnXbF*lEC%0.E@3qpMU7/J6hBG:QFuDlD`g\OU'[
arKh[:bf>5Lc%P)94sLBN5Yh;1oYtcD>Rr"C]A=BA:X*'U`[,WnrT"Gc8Q-sR:In5Z;&H%bqB
+!_SiDlPm2mu-AA9Ek?b3/l]Ar*+@q.=>h>'K)j%7k]AfuAd!-Q<XO<`k9\O=38`G2WW0d<De$
S1mu`>nKW&q[Kt%Cs:Vg["1d6>(2;$QG1UU5NN"KRQB^cMX\=a+'=4ON1O4AA_/XY"MA41d$
2=hE;_=\Z5SB58?Qk8\H*04$K0Lke[oj1!sN*P+aEaRHR?fHtMnIjf83tCL><9jbRq5P_2WI
#f@R_!03dKcP.4B5F+H80E&?.J!CBAZ#.`&]A"LO(01F8714qXEd%*5grfuZhqj.'@(BW((MP
i\Y;@\C'^4$ftcf&h6YYeh>:2pA9';h(OL#-8+/ito&RUZB]AkgL0\pHNLg7d\l^+eY54lX3W
$Ut(_)0)?N9n-%Wa`V]AO,`eJglQ]A'pA%SYctLt?otK^nXgVVe:4.^kbN6FR.2]AuJHf]A9;BQG
/e@*kIG+[p`o"]Am59]AAC(f?#3-_0b/-hbcp83p\N\b&5j<^MCU8%]A7MNqmsC?idjk+;),Z4(
I,oPVSROq\.##p=I"^[Q-Lo]A=WVT8DlNA!I]AMulZel\PjO4S:6O\Cs=i/7:dM`(i*A3."f'!
qFn0=RlW(qaRY6K)Yo"BrE.4D1\6?hVA@#P9>&M;CjjI#L^@!:)n8(#)O:j;j!h<,-jJf%NZ
?1ZS>9m1[43Vm3CAV9dZhqsee#*.9*OO<EAI.-B%:1bLq1dKS\B.E?n8Amco*9gRBWNU5bKP
T3dN9U:]AF7c@F$$*HkJ*'**$QE6uG9WW0EH)E4i8An@s2WKZ6`'E_ff))T=[H%1GL%2Pb^\b
h+K):Zn<an9J?4p_mHR\>Rrb(?Yk_gf:S%utu'.^T\#aLlV6;NtY5=8[&lqVF,ce%i/qcBoV
]AM]A\\XrJlj;'ZS9md+_4DV@7T'g@mop),2JH8<MXh8OF,`<\/^aW*EqT@81>G%:/BGD7g!G@
qumokhbh?N3'3Ca]AN`T`4k6>_8Be9h9W^2[:mO\BUm:5epcH..l-g-,[UNi+12E6MYW8lk<)
@S:J?Hr(QV*50Tcr\qV?^M"kM61)nu1IT:(@GV!\k6f1_\*>Yf4$*r-r^61j:BJV`8"%75Zg
't?S['d@>#cmoacqiiCUAG[1E4#:W;kC4>l"t^@$,j'HN.%&nP_1XN)HkY,)H[,>ng8Z\6bL
Jl9:/o1<k;\%,V>DWa;#&%Gp`2d0"V+Zot)*oRUf3FfFqCM,]Adl2Kq]AT>$YkTknW>[LIliE5
QHoWcH2/FlXf"UUqnrZPbrIC6"A3,/:12a+EsgQi&IQpqoo'>5@r%CP^E'k,$ED5S_']A@:>S
'0VU@@S#^'j%5=*J?tE]A*Ub:$'@CnqA/SL0-a(`/*,?`&(h=hJ)&JkYB39arhR'Yb5=Qs!7u
Aegte5i*Y`+Te4X11AUW>$ideJrJqX'K/YUtB,h=a*f%q@%AUgST,NOkKUO&0$1/Q9>asE:h
B`50"]A[R+A]AK(D=o,)ZCbF/$r$3`bZl!$=WVJi32GiV:jR[=RK7pBO+942TY2>2iMkE^`imG
5DB29h3b%j$?ia#SL1A^LH:LtLrQ\4cthPB[Q/d8Fd,/D9<^Xqrm.O;F&\^[/rb4oB7GH#Ql
BepG#A`"G1s&b%K2A.Bsm/S/upM?AnCG\u5Q#omF6D03CdV*^WVLBEXgbpKLOdLdL`R>U3FC
5Q$RPCuUAS;.c9d\<NgnL^3K]AEtb7HRI4ZX"&e!EfrYNesdc0>*Ab\&.SCX*nFQg3DU]A7%O#
+(qmEUK`^Bgp2%+-NT@`&4K-iZ%9L&d+%uhf0H/?(i5Z\(:h]A]AT9]A)rgY\_`Ae+'[^%OM;Mr
QJWi)Ra>N4C@tXkJ7\=0Gl>NP]A9/2\,9+>\]A@8?5Ji+"mr;"hn@Xsfm8YY(lm["oSVr[kMp;
GQ3C@^_?i898LkCh=nR>gS`'sF471_OkCT6t\751iP`#<>oDFX#lK'ThOeY.BZg1ci=[%EI7
dP;pjG"!=a>k)W6cAo'%;X4jBSWG;gfU8TJ4,$H^1ob8F]AsKN,dN>We6UP^SgU[_1hR>lRrR
N4V]AL)ahN#7b//[X`0@Ida4:4&jZSd0U_hZfWU$In.phZVj2cqO0no)"tl#9=Gn"aF[_^3;Z
@J^=AXIad/S7QtOi6bA?N4.(FcC]AQ;+Ba$so&F0O\a;G]A]Ae^(f,2/JV"q@peE2j\fM><Z+#3
SC67F(,rg3,:-<S-6t]A*uq&"<g,(jXg2uI,3p%H!)8JnruKY&a['AD;589X]ALEj`pSt((YRj
7-.@6`sn)Z,:^)igUn_`(>oVN"0d)<KHhVfK\#qVScPETi(LO64nk`#?"dI8De-b9BfKh?X/
,S-[KJ9(i9,Z/cXP<dg+&Xk'(Dp3@+9Fn`O%B64c04Q&RaM:G[/E=$SofX1[>`>Yu$rDT!da
sB#I?@+Vp=!DC`FBoe0Al\GkUD$F0Jn(;UEqTGi[cF0Gh8Fln5bLKOc>&tHiBZQB]A,Wsh/)d
9&e&`02&6[QA7"7079rQAEIM'*oG;gaR_eE4T_mb*&dJ8?6[4G"hV+B]A4a(]AdZ<?@f2F3*Te
_q)$oYRp7@q+l'F5>9D:VcRt-.Cgm<?RO.,O=s^ONIOO<XMJM4<5XWLAo&3R<\*IkY@\]A`H$
LUmA>n4JR_H$*Ol<lCO:@d&qgZ;FWW^4%=ut<i+\SJF(Nk^"MRG;PGe-hOdA(_Z@/C,^C71c
Eo,6(@b0RTU!&4i)_TBa&$p%;@T*PX;`k#Y0CE\AUKOU8Ta:b88f%kl:njcU)+!2cKAc&hhZ
CD#5aiE_3qf*gH6)PO9agM/Y[:rA-NI5njWQlo/*?BDJW^Jrn=I!s+SqX6jscbmfC$4M,2HL
cB`;7[&?WQ=JI8g4IN<.fj)D]Ak$eeVU-3OH%RCM:c4:t]A=;Gg#nEZ+Hqr^1YM+2J+#?@.1=I
sBdkfX<:8l+4R\8aj?-R1@t"'-.RH"A@GIC6Sbg'6$*tXf%djPLi,WQ0Gf<24>B[#,<77m:d
`UaaHNYO([qVmf?Eb^WaD.<ftp>!sAOS<itsHs5Yui^An~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="470"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report3_c"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="350" height="470"/>
<ResolutionScalingAttr percent="1.2"/>
<tabFitAttr index="2" tabNameIndex="2"/>
</Widget>
<Widget class="com.fr.form.ui.container.cardlayout.WTabFitLayout">
<WidgetName name="冬"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<WidgetName name="report0_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="report0_c" frozen="false"/>
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
<WidgetName name="report0_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<![CDATA[723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[12687300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0">
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
<StyleList/>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[[9.3@;eI!YLq8<EAjpIA>F=bFjedQeAJ%Ifi$B'QJKUpEL11j`W_b;O+[T;%_!&Xs;Ac&1.0
IWu!X#jL&0MD3YelN%Bo@KQXHGDT\X8TuEng%f&q\\XIq8@r_T@/=n,8jPH(Oan\J,p$cjmf
*O2KrC6>?=kEB(!'*7"EZ5HFs2Ft-.?E&bmrU&0FOc;jm,FmSZ:7OIldjGp<g23B?Ffa&s;a
PV\7g3iF/DimT,qHN#FeHOb%>N]A.t,rN2XkD'X,0E%N&EV*`\K;:q*d4Yhq"P-gh(Nb*$QR.
O'etB.I.&QDJ<GE!Vh+4WA.K[)1=6,QiHrDJApJM\7ItthnI_b_i#5p1pmGad83\>>$<<q;r
Nu-qm-l7<kmo#"U3mNr0g68%OmYMJ?H-crF:%pk*frnqNQW?3Z:S'JOb\%gA$&W_(&qdW@p\
b!p*q[t2$%,m?rQ(8r,rppb*#EtdfK".Fb+q[S-R:`-$i1a#FpXoUX)>i.fLV?ts)Y^B82Zb
1g%'bJn?e6U"]A:,f?#ZYE#?Dr,"]Am^[mc#&jI70@XW"ic6]A&ME;B+4o0j&Qp@8\M4oUfu*Kn
Y&@On44IH_Zq!Vd57BRVYe4-3<%b.e/qg^\2IUI%IUXUoAX'_.]A$<MI_jdGZ/:;*&UAmm:1B
=Fnf@8kL!,*D)uEFCP.3F,_'#<H/Lg%92M3to^aJsZ!%U@/mSQ2SgunbRqag\(PJ\:.6-R[7
gQQt03j/hklN^S9p$:;a`,`s9CMm1gS'd"kY46H7A\N8\eK<UB8Z0)@7:t\BTgILpR]AEKp^)
B2ITr'@Z::K!qZH/mt44g"'8P=1)C,fp#YFE_<rbLGagj_Q"r8Ni]A[JMCeUP]Ajbk-0+X`ntu
h=2Q;UJM&XfR:Ts`l71>Q&3UX9Nm^a0"jTm7pPnV\ct9]ACg&oWo+u!]A?OO_ITH6Te<%=G4d"
-1NiD[YLW/8Ul2!iH@N[KY`QJZh1,q8?Da#$FD:@Y\2j''/VeNo+":D7J$/PJJC9#r<7H:*0
eO>rG(>hX[KU:+pd"`OXo1W\oacoJ'NNH97#1eO/@fBVWJ:S)E&\[)D(dT)H=/7!Gu%=7,<!
"mXeo!nI=Q-pd8>Ud3uFqWp.Vs*E=bBa(h!+::r$4q%$oE1[8qo#G"OmUJH_lC9Ku*CZ<cT(
8B]AYs-cTTkpN%7"3*:rMKZ%5=q>[FkTWr_`LGPP/SOpC.4585Cn51#du6oB_>[B@i\p`p/RQ
KmsAR&+b9gQ]A(nCTbbKJ>]AE8!JCLO$,h(\6NUILose?l@mKIP![:jo5@c?d6*^mVB:),M6BU
oVFV,f(P!/]AL]A(>jtjgH\4:R+,XbZ/9>Bb;)Z[>r1,p[*k<iTXW4ae<j"P6^f1_q5#7<JSA0
nrJgEn4`,D$rK8<7[Lr':KH&^FcUAD>-4se"EFjD7\W2`,M6[6%5r15HlI0S0L9A!*g,$(&^
HlYZ1U%-o?.XB-RiVY7&]A`%gKdQs"GND^Hsj?`XE-n*:A'!epM7S.[O=XBT=-nOr@a_&92-p
AZcAYCsY(a").+PL,>.r+[/LJ^bNp*#ZtO$LT$?=(q40oS+/a9_OJFTq,P'MXoV#Pek2FH;u
g/hR3.]A`_iX7>,hoi6Nq-KaQ]AP9e4M!YgjJ4^bTh$9C+QD_kGm*n5N(O&s<C^X"]An0&m>6\P
<cT=RZIr%iOChS&i>K9d?UFm2g'$4(<ZCuR.fB*<gP^BF#`,HX$?l>k309K4PZ2;i0JV5Afi
%I<p>>eZKK5X[Rir3[ij-oArTlrk8&/:7[2KdW\?`$[A?fWY^@JNM:i@XB!R2leDH-)Yci;F
+l+Jop(ek>cS@m-cI-5)U<,NZoMgGWNS51YM$F0-E((["qi:W6)OS>u?I+K$nCBOGhD-aF4^
<n/F$kEtO!H>"9M_!Qho$iu.Q`Kf*b:?'q<n1gYk<FW@ZmIa0hrW.%I>T]A#$NT0T$NmIqc+m
8CA$_.k+""pq")91N("ZWJ2k``fN4WDpB9K^'t/.6N.OnTkDoM`9$F%:F(e@X%-o8JbpT;o*
+;[nOi/\;k:n4)<4opbID$4[lJCkkeBZPO@@7gGTHaQe>8F93R$l'5pk?4\8D>$4f0.mmVDi
aK66F;6/Q>KuK@tDk&B+Pm=E7"^_;NbVX=L5lVP?<uOG#/L9gut,?h9Fl?cqr60tCK1mNo8m
K1<Ic4kGKUk<m?df'#4s$rdkH<+Tm3^lb0^Nm(2<';rHMY$8cAP\@FU<TB-O>'NJPV#[*lb)
eIAdKeDGR#I09<PNkH!b1FXWPlALG0b]AITkM$]APUEAE6L4MuBF&75[D9kk//M)j7:e4fk-gs
91[?;dOlN\Q(!uB*(e_u)REu.C\HrjL9!'H4Is[;kp%B%2I!s`*KU^'n$no"XoiCjE\m^LG2
]AL<0Kn(X.[(;I71nufk0/4`M8kuMPrfA9CUC1m61*PJ:Hiuh>6e]AC`jR%[RI]A(NU+t1A`fj:
IKI?"mo_ul`IFCrFJ]AQPUg/0LFq#f[D.a:rHY.`3]AK*h%KGD3j\!NWF)r$%_Kgn.?<(/"Ydg
oa#_k)t44JehSr2ZA9(oA(?&N1e4FH0^HlS`&J.8PHm%29V]AF5M4':bQk+O0inTn9=jJV\NJ
*.j'N-n:(7eNW*_l6/ECZ5MOe(X"L<Bd-#N1pbi)(2o'T\1U5fLf]AFG9kk5"6\rnIUL.dY*g
or^^fZ?OQRcY7#df^_2ChVYbVIR0>2^(#mU*XTF]AN%&N-7PQfTgnS;*WXOX(3IL+!qT2)_aB
*H(8U*r]AR\EJL.FmG,^7u@I?F&aUB1g*))"h?t;Fl6KkJ5."hhm.ORE,cJ!XTcm?:\jmUIBC
@S@4;]AaL$k#Sf1,_s_LDR~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="470"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
<body class="com.fr.form.ui.ElementCaseEditor">
<WidgetName name="report0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="true" bookMarkName="" frozen="false"/>
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
<![CDATA[9372600,723900,723900,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[12687300,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[冬]]></O>
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
<FRFont name="微软雅黑" style="0" size="480"/>
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
<![CDATA[m8n"$P?/Y4M^=Sp!`it\(_))Gkl;;T!bm68>8Rs0B[p.?Ch?q0,1IG2"9U6K#V!.gW1\`OYp
+uUq]A_"B&lFGja:]A6p!\k;(OoS)9I`[AqWQR;C3-3B8;uV0dGMi//c[F[55&X!i_TRAtq]AES
65TghhOBa[e&8F32KR*^j4n-JUDXnnOn_k5o(ZOR%B:j>,6WU[]AIcoV<i8]A7)]AtYh/UJ'S?]A
pfhA.CNC(1t9%G->f0O8`o((BWl4M&Z37HXnV&*KM9d8O/(1-=j_88UUXHST-8,0%cl,=#WR
dRRB4$-#\g&CWj>lI3H8nAg6fSloLO5m,DW9Pm>G<p:MV<CUmAH:L<#pM;lofJ*lsX:`JFp9
E@q4'^=eR>BbM^dZs7*/+(8.P;$c.g3?sEUE(DId@m8:W36Bj?T\5Fe[0^$t*6"#`UJdLQ6f
Xf?`eh/8aj0fV;:(J>:9)Z9Uirbn$.6Q4f30F\f!RQ0m=F'bYeI+mLm!E3o),$>V/l\9;4qP
81)5,>iQ"<fiB&k"57Lh`egW5S<d]AVqN]A.SF+9/"r7T&Ng.AXfVK]A:#"+4+bB9?`4W$"srME
s[u*WJtMa[11]A&JpK.BVAT]A6YN`kiX9+lk\bm3;^CT\?Oc59W'K]Ar?jp!j`]A'\fdRYd$1@oD
e+:S1KPdc==^JWh7t&;9aY0kSJNh.kX#C8[dnK6c!l7d3Fr(05jW%Ue<;O/@[BO9rsr\`0`7
r;iQ`ad!Q\hJ?.k,^jD\C+G%WA:H!Hqme7HQs;pggHg0GLn(nI[n/6am5PD;Sj&s+b3fURX'
5tI>!<PD\i!tgT0ef-&QTo38RSitL&>99oSIs2eX-8Q`KK-GgfW=T8PGJ?=/-FbX>C=SnWuT
&i(+?kca"u2@go;Ta[sNa(PrF@!hT=ks+,_=ERf[UZ3An]ApW]ApBAbD_Mod!@G'dl,cMn\"'W
VnY=6Tc)C0e",+VaulPbdo7>:)&iHSf@jQ9!BA_C`Z$q2q31#k\Ga-gE1<cNKB8qNP<kt/We
aU@KZ*"R[;k%95Q,2K'0h725eN%9/41ITq>dl1qo,HFNNOG#sV,Y!G@H&^d5ff'+]Af+;@`5s
_`Ea;?(a<Fg7i2Qp>#M9n-D83&3#D<ZWe$uDNeN-3Ts<;7s*0M=\=L8qjoehosoRdW><)P5W
a#c;L.gl\EMelb`*g-Dbc[D!A`<4h0q-o#/YjB6"caXM(?Y+S6Ps<nnL<,af\-kT7Z6R>R;-
&Kco9e@Dg4JaPhQ22IdJ4[Kh%%@Cu%<1[$[/jHX+#9!P1&"k>I96H3<6W*t!2LRJ>?KWDjch
$?4;b)E"21@&&4\L@h!]A"]Ac0r5+/m.qi3EdZ:*uAcX"3-/j9)-&WM8_Mn1Ek`'Sb/GnirA<[
3I]ALL`.ZTg\_\5M>&nd5cZR8'7MhTF8LV?]Aq!>_bH:&`3!"1Q![qfi\-o;ilCtD@JIs6J'o9
=EItSI_l+a!6(0E,FN&/N[84E'Q`qb5OF*-mW.dd5>)@lAP\pSOSbSkB:7A34#5K%n[oS'W:
o1D?L/j#jp`k_d?1SsKm,2"(NiAHF2)7o$obI1SD'B0W>Ltn)XleX\p`q<)M:17J'V&[8N6;
HCJ5'!hPXrW!ZbkBq_<]Amp2f%.>!kPcr>.+##5OUe/YQCViUKT/V,$Y!9Q+k=mI-*I-&E.1I
e8?JU#S,HiFh+pqr9qb7Y`9ur!KR\)W6-Ul%8CqG/%SP$8(HU^::/;=3B0`JE/RV]AZ/#68cT
`*F7O+rRUUJl-b/L5!.eQ(_7W>L6@+jgWa\GGW@":Jp@jhP'go*`afi9B@8CG3SUEds_<.e%
CGe"K*IP8_^_ue=hUC-49aV=6]A:1s4Q6)WC\`R,[rR2"^8'4W/JhChPEYLR<I*Eu<;d\HIKV
DTi6U39>)9]A]A6Oe*0i_F&#dd>=TP,(Y.^8B>Pkp.Co%/B%X!0!_&H7`'o4mt$FeNkLa&^YZI
BkY"ht^=D31pY,Z[_XHi,,qd.A?i[CM;e+8m:%gCa#4Vj[UP94TBh)$M3W/nU?u7:FY).Lta
.O"Sq([MbYe+UD]A!WPeP%Td2:9s?qL1X0J_8Ap79Sr&UQ3s*Mg1ZYZBrP5DqB!qKKj`Vl3.:
!_RG",Vrc&tEX#,]AOlX3]AImY_T#6e-A7M*+d@\NK'F<)cS*#*lrKkO--BMpd&scJG.7h\CLI
A]A(.Dp,H\AX0SBMZ[D0Y)=@8ugba"tgE4Bss,9;B*5`q(k%!_9Men)c$A,V?dkioUoV?g[/V
4&4<X)]Ag&G?(g8`s:,75X6%g>Bef;$Q>hr,[?4+c%#cbBe=;.%W-#GY&C)1)'0A95A2uBF3#
k<o-n)%3ralPHalc\pl@118;+dWK[lMP?]A$Fn[tIa7E"6N:'R`k<Qas*)OeVR!.MCj9fQOIo
bmpO\H4);>DS'o2%M$bMT6+^J-?.6iLI^7\Kp.XhF_4D7&''3qjtJC^d?E&o;26UU9rMIO<'
Oah=Jn90:Nq;25n@*-jc=H,SgZbi5Q?lmG9&fUf!f=4.<B90IbD:8pWR@^_*rjoq#HdjC,cJ
^-kLaKg868=c]ATV\&#.Ke>`JQd7jEuAO@5hSe4Pgh>O'r<@*GFAJ0)KcL>P!VD]A]AfYWU)QCd
dkFh0[DOaXY[iHBU<brZOj8o74!/DZZt#`UCaK_BPhsiJ)TZn;eW-S$jX3LtVc:5:[IiYIP&
TV?RFL$b&YU7o(7u5>g!(#7^i-Zuj7\Yj.Fi\+/FP=f5]A0h"iM."f16>Z#!<7Q9Orn::7bp`
$`Kg10%..:D@`D[K0o(D`flV6*;?KoaZL0eQ-)lKjFE'9,o!S$N@q8#`X<I+B<u?a4e'F;)K
n&c7jttP0*_)4'VJAOr\=4PYHL[!HRl/FiK!5l;.\'Ff3ZM^:_Zt/S-<(ihS0XH*Et;'.>ON
+@"!RQ[bBC4i>FJjqj\R(kT&YPOTa,_5rBJl(edX1:0V#Dn5Kh't0#(U's5.GasC!oe=#VLT
GW;H#@)bh[PS=XoH4CW>$4><,_McljMgBf)f=!"VTFTbc,70%jP]A&PiB9PV9B7K^$nDP#T4\
JhYKS6>dYuWB#/TiC.<En"D_=n1FH&"-6U:V:=ZoEbc<MMGm>-k`o)@C&ngmH*b-_CWG`;C2
9>j4ch_8:!JWS*5MuV;B3fXVE=Q7KN:,0V;VEWbh_f$RA0ArnMRJGG)N5%-&HAgFQpl$O5_8
L8,Uo]Ad[an?ZBf0]A,h!P9^KP;sNYnI=amcDT!Z7;-R0Va=d119(,7@Bbfn6FpM8_Hj\GR/@o
M'"*'cb&OZNdiq>RN^ufR.'I[r%cnZH*F]A%TTO%P06_hnEO<q#AgCHr;@OTui!ZQ;H^tfIKm
;]AE?7>2"3Q'7Ub'lj]A^LZnEaQ*`7J?1sYff1"&m:6?d6[*!EKp;jdH%F0_ifs!P8c=&CNX>I
1U(HuNQ?4Y;_aSX6Goo$7bsE[s.r:07*YqGr<A5HU9O$;QXap=7(,^7Z3^gF6@q"1$1Aj]ANk
(Q;0#Y(A,$u4jRQTTBabbY,/mTIX^^BCkGY-!s_:59&VE3[J3PQUNS4+e5+B3"DKjla[6eR#
f6HI:Goo@E6:&`D!Qe]AT9=o3KRi!r~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="470"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0_c"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="350" height="470"/>
<ResolutionScalingAttr percent="1.2"/>
<tabFitAttr index="3" tabNameIndex="3"/>
</Widget>
<ShowBookmarks showBookmarks="false"/>
<carouselAttr isCarousel="false" carouselInterval="1.8"/>
</Center>
</InnerWidget>
<BoundsAttr x="0" y="0" width="420" height="600"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="tablayout0"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetScalingAttr compState="0"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="350" height="500"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="false"/>
<Size width="350" height="500"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="1"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="5"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-13421799" horizontalGap="200" verticalGap="100" valid="true">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="a436a36c-431b-4e16-b9f9-179a9f532552"/>
</TemplateIdAttMark>
</Form>
