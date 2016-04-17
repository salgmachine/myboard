package me.bcfh.myboard;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.json.JSONStringer;

import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;

public class ItemPatchTest extends BaseTest {

    @Test
    public void testPatch() throws Exception {
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
		).andExpect(status().isOk())
		.andExpect(jsonPath("$.id", equalTo(1)))
		.andExpect(jsonPath("$.title", equalTo("title")))
		.andExpect(jsonPath("$.text", equalTo("text")))
		.andExpect(jsonPath("$.editor", equalTo("editor")))
		.andExpect(jsonPath("$.complexity", equalTo(1)))
		.andExpect(jsonPath("$.effort", equalTo(3.3)));
	count = itemRepo.count();
	Assert.assertEquals(1, count);
	
	mvc.perform(patch("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_VALUE)
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.content(new JSONStringer().object()
			.key("id").value(1)
			.key("title").value("changedTitle")
			.key("text").value("changedText")
			.endObject().toString())
		).andExpect(status().isOk())
		.andExpect(jsonPath("$.id", equalTo(1)))
		.andExpect(jsonPath("$.title", equalTo("changedTitle")))
		.andExpect(jsonPath("$.text", equalTo("changedText")));
    }

}
