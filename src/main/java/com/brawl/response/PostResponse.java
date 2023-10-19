package com.brawl.response;

import com.brawl.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 서비스 정책에 맞는 클래스
 */
@Getter
@Builder
public class PostResponse {

    private final Long id;
    private final String title;
    private final String content;

    //생성자 오버로딩
    public PostResponse(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder //기존에 클래스 위에 Builder를 달았는데 이번에는 생성자를 만들어서 Builder를 추가하였음
    public PostResponse(Long id, String title, String content){
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }
}