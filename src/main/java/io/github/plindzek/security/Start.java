package io.github.plindzek.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Adam
 *  create users and save to database at application startup
 *  with encoded passwords
 */
@Configuration
class Start {

private UserRepository userRepository;

    Start(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        AppUser appAdmin = new AppUser();
        appAdmin.setUsername("admin");
        appAdmin.setPassword(encoder.encode("1234"));
        appAdmin.setRole("ROLE_ADMIN");
        userRepository.save(appAdmin);

        AppUser appUser = new AppUser();
        appUser.setUsername("user");
        appUser.setPassword(encoder.encode("1234"));
        appUser.setRole("ROLE_USER");
        userRepository.save(appUser);
    }
}
