package io.github.rodrigobarr0s.workshopmongo.dto;

import io.github.rodrigobarr0s.workshopmongo.domain.User;

import java.io.Serial;
import java.io.Serializable;

public class AuthorDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;

    public AuthorDTO() {
    }

    public AuthorDTO(User entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
