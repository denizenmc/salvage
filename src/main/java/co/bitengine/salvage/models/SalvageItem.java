package co.bitengine.salvage.models;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SalvageItem {
    private final ItemStack item;
    private boolean matchDisplayName;
    private final List<String> loreLinesToMatch;

    public SalvageItem(ItemStack item) {
        this.item = item;
        matchDisplayName = false;
        loreLinesToMatch = new ArrayList<>();
    }

    public SalvageItem(ItemStack item, boolean matchDisplayName, List<String> loreLinesToMatch) {
        this.item = item;
        this.matchDisplayName = matchDisplayName;
        this.loreLinesToMatch = loreLinesToMatch;
    }

    public boolean isMatchDisplayName() {
        return matchDisplayName;
    }

    public void setMatchDisplayName(boolean matchDisplayName) {
        this.matchDisplayName = matchDisplayName;
    }

    public ItemStack getItem() {
        return item;
    }

    public List<String> getLoreLinesToMatch() {
        return loreLinesToMatch;
    }
}
