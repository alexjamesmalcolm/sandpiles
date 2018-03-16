describe("script.js", () => {
	describe("createCanvas", () => {
		it("should put a canvas in the body with a width and height", () => {
			createCanvas(100, 200);
			const actual = document.querySelector("canvas");
			const canvas = document.createElement("canvas");
			canvas.setAttribute("width", 100);
			canvas.setAttribute("height", 200);
			expect(actual).toEqual(canvas);
		});
	});
});