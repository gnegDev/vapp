package com.gnegdev.vapp.service.data;

import com.gnegdev.vapp.data.repository.AttendanceRepository;
import com.gnegdev.vapp.data.repository.RegistrationRepository;
import com.gnegdev.vapp.dto.entity.Attendance;
import com.gnegdev.vapp.dto.entity.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
@Autowired
private AttendanceRepository attRepo;
@Autowired private RegistrationRepository regRepo;


public Attendance markAttendance(Long registrationId, boolean attended, int hours) {
Registration r = regRepo.findById(registrationId).orElseThrow();
Attendance a = new Attendance();
a.setRegistration(r);
a.setAttended(attended);
a.setHours(hours);
return attRepo.save(a);
}
}