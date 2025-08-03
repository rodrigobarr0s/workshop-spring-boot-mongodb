package io.github.rodrigobarr0s.workshopmongo.resources;

import io.github.rodrigobarr0s.workshopmongo.domain.User;
import io.github.rodrigobarr0s.workshopmongo.repositories.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        var entities = service.findAll();
        return ResponseEntity.ok().body(entities);
    }
}
