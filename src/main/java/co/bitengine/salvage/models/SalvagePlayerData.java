package co.bitengine.salvage.models;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SalvagePlayerData {
    private final UUID uuid;
    private final List<ItemStack> input;
    public SalvagePlayerData(UUID uuid) {
        this.uuid = uuid;
        input = new ArrayList<>();
    }

    public SalvagePlayerData(UUID uuid, List<ItemStack> input) {
        this.uuid = uuid;
        this.input = input;
    }

    public UUID getUUID() {
        return uuid;
    }

    public List<ItemStack> getInput() {
        return input;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SalvagePlayerData && ((SalvagePlayerData) o).getUUID().equals(uuid);
    }
}
