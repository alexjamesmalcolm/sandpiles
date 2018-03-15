package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BoardTest {

	@Mock
	Tile tileOne;

	@Mock
	Tile tileTwo;

	@Mock
	Tile tileThree;
	
	@Mock
	Tile tileFour;
	
	@Mock
	Tile tileFive;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

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
		underTest.setTile(0, 0, tileOne);
		assertThat(underTest.getTile(0, 0), is(tileOne));
	}

	@Test
	public void shouldGetAdjacentTilesToZeroZeroTile() {
		Board underTest = new Board(2, 2);
		underTest.setTile(0, 0, tileOne);
		underTest.setTile(0, 1, tileTwo);
		underTest.setTile(1, 0, tileThree);
		Collection<Tile> tiles = underTest.getAdjacentTiles(0, 0);
		assertThat(tiles, containsInAnyOrder(tileTwo, tileThree));
	}

	@Test
	public void shouldGetAdjacentTilesToOneZeroTile() {
		Board underTest = new Board(2, 2);
		underTest.setTile(1, 0, tileOne);
		underTest.setTile(1, 1, tileTwo);
		underTest.setTile(0, 0, tileThree);
		Collection<Tile> tiles = underTest.getAdjacentTiles(1, 0);
		assertThat(tiles, containsInAnyOrder(tileTwo, tileThree));
	}
	
	@Test
	public void shouldGetAdjacentTilesToOneOneTileInThreeGrid() {
		Board underTest = new Board(3,3);
		underTest.setTile(1, 1, tileOne);
		underTest.setTile(1, 0, tileTwo);
		underTest.setTile(1, 2, tileThree);
		underTest.setTile(0, 1, tileFour);
		underTest.setTile(2, 1, tileFive);
		Collection<Tile> tiles = underTest.getAdjacentTiles(1, 1);
		assertThat(tiles, containsInAnyOrder(tileTwo, tileThree, tileFour, tileFive));
	}
	
	@Test
	public void shouldGetAdjacentTilesToOneOneTile() {
		Board underTest = new Board(2,2);
		underTest.setTile(1, 1, tileOne);
		underTest.setTile(0, 1, tileTwo);
		underTest.setTile(1, 0, tileThree);
		Collection<Tile> tiles = underTest.getAdjacentTiles(1, 1);
		assertThat(tiles, containsInAnyOrder(tileTwo, tileThree));
	}
}
