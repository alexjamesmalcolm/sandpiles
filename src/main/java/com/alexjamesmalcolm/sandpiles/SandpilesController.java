package com.alexjamesmalcolm.sandpiles;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SandpilesController {

	@Resource
	private BoardRepository boardRepo;

	@RequestMapping(value = "/boards/{id}", method = GET)
	public Board getBoard(@PathVariable long id) {
		return boardRepo.findOne(id);
	}

	@RequestMapping(value = "/boards/{id}", method = DELETE)
	public void deleteBoard(@PathVariable long id) {
		boardRepo.delete(id);
	}

	@RequestMapping(value = "/boards", method = GET)
	public Collection<Board> getBoards() {
		return (Collection<Board>) boardRepo.findAll();
	}

	@RequestMapping(value = "/boards/{id}", method = PUT)
	public Board toppleBoard(@PathVariable long id) {
		Board board = boardRepo.findOne(id);
		board.topple();
		return board;
	}

	@RequestMapping(value = "/boards/{id}", method = PATCH)
	public Board setTile(@PathVariable long id, @RequestParam int x, @RequestParam int y, @RequestParam int sand) {
		Board board = boardRepo.findOne(id);
		board.setTile(x, y, new Tile(sand));
		return board;
	}

	@RequestMapping(value = "/boards", method = POST)
	public Board makeBoard(@RequestParam(value = "w") int width, @RequestParam(value = "h") int height,
			@RequestParam int sand, @RequestParam boolean topple) {
		Board board = new Board(width, height);
		board.generate(sand);
		if (topple) {
			board.topple();
		}
		boardRepo.save(board);
		return board;
	}

}
