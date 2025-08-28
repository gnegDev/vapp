package com.gnegdev.vapp.dto.entity;

import com.gnegdev.vapp.dto.Status;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Registration {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne(optional = false)
private User participant;


@ManyToOne(optional = false)
private EventRole role;


@Enumerated(EnumType.STRING)
private Status status;
}


