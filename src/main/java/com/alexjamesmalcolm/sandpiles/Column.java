package com.alexjamesmalcolm.sandpiles;

import static javax.persistence.CascadeType.ALL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Column {

	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	Board board;
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "column")
	List<Tile> tiles;
	private int height;

	@SuppressWarnings("unused")
	private Column() {}
	
	public Column(int height, int sand, Board board) {
		this.height = height;
		this.board = board;
		tiles = new ArrayList<>(height);
		for (int i = 0; i < height; i++) {
			tiles.add(new Tile(sand, this));
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
