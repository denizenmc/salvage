package co.bitengine.salvage.tests;

import co.bitengine.salvage.logs.SalvageLog;
import co.bitengine.salvage.logs.SevereSalvageLog;
import co.bitengine.salvage.models.SalvageItem;
import co.bitengine.salvage.services.SalvageItemService;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SalvageItemServiceTest implements ISalvageTest {
    @Override
    public List<SalvageLog> run() {
        return testIsItem();
    }

    private List<SalvageLog> testIsItem() {
        List<SalvageLog> logs = new ArrayList<>();
        ItemStack item1 = new ItemStack(Material.DIAMOND_SWORD);
        item1.setAmount(2);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.GOLD + "Excalibur");
        meta1.setLore(Arrays.asList(ChatColor.GRAY + "The best sword in the land", ChatColor.YELLOW + "None Compare"));
        item1.setItemMeta(meta1);
        SalvageItem salvageItem = new SalvageItem(item1);
        salvageItem.setMatchDisplayName(true);
        salvageItem.getLoreLinesToMatch().add("&eNone Compare");

        ItemStack item2 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.GRAY + "Dust");
        meta2.setLore(Arrays.asList(ChatColor.GRAY + "The best sword in the land", ChatColor.YELLOW + "None Compare"));
        item2.setItemMeta(meta2);

        if (SalvageItemService.isItem(salvageItem, item2)) logs.add(new SevereSalvageLog("[TEST FAILED] Salvage item match 1"));
        salvageItem.setMatchDisplayName(false);
        if (SalvageItemService.isItem(salvageItem, item2)) logs.add(new SevereSalvageLog("[TEST FAILED] Salvage item match 2"));
        item2.setAmount(3);
        if (!SalvageItemService.isItem(salvageItem, item2)) logs.add(new SevereSalvageLog("[TEST FAILED] Salvage item match 3"));
        return logs;
    }

}
