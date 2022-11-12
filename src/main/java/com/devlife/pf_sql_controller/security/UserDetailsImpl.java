package com.devlife.pf_sql_controller.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private  Long id;
    private  String username;
    private  String email;
    private  String phoneNumber;
    @JsonIgnore
    private  String password;
    private  Boolean enabled;
    private  Boolean accountNonExpired;
    private  Boolean accountNonLocked;
    private  Boolean credentialsNonExpired;
    private  Collection<? extends GrantedAuthority> authorities;
    public void setAuthorities(List<Map<String, Object>> authorities) {
        this.authorities = authorities.stream()
                .map(x -> (String) x.get("authority"))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
