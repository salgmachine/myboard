package me.bcfh.myboard;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import me.bcfh.myboard.repo.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MyboardApplication.class)
@WebIntegrationTest
public abstract class BaseTest {

    @Autowired
    private WebApplicationContext ctx;

    @Autowired
    protected ItemRepository itemRepo;

    protected MockMvc mvc;

    @Before
    @Transactional
    public void setupTest() {
	mvc = MockMvcBuilders.webAppContextSetup(ctx).alwaysDo(print()).build();
	itemRepo.deleteAll();
    }

    // @Test
    // public void testPost() throws Exception {
//	//@formatter:off
//	mvc.perform(post("http://localhost:8080/items")
//		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//		.content(
//			new JSONStringer()
//			.object()
//			.key("title").value("title")
//			.key("text").value("text")
//			.key("editor").value("editor")
//			.key("effort").value(3.3F)
//			.key("complexity").value(3)
//			.endObject().toString()))
//	.andExpect(status().isOk());
//    }
//    
//    @Test
//    public void testDelete() throws Exception { 
//	//@formatter:off
//	mvc.perform(delete("http://localhost:8080/items")
//		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//		.param("id", "1"))
//	.andExpect(status().isOk());
//    }
//
//    @Test
//    public void testPatch() throws Exception { 
//	//@formatter:off
//	mvc.perform(patch("http://localhost:8080/items")
//		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//		.content(
//			new JSONStringer()
//			.object()
//			.key("id").value(1)
//			.key("title").value("title")
//			.key("text").value("text")
//			.key("editor").value("editor")
//			.key("effort").value(3.3F)
//			.key("complexity").value(3)
//			.endObject().toString()))
//	.andExpect(status().isOk());
//    }
//    
//    @Test
//    public void testPatch_withMissingId() throws Exception { 
//	//@formatter:off
//	mvc.perform(patch("http://localhost:8080/items")
//		.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//		.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//		.content(
//			new JSONStringer()
//			.object()
//			.key("title").value("title")
//			.key("text").value("text")
//			.key("editor").value("editor")
//			.key("effort").value(3.3F)
//			.key("complexity").value(3)
//			.endObject().toString()))
//	.andExpect(status().isBadRequest())
//	.andExpect(jsonPath("$.message", equalTo("Id missing")));
//    }

}
