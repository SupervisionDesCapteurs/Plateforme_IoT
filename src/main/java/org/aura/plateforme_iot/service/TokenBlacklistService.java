package org.aura.plateforme_iot.service;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenBlacklistService {
    private final Set<String> blacklistedTokens = new HashSet<>();


    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    // Check if a token is blacklisted
    public boolean isBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }
}
