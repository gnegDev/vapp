package com.gnegdev.vapp.service.security.auth;

import com.gnegdev.vapp.data.repository.EventRoleRepository;
import com.gnegdev.vapp.data.repository.RegistrationRepository;
import com.gnegdev.vapp.data.repository.UserRepository;
import com.gnegdev.vapp.dto.Status;
import com.gnegdev.vapp.dto.entity.EventRole;
import com.gnegdev.vapp.dto.entity.Registration;
import com.gnegdev.vapp.dto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
@Autowired
private RegistrationRepository regRepo;
@Autowired private EventRoleRepository roleRepo;
@Autowired private UserRepository userRepo;


public Registration registerForRole(Long userId, Long roleId) {
if (regRepo.existsByParticipantIdAndRoleId(userId, roleId)) {
throw new RuntimeException("Already registered");
}
User participant = userRepo.findById(userId).orElseThrow();
EventRole role = roleRepo.findById(roleId).orElseThrow();


if (role.getRegistrations().size() >= role.getNeededCount()) {
throw new RuntimeException("Role is full");
}


Registration r = new Registration();
r.setParticipant(participant);
r.setRole(role);
r.setStatus(Status.CONFIRMED);
return regRepo.save(r);
}
}