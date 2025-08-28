package com.gnegdev.vapp.dto.request;

import com.gnegdev.vapp.dto.Role;

public record AuthResponse(String token, Long userId, String name, Role role) {}
