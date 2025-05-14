package me.tinye.shortener.entity;

public enum UserRole {
    PREMIUM("premium"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
