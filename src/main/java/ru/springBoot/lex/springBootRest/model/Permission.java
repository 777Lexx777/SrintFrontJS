package ru.springBoot.lex.springBootRest.model;

public enum Permission {
    DEVELOPER_READ("ROLE_USER"),
    DEVELOPER_WRITE("ROLE_ADMIN");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
