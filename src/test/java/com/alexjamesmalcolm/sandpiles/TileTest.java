package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class TileTest {

	@Test
	public void shouldHaveSandAmountOne() {
		int sand = 1;
		Tile underTest = new Tile(sand);
		assertThat(underTest.getSand(), is(sand));
	}
	
	@Test
	public void shouldHaveSandAmountTwo() {
		Tile underTest = new Tile(2);
		assertThat(underTest.getSand(), is(2));
	}
	
	@Test
	public void shouldBeUnstableWhenSandIsFour() {
		Tile underTest = new Tile(4);
		assertThat(underTest.isUnstable(), is(true));
	}
	
	@Test
	public void shouldBeStableWhenSandIsThree() {
		Tile underTest = new Tile(3);
		assertThat(underTest.isUnstable(), is(false));
	}
	
	@Test
	public void shouldHaveOneToppleWhenSandIsFour() {
		Tile underTest = new Tile(4);
		assertThat(underTest.getNumOfTopples(), is(1));
	}
	
	@Test
	public void shouldHaveTwoToppleWhenSandIsEight() {
		Tile underTest = new Tile(8);
		assertThat(underTest.getNumOfTopples(), is(2));
	}
	
	@Test
	public void shouldHaveOneToppleWhenSandIsSeven() {
		Tile underTest = new Tile(7);
		assertThat(underTest.getNumOfTopples(), is(1));
	}
	
	@Test
	public void shouldHaveThreeToppleWhenSandIsTwelve() {
		Tile underTest = new Tile(12);
		assertThat(underTest.getNumOfTopples(), is(3));
	}
}
