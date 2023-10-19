package com.brawl.service;

import com.brawl.domain.Post;
import com.brawl.domain.PostEditor;
import com.brawl.exception.PostNotFound;
import com.brawl.repository.PostRepository;
import com.brawl.request.PostCreate;
import com.brawl.request.PostEdit;
import com.brawl.request.PostSearch;
import com.brawl.response.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        // postRepository.save(postCreate); -> 에러발생(일반 클래스를 Entity 변환 필요)
        // 일반 클래스(DTO) -> Entity 변환
        //Post post = new Post(postCreate.getTitle(), postCreate.getContent());
        Post post = Post.builder()
                        .title(postCreate.getTitle())
                        .content(postCreate.getContent())
                        .build();
        postRepository.save(post);
    }

    public PostResponse get(Long id) {
        //findById로 하게되면 Optional로 가져오게 된다
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());
        // Entity -> Response 타입으로 변환
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    public List<PostResponse> getList(PostSearch postSearch) {
        //web에서 page 1로 넘어와도 여기 PageRequest.of(page, 5)에 있는 page에는 0이 들어가야 한다

        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());
        /*
        //현재 위 메서드의 경우 아직 FIX 되지 않은 builder이기 때문에 밑에 데이터를 수정해줄 수 있다
        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

       //FIX 되지 않은 builder에 데이터를 변경해주고 build 해서 새로운 PostEditor를 만든다
        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        //그리고 나서 Post에 다가 edit 메서드를 호출한다
        post.edit(postEditor);
         */

        //일단 단순하게 change를 통해서 수정하는 방식으로 사용(추후에 바꿔보자)
        post.change(postEdit.getTitle(), postEdit.getContent());

        new PostResponse(post);
    }

    public void delete(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFound());
        //존재하는 경우
        postRepository.delete(post);
    }

}
