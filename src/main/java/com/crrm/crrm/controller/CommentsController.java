package com.crrm.crrm.controller;

import com.crrm.crrm.entity.Comment;
import com.crrm.crrm.entity.Posts;
import com.crrm.crrm.repository.CommentRepository;
import com.crrm.crrm.repository.PostsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentsController {

    private PostsRepository postsRepository;
    private CommentRepository commentsRepository;

    public CommentsController(PostsRepository postsRepository, CommentRepository commentsRepository) {
        this.postsRepository = postsRepository;
        this.commentsRepository = commentsRepository;
    }

    @PostMapping
    public String createComment(
            @RequestBody Comment comments,
            @RequestParam long postId
    ) {
        Posts posts = postsRepository.findById(postId).get();
        comments.setPosts(posts);
        commentsRepository.save(comments);

        return "comments are created";
    }
}
