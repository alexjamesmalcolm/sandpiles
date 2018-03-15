package com.alexjamesmalcolm.sandpiles;

public class Column {

	Tile tile;
	private int height;

	public Column(int height) {
		this.height = height;
	}

	public Tile getTile(int position) {
		return tile;
	}

	public void setTile(int position, Tile tile) {
		this.tile = tile;
	}

	public int getHeight() {
		return height;
	}
}
