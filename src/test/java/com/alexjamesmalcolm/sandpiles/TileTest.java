package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class TileTest {

	@Test
	public void shouldHaveSandAmountOne() {
		int sand = 1;
		Tile underTest = new Tile(sand, null);
		assertThat(underTest.getSand(), is(sand));
	}
	
	@Test
	public void shouldHaveSandAmountTwo() {
		Tile underTest = new Tile(2, null);
		assertThat(underTest.getSand(), is(2));
	}
	
	@Test
	public void shouldBeUnstableWhenSandIsFour() {
		Tile underTest = new Tile(4, null);
		assertThat(underTest.isUnstable(), is(true));
	}
	
	@Test
	public void shouldBeStableWhenSandIsThree() {
		Tile underTest = new Tile(3, null);
		assertThat(underTest.isUnstable(), is(false));
	}
	
	@Test
	public void shouldHaveOneToppleWhenSandIsFour() {
		Tile underTest = new Tile(4, null);
		assertThat(underTest.getNumOfTopples(), is(1));
	}
	
	@Test
	public void shouldHaveTwoToppleWhenSandIsEight() {
		Tile underTest = new Tile(8, null);
		assertThat(underTest.getNumOfTopples(), is(2));
	}
	
	@Test
	public void shouldHaveOneToppleWhenSandIsSeven() {
		Tile underTest = new Tile(7, null);
		assertThat(underTest.getNumOfTopples(), is(1));
	}
	
	@Test
	public void shouldHaveThreeToppleWhenSandIsTwelve() {
		Tile underTest = new Tile(12, null);
		assertThat(underTest.getNumOfTopples(), is(3));
	}
	
	@Test
	public void shouldHaveToppleMakeSandZeroWhenSandIsFour() {
		Tile underTest = new Tile(4, null);
		underTest.topple();
		assertThat(underTest.getSand(), is(0));
	}
	
	@Test
	public void shouldHaveToppleNotChangeSandWhenSandIsThree() {
		Tile underTest = new Tile(3, null);
		underTest.topple();
		assertThat(underTest.getSand(), is(3));
	}
	
	@Test
	public void shouldAddOneSandToTileWithTwo() {
		Tile underTest = new Tile(2, null);
		underTest.addSand(1);
		assertThat(underTest.getSand(), is(3));
	}
	
	@Test
	public void shouldHaveTwoTilesEqualEachOther() {
		Tile firstTile = new Tile(0, null);
		Tile secondTile = new Tile(0, null);
		assertThat(firstTile.equals(secondTile), is(true));
	}
	
	@Test
	public void shouldHaveTileNotEqualString() {
		Tile underTest = new Tile(0, null);
		String string = "";
		assertThat(underTest.equals(string), is(false));
	}
	
	@Test
	public void shouldHaveTileWithDifferentSandNotEqual() {
		Tile firstTile = new Tile(0, null);
		Tile secondTile = new Tile(1, null);
		assertThat(firstTile.equals(secondTile), is(false));
	}
}
