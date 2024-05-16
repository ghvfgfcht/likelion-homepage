package com.homepage.likelion.posts.controller;

import com.homepage.likelion.posts.dto.PostCreateDto;
import com.homepage.likelion.posts.dto.PostUpdateDto;
import com.homepage.likelion.posts.service.PostService;
import com.homepage.likelion.util.response.CustomApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    // 게시글 작성
    @PostMapping("")
    public ResponseEntity<CustomApiResponse<?>> createPost(@Valid @RequestBody PostCreateDto.Req req){
        ResponseEntity<CustomApiResponse<?>> result = postService.createPost(req);

        return result;
    }

    // 게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<CustomApiResponse<?>> modifyPost(@PathVariable("postId") Long postId, @RequestBody PostUpdateDto.Req req){
        ResponseEntity<CustomApiResponse<?>> result = postService.modifyPost(postId, req);

        return result;
    }


    // 게시물 한 개 조회
    @GetMapping("/{postId}")
    public ResponseEntity<CustomApiResponse<?>> getOnePost(@PathVariable("postId") Long postId /* 강의교안에는 @RequestParam으로 구현되어있음*/){
        ResponseEntity<CustomApiResponse<?>> result = postService.getOnePost(postId);

        return result;
    }

    // 게시글 전체 조회
    @GetMapping("all")
    public ResponseEntity<CustomApiResponse<?>> getAllPosts(){
        ResponseEntity<CustomApiResponse<?>> result = postService.getAllPosts();

        return result;
    }
}
