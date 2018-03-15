package com.alexjamesmalcolm.sandpiles;

import java.util.ArrayList;
import java.util.List;

public class Column {

	List<Tile> tiles = new ArrayList<>();
	private int height;

	public Column(int height) {
		this.height = height;
		tiles = new ArrayList<>(height);
		for(int i = 0; i < height; i++) {
			tiles.add(null);
		}
	}

	public Tile getTile(int position) {
		return tiles.get(position);
	}

	public void setTile(int position, Tile tile) {
		tiles.set(position, tile);
	}

	public int getHeight() {
		return height;
	}
}
