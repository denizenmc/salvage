package co.bitengine.salvage.tests;

import co.bitengine.salvage.logs.SalvageLog;
import co.bitengine.salvage.logs.SevereSalvageLog;
import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.LootTable;
import co.bitengine.salvage.models.SalvageResultRange;
import co.bitengine.salvage.services.LootTableService;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LootTableServiceTest implements ISalvageTest {
    @Override
    public List<SalvageLog> run() {
        List<SalvageLog> logs = new ArrayList<>();
        logs.addAll(testGenerate());
        logs.addAll(testGetQuantity());
        return logs;
    }

    private List<SalvageLog> testGenerate() {
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
        return (!(valid && total/1000 != 0)) ? new ArrayList<>(Arrays.asList(new SevereSalvageLog("[TEST FAILED] Loot table generate"))) : new ArrayList<>();
    }

    private List<SalvageLog> testGetQuantity() {
        int value1 = LootTableService.getQuantity(new SalvageResultRange(0, 10));
        int value2 = LootTableService.getQuantity(new SalvageResultRange(0, 10));
        int value3 = LootTableService.getQuantity(new SalvageResultRange(0, 10));
        return value1 >= 0 && value1 <= 10 && value2 >= 0 && value2 <= 10 && value3 >= 0 && value3 <= 10 ?
                new ArrayList<>() : new ArrayList<>(Arrays.asList(new SevereSalvageLog("[TEST FAILED] Loot table get quantity from range")));
    }
}
