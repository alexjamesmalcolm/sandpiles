package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SandpilesControllerTest {
	
	/*
	 * TODO Controller should respond to: GET, DELETE, POST, and maybe PATCH
	 * */
	
	@InjectMocks
	private SandpilesController underTest;
	
	@Mock
	Board board;
	
	@Mock
	BoardRepository boardRepo;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldGetBoard() {
		long boardId = 1L;
		when(board.getId()).thenReturn(boardId);
		when(boardRepo.findOne(boardId)).thenReturn(board);
		
		Board actual = underTest.getBoard(boardId);
		assertThat(actual, is(board));
	}
}
