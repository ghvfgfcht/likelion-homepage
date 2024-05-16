package com.homepage.likelion.posts.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class PostListDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PostResponse{
        private Long postId;
        private String postedUserName;
        private String title;
        private String content;
        private LocalDateTime updateAt;
    }

    // 게시글 조회 : List<Post> posts
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SearchPostRes{
        private List<PostResponse> posts;


        public SearchPostRes(List<PostResponse> posts) {
            this.posts = posts;
        }

    }
}
