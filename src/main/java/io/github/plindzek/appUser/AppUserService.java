package io.github.plindzek.appUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
class AppUserService implements UserDetailsService {
    private AppUserRepository appUserRepository;



    private final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("Request from Spring SEC for find UserDetails got");
        return appUserRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException("no user: " + s));
    }

    public AppUser findByName(String name) throws UsernameNotFoundException{
        logger.info("Request for find AppUser got");
        return appUserRepository.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException("No user found with username " + name));
    }

    public List<AppUser> findAll() {
        logger.info("Request for list all users got");
        return appUserRepository.findAll();
    }

    public UserDetails save(AppUser appUser) {
        logger.info("Request for add user got");
        appUserRepository.save(appUser);
        return appUser;
    }

    public void delete(String name) {
        logger.info("Request for delete user got");
        var appUser = appUserRepository.findByUsername(name);
        appUserRepository.delete(appUser.orElseThrow(() -> new UsernameNotFoundException("No user found with username " + name)));
    }

    }