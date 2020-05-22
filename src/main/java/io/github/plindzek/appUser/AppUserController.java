package io.github.plindzek.appUser;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
class AppUserController {

    private AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @PostMapping("/register")
    public String register(@RequestBody AppUser appUser) {

        String response;

        if (appUser.getUsername().equals("") || appUser.getPassword().equals("")) {
            response = "no empty login/password allowed";

        } else {
            try {
                var appUserFromDb = appUserService.findByName(appUser.getUsername());
                response = "User exist";

            } catch (UsernameNotFoundException e) {
                appUser.setRole("ROLE_USER");
                appUser.setPassword(getPasswordEncoder().encode(appUser.getPassword()));
                appUserService.save(appUser);
                response = "user created, please login";
            }
        }
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser appUserToLogin) {

        ResponseEntity response;

        if (appUserToLogin.getUsername().equals("") || appUserToLogin.getPassword().equals("")) {
            response = ResponseEntity.ok("no empty login/password allowed");

        } else {
            try {
                var appUserFromDb = appUserService.findByName(appUserToLogin.getUsername());

                if (getPasswordEncoder().matches(appUserToLogin.getPassword(), appUserFromDb.getPassword())) {

                    Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(appUserFromDb.getRole()));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(appUserFromDb.getUsername(), null, simpleGrantedAuthorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    response = ResponseEntity.ok("User logged");

                } else {
                    response = ResponseEntity.ok("Wrong password");
                }
            } catch (UsernameNotFoundException e) {

                return ResponseEntity.ok("User not found");
            }
        }
        return response;
    }

    @GetMapping("/{name}")
    ResponseEntity<UserDetails> findUser(@PathVariable String name) {
        return ok(appUserService.loadUserByUsername(name));
    }

    @GetMapping("/loggedUser")
    String loggedUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;

        if (authentication.getClass().equals(OAuth2AuthenticationToken.class)) {
            username = (((OAuth2AuthenticationToken) authentication).getPrincipal().getAttributes()).get("login").toString();
        } else {
            username = authentication.getName();
        }
        return username;
    }

    @GetMapping
    ResponseEntity<List<AppUser>> findAllUsers() {
        return ok(appUserService.findAll());
    }

    @PostMapping
    ResponseEntity<UserDetails> saveUser(@RequestBody AppUser appUser) {
        appUserService.save(appUser);
        return ResponseEntity.status(HttpStatus.OK).body(appUser);
    }

    @DeleteMapping("/{name}")
    ResponseEntity<String> deleteUser(@PathVariable String name) {
        ResponseEntity responseEntity = null;
        try {
            appUserService.delete(name);
            responseEntity = ResponseEntity.noContent().build();

        } catch (NullPointerException e) {
            responseEntity = ResponseEntity.notFound().build();
        }
        return responseEntity;
    }


}
