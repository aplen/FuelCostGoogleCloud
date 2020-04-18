package io.github.plindzek.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Adam
 *  CRUD operations on users in database
 */
@Service
class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AppUser loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("Request for view user got");
        return userRepository.findByUsername(s);
    }

    public List<AppUser> findAll() {
        logger.info("Request for list all users got");
        return userRepository.findAll();
    }

    public AppUser save(AppUser appUser) throws UserExistException {
        logger.info("Request for add user got");

        if ((userRepository.findByUsername(appUser.getUsername())==null)){
            userRepository.save(appUser);
        }
else throw new UserExistException();
        return appUser;
    }

    public void delete(String name) {

        logger.info("Request for delete user got");
            var appUser = userRepository.findByUsername(name);
            int id = appUser.getId();
            userRepository.deleteById(id);
    }

}