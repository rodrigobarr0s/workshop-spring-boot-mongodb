package io.github.rodrigobarr0s.workshopmongo.resources;

import io.github.rodrigobarr0s.workshopmongo.domain.Post;
import io.github.rodrigobarr0s.workshopmongo.resources.util.URL;
import io.github.rodrigobarr0s.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        var entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        String decodedText = URL.decodeParam(text);
        List<Post> list = service.findByTitle(decodedText);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        String decodedText = URL.decodeParam(text);
        Instant startDate = URL.convertDate(minDate, Instant.EPOCH); // defaultDate 1970-01-01T00:00:00Z
        Instant endDate = URL.convertDate(maxDate, Instant.now());
        List<Post> list = service.fullSearch(decodedText, startDate, endDate);
        return ResponseEntity.ok().body(list);
    }

}
