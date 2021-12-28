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
<InnerWidget class="com.fr.form.ui.container.WTitleLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function() {
    //每次重新查询加载的时候需要清掉上一次设置的定时器
    $("div[widgetname=REPORT0]A").find("#frozen-center").css('overflow-x', 'hidden');
    $("div[widgetname=REPORT0]A").find("#frozen-center").css('overflow-y', 'hidden');
    $("div[widgetname=REPORT0]A").find("#frozen-north").css('overflow-x', 'hidden');
    $("div[widgetname=REPORT0]A").find("#frozen-north").css('overflow-y', 'hidden');
    $("div[widgetname=REPORT0]A").find(".reportContent").css('overflow-y', 'hidden');
    $("div[widgetname=REPORT0]A").find(".reportContent").css('overflow-x', 'hidden');
    //隐藏报表块report0_c的滚动条（此报表块名为report0_c，根据具体情况修改）
    $("div[widgetname=REPORT0_C]A").find(".frozen-north")[0]A.style.overflow = "hidden";
    $("div[widgetname=REPORT0_C]A").find(".frozen-center")[0]A.style.overflow = "hidden";
},
100);

window.flag2 = true;
//鼠标悬停，滚动停止  
setTimeout(function() {
    $("div[widgetname=REPORT0_C]A").find(".frozen-center").mouseover(function() {
        window.flag2 = false;
    });

    //鼠标离开，继续滚动  
    $("div[widgetname=REPORT0_C]A").find(".frozen-center").mouseleave(function() {
        window.flag2 = true;
    });

    var old = -1;
    var interval = setInterval(function() {
        if (window.flag2) {
            currentpos2 = $("div[widgetname=REPORT0_C]A").find(".frozen-center")[0]A.scrollTop;
            if (currentpos2 == old) {
                $("div[widgetname=REPORT0_C]A").find(".frozen-center")[0]A.scrollTop = 0;
            } else {
                old = currentpos2;
                //以25ms的速度每次滚动1.5PX  
                $("div[widgetname=REPORT0_C]A").find(".frozen-center")[0]A.scrollTop = currentpos2 + 1.5;
            }
        }
    },
    25);
},
1000);]]></Content>
</JavaScript>
</Listener>
<WidgetName name="report0_c"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="report0_c" frozen="false"/>
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
<Refresh customClass="false" interval="0.0" state="0"/>
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
<![CDATA[m9>'B'3DcRCKjl5Z?NR2QBHAljfnm(.Te/GcdhHp'I@X2"U.J=Ysq._M=O#s9Xb@6M`(5q.g
hZF5:Rj/&[@&rA2I.^<#>a80SC8E6m*WZ+[7>1F+_iAM_i'>[e@8Xf"^/%bW59co6p*)Ic#@
("7)[_^Z%0'D9A(lY=/BOm<>I%RHcM<b--2ArNb`Bn_K,[Rkf[1)u93B^R%rt;fIZ%n:h]A?b
-uU2,`AB\O.U2IrVJhX-M@sm#M/jRie,Q@l1t;$Q*u1s*jnQ/+7:5ceekhSc>Ci(hlQ`]ApYQ
dqnAOs`I`h`3b!!n"QAPg5m4h,c'Um9U_6$WY`%#DQ/kHc7n(u5RP*LN%S``QgZdq2ZB!L2:
^Wo&3?/EMuaiX#6^spk1hDm'>0&Vg,N]AiR]AFAcrqYjHTsVcZE/Q[\"nD:_F-<RQ8.-[U(4UW
Kh4df(N'[pac=K7,#8AM]A.ViZ=8JX4tW@[^#`nVXi$OJt>F,Xp'OAgVuaa^!9D&iTAQAHW^Q
ToFZ^JWjtZLbP0D!lF+*tl6)-=8KpK-j%F";]A'c_eNN^@5[#I1bFrhH=WqEhh!%6-!\ts!@9
(pq"p96G`(`Kb:8taEVVitV6Sp%42@u4XomV`s2Gd3!MR'l:Ta?P$V1@3<&djWT6,2ZQCkC*
*P?7e5TL93=T=s(-MRDg\2p04K(doXQu0m8:.p/OPElV>c/CD8M0C$_eL$\DeU`7V0M*A\TP
p+E_S)*.8l-?WI$EX=S;F9R/?"2)Bm3rJ7,UKsi@6S9#W(5f[i)B&:8SQnPu"gO@@dZ6B.%]A
bmu^$^%%#1kIZS]AaZP?&oD/md36`?@7$^(MeDAeH'7Rq;b>EWlW!"[3M4c9j9i5i8OGtIh.N
ec-ha[9,j'i5iUPi"l%*e4,-lGHekL;)/K;D=[&B]AQU$%!0u(59Tj:m.A_ApMgDUW+7,)#5H
Eeo8e1u:@]A@dG179f2,[9csKe&p:406pB8;j)=gnQ/oR$Vs9'RuZdaItER@(pamt?6toHg%X
@c8$6K1>"#N?ClA/4>WR%/EiRQ_Lp^'dAu'MD7RXo/A2H'&6M!uJVuEMegsjYi\YNd%Z7?@F
LP9Runi%kpG9n$laB36!L1JPJTBgVU>iB>mEc`L3'JK#-@`r';,!LJ*PWi2a]AQ)DCE5RY79.
WC[gnb#i^sR2k@.8:L*Y%M3KjOLK3n2r7QZcpbo(LXe_5CAtb9J29ZbDu-']A9!qc'5'RqDC+
J4u-PCBTh]A20DK7jI2fArnuetdNra.gFk<=)UNX$;9N]Ac6khU(TE)jVj')gdlQ-t%Uri;511
BK[rSQf80);-Pqr(V*ZYX,emBREs@c\h/_%T*1.k:i@3oX:X?3]A'WEp6K\&_!8c7[RK/(ChN
On;^mqB+U\SF-ni!pNsPLrl-/:6dkGb^/a]Aa&!#.oi&9:snm?0:V7gWSY#'Nr*c&emDmJQmf
;*HeVIb9It.)i`sofR*p[36r,,Wg(/GscUIr:jSbo:m>U9q0G@7)pp/mOgY/<#t#.LDn!?YD
dI>X"J-]AY.>4:^Y%S<s*dbk>Kl5#XJ/8:kUqk:Njd\1GPYOS"Yor!=aB/;3>12M#uY2l\4Mk
c4D53^"Xr_rBH.60RD4ffnf>T3O?0S.=$s*h.i>obG/=6c;'lmOjJ.P@lj@eBgi^M6f;I2A,
rJfSRUFgEA$8Ms*eqPeV0\.38pLfASqi",TW:ECM:gG3X>hj/>WT&JAtd3L$hF`V@KhLCP7J
+YpE8%10HRGNU2Ul[Oo*$\LQYcq&S>k5#4Ld;]A'23ort-!8kdFt8a5*Io_i^6nMB6R60N8Ma
er"@A@X?L)*eSGJ&p*pn6kP&SB(D@*Hqhb>HYc@lV%gqUS6q;^[+Xp;j+6V(9AJU)>lf_fVi
i",]AZjJ'H++cYmu[i/1065ppE_Q/P+q;`[/PfWbj.AbqEo6=Vg2@<H?Y&eJBV/gh>JG.q\l*
LHkn6FJ+C2&-jp7I8CFm,o9$X1<9SO*;5?YB<m9'FaF(7\Xu"2NW^3:OG3D;iW[t5)6OQo"!
ja?#8DG)uF<@r!R;mE1dRL*:#(7(5[+3e$_)itIZJN;D;NpTYX[RD6m+b*>:\YU8(rM?;B]A>
M9p\3Qn=k4[R,4Y5[X-uAU\g.!1:=G_4%oDZ5)Mr0Vbmd.S9Y+Ue6E$2J*b^(h=bWT?GJuR]A
$Nd5hp*M37,@eI3]Ai(!B!Sj[\1Fr2.mTSt_)R,<DZq9Q/1lEiIO7>`iN/ojn\bphZQbtX"N.
]AKW@s*6uPg0m:En;rbLjVZ[eM&]A0H*!jC[AP6/Yp\q)YtuKW9,QX_]AX=G25*Q5)A9jDhlcJP
l.'c%UBM<33I-=mf*m1uWk#9kP;T<anCFj^[^QE;MOi&sJ!GOEYdgVk4BNd,)OXUl>$H9+%K
`u6O?Z'n'PW`bNY3)ea$C6D#G=SG'MX29Aa/(Ph,O!X/$2r(3T<"t'#<WPT`Gf^$Abq@bkSF
2J-3DHafm/gDC&N(eAOB(-`>JWr&*Bm8\+.b/?[G2/VsQPYlWIRRoQ<UHe+%0;%DI;W#ja/a
51&MaF>,81RZXYN<8(#,.b;R8g8UrZSIrJu%8C?n8e%a,.SI%lY>G8f4F#N9K3&F5F[;I%I$
C`!i)n,f6aOFchj+0p=I);,9oH63humY[3@8%EJp#[tYC7Z&/d?$8Vt=3;?@A_>YhR&Lne04
JD'oMW!cf2fqa_Zf$4FFBMqgq\XQR(fk.mon1j[[fk0QG';::Q)N\AJ"R>T+THq%pl:qa87O
rrN8N4A7i!pp(H3G77hG4Dip?j02gI-^q*WRBIqK93.b@qLt-.RoC5WRABU+uZfs_h)fjS:X
^\5F5W-7.G-G,>YJ"]A"2Vi^D'RFDn>R&G%RBPQ.b"q9a932&f`%&J4,Sa@=pJ)?IN&lJnq!J
(J/sRgj@RlU.?KH5[?PkJ[8E5Dn5XMdpO*@@7F$,c/p2eV&Ho2ai]A-&9)p<oLH5#oMDG<%Y:
"<Hq;b^I-/`"Eq_NB^\b]A-OEjqW#4"lFq6j<`*jhQnNBe58pf,6`PW5phb!9>1@9nK,c$_jF
fY,\=6YYmeTS;`BAd&PTdfLS+rJa+Zo!6iHo@ro0mo9:*!=$dCj3NcOa0GV\l]Ad\on#jB19#
_34E;ZJCl=CFc)S=droLa'76_4!F/mjd)e.po]ARP\CO^lY/3uOfrM]Ao_-#rOC1+P=M%iE@.Q
pQ!e,b]Akg"`03LZ!4DBP")NRU97H`<Op]AWY,-R-sf"CU"YA3<:2`cbUObFtq^LS&T_E%4!%A
AoNK>j<TL(?4N]A!bf0ad-49)g9Pkn*ZI?B*.c"isAVhNM0au0obm.c1aS#/I;87f??^#]A82m
34]Ai)[qAR&o`/Z($+Mp5i%o]ANAIK.FZc>"O@G==l0MJ7qeg<k@Nl=+W9#X"O/t70YlnT`B%I
t4P>/.(;RLHl"eZ!+[ISY;&R'4dr,Gm-UBO_84++K!!8;_^6a`M$l'Ka`/%;^F8!fnS9H4d8
>^01(<cnO(X&NKV1=aW@qj1Zij<%RZ*[d4#tX-t/FMYJ$Gl#62pH_R-+hj57.4gW$t,ZRgEp
]AHi(!1N9s`:4[Aqm79[bj;g1q;)TR7oUi<((0DV?dUoR.HQnAZKP;57,u.aZ)3bL?t!;"4sl
Q.&5h*>Ui5k25?8AXj0r8[I6$Im#dV#j+2VoI&%h-)bkgTSW\dh-N6:oP^<M(ke_IHi*7OBY
R#`KmfLsbW;+NZ>4Zl/(m(41RCn$ZC6D7UWgR#\<@1J@J#4"W]A53tGDc?I<LnQ1dda`j&*oi
7"9rDhK25n`p]AaA1$$YL27U\rM5``%un6<IrW=<Mf0Nb"7iq-A6l=8Hi9LE6VNl_;:`S"q8.
A4B[NoYg.LP:tp=2O5K%AjVkXh+pLQ'>J5ZA]A3tc$f`\R"/['"LIt"4"5n.V4F]AI&d3o,k1)
a4U3;@oXW't&e@8E;-usSU-o%6@_KPP+4B^Gab;4E%%&)2r(,-!87#,-Ul@J\H/O^Eh+?st3
!qn^AW>*6aBpddX^a9-$Lr%6e$88<EbW6Z"OubUuUjAh>8$Uk3GG/AhJmTahBuh%FC:$jq%B
ja<iP>+gO:D)04$'9K.%itH:pI'e*/]A_-#=n3&;^b*KQu6<dSE4FnKa+ip.aZC4Q9e+)^@A=
]A?!C0?m`jpu._*0j\:@I@j,)^^FI/`\]ABQ^*Z+2[sEjjFXcoM1T)2;jcHNL8d1\W8"`,Dk$:
:D&9l!sWt"g(4h0%:Zk9Mq'-V^YIL"_i]A$!riQYn=rA<RP$9m&_fd/l]AkJ:&fbD[._4^:9`G
7`Zd7+3/n-JGa9?IKh6NP+d\p*=hcNf3N?7>gG_&?(1_LJ&PZ[`o\.LC@lIN5a3[g7K>-T&`
3$,bU+]AS!jK9l3UnZJ<s=`KigBs0=,'0V8^Bof45P^FN58Ya&r"9IALPrHaZ'=a1]Ag(ofBJ!
5FA;35rAn5d,`\RHUm:h;+doD6ei1a1.;kZPk+lg(]Ao[OgOSQfZ(P+jILM-#&5=2lkn&q[L1
%c/T`k/05)9d.V"JOC;;gi)ta>;_ekL116/'VFCCWL9#t)H(+Aj;F\De`GFf@00OPIC7HRJ=
1j`73F)#P5&PN-lm9$0WF;'>+.&"o(`CK/h%))pU8$T3hTW*q_X>1?VhE;h3bWI*J[#_)3en
s;Q$ljpV20cTXE^sdU(NJo'3&#VIB6EB+9-ImG[.UdP&kP:F9he"nu.2fo8e[@/'D13<bQ2G
*Ab!<7b5RNUQ6,P(nt@m^"E?"K(2rd<+2rPf^lTZj2QqadL?5>>S;OLM('b+`?#3J-RTJlVL
UUPdN?L:'Xd:D+PtR?;W<+DhO$snKk%bG0:HFj(cTBYcbas9&j6_;@As4[buf7=oC,7&cakm
<@#15$qr"fei0q9"$aZ%?1uiTqSeiG_!MP"5\*rl0*YN!#N1]AA'_0ldUD&X=Z[J317WVZIT"
u7cnEth?Im\:)WrF^nNcO[g2R-*c%LcUA9$g/4?f60>HQon*iqr>\'B76(oHd9m]Af?]AGgZ9S
65;[)HI[id5ihQ\:^op0:YGE@=P7*6F"'''k+:/pIB,edOQCgB<1>/Hld/'6Y^9:du/?R`_`
%DGbUEHfO.JXd0pWmKp8*%*q:(\P7ncFo0R!J%r9DVfO>+0o<B\$4FU2esiCU'Z+qPmH_LNP
)XlWG!4M(c3W8KtSWN\ErT\r?$AS:NQ7-VLi9n7:DVDL"BStFSV0!WVu?^H7B;;B'2$g1%E,
6gSTZ0i5)6>-k7=i"gdfp%d.^R&5R]AI:Y9Lb#0ri6^Al&<@e!A%W3JVe_tE+=&?_I<j%-fDE
6X7N`Z9<Ij:gkXZW]A&[9V"8VO%#<F5u%dfOi3>,L__/JUn)H>kONYF<uD)aT->>?EOV^7/IZ
k^gkBYN_pm.:W=**E?@G;Af8jU^Wh'rP'(F::,Go]A'IWiQ?gPMVXBoT5\puB<'dhg0E6u*^)
]Ana5AKV7M@6^7BV`YShs_hYGXXG`cJi@db(CNht3,`r'n'6Qn&eBi3ML-rSE<LQNfJY.j'qj
[/a.<VIA"EN0W#<Vq(K0+HI4g_ceb>ms+=nB"oo(fJ-r2hcX?$64g`M7LYWMNBZ,l\M!X!hX
OBKdkNCI(_1DRb3QDraPV37DN8`utWL$ipEYDI'bFNP&&VnN@(>LZD5<aY@OH(?qlU3-BBcP
;lj*"WuI1#\)!/3B*#?gbNqEiUE#pf$kG'eWnPWchmj&bqZl.OuI$r'kQ.i`QA^sGm:o\Nn,
`NMb`L7=RSKe5bN4O\Q=IU,GK/tq)$3G.trFta^.^Y?DGBB-o1hlMZS3*):#1ekMe9a#X7l-
md%593Z\=RgCu8e2=G1cTIdo@ZrHEq!=ok)SrHB?`QGP.MpB,Ub6`21o;aOV9@[*`7bT/&RA
IGD'jejW+4Q"p&<)#XUnrf.>u0ZWIc523*fm=onF5V*4@.(n9\9)@U8"Be#$L&:V;`d^86hi
8Zk@n6+1P_:4T`e!'MBL#G^/5a]A-g\1$CBeZ0n'WIg-Ae;OXNjaqcmnYf36(l$mDTFcQR6np
2sc`U09fgnHGJ6BR:MABG01*,pMUIZ*)G[4Gp5okfo!"<T>4NoH6$^Hr.?Q%rWAu\6('fBMA
-nou42p6d!Kf:C*cNs&nUCpii(h):NakC-*EZk,I1.;2^7GK'5I\d\]Apg:.5@P&G5m>3JB3V
*:?[bTsfoZI*1kS6VppF^UE4Df,so/c9&/YUp'J]A',\O<-1CZ+qK$pBZ,Wr(\l(iA'.L$QOi
$*_T;^cXEGH8pA=Z_g9$e$cDaAf46]AWl;C\mqeN4(I2Si`NHe+*HTB;nsQY-[!a?]A(DB9L=]A
<.2QT^3D;gT4e"ob+ZKX$5<!mIgFj33Hhc&.nl-V8@262@?JCAN9a.7p.gQE'4>6]AJ8kO0K9
_gc$$NCM.nIrq2\smj<7"+",f7C&dBB7j0VeRGo&C>4/RJ<,Mr$$?!`Cj8XL3%Te,(=4?ZZB
/R4s!R'P._AT;Wq8'3"?W+BqaR+F9k`&["(Y]AIB&4BItaqBl52VW/&HH[_[&:(4qU.Sg968d
HcL&KSKUUjBB?5saEIrRW=98(]AO2iaYPJSJU0R>N_roUF-%!W0@[O)[J]AuAsdXqZ]A5-?.CEU
ASgmD$:p[s.a*LRCK>6f=fq@j8Q`_>3Z$33IPq/(3]A9/dpi4K]ABVi-''!WAIuD6Fka8g80V`
CBXIMcat=iW"E`us1R6VF?X?klesS#4e<J'<M$S"k+P'H#:.Ru(.Z&1GGLVpVU>*BnO8Qo1;
AP;,M2Pk$jm/>B`.Zr>q1,'E%e!UNcm@j9Ho?2J!:"Vss5*?HH=Gk*7it;Z=2S!H*s9<K2b+
bR;X"_<k.\JEj:h"Jn);C-9D.EIS$nm*m4+)Mo[pp$&34FJ\+aHL#l<MCFg8\UGi"2Q-[K)F
e1!217.ql.L'oUapsah$'8[p\ND1e9-;JuaGt(\;CRQ#j^PA^+J]Ae\Dhsgi"it;?;!,fQpV8
>fU=MroV=i'^U%_p2p1'mK76Th,ma]A'e1dcsjFI=YE@<5s;m`?G6SP]AZ0.KD7e2P`"GDY?GO
"Hj\1CRbe)e<Nu]AlH2\C<?&X/PEcK2SmV]ALRr9F_nM@rSdM'8F>GX'YY8CWFM2_-QDVM+0uY
9m,8VJ@Q\=T'?]Akr`BY/'NH/P<<)?o]AAnMI5Zu6&Hli0r="OtrAf6)7*.;.H_Jg";dR#&1%q
75;^25UrH?`6%mKt7~
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
<BoundsAttr x="0" y="0" width="403" height="216"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="report0_c"/>
</MobileWidgetList>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="650" height="450"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="650" height="450"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="1"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="5"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="366cc495-1686-48e7-a129-d3b1d304c4bc"/>
</TemplateIdAttMark>
</Form>
