package com.alexjamesmalcolm.sandpiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Board {

	private int width;
	private int height;
	private List<List<Tile>> board;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		generate(0);
	}

	Board(long id) {
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTile(int x, int y) {
		List<Tile> column = board.get(x);
		Tile tile = column.get(y);
		return tile;
	}

	public void generate(int sand) {
		board = new ArrayList<>();
		for (int x = 0; x < width; x++) {
			List<Tile> column = new ArrayList<Tile>();
			for (int y = 0; y < height; y++) {
				column.add(y, new Tile(sand));
			}
			board.add(x, column);
		}
	}

	public void setTile(int x, int y, Tile tile) {
		List<Tile> column = board.get(x);
		column.set(y, tile);
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
				if (tile.equals(check)) {
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
				if (tile.equals(check)) {
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
		if(needsToppling()) {
			topple();
		}
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

	public Collection<Tile> getUnstableTiles() {
		Collection<Tile> tiles = getTiles();
		tiles.removeIf(tile -> !tile.isUnstable());
		return tiles;
	}

	public long getId() {
		return 1;
	}

}
