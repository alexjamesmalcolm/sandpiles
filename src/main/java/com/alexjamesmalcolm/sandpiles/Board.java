package com.alexjamesmalcolm.sandpiles;

public class Board {

	private int width;
	private int height;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTile(int i, int j) {
		return new Tile(0);
	}

	public void generate(int i) {
	}

}
