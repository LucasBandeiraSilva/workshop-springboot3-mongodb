package com.lucasbandeira.workshopmongo.services;

import com.lucasbandeira.workshopmongo.domain.User;
import com.lucasbandeira.workshopmongo.dto.UserDTO;
import com.lucasbandeira.workshopmongo.repository.UserRepository;
import com.lucasbandeira.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User>findAll(){
        return userRepository.findAll();
    }

    public User findById(String id){
        Optional<User>optionalUser = userRepository.findById(id);
        return optionalUser.orElseThrow(()-> new ObjectNotFoundException("Object not found with id: " + id));
    }
    public User insert(User user ){
        return userRepository.insert(user);
    }

    public void delete (String id){
        User user = userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found with id: " + id));
        userRepository.delete(user);
    }


    public User fromDTO( UserDTO userDTO ){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
