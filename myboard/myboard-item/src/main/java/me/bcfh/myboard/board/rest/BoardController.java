package me.bcfh.myboard.board.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import me.bcfh.myboard.board.repo.BoardRepository;
import me.bcfh.myboard.model.Board;
import me.bcfh.myboard.model.ErrorDto;

@RestController("/boards")
@RequestMapping(value = "/boards")
public class BoardController {

    @Autowired
    private BoardRepository repo;

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id) {
	if (id == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Data missing"), HttpStatus.BAD_REQUEST);
	Board board = repo.findOne(id);
	if (board == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Item not found"), HttpStatus.NOT_FOUND);
	return new ResponseEntity<Board>(board, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/{uname}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@PathVariable String uname) {
	if (uname == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Data missing"), HttpStatus.BAD_REQUEST);
	Board board = repo.findByName(uname);
	if (board == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Board not found"), HttpStatus.NOT_FOUND);
	return new ResponseEntity<Board>(board, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Board>> get() {
	List<Board> result = repo.findAll();
	return new ResponseEntity<List<Board>>(result, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestParam(value = "id", required = true) Long id) {
	if (id == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Data missing"), HttpStatus.BAD_REQUEST);
	Board board = repo.findOne(id);
	if (board == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Board not found"), HttpStatus.NOT_FOUND);
	repo.delete(board);
	return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> persist(@RequestBody Board board) {
	if (board == null) {
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Data missing"), HttpStatus.BAD_REQUEST);
	}
	board = repo.saveAndFlush(board);
	return new ResponseEntity<>(board, HttpStatus.OK);
    }

}
