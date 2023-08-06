package co.bitengine.salvage.models;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Recipe implements IModifiable {
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

    @Override
    public Object getValue(String key) {
        switch (key) {
            case "id":
                return id;
            case "name":
                return name;
            case "permission":
                return permission;
        }
        if (key.contains("input") && key.split("-").length > 1) {
            int index = Utils.getIntFromString(key.split("-")[1]);
            if (index < inputs.size()) return inputs.get(index);
        }
        return null;
    }

    @Override
    public void setValue(String key, Object o) {
        switch (key) {
            case "name":
                if (o instanceof String) name = (String) o;
                break;
            case "permission":
                if (o instanceof String) permission = (String) o;
                break;
        }
        if (key.contains("input") && key.split("-").length > 1) {
            int index = Utils.getIntFromString(key.split("-")[1]);
            if (index < inputs.size() && o instanceof String) {
                inputs.get(index).getLoreLinesToMatch().add((String) o);
            }
        } else if (key.contains("output") && key.split("-").length > 2) {
            int index = Utils.getIntFromString(key.split("-")[1]);
            if (index < output.size() && o instanceof String) {
                if (key.contains("chance")) output.get(index).setChance(Utils.getDoubleFromString((String) o));
                else if (key.contains("min")) output.get(index).getRange().setMin(Utils.getIntFromString((String) o));
                else if (key.contains("max")) output.get(index).getRange().setMax(Utils.getIntFromString((String) o));
            }
        }
    }
}
