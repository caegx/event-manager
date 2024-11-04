package com.example.EventVenueManagement.repository;

import com.example.EventVenueManagement.model.User;
import com.example.EventVenueManagement.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {
    Optional<User> findByUser();
}
