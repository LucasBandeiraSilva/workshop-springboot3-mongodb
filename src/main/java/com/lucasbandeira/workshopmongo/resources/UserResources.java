package com.lucasbandeira.workshopmongo.resources;

import com.lucasbandeira.workshopmongo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        User maria = new User("1a","maria","maria@Gmail.com");
        User mario = new User("2","Mario","mario@gmail.com");
        User bob = new User("3","Bob","bob@gmail.com");
        List<User>list = new ArrayList <>(Arrays.asList(maria,mario,bob));
        return ResponseEntity.ok().body(list);

    }
}
