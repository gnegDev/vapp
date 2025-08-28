package com.gnegdev.vapp.dto.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 4000)
    private String description;
    @Column(nullable = false)
    private OffsetDateTime startTime;
    @Column(nullable = false)
    private OffsetDateTime endTime;
    @Column(nullable = false)
    private String location;
    @ManyToOne(optional = false)
    private User organizer;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventRole> roles = new ArrayList<>();
}