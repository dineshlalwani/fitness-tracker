package com.dl.fitness_tracking_app.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.Set;

import static com.dl.fitness_tracking_app.entity.Permission.*;

@RequiredArgsConstructor
@Getter
public enum Role {
    USER(
            Set.of(
                    USER_CREATE,
                    USER_UPDATE,
                    USER_READ,
                    USER_DELETE
            )
    ),
    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_CREATE,
                    ADMIN_DELETE,
                    ADMIN_UPDATE,
                    USER_CREATE,
                    USER_UPDATE,
                    USER_READ,
                    USER_DELETE
            )
    );

    private final Set<Permission> permissions;

}
