package com.gnegdev.vapp.service.data;

import com.gnegdev.vapp.data.repository.EventRepository;
import com.gnegdev.vapp.data.repository.UserRepository;
import com.gnegdev.vapp.dto.Role;
import com.gnegdev.vapp.dto.entity.Event;
import com.gnegdev.vapp.dto.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepo;
    @Autowired private UserRepository userRepo;

    public Event createEvent(Event event, Long organizerId) {
        User org = userRepo.findById(organizerId).orElseThrow();
        if (org.getRole() != Role.ORGANIZER) throw new RuntimeException("Only organizers");
        event.setOrganizer(org);
        return eventRepo.save(event);
    }

    public List<Event> listEvents() {
        return eventRepo.findAll();
    }

}