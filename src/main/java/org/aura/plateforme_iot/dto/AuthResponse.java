package org.aura.plateforme_iot.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;

}