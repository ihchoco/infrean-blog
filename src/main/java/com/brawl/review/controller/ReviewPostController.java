package com.brawl.review.controller;

import com.brawl.review.domain.Posts;
import com.brawl.review.request.PostCreate;
import com.brawl.review.service.ReviewPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReviewPostController {

    private final ReviewPostService reviewPostService;

    @PostMapping("/review_posts")
    public String post(@RequestParam String title, @RequestParam String content){
        log.info(title, content);
        return "HELLO WORLD";
    }

    @PostMapping("/review_posts_map")
    public String post_map(@RequestParam Map<String, String> request){
        log.info("input = {}", request);
        return "HELLO WORLD";
    }

    @PostMapping("/review_posts_model")
    public String post_model(@ModelAttribute PostCreate request){
        log.info("input = {}", request);
        return "HELLO WORLD";
    }

    @PostMapping("/review_posts_body")
    public Posts post_body(@RequestBody @Valid PostCreate request){
        log.info("input = {}", request);
        Posts posts = reviewPostService.write(request);

        return posts;
    }

}
