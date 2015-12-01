package me.bcfh.myboard;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONStringer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyboardApplication.class)
@WebIntegrationTest
public class ItemControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mvc;

    @Before
    public void beforeTest() {
	mvc = MockMvcBuilders.webAppContextSetup(ctx).alwaysDo(print()).build();
    }

    @Test
    public void testGet() throws Exception {
	//@formatter:off
	mvc.perform(get("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().isOk());
    }
    
    @Test
    public void testGetById() throws Exception {
	//@formatter:off
	mvc.perform(get("http://localhost:8080/items/1")
		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
		)
	.andExpect(status().isOk());
    }
    
    
    @Test
    public void testPost() throws Exception { 
	//@formatter:off
	mvc.perform(post("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.content(
			new JSONStringer()
			.object()
			.key("title").value("title")
			.key("text").value("text")
			.key("editor").value("editor")
			.key("effort").value(3.3F)
			.key("complexity").value(3)
			.endObject().toString()))
	.andExpect(status().isOk());
    }
    
    @Test
    public void testDelete() throws Exception { 
	//@formatter:off
	mvc.perform(delete("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.param("id", "1"))
	.andExpect(status().isOk());
    }

    @Test
    public void testPatch() throws Exception { 
	//@formatter:off
	mvc.perform(patch("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.content(
			new JSONStringer()
			.object()
			.key("id").value(1)
			.key("title").value("title")
			.key("text").value("text")
			.key("editor").value("editor")
			.key("effort").value(3.3F)
			.key("complexity").value(3)
			.endObject().toString()))
	.andExpect(status().isOk());
    }
    
    @Test
    public void testPatch_withMissingId() throws Exception { 
	//@formatter:off
	mvc.perform(patch("http://localhost:8080/items")
		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
		.content(
			new JSONStringer()
			.object()
			.key("title").value("title")
			.key("text").value("text")
			.key("editor").value("editor")
			.key("effort").value(3.3F)
			.key("complexity").value(3)
			.endObject().toString()))
	.andExpect(status().isBadRequest())
	.andExpect(jsonPath("$.message", equalTo("Id missing")));
    }

}
