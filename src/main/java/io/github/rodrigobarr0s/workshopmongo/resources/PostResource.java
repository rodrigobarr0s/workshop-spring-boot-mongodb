package io.github.rodrigobarr0s.workshopmongo.resources;

import io.github.rodrigobarr0s.workshopmongo.domain.Post;
import io.github.rodrigobarr0s.workshopmongo.resources.util.URL;
import io.github.rodrigobarr0s.workshopmongo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
