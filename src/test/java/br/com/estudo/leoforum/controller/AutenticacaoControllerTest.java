package br.com.estudo.leoforum.controller;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class AutenticacaoControllerTest {
	
//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	void retornar403CasoNaoFalheNaAutenticacao() throws Exception {
//		
//		URI uri = new URI("/auth");
//		
//		String json = "{\"email\": \"leonardo\", \"senha\": \"abc123\"}";
//		
//		mockMvc
//		.perform(MockMvcRequestBuilders.post(uri)
//				.content(json)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers
//				.status()
//				.is(403));
//		
//	}

}
