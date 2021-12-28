<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<TableDataMap>
<TableData name="ds1" class="com.fr.data.impl.DBTableData">
<Parameters>
<Parameter>
<Attributes name="start_time"/>
<O>
<![CDATA[]]></O>
</Parameter>
<Parameter>
<Attributes name="end_time"/>
<O>
<![CDATA[]]></O>
</Parameter>
</Parameters>
<Attributes maxMemRowCount="-1"/>
<Connection class="com.fr.data.impl.NameDatabaseConnection">
<DatabaseName>
<![CDATA[FRDemo]]></DatabaseName>
</Connection>
<Query>
<![CDATA[SELECT * FROM 订单 where 1=1 
${if(len(start_time)=0,""," and 订购日期<='"+start_time+"'")}
${if(len(end_time)=0,""," and 订购日期>='"+end_time+"'")}]]></Query>
<PageQuery>
<![CDATA[]]></PageQuery>
</TableData>
</TableDataMap>
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="true" isAdaptivePropertyAutoMatch="true" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
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
<NorthAttr/>
<North class="com.fr.form.ui.container.WParameterLayout">
<WidgetName name="para"/>
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
<Background name="ColorBackground"/>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.DateEditor">
<Listener event="stopedit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var start = this.options.form.getWidgetByName("start_time").getValue();  
var end = this.options.form.getWidgetByName("end_time").getValue();  
var endtimes = this.options.form.getWidgetByName("end_time");

 
 var day3 = start.substring(0,7);
 var yy = start.substring(0,4);
 var startmm = start.substr(5,2);
 var endmm = end.substr(5,2);

 var dd = new Date(yy,startmm,0).getDate();
 var day4 = day3+ '-' +dd

  var day2 = new Date();
  day2.setTime(day2.getTime());
  var s2 = day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate();
  var s2mm = (day2.getMonth()+1);

   FR.Msg.alert = alert;
    function alert(data, callback) { //回调函数
        var alert_bg = document.createElement('div');
        alert_box = document.createElement('div'),
            alert_text = document.createElement('div'),
            alert_btn = document.createElement('div'),
            textNode = document.createTextNode(data ? data : ''),
            btnText = document.createTextNode('确 定');

        // 控制样式
        css(alert_bg, {
            'position': 'fixed',
            'top': '0',
            'left': '0',
            'right': '0',
            'bottom': '0',
            'background-color': 'rgba(0, 0, 0, 0.1)',
            'z-index': '999999999'
        });

        css(alert_box, {
            'width': '270px',
            'max-width': '90%',
            'font-size': '16px',
            'text-align': 'center',
            'background-color': '#fff',
            'border-radius': '15px',
            'position': 'absolute',
            'top': '50%',
            'left': '50%',
            'transform': 'translate(-50%, -50%)'
        });

        css(alert_text, {
            'padding': '10px 15px',
            'border-bottom': '1px solid #ddd'
        });

        css(alert_btn, {
            'padding': '10px 0',
            'color': '#007aff',
            'font-weight': '600',
            'cursor': 'pointer'
        });

        // 内部结构套入
        alert_text.appendChild(textNode);
        alert_btn.appendChild(btnText);
        alert_box.appendChild(alert_text);
        alert_box.appendChild(alert_btn);
        alert_bg.appendChild(alert_box);

        // 整体显示到页面内
        document.getElementsByTagName('body')[0]A.appendChild(alert_bg);

        // 确定绑定点击事件删除标签
        alert_btn.onclick = function() {
            alert_bg.parentNode.removeChild(alert_bg);
            if (typeof callback === 'function') {
                callback(); //回调
            }
        }
    }

    function css(targetObj, cssObj) {
        var str = targetObj.getAttribute("style") ? targetObj.getAttribute('style') : '';
        for (var i in cssObj) {
            str += i + ':' + cssObj[i]A + ';';
        }
        targetObj.style.cssText = str;
    }
	var starttime = this.options.form.getWidgetByName("start_time").getValue();  
			var time = starttime.substring(1,4);
			var Year = new Date().getFullYear(), s = 0, d;
                for (var i = 1; i < 13; i++) {
                    d = new Date(time, i, 0);
                    s += d.getDate();
                };
 
 
				
if(end > s2 || endmm != startmm  && s2mm == startmm) {
  if(end > s2 && endmm == startmm){
   alert("结束时间不能超过今天。"); 
	  endtimes.setValue(s2);
  };
    if(s2mm ==startmm && endmm != startmm){
	alert("不支持跨月查询，请重新查询。"); 
	endtimes.setValue(s2);
	};
};
 
 if(endmm != startmm && startmm != s2mm){
 
   alert("不支持跨月查询，请重新查询。"); 
  endtimes.setValue(day4);
 }
 
  ]]></Content>
</JavaScript>
</Listener>
<WidgetName name="end_time"/>
<LabelName name="结束时间:"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<EMSG>
<![CDATA[请选择结束时间]]></EMSG>
<allowBlank>
<![CDATA[false]]></allowBlank>
<DirectEdit>
<![CDATA[false]]></DirectEdit>
<DateAttr/>
<widgetValue>
<O t="Date">
<![CDATA[1271347200000]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="264" y="18" width="80" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.DateEditor">
<Listener event="stopedit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[var start = this.options.form.getWidgetByName("start_time").getValue(); 
var end = this.options.form.getWidgetByName("end_time");  
 
 
 
 var day2 = new Date();
  day2.setTime(day2.getTime());
  var s2 = day2.getFullYear()+"-" + (day2.getMonth()+1) + "-" + day2.getDate();
 var s1 = start.substring(5,7);
 var s3 = s2.substring(5,7);
 
 
 var yy = start.substring(0,4);
 var mm = start.substr(5,2);
 
 
 var dd = new Date(yy,mm,0).getDate();
  
 var day3 = start.substring(0,7);
 
 var day4 = day3+ '-' +dd
 
 if(s1==s3){
   
	end.setValue(s2);
 
 }else {

   
  end.setValue(day4);
   
 }
 
 ]]></Content>
