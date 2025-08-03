package io.github.rodrigobarr0s.workshopmongo.repositories;

import io.github.rodrigobarr0s.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
