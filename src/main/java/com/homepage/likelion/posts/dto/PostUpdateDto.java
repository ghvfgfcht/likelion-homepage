package com.homepage.likelion.posts.dto;

import lombok.*;

import java.time.LocalDateTime;

public class PostUpdateDto {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Req{
        private Long postId;
        private String title;
        private String content;
        private String postedUserName;
        private String password;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdatePost{
        private LocalDateTime updatedAt;

        public UpdatePost(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

}
