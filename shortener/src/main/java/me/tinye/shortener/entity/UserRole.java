package me.tinye.shortener.entity;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum UserRole {
    USER("user"),
    PREMIUM("premium");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
