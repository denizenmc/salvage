package co.bitengine.salvage.services;

import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.Recipe;
import co.bitengine.salvage.models.SalvageItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeService {
    public static Map<Loot, Integer> getPossibleLoot(List<ItemStack> input, List<Recipe> recipes) {
        Map<Loot, Integer> results = new HashMap<>();
        for (Recipe recipe : recipes) {
            for (ItemStack item : input) {
                if (isRecipeInput(recipe, item)) {
                    for (Loot loot : recipe.getOutput().getLoot()) {
                        results.put(loot, results.containsKey(loot) ? results.get(loot)+1 : 1);
                    }
                }
            }
        }
        return results;
    }

    public static List<ItemStack> getLoot(List<ItemStack> input, List<Recipe> recipes) {
        List<ItemStack> items = new ArrayList<>();
        for (Recipe recipe : recipes) {
            for (ItemStack item : input) {
                if (isRecipeInput(recipe, item)) {
                    items.addAll(LootTableService.generate(recipe.getOutput()));
                }
            }
        }
        return items;
    }

    public static boolean isRecipeInput(Recipe recipe, ItemStack item) {
        if (recipe.getInputs().isEmpty()) return false;
        for (SalvageItem i : new ArrayList<>(recipe.getInputs())) {
            if (SalvageItemService.isItem(i, item)) return true;
        }
        return false;
    }
}
