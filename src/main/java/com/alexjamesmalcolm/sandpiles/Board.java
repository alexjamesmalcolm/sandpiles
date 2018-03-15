package com.alexjamesmalcolm.sandpiles;

public class Board {

	private int width;
	private int height;
	private Tile tile;

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
		return tile;
	}

	public void generate(int sand) {
		tile = new Tile(sand);
	}

	public void setTile(int x, int y, Tile tile) {
		this.tile = tile;
	}

}
