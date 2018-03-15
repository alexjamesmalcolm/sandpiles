package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
}
