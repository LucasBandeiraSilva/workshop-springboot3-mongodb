package com.lucasbandeira.workshopmongo.config;

import com.lucasbandeira.workshopmongo.domain.Post;
import com.lucasbandeira.workshopmongo.domain.User;
import com.lucasbandeira.workshopmongo.dto.AuthorDTO;
import com.lucasbandeira.workshopmongo.dto.CommentDTO;
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
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, LocalDate.parse("21/03/2018",formatter),"Let's hit the road!","I'm going to travel to São Paulo! Hugs!",new AuthorDTO(maria));
        Post post2= new Post(null,LocalDate.parse("23/03/2018",formatter),"Good morning", "I woke up happy today!",new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Nice travel bro!",LocalDate.parse("21/03/2018",formatter),new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Enjoy",LocalDate.parse("22/03/2018",formatter),new AuthorDTO(bob));
        CommentDTO c3 = new CommentDTO("Have a nice day!",LocalDate.parse("23/03/2018",formatter),new AuthorDTO(alex));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));
        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1,post2));
        userRepository.save(maria);


    }
}
