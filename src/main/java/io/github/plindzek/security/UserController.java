package io.github.plindzek.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/users")
class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{name}")
    ResponseEntity<AppUser> findUser(@PathVariable String name) {
        return ok(userService.loadUserByUsername(name));
    }

    @GetMapping
    ResponseEntity<List<AppUser>> findAllUsers() {
        return ok(userService.findAll());
    }

    @PostMapping
    ResponseEntity<AppUser> saveUser(@RequestBody AppUser appUser) {
        ResponseEntity responseEntity = null;
        try {
            userService.save(appUser);
            responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(appUser);
        } catch (UserExistException e) {
            e.printStackTrace();
            responseEntity = ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return responseEntity;
    }

    @DeleteMapping("/{name}")
    ResponseEntity<String> deleteUser(@PathVariable String name) {
        ResponseEntity responseEntity = null;
        try {
            userService.delete(name);
            responseEntity = ResponseEntity.noContent().build();

        } catch (NullPointerException e) {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }
}