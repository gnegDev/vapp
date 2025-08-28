package com.gnegdev.vapp.data.repository;

import com.gnegdev.vapp.dto.entity.EventRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRoleRepository extends JpaRepository<EventRole, Long> {
List<EventRole> findByEventId(Long eventId);
}