package GeminiConsult.Controllers;

import GeminiConsult.Entities.Dtos.UserDto;
import GeminiConsult.Entities.Users;
import GeminiConsult.Services.Implementation.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("{id}")
    public Optional<Users> findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/")
    public List<Users> findAll(){
        return userService.findAll();
    }

    @PostMapping("/")
    public Users save(@RequestBody UserDto user){
        return userService.save(user);
    }

}
