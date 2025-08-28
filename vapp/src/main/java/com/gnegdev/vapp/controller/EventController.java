package com.gnegdev.vapp.controller;

import com.gnegdev.vapp.dto.entity.Event;
import com.gnegdev.vapp.dto.entity.User;
import com.gnegdev.vapp.service.data.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public Event create(@RequestBody Event e, @AuthenticationPrincipal UserDetails ud) {
        // достаём id пользователя через UserDetailsServiceImpl
        User u = eventService.findByEmail(ud.getUsername());
        return eventService.createEvent(e, u.getId());
    }

    @GetMapping
    public List<Event> list() { return eventService.listEvents(); }
}