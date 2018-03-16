package com.alexjamesmalcolm.sandpiles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tile {

	@Id
	@GeneratedValue
	long id;

	@ManyToOne
	Column column;

	private int sand;

	public Tile(int sand) {
		this.sand = sand;
	}

	public int getSand() {
		return sand;
	}

	public boolean isUnstable() {
		if (sand < 4) {
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

	public void addSand(int sand) {
		this.sand += sand;
	}

	public boolean equals(Object obj) {
		if (!obj.getClass().equals(getClass()))
			return false;
		Tile tile = (Tile) obj;
		if (tile.getSand() != getSand())
			return false;
		return true;
	}

}
