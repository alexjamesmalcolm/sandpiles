package com.alexjamesmalcolm.sandpiles;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

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
	long boardId;
	
	@Mock
	Board anotherBoard;
	long anotherBoardId;
	
	@Mock
	BoardRepository boardRepo;
	
	@Mock
	Tile tile;
	
	@Mock
	Tile anotherTile;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		boardId = 1L;
		when(board.getId()).thenReturn(boardId);
		when(boardRepo.findOne(boardId)).thenReturn(board);
		Collection<Board> boards = new ArrayList<>();
		boards.add(board);
		boards.add(anotherBoard);
		when(boardRepo.findAll()).thenReturn(boards);
		
	}
	
	@Test
	public void shouldGetBoard() {
		Board actual = underTest.getBoard(boardId);
		assertThat(actual, is(board));
	}
	
	@Test
	public void shouldDeleteBoard() {
		underTest.deleteBoard(boardId);
		verify(boardRepo).delete(boardId);
	}
	
	@Test
	public void shouldGetBoards() {
		Collection<Board> actual = underTest.getBoards();
		assertThat(actual, containsInAnyOrder(board, anotherBoard));
	}
	
	@Test
	public void shouldToppleBoard() {
		underTest.toppleBoard(boardId);
		verify(board).topple();
	}
	
	@Test
	public void shouldSetTile() {
		underTest.setTile(boardId, 1, 2, tile);
		verify(board).setTile(1, 2, tile);
	}
	
	@Test
	public void shouldMakeBoardWithWidthTwoAndHeightThree() {
		int width = 2;
		int height = 3;
//		boolean topple = true;
		underTest.makeBoard(width, height);
		verify(boardRepo).save(new Board(width, height));
	}
}
