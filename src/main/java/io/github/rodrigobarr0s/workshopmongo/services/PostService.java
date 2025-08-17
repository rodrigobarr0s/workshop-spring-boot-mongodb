package io.github.rodrigobarr0s.workshopmongo.services;

import io.github.rodrigobarr0s.workshopmongo.domain.Post;
import io.github.rodrigobarr0s.workshopmongo.repositories.PostRepository;
import io.github.rodrigobarr0s.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public List<Post> findByTitle(String text) {
        return repository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Instant minDate, Instant maxDate) {
        Instant endDate = maxDate.atZone(ZoneOffset.UTC)
                .withHour(23).withMinute(59).withSecond(59)
                .toInstant();
        return repository.fullSearch(text, minDate, endDate);
    }

}
