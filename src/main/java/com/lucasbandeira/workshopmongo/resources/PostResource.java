package com.lucasbandeira.workshopmongo.resources;

import com.lucasbandeira.workshopmongo.domain.Post;
import com.lucasbandeira.workshopmongo.domain.User;
import com.lucasbandeira.workshopmongo.dto.UserDTO;
import com.lucasbandeira.workshopmongo.services.PostService;
import com.lucasbandeira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    @Autowired
    private PostService postService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Post>findById(@PathVariable String id){
        return ResponseEntity.ok(postService.findById(id));
    }


}
