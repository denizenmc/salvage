package co.bitengine.salvage.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Recipe {
    private final UUID id;
    private String name, permission;
    private final List<SalvageableItem> inputs;
    public Recipe() {
        id = UUID.randomUUID();
        name = "New Recipe";
        permission = null;
        inputs = new ArrayList<>();
    }
    public Recipe(UUID id, String name, String permission, List<SalvageableItem> inputs) {
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.inputs = inputs;
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

    public List<SalvageableItem> getInputs() { return inputs; }
}
