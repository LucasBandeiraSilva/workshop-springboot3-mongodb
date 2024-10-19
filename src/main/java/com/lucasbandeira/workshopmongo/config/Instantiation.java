package com.lucasbandeira.workshopmongo.config;

import com.lucasbandeira.workshopmongo.domain.Post;
import com.lucasbandeira.workshopmongo.domain.User;
import com.lucasbandeira.workshopmongo.repository.PostRepository;
import com.lucasbandeira.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run( String... args ) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.of("GMT"));

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, LocalDate.parse("21/03/2018",formatter),"Let's hit the road!","I'm going to travel to São Paulo! Hugs!",maria);
        Post post2= new Post(null,LocalDate.parse("23/03/2018",formatter),"Good morning", "I woke up happy today!",maria);

        userRepository.saveAll(Arrays.asList(maria,alex,bob));
        postRepository.saveAll(Arrays.asList(post1,post2));
    }
}
