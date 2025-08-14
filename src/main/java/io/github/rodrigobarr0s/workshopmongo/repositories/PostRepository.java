package io.github.rodrigobarr0s.workshopmongo.repositories;

import io.github.rodrigobarr0s.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    // Query Methods
    List<Post> findByTitleContainingIgnoreCase(String text);

}
