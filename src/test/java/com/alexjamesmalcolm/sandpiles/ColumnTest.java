package com.alexjamesmalcolm.sandpiles;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ColumnTest {
	
	@Mock
	Tile tile;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetTile() {
		int height = 1;
		Column underTest = new Column(height);
		int position = 0;
		underTest.setTile(position, tile);
		Assert.assertThat(underTest.getTile(position), Matchers.is(tile));
	}
}
