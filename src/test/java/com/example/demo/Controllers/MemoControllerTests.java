package com.example.demo.Controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Entities.Memo;
import com.example.demo.Repositories.MemoRepository;

@WebMvcTest(MemoController.class)
class MemoControllerTests {
    @Autowired 
    MockMvc mvc;

    @MockBean
    private MemoRepository memoRepository;

	@Test
	void listTest() throws Exception {
        this.mvc
            .perform(get("/api/memos"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}

	@Test
	void getOkTest() throws Exception {
        Memo mockMemo = new Memo("Demo", "Content...");
        when(memoRepository.findById(1L)).thenReturn(Optional.of(mockMemo));
        
        this.mvc
            .perform(get("/api/memos/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
	}

	@Test
	void getNotFoundTest() throws Exception {
        Memo mockMemo = new Memo("Demo", "Content...");
        when(memoRepository.findById(1L)).thenReturn(Optional.of(mockMemo));

        this.mvc
            .perform(get("/api/memos/2"))
            .andExpect(status().isNotFound());
	}
}
