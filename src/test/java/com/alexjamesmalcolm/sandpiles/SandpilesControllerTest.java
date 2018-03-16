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
	 */

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
		// boolean topple = true;
		underTest.makeBoard(width, height, 0, false);
		verify(boardRepo).save(new Board(width, height));
	}

	@Test
	public void shouldMakeBoardThatComesWithTwoSandOnEachTile() {
		int width = 2;
		int height = 2;
		Board board = new Board(width, height);
		int sand = 2;
		board.generate(sand);
		underTest.makeBoard(width, height, sand, false);
		verify(boardRepo).save(board);
	}

	@Test
	public void shouldMakeBoardThatComesToppled() {
		int width = 2;
		int height = 2;
		boolean topple = true;
		int sand = 10;
		Board board = new Board(width, height);
		board.generate(sand);
		board.topple();
		underTest.makeBoard(width, height, sand, topple);
		verify(boardRepo).save(board);
	}

	@Test
	public void shouldMakeBoardThatComesWantingToToppleButWasnt() {
		Board board = new Board(2, 2);
		board.generate(10);
		underTest.makeBoard(2, 2, 10, false);
		verify(boardRepo).save(board);
	}

	@Test
	public void shouldHaveMakeBoardReturnTheBoardItMadeTwoByTwo() {
		Board board = new Board(2, 2);
		Board actual = underTest.makeBoard(2, 2, 0, false);
		assertThat(actual, is(board));
	}
}
