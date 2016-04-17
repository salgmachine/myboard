package me.bcfh.myboard;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import me.bcfh.myboard.model.Item;

public class ItemGetTest extends BaseTest {

    private Item item;

    @Before
    public void beforeTest() {
	item = new Item();
	item.setTitle("TEST");
	item.setText("TEST");
	item = itemRepo.saveAndFlush(item);
    }

    @Test
    public void testGetItemById() throws Exception {
	//@formatter:off
	mvc.perform(get("http://localhost:8080/items/"+item.getId())
		.accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", equalTo(1)))
		.andExpect(jsonPath("$.title", equalTo("TEST")))
		.andExpect(jsonPath("$.text", equalTo("TEST")));
    }
    
    @Test
    public void testGetItemById_whenItemMissing() throws Exception {
	//@formatter:off
	mvc.perform(get("http://localhost:8080/items/2")
		.accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE))
	.andExpect(status().isNotFound())
	.andExpect(jsonPath("$.message", equalTo("Item not found")));
    }
}