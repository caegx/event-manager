package com.example.EventVenueManagement.controller;


import com.example.EventVenueManagement.model.Venue;
import com.example.EventVenueManagement.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("owner")
public class VenueController {

    @Autowired
    private VenueService service;

    @GetMapping("get-venues")
    public ResponseEntity<List<Venue>> getVenues() {
        return ResponseEntity.ok(service.getVenues());
    }
}
