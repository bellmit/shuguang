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
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[$("body").prepend('<canvas id="mycanvas" style="position:absolute;z-index:-2; background-color:black;"></canvas>');
var canvas = mycanvas; 
var w = window.innerWidth;
var h = window.innerHeight;
canvas.width = w;
canvas.height = h;
canvas.backgroundColor = "#000";
var ctx = canvas.getContext('2d');
 
function Build() {
    this.ctx = ctx;
    this.counts = 3000;  
    this.maxSize = 4;  
    this.halfWidth = w / 2,
        this.halfHeight = h / 2;
    this.arr = []A;  
}
Build.prototype.add = function(coor) {
    var grd = this.ctx.createRadialGradient(coor.x, coor.y, coor.size / 2, coor.x, coor.y, coor.size);
    grd.addColorStop(0, "white");
    grd.addColorStop(1, coor.color);
    this.ctx.fillStyle = grd;
    this.ctx.beginPath();
    this.ctx.arc(coor.x, coor.y, coor.size, 0, Math.PI * 2, true);
    this.ctx.transform(1, 0, 0, 1, 0, coor.z);
    this.ctx.closePath();
    this.ctx.fill();
}
Build.prototype.init = function() {
    this.run();
    this.render();
    this.animate();
}
Build.prototype.run = function() {
    var nums = 0;
    while (nums < this.counts) {
        var coor = {
            x: Math.ceil(Math.random() * w),
            y: Math.ceil(Math.random() * h),
            posx: Math.random() * w - this.halfWidth,
            posy: Math.random() * h - this.halfHeight,
            fl: 100,
            speed: Math.random() * 2,
            posz: Math.random() * 250,
            r: Math.ceil(Math.random() * this.maxSize),
            color: "rgba(" + Math.ceil(Math.random() * 255) + "," + Math.ceil(Math.random() * 255) + "," + Math.ceil(Math.random() * 255) + "," + Math.random() + ")"
        };
        this.arr.push(coor);
        nums++;
    }
}
Build.prototype.clear = function() {
    ctx.clearRect(0, 0, w, h);
}
Build.prototype.render = function() {
    this.clear();
    for (var item of this.arr) {
        this.draw(item);
    }
}
Build.prototype.animate = function() {
        var _this = this;
        this.render(); 
        window.requestAnimationFrame(function() {
            _this.animate();
        });
    },
    Build.prototype.draw = function(item) {
        if (item.posz > -item.fl) { 
            var scale = item.fl / (item.fl + item.posz); 
            item.x = this.halfWidth + item.posx * scale;
            item.y = this.halfHeight + item.posy * scale;
            item.size = item.r * scale;
            item.posz -= item.speed;
        } else { 
            item.posz = Math.random() * 250;
        }
        this.add(item);
    }
var app = new Build();
app.init();
window.addEventListener('resize', function() {
    canvas.width = w = window.innerWidth;
    canvas.height = h = window.innerHeight;
}, false); ]]></Content>
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
<FRFont name="SimSun" style="0" size="72"/>
<Position pos="0"/>
</WidgetTitle>
<Alpha alpha="1.0"/>
</Border>
<LCAttr vgap="0" hgap="0" compInterval="0"/>
<ShowBookmarks showBookmarks="true"/>
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
<MobileBookMarkStyle class="com.fr.form.ui.mobile.impl.DefaultMobileBookMarkStyle"/>
<WidgetZoomAttr compState="0"/>
<AppRelayout appRelayout="true"/>
<Size width="960" height="540"/>
<ResolutionScalingAttr percent="1.2"/>
<BodyLayoutType type="0"/>
</Center>
</Layout>
<DesignerVersion DesignerVersion="KAA"/>
<PreviewType PreviewType="0"/>
<TemplateIdAttMark class="com.fr.base.iofile.attr.TemplateIdAttrMark">
<TemplateIdAttMark TemplateId="b7864ac6-5c8c-40bd-8377-aff4ba54f2aa"/>
</TemplateIdAttMark>
</Form>
