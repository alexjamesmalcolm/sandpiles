package com.alexjamesmalcolm.sandpiles;

public class Column {

	Tile tile;

	public Column(int height) {
	}

	public Tile getTile(int position) {
		return tile;
	}

	public void setTile(int position, Tile tile) {
		this.tile = tile;
	}

	public int getHeight() {
		return 1;
	}
}