</JavaScript>
</Listener>
<WidgetName name="start_time"/>
<LabelName name="开始时间:"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<EMSG>
<![CDATA[请选择开始时间]]></EMSG>
<allowBlank>
<![CDATA[false]]></allowBlank>
<DirectEdit>
<![CDATA[false]]></DirectEdit>
<DateAttr/>
<widgetValue>
<O t="XMLable" class="com.fr.base.Formula">
<Attributes>
<![CDATA[=monthdelta($end_time,1)]]></Attributes>
</O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="86" y="18" width="80" height="21"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="start_time"/>
<Widget widgetName="end_time"/>
</MobileWidgetList>
<FrozenWidgets/>
<Display display="true"/>
<DelayDisplayContent delay="false"/>
<UseParamsTemplate use="true"/>
<Position position="0"/>
<Design_Width design_width="960"/>
<NameTagModified/>
<WidgetNameTagMap/>
<ParamAttr class="com.fr.report.mobile.DefaultMobileParamStyle"/>
</North>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-460552"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-460552"/>
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
<![CDATA[3390900,3314700,3657600,3276600,3695700,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[订单ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[客户ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[订购日期]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[运货商]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[应付金额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="订单ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="客户ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="订购日期"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="运货商"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="3">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="应付金额"/>
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
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
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
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.SimpleDateFormatThreadSafe">
<![CDATA[yyyy-MM-dd]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-6697729"/>
<Bottom style="1" color="-6697729"/>
<Left style="1" color="-6697729"/>
<Right style="1" color="-6697729"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00]]></Format>
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
<![CDATA[h-#)(;g5[IG@guU$:R&9K?"ZuSPFac>2CMBV9!n\cmbM8cdYi\6jOVs+<ej+=Ag(]AF)+E`+\
AY26jaXG1I.[J8J+L<8V"I2#U+jHOH@EAOIR9E><)9?>uU4'CU#LArD%BRc>W1MIGb%tNsfk
p7rJ;(mbcdPfkinMf/MQpZ[8S(cL0Sa*B)S7H1@Cf_9U?h08JFEn4m;D8qR&@#p9SAWeu\jH
gB1RG",<bRG@bPkIKbn+g*us>Bb41pAs47nCrTAVtdo/qpt8kDd8(4j5Ei1L\,?l/Ust:J*G
;Je210Rh:1($WN(C:C@?j$r56*\PN\`<]AGIKSn1S-LZ;2>3b;)V/rAdsu/iQi`0d`c;6@lMs
H1Pk?,mJ*q0^[-OQ4bOl@!V"eaa)tG\TmqL4V(FQJ@s>p#:YS'6mc"[?'CMnMLKWO0I6.c4*
[@"n%"Ie&%r?37g_V,9\E-iaY.?_;hN_4N=PYDmBKegpW.Q5RS+lWD?aCa>cG&UQ.BFSIbh,
3W_@/r>'\)b]A=,0!5WsQDU.p?^Z&oH5QD)8JJ:#g=S'4q[adYiY96X?)jL#]Aaandc[;'u\-k
c=?G4'#PAC",ElVSu@j7$#F$d'pO0EB^D/ELcLR"<TE1bI<m:pD\sYPGla%3PO3M)KuYr&e8
D0gZuOE^r]AF,?nP'NVG:-NaumSgaucVa,#10T<-D07G#H%kdQN5]A^hgHpQGtpRot5N]AW\`g`
Y!3j2+'2BdSr(+uK;+Sk(t#N"oQD%u#-leQKus"@U)94G%LmU:DsC!@SNR\;:Nt>,]Aa`aY.D
Ri"K@Lt3YgN^QUq]AOm8g85)=MoQZ6Z6k=O)tFP]A@t(NG>X)AUq0^;gY\j\!LEbqjS<m^drU&
R-WBJrF1*^?g$r(_H1B![n=qKOF<9Po@.HD\q#UM<1r.n1EcL!Cnimf8<J9;U:M7A..rMqf/
.;&!OHT>ne]AML"n66knO@+NXH1mTmg%&LhF[>@RSh#8.m\f>n/iGbpVDj5/=Pe#3DO$PufI0
R9-;In+n'bQB1+$:-=6MP:^VC?V5h&*3LS`IN9u*\mpJu-*+/.gJHj55M#FI^B6T$mBjGu7N
mnrF_52[.$jM)K-$uBYl+Jp\2'@^u7A%A:)JTkReW,rYNW?/u9!EubV1i/0qejj9T)X,c85Y
^5e@ELi<7G@Z7-=59BqI"C8Y\2rRSDlobPX^lJ(uZt#a%*\N]A"aGgZ:q7ZJtQRBV`7]AkjnO8
jmY2XeEGJ>p"%YnA@=);R`^]A'h;bMn^A#sX@=fg+"qKB(\2c`&TOu:,9PL"\F`Da)Rhe<UK%
4Ap8bYp/D1hQ8YD)'Jm?.dDa?f<o3)Iun95V5L:4j?q;@Y;[Qg'R5`mYpt%Z9@qt#]A:K%SPP
TI@ol#c)]A8/A85=+&+U.kJ4Eoc;%W;]A/n&FEgdkdL+`ZY@-<D>MU/0ukZ*="(jn63.]A;X6bG
[bBIWXRYVrp`i7p4rpW*Vl;\N4VojLYaJuFMagC(>PA,RqHsl*(_775\&>"-M%5gmBPa(WmV
jOU#5,1B[9qNEKZitBYdjOcLK<[I5;!CYjMQhoVA`l#hmcKV<Y"d["S^%0ER'LQPrLf?SUg1
/?tg%=VA#/lfT=MTA_<N,R@<&F1d=4>aBFP$#$aU%7g'L,/'<&=2(qB1"R0cV:1g@Z#;F+r?
s>aXiXr`r0E(:qlttZ1gJt@Kf'hW1Je#qt\-+/tJBPko)06g([t8RNi+cf:AWU`pP#Wpn`j*
'u;j67XRe$Noj$=W:bnu1jZIOL#ldEoi1^`\$BO>0CG([@:WUr]A!q.,/u8<]AGYSKsjJe.2/&
$U.7p,U0G+*gbG..LSLqb>_\mK!"[6ki2AdMrnU0X\'g#(QQ@b2(+o[bS'"DR8X%2<A8tm$#
6?`W#OJZ97q.d&h)h"Z\m?Ac?NK,2..G_Dj]AqqA<IN6#D#fpA.;rIK3)pSB>Fq\\69@J4Oc.
n_M1bM^"1KX]A'C9/oquZ*3LCAeH%^[%il>/T`%K(_NeWI-..5Fb@bhe:`^+F]AK@aLR?tEJ\k
u,-4Up<LnSf#<cDX`t9Xq6Jeeur!@$@$3b`n@@H_T?<`g;E2P+s1bO!&1^@&+5Zb`,ZRV]A39
/kp(cht@6i1NChFJ:E%Y)VJ??i=\n6R_qHcUGnrr[MeM8=&WdiQTO$@]AR2?kAhl2W/c."V*u
mb%s#-pT;'Li]A!'erk2:>i]A$1#8j/We&YbMeM+KUITGhRL6I=$HJu@j6=Gnc*YUn)1YKjca`
81a6o^$\UD`Ijd\F2FDP:gFJ'o2BlARDTM\;4,;Ia\S"Wb7@M&fMKNkDS+A),AJ8C^[EmqJ]A
q;?eJ:gJjJYZ9s;2jb1eL<Pn2MXdW(G3gFUa'lTR)::6e=$EUL(JdIu-$(#VL+$PuCbEjTV3
QCt?hBPieEL/0GJ!_oj'M1DV:9qHKbuo6;hn?n/Qg]Amt-WKC+7Ke#,X=d`O0]AB4=4:Firrog
E/CR+"U,A5X]A4kEg*Ilbu`0((f09nWTV5[^c>H05qA(hZ6K*c'$C$PJ#G)2![/A\hdGa0:3*
0L"<$4>b9fs(i_UL'+bQC4SC!:V13O'[4]A.C^Zq-oR4sZii8NsQEE_B\`)I4+R*d=9c&&Y9X
+MKRW&_uDQ6+>*Gq7&oU'=?#EgER_Vml%9Y@T]AUWap$VfRRN/R=%>=;kTKIgcS5!@[j)p`iA
Y`mttcO]A%/D7?jh'?C&d!"Sq2L.i0"99N-'oq;G@phUl/`+A%'Y6*`84`o's+*,@t5`Qa(X6
LhI8fuUr5i^cEn,;XZ"IlU*DCZ'o+D`(e\`+B>]AobZ>-Wl>XK3'?$!,DSAa\@0$Hc&@kSO3?
2eE`6KUMH+g[W!0&3PPWHgn9[rN&h0hs9(E(&7CunBT\H9.#9n:Glh%8AB.B2.B-bB;7fsZ[
Y@qU4O[bq1X[N7h0$L;6['\cB*k>B/l&i;O@br2a-I4/>47D'BO;-ni5iVhKa1&5<iP)4ZF`
IT=Am\=DQaNSfN&+duq+bDSFk(AQoOY@Ak,AGt!]A(#H);QVs,)Y]AEUOl]AZEortla;[OZL0DS
9M(eJm0;F,dUM+",pAFWsq$+3JnN7Gel/Y$m#9H=%-0=%t,N^i">YjLN-qD\GTf=L:XrS(:[
*)FU]A&ZS6-*NcMfNZP+gmf-8_,45ndo8q,-gT_b4FSi91b_"Pe\KEi".>]AeF'+rrT:9l2%ol
@k>dVQo9@kbpr2P@.n!59S(JB`PUq4%Gq^md`;IhDL0ZcuiK3.=fU7Fji!'@B^TZ5egm\_H9
.qLoRqE6@M;G__LFs)mV_V^=%b*1.,!YOc+'H;*R$N3a=WVOcn%9mPomTh>bS5^Fr9dWYc-s
6D<'-Up/fGOi\-Q-h!Fr)Z[m(f/Q5rg.4khAgrGA01Y`Z-of3<doF^W\L4j\<qH^.+l._%\j
Z)086R^o#h]A\9:R1m1fAL:q+s+lQ)5KD!at'6nNi=+>-bWCAG[;8(eMTqQ3=.M5s&/^Hf5pS
jf&HOiY]A7AE-p^VE@E-Jd;tT4csQ*)+F9g'mU@W[2J8T<5@ZL(T0h6b@t.kCZ\k#4g12r@f[
Z1ApdDH)&Af*iRI3Z'0F!+oftla<(MpGP(?`8$CB5plK%;ej.&oCn^lmk>\a"pIt]AA1A,fTt
dnO/:3s/6p$9lfGTa1eM+&UtON!E[n2iqb<k66/oe@VBfY?3&'%cnoNgM(3ijg5"R(rQ-JY/
#'Hf85niojGO?7f9=k[&&pqBp$i>q0eqX>unX%ijOb\7V<5:#F<jrr/`E7Cg@D"_aec@Ou+0
hE^*SdfMbB2#@qPW4Jq]AY_m%*ne,B$k$=9\]A._)'Nckmm,)<6XTrGDd;Z@P6q]AS&mp!tLF:E
Z)p95auN3\LUeSjN:1P3(f=-UE>XNF(&T*L.bbmOb%VS9^XDuh<"ceo`8UBLsD'sk6;l4l+D
Fdcj>^kTu)VpX1;@MIB!L&O5nn5NFilPJ5I[GYYI:W.1+U*?a+D?+-$WT8:*D>.C)-(KD[[F
gJtC#^lL/kKUC/2#R<=UMAOT8jXLE&IW7f]AEj1)G:<CMdT5qBg,ADHKfs8:RV@/J/3+?i\"^
AOKCelr#bGdlcX#u-H=R8>_R^EEuAK2J?d);"fY^!C;8FYd.r%$jW(dQ$('UVV)"muh_Xuu3
Plkl.Pc9M0=p\jN7Kn+1]A^0ksa24HOihoGd>U/-u[B^TRT_.jl#-u[<"RSC,o0Pe7H7ctUY(
BTAEY;loKDi'p(]A,pWP-b<p&6s82%N]AfN[d,#is]APTI]A1"K1f79S>.IX>e.gETJ0'`RY)H^T
8GJ@>Q;/4WkJ#+0[^c/_L##+qsF_X]A:b[Bdd>!MM'j,0[>FGi34HkPr=Dlca$M,#klOb^ZR0
LS6\RJ+P!U_<j!<ThX,DBE_=_!F2Hcq1Pm\HuQX>j)uh@d9Q)_1F7DdN(RI0T?m#d9#8+no5
o81WRQQJG/#o%Bj)Fri%!M7J[:>!)6+04blG^hD6$T_LL5?=em(Sa<gsYnp-4e^5><<FX#ch
84&=YtU:g)mJ;*lBf<iDsD&k6-+e<.L+dus@lR$+gg5Qh]Ahf7`*XG;%S]A1\_\Souj:$.Qp1#
jO'4E0AE^A<rF4&@P9le[(&u8`cO^M(ju#pFutWWPH5OXf,%S=i>Zg6o6]Aq74?Q5G6I)uPT,
Nm3MZi?L3I>mjgjV%0rD6D[&=CbYeh>@V[(-MK(e4u!$%tA7ZK)2\nn>]A1Z"AOKUKWDD/=>f
a:.1bNbZ%b9bK,2U7U:7__<iNGh:F;+$ZslIiL-m1:(ihaGILqi!]AK<QI2'Ln,b2C"Oj0H,+
l(=/0`1'f<"Em7'o+MhUQEMMtspZqIe;MjNbj($:]AUk7EO]A!\Tmskh;9X68[%`@>%lJmlusm
?.a]AR8QWCR$msaD0`KeXhS%0&?]AlKn*DY6NNK(nYUb>4"i^b&-]AOP`CY4nY-0km(0@"cIinf
pU81c[4*`s-<6efKlN%)mQuVR/YIQ^[eGjj(q!3%`sP\l2>-b2K:T:Ru^D?Hfm+aPCbE,r6)
+4)2@gl7CN#/[.qOh4)5"o0WE_YhpSW5n!I;UDD*a)lt4=_HR3"Aqn'+#CC#L35S9`6?45V^
E5f2"$D5%iM?=X+a1gVA=W\@_[['-;?2/(YcYt[$fRLHo&:`Ls^<$C>2A`[1XW?mk",BqE^o
78.NY"YUk@MuA&Yo/tZPiUt'<m+ph+=j$)Ppa2prDE.HYNgl@brsWZI(E=U1cZ"$_"a9LM1k
l8`tEBlFrTIQP[(\Y<5!qpWEDOq*"g3:Kgu;,ctb\-2UcGCO[6[>&KVllhS3(PYh"A_4T*kp
FFhta[IE10&gYDOCE^eHnUg5q]Ati1&.1d>Gdm@m3p7Em_BKcI<#81#<!`21dNG_N'q1bdWqH
&KXME\L9n>4RO+fjpQ23#_1Q@JmZjYV1T-N7SU*qYlcYM2n[3T^!G)"CnaBu'j,uVa]AB;3SL
0HIULiS[m?IX1l0nd,Hp=4Z\ahmE:1dkg2#-!N9%60*rZJuR*&kN?.a[4[[e'"T2?IV3clmF
De22:Tim6c;.h1Zk1deYkT"SY!E;==b'5EL7ip[]Aa<kCFk70rUbL6cA<"NRf+?CB"=N[W"f;
c'kdM)08SZbZTFHnkoAtl4uZ*sMoH9>g028Kg1Ho.?%g<h+`?mTo^N+76-hr[e^sO&,(oM>H
Qb!as*Z?WRF]Am2:l?FsdY_KWf8m/_dAKj>A#r*<O;<2*^mthh/d^#7.M&oG@R:Oi:h%A82[?
*`f'^C5Ug5e-%:N=(h]ADTHqV@4"USWu>Y!'MlOO4l$oLa5ZBZVsf%@_50E*S0Zj\.'`Zs30<
4Jt+u0IomXf,(UX,rqhe"+#AQU+MT7Tp@Uqb'KH]A8+&N5c$%iTbqe0q@1D3DCjn[@%dfa%iH
?7A"c.t,A\72uctfD/jl3bn[Cce%6HW<kjK!/hlijXdA*=PL+F*0s#i7I1*p>*^gYF[2_Ukc
`9E?D3,9\YBMg7Mb@q^dBGr-uc6VoJ'E(0TQ@kmW!^(Du=f@s!m7e7/8%hH$&.ftZ&LOKKU2
Z05J7d'%?.K!14&OpL`JnRBU0UH%A._ONKTA[.?IfTlIp5ahL9"I;DJK6AenfV`d:4f/7-\h
,RA_p8L"@!0_'fl7:CRWmK=WA5DkO0:e0Kd?J#TSMu#N[a:o2"iTfkaEqjMeJhighM7j-4DC
5u6=(5Ei;2E+,&eOh79AVBp'E#XhqXM_LZDc79ds't.rBJ=\bX3RBgNTZlElLHmEb]Al.Gn-*
:D453mrb%AFRMjc>U*a=?<lZYkF(6DeqN$R6_u,_'B/&W]AdKkddom=4IEte8G#TatAGX5jnH
bZG/K63haX#)LnR+P^7m'g48a>"9I;Wj?6;j"^O]AMi*lpHgiA6`F4ei7C@!-m"Go38eDGRCG
[r[7m/s\N".C#K:O.d:2u@<i"A1\,^k'M15gLu>G&B:PkOlk<SKWGnMa!#$PJ$\CUV2$30V,
'2\9Wl3+3D(pX=(n3Xq0j71(ie:Xh1H"'ueNU%^lI75/-G0XhF_u6V0n5M6fBSD9]AZ%YNHiU
['fp;1NuGQZ.Atgb_aqUd[=AP`Ja2,W91QY5uiL#XA6jupp8f.BQGGlGsUd#U!63pjkaXbFV
([32L'4]A:pK%?EqMF[2R`l]A"!TY]AoI<,e/F8:>FEY&MH'$>3D>r&`nRIq\>FV5YKbt+8<)SZ
ZepV#@dE`\MKHJegTY8R8Q;QIaZqr&0$oK!<&LjNI^48.I=I[ih8[^oR&<oP/Jd%!(:lVq*J
tDac+o\-:]AuqGrhdj4FNq+bBW\%!</8)jMYM>:uMFm5WBgiSG[rGO2#lj`>Dg.Ang)--!%;F
0U/%l&9*GkY_?i1^*eRAL!XB%Zn"/pF3a6(rJ$uNk.@fCMmR_=2eDE:mgbh.nfhu$1VgB%'$
jh[12WkG'CD?"8X*=[]A&_=sT&dHJ*#;L7R<APeU2a^qXHZ_t1;eED9WdX")g.n!(POYZX3ZT
GC@1M(&/WmW6-3&BP1m<[Dk[UUn@?eQ?doO^CPdXn'P^^&fRV#NY3k*MY8J!f2<9i\&rAa36
EK6ChKiZN$dK3d7:m9:*/I;^J4,@Wc]A24$R3&J/fdnaB+aY>-lc@Q%u)Y+Ma70fhOm<I\G&o
KU9s[HX)VBcY5V(CqtlPIN_B9AQsu?nL:8Ir_7nHMe*J>5%L_`#%cVIto8Chl&d7hRWXh~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="375" height="560"/>
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
<![CDATA[3390900,3314700,3657600,3276600,3695700,2743200,2743200,2743200,2743200,2743200,2743200]]></ColumnWidth>
<CellElementList>
<C c="0" r="0" s="0">
<O>
<![CDATA[订单ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="1" r="0" s="0">
<O>
<![CDATA[客户ID]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="2" r="0" s="0">
<O>
<![CDATA[订购日期]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="3" r="0" s="0">
<O>
<![CDATA[运货商]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="4" r="0" s="0">
<O>
<![CDATA[应付金额]]></O>
<PrivilegeControl/>
<Expand/>
</C>
<C c="0" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="订单ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="1" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="客户ID"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="2" r="1" s="2">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="订购日期"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="3" r="1" s="1">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="运货商"/>
<Complex/>
<RG class="com.fr.report.cell.cellattr.core.group.FunctionGrouper"/>
<Parameters/>
</O>
<PrivilegeControl/>
<Expand dir="0"/>
</C>
<C c="4" r="1" s="3">
<O t="DSColumn">
<Attributes dsName="ds1" columnName="应付金额"/>
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
<PaperSetting>
<PaperSize width="24688800" height="43891200"/>
</PaperSetting>
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
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.SimpleDateFormatThreadSafe">
<![CDATA[yyyy-MM-dd]]></Format>
<FRFont name="SimSun" style="0" size="72"/>
<Background name="NullBackground"/>
<Border>
<Top style="1" color="-6697729"/>
<Bottom style="1" color="-6697729"/>
<Left style="1" color="-6697729"/>
<Right style="1" color="-6697729"/>
</Border>
</Style>
<Style horizontal_alignment="0" imageLayout="1">
<Format class="com.fr.base.CoreDecimalFormat">
<![CDATA[#0.00]]></Format>
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
<![CDATA[buoBqe*#D]Am5AW@0^=mg,&DANn%1DeN`&+PRC9n9.acZnE_7&]A-$%?4JV5:I:hCq_es9ElUr
jf(Oq]AX(&<^"kAr%]AP!WbWD;';Bl!'-25([ObCI$\0?rOtMSY8<ArF?fM@5PObi@;fu+\i7:
YE,fl#\,4JnIH'8W8t1a^X0HQWEr>%nB3d5E/mDbr5MGpIIYC[7]AmRHMTPWIU=X^iA\+A/eI
rW$uGQ78A^@LFC:=m8U-[bR\m@V'(DqPFh4je90(WUPa2`lqh4M7F]As%$9)3S'/=jiQ:&'Ra
F>4GA6b:@Kus&fR_2_VfhSJ^RpQb.uRDaqO;JB<)r>Wlf-0.>K_P4&[iUXMaMhj5(m!'D9/[
d#1aEh]A9J6]ANN4KOkq9ms/dYTZ7cES%,S]Aim]Anrn>MC[UW>RF[Xgstjj,79VpgCUJLM6s@+g
f2tZ>bX;?<KuMhJp`]AbT)A-7in-K2'i(=)YelU83F&kj6R%_X,[BYJ-\Y(a`gL[M"1o(q$6>
NPW/j+GZ_^p(_'9K,Jor6f4Y!pfa-t`(Du$/E`[L2_4Q1!T5h=q*NMT2ZdS"A\`[[Hob:@De
#iQWGe:J4d7YM5;Vo>f5iL>;TN4lnB2`UZ0tu<=<JX(bFlEQ4JFC#<4Omc.JLn2)q3rMm.5U
*k_Hb\1qY4^5%icN7Qr?25e..9>b[+oVdg#0MAFRsMT4Qq!rm)7qR2[qB%>b]Am(E68$aUFeU
i%3^8>e;ZW\X57a\\;(dfUSuWleY>.=T8UZ7+h,WIE$bGb3rg@"=%G`h#?5(QXC?80+WR+_-
<n"6qJahE7TL9+0HP)=WpN7[8gd4-=TNh^sYiX$tkA;m&^41N`U\m.8LqkPpu>R5lmKD<Y_;
aWY!4+Hh7J%"lp)!/tLQRgWS3T.#QaW8@9@74VLH8Yf%Q><r651Etp!FVcof1B.o=u$dpD/:
$LH3YGU2(#rEgqD!K7-q6V$e'Tk6lRiG]A8#'29Xp%Atdb67\+i0<`1?,4O^9Q-@*8+IHdA.]A
mp4QS,gZAHDXL2Hokh>0W.T2o`;<\'<SeJ,9:D.@=WD=K/':0_/omlGWr1g%[Q^QEh8d3,<p
d#:b\AA/Pn;Q@*goXsF53;R%*XeQ->aE"n)10>@C.Gl11$Yg(?+Jj19$ER0)GjVRbMeCK'm$
`]A6feV<L5s;6O<Ne",$P*,1m+k:!ED*j!/3bo%^L:s:iGdsiOc86gK`;CjK,,s7Y@O>HUk\b
`@Uf[XA8[3df+>@BJ&!u2VjZ9NY1)"nOQkl/oJAU)>OP-t[<t]Aa$Htr9:[A1Unr^0kG#F5Yj
d)(K+-oiun.\fe\%0'),&;)%nH4ZK`NCKoG[KT4DANCTM7/;]A^E'Y(-)XQCa@0u3PFGjIB[?
)a_OlP!+Q41fSP`na\kJ9\)MGZr]Ar8Qb_^AD$1LO+Y:79DAHbZ9GnFS[j<0n=r:f(u'7T0'=
''(,':h/$LP*#>X!WtWKTAKu?l!^(MPd)QJ+W]A*40^EQQ!gh-9`IQM(#A,ss962)e(JA9aU:
JgNb\fddL=c_s[>!oUj[mj>UnLVmB%S@;$I9Y#ip0(R^Jk[8kQVbDT[rPi@$RWna082UaPf#
p6"&H/KJX-R]AYe9>@PuJV%@1I5r34!RahEA-bk_b93A0Q,=T?mKKPEUeq0%IK*CnO3$l"@Y4
9_?AjGgFY8U4K6%pTVmLBWce#UPsK>pDIdM&IrUBMTksWPGs!)iaPG+'V%FWEi2T[0ENCTSG
ZZ*1I!<_-0\u]AdI#t*F\,BR437Q/5ka`g5I?>mMNq&RX1:fk09^U$M-X&/JobtT"G&hgk6ME
0Q`EU"jqLrp@OCE`SQET(>9It7e-^h/X!Vr3sjJD<[EHTlRC3Lp'JW[a=>)]A<-MP]ATrAa0Y#
eiA;d"P9D(66EOem)j0&o9,nD;#C/6J@=4;ET+[53NYl_.^$ck;G%R_JJ%BE5`!^K?K@bGd=
$#(4A=dCa0r4F34lC86P'q#R1$=E`:16J,C'Zug[t(W.qRP!.3Obo?X]A<g%U-j\]A0$?3q[L)
S]AgW)d@+#bdB<a!'<N$(?EbrAqdY<ed,+JNuW_e);h,D"RWG1NWM+L1oM",1CP98C)p'j]AWO
B89B@\=&;sIF\]A\ruXX.`CPb!]A!_V^M8*7PgZ\XXm2-n=4eO>uE(?\9\uJB,,*jIMl6`Ums%
KnaMFp5itV%%ZjdSGL%Sj=2%Sn!&(FnF1[nrU3aBb%hWu3VbKLe:u4D]At""X!niZ;%-H6!Cf
mB&"1,`q7-QkT)G*3sH2mfIpk7H#[i"hpEBAs=3mR"C8\_BLHR<85BWtu\IFd5m%tr`(^3E4
FoFJ@/Y5i>l_@">]A3mFt4#L*I?[1YJLDE!lu/-8i-)?J;jA1H[R1UMcl#]Ah=sq6Wh96rKaKW
3!8GlQn$-@6d[prPYX.,Z0dT1Jh23mf)_"(a3?rZ>l$G3@F@8jVm81-?kPV*GN,5V/u;CnSu
4!:sVR2;JiHU__r34KP<d8R*>i`->P[,$Lb5->\"&F>PDuIP.7r]AHBld%<'qUeBi4=&PTW;^
8;/l'/\%mF4)GcrC;B8rl]AN0='Eo\qn0B!J>i$M:'#XoWM!#7Yj."7g8A&!0(</Z,'@C%IOD
G#C`f-&u\<ITlit@iq94=oS.;d'A3*aXdFU0Kl":rU5YPD$th(;=i=7e5MW<]A9:l01&:>)B;
_g`0]Ar2cOTVo;R]A!PW7?jDm!nZM!31Po%\hM/e6#jF$l-P)mFo$e#S89m%o^mPUR3<1nh2!=
Fn)-YS]At-mN'Qb'Q9H74d\/80hnZ5O@>q/+BoPYhLV/-Cj1n^:"BdtV:E<50lF7D<uI;YFDa
A_o-aGM-,m%`cs!BC_kl"2:EJB>"LjeO"m0NdH??uh"iDWPC"o'\:d(E9ej*3N4KD>Ggm\OC
-5@;^5YlLOJ&NPdX6!b*YiKD7gAG?9AtFsg2Cs<;Iko(sIu5<,B=B:9n$>]AT@Nl2R2&=)LUG
A<@4_Xecl!'kEi>2,8N0guO'qOcfMW([I%!_^qhacVG!b7o#W1L8JDRYQRN.pn@Vm2+VK-mj
*869TZD1dF=R$/RT@HpffH#7"N+#<\WeB[QLn2$%\QL9ep'T@$k7gm;-KX[S2aIS=0HH.I$#
E\Y5qrB8^0ir]A.7E2TRe>$%NdB_6r>FlT;@:K_4YB=Jd$mg25MfW:K7N2RZctj"f'UUOh/GI
m-=M]AQq),Ig1JtL9V35_8pcZZ0QG\H'*d<JeOr,m>(@N607,`NTbb8:XSG1Tf:VK'!)FE:/+
Au_P-TDE\VYkA"5WDmq*faNdtgf'66W5HRr;neZA`sk8jiMo\m0/D3`Q7:.8Y"%gp0OE;7B9
EBVZ_^JP-YNH'?DA$3q-`@2_.D9`WRaiJ)Yk/&nY+uJe%6).r]A3/[comaNZZW^S780ejA&`t
>5uZ0OBCN,V<EWh47!_/_>j?9?_i+]A'hP3eK=\*Gd`1eds(RcU`i98e",.YtgBTo@q$f^KTJ
d9G&XnrBF,Q>qX4B/sp(T/ps03lE@SWds-WtZshgBI@+f!H/&0m8Y^HBKcm<4a15b?8?^_l[
@]Ak+m-C5%LolMRUiDBR9mp6L?G6&kDaH%3F&XI3kD'=/n<4lo<fK17!=sR[Lf?_q&QHVmEF<
#blteeN>q_TQdc(\P;TF72:r"L!J3)3AEKLY.51n6m$?-*'e]AC-(Y$]A2Jq;(]AL'GRKBU@5(6
'SWUNo2M>L^B!lB9g(I=2[5=Z2.c]A)*RP[W-G7W&80VL)I1I$-c852=3M!,31ARZ\3OhD:`,
QBGR7gnEhd^BgXo".&iArqclGVmXuJ4]Ak+^KL.'l<9kqJt8i8SDg1Z,L,:aO*N]AXHo;9?Q.[
pVEu]A*'csVY@&r6n(,]AK7]A"rO#"b[jHaG(J\DCM[QT.`Z0#3WMR<b=b3T,WY7^[=0\iCGk_L
R;NAo?.5\4*Epe%fPq/?br"=nkiQ=mZeLrpTj>J!m;]A+3t=WAO7^2D_K_gNl]AihFe&7M@I%E
%o\K,b>:gc3#d@qcm,obHAHU?YPGFq]AH66]A]A]A4MY1nZh<l[,AB:-`6K)t(No]Ade\3?lgZVWJ
uP^eE>l>Ha?i>hRkntA#'HQ9=^Zh3t['"YmB#]A7bcGfL!E%mVIK74r(5o$_j-og+i*mbZ;he
)^0sh5#8@'a1q^IXk)]A>!5aQW;PZV@[V(G>S4*f.SDEB$o2[+/p(;hlf5*R<[#g%`kdG[,R9
gE2<8Qrb!gJfkC8c7Q*,6'gETZCBu2U2tl?I)6_8<RO/3R^O#:KF6[k9JADh:4Bt$^*p7,R#
Q_IGkg6!(u!=jOgVOQ2;YjL"[]Al:j^tY)"q:E5h@`rd;mbI(-@2a587G:R2oU7H-G75`S#Us
1]AK(c"OO<JE_&G>0dn)"f!+8G_rQ=&eJ3&X]A]A"9oa00cLl\6KGJFg7e$AEmO0p35Lg18(:in
(dAdhn<Im-^@/ZgOFII6gXBanBo$IR;MfHm,b7m4Y"\<T]A0W7Q#h2'ZVLrqqC,B^Cn,AX!I^
-<S#+9$G.1R$/$pqI^TtNL276>.7N)n<o_NT^4K;?qV-YL@=9fi6h*OJaSen1]Ae_?@Pj=.#*
p[0JSGN]Au6"KT\#B)KnT6eoI5@IgK:ZtP*bp4n`CP1dF>;E'O>k,)(^3j2DS-jUrn8M,s?cq
j&?T9brf(Qas]AV6kXC^nUf(RP]AGc#dGbrOC^okHsP65JhWbmH)>>/.PnHTB86+<pIYtl/noU
-cRf$:'8E[?)R4cq<*s._=7&&rbVEh4'MRJE7f1:#!fe2muD5\EM<AaL4Y(fac5I<U@/"B>m
<57FHt[qs&RW[6Srkn1W2AC6r&!N>e'B'YkG]Al!?qJMZqk@XP6SE?0,%!g(\30K-sjK@[Z''
SC7I(?WSqO'?YZ+!WE,]ARL0T?OH/GtRo-JHj732o6QD2!V)*Q8%-Zo4dJiXo03OGV3k.(e\?
<<bH(3<MriapDQfk#;/esT8'QQ&/9OW']A@HH.1_M49oN5G(?(^_qrZVNV)^7Pg(VD%>J-Fma
ClIg'oGd=D%QY($UR<dY80Bd$sYh0pN]Ab,PCEq?tU[eUDHV"%@Ti9O5B>252Z3$jI!UoPKI%
"!D?h"3FOK2s6,?1UN_rTH^NKg'=n=L_8Fa7fMQ2Ku#5=KAR%XC,jAc%o!-U?Tg@53'cSVRe
Th$krl7KT0sY/HT5h%P.9hfT3ngOBTVo]A9".$p;I>ab'.Wp<`q?iZ-*,AjF[1VfhajCo82Pk
Ks-F0[VV7X8C=KUKFnK_sGG$>tF1]AIAI3=:V[eJ=J5^r1_GIe9kMi`p$%U9pj=$@fOHT4H(r
'GrjYBH:+Ng@HEr(o>Op(.5<ba>1qqZcO^W$(-?2ZS;BQ11Kq3kGFXPEl:Q'YkZjd`Cp:C#+
Pp/Fh,ieNl\-!&CcS\5uDja]AMl+oqI?0W$`l]ASb6M"&0_D>n.FuW-Lmu%hgTIK9i'tQ3)To<
Hc[ei4agIe"ceFQrThCpQ?CA18%H*JoGPG\=]Ah^,JB)W(3*^fBOV<slp<`X4mATrE\/3i99i
fYPXa3?-<:f[@>Bql@'0jUnF=F';J5GjtV>(#gHr/SDcae#dpJ+fr4uqc9]A0K)OR(cB^qDFB
,lt?Qq#,&@s7WI#T]A-pJY/mpb8eQ-',9@1[Y`Q>Pf]AF/\PX/&[-UgVpsX[7kCfo%V6_lSK]Ar
"Z^.qa2/Bj`C*D3TA.CB,cq/XR_NlEn]AsuY^NKeOBXC_LMbnYg2-AfWt^mZgrk1[,>d*;1)5
DV.%kcr\rg0]ADZb(kQsXRc2LfBulig]A'\D7UO=+kJWDkI)p(Y7f!^<B/=7]A#B')Vm1i*V@J/
OdD<"Ps(81P`/r'$P^WU&X.P:M)`("TFNYLS$#7=7kCtR<=0kIhQHut#-KWm[,]A+j1g(0Bfr
NCi'LW#pictfgT=FHmDk%0mQZUr#'@9e!@#8qK$#i6!J7"Hd&&920(QhW#QkBQ/6D&Wei8BJ
&A<lq*[-3:Rb#o]ARN_mj.D1SE4iq&Aii'o"/Fo)2>EeD@:A,QYj`^@K%?\`b)^6;r]ABT&W4(
0d-/E1&DH?,jtdi3Z[Fa>"%qM(&&gJ:`ZM#XDAT.+`<'SX\[j"HoAY!U@acN!Y9K=KXSgR>o
b$DTq8oOU5$h.*YE30XC0J,MPHF(LE5mF_qj/QD$--M#PO++%/jJ&/^omY^_Z(8h8UsD+#=-
*6J:kYXa2^^Ng4&]ABW%L%TM@=!kr__p23<doeumKQO$M'$\Wl7pod.qjJgi.O%>Cb8MYhA^_
[cARg_T&=,=%I7gAE)JHbdMnBf5s,iMr7&=6n41Jre0bOW<%.Ak*?h9=/.cr2Rk`^maDT1A@
-1k4u8,P5i'gTIodebS96_OcZ1JK'fFrne%$P%F2hPobclXp)3n([//Qql$2A2WBgE3tP(JE
Ge/HW4H&oMW:(i&\lk/esFI`R"'aI4cEO+5okN]AiP0mAHY.TMrbF)99?PVQ]A]AjH^XV`H3=07
7l*^Y;;dBou>+IeG)[L^lB>FsFYPs9+L_i8Y"O0:=hFg+:@kk`VpC.Ug$>Di+[?u9-Oes`O*
jWNb_.%o>>]A=aP[FHQjlR$VJBEamK@aR[HFW'/3`Ujs*>TM,Z42?UJshgiqW)u)U:>#FQLI,
5$Mr.-&#V&G.Y$ttt]AZ2r;Xb2*Rd$p5m:-h.oc83U:H:>&5G"b:q2bDhD6e$_m?C&1dcZ#W;
/kSN[Kl0g?Vq>C7GJP88Zg!S#,c:>='1(_'Nb).*ZhUpW_7jRaM^p+Gq1&99Y5^>Q\i?N#D!
dglrdeCsD_RqTFmir%EJH#.urs_IJTVG6\.pc&U-5TV&eM\$'HQY,)iT$!`2oc^b=.h=<!s(
SM3(,*h,V*ld.]Aq3uU0oLmZEd:k@7Pt(PeGA?2MVDaIX;l-EF7@Q\Tb2^cIm4-(2"HRs4@G-
HlnG\T;6/8=`ABYm#Hu%LplWUZi:+~
]]></IM>
<ElementCaseMobileAttrProvider horizontal="1" vertical="1" zoom="true" refresh="false" isUseHTML="false" isMobileCanvasSize="false" appearRefresh="false" allowFullScreen="false" allowDoubleClickOrZoom="true" functionalWhenUnactivated="false"/>
</body>
</InnerWidget>
<BoundsAttr x="0" y="0" width="375" height="560"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="report0"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetZoomAttr compState="1"/>
<AppRelayout appRelayout="true"/>
<Size width="375" height="560"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="4"/>
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" horizontalGap="200" verticalGap="100" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<MobileOnlyTemplateAttrMark class="com.fr.base.iofile.attr.MobileOnlyTemplateAttrMark"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="81b526f1-6338-431d-b21e-60a3112474cd"/>
</TemplateIdAttMark>
</Form>
