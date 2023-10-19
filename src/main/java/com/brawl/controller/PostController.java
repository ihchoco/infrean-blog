package com.brawl.controller;

import com.brawl.domain.Post;
import com.brawl.exception.InvalidRequest;
import com.brawl.request.PostCreate;
import com.brawl.request.PostEdit;
import com.brawl.request.PostSearch;
import com.brawl.response.PostResponse;
import com.brawl.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request)  {
        request.validate();
        postService.write(request);
    }

    /**
     * /posts -> 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */
    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable Long postId){
        // Request 클래스
        // Response 클래스

        return postService.get(postId);
    }

    //여러 건 조회 API

    /** 현재 문제점
     * 글이 너무 많은 경우 -> 비용이 너무 많이 든다
     * 글이 -> 100,000,000 -> DB 글 모두 조회하는 경우 -> DB가 뻗을 수 있다
     * DB -> 애플리케이션 서버로 전달하는 시간, 데이터, 트래픽비용 등이 많이 발생 가능
     */
    @GetMapping("/posts")
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch){
        return postService.getList(postSearch);
    }


    //게시글 수정
    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable Long postId, @RequestBody @Valid PostEdit request){
        postService.edit(postId, request);
    }

    //게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId){
        postService.delete(postId);
    }

}
