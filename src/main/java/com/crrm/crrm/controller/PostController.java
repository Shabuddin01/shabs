package com.crrm.crrm.controller;

import com.crrm.crrm.entity.Comment;
import com.crrm.crrm.entity.Posts;
import com.crrm.crrm.repository.CommentRepository;
import com.crrm.crrm.repository.EmployeeRepository;
import com.crrm.crrm.repository.PostsRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private PostsRepository postsRepository;


    public PostController(PostsRepository postsRepository, CommentRepository commentRepository,
                          EmployeeRepository employeeRepository) {
        this.postsRepository = postsRepository;
    }

    @PostMapping("/add")
    public String createPost(
            @RequestBody Posts post
            ) {
        postsRepository.save(post);


        return "";
    }

    @DeleteMapping
    public String deletePost(){
        postsRepository.deleteById(1l);
        return "post deleted";
    }

}