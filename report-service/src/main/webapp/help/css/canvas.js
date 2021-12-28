function WaveWater() {
	this.isDrawContainer = false;
	
	this.componentDidMount = function(ctx,options)
	{
		this.draw = this.draw.bind(this);
		const canvas = ctx;
		this.canvas = canvas;
		this.canvasWidth = canvas.width;
		this.canvasHeight = canvas.height;
		this.radius = this.canvasWidth / 2;
		this.options = options;
		// 高清适配
		Retina(canvas);
		this.nowRange = 0;
		this.rangeValue = (this.options.minVal == 0) ? 0 : (this.options.minVal / this.options.maxVal) * 100;
		this.wave1 = new Wave({
			canvasWidth: this.canvasWidth, // 轴长
			canvasHeight: this.canvasHeight, // 轴高
			waveWidth: 0.055, // 波浪宽度,数越小越宽
			waveHeight: this.canvasWidth / 50, // 波浪高度,数越大越高
			colors: this.options.aColor, // 波浪颜色
			xOffset: 0, // 初始偏移
			speed: 0.04, // 速度
		});
		this.wave2 = new Wave({
			canvasWidth: this.canvasWidth, // 轴长
			canvasHeight: this.canvasHeight, // 轴高
			waveWidth: 0.04, // 波浪宽度,数越小越宽
			waveHeight: this.canvasWidth / 50, // 波浪高度,数越大越高
			colors: this.options.bColor, // 波浪颜色
			xOffset: 2, // 初始偏移
			speed: 0.02, // 速度
		});
		this.draw();
	}
	
	this.draw = function()
	{
		const ctx = this.canvas.getContext('2d');
		ctx.clearRect(0, 0, this.canvasWidth, this.canvasHeight);
		if (!this.isDrawContainer) {
			this.drawCircle(ctx,this.canvasWidth/2,this.canvasHeight/2,this.canvasHeight / 2 - 4,4,this.options.lineColor);
			this.drawCircle(ctx,this.canvasWidth/2,this.canvasHeight/2,this.canvasHeight / 2 - 10,6,'rgba(0,0,0,0)');
		}
		// this.drawBackground(ctx);
		if (this.nowRange <= this.rangeValue) {
			this.nowRange += 3;
		}
		if (this.nowRange > this.rangeValue) {
			this.nowRange -= 3;
		}
		this.wave2.update({
			nowRange: this.nowRange,
		});
		this.wave2.draw(ctx);
		this.wave1.update({
			nowRange: this.nowRange,
		});
		this.wave1.draw(ctx);
		this.writeWord(ctx,this.rangeValue.toFixed(2) + "%",this.canvasWidth/2,this.canvasHeight/2+10,
			this.options.wordColor ? this.options.wordColor : "#000000",
			this.options.wordStyle?this.options.wordStyle:"18px bold 微软雅黑");
		this.writeWord(ctx,this.options.name,this.canvasWidth/2,this.canvasHeight/2-20,
			this.options.nameColor ? this.options.nameColor : "#898989",
			this.options.nameStyle ? this.options.nameStyle : "12px normal 微软雅黑");
		window.requestAnimationFrame(this.draw);
	}
	
	this.drawCircle = function(ctx,px,py,r,lw,color)
	{
		const lineWidth = lw;
		const cR = r - (lineWidth);
		ctx.lineWidth = lineWidth;
		ctx.beginPath();
		ctx.arc(px,py, cR, 0, 2 * Math.PI);
		ctx.strokeStyle = color;
		ctx.stroke();
		ctx.clip();
		this.isDrawContainer = true;
	}
	
	this.writeWord = function (context,word,x,y,color,style) {
		context.beginPath();
		// 设置字体
		context.font = style;
		// 设置颜色
		context.fillStyle = color;
		// 设置水平对齐方式
		context.textAlign = "center";
		// 设置垂直对齐方式
		context.textBaseline = "middle";
		// 绘制文字（参数：要写的字，x坐标，y坐标）
		context.fillText(word, x, y);
	}
}

