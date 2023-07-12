package co.bitengine.salvage.io.files;

import co.bitengine.salvage.Salvage;
import co.bitengine.salvage.io.IDAO;
import co.bitengine.salvage.models.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileRecipeDAO implements IDAO<Recipe> {

    @Override
    public Optional<Recipe> get(UUID id) {
        File recipe = FileUtils.getFile(FileUtils.RECIPE_DIRECTORY+"/"+id+".yml");
        if (!recipe.exists()) return Optional.empty();
        return getFromFile(recipe);
    }

    @Override
    public Optional<Recipe> get(String name) {
        File recipes = FileUtils.getFile(FileUtils.RECIPE_DIRECTORY);
        if (!recipes.exists()) recipes.mkdirs();
        File [] list = recipes.listFiles();
        if (list != null && list.length > 0) {
            for (File recipe : list) {
                Optional<Recipe> r = getFromFile(recipe);
                if (r.isPresent() && r.get().getName().equals(name)) return r;
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Recipe> getAll() {
        List<Recipe> all = new ArrayList<>();
        File recipes = FileUtils.getFile(FileUtils.RECIPE_DIRECTORY);
        if (!recipes.exists()) recipes.mkdirs();
        File [] list = recipes.listFiles();
        if (list != null && list.length > 0) {
            for (File recipe : list) {
                Optional<Recipe> r = getFromFile(recipe);
                r.ifPresent(all::add);
            }
        }
        return all;
    }

    @Override
    public void save(Recipe recipe) {
        File file = FileUtils.getFile(FileUtils.RECIPE_DIRECTORY+"/"+recipe.getId().toString()+".yml");
        if (!file.exists()) FileUtils.createFile(file);
        if (!file.exists()) return;
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.set("id", recipe.getId().toString());
        configuration.set("name", recipe.getName());
        configuration.set("permission", recipe.getPermission());
        int count = 1;
        for (SalvageItem input : recipe.getInputs()) {
            configuration.set("input."+count+".item", input.getItem());
            configuration.set("input."+count+".matchDisplayName", input.isMatchDisplayName());
            configuration.set("input."+count+".loreLinesToMatch", input.getLoreLinesToMatch());
            count++;
        }
        count = 1;
        for (Loot loot : recipe.getOutput()) {
            configuration.set("output."+count+".item", loot.getItem());
            configuration.set("output."+count+".chance", loot.getChance());
            configuration.set("output."+count+".range.min", loot.getRange().getMin());
            configuration.set("output."+count+".range.max", loot.getRange().getMax());
            count++;
        }
        FileUtils.saveYML(configuration, file);
    }

    @Override
    public void delete(Recipe recipe) {
        File file = FileUtils.getFile(FileUtils.RECIPE_DIRECTORY+"/"+recipe.getId().toString()+".yml");
        if (file.exists()) file.delete();
    }

    private Optional<Recipe> getFromFile(File file) {
        UUID id;
        String name, permission;
        List<SalvageItem> inputs;
        List<Loot> output;
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        if (configuration.getString("id") == null) return Optional.empty();
        id = UUID.fromString(configuration.getString("id"));
        if (configuration.getString("name") == null) return Optional.empty();
        name = configuration.getString("name");
        permission = configuration.getString("permission");
        inputs = getSalvageItemsFromRecipeConfiguration(configuration);
        output = getLootTableFromRecipeConfiguration(configuration);
        return Optional.of(new Recipe(id, name, permission, inputs, output));
    }

    private List<SalvageItem> getSalvageItemsFromRecipeConfiguration(FileConfiguration configuration) {
        List<SalvageItem> items = new ArrayList<>();
        for (String input : configuration.getConfigurationSection("input").getValues(false).keySet()) {
            ItemStack item = configuration.getItemStack("input."+input+".item");
            boolean matchDisplayName = configuration.getBoolean("input."+input+".matchDisplayName");
            List<String> lorelinesToMatch = configuration.getStringList("input."+input+".loreLinesToMatch");
            items.add(new SalvageItem(item, matchDisplayName, lorelinesToMatch));
        }
        return items;
    }

    private List<Loot> getLootTableFromRecipeConfiguration(FileConfiguration configuration) {
        List<Loot> items = new ArrayList<>();
        for (String output : configuration.getConfigurationSection("output").getValues(false).keySet()) {
            ItemStack item = configuration.getItemStack("output."+output+".item");
            double chance = configuration.getDouble("output."+output+".chance");
            int min = configuration.getInt("output."+output+".range.min");
            int max = configuration.getInt("output."+output+".range.max");
            items.add(new Loot(item, chance, new SalvageResultRange(min, max)));
        }
        return items;
    }
}
