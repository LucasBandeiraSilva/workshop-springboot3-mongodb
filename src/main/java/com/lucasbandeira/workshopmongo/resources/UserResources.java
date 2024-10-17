package com.lucasbandeira.workshopmongo.resources;

import com.lucasbandeira.workshopmongo.domain.User;
import com.lucasbandeira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity <List <User>> findAll() {
        return ResponseEntity.ok(userService.findAll());

    }
}
