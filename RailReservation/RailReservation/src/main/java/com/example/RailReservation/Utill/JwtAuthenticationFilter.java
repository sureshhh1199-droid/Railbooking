package com.example.RailReservation.Utill;

import com.example.RailReservation.Entity.Agent;
import com.example.RailReservation.Repository.AgentRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final BlacklistService blacklistService;
    private final AgentRepository agentRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader=request.getHeader("Authorization");

        if (authHeader !=null && authHeader.startsWith("Bearer ")){
            String token=authHeader.substring(7);

            if (blacklistService.isBlacklisted(token)){
                filterChain.doFilter(request,response);
            }

            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                Agent agent = agentRepository.findByEmail(email).
                        orElse(null);


                SimpleGrantedAuthority authority = null;
                if (agent != null) {
                    authority = new SimpleGrantedAuthority("ROLE_" + agent.getRole().name());
                }
                assert authority != null;
                var auth = new UsernamePasswordAuthenticationToken(email, null, List.of(authority));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
}

