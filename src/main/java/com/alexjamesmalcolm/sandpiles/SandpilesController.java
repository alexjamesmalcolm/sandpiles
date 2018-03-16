package com.alexjamesmalcolm.sandpiles;

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

}
