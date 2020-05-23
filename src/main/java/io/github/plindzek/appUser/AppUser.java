package io.github.plindzek.appUser;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Adam
 *  AppUser entity
 */
@Data
@Setter(AccessLevel.PACKAGE)
@Entity
@Table(name="users")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "langid")
    @Getter
    @Setter(AccessLevel.PUBLIC)
    private Integer langId;

    @Getter
    @Setter(AccessLevel.PUBLIC)
    private String username;

    @Getter
    @Setter(AccessLevel.PUBLIC)
    private String password;

    @Getter
    @Setter(AccessLevel.PUBLIC)
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}