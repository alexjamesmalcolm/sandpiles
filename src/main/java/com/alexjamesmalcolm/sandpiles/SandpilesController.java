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

	public void toppleBoard(long id) {
		Board board = boardRepo.findOne(id);
		board.topple();
	}

	public void setTile(long id, int x, int y, Tile tile) {
		Board board = boardRepo.findOne(id);
		board.setTile(x, y, tile);
	}

	public void makeBoard(int width, int height) {
		Board board = new Board(width, height);
		boardRepo.save(board);
	}

}
