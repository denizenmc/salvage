package co.bitengine.salvage.controllers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerController {
    private Map<UUID, List<ItemStack>> input;

    public PlayerController() {
        input = new HashMap<>();
    }

    public void addInput(Player player, ItemStack item) {
        if (!input.containsKey(player.getUniqueId())) input.put(player.getUniqueId(), new ArrayList<>());
        input.get(player.getUniqueId()).add(item);
    }

    public void removeInput(Player player, ItemStack item) {
        if (input.containsKey(player.getUniqueId())) input.get(player.getUniqueId()).remove(item);
    }

    public List<ItemStack> getInput(Player player) {
        return input.get(player.getUniqueId());
    }

}
