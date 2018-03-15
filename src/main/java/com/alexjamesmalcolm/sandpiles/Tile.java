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
		if(sand < 4) {
			return false;
		}
		return true;
	}

	public int getNumOfTopples() {
		return getSand() / 4;
	}

	public void topple() {
		sand = getSand() % 4;
	}

}
