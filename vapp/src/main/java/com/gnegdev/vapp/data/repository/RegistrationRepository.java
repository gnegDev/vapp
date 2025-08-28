package com.gnegdev.vapp.data.repository;

import com.gnegdev.vapp.dto.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
List<Registration> findByParticipantId(Long participantId);
List<Registration> findByRoleId(Long roleId);
boolean existsByParticipantIdAndRoleId(Long participantId, Long roleId);
}