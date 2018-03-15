package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BoardTest {
	
	@Test
	public void shouldHaveWidthOfTenTiles() {
		int width = 10;
		Board underTest = new Board(width);
		int actualWidth = underTest.getWidth();
		assertThat(actualWidth, is(width));
	}
	
	@Test
	public void shouldHaveWidthOfElevenTiles() {
		Board underTest = new Board(11);
		assertThat(underTest.getWidth(), is(11));
	}
}
