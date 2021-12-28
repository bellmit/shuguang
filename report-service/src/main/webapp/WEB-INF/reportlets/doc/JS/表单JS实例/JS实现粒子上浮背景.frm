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
<ShowBookmarks showBookmarks="true"/>
<Center class="com.fr.form.ui.container.WFitLayout">
<Listener event="afterinit">
<JavaScript class="com.fr.js.JavaScriptImpl">
<Parameters/>
<Content>
<![CDATA[$("body").prepend('<canvas id="canvas" style="position:absolute;z-index:-2;"></canvas>');
//在body中插入canvas画布，并置于底层
var _createClass = function () {function defineProperties(target, props) {for (var i = 0; i < props.length; i++) {var descriptor = props[i]A;descriptor.enumerable = descriptor.enumerable || false;descriptor.configurable = true;if ("value" in descriptor) descriptor.writable = true;Object.defineProperty(target, descriptor.key, descriptor);}}return function (Constructor, protoProps, staticProps) {if (protoProps) defineProperties(Constructor.prototype, protoProps);if (staticProps) defineProperties(Constructor, staticProps);return Constructor;};}();function _classCallCheck(instance, Constructor) {if (!(instance instanceof Constructor)) {throw new TypeError("Cannot call a class as a function");}}var Line = function () {
  function Line(x, y, size, color, speed, seed, amplitude) {_classCallCheck(this, Line);
    this.x = x;
    this.y = y;
    this.size = size;
    this.color = color;
    this.speed = speed;
    this.seed = seed;
    this.amplitude = amplitude;
    this.i = this.seed;
  }_createClass(Line, [{ key: 'update', value: function update()

    {
      this.y -= this.speed;
      this.i += this.seed;
    } }, { key: 'draw', value: function draw(

    canvas) {
      var x = this.x + Math.sin(this.i) * this.amplitude;

      canvas.ctx.beginPath();
      canvas.ctx.fillStyle = this.color;
      canvas.ctx.shadowColor = this.color;
      canvas.ctx.shadowBlur = 5;
      canvas.ctx.arc(x, this.y, this.size, 0, 2 * Math.PI);
      canvas.ctx.fill();
      canvas.ctx.closePath();
    } }]A);return Line;}();var


Canvas = function () {
  function Canvas(ctx, w, h) {_classCallCheck(this, Canvas);
    this.ctx = ctx;
    this.width = w;
    this.height = h;
    this.scale = 1.0;
    this.lines = []A;
  }_createClass(Canvas, [{ key: 'pushParticle', value: function pushParticle()

    {
      var x = Math.random() * this.width;
      var y = this.height + Math.random() * 250;
      var size = 1 + Math.random();

      var g = Math.floor(150 + Math.random() * 50);
      var b = Math.floor(150 + Math.random() * 50);
      var color = 'rgba(120,' + g + ',' + b + ',0.7)';

      var speed = 2 + Math.random() * 1.5;
      var seed = Math.random() / 20;
      var amp = Math.random() * 15;

      this.lines.push(new Line(x, y, size, color, speed, seed, amp));
    } }, { key: 'start', value: function start()

    {
      for (var i = 0; i < 100; i++) {
        this.pushParticle();
      }
    } }, { key: 'update', value: function update()

    {
      for (var i = 0; i < this.lines.length; i++) {
        var line = this.lines[i]A;

        line.update();
      }

      this.lines = this.lines.filter(function (line) {
        return line.y > -2;
      });

      var toAdd = 100 - this.lines.length;

      if (toAdd === 0) {return;}

      for (var _i = 0; _i < toAdd; _i++) {
        this.pushParticle();
      }
    } }, { key: 'draw', value: function draw()

    {
      this.ctx.shadowColor = '#000';
      this.ctx.shadowBlur = 0;
      this.ctx.globalCompositeOperation = 'source-over';
      this.ctx.fillStyle = 'rgba(0, 0, 0, 0.05)';
      this.ctx.fillRect(0, 0, this.width, this.height);
      this.ctx.globalCompositeOperation = 'lighter';

      for (var i = 0; i < this.lines.length; i++) {
        var line = this.lines[i]A;

        line.draw(this);
      }
    } }]A);return Canvas;}();


(function (_) {
  var canvasElement = document.getElementById('canvas'),
  ctx = canvasElement.getContext('2d'),
  canvas = new Canvas(ctx);

  var w = canvasElement.width = window.innerWidth,
  h = canvasElement.height = window.innerHeight,
  density = 1;

  function setup() {
    window.addEventListener('resize', resize);

    density = window.devicePixelRatio != undefined ? window.devicePixelRatio : 1.0;

    canvasElement.width = w * density;
    canvasElement.height = h * density;

    canvas.width = w;
    canvas.height = h;
    canvas.scale = density;

    ctx.scale(density, density);

    canvas.start();

    draw();
  }

  function draw() {
    canvas.update();
    canvas.draw();

    window.requestAnimationFrame(draw);
  }

  function resize() {
    w = canvasElement.width = window.innerWidth;
    h = canvasElement.height = window.innerHeight;

    canvasElement.width = w * density;
    canvasElement.height = h * density;

    canvas.width = w;
    canvas.height = h;

    ctx.scale(density, density);
  }

  setup();
})();]]></Content>
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
<TemplateIdAttMark TemplateId="902281a8-f4a7-481b-89c0-127c6618feea"/>
</TemplateIdAttMark>
</Form>
