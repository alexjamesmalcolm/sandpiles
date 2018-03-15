package com.alexjamesmalcolm.sandpiles;

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
	@OneToMany(mappedBy = "column")
	List<Tile> tiles;
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
