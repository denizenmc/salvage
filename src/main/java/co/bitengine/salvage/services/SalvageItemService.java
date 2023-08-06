package co.bitengine.salvage.services;

import co.bitengine.salvage.models.SalvageItem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public class SalvageItemService {
    public static boolean isItem(SalvageItem item, ItemStack input) {
        boolean matches = true;
        if (item.getItem() == null || input == null) return false;
        if (!item.getItem().getType().equals(input.getType())) return false;
        if (!item.isMatchDisplayName() && item.getLoreLinesToMatch().isEmpty()) return item.getItem().isSimilar(input) && input.getAmount() >= item.getItem().getAmount();
        if (item.isMatchDisplayName() && item.getItem().hasItemMeta() && item.getItem().getItemMeta().hasDisplayName()) {
            matches = input.hasItemMeta() && input.getItemMeta().hasDisplayName() && input.getItemMeta().getDisplayName().equals(item.getItem().getItemMeta().getDisplayName());
        }
        if (!item.getLoreLinesToMatch().isEmpty()) {
            if (!input.hasItemMeta() || !input.getItemMeta().hasLore()) matches = false;
            else {
                for (String line : item.getLoreLinesToMatch()) {
                    if (!input.getItemMeta().getLore().contains(line) && !input.getItemMeta().getLore().contains(ChatColor.translateAlternateColorCodes('&', line)) && !input.getItemMeta().getLore().contains(ChatColor.stripColor(line))) {
                        matches = false;
                        break;
                    }
                }
            }
        }
        return matches && input.getAmount() >= item.getItem().getAmount();
    }
}
