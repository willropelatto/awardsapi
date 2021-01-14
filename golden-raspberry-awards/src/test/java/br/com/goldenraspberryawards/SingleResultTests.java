package br.com.goldenraspberryawards;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "csv.filePath=/static/test01.csv")
@AutoConfigureMockMvc
class SingleResultTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void testSingleResult() throws Exception {
		mvc.perform(get("/interval").contentType(MediaType.APPLICATION_JSON)) //
				.andDo(print()) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON)) //
				.andExpect(jsonPath("$.min", hasSize(1))) //
				.andExpect(jsonPath("$.min.[0].producer", is("Joel Silver")))
				.andExpect(jsonPath("$.min.[0].interval", is(1)))
				.andExpect(jsonPath("$.min.[0].previousWin", is(1990)))
				.andExpect(jsonPath("$.min.[0].followingWin", is(1991)))
				.andExpect(jsonPath("$.max", hasSize(1)))
				.andExpect(jsonPath("$.max.[0].producer", is("Bo Derek")))
				.andExpect(jsonPath("$.max.[0].interval", is(6)))
				.andExpect(jsonPath("$.max.[0].previousWin", is(1984)))
				.andExpect(jsonPath("$.max.[0].followingWin", is(1990)));
	}

}
