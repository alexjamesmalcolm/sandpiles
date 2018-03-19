package com.alexjamesmalcolm.sandpiles;

import static javax.persistence.CascadeType.ALL;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Board {

	private int width;
	private int height;
	@OneToMany(cascade = ALL, orphanRemoval = true, mappedBy = "board")
	private List<Column> columns;
	@Id
	@GeneratedValue
	private long id;

	@SuppressWarnings("unused")
	private Board() {
	}

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		generate(0);
	}

	Board(long id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTile(int x, int y) {
		Column column = columns.get(x);
		Tile tile = column.getTile(y);
		return tile;
	}

	public void generate(int sand) {
		columns = new ArrayList<>();
		for (int x = 0; x < width; x++) {
			Column column = new Column(height, sand, this);
			columns.add(x, column);
		}
	}

	public void setTile(int x, int y, int sand) {
		Column column = columns.get(x);
		Tile tile = column.getTile(y);
		tile.setSand(sand);
	}

	public Collection<Tile> getAdjacentTiles(int x, int y) {
		Collection<Tile> tiles = new ArrayList<Tile>();
		if (y + 1 < height) {
			tiles.add(getTile(x, y + 1));
		}
		if (x + 1 < width) {
			tiles.add(getTile(x + 1, y));
		}
		if (x > 0) {
			tiles.add(getTile(x - 1, y));
		}
		if (y > 0) {
			tiles.add(getTile(x, y - 1));
		}
		return tiles;
	}

	public boolean needsToppling() {
		Collection<Tile> tiles = getTiles();
		return tiles.stream().anyMatch(tile -> tile.isUnstable());
	}

	public int findXPosition(Tile tile) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Tile check = getTile(x, y);
				if (tile.hashCode() == check.hashCode()) {
					return x;
				}
			}
		}
		return -1;
	}

	public int findYPosition(Tile tile) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Tile check = getTile(x, y);
				if (tile.hashCode() == check.hashCode()) {
					return y;
				}
			}
		}
		return -1;
	}

	public void topple() {
		Collection<Tile> tiles = getUnstableTiles();
		tiles.forEach(tile -> {
			if (tile.isUnstable()) {
				int x = findXPosition(tile);
				int y = findYPosition(tile);
				int sandToAdd = tile.getNumOfTopples();
				Collection<Tile> adjacentTiles = getAdjacentTiles(x, y);
				adjacentTiles.forEach(adjacentTile -> adjacentTile.addSand(sandToAdd));
				tile.topple();
			}
		});
		if (needsToppling()) {
			topple();
		}
//		System.out.println(tiles.size());
	}

	public Collection<Tile> getTiles() {
		Collection<Tile> result = new ArrayList<>();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Tile tile = getTile(x, y);
				result.add(tile);
			}
		}
		return result;
	}

	@JsonIgnore
	public Collection<Tile> getUnstableTiles() {
		Collection<Tile> tiles = getTiles();
		tiles.removeIf(tile -> !tile.isUnstable());
		return tiles;
	}

	public long getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (!obj.getClass().equals(this.getClass())) {
			return false;
		}
		Board board = (Board) obj;
		if (board.getWidth() != getWidth()) {
			return false;
		}
		if (board.getHeight() != getHeight()) {
			return false;
		}
		List<Tile> otherTiles = (List<Tile>) board.getTiles();
		List<Tile> tiles = (List<Tile>) getTiles();
		for (int i = 0; i < tiles.size(); i++) {
			Tile otherTile = otherTiles.get(i);
			Tile tile = tiles.get(i);
			if (tile.getSand() != otherTile.getSand()) {
				return false;
			}
		}
		return true;
	}
}
