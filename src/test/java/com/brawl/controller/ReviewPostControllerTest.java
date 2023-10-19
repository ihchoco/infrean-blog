package com.brawl.controller;

import com.brawl.review.repository.ReviewPostRepository;
import com.brawl.review.request.PostCreate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
class ReviewPostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReviewPostRepository reviewPostRepository;

    @Test
    @DisplayName("post_테스트")
    void PostControllerTest() throws Exception {
        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/review_posts")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", "제목입니다")
                .param("content", "내용입니다"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("HELLO WORLD"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("post_테스트")
    void PostControllerTest2() throws Exception {
        PostCreate request = PostCreate.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();
        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/review_posts_model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("HELLO WORLD"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("post_테스트_body")
    void PostControllerTest3() throws Exception {
        PostCreate request = PostCreate.builder()
                .content("내용입니다")
                .build();
        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/review_posts_body")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.validation.title").value("제목을 입력해주세요"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("post_테스트")
    void PostControllerTest4() throws Exception {
        PostCreate request = PostCreate.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();
        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/review_posts_map")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("title", "제목입니다")
                        .param("content", "내용입니다"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("HELLO WORLD"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("post_테스트_body_success")
    void PostControllerTest5() throws Exception {
        PostCreate request = PostCreate.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();
        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/review_posts_body")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("제목입니다"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("내용입니다"))
                .andDo(MockMvcResultHandlers.print());

        Assertions.assertThat(reviewPostRepository.count()).isEqualTo(1);
    }


}