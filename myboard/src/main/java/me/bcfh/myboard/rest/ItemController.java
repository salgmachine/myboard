package me.bcfh.myboard.rest;

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

import me.bcfh.myboard.model.ErrorDto;
import me.bcfh.myboard.model.Item;
import me.bcfh.myboard.repo.ItemRepository;

@RestController("/items")
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepo;

    @Transactional
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> get(@PathVariable Long id) {
	if (id == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Data missing"), HttpStatus.BAD_REQUEST);
	Item itm = itemRepo.findOne(id);
	if (itm == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Item not found"), HttpStatus.NOT_FOUND);

	return new ResponseEntity<>(itm, HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Item>> get() {
	return new ResponseEntity<>(itemRepo.findAll(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> delete(@RequestParam(name = "id", required = true) Long id) {
	if (id == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Data missing"), HttpStatus.BAD_REQUEST);
	Item itm = itemRepo.findOne(id);
	if (itm == null)
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Item not found"), HttpStatus.NOT_FOUND);
	itemRepo.delete(itm);
	return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> persist(@RequestBody Item item) {
	if (item == null) {
	    return new ResponseEntity<ErrorDto>(new ErrorDto("Data missing"), HttpStatus.BAD_REQUEST);
	}
	Item itm = itemRepo.saveAndFlush(item);
	return new ResponseEntity<>(itm, HttpStatus.OK);
    }

}
