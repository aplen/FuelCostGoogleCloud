package io.github.plindzek;

import io.github.plindzek.appUser.AppUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserService appUserService;

    public SecurityConfig(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/users/login").permitAll()
                .antMatchers(HttpMethod.POST,"/users/register").permitAll()
                .antMatchers(HttpMethod.GET,"/users/**").permitAll()
                .antMatchers(HttpMethod.GET,"/users/loggeduser").hasAnyRole("USER","ADMIN")

                .antMatchers(HttpMethod.POST, "/users/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/api/langs").permitAll()
                .antMatchers(HttpMethod.GET, "/api/cars").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/cars/**").hasRole("ADMIN")

                .antMatchers("/register.html").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/css/**").permitAll()

                .anyRequest().authenticated()
                .and().formLogin()
                .loginProcessingUrl("/login.html").permitAll()
                .loginPage("/login.html").permitAll()
                .failureUrl("/login?error=true").permitAll()

                .and().oauth2Login()
                .loginPage("/login.html")

                .and().logout()
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")

        ;
    }

}



