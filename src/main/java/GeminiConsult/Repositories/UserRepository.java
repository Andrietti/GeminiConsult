package GeminiConsult.Repositories;

import GeminiConsult.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByLogin(String login);
}
