package com.Lycle.Server.config.auth;

import com.Lycle.Server.domain.User.Role;
import com.Lycle.Server.domain.User.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class UserPrincipal implements UserDetails {
    private final User user;

    private Long id;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRoleKey().equals("ROLE_ADMIN")) {
            authorities.add(new SimpleGrantedAuthority(Role.USER.getKey()));
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoleKey());
        authorities.add(authority);
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getNickname();
    }

    public List<String> getRoles(){
        List<String> roles = new ArrayList<>();
        if (user.getRoleKey().equals("ROLE_ADMIN")) {
            roles.add(Role.USER.getKey());

        }
        roles.add(user.getRoleKey());
        return roles;
    }

    public String getEmail(){
        return this.user.getEmail();
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
