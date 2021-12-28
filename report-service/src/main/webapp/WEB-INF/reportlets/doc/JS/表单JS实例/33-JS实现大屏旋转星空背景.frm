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
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[$("body").prepend('<canvas id="canvas" style="position:absolute;z-index:-2;"></canvas>');

var canvas = document.getElementById('canvas'),

	ctx = canvas.getContext('2d'),

	w = canvas.width = window.innerWidth,

	h = canvas.height = window.innerHeight,



	hue = 217,

	stars = []A,

	count = 0,

	maxStars = 1100; //星星数量,默认1300

var canvas2 = document.createElement('canvas'),

	ctx2 = canvas2.getContext('2d');

canvas2.width = 100;

canvas2.height = 100;

var half = canvas2.width / 2,

	gradient2 = ctx2.createRadialGradient(half, half, 0, half, half, half);

gradient2.addColorStop(0.025, '#CCC');

gradient2.addColorStop(0.1, 'hsl(' + hue + ', 61%, 33%)');

gradient2.addColorStop(0.25, 'hsl(' + hue + ', 64%, 6%)');

gradient2.addColorStop(1, 'transparent');



ctx2.fillStyle = gradient2;

ctx2.beginPath();

ctx2.arc(half, half, half, 0, Math.PI * 2);

ctx2.fill();



// End cache

function random(min, max) {

	if (arguments.length < 2) {

		max = min;

		min = 0;

	}



	if (min > max) {

		var hold = max;

		max = min;

		min = hold;

	}



	return Math.floor(Math.random() * (max - min + 1)) + min;

}



function maxOrbit(x, y) {

	var max = Math.max(x, y),

		diameter = Math.round(Math.sqrt(max * max + max * max));

	return diameter / 2;

	//星星移动范围，值越大范围越小，

}



var Star = function() {



	this.orbitRadius = random(maxOrbit(w, h));

	this.radius = random(60, this.orbitRadius) / 10; //星星大小,值越大星星越小,默认8



	this.orbitX = w / 2;

	this.orbitY = h / 2;

	this.timePassed = random(0, maxStars);

	this.speed = random(this.orbitRadius) / 80000; //星星移动速度,值越大越慢,默认5W



	this.alpha = random(2, 10) / 10;



	count++;

	stars[count]A = this;

}



Star.prototype.draw = function() {

	var x = Math.sin(this.timePassed) * this.orbitRadius + this.orbitX,

		y = Math.cos(this.timePassed) * this.orbitRadius + this.orbitY,

		twinkle = random(10);



	if (twinkle === 1 && this.alpha > 0) {

		this.alpha -= 0.05;

	} else if (twinkle === 2 && this.alpha < 1) {

		this.alpha += 0.05;

	}



	ctx.globalAlpha = this.alpha;

	ctx.drawImage(canvas2, x - this.radius / 2, y - this.radius / 2, this.radius, this.radius);

	this.timePassed += this.speed;

}



for (var i = 0; i < maxStars; i++) {

	new Star();

}



function animation() {

	ctx.globalCompositeOperation = 'source-over';

	ctx.globalAlpha = 0.5; //尾巴

	ctx.fillStyle = 'hsla(' + hue + ', 64%, 6%, 2)';

	ctx.fillRect(0, 0, w, h)



	ctx.globalCompositeOperation = 'lighter';

	for (var i = 1,

			l = stars.length; i < l; i++) {

		stars[i]A.draw();

	};



	window.requestAnimationFrame(animation);

}



animation();]]></Content>
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
<Sorted sorted="false"/>
<MobileWidgetList/>
<FrozenWidgets/>
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
<TemplateIdAttMark TemplateId="5f4bd6fd-1700-4777-beb9-8c7e6e9ece48"/>
</TemplateIdAttMark>
</Form>
