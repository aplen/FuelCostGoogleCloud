package io.github.plindzek.appUser;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
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



    @PostMapping("/login")
    public void login(@RequestBody AppUser appUserToSave) throws UnsupportedEncodingException {
        AppUser authenticatedAppUser;

        //sprawdzamy po nazwie, czy istnieje w bazie
        try {
            var appUserFromDb = appUserService.findByName(appUserToSave.getUsername());
            //jeżeli istnieje to sprawdzamy, czy hasła są zgodne
            if (getPasswordEncoder().matches(appUserToSave.getPassword(), appUserFromDb.getPassword())) {
                //logger.info("hasło OK");
                //jeżeli hasła są zgodne pobieramy istniejącego użytkownika
                authenticatedAppUser = appUserFromDb;
            } else {
                authenticatedAppUser = appUserService.findByName("user");
                //jeżeli hasła nie są zgodne, zostaje zalogowany domyslny użytkownik
                //TODO exception - zamiast default user powinien być userdontexistexception oraz brak
                //logger.info("Błędne hasło");
            }

        } catch (UsernameNotFoundException e) {
            //jeżeli nie istnieje to dodajemy nowego
            //TODO zapytać się użytkownika, czy dodać go do bazy
            //dodajemy role, jaka chcemy by pełnił nowy user
            appUserToSave.setRole("ROLE_USER");
            //zabezpieczamy hasło
            appUserToSave.setPassword(getPasswordEncoder().encode(appUserToSave.getPassword()));
            //zapis do bazy
            authenticatedAppUser = appUserToSave;
            appUserService.save(authenticatedAppUser);
        }
        String userName = authenticatedAppUser.getUsername();
        Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(authenticatedAppUser.getRole()));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, null, simpleGrantedAuthorities);


        UsernamePasswordAuthenticationToken authResult = authenticationToken;
        SecurityContextHolder.getContext().setAuthentication(authResult);
    }

    @GetMapping("/{name}")
    ResponseEntity<UserDetails> findUser(@PathVariable String name) {
        return ok(appUserService.loadUserByUsername(name));
    }

    @GetMapping("/loggedUser")
    String loggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username;

        if(authentication.getClass().equals(OAuth2AuthenticationToken.class)){
            username = (((OAuth2AuthenticationToken) authentication).getPrincipal().getAttributes()).get("login").toString();
        }else
            {
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
