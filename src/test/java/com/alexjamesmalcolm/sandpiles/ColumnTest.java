package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ColumnTest {
	
	@Mock
	Tile tileOne;
	
	@Mock
	Tile tileTwo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetTile() {
		int height = 1;
		Column underTest = new Column(height);
		int position = 0;
		underTest.setTile(position, tileOne);
		assertThat(underTest.getTile(position), is(tileOne));
	}
	
	@Test
	public void shouldGetHeightOne() {
		int height = 1;
		Column underTest = new Column(height);
		assertThat(underTest.getHeight(), is(height));
	}
	
	@Test
	public void shouldGetHeightTwo() {
		int height = 2;
		Column underTest = new Column(height);
		assertThat(underTest.getHeight(), is(height));
	}
	
	@Test
	public void shouldGetFirstTile() {
		Column underTest = new Column(2);
		underTest.setTile(0, tileOne);
		underTest.setTile(1, tileTwo);
		assertThat(underTest.getTile(0), is(tileOne));
	}
}
