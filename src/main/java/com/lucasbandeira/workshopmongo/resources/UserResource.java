package com.lucasbandeira.workshopmongo.resources;

import com.lucasbandeira.workshopmongo.domain.User;
import com.lucasbandeira.workshopmongo.dto.UserDTO;
import com.lucasbandeira.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Void>insert(@RequestBody UserDTO userDTO){
        User user = userService.fromDTO(userDTO);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable String id){
         userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<User>updateUser(@RequestBody UserDTO userDTO,@PathVariable String id){
        User user = userService.fromDTO(userDTO);
        userService.updateUser(user,id);
        return ResponseEntity.noContent().build();
    }
}
