package com.homepage.likelion.posts.dto;

import com.homepage.likelion.domain.Post;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

public class PostCreateDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req{

        @NotBlank(message = "제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "본문을 입력해주세요.")
        private String content;

        @NotBlank(message = "작성자 이름을 입력해주세요.")
        private String postedUserName;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;

        public Post toEntity(){
            return Post.builder()
                    .title(title)
                    .content(content)
                    .postedUserName(postedUserName)
                    .password(password)
                    .build();
        }

    }

    // 게시글 작성 : postId, createdAt
    @Getter
    @Builder
    @NoArgsConstructor
    public static class CreatePost{
        private Long postId;
        private LocalDateTime createdAt;

        public CreatePost(Long postId, LocalDateTime createdAt) {
            this.postId = postId;
            this.createdAt = createdAt;
        }
    }
}