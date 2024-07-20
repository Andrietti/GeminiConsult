package GeminiConsult.Controllers;


import GeminiConsult.Dtos.LoginRequestDTO;
import GeminiConsult.Entities.Dtos.RegisterUserDTO;
import GeminiConsult.Entities.Dtos.UserDto;
import GeminiConsult.Entities.Users;
import GeminiConsult.Exceptions.GenericExceptions;
import GeminiConsult.Repositories.UserRepository;
import GeminiConsult.Security.TokenService;
import GeminiConsult.Services.Implementation.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final TokenService tokenService;
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        Users user = userRepository.findByLogin(loginRequestDTO.login());

        return userService.login(loginRequestDTO.login(), loginRequestDTO.password());
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserDto registerUserDTO){
        return userService.register(registerUserDTO);
    }
}
