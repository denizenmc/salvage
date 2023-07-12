package co.bitengine.salvage.tests;

import co.bitengine.salvage.logs.SalvageLog;
import co.bitengine.salvage.logs.SevereSalvageLog;
import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.Recipe;
import co.bitengine.salvage.models.SalvageItem;
import co.bitengine.salvage.services.RecipeService;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeServiceTest implements ISalvageTest {
    @Override
    public List<SalvageLog> run() {
        List<SalvageLog> logs = new ArrayList<>();
        logs.addAll(testGetPossibleLoot());
        logs.addAll(testGetLoot());
        return logs;
    }

    private List<SalvageLog> testGetPossibleLoot() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Unique Dust");
        ItemStack item1 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.GOLD + "Excalibur");
        item1.setItemMeta(meta1);
        recipe1.getInputs().add(new SalvageItem(item1));

        List<Recipe> recipes = new ArrayList<>(Arrays.asList(recipe1));

        Recipe recipe2 = new Recipe();
        recipe2.setName("Unique Dust");
        recipe2.setPermission("salvage.uniquedust");
        ItemStack item2 = new ItemStack(Material.GUNPOWDER);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.GRAY + "Dust");
        item2.setItemMeta(meta2);
        recipe2.getOutput().getLoot().add(new Loot(item2));

        recipes.add(recipe2);

        if (RecipeService.getPossibleLoot(new ArrayList<>(Arrays.asList(item1, item2)), recipes).isEmpty()
        || RecipeService.getPossibleLoot(new ArrayList<>(Arrays.asList(item1, item2)), recipes).size() != 1) {
            return new ArrayList<>(Arrays.asList(new SevereSalvageLog("[TEST FAILED] Recipe get possible loot")));
        }
        return new ArrayList<>();
    }

    private List<SalvageLog> testGetLoot() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Unique Dust");
        ItemStack item1 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.GOLD + "Excalibur");
        item1.setItemMeta(meta1);
        recipe1.getInputs().add(new SalvageItem(item1));
        recipe1.getOutput().getLoot().add(new Loot(item1));

        List<Recipe> recipes = new ArrayList<>(Arrays.asList(recipe1));

        Recipe recipe2 = new Recipe();
        recipe2.setName("Unique Dust");
        recipe2.setPermission("salvage.uniquedust");
        ItemStack item2 = new ItemStack(Material.GUNPOWDER);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.GRAY + "Dust");
        item2.setItemMeta(meta2);
        recipe2.getInputs().add(new SalvageItem(item2));
        recipe2.getOutput().getLoot().add(new Loot(item2));

        recipes.add(recipe2);
        if (RecipeService.getLoot(new ArrayList<>(Arrays.asList(item1.clone(), item2.clone())), recipes).isEmpty()
                || RecipeService.getLoot(new ArrayList<>(Arrays.asList(item1.clone(), item2.clone())), recipes).size() != 2) {
            return new ArrayList<>(Arrays.asList(new SevereSalvageLog("[TEST FAILED] Recipe get loot")));
        }
        return new ArrayList<>();
    }
}
