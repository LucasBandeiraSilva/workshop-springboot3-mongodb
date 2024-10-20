package com.lucasbandeira.workshopmongo.services;

import com.lucasbandeira.workshopmongo.domain.Post;
import com.lucasbandeira.workshopmongo.repository.PostRepository;
import com.lucasbandeira.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById( String id ) {
        Optional <Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElseThrow(() -> new ObjectNotFoundException("Object not found with id: " + id));
    }
    public List<Post> findByTitle(String text){
        return postRepository.findByTitleContainingIgnoreCase(text);
    }
}
