package com.brawl.repository;

import com.brawl.domain.Post;
import com.brawl.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}

