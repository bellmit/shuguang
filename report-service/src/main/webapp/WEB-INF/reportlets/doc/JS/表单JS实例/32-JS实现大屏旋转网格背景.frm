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

const PHI = (1 + Math.sqrt(5)) / 2, // 1.618033988749895

	maxGeneration = (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) ? 5 : 6,

	frameDuration = 1000 / 60,

	duration = 3000,

	rotationSpeed = 0.3,

	totalIterations = Math.floor(duration / frameDuration),

	maxBaseSize = 100,

	baseSizeSpeed = 0.02;



var canvas = document.getElementById("canvas"),

	ctx = canvas.getContext("2d"),

	canvasWidth = document.documentElement.clientWidth,

	canvasHeight = document.documentElement.clientHeight,

	shapes = []A,

	sizeVariation,

	iteration = 0,

	animationDirection = 1,

	sizeVariationRange = .15,

	baseRotation = 0,

	baseSize = 50,

	c1 = 43,

	c1S = 1,

	c2 = 205,

	c2S = 1,

	c3 = 255,

	c3S = 1;



canvas.setAttribute("width", canvasWidth);

canvas.setAttribute("height", canvasHeight);



function Shape(gen, x, y, size, rotation) {

	this.generation = gen;

	this.size = size;

	this.rotation = -rotation;

	this.start = {

		x: x,

		y: y

	};

	this.end = {

		x_1: this.start.x + Math.cos(degToRad(this.rotation)) * this.size,

		y_1: this.start.y + Math.sin(degToRad(this.rotation)) * this.size,

		x_2: this.start.x + Math.cos(degToRad(this.rotation + 360 / 3)) * this.size,

		y_2: this.start.y + Math.sin(degToRad(this.rotation + 360 / 3)) * this.size,

		x_3:

			this.start.x +

			Math.cos(degToRad(this.rotation + 360 / 3 * 2)) * this.size,

		y_3:

			this.start.y + Math.sin(degToRad(this.rotation + 360 / 3 * 2)) * this.size

	};



	this.init();

}



Shape.prototype.init = function() {

	if (this.generation < maxGeneration) {

		var gen = this.generation + 1,

			newSize = this.size * sizeVariation,

			newRotation = this.rotation;



		shapes.push(

			new Shape(gen, this.end.x_1, this.end.y_1, newSize, newRotation)

		);

		shapes.push(

			new Shape(gen, this.end.x_2, this.end.y_2, newSize, newRotation)

		);

		shapes.push(

			new Shape(gen, this.end.x_3, this.end.y_3, newSize, newRotation)

		);

	}

	this.draw();

};



Shape.prototype.draw = function() {

	ctx.beginPath();

	ctx.moveTo(this.start.x, this.start.y);

	ctx.lineTo(this.end.x_1, this.end.y_1);

	ctx.moveTo(this.start.x, this.start.y);

	ctx.lineTo(this.end.x_2, this.end.y_2);

	ctx.moveTo(this.start.x, this.start.y);

	ctx.lineTo(this.end.x_3, this.end.y_3);

	//ctx.closePath();

	ctx.strokeStyle =

		"rgba(" + c1 + "," + c2 + "," + c3 + "," + 1 / this.generation / 5 + ")";

	ctx.stroke();

	//ctx.fill();

};



function animate() {

	//ctx.clearRect(0, 0, canvasWidth, canvasHeight);

	ctx.globalCompositeOperation = "source-over";

	ctx.fillStyle = "rgba(0,0,0,.1)";

	ctx.fillRect(0, 0, canvasWidth, canvasHeight);

	ctx.globalCompositeOperation = "lighter";

	shapes = []A;

	shapes.push(

		new Shape(0, canvasWidth / 2, canvasHeight / 2, baseSize, baseRotation)

	);



	changeColor();

	iteration++;

	if (baseSize < maxBaseSize) baseSize += baseSizeSpeed;

	baseRotation += rotationSpeed;

	sizeVariation = easeInOutSine(

		iteration,

		1 - sizeVariationRange * animationDirection,

		sizeVariationRange * 2 * animationDirection,

		totalIterations

	);

	if (iteration >= totalIterations) {

		iteration = 0;

		animationDirection *= -1;

	}

	requestAnimationFrame(animate);

}



function degToRad(deg) {

	return Math.PI / 180 * deg;

}



function easeInOutSine(

	currentIteration,

	startValue,

	changeInValue,

	totalIterations

) {

	return (

		changeInValue /

		2 *

		(1 - Math.cos(Math.PI * currentIteration / totalIterations)) +

		startValue

	);

}



function changeColor() {

	if (c1 == 0 || c1 == 255) c1S *= -1;

	if (c2 == 0 || c2 == 255) c2S *= -1;

	if (c3 == 0 || c3 == 255) c3S *= -1;

	c1 += 1 * c1S;

	c2 += 1 * c2S;

	c3 += 1 * c3S;

}



ctx.globalCompositeOperation = "lighter";

animate();



window.addEventListener("resize", function() {

	canvasWidth = document.documentElement.clientWidth;

	canvasHeight = document.documentElement.clientHeight;



	canvas.setAttribute("width", canvasWidth);

	canvas.setAttribute("height", canvasHeight);

	ctx.strokeStyle = "rgba(66,134,240,.3)";

	ctx.globalCompositeOperation = "lighter";

});]]></Content>
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
<TemplateIdAttMark TemplateId="44c51451-af70-48d6-a7d6-61861262c9fc"/>
</TemplateIdAttMark>
</Form>
