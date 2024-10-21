package com.lucasbandeira.workshopmongo.resources;

import com.lucasbandeira.workshopmongo.domain.Post;
import com.lucasbandeira.workshopmongo.domain.User;
import com.lucasbandeira.workshopmongo.dto.UserDTO;
import com.lucasbandeira.workshopmongo.resources.util.URL;
import com.lucasbandeira.workshopmongo.services.PostService;
import com.lucasbandeira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
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

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>>findByTitle(@RequestParam(value = "text",defaultValue = "") String text){
        text = URL.decodeParam(text);
        return ResponseEntity.ok(postService.findByTitle(text));
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>>fullSearch( @RequestParam(value = "text",defaultValue = "") String text,
                                                 @RequestParam(value = "minDate",defaultValue = "")String minDate,
                                                 @RequestParam(value = "maxDate",defaultValue = "") String maxDate){
       LocalDate min = URL.convertDate(minDate,LocalDate.of(1970, 1, 1));
       LocalDate max = URL.convertDate(maxDate,LocalDate.now());
       text = URL.decodeParam(text);
        return ResponseEntity.ok(postService.fullSearch(text,min,max));
    }

}
