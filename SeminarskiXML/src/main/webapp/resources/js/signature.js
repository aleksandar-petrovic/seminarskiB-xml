var size = 1.2;

var canvas = document.getElementById('sig')
var ctx = canvas.getContext("2d");
ctx.lineWidth = size;

var mouseIsDown = false;
var lastPosition = null;

canvas.onmousemove = function(e) {
	if (mouseIsDown) {
		var mousePos = getMousePos(canvas, e);
		var x = mousePos.x;
		var y = mousePos.y;

		if (lastPosition == null) {
			lastPosition = new Point(x, y);
			ctx.moveTo(lastPosition.x, lastPosition.y);
		} else
			drawLine(x, y);
	}
}

canvas.onmousedown = function(e) {
	lastPosition = null;
	mouseIsDown = true;
}

canvas.onmouseup = function(e) {
	mouseIsDown = false;
}

canvas.onmouseout = function(e) {
	mouseIsDown = false;
}

function getMousePos(canvas, evt) {
	// mouse rectangle
	var rect = canvas.getBoundingClientRect();
	return {
		x : evt.clientX - rect.left,
		y : evt.clientY - rect.top
	};
}

function drawLine(x, y) {
	ctx.lineTo(x, y);
	ctx.stroke();
	lastPosition = new Point(x, y);
}

function Point(x, y) {
	this.x = x;
	this.y = y;
}

function getImage() {
	return canvas.toDataURL();
}

function putImageData(data) {
	var imageObj = new Image();
	imageObj.onload = function() {
		ctx.drawImage(this, 0, 0);
	};
	imageObj.src = data;
}

function getData() {
	document.getElementById('form:imageURL').value = getImage();
}
