package com.gnegdev.vapp.service.data;

import com.gnegdev.vapp.data.repository.UserRepository;
import com.gnegdev.vapp.dto.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
private final UserRepository userRepository;


public UserDetailsServiceImpl(UserRepository userRepository) {
this.userRepository = userRepository;
}


@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
User user = userRepository.findByEmail(email)
.orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));


// Преобразуем роль в GrantedAuthority (Spring ожидает префикс ROLE_ для ролей)
String roleName = "ROLE_" + user.getRole().name();
return org.springframework.security.core.userdetails.User
.withUsername(user.getEmail())
.password(user.getPasswordHash())
.authorities(List.of(new SimpleGrantedAuthority(roleName)))
.accountExpired(false)
.accountLocked(false)
.credentialsExpired(false)
.disabled(false)
.build();
}
}