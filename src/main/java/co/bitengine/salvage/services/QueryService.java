package co.bitengine.salvage.services;

import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.Recipe;
import co.bitengine.salvage.models.RecipeQuery;
import co.bitengine.salvage.models.SalvageItem;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QueryService {
    public static List<Recipe> getRecipes(RecipeQuery query, List<Recipe> allRecipes) {
        List<Recipe> r = new ArrayList<>();
        for (Recipe recipe : allRecipes) {
            boolean canAdd = true;
            if (query.getName() != null) {
                if (!(recipe.getName().contains(query.getName()) ||
                        ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', recipe.getName())).contains(query.getName()))) {
                   canAdd = false;
                }
            }
            if (query.getPermission() != null) {
                if (recipe.getPermission() == null) canAdd = false;
                if (!recipe.getPermission().contains(query.getPermission())) canAdd = false;
            }
            if (!query.getSalvageItemTexts().isEmpty()) {
                Set<String> found = new HashSet<>();
                for (SalvageItem item : new ArrayList<>(recipe.getInputs())) {
                    if (item.getItem().hasItemMeta()) {
                        if (item.getItem().getItemMeta().hasDisplayName()) {
                            for (String input : query.getSalvageItemTexts()) {
                                if (ChatColor.stripColor(item.getItem().getItemMeta().getDisplayName()).contains(input) ||
                                item.getItem().getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', input))) {
                                    found.add(input);
                                }
                            }
                        }
                        if (item.getItem().getItemMeta().hasLore()) {
                            for (String line : item.getItem().getItemMeta().getLore()) {
                                for (String input : query.getSalvageItemTexts()) {
                                    if (line.contains(input)) found.add(input);
                                }
                            }
                        }
                    }
                }
                if (found.size() != query.getSalvageItemTexts().size()) canAdd = false;
            }
            if (!query.getLootTableItemTexts().isEmpty()) {
                Set<String> found = new HashSet<>();
                for (Loot loot : new ArrayList<>(recipe.getOutput())) {
                    if (loot.getItem().hasItemMeta()) {
                        if (loot.getItem().getItemMeta().hasDisplayName()) {
                            for (String input : query.getLootTableItemTexts()) {
                                if (ChatColor.stripColor(loot.getItem().getItemMeta().getDisplayName()).contains(input) ||
                                        loot.getItem().getItemMeta().getDisplayName().contains(ChatColor.translateAlternateColorCodes('&', input))) {
                                    found.add(input);
                                }
                            }
                        }
                        if (loot.getItem().getItemMeta().hasLore()) {
                            for (String line : loot.getItem().getItemMeta().getLore()) {
                                for (String input : query.getLootTableItemTexts()) {
                                    if (line.contains(input)) found.add(input);
                                }
                            }
                        }
                    }
                }
                if (found.size() != query.getLootTableItemTexts().size()) canAdd = false;
            }
            if (canAdd) r.add(recipe);
        }
        return r;
    }
}
