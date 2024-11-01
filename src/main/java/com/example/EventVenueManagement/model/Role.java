package com.example.EventVenueManagement.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.EventVenueManagement.model.Permission.*;

@RequiredArgsConstructor
public enum Role {
    VENUE_OWNER(
            Set.of(
                    VENUE_OWNER_CREATE,
                    VENUE_OWNER_DELETE,
                    VENUE_OWNER_UPDATE,
                    VENUE_OWNER_READ

            )

    ),

    EVENT_PLANNER(Set.of(
            EVENT_PLANNER_CREATE,
            EVENT_PLANNER_DELETE,
            EVENT_PLANNER_UPDATE,
            EVENT_PLANNER_READ
    )),
    ADMIN(
            Set.of(
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    ADMIN_READ,
                    ADMIN_UPDATE)
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
