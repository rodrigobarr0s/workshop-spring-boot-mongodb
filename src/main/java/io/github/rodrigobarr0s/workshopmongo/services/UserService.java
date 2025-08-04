package io.github.rodrigobarr0s.workshopmongo.services;

import io.github.rodrigobarr0s.workshopmongo.domain.User;
import io.github.rodrigobarr0s.workshopmongo.dto.UserDTO;
import io.github.rodrigobarr0s.workshopmongo.repositories.UserRepository;
import io.github.rodrigobarr0s.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User entity) {
        return repository.insert(entity);
    }

    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        repository.deleteById(id);
    }

    public User fromDTO(UserDTO dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail());
    }

}
