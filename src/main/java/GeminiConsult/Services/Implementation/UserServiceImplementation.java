package GeminiConsult.Services.Implementation;

import GeminiConsult.Entities.Dtos.RespondeDTO;
import GeminiConsult.Entities.Dtos.UserDto;
import GeminiConsult.Entities.Users;
import GeminiConsult.Exceptions.GenericExceptions;
import GeminiConsult.Repositories.UserRepository;
import GeminiConsult.Security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Optional<Users> findById(Long id){
        return userRepository.findById(id);
    }

    @Override

    public List<Users> findAll(){
        return userRepository.findAll();
    }

    @Override
    public Users save(UserDto user){
        Users newUser = new Users();
        newUser.setLogin(user.getLogin());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        return userRepository.save(newUser);
    }

    @Override

    public ResponseEntity login(String login, String password){
        Users user = userRepository.findByLogin(login);
        if(user == null){
            throw new GenericExceptions.UserNotFoundException("User not found with login: " + login);
        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new GenericExceptions.InvalidPasswordException("Invalid password");
        }
        String token = this.tokenService.generateToken(user);
        return ResponseEntity.ok(new RespondeDTO(user.getLogin(), token));
    }

    @Override
    public ResponseEntity register(UserDto user){

        if(userRepository.findByLogin(user.getLogin()) != null){
            throw new GenericExceptions.UserAlreadyExistsException("User alraeady exists with login: " + user.getLogin());
        }

        if(user.getLogin().isEmpty() || user.getPassword().isEmpty() || user.getRole().isEmpty()){
            throw new GenericExceptions.InvalidRoleException("Invalid user");
        }

        Users newUser = new Users();
        newUser.setLogin(user.getLogin());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(user.getRole());
        userRepository.save(newUser);
        return ResponseEntity.ok(new RespondeDTO(newUser.getLogin(), "User registered successfully"));
    }
}
