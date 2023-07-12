package co.bitengine.salvage.services;

import co.bitengine.salvage.Utils;
import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.LootTable;
import co.bitengine.salvage.models.SalvageResultRange;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootTableService {
    public static List<ItemStack> generate(List<Loot> lootTable) {
        List<ItemStack> items = new ArrayList<>();
        for (Loot loot : lootTable) {
            double value = Utils.getRandomValue(0, 100);
            if (loot.getChance() >= value) {
                ItemStack item = new ItemStack(loot.getItem());
                item.setAmount(LootTableService.getQuantity(loot.getRange()));
                items.add(item);
            }
        }
        return items;
    }
    public static int getQuantity(SalvageResultRange range) {
        return new Random().nextInt(range.getMax()-range.getMin()+1)+range.getMin();
    }
}
