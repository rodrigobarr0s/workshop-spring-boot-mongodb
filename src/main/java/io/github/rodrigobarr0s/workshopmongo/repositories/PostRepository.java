package io.github.rodrigobarr0s.workshopmongo.repositories;

import io.github.rodrigobarr0s.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {

}
