package co.bitengine.salvage.services;

import co.bitengine.salvage.models.SalvageItem;
import org.bukkit.inventory.ItemStack;

public class SalvageItemService {
    public static boolean isItem(SalvageItem item, ItemStack input) {
        boolean matches = true;
        if (item.isMatchDisplayName() && item.getItem().hasItemMeta() && item.getItem().getItemMeta().hasDisplayName()) {
            matches = input.hasItemMeta() && input.getItemMeta().hasDisplayName() && input.getItemMeta().getDisplayName().equals(item.getItem().getItemMeta().getDisplayName());
        }
        if (!item.getLoreLinesToMatch().isEmpty() && item.getItem().hasItemMeta() && item.getItem().getItemMeta().hasLore()) {
            if (!input.hasItemMeta() || !input.getItemMeta().hasLore()) matches = false;
            else {
                for (String line : item.getItem().getItemMeta().getLore()) {
                    if (!input.getItemMeta().getLore().contains(line)) {
                        matches = false;
                        break;
                    }
                }
            }
        }
        return matches;
    }
}
