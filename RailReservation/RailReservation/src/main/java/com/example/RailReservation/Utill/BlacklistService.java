package com.example.RailReservation.Utill;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BlacklistService {
    private final Set<String> blacklistedTokens= ConcurrentHashMap.newKeySet();

    public void blacklistToken(String token){
        blacklistedTokens.add(token);
    }
    public boolean isBlacklisted(String token){
        return blacklistedTokens.contains(token);
    }
}
