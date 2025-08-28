package com.gnegdev.vapp.dto.request;

import com.gnegdev.vapp.dto.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(@Email String email, @NotBlank String password, @NotBlank String name, @NotNull Role role) {}
