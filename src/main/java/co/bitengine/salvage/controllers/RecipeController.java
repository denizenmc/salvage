package co.bitengine.salvage.controllers;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.io.IDAO;
import co.bitengine.salvage.logs.SevereSalvageLog;
import co.bitengine.salvage.models.Loot;
import co.bitengine.salvage.models.Recipe;
import co.bitengine.salvage.models.RecipeQuery;
import co.bitengine.salvage.services.QueryService;
import co.bitengine.salvage.services.RecipeService;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RecipeController {

    private IDAO<Recipe> dao;
    private List<Recipe> cache;

    public RecipeController() {
        dao = getDAO();
        cache = getAll();
    }

    public List<Recipe> getCache() {
        return new ArrayList<>(cache);
    }

    public List<Recipe> getCache(String search) {
        if (search == null) return getCache();
        return QueryService.getRecipes(RecipeQuery.from(search), getCache());
    }

    public Optional<Recipe> get(String name) {
        if (dao == null) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("Failed to load recipes from IO source"), true);
            return Optional.empty();
        }
        return dao.get(name);
    }

    public Optional<Recipe> get(UUID id) {
        if (dao == null) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("Failed to load recipes from IO source"), true);
            return Optional.empty();
        }
        return dao.get(id);
    }

    public void save(Recipe recipe) {
        if (!cache.contains(recipe)) {
            cache.add(recipe);
        }
        if (dao == null) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("Failed to save recipe to IO source"), true);
        } else {
            dao.save(recipe);
        }
    }

    public void delete(Recipe recipe) {
        cache.remove(recipe);
        dao.delete(recipe);
    }

    public Map<Loot, Integer> getPossibleLoot(List<ItemStack> input) {
        return RecipeService.getPossibleLoot(input, getCache());
    }

    public List<ItemStack> getLoot(List<ItemStack> input) {
        return RecipeService.getLoot(input, getCache());
    }

    private IDAO<Recipe> getDAO() {
        Optional<IDAO<Recipe>> recipeDAO = Salvage.getInstance().getDAOController().getRecipeDAO();
        return recipeDAO.orElse(null);
    }

    private List<Recipe> getAll() {
        if (dao == null) {
            Salvage.getInstance().getLogController().log(new SevereSalvageLog("Failed to load recipes from IO source"), true);
            return new ArrayList<>();
        }
        return dao.getAll();
    }

}
