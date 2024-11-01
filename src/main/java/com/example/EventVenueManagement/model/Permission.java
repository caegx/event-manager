package com.example.EventVenueManagement.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum Permission {

    VENUE_OWNER_CREATE("owner:create"),
    VENUE_OWNER_READ("owner:read"),
    VENUE_OWNER_UPDATE("owner:update"),
    VENUE_OWNER_DELETE("owner:delete"),


    EVENT_PLANNER_READ("planner:read"),
    EVENT_PLANNER_CREATE("planner:create"),
    EVENT_PLANNER_UPDATE("planner:update"),
    EVENT_PLANNER_DELETE("planner:delete"),

    ADMIN_READ("planner:read"),
    ADMIN_CREATE("planner:create"),
    ADMIN_UPDATE("planner:update"),
    ADMIN_DELETE("planner:delete");

    @Getter
    private final String permission;
}
