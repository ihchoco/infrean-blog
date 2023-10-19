package com.brawl.review.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class PostCreate {
    @NotBlank(message = "제목을 입력해주세요")
    private final String title;
    @NotBlank(message = "내용을 입력해주세요")
    private final String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PostCreate changeTitle(String title){
        return PostCreate.builder()
                .title(title)
                .content(content)
                .build();
    }
}
