package io.github.plindzek;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(User.builder()
                .username("user")
                .password(getPasswordEncoder().encode("1234"))
                .roles("USER"));
        auth.inMemoryAuthentication().withUser(User.builder()
                .username("admin")
                .password(getPasswordEncoder().encode("1234"))
                .roles("ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*
* version with no loginpage - authenticated by token
* */
        http.authorizeRequests().antMatchers("/api/cars/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/cars/**").hasRole("ADMIN")
                .and().addFilter(new JwtFilter(authenticationManager()))
                .csrf().disable();

        // http.authorizeRequests()
        //       .antMatchers(HttpMethod.DELETE, "/api/cars/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
        // .and().formLogin()
        // .permitAll()
        // .and().logout()
        // .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        // .invalidateHttpSession(true)
        // .deleteCookies("JSESSIONID")
        // .logoutSuccessUrl("/login")
//                .csrf().disable(); //for all method works PUT/POST/DELETE
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    //    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("1234")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin);
//    }
}
