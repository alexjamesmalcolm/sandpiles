package com.alexjamesmalcolm.sandpiles;

public class Tile {

	private int sand;

	public Tile(int sand) {
		this.sand = sand;
	}

	public int getSand() {
		return sand;
	}

	public boolean isUnstable() {
		return true;
	}

}
