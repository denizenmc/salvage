package co.bitengine.salvage.tests;

import co.bitengine.salvage.logs.SalvageLog;
import co.bitengine.salvage.logs.SevereSalvageLog;
import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.Recipe;
import co.bitengine.salvage.models.RecipeQuery;
import co.bitengine.salvage.models.SalvageItem;
import co.bitengine.salvage.services.QueryService;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryServiceTest implements ISalvageTest {
    @Override
    public List<SalvageLog> run() {
        return testGetRecipes();
    }

    private List<SalvageLog> testGetRecipes() {
        List<SalvageLog> logs = new ArrayList<>();

        // query 1 - name, item input
        Recipe recipe1 = new Recipe();
        recipe1.setName("Unique Dust");
        ItemStack item1 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(ChatColor.GOLD + "Excalibur");
        item1.setItemMeta(meta1);
        recipe1.getInputs().add(new SalvageItem(item1));

        List<Recipe> recipes = new ArrayList<>(Arrays.asList(recipe1));

        if (QueryService.getRecipes(RecipeQuery.from("name=Unique&input=Excal"), recipes).isEmpty()) {
            logs.add(new SevereSalvageLog("[TEST FAILED] Query 1"));
        }

        Recipe recipe2 = new Recipe();
        recipe2.setName("Unique Dust");
        recipe2.setPermission("salvage.uniquedust");
        ItemStack item2 = new ItemStack(Material.GUNPOWDER);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName(ChatColor.GRAY + "Dust");
        item2.setItemMeta(meta2);
        recipe2.getOutput().add(new Loot(item2));

        recipes.add(recipe2);

        if (QueryService.getRecipes(RecipeQuery.from("output=Dust"), recipes).isEmpty()) {
            logs.add(new SevereSalvageLog("[TEST FAILED] Query 2"));
        }

        if (QueryService.getRecipes(RecipeQuery.from("permission=salvage.uniquedust"), recipes).isEmpty()) {
            logs.add(new SevereSalvageLog("[TEST FAILED] Query 3"));
        }

        if (!QueryService.getRecipes(RecipeQuery.from("permission=salvage.none"), recipes).isEmpty()) {
            logs.add(new SevereSalvageLog("[TEST FAILED] Query 4"));
        }
        return logs;
    }
}
