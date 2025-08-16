package io.github.rodrigobarr0s.workshopmongo.repositories;

import io.github.rodrigobarr0s.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    // Consulta personalizada usando @Query e operador $regex
    // Busca por posts cujo título contenha o texto informado, ignorando maiúsculas/minúsculas
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Post> searchTitle(String text);

    // Query Method automático do Spring Data
    // Realiza a mesma busca que o metodo acima, mas usando convenção de nomes
    // "Containing" indica que o texto pode estar em qualquer parte do título
    // "IgnoreCase" torna a busca insensível a maiúsculas/minúsculas
    List<Post> findByTitleContainingIgnoreCase(String text);

}
