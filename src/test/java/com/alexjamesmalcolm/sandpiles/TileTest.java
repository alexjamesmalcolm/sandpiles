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
}
