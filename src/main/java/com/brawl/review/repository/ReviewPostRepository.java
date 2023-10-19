package com.brawl.review.repository;

import com.brawl.review.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewPostRepository extends JpaRepository<Posts, Long> {
}
