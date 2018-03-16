package com.alexjamesmalcolm.sandpiles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

@Entity
public class Tile {

	@Id
	@GeneratedValue
	long id;

	@ManyToOne
	Column column;

	private int sand;

	@SuppressWarnings("unused")
	private Tile() {}
	
	public Tile(int sand, Column column) {
		this.sand = sand;
		this.column = column;
	}

	@JsonValue
	public int getSand() {
		return sand;
	}

	@JsonIgnore
	public boolean isUnstable() {
		if (sand < 4) {
			return false;
		}
		return true;
	}

	@JsonIgnore
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
