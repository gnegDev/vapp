package com.gnegdev.vapp.data.repository;

import com.gnegdev.vapp.dto.entity.Event;
import com.gnegdev.vapp.dto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByOrganizerIdOrderByStartTimeDesc(Long organizerId);
}