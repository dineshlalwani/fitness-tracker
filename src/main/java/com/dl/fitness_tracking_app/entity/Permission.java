package com.dl.fitness_tracking_app.entity;

import lombok.RequiredArgsConstructor;

public enum Permission {

    ADMIN_READ("read"),
    ADMIN_CREATE("create"),
    ADMIN_UPDATE("update"),
    ADMIN_DELETE("delete"),

    USER_READ("read"),
    USER_CREATE("create"),
    USER_UPDATE("update"),
    USER_DELETE("delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
