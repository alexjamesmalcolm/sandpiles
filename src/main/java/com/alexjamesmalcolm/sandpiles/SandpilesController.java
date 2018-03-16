package com.alexjamesmalcolm.sandpiles;

import java.util.Collection;

import javax.annotation.Resource;

public class SandpilesController {

	@Resource
	private BoardRepository boardRepo;

	public Board getBoard(long id) {
		return boardRepo.findOne(id);
	}

	public void deleteBoard(long id) {
		boardRepo.delete(id);
	}

	public Collection<Board> getBoards() {
		return (Collection<Board>) boardRepo.findAll();
	}

	public Board toppleBoard(long id) {
		Board board = boardRepo.findOne(id);
		board.topple();
		return board;
	}

	public Board setTile(long id, int x, int y, Tile tile) {
		Board board = boardRepo.findOne(id);
		board.setTile(x, y, tile);
		return board;
	}

	public Board makeBoard(int width, int height, int sand, boolean topple) {
		Board board = new Board(width, height);
		board.generate(sand);
		if (topple) {
			board.topple();
		}
		boardRepo.save(board);
		return board;
	}

}
