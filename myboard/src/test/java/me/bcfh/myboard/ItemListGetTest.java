package me.bcfh.myboard;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import me.bcfh.myboard.model.Item;

public class ItemListGetTest extends BaseTest {

    private Item item;

    @Before
    public void beforeTest() {
	item = new Item();
	item.setTitle("TEST");
	item.setText("TEST");
	item = itemRepo.saveAndFlush(item);
    }

    @Test
    public void testGetItemList() throws Exception {
	//@formatter:off
	mvc.perform(get("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(1)))
		.andExpect(jsonPath("$[0].id", equalTo(1)))
		.andExpect(jsonPath("$[0].title", equalTo("TEST")));
    }
}
