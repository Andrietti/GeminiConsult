package GeminiConsult.Services.Implementation;

import GeminiConsult.Entities.Dtos.UserDto;
import GeminiConsult.Entities.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface UserService {
    Optional<Users> findById(Long id);

    List<Users> findAll();

    Users save(UserDto user);

    ResponseEntity login(String login, String password);

    ResponseEntity register(UserDto user);
}
