package com.brawl.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob //Long Text
    private String content;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle(){ // PostSearch 클래스 새로 생성 이유
        //서비스의 정책을 넣지 마세요!! 절대!!
        return this.title;
    }
    //수정을 위한 메서드 추가
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }
    /*
    public PostEditor.PostEditorBuilder toEditor(){ //빌더 하지 않은 빌드 클래스 자체를 넘겨줘야 한다
        return PostEditor.builder()
                .title(title)
                .content(content);
    }

    public void edit(PostEditor postEditor){
        title = postEditor.getTitle();
        content = postEditor.getContent();
    }
     */
}

