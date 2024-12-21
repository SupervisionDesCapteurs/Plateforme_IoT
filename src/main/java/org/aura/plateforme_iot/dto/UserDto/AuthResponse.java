package org.aura.plateforme_iot.dto.UserDto;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class AuthResponse {
    private String token;
    private String username;
    private Collection<? extends GrantedAuthority> roles;
    // private Date expirationDate;

    public AuthResponse(String token, String username, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        // this.expirationDate = expirationDate;

    }
}
