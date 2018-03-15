package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TileTest {
	
	@Test
	public void shouldHaveSandAmountOne() {
		int sand = 1;
		Tile underTest = new Tile(sand);
		assertThat(underTest.getSand(), is(sand));
	}
}
