package ru.learnhub.learnhubtestproject.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.learnhub.learnhubtestproject.entity.User;

import java.util.Collection;
import java.util.Collections;

public class PersonDetails implements UserDetails {

    private final User user;

    public PersonDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(user.getRole());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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

    public User getPerson() {
        return this.user;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "user=" + user.toString() +
                '}';
    }
}
