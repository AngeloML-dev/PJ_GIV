package com.lozano.security;

import com.lozano.domain.entity.User;
import com.lozano.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final IUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no registrado"));
        
        return new org.springframework.security.core.userdetails.User(
          user.getUsername(),
          user.getPassword(),
          user.getRoles().stream()
                  .map(r -> new SimpleGrantedAuthority(r.getName()))
                  .toList()
        );


    }
}
