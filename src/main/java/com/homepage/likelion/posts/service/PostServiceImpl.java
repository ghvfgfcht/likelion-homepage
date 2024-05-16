package com.homepage.likelion.posts.service;

import com.homepage.likelion.domain.Post;
import com.homepage.likelion.posts.dto.PostCreateDto;
import com.homepage.likelion.posts.dto.PostListDto;
import com.homepage.likelion.posts.dto.PostUpdateDto;
import com.homepage.likelion.posts.repository.PostRepository;
import com.homepage.likelion.util.response.CustomApiResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public ResponseEntity<CustomApiResponse<?>> createPost(PostCreateDto.Req req) {

        // 방법 1. toEntity
        Post post = req.toEntity();

        // 방법 2. builder


        Post savedPost = postRepository.save(post);

        // 게시글 정보를 포함한 응답
        PostCreateDto.CreatePost createPostResponse = new PostCreateDto.CreatePost(savedPost.getId(), savedPost.getCreatedAt());

        CustomApiResponse<PostCreateDto.CreatePost> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), createPostResponse, "게시글 작성이 완료되었습니다.");

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> modifyPost(Long postId, PostUpdateDto.Req req) {
        Optional<Post> originalPost = postRepository.findById(postId);

        Post post = originalPost.get();
        post.changeTitle(req.getTitle());
        post.changeContent(req.getContent());
        post.changeUserName(req.getPostedUserName());

        postRepository.flush();

        PostUpdateDto.UpdatePost data = new PostUpdateDto.UpdatePost(post.getUpdatedAt());
        CustomApiResponse<PostUpdateDto.UpdatePost> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), data, "게시글 수정 완료");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> getOnePost(Long postId) {
        Optional<Post> originalPost = postRepository.findById(postId);

        if(originalPost.isEmpty()) {
            CustomApiResponse<?> response = CustomApiResponse.createSuccess(HttpStatus.NOT_FOUND.value(), null, "게시글을 찾을 수 없습니다.");
            return ResponseEntity.ok(response);
        }

        Post post = originalPost.get();
        PostListDto.PostResponse postResponse = new PostListDto.PostResponse(
                post.getId(),
                post.getPostedUserName(),
                post.getTitle(),
                post.getContent(),
                post.getUpdatedAt()
        );

        CustomApiResponse<PostListDto.PostResponse> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), postResponse, "게시글 조회 완료");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CustomApiResponse<?>> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        List<PostListDto.PostResponse> responseList = new ArrayList<>();
        for(Post post : posts) {
            PostListDto.PostResponse tmp = PostListDto.PostResponse.builder()
                    .postId(post.getId())
                    .postedUserName(post.getPostedUserName())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .build();

            responseList.add(tmp);
        }
        PostListDto.SearchPostRes result = new PostListDto.SearchPostRes(responseList);

        CustomApiResponse<PostListDto.SearchPostRes> response = CustomApiResponse.createSuccess(HttpStatus.OK.value(), result, "게시글 전체 조회 완료");

        return ResponseEntity.ok(response);
    }

}
