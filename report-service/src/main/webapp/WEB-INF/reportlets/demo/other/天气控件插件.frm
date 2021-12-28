<?xml version="1.0" encoding="UTF-8"?>
<Form xmlVersion="20170720" releaseVersion="10.0.0">
<FormMobileAttr>
<FormMobileAttr refresh="false" isUseHTML="false" isMobileOnly="false" isAdaptivePropertyAutoMatch="false" appearRefresh="false" promptWhenLeaveWithoutSubmit="false" allowDoubleClickOrZoom="true"/>
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
<InnerWidget class="com.fr.plugin.widget.weather.Weather" pluginID="com.fr.plugin.widget.weather.v10">
<WidgetName name="weather0"/>
<WidgetAttr description="">
<PrivilegeControl/>
</WidgetAttr>
<TextFormatT class="com.fr.plugin.widget.weather.core.TextFormat" pluginID="com.fr.plugin.widget.weather.v10">
<Attr fontSize="10" color="-16777216" bold="false" italic="false"/>
</TextFormatT>
<TextFormatD class="com.fr.plugin.widget.weather.core.TextFormat" pluginID="com.fr.plugin.widget.weather.v10">
<Attr fontSize="10" color="-16777216" bold="false" italic="false"/>
</TextFormatD>
<Background name="ColorBackground" color="-12064022"/>
<margin>
<![CDATA[[0,0,0,0]A]]></margin>
<font>
<![CDATA[宋体]]></font>
<style>
<![CDATA[2]]></style>
<subBackground name="ColorBackground" color="-12064022"/>
<cityInfo class="com.fr.plugin.widget.weather.core.CityInfo" pluginID="com.fr.plugin.widget.weather.v10">
<Attr cityName="北京" cityCode="WX4FBXXFKE4F"/>
</cityInfo>
<radius>
<![CDATA[true]]></radius>
</InnerWidget>
<BoundsAttr x="0" y="0" width="361" height="300"/>
</Widget>
<Sorted sorted="true"/>
<MobileWidgetList>
<Widget widgetName="weather0"/>
</MobileWidgetList>
<FrozenWidgets/>
<WidgetScalingAttr compState="1"/>
<DesignResolution absoluteResolutionScaleW="1920" absoluteResolutionScaleH="1080"/>
<AppRelayout appRelayout="true"/>
</InnerWidget>
<BoundsAttr x="0" y="0" width="650" height="460"/>
</Widget>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="650" height="460"/>
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
<TemplateIdAttMark TemplateId="02a6d567-ea25-4f6e-9cc6-3b34489d9c88"/>
</TemplateIdAttMark>
</Form>
