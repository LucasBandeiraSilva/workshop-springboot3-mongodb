package com.lucasbandeira.workshopmongo.resources;

import com.lucasbandeira.workshopmongo.domain.User;
import com.lucasbandeira.workshopmongo.dto.UserDTO;
import com.lucasbandeira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity <List <UserDTO>> findAll() {
        List<User>list = userService.findAll();
        List<UserDTO>userDTOList = list.stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(userDTOList);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO>findById(@PathVariable String id){
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserDTO(user));
    }
}
