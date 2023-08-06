package co.bitengine.salvage.models;

import org.bukkit.inventory.ItemStack;

public class Loot implements Comparable<Loot> {
    private final ItemStack item;
    private double chance;
    private final SalvageResultRange range;
    public Loot(ItemStack item) {
        this.item = item;
        chance = 100;
        range = new SalvageResultRange(item.getAmount(), item.getAmount());
    }

    public Loot(ItemStack item, double chance, SalvageResultRange range) {
        this.item = item;
        this.chance = chance;
        this.range = range;
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

    @Override
    public boolean equals(Object o) {
        return o instanceof Loot && ((Loot) o).getItem().equals(item) && ((Loot) o).getChance() == chance;
    }

    @Override
    public int compareTo(Loot o) {
        return item.getType().toString().compareTo(o.getItem().getType().toString());
    }
}
