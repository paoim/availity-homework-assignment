package com.pao.availity.exercisesix.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.pao.availity.exercisesix.model.FileContent;
import com.pao.availity.exercisesix.service.FileContentService;

@WebMvcTest(FileContentController.class)
public class FileContentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FileContentService service;

	private List<FileContent> getEmpty() {
		return new ArrayList<FileContent>();
	}

	@Test
	public void test_getAllOriginalContents() throws Exception {
		when(service.getAllOriginalContents()).thenReturn(getEmpty());
		this.mockMvc.perform(get("/api/content/origin")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}

	@Test
	public void test_getAllCustomContents() throws Exception {
		when(service.getAllCustomContents()).thenReturn(getEmpty());
		this.mockMvc.perform(get("/api/content/custom")).andDo(print()).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}
}
