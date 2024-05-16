package com.homepage.likelion.domain;

import com.homepage.likelion.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "POSTS")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "POSTS_ID")
    private Long id;

    @Column(name = "POSTS_TITLE")
    private String title;

    @Column(name = "POSTS_CONTENT")
    private String content;

    @Column(name = "POSTED_USER_NAME")
    private String postedUserName;

    @Column(name = "POSTS_PASSWORD")
    private String password;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void changeUserName(String postedUserName) {
        this.postedUserName = postedUserName;
    }
}
