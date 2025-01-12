package com.sales.market.data.model;

public enum RoleType {
    ADMIN(1, "ROLE_ADMIN"),
    GENERAL(2, "ROLE_GENERAL"),
    SUPERVISOR(3, "ROLE_SUPERVISOR");

    private final long id;
    private final String type;

    RoleType(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}
