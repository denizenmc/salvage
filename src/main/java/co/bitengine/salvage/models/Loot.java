package co.bitengine.salvage.models;

import org.bukkit.inventory.ItemStack;

public class Loot {
    private final ItemStack item;
    private double chance;
    private final SalvageResultRange range;
    public Loot(ItemStack item) {
        this.item = item;
        chance = 100;
        range = new SalvageResultRange(item.getAmount(), item.getAmount());
    }

    public ItemStack getItem() {
        return item;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public SalvageResultRange getRange() {
        return range;
    }
}
