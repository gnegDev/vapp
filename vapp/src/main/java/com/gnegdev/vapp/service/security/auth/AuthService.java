package com.gnegdev.vapp.service.security.auth;

import com.gnegdev.vapp.data.repository.UserRepository;
import com.gnegdev.vapp.dto.entity.User;
import com.gnegdev.vapp.dto.request.AuthResponse;
import com.gnegdev.vapp.dto.request.LoginRequest;
import com.gnegdev.vapp.dto.request.RegisterRequest;
import com.gnegdev.vapp.service.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtTokenProvider tokenProvider;

    public AuthResponse register(RegisterRequest req) {
        if (userRepo.findByEmail(req.email()).isPresent()) {
            throw new RuntimeException("Email already taken");
        }
        User u = new User();
        u.setEmail(req.email());
        u.setPasswordHash(encoder.encode(req.password()));
        u.setName(req.name());
        u.setRole(req.role());
        userRepo.save(u);
        return login(new LoginRequest(req.email(), req.password()));
    }

    public AuthResponse login(LoginRequest req) {
        User u = userRepo.findByEmail(req.email())
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));
        if (!encoder.matches(req.password(), u.getPasswordHash())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        UserDetails ud = new org.springframework.security.core.userdetails.User(
                u.getEmail(), u.getPasswordHash(),
                List.of(new SimpleGrantedAuthority("ROLE_"+u.getRole().name())));
        String token = tokenProvider.generateToken(ud);
        return new AuthResponse(token, u.getId(), u.getName(), u.getRole());
    }
}