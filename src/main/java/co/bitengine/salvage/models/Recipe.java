package co.bitengine.salvage.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Recipe {
    private final UUID id;
    private String name, permission;
    private final List<SalvageItem> inputs;
    private final List<Loot> output;

    public Recipe() {
        id = UUID.randomUUID();
        name = "New Recipe";
        permission = null;
        inputs = new ArrayList<>();
        output = new ArrayList<>();
    }
    public Recipe(UUID id, String name, String permission, List<SalvageItem> inputs, List<Loot> output) {
        this.id = id;
        this.name = name;
        this.permission = permission;
        this.inputs = inputs;
        this.output = output;
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

    public List<SalvageItem> getInputs() { return inputs; }

    public List<Loot> getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Recipe && ((Recipe) o).getId().equals(id);
    }
}
