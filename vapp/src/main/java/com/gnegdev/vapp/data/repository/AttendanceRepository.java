package com.gnegdev.vapp.data.repository;

import com.gnegdev.vapp.dto.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
List<Attendance> findByRegistrationId(Long registrationId);
}