function Retina(canvasEl) {
	const canvas = canvasEl;
	const ctx = canvas.getContext('2d');
	const devicePixelRatio = window.devicePixelRatio || 1;
	const backingStorePixelRatio = ctx.webkitBackingStorePixelRatio ||
		ctx.mozBackingStorePixelRatio ||
		ctx.msBackingStorePixelRatio ||
		ctx.oBackingStorePixelRatio ||
		ctx.backingStorePixelRatio || 1;
	
	const ratio = devicePixelRatio / backingStorePixelRatio;
	if (devicePixelRatio !== backingStorePixelRatio) {
		const oldWidth = canvas.width;
		const oldHeight = canvas.height;
		
		canvas.width = oldWidth * ratio;
		canvas.height = oldHeight * ratio;
		
		canvas.style.width = `${oldWidth}px`;
		canvas.style.height = `${oldHeight}px`;
		ctx.scale(ratio, ratio);
	}
}

function Wave(options) {
	this.points = options.points ? options.points : [];
	this.startX = 0;
	this.canvasWidth = options.canvasWidth ? options.canvasWidth : 0; // 轴长
	this.canvasHeight = options.canvasHeight ? options.canvasHeight : 0;// 轴高
	this.waveWidth = options.waveWidth ? options.waveWidth : 0.055;// 波浪宽度,数越小越宽
	this.waveHeight = options.waveHeight ? options.waveHeight : 6; // 波浪高度,数越大越高
	this.xOffset = 0;
	this.speed = options.speed ? options.speed : 0.04;
	this.colors = options.colors ? options.colors : ['#DBB77A', '#BF8F3B']; // 波浪颜色
	
	this.getChartColor = function (ctx) {
		const radius = this.canvasWidth / 2;
		const grd = ctx.createLinearGradient(radius, radius, radius, this.canvasHeight);
		grd.addColorStop(0, this.colors[0]);
		grd.addColorStop(1, this.colors[1]);
		return grd;
	}
	
	this.draw = function(ctx) {
		ctx.save();
		const points = this.points;
		ctx.beginPath();
		for (let i = 0; i < points.length; i += 1) {
			const point = points[i];
			ctx.lineTo(point[0], point[1]);
		}
		ctx.lineTo(this.canvasWidth, this.canvasHeight);
		ctx.lineTo(this.startX, this.canvasHeight);
		ctx.lineTo(points[0][0], points[0][1]);
		ctx.fillStyle = this.getChartColor(ctx);
		ctx.fill();
		ctx.restore();
	}
	
	this.update = function({
							   nowRange,
						   } = {}) {
		this.points = [];
		const {
			startX, waveHeight, waveWidth, canvasWidth, canvasHeight, xOffset,
		} = this;
		for (let x = startX; x < startX + canvasWidth; x += 20 / canvasWidth) {
			const y = Math.sin(((startX + x) * waveWidth) + xOffset);
			const dY = canvasHeight * (1 - (nowRange / 100));
			this.points.push([x, dY + (y * waveHeight)]);
		}
		this.xOffset += this.speed;
	}
}

var waters = [];

function runcanvas(options) {
	var timer = setInterval(function () {
		var obj = $("div[widgetname='"+options.absolute+"']").eq(0);
		if (obj.length <= 0) {
			return false;
		}
		
		var w = obj.width();
		var h = obj.height();
		
		$("div[widgetname='"+options.absolute+"']").find(".line-canvas").remove();
		var newCanvas = document.createElement("canvas");
		newCanvas.setAttribute("width", w + "px");
		newCanvas.setAttribute("height", h + "px");
		newCanvas.className = "line-canvas";
		obj.append(newCanvas);
		
		clearInterval(timer);
		
		var water = new WaveWater();
		water.componentDidMount(newCanvas,options);
		waters[options.absolute] = water;
	}, 100)
}

function updateWaterByName(name, options) {
	waters[name].rangeValue = (options.minVal == 0) ? 0 : (options.minVal / options.maxVal) * 100;
}