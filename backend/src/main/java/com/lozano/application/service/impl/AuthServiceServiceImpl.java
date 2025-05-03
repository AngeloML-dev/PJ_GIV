package com.lozano.application.service.impl;

import com.lozano.application.dto.Login.LoginRequest;
import com.lozano.application.dto.Login.LoginResponse;
import com.lozano.application.service.IAuthService;
import com.lozano.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                  loginRequest.getUsername(),
                  loginRequest.getPassword()
                )
        );
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(user);
        long expiration = jwtUtil.extractExpiration(token).getTime();
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getAuthorities().stream()
                        .map(r -> r.getAuthority())
                        .toList())
                .expiresAt(expiration)
                .build();
    }
}
