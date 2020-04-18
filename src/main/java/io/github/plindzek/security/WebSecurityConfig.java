package io.github.plindzek.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/cars/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/cars/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                .and().addFilter(new JwtFilter(authenticationManager()));

        // .antMatchers(HttpMethod.DELETE, "/api/cars/**").hasRole("ADMIN")
        // .anyRequest().authenticated()
        // .and().formLogin()
        // .permitAll()
        // .and().logout()
        // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // .invalidateHttpSession(true)
        // .deleteCookies("JSESSIONID")
        // .logoutSuccessUrl("/login")
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

//configure alternative options with user in memory with no DB:
//    @Bean
//    public UserDetailsService userDetailsService() {
//        auth.inMemoryAuthentication().withUser(User.builder()
//                .username("user")
//                .password(getPasswordEncoder().encode("1234"))
//                .roles("USER"));
//        auth.inMemoryAuthentication().withUser(User.builder()
//                .username("admin")
//                .password(getPasswordEncoder().encode("1234"))
//                .roles("ADMIN"));
//return new InMemoryUserDetailsManager(admin, user);
//    }