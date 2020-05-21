package io.github.plindzek.appUser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Adam
 *  create users and save to database at application startup
 *  with encoded passwords
 */
@Configuration
class AppUserCreate {

private AppUserRepository appUserRepository;

    AppUserCreate(AppUserRepository appUserRepository, PasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;

        AppUser appAdmin = new AppUser();
        appAdmin.setLangId(1);
        appAdmin.setUsername("admin");
        appAdmin.setPassword(encoder.encode("admin"));
        appAdmin.setRole("ROLE_ADMIN");

        AppUser appUser = new AppUser();
        appUser.setUsername("user");
        appUser.setLangId(1);
        appUser.setPassword(encoder.encode("user"));
        appUser.setRole("ROLE_USER");

        appUserRepository.save(appAdmin);
        appUserRepository.save(appUser);

    }

}
