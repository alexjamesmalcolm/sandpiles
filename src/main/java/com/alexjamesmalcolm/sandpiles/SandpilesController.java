package com.alexjamesmalcolm.sandpiles;

import java.util.Collection;

import javax.annotation.Resource;

public class SandpilesController {
	
	@Resource
	private BoardRepository boardRepo;
	
	public Board getBoard(long boardId) {
		return boardRepo.findOne(boardId);
	}

	public void deleteBoard(long boardId) {	
		boardRepo.delete(boardId);
	}

	public Collection<Board> getBoards() {
		return (Collection<Board>) boardRepo.findAll();
	}

	public void toppleBoard(long boardId) {
		Board board = boardRepo.findOne(boardId);
		board.topple();
	}

}
