package com.brawl.review.service;

import com.brawl.review.domain.Posts;
import com.brawl.review.repository.ReviewPostRepository;
import com.brawl.review.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewPostService {
    private final ReviewPostRepository reviewPostRepository;

    public Posts write(PostCreate request){
        Posts post = Posts.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();
        return reviewPostRepository.save(post);
    }
}
