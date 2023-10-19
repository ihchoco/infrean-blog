package com.brawl.service;

import com.brawl.domain.Post;
import com.brawl.exception.PostNotFound;
import com.brawl.repository.PostRepository;
import com.brawl.request.PostCreate;
import com.brawl.request.PostEdit;
import com.brawl.request.PostSearch;
import com.brawl.response.PostResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void PostServiceTest(){
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();

        //when
        postService.write(postCreate);

        //then
        Post post = postRepository.findAll().get(0);
        Assertions.assertEquals("제목입니다", post.getTitle());
        Assertions.assertEquals("내용입니다", post.getContent());
    }
    
    @Test
    @DisplayName("글 1개 조회")
    void PostServiceTest2(){
        //given
        Post requestPost = Post.builder()
                .title("1234567890abcde")
                .content("bar")
                .build();
        postRepository.save(requestPost);


        //when
        PostResponse post = postService.get(requestPost.getId());

        //then
        Assertions.assertNotNull(post);
        assertEquals(1L, postRepository.count());
        Assertions.assertEquals("1234567890", post.getTitle());
        Assertions.assertEquals("bar", post.getContent());
    }

    /* 변경(글 여러개 조회 -> 글 1페이지 조회) : 페이징 처리가 들어가면서 테스트 코드 변경
    @Test
    @DisplayName("글 여러개 조회")
    void PostServiceTest3(){
        //given
        postRepository.saveAll(List.of(
                Post.builder()
                        .title("title_1")
                        .content("content_1")
                        .build(),
                Post.builder()
                        .title("title_2")
                        .content("content_2")
                        .build()
        ));


        //when
        List<PostResponse> posts = postService.getList();

        //then
        //assertEquals(2L, postRepository.count());
        assertEquals(2L, posts.size());
    }
     */

    @Test
    @DisplayName("글 1페이지 조회")
    void PostServiceTest3(){
        //given

        //게시글을 일단 여러개 저장을 해보자(30개 정도) - 재미있게 하려면
        List<Post> requestPosts = IntStream.range(0, 20) //-> for(int i = 0; i < 30; i++){} 와 같은 결과
                .mapToObj(i -> {
                    return Post.builder()
                            .title("호돌맨 제목 "+ i)
                            .content("반포 자이 "+i)
                            .build();
                })
                .collect(Collectors.toList());
        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .size(10)
                .build();

        //when
        List<PostResponse> posts = postService.getList(postSearch); //첫 페이지를 넘겼을 때 1페이지 정보만 리턴

        //then
        assertEquals(10, posts.size());
        assertEquals("호돌맨 제목 19", posts.get(0).getTitle());
    }

    @Test
    @DisplayName("글 제목 수정")
    void PostServiceTest4(){
        //given
        //게시글을 일단 여러개 저장을 해보자(30개 정도) - 재미있게 하려면
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("호돌걸")
                .build();

        //when
        postService.edit(post.getId(), postEdit);

        //then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id = " + post.getId()));
        Assertions.assertEquals("호돌걸", changePost.getTitle());
        Assertions.assertEquals(null, changePost.getContent());
    }

    @Test
    @DisplayName("글 삭제")
    void PostServiceTest5(){
        //given
        //게시글을 일단 여러개 저장을 해보자(30개 정도) - 재미있게 하려면
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();
        postRepository.save(post);

        //when
        postService.delete(post.getId());

        //then
        Assertions.assertEquals(0, postRepository.count());
    }

    @Test
    @DisplayName("글 1개 조회-실패")
    void PostServiceTest7(){
        //given
        //게시글을 일단 여러개 저장을 해보자(30개 정도) - 재미있게 하려면
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();
        postRepository.save(post);

        //case 4 : 자체 Exception을 만들어서 테스트
        assertThrows(PostNotFound.class, () -> {
            postService.get(post.getId() + 1L);
        });
    }

    @Test
    @DisplayName("글 제목 수정 - 존재하지 않는 글")
    void PostServiceTest8(){
        //given
        //게시글을 일단 여러개 저장을 해보자(30개 정도) - 재미있게 하려면
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("호돌걸")
                .build();

        //expected
        assertThrows(PostNotFound.class, () -> {
            postService.edit(post.getId()+1L, postEdit);
        });
    }

    @Test
    @DisplayName("게시글 삭제 - 존재하지 않는 글")
    void PostServiceTest9(){
        //given
        //게시글을 일단 여러개 저장을 해보자(30개 정도) - 재미있게 하려면
        Post post = Post.builder()
                .title("호돌맨")
                .content("반포자이")
                .build();
        postRepository.save(post);

        //expected
        assertThrows(PostNotFound.class, () -> {
            postService.delete(post.getId() + 1L);
        });
    }
}