package com.gnegdev.vapp.dto.entity;

import com.gnegdev.vapp.dto.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter; import lombok.Setter; import lombok.EqualsAndHashCode;

@Entity @Table(name = "users")
@Getter @Setter @EqualsAndHashCode(of = "id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String passwordHash;

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
}