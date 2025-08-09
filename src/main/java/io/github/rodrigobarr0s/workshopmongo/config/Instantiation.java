package io.github.rodrigobarr0s.workshopmongo.config;

import io.github.rodrigobarr0s.workshopmongo.domain.Post;
import io.github.rodrigobarr0s.workshopmongo.domain.User;
import io.github.rodrigobarr0s.workshopmongo.dto.AuthorDTO;
import io.github.rodrigobarr0s.workshopmongo.dto.CommentDTO;
import io.github.rodrigobarr0s.workshopmongo.repositories.PostRepository;
import io.github.rodrigobarr0s.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post[] posts = {
                new Post(null, Instant.parse("2018-03-21T00:00:00Z"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria)),
                new Post(null, Instant.parse("2018-03-23T00:00:00Z"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria))
        };

        CommentDTO[] comments = {
                new CommentDTO("Boa viagem mano!",Instant.parse("2018-03-21T00:00:00Z"),new AuthorDTO(alex)),
                new CommentDTO("Aproveite!",Instant.parse("2018-03-22T00:00:00Z"),new AuthorDTO(bob)),
                new CommentDTO("Tenha um ótimo dia!",Instant.parse("2018-03-23T00:00:00Z"),new AuthorDTO(alex))
        };

        posts[0].getComments().addAll(Arrays.asList(comments[0],comments[1]));
        posts[1].getComments().add(comments[2]);

        postRepository.saveAll(Arrays.asList(posts));

        maria.getPosts().addAll(Arrays.asList(posts[0], posts[1]));

        userRepository.save(maria);

    }
}