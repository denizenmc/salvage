package co.bitengine.salvage.models;

import java.util.UUID;

public class Recipe {
    private final UUID id;
    private String name, permission;
    public Recipe() {
        id = UUID.randomUUID();
        name = "New Recipe";
        permission = null;
    }
    public Recipe(UUID id, String name, String permission) {
        this.id = id;
        this.name = name;
        this.permission = permission;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
