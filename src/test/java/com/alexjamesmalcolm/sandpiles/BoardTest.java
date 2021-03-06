package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class BoardTest {

	@Test
	public void shouldHaveWidthOfTenTiles() {
		int width = 10;
		Board underTest = new Board(width, 1);
		int actualWidth = underTest.getWidth();
		assertThat(actualWidth, is(width));
	}

	@Test
	public void shouldHaveWidthOfElevenTiles() {
		Board underTest = new Board(11, 1);
		assertThat(underTest.getWidth(), is(11));
	}

	@Test
	public void shouldHaveHeightOfTenTiles() {
		int height = 10;
		Board underTest = new Board(1, height);
		assertThat(underTest.getHeight(), is(height));
	}

	@Test
	public void shouldHaveHeightOfElevenTiles() {
		Board underTest = new Board(1, 11);
		assertThat(underTest.getHeight(), is(11));
	}

	@Test
	public void shouldHaveBoardGenerateEmptyTiles() {
		Board underTest = new Board(2, 2);
		underTest.generate(0);
		assertThat(underTest.getTile(0, 0).getSand(), is(0));
	}

	@Test
	public void shouldHaveBoardGenerateOneSandTiles() {
		Board underTest = new Board(2, 2);
		underTest.generate(1);
		assertThat(underTest.getTile(0, 0).getSand(), is(1));
	}

	@Test
	public void shouldSetTileAtZeroZero() {
		Board underTest = new Board(2, 2);
		underTest.setTile(0, 0, 1);
		assertThat(underTest.getTile(0, 0).getSand(), is(1));
	}

	@Test
	public void shouldGetAdjacentTilesToZeroZeroTile() {
		Board underTest = new Board(2, 2);
		underTest.setTile(0, 0, 1);
		underTest.setTile(0, 1, 2);
		underTest.setTile(1, 0, 3);
		Collection<Tile> tiles = underTest.getAdjacentTiles(0, 0);
		List<Integer> sands = tiles.stream().map(tile -> tile.getSand()).collect(Collectors.toList());
		assertThat(sands, containsInAnyOrder(2, 3));
	}

	@Test
	public void shouldGetAdjacentTilesToOneZeroTile() {
		Board underTest = new Board(2, 2);
		underTest.setTile(1, 0, 1);
		underTest.setTile(1, 1, 2);
		underTest.setTile(0, 0, 3);
		Collection<Tile> tiles = underTest.getAdjacentTiles(1, 0);
		List<Integer> sands = tiles.stream().map(tile -> tile.getSand()).collect(Collectors.toList());
		assertThat(sands, containsInAnyOrder(2, 3));
	}

	@Test
	public void shouldGetAdjacentTilesToOneOneTileInThreeGrid() {
		Board underTest = new Board(3, 3);
		underTest.setTile(1, 1, 1);
		underTest.setTile(1, 0, 2);
		underTest.setTile(1, 2, 3);
		underTest.setTile(0, 1, 4);
		underTest.setTile(2, 1, 5);
		Collection<Tile> tiles = underTest.getAdjacentTiles(1, 1);
		List<Integer> sands = tiles.stream().map(tile -> tile.getSand()).collect(Collectors.toList());
		assertThat(sands, containsInAnyOrder(2, 3, 4, 5));
	}

	@Test
	public void shouldGetAdjacentTilesToOneOneTile() {
		Board underTest = new Board(2, 2);
		underTest.setTile(1, 1, 1);
		underTest.setTile(0, 1, 2);
		underTest.setTile(1, 0, 3);
		Collection<Tile> tiles = underTest.getAdjacentTiles(1, 1);
		List<Integer> sands = tiles.stream().map(tile -> tile.getSand()).collect(Collectors.toList());
		assertThat(sands, containsInAnyOrder(2, 3));
	}

	@Test
	public void shouldGetAdjacentTilesToZeroOne() {
		Board underTest = new Board(2, 2);
		underTest.setTile(0, 1, 1);
		underTest.setTile(1, 1, 2);
		underTest.setTile(0, 0, 3);
		Collection<Tile> tiles = underTest.getAdjacentTiles(0, 1);
		List<Integer> sands = tiles.stream().map(tile -> tile.getSand()).collect(Collectors.toList());
		assertThat(sands, containsInAnyOrder(2, 3));
	}

	@Test
	public void shouldRequireToppling() {
		Board underTest = new Board(2, 2);
		underTest.generate(4);
		boolean isTopplingNeeded = underTest.needsToppling();
		assertThat(isTopplingNeeded, is(true));
	}

	@Test
	public void shouldNotRequireToppling() {
		Board underTest = new Board(2, 2);
		underTest.generate(3);
		boolean actual = underTest.needsToppling();
		assertThat(actual, is(false));
	}

	@Test
	public void shouldRequireTopplingOnOneOne() {
		Board underTest = new Board(2, 2);
		underTest.setTile(1, 1, 4);
		boolean actual = underTest.needsToppling();
		assertThat(actual, is(true));
	}

	@Test
	public void shouldRequireTopplingOnZeroZero() {
		Board underTest = new Board(2, 2);
		underTest.setTile(0, 0, 4);
		boolean actual = underTest.needsToppling();
		assertThat(actual, is(true));
	}

//	@Test
//	public void shouldGetTilesXPositionAsZero() {
//		Board underTest = new Board(2, 2);
//		underTest.setTile(0, 0, tileOne);
//		int position = underTest.findXPosition(tileOne);
//		assertThat(position, is(0));
//	}

//	@Test
//	public void shouldGetTilesXPositionAsOne() {
//		Board underTest = new Board(2, 2);
//		underTest.setTile(1, 0, tileOne);
//		int position = underTest.findXPosition(tileOne);
//		assertThat(position, is(1));
//	}

//	@Test
//	public void shouldGetTilesYPositionAsZero() {
//		Board underTest = new Board(1, 1);
//		underTest.setTile(0, 0, tileOne);
//		int position = underTest.findYPosition(tileOne);
//		assertThat(position, is(0));
//	}

//	@Test
//	public void shouldGetTilesYPositionAsOne() {
//		Board underTest = new Board(2, 2);
//		underTest.setTile(0, 1, tileOne);
//		int position = underTest.findYPosition(tileOne);
//		assertThat(position, is(1));
//	}

	@Test
	public void shouldToppleBoardSoZeroZeroHasNoSand() {
		Board underTest = new Board(2, 2);
		underTest.setTile(0, 0, 4);
		underTest.topple();
		assertThat(underTest.getTile(0, 0).getSand(), is(0));
	}

	@Test
	public void shouldToppleBoardSoOneOneHasNoSand() {
		Board underTest = new Board(2, 2);
		underTest.setTile(1, 1, 4);
		underTest.topple();
		assertThat(underTest.getTile(1, 1).getSand(), is(0));
	}

	@Test
	public void shouldToppleBoardSoZeroZeroGetsZeroOnesSand() {
		Board underTest = new Board(2, 2);
		underTest.setTile(0, 1, 4);
		underTest.topple();
		assertThat(underTest.getTile(0, 0).getSand(), is(1));
	}

	@Test
	public void shouldToppleBoardSoZeroZeroGetsZeroOnesTwoSands() {
		Board underTest = new Board(2, 2);
		underTest.setTile(0, 1, 8);
		underTest.topple();
		assertThat(underTest.getTile(0, 0).getSand(), is(2));
	}

	@Test
	public void shouldChainToppleTheBoard() {
		Board underTest = new Board(2, 2);
		underTest.generate(6);
		underTest.topple();
		assertThat(underTest.needsToppling(), is(false));
	}

//	@Test
//	public void shouldReturnOneTileThatNeedsToppling() {
//		Board underTest = new Board(2, 2);
////		Tile tile = new Tile(4, null);
//		underTest.setTile(0, 0, 4);
//		Collection<Tile> tiles = underTest.getUnstableTiles();
//		assertThat(tiles, contains(tile));
//	}

//	@Test
//	public void shouldReturnTwoTilesThatNeedToppling() {
//		Board underTest = new Board(2, 2);
//		when(tileOne.isUnstable()).thenReturn(true);
//		underTest.setTile(0, 1, tileOne);
//		when(tileTwo.isUnstable()).thenReturn(true);
//		underTest.setTile(1, 0, tileTwo);
//		Collection<Tile> tiles = underTest.getUnstableTiles();
//		assertThat(tiles, containsInAnyOrder(tileOne, tileTwo));
//	}

	@Test
	public void shouldGetIdOne() {
		long id = 1;
		Board underTest = new Board(id);
		assertThat(underTest.getId(), is(id));
	}

	@Test
	public void shouldGetIdTwo() {
		long id = 2;
		Board underTest = new Board(id);
		assertThat(underTest.getId(), is(id));
	}

	@Test
	public void shouldMakeSureBoardsAreEqual() {
		Board firstBoard = new Board(2, 2);
		Board secondBoard = new Board(2, 2);
		assertThat(firstBoard.equals(secondBoard), is(true));
	}

	@Test
	public void shouldMakeSureBoardDoesNotEqualString() {
		Board firstBoard = new Board(2, 2);
		String string = "";
		assertThat(firstBoard.equals(string), is(false));
	}

	@Test
	public void shouldMakeSureBoardDoesNotEqualIfDifferentWidths() {
		Board firstBoard = new Board(3, 2);
		Board secondBoard = new Board(2, 2);
		assertThat(firstBoard.equals(secondBoard), is(false));
	}

	@Test
	public void shouldMakeSureBoardDoesNotEqualIfDifferentHeights() {
		Board firstBoard = new Board(2, 3);
		Board secondBoard = new Board(2, 2);
		assertThat(firstBoard.equals(secondBoard), is(false));
	}

	@Test
	public void shouldMakeSureBoardDoesNotEqualIfContainsDifferentTile() {
		Board firstBoard = new Board(2, 2);
		Board secondBoard = new Board(2, 2);
		firstBoard.setTile(0, 0, 2);
		assertThat(firstBoard.equals(secondBoard), is(false));
	}

	@Test
	public void shouldProperlyToppleAllFours() {
		Board underTest = new Board(3, 3);
		underTest.generate(4);
		underTest.topple();

//		underTest.getTiles().forEach(tile -> {
//			System.out.println(tile.getSand());
//		});

		assertThat(underTest.getTile(0, 0).getSand(), is(0));
		assertThat(underTest.getTile(1, 0).getSand(), is(3));
		assertThat(underTest.getTile(2, 0).getSand(), is(0));
		assertThat(underTest.getTile(0, 1).getSand(), is(3));
		assertThat(underTest.getTile(1, 1).getSand(), is(0));
		assertThat(underTest.getTile(2, 1).getSand(), is(3));
		assertThat(underTest.getTile(0, 2).getSand(), is(0));
		assertThat(underTest.getTile(1, 2).getSand(), is(3));
		assertThat(underTest.getTile(2, 2).getSand(), is(0));
	}
	
	@Test
	public void shouldProperlyToppleAllFives() {
		Board underTest = new Board(2, 2);
		underTest.generate(5);
		underTest.topple();
		Board actual = new Board(2, 2);
		actual.generate(3);
		assertThat(underTest.equals(actual), is(true));
	}
	
	@Test
	public void shouldProperlyToppleAllSixes() {
		Board underTest = new Board(2, 2);
		underTest.generate(6);
		underTest.topple();
		Board check = new Board(2, 2);
		check.generate(2);
		assertThat(underTest.equals(check), is(true));
	}
	
	@Test
	public void shouldProperlyToppleAllTwenties() {
		Board underTest = new Board(2, 2);
		underTest.generate(20);
		underTest.topple();
		Board check = new Board(2, 2);
		check.generate(2);
		assertThat(underTest.equals(check), is(true));
	}
	
	@Test
	public void shouldProperlyToppleOneNine() {
		Board underTest = new Board(3, 3);
		underTest.generate(0);
		underTest.setTile(1, 1, 9);
		underTest.topple();

		underTest.getTiles().forEach(tile -> {
			System.out.println(tile.getSand());
		});

		assertThat(underTest.getTile(0, 0).getSand(), is(0));
		assertThat(underTest.getTile(1, 0).getSand(), is(2));
		assertThat(underTest.getTile(2, 0).getSand(), is(0));
		assertThat(underTest.getTile(0, 1).getSand(), is(2));
		assertThat(underTest.getTile(1, 1).getSand(), is(1));
		assertThat(underTest.getTile(2, 1).getSand(), is(2));
		assertThat(underTest.getTile(0, 2).getSand(), is(0));
		assertThat(underTest.getTile(1, 2).getSand(), is(2));
		assertThat(underTest.getTile(2, 2).getSand(), is(0));
	}
}
