package com.example.EventVenueManagement.service;

import com.example.EventVenueManagement.model.Venue;
import com.example.EventVenueManagement.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    @Autowired
    private VenueRepository repository;


    public List<Venue> getVenues() {
        return repository.findAll();
    }
}
