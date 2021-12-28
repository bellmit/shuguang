<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="File1" class="com.fr.data.impl.ExcelTableData">
<ExcelTableDataAttr needColumnName="true" isFromEnv="true" filePath="reportlets/旋转图标图数据.xls"/>
<Parameters/>
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
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    var $report = $("div[widgetname=REPORT0]A");
    //获取对应report的div元素
    var $scroll = $report.find("#frozen-center");
    //获取对应report的div元素的滚动块元素,冻结为#frozen-center，未冻结且未安装自定义滚动条插件为.reportContent，未冻结且安装了自定义滚动条插件为.scrollDiv
    var flag = window.flag0;
    //设置全局变量flag,每个报表块需保证各不相同
    $report.find("#frozen-center").css('overflow-x', 'hidden');
    $report.find("#frozen-center").css('overflow-y', 'hidden');
    $report.find("#frozen-north").css('overflow-x', 'hidden');
    $report.find("#frozen-north").css('overflow-y', 'hidden');
    //冻结情况下隐藏滚动条
    $report.find(".reportContent").css('overflow-y', 'hidden');
    $report.find(".reportContent").css('overflow-x', 'hidden');
    //非冻结情况下隐藏滚动条
    flag = true;
    //定义全局参数flag，用来控制滚动的暂停和继续
    $scroll.mouseover(function() {
        flag = false;
    })
    //鼠标悬浮，滚动停止
    $scroll.mouseleave(function() {
        flag = true;
    })
    //鼠标离开，继续滚动
    var old = -1;
    setInterval(function() {
        if (flag) {
            currentpos = $scroll[0]A.scrollTop;
            //获取距顶部距离
            if (currentpos == old) {
                $scroll[0]A.scrollTop = 0;
                //若已到达底部，则重置
            } else {
                old = currentpos;
                $scroll[0]A.scrollTop = currentpos + 1.5;
                //若未到达底部，则向下移动1.5像素
            }
        }
    }, 25);
    //以25ms的频率执行
}, 500);]]></Content>
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
<![CDATA[1333500,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,3200400,6324600,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[轨道编号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[图片提示]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[介绍]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O>
<![CDATA[海洋馆]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="1">
<O>
<![CDATA[珍稀海洋动物展览]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="1">
<O>
<![CDATA[小黄人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="1">
<O>
<![CDATA[可爱的小黄人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="1">
<O>
<![CDATA[动物世界]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="1">
<O>
<![CDATA[尔虞我诈的动物世界]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="1">
<O>
<![CDATA[漫威]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="1">
<O>
<![CDATA[美国队长前来报道]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="1">
<O>
<![CDATA[天气预报]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="1">
<O>
<![CDATA[接下来的一周万里无云]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="6" s="1">
<O>
<![CDATA[豪车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="1">
<O>
<![CDATA[速度激情不容错过]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" s="1">
<O>
<![CDATA[菜谱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="7" s="1">
<O>
<![CDATA[有了她再也不必担心吃啥了]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="8" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="8" s="1">
<O>
<![CDATA[游戏]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="8" s="1">
<O>
<![CDATA[男人必备]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="9" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="9" s="1">
<O>
<![CDATA[万圣节]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="9" s="1">
<O>
<![CDATA[不给糖就捣乱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="10" s="1">
<O t="I">
<![CDATA[4]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="10" s="1">
<O>
<![CDATA[天猫购物]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="10" s="1">
<O>
<![CDATA[是时候剁一波手了]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="11" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="11" s="1">
<O>
<![CDATA[海洋馆]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="11" s="1">
<O>
<![CDATA[珍稀海洋动物展览]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="12" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="12" s="1">
<O>
<![CDATA[小黄人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="12" s="1">
<O>
<![CDATA[可爱的小黄人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="13" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="13" s="1">
<O>
<![CDATA[动物世界]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="13" s="1">
<O>
<![CDATA[尔虞我诈的动物世界]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="14" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="14" s="1">
<O>
<![CDATA[漫威]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="14" s="1">
<O>
<![CDATA[美国队长前来报道]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="15" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="15" s="1">
<O>
<![CDATA[天气预报]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="15" s="1">
<O>
<![CDATA[接下来的一周万里无云]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="16" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="16" s="1">
<O>
<![CDATA[豪车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="16" s="1">
<O>
<![CDATA[速度激情不容错过]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="17" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="17" s="1">
<O>
<![CDATA[菜谱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="17" s="1">
<O>
<![CDATA[有了她再也不必担心吃啥了]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="18" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="18" s="1">
<O>
<![CDATA[游戏]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="18" s="1">
<O>
<![CDATA[男人必备]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="19" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="19" s="1">
<O>
<![CDATA[万圣节]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="19" s="1">
<O>
<![CDATA[不给糖就捣乱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="20" s="1">
<O t="I">
<![CDATA[4]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="20" s="1">
<O>
<![CDATA[天猫购物]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="20" s="1">
<O>
<![CDATA[是时候剁一波手了]]></O>
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
<FRFont name="微软雅黑 Light" style="1" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑 Light" style="0" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
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
<![CDATA[m(I[He(;&^Cpa`f($c!',]AtR\6WB9f;JWB:+;Rqr&2=Bba;?$.)6A]Ap)<(WaA;Cms.M<"ZJ3
]AhO6jO>-2$O<u!X)%b#_>\,$nqSXcL]Ah4qMXh>h"O"q3VV=`J'F=npLgV9fD=jAf%s^pEW#=
KjkhUrY[G;iUITe*=g29GY[G;ahj,j^rtfYQjNrJ^l*l]APGi3+QOf6C`QI'b_=GgSF#*URV=
l*,AX`VGJkWKG5k+q!PESp`!D0jC%De$7$G3rE">W/+ONcED4fcgeiJ%85\Ybl\$Qp@k;l&"
NVeLHqmW42N8mb[X0>*_\S6dSVj/?Ggo[lSW>AN]A"TZ"uEg5A[F<l%jWhlR[f8U,STY!h;r&
1N9(<pY!=[*E-VA.UK7IR1ftRGads!iW%lg5<lU]A@&o\/16;0/ioN<P)Fi#%q";tZX$sN#en
$E%.1(/tgP?R)ns:(S"LXBbMlVckl2OBa)*BM&1\=9N>ca3Uj_Ad]A?'EhGmDHchH5Zh75gUU
*=u1@]A@e'N5A=Ecu!3s#Pr<PD'rs1Hi%(fs!pH)@Pp;;Z`f1HdG:6W<mLSo(&]AOg1h/@Mn/a
"VZ7\+?*%G@D(5;J&E9b-a5ZMihTIO,%=Rb("m3Di;-s:jL<eS_8Bho4-(upeIn[HT#MIHRh
F84!DY:<oQK,gAnlKDoXcqSS^DIl4HdZ1F>'o]Ai?)Ao1<p#\51BtMd[Z[icL*?^.iapXR#9"
)Pt:\\IWN+/V;K5Qg&M#Akb_H^3K08^bsYje.ELaUVq4ll@rP]A&\$H=obo'iKH*J<MZ4<0F.
;R?*4&]A7OWu"gE^hV>pDlFK76]A`pR31'qlTa=s4*@LL[5h-CZ/>0q2c"s;%olB6$@ftMfqu.
;ij%g^s-/1if`*#n!<l5B$&]A*f8A1rQ73S]AIl(R4<$D_\Fh7Q8Pb"N/:DUZ%ek(WX6V"8BH8
9E.uj>-(&SJGA</LA4&(@h'%Zd#Us3iqpbd3H[Mr<O:<QCnB@U[Q9MB\[/;^&;_p[7_#54YM
q;_%F#_XII$>),Ae?YAZ=&S$dU(SBo:pB(3TG^RLb"!PGPb.3-F6+\97EQf#W/YMl9PaHj_9
LA5rro;=eXb*eUfH*`j=k-$^^Id&F*4]A:iH;(LY745AVgChFK#.mA2[2D:^d"R3Z[<MsG"dc
#[gXRfs"*<('7iG%A`fbT&trS.6V!T=P'A1;mqM;S7"9^to4el5lQIt.5h,DL:3Qi)mX@=PF
EX.9bmqTJU-$.?r^d)!`o8r0':^[6<u[]ADVh,<-]A:+-Et/bnT$VS<8oM.s]AJHO<"tgX_i:Gg
$c*.9oj2kb90iS`X3;@m2kDT^H>_#Eb+di7K9Y*H-+W&)qt0dcK-_YAX,!Qq%u*9_M@Z%_7s
f+#BM5/%rZ;Fs3/Hs2unI(-9Qm(7rn!&h!Q!Yi]AOLajHr]A]AO->U$BE#33o<iFSNl+uQejQhZ
s5FKlr+aV+RG1I!<D3<8.Si50;WQXHCOT8B:^WAaAZLXg3iP!$SR&G4_lX/0P+4=_iD=`gF0
Tpl1Wu^j2LBJ!G^FP0W/R,+I9]A#fUe.T.+JPM8ZAiQId`tGYP?9oX1h9Y?!gX"1D6uCd/l#H
8TWKsgc_)q&Xn9jn!3FK^n0hp\r\VU?l`%<2Nq=><0kjaLAs?%nS@Cb2<)kA$"$:/SB4YWPq
1u)'6g;1$":jXRL.FSGNB<gmp->#8d7'1VVn'&YX,sKbL1j&m`kb0j]A,?^Edn(&Knp;S7.jU
(G2<2HUdX-le&s]AZ6;jppR\JV*-WSnpi!?N%Ps!UuQ5oA81I?h=b=)R\ARlEDr!X?Z=-H:A9
:*EMWM-uK(o[=BNM8M'a+mGi;7<A`PN<,<54Vfc$Sb*+>cXgFm^EA3V9_&tBf1_+IIW.\@D!
i8Pc),l6V9gG'Io9$L'UmY'?-jI5@&M:r`P4uR+gCj2]A!(.Phg4%##"+]APj,g]AUFOZTZRFBL
)VJ;73JA1I`o!OLT9k>unMu;)1/i(^a@2UDgqcGAXVR^Z$;J=RJ.0r%V.mVg2CJ7@V`OmMdA
p7X<"B-?S]A+/-6GZi8oe<u@\bQ0YN\=;ICN"gI#(ct>J;'4.j1AI"W==,hqk/E-NgN+G1ook
&Sqf3eT(XVc##CQ>a>52>USuY>IG?3GrRU.\sjefiVBob@c*iZu^]A@sl$SrZtq;Of5TCF_f3
;Dp<o1\p.c<,qRo)eK7f("TVokB@r?8$b:"M2<mEOlYuen&u5#ng-Gu?X"$:e9&utkZ.cQ@T
)*-3!Vm-ZJ!#?*F!@cPiBkOe2\8@^rm#>,kBf35?LZH`!u^IHV&s".tgK`Ak-87<Da?qmNn5
,CV<d-"aM;$bTn:B5")t&<HT?U8@4/:C`5ooE_Mh.\#HTWQC<_CbmjPcT_3q(&-418=fl9cA
qfnP)HV]AX",8^IL+NsFD?Y9<*XV_$,tJ`+>rm(Y-e_)n\_>._)HBL]AI*ge7&hOJ:?`cG0<#`
loa1*C!qI0(%ok-\]AMI7A>\#j$,>VJese&=%A\edb88'Km%eXtjn)`#J%_q.OPXKW!7!ht;G
Oj#>]AMo00TkKLX;!>fXh3?cGbqEfi^;Jla3DcAKZ\q>1AZY,Bfdq-7'Jr$XDE\u@F"P8`hgG
M>@K04aDSo/_U']A&%WT8D#*9ZZ#ij,eK"jk-?j9JF+i_Sp+iSbqs)Ve_2<E6#-u:1t`\RFLd
5k*!+#[E6YDD[^s&)R#!c@KgY!%5GGHJEc&<E6tMfX;-U1Z]AaT)S@&]A19ahR)Nm!3RAQ'ZRF
&#1R;O<jU1dpASQ:gJZ!'*>1^iH3fE(sY+Vp[,g'nPq_>t]A4+HM2(8"oL`ALh+<SF`-HZ:*:
p?'?HAX#,NJ6/9<$CH;']Ai8e?%&#bf[.I@H3N]ApQ^Cj&48!>2kc(OepMk(87B55LFn*Xs8br
mT7L%04QD#@br339t2;e)M8O(8l=cLVZ:OQ?o`Xb%0S`/_61S==T_Vj`]A-SU1,O&<&h%1N"g
ruF2HR?,Oml!,)<8'RBQQe=&="7C/'&2<>W#fHcp0,al$#.NZ*\7R6M8tu6J=9i^-gMKp`B0
'6lO%iUnrdm)dMX8MO+d)]AX1bM,'@-hJ:,Ep:bKjHi+59GCN*<o<U/>7P]AIf:3ngLpTB+M.E
KQRPW<Zs<0CuVsBC3Z!.2S;Ne]AKT%d2IO'NNP6X3Ccr6,ZfduS%PS=gmu5E?fOkMFm@4YdTg
FKP(Vk-7NQs!+nFt^GJbLXAGEb:8/E(.pK9b=OmKgdQ@S<JG5B'_Q%;L9&5.NqFPFtuhW>!5
TH1^(!6DE#[`Mq7NUYQk^/fc/s%8^'I:daoB5829L<r_tP.!+Y&(8TA;_-8gn/<Wt:I*>N?$
#qd>j]Ae=X8.$dqsq/l%6/dd+(Wagr[qVZh/#;)qiEi>on@7eWINP&`hiNZX_mS<3peA?-?7a
ueG9RERGro]Ad?pS$=[]A$:pPHcJe1RDa9deSS$V<5*?CO@jf>288>'o6(eT0YIVTl:,8-)G.G
1/S*gL.hLd0DXWZ0Q+Lp)]As]AIso8]A6I;S/rs4TOgRXrF:Y'PCHnOrZ-X_a<p`ar!4IZ6PGhn
HUqT/>Q)MNp-JuWuY48a[hNu#,jQQH*$WqKsmK%#nT^jl:\d,Z]A^et\bMCp!A4?/n%S]A81b,
-bN'(n7&]AsA/gaZ#+cR7h&o_P"S^\`:lIM8pc!qMen3W_jF9pVZor:'eekcRlogDJrbi+^qZ
prerI2+rd6%`29&21]AW-1WtX>BAOp&6,AEtj$2`[6"Ar6UG7G;42b,Vj8`JDb61M"<(t^.Iq
$7sH+^#a(%_[I-l&7Pfag:^>[.CbdC?rQa\Z'kPj7JTR(BP(1ef$g?l;ce.d==c\fK<fCPY6
(e);Lq<MiZfM;TlMVZ?0kd$$qm4@^4AF=$fcplc,i$4ALaV+_BP\;_l1UFT>L_.Zp+_L\P8e
Q(GCijX1E_S\JR!*>.nT1*L>L<@]An/+YaNDV#.h)hYce2uR$>8VQgH!t`A6C'-6b's\<>VD'
@_B`mjS_O*Pt7AXls"TOeCXn;S=.bXEJqRBC<qiJ)me4e_i$[LHu%%q3dL=nf8h"qfUNP"7g
H\>jI;J>b6>On0+QWFH3+iMjq,ec^p%8>P+Y]A."PPj7fP&f58!C96K)+kGii577X[mDEA;\@
PRs;TNJ$lrO)87p"_t?tq/cbGA6[OfVs!H5k$m,QD49/iBjOE"eP&RJj)Rsd%5!6o?3+fP-s
8A9>iC:SASQ=$;,BeH!?M8g[F%/<mG;+#Q:,p&hd"uVl)t#(l8_o!F833SH6d2k_=6k+*'G;
ZM/uh^g$Th*MliiX5[%<K)6]A7Q!V!m]A"'G>l8b(GXK!6r@"!e6m"W6:WjeK/3'QApY`@l,We
7c0lk6<u\n3!`0ei04J*[&EYFeoBFj2BfgXE+r?C@+`^;oa[6'ma0a7pbS;3N_!ub5mu5pTR
Z)8RGE_S-sfdr@1t`aju5#Oj!;U?1<!W=Z]AS5Y*_PDI,FpZhTD8HfMhMnFVe:u+jWJTCdK-,
n<cb/K<7RW8[0>tkb1L]A;%Q>-(<Dk7hlhUn@N^%t]AjLkh/?,R&(9,>P,oq)m&E<Crp]A=HPSp
L\4*j!3>bL\:a73roBYBTdL32F4sMItpY),RsS#>Jg!_Y*@qu^g"OBf_=N1nL,s^Jj@\=>Lh
%MjiWIJ\p0(/cL8P=/2&>Tmd!"c^h3+5L5m4<,/tG9__H(+<A4"tABT>a55Eh[h;C,-5,?/F
r(UYD%coTW#@:IX>J3)Nh(l^B#]AP1W^-1;S*_\Y@5sP6DB/E69"?u@Y6Wa$Qe$HhGJ_V3T1F
#Y4FHKspCboE!2&t`@WQ"\B'G5#*F[Gd3>Ng8W;hH6mUq*')'CuT$GuXH\M5/'/;B42Ic=[C
n?(<;mU6PoP*lQP]AV*gs]A^PSq@l`G3%aC[EmP65j7M6Smdg`W%eAZPJ^^MH.6`0S^_Bl>hnf
]A"H2K55-A#2%ID"D$%SSBn8GYk2U:SUF4la3PY2p^/fi$sMDKL4q'-e+@Z!FV"_a`QotE0fH
_G<*Ei_s7gD[YsRp^KGl]A":U=\#D#ge=/[/)9>'IA5M5-Qb7fc.77OLpAfI,+I'8;Th\PV/u
ZbOefbhd+5fOcH!C@:E(9AYEbP%"k3Da1uB%8kX$\dPOYM]AEMBJfWj_6$<7#^KB/:BBkX']A%
p<"K9ksk%STC]A">J.io\kj&R+/=cgp&ds$h$s>%o_Ah82j`*16trkBMiOmi8.N#LXS&T)h)9
PW-u7rr5i_JG]A(5"f$0p!?CJl``Z07KgfEQ&?fQj#,kuh"aX./9[+=Y6.72LhR8qh,^tajMb
7:BJ8G<U0JTH^dLAc(!f32nj&>7]A%q/1D`:EB+&%4;#4g^c%V,5$)kQcf5rhQc]AR_4Xlg\e*
`_WfqI0A&]A79Tk$jEDL]AO@DJD$Wr)grQHXqR=^22*&+mF:[[)77UI\%^=_V,:b;gm?q;F[eV
3>A3k\6gOF'Be+L?K_8tea\*`D[J;PeC9F&i_;OrpNBe_Ih^]AX[!do#["<A_EN_J[`9b6?#l
QuD%K+O=Bm_2SLFXZ#YY=s`m@H5B/Q-Nhr#_F^A:=58bd#,GSY_urIne"^UkAXdZLQ\aQ1LU
TV2MinT%CrefiM_ulc>76h&(j+I0P&i:OX[pY(RX%:^VI<.^okZ+`,r^nJ)'SAaPnXL!B:4Z
u06<UN+Ip]AcrdL!pfD#$Gd%<..1c=)Mu&H:7Fu)7du[OD*ETk#BcE"KZ9E]AA1dob#5:LXra.
(t;QUCc#i]AlsR+[@F/FB(?)o,%VhRB=o%b]A.F2H#Ic6KpuVaU^1FaKN7E.*uJ!V3o9Y<V=+V
^LI7(rmma&o\lf.1+oE(-1L>X#9,c0CS)pD&)Bne7hEc@WWGJTdJ`on@iQ7W&=<-Cr$)>F8V
FIq#\*\djKok?X0gb%:,I8MI@@>sp,oIcY9?PSY8/U:d@[s1kHg__5kd3W+PgH>J27q08@&A
j6UFED4409MVHW$iE<*bK(8S5'_[ITb/V1EYa)JF1O;k[71FdGG)ch<+]A8XYlI6,#l5es9GK
_cYR_ScqC#_bm?KmgNB#4X5)i*gD:'m#Q+Y<P6&V1XfTJ$KcN6gd.qI.'UT(a=]A=Zal&`8&O
tS.WHn+k*A8^ktJ:g0(oh,%,PKY?bcIM#kW[d%Y?["WE=0s2:N1)XWu:r"@Cl8!L?SaCV^%O
c3pMJUp9tqW5J#>m2Hle2!Kbm:jn4H$-,-!]AiX"u+#-p#I]Ab,PoHVBkL(ZL6*AfhXFJkm`7=
Nn2h?OC&!(R^UchU`0>uR-3lA0j@@beh*%D^Ib5doPSj%4h]A^'8e3N]A<K\Te"rj\%H@2iWW"
TLie:/pSr2LF,4hf5`5XAZG`?`%nt55jUtc?U0_`'qNE(QGK?GRJ)kD:IJQ((Yc'.a9\=l^D
B4O24.uQq=BTgQdm:f9_7q+M]A80$#Gtk-)\MZe72ZF,n%.e9;RsC:&>h5ZK.7C\%k']A,4@jR
P4\YZHGM@7)PL'`)Xr"TAar4nsoIRphnZufLJeMRK\.I:FT:XdsPKg)0!(\YkDB7.AY!81?l
#,9/'T9qQ78&H[RNu2tc@1RW(]AURe7SJ-0u^u64K+egLnnD3mQ?YOPTe2/,,Q5o!32sWQtGH
h!`!X3BHkVqeTFNhb3Mc!W(%Nj(8ED?HAY]Ap"K$g(lp*-cR1_`0FIf6#4Jj3Jmb^\t:)ru*;
>(8WX@1>T`mGL1>\mmRNN#ht`acBs[0X/6hD\`6I2=EtPoB7?,F+Hj4og$,cB#d[EX9<@q+I
%r92eT5uJ9!(\QAb2tm/Em^Mi']AP@0R!d_L9fU]AnhTe_J)^"T3m0(8TdM1_8n$-k@?T`&q]AN
7Gk[TKBYJ!4pUG+sToQAV*HKFq]AbQN\1g.ES>h"5iLZ;-;&TfFf'W#VDd<@V#kba7$KE3/oR
L0\Z@g>PhL8;:5LF-5SW1qCfb^==^6L&q(NCWZ`N.YjW4f%[^#/Ib5/Do<\,#X5+(h_h]A!@4
+\n0h4g:0EX99dWsY'orsts2'p=!g!gQfqVm5Ii1;&.4kk.sc,<U)2X!d9jSX@GE2NO/bMVt
.+,$^K$h\p1[cY-n(=:^bO0I_nf1]ArB[&oqVfk?:7PU3du=l!h\PPKqK*BP!FT@*FJA]Ah2/8
Il.c.WO,0I,T(^#hVlPFm+k)mq=!/:Z*VqeIueGR-$;@$i"l1WJ/loN*Aam6J;2]A9VN,1k69
VRakbasL<o,BbGUc>Z5pmV`CAhcHCVAnT.FYg5!P*AIA0:[3\]A+$F$$Vn:IiI)A;Df@gc6g'
'k-\(#J_JN9RVQidp"T'9.h=n.b"$-JgA7e.TT=NLBfa2-<qa*Z5k_?/R>oUrL$&nTKLRN`&
j)@cq@$f7>*n?KQV9"GE&=o.u%[nOk7BIi8'L!W%^@F>=1oiEKO_!TmF_8q%NL:BH'^1lepM
n)h\\9)6rJdqWJ57W?8_jNq6d=_hjFt]A_?Ro>^_*81.&8ki/\GmN]AAclj#$prgL]A#hob&`0n
:o(Z:nZp5Q),CW)@:qAM%gIAq:te[.9sW2c%gnbIFYOY?YA!X$#9A^[@Zbr8SYF?O>?cP+V[
PJ:-<;E2"kb(2:8"g'+.G.UU8]AdXZV$*F&[hqAu_lg1raepY#]Ab8A%S/'0e;]A*_2*m,K>)h@
E`k(MobjEIK`9uYr1=Z8m1gGg3-7B&UE&V"1I'9-gmijtg=Na&T]At;6ed%tpJZZ^(mE;C3IC
h1#2Su7a;i$f=5O6Et>PVt4_NH&;'@L*\=DSfth4>M%JbY>9&osUZVM:84j^=-6DKFH/SIm#
\mdJ?V97&mFZEBn3hqWC[@jeSa!e1W`UA.SbN/"L\ZkOcC%3:1BY\&c>.6J+cojk`J'-f)'P
7ptLP1TaJZK\)?6a"HYGb2!;k%ip;2O)@%FN3L^6u?>GeNM4h,<C'3?u-nPQ)LFQPP47Q_)E
OMBB&:Sg`^Np.W6u]A"$qt*c?r/R#-4KA<Ptu2kuq^;d>Xu;'#NO$otj]A\Bl\t/:^^0+D$Y,4
6>r!LAdM-XUaplsG94#&ND-a[CQ=`>-Elm*E9HSYLo(r,DrIgLH=CPN`I)sLrIj4T:-l=_/R
VfmBBR&(hXoBWfgfN<cW;g:b1\=nlpcD5_&8ggYA98i5\rC[N1l9oT>K>?H>Mj:J4ldW%FPe
R6qa)i\qaqe""6i"ELu]AGTSo5RCZ:_J6N&6Gq:%SOmbn.!Dq7D*"'<5KeHlG-\,@?>rd-o@6
cdgC=6SK4Ld0R1gRW39s$r&_\FZ=A"Uc>uHfAC59q)(%O=#H-k<Bc?WpWLJKUFI_cVT?i_)8
kg9LjQf?07%YQV8p;UJc"@Fd7:j)`H$$kRL&;M_I!HF]AOGWX]A,kP'%RGD4f!D#n2B#3rKJGW
87pbTb@AkeJ?Kns*\Q&f6a:Pb+A9`-Q6PX8.FHQb6KY94Wr/;jX_qJX0J-n3Y'>up[s:,O[,
Ak+<'Wrij8#]A!`WT$I*9,mNG$\`P%[Z=pbYb7F`(V#N60JgcOY^EfD6c`3/!6/^2p>A5CRO8
roRSR-VYPqn2T(Kp/t_/ISKshuI32jMA!7ZugtlLoKPDUAfR3e=0-6\&["f)MD!X&?O?Tam+
]A;?`<a#dg*2E"AT,%DF)>DhT:fA@0(01_<niA!58A3_'."d<%P4tK!;1ia]A?RA-^.5Fhi9Iu
kZRCiHdE3h2uqjNJe7aQ4+ES@h'^!AJqV1;GS*>>["C3n>fC>\),Fko:r!YV7*#5mTj]AmfB#
dUduf^[3XhP:tIp@,44dfhA1HfQ4uA_]AWRTW8S`+=/Q<.kMY*_>-8kGkO;RsN4mQn_hpEY<\
;m<q%M,[R_C.qT<Bcm(]A<>`!njMdnUqcCER2>N8l5m+>XFF_058lGVCcR8:s[ALe&(1T"j_5
jean"DZBfg,$j.>'?@@>]Ai-RAbaN2<2$Po<]AOEcH%WDrt>N(jkh4\[$_H;%S^TaTe("^6,:<
Mb551n+>'`o;3iqqWTuJPK4tb*fV)<<712^!XAlJtE9p[o`[-#^p,GW"q]AkICUlH^IeX_@#u
ta9=2(T7:<CQh'N=>dC-6Gj"5P7DiM^O4lh4&*Ui?cXTqF"B>gM)1_ML.es<cl025c:Ba%R0
L@VNT#h+9j1?j4Gl$mW#;@Z<VKq<u4qh6qW?ffq#K?5m`NSpJHoP4o_h,A?aF0An4Hn#AP,P
^T-U2Mt"WN6!:4_0?a'mdDCc6QOC7LEptguWeP3i[Sg%Qk$B9RchJbSrItjeV%*0YQ1>MK\^
2FXR38-7&!gMEA8]A<Pf3Zd'jK"/r(:N,5jW*jh]A$YWj?eaXaAO2aa>0rklbU]A"[eF2F,Z=V*
%TSn@*7Hm]At1[%G9BIQ6nf/<l[5^djZj8c_s"?W\gS%cbT[\Y;]A&*N,pN;-.#3'0[GMb?)Z@
Te&n&In@eR(Q^6Zi42Z3PJffEpkI/Hk&;)>suSVsC0Uc%+YLTdQ\ll&/nng6n+(UroWk-Vd[
?b:2n<lSBL0I*o/%n))EkmN:'+uK8bkJ?JE8N:+7jnG6V]ArVti1YH*Qd3fb^Tenr+@prZs__
3^:m+[bqRiHf<#fJL;<L2H2.97iSQgU.rdXo6KLD]A"3f);O793pdf:fS2o>2kFg2@Ofpi\`O
^OJmCriRIB-XN:n+*Hl<<eE7^*aFTbGWH/hpT3]Af.X)GQk,3MJ-85<fEg1aQFbI@HB\_\7id
Z7J]AAGS\JjE!A!j.-au;/e&1J)p,GV\KX0)-fILUZl+QATRa30S_5o`p1Z`)/!\MpeH!HVSD
b>bF"KjOO-U)N_%'thhU`NrO#!OL@4B/'&\CUVa?"D<ZctM^(?ZZ(DK4uZaZ.["KdAsAY>Eo
Il$nKm%hNc1rcbt`t_VfRGO2+#EM&o_:F&Pe]A(]A5,N_Un,]A-kg*]A!38P^DCKJO3fehHQuT+L
.*:)(Pht`h#]A%lGd4XIO%GSJKgu>&dQfOB2!MH<]Aji.0&`#GS9Z^D1(I]APiaG,T+cZNNha.)
,FO]A3jq-Sn$H]ATAs,rfO&oGb42KmK*=1>0Uf,G6cSP>AN"he]AkH;5LX9I(aNHE+?Hb%`Oj:`
=]A1L,E+*t.\7g4p)'s2N+Ab0m_Df,Q4tr:m$0/-X?M%sV`!%)]AIOKU6)g$6*h!R\U`em;B-l
+CYd]AZu`bple^^Eo3S6!<<1c_,t:gr.7=[K+mhBI^'eQbaAQ;;T"gRXcl?PIi,m1I90dXQ>q
?6[cX%AfG:)O)<r5#g`oZorc@i^W6-6%,0/md+YX[:b!*<2udlP71UOm]AjaU\@j*60k73')/
C:YUC]AR9fR?p-DRMH[CtNT"SW/":T.2_k84`T&be\sQN3Rk<G@;"Lg%_!U<-lF/igA7cH^/Q
`#pdtRfdmKc'4F18b"hq4erqZL&c!LN<aYU"KpOTRD='k8Q;c:&V+&XbGL98]Aa:(C6m26!6Y
DWKh:6?]AOi@l-i>hPQ[K;%LkN`2j8Y4P?'e0K]AO_:P>$UZF6S8UHu.Q.Z/ACS*&efDrio/fh
k)@m<'9;kD#nXm%9>Qoo*53gXZ\/8B>1iX41!^Di.nZa:".o63:/K*;$V#fo'A?Sq=]ArD]A\4
)ZjXj>nLol?.[b?[f*PgoK>1h"cbe7X]Ann__?S4p=XgeRn1e"tF7!r,19DoN37+5H*Kpdk/U
rrWV3uGk$QjX&pc42SGK\ULB,sVB%8h.O1?sA%2*#XY4pL`N$H]AOWK%8".g+p)PM<T9O*MP6
:J0/tTVSBt'[@1cqV]A@8GCHlrNV2_.Kgr5UmQ*(R-c-qa;(8!2)GhMS3SL*tuFl8Ud[]AITqS
+?,`36O^NYC`1R5XtuE59m+UD$qS?.IL\K3/9=Hqa+q>H*DoA_"CR2JapgucM.%QMekW%#*a
'*n9t\A[=Wj_""r+Hbs+B3qT9<+n%(@S>(h#Ua3csW-!`=.!9Uu*gJ"/fi2RKY'%Zh$-#CT;
j0"[,Y]AjfgARr$X%!B@m%JB[<LEJK(Kqhn;FTsJ'X[!TpSRWgu#\,_h.4,0H.deHJal-H>lJ
65VfE#ePXDrRK2KC"N_Z"c3l<X7\5.=Z`oXCjPMsmLDDd2:rU/4MsN+6PC0nu1<5dC]Annt;S
c\K,-*q^rpNjYeYmOfoc3[n4&0WJ0\[M]AmChGpb0pBeI'SClEBh9-S]A@d3PE>lR,PX'sY(mW
QX9CMV*/35CeSbd9ko$@Ca9hT+Y9!W\YV3p-UP9f4_M.`Ko6MFic[3M9nITqLeisAB2a8'?_
,n%Ri=X-]AWs0K=W]A\+PQ[&(:I7)5k=eQ'@Gk#/9,oud7'#!M5/NgLE2c323/FqU-*%9$AtV1
:44^q7R%PXGkG_Eql/9H+UY/WF4mZhDF[1RZ!0K=`'_EkdXq=<\(hH0+4YWoUt@i"njE[?K0
#TR]A=;ip@qNqSMC<m3lN?FdgciH%An0u1lGMPsQnI*j,T0(f%nL)i-#"C2nYhMWQFu#+k_@@
"JI)u9>#CCpb42@pB,Y;eT\F2@.)Fo)o*kbj))4V)l+#]AinNLfY,m\C9qP_uKF]Aq-;)7E`n6
1.GTVb^hTSdi+oLY=fG:O=qA9"28"<`NObDJnn41.Ks1/LAk5meD"I@en_LhJMmS*"&,T3sf
lDdRjaf!H?5uW,D`\29806[.R]A7d6Su[BFX@H>/;YX,5o>oUOS*gS?QU)$Oo6n['pJ+V;*;%
f8]A/^?6a5'Mn;F3#X3.t.pMlX-+[NX'RRS^k:P.GORMrO;]ACC=qVk+>JPqEHf[PZ@FP+C$gb
@Bm5MJSQ3Xe'aKn`[5L\KnYUf@Sgeb!_=7`RejRhGsHM6h`[GMH>lS-:L)bsGR_4^Y6kT2$k
/bWG$uhDV1Y,b\,4NO0TDN.(;Ap?]AO#Pd)574er8%_A/`&X0VJY/\?bL^Zcg\5-dBkc\91^`
s).r^,`q7G-'6_\`NaUA!W`BDBo,K5+hjA."aIs"%5"EnNubY@]Af_8OhaVLmYe)uX>1\o?et
VpNTQD_^)a[Ti/8<Z.l,QBE>i,a*gu/H.LK>fJ`<17:p')uKJf"O;LFmL\-QeGAjgmJ]A9@oN
\sMGpHcV2SHUTGkEW:\JGVi=OHV&M_>3YB7,Fim^3FcaT6$@GSVc[q'fsQttluVTg5$Ba%=&
!)]A:!P)j@ViR)7s\=qJBF+9P6KSa>lu#`q%;Xj^Ym&U0hM[O]A(PPO9\kdRH$Xhfjl4V:-<cb
r]A?X7@7L`Lko;s:An["74]AEV%Jm=L'>&]A<\C5,kPJc1!GG0Anr!_N1n12+)F,9N[urN)^[-/
*UZ]Aj<']AhOB'c=E@Dj[Z?"&(1('FZA$V/S[$p)0rcPa-<<U%c\BQ^]A@P+]A-qT8'LTomUOQ3/
Ljp%Xsc]A_,-McNV&skNm_>H<`Q*%f!<MDo&3;@D8Y6F@DVRY`R,`iO[uHPo3pD$j(@\,$\AT
PnlO1koLgL=9!e81XH]A8mG"@h)A*b52FsQ@2Wle5h.go`\j[`_K./>?FM;oi:\q;V&."c`=%
F]AKHsb?-g$oV?:MN-r(d52p-KL)sg!r<mdMo*RiptK3s*0\rRE-Yrpolft4o^Bp1)<-?(#!Q
7@7fKn`SE,?Zb#)iOJqYG@.&dlj2,aEr`'CNVgi##?X./]A))i+g<Ik_B7U3[-A<I#1UKm)Gq
>7t5\?A_[pT/q1%pS_c#b8%C@KWEf_onV"e@X/Cif>Bt"41+q,1q"GZtl'PBN[Mc#$#/d2IB
Kd^q`T`M[&`?F.IjG\gH,P_hl6/Q%`e=5Ll6?ENh[V:JaMc9gZ[.]A-P2P7uO@LG"pIR>HIl*
TqR/4;N]A/Vq2$lf&R.%eO0RI0SkiU'N"-OcT]AKqe57.OkSHaEE]Ag!(%bC36a6@_an6LC,j5_
d+4)h-0>(AD-ojVJQt,@T+jK@Hd);NV0\2/I:^/dn@3$j\EU5565C85X&h'qM2e*'>a_*`si
Q8;_k79&M75=-f1OAYo0s]A,hUmA*O\K=/;0bO'gg>em+b6Y0tJ%^3m@0f9CJ+pb<1[h7p.KX
+Y:GHr?.^U:,</F'g6R&f`e0OV(%@[j4\[cZ3-so<b#9;eDc'`/j!l3uQf2[?[(QY6l+qr]A3
:A'd!M`OL2-&K&j61?`O+8>Wj]ABSp"9i?u1S&d4`g(K';jl?Uc@aTHauE^p5iD!tF?&k82L5
+RC-UlggYPcF)0#=R`dh5j:O`nS$'74d@rTC'n',iHcAfk9s3o#Dobd4U?GUdt:kJX12WlCH
QMfD6.,_!-mW,P7O#o3FFE3cP3YkoZLN$;58rorC$88n3m3iQ&Xe'9/J9SbO@ePm'1,G(-M=
8.]Ab@W)MHg?4>n1hKRi4hIQc2;]AfVsZicc^^CnjOH_MGilmYg9t[A+GV!AU!;5E);,ftDu9S
T5m4?3IM:\?/&>iF07GD!'_A[RjZ2)!]A&bdh50\M_5u#+@m6\DBm"*%./pfaGOR.8fIb=o!#
T,WHb$uh.C3B8a?NN:aP%<ZL$F&V`$53%CCnoJs/q$8.I3NSV4IFP%P#YXp;4kQC!aX'i.Mu
]A0nB+ld$'V*_ab,S\Qk7$'+dSalSPtSB-brB!fK7/Z;N(N94dLMr66ib+;d;=5ckS4N;crf!
JkQSN_2pd[Y`7.Yh=jD$p:$%8o\Jd(68A@MC$A:OgF7s!CQQ\&`mT&7%QiUb;VuAr=7lMZ+t
"ch_9Tb,M+b.rcKA<VP[<Z:fZtk<Baif[Gt-4B,?%lWi3h(,d;%EQ@!6a'ru29XTV_oJelNU
G0N*MZI1TGWAX"VQ1)8XNed&;f74NaPVuaULl,W#6i0#qWZp%+q'+bJQ0)fSordH1>@!$B,p
C)CRXEEEq2dhXt*Zs?YP32[hF>@&Ju0urCk9BTh?:]A.%E-s>3F0Mh+t+\UH;j<eqG\#@q#)+
Zk3+."AU\UZ^`U*;r>q1CdQL@XG`*'JZ_fnYBC5mQc#bf5\m57jd8Xf\3UuI2FjR"U[\pPDM
:Peor?6iGaVQ+-Y.VXRI#-P.@Jj:Ma^8#V^D;3qX+BN)G$j3@7h%oA6XFu.`[gSJ$j:M2XA_
3DE\m8PB4gS?g7=]AV>LaFAJ>Kslh4*[-ejTM[1ROB;.,%%a%'$HJF4lu#<%1u^=q'ic6)9tN
rIB]AGNnLnd!QW`_:j_:ZSM@)LR#slHLaKNd2SQCkShL2*&;I)5NQ^/lSI?^S"F-,34t1[&c8
*kWJG/a.o$pjkAU0<aG)bUW[%_c$?qlZ*SmrcWK(XST:=a9[_;*qD[W9DF0uU8%VTNS\"qC]A
<$J<mEN2B5.mh(->k*`OFL)`eJ2<'fd#g#7SA_%OWHJ+?X[qq+lS`+?qJ/KV\=X0R1:)tK(Q
s%tHmhe[A3i$JrlBj/+*Zb5caHMAYk#1Q0?:a'XXll.1&ogl;U8mK[+H']Ao$)*hJ/#^S):SF
350XioZSe0;JPTL.h_TW]A>C:SqeSe"&Y$U1,0F]AGWEh0/3GcYtBe:5DO_f0DfHq]AWhc]A(+J?
7o\0GLB*"7=LO=R+QR^<R[GfP7YRkG2f^,E4M*fQT\j94C&><9qDgAD\=$Dr$Hs.DH_Bq&a=
qo:@A0_?e7^^W..'eCSKjXr*tkpk7f^@`#,_1Te"7_hW&1rS1/hP!n=D;:(.K5PO6otF6N$`
M&UFqfhCQPH;I^:9>\l17H\j(6FXljIJZ0#dFP:DDtWb#,HUUF*8Y2&?bm"7h_qtc?)2L(ZH
_l]AWNgh".XbkKc)L1;*cu[?YW72%El?@YM1mT6cIIQrd*T=>eQ%s?LAI$#^2A'HHcfi:^:*j
LCRThE'!4Wb(MjOHV.N_s9'f-@b>Zp98X*SbbVclZJI]Am^%f!uJg]A2%OMhe3tDtfWWajk-g)
F%Cb?&=SROS*2iBu>ZZ3MSTpEN<GeXC3<W+X.b3W=')\:<V/VR[OG>f.+5aM:mA3m2^)9l.a
uWqDG<@(K#:<V-'^E\QAM5a&jaUEmgSG^CciLlp6Ff4gVK:/CHoc#Pt?_'j574=3^TnS_2HM
BTZS:e,E]A9YYi&EoB2uQ6S7U$n"6L&1i\SQ-XmiA`nh.S)XW5`63doR:C4]Al>q(Ogpr0C14!
$B4&CnD.>=c-GB?=ra+$+O#dPkGn0U.dnBB;qp>$Le/$PQ5*7-YT_21<t,aiA6*;+u1"+9r%
dV#lXgl2-8!C2]A-&^*_Y,)V.!6FM3bEM5p+SCr6RKmih<u"qR"HNI<fC@<)0H4a=nCqsm'#[
P>=j'OfN,l^l&BpEDH'RD#V.CJmBbNj5;_eT<3#7<h;doN`VJ0i3:$)ioE$:mEfY?$5YR1XY
,d\P0(EH//tO@jAOenP8$GUQp*=a2YY!=@LWcUP<i6]AN9!dd)fg`\;e59>BtTNcI-!a1KI]A+
obJ.N0i=LcF01"H2-B'?@HbX[eA,dg^Ka\"(oM>BDXECf6)j:D81Wt5i&!G@`k%lQ)J++%cN
L<g^o%(Ke0/<2<rVP#nf;:So%+FhrON4_DR8[2ST%&o9L:OT\B.KO$Ndg'$@e%?VJ63Z]AM4D
LB&#tX,=VWAg9X-MYlnSnao:D6\Q%G%$/3^GIMUM,#`"?mV+,]AkqAfHC63/!UbMXEk^!YK\,
#90aq=T`chDr(KY"J$!lln5FRqiLX?]AOtf4ZI$?F(g-$D8^E\@1;@.!_cWQcW'"6jlhktdHp
CX@n2S9dn3&fTS8Xc#c1+O8W$n/<qbTmhXQ(\_U#LLT2f.6cYWFWgO6iOQ1LUS7H_a78n]AT\
9FPIUb(e9gku83c#j1[4paH@W/W.am5<s5VF525=N,#k,bnk(s>;i9pcLA>%25i#D//Aa5[f
lELp`s#-bAL_#20V4?#5,jgN[*bW1-_&1,p$S5o?l2RoV/2LELOB0C[np*lda-GWT*0d!,^N
AE%T)>(]A)Bp2;*<^n`J)m,-TD8\oi/WAfHd'FgGkm7u,3c<RC\WFPfrTp8/@U1n7pkp_rB"D
@jfF'fdt=i`$k$U=[.L&!b+8D^b_YAmKQu^@a?a]AjoLV]A+(":GlPTiLI=>DJ8g3e`iXPQS'd
Cb\`)urc`cbj\*p(6G<OfG</[nDKCmUER:e56)E$GKXLfNUC%KMF+@?6-V$hWtB)$l2NNAF&
.uVopa"A\ViXuLfZjC[IIcMp4LK!Q'$Jp(`Ysn-O23n-8l7J=Ln7t=%D#p*UNsc#W=se]AS'J
9VHp0`ksk31t+c#=W<6'HR)4gFqEkao'Yo>Bh-JqN6pI/`pF>VJ\=(a4"7XkHWj9-K#naaiW
]A.jDK^.#S>d6?!2<"+iJ'.4G\Oe]A(dmiKLLRq)j8-qWtDi*17d0hDPI67j&9E9B1oaM)bIad
"&<GE/.Bq_tptDp=6K>arpR-<3T3B%Rk9g%T\oP7@2^ATDp<Qc1N%SMQ(D`7Y;Qn,jpFl($[
"a=:3RtioE&b^3Z`RCD$KH4t#q,6YXqh*8$EH:k8JP.'nO=((sn8n6r`YisY.,kFjfC-Tj+P
oakV.O/-$FXU(g?l"s=_;o\_p=O*o'd4l*?Q=+t`*Pu_>Yi.uAAi2,Z2/W9-NR(k<]A1(?8/I
:(qo<WZ[fDfkVfIm^l1Y2oMK1gOHZs,HP.]A-Fb_@\'cSQrSiEhP@61eN-Y-B:?QFB!!%I`_s
+^r#A4CuD/XiB64al(@4io!7D)pS;;Jf!^D\XYcuhQd`.3B-ak9'n/esPT@?QAU$aNJ#9r0&
DuB2m+O4aPgtYKfXY.P!;^QPB$!?<WcmFc?^JC-]AAK;\-Gb@)+>F'II9a)Z8A%"X'*i?k1\W
k=Xca#5#Y&N*-ijh*&Gnt:E-hi@JUT'oWa7?EEU2HkmXE^TZA\6HSr`:T_*5bkM$$OmFl\qO
"jE$9::b6sDp#m[lcF>IeiiGR]A@$'tVLn/N"e\GEpEN///1EA6g3T.$8h6/>MJ)Tg-:tV0jX
YZ+0D;>64*CH#h_$/183tfNHms&Db'd!u#>u_fK:?NX"EXF<:,S<fL[nGrXua?2-7U=j-Sk(
>21LC:ruP#9a'2>s"oY=fS/Y(aL4<a,p5h<t_W7ORfTJr&dGL*Sp):[@:QTIGG8gF^]A`00(@
gCV/Qhc+.a,<aBEl%W[Ch^Zo9SJDa+?*jO5l)pnfu.ce+5fOp\;p.Xe>>3F&8SO:,fI<(T=n
=h?'R#$=n%8d4<0886L.n!BSF:LA%[X$/$2`_CK3T&X7:+@kMV2!M%[AaP@3R,g'(3>BUL.A
^H'A$RQR0]AbL7dS[E/=:JcdKR-R@,Wb]AiCY>`,\\4SIl5TU_&fYd/tanRg&FIbEAb#!:I/AX
;:]A[>YFbOHkq3CEVuE'-3&,5jFcr:7Wqk?#%r[ndYSk:8Rf#blH=WiSTTV+ZP>V@fcZQcJ!<
ep;bkc2'q\WdBrhX49i`+`_IUiCbO2^8@>J(S=#9?Ste\=<r%up_#;+NYO@<.'$Q>(EV'`dX
m#FB:;Ha62;dY%#1I;?%o(_ZG3?oU_2P7>md_)CqL-TWWX7j@OCCYd-puP/nuj1d9X]A.SEND
_5'6tjLbP8=\*KpXYnM>H<C<M9HYdc5k)q4^<\kbne&$+e>FYX6JP=*L;qB.lG_,%EAI:53P
FX=1U@>Q=/fG4p+moT*DeNTN4^+Xo&?2$1CErJ]A'/jQiHGjGsYJSREG'pQX3X$sKWJ<6iEW_
Q[udVq3fYBE[n)g%a=K(5Y(q9/Y>%F%[%)\CqL^QB\@ZWKD:`a>=bF6g]A`F&CLsnG@o57$3]A
Ah*%@*#i[_GGjk?pBVR.dLDIF_eoSHN[*9H06o>cS,3DN5lr(61nWs\=Ct&-j8aCQbg^sh*l
Ub-SAmqh_^e6Ucj[j3I^iFb/C1=Cb2Ern&_qi$'@s=V/Hdn<ui>N,2i0bXeYPCUA%XAih_Vo
L]AFfYdm7#WDfq2L38c[J-T2+e38'%A8__-I%"3Af5;j-/b#YLndb_O26QZX"Uu4KWBi@A!F+
`O^(Z7m96moh9R*CkShEU!&Ha5J.))0tYER@tpun9.bhB;6_De@q#&k0foBc(_m[-Rm)to#q
W;5<$9VJ3[J/$`enB"pGu;UXu]A1:P)(t>b&p'i?h*"_7Q,9nm0tIIgjKUi3V;sm9NmD\P=1;
:6L^Wtk/L)#F?+NCXts[J;qD`uZ5r)sUNMB*H^LCs_IF^.=A!n+j8bqHHU@?AU$-G=?4t*bH
p]A*0/'RpJaJ#s/&jPm,]AY0%RHSaPD/aa,>#egeVl;KOn]AsO8Am3Jjb:q1'XVRqa[msLsF?_]A
bce"dEc7cC`]A_$";%>7$"_gZqLf3DJ+e6+k1/C@&.e)X'*EB4@E-1^Wpp[K->n?nU7PjR/^(
YLN:1aeB1m<\fnsdtb,VVAMtci9f#u4O^C<J.i#HJN,$ZJ%#h@7g6nM;r2(@FTMk#-doqOh%
_Mo,3ZiK4B6*IG9+8k[)/jJ]A\d+QS9T,S>h]AuU>M^7C"<nQt6G?DC?:s;!CW!>0FgFZ=Tf'R
p-WJ1Fcff:^Ej`d8?W.`Rk`V79$79kFVCpRU6Bj^NaG_\980LrM28/kM)(TRK,8S;K)Lu*M'
HlVrdO'-1U4q0]A"WKVB_>U/kZV_g*<f<E"<-Nk9/e"XDhm!o5=%no_IIVP.\1Cr6>A4uf#&t
I'rU?#Xpo@EWs/0*3e+^JALgDCq>7hVbG_=7j?Zem(KX*>a/.D:Rqqf3__9"=[ER^Fq:F,Je
At07"?gO6-!mg_ub^2O/imm3;nm=8rT5Q*r-tCV4s1HM<rH]A7Of=-t39^&gks6G/GOT1ABqs
A6me"l6kXQ&U0Ls!-OWVHMI8P0=?6,5:9>A:IB:uR=J5jP&PA=(ut@G_'uW\hSo;#=@h+>A8
rdtXo)iM.BBjU^i\Nab+$5-*Y<q^O2o-qY;Ka;4EmET*^K;M2E246m"%GC!V!r2KV6D_WZ0n
Ws#1`C@b]Ab$)Tj`Nb4m2t#;kW8)`p83=aRbC&P8fBM:dqZAg@-PkJ.P^aA!jcR\IeIgQCSRp
*`qNb1$YTEAB(N:m$&698_KkJZM>]AAchST=q_$0"nR]A1QP0m,%n>MNpgC9$,i*_;lN:S.&n:
C8UcKi'c08(`5M=DsI+Kg1<lSE842M&>$_QWatMI\&AO[V@ClSaQA_DZufb*19!I;C[.laHC
%<Y,cTK7-d2R7f.fhHno(--!_qktkH5a>J^<B8\&BEA#Y;34f;Ak-TJMq0-A=>%6I-OAA^h)
_qEZ!f-\HW_I!O\mQg/7HHH9`m$kdZ+MTEJG#;@YBU[mSY*)#Qhd+mfTp3PnW$4^jFOFnJG9
QuY>M)QTUV9[Q[3KdWa.q<lubdU+BFVV3c&\)a#\^+sZ?m)D@>/Vn5#PTqslQb(mP42]A*qB%
4":]A"Fd>bb/",`V*sDd(0rG$Nh<e2`3*koZ:cB"erNlCK[4%N#%rmXV)lL"^C@9R5G;cLWS_
XSqN+"Ki9J'qr]A2D)Yqs7<DTAme$abq)*f`M/m"0M5SseM0W6@*P.H`J(-11;<@#6o5,$*!T
+!.8@"(_OEh+(]A>n%<$\^LQO=<0s33;u7Yd&cOTgK'o"&t*,/QMW#`5Or9h<B$Zm*HO?@t3)
?DV>49=:_M7BYkHE3D'tR.0-%'OntfJ`TH_j-+cE(TkG`'Bs]ASTW1$*gq\kj(?Z`j"cYi\sl
04&uYRL@W3C2YAp\$aL>?7HUKKUJtGU0F;Wa#ZuXdEWKL[N@n'=@i\]A(=CL:5Z3MD_/5Ee+X
DI(Uerb'G``.Oj.FM^lTR'$HOSP^QRgtm\5T4:O-A)4aotN\13WV@hskOT[W3#:>&"'Sl@l/
FXKD(KI:s1VOW_UT3)WemK!/4]AnQ=eH,DrDg'\nUEtOAdfT'KblKH_BE:t!e$>2:['<t_@mk
=>CC;WI]A\qn_.L(*j,ghteTg&A#ZX;P5c$W@Wmr=,-BWiSN$hYPDcD:@?Y6!Da7a=Z3?N=ug
mY*Y.\0FP(!9,GsE6itB;4O[<7IX:W*!^TIX.VYo[]AMtfFlZ4HEa,&<^HcFn0C=-4kf[Ids=
DNG)A?_,0T6h"Z#0,/_/3Ot"Vq^3031:5^i%pphrT;+9)65q#Y/1fQ7%0?Rk+!@!pseKU=D"
H]A3BYC8<.U>S"X\n\5Sd3mK$!IUTCWb%15tK)Q]A[1W'P<J$*UJKRB@uR^k)BTEX>(PJ`Pco,
_@\cXmnK'TUTlhW7t'3.`0TCm4E4!\8ND]A^#jHZ!*cpKbrem#!A/P0`iL)"KKW"SBksQ='rb
sI$RI/!ljG7WKYPm%!Z9le=Jkus9X+*9"9d(XPhZm^rfI;)BBB)"`HQa+eVMst1'<VuP<%5"
3$c5Q4&,Zd^A);EXZiq=GcoMp/<T5!_Nsk?;J+]AWh/t`h,.J#Oq$_,6/n"B?WUc2&*]Ae5G7K
?MmW5C_BFrd_&n8<hMV=$C/VU$fCIQZA0/rD'u^\c2eu7TP7Xf3P]AC/%Yqq+p6[n-7A-2VC3
2[XE3*Q=n.7T@tAc4\(FRBZmR%,bo:a"\KrUR[^m9bJ*',=B^cZ.6J".tj67>=HNoH]AGcTk@
=:F9"2G\LSGBH2.N?98Y(;5DSmfc;?>["m"lHnFnpqO]Als*/KlV*:CAc[ICt<s?1YIsH!BNL
cA[o@<$,4T97h49+c:"EJ!Z'!r0:E[bmIkLOqMZ7UWhalE;>Ak[,d=g-*l<Y"]A?r8A]AKRoE4
k2,7+L0li<_Blf#&&V@\M1&%R!"p@UuBuG3MFIYPI#KLY?gh(6IqP+37?*TTG3nFp[WaJPQm
7>Fg,3D/d?*"dt2\'pL:<4q#mgJRQI=>(AIGeW\RGk=;^+W:Z`cG&6`"oJfd2o<&C5&Mrk;8
bB1tG4)C!L)lnj4ZcV1X!R\+rrX6pVm``cH(Hhds#78I]AQG]A&3cmOjW=2/I!MQF%5#YAF0XU
W(-rq'L[/Ge^Ms3bR_-U9EWO#W$OJ#%XVWYjYc`B4BCPfL@9\kPmblA+f`5_YpnJb$=qZ_=(
6B)/AX@-G-A"W,LeQB:02r3iP/mfe1hd;jMJlLPs`\Y?8X+i_JsIX?P'^]AfiFRZ8+_`9h$/8
\*W/[*pY3@B)U^5<nT>0]A91JR?L%crM!r<C^\NP=:6B9Z).IEo!!S06"F_FQ#1E+<=;bZQ=#
EJ.el]A9>'AJUs<EHE=Ak=<sEr\.A_`u4*q4k<Y@1ggF@Wbb3gh*BH4[l,u(#OZV7IUct1=;5
?d6C0U+KGrM*o+;5:l&h#69Wo2ir'0hD.WfFJD'&s;J<@%$&+,9s>Mg%_h;$j&1[B5?,N8+0
!fsOtTO[L$@JY_"=Fa=3Mu.1G)@&Ca5hA05[2md=@cX^%[bsIE76$Mom*41C>l`f&5ckg9Ai
6D->2k*Xa<`#099Ld\n7)#9nM"bn8,ql0VY[:XYlCW7F8p"79EVN2hR4o\>-Ek*Yq7GG;kdM
b8?8I":b$]AW8C1h=LkHt8KM+($=&Q"gd?9#&nPZ#.&.(ZGJY\Su.KNG+>XF*7@gIaL!$MZjA
24O-J(jD@mQ%n+Nq3L'mlB9Q1[jqLo?Hmc)g*^p&5BDe,We#(l3#N,opFI(`R72nR`Xg5d1H
<gdd<fIR3\2eG#-Wq\:cKD(H8I'_"N^g@(;u)/3/<8B;#EiYaE[T]A\j2^H14OUma#"O7=#]AL
FC'V7Y-aGTE\MIX]A`\rFA,3tHmXYgB+j>HIg<IZAbpQbg(:Ali0`[hf^s%*[E,nQ,W_W\bVR
V$@Y]A,@In?(NZ8"Q;i%0D)kGs)<LlSG?`04=ma\jIo";@"Cu\M;Gte4pG#aMG[BeP:=/6RWQ
f+b%###m7f1q\;mI<O'uFL7H`HODg$bn^Tb'ZLk/7K9K2t4Ak\rpcJoMl2oI6Mu8Y"jAq2;?
%:rd1<P*&&WFg,V#"qk(fM@'_JDa48RlHKaJRu'CM5`Ym6`g;!hIu7)IGSF7Y#$)hQ`dED+V
#2=aj+orN_%lAVC_rS!E]A<9[mV[%;2<P'N`+$Gokqr\a8L'l@M$NJb2:AhR50NY3+QF1B)k[
YoE'dHZ$7r?^e\5W%C-%l"<DjlOLZ$l:RSE%sqkD4_hkBh"KZ<CUG_(^[r%r(q`\K@\MCH"d
fE/^9mtsV3]AsZf))E1=%LG,@P)R]Ahi7b8f86?I5[4Fr8.q=<F7?gf2o$u/c0J=q>$MSW*u"e
lp,hQ$V)YB<h0.7TRs%a,!h+i;C4[C<pb]AIF4`_:">Y`CfI(T@<l!%&^W`t[G?W$"HU&e/E2
-*B`dHE]A>MOg%Rn?gQK_,`k-G6-r7[:KE]AJi/do`U`pU.JJ;?fo/s9X!X)RW#89_9i]A]Au,sk
fTNPkX6Fm2usV%i=#L2CQ*blYEqAcLHUO)r_bI_qZ?i>0_#S?Pm;h,i;eOVZKt?3BJgL<"S#
?)2$1)Vn(%E6]A\V$_&5KlB3^C*nrC.oc5.i/f6XZ`$SnbM-Tr+\F8VA*ch\_ZJf:TCAZG7!a
h:G'kk?lO,gp)RTmuj)JFil:U2\0\$Y?!1)/UaVq.A!]AnLJHm`^G#ZW_G4]A''oAe5>b;f%bl
kYNV,839%3W*;b8s6BCAW&dr?ZW"*/$$Ic9@SjA/,X]AIO\rZURijn,?+R@a505]AMG.]A9N?1'
P(6V@;E<J^"qoETf]AXV:RQp\!<Fgf:S[a7-Ee'/mQu0cJSY:kTg#44*T6njJcl><G@pNCQt%
_b1+/@52.A$T[oMuZrpR\%M8n`3HY=nW1;R8Q8!]Ak9JG^HtKhP*Gpt\M1MqOm<(7nj!E>ems
BW*Y9\-M<4;XAGY^=6K!\7$L>=:so;o0cD&\)V!qL5_4`hhp/TM@?NpIHrb_K>^UA<SpB#Ul
kSbBT1U6XF8B8\cVq"(]A<K=Sof3YZA@/H]ASNTMKF`;:Q[1:$-#ED:]Ap[if>KRt57C5Kp7pZ6
e7HDQc;?o@]AN`$L6\0,10o=3FlE)pr'A?7M.9FU:e$G1:6-o:4a0CP_G;u'T\YEu"F7-=<I3
\S"D[T)6?WDNNe!A^gWNre-\%$4kXiE52o6T';]AetuEu)a^:0Y=8X2p-7#e.ZM]A-]A?8RQ0th
dl&oo>M.,DC,kNCo/aa&KQjg!;0%2`2ldi*juI!$H,?1Uoq7n4/7,r7Hu:l+;',B'rt5q4NL
&ui)k4((Aje(J?I:k/^GnDLm7FiIo,auI9mK55T:eH.B5&RcS@id<r7.;s,F?q%C0&cbZP9T
B*0?G7RDI@uN;Af=ntQkT\G8s<$M`^N@-K2^6nD/K>Prk%%(ib7(B--#/!i3NT2m/i>o,K/t
5CgjmFlb'._mONQTeu[gMl"_M?`mi&2--E[(HG4<V1C.:tB=U9`2=/.%Vt6MP>s!t9icmM<&
(KIEl_<3M.Si-rdDS<4.R/a?%^H)Or5&!h^1>Y>QI18aXdPTr)\3^Ek]AeE#BXr/X:\@O/6$4
!G^K"(,"YWO_SP]AoEk-C6bPYQ:Kb^8VBbKTdnj[/$t'+Vr*C;BgZ<28\#q^JY#QUM4I)!_F!
B,X`2_<n;DAUPm6)/@m_f=;!JH!b=SRs"/Fh'EZ*)56,8H0ZD;3?>CnA"*s.(=$@VC7.]A4NY
m-5m4@UhWVPeUN$&PN-[JQt^PUKKB'k:NcQ*Wo3!Sea=41s1>0%OZ0e57[Z<4aUkg-d"$(m4
G7JnE3::SR6c/hf,WTm;3Tm1*Bqcgc0c?EQTGHI-SRJ5SIdVe*,"dRbr:2*\^Mfqi7pOs_k0
\%u$(;/9%h$`3ZV\8V,+g5HtOHt/=S'&St4%;im2GIN>4GFBnn'??G%SdXi@hZPlDZo4CpK:
[4k*n:jR_P@6s)OY!/7]A@l8to71i^A<l?"Wh580-'mcKqOf\pd5`"#6(_"3*p804>N0UYCVI
Kb77W$>\KC+"N?COBhB,%A.M_T-e$[Zon.Z4,Hh00227RNkF/^I9Om:-(:B3$8ucf5:!!N'L
O'YPiJm#Y$$=$EaF15COnKd6baWgd7IC;YgW?m*b$rg?FN/I+=5hZN)YNoo:u[=pE^*Jk^,q
ir&m"ckaEeM=M'@H9'GKJ[JYH4+6a'N.ZMDR<c%LKb"qQr)'5o+@ZFR>'D<V6!tQ^nV"D,G<
"g-LRr:.*jFO3R!fi"\O;T^R0]A.E'+De)@/amQh)UJ3^..@_,/K-/I,]AK<.1gr(V@lo2u5OJ
8UeLSV)Dp!Z`*EH+MWD:WOkt]AaQb(2A/Kl!AAW</YZnBIG[$Wj'g`o*O9f3K;L]AU<.)e]AR$0
AcaoBU(o`oQmgqk:)aUl`^]A*7+UMJp#;5Z^%W+'cWjo^6DfupeEf/M7OM9(S:;AOn$_0Mo+>
JSc=j_b;(^bVD)E]A\lmsuGbR8oV!'n5K^85Vjfm_9=M@Acol%>/GJp_l'r:+c3QA)7V/oH[I
YcbE>E>>.E#eU$buD?_(]AQ;CHe1m3?f?WE=7I8!F48]ABj=UK!\8k0]AZ_,Of[Np'B0KU?cJ^Z
/W+-f0-IW"2:)2'cH%m2$?FChakt\(t04sQteMINPsU=]AIF<iU;p^k&>!)DOC+(eEMR"k92r
4h%Q!Nrp#N7K<"b[N&`L$0FEIi%b'YMM1,9f@98fH"'A0IIk8T8s(L:lJ?'ofd[SdoJg8t_M
dFpQd`NK16nfba02qOnDRIZ^$GTY*g+Q0g4d39]Ai;;=`2]A3dbsa,oN\aSfa2]Ako(q;)PQFBS
1I#00?[`:RqIrhs@9]ABVFILZ-L]AtSB7:3EgA=6qq8ke>0La.M"qE6>h@I]Am#B%i,->0@A.&K
gcS768=D_h3fgf2^E@L40Nmo:G0aI^Jlic3fQlJ(&OIPY7@`d4qf4qW4!qNO5XE3>uE<rt\I
7uL1p-V&&*(L*)B_:ccU/31OFX0$e&8rR2@*junZf_a!_Cu</088/4r+">5_E&,"gIoh!8?\
s(anoubVi[fNIM*H=m.%j\QQ+EXh2U1MmccC5+ZF1'bM#'.`.#1skmg8I*Kia:i+[(u.>Gl"
.?.oV3^]A0h(T51&qdmiU7*UQYIZr:`2\9pVS'2u">rtqZE3Wbp4NnPRMB:^C>B435D-_]A@hN
3ZS:*IpUFRRmZnO!O.hnYZtfX93=M0iK&&q,ZIlR$mYL8H*Pq3'8h+JORJh:SbU5"`@1J)NI
Z06#C9q'-pUVTZlO(]AndQ8P4nmQX+De.E=9qfl,a![9K8nK&t;9*H`VF9D]APcl.J;DIdH]Ao?
Og_>#EDSl^Yiacbcd<J,pE<f%RL(IK$<YYp,%'k+k5gRfg`Ad3b,PmF!l)Wim$rgD,\6aj`?
uap)fu\2CF^1IS9(rLSV&-[.@n4"9+)L<pi.ol(7~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="532" y="0" width="428" height="540"/>
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
<![CDATA[1333500,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,1728000,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[2743200,3200400,6324600,2743200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[轨道编号]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[图片提示]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[介绍]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="1" s="1">
<O>
<![CDATA[海洋馆]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="1" s="1">
<O>
<![CDATA[珍稀海洋动物展览]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="2" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="2" s="1">
<O>
<![CDATA[小黄人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="2" s="1">
<O>
<![CDATA[可爱的小黄人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="3" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="3" s="1">
<O>
<![CDATA[动物世界]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="3" s="1">
<O>
<![CDATA[尔虞我诈的动物世界]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="4" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="4" s="1">
<O>
<![CDATA[漫威]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="4" s="1">
<O>
<![CDATA[美国队长前来报道]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="5" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="5" s="1">
<O>
<![CDATA[天气预报]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="5" s="1">
<O>
<![CDATA[接下来的一周万里无云]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="6" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="6" s="1">
<O>
<![CDATA[豪车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="6" s="1">
<O>
<![CDATA[速度激情不容错过]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="7" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="7" s="1">
<O>
<![CDATA[菜谱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="7" s="1">
<O>
<![CDATA[有了她再也不必担心吃啥了]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="8" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="8" s="1">
<O>
<![CDATA[游戏]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="8" s="1">
<O>
<![CDATA[男人必备]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="9" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="9" s="1">
<O>
<![CDATA[万圣节]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="9" s="1">
<O>
<![CDATA[不给糖就捣乱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="10" s="1">
<O t="I">
<![CDATA[4]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="10" s="1">
<O>
<![CDATA[天猫购物]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="10" s="1">
<O>
<![CDATA[是时候剁一波手了]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="11" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="11" s="1">
<O>
<![CDATA[海洋馆]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="11" s="1">
<O>
<![CDATA[珍稀海洋动物展览]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="12" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="12" s="1">
<O>
<![CDATA[小黄人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="12" s="1">
<O>
<![CDATA[可爱的小黄人]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="13" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="13" s="1">
<O>
<![CDATA[动物世界]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="13" s="1">
<O>
<![CDATA[尔虞我诈的动物世界]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="14" s="1">
<O t="I">
<![CDATA[1]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="14" s="1">
<O>
<![CDATA[漫威]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="14" s="1">
<O>
<![CDATA[美国队长前来报道]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="15" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="15" s="1">
<O>
<![CDATA[天气预报]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="15" s="1">
<O>
<![CDATA[接下来的一周万里无云]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="16" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="16" s="1">
<O>
<![CDATA[豪车]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="16" s="1">
<O>
<![CDATA[速度激情不容错过]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="17" s="1">
<O t="I">
<![CDATA[2]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="17" s="1">
<O>
<![CDATA[菜谱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="17" s="1">
<O>
<![CDATA[有了她再也不必担心吃啥了]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="18" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="18" s="1">
<O>
<![CDATA[游戏]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="18" s="1">
<O>
<![CDATA[男人必备]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="19" s="1">
<O t="I">
<![CDATA[3]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="19" s="1">
<O>
<![CDATA[万圣节]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="19" s="1">
<O>
<![CDATA[不给糖就捣乱]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="20" s="1">
<O t="I">
<![CDATA[4]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="20" s="1">
<O>
<![CDATA[天猫购物]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="20" s="1">
<O>
<![CDATA[是时候剁一波手了]]></O>
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
<FRFont name="微软雅黑 Light" style="1" size="96" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
<Border>
<Top style="1"/>
<Bottom style="1"/>
<Left style="1"/>
<Right style="1"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑 Light" style="0" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-16777216"/>
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
<![CDATA[m<iPKP?HM?cAXL[ii!,l6QI18!tc(U.C#QK-n%]AO&uB.N6C/IN7s"e^8/tL$[#@TpV>$-<1l
+\^JeB4l$j[G380F^jnf28umsN`"I'7V)k;qMb_bZlbhJ97,pU\tip3q&RN8r%1WgctS/R,[
;mcWWUbEdQ$>IJADjS(0krj,(CJ\,aOfhB$<8']A0:E,s'EX#ohPm+bZjhp20WmO,UF;=n?:l
=L,&8*bSKE;cQ>GH^H"W(oBTT\8L*lt=Ki[g1.76-Z$N-^XLY.!.&^-A/n!1aFgp+:j\7fWh
%E\H74s[H*\+-]AcqsRX]A[sK8#,.!jY'/")l42imA0?\X:o+)UGS9KRS6Q6hdc[Y2?@^$H%J7
]A:>sor,6gFn7@22:lHWn5e0&oA?f7,Eb:%UGfOZi#cE^Z$>+"b.Ekm&N/6,2AtG4U;sshR6f
X`K4Z`0q3s`jr59W'PX%Ur6Mqh9h'9`Ft$:((i"I22-8JeS6PN]At#/h^=7E0CqGr7dmXGH&p
_7BNT77p'%l&@$`JOmCelV5B)LKSnNg\(0bje/P]AP1/%aC%bkN^I#=pcjkP3<\dXg(T,(8=g
En\j[m`DbkZ'rDc1jt[hMBWg04E:=V<1CR*Z_ncqAn@e\g5<=j.=@?PNR-?OF(N,MK>64Sb?
=gdj>LB9Q?S;r;.A:C%oU)fBn8L2KFUBmLI*SKa=qDF$Xu8l%BhW+7[E`?eJs&E;/Rg(1eV_
$;:BCUt$eIi4R\CP_W.bcKNG$5NRsQi\Hj1Qb+9Eq==uVY7c3ND>;<;AQWnG-cSYMF^CkiS`
)Z`Nli#g>/dc-A1(7ST5dJ05#K0dW;ICe6_62lEHOiVNmX&e!EkP=3D0H<0*Xjkh08Ijm>o>
bJ'Q6f#H5kC0AXJC>B!Bq9Dm8po".qDBd\#JOT*9Ab[SEc$61I&1pltYo"-'R<j2RXhTh[%T
!ETl\r`;j:aDVi^369!6V-d+mmI)&aVS5j!htUO'Df!R&`70gq6itDrp=(&BBPW@H<(dC]AB.
"-SQFLss'S1G4UQ)eablS-*u"XkM?PpTn:#bm2WV,hK?q<MVbk2/=jX8)=E7o1#lhn+H\$ke
%]At<,db,;+J%n8.:(5f`ifMLgl"r3+Vu7f(%Z<dcMFbYjnFfH]AXMn&U]A/kM]Ang)@2bIsc_?-
UC:02p\0WF5kgfeZa@+58+]A=Pp>XBfIpbJ98^?<a]A+J%EZS!7eQ5t.s;krd7^[eI;#=r\gY@
l_`s@-BUXgm,PSA]ASiCt;DTd6b8'T3]A@eNBpIO9iHb[$K`ACt6gE]AQZoEU!;sn5%CLmJ$4o4
"H5k?odq3;?"\g##u^*kY-3[B,cW_+BA!&IRcFnTV15Q-A/h&0lf(]A7/f16%/M4Ci2qq'".f
m\rb:U;ge[W%6re3@Hi+fT:2JaSD^MJ*4:-g/FEHPU'$i?O=Za^L,&L&nfQ'#ccD-H(?Wauq
W$nfqlO1J#fU=.8kmKW5Pq,RrAFSAr5NDH[r1YU$8a&9ADtk[PEVRM?3*'Qbj1S`8X>cVZ`S
TV,3Gg=k5hb<CBFA7H;h/60e3d`_?`J`.#E]ACY(jdE)O>Ild3IMA:K5OkW^G$1'p[a`89cuD
#0sX9=J3&82q,&d(4#N5D<)[eK)VJ^%]AZ4g(.07Iu(3d4b_4iCZ63<4]Aa@ZV]A/Xa%'mEPG4H
lJp[6.Gk9VO57[X_*=ln)Jo]A8FhhKA=q&)L:n??3R2rOR@RXJoj9N?j%n2@Xmj=)`Tg>j\Jk
%U(l(H'Z1c?$"Y>`'KkuC_,lA5S;o&S%)AMZ-Ld?Zg`0.BG<Ic6ec$(\Jg[GC$!P]AtO#-cd'
/NiQ:`lsi'\cGgOA'=Chl/I=9#s1<jf*QBp[r$H_/rn"'g9sd82SJK[#Q:a6hYrhE7sO\f1m
"RO$5:ENfB&R928',G`:W@DV@X`U.X6DDOjb(SV8]AqH'6m,K,tBZCCg-;i[U)%DD`LPZ+4QR
`7maeO%(g!)9$XsRo+C#oGqb0p5%fm!YBDiHk7ZR9:X`Ys>3C;YVmCYD%ZbP-D!*&NG)A1dV
!-\+,9aFr-D]AXOT3hc3==b,(/p1p/;['$.?_Zh2D>Bl0Br".8-Gt&rU#P`9@^)\o#K*%`M?R
+Z*S!!<\O446[5e7h>X]A8.pi"/FAWjl^6*U\B'Rb/gE\\2u7j0q?MqEAfY9J$p?2+^jS3hcU
bt""CYI,_n#1c.Vihu>U\HfN]Aq)nGQ5&9O)N*[RK`c]A*iNVkQ_2b]AqF4A6J0YFUB[$Y7hL[_
JXM@!N@-C06to3&i0sGjX64R^p`;,pVcHb%01d^KSu*?3LVkHi1fF2jo#R\t3chRI(i31/'C
beIPNifPR0\,q.>Agic4,AJ@`#BfI\5j%$&b5i_%qJjTX0'_.\NHInU#lh=gRJPq0HK7tPab
<#o[WOjf$auI_[64=h<h-;1o-KcIL^ft57,B*8]Al)`-6p;p4-FD&-m:O#VaLiu:I:T:;kfJA
;5:i41q\KuRVU@i(%6GtWJ9gmk#0!idpU-WFD^SE5.[>f9UdM>KYn<)>gEFoRpXB"DT1Cl:Z
*8pPm1ORPm)f-PljF6QY$rBGgA^g5[QViEsEl)7]A:"QSJpsH2u??8!&D,G'J\iL9]An-"<%S(
lp?nBT4T#'-_t!dqZEBA,rnSQgGAgg>$5IlRs1)^q=cCh58[#7g%qgJQWS^'o"/'GJNdUu6,
%+CFPh2-B@VZZtm4pKu5E0dcBX'D(df;EW9]A)1/U!7d3Mo:/bS=cTIWhj8Ttgg9pglrb0=$N
/c*m$s@;_pSopb<9ki/16ior^]Ai'i5"4fNk\ON2/FaW(jiE=eCZi,Ur[]Aq;m!\*M7[kd-+.\
_ah>KY<qUuJ[DD/Cj%4%q^<nmZI8BDV&[?]A@.]AuD%AWVS8nEUn@rpA[&O($5+JFe!uml9]AYS
a**+NM'NEAlnC.[dU3t]A\"8S_QSd:?$rMcG\Zpgja5!tlFLoIZG]Ad3`CmM/5XNm7NV\TJrS,
4<OK?joao=\<_*U$&oZYiC)i!pOpa_I:*PX!8(N?)hDGj9A)1u@4mbf:B4EXDlJg`bAPZ`]Ac
c<`JGhWo`Y7=u*[Z\D`uPSh96+F7[LaLX7Jm3?$eQDbr:Qd-DQd48YS::bKO(k!&g.npNGF&
._!<H0Td,?ED=eDS6!O?JSW;MI(6aT[d"?/3jCaL.g%+k#ls*UDt9*;BEie3L9li6EKmjpY9
cc:cE<7$O]ASNle'td><Mp.=S81oiTVrF<ACHH;Mb4"ALPO/HAQm\V6t(f880@FQ$:W&@dDXZ
qb[6]AB]A%`'O:lgeE:I,IB#<`O2h^P9/r(:olLto^I(&a-G*lnVYh,cP$&M)))]AhH;]AmQS9']A
@Ve(,SjX+e"=)GbT5c>cB>C\1r,-mLq-_kBM*X.TGg6'.ZG'?,(GjPLHkEW[pGCa/S@1UQ;m
_VqFNuB$*5^gAr-+j_cd&3"d&JmoYGu2BD!iB\7,'6-6@0p=*e$4GcRnSeOr5QqYX=f.`\sK
XNAnRRb#TrUJsATS/j'rV*)^02J(CZR-b*C-;7I=WRk>kA:cSm`Y'/""^b<RkaOjN>%"b(d.
'qcY7UH_Q]At%QbdS!KgAEd@#Q1+[66K<@ER+BDj02/7pe?X?IP+uX$\3T#:?EYaYHS.n)O$h
kDVpg(BqS*et`6j&YpWFCIb)Q*idWn&m':>$f3,/-ntGM?Wn:OKXea4Dfe$#<b^M5_ZCcmC_
lV41,`BsrP`Dn_3mVWr)&<I]AD0F:*24Ldd)5$\&IJ$0)?-A/&IJZohLpWki^5kecPNiQAc>f
!G<8(XRErZ@r52KbkV!Bp@n/Z7AV1/13>OlmNK#+5.rGgc70Vu[.OHpmc"E""VQ6rF]AR*E"d
5)Y"WDse;+IGh"<:t5"IcBH#qNTA-/pA"<ZU5FV1buo1786NR@s#bV^=f(E1NIu1*=)sDjia
'J-s`"]A^k8;Zl)IqP_#5deG1K/+77dlFZQ]A`m[,-6.GiC4,)1O]AjY0*5Zfa,WBD[0o&<g<eC
bH$^]A'+WnG4Hm1RUl5U,c7Z?4TSlf%OC4$;%:)QtJkgeSbd8M`F8(+SeH0ADMdc"4+\9K>"7
8&9a,rNt2=S<R21'4KJZSbbD9Vdd6J?g^qFNR&2U^&$80kH%/rZo84T?\>U='BuYn>P=EmdB
*U-JWXH>$%t=`lhtJs2^RF%*'G\1)+_gbEi#+Ua@7.VPKhWku/4k'hDCE(r9Z*g:eF(nX4X%
ER%>;s<H)hI?`EXmW!t2WpTar)%'RT&*8r.b!E:]A*@+>V3B#3Xfo)EVej/%C)@fA:Ck"F3)Z
FL=WPjbZ*`k6]AJ>53;mclMY*7A=`6AkDg%3OM[Q/3:4)q"Il2*Yr'A0?oF0"X+.*;Ob/=p62
I91fmZYnRNn<G9rEMr+5aqirR\0(PH*frVe(4l&IKot\_SRX]Ab<c0mGr+551otNs&gmIdtq<
u,u.tG5"$JAuRATT_3=`BX*8/'9!6s%JF;e=f'nsMZ`U9I7)oH>N/eX'KeI2.gje18VoE9ES
5WgJ2Oq5j>:)sY%M3CE.in$o*[;,PAp_]AjhJ$dnlX,RQ^L>!$1(o@%!PI.9)W(/JQLdejTZ]A
6c8-*gYG[lE[ES(j0`WDGj:8TRA=#!4r&6>T`@(3S(@Gm9RZ=b9\"<D7F$DZ+"!E5Xb"e=I:
qZ6]A^dMAKDk@k7q,_9M"-0)pgY8Z_)2+$>Bo'dB_!46M`$&$#@mlgn0g'!L!T>_Mg$/pRm,I
RZuAB>7Bg,>sQuM"mk]A6\ZeBi8=_j$0q*tGHYa8-\"VG*"=s3U2CL1X3.loI_MCI*VQ.6B+I
A]A-m3\7O5E6>8E5?%GmZ5`+F&.).5U"0Igs7S";%Q'kW9NMQd@VL/'j5+[X]An$D1;WAN[m#5
QS8:[n`S-JWRk-_74\QRfD1K9_F&+u')tM.P'o\@0$r/E]A]AT3!hLD!.8.X3Y[17qPUakbfeG
X8(LJKbZPNfjtQ<X3-,=f.5=V74?R<SZ8[2`BsdTgONp(eXA&<?80MoQp9;cc<kt3+u,L1)#
85EuY!;*NViSYRWT^@;>ZM.Vnp8!P43T^KQF`7U#@)a2Z'a+^c<!2$3'Y)2^,R^.$gKJfqj`
G9N;L&@FKpdCUVV:V6t_4nOq9nYsQ#Y)0kNiOHf]ASW>tkA/@$5($"N/BnFB3<pH@A!8(TZ?@
I^Bcq<6q=N.=IZZX0P.pVOaJAq149J3sDgUr??YH';LX>/+?Bj[-d;Z/Rm7"dHlgMbGFJSs7
XLtf(%%k]Astma4n=dH$c(ZI#ca^,3jo%<V7+e9U*N5\K[R^WNgm]Aj^"1p,4O<`mQg>IqSPk1
]A781JACnU>$Z*X2Ht8aQEt:c]A,HPPN3kF]AGXl$QH$=N0TM]AbTU-9Z5+$;4bNlb=$)J#0Hc!H
C!E=u*eoI_D>;U"^-IY9>MMLUnH3,HObj,Xt5-aTR3'W+mm2\Aa=BW)m4-FrJH?M=6)^0$@K
&'`AI?;[gt@7h9M87s/37*BA8XU&HP*VYT8GOs%mQhWV9oq[]AGJX*C&Mq/^9?ttI23Ihu#XL
==)P%#rpH`t6umC\fG&KIE;#sNI8$aimA!!OPU;<]A!E+VV8'Z]Aq7dT%CL/Rec^mUl.4))8YN
@hBPDl&j2Eu>n+G(InDJTMaiVGYYa>AYNfMb`<r$h/Hs2FB7?U,`kCe$?R"Ra@0,ga1m@Z";
]A$B,)_n&+NR+Ze&fPn2dD_`Tlc>P%lV:0SW/m.m]A'_3kd?*K=[1T?u-nl)`9nec;@4U($m4U
gX&Eo,OWU'%a<@n>;d4Q^S%PY2q-#ZEao_k0<2!j.l!qqq2GRk$i@7j;NK_l^YQ=A9bPf?/=
)OJ]A:mJCX@^`T&G/ab?]AQoEjjb[jD%hfoLOV0CAG#[#9OIaP!aL_[Ec2R@l-FmoPm!I<Mp2e
Ytp[ClKH.p-Yp4>(k6(L<of+JR.Z@-[0A.1>^bdDeRp1#9SE@<dec=$07=0_q_VK@_C.rje[
3.Oi:OH;;5Aa6L6>k`!pXIYek1=LZ.qQCnc-GsFN@$$<!J&[cE]Ag9lS?IYXPO1>Wh13e(M;=
c0lHrj2Q2H'd8fo8I1qm!8n*Lggs7Z!-TfB%PfdL&AKW(hre[fT@bh(Ib]AL;YXn^<`rHSH>Q
ULTThu:YV80C3*K:(DaVjpb:_PK/[kqYaUZVNnC=^)-\AU)k!'5+**fn%8_M7`3!EV,m(J?I
)55^/%]Ah-dkIm5<3ps0F7Y,*=1ObPuLFU9S_4F8GE5++lI)=.I_`M8CULh1*A8h/0nRmUr?6
r::RGB1qg#LALiNJ_JX=G?Lk8nppW73B($:aKY$A;XH6`M7!)tW_Rf''6a/)Q>"[/+:KI=Qa
.@(B48;9dB.3MHj=6D/,en[X(0BLQW4f3f]A\IX,G565.NA%/Wb<H]AoLC9+?<C+_CC695&0Pp
XDQ%_j8(]ARgrk&3>.Fn2r/7b:'R)kNAFH!s$T7,pP,.oml/A%=!#l=2AS#sAOtP7*@"[+\p.
RP)HDrRKi&Eg?&S=T\(us+2,`ZfFSsCDFH=b26TKNS@7<iERkkB9VQFC0h`ed%^0O=tq5WUG
G%YI"]A!YNZoL&`rpI)FW!Fib]AB[cA]A.QosX0r/1oQir,CXHglYo\Q^Ib+J[hCAUd3r3)Yg'p
%B.9b+2l]AlE':pQ!O+U\*p1R'c_Y_#?uWi*XW@,-j5l@kEPBUj]A&TW"*4bMb"(IaVYmO@bkK
eYP_0&jiaTp$8pE^RkpS3Y'@gV_e$[H_V5H1XlGnd0%4FdjNrJ&g`D"WkjqgGPH8iAR7<?H+
cUa3cX`!R)q!RC5T:/(TtmaA("1$HFrqs?i;0acTet*c`h$!X0SJ`s'bX`J0)XdpWK$*b`a^
EN>_JsbSJA'R_\J"7$5TKe%PamPQ'BF/:]A,q/5I%*jk:u[D5K?TD)DQ!J;E$&eG"1fo`A>dc
'C+7c=Kq5c4E=L\>Wuo4GaVf601l$66/1ssF-NnkftNi1l^?m->-YZ+LL42EK?/Xf4Q2PI:i
h)-l@MTk84TqVK]Ab;*HmXsFeaJO_5d_=ZZR@[bRMXWTfr]AT_XQ"<PK!=S;*d7/(lPZt=M+TQ
]A@jlsK[DM9t]A%q+YhfKR\U0WDmjJ/TVajDU`nEF8W,ElFD!?]A74cZn/1<r4%Wa^VjDa$0?-'
I^@DD,(m,k6Uut*\sEFePoQQ6BE5S^k<RR:&GoihdBZ!+`6D[jc0U$PZ)p4@q?h&[/56jS2&
dP7#&RgjtO>&Pq[=,_c0$O]A&L=pMVPaE;\#$FeaYXK3VPL7O(7C@O@R+P.&abli!h>pKI*)d
`HeQb)tVojL4PAoQ^iT1q%:;_R&7`a%Y?2'0^je#gfk(h0<V0R+?cRe1II=V@/\;#-?K?ES(
./i(qm^.@@g/V*SuGB#o(:+qm,J<_db8I;'7;6:eYL&g5_YkiEH\61[^GK0]A@ktHo77TB/;$
#(P(STV28OC)3_CoKPfb/qfM,jeuY?bqG)rfYpAp2UK2>+M2:m)6^MQH&4X?o$bMA*$HbG3r
N6sc5-Y3p.*5>cbX_GI>Ft>SCdFbZ;O'a[+m6VF$?7QH"RC<p;eaKW^iT6N=Dl-H7?)5Pm*>
RHmRt[+VQ%PXDk6bINn)3g=4d+dFaFnLp=5G1d-[43>;:Yu1bNLX:YISAME#`:gOBL#\D>bQ
eR:?kaei('o3fGp^.jJKMj;ce,BK_.E,u[1_=>^VPK.cC0c]A*P3(Z'W8*mf2]AQ1il,oK*pf^
`SuEBcS.]AQZJYnQN=h\U]A<hhl?09)<`ioFc761G0<6]A6LC6;H0GD^W+bF.oo>((1[_ml/`2?
fcVF%"o+0un0n=p(r?pt.+:'1e\M/p0nM\HR0[@CdGI*q$(DhQ++#97o1O/u1>@fdN!^gj46
[IWFOe.r-X$b@;1UhK0*#o3nNP0jFJ'1Z:$2!ntRiZ2T$RfIrg5kkR<Z#iT:h@CbRWJ$TPiT
X%(^O$&`D9/34,E[lM792\(U##$[nPgVA?BH$")M(AG1i^<r&aXnJL&:=fEX/q</[6.NKqeN
I]A_@c$A/lY8UlrkIla7hYH)RFghm%+f2=j$s'trOSLqk`s"R.3`UD%M%d^q58;_>e6cUC!jB
K8oc)7LrRG[(Ffd;>X`%cG!S.rUpi2;[6@%'<==@4TSQ44$W51g9P]ApHHEWICZm)>8!]A4BkE
"gfTo4JZ%0>C>h\cD-S7AP?GdDkUqW/aP(eMX$=1alcZ/p8"E#?-TgMd5(Q03I!Ym,Q:_ij1
E&Z&=gO:Dh,3K[Ak;EEX4??;gKmN>ql!Z0Z:I/4Y4`]AYoI2HA9EH4p[h1;<_YbMJK[\\#->H
r=WH8MHRdf=j/_^i6C?-NI9CQu6]AIs,+iLW7@g8BKPbK^N]ASSMbl+-&[Ih$C0P_OGt"S(-Be
N`7C&o&28<.!9NGC$0jght+AWg90(e"%A8GBhVKh\gR@>MGNbb5*Xa:C%I<lj9YjrS&&Fd*Y
]A#)16eVVn5-\+Lu.k]A55hkP,aORh"&/Pc<(J>3Z5m9mPAP$d,9r/d>j=e+NeN0"Zjm;\*3q]A
u+bp@S`a6uo3NoP'1HS20?jHEE?Jd$(c!:4b?(='TB1<r9!LBiQ5*(%9:t!X@b3gceQr4%\?
bES(qPfU61O8/^9lggC>7="/D!E'@3ctp3aZ*<eU3eHC1b=.q]A^6p'@`dt<S#;"!FCm)(a-L
'kD2%dA$jhC#=t9:J-P]Ao,V47SW(2#&V.$STZ1R97`iC:q]AJ@WjfHGM5nPS_ffR1!&Lp97ne
#H,qX7Ba;#doX+nS_ferErN[Q/Hrg8j4doSVqUJKMPAgM8^SmL)FlA13g&L,)kHjd\;U`?Cj
jdlRnWOB`,"F5c`*;Q6r*'SI]ASjC<`ROhIo4o,>P?4ZT*lRBTZ&XBXbjD@_H#U8*kO#f_tfa
:R<a_fNO,Tjd*..\pJ'i7-u_Yu=uAoYMK%?MpJ^;[C%2;&/tj2p/MUR6:s$UR72m,9P5#YgJ
Ygh%=S*,5Out2/\ng:n]A@6)nI:(0DnWZBB"uS;nYin7:8\;lb$B]AI!h(F472LG+.$nKX&_X=
R1KQo6E%4Wnb;moj7e_@o@<GMGHVFk`D:+,Il@JMt`9_1-6KJV:2C7s5&;@NmU>#g,Oq=e7)
UY9MTog$nr9nhd$?f:g"&Df'f7u]A,8<N)hsM^5#H8@?T*f+#k-CED+N,hh1Tc(/:Q=;M4Wcj
F,Zr//LG,):bAA:+ii\A/E_gO#03pGL`)?Ys]A2l\*M<q57dr%GMj0ghUujJBe9B*[i('j%l^
B;7ZCK<B2*INNITGncKd(p,q<M11`J/If.TRdfWQeo4%/6a-GcJD_%18Ug@1DgkO>T!8i<h)
F'u_Xs]ACiW+;Y3$&tZ_"Q>.5Wkc8QCWj7'$6c(CFIb\7]A)]A)I7H4D:S8mN'Dn8aiWcR<W5=c
kg22ki6:>j[<7$r@XOiO%W,f@HIQWU7t@^di,e6C5<'Wikp'PlSAi`4:#%(,;&SEd+KUE^c*
,e"j.[YXD*GeE0nmn\K`,&NQ&r#7Uei*qnh:H&LPr,Imc!H4<JK+ArN'?!`ia$$6EnRJITTJ
ArWkTfQL?4c_09gMPcDPLO37sWYa_B-]A)linc1<=k?FnTA>[RkohWraqSu-/uP2;]AYSj%[D#
j0Dr`nW9G*E5jR6CIg>qe&('C@hDY@;;,EHcaCeK/6k/?E0Z!6"$E!V)@BTRc.))p`QCTm!Q
A3aVAf7JEq?TM0^e%&&1S"fraU[qa.;^`+mO'!R*IJm!HNa<?WTBmRVo$eVG(%Es'Dg/4cU%
-+f90R:]ACF.IaF.tc2`_/m$oS8onl-FifOJA-F/%-$6WDW=Zf%FRh1F68aVr,P9KIbKpS,=>
5ZujN\KRPW`7-G'rbep`6\"-uB7!]A+%F7F6+S2oaqo%;UIi2`fV`CRX0k2Qmb@W?<0m]AF?$!
+mFZ4H5.q""]Ao?_nP).Gm:_9g$5u*c)E6JjbTVUG]A9V?g24F7mT6QY'p3?[XGHNOc?Ei4.Vo
=6;mZ7$404p&khP-3tIdq7RMrE>a95SC,qsgkWM)*6fKigphN#cOjh*T)[$U7[.1r?-r_t+<
5:OTI5+&Ui"D$H;SX#2_Y[UgnOG]Agf0Sde_MbRJORec34b58*]Af]AP$I%&R)Fb=hM$[sp3-;&
Ih^/]A\t@'l#:V%KmIm"WC!&[2D^ign"_ZQ-A:-d,9f04'_>*nY`DSaO`XHDdle,:K(]A]A<[C,
`*+0Kr+"7\nW.d1\R'Nn8(JOM&Fa'/UF`$42;S68Dc'u4XEFuq#W:3h)ZW,-H:f:;Koaqp]AH
lHZM&b)[\N6ssoJ@+a`$+(/*VO*A$LXdH\GtO@@4V:)mn@0S3:'a;![4M[VBTAC?cGPYH_)s
0L!`QOa/1oBr/S9"k"BU2?5R'5>\KJtZa:kU6!+"k'V2kG/2..3*"+BHFYob?JUm$TXkl$2[
,X<6hmJsFll?SJf/OMoc%?pNTZul8n0?Ie02HB.GmBUmX\i,ehR.u)7e'/#crmJQ\E#E`Ijb
Ws)'bAiM,>Z62;5s=d/6'b[r'@1gnsXd*[1!qHIPC=k`Zi%\H2_dZlou,b.HiPU`+LV#e8nb
CIIK`*,H/Zm"ZOL.RID2MMs^iFJo\$RZTJ`4Db/%6$%(Z[CU.)`-G>_QQif*/%_E#k+,QJkU
UH:Qhn"6hu:9HDd^+SRaZ9-U)"l,EMc$e9(4%AIN=KnHBb:8Qg`L6=8%H3UY]Ah[9>ZuO_:4/
&`.jAtC>$/N[O@Io-F*[Wf3M$fc$-##dnj%bV&p>pr3-hNQ'8ODWMV?.BkOnYgj1U/dcFrn%
oX#[BS\@XR%b'aha%6JF_'c+o;SNPO,>RD/)=>(HrC"BZmFIk4W\+kFfD,o)+K\[nIQ@551<
5:\.Yt!Jq_.r>.MA%PH_mj*cFgA3/-9_A\4m)KO5aeK[]A^<No]Au5]AIPo+$(>q&-J&6!9TdiI
JboG'1jiUcN/1kPRE.K2&lc-oS=9BJF"5FngeR?NI,D%HQE.+G`sFsd_;+Q*P]An22NkJZaB5
;gES@8W\FeR`0e[IEEQ1\n-Kf=T1O`h6G!Me6oK-l)?K`7kO8gHiuB[ND[rHdYP]AGHp[*'&I
10r=ahUO;UscJO3ODZP`2)IpF\`?+tZBRs6@?*utQW<DZ5`DCsf2(MYC>u1Ll,a^Dhq%!\-E
".r(a/h[u0_eHm+Q"B&F:^UH"Ok-/M,bLWp*B^6BQ=Jp"J_c+S4Y1p:jLPYg_KcTjM9k()cL
HkN6"#*hoWiS:e2UP6t+3qr[*?SHL-&6'aq_^G)5Ed"#3^XT%(@k>p&YgFT;f$>]A&La`""8P
4PLLh5ORkf%=Z:M^t!4tN\qFR=-KWQ$l&'E5LK-7M3jM/Dbl?:RUVH]AO&q(cSS%U84j-;iG0
baRVo7WA&Wl?]Arg(p]Ad5&76,.'PYF)KHce#F:34u_,+]A-jTG>-V"&qQ.fD<_"=lrJOIAfG4h
h3n!-#K*>!tN,u\)gX"SSAZl-&4fNm.KVMrCr6nlh#VAIs*Ga6QET!gSDfW:tD1mgLQ[Fn$r
-nI^9EsFMc6&QA`M?C>R7qq2qj-FWZu6\VN5U>]AV"+(QNN(6aH@O9\^mU?tk8AJ@aY6\#qqQ
0_/Q<0QheeKJ@JK%uqgHiVb*U[:LZ,>2G!W@a/,kB[[[1W*^rFY/gTWca7VrQoi4GG&k-krn
khYBG`69?G+_::Q]A=XF'&ZSDm(OA<1bgoVsCku+VJ'J\jBt#]Ajp(h_L'^$+WN\YaVf0B'&<V
rm&m^bt`&`rZj>2`N@HUh1N`DHKVgmV,$9mU?RdR"eJ!<0/!iZ;+W4GUN>I^27(=.oNFA:K+
<Edd9]Aa5Vm56\DY#BrJQr,!ls`-u__u`%'Q1^aB>]A%e%jb7bD:;abk$`2)YuhLBn\e*c%<Sq
YFFP\)]A:NOY-e6I(aeW2/lE[b/lN.)B?W)4G6/sR4B0&^"Z^$*]Afp-<I"/-k)"LTCRS5%I>A
^1lT,mOKi">O*e"Op0glJ@,a9i?8ELYT9^n#`S<Oj9hRZYVStO5q^:<(T0rhM*3h^Sk.q*%`
q/sL!*W2!.MSA2g&Z^I36&5UVh#(_(FG:+EF;0O2(*b.'LSsKQ($CLLZU9o5n"la6/`&)Off
7e*&P'0@-F.]AR+PInYM.9#(XIVo)5hP%#`#70*AA9@m*51,_C[G8L2j>80ds4H!6m_mGV$)\
Q3E6Zd0,B/Bm?P?lcK>dY?Z(JLI+B%$(O2qt5B-[QhB.NUUVJu5nO2D`X0CK+mVPfMcYSgdT
6q\f_53@@8#d.bV<ReC6S1[$/INN<;</;0OX`he0;28Y'T;LF6Y>\f6:[0P&!e7)o$i@"a)!
dubd*NUq<9E/%rD55YL\$K05`ag$.rn?HspD(,!0Y0_\67AaJN-c6bBK!124HopOfBg"7[Qb
YFJILIbNH`2gbS@Xjh#'"78bP&6H1f*JY&l$$17.Cu/bdCaA#;LFUC?i<t>P0u26j3n%b<rC
i)76"#b/9fq_@kiQa5QtXdBFVGYdk0pJi1_nRM]Ao(IkZ2qoSa#!n/N2d'llolR2`p5&Lf7&:
;e.S\1Kq51?Z+DU7Jo1U+h&'8@?<k%aj;m2U/'u(!'SQ)BQSR.hVU.![*J.a4ojoP!i7"snZ
XQ,#JMl&n2>G92>Y2>#*eLE$G1%]Ac+P]A1!I#Y,Pr9D#ZLNs.s^a\TK*NX^8e4+*Vd8\U8!/7
Gn-Ti6s[Y&p;`OkjE\K.HWXi6e0VQBf>5`B.ZCi8Kq>uPn+"sSHTq&e1jAaD,Ha!d;JJ$\t>
_#@"$fDM>4>3$q/?`G,?ViY&t1fL,$[kEI_N4^LS6A:Om.0ckhi?72Qo$<n7o5r.2]A;-k*f2
l436eb4bTmUadU#iR>=bp?7167CB<$6KOH+(I(m\q7"QXJ\"d^m;K\X0,SVjU"SA7CSY1ETP
me?_\D"oBp?&Y6LpmU=`cf6;,0/c(pD/iRo?1UIlJY.]A^]A=B$&MGYchAAEq@@GN^&b9fgDO?
9T)2+OH:PILQe_AEE]AL'j0sHjF+1VfD*.^>6,mgqM,^iJQL^p5<K*K!lh@H&>J5GOt@Qe\MS
gOBmT<E[Z+7F[dD\5T=-Yb4Q`o[gqGe5fP(J:qRT>b(DmhLdpgfi1^]AtgGs7##YW*0hU7t@&
$o`"cfN^VBLrPrq<ka_]A,3A.<)7)`oS,fUfs6PI%VZ>YmKH&XJi^Ai]A*]AaLT;!NJ4L5lZE6g
$6<qjgb-4Bn?dS/#[DdWNl_SkG-AU3E>(U4\2uo=8-?/\&/`-8PP-R6mb<`65j"Nqnq.=,]AB
-WC7%1h^>_.`4iUHK>ds^aPsg=^O:9.n`"FmiRU&-\7*OFX*%af0^F;5,77>ZQ&YID>?_K=P
;8o2Hkffo4;`kIB0)0^f*2/6aOom)CjCh"hURYD7.$J^#:=2oSm5du!i>a]Al0tJ[+,ESuYb'
9!pLBXRp3HDXZWJ4o0XU^8020&70T?;gVur64JUDb=G\R!]A_q\KE#ELmK'UCfBd\(*p^2hYK
9&IXIYpKeA._"@<oo&qe;3BK:_.1cJooun>QeRbMb&SJ$<anHDqIDO3Mu,YlA`OQcK0?k<hr
oSgMGM^Hqf<Dl`I-.7p;TfVd;;GCQH9&JWj%gsSC7_t;;Y]ApIdR4oH"F>Rlb:C+]AdBRM4BU_
sCVn0N>"9?)2K[6(mJO&Q!]AHuh1bKJBGd/Kt>9e=k`/`(/juFlui#4?e')e6cf;M4*`&3"n]A
6OS!I:c]Asg@V/W)!RcLdJu);=jAV`Un:@S*7CmX;8d-3,9u"2o.t81-m-!V;sV>BmVa\,@cP
@qn<klp1CUoSa.[R8pgip4lnE<Q7hq/PJ`9>Ws!FE="U&r3^(.#L#D%iA@]A!/@07PEaG?b(f
o`%+jQKOH!=*hjtVeP#@p#ND46"V[c#J-^QbE-.gd2E<]Af4Sc?aJX^ABH?:PQhR)_^jS>Gp,
/P6\U$1mDaRlWm?@h<#;jR*&\priS"`7Ea\`'ZEPtGV*#:PU9qG.kGNm\$rD,afWo0(&oj^d
o1C?#j+`ibp.'F#i+:_?'m7G"P^Rp]AERFHsfdYmGki'Eif2^*SNGQ*.!2[N^9h>N*jn&.R?8
EA9$&pslj0ObQs)e]A4bOErsKMdca_Z,(OcOsadS<4At<6i7XJDNXbb$"8guiNtjSUoaumJ*B
N_(>/E&q4$;($@Hd.g5mWi^]AZeU%gkuCPO'Kdg%R\Ue[:"Y9TaXJ+TG!5+*i_"q,UrKT$>2E
^dBE9X)uK+eb]A'/[5.BsYK3GV:tEu'rl:H&%1@"Jj5':D$8,(\G2fV`#,e:?M@-_N/Or+<DM
UB'7]A2IV\ttN>\k>V1<.%Ko&RbGE?J1P63DB!_(i^(gT4*)?_SLd;.Pf0EfM1#)&IZh%Te`m
RbAe6#kKE9E;b'[l!%4-j]A`XYeVXkdj;s!hKi0nCR?.d3d=mj0#]A>MOW,kRX2pUYEK:S$c1#
u>pGm$rYNoZ@1a<Floq8c;]AA2.7qt9Vqs4_ZRl,?RgV!<M7[]AYLhI6+@s?sBhejtT_Ag:V,s
$5]AfWq=3`HQ`hLa;2m5RVbF6V\'nsI#X-YLCL=T.XQ,Zai#"Rm!60N#R?T_HSZ,rE*FU?L+X
I9rA0PlACpr[7l<e+)7<W=s8\Gq"Eo-%9mk;t#@47]A0\@\.'1tCaldDQ##)qGTbh^?S^>n2#
&sUns8LWDel<8a"mVVj)Sceq;IZ_Q)C!2dFOs:Tj@B&;A?iFP`*<@eYBnhDSd$D"nrFIY@a)
c'68Pu+.u=H5Ho-<?JR-^]A_I%NO(l-`3Q?J"IX0>am(g%]AC!sK&Xlj-s/]A%r(M5dr'cUEK%=
,SP;8MG'BdrP<--m`f(QFop4(lm$;bZ#fW<qC8Wo>iC7"=YA?>N>@T%6^6,(ChD,Yb5A23%'
H#pNe;*11bk\%4kPBThNmsVNUeYW48@okbO<R$88)s?@<PE3:+Qr#[2p,HY$L0;0u"8Kp)YC
d->SsU&PgD%Z]AUJOsm[#J1r>F]AJq2$9@j#@5$!!,'AB_T@3@6Js3gH-[2h!i\@5S,*Ya*GVT
`!DMdd;UIX%h%,lD$a7[ke]A/`(>)^-!`uCuVg8;isbHJ1f!ZVU/mH9.iQE:p]A-Yc3(=JpHFd
;JhWp]A3]A6Y<*TW,th>oWTiKt!"]A"c<5X,8PX_r+]A+nbC`OIjad$qniA'!rdoo$:`"X6j5kfG
SV$O1mcCCrd4ll[(J+?eb]AlOR>:hr6dcroB/(toQ*.XO(Od>gd-cGD\,he6P'&7&5:CG%gg"
4?:61;#W>*TO.+PK8)T_Dj?r^5)^9d/m[mJoRJT/K$G9`Z2*!EQ2JrE=me7sB23I33;k-GWS
XfC<Z:.UI$'+tr3kFL+JLS=E0kn\-$n5qe9HqrsFWJH\:"+E)aWHqQ@JTfZD\5C_N<)<=,5m
g;,=>>JK@6tpN#1.W!5_DKUI#e^)HYtV`SKRL-Ejb/S@BfOqYnl$9iEQ"[f4Q2rI5(!?)8j2
Z:1"3XnWO(X05tTr>aqtG8m!%5qsh7\>EC/?Ra(,GO"kbhk5eA`0&_R]A<"j&f9>[QN`U$kj(
mZIu*GQnhQ(dJF*4nWd7`<D=]AU$jf<?i<H1>K*4RV2pi7UF)ee\/0ul76Sh*XYU>r(.</OTO
"cU^B9nTi]AQhlPFZ:GL%2^ZE<nc[jOjGZ72uQaq(N^AisO):>`N[o"`=W+:;\TWpgY1&%>!Y
mZP"U=!h:XKC+pqK(!)5+h&tP1suF\Vs?-Y\c/`cV,oQNC+1?U+-Q3jg5>f8&j;UYkgN-7c,
-B?_VNT\SWf:!6Wm3,p-`GOb>!&F_+*^-E?0a<\Mg=<#nLjpe)?&#?V.)Iouq2A)YB7FPp<'
io#TojH(h1b,isK5NZo'QF^9h(*X.Gs"_s"fim^h\:Teb^JN!TG1!M;c,8\dM_7m*Xj$:ddT
'hF#KFXmMoT9(Z`C&^[l4t<L&L+C`q?@.f,`LO'P<:cl>:7q^;08#94=r`Ek52=[S]ABrG(TG
*7RrR6HQW.>3`1_2MKSk-V97C+t+^tS1gs0,`AI*lcQdRmcG#Znb"r:2!58,-e<CoFZMWGoT
%Tau5Lb+kD[UjG4]A01=2&FsD]A(]A=!lUX^_*Es).g>Pb\4FZ`uRo]A!:Y!W~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="472" y="0" width="378" height="540"/>
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
<Chart name="默认" chartClass="com.little.plugin.rotatechart.basic.RotateChart">
<attr refreshEnabled="false" refreshTime="10.0" style="{&quot;centerText&quot;:&quot;中心按钮&quot;,&quot;circleStyle&quot;:&quot;solid&quot;,&quot;circleWidth&quot;:5,&quot;centerTextColor&quot;:&quot;#ff9900&quot;,&quot;circleShow&quot;:true,&quot;firstRadius&quot;:80,&quot;centerRadius&quot;:42,&quot;radiusGap&quot;:50,&quot;autoResize&quot;:false,&quot;titleSize&quot;:18,&quot;centerUrl&quot;:&quot;https://help.finereport.com/doc-view-3346.html&quot;,&quot;title&quot;:&quot;=\&quot;旋转图插件\&quot;&quot;,&quot;circleColor&quot;:&quot;#00ccff&quot;,&quot;centerColor&quot;:&quot;#ffcc99&quot;,&quot;iconRadius&quot;:25,&quot;titleColor&quot;:&quot;#ffffff&quot;,&quot;centerShape&quot;:&quot;circle&quot;,&quot;rotateSpeed&quot;:10,&quot;rotateDirection&quot;:2}"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<AbstractDataConfig class="com.little.plugin.rotatechart.data.MyChartDataConfig" pluginID="com.little.plugin.rotatechart">
<attr img="图标地址" img_customName="" url="跳转网址" url_customName="" tip="提示" tip_customName="" index="轨道编号" index_customName="" customName="false"/>
</AbstractDataConfig>
</DataSet>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="532" height="540"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
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
<Chart name="默认" chartClass="com.little.plugin.rotatechart.basic.RotateChart">
<attr refreshEnabled="false" refreshTime="10.0" style="{&quot;centerText&quot;:&quot;中心按钮&quot;,&quot;circleStyle&quot;:&quot;solid&quot;,&quot;circleWidth&quot;:5,&quot;centerTextColor&quot;:&quot;#ff9900&quot;,&quot;circleShow&quot;:true,&quot;firstRadius&quot;:80,&quot;centerRadius&quot;:42,&quot;radiusGap&quot;:50,&quot;autoResize&quot;:false,&quot;titleSize&quot;:18,&quot;centerUrl&quot;:&quot;&quot;,&quot;title&quot;:&quot;=\&quot;旋转图插件\&quot;&quot;,&quot;circleColor&quot;:&quot;#00ccff&quot;,&quot;centerColor&quot;:&quot;#ffcc99&quot;,&quot;iconRadius&quot;:25,&quot;titleColor&quot;:&quot;#ffffff&quot;,&quot;centerShape&quot;:&quot;circle&quot;,&quot;rotateSpeed&quot;:10,&quot;rotateDirection&quot;:2}"/>
<DataSet class="com.fr.extended.chart.ExtendedTableDataSet">
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[File1]]></Name>
</TableData>
<AbstractDataConfig class="com.little.plugin.rotatechart.data.MyChartDataConfig" pluginID="com.little.plugin.rotatechart">
<attr img="图标地址" img_customName="" url="跳转网址" url_customName="" tip="提示" tip_customName="" index="轨道编号" index_customName="" customName="false"/>
</AbstractDataConfig>
</DataSet>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="472" height="540"/>
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
<Size width="850" height="540"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="5"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="e460f9a8-cd52-4865-a40c-fce46b475c5f"/>
</TemplateIdAttMark>
</Form>
