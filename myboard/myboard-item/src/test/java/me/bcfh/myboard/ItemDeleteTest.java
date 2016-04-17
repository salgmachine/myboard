package me.bcfh.myboard;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;

public class ItemDeleteTest extends BaseTest {

    @Test
    public void testDelete() throws Exception {
	long count = itemRepo.count();
	Assert.assertEquals(0, count);
	//@formatter:off
	mvc.perform(post("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.param("title", "title")
		.param("text", "text")
		.param("editor", "editor")
		.param("complexity", "1")
		.param("effort", "3.3")
		).andExpect(status().isOk());
	count = itemRepo.count();
	Assert.assertEquals(1, count);
	
	mvc.perform(delete("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.param("id", "1")
		)
	.andExpect(status().isOk());
	count = itemRepo.count();
	Assert.assertEquals(0, count);
	
    }
}