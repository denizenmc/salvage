package co.bitengine.salvage.services;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.LootTable;
import co.bitengine.salvage.models.SalvageResultRange;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LootTableServiceTest {

    @Test
    public void testGenerate() {

        LootTable table = new LootTable();
        Loot loot1 = new Loot(new ItemStack(Material.PAPER));
        loot1.setChance(50);
        loot1.getRange().setMin(1);
        loot1.getRange().setMin(6);
        Loot loot2 = new Loot(new ItemStack(Material.FIRE_CHARGE));
        loot2.setChance(75);
        loot2.getRange().setMin(3);
        loot2.getRange().setMax(3);
        Loot loot3 = new Loot(new ItemStack(Material.GUNPOWDER));
        loot3.setChance(25);
        loot3.getRange().setMin(2);
        loot3.getRange().setMax(5);

        table.getLoot().add(loot1);
        table.getLoot().add(loot2);
        table.getLoot().add(loot3);

        boolean valid = true;
        double total = 0;
        for (int i = 0; i < 1000; i++) {
            List<ItemStack> items = LootTableService.generate(table);
            total+=items.size();
            for (ItemStack item : items) {
                switch (item.getType()) {
                    case FIRE_CHARGE:
                        valid = item.getAmount() == 3;
                        break;
                    case GUNPOWDER:
                        valid = item.getAmount() >= 2 && item.getAmount() <= 5;
                        break;
                    case PAPER:
                        valid = item.getAmount() >= 1 && item.getAmount() <= 6;
                }
            }
        }
        assertTrue(valid && total/1000 != 0, "Loot table generation is invalid");
    }

    @Test
    public void testGetQuantity() {
        int value1 = LootTableService.getQuantity(new SalvageResultRange(0, 10));
        int value2 = LootTableService.getQuantity(new SalvageResultRange(0, 10));
        int value3 = LootTableService.getQuantity(new SalvageResultRange(0, 10));
        assertTrue(value1 >= 0 && value1 <= 10 && value2 >= 0 && value2 <= 10 && value3 >= 0 && value3 <= 10,
                "Random range quantity for Loot is producing invalid values");
    }
}