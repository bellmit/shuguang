<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="疫情环形图文本数据集" class="com.fr.data.impl.ExcelTableData">
<ExcelTableDataAttr needColumnName="true" isFromEnv="true" filePath="reportlets/国外疫情数据前10（丁香医生）.xls"/>
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
<Chart name="默认" chartClass="com.fr.plugin.ring.custompie.MyPieChart">
<customChartDemo custom="{borderLine=no, move=no, startAngle=yes, len_p=center, seriesColor=, animation=zoom, titleTxt=国外疫情数据(插件图表), highlight=yes, bgColor=255_255_255_0, load=yes, selCom=no, isPlay=no, fontSize=16, theme=dark, isAsc=yes, selected=yes}"/>
<ChartDefinition>
<OneValueCDDefinition seriesName="国家" valueName="确诊" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[疫情环形图文本数据集]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</InnerWidget>
<BoundsAttr x="0" y="0" width="430" height="270"/>
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
<Chart name="默认" chartClass="com.fr.plugin.ring.custompie.MyPieChart">
<customChartDemo custom="{borderLine=no, move=no, startAngle=yes, len_p=center, seriesColor=, animation=zoom, titleTxt=国外疫情数据(插件图表), highlight=yes, bgColor=255_255_255_0, load=yes, selCom=no, isPlay=no, fontSize=16, theme=dark, isAsc=yes, selected=yes}"/>
<ChartDefinition>
<OneValueCDDefinition seriesName="国家" valueName="确诊" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[疫情环形图文本数据集]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<ChartMobileAttrProvider zoomOut="0" zoomIn="2" allowFullScreen="true" functionalWhenUnactivated="false"/>
<MobileChartCollapsedStyle class="com.fr.form.ui.mobile.MobileChartCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
</MobileChartCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="430" height="270"/>
</Widget>
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
    }, 30);
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
<border style="1" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[国外疫情数据]]></O>
<FRFont name="微软雅黑" style="1" size="88" foreground="-1"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-14803426"/>
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
<![CDATA[1333500,2438400,2438400,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[3619500,3352800,3200400,3505200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[国家]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[确诊]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[死亡]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[排名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="国家"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="确诊"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="死亡"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="排序"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="国家"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="确诊"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="死亡"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="排序"/>
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
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-14803426"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-1"/>
<Background name="ColorBackground" color="-14803426"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m9F@-P@qH2M%D8.6WK?s['\7r"g#jAJosQ&;G!c*[V17jgjoG/!ne\q;HbAMVi<9$lENm^6;
[A-+\%<Q6E2/:c!nHo!Kn#"0Ad(ZAi`FQjuT*_?N*d3\1Z$mpO/[s^#/^tTDSMKU:4X-SQ;n
#Bk(Z5[/7%VI`KG4"LbR<p)2N>r'*[<D2jNLMIcIRGJ&\`SK?OL0%sNmE0MIC;?)C+[25j_I
;f[i&-(e\P>qS4_"V49dKeXY;gW3T;9A6hb@P%pP5dZ0cPp_+M(nL^OUgNb9X<:Go-cdX&WH
gbIZ4?WJ-iaNEHS0#d*[B>c#rXP']AHEiWL"t/k)\f9iP($6TjE?F<I3m<+U/FL!"=f>2VTgi
,I[L0P_S%4QeMnOrnGDIU1tB6XEoN4A3bPC*lCia[f/>)^TQhC9+Z:`*nNF^<(U6S?^FF8L]A
0<W5Hh>'p/7EjEunECq4u*VO^)VHD_(YV)EVD2J4K9#nD6XG$g+5>C4=:>YBJkCHVc[WBO>s
u9aVB)Wghnd#l^G.:%iFC!!,OnTGD0818JV]A1(g!\_C+ja7%kYklt`E7\1*KqgRfdKA7UX(j
u$c@j>@Bb:`E.rUNNKO;m09hMLV!:@*fh[0a\hJ&[rK@.iVINf<o-e0=-!iph65!]ABb<+:;M
;Q7r.E<k[H3b:@;`%iMo$;@JBib\_c8[p^<,udVr9X&@9-]ATjO=/r,fh^2?B3cZ_t%dQbum,
'm?Q\1`o60`^h5$&kfc"pnRUeI9@DY<)27eWbu`Kd+G28bTDgo=BiOKVg1-+G)GG,RA%Oe:^
a;3&&/Do^`'8ts*THf)X=(8,Q!-RgH0ul7ZQEZ46mhUeZJM>\K&n@!ga^1;nl7/r,3h1gg[N
n*a@=Vj1p,IjNF&NaVW%,q<#H9fZ+fXYpjW&ok:(a`db=8o6_qn`"Y0&:E55-3B%?dfn;-u7
iMXZN]A`*_KnRJgS(MPl`Q,YQSe&(N-<G+d)e7,QqWE/S+J8,PeX=HKXf_A++g`ZNrs0(LeSj
<Ymaoh0dua#09IZ.U'2<SjY2e?JZL:Y#SbrlD^!b.\d$<\8?,*JgT=Fe61jP:1-3(\%ot?*a
Rq$B(=-+?]A'*:6B#`&=HjqY7!*.<36BA1t[Cf02\l=R&lh;5]AIogXEN4-r1%_lbcW[FK3@>"
J&"bsCLL$NKn#\s#qqPH"B@YD58:>i99aLLS=@Wg#lgKZ(m`$r^(Q:`llC;c#rIed6K<0uun
?U.+<e]ABUK22TIj8F[bA!hL[$=VdSQpC/t.1\9Y[\"XpQqS]A2/52DZ,73nSX8_oR2jJ"4VRn
?"m,g?ZV:n-,3mI8+:,=P*PsA*6l-jKgf$#Q_k/6V<i)%;6kk-[kcNT<BO,]A<m4ggl)n9BdK
G3RnDs-_@-pH=;n5K.Sh_u0-iA$7F`6X?SF9.!6h?Od2=U7fJ;X$$U<Q?gC/?T(*?n:4fCHX
j!/W2k3T68`^f0QdHb%EA"5MfM1n>nlf>qk7o:OD]AmnTPDj&9uL,AQNJ7_R%f!gqFjdcJU%N
mZgF;^]AFl>V;o=o`C,B.uq@GB7_/2VUN:nkp;-a;DQ566EZ]A;EDp=F`mbq^6-[<Yp\W'E+Oh
k'KOu.#&,>fNE&/;XMaM]A&KrhjFu#hd@CPbp@W"j&H(KurbJ27WW4+W@]A^DPo3K)o$[_(nnF
9n*Hj9_f6P:+;c8b)%Q7/Ysr`K@FHYl+Z5>f%dAH:6`m>3kfIYY8=.3V+*.ceQ!@^<Fa7bSe
$t+`k4f0/=ICk5.!28j0LZ147K[6$@)@"+(UF!P>8ErsGB`&l<V.>tNIC3OB97`WK?l@<;nn
8N1b[6'dMd.*Oa2Nk1Q)89S(]A=..2boPHUM">9b4FuQN5FE]A<jAWb&^/pt((Be2l>\>XS93J
*<C!tBlL.SR%3DXg"+Q4Zf/$=uXQ<0V4_^^S2oXJU(6DI12Q)L$)uH9R@=C/=+,1?P#Bp4_.
5L;k+s3$T:0(`&]AKPDWF"&N#,TJk=1:makKhH>eJgE]A>fXSdY;JJXQri(e7$]A`ZWS=eY:QSj
9`'RJ>#&JgKd0sqPk4tO<kGcn15G@a#<.'(d"Hb44u6iZ#NJ@1pV1h'$rMQ*'AsDB0BrTA$'
JZ-i-W1%/"=I/mVVYdVdI?'L>V.I'O8$(+'TMa1=Zlk=A=H<T58$JF\;Fq,b9j>t46a"O4#0
X$Cta=$nG901rC:QKfgj#mm_$f6",jnP1fJ,-LW!JnJRb)cOd^9!31F"dBl$iq@@sc4NNBmA
`^Y@A)P+I**$VlYL#rU6romH5lB_Rb)tVY[.e^17?:p-)f'jF[Gm%[u$"LL2>^bAY]AFL2qld
nET0#'ke4oJf/E7t?#f`\RQ6S/F\gb]A9jcR/oj+<qHHMAg5fGX[O!Z7Q[R!rh)_'Yp]Ajq?FM
Culh$>4C[nB.-JKhKEGK)MUbdpMHu+o=)Z>j:A_o'PG0<@*_IJ_PV"\NH@jp>8K0`h"Hf\>7
Pt4InN[?bF"OB@)Df8?lNB\;0Ca*"]AHW85r9o#D#VcHAn8E7H,U=Ad@)bUKE;2!Frd8Ap_m.
'b6^kDOTBtT/kbrerFgV,PF5h\GC@R&?S?eeebeA,Y+l$f+P`YojbZcDDq^pTP'6eY8GiRc,
#+@ks^rE1`L)-SGBgi$<;gr_<)rKm`3cPd@Z0NjG7h>4F,F>bG0=f"%Qp-rc3>sTC`u'.+JQ
66[=9&`3"^J$ri-C!XCMVmTopL2WXUO#OFaUpCI?+Tb5<XFC:j<]AH=,2MIh$Co?;u)3\=&"o
dR'>*-*"P9eW'E#O0!M/9A[fp(Yife53_>&Q?]A)H#Y+=Y9V668V6JV%XF`9'DBFm\@.[9Q!/
5\Cn5@2oA3RS&?BGWXQuHV9$V7eW5T0FUVStFQ[o\=$'D)SagAXsG!u*3JTSk?>3oDrU/m!(
p^VWF\WVE8c7doE</OD3m*C=ua[o@8$^*7'B)BIlgr;el(<\Y0!c'8@,!6+S[&DJB0[al_21
,1HT;_mt^=hiUk.U1ndV",ud_@oa90_,cj1&lV0/H_J;gA]A=j"VUmOfp>g0YIPSL_CK#WcEQ
Keop73mH=i$C3'N-N=;i1`JZ)`5$hK4jfeRJ`=Mo&V(JGnU.;NmAp<DaBTmUG&SE?X;*e"0j
CW,nO(29)&sMXp,JO?//_/,scJYak"2Z7:/Ml"aJ?KX@16D\VFE;LqqZ50uJC#T`MS$FrqJ3
kF*'\&bci>(!*`F)OHCiKNM,$B3ErdL-0Ku/sSPAf5fnGi/l+-T/b_M+2Ibu2G5#5[UFmb%A
,>2Vs4uit(]AhU6^"*5MA1J4GB;LM1.4^YELI%Dl,J=e#^.ba.bUQ`st7Fbs8Hl]A%TZ^5JpH?
[je/AAFtl;KLS[i*^^VfO1:mtH%8Z'P6b(qo`FdB%%[5%uXt&AflJ6-SW[@r_@,HI;)T8M9"
K68K_+9r?#7#)3c4T-I:1?=jMZDq20C*!2[#6"=+9HAt+dR:"&X&S6\Mh9o:?BCq"g0"fe.:
U<1J;?#CQ<[\qQ,jo0!N.#2'14]Aj89[/rg'UT,"4j<c(Q/ZB)j'X4QbsT8LQgFcFD%,86,t#
-\7?jWP[:)7)Wn;jtfFFe/P&-OC-T*Ct"-s7&G-a7N5VU8*`'"CE[E[q^Hb0>&S*^7d)=?e9
'>OUc<ep>Z`k2ST6LP0?h+KV_1T;U6D*jh#D9I<O#?9WW(#IEGOX2g)ei@8tL'<23C^g#K_M
P5,mFssX6*6,Sk(caC(L7SK4@#6#YT0o3=&XFr(L3L,2[sN?/UBLiNAkWuY).r0lonXNCpgE
Hi=qE#(#faF@#b]A8W;JadD!h=i!-@I+0J<\;L^nfJPV:5oNSc?B?U#CB(6QJl*tu_"9U:lq@
RXb@12.C^;@XjEUD,f7)s*Y6QleFYUK\d9U8bi9]AU8AkSf9&<:<bb3',Ual.)_[W5cK2EfZ7
L[`sY1J`iI[8dj-XF2bS%2ZJM"q"kop33U[ni>fYi`BJ!G.DOIuIK/D9klgEKhh$IW*9W[X/
FlbSfiZ49J+ot'N\EEE^`%7s=pI^TSILQ.fLK;9*O=DmIKu0@$qr>,r459HiJD?5j_L1.0>m
eQZ+[]A=:keLT=D4uaLEoY;#m*(hB3>Qm5@(cJaotd)$9/<+4Y"=>"#>H59I8BQ_?WQ+n[Kld
9JJ"dYSBm@5Kq<HE^;M.?[o#>uS`0p0R\17K<5rD@.e.UtCV(O?hqcqpLOIT(/Y'%M,rafGE
+9kc,5]A:*6BBPa<b"CAVt&oH+5Z+.:!8L2(b.Xag*mZ02Vp/H=M`F\9GgQ^EG-(8!()M.-]AD
Q%5mj5GClU$DQ)P&_#Yj\gS')Y4SooCZ2flAIH%]AR'J%CH4PMPS]A?VhtPJm[]AfNg2EuFT#DA
AIc@4.'os<XCt".U`Onlpg*ZCam&p"T(+((!`F6+&[X3X_q"Ld<O27EBgjT+kPR]A;6$#$>[G
M5nL)!U/eu'NI&%1s<U3QN)-O8X$-kC8Wmm93"2453inD+jW:u.'j>G80&Xdb3Ur&uC7pLgG
hVh@T$[:O`qh[L."&9e>l/IL.oSK\^":VFi:AMFbejbY;Z?+0RAg3LX81RcLqbcWGuCJ1R:i
RMlPOEY2O6jdXj50QpW1)9Kn9r@_?\QD!R>r*onhTA*21pa$+/[\<QT.U3A%t.N^TpAI31dB
*"-4,QDK%'+jLF>m&bAj$060eUiX`3>2mh+&[A88.:)f#'fja>PV#>8cj*X>LAD'cP/.YlC6
Z;f3J@_\O@.#^X?m.=BPkPSr6[$&b\WcsZYQS6BTR@Jp.rFiAK[l%X7h\V[C+u._rZ-t9Lc'
c`L*6U]A?F"eZ<5IXtAboQ3%2gI"+roWHCiNl)??X8aQ9[7cD?8^''_-gL20RBK`4C3+C_%7I
PVR"ulS]A[]AMLbb%bSg$B69>-bpbD-XlA2@k/gpes*EXb.ark@]A_8GF<m(4r15Ge_!I7]AO^=Q
ot?F_mJnF;:a[")AEU"Fl_#gZaq'e:>`"JjG.7%^nYfbZ3o^$IR68u?[:9O)C<D!(#R#mq/,
6jnb:';rOA2EER;b-&asc,1q%=?a?7eP6#IhBaI;g`>=^4K^(94?J+t$TG5;=&-@`XDdnN0X
X!,_aUMRD/TI5*B&')D?4IcM0AnRAp!R7lO,k$CugglcnY<T6Tg``;f:^6`ccb:2&N^s`aQl
CrF(c+kH7FPO22gPQAL=^@<3BH3tfGnXnm9nO)+q?imTPO"M5r1[B*[`?6bS4"ncD+'*WYsq
WpBB\c9Y4DYlcK%L5<N/&Mejp@@J5o'>a%`,jN`6"Nufob9]A:Y1rj;W>O^H6XkBpVqY6-TG]A
Vt\P4Kp&:45SuV_CQ'F:fl!`ku`SdQ$69BJhX^:[%3oiGc0!:Z]A"!urS(9n%F`Joon2P-gQk
_1nZg,:($`5NnTreFot'^IfbBjmQf.=F8!KX;kh;\=?'oUVI-KCqh5#/adtdO%+eXt5r!RRo
WE[T7^V<b1B?[s!40O(6W]A&.o>R9ekTp'5C5H^eoR#F.\[KKlCn*C2u]A3U:Jcg@TEkfW9%`4
pPfYg)>IbQ.*;nr0=:5"27hAOdS"6^^'*?46:5BWd_g8%Uh!kL;!O)S]A>O0?1%:;@d`Y9@4,
3URn^7B@"4]AJ85pWe*?V/#)ulPgnS.RE1C^@Li]AZX!?jm!d[CD2BPNnK1RU6pi'DZR#3HjO>
;8-p>*A^t")"JhT9*Y0M$,C2qYD6S;J=q*-%E^]AGd(j&\Y3rss!/1gpbihbQ8Q'G0<2[-?T<
SS47eLLJmud,7[uLTN+MG#\:A1@UOIh]Akm?@rc3)]A7fSM&5",RN^*.W@HSne9<R$0%&`di$&
TXK3ISIa5UVDuPAdEO:[%Yh6N3EC=bNR9aZAY-+);K<QdXHE@mC/=sam#,7i">gMDA<Qu^.C
)@=&)/EJ#Cd(Fjg>2%)YiqEb<Vpe%#5/bh*(m#H4A4l<p/+(+LhQll]A38@7dSU"N-Doa$*m]A
Q[h2SNqh(41GGQ:HpI6:kP"p6fcT0be1U^PK1Vh-8AN,"Q^%NR5^@^XfLP8u?T@m@8Fc]A9D$
hMm)%g?=2n7Feci`gK!:c#&14FtX"DaC)G->R7Vg&#o9C8Vs:F$Q+.#g?>r<M;\ID?S8P.1[
26bI>UVe`jWVR=.U3N$oNEf_`qY"+LgNGR*&*%;:nGB=rS?YI8"r,Xe?us4E>*pr@)'5aknf
>D,fcDg0;E7&08Pcc?^I@&GI<SB:8qM8douZ0/()`oBU:a2;A19q]Ai.oe2UTO3+;\OI2a-QH
iO?f]AP@D$8qTN7%5"?E.'JEJN/2jRWBo<&#Wu(nY+RbH[ZQ*6M3S-VD:/Z]A'7482Y+G]A`[_j
3Ffg"d?1$2/3HfJ1HC19PWU9,mKg^,):]ATg(B]AfF5nM2pr_ORh)':]AUF^n3L`5WfkCi,&7Xi
rFCl4K7gu&S`/7iMHqGDqq?mCr?foONA_k11DGX:bli'RuX+eLI?u$Up75njU&.S34#o51CD
b(V=k=2g;>p88ksVPgX,=5DWb6G5JchY[\#B<TN-2i@t5KT<+q43(`.V8]Acd1qNZMAqX#.(>
aB1eVl7V/pe>YuV?:%R/Ce.K:/16c<!OT*]A'"Ri=CA5ur4dmt3A0S9^>fBtf>f%sdZn"6DWs
pt._er4[A.!U@[a'#f+mtXaoY%5/[`IaP9tk^)`cFYCdX.-Fof\7aS2c.O8LB/03."clC#b1
oFmY;]A$"5%bjja)DCr(MeT_e-:mQi4I&ESS"O4\7gs#?:4gnYk8it?u2Nq-_]Aj[F/';TV^L@
II;WF[RSHb7/q-5G#=^Y4f$h_hBB;"pte,Y_!S\On5Z'9`*WHeFPULU=\a-J*4"rG>QD:U/:
_p;AUsGOGb8K,Hg<6(&e-q,h[F)?FE2lpH,eUaK-_]AlcL)P=tB]A&94]AdeY&2pJ^+MW7301!Q
%`_+/a`L<@Idg%CM7tJtI<s$O?Ee-H)n&iA'(Oe$-h)ce'\2D1Om9Jj=&8CF/`W^T9511C7k
g"8NraYDhu;32;:q]AYh5oht:p(s/_XJJgg&ORm`5qLOqM+QF$j34Zp-lB4Qfq)UEp,lUqQ@O
]AD>j"o2EpBt3+1YUE0qp$KVFjn&eTD?2`<QPGsUAh2Ri!7j^]Ac!9@JRcG4jo*bR)`&m"cj.C
TCe6IF#g6fDK4s2u:X#c'Pr>eZ>5C,J]AK"A!68aAGk(u<K\u6EB3D!/nDpbltk96SRQ4d4E_
hQs%VOL06l(,0HOp(&.OOV%&lD@Xm`H\J<$:7Wmu]A^nGs[q9WHF%gCG54Y8P<.T[Kh#W'I>"
r\I/nA-tFi#ubZKB="K_c\TMk^OC7WXZRAT'J<;L,<t>tN*UM62bh[j\*<e-;D^>?1Gpr#=m
$LD`?EftL;[c:P'EORqGQr%9`5Bj0,7oun=b)$Pnu+#R+8""T9u'.1O4pFiN[atZWPqo"9Y$
9a-J_D-4&Un^j[T]Ac6DU@MX#)"cu?Hb`AKP0<1E7W-pJRFje$t$=[H.GHt2RRcYEC9hbuUBb
&I.Q^`:DqoiD9-VUo^:n1PuqBW3Y0DKRm0?c3j1`<Cs'3V#-K^o[,";`I,i:'i8IHcGaD#Y'
-S0DK<.'saTh_4K=tAuE'CnPG6o/AG!]A-IV<7b!cBCb$9ZJq9I^"h#"=QK2MM%N:XY]Ah,dF0
PWB3TG*l6$72F1Sg]A.hibd/&$3o-VsE"eg8qD/5!4a">I*pMWE?):$(4)Y,KLZAA_5(Y1ZEW
fiH-'\XLAQ&Q&jQZ4<G823k8AaIL'>:%fA38d2iQ]A45*Qr/!=mEuiXc<l6oC$h)fV6E"r/eI
l^h%%hplpDQ1Q6k6aGU&o=$1aJE!^f@&3^S;&45>:8T_?GY:IB6PsWk3;V(`*r]A4E\E!POdG
64*/&X7:,OM8:K;Mf$cEB+je"E_cmbTja3dn_;u$UPe5[!o<ep#L;XO,@^?02oCXNpg&O.DS
BLcpEQj_jdhX#M[63QTT;BnD!I'_.(aMn2@jYYoBpFKU(UFadR64nHN$W"+cKuqk?-l,V7B&
%:'i:L(FMK^?O8Hap,$n:o=/!e;QD%U.kCkp`U`N9D-"aeXQ+n&mtZZHfI"+NQA>-%$-?$N\
Cl%G=4s$15Fj0<UqK3U=mU/.9Z+casjf]A<IBSDoNOoj"dqI#me)AnWEkpURi%MlJqOq)NTk2
,2&6N1IK)4p;>bP-bnfD=ErAjf%,c+^DqBE9\o-bG#02lQ,NYr'CmeX-JQCmrd^Q3[7fg</*
?TtRd_:J!Q=DIK9EYR5fVbTmEln4F$a1bl*:ds7WQe)m.snY[rO4gk/hOZTmKh+4f)\>]Ah:t
G22]A9WLmY03HZb-cZ<FuM?&GSk]AdPr)]AOZ$=/:80DJptUBlLL9VLjJ.r&Obrgs9f]A;r'0LSc
@irhQg2"H+VBa.Mq1;>U>,:$o@q.3Y/R7[K"CJb$1D(^R\9_&NW;r^M?lrr$TmNaNilu#SE\
iSVP\q[odZ`cE_clh:>ejF+_8!!WSL"qRCct*I"pZum&ot7fBUtFu&O?E!Cjo]AfR-0p,0J*E
[Aig=eQ.s;$jLPGm7W+te1kB)AaaEc_8Nri@8gQ-;;eMq''-8m/SlDc;A%`XQ(dUr)RsH)H>
/n!^VI`.jaA&uS$:(AWQ_At+Z@a>fYZ-ape=[JB#9=Zh]AK_l"%H.%m+QD)d6*$7)1.PsM34_
R%;A[O??d[CPe[K[H%s>\XV?9]A$j"+]Aui$dArLMZCIq99o-jZ)@R]AkUWelq#LEjTR9^NUk\L
1`RA%F+,Ju^kejW/g[$;[?!gW32jI)KSp2]Ah8W.@M+9'jEpb)6\(\XR@$5O+!4b%o$f^.AQB
P6#/.63F%c8[64tN@gq2A:*"(.auHUA=)K8H>tkFnZNRt#MMVDVg6cP;m!OFIlXLcoI@`nGD
p,c#>'%TKn%94O;O+O;#9e0*1?&fZ1JmT#9blKf'J8EcSZI(a:Q,\9Bt\:2P+j.AmM+%<^_.
gW"<KjK?&`#EdaE.eK%-E#&3R>tp'D_Om-aVP*f:!32>G=G3\D&bPiR@uVM`#Mq=Jg=u_XBP
rCRWIR9>G<>eQTVF(&NWReKLm\h&aoh-m0+;-,#!$ppN\#2;h##T1&!LM3FdQ$i']A.\SX4i:
dCg!-h?M<QBP%Q>+.2lc%:PLZY1r5H.q-ZcgEuH(2"+rB4ibF1Gm[d<c+$]Ati'LIq0ZPLu85
^RaWBSB:()!LEQ3ABclJ.Q:\1MC6ec35?INiXN>=J0F#*+3$Zu0R+A7G1Glm.t>,kNbb-%?T
L0_c3&LI<-eB_mqIYWXYo&]AkFt13[8cV\V0A=Cu]ApaLa7MI;-RSUV4`r>T8<Q7B.*>_6c(Z6
@86]ACaOn&#*Z6\lE?g>m`"]AC9\N!.-ZTNj2$JCk"rXc^n7LhrZ5-,^Ul,iu9'm`-D[`&oiha
^)4hH#'A1)Z%C<SZ9.)T:b6n7Jbo'p=`i4^:gFo#aiQ#FXi,&!h[5G^>`ER4XVWn@2Of_^e6
T;h\5Bl[!!EGdd(0+:aOEgdYqVbiiEY=5ne'4<Heq1:Qd6=;.HHHTi<(b10Ph<Td1CI?X3m\
O[I_T?5l]A;X_,]A*GFUmh]A4l?n$jhgl;[/9u3;_259Y!UiTaUac+jG_@;e0bsBLA"S@]A9CJOh
s00UbCBU\KrDU\55=r`hgGUO^ul9"ZSm?E^!nAh8"F$D.@qh5uH-%-/]Ab%p(#",eYJ(cG";B
%t1Q[Ds2mm1J;)FP>\V)W_(F.IeuSW?-r-&$'(3K:g9SE"7R#H:6Ju-IF/^,0_jN*JU,rpX?
\%S__`I^:5uc"abK)"S4mks'9]A<7F>;Cf=J-sad$fIM:P_Y:=a)[-s./2c1GsKdYt`=&kOu$
*66mK=Elftg;76gmdAg$\Z^+p4(gG)4I8ko2s'C""0SlHZko^$>RZ\&TYiFLHT*FI(/3VJF*
b"H>uhpe;*I^P7N,hN%fDQU/DY+Lc$Agt2ZV*i+-GAA;;h0V.8IN+2<0.J;22\/KaZPSFkH2
M%*J<o53HR)[j43!;]AWG@40kiQg@fe_^$mY/rb->u%H\+!=g2I?C.[sp\`EC@W0nnsDtt8AG
p&mKJ73c5VFt8ql0O*_Q4lrcF$*;Rm4a54U)iU#T7hpP%`oV[IZSMt*O`dHc`]AWXq19hhelk
\G_Mq240u)scQ^-\M5O\"gK?58c.q>X=0^PF))H.hOj%R/2rK(Zp=d\oSQteQD-.i=R%"j8s
,bOPlh=7$SqktO[FC%EXd/ltJl=QEVYV0J6s/SWZBR&!%RgKMto5[92XA)al"-#Y7beV[AAG
B9)G1Qb#&YQ*7(9<X@/Wp*_p9D@:%NqS$>i\qX+1cg4n48mF^E:A6cBg=(Gg1Ee+iDtU0eF2
u^>=:.H]A.G-[`.a(UNmm:NN>S!\(08o9n,7Jg52ir(#UDt'eJ,Eq)!F<G68K=FBC#P*-s]A0:
jZ5[L]A"c%?G`13h_*nR_eC,EF+^I0$f8S(fcDEX(&FJ>oe>TI;nK[4'!R3Qf8GTd,PmnZn_;
0`$-&H?VV^7'+?V7bpR,GRhD$4.Z;b%pV71$Y?;F,:Q:ueI]AI7lcD*k=nFrHe`QP(-pEMa2$
jJnarGjt$a$ZggOSp\5!lRD+3dh!nrI!NE:5>c@/WgV4!eQZ9ddBPQD$/WVg$8DEL@'0R9c5
G>qc*t8rTqB+GRKEn!qHTN,D&elU,tMgb]Ap)0/`?#0")pn*moNQq+mE1p6\8:rNfRp3nX.dE
D,=dg9h63TR8R$sXmE)qB"E1J8Lm+b-($Bt\Ypk4m!p9S<^TD&qK)Q7cjIUtZ.?bGiMU-s7%
D"5'oeJEoU]A`6O]AF,6ZC#9\Rqa/#$o2q;!8trL$BC=p&<6#"2'nf*_VIJ!V(j4E+7)rnGOLg
S>>oZ+dZuH<$?pFbK`BKaTla]AnN0O:HSoj*3;A]AYZLg7,$WnW0i4fHn2?e1H58)K0\r=1=7"
bd3j6qV<YDR1HJ48JH1_Um_k";97R$TK>5hKJ!qL,t>M41:@S2!s-fm\mDm9rAkc9;18n?&`
IsmA?HQNP]A^,MZI+(R2q_g%iS6>i"OiPp=imEO,2P-#]AVT0+<)YfT#eJ6(Bc9=V!PqA!$r5_
_3kt.G*@G$4eEC$3^1;dkQl.Z'/&QLJGN9rtmK**F^Y>Y-QJ<dQ-NQ#1!SmS_m0XfBJ!kV#:
#XdT\[HVheb;Lt$qamNb&W\@ZO5b`%1YtiP7D[ES[fod1pR4LCkNXPIO>pgH)oi'&LX1EjuM
nG%t3FT&.fQ7TdB+%\WRm=@<`:H2`t&]AQf=0IaqetHOs'Zl'JO'-E,Mj!(n?uPl&59NV]A<@*
8`jAY%OHt*V7qO"/\q`06"6!4Ctn0r"Pl-I_,=1]Aq"L.]A'm:(?#@]AdSAbNc[eLHAhRL3'268
.+9b#o1=1pbjk&"?oY#>3[2Mhsu,k%.T;@fL_L5KOdGrm6@o_$=b?76;s&k+>/T-@0k2[g!j
6p9$t>JO+>oR%\jtg]AR4[gp_)KIh4D<).^Y;.96#]AqlcqA62?UK'sLF,U[Gf!^0mM_GBHNUe
_!_h'3gV\U\td0&G'@IE[U\563rMN95URc%=+s94T\7chmA.W94N!T-dscup`?c/?u/4rr00
j=p]AWpTa$`(35G,KYceQiZmcQD#o9oLObl)o]Ah+n?(rO_!PSbf/(n:4&I7J:1d0Q`%I+`3_+
8)Z%.:XpW'VCV(Hm*?,I]AJG!2%s>Q?SBT*[`VUTiBgVdS49:p&U#1OYQoW$.%4A:i\'1a4(2
99HfU6;d8!H3b\egrM:('aOP2RreYgrt_\C9^GXiI/4o]AkR=Q;a-T]A"i=nkh%QGLj$bK$jX`
?F!dCTgE60L.-;l9Vpd%)9,B-ekbt?>RupN]A]AKT[l.@8h,>n!E)<VP<c6uI.+F><JE/?[&,K
">$7_Ctk;ch><9m>pbaa@0oCAri5</UX4HOR>26pdBt3n.27*XGosuqk0I9dN')X552dd,FI
n,nR/K)(18I;5Jn3DHfF-M6]AQl=7Qs/!U.)JjaiPHFTE7tRB0*-aPF4V7,EE->ecqSKc#7^E
#$?H?ZICHE]AHr03$XhcTa:hs1GnQ9WeSV\^h'$RC9X%9";qEf6rU2JJr=Odq=rpf#qX/#uGH
9M;&)oW2'F3Md54X@lDTW;P[B9:Xb;p[@%I89(V^"t?akpYujUNDbdAX#`%ibK6?Q[HcqJZR
7lnc05&!]A$P8WSrV0]A#(.[2h2I8hXlrf5ZN'L!em-!d19MjjT<fbY+>##$B>1$Qm36U=_8lX
X%cB\^tmD_Os+Mj4:[6[uXWO#9WjQ1BS(#?Z=S#:Q8+-ld(9'?>.QiW^LaX&JZ*C[dRHj!&n
53&scKsfJ;bk\&q7?E6E@R]A)uUU+&B.^o'+fWG'6n$;7pC]A]A+si6h_4cBG6m6rC/Cp!I:#TC
^;#(F5Z0?aPaK78HJneA_X/)>iU8=d(\_KV"@RA:G8An/NVYrAg:,]A^cpn1dMW1BO$k.G'oa
P]AVik58!`.[,_*;%#/THagaUoAabW3_A<8in6?2;s4(TMk?`21u_Ed/d@Ob4i*LQb(gT_W.M
pBt=97"[?[H7QXC+m4<?PJhP5@f$p\`:7gB:@>EXlDF;R\Q[NY'.I')Rf>9/@'fg/i]ACYS[7
TNTEZdFYn>PQ?tN)=QSKr:fWM0&cfFF*c1Y0<bCs(]A?\Y;`h8m\i^)H5ac;/::M,'`XAY>:f
Tug/c$15`%So3%!GQJ*>GV.FP<>-f`1":AtCHdV9S9N'F9Vg9GuN_o3N]AST7n?>*,DdI*@Ir
cASdBpW(`t:6^^\-c9Go=f;#n`_Z7`'4@P9hc%uaqCd49Di=-cjYc"HWbaR"a1uU\_AUqCC%
Oa4D]A'b!$!X9slNhC\@g*5H)Q>l5`1kk7bDAR5Trh+*8Ggk#BD1M=c"B&io$N><rAj6O?.7\
b#ZMC")Ms\aq(c$]AlShb@HWZOC@M]A!;Zstu+[8(,B5pI3:3S_OPWRTVU:n9kBepAhWe#H>Cg
LdU2<f9rIJj2D>fA[4nI%""YPA<dt74<_gKo<V:?11PSAagS%rXVhG#L'I[NgmKB0DF-Tqcs
jla/Mg7*@"R<0A&M]A5Bt]Ai-ZIfl.odq3`Vd:^0D\Mj(\tM`X=(>RZ)\6b2m*O.?oD-4MV!kR
p+s>WJ"PJ4$[/qVdM&8.A@6OC5D_^LflZ:$Zs(HL`2n@>KhlGeRoRtT=5Sj`O'R,9TboFNXq
eg!VRN0-]AcH_.h?`2!9^IK%b#?Ftesr8[/cRaKa]Ap`rfgrl9NPpR,)MZ`7>IuDhR`nDa!L=0
LaCA6\.W,m3bhA7"HF=hKL3H2H,0[?[3!.[!GaV7e>FWX6[sQcY>54cYGXm7!G>qa')sn;_&
n;:9f[^PXZQAPV_Lm15qNWQ-kH*EuTg%lc/,O&9-S7JsW<ESO+KP4b(\SuXX>`(_0c%%`n>2
=Jj1!T=;I!?j_A(VDarh('Rbe@qZL1!R]AkXKndPCd5<'cNbO^Z+:5>(ZB\bTI']A:']ANm5f[p
O7*V[![bNsGh8QQDncMWU_Vq[f*>U;:49F]A\2CaU(&E*5pU^%(ct<$r6^+WZQX^fgFS5s;1l
ee1p!XFY.,Cm&:fgpG7P$mE5$';P`aDbNXC,Ai)+%6OcL6hQZnGT6d,rfQcLACW>">]AVIZuq
+BZn;u%f%r*"7.EeL0m/0\ldQ(fc`G_.Ue*+B5rT#Fpf0iQ.EA;K(1]A<pMNI9@WutG7l)G<5
V+Pje)I-Z:d`.L_;\hk?dk'32D8YOfukc=Oe65*Z*;YD4&BY/S@dID,Lq_Gj[7XJV3Y#d#.1
he_SX&ncFd^;0e0b;q^T,(Y+5!NmYRjMF>!Kc7Ikhp!bVur]AsFnnrF7GGkI0Q0?h@J,]AS@oF
D^fV45#!2dVY^U[4EF1,/apqSrnuJSbGTB^5fD@rc?qE1<X:V:e+Qm1lH\RKdaoI4SlROY7S
m>M/Bm\AW[bm%jcMAM5_lNH;(Ki#<_a']AniU5q3>'j9qKIJ@Yb!7NF-$epnVlcudF6dG65Ko
F4NTeb%aZsQael<W8B/0j)Sn9-1c7JsK/h?&\YZG0EUZkt[B7Po*K=F'q4&n=[*Y4JLk;<bF
a/FM0c"auGoiEa6e5]Aq*rT@t&@jp[[E[UOJUqjq3Q9RBMVTl.FqK'"JOEuWI-grB]AYU8ZV":
,V!?q3UT9#1g_>\BOJOEuOI!(_#&P$G4>KBX.'l)WB5.lE#Trq\H4T6Ua1D_e.`M@l3ml'4h
45NUf53QNMWUss$?H0&-.&R#3*TQ_P0O]AX2;'PbHp6R>jq4M^CkFmAYCF,[ET9:D7>76&A9t
JEm<78O]AfRnLY#AEM$Vh?JqQ8<0Zo]AIqK-p_FXO?imj+;Q&J/g'[qq-DpPr]A!EKkJ,:I4hlP
nOXg%0?I-'_YsMFk@ln33S!PoWG:gXROsmhmZSTLYn4X>js2q#@h=PRM]A%SD2Uf<.ID,jZhq
1Qs$FGR&(6B[e6G@quA]Aa\^R4`(DL[dQ,T-c!\>/IcQFNl\?,6;$J;;5,2^imrL_6^BNdb8R
k,Cr2R)%#ebLrZ*J@[4Rudm?!1C2#]AL(en>#_CC!Vg%b-'-V!sguIPdm`S25([**,.*ecQRR
!Uh&FD<Y04'lc=AS67r$6+)_!%Ca`rLp$?soVN!U<j6FA8>bgm<%IMC2sF#3iD"&;K:!%X:@
,J.N1a?ZCr!*+=j&&fd0`:TS4TS5nF5:s(]AF`;;`QXSHfiGF:C2/mZ3/W<#(:Gp&T+@^V=-[
G^A9`Xnmib7QH"1fEf5Z[Z/AT$#cjcDYNd_SS@:6Qg&78i5?GU^5l>O~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</InnerWidget>
<BoundsAttr x="430" y="0" width="430" height="540"/>
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
<border style="1" color="-723724" borderRadius="0" type="0" borderStyle="0"/>
<WidgetTitle>
<O>
<![CDATA[国外疫情数据]]></O>
<FRFont name="微软雅黑" style="1" size="88" foreground="-1"/>
<Position pos="0"/>
<Background name="ColorBackground" color="-14803426"/>
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
<![CDATA[1333500,2438400,2438400,723900,723900,723900,723900,723900,723900,723900,723900]]></RowHeight>
<ColumnWidth defaultValue="2743200">
<![CDATA[3619500,3352800,3200400,3505200,2743200,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[国家]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[确诊]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[死亡]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[排名]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="国家"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="确诊"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="死亡"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="排序"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="0" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="国家"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="确诊"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="死亡"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="2" s="1">
<O t="DSColumn">
<Attributes dsName="疫情环形图文本数据集" columnName="排序"/>
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
<FRFont name="微软雅黑" style="1" size="80" foreground="-1"/>
<Background name="ColorBackground" color="-14803426"/>
<Border/>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<FRFont name="微软雅黑" style="0" size="72" foreground="-1"/>
<Background name="ColorBackground" color="-14803426"/>
<Border/>
</Style>
</StyleList>
<heightRestrict heightrestrict="false"/>
<heightPercent heightpercent="0.75"/>
<IM>
<![CDATA[m<`P4;eOgmeQ3VWbr`<Z@[50EUXk`"`>qLH>3e.IZ#s$-,qd`H#a%OWK]AIrfZj+cKSV!RIX[
TTH;-Cc%*0>p)Z31>f(m7,c":.R)+UcE6H+orZ^_"5`o="7-o6U2niAo6_grjhIf6n-9CtlO
8er4l1-;BpcC\+/^r,gA_aloNlYBBeURf.`+Irk>*Ue)LORm6i/0C`,nOtG(=kNRPEci7Enq
iYIXZE&T"Nto"]Al;FM7FFE<7jSa7%BURlBes<BTNKZ=$FjfIKPHoTC'oss8`ANo4amk>c@eK
]A!+1/Ed=G88"#R@YEEZ[K/nu2/Yl.WaBXY6l)=(nod:;?u5\>;545r+.o(,QF^'4k+u0]Af_;
+"t@p$iE."IAR75VCka2.Veg[?="dG<qj@Xa]AU82%![?ebCuTh/r/$%s-@Wd%4C1Wk2%]A4p*
RASLHuP(^2%JC&T?,^^TjH5AVTlLa'_[N8QtU3I9sk"!9I\>e1&EH0DRaqR;:Sr/hgh&P"W.
c$FeI)''6"u^VMH9GA8B&ZhIgE=nEsu5KD7!bA\tTRnQ\IPdlKcS(h24D3Y:24A:q*!l)R5,
NU+`E!%_Y'A$TI&[,j'?0V354mP344Rb>eP>D]AQH&]Agcde=1=(FZ.9M2*9I[*mcm>l/\LU\(
>erA&R]AY37]A+3.-"fBm^[HIS!7O;,`KQ:@(!W';Q$G<eOVB,s@rblSu#2J-Dg)Qs7-U.QNnr
E/F:m.uu+UF0>J+n^UH/HKeg_;sqQTBPc,)g_Z!-d$=[7Td<n)GJ&'N0W1A>k"ZBZm>eNFXF
^$AT`<iBL0TEP53a5YR&r08E2*l7DmtJUa[;,O>H4[Y4sI"JaN)WT1M924(T-Y=8WCdt+PD8
!'0V?>C7JaQ_t;YhSLf[DV3"&);X^JB0:FXnZq=Vo1Sqk>EjX-210F28O>H8nBMm5eBA,Cu:
5e^!*0*6UQMI*CX"j"S8Ghpd^iNi9i+@BY,?,q$[%tFK9$BJ[LQ?PZo01pTjQdS2l$p8)!"k
'eLJ\f!m_!#RnB^f]Afg$(Am-simF<O>e7DG1d<gVq)-OiX[jC;M&2(\kngjYkg1T`@BbC]AI,
J%RnkXb4Lr4d=;00(`X7KcS+F]AXm1X%roq4$"qZi4CbEU^?r`9"&"q.A'oK>L/s\9cAU=42K
]A4N^Psp[rpXjh#WLP'h02W2DkYopr=mCLje/5#T]A3$F=]AR5kW8C\Q>]A5^mUM]A=UYV@1^0!*`
*gnsOie!Sck_-5JJiipUW#UX[]APP(l#&$OpDH&SSJMRB?9\J]AWn%\"CZ]AT(-K:c[\Kn^pb`F
2C&</rl:Sk@=2H;KfMt%3H?7O,?0b6ZnGbcU,//,>m8_6q$IOCF70p8u0fBKiY$"onMtqD)?
=t8WPBNC.qtXY3K<pr.ibo4.0[#rL5k^d<1WKMagf_`!b4t:)($%GU)dJ[e3;_=:UG%_i*9r
ene7dD=;O246D:3\MOl>'hTP5@kCPYV0^j5'DKaQ52H%&0"7$^0%*!k2Efn#Lm!O0[F1(rH_
56cmJB=*E/RD"eHEZ(NW)$"5>bAI:dO.i2S*^j4"sdCV/X(AUH;6&;MQe)6e1RgZ^eY5(gg=
P%L3(TNCB-rHQjCXLHm(+.P:D;;WhqN/E+E/(Vs$1G`""Q-3;U($VUlp7\P<pVo[QiWj8o0B
K4)U&A1b41;KsTjlDr#77U%#da]A+!1qNS16kVg3l-&WW16^h5,?'9Y+q.Q&D^CnUI?_TKEEm
`6Dg!&ITJ?^ROu.%&3f,a><'`cS^nHF,mSaKZ>08O20+DXP,U$f7l88KrH"PB1oN)B"lJub:
gGX2PKP.oM-uR%lKSnO=(6L5(,.dna1XWNU_r;`;JktDj@]A;iNc5'3_W^S?a`)M@9Nr[YK_j
WD)Tt+e2>c5@c;LBoX1&);./U>ies5l[bLhMST+WDs.#LZ6OJ)+D/[Csm!+7PKX,NIf8L=iR
K4-E$Vd3m<eY$_J\jEX0%1t#Eb?=3b4BMN[N.WpGLl"sk0E%%eoWGH;:Km8ZA\XNKQolq.DD
bUd0n5U#NaYi_.^!^-UahddV)cQ$QBP#<W3+WdRSnUEPOW,KD$9\i`g&`NUBdOVF]Au#gI?,0
X5*!A/M:MY<j98gq9m<5Eo<fbWFL>f33puXeb\:X+4976&XFhY>/X-TB;(o0Hd3Y1ko=8F*O
cLTnb(8:_,KrF%?m?0^$%)Wm7[`MtE:N^6XrJ;L0PZ^ai//N?$A%@nce:^f,q"mhpcP[I(?W
tB,B+"rBKkB#PXe*.0PKJBri\XJdnE:%A?^)&U;_&0V._0BLWu12u(kQ4ciNL0+H%pI.6%eR
)lp+GakYA%h[H8t$76-2Zf?V"`W0@DU_o(SJ9Lc0[69bN9CN?\M#s1n=e?OrH*AJKC7^,D;[
*qO8I`LFfmm5]AM6:qT/d_EV9og.2:7:3Y^Hha#FUA^<\_U"Op_W"To67q0`-ITNT(A*!>_e^
%=cfT*!D*(H5WbF((9VNPtB/?Mm!UI@!Z(/^^qMt:95oZka7S2XHr$:jt?dL2"/X?Mto>**a
<5rCC;=at/5?CId&#K@/(_ZP*9'B%jOhq[j/N9r%'!P_&0EQhp<^'4P493/55XcsKW]AAEB`8
`t-`R<aRZcT2=IK\7us'a\-QXZZ-('faQ!tjj`7!4A4@,AE\RVV$=^D$UI,VgK1en@<b:i:n
F5cHLaAKhn\j`gjrPEa<%jmho+6_GJIF=?iAMK(V>q034mlVr^,#1<19QK)&JqH,'Z\PM6SP
'm.nO#_laPSNtn/D2CdbhmWE')SJt`%9*ql7^$em9%Z9)FE&[9p^76;-Op15-V:qa1EW"$%p
6Pf4DD/6TQea#3/hPPq71"#]ArnW*\Oo22>KtD=H/6G@\6k:U^i'+,J9U86dl%;Mh'Mlgj[bB
@I#P8Q,4,"q,a\_5m@A$2Ll"TE;;iJJ<8uf(4uC]A4t:Yc=@"R,3nm**3GRrWIXt+L?mren=+
PO$IC[]A,*TU9HEla\o*ULBJ+tM2Y,5X41Ii4Yg0VV_`_TJ6CB=qpN4=(^/SmWA>7m1K7rn-2
@N^QkE0I[Dk#GRs=dL(kgDT!&uia"n3"CBQhKCPqZi\]ANcV*g]A,[0o2U5m61t6=5Q4G<)=#4
,F:Uprt/6dkY#!-PEl37DYkeLut!:aqdoOMp7-M:1WL&aC&e'2&&[cHF;UI.H+RNXSFLJ)CB
1O]Ajm*U9^!7O2,FWiV3*<bE.Qr<U7L'HPc]AQ5ff#-Xb,dTCQ6H5e]AtaLHY-]AVo>`9h"l#.c9
WPFnH]A@`,Wg/Rpc5)2OB-TFTXpf?3V[]A.:?R1=k>YE/k0f6^u5Cr`F5_ST>M2qRNo)^aW3%,
_Hqn/P0+*gmYUMres8&U>VgOD-dLi!i#<dlntD>)"Tp8crp`DUc+GYfFe)Oj(&_p2aX\.Z7c
<A:RSCO>Y.tB;G$h@=f(pniN80cD^Go88Su@1mmCXQ7H'.huI":5bECu-4U';nJ8*3gY,olT
p_"hp)99bJU;2R.ONR3Xc\:bP,,$[2J3_m-lB2?Yl`t8o?D.^$%I:eq,XN<(+V;_`%K>aP5t
km6,+T_Q#`69;n9pXDqkLl6\<IH88gJ%61dtd%UsEqJaEAu9$Rhc2R;3u(Y)NK0D^&<+;XiB
BTJX)"J19>)H?DAgA5TeX`=IuKg#)*p'"Z=jpK'26LinEjBl&%[SG\D]AQ%)<WeE9oVX8a-JG
'p7GEJNo5H'n=/XGr2`XuZ[2JiDNH1^1`,$PH.^YrumO/uAp'lY#:oBF92KZeuS5(+YH(l&9
(oAiX4c:(;t2F&hqrQOs_6#`<ALVIdqOiD_C8eb(8JGP8bW@Q/Ibm=nr4(QpRTaaocnco>JC
@O;L`e&e&RNDDW_&.si^MrDpj@uM*HfH'NV0<[S_:[>28l]AMP1Mfu$dN6EfD#Qqs=]A94jNr_
7jVqaL"daQ-^CH"kkHS.i.hu]A0bjnb?qdV6.UE$KcBQI[]AbXd/JJJa@gK:P^3N-F7*).<>Be
(4FuE&_at<$ftAN2N-#T`'A,rMg[q"T.rUFYi!QlR6kEnq-E/NoY'k0b#ORF>^9(@pZl2N=R
GE@:WW)Z@u0$FpS1;u!:%HN?E[0W4C2ZhHG7R>=20hUE5F3nS%bs/PjT/KY5c]Aep,f>mHl]AM
jf;(Zbf83$>CsVKt:N)^X6;>qf(oh%6LWQO0PUYaAg)[gH:JMq\L;)Su@r$H.\&b7oWTU&B&
K7Ch\k>5o;nC.1pf,\iBaO#bDfY!]AnpDR+m:gM23Wt2[OWQRYrh"=_F`9(a(/[C'Z`@n+o@S
Y;2SU&2\Med;O)OX6!?&=rF9n?-5kD:eRgl&G6j(,U*hn1:@fo-ca<5`)k6KI'_NsX[89PPu
#Lkt_UWsikOrsN+Mn>Wfg5;rr\77?E#]A<oI:[=83LZ=lUSY!,>?8%eo"mo2>J2BS#92!!Xnk
aN;`2NLtA'.K3;kB=.d49@\/0!\p2_sZ%Yt0kGOc6<(>T%%fbq>(NB@*p!nfu$E>$GZ9Vhe`
4B2Cp.hX]AZ)?#eCk(HRXRl@)6:L2BF2g.]AF6Z?,P-!dT1(N.NBj5bH?I&AQ&@,1#*SOR!E5+
PTPA:Kcq%6pjUM]Ae?fan22AR[u'^%3UtEGoH-Kt)fl<UIF7sk@4RAVVrR!qiWc<b)st;/T/t
QMLj_<rO<kT<]AnEr8aN<g/HfQC%\qSDU-U)t8_?Wq,[M;cjV39:c?BVDh+\Sc#/L^>9(2:<U
qbgX+,G-[2Y8g793dDNX(u!e+FSht^mBmVG-ZGdih"TOo*W*I3fYq4"F>Dhsq^g9=UACtaId
%<*Xa=WGJp:OGa#kVCr(\g4SkKn_-[<?`6hYi-L=ac&:/9:9h&Em;huXg+&hOL>3'uBX:k/6
?s8*;&9/HL)\3VMq@7c>jfc,U*9eosfN\YZTHro,_#:qdCJfA,5h2OVM)Eq?-2g;NVb9G^DV
kf;4X(.(S#tQYSef?]A\a5<\?bD1uTb&f*"@1D&]A0UQsdADs4l.Ug5]AP2*T1Rp&l7k5*qs0BO
4V=<HrgYGKNsKpgU8IUoM!hPtIGDrZ6J]A#R]Ae3EVhjgk-to]A_8ET7c*Qh/$#h;_[$2GH)sIn
I-JuP9+70K/4+6\cdYb.AFN%?#_>tn0o5,*g_C(*&e%l@2,XCF9^PJZ9`3^?,&+5X4h0seCI
.1@,9rRk-[Ui\c?&<SCnVLV5&9%eYPDpUill3;DrCf5am?:#.8m>uCF'l;1cq[S-+#^+eM_c
ValJ?Q8)/ImCZ>7rb)#Lk!Eqt-m=mtt"/tSi+23eAg'_?OBB?Gn=aDFPmu$[t*5?Jb$<K#%D
Q;HcA?a`2BF>cXC0'7V</1VuM4I-'MCL@DAp%^drQ&LR6qM&(ldN'<<@hOp+%70&IcK0i4@8
/tF^[R;=N.oBeKDMsY4\tpBpQ\^ID-n;HK@N<Jhu,'NIfeU9ViGM^&Rm!(t#;_m;BTlV'&q+
M&muQ*WPLhWq:\M@89pC,@Qph=FOhMXCVTQofIuoe4;*.7c[t5DcR<@r[@bH!790l2!FO+V7
;JgS+=CGr]As/+KPSntWKFJNCTXe\ircNY_%g57KD>7u:De)#Rb+VDD5Wm=f*kOEIAKo4'LS2
&:Kc8\JQP,im;t%ZjhU$/VTBo7;c[Q"onB9s`LjLbf6ZtUG8q;q%H"Y,9n\97X$P\dGk`n,<
g'Y<7;XZ,4ZZgr(?Xj8Xn%kC"i),V4/#gHi"^ReF_&bH1jliu6X19OX,$4O>N-rsd2H>0'Ea
6O6<d[pqpC#fP9^:O8nmW`S4D2lM)YWXLfWns*Y-)8h/oQJ$/'NqlYs5Ob:'u)B_9i0"!:fY
D+o[#O,AT&$egff3(iPcZUq@*9=uTC>qT44@%bWZcSD.DnS]A_=bg]Am';._`^5OFfJ#0p;Ih_
&I3/m<q0h\Js,hFH,EX>aJT7OpnTYJSTH[f]AZiMXhs#`Ok[tT[osAHIa2mQW_S67FZp+>?l'
%jee<^(PEqh.P$Fc;&eJFWl!bB4*=Qthp:/DF&8$P^["O)l@k;Qj90G56H),mimh)T"L<BLH
uo?G:/,N\H?PW%@danmoUr:LAXcnJ<]Ag>^bR!7LKL-HP8ih^j<uVJ?[AaT(X`gbI,?sQ&qH]A
s164a2=YaY/0%V<u6Hu1J<M9FAk%LV5!,>EBHPc3VRlR:#<7]Ab8T`cK,L1-X>IdI,fEbkt<f
D9T^7;tD>kH$r_8:1`OC-SV`bX2P`F7%7E[J"<?0YU'1W%S"g4]A![\8b"Jj"5?IH2T)o9A1u
Aq1a')?Yd:QHmG>d=JL"G`<*#>%VN14IYkrLCch?^J/KR71?S9>pJcm<"smBpucF/O'0no9_
<.>#Sn>3#b3'4KHTV@2G)-S+f55+O>_B0VNY8T:VSNJ5MkLWMGEA;^&4J8E?#Z#M2>O0o7jQ
d\<uG*/3%+bS$?-(qJKM]A"97)`.m)Ie6GG84#qdG1otdQd?Ns;.#0s%6DX@Md7>PH!r-T[r"
,hHDo>qbQN3cVOY&M7N`=W+mh!*TjC/l`6<p#jF((WhkJ98]AdCg[lW<Qr"a8X[=:#,56lGE[
ICU<pZH>qG^3;=)7PFmRHh<('ZNpno28k.J`N(!.mNYgXX4l'LR<pm(^a9j1DjH#fPFuh<`T
gotesA_dYj*8X3Fu]AgO-i*Fp,1&kCLrJ`D*M"Dp=@5;#djKP@'be>m=q6U_njBl<`/WL>Z.\
K5F2bZY!AD]A52WQ%kM33nCn>%$3/0U@)&'=1]A-J=0/S0cp$L#WtI5ha3L&h,*'^!mR1Xg.$$
pSGff(6KHNK8?r94Oh`[`O1?LcNQZ+?LPc_GcSbi6q3Va:L@k&Sk9apu^=!"A<+PQ=-L7`W:
2A6]ApAg<=I3qe)<W)+N]AMn/]AGpVn9gOUF\a1+F:T8QR)glD<d_JMH^Z0[b(LePg:":Y-Eu(.
/<p>R1OpZ1;q/AC_SW,.^/Rlq&#$%(36Bdfr-a&0p\I?MJh^KLLt^6''3`?r_IMQ4O/!"Ia;
"E?HY/Z>mbCVO+lm'H#.?/N9CKZV.2dQ9OB/:.Sgq)AJk=Cg,_j41=jU(\2*n!Scs)[*M8W:
fk_lhUJf.JoJHj?)oCss.4pV:+aXf\k-.U+G9c/a8er2pkA!IEiD#:jq+sCLA4g>Mp]A98KGD
!m.+,ZFjn%1F3)J5+9&<n/*>A>m3I40M6l"8u(IlJ+3ea(m<dgp^1)iE.)@3efnej]A^jCGA5
1N$_:a^p!^CI;Pc,InU/Fp,eb6nW[8k-\ZeZ-0_gbJ4gfl[r+XSI3#bq<'b:kG9H[8'oT!Xn
#I/FsJ!38[""!WTeMV]AHd:h;bVFkM-P>'B8g(Gs(284B!a,C$6iUf@RVp=P7b>n3PR98WdQ\
h@$q.-?Gd27O<plsI.m9qbsF+@%t[Ts3#&SfRZ@JeBA+guM8LQ(6E$flN=_,T]A/[G"Ak:>,m
3,+<WI#]A/=ZnA-Td"q<5R:Agf:B[liq.#r0<.^nfM@otkVa.J!<ibtK?;+Nh8KkelNY*k'2V
%O,/]AJOhn:Q8R_`-d15P[47>l,G]AiDqH0CDT-,>k.,@5\J3S_b.$94.r=M1Sme*nTOO$=ilO
F[%%p[KSEJMG-L6Bf:L:3/.F+`A(;&auXV^al1>@am.F7kk11kQH2Duqtp@nA&D'(J1J9r<T
n!%h='^\nAq%i"X.7E3S.0NhAUG;lq*]A7gMq6\4/T/TYNX(W0/ke]A\J(3-Lm=^*BTn;Odg=8
LkVKN,T!gW!*5<FpQi/V(h)))Dnm(P^SkUdX]A!P0`j8RYkB;c0\_s(`AupI=ju]A^%+dG2rUL
M[Kbs-Z*9+&cgC:lO$Q)dQ&#_,lSNC3CruJk7GN<Gh8+R_1L.2c28]A:E_TbM]A<`8M]A/)CgIU
68JoAU\4E6(f6r`0K7:8ee^hC6#<cKo9Y@"5P^<mq#S<IBU70k-R8)%gD.<i2U;FI'r8=<XA
:@/'j9nj);WX`WP-cV[Re/%eXCE+/as!GB&Ib30@A+,&&mh%W:u4n_Z5b,9tG:>k6J2fh#`W
C#G]A]A*?l.8i!VSnAsriqfA<shbfif80E7ag,7)+!lsrmk4C9Yfrd&<@ih.oSIZ&Kfp9HFLdg
0OO`&<NFYjd'ac&QKU%B]A;8@4RIXS4W$c"WZ*p\Hj,1<q]Aap2K1k/A?BJND(In.Q'sQi$BFH
`25L=[[B\*Lb@UAQ[S;P3RSljAT]A#F@)%Xp.$KL,fd$4l'SWn:OE\Hc*j5fqboa&McP,U97[
DXpE':NER(g>EeDU+raVdp]AaB*.qd.!`q?V,F!NkIJ1uUHBil&(Do[2@[s773^kuGqmNs"Vp
ZBP_842p6q.YYfc_[#tV*.(E/_@-Q[=-,HK#r;3akemaF1[TY+[!nQ3$\:'u+PJVHZMPEmdB
.QMA815["BE>m9j8=$P)B$b-^NU/+Y)B7+R$Q:3EY;T)RLaAU@HI\@K"KS5;AgH'<GVIm9HS
&T!Y*"MdjLfk<jTb-r&%(VD;F/29:s3+bL7(<oq*@V,F=+[3cgOr!mAKqlgF)t@2jLMtfi>n
T7Ph/ln7YULQi]AJ#&T,/TMM1Y40^!I!ZCdqq9WF.!<ZF.Y[./Kcgpn+_<;/<'*GJ:I6\`ddY
!`=LDeKr,<n8f\:BoS>q0A0#ghftfWJ>u%eb=B(cG<dJGiAkj<o+q]Ao'.=jM0o$TYKo_=RXU
D,G&<qt]AnB[[\b7dFYGO^+$]AB=l4?.P5`i>h]AIp1N=eS33`e"TJ]A<cl&91E;BZ-`UD_XjMG>
mG_t9^p8d'>q#^-)mrOZ48n[2d??TVCCFmkf:F>I[UreFO[%JXF^L\KGH'k;Dm#YE>l-ooWS
g''"1+0FOa:(oq#!/H?;pSlA$$qY(Z86P-`0M4/jM5^J>74odnkUV)g(Ln/c)+0D,CTa?E3T
`laS<+kt_DU2OcP`KLiD^/*oJ2GJ/Ir1iD,Cg$p$UV(&p<>d'4B<<^OXkq$OWQeL]AnPS[9K!
S,pTm"">o>$TpK$g!q7j4DT9jhGMdr5_!4e1nlHa8baEa4.Kq:KaX?Rb2@*X:]AJ$:k@b>L[o
so]A6O.8*9UAZ=&RMarhO@+oU7`(blG=4AkG(%c!98@!GRP,:YYu>4mrct>(PPWPCkdthtKeQ
PKlJ7bVEP*@5fR>DcJn\,:%2QH"2A=f`k=rg6Dm0\%@Vo6O2(*puWi*XiY3(^7&H@PH_9d3L
dnn<T&c1b)UVCY4I_-3p)!Ul%#kcGqL\?988Lmo<KKB=D!'Ib0F&A;hYVC8d(RM..MnMqc'8
WJ9T.Q1orrG6eQ"KW'i*^,)t]A%+5R?^_#aEC2EAVEomXMX[WVT>>#!^s!b:egI'r;1W[SXfc
tgrH6^,DsLm,tE<i&nA+:4mhla;EoXYNDY[XN0T:8cA3[N5(U\#/"l=#fp<QB0:gY;Z!,pCZ
NE<N6k!O;.E<%j5]Ad0%Q^n;;T/On]A?!X5cepa\9Eq3UR6/a4O^!>RI2/<ptG4IMch@j+qqe=
<[uscLq*CX22]Ao^]A6p#rXt01Cm7rbS.9RH:q%2h]A/0_eG6``ho!I4&]A+8p_gS`ekSH@t$'Ib
:ITf(!J\en(7fb1=;9;VZJQIY9DDE=>LKF)#B\%"e^FA6G3gIc:M@-,h>a^"<`:.,ceXbTS-
G3@G9N%]AMg[E8AGVh+s`Xo8i?"?h=G?hcit@?j:#aT4%E3>rWJAdE?>&^5ndOVO!!cJ+P=TM
9MC/4K=@[Me6=@i(0[D?hX<PIpkngf*l]A9!;X9::&RSOoH>=V_n57^r6;YCHgNu&QOa%Qn7!
,Cjac?n/'jbeg,8V6hb;FLhu/oWl1kASq#CIITR7t'=Cbg'iC?f^@)2li73f7b__/3/%eg,q
L*.?[(Is(',ikH/iNL3mU3+>VI1Lfge]Ak&-4a8-`X81*g`q5To+`84t688V:/GK)7Kue-`2L
ECoHdt30OKoiD+!N,i54IPF\8r(_*'7e4p+lXHiX<-DSnQBL@m@:-GBu>>-PP4EnF0*ABS@G
/je)B`n=Nn%@fUiJ`ZJO"Fan2VZ&[+<JCkEd6Z?S[;_2TqJZp8Kl"JOu/s+G]A%<lpf<V^5m)
?\t[3rb*UQNKlM4#h-EFQ&L70=b\q9m<7gY9"Fm:H(C)krm>mM6@u\Y*).>2J$LE&R>)"V[V
-YV[ErM++"d%KteF:]AR_NM`"&[JD*>7.'hULVQQJ#]A*oDR'#UP4<GVABB"2a.CLe'JsrMrc/
aYtQRXS/<a+!?UY3r@_W@1D[E9>3L<Hc4WtpM"'U/N)2d=tYk^on#c;'&VqYb]A+:H&',+SK$
q1o]AnWp"09@6W)S`Alr;Kj,rW.;RdCi"Q46dKGg&q*R,GmZgWuL_DToh%Li@R-N-Gto>&G@M
&K@]AgJ'P'=>r$fk-J\3[S.m'#KW12F#fn*RGF=j-[anU9IdBi_OjaEKErG!CT*gmo5H=P`R%
_kk<\:QLgj3ANZN7*hArot9OZQ3#Q+1iM#IWSXF/f0DZi'YK^ci_5OkjD37%Lfq$^Tq#Va.$
HiG]A"1Okhnqgn.!ItN?6Xe%Njj&4L]ATJR8au&_A&EGW=JOAh-n>$d]A@Y[#sX\fWKVMMbHGin
8p"cDebI%`[2,65fDb%N$mT*UZ+5"h*N$#GcJiYuYTWSppfg71i3OK,_ZXple@dnePiZU`n1
X=7>miFB*T>+/#5n.t^YOH;`!Zbm0j@@h$XV,\"h/lb*ECh:H$.<6J)HZGL7qNU1Z.&hH6.T
,"59C9&j%:X."e.t,S$BgZHE*-Nop;#b)(F;M#WkJ[+@Lc]Al($doD`*A0.S]AiIdogTIJHaZc
Z_t./O!jLThY/=R5F28J!jH/im<kVeKnhp8OlL9^hlupEJP+u7S_O[NI"rVhD(>NL&]ANGL51
8qF,mk6&/Nc/$)/r0IdoXgUn%jTOLG^[F2j-Q?.f&?>He3FDi2!M;PK'0\04"X!@=</*`fG]A
(l=_emHi8Emsc"-0U,^T4fm*.)!gt;n4mMArGPVjGC&WhU_`[m(-.Dum34?_`n#GKB/-K1=9
f/_^FU*!7$$&9bJQ7k,Rg\^*1En">5ea+^[(8'esi5a&?SCSN^57-ps3\%cl3r\44N9MQq@6
PkgFEHWt%DWnF4Mj*etcjX"ELA&4)'h>#T/_dQpIV'htONX1nn7paTfIa!4,<h@lHFs/T@C]A
@;e/XjoV?m3_U(<00EWP<\PZX<>.?c-S_Gis:nYZP?u.UDn#Qg!<_3i[22OA(%/6BUGL#Y&p
+<-K>%eN4@R.P\V8VKW*%d*@f1mb]A65eMB7&<SuA#5hY"!k)9??=+pUsr1h/cY*tgZ6gL):N
_ZVY9LQ)mpNlM:.Vi,&k?kU>U)iq#EnOn?0[d:L?4Rb`pA5M.:>\a;"_,N6C&3=Q\l1n#-UY
bf>/bmm19s&/3ZR0YNMiopn1XNlu7SOs.Qp)[*Ebf_2N[rcdZ7)&!?<:fp/9)?ZU/jVa#OOe
2]A0S:7V7R;_*$7+Us/0f5gSi.5Ta2ufLseZb)_Cj!j376_YBA:<iVk^=j+sj:^g.UKEC,D82
-,K4BYT+>G[mI+4X#/8j_</f!tjRUCM7b@^OUVj*bsJ=J5-b.]AY;Fgr5ZZZF'RYj#Z!PZ'QO
HJqhm'$eh/3E;,7LF<iTYQ&YUH35:\OR1WBg;(_S#B^T7;.;hEsl))MB5q'R)8]Ab9Lmjs]ACQ
I*TK@p^HuU"[r3Hs+M[+cl*?($F`+ZQ+^lc"4/'_TF(&/_)pqT*V'1cDSQhm*o5+l1G!sR5<
k1bS3H3ui.+]AO^#l`mhe!uPpll;L`BtL]AOX3QdW13nsq+tT.EPcmB]AR'?1QNLV(778:;a6iD
O$U@,r,"3mNFOWB<J5e>P\t0o>"PfH[McJ13eNK&n+)d@6I<tCOdu;&bCYR(r-JAE9hRN6r]A
_-VHrXS~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
<MobileFormCollapsedStyle class="com.fr.form.ui.mobile.MobileFormCollapsedStyle">
<collapseButton showButton="true" color="-6710887" foldedHint="" unfoldedHint="" defaultState="0"/>
<collapsedWork value="false"/>
<lineAttr number="1"/>
</MobileFormCollapsedStyle>
</body>
</InnerWidget>
<BoundsAttr x="430" y="0" width="430" height="540"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<WidgetName name="chart1"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="chart1" frozen="false"/>
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
<WidgetName name="chart1"/>
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
<Background name="ColorBackground" color="-14803426"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.97"/>
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
<O>
<![CDATA[国外疫情数据(内置图表)]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="128" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
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
<Format class="com.fr.base.CoreDecimalFormat">
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
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
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
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
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
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
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
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="3" visible="true"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
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
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName="新特性"/>
<isCustomFillStyle isCustomFillStyle="false"/>
<ColorList>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="60.0" supportRotation="true"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="国家" valueName="确诊" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[疫情环形图文本数据集]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="c10a1a68-e167-45e7-985c-c20006fc8631"/>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
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
<Format class="com.fr.base.CoreDecimalFormat">
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
<BoundsAttr x="0" y="0" width="430" height="270"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<body class="com.fr.form.ui.ChartEditor">
<WidgetName name="chart1"/>
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
<Background name="ColorBackground" color="-14803426"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1118482"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="0.97"/>
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
<O>
<![CDATA[国外疫情排行TOP10(自带)]]></O>
<TextAttr>
<Attr alignText="0">
<FRFont name="微软雅黑" style="1" size="128" foreground="-1"/>
</Attr>
</TextAttr>
<TitleVisible value="true" position="0"/>
</Title>
<Attr4VanChart useHtml="false" floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0"/>
</Title4VanChart>
<Plot class="com.fr.plugin.chart.PiePlot4VanChart">
<VanChartPlotVersion version="20170715"/>
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
<Attr isNullValueBreak="true" autoRefreshPerSecond="6" seriesDragEnable="false" plotStyle="4" combinedSize="50.0"/>
<newHotTooltipStyle>
<AttrContents>
<Attr showLine="false" position="1" isWhiteBackground="true" isShowMutiSeries="false" seriesLabel="${VALUE}"/>
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#.##]]></Format>
<PercentFormat>
<Format class="com.fr.base.CoreDecimalFormat">
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
<Format class="com.fr.base.CoreDecimalFormat">
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
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
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
<Attr class="com.fr.chart.base.AttrBorder">
<AttrBorder>
<Attr lineStyle="1" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-1"/>
</AttrBorder>
</Attr>
<Attr class="com.fr.plugin.chart.base.AttrLabel">
<AttrLabel>
<labelAttr enable="true"/>
<labelDetail class="com.fr.plugin.chart.base.AttrLabelDetail">
<Attr showLine="true" autoAdjust="false" position="6" isCustom="false"/>
<TextAttr>
<Attr alignText="0">
<FRFont name="宋体" style="0" size="72"/>
</Attr>
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
<Attr enable="true"/>
<Format class="com.fr.base.CoreDecimalFormat">
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
<Attr enable="true"/>
</AttrTooltipSeriesFormat>
</series>
<changedPercent class="com.fr.plugin.chart.base.format.AttrTooltipChangedPercentFormat">
<AttrTooltipChangedPercentFormat>
<Attr enable="false"/>
<Format class="com.fr.base.CoreDecimalFormat">
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
</AttrLabel>
</Attr>
</AttrList>
</ConditionAttr>
</DefaultAttr>
</ConditionCollection>
<Legend4VanChart>
<Legend>
<GI>
<AttrBackground>
<Background name="NullBackground"/>
<Attr shadow="false"/>
</AttrBackground>
<AttrBorder>
<Attr lineStyle="0" isRoundBorder="false" roundRadius="0"/>
<newColor borderColor="-3355444"/>
</AttrBorder>
<AttrAlpha>
<Attr alpha="1.0"/>
</AttrAlpha>
</GI>
<Attr position="3" visible="true"/>
<FRFont name="微软雅黑" style="0" size="88" foreground="-10066330"/>
</Legend>
<Attr4VanChart floating="false" x="0.0" y="0.0" limitSize="false" maxHeight="15.0" isHighlight="true"/>
</Legend4VanChart>
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
<AFStyle colorStyle="1"/>
<FillStyleName fillStyleName="新特性"/>
<isCustomFillStyle isCustomFillStyle="false"/>
<ColorList>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
<OColor colvalue="-472193"/>
<OColor colvalue="-486008"/>
<OColor colvalue="-8595761"/>
<OColor colvalue="-7236949"/>
<OColor colvalue="-8873759"/>
<OColor colvalue="-1071514"/>
<OColor colvalue="-1188474"/>
<OColor colvalue="-6715442"/>
<OColor colvalue="-10243346"/>
<OColor colvalue="-8988015"/>
</ColorList>
</AttrFillStyle>
</newPlotFillStyle>
<VanChartPlotAttr isAxisRotation="false" categoryNum="1"/>
<PieAttr4VanChart roseType="normal" startAngle="0.0" endAngle="360.0" innerRadius="60.0" supportRotation="true"/>
<VanChartRadius radiusType="auto" radius="100"/>
</Plot>
<ChartDefinition>
<OneValueCDDefinition seriesName="国家" valueName="确诊" function="com.fr.data.util.function.SumFunction">
<Top topCate="-1" topValue="-1" isDiscardOtherCate="false" isDiscardOtherSeries="false" isDiscardNullCate="false" isDiscardNullSeries="false"/>
<TableData class="com.fr.data.impl.NameTableData">
<Name>
<![CDATA[疫情环形图文本数据集]]></Name>
</TableData>
<CategoryName value="无"/>
</OneValueCDDefinition>
</ChartDefinition>
</Chart>
<UUID uuid="1b958108-1230-4854-885e-89e04f24c24a"/>
<tools hidden="true" sort="true" export="true" fullScreen="true"/>
<VanChartZoom>
<zoomAttr zoomVisible="false" zoomGesture="true" zoomResize="true" zoomType="xy"/>
<from>
<![CDATA[]]></from>
<to>
<![CDATA[]]></to>
</VanChartZoom>
<refreshMoreLabel>
<attr moreLabel="false" autoTooltip="true"/>
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
<Format class="com.fr.base.CoreDecimalFormat">
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
<Format class="com.fr.base.CoreDecimalFormat">
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
<BoundsAttr x="0" y="270" width="430" height="270"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="chart0"/>
<Widget widgetName="report0"/>
<Widget widgetName="chart1"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="860" height="540"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="732b28e4-d8f6-47f8-a266-8a5a15cd397f"/>
</TemplateIdAttMark>
</Form>
