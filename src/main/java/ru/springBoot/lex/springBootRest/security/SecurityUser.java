package ru.springBoot.lex.springBootRest.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.springBoot.lex.springBootRest.model.Status;
import ru.springBoot.lex.springBootRest.model.User;


import java.util.Collection;
import java.util.List;

public class SecurityUser implements UserDetails {

    private final String name;
    private final String password;
    private final List<SimpleGrantedAuthority> authorities;
    private final boolean isActive;

    public SecurityUser(String name, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
        this.name = name;
        this.password = password;
        this.authorities = authorities;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromUser(User user){
           return new org.springframework.security.core.userdetails.User (
                   user.getName(), user.getPassword(),
                   user.getStatus().equals(Status.ACTIVE),
                   user.getStatus().equals(Status.ACTIVE),
                   user.getStatus().equals(Status.ACTIVE),
                   user.getStatus().equals(Status.ACTIVE),
                   user.getRoles().getAuthorities()
           );
    }
}
