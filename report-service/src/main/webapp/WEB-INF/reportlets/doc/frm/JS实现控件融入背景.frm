<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
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
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[setTimeout(function(){	
        
	//以下修改文本控件、数字控件的属性	
	$(".fr-texteditor").css({"background":"rgba(0,0,0,0)","color":"#c0c0c0"});//设置背景和字体颜色
	$(".fr-texteditor").css("border","none");//去除边框

	//以下修改下拉单选控件，下拉复选控件和日期控件的属性
	$(".fr-trigger-text.fr-border-box").css({"background":"rgba(0,0,0,0)"});//设置控件本身背景
	$(".fr-trigger-text").find("input").css({"background":"rgba(0,0,0,0)","color":"#c0c0c0"});//设置控件输入框背景和字体颜色
	$(".fr-trigger-text").css("border","none");//去除边框
      $(".fr-trigger-btn-up").css({"background":"rgba(0,0,0,0)","border":"none"});//设置控件右侧点击按钮	
},100)
	]]></Content>
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
<FRFont name="宋体" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Background name="ColorBackground" color="-16764058"/>
<Alpha alpha="1.0"/>
</Border>
<Background name="ColorBackground" color="-16764058"/>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ComboBox">
<WidgetName name="comboBox1"/>
<WidgetID widgetID="67c1cc2c-7bee-4ef9-8155-9a81b7010360"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="comboBox1" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<widgetValue>
<O>
<![CDATA[默认值]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="44" y="77" width="95" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.NumberEditor">
<WidgetName name="numberEditor0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="numberEditor0" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<NumberAttr>
<widgetValue>
<O t="D">
<![CDATA[123456.0]]></O>
</widgetValue>
</NumberAttr>
</InnerWidget>
<BoundsAttr x="164" y="78" width="50" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.FreeButton">
<WidgetName name="button0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="button0" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Text>
<![CDATA[button1]]></Text>
<initial>
<Background name="ColorBackground"/>
</initial>
<FRFont name="SimSun" style="0" size="72" foreground="-4144960"/>
<isCustomType isCustomType="true"/>
</InnerWidget>
<BoundsAttr x="164" y="125" width="50" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.TextEditor">
<WidgetName name="textEditor0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<TextAttr/>
<Reg class="com.fr.form.ui.reg.NoneReg"/>
<widgetValue>
<O>
<![CDATA[默认值]]></O>
</widgetValue>
<MobileScanCodeAttr scanCode="true" textInputMode="0"/>
</InnerWidget>
<BoundsAttr x="164" y="36" width="50" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.DateEditor">
<WidgetName name="dateEditor0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="dateEditor0" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<DirectEdit>
<![CDATA[false]]></DirectEdit>
<DateAttr/>
<widgetValue>
<O t="Date">
<![CDATA[1587312000000]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="44" y="125" width="95" height="21"/>
</Widget>
<Widget class="com.fr.form.ui.container.WAbsoluteLayout$BoundsWidget">
<InnerWidget class="com.fr.form.ui.ComboBox">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[null]]></Content>
</JavaScript>
</Listener>
<WidgetName name="comboBox0"/>
<WidgetAttr description="">
<MobileBookMark useBookMark="false" bookMarkName="comboBox0" frozen="false"/>
<PrivilegeControl/>
</WidgetAttr>
<Dictionary class="com.fr.data.impl.DatabaseDictionary">
<FormulaDictAttr ki="1" vi="-1"/>
<DBDictAttr tableName="" schemaName="" ki="1" vi="-1" kiName="" viName=""/>
<Connection class="com.fr.data.impl.JDBCDatabaseConnection">
<DatabaseAttr creator="designer"/>
<JDBCDatabaseAttr url="" driver="sun.jdbc.odbc.JdbcOdbcDriver" auth="com.fr.data.auth.normal.NormalAuthentication" user="" password="___" encryptPassword="true">
<DBCPAttr/>
</JDBCDatabaseAttr>
</Connection>
</Dictionary>
<widgetValue>
<O>
<![CDATA[默认值]]></O>
</widgetValue>
</InnerWidget>
<BoundsAttr x="44" y="36" width="95" height="21"/>
</Widget>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList>
<Widget widgetName="comboBox0"/>
<Widget widgetName="textEditor0"/>
<Widget widgetName="comboBox1"/>
<Widget widgetName="numberEditor0"/>
<Widget widgetName="dateEditor0"/>
<Widget widgetName="button0"/>
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
<WatermarkAttr class="com.fr.base.iofile.attr.WatermarkAttr">
<WatermarkAttr fontSize="20" color="-6710887" horizontalGap="200" verticalGap="100" valid="false">
<Text>
<![CDATA[]]></Text>
</WatermarkAttr>
</WatermarkAttr>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="bff8e939-8bcb-4259-97ca-14636e73d473"/>
</TemplateIdAttMark>
</Form>
