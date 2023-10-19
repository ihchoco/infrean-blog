package com.brawl.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostSearch {

    private static final int MAX_SIZE = 2000;

    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer size = 20;

    public long getOffset(){
        // return (long)(page - 1) * size; //만약 url : /posts?page=0 이렇게 들어오면 ERROR발생
        // 개선작업
        return (long)(Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }
}


