const createCanvas = (width, height) => {
	let canvas = document.createElement("canvas");
	canvas.setAttribute("width", width);
	canvas.setAttribute("height", height);
	document.body.append(canvas);
};


const getBoard = (id) => {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = () => {
		if(xhr.readyState == 4 && xhr.status == 200) {
			const response = JSON.parse(xhr.responseText);
			createCanvas(response.width, response.height);
			paintCanvas(response);
		}
	};
	xhr.open("GET", `http://localhost:8080/boards/${id}`, true);
	xhr.send();
};

const paintCanvas = (response) => {
	const zeroColor = "red";
	const oneColor = "blue";
	const twoColor = "yellow";
	const threeColor = "green";
	const fourOrMoreColor = "black";
	const canvas = document.querySelector("canvas");
	const ctx = canvas.getContext("2d");
	for (var i = 0; i < response.tiles.length; i++) {
		const tile = response.tiles[i];
		const x = i % response.width;
		const y = Math.floor(i / response.height);
		if(tile == 0) {
			ctx.fillStyle = zeroColor;
		}
		if(tile == 1) {
			ctx.fillStyle = oneColor;
		}
		if(tile == 2) {
			ctx.fillStyle = twoColor;
		}
		if(tile == 3) {
			ctx.fillStyle = threeColor;
		}
		if(tile > 3) {
			ctx.fillStyle = fourOrMoreColor;
		}
		ctx.fillRect(x, y, 1, 1);
	}
